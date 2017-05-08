/*
 * Created on Jun 5, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.common.logger;

/**
 * @author mm57219
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

//Java import statements
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.resourceloader.EMDEnvLoader;

//import statements

/**
 * This is a util class for logging Error Messages or Error Information.
 */
public class LogUtil {
	
	/**
	 * Stores the custom description for a generic exception.
	 */
	private static String exceptionDescription = "";
	
	/**
	 * This holds the logger object
	 */
	
	/**
	 *  Holds the Static instance of m_EMDException object
	 */
	private static EMDException m_EMDException;
	
	private static LoggerFactory m_loggerFactory = LoggerFactory
	.getLoggerFactory();
	
	private static EMDLogger m_EMDLogger = m_loggerFactory.createLogger();
	
	//EMDLogger m_EMDLogger = m_loggerFactory.createLogger();
	//	Added for disabling and enabling debug message
	static EMDEnvLoader lEMDEnvLoader = EMDEnvLoader.getInstance();
	private static String logConsole =  (String)lEMDEnvLoader.getEnvEntryValue("SUPPRESSDEBUG");
	
	/**
	 * Default Constructor without Parameters
	 */
	public LogUtil() {
		
	}
	
	/**
	 * This is a static method that is used to log the Error message with exceptions
	 * @param p_exception
	 */
	/**
	 *  Static method takes Throwable object as input to log Error message 
	 */
	public static void logError(Throwable p_exception) {
		
		if (p_exception != null) {
			if (p_exception instanceof EMDException) {
				m_EMDException = (EMDException) p_exception;
				
				exceptionDescription = m_EMDException.getDescription();
				
				m_EMDLogger.logError(exceptionDescription);
			}//end of if
			else {
				m_EMDLogger.logError(p_exception.getMessage());
			}
		} //end of if 
	}
	
	/**
	 * This is a static method that is used to log the Error message with Error 
	 * Informations.
	 * @param p_errorInfo
	 */
	/**
	 * Static Method takes ErrorInfo object as Input to log Error message
	 */
	public static void logError(ErrorInfo p_errorInfo) {
		LogUtil.logMessage("Inside Logger class:....");
		if (p_errorInfo != null) {
			m_EMDLogger.logError(p_errorInfo.getMessage());
		}
		LogUtil.logMessage("Leaves Logger class:....");
	}
	
	/**
	 * This is a static method that is used to log the Error with Exception and  Error 
	 * Informations.
	 * @param p_exception
	 * @param p_errorInfo
	 */
	/**
	 * Static Method takes Throwable and ErroInfo Objects as input is used to log the Error
	 */
	public static void logError(Throwable p_exception, ErrorInfo p_errorInfo) {
		/**
		 * Appends Messages of EMDException and ErrorInfo
		 */
		StringBuffer addStr = new StringBuffer();
		
		if (p_exception != null) {
			if (p_exception instanceof EMDException) {
				m_EMDException = (EMDException) p_exception;
				
				exceptionDescription = m_EMDException.getDescription();
				
				addStr.append(exceptionDescription);
			} //end of  if
			else {
				exceptionDescription = "Genereal Exception"
					+ p_exception.getMessage();
				addStr.append(exceptionDescription);
			}// end of else
			
		}//end of if
		
		if (p_errorInfo != null) {
			exceptionDescription = "Business Exception"
				+ p_errorInfo.getMessage();
			
			addStr.append(exceptionDescription);
		}//end of if 
		
		m_EMDLogger.logError(addStr.toString());
	}//end of if
	
	/**
	 * This is a static method that is used to log the error messages
	 * @param p_message
	 */
	/**
	 * Static method takes String as input is used to log the error messages 
	 */
	public static void logMessage(Object p_message) {
		//Added for disabling and enabling debug message
		if("true".equalsIgnoreCase(logConsole)){
			m_EMDLogger.logDebug("" + p_message);
			m_EMDLogger.logInfo("" + p_message);
		}
	}
	
	/**
	 * This is a static method that is used to log the error messages according to the 
	 * specified Log Level.
	 * @param p_message
	 * @param p_logLevel
	 */
	/**
	 * Static method takes String adn LogLevel Objects as input is used to log the error messages 
	 */
	public static void logMessage(String p_message, LogLevel p_logLevel) {

		if (p_logLevel == LogLevel.INFO) {

			if("true".equalsIgnoreCase(logConsole)){
				m_EMDLogger.logInfo(p_message);
			}
		}
		
		else if (p_logLevel == LogLevel.DEBUG) {
			if("true".equalsIgnoreCase(logConsole)){
				m_EMDLogger.logDebug(p_message);
			}
		}
		
		else if (p_logLevel == LogLevel.WARNING) {
			m_EMDLogger.logWarn(p_message);
		}
		
		else if (p_logLevel == LogLevel.ERROR) {
			m_EMDLogger.logError(p_message);
		}
		
		else if (p_logLevel == LogLevel.FATAL) {
			m_EMDLogger.logFatal(p_message);
		}
	}
}
