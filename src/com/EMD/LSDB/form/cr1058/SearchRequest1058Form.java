package com.EMD.LSDB.form.cr1058;

import java.util.ArrayList;

import org.apache.struts.upload.FormFile;

import com.EMD.LSDB.form.common.EMDForm;

public class SearchRequest1058Form extends EMDForm {
	
	private ArrayList custNameList;
	
	private ArrayList mdlNameList;
	
	private ArrayList statusList;
	
	private ArrayList userList;
	
	private ArrayList requestList;
	
	private String orderNo;
	
	private int statusSeqNo;
	
	private int custSeqNo;
	
	private int SeqNo1058;
	
	private int mdlSeqNo;
	
	private String awApproval;
	
	private String hdnSelectedCustomer;
	
	private String hdnSelectedStatus;
	
	private String hdnSelectedModel;
	
	private String hdnorderNo;
	
	private String hdnSelectedAwApproval;
	
	private int[] modelSeqNos;
	
//	Added for CR-117
	private String number1058;
	
	private String customer;
	
	private String model;
	
	private String status;
	
	private String specRev;
	
	private FormFile uploadAttachment;
	
	private String role;
	
	private int hdnStatus;
	
	//Added for CR-120
	private String issuedBy;
	private ArrayList issuedByUserList;
	private String genDesc;
	private String actualSellPrice;
	private String hdnIssuedBy;
	
	//Added for CR_118
	private String workOrderUSD = "0.00";
	private String designHrs;
    private String draftingHrs;
    
    //Added for CR_124
    private boolean fileExist;
    
    //Added for CR_126
    private int[] statusSeqNos;
    private String editLegacyFlag;
    private ArrayList editLegacyList;
	private ArrayList unapproved1058ReportDetails;
    private ArrayList clausesAppliedtoSpec;
    
    //added for CR-127
    private ArrayList pending1058sList;
    private int orderbyFlag = 1;
	private String columnheader;
	
	//Added for CR-134
	private String fromDate;
	private String toDate;
	private ArrayList transitionSummary1058List;
	
	public String getColumnheader() {
		return columnheader;
	}

	public void setColumnheader(String columnheader) {
		this.columnheader = columnheader;
	}

	public int getOrderbyFlag() {
		return orderbyFlag;
	}

	public void setOrderbyFlag(int orderbyFlag) {
		this.orderbyFlag = orderbyFlag;
	}

	public boolean isFileExist() {
		return fileExist;
	}

	public void setFileExist(boolean fileExist) {
		this.fileExist = fileExist;
	}

	public String getDesignHrs() {
		return designHrs;
	}

	public void setDesignHrs(String designHrs) {
		this.designHrs = designHrs;
	}

	public String getDraftingHrs() {
		return draftingHrs;
	}

	public void setDraftingHrs(String draftingHrs) {
		this.draftingHrs = draftingHrs;
	}

	public String getWorkOrderUSD() {
		return workOrderUSD;
	}

	public void setWorkOrderUSD(String workOrderUSD) {
		this.workOrderUSD = workOrderUSD;
	}

	public int getCustSeqNo() {
		return custSeqNo;
	}

	public int getMdlSeqNo() {
		return mdlSeqNo;
	}

	public int getSeqNo1058() {
		return SeqNo1058;
	}

