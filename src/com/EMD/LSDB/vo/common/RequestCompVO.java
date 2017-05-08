/*
 * Created on Oct 20, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.vo.common;

/**
 * @author PS57222
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class RequestCompVO extends EMDVO {
	
	private int changeTypeSeqNo;
	
	private String changeTypeDesc;
	
	private int oldCompSeqNo;
	
	private String oldCompName;
	
	private String oldCompDefaultFlag;
	
	private String newCompName;
	
	private String newCompDefaultFlag;
	
	private String compNameColorFlag;
	
	private String compDefColorFlag;
	
	/**
	 * @return Returns the changeTypeSeqNo.
	 */
	public int getChangeTypeSeqNo() {
		return changeTypeSeqNo;
	}
	
	/**
	 * @param changeTypeSeqNo
	 *            The changeTypeSeqNo to set.
	 */
	public void setChangeTypeSeqNo(int changeTypeSeqNo) {
		this.changeTypeSeqNo = changeTypeSeqNo;
	}
	
	/**
	 * @return Returns the newCompDefaultFlag.
	 */
	public String getNewCompDefaultFlag() {
		return newCompDefaultFlag;
	}
	
	/**
	 * @param newCompDefaultFlag
	 *            The newCompDefaultFlag to set.
	 */
	public void setNewCompDefaultFlag(String newCompDefaultFlag) {
		this.newCompDefaultFlag = newCompDefaultFlag;
	}
	
	/**
	 * @return Returns the newCompName.
	 */
	public String getNewCompName() {
		return newCompName;
	}
	
	/**
	 * @param newCompName
	 *            The newCompName to set.
	 */
	public void setNewCompName(String newCompName) {
		this.newCompName = newCompName;
	}
	
	/**
	 * @return Returns the oldCompDefaultFlag.
	 */
	public String getOldCompDefaultFlag() {
		return oldCompDefaultFlag;
	}
	
	/**
	 * @param oldCompDefaultFlag
	 *            The oldCompDefaultFlag to set.
	 */
	public void setOldCompDefaultFlag(String oldCompDefaultFlag) {
		this.oldCompDefaultFlag = oldCompDefaultFlag;
	}
	
	/**
	 * @return Returns the oldCompName.
	 */
	public String getOldCompName() {
		return oldCompName;
	}
	
	/**
	 * @param oldCompName
	 *            The oldCompName to set.
	 */
	public void setOldCompName(String oldCompName) {
		this.oldCompName = oldCompName;
	}
	
	/**
	 * @return Returns the oldCompSeqNo.
	 */
	public int getOldCompSeqNo() {
		return oldCompSeqNo;
	}
	
	/**
	 * @param oldCompSeqNo
	 *            The oldCompSeqNo to set.
	 */
	public void setOldCompSeqNo(int oldCompSeqNo) {
		this.oldCompSeqNo = oldCompSeqNo;
	}
	
	/**
	 * @return Returns the changeTypeDesc.
	 */
	public String getChangeTypeDesc() {
		return changeTypeDesc;
	}
	
	/**
	 * @param changeTypeDesc
	 *            The changeTypeDesc to set.
	 */
	public void setChangeTypeDesc(String changeTypeDesc) {
		this.changeTypeDesc = changeTypeDesc;
	}
	
	/**
	 * @return Returns the compDefColorFlag.
	 */
	public String getCompDefColorFlag() {
		return compDefColorFlag;
	}
	
	/**
	 * @param compDefColorFlag
	 *            The compDefColorFlag to set.
	 */
	public void setCompDefColorFlag(String compDefColorFlag) {
		this.compDefColorFlag = compDefColorFlag;
	}
	
	/**
	 * @return Returns the compNameColorFlag.
	 */
	public String getCompNameColorFlag() {
		return compNameColorFlag;
	}
	
	/**
	 * @param compNameColorFlag
	 *            The compNameColorFlag to set.
	 */
	public void setCompNameColorFlag(String compNameColorFlag) {
		this.compNameColorFlag = compNameColorFlag;
	}
}