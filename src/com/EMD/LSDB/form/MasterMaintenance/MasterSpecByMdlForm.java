package com.EMD.LSDB.form.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

public class MasterSpecByMdlForm extends EMDForm {
	
	private ArrayList modelList;
	
	private ArrayList customerList;
	
	private int modelSeqNo;
	
	private int[] custSeqNos;
	
	private String customerSeq;
	
	private String hdnSelectedCustomers;
	
	private String hdnSelectedMdlNames;
	
	private String hnOrderKey;
	
	private String modelName;
	
	private ArrayList modelSubSectionList;
	
	private ArrayList modelSpecList;
	
	//Added for CR-46 PM&I Spec
	private String hdnSelSpecType;
	
	//Added for LSDB_CR-77
	private ArrayList cusOptionCatalogList;
	
	private ArrayList listImages;
	
	private String reportCreationDate;
	
	//Added for CR_118
	private String dispInCOC;
	private int hdnCompGroupSeqNo;
	private int hdnDispInCOC;
	

	public String getDispInCOC() {
		return dispInCOC;
	}

	public void setDispInCOC(String dispInCOC) {
		this.dispInCOC = dispInCOC;
	}

	public int getHdnCompGroupSeqNo() {
		return hdnCompGroupSeqNo;
	}

	public void setHdnCompGroupSeqNo(int hdnCompGroupSeqNo) {
		this.hdnCompGroupSeqNo = hdnCompGroupSeqNo;
	}

	public int getHdnDispInCOC() {
		return hdnDispInCOC;
	}

	public void setHdnDispInCOC(int hdnDispInCOC) {
		this.hdnDispInCOC = hdnDispInCOC;
	}

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
	
	public String getModelName() {
		return modelName;
	}
	
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
	public String getHnOrderKey() {
		return hnOrderKey;
	}
	
	public void setHnOrderKey(String hnOrderKey) {
		this.hnOrderKey = hnOrderKey;
	}
	
	/**
	 * @return Returns the customerList.
	 */
	public ArrayList getCustomerList() {
		return customerList;
	}
	
	/**
	 * @param customerList The customerList to set.
	 */
	public void setCustomerList(ArrayList customerList) {
		this.customerList = customerList;
	}
	
	/**
	 * @return Returns the custSeqNos.
	 */
	public int[] getCustSeqNos() {
		return custSeqNos;
	}
	
	/**
	 * @param custSeqNos The custSeqNos to set.
	 */
	public void setCustSeqNos(int[] custSeqNos) {
		this.custSeqNos = custSeqNos;
	}
	
	/**
	 * @return Returns the hdnSelectedCustomers.
	 */
	public String getHdnSelectedCustomers() {
		return hdnSelectedCustomers;
	}
	
	/**
	 * @param hdnSelectedCustomers The hdnSelectedCustomers to set.
	 */
	public void setHdnSelectedCustomers(String hdnSelectedCustomers) {
		this.hdnSelectedCustomers = hdnSelectedCustomers;
	}
	
	/**
	 * @return Returns the hdnSelectedMdlNames.
	 */
	public String getHdnSelectedMdlNames() {
		return hdnSelectedMdlNames;
	}
	
	/**
	 * @param hdnSelectedMdlNames The hdnSelectedMdlNames to set.
	 */
	public void setHdnSelectedMdlNames(String hdnSelectedMdlNames) {
		this.hdnSelectedMdlNames = hdnSelectedMdlNames;
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
	 * @return Returns the customerSeq.
	 */
	public String getCustomerSeq() {
		return customerSeq;
	}
	
	/**
	 * @param customerSeq The customerSeq to set.
	 */
	public void setCustomerSeq(String customerSeq) {
		this.customerSeq = customerSeq;
	}
	
	public ArrayList getModelSubSectionList() {
		return modelSubSectionList;
	}
	
	public void setModelSubSectionList(ArrayList modelSubSectionList) {
		this.modelSubSectionList = modelSubSectionList;
	}
	
	public ArrayList getModelSpecList() {
		return modelSpecList;
	}
	
	public void setModelSpecList(ArrayList modelSpecList) {
		this.modelSpecList = modelSpecList;
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
	
}
