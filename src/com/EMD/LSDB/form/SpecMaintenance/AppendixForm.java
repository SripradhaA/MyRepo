/*
 * Created on May 5, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.form.SpecMaintenance;

import java.util.ArrayList;

import org.apache.struts.upload.FormFile;

import com.EMD.LSDB.form.common.EMDForm;

/**
 * @author PS57222
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AppendixForm extends EMDForm {
	private String imageName;
	
	private String imageDesc;
	
	private int imageSeqNo;
	
	private int orderKey;
	
	private String specStatus;
	
	private ArrayList imageList;
	
	private ArrayList sectionList;
	
	private ArrayList orderList;
	
	private String imgName;
	
	private String imgDesc;
	
	private int hdnOrderKey;
	
	private String appImageName;
	
	private String appImageDesc;
	
	private int subSectionSeqNo;
	
	private int hdPreSelectedModel;
	
	private int clauseSeqNo;
	
	private String clauseDes;
	
	private int versionNo;
	
	private String modelName;
	
	private String modelSeqNo;
	
	private int sectionSeqNo;
	
	private int subSecSeqNo;
	
	private ArrayList subSectionList;
	
	private ArrayList clauseDetail;
	
	private String orderNo;
	
	private String subSectionName;
	
	private String appendixFlag;
	
	private String toggleFlag;
	//Added For CR_84
	private String perfCurveLinkFlag;
	
	/**
	 * @return Returns the toggleFlag.
	 */
	public String getToggleFlag() {
		return toggleFlag;
	}
	
	/**
	 * @param toggleFlag
	 *            The toggleFlag to set.
	 */
	public void setToggleFlag(String toggleFlag) {
		this.toggleFlag = toggleFlag;
	}
	
	/**
	 * @return Returns the appendixFlag.
	 */
	public String getAppendixFlag() {
		return appendixFlag;
	}
	
	/**
	 * @param appendixFlag
	 *            The appendixFlag to set.
	 */
	public void setAppendixFlag(String appendixFlag) {
		this.appendixFlag = appendixFlag;
	}
	
	/**
	 * @return Returns the subSectionName.
	 */
	public String getSubSectionName() {
		return subSectionName;
	}
	
	/**
	 * @param subSectionName
	 *            The subSectionName to set.
	 */
	public void setSubSectionName(String subSectionName) {
		this.subSectionName = subSectionName;
	}
	
	/**
	 * @return Returns the subSecSeqNo.
	 */
	public int getSubSecSeqNo() {
		return subSecSeqNo;
	}
	
	/**
	 * @param subSecSeqNo
	 *            The subSecSeqNo to set.
	 */
	public void setSubSecSeqNo(int subSecSeqNo) {
		this.subSecSeqNo = subSecSeqNo;
	}
	
	/**
	 * @return Returns the subSectionList.
	 */
	public ArrayList getSubSectionList() {
		return subSectionList;
	}
	
	/**
	 * @param subSectionList
	 *            The subSectionList to set.
	 */
	public void setSubSectionList(ArrayList subSectionList) {
		this.subSectionList = subSectionList;
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
	
	/**
	 * @return Returns the clauseDes.
	 */
	public String getClauseDes() {
		return clauseDes;
	}
	
	/**
	 * @param clauseDes
	 *            The clauseDes to set.
	 */
	public void setClauseDes(String clauseDes) {
		this.clauseDes = clauseDes;
	}
	
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
	 * @return Returns the hdPreSelectedModel.
	 */
	public int getHdPreSelectedModel() {
		return hdPreSelectedModel;
	}
	
	/**
	 * @param hdPreSelectedModel
	 *            The hdPreSelectedModel to set.
	 */
	public void setHdPreSelectedModel(int hdPreSelectedModel) {
		this.hdPreSelectedModel = hdPreSelectedModel;
	}
	
	/**
	 * @return Returns the subSectionSeqNo.
	 */
	public int getSubSectionSeqNo() {
		return subSectionSeqNo;
	}
	
	/**
	 * @param subSectionSeqNo
	 *            The subSectionSeqNo to set.
	 */
	public void setSubSectionSeqNo(int subSectionSeqNo) {
		this.subSectionSeqNo = subSectionSeqNo;
	}
	
	/**
	 * @return Returns the hdnOrderKey.
	 */
	public int getHdnOrderKey() {
		return hdnOrderKey;
	}
	
	/**
	 * @param hdnOrderKey
	 *            The hdnOrderKey to set.
	 */
	public void setHdnOrderKey(int hdnOrderKey) {
		this.hdnOrderKey = hdnOrderKey;
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
	
	public FormFile imageSource;
	
	/**
	 * @return Returns the imageList.
	 */
	public ArrayList getImageList() {
		return imageList;
	}
	
	/**
	 * @param imageList
	 *            The imageList to set.
	 */
	public void setImageList(ArrayList imageList) {
		this.imageList = imageList;
	}
	
	/**
	 * @return Returns the orderList.
	 */
	public ArrayList getOrderList() {
		return orderList;
	}
	
	/**
	 * @param orderList
	 *            The orderList to set.
	 */
	public void setOrderList(ArrayList orderList) {
		this.orderList = orderList;
	}
	
	/**
	 * @return Returns the sectionList.
	 */
	public ArrayList getSectionList() {
		return sectionList;
	}
	
	/**
	 * @param sectionList
	 *            The sectionList to set.
	 */
	public void setSectionList(ArrayList sectionList) {
		this.sectionList = sectionList;
	}
	
	/**
	 * @return Returns the specStatus.
	 */
	public String getSpecStatus() {
		return specStatus;
	}
	
	/**
	 * @param specStatus
	 *            The specStatus to set.
	 */
	public void setSpecStatus(String specStatus) {
		this.specStatus = specStatus;
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
	 * @return Returns the imageSource.
	 */
	public FormFile getImageSource() {
		return imageSource;
	}
	
	/**
	 * @param imageSource
	 *            The imageSource to set.
	 */
	public void setImageSource(FormFile imageSource) {
		this.imageSource = imageSource;
	}
	
	/**
	 * @return Returns the appImageDesc.
	 */
	public String getAppImageDesc() {
		return appImageDesc;
	}
	
	/**
	 * @param appImageDesc
	 *            The appImageDesc to set.
	 */
	public void setAppImageDesc(String appImageDesc) {
		this.appImageDesc = appImageDesc;
	}
	
	/**
	 * @return Returns the appImageName.
	 */
	public String getAppImageName() {
		return appImageName;
	}
	
	/**
	 * @param appImageName
	 *            The appImageName to set.
	 */
	public void setAppImageName(String appImageName) {
		this.appImageName = appImageName;
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
	public String getModelSeqNo() {
		return modelSeqNo;
	}
	
	/**
	 * @param modelSeqNo
	 *            The modelSeqNo to set.
	 */
	public void setModelSeqNo(String modelSeqNo) {
		this.modelSeqNo = modelSeqNo;
	}
	
	/**
	 * @return Returns the sectionSeqNo.
	 */
	public int getSectionSeqNo() {
		return sectionSeqNo;
	}
	
	/**
	 * @param sectionSeqNo
	 *            The sectionSeqNo to set.
	 */
	public void setSectionSeqNo(int sectionSeqNo) {
		this.sectionSeqNo = sectionSeqNo;
	}
	
	/**
	 * @return Returns the clauseDetail.
	 */
	public ArrayList getClauseDetail() {
		return clauseDetail;
	}
	
	/**
	 * @param clauseDetail
	 *            The clauseDetail to set.
	 */
	public void setClauseDetail(ArrayList clauseDetail) {
		this.clauseDetail = clauseDetail;
	}
	
	/**
	 * @return Returns the orderNo.
	 */
	public String getOrderNo() {
		return orderNo;
	}
	
	/**
	 * @param orderNo The orderNo to set.
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * @return Returns the perfCurveLinkFlag.
	 */
	public String getPerfCurveLinkFlag() {
		return perfCurveLinkFlag;
	}

	/**
	 * @param perfCurveLinkFlag The perfCurveLinkFlag to set.
	 */
	public void setPerfCurveLinkFlag(String perfCurveLinkFlag) {
		this.perfCurveLinkFlag = perfCurveLinkFlag;
	}
}