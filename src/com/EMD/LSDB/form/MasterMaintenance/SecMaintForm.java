/**
 * 
 */
package com.EMD.LSDB.form.MasterMaintenance;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.EMD.LSDB.form.common.EMDForm;

/**
 * @author PS57222
 * 
 */
public class SecMaintForm extends EMDForm {
	
	private int sectionSeqNo;
	
	private String sectionName;
	
	private String sectionComments;
	
	private String sectionCode;
	
	private int modelSeqNo;
	
	private int model;
	
	private String modelName;
	
	private boolean successMessage;
	
	private String errorMessage;
	
	private String section;
	
	private String hdSelectedModel; /*
	* This hidden variable is used to display
	* the selected model while search and add
	*/
	
	private String hdSecComments;
	
	private String hdsection;
	
	private ArrayList sectionList;
	
	private ArrayList modelList;
	
	private String onLoad = "-1";
	
	private String secName;
	
	private String secComments;
	
	private int hdPreSelectedModel;/*
	* This hidden variable is used to check
	* whether the selected model and previous
	* selected models are same
	*/
	
	private String method;
	
	/**
	 * Added For LSDB_CR-46(PM&I) Added on 28-Aug-08 by ps57222
	 */
	
	private String hdSelectedSpecType;
	
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
	 * @return Returns the hdSelectedSpecType.
	 */
	public String getHdSelectedSpecType() {
		return hdSelectedSpecType;
	}
	
	/**
	 * @param hdSelectedSpecType
	 *            The hdSelectedSpecType to set.
	 */
	public void setHdSelectedSpecType(String hdSelectedSpecType) {
		this.hdSelectedSpecType = hdSelectedSpecType;
	}
	
	/**
	 * @return Returns the hdsection.
	 */
	public String getHdsection() {
		return hdsection;
	}
	
	/**
	 * @param hdsection
	 *            The hdsection to set.
	 */
	public void setHdsection(String hdsection) {
		this.hdsection = hdsection;
	}
	
	/**
	 * @return Returns the model.
	 */
	public int getModel() {
		return model;
	}
	
	/**
	 * @param model
	 *            The model to set.
	 */
	public void setModel(int model) {
		this.model = model;
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
	 * @return Returns the modelSeqNo.
	 */
	
	/**
	 * @return Returns the section.
	 */
	public String getSection() {
		return section;
	}
	
	/**
	 * @param section
	 *            The section to set.
	 */
	public void setSection(String section) {
		this.section = section;
	}
	
	/**
	 * @return Returns the sectionCode.
	 */
	public String getSectionCode() {
		return sectionCode;
	}
	
	/**
	 * @param sectionCode
	 *            The sectionCode to set.
	 */
	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}
	
	/**
	 * @return Returns the sectionComments.
	 */
	public String getSectionComments() {
		return sectionComments;
	}
	
	/**
	 * @param sectionComments
	 *            The sectionComments to set.
	 */
	public void setSectionComments(String sectionComments) {
		this.sectionComments = sectionComments;
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
	 * @return Returns the sectionVo.
	 */
	
	/**
	 * @return Returns the hdSecComments.
	 */
	public String getHdSecComments() {
		return hdSecComments;
	}
	
	/**
	 * @param hdSecComments
	 *            The hdSecComments to set.
	 */
	public void setHdSecComments(String hdSecComments) {
		this.hdSecComments = hdSecComments;
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
	
	public void reset(ActionMapping objActionMapping,
			HttpServletRequest objHttpServletRequest) {
		this.secName = null;
		this.secComments = null;
		
	}
	
	/**
	 * @return Returns the successMessage.
	 */
	public boolean isSuccessMessage() {
		return successMessage;
	}
	
	/**
	 * @param successMessage
	 *            The successMessage to set.
	 */
	public void setSuccessMessage(boolean successMessage) {
		this.successMessage = successMessage;
	}
	
	/**
	 * @return Returns the errorMessage.
	 */
	
	/**
	 * @return Returns the secComments.
	 */
	public String getSecComments() {
		return secComments;
	}
	
	/**
	 * @param secComments
	 *            The secComments to set.
	 */
	public void setSecComments(String secComments) {
		this.secComments = secComments;
	}
	
	/**
	 * @return Returns the secName.
	 */
	public String getSecName() {
		return secName;
	}
	
	/**
	 * @param secName
	 *            The secName to set.
	 */
	public void setSecName(String secName) {
		this.secName = secName;
	}
	
	/**
	 * @return Returns the errorMessage.
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	
	/**
	 * @param errorMessage
	 *            The errorMessage to set.
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	/**
	 * @return Returns the onLoad.
	 */
	public String getOnLoad() {
		return onLoad;
	}
	
	/**
	 * @param onLoad
	 *            The onLoad to set.
	 */
	public void setOnLoad(String onLoad) {
		this.onLoad = onLoad;
	}
	
	/**
	 * @return Returns the hdSelectedModel.
	 */
	public String getHdSelectedModel() {
		return hdSelectedModel;
	}
	
	/**
	 * @param hdSelectedModel
	 *            The hdSelectedModel to set.
	 */
	public void setHdSelectedModel(String hdSelectedModel) {
		this.hdSelectedModel = hdSelectedModel;
	}
	
	/**
	 * @return Returns the hdPreSelectedModel.
	 */
	public int getHdPreSelectedModel() {
		return hdPreSelectedModel;
	}
	
	/**
	 * @param hdPreSelectedModel
	 *            The hdPreSelectedModel to set.
	 */
	public void setHdPreSelectedModel(int hdPreSelectedModel) {
		this.hdPreSelectedModel = hdPreSelectedModel;
	}
	
	/**
	 * @return Returns the method.
	 */
	public String getMethod() {
		return method;
	}
	
	/**
	 * @param method
	 *            The method to set.
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	
}
