/**
 * 
 */
package com.EMD.LSDB.form.admn;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

/**
 * @author VV49326
 * 
 */
public class UserMaintForm extends EMDForm {
	
	private String userId;
	
	private String firstName;
	
	private String lastName;
	
	private String password;
	
	private String emailId;
	
	private String locationName;
	
	private String contactNo;
	
	private String roleid;
	
	private String hdnUserID;
	
	private String hdnFirstName;
	
	private String hdnLastName;
	
	private String hdnLocation;
	
	private String hdnEmail;
	
	private String hdnContactNo;
	
	private String hdnUserRole;
	
	private int userSeqNo;
	
	private ArrayList userDetails;
	
	private String userid;
	//Added for CR-112 for sorting
	private int orderbyFlag = 0;
	
	private String columnheader;

	//Added for CR-112 - Dynamic Role Loading
	private ArrayList userRolesList;
	//CR-112 Ends Here
	
	//Added for CR-113
	private String subject;
	
	private String body;
	
	private String roleName;
	//CR-113 Ends Here
	
	//Added for CR-124 Starts
	private int seqNo;
	
	private String fromEmailId;
	
	private String toEmailId;
	
	private String emailSubject;
	
	private String emailBody;
	
	private String updtUserId;
	
	private String updtDate;
	
	private String sentTime;
	
	private ArrayList emailDetails;
	
	private int hdnSeqNo;
	//Added for CR-124 ends
	
	//Added for CR-126
	private String hdnActionNoticeFlag;
	
	private String hdnInformationNoticeFlag;
	
	private String hdnCCNoticeFlag;
	
	private int emailPeriod;
	
	private String emailFlag;
	
	//Added for CR-128 starts here
	private ArrayList activityLog;
	
	public ArrayList getActivityLog() {
		return activityLog;
	}

	public void setActivityLog(ArrayList activityLog) {
		this.activityLog = activityLog;
	}
//	Added for CR-128 ends here
	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password
	 *            The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return Returns the userDetails.
	 */
	public ArrayList getUserDetails() {
		return userDetails;
	}
	
	/**
	 * @param userDetails
	 *            The userDetails to set.
	 */
	public void setUserDetails(ArrayList userDetails) {
		this.userDetails = userDetails;
	}
	
	/**
	 * @return Returns the contactNo.
	 */
	public String getContactNo() {
		return contactNo;
	}
	
	/**
	 * @return Returns the roleid.
	 */
	public String getRoleid() {
		return roleid;
	}
	
