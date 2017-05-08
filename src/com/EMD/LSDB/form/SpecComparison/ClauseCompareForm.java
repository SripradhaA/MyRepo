package com.EMD.LSDB.form.SpecComparison;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

public class ClauseCompareForm extends EMDForm {
	
	private String hdnorderNo;
	
	private int hdnQty;
	
	private String hdnSelModel;
	
	private String hdnSelSpecType;
	
	ArrayList modelList;
	
	private int modelSeqNo = 0;
	
	private String orderNo;
	
	ArrayList specTypeList;
	
	private String selectedSection;
	
	private int[] modelSeqNos;
	
	private String hdnSelectedMdlNames;
	
	private String hdnSelSpecTypeName;
	
	private String[] hdnSelModels;
	
	private int spectypeSeqno = 0;
	
	private ArrayList compareOrderList;
	
	private int orderListSize = 0;
	
	private ArrayList selectedOrderList;
	
	//Change for LSDb_CR-87
	private String sortByFlag="2";
	private String columnheader;
	//Added For CR_104
	private String custMdlName;
	
	// Added for CR_104 for show latest published spec
	private boolean clauseShowLatFlag;
	//Added for CR_104 fix
	private String clauseShowLatONOFF;
	
	//Added for CR-135
	private ArrayList orderModelDetList;
	
	public boolean isClauseShowLatFlag() {
		return clauseShowLatFlag;
	}
	
	public void setClauseShowLatFlag(boolean clauseShowLatFlag) {
		this.clauseShowLatFlag = clauseShowLatFlag;
	}
	// CR_104 Ends here	
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

	public ArrayList getCompareOrderList() {
		return compareOrderList;
	}
	
	public void setCompareOrderList(ArrayList compareOrderList) {
		this.compareOrderList = compareOrderList;
	}
	
	public int getOrderListSize() {
		return orderListSize;
	}
	
	public void setOrderListSize(int orderListSize) {
		this.orderListSize = orderListSize;
	}
	
	public ArrayList getSelectedOrderList() {
		return selectedOrderList;
	}
	
	public void setSelectedOrderList(ArrayList selectedOrderList) {
		this.selectedOrderList = selectedOrderList;
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
	
	public String getHdnSelectedMdlNames() {
		return hdnSelectedMdlNames;
	}
	
	public void setHdnSelectedMdlNames(String hdnSelectedMdlNames) {
		this.hdnSelectedMdlNames = hdnSelectedMdlNames;
	}
	
	public String getHdnSelSpecTypeName() {
		return hdnSelSpecTypeName;
	}
	
	public void setHdnSelSpecTypeName(String hdnSelSpecTypeName) {
		this.hdnSelSpecTypeName = hdnSelSpecTypeName;
	}
	
	public String getSelectedSection() {
		return selectedSection;
	}
	
	public void setSelectedSection(String selectedSection) {
		this.selectedSection = selectedSection;
	}

	public String getCustMdlName() {
		return custMdlName;
	}

	public void setCustMdlName(String custMdlName) {
		this.custMdlName = custMdlName;
	}

	public String getClauseShowLatONOFF() {
		return clauseShowLatONOFF;
	}

	public void setClauseShowLatONOFF(String clauseShowLatONOFF) {
		this.clauseShowLatONOFF = clauseShowLatONOFF;
	}

	public ArrayList getOrderModelDetList() {
		return orderModelDetList;
	}

	public void setOrderModelDetList(ArrayList orderModelDetList) {
		this.orderModelDetList = orderModelDetList;
	}
	
}
