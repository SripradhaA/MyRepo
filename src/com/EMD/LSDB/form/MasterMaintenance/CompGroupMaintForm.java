/*AK49339
 * Created on Aug 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.form.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the form fields for the Component Group
 ******************************************************************************/

public class CompGroupMaintForm extends EMDForm {
	private int compGroupSeqNo;
	
	private String compgrpCat = null;
	
	private String compgrpName = null;
	
	private String compgrpDesc = null;
	
	private String compgrpIdentifier = null;
	
	private String charzFlag = null;
	
	private ArrayList compgroupList = new ArrayList();
	
	private String hdncompgrpCat = null;
	
	private String hdncompgrpName = null;
	
	private String hdncompgrpDesc = null;
	
	private String hdncompgrpIdentifier = null;
	
	private String hdncharzFlag = null;
	
	private String hdncharacterisationFlag = null;
	
	private String[] charRadio;
	
	private String componentGroupSeqNo = null;
	
	/* Added For Valid Component Group Flag Change Request */
	
	private String validFlag;
	
	private String[] validRadio;
	
	private String hdnValidFlag;
	
	//change for LSDB_CR-77
	private boolean displayCOCFlag;
	
	private boolean hdnDisplayInCOC;
	
	//Added For CR_81 on 24-Dec-09 by RR68151
	private int compGroupTypeSeqNo;
	
	private ArrayList compGroupTypeList = new ArrayList();
	
	private int hdncompGroupTypeSeqNo;
	//Added For CR_81 on 24-Dec-09 by RR68151
	
//Added For CR_97  on 15 march 2011 by SD41630
	//Added For CR_101
	private boolean includeOrderFlag;
	
	private String hdnSelSpecType;
	
	//Added For CR_104
	ArrayList modelList;
	private int[] modelSeqNos;
	private String hdnSelectedMdlNames;
	private String hdnSelSpecTypeName;
	private String[] hdnSelModels;
	private boolean hdnIncludeOrderFlag;
	private String selectedMdlNames;
	//added for CR_104 fix
	private String includeOrderONOFF=null;
	
	//Added For CR_108 Components in Order Report
	private int modelSeqNo;
	private ArrayList compList = new ArrayList();
	private int componentSeqNo;
	private boolean showLatSpecFlag;
	private String hdncompName = null;
	private String hdnShowLatSpecFlag = null;
	private ArrayList compInOrdList = new ArrayList();
	
	//Added for CR-121
	private ArrayList orderList = new ArrayList();
	private ArrayList specItemList = new ArrayList();
	
	//Added for CR-121 for sorting by Vb106565 starts here 
	
    private int orderbyCompGroupFlag ;
	
	private String columnheader;
	
	private int orderbyCompgrpFlag;
	
	private int orderbyCompFlag;
	
	//Added for CR-121 for sorting by Vb106565 ends here 	

	
	
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
	 * @return Returns the hdnValidFlag.
	 */
	public String getHdnValidFlag() {
		return hdnValidFlag;
	}
	
	/**
	 * @param hdnValidFlag
	 *            The hdnValidFlag to set.
	 */
	public void setHdnValidFlag(String hdnValidFlag) {
		this.hdnValidFlag = hdnValidFlag;
	}
	
	/**
	 * @return Returns the validRadio.
	 */
	public String[] getValidRadio() {
		return validRadio;
	}
	
