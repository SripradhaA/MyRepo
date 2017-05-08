package com.EMD.LSDB.form.MasterMaintenance;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.EMD.LSDB.form.common.EMDForm;

/*******************************************************************************************
 * @author  Satyam Computer Services Ltd  
 * @version 1.0  
 * This class has the form fields for the Distributor 
 ******************************************************************************************/

public class DistMaintForm extends EMDForm {
	
	private String distributor;
	
	private String comments;
	
	private String distributorSeqNo;
	
	private String errorMessage;
	
	private String hdnDistName;
	
	private String hdnDistComments;
	
	private boolean successMessage = false;
	
	ArrayList distList = new ArrayList();
	//Added for CR_106
	private int imageSeqNo=0;
	private String firstName;
	private String lastName;
	private String logoUpdatedDate;
	private FormFile theFile;
	private int hdnImageSeqNo=0;
	private FormFile theModifyFile;
	private String filePath;
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public FormFile getTheModifyFile() {
		return theModifyFile;
	}

	public void setTheModifyFile(FormFile theModifyFile) {
		this.theModifyFile = theModifyFile;
	}

	public int getHdnImageSeqNo() {
		return hdnImageSeqNo;
	}

	public void setHdnImageSeqNo(int hdnImageSeqNo) {
		this.hdnImageSeqNo = hdnImageSeqNo;
	}

	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public String getDistributor() {
		return distributor;
	}
	
	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}
	
	public ArrayList getDistList() {
		return distList;
	}
	
	public void setDistList(ArrayList distList) {
		this.distList = distList;
	}
	
	public String getDistributorSeqNo() {
		return distributorSeqNo;
	}
	
	public void setDistributorSeqNo(String distributorSeqNo) {
		this.distributorSeqNo = distributorSeqNo;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getHdnDistComments() {
		return hdnDistComments;
	}
	
	public void setHdnDistComments(String hdnDistComments) {
		this.hdnDistComments = hdnDistComments;
	}
	
	public String getHdnDistName() {
		return hdnDistName;
	}
	
	public void setHdnDistName(String hdnDistName) {
		this.hdnDistName = hdnDistName;
	}
	
	public boolean isSuccessMessage() {
		return successMessage;
	}
	
	public void setSuccessMessage(boolean successMessage) {
		this.successMessage = successMessage;
	}
	
	public void reset(ActionMapping objActionMapping,
			HttpServletRequest objHttpServletRequest) {
		
		this.distributor = "";
		this.comments = "";
		this.distributorSeqNo = "";
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getImageSeqNo() {
		return imageSeqNo;
	}

	public void setImageSeqNo(int imageSeqNo) {
		this.imageSeqNo = imageSeqNo;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogoUpdatedDate() {
		return logoUpdatedDate;
	}

	public void setLogoUpdatedDate(String logoUpdatedDate) {
		this.logoUpdatedDate = logoUpdatedDate;
	}

	public FormFile getTheFile() {
		return theFile;
	}

	public void setTheFile(FormFile theFile) {
		this.theFile = theFile;
	}
	
}
