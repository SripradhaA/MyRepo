package com.EMD.LSDB.form.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

public class CompSearchForm extends EMDForm {
	
	private String characterisationFlag;
	
	private ArrayList compList;
	
	private int componentSeqNo;
	
	private String componentName;
	
	private int modelSeqNo;
	
	private String componentGroupName;
	
	//Added For CR_81 on 29-Dec-09 by RR68151
	private int compGroupTypeSeqNo;	

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
	 * @return Returns the characterisationFlag.
	 */
	public String getCharacterisationFlag() {
		return characterisationFlag;
	}
	
	/**
	 * @param characterisationFlag
	 *            The characterisationFlag to set.
	 */
	public void setCharacterisationFlag(String compCategory) {
		this.characterisationFlag = compCategory;
	}
	
	/**
	 * @return Returns the compList.
	 */
	public ArrayList getCompList() {
		return compList;
	}
	
	/**
	 * @param compList
	 *            The compList to set.
	 */
	public void setCompList(ArrayList compList) {
		this.compList = compList;
	}
	
	/**
	 * @return Returns the componentName.
	 */
	public String getComponentName() {
		return componentName;
	}
	
	/**
	 * @param componentName
	 *            The componentName to set.
	 */
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	
	/**
	 * @return Returns the componentSeqNo.
	 */
	public int getComponentSeqNo() {
		return componentSeqNo;
	}
	
	/**
	 * @param componentSeqNo
	 *            The componentSeqNo to set.
	 */
	public void setComponentSeqNo(int componentSeqNo) {
		this.componentSeqNo = componentSeqNo;
	}
	
	public String getComponentGroupName() {
		return componentGroupName;
	}

	public void setComponentGroupName(String componentGroupName) {
		this.componentGroupName = componentGroupName;
	}

	/**
	 * @return Returns the compGroupTypeSeqNo.
	 */
	public int getCompGroupTypeSeqNo() {
		return compGroupTypeSeqNo;
	}

	/**
	 * @param compGroupTypeSeqNo The compGroupTypeSeqNo to set.
	 */
	public void setCompGroupTypeSeqNo(int compGroupTypeSeqNo) {
		this.compGroupTypeSeqNo = compGroupTypeSeqNo;
	}	
}
