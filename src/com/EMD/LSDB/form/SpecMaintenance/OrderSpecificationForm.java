/*
 * Created on Aug 28, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.form.SpecMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the form fields for the Model Specification
 ******************************************************************************/

public class OrderSpecificationForm extends EMDForm {
	
	private int orderSpecSeqNo = 0;
	
	private int orderSpecItemSeqNo = 0;
	
	private int ItemSeqNo = 0;
	
	private String orderHpDesc;
	
	private String orderSpecName;
	
	private String orderSpecItemDesc;
	
	private String orderSpecItemValue;
	
	private String dataLocType;
	
	private String hdndataLocType;
	
	private int hdnorderKey = 0;
	//Modified Int to String as part of issue in CR_91
	private String orderNo;
	
	private int hdnitemSeqNo = 0;
	
	private String hdnitemDesc;
	
	private String hdnitemValue;
	
	private String hdnSpecName;
	
	private int hdnSpecSeqNo;
	
	private ArrayList specList;
	
	private ArrayList specItemList;
	
	private ArrayList specItem;
	
	private ArrayList sectionList;
	
	private String colourFlag;
	
	// private String hideMessage ="-1";
	
    //Added for CR-74
	private ArrayList revisionList;
	
	private int revCode;
	
	private ArrayList hpRatingMarkers;
	
	//Added for CR-76 VV49326
    private String hpSysMarkFlag;
	
	private String hpSysMarkDesc;
	
	//Added For CR_84
	private String perfCurveLinkFlag;
	
	private String genArrLinkFlag;
	
	//Added For CR_104
	
	private String custMdlName;
	
	/**
	 * @return Returns the hpRatingMarkers.
	 */
	public ArrayList getHpRatingMarkers() {
		return hpRatingMarkers;
	}

