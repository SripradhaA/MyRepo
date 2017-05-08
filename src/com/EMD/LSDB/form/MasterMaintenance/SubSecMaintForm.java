/**
 * 
 */
package com.EMD.LSDB.form.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

/**
 * @author vv49326
 * 
 */
public class SubSecMaintForm extends EMDForm {
	
	private String subsecname = null;
	
	private String comments = null;
	
	private String Username = null;
	
	private String hdnsectionName = null;
	
	private String hdnSectionComments = null;
	
	private String hdnModel = null;
	
	private String hdnSection = null;
	
	private String errorMessage = null;
	
	private String onLoad = "-1";
	
	private String hdnsearch = null;
	
	private boolean successMessage = false;
	
	private int modelseqno = 0;
	
	private int sectionseqno = 0;
	
	private int subsecseqno;
	
	private int hdnSelectedSec = 0;
	
	private ArrayList listModels;
	
	private ArrayList listSections;
	
	private ArrayList subsections;
	
	/**
	 * Added For LSDB_CR-46(PM&I) Added on 28-Aug-08 by ps57222
	 */
	
	private String hdSelectedSpecType;
	
	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}
	
	/**
	 * @return comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	/**
	 * @return Returns the errorMessage.
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	
	/**
	 * @return errorMessage The errorMessage to set.
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	/**
	 * @return Returns the hdModel.
	 */
	public String getHdnModel() {
		return hdnModel;
	}
	
	/**
	 * @return hdModel The hdModel to set.
	 */
	public void setHdnModel(String hdnModel) {
		this.hdnModel = hdnModel;
	}
	
	/**
	 * @return Returns the hdSection.
	 */
	public String getHdnSection() {
		return hdnSection;
	}
	
	/**
	 * @return hdSection The hdSection to set.
	 */
	public void setHdnSection(String hdnSection) {
		this.hdnSection = hdnSection;
	}
	
	/**
	 * @return Returns the hdSectionComments.
	 */
	public String getHdnSectionComments() {
		return hdnSectionComments;
	}
	
	/**
	 * @return hdSectionComments The hdSectionComments.
	 */
	public void setHdnSectionComments(String hdnSectionComments) {
		this.hdnSectionComments = hdnSectionComments;
	}
	
	/**
	 * @return Returns the hdSectionName.
	 */
	public String getHdnsectionName() {
		return hdnsectionName;
	}
	
	/**
	 * @return hdSectionName The hdSectionName to set.
	 */
	public void setHdnsectionName(String hdnsectionName) {
		this.hdnsectionName = hdnsectionName;
	}
	
	/**
	 * @return Returns the listModels.
	 */
	public ArrayList getListModels() {
		return listModels;
	}
	
	/**
	 * @return listModels The listModels to set.
	 */
	public void setListModels(ArrayList listModels) {
		this.listModels = listModels;
	}
	
	/**
	 * @return Returns the listSections.
	 */
	public ArrayList getListSections() {
		return listSections;
	}
	
	/**
	 * @return listSections The listsections to set.
	 */
	public void setListSections(ArrayList listSections) {
		this.listSections = listSections;
	}
	
	/**
	 * @return Returns the modelseqno.
	 */
	public int getModelseqno() {
		return modelseqno;
	}
	
	/**
	 * @return modelseqno The modelseqno to set.
	 */
	public void setModelseqno(int modelseqno) {
		this.modelseqno = modelseqno;
	}
	
	/**
	 * @return Returns the onload.
	 */
	public String getOnLoad() {
		return onLoad;
	}
	
	/**
	 * @return onload The onload to set.
	 */
	public void setOnLoad(String onLoad) {
		this.onLoad = onLoad;
	}
	
	/**
	 * @return Returns the sectionseqno.
	 */
	public int getSectionseqno() {
		return sectionseqno;
	}
	
	/**
	 * @return sectionseqno The sectionseqno to set.
	 */
	public void setSectionseqno(int sectionseqno) {
		this.sectionseqno = sectionseqno;
	}
	
	/**
	 * @return Returns the subsecname.
	 */
	public String getSubsecname() {
		return subsecname;
	}
	
	/**
	 * @return subsecname The subsecname to set.
	 */
	public void setSubsecname(String subsecname) {
		this.subsecname = subsecname;
	}
	
	/**
	 * @return Returns the subsecseqno.
	 */
	public int getSubsecseqno() {
		return subsecseqno;
	}
	
	/**
	 * @return subsecseqno The subsecseqno to set.
	 */
	public void setSubsecseqno(int subsecseqno) {
		this.subsecseqno = subsecseqno;
	}
	
	/**
	 * @return Returns the subsections.
	 */
	public ArrayList getSubsections() {
		return subsections;
	}
	
	/**
	 * @return subsections The subsections to set.
	 */
	public void setSubsections(ArrayList subsections) {
		this.subsections = subsections;
	}
	
	/**
	 * @return Returns the successMessage.
	 */
	public boolean isSuccessMessage() {
		return successMessage;
	}
	
	/**
	 * @return successMessage The successMessage to set.
	 */
	public void setSuccessMessage(boolean successMessage) {
		this.successMessage = successMessage;
	}
	
	/**
	 * @return Returns the Username.
	 */
	public String getUsername() {
		return Username;
	}
	
	/**
	 * @return Username The Username to set.
	 */
	public void setUsername(String username) {
		Username = username;
	}
	
	/**
	 * @return Returns the hdnsearch.
	 */
	public String getHdnsearch() {
		return hdnsearch;
	}
	
	/**
	 * @return hdnsearch the hdnsearch to set.
	 */
	public void setHdnsearch(String hdnsearch) {
		this.hdnsearch = hdnsearch;
	}
	
	/**
	 * @return Returns the hdnSelectedSec.
	 */
	public int getHdnSelectedSec() {
		return hdnSelectedSec;
	}
	
	/**
	 * @return hdnSelectedSec The hdnSelectedSec to set.
	 */
	public void setHdnSelectedSec(int hdnSelectedSec) {
		this.hdnSelectedSec = hdnSelectedSec;
	}
	
	/**
	 * @return Returns the hdSelectedSpecType.
	 */
	public String getHdSelectedSpecType() {
		return hdSelectedSpecType;
	}
	
	/**
	 * @param hdSelectedSpecType
	 *            The hdSelectedSpecType to set.
	 */
	public void setHdSelectedSpecType(String hdSelectedSpecType) {
		this.hdSelectedSpecType = hdSelectedSpecType;
	}
}