/*
 * Created on Jun 7, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.common.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.logger.LogLevel;
import com.EMD.LSDB.common.logger.LogUtil;

/**
 * @author mm57219
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DataAccessException extends EMDException {
	
	/**
	 * Default Constructor
	 */
	public DataAccessException() {
		
	}
	
	/**
	 * Overloaded Constructor:
	 * Sets the error information (to the attribute)
	 * @param p_errorInfo
	 */
	public DataAccessException(ErrorInfo p_errorInfo) {
		setErroInfo(p_errorInfo);
	}
	
	/**
	 * Overloaded Constructor:
	 * Sets the exception and error information (to the attributes)
	 * @param p_exception
	 * @param p_errorInfo
	 */
	public DataAccessException(Exception p_exception, ErrorInfo p_errorInfo) {
		setErroInfo(p_errorInfo);
		setException(p_exception);
		logMessage(p_exception);
	}
	
	/**
	 * Overloaded Constructor:
	 * Sets the exception and error information (to the attributes)
	 * @param p_exception
	 */
	public DataAccessException(Exception p_exception) {
		setException(p_exception);
		logMessage(p_exception);
	}
	
	/**
	 * Private method:
	 * Log message
	 * @param p_exception
	 */
	private void logMessage(Exception p_exception) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		p_exception.printStackTrace(pw);
		LogUtil.logMessage(sw.toString(), LogLevel.ERROR);
		try {
			sw.close();
			pw.close();
		} catch (IOException p_IOException) {
			LogUtil.logMessage("IOException in ErroUtil - logMessage() : "
					+ p_IOException);
		}
	}
}
