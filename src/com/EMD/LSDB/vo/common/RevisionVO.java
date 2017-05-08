/*
 * Created on May 20, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.vo.common;

/**
 * @author ps57222
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class RevisionVO extends EMDVO {
	
	public RevisionVO() {
		
	}
	
	private String revViewName;
	
	private int revCode;
	
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
	 * @return Returns the revViewName.
	 */
	public String getRevViewName() {
		return revViewName;
	}
	
	/**
	 * @param revViewName
	 *            The revViewName to set.
	 */
	public void setRevViewName(String revViewName) {
		this.revViewName = revViewName;
	}
}