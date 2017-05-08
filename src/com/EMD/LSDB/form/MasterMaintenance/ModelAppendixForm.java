package com.EMD.LSDB.form.MasterMaintenance;

import java.util.ArrayList;

import org.apache.struts.upload.FormFile;

import com.EMD.LSDB.form.common.EMDForm;

public class ModelAppendixForm extends EMDForm {
	
	/**
	 * @author VV49326
	 * 
	 */
	public FormFile theFile;
	
	private ArrayList listModels;
	
	private ArrayList listImages;
	
	private int modelSeqNo;
	
	private int imgSeqNo;
	
	private int hdnModelSeqNo;
	
	private int hdnImgSeqNo;
	
	private String imageName;
	
	private String imgName;
	
	private String imgDesc;
	
	private String imageDesc;
	
	private String filePath;
	
	private String selectedModel;
	
	private String txtImageName;
	
	private String txtImageDesc;
	
	private String hdnImageName;
	
	private String hdnImageDesc;
	//Added for CR_92
	private String clauseDes;
	private int hdPreSelectedModel;
	private int subSectionSeqNo;
	private int versionNo;
	private int clauseSeqNo;
	
	// Added for CR-46 PM&I
	
	private String hdnSelSpecType;
	
	/**
	 * @return Returns the hdnSelSpecType.
	 */
	public String getHdnSelSpecType() {
		return hdnSelSpecType;
	}
	
	/**
	 * @param hdnSelSpecType
	 *            The hdnSelSpecType to set.
	 */
	public void setHdnSelSpecType(String hdnSelSpecType) {
		this.hdnSelSpecType = hdnSelSpecType;
	}
	
	/**
	 * @return Returns the hdnImageDesc.
	 */
	public String getHdnImageDesc() {
		return hdnImageDesc;
	}
	
	/**
	 * @param hdnImageDesc
	 *            The hdnImageDesc to set.
	 */
	public void setHdnImageDesc(String hdnImageDesc) {
		this.hdnImageDesc = hdnImageDesc;
	}
	
	/**
	 * @return Returns the hdnImageName.
	 */
	public String getHdnImageName() {
		return hdnImageName;
	}
	
	/**
	 * @param hdnImageName
	 *            The hdnImageName to set.
	 */
	public void setHdnImageName(String hdnImageName) {
		this.hdnImageName = hdnImageName;
	}
	
	/**
	 * @return Returns the txtImageDesc.
	 */
	public String getTxtImageDesc() {
		return txtImageDesc;
	}
	
	/**
	 * @param txtImageDesc
	 *            The txtImageDesc to set.
	 */
	public void setTxtImageDesc(String txtImageDesc) {
		this.txtImageDesc = txtImageDesc;
	}
	
	/**
	 * @return Returns the txtImageName.
	 */
	public String getTxtImageName() {
		return txtImageName;
	}
	
	/**
	 * @param txtImageName
	 *            The txtImageName to set.
	 */
	public void setTxtImageName(String txtImageName) {
		this.txtImageName = txtImageName;
	}
	
	/**
	 * @return Returns the selectedModel.
	 */
	public String getSelectedModel() {
		return selectedModel;
	}
	
	/**
	 * @param selectedModel
	 *            The selectedModel to set.
	 */
	public void setSelectedModel(String selectedModel) {
		this.selectedModel = selectedModel;
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
	 * @return Returns the listImages.
	 */
	public ArrayList getListImages() {
		return listImages;
	}
	
	/**
	 * @param listImages
	 *            The listImages to set.
	 */
	public void setListImages(ArrayList listImages) {
		this.listImages = listImages;
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
	 * @return Returns the imgDesc.
	 */
	public String getImgDesc() {
		return imgDesc;
	}
	
	/**
	 * @param imgDesc
	 *            The imgDesc to set.
	 */
	public void setImgDesc(String imgDesc) {
		this.imgDesc = imgDesc;
	}
	
	/**
	 * @return Returns the imgName.
	 */
	public String getImgName() {
		return imgName;
	}
	
	/**
	 * @param imgName
	 *            The imgName to set.
	 */
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	
	/**
	 * @return Returns the hdnModelSeqNo.
	 */
	public int getHdnModelSeqNo() {
		return hdnModelSeqNo;
	}
	
	/**
	 * @param hdnModelSeqNo
	 *            The hdnModelSeqNo to set.
	 */
	public void setHdnModelSeqNo(int hdnModelSeqNo) {
		this.hdnModelSeqNo = hdnModelSeqNo;
	}
	
	/**
	 * @return Returns the hdnImgSeqNo.
	 */
	public int getHdnImgSeqNo() {
		return hdnImgSeqNo;
	}
	
	/**
	 * @param hdnImgSeqNo
	 *            The hdnImgSeqNo to set.
	 */
	public void setHdnImgSeqNo(int hdnImgSeqNo) {
		this.hdnImgSeqNo = hdnImgSeqNo;
	}

	public String getClauseDes() {
		return clauseDes;
	}

	public void setClauseDes(String clauseDes) {
		this.clauseDes = clauseDes;
	}

	public int getHdPreSelectedModel() {
		return hdPreSelectedModel;
	}

	public void setHdPreSelectedModel(int hdPreSelectedModel) {
		this.hdPreSelectedModel = hdPreSelectedModel;
	}

	public int getSubSectionSeqNo() {
		return subSectionSeqNo;
	}

	public void setSubSectionSeqNo(int subSectionSeqNo) {
		this.subSectionSeqNo = subSectionSeqNo;
	}

	public int getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}

	public int getClauseSeqNo() {
		return clauseSeqNo;
	}

	public void setClauseSeqNo(int clauseSeqNo) {
		this.clauseSeqNo = clauseSeqNo;
	}
}
