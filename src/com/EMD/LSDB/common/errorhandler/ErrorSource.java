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
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

//import statements
//class level javadocs
/**
 * <p>
 * This holds the source of error from where the error has occured
 * </p>
 * 
 * @author mk25942
 * @version 1.0
 */

/**
 * This holds the source of error (from where the error has occured).
 */

public class ErrorSource {
	
	/**
	 * Holds the client name
	 */
	private String m_clientName;
	
	/**
	 * Holds the server name
	 */
	private String m_serverName;
	
	/**
	 * Holds the user credentail informations
	 */
	// private UserCredentialsTO m_userCredential;
	/**
	 * Default constructor with no parameter
	 */
	public ErrorSource() {
	}
	
	/**
	 * This method is used to get the ClientName
	 */
	public String getClientName() {
		return this.m_clientName;
	}
	
	/**
	 * This method is used to get the ServerName where the applicaiton runs
	 */
	public String getServerName() {
		return this.m_serverName;
	}
	
	/**
	 * This method is used to get the UserCredential informations
	 */
	/*
	 * public UserCredentialsTO getUserCredentials() { return
	 * this.m_userCredential; }
	 */
	/**
	 * This method is used to set the ClientName
	 */
	public void setClientName(String m_clientName) {
		this.m_clientName = m_clientName;
	}
	
	/**
	 * This method is used to set the ServerName where the applicaiton runs
	 */
	public void setServerName(String m_serverName) {
		this.m_serverName = m_serverName;
	}
	
	/**
	 * This method is used to set UserCredential informations
	 */
	/*
	 * public void setUserCredentials(UserCredentialsTO m_userCredential) {
	 * this.m_userCredential=m_userCredential; }
	 */
	
}
