
package com.EMD.LSDB.form.SpecMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

/**
 * @author VV49326
 * 
 */
/***********************************************************************
----------------------------------------------------------------------------------------------------------
*    Date     Version   Modified by    	 Comments                              		Remarks 
* 19/01/2010    1.0      RR68151      Added claEdlChngType String for EDL      Added for CR_81
* 										Indicator Screen.  
* 													 	 
* 
* --------------------------------------------------------------------------------------------------------
**************************************************************************/

public class AcceptRejectClauseForm extends EMDForm {
	
	private int clauseSeqNo = 0;
	
	private int hdnClauseVersionNo = 0;
	
	private int hdnversionNo = 0;
	
	private String flag = null;
	
	private String reason = null;
	
	private String indFlag = null;
	
	private ArrayList clauseVersions = null;
	
	private ArrayList listModels = null;
	
	private int secSeq;
	
	private String secName;
	
	private String secCode;
	
	private int revCode;
	
	private String subSecName;
	
	private String subSecCode;
	
	private int subsecseqno;
	
	/* Added for  landing into particular sub section on  13/02/09 as per CR 71 by cm68219*/
	private int subSecSeqNo;
	
	private int modelSeqNo;
	
	private String orderNo;
	
	private int version;
	
	private int customerSeqNo;
	
	/** Included For adding CustomerSeqno when adding clauses form order level ** */
	// Added for CR-10 display of Reason based on Spec Status Code
	private int specStatusCode;
	
	//Added for CR_114
	 private int appendixImgSeqNo;
	 private int mapAppendixImg;
	//Added for CR_114 Ends Here
	
	//Added for CR-134
	 private String delModFlag;
	 private int modVersionNo;
	/**
	 * @return Returns the specStatusCode.
	 */
	public int getSpecStatusCode() {
		return specStatusCode;
	}
	
	/**
	 * @param specStatusCode
	 *            The specStatusCode to set.
	 */
	public void setSpecStatusCode(int specStatusCode) {
		this.specStatusCode = specStatusCode;
	}
	
	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
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
	 * @return Returns the revCode.
	 */
	public int getRevCode() {
		return revCode;
	}
	
	/**
	 * @param revCode
	 *            The revCode to set.
	 */
	public void setRevCode(int revCode) {
		this.revCode = revCode;
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
	 * @return Returns the secSeq.
	 */
	public int getSecSeq() {
		return secSeq;
	}
	
	/**
	 * @param secSeq
	 *            The secSeq to set.
	 */
	public void setSecSeq(int secSeq) {
		this.secSeq = secSeq;
	}
	
	/**
	 * @return Returns the clauseVersions.
	 */
	public ArrayList getClauseVersions() {
		return clauseVersions;
	}
	
	/**
	 * @param clauseVersions
	 *            The clauseVersions to set.
	 */
	public void setClauseVersions(ArrayList clauseVersions) {
		this.clauseVersions = clauseVersions;
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
	 * @return Returns the hdnClauseVersionNo.
	 */
	public int getHdnClauseVersionNo() {
		return hdnClauseVersionNo;
	}
	
	/**
	 * @param hdnClauseVersionNo
	 *            The hdnClauseVersionNo to set.
	 */
	public void setHdnClauseVersionNo(int hdnClauseVersionNo) {
		this.hdnClauseVersionNo = hdnClauseVersionNo;
	}
	
	/**
	 * @return Returns the flag.
	 */
	public String getFlag() {
		return flag;
	}
	
	/**
	 * @param flag
	 *            The flag to set.
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	/**
	 * @return Returns the hdnversionNo.
	 */
	public int getHdnversionNo() {
		return hdnversionNo;
	}
	
	/**
	 * @param hdnversionNo
	 *            The hdnversionNo to set.
	 */
	public void setHdnversionNo(int hdnversionNo) {
		this.hdnversionNo = hdnversionNo;
	}
	
	/**
	 * @return Returns the indFlag.
	 */
	public String getIndFlag() {
		return indFlag;
	}
	
	/**
	 * @param indFlag
	 *            The indFlag to set.
	 */
	public void setIndFlag(String indFlag) {
		this.indFlag = indFlag;
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
	 * @return Returns the subsecseqno.
	 */
	public int getSubsecseqno() {
		return subsecseqno;
	}
	
	/**
	 * @param subsecseqno
	 *            The subsecseqno to set.
	 */
	public void setSubsecseqno(int subsecseqno) {
		this.subsecseqno = subsecseqno;
	}
	
	/**
	 * @return Returns the customerSeqNo.
	 */
	public int getCustomerSeqNo() {
		return customerSeqNo;
	}
	
	/**
	 * @param customerSeqNo
	 *            The customerSeqNo to set.
	 */
	public void setCustomerSeqNo(int customerSeqNo) {
		this.customerSeqNo = customerSeqNo;
	}
	/* Added for  landing into particular sub section on  13/02/09 as per CR 71 by cm68219*/
	/**
	 * @param SubSecSeqNo
	 * The SubSecSeqNo to set.
	 */
	public int getSubSecSeqNo() {
		return subSecSeqNo;
	}

	public void setSubSecSeqNo(int subSecSeqNo) {
		this.subSecSeqNo = subSecSeqNo;
	}

	public int getAppendixImgSeqNo() {
		return appendixImgSeqNo;
	}

	public void setAppendixImgSeqNo(int appendixImgSeqNo) {
		this.appendixImgSeqNo = appendixImgSeqNo;
	}

	public int getMapAppendixImg() {
		return mapAppendixImg;
	}

	public void setMapAppendixImg(int mapAppendixImg) {
		this.mapAppendixImg = mapAppendixImg;
	}

	public String getDelModFlag() {
		return delModFlag;
	}

	public void setDelModFlag(String delModFlag) {
		this.delModFlag = delModFlag;
	}

	public int getModVersionNo() {
		return modVersionNo;
	}

	public void setModVersionNo(int modVersionNo) {
		this.modVersionNo = modVersionNo;
	}

	

}
