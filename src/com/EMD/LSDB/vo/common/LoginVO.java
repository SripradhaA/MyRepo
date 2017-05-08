package com.EMD.LSDB.vo.common;

public class LoginVO extends EMDVO {
	
	private String password;
	private int intScreenId=0;
	
//	Added for CR-126
	private String emailFlag;
	
	//Added for CR-134
	private String resetFlag;
	
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
	 * @return Returns the intScreenId.
	 */
	public int getIntScreenId() {
		return intScreenId;
	}

	/**
	 * @param intScreenId The intScreenId to set.
	 */
	public void setIntScreenId(int intScreenId) {
		this.intScreenId = intScreenId;
	}

	public String getEmailFlag() {
		return emailFlag;
	}

	public void setEmailFlag(String emailFlag) {
		this.emailFlag = emailFlag;
	}

	public String getResetFlag() {
		return resetFlag;
	}

	public void setResetFlag(String resetFlag) {
		this.resetFlag = resetFlag;
	}
	
	
	
}
