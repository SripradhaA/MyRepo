/**
 * 
 */
package com.EMD.LSDB.common.framework.notificationservice;

/**
 * @author ps57222
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.sql.RowSet;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.logger.LogLevel;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.resourceloader.EMDEnvLoader;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.dao.common.DBHelper;

/**
 * <p><Description></p>
 * 
 * @author na25828
 * @version 1.0
 */

/**
 * total of 3 (configurable) attempts are made to send the mail.  On successful 
 * delivery it updates the Mail Status to "DELIVERED" in the database.
 */
public class MailService implements Job {
	
	public final static Logger logger = Logger.getLogger(MailService.class);
	
	private Connection objconnection = null;
	
	private EMDEnvLoader objEnvLoader = EMDEnvLoader.getInstance();
	
	//private String MAIL_SMTP_HOST =(String) objEnvLoader.getEnvEntryValue(NotificationConstants.MAIL_SMTP_HOST);
	
	//private String MAIL_SMTP_AUTH =(String) objEnvLoader.getEnvEntryValue(NotificationConstants.MAIL_SMTP_AUTH);
	
	/**
	 * 
	 * 		Default Constructor
	 */
	
	public MailService() {
		
	}
	
	/**
	 * 		This static method is used to get Individual MailAddress by breaking comma
	 * 		@param breakComma
	 *		@return Collection
	 */
	
	private Collection getAddress(String breakComma) {
		
		if (breakComma == null)
			return null;
		Collection collectionAddr = new ArrayList();
		StringTokenizer AddrToken = new StringTokenizer(breakComma, ",");
		while (AddrToken.hasMoreElements()) {
			String tokenStr = AddrToken.nextToken();
			InternetAddress address;
			collectionAddr.add(tokenStr);
		}
		return collectionAddr;
	}
	
	/**
	 * 		This method is called by a scheduler.  It retrieves the list of pending mails 
	 * 		from the database and sends email notifications accordingly.  
	 * 
	 * 		1. Get the list of pending mails from the database 
	 * 		[Vector list = loadUnSentMails]
	 * 		2. Send email notificatiaon
	 * 		[Iterate the list and for every element in the list call the 'sendMail' method
	 * 		if (SendMail(tmail))
	 * 		//update the column DELIVERY_ATTEMPTS
	 * 		//update the column MAIL_STATUS
	 * 		//update the column DELIVERY_TIMESTAMP
	 * 		in TBL_MailBox]
	 * 		@throws com.gm.lfo.framework.notificationservice.MailDeliveryException
	 */
	
	public void postMails() throws MailDeliveryException, DataAccessException {
		
		LogUtil.logMessage("Enters into MailService:postMails");
		
		try {
			//int deliveryAttempts = 0;
			String sqlQuery = "";
			
			//int returnValue = 0;
			
			/*
			 * Here we Call the unsentMails method to load all the mails from Email_Log Table with Flag 'N'
			 */
			
			ArrayList mailTOCollection = (ArrayList) loadUnSentMails();
			LogUtil.logMessage("mailTOCollection " + mailTOCollection.size());
			
			objconnection = DBHelper.prepareConnection();
			EmailTO m_mailTO = null;
			
			for (int count = 0; count < mailTOCollection.size(); count++) {
				try {
					
					m_mailTO = (EmailTO) mailTOCollection.get(count);
					//deliveryAttempts = m_mailTO.getDeliveryAttempt();
					
					Integer activityID = new Integer(m_mailTO.getActivityID());
					
					if (this.sendMail(m_mailTO)) {
						Vector parameter = new Vector();
						parameter.add(NotificationConstants.DELIVERED_VAL
								.toString());
						parameter.add(activityID);
						sqlQuery = NotificationQueries.UPDATE_MAIL_STATUS;
						
						int flag = DBHelper.executeUpdate(objconnection,
								sqlQuery, parameter);
						
					} else {
						LogUtil.logMessage("Inside Else Block");
						LogUtil.logMessage("No More Mails to sent");
						
					}
				}
				
				catch (MailDeliveryException exec) {
					LogUtil.logMessage(exec.getMessage(), LogLevel.ERROR);
					LogUtil.logError(exec);
					
				}
				
			}
		} catch (Exception exec) {
			LogUtil.logMessage(exec.getMessage(), LogLevel.ERROR);
			LogUtil.logMessage("MAIL IS GIVEING ERROR......" + exec);
			LogUtil.logError(exec);
		} finally {
			DBHelper.closeConnection(objconnection);
		}
	}
	