	/**
	 * @param validRadio
	 *            The validRadio to set.
	 */
	public void setValidRadio(String[] validRadio) {
		this.validRadio = validRadio;
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
	 * @return Returns the componentGroupSeqNo.
	 */
	public String getComponentGroupSeqNo() {
		return componentGroupSeqNo;
	}
	
	/**
	 * @param componentGroupSeqNo
	 *            The componentGroupSeqNo to set.
	 */
	public void setComponentGroupSeqNo(String componentGroupSeqNo) {
		this.componentGroupSeqNo = componentGroupSeqNo;
	}
	
	/**
	 * @return Returns the compgroupList.
	 */
	public ArrayList getCompgroupList() {
		return compgroupList;
	}
	
	/**
	 * @param compgroupList
	 *            The compgroupList to set.
	 */
	public void setCompgroupList(ArrayList compgroupList) {
		this.compgroupList = compgroupList;
	}
	
	/**
	 * @return Returns the compgrpCat.
	 */
	public String getCompgrpCat() {
		return compgrpCat;
	}
	
	/**
	 * @param compgrpCat
	 *            The compgrpCat to set.
	 */
	public void setCompgrpCat(String compgrpCat) {
		this.compgrpCat = compgrpCat;
	}
	
	/**
	 * @return Returns the compgrpDesc.
	 */
	public String getCompgrpDesc() {
		return compgrpDesc;
	}
	
	/**
	 * @param compgrpDesc
	 *            The compgrpDesc to set.
	 */
	public void setCompgrpDesc(String compgrpDesc) {
		this.compgrpDesc = compgrpDesc;
	}
	
	/**
	 * @return Returns the compgrpName.
	 */
	public String getCompgrpName() {
		return compgrpName;
	}
	
	/**
	 * @param compgrpName
	 *            The compgrpName to set.
	 */
	public void setCompgrpName(String compgrpName) {
		this.compgrpName = compgrpName;
	}
	
	/**
	 * @return Returns the compGroupSeqNo.
	 */
	public int getCompGroupSeqNo() {
		return compGroupSeqNo;
	}
	
	/**
	 * @param compGroupSeqNo
	 *            The compGroupSeqNo to set.
	 */
	public void setCompGroupSeqNo(int compGroupSeqNo) {
		this.compGroupSeqNo = compGroupSeqNo;
	}
	
	/**
	 * @return Returns the hdncompgrpCat.
	 */
	public String getHdncompgrpCat() {
		return hdncompgrpCat;
	}
	
	/**
	 * @param hdncompgrpCat
	 *            The hdncompgrpCat to set.
	 */
	public void setHdncompgrpCat(String hdncompgrpCat) {
		this.hdncompgrpCat = hdncompgrpCat;
	}
	
	/**
	 * @return Returns the hdncompgrpDesc.
	 */
	public String getHdncompgrpDesc() {
		return hdncompgrpDesc;
	}
	
	/**
	 * @param hdncompgrpDesc
	 *            The hdncompgrpDesc to set.
	 */
	public void setHdncompgrpDesc(String hdncompgrpDesc) {
		this.hdncompgrpDesc = hdncompgrpDesc;
	}
	
	/**
	 * @return Returns the hdncompgrpName.
	 */
	public String getHdncompgrpName() {
		return hdncompgrpName;
	}
	
	/**
	 * @param hdncompgrpName
	 *            The hdncompgrpName to set.
	 */
	public void setHdncompgrpName(String hdncompgrpName) {
		this.hdncompgrpName = hdncompgrpName;
	}
	
	/**
	 * @return Returns the hdncharacterisationFlag.
	 */
	public String getHdncharacterisationFlag() {
		return hdncharacterisationFlag;
	}
	
	/**
	 * @param hdncharacterisationFlag
	 *            The hdncharacterisationFlag to set.
	 */
	public void setHdncharacterisationFlag(String hdncharacterisationFlag) {
		this.hdncharacterisationFlag = hdncharacterisationFlag;
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
	 * @return Returns the compgrpIdentifier.
	 */
	public String getCompgrpIdentifier() {
		return compgrpIdentifier;
	}
	
	/**
	 * @param compgrpIdentifier
	 *            The compgrpIdentifier to set.
	 */
	public void setCompgrpIdentifier(String compgrpIdentifier) {
		this.compgrpIdentifier = compgrpIdentifier;
	}
	
	/**
	 * @return Returns the hdncompgrpIdentifier.
	 */
	public String getHdncompgrpIdentifier() {
		return hdncompgrpIdentifier;
	}
	
	/**
	 * @param hdncompgrpIdentifier
	 *            The hdncompgrpIdentifier to set.
	 */
	public void setHdncompgrpIdentifier(String hdncompgrpIdentifier) {
		this.hdncompgrpIdentifier = hdncompgrpIdentifier;
	}
	
	/**
	 * @return Returns the charzFlag.
	 */
	public String getCharzFlag() {
		return charzFlag;
	}
	
	/**
	 * @param charzFlag
	 *            The charzFlag to set.
	 */
	public void setCharzFlag(String charzFlag) {
		this.charzFlag = charzFlag;
	}
	
	/**
	 * @return Returns the hdncharzFlag.
	 */
	public String getHdncharzFlag() {
		return hdncharzFlag;
	}
	
	/**
	 * @param hdncharzFlag The hdncharzFlag to set.
	 */
	public void setHdncharzFlag(String hdncharzFlag) {
		this.hdncharzFlag = hdncharzFlag;
	}

	/**
	 * @return Returns the displayCOCFlag.
	 */
	public boolean isDisplayCOCFlag() {
		return displayCOCFlag;
	}

	/**
	 * @param displayCOCFlag The displayCOCFlag to set.
	 */
	public void setDisplayCOCFlag(boolean displayCOCFlag) {
		this.displayCOCFlag = displayCOCFlag;
	}

	/**
	 * @return Returns the hdnDisplayInCOC.
	 */
	public boolean isHdnDisplayInCOC() {
		return hdnDisplayInCOC;
	}

	/**
	 * @param hdnDisplayInCOC The hdnDisplayInCOC to set.
	 */
	public void setHdnDisplayInCOC(boolean hdnDisplayInCOC) {
		this.hdnDisplayInCOC = hdnDisplayInCOC;
	}

	/**
	 * @return Returns the compGroupTypeSeqNo.
	 */
	public int getCompGroupTypeSeqNo() {
		return compGroupTypeSeqNo;
	}

	/**
	 * @param compGroupTypeSeqNo The compGroupTypeSeqNo to set.
	 */
	public void setCompGroupTypeSeqNo(int compGroupTypeSeqNo) {
		this.compGroupTypeSeqNo = compGroupTypeSeqNo;
	}

	/**
	 * @return Returns the compGroupTypeList.
	 */
	public ArrayList getCompGroupTypeList() {
		return compGroupTypeList;
	}

	/**
	 * @param compGroupTypeList The compGroupTypeList to set.
	 */
	public void setCompGroupTypeList(ArrayList compGroupTypeList) {
		this.compGroupTypeList = compGroupTypeList;
	}

	/**
	 * @return Returns the hdncompGroupTypeSeqNo.
	 */
	public int getHdncompGroupTypeSeqNo() {
		return hdncompGroupTypeSeqNo;
	}

	/**
	 * @param hdncompGroupTypeSeqNo The hdncompGroupTypeSeqNo to set.
	 */
	public void setHdncompGroupTypeSeqNo(int hdncompGroupTypeSeqNo) {
		this.hdncompGroupTypeSeqNo = hdncompGroupTypeSeqNo;
	}

	public String getHdnSelSpecType() {
		return hdnSelSpecType;
	}

	public void setHdnSelSpecType(String hdnSelSpecType) {
		this.hdnSelSpecType = hdnSelSpecType;
	}

	public boolean isIncludeOrderFlag() {
		return includeOrderFlag;
	}

	public void setIncludeOrderFlag(boolean includeOrderFlag) {
		this.includeOrderFlag = includeOrderFlag;
	}

	public ArrayList getModelList() {
		return modelList;
	}

	public void setModelList(ArrayList modelList) {
		this.modelList = modelList;
	}

	public int[] getModelSeqNos() {
		return modelSeqNos;
	}

	public void setModelSeqNos(int[] modelSeqNos) {
		this.modelSeqNos = modelSeqNos;
	}

	public String getHdnSelectedMdlNames() {
		return hdnSelectedMdlNames;
	}

	public void setHdnSelectedMdlNames(String hdnSelectedMdlNames) {
		this.hdnSelectedMdlNames = hdnSelectedMdlNames;
	}

	public String[] getHdnSelModels() {
		return hdnSelModels;
	}

	public void setHdnSelModels(String[] hdnSelModels) {
		this.hdnSelModels = hdnSelModels;
	}

	public String getHdnSelSpecTypeName() {
		return hdnSelSpecTypeName;
	}

	public void setHdnSelSpecTypeName(String hdnSelSpecTypeName) {
		this.hdnSelSpecTypeName = hdnSelSpecTypeName;
	}

	
	public boolean isHdnIncludeOrderFlag() {
		return hdnIncludeOrderFlag;
	}

	public void setHdnIncludeOrderFlag(boolean hdnIncludeOrderFlag) {
		this.hdnIncludeOrderFlag = hdnIncludeOrderFlag;
	}

	public String getSelectedMdlNames() {
		return selectedMdlNames;
	}

	public void setSelectedMdlNames(String selectedMdlNames) {
		this.selectedMdlNames = selectedMdlNames;
	}

	public String getIncludeOrderONOFF() {
		return includeOrderONOFF;
	}

	public void setIncludeOrderONOFF(String includeOrderONOFF) {
		this.includeOrderONOFF = includeOrderONOFF;
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
	 * @return Returns the compList.
	 */
	public ArrayList getCompList() {
		return compList;
	}

	/**
	 * @param compList The compList to set.
	 */
	public void setCompList(ArrayList compList) {
		this.compList = compList;
	}

	/**
	 * @return Returns the hdncompName.
	 */
	public String getHdncompName() {
		return hdncompName;
	}

	/**
	 * @param hdncompName The hdncompName to set.
	 */
	public void setHdncompName(String hdncompName) {
		this.hdncompName = hdncompName;
	}

	/**
	 * @return Returns the hdnShowLatSpecFlag.
	 */
	public String getHdnShowLatSpecFlag() {
		return hdnShowLatSpecFlag;
	}

	/**
	 * @param hdnShowLatSpecFlag The hdnShowLatSpecFlag to set.
	 */
	public void setHdnShowLatSpecFlag(String hdnShowLatSpecFlag) {
		this.hdnShowLatSpecFlag = hdnShowLatSpecFlag;
	}

	/**
	 * @return Returns the showLatSpecFlag.
	 */
	public boolean isShowLatSpecFlag() {
		return showLatSpecFlag;
	}

	/**
	 * @param showLatSpecFlag The showLatSpecFlag to set.
	 */
	public void setShowLatSpecFlag(boolean showLatSpecFlag) {
		this.showLatSpecFlag = showLatSpecFlag;
	}

	/**
	 * @return Returns the compInOrdList.
	 */
	public ArrayList getCompInOrdList() {
		return compInOrdList;
	}

	/**
	 * @param compInOrdList The compInOrdList to set.
	 */
	public void setCompInOrdList(ArrayList compInOrdList) {
		this.compInOrdList = compInOrdList;
	}

	public ArrayList getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList orderList) {
		this.orderList = orderList;
	}

	public ArrayList getSpecItemList() {
		return specItemList;
	}

	public void setSpecItemList(ArrayList specItemList) {
		this.specItemList = specItemList;
	}

	public String getColumnheader() {
		return columnheader;
	}

	public void setColumnheader(String columnheader) {
		this.columnheader = columnheader;
	}

	public int getOrderbyCompFlag() {
		return orderbyCompFlag;
	}

	public void setOrderbyCompFlag(int orderbyCompFlag) {
		this.orderbyCompFlag = orderbyCompFlag;
	}

	public int getOrderbyCompGroupFlag() {
		return orderbyCompGroupFlag;
	}

	public void setOrderbyCompGroupFlag(int orderbyCompGroupFlag) {
		this.orderbyCompGroupFlag = orderbyCompGroupFlag;
	}

	public int getOrderbyCompgrpFlag() {
		return orderbyCompgrpFlag;
	}

	public void setOrderbyCompgrpFlag(int orderbyCompgrpFlag) {
		this.orderbyCompgrpFlag = orderbyCompgrpFlag;
	}
    
	
	
}