	/**
	 * @param roleid
	 *            The roleid to set.
	 */
	public void setRoleid(String roleid) {
		this.roleid = roleid;
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
	 * @return Returns the locationName.
	 */
	public String getLocationName() {
		return locationName;
	}
	
	/**
	 * @param locationName
	 *            The locationName to set.
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	/**
	 * @return Returns the emailId.
	 */
	public String getEmailId() {
		return emailId;
	}
	
	/**
	 * @param emailId
	 *            The emailId to set.
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	/**
	 * @return Returns the userId.
	 */
	public String getUserId() {
		return userId;
	}
	
	/**
	 * @param userId
	 *            The userId to set.
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * @return Returns the hdnContactNo.
	 */
	public String getHdnContactNo() {
		return hdnContactNo;
	}
	
	/**
	 * @param hdnContactNo
	 *            The hdnContactNo to set.
	 */
	public void setHdnContactNo(String hdnContactNo) {
		this.hdnContactNo = hdnContactNo;
	}
	
	/**
	 * @return Returns the hdnEmail.
	 */
	public String getHdnEmail() {
		return hdnEmail;
	}
	
	/**
	 * @param hdnEmail
	 *            The hdnEmail to set.
	 */
	public void setHdnEmail(String hdnEmail) {
		this.hdnEmail = hdnEmail;
	}
	
	/**
	 * @return Returns the hdnFirstName.
	 */
	public String getHdnFirstName() {
		return hdnFirstName;
	}
	
	/**
	 * @param hdnFirstName
	 *            The hdnFirstName to set.
	 */
	public void setHdnFirstName(String hdnFirstName) {
		this.hdnFirstName = hdnFirstName;
	}
	
	/**
	 * @return Returns the hdnLastName.
	 */
	public String getHdnLastName() {
		return hdnLastName;
	}
	
	/**
	 * @param hdnLastName
	 *            The hdnLastName to set.
	 */
	public void setHdnLastName(String hdnLastName) {
		this.hdnLastName = hdnLastName;
	}
	
	/**
	 * @return Returns the hdnLocation.
	 */
	public String getHdnLocation() {
		return hdnLocation;
	}
	
	/**
	 * @param hdnLocation
	 *            The hdnLocation to set.
	 */
	public void setHdnLocation(String hdnLocation) {
		this.hdnLocation = hdnLocation;
	}
	
	/**
	 * @return Returns the hdnUserID.
	 */
	public String getHdnUserID() {
		return hdnUserID;
	}
	
	/**
	 * @param hdnUserID
	 *            The hdnUserID to set.
	 */
	public void setHdnUserID(String hdnUserID) {
		this.hdnUserID = hdnUserID;
	}
	
	/**
	 * @return Returns the hdnUserRole.
	 */
	public String getHdnUserRole() {
		return hdnUserRole;
	}
	
	/**
	 * @param hdnUserRole
	 *            The hdnUserRole to set.
	 */
	public void setHdnUserRole(String hdnUserRole) {
		this.hdnUserRole = hdnUserRole;
	}
	
	/**
	 * @param contactNo
	 *            The contactNo to set.
	 */
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
	/**
	 * @return Returns the userSeqNo.
	 */
	public int getUserSeqNo() {
		return userSeqNo;
	}
	
	/**
	 * @param userSeqNo
	 *            The userSeqNo to set.
	 */
	public void setUserSeqNo(int userSeqNo) {
		this.userSeqNo = userSeqNo;
	}
	
	/**
	 * @return Returns the userid.
	 */
	public String getUserid() {
		return userid;
	}
	
	/**
	 * @param userid
	 *            The userid to set.
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	//Added for CR-112
	public ArrayList getUserRolesList() {
		return userRolesList;
	}

	public void setUserRolesList(ArrayList userRolesList) {
		this.userRolesList = userRolesList;
	}

	//Added for CR-112 for Sorting
	
	public String getColumnheader() {
		return columnheader;
	}

	public void setColumnheader(String columnheader) {
		this.columnheader = columnheader;
	}
	
	public int getOrderbyFlag() {
		return orderbyFlag;
	}

	public void setOrderbyFlag(int orderbyFlag) {
		this.orderbyFlag = orderbyFlag;
	}
	
	//	Added for CR-112 for Sorting Ends here

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	//	Added for CR-124 Starts
	public String getSentTime() {
		return sentTime;
	}

	public void setSentTime(String sentTime) {
		this.sentTime = sentTime;
	}

	public String getToEmailId() {
		return toEmailId;
	}

	public void setToEmailId(String toEmailId) {
		this.toEmailId = toEmailId;
	}
	//	Added for CR-124 Ends

	public String getEmailBody() {
		return emailBody;
	}

	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getFromEmailId() {
		return fromEmailId;
	}

	public void setFromEmailId(String fromEmailId) {
		this.fromEmailId = fromEmailId;
	}

	public int getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}

	public String getUpdtDate() {
		return updtDate;
	}

	public void setUpdtDate(String updtDate) {
		this.updtDate = updtDate;
	}

	public String getUpdtUserId() {
		return updtUserId;
	}

	public void setUpdtUserId(String updtUserId) {
		this.updtUserId = updtUserId;
	}

	public ArrayList getEmailDetails() {
		return emailDetails;
	}

	public void setEmailDetails(ArrayList emailDetails) {
		this.emailDetails = emailDetails;
	}

	public int getHdnSeqNo() {
		return hdnSeqNo;
	}

	public void setHdnSeqNo(int hdnSeqNo) {
		this.hdnSeqNo = hdnSeqNo;
	}

	public String getHdnActionNoticeFlag() {
		return hdnActionNoticeFlag;
	}

	public void setHdnActionNoticeFlag(String hdnActionNoticeFlag) {
		this.hdnActionNoticeFlag = hdnActionNoticeFlag;
	}

	public String getHdnCCNoticeFlag() {
		return hdnCCNoticeFlag;
	}

	public void setHdnCCNoticeFlag(String hdnCCNoticeFlag) {
		this.hdnCCNoticeFlag = hdnCCNoticeFlag;
	}

	public String getHdnInformationNoticeFlag() {
		return hdnInformationNoticeFlag;
	}

	public void setHdnInformationNoticeFlag(String hdnInformationNoticeFlag) {
		this.hdnInformationNoticeFlag = hdnInformationNoticeFlag;
	}
	
	public int getEmailPeriod() {
		return emailPeriod;
	}

	public void setEmailPeriod(int emailPeriod) {
		this.emailPeriod = emailPeriod;
	}

	public String getEmailFlag() {
		return emailFlag;
	}

	public void setEmailFlag(String emailFlag) {
		this.emailFlag = emailFlag;
	}

	
	
	
	
}
