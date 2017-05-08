/* AK49339
 * Created on Aug 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package com.EMD.LSDB.form.MasterMaintenance;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import org.apache.struts.upload.FormFile;

import com.EMD.LSDB.form.common.EMDForm;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the form fields for the Customer
 ******************************************************************************/

public class CustMaintForm extends EMDForm {
	
	private String custName = null;
	
	private String custDesc = null;
	
	private int custTypeSeqNo = 0;
	
	private String customerSeqNo;
	
	private String hdncustName;
	private int hdnImageSeqNo=0;
	private String hdncustDesc;
	
	private int hdncustomertypeseqNo;
	
	private ArrayList custList = new ArrayList();
	
	/**
	 * Added For LSDB_CR-46(PM&I) Added on 28-Aug-08 by ps57222
	 */
	
	private String hdSelectedSpecType;
	
	private int hdPreSelectedSpecType;/*
	* This hidden variable is used to check
	* whether the selected
	* specificationtype and previous
	* selected specificationtype are same
	*/
	
	/**
	 * @return
	 */
	//Added for CR_106 Customer Logo Image Sequence No
	private int imageSeqNo=0;
	private String firstName;
	private String lastName;
	private String logoUpdatedDate;
	private FormFile theFile;
	private FormFile theModifyFile;
	private String filePath;
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public FormFile getTheFile() {
		return theFile;
	}

	public void setTheFile(FormFile theFile) {
		this.theFile = theFile;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCustDesc() {
		return custDesc;
	}
	
	/**
	 * @param custDesc
	 */
	public void setCustDesc(String custDesc) {
		this.custDesc = custDesc;
	}
	
	/**
	 * @return
	 */
	public String getCustName() {
		return custName;
	}
	
	/**
	 * @param custName
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}
	
	/**
	 * @return
	 */
	public int getCustTypeSeqNo() {
		return custTypeSeqNo;
	}
	
	/**
	 * @param custTypeSeqNo
	 */
	public void setCustTypeSeqNo(int custTypeSeqNo) {
		this.custTypeSeqNo = custTypeSeqNo;
	}
	
	/**
	 * @return
	 */
	public ArrayList getCustList() {
		return custList;
	}
	
	/**
	 * @param custList
	 */
	public void setCustList(ArrayList custList) {
		this.custList = custList;
	}
	
	/**
	 * @return
	 */
	public String getCustomerSeqNo() {
		return customerSeqNo;
	}
	
	/**
	 * @param customerSeqNo
	 */
	public void setCustomerSeqNo(String customerSeqNo) {
		this.customerSeqNo = customerSeqNo;
	}
	
	/**
	 * @return
	 */
	public String getHdncustDesc() {
		return hdncustDesc;
	}
	
	/**
	 * @param hdncustDesc
	 */
	public void setHdncustDesc(String hdncustDesc) {
		this.hdncustDesc = hdncustDesc;
	}
	
	/**
	 * @return
	 */
	public String getHdncustName() {
		return hdncustName;
	}
	
	/**
	 * @param hdncustName
	 */
	public void setHdncustName(String hdncustName) {
		this.hdncustName = hdncustName;
	}
	
	public void reset(ActionMapping objActionMapping,
			HttpServletRequest objHttpServletRequest) {
		this.customerSeqNo = "";
		this.custName = "";
		this.custDesc = "";
	}
	
	public int getHdncustomertypeseqNo() {
		return hdncustomertypeseqNo;
	}
	
	public void setHdncustomertypeseqNo(int hdncustomertypeseqNo) {
		this.hdncustomertypeseqNo = hdncustomertypeseqNo;
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
	
	/**
	 * @return Returns the hdPreSelectedSpecType.
	 */
	public int getHdPreSelectedSpecType() {
		return hdPreSelectedSpecType;
	}
	
	/**
	 * @param hdPreSelectedSpecType
	 *            The hdPreSelectedSpecType to set.
	 */
	public void setHdPreSelectedSpecType(int hdPreSelectedSpecType) {
		this.hdPreSelectedSpecType = hdPreSelectedSpecType;
	}
	/**
	 * @return ImageSeqNo
	 */
	public int getImageSeqNo() {
		return imageSeqNo;
	}
	
	
	/**
	 * @param ImageSeqNo
	 * The Image Sequence No to Set
	 */
	public void setImageSeqNo(int imageSeqNo) {
		this.imageSeqNo = imageSeqNo;
	}
//	Added for CR 106 Customer Logo Updated Date JG101007
	public String getLogoUpdatedDate() {
		return logoUpdatedDate;
	}
	public void setLogoUpdatedDate(String logoUpdatedDate){
		this.logoUpdatedDate= logoUpdatedDate;
		
	}
//	Added for CR 106 Customer Logo Updated By User Name JG101007
//	Added for CR 106 Customer Logo Updated By User Name JG101007
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName){
		this.firstName= firstName;
		
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastname(String lastName){
		this.lastName= lastName;
		
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

	

}