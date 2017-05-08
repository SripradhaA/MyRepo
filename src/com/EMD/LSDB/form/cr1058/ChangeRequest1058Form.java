package com.EMD.LSDB.form.cr1058;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.struts.upload.FormFile;

import com.EMD.LSDB.form.common.EMDForm;

public class ChangeRequest1058Form extends EMDForm{
	
	private ArrayList arlRequestDetails1058=null;
	private String specRev;
	
	//By vipul starts
	private ArrayList custNameList;
	private ArrayList mdlNameList;
	private ArrayList statusList;
	private ArrayList userList;
	private ArrayList requestList;
	private String orderNo;
	private int statusSeqNo;
	private int custSeqNo;
	private int seqNo1058;
	private int mdlSeqNo;
	
	private int orderQty;

	private String revStatus;

	private String changeFromSpec;

	private String changeFromClaDesc;

	private String changeFromEngData;

	private String changeToSpec;

	private String changeToClaDesc;

	private String changeToEngData;
	
	private String changeClaReason;
	
	private int hdnSelectedChangeReqSeqNo;
	
	private int claChangeReqSeqNo;
	
	private ArrayList claList; 
	
	private String hdnNonLsdbFlag;

	//By vipul Ends
		
	
	//BY rajesh starts
		
		private String custName;
		private String mdlName;
		private String status;
		private String awaitingApprvrUser;
		private String nonLsdbFlag=null;
		private ArrayList requestDetailsList=null;
		
		
		private String genDesc;
		private String unitNumber = null;
	    private String roadNumber = null;
	    private String mcrNumber = null;
	    private int mcrReq;
	    
	    private String filePath;
	    private int imgSeqNo;
	    private int categorySeqNo;
	    private int reqTypeSeqNo;
	    
	    private ArrayList reqTypes=null;
	    private ArrayList categories=null;
	    private ArrayList clauseChangeTypes=null;
	    private String sections=null;
	    private String subSections=null;
	    private String componentGroup=null;
	    private String components=null;
	    private String dataLocType;
	    private String addClauseDescription=null;
	    private FormFile attachmentSource;
	    
	    //By reajesh Ends
	    
	    //Added by SM105772 for CR-110 Request Common Details
	    private ArrayList  representativeList;
	    private String sysEngg;
	    private String opRep;
	    private String finRep;
	    private String pgmMgr;
	    private String propMgr;
	    private String repList;
	    
	    private int orderKey;
	    private String dataLocTypeCode;
	    private int orderDetails; 
	    private String userId;
	    private String chngAffectFlag;
	    private String earStnAff;
	    private int  sectionStatusSeq;
	    private String sysEnggLastName=null;

	    private String sysEnggDateCompleted;
	    
	    private String systemEngComment;
	    private String partNoAdded;
	    private String partNoDeleted; 
	    private String ChangeAffectsWeight;
	    private String changeAffectsClear;
	    //Updated DataType from int to string CR-120 "0" validation issue
	    private String designHrs;
	    private String draftingHrs;
	    //Updated DataType from int to string CR-120 "0" validation issue Ends Here
	    private String drawingDueDate;
	    private String completionDate;
	    private String stationAffected;
	    private String oprEnggLastName=null;
	    private String oprEnggDateReceived;
	    private String oprEnggDateCompleted;
	    private String operationComments; 
	    private String disposExcessMaterial;
	    private String supplierAffectedCost;
	    private String laborImpact;
	    private String recEffectivityDel;
	    private String toolingCostUSD = "0.00";
	    private String scrapCostUSD = "0.00";
	    private String updtUserID;
	    private String reworkCost = "0.00";
	    
	    private String financeLastName=null;
	    private String financeDateReceived;
	    private String financeDateCompleted; 
	    
	    private String financeComments;
	    private String prodCostChange;
	    private String prodCostChangeSup;
	    
	    private String pgmMgrLastName=null;
	    private String pgmMgrDateReceived;
	    private String pgmMgrDateCompleted; 
	    private String progManagerComments;
	    
	    
	    private String propMgrDateReceived;
	    private String propMgrDateCompleted;
	    
	    private String propMgrLastName=null;
	    private String propManagerComments;
	    private String sellpriceCustomer = "0.00";
	    private String markUp = "0.00";
	    private String customerApprovalReq;
	    private String customerDecisionDate;
	    private String customerDecision;
	    private String custApprovalDet;
	    private String actualSellPrice = "0.00";
	    private int countDays;
	    
	    
	    private int  sectionStatus;
	    private String hdnSelSysEngg;
	    private int hdnsectionStatusSeq; 
	    
	    private String modChangeAffectsWeightSeqNo;
	    
	    private String workOrderUSD = "0.00";
	    private String sysEnggDateReceived;
	    private String hdnnewChngAfftWeightFlag;
	    private boolean showLatSpecFlag;
	    private String hdnnewChngAfftClearFlag;
	    
	    //Component Tied to Clause
	    private String hdncomponentGroupSeqNo;
	    private String componentSeqNo;
	    private String hdnComponentName;
	    //Ends Component Tied to clause
	    
	    public int[] component;
	    
	    //For add clause
	    private int componentGrpSeqNoinAdd;
	    private int hdnLeadComponentSeqNoinAdd;
	    private String hdnLeadComponentNameinAdd;
	    private int hdnParentClauseSeqNo;
	    private String hdnSectionName;
	    private String hdnSubSectionName;
	    private int subSectionSeqNo;
	    private ArrayList clauseGroup;
	    private int sectionSeqNo;
	    private String standardEquipmentListAdd;
		public int standardEquipmentSeqNoAdd;
	    public int[] partOfSeqNo;
	    public String[] refEdlNoAdd;
		public String[] newEdlNoAdd;
	    private String dwoNoAdd;
	    private String[] partOf=null;
	    private String partNoAdd;
	    public String[] clauseSeqNum;
	    public String[] clauseTableDataArray1Add;
		public String[] clauseTableDataArray2Add;
		public String[] clauseTableDataArray3Add;
		public String[] clauseTableDataArray4Add;
		public String[] clauseTableDataArray5Add;
		public String priceBookNoAdd;
		public String commentsAdd;
		public String reasonAdd;
	    //For Add Clause Ends
	
