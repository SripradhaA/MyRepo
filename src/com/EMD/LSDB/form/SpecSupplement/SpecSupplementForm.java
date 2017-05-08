/* AK49339
 * Created on Aug 24, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.form.SpecSupplement;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

public class SpecSupplementForm extends EMDForm {
	
	ArrayList custList;
	
	ArrayList specTypeList;
	
	ArrayList modelList;
	
	private String orderNo;
	
	private int spectypeSeqno = 0;
	
	private int custSeqNo = 0;
	
	private String custName;
	
	private String specType;
	//Added FOr CR_104	
	private String custMdlName;
	
	/**
	 * @return Returns the custSeqNo.
	 */
	public int getCustSeqNo() {
		return custSeqNo;
	}
	
	/**
	 * @param custSeqNo
	 *            The custSeqNo to set.
	 */
	public void setCustSeqNo(int custSeqNo) {
		this.custSeqNo = custSeqNo;
	}
	
	/**
	 * @return Returns the spectypeSeqno.
	 */
	public int getSpectypeSeqno() {
		return spectypeSeqno;
	}
	
	/**
	 * @param spectypeSeqno
	 *            The spectypeSeqno to set.
	 */
	public void setSpectypeSeqno(int spectypeSeqno) {
		this.spectypeSeqno = spectypeSeqno;
	}
	
	/**
	 * @return Returns the custList.
	 */
	public ArrayList getCustList() {
		return custList;
	}
	
	/**
	 * @param custList
	 *            The custList to set.
	 */
	public void setCustList(ArrayList custList) {
		this.custList = custList;
	}
	
	/**
	 * @return Returns the orderNo.
	 */
	public String getOrderNo() {
		return orderNo;
	}
	
	/**
	 * @param orderNo
	 *            The orderNo to set.
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	/**
	 * @return Returns the specTypeList.
	 */
	public ArrayList getSpecTypeList() {
		return specTypeList;
	}
	
	/**
	 * @param specTypeList
	 *            The specTypeList to set.
	 */
	public void setSpecTypeList(ArrayList specTypeList) {
		this.specTypeList = specTypeList;
	}
	
	/**
	 * @return Returns the modelList.
	 */
	public ArrayList getModelList() {
		return modelList;
	}
	
	/**
	 * @param modelList
	 *            The modelList to set.
	 */
	public void setModelList(ArrayList modelList) {
		this.modelList = modelList;
	}
	
	/**
	 * @return Returns the custName.
	 */
	public String getCustName() {
		return custName;
	}
	
	/**
	 * @param custName
	 *            The custName to set.
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}
	
	/**
	 * @return Returns the specType.
	 */
	public String getSpecType() {
		return specType;
	}
	
	/**
	 * @param specType
	 *            The specType to set.
	 */
	public void setSpecType(String specType) {
		this.specType = specType;
	}

	public String getCustMdlName() {
		return custMdlName;
	}

	public void setCustMdlName(String custMdlName) {
		this.custMdlName = custMdlName;
	}
}
