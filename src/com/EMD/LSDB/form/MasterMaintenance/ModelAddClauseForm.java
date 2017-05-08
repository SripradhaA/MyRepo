package com.EMD.LSDB.form.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

/*******************************************************************************************
 * @author  Satyam Computer Services Ltd  
 * @version 1.0  
 * This class has the form fields for the Clause 
 ******************************************************************************************/

/***************************************************************************
 * ------------------------------------------------------------------------------------------------------
 *     Date         version  modify by         comments                              Remarks 
 * 19/01/2010        1.0      SD41630   Added new mehtod for view characterisitic     Added for CR_81
 * 													   group Flag.   
 * ------------------------------------------------------------------------------------------------------
 *     Date         version  modify by         comments                              Remarks 
 * 12/02/2010        1.0      RR68151   Added various fields to accomodate	     	Added for CR_83 
 * 										 merging of screens
 * 12/05/2010        1.1      SD41630   Added charcombntn value to list 	     	Added for CR_85
 * 										and pass hdn fields vale from action 
 * 										to anther action  
 * 02/07/2010        1.2      SD41630   Added  rowIndexList and childflage  	Added for CR_88
 * 										fields  
 * 15/03/2011       1.3     SD41630   Added  leadComponentVO                	Added for CR_97
 * 										
 * 										 
  **************************************************************************/
public class ModelAddClauseForm extends EMDForm {
	public int clauseSeqNo;
	
	public int clauseNo;
	
	public int parentClauseSeqNo;
	
	public int versionNo;
	
	public String clauseDesc; 
	
	public String[] edlNo;
	
	public String[] RefEDLNo;
	
	public int[] partOfSeqNo;
	
	public String[] partOfCode;
	
	public String orderNo;
	
	public String revisionNo;
	
	public int dwONumber;
	
	public int partNumber;
	
	public int priceBookNumber;
	
	public String comments;
	
	public boolean defaultFlag;
	
	public String reason;
	
	public int modelSeqNo;
	
	public String modelName;
	
	public ArrayList modelList;
	
	public int sectionSeqNo;
	
	public String sectionSeqName;
	
	public ArrayList sectionList;
	
	public int subSectionSeqNo;
	
	public String subSectionSeqName;
	
	public ArrayList subSectionList;
	
	public ArrayList subSectionSeqCode;
	
	public int customerSeqNo;
	
	public String customerName;
	
	public ArrayList customerList;
	
	public String modifiedBy;
	
	public String modifiedDate;
	
	public ArrayList componentVO;
	
	public String[] clauseTableDataArray1;
	
	public String[] clauseTableDataArray2;
	
	public String[] clauseTableDataArray3;
	
	public String[] clauseTableDataArray4;
	
	public String[] clauseTableDataArray5;
	
	public int standardEquipmentSeqNo;
	
	public String selectedClause;
	
	public String standardEquipmentDesc;
	
	public ArrayList standardEquipmentList;
	
	public char headerFlag;
	
	public int rowIndex;
	
	public int[] componentfrom;
	
	public String componentSeqNo;
	
	private ArrayList tableClauseData;
	
	private int compGroupSeqNo;
	
	/**** Added For Attach To Clause CR *******/
	
	private ArrayList compGroupList;
	
	private int leadComponentSeqNo;

	/*
	 * Added for LSDB_CR-48
	 * on 30-July-08
	 * by ps57222
	 */
	
	private String[] partOfclaSeqNo;
	
//	Added For CR_81 on 24-Dec-09 by sd41630 started here 
	public boolean selectCGCFlag;
//	Added For CR_81 on 24-Dec-09 by sd41630 Ends here
	
	//Added For CR_83 on 12-Feb-10 by RR68151
	public String modifyClauseFlag;
	//Added For CR_88 on 28-jun-10 by sd41630
	//childflage value  "N" for without childclause and "Y" for All clause  
	public String childFlag;
//	rowIndexList having new rearrange list clause seq nos 
	public String rowIndexList;
	//CR_88 lines are ends gere
	public int hdnClauseSeqNo;
	
	public ArrayList clauseList;
	
	public ArrayList clauseVersions = null;
	
	public int hdnClauseVersionNo;
	
	private String deleterow;
	
	private String clauseDescForTextArea;
	
	private String applyToDefault;
	//CR_85
	public int hdnCombntnSeqNo;
	
	public int hdnCharClaSeqNo;
	//CR 88
	public String hdnCombntnSeqNoArr;
	private ArrayList charCombnList;
	
