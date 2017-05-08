/**
 * 
 */
package com.EMD.LSDB.vo.common;

import java.util.ArrayList;

/**
 * @author vv49326
 * 
 */
public class SubSectionVO extends LoginVO {
	
	private int subSecSeqNo = 0;
	
	private int secSeqNo = 0;// Added for Goback to ModifySpec Screen
	
	private String secName;
	
	private int revCode;
	
	private int modelSeqNo = 0;
	
	private String subSecName = null;
	
	private String subSecDesc = null;
	
	private String subSecCode = null;
	
	private String userID;
	
	private String colourFlag;
	
	private String secCode;
	
	private ArrayList compGroup;
	
	private ArrayList clauseGroup;
	
	private String subSecDisplay = null;
	
	private int partOfClauseSeqNo = 0;// Added for PartOf
	
	private String claCharstcFlag = null;// Added for CR_81
	
	private String[] orderkeys;
	private String subSecDisplayName = null;
	
	//Added for CR_85 
	private int CombntnSeqNo = 0;
	//Added for CR_94
	private String rearrFlag = null;
	//Added for CR_105
	private String[] subSecNames;
	//Added for CR_109
	private String newSubSecFlag;
	
	private String subCode;
	//Added for Cr-114
	private boolean indicatedSubSec;
	//Added for Cr-114 Ends
	
	//Added for CR-127
	private ArrayList mainList;
	private ArrayList parentClausesList;
	private ArrayList childClausesList;
	//Added for CR-127 ends here

	public boolean isIndicatedSubSec() {
		return indicatedSubSec;
	}

	public void setIndicatedSubSec(boolean indicatedSubSec) {
		this.indicatedSubSec = indicatedSubSec;
	}

