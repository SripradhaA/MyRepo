package com.EMD.LSDB.form.CRForm;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

public class ModifyChangeRequestForm extends EMDForm {
	
	private String lastName;
	
	private String fromDate;
	
	private String toDate;
	
	private String requestId;
	
	private int statusSeqNo;
	
	private ArrayList statusList;
	
	private int hdnstatusseqno;
	
	private ArrayList requestList;
	
	private String[] reqID;
	
	private String[] id;
	
	private String hdStatus;
	
	private String hdnLastName;
	
	private int hdnRequestId;
	
	private String hdnFromDate;
	
	private String hdnToDate;
	
	private String hdnsearch;
	
	private String hdnID;
	
	private ArrayList lastList;
	
	//Added For CR_80 by RR68151
	private String assigneeId;
	
	private String hdnAssigneeId;
	
	/**
	 * @return Returns the lastList.
	 */
	public ArrayList getLastList() {
		return lastList;
	}
	
	/**
	 * @param lastList The lastList to set.
	 */
	public void setLastList(ArrayList lastList) {
		this.lastList = lastList;
	}
	
	/**
	 * @return Returns the id.
	 */
	public String[] getId() {
		return id;
	}
	
	/**
	 * @param id The id to set.
	 */
	public void setId(String[] id) {
		this.id = id;
	}
	
	/**
	 * @return Returns the requestList.
	 */
	public ArrayList getRequestList() {
		return requestList;
	}
	
	/**
	 * @param requestList The requestList to set.
	 */
	public void setRequestList(ArrayList requestList) {
		this.requestList = requestList;
	}
	
	/**
	 * @return Returns the fromDate.
	 */
	public String getFromDate() {
		return fromDate;
	}
	
	/**
	 * @param fromDate The fromDate to set.
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	
	/**
	 * @return Returns the hdnstatusseqno.
	 */
	public int getHdnstatusseqno() {
		return hdnstatusseqno;
	}
	
	/**
	 * @param hdnstatusseqno The hdnstatusseqno to set.
	 */
	public void setHdnstatusseqno(int hdnstatusseqno) {
		this.hdnstatusseqno = hdnstatusseqno;
	}
	
	/**
	 * @return Returns the lastName.
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * @param lastName The lastName to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @return Returns the statusList.
	 */
	public ArrayList getStatusList() {
		return statusList;
	}
	
	/**
	 * @param statusList The statusList to set.
	 */
	public void setStatusList(ArrayList statusList) {
		this.statusList = statusList;
	}
	
	/**
	 * @return Returns the statusSeqNo.
	 */
	public int getStatusSeqNo() {
		return statusSeqNo;
	}
	
	/**
	 * @param statusSeqNo The statusSeqNo to set.
	 */
	public void setStatusSeqNo(int statusSeqNo) {
		this.statusSeqNo = statusSeqNo;
	}
	
	/**
	 * @return Returns the toDate.
	 */
	public String getToDate() {
		return toDate;
	}
	
	/**
	 * @param toDate The toDate to set.
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	/**
	 * @return Returns the reqID.
	 */
	public String[] getReqID() {
		return reqID;
	}
	
	/**
	 * @param reqID The reqID to set.
	 */
	public void setReqID(String[] reqID) {
		this.reqID = reqID;
	}
	
	/**
	 * @return Returns the requestId.
	 */
	public String getRequestId() {
		return requestId;
	}
	
	/**
	 * @param requestId The requestId to set.
	 */
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	
	/**
	 * @return Returns the hdnFromDate.
	 */
	public String getHdnFromDate() {
		return hdnFromDate;
	}
	
	/**
	 * @param hdnFromDate The hdnFromDate to set.
	 */
	public void setHdnFromDate(String hdnFromDate) {
		this.hdnFromDate = hdnFromDate;
	}
	
	/**
	 * @return Returns the hdnLastName.
	 */
	public String getHdnLastName() {
		return hdnLastName;
	}
	
	/**
	 * @param hdnLastName The hdnLastName to set.
	 */
	public void setHdnLastName(String hdnLastName) {
		this.hdnLastName = hdnLastName;
	}
	
	/**
	 * @return Returns the hdnRequestId.
	 */
	public int getHdnRequestId() {
		return hdnRequestId;
	}
	
	/**
	 * @param hdnRequestId The hdnRequestId to set.
	 */
	public void setHdnRequestId(int hdnRequestId) {
		this.hdnRequestId = hdnRequestId;
	}
	
	/**
	 * @return Returns the hdnsearch.
	 */
	public String getHdnsearch() {
		return hdnsearch;
	}
	
	/**
	 * @param hdnsearch The hdnsearch to set.
	 */
	public void setHdnsearch(String hdnsearch) {
		this.hdnsearch = hdnsearch;
	}
	
	/**
	 * @return Returns the hdnToDate.
	 */
	public String getHdnToDate() {
		return hdnToDate;
	}
	
	/**
	 * @param hdnToDate The hdnToDate to set.
	 */
	public void setHdnToDate(String hdnToDate) {
		this.hdnToDate = hdnToDate;
	}
	
	/**
	 * @return Returns the hdStatus.
	 */
	public String getHdStatus() {
		return hdStatus;
	}
	
	/**
	 * @param hdStatus The hdStatus to set.
	 */
	public void setHdStatus(String hdStatus) {
		this.hdStatus = hdStatus;
	}
	
	/**
	 * @return Returns the hdnID.
	 */
	public String getHdnID() {
		return hdnID;
	}
	
	/**
	 * @param hdnID The hdnID to set.
	 */
	public void setHdnID(String hdnID) {
		this.hdnID = hdnID;
	}

	/**
	 * @return Returns the assigneeId.
	 */
	public String getAssigneeId() {
		return assigneeId;
	}

	/**
	 * @param assigneeId The assigneeId to set.
	 */
	public void setAssigneeId(String assigneeId) {
		this.assigneeId = assigneeId;
	}

	/**
	 * @return Returns the hdnAssigneeId.
	 */
	public String getHdnAssigneeId() {
		return hdnAssigneeId;
	}

	/**
	 * @param hdnAssigneeId The hdnAssigneeId to set.
	 */
	public void setHdnAssigneeId(String hdnAssigneeId) {
		this.hdnAssigneeId = hdnAssigneeId;
	}
	
}
