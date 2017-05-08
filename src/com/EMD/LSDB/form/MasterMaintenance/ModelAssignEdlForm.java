/**
 * 
 */
package com.EMD.LSDB.form.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

/**
 * @author RR68151
 * 
 */
public class ModelAssignEdlForm extends EMDForm {

	private int prevSpecType;
	
	private int modelseqno;
	
	private int hdPreSelectedModel;
	
	private String hdnModelName = null;
	
	private ArrayList listModels = null;
	
	private int subSectionSeqNo;
	
	private int clauseNo;
	
	private int clauseSeqNo;
	
	private int hdnClauseSeqNo = 0;
	
	private String clauseDes = null;
	
	private String charstcEdlNo = null;
	
	private String charstcRefEDLNo = null;
	
	private int combntnSeqNo;

	private ArrayList compGroupList;
	
	private ArrayList compList;
	
	private String[] componentGroupSeqNo;
	
	private String[] compSeqNo;

	private ArrayList charGrpCombntnList;
	
	private String hdnCharEdlNo;
	
	private String hdnCharRefEdlNo;	
	//Added For CR_85
	private int linkClaSeqNo;
	private ArrayList subSectionList;
	private ArrayList sectionList;
	
	private String hdnSelSpecType;
	
	//Added for CR_131
	private String clauseUnlinked;
	
	public String getClauseUnlinked() {
		return clauseUnlinked;
	}

	public void setClauseUnlinked(String clauseUnlinked) {
		this.clauseUnlinked = clauseUnlinked;
	}

	/**
	 * @return Returns the preSpecType.
	 */
	public int getPrevSpecType() {
		return prevSpecType;
	}
	
	/**
	 * @param preSpecType
	 *            The preSpecType to set.
	 */
	public void setPrevSpecType(int prevSpecType) {
		this.prevSpecType = prevSpecType;
	}
	
	/**
	 * @return Returns the compList.
	 */
	public ArrayList getCompList() {
		return compList;
	}
	
	/**
	 * @param compList
	 *            The compList to set.
	 */
	public void setCompList(ArrayList compList) {
		this.compList = compList;
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
	 * @return Returns the listModels.
	 */
	public ArrayList getListModels() {
		return listModels;
	}
	
	/**
	 * @param listModels
	 *            The listModels to set.
	 */
	public void setListModels(ArrayList listModels) {
		this.listModels = listModels;
	}
	
	/**
	 * @return Returns the modelseqno.
	 */
	public int getModelseqno() {
		return modelseqno;
	}
	
	/**
	 * @param modelseqno
	 *            The modelseqno to set.
	 */
	public void setModelseqno(int modelseqno) {
		this.modelseqno = modelseqno;
	}
	
	/**
	 * @return Returns the hdnClauseSeqNo.
	 */
	public int getHdnClauseSeqNo() {
		return hdnClauseSeqNo;
	}
	
	/**
	 * @param hdnClauseSeqNo
	 *            The hdnClauseSeqNo to set.
	 */
	public void setHdnClauseSeqNo(int hdnClauseSeqNo) {
		this.hdnClauseSeqNo = hdnClauseSeqNo;
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
	 * @return Returns the hdPreSelectedModel.
	 */
	public int getHdPreSelectedModel() {
		return hdPreSelectedModel;
	}
	
	/**
	 * @param hdPreSelectedModel The hdPreSelectedModel to set.
	 */
	public void setHdPreSelectedModel(int hdPreSelectedModel) {
		this.hdPreSelectedModel = hdPreSelectedModel;
	}

	/**
	 * @return Returns the compGroupList.
	 */
	public ArrayList getCompGroupList() {
		return compGroupList;
	}

	/**
	 * @param compGroupList The compGroupList to set.
	 */
	public void setCompGroupList(ArrayList compGroupList) {
		this.compGroupList = compGroupList;
	}

	/**
	 * @return Returns the componentGroupSeqNo.
	 */
	public String[] getComponentGroupSeqNo() {
		return componentGroupSeqNo;
	}

	/**
	 * @param componentGroupSeqNo The componentGroupSeqNo to set.
	 */
	public void setComponentGroupSeqNo(String[] componentGroupSeqNo) {
		this.componentGroupSeqNo = componentGroupSeqNo;
	}

	/**
	 * @return Returns the compSeqNo.
	 */
	public String[] getCompSeqNo() {
		return compSeqNo;
	}

	/**
	 * @param compSeqNo The compSeqNo to set.
	 */
	public void setCompSeqNo(String[] compSeqNo) {
		this.compSeqNo = compSeqNo;
	}

	/**
	 * @return Returns the clauseDes.
	 */
	public String getClauseDes() {
		return clauseDes;
	}

	/**
	 * @param clauseDes The clauseDes to set.
	 */
	public void setClauseDes(String clauseDes) {
		this.clauseDes = clauseDes;
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
	 * @return Returns the hdnCharEdlNo.
	 */
	public String getHdnCharEdlNo() {
		return hdnCharEdlNo;
	}

	/**
	 * @param hdnCharEdlNo The hdnCharEdlNo to set.
	 */
	public void setHdnCharEdlNo(String hdnCharEdlNo) {
		this.hdnCharEdlNo = hdnCharEdlNo;
	}

	/**
	 * @return Returns the hdnCharRefEdlNo.
	 */
	public String getHdnCharRefEdlNo() {
		return hdnCharRefEdlNo;
	}

	/**
	 * @param hdnCharRefEdlNo The hdnCharRefEdlNo to set.
	 */
	public void setHdnCharRefEdlNo(String hdnCharRefEdlNo) {
		this.hdnCharRefEdlNo = hdnCharRefEdlNo;
	}

	/**
	 * @return Returns the charstcEdlNo.
	 */
	public String getCharstcEdlNo() {
		return charstcEdlNo;
	}

	/**
	 * @param charstcEdlNo The charstcEdlNo to set.
	 */
	public void setCharstcEdlNo(String charstcEdlNo) {
		this.charstcEdlNo = charstcEdlNo;
	}

	/**
	 * @return Returns the charstcRefEDLNo.
	 */
	public String getCharstcRefEDLNo() {
		return charstcRefEDLNo;
	}

	/**
	 * @param charstcRefEDLNo The charstcRefEDLNo to set.
	 */
	public void setCharstcRefEDLNo(String charstcRefEDLNo) {
		this.charstcRefEDLNo = charstcRefEDLNo;
	}

	public int getLinkClaSeqNo() {
		return linkClaSeqNo;
	}

	public void setLinkClaSeqNo(int linkClaSeqNo) {
		this.linkClaSeqNo = linkClaSeqNo;
	}

	public ArrayList getSectionList() {
		return sectionList;
	}

	public void setSectionList(ArrayList sectionList) {
		this.sectionList = sectionList;
	}

	public ArrayList getSubSectionList() {
		return subSectionList;
	}

	public void setSubSectionList(ArrayList subSectionList) {
		this.subSectionList = subSectionList;
	}

	public String getHdnSelSpecType() {
		return hdnSelSpecType;
	}

	public void setHdnSelSpecType(String hdnSelSpecType) {
		this.hdnSelSpecType = hdnSelSpecType;
	}

	
	
}