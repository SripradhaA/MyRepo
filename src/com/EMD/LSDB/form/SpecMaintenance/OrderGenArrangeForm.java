package com.EMD.LSDB.form.SpecMaintenance;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.upload.FormFile;

import com.EMD.LSDB.form.common.EMDForm;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the form fields for the Order GenArrange
 ******************************************************************************/
public class OrderGenArrangeForm extends EMDForm {

	private int orderKeyID;

	private String genArgmntNotes;

	// Added For CR_101
//	Added For CR_101
	private String mdlNewViewNotes;
	private String mdlNewViewName;
	public FormFile mdlNewViewImage;
	
	private int hdSelectedMdlViewSeqNo;
	private String hdSelectedMdlViewNotes;
	private String hdSelectedMdlView;
	
	private int modelViewSeqNo;

	private String modelView;

	private ArrayList resultList;

	private String filePath;

	private int imageSeqNo;

	public FormFile frontView;

	public FormFile topView;

	public FormFile sideView;

	/**
	 * Added For LSDB_CR-74[Revision Markers] on 01-June-09 by ps57222
	 */

	private ArrayList revisionList;

	private int revCode;

	private ArrayList gnNotesRevCode;

	// Added for CR-76

	private String notesMarkFlag;

	private String notesMarkDesc;

	// Added For CR_84

	private String perfCurveLinkFlag;

	/**
	 * @return Returns the notesMarkDesc.
	 */
	public String getNotesMarkDesc() {
		return notesMarkDesc;
	}

	/**
	 * @param notesMarkDesc
	 *            The notesMarkDesc to set.
	 */
	public void setNotesMarkDesc(String notesMarkDesc) {
		this.notesMarkDesc = notesMarkDesc;
	}

	/**
	 * @return Returns the notesMarkFlag.
	 */
	public String getNotesMarkFlag() {
		return notesMarkFlag;
	}

	/**
	 * @param notesMarkFlag
	 *            The notesMarkFlag to set.
	 */
	public void setNotesMarkFlag(String notesMarkFlag) {
		this.notesMarkFlag = notesMarkFlag;
	}

	public ArrayList getGnNotesRevCode() {
		return gnNotesRevCode;
	}

	public void setGnNotesRevCode(ArrayList gnNotesRevCode) {
		this.gnNotesRevCode = gnNotesRevCode;
	}

	public int getRevCode() {
		return revCode;
	}

	public void setRevCode(int revCode) {
		this.revCode = revCode;
	}

	public ArrayList getRevisionList() {
		return revisionList;
	}

	public void setRevisionList(ArrayList revisionList) {
		this.revisionList = revisionList;
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

	/**
	 * @return Returns the sideView.
	 */
	public FormFile getSideView() {
		return sideView;
	}

	/**
	 * @param sideView
	 *            The sideView to set.
	 */
	public void setSideView(FormFile sideView) {
		this.sideView = sideView;
	}

	/**
	 * @return Returns the topView.
	 */
	public FormFile getTopView() {
		return topView;
	}

	/**
	 * @param topView
	 *            The topView to set.
	 */
	public void setTopView(FormFile topView) {
		this.topView = topView;
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
	 * @return Returns the orderKeyID.
	 */
	public int getOrderKeyID() {
		return orderKeyID;
	}

	/**
	 * @param orderKeyID
	 *            The orderKeyID to set.
	 */
	public void setOrderKeyID(int orderKeyID) {
		this.orderKeyID = orderKeyID;
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
	 * @return Returns the perfCurveLinkFlag.
	 */
	public String getPerfCurveLinkFlag() {
		return perfCurveLinkFlag;
	}

	/**
	 * @param perfCurveLinkFlag
	 *            The perfCurveLinkFlag to set.
	 */
	public void setPerfCurveLinkFlag(String perfCurveLinkFlag) {
		this.perfCurveLinkFlag = perfCurveLinkFlag;
	}

	public FormFile getMdlNewViewImage() {
		return mdlNewViewImage;
	}

	public void setMdlNewViewImage(FormFile mdlNewViewImage) {
		this.mdlNewViewImage = mdlNewViewImage;
	}

	public String getMdlNewViewName() {
		return mdlNewViewName;
	}

	public void setMdlNewViewName(String mdlNewViewName) {
		this.mdlNewViewName = mdlNewViewName;
	}

	public String getMdlNewViewNotes() {
		return mdlNewViewNotes;
	}

	public void setMdlNewViewNotes(String mdlNewViewNotes) {
		this.mdlNewViewNotes = mdlNewViewNotes;
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

	public int getHdSelectedMdlViewSeqNo() {
		return hdSelectedMdlViewSeqNo;
	}

	public void setHdSelectedMdlViewSeqNo(int hdSelectedMdlViewSeqNo) {
		this.hdSelectedMdlViewSeqNo = hdSelectedMdlViewSeqNo;
	}

	public String getHdSelectedMdlView() {
		return hdSelectedMdlView;
	}

	public void setHdSelectedMdlView(String hdSelectedMdlView) {
		this.hdSelectedMdlView = hdSelectedMdlView;
	}

	public String getHdSelectedMdlViewNotes() {
		return hdSelectedMdlViewNotes;
	}

	public void setHdSelectedMdlViewNotes(String hdSelectedMdlViewNotes) {
		this.hdSelectedMdlViewNotes = hdSelectedMdlViewNotes;
	}
	

}