	/**
	 * 	This method is used to fetch the pending (UNDELIVERED) mails from the database.
	 * 	@return Collection
	 */
	
	private synchronized ArrayList loadUnSentMails() throws DataAccessException {
		
		LogUtil.logMessage("Enters into MailService:loadUnSentMails");
		String resultSetAddr = "";
		ArrayList collMail = new ArrayList();
		Collection collMailAddr = null;
		try {
			objconnection = DBHelper.prepareConnection();
			LogUtil
			.logMessage("Enters into MailService:loadUnSentMails:sqlQuery");
			String sqlQuery = NotificationQueries.UNSENT_MAILS;
			
			List params = new ArrayList();
			params.add(NotificationConstants.UNDELIVERED_VAL.toString());
			RowSet resultSet = DBHelper.executeQuery(objconnection, sqlQuery,
					params);
			
			EmailTO m_mailTO = null;
			while (resultSet.next()) {
				m_mailTO = new EmailTO();
				m_mailTO.setFrom(resultSet
						.getString(NotificationConstants.MAIL_FROM));
				LogUtil.logMessage("MAIL_FROM:" + m_mailTO.getFrom());
				
				resultSetAddr = resultSet
				.getString(NotificationConstants.MAIL_TO);
				LogUtil.logMessage("MAIL_TO:" + resultSetAddr);
				collMailAddr = getAddress(resultSetAddr);
				m_mailTO.setTo(collMailAddr);
				
				resultSetAddr = resultSet
				.getString(NotificationConstants.MAIL_CC);
				collMailAddr = getAddress(resultSetAddr);
				m_mailTO.setCarbonCopy(collMailAddr);
				LogUtil.logMessage("MAIL_CC:" + resultSetAddr);
				
				m_mailTO.setSubject(resultSet
						.getString(NotificationConstants.MAIL_SUBJECT));
				LogUtil.logMessage("MAIL_SUBJECT:" + m_mailTO.getSubject());
				// Updated from getString to getClob for Production 1058 mail issue dated 08-July-2014
				m_mailTO.setMessage(ApplicationUtil.clobToString(resultSet
						.getClob(NotificationConstants.MAIL_MESSAGE)));
				
				LogUtil.logMessage("MAIL_MESSAGE:" + m_mailTO.getMessage());
				
				
				/*m_mailTO.setDeliveryAttempt(resultSet.getInt(NotificationConstants.DELIVERY_ATTEMPTS));
				 LogUtil.logMessage("DELIVERY_ATTEMPTS:"+m_mailTO.getDeliveryAttempt());*/
				
				m_mailTO.setMailStatus(resultSet
						.getString(NotificationConstants.MAIL_STATUS));
				LogUtil.logMessage("MAIL_STATUS:" + m_mailTO.getMailStatus());
				m_mailTO.setActivityID(resultSet
						.getInt(NotificationConstants.MAIL_ID));
				LogUtil.logMessage("MAIL_ID:" + m_mailTO.getActivityID());
				collMail.add(m_mailTO);
			}
		} catch (SQLException sqle) {
			LogUtil.logMessage("Enters into Catch Block:" + sqle.getMessage());
			ErrorInfo errorInfoObj = new ErrorInfo();
			errorInfoObj.setMessage(sqle.getMessage());
			throw new DataAccessException(errorInfoObj);
		} catch (Exception sqle) {
			sqle.printStackTrace();
			ErrorInfo errorInfoObj = new ErrorInfo();
			errorInfoObj.setMessage(sqle.getMessage());
			throw new DataAccessException(errorInfoObj);
		} finally {
			DBHelper.closeConnection(objconnection);
		}
		LogUtil.logMessage("Leaves From MailService:loadUnSentMails");
		return collMail;
		
	}
	
