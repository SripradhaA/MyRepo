package com.EMD.LSDB.vo.common;

import java.util.ArrayList;

/*******************************************************************************************
 * @author  Satyam Computer Services Ltd  
 * @version 1.0  
 * This class has the setter and getter methods for the Components 
 ******************************************************************************************/
public class ComponentVO extends EMDVO {
	
	private boolean applyToModelFlag;
	
	private String characterisationFlag;
	
	private String comments;
	
	private String componentIdentifier;
	
	private int componentGroupSeqNo;
	
	private String componentGroupName;
	
	private String componentName;
	
	private int componentSeqNo;
	
	private boolean defaultFlag;
	
	private boolean displayInSpecFlag;
	
	private int modelSeqNo;
	
	private int subSectionSeqNo;
	
	private String orderDefaultComp;
	
	private String componentDescription;
	
	//Change for LSDB_CR-74 by KA57588
	private String compColorFlag;
	
	/******************************* 
	 * The Attributes orderOneComponentName,orderTwoComponentName,orderOneCompDescName,orderTwoCompDescName
	 * are added for LSDB_CR-06
	 * Added o 15-April-08
	 * By ps57222
	 *
	 **************************/
	private String orderOneComponentName;
	
	private String orderTwoComponentName;
	
	private String orderOneCompDescName;
	
	private String orderTwoCompDescName;
	
	//	Added for CR-26
	private ArrayList modelsAffected;
	
	private ArrayList modelDefault;
	
	//Added for CR-58 to display Component Group name in Component Comparison Report
	
	private String componentGrpDesc;
	
	private String charFlag;
	
	private String compGrpIdentifier;
	
	private String compGrpValdFlag;
	
	//Added for CR-67 Unmap Component Group
	private String compLeadFlag;
	
	private String compDefFlag;
	
	private ArrayList clauseVOList;
	
	private String deletedFlag;
	
	//Added for CR-68 Order New Component
	private String orderNo;	

	//Added For CR_81 on 24-Dec-09 by RR68151
	private int compGrpTypeSeqNo;
	//Added For CR_93
	private String compSourceFlag=null;
	
	private int newClaSeqNo;
	
	private String orderCompColorFlag;
	
	private String orderCompClaDesc;
	
	private String newOrderNo;
	
	// Added for CR_97
	private String labelFlag;
	// Ends Here.
	
	// Added for CR_108
	private ArrayList ordersUsed;
	// Ends Here.
	
	//Added for CR_109
	private String validationFlag;
	//CR_109 Ends here
	
	//Added for CR_109 comments
	private int sortByflag;	
	//Ends here
	
	//Added for CR_110 selected Component
	private String selectedComponent;
	private int reviseCheck;
	public int getReviseCheck() {
		return reviseCheck;
	}

	public void setReviseCheck(int reviseCheck) {
		this.reviseCheck = reviseCheck;
	}

	public String getSelectedComponent() {
		return selectedComponent;
	}

	public void setSelectedComponent(String selectedComponent) {
		this.selectedComponent = selectedComponent;
	}

	//Ends Here
	
	
	public int getSortByflag() {
		return sortByflag;
	}

