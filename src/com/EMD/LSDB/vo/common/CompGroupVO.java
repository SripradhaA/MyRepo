/*AK49339
 * Created on Aug 14, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.vo.common;

import java.util.ArrayList;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the setter and getter methods for the Component
 * /***************************************************************************
 * ------------------------------------------------------------------------------------------------------
 *     Date            version       modify by                 comments                              Remarks 
 * 15/03/2011           1.0               SD41630           Added  selectedSpecType                	Added for CR_97
 * 										
 * 										 
  **************************************************************************/
 
public class CompGroupVO extends EMDVO {
	
	public CompGroupVO() {
		
	}
	
	private int componentGroupSeqNo;
	
	private String componentGroupName;
	
	private String componentGroupIdentifier;
	
	private String comments;
	
	private String characterisationFlag;
	
	private String componentgroupCat;
	
	private String[] charRadio;
	
	private String[] charYes;
	
	private String[] charNo;
	
	private String validFlag;
	
	private ArrayList component;
	
	private ArrayList componentVO; /* Added For Attach to Clause CR ** */
	
	// Added for CR-26 Component Group/Component Report
	private ArrayList modelAffected;
	
	private ArrayList modelDefault;
	
	private String leadFlag;
	
	private ComponentVO compVO;
	
	//	Added for CR-67 Un Map Component Group
	private int specTypeSeqNo;
	
	private String specType;
	
	private int modelSeqNo;
	
	private String modelName;
	
	private int sectionSeqNo;
	
	private String secCode;
	
	private String sectionName;
	
	private int subSectionSeqNo;
	
	private String subSectionName;
	
	private String subSectionCode;
	
	//change for LSDB_CR-77
	private boolean displayInCOCFlag;
	
	//Added For CR_81 on 24-Dec-09 by RR68151
	private int compGrpTypeSeqNo;
	
	private String compGrpTypeName;
	//Added For CR_81 on 24-Dec-09 by RR68151
	
//	Added For CR_97  on 15 march 2011 by sd41630 
	private String selectedSpecType;
	
	// Added for CR_101 to include order level comp in report
	private String incOrderComp;
	//CR_101 Ends here
	
	//Added For CR_104  
	private int[] modelSelected;
	
	//Added For CR_108 to bring components in Order Report
	private int componentSeqNo;
	
	private String showLatestPubSpecFlag;
	
	//Added for CR_118 Display in COC
	private String dispInCOC;
	//Added for CR_118 Ends Display in COC
	
	//Adde for CR-121
	private ArrayList specificationItemVO;
	private ArrayList ItemVO;
	private String[] orderKeys;
	private String specDesc;
	
	//Added for CR-121 for sorting by Vb106565 starts here 
	private int orderbyFlag;
	
	private int orderbyCompgrpFlag;
	
	private int orderbyCompFlag;
	
	public String getDispInCOC() {
		return dispInCOC;
	}

	public void setDispInCOC(String dispInCOC) {
		this.dispInCOC = dispInCOC;
	}

	/**
	 * @return Returns the characterisationFlag.
	 */
	public String getCharacterisationFlag() {
		return characterisationFlag;
	}
	
	/**
	 * @param characterisationFlag
	 *            The characterisationFlag to set.
	 */
	public void setCharacterisationFlag(String characterisationFlag) {
		this.characterisationFlag = characterisationFlag;
	}
	
	/**
	 * @return Returns the charNo.
	 */
	public String[] getCharNo() {
		return charNo;
	}
	
	/**
	 * @param charNo
	 *            The charNo to set.
	 */
	public void setCharNo(String[] charNo) {
		this.charNo = charNo;
	}
	
	/**
	 * @return Returns the charYes.
	 */
	public String[] getCharYes() {
		return charYes;
	}
	
	/**
	 * @param charYes
	 *            The charYes to set.
	 */
	public void setCharYes(String[] charYes) {
		this.charYes = charYes;
	}
	
	/**
	 * @return Returns the component.
	 */
	public ArrayList getComponent() {
		return component;
	}
	
