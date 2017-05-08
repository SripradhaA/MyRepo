package com.EMD.LSDB.form.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

public class CompMapMaintForm extends EMDForm {
	
	private int compGrpSeqNo = 0;
	
	private int modelSeqNo = 0;
	
	private int sectionSeqNo = 0;
	
	private int subSectionSeqNo = 0;
	//Added For CR_93
	private int componentSeqNo=0;
	
	private String hdnDefaultFlag;
	
	private String hdnCompValues;
	
	private String hdnApplyModel;
	
	private String hdnSelModel;
	
	private String hdnSelSec;
	
	private String hdnSelSubSec;
	
	private String hdnSelCompGrp;
	
	/* This Piece of Code is Modified For Valid Flag Change Request Starts */
	
	private String validFlag;
	
	/* This Piece of Code is Modified For Valid Flag Change Request Ends*/
	ArrayList modelList;
	
	ArrayList sectionList;
	
	ArrayList subSectionList;
	
	ArrayList compGrpList;
	
	ArrayList compList;
	
	//Added for CR-46 PM&I
	
	private String hdnSelSpecType;
	
	//Added For CR_81 on 28-Dec-09 by RR68151	
	private ArrayList compGroupTypeList = new ArrayList();
	
	private String compgrpCat = null;
	//Added For CR_81 on 28-Dec-09 by RR68151 - Ends here
	//Added For CR_93
	private String compSourceFlag=null;
	//Added For CR_93
	private int newClaSeqNo;
	
	//Added for CR_118
	private String displayCOCFlag;
	public String getDisplayCOCFlag() {
		return displayCOCFlag;
	}

	public void setDisplayCOCFlag(String displayCOCFlag) {
		this.displayCOCFlag = displayCOCFlag;
	}

	//Added for CR_118 Ends
	/**
	 * @return Returns the hdnSelSpecType.
	 */
	public String getHdnSelSpecType() {
		return hdnSelSpecType;
	}
	
	/**
	 * @param hdnSelSpecType The hdnSelSpecType to set.
	 */
	public void setHdnSelSpecType(String hdnSelSpecType) {
		this.hdnSelSpecType = hdnSelSpecType;
	}
	
	public ArrayList getCompGrpList() {
		return compGrpList;
	}
	
	public void setCompGrpList(ArrayList compGrpList) {
		this.compGrpList = compGrpList;
	}
	
	public int getCompGrpSeqNo() {
		return compGrpSeqNo;
	}
	
	public void setCompGrpSeqNo(int compGrpSeqNo) {
		this.compGrpSeqNo = compGrpSeqNo;
	}
	
	public ArrayList getCompList() {
		return compList;
	}
	
	public void setCompList(ArrayList compList) {
		this.compList = compList;
	}
	
	public String getHdnApplyModel() {
		return hdnApplyModel;
	}
	
	public void setHdnApplyModel(String hdnApplyModel) {
		this.hdnApplyModel = hdnApplyModel;
	}
	
	public String getHdnCompValues() {
		return hdnCompValues;
	}
	
	public void setHdnCompValues(String hdnCompValues) {
		this.hdnCompValues = hdnCompValues;
	}
	
	public String getHdnDefaultFlag() {
		return hdnDefaultFlag;
	}
	
	public void setHdnDefaultFlag(String hdnDefaultFlag) {
		this.hdnDefaultFlag = hdnDefaultFlag;
	}
	
	public String getHdnSelCompGrp() {
		return hdnSelCompGrp;
	}
	
	public void setHdnSelCompGrp(String hdnSelCompGrp) {
		this.hdnSelCompGrp = hdnSelCompGrp;
	}
	
	public String getHdnSelModel() {
		return hdnSelModel;
	}
	
	public void setHdnSelModel(String hdnSelModel) {
		this.hdnSelModel = hdnSelModel;
	}
	
	public String getHdnSelSec() {
		return hdnSelSec;
	}
	
	public void setHdnSelSec(String hdnSelSec) {
		this.hdnSelSec = hdnSelSec;
	}
	
	public String getHdnSelSubSec() {
		return hdnSelSubSec;
	}
	
	public void setHdnSelSubSec(String hdnSelSubSec) {
		this.hdnSelSubSec = hdnSelSubSec;
	}
	
	public ArrayList getModelList() {
		return modelList;
	}
	
	public void setModelList(ArrayList modelList) {
		this.modelList = modelList;
	}
	
	public int getModelSeqNo() {
		return modelSeqNo;
	}
	
	public void setModelSeqNo(int modelSeqNo) {
		this.modelSeqNo = modelSeqNo;
	}
	
	public ArrayList getSectionList() {
		return sectionList;
	}
	
	public void setSectionList(ArrayList sectionList) {
		this.sectionList = sectionList;
	}
	
	public int getSectionSeqNo() {
		return sectionSeqNo;
	}
	
	public void setSectionSeqNo(int sectionSeqNo) {
		this.sectionSeqNo = sectionSeqNo;
	}
	
	public ArrayList getSubSectionList() {
		return subSectionList;
	}
	
	public void setSubSectionList(ArrayList subSectionList) {
		this.subSectionList = subSectionList;
	}
	
	public int getSubSectionSeqNo() {
		return subSectionSeqNo;
	}
	
	public void setSubSectionSeqNo(int subSectionSeqNo) {
		this.subSectionSeqNo = subSectionSeqNo;
	}
	
	/**
	 * @return Returns the validFlag.
	 */
	public String getValidFlag() {
		return validFlag;
	}
	
	/**
	 * @param validFlag The validFlag to set.
	 */
	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}

	/**
	 * @return Returns the compGroupTypeList.
	 */
	public ArrayList getCompGroupTypeList() {
		return compGroupTypeList;
	}

	/**
	 * @param compGroupTypeList The compGroupTypeList to set.
	 */
	public void setCompGroupTypeList(ArrayList compGroupTypeList) {
		this.compGroupTypeList = compGroupTypeList;
	}

	/**
	 * @return Returns the compgrpCat.
	 */
	public String getCompgrpCat() {
		return compgrpCat;
	}

	/**
	 * @param compgrpCat The compgrpCat to set.
	 */
	public void setCompgrpCat(String compgrpCat) {
		this.compgrpCat = compgrpCat;
	}

	public String getCompSourceFlag() {
		return compSourceFlag;
	}

	public void setCompSourceFlag(String compSourceFlag) {
		this.compSourceFlag = compSourceFlag;
	}

	public int getComponentSeqNo() {
		return componentSeqNo;
	}

	public void setComponentSeqNo(int componentSeqNo) {
		this.componentSeqNo = componentSeqNo;
	}

	public int getNewClaSeqNo() {
		return newClaSeqNo;
	}

	public void setNewClaSeqNo(int newClaSeqNo) {
		this.newClaSeqNo = newClaSeqNo;
	}
	
}
