/*
 * Created on Jun 15, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.common.constant;

/**
 * @author ps57222
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
/***********************************************************************
----------------------------------------------------------------------------------------------------------
*    Date     Version   Modified by    	     Comments                              		Remarks 
* 19/01/2010    1.0      RR68151         Added list of DatabaseConstants     		   Added for CR_81
* 										  
* 03/05/2010    1.2     SD41630	       Added Dynamic clause num ON/OFF flag             Added for CR_86	
* 										of DatabaseConstants		                												 	 
* 03/05/2010  1.3    SD41630	       Added no of links to get the total              Added for CR_85	
* 										no of clause are tied
* 03/07/2010  1.4    SD41630	       Added LS300_CLA_CODE and  CHILD_FLAG fields              Added for CR_85
* 16/03/2012  1.5    SD41630	       Added for customer and distributor logs.               Added for CR_106
*                                      Added for genaral info text Draft amd open             Added for CR_106
* --------------------------------------------------------------------------------------------------------
**************************************************************************/
public interface DatabaseConstants {
	
	public static final String CONTEXT_FACTORY = "CONTEXT_FACTORY";
	
	public static final String JNDI_NAME = "JNDI_NAME";
	
	public static final String PROVIDER_URL_VALUE = "PROVIDER_URL_VALUE";
	
	public static final String USERNAME = "LS010_USER_ID";
	
	public static final String PASSWORD = "LS010_PWD";
	
	public static final String DATALOCATION = "W";
	
	public static final String MODEL_SEQ_NO = "LS200_MDL_SEQ_NO";
	
	public static final String MODEL_NAME = "LS200_MDL_NAME";
	
	public static final String MODEL_DESC = "LS200_MDL_DESC";
	
	public static final String HORSE_POWER_RATE = "LS200_HP_DESC";
	
	public static final String SPEC_TYPE_SEQ_NO = "LS030_SPEC_TYPE_SEQ_NO";
	
	public static final String CUS_TYPE_SEQ_NO = "LS040_CUST_TYPE_SEQ_NO";
	
	public static final String SPEC_TYPE = "LS030_SPEC_TYPE";
	
	public static final String LS010_USER_ID = "LS010_USER_ID";
	
	public static final String LS010_PWD = "LS010_PWD";
	
	public static final String LS01_SPEC_TYPE_SEQ_NO = "SpecType Id";
	
	public static final String LS01_SPEC_TYPE = "SpecType Name";
	
	public static final String LS410_UPDT_DATE = "LS410_UPDT_DATE";
	
	/** ** Used For Section Maintenance ********** */
	public static final String LS201_SEC_SEQ_NO = "LS201_SEC_SEQ_NO";
	
	public static final String LS201_SEC_NAME = "LS201_SEC_NAME";
	
	public static final String LS201_SEC_CODE = "LS201_SEC_CODE";
	
	public static final String LS201_SEC_DESC = "LS201_SEC_DESC";
	
	public static final String COLOUR_FLAG = "COLOUR_FLAG";
	
	public static final String DEL_COLOUR_FLAG = "DEL_COLOUR_FLAG";
	
	public static final String MDL_COLOUR_FLAG = "MDL_COLOUR_FLAG";
	
	/*Added for CR_100 */
	public static final String CPY_COLOUR_FLAG="CPY_COLOUR_FLAG";
	
	public static final String NEW_FLAG = "NEW";
		
	/** ** Used For Section Maintenance ********** */
	
	public static final String LS200_MDL_SEQ_NO = "LS200_MDL_SEQ_NO";
	
	public static final String LS120_ROLE_ID = "LS120_ROLE_ID";
	
	public static final String LS020_SCREEN_ID = "LS020_SCREEN_ID";
	
	public static final String LS200_MDL_NAME = "LS200_MDL_NAME";
	
	public static final String LS080_SPEC_STATUS_CODE = "LS080_SPEC_STATUS_CODE";
	
	public static final String LS205_MDL_SPEC_SEQ_NO = "LS205_MDL_SPEC_SEQ_NO";
	
	public static final String LS206_MDL_ITEM_SEQ_NO = "LS206_MDL_ITEM_SEQ_NO";
	
	public static final String LS206_ITEM_DESC = "LS206_ITEM_DESC";
	
	public static final String LS206_ITEM_VALUE = "LS206_ITEM_VALUE";
	
	public static final String LS205_SPEC_DESC = "LS205_SPEC_DESC";
	
	public static final String CUSTOMER_SEQ_NO = "LS050_CUST_SEQ_NO";
	
	public static final String CUSTOMER_NAME = "LS050_CUST_NAME";
	
	public static final String CUSTOMER_DESC = "LS050_CUST_DESC";
	
	public static final String DIST_SEQ_NO = "LS070_DISTRI_SEQ_NO";
	
	public static final String DIST_NAME = "LS070_DISTRI_NAME";
	
	public static final String DIST_DESC = "LS070_DISTRI_DESC";
	
	public static final String COMP_GRP_SEQ_NO = "LS130_COMP_GRP_SEQ_NO";
	
	public static final String COMP_GRP_NAME = "LS130_COMP_GRP_NAME";
	
	public static final String MDL_COMP = "MDL_COMP";
	
	public static final String COMP_GRP_DESC = "LS130_COMP_GRP_DESC";
	
	public static final String CHARZ_FLAG = "LS130_CHARZ_FLAG";
	
	public static final String LS130_VALIDATION_FLAG = "LS204_VALIDATION_FLAG";
	
	public static final String LS130_COC_FLAG = "LS130_COC_FLAG";
	
	public static final String COMP_GRP_IDENTIFIER = "LS130_COMP_GRP_IDENTIFIER";
	
	public static final String COMP_SEQ_NO = "LS140_COMP_SEQ_NO";
	
	public static final String LS140_COMP_DESC = "LS140_COMP_DESC";
	
	public static final String LS140_COMP_IDENTIFIER = "LS140_COMP_IDENTIFIER";
	
	public static final String LS130_COMP_GRP_NAME = "LS130_COMP_GRP_NAME";
	
	public static final String LS140_DISP_IN_SPEC = "LS140_DISP_IN_SPEC";
	
	public static final String COMP_NAME = "LS140_COMP_NAME";
	
	public static final String COMP_IDENTIFIER = "LS140_COMP_IDENTIFIER";
	
	public static final String DEFAULT_SELECTED = "SELECTED";
	
	public static final String COMP_DESC = "LS140_COMP_DESC";
	
	public static final String DISP_SPEC = "LS140_DISP_IN_SPEC";
	
	//Added for LSDB_CR-62
	
	public static final String LS405_DISP_IN_SPEC = "LS405_DISP_IN_SPEC";
	
	public static final String MDL_SEQ_NO = "MDL_SEQ_NO";
	
	public static final String DEFAULT_FLAG = "DEFAULT_FLAG";
	
	public static final String COPY_FLAG = "COPY_FLAG";
	
	public static final String LS406_CPY_IND = "LS406_CPY_IND";
	
	public static final String MODEL_INDICATOR = "MODEL_INDICATOR";
	
	public static final String DEL_IND = "DEL_IND";
	
	public static final String REVISION_NUMBER = "REVISION_NUMBER";
	
	public static final String REVISION_NUM = "REVISION_NUM";
	
