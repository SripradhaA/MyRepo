package com.EMD.LSDB.form.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

public class ModelClauseDescPopUpForm extends EMDForm {
	
	private int modelSeqNo;
	
	private String modelName;
	
	private int sectionSeqNo;
	
	private ArrayList sectionList;
	
	private String sectionName;
	
	private int subSecSeqNo;
	
	private ArrayList subSectionList;
	
	private String subSecName;
	
	private ArrayList componentVO;
	
	private int clauseSeqNo;
	
	private String clauseDesc;
	
	private String hdnModelName;
	
	private int clauseVersionNo;
	
	//Added for LSDB_CR_46 PM&I change
	private String specType;
	
	private String hdnspecType;
	//Added for CR_81 Locomotive and Power Products Enhancements by RR68151
	private String hdnClaChrstcFlag = null;
	
	//Added for CR_85 by sd41630 on 08/01/2010
	private int combntnSeqNo;
	
	private int hdnClauseSeqNo;

	private ArrayList charGrpCombntnList;
	
	private String hdnSectionName;
	
	private String  hdnSubSectionName;
	
	private int linkClaSeqNo;
	
	//Added for CR_92
	private String clauseDes;
	private String subSecDisplayName = null;
	
	/**
	 * @return Returns the specType.
	 */
	public String getSpecType() {
		return specType;
	}
	
	/**
	 * @param specType The specType to set.
	 */
	public void setSpecType(String specType) {
		this.specType = specType;
	}
	
	/**
	 * @return Returns the clauseVersionNo.
	 */
	public int getClauseVersionNo() {
		return clauseVersionNo;
	}
	
	/**
	 * @param clauseVersionNo
	 *            The clauseVersionNo to set.
	 */
	public void setClauseVersionNo(int clauseVersionNo) {
		this.clauseVersionNo = clauseVersionNo;
	}
	
	/**
	 * @return Returns the hdnModelName.
	 */
	public String getHdnModelName() {
		return hdnModelName;
	}
	
	/**
	 * @param hdnModelName
	 *            The hdnModelName to set.
	 */
	public void setHdnModelName(String hdnModelName) {
		this.hdnModelName = hdnModelName;
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
	 * @return Returns the componentVO.
	 */
	public ArrayList getComponentVO() {
		return componentVO;
	}
	
	/**
	 * @param componentVO
	 *            The componentVO to set.
	 */
	public void setComponentVO(ArrayList componentVO) {
		this.componentVO = componentVO;
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
	 * @return Returns the sectionList.
	 */
	public ArrayList getSectionList() {
		return sectionList;
	}
	
	/**
	 * @param sectionList
	 *            The sectionList to set.
	 */
	public void setSectionList(ArrayList sectionList) {
		this.sectionList = sectionList;
	}
	
	/**
	 * @return Returns the sectionName.
	 */
	public String getSectionName() {
		return sectionName;
	}
	
	/**
	 * @param sectionName
	 *            The sectionName to set.
	 */
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	
	/**
	 * @return Returns the sectionSeqNo.
	 */
	public int getSectionSeqNo() {
		return sectionSeqNo;
	}
	
	/**
	 * @param sectionSeqNo
	 *            The sectionSeqNo to set.
	 */
	public void setSectionSeqNo(int sectionSeqNo) {
		this.sectionSeqNo = sectionSeqNo;
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
	 * @return Returns the subSectionName.
	 */
	public String getSubSecName() {
		return subSecName;
	}
	
	/**
	 * @param subSectionName
	 *            The subSectionName to set.
	 */
	public void setSubSecName(String subSectionName) {
		this.subSecName = subSectionName;
	}
	
	/**
	 * @return Returns the subSectionSeqNo.
	 */
	public int getSubSecSeqNo() {
		return subSecSeqNo;
	}
	
	/**
	 * @param subSectionSeqNo
	 *            The subSectionSeqNo to set.
	 */
	public void setSubSecSeqNo(int subSectionSeqNo) {
		this.subSecSeqNo = subSectionSeqNo;
	}
	
	/**
	 * @return Returns the hdnspecType.
	 */
	public String getHdnspecType() {
		return hdnspecType;
	}
	
	/**
	 * @param hdnspecType The hdnspecType to set.
	 */
	public void setHdnspecType(String hdnspecType) {
		this.hdnspecType = hdnspecType;
	}

	/**
	 * @return Returns the hdnClaChrstcFlag.
	 */
	public String getHdnClaChrstcFlag() {
		return hdnClaChrstcFlag;
	}

	/**
	 * @param hdnClaChrstcFlag The hdnClaChrstcFlag to set.
	 */
	public void setHdnClaChrstcFlag(String hdnClaChrstcFlag) {
		this.hdnClaChrstcFlag = hdnClaChrstcFlag;
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
	 * @return Returns the combntnSeqNo.
	 */
	public int getCombntnSeqNo() {
		return combntnSeqNo;
	}

	/**
	 * @param combntnSeqNo The combntnSeqNo to set.
	 */
	public void setCombntnSeqNo(int combntnSeqNo) {
		this.combntnSeqNo = combntnSeqNo;
	}

	public String getHdnSectionName() {
		return hdnSectionName;
	}

	public void setHdnSectionName(String hdnSectionName) {
		this.hdnSectionName = hdnSectionName;
	}

	public int getHdnClauseSeqNo() {
		return hdnClauseSeqNo;
	}

	public void setHdnClauseSeqNo(int hdnClauseSeqNo) {
		this.hdnClauseSeqNo = hdnClauseSeqNo;
	}

	public String getHdnSubSectionName() {
		return hdnSubSectionName;
	}

	public void setHdnSubSectionName(String hdnSubSectionName) {
		this.hdnSubSectionName = hdnSubSectionName;
	}
//CR_85
	public int getLinkClaSeqNo() {
		return linkClaSeqNo;
	}

	public void setLinkClaSeqNo(int linkClaSeqNo) {
		this.linkClaSeqNo = linkClaSeqNo;
	}

	public String getClauseDes() {
		return clauseDes;
	}

	public void setClauseDes(String clauseDes) {
		this.clauseDes = clauseDes;
	}

	public String getSubSecDisplayName() {
		return subSecDisplayName;
	}

	public void setSubSecDisplayName(String subSecDisplayName) {
		this.subSecDisplayName = subSecDisplayName;
	}

}
