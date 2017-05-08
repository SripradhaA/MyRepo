/*
 * Created on Jun 14, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.common.constant;

/**
 * @author Satyam
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

    /***************************************************************************
	 * ------------------------------------------------------------------------------------------------------
	 *     Date         version  create by          comments                              Remarks 
	 * 19/01/2010        1.0      SD41630          Added  for view characterisitic     Added for CR_81
	 * 											   group EDl MApping.   
	 * 													 	 
	 * 16/02/2010        1.1      SD41630          Added for maintaining specTypeNo     Added for CR_83
	 * 												value in the session
	 * 03/05/2010        1.2      SD41630			Added for maintaining dynamicNoOff
	 * 												value in the session                 Added for CR_86
	 * * 03/05/2010        1.2      SD41630			Added for forword the request
	 * 												                                      Added for CR_85
	 * --------------------------------------------------------------------------------------------------------
	 * **************************************************************************/
public interface ApplicationConstants {
	
	public final static String LOG4J_URL = "LOG4J_URL";
	
	//	CR_91
	public final static String PM_I_SPEC_SUPPLIMENT = "ENGINE SUPPLEMENT";
	
	public final static String LOCOMOTIVE_SPEC_SUPPLIMENT = "LOCOMOTIVE SUPPLEMENT";
	
	public final static String REF_EDL = "(ref EDL ";
	
	public final static String REF_EDL_END = ")";
	
	public final static String EDL = "EDL ";
	
	public final static String PARTOF = "Part of ";
	
	public final static String SUCCESS = "success";
	
	public final static String DWO ="DWO ";
	
	public final static String CHAR_GRP_RPT_SUCCESS = "chargrprptSuccess";
	
	public final static String PART_NUMBER = "Part No ";
	
	public final static String FAILURE = "failure";
	
	//public final static String CHAR_GRP_RPT_FAILURE = "chargrprptfailure";
	
	public final static String COMPINFO = "AddCompInfo";
	
	public final static String USER_IN_SESSION = "UserInSession";
	
	public final static String ERROR_PATH = "ErrorListPath";
	
	public final static String TABLE_HEADER_PATH = "TableHeaderPath";
	
	public final static String SPEC_FORWARD = "specification";
	
	public final static String MODIFY_MODEL_SPEC_FORWARD = "modelspecmodify";
	
	public final static String SPEC_ITEM_FORWARD = "specificationitem";
	
	public final static String SPEC_ORDR_ITEM_FORWARD = "orderspecitem";
	
	// Added for LSDB_CR-64
	public final static String SPEC_ORDR_MODIFY_FORWARD = "orderspecmodify";
	
	public final static String SPEC_ORDR_FORWARD = "orderspec";
	
	public final static String SUCCESS_MESSAGE_ID = "6";
	//Added For CR_104
	public final static String NO_RECORD_MESSAGE_ID = "16";
	
	public final static String SUCCESS_PASSWORD_ID = "151";
	
	public final static String MESSAGE_ID = "6";
	
	public final static String YES = "Y";
	
	public final static String NO = "N";
	
	public final static String NO_SECTION_MESSAGE_ID = "519";
	
	public final static String NO_SUB_SECTION_MESSAGE_ID = "511";
	
	public static final String PARENT_CLAUSE = "parentClauseOpen";
	
	public static final String SELECTED_MODEL_NAME = "selectedModelName";
	
	public static final String SELECTED_SECTION_NAME = "selectedSectionName";
	
	public static final String SELECTED_SUB_SECTION_NAME = "selectedSubSectionName";
	
	public static final String SELECTED_SUB_SECTION_ID = "selectedSubSectionID";
	
	public static final String SELECTED_MODEL_ID = "selectedModelID";
	
	public static final String TEXT_BOX_INDEX = "textBoxIndex";
//	CR_83 lines are started here
	public static final String SPEC_TYPE_NO = "SpecTypeNo";
	//CR_83 lines ends here
	public static final String MODEL_SEQ_NO = "ModelSeqNo";
	
	public static final String CUSTOMER_SEQ_NO = "CustSeqNo";
	
	public static final String SECTION_SEQ_NO = "SectionSeqNo";
	
	public static final String SUB_SECTION_SEQ_NO = "SubSectionSeqNo";
	
	public static final String STANDARD_EQUIPMENT_SEQ_NO = "StandardEquipNo";
	
	public static final String NO_PARENT_CLAUSE = "521";
	
	public static final String SECTION_VIEW = "sectionview";
	
	public static final String NO_ORDER_GENARRANGE_IMAGE = "16";
	
	public static final String ORDER_SECTION_POPUP = "OrderSectionPopUp";
	
