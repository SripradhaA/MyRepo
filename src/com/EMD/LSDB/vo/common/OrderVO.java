/*
 * Created on Jun 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.vo.common;

import java.util.ArrayList;

/*******************************************************************************************
 * @author  Satyam Computer Services Ltd  
 * @version 1.0  
 * This class has the setter and getter methods for the Model 
 ******************************************************************************************/

public class OrderVO extends EMDVO {
	
	public OrderVO() {
		
	}
	
	private int modelSeqNo;
	
	private int modelCustTypeSeqNo;
	
	private int specTypeSeqNo;
	
	private int distSeqNo;
	
	private int cusSeqNo;
	
	private String sapCusCode;
	
	private String orderNo;
	
	private int quantity;
	
	private String specRevCode;
	
	private int specSubRevCode;
	
	private String dataLocTypeCode;
	
	private int orderKey;
	
	private int specStatusCode;
	
	private String statusDesc;
	
	private String specTypeName;
	
	private String modelName;
	
	private ArrayList customerVo;
	
	private ArrayList sectionName;
	
	private String revFlag;
	
	private String finalFlag;
	
	private int revCode;//this is for Revision
	
	private int[] modelSelected;
	
	private ArrayList fileLoc;
	
	private int selectedSectionSeqNo;
	
	private String customerName;
	
	private int checkedOrder;
	
	private String publishedDate;
	
	private String CustSeqNos;
	
	private int versionNo;
	
	private String clauseDesc;
	
	private ArrayList tableArrayData1;
	
	private String versionIndicator;
	
	

	
	/** 	New Attribute OrderKeys is added for LSDB_CR-06
	 * 		Added on 15-April-08
	 * 		by ps57222
	 */
	
	private String[] orderKeys;
	
	/**
	 *  Added for LSDB_CR_24 clause image change
	 */
	
	private String appendixFlag;
	
	private String distributorName;
	
	//Added for CR-74 18-06-09
	private String clauseDelFlag;
	
	//Added for LSDB_CR-76 
	private String SortBy;
	
	private int fromOrderKey;
	
	private int toOrderkey; 
	
//	Added for CR-86 Adding Dyanamic numbering SD41630
	private String dynamicNoFlag;
	
	//Added for CR-79 Adding PDF Header Image RR68151
	private String pdfHeaderFlag;
	
	private ArrayList ssOrderKeys;
	
	//Change for LSDb_CR-87
	private String sortByFlag;
	
	//	Added for CR_97
	private String changeControlFlag;
	
	//Added for CR_101
	private int[] customerSeqnos;
	
	private String customerSeqno;
	//CR_101 Ends here
	
	//Added for CR_104 Custom Model Name and General Information text
	private String genInfoText;
	
	private String custMdlName;
	//CR_101 Ends here
	
	//Added For CR_104
	private String subject=null;
	private String bodyCont=null;

	//Added for CR_104
	
	private String publishedUser;
	
	//Added for CR_104
	private String showLatestFlag;
	
	private String userMarkerFlag; 

	//CR_104 Ends here
	
	//Added for CR_104 - Preserve General Information Flag
	private String presrveGenInfoFlag;
//Added For CR_106 - customer and distributor logos flag to display - Modified to NULL for CR_106 QA Fix 
	private String custLogoFlag=null;
	private String distriLogoFlag=null; 
//	Added for CR_106
	private int custImageSeqNo;
	private int distImageSeqNo;
	//CR_104 Ends here

	//Added for CR_106 - On Demand Spec Supplement
	private int prevOrderKey;
	//CR_106 Ends here
	
	//Added For CR_108 copy and model Indicators ON/OFF
	private String copyMdlIndFlag;
	//Added for CR_112
	private int[] ModelSeqnos;
	private String ModelSeqno;
	//CR_112 Ends here
	
	//Added for CR-118
	private String genInfoTextForProofReading;
	
