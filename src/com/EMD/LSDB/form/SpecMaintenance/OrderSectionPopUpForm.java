package com.EMD.LSDB.form.SpecMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

public class OrderSectionPopUpForm extends EMDForm {
	
	public ArrayList clauseGroup;
	
	private String subSecCode;
	
	private String subSecName;
	
	private int orderKey;
	
	private int clauseSeqNo;
	
	private int subSecSeqNo;
	
	private int parentClauseSeqNo;
	
	private int versionNo;
	
	private String hdnClauseNum;
	
	private String numClause;
	
	private int secSeqNo;
	
	private int revCode;
	
	private String secName;
	
	private String secCode;
	
	private int parentSecSeqNo;
	
	private int partOfClauseSeqNo;
	
	/**
	 * @return Returns the partOfClauseSeqNo.
	 */
	public int getPartOfClauseSeqNo() {
		return partOfClauseSeqNo;
	}
	
	/**
	 * @param partOfClauseSeqNo The partOfClauseSeqNo to set.
	 */
	public void setPartOfClauseSeqNo(int partOfClauseSeqNo) {
		this.partOfClauseSeqNo = partOfClauseSeqNo;
	}
	
	/**
	 * @return Returns the parentSecSeqNo.
	 */
	public int getParentSecSeqNo() {
		return parentSecSeqNo;
	}
	
	/**
	 * @param parentSecSeqNo The parentSecSeqNo to set.
	 */
	public void setParentSecSeqNo(int parentSecSeqNo) {
		this.parentSecSeqNo = parentSecSeqNo;
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
	 * @return Returns the secCode.
	 */
	public String getSecCode() {
		return secCode;
	}
	
	/**
	 * @param secCode The secCode to set.
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
	 * @param secName The secName to set.
	 */
	public void setSecName(String secName) {
		this.secName = secName;
	}
	
	/**
	 * @return Returns the secSeqNo.
	 */
	public int getSecSeqNo() {
		return secSeqNo;
	}
	
	/**
	 * @param secSeqNo
	 *            The secSeqNo to set.
	 */
	public void setSecSeqNo(int secSeqNo) {
		this.secSeqNo = secSeqNo;
	}
	
	/**
	 * @return Returns the numClause.
	 */
	public String getNumClause() {
		return numClause;
	}
	
	/**
	 * @param numClause
	 *            The numClause to set.
	 */
	public void setNumClause(String numClause) {
		this.numClause = numClause;
	}
	
	/**
	 * @return Returns the clauseNum.
	 */
	public String getHdnClauseNum() {
		return hdnClauseNum;
	}
	
	/**
	 * @param clauseNum
	 *            The clauseNum to set.
	 */
	public void setHdnClauseNum(String clauseNum) {
		this.hdnClauseNum = clauseNum;
	}
	
	/**
	 * @return Returns the versionNo.
	 */
	public int getVersionNo() {
		return versionNo;
	}
	
	/**
	 * @param versionNo
	 *            The versionNo to set.
	 */
	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}
	
	/**
	 * @return Returns the parentClauseSeqNo.
	 */
	public int getParentClauseSeqNo() {
		return parentClauseSeqNo;
	}
	
	/**
	 * @param parentClauseSeqNo
	 *            The parentClauseSeqNo to set.
	 */
	public void setParentClauseSeqNo(int parentClauseSeqNo) {
		this.parentClauseSeqNo = parentClauseSeqNo;
	}
	
	/**
	 * @return Returns the subSecSeqNo.
	 */
	public int getSubSecSeqNo() {
		return subSecSeqNo;
	}
	
	/**
	 * @param subSecSeqNo
	 *            The subSecSeqNo to set.
	 */
	public void setSubSecSeqNo(int subSecSeqNo) {
		this.subSecSeqNo = subSecSeqNo;
	}
	
	/**
	 * @return Returns the clauseSeqNo.
	 */
	public int getClauseSeqNo() {
		return clauseSeqNo;
	}
	
	/**
	 * @param clauseSeqNo
	 *            The clauseSeqNo to set.
	 */
	public void setClauseSeqNo(int clauseSeqNo) {
		this.clauseSeqNo = clauseSeqNo;
	}
	
	/**
	 * @return Returns the orderKey.
	 */
	public int getOrderKey() {
		return orderKey;
	}
	
	/**
	 * @param orderKey
	 *            The orderKey to set.
	 */
	public void setOrderKey(int orderKey) {
		this.orderKey = orderKey;
	}
	
	/**
	 * @return Returns the componentVO.
	 */
	public ArrayList getClauseGroup() {
		return clauseGroup;
	}
	
	/**
	 * @param componentVO
	 *            The componentVO to set.
	 */
	public void setClauseGroup(ArrayList componentVO) {
		this.clauseGroup = componentVO;
	}
	
	/**
	 * @return Returns the subSecCode.
	 */
	public String getSubSecCode() {
		return subSecCode;
	}
	
	/**
	 * @param subSecCode
	 *            The subSecCode to set.
	 */
	public void setSubSecCode(String subSecCode) {
		this.subSecCode = subSecCode;
	}
	
	/**
	 * @return Returns the subSecName.
	 */
	public String getSubSecName() {
		return subSecName;
	}
	
	/**
	 * @param subSecName
	 *            The subSecName to set.
	 */
	public void setSubSecName(String subSecName) {
		this.subSecName = subSecName;
	}
}
