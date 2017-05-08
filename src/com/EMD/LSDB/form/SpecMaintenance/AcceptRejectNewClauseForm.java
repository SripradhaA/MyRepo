package com.EMD.LSDB.form.SpecMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

public class AcceptRejectNewClauseForm extends EMDForm {
	// private int ordrKey=0;
	private int clauseSeqNo;
	
	private String flag;
	
	private ArrayList newClauseList = null;
	
	private String reason = null;
	
	private int secSeq;
	
	private String secName;
	
	private String secCode;
	
	private int revCode;
	
	private int subSecSeqNo;
	
	private String noCompFlag="N";
	
	private String claReasonArray;
	
	private String claSeqArray;
	
	/**
	 * Reason TextArea in AcceptrejectNewClause screen is visible only for the
	 * spec status opening and above, to check this specStatusCode attribute is
	 * added Added on 05-June-08 by ps57222
	 * 
	 */
	
	private String specStatusCode;
	
	/**
	 * @return Returns the specStatusCode.
	 */
	public String getSpecStatusCode() {
		return specStatusCode;
	}
	
	/**
	 * @param specStatusCode
	 *            The specStatusCode to set.
	 */
	public void setSpecStatusCode(String specStatusCode) {
		this.specStatusCode = specStatusCode;
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
	 * @return Returns the newClauseList.
	 */
	public ArrayList getNewClauseList() {
		return newClauseList;
	}
	
	/**
	 * @param newClauseList
	 *            The newClauseList to set.
	 */
	public void setNewClauseList(ArrayList newClauseList) {
		this.newClauseList = newClauseList;
	}
	
	/**
	 * @return Returns the reason.
	 */
	public String getReason() {
		return reason;
	}
	
	/**
	 * @param reason
	 *            The reason to set.
	 */
	public void setReason(String reason) {
		this.reason = reason;
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
	 * @return Returns the flag.
	 */
	public String getFlag() {
		return flag;
	}
	
	/**
	 * @param flag
	 *            The flag to set.
	 */
	public void setFlag(String flag) {
		this.flag = flag;
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
	 * @return Returns the secSeq.
	 */
	public int getSecSeq() {
		return secSeq;
	}
	
	/**
	 * @param secSeq
	 *            The secSeq to set.
	 */
	public void setSecSeq(int secSeq) {
		this.secSeq = secSeq;
	}

	public String getNoCompFlag() {
		return noCompFlag;
	}

	public void setNoCompFlag(String noCompFlag) {
		this.noCompFlag = noCompFlag;
	}

	/**
	 * @return Returns the claReasonArray.
	 */
	public String getClaReasonArray() {
		return claReasonArray;
	}

	/**
	 * @param claReasonArray The claReasonArray to set.
	 */
	public void setClaReasonArray(String claReasonArray) {
		this.claReasonArray = claReasonArray;
	}

	/**
	 * @return Returns the claSeqArray.
	 */
	public String getClaSeqArray() {
		return claSeqArray;
	}

	/**
	 * @param claSeqArray The claSeqArray to set.
	 */
	public void setClaSeqArray(String claSeqArray) {
		this.claSeqArray = claSeqArray;
	}


}