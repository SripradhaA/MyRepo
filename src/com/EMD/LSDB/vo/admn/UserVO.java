/**
 * 
 */
package com.EMD.LSDB.vo.admn;

import java.util.ArrayList;

import com.EMD.LSDB.vo.common.EMDVO;

/**
 * @author VV49326
 *
 */
public class UserVO extends EMDVO {
	
	private String userrId;
	
	private String firsttName;
	
	private String lasttName;
	
	private String password;
	
	private String emaillId;
	
	private String location;
	
	private String contacttNo;
	
	private String role;
	
	private ArrayList userRoleVO;
	
	private String name;
	//Added for CR_94 
	private int orderbyFlag=0;
	
	//Added for CR_109
	private String prevLoggedIn;
	
	private String loginFlag;

	//Added for CR_112
	private String roleName;
	
	//Added for CR-113
	private String[] userList;
	
	private String subject;
	
	private String body;
	
	//Added for CR-121
	private String serverType;
	
	//Added for CR-124 Starts
	private int seqNo;
	
	private String fromEmailId;
	
	private String toEmailId;
	
	private String emailBody;
	
	private String emailSubject;
	
	private String sentTime;
	
	private String updtUserId;
	
	private String updtDate;
	
	private String[] seqNos;
	
	//	Added for CR-124 Ends
	
	//Added for CR-126 Starts
	private String actionNoticeFlag;
	
	private String informationNoticeFlag;
	
	private String ccNoticeFlag;
	
	private String[] actionNoticeFlags;
	
	private String[] informationNoticeFlags;
	
	private String[] ccNoticeFlags;
	
	private int emailPeriod;
	//Added for CR-126 Ends

	//Added for CR-128 starts
	private String activitySeqNo;
	
	private String activityType;
	
	private String eventType;
	
	private String eventFrom;
	
	private String eventTo;
	
	private String actionBy;
	
	private String actionOn;
	
	private String modifiedFor; //Added for CR-128
	
	//Added for CR-128 ends	
	
	

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return Returns the location.
	 */
	public String getLocation() {
		return location;
	}
	
	/**
	 * @param location The location to set.
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
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
	 * @return Returns the role.
	 */
	public String getRole() {
		return role;
	}
	
	/**
	 * @param role The role to set.
	 */
	public void setRole(String role) {
		this.role = role;
	}
	
	/**
	 * @return Returns the userRoleVO.
	 */
	public ArrayList getUserRoleVO() {
		return userRoleVO;
	}
	
	/**
	 * @param userRoleVO The userRoleVO to set.
	 */
	public void setUserRoleVO(ArrayList userRoleVO) {
		this.userRoleVO = userRoleVO;
	}
	
	/**
	 * @return Returns the userrId.
	 */
	public String getUserrId() {
		return userrId;
	}
	
	/**
	 * @param userrId The userrId to set.
	 */
	public void setUserrId(String userrId) {
		this.userrId = userrId;
	}
	
	/**
	 * @return Returns the contacttNo.
	 */
	public String getContacttNo() {
		return contacttNo;
	}
	
	/**
	 * @param contacttNo The contacttNo to set.
	 */
	public void setContacttNo(String contacttNo) {
		this.contacttNo = contacttNo;
	}
	
	/**
	 * @return Returns the emaillId.
	 */
	public String getEmaillId() {
		return emaillId;
	}
	
	/**
	 * @param emaillId The emaillId to set.
	 */
	public void setEmaillId(String emaillId) {
		this.emaillId = emaillId;
	}
	
	/**
	 * @return Returns the firsttName.
	 */
	public String getFirsttName() {
		return firsttName;
	}
	
	/**
	 * @param firsttName The firsttName to set.
	 */
	public void setFirsttName(String firsttName) {
		this.firsttName = firsttName;
	}
	
	/**
	 * @return Returns the lasttName.
	 */
	public String getLasttName() {
		return lasttName;
	}
	
