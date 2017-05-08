package com.EMD.LSDB.form.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

public class PartOfForm extends EMDForm {
	
	public int modelSeqNo;
	
	public String modelName;
	
	public int sectionSeqNo;
	
	public ArrayList sectionList;
	
	public String sectionName;
	
	public int subSectionSeqNo;
	
	public ArrayList subSectionList;
	
	public String subSecName;
	
	public int textBoxIndex;
	
	/*
	 * Added for LSDB_CR-48 on 30-July-08 by ps57222
	 */
	private String clauseNum;
	
	private int hdnClauseSeqNo;
	
	private String hdnClauseNo;
	
	// Added for LSDB_CR_46 PM&I Change
	private String specType;
	
	private String hdnspecType;
	
	/**
	 * @return Returns the hdnspecType.
	 */
	public String getHdnspecType() {
		return hdnspecType;
	}
	
	/**
	 * @param hdnspecType
	 *            The hdnspecType to set.
	 */
	public void setHdnspecType(String hdnspecType) {
		this.hdnspecType = hdnspecType;
	}
	
	/**
	 * @return Returns the hdnClauseNo.
	 */
	public String getHdnClauseNo() {
		return hdnClauseNo;
	}
	
	/**
	 * @param hdnClauseNo
	 *            The hdnClauseNo to set.
	 */
	public void setHdnClauseNo(String hdnClauseNo) {
		this.hdnClauseNo = hdnClauseNo;
	}
	
	/**
	 * @return Returns the hdnClauseSeqNo.
	 */
	public int getHdnClauseSeqNo() {
		return hdnClauseSeqNo;
	}
	
	/**
	 * @param hdnClauseSeqNo
	 *            The hdnClauseSeqNo to set.
	 */
	public void setHdnClauseSeqNo(int hdnClauseSeqNo) {
		this.hdnClauseSeqNo = hdnClauseSeqNo;
	}
	
	/**
	 * @return Returns the clauseNum.
	 */
	public String getClauseNum() {
		return clauseNum;
	}
	
	/**
	 * @param clauseNum
	 *            The clauseNum to set.
	 */
	public void setClauseNum(String clauseNum) {
		this.clauseNum = clauseNum;
	}
	
	private int clauseSeqNo;
	
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
	
	private ArrayList clauseList;
	
	/**
	 * @return Returns the clauseList.
	 */
	public ArrayList getClauseList() {
		return clauseList;
	}
	
	/**
	 * @param clauseList
	 *            The clauseList to set.
	 */
	public void setClauseList(ArrayList clauseList) {
		this.clauseList = clauseList;
	}
	
	/**
	 * @return Returns the textBoxIndex.
	 */
	public int getTextBoxIndex() {
		return textBoxIndex;
	}
	
	/**
	 * @param textBoxIndex
	 *            The textBoxIndex to set.
	 */
	public void setTextBoxIndex(int textBoxIndex) {
		this.textBoxIndex = textBoxIndex;
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
	 * @return Returns the sectionName.
	 */
	public String getSectionName() {
		return sectionName;
	}
	
	/**
	 * @param sectionName
	 *            The sectionName to set.
	 */
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
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
	 * @return Returns the subSecName.
	 */
	public String getSubSecName() {
		return subSecName;
	}
	
	/**
	 * @param subSecName
	 *            The subSecName to set.
	 */
	public void setSubSecName(String subSecName) {
		this.subSecName = subSecName;
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
	 * @return Returns the specType.
	 */
	public String getSpecType() {
		return specType;
	}
	
	/**
	 * @param specType
	 *            The specType to set.
	 */
	public void setSpecType(String specType) {
		this.specType = specType;
	}
}