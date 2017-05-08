/**
 * 
 */
package com.EMD.LSDB.common.framework.notificationservice;

/**
 * @author ps57222
 *
 */
/*
 EmailTO.java : Class for notifying transfer object and  used to hold the email notification details
 
 Copy right version 1.0
 
 */

import java.io.Serializable;
import java.util.Collection;

/**
 * The EmailTO is a notification transfer object used to hold the email 
 * notification details.
 */
public class EmailTO implements Serializable {
	
	/**
	 * holds the 'from' user id.
	 */
	private String m_fromUserId;
	
	/**
	 * This attribute is used to hold one or many userids to whom the email needs to be 
	 * sent. This is over and above the pre-defined groups that will receive the same.
	 */
	private Collection m_toUserIds;
	
	/**
	 * This attribute is used to hold one or many email ids to whom the email needs to be 
	 * sent. This is over and above the pre-defined groups that will receive the same.
	 */
	private Collection m_toEMailIds;
	
	/**
	 * This attribute is used to hold one or many group Ids to whom the email needs to be 
	 * sent. This is over and above the pre-defined groups that will receive the same.
	 */
	private Collection m_toGroupIds;
	
	/**
	 * marked a cc. This is over and above the pre-defined groups that will receive the 
	 * same.
	 */
	private Collection m_carbonCopy;
	
	/**
	 * marked a bcc. This is over and above the pre-defined groups that will receive 
	 * the same.
	 */
	private Collection m_blindCarbonCopy;
	
	/**
	 * This attribute is used to store the subject of the mail
	 */
	private String m_subject;
	
	/**
	 * This attribute is used to store the message of the mail
	 */
	private String m_message;
	
	/**
	 * This attribute is used to store the attachment of the mail
	 */
	private String m_attachment;
	
	/**
	 * This attribute is used to store the delivery attempt number.
	 */
	private int m_deliveryAttempt;
	
	/**
	 * This attribute is used to store the mail status of the mail
	 */
	private String m_mailStatus;
	
	/**
	 * Every transaction in LFO will be given an id called Activity id. This variable 
	 * will hold this activity id.
	 */
	private int m_activityID;
	
	/**
	 * Default Constructor
	 */
	public EmailTO() {
		
	}
	
	/**
	 * This method is used to get the From Address
	 * @return String
	 */
	public String getFrom() {
		return m_fromUserId;
	}
	
	/**
	 * This method is used to set the From Address
	 * @param p_fromUserId
	 * @return Void
	 */
	public void setFrom(String p_fromUserId) {
		this.m_fromUserId = p_fromUserId;
	}
	
	/**
	 * This method is used to get the To address id
	 * @return Collection
	 */
	public Collection getTo() {
		return m_toUserIds;
	}
	
	/**
	 * This method is used to set the To address id
	 * @param p_toUserIds
	 * @return Void
	 */
	public void setTo(Collection p_toUserIds) {
		this.m_toUserIds = p_toUserIds;
	}
	
	/**
	 * This method is used to get one or many email ids
	 * @return Collection
	 */
	public Collection getToEMailIds() {
		return m_toEMailIds;
	}
	
	/**
	 * This method is used to set one or many email ids
	 * @param p_toUserIds
	 * @return Void
	 */
	public void setToEMailIds(Collection p_toEMailIds) {
		this.m_toEMailIds = p_toEMailIds;
	}
	
	/**
	 * This method is used to get the group of mail address 
	 * @return Collection
	 */
	public Collection getToGroupIds() {
		return m_toGroupIds;
	}
	
	/**
	 * This method is used to set the group of mail address id
	 * @param p_toUserIds
	 * @return Void
	 */
	public void setToGroupIds(Collection p_toGroupIds) {
		this.m_toGroupIds = p_toGroupIds;
	}
	
	/**
	 * This method is used to get the carbon address
	 * @return Collection
	 */
	public Collection getCarbonCopy() {
		return m_carbonCopy;
	}
	
	/**
	 * This method is used to set the carbonfrom address id
	 * @param p_carbonCopy
	 * @return Void
	 */
	public void setCarbonCopy(Collection p_carbonCopy) {
		this.m_carbonCopy = p_carbonCopy;
	}
	
	/**
	 * This method is used to get the blind carbon copy address id
	 * @return Collection
	 */
	public Collection getBlindCarbonCopy() {
		return m_blindCarbonCopy;
	}
	
	/**
	 * This method is used to set the blind carbon copy address id
	 * @param p_blindCarbon
	 * @return Void
	 */
	public void setBlindcarbonCopy(Collection p_blindCarbon) {
		this.m_blindCarbonCopy = p_blindCarbon;
	}
	
	/**
	 * This method is used to get the subject
	 * @return String
	 */
	public String getSubject() {
		return m_subject;
	}
	
	/**
	 * This method is used to set the subject
	 * @param p_subject
	 * @return Void
	 */
	public void setSubject(String p_subject) {
		this.m_subject = p_subject;
	}
	
	/**
	 * This method is used to get the message
	 * @return String
	 */
	public String getMessage() {
		return m_message;
	}
	
	/**
	 * This method is used to set the message
	 * @param p_message
	 * @return Void
	 */
	public void setMessage(String p_message) {
		this.m_message = p_message;
	}
	
	/**
	 * This method is used to get attachment
	 * @return String
	 */
	public String getAttachment() {
		return m_attachment;
	}
	
	/**
	 * This method is used to set attachment
	 * @param p_attachment
	 * @return Void
	 */
	public void setAttachment(String p_attachment) {
		this.m_attachment = p_attachment;
	}
	
	/**
	 * This method is used to get the delivery attempt number
	 * @return int
	 */
	public int getDeliveryAttempt() {
		return m_deliveryAttempt;
	}
	
	/**
	 * This method is used to set the delivery attempt number
	 * @param p_deliveryAttempt
	 * @return Void
	 */
	public void setDeliveryAttempt(int p_deliveryAttempt) {
		this.m_deliveryAttempt = p_deliveryAttempt;
	}
	
	/**
	 * This method is used to get the Mail Status
	 * @return String
	 */
	public String getMailStatus() {
		return m_mailStatus;
	}
	
	/**
	 * This method is used to set the Mail Status
	 * @param p_Status
	 * @return Void
	 */
	public void setMailStatus(String p_Status) {
		this.m_mailStatus = p_Status;
	}
	
	/**
	 * This method is used to get the Activity id
	 * @return int
	 */
	public int getActivityID() {
		return m_activityID;
	}
	
	/**
	 * This method is used to set the Activity id
	 * @param p_activityID
	 * @return Void
	 */
	public void setActivityID(int p_activityID) {
		this.m_activityID = p_activityID;
	}
	
}