	//Added for CR-131
	private int tableDataColSize=0;
	
	
	//Added for CR-135
	private String prevOrderNo;
	private String prevCustomerName;
	private String prevQuantity;
	private String prevCustCode;
	private String prevModelName;
	
	public String getPrevCustCode() {
		return prevCustCode;
	}

	public void setPrevCustCode(String prevCustCode) {
		this.prevCustCode = prevCustCode;
	}

	public String getPrevCustomerName() {
		return prevCustomerName;
	}

	public void setPrevCustomerName(String prevCustomerName) {
		this.prevCustomerName = prevCustomerName;
	}

	public String getPrevModelName() {
		return prevModelName;
	}

	public void setPrevModelName(String prevModelName) {
		this.prevModelName = prevModelName;
	}

	public String getPrevQuantity() {
		return prevQuantity;
	}

	public void setPrevQuantity(String prevQuantity) {
		this.prevQuantity = prevQuantity;
	}
	
	
	public int getTableDataColSize() {
		return tableDataColSize;
	}

	public void setTableDataColSize(int tableDataColSize) {
		this.tableDataColSize = tableDataColSize;
	}

	public String getUserMarkerFlag() {
		return userMarkerFlag;
	}

	public void setUserMarkerFlag(String userMarkerFlag) {
		this.userMarkerFlag = userMarkerFlag;
	}

	public String getCustomerSeqno() {
		return customerSeqno;
	}

	/**
	 * @param customerSeqno The customerSeqno to set.
	 */
	public void setCustomerSeqno(String customerSeqno) {
		this.customerSeqno = customerSeqno;
	}

	/**
	 * @return Returns the clauseDelFlag.
	 */
	public String getClauseDelFlag() {
		return clauseDelFlag;
	}

	/**
	 * @param clauseDelFlag The clauseDelFlag to set.
	 */
	public void setClauseDelFlag(String clauseDelFlag) {
		this.clauseDelFlag = clauseDelFlag;
	}

	/**
	 * @return Returns the distributorName.
	 */
	public String getDistributorName() {
		return distributorName;
	}

