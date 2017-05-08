/*
 * Created on Jun 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.vo.common;

import java.util.ArrayList;

/*******************************************************************************************
 * @author  Satyam Computer Services Ltd  
 * @version 1.0  
 * This class has the setter and getter methods for the Spec Type
 ******************************************************************************************/
/***************************************************************************
 * ------------------------------------------------------------------------------------------------------
 *     Date         version  create by          	comments                              Remarks 
 * 19/02/2010        1.0      SD41630            Added for CR_84      
 * 											 	 
 * --------------------------------------------------------------------------------------------------------
 * **************************************************************************/
public class SpecTypeVo extends EMDVO {
	
	public SpecTypeVo() {
		
	}
	
	private int spectypeSeqno=0;
	
	private String spectypename;
	
	
	//CR_84 addeed
private int screenID=0;
private String strScreenID=null;
	
	private String screenName;
	private int customerMaintID=0;
	private int distributorMaintID=0;
	private int generalArrangementMaintID=0;
	private int performanceCurveMaintID=0;
	
	
	private boolean custMaintDisPlayFlag;
	private boolean distMaintDisPlayFlag;
	private boolean genArrMaintDisPlayFlag;
	private boolean perfCurveMaintDisPlayFlag;
	private String disPlayFlag;
	private String comments;
	//private String comments;
//private String[] screenFlaglist;
	
	private String[] screenIdList;
	
	private ArrayList screenList;
	
	
	/**
	 * @return Returns the spectypename.
	 */
	public String getSpectypename() {
		return spectypename;
	}
	
	/**
	 * @param spectypename The spectypename to set.
	 */
	public void setSpectypename(String spectypename) {
		this.spectypename = spectypename;
	}
	
	/**
	 * @return Returns the spectypeSeqno.
	 */
	public int getSpectypeSeqno() {
		return spectypeSeqno;
	}

	/**
	 * @return Returns the customerMaintID.
	 */
	public int getCustomerMaintID() {
		return customerMaintID;
	}

	/**
	 * @param customerMaintID The customerMaintID to set.
	 */
	public void setCustomerMaintID(int customerMaintID) {
		this.customerMaintID = customerMaintID;
	}

	/**
	 * @return Returns the distributorMaintID.
	 */
	public int getDistributorMaintID() {
		return distributorMaintID;
	}

	/**
	 * @param distributorMaintID The distributorMaintID to set.
	 */
	public void setDistributorMaintID(int distributorMaintID) {
		this.distributorMaintID = distributorMaintID;
	}

	/**
	 * @return Returns the generalArrangementMaintID.
	 */
	public int getGeneralArrangementMaintID() {
		return generalArrangementMaintID;
	}

	/**
	 * @param generalArrangementMaintID The generalArrangementMaintID to set.
	 */
	public void setGeneralArrangementMaintID(int generalArrangementMaintID) {
		this.generalArrangementMaintID = generalArrangementMaintID;
	}

	/**
	 * @return Returns the performanceCurveMaintID.
	 */
	public int getPerformanceCurveMaintID() {
		return performanceCurveMaintID;
	}

	/**
	 * @param performanceCurveMaintID The performanceCurveMaintID to set.
	 */
	public void setPerformanceCurveMaintID(int performanceCurveMaintID) {
		this.performanceCurveMaintID = performanceCurveMaintID;
	}

	/**
	 * @return Returns the screenID.
	 */
	public int getScreenID() {
		return screenID;
	}

	/**
	 * @param screenID The screenID to set.
	 */
	public void setScreenID(int screenID) {
		this.screenID = screenID;
	}

	/**
	 * @return Returns the screenName.
	 */
	public String getScreenName() {
		return screenName;
	}

	/**
	 * @param screenName The screenName to set.
	 */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	/**
	 * @param spectypeSeqno The spectypeSeqno to set.
	 */
	public void setSpectypeSeqno(int spectypeSeqno) {
		this.spectypeSeqno = spectypeSeqno;
	}



	/**
	 * @return Returns the disPlayFlag.
	 */
	public String getDisPlayFlag() {
		return disPlayFlag;
	}

	/**
	 * @param disPlayFlag The disPlayFlag to set.
	 */
	public void setDisPlayFlag(String disPlayFlag) {
		this.disPlayFlag = disPlayFlag;
	}

	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return Returns the screenList.
	 */
	public ArrayList getScreenList() {
		return screenList;
	}

	/**
	 * @param screenList The screenList to set.
	 */
	public void setScreenList(ArrayList screenList) {
		this.screenList = screenList;
	}

	/**
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
	}
*/
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
	 * @return Returns the custMaintDisPlayFlag.
	 */
	public boolean isCustMaintDisPlayFlag() {
		return custMaintDisPlayFlag;
	}

	/**
	 * @param custMaintDisPlayFlag The custMaintDisPlayFlag to set.
	 */
	public void setCustMaintDisPlayFlag(boolean custMaintDisPlayFlag) {
		this.custMaintDisPlayFlag = custMaintDisPlayFlag;
	}

	/**
	 * @return Returns the distMaintDisPlayFlag.
	 */
	public boolean isDistMaintDisPlayFlag() {
		return distMaintDisPlayFlag;
	}

	/**
	 * @param distMaintDisPlayFlag The distMaintDisPlayFlag to set.
	 */
	public void setDistMaintDisPlayFlag(boolean distMaintDisPlayFlag) {
		this.distMaintDisPlayFlag = distMaintDisPlayFlag;
	}

	/**
	 * @return Returns the genArrMaintDisPlayFlag.
	 */
	public boolean isGenArrMaintDisPlayFlag() {
		return genArrMaintDisPlayFlag;
	}

	/**
	 * @param genArrMaintDisPlayFlag The genArrMaintDisPlayFlag to set.
	 */
	public void setGenArrMaintDisPlayFlag(boolean genArrMaintDisPlayFlag) {
		this.genArrMaintDisPlayFlag = genArrMaintDisPlayFlag;
	}

	/**
	 * @return Returns the perfCurveMaintDisPlayFlag.
	 */
	public boolean isPerfCurveMaintDisPlayFlag() {
		return perfCurveMaintDisPlayFlag;
	}

	/**
	 * @param perfCurveMaintDisPlayFlag The perfCurveMaintDisPlayFlag to set.
	 */
	public void setPerfCurveMaintDisPlayFlag(boolean perfCurveMaintDisPlayFlag) {
		this.perfCurveMaintDisPlayFlag = perfCurveMaintDisPlayFlag;
	}

	/**
	 * @return Returns the strScreenID.
	 */
	public String getStrScreenID() {
		return strScreenID;
	}

	/**
	 * @param strScreenID The strScreenID to set.
	 */
	public void setStrScreenID(String strScreenID) {
		this.strScreenID = strScreenID;
	}
		
}
