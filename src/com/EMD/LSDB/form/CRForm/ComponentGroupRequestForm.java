/*
 * Created on Jun 5, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.form.CRForm;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the form fields for the CR Form
 ******************************************************************************/

public class ComponentGroupRequestForm extends EMDForm {
	
	/*
	 * Component group info starts
	 */
	
	private int modelSeqNo;
	
	private int sectionSeqNo;
	
	private int subSectionSeqNo;
	
	private int compGrpSeqNo;
	
	private int compSeqNo;
	
	/*
	 * Change for Model apply flag
	 */
	
	private boolean applyModelFlag;
	
	private int compGroupChangeTypeSeqNo;
	
	private int oldCompGrpSeqNo;
	
	private String oldCompGrpName;
	
	private String oldCompGrpValidFlag;
	
	private String oldCompGrpChacFlag;
	
	private String newCompGrpName;
	
	private String newCompGrpValidFlag;
	
	private String newCompGrpChacFlag;
	
	//Ends
	
	/*
	 * Component info starts
	 */
	
	private int compChangeTypeSeqNo;
	
	private int oldCompSeqNo;
	
	private String oldCompName;
	
	private String oldCompDefaultFlag;
	
	private String newCompName;
	
	private String newCompDefaultFlag;
	
	private ArrayList requestDetails;
	
	private ArrayList modelList;
	
	private ArrayList listSections;
	
	private ArrayList subSectionList;
	
	private ArrayList compGrpList;
	
	private ArrayList compList;
	
	private ArrayList compGroupDetail;
	
	private ArrayList compDetail;
	
	private String reason;
	
	private String hdnReqID;
	
	private String clauseColorFlag;
	
	private String userOwnRequest;
	
	//Button level changes
	public String approveRejectHide;
	
	/**
	 * Added for Admin comments enhancement on 19-Nov-08
	 */
	
	private String requestDesc;
	
	private String adminComments;
	
	private String hdnAdminComments;
	
	//Added to bring back user to same work area	
	private String tableID;
	
	//Added to include Save function from alert
	private String alertFlag;
	
	//Added For CR_80 by RR68151
	private String hdnSelModel;
	
	private String hdnSelSec;
	
	private String hdnSelSubSec;
	
	private String hdnSelCompGrp;
	
	private String validFlag;
	
	//	Adding for CR_94
	private ArrayList searchList;
	public ArrayList getSearchList() {
		return searchList;
	}

	public void setSearchList(ArrayList searchList) {
		this.searchList = searchList;
	}

	//Ends here CR_94
	/**
	 * @return Returns the alertFlag.
	 */
	public String getAlertFlag() {
		return alertFlag;
	}
	
	/**
	 * @param alertFlag The alertFlag to set.
	 */
	public void setAlertFlag(String alertFlag) {
		this.alertFlag = alertFlag;
	}
	
	/**
	 * @return Returns the tableID.
	 */
	public String getTableID() {
		return tableID;
	}
	
	/**
	 * @param tableID The tableID to set.
	 */
	public void setTableID(String tableID) {
		this.tableID = tableID;
	}
	
	/**
	 * @return Returns the applyModelFlag.
	 */
	public boolean isApplyModelFlag() {
		return applyModelFlag;
	}
	
	/**
	 * @param applyModelFlag The applyModelFlag to set.
	 */
	public void setApplyModelFlag(boolean applyModelFlag) {
		this.applyModelFlag = applyModelFlag;
	}
	
	public String getAdminComments() {
		return adminComments;
	}
	
	public void setAdminComments(String adminComments) {
		this.adminComments = adminComments;
	}
	
	public String getHdnAdminComments() {
		return hdnAdminComments;
	}
	
	public void setHdnAdminComments(String hdnAdminComments) {
		this.hdnAdminComments = hdnAdminComments;
	}
	
	public String getRequestDesc() {
		return requestDesc;
	}
	
	public void setRequestDesc(String requestDesc) {
		this.requestDesc = requestDesc;
	}
	
	/**
	 * @return Returns the approveRejectHide.
	 */
	public String getApproveRejectHide() {
		return approveRejectHide;
	}
	
	/**
	 * @param approveRejectHide
	 *            The approveRejectHide to set.
	 */
	public void setApproveRejectHide(String approveRejectHide) {
		this.approveRejectHide = approveRejectHide;
	}
	