	private ArrayList charGrpCombntnList;
	//Added For CR_83 on 12-Feb-10 by RR68151 - Ends here
	//Added for CR_94
	private ArrayList parentClaList;
	private ArrayList childClaList;
	//CR_94 Ends here
	//Added For CR_97 on march 2011 by sd41630, lead component clauses
	private ArrayList leadComponentVO;
	
	private int leadClauseSeqNo;
	
	//CR_85
	//Added for CR-113
	private String hdnSelectedSpecType;
	private String hdnSelectedModel;
	private String hdnSelectedSection;
	private String hdnSelectedSubSection;
	private boolean showLatSpecFlag;
	private String hdnShowLatSpecFlag = null;
	//Added for CR-113 Ends Here
	
	//Added for CR_114
	private int mapAppendixImg;
	private int appendixImgSeqNo;
	//Added for CR_114 Ends Here
	
	//Added for CR-118
	private String SpecTypeSelectFlag = null;
	
	//Added for CR-121
	public String leadSubClaExistsFlag  = null;
	
	//Added for CR-134
	private String markClaReason;
	private String userMarkFlag;
	
	
	public String getMarkClaReason() {
		return markClaReason;
	}

	public void setMarkClaReason(String markClaReason) {
		this.markClaReason = markClaReason;
	}

	public String getUserMarkFlag() {
		return userMarkFlag;
	}

	public void setUserMarkFlag(String userMarkFlag) {
		this.userMarkFlag = userMarkFlag;
	}

	/**
	 * @return Returns the leadComponentSeqNo.
	 */
	public int getLeadComponentSeqNo() {
		return leadComponentSeqNo;
	}
	
	/**
	 * @param leadComponentSeqNo The leadComponentSeqNo to set.
	 */
	public void setLeadComponentSeqNo(int leadComponentSeqNo) {
		this.leadComponentSeqNo = leadComponentSeqNo;
	}
	
	/**
	 * @return Returns the compGroupSeqNo.
	 */
	public int getCompGroupSeqNo() {
		return compGroupSeqNo;
	}
	
	/**
	 * @param compGroupSeqNo The compGroupSeqNo to set.
	 */
	public void setCompGroupSeqNo(int compGroupSeqNo) {
		this.compGroupSeqNo = compGroupSeqNo;
	}
	
	/**
	 * @return Returns the compGroupList.
	 */
	public ArrayList getCompGroupList() {
		return compGroupList;
	}
	
	/**
	 * @param compGroupList The compGroupList to set.
	 */
	public void setCompGroupList(ArrayList compGroupList) {
		this.compGroupList = compGroupList;
	}
	
	/**
	 * @return Returns the tableClauseData.
	 */
	public ArrayList getTableClauseData() {
		return tableClauseData;
	}
	
	/**
	 * @param tableClauseData The tableClauseData to set.
	 */
	public void setTableClauseData(ArrayList tableClauseData) {
		this.tableClauseData = tableClauseData;
	}
	
	/**
	 * @return Returns the clauseDesc.
	 */
	public String getClauseDesc() {
		return clauseDesc;
	}
	
	/**
	 * @param clauseDesc The clauseDesc to set.
	 */
	public void setClauseDesc(String clauseDesc) {
		this.clauseDesc = clauseDesc;
	}
	
	/**
	 * @return Returns the clauseNo.
	 */
	public int getClauseNo() {
		return clauseNo;
	}
	
	/**
	 * @param clauseNo The clauseNo to set.
	 */
	public void setClauseNo(int clauseNo) {
		this.clauseNo = clauseNo;
	}
	
	/**
	 * @return Returns the clauseSeqNo.
	 */
	public int getClauseSeqNo() {
		return clauseSeqNo;
	}
	
	/**
	 * @param clauseSeqNo The clauseSeqNo to set.
	 */
	public void setClauseSeqNo(int clauseSeqNo) {
		this.clauseSeqNo = clauseSeqNo;
	}
	
	/**
	 * @return Returns the clauseTableDataArray1.
	 */
	public String[] getClauseTableDataArray1() {
		return clauseTableDataArray1;
	}
	
	/**
	 * @param clauseTableDataArray1 The clauseTableDataArray1 to set.
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
	 * @param clauseTableDataArray2 The clauseTableDataArray2 to set.
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
	 * @param clauseTableDataArray3 The clauseTableDataArray3 to set.
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
	 * @param clauseTableDataArray4 The clauseTableDataArray4 to set.
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
	 * @param clauseTableDataArray5 The clauseTableDataArray5 to set.
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
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	/**
	 * @return Returns the componentfrom.
	 */
	public int[] getComponentfrom() {
		return componentfrom;
	}
	
