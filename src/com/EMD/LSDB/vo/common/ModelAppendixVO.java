package com.EMD.LSDB.vo.common;

import java.io.Serializable;
import java.util.ArrayList;

public class ModelAppendixVO extends EMDVO implements Serializable {
	
	private FileVO objFileVO = new FileVO();
	
	public FileVO getFileVO() {
		
		return this.objFileVO;
	}
	
	public void setFileVO(FileVO fileVO) {
		
		this.objFileVO = fileVO;
	}
	
	private int modelSeqNo;
	
	private int imgSeqNo;
	
	private String imageName;
	
	private String imageDesc;
	//Added for CR_92
	private ArrayList mapClauses;
	private int clauseSeqNo;
	private int versionNo;
	private int subSectionSeqNo;
	
	
	/**
	 * @return Returns the imageDesc.
	 */
	public String getImageDesc() {
		return imageDesc;
	}
	
	/**
	 * @param imageDesc The imageDesc to set.
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
	 * @param imageName The imageName to set.
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
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
	
	/**
	 * @return Returns the imgSeqNo.
	 */
	public int getImgSeqNo() {
		return imgSeqNo;
	}
	
	/**
	 * @param imgSeqNo The imgSeqNo to set.
	 */
	public void setImgSeqNo(int imgSeqNo) {
		this.imgSeqNo = imgSeqNo;
	}

	public ArrayList getMapClauses() {
		return mapClauses;
	}

	public void setMapClauses(ArrayList mapClauses) {
		this.mapClauses = mapClauses;
	}

	public int getClauseSeqNo() {
		return clauseSeqNo;
	}

	public void setClauseSeqNo(int clauseSeqNo) {
		this.clauseSeqNo = clauseSeqNo;
	}

	public int getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}

	public int getSubSectionSeqNo() {
		return subSectionSeqNo;
	}

	public void setSubSectionSeqNo(int subSectionSeqNo) {
		this.subSectionSeqNo = subSectionSeqNo;
	}

	
	
}
