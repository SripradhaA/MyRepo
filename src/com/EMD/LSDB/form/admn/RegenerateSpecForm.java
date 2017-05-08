package com.EMD.LSDB.form.admn;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

public class RegenerateSpecForm extends EMDForm {

	private String hdnorderNo;
		
    private ArrayList customerList;

    private String custSeqNo;

	private String hdnSelectedCustomers;

	private String hdnSelModel;
	
	private String hdnSelSpecType;
	
	private ArrayList modelList;
	
	private int modelSeqNo = 0;
	
	private String orderNo;
	
	private ArrayList specTypeList;
	
	private int spectypeSeqno = 0;

	private int fromOrderKey;
	
	private int toOrderkey;
	
	private int hdnToOrderkey;

	/**
	 * @return Returns the customerList.
	 */
	public ArrayList getCustomerList() {
		return customerList;
	}

	/**
	 * @param customerList The customerList to set.
	 */
	public void setCustomerList(ArrayList customerList) {
		this.customerList = customerList;
	}

	/**
	 * @return Returns the custSeqNo.
	 */
	public String getCustSeqNo() {
		return custSeqNo;
	}

	/**
	 * @param custSeqNo The custSeqNo to set.
	 */
	public void setCustSeqNo(String custSeqNo) {
		this.custSeqNo = custSeqNo;
	}

	/**
	 * @return Returns the fromOrderKey.
	 */
	public int getFromOrderKey() {
		return fromOrderKey;
	}

	/**
	 * @param fromOrderKey The fromOrderKey to set.
	 */
	public void setFromOrderKey(int fromOrderKey) {
		this.fromOrderKey = fromOrderKey;
	}

	/**
	 * @return Returns the hdnorderNo.
	 */
	public String getHdnorderNo() {
		return hdnorderNo;
	}

	/**
	 * @param hdnorderNo The hdnorderNo to set.
	 */
	public void setHdnorderNo(String hdnorderNo) {
		this.hdnorderNo = hdnorderNo;
	}

	/**
	 * @return Returns the hdnSelectedCustomers.
	 */
	public String getHdnSelectedCustomers() {
		return hdnSelectedCustomers;
	}

	/**
	 * @param hdnSelectedCustomers The hdnSelectedCustomers to set.
	 */
	public void setHdnSelectedCustomers(String hdnSelectedCustomers) {
		this.hdnSelectedCustomers = hdnSelectedCustomers;
	}

	/**
	 * @return Returns the hdnSelModel.
	 */
	public String getHdnSelModel() {
		return hdnSelModel;
	}

	/**
	 * @param hdnSelModel The hdnSelModel to set.
	 */
	public void setHdnSelModel(String hdnSelModel) {
		this.hdnSelModel = hdnSelModel;
	}

	/**
	 * @return Returns the hdnSelSpecType.
	 */
	public String getHdnSelSpecType() {
		return hdnSelSpecType;
	}

	/**
	 * @param hdnSelSpecType The hdnSelSpecType to set.
	 */
	public void setHdnSelSpecType(String hdnSelSpecType) {
		this.hdnSelSpecType = hdnSelSpecType;
	}

	/**
	 * @return Returns the modelList.
	 */
	public ArrayList getModelList() {
		return modelList;
	}

	/**
	 * @param modelList The modelList to set.
	 */
	public void setModelList(ArrayList modelList) {
		this.modelList = modelList;
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

	/**
	 * @return Returns the specTypeList.
	 */
	public ArrayList getSpecTypeList() {
		return specTypeList;
	}

	/**
	 * @param specTypeList The specTypeList to set.
	 */
	public void setSpecTypeList(ArrayList specTypeList) {
		this.specTypeList = specTypeList;
	}

	/**
	 * @return Returns the spectypeSeqno.
	 */
	public int getSpectypeSeqno() {
		return spectypeSeqno;
	}

	/**
	 * @param spectypeSeqno The spectypeSeqno to set.
	 */
	public void setSpectypeSeqno(int spectypeSeqno) {
		this.spectypeSeqno = spectypeSeqno;
	}

	/**
	 * @return Returns the toOrderkey.
	 */
	public int getToOrderkey() {
		return toOrderkey;
	}

	/**
	 * @param toOrderkey The toOrderkey to set.
	 */
	public void setToOrderkey(int toOrderkey) {
		this.toOrderkey = toOrderkey;
	}

	/**
	 * @return Returns the hdnToOrderkey.
	 */
	public int getHdnToOrderkey() {
		return hdnToOrderkey;
	}

	/**
	 * @param hdnToOrderkey The hdnToOrderkey to set.
	 */
	public void setHdnToOrderkey(int hdnToOrderkey) {
		this.hdnToOrderkey = hdnToOrderkey;
	}
	
	

}