	public static final String LS414_CLA_NO = "LS414_CLA_NO";
	
	public static final String EDLNO = "EDLNO";
	
	public static final String TABLE_DATE = "TABLE_DATE";
	
	public static final String refEDLNO = "refEDLNO";
	
	public static final String PartOF = "PartOF";
	
	public static final String STD_EQUIP = "STD_EQUIP";
	
	public static final String COMPONENTS = "COMPONENTS";
	
	public static final String LS202_SUBSEC_SEQ_NO = "LS202_SUBSEC_SEQ_NO";
	
	public static final String LS407_PART_OF_CLA_NO = "LS407_PART_OF_CLA_NO";
	
	public static final String LS202_SUBSEC_NAME = "LS202_SUBSEC_NAME";
	
	public static final String LS202_SUBSEC_DESC = "LS202_SUBSEC_DESC";
	
	public static final String SUBSEC_CODE = "SUB_CODE";
	
	public static final String LS202_SUBSEC_CODE = "LS202_SUBSEC_CODE";
	
	public static final String FLAG = "FLAG";
  /*
	 * added CR_81 for edl no and ref edl no for characterisitc group clause by sd41630
	 */
	public static final String CHRSTC_EDL_AND_REF_EDL_NO="CHRSTC_EDL_AND_REF_EDL_NO";
	
	public static final String LS428_CHARSTC_EDL_NO="LS428_CHARSTC_EDL_NO";
	
	public static final String LS428_CHARSTC_REF_EDL_NO="LS428_CHARSTC_REF_EDL_NO";
	
	public static final String EDLINDICATOR ="EDLINDICATOR";
	
	//ende here  by sd41630
	
	/*
	 * Change for Model apply flag
	 */
	
	public static final String LS504_CHNG_EFF_MDL_FLAG = "LS504_CHNG_EFF_MDL_FLAG";
	
	/** *************** used for Gen Arrangement******* */
	public static final String LS110_MDL_VIEW_SEQ_NO = "LS110_MDL_VIEW_SEQ_NO";
//	Added CR_101
	public static final String LS404_ORDR_VIEW_SEQ_NO="LS404_ORDR_VIEW_SEQ_NO";
	public static final String LS404_ORDR_VIEW_NAME="LS404_ORDR_VIEW_NAME";
	public static final String LS200_GEN_ARGMNT_NOTES = "LS200_GEN_ARGMNT_NOTES";
	//Added CR_101
	public static final String LS207_MDL_VIEW_NOTES = "LS207_MDL_VIEW_NOTES";
	public static final String LS404_ORDR_VIEW_NOTES="LS404_ORDR_VIEW_NOTES";
	
	public static final String LS207_IMG_CONTENT_TYPE = "LS207_IMG_CONTENT_TYPE";
	
	public static final String LS207_VIEW_IMG = "LS207_VIEW_IMG";
	
	public static final String LS110_MDL_VIEW = "LS110_MDL_VIEW";

	//Added CR_101
	public static final String LS207_MDL_VIEW_NAME = "LS207_MDL_VIEW_NAME";
	public static final String LS207_MDL_VIEW_SEQ_NO ="LS207_MDL_VIEW_SEQ_NO";
	
	public static final String LS170_IMG_CONTENT_TYPE = "LS170_IMG_CONTENT_TYPE";
	
	public static final String LS170_IMG = "LS170_IMG";
	
	public static final String LS170_IMG_SEQ_NO = "LS170_IMG_SEQ_NO";
		
	public static final String LS170_IMG_SEQ_Name = "SEQ_IMAGE";
	
	public static final String CURRENT_DATE = "LS170_UPDT_DATE";
	
	/** *************** used for Gen Arrangement******* */
	
	/** *************** used for Order GenArrangement******* */
	
	public static final String LS400_ORDR_KEY = "LS400_ORDR_KEY";
	
	public static final String LS400_GEN_ARGMNT_NOTES = "LS400_GEN_ARGMNT_NOTES";
	
	/** ***************used for Order GenArrangement******* */
	
	public static final String LS208_MDL_CURV_IMG_SEQ_NO = "LS208_MDL_CURV_IMG_SEQ_NO";
	
	public static final String LS409_ORDR_CURV_IMG_SEQ_NO = "SEQ_PERF_CURV";
	
	/** *************** used for MianFeature Information******* */
	public static final String LS403_COMP_INFO_SEQ_NO = "LS403_COMP_INFO_SEQ_NO";
	
	public static final String LS403_COMP_INFO_DESC = "LS403_COMP_INFO_DESC";
	
	/**
	 * ********************************Modify Spec Start************************************
	 */
	//Added for LSDB_CR_42 clause image
	public static final String LS400_APENDX_FLAG = "LS400_APENDX_FLAG";
	
	public static final String ORDR_KEY = "LS400_ORDR_KEY";
	
	public static final String ORDR_NO = "LS400_ORDR_NO";
	
	public static final String SPEC_STATUS_CODE = "LS080_SPEC_STATUS_CODE";
	
	public static final String SPEC_REV_CODE = "LS400_SPEC_REV_CODE";
	
	public static final String SPEC_SUBLVL_REV_CODE = "LS400_SPEC_SUBLVL_REV_CODE";
	
	public static final String STATUS = "STATUS";
	
	public static final String CUST_SEQ_NO = "LS050_CUST_SEQ_NO";
	
	public static final String CUST_NAME = "LS050_CUST_NAME";
	
	public static final String CUST_TYPE_SEQ_NO = "LS040_CUST_TYPE_SEQ_NO";
	
	public static final String CUST_TYPE = "LS040_CUST_TYPE";
	
	public static final String MDL_NAME = "LS200_MDL_NAME";
	
	public static final String ORDR_QTY = "LS400_ORDR_QTY";
	
	public static final String LS400_HP_DESC = "LS400_HP_DESC";
	
	public static final String CURSOR = "CURSOR";
	
	public static final String FILE_TYPE = "LS090_FILE_TYPE_CODE";
	
	public static final String FILE_LOC = "LS410_FILE_LOC";
	
	public static final String FILE_DESC = "LS090_FILE_TYPE_DESC";
	
	/**
	 * ********************************Modify Spec End**************************************
	 */
//	Added For CR_81 on 24-Dec-09 by sd41630
	public static final String LS300_CHAR_GRP_FLAG = "LS300_CHAR_GRP_FLAG";
//	Added For CR_81 on 24-Dec-09 by sd41630 Ends here
	
	public static final String LS300_CLA_SEQ_NO = "LS300_CLA_SEQ_NO";
	
	public static final String LS301_VERSION_NO = "LS301_VERSION_NO";
	
	public static final String LS301_CLA_DESC = "LS301_CLA_DESC";
	
	//Added for LSDB_CR-74
	
	public static final String LS406_USR_MARKER = "LS406_USR_MARKER";
	
	public static final String LS406_SYS_MARKER = "LS406_SYS_MARKER";
	
	public static final String LS406_CLA_DEL_FLAG = "LS406_CLA_DEL_FLAG";
	
	public static final String CLA_EXISTS_FLAG = "CLA_EXISTS_FLAG";
	
	public static final String SYS_MARKER_DESC = "SYS_MARKER_DESC";
	
	public static final String LS406_CLA_REASON ="LS406_CLA_REASON";
	
