/* Er91220
 * Created on March 08, 2012
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.form.SpecComparison;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

public class OndemandSpecSupplementForm extends EMDForm {
	
	ArrayList custList;
	
	ArrayList specTypeList;
	
	
	
	ArrayList modelList;
	
	private String orderNo;
	
	private int spectypeSeqno = 0;
	
	private int custSeqNo = 0;
	
	private String custName;
	
	private String specType;

	private String custMdlName;
	//Change for LSDb_CR-108
	private String sortByFlag;
	
	private String columnheader;
	
	private String hdnorderNo;
	
	private String hdnSelSpecType;
	
	private int[] modelSeqNos;
	
	private int[] modelSelected;
	
	private String hdnSelectedMdlNames;

	public int[] getModelSelected() {
		return modelSelected;
	}

	public void setModelSelected(int[] modelSelected) {
		this.modelSelected = modelSelected;
	}

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

	public String getColumnheader() {
		return columnheader;
	}

	public void setColumnheader(String columnheader) {
		this.columnheader = columnheader;
	}

	public String getSortByFlag() {
		return sortByFlag;
	}

	public void setSortByFlag(String sortByFlag) {
		this.sortByFlag = sortByFlag;
	}

	public String getHdnorderNo() {
		return hdnorderNo;
	}

	public void setHdnorderNo(String hdnorderNo) {
		this.hdnorderNo = hdnorderNo;
	}

	public String getHdnSelSpecType() {
		return hdnSelSpecType;
	}

	public void setHdnSelSpecType(String hdnSelSpecType) {
		this.hdnSelSpecType = hdnSelSpecType;
	}

	public int[] getModelSeqNos() {
		return modelSeqNos;
	}

	public void setModelSeqNos(int[] modelSeqNos) {
		this.modelSeqNos = modelSeqNos;
	}

	public String getHdnSelectedMdlNames() {
		return hdnSelectedMdlNames;
	}

	public void setHdnSelectedMdlNames(String hdnSelectedMdlNames) {
		this.hdnSelectedMdlNames = hdnSelectedMdlNames;
	}
}
