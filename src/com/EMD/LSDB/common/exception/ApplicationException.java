/*
 * Created on Jun 5, 2007
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

public class ApplicationException extends EMDException {
	
	/**
	 * Default Constructor
	 */
	
	/**
	 * @roseuid 41DA841E037A
	 */
	public ApplicationException() {
		
	}
	
	/**
	 * Overloaded Constructor:
	 * Sets the error information (to the attribute)
	 * @param p_errorInfo
	 */
	public ApplicationException(ErrorInfo p_errorInfo) {
		setErroInfo(p_errorInfo);
	}
	
	/**
	 * Overloaded Constructor:
	 * Sets the exception and error information (to the attributes)
	 * @param p_exception
	 * @param p_errorInfo
	 */
	public ApplicationException(Exception p_exception, ErrorInfo p_errorInfo) {
		setErroInfo(p_errorInfo);
		setException(p_exception);
	}
	
	/**
	 * Overloaded Constructor:
	 * Sets the exception and error information (to the attributes)
	 * @param p_exception
	 * @param p_errorInfo
	 */
	public ApplicationException(Exception p_exception) {
		setException(p_exception);
	}
	
}