	//Ends
	
	public static final String LS050_CUST_NAME = "LS050_CUST_NAME";
	
	public static final String LS301_UPDT_USER_ID = "LS301_UPDT_USER_ID";
	
	public static final String LS301_UPDT_DATE = "LS301_UPDT_DATE";
	
	public static final String LS301_DEFAULT_FLAG = "LS301_DEFAULT_FLAG";
	
	public static final String LS302_EDL_NO = "LS302_EDL_NO";
	
	public static final String LS303_REF_EDL_NO = "LS303_REF_EDL_NO";
	
	public static final String LS304_SUBSEC_NO = "LS304_SUBSEC_NO";
	
	public static final String LS301_DWO_NUMBER = "LS301_DWO_NUMBER";
	
	public static final String LS301_PART_NUMBER = "LS301_PART_NUMBER";
	
	public static final String LS301_PRICE_BOOK_NUMBER = "LS301_PRICE_BOOK_NUMBER";
	
	public static final String LS060_STD_EQP_SEQ_NO = "LS060_STD_EQP_SEQ_NO";
	
	public static final String LS060_STD_EQP_DESC = "LS060_STD_EQP_DESC";
	
	public static final String LS306_TBL_DATA_COL_1 = "LS306_TBL_DATA_COL_1";
	
	public static final String LS306_TBL_DATA_COL_2 = "LS306_TBL_DATA_COL_2";
	
	public static final String LS306_TBL_DATA_COL_3 = "LS306_TBL_DATA_COL_3";
	
	public static final String LS306_TBL_DATA_COL_4 = "LS306_TBL_DATA_COL_4";
	
	public static final String LS306_TBL_DATA_COL_5 = "LS306_TBL_DATA_COL_5";
	
	public static final String LS306_HEADER_FLAG = "LS306_HEADER_FLAG";
	
	public static final String LS301_ENGG_DATA_COMMENTS = "LS301_ENGG_DATA_COMMENTS";
	
	public static final String LS308_CLA_NO = "LS308_CLA_NO";
	
	public static final String Italics = "Italics";
	
	public static final String Indicator = "Indicator";
	
	public static final String LS204_DEFAULT_FLAG = "LS204_DEFAULT_FLAG";
	
	public static final String LS301_CLA_REASON = "LS301_CLA_REASON";
	
	public static final String LS401_ORDR_SPEC_SEQ_NO = "LS401_ORDR_SPEC_SEQ_NO";
	
	public static final String LS401_SPEC_DESC = "LS401_SPEC_DESC";
	
	public static final String LS402_ORDR_ITEM_SEQ_NO = "LS402_ORDR_ITEM_SEQ_NO";
	
	public static final String LS402_ITEM_DESC = "LS402_ITEM_DESC";
	
	public static final String LS402_ITEM_VALUE = "LS402_ITEM_VALUE";
	
	public static final String LS150_DATA_LOC_TYPE_CODE = "LS150_DATA_LOC_TYPE_CODE";
	
	public static final String LS400_UPDT_DATE = "LS400_UPDT_DATE";
	
	/**
	 * ********************************ModelAddClause Starts**************************************
	 */
	public static final String STR_ARRAY = "STR_ARRAY";
	
	public static final String LS140_COMP_NAME = "LS140_COMP_NAME";
	
	public static final String LS140_COMP_SEQ_NO = "LS140_COMP_SEQ_NO";
	
	public static final String YES_FLAG = "Y";
	
	/**
	 * ********************************ModelAddClause  Ends**************************************
	 */
	public static final String LS400_SAP_CUST_CODE = "LS400_SAP_CUST_CODE";
	
	public static final String DATALOCATION_SNAP_SHOT = "S";
	
	public static final String LS409_ORDR_CURVE_IMG_SEQ_NO = "LS409_ORDR_CURV_IMG_SEQ_NO";
	
	public static final String LS090_FILE_TYPE_DESC = "LS090_FILE_TYPE_DESC";
	
	public static final String LS410_FILE_LOC = "LS410_FILE_LOC";
	
	public static final String LS400_ORDR_NO = "LS400_ORDR_NO";
	
	public static final String LS300_PART_OF_CLA_SEQ_NO = "LS300_PART_OF_CLA_SEQ_NO";
	
	/**
	 * ********************************Administration Starts **************************************
	 */
	public static final String LS010_EMAIL_ADDRESS = "LS010_EMAIL_ADDRESS";
	
	public static final String LS010_FIRSTNAME = "LS010_FIRSTNAME";
	
	public static final String LS010_LOC = "LS010_LOC";
	
	public static final String LS010_CONTACT_NUMBER = "LS010_CONTACT_NUMBER";
	
	public static final String LS010_LASTNAME = "LS010_LASTNAME";
	
	/**
	 * ********************************Administration Ends**************************************
	 */
	/** Spec Comparison****************************************************************/
	public static final String SECTION_NAME = "LS201_SEC_NAME";
	
	public static final String SPEC_STATUS = "SPECSTATUS";
	
	public static final String SUB_SEC_NAME = "SUBSECNAME";
	
	/**Spec comparison ends**********************************************************/
	
	/************* DB JOB *************************/
	
	public static final int PROC_STATUS_PENDING = 1;
	
	public static final int PROC_STATUS_INPRGRESS = 2;
	
	public static final int PROC_STATUS_COMPLETE = 3;
	
	public static final int PROC_STATUS_SYSERROR = 4;
	
	public static final int FILE_TYPE_ENG_SPEC = 1;
	
	public static final int FILE_TYPE_SALE_SPEC = 2;
	
	public static final int FILE_TYPE_SUPPLIMENT_SPEC = 3;
	
	public static final String ORDER_FILE_LOC_PATH = "/tmp/";
	
	/************************* 
	 * Added For LSDB_CR-06(Difference Component Comparison)
	 * Added on 15-April-08
	 * by ps57222 
	 * **********************************/
	
	public static final String COMP_OK1 = "COMP_OK1";
	
	public static final String COMP_OK2 = "COMP_OK2";
	
	public static final String COMP_DESC_OK1 = "COMP_DESC_OK1";
	
	public static final String COMP_DESC_OK2 = "COMP_DESC_OK2";
	
	//	Added for CR-26 Component Group/Component Report
	
	public static final String MODELS_USED = "MODELS_USED";
	
	public static final String DEF_MODELS = "DEF_MODELS";
	
	public static final String CHARACTERIZATION = "characterization";
	
	/************************* 
	 * Added For LSDB_CR-42(Appendix Image)
	 * Added on 05-may-08
	 * by ps57222 
	 * **********************************/
	
	public static final String LS415_IMG_NAME = "LS415_IMG_NAME";
	
	public static final String LS415_IMG_DESC = "LS415_IMG_DESC";
	
	public static final String MODIFY_SPEC = "ModifySpec";
	
	public static final String APPENDIX_IMAGE = "AppendixImage";
	
	/************************* 
	 * Added For LSDB_CR-42(Model Appendix Image)
	 * Added on 06-may-08
	 * by vv49326
	 * **********************************/
	public static final String LS209_IMG_NAME = "LS209_IMG_NAME";
	
	public static final String LS209_IMG_DESC = "LS209_IMG_DESC";
	
