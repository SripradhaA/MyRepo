/**
 * 
 */
package com.EMD.LSDB.form.MasterMaintenance;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.upload.FormFile;

import com.EMD.LSDB.form.common.EMDForm;

/**
 * @author PS57222
 * 
 */
public class ModelGenArrangeForm extends EMDForm {
	
	public ModelGenArrangeForm() {
		
	}
	
	public FormFile frontView;
	
	public FormFile topView;

	
	private String modelName;
	private int modelSeqNo;
	private String genArgmntNotes;
	//Added For CR_101
	private String mdlNewViewNotes;
	private String mdlNewViewName;
	public FormFile mdlNewViewImage;
	
	private int modelViewSeqNo;
	private String mdlViewNotes;
	private String modelView;
	private int hdSelectedMdlViewSeqNo;
	private String hdSelectedMdlViewNotes;
	private String hdSelectedMdlView;
	
	
	private ArrayList resultList;
	
	private ArrayList modelList;
	
	private String filePath;
	
	private boolean genNotesSuccesMsg;
	
	private String method;
	
	private int hdPreSelectedModel;
	
	private String hdSelectedModel;
	
	private int imageSeqNo;
	
	private String hdSelectedSpecType;
	private String hdPreSelectedSpecType;
	
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
	 * @return Returns the modelView.
	 */
	public String getModelView() {
		return modelView;
	}
	
	/**
	 * @param modelView
	 *            The modelView to set.
	 */
	public void setModelView(String modelView) {
		this.modelView = modelView;
	}
	
	/**
	 * @return Returns the genArgmntNotes.
	 */
	public String getGenArgmntNotes() {
		return genArgmntNotes;
	}
	
	/**
	 * @param genArgmntNotes
	 *            The genArgmntNotes to set.
	 */
	public void setGenArgmntNotes(String genArgmntNotes) {
		this.genArgmntNotes = genArgmntNotes;
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
	 * @return Returns the modelViewSeqNo.
	 */
	public int getModelViewSeqNo() {
		return modelViewSeqNo;
	}
	
	/**
	 * @param modelViewSeqNo
	 *            The modelViewSeqNo to set.
	 */
	public void setModelViewSeqNo(int modelViewSeqNo) {
		this.modelViewSeqNo = modelViewSeqNo;
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
	 * @return Returns the genNotesSuccesMsg.
	 */
	public boolean isGenNotesSuccesMsg() {
		return genNotesSuccesMsg;
	}
	
	/**
	 * @param genNotesSuccesMsg
	 *            The genNotesSuccesMsg to set.
	 */
	public void setGenNotesSuccesMsg(boolean genNotesSuccesMsg) {
		this.genNotesSuccesMsg = genNotesSuccesMsg;
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
	 * @return Returns the hdSelectedModel.
	 */
	/**
	 * @return Returns the hdSelectedModel.
	 */
	public String getHdSelectedModel() {
		return hdSelectedModel;
	}
	
	/**
	 * @param hdSelectedModel
	 *            The hdSelectedModel to set.
	 */
	public void setHdSelectedModel(String hdSelectedModel) {
		this.hdSelectedModel = hdSelectedModel;
	}
	
	/**
	 * @return Returns the frontView.
	 */
	public FormFile getFrontView() {
		return frontView;
	}
	
	/**
	 * @param frontView
	 *            The frontView to set.
	 */
	public void setFrontView(FormFile frontView) {
		this.frontView = frontView;
	}
	
/*	*//**
	 * @return Returns the sideView.
	 *//*
	public FormFile getSideView() {
		return sideView;
	}
	
	*//**
	 * @param sideView
	 *            The sideView to set.
	 *//*
	public void setSideView(FormFile sideView) {
		this.sideView = sideView;
	}*/
	
	/**
	 * @return Returns the topView.
	 */
	public FormFile getTopView() {
		return topView;
	}
	
	/**
	 * @param topView The topView to set.
	 */
	public void setTopView(FormFile topView) {
		this.topView = topView;
	}

	/**
	 * @return Returns the hdSelectedSpecType.
	 */
	public String getHdSelectedSpecType() {
		return hdSelectedSpecType;
	}

	/**
	 * @param hdSelectedSpecType The hdSelectedSpecType to set.
	 */
	public void setHdSelectedSpecType(String hdSelectedSpecType) {
		this.hdSelectedSpecType = hdSelectedSpecType;
	}

	/**
	 * @return Returns the hdPreSelectedSpecType.
	 */
	public String getHdPreSelectedSpecType() {
		return hdPreSelectedSpecType;
	}

	/**
	 * @param hdPreSelectedSpecType The hdPreSelectedSpecType to set.
	 */
	public void setHdPreSelectedSpecType(String hdPreSelectedSpecType) {
		this.hdPreSelectedSpecType = hdPreSelectedSpecType;
	}


	public String getMdlViewNotes() {
		return mdlViewNotes;
	}

	public void setMdlViewNotes(String mdlViewNotes) {
		this.mdlViewNotes = mdlViewNotes;
	}

	
	
	private List formFiles = new ArrayList(); 
	
	public FormFile viewImg[];

	public List getViewImg() { return this.formFiles; } 

	public void setViewImg(int iIndex, FormFile formFile){ 
	   this.formFiles.add(formFile); 
	} 

	public void setViewImg(FormFile[] viewImg) {
		this.viewImg = viewImg;
	}

	public String getHdSelectedMdlViewNotes() {
		return hdSelectedMdlViewNotes;
	}

	public void setHdSelectedMdlViewNotes(String hdSelectedMdlViewNotes) {
		this.hdSelectedMdlViewNotes = hdSelectedMdlViewNotes;
	}

	public String getHdSelectedMdlView() {
		return hdSelectedMdlView;
	}

	public void setHdSelectedMdlView(String hdSelectedMdlView) {
		this.hdSelectedMdlView = hdSelectedMdlView;
	}

	public int getHdSelectedMdlViewSeqNo() {
		return hdSelectedMdlViewSeqNo;
	}

	public void setHdSelectedMdlViewSeqNo(int hdSelectedMdlViewSeqNo) {
		this.hdSelectedMdlViewSeqNo = hdSelectedMdlViewSeqNo;
	}

	public String getMdlNewViewNotes() {
		return mdlNewViewNotes;
	}

	public void setMdlNewViewNotes(String mdlNewViewNotes) {
		this.mdlNewViewNotes = mdlNewViewNotes;
	}

	public String getMdlNewViewName() {
		return mdlNewViewName;
	}

	public void setMdlNewViewName(String mdlNewViewName) {
		this.mdlNewViewName = mdlNewViewName;
	}

	public FormFile getMdlNewViewImage() {
		return mdlNewViewImage;
	}

	public void setMdlNewViewImage(FormFile mdlNewViewImage) {
		this.mdlNewViewImage = mdlNewViewImage;
	}

	

	
}