	/**
	 * @param lasttName The lasttName to set.
	 */
	public void setLasttName(String lasttName) {
		this.lasttName = lasttName;
	}

	public int getOrderbyFlag() {
		return orderbyFlag;
	}

	public void setOrderbyFlag(int orderbyFlag) {
		this.orderbyFlag = orderbyFlag;
	}
	
//	Added for CR_109 
	public String getPrevLoggedIn() {
		return prevLoggedIn;
	}

	public void setPrevLoggedIn(String prevLoggedIn) {
		this.prevLoggedIn = prevLoggedIn;
	}

	/**
	 * @return Returns the loginFlag.
	 */
	public String getLoginFlag() {
		return loginFlag;
	}

	/**
	 * @param loginFlag The loginFlag to set.
	 */
	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}
//	Added for CR_109 Ends here

//	Added for CR_112
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
//	Added for CR_112 Ends Here	

	//Added for CR_113 		
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

	public String[] getUserList() {
		return userList;
	}

	public void setUserList(String[] userList) {
		this.userList = userList;
	}
	//Added for CR_113 Ends Here			

	public String getServerType() {
		return serverType;
	}

	public void setServerType(String serverType) {
		this.serverType = serverType;
	}
	//Added for CR-124 Starts
	
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

	public String[] getSeqNos() {
		return seqNos;
	}

	public void setSeqNos(String[] seqNos) {
		this.seqNos = seqNos;
	}
	//	Added for CR-124 Ends

	//	Added for CR-126
	public String getActionNoticeFlag() {
		return actionNoticeFlag;
	}

	public void setActionNoticeFlag(String actionNoticeFlag) {
		this.actionNoticeFlag = actionNoticeFlag;
	}

	public String getCcNoticeFlag() {
		return ccNoticeFlag;
	}

	public void setCcNoticeFlag(String ccNoticeFlag) {
		this.ccNoticeFlag = ccNoticeFlag;
	}

	public String getInformationNoticeFlag() {
		return informationNoticeFlag;
	}

	public void setInformationNoticeFlag(String informationNoticeFlag) {
		this.informationNoticeFlag = informationNoticeFlag;
	}

	public String[] getActionNoticeFlags() {
		return actionNoticeFlags;
	}

	public void setActionNoticeFlags(String[] actionNoticeFlags) {
		this.actionNoticeFlags = actionNoticeFlags;
	}

	public String[] getCcNoticeFlags() {
		return ccNoticeFlags;
	}

	public void setCcNoticeFlags(String[] ccNoticeFlags) {
		this.ccNoticeFlags = ccNoticeFlags;
	}

	public String[] getInformationNoticeFlags() {
		return informationNoticeFlags;
	}

	public void setInformationNoticeFlags(String[] informationNoticeFlags) {
		this.informationNoticeFlags = informationNoticeFlags;
	}

	public int getEmailPeriod() {
		return emailPeriod;
	}

	public void setEmailPeriod(int emailPeriod) {
		this.emailPeriod = emailPeriod;
	}

	public String getActivitySeqNo() {
		return activitySeqNo;
	}

	public void setActivitySeqNo(String activitySeqNo) {
		this.activitySeqNo = activitySeqNo;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getEventFrom() {
		return eventFrom;
	}

	public void setEventFrom(String eventFrom) {
		this.eventFrom = eventFrom;
	}

	public String getEventTo() {
		return eventTo;
	}

	public void setEventTo(String eventTo) {
		this.eventTo = eventTo;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getActionBy() {
		return actionBy;
	}

	public void setActionBy(String actionBy) {
		this.actionBy = actionBy;
	}

	public String getActionOn() {
		return actionOn;
	}

	public void setActionOn(String actionOn) {
		this.actionOn = actionOn;
	}
	
	//Added for CR-126 Ends

	//Added for CR-128
	
	public String getModifiedFor() {
		return modifiedFor;
	}

	public void setModifiedFor(String modifiedFor) {
		this.modifiedFor = modifiedFor;
	}
	
	//Added for CR-128 Ends here
	
	
}