	/***
	 * Added For Populating drop down changes in Modify Spec Screen
	 * Added on 20-May-08
	 * by ps57222
	 * 
	 */
	
	public static final String LS081_REV_VIEW_SEQ_NO = "LS081_REV_VIEW_SEQ_NO";
	
	public static final String LS081_REV_VIEW_DESC = "LS081_REV_VIEW_DESC";
	
	//	Added for CR-49 
	public static final String LS190_CLA_SOURCE_CODE = "LS190_CLA_SOURCE_CODE";
	
	//Added for LSDB_CR_49 for delete Clause 
	public static final String LS301_DELETE_FLAG = "LS301_DELETE_FLAG";
	
	/************************* 
	 * Added For LSDB_CR-48(Part Of)
	 * Added on 01-Aug-08
	 * by ps57222
	 * **********************************/
	public static final String LS304_PART_OF_LEAD_CMP_GRP = "LS304_PART_OF_LEAD_CMP_GRP";
	
	public static final String LS304_PART_OF_CLA_SEQ_NO = "LS304_PART_OF_CLA_SEQ_NO";
	
	/************************* 
	 * Added For LSDB_CR-45(CR Form)
	 * Added on 20-Oct-08
	 * by ps57222
	 * **********************************/
	public static final String LS503_REQ_DESC = "LS503_REQ_DESC";
	
	public static final String LS502_STATUS_TYPE_SEQ_NO = "LS502_STATUS_TYPE_SEQ_NO";
	
	public static final String LS502_STATUS_TYPE_DESC = "LS502_STATUS_TYPE_DESC";
	
	public static final String LS503_REQ_REASON = "LS503_REQ_REASON";
	
	public static final String LS503_REQ_SUBMTD_DATE = "LS503_REQ_SUBMTD_DATE";
	
	public static final String MSTR_FN = "MSTR_FN";
	
	public static final String MSTR_LN = "MSTR_LN";
	
	public static final String REQ_FN = "REQ_FN";
	
	public static final String REQ_LN = "REQ_LN";
	
	public static final String LS503_MSTR_CHNGD_ID = "LS503_MSTR_CHNGD_ID";
	
	public static final String LS503_MSTR_CHNGD_DATE = "LS503_MSTR_CHNGD_DATE";
	
	public static final String LS503_REQ_ID = "LS503_REQ_ID";
	
	public static final String REQ_ID = "REQ_ID";
	
	public static final String COMP_GRP_CHANGE_TYPE = "COMP_GRP_CHANGE_TYPE";
	
	public static final String COMP_GRP_CHANGE_TYPE_DESC = "COMP_GRP_CHANGE_TYPE_DESC";
	
	public static final String LS130_COMP_GRP_SEQ_NO_OLD = "LS130_COMP_GRP_SEQ_NO_OLD";
			
	public static final String LS505_COMP_GRP_NAME_OLD = "LS505_COMP_GRP_NAME_OLD";
	
	public static final String LS505_CHARZ_FLAG_OLD = "LS505_CHARZ_FLAG_OLD";
	
	public static final String LS505_VALIDATION_FLAG_OLD = "LS505_VALIDATION_FLAG_OLD";
	
	public static final String LS505_COMP_GRP_NAME_NEW = "LS505_COMP_GRP_NAME_NEW";
	
	public static final String LS505_CHARZ_FLAG_NEW = "LS505_CHARZ_FLAG_NEW";
	
	public static final String LS505_VALIDATION_FLAG_NEW = "LS505_VALIDATION_FLAG_NEW";
	
	public static final String NAME_FLAG = "NAME_FLAG";
	
	public static final String VLDN_FLAG = "VLDN_FLAG";
	
	public static final String COMP_CHANGE_TYPE = "COMP_CHANGE_TYPE";
	
	public static final String COMP_CHANGE_TYPE_DESC = "COMP_CHANGE_TYPE_DESC";
	
	public static final String LS140_COMP_SEQ_NO_OLD = "LS140_COMP_SEQ_NO_OLD";
	
	public static final String LS506_COMP_NAME_OLD = "LS506_COMP_NAME_OLD";
	
	public static final String LS506_DEFAULT_FLAG_OLD = "LS506_DEFAULT_FLAG_OLD";
	
	public static final String LS506_DEFAULT_FLAG_NEW = "LS506_DEFAULT_FLAG_NEW";
	
	public static final String COMP_FLAG = "COMP_FLAG";
	
	public static final String DFULT_FLAG = "DFULT_FLAG";
	
	public static final String LS506_COMP_NAME_NEW = "LS506_COMP_NAME_NEW";
	
	public static final String CHRZ_FLAG = "CHRZ_FLAG";
	
	public static final String LS501_CHNG_TYPE_SEQ_NO = "LS501_CHNG_TYPE_SEQ_NO";
	
	public static final String CLA_CHANGE_TYPE_DESC = "CLA_CHANGE_TYPE_DESC";
	
	public static final String LS507_CHNG_FRM_CLA_NO = "LS507_CHNG_FRM_CLA_NO";
	
	public static final String LS507_CHNG_FRM_CLA_SEQ_NO = "LS507_CHNG_FRM_CLA_SEQ_NO";
	
	public static final String LS507_CHNG_FRM_VERSION_NO = "LS507_CHNG_FRM_VERSION_NO";
	
	public static final String LS507_CHANGE_TO_CLA_NO = "LS507_CHANGE_TO_CLA_NO";
	
	public static final String CLANO_FLAG = "CLANO_FLAG";
	
	public static final String LS507_CHNG_TO_VERSION_NO = "LS507_CHNG_TO_VERSION_NO";
	
	public static final String VERSN_FLAG = "VERSN_FLAG";
	
	public static final String LS507_PARNT_CLA_NO = "LS507_PARNT_CLA_NO";
	
	public static final String LS507_PARNT_CLA_DESC = "LS507_PARNT_CLA_DESC";
	
	public static final String LS507_CLA_DESC = "LS507_CLA_DESC";
	
	public static final String LS507_ENGG_DATA_COMMENTS = "LS507_ENGG_DATA_COMMENTS";
	
	public static final String LS507_DWO_NO = "LS507_DWO_NO";
	
	public static final String LS507_PART_NO = "LS507_PART_NO";
	
	public static final String LS507_PRICE_BOOK_NO = "LS507_PRICE_BOOK_NO";
	
	public static final String EDL_NO = "EDL_NO";
	
	public static final String REF_EDL_NO = "REF_EDL_NO";
	
	public static final String CLA_PART_OF = "CLA_PART_OF";
	
	public static final String CLA_TBL_DATA = "CLA_TBL_DATA";
	
	public static final String CLA_COMP = "CLA_COMP";
	
	public static final String LS508_EDL_NO = "LS508_EDL_NO";
	
	public static final String LS509_REF_EDL_NO = "LS509_REF_EDL_NO";
	
	public static final String LS510_PART_OF_CLA_SEQ_NO = "LS510_PART_OF_CLA_SEQ_NO";
	
	public static final String LS510_PART_OF_CLA_NO = "LS510_PART_OF_CLA_NO";
	
	public static final String LS510_CLA_DESC = "LS510_CLA_DESC";
	
	public static final String LS511_TBL_DATA_COL_1 = "LS511_TBL_DATA_COL_1";
	
