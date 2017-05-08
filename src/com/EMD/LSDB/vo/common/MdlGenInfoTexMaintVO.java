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

public class MdlGenInfoTexMaintVO extends EMDVO {
	
	public MdlGenInfoTexMaintVO() {
		
	}
	
	private int modelSeqNo;
	
	private int specSeqNo = 0;
	
	private String specName;
	
	private ArrayList specItem;
	
	private int orderKey = 0;
	
	private ArrayList specItemList;
	
	private String dataLocationType;
	
	private String hpDesc;
	
	private int quantity = 0;
	
	private String genArgNotes;
	
	private SpecificationItemVO specificationItem;
	
    //	Added for CR-74 02-06-09
	private int revCode;
	
	private ArrayList hpRatingMarkers;
	
	//Added for CR-76
	private String hpSysMarkFlag;
	
	private String hpSysMarkDesc;
	
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
	 * @return Returns the specItem.
	 */
	public ArrayList getSpecItem() {
		return specItem;
	}
	
	/**
	 * @param specItem The specItem to set.
	 */
	public void setSpecItem(ArrayList specItem) {
		this.specItem = specItem;
	}
	
	/**
	 * @return Returns the specName.
	 */
	public String getSpecName() {
		return specName;
	}
	
	/**
	 * @param specName The specName to set.
	 */
	public void setSpecName(String specName) {
		this.specName = specName;
	}
	
	/**
	 * @return Returns the specSeqNo.
	 */
	public int getSpecSeqNo() {
		return specSeqNo;
	}
	
	/**
	 * @param specSeqNo The specSeqNo to set.
	 */
	public void setSpecSeqNo(int specSeqNo) {
		this.specSeqNo = specSeqNo;
	}
	
	/**
	 * @return Returns the orderKey.
	 */
	public int getOrderKey() {
		return orderKey;
	}
	
	/**
	 * @param orderKey The orderKey to set.
	 */
	public void setOrderKey(int orderKey) {
		this.orderKey = orderKey;
	}
	
	/**
	 * @return Returns the specificationItem.
	 */
	public SpecificationItemVO getSpecificationItem() {
		return specificationItem;
	}
	
	/**
	 * @param specificationItem The specificationItem to set.
	 */
	public void setSpecificationItem(SpecificationItemVO specificationItem) {
		this.specificationItem = specificationItem;
	}
	
	/**
	 * @return Returns the dataLocationType.
	 */
	public String getDataLocationType() {
		return dataLocationType;
	}
	
	/**
	 * @param dataLocationType The dataLocationType to set.
	 */
	public void setDataLocationType(String dataLocationType) {
		this.dataLocationType = dataLocationType;
	}
	
	/**
	 * @return Returns the specItemList.
	 */
	public ArrayList getSpecItemList() {
		return specItemList;
	}
	
	/**
	 * @param specItemList The specItemList to set.
	 */
	public void setSpecItemList(ArrayList specItemList) {
		this.specItemList = specItemList;
	}
	
	/**
	 * @return Returns the hpDesc.
	 */
	public String getHpDesc() {
		return hpDesc;
	}
	
	/**
	 * @param hpDesc The hpDesc to set.
	 */
	public void setHpDesc(String hpDesc) {
		this.hpDesc = hpDesc;
	}
	
	/**
	 * @return Returns the genArgNotes.
	 */
	public String getGenArgNotes() {
		return genArgNotes;
	}
	
	/**
	 * @param genArgNotes The genArgNotes to set.
	 */
	public void setGenArgNotes(String genArgNotes) {
		this.genArgNotes = genArgNotes;
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
	
}