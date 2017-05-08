package com.EMD.LSDB.form.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class contains the fields for Master Maintence report
 ******************************************************************************/

public class CustOptCatForm extends EMDForm {
	
	private ArrayList modelList;
	
	private String modelName;
	
	private int modelSeqNo;
	
	private ArrayList sectionList;
	
	private ArrayList modelSubSectionList;
	
	private ArrayList modelSpecList;
	
	//Added for CR-113 @vipul to turn on/off deleted clause versions
	
	private String showDeletedClauses;
	
	private boolean chkBoxStatus;
	
    private ArrayList cusOptionCatalogList;
	
	private ArrayList listImages;
	
	private String reportCreationDate;
	
	private int spectypeSeqno = 0;
	
	private ArrayList customerList;
	
	// Added for CR-46 PM&I Spec
	private String hdnSelSpecType;
	
	//Added for CR_118
	private ArrayList compGroupList;
	//private String dispInCOC;
	//private int hdnCompGroupSeqNo;
	//private int hdnDispInCOC;
	//Added for CR_118 Ends
	
	/*
	public String getDispInCOC() {
		return dispInCOC;
	}

	public void setDispInCOC(String dispInCOC) {
		this.dispInCOC = dispInCOC;
	}
*./
	

	/**
	 * @return Returns the hdnSelSpecType.
	 */
	public String getHdnSelSpecType() {
		return hdnSelSpecType;
	}
	
	/**
	 * @param hdnSelSpecType
	 *            The hdnSelSpecType to set.
	 */
	public void setHdnSelSpecType(String hdnSelSpecType) {
		this.hdnSelSpecType = hdnSelSpecType;
	}
	
	/**
	 * @return Returns the sections.
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
	 * @param modelSubSectionList
	 *            The modelSubSectionList to set.
	 */
	public ArrayList getModelSubSectionList() {
		return modelSubSectionList;
	}
	
	/**
	 * @return Returns the modelSubSectionList.
	 */
	public void setModelSubSectionList(ArrayList modelSubSectionList) {
		this.modelSubSectionList = modelSubSectionList;
	}
	
	/**
	 * @return Returns the modelSpecList.
	 */
	public ArrayList getModelSpecList() {
		return modelSpecList;
	}
	
	/**
	 * @param modelSpecList
	 *            The modelSpecList to set.
	 */
	public void setModelSpecList(ArrayList modelSpecList) {
		this.modelSpecList = modelSpecList;
	}
	//Added for CR-113 @vipul to turn on/off deleted clause versions
	public String getShowDeletedClauses() {
		return showDeletedClauses;
	}

	public void setShowDeletedClauses(String showDeletedClauses) {
		this.showDeletedClauses = showDeletedClauses;
	}

	public boolean isChkBoxStatus() {
		return chkBoxStatus;
	}

	public void setChkBoxStatus(boolean chkBoxStatus) {
		this.chkBoxStatus = chkBoxStatus;
	}
	/**
	 * @return Returns the cusOptionCatalogList.
	 */
	public ArrayList getCusOptionCatalogList() {
		return cusOptionCatalogList;
	}

	/**
	 * @param cusOptionCatalogList The cusOptionCatalogList to set.
	 */
	public void setCusOptionCatalogList(ArrayList cusOptionCatalogList) {
		this.cusOptionCatalogList = cusOptionCatalogList;
	}

	/**
	 * @return Returns the listImages.
	 */
	public ArrayList getListImages() {
		return listImages;
	}

	/**
	 * @param listImages The listImages to set.
	 */
	public void setListImages(ArrayList listImages) {
		this.listImages = listImages;
	}

	/**
	 * @return Returns the reportCreationDate.
	 */
	public String getReportCreationDate() {
		return reportCreationDate;
	}

	/**
	 * @param reportCreationDate The reportCreationDate to set.
	 */
	public void setReportCreationDate(String reportCreationDate) {
		this.reportCreationDate = reportCreationDate;
	}
	public int getSpectypeSeqno() {
		return spectypeSeqno;
	}
	
	public void setSpectypeSeqno(int spectypeSeqno) {
		this.spectypeSeqno = spectypeSeqno;
	}

	public ArrayList getCustomerList() {
		return customerList;
	}

	public void setCustomerList(ArrayList customerList) {
		this.customerList = customerList;
	}

	public ArrayList getCompGroupList() {
		return compGroupList;
	}

	public void setCompGroupList(ArrayList compGroupList) {
		this.compGroupList = compGroupList;
	}
	
}
