/**
 * 
 */
package com.EMD.LSDB.vo.common;

/**
 * @author PS57222
 * 
 */
public class EMDVO {
	
	private String userID;
	
	private String roleID;
	
	private int orderKey;
	
	private String dataLocationType;
	
	private String firstName;
	
	private String lastName;
	
	private String message;
	
	// Change for LSDB_CR-45
	private int requestID;
	
	private String reason;
	
	// Ends for LSDB_CR-45
	
	private String adminComments;
	
	private String requestDesc;
	
	//Added for LSDB_CR_71 for Server Side Component Validation
	private int messageID;
	
	
	//Added for CR-121
	private String orderNo;
	private String customerName;
		
	public int getMessageID() {
		return messageID;
	}

	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}

	public String getRequestDesc() {
		return requestDesc;
	}
	
	public void setRequestDesc(String requestDesc) {
		this.requestDesc = requestDesc;
	}
	
	public String getAdminComments() {
		return adminComments;
	}
	
	public void setAdminComments(String adminComments) {
		this.adminComments = adminComments;
	}
	
	/**
	 * @return Returns the reason.
	 */
	public String getReason() {
		return reason;
	}
	
	/**
	 * @param reason
	 *            The reason to set.
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	/**
	 * @return Returns the requestID.
	 */
	public int getRequestID() {
		return requestID;
	}
	
	/**
	 * @param requestID
	 *            The requestID to set.
	 */
	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}
	
	/**
	 * @return Returns the firstName.
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * @param firstName
	 *            The firstName to set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * @return Returns the firstName.
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * @param firstName
	 *            The firstName to set.
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
	 * @param lastName
	 *            The lastName to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @return Returns the dataLocationType.
	 */
	public String getDataLocationType() {
		return dataLocationType;
	}
	
	/**
	 * @param dataLocationType
	 *            The dataLocationType to set.
	 */
	public void setDataLocationType(String dataLocationType) {
		this.dataLocationType = dataLocationType;
	}
	
	/**
	 * @return Returns the orderKey.
	 */
	public int getOrderKey() {
		return orderKey;
	}
	
	/**
	 * @param orderKey
	 *            The orderKey to set.
	 */
	public void setOrderKey(int orderKey) {
		this.orderKey = orderKey;
	}
	
	/**
	 * @return Returns the roleID.
	 */
	public String getRoleID() {
		return roleID;
	}
	
	/**
	 * @param roleID
	 *            The roleID to set.
	 */
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	
	/**
	 * @return Returns the userID.
	 */
	public String getUserID() {
		return userID;
	}
	
	/**
	 * @param userID
	 *            The userID to set.
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
}