/*
 * Created on Jun 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.common.exception;

import com.EMD.LSDB.common.errorhandler.ErrorInfo;

/**
 * @author MM57219
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BusinessException extends EMDException {
	/**
	 * Default Constructor
	 */
	
	/**
	 * @roseuid 41DA841E037A
	 */
	public BusinessException() {
		
	}
	
	/**
	 * Overloaded Constructor:
	 * Sets the error information (to the attribute)
	 * @param p_errorInfo
	 */
	public BusinessException(ErrorInfo p_errorInfo) {
		setErroInfo(p_errorInfo);
	}
	
	/**
	 * Overloaded Constructor:
	 * Sets the exception and error information (to the attributes)
	 * @param p_exception
	 * @param p_errorInfo
	 */
	public BusinessException(Exception p_exception, ErrorInfo p_errorInfo) {
		setErroInfo(p_errorInfo);
		setException(p_exception);
	}
	
}
