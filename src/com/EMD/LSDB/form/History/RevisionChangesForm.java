/**
 * 
 */
package com.EMD.LSDB.form.History;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

/**
 * @author ER91220
 * 
 */
public class RevisionChangesForm extends EMDForm { 
		
	
	private ArrayList revisionDetails = new ArrayList();
	

	/**
	 * @return Returns the revisionDetails.
	 */
	public ArrayList getRevisionDetails() {
		return revisionDetails;
	}

	/**
	 * @param revisionDetails The revisionDetails to set.
	 */
	public void setRevisionDetails(ArrayList revisionDetails) {
		this.revisionDetails = revisionDetails;
	}

	
	
}
