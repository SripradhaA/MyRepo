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
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
//Import statements
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.EMD.LSDB.common.resourceloader.EMDEnvLoader;

//class level javadocs

/**
 * This is a Factory class to create Logger.
 */
public class LoggerFactory {
	
	/**
	 * Holds the static instance of the LoggerFactory
	 */
	private static LoggerFactory m_loggerFactory;
	
	private boolean m_isConfigured = false;
	
	public EMDLogger m_lfoLogger;
	
	/**
	 * Private Constructor.
	 */
	/**
	 * Private Constructor without Parameters
	 */
	private LoggerFactory() {
		
	}
	
	/**
	 * Gets the instance of the LoggerFactory
	 * 
	 */
	/*
	 * This method returns LoggerFactory Object
	 */
	public static LoggerFactory getLoggerFactory() {
		synchronized ("LOCK") {
			if (m_loggerFactory == null) {
				m_loggerFactory = new LoggerFactory();
			}
			return m_loggerFactory;
		}
	}
	
	/**
	 * Creates the Logger (Weblogic / Log4j / Custom / any Third Party provided
	 * logger).
	 * 
	 * Creates the LFOLogger using Logger.
	 * 
	 * @return LFOLogger
	 */
	
	/*
	 * This method returns LFOLogger Object
	 */
	/*
	 * This method returns LFOLogger Object
	 */
	public EMDLogger createLogger() {
		if (m_lfoLogger == null) {
			// configureLogger();
			m_lfoLogger = new EMDLogger(Logger.getLogger("EMD"));
		}
		return m_lfoLogger;
	}
	
	/*
	 * This method returns LFOLogger Object
	 */
	private void configureLogger() {
		if (!m_isConfigured) {
			try {
				EMDEnvLoader lfoEnvLoader = EMDEnvLoader.getInstance();
				String urlString = (String) lfoEnvLoader
				.getEnvEntryValue("LOG4J_URL");
				PropertyConfigurator.configure(urlString);
				m_isConfigured = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}