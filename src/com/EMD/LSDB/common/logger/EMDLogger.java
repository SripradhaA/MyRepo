/*
 * Created on Jun 5, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.common.logger;

import org.apache.log4j.Logger;

//import statement 

/**
 * This is the Logger for EMD application.  It acts as a wrapper over the third 
 * party logger (Weblogic / Log4j / Custom).
 */

public class EMDLogger {
	
	/**
	 * Third party logger (Weblogic or Log4J).
	 */
	private Logger m_logger;
	
	/**
	 *  Constructor without parameters
	 */
	public EMDLogger() {
		
	}
	
	/**
	 * Private Constructor
	 * Construct a EMDLogger with third party Logger.
	 * @param p_logger
	 */
	/**
	 * Constructor which takes logger as input and gives EMDLogger as output
	 */
	public EMDLogger(Logger p_logger) {
		m_logger = p_logger;
	}
	
	/**
	 * This is used for logging - informations.
	 * @param p_info
	 */
	/**
	 *This method takes object as input and used for logging Informations 
	 */
	public void logInfo(Object p_info) {
		m_logger.info(p_info);
	}
	
	/**
	 * This is used for logging - debug messages.
	 * @param p_debug
	 */
	/**
	 *This method takes object as input and used for logging Debug messages 
	 */
	public void logDebug(Object p_debug) {
		m_logger.debug(p_debug);
	}
	
	/**
	 * This is used for logging - Warning messages.
	 * @param p_warn
	 */
	/**
	 *This method takes object as input and used for logging Warning messages 
	 */
	public void logWarn(Object p_warn) {
		
		m_logger.warn(p_warn);
	}
	
	/**
	 * This is used for logging - Errors messages.
	 * @param p_error
	 */
	/**
	 *This method takes object as input and used for logging Error messages 
	 */
	public void logError(Object p_error) {
		m_logger.error(p_error);
	}
	
	/**
	 * This is used for logging - Fatal Errors.
	 * @param p_fatal
	 */
	/**
	 *This method takes object as input and used for logging Fatal Errors
	 */
	public void logFatal(Object p_fatal) {
		m_logger.fatal(p_fatal);
	}
}
