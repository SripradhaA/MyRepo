/*
 * Created on Oct 20, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.vo.common;

import java.util.ArrayList;

/**
 * @author PS57222
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ReqClauseVO extends EMDVO {
	
	private int changeTypeSeqNO;
	
	private int fromClauseSeqNo;
	
	private String fromClauseNo;
	
	private int fromClauseVerNo;
	
	private String toClauseNo;
	
	private int toClauseVersionNo;
	
	private int toParentClaSeqNo;
	
	private String toParentClaDesc;
	
	private String toClauseDesc;
	
	private String toComments;
	
	private int todwONumber;
	
	private int toPartNumber;
	
	private int topriceBookNumber;
	
	private String toDefaultFlag;
	
	private String[] eDLNo;
	
	private String[] refEDLNO;
	
	private String toParentClauseNo;
	
	private String toClaVerClauseNo;
	
	private String toClaVerDesc;
	
	private String toComponents;
	
	private String[] toRefEDLNo;
	
	private String[] toEdlNo;
	
	private String[] componentSeqNo;
	
	private int toStdEquipSeqNo;
	
	private ArrayList tableDataVO;
	
	private int toClaVerSeqNo;
	
	private int[] partOfClaSeqNo;
	
	private int[] partOfSeqNo;
	
	private int[] partOfSubSecSeqNo;
	
	private String[] partOfClaNo;
	
	private String[] partOfClaDesc;
	
	private String reason;
	
	private String claNoFlag;
	
	private String versionFlag;
	
	private String claDescFlag;
	
	private String toStdEqpDesc;
	
	private ArrayList edlNo;
	
	private ArrayList refEdlNo;
	
	private ArrayList partOf;
	
	private ArrayList tableData;
	
	private ArrayList claComp;
	
	private ArrayList partSubSecSeqNo;
	
	private ArrayList partClaNo;
	
	private ArrayList partClaSeqNo;
	
	private ArrayList partClaDes;
	
	private String changeTypeDesc;
	
	private String toComponentSeqNo;
	
	//Added for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151
	private String enggDataClrFlag;
	
	private String dwoNoClrFlag;
	
	private String partNoClrFlag;
	
	private String priceBookClrFlag;
	
	private String stdEqpClrFlag;
	
	private String[] edlClrFlag;
	
	private String[] refEdlClrFlag;
	
	private String[] partOfClrFlag;
//	Added For CR_93
	private int tableDataColSize=0;
	
	/**
	 * @return Returns the toComponentSeqNo.
	 */
	public String getToComponentSeqNo() {
		return toComponentSeqNo;
	}
	
	/**
	 * @param toComponentSeqNo
	 *            The toComponentSeqNo to set.
	 */
	public void setToComponentSeqNo(String toComponentSeqNo) {
		this.toComponentSeqNo = toComponentSeqNo;
	}
	
	/**
	 * @return Returns the changeTypeDesc.
	 */
	public String getChangeTypeDesc() {
		return changeTypeDesc;
	}
	
	/**
	 * @param changeTypeDesc
	 *            The changeTypeDesc to set.
	 */
	public void setChangeTypeDesc(String changeTypeDesc) {
		this.changeTypeDesc = changeTypeDesc;
	}
	
	/**
	 * @return Returns the claComp.
	 */
	public ArrayList getClaComp() {
		return claComp;
	}
	
	/**
	 * @param claComp
	 *            The claComp to set.
	 */
	public void setClaComp(ArrayList claComp) {
		this.claComp = claComp;
	}
	
	/**
	 * @return Returns the edlNo.
	 */
	public ArrayList getEdlNo() {
		return edlNo;
	}
	
	/**
	 * @param edlNo
	 *            The edlNo to set.
	 */
	public void setEdlNo(ArrayList edlNo) {
		this.edlNo = edlNo;
	}
	
	/**
	 * @return Returns the partOf.
	 */
	public ArrayList getPartOf() {
		return partOf;
	}
	
	/**
	 * @param partOf
	 *            The partOf to set.
	 */
	public void setPartOf(ArrayList partOf) {
		this.partOf = partOf;
	}
	
	/**
	 * @return Returns the refEdlNo.
	 */
	public ArrayList getRefEdlNo() {
		return refEdlNo;
	}
	
	/**
	 * @param refEdlNo
	 *            The refEdlNo to set.
	 */
	public void setRefEdlNo(ArrayList refEdlNo) {
		this.refEdlNo = refEdlNo;
	}
	
	/**
	 * @return Returns the tableData.
	 */
	public ArrayList getTableData() {
		return tableData;
	}
	
	/**
	 * @param tableData
	 *            The tableData to set.
	 */
	public void setTableData(ArrayList tableData) {
		this.tableData = tableData;
	}
	
	/**
	 * @return Returns the versionFlag.
	 */
	public String getVersionFlag() {
		return versionFlag;
	}
	
	/**
	 * @param versionFlag
	 *            The versionFlag to set.
	 */
	public void setVersionFlag(String versionFlag) {
		this.versionFlag = versionFlag;
	}
	
	/**
	 * @return Returns the claNoFlag.
	 */
	public String getClaNoFlag() {
		return claNoFlag;
	}
	
	/**
	 * @param claNoFlag
	 *            The claNoFlag to set.
	 */
	public void setClaNoFlag(String claNoFlag) {
		this.claNoFlag = claNoFlag;
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
	 * @return Returns the partOfClaSeqNo.
	 */
	
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
	
	private String[] partOfCode;
	
	/**
	 * @return Returns the tableDataVO.
	 */
	public ArrayList getTableDataVO() {
		return tableDataVO;
	}
	
	/**
	 * @param tableDataVO
	 *            The tableDataVO to set.
	 */
	public void setTableDataVO(ArrayList tableDataVO) {
		this.tableDataVO = tableDataVO;
	}
	
	/**
	 * @return Returns the toStdEquipSeqNo.
	 */
	public int getToStdEquipSeqNo() {
		return toStdEquipSeqNo;
	}
	
	/**
	 * @param toStdEquipSeqNo
	 *            The toStdEquipSeqNo to set.
	 */
	public void setToStdEquipSeqNo(int toStdEquipSeqNo) {
		this.toStdEquipSeqNo = toStdEquipSeqNo;
	}
	
	/**
	 * @return Returns the componentSeqNo.
	 */
	public String[] getComponentSeqNo() {
		return componentSeqNo;
	}
	
	/**
	 * @param componentSeqNo
	 *            The componentSeqNo to set.
	 */
	public void setComponentSeqNo(String[] componentSeqNo) {
		this.componentSeqNo = componentSeqNo;
	}
	
	/**
	 * @return Returns the changeTypeSeqNO.
	 */
	public int getChangeTypeSeqNO() {
		return changeTypeSeqNO;
	}
	
	/**
	 * @param changeTypeSeqNO
	 *            The changeTypeSeqNO to set.
	 */
	public void setChangeTypeSeqNO(int changeTypeSeqNO) {
		this.changeTypeSeqNO = changeTypeSeqNO;
	}
	
	/**
	 * @return Returns the eDLNo.
	 */
	public String[] getEDLNo() {
		return eDLNo;
	}
	
	/**
	 * @param no
	 *            The eDLNo to set.
	 */
	public void setEDLNo(String[] no) {
		eDLNo = no;
	}
	
	/**
	 * @return Returns the fromClauseNo.
	 */
	public String getFromClauseNo() {
		return fromClauseNo;
	}
	
	/**
	 * @param fromClauseNo
	 *            The fromClauseNo to set.
	 */
	public void setFromClauseNo(String fromClauseNo) {
		this.fromClauseNo = fromClauseNo;
	}
	
	/**
	 * @return Returns the fromClauseSeqNo.
	 */
	public int getFromClauseSeqNo() {
		return fromClauseSeqNo;
	}
	
	/**
	 * @param fromClauseSeqNo
	 *            The fromClauseSeqNo to set.
	 */
	public void setFromClauseSeqNo(int fromClauseSeqNo) {
		this.fromClauseSeqNo = fromClauseSeqNo;
	}
	
	/**
	 * @return Returns the fromClauseVerNo.
	 */
	public int getFromClauseVerNo() {
		return fromClauseVerNo;
	}
	
	/**
	 * @param fromClauseVerNo
	 *            The fromClauseVerNo to set.
	 */
	public void setFromClauseVerNo(int fromClauseVerNo) {
		this.fromClauseVerNo = fromClauseVerNo;
	}
	
	/**
	 * @return Returns the refEDLNO.
	 */
	public String[] getRefEDLNO() {
		return refEDLNO;
	}
	
	/**
	 * @param refEDLNO
	 *            The refEDLNO to set.
	 */
	public void setRefEDLNO(String[] refEDLNO) {
		this.refEDLNO = refEDLNO;
	}
	
	/**
	 * @return Returns the toClauseDesc.
	 */
	public String getToClauseDesc() {
		return toClauseDesc;
	}
	
	/**
	 * @param toClauseDesc
	 *            The toClauseDesc to set.
	 */
	public void setToClauseDesc(String toClauseDesc) {
		this.toClauseDesc = toClauseDesc;
	}
	
	/**
	 * @return Returns the toClauseNo.
	 */
	public String getToClauseNo() {
		return toClauseNo;
	}
	
	/**
	 * @param toClauseNo
	 *            The toClauseNo to set.
	 */
	public void setToClauseNo(String toClauseNo) {
		this.toClauseNo = toClauseNo;
	}
	
	/**
	 * @return Returns the toClauseVersionNo.
	 */
	public int getToClauseVersionNo() {
		return toClauseVersionNo;
	}
	
	/**
	 * @param toClauseVersionNo
	 *            The toClauseVersionNo to set.
	 */
	public void setToClauseVersionNo(int toClauseVersionNo) {
		this.toClauseVersionNo = toClauseVersionNo;
	}
	
	/**
	 * @return Returns the toComments.
	 */
	public String getToComments() {
		return toComments;
	}
	
	/**
	 * @param toComments
	 *            The toComments to set.
	 */
	public void setToComments(String toComments) {
		this.toComments = toComments;
	}
	
	/**
	 * @return Returns the toDefaultFlag.
	 */
	public String getToDefaultFlag() {
		return toDefaultFlag;
	}
	
	/**
	 * @param toDefaultFlag
	 *            The toDefaultFlag to set.
	 */
	public void setToDefaultFlag(String toDefaultFlag) {
		this.toDefaultFlag = toDefaultFlag;
	}
	
	/**
	 * @return Returns the toDWONo.
	 */
	
	/**
	 * @return Returns the toParentClaDesc.
	 */
	public String getToParentClaDesc() {
		return toParentClaDesc;
	}
	
	/**
	 * @param toParentClaDesc
	 *            The toParentClaDesc to set.
	 */
	public void setToParentClaDesc(String toParentClaDesc) {
		this.toParentClaDesc = toParentClaDesc;
	}
	
	/**
	 * @return Returns the toParentClaSeqNo.
	 */
	public int getToParentClaSeqNo() {
		return toParentClaSeqNo;
	}
	
	/**
	 * @param toParentClaSeqNo
	 *            The toParentClaSeqNo to set.
	 */
	public void setToParentClaSeqNo(int toParentClaSeqNo) {
		this.toParentClaSeqNo = toParentClaSeqNo;
	}
	
	/**
	 * @return Returns the toClaVerClauseNo.
	 */
	public String getToClaVerClauseNo() {
		return toClaVerClauseNo;
	}
	
	/**
	 * @param toClaVerClauseNo
	 *            The toClaVerClauseNo to set.
	 */
	public void setToClaVerClauseNo(String toClaVerClauseNo) {
		this.toClaVerClauseNo = toClaVerClauseNo;
	}
	
	/**
	 * @return Returns the toClaVerDesc.
	 */
	public String getToClaVerDesc() {
		return toClaVerDesc;
	}
	
	/**
	 * @param toClaVerDesc
	 *            The toClaVerDesc to set.
	 */
	public void setToClaVerDesc(String toClaVerDesc) {
		this.toClaVerDesc = toClaVerDesc;
	}
	
	/**
	 * @return Returns the toComponents.
	 */
	public String getToComponents() {
		return toComponents;
	}
	
	/**
	 * @param toComponents
	 *            The toComponents to set.
	 */
	public void setToComponents(String toComponents) {
		this.toComponents = toComponents;
	}
	
	/**
	 * @return Returns the todwONumber.
	 */
	public int getTodwONumber() {
		return todwONumber;
	}
	
	/**
	 * @param todwONumber
	 *            The todwONumber to set.
	 */
	public void setTodwONumber(int todwONumber) {
		this.todwONumber = todwONumber;
	}
	
	/**
	 * @return Returns the toEdlNo.
	 */
	public String[] getToEdlNo() {
		return toEdlNo;
	}
	
	/**
	 * @param toEdlNo
	 *            The toEdlNo to set.
	 */
	public void setToEdlNo(String[] toEdlNo) {
		this.toEdlNo = toEdlNo;
	}
	
	/**
	 * @return Returns the toParentClauseNo.
	 */
	public String getToParentClauseNo() {
		return toParentClauseNo;
	}
	
	/**
	 * @param toParentClauseNo
	 *            The toParentClauseNo to set.
	 */
	public void setToParentClauseNo(String toParentClauseNo) {
		this.toParentClauseNo = toParentClauseNo;
	}
	
	/**
	 * @return Returns the toPartNumber.
	 */
	public int getToPartNumber() {
		return toPartNumber;
	}
	
	/**
	 * @param toPartNumber
	 *            The toPartNumber to set.
	 */
	public void setToPartNumber(int toPartNumber) {
		this.toPartNumber = toPartNumber;
	}
	
	/**
	 * @return Returns the topriceBookNumber.
	 */
	public int getTopriceBookNumber() {
		return topriceBookNumber;
	}
	
	/**
	 * @param topriceBookNumber
	 *            The topriceBookNumber to set.
	 */
	public void setTopriceBookNumber(int topriceBookNumber) {
		this.topriceBookNumber = topriceBookNumber;
	}
	
	/**
	 * @return Returns the toRefEDLNo.
	 */
	public String[] getToRefEDLNo() {
		return toRefEDLNo;
	}
	
	/**
	 * @param toRefEDLNo
	 *            The toRefEDLNo to set.
	 */
	public void setToRefEDLNo(String[] toRefEDLNo) {
		this.toRefEDLNo = toRefEDLNo;
	}
	
	/**
	 * @return Returns the toClaVerSeqNo.
	 */
	public int getToClaVerSeqNo() {
		return toClaVerSeqNo;
	}
	
	/**
	 * @param toClaVerSeqNo
	 *            The toClaVerSeqNo to set.
	 */
	public void setToClaVerSeqNo(int toClaVerSeqNo) {
		this.toClaVerSeqNo = toClaVerSeqNo;
	}
	
	/**
	 * @return Returns the partOfClaSeqNo.
	 */
	public int[] getPartOfClaSeqNo() {
		return partOfClaSeqNo;
	}
	
	/**
	 * @param partOfClaSeqNo
	 *            The partOfClaSeqNo to set.
	 */
	public void setPartOfClaSeqNo(int[] partOfClaSeqNo) {
		this.partOfClaSeqNo = partOfClaSeqNo;
	}
	
	/**
	 * @return Returns the toStdEqpDesc.
	 */
	public String getToStdEqpDesc() {
		return toStdEqpDesc;
	}
	
	/**
	 * @param toStdEqpDesc
	 *            The toStdEqpDesc to set.
	 */
	public void setToStdEqpDesc(String toStdEqpDesc) {
		this.toStdEqpDesc = toStdEqpDesc;
	}
	
	/**
	 * @return Returns the partOfSubSecSeqNo.
	 */
	public int[] getPartOfSubSecSeqNo() {
		return partOfSubSecSeqNo;
	}
	
	/**
	 * @param partOfSubSecSeqNo
	 *            The partOfSubSecSeqNo to set.
	 */
	public void setPartOfSubSecSeqNo(int[] partOfSubSecSeqNo) {
		this.partOfSubSecSeqNo = partOfSubSecSeqNo;
	}
	
	/**
	 * @return Returns the partOfClaDesc.
	 */
	public String[] getPartOfClaDesc() {
		return partOfClaDesc;
	}
	
	/**
	 * @param partOfClaDesc
	 *            The partOfClaDesc to set.
	 */
	public void setPartOfClaDesc(String[] partOfClaDesc) {
		this.partOfClaDesc = partOfClaDesc;
	}
	
	/**
	 * @return Returns the partClaDes.
	 */
	public ArrayList getPartClaDes() {
		return partClaDes;
	}
	
	/**
	 * @param partClaDes
	 *            The partClaDes to set.
	 */
	public void setPartClaDes(ArrayList partClaDes) {
		this.partClaDes = partClaDes;
	}
	
	/**
	 * @return Returns the partClaNo.
	 */
	public ArrayList getPartClaNo() {
		return partClaNo;
	}
	
	/**
	 * @param partClaNo
	 *            The partClaNo to set.
	 */
	public void setPartClaNo(ArrayList partClaNo) {
		this.partClaNo = partClaNo;
	}
	
	/**
	 * @return Returns the partClaSeqNo.
	 */
	public ArrayList getPartClaSeqNo() {
		return partClaSeqNo;
	}
	
	/**
	 * @param partClaSeqNo
	 *            The partClaSeqNo to set.
	 */
	public void setPartClaSeqNo(ArrayList partClaSeqNo) {
		this.partClaSeqNo = partClaSeqNo;
	}
	
	/**
	 * @return Returns the partSubSecSeqNo.
	 */
	public ArrayList getPartSubSecSeqNo() {
		return partSubSecSeqNo;
	}
	
	/**
	 * @param partSubSecSeqNo
	 *            The partSubSecSeqNo to set.
	 */
	public void setPartSubSecSeqNo(ArrayList partSubSecSeqNo) {
		this.partSubSecSeqNo = partSubSecSeqNo;
	}
	
	/**
	 * @param partOfClaNo
	 *            The partOfClaNo to set.
	 */
	public void setPartOfClaNo(String[] partOfClaNo) {
		this.partOfClaNo = partOfClaNo;
	}
	
	/**
	 * @return Returns the partOfClaNo.
	 */
	public String[] getPartOfClaNo() {
		return partOfClaNo;
	}
	
	/**
	 * @return Returns the claDescFlag.
	 */
	public String getClaDescFlag() {
		return claDescFlag;
	}
	
	/**
	 * @param claDescFlag The claDescFlag to set.
	 */
	public void setClaDescFlag(String claDescFlag) {
		this.claDescFlag = claDescFlag;
	}

	/**
	 * @return Returns the dwoNoClrFlag.
	 */
	public String getDwoNoClrFlag() {
		return dwoNoClrFlag;
	}

	/**
	 * @param dwoNoClrFlag The dwoNoClrFlag to set.
	 */
	public void setDwoNoClrFlag(String dwoNoClrFlag) {
		this.dwoNoClrFlag = dwoNoClrFlag;
	}

	/**
	 * @return Returns the edlClrFlag.
	 */
	public String[] getEdlClrFlag() {
		return edlClrFlag;
	}

	/**
	 * @param edlClrFlag The edlClrFlag to set.
	 */
	public void setEdlClrFlag(String[] edlClrFlag) {
		this.edlClrFlag = edlClrFlag;
	}

	/**
	 * @return Returns the enggDataClrFlag.
	 */
	public String getEnggDataClrFlag() {
		return enggDataClrFlag;
	}

	/**
	 * @param enggDataClrFlag The enggDataClrFlag to set.
	 */
	public void setEnggDataClrFlag(String enggDataClrFlag) {
		this.enggDataClrFlag = enggDataClrFlag;
	}

	/**
	 * @return Returns the partNoClrFlag.
	 */
	public String getPartNoClrFlag() {
		return partNoClrFlag;
	}

	/**
	 * @param partNoClrFlag The partNoClrFlag to set.
	 */
	public void setPartNoClrFlag(String partNoClrFlag) {
		this.partNoClrFlag = partNoClrFlag;
	}

	/**
	 * @return Returns the partOfClrFlag.
	 */
	public String[] getPartOfClrFlag() {
		return partOfClrFlag;
	}

	/**
	 * @param partOfClrFlag The partOfClrFlag to set.
	 */
	public void setPartOfClrFlag(String[] partOfClrFlag) {
		this.partOfClrFlag = partOfClrFlag;
	}

	/**
	 * @return Returns the priceBookClrFlag.
	 */
	public String getPriceBookClrFlag() {
		return priceBookClrFlag;
	}

	/**
	 * @param priceBookClrFlag The priceBookClrFlag to set.
	 */
	public void setPriceBookClrFlag(String priceBookClrFlag) {
		this.priceBookClrFlag = priceBookClrFlag;
	}

	/**
	 * @return Returns the refEdlClrFlag.
	 */
	public String[] getRefEdlClrFlag() {
		return refEdlClrFlag;
	}

	/**
	 * @param refEdlClrFlag The refEdlClrFlag to set.
	 */
	public void setRefEdlClrFlag(String[] refEdlClrFlag) {
		this.refEdlClrFlag = refEdlClrFlag;
	}

	/**
	 * @return Returns the stdEqpClrFlag.
	 */
	public String getStdEqpClrFlag() {
		return stdEqpClrFlag;
	}

	/**
	 * @param stdEqpClrFlag The stdEqpClrFlag to set.
	 */
	public void setStdEqpClrFlag(String stdEqpClrFlag) {
		this.stdEqpClrFlag = stdEqpClrFlag;
	}

	public int getTableDataColSize() {
		return tableDataColSize;
	}

	public void setTableDataColSize(int tableDataColSize) {
		this.tableDataColSize = tableDataColSize;
	}
}