	/**
	 * @param componentfrom The componentfrom to set.
	 */
	public void setComponentfrom(int[] componentfrom) {
		this.componentfrom = componentfrom;
	}
	
	/**
	 * @return Returns the componentSeqNo.
	 */
	public String getComponentSeqNo() {
		return componentSeqNo;
	}
	
	/**
	 * @param componentSeqNo The componentSeqNo to set.
	 */
	public void setComponentSeqNo(String componentSeqNo) {
		this.componentSeqNo = componentSeqNo;
	}
	
	/**
	 * @return Returns the componentVO.
	 */
	public ArrayList getComponentVO() {
		return componentVO;
	}
	
	/**
	 * @param componentVO The componentVO to set.
	 */
	public void setComponentVO(ArrayList componentVO) {
		this.componentVO = componentVO;
	}
	
	/**
	 * @return Returns the customerList.
	 */
	public ArrayList getCustomerList() {
		return customerList;
	}
	
	/**
	 * @param customerList The customerList to set.
	 */
	public void setCustomerList(ArrayList customerList) {
		this.customerList = customerList;
	}
	
	/**
	 * @return Returns the customerName.
	 */
	public String getCustomerName() {
		return customerName;
	}
	
	/**
	 * @param customerName The customerName to set.
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	/**
	 * @return Returns the customerSeqNo.
	 */
	public int getCustomerSeqNo() {
		return customerSeqNo;
	}
	
	/**
	 * @param customerSeqNo The customerSeqNo to set.
	 */
	public void setCustomerSeqNo(int customerSeqNo) {
		this.customerSeqNo = customerSeqNo;
	}
	
	/**
	 * @return Returns the defaultFlag.
	 */
	public boolean isDefaultFlag() {
		return defaultFlag;
	}
	
	/**
	 * @param defaultFlag The defaultFlag to set.
	 */
	public void setDefaultFlag(boolean defaultFlag) {
		this.defaultFlag = defaultFlag;
	}
	
	/**
	 * @return Returns the dwONumber.
	 */
	public int getDwONumber() {
		return dwONumber;
	}
	
	/**
	 * @param dwONumber The dwONumber to set.
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
	 * @param edlNo The edlNo to set.
	 */
	public void setEdlNo(String[] edlNo) {
		this.edlNo = edlNo;
	}
	
	/**
	 * @return Returns the headerFlag.
	 */
	public char getHeaderFlag() {
		return headerFlag;
	}
	
	/**
	 * @param headerFlag The headerFlag to set.
	 */
	public void setHeaderFlag(char headerFlag) {
		this.headerFlag = headerFlag;
	}
	
	/**
	 * @return Returns the modelList.
	 */
	public ArrayList getModelList() {
		return modelList;
	}
	
	/**
	 * @param modelList The modelList to set.
	 */
	public void setModelList(ArrayList modelList) {
		this.modelList = modelList;
	}
	
	/**
	 * @return Returns the modelName.
	 */
	public String getModelName() {
		return modelName;
	}
	
	/**
	 * @param modelName The modelName to set.
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
	/**
	 * @return Returns the modelSeqNo.
	 */
	public int getModelSeqNo() {
		return modelSeqNo;
	}
	
	/**
	 * @param modelSeqNo The modelSeqNo to set.
	 */
	public void setModelSeqNo(int modelSeqNo) {
		this.modelSeqNo = modelSeqNo;
	}
	
	/**
	 * @return Returns the modifiedBy.
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}
	
	/**
	 * @param modifiedBy The modifiedBy to set.
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	/**
	 * @return Returns the modifiedDate.
	 */
	public String getModifiedDate() {
		return modifiedDate;
	}
	
	/**
	 * @param modifiedDate The modifiedDate to set.
	 */
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	/**
	 * @return Returns the orderNo.
	 */
	public String getOrderNo() {
		return orderNo;
	}
	
	/**
	 * @param orderNo The orderNo to set.
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	/**
	 * @return Returns the parentClauseSeqNo.
	 */
	public int getParentClauseSeqNo() {
		return parentClauseSeqNo;
	}
	
	/**
	 * @param parentClauseSeqNo The parentClauseSeqNo to set.
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
	 * @param partNumber The partNumber to set.
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
	 * @param partOfCode The partOfCode to set.
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
	 * @param partOfSeqNo The partOfSeqNo to set.
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
	 * @param priceBookNumber The priceBookNumber to set.
	 */
	public void setPriceBookNumber(int priceBookNumber) {
		this.priceBookNumber = priceBookNumber;
	}
	