	/**
	 * @param distributorName The distributorName to set.
	 */
	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}

	
	/**
	 * @return Returns the appendixFlag.
	 */
	public String getAppendixFlag() {
		return appendixFlag;
	}
	
	/**
	 * @param appendixFlag The appendixFlag to set.
	 */
	public void setAppendixFlag(String appendixFlag) {
		this.appendixFlag = appendixFlag;
	}
	
	/**
	 * @return Returns the orderKeys.
	 */
	public String[] getOrderKeys() {
		return orderKeys;
	}
	
	/**
	 * @param orderKeys The orderKeys to set.
	 */
	public void setOrderKeys(String[] orderKeys) {
		this.orderKeys = orderKeys;
	}
	
	/*private int sortBy;
	 
	 public int getSortBy() {
	 return sortBy;
	 }
	 
	 public void setSortBy(int sortBy) {
	 this.sortBy = sortBy;
	 }*/
	
	/**
	 * @return Returns the custSeqNos.
	 */
	public String getCustSeqNos() {
		return CustSeqNos;
	}
	
	/**
	 * @param custSeqNos The custSeqNos to set.
	 */
	public void setCustSeqNos(String custSeqNos) {
		CustSeqNos = custSeqNos;
	}
	
	public int getCheckedOrder() {
		return checkedOrder;
	}
	
	public void setCheckedOrder(int checkedOrder) {
		this.checkedOrder = checkedOrder;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public int getSelectedSectionSeqNo() {
		return selectedSectionSeqNo;
	}
	
	public void setSelectedSectionSeqNo(int selectedSectionSeqNo) {
		this.selectedSectionSeqNo = selectedSectionSeqNo;
	}
	
	/**
	 * @return Returns the fileLoc.
	 */
	public ArrayList getFileLoc() {
		return fileLoc;
	}
	
	/**
	 * @param fileLoc The fileLoc to set.
	 */
	public void setFileLoc(ArrayList fileLoc) {
		this.fileLoc = fileLoc;
	}
	
	/**
	 * @return Returns the cusSeqNo.
	 */
	public int getCusSeqNo() {
		return cusSeqNo;
	}
	
	/**
	 * @param cusSeqNo The cusSeqNo to set.
	 */
	public void setCusSeqNo(int cusSeqNo) {
		this.cusSeqNo = cusSeqNo;
	}
	
	/**
	 * @return Returns the dataLocTypeCode.
	 */
	public String getDataLocTypeCode() {
		return dataLocTypeCode;
	}
	
	/**
	 * @param dataLocTypeCode The dataLocTypeCode to set.
	 */
	public void setDataLocTypeCode(String dataLocTypeCode) {
		this.dataLocTypeCode = dataLocTypeCode;
	}
	
	/**
	 * @return Returns the distSeqNo.
	 */
	public int getDistSeqNo() {
		return distSeqNo;
	}
	
	/**
	 * @param distSeqNo The distSeqNo to set.
	 */
	public void setDistSeqNo(int distSeqNo) {
		this.distSeqNo = distSeqNo;
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
	 * @return Returns the orderNo.
	 */
	public String getOrderNo() {
		return orderNo;
	}
	
	/**
	 * @param orderNo The orderNo to set.
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	/**
	 * @return Returns the quantity.
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * @param quantity The quantity to set.
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * @return Returns the sapCusCode.
	 */
	public String getSapCusCode() {
		return sapCusCode;
	}
	
	/**
	 * @param sapCusCode The sapCusCode to set.
	 */
	public void setSapCusCode(String sapCusCode) {
		this.sapCusCode = sapCusCode;
	}
	
	/**
	 * @return Returns the specRevCode.
	 */
	public String getSpecRevCode() {
		return specRevCode;
	}
	
	/**
	 * @param specRevCode The specRevCode to set.
	 */
	public void setSpecRevCode(String specRevCode) {
		this.specRevCode = specRevCode;
	}
	
	/**
	 * @return Returns the specSubRevCode.
	 */
	public int getSpecSubRevCode() {
		return specSubRevCode;
	}
	
	/**
	 * @param specSubRevCode The specSubRevCode to set.
	 */
	public void setSpecSubRevCode(int specSubRevCode) {
		this.specSubRevCode = specSubRevCode;
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
	
	public int getOrderKey() {
		return orderKey;
	}
	
	public void setOrderKey(int orderKey) {
		this.orderKey = orderKey;
	}
	
	public int getSpecStatusCode() {
		return specStatusCode;
	}
	
	public void setSpecStatusCode(int specStatusCode) {
		this.specStatusCode = specStatusCode;
	}
	
	public String getSpecTypeName() {
		return specTypeName;
	}
	
	public void setSpecTypeName(String specTypeName) {
		this.specTypeName = specTypeName;
	}
	
	public String getStatusDesc() {
		return statusDesc;
	}
	
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
	public ArrayList getCustomerVo() {
		return customerVo;
	}
	
	public void setCustomerVo(ArrayList customerVo) {
		this.customerVo = customerVo;
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
	
	public ArrayList getSectionName() {
		return sectionName;
	}
	
	public void setSectionName(ArrayList sectionName) {
		this.sectionName = sectionName;
	}
	
	/**
	 * @return Returns the finalFlag.
	 */
	public String getFinalFlag() {
		return finalFlag;
	}
	
	/**
	 * @param finalFlag The finalFlag to set.
	 */
	public void setFinalFlag(String finalFlag) {
		this.finalFlag = finalFlag;
	}
	
	/**
	 * @return Returns the revFlag.
	 */
	public String getRevFlag() {
		return revFlag;
	}
	
	/**
	 * @param revFlag The revFlag to set.
	 */
	public void setRevFlag(String revFlag) {
		this.revFlag = revFlag;
	}
	
	/**
	 * @return Returns the modelSelected.
	 */
	public int[] getModelSelected() {
		return modelSelected;
	}
	
	/**
	 * @param modelSelected The modelSelected to set.
	 */
	public void setModelSelected(int[] modelSelected) {
		this.modelSelected = modelSelected;
	}
	
	/**
	 * @return Returns the revCode.
	 */
	public int getRevCode() {
		return revCode;
	}
	
	/**
	 * @param revCode The revCode to set.
	 */
	public void setRevCode(int revCode) {
		this.revCode = revCode;
	}
	
	/**
	 * @return Returns the publishedDate.
	 */
	public String getPublishedDate() {
		return publishedDate;
	}
	
	/**
	 * @param publishedDate The publishedDate to set.
	 */
	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}
	//CR_104 Published User
	public String getPublishedUser() {
	return publishedUser;
	}
	
	public void setPublishedUser(String publishedUser){
	
		this.publishedUser=publishedUser;
	}
	//CR_104 ends here
	
	public int getVersionNo() {
		return versionNo;
	}
	
	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}
	
	public String getVersionIndicator() {
		return versionIndicator;
	}
	
	public void setVersionIndicator(String versionIndicator) {
		this.versionIndicator = versionIndicator;
	}
	
	public String getClauseDesc() {
		return clauseDesc;
	}
	
	public void setClauseDesc(String clauseDesc) {
		this.clauseDesc = clauseDesc;
	}
	
	public ArrayList getTableArrayData1() {
		return tableArrayData1;
	}
	
	public void setTableArrayData1(ArrayList tableArrayData1) {
		this.tableArrayData1 = tableArrayData1;
	}

	/**
	 * @return Returns the sortBy.
	 */
	public String getSortBy() {
		return SortBy;
	}

	/**
	 * @param sortBy The sortBy to set.
	 */
	public void setSortBy(String sortBy) {
		SortBy = sortBy;
	}

	/**
	 * @return Returns the fromOrderKey.
	 */
	public int getFromOrderKey() {
		return fromOrderKey;
	}

	/**
	 * @param fromOrderKey The fromOrderKey to set.
	 */
	public void setFromOrderKey(int fromOrderKey) {
		this.fromOrderKey = fromOrderKey;
	}

	/**
	 * @return Returns the toOrderkey.
	 */
	public int getToOrderkey() {
		return toOrderkey;
	}

	/**
	 * @param toOrderkey The toOrderkey to set.
	 */
	public void setToOrderkey(int toOrderkey) {
		this.toOrderkey = toOrderkey;
	}

	/**
	 * @return Returns the ssOrderKeys.
	 */
	public ArrayList getSsOrderKeys() {
		return ssOrderKeys;
	}

	/**
	 * @param ssOrderKeys The ssOrderKeys to set.
	 */
	public void setSsOrderKeys(ArrayList ssOrderKeys) {
		this.ssOrderKeys = ssOrderKeys;
	}

	public String getPdfHeaderFlag() {
		return pdfHeaderFlag;
	}

	public void setPdfHeaderFlag(String pdfHeaderFlag) {
		this.pdfHeaderFlag = pdfHeaderFlag;
	}

	public  String getDynamicNoFlag() {
		return dynamicNoFlag;
	}

	public void setDynamicNoFlag(String dynamicNoFlag) {
		this.dynamicNoFlag = dynamicNoFlag;
	}

	public String getSortByFlag() {
		return sortByFlag;
	}

	public void setSortByFlag(String sortByFlag) {
		this.sortByFlag = sortByFlag;
	}

	//Added for CR_97 Start 
 	public String getChangeControlFlag() {
		return changeControlFlag;
	}
 	
	public void setChangeControlFlag(String changeControlFlag) {
		this.changeControlFlag = changeControlFlag;
	}
	//Added for CR_97 End

	/**
	 * @return Returns the customerSeqnos.
	 */
	public int[] getCustomerSeqnos() {
		return customerSeqnos;
	}

	/**
	 * @param customerSeqnos The customerSeqnos to set.
	 */
	public void setCustomerSeqnos(int[] customerSeqnos) {
		this.customerSeqnos = customerSeqnos;
	}

	/**
	 * @return Returns the custMdlName.
	 */
	public String getCustMdlName() {
		return custMdlName;
	}

	/**
	 * @param custMdlName The custMdlName to set.
	 */
	public void setCustMdlName(String custMdlName) {
		this.custMdlName = custMdlName;
	}

	/**
	 * @return Returns the genInfoText.
	 */
	public String getGenInfoText() {
		return genInfoText;
	}

	/**
	 * @param genInfoText The genInfoText to set.
	 */
	public void setGenInfoText(String genInfoText) {
		this.genInfoText = genInfoText;
	}
	
	public String getBodyCont() {
		return bodyCont;
	}

	public void setBodyCont(String bodyCont) {
		this.bodyCont = bodyCont;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	//private ModelVO modelVO;
	
	//private SectionVO sectionVO;
	
	//private DistributorVO distributorVO;

/**
	 * @return Returns the showLatestFlag.
	 */
	public String getShowLatestFlag() {
		return showLatestFlag;
	}

	/**
	 * @param showLatestFlag The showLatestFlag to set.
	 */
	public void setShowLatestFlag(String showLatestFlag) {
		this.showLatestFlag = showLatestFlag;
	}

	/**
	 * @return Returns the presrveGenInfoFlag.
	 */
	public String getPresrveGenInfoFlag() {
		return presrveGenInfoFlag;
	}

	/**
	 * @param presrveGenInfoFlag The presrveGenInfoFlag to set.
	 */
	public void setPresrveGenInfoFlag(String presrveGenInfoFlag) {
		this.presrveGenInfoFlag = presrveGenInfoFlag;
	}
	/**
	 * @return Returns the prevOrderKey.
	 */
	public int getPrevOrderKey() {
		return prevOrderKey;
	}

	/**
	 * @param prevOrderKey The prevOrderKey to set.
	 */
	public void setPrevOrderKey(int prevOrderKey) {
		this.prevOrderKey = prevOrderKey;
	}
	public int getCustImageSeqNo() {
		return custImageSeqNo;
	}

	public void setCustImageSeqNo(int custImageSeqNo) {
		this.custImageSeqNo = custImageSeqNo;
	}

	public int getDistImageSeqNo() {
		return distImageSeqNo;
	}

	public void setDistImageSeqNo(int distImageSeqNo) {
		this.distImageSeqNo = distImageSeqNo;
	}

	public String getCustLogoFlag() {
		return custLogoFlag;
	}

	public void setCustLogoFlag(String custLogoFlag) {
		this.custLogoFlag = custLogoFlag;
	}



	public String getDistriLogoFlag() {
		return distriLogoFlag;
	}

	public void setDistriLogoFlag(String distriLogoFlag) {
		this.distriLogoFlag = distriLogoFlag;
	}

	public String getCopyMdlIndFlag() {
		return copyMdlIndFlag;
	}

	public void setCopyMdlIndFlag(String copyMdlIndFlag) {
		this.copyMdlIndFlag = copyMdlIndFlag;
	}

	public String getModelSeqno() {
		return ModelSeqno;
	}

	public void setModelSeqno(String modelSeqno) {
		ModelSeqno = modelSeqno;
	}

	public int[] getModelSeqnos() {
		return ModelSeqnos;
	}

	public void setModelSeqnos(int[] modelSeqnos) {
		ModelSeqnos = modelSeqnos;
	}

	public String getGenInfoTextForProofReading() {
		return genInfoTextForProofReading;
	}

	public void setGenInfoTextForProofReading(String genInfoTextForProofReading) {
		this.genInfoTextForProofReading = genInfoTextForProofReading;
	}

	public String getPrevOrderNo() {
		return prevOrderNo;
	}

	public void setPrevOrderNo(String prevOrderNo) {
		this.prevOrderNo = prevOrderNo;
	}
	
	
}