	public static final String NO_PART_OF_ORDER_SECTION = "16";
	
	public static final String ORDER_SECTION_POPUP_FAILURE = "307";
	
	public static final String SUB_SEC_SEQ_NO = "subSecSeqNo";
	
	public static final String SUB_SEC_CODE = "subSecCode";
	
	public static final String SUB_SEC_NAME = "subSecName";
	
	public static final String ORDER_KEY = "orderKey";
	
	public static final String ORDER_NUMBER = "OrderNum";
	
	public static final String SEC_SEQ_NO = "secSeqNo";
	
	public static final String CLAUSE_SEQ_NO = "clauseSeqNo";
	
	public static final String RELOAD_SPEC = "reloadspec";
	
	public static final String SUCCESS_PUBLISH = "publishsuccess";
	
	public static final String VERSION_NO = "versionNo";
	
	public static final String REVISION_CODE = "revCode";
	
	public static final String SEC_NAME = "secName";
	
	public static final String SEC_CODE = "secCode";
	
	public static final String SELECTED_MODEL = "selectedModel";
	
	public static final String MODEL_NAME = "modelName";
	
	public static final String CUSTOMER_NAME = "CustomerName";
	
	public static final String SPEC_STATUS = "SpecStatus";
	
	public static final String NO_RECORDS_FOUND = "16";
	
	public static final int Reset_Dropdown = -1;
	
	public static final String HOME_PAGE = "Home";
	
	public static final String HELP_PAGE = "Help";
	
	public final static String URLRESTRICTION = "Blocked";
	
	public static final String POP_UP = "popup";
	
	public static final String POP_UP_FOR_EXCEL = "popupForExcel";
	
	public static final String ORDER_KEYS = "orderKeys";
		
	public static final String SECTION_IDS = "sectionIds";
	
	public static final String CUST_NAME = "customerName";
	
	public static final String SELECTED_MODELS = "selectedModels";
	
	public static final String SELECTED_SPEC_TYPE = "selectedSpecType";
	
	public static final String ORDER_NUMBERS = "orderNumbers";
	
	public static final String COMMA = ",";
	
	public static final String DATA_lOCATION_TYPE = "S";
	
	public static final String EMPTY_STRING = "";
	
	public static final String SELECTED_SECTION = "selectedSection";
	
	public static final String SPEC_STATUS_NO = "1";
	
	public static final int DEFAULT_REV_CODE = 1;
	
	public static final String PARENT_SEC_SEQ_NO = "parentSecSeqNo";
	
	public static final String LOG_USER = "UserID-";
	
	public static final String RESETPASSWORD = "password";
	
	public static final String RESETPASSWORD_FLAG = "R";
	
	public static final String CHANGEPASSWORD_FLAG = "C";
	
	public static final String DELETE_MESSAGE_ID = "791";
	
	public static final String CLAUSE_SOURCE_MODEL = "M";
	
	/** ** Added For LSDB_CR-35 ** */
	
	public static final String CLAUSE_SOURCE_ORDER = "O";
	
	/** ** Added For LSDB_CR-35 ** */
	
	public static final String POP_UP_DIFF_COMP = "DiffCompPopup";
	
	/** ** Added For LSDB_CR-06 ** */
	
	// Added for CR-26 Component Group/Component Report
	public static final String COMP_GRP_REPORT = "compreport";
	
	public static final String COMP_GRP_REPORT_EXCEL = "compgrpexcel";
	
	// Added for CR-4 Delete Section and Sub-Section
	
	public static final String DELETE_SECTION_MESSAGE_ID = "806";
	
	public static final String DELETE_SUBSEC_MESSAGE_ID = "796";
	
	// Added for CR-42
	public static final String SELECT_CLAUSE = "selectclause";
	
	public static final String VIEW_MAP = "viewmap";
	
	public static final String IMAGE_SIZE_VALIDATION = "811";
	
	// Added for LSDB_CR_46
	public static final String SPEC_TYPE = "spectype";
	
	public static final int LOCOMOTIVE_ONE = 1;
	
	/**
	 * Added for LSDB_CR-45 added on 21-Oct-08 by ps57222
	 */
	
	public static final String CRForm_Select_Clause = "CRFormSelectClause";
	
	public static final String CRForm_Select_Parent_Clause = "CRFormSelectParentClause";
	
	public static final String CRForm_Select_Clause_Version = "CRFormSelectClauseVersion";
	
	public static final String ADMN = "ADMN";
	
	public static final String MSE = "MSE";
	
	public static final int IN_PROGRESS_ONE = 1;
	
	public static final int SUBMITTED_TWO = 2;
	
