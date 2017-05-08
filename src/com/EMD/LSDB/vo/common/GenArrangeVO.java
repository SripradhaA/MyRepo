/**
 * 
 */
package com.EMD.LSDB.vo.common;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.struts.upload.FormFile;

/**
 * @author PS57222
 *
 */
public class GenArrangeVO extends EMDVO implements Serializable {
	
	private FileVO objFileVO = new FileVO();
	
	public FileVO getFileVO() {
		
		return this.objFileVO;
	}
	
	public void setFileVO(FileVO fileVO) {
		
		this.objFileVO = fileVO;
	}
	
	private int modelSeqNo;
	
	private String modelName;
	
	private int modelViewSeqNo;
	
	private String genArrangeNotes;
	//Added For CR_101
	private String mdlViewNotes;
	
	private int modelImgSeqNo;
	
	private int orderKey;
	
	private String modelView;
	
	/**
	 * Added For LSDB_CR-74[Revision Markers] on 01-June-09 by ps57222
	 */
	
	private ArrayList revCode;
	
	private int revisionInput;
	
	private ArrayList gnNotesRevCode;
	
	//Added for CR-76 VV49326 10-08-09
	private String notesMarkFlag;
	
	private String notesMarkDesc;
	
	private String imgMarkFlag;
	
	private String imgMarkDesc;
	//Added For CR_101
	private String mdlNewViewNotes;
	private String mdlNewViewName;
	
	//public FormFile mdlNewViewImage;
	
	/**
	 * @return Returns the imgMarkDesc.
	 */
	public String getImgMarkDesc() {
		return imgMarkDesc;
	}

	/**
	 * @param imgMarkDesc The imgMarkDesc to set.
	 */
	public void setImgMarkDesc(String imgMarkDesc) {
		this.imgMarkDesc = imgMarkDesc;
	}

	/**
	 * @return Returns the imgMarkFlag.
	 */
	public String getImgMarkFlag() {
		return imgMarkFlag;
	}

	/**
	 * @param imgMarkFlag The imgMarkFlag to set.
	 */
	public void setImgMarkFlag(String imgMarkFlag) {
		this.imgMarkFlag = imgMarkFlag;
	}

	/**
	 * @return Returns the notesMarkDesc.
	 */
	public String getNotesMarkDesc() {
		return notesMarkDesc;
	}

	/**
	 * @param notesMarkDesc The notesMarkDesc to set.
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
	 * @param notesMarkFlag The notesMarkFlag to set.
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
	
	/**
	 * @return Returns the modelView.
	 */
	public String getModelView() {
		return modelView;
	}
	
	/**
	 * @param modelView The modelView to set.
	 */
	public void setModelView(String modelView) {
		this.modelView = modelView;
	}
	
	/**
	 * @return Returns the modelName.
	 */
	public String getModelName() {
		return modelName;
	}
	
	/**
	 * @param modelName The modelName to set.
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
	 * @param modelSeqNo The modelSeqNo to set.
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
	 * @param modelViewSeqNo The modelViewSeqNo to set.
	 */
	public void setModelViewSeqNo(int modelViewSeqNo) {
		this.modelViewSeqNo = modelViewSeqNo;
	}
	
	/**
	 * @return Returns the genArrangeNotes.
	 */
	public String getGenArrangeNotes() {
		return genArrangeNotes;
	}
	
	/**
	 * @param genArrangeNotes The genArrangeNotes to set.
	 */
	public void setGenArrangeNotes(String genArrangeNotes) {
		this.genArrangeNotes = genArrangeNotes;
	}
	
	/**
	 * @return Returns the orderKey.
	 */
	public int getOrderKey() {
		return orderKey;
	}
	
	/**
	 * @param orderKey The orderKey to set.
	 */
	public void setOrderKey(int orderKey) {
		this.orderKey = orderKey;
	}

	public String getMdlViewNotes() {
		return mdlViewNotes;
	}

	public void setMdlViewNotes(String mdlViewNotes) {
		this.mdlViewNotes = mdlViewNotes;
	}

	public int getModelImgSeqNo() {
		return modelImgSeqNo;
	}

	public void setModelImgSeqNo(int modelImgSeqNo) {
		this.modelImgSeqNo = modelImgSeqNo;
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
	
}
