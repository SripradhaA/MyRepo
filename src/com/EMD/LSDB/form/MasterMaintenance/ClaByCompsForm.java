/**
 * Created for CR_109 - Clauses By Components Report 
 */
package com.EMD.LSDB.form.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.form.common.EMDForm;

/**
 * @author MSAT
 * 
 */
public class ClaByCompsForm extends EMDForm {
	
	private String compGrpName;
	
	private String compName;
	
	private int compGrpSeqNo;
	
	private int componentSeqNo;
	
	private String hdnSelCompGrp;
	
	private String compgrpCat;
	
	private String hdnCompName;
	
	private ArrayList compGroupTypeList;
	
	private ArrayList compGrpList;
	
	private ArrayList compList;
	
	private ArrayList claList;
	
	//Added for CR_109 Comments - Sorting the report 
	private int sortByFlag=3;
	//Ends here
	public int getSortByFlag() {
		return sortByFlag;
	}

	public void setSortByFlag(int sortByFlag) {
		this.sortByFlag = sortByFlag;
	}
	
	

	public String getCompGrpName() {
		return compGrpName;
	}

	public void setCompGrpName(String compGrpName) {
		this.compGrpName = compGrpName;
	}

	public int getCompGrpSeqNo() {
		return compGrpSeqNo;
	}

	public void setCompGrpSeqNo(int compGrpSeqNo) {
		this.compGrpSeqNo = compGrpSeqNo;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public ArrayList getClaList() {
		return claList;
	}

	public void setClaList(ArrayList claList) {
		this.claList = claList;
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

	public String getHdnSelCompGrp() {
		return hdnSelCompGrp;
	}

	public void setHdnSelCompGrp(String hdnSelCompGrp) {
		this.hdnSelCompGrp = hdnSelCompGrp;
	}

	public String getCompgrpCat() {
		return compgrpCat;
	}

	public void setCompgrpCat(String compgrpCat) {
		this.compgrpCat = compgrpCat;
	}

	public ArrayList getCompGroupTypeList() {
		return compGroupTypeList;
	}

	public void setCompGroupTypeList(ArrayList compGroupTypeList) {
		this.compGroupTypeList = compGroupTypeList;
	}

	public int getComponentSeqNo() {
		return componentSeqNo;
	}

	public void setComponentSeqNo(int componentSeqNo) {
		this.componentSeqNo = componentSeqNo;
	}

	public String getHdnCompName() {
		return hdnCompName;
	}

	public void setHdnCompName(String hdnCompName) {
		this.hdnCompName = hdnCompName;
	}
	
	
}
	
