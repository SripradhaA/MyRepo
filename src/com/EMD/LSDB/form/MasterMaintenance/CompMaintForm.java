package com.EMD.LSDB.form.MasterMaintenance;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.EMD.LSDB.form.common.EMDForm;

/*******************************************************************************************
 * @author  Satyam Computer Services Ltd  
 * @version 1.0  
 * This class has the form fields for the Component 
 ******************************************************************************************/
public class CompMaintForm extends EMDForm {
	
	private int compGrpSeqNo = 0;
	
	ArrayList compGrpList;
	
	ArrayList compList;
	
	private String component;
	
	private String compDesc;
	
	private String compIdentifier;
	
	private String componentSeqNo;
	
	private boolean displaySpec;
	
	public String errorMessage;
	
	private String hdnCompName;
	
	private String hdnCompDesc;
	
	private String hdnCompIdentifier;
	
	private boolean hdnDispSpec;
	
	private String hdnSelCompGrp;
	
	private boolean successMessage = false;
	
	private String compCategory;

	//Added For CR_81 on 28-Dec-09 by RR68151
	private ArrayList compGroupTypeList = new ArrayList();
	
	private String compgrpCat = null;
	
	private String hdnCompGrpCat = null;
	//Added For CR_81 on 28-Dec-09 by RR68151 - Ends here
	
//Added For CR_97 
	private String hdnSelSpecType = null;
	
	/**
	 * @return Returns the compCategory.
	 */
	public String getCompCategory() {
		return compCategory;
	}
	
	/**
	 * @param compCategory The compCategory to set.
	 */
	public void setCompCategory(String compCategory) {
		this.compCategory = compCategory;
	}
	
	public String getCompDesc() {
		return compDesc;
	}
	
	public void setCompDesc(String compDesc) {
		this.compDesc = compDesc;
	}
	
	public ArrayList getCompGrpList() {
		return compGrpList;
	}
	
	public void setCompGrpList(ArrayList compGrpList) {
		this.compGrpList = compGrpList;
	}
	
	public ArrayList getCompList() {
		return compList;
	}
	
	public void setCompList(ArrayList compList) {
		this.compList = compList;
	}
	
	public String getComponent() {
		return component;
	}
	
	public void setComponent(String component) {
		this.component = component;
	}
	
	public int getCompGrpSeqNo() {
		return compGrpSeqNo;
	}
	
	public void setCompGrpSeqNo(int compGrpSeqNo) {
		this.compGrpSeqNo = compGrpSeqNo;
	}
	
	public String getComponentSeqNo() {
		return componentSeqNo;
	}
	
	public void setComponentSeqNo(String componentSeqNo) {
		this.componentSeqNo = componentSeqNo;
	}
	
	public boolean isDisplaySpec() {
		return displaySpec;
	}
	
	public void setDisplaySpec(boolean displaySpec) {
		this.displaySpec = displaySpec;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getHdnCompDesc() {
		return hdnCompDesc;
	}
	
	public void setHdnCompDesc(String hdnCompDesc) {
		this.hdnCompDesc = hdnCompDesc;
	}
	
	public String getHdnCompName() {
		return hdnCompName;
	}
	
	public void setHdnCompName(String hdnCompName) {
		this.hdnCompName = hdnCompName;
	}
	
	public boolean isHdnDispSpec() {
		return hdnDispSpec;
	}
	
	public void setHdnDispSpec(boolean hdnDispSpec) {
		this.hdnDispSpec = hdnDispSpec;
	}
	
	public String getHdnSelCompGrp() {
		return hdnSelCompGrp;
	}
	
	public void setHdnSelCompGrp(String hdnSelCompGrp) {
		this.hdnSelCompGrp = hdnSelCompGrp;
	}
	
	public boolean isSuccessMessage() {
		return successMessage;
	}
	
	public void setSuccessMessage(boolean successMessage) {
		this.successMessage = successMessage;
	}
	
	/**
	 * @return Returns the compIdentifier.
	 */
	public String getCompIdentifier() {
		return compIdentifier;
	}
	
	/**
	 * @param compIdentifier The compIdentifier to set.
	 */
	public void setCompIdentifier(String compIdentifier) {
		this.compIdentifier = compIdentifier;
	}
	
	/**
	 * @return Returns the hdnCompIdentifier.
	 */
	public String getHdnCompIdentifier() {
		return hdnCompIdentifier;
	}
	
	/**
	 * @param hdnCompIdentifier The hdnCompIdentifier to set.
	 */
	public void setHdnCompIdentifier(String hdnCompIdentifier) {
		this.hdnCompIdentifier = hdnCompIdentifier;
	}
	
	public void reset(ActionMapping objActionMapping,
			HttpServletRequest objHttpServletRequest) {
		this.component = "";
		this.compDesc = "";
	}

	/**
	 * @return Returns the compGroupTypeList.
	 */
	public ArrayList getCompGroupTypeList() {
		return compGroupTypeList;
	}

	/**
	 * @param compGroupTypeList The compGroupTypeList to set.
	 */
	public void setCompGroupTypeList(ArrayList compGroupTypeList) {
		this.compGroupTypeList = compGroupTypeList;
	}

	/**
	 * @return Returns the compgrpCat.
	 */
	public String getCompgrpCat() {
		return compgrpCat;
	}

	/**
	 * @param compgrpCat The compgrpCat to set.
	 */
	public void setCompgrpCat(String compgrpCat) {
		this.compgrpCat = compgrpCat;
	}

	/**
	 * @return Returns the hdnCompGrpCat.
	 */
	public String getHdnCompGrpCat() {
		return hdnCompGrpCat;
	}

	/**
	 * @param hdnCompGrpCat The hdnCompGrpCat to set.
	 */
	public void setHdnCompGrpCat(String hdnCompGrpCat) {
		this.hdnCompGrpCat = hdnCompGrpCat;
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
	
}
