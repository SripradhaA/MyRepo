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
public class RequestModelVO extends EMDVO {
	
	private int modelSeqNo;
	
	private int sectionSeqNo;
	
	private int subSectionSeqNo;
	
	// Added For CR_80 to add Specification Type Dropdown
	private int specTypeNo;
	// Added For CR_80 to add Specification Type Dropdown - Ends here
	
	/*
	 * Change for Model apply flag
	 */
	
	private boolean applyModelFlag;
	
	private String modelName;
	
	private String secCode;
	
	private String secName;
	
	private String subSecCode;
	
	private String subSecName;
	
	private ArrayList modelVO;
	
	private ArrayList sectionVO;
	
	private ArrayList subSectionVO;
	
	private RequestCompGrpVO requestCompGrpVO;
	
	private RequestCompVO requestCompVO;
	
	private ReqClauseVO reqClauseVO;
	
	private String compGrpLinkColorFlag;
	
	private String claGrpLinkColorFlag;
	
	// Added for CR-59
	private String mdlFlag;
	
	/**
	 * @return Returns the mdlFlag.
	 */
	public String getMdlFlag() {
		return mdlFlag;
	}
	
	/**
	 * @param mdlFlag
	 *            The mdlFlag to set.
	 */
	public void setMdlFlag(String mdlFlag) {
		this.mdlFlag = mdlFlag;
	}
	
	/**
	 * @return Returns the applyModelFlag.
	 */
	public boolean isApplyModelFlag() {
		return applyModelFlag;
	}
	
	/**
	 * @param applyModelFlag
	 *            The applyModelFlag to set.
	 */
	public void setApplyModelFlag(boolean applyModelFlag) {
		this.applyModelFlag = applyModelFlag;
	}
	
	/**
	 * @return Returns the reqClauseVO.
	 */
	public ReqClauseVO getReqClauseVO() {
		return reqClauseVO;
	}
	
	/**
	 * @param reqClauseVO
	 *            The reqClauseVO to set.
	 */
	public void setReqClauseVO(ReqClauseVO reqClauseVO) {
		this.reqClauseVO = reqClauseVO;
	}
	
	/**
	 * @return Returns the compGrpLinkColorFlag.
	 */
	public String getCompGrpLinkColorFlag() {
		return compGrpLinkColorFlag;
	}
	
	/**
	 * @param compGrpLinkColorFlag
	 *            The compGrpLinkColorFlag to set.
	 */
	public void setCompGrpLinkColorFlag(String compGrpLinkColorFlag) {
		this.compGrpLinkColorFlag = compGrpLinkColorFlag;
	}
	
	/**
	 * @return Returns the requestCompGrpVO.
	 */
	public RequestCompGrpVO getRequestCompGrpVO() {
		return requestCompGrpVO;
	}
	
	/**
	 * @param requestCompGrpVO
	 *            The requestCompGrpVO to set.
	 */
	public void setRequestCompGrpVO(RequestCompGrpVO requestCompGrpVO) {
		this.requestCompGrpVO = requestCompGrpVO;
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
	 * @return Returns the modelVO.
	 */
	public ArrayList getModelVO() {
		return modelVO;
	}
	
	/**
	 * @param modelVO
	 *            The modelVO to set.
	 */
	public void setModelVO(ArrayList modelVO) {
		this.modelVO = modelVO;
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
	 * @return Returns the sectionVO.
	 */
	public ArrayList getSectionVO() {
		return sectionVO;
	}
	
	/**
	 * @param sectionVO
	 *            The sectionVO to set.
	 */
	public void setSectionVO(ArrayList sectionVO) {
		this.sectionVO = sectionVO;
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
	 * @return Returns the subSectionVO.
	 */
	public ArrayList getSubSectionVO() {
		return subSectionVO;
	}
	
	/**
	 * @param subSectionVO
	 *            The subSectionVO to set.
	 */
	public void setSubSectionVO(ArrayList subSectionVO) {
		this.subSectionVO = subSectionVO;
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
	 * @return Returns the secCode.
	 */
	public String getSecCode() {
		return secCode;
	}
	
	/**
	 * @param secCode
	 *            The secCode to set.
	 */
	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}
	
	/**
	 * @return Returns the secName.
	 */
	public String getSecName() {
		return secName;
	}
	
	/**
	 * @param secName
	 *            The secName to set.
	 */
	public void setSecName(String secName) {
		this.secName = secName;
	}
	
	/**
	 * @return Returns the subSecCode.
	 */
	public String getSubSecCode() {
		return subSecCode;
	}
	
	/**
	 * @param subSecCode
	 *            The subSecCode to set.
	 */
	public void setSubSecCode(String subSecCode) {
		this.subSecCode = subSecCode;
	}
	
	/**
	 * @return Returns the subSecName.
	 */
	public String getSubSecName() {
		return subSecName;
	}
	
	/**
	 * @param subSecName
	 *            The subSecName to set.
	 */
	public void setSubSecName(String subSecName) {
		this.subSecName = subSecName;
	}
	
	/**
	 * @return Returns the requestCompVO.
	 */
	public RequestCompVO getRequestCompVO() {
		return requestCompVO;
	}
	
	/**
	 * @param requestCompVO
	 *            The requestCompVO to set.
	 */
	public void setRequestCompVO(RequestCompVO requestCompVO) {
		this.requestCompVO = requestCompVO;
	}
	
	/**
	 * @return Returns the claGrpLinkColorFlag.
	 */
	public String getClaGrpLinkColorFlag() {
		return claGrpLinkColorFlag;
	}
	
	/**
	 * @param claGrpLinkColorFlag
	 *            The claGrpLinkColorFlag to set.
	 */
	public void setClaGrpLinkColorFlag(String claGrpLinkColorFlag) {
		this.claGrpLinkColorFlag = claGrpLinkColorFlag;
	}
	
	/**
	 * @return Returns the specTypeNo.
	 */
	public int getSpecTypeNo() {
		return specTypeNo;
	}
	
	/**
	 * @param specTypeNo
	 *            The specTypeNo to set.
	 */
	public void setSpecTypeNo(int specTypeNo) {
		this.specTypeNo = specTypeNo;
	}
	
}