package com.EMD.LSDB.form.common;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class EMDForm extends ActionForm {
	
	private String errorMessage;
	
	private String userID;
	
	private String method;
	
	private String messageID;
	
	private ArrayList sectionList;
	
	private ArrayList orderList;
	
	private int orderKey;
	
	// Added for LSDB_CR_46
	private ArrayList specTypeList;
	
	private int specTypeNo = 0;
//Added For CR_104
	private int hdnSelSpecTypeNo = 0;
	
	// Added for LSDB_CR-45(CR Form)
	private int requestID;
	
	private String reqModelSaved;
	
	private String roleID;
	
	// Added to give View Current Spec button in Main Features Screen CR-65
	private String hdnRevViewCode;
	// Ends
	
	//Added for CR-112 to Add message of the day in HOME Screeen
	private String hdnMessage;
	
	//Added for CR-121
	private int intScreenId;
	private String errorElement;
	
	//Added for CR-126
	private String emailFlag;
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
	 * @return Returns the specTypeNo.
	 */
	public int getSpecTypeNo() {
		return specTypeNo;
	}
	
	/**
	 * @param specTypeNo
	 *            The specTypeNo to set.
	 */
	public void setSpecTypeNo(int specTypeNo) {
		this.specTypeNo = specTypeNo;
	}
	
	/**
	 * @return Returns the specTypeList.
	 */
	public ArrayList getSpecTypeList() {
		return specTypeList;
	}
	
	/**
	 * @param specTypeList
	 *            The specTypeList to set.
	 */
	public void setSpecTypeList(ArrayList specTypeList) {
		this.specTypeList = specTypeList;
	}
	
	/**
	 * @return Returns the orderList.
	 */
	public ArrayList getOrderList() {
		return orderList;
	}
	
	/**
	 * @param orderList
	 *            The orderList to set.
	 */
	public void setOrderList(ArrayList orderList) {
		this.orderList = orderList;
	}
	
	/**
	 * @return Returns the sectionList.
	 */
	public ArrayList getSectionList() {
		return sectionList;
	}
	
	/**
	 * @param sectionList
	 *            The sectionList to set.
	 */
	public void setSectionList(ArrayList sectionList) {
		this.sectionList = sectionList;
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
	
	public void reset(ActionMapping objActionMapping,
			HttpServletRequest objHttpServletRequest) {
	}
	
	public ActionErrors validate(ActionMapping objActionMapping,
			HttpServletRequest objHttpServletRequest) {
		ActionErrors objActionErrors = super.validate(objActionMapping,
				objHttpServletRequest);
		if (objActionErrors == null) {
			objActionErrors = new ActionErrors();
		}
		return objActionErrors;
	}
	
	/**
	 * @return Returns the errorMessage.
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	
	/**
	 * @param errorMessage
	 *            The errorMessage to set.
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	/**
	 * @return Returns the method.
	 */
	public String getMethod() {
		return method;
	}
	
	/**
	 * @param method
	 *            The method to set.
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	
	/**
	 * @return Returns the messageID.
	 */
	public String getMessageID() {
		return messageID;
	}
	
	/**
	 * @param messageID
	 *            The messageID to set.
	 */
	public void setMessageID(String messageID) {
		this.messageID = messageID;
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
	 * @return Returns the reqModelSaved.
	 */
	public String getReqModelSaved() {
		return reqModelSaved;
	}
	
	/**
	 * @param reqModelSaved
	 *            The reqModelSaved to set.
	 */
	public void setReqModelSaved(String reqModelSaved) {
		this.reqModelSaved = reqModelSaved;
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
	 * @return Returns the hdnRevViewCode.
	 */
	public String getHdnRevViewCode() {
		return hdnRevViewCode;
	}
	
	/**
	 * @param hdnRevViewCode
	 *            The hdnRevViewCode to set.
	 */
	public void setHdnRevViewCode(String hdnRevViewCode) {
		this.hdnRevViewCode = hdnRevViewCode;
	}

	public int getHdnSelSpecTypeNo() {
		return hdnSelSpecTypeNo;
	}

	public void setHdnSelSpecTypeNo(int hdnSelSpecTypeNo) {
		this.hdnSelSpecTypeNo = hdnSelSpecTypeNo;
	}
	
//	Added for CR-112 to Add message of the day in HOME Screeen

	public String getHdnMessage() {
		return hdnMessage;
	}

	public void setHdnMessage(String hdnMessage) {
		this.hdnMessage = hdnMessage;
	}

	public int getIntScreenId() {
		return intScreenId;
	}

	public void setIntScreenId(int intScreenId) {
		this.intScreenId = intScreenId;
	}

	public String getErrorElement() {
		return errorElement;
	}

	public void setErrorElement(String errorElement) {
		this.errorElement = errorElement;
	}

	public String getEmailFlag() {
		return emailFlag;
	}

	public void setEmailFlag(String emailFlag) {
		this.emailFlag = emailFlag;
	}

//	Added for CR-112 to Add message of the day in HOME Screeen Ends here.
	
	
	
}