	public static final String LS511_TBL_DATA_COL_2 = "LS511_TBL_DATA_COL_2";
	
	public static final String LS511_TBL_DATA_COL_3 = "LS511_TBL_DATA_COL_3";
	
	public static final String LS511_TBL_DATA_COL_4 = "LS511_TBL_DATA_COL_4";
	
	public static final String LS511_TBL_DATA_COL_5 = "LS511_TBL_DATA_COL_5";
	
	public static final String LS304_PART_OF_CLA_NUMBER = "LS304_PART_OF_CLA_NUMBER";
	
	public static final String LS507_DEFAULT_FLAG = "LS507_DEFAULT_FLAG";
	
	public static final String LS503_REQ_USER_ID = "LS503_REQ_USER_ID";
	
	public static final String LS503_REQ_ADMN_COMMENTS = "LS503_REQ_ADMN_COMMENTS";
	
	public static final String CLADESC_FLAG = "CLADESC_FLAG";
	
	/**
	 * Added For LSDB_CR-63 on 11-Dec-08 by ps57222
	 */
	public static final String LS409_ORDR_PDF_IMG_NAME = "LS409_ORDR_PDF_IMG_NAME";
	
	public static final String LS208_MDL_PDF_IMG_NAME = "LS208_MDL_PDF_IMG_NAME";
	
	/**
	 * Added for LSDB_CR_71 for deleted component in red color
	 */
	public static final String COMPONENT_DELETE_FLAG = "LS140_DELETE_FLAG";
	/**Added for CR-74 VV49326 VV49326 01-06-09**/
		
	public static final String HP_REVISION_MARKER="HP_REVISION_MARKER";
	
	public static final String ITEMS="ITEMS";
	
	/** Added For LSDB_CR-74[Revision Markers] on 01-June-09 by ps57222 **/
	
	public static final String REVISION_MARKER = "REVISION_MARKER";
	
	public static final String GN_REVISION_MARKER = "GN_REVISION_MARKER";
	
	//Change for LSDB_CR-76
	public static final String SS_ORDR_DETAILS = "SS_ORDR_DETAILS";
	
	
	//Added for CR-76 VV49326
	public static final String LS403_SYS_MARKER = "LS403_SYS_MARKER";
	
	public static final String LS405_SYS_MARKER = "LS405_SYS_MARKER";
	
	public static final String LS402_SYS_MARKER = "LS402_SYS_MARKER";
	
	public static final String HP_DESC_SYS_MARKER = "HP_DESC_SYS_MARKER";
	
	public static final String HP_SYS_MARKER_DESC = "HP_SYS_MARKER_DESC";
	
	public static final String IMG_SYS_MARKER = "IMG_SYS_MARKER";
	
	public static final String GEN_ARR_SYS_MARKER = "GEN_ARR_SYS_MARKER";

	public static final String IMG_SYS_MARKER_DESC = "IMG_SYS_MARKER_DESC";
	
	public static final String GEN_ARR_SYS_MARKER_DESC = "GEN_ARR_SYS_MARKER_DESC";
	
	//Added for CR-79 Adding PDF Header Image on 02-Nov-09 by RR68151
	
	public static final String LS400_PDF_HDR_IMG_FLAG = "LS400_PDF_HDR_IMG_FLAG";
	
	//Added for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151
	
	public static final String ENG_DATA_CLR_FLAG = "ENG_DATA_CLR_FLAG";
	
	public static final String DWO_NO_CLR_FLAG = "DWO_NO_CLR_FLAG";
	
	public static final String PART_NO_CLR_FLAG = "PART_NO_CLR_FLAG";
	
	public static final String PRICE_BK_NO_CLR_FLAG = "PRICE_BK_NO_CLR_FLAG";
	
	public static final String STD_EQP_CLR_FLAG = "STD_EQP_CLR_FLAG";
	
	public static final String EDL_CLR_FLAG = "EDL_CLR_FLAG";
	
	public static final String REF_EDL_CLR_FLAG = "REF_EDL_CLR_FLAG";
	
	public static final String PART_OF_CLR_FLAG = "PART_OF_CLR_FLAG";
	
	public static final String LS513_EFF_CLA_SEQ_NO = "LS513_EFF_CLA_SEQ_NO";
	
	public static final String COMP = "COMP";

	//Added For CR_81 on 24-Dec-09 by RR68151
	public static final String LS131_COMP_GRP_TYP_SEQ = "LS131_COMP_GRP_TYP_SEQ";
	
	public static final String LS131_COMP_GRP_TYP_DESC = "LS131_COMP_GRP_TYP_DESC";
	
	public static final String LS310_COMBNTN_SEQ_NO = "LS310_COMBNTN_SEQ_NO";
	
	public static final String LS311_CHARSTC_EDL_NO = "LS311_CHARSTC_EDL_NO";
	
	public static final String LS311_CHARSTC_REF_EDL_NO = "LS311_CHARSTC_REF_EDL_NO";
	
	public static final String COMP_COMBNTN = "COMP_COMBNTN";
	
	public static final String NEW_EDL = "NEW_EDL";
	
	public static final String NEW_REF_EDL = "NEW_REF_EDL";
	
	public static final String OLD_EDL = "OLD_EDL";
	
	public static final String OLD_REF_EDL = "OLD_REF_EDL";
	
	public static final String LS311_UPDT_USER_ID = "LS311_UPDT_USER_ID";
	
	public static final String LS311_UPDT_DATE = "LS311_UPDT_DATE";
	
	public static final String UPDT_USER_ID = "UPDT_USER_ID";
	
	public static final String UPDT_DATE = "UPDT_DATE";
	//Added for CR_84 for Specification Type Maintenance
	
	public static final String LS030_SPEC_TYPE_DESC = "LS030_SPEC_TYPE_DESC";
	
	public static final String LS020_SCREEN_NAME="LS020_SCREEN_NAME";
	
	public static final String SCREENMAP="SCREENMAP";
	public static final String IN_ARRAY = "IN_ARRAY";
	
//	Added for CR_86 for Dynamic clause num ON/OFF flag
	
	public static final String LS400_DYN_CLA_NUMBR_FLAG = "LS400_DYN_CLA_NUMBR_FLAG";
	//Added for CR_85 count of clause linked
	public static final String NO_OF_LINKS = "NO_OF_LINKS";
	
	//Added For CR_88 on 28_jun_10 by sd 41630 strat here
	public static final String  LS300_CLA_CODE ="LS300_CLA_CODE";
	public static final String  CHILD_FLAG ="CHILD_FLAG";
	//Ends here
//Added for CR_92
	public static final String LS406_CLA_NUM="LS406_CLA_NUM";
	public static final String CURR_REV_FLAG="CURR_REV_FLAG";
	public static final String SEC_CODE="SEC_CODE";
	public static final String LS130_COMP_GRP_SEQ_NO="LS130_COMP_GRP_SEQ_NO";
//ENDS here
	//Updated for link color for view clause histroy CR_92
	public static final String CURR_DEL_CLA_COUNT = "CURR_DEL_CLA_COUNT";
//Added for CR_93
	public static final String COMP_COLOR_FLAG = "COMP_COLOR_FLAG";
	
// Added for CR_97 for Change Control Type
	public static final String CHANGE_CONTROL_FLAG = "LS200_CHNGE_CTRL_TYPE";
	
