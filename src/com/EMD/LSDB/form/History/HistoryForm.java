/**
 * 
 */
package com.EMD.LSDB.form.History;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

/**
 * @author VV49326
 * 
 */
public class HistoryForm extends EMDForm {
	private int specTypeSeqno;
	
	private int specStatusSeqNo;
	
	private String OrderNo;
	
	private ArrayList specTypeList;
	
	private ArrayList orderList;
	
	private String orderNum;
	
	private String statusDesc;
	
	private String customerName;
	
	private String modelName;
	
	private String hdnSpecTypeNme;
	
	private String hdnSpecStatus;
	
	//Added for LSDB_CR-76
	private String sortByFlag;
	
	private String columnheader;
	
	//Added for CR_101 to bring the customer list
	private String customerSeqno;
	
	private int[] customerSeqnos;
	
	private ArrayList customerList = new ArrayList();
	
	private String hdnSelectedCustNames;
	//Added for CR_106 to bring Custom Model Name by JG101007
	private String custMdlName;
	
	//Added for CR_112 - Multi Select Model List Box - Spec history
	private String modelSeqno;
	
	private int[] modelSeqnos;
	
	private ArrayList modelList = new ArrayList();
	
	private String hdnSelectedModelNames;
	
	public String getHdnSelectedModelNames() {
		return hdnSelectedModelNames;
	}

	public void setHdnSelectedModelNames(String hdnSelectedModelNames) {
		this.hdnSelectedModelNames = hdnSelectedModelNames;
	}

	public ArrayList getModelList() {
		return modelList;
	}

	public void setModelList(ArrayList modelList) {
		this.modelList = modelList;
	}

	public String getModelSeqno() {
		return modelSeqno;
	}

	public void setModelSeqno(String modelSeqno) {
		this.modelSeqno = modelSeqno;
	}

	public int[] getModelSeqnos() {
		return modelSeqnos;
	}

	public void setModelSeqnos(int[] modelSeqnos) {
		this.modelSeqnos = modelSeqnos;
	}
	
	//CR_112 Ends here	

	/**
	 * @return Returns the hdnSelectedCustNames.
	 */
	public String getHdnSelectedCustNames() {
		return hdnSelectedCustNames;
	}

	/**
	 * @param hdnSelectedCustNames The hdnSelectedCustNames to set.
	 */
	public void setHdnSelectedCustNames(String hdnSelectedCustNames) {
		this.hdnSelectedCustNames = hdnSelectedCustNames;
	}
	//	CR_101 Ends here.
	/**
	 * @return Returns the customerName.
	 */
	public String getCustomerName() {
		return customerName;
	}
	
	/**
	 * @param customerName
	 *            The customerName to set.
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	/**
	 * @return Returns the modelName.
	 */
	public String getModelName() {
		return modelName;
	}
	
	/**
	 * @param modelName
	 *            The modelName to set.
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
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
	 * @return Returns the orderList.
	 */
	public ArrayList getOrderList() {
		return orderList;
	}
	
	/**
	 * @param orderList
	 *            The orderList to set.
	 */
	public void setOrderList(ArrayList orderList) {
		this.orderList = orderList;
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
	 * @return Returns the orderNo.
	 */
	public String getOrderNo() {
		return OrderNo;
	}
	
	/**
	 * @param orderNo
	 *            The orderNo to set.
	 */
	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}
	
	/**
	 * @return Returns the specTypeSeqno.
	 */
	public int getSpecTypeSeqno() {
		return specTypeSeqno;
	}
	
	/**
	 * @param specTypeSeqno
	 *            The specTypeSeqno to set.
	 */
	public void setSpecTypeSeqno(int specTypeSeqno) {
		this.specTypeSeqno = specTypeSeqno;
	}
	
	/**
	 * @return Returns the specStatusSeqNo.
	 */
	public int getSpecStatusSeqNo() {
		return specStatusSeqNo;
	}
	
	/**
	 * @param specStatusSeqNo
	 *            The specStatusSeqNo to set.
	 */
	public void setSpecStatusSeqNo(int specStatusSeqNo) {
		this.specStatusSeqNo = specStatusSeqNo;
	}
	
	/**
	 * @return Returns the orderNum.
	 */
	public String getOrderNum() {
		return orderNum;
	}
	
	/**
	 * @param orderNum
	 *            The orderNum to set.
	 */
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
	/**
	 * @return Returns the SpecName.
	 */
	public String getHdnSpecTypeNme() {
		return hdnSpecTypeNme;
	}
	
	/**
	 * @param hdnSpecType
	 *            The hdnSpecType to set.
	 */
	public void setHdnSpecTypeNme(String hdnSpecTypeNme) {
		this.hdnSpecTypeNme = hdnSpecTypeNme;
	}
	
	/**
	 * @return Returns the hdnSpecStatus.
	 */
	public String getHdnSpecStatus() {
		return hdnSpecStatus;
	}
	
	/**
	 * @param hdnSpecType
	 *            The hdnSpecStatus to set.
	 */
	public void setHdnSpecStatus(String hdnSpecStatus) {
		this.hdnSpecStatus = hdnSpecStatus;
	}

	/**
	 * @return Returns the sortByFlag.
	 */
	public String getSortByFlag() {
		return sortByFlag;
	}

	/**
	 * @param sortByFlag The sortByFlag to set.
	 */
	public void setSortByFlag(String sortByFlag) {
		this.sortByFlag = sortByFlag;
	}

	/**
	 * @return Returns the columnheader.
	 */
	public String getColumnheader() {
		return columnheader;
	}

	/**
	 * @param columnheader The columnheader to set.
	 */
	public void setColumnheader(String columnheader) {
		this.columnheader = columnheader;
	}

	/**
	 * @return Returns the customerSeqno.
	 */
	public String getCustomerSeqno() {
		return customerSeqno;
	}

	/**
	 * @param customerSeqno The customerSeqno to set.
	 */
	public void setCustomerSeqno(String customerSeqno) {
		this.customerSeqno = customerSeqno;
	}

	/**
	 * @return Returns the customerList.
	 */
	public ArrayList getCustomerList() {
		return customerList;
	}

	/**
	 * @param customerList The customerList to set.
	 */
	public void setCustomerList(ArrayList customerList) {
		this.customerList = customerList;
	}

	/**
	 * @return Returns the customerSeqnos.
	 */
	public int[] getCustomerSeqnos() {
		return customerSeqnos;
	}

	/**
	 * @param customerSeqnos The customerSeqnos to set.
	 */
	public void setCustomerSeqnos(int[] customerSeqnos) {
		this.customerSeqnos = customerSeqnos;
	}
	/**
	 * @return Returns the custMdlName.
	 */
	public String getCustomModelName() {
		return custMdlName;
	}
	
	/**
	 * @param custMdlName
	 *            The Custom Model Name to set.
	 */
	public void setCustomModelName(String custMdlName) {
		this.custMdlName = custMdlName;
	}
	
	
	
	
	
}
