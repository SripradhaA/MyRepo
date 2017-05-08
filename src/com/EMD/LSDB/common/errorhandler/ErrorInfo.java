/*
 * Created on Jun 5, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.common.errorhandler;

import java.util.Date;

/**
 * @author mm57219
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * This holds all the error details like location, source, type, level etc.
 */
public class ErrorInfo {
	
	private String messageID;
	
	/**
	 * Holds the error message
	 */
	private String m_message;
	
	/**
	 * Holds the time stamp in which the error has occured
	 */
	private Date m_timeStamp;
	
	/**
	 * Holds the source of the error
	 */
	private ErrorSource m_source;
	
	/**
	 * Holds the error location
	 */
	private ErrorLocation m_location;
	
	/**
	 * Holds the type of error
	 */
	private ErrorType m_errorType;
	
	/**
	 * Holds the error level
	 */
	private ErrorLevel m_errorLevel;
	
	/**
	 * Holds the error ID.
	 */
	private String m_errorID;
	
	/**
	 * Holds the attribute name.
	 */
	private String m_attributeName;
	
	/**
	 * Default constructor with parameters
	 */
	public ErrorInfo(String p_errorID, String p_methodName, String p_className,
			String p_componentName) {
		
		ErrorLocation errorLocation = new ErrorLocation();
		errorLocation.setComponentName(p_componentName);
		errorLocation.setClassName(p_className);
		errorLocation.setMethodName(p_componentName);
		errorLocation.setApplicationName("EMD");
		this.setLocation(errorLocation);
		this.setErrorId(p_errorID);
	}
	
	/**
	 * Default constructor with no parameters
	 */
	public ErrorInfo() {
		
	}
	
	/**
	 * This method is used to get Message
	 */
	public String getMessage() {
		return this.m_message;
	}
	
	/**
	 * This method is used to get the TimeStamp during which error occured
	 */
	public Date getTimeStamp() {
		return this.m_timeStamp;
	}
	
	/**
	 * This  method is used to get the source information of the error
	 */
	public ErrorSource getSource() {
		return this.m_source;
	}
	
	/**
	 * This method is used to get location information of error
	 */
	public ErrorLocation getLocation() {
		return this.m_location;
	}
	
	/**
	 * This method is used to get type of error
	 */
	public ErrorType getErrorType() {
		return this.m_errorType;
	}
	
	/**
	 * This method is used to get the ErrorLevel
	 */
	public ErrorLevel getErrorLevel() {
		return this.m_errorLevel;
	}
	
	/**
	 * This method is used to get Error ID
	 */
	public String getErrorId() {
		return this.m_errorID;
	}
	
	/**
	 * This method is used to get attribute name
	 */
	public String getAttributeName() {
		return this.m_attributeName;
	}
	
	/**
	 * This method is used to set Message
	 */
	public void setMessage(String m_message) {
		this.m_message = m_message;
	}
	
	/**
	 * this method is used to set TimeStamp during when error occured
	 */
	public void setTimeStamp(Date m_timeStamp) {
		this.m_timeStamp = m_timeStamp;
	}
	
	/**
	 * This method is used to set the source of error
	 */
	public void setSource(ErrorSource m_source) {
		this.m_source = m_source;
	}
	
	/**
	 * This method is used to set location information of error
	 */
	public void setLocation(ErrorLocation m_location) {
		this.m_location = m_location;
	}
	
	/**
	 * This method is used to set type of error
	 */
	public void setErrorType(ErrorType m_errorType) {
		this.m_errorType = m_errorType;
	}
	
	/**
	 * This method is used to set the ErrorLevel
	 */
	public void setErrorLevel(ErrorLevel m_errorLevel) {
		this.m_errorLevel = m_errorLevel;
	}
	
	/**
	 * This method is used to set Error ID
	 */
	public void setErrorId(String m_errorID) {
		this.m_errorID = m_errorID;
	}
	
	/**
	 * This method is used to set attribute name
	 */
	public void setAttributeName(String p_attributeName) {
		this.m_attributeName = p_attributeName;
	}
	
	/**
	 * @return Returns the messageID.
	 */
	public String getMessageID() {
		return messageID;
	}
	
	/**
	 * @param messageID The messageID to set.
	 */
	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}
	
}
