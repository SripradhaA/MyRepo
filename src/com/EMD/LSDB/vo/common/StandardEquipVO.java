package com.EMD.LSDB.vo.common;


/*******************************************************************************************
 * @author  Satyam Computer Services Ltd  
 * @version 1.0  
 * This class has the setter and getter methods for the StandardEquipment 
 ******************************************************************************************/

public class StandardEquipVO extends EMDVO {
	private int standardEquipmentSeqNo;
	
	private String standardEquipmentDesc;
	
	private String standardEquipmentList;
	
	//Added for CR-114
	private String stdEnggDataDesc;
	
	/**
	 * @return Returns the standardEquipmentDesc.
	 */
	public String getStandardEquipmentDesc() {
		return standardEquipmentDesc;
	}
	
	/**
	 * @param standardEquipmentDesc The standardEquipmentDesc to set.
	 */
	public void setStandardEquipmentDesc(String standardEquipmentDesc) {
		this.standardEquipmentDesc = standardEquipmentDesc;
	}
	
	/**
	 * @return Returns the standardEquipmentSeqNo.
	 */
	public int getStandardEquipmentSeqNo() {
		return standardEquipmentSeqNo;
	}
	
	/**
	 * @param standardEquipmentSeqNo The standardEquipmentSeqNo to set.
	 */
	public void setStandardEquipmentSeqNo(int standardEquipmentSeqNo) {
		this.standardEquipmentSeqNo = standardEquipmentSeqNo;
	}
	
	/**
	 * @return Returns the standardEquipmentList.
	 */
	public String getStandardEquipmentList() {
		return standardEquipmentList;
	}
	
	/**
	 * @param standardEquipmentList The standardEquipmentList to set.
	 */
	public void setStandardEquipmentList(String standardEquipmentList) {
		this.standardEquipmentList = standardEquipmentList;
	}

	public String getStdEnggDataDesc() {
		return stdEnggDataDesc;
	}

	public void setStdEnggDataDesc(String stdEnggDataDesc) {
		this.stdEnggDataDesc = stdEnggDataDesc;
	}

    
	
	
}
