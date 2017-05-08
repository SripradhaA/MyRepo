/**
 * 
 */
package com.EMD.LSDB.form.SpecMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

/**
 * @author ps57222
 * 
 */
public class OrderSectionForm extends EMDForm {
	
	private String orderSecName;
	
	private String orderSecCode;
	
	private ArrayList secDetail;
	
	//Added for LSDB_CR-74
	
	private ArrayList clauseDetail;
	
	private int orderComponentSeqNo;
	
	private String partOf;
	
	private int orderSecSeqNo;
	
	private int revCode;// This is for whether all rev,no rev or latest rev
	
	//Change for LSDB_CR-74 for retaining necessary parameters
	private int subSecSeqNo;
	
	private int clauseSeqNo;
	
	private int clauseVersionNo;
	
	private String reason;
	
	private ArrayList specStatus;
	
	private int specStatusCode;
	
	private String revFlag;
	
	private String finalFlag;
	
	private String validFlag;
	
	public String[] componentGroupSeqNo;
	
	public String[] compSeqNo;
	
	private int customerSeqNo;
	//added for CR_81 by sd41630 on 08/01/2010
	private ArrayList charGrpCombntnList;
	
	/** Included For adding CustomerSeqno when adding clauses form order level ** */
	// Added for CR-10 display of Reason based on Spec Status Code
	private int currentSpecStatus;
	
	//Added for LSDB_CR_71 - Server Side Component Validation
	private String compErrorMessage;

	//Added For CR_84
	private String perfCurveLinkFlag;
	
	//Added For CR_93
	private String newOrderNo;
	
	//Added for CR_97
	private int newReqID;
	//Added For CR_99
	private String salesVerDescription;
	
	
	//Added For CR_104
	
	private String bodyCont;
	private String subject;
	private String custMdlName;
	
	//Added for CR_109
	private String markClaReason;
	//Added for CR_109 Ends here
	
	//Added for CR_111
	public String rowIndexList;	
	
	//Added for CR_113 for QA-Fix
	private String redirectGenInfoPageFlag;
	
	//Added for CR-127
	private ArrayList childClaList;
	
	public String getCompErrorMessage() {
		return compErrorMessage;
	}

	public void setCompErrorMessage(String compErrorMessage) {
		this.compErrorMessage = compErrorMessage;
	}

	/**
	 * @return Returns the currentSpecStatus.
	 */
	public int getCurrentSpecStatus() {
		return currentSpecStatus;
	}
	
	/**
	 * @param currentSpecStatus
	 *            The currentSpecStatus to set.
	 */
	public void setCurrentSpecStatus(int currentSpecStatus) {
		this.currentSpecStatus = currentSpecStatus;
	}
	
	private ArrayList revisionList;
	
	/**
	 * @return Returns the revisionList.
	 */
	public ArrayList getRevisionList() {
		return revisionList;
	}
	
	/**
	 * @param revisionList
	 *            The revisionList to set.
	 */
	public void setRevisionList(ArrayList revisionList) {
		this.revisionList = revisionList;
	}
	
	/**
	 * @return Returns the partOf.
	 */
	public String getPartOf() {
		return partOf;
	}
	
	/**
	 * @param partOf
	 *            The partOf to set.
	 */
	public void setPartOf(String partOf) {
		this.partOf = partOf;
	}
	
	/**
	 * @return Returns the orderComponentSeqNo.
	 */
	public int getOrderComponentSeqNo() {
		return orderComponentSeqNo;
	}
	
	/**
	 * @param orderComponentSeqNo
	 *            The orderComponentSeqNo to set.
	 */
	public void setOrderComponentSeqNo(int orderComponentSeqNo) {
		this.orderComponentSeqNo = orderComponentSeqNo;
	}
	
	/**
	 * @return Returns the secDetail.
	 */
	public ArrayList getSecDetail() {
		return secDetail;
	}
	
	/**
	 * @param secDetail
	 *            The secDetail to set.
	 */
	public void setSecDetail(ArrayList secDetail) {
		this.secDetail = secDetail;
	}
	
	/**
	 * @return Returns the orderSecCode.
	 */
	public String getOrderSecCode() {
		return orderSecCode;
	}
	
	/**
	 * @param orderSecCode
	 *            The orderSecCode to set.
	 */
	public void setOrderSecCode(String orderSecCode) {
		this.orderSecCode = orderSecCode;
	}
	
	/**
	 * @return Returns the orderSecName.
	 */
	public String getOrderSecName() {
		return orderSecName;
	}
	