	public static final String LABEL_FLAG="LABEL_FLAG";
// Ends Here	
	//CR_99 start here
	public static final String LS406_SALES_VERSION_FLAG="LS406_SALES_VERSION_FLAG";
	//CR_99 ends here
	
	//CR_100 Starts here for Suggestions Module
	public static final String LS601_SUGGESTION_ID = "LS601_SUGGESTION_ID";
	public static final String LS600_SUGG_STATUS_CODE = "LS600_SUGG_STATUS_CODE";
	public static final String LS600_SUGG_STATUS_DESC = "LS600_SUGG_STATUS_DESC";
	public static final String LS601_SCREEN_NAME = "LS601_SCREEN_NAME";
	public static final String LS601_SUGGESTION = "LS601_SUGGESTION";
	public static final String LS602_IMG_NAME = "LS602_IMG_NAME";
	public static final String LS601_SUGG_USER_ID = "LS601_SUGG_USER_ID";
	public static final String LS601_SUGG_DATE = "LS601_SUGG_DATE";
	public static final String LS601_SUGG_MSTR_COMMENT = "LS601_SUGG_MSTR_COMMENT";
	public static final String LS601_MSTR_UPDT_ID = "LS601_MSTR_UPDT_ID";
	public static final String LS601_MSTR_UPDT_DATE = "LS601_MSTR_UPDT_DATE";
	//CR_100 Ends here
	
	//CR_101 Starts here to hide Appendix Images in Sales Version
	public static final String SALES_SPEC_DISP_FLAG = "SALES_SPEC_DISP_FLAG";
	//CR_101 Ends here
	
	//Added for CR_101 for Component_Source_Codec (Include Order Level Components)
	public static final String COMP_SOURCE_CODE = "LS191_COMP_SOURCE_CODE";
	//CR_101 Ends here

	//Added for CR_101 - Revision Changes
	public static final String LS700_REVISION_ID = "LS700_REVISION_ID";
	public static final String LS700_REVISION_DESC = "LS700_REVISION_DESC";
	public static final String LS700_UPDT_DATE = "LS700_UPDT_DATE";
	public static final String REVISION_ITEM_DESC = "REVISION_ITEM_DESC";
	public static final String LS701_REVISION_ITEM_ID = "LS701_REVISION_ITEM_ID";
	public static final String LS701_REVISION_ITEM_DESC = "LS701_REVISION_ITEM_DESC";
	//CR_101 Ends here
	//Added for CR_101 QA testing comments
	public static final String LS700_REV_RELEASE_DATE = "LS700_REV_RELEASE_DATE";
	//Added for CR_104 Custom Model Name and General Information text
	public static final String LS400_GEN_INFO_TEXT = "LS400_GEN_INFO_TEXT";
	public static final String LS400_CUST_MDL_NAME = "LS400_CUST_MDL_NAME";
	//Added for CR_105 EDL Report Improvements
	public static final String CLA_DESC = "CLA_DESC";
	//Added for CR_104 Published User Name 
	public static final String PUBLISHED_USER  =  "USER_NAME";
	public static final String LS400_USER_MARKER_FLAG = "LS400_USER_MARKER_FLAG";
	//Added for CR_104 - Preserve GeneralInformation text Flag. 
	public static final String LS400_PRESRVE_GENINFO_FLAG = "LS400_PRESRVE_GENINFO_FLAG";
//	Added For CR_106
	public static final String LS050_CUST_IMG_SEQ_NO ="LS050_CUST_IMG_SEQ_NO";
	public static final String LS070_DISTRI_IMG_SEQ_NO ="LS070_DISTRI_IMG_SEQ_NO";
	public static final String LS400_DISTRI_LOGO_FLAG ="LS400_DISTRI_LOGO_FLAG";
	public static final String LS400_CUST_LOGO_FLAG ="LS400_CUST_LOGO_FLAG";
	public static final String LS200_GEN_INFO_TEXT_DRFT ="LS200_GEN_INFO_TEXT_DRFT";
	public static final String LS200_GEN_INFO_TEXT_OPN ="LS200_GEN_INFO_TEXT_OPN";
	
	 
		//Added for CR_106 - Customer Logo Updated Date.
	 public static final String LS050_IMG_UPDT_DATE="LS050_IMG_UPDT_DATE";
	 public static final String LS406_SALES_SYS_MARKER="LS406_SALES_SYS_MARKER";	 
	 
