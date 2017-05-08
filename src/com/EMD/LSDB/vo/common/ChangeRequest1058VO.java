package com.EMD.LSDB.vo.common;
import java.math.BigDecimal;
import java.util.ArrayList;
public class ChangeRequest1058VO extends EMDVO{
	//Added by vb106565 for create 1058 request
	private String dataLocTypeCode;
	//Ends here vb106565
	
	//Added By vg108447 for search 1058 request
    private int statusSeqNo;
    private int custSeqNo;
    private int seqNo1058; 
    private int mdlSeqNo; 
    private String nonLsdbFlag=null;
    private String ReqId1058=null;
    private String statusDesc=null;
    private String mdlName=null;
    private String reqTypeDesc=null;
    private String categoryName=null;
    private BigDecimal actualSellPrice = new BigDecimal("0.00");
    private String custMdlName=null;
    private int orderQty;
    private String distriName=null;
    private String specRev=null;
    private String genDesc=null;
    private String custName=null;
    
    private String userName=null;
    private String contactNo=null;
    private String orderNo=null;
    private String awApproval =null;
    
	private String changeFromSpec;

	private String changeFromClaDesc;

	private String changeFromEngData;

	private String changeToSpec;

	private String changeToClaDesc;

	private String changeToEngData;
	
	private String changeClaReason;
	
	private int claChangeReqSeqNo;
	
	private String userId;
    //Ends here vg108447
    
    
    private int categorySeqNo;
    private int reqTypeSeqNo;
    private String awaitingApprvrUser;
    private String unitNumber =null;
    private String roadNumber =null;
    private String mcrNumber =null;
    private int mcrReq;
    private ArrayList attachmentsList;
    private String saveOnlyFlag = null;
    
    
    //Added by SM105772 for CR-110 Request Common Details
    private String id1058;
    private int claChngReqSeq;
    
    private String sysEnggFirstName=null;
    private String sysEnggLastName=null;
    private String sysEnggUserID=null;
    private String sysEnggStatusDesc=null;
    private int sysEnggStatusSeq;
    
    private String oprEnggFirstName=null;
    private String oprEnggLastName=null;
    private String oprEnggUserID=null;    
    private String oprEnggStatusDesc=null;
    private int oprEnggStatusSeq;
    
    private String financeFirstName=null;
    private String financeLastName=null;
    private String financeUserID=null;    
    private String financeStatusDesc=null;
    private int financeStatusSeq;
    
    private String pgmMgrFirstName=null;
    private String pgmMgrLastName=null;
    private String pgmMgrUserName=null;
    private String pgmMgrUserID=null;
    private String pgmMgrStatusDesc=null;
    private int pgmMgrStatusSeq;
    
    private String propMgrFirstName=null;
    private String propMgrLastName=null;
    private String propMgrUserName=null;
    private String propMgrUserID=null;
    private String propMgrStatusDesc=null;
    private int propMgrStatusSeq;
    
    private String sysEnggUserName=null;
    private String sysEnggDateReceived=null;
    private String sysEnggDateCompleted=null;
    private String SystemEngComment;
    private String partNoAdded;
    private String partNoDeleted; 
    private String ChangeAffectsWeight;
    private String changeAffectsClear;
    //Updated DataType from int to string CR-120 "0" validation issue
    private String designHrs;
    private String draftingHrs;
    //Updated DataType from int to string CR-120 "0" validation issue ends here
    private String drawingDueDate;
    private String completionDate;
    private BigDecimal workOrderUSD = new BigDecimal("0.00");
    private String stationAffected;
    
    private String oprEnggUserName=null;
    private String oprEnggDateReceived=null;
    private String oprEnggDateCompleted=null;
    private String operationComments; 
    private String disposExcessMaterial;
    private String supplierAffectedCost;
    private String laborImpact;
    private String recEffectivityDel;
    private BigDecimal toolingCostUSD  = new BigDecimal("0.00");
    private BigDecimal scrapCostUSD = new BigDecimal("0.00");
    private String updtUserID;
    private BigDecimal reworkCost = new BigDecimal("0.00");
    
    private String financeUserName=null;
    private String financeDateReceived=null;
    private String financeDateCompleted=null;
    private String financeComments;
    private String prodCostChange;
    private String prodCostChangeSup;
    private String progManagerDateReceived=null;
    private String progManagerDateCompleted=null;
    private String progManagerComments;
    private String propManagerDateReceived=null;
    private String propManagerDateCompleted=null;
    private String propManagerComments;
    private BigDecimal sellpriceCustomer = new BigDecimal("0.00");
    private BigDecimal markUp = new BigDecimal("0.00");
    private String customerApprovalReq;
    private int countDays;
    private String customerDecisionDate;
    private String customerDecision;
    private String custApprovalDet;
    private String customerApprovalDet;
    private int hdnsectionStatusSeq; 
    private int hdnsysCmnt;
    
    private String section=null;
    private String action=null;
    private String actionDate=null;
    private ArrayList actionList;
    private String roleID=null;
    private ArrayList sysEnggList;
    private ArrayList opRepList;
    private ArrayList finRepList;
    private ArrayList pgmMgrList;
    private ArrayList propMgrList;
    private String[] repList;
    private int sectionSeqNo;
    private int sectionStatus;
    private String reqFrom=null;
    private String reqType=null;
    private String ownerFlag=null;
    
    //For Non-Lsdb
    private String chngFromSpecSec=null;
    private String chngFromClaDesc=null;
    private String chngFromEnggData=null;
    private String chngToSpecSec=null;
    private String chngToClaDesc=null;
    private String chngToEnggData=null;
    private String nLsdbReason=null;
    
    private FileVO fileToAttach =null;
    private int attachmentToDelete ;
    private int orderKey;
    private String dataLocType;
    
    //for add clause specific
    private int clauseChangeType;
    private int subSectionSeqNo;
    private int componentGrpSeqNoinAdd;
    private int hdnParentClauseSeqNo;
    public String[] refEdlNoAdd;
    private String addClauseDescription=null;
    public String[] newEdlNoAdd;
    public int[] partOfSeqNo;
    private String[] partOf=null;
    private String dwoNoAdd;
    private String partNoAdd;
    private String priceBookNoAdd;
    public int standardEquipmentSeqNoAdd;
    public String commentsAdd;
    public String reasonAdd;
    private int specType;
    private int[] modelSeqNos; 
    
    
    //Added for CR-116
    private String lagacy1058Flag;
    private String lagacyFileLoc;
    
//	Added for CR-117
   	private String number1058;
   	private String[] seqNo;
	
	private String customer;
	
	private String model;
	
	private String uploadAttachment;
	
	private int orderbyFlag;
	
	// 	Added for CR-120	
	private String createdOn=null;
	private String issuedBy=null;
	