	public static final int ONHOLD_THREE = 3;
	
	public static final int APPROVED_FOUR = 4;
	
	public static final int REJECTED_FIVE = 5;
	
	public static final int COMPLETED_SIX = 6;
	
	public static final int CHANGE_TYPE_DESC_DEFAULT_VERSION = 6;
	
	// Added for changing replace with <br> in DAO
	public static final String HTML_BREAK = "<br>";
	
	public static final String DELIMITER = "~";
	
	// Added for CR-67 
	public static final String UNMAPCOMPONENT="UNMAPCOMPONENT";

	//Added for redirecting screen from Create spec to Modify spec CR
	public static final int ORDER_ID_EXIST = 600;

	//Added for CR-68 Order New Component
	public static final String ORDER_NEW_COMP="OrderNewComp";
	/*Added for Clause comparision Bo by cm68219*/
	public static final int SECTION_SEQ_SIZE=1;
	public static final String EDL_POP_UP = "popupforedl";
	public static final String EDL_POP_UP_EXCEL="EDLPOPUPEXCEL";
	
	//Added for LSDB_CR-74
	public static final String CLAUSE_REASON_VIEW ="displayreason";
	
	//Added for CR_80 CR Form Enhancements III on 25-Nov-09 by RR68151 - Ends Here
	public static final int CHANGE_TYPE_DESC_DELETE_CLAUSE_VERSION = 9;
	
	public static final String CRFORM_EFFECTED_CLAUSES = "CRFormEffectedClauses";
	
	public static final int CHANGE_TYPE_NOT_REQUIRED = 7;
	
	public static final int CHANGE_TYPE_DELETE_COMPGRP = 8;
	
	public static final String REQUEST_ID = "requestID";

	//Added For CR_81 on 28-Dec-09 by RR68151
	public static final int COMPGRPTYPE_ZERO = 0;
	
	public static final int COMPGRPTYPE_CHARSTC = 2;
	
	//Added for CR_81 om 08/01/2010 by SD 41630 - for fetchCharCombntnEDLMap
	public static final String FETCH_CHAR_COMBNTN_EDL_MAP ="FetchCharCombntnEDLMap";
	
//	Added for CR_86
	public static final String DYNAMIC_NO_OFF = "dynamicNoOff";
//	CR_85 Added
	public final static String POPUP_SUCCESS = "popupsuccess";
	public static final String FETCH_CHAR_COMBNTN ="fetchcharcombntnSuccess";
	public static final String CLOSE_POPUP="closepopup";
	public static final String View_Unlink_Clause="viewunlinkclause";
	public static final String Unlink_Clause="unlinkclause";
	//CR_91
	public static final String SUCCESS_EXCEL="successexcel";
	//CR_92
	public static final String DELETED_CLAUSES_HISTORY_VIEW = "deletedClausesHistory";
	public static final String VIEW_MAP_CLAUSE="ViewMapClause";
//	 Added for CR_93
	public static final String COPY_ORDER_COMPONENT="CopyOrderComponent";
	
	//Added For CR_100
	public static final int PENDING_ONE = 1;
	public static final int ACCEPTED_ONE = 2;
	public static final int REJECTED_ONE = 3;
	public static final String HOMEPAGE_SCREENID = "24";
	public static final String FILESIZETOOLARGE = "filesizetoolarge";
	//Ends here
	
	//Added For CR_101
	public final static String LXO_SPEC_SUPPLIMENT = "LOCOMOTIVE EXPORT ORDER KIT SUPPLEMENT";
	//Ends here
//Added For CR_106
	public final static String IMAGE = "image";

	//Added For CR_108 -title name in tab
	public final static String HEADER_ENV_INFO = "HEADER_ENV_INFO"; 

	// Added for CR_108 Components In Order Report
	public static final String COMP_IN_ORDER_REPORT = "compinorderreport";
	public static final String COMP_IN_ORDER_EXCEL = "compinorderexcel";
	
	//Added for CR_109 To bring components from Multiple Models
	public static final String LOAD_ALL_COMP = "LoadAllComp";
	public static final int COMP_MAP_EXISTS_ID = 226;
	public static final String LOCOMOTIVE_MARKED_REPORT = "LOCOMOTIVE MARKED CLAUSE REPORT";
	public static final String PM_I_MARKED_REPORT = "ENGINE MARKED CLAUSE REPORT";
	public static final String LXO_MARKED_REPORT = "LOCOMOTIVE EXPORT ORDER KIT MARKED CLAUSE REPORT";
	
	//Added for CR-112 to Add Message of the Day in home screen
	public static final String MOD = "Message";
	public final static String RESET_PASSWORD_ID = "934";
	
