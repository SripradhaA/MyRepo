/**
 * 
 */
package com.EMD.LSDB.common.framework.notificationservice;

/**
 * @author ps57222
 * 
 */
/*
 * NotificationException.java : Custom application exception associated with
 * Notification Service.
 * 
 * Copy right version 1.0
 * 
 */

/*
 * ---------------------------------------------------------------------- Major
 * Modification History
 * ---------------------------------------------------------------------- Date
 * Programmer Comments
 * ----------------------------------------------------------------------
 * 
 * 
 * ----------------------------------------------------------------------
 */

//package name

import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.EMDException;

//import statements

//class level javadocs
/**
 * <p>
 * <Description>
 * </p>
 * 
 * @author na25828
 * @version 1.0
 */

/**
 * This is a custom application exception associated with Notification Service.
 */
public class NotificationException extends EMDException {
	
	/**
	 * When an exception occurs, and if a new exception is created wrapping up
	 * the exception caught, the wrapped exception is held by this attribute.
	 */
	private Exception m_exception;
	
	private ErrorInfo m_errorInfo;
	
	/**
	 * Default Constructor
	 */
	public NotificationException() {
		
	}
	
	/**
	 * Constructs a NotificationException object with the given error
	 * information.
	 * 
	 * @param p_errorInfo
	 */
	public NotificationException(ErrorInfo p_errorInfo) {
		setErroInfo(p_errorInfo);
	}
	
	/**
	 * Constructs a NotificationException object with the given exception and
	 * error information.
	 * 
	 * @param p_exception
	 * @param p_errorInfo
	 */
	public NotificationException(Exception p_exception, ErrorInfo p_errorInfo) {
		
		setException(p_exception);
		setErroInfo(p_errorInfo);
	}
}