		//For Modify Clause
		public String clauseToModify;
		public String hdnClauseToModify;
		public int hdnClauseToModifySeqNo;
		public String clauseDescriptionMod;
		public String[] refEdlNoMod;
		public String[] newEdlNoMod;
		public String dwoNoMod;
		public String partNoMod;
		public String priceBookNoMod;
		public int standardEquipmentSeqNoMod;
		public String commentsMod;
		public String reasonMod;
		//For Modify Clause ends
		
		//For Delete Clause
		public String hdnClauseToDelete;
		public String reasonDel;
		public int hdnClauseToDeleteSeqNo;
		public String clauseToDelete;
		public String[] clauseTableDataArray1Mod;
		public String[] clauseTableDataArray2Mod;
		public String[] clauseTableDataArray3Mod;
		public String[] clauseTableDataArray4Mod;
		public String[] clauseTableDataArray5Mod;
        //For Delete Clause end
		 
		//For Change Component
		private int hdnOldComponentSeqNo;
		private String hdnOldComponentName; 
		public String clauseDescriptionChngComp;
		public String[] newEdlNoChngComp;
		public String[] refEdlNoChngComp;
		public String dwoNoChngComp;
		public String partNoChngComp;
		public String priceBookChngComp;
		public int standardEquipmentSeqNoChngComp;
		public String commentsChngComp;
		public String reasonChngComp;
		public String[] clauseTableDataArray1ChngComp;
		public String[] clauseTableDataArray2ChngComp;
		public String[] clauseTableDataArray3ChngComp;
		public String[] clauseTableDataArray4ChngComp;
		public String[] clauseTableDataArray5ChngComp;
		private String deleterow;
		//For Change Component ends
		 
		private int clauseChangeType;
		private ArrayList componentVO;
		private ArrayList reqSpecChngClauseList;
		private ArrayList reviseClauseDetailsList;
		private int reviseClauseCheck;
		private ArrayList standardEquipList;
		private int clauseChangeRequestSeqNo;
		private int checkCompChangeClauses;
		private ArrayList changeComponentClause;
		private int clauseToVerNo; 
		private int clauseToSeqNo;
		private ArrayList sectionList;
		private ArrayList subSectionList;
		private ArrayList componentGrpList;
		private ArrayList componentList;
		
		private int specType;
		private String role;
		private int versionNoMod;
		private int hdnVersionNoMod;
		private int noOfClaVersion;
		private ArrayList clauseVersions;
		private int checkVersionClause;
		private ArrayList versionClauseDetails;
		
		//added for radio button fix
		private int requestType;
		private int requestFrom;
		private int mcrnumberReq;
		
		//Added for CR-117
		private String id1058;
        private String hdnSpecRev;
        
        //Added for CR-127
        private ArrayList mdlClaChangesList;
        private String reasonMdl;
        private String clauseNumber;
        private int changeTypeSeqNo;
        private int mdlClaChangeListSize;
        
        //Added for CR-130
        private ArrayList importSubSecList;
        private String subSection;
        private String reasonSubSec;
        private String clauseDesc;
        private String enggData;
        private int secChangeTypeSeqNo;
        private int importSubSecListSize;
        private ArrayList SubSec1058List;
        
		public int getMdlClaChangeListSize() {
			return mdlClaChangeListSize;
		}
		public void setMdlClaChangeListSize(int mdlClaChangeListSize) {
			this.mdlClaChangeListSize = mdlClaChangeListSize;
		}
		public String getReasonMdl() {
			return reasonMdl;
		}
		public void setReasonMdl(String reasonMdl) {
			this.reasonMdl = reasonMdl;
		}
		public ArrayList getMdlClaChangesList() {
			return mdlClaChangesList;
		}
		public void setMdlClaChangesList(ArrayList mdlClaChangesList) {
			this.mdlClaChangesList = mdlClaChangesList;
		}
		public int getRequestFrom() {
			return requestFrom;
		}
		public void setRequestFrom(int requestFrom) {
			this.requestFrom = requestFrom;
		}
		public int getRequestType() {
			return requestType;
		}
		public void setRequestType(int requestType) {
			this.requestType = requestType;
		}
		
