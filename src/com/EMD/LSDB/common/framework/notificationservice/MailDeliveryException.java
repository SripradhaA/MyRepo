/**
 * 
 */
package com.EMD.LSDB.common.framework.notificationservice;

/**
 * @author ps57222
 *
 */
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.EMDException;

//class level javadocs
/**
 * <p><Description></p>
 * 
 * @author na25828
 * @version 1.0
 */

/**
 * This is a custom application exception associated with Mail Service.
 */
public class MailDeliveryException extends EMDException {
	
	/**
	 * When an exception occurs, and if a new exception is created wrapping up the 
	 * exception caught, the wrapped exception is held by this attribute.
	 */
	private Exception m_exception;
	
	private ErrorInfo m_errorInfo;
	
	// New Class varicbles added by JY24321
	private Integer activityID;
	
	private int noOfAttempts;
	
	/**
	 * Default Constructor
	 */
	public MailDeliveryException() {
		
	}
	
	/**
	 * Constructs a MailDeliveryException object with the given error information.
	 * @param p_errorInfo
	 */
	public MailDeliveryException(ErrorInfo p_errorInfo) {
		setErroInfo(p_errorInfo);
	}
	
	/**
	 * Constructs a MailDeliveryException object with the given exception and error 
	 * information.
	 * @param p_exception
	 * @param p_errorInfo
	 */
	public MailDeliveryException(Exception p_exception, ErrorInfo p_errorInfo) {
		setErroInfo(p_errorInfo);
		setException(p_exception);
	}
	
	// Code added by JY24321
	/**
	 * Constructs a MailDeliveryException object with the given exception and other 
	 * information.
	 * @param p_exception
	 * @param activityId
	 * @param noofattempts
	 */
	public MailDeliveryException(Exception p_exception, Integer activityId,
			int noofattempts) {
		setException(p_exception);
		this.activityID = activityId;
		this.noOfAttempts = noofattempts;
	}
	
	/**
	 * @return Integer
	 */
	public Integer getActivityID() {
		return this.activityID;
	}
	
	/**
	 * @return int
	 */
	public int getNoOfAttempts() {
		return this.noOfAttempts;
	}
	// End of Code added by JY24321	
}
