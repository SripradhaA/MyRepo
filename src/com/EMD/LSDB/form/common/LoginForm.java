package com.EMD.LSDB.form.common;


public class LoginForm extends EMDForm {
	
	String actiontype;
	
	String password;
	
	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return Returns the actiontype.
	 */
	public String getActiontype() {
		return actiontype;
	}
	
	/**
	 * @param actiontype The actiontype to set.
	 */
	public void setActiontype(String actiontype) {
		this.actiontype = actiontype;
	}
	
}