	 //	Added for CR_106 - Distributor Logo Updated Date.
	 public static final String LS070_IMG_UPDT_DATE="LS070_IMG_UPDT_DATE";
	 //Added for Distributor Logo Image Seq Number
	 public static final String LS070_DIST_IMG_SEQ_NO="LS070_DISTRI_IMG_SEQ_NO";
	 //	Added for CR_108 to include COmponents In Order Report
	 public static final String ORDERS_USED="ORDERS_USED";
	 //	Added for CR_109
	 public static final String LS010_PREV_LOGGEDIN = "LS010_PREV_LOGGEDIN"; 
	 public static final String LS406_USR_MARKED_REASON="LS406_USR_MARKED_REASON";
	 public static final String NEW_SUBSEC_FLAG="NEW_SUBSEC_FLAG";
	 public static final String APPENDIX_EXISTS_FLAG ="APPENDIX_EXISTS_FLAG";
	 //Added for CR-112
	 public static final String LS800_MSG_DESC="LS800_MSG_DESC";
	 public static final String LS120_ROLE_NAME="LS120_ROLE_NAME";
	 //Added for CR-113
	 public static final String INDICATOR_FLAG="INDICATOR_FLAG";
	 //Added for CR-110
	 public static final String LS900_1058_SEQ_NO="LS900_1058_SEQ_NO";
	 public static final String LS900_1058_ID="LS900_1058_ID";
	 public static final String LS900_NON_LSDB_REQ = "LS900_NON_LSDB_REQ";
	 public static final String LS902_1058_STATUS_SEQ_NO="LS902_1058_STATUS_SEQ_NO";
	 public static final String LS902_1058_STATUS_DESC="LS902_1058_STATUS_DESC";
	 public static final String LS900_SPEC_REVISION="LS900_SPEC_REVISION";
	 public static final String LS904_SECTION_NAME = "LS904_SECTION_NAME";
	 public static final String LS905_SECTION_STATUS_DESC = "LS905_SECTION_STATUS_DESC"; 
	 public static final String LS905_SECTION_STATUS_SEQ = "LS905_SECTION_STATUS_SEQ"; 
	 public static final String LS909_ACTION_DATE = "LS909_ACTION_DATE";
	 public static final String LS900_GEN_DESC="LS900_GEN_DESC";
	 public static final String LS900_ACTUAL_SELL_PRICE="LS900_ACTUAL_SELL_PRICE";
	 public static final String LS906_CATEGORY_NAME = "LS906_CATEGORY_NAME"; 
	 public static final String LS901_REQ_TYPE_DESC = "LS901_REQ_TYPE_DESC";
	 public static final String LS907_IMAGE_NAME = "LS907_IMAGE_NAME";
	 public static final String LS908_UNIT_NO = "LS908_UNIT_NO";
	 public static final String LS908_ROAD_NO = "LS908_ROAD_NO";
	 public static final String LS908_MCR_NO = "LS908_MCR_NO";
	 public static final String LS908_MCR_REQ = "LS908_MCR_REQ";
	 public static final String LS919_DATE_RECEIVED = "LS919_DATE_RECEIVED";
	 public static final String LS919_DATE_COMPLETED = "LS919_DATE_COMPLETED";
	 public static final String LS919_SYSTEM_ENG_COMMENT = "LS919_SYSTEM_ENG_COMMENT";
	 public static final String LS919_PART_NO_ADDED = "LS919_PART_NO_ADDED";
	 public static final String LS919_PART_NO_DELETED = "LS919_PART_NO_DELETED";
	 public static final String LS919_CHANGE_AFFECTS_WEIGHT = "LS919_CHANGE_AFFECTS_WEIGHT"; 
	 public static final String LS919_CHANGE_AFFECTS_CLEAR = "LS919_CHANGE_AFFECTS_CLEAR";
	 public static final String LS919_DESIGN_HRS = "LS919_DESIGN_HRS";
	 public static final String LS919_DRAFTING_HRS = "LS919_DRAFTING_HRS";
	 public static final String LS919_DRAWING_DUE_DATE = "LS919_DRAWING_DUE_DATE";
	 public static final String LS919_COMPLETION_DATE = "LS919_COMPLETION_DATE";
	 public static final String LS919_WORK_ORDER_USD = "LS919_WORK_ORDER_USD";
	 public static final String LS919_STATION_AFFECTED = "LS919_STATION_AFFECTED";
	 public static final String LS920_DATE_RECEIVED = "LS920_DATE_RECEIVED";
	 public static final String LS920_DATE_COMPLETED = "LS920_DATE_COMPLETED";
	 public static final String LS920_OPERATION_COMMENTS = "LS920_OPERATION_COMMENTS";
	 public static final String LS920_DISPOS_EXCESS_MATERIAL = "LS920_DISPOS_EXCESS_MATERIAL";
	 public static final String LS920_SUPPLIER_AFFECTED_COST = "LS920_SUPPLIER_AFFECTED_COST";
	 public static final String LS920_LABOR_IMPACT = "LS920_LABOR_IMPACT";
	 public static final String LS920_REC_EFFECTIVITY_DEL = "LS920_REC_EFFECTIVITY_DEL";
	 public static final String LS920_TOOLING_COST_USD = "LS920_TOOLING_COST_USD";
	 public static final String LS920_SCRAP_COST_USD= "LS920_SCRAP_COST_USD";
	 public static final String LS920_REWORK_COST = "LS920_REWORK_COST";
	 public static final String LS921_DATE_RECEIVED = "LS921_DATE_RECEIVED";
	 public static final String LS921_DATE_COMPLETED = "LS921_DATE_COMPLETED";
	 public static final String LS921_FINANCE_COMMENT = "LS921_FINANCE_COMMENT";
	 public static final String LS921_PROD_COST_CHANGE = "LS921_PROD_COST_CHANGE";
	 public static final String LS921_PROD_COST_CHANGE_SUP = "LS921_PROD_COST_CHANGE_SUP";
	 public static final String LS922_DATE_RECEIVED = "LS922_DATE_RECEIVED";
	 public static final String LS922_DATE_COMPLETED = "LS922_DATE_COMPLETED";
	 public static final String LS922_PROG_MANAGER_COMMENT = "LS922_PROG_MANAGER_COMMENT";
	 public static final String LS923_DATE_RECEIVED = "LS923_DATE_RECEIVED";
	 public static final String LS923_DATE_COMPLETED = "LS923_DATE_COMPLETED";
	 public static final String LS923_PROP_MANAGER_COMMENT = "LS923_PROP_MANAGER_COMMENT";
	 public static final String LS923_SELL_PRICE_CUSTOMER = "LS923_SELL_PRICE_CUSTOMER";
	 public static final String LS923_MARK_UP = "LS923_MARK_UP";
	 public static final String LS923_CUSTOMER_APPROVAL_REQ = "LS923_CUSTOMER_APPROVAL_REQ";
	 public static final String LS923_CUSTOMER_DECISION_DATE = "LS923_CUSTOMER_DECISION_DATE";
	 public static final String LS923_CUSTOMER_DECISION = "LS923_CUSTOMER_DECISION";
	 public static final String LS923_CUSTOMER_APPROVAL_DET = "LS923_CUSTOMER_APPROVAL_DET";
	 public static final String COUNT_OF_DAYS = "COUNT_OF_DAYS";
	 public static final String LS918_CHANGE_FROM_SPEC_SECT = "LS918_CHANGE_FROM_SPEC_SECT";
	 public static final String LS918_CHANGE_FROM_CLA_DESC = "LS918_CHANGE_FROM_CLA_DESC";
	 public static final String LS918_CHANGE_FROM_ENG_DATA = "LS918_CHANGE_FROM_ENG_DATA";
	 public static final String LS918_CHANGE_TO_SPEC_SECTION = "LS918_CHANGE_TO_SPEC_SECTION";
	 public static final String LS918_CHANGE_TO_CLA_DESC = "LS918_CHANGE_TO_CLA_DESC";
	 public static final String LS918_CHANGE_TO_ENG_DATA = "LS918_CHANGE_TO_ENG_DATA";
	 public static final String LS918_REASON = "LS918_REASON";
	 public static final String OWNER_FLAG="OWNER_FLAG";
	 public static final String LS906_CATEGORY_SEQ_NO ="LS906_CATEGORY_SEQ_NO";
	 public static final String LS901_REQ_TYPE_SEQ_NO="LS901_REQ_TYPE_SEQ_NO";
	 public static final String LS903_CHANGE_TYPE_SEQ_NO="LS903_CHANGE_TYPE_SEQ_NO";
	 public static final String LS903_CHANGE_TYPE_DESC="LS903_CHANGE_TYPE_DESC";
	 public static final String subcode="SUB_CODE";
	 public static final String SELECTED="SELECTED";
	 public static final String LS915_TBL_DATA_COL_1 = "LS915_TBL_DATA_COL_1";
	 public static final String LS915_TBL_DATA_COL_2 = "LS915_TBL_DATA_COL_2";
	 public static final String LS915_TBL_DATA_COL_3 = "LS915_TBL_DATA_COL_3";
	 public static final String LS915_TBL_DATA_COL_4 = "LS915_TBL_DATA_COL_4";
	 public static final String LS915_TBL_DATA_COL_5 = "LS915_TBL_DATA_COL_5";
	 public static final String LS910_CLA_CHANGE_REQ_SEQ_NO="LS910_CLA_CHANGE_REQ_SEQ_NO";
	 public static final String LS910_REASON="LS910_REASON";
	 public static final String LS910_CLA_UPDATED_TO_SPEC="LS910_CLA_UPDATED_TO_SPEC";
	 public static final String LS910_CHANGE_FROM_CLA_NUM="LS910_CHANGE_FROM_CLA_NUM";
	 public static final String LS910_CHANGE_FROM_CLA_SEQ_NO="LS910_CHANGE_FROM_CLA_SEQ_NO";
	 public static final String LS910_CHANGE_FROM_VER_NO="LS910_CHANGE_FROM_VER_NO";
	 public static final String LS910_CHANGE_TO_CLA_DESC="LS910_CHANGE_TO_CLA_DESC";
	 public static final String LS911_EDL_NO="LS911_EDL_NO";
	 public static final String LS912_REF_EDL_NO="LS912_REF_EDL_NO";
	 public static final String LS913_PART_OF_CLA_NO="LS913_PART_OF_CLA_NO";
	 public static final String LS910_DWO_NO="LS910_DWO_NO";
	 public static final String LS910_PART_NO="LS910_PART_NO";
	 public static final String LS910_ENG_DATA_COMMENTS="LS910_ENG_DATA_COMMENTS";
	 public static final String LS910_CHANGE_TO_CLA_NUM="LS910_CHANGE_TO_CLA_NUM";
	 public static final String LS910_CHANGE_TO_CLA_SEQ_NO="LS910_CHANGE_TO_CLA_SEQ_NO";
	 public static final String LS910_CHANGE_TO_VER_NO="LS910_CHANGE_TO_VER_NO";
	 public static final String LS913_PART_OF_CLA_SEQ_NO="LS913_PART_OF_CLA_SEQ_NO";
	 public static final String SUB_CODE="SUB_CODE";
	 public static final String LS910_LEAD_COMP_GRP_SEQ_NO="LS910_LEAD_COMP_GRP_SEQ_NO";
	 public static final String LS910_CHANGE_TO_PRNT_CLA="LS910_CHANGE_TO_PRNT_CLA";
	 public static final String LS910_PRICE_BOOK_NO="LS910_PRICE_BOOK_NO";
	 public static final String LS910_OLD_COMP_SEQ_NO="LS910_OLD_COMP_SEQ_NO";
	 public static final String LEAD_COMPONENT="LEAD_COMPONENT";
	 public static final String COMP_TIED="COMP_TIED";
	 //Added for CR-116
	 public static final String NON_LSDB_FLAG="NON_LSDB_FLAG";
	 public static final String LS900_LEGACY_1058_FLAG="LS900_LEGACY_1058_FLAG";
	 public static final String LS924_1058_FILE_LOC="LS924_1058_FILE_LOC";
	 //Added for CR-120
	 public static final String CREATED_ON="CREATED_ON";
	 //Added for CR-114
	 public static final String LS060_STD_EQP_COMMENTS = "LS060_STD_EQP_COMMENTS";
	 public static final String LS301_CREATED_BY = "LS301_CREATED_BY";
	 public static final String LS301_CREATED_DATE = "LS301_CREATED_DATE";
	 