	/**
	 * @return Returns the clauseColorFlag.
	 */
	public String getClauseColorFlag() {
		return clauseColorFlag;
	}
	
	/**
	 * @param clauseColorFlag
	 *            The clauseColorFlag to set.
	 */
	public void setClauseColorFlag(String clauseColorFlag) {
		this.clauseColorFlag = clauseColorFlag;
	}
	
	/**
	 * @return Returns the hdnReqID.
	 */
	public String getHdnReqID() {
		return hdnReqID;
	}
	
	/**
	 * @param hdnReqID
	 *            The hdnReqID to set.
	 */
	public void setHdnReqID(String hdnReqID) {
		this.hdnReqID = hdnReqID;
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
	 * @return Returns the compChangeTypeSeqNo.
	 */
	public int getCompChangeTypeSeqNo() {
		return compChangeTypeSeqNo;
	}
	
	/**
	 * @param compChangeTypeSeqNo
	 *            The compChangeTypeSeqNo to set.
	 */
	public void setCompChangeTypeSeqNo(int compChangeTypeSeqNo) {
		this.compChangeTypeSeqNo = compChangeTypeSeqNo;
	}
	
	/**
	 * @return Returns the compGroupChangeTypeSeqNo.
	 */
	public int getCompGroupChangeTypeSeqNo() {
		return compGroupChangeTypeSeqNo;
	}
	
	/**
	 * @param compGroupChangeTypeSeqNo
	 *            The compGroupChangeTypeSeqNo to set.
	 */
	public void setCompGroupChangeTypeSeqNo(int compGroupChangeTypeSeqNo) {
		this.compGroupChangeTypeSeqNo = compGroupChangeTypeSeqNo;
	}
	
	/**
	 * @return Returns the newCompDefaultFlag.
	 */
	public String getNewCompDefaultFlag() {
		return newCompDefaultFlag;
	}
	
	/**
	 * @param newCompDefaultFlag
	 *            The newCompDefaultFlag to set.
	 */
	public void setNewCompDefaultFlag(String newCompDefaultFlag) {
		this.newCompDefaultFlag = newCompDefaultFlag;
	}
	
	/**
	 * @return Returns the newCompGrpChacFlag.
	 */
	public String getNewCompGrpChacFlag() {
		return newCompGrpChacFlag;
	}
	
	/**
	 * @param newCompGrpChacFlag
	 *            The newCompGrpChacFlag to set.
	 */
	public void setNewCompGrpChacFlag(String newCompGrpChacFlag) {
		this.newCompGrpChacFlag = newCompGrpChacFlag;
	}
	
	/**
	 * @return Returns the newCompGrpName.
	 */
	public String getNewCompGrpName() {
		return newCompGrpName;
	}
	
	/**
	 * @param newCompGrpName
	 *            The newCompGrpName to set.
	 */
	public void setNewCompGrpName(String newCompGrpName) {
		this.newCompGrpName = newCompGrpName;
	}
	
	/**
	 * @return Returns the newCompGrpValidFlag.
	 */
	public String getNewCompGrpValidFlag() {
		return newCompGrpValidFlag;
	}
	
	/**
	 * @param newCompGrpValidFlag
	 *            The newCompGrpValidFlag to set.
	 */
	public void setNewCompGrpValidFlag(String newCompGrpValidFlag) {
		this.newCompGrpValidFlag = newCompGrpValidFlag;
	}
	
	/**
	 * @return Returns the newCompName.
	 */
	public String getNewCompName() {
		return newCompName;
	}
	
	/**
	 * @param newCompName
	 *            The newCompName to set.
	 */
	public void setNewCompName(String newCompName) {
		this.newCompName = newCompName;
	}
	
	/**
	 * @return Returns the oldCompDefaultFlag.
	 */
	public String getOldCompDefaultFlag() {
		return oldCompDefaultFlag;
	}
	
	/**
	 * @param oldCompDefaultFlag
	 *            The oldCompDefaultFlag to set.
	 */
	public void setOldCompDefaultFlag(String oldCompDefaultFlag) {
		this.oldCompDefaultFlag = oldCompDefaultFlag;
	}
	
	/**
	 * @return Returns the oldCompGrpChacFlag.
	 */
	public String getOldCompGrpChacFlag() {
		return oldCompGrpChacFlag;
	}
	
	/**
	 * @param oldCompGrpChacFlag
	 *            The oldCompGrpChacFlag to set.
	 */
	public void setOldCompGrpChacFlag(String oldCompGrpChacFlag) {
		this.oldCompGrpChacFlag = oldCompGrpChacFlag;
	}
	
	/**
	 * @return Returns the oldCompGrpName.
	 */
	public String getOldCompGrpName() {
		return oldCompGrpName;
	}
	
	/**
	 * @param oldCompGrpName
	 *            The oldCompGrpName to set.
	 */
	public void setOldCompGrpName(String oldCompGrpName) {
		this.oldCompGrpName = oldCompGrpName;
	}
	
	/**
	 * @return Returns the oldCompGrpSeqNo.
	 */
	public int getOldCompGrpSeqNo() {
		return oldCompGrpSeqNo;
	}
	
	/**
	 * @param oldCompGrpSeqNo
	 *            The oldCompGrpSeqNo to set.
	 */
	public void setOldCompGrpSeqNo(int oldCompGrpSeqNo) {
		this.oldCompGrpSeqNo = oldCompGrpSeqNo;
	}
	
	/**
	 * @return Returns the oldCompGrpValidFlag.
	 */
	public String getOldCompGrpValidFlag() {
		return oldCompGrpValidFlag;
	}
	
	/**
	 * @param oldCompGrpValidFlag
	 *            The oldCompGrpValidFlag to set.
	 */
	public void setOldCompGrpValidFlag(String oldCompGrpValidFlag) {
		this.oldCompGrpValidFlag = oldCompGrpValidFlag;
	}
	
	/**
	 * @return Returns the oldCompName.
	 */
	public String getOldCompName() {
		return oldCompName;
	}
	
	/**
	 * @param oldCompName
	 *            The oldCompName to set.
	 */
	public void setOldCompName(String oldCompName) {
		this.oldCompName = oldCompName;
	}
	
	/**
	 * @return Returns the oldCompSeqNo.
	 */
	public int getOldCompSeqNo() {
		return oldCompSeqNo;
	}
	
	/**
	 * @param oldCompSeqNo
	 *            The oldCompSeqNo to set.
	 */
	public void setOldCompSeqNo(int oldCompSeqNo) {
		this.oldCompSeqNo = oldCompSeqNo;
	}
	
	/**
	 * @return Returns the requestDetails.
	 */
	public ArrayList getRequestDetails() {
		return requestDetails;
	}
	
	/**
	 * @param requestDetails
	 *            The requestDetails to set.
	 */
	public void setRequestDetails(ArrayList requestDetails) {
		this.requestDetails = requestDetails;
	}
	
	/**
	 * @return Returns the modelSeqNo.
	 */
	public int getModelSeqNo() {
		return modelSeqNo;
	}
	
	/**
	 * @param modelSeqNo
	 *            The modelSeqNo to set.
	 */
	public void setModelSeqNo(int modelSeqNo) {
		this.modelSeqNo = modelSeqNo;
	}
	
	/**
	 * @return Returns the sectionSeqNo.
	 */
	public int getSectionSeqNo() {
		return sectionSeqNo;
	}
	
	/**
	 * @param sectionSeqNo
	 *            The sectionSeqNo to set.
	 */
	public void setSectionSeqNo(int sectionSeqNo) {
		this.sectionSeqNo = sectionSeqNo;
	}
	
	/**
	 * @return Returns the subSectionSeqNo.
	 */
	public int getSubSectionSeqNo() {
		return subSectionSeqNo;
	}
	
	/**
	 * @param subSectionSeqNo
	 *            The subSectionSeqNo to set.
	 */
	public void setSubSectionSeqNo(int subSectionSeqNo) {
		this.subSectionSeqNo = subSectionSeqNo;
	}
	
	/**
	 * @return Returns the compGrpList.
	 */
	public ArrayList getCompGrpList() {
		return compGrpList;
	}
	
	/**
	 * @param compGrpList
	 *            The compGrpList to set.
	 */
	public void setCompGrpList(ArrayList compGrpList) {
		this.compGrpList = compGrpList;
	}
	
	/**
	 * @return Returns the compList.
	 */
	public ArrayList getCompList() {
		return compList;
	}
	
	/**
	 * @param compList
	 *            The compList to set.
	 */
	public void setCompList(ArrayList compList) {
		this.compList = compList;
	}
	
	/**
	 * @return Returns the listSections.
	 */
	public ArrayList getListSections() {
		return listSections;
	}
	
	/**
	 * @param listSections
	 *            The listSections to set.
	 */
	public void setListSections(ArrayList listSections) {
		this.listSections = listSections;
	}
	
	/**
	 * @return Returns the modelList.
	 */
	public ArrayList getModelList() {
		return modelList;
	}
	
	/**
	 * @param modelList
	 *            The modelList to set.
	 */
	public void setModelList(ArrayList modelList) {
		this.modelList = modelList;
	}
	
	/**
	 * @return Returns the subSectionList.
	 */
	public ArrayList getSubSectionList() {
		return subSectionList;
	}
	
	/**
	 * @param subSectionList
	 *            The subSectionList to set.
	 */
	public void setSubSectionList(ArrayList subSectionList) {
		this.subSectionList = subSectionList;
	}
	
	/**
	 * @return Returns the compGrpSeqNo.
	 */
	public int getCompGrpSeqNo() {
		return compGrpSeqNo;
	}
	
	/**
	 * @param compGrpSeqNo
	 *            The compGrpSeqNo to set.
	 */
	public void setCompGrpSeqNo(int compGrpSeqNo) {
		this.compGrpSeqNo = compGrpSeqNo;
	}
	
	/**
	 * @return Returns the compSeqNo.
	 */
	public int getCompSeqNo() {
		return compSeqNo;
	}
	
	/**
	 * @param compSeqNo
	 *            The compSeqNo to set.
	 */
	public void setCompSeqNo(int compSeqNo) {
		this.compSeqNo = compSeqNo;
	}
	
	/**
	 * @return Returns the compDetail.
	 */
	public ArrayList getCompDetail() {
		return compDetail;
	}
	
	/**
	 * @param compDetail
	 *            The compDetail to set.
	 */
	public void setCompDetail(ArrayList compDetail) {
		this.compDetail = compDetail;
	}
	
	/**
	 * @return Returns the compGroupDetail.
	 */
	public ArrayList getCompGroupDetail() {
		return compGroupDetail;
	}
	
	/**
	 * @param compGroupDetail
	 *            The compGroupDetail to set.
	 */
	public void setCompGroupDetail(ArrayList compGroupDetail) {
		this.compGroupDetail = compGroupDetail;
	}
	
	/**
	 * @return Returns the userOwnRequest.
	 */
	public String getUserOwnRequest() {
		return userOwnRequest;
	}
	
	/**
	 * @param userOwnRequest
	 *            The userOwnRequest to set.
	 */
	public void setUserOwnRequest(String userOwnRequest) {
		this.userOwnRequest = userOwnRequest;
	}

	/**
	 * @return Returns the hdnSelModel.
	 */
	public String getHdnSelModel() {
		return hdnSelModel;
	}

	/**
	 * @param hdnSelModel The hdnSelModel to set.
	 */
	public void setHdnSelModel(String hdnSelModel) {
		this.hdnSelModel = hdnSelModel;
	}

	/**
	 * @return Returns the hdnSelSec.
	 */
	public String getHdnSelSec() {
		return hdnSelSec;
	}

	/**
	 * @param hdnSelSec The hdnSelSec to set.
	 */
	public void setHdnSelSec(String hdnSelSec) {
		this.hdnSelSec = hdnSelSec;
	}

	/**
	 * @return Returns the hdnSelSubSec.
	 */
	public String getHdnSelSubSec() {
		return hdnSelSubSec;
	}

	/**
	 * @param hdnSelSubSec The hdnSelSubSec to set.
	 */
	public void setHdnSelSubSec(String hdnSelSubSec) {
		this.hdnSelSubSec = hdnSelSubSec;
	}

	/**
	 * @return Returns the hdnSelCompGrp.
	 */
	public String getHdnSelCompGrp() {
		return hdnSelCompGrp;
	}

	/**
	 * @param hdnSelCompGrp The hdnSelCompGrp to set.
	 */
	public void setHdnSelCompGrp(String hdnSelCompGrp) {
		this.hdnSelCompGrp = hdnSelCompGrp;
	}

	/**
	 * @return Returns the validFlag.
	 */
	public String getValidFlag() {
		return validFlag;
	}

	/**
	 * @param validFlag The validFlag to set.
	 */
	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}
	
	//Ends
	
}