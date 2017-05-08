/*
 * Created on Jun 5, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.common.exception;

/**
 * @author mm57219
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

//Java import statements
import java.sql.SQLException;

import com.EMD.LSDB.common.errorhandler.ErrorInfo;

//import statements

/**
 * The high level exception are categorized into ApplicationException and 
 * FatalException.  This holds the exception object and error information object.
 */
public class EMDException extends Exception {
	
	/**
	 * Stores the custom description for a generic exception.
	 */
	private String exceptionDescription = "";
	
	/**
	 * When an exception occurs, and if a new exception is created wrapping up the 
	 * exception caught, the wrapped exception is held by this attribute.
	 */
	private Exception m_exception;
	
	private ErrorInfo m_errorInfo;
	
	/**
	 * Default Constructor
	 */
	public EMDException() {
		
	}
	
	/**
	 * Overloaded Constructor:
	 * Sets the error information (to the attribute)
	 * @param p_errorInfo
	 */
	/**
	 * Constructor takes input ErrorInfo Object
	 */
	public EMDException(ErrorInfo p_errorInfo) {
		this.m_errorInfo = p_errorInfo;
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
	
	/**
	 * Overloaded Constructor:
	 * Sets the exception and error information (to the attributes)
	 * @param p_exception
	 * @param p_errorInfo
	 */
	/**
	 * Constructor takes Exception and ErrorInfo Object's as input and 
	 * sets to the attributes m_exception and m_erroInfo
	 */
	public EMDException(Exception p_exception, ErrorInfo p_errorInfo) {
		setErroInfo(p_errorInfo);
		setException(p_exception);
		
	}
	
	/**
	 * Overloaded Constructor:
	 * Sets the exception and error information (to the attributes)
	 * @param p_exception
	 */
	/**
	 * Constructor takes Exception Object as input and 
	 */
	public EMDException(Exception p_exception) {
		setException(p_exception);
	}
	
	public String getDescription() {
		if (m_exception != null) {
			EMDException EMDException = (EMDException) m_exception;
			
			if (m_exception instanceof SQLException) {
				exceptionDescription = "SQLException";
				boolean doPrint = false;
				boolean firstPrint = false;
				
				/**
				 * Appends the chain of  SQLException message
				 */
				StringBuffer sb = new StringBuffer();
				
				SQLException sqlException = (SQLException) m_exception;
				
				do {
					
					if (doPrint) {
						sb.append("[Start Chained SQLExceptions]\n");
					} //end of if
					
					firstPrint = doPrint;
					doPrint = true;
					
					sb.append(" DBSTATE[" + sqlException.getSQLState() + "]"
							+ " : " + sqlException.getMessage() + "\n");
				} while ((sqlException = sqlException.getNextException()) != null);
				
				sb.append((firstPrint) ? "[End Chained SQLExceptions]\n" : "");
				
				exceptionDescription += sb.toString();
			} //end of  if
			
			else {
				exceptionDescription = EMDException.getMessage();
			}//end of else
			
		}//end of if
		return exceptionDescription;
	}
	
}
