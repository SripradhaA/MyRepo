package com.EMD.LSDB.form.SpecMaintenance;

import java.util.ArrayList;

import org.apache.struts.upload.FormFile;

import com.EMD.LSDB.form.common.EMDForm;

/*******************************************************************************************
 * @author  Satyam Computer Services Ltd  
 * @version 1.0  
 * This class has the form fields for OrderPerfCurve
 ******************************************************************************************/
public class OrderPerfCurveForm extends EMDForm {
	
	public FormFile theFile;
	
	private ArrayList resultList;
	
	private String filePath;
	
	private String method;
	
	private int curveSeqNo;
	
	private int imgSeqNo;
	
	/**
	 * Added for LSDB_CR-63 
	 * on 11-Dec-08 by ps57222
	 * 
	 */
	private String imageName;
	
	private String hdnImageName;
	
	/**
	 * Added For LSDB_CR-74[Revision Markers] on 01-June-09 by ps57222
	 */
	private ArrayList revisionList;
	
	private int revCode;
	
	private int orderKey;
	
//	Added for CR_121 for Order performance curve rearrange
	private String rowIndexList;
	private int noOfPerfCurve;
	
	
	
	public int getOrderKey() {
		return orderKey;
	}
	
	public void setOrderKey(int orderKey) {
		this.orderKey = orderKey;
	}
	
	public int getRevCode() {
		return revCode;
	}
	
	public void setRevCode(int revCode) {
		this.revCode = revCode;
	}
	
	/**
	 * @return Returns the curveSeqNo.
	 */
	public int getCurveSeqNo() {
		return curveSeqNo;
	}
	
	/**
	 * @param curveSeqNo
	 *            The curveSeqNo to set.
	 */
	public void setCurveSeqNo(int curveSeqNo) {
		this.curveSeqNo = curveSeqNo;
	}
	
	/**
	 * @return Returns the filePath.
	 */
	public String getFilePath() {
		return filePath;
	}
	
	/**
	 * @param filePath
	 *            The filePath to set.
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	/**
	 * @return Returns the imgSeqNo.
	 */
	public int getImgSeqNo() {
		return imgSeqNo;
	}
	
	/**
	 * @param imgSeqNo
	 *            The imgSeqNo to set.
	 */
	public void setImgSeqNo(int imgSeqNo) {
		this.imgSeqNo = imgSeqNo;
	}
	
	/**
	 * @return Returns the method.
	 */
	public String getMethod() {
		return method;
	}
	
	/**
	 * @param method
	 *            The method to set.
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	
	/**
	 * @return Returns the resultList.
	 */
	public ArrayList getResultList() {
		return resultList;
	}
	
	/**
	 * @param resultList
	 *            The resultList to set.
	 */
	public void setResultList(ArrayList resultList) {
		this.resultList = resultList;
	}
	
	/**
	 * @return Returns the theFile.
	 */
	public FormFile getTheFile() {
		return theFile;
	}
	
	/**
	 * @param theFile
	 *            The theFile to set.
	 */
	public void setTheFile(FormFile theFile) {
		this.theFile = theFile;
	}
	
	public String getHdnImageName() {
		return hdnImageName;
	}
	
	public void setHdnImageName(String hdnImageName) {
		this.hdnImageName = hdnImageName;
	}
	
	public String getImageName() {
		return imageName;
	}
	
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	public ArrayList getRevisionList() {
		return revisionList;
	}
	
	public void setRevisionList(ArrayList revisionList) {
		this.revisionList = revisionList;
	}

	public int getNoOfPerfCurve() {
		return noOfPerfCurve;
	}

	public void setNoOfPerfCurve(int noOfPerfCurve) {
		this.noOfPerfCurve = noOfPerfCurve;
	}

	public String getRowIndexList() {
		return rowIndexList;
	}

	public void setRowIndexList(String rowIndexList) {
		this.rowIndexList = rowIndexList;
	}
	
	
}