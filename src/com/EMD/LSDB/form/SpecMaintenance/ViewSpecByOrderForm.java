package com.EMD.LSDB.form.SpecMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

public class ViewSpecByOrderForm extends EMDForm {
	
	ArrayList mainFeaList = new ArrayList();
	
	ArrayList sectionList = new ArrayList();
	
	ArrayList orderList = new ArrayList();
	
	ArrayList specList = new ArrayList();
	
	ArrayList genArgmntList = new ArrayList();
	
	ArrayList perCurveList = new ArrayList();
	
	ArrayList subSectionList = new ArrayList();
	
	ArrayList secDetailList = new ArrayList();
	
	//Added for CR to dispaly Order Components in Main Features
	
	ArrayList orderDefCompsList = new ArrayList();
	
	//Added for LSDB_CR_42
	ArrayList ClauseImageList = new ArrayList();
	
	/**
	 * @return Returns the clauseImageList.
	 */
	public ArrayList getClauseImageList() {
		return ClauseImageList;
	}
	
	/**
	 * @param clauseImageList The clauseImageList to set.
	 */
	public void setClauseImageList(ArrayList clauseImageList) {
		ClauseImageList = clauseImageList;
	}
	
	public ArrayList getMainFeaList() {
		return mainFeaList;
	}
	
	public void setMainFeaList(ArrayList mainFeaList) {
		this.mainFeaList = mainFeaList;
	}
	
	public ArrayList getOrderList() {
		return orderList;
	}
	
	public void setOrderList(ArrayList orderList) {
		this.orderList = orderList;
	}
	
	public ArrayList getSectionList() {
		return sectionList;
	}
	
	public void setSectionList(ArrayList sectionList) {
		this.sectionList = sectionList;
	}
	
	public ArrayList getSpecList() {
		return specList;
	}
	
	public void setSpecList(ArrayList specList) {
		this.specList = specList;
	}
	
	public ArrayList getGenArgmntList() {
		return genArgmntList;
	}
	
	public void setGenArgmntList(ArrayList genArgmntList) {
		this.genArgmntList = genArgmntList;
	}
	
	public ArrayList getPerCurveList() {
		return perCurveList;
	}
	
	public void setPerCurveList(ArrayList perCurveList) {
		this.perCurveList = perCurveList;
	}
	
	public ArrayList getSubSectionList() {
		return subSectionList;
	}
	
	public void setSubSectionList(ArrayList subSectionList) {
		this.subSectionList = subSectionList;
	}
	
	public ArrayList getSecDetailList() {
		return secDetailList;
	}
	
	public void setSecDetailList(ArrayList secDetailList) {
		this.secDetailList = secDetailList;
	}
	
	/**
	 * @return Returns the orderDefCompsList.
	 */
	public ArrayList getOrderDefCompsList() {
		return orderDefCompsList;
	}
	
	/**
	 * @param orderDefCompsList The orderDefCompsList to set.
	 */
	public void setOrderDefCompsList(ArrayList orderDefCompsList) {
		this.orderDefCompsList = orderDefCompsList;
	}
	
}
