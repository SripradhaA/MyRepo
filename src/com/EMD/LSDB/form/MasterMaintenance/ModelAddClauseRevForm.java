/**
 * 
 */
package com.EMD.LSDB.form.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;
import com.EMD.LSDB.vo.common.ClauseVO;

/**
 * @author ps57222
 * 
 */
public class ModelAddClauseRevForm extends EMDForm {
	
	private ArrayList modelList;
	
	private ArrayList stdEquipmentList;
	
	private int modelSeqNo;
	
	private String modelName;
	
	private int clauseSeqNo;
	
	private int subSectionSeqNo;
	
	private int versionNo;
	
	private ClauseVO clauseVo;
	
	private int standardEquipmentSeqNo;
	
	private String standardEquipmentDesc;
	
	private int clauseNo;
	
	private int parentClauseSeqNo;
	
	private String clauseDesc;
	
	private String clauseDescForTextArea;
	
	private String[] edlNo;
	
	private String[] RefEDLNo;
	
	private int[] partOfSeqNo;
	
	private String[] partOfCode;
	
	private String revisionNo;
	
	private int dwONumber;
	
	private int partNumber;
	
	private int priceBookNumber;
	
	private String comments;
	
	private boolean defaultFlag;
	
	private String reason;
	
	private String subSectionSeqName;
	
	private ArrayList subSectionList;
	
	private ArrayList subSectionSeqCode;
	
	private String[] clauseTableDataArray1;
	
	private String[] clauseTableDataArray2;
	
	private String[] clauseTableDataArray3;
	
	private String[] clauseTableDataArray4;
	
	private String[] clauseTableDataArray5;
	
	private String deleterow;
	
	private String[] tableArrayData1;
	
	private String selectedClause;
	
	private ArrayList standardEquipmentList;
	
	private char headerFlag;
	
	private int rowIndex;
	
	private int[] componentfrom;
	
	private String componentSeqNo;
	
	private ArrayList clauseList;
	
	private int hdPreSelectedModel;/*
	* This hidden variable is used to check
	* whether the selected model and previous
	* selected models are same
	*/
	
	private String hdSelectedModel; /*
	* This hidden variable is used to display
	* the selected model while search and add
	*/
	
	/**
	 * @return Returns the clauseList.
	 */
	public ArrayList getClauseList() {
		return clauseList;
	}
	
	/**
	 * @param clauseList
	 *            The clauseList to set.
	 */
	public void setClauseList(ArrayList clauseList) {
		this.clauseList = clauseList;
	}
	
	/**
	 * @return Returns the clauseDesc.
	 */
	public String getClauseDesc() {
		return clauseDesc;
	}
	
	/**
	 * @param clauseDesc
	 *            The clauseDesc to set.
	 */
	public void setClauseDesc(String clauseDesc) {
		this.clauseDesc = clauseDesc;
	}
	
	/**
	 * @return Returns the clauseNo.
	 */
	public int getClauseNo() {
		return clauseNo;
	}
	
	/**
	 * @param clauseNo
	 *            The clauseNo to set.
	 */
	public void setClauseNo(int clauseNo) {
		this.clauseNo = clauseNo;
	}
	
	/**
	 * @return Returns the clauseTableDataArray1.
	 */
	public String[] getClauseTableDataArray1() {
		return clauseTableDataArray1;
	}
	
	/**
	 * @param clauseTableDataArray1
	 *            The clauseTableDataArray1 to set.
	 */
	public void setClauseTableDataArray1(String[] clauseTableDataArray1) {
		this.clauseTableDataArray1 = clauseTableDataArray1;
	}
	
	/**
	 * @return Returns the clauseTableDataArray2.
	 */
	public String[] getClauseTableDataArray2() {
		return clauseTableDataArray2;
	}
	
	/**
	 * @param clauseTableDataArray2
	 *            The clauseTableDataArray2 to set.
	 */
	public void setClauseTableDataArray2(String[] clauseTableDataArray2) {
		this.clauseTableDataArray2 = clauseTableDataArray2;
	}
	
