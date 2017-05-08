/**
 * 
 */
package com.EMD.LSDB.form.admn;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

/**
 * @author SI50968
 *
 */
public class ResetPasswordForm extends EMDForm {
	
	/**
	 * 
	 */
	/*private static final long serialVersionUID = -9069207642044937531L;*/
	
	private String userId;
	
	private String firstName;
	
	private String lastName;
	
	private String emailId;
	
	private int userSeqNo;
	
	private ArrayList userDetails;
	
	/**
	 * @return Returns the firstName.
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * @param firstName The firstName to set.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * @return Returns the lastName.
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * @param lastName The lastName to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @return Returns the emailId.
	 */
	public String getEmailId() {
		return emailId;
	}
	
	/**
	 * @param emailId The emailId to set.
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	/**
	 * @return Returns the userId.
	 */
	/*public String getUserId() {
	 return userId;
	 }
	 
	 /**
	  * @param userId The userId to set.
	  */
	/*public void setUserId(String userId) {
	 this.userId = userId;
	 }
	 
	 
	 /**
	  * @return Returns the userDetails.
	  */
	public ArrayList getUserDetails() {
		return userDetails;
	}
	
	/**
	 * @param userDetails The userDetails to set.
	 */
	public void setUserDetails(ArrayList userDetails) {
		this.userDetails = userDetails;
	}
	
	/**
	 * @return Returns the userSeqNo.
	 */
	public int getUserSeqNo() {
		return userSeqNo;
	}
	
	/**
	 * @param userSeqNo The userSeqNo to set.
	 */
	public void setUserSeqNo(int userSeqNo) {
		this.userSeqNo = userSeqNo;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * @return Returns the userid.
	 */
	
}
