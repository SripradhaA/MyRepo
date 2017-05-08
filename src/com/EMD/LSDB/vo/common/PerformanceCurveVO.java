/**
 * 
 */
package com.EMD.LSDB.vo.common;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author PS57222
 * 
 */
public class PerformanceCurveVO extends EMDVO implements Serializable {
	
	private FileVO objFileVO = new FileVO();
	
	public FileVO getFileVO() {
		
		return this.objFileVO;
	}
	
	public void setFileVO(FileVO fileVO) {
		
		this.objFileVO = fileVO;
	}
	
	private int modelSeqNo;
	
	private int curveSeqNo;
	
	private int curSeqNo;
	
	private int modSeqNo;
	
	private int imgSeqNo;
	
	private int orderKey;
	
	private String modelName;
	
	/**
	 * Added For LSDB_CR-74[Revision Markers] on 01-June-09 by ps57222
	 */
	
	private ArrayList revCode;
	
	private int revisionInput;
	
	/**
	 * Added for LSDB_CR-63 on 10-Dec-08 by ps57222
	 * 
	 */
	private String imageName;
	
	//Added for CR-76
	private String sysMarkFlag;
	
	private String sysMarkDesc;
	
	//Added for CR_121 for Performance Curve Rearrange
	private int orderByCode;
	private String[] perfCurveList;
	
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

	public String getImageName() {
		return imageName;
	}
	
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	/**
	 * @return Returns the curSeqNo.
	 */
	public int getCurSeqNo() {
		return curSeqNo;
	}
	
	/**
	 * @param curSeqNo
	 *            The curSeqNo to set.
	 */
	public void setCurSeqNo(int curSeqNo) {
		this.curSeqNo = curSeqNo;
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
	 * @return Returns the modSeqNo.
	 */
	public int getModSeqNo() {
		return modSeqNo;
	}
	
	/**
	 * @param modSeqNo
	 *            The modSeqNo to set.
	 */
	public void setModSeqNo(int modSeqNo) {
		this.modSeqNo = modSeqNo;
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
	 * @return Returns the modelName.
	 */
	public String getModelName() {
		return modelName;
	}
	
	/**
	 * @param modelName
	 *            The modelName to set.
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
	/**
	 * @return Returns the modelSeqNo.
	 */
	public int getModelSeqNo() {
		return modelSeqNo;
	}
	
	/**
	 * @param modelSeqNo
	 *            The modelSeqNo to set.
	 */
	public void setModelSeqNo(int modelSeqNo) {
		this.modelSeqNo = modelSeqNo;
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
	
	public ArrayList getRevCode() {
		return revCode;
	}
	
	public void setRevCode(ArrayList revCode) {
		this.revCode = revCode;
	}
	
	public int getRevisionInput() {
		return revisionInput;
	}
	
	public void setRevisionInput(int revisionInput) {
		this.revisionInput = revisionInput;
	}

	public FileVO getObjFileVO() {
		return objFileVO;
	}

	public void setObjFileVO(FileVO objFileVO) {
		this.objFileVO = objFileVO;
	}

	public int getOrderByCode() {
		return orderByCode;
	}

	public void setOrderByCode(int orderByCode) {
		this.orderByCode = orderByCode;
	}

	public String[] getPerfCurveList() {
		return perfCurveList;
	}

	public void setPerfCurveList(String[] perfCurveList) {
		this.perfCurveList = perfCurveList;
	}
	
	
}