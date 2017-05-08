/*
 * Created on Apr 11, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.form.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

/**
 * @author ps57222
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class EngineeringDataForm extends EMDForm {
	
	private int standardEquipmentSeqNo;
	
	private String standardEquipmentDesc;
	
	private ArrayList standardEquipmentList;
	
	private String stdEquipDesc;
	
	private String hdStdEquipDesc;
	
//	Added for CR-114
	private String enggDataDesc;
	private String stdEnggDataDesc;
	private String hdEnggDataDesc;
	
	/**
	 * @return Returns the hdStdEquipDesc.
	 */
	public String getHdStdEquipDesc() {
		return hdStdEquipDesc;
	}
	
	/**
	 * @param hdStdEquipDesc
	 *            The hdStdEquipDesc to set.
	 */
	public void setHdStdEquipDesc(String hdStdEquipDesc) {
		this.hdStdEquipDesc = hdStdEquipDesc;
	}
	
	/**
	 * @return Returns the stdEquipDesc.
	 */
	public String getStdEquipDesc() {
		return stdEquipDesc;
	}
	
	/**
	 * @param stdEquipDesc
	 *            The stdEquipDesc to set.
	 */
	public void setStdEquipDesc(String stdEquipDesc) {
		this.stdEquipDesc = stdEquipDesc;
	}
	
	/**
	 * @return Returns the standardEquipmentDesc.
	 */
	public String getStandardEquipmentDesc() {
		return standardEquipmentDesc;
	}
	
	/**
	 * @param standardEquipmentDesc
	 *            The standardEquipmentDesc to set.
	 */
	public void setStandardEquipmentDesc(String standardEquipmentDesc) {
		this.standardEquipmentDesc = standardEquipmentDesc;
	}
	
	/**
	 * @return Returns the standardEquipmentList.
	 */
	public ArrayList getStandardEquipmentList() {
		return standardEquipmentList;
	}
	
	/**
	 * @param standardEquipmentList
	 *            The standardEquipmentList to set.
	 */
	public void setStandardEquipmentList(ArrayList standardEquipmentList) {
		this.standardEquipmentList = standardEquipmentList;
	}
	
	public int getStandardEquipmentSeqNo() {
		return standardEquipmentSeqNo;
	}
	
	/**
	 * @param standardEquipmentSeqNo
	 *            The standardEquipmentSeqNo to set.
	 */
	public void setStandardEquipmentSeqNo(int standardEquipmentSeqNo) {
		this.standardEquipmentSeqNo = standardEquipmentSeqNo;
	}

	public String getStdEnggDataDesc() {
		return stdEnggDataDesc;
	}

	public void setStdEnggDataDesc(String stdEnggDataDesc) {
		this.stdEnggDataDesc = stdEnggDataDesc;
	}

	public String getHdEnggDataDesc() {
		return hdEnggDataDesc;
	}

	public void setHdEnggDataDesc(String hdEnggDataDesc) {
		this.hdEnggDataDesc = hdEnggDataDesc;
	}

	public String getEnggDataDesc() {
		return enggDataDesc;
	}

	public void setEnggDataDesc(String enggDataDesc) {
		this.enggDataDesc = enggDataDesc;
	}
	
	
	
	
}