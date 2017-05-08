/*
 * Created on Jun 5, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.form.SpecMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the form fields for the new Order
 ******************************************************************************/

public class CreateSpecForm extends EMDForm {
	
	private int modelSeqNo = 0;
	
	private int modCustTypeSeqNo = 0;
	
	private int specTypeNo = 0;
	
	private int distSeqNo = 0;
	
	private int cusSeqNo = 0;
	
	private String sapCusCode;
	
	private String orderNo;
	
	private int quantity;
	
	private ArrayList specTypeList;
	
	private ArrayList distributorList;
	
	private ArrayList customerList;
	
	private ArrayList modelList;
	
	//Added For CR_84
	private String distributorFlag;
	
	/**
	 * @return Returns the customerList.
	 */
	public ArrayList getCustomerList() {
		return customerList;
	}
	
	/**
	 * @param customerList
	 *            The customerList to set.
	 */
	public void setCustomerList(ArrayList customerList) {
		this.customerList = customerList;
	}
	
	/**
	 * @return Returns the distributorList.
	 */
	public ArrayList getDistributorList() {
		return distributorList;
	}
	
	/**
	 * @param distributorList
	 *            The distributorList to set.
	 */
	public void setDistributorList(ArrayList distributorList) {
		this.distributorList = distributorList;
	}
	
	/**
	 * @return Returns the modelList.
	 */
	public ArrayList getModelList() {
		return modelList;
	}
	
	/**
	 * @param modelList
	 *            The modelList to set.
	 */
	public void setModelList(ArrayList modelList) {
		this.modelList = modelList;
	}
	
	/**
	 * @return Returns the cusSeqNo.
	 */
	public int getCusSeqNo() {
		return cusSeqNo;
	}
	
	/**
	 * @param cusSeqNo
	 *            The cusSeqNo to set.
	 */
	public void setCusSeqNo(int cusSeqNo) {
		this.cusSeqNo = cusSeqNo;
	}
	
	/**
	 * @return Returns the distSeqNo.
	 */
	public int getDistSeqNo() {
		return distSeqNo;
	}
	
	/**
	 * @param distSeqNo
	 *            The distSeqNo to set.
	 */
	public void setDistSeqNo(int distSeqNo) {
		this.distSeqNo = distSeqNo;
	}
	
	/**
	 * @return Returns the modCustTypeSeqNo.
	 */
	public int getModCustTypeSeqNo() {
		return modCustTypeSeqNo;
	}
	
	/**
	 * @param modCustTypeSeqNo
	 *            The modCustTypeSeqNo to set.
	 */
	public void setModCustTypeSeqNo(int modCustTypeSeqNo) {
		this.modCustTypeSeqNo = modCustTypeSeqNo;
	}
	
	/**
	 * @return Returns the modelSeqNo.
	 */
	public int getModelSeqNo() {
		return modelSeqNo;
	}
	
	/**
	 * @param modelSeqNo
	 *            The modelSeqNo to set.
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
	 * @param orderNo
	 *            The orderNo to set.
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	/**
	 * @return Returns the quantity.
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * @param quantity
	 *            The quantity to set.
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * @return Returns the sapCusCode.
	 */
	public String getSapCusCode() {
		return sapCusCode;
	}
	
	/**
	 * @param sapCusCode
	 *            The sapCusCode to set.
	 */
	public void setSapCusCode(String sapCusCode) {
		this.sapCusCode = sapCusCode;
	}
	
	/**
	 * @return Returns the specTypeNo.
	 */
	public int getSpecTypeNo() {
		return specTypeNo;
	}
	
	/**
	 * @param specTypeNo
	 *            The specTypeNo to set.
	 */
	public void setSpecTypeNo(int specTypeNo) {
		this.specTypeNo = specTypeNo;
	}
	
	/**
	 * @return Returns the specTypeList.
	 */
	public ArrayList getSpecTypeList() {
		return specTypeList;
	}
	
	/**
	 * @param specTypeList
	 *            The specTypeList to set.
	 */
	public void setSpecTypeList(ArrayList specTypeList) {
		this.specTypeList = specTypeList;
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
	
}