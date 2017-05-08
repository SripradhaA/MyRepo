/*
 * Created on Jun 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.vo.common;


/*******************************************************************************************
 * @author  Satyam Computer Services Ltd  
 * @version 1.0  
 * This class has the setter and getter methods for the Model 
 ******************************************************************************************/

public class ModelVo extends EMDVO {
	
	public ModelVo() {
		
	}
	
	private int modelSeqNo;
	
	private String modelName;
	
	private String modelDesc;
	
	private String hpowerRate;
	
	private String specTypeName;
	
	private int modelCustTypeSeqNo;
	
	private int specTypeSeqNo;
	
	//Added for CR_97 for Change Control Type.
	
	private String chngCtrlTypeFlag; 
	
	//Aded for CR_106 genaral info itmes LS200_GEN_INFO_TEXT_DRFT
	private String genInfoTextDraft;
	private String genInfoTextOpen;
	private int intSuccess=0;
	
	//Added for CR_118
	private String modelFlag;
	private int[] componentGrpSeqNos;
	private String[] dispInCOCFlags;
	//Added for CR_118 Ends
	
	

	public int[] getComponentGrpSeqNos() {
		return componentGrpSeqNos;
	}

	public void setComponentGrpSeqNos(int[] componentGrpSeqNos) {
		this.componentGrpSeqNos = componentGrpSeqNos;
	}

	public String[] getDispInCOCFlags() {
		return dispInCOCFlags;
	}

	public void setDispInCOCFlags(String[] dispInCOCFlags) {
		this.dispInCOCFlags = dispInCOCFlags;
	}

	/**
	 * @return Returns the hpowerRate.
	 */
	public String getHpowerRate() {
		return hpowerRate;
	}
	
	/**
	 * @param hpowerRate The hpowerRate to set.
	 */
	public void setHpowerRate(String hpowerRate) {
		this.hpowerRate = hpowerRate;
	}
	
	/**
	 * @return Returns the modelCustTypeSeqNo.
	 */
	public int getModelCustTypeSeqNo() {
		return modelCustTypeSeqNo;
	}
	
	/**
	 * @param modelCustTypeSeqNo The modelCustTypeSeqNo to set.
	 */
	public void setModelCustTypeSeqNo(int modelCustTypeSeqNo) {
		this.modelCustTypeSeqNo = modelCustTypeSeqNo;
	}
	
	/**
	 * @return Returns the modelDesc.
	 */
	public String getModelDesc() {
		return modelDesc;
	}
	
	/**
	 * @param modelDesc The modelDesc to set.
	 */
	public void setModelDesc(String modelDesc) {
		this.modelDesc = modelDesc;
	}
	
	/**
	 * @return Returns the modelName.
	 */
	public String getModelName() {
		return modelName;
	}
	
	/**
	 * @param modelName The modelName to set.
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
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
	 * @return Returns the specTypeName.
	 */
	public String getSpecTypeName() {
		return specTypeName;
	}
	
	/**
	 * @param specTypeName The specTypeName to set.
	 */
	public void setSpecTypeName(String specTypeName) {
		this.specTypeName = specTypeName;
	}
	
	/**
	 * @return Returns the specTypeSeqNo.
	 */
	public int getSpecTypeSeqNo() {
		return specTypeSeqNo;
	}
	
	/**
	 * @param specTypeSeqNo The specTypeSeqNo to set.
	 */
	public void setSpecTypeSeqNo(int specTypeSeqNo) {
		this.specTypeSeqNo = specTypeSeqNo;
	}

	public String getChngCtrlTypeFlag() {
		return chngCtrlTypeFlag;
	}

	public void setChngCtrlTypeFlag(String chngCtrlTypeFlag) {
		this.chngCtrlTypeFlag = chngCtrlTypeFlag;
	}

	public String getGenInfoTextDraft() {
		return genInfoTextDraft;
	}

	public void setGenInfoTextDraft(String genInfoTextDraft) {
		this.genInfoTextDraft = genInfoTextDraft;
	}

	public String getGenInfoTextOpen() {
		return genInfoTextOpen;
	}

	public void setGenInfoTextOpen(String genInfoTextOpen) {
		this.genInfoTextOpen = genInfoTextOpen;
	}

	public int getIntSuccess() {
		return intSuccess;
	}

	public void setIntSuccess(int intSuccess) {
		this.intSuccess = intSuccess;
	}

	public String getModelFlag() {
		return modelFlag;
	}

	public void setModelFlag(String modelFlag) {
		this.modelFlag = modelFlag;
	}
	
}