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
 * This class has the setter and getter methods for the Model Specification
 ******************************************************************************************/

public class SpecificationItemVO extends EMDVO {
	
	public SpecificationItemVO() {
		
	}
	
	private int modSpecItemSeqNo;
	
	private String modSpecItemDesc;
	
	private String modSpecItemValue;
	
	private int itemSeqNo;
	
	private String itemDesc;
	
	private String itemValue;
	//Added for CR-74 VV49326 02-06-09
	private ArrayList itemMarker;
	
    //Added for CR-76 VV49326 06-08-09
	private String sysMarkFlag;
	
	private String sysMarkDesc;
	
	//Added for CR-121
	private int itemCount;
	
	/**
	 * @return Returns the itemDesc.
	 */
	public String getItemDesc() {
		return itemDesc;
	}
	
	/**
	 * @param itemDesc The itemDesc to set.
	 */
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	
	/**
	 * @return Returns the itemSeqNo.
	 */
	public int getItemSeqNo() {
		return itemSeqNo;
	}
	
	/**
	 * @param itemSeqNo The itemSeqNo to set.
	 */
	public void setItemSeqNo(int itemSeqNo) {
		this.itemSeqNo = itemSeqNo;
	}
	
	/**
	 * @return Returns the itemValue.
	 */
	public String getItemValue() {
		return itemValue;
	}
	
	/**
	 * @param itemValue The itemValue to set.
	 */
	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
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
	 * @return Returns the itemMarker.
	 */
	public ArrayList getItemMarker() {
		return itemMarker;
	}

	/**
	 * @param itemMarker The itemMarker to set.
	 */
	public void setItemMarker(ArrayList itemMarker) {
		this.itemMarker = itemMarker;
	}

	/**
	 * @return Returns the sysMarkDesc.
	 */
	public String getSysMarkDesc() {
		return sysMarkDesc;
	}

	/**
	 * @param sysMarkDesc The sysMarkDesc to set.
	 */
	public void setSysMarkDesc(String sysMarkDesc) {
		this.sysMarkDesc = sysMarkDesc;
	}

	/**
	 * @return Returns the sysMarkFlag.
	 */
	public String getSysMarkFlag() {
		return sysMarkFlag;
	}

	/**
	 * @param sysMarkFlag The sysMarkFlag to set.
	 */
	public void setSysMarkFlag(String sysMarkFlag) {
		this.sysMarkFlag = sysMarkFlag;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	
	
}