	public void setSortByflag(int sortByflag) {
		this.sortByflag = sortByflag;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public ArrayList getClauseVOList() {
		return clauseVOList;
	}

	public void setClauseVOList(ArrayList clauseVOList) {
		this.clauseVOList = clauseVOList;
	}

	public String getCompDefFlag() {
		return compDefFlag;
	}

	public void setCompDefFlag(String compDefFlag) {
		this.compDefFlag = compDefFlag;
	}

	public String getCompLeadFlag() {
		return compLeadFlag;
	}

	public void setCompLeadFlag(String compLeadFlag) {
		this.compLeadFlag = compLeadFlag;
	}

	/**
	 * @return Returns the compGrpValdFlag.
	 */
	public String getCompGrpValdFlag() {
		return compGrpValdFlag;
	}
	
	/**
	 * @param compGrpValdFlag The compGrpValdFlag to set.
	 */
	public void setCompGrpValdFlag(String compGrpValdFlag) {
		this.compGrpValdFlag = compGrpValdFlag;
	}
	
	/**
	 * @return Returns the compGrpIdentifier.
	 */
	public String getCompGrpIdentifier() {
		return compGrpIdentifier;
	}
	
	/**
	 * @param compGrpIdentifier The compGrpIdentifier to set.
	 */
	public void setCompGrpIdentifier(String compGrpIdentifier) {
		this.compGrpIdentifier = compGrpIdentifier;
	}
	
	/**
	 * @return Returns the charFlag.
	 */
	public String getCharFlag() {
		return charFlag;
	}
	
	/**
	 * @param charFlag The charFlag to set.
	 */
	public void setCharFlag(String charFlag) {
		this.charFlag = charFlag;
	}
	
	/**
	 * @return Returns the orderOneCompDescName.
	 */
	public String getOrderOneCompDescName() {
		return orderOneCompDescName;
	}
	
	/**
	 * @param orderOneCompDescName The orderOneCompDescName to set.
	 */
	public void setOrderOneCompDescName(String orderOneCompDescName) {
		this.orderOneCompDescName = orderOneCompDescName;
	}
	
	/**
	 * @return Returns the orderOneComponentName.
	 */
	public String getOrderOneComponentName() {
		return orderOneComponentName;
	}
	
	/**
	 * @param orderOneComponentName The orderOneComponentName to set.
	 */
	public void setOrderOneComponentName(String orderOneComponentName) {
		this.orderOneComponentName = orderOneComponentName;
	}
	
	/**
	 * @return Returns the orderTwoCompDescName.
	 */
	public String getOrderTwoCompDescName() {
		return orderTwoCompDescName;
	}
	
	/**
	 * @param orderTwoCompDescName The orderTwoCompDescName to set.
	 */
	public void setOrderTwoCompDescName(String orderTwoCompDescName) {
		this.orderTwoCompDescName = orderTwoCompDescName;
	}
	
	/**
	 * @return Returns the orderTwoComponentName.
	 */
	public String getOrderTwoComponentName() {
		return orderTwoComponentName;
	}
	
	/**
	 * @param orderTwoComponentName The orderTwoComponentName to set.
	 */
	public void setOrderTwoComponentName(String orderTwoComponentName) {
		this.orderTwoComponentName = orderTwoComponentName;
	}
	
	public boolean isApplyToModelFlag() {
		return applyToModelFlag;
	}
	
	public void setApplyToModelFlag(boolean applyToModelFlag) {
		this.applyToModelFlag = applyToModelFlag;
	}
	
	public String getCharacterisationFlag() {
		return characterisationFlag;
	}
	
	public void setCharacterisationFlag(String characterisationFlag) {
		this.characterisationFlag = characterisationFlag;
	}
	
	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public int getComponentGroupSeqNo() {
		return componentGroupSeqNo;
	}
	
	public void setComponentGroupSeqNo(int componentGroupSeqNo) {
		this.componentGroupSeqNo = componentGroupSeqNo;
	}
	
	public String getComponentName() {
		return componentName;
	}
	
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	
	public int getComponentSeqNo() {
		return componentSeqNo;
	}
	
	public void setComponentSeqNo(int componentSeqNo) {
		this.componentSeqNo = componentSeqNo;
	}
	
	public boolean isDefaultFlag() {
		return defaultFlag;
	}
	
	public void setDefaultFlag(boolean defaultFlag) {
		this.defaultFlag = defaultFlag;
	}
	
	public boolean isDisplayInSpecFlag() {
		return displayInSpecFlag;
	}
	
	public void setDisplayInSpecFlag(boolean displayInSpecFlag) {
		this.displayInSpecFlag = displayInSpecFlag;
	}
	
	public int getModelSeqNo() {
		return modelSeqNo;
	}
	
	public void setModelSeqNo(int modelSeqNo) {
		this.modelSeqNo = modelSeqNo;
	}
	
	public int getSubSectionSeqNo() {
		return subSectionSeqNo;
	}
	
	public void setSubSectionSeqNo(int subSectionSeqNo) {
		this.subSectionSeqNo = subSectionSeqNo;
	}
	
	/**
	 * @return Returns the orderDefaultComp.
	 */
	public String getOrderDefaultComp() {
		return orderDefaultComp;
	}
	
	/**
	 * @param orderDefaultComp The orderDefaultComp to set.
	 */
	public void setOrderDefaultComp(String orderDefaultComp) {
		this.orderDefaultComp = orderDefaultComp;
	}
	
	public String getComponentDescription() {
		return componentDescription;
	}
	
	public void setComponentDescription(String componentDescription) {
		this.componentDescription = componentDescription;
	}
	
	/**
	 * @return Returns the componentIdentifier.
	 */
	public String getComponentIdentifier() {
		return componentIdentifier;
	}
	
	/**
	 * @param componentIdentifier The componentIdentifier to set.
	 */
	public void setComponentIdentifier(String componentIdentifier) {
		this.componentIdentifier = componentIdentifier;
	}
	
	public String getComponentGroupName() {
		return componentGroupName;
	}
	
	public void setComponentGroupName(String componentGroupName) {
		this.componentGroupName = componentGroupName;
	}
	
	/**
	 * @return Returns the modelDefault.
	 */
	public ArrayList getModelDefault() {
		return modelDefault;
	}
	
	/**
	 * @param modelDefault The modelDefault to set.
	 */
	public void setModelDefault(ArrayList modelDefault) {
		this.modelDefault = modelDefault;
	}
	
	/**
	 * @return Returns the modelsAffected.
	 */
	public ArrayList getModelsAffected() {
		return modelsAffected;
	}
	
	/**
	 * @param modelsAffected The modelsAffected to set.
	 */
	public void setModelsAffected(ArrayList modelsAffected) {
		this.modelsAffected = modelsAffected;
	}
	
	/**
	 * @return Returns the componentGrpDesc.
	 */
	public String getComponentGrpDesc() {
		return componentGrpDesc;
	}
	
	/**
	 * @param componentGrpDesc The componentGrpDesc to set.
	 */
	public void setComponentGrpDesc(String componentGrpDesc) {
		this.componentGrpDesc = componentGrpDesc;
	}

	public String getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(String deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	/**
	 * @return Returns the compColorFlag.
	 */
	public String getCompColorFlag() {
		return compColorFlag;
	}

	/**
	 * @param compColorFlag The compColorFlag to set.
	 */
	public void setCompColorFlag(String compColorFlag) {
		this.compColorFlag = compColorFlag;
	}

	/**
	 * @return Returns the compGrpTypeSeqNo.
	 */
	public int getCompGrpTypeSeqNo() {
		return compGrpTypeSeqNo;
	}

	/**
	 * @param compGrpTypeSeqNo The compGrpTypeSeqNo to set.
	 */
	public void setCompGrpTypeSeqNo(int compGrpTypeSeqNo) {
		this.compGrpTypeSeqNo = compGrpTypeSeqNo;
	}

	public String getCompSourceFlag() {
		return compSourceFlag;
	}

	public void setCompSourceFlag(String compSourceFlag) {
		this.compSourceFlag = compSourceFlag;
	}

	public int getNewClaSeqNo() {
		return newClaSeqNo;
	}

	public void setNewClaSeqNo(int newClaSeqNo) {
		this.newClaSeqNo = newClaSeqNo;
	}

	/**
	 * @return Returns the orderCompClaDesc.
	 */
	public String getOrderCompClaDesc() {
		return orderCompClaDesc;
	}

	/**
	 * @param orderCompClaDesc The orderCompClaDesc to set.
	 */
	public void setOrderCompClaDesc(String orderCompClaDesc) {
		this.orderCompClaDesc = orderCompClaDesc;
	}

	/**
	 * @return Returns the orderCompColorFlag.
	 */
	public String getOrderCompColorFlag() {
		return orderCompColorFlag;
	}

	/**
	 * @param orderCompColorFlag The orderCompColorFlag to set.
	 */
	public void setOrderCompColorFlag(String orderCompColorFlag) {
		this.orderCompColorFlag = orderCompColorFlag;
	}

	/**
	 * @return Returns the newOrderNo.
	 */
	public String getNewOrderNo() {
		return newOrderNo;
	}

	/**
	 * @param newOrderNo The newOrderNo to set.
	 */
	public void setNewOrderNo(String newOrderNo) {
		this.newOrderNo = newOrderNo;
	}

	// Added for CR_97
	public String getLabelFlag() {
		return labelFlag;
	}

	public void setLabelFlag(String labelFlag) {
		this.labelFlag = labelFlag;
	}

	/**
	 * @return Returns the ordersUsed.
	 */
	public ArrayList getOrdersUsed() {
		return ordersUsed;
	}

	/**
	 * @param ordersUsed The ordersUsed to set.
	 */
	public void setOrdersUsed(ArrayList ordersUsed) {
		this.ordersUsed = ordersUsed;
	}

	public String getValidationFlag() {
		return validationFlag;
	}

	public void setValidationFlag(String validationFlag) {
		this.validationFlag = validationFlag;
	}

}