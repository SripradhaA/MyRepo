package com.EMD.LSDB.form.Suggestions;

import java.util.ArrayList;

import org.apache.struts.upload.FormFile;

import com.EMD.LSDB.form.common.EMDForm;

public class SuggestForm extends EMDForm {
	
	public FormFile uploadedFile;
	
	private String screenName;
	
	private String Suggestions;
	
	private ArrayList suggestionList;

	private ArrayList suggestionStatusList;
	
	private int suggestStatusCode;
	
	private String imageName;
	
	private int imgSeqNo;
	
	private String imgContentType;
	
	private int suggestId;
	
	private String suggestComments;
	
	private String suggestUserId;
	
	private String adminUserId;
	
	private String suggestDate;
	
	private String adminUpdateDate;
	
	private int updateStatusCode;
	
	private String hdnAdminComments;
	
	//Added for CR-118
	private String roleName;
	
	//Added for CR-127
	private ArrayList initiatorList;
	private String userName;
	private String selectedInitiator;
	
	/**
	 * @return Returns the hdnAdminComments.
	 */
	public String getHdnAdminComments() {
		return hdnAdminComments;
	}

	/**
	 * @param hdnAdminComments The hdnAdminComments to set.
	 */
	public void setHdnAdminComments(String hdnAdminComments) {
		this.hdnAdminComments = hdnAdminComments;
	}

	/**
	 * @return Returns the screenName.
	 */
	public String getScreenName() {
		return screenName;
	}

	/**
	 * @param screenName The screenName to set.
	 */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	/**
	 * @return Returns the suggestions.
	 */
	public String getSuggestions() {
		return Suggestions;
	}

	/**
	 * @param suggestions The suggestions to set.
	 */
	public void setSuggestions(String suggestions) {
		Suggestions = suggestions;
	}

	/**
	 * @return Returns the uploadedFile.
	 */
	public FormFile getUploadedFile() {
		return uploadedFile;
	}

	/**
	 * @param uploadedFile The uploadedFile to set.
	 */
	public void setUploadedFile(FormFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	/**
	 * @return Returns the suggestionList.
	 */
	public ArrayList getSuggestionList() {
		return suggestionList;
	}

	/**
	 * @param suggestionList The suggestionList to set.
	 */
	public void setSuggestionList(ArrayList suggestionList) {
		this.suggestionList = suggestionList;
	}

	/**
	 * @return Returns the adminUpdateDate.
	 */
	public String getAdminUpdateDate() {
		return adminUpdateDate;
	}

	/**
	 * @param adminUpdateDate The adminUpdateDate to set.
	 */
	public void setAdminUpdateDate(String adminUpdateDate) {
		this.adminUpdateDate = adminUpdateDate;
	}

	/**
	 * @return Returns the adminUserId.
	 */
	public String getAdminUserId() {
		return adminUserId;
	}

	/**
	 * @param adminUserId The adminUserId to set.
	 */
	public void setAdminUserId(String adminUserId) {
		this.adminUserId = adminUserId;
	}

	/**
	 * @return Returns the imageName.
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * @param imageName The imageName to set.
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	/**
	 * @return Returns the imgContentType.
	 */
	public String getImgContentType() {
		return imgContentType;
	}

	/**
	 * @param imgContentType The imgContentType to set.
	 */
	public void setImgContentType(String imgContentType) {
		this.imgContentType = imgContentType;
	}

	/**
	 * @return Returns the imgSeqNo.
	 */
	public int getImgSeqNo() {
		return imgSeqNo;
	}

	/**
	 * @param imgSeqNo The imgSeqNo to set.
	 */
	public void setImgSeqNo(int imgSeqNo) {
		this.imgSeqNo = imgSeqNo;
	}

	/**
	 * @return Returns the suggestComments.
	 */
	public String getSuggestComments() {
		return suggestComments;
	}

	/**
	 * @param suggestComments The suggestComments to set.
	 */
	public void setSuggestComments(String suggestComments) {
		this.suggestComments = suggestComments;
	}

	/**
	 * @return Returns the suggestDate.
	 */
	public String getSuggestDate() {
		return suggestDate;
	}

	/**
	 * @param suggestDate The suggestDate to set.
	 */
	public void setSuggestDate(String suggestDate) {
		this.suggestDate = suggestDate;
	}

	/**
	 * @return Returns the suggestId.
	 */
	public int getSuggestId() {
		return suggestId;
	}

	/**
	 * @param suggestId The suggestId to set.
	 */
	public void setSuggestId(int suggestId) {
		this.suggestId = suggestId;
	}

	/**
	 * @return Returns the suggestStatusCode.
	 */
	public int getSuggestStatusCode() {
		return suggestStatusCode;
	}

	/**
	 * @param suggestStatusCode The suggestStatusCode to set.
	 */
	public void setSuggestStatusCode(int suggestStatusCode) {
		this.suggestStatusCode = suggestStatusCode;
	}

	/**
	 * @return Returns the suggestUserId.
	 */
	public String getSuggestUserId() {
		return suggestUserId;
	}

	/**
	 * @param suggestUserId The suggestUserId to set.
	 */
	public void setSuggestUserId(String suggestUserId) {
		this.suggestUserId = suggestUserId;
	}

	/**
	 * @return Returns the suggestionStatusList.
	 */
	public ArrayList getSuggestionStatusList() {
		return suggestionStatusList;
	}

	/**
	 * @param suggestionStatusList The suggestionStatusList to set.
	 */
	public void setSuggestionStatusList(ArrayList suggestionStatusList) {
		this.suggestionStatusList = suggestionStatusList;
	}

	/**
	 * @return Returns the updateStatusCode.
	 */
	public int getUpdateStatusCode() {
		return updateStatusCode;
	}

	/**
	 * @param updateStatusCode The updateStatusCode to set.
	 */
	public void setUpdateStatusCode(int updateStatusCode) {
		this.updateStatusCode = updateStatusCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public ArrayList getInitiatorList() {
		return initiatorList;
	}

	public void setInitiatorList(ArrayList initiatorList) {
		this.initiatorList = initiatorList;
	}

	public String getSelectedInitiator() {
		return selectedInitiator;
	}

	public void setSelectedInitiator(String selectedInitiator) {
		this.selectedInitiator = selectedInitiator;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}	
	
	
	
	
}
