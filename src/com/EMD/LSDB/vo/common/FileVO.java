/**
 * 
 */
package com.EMD.LSDB.vo.common;

import java.io.InputStream;
import java.io.Serializable;

/**
 * @author PS57222
 *
 */
public class FileVO implements Serializable {
	
	private int fileLength;
	
	private InputStream fileStream;
	
	private int imageSeqNo;
	
	private int modelSeqNo;
	
	private String contentType;
	
	/**
	 * This attribute is used to store the file name .
	 */
	private String fileName;
	
	
	/**
	 * This attribute is used to store the file object.
	 */
	
	private byte[] file;
	
	/*
	 * Default Constructor
	 */
	public FileVO() {
		
	}
	
	/**
	 * This method is used to set the upload file name
	 * 
	 * @param p_fileName
	 *            String
	 * @return void
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
		
	}
	
	/**
	 * This method is used to get the upload file name
	 * 
	 * @return fileName String
	 */
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * @return Returns the m_fileByteArray ByteArray.
	 */
	
	public byte[] getUploadedFile() {
		
		return file;
		
	}
	
	/**
	 * @param p_fileByteArray
	 *            ByteArray
	 * @return void
	 */
	public void setuploadedFile(byte[] uploadedFile) {
		
		this.file = uploadedFile;
		
	}
	
	/**
	 * @return Returns the contentType.
	 */
	public String getContentType() {
		return contentType;
	}
	
	/**
	 * @param contentType The contentType to set.
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	/**
	 * @return Returns the fileLength.
	 */
	public int getFileLength() {
		return fileLength;
	}
	
	/**
	 * @param fileLength The fileLength to set.
	 */
	public void setFileLength(int fileLength) {
		this.fileLength = fileLength;
	}
	
	/**
	 * @return Returns the fileStream.
	 */
	public InputStream getFileStream() {
		return fileStream;
	}
	
	/**
	 * @param fileStream The fileStream to set.
	 */
	public void setFileStream(InputStream fileStream) {
		this.fileStream = fileStream;
	}
	
	/**
	 * @return Returns the imageSeqNo.
	 */
	public int getImageSeqNo() {
		return imageSeqNo;
	}
	
	/**
	 * @param imageSeqNo The imageSeqNo to set.
	 */
	public void setImageSeqNo(int imageSeqNo) {
		this.imageSeqNo = imageSeqNo;
	}
	
	/**
	 * @return Returns the modelSeqNo.
	 */
	public int getModelSeqNo() {
		return modelSeqNo;
	}
	
	/**
	 * @param modelSeqNo The modelSeqNo to set.
	 */
	public void setModelSeqNo(int modelSeqNo) {
		this.modelSeqNo = modelSeqNo;
	}

	
}