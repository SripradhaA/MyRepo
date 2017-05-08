/*
 * Created on Jun 5, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.form.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

/*******************************************************************************************
 * @author  Satyam Computer Services Ltd  
 * @version 1.0  
 * This class has the form fields for the Model 
 ******************************************************************************************/

public class ModelMaintForm extends EMDForm {
	
	private int modelSeqNo = 0;
	
	private int modCustTypeSeqNo = 0;
	
	//Commented for LSDB_CR_46
	//private int specTypeNo = 0;
	
	//Main fields 
	private String modName = null;
	
	private String modDesc = null;
	
	private String horsePower = null;
	
	//Search results fields
	
	private String hdnModelName;
	
	private String hdnHorsePower;
	
	private String hdnModelDesc;
	
	private int hdnSpecTypeSeqNo = 0;
	
	private int hdnCusTypeSeqNo = 0;
	
	private String hdnSpecTypeName;
	
	private int hdnPrevSelSpec = 0;
	
	private int hdnPrevSelCusSeq = 0;
	
	private ArrayList modelList;
	
	private int selSpecSeqNo = 0;
	
	private boolean successMessage;
	
	private String hideMessage = "-1";
	
	//Added for CR_97 for Change Control Type
	private String changeControlFlag;
	
	private String[] changeCtrlRadio;
	
	private String hdnChangeControlFlag;
	
	private String copyChangeControl;
	
	//Added for CR_101 for copy model across Specification type

	private int toModelSpecTypeNo;
	
	//Added for CR_118
	private String modelFlag;
	private String hdnModelFlag;
	private int hideUnhideModel;
	private boolean hdnModelMaintScreen =false;
	//Added for CR_118 Ends
	
		
	public boolean isHdnModelMaintScreen() {
		return hdnModelMaintScreen;
	}

	public void setHdnModelMaintScreen(boolean hdnModelMaintScreen) {
		this.hdnModelMaintScreen = hdnModelMaintScreen;
	}

	

	public int getHideUnhideModel() {
		return hideUnhideModel;
	}

	public void setHideUnhideModel(int hideUnhideModel) {
		this.hideUnhideModel = hideUnhideModel;
	}

	/**
	 * @return Returns the toModelSpecTypeNo.
	 */
	public int getToModelSpecTypeNo() {
		return toModelSpecTypeNo;
	}

	/**
	 * @param toModelSpecTypeNo The toModelSpecTypeNo to set.
	 */
	public void setToModelSpecTypeNo(int toModelSpecTypeNo) {
		this.toModelSpecTypeNo = toModelSpecTypeNo;
	}
//CR_101 Ends here 
	/**
	 * @return Returns the hideMessage.
	 */
	public String getHideMessage() {
		return hideMessage;
	}
	
	/**
	 * @param hideMessage The hideMessage to set.
	 */
	public void setHideMessage(String hideMessage) {
		this.hideMessage = hideMessage;
	}
	
	/**
	 * @return Returns the horsePower.
	 */
	public String getHorsePower() {
		return horsePower;
	}
	
	/**
	 * @param horsePower The horsePower to set.
	 */
	public void setHorsePower(String horsePower) {
		this.horsePower = horsePower;
	}
	
	/**
	 * @return Returns the modCustTypeSeqNo.
	 */
	public int getModCustTypeSeqNo() {
		return modCustTypeSeqNo;
	}
	
	/**
	 * @param modCustTypeSeqNo The modCustTypeSeqNo to set.
	 */
	public void setModCustTypeSeqNo(int modCustTypeSeqNo) {
		this.modCustTypeSeqNo = modCustTypeSeqNo;
	}
	
	/**
	 * @return Returns the modDesc.
	 */
	public String getModDesc() {
		return modDesc;
	}
	
