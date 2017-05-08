package com.EMD.LSDB.form.SpecComparison;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This ActionForm holds the values that are set in the front end.
 ******************************************************************************/

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

public class ComponentCompareForm extends EMDForm {
	
	private String hdnorderNo;
	
	private int hdnQty;
	
	private String hdnSelModel;
	
	private String hdnSelSpecType;
	
	private int selectedSectionSeqNo;
	
	ArrayList modelList;
	
	private int modelSeqNo = 0;
	
	private String orderNo;
	
	ArrayList specTypeList;
	
	private int[] modelSeqNos;
	
	private String hdnSelectedMdlNames;
	//Added For CR_84
	private String hdnSelSpecTypeName;
	
	private String[] hdnSelModels;
	
	private int spectypeSeqno = 0;
	
	private ArrayList sectionList;
	
	private ArrayList compareOrderList;
	
	private int orderListSize = 0;

	/******************
	 * New Attribute Section Name is Added For LSDB_CR-06
	 * Added on 15-April-08
	 * By ps57222
	 **********************/
	
	//Change for LSDb_CR-87
	private String sortByFlag="2";
	private String columnheader;
	
	
	private String sectionName;
	//Added for CR_104 for show latest published spec
	private boolean compShowLatFlag;
	//CR_104 Ends here
	private String compShowLatONOFF;
	/**
	 * @return Returns the sectionName.
	 */
	public String getSectionName() {
		return sectionName;
	}
	
	/**
	 * @param sectionName The sectionName to set.
	 */
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	
	private ArrayList selectedOrderList;
	
	public ArrayList getCompareOrderList() {
		return compareOrderList;
	}
	
	public void setCompareOrderList(ArrayList compareOrderList) {
		this.compareOrderList = compareOrderList;
	}
	
	public ArrayList getSectionList() {
		return sectionList;
	}
	
	public void setSectionList(ArrayList sectionList) {
		this.sectionList = sectionList;
	}
	
	public String getHdnorderNo() {
		return hdnorderNo;
	}
	
	public void setHdnorderNo(String hdnorderNo) {
		this.hdnorderNo = hdnorderNo;
	}
	
	public int getHdnQty() {
		return hdnQty;
	}
	
	public void setHdnQty(int hdnQty) {
		this.hdnQty = hdnQty;
	}
	
	public String getHdnSelModel() {
		return hdnSelModel;
	}
	
	public void setHdnSelModel(String hdnSelModel) {
		this.hdnSelModel = hdnSelModel;
	}
	
	public String getHdnSelSpecType() {
		return hdnSelSpecType;
	}
	
	public void setHdnSelSpecType(String hdnSelSpecType) {
		this.hdnSelSpecType = hdnSelSpecType;
	}
	
	public ArrayList getModelList() {
		return modelList;
	}
	
	public void setModelList(ArrayList modelList) {
		this.modelList = modelList;
	}
	
	public int getModelSeqNo() {
		return modelSeqNo;
	}
	
	public void setModelSeqNo(int modelSeqNo) {
		this.modelSeqNo = modelSeqNo;
	}
	
	public String getOrderNo() {
		return orderNo;
	}
	
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public ArrayList getSpecTypeList() {
		return specTypeList;
	}
	
	public void setSpecTypeList(ArrayList specTypeList) {
		this.specTypeList = specTypeList;
	}
	
	public int getSpectypeSeqno() {
		return spectypeSeqno;
	}
	
	public void setSpectypeSeqno(int spectypeSeqno) {
		this.spectypeSeqno = spectypeSeqno;
	}
	
	public int[] getModelSeqNos() {
		return modelSeqNos;
	}
	
	public void setModelSeqNos(int[] modelSeqNos) {
		this.modelSeqNos = modelSeqNos;
	}
	
	public String[] getHdnSelModels() {
		return hdnSelModels;
	}
	
	public void setHdnSelModels(String[] hdnSelModels) {
		this.hdnSelModels = hdnSelModels;
	}
	
	public int getSelectedSectionSeqNo() {
		return selectedSectionSeqNo;
	}
	
	public void setSelectedSectionSeqNo(int selectedSectionSeqNo) {
		this.selectedSectionSeqNo = selectedSectionSeqNo;
	}
	
	public ArrayList getSelectedOrderList() {
		return selectedOrderList;
	}
	
	public void setSelectedOrderList(ArrayList selectedOrderList) {
		this.selectedOrderList = selectedOrderList;
	}
	
	public int getOrderListSize() {
		return orderListSize;
	}
	
	public void setOrderListSize(int orderListSize) {
		this.orderListSize = orderListSize;
	}
	
	public String getHdnSelectedMdlNames() {
		return hdnSelectedMdlNames;
	}
	
	public void setHdnSelectedMdlNames(String hdnSelectedMdlNames) {
		this.hdnSelectedMdlNames = hdnSelectedMdlNames;
	}

	/**
	 * @return Returns the hdnSelSpecTypeName.
	 */
	public String getHdnSelSpecTypeName() {
		return hdnSelSpecTypeName;
	}

	/**
	 * @param hdnSelSpecTypeName The hdnSelSpecTypeName to set.
	 */
	public void setHdnSelSpecTypeName(String hdnSelSpecTypeName) {
		this.hdnSelSpecTypeName = hdnSelSpecTypeName;
	}

	public String getColumnheader() {
		return columnheader;
	}

	public void setColumnheader(String columnheader) {
		this.columnheader = columnheader;
	}

	public String getSortByFlag() {
		return sortByFlag;
	}

	public void setSortByFlag(String sortByFlag) {
		this.sortByFlag = sortByFlag;
	}

// Added for CR_104
	public boolean isCompShowLatFlag() {
		return compShowLatFlag;
	}

	public void setCompShowLatFlag(boolean compShowLatFlag) {
		this.compShowLatFlag = compShowLatFlag;
	}
	//CR_104 Ends here

	public String getCompShowLatONOFF() {
		return compShowLatONOFF;
	}

	public void setCompShowLatONOFF(String compShowLatONOFF) {
		this.compShowLatONOFF = compShowLatONOFF;
	}
}