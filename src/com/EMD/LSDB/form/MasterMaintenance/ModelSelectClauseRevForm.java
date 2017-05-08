/**
 * 
 */
package com.EMD.LSDB.form.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

/**
 * @author VV49326
 * 
 */
public class ModelSelectClauseRevForm extends EMDForm {
	
	private int modelseqno;
	
	private int clauseNo;
	
	private int subSectionSeqNo;
	
	private int clauseSeqNo;
	
	private int hdnClauseSeqNo = 0;
	
	private int versionNo = 0;
	
	private int hdnClauseVersionNo = 0;
	
	private int hdPreSelectedModel;
	
	private String hdnModelName = null;
	
	private ArrayList clauseVersions = null;
	
	private ArrayList listModels = null;
	
	private String defaultFlag = null;
	
	private String reason = null;
	
	private String clauseDes = null;
	
	private ArrayList clauseList;
	
	private ArrayList stdEquipmentList;
	
	private int standardEquipmentSeqNo;
	
	private String standardEquipmentDesc;
	
	private int parentClauseSeqNo;
	
	private String clauseDesc;
	
	private String clauseDescForTextArea;
	
	private String[] edlNo;
	
	private String[] RefEDLNo;
	
	private int[] partOfSeqNo;
	
	private String[] partOfCode;
	
	private String revisionNo;
	
	private int dwONumber;
	
	private int partNumber;
	
	private int priceBookNumber;
	
	private String comments;
	
	private String[] clauseTableDataArray1;
	
	private String[] clauseTableDataArray2;
	
	private String[] clauseTableDataArray3;
	
	private String[] clauseTableDataArray4;
	
	private String[] clauseTableDataArray5;
	
	private String deleterow;
	
	private String[] tableArrayData1;
	
	// private boolean defaultFlag;
	
	private String applyToDefault;
	
	/***************************************************************************
	 * New String array is added to test LSDB_CR-48
	 */
	private String[] partOfclaSeqNo;
	
	// Added for LSDB_CR_46 Pm&I change
	private int prevSpecType;
	
	/**
	 * @return Returns the preSpecType.
	 */
	public int getPrevSpecType() {
		return prevSpecType;
	}
	
	/**
	 * @param preSpecType
	 *            The preSpecType to set.
	 */
	public void setPrevSpecType(int prevSpecType) {
		this.prevSpecType = prevSpecType;
	}
	
	/**
	 * @return Returns the partOfclaSeqNo.
	 */
	public String[] getPartOfclaSeqNo() {
		return partOfclaSeqNo;
	}
	
	/**
	 * @param partOfclaSeqNo
	 *            The partOfclaSeqNo to set.
	 */
	public void setPartOfclaSeqNo(String[] partOfclaSeqNo) {
		this.partOfclaSeqNo = partOfclaSeqNo;
	}
	
	/**
	 * @return Returns the applyToDefault.
	 */
	public String getApplyToDefault() {
		return applyToDefault;
	}
	
	/**
	 * @param applyToDefault
	 *            The applyToDefault to set.
	 */
	public void setApplyToDefault(String applyToDefault) {
		this.applyToDefault = applyToDefault;
	}
	
	/**
	 * @return Returns the clauseDesc.
	 */
	public String getClauseDesc() {
		return clauseDesc;
	}
	
	/**
	 * @param clauseDesc
	 *            The clauseDesc to set.
	 */
	public void setClauseDesc(String clauseDesc) {
		this.clauseDesc = clauseDesc;
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
	 * @return Returns the standardEquipmentDesc.
	 */
	public String getStandardEquipmentDesc() {
		return standardEquipmentDesc;
	}
	
	/**
	 * @param standardEquipmentDesc
	 *            The standardEquipmentDesc to set.
	 */
	public void setStandardEquipmentDesc(String standardEquipmentDesc) {
		this.standardEquipmentDesc = standardEquipmentDesc;
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
	 * @return Returns the clauseDes.
	 */
	public String getClauseDes() {
		return clauseDes;
	}
	
	/**
	 * @param clauseDes
	 *            The clauseDes to set.
	 */
	public void setClauseDes(String clauseDes) {
		this.clauseDes = clauseDes;
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
	 * @return Returns the defaultFlag.
	 */
	public String getDefaultFlag() {
		return defaultFlag;
	}
	
	/**
	 * @param defaultFlag
	 *            The defaultFlag to set.
	 */
	public void setDefaultFlag(String defaultFlag) {
		this.defaultFlag = defaultFlag;
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
	 * @return Returns the listModels.
	 */
	public ArrayList getListModels() {
		return listModels;
	}
	
	/**
	 * @param listModels
	 *            The listModels to set.
	 */
	public void setListModels(ArrayList listModels) {
		this.listModels = listModels;
	}
	
	/**
	 * @return Returns the modelseqno.
	 */
	public int getModelseqno() {
		return modelseqno;
	}
	
	/**
	 * @param modelseqno
	 *            The modelseqno to set.
	 */
	public void setModelseqno(int modelseqno) {
		this.modelseqno = modelseqno;
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
	 * @return Returns the hdnClauseVersionNo.
	 */
	public int getHdnClauseVersionNo() {
		return hdnClauseVersionNo;
	}
	
	/**
	 * @param hdnClauseVersionNo
	 *            The hdnClauseVersionNo to set.
	 */
	public void setHdnClauseVersionNo(int hdnClauseVersionNo) {
		this.hdnClauseVersionNo = hdnClauseVersionNo;
	}
	
	/**
	 * @return Returns the hdnModelName.
	 */
	public String getHdnModelName() {
		return hdnModelName;
	}
	
	/**
	 * @param hdnModelName
	 *            The hdnModelName to set.
	 */
	public void setHdnModelName(String hdnModelName) {
		this.hdnModelName = hdnModelName;
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
	 * @return Returns the clauseNo.
	 */
	public int getClauseNo() {
		return clauseNo;
	}
	
	/**
	 * @param clauseNo
	 *            The clauseNo to set.
	 */
	public void setClauseNo(int clauseNo) {
		this.clauseNo = clauseNo;
	}
	
	/**
	 * @return Returns the hdPreSelectedModel.
	 */
	public int getHdPreSelectedModel() {
		return hdPreSelectedModel;
	}
	
	/**
	 * @param hdPreSelectedModel The hdPreSelectedModel to set.
	 */
	public void setHdPreSelectedModel(int hdPreSelectedModel) {
		this.hdPreSelectedModel = hdPreSelectedModel;
	}
	
}