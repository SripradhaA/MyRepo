package com.EMD.LSDB.form.SpecMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

public class ModifySpecForm extends EMDForm {
	
	private String hdnorderNo;
	
	private int hdnQty;
	
	 /* Added for LSDB_CR-75 on 04-May-09 by cm68219 */
	
    private ArrayList customerList;

    private String custSeqNo;

	private String hdnSelectedCustomers;
	
	/* added*/
	
	private String hdnSelModel;
	
	private String hdnSelSpecType;
	
	ArrayList modelList;
	
	private int modelSeqNo = 0;
	
	private String orderNo;
	
	ArrayList specTypeList;
	
	private int spectypeSeqno = 0;
	
	private String hdnSapCustCode;
	
	//Change for LSDb_CR-76
	private String sortByFlag;
	
	private String columnheader;
	//Addedd FOR CR_104
	private String hdnCustMdlName;
	
	/**
	 * @return Returns the hdnSapCustCode.
	 */
	public String getHdnSapCustCode() {
		return hdnSapCustCode;
	}
	
	/**
	 * @param hdnSapCustCode The hdnSapCustCode to set.
	 */
	public void setHdnSapCustCode(String hdnSapCustCode) {
		this.hdnSapCustCode = hdnSapCustCode;
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

	public ArrayList getCustomerList() {
		return customerList;
	}

	public void setCustomerList(ArrayList customerList) {
		this.customerList = customerList;
	}
	public String getHdnSelectedCustomers() {
		return hdnSelectedCustomers;
	}

	public void setHdnSelectedCustomers(String hdnSelectedCustomers) {
		this.hdnSelectedCustomers = hdnSelectedCustomers;
	}

	public String getCustSeqNo() {
		return custSeqNo;
	}

	public void setCustSeqNo(String custSeqNo) {
		this.custSeqNo = custSeqNo;
	}

	/**
	 * @return Returns the sortByFlag.
	 */
	public String getSortByFlag() {
		return sortByFlag;
	}

	/**
	 * @param sortByFlag The sortByFlag to set.
	 */
	public void setSortByFlag(String sortByFlag) {
		this.sortByFlag = sortByFlag;
	}

	/**
	 * @return Returns the columnheader.
	 */
	public String getColumnheader() {
		return columnheader;
	}

	/**
	 * @param columnheader The columnheader to set.
	 */
	public void setColumnheader(String columnheader) {
		this.columnheader = columnheader;
	}

	public String getHdnCustMdlName() {
		return hdnCustMdlName;
	}

	public void setHdnCustMdlName(String hdnCustMdlName) {
		this.hdnCustMdlName = hdnCustMdlName;
	}

	
	
}