	public int getStatusSeqNo() {
		return statusSeqNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public ArrayList getCustNameList() {
		return custNameList;
	}

	public void setCustNameList(ArrayList custNameList) {
		this.custNameList = custNameList;
	}

	

	public ArrayList getMdlNameList() {
		return mdlNameList;
	}

	public void setMdlNameList(ArrayList mdlNameList) {
		this.mdlNameList = mdlNameList;
	}



	public ArrayList getStatusList() {
		return statusList;
	}

	public void setStatusList(ArrayList statusList) {
		this.statusList = statusList;
	}


	public ArrayList getUserList() {
		return userList;
	}

	public void setUserList(ArrayList userList) {
		this.userList = userList;
	}

	public ArrayList getRequestList() {
		return requestList;
	}

	public void setRequestList(ArrayList requestList) {
		this.requestList = requestList;
	}

	public void setCustSeqNo(int custSeqNo) {
		this.custSeqNo = custSeqNo;
	}

	public void setMdlSeqNo(int mdlSeqNo) {
		this.mdlSeqNo = mdlSeqNo;
	}

	public void setSeqNo1058(int seqNo1058) {
		SeqNo1058 = seqNo1058;
	}

	public void setStatusSeqNo(int statusSeqNo) {
		this.statusSeqNo = statusSeqNo;
	}

	public String getAwApproval() {
		return awApproval;
	}

	public void setAwApproval(String awApproval) {
			this.awApproval = awApproval;
	}

	public String getHdnorderNo() {
		return hdnorderNo;
	}

	public void setHdnorderNo(String hdnorderNo) {
		this.hdnorderNo = hdnorderNo;
	}

	public String getHdnSelectedAwApproval() {
		return hdnSelectedAwApproval;
	}

	public void setHdnSelectedAwApproval(String hdnSelectedAwApproval) {
		this.hdnSelectedAwApproval = hdnSelectedAwApproval;
	}

	public String getHdnSelectedCustomer() {
		return hdnSelectedCustomer;
	}

	public void setHdnSelectedCustomer(String hdnSelectedCustomer) {
		this.hdnSelectedCustomer = hdnSelectedCustomer;
	}

	public String getHdnSelectedModel() {
		return hdnSelectedModel;
	}

	public void setHdnSelectedModel(String hdnSelectedModel) {
		this.hdnSelectedModel = hdnSelectedModel;
	}

	public String getHdnSelectedStatus() {
		return hdnSelectedStatus;
	}

	public void setHdnSelectedStatus(String hdnSelectedStatus) {
		this.hdnSelectedStatus = hdnSelectedStatus;
	}

	public int[] getModelSeqNos() {
		return modelSeqNos;
	}

	public void setModelSeqNos(int[] modelSeqNos) {
		this.modelSeqNos = modelSeqNos;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getNumber1058() {
		return number1058;
	}

	public void setNumber1058(String number1058) {
		this.number1058 = number1058;
	}

	public String getSpecRev() {
		return specRev;
	}

	public void setSpecRev(String specRev) {
		this.specRev = specRev;
	}

	public FormFile getUploadAttachment() {
		return uploadAttachment;
	}

	public void setUploadAttachment(FormFile uploadAttachment) {
		this.uploadAttachment = uploadAttachment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getHdnStatus() {
		return hdnStatus;
	}

	public void setHdnStatus(int hdnStatus) {
		this.hdnStatus = hdnStatus;
	}

	public ArrayList getIssuedByUserList() {
		return issuedByUserList;
	}

	public void setIssuedByUserList(ArrayList issuedByUserList) {
		this.issuedByUserList = issuedByUserList;
	}

	public String getGenDesc() {
		return genDesc;
	}

	public void setGenDesc(String genDesc) {
		this.genDesc = genDesc;
	}

	public String getIssuedBy() {
		return issuedBy;
	}

	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}

	public String getActualSellPrice() {
		return actualSellPrice;
	}

	public void setActualSellPrice(String actualSellPrice) {
		this.actualSellPrice = actualSellPrice;
	}

	public String getHdnIssuedBy() {
		return hdnIssuedBy;
	}

	public void setHdnIssuedBy(String hdnIssuedBy) {
		this.hdnIssuedBy = hdnIssuedBy;
	}

	public int[] getStatusSeqNos() {
		return statusSeqNos;
	}

	public void setStatusSeqNos(int[] statusSeqNos) {
		this.statusSeqNos = statusSeqNos;
	}

	public String getEditLegacyFlag() {
		return editLegacyFlag;
	}

	public void setEditLegacyFlag(String editLegacyFlag) {
		this.editLegacyFlag = editLegacyFlag;
	}

	public ArrayList getEditLegacyList() {
		return editLegacyList;
	}

	public void setEditLegacyList(ArrayList editLegacyList) {
		this.editLegacyList = editLegacyList;
	}

	public ArrayList getClausesAppliedtoSpec() {
		return clausesAppliedtoSpec;
	}

	public void setClausesAppliedtoSpec(ArrayList clausesAppliedtoSpec) {
		this.clausesAppliedtoSpec = clausesAppliedtoSpec;
	}

	public ArrayList getUnapproved1058ReportDetails() {
		return unapproved1058ReportDetails;
	}

	public void setUnapproved1058ReportDetails(ArrayList unapproved1058ReportDetails) {
		this.unapproved1058ReportDetails = unapproved1058ReportDetails;
	}

	public ArrayList getPending1058sList() {
		return pending1058sList;
	}

	public void setPending1058sList(ArrayList pending1058sList) {
		this.pending1058sList = pending1058sList;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public ArrayList getTransitionSummary1058List() {
		return transitionSummary1058List;
	}

	public void setTransitionSummary1058List(ArrayList transitionSummary1058List) {
		this.transitionSummary1058List = transitionSummary1058List;
	}

	
	
    }
