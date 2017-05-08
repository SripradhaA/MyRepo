/*
 * Created on Oct 20, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.form.CRForm;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;
import com.EMD.LSDB.vo.common.RequestModelVO;

/**
 * @author PS57222
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class CreateChangeClauseRequestForm extends EMDForm {
	
	private int modelSeqNo;
	
	private int sectionSeqNo;
	
	private int subSectionSeqNo;
	
	/*
	 * Change for Model apply flag
	 */
	
	private boolean applyModelFlag;
	
	private ArrayList modelList;
	
	private ArrayList sectionList;
	
	private ArrayList subSectionList;
	
	private ArrayList requestList;
	
	private ArrayList clauseList;
	
	private ArrayList stdEquipmentList;
	
	private String clauseDescForTextArea;
	
	private String[] edlNo;
	
	private String[] RefEDLNo;
	
	private int[] partOfSeqNo;
	
	private String[] partOfCode;
	
	private int[] partOfclaSeqNo;
	
	private String revisionNo;
	
	private int dwONumber;
	
	private int partNumber;
	
	private int priceBookNumber;
	
	private String comments;
	
	private String clauseNo;
	
	private String fromComps;
	
	private int standardEquipmentSeqNo;
	
	private String[] clauseTableDataArray1;
	
	private String[] clauseTableDataArray2;
	
	private String[] clauseTableDataArray3;
	
	private String[] clauseTableDataArray4;
	
	private String[] clauseTableDataArray5;
	
	private String deleterow;
	
	private String[] tableArrayData1;
	
	private String modelName;
	
	private String sectionName;
	
	private String subSectionName;
	
	private ArrayList componentVO;
	
	private int clauseSeqNo;
	
	private String clauseNum;
	
	private int versionNo;
	
	private int hdnClauseSeqNo;
	
	private int hdnVersionNo;
	
	private String hdnClauseNum;
	
	private int hdnModelSeqNo;
	
	private int hdnSubSectionSeqNo;
	
	private String[] toPartOfCode;
	
	private int todwONumber;
	
	private int toPartNumber;
	
	private int topriceBookNumber;
	
	private String toComments;
	
	private int hdnSectionSeqNo;
	
	private String requestDesc;
	
	private String adminComments;
	
	private String hdnAdminComments;
	
	/**
	 * 
	 * Form Fields for To Part
	 */
	
	private String toParentClauseNo;
	
	private String toParentClaDesc;
	
	private String toClaVerClauseNo;
	
	private String toClaVerDesc;
	
	private String toClauseNo;
	
	private String toClauseDesc;
	
	private String toComponents;
	
	private String toDefaultFlag;
	
	private String[] toRefEDLNo;
	
	private String[] toEdlNo;
	
	private int toStdEquipSeqNo;
	
	private String[] partOfClaDesc;
	
	private ArrayList clauseVersions;
	
	private String reqModelSaved;
	
	private RequestModelVO compGrpNameVO;
	
	private String hdnReqID;
	
	private int standEquipmentSeqNo;
	
	private String compColourFlag;
	
	private String userOwnRequest;
	
	public String[] toclauseTableDataArray1;
	
	public String[] toclauseTableDataArray2;
	
	public String[] toclauseTableDataArray3;
	
	public String[] toclauseTableDataArray4;
	
	public String[] toclauseTableDataArray5;
	
	private String toComponentSeqNo;
	
	private String reason;
	
	private int parentClauseSeqNo;
	
	private int claVerSeqNo;
	
	private int toClaVerSeqNo;
	
	private int changeTypeSeqNO;
	
	private ArrayList reqClauseList;
	
	// Added to include Save function from alert
	private String alertFlag1;
	
	// Added For LSDB_CR-61 on 04-Dec-08
	
	private int hdnCompGrpSeqNo;
	
	private int hdnCompGrpChnTypeSeqNo;
	
	private int hdnCompSeqNo;
	
	private int hdnCompChnTypeSeqNo;
	
	// Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151
	private String defaultFlag;
	
	private int noOfClaVersion;
	
	private String chngToFlag;
	//Adding for CR_94
	private ArrayList searchList;
	//Ends here CR_94 
	
	public int getHdnCompChnTypeSeqNo() {
		return hdnCompChnTypeSeqNo;
	}
	
	public void setHdnCompChnTypeSeqNo(int hdnCompChnTypeSeqNo) {
		this.hdnCompChnTypeSeqNo = hdnCompChnTypeSeqNo;
	}
	
	public int getHdnCompGrpChnTypeSeqNo() {
		return hdnCompGrpChnTypeSeqNo;
	}
	
	public void setHdnCompGrpChnTypeSeqNo(int hdnCompGrpChnTypeSeqNo) {
		this.hdnCompGrpChnTypeSeqNo = hdnCompGrpChnTypeSeqNo;
	}
	
	public int getHdnCompGrpSeqNo() {
		return hdnCompGrpSeqNo;
	}
	
	public void setHdnCompGrpSeqNo(int hdnCompGrpSeqNo) {
		this.hdnCompGrpSeqNo = hdnCompGrpSeqNo;
	}
	
	public int getHdnCompSeqNo() {
		return hdnCompSeqNo;
	}
	
	public void setHdnCompSeqNo(int hdnCompSeqNo) {
		this.hdnCompSeqNo = hdnCompSeqNo;
	}
	
	/**
	 * @return Returns the alertFlag1.
	 */
	public String getAlertFlag1() {
		return alertFlag1;
	}
	
	/**
	 * @param alertFlag1
	 *            The alertFlag1 to set.
	 */
	public void setAlertFlag1(String alertFlag1) {
		this.alertFlag1 = alertFlag1;
	}
	
	public String getHdnAdminComments() {
		return hdnAdminComments;
	}
	
	public void setHdnAdminComments(String hdnAdminComments) {
		this.hdnAdminComments = hdnAdminComments;
	}
	
	public String getAdminComments() {
		return adminComments;
	}
	
	public void setAdminComments(String adminComments) {
		this.adminComments = adminComments;
	}
	
	/**
	 * @return Returns the hdnSectionSeqNo.
	 */
	public int getHdnSectionSeqNo() {
		return hdnSectionSeqNo;
	}
	
	/**
	 * @param hdnSectionSeqNo
	 *            The hdnSectionSeqNo to set.
	 */
	public void setHdnSectionSeqNo(int hdnSectionSeqNo) {
		this.hdnSectionSeqNo = hdnSectionSeqNo;
	}
	
	/**
	 * @return Returns the applyModelFlag.
	 */
	public boolean isApplyModelFlag() {
		return applyModelFlag;
	}
	
	/**
	 * @param applyModelFlag
	 *            The applyModelFlag to set.
	 */
	public void setApplyModelFlag(boolean applyModelFlag) {
		this.applyModelFlag = applyModelFlag;
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
	 * @return Returns the compColourFlag.
	 */
	public String getCompColourFlag() {
		return compColourFlag;
	}
	
	/**
	 * @param compColourFlag
	 *            The compColourFlag to set.
	 */
	public void setCompColourFlag(String compColourFlag) {
		this.compColourFlag = compColourFlag;
	}
	
	/**
	 * @return Returns the standEquipmentSeqNo.
	 */
	public int getStandEquipmentSeqNo() {
		return standEquipmentSeqNo;
	}
	
	/**
	 * @param standEquipmentSeqNo
	 *            The standEquipmentSeqNo to set.
	 */
	public void setStandEquipmentSeqNo(int standEquipmentSeqNo) {
		this.standEquipmentSeqNo = standEquipmentSeqNo;
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
	 * @return Returns the compGrpNameVO.
	 */
	public RequestModelVO getCompGrpNameVO() {
		return compGrpNameVO;
	}
	
	/**
	 * @param compGrpNameVO
	 *            The compGrpNameVO to set.
	 */
	public void setCompGrpNameVO(RequestModelVO compGrpNameVO) {
		this.compGrpNameVO = compGrpNameVO;
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
	 * @return Returns the clauseVersions.
	 */
	public ArrayList getClauseVersions() {
		return clauseVersions;
	}
	
	/**
	 * @param clauseVersions
	 *            The clauseVersions to set.
	 */
	public void setClauseVersions(ArrayList clauseVersions) {
		this.clauseVersions = clauseVersions;
	}
	
	/**
	 * @return Returns the partOfClaDesc.
	 */
	public String[] getPartOfClaDesc() {
		return partOfClaDesc;
	}
	
	/**
	 * @param partOfClaDesc
	 *            The partOfClaDesc to set.
	 */
	public void setPartOfClaDesc(String[] partOfClaDesc) {
		this.partOfClaDesc = partOfClaDesc;
	}
	
	/**
	 * @return Returns the toStdEquipSeqNo.
	 */
	public int getToStdEquipSeqNo() {
		return toStdEquipSeqNo;
	}
	
	/**
	 * @param toStdEquipSeqNo
	 *            The toStdEquipSeqNo to set.
	 */
	public void setToStdEquipSeqNo(int toStdEquipSeqNo) {
		this.toStdEquipSeqNo = toStdEquipSeqNo;
	}
	
	/**
	 * @return Returns the reqClauseList.
	 */
	public ArrayList getReqClauseList() {
		return reqClauseList;
	}
	
	/**
	 * @param reqClauseList
	 *            The reqClauseList to set.
	 */
	public void setReqClauseList(ArrayList reqClauseList) {
		this.reqClauseList = reqClauseList;
	}
	
	/**
	 * @return Returns the changeTypeSeqNO.
	 */
	public int getChangeTypeSeqNO() {
		return changeTypeSeqNO;
	}
	
	/**
	 * @param changeTypeSeqNO
	 *            The changeTypeSeqNO to set.
	 */
	public void setChangeTypeSeqNO(int changeTypeSeqNO) {
		this.changeTypeSeqNO = changeTypeSeqNO;
	}
	
	/**
	 * @return Returns the toClaVerSeqNo.
	 */
	public int getToClaVerSeqNo() {
		return toClaVerSeqNo;
	}
	
	/**
	 * @param toClaVerSeqNo
	 *            The toClaVerSeqNo to set.
	 */
	public void setToClaVerSeqNo(int toClaVerSeqNo) {
		this.toClaVerSeqNo = toClaVerSeqNo;
	}
	
	/**
	 * @return Returns the claVerSeqNo.
	 */
	public int getClaVerSeqNo() {
		return claVerSeqNo;
	}
	
	/**
	 * @param claVerSeqNo
	 *            The claVerSeqNo to set.
	 */
	public void setClaVerSeqNo(int claVerSeqNo) {
		this.claVerSeqNo = claVerSeqNo;
	}
	
	/**
	 * @return Returns the parentClauseSeqNo.
	 */
	public int getParentClauseSeqNo() {
		return parentClauseSeqNo;
	}
	
	/**
	 * @param parentClauseSeqNo
	 *            The parentClauseSeqNo to set.
	 */
	public void setParentClauseSeqNo(int parentClauseSeqNo) {
		this.parentClauseSeqNo = parentClauseSeqNo;
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
	 * @return Returns the toclauseTableDataArray1.
	 */
	public String[] getToclauseTableDataArray1() {
		return toclauseTableDataArray1;
	}
	
	/**
	 * @param toclauseTableDataArray1
	 *            The toclauseTableDataArray1 to set.
	 */
	public void setToclauseTableDataArray1(String[] toclauseTableDataArray1) {
		this.toclauseTableDataArray1 = toclauseTableDataArray1;
	}
	
	/**
	 * @return Returns the toclauseTableDataArray2.
	 */
	public String[] getToclauseTableDataArray2() {
		return toclauseTableDataArray2;
	}
	
	/**
	 * @param toclauseTableDataArray2
	 *            The toclauseTableDataArray2 to set.
	 */
	public void setToclauseTableDataArray2(String[] toclauseTableDataArray2) {
		this.toclauseTableDataArray2 = toclauseTableDataArray2;
	}
	
	/**
	 * @return Returns the toclauseTableDataArray3.
	 */
	public String[] getToclauseTableDataArray3() {
		return toclauseTableDataArray3;
	}
	
	/**
	 * @param toclauseTableDataArray3
	 *            The toclauseTableDataArray3 to set.
	 */
	public void setToclauseTableDataArray3(String[] toclauseTableDataArray3) {
		this.toclauseTableDataArray3 = toclauseTableDataArray3;
	}
	
	/**
	 * @return Returns the toclauseTableDataArray4.
	 */
	public String[] getToclauseTableDataArray4() {
		return toclauseTableDataArray4;
	}
	
	/**
	 * @param toclauseTableDataArray4
	 *            The toclauseTableDataArray4 to set.
	 */
	public void setToclauseTableDataArray4(String[] toclauseTableDataArray4) {
		this.toclauseTableDataArray4 = toclauseTableDataArray4;
	}
	
	/**
	 * @return Returns the toclauseTableDataArray5.
	 */
	public String[] getToclauseTableDataArray5() {
		return toclauseTableDataArray5;
	}
	
	/**
	 * @param toclauseTableDataArray5
	 *            The toclauseTableDataArray5 to set.
	 */
	public void setToclauseTableDataArray5(String[] toclauseTableDataArray5) {
		this.toclauseTableDataArray5 = toclauseTableDataArray5;
	}
	
	/**
	 * @return Returns the applyToDefault.
	 */
	
	/**
	 * @return Returns the toClauseDesc.
	 */
	public String getToClauseDesc() {
		return toClauseDesc;
	}
	
	/**
	 * @param toClauseDesc
	 *            The toClauseDesc to set.
	 */
	public void setToClauseDesc(String toClauseDesc) {
		this.toClauseDesc = toClauseDesc;
	}
	
	/**
	 * @return Returns the toClauseNo.
	 */
	public String getToClauseNo() {
		return toClauseNo;
	}
	
	/**
	 * @param toClauseNo
	 *            The toClauseNo to set.
	 */
	public void setToClauseNo(String toClauseNo) {
		this.toClauseNo = toClauseNo;
	}
	
	/**
	 * @return Returns the toClaVerClauseNo.
	 */
	public String getToClaVerClauseNo() {
		return toClaVerClauseNo;
	}
	
	/**
	 * @param toClaVerClauseNo
	 *            The toClaVerClauseNo to set.
	 */
	public void setToClaVerClauseNo(String toClaVerClauseNo) {
		this.toClaVerClauseNo = toClaVerClauseNo;
	}
	
	/**
	 * @return Returns the toClaVerDesc.
	 */
	public String getToClaVerDesc() {
		return toClaVerDesc;
	}
	
	/**
	 * @param toClaVerDesc
	 *            The toClaVerDesc to set.
	 */
	public void setToClaVerDesc(String toClaVerDesc) {
		this.toClaVerDesc = toClaVerDesc;
	}
	
	/**
	 * @return Returns the toComments.
	 */
	public String getToComments() {
		return toComments;
	}
	
	/**
	 * @param toComments
	 *            The toComments to set.
	 */
	public void setToComments(String toComments) {
		this.toComments = toComments;
	}
	
	/**
	 * @return Returns the toComponents.
	 */
	public String getToComponents() {
		return toComponents;
	}
	
	/**
	 * @param toComponents
	 *            The toComponents to set.
	 */
	public void setToComponents(String toComponents) {
		this.toComponents = toComponents;
	}
	
	/**
	 * @return Returns the todwONumber.
	 */
	public int getTodwONumber() {
		return todwONumber;
	}
	
	/**
	 * @param todwONumber
	 *            The todwONumber to set.
	 */
	public void setTodwONumber(int todwONumber) {
		this.todwONumber = todwONumber;
	}
	
	/**
	 * @return Returns the toEdlNo.
	 */
	public String[] getToEdlNo() {
		return toEdlNo;
	}
	
	/**
	 * @param toEdlNo
	 *            The toEdlNo to set.
	 */
	public void setToEdlNo(String[] toEdlNo) {
		this.toEdlNo = toEdlNo;
	}
	
	/**
	 * @return Returns the toParentClaDesc.
	 */
	public String getToParentClaDesc() {
		return toParentClaDesc;
	}
	
	/**
	 * @param toParentClaDesc
	 *            The toParentClaDesc to set.
	 */
	public void setToParentClaDesc(String toParentClaDesc) {
		this.toParentClaDesc = toParentClaDesc;
	}
	
	/**
	 * @return Returns the toParentClauseNo.
	 */
	public String getToParentClauseNo() {
		return toParentClauseNo;
	}
	
	/**
	 * @param toParentClauseNo
	 *            The toParentClauseNo to set.
	 */
	public void setToParentClauseNo(String toParentClauseNo) {
		this.toParentClauseNo = toParentClauseNo;
	}
	
	/**
	 * @return Returns the toPartNumber.
	 */
	public int getToPartNumber() {
		return toPartNumber;
	}
	
	/**
	 * @param toPartNumber
	 *            The toPartNumber to set.
	 */
	public void setToPartNumber(int toPartNumber) {
		this.toPartNumber = toPartNumber;
	}
	
	/**
	 * @return Returns the toPartOfCode.
	 */
	public String[] getToPartOfCode() {
		return toPartOfCode;
	}
	
	/**
	 * @param toPartOfCode
	 *            The toPartOfCode to set.
	 */
	public void setToPartOfCode(String[] toPartOfCode) {
		this.toPartOfCode = toPartOfCode;
	}
	
	/**
	 * @return Returns the topriceBookNumber.
	 */
	public int getTopriceBookNumber() {
		return topriceBookNumber;
	}
	
	/**
	 * @param topriceBookNumber
	 *            The topriceBookNumber to set.
	 */
	public void setTopriceBookNumber(int topriceBookNumber) {
		this.topriceBookNumber = topriceBookNumber;
	}
	
	/**
	 * @return Returns the toRefEDLNo.
	 */
	public String[] getToRefEDLNo() {
		return toRefEDLNo;
	}
	
	/**
	 * @param toRefEDLNo
	 *            The toRefEDLNo to set.
	 */
	public void setToRefEDLNo(String[] toRefEDLNo) {
		this.toRefEDLNo = toRefEDLNo;
	}
	
	/**
	 * @return Returns the clauseTableDataArray1.
	 */
	public String[] getClauseTableDataArray1() {
		return clauseTableDataArray1;
	}
	
	/**
	 * @param clauseTableDataArray1
	 *            The clauseTableDataArray1 to set.
	 */
	public void setClauseTableDataArray1(String[] clauseTableDataArray1) {
		this.clauseTableDataArray1 = clauseTableDataArray1;
	}
	
	/**
	 * @return Returns the clauseTableDataArray2.
	 */
	public String[] getClauseTableDataArray2() {
		return clauseTableDataArray2;
	}
	
	/**
	 * @param clauseTableDataArray2
	 *            The clauseTableDataArray2 to set.
	 */
	public void setClauseTableDataArray2(String[] clauseTableDataArray2) {
		this.clauseTableDataArray2 = clauseTableDataArray2;
	}
	
	/**
	 * @return Returns the clauseTableDataArray3.
	 */
	public String[] getClauseTableDataArray3() {
		return clauseTableDataArray3;
	}
	
	/**
	 * @param clauseTableDataArray3
	 *            The clauseTableDataArray3 to set.
	 */
	public void setClauseTableDataArray3(String[] clauseTableDataArray3) {
		this.clauseTableDataArray3 = clauseTableDataArray3;
	}
	
	/**
	 * @return Returns the clauseTableDataArray4.
	 */
	public String[] getClauseTableDataArray4() {
		return clauseTableDataArray4;
	}
	
	/**
	 * @param clauseTableDataArray4
	 *            The clauseTableDataArray4 to set.
	 */
	public void setClauseTableDataArray4(String[] clauseTableDataArray4) {
		this.clauseTableDataArray4 = clauseTableDataArray4;
	}
	
	/**
	 * @return Returns the clauseTableDataArray5.
	 */
	public String[] getClauseTableDataArray5() {
		return clauseTableDataArray5;
	}
	
	/**
	 * @param clauseTableDataArray5
	 *            The clauseTableDataArray5 to set.
	 */
	public void setClauseTableDataArray5(String[] clauseTableDataArray5) {
		this.clauseTableDataArray5 = clauseTableDataArray5;
	}
	
	/**
	 * @return Returns the deleterow.
	 */
	public String getDeleterow() {
		return deleterow;
	}
	
	/**
	 * @param deleterow
	 *            The deleterow to set.
	 */
	public void setDeleterow(String deleterow) {
		this.deleterow = deleterow;
	}
	
	/**
	 * @return Returns the tableArrayData1.
	 */
	public String[] getTableArrayData1() {
		return tableArrayData1;
	}
	
	/**
	 * @param tableArrayData1
	 *            The tableArrayData1 to set.
	 */
	public void setTableArrayData1(String[] tableArrayData1) {
		this.tableArrayData1 = tableArrayData1;
	}
	
	/**
	 * @return Returns the hdnModelSeqNo.
	 */
	public int getHdnModelSeqNo() {
		return hdnModelSeqNo;
	}
	
	/**
	 * @param hdnModelSeqNo
	 *            The hdnModelSeqNo to set.
	 */
	public void setHdnModelSeqNo(int hdnModelSeqNo) {
		this.hdnModelSeqNo = hdnModelSeqNo;
	}
	
	/**
	 * @return Returns the hdnSubSectionSeqNo.
	 */
	public int getHdnSubSectionSeqNo() {
		return hdnSubSectionSeqNo;
	}
	
	/**
	 * @param hdnSubSectionSeqNo
	 *            The hdnSubSectionSeqNo to set.
	 */
	public void setHdnSubSectionSeqNo(int hdnSubSectionSeqNo) {
		this.hdnSubSectionSeqNo = hdnSubSectionSeqNo;
	}
	
	/**
	 * @return Returns the hdnClauseNum.
	 */
	public String getHdnClauseNum() {
		return hdnClauseNum;
	}
	
	/**
	 * @param hdnClauseNum
	 *            The hdnClauseNum to set.
	 */
	public void setHdnClauseNum(String hdnClauseNum) {
		this.hdnClauseNum = hdnClauseNum;
	}
	
	/**
	 * @return Returns the hdnClauseSeqNo.
	 */
	public int getHdnClauseSeqNo() {
		return hdnClauseSeqNo;
	}
	
	/**
	 * @param hdnClauseSeqNo
	 *            The hdnClauseSeqNo to set.
	 */
	public void setHdnClauseSeqNo(int hdnClauseSeqNo) {
		this.hdnClauseSeqNo = hdnClauseSeqNo;
	}
	
	/**
	 * @return Returns the hdnVersionNo.
	 */
	public int getHdnVersionNo() {
		return hdnVersionNo;
	}
	
	/**
	 * @param hdnVersionNo
	 *            The hdnVersionNo to set.
	 */
	public void setHdnVersionNo(int hdnVersionNo) {
		this.hdnVersionNo = hdnVersionNo;
	}
	
	/**
	 * @return Returns the versionNo.
	 */
	public int getVersionNo() {
		return versionNo;
	}
	
	/**
	 * @param versionNo
	 *            The versionNo to set.
	 */
	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}
	
	/**
	 * @return Returns the clauseNum.
	 */
	public String getClauseNum() {
		return clauseNum;
	}
	
	/**
	 * @param clauseNum
	 *            The clauseNum to set.
	 */
	public void setClauseNum(String clauseNum) {
		this.clauseNum = clauseNum;
	}
	
	/**
	 * @return Returns the clauseSeqNo.
	 */
	public int getClauseSeqNo() {
		return clauseSeqNo;
	}
	
	/**
	 * @param clauseSeqNo
	 *            The clauseSeqNo to set.
	 */
	public void setClauseSeqNo(int clauseSeqNo) {
		this.clauseSeqNo = clauseSeqNo;
	}
	
	/**
	 * @return Returns the componentVO.
	 */
	public ArrayList getComponentVO() {
		return componentVO;
	}
	
	/**
	 * @param componentVO
	 *            The componentVO to set.
	 */
	public void setComponentVO(ArrayList componentVO) {
		this.componentVO = componentVO;
	}
	
	/**
	 * @return Returns the modelName.
	 */
	public String getModelName() {
		return modelName;
	}
	
	/**
	 * @param modelName
	 *            The modelName to set.
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
	/**
	 * @return Returns the sectionName.
	 */
	public String getSectionName() {
		return sectionName;
	}
	
	/**
	 * @param sectionName
	 *            The sectionName to set.
	 */
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	
	/**
	 * @return Returns the subSectionName.
	 */
	public String getSubSectionName() {
		return subSectionName;
	}
	
	/**
	 * @param subSectionName
	 *            The subSectionName to set.
	 */
	public void setSubSectionName(String subSectionName) {
		this.subSectionName = subSectionName;
	}
	
	/**
	 * @return Returns the clauseDescForTextArea.
	 */
	public String getClauseDescForTextArea() {
		return clauseDescForTextArea;
	}
	
	/**
	 * @param clauseDescForTextArea
	 *            The clauseDescForTextArea to set.
	 */
	public void setClauseDescForTextArea(String clauseDescForTextArea) {
		this.clauseDescForTextArea = clauseDescForTextArea;
	}
	
	/**
	 * @return Returns the clauseNo.
	 */
	public String getClauseNo() {
		return clauseNo;
	}
	
	/**
	 * @param clauseNo
	 *            The clauseNo to set.
	 */
	public void setClauseNo(String clauseNo) {
		this.clauseNo = clauseNo;
	}
	
	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}
	
	/**
	 * @param comments
	 *            The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	/**
	 * @return Returns the dwONumber.
	 */
	public int getDwONumber() {
		return dwONumber;
	}
	
	/**
	 * @param dwONumber
	 *            The dwONumber to set.
	 */
	public void setDwONumber(int dwONumber) {
		this.dwONumber = dwONumber;
	}
	
	/**
	 * @return Returns the edlNo.
	 */
	public String[] getEdlNo() {
		return edlNo;
	}
	
	/**
	 * @param edlNo
	 *            The edlNo to set.
	 */
	public void setEdlNo(String[] edlNo) {
		this.edlNo = edlNo;
	}
	
	/**
	 * @return Returns the fromComps.
	 */
	public String getFromComps() {
		return fromComps;
	}
	
	/**
	 * @param fromComps
	 *            The fromComps to set.
	 */
	public void setFromComps(String fromComps) {
		this.fromComps = fromComps;
	}
	
	/**
	 * @return Returns the partNumber.
	 */
	public int getPartNumber() {
		return partNumber;
	}
	
	/**
	 * @param partNumber
	 *            The partNumber to set.
	 */
	public void setPartNumber(int partNumber) {
		this.partNumber = partNumber;
	}
	
	/**
	 * @return Returns the partOfclaSeqNo.
	 */
	public int[] getPartOfclaSeqNo() {
		return partOfclaSeqNo;
	}
	
	/**
	 * @param partOfclaSeqNo
	 *            The partOfclaSeqNo to set.
	 */
	public void setPartOfclaSeqNo(int[] partOfclaSeqNo) {
		this.partOfclaSeqNo = partOfclaSeqNo;
	}
	
	/**
	 * @return Returns the partOfCode.
	 */
	public String[] getPartOfCode() {
		return partOfCode;
	}
	
	/**
	 * @param partOfCode
	 *            The partOfCode to set.
	 */
	public void setPartOfCode(String[] partOfCode) {
		this.partOfCode = partOfCode;
	}
	
	/**
	 * @return Returns the partOfSeqNo.
	 */
	public int[] getPartOfSeqNo() {
		return partOfSeqNo;
	}
	
	/**
	 * @param partOfSeqNo
	 *            The partOfSeqNo to set.
	 */
	public void setPartOfSeqNo(int[] partOfSeqNo) {
		this.partOfSeqNo = partOfSeqNo;
	}
	
	/**
	 * @return Returns the priceBookNumber.
	 */
	public int getPriceBookNumber() {
		return priceBookNumber;
	}
	
	/**
	 * @param priceBookNumber
	 *            The priceBookNumber to set.
	 */
	public void setPriceBookNumber(int priceBookNumber) {
		this.priceBookNumber = priceBookNumber;
	}
	
	/**
	 * @return Returns the refEDLNo.
	 */
	public String[] getRefEDLNo() {
		return RefEDLNo;
	}
	
	/**
	 * @param refEDLNo
	 *            The refEDLNo to set.
	 */
	public void setRefEDLNo(String[] refEDLNo) {
		RefEDLNo = refEDLNo;
	}
	
	/**
	 * @return Returns the revisionNo.
	 */
	public String getRevisionNo() {
		return revisionNo;
	}
	
	/**
	 * @param revisionNo
	 *            The revisionNo to set.
	 */
	public void setRevisionNo(String revisionNo) {
		this.revisionNo = revisionNo;
	}
	
	/**
	 * @return Returns the standardEquipmentSeqNo.
	 */
	public int getStandardEquipmentSeqNo() {
		return standardEquipmentSeqNo;
	}
	
	/**
	 * @param standardEquipmentSeqNo
	 *            The standardEquipmentSeqNo to set.
	 */
	public void setStandardEquipmentSeqNo(int standardEquipmentSeqNo) {
		this.standardEquipmentSeqNo = standardEquipmentSeqNo;
	}
	
	/**
	 * @return Returns the stdEquipmentList.
	 */
	public ArrayList getStdEquipmentList() {
		return stdEquipmentList;
	}
	
	/**
	 * @param stdEquipmentList
	 *            The stdEquipmentList to set.
	 */
	public void setStdEquipmentList(ArrayList stdEquipmentList) {
		this.stdEquipmentList = stdEquipmentList;
	}
	
	/**
	 * @return Returns the clauseList.
	 */
	public ArrayList getClauseList() {
		return clauseList;
	}
	
	/**
	 * @param clauseList
	 *            The clauseList to set.
	 */
	public void setClauseList(ArrayList clauseList) {
		this.clauseList = clauseList;
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
	 * @return Returns the requestList.
	 */
	public ArrayList getRequestList() {
		return requestList;
	}
	
	/**
	 * @param requestList
	 *            The requestList to set.
	 */
	public void setRequestList(ArrayList requestList) {
		this.requestList = requestList;
	}
	
	/**
	 * @return Returns the toComponentSeqNo.
	 */
	public String getToComponentSeqNo() {
		return toComponentSeqNo;
	}
	
	/**
	 * @param toComponentSeqNo
	 *            The toComponentSeqNo to set.
	 */
	public void setToComponentSeqNo(String toComponentSeqNo) {
		this.toComponentSeqNo = toComponentSeqNo;
	}
	
	/**
	 * @return Returns the toDefaultFlag.
	 */
	public String getToDefaultFlag() {
		return toDefaultFlag;
	}
	
	/**
	 * @param toDefaultFlag
	 *            The toDefaultFlag to set.
	 */
	public void setToDefaultFlag(String toDefaultFlag) {
		this.toDefaultFlag = toDefaultFlag;
	}
	
	public String getRequestDesc() {
		return requestDesc;
	}
	
	public void setRequestDesc(String requestDesc) {
		this.requestDesc = requestDesc;
	}

	/**
	 * @return Returns the defaultFlag.
	 */
	public String getDefaultFlag() {
		return defaultFlag;
	}

	/**
	 * @param defaultFlag The defaultFlag to set.
	 */
	public void setDefaultFlag(String defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	/**
	 * @return Returns the noOfClaVersion.
	 */
	public int getNoOfClaVersion() {
		return noOfClaVersion;
	}

	/**
	 * @param noOfClaVersion The noOfClaVersion to set.
	 */
	public void setNoOfClaVersion(int noOfClaVersion) {
		this.noOfClaVersion = noOfClaVersion;
	}

	/**
	 * @return Returns the chngToFlag.
	 */
	public String getChngToFlag() {
		return chngToFlag;
	}

	/**
	 * @param chngToFlag The chngToFlag to set.
	 */
	public void setChngToFlag(String chngToFlag) {
		this.chngToFlag = chngToFlag;
	}
	//Added for CR_94
	public ArrayList getSearchList() {
		return searchList;
	}

	public void setSearchList(ArrayList searchList) {
		this.searchList = searchList;
	}
	//CR_94 Ends here
}