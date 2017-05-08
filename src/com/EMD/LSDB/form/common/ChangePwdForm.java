/* AK49339
 * Created on Aug 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.form.common;

public class ChangePwdForm extends EMDForm {
	
	private String newPassword;
	
	private String confirmPassword;
	
	/**
	 * @return Returns the confirmPassword.
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	/**
	 * @param confirmPassword
	 *            The confirmPassword to set.
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	/**
	 * @return Returns the newPassword.
	 */
	public String getNewPassword() {
		return newPassword;
	}
	
	/**
	 * @param newPassword
	 *            The newPassword to set.
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}
