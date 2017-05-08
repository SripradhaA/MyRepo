/**
 * 
 */
package com.EMD.LSDB.form.MasterMaintenance;

import java.util.ArrayList;

import org.apache.struts.upload.FormFile;

import com.EMD.LSDB.form.common.EMDForm;

/**
 * @author VV49326
 *  
 */
public class ModelPerfCurveForm extends EMDForm {

	public ModelPerfCurveForm() {

	}
	public FormFile theFile;

	private ArrayList resultList;
	private ArrayList modelList;
	private ArrayList listModels;

	private String filePath;
	private String method;
	private String modelName;

	private int modelSeqNo;
	private int curveSeqNo;
	private int curSeqNo;
	private int modSeqNo;
	private int imgSeqNo;
	private int hdnDisp;
	private String selectMdl;

	/**
	 * Added for LSDB_CR-63 on 10-Dec-08 by ps57222
	 *  
	 */
	private String imageName;

	private String hdnImageName;
	
	//Added For CR_84
	private String hdnSelSpecType;
	
//	Added for CR_121 for model performance curve rearrange
	private String rowIndexList;
	private int noOfPerfCurve;

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
	 * @return Returns the listModels.
	 */
	public ArrayList getListModels() {
		return listModels;
	}
	/**
	 * @param listModels
	 *            The listModels to set.
	 */
	public void setListModels(ArrayList listModels) {
		this.listModels = listModels;
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
	 * @return Returns the modelList.
	 */
	public ArrayList getModelList() {
		return modelList;
	}
	/**
	 * @param modelList
	 *            The modelList to set.
	 */
	public void setModelList(ArrayList modelList) {
		this.modelList = modelList;
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
	 * @return Returns the selectMdl.
	 */
	public String getSelectMdl() {
		return selectMdl;
	}
	/**
	 * @param selectMdl
	 *            The selectMdl to set.
	 */
	public void setSelectMdl(String selectMdl) {
		this.selectMdl = selectMdl;
	}
	/**
	 * @return Returns the hdnDisp.
	 */
	public int getHdnDisp() {
		return hdnDisp;
	}
	/**
	 * @param hdnDisp The hdnDisp to set.
	 */
	public void setHdnDisp(int hdnDisp) {
		this.hdnDisp = hdnDisp;
	}
	/**
	 * @return Returns the hdnSelSpecType.
	 */
	public String getHdnSelSpecType() {
		return hdnSelSpecType;
	}
	/**
	 * @param hdnSelSpecType The hdnSelSpecType to set.
	 */
	public void setHdnSelSpecType(String hdnSelSpecType) {
		this.hdnSelSpecType = hdnSelSpecType;
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