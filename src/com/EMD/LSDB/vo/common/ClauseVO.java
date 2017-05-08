package com.EMD.LSDB.vo.common;

import java.util.ArrayList;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the setter and getter methods for the Clause
 ******************************************************************************/

public class ClauseVO extends EMDVO {
	private int clauseSeqNo;
	
	private int clauseNo;
	
	private int parentClauseSeqNo;
	
	private int versionNo;
	
	private String clauseDesc;
	
	private String[] edlNo;
	
	private String[] refEDLNo;
	
	private int[] partOfSeqNo;
	
	private String[] partOfCode;
	
	private String orderNo;
	
	private String revisionNo;
	
	private int dwONumber;
	
	private int partNumber;
	
	private int priceBookNumber;
	
	private String comments;
	
	private String defaultFlag;
	
	private String reason;
	
	private String customerName;
	
	private int modelSeqNo;
	
	private int sectionSeqNo;
	
	private String secCode;
	
	private int subSectionSeqNo;
	
	private ArrayList subSectionSeqCode;
	
	private int customerSeqNo;
	
	private String modifiedBy;
	
	private String modifiedDate;
	
	private ArrayList componentVO;
	
	private ArrayList compGroupVO;
	
	private ArrayList orderVO;
	
	private ArrayList objStandardEquipVO;
	
	private ArrayList tableDataVO;
	
	private int rowIndex;
	
	private String modelName;
	
	private String sectionName;
	
	private String subSectionName;
	
	private ArrayList componentList;
	
	private String modelDefFlag;
	
	private String copyFlag;
	
	private int standardEquipmentSeqNo;
	
	// Added for Section view
	
	private String copyIndicator;
	
	private String modelIndicator;
	
	private String deleteIndicator;
	
	private ArrayList compName;
	//CR_90
	private String compGroupName;
	
	private ArrayList revCode;
	
	private ArrayList edlNO;
	
	private ArrayList refEDLNO;
	
	private ArrayList partOF;
	//CR_86
	private String removeClause;
	//CR_85 for Assign EDL page
	private int noOfLinks;
	//Added  for CR_88 0n 2Jun10
	private String childFlag;
	private String claCode;
	private String[] claSeqNoList;
	//CR 88
	private int[] CombntnSeqNoVO;
	private ArrayList charCombnList;

	// CR_91
	private String strPreReason;

	private String strPastClauseNo;	
	
	private String strPastClauseDesc;

	private String strModDelFlag;

	private String strEngData;

	private String strPreClauseNo;

	private String strPreClauseDesc;
	
	

	
	/***************************************************************************
	 * Added For LSDB_CR's-27,28,35 Added on 04-April-08 by ps57222
	 * 
	 **************************************************************************/
	
	private String clauseSource;
	
	/***************************************************************************
	 * clauseNums-Added For LSDB_CR-34 Added on 21-April-08 by ps57222
	 * 
	 **************************************************************************/
	
	private ArrayList clauseNums;
	
	/**
	 * Added for LADB_CR_42 clause image
	 * 
	 */
	
	private String clauseImageName;
	
	/*
	 * Added for LSDB_CR_49 for delete Clause
	 */
	
	private String deleteFlag;
	
	/**
	 * Added for LSDB_CR-48 on 30-July-08 by ps57222
	 */
	
	private String[] partOfClaSeqNo;
	
	// Added for CR-45 to get Clause No for Pert Of
	private ArrayList partOfClaNo;
	
	// Added for CR-45 Table data
	private ArrayList tabData;
	
	/**
	 * Added for LSDB_CR-45 on 05-Nov-08 by ps57222
	 */
	
	private int[] reqpartOfClaSeqNo;
	
	/**
	 * Added for LSDB_CR-45 on 11-Nov-08 by ps57222
	 */
	
	private String componentSeqNo;

	
	//Added for CR-68 Order New Component
	private String orderCompName;
	
    //Added for CR 75
	private String specTypeName;
	
	private String componentName;
	
	private String edlNum;
	
	//Added for LSDB_CR-74
	private String userMarkFlag;
	
	private String sysMarkFlag;
	
	private String clauseDelFlag;//Order Level Del Flag
	
	private String reviseEnableFlag;//to (enable/disable) revise link
	
	private String compShowFlag;//to (show/hide) component
	
	private String delClaStagFlag;//Check for del cla staging
	
	private String sysMarkDesc;
	
	private String saleSysMarker;
	
	//Added For CR_81 Locomotive and Power Products Enhancements by RR68151
	private String charEdlNo;
	
	private String charRefEDLNo;
	
	private int combntnSeqNo;
	
    private String[] componentGroupSeqNo;
	
	private String[] compSeqNo;
	
	private String edlIndicator;
	
	private String oldCharEdlNo;
	
	private String oldCharRefEDLNo;
	
	private int claEdlChngType;
	
//	Added For CR_81 on 24-Dec-09 by sd41630
	public String selectCGCFlag;
//	Added For CR_81 on 24-Dec-09 by sd41630 Ends here
//	CR_85 by sd41630
	private int linkClaSeqNo;
	private int[] combntnSeqNoArr;

//Added for CR_92
	private String currRevFlag;
	private String subSecCode;
	private int compSeqNum;
	private int compGrpSeqNo;
	private ArrayList clauseGroup;
	private int nonCurrDelCount;
	private int currDelCount;
//Ends here
	
//	Added For CR_93
	private int tableDataColSize=0;
	//Added for CR_94
	private int claHrchyLevel;
	private int noOfChildClauses;
	private int noOfChildForParent;
	//Added for CR_97
	private String newCRFlag;
//	Added for CR_99
	private String salesVerFLAG;
	private String salesClaDesc;

//Added for CR_99
	private String newClauseDesc;
	private String claDiffFlag;
	//CR_99 Ends here
	
	//Added for CR_100
	private int specTypeNo;
	//CR_100 Ends here
	
	//Added for CR_106
	private String refEdlNum;
	//CR_106 Ends here
	
	//Added for CR_108	
	private String status;
	//CR_108 Ends here

	//Added for CR_109
	private String markClaReason;
	private String appendixExitsFlag;
	private String claReason;
	private int partofLeadCompGrp;
	//Added for CR_109 Ends here
	//Added for CR_114
	private int appendixImgSeqNo;
	private boolean mapAppendixImg;
	//Added for CR_114 Ends here
	
