/**
 * 
 */
package com.EMD.LSDB.vo.common;


/**
 * @author rs37205
 * 
 */
public class RequestVO extends EMDVO {
	
	private int statusTypeSeqNo;
	
	private String reqUserID;
	
	private String reqUserDate;
	
	private String masterSpecChangedID;
	
	private String masterSpecChangedDate;
	
	private RequestModelVO requestModelVO;
	
	private String reqSubFromDate;
	
	private String reqSubToDate;
	
	private String statusTypeDesc;
	
	private String reqByUserFN;
	
	private String reqByUserLN;
	
	private String approveByUserLN;
	
	private String approveByUserFN;
	
	private String reqID;
	
	private String reason;
	
	private String statusDesc;
	
	//Added For CR_80 by RR68151
	private String assigneeId;
	
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
	 * @return Returns the statusDesc.
	 */
	public String getStatusDesc() {
		return statusDesc;
	}
	
	/**
	 * @param statusDesc
	 *            The statusDesc to set.
	 */
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
	/**
	 * @return Returns the reqID.
	 */
	public String getReqID() {
		return reqID;
	}
	
	/**
	 * @param reqID
	 *            The reqID to set.
	 */
	public void setReqID(String reqID) {
		this.reqID = reqID;
	}
	
	/**
	 * @return Returns the statusTypeDesc.
	 */
	public String getStatusTypeDesc() {
		return statusTypeDesc;
	}
	
	/**
	 * @param statusTypeDesc
	 *            The statusTypeDesc to set.
	 */
	public void setStatusTypeDesc(String statusTypeDesc) {
		this.statusTypeDesc = statusTypeDesc;
	}
	
	/**
	 * @return Returns the reqSubFromDate.
	 */
	public String getReqSubFromDate() {
		return reqSubFromDate;
	}
	
	/**
	 * @param reqSubFromDate
	 *            The reqSubFromDate to set.
	 */
	public void setReqSubFromDate(String reqSubFromDate) {
		this.reqSubFromDate = reqSubFromDate;
	}
	
	/**
	 * @return Returns the reqSubToDate.
	 */
	public String getReqSubToDate() {
		return reqSubToDate;
	}
	
	/**
	 * @param reqSubToDate
	 *            The reqSubToDate to set.
	 */
	public void setReqSubToDate(String reqSubToDate) {
		this.reqSubToDate = reqSubToDate;
	}
	
	/**
	 * @return Returns the requestModelVO.
	 */
	public RequestModelVO getRequestModelVO() {
		return requestModelVO;
	}
	
	/**
	 * @param requestModelVO
	 *            The requestModelVO to set.
	 */
	public void setRequestModelVO(RequestModelVO requestModelVO) {
		this.requestModelVO = requestModelVO;
	}
	
	/**
	 * @return Returns the masterSpecChangedDate.
	 */
	public String getMasterSpecChangedDate() {
		return masterSpecChangedDate;
	}
	
	/**
	 * @param masterSpecChangedDate
	 *            The masterSpecChangedDate to set.
	 */
	public void setMasterSpecChangedDate(String masterSpecChangedDate) {
		this.masterSpecChangedDate = masterSpecChangedDate;
	}
	
	/**
	 * @return Returns the masterSpecChangedID.
	 */
	public String getMasterSpecChangedID() {
		return masterSpecChangedID;
	}
	
	/**
	 * @param masterSpecChangedID
	 *            The masterSpecChangedID to set.
	 */
	public void setMasterSpecChangedID(String masterSpecChangedID) {
		this.masterSpecChangedID = masterSpecChangedID;
	}
	
	/**
	 * @return Returns the reqUserDate.
	 */
	public String getReqUserDate() {
		return reqUserDate;
	}
	
	/**
	 * @param reqUserDate
	 *            The reqUserDate to set.
	 */
	public void setReqUserDate(String reqUserDate) {
		this.reqUserDate = reqUserDate;
	}
	
	/**
	 * @return Returns the reqUserID.
	 */
	public String getReqUserID() {
		return reqUserID;
	}
	
	/**
	 * @param reqUserID
	 *            The reqUserID to set.
	 */
	public void setReqUserID(String reqUserID) {
		this.reqUserID = reqUserID;
	}
	
	/**
	 * @return Returns the statusTypeSeqNo.
	 */
	public int getStatusTypeSeqNo() {
		return statusTypeSeqNo;
	}
	
	/**
	 * @param statusTypeSeqNo
	 *            The statusTypeSeqNo to set.
	 */
	public void setStatusTypeSeqNo(int statusTypeSeqNo) {
		this.statusTypeSeqNo = statusTypeSeqNo;
	}
	
	/**
	 * @return Returns the approveByUserFN.
	 */
	public String getApproveByUserFN() {
		return approveByUserFN;
	}
	
	/**
	 * @param approveByUserFN
	 *            The approveByUserFN to set.
	 */
	public void setApproveByUserFN(String approveByUserFN) {
		this.approveByUserFN = approveByUserFN;
	}
	
	/**
	 * @return Returns the approveByUserLN.
	 */
	public String getApproveByUserLN() {
		return approveByUserLN;
	}
	
	/**
	 * @param approveByUserLN
	 *            The approveByUserLN to set.
	 */
	public void setApproveByUserLN(String approveByUserLN) {
		this.approveByUserLN = approveByUserLN;
	}
	
	/**
	 * @return Returns the reqByUserFN.
	 */
	public String getReqByUserFN() {
		return reqByUserFN;
	}
	
	/**
	 * @param reqByUserFN
	 *            The reqByUserFN to set.
	 */
	public void setReqByUserFN(String reqByUserFN) {
		this.reqByUserFN = reqByUserFN;
	}
	
	/**
	 * @return Returns the reqByUserLN.
	 */
	public String getReqByUserLN() {
		return reqByUserLN;
	}
	
	/**
	 * @param reqByUserLN
	 *            The reqByUserLN to set.
	 */
	public void setReqByUserLN(String reqByUserLN) {
		this.reqByUserLN = reqByUserLN;
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
}