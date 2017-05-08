package com.EMD.LSDB.form.SpecMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.ComponentVO;

public class AddClauseOrderForm extends EMDForm {
	
	ArrayList standardEquipmentList;
	
	public int standardEquipmentSeqNo;
	
	public String newClauseDesc;
	
	public String[] refEdlNo;
	
	public String[] newEdlNo;
	
	public int[] component;
	
	public String[] clauseSeqNum;
	
	public int[] partOfSeqNo;
	
	public String[] clauseTableDataArray1;
	
	public String[] clauseTableDataArray2;
	
	public String[] clauseTableDataArray3;
	
	public String[] clauseTableDataArray4;
	
	public String[] clauseTableDataArray5;
	
	public String dwoNo;
	
	public String partNo;
	
	public String priceBookNo;
	
	public String comments;
	
	public String reason;
	
	public String orderNo;
	
	public String section;
	
	public String subSection;
	
	public String componentSeqNo;
	
	public String[] partOf;
	
	public int hdnModelSeqNo;
	
	public int hdnSubSecSeqNo;
	
	public int hdnOrderKey;
	
	public int hdnCustSeqNo;
	
	public int hdnsecSeq;
	
	public String hdnsecCode;
	
	public String hdnSecName;
	
	public String hdnRevCode;
	
	public String hdnOrderNo;
	
	public String hdnSection;
	
	public String hdnSubsection;
	
	public String componentGroupName;
	
	public int specStatusCode;
	
	//Added for CR-129
	public String hdnValidFlag;
	
	/*** Added to Check the Spec Status when display Reason Text Area in Jsp ***/
	
	/** Added For LSDB_CR-35 **
	 *  Added on 04-April-08
	 * 	by ps57222 
	 * 
	 * ***/
	
	public String clauseSource;
	
	/****** Added for CR-51 Alert System Engineer when Adding Clause to Order/Model***/
	
	public String roleID;
	
	public int specTypeSeqNo;
	
	public String componentName;
	
	
	//Added for new Order new Component
		
	private String hdnOrdrNewComp;
	
	private String validCompName;
	
	private String hdnOrderNumber;
	
    //Added for CR-74 VV49326 05-06-09
	
	public ArrayList arlClauseVO;
	
	private String deleterow;
	
	private String hdnComponentName;
	
	private ArrayList componentVO;
	
	//Added for CR_97 RJ85495 14-03-11
	private String newCRFlag="N";
	// Added for CR_97
	private String changeControlFlag;
	
	//Added for CR_109
	private ArrayList leadCompClauseVO;
	private int hdnClauseSeqNo;
	private ArrayList modelCompVO;
	private ArrayList leadClauseVO;
	
	/**
	 * @return Returns the leadCompClauseVO.
	 */
	public ArrayList getLeadCompClauseVO() {
		return leadCompClauseVO;
	}

	/**
	 * @param leadCompClauseVO The leadCompClauseVO to set.
	 */
	public void setLeadCompClauseVO(ArrayList leadCompClauseVO) {
		this.leadCompClauseVO = leadCompClauseVO;
	}

	/**
	 * @return Returns the hdnComponentName.
	 */
	public String getHdnComponentName() {
		return hdnComponentName;
	}