	/**
	 * @param hpRatingMarkers The hpRatingMarkers to set.
	 */
	public void setHpRatingMarkers(ArrayList hpRatingMarkers) {
		this.hpRatingMarkers = hpRatingMarkers;
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
	 * @return Returns the specItem.
	 */
	public ArrayList getSpecItem() {
		return specItem;
	}
	
	/**
	 * @param specItem
	 *            The specItem to set.
	 */
	public void setSpecItem(ArrayList specItem) {
		this.specItem = specItem;
	}
	
	/**
	 * @return Returns the orderSpecItemDesc.
	 */
	public String getOrderSpecItemDesc() {
		return orderSpecItemDesc;
	}
	
	/**
	 * @param orderSpecItemDesc
	 *            The orderSpecItemDesc to set.
	 */
	public void setOrderSpecItemDesc(String orderSpecItemDesc) {
		this.orderSpecItemDesc = orderSpecItemDesc;
	}
	
	/**
	 * @return Returns the orderSpecItemSeqNo.
	 */
	public int getOrderSpecItemSeqNo() {
		return orderSpecItemSeqNo;
	}
	
	/**
	 * @param orderSpecItemSeqNo
	 *            The orderSpecItemSeqNo to set.
	 */
	public void setOrderSpecItemSeqNo(int orderSpecItemSeqNo) {
		this.orderSpecItemSeqNo = orderSpecItemSeqNo;
	}
	
	/**
	 * @return Returns the orderSpecItemValue.
	 */
	public String getOrderSpecItemValue() {
		return orderSpecItemValue;
	}
	
	/**
	 * @param orderSpecItemValue
	 *            The orderSpecItemValue to set.
	 */
	public void setOrderSpecItemValue(String orderSpecItemValue) {
		this.orderSpecItemValue = orderSpecItemValue;
	}
	
	/**
	 * @return Returns the orderSpecName.
	 */
	public String getOrderSpecName() {
		return orderSpecName;
	}
	
	/**
	 * @param orderSpecName
	 *            The orderSpecName to set.
	 */
	public void setOrderSpecName(String orderSpecName) {
		this.orderSpecName = orderSpecName;
	}
	
	/**
	 * @return Returns the orderSpecSeqNo.
	 */
	public int getOrderSpecSeqNo() {
		return orderSpecSeqNo;
	}
	
	/**
	 * @param orderSpecSeqNo
	 *            The orderSpecSeqNo to set.
	 */
	public void setOrderSpecSeqNo(int orderSpecSeqNo) {
		this.orderSpecSeqNo = orderSpecSeqNo;
	}
	
	/**
	 * @return Returns the specItemList.
	 */
	public ArrayList getSpecItemList() {
		return specItemList;
	}
	
	/**
	 * @param specItemList
	 *            The specItemList to set.
	 */
	public void setSpecItemList(ArrayList specItemList) {
		this.specItemList = specItemList;
	}
	
	/**
	 * @return Returns the dataLocType.
	 */
	public String getDataLocType() {
		return dataLocType;
	}
	
	/**
	 * @param dataLocType
	 *            The dataLocType to set.
	 */
	public void setDataLocType(String dataLocType) {
		this.dataLocType = dataLocType;
	}
	
	/**
	 * @return Returns the itemSeqNo.
	 */
	public int getItemSeqNo() {
		return ItemSeqNo;
	}
	
	/**
	 * @param itemSeqNo
	 *            The itemSeqNo to set.
	 */
	public void setItemSeqNo(int itemSeqNo) {
		ItemSeqNo = itemSeqNo;
	}
	
	/**
	 * @return Returns the specList.
	 */
	public ArrayList getSpecList() {
		return specList;
	}
	
	/**
	 * @param specList
	 *            The specList to set.
	 */
	public void setSpecList(ArrayList specList) {
		this.specList = specList;
	}
	
	/**
	 * @return Returns the hdnitemDesc.
	 */
	public String getHdnitemDesc() {
		return hdnitemDesc;
	}
	
	/**
	 * @param hdnitemDesc
	 *            The hdnitemDesc to set.
	 */
	public void setHdnitemDesc(String hdnitemDesc) {
		this.hdnitemDesc = hdnitemDesc;
	}
	
	/**
	 * @return Returns the hdnitemSeqNo.
	 */
	public int getHdnitemSeqNo() {
		return hdnitemSeqNo;
	}
	
	/**
	 * @param hdnitemSeqNo
	 *            The hdnitemSeqNo to set.
	 */
	public void setHdnitemSeqNo(int hdnitemSeqNo) {
		this.hdnitemSeqNo = hdnitemSeqNo;
	}
	
	/**
	 * @return Returns the hdnitemValue.
	 */
	public String getHdnitemValue() {
		return hdnitemValue;
	}
	
	/**
	 * @param hdnitemValue
	 *            The hdnitemValue to set.
	 */
	public void setHdnitemValue(String hdnitemValue) {
		this.hdnitemValue = hdnitemValue;
	}
	
	/**
	 * @return Returns the hdndataLocType.
	 */
	public String getHdndataLocType() {
		return hdndataLocType;
	}
	
	/**
	 * @param hdndataLocType
	 *            The hdndataLocType to set.
	 */
	public void setHdndataLocType(String hdndataLocType) {
		this.hdndataLocType = hdndataLocType;
	}
	
	/**
	 * @return Returns the hdnorderKey.
	 */
	public int getHdnorderKey() {
		return hdnorderKey;
	}
	
	/**
	 * @param hdnorderKey
	 *            The hdnorderKey to set.
	 */
	public void setHdnorderKey(int hdnorderKey) {
		this.hdnorderKey = hdnorderKey;
	}
	
	/**
	 * @return Returns the hdnSpecName.
	 */
	public String getHdnSpecName() {
		return hdnSpecName;
	}
	
	/**
	 * @param hdnSpecName
	 *            The hdnSpecName to set.
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
	 * @param hdnSpecSeqNo
	 *            The hdnSpecSeqNo to set.
	 */
	public void setHdnSpecSeqNo(int hdnSpecSeqNo) {
		this.hdnSpecSeqNo = hdnSpecSeqNo;
	}
	
	/**
	 * @return Returns the colourFlag.
	 */
	public String getColourFlag() {
		return colourFlag;
	}
	
	/**
	 * @param colourFlag
	 *            The colourFlag to set.
	 */
	public void setColourFlag(String colourFlag) {
		this.colourFlag = colourFlag;
	}
	
	/**
	 * @return Returns the orderHpDesc.
	 */
	public String getOrderHpDesc() {
		return orderHpDesc;
	}
	
	/**
	 * @param orderHpDesc
	 *            The orderHpDesc to set.
	 */
	public void setOrderHpDesc(String orderHpDesc) {
		this.orderHpDesc = orderHpDesc;
	}
	
	/**
	 * @return Returns the orderNo.
	 */
	public String getOrderNo() {
		return orderNo;
	}
	
	/**
	 * @param orderNo
	 *            The orderNo to set.
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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
	 * @return Returns the revisionList.
	 */
	public ArrayList getRevisionList() {
		return revisionList;
	}

	/**
	 * @param revisionList The revisionList to set.
	 */
	public void setRevisionList(ArrayList revisionList) {
		this.revisionList = revisionList;
	}

	/**
	 * @return Returns the hpSysMarkDesc.
	 */
	public String getHpSysMarkDesc() {
		return hpSysMarkDesc;
	}

	/**
	 * @param hpSysMarkDesc The hpSysMarkDesc to set.
	 */
	public void setHpSysMarkDesc(String hpSysMarkDesc) {
		this.hpSysMarkDesc = hpSysMarkDesc;
	}

	/**
	 * @return Returns the hpSysMarkFlag.
	 */
	public String getHpSysMarkFlag() {
		return hpSysMarkFlag;
	}

	/**
	 * @param hpSysMarkFlag The hpSysMarkFlag to set.
	 */
	public void setHpSysMarkFlag(String hpSysMarkFlag) {
		this.hpSysMarkFlag = hpSysMarkFlag;
	}

	/**
	 * @return Returns the genArrLinkFlag.
	 */
	public String getGenArrLinkFlag() {
		return genArrLinkFlag;
	}

	/**
	 * @param genArrLinkFlag The genArrLinkFlag to set.
	 */
	public void setGenArrLinkFlag(String genArrLinkFlag) {
		this.genArrLinkFlag = genArrLinkFlag;
	}

	/**
	 * @return Returns the perfCurveLinkFlag.
	 */
	public String getPerfCurveLinkFlag() {
		return perfCurveLinkFlag;
	}

	/**
	 * @param perfCurveLinkFlag The perfCurveLinkFlag to set.
	 */
	public void setPerfCurveLinkFlag(String perfCurveLinkFlag) {
		this.perfCurveLinkFlag = perfCurveLinkFlag;
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
	
}