	/**
	 * @return Returns the reason.
	 */
	public String getReason() {
		return reason;
	}
	
	/**
	 * @param reason The reason to set.
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	/**
	 * @return Returns the refEDLNo.
	 */
	public String[] getRefEDLNo() {
		return RefEDLNo;
	}
	
	/**
	 * @param refEDLNo The refEDLNo to set.
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
	 * @param revisionNo The revisionNo to set.
	 */
	public void setRevisionNo(String revisionNo) {
		this.revisionNo = revisionNo;
	}
	
	/**
	 * @return Returns the rowIndex.
	 */
	public int getRowIndex() {
		return rowIndex;
	}
	
	/**
	 * @param rowIndex The rowIndex to set.
	 */
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}
	
	/**
	 * @return Returns the sectionList.
	 */
	public ArrayList getSectionList() {
		return sectionList;
	}
	
	/**
	 * @param sectionList The sectionList to set.
	 */
	public void setSectionList(ArrayList sectionList) {
		this.sectionList = sectionList;
	}
	
	/**
	 * @return Returns the sectionSeqName.
	 */
	public String getSectionSeqName() {
		return sectionSeqName;
	}
	
	/**
	 * @param sectionSeqName The sectionSeqName to set.
	 */
	public void setSectionSeqName(String sectionSeqName) {
		this.sectionSeqName = sectionSeqName;
	}
	
	/**
	 * @return Returns the sectionSeqNo.
	 */
	public int getSectionSeqNo() {
		return sectionSeqNo;
	}
	
	/**
	 * @param sectionSeqNo The sectionSeqNo to set.
	 */
	public void setSectionSeqNo(int sectionSeqNo) {
		this.sectionSeqNo = sectionSeqNo;
	}
	
	/**
	 * @return Returns the selectedClause.
	 */
	public String getSelectedClause() {
		return selectedClause;
	}
	
	/**
	 * @param selectedClause The selectedClause to set.
	 */
	public void setSelectedClause(String selectedClause) {
		this.selectedClause = selectedClause;
	}
	
	/**
	 * @return Returns the standardEquipmentDesc.
	 */
	public String getStandardEquipmentDesc() {
		return standardEquipmentDesc;
	}
	
	/**
	 * @param standardEquipmentDesc The standardEquipmentDesc to set.
	 */
	public void setStandardEquipmentDesc(String standardEquipmentDesc) {
		this.standardEquipmentDesc = standardEquipmentDesc;
	}
	
	/**
	 * @return Returns the standardEquipmentList.
	 */
	public ArrayList getStandardEquipmentList() {
		return standardEquipmentList;
	}
	
	/**
	 * @param standardEquipmentList The standardEquipmentList to set.
	 */
	public void setStandardEquipmentList(ArrayList standardEquipmentList) {
		this.standardEquipmentList = standardEquipmentList;
	}
	
	/**
	 * @return Returns the standardEquipmentSeqNo.
	 */
	public int getStandardEquipmentSeqNo() {
		return standardEquipmentSeqNo;
	}
	
	/**
	 * @param standardEquipmentSeqNo The standardEquipmentSeqNo to set.
	 */
	public void setStandardEquipmentSeqNo(int standardEquipmentSeqNo) {
		this.standardEquipmentSeqNo = standardEquipmentSeqNo;
	}
	
	/**
	 * @return Returns the subSectionList.
	 */
	public ArrayList getSubSectionList() {
		return subSectionList;
	}
	
	/**
	 * @param subSectionList The subSectionList to set.
	 */
	public void setSubSectionList(ArrayList subSectionList) {
		this.subSectionList = subSectionList;
	}
	
	/**
	 * @return Returns the subSectionSeqCode.
	 */
	public ArrayList getSubSectionSeqCode() {
		return subSectionSeqCode;
	}
	
	/**
	 * @param subSectionSeqCode The subSectionSeqCode to set.
	 */
	public void setSubSectionSeqCode(ArrayList subSectionSeqCode) {
		this.subSectionSeqCode = subSectionSeqCode;
	}
	
	/**
	 * @return Returns the subSectionSeqName.
	 */
	public String getSubSectionSeqName() {
		return subSectionSeqName;
	}
	
	/**
	 * @param subSectionSeqName The subSectionSeqName to set.
	 */
	public void setSubSectionSeqName(String subSectionSeqName) {
		this.subSectionSeqName = subSectionSeqName;
	}
	
	/**
	 * @return Returns the subSectionSeqNo.
	 */
	public int getSubSectionSeqNo() {
		return subSectionSeqNo;
	}
	
	/**
	 * @param subSectionSeqNo The subSectionSeqNo to set.
	 */
	public void setSubSectionSeqNo(int subSectionSeqNo) {
		this.subSectionSeqNo = subSectionSeqNo;
	}
	
	/**
	 * @return Returns the versionNo.
	 */
	public int getVersionNo() {
		return versionNo;
	}
	
	/**
	 * @param versionNo The versionNo to set.
	 */
	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}
	
	/**
	 * @return Returns the partOfclaSeqNo.
	 */
	public String[] getPartOfclaSeqNo() {
		return partOfclaSeqNo;
	}
	
	/**
	 * @param partOfclaSeqNo The partOfclaSeqNo to set.
	 */
	public void setPartOfclaSeqNo(String[] partOfclaSeqNo) {
		this.partOfclaSeqNo = partOfclaSeqNo;
	}
	
	/**
	 * @param SelectCGCFlag set the Characteristic Group Clause flag.
	 */