	//Added for CR-113
	public static final String CLAUSES_WITH_INDICATORS = "ClausesWithIndicators";
	public static final String BROADCASTEMAIL = "BroadcastEmail";
	public static final String CLA_IN_ORDERS_REPORT = "clainordersreport";
	public static final String CLA_IN_ORDERS_REPORT_EXCEL = "clainordersreportexcel";
	public static final int ORDERBY_FLAG_ONE = 1;
	
	//Added for CR-110
	public static final int SECTION_SEQ_NO_ZERO = 0;
	public static final int SECTION_STATUS_COMPLETE = 5;
	public static final String CREATE = "create";
	public static final String PRICE_BOOK_NO="Price Book No ";
	public static final String SE = "SE";
	public static final String VALID_USER = "VALID_USER";
	public static final String OWNER = "OWNER";
	public static final String ParentClause="ParentClause";
	public final static String REASSIGN = "R";
	public static final String CR1058_Select_Clause_Version="CR1058SelectClauseVersion";
	
	//Added for CR-117
	public static final String LEGACY1058REQUESTS = "1058LegacyRequest";
	//public static final String FILEPATH = "\\"+"\\"+"cmslnxfps001.mahindrasatyam.com"+"\\"+"EMD-LSDB"+"\\"+"LegacyReport";
	
	//Onsite Dev
	String LEGACY_FILE_LOC_PATH = "/data/lsdb_share/dev/Legacy1058Requests";	
	String LEGACY_FILE_SERVER_PATH = "file://crsisil02/lsdb_qa/";
	
    //crsisil02\lsdb_dev -- lsdb_share/dev/
	
    //QA
    //String LEGACY_FILE_LOC_PATH = "/data/lsdb_share/qa/Legacy1058Requests/";
    //String LEGACY_FILE_SERVER_PATH = "file://crsisil02/lsdb_qa/";// Commented as it is doubtful //Legacy1058Requests/

	
    
	//Prod 
	
    //String LEGACY_FILE_LOC_PATH = "/data/lsdb_prod/specs/Legacy1058Requests/";

    //String LEGACY_SERVER_PATH = "file://crsisil02/lsdb_prod/Legacy1058Requests/";
	
	public final static String FILESIZELARGE = "1029";//Updated value from 999 to 1029 for CR-124
	public static final int SECTION_STATUS_CANCEL = 10;
	public final static String FILEEXISTS = "1020";
	
	//Added for CR_118 
	public final static String All_MODELS = "A";
	//public final static String HIDDEN_MODELS = "Y";
	public final static String NON_HIDDEN_MODELS = "N";
	public static final String VIEW_SELECTED_SUMMARY_REPORT = "1058SummaryReport";
	//Added for CR_118 Ends
	
//	Added for CR_121 Starts Here
	public static final String VIEW_COMP_IN_ORDER_MAP_REPORT = "viewcompinordermapreport";

	public static final String SERVER_IP_PROD = "143.242.67.177";
	public static final String SERVER_IP_QA = "143.242.67.254";
	public static final String SERVER_IP_DEV = "172.18.140.72";
	
	public static final String SERVER_QA = " - QA";
	public static final String SERVER_PROD = "";
	public static final String SERVER_DEV = " Development Server";
	
	//Added for CR_124
	public static final String PURGEEMAIL = "PurgeEmail";
	
	//Added for CR-126
	public static final String REPORTS = "1058Reports";
	public static final String VIEW_UNAPPROVED_1058_WITH_CLAUSE_APPLIED = "ViewUnapproved1058withClauseApplied";
	public static final String VIEW_1058_CLAUSE_APPLIED_TO_SPEC = "View1058ClauseAppliedtoSpec";
	public static final String MANAGE_EMAIL_SUBSCRIPTIONS = "ManageEmailSubscriptions";
	public final static String EMAILFLAG = "EmailFlag";
	
	//Added for CR-127
	public static final String VIEW_PENDING_1058s = "viewPending1058s";
    //Added for Cr-128
	public static final String ACTIVITY_LOG = "activityLog";
	//Added for CR-130
	public static final String MAP_1058_ID = "1033";
	
	//Added for CR-130 QA Fix
	public static final String SEARCH1058_SCREENID = "47";
	
	//Added fr CR-134
	public static final String CHANGEPSW = "changePswd";
	
	//Added for CR-135
	public static final String ORDER_MODEL_POP_UP = "orderModelPopup";
	public static final String DATA_LOC_TYPE = "dataLocType";
	
	//Added for CR-135 PDF Attachments
	public static final String emdlsdb = "/EMD-LSDB";
	
}