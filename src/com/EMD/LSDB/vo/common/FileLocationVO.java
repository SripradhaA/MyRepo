/**
 * 
 */
package com.EMD.LSDB.vo.common;

/**
 * @author VV49326
 *
 */

public class FileLocationVO extends EMDVO {
	
	public FileLocationVO() {
	}
	
	private String fileDesc;
	
	private String fileLoc;
	
	private String modifiedDate;
	
	/**
	 * Added For LSDB_CR-74[Revision Markers CR] on 08-June-09 by ps57222
	 */
	private int revViewSeqNo;
	
	public int getRevViewSeqNo() {
		return revViewSeqNo;
	}
	
	public void setRevViewSeqNo(int revViewSeqNo) {
		this.revViewSeqNo = revViewSeqNo;
	}
	
	/**
	 * @return Returns the modifiedDate.
	 */
	public String getModifiedDate() {
		return modifiedDate;
	}
	
	/**
	 * @param modifiedDate The modifiedDate to set.
	 */
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	/**
	 * @return Returns the fileDesc.
	 */
	public String getFileDesc() {
		return fileDesc;
	}
	
	/**
	 * @param fileDesc The fileDesc to set.
	 */
	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}
	
	/**
	 * @return Returns the fileLoc.
	 */
	public String getFileLoc() {
		return fileLoc;
	}
	
	/**
	 * @param fileLoc The fileLoc to set.
	 */
	public void setFileLoc(String fileLoc) {
		this.fileLoc = fileLoc;
	}
	
}