		public int getMcrnumberReq() {
			return mcrnumberReq;
		}
		public void setMcrnumberReq(int mcrnumberReq) {
			this.mcrnumberReq = mcrnumberReq;
		}
		public int getCheckVersionClause() {
			return checkVersionClause;
		}
		public void setCheckVersionClause(int checkVersionClause) {
			this.checkVersionClause = checkVersionClause;
		}
		public ArrayList getVersionClauseDetails() {
			return versionClauseDetails;
		}
		public void setVersionClauseDetails(ArrayList versionClauseDetails) {
			this.versionClauseDetails = versionClauseDetails;
		}
		public ArrayList getClauseVersions() {
			return clauseVersions;
		}
		public void setClauseVersions(ArrayList clauseVersions) {
			this.clauseVersions = clauseVersions;
		}
		public int getNoOfClaVersion() {
			return noOfClaVersion;
		}
		public void setNoOfClaVersion(int noOfClaVersion) {
			this.noOfClaVersion = noOfClaVersion;
		}
		public int getSpecType() {
			return specType;
		}
		public void setSpecType(int specType) {
			this.specType = specType;
		}
		public String getGenDesc() {
			return genDesc;
		}
		public void setGenDesc(String genDesc) {
			this.genDesc = genDesc;
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
		public String getAwaitingAppUser() {
			return awaitingApprvrUser;
		}
		public void setAwaitingAppUser(String awaitingAppUser) {
			this.awaitingApprvrUser = awaitingAppUser;
		}
		public String getCustName() {
			return custName;
		}
		public void setCustName(String custName) {
			this.custName = custName;
		}
		public ArrayList getCustNameList() {
			return custNameList;
		}
		public void setCustNameList(ArrayList custNameList) {
			this.custNameList = custNameList;
		}
		public int getCustSeqNo() {
			return custSeqNo;
		}
		public void setCustSeqNo(int custSeqNo) {
			this.custSeqNo = custSeqNo;
		}
		public String getMdlName() {
			return mdlName;
		}
		public void setMdlName(String mdlName) {
			this.mdlName = mdlName;
		}
		public ArrayList getMdlNameList() {
			return mdlNameList;
		}
		public void setMdlNameList(ArrayList mdlNameList) {
			this.mdlNameList = mdlNameList;
		}
		public int getMdlSeqNo() {
			return mdlSeqNo;
		}
		public void setMdlSeqNo(int mdlSeqNo) {
			this.mdlSeqNo = mdlSeqNo;
		}
		public String getOrderNo() {
			return orderNo;
		}
		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}
		public ArrayList getRequestList() {
			return requestList;
		}
		public void setRequestList(ArrayList requestList) {
			this.requestList = requestList;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public ArrayList getStatusList() {
			return statusList;
		}
		public void setStatusList(ArrayList statusList) {
			this.statusList = statusList;
		}
		public int getStatusSeqNo() {
			return statusSeqNo;
		}
		public void setStatusSeqNo(int statusSeqNo) {
			this.statusSeqNo = statusSeqNo;
		}
		public ArrayList getUserList() {
			return userList;
		}
		public void setUserList(ArrayList userList) {
			this.userList = userList;
		}
		public String getAwaitingApprvrUser() {
			return awaitingApprvrUser;
		}
		public void setAwaitingApprvrUser(String awaitingApprvrUser) {
			this.awaitingApprvrUser = awaitingApprvrUser;
		}
		public String getNonLsdbFlag() {
			return nonLsdbFlag;
		}
		public void setNonLsdbFlag(String nonLsdbFlag) {
			this.nonLsdbFlag = nonLsdbFlag;
		}
		public ArrayList getRequestDetailsList() {
			return requestDetailsList;
		}
		public void setRequestDetailsList(ArrayList requestDetailsList) {
			this.requestDetailsList = requestDetailsList;
		}
		public ArrayList getRepresentativeList() {
			return representativeList;
		}
		public void setRepresentativeList(ArrayList representativeList) {
			this.representativeList = representativeList;
		}
		public String getSysEngg() {
			return sysEngg;
		}
		public void setSysEngg(String sysEngg) {
			this.sysEngg = sysEngg;
		}
		public String getFinRep() {
			return finRep;
		}
		public void setFinRep(String finRep) {
			this.finRep = finRep;
		}
		public String getOpRep() {
			return opRep;
		}
		public void setOpRep(String opRep) {
			this.opRep = opRep;
		}
		public String getPgmMgr() {
			return pgmMgr;
		}
		public void setPgmMgr(String pgmMgr) {
			this.pgmMgr = pgmMgr;
		}
		public String getPropMgr() {
			return propMgr;
		}
		public void setPropMgr(String propMgr) {
			this.propMgr = propMgr;
		}
		public String getRepList() {
			return repList;
		}
		public void setRepList(String repList) {
			this.repList = repList;
		}
		public ArrayList getArlRequestDetails1058() {
			return arlRequestDetails1058;
		}
		public void setArlRequestDetails1058(ArrayList arlRequestDetails1058) {
			this.arlRequestDetails1058 = arlRequestDetails1058;
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
		public ArrayList getClaList() {
			return claList;
		}
		public void setClaList(ArrayList claList) {
			this.claList = claList;
		}
		public int getHdnSelectedChangeReqSeqNo() {
			return hdnSelectedChangeReqSeqNo;
		}
		public void setHdnSelectedChangeReqSeqNo(int hdnSelectedChangeReqSeqNo) {
			this.hdnSelectedChangeReqSeqNo = hdnSelectedChangeReqSeqNo;
		}
		public int getOrderQty() {
			return orderQty;
		}
		public void setOrderQty(int orderQty) {
			this.orderQty = orderQty;
		}
		public String getRevStatus() {
			return revStatus;
		}
		public void setRevStatus(String revStatus) {
			this.revStatus = revStatus;
		}
		public String getHdnNonLsdbFlag() {
			return hdnNonLsdbFlag;
		}
		public void setHdnNonLsdbFlag(String hdnNonLsdbFlag) {
			this.hdnNonLsdbFlag = hdnNonLsdbFlag;
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
		public String getChngAffectFlag() {
			return chngAffectFlag;
		}
		public void setChngAffectFlag(String chngAffectFlag) {
			this.chngAffectFlag = chngAffectFlag;
		}
		public String getCompletionDate() {
			return completionDate;
		}
		public void setCompletionDate(String completionDate) {
			this.completionDate = completionDate;
		}
		public int getCountOfDays() {
			return countDays;
		}
		public void setCountOfDays(int countDays) {
			this.countDays = countDays;
		}
		public String getCustApprovalDet() {
			return custApprovalDet;
		}
		public void setCustApprovalDet(String custApprovalDet) {
			this.custApprovalDet = custApprovalDet;
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
		public String getCustomerDecisionDate() {
			return customerDecisionDate;
		}
		public void setCustomerDecisionDate(String customerDecisionDate) {
			this.customerDecisionDate = customerDecisionDate;
		}
		public String getDataLocTypeCode() {
			return dataLocTypeCode;
		}
		public void setDataLocTypeCode(String dataLocTypeCode) {
			this.dataLocTypeCode = dataLocTypeCode;
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
		public String getEarStnAff() {
			return earStnAff;
		}
		public void setEarStnAff(String earStnAff) {
			this.earStnAff = earStnAff;
		}
		public String getFinanceComments() {
			return financeComments;
		}
		public void setFinanceComments(String financeComments) {
			this.financeComments = financeComments;
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
		public String getFinanceLastName() {
			return financeLastName;
		}
		public void setFinanceLastName(String financeLastName) {
			this.financeLastName = financeLastName;
		}
		public String getHdnnewChngAfftClearFlag() {
			return hdnnewChngAfftClearFlag;
		}
		public void setHdnnewChngAfftClearFlag(String hdnnewChngAfftClearFlag) {
			this.hdnnewChngAfftClearFlag = hdnnewChngAfftClearFlag;
		}
		public String getHdnnewChngAfftWeightFlag() {
			return hdnnewChngAfftWeightFlag;
		}
		public void setHdnnewChngAfftWeightFlag(String hdnnewChngAfftWeightFlag) {
			this.hdnnewChngAfftWeightFlag = hdnnewChngAfftWeightFlag;
		}
		public int getHdnsectionStatusSeq() {
			return hdnsectionStatusSeq;
		}
		public void setHdnsectionStatusSeq(int hdnsectionStatusSeq) {
			this.hdnsectionStatusSeq = hdnsectionStatusSeq;
		}
		public String getHdnSelSysEngg() {
			return hdnSelSysEngg;
		}
		public void setHdnSelSysEngg(String hdnSelSysEngg) {
			this.hdnSelSysEngg = hdnSelSysEngg;
		}
		public String getLaborImpact() {
			return laborImpact;
		}
		public void setLaborImpact(String laborImpact) {
			this.laborImpact = laborImpact;
		}
		
		
		public String getModChangeAffectsWeightSeqNo() {
			return modChangeAffectsWeightSeqNo;
		}
		public void setModChangeAffectsWeightSeqNo(String modChangeAffectsWeightSeqNo) {
			this.modChangeAffectsWeightSeqNo = modChangeAffectsWeightSeqNo;
		}
		public String getOperationComments() {
			return operationComments;
		}
		public void setOperationComments(String operationComments) {
			this.operationComments = operationComments;
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
		public String getOprEnggLastName() {
			return oprEnggLastName;
		}
		public void setOprEnggLastName(String oprEnggLastName) {
			this.oprEnggLastName = oprEnggLastName;
		}
		public int getOrderDetails() {
			return orderDetails;
		}
		public void setOrderDetails(int orderDetails) {
			this.orderDetails = orderDetails;
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
		public String getPgmMgrDateCompleted() {
			return pgmMgrDateCompleted;
		}
		public void setPgmMgrDateCompleted(String pgmMgrDateCompleted) {
			this.pgmMgrDateCompleted = pgmMgrDateCompleted;
		}
		public String getPgmMgrDateReceived() {
			return pgmMgrDateReceived;
		}
		public void setPgmMgrDateReceived(String pgmMgrDateReceived) {
			this.pgmMgrDateReceived = pgmMgrDateReceived;
		}
		public String getPgmMgrLastName() {
			return pgmMgrLastName;
		}
		public void setPgmMgrLastName(String pgmMgrLastName) {
			this.pgmMgrLastName = pgmMgrLastName;
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
		public String getPropMgrDateCompleted() {
			return propMgrDateCompleted;
		}
		public void setPropMgrDateCompleted(String propMgrDateCompleted) {
			this.propMgrDateCompleted = propMgrDateCompleted;
		}
		public String getPropMgrDateReceived() {
			return propMgrDateReceived;
		}
		public void setPropMgrDateReceived(String propMgrDateReceived) {
			this.propMgrDateReceived = propMgrDateReceived;
		}
		public String getPropMgrLastName() {
			return propMgrLastName;
		}
		public void setPropMgrLastName(String propMgrLastName) {
			this.propMgrLastName = propMgrLastName;
		}
		public String getRecEffectivityDel() {
			return recEffectivityDel;
		}
		public void setRecEffectivityDel(String recEffectivityDel) {
			this.recEffectivityDel = recEffectivityDel;
		}
		
		public int getSectionStatus() {
			return sectionStatus;
		}
		public void setSectionStatus(int sectionStatus) {
			this.sectionStatus = sectionStatus;
		}
		public int getSectionStatusSeq() {
			return sectionStatusSeq;
		}
		public void setSectionStatusSeq(int sectionStatusSeq) {
			this.sectionStatusSeq = sectionStatusSeq;
		}
		
		public boolean isShowLatSpecFlag() {
			return showLatSpecFlag;
		}
		public void setShowLatSpecFlag(boolean showLatSpecFlag) {
			this.showLatSpecFlag = showLatSpecFlag;
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
		public String getSysEnggLastName() {
			return sysEnggLastName;
		}
		public void setSysEnggLastName(String sysEnggLastName) {
			this.sysEnggLastName = sysEnggLastName;
		}
		public String getSystemEngComment() {
			return systemEngComment;
		}
		public void setSystemEngComment(String systemEngComment) {
			this.systemEngComment = systemEngComment;
		}
		
		public String getUpdtUserID() {
			return updtUserID;
		}
		public void setUpdtUserID(String updtUserID) {
			this.updtUserID = updtUserID;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public int getSeqNo1058() {
			return seqNo1058;
		}
		public void setSeqNo1058(int seqNo1058) {
			this.seqNo1058 = seqNo1058;
		}
		public ArrayList getChangeComponentClause() {
			return changeComponentClause;
		}
		public void setChangeComponentClause(ArrayList changeComponentClause) {
			this.changeComponentClause = changeComponentClause;
		}
		public int getCheckCompChangeClauses() {
			return checkCompChangeClauses;
		}
		public void setCheckCompChangeClauses(int checkCompChangeClauses) {
			this.checkCompChangeClauses = checkCompChangeClauses;
		}
		public int getClauseChangeRequestSeqNo() {
			return clauseChangeRequestSeqNo;
		}
		public void setClauseChangeRequestSeqNo(int clauseChangeRequestSeqNo) {
			this.clauseChangeRequestSeqNo = clauseChangeRequestSeqNo;
		}
		public int getClauseChangeType() {
			return clauseChangeType;
		}
		public void setClauseChangeType(int clauseChangeType) {
			this.clauseChangeType = clauseChangeType;
		}
		public String getClauseDescriptionChngComp() {
			return clauseDescriptionChngComp;
		}
		public void setClauseDescriptionChngComp(String clauseDescriptionChngComp) {
			this.clauseDescriptionChngComp = clauseDescriptionChngComp;
		}
		public String getClauseDescriptionMod() {
			return clauseDescriptionMod;
		}
		public void setClauseDescriptionMod(String clauseDescriptionMod) {
			this.clauseDescriptionMod = clauseDescriptionMod;
		}
		public ArrayList getClauseGroup() {
			return clauseGroup;
		}
		public void setClauseGroup(ArrayList clauseGroup) {
			this.clauseGroup = clauseGroup;
		}
		public String[] getClauseSeqNum() {
			return clauseSeqNum;
		}
		public void setClauseSeqNum(String[] clauseSeqNum) {
			this.clauseSeqNum = clauseSeqNum;
		}
		public String[] getClauseTableDataArray1Add() {
			return clauseTableDataArray1Add;
		}
		public void setClauseTableDataArray1Add(String[] clauseTableDataArray1Add) {
			this.clauseTableDataArray1Add = clauseTableDataArray1Add;
		}
		public String[] getClauseTableDataArray1ChngComp() {
			return clauseTableDataArray1ChngComp;
		}
		public void setClauseTableDataArray1ChngComp(
				String[] clauseTableDataArray1ChngComp) {
			this.clauseTableDataArray1ChngComp = clauseTableDataArray1ChngComp;
		}
		public String[] getClauseTableDataArray1Mod() {
			return clauseTableDataArray1Mod;
		}
		public void setClauseTableDataArray1Mod(String[] clauseTableDataArray1Mod) {
			this.clauseTableDataArray1Mod = clauseTableDataArray1Mod;
		}
		public String[] getClauseTableDataArray2Add() {
			return clauseTableDataArray2Add;
		}
		public void setClauseTableDataArray2Add(String[] clauseTableDataArray2Add) {
			this.clauseTableDataArray2Add = clauseTableDataArray2Add;
		}
		public String[] getClauseTableDataArray2ChngComp() {
			return clauseTableDataArray2ChngComp;
		}
		public void setClauseTableDataArray2ChngComp(
				String[] clauseTableDataArray2ChngComp) {
			this.clauseTableDataArray2ChngComp = clauseTableDataArray2ChngComp;
		}
		public String[] getClauseTableDataArray2Mod() {
			return clauseTableDataArray2Mod;
		}
		public void setClauseTableDataArray2Mod(String[] clauseTableDataArray2Mod) {
			this.clauseTableDataArray2Mod = clauseTableDataArray2Mod;
		}
		public String[] getClauseTableDataArray3Add() {
			return clauseTableDataArray3Add;
		}
		public void setClauseTableDataArray3Add(String[] clauseTableDataArray3Add) {
			this.clauseTableDataArray3Add = clauseTableDataArray3Add;
		}
		public String[] getClauseTableDataArray3ChngComp() {
			return clauseTableDataArray3ChngComp;
		}
		public void setClauseTableDataArray3ChngComp(
				String[] clauseTableDataArray3ChngComp) {
			this.clauseTableDataArray3ChngComp = clauseTableDataArray3ChngComp;
		}
		public String[] getClauseTableDataArray3Mod() {
			return clauseTableDataArray3Mod;
		}
		public void setClauseTableDataArray3Mod(String[] clauseTableDataArray3Mod) {
			this.clauseTableDataArray3Mod = clauseTableDataArray3Mod;
		}
		public String[] getClauseTableDataArray4Add() {
			return clauseTableDataArray4Add;
		}
		public void setClauseTableDataArray4Add(String[] clauseTableDataArray4Add) {
			this.clauseTableDataArray4Add = clauseTableDataArray4Add;
		}
		public String[] getClauseTableDataArray4ChngComp() {
			return clauseTableDataArray4ChngComp;
		}
		public void setClauseTableDataArray4ChngComp(
				String[] clauseTableDataArray4ChngComp) {
			this.clauseTableDataArray4ChngComp = clauseTableDataArray4ChngComp;
		}
		public String[] getClauseTableDataArray4Mod() {
			return clauseTableDataArray4Mod;
		}
		public void setClauseTableDataArray4Mod(String[] clauseTableDataArray4Mod) {
			this.clauseTableDataArray4Mod = clauseTableDataArray4Mod;
		}
		public String[] getClauseTableDataArray5Add() {
			return clauseTableDataArray5Add;
		}
		public void setClauseTableDataArray5Add(String[] clauseTableDataArray5Add) {
			this.clauseTableDataArray5Add = clauseTableDataArray5Add;
		}
		public String[] getClauseTableDataArray5ChngComp() {
			return clauseTableDataArray5ChngComp;
		}
		public void setClauseTableDataArray5ChngComp(
				String[] clauseTableDataArray5ChngComp) {
			this.clauseTableDataArray5ChngComp = clauseTableDataArray5ChngComp;
		}
		public String[] getClauseTableDataArray5Mod() {
			return clauseTableDataArray5Mod;
		}
		public void setClauseTableDataArray5Mod(String[] clauseTableDataArray5Mod) {
			this.clauseTableDataArray5Mod = clauseTableDataArray5Mod;
		}
		public String getClauseToDelete() {
			return clauseToDelete;
		}
		public void setClauseToDelete(String clauseToDelete) {
			this.clauseToDelete = clauseToDelete;
		}
		public String getClauseToModify() {
			return clauseToModify;
		}
		public void setClauseToModify(String clauseToModify) {
			this.clauseToModify = clauseToModify;
		}
		public int getClauseToSeqNo() {
			return clauseToSeqNo;
		}
		public void setClauseToSeqNo(int clauseToSeqNo) {
			this.clauseToSeqNo = clauseToSeqNo;
		}
		public int getClauseToVerNo() {
			return clauseToVerNo;
		}
		public void setClauseToVerNo(int clauseToVerNo) {
			this.clauseToVerNo = clauseToVerNo;
		}
		public String getCommentsAdd() {
			return commentsAdd;
		}
		public void setCommentsAdd(String commentsAdd) {
			this.commentsAdd = commentsAdd;
		}
		public String getCommentsChngComp() {
			return commentsChngComp;
		}
		public void setCommentsChngComp(String commentsChngComp) {
			this.commentsChngComp = commentsChngComp;
		}
		public String getCommentsMod() {
			return commentsMod;
		}
		public void setCommentsMod(String commentsMod) {
			this.commentsMod = commentsMod;
		}
		public ArrayList getComponentGrpList() {
			return componentGrpList;
		}
		public void setComponentGrpList(ArrayList componentGrpList) {
			this.componentGrpList = componentGrpList;
		}
		public int getComponentGrpSeqNoinAdd() {
			return componentGrpSeqNoinAdd;
		}
		public void setComponentGrpSeqNoinAdd(int componentGrpSeqNoinAdd) {
			this.componentGrpSeqNoinAdd = componentGrpSeqNoinAdd;
		}
		public ArrayList getComponentList() {
			return componentList;
		}
		public void setComponentList(ArrayList componentList) {
			this.componentList = componentList;
		}
		public ArrayList getComponentVO() {
			return componentVO;
		}
		public void setComponentVO(ArrayList componentVO) {
			this.componentVO = componentVO;
		}
		public String getDeleterow() {
			return deleterow;
		}
		public void setDeleterow(String deleterow) {
			this.deleterow = deleterow;
		}
		public String getDwoNoAdd() {
			return dwoNoAdd;
		}
		public void setDwoNoAdd(String dwoNoAdd) {
			this.dwoNoAdd = dwoNoAdd;
		}
		public String getDwoNoChngComp() {
			return dwoNoChngComp;
		}
		public void setDwoNoChngComp(String dwoNoChngComp) {
			this.dwoNoChngComp = dwoNoChngComp;
		}
		public String getDwoNoMod() {
			return dwoNoMod;
		}
		public void setDwoNoMod(String dwoNoMod) {
			this.dwoNoMod = dwoNoMod;
		}
		public String getHdnClauseToDelete() {
			return hdnClauseToDelete;
		}
		public void setHdnClauseToDelete(String hdnClauseToDelete) {
			this.hdnClauseToDelete = hdnClauseToDelete;
		}
		public int getHdnClauseToDeleteSeqNo() {
			return hdnClauseToDeleteSeqNo;
		}
		public void setHdnClauseToDeleteSeqNo(int hdnClauseToDeleteSeqNo) {
			this.hdnClauseToDeleteSeqNo = hdnClauseToDeleteSeqNo;
		}
		public String getHdnClauseToModify() {
			return hdnClauseToModify;
		}
		public void setHdnClauseToModify(String hdnClauseToModify) {
			this.hdnClauseToModify = hdnClauseToModify;
		}
		public int getHdnClauseToModifySeqNo() {
			return hdnClauseToModifySeqNo;
		}
		public void setHdnClauseToModifySeqNo(int hdnClauseToModifySeqNo) {
			this.hdnClauseToModifySeqNo = hdnClauseToModifySeqNo;
		}
		public String getHdnLeadComponentNameinAdd() {
			return hdnLeadComponentNameinAdd;
		}
		public void setHdnLeadComponentNameinAdd(String hdnLeadComponentNameinAdd) {
			this.hdnLeadComponentNameinAdd = hdnLeadComponentNameinAdd;
		}
		public int getHdnLeadComponentSeqNoinAdd() {
			return hdnLeadComponentSeqNoinAdd;
		}
		public void setHdnLeadComponentSeqNoinAdd(int hdnLeadComponentSeqNoinAdd) {
			this.hdnLeadComponentSeqNoinAdd = hdnLeadComponentSeqNoinAdd;
		}
		public String getHdnOldComponentName() {
			return hdnOldComponentName;
		}
		public void setHdnOldComponentName(String hdnOldComponentName) {
			this.hdnOldComponentName = hdnOldComponentName;
		}
		public int getHdnOldComponentSeqNo() {
			return hdnOldComponentSeqNo;
		}
		public void setHdnOldComponentSeqNo(int hdnOldComponentSeqNo) {
			this.hdnOldComponentSeqNo = hdnOldComponentSeqNo;
		}
		public int getHdnParentClauseSeqNo() {
			return hdnParentClauseSeqNo;
		}
		public void setHdnParentClauseSeqNo(int hdnParentClauseSeqNo) {
			this.hdnParentClauseSeqNo = hdnParentClauseSeqNo;
		}
		public String getHdnSectionName() {
			return hdnSectionName;
		}
		public void setHdnSectionName(String hdnSectionName) {
			this.hdnSectionName = hdnSectionName;
		}
		public String getHdnSubSectionName() {
			return hdnSubSectionName;
		}
		public void setHdnSubSectionName(String hdnSubSectionName) {
			this.hdnSubSectionName = hdnSubSectionName;
		}
		public String[] getNewEdlNoAdd() {
			return newEdlNoAdd;
		}
		public void setNewEdlNoAdd(String[] newEdlNoAdd) {
			this.newEdlNoAdd = newEdlNoAdd;
		}
		public String[] getNewEdlNoChngComp() {
			return newEdlNoChngComp;
		}
		public void setNewEdlNoChngComp(String[] newEdlNoChngComp) {
			this.newEdlNoChngComp = newEdlNoChngComp;
		}
		public String[] getNewEdlNoMod() {
			return newEdlNoMod;
		}
		public void setNewEdlNoMod(String[] newEdlNoMod) {
			this.newEdlNoMod = newEdlNoMod;
		}
		public String getPartNoAdd() {
			return partNoAdd;
		}
		public void setPartNoAdd(String partNoAdd) {
			this.partNoAdd = partNoAdd;
		}
		public String getPartNoChngComp() {
			return partNoChngComp;
		}
		public void setPartNoChngComp(String partNoChngComp) {
			this.partNoChngComp = partNoChngComp;
		}
		public String getPartNoMod() {
			return partNoMod;
		}
		public void setPartNoMod(String partNoMod) {
			this.partNoMod = partNoMod;
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
		public String getPriceBookChngComp() {
			return priceBookChngComp;
		}
		public void setPriceBookChngComp(String priceBookChngComp) {
			this.priceBookChngComp = priceBookChngComp;
		}
		public String getPriceBookNoAdd() {
			return priceBookNoAdd;
		}
		public void setPriceBookNoAdd(String priceBookNoAdd) {
			this.priceBookNoAdd = priceBookNoAdd;
		}
		public String getPriceBookNoMod() {
			return priceBookNoMod;
		}
		public void setPriceBookNoMod(String priceBookNoMod) {
			this.priceBookNoMod = priceBookNoMod;
		}
		public String getReasonAdd() {
			return reasonAdd;
		}
		public void setReasonAdd(String reasonAdd) {
			this.reasonAdd = reasonAdd;
		}
		public String getReasonChngComp() {
			return reasonChngComp;
		}
		public void setReasonChngComp(String reasonChngComp) {
			this.reasonChngComp = reasonChngComp;
		}
		public String getReasonDel() {
			return reasonDel;
		}
		public void setReasonDel(String reasonDel) {
			this.reasonDel = reasonDel;
		}
		public String getReasonMod() {
			return reasonMod;
		}
		public void setReasonMod(String reasonMod) {
			this.reasonMod = reasonMod;
		}
		public String[] getRefEdlNoAdd() {
			return refEdlNoAdd;
		}
		public void setRefEdlNoAdd(String[] refEdlNoAdd) {
			this.refEdlNoAdd = refEdlNoAdd;
		}
		public String[] getRefEdlNoChngComp() {
			return refEdlNoChngComp;
		}
		public void setRefEdlNoChngComp(String[] refEdlNoChngComp) {
			this.refEdlNoChngComp = refEdlNoChngComp;
		}
		public String[] getRefEdlNoMod() {
			return refEdlNoMod;
		}
		public void setRefEdlNoMod(String[] refEdlNoMod) {
			this.refEdlNoMod = refEdlNoMod;
		}
		public ArrayList getReqSpecChngClauseList() {
			return reqSpecChngClauseList;
		}
		public void setReqSpecChngClauseList(ArrayList reqSpecChngClauseList) {
			this.reqSpecChngClauseList = reqSpecChngClauseList;
		}
		public int getReviseClauseCheck() {
			return reviseClauseCheck;
		}
		public void setReviseClauseCheck(int reviseClauseCheck) {
			this.reviseClauseCheck = reviseClauseCheck;
		}
		public ArrayList getReviseClauseDetailsList() {
			return reviseClauseDetailsList;
		}
		public void setReviseClauseDetailsList(ArrayList reviseClauseDetailsList) {
			this.reviseClauseDetailsList = reviseClauseDetailsList;
		}
		public ArrayList getSectionList() {
			return sectionList;
		}
		public void setSectionList(ArrayList sectionList) {
			this.sectionList = sectionList;
		}
		public int getSectionSeqNo() {
			return sectionSeqNo;
		}
		public void setSectionSeqNo(int sectionSeqNo) {
			this.sectionSeqNo = sectionSeqNo;
		}
		public ArrayList getStandardEquipList() {
			return standardEquipList;
		}
		public void setStandardEquipList(ArrayList standardEquipList) {
			this.standardEquipList = standardEquipList;
		}
		public String getStandardEquipmentListAdd() {
			return standardEquipmentListAdd;
		}
		public void setStandardEquipmentListAdd(String standardEquipmentListAdd) {
			this.standardEquipmentListAdd = standardEquipmentListAdd;
		}
		public int getStandardEquipmentSeqNoAdd() {
			return standardEquipmentSeqNoAdd;
		}
		public void setStandardEquipmentSeqNoAdd(int standardEquipmentSeqNoAdd) {
			this.standardEquipmentSeqNoAdd = standardEquipmentSeqNoAdd;
		}
		public int getStandardEquipmentSeqNoChngComp() {
			return standardEquipmentSeqNoChngComp;
		}
		public void setStandardEquipmentSeqNoChngComp(int standardEquipmentSeqNoChngComp) {
			this.standardEquipmentSeqNoChngComp = standardEquipmentSeqNoChngComp;
		}
		public int getStandardEquipmentSeqNoMod() {
			return standardEquipmentSeqNoMod;
		}
		public void setStandardEquipmentSeqNoMod(int standardEquipmentSeqNoMod) {
			this.standardEquipmentSeqNoMod = standardEquipmentSeqNoMod;
		}
		public ArrayList getSubSectionList() {
			return subSectionList;
		}
		public void setSubSectionList(ArrayList subSectionList) {
			this.subSectionList = subSectionList;
		}
		public int getSubSectionSeqNo() {
			return subSectionSeqNo;
		}
		public void setSubSectionSeqNo(int subSectionSeqNo) {
			this.subSectionSeqNo = subSectionSeqNo;
		}
		public String getAddClauseDescription() {
			return addClauseDescription;
		}
		public void setAddClauseDescription(String addClauseDescription) {
			this.addClauseDescription = addClauseDescription;
		}
		public ArrayList getCategories() {
			return categories;
		}
		public void setCategories(ArrayList categories) {
			this.categories = categories;
		}
		public int getCategorySeqNo() {
			return categorySeqNo;
		}
		public void setCategorySeqNo(int categorySeqNo) {
			this.categorySeqNo = categorySeqNo;
		}
		public ArrayList getClauseChangeTypes() {
			return clauseChangeTypes;
		}
		public void setClauseChangeTypes(ArrayList clauseChangeTypes) {
			this.clauseChangeTypes = clauseChangeTypes;
		}
		public String getComponentGroup() {
			return componentGroup;
		}
		public void setComponentGroup(String componentGroup) {
			this.componentGroup = componentGroup;
		}
		public String getComponents() {
			return components;
		}
		public void setComponents(String components) {
			this.components = components;
		}
		public String getDataLocType() {
			return dataLocType;
		}
		public void setDataLocType(String dataLocType) {
			this.dataLocType = dataLocType;
		}
		public String getFilePath() {
			return filePath;
		}
		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}
		public int getImgSeqNo() {
			return imgSeqNo;
		}
		public void setImgSeqNo(int imgSeqNo) {
			this.imgSeqNo = imgSeqNo;
		}
		public int getOrderKey() {
			return orderKey;
		}
		public void setOrderKey(int orderKey) {
			this.orderKey = orderKey;
		}
		public ArrayList getReqTypes() {
			return reqTypes;
		}
		public void setReqTypes(ArrayList reqTypes) {
			this.reqTypes = reqTypes;
		}
		public int getReqTypeSeqNo() {
			return reqTypeSeqNo;
		}
		public void setReqTypeSeqNo(int reqTypeSeqNo) {
			this.reqTypeSeqNo = reqTypeSeqNo;
		}
		public String getSections() {
			return sections;
		}
		public void setSections(String sections) {
			this.sections = sections;
		}
		public String getSubSections() {
			return subSections;
		}
		public void setSubSections(String subSections) {
			this.subSections = subSections;
		}
		public int[] getComponent() {
			return component;
		}
		public void setComponent(int[] component) {
			this.component = component;
		}
		public String getComponentSeqNo() {
			return componentSeqNo;
		}
		public void setComponentSeqNo(String componentSeqNo) {
			this.componentSeqNo = componentSeqNo;
		}
		public String getHdncomponentGroupSeqNo() {
			return hdncomponentGroupSeqNo;
		}
		public void setHdncomponentGroupSeqNo(String hdncomponentGroupSeqNo) {
			this.hdncomponentGroupSeqNo = hdncomponentGroupSeqNo;
		}
		public String getHdnComponentName() {
			return hdnComponentName;
		}
		public void setHdnComponentName(String hdnComponentName) {
			this.hdnComponentName = hdnComponentName;
		}
		public String getSpecRev() {
			return specRev;
		}
		public void setSpecRev(String specRev) {
			this.specRev = specRev;
		}
		public FormFile getAttachmentSource() {
			return attachmentSource;
		}
		public void setAttachmentSource(FormFile attachmentSource) {
			this.attachmentSource = attachmentSource;
		}
		public int getCountDays() {
			return countDays;
		}
		public void setCountDays(int countDays) {
			this.countDays = countDays;
		}
		public String getActualSellPrice() {
			return actualSellPrice;
		}
		public void setActualSellPrice(String actualSellPrice) {
			this.actualSellPrice = actualSellPrice;
		}
		public String getMarkUp() {
			return markUp;
		}
		public void setMarkUp(String markUp) {
			this.markUp = markUp;
		}
		public String getReworkCost() {
			return reworkCost;
		}
		public void setReworkCost(String reworkCost) {
			this.reworkCost = reworkCost;
		}
		public String getScrapCostUSD() {
			return scrapCostUSD;
		}
		public void setScrapCostUSD(String scrapCostUSD) {
			this.scrapCostUSD = scrapCostUSD;
		}
		public String getSellpriceCustomer() {
			return sellpriceCustomer;
		}
		public void setSellpriceCustomer(String sellpriceCustomer) {
			this.sellpriceCustomer = sellpriceCustomer;
		}
		public String getToolingCostUSD() {
			return toolingCostUSD;
		}
		public void setToolingCostUSD(String toolingCostUSD) {
			this.toolingCostUSD = toolingCostUSD;
		}
		public String getWorkOrderUSD() {
			return workOrderUSD;
		}
		public void setWorkOrderUSD(String workOrderUSD) {
			this.workOrderUSD = workOrderUSD;
		}
		public int getVersionNoMod() {
			return versionNoMod;
		}
		public void setVersionNoMod(int versionNoMod) {
			this.versionNoMod = versionNoMod;
		}
		public int getHdnVersionNoMod() {
			return hdnVersionNoMod;
		}
		public void setHdnVersionNoMod(int hdnVersionNoMod) {
			this.hdnVersionNoMod = hdnVersionNoMod;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		public String getId1058() {
			return id1058;
		}
		public void setId1058(String id1058) {
			this.id1058 = id1058;
		}
		public String getHdnSpecRev() {
			return hdnSpecRev;
		}
		public void setHdnSpecRev(String hdnSpecRev) {
			this.hdnSpecRev = hdnSpecRev;
		}
		public String getClauseNumber() {
			return clauseNumber;
		}
		public void setClauseNumber(String clauseNumber) {
			this.clauseNumber = clauseNumber;
		}
		public int getChangeTypeSeqNo() {
			return changeTypeSeqNo;
		}
		public void setChangeTypeSeqNo(int changeTypeSeqNo) {
			this.changeTypeSeqNo = changeTypeSeqNo;
		}
		public String getClauseDesc() {
			return clauseDesc;
		}
		public void setClauseDesc(String clauseDesc) {
			this.clauseDesc = clauseDesc;
		}
		public String getEnggData() {
			return enggData;
		}
		public void setEnggData(String enggData) {
			this.enggData = enggData;
		}
		public ArrayList getImportSubSecList() {
			return importSubSecList;
		}
		public void setImportSubSecList(ArrayList importSubSecList) {
			this.importSubSecList = importSubSecList;
		}
		public int getImportSubSecListSize() {
			return importSubSecListSize;
		}
		public void setImportSubSecListSize(int importSubSecListSize) {
			this.importSubSecListSize = importSubSecListSize;
		}
		public String getSubSection() {
			return subSection;
		}
		public void setSubSection(String subSection) {
			this.subSection = subSection;
		}
		public String getReasonSubSec() {
			return reasonSubSec;
		}
		public void setReasonSubSec(String reasonSubSec) {
			this.reasonSubSec = reasonSubSec;
		}
		public ArrayList getSubSec1058List() {
			return SubSec1058List;
		}
		public void setSubSec1058List(ArrayList subSec1058List) {
			SubSec1058List = subSec1058List;
		}
		public int getSecChangeTypeSeqNo() {
			return secChangeTypeSeqNo;
		}
		public void setSecChangeTypeSeqNo(int secChangeTypeSeqNo) {
			this.secChangeTypeSeqNo = secChangeTypeSeqNo;
		}
		
		
		
}