	//Added for CR-113
	private String indicatorFlag;
	private String showLatestPubSpecFlag;
	private int orderbyFlag;
	private ArrayList orderList;
	
	//Added for CR-110
	private int seqNo1058;
	private int clauseChangeType;
	private String clauseChangeTypeDesc;
	private int oldComponentSeqNo;
	private String oldComponentName;
	private int changeFromClaSeqNo;
    private int changeToClaSeqNo;
    private int changeFromClaVerNo;
    private String changeFromClaNo;
    private String changeToClaNo;
    private int changeToClaVerNo;
    private int clauseChangeReqSeqNo;
    private String clauseUpdatedToSpec;
	private String sectionDesc;
	private String subSectionDesc;
	private String leadComponentGrpName;
	private int leadComponentSeqNo;
	private String leadComponentName;
	private String parentClauseDesc;
	private String strComps;
	private int unUpdatedClauses;
	
	//Added for CR-114
	private String createdBy;
	private String createdDate;
	
	//Added for CR-121
	public String subClaExistsFlag;
	public int hdnClauseSeqNo;
	public String removeAllOptCla;

	//Added for CR-134
	
	private String delModFlag;
	private int modVersionNo;
	private String usedVersionFlag;
	
	
	//Added for CR-135
	private String changeFromClaDesc;
	private String changeFromEdlNo;
	private String changeFromRefEdlNo;
	private String changeFromPartOfNo;
	private String changeFromDwoNo;
	private String changeFromPartNo;
	private String changeFromPriceBookNo;
	private String changeFromEnggComments;
	private String changeFromStdEqp;
	private String partOfNo;
	private String stdEqpDesc;
	private ArrayList frmTableArrayData1;
	private int frmTableDataColSize;
	private String changeFromCompName;
	private String changeFromCompGrpName;
	private String changeToCompName;
	private String changeToCompGrpName;
	private String dwoNumber;
	private String partNo;
	private String priceBookNo;
	
	private ArrayList clauseDet;
	private ArrayList sectionDet;
	
	

	public String getChangeFromEdlNo() {
		return changeFromEdlNo;
	}

	public void setChangeFromEdlNo(String changeFromEdlNo) {
		this.changeFromEdlNo = changeFromEdlNo;
	}

	public String getChangeFromClaDesc() {
		return changeFromClaDesc;
	}

	public void setChangeFromClaDesc(String changeFromClaDesc) {
		this.changeFromClaDesc = changeFromClaDesc;
	}

	public int getUnUpdatedClauses() {
		return unUpdatedClauses;
	}

	public void setUnUpdatedClauses(int unUpdatedClauses) {
		this.unUpdatedClauses = unUpdatedClauses;
	}

	public String getStrComps() {
		return strComps;
	}

	public void setStrComps(String strComps) {
		this.strComps = strComps;
	}

	public String getSectionDesc() {
		return sectionDesc;
	}

	public void setSectionDesc(String sectionDesc) {
		this.sectionDesc = sectionDesc;
	}

	public String getClauseUpdatedToSpec() {
		return clauseUpdatedToSpec;
	}

	public void setClauseUpdatedToSpec(String clauseUpdatedToSpec) {
		this.clauseUpdatedToSpec = clauseUpdatedToSpec;
	}

	public int getClauseChangeReqSeqNo() {
		return clauseChangeReqSeqNo;
	}

	public void setClauseChangeReqSeqNo(int clauseChangeReqSeqNo) {
		this.clauseChangeReqSeqNo = clauseChangeReqSeqNo;
	}

	public int getOldComponentSeqNo() {
		return oldComponentSeqNo;
	}

	public void setOldComponentSeqNo(int oldComponentSeqNo) {
		this.oldComponentSeqNo = oldComponentSeqNo;
	}

	public int getClauseChangeType() {
		return clauseChangeType;
	}

	public void setClauseChangeType(int clauseChangeType) {
		this.clauseChangeType = clauseChangeType;
	}

	public int getSeqNo1058() {
		return seqNo1058;
	}

	public void setSeqNo1058(int seqNo1058) {
		this.seqNo1058 = seqNo1058;
	}

	public int[] getCombntnSeqNoArr() {
		return combntnSeqNoArr;
	}

	public void setCombntnSeqNoArr(int[] combntnSeqNoArr) {
		this.combntnSeqNoArr = combntnSeqNoArr;
	}

	/**
	 * @return Returns the compShowFlag.
	 */
	public String getCompShowFlag() {
		return compShowFlag;
	}

	/**
	 * @param compShowFlag The compShowFlag to set.
	 */
	public void setCompShowFlag(String compShowFlag) {
		this.compShowFlag = compShowFlag;
	}

	/**
	 * @return Returns the delClaStagFlag.
	 */
	public String getDelClaStagFlag() {
		return delClaStagFlag;
	}

	/**
	 * @param delClaStagFlag The delClaStagFlag to set.
	 */
	public void setDelClaStagFlag(String delClaStagFlag) {
		this.delClaStagFlag = delClaStagFlag;
	}

	/**
	 * @return Returns the reviseEnableFlag.
	 */
	public String getReviseEnableFlag() {
		return reviseEnableFlag;
	}

	/**
	 * @param reviseEnableFlag The reviseEnableFlag to set.
	 */
	public void setReviseEnableFlag(String reviseEnableFlag) {
		this.reviseEnableFlag = reviseEnableFlag;
	}

	/**
	 * @return Returns the sysMarkFlag.
	 */
	public String getSysMarkFlag() {
		return sysMarkFlag;
	}

	/**
	 * @param sysMarkFlag The sysMarkFlag to set.
	 */
	public void setSysMarkFlag(String sysMarkFlag) {
		this.sysMarkFlag = sysMarkFlag;
	}

	/**
	 * @return Returns the userMarkFlag.
	 */
	public String getUserMarkFlag() {
		return userMarkFlag;
	}

	/**
	 * @param userMarkFlag The userMarkFlag to set.
	 */
	public void setUserMarkFlag(String userMarkFlag) {
		this.userMarkFlag = userMarkFlag;
	}

	
	/**
	 * @return Returns the clauseDelFlag.
	 */
	public String getClauseDelFlag() {
		return clauseDelFlag;
	}

	/**
	 * @param clauseDelFlag The clauseDelFlag to set.
	 */
	public void setClauseDelFlag(String clauseDelFlag) {
		this.clauseDelFlag = clauseDelFlag;
	}

	
	/**
	 * @return Returns the sysMarkDesc.
	 */
	public String getSysMarkDesc() {
		return sysMarkDesc;
	}

