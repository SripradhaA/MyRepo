/*
 * Created on Jun 5, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.common.errorhandler;

/**
 * @author mm57219
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

//import statements

//class level javadocs
/**
 * <p>This holds the location details of the error</p>
 * 
 * @author mk25942
 * @version 1.0
 */

/**
 * This holds the location details of the error
 */
public class ErrorLocation {
	
	/**
	 * Holds the application name
	 */
	private String m_applicationName;
	
	/**
	 * Holds the component name
	 */
	private String m_componentName;
	
	/**
	 * Holds the name of the class where the error has occured
	 */
	private String m_className;
	
	/**
	 * Holds the method name where the error has occured
	 */
	private String m_methodName;
	
	/**
	 * Default constructor with no parameter
	 */
	public ErrorLocation() {
	}
	
	/**
	 * This method is used to get ApplicationName
	 */
	public String getApplicationName() {
		return this.m_applicationName;
	}
	
	/**
	 * This method is used to get the ComponentName
	 */
	public String getComponentName() {
		return this.m_componentName;
	}
	
	/**
	 * This method is used to get the ClassName
	 */
	public String getClassName() {
		return this.m_className;
	}
	
	/**
	 * This method is used to get the MethodName
	 */
	public String getMethodName() {
		return this.m_methodName;
	}
	
	/**
	 * This method is used to set ApplicationName
	 */
	public void setApplicationName(String m_applicationName) {
		this.m_applicationName = m_applicationName;
	}
	
	/**
	 * This method is used to set ComponentName
	 */
	public void setComponentName(String m_componentName) {
		this.m_componentName = m_componentName;
	}
	
	/**
	 * This method is used to set ClassName
	 */
	public void setClassName(String m_className) {
		this.m_className = m_className;
	}
	
	/**
	 * This method is used to set MethodName
	 */
	public void setMethodName(String m_methodName) {
		this.m_methodName = m_methodName;
	}
	
}