	/**
	 * @param hdnComponentName The hdnComponentName to set.
	 */
	public void setHdnComponentName(String hdnComponentName) {
		this.hdnComponentName = hdnComponentName;
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

	public String getHdnOrderNumber() {
		return hdnOrderNumber;
	}

	public void setHdnOrderNumber(String hdnOrderNumber) {
		this.hdnOrderNumber = hdnOrderNumber;
	}

	public String getValidCompName() {
		return validCompName;
	}

	public void setValidCompName(String validCompName) {
		this.validCompName = validCompName;
	}

	public String getHdnOrdrNewComp() {
		return hdnOrdrNewComp;
	}

	public void setHdnOrdrNewComp(String hdnOrdrNewComp) {
		this.hdnOrdrNewComp = hdnOrdrNewComp;
	}

	
	/**
	 * @return Returns the specTypeSeqNo.
	 */
	public int getSpecTypeSeqNo() {
		return specTypeSeqNo;
	}
	
	/**
	 * @param specTypeSeqNo The specTypeSeqNo to set.
	 */
	public void setSpecTypeSeqNo(int specTypeSeqNo) {
		this.specTypeSeqNo = specTypeSeqNo;
	}
	
	/**
	 * @return Returns the clauseSource.
	 */
	public String getClauseSource() {
		return clauseSource;
	}
	
	/**
	 * @param clauseSource The clauseSource to set.
	 */
	public void setClauseSource(String clauseSource) {
		this.clauseSource = clauseSource;
	}
	
	private int compGroupSeqNo;
	
	/**** Added For Attach To Clause CR *******/
	
	private int leadComponentSeqNo;
	
	private ArrayList compGroupList;
	
	/**** Added For Attach To Clause CR *******/
	
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
	
	public String getComponentGroupName() {
		return componentGroupName;
	}
	
	public void setComponentGroupName(String componentGroupName) {
		this.componentGroupName = componentGroupName;
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
	
	public ArrayList getCompGroupList() {
		return compGroupList;
	}
	
	public void setCompGroupList(ArrayList compGroupList) {
		this.compGroupList = compGroupList;
	}
	
	public ArrayList getStandardEquipmentList() {
		return standardEquipmentList;
	}
	
	public void setStandardEquipmentList(ArrayList standardEquipmentList) {
		this.standardEquipmentList = standardEquipmentList;
	}
	
	public int getStandardEquipmentSeqNo() {
		return standardEquipmentSeqNo;
	}
	
	public void setStandardEquipmentSeqNo(int standardEquipmentSeqNo) {
		this.standardEquipmentSeqNo = standardEquipmentSeqNo;
	}
	
	public String[] getClauseTableDataArray1() {
		return clauseTableDataArray1;
	}
	
	public void setClauseTableDataArray1(String[] clauseTableDataArray1) {
		this.clauseTableDataArray1 = clauseTableDataArray1;
	}
	
	public String[] getClauseTableDataArray2() {
		return clauseTableDataArray2;
	}
	
	public void setClauseTableDataArray2(String[] clauseTableDataArray2) {
		this.clauseTableDataArray2 = clauseTableDataArray2;
	}
	
	public String[] getClauseTableDataArray3() {
		return clauseTableDataArray3;
	}
	
	public void setClauseTableDataArray3(String[] clauseTableDataArray3) {
		this.clauseTableDataArray3 = clauseTableDataArray3;
	}
	
	public String[] getClauseTableDataArray4() {
		return clauseTableDataArray4;
	}
	
	public void setClauseTableDataArray4(String[] clauseTableDataArray4) {
		this.clauseTableDataArray4 = clauseTableDataArray4;
	}
	
	public String[] getClauseTableDataArray5() {
		return clauseTableDataArray5;
	}
	
	public void setClauseTableDataArray5(String[] clauseTableDataArray5) {
		this.clauseTableDataArray5 = clauseTableDataArray5;
	}
	
	public String getNewClauseDesc() {
		return newClauseDesc;
	}
	
	public void setNewClauseDesc(String newClauseDesc) {
		this.newClauseDesc = newClauseDesc;
	}
	
	public String[] getNewEdlNo() {
		return newEdlNo;
	}
	
	public void setNewEdlNo(String[] newEdlNo) {
		this.newEdlNo = newEdlNo;
	}
	
	public String[] getRefEdlNo() {
		return refEdlNo;
	}
	
	public void setRefEdlNo(String[] refEdlNo) {
		this.refEdlNo = refEdlNo;
	}
	
	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public int[] getPartOfSeqNo() {
		return partOfSeqNo;
	}
	
	public void setPartOfSeqNo(int[] partOfSeqNo) {
		this.partOfSeqNo = partOfSeqNo;
	}
	
	public String getReason() {
		return reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public String getOrderNo() {
		return orderNo;
	}
	
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public String getSection() {
		return section;
	}
	
	public void setSection(String section) {
		this.section = section;
	}
	
	public String getSubSection() {
		return subSection;
	}
	
	public void setSubSection(String subSection) {
		this.subSection = subSection;
	}
	
	public String getComponentSeqNo() {
		return componentSeqNo;
	}
	
	public void setComponentSeqNo(String componentSeqNo) {
		this.componentSeqNo = componentSeqNo;
	}
	
	public int[] getComponent() {
		return component;
	}
	
	public void setComponent(int[] component) {
		this.component = component;
	}
	
	public String[] getPartOf() {
		return partOf;
	}
	
	public void setPartOf(String[] partOf) {
		this.partOf = partOf;
	}
	
	public String getDwoNo() {
		return dwoNo;
	}
	
	public void setDwoNo(String dwoNo) {
		this.dwoNo = dwoNo;
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
	
	public int getHdnCustSeqNo() {
		return hdnCustSeqNo;
	}
	
	public void setHdnCustSeqNo(int hdnCustSeqNo) {
		this.hdnCustSeqNo = hdnCustSeqNo;
	}
	
	public int getHdnModelSeqNo() {
		return hdnModelSeqNo;
	}
	
	public void setHdnModelSeqNo(int hdnModelSeqNo) {
		this.hdnModelSeqNo = hdnModelSeqNo;
	}
	
	public int getHdnOrderKey() {
		return hdnOrderKey;
	}
	
	public void setHdnOrderKey(int hdnOrderKey) {
		this.hdnOrderKey = hdnOrderKey;
	}
	
	public int getHdnSubSecSeqNo() {
		return hdnSubSecSeqNo;
	}
	
	public void setHdnSubSecSeqNo(int hdnSubSecSeqNo) {
		this.hdnSubSecSeqNo = hdnSubSecSeqNo;
	}
	
	public String getHdnRevCode() {
		return hdnRevCode;
	}
	
	public void setHdnRevCode(String hdnRevCode) {
		this.hdnRevCode = hdnRevCode;
	}
	
	public String getHdnsecCode() {
		return hdnsecCode;
	}
	
	public void setHdnsecCode(String hdnsecCode) {
		this.hdnsecCode = hdnsecCode;
	}
	
	public String getHdnSecName() {
		return hdnSecName;
	}
	
	public void setHdnSecName(String hdnSecName) {
		this.hdnSecName = hdnSecName;
	}
	
	public int getHdnsecSeq() {
		return hdnsecSeq;
	}
	
	public void setHdnsecSeq(int hdnsecSeq) {
		this.hdnsecSeq = hdnsecSeq;
	}
	
	public String getHdnOrderNo() {
		return hdnOrderNo;
	}
	
	public void setHdnOrderNo(String hdnOrderNo) {
		this.hdnOrderNo = hdnOrderNo;
	}
	
	public String[] getClauseSeqNum() {
		return clauseSeqNum;
	}
	
	public void setClauseSeqNum(String[] clauseSeqNum) {
		this.clauseSeqNum = clauseSeqNum;
	}
	
	public String getHdnSection() {
		return hdnSection;
	}
	
	public void setHdnSection(String hdnSection) {
		this.hdnSection = hdnSection;
	}
	
	public String getHdnSubsection() {
		return hdnSubsection;
	}
	
	public void setHdnSubsection(String hdnSubsection) {
		this.hdnSubsection = hdnSubsection;
	}
	
	/**
	 * @return Returns the specStatusCode.
	 */
	public int getSpecStatusCode() {
		return specStatusCode;
	}
	
	/**
	 * @param specStatusCode The specStatusCode to set.
	 */
	public void setSpecStatusCode(int specStatusCode) {
		this.specStatusCode = specStatusCode;
	}
	
	/**
	 * @return Returns the roleID.
	 */
	public String getRoleID() {
		return roleID;
	}
	
	/**
	 * @param roleID The roleID to set.
	 */
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	/**
	 * @return Returns the arlClauseVO.
	 */
	public ArrayList getArlClauseVO() {
		return arlClauseVO;
	}

	/**
	 * @param arlClauseVO The arlClauseVO to set.
	 */
	public void setArlClauseVO(ArrayList arlClauseVO) {
		this.arlClauseVO = arlClauseVO;
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
	//Added for CR_97
	/**
	 * @return Returns the newCRFlag.
	 */
	public String getNewCRFlag() {
		return newCRFlag;
	}
	/**
	 * @param newCRFlag The newCRFlag to set.
	 */
	public void setNewCRFlag(String newCRFlag) {
		this.newCRFlag = newCRFlag;
	}

	// Added for CR_97
	
	public String getChangeControlFlag() {
		return changeControlFlag;
	}

	public void setChangeControlFlag(String changeControlFlag) {
		this.changeControlFlag = changeControlFlag;
	}
	// Ends Here.

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
	 * @return Returns the modelCompVO.
	 */
	public ArrayList getModelCompVO() {
		return modelCompVO;
	}

	/**
	 * @param modelCompVO The modelCompVO to set.
	 */
	public void setModelCompVO(ArrayList modelCompVO) {
		this.modelCompVO = modelCompVO;
	}

	/**
	 * @return Returns the leadClauseVO.
	 */
	public ArrayList getLeadClauseVO() {
		return leadClauseVO;
	}

	/**
	 * @param leadClauseVO The leadClauseVO to set.
	 */
	public void setLeadClauseVO(ArrayList leadClauseVO) {
		this.leadClauseVO = leadClauseVO;
	}

	public String getHdnValidFlag() {
		return hdnValidFlag;
	}

	public void setHdnValidFlag(String hdnValidFlag) {
		this.hdnValidFlag = hdnValidFlag;
	}

	
}
