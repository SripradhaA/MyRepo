/**
 * 
 */
package com.EMD.LSDB.form.SpecMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

/**
 * @author ps57222
 * 
 */
public class MainFeatureInfoForm extends EMDForm {
	
	private int compinfoSeqNo;
	
	private String componentDesc;
	
	private String hdCompDesc;
	
	private int orderKey;
	
	private String dataLocationType;
	
	private ArrayList mainFeatureList;
	
	private String orderNo;
	
	private String compDesc;
	
	public String colourFlag;
	
	public String specStatus;
	
	public String actionType;
	
	// Added for LSDB_CR-62 by ka57588
	private ArrayList modelMainFeatureList;
	
	private boolean hdnDispSpec;
	
	private int compSeqNo;
	
   //	Added for CR-74
	private ArrayList revisionList;
	
	private int revCode;
	
	//Added For CR_84
	private String perfCurveLinkFlag;
	
	private String genArrLinkFlag;
	//Added for CR_93
	private String rowIndexList;
	//Added For CR_100
	private ArrayList specStatusList;
	private int specStatusCode;
	private int currentSpecStatus;
	private String revFlag;
	private String finalFlag;
	
	//Added For CR_104
	
	private String bodyCont;
	private String subject;
	private String custMdlName;
	private String hdncustMdlName;
	private String genInfoText;

	//CR_104 Ends here
	//Added for CR_104 - Preserve general Information Flag
	private String presrveGenInfoFlag;
	
	//Added for CR-113 For QA-Fix
	private String orderSecName;
	private String orderSecCode;
	private int orderSecSeqNo;
	private ArrayList secDetail;
	private ArrayList clauseDetail;
	
	
	public String getPresrveGenInfoFlag() {
		return presrveGenInfoFlag;
	}
	
	public void setPresrveGenInfoFlag(String presrveGenInfoFlag) {
		this.presrveGenInfoFlag = presrveGenInfoFlag;
	}
	
	//CR_104 Ends here
	
	/**
	 * @return Returns the revCode.
	 */
	public int getRevCode() {
		return revCode;
	}

	/**
	 * @param revCode The revCode to set.
	 */
	public void setRevCode(int revCode) {
		this.revCode = revCode;
	}

	/**
	 * @return Returns the revisionList.
	 */
	public ArrayList getRevisionList() {
		return revisionList;
	}

