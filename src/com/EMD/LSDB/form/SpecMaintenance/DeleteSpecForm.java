package com.EMD.LSDB.form.SpecMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

public class DeleteSpecForm extends EMDForm {
	
	
	 /* Added for LSDB_CR-75 on 04-May-09 by cm68219 */

	private ArrayList customerList;

    private String custSeqNo;

	private String hdnSelectedCustomers;

	/* added*/
	
	private String hdnorderNo;
	
	private String hdnSelModel;
	
	private String hdnSelSpecType;
	
	private int spectypeSeqno;
	
	private int modelSeqNo;
	
	private String orderNum;
	
	private String orderNo;
	
	ArrayList specTypeList;
	
	ArrayList modelList;
	
	private String srchOrderNum;
	
	/**
	 * @return Returns the srchOrderNum.
	 */
	public String getSrchOrderNum() {
		return srchOrderNum;
	}
	
	/**
	 * @param srchOrderNum The srchOrderNum to set.
	 */
	public void setSrchOrderNum(String srchOrderNum) {
		this.srchOrderNum = srchOrderNum;
	}
	
	public String getHdnorderNo() {
		return hdnorderNo;
	}
	
	public void setHdnorderNo(String hdnorderNo) {
		this.hdnorderNo = hdnorderNo;
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
	
	public String getOrderNum() {
		return orderNum;
	}
	
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
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
	
	/**
	 * @return Returns the orderNo.
	 */
	public String getOrderNo() {
		return orderNo;
	}
	
	/**
	 * @param orderNo The orderNo to set.
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	
	public String getHdnSelectedCustomers() {
		return hdnSelectedCustomers;
	}

	public void setHdnSelectedCustomers(String hdnSelectedCustomers) {
		this.hdnSelectedCustomers = hdnSelectedCustomers;
	}

	public ArrayList getCustomerList() {
		return customerList;
	}

	public void setCustomerList(ArrayList customerList) {
		this.customerList = customerList;
	}

	public String getCustSeqNo() {
		return custSeqNo;
	}

	public void setCustSeqNo(String custSeqNo) {
		this.custSeqNo = custSeqNo;
	}

	
	
}
