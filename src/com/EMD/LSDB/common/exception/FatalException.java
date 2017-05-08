/*
 * Created on Jun 7, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.common.exception;

import com.EMD.LSDB.common.errorhandler.ErrorInfo;

/**
 * @author mm57219
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
//Source file: d:\\log\\com\\gm\\lfo\\common\\FatalException.java

//import statements

/**
 * This class is used when a fatal error occurrs while the applicaiton runs.
 * For all the fatal errors 'FatalException' will be thrown.
 */
public class FatalException extends RuntimeException {
	private ErrorInfo m_errorInfo;
	
	private Exception m_exception;
	
	/**
	 * Default Constructor
	 */
	public FatalException() {
		
	}
	
	/**
	 * Default Constructor
	 */
	public FatalException(Exception p_exception) {
		this.m_exception = p_exception;
	}
	
	/**
	 * Overloaded Constructor:
	 * Sets the error information (to the attribute)
	 * @param p_errorInfo
	 */
	public FatalException(ErrorInfo p_errorInfo) {
		this.m_errorInfo = p_errorInfo;
	}
	
	/**
	 * Overloaded Constructor:
	 * Sets the exception and error information (to the attributes)
	 * @param p_exception
	 * @param p_errorInfo
	 */
	public FatalException(Exception p_exception, ErrorInfo p_errorInfo) {
		this.m_errorInfo = p_errorInfo;
		this.m_exception = p_exception;
	}
	
	public void setException(Exception p_exception) {
		this.m_exception = p_exception;
	}
	
	public Exception getException() {
		return this.m_exception;
	}
	
	public void setErroInfo(ErrorInfo p_errorInfo) {
		this.m_errorInfo = p_errorInfo;
	}
	
	public ErrorInfo getErrorInfo() {
		return this.m_errorInfo;
	}
}