	/**
	 * 	This method is used to send the email notification using java mail API.
	 *	@param p_mailDtls
	 * 	@return Boolean
	 * 	@throws com.gm.lfo.framework.notificationservice.MailDeliveryException
	 */
	
	public boolean sendMail(EmailTO objEmailTO) throws MailDeliveryException {
		
		LogUtil.logMessage("Enters into MailService:sendMail");
		
		boolean isSent = false;
		
		Properties objproperties = new Properties();
		objproperties.put(NotificationConstants.SENDMAIL_HOST,
				NotificationConstants.MAIL_SMTP_HOST);
		LogUtil.logMessage("SENDMAIL_HOST-------------------" + NotificationConstants.SENDMAIL_HOST );
		LogUtil.logMessage("MAIL_SMTP_HOST-------------------" + NotificationConstants.MAIL_SMTP_HOST );
		Session session = Session.getInstance(objproperties, null);
		
		//Transport transport = null;
		Message message = new MimeMessage(session);
		InternetAddress[] addressTO = null;
		
		InternetAddress[] addressCC = null;
		
		try {
			
			if (objEmailTO != null) {
				if (objEmailTO.getTo() != null) {
					Iterator it = objEmailTO.getTo().iterator();
					addressTO = new InternetAddress[(objEmailTO.getTo()).size()];
					int count = 0;
					while (it.hasNext()) {
						String address = (String) it.next();
						addressTO[count] = new InternetAddress(address);
						count++;
					}
					addressTO = new InternetAddress[(objEmailTO.getTo()).size()];
					for (count = 0; count < (objEmailTO.getTo()).size(); count++) {
						String address = (String) ((ArrayList) objEmailTO
								.getTo()).get(count);
						addressTO[count] = new InternetAddress(address);
					}
				}
				
				if (objEmailTO.getCarbonCopy() != null) {
					Iterator it = objEmailTO.getCarbonCopy().iterator();
					addressCC = new InternetAddress[(objEmailTO.getCarbonCopy())
					                                .size()];
					int count = 0;
					while (it.hasNext()) {
						String address = (String) it.next();
						addressCC[count] = new InternetAddress(address);
						count++;
					}
				}
				
				//message.setText(objEmailTO.getMessage()); commented for CR-126
				
				message.setRecipients(Message.RecipientType.TO, addressTO);
				
				message.setRecipients(Message.RecipientType.CC, addressCC);
				
				message.setFrom(new InternetAddress(objEmailTO.getFrom()));
				
				message.setSubject(objEmailTO.getSubject());
				
				//Added for CR-126 for sending html format email
				message.setContent(objEmailTO.getMessage(),"text/html");
				
				message.saveChanges();
				Transport.send(message);
				
				isSent = true;
			}
		}
		
		catch (AddressException objaddressException) {
			LogUtil.logMessage("AddressException is thrown by the mail server");
			throw new MailDeliveryException(objaddressException, new Integer(
					objEmailTO.getActivityID()), objEmailTO
					.getDeliveryAttempt());
		} catch (MessagingException objmessageException) {
			LogUtil
			.logMessage("MessagingException is thrown by the mail server");
			LogUtil.logError(objmessageException);
			
		}
		
		return isSent;
	}
	
	/**
	 * 		configuration file. This method performs the function of sending emails for all 
	 * 		pending records in the Email table in database.
	 * 		@param p_context
	 */
	
	public void execute(JobExecutionContext p_context)
	throws org.quartz.JobExecutionException {
		
		try {
			LogUtil.logMessage("Enters into MailService:execute");
			
			postMails();
			
		} catch (Exception objMailDeliveryException) {
			
			LogUtil.logMessage("SENDING MAIL EXECEPTION"
					+ objMailDeliveryException.getMessage());
			LogUtil.logError(objMailDeliveryException);
		}
	}
}