	/**
	 * @param sysMarkDesc The sysMarkDesc to set.
	 */
	public void setSysMarkDesc(String sysMarkDesc) {
		this.sysMarkDesc = sysMarkDesc;
	}

	public String getEdlNum() {
		return edlNum;
	}

	public void setEdlNum(String edlNum) {
		this.edlNum = edlNum;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getSpecTypeName() {
		return specTypeName;
	}

	public void setSpecTypeName(String specTypeName) {
		this.specTypeName = specTypeName;
	}

	public String getOrderCompName() {
		return orderCompName;
	}

	public void setOrderCompName(String orderCompName) {
		this.orderCompName = orderCompName;
	}
	
	/**
	 * @return Returns the tabData.
	 */
	public ArrayList getTabData() {
		return tabData;
	}
	
	/**
	 * @param tabData
	 *            The tabData to set.
	 */
	public void setTabData(ArrayList tabData) {
		this.tabData = tabData;
	}
	
	/**
	 * @return Returns the partOfClaNo.
	 */
	public ArrayList getPartOfClaNo() {
		return partOfClaNo;
	}
	
	/**
	 * @param partOfClaNo
	 *            The partOfClaNo to set.
	 */
	public void setPartOfClaNo(ArrayList partOfClaNo) {
		this.partOfClaNo = partOfClaNo;
	}
	
	/**
	 * @return Returns the partOfClaSeqNo.
	 */
	public String[] getPartOfClaSeqNo() {
		return partOfClaSeqNo;
	}
	
	/**
	 * @param partOfClaSeqNo
	 *            The partOfClaSeqNo to set.
	 */
	public void setPartOfClaSeqNo(String[] partOfClaSeqNo) {
		this.partOfClaSeqNo = partOfClaSeqNo;
	}
	
	/**
	 * @return Returns the deleteFlag.
	 */
	public String getDeleteFlag() {
		return deleteFlag;
	}
	
	/**
	 * @param deleteFlag
	 *            The deleteFlag to set.
	 */
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	/**
	 * @return Returns the clauseImageName.
	 */
	public String getClauseImageName() {
		return clauseImageName;
	}
	
	/**
	 * @param clauseImageName
	 *            The clauseImageName to set.
	 */
	public void setClauseImageName(String clauseImageName) {
		this.clauseImageName = clauseImageName;
	}
	
	/**
	 * @return Returns the clauseSource.
	 */
	public String getClauseSource() {
		return clauseSource;
	}
	
	/**
	 * @param clauseSource
	 *            The clauseSource to set.
	 */
	public void setClauseSource(String clauseSource) {
		this.clauseSource = clauseSource;
	}
	
	// Ends
	
	private String clauseNum;
	
	// Enteries for accept/Reject Clause Change
	private String flag = null;
	
	private String indFlag = null;
	
	// Enteries for fetching Clause details
	
	private ArrayList tableArrayData5;
	
	private ArrayList tableArrayData1;
	
	private ArrayList tableArrayData2;
	
	private ArrayList tableArrayData3;
	
	private ArrayList tableArrayData4;
	
	private String standardEquipmentDesc;
	
	private String engDataComment;
	
	// Added For Add Clause At Order
	
	private String[] clauseSeqNoArray;
	
	private String[] clauseNoArray;
	
	private int[] clauseSeqNum;
	
	private String clauseDescForTextArea;
	
	private int partOfClauseNo;
	
	private int specStatusCode;
	
	/** * Added to Check the Spec Status when display Reason Text Area in Jsp ** */
	
	/* Added for Attach Clause CR */
	
	private int leadCompGrpSeqNo;
	
	//Added for CR_80 to Pick Core Clauses for Not Required Option by RR68151
	
	private String coreClausesFlag;
	
	/**
	 * **** imageSeqNo is Added for LSDB_CR-42 Added on 08-05-08 by ps57222
	 */
	private String imageMapNo;
	
	/**
	 * @return Returns the imageMapNo.
	 */
	public String getImageMapNo() {
		return imageMapNo;
	}
	
	/**
	 * @param imageMapNo
	 *            The imageMapNo to set.
	 */
	public void setImageMapNo(String imageMapNo) {
		this.imageMapNo = imageMapNo;
	}
	
	/**
	 * @return Returns the leadCompGrpSeqNo.
	 */
	public int getLeadCompGrpSeqNo() {
		return leadCompGrpSeqNo;
	}
	
	/**
	 * @param leadCompGrpSeqNo
	 *            The leadCompGrpSeqNo to set.
	 */
	public void setLeadCompGrpSeqNo(int leadCompGrpSeqNo) {
		this.leadCompGrpSeqNo = leadCompGrpSeqNo;
	}
	
	/**
	 * @return Returns the partOfClauseNo.
	 */
	public int getPartOfClauseNo() {
		return partOfClauseNo;
	}
	
	/**
	 * @param partOfClauseNo
	 *            The partOfClauseNo to set.
	 */
	public void setPartOfClauseNo(int partOfClauseNo) {
		this.partOfClauseNo = partOfClauseNo;
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
	 * @return Returns the clauseSeqNum.
	 */
	public int[] getClauseSeqNum() {
		return clauseSeqNum;
	}
	
	/**
	 * @param clauseSeqNum
	 *            The clauseSeqNum to set.
	 */
	public void setClauseSeqNum(int[] clauseSeqNum) {
		this.clauseSeqNum = clauseSeqNum;
	}
	
	/**
	 * @return Returns the engDataComment.
	 */
	public String getEngDataComment() {
		return engDataComment;
	}
	
	/**
	 * @param engDataComment
	 *            The engDataComment to set.
	 */
	public void setEngDataComment(String engDataComment) {
		this.engDataComment = engDataComment;
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
	 * @return Returns the tableArrayData1.
	 */
	public ArrayList getTableArrayData1() {
		return tableArrayData1;
	}
	
	/**
	 * @param tableArrayData1
	 *            The tableArrayData1 to set.
	 */
	public void setTableArrayData1(ArrayList tableArrayData1) {
		this.tableArrayData1 = tableArrayData1;
	}
	
	/**
	 * @return Returns the tableArrayData2.
	 */
	public ArrayList getTableArrayData2() {
		return tableArrayData2;
	}
	
	/**
	 * @param tableArrayData2
	 *            The tableArrayData2 to set.
	 */
	public void setTableArrayData2(ArrayList tableArrayData2) {
		this.tableArrayData2 = tableArrayData2;
	}
	
	/**
	 * @return Returns the tableArrayData3.
	 */
	public ArrayList getTableArrayData3() {
		return tableArrayData3;
	}
	
	/**
	 * @param tableArrayData3
	 *            The tableArrayData3 to set.
	 */
	public void setTableArrayData3(ArrayList tableArrayData3) {
		this.tableArrayData3 = tableArrayData3;
	}
	
	/**
	 * @return Returns the tableArrayData4.
	 */
	public ArrayList getTableArrayData4() {
		return tableArrayData4;
	}
	
	/**
	 * @param tableArrayData4
	 *            The tableArrayData4 to set.
	 */
	public void setTableArrayData4(ArrayList tableArrayData4) {
		this.tableArrayData4 = tableArrayData4;
	}
	
	/**
	 * @return Returns the tableArrayData5.
	 */
	public ArrayList getTableArrayData5() {
		return tableArrayData5;
	}
	
	/**
	 * @param tableArrayData5
	 *            The tableArrayData5 to set.
	 */
	public void setTableArrayData5(ArrayList tableArrayData5) {
		this.tableArrayData5 = tableArrayData5;
	}
	
	/**
	 * @return Returns the partOF.
	 */
	public ArrayList getPartOF() {
		return partOF;
	}
	
	/**
	 * @param partOF
	 *            The partOF to set.
	 */
	public void setPartOF(ArrayList partOF) {
		this.partOF = partOF;
	}
	
	/**
	 * @return Returns the revCode.
	 */
	public ArrayList getRevCode() {
		return revCode;
	}
	
	/**
	 * @param revCode
	 *            The revCode to set.
	 */
	public void setRevCode(ArrayList revCode) {
		this.revCode = revCode;
	}
	
	/**
	 * @return Returns the modelIndicator.
	 */
	public String getModelIndicator() {
		return modelIndicator;
	}
	
	/**
	 * @param modelIndicator
	 *            The modelIndicator to set.
	 */
	public void setModelIndicator(String modelIndicator) {
		this.modelIndicator = modelIndicator;
	}
	
	/**
	 * @return Returns the copyIndicator.
	 */
	public String getCopyIndicator() {
		return copyIndicator;
	}
	
	/**
	 * @param copyIndicator
	 *            The copyIndicator to set.
	 */
	public void setCopyIndicator(String copyIndicator) {
		this.copyIndicator = copyIndicator;
	}
	
	/**
	 * @return Returns the componentList.
	 */
	public ArrayList getComponentList() {
		return componentList;
	}
	
	/**
	 * @param componentList
	 *            The componentList to set.
	 */
	public void setComponentList(ArrayList componentList) {
		this.componentList = componentList;
	}
	
	public int getRowIndex() {
		return rowIndex;
	}
	
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
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
	 * @return Returns the customerName.
	 */
	public String getCustomerName() {
		return customerName;
	}
	
	/**
	 * @param customerName
	 *            The customerName to set.
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
	 * @param customerSeqNo
	 *            The customerSeqNo to set.
	 */
	public void setCustomerSeqNo(int customerSeqNo) {
		this.customerSeqNo = customerSeqNo;
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
	 * @return Returns the dwONumber.
	 */
	public int getDwONumber() {
		return dwONumber;
	}
	
	/**
	 * @param dwONumber
	 *            The dwONumber to set.
	 */
	public void setDwONumber(int number) {
		dwONumber = number;
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
	public void setEdlNo(String[] eDLNo) {
		this.edlNo = eDLNo;
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
	 * @return Returns the modifiedBy.
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}
	
	/**
	 * @param modifiedBy
	 *            The modifiedBy to set.
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
	 * @param modifiedDate
	 *            The modifiedDate to set.
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
	 * @param orderNo
	 *            The orderNo to set.
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
	 * @return Returns the refEDLNo.
	 */
	public String[] getRefEDLNo() {
		return refEDLNo;
	}
	
	/**
	 * @param refEDLNo
	 *            The refEDLNo to set.
	 */
	public void setRefEDLNo(String[] refEDLNo) {
		this.refEDLNo = refEDLNo;
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
	 * @return Returns the subSectionSeqCode.
	 */
	public ArrayList getSubSectionSeqCode() {
		return subSectionSeqCode;
	}
	
	/**
	 * @param subSectionSeqCode
	 *            The subSectionSeqCode to set.
	 */
	public void setSubSectionSeqCode(ArrayList subSectionSeqCode) {
		this.subSectionSeqCode = subSectionSeqCode;
	}
	
	/**
	 * @return Returns the tableDataVO.
	 */
	public ArrayList getTableDataVO() {
		return tableDataVO;
	}
	
	/**
	 * @param tableDataVO
	 *            The tableDataVO to set.
	 */
	public void setTableDataVO(ArrayList tableDataVO) {
		this.tableDataVO = tableDataVO;
	}
	
	/**
	 * @return Returns the objStandardEquipVO.
	 */
	public ArrayList getObjStandardEquipVO() {
		return objStandardEquipVO;
	}
	
	/**
	 * @param objStandardEquipVO
	 *            The objStandardEquipVO to set.
	 */
	public void setObjStandardEquipVO(ArrayList objStandardEquipVO) {
		this.objStandardEquipVO = objStandardEquipVO;
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
	 * @return Returns the compName.
	 */
	public ArrayList getCompName() {
		return compName;
	}
	
	/**
	 * @param compName
	 *            The compName to set.
	 */
	public void setCompName(ArrayList compName) {
		this.compName = compName;
	}
	
	/**
	 * @return Returns the edlNO.
	 */
	public ArrayList getEdlNO() {
		return edlNO;
	}
	
	/**
	 * @param edlNO
	 *            The edlNO to set.
	 */
	public void setEdlNO(ArrayList edlNO) {
		this.edlNO = edlNO;
	}
	
	/**
	 * @return Returns the refEDLNO.
	 */
	public ArrayList getRefEDLNO() {
		return refEDLNO;
	}
	
	/**
	 * @param refEDLNO
	 *            The refEDLNO to set.
	 */
	public void setRefEDLNO(ArrayList refEDLNO) {
		this.refEDLNO = refEDLNO;
	}
	
	/**
	 * @return Returns the flag.
	 */
	public String getFlag() {
		return flag;
	}
	
	/**
	 * @param flag
	 *            The flag to set.
	 */
	public void setFlag(String flag) {
		this.flag = flag;
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
	 * @return Returns the clauseSeqNoArray.
	 */
	public String[] getClauseSeqNoArray() {
		return clauseSeqNoArray;
	}
	
	/**
	 * @param clauseSeqNoArray
	 *            The clauseSeqNoArray to set.
	 */
	public void setClauseSeqNoArray(String[] clauseSeqNoArray) {
		this.clauseSeqNoArray = clauseSeqNoArray;
	}
	
	/**
	 * @return Returns the clauseNoArray.
	 */
	public String[] getClauseNoArray() {
		return clauseNoArray;
	}
	
	/**
	 * @param clauseNoArray
	 *            The clauseNoArray to set.
	 */
	public void setClauseNoArray(String[] clauseNoArray) {
		this.clauseNoArray = clauseNoArray;
	}
	
	/**
	 * @return Returns the indFlag.
	 */
	public String getIndFlag() {
		return indFlag;
	}
	
	/**
	 * @param indFlag
	 *            The indFlag to set.
	 */
	public void setIndFlag(String indFlag) {
		this.indFlag = indFlag;
	}
	
	public String getModelDefFlag() {
		return modelDefFlag;
	}
	
	public void setModelDefFlag(String modelDefFlag) {
		this.modelDefFlag = modelDefFlag;
	}
	
	public String getSecCode() {
		return secCode;
	}
	
	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}
	
	public String getCopyFlag() {
		return copyFlag;
	}
	
	public void setCopyFlag(String copyFlag) {
		this.copyFlag = copyFlag;
	}
	
	/**
	 * @return Returns the specStatusCode.
	 */
	public int getSpecStatusCode() {
		return specStatusCode;
	}
	
	/**
	 * @param specStatusCode
	 *            The specStatusCode to set.
	 */
	public void setSpecStatusCode(int specStatusCode) {
		this.specStatusCode = specStatusCode;
	}
	
	public ArrayList getCompGroupVO() {
		return compGroupVO;
	}
	
	public void setCompGroupVO(ArrayList compGroupVO) {
		this.compGroupVO = compGroupVO;
	}
	
	public ArrayList getOrderVO() {
		return orderVO;
	}
	
	public void setOrderVO(ArrayList orderVO) {
		this.orderVO = orderVO;
	}
	
	/**
	 * @return Returns the clauseNums.
	 */
	public ArrayList getClauseNums() {
		return clauseNums;
	}
	
	/**
	 * @param clauseNums
	 *            The clauseNums to set.
	 */
	public void setClauseNums(ArrayList clauseNums) {
		this.clauseNums = clauseNums;
	}
	
	/**
	 * @return Returns the deleteIndicator.
	 */
	public String getDeleteIndicator() {
		return deleteIndicator;
	}
	
	/**
	 * @param deleteIndicator
	 *            The deleteIndicator to set.
	 */
	public void setDeleteIndicator(String deleteIndicator) {
		this.deleteIndicator = deleteIndicator;
	}
	
	/**
	 * @return Returns the reqpartOfClaSeqNo.
	 */
	public int[] getReqpartOfClaSeqNo() {
		return reqpartOfClaSeqNo;
	}
	
	/**
	 * @param reqpartOfClaSeqNo
	 *            The reqpartOfClaSeqNo to set.
	 */
	public void setReqpartOfClaSeqNo(int[] reqpartOfClaSeqNo) {
		this.reqpartOfClaSeqNo = reqpartOfClaSeqNo;
	}
	
	/**
	 * @return Returns the componentSeqNo.
	 */
	public String getComponentSeqNo() {
		return componentSeqNo;
	}
	
	/**
	 * @param componentSeqNo
	 *            The componentSeqNo to set.
	 */
	public void setComponentSeqNo(String componentSeqNo) {
		this.componentSeqNo = componentSeqNo;
	}

	/**
	 * @return Returns the saleSysMarker.
	 */
	public String getSaleSysMarker() {
		return saleSysMarker;
	}

	/**
	 * @param saleSysMarker The saleSysMarker to set.
	 */
	public void setSaleSysMarker(String saleSysMarker) {
		this.saleSysMarker = saleSysMarker;
	}

	/**
	 * @return Returns the coreClausesFlag.
	 */
	public String getCoreClausesFlag() {
		return coreClausesFlag;
	}

	/**
	 * @param coreClausesFlag The coreClausesFlag to set.
	 */
	public void setCoreClausesFlag(String coreClausesFlag) {
		this.coreClausesFlag = coreClausesFlag;
	}

	/**
	 * @return Returns the charEdlNo.
	 */
	public String getCharEdlNo() {
		return charEdlNo;
	}

	/**
	 * @param charEdlNo The charEdlNo to set.
	 */
	public void setCharEdlNo(String charEdlNo) {
		this.charEdlNo = charEdlNo;
	}

	/**
	 * @return Returns the charRefEDLNo.
	 */
	public String getCharRefEDLNo() {
		return charRefEDLNo;
	}

	/**
	 * @param charRefEDLNo The charRefEDLNo to set.
	 */
	public void setCharRefEDLNo(String charRefEDLNo) {
		this.charRefEDLNo = charRefEDLNo;
	}

	/**
	 * @return Returns the combntnSeqNo.
	 */
	public int getCombntnSeqNo() {
		return combntnSeqNo;
	}

	/**
	 * @param combntnSeqNo The combntnSeqNo to set.
	 */
	public void setCombntnSeqNo(int combntnSeqNo) {
		this.combntnSeqNo = combntnSeqNo;
	}
	/**
	 * @param SelectCGCFlag set the Characteristic Group Clause flag.
	 */
//	Added For CR_81 on 24-Dec-09 by sd41630
	

	public String getSelectCGCFlag() {
		return selectCGCFlag;
	}

	public void setSelectCGCFlag(String selectCGCFlag) {
		this.selectCGCFlag = selectCGCFlag;
	}
//	Added For CR_81 on 24-Dec-09 by sd41630 Ends here

	/**
	 * @return Returns the componentGroupSeqNo.
	 */
	public String[] getComponentGroupSeqNo() {
		return componentGroupSeqNo;
	}

	/**
	 * @param componentGroupSeqNo The componentGroupSeqNo to set.
	 */
	public void setComponentGroupSeqNo(String[] componentGroupSeqNo) {
		this.componentGroupSeqNo = componentGroupSeqNo;
	}

	/**
	 * @return Returns the compSeqNo.
	 */
	public String[] getCompSeqNo() {
		return compSeqNo;
	}

	/**
	 * @param compSeqNo The compSeqNo to set.
	 */
	public void setCompSeqNo(String[] compSeqNo) {
		this.compSeqNo = compSeqNo;
	}

	/**
	 * @return Returns the edlIndicator.
	 */
	public String getEdlIndicator() {
		return edlIndicator;
	}

	/**
	 * @param edlIndicator The edlIndicator to set.
	 */
	public void setEdlIndicator(String edlIndicator) {
		this.edlIndicator = edlIndicator;
	}

	/**
	 * @return Returns the oldCharEdlNo.
	 */
	public String getOldCharEdlNo() {
		return oldCharEdlNo;
	}

	/**
	 * @param oldCharEdlNo The oldCharEdlNo to set.
	 */
	public void setOldCharEdlNo(String oldCharEdlNo) {
		this.oldCharEdlNo = oldCharEdlNo;
	}

	/**
	 * @return Returns the oldCharRefEDLNo.
	 */
	public String getOldCharRefEDLNo() {
		return oldCharRefEDLNo;
	}

	/**
	 * @param oldCharRefEDLNo The oldCharRefEDLNo to set.
	 */
	public void setOldCharRefEDLNo(String oldCharRefEDLNo) {
		this.oldCharRefEDLNo = oldCharRefEDLNo;
	}

	/**
	 * @return Returns the claEdlChngType.
	 */
	public int getClaEdlChngType() {
		return claEdlChngType;
	}

	/**
	 * @param claEdlChngType The claEdlChngType to set.
	 */
	public void setClaEdlChngType(int claEdlChngType) {
		this.claEdlChngType = claEdlChngType;
	}

	public String getRemoveClause() {
		return removeClause;
	}

	public void setRemoveClause(String removeClause) {
		this.removeClause = removeClause;
	}



	public int getNoOfLinks() {
		return noOfLinks;
	}

	public void setNoOfLinks(int noOfLinks) {
		this.noOfLinks = noOfLinks;
	}

	public int getLinkClaSeqNo() {
		return linkClaSeqNo;
	}

	public void setLinkClaSeqNo(int linkClaSeqNo) {
		this.linkClaSeqNo = linkClaSeqNo;
	}

	public String getChildFlag() {
		return childFlag;
	}

	public void setChildFlag(String childFlag) {
		this.childFlag = childFlag;
	}

	public String getClaCode() {
		return claCode;
	}

	public void setClaCode(String claCode) {
		this.claCode = claCode;
	}

	public String[] getClaSeqNoList() {
		return claSeqNoList;
	}

	public void setClaSeqNoList(String[] claSeqNoList) {
		this.claSeqNoList = claSeqNoList;
	}
public int[] getCombntnSeqNoVO() {
		return CombntnSeqNoVO;
	}

	public void setCombntnSeqNoVO(int[] combntnSeqNoVO) {
		CombntnSeqNoVO = combntnSeqNoVO;
	}

	public ArrayList getCharCombnList() {
		return charCombnList;
	}

	public void setCharCombnList(ArrayList charCombnList) {
		this.charCombnList = charCombnList;
	}

	public String getCompGroupName() {
		return compGroupName;
	}

	public void setCompGroupName(String compGroupName) {
		this.compGroupName = compGroupName;
	}

	public String getStrPreReason() {
		return strPreReason;
	}

	public void setStrPreReason(String strPreReason) {
		this.strPreReason = strPreReason;
	}

	public String getStrModDelFlag() {
		return strModDelFlag;
	}

	public void setStrModDelFlag(String strModDelFlag) {
		this.strModDelFlag = strModDelFlag;
	}

	public String getStrPastClauseNo() {
		return strPastClauseNo;
	}

	public void setStrPastClauseNo(String strPastClauseNo) {
		this.strPastClauseNo = strPastClauseNo;
	}

	public String getStrEngData() {
		return strEngData;
	}

	public void setStrEngData(String strEngData) {
		this.strEngData = strEngData;
	}

	public String getStrPreClauseNo() {
		return strPreClauseNo;
	}

	public void setStrPreClauseNo(String strPreClauseNo) {
		this.strPreClauseNo = strPreClauseNo;
	}

	public String getStrPreClauseDesc() {
		return strPreClauseDesc;
	}

	public void setStrPreClauseDesc(String strPreClauseDesc) {
		this.strPreClauseDesc = strPreClauseDesc;
	}

	/**
	 * @return Returns the strPastClauseDesc.
	 */
	public String getStrPastClauseDesc() {
		return strPastClauseDesc;
	}

	/**
	 * @param strPastClauseDesc The strPastClauseDesc to set.
	 */
	public void setStrPastClauseDesc(String strPastClauseDesc) {
		this.strPastClauseDesc = strPastClauseDesc;
	}
//Added for CR_92
	/**
	 * @return Returns the currRevFlag.
	 */
	public String getCurrRevFlag() {
		return currRevFlag;
	}

	/**
	 * @param currRevFlag The currRevFlag to set.
	 */
	public void setCurrRevFlag(String currRevFlag) {
		this.currRevFlag = currRevFlag;
	}
	/**
	 * @return Returns the subSecCode.
	 */
	public String getSubSecCode() {
		return subSecCode;
	}
	/**
	 * @param subSecCode The subSecCode to set.
	 */
	public void setSubSecCode(String subSecCode) {
		this.subSecCode = subSecCode;
	}
	/**
	 * @return Returns the specStatusCode.
	 */
	public int getCompSeqNum() {
		return compSeqNum;
	}
	
	/**
	 * @param specStatusCode
	 *            The specStatusCode to set.
	 */
	public void setCompSeqNum(int compSeqNum) {
		this.compSeqNum = compSeqNum;
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
	 * @return Returns the compGroup.
	 */
	public ArrayList getClauseGroup() {
		return clauseGroup;
	}
	
	/**
	 * @param clauseGroup
	 *            The clauseGroup to set.
	 */
	public void setClauseGroup(ArrayList clauseGroup) {
		this.clauseGroup = clauseGroup;
	}
//	Added for CR_92

	public int getCurrDelCount() {
		return currDelCount;
	}

	public void setCurrDelCount(int currDelCount) {
		this.currDelCount = currDelCount;
	}

	public int getNonCurrDelCount() {
		return nonCurrDelCount;
	}

	public void setNonCurrDelCount(int nonCurrDelCount) {
		this.nonCurrDelCount = nonCurrDelCount;
	}

	public int getTableDataColSize() {
		return tableDataColSize;
	}

	public void setTableDataColSize(int tableDataColSize) {
		this.tableDataColSize = tableDataColSize;
	}
//Ends here
	//Added for CR_94
	public int getClaHrchyLevel() {
		return claHrchyLevel;
	}

	public void setClaHrchyLevel(int claHrchyLevel) {
		this.claHrchyLevel = claHrchyLevel;
	}

	public int getNoOfChildClauses() {
		return noOfChildClauses;
	}

	public void setNoOfChildClauses(int noOfChildClauses) {
		this.noOfChildClauses = noOfChildClauses;
	}

	public int getNoOfChildForParent() {
		return noOfChildForParent;
	}

	public void setNoOfChildForParent(int noOfChildForParent) {
		this.noOfChildForParent = noOfChildForParent;
	}
	//CR_94 Ends here
	//Added for CR_97 
	public String getNewCRFlag() {
		return newCRFlag;
	}

	public void setNewCRFlag(String newCRFlag) {
		this.newCRFlag = newCRFlag;
	}	
	//CR_97 Ends here
//CR_99 starts here

	public String getSalesVerFLAG() {
		return salesVerFLAG;
	}

	public void setSalesVerFLAG(String salesVerFLAG) {
		this.salesVerFLAG = salesVerFLAG;
	}
//	CR_99 Ends here

	public String getSalesClaDesc() {
		return salesClaDesc;
	}

	public void setSalesClaDesc(String salesClaDesc) {
		this.salesClaDesc = salesClaDesc;
	}
//Added for CR_99
	public String getClaDiffFlag() {
		return claDiffFlag;
	}

	public void setClaDiffFlag(String claDiffFlag) {
		this.claDiffFlag = claDiffFlag;
	}

	public String getNewClauseDesc() {
		return newClauseDesc;
	}

	public void setNewClauseDesc(String newClauseDesc) {
		this.newClauseDesc = newClauseDesc;
	}
	//CR_99 Ends here

	//Added for CR_100
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
	//CR_100 Ends here

	/**
	 * @return Returns the refEdlNum.
	 */
	public String getRefEdlNum() {
		return refEdlNum;
	}

	/**
	 * @param refEdlNum The refEdlNum to set.
	 */
	public void setRefEdlNum(String refEdlNum) {
		this.refEdlNum = refEdlNum;
	}

	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return Returns the markClaReason.
	 */
	public String getMarkClaReason() {
		return markClaReason;
	}

	/**
	 * @param markClaReason The markClaReason to set.
	 */
	public void setMarkClaReason(String markClaReason) {
		this.markClaReason = markClaReason;
	}

	public String getAppendixExitsFlag() {
		return appendixExitsFlag;
	}

	public void setAppendixExitsFlag(String appendixExitsFlag) {
		this.appendixExitsFlag = appendixExitsFlag;
	}

	public String getClaReason() {
		return claReason;
	}

	public void setClaReason(String claReason) {
		this.claReason = claReason;
	}

	public int getPartofLeadCompGrp() {
		return partofLeadCompGrp;
	}

	public void setPartofLeadCompGrp(int partofLeadCompGrp) {
		this.partofLeadCompGrp = partofLeadCompGrp;
	}

	public String getIndicatorFlag() {
		return indicatorFlag;
	}

	public void setIndicatorFlag(String indicatorFlag) {
		this.indicatorFlag = indicatorFlag;
	}
	public int getOrderbyFlag() {
		return orderbyFlag;
	}

	public void setOrderbyFlag(int orderbyFlag) {
		this.orderbyFlag = orderbyFlag;
	}

	public ArrayList getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList orderList) {
		this.orderList = orderList;
	}

   	public String getShowLatestPubSpecFlag() {
		return showLatestPubSpecFlag;
	}

	public void setShowLatestPubSpecFlag(String showLatestPubSpecFlag) {
		this.showLatestPubSpecFlag = showLatestPubSpecFlag;
	}

	public String getChangeFromClaNo() {
		return changeFromClaNo;
	}

	public void setChangeFromClaNo(String changeFromClaNo) {
		this.changeFromClaNo = changeFromClaNo;
	}

	public int getChangeFromClaSeqNo() {
		return changeFromClaSeqNo;
	}

	public void setChangeFromClaSeqNo(int changeFromClaSeqNo) {
		this.changeFromClaSeqNo = changeFromClaSeqNo;
	}

	public int getChangeFromClaVerNo() {
		return changeFromClaVerNo;
	}

	public void setChangeFromClaVerNo(int changeFromClaVerNo) {
		this.changeFromClaVerNo = changeFromClaVerNo;
	}

	public int getChangeToClaSeqNo() {
		return changeToClaSeqNo;
	}

	public void setChangeToClaSeqNo(int changeToClaSeqNo) {
		this.changeToClaSeqNo = changeToClaSeqNo;
	}

	

	public int getChangeToClaVerNo() {
		return changeToClaVerNo;
	}

	public void setChangeToClaVerNo(int changeToClaVerNo) {
		this.changeToClaVerNo = changeToClaVerNo;
	}

	public String getClauseChangeTypeDesc() {
		return clauseChangeTypeDesc;
	}

	public void setClauseChangeTypeDesc(String clauseChangeTypeDesc) {
		this.clauseChangeTypeDesc = clauseChangeTypeDesc;
	}

	public String getChangeToClaNo() {
		return changeToClaNo;
	}

	public void setChangeToClaNo(String changeToClaNo) {
		this.changeToClaNo = changeToClaNo;
	}

	public String getSubSectionDesc() {
		return subSectionDesc;
	}

	public void setSubSectionDesc(String subSectionDesc) {
		this.subSectionDesc = subSectionDesc;
	}

	public String getParentClauseDesc() {
		return parentClauseDesc;
	}

	public void setParentClauseDesc(String parentClauseDesc) {
		this.parentClauseDesc = parentClauseDesc;
	}

	public String getLeadComponentGrpName() {
		return leadComponentGrpName;
	}

	public void setLeadComponentGrpName(String leadComponentGrpName) {
		this.leadComponentGrpName = leadComponentGrpName;
	}

	public String getLeadComponentName() {
		return leadComponentName;
	}

	public void setLeadComponentName(String leadComponentName) {
		this.leadComponentName = leadComponentName;
	}

	public int getLeadComponentSeqNo() {
		return leadComponentSeqNo;
	}

	public void setLeadComponentSeqNo(int leadComponentSeqNo) {
		this.leadComponentSeqNo = leadComponentSeqNo;
	}

	public String getOldComponentName() {
		return oldComponentName;
	}

	public void setOldComponentName(String oldComponentName) {
		this.oldComponentName = oldComponentName;
	}

	public int getAppendixImgSeqNo() {
		return appendixImgSeqNo;
	}

	public void setAppendixImgSeqNo(int appendixImgSeqNo) {
		this.appendixImgSeqNo = appendixImgSeqNo;
	}

	public boolean isMapAppendixImg() {
		return mapAppendixImg;
	}

	public void setMapAppendixImg(boolean mapAppendixImg) {
		this.mapAppendixImg = mapAppendixImg;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getSubClaExistsFlag() {
		return subClaExistsFlag;
	}

	public void setSubClaExistsFlag(String subClaExistsFlag) {
		this.subClaExistsFlag = subClaExistsFlag;
	}

	public int getHdnClauseSeqNo() {
		return hdnClauseSeqNo;
	}

	public void setHdnClauseSeqNo(int hdnClauseSeqNo) {
		this.hdnClauseSeqNo = hdnClauseSeqNo;
	}

	public String getRemoveAllOptCla() {
		return removeAllOptCla;
	}

	public void setRemoveAllOptCla(String removeAllOptCla) {
		this.removeAllOptCla = removeAllOptCla;
	}

	public String getDelModFlag() {
		return delModFlag;
	}

	public void setDelModFlag(String delModFlag) {
		this.delModFlag = delModFlag;
	}

	public int getModVersionNo() {
		return modVersionNo;
	}

	public void setModVersionNo(int modVersionNo) {
		this.modVersionNo = modVersionNo;
	}

	public String getUsedVersionFlag() {
		return usedVersionFlag;
	}

	public void setUsedVersionFlag(String usedVersionFlag) {
		this.usedVersionFlag = usedVersionFlag;
	}

	public String getChangeFromRefEdlNo() {
		return changeFromRefEdlNo;
	}

	public void setChangeFromRefEdlNo(String changeFromRefEdlNo) {
		this.changeFromRefEdlNo = changeFromRefEdlNo;
	}

	public String getChangeFromPartOfNo() {
		return changeFromPartOfNo;
	}

	public void setChangeFromPartOfNo(String changeFromPartOfNo) {
		this.changeFromPartOfNo = changeFromPartOfNo;
	}

	public String getChangeFromDwoNo() {
		return changeFromDwoNo;
	}

	public void setChangeFromDwoNo(String changeFromDwoNo) {
		this.changeFromDwoNo = changeFromDwoNo;
	}

	public String getChangeFromEnggComments() {
		return changeFromEnggComments;
	}

	public void setChangeFromEnggComments(String changeFromEnggComments) {
		this.changeFromEnggComments = changeFromEnggComments;
	}

	public String getChangeFromPriceBookNo() {
		return changeFromPriceBookNo;
	}

	public void setChangeFromPriceBookNo(String changeFromPriceBookNo) {
		this.changeFromPriceBookNo = changeFromPriceBookNo;
	}

	public String getChangeFromPartNo() {
		return changeFromPartNo;
	}

	public void setChangeFromPartNo(String changeFromPartNo) {
		this.changeFromPartNo = changeFromPartNo;
	}

	public String getChangeFromStdEqp() {
		return changeFromStdEqp;
	}

	public void setChangeFromStdEqp(String changeFromStdEqp) {
		this.changeFromStdEqp = changeFromStdEqp;
	}

	public String getPartOfNo() {
		return partOfNo;
	}

	public void setPartOfNo(String partOfNo) {
		this.partOfNo = partOfNo;
	}

	public String getStdEqpDesc() {
		return stdEqpDesc;
	}

	public void setStdEqpDesc(String stdEqpDesc) {
		this.stdEqpDesc = stdEqpDesc;
	}

	public ArrayList getFrmTableArrayData1() {
		return frmTableArrayData1;
	}

	public void setFrmTableArrayData1(ArrayList frmTableArrayData1) {
		this.frmTableArrayData1 = frmTableArrayData1;
	}

	public int getFrmTableDataColSize() {
		return frmTableDataColSize;
	}

	public void setFrmTableDataColSize(int frmTableDataColSize) {
		this.frmTableDataColSize = frmTableDataColSize;
	}

	public String getChangeFromCompGrpName() {
		return changeFromCompGrpName;
	}

	public void setChangeFromCompGrpName(String changeFromCompGrpName) {
		this.changeFromCompGrpName = changeFromCompGrpName;
	}

	public String getChangeFromCompName() {
		return changeFromCompName;
	}

	public void setChangeFromCompName(String changeFromCompName) {
		this.changeFromCompName = changeFromCompName;
	}

	public String getChangeToCompGrpName() {
		return changeToCompGrpName;
	}

	public void setChangeToCompGrpName(String changeToCompGrpName) {
		this.changeToCompGrpName = changeToCompGrpName;
	}

	public String getChangeToCompName() {
		return changeToCompName;
	}

	public void setChangeToCompName(String changeToCompName) {
		this.changeToCompName = changeToCompName;
	}

	public ArrayList getClauseDet() {
		return clauseDet;
	}

	public void setClauseDet(ArrayList clauseDet) {
		this.clauseDet = clauseDet;
	}

	public ArrayList getSectionDet() {
		return sectionDet;
	}

	public void setSectionDet(ArrayList sectionDet) {
		this.sectionDet = sectionDet;
	}

	public String getDwoNumber() {
		return dwoNumber;
	}

	public void setDwoNumber(String dwoNumber) {
		this.dwoNumber = dwoNumber;
	}

	public String getPartNo() {
		return partNo;
	}

	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

	public String getPriceBookNo() {
		return priceBookNo;
	}

	public void setPriceBookNo(String priceBookNo) {
		this.priceBookNo = priceBookNo;
	}

	
	
	
}