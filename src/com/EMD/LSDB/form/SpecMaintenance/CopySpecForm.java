package com.EMD.LSDB.form.SpecMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

public class CopySpecForm extends EMDForm {
	
	/* Added for LSDB_CR-75 on 04-May-09 by cm68219 */
	
	private String hdnSelectedCustomers;
	
	private int hdnPreSelectedCustomer;
	
	/* added*/
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
	
	//Added for CR-75 for Customer drop down in search
	private int customerSeqNO;
	
	ArrayList customerList;
	
	//Added For CR_84 
	private String distributorFlag;
	//Added For CR_108 copy and model Indicators ON/OFF
	private boolean copyMdlIndFlag;
	
	//Added For CR_131 to obtain Order Key
	private int hdnOrderKey;
	
	public int getHdnOrderKey() {
		return hdnOrderKey;
	}

	public void setHdnOrderKey(int hdnOrderKey) {
		this.hdnOrderKey = hdnOrderKey;
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

	
	public void setCopyMdlIndFlag(boolean copyMdlIndFlag) {
		this.copyMdlIndFlag = copyMdlIndFlag;
	}

	public boolean isCopyMdlIndFlag() {
		return copyMdlIndFlag;
	}
	
	
}
