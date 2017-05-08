/*
 * Created on Jun 5, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.form.CRForm;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;
import com.EMD.LSDB.vo.common.RequestModelVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the form fields for the CR Form
 ******************************************************************************/

public class PreviewRequestForm extends EMDForm {
	
	private ArrayList requestList;
	
	private String reqID;
	
	private RequestModelVO requestModelVO;
	
	private RequestModelVO requestCompGroupVO;
	
	private RequestModelVO requestClauseVO;
	
	private ArrayList requestFrmClauseVO;
	
	//Added for CR_80 CR Form Enhancements III on 25-Nov-09 by RR68151
	private ArrayList effClauseList;
	
	public ArrayList getRequestFrmClauseVO() {
		return requestFrmClauseVO;
	}
	
	public void setRequestFrmClauseVO(ArrayList requestFrmClauseVO) {
		this.requestFrmClauseVO = requestFrmClauseVO;
	}
	
	public RequestModelVO getRequestClauseVO() {
		return requestClauseVO;
	}
	
	public void setRequestClauseVO(RequestModelVO requestClauseVO) {
		this.requestClauseVO = requestClauseVO;
	}
	
	public RequestModelVO getRequestCompGroupVO() {
		return requestCompGroupVO;
	}
	
	public void setRequestCompGroupVO(RequestModelVO requestCompGroupVO) {
		this.requestCompGroupVO = requestCompGroupVO;
	}
	
	public RequestModelVO getRequestModelVO() {
		return requestModelVO;
	}
	
	public void setRequestModelVO(RequestModelVO requestModelVO) {
		this.requestModelVO = requestModelVO;
	}
	
	public ArrayList getRequestList() {
		return requestList;
	}
	
	public void setRequestList(ArrayList requestList) {
		this.requestList = requestList;
	}
	
	public String getReqID() {
		return reqID;
	}
	
	public void setReqID(String reqID) {
		this.reqID = reqID;
	}

	/**
	 * @return Returns the effClauseList.
	 */
	public ArrayList getEffClauseList() {
		return effClauseList;
	}

	/**
	 * @param effClauseList The effClauseList to set.
	 */
	public void setEffClauseList(ArrayList effClauseList) {
		this.effClauseList = effClauseList;
	}
	
}