	/**
	 * @param modDesc The modDesc to set.
	 */
	public void setModDesc(String modDesc) {
		this.modDesc = modDesc;
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
	 * @return Returns the modName.
	 */
	public String getModName() {
		return modName;
	}
	
	/**
	 * @param modName The modName to set.
	 */
	public void setModName(String modName) {
		this.modName = modName;
	}
	
	/**
	 * @return Returns the selSpecSeqNo.
	 */
	public int getSelSpecSeqNo() {
		return selSpecSeqNo;
	}
	
	/**
	 * @param selSpecSeqNo The selSpecSeqNo to set.
	 */
	public void setSelSpecSeqNo(int selSpecSeqNo) {
		this.selSpecSeqNo = selSpecSeqNo;
	}
	
	/**
	 * @return Returns the successMessage.
	 */
	public boolean isSuccessMessage() {
		return successMessage;
	}
	
	/**
	 * @param successMessage The successMessage to set.
	 */
	public void setSuccessMessage(boolean successMessage) {
		this.successMessage = successMessage;
	}
	
	//Commented for LSDB_CR_46(PM&I)
	/**
	 * @return Returns the specTypeNo.
	 */
	/*public int getSpecTypeNo() {
	 return specTypeNo;
	 }*/
	
	/**
	 * @param specTypeNo The specTypeNo to set.
	 */
	/*public void setSpecTypeNo(int specTypeNo) {
	 this.specTypeNo = specTypeNo;
	 }*/
	
	/**
	 * @return Returns the hdnCusTypeSeqNo.
	 */
	public int getHdnCusTypeSeqNo() {
		return hdnCusTypeSeqNo;
	}
	
	/**
	 * @param hdnCusTypeSeqNo The hdnCusTypeSeqNo to set.
	 */
	public void setHdnCusTypeSeqNo(int hdnCusTypeSeqNo) {
		this.hdnCusTypeSeqNo = hdnCusTypeSeqNo;
	}
	
	/**
	 * @return Returns the hdnHorsePower.
	 */
	public String getHdnHorsePower() {
		return hdnHorsePower;
	}
	
	/**
	 * @param hdnHorsePower The hdnHorsePower to set.
	 */
	public void setHdnHorsePower(String hdnHorsePower) {
		this.hdnHorsePower = hdnHorsePower;
	}
	
	/**
	 * @return Returns the hdnModelDesc.
	 */
	public String getHdnModelDesc() {
		return hdnModelDesc;
	}
	
	/**
	 * @param hdnModelDesc The hdnModelDesc to set.
	 */
	public void setHdnModelDesc(String hdnModelDesc) {
		this.hdnModelDesc = hdnModelDesc;
	}
	
	/**
	 * @return Returns the hdnModelName.
	 */
	public String getHdnModelName() {
		return hdnModelName;
	}
	
	/**
	 * @param hdnModelName The hdnModelName to set.
	 */
	public void setHdnModelName(String hdnModelName) {
		this.hdnModelName = hdnModelName;
	}
	
	/**
	 * @return Returns the hdnSpecTypeSeqNo.
	 */
	public int getHdnSpecTypeSeqNo() {
		return hdnSpecTypeSeqNo;
	}
	
	/**
	 * @param hdnSpecTypeSeqNo The hdnSpecTypeSeqNo to set.
	 */
	public void setHdnSpecTypeSeqNo(int hdnSpecTypeSeqNo) {
		this.hdnSpecTypeSeqNo = hdnSpecTypeSeqNo;
	}
	
	/**
	 * @return Returns the hdnSpecTypeName.
	 */
	public String getHdnSpecTypeName() {
		return hdnSpecTypeName;
	}
	
	/**
	 * @param hdnSpecTypeName The hdnSpecTypeName to set.
	 */
	public void setHdnSpecTypeName(String hdnSpecTypeName) {
		this.hdnSpecTypeName = hdnSpecTypeName;
	}
	
	/**
	 * @return Returns the hdnPrevSelSpec.
	 */
	public int getHdnPrevSelSpec() {
		return hdnPrevSelSpec;
	}
	
	/**
	 * @param hdnPrevSelSpec The hdnPrevSelSpec to set.
	 */
	public void setHdnPrevSelSpec(int hdnPrevSelSpec) {
		this.hdnPrevSelSpec = hdnPrevSelSpec;
	}
	
	/**
	 * @return Returns the hdnPrevSelCusSeq.
	 */
	public int getHdnPrevSelCusSeq() {
		return hdnPrevSelCusSeq;
	}
	
	/**
	 * @param hdnPrevSelCusSeq The hdnPrevSelCusSeq to set.
	 */
	public void setHdnPrevSelCusSeq(int hdnPrevSelCusSeq) {
		this.hdnPrevSelCusSeq = hdnPrevSelCusSeq;
	}
	
	//Added for CR_97 for Change Control Type
	/**
	 * @return Returns the changeControlFlag.
	 */
	public String getChangeControlFlag() {
		return changeControlFlag;
	}
	/**
	 * @param changeControlFlag The changeControlFlag to set.
	 */
	public void setChangeControlFlag(String changeControlFlag) {
		this.changeControlFlag = changeControlFlag;
	}
	

	public String[] getChangeCtrlRadio() {
		return changeCtrlRadio;
	}

	public void setChangeCtrlRadio(String[] changeCtrlRadio) {
		this.changeCtrlRadio = changeCtrlRadio;
	}

	public String getHdnChangeControlFlag() {
		return hdnChangeControlFlag;
	}

	public void setHdnChangeControlFlag(String hdnChangeControlFlag) {
		this.hdnChangeControlFlag = hdnChangeControlFlag;
	}


	public String getCopyChangeControl() {
		return copyChangeControl;
	}

	public void setCopyChangeControl(String copyChangeControl) {
		this.copyChangeControl = copyChangeControl;
	}

	public String getModelFlag() {
		return modelFlag;
	}

	public void setModelFlag(String modelFlag) {
		this.modelFlag = modelFlag;
	}

	public String getHdnModelFlag() {
		return hdnModelFlag;
	}

	public void setHdnModelFlag(String hdnModelFlag) {
		this.hdnModelFlag = hdnModelFlag;
	}

	

	//	CR_97 Ends here
}