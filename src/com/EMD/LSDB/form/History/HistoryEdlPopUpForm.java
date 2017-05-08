package com.EMD.LSDB.form.History;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the form fields for the History Edl Pop Up
 ******************************************************************************/
public class HistoryEdlPopUpForm extends EMDForm {
	public ArrayList clauseGroup;
	
	private String orderNo;
	
	private String specStatus;
	
	private String customerName;
	
	private String modelName;
	
	/**
	 * @return Returns the clauseNo.
	 */
	public String getOrderNo() {
		return orderNo;
	}
	
	/**
	 * @param clauseNo
	 *            The clauseNo to set.
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	/**
	 * @return Returns the customerName.
	 */
	public String getCustomerName() {
		return customerName;
	}
	
	/**
	 * @param customerName
	 *            The customerName to set.
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	/**
	 * @return Returns the modelName.
	 */
	public String getModelName() {
		return modelName;
	}
	
	/**
	 * @param modelName
	 *            The modelName to set.
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
	/**
	 * @return Returns the specStatus.
	 */
	public String getSpecStatus() {
		return specStatus;
	}
	
	/**
	 * @param specStatus
	 *            The specStatus to set.
	 */
	public void setSpecStatus(String specStatus) {
		this.specStatus = specStatus;
	}
	
	/**
	 * @return Returns the clauseGroup.
	 */
	public ArrayList getClauseGroup() {
		return clauseGroup;
	}
	
	/**
	 * @param clauseGroup
	 *            The clauseGroup to set.
	 */
	public void setClauseGroup(ArrayList clauseGroup) {
		this.clauseGroup = clauseGroup;
	}
	
}
