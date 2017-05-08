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
public class RequestCompGrpVO extends EMDVO {
	
	private int changeTypeSeqNo;
	
	private String changeTypeDesc = "";
	
	private int oldCompGrpSeqNo;
	
	private String oldCompGrpName = "";
	
	private String oldCompGrpValidFlag = "";
	
	private String oldCompGrpChacFlag = "";
	
	private String newCompGrpName = "";
	
	private String newCompGrpValidFlag = "";
	
	private String newCompGrpChacFlag = "";
	
	private RequestCompVO requestCompVO;
	
	private String compGrpNameColorFlag = "";
	
	private String compGrpCharColorFlag = "";
	
	private String compGrpValdColorFlag = "";
	
	/**
	 * @return Returns the requestCompVO.
	 */
	public RequestCompVO getRequestCompVO() {
		return requestCompVO;
	}
	
	/**
	 * @param requestCompVO
	 *            The requestCompVO to set.
	 */
	public void setRequestCompVO(RequestCompVO requestCompVO) {
		this.requestCompVO = requestCompVO;
	}
	
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
	 * @return Returns the newCompGrpChacFlag.
	 */
	public String getNewCompGrpChacFlag() {
		return newCompGrpChacFlag;
	}
	
	/**
	 * @param newCompGrpChacFlag
	 *            The newCompGrpChacFlag to set.
	 */
	public void setNewCompGrpChacFlag(String newCompGrpChacFlag) {
		this.newCompGrpChacFlag = newCompGrpChacFlag;
	}
	
	/**
	 * @return Returns the newCompGrpName.
	 */
	public String getNewCompGrpName() {
		return newCompGrpName;
	}
	
	/**
	 * @param newCompGrpName
	 *            The newCompGrpName to set.
	 */
	public void setNewCompGrpName(String newCompGrpName) {
		this.newCompGrpName = newCompGrpName;
	}
	
	/**
	 * @return Returns the newCompGrpValidFlag.
	 */
	public String getNewCompGrpValidFlag() {
		return newCompGrpValidFlag;
	}
	
	/**
	 * @param newCompGrpValidFlag
	 *            The newCompGrpValidFlag to set.
	 */
	public void setNewCompGrpValidFlag(String newCompGrpValidFlag) {
		this.newCompGrpValidFlag = newCompGrpValidFlag;
	}
	
	/**
	 * @return Returns the oldCompGrpChacFlag.
	 */
	public String getOldCompGrpChacFlag() {
		return oldCompGrpChacFlag;
	}
	
	/**
	 * @param oldCompGrpChacFlag
	 *            The oldCompGrpChacFlag to set.
	 */
	public void setOldCompGrpChacFlag(String oldCompGrpChacFlag) {
		this.oldCompGrpChacFlag = oldCompGrpChacFlag;
	}
	
	/**
	 * @return Returns the oldCompGrpName.
	 */
	public String getOldCompGrpName() {
		return oldCompGrpName;
	}
	
	/**
	 * @param oldCompGrpName
	 *            The oldCompGrpName to set.
	 */
	public void setOldCompGrpName(String oldCompGrpName) {
		this.oldCompGrpName = oldCompGrpName;
	}
	
	/**
	 * @return Returns the oldCompGrpSeqNo.
	 */
	public int getOldCompGrpSeqNo() {
		return oldCompGrpSeqNo;
	}
	
	/**
	 * @param oldCompGrpSeqNo
	 *            The oldCompGrpSeqNo to set.
	 */
	public void setOldCompGrpSeqNo(int oldCompGrpSeqNo) {
		this.oldCompGrpSeqNo = oldCompGrpSeqNo;
	}
	
	/**
	 * @return Returns the oldCompGrpValidFlag.
	 */
	public String getOldCompGrpValidFlag() {
		return oldCompGrpValidFlag;
	}
	
	/**
	 * @param oldCompGrpValidFlag
	 *            The oldCompGrpValidFlag to set.
	 */
	public void setOldCompGrpValidFlag(String oldCompGrpValidFlag) {
		this.oldCompGrpValidFlag = oldCompGrpValidFlag;
	}
	
	/**
	 * @return Returns the changeTypeDesc.
	 */
	public String getChangeTypeDesc() {
		return changeTypeDesc;
	}
	
	/**
	 * @return Returns the compGrpNameColorFlag.
	 */
	public String getCompGrpNameColorFlag() {
		return compGrpNameColorFlag;
	}
	
	/**
	 * @param compGrpNameColorFlag
	 *            The compGrpNameColorFlag to set.
	 */
	public void setCompGrpNameColorFlag(String compGrpNameColorFlag) {
		this.compGrpNameColorFlag = compGrpNameColorFlag;
	}
	
	/**
	 * @return Returns the compGrpCharColorFlag.
	 */
	public String getCompGrpCharColorFlag() {
		return compGrpCharColorFlag;
	}
	
	/**
	 * @param compGrpCharColorFlag
	 *            The compGrpCharColorFlag to set.
	 */
	public void setCompGrpCharColorFlag(String compGrpCharColorFlag) {
		this.compGrpCharColorFlag = compGrpCharColorFlag;
	}
	
	/**
	 * @return Returns the compGrpValdColorFlag.
	 */
	public String getCompGrpValdColorFlag() {
		return compGrpValdColorFlag;
	}
	
	/**
	 * @param compGrpValdColorFlag
	 *            The compGrpValdColorFlag to set.
	 */
	public void setCompGrpValdColorFlag(String compGrpValdColorFlag) {
		this.compGrpValdColorFlag = compGrpValdColorFlag;
	}
	
	/**
	 * @param changeTypeDesc
	 *            The changeTypeDesc to set.
	 */
	public void setChangeTypeDesc(String changeTypeDesc) {
		this.changeTypeDesc = changeTypeDesc;
	}
	
}