	/**
	 * @param component
	 *            The component to set.
	 */
	public void setComponent(ArrayList component) {
		this.component = component;
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
	 * @return Returns the componentgroupCat.
	 */
	public String getComponentgroupCat() {
		return componentgroupCat;
	}
	
	/**
	 * @param componentgroupCat
	 *            The componentgroupCat to set.
	 */
	public void setComponentgroupCat(String componentgroupCat) {
		this.componentgroupCat = componentgroupCat;
	}
	
	/**
	 * @return Returns the componentGroupName.
	 */
	public String getComponentGroupName() {
		return componentGroupName;
	}
	
	/**
	 * @param componentGroupName
	 *            The componentGroupName to set.
	 */
	public void setComponentGroupName(String componentGroupName) {
		this.componentGroupName = componentGroupName;
	}
	
	/**
	 * @return Returns the componentGroupSeqNo.
	 */
	public int getComponentGroupSeqNo() {
		return componentGroupSeqNo;
	}
	
	/**
	 * @param componentGroupSeqNo
	 *            The componentGroupSeqNo to set.
	 */
	public void setComponentGroupSeqNo(int componentGroupSeqNo) {
		this.componentGroupSeqNo = componentGroupSeqNo;
	}
	
	/**
	 * @return Returns the charRadio.
	 */
	public String[] getCharRadio() {
		return charRadio;
	}
	
	/**
	 * @param charRadio
	 *            The charRadio to set.
	 */
	public void setCharRadio(String[] charRadio) {
		this.charRadio = charRadio;
	}
	
	/**
	 * @return Returns the componentGroupIdentifier.
	 */
	public String getComponentGroupIdentifier() {
		return componentGroupIdentifier;
	}
	
	/**
	 * @param componentGroupIdentifier
	 *            The componentGroupIdentifier to set.
	 */
	public void setComponentGroupIdentifier(String componentGroupIdentifier) {
		this.componentGroupIdentifier = componentGroupIdentifier;
	}
	
	/**
	 * @return Returns the validFlag.
	 */
	public String getValidFlag() {
		return validFlag;
	}
	
	/**
	 * @param validFlag
	 *            The validFlag to set.
	 */
	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
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
	
	public String getLeadFlag() {
		return leadFlag;
	}
	
	public void setLeadFlag(String leadFlag) {
		this.leadFlag = leadFlag;
	}
	
	public ComponentVO getCompVO() {
		return compVO;
	}
	
	public void setCompVO(ComponentVO compVO) {
		this.compVO = compVO;
	}
	
	/**
	 * @return Returns the modelAffected.
	 */
	public ArrayList getModelAffected() {
		return modelAffected;
	}
	
	/**
	 * @param modelAffected
	 *            The modelAffected to set.
	 */
	public void setModelAffected(ArrayList modelAffected) {
		this.modelAffected = modelAffected;
	}
	
	/**
	 * @return Returns the modelDefault.
	 */
	public ArrayList getModelDefault() {
		return modelDefault;
	}
	
	/**
	 * @param modelDefault
	 *            The modelDefault to set.
	 */
	public void setModelDefault(ArrayList modelDefault) {
		this.modelDefault = modelDefault;
	}
	
	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public int getModelSeqNo() {
		return modelSeqNo;
	}

	public void setModelSeqNo(int modelSeqNo) {
		this.modelSeqNo = modelSeqNo;
	}

	public String getSecCode() {
		return secCode;
	}

	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public int getSectionSeqNo() {
		return sectionSeqNo;
	}

	public void setSectionSeqNo(int sectionSeqNo) {
		this.sectionSeqNo = sectionSeqNo;
	}

	public String getSubSectionCode() {
		return subSectionCode;
	}

	public void setSubSectionCode(String subSectionCode) {
		this.subSectionCode = subSectionCode;
	}

	public String getSubSectionName() {
		return subSectionName;
	}

	public void setSubSectionName(String subSectionName) {
		this.subSectionName = subSectionName;
	}

	public int getSubSectionSeqNo() {
		return subSectionSeqNo;
	}

	public void setSubSectionSeqNo(int subSectionSeqNo) {
		this.subSectionSeqNo = subSectionSeqNo;
	}
	
	public String getSpecType() {
		return specType;
	}

	public void setSpecType(String specType) {
		this.specType = specType;
	}

	public int getSpecTypeSeqNo() {
		return specTypeSeqNo;
	}

	public void setSpecTypeSeqNo(int specTypeSeqNo) {
		this.specTypeSeqNo = specTypeSeqNo;
	}

	/**
	 * @return Returns the displayCOCFlag.
	 */
	public boolean isDisplayInCOCFlag() {
		return displayInCOCFlag;
	}

	/**
	 * @param displayCOCFlag The displayCOCFlag to set.
	 */
	public void setDisplayInCOCFlag(boolean displayInCOCFlag) {
		this.displayInCOCFlag = displayInCOCFlag;
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

	/**
	 * @return Returns the compGrpTypeName.
	 */
	public String getCompGrpTypeName() {
		return compGrpTypeName;
	}

	/**
	 * @param compGrpTypeName The compGrpTypeName to set.
	 */
	public void setCompGrpTypeName(String compGrpTypeName) {
		this.compGrpTypeName = compGrpTypeName;
	}

	public String getSelectedSpecType() {
		return selectedSpecType;
	}

	public void setSelectedSpecType(String selectedSpecType) {
		this.selectedSpecType = selectedSpecType;
	}

//	 Added for CR_101 to include order level comp in report	
	/**
	 * @return Returns the incOrderComp.
	 */
	public String getIncOrderComp() {
		return incOrderComp;
	}

	/**
	 * @param incOrderComp The incOrderComp to set.
	 */
	public void setIncOrderComp(String incOrderComp) {
		this.incOrderComp = incOrderComp;
	}

	public int[] getModelSelected() {
		return modelSelected;
	}

	public void setModelSelected(int[] modelSelected) {
		this.modelSelected = modelSelected;
	}

	/**
	 * @return Returns the componentSeqNo.
	 */
	public int getComponentSeqNo() {
		return componentSeqNo;
	}

	/**
	 * @param componentSeqNo The componentSeqNo to set.
	 */
	public void setComponentSeqNo(int componentSeqNo) {
		this.componentSeqNo = componentSeqNo;
	}

	/**
	 * @return Returns the showLatestPubSpecFlag.
	 */
	public String getShowLatestPubSpecFlag() {
		return showLatestPubSpecFlag;
	}

	/**
	 * @param showLatestPubSpecFlag The showLatestPubSpecFlag to set.
	 */
	public void setShowLatestPubSpecFlag(String showLatestPubSpecFlag) {
		this.showLatestPubSpecFlag = showLatestPubSpecFlag;
	}
// CR_101 Ends here
	
	public ArrayList getSpecificationItemVO() {
		return specificationItemVO;
	}

	public void setSpecificationItemVO(ArrayList specificationItemVO) {
		this.specificationItemVO = specificationItemVO;
	}

	public ArrayList getItemVO() {
		return ItemVO;
	}

	public void setItemVO(ArrayList itemVO) {
		ItemVO = itemVO;
	}

	
	public String[] getOrderKeys() {
		return orderKeys;
	}

	public void setOrderKeys(String[] orderKeys) {
		this.orderKeys = orderKeys;
	}

	public String getSpecDesc() {
		return specDesc;
	}

	public void setSpecDesc(String specDesc) {
		this.specDesc = specDesc;
	}

	public int getOrderbyCompFlag() {
		return orderbyCompFlag;
	}

	public void setOrderbyCompFlag(int orderbyCompFlag) {
		this.orderbyCompFlag = orderbyCompFlag;
	}

	public int getOrderbyCompgrpFlag() {
		return orderbyCompgrpFlag;
	}

	public void setOrderbyCompgrpFlag(int orderbyCompgrpFlag) {
		this.orderbyCompgrpFlag = orderbyCompgrpFlag;
	}

	public int getOrderbyFlag() {
		return orderbyFlag;
	}

	public void setOrderbyFlag(int orderbyFlag) {
		this.orderbyFlag = orderbyFlag;
	}
	
	
	
}
