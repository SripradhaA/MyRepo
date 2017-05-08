/*
 * Created on Sep 2011 
 * @author ER91220
*/
package com.EMD.LSDB.vo.common;

import java.util.ArrayList;

public class RevisionChangesVO extends EMDVO {
	
	public RevisionChangesVO() {
	}

	private int revisionID;
	
	private String revisionDesc;
	
	private int revisionItemID;
	
	private String revisionItemDesc;
	
	private String revisionRelDate;
	
	private ArrayList arlRevisionItem;
	
	private ArrayList arlRevHistoryDetails;



	
	public ArrayList getArlRevisionItem() {
		return arlRevisionItem;
	}

	
	 // @param arlRevisionItem The arlRevisionItem to set.
	 
	public void setArlRevisionItem(ArrayList arlRevisionItem) {
		this.arlRevisionItem = arlRevisionItem;
	}

	/**
	 * @return Returns the revisionDesc.
	 */
	public String getRevisionDesc() {
		return revisionDesc;
	}

	/**
	 * @param revisionDesc The revisionDesc to set.
	 */
	public void setRevisionDesc(String revisionDesc) {
		this.revisionDesc = revisionDesc;
	}

	/**
	 * @return Returns the revisionID.
	 */
	public int getRevisionID() {
		return revisionID;
	}

	/**
	 * @param revisionID The revisionID to set.
	 */
	public void setRevisionID(int revisionID) {
		this.revisionID = revisionID;
	}

	/**
	 * @return Returns the revisionItemDesc.
	 */
	public String getRevisionItemDesc() {
		return revisionItemDesc;
	}

	/**
	 * @param revisionItemDesc The revisionItemDesc to set.
	 */
	public void setRevisionItemDesc(String revisionItemDesc) {
		this.revisionItemDesc = revisionItemDesc;
	}

	/**
	 * @return Returns the revisionItemID.
	 */
	public int getRevisionItemID() {
		return revisionItemID;
	}

	/**
	 * @param revisionItemID The revisionItemID to set.
	 */
	public void setRevisionItemID(int revisionItemID) {
		this.revisionItemID = revisionItemID;
	}

	/**
	 * @return Returns the arlRevHistoryDetails.
	 */
	public ArrayList getArlRevHistoryDetails() {
		return arlRevHistoryDetails;
	}

	/**
	 * @param arlRevHistoryDetails The arlRevHistoryDetails to set.
	 */
	public void setArlRevHistoryDetails(ArrayList arlRevHistoryDetails) {
		this.arlRevHistoryDetails = arlRevHistoryDetails;
	}


	/**
	 * @return Returns the revisionRelDate.
	 */
	public String getRevisionRelDate() {
		return revisionRelDate;
	}


	/**
	 * @param revisionRelDate The revisionRelDate to set.
	 */
	public void setRevisionRelDate(String revisionRelDate) {
		this.revisionRelDate = revisionRelDate;
	}
	
	
}