//	Added For CR_81 on 24-Dec-09 by sd41630 started here

	public boolean isSelectCGCFlag() {
		return selectCGCFlag;
	}

	public void setSelectCGCFlag(boolean selectCGCFlag) {
		this.selectCGCFlag = selectCGCFlag;
	}

	/**
	 * @return Returns the modifyClauseFlag.
	 */
	public String getModifyClauseFlag() {
		return modifyClauseFlag;
	}

	/**
	 * @param modifyClauseFlag The modifyClauseFlag to set.
	 */
	public void setModifyClauseFlag(String modifyClauseFlag) {
		this.modifyClauseFlag = modifyClauseFlag;
	}

	/**
	 * @return Returns the clauseList.
	 */
	public ArrayList getClauseList() {
		return clauseList;
	}

	/**
	 * @param clauseList The clauseList to set.
	 */
	public void setClauseList(ArrayList clauseList) {
		this.clauseList = clauseList;
	}

	/**
	 * @return Returns the clauseVersions.
	 */
	public ArrayList getClauseVersions() {
		return clauseVersions;
	}

	/**
	 * @param clauseVersions The clauseVersions to set.
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
	 * @param hdnClauseSeqNo The hdnClauseSeqNo to set.
	 */
	public void setHdnClauseSeqNo(int hdnClauseSeqNo) {
		this.hdnClauseSeqNo = hdnClauseSeqNo;
	}

	/**
	 * @return Returns the hdnClauseVersionNo.
	 */
	public int getHdnClauseVersionNo() {
		return hdnClauseVersionNo;
	}

	/**
	 * @param hdnClauseVersionNo The hdnClauseVersionNo to set.
	 */
	public void setHdnClauseVersionNo(int hdnClauseVersionNo) {
		this.hdnClauseVersionNo = hdnClauseVersionNo;
	}

	/**
	 * @return Returns the deleterow.
	 */
	public String getDeleterow() {
		return deleterow;
	}

	/**
	 * @param deleterow The deleterow to set.
	 */
	public void setDeleterow(String deleterow) {
		this.deleterow = deleterow;
	}

	/**
	 * @return Returns the applyToDefault.
	 */
	public String getApplyToDefault() {
		return applyToDefault;
	}

	/**
	 * @param applyToDefault The applyToDefault to set.
	 */
	public void setApplyToDefault(String applyToDefault) {
		this.applyToDefault = applyToDefault;
	}

	/**
	 * @return Returns the clauseDescForTextArea.
	 */
	public String getClauseDescForTextArea() {
		return clauseDescForTextArea;
	}

	/**
	 * @param clauseDescForTextArea The clauseDescForTextArea to set.
	 */
	public void setClauseDescForTextArea(String clauseDescForTextArea) {
		this.clauseDescForTextArea = clauseDescForTextArea;
	}

	

	public ArrayList getCharGrpCombntnList() {
		return charGrpCombntnList;
	}

	public void setCharGrpCombntnList(ArrayList charGrpCombntnList) {
		this.charGrpCombntnList = charGrpCombntnList;
	}


	public int getHdnCharClaSeqNo() {
		return hdnCharClaSeqNo;
	}

	public void setHdnCharClaSeqNo(int hdnCharClaSeqNo) {
		this.hdnCharClaSeqNo = hdnCharClaSeqNo;
	}

	public int getHdnCombntnSeqNo() {
		return hdnCombntnSeqNo;
	}

	public void setHdnCombntnSeqNo(int hdnCombntnSeqNo) {
		this.hdnCombntnSeqNo = hdnCombntnSeqNo;
	}


	public String getChildFlag() {
		return childFlag;
	}

	public void setChildFlag(String childFlag) {
		this.childFlag = childFlag;
	}



	public String getRowIndexList() {
		return rowIndexList;
	}

	public void setRowIndexList(String rowIndexList) {
		this.rowIndexList = rowIndexList;
	}
