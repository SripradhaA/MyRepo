package com.EMD.LSDB.form.SpecMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

public class OrderClausePartOfPopUpForm extends EMDForm {
	
	private String orderNo;
	
	private int sectionSeqNo;
	
	private ArrayList sectionList;
	
	private int subSectionSeqNo;
	
	private ArrayList subSectionList;
	
	private ArrayList clauseGroup;
	
	private String hdnClauseNum;
	
	private int orderKey;
	
	private int modelSeqNo;
	
	private int clauseSeqNo;
	
	private int textBoxIndex;
	
	//Added for CR-117 Fixes
	private int versionNo;
	//Added for CR-117 Fixes Ends
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
	
	/**
	 * @return Returns the clauseSeqNo.
	 */
	public int getClauseSeqNo() {
		return clauseSeqNo;
	}
	
	/**
	 * @param clauseSeqNo
	 *            The clauseSeqNo to set.
	 */
	public void setClauseSeqNo(int clauseSeqNo) {
		this.clauseSeqNo = clauseSeqNo;
	}
	
	/**
	 * @return Returns the hdnClauseNum.
	 */
	public String getHdnClauseNum() {
		return hdnClauseNum;
	}
	
	/**
	 * @param hdnClauseNum
	 *            The hdnClauseNum to set.
	 */
	public void setHdnClauseNum(String hdnClauseNum) {
		this.hdnClauseNum = hdnClauseNum;
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
	 * @return Returns the orderKey.
	 */
	public int getOrderKey() {
		return orderKey;
	}
	
	/**
	 * @param orderKey
	 *            The orderKey to set.
	 */
	public void setOrderKey(int orderKey) {
		this.orderKey = orderKey;
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
	 * @return Returns the sectionList.
	 */
	public ArrayList getSectionList() {
		return sectionList;
	}
	
	/**
	 * @param sectionList
	 *            The sectionList to set.
	 */
	public void setSectionList(ArrayList sectionList) {
		this.sectionList = sectionList;
	}
	
	/**
	 * @return Returns the sectionSeqNo.
	 */
	public int getSectionSeqNo() {
		return sectionSeqNo;
	}
	
	/**
	 * @param sectionSeqNo
	 *            The sectionSeqNo to set.
	 */
	public void setSectionSeqNo(int sectionSeqNo) {
		this.sectionSeqNo = sectionSeqNo;
	}
	
	/**
	 * @return Returns the subSectionList.
	 */
	public ArrayList getSubSectionList() {
		return subSectionList;
	}
	
	/**
	 * @param subSectionList
	 *            The subSectionList to set.
	 */
	public void setSubSectionList(ArrayList subSectionList) {
		this.subSectionList = subSectionList;
	}
	
	/**
	 * @return Returns the subSectionSeqNo.
	 */
	public int getSubSectionSeqNo() {
		return subSectionSeqNo;
	}
	
	/**
	 * @param subSectionSeqNo
	 *            The subSectionSeqNo to set.
	 */
	public void setSubSectionSeqNo(int subSectionSeqNo) {
		this.subSectionSeqNo = subSectionSeqNo;
	}
	
	/**
	 * @return Returns the textBoxIndex.
	 */
	public int getTextBoxIndex() {
		return textBoxIndex;
	}
	
	/**
	 * @param textBoxIndex The textBoxIndex to set.
	 */
	public void setTextBoxIndex(int textBoxIndex) {
		this.textBoxIndex = textBoxIndex;
	}

	public int getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}
	
	
}
