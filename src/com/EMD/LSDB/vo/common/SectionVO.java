/**
 * 
 */
package com.EMD.LSDB.vo.common;

import java.util.ArrayList;

import com.EMD.LSDB.common.constant.ApplicationConstants;

/**
 * @author rs37205
 * 
 */
public class SectionVO extends EMDVO {
	private int sectionSeqNo;
	
	private int subSecSeqNo;
	
	private String sectionName;
	
	private String sectionComments;
	
	private String sectionCode;
	
	private int modelSeqNo;
	
	private String modelName;
	
	private String colourFlag;
	
	private int revisionInput;
	
	private String sectionDisplay;
	
	// This is for Order Component
	private String[] componentGroupSeqNo;
	
	private String[] compSeqNo;
	/*Added for CR 75*/
	private ArrayList clauseVO;
	
	//Added for LSDB_CR-77
	private ArrayList subSec;
//	Added for CR_92 
	private String linkColourFlag;
	
	//Added For CR_99
	private int intPdfType;
	
	//Added For CR_105
	private String[] secNames;

	//Added For CR_109
	private String newSubSecFlag = ApplicationConstants.NO;
	//Added For CR_109 - Ends here
	
	//Added for CR-127
	private String rearrFlag = null;
	/**
	 * @return Returns the modelSeqNo.
	 */
	
	/**
	 * @return Returns the sectionCode.
	 */
	public String getSectionCode() {
		return sectionCode;
	}
	
	/**
	 * @param sectionCode
	 *            The sectionCode to set.
	 */
	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}
	
	/**
	 * @return Returns the sectionComments.
	 */
	public String getSectionComments() {
		return sectionComments;
	}
	
	/**
	 * @param sectionComments
	 *            The sectionComments to set.
	 */
	public void setSectionComments(String sectionComments) {
		this.sectionComments = sectionComments;
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
	 * @return Returns the colourFlag.
	 */
	public String getColourFlag() {
		return colourFlag;
	}
	
	/**
	 * @param colourFlag
	 *            The colourFlag to set.
	 */
	public void setColourFlag(String colourFlag) {
		this.colourFlag = colourFlag;
	}
	
	/**
	 * @return Returns the revisionInput.
	 */
	public int getRevisionInput() {
		return revisionInput;
	}
	
	/**
	 * @param revisionInput
	 *            The revisionInput to set.
	 */
	public void setRevisionInput(int revisionInput) {
		this.revisionInput = revisionInput;
	}
	
	/**
	 * @return Returns the componentGroupSeqNo.
	 */
	public String[] getComponentGroupSeqNo() {
		return componentGroupSeqNo;
	}
	
	/**
	 * @param componentGroupSeqNo
	 *            The componentGroupSeqNo to set.
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
	 * @param compSeqNo
	 *            The compSeqNo to set.
	 */
	public void setCompSeqNo(String[] compSeqNo) {
		this.compSeqNo = compSeqNo;
	}
	
	/**
	 * @return Returns the subSecSeqNo.
	 */
	public int getSubSecSeqNo() {
		return subSecSeqNo;
	}
	
	/**
	 * @param subSecSeqNo
	 *            The subSecSeqNo to set.
	 */
	public void setSubSecSeqNo(int subSecSeqNo) {
		this.subSecSeqNo = subSecSeqNo;
	}
	
	/**
	 * @return Returns the sectionDisplay.
	 */
	public String getSectionDisplay() {
		return sectionDisplay;
	}
	
	/**
	 * @param sectionDisplay
	 *            The sectionDisplay to set.
	 */
	public void setSectionDisplay(String sectionDisplay) {
		this.sectionDisplay = sectionDisplay;
	}

	public ArrayList getClauseVO() {
		return clauseVO;
	}

	public void setClauseVO(ArrayList clauseVO) {
		this.clauseVO = clauseVO;
	}

	/**
	 * @return Returns the subSec.
	 */
	public ArrayList getSubSec() {
		return subSec;
	}

	/**
	 * @param subSec The subSec to set.
	 */
	public void setSubSec(ArrayList subSec) {
		this.subSec = subSec;
	}

	public String getLinkColourFlag() {
		return linkColourFlag;
	}

	public void setLinkColourFlag(String linkColourFlag) {
		this.linkColourFlag = linkColourFlag;
	}

	public int getIntPdfType() {
		return intPdfType;
	}

	public void setIntPdfType(int intPdfType) {
		this.intPdfType = intPdfType;
	}

	/**
	 * @return Returns the secNames.
	 */
	public String[] getSecNames() {
		return secNames;
	}

	/**
	 * @param secNames The secNames to set.
	 */
	public void setSecNames(String[] secNames) {
		this.secNames = secNames;
	}

	/**
	 * @return Returns the newSubSecFlag.
	 */
	public String getNewSubSecFlag() {
		return newSubSecFlag;
	}

	/**
	 * @param newSubSecFlag The newSubSecFlag to set.
	 */
	public void setNewSubSecFlag(String newSubSecFlag) {
		this.newSubSecFlag = newSubSecFlag;
	}

	public String getRearrFlag() {
		return rearrFlag;
	}

	public void setRearrFlag(String rearrFlag) {
		this.rearrFlag = rearrFlag;
	}
	
	
	
}
