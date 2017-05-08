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
 * This class has the form fields for the Model Specification
 ******************************************************************************************/

public class ModelSpecificationForm extends EMDForm {
	
	private int modelSeqNo = 0;
	
	private String hdnModelName;
	
	private int modSpecSeqNo = 0;
	
	private int modSpecItemSeqNo = 0;
	
	private String modSpecName;
	
	private String modSpecItemDesc;
	
	private String modSpecItemValue;
	
	private ArrayList modelList;
	
	private boolean successMessage;
	
	private String errorMessage;
	
	private String hideMessage = "-1";
	
	private String modelName;
	
	private String hdnSpecName;
	
	private int hdnSpecSeqNo;
	
	private String hdnSpecItemDesc;
	
	private String hdnSpecItemValue;
	
	private int hdnPrevSelModel;
	
	private ArrayList modSpecList;
	
	//	Added for CR- 46 PM&I Spec
	private String hdnSelSpecType;
	
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
	 * @return Returns the errorMessage.
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	
	/**
	 * @param errorMessage The errorMessage to set.
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
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
	
	/**
	 * @return Returns the modSpecItemDesc.
	 */
	public String getModSpecItemDesc() {
		return modSpecItemDesc;
	}
	
	/**
	 * @param modSpecItemDesc The modSpecItemDesc to set.
	 */
	public void setModSpecItemDesc(String modSpecItemDesc) {
		this.modSpecItemDesc = modSpecItemDesc;
	}
	
	/**
	 * @return Returns the modSpecItemValue.
	 */
	public String getModSpecItemValue() {
		return modSpecItemValue;
	}
	
	/**
	 * @param modSpecItemValue The modSpecItemValue to set.
	 */
	public void setModSpecItemValue(String modSpecItemValue) {
		this.modSpecItemValue = modSpecItemValue;
	}
	
	/**
	 * @return Returns the modSpecName.
	 */
	public String getModSpecName() {
		return modSpecName;
	}
	
	/**
	 * @param modSpecName The modSpecName to set.
	 */
	public void setModSpecName(String modSpecName) {
		this.modSpecName = modSpecName;
	}
	
	/**
	 * @return Returns the modSpecSeqNo.
	 */
	public int getModSpecSeqNo() {
		return modSpecSeqNo;
	}
	
	/**
	 * @param modSpecSeqNo The modSpecSeqNo to set.
	 */
	public void setModSpecSeqNo(int modSpecSeqNo) {
		this.modSpecSeqNo = modSpecSeqNo;
	}
	
	/**
	 * @return Returns the modSpecItemSeqNo.
	 */
	public int getModSpecItemSeqNo() {
		return modSpecItemSeqNo;
	}
	
	/**
	 * @param modSpecItemSeqNo The modSpecItemSeqNo to set.
	 */
	public void setModSpecItemSeqNo(int modSpecItemSeqNo) {
		this.modSpecItemSeqNo = modSpecItemSeqNo;
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
	 * @return Returns the modSpecList.
	 */
	public ArrayList getModSpecList() {
		return modSpecList;
	}
	
	/**
	 * @param modSpecList The modSpecList to set.
	 */
	public void setModSpecList(ArrayList modSpecList) {
		this.modSpecList = modSpecList;
	}
	
	/**
	 * @return Returns the hdnSpecName.
	 */
	public String getHdnSpecName() {
		return hdnSpecName;
	}
	
	/**
	 * @param hdnSpecName The hdnSpecName to set.
	 */
	public void setHdnSpecName(String hdnSpecName) {
		this.hdnSpecName = hdnSpecName;
	}
	
	/**
	 * @return Returns the hdnSpecSeqNo.
	 */
	public int getHdnSpecSeqNo() {
		return hdnSpecSeqNo;
	}
	
	/**
	 * @param hdnSpecSeqNo The hdnSpecSeqNo to set.
	 */
	public void setHdnSpecSeqNo(int hdnSpecSeqNo) {
		this.hdnSpecSeqNo = hdnSpecSeqNo;
	}
	
	/**
	 * @return Returns the hdnSpecItemDesc.
	 */
	public String getHdnSpecItemDesc() {
		return hdnSpecItemDesc;
	}
	
	/**
	 * @param hdnSpecItemDesc The hdnSpecItemDesc to set.
	 */
	public void setHdnSpecItemDesc(String hdnSpecItemDesc) {
		this.hdnSpecItemDesc = hdnSpecItemDesc;
	}
	
	/**
	 * @return Returns the hdnSpecItemValue.
	 */
	public String getHdnSpecItemValue() {
		return hdnSpecItemValue;
	}
	
	/**
	 * @param hdnSpecItemValue The hdnSpecItemValue to set.
	 */
	public void setHdnSpecItemValue(String hdnSpecItemValue) {
		this.hdnSpecItemValue = hdnSpecItemValue;
	}
	
	/**
	 * @return Returns the hdnPrevSelModel.
	 */
	public int getHdnPrevSelModel() {
		return hdnPrevSelModel;
	}
	
	/**
	 * @param hdnPrevSelModel The hdnPrevSelModel to set.
	 */
	public void setHdnPrevSelModel(int hdnPrevSelModel) {
		this.hdnPrevSelModel = hdnPrevSelModel;
	}
	
}