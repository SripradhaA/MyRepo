/*
 * Created on May 5, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.vo.common;

import java.util.ArrayList;
 
/**
 * @author PS57222
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AppendixVO extends EMDVO{
	
	private String imageName;
	
	private String imageDesc;
	
	private int imageSeqNo;
	
	private int orderKey;
	
	private ArrayList mapClauses;
	
	private int clauseSeqNo;
	
	private int versionNo;
	
	//Added for CR-91
	private String modelSeqNo;
	
	private String modelName;
	//Ends here...
	
	//Added for CR-101
	private String salesDispFlag;
	//Ends here
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
	 * @return Returns the objFileVO.
	 */
	public FileVO getObjFileVO() {
		return objFileVO;
	}
	
	/**
	 * @param objFileVO
	 *            The objFileVO to set.
	 */
	public void setObjFileVO(FileVO objFileVO) {
		this.objFileVO = objFileVO;
	}
	
	/**
	 * @return Returns the versionNo.
	 */
	public int getVersionNo() {
		return versionNo;
	}
	
	/**
	 * @param versionNo
	 *            The versionNo to set.
	 */
	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}
	
	private FileVO objFileVO = new FileVO();
	
	public FileVO getFileVO() {
		
		return this.objFileVO;
	}
	
	public void setFileVO(FileVO fileVO) {
		
		this.objFileVO = fileVO;
	}
	
	/**
	 * @return Returns the imageDesc.
	 */
	public String getImageDesc() {
		return imageDesc;
	}
	
	/**
	 * @param imageDesc
	 *            The imageDesc to set.
	 */
	public void setImageDesc(String imageDesc) {
		this.imageDesc = imageDesc;
	}
	
	/**
	 * @return Returns the imageName.
	 */
	public String getImageName() {
		return imageName;
	}
	
	/**
	 * @param imageName
	 *            The imageName to set.
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	/**
	 * @return Returns the imageSeqNo.
	 */
	public int getImageSeqNo() {
		return imageSeqNo;
	}
	
	/**
	 * @param imageSeqNo
	 *            The imageSeqNo to set.
	 */
	public void setImageSeqNo(int imageSeqNo) {
		this.imageSeqNo = imageSeqNo;
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
	 * @return Returns the mapClauses.
	 */
	public ArrayList getMapClauses() {
		return mapClauses;
	}
	
	/**
	 * @param mapClauses
	 *            The mapClauses to set.
	 */
	public void setMapClauses(ArrayList mapClauses) {
		this.mapClauses = mapClauses;
	}
	//Added for CR-91
	/**
	 * @return Returns the modelSeqNo.
	 */
	public String getModelSeqNo() {
		return modelSeqNo;
	}
	
	/**
	 * @param modelSeqNo The modelSeqNo to set.
	 */
	public void setModelSeqNo(String modelSeqNo) {
		this.modelSeqNo = modelSeqNo;
	}
	/**
	 * @return Returns the ModelName
	 */
	public String getModelName() {
		return imageDesc;
	}
	
	/**
	 * @param ModelName
	 *            The modelName to set.
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	/**
	 * @return Returns the salesDispFlag.
	 */
	public String getSalesDispFlag() {
		return salesDispFlag;
	}

	/**
	 * @param salesDispFlag The salesDispFlag to set.
	 */
	public void setSalesDispFlag(String salesDispFlag) {
		this.salesDispFlag = salesDispFlag;
	}
	
	//Ends here...
}