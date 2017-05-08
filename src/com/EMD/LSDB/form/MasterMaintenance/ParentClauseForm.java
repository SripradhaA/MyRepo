package com.EMD.LSDB.form.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

public class ParentClauseForm extends EMDForm {
	private String modelName;
	
	private String sectionName;
	
	private String subSectionName;
	
	private ArrayList componentVO;
	
	private int clauseSeqNo;
	
	private String clauseDesc;
	
	public ArrayList componentList;
	
	private int clauseVersionNo;
	
	private ArrayList tableArrayData1;
	
	// Added for LSDB_CR_46 PM&I Change
	private String specType;
	
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
	
	/**
	 * @return Returns the tableClauseData.
	 */
	public ArrayList getTableArrayData1() {
		return tableArrayData1;
	}
	
	/**
	 * @param tableClauseData
	 *            The tableClauseData to set.
	 */
	public void setTableArrayData1(ArrayList tableClauseData) {
		this.tableArrayData1 = tableClauseData;
	}
	
	/**
	 * @return Returns the clauseDesc.
	 */
	public String getClauseDesc() {
		return clauseDesc;
	}
	
	/**
	 * @param clauseDesc
	 *            The clauseDesc to set.
	 */
	public void setClauseDesc(String clauseDesc) {
		this.clauseDesc = clauseDesc;
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
	 * @return Returns the componentList.
	 */
	public ArrayList getComponentList() {
		return componentList;
	}
	
	/**
	 * @param componentList
	 *            The componentList to set.
	 */
	public void setComponentList(ArrayList componentList) {
		this.componentList = componentList;
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
	 * @return Returns the parentList.
	 */
	public ArrayList getComponentVO() {
		return componentVO;
	}
	
	/**
	 * @param parentList
	 *            The parentList to set.
	 */
	public void setComponentVO(ArrayList parentList) {
		this.componentVO = parentList;
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
	 * @return Returns the subSectionName.
	 */
	public String getSubSectionName() {
		return subSectionName;
	}
	
	/**
	 * @param subSectionName
	 *            The subSectionName to set.
	 */
	public void setSubSectionName(String subSectionName) {
		this.subSectionName = subSectionName;
	}
	
	/**
	 * @return Returns the clauseVersionNo.
	 */
	public int getClauseVersionNo() {
		return clauseVersionNo;
	}
	
	/**
	 * @param clauseVersionNo
	 *            The clauseVersionNo to set.
	 */
	public void setClauseVersionNo(int clauseVersionNo) {
		this.clauseVersionNo = clauseVersionNo;
	}
}