public String getHdnCombntnSeqNoArr() {
		return hdnCombntnSeqNoArr;
	}

	public void setHdnCombntnSeqNoArr(String hdnCombntnSeqNoArr) {
		this.hdnCombntnSeqNoArr = hdnCombntnSeqNoArr;
	}

	public ArrayList getCharCombnList() {
		return charCombnList;
	}

	public void setCharCombnList(ArrayList charCombnList) {
		this.charCombnList = charCombnList;
	}
	//Added For CR_81 on 24-Dec-09 by sd41630 Ends here
	//Added for CR_94
	public ArrayList getChildClaList() {
		return childClaList;
	}

	public void setChildClaList(ArrayList childClaList) {
		this.childClaList = childClaList;
	}

	public ArrayList getParentClaList() {
		return parentClaList;
	}

	public void setParentClaList(ArrayList parentClaList) {
		this.parentClaList = parentClaList;
	}

	public ArrayList getLeadComponentVO() {
		return leadComponentVO;
	}

	public void setLeadComponentVO(ArrayList leadComponentVO) {
		this.leadComponentVO = leadComponentVO;
	}
//Added For CR_97 lines starts here 
	public int getLeadClauseSeqNo() {
		return leadClauseSeqNo;
	}

	public void setLeadClauseSeqNo(int leadClauseSeqNo) {
		this.leadClauseSeqNo = leadClauseSeqNo;
	}

	//CR_94 Ends here

	//Added for CR-113
	public String getHdnSelectedModel() {
		return hdnSelectedModel;
	}

	public void setHdnSelectedModel(String hdnSelectedModel) {
		this.hdnSelectedModel = hdnSelectedModel;
	}

	public String getHdnSelectedSection() {
		return hdnSelectedSection;
	}

	public void setHdnSelectedSection(String hdnSelectedSection) {
		this.hdnSelectedSection = hdnSelectedSection;
	}

	public String getHdnSelectedSpecType() {
		return hdnSelectedSpecType;
	}

	public void setHdnSelectedSpecType(String hdnSelectedSpecType) {
		this.hdnSelectedSpecType = hdnSelectedSpecType;
	}

	public String getHdnSelectedSubSection() {
		return hdnSelectedSubSection;
	}

	public void setHdnSelectedSubSection(String hdnSelectedSubSection) {
		this.hdnSelectedSubSection = hdnSelectedSubSection;
	}

	public String getHdnShowLatSpecFlag() {
		return hdnShowLatSpecFlag;
	}

	public void setHdnShowLatSpecFlag(String hdnShowLatSpecFlag) {
		this.hdnShowLatSpecFlag = hdnShowLatSpecFlag;
	}

	public boolean isShowLatSpecFlag() {
		return showLatSpecFlag;
	}

	public void setShowLatSpecFlag(boolean showLatSpecFlag) {
		this.showLatSpecFlag = showLatSpecFlag;
	}


	public int getMapAppendixImg() {
		return mapAppendixImg;
	}

	public void setMapAppendixImg(int mapAppendixImg) {
		this.mapAppendixImg = mapAppendixImg;
	}

	public int getAppendixImgSeqNo() {
		return appendixImgSeqNo;
	}

	public void setAppendixImgSeqNo(int appendixImgSeqNo) {
		this.appendixImgSeqNo = appendixImgSeqNo;
	}
//	Added for CR-113 Ends Here

	public String getSpecTypeSelectFlag() {
		return SpecTypeSelectFlag;
	}

	public void setSpecTypeSelectFlag(String specTypeSelectFlag) {
		SpecTypeSelectFlag = specTypeSelectFlag;
	}

	public String getLeadSubClaExistsFlag() {
		return leadSubClaExistsFlag;
	}

	public void setLeadSubClaExistsFlag(String leadSubClaExistsFlag) {
		this.leadSubClaExistsFlag = leadSubClaExistsFlag;
	}

	
	
	
}