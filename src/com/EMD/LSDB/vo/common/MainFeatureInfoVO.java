/**
 * 
 */
package com.EMD.LSDB.vo.common;

import java.util.ArrayList;
/**
 * @author ps57222
 * 
 */
public class MainFeatureInfoVO extends EMDVO {
	
	private int compinfoSeqNo;
	
	private String componentDesc;
	
	private String hdCompDesc;
	
	private int orderKey;
	
	private String dataLocationType;
	
	private String compDesc;
	
	// Added for LSDB_CR-62
	private int compSeqNo;
	
	private String compName;
	
	private boolean displayInSpecFlag;
	
	//Added for CR-74 01-06-09
	private int revCode;
	
	private ArrayList revMarkers;
	
	//Added for CR-76 VV49326 06-08-09
	private String sysMarkFlag;
	
	private String sysMarkDesc;
	//Added for CR_93
	private int cpyFrmCompSeqNo=0;
	private String[] compSeqNoList;
	//Added for CR_104
    private String revFlag;
	private String finalFlag;
	private String bodyCont;
	private String subject;
	
	
	
	/**
	 * @return Returns the sysMarkDesc.
	 */
	public String getSysMarkDesc() {
		return sysMarkDesc;
	}

	/**
	 * @param sysMarkDesc The sysMarkDesc to set.
	 */
	public void setSysMarkDesc(String sysMarkDesc) {
		this.sysMarkDesc = sysMarkDesc;
	}

	/**
	 * @return Returns the revCode.
	 */
	public int getRevCode() {
		return revCode;
	}

	/**
	 * @param revCode The revCode to set.
	 */
	public void setRevCode(int revCode) {
		this.revCode = revCode;
	}

	/**
	 * @return Returns the compSeqNo.
	 */
	public int getCompSeqNo() {
		return compSeqNo;
	}
	
	/**
	 * @param compSeqNo
	 *            The compSeqNo to set.
	 */
	public void setCompSeqNo(int compSeqNo) {
		this.compSeqNo = compSeqNo;
	}
	
	/**
	 * @return Returns the compName.
	 */
	public String getCompName() {
		return compName;
	}
	
	/**
	 * @param compName
	 *            The compName to set.
	 */
	public void setCompName(String compName) {
		this.compName = compName;
	}
	
	/**
	 * @return Returns the displayInSpec.
	 */
	public boolean getDisplayInSpec() {
		return displayInSpecFlag;
	}
	
	/**
	 * @param displayInSpec
	 *            The displayInSpec to set.
	 */
	public void setDisplayInSpec(boolean displayInSpec) {
		this.displayInSpecFlag = displayInSpec;
	}
	
	/**
	 * @return Returns the compDesc.
	 */
	public String getCompDesc() {
		return compDesc;
	}
	
	/**
	 * @param compDesc
	 *            The compDesc to set.
	 */
	public void setCompDesc(String compDesc) {
		this.compDesc = compDesc;
	}
	
	/**
	 * @return Returns the dataLocationType.
	 */
	public String getDataLocationType() {
		return dataLocationType;
	}
	
	/**
	 * @param dataLocationType
	 *            The dataLocationType to set.
	 */
	public void setDataLocationType(String dataLocationType) {
		this.dataLocationType = dataLocationType;
	}
	
	/**
	 * @return Returns the orderKey.
	 */
	public int getOrderKey() {
		return orderKey;
	}
	
	/**
	 * @param orderKey
	 *            The orderKey to set.
	 */
	public void setOrderKey(int orderKey) {
		this.orderKey = orderKey;
	}
	
	/**
	 * @return Returns the hdCompDesc.
	 */
	public String getHdCompDesc() {
		return hdCompDesc;
	}
	
	/**
	 * @param hdCompDesc
	 *            The hdCompDesc to set.
	 */
	public void setHdCompDesc(String hdCompDesc) {
		this.hdCompDesc = hdCompDesc;
	}
	
	/**
	 * @return Returns the compinfoSeqNo.
	 */
	public int getCompinfoSeqNo() {
		return compinfoSeqNo;
	}
	
	/**
	 * @param compinfoSeqNo
	 *            The compinfoSeqNo to set.
	 */
	public void setCompinfoSeqNo(int compinfoSeqNo) {
		this.compinfoSeqNo = compinfoSeqNo;
	}
	
	/**
	 * @return Returns the componentDesc.
	 */
	public String getComponentDesc() {
		return componentDesc;
	}
	
	/**
	 * @param componentDesc
	 *            The componentDesc to set.
	 */
	public void setComponentDesc(String componentDesc) {
		this.componentDesc = componentDesc;
	}
	/**
	 * @return Returns the revMarkers.
	 */
	public ArrayList getRevMarkers() {
		return revMarkers;
	}

	/**
	 * @param revMarkers The revMarkers to set.
	 */
	public void setRevMarkers(ArrayList revMarkers) {
		this.revMarkers = revMarkers;
	}

	/**
	 * @return Returns the sysMarkFlag.
	 */
	public String getSysMarkFlag() {
		return sysMarkFlag;
	}

	/**
	 * @param sysMarkFlag The sysMarkFlag to set.
	 */
	public void setSysMarkFlag(String sysMarkFlag) {
		this.sysMarkFlag = sysMarkFlag;
	}

	public int getCpyFrmCompSeqNo() {
		return cpyFrmCompSeqNo;
	}

	public void setCpyFrmCompSeqNo(int cpyFrmCompSeqNo) {
		this.cpyFrmCompSeqNo = cpyFrmCompSeqNo;
	}

	public String[] getCompSeqNoList() {
		return compSeqNoList;
	}

	public void setCompSeqNoList(String[] compSeqNoList) {
		this.compSeqNoList = compSeqNoList;
	}

	public String getFinalFlag() {
		return finalFlag;
	}

	public void setFinalFlag(String finalFlag) {
		this.finalFlag = finalFlag;
	}

	public String getRevFlag() {
		return revFlag;
	}

	public void setRevFlag(String revFlag) {
		this.revFlag = revFlag;
	}

	public String getBodyCont() {
		return bodyCont;
	}

	public void setBodyCont(String bodyCont) {
		this.bodyCont = bodyCont;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
}