	/**
	 * @return Returns the clauseTableDataArray3.
	 */
	public String[] getClauseTableDataArray3() {
		return clauseTableDataArray3;
	}
	
	/**
	 * @param clauseTableDataArray3
	 *            The clauseTableDataArray3 to set.
	 */
	public void setClauseTableDataArray3(String[] clauseTableDataArray3) {
		this.clauseTableDataArray3 = clauseTableDataArray3;
	}
	
	/**
	 * @return Returns the clauseTableDataArray4.
	 */
	public String[] getClauseTableDataArray4() {
		return clauseTableDataArray4;
	}
	
	/**
	 * @param clauseTableDataArray4
	 *            The clauseTableDataArray4 to set.
	 */
	public void setClauseTableDataArray4(String[] clauseTableDataArray4) {
		this.clauseTableDataArray4 = clauseTableDataArray4;
	}
	
	/**
	 * @return Returns the clauseTableDataArray5.
	 */
	public String[] getClauseTableDataArray5() {
		return clauseTableDataArray5;
	}
	
	/**
	 * @param clauseTableDataArray5
	 *            The clauseTableDataArray5 to set.
	 */
	public void setClauseTableDataArray5(String[] clauseTableDataArray5) {
		this.clauseTableDataArray5 = clauseTableDataArray5;
	}
	
	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}
	
	/**
	 * @param comments
	 *            The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	/**
	 * @return Returns the componentfrom.
	 */
	public int[] getComponentfrom() {
		return componentfrom;
	}
	
	/**
	 * @param componentfrom
	 *            The componentfrom to set.
	 */
	public void setComponentfrom(int[] componentfrom) {
		this.componentfrom = componentfrom;
	}
	
	/**
	 * @return Returns the componentSeqNo.
	 */
	public String getComponentSeqNo() {
		return componentSeqNo;
	}
	
	/**
	 * @param componentSeqNo
	 *            The componentSeqNo to set.
	 */
	public void setComponentSeqNo(String componentSeqNo) {
		this.componentSeqNo = componentSeqNo;
	}
	
	/**
	 * @return Returns the defaultFlag.
	 */
	public boolean isDefaultFlag() {
		return defaultFlag;
	}
	
	/**
	 * @param defaultFlag
	 *            The defaultFlag to set.
	 */
	public void setDefaultFlag(boolean defaultFlag) {
		this.defaultFlag = defaultFlag;
	}
	
	/**
	 * @return Returns the dwONumber.
	 */
	public int getDwONumber() {
		return dwONumber;
	}
	
	/**
	 * @param dwONumber
	 *            The dwONumber to set.
	 */
	public void setDwONumber(int dwONumber) {
		this.dwONumber = dwONumber;
	}
	
	/**
	 * @return Returns the edlNo.
	 */
	public String[] getEdlNo() {
		return edlNo;
	}
	
	/**
	 * @param edlNo
	 *            The edlNo to set.
	 */
	public void setEdlNo(String[] edlNo) {
		this.edlNo = edlNo;
	}
	
	/**
	 * @return Returns the headerFlag.
	 */
	public char getHeaderFlag() {
		return headerFlag;
	}
	
	/**
	 * @param headerFlag
	 *            The headerFlag to set.
	 */
	public void setHeaderFlag(char headerFlag) {
		this.headerFlag = headerFlag;
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
	 * @return Returns the partNumber.
	 */
	public int getPartNumber() {
		return partNumber;
	}
	
	/**
	 * @param partNumber
	 *            The partNumber to set.
	 */
	public void setPartNumber(int partNumber) {
		this.partNumber = partNumber;
	}
	
	/**
	 * @return Returns the partOfCode.
	 */
	public String[] getPartOfCode() {
		return partOfCode;
	}
	
	/**
	 * @param partOfCode
	 *            The partOfCode to set.
	 */
	public void setPartOfCode(String[] partOfCode) {
		this.partOfCode = partOfCode;
	}
	
	/**
	 * @return Returns the partOfSeqNo.
	 */
	public int[] getPartOfSeqNo() {
		return partOfSeqNo;
	}
	
	/**
	 * @param partOfSeqNo
	 *            The partOfSeqNo to set.
	 */
	public void setPartOfSeqNo(int[] partOfSeqNo) {
		this.partOfSeqNo = partOfSeqNo;
	}
	
	/**
	 * @return Returns the priceBookNumber.
	 */
	public int getPriceBookNumber() {
		return priceBookNumber;
	}
	
	/**
	 * @param priceBookNumber
	 *            The priceBookNumber to set.
	 */
	public void setPriceBookNumber(int priceBookNumber) {
		this.priceBookNumber = priceBookNumber;
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
	 * @return Returns the refEDLNo.
	 */
	public String[] getRefEDLNo() {
		return RefEDLNo;
	}
	
	/**
	 * @param refEDLNo
	 *            The refEDLNo to set.
	 */
	public void setRefEDLNo(String[] refEDLNo) {
		RefEDLNo = refEDLNo;
	}
	
	/**
	 * @return Returns the revisionNo.
	 */
	public String getRevisionNo() {
		return revisionNo;
	}
	
	/**
	 * @param revisionNo
	 *            The revisionNo to set.
	 */
	public void setRevisionNo(String revisionNo) {
		this.revisionNo = revisionNo;
	}
	
	/**
	 * @return Returns the rowIndex.
	 */
	public int getRowIndex() {
		return rowIndex;
	}
	
	/**
	 * @param rowIndex
	 *            The rowIndex to set.
	 */
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}
	
	/**
	 * @return Returns the selectedClause.
	 */
	public String getSelectedClause() {
		return selectedClause;
	}
	
	/**
	 * @param selectedClause
	 *            The selectedClause to set.
	 */
	public void setSelectedClause(String selectedClause) {
		this.selectedClause = selectedClause;
	}
	
	/**
	 * @return Returns the standardEquipmentList.
	 */
	public ArrayList getStandardEquipmentList() {
		return standardEquipmentList;
	}
	
	/**
	 * @param standardEquipmentList
	 *            The standardEquipmentList to set.
	 */
	public void setStandardEquipmentList(ArrayList standardEquipmentList) {
		this.standardEquipmentList = standardEquipmentList;
	}
	
	/**
	 * @return Returns the subSectionList.
	 */
	public ArrayList getSubSectionList() {
		return subSectionList;
	}
	
	/**
	 * @param subSectionList
	 *            The subSectionList to set.
	 */
	public void setSubSectionList(ArrayList subSectionList) {
		this.subSectionList = subSectionList;
	}
	
	/**
	 * @return Returns the subSectionSeqCode.
	 */
	public ArrayList getSubSectionSeqCode() {
		return subSectionSeqCode;
	}
	
	/**
	 * @param subSectionSeqCode
	 *            The subSectionSeqCode to set.
	 */
	public void setSubSectionSeqCode(ArrayList subSectionSeqCode) {
		this.subSectionSeqCode = subSectionSeqCode;
	}
	
	/**
	 * @return Returns the subSectionSeqName.
	 */
	public String getSubSectionSeqName() {
		return subSectionSeqName;
	}
	
	/**
	 * @param subSectionSeqName
	 *            The subSectionSeqName to set.
	 */
	public void setSubSectionSeqName(String subSectionSeqName) {
		this.subSectionSeqName = subSectionSeqName;
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
	 * @return Returns the subSectionSeqNo.
	 */
	public int getSubSectionSeqNo() {
		return subSectionSeqNo;
	}
	
	/**
	 * @param subSectionSeqNo
	 *            The subSectionSeqNo to set.
	 */
	public void setSubSectionSeqNo(int subSectionSeqNo) {
		this.subSectionSeqNo = subSectionSeqNo;
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
	 * @return Returns the modelSeqNo.
	 */
	public int getModelSeqNo() {
		return modelSeqNo;
	}
	
	/**
	 * @param modelSeqNo
	 *            The modelSeqNo to set.
	 */
	public void setModelSeqNo(int modelSeqNo) {
		this.modelSeqNo = modelSeqNo;
	}
	
	/**
	 * @return Returns the clauseVo.
	 */
	public ClauseVO getClauseVo() {
		return clauseVo;
	}
	
	/**
	 * @param clauseVo
	 *            The clauseVo to set.
	 */
	public void setClauseVo(ClauseVO clauseVo) {
		this.clauseVo = clauseVo;
	}
	
	/**
	 * @return Returns the stdEquipmentList.
	 */
	public ArrayList getStdEquipmentList() {
		return stdEquipmentList;
	}
	
	/**
	 * @param stdEquipmentList
	 *            The stdEquipmentList to set.
	 */
	public void setStdEquipmentList(ArrayList stdEquipmentList) {
		this.stdEquipmentList = stdEquipmentList;
	}
	
	/**
	 * @return Returns the standardEquipmentDesc.
	 */
	public String getStandardEquipmentDesc() {
		return standardEquipmentDesc;
	}
	
	/**
	 * @param standardEquipmentDesc
	 *            The standardEquipmentDesc to set.
	 */
	public void setStandardEquipmentDesc(String standardEquipmentDesc) {
		this.standardEquipmentDesc = standardEquipmentDesc;
	}
	
	/**
	 * @return Returns the standardEquipmentSeqNo.
	 */
	public int getStandardEquipmentSeqNo() {
		return standardEquipmentSeqNo;
	}
	
	/**
	 * @param standardEquipmentSeqNo
	 *            The standardEquipmentSeqNo to set.
	 */
	public void setStandardEquipmentSeqNo(int standardEquipmentSeqNo) {
		this.standardEquipmentSeqNo = standardEquipmentSeqNo;
	}
	
	/**
	 * @return Returns the tableArrayData1.
	 */
	public String[] getTableArrayData1() {
		return tableArrayData1;
	}
	
	/**
	 * @param tableArrayData1
	 *            The tableArrayData1 to set.
	 */
	public void setTableArrayData1(String[] tableArrayData1) {
		this.tableArrayData1 = tableArrayData1;
	}
	
	/**
	 * @return Returns the deleterow.
	 */
	public String getDeleterow() {
		return deleterow;
	}
	
	/**
	 * @param deleterow
	 *            The deleterow to set.
	 */
	public void setDeleterow(String deleterow) {
		this.deleterow = deleterow;
	}
	
	/**
	 * @return Returns the hdPreSelectedModel.
	 */
	public int getHdPreSelectedModel() {
		return hdPreSelectedModel;
	}
	
	/**
	 * @param hdPreSelectedModel
	 *            The hdPreSelectedModel to set.
	 */
	public void setHdPreSelectedModel(int hdPreSelectedModel) {
		this.hdPreSelectedModel = hdPreSelectedModel;
	}
	
	/**
	 * @return Returns the hdSelectedModel.
	 */
	public String getHdSelectedModel() {
		return hdSelectedModel;
	}
	
	/**
	 * @param hdSelectedModel
	 *            The hdSelectedModel to set.
	 */
	public void setHdSelectedModel(String hdSelectedModel) {
		this.hdSelectedModel = hdSelectedModel;
	}
	
	/**
	 * @return Returns the clauseDescForTextArea.
	 */
	public String getClauseDescForTextArea() {
		return clauseDescForTextArea;
	}
	
	/**
	 * @param clauseDescForTextArea
	 *            The clauseDescForTextArea to set.
	 */
	public void setClauseDescForTextArea(String clauseDescForTextArea) {
		this.clauseDescForTextArea = clauseDescForTextArea;
	}
	
}
