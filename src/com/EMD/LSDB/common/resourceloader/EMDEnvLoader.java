/*
 * Created on Jun 5, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.common.resourceloader;

/**
 * @author mm57219
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

//package name
//import statements
import java.util.Enumeration;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;

import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;

//class level javadocs
/**
 * <p> defined in Application Server. EMDResourceLoader will be called once to load all 
 * the environment variables and store it in a Hashtable</p>
 * 
 * @author mk25942
 * @version 1.0
 */

/**
 * defined in Application Server. EMDResourceLoader will be called once to load all 
 * the environment variables and store it in a Hashtable.
 */
public class EMDEnvLoader {
	
	/**
	 * Holds the environment entries defined in the Application Server
	 */
	private static Hashtable m_envEntries;
	
	/**
	 * Holds the single instance of this class
	 */
	private static EMDEnvLoader m_loader;
	
	/**
	 * A private constructor to avoid creating more than one instance of this class
	 */
	private EMDEnvLoader() {
		try {
			
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * This methods creates a single instance of this class and returns an EMDEnvLoader 
	 * object.
	 
	 */
	public static EMDEnvLoader getInstance() {
		synchronized (EMDResourceLoaderConstants.LOCK) {
			if (m_loader == null) {
				m_loader = new EMDEnvLoader();
			}
		}
		return m_loader;
	}
	
	/**
	 * Server. The Environment Entries loaded will be updated into a Hashtable so that 
	 * environment entries are available to use in any of the Modules of EMD.
	 */
	public void loadEnvEntries() throws ApplicationException {
		try {
			m_envEntries = new Hashtable();
			Context context = new InitialContext();
			Context envContext = (Context) context
			.lookup(EMDResourceLoaderConstants.java_env);
			NamingEnumeration customNamingEnum = context
			.list(EMDResourceLoaderConstants.java_env);
			while (customNamingEnum.hasMore()) {
				NameClassPair keyPair = (NameClassPair) customNamingEnum.next();
				String key = keyPair.getName();
				Object value = envContext.lookup(key);
				m_envEntries.put(key, value);
			}
		} catch (Exception e) {
			ErrorInfo errorInfoObj = new ErrorInfo();
			errorInfoObj
			.setMessage("Error occured, while loading Enviromental entries,Please contact System Administrator");
			throw new ApplicationException(e, errorInfoObj);
			
		}
	}
	
	/**
	 * This method will return all the Environment entry value for a given Environment 
	 * Entry name defined in the Application Server
	 * @param Object p_key
	 * @return Object
	 */
	public Object getEnvEntryValue(Object p_key) {
		if (m_envEntries != null && m_envEntries.get(p_key) != null) {
			return (String) m_envEntries.get(p_key.toString());
		} else {
			return null;
		}
	}
	
	/**
	 * This method will return all the Environment entry Names defined in the 
	 * Application Server
	 * @return Enumeration
	 */
	public Enumeration getEnvEntryNames() {
		Enumeration customEnum = m_envEntries.elements();
		return customEnum;
	}
	
	/**
	 * This method will add new Environment entry Variables to Application Server. The 
	 * input parameters are Environment Entry Name and its Value
	 * @param Object p_key
	 * @param Object p_value
	 */
	public void addEnvEntry(Object p_key, Object p_value) {
		if (m_envEntries == null) {
			m_envEntries.put(p_key, p_value);
		}
	}
}