	/**
	 * @param revisionList The revisionList to set.
	 */
	public void setRevisionList(ArrayList revisionList) {
		this.revisionList = revisionList;
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
	 * @return Returns the hdnDispSpec.
	 */
	public boolean isHdnDispSpec() {
		return hdnDispSpec;
	}
	
	/**
	 * @param hdnDispSpec
	 *            The hdnDispSpec to set.
	 */
	public void setHdnDispSpec(boolean hdnDispSpec) {
		this.hdnDispSpec = hdnDispSpec;
	}
	
	/**
	 * @return Returns the modelMainFeatureList.
	 */
	public ArrayList getModelMainFeatureList() {
		return modelMainFeatureList;
	}
	
	/**
	 * @param modelMainFeatureList
	 *            The modelMainFeatureList to set.
	 */
	public void setModelMainFeatureList(ArrayList modelMainFeatureList) {
		this.modelMainFeatureList = modelMainFeatureList;
	}
	
	public String getActionType() {
		return actionType;
	}
	
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	
	public String getSpecStatus() {
		return specStatus;
	}
	
	public void setSpecStatus(String specStatus) {
		this.specStatus = specStatus;
	}
	
	/**
	 * @return Returns the colourFlag.
	 */
	public String getColourFlag() {
		return colourFlag;
	}
	
	/**
	 * @param colourFlag
	 *            The colourFlag to set.
	 */
	public void setColourFlag(String colourFlag) {
		this.colourFlag = colourFlag;
	}
	
	/**
	 * @return Returns the compDesc.
	 */
	public String getCompDesc() {
		return compDesc;
	}
	
	/**
	 * @param compDesc
	 *            The compDesc to set.
	 */
	public void setCompDesc(String compDesc) {
		this.compDesc = compDesc;
	}
	
	/**
	 * @return Returns the mainFeatureList.
	 */
	public ArrayList getMainFeatureList() {
		return mainFeatureList;
	}
	
	/**
	 * @param mainFeatureList
	 *            The mainFeatureList to set.
	 */
	public void setMainFeatureList(ArrayList mainFeatureList) {
		this.mainFeatureList = mainFeatureList;
	}
	
	/**
	 * @return Returns the dataLocationType.
	 */
	public String getDataLocationType() {
		return dataLocationType;
	}
	
	/**
	 * @param dataLocationType
	 *            The dataLocationType to set.
	 */
	public void setDataLocationType(String dataLocationType) {
		this.dataLocationType = dataLocationType;
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
	 * @return Returns the hdCompDesc.
	 */
	public String getHdCompDesc() {
		return hdCompDesc;
	}
	
	/**
	 * @param hdCompDesc
	 *            The hdCompDesc to set.
	 */
	public void setHdCompDesc(String hdCompDesc) {
		this.hdCompDesc = hdCompDesc;
	}
	
	/**
	 * @return Returns the compinfoSeqNo.
	 */
	public int getCompinfoSeqNo() {
		return compinfoSeqNo;
	}
	
	/**
	 * @param compinfoSeqNo
	 *            The compinfoSeqNo to set.
	 */
	public void setCompinfoSeqNo(int compinfoSeqNo) {
		this.compinfoSeqNo = compinfoSeqNo;
	}
	
	/**
	 * @return Returns the componentDesc.
	 */
	public String getComponentDesc() {
		return componentDesc;
	}
	
	/**
	 * @param componentDesc
	 *            The componentDesc to set.
	 */
	public void setComponentDesc(String componentDesc) {
		this.componentDesc = componentDesc;
	}
	
	/**
	 * @return Returns the sectionList.
	 */
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
	 * @return Returns the genArrLinkFlag.
	 */
	public String getGenArrLinkFlag() {
		return genArrLinkFlag;
	}

	/**
	 * @param genArrLinkFlag The genArrLinkFlag to set.
	 */
	public void setGenArrLinkFlag(String genArrLinkFlag) {
		this.genArrLinkFlag = genArrLinkFlag;
	}

	/**
	 * @return Returns the perfCurveLinkFlag.
	 */
	public String getPerfCurveLinkFlag() {
		return perfCurveLinkFlag;
	}

	/**
	 * @param perfCurveLinkFlag The perfCurveLinkFlag to set.
	 */
	public void setPerfCurveLinkFlag(String perfCurveLinkFlag) {
		this.perfCurveLinkFlag = perfCurveLinkFlag;
	}

	public String getRowIndexList() {
		return rowIndexList;
	}

	public void setRowIndexList(String rowIndexList) {
		this.rowIndexList = rowIndexList;
	}

	public ArrayList getSpecStatusList() {
		return specStatusList;
	}

	public void setSpecStatusList(ArrayList specStatusList) {
		this.specStatusList = specStatusList;
	}

	public int getSpecStatusCode() {
		return specStatusCode;
	}

	public void setSpecStatusCode(int specStatusCode) {
		this.specStatusCode = specStatusCode;
	}

	public int getCurrentSpecStatus() {
		return currentSpecStatus;
	}

	public void setCurrentSpecStatus(int currentSpecStatus) {
		this.currentSpecStatus = currentSpecStatus;
	}

	public String getRevFlag() {
		return revFlag;
	}

	public void setRevFlag(String revFlag) {
		this.revFlag = revFlag;
	}

	public String getFinalFlag() {
		return finalFlag;
	}

	public void setFinalFlag(String finalFlag) {
		this.finalFlag = finalFlag;
	}

	/**
	 * @return Returns the genInfoText.
	 */
	public String getGenInfoText() {
		return genInfoText;
	}

	/**
	 * @param genInfoText The genInfoText to set.
	 */
	public void setGenInfoText(String genInfoText) {
		this.genInfoText = genInfoText;
	}
public String getBodyCont() {
		return bodyCont;
	}

	public void setBodyCont(String bodyCont) {
		this.bodyCont = bodyCont;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCustMdlName() {
		return custMdlName;
	}

	public void setCustMdlName(String custMdlName) {
		this.custMdlName = custMdlName;
	}

	public String getOrderSecCode() {
		return orderSecCode;
	}

	public void setOrderSecCode(String orderSecCode) {
		this.orderSecCode = orderSecCode;
	}

	public String getOrderSecName() {
		return orderSecName;
	}

	public void setOrderSecName(String orderSecName) {
		this.orderSecName = orderSecName;
	}

	public int getOrderSecSeqNo() {
		return orderSecSeqNo;
	}

	public void setOrderSecSeqNo(int orderSecSeqNo) {
		this.orderSecSeqNo = orderSecSeqNo;
	}

	public ArrayList getSecDetail() {
		return secDetail;
	}

	public void setSecDetail(ArrayList secDetail) {
		this.secDetail = secDetail;
	}

	public ArrayList getClauseDetail() {
		return clauseDetail;
	}

	public void setClauseDetail(ArrayList clauseDetail) {
		this.clauseDetail = clauseDetail;
	}
	
}