	//Added for CR-126
	private int[] statusSeqNos;
	private String legacy1058ID;
	private String sysEngineerName;
	private String changeTypeSeqNo;
	private String changeTypeDesc;
	private String claUpdatedToSpec;
	private String randomNo;
	private String changeFromClaNo;
	private String changeFromClaSeqNo;
	private String changeFromVerNo;
	private String changeFromEdlNo;
	private String changeFromRefEdlNo;
	private String changeFromPartOfNo;
	private String changeToClaNo;
	private String changeToClaSeqNo;
	private String changeToVerNo;
	private String changeToEdlNo;
	private String changeToRefEdlNo;
	private String changeToPartOfNo;
	private String changeFromDwoNo;
	private String changeFromPartNo;
	private String changeFromPriceBookNo;
	private String changeFromEngiComments;
	private String changeFromEquip;
	private String changeToDwoNo;
	private String changeToPartNo;
	private String changeToPriceBookNo;
	private String changeToEngiComments;
	private String changeToEquip;
	private String changeFromEngineeringData;
	
	//Added for CR-127
	private String awaitingActionOnName;
	private String lastTransc;
	private String clauseNumber;
	private String indicatorFlag;
	private String chngFrmPartOfSeqNo;
	private String chngFrmSubSecSeqNo;
	private String chngFrmPartOfLeadCompGrp;
	private String chngToPartOfSeqNo;
	private String chngToSubSecSeqNo;
	private String chngToPartOfLeadCompGrp;
	private String chngFromCompName;
	private String chngFromCompSeqNo;
	private String chngFromCompGrpSeqNo;
	private String chngFromCompGrpName;
	private String chngToCompName;
	private String chngToCompSeqNo;
	private String chngToCompGrpSeqNo;
	private String chngToCompGrpName;
	private String chngFrmTblDataCol1;
	private String chngFrmTblDataCol2;
	private String chngFrmTblDataCol3;
	private String chngFrmTblDataCol4;
	private String chngFrmTblDataCol5;
	private String chngToTblDataCol1;
	private String chngToTblDataCol2;
	private String chngToTblDataCol3;
	private String chngToTblDataCol4;
	private String chngToTblDataCol5;
	private String frmHeaderFlag;
	private String toHeaderFlag;
	private String changeFromEquipSeqNo;
	private String changeToEquipSeqNo;
 	private int[] claSeqNo;
 	private int[] claChangeType;
 	private int mdlClaSeqNo;
 	private String claExistsFlag;
 	
 	//Added for CR-130
 	private String SubSecCode;
 	private String SubSecName;
 	private int[] subSecSeqNo;
 	private String reasonSubSec;
 	private String subSecExistsFlag;
 	private int subSecChngReqSeq;
 	private String updatedToSpecFlag;
 	private int SubSecChngReqSeqNo;
 	private ArrayList claDetailList;
 	private ArrayList tableArrayData1;
 	
 	//Added for CR-134
 	private String transitionDate;
 	private String actionUser;
 	private String fromDate;
 	private String toDate;
	
	
	public String getIndicatorFlag() {
		return indicatorFlag;
	}

	public void setIndicatorFlag(String indicatorFlag) {
		this.indicatorFlag = indicatorFlag;
	}

	public String getClauseNumber() {
		return clauseNumber;
	}

	public void setClauseNumber(String clauseNumber) {
		this.clauseNumber = clauseNumber;
	}

	public String getAwaitingActionOnName() {
		return awaitingActionOnName;
	}

	public void setAwaitingActionOnName(String awaitingActionOnName) {
		this.awaitingActionOnName = awaitingActionOnName;
	}

	public String getLastTransc() {
		return lastTransc;
	}

	public void setLastTransc(String lastTransc) {
		this.lastTransc = lastTransc;
	}

	public String getMcrNumber() {
		return mcrNumber;
	}

	public void setMcrNumber(String mcrNumber) {
		this.mcrNumber = mcrNumber;
	}

	public int getMcrReq() {
		return mcrReq;
	}

	public void setMcrReq(int mcrReq) {
		this.mcrReq = mcrReq;
	}

	public String getRoadNumber() {
		return roadNumber;
	}

	public void setRoadNumber(String roadNumber) {
		this.roadNumber = roadNumber;
	}

	public String getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getCustMdlName() {
		return custMdlName;
	}

