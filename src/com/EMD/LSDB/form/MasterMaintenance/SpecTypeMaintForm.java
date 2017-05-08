package com.EMD.LSDB.form.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

/***************************************************************************
 * ------------------------------------------------------------------------------------------------------
 *     Date         version  create by          	comments                              Remarks 
 * 03/03/2010        1.0      SD41630            Added for CR_84      
 * 											 	 
 * --------------------------------------------------------------------------------------------------------
 * **************************************************************************/

public class SpecTypeMaintForm extends EMDForm{
	
	private String specTypeName;
	//private String comments = null;
	private String specTypeDesc = null;
	
	
	//private String[] specTypesList;
   // private String[] screenFlaglist;
    private ArrayList specTypeList;
	
	private String[] screenIdList;
	private String spectypeSeqno;
	
	private String hdnSelSpecTypeSeqNo;
	private String hdnSelSpectypeName;
	private String hdnSpecDesc;
	private boolean hdnSelCustFlag;
	private boolean hdnSelDistFlag;
	private boolean hdnSelGenArrFlag;
	private boolean hdnSelPerfCurveFlag;
	
	
	
	
	

	
	/**
	 * @return Returns the specTypeName.
	 */
	public String getSpecTypeName() {
		return specTypeName;
	}
	/**
	 * @param specTypeName The specTypeName to set.
	 */
	public void setSpecTypeName(String specTypeName) {
		this.specTypeName = specTypeName;
	}

	/**
	 * @return Returns the specTypesList.
	 *//*
	public String[] getSpecTypesList() {
		return specTypesList;
	}
	*//**
	 * @param specTypesList The specTypesList to set.
	 *//*
	public void setSpecTypesList(String[] specTypesList) {
		this.specTypesList = specTypesList;
	}
	*//**
	 * @return Returns the screenFlaglist.
	 *//*
	public String[] getScreenFlaglist() {
		return screenFlaglist;
	}
	*//**
	 * @param screenFlaglist The screenFlaglist to set.
	 *//*
	public void setScreenFlaglist(String[] screenFlaglist) {
		this.screenFlaglist = screenFlaglist;
	}*/
	/**
	 * @return Returns the screenIdList.
	 */
	public String[] getScreenIdList() {
		return screenIdList;
	}
	/**
	 * @param screenIdList The screenIdList to set.
	 */
	public void setScreenIdList(String[] screenIdList) {
		this.screenIdList = screenIdList;
	}
	
	/**
	 * @return Returns the specTypeList.
	 */
	public ArrayList getSpecTypeList() {
		return specTypeList;
	}
	/**
	 * @param specTypeList The specTypeList to set.
	 */
	public void setSpecTypeList(ArrayList specTypeList) {
		this.specTypeList = specTypeList;
	}
	
	
	/**
	 * @return Returns the specTypeDesc.
	 */
	public String getSpecTypeDesc() {
		return specTypeDesc;
	}
	/**
	 * @param specTypeDesc The specTypeDesc to set.
	 */
	public void setSpecTypeDesc(String specTypeDesc) {
		this.specTypeDesc = specTypeDesc;
	}
	
	/**
	 * @return Returns the spectypeSeqno.
	 */
	public String getSpectypeSeqno() {
		return spectypeSeqno;
	}
	/**
	 * @param spectypeSeqno The spectypeSeqno to set.
	 */
	public void setSpectypeSeqno(String spectypeSeqno) {
		this.spectypeSeqno = spectypeSeqno;
	}
	/**
	 * @return Returns the hdnSelSpectypeName.
	 */
	public String getHdnSelSpectypeName() {
		return hdnSelSpectypeName;
	}
	/**
	 * @param hdnSelSpectypeName The hdnSelSpectypeName to set.
	 */
	public void setHdnSelSpectypeName(String hdnSelSpectypeName) {
		this.hdnSelSpectypeName = hdnSelSpectypeName;
	}
	/**
	 * @return Returns the hdnSelCustFlag.
	 */
	public boolean isHdnSelCustFlag() {
		return hdnSelCustFlag;
	}
	/**
	 * @param hdnSelCustFlag The hdnSelCustFlag to set.
	 */
	public void setHdnSelCustFlag(boolean hdnSelCustFlag) {
		this.hdnSelCustFlag = hdnSelCustFlag;
	}
	/**
	 * @return Returns the hdnSelDistFlag.
	 */
	public boolean isHdnSelDistFlag() {
		return hdnSelDistFlag;
	}
	/**
	 * @param hdnSelDistFlag The hdnSelDistFlag to set.
	 */
	public void setHdnSelDistFlag(boolean hdnSelDistFlag) {
		this.hdnSelDistFlag = hdnSelDistFlag;
	}
	/**
	 * @return Returns the hdnSelGenArrFlag.
	 */
	public boolean isHdnSelGenArrFlag() {
		return hdnSelGenArrFlag;
	}
	/**
	 * @param hdnSelGenArrFlag The hdnSelGenArrFlag to set.
	 */
	public void setHdnSelGenArrFlag(boolean hdnSelGenArrFlag) {
		this.hdnSelGenArrFlag = hdnSelGenArrFlag;
	}
	/**
	 * @return Returns the hdnSelPerfCurveFlag.
	 */
	public boolean isHdnSelPerfCurveFlag() {
		return hdnSelPerfCurveFlag;
	}
	/**
	 * @param hdnSelPerfCurveFlag The hdnSelPerfCurveFlag to set.
	 */
	public void setHdnSelPerfCurveFlag(boolean hdnSelPerfCurveFlag) {
		this.hdnSelPerfCurveFlag = hdnSelPerfCurveFlag;
	}
	/**
	 * @return Returns the hdnSelSpecTypeSeqNo.
	 */
	public String getHdnSelSpecTypeSeqNo() {
		return hdnSelSpecTypeSeqNo;
	}
	/**
	 * @param hdnSelSpecTypeSeqNo The hdnSelSpecTypeSeqNo to set.
	 */
	public void setHdnSelSpecTypeSeqNo(String hdnSelSpecTypeSeqNo) {
		this.hdnSelSpecTypeSeqNo = hdnSelSpecTypeSeqNo;
	}
	/**
	 * @return Returns the hdnSpecDesc.
	 */
	public String getHdnSpecDesc() {
		return hdnSpecDesc;
	}
	/**
	 * @param hdnSpecDesc The hdnSpecDesc to set.
	 */
	public void setHdnSpecDesc(String hdnSpecDesc) {
		this.hdnSpecDesc = hdnSpecDesc;
	}
	
		

}
