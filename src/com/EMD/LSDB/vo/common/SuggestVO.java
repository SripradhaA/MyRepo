/*
 * Created on May 5, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.vo.common;
 
/**
 * @author PS57222
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SuggestVO extends EMDVO{
	
	private FileVO objFileVO = new FileVO();
	
	private String Suggestions;
	
	private String screenName;
	
	private int suggestStatusCode;
	
	private String suggestStatusDesc;
	
	private String imageName;
	
	private int imgSeqNo;
	
	private String imgContentType;
	
	private int suggestId;
	
	private String suggestComments;
	
	private String suggestUserId;
	
	private String adminUserId;
	
	private String suggestDate;
	
	private String adminUpdateDate;
	
	//Added for CR-127
	private String selectedInitiator;
	
	public FileVO getFileVO() {
		
		return this.objFileVO;
	}
	
	public void setFileVO(FileVO fileVO) {
		
		this.objFileVO = fileVO;
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
	 * @return Returns the objFileVO.
	 */
	public FileVO getObjFileVO() {
		return objFileVO;
	}

	/**
	 * @param objFileVO The objFileVO to set.
	 */
	public void setObjFileVO(FileVO objFileVO) {
		this.objFileVO = objFileVO;
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
	 * @return Returns the suggestStatusDesc.
	 */
	public String getSuggestStatusDesc() {
		return suggestStatusDesc;
	}

	/**
	 * @param suggestStatusDesc The suggestStatusDesc to set.
	 */
	public void setSuggestStatusDesc(String suggestStatusDesc) {
		this.suggestStatusDesc = suggestStatusDesc;
	}

	public String getSelectedInitiator() {
		return selectedInitiator;
	}

	public void setSelectedInitiator(String selectedInitiator) {
		this.selectedInitiator = selectedInitiator;
	}
	
	
}