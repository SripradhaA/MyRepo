package com.EMD.LSDB.form.SpecMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

//Created at CR_108 for Order Specific Clause Report

public class OrderSpecificClauseForm extends EMDForm {
	
	private String hdnSelectedCustomers;
	
	private int hdnPreSelectedCustomer;
	
	ArrayList custList;
	
	private int custSeqNo = 0;
	
	private int custTypeSeqNo = 0;
	
	ArrayList distList;
	
	private int distSeqNo = 0;
	
	private String hdnSelModel;
	
	private String hdnSelSpecType;
	
	ArrayList modelList;
	
	private int modelSeqNo = 0;
	
	private String orderNo;
	
	private String orderNumber;
	
	private int quantity;
	
	private String sapCustCode;
	
	ArrayList specTypeList;
	
	private int spectypeSeqno = 0;
	
	private int hdPreSelectedModel;

	private int customerSeqNO;
	
	ArrayList customerList;
 
	private String distributorFlag;
	
	private boolean showLatestFlag;
	
	private String hnOrderKey;

	private ArrayList selectedOrderList;
	
	private int orderListSize = 0;
	
	ArrayList ordrKeyList;
	
	ArrayList orderNosList;
	
	private String[] orderNumbers;
	
	ArrayList arlClauseList;
	
	private String orderNumberResult;
	
	private String hdnOrderNo;
	
	private String hdnLatestFlag;	
	
	public String getOrderNumberResult() {
		return orderNumberResult;
	}

	public void setOrderNumberResult(String orderNumberResult) {
		this.orderNumberResult = orderNumberResult;
	}

	public ArrayList getArlClauseList() {
		return arlClauseList;
	}

	public void setArlClauseList(ArrayList arlClauseList) {
		this.arlClauseList = arlClauseList;
	}

	public ArrayList getOrderNosList() {
		return orderNosList;
	}

	public void setOrderNosList(ArrayList orderNosList) {
		this.orderNosList = orderNosList;
	}

	public ArrayList getOrdrKeyList() {
		return ordrKeyList;
	}

	public void setOrdrKeyList(ArrayList ordrKeyList) {
		this.ordrKeyList = ordrKeyList;
	}

	public ArrayList getSelectedOrderList() {
		return selectedOrderList;
	}

	public void setSelectedOrderList(ArrayList selectedOrderList) {
		this.selectedOrderList = selectedOrderList;
	}

	/**
	 * @return Returns the showLatestFlag.
	 */
	public boolean isShowLatestFlag() {
		return showLatestFlag;
	}

	/**
	 * @param showLatestFlag The showLatestFlag to set.
	 */
	public void setShowLatestFlag(boolean showLatestFlag) {
		this.showLatestFlag = showLatestFlag;
	}

	public ArrayList getCustomerList() {
		return customerList;
	}
	
	public void setCustomerList(ArrayList customerList) {
		this.customerList = customerList;
	}
	
	public int getCustomerSeqNO() {
		return customerSeqNO;
	}
	
	public void setCustomerSeqNO(int customerSeqNO) {
		this.customerSeqNO = customerSeqNO;
	}
	
	public ArrayList getCustList() {
		return custList;
	}
	
	public void setCustList(ArrayList custList) {
		this.custList = custList;
	}
	
	public int getCustSeqNo() {
		return custSeqNo;
	}
	
	public void setCustSeqNo(int custSeqNo) {
		this.custSeqNo = custSeqNo;
	}
	
	public int getCustTypeSeqNo() {
		return custTypeSeqNo;
	}
	
	public void setCustTypeSeqNo(int custTypeSeqNo) {
		this.custTypeSeqNo = custTypeSeqNo;
	}
	
	public ArrayList getDistList() {
		return distList;
	}
	
	public void setDistList(ArrayList distList) {
		this.distList = distList;
	}
	
	public int getDistSeqNo() {
		return distSeqNo;
	}
	
	public void setDistSeqNo(int distSeqNo) {
		this.distSeqNo = distSeqNo;
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
	
	public String getOrderNumber() {
		return orderNumber;
	}
	
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getSapCustCode() {
		return sapCustCode;
	}
	
	public void setSapCustCode(String sapCustCode) {
		this.sapCustCode = sapCustCode;
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
	 * @return Returns the hdPreSelectedModel.
	 */
	public int getHdPreSelectedModel() {
		return hdPreSelectedModel;
	}
	
	/**
	 * @param hdPreSelectedModel The hdPreSelectedModel to set.
	 */
	public void setHdPreSelectedModel(int hdPreSelectedModel) {
		this.hdPreSelectedModel = hdPreSelectedModel;
	}
	
	public String getHdnSelectedCustomers() {
		return hdnSelectedCustomers;
	}
	
	public void setHdnSelectedCustomers(String hdnSelectedCustomers) {
		this.hdnSelectedCustomers = hdnSelectedCustomers;
	}

	public int getHdnPreSelectedCustomer() {
		return hdnPreSelectedCustomer;
	}

	public void setHdnPreSelectedCustomer(int hdnPreSelectedCustomer) {
		this.hdnPreSelectedCustomer = hdnPreSelectedCustomer;
	}

	/**
	 * @return Returns the distributorFlag.
	 */
	public String getDistributorFlag() {
		return distributorFlag;
	}

	/**
	 * @param distributorFlag The distributorFlag to set.
	 */
	public void setDistributorFlag(String distributorFlag) {
		this.distributorFlag = distributorFlag;
	}

	public String getHnOrderKey() {
		return hnOrderKey;
	}

	public void setHnOrderKey(String hnOrderKey) {
		this.hnOrderKey = hnOrderKey;
	}

	public int getOrderListSize() {
		return orderListSize;
	}

	public void setOrderListSize(int orderListSize) {
		this.orderListSize = orderListSize;
	}

	public String[] getOrderNumbers() {
		return orderNumbers;
	}

	public void setOrderNumbers(String[] orderNumbers) {
		this.orderNumbers = orderNumbers;
	}

	/**
	 * @return Returns the hdnLatestFlag.
	 */
	public String getHdnLatestFlag() {
		return hdnLatestFlag;
	}

	/**
	 * @param hdnLatestFlag The hdnLatestFlag to set.
	 */
	public void setHdnLatestFlag(String hdnLatestFlag) {
		this.hdnLatestFlag = hdnLatestFlag;
	}

	/**
	 * @return Returns the hdnOrderNo.
	 */
	public String getHdnOrderNo() {
		return hdnOrderNo;
	}

	/**
	 * @param hdnOrderNo The hdnOrderNo to set.
	 */
	public void setHdnOrderNo(String hdnOrderNo) {
		this.hdnOrderNo = hdnOrderNo;
	}


}