	/**
	 * @param orderSecName
	 *            The orderSecName to set.
	 */
	public void setOrderSecName(String orderSecName) {
		this.orderSecName = orderSecName;
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
	 * @return Returns the orderSecSeqNo.
	 */
	public int getOrderSecSeqNo() {
		return orderSecSeqNo;
	}
	
	/**
	 * @param orderSecSeqNo
	 *            The orderSecSeqNo to set.
	 */
	public void setOrderSecSeqNo(int orderSecSeqNo) {
		this.orderSecSeqNo = orderSecSeqNo;
	}
	
	/**
	 * @return Returns the componentGroupSeqNo.
	 */
	public String[] getComponentGroupSeqNo() {
		return componentGroupSeqNo;
	}
	
	/**
	 * @param componentGroupSeqNo
	 *            The componentGroupSeqNo to set.
	 */
	public void setComponentGroupSeqNo(String[] componentGroupSeqNo) {
		this.componentGroupSeqNo = componentGroupSeqNo;
	}
	
	/**
	 * @return Returns the compSeqNo.
	 */
	public String[] getCompSeqNo() {
		return compSeqNo;
	}
	
	/**
	 * @param compSeqNo
	 *            The compSeqNo to set.
	 */
	public void setCompSeqNo(String[] compSeqNo) {
		this.compSeqNo = compSeqNo;
	}
	
	/**
	 * @return Returns the specStatus.
	 */
	public ArrayList getSpecStatus() {
		return specStatus;
	}
	
	/**
	 * @param specStatus
	 *            The specStatus to set.
	 */
	public void setSpecStatus(ArrayList specStatus) {
		this.specStatus = specStatus;
	}
	
	/**
	 * @return Returns the specStatusCode.
	 */
	public int getSpecStatusCode() {
		return specStatusCode;
	}
	
	/**
	 * @param specStatusCode
	 *            The specStatusCode to set.
	 */
	public void setSpecStatusCode(int specStatusCode) {
		this.specStatusCode = specStatusCode;
	}
	
	/**
	 * @return Returns the finalFlag.
	 */
	public String getFinalFlag() {
		return finalFlag;
	}
	
	/**
	 * @param finalFlag
	 *            The finalFlag to set.
	 */
	public void setFinalFlag(String finalFlag) {
		this.finalFlag = finalFlag;
	}
	
	/**
	 * @return Returns the revFlag.
	 */
	public String getRevFlag() {
		return revFlag;
	}
	
	/**
	 * @param revFlag
	 *            The revFlag to set.
	 */
	public void setRevFlag(String revFlag) {
		this.revFlag = revFlag;
	}
	
	public String getValidFlag() {
		return validFlag;
	}
	
	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}
	
	/**
	 * @return Returns the customerSeqNo.
	 */
	public int getCustomerSeqNo() {
		return customerSeqNo;
	}
	
	/**
	 * @param customerSeqNo
	 *            The customerSeqNo to set.
	 */
	public void setCustomerSeqNo(int customerSeqNo) {
		this.customerSeqNo = customerSeqNo;
	}

	/**
	 * @return Returns the subSecSeqnNo.
	 */
	public int getSubSecSeqNo() {
		return subSecSeqNo;
	}

	/**
	 * @param subSecSeqnNo The subSecSeqnNo to set.
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
	 * @param clauseSeqNo The clauseSeqNo to set.
	 */
	public void setClauseSeqNo(int clauseSeqNo) {
		this.clauseSeqNo = clauseSeqNo;
	}

	/**
	 * @return Returns the clauseVersionNo.
	 */
	public int getClauseVersionNo() {
		return clauseVersionNo;
	}

	/**
	 * @param clauseVersionNo The clauseVersionNo to set.
	 */
	public void setClauseVersionNo(int clauseVersionNo) {
		this.clauseVersionNo = clauseVersionNo;
	}

	/**
	 * @return Returns the clauseDetail.
	 */
	public ArrayList getClauseDetail() {
		return clauseDetail;
	}

	/**
	 * @param clauseDetail The clauseDetail to set.
	 */
	public void setClauseDetail(ArrayList clauseDetail) {
		this.clauseDetail = clauseDetail;
	}

	/**
	 * @return Returns the reason.
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason The reason to set.
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return Returns the charGrpCombntnList.
	 */
	public ArrayList getCharGrpCombntnList() {
		return charGrpCombntnList;
	}

	/**
	 * @param charGrpCombntnList The charGrpCombntnList to set.
	 */
	public void setCharGrpCombntnList(ArrayList charGrpCombntnList) {
		this.charGrpCombntnList = charGrpCombntnList;
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
	//Added For CR_93
	
	public String getNewOrderNo() {
		return newOrderNo;
	}

	public void setNewOrderNo(String newOrderNo) {
		this.newOrderNo = newOrderNo;
	}

	//Added for CR_97
	public int getNewReqID() {
		return newReqID;
	}

	public void setNewReqID(int newReqID) {
		this.newReqID = newReqID;
	}


	public String getSalesVerDescription() {
		return salesVerDescription;
	}

	public void setSalesVerDescription(String salesVerDescription) {
		this.salesVerDescription = salesVerDescription;
	}

	public String getBodyCont() {
		return bodyCont;
	}

	public void setBodyCont(String bodyCont) {
		this.bodyCont = bodyCont;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCustMdlName() {
		return custMdlName;
	}

	public void setCustMdlName(String custMdlName) {
		this.custMdlName = custMdlName;
	}

	public String getMarkClaReason() {
		return markClaReason;
	}

	public void setMarkClaReason(String markClaReason) {
		this.markClaReason = markClaReason;
	}

	/**
	 * @return Returns the rowIndexList.
	 */
	public String getRowIndexList() {
		return rowIndexList;
	}

	/**
	 * @param rowIndexList The rowIndexList to set.
	 */
	public void setRowIndexList(String rowIndexList) {
		this.rowIndexList = rowIndexList;
	}

	public String getRedirectGenInfoPageFlag() {
		return redirectGenInfoPageFlag;
	}

	public void setRedirectGenInfoPageFlag(String redirectGenInfoPageFlag) {
		this.redirectGenInfoPageFlag = redirectGenInfoPageFlag;
	}

	public ArrayList getChildClaList() {
		return childClaList;
	}

	public void setChildClaList(ArrayList childClaList) {
		this.childClaList = childClaList;
	}
	
	
}