	public void setCustMdlName(String custMdlName) {
		this.custMdlName = custMdlName;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public int getCustSeqNo() {
		return custSeqNo;
	}

	public void setCustSeqNo(int custSeqNo) {
		this.custSeqNo = custSeqNo;
	}

	public String getDistriName() {
		return distriName;
	}

	public void setDistriName(String distriName) {
		this.distriName = distriName;
	}

	public String getGenDesc() {
		return genDesc;
	}

	public void setGenDesc(String genDesc) {
		this.genDesc = genDesc;
	}

	public String getMdlName() {
		return mdlName;
	}

	public void setMdlName(String mdlName) {
		this.mdlName = mdlName;
	}

	public int getMdlSeqNo() {
		return mdlSeqNo;
	}

	public void setMdlSeqNo(int mdlSeqNo) {
		this.mdlSeqNo = mdlSeqNo;
	}

	public String getNonLsdbFlag() {
		return nonLsdbFlag;
	}

	public void setNonLsdbFlag(String nonLsdbFlag) {
		this.nonLsdbFlag = nonLsdbFlag;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}

	public String getReqId1058() {
		return ReqId1058;
	}

	public void setReqId1058(String reqId1058) {
		ReqId1058 = reqId1058;
	}

	public String getReqTypeDesc() {
		return reqTypeDesc;
	}

	public void setReqTypeDesc(String reqTypeDesc) {
		this.reqTypeDesc = reqTypeDesc;
	}

	public String getSpecRev() {
		return specRev;
	}

	public void setSpecRev(String specRev) {
		this.specRev = specRev;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public int getStatusSeqNo() {
		return statusSeqNo;
	}

	public void setStatusSeqNo(int statusSeqNo) {
		this.statusSeqNo = statusSeqNo;
	}

	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAwaitingApprvrUser() {
		return awaitingApprvrUser;
	}

	public void setAwaitingApprvrUser(String awaitingApprvrUser) {
		this.awaitingApprvrUser = awaitingApprvrUser;
	}

	public int getCategorySeqNo() {
		return categorySeqNo;
	}

	public void setCategorySeqNo(int categorySeqNo) {
		this.categorySeqNo = categorySeqNo;
	}

	public int getReqTypeSeqNo() {
		return reqTypeSeqNo;
	}

	public void setReqTypeSeqNo(int reqTypeSeqNo) {
		this.reqTypeSeqNo = reqTypeSeqNo;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getActionDate() {
		return actionDate;
	}

	public void setActionDate(String actionDate) {
		this.actionDate = actionDate;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getId1058() {
		return id1058;
	}

	public void setId1058(String id1058) {
		this.id1058 = id1058;
	}

	public ArrayList getActionList() {
		return actionList;
	}

	public void setActionList(ArrayList actionList) {
		this.actionList = actionList;
	}

	public ArrayList getFinRepList() {
		return finRepList;
	}

	public void setFinRepList(ArrayList finRepList) {
		this.finRepList = finRepList;
	}

	public ArrayList getOpRepList() {
		return opRepList;
	}

	public void setOpRepList(ArrayList opRepList) {
		this.opRepList = opRepList;
	}

	public ArrayList getPgmMgrList() {
		return pgmMgrList;
	}

	public void setPgmMgrList(ArrayList pgmMgrList) {
		this.pgmMgrList = pgmMgrList;
	}

	public ArrayList getPropMgrList() {
		return propMgrList;
	}

	public void setPropMgrList(ArrayList propMgrList) {
		this.propMgrList = propMgrList;
	}

	public ArrayList getSysEnggList() {
		return sysEnggList;
	}

	public void setSysEnggList(ArrayList sysEnggList) {
		this.sysEnggList = sysEnggList;
	}
	
	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	public String[] getRepList() {
		return repList;
	}

	public void setRepList(String[] repList) {
		this.repList = repList;
	}

	public String getFinanceFirstName() {
		return financeFirstName;
	}

	public void setFinanceFirstName(String financeFirstName) {
		this.financeFirstName = financeFirstName;
	}

	public String getFinanceLastName() {
		return financeLastName;
	}

	public void setFinanceLastName(String financeLastName) {
		this.financeLastName = financeLastName;
	}

	public String getFinanceStatusDesc() {
		return financeStatusDesc;
	}

	public void setFinanceStatusDesc(String financeStatusDesc) {
		this.financeStatusDesc = financeStatusDesc;
	}

	public String getOprEnggFirstName() {
		return oprEnggFirstName;
	}

	public void setOprEnggFirstName(String oprEnggFirstName) {
		this.oprEnggFirstName = oprEnggFirstName;
	}

	public String getOprEnggLastName() {
		return oprEnggLastName;
	}

	public void setOprEnggLastName(String oprEnggLastName) {
		this.oprEnggLastName = oprEnggLastName;
	}

	public String getOprEnggStatusDesc() {
		return oprEnggStatusDesc;
	}

	public void setOprEnggStatusDesc(String oprEnggStatusDesc) {
		this.oprEnggStatusDesc = oprEnggStatusDesc;
	}

	public String getPgmMgrFirstName() {
		return pgmMgrFirstName;
	}

	public void setPgmMgrFirstName(String pgmMgrFirstName) {
		this.pgmMgrFirstName = pgmMgrFirstName;
	}

	public String getSysEnggFirstName() {
		return sysEnggFirstName;
	}

	public void setSysEnggFirstName(String sysEnggFirstName) {
		this.sysEnggFirstName = sysEnggFirstName;
	}

	public String getSysEnggLastName() {
		return sysEnggLastName;
	}

	public void setSysEnggLastName(String sysEnggLastName) {
		this.sysEnggLastName = sysEnggLastName;
	}

	public String getSysEnggStatusDesc() {
		return sysEnggStatusDesc;
	}

	public void setSysEnggStatusDesc(String sysEnggStatusDesc) {
		this.sysEnggStatusDesc = sysEnggStatusDesc;
	}

	public String getPropMgrFirstName() {
		return propMgrFirstName;
	}

	public void setPropMgrFirstName(String propMgrFirstName) {
		this.propMgrFirstName = propMgrFirstName;
	}

	public String getPropMgrLastName() {
		return propMgrLastName;
	}

	public void setPropMgrLastName(String propMgrLastName) {
		this.propMgrLastName = propMgrLastName;
	}

	public String getPropMgrStatusDesc() {
		return propMgrStatusDesc;
	}

	public void setPropMgrStatusDesc(String propMgrStatusDesc) {
		this.propMgrStatusDesc = propMgrStatusDesc;
	}

	public String getPgmMgrLastName() {
		return pgmMgrLastName;
	}

	public void setPgmMgrLastName(String pgmMgrLastName) {
		this.pgmMgrLastName = pgmMgrLastName;
	}

	public String getPgmMgrStatusDesc() {
		return pgmMgrStatusDesc;
	}

	public void setPgmMgrStatusDesc(String pgmMgrStatusDesc) {
		this.pgmMgrStatusDesc = pgmMgrStatusDesc;
	}

	public int getSectionSeqNo() {
		return sectionSeqNo;
	}

	public void setSectionSeqNo(int sectionSeqNo) {
		this.sectionSeqNo = sectionSeqNo;
	}

	public int getSectionStatus() {
		return sectionStatus;
	}

	public void setSectionStatus(int sectionStatus) {
		this.sectionStatus = sectionStatus;
	}

	public String getAwApproval() {
		return awApproval;
	}

	public void setAwApproval(String awApproval) {
		this.awApproval = awApproval;
	}

	public String getDataLocTypeCode() {
		return dataLocTypeCode;
	}

	public void setDataLocTypeCode(String dataLocTypeCode) {
		this.dataLocTypeCode = dataLocTypeCode;
	}

	public String getReqFrom() {
		return reqFrom;
	}

	public void setReqFrom(String reqFrom) {
		this.reqFrom = reqFrom;
	}

	public String getReqType() {
		return reqType;
	}

	public void setReqType(String reqType) {
		this.reqType = reqType;
	}

	public ArrayList getAttachmentsList() {
		return attachmentsList;
	}

	public void setAttachmentsList(ArrayList attachmentsList) {
		this.attachmentsList = attachmentsList;
	}

	
	public String getFinanceUserName() {
		return financeUserName;
	}

	public void setFinanceUserName(String financeUserName) {
		this.financeUserName = financeUserName;
	}

	public String getOprEnggUserName() {
		return oprEnggUserName;
	}

	public void setOprEnggUserName(String oprEnggUserName) {
		this.oprEnggUserName = oprEnggUserName;
	}


	public String getPgmMgrUserName() {
		return pgmMgrUserName;
	}

	public void setPgmMgrUserName(String pgmMgrUserName) {
		this.pgmMgrUserName = pgmMgrUserName;
	}

	public String getPropMgrUserName() {
		return propMgrUserName;
	}

	public void setPropMgrUserName(String propMgrUserName) {
		this.propMgrUserName = propMgrUserName;
	}

	public String getSysEnggUserName() {
		return sysEnggUserName;
	}

	public void setSysEnggUserName(String sysEnggUserName) {
		this.sysEnggUserName = sysEnggUserName;
	}

	public String getChangeAffectsClear() {
		return changeAffectsClear;
	}

	public void setChangeAffectsClear(String changeAffectsClear) {
		this.changeAffectsClear = changeAffectsClear;
	}

	public String getChangeAffectsWeight() {
		return ChangeAffectsWeight;
	}

	public void setChangeAffectsWeight(String changeAffectsWeight) {
		ChangeAffectsWeight = changeAffectsWeight;
	}

	public String getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}

	public String getCustApprovalDet() {
		return custApprovalDet;
	}

	public void setCustApprovalDet(String custApprovalDet) {
		this.custApprovalDet = custApprovalDet;
	}

	public String getCustomerApprovalDet() {
		return customerApprovalDet;
	}

	public void setCustomerApprovalDet(String customerApprovalDet) {
		this.customerApprovalDet = customerApprovalDet;
	}

	public String getCustomerApprovalReq() {
		return customerApprovalReq;
	}

	public void setCustomerApprovalReq(String customerApprovalReq) {
		this.customerApprovalReq = customerApprovalReq;
	}

	public String getCustomerDecision() {
		return customerDecision;
	}

	public void setCustomerDecision(String customerDecision) {
		this.customerDecision = customerDecision;
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

	public String getDisposExcessMaterial() {
		return disposExcessMaterial;
	}

	public void setDisposExcessMaterial(String disposExcessMaterial) {
		this.disposExcessMaterial = disposExcessMaterial;
	}

	public String getDrawingDueDate() {
		return drawingDueDate;
	}

	public void setDrawingDueDate(String drawingDueDate) {
		this.drawingDueDate = drawingDueDate;
	}

	public String getFinanceComments() {
		return financeComments;
	}

	public void setFinanceComments(String financeComments) {
		this.financeComments = financeComments;
	}

	
	public int getHdnsectionStatusSeq() {
		return hdnsectionStatusSeq;
	}

	public void setHdnsectionStatusSeq(int hdnsectionStatusSeq) {
		this.hdnsectionStatusSeq = hdnsectionStatusSeq;
	}

	public int getHdnsysCmnt() {
		return hdnsysCmnt;
	}

	public void setHdnsysCmnt(int hdnsysCmnt) {
		this.hdnsysCmnt = hdnsysCmnt;
	}

	public String getLaborImpact() {
		return laborImpact;
	}

	public void setLaborImpact(String laborImpact) {
		this.laborImpact = laborImpact;
	}
	
	public String getOperationComments() {
		return operationComments;
	}

	public void setOperationComments(String operationComments) {
		this.operationComments = operationComments;
	}

	
	public String getPartNoAdded() {
		return partNoAdded;
	}

	public void setPartNoAdded(String partNoAdded) {
		this.partNoAdded = partNoAdded;
	}

	public String getPartNoDeleted() {
		return partNoDeleted;
	}

	public void setPartNoDeleted(String partNoDeleted) {
		this.partNoDeleted = partNoDeleted;
	}

	public String getProgManagerComments() {
		return progManagerComments;
	}

	public void setProgManagerComments(String progManagerComments) {
		this.progManagerComments = progManagerComments;
	}

	

	public String getPropManagerComments() {
		return propManagerComments;
	}

	public void setPropManagerComments(String propManagerComments) {
		this.propManagerComments = propManagerComments;
	}

	

	public String getRecEffectivityDel() {
		return recEffectivityDel;
	}

	public void setRecEffectivityDel(String recEffectivityDel) {
		this.recEffectivityDel = recEffectivityDel;
	}

	public String getStationAffected() {
		return stationAffected;
	}

	public void setStationAffected(String stationAffected) {
		this.stationAffected = stationAffected;
	}
   
	public String getSupplierAffectedCost() {
		return supplierAffectedCost;
	}

	public void setSupplierAffectedCost(String supplierAffectedCost) {
		this.supplierAffectedCost = supplierAffectedCost;
	}

	public String getSystemEngComment() {
		return SystemEngComment;
	}

	public void setSystemEngComment(String SystemEngComment) {
		this.SystemEngComment = SystemEngComment;
	}

	public String getUpdtUserID() {
		return updtUserID;
	}

	public void setUpdtUserID(String updtUserID) {
		this.updtUserID = updtUserID;
	}

	public String getFinanceDateCompleted() {
		return financeDateCompleted;
	}

	public void setFinanceDateCompleted(String financeDateCompleted) {
		this.financeDateCompleted = financeDateCompleted;
	}

	public String getFinanceDateReceived() {
		return financeDateReceived;
	}

	public void setFinanceDateReceived(String financeDateReceived) {
		this.financeDateReceived = financeDateReceived;
	}

	public String getOprEnggDateCompleted() {
		return oprEnggDateCompleted;
	}

	public void setOprEnggDateCompleted(String oprEnggDateCompleted) {
		this.oprEnggDateCompleted = oprEnggDateCompleted;
	}

	public String getOprEnggDateReceived() {
		return oprEnggDateReceived;
	}

	public void setOprEnggDateReceived(String oprEnggDateReceived) {
		this.oprEnggDateReceived = oprEnggDateReceived;
	}

	public String getProgManagerDateCompleted() {
		return progManagerDateCompleted;
	}

	public void setProgManagerDateCompleted(String progManagerDateCompleted) {
		this.progManagerDateCompleted = progManagerDateCompleted;
	}

	public String getProgManagerDateReceived() {
		return progManagerDateReceived;
	}

	public void setProgManagerDateReceived(String progManagerDateReceived) {
		this.progManagerDateReceived = progManagerDateReceived;
	}

	public String getPropManagerDateCompleted() {
		return propManagerDateCompleted;
	}

	public void setPropManagerDateCompleted(String propManagerDateCompleted) {
		this.propManagerDateCompleted = propManagerDateCompleted;
	}

	public String getPropManagerDateReceived() {
		return propManagerDateReceived;
	}

	public void setPropManagerDateReceived(String propManagerDateReceived) {
		this.propManagerDateReceived = propManagerDateReceived;
	}

	public String getSysEnggDateCompleted() {
		return sysEnggDateCompleted;
	}

	public void setSysEnggDateCompleted(String sysEnggDateCompleted) {
		this.sysEnggDateCompleted = sysEnggDateCompleted;
	}

	public String getSysEnggDateReceived() {
		return sysEnggDateReceived;
	}

	public void setSysEnggDateReceived(String sysEnggDateReceived) {
		this.sysEnggDateReceived = sysEnggDateReceived;
	}

	public int getCountDays() {
		return countDays;
	}

	public void setCountDays(int countDays) {
		this.countDays = countDays;
	}

	public String getCustomerDecisionDate() {
		return customerDecisionDate;
	}

	public void setCustomerDecisionDate(String customerDecisionDate) {
		this.customerDecisionDate = customerDecisionDate;
	}

	public String getProdCostChange() {
		return prodCostChange;
	}

	public void setProdCostChange(String prodCostChange) {
		this.prodCostChange = prodCostChange;
	}

	public String getProdCostChangeSup() {
		return prodCostChangeSup;
	}

	public void setProdCostChangeSup(String prodCostChangeSup) {
		this.prodCostChangeSup = prodCostChangeSup;
	}

	public BigDecimal getActualSellPrice() {
		return actualSellPrice;
	}

	public void setActualSellPrice(BigDecimal actualSellPrice) {
		this.actualSellPrice = actualSellPrice;
	}

	public BigDecimal getMarkUp() {
		return markUp;
	}

	public void setMarkUp(BigDecimal markUp) {
		this.markUp = markUp;
	}

	public BigDecimal getReworkCost() {
		return reworkCost;
	}

	public void setReworkCost(BigDecimal reworkCost) {
		this.reworkCost = reworkCost;
	}

	public BigDecimal getScrapCostUSD() {
		return scrapCostUSD;
	}

	public void setScrapCostUSD(BigDecimal scrapCostUSD) {
		this.scrapCostUSD = scrapCostUSD;
	}

	public BigDecimal getSellpriceCustomer() {
		return sellpriceCustomer;
	}

	public void setSellpriceCustomer(BigDecimal sellpriceCustomer) {
		this.sellpriceCustomer = sellpriceCustomer;
	}

	public BigDecimal getToolingCostUSD() {
		return toolingCostUSD;
	}

	public void setToolingCostUSD(BigDecimal toolingCostUSD) {
		this.toolingCostUSD = toolingCostUSD;
	}

	public BigDecimal getWorkOrderUSD() {
		return workOrderUSD;
	}

	public void setWorkOrderUSD(BigDecimal workOrderUSD) {
		this.workOrderUSD = workOrderUSD;
	}

	public int getClaChngReqSeq() {
		return claChngReqSeq;
	}

	public void setClaChngReqSeq(int claChngReqSeq) {
		this.claChngReqSeq = claChngReqSeq;
	}

	public String getChngFromClaDesc() {
		return chngFromClaDesc;
	}

	public void setChngFromClaDesc(String chngFromClaDesc) {
		this.chngFromClaDesc = chngFromClaDesc;
	}

	public String getChngFromEnggData() {
		return chngFromEnggData;
	}

	public void setChngFromEnggData(String chngFromEnggData) {
		this.chngFromEnggData = chngFromEnggData;
	}

	public String getChngFromSpecSec() {
		return chngFromSpecSec;
	}

	public void setChngFromSpecSec(String chngFromSpecSec) {
		this.chngFromSpecSec = chngFromSpecSec;
	}

	public String getChngToClaDesc() {
		return chngToClaDesc;
	}

	public void setChngToClaDesc(String chngToClaDesc) {
		this.chngToClaDesc = chngToClaDesc;
	}

	public String getChngToEnggData() {
		return chngToEnggData;
	}

	public void setChngToEnggData(String chngToEnggData) {
		this.chngToEnggData = chngToEnggData;
	}

	public String getChngToSpecSec() {
		return chngToSpecSec;
	}

	public void setChngToSpecSec(String chngToSpecSec) {
		this.chngToSpecSec = chngToSpecSec;
	}

	public String getNLsdbReason() {
		return nLsdbReason;
	}

	public void setNLsdbReason(String lsdbReason) {
		nLsdbReason = lsdbReason;
	}

	public String getChangeClaReason() {
		return changeClaReason;
	}

	public void setChangeClaReason(String changeClaReason) {
		this.changeClaReason = changeClaReason;
	}

	public String getChangeFromClaDesc() {
		return changeFromClaDesc;
	}

	public void setChangeFromClaDesc(String changeFromClaDesc) {
		this.changeFromClaDesc = changeFromClaDesc;
	}

	public String getChangeFromEngData() {
		return changeFromEngData;
	}

	public void setChangeFromEngData(String changeFromEngData) {
		this.changeFromEngData = changeFromEngData;
	}

	public String getChangeFromSpec() {
		return changeFromSpec;
	}

	public void setChangeFromSpec(String changeFromSpec) {
		this.changeFromSpec = changeFromSpec;
	}

	public String getChangeToClaDesc() {
		return changeToClaDesc;
	}

	public void setChangeToClaDesc(String changeToClaDesc) {
		this.changeToClaDesc = changeToClaDesc;
	}

	public String getChangeToEngData() {
		return changeToEngData;
	}

	public void setChangeToEngData(String changeToEngData) {
		this.changeToEngData = changeToEngData;
	}

	public String getChangeToSpec() {
		return changeToSpec;
	}

	public void setChangeToSpec(String changeToSpec) {
		this.changeToSpec = changeToSpec;
	}

	public int getClaChangeReqSeqNo() {
		return claChangeReqSeqNo;
	}

	public void setClaChangeReqSeqNo(int claChangeReqSeqNo) {
		this.claChangeReqSeqNo = claChangeReqSeqNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOwnerFlag() {
		return ownerFlag;
	}

	public void setOwnerFlag(String ownerFlag) {
		this.ownerFlag = ownerFlag;
	}

	public String getAddClauseDescription() {
		return addClauseDescription;
	}

	public void setAddClauseDescription(String addClauseDescription) {
		this.addClauseDescription = addClauseDescription;
	}

	public int getAttachmentToDelete() {
		return attachmentToDelete;
	}

	public void setAttachmentToDelete(int attachmentToDelete) {
		this.attachmentToDelete = attachmentToDelete;
	}

	public int getClauseChangeType() {
		return clauseChangeType;
	}

	public void setClauseChangeType(int clauseChangeType) {
		this.clauseChangeType = clauseChangeType;
	}

	public String getCommentsAdd() {
		return commentsAdd;
	}

	public void setCommentsAdd(String commentsAdd) {
		this.commentsAdd = commentsAdd;
	}

	public int getComponentGrpSeqNoinAdd() {
		return componentGrpSeqNoinAdd;
	}

	public void setComponentGrpSeqNoinAdd(int componentGrpSeqNoinAdd) {
		this.componentGrpSeqNoinAdd = componentGrpSeqNoinAdd;
	}

	public String getDataLocType() {
		return dataLocType;
	}

	public void setDataLocType(String dataLocType) {
		this.dataLocType = dataLocType;
	}

	public String getDwoNoAdd() {
		return dwoNoAdd;
	}

	public void setDwoNoAdd(String dwoNoAdd) {
		this.dwoNoAdd = dwoNoAdd;
	}

	public FileVO getFileToAttach() {
		return fileToAttach;
	}

	public void setFileToAttach(FileVO fileToAttach) {
		this.fileToAttach = fileToAttach;
	}

	public int getHdnParentClauseSeqNo() {
		return hdnParentClauseSeqNo;
	}

	public void setHdnParentClauseSeqNo(int hdnParentClauseSeqNo) {
		this.hdnParentClauseSeqNo = hdnParentClauseSeqNo;
	}

	public String[] getNewEdlNoAdd() {
		return newEdlNoAdd;
	}

	public void setNewEdlNoAdd(String[] newEdlNoAdd) {
		this.newEdlNoAdd = newEdlNoAdd;
	}

	public int getOrderKey() {
		return orderKey;
	}

	public void setOrderKey(int orderKey) {
		this.orderKey = orderKey;
	}

	public String getPartNoAdd() {
		return partNoAdd;
	}

	public void setPartNoAdd(String partNoAdd) {
		this.partNoAdd = partNoAdd;
	}

	public String[] getPartOf() {
		return partOf;
	}

	public void setPartOf(String[] partOf) {
		this.partOf = partOf;
	}

	public int[] getPartOfSeqNo() {
		return partOfSeqNo;
	}

	public void setPartOfSeqNo(int[] partOfSeqNo) {
		this.partOfSeqNo = partOfSeqNo;
	}

	public String getPriceBookNoAdd() {
		return priceBookNoAdd;
	}

	public void setPriceBookNoAdd(String priceBookNoAdd) {
		this.priceBookNoAdd = priceBookNoAdd;
	}

	public String getReasonAdd() {
		return reasonAdd;
	}

	public void setReasonAdd(String reasonAdd) {
		this.reasonAdd = reasonAdd;
	}

	public String[] getRefEdlNoAdd() {
		return refEdlNoAdd;
	}

	public void setRefEdlNoAdd(String[] refEdlNoAdd) {
		this.refEdlNoAdd = refEdlNoAdd;
	}

	public int getSeqNo1058() {
		return seqNo1058;
	}

	public void setSeqNo1058(int seqNo1058) {
		this.seqNo1058 = seqNo1058;
	}

	public int getStandardEquipmentSeqNoAdd() {
		return standardEquipmentSeqNoAdd;
	}

	public void setStandardEquipmentSeqNoAdd(int standardEquipmentSeqNoAdd) {
		this.standardEquipmentSeqNoAdd = standardEquipmentSeqNoAdd;
	}

	public int getSubSectionSeqNo() {
		return subSectionSeqNo;
	}

	public void setSubSectionSeqNo(int subSectionSeqNo) {
		this.subSectionSeqNo = subSectionSeqNo;
	}

	public int getSpecType() {
		return specType;
	}

	public void setSpecType(int specType) {
		this.specType = specType;
	}
	
	public String getFinanceUserID() {
		return financeUserID;
	}

	public void setFinanceUserID(String financeUserID) {
		this.financeUserID = financeUserID;
	}

	public String getOprEnggUserID() {
		return oprEnggUserID;
	}

	public void setOprEnggUserID(String oprEnggUserID) {
		this.oprEnggUserID = oprEnggUserID;
	}

	public String getPgmMgrUserID() {
		return pgmMgrUserID;
	}

	public void setPgmMgrUserID(String pgmMgrUserID) {
		this.pgmMgrUserID = pgmMgrUserID;
	}

	public String getPropMgrUserID() {
		return propMgrUserID;
	}

	public void setPropMgrUserID(String propMgrUserID) {
		this.propMgrUserID = propMgrUserID;
	}

	public String getSysEnggUserID() {
		return sysEnggUserID;
	}

	public void setSysEnggUserID(String sysEnggUserID) {
		this.sysEnggUserID = sysEnggUserID;
	}

	public int getFinanceStatusSeq() {
		return financeStatusSeq;
	}

	public void setFinanceStatusSeq(int financeStatusSeq) {
		this.financeStatusSeq = financeStatusSeq;
	}

	public int getOprEnggStatusSeq() {
		return oprEnggStatusSeq;
	}

	public void setOprEnggStatusSeq(int oprEnggStatusSeq) {
		this.oprEnggStatusSeq = oprEnggStatusSeq;
	}

	public int getPgmMgrStatusSeq() {
		return pgmMgrStatusSeq;
	}

	public void setPgmMgrStatusSeq(int pgmMgrStatusSeq) {
		this.pgmMgrStatusSeq = pgmMgrStatusSeq;
	}

	public int getPropMgrStatusSeq() {
		return propMgrStatusSeq;
	}

	public void setPropMgrStatusSeq(int propMgrStatusSeq) {
		this.propMgrStatusSeq = propMgrStatusSeq;
	}

	public int getSysEnggStatusSeq() {
		return sysEnggStatusSeq;
	}

	public void setSysEnggStatusSeq(int sysEnggStatusSeq) {
		this.sysEnggStatusSeq = sysEnggStatusSeq;
	}

	public String getSaveOnlyFlag() {
		return saveOnlyFlag;
	}

	public void setSaveOnlyFlag(String saveOnlyFlag) {
		this.saveOnlyFlag = saveOnlyFlag;
	}

	public int[] getModelSeqNos() {
		return modelSeqNos;
	}

	public void setModelSeqNos(int[] modelSeqNos) {
		this.modelSeqNos = modelSeqNos;
	}

	public String getLagacy1058Flag() {
		return lagacy1058Flag;
	}

	public void setLagacy1058Flag(String lagacy1058Flag) {
		this.lagacy1058Flag = lagacy1058Flag;
	}

	public String getLagacyFileLoc() {
		return lagacyFileLoc;
	}

	public void setLagacyFileLoc(String lagacyFileLoc) {
		this.lagacyFileLoc = lagacyFileLoc;
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

	public String getUploadAttachment() {
		return uploadAttachment;
	}

	public void setUploadAttachment(String uploadAttachment) {
		this.uploadAttachment = uploadAttachment;
	}

	public int getOrderbyFlag() {
		return orderbyFlag;
	}

	public void setOrderbyFlag(int orderbyFlag) {
		this.orderbyFlag = orderbyFlag;
	}

	public String[] getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String[] seqNo) {
		this.seqNo = seqNo;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getIssuedBy() {
		return issuedBy;
	}

	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}

	public int[] getStatusSeqNos() {
		return statusSeqNos;
	}

	public void setStatusSeqNos(int[] statusSeqNos) {
		this.statusSeqNos = statusSeqNos;
	}

	public String getLegacy1058ID() {
		return legacy1058ID;
	}

	public void setLegacy1058ID(String legacy1058ID) {
		this.legacy1058ID = legacy1058ID;
	}

	public String getChangeFromClaNo() {
		return changeFromClaNo;
	}

	public void setChangeFromClaNo(String changeFromClaNo) {
		this.changeFromClaNo = changeFromClaNo;
	}

	public String getChangeFromClaSeqNo() {
		return changeFromClaSeqNo;
	}

	public void setChangeFromClaSeqNo(String changeFromClaSeqNo) {
		this.changeFromClaSeqNo = changeFromClaSeqNo;
	}

	public String getChangeFromDwoNo() {
		return changeFromDwoNo;
	}

	public void setChangeFromDwoNo(String changeFromDwoNo) {
		this.changeFromDwoNo = changeFromDwoNo;
	}

	public String getChangeFromEdlNo() {
		return changeFromEdlNo;
	}

	public void setChangeFromEdlNo(String changeFromEdlNo) {
		this.changeFromEdlNo = changeFromEdlNo;
	}

	public String getChangeFromEngiComments() {
		return changeFromEngiComments;
	}

	public void setChangeFromEngiComments(String changeFromEngiComments) {
		this.changeFromEngiComments = changeFromEngiComments;
	}

	public String getChangeFromEngineeringData() {
		return changeFromEngineeringData;
	}

	public void setChangeFromEngineeringData(String changeFromEngineeringData) {
		this.changeFromEngineeringData = changeFromEngineeringData;
	}

	public String getChangeFromEquip() {
		return changeFromEquip;
	}

	public void setChangeFromEquip(String changeFromEquip) {
		this.changeFromEquip = changeFromEquip;
	}

	public String getChangeFromPartNo() {
		return changeFromPartNo;
	}

	public void setChangeFromPartNo(String changeFromPartNo) {
		this.changeFromPartNo = changeFromPartNo;
	}

	public String getChangeFromPartOfNo() {
		return changeFromPartOfNo;
	}

	public void setChangeFromPartOfNo(String changeFromPartOfNo) {
		this.changeFromPartOfNo = changeFromPartOfNo;
	}

	public String getChangeFromPriceBookNo() {
		return changeFromPriceBookNo;
	}

	public void setChangeFromPriceBookNo(String changeFromPriceBookNo) {
		this.changeFromPriceBookNo = changeFromPriceBookNo;
	}

	public String getChangeFromRefEdlNo() {
		return changeFromRefEdlNo;
	}

	public void setChangeFromRefEdlNo(String changeFromRefEdlNo) {
		this.changeFromRefEdlNo = changeFromRefEdlNo;
	}

	public String getChangeFromVerNo() {
		return changeFromVerNo;
	}

	public void setChangeFromVerNo(String changeFromVerNo) {
		this.changeFromVerNo = changeFromVerNo;
	}

	public String getChangeToClaNo() {
		return changeToClaNo;
	}

	public void setChangeToClaNo(String changeToClaNo) {
		this.changeToClaNo = changeToClaNo;
	}

	public String getChangeToClaSeqNo() {
		return changeToClaSeqNo;
	}

	public void setChangeToClaSeqNo(String changeToClaSeqNo) {
		this.changeToClaSeqNo = changeToClaSeqNo;
	}

	public String getChangeToDwoNo() {
		return changeToDwoNo;
	}

	public void setChangeToDwoNo(String changeToDwoNo) {
		this.changeToDwoNo = changeToDwoNo;
	}

	public String getChangeToEdlNo() {
		return changeToEdlNo;
	}

	public void setChangeToEdlNo(String changeToEdlNo) {
		this.changeToEdlNo = changeToEdlNo;
	}

	public String getChangeToEngiComments() {
		return changeToEngiComments;
	}

	public void setChangeToEngiComments(String changeToEngiComments) {
		this.changeToEngiComments = changeToEngiComments;
	}

	public String getChangeToEquip() {
		return changeToEquip;
	}

	public void setChangeToEquip(String changeToEquip) {
		this.changeToEquip = changeToEquip;
	}

	public String getChangeToPartNo() {
		return changeToPartNo;
	}

	public void setChangeToPartNo(String changeToPartNo) {
		this.changeToPartNo = changeToPartNo;
	}

	public String getChangeToPartOfNo() {
		return changeToPartOfNo;
	}

	public void setChangeToPartOfNo(String changeToPartOfNo) {
		this.changeToPartOfNo = changeToPartOfNo;
	}

	public String getChangeToPriceBookNo() {
		return changeToPriceBookNo;
	}

	public void setChangeToPriceBookNo(String changeToPriceBookNo) {
		this.changeToPriceBookNo = changeToPriceBookNo;
	}

	public String getChangeToRefEdlNo() {
		return changeToRefEdlNo;
	}

	public void setChangeToRefEdlNo(String changeToRefEdlNo) {
		this.changeToRefEdlNo = changeToRefEdlNo;
	}

	public String getChangeToVerNo() {
		return changeToVerNo;
	}

	public void setChangeToVerNo(String changeToVerNo) {
		this.changeToVerNo = changeToVerNo;
	}

	public String getChangeTypeDesc() {
		return changeTypeDesc;
	}

	public void setChangeTypeDesc(String changeTypeDesc) {
		this.changeTypeDesc = changeTypeDesc;
	}

	public String getChangeTypeSeqNo() {
		return changeTypeSeqNo;
	}

	public void setChangeTypeSeqNo(String changeTypeSeqNo) {
		this.changeTypeSeqNo = changeTypeSeqNo;
	}

	public String getClaUpdatedToSpec() {
		return claUpdatedToSpec;
	}

	public void setClaUpdatedToSpec(String claUpdatedToSpec) {
		this.claUpdatedToSpec = claUpdatedToSpec;
	}

	public String getRandomNo() {
		return randomNo;
	}

	public void setRandomNo(String randomNo) {
		this.randomNo = randomNo;
	}

	public String getSysEngineerName() {
		return sysEngineerName;
	}

	public void setSysEngineerName(String sysEngineerName) {
		this.sysEngineerName = sysEngineerName;
	}

	public String getChngFrmPartOfLeadCompGrp() {
		return chngFrmPartOfLeadCompGrp;
	}

	public void setChngFrmPartOfLeadCompGrp(String chngFrmPartOfLeadCompGrp) {
		this.chngFrmPartOfLeadCompGrp = chngFrmPartOfLeadCompGrp;
	}

	public String getChngFrmPartOfSeqNo() {
		return chngFrmPartOfSeqNo;
	}

	public void setChngFrmPartOfSeqNo(String chngFrmPartOfSeqNo) {
		this.chngFrmPartOfSeqNo = chngFrmPartOfSeqNo;
	}

	public String getChngFrmSubSecSeqNo() {
		return chngFrmSubSecSeqNo;
	}

	public void setChngFrmSubSecSeqNo(String chngFrmSubSecSeqNo) {
		this.chngFrmSubSecSeqNo = chngFrmSubSecSeqNo;
	}

	public String getChngToPartOfLeadCompGrp() {
		return chngToPartOfLeadCompGrp;
	}

	public void setChngToPartOfLeadCompGrp(String chngToPartOfLeadCompGrp) {
		this.chngToPartOfLeadCompGrp = chngToPartOfLeadCompGrp;
	}

	public String getChngToPartOfSeqNo() {
		return chngToPartOfSeqNo;
	}

	public void setChngToPartOfSeqNo(String chngToPartOfSeqNo) {
		this.chngToPartOfSeqNo = chngToPartOfSeqNo;
	}

	public String getChngToSubSecSeqNo() {
		return chngToSubSecSeqNo;
	}

	public void setChngToSubSecSeqNo(String chngToSubSecSeqNo) {
		this.chngToSubSecSeqNo = chngToSubSecSeqNo;
	}

	public String getChngFromCompGrpName() {
		return chngFromCompGrpName;
	}

	public void setChngFromCompGrpName(String chngFromCompGrpName) {
		this.chngFromCompGrpName = chngFromCompGrpName;
	}

	public String getChngFromCompGrpSeqNo() {
		return chngFromCompGrpSeqNo;
	}

	public void setChngFromCompGrpSeqNo(String chngFromCompGrpSeqNo) {
		this.chngFromCompGrpSeqNo = chngFromCompGrpSeqNo;
	}

	public String getChngFromCompName() {
		return chngFromCompName;
	}

	public void setChngFromCompName(String chngFromCompName) {
		this.chngFromCompName = chngFromCompName;
	}

	public String getChngFromCompSeqNo() {
		return chngFromCompSeqNo;
	}

	public void setChngFromCompSeqNo(String chngFromCompSeqNo) {
		this.chngFromCompSeqNo = chngFromCompSeqNo;
	}

	public String getChngToCompGrpName() {
		return chngToCompGrpName;
	}

	public void setChngToCompGrpName(String chngToCompGrpName) {
		this.chngToCompGrpName = chngToCompGrpName;
	}

	public String getChngToCompGrpSeqNo() {
		return chngToCompGrpSeqNo;
	}

	public void setChngToCompGrpSeqNo(String chngToCompGrpSeqNo) {
		this.chngToCompGrpSeqNo = chngToCompGrpSeqNo;
	}

	public String getChngToCompName() {
		return chngToCompName;
	}

	public void setChngToCompName(String chngToCompName) {
		this.chngToCompName = chngToCompName;
	}

	public String getChngToCompSeqNo() {
		return chngToCompSeqNo;
	}

	public void setChngToCompSeqNo(String chngToCompSeqNo) {
		this.chngToCompSeqNo = chngToCompSeqNo;
	}

	public String getChangeFromEquipSeqNo() {
		return changeFromEquipSeqNo;
	}

	public void setChangeFromEquipSeqNo(String changeFromEquipSeqNo) {
		this.changeFromEquipSeqNo = changeFromEquipSeqNo;
	}

	public String getChangeToEquipSeqNo() {
		return changeToEquipSeqNo;
	}

	public void setChangeToEquipSeqNo(String changeToEquipSeqNo) {
		this.changeToEquipSeqNo = changeToEquipSeqNo;
	}

	public String getChngFrmTblDataCol1() {
		return chngFrmTblDataCol1;
	}

	public void setChngFrmTblDataCol1(String chngFrmTblDataCol1) {
		this.chngFrmTblDataCol1 = chngFrmTblDataCol1;
	}

	public String getChngFrmTblDataCol2() {
		return chngFrmTblDataCol2;
	}

	public void setChngFrmTblDataCol2(String chngFrmTblDataCol2) {
		this.chngFrmTblDataCol2 = chngFrmTblDataCol2;
	}

	public String getChngFrmTblDataCol3() {
		return chngFrmTblDataCol3;
	}

	public void setChngFrmTblDataCol3(String chngFrmTblDataCol3) {
		this.chngFrmTblDataCol3 = chngFrmTblDataCol3;
	}

	public String getChngFrmTblDataCol4() {
		return chngFrmTblDataCol4;
	}

	public void setChngFrmTblDataCol4(String chngFrmTblDataCol4) {
		this.chngFrmTblDataCol4 = chngFrmTblDataCol4;
	}

	public String getChngFrmTblDataCol5() {
		return chngFrmTblDataCol5;
	}

	public void setChngFrmTblDataCol5(String chngFrmTblDataCol5) {
		this.chngFrmTblDataCol5 = chngFrmTblDataCol5;
	}

	public String getChngToTblDataCol1() {
		return chngToTblDataCol1;
	}

	public void setChngToTblDataCol1(String chngToTblDataCol1) {
		this.chngToTblDataCol1 = chngToTblDataCol1;
	}

	public String getChngToTblDataCol2() {
		return chngToTblDataCol2;
	}

	public void setChngToTblDataCol2(String chngToTblDataCol2) {
		this.chngToTblDataCol2 = chngToTblDataCol2;
	}

	public String getChngToTblDataCol3() {
		return chngToTblDataCol3;
	}

	public void setChngToTblDataCol3(String chngToTblDataCol3) {
		this.chngToTblDataCol3 = chngToTblDataCol3;
	}

	public String getChngToTblDataCol4() {
		return chngToTblDataCol4;
	}

	public void setChngToTblDataCol4(String chngToTblDataCol4) {
		this.chngToTblDataCol4 = chngToTblDataCol4;
	}

	public String getChngToTblDataCol5() {
		return chngToTblDataCol5;
	}

	public void setChngToTblDataCol5(String chngToTblDataCol5) {
		this.chngToTblDataCol5 = chngToTblDataCol5;
	}

	public String getFrmHeaderFlag() {
		return frmHeaderFlag;
	}

	public void setFrmHeaderFlag(String frmHeaderFlag) {
		this.frmHeaderFlag = frmHeaderFlag;
	}

	public String getToHeaderFlag() {
		return toHeaderFlag;
	}

	public void setToHeaderFlag(String toHeaderFlag) {
		this.toHeaderFlag = toHeaderFlag;
	}

	public int[] getClaSeqNo() {
		return claSeqNo;
	}

	public void setClaSeqNo(int[] claSeqNo) {
		this.claSeqNo = claSeqNo;
	}

	public int[] getClaChangeType() {
		return claChangeType;
	}

	public void setClaChangeType(int[] claChangeType) {
		this.claChangeType = claChangeType;
	}

	public int getMdlClaSeqNo() {
		return mdlClaSeqNo;
	}

	public void setMdlClaSeqNo(int mdlClaSeqNo) {
		this.mdlClaSeqNo = mdlClaSeqNo;
	}

	public String getClaExistsFlag() {
		return claExistsFlag;
	}

	public void setClaExistsFlag(String claExistsFlag) {
		this.claExistsFlag = claExistsFlag;
	}

	public String getSubSecCode() {
		return SubSecCode;
	}

	public void setSubSecCode(String subSecCode) {
		SubSecCode = subSecCode;
	}

	public String getSubSecName() {
		return SubSecName;
	}

	public void setSubSecName(String subSecName) {
		SubSecName = subSecName;
	}

	public int[] getSubSecSeqNo() {
		return subSecSeqNo;
	}

	public void setSubSecSeqNo(int[] subSecSeqNo) {
		this.subSecSeqNo = subSecSeqNo;
	}

	public String getReasonSubSec() {
		return reasonSubSec;
	}

	public void setReasonSubSec(String reasonSubSec) {
		this.reasonSubSec = reasonSubSec;
	}

	public String getSubSecExistsFlag() {
		return subSecExistsFlag;
	}

	public void setSubSecExistsFlag(String subSecExistsFlag) {
		this.subSecExistsFlag = subSecExistsFlag;
	}

	public int getSubSecChngReqSeq() {
		return subSecChngReqSeq;
	}

	public void setSubSecChngReqSeq(int subSecChngReqSeq) {
		this.subSecChngReqSeq = subSecChngReqSeq;
	}

	public String getUpdatedToSpecFlag() {
		return updatedToSpecFlag;
	}

	public void setUpdatedToSpecFlag(String updatedToSpecFlag) {
		this.updatedToSpecFlag = updatedToSpecFlag;
	}

	public int getSubSecChngReqSeqNo() {
		return SubSecChngReqSeqNo;
	}

	public void setSubSecChngReqSeqNo(int subSecChngReqSeqNo) {
		SubSecChngReqSeqNo = subSecChngReqSeqNo;
	}

	public ArrayList getClaDetailList() {
		return claDetailList;
	}

	public void setClaDetailList(ArrayList claDetailList) {
		this.claDetailList = claDetailList;
	}

	public ArrayList getTableArrayData1() {
		return tableArrayData1;
	}

	public void setTableArrayData1(ArrayList tableArrayData1) {
		this.tableArrayData1 = tableArrayData1;
	}

	public String getTransitionDate() {
		return transitionDate;
	}

	public void setTransitionDate(String transitionDate) {
		this.transitionDate = transitionDate;
	}

	public String getActionUser() {
		return actionUser;
	}

	public void setActionUser(String actionUser) {
		this.actionUser = actionUser;
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
	
	
	
}