	 //Added for CR_118
	 public static final String LS200_MDL_HIDDEN_FLAG="LS200_MDL_HIDDEN_FLAG";
	 public static final String LS204_COC_FLAG = "LS204_COC_FLAG";
	 public static final String GEN_TEXT_PROOF_READING = "GEN_TEXT_PROOF_READING";
	 //Added for CR_118 Ends
	 
	 //Added for CR-121
	 public static final String ITEMS_COUNT = "ITEMS_COUNT";
	 public static final String SUB_CLAUSE_EXISTS = "SUB_CLAUSE_EXISTS";

	 //Added for CR-124
	 public static final String NEWSTATUS = "NEWSTATUS";
	 public static final String LS180_EMAIL_SEQ_NO = "LS180_EMAIL_SEQ_NO";
	 public static final String LS180_EMAIL_FROM_X = "LS180_EMAIL_FROM_X";
	 public static final String LS180_EMAIL_TO_X = "LS180_EMAIL_TO_X";
	 public static final String LS180_EMAIL_SUBJECT_X = "LS180_EMAIL_SUBJECT_X";
	 public static final String LS180_EMAIL_BODY_X = "LS180_EMAIL_BODY_X";
	 public static final String LS180_EMAIL_TS ="LS180_EMAIL_TS";
	 public static final String LS180_UPDT_USER_ID = "LS180_UPDT_USER_ID";
	 public static final String LS180_UPDT_DATE ="LS180_UPDT_DATE";
	 
	 //Added for CR-126
	 public static final String LS900_LEGACY_1058_ID = "LS900_LEGACY_1058_ID";
	 public static final String LS011_ACTION_NOTICE = "LS011_ACTION_NOTICE";
	 public static final String LS011_INFO_NOTICE = "LS011_INFO_NOTICE";
	 public static final String LS011_CC_NOTICE = "LS011_CC_NOTICE";
	 public static final String LS012_EMAIL_PERIOD = "LS012_EMAIL_PERIOD";
	 public static final String LS900_UPDT_USER_ID = "LS900_UPDT_USER_ID";
	 public static final String ISSUEDBY = "ISSUEDBY";
	 public static final String SYSTEMENGINEERNAME = "SYSTEMENGINEERNAME";
	 public static final String LSDB_1058_ID = "LSDB_1058_ID";	
	 
	 //Added for CR-127
	 public static final String LS300_PARENT_CLA_SEQ_NO = "LS300_PARENT_CLA_SEQ_NO";
	 public static final String LS900_UPDT_DATE = "LS900_UPDT_DATE";
	 public static final String SYSTEMENGINEER = "SYSTEMENGINEER";
	 public static final String WAITINGONACTION = "WAITINGONACTION";
	 public static final String LASTTRANSACTION = "LASTTRANSACTION";	
	 
	 //Added for CR-128
	 public static final String LS093_EVNT_ACT_SEQ_NO = "LS093_EVNT_ACT_SEQ_NO";
	 public static final String LS091_ACTIVITY_TYPE = "LS091_ACTIVITY_TYPE";
	 public static final String LS092_EVENT_TYPE = "LS092_EVENT_TYPE";
	 public static final String LS093_EVENT_FROM = "LS093_EVENT_FROM";
	 public static final String LS093_EVENT_TO = "LS093_EVENT_TO";
	 public static final String ACTIONBY = "ACTIONBY";
	 public static final String ACTIONON = "ACTIONON";
	 public static final String LS093_MODIFIED_FOR = "LS093_MODIFIED_FOR"; //Added for CR-128
	 //Added for CR-130
	 public static final String SUBSEC_EXISTS_FLAG = "SUBSEC_EXISTS_FLAG";
	 public static final String LS925_REASON = "LS925_REASON";
	 public static final String LS925_SUBSEC_CHNG_REQ_SEQ_NO = "LS925_SUBSEC_CHNG_REQ_SEQ_NO";
	 public static final String LS925_SUBSEC_UPDATED_TO_SPEC = "LS925_SUBSEC_UPDATED_TO_SPEC";
	 
	 //Added for CR-134
	 public static final String LSDB_1058_NUMBER = "LS1058_NUMBER";
	 public static final String LS010_RESET_FLAG = "LS010_RESET_FLAG";
	 public static final String ACTION_USER = "ACTION_USER";
	 public static final String NEW_MDL_CLA_FLAG = "NEW_MDL_CLA_FLAG";
	 public static final String LS1058_STATUS = "LS1058_STATUS";
	 public static final String USED_VERSION_FLAG = "USED_VERSIONS";
	 public static final String LS301_USR_MARKER = "LS301_USR_MARKER";
	 public static final String LS301_USR_MARKED_REASON = "LS301_USR_MARKED_REASON";
	 
	 //Added for CR-135
	 public static final String SUBSECNAME = "SUBSECNAME";
	 
}