	public String getSubCode() {
		return subCode;
	}

	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}

	public String[] getOrderkeys() {
		return orderkeys;
	}
	
	public void setOrderkeys(String[] orderkeys) {
		this.orderkeys = orderkeys;
	}
	
	public int getPartOfClauseSeqNo() {
		return partOfClauseSeqNo;
	}
	
	public void setPartOfClauseSeqNo(int partOfClauseSeqNo) {
		this.partOfClauseSeqNo = partOfClauseSeqNo;
	}
	
	/**
	 * @return Returns the clauseGroup.
	 */
	public ArrayList getClauseGroup() {
		return clauseGroup;
	}
	
	/**
	 * @param clauseGroup
	 *            The clauseGroup to set.
	 */
	public void setClauseGroup(ArrayList clauseGroup) {
		this.clauseGroup = clauseGroup;
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
	 * @return Returns the modelSeqNo.
	 */
	public int getModelSeqNo() {
		return modelSeqNo;
	}
	
	/**
	 * @return modelSeqNo The modelSeqNo to set.
	 */
	public void setModelSeqNo(int modelSeqNo) {
		this.modelSeqNo = modelSeqNo;
	}
	
	/**
	 * @return Returns the secSeqNo.
	 */
	public int getSecSeqNo() {
		return secSeqNo;
	}
	
	/**
	 * @return secSeqNo The secSeqNo to set.
	 */
	public void setSecSeqNo(int secSeqNo) {
		this.secSeqNo = secSeqNo;
	}
	
	/**
	 * @return Returns the subSecCode.
	 */
	public String getSubSecCode() {
		return subSecCode;
	}
	
	/**
	 * @return subSecCode The subSecCode to set.
	 */
	public void setSubSecCode(String subSecCode) {
		this.subSecCode = subSecCode;
	}
	
	/**
	 * @return Returns the subSecDesc.
	 */
	public String getSubSecDesc() {
		return subSecDesc;
	}
	
	/**
	 * @return subSecDesc The subSecDesc to set.
	 */
	public void setSubSecDesc(String subSecDesc) {
		this.subSecDesc = subSecDesc;
	}
	
	/**
	 * @return Returns the subSecName.
	 */
	public String getSubSecName() {
		return subSecName;
	}
	
	/**
	 * @return subSecName The subSecName to set.
	 */
	public void setSubSecName(String subSecName) {
		this.subSecName = subSecName;
	}
	
	/**
	 * @return Returns the subSecSeqNo.
	 */
	public int getSubSecSeqNo() {
		return subSecSeqNo;
	}
	
	/**
	 * @return subSecSeqNo The subSecSeqNo to set.
	 */
	public void setSubSecSeqNo(int subSecSeqNo) {
		this.subSecSeqNo = subSecSeqNo;
	}
	
	/**
	 * @return Returns the userName.
	 */
	public String getUserID() {
		return userID;
	}
	
	/**
	 * @return userName The userName to set.
	 */
	public void setUserID(String userName) {
		this.userID = userName;
	}
	
	/**
	 * @return Returns the compGroup.
	 */
	public ArrayList getCompGroup() {
		return compGroup;
	}
	
	/**
	 * @param compGroup
	 *            The compGroup to set.
	 */
	public void setCompGroup(ArrayList compGroup) {
		this.compGroup = compGroup;
	}
	
	/**
	 * @return Returns the secCode.
	 */
	public String getSecCode() {
		return secCode;
	}
	
	/**
	 * @param secCode
	 *            The secCode to set.
	 */
	public void setSecCode(String secCode) {
		this.secCode = secCode;
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
	 * @return Returns the revCode.
	 */
	public int getRevCode() {
		return revCode;
	}
	
	/**
	 * @param revCode
	 *            The revCode to set.
	 */
	public void setRevCode(int revCode) {
		this.revCode = revCode;
	}
	
	/**
	 * @return Returns the subSecDisplay.
	 */
	public String getSubSecDisplay() {
		return subSecDisplay;
	}
	
	/**
	 * @param subSecDisplay
	 *            The subSecDisplay to set.
	 */
	public void setSubSecDisplay(String subSecDisplay) {
		this.subSecDisplay = subSecDisplay;
	}

	/**
	 * @return Returns the claCharstcFlag.
	 */
	public String getClaCharstcFlag() {
		return claCharstcFlag;
	}

	/**
	 * @param claCharstcFlag The claCharstcFlag to set.
	 */
	public void setClaCharstcFlag(String claCharstcFlag) {
		this.claCharstcFlag = claCharstcFlag;
	}

	public int getCombntnSeqNo() {
		return CombntnSeqNo;
	}

	public void setCombntnSeqNo(int combntnSeqNo) {
		CombntnSeqNo = combntnSeqNo;
	}

	public String getSubSecDisplayName() {
		return subSecDisplayName;
	}

	public void setSubSecDisplayName(String subSecDisplayName) {
		this.subSecDisplayName = subSecDisplayName;
	}
//Added for CR_94
	public String getRearrFlag() {
		return rearrFlag;
	}

	public void setRearrFlag(String rearrFlag) {
		this.rearrFlag = rearrFlag;
	}

	/**
	 * @return Returns the subSecNames.
	 */
	public String[] getSubSecNames() {
		return subSecNames;
	}

	/**
	 * @param subSecNames The subSecNames to set.
	 */
	public void setSubSecNames(String[] subSecNames) {
		this.subSecNames = subSecNames;
	}

	/**
	 * @return Returns the newSubSecFlag.
	 */
	public String getNewSubSecFlag() {
		return newSubSecFlag;
	}

	/**
	 * @param newSubSecFlag The newSubSecFlag to set.
	 */
	public void setNewSubSecFlag(String newSubSecFlag) {
		this.newSubSecFlag = newSubSecFlag;
	}

	public ArrayList getChildClausesList() {
		return childClausesList;
	}

	public void setChildClausesList(ArrayList childClausesList) {
		this.childClausesList = childClausesList;
	}

	public ArrayList getMainList() {
		return mainList;
	}

	public void setMainList(ArrayList mainList) {
		this.mainList = mainList;
	}

	public ArrayList getParentClausesList() {
		return parentClausesList;
	}

	public void setParentClausesList(ArrayList parentClausesList) {
		this.parentClausesList = parentClausesList;
	}
	
	
}
