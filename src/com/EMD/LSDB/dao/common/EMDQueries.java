/*
 * Created on Jun 15, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.dao.common;

/**
 * 
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
/***********************************************************************
----------------------------------------------------------------------------------------------------------
*    Date     Version   Modified by    	 Comments                              		Remarks 
* 19/01/2010    1.0      RR68151       Added two new queries for Edl Indicator     Added for CR_81
* 											Screen.  
* 19/01/2010    1.1      SD 41630      	Added input characterisitic group flag     Added for CR_81												 	 
*                                       param
*19/01/2010    1.2      SD41630        Added Queries for View Characterisitic     Added for CR_81
*                                       Group Report By Model Report 
*05/05/2010    1.3     SD41630         Updated  Queries
*                                     (SP_SELECT_CHAR_RPT_SPEC_BY_MDL and insert_Clause)
*                                      for pass in param                                  Added for CR_85
* 02/07/2010    1.4     SD41630         Updated   SP_REARRANGE_MDL_CLAUSES Queries  
* 										for childflag                                     
* --------------------------------------------------------------------------------------------------------
**************************************************************************/

public interface EMDQueries {
	
	public static String query_findUser = "select LS010_USER_ID, LS010_PWD,LS120_ROLE_ID,LS010_LASTNAME,LS010_FIRSTNAME,LS010_RESET_FLAG from LSDB010_EMD_USERS where LS010_USER_ID=? and LS010_PWD=PK_LSDB_UTIL.Lsdb_Encrypt_Fn(?)";//Flag added for CR-134
	//Added For CR_104 
	public static String query_findServerName = "select PK_LSDB_UTIL.LSDB_DECRYPT_FN(b.LS010_PWD) from LSDB010_EMD_USERS b where b.XX=?";
	
	public static String spec_type = "SELECT  LS030_SPEC_TYPE_SEQ_NO,LS030_SPEC_TYPE  FROM LSDB030_SPEC_TYPE";
	
	public static String Query_Model = "select LS200_MDL_SEQ_NO,LS200_MDL_NAME from LSDB200_MODEL";
	
	public static String Query_fetchSections = "select * from LSDB201_MDL_SEC where LS200_MDL_SEQ_NO=?";
	
	public static String Query_test = "select * from LSDB010_EMD_USERS";
	
	public static String Query_ScreenID = "select LS020_SCREEN_ID from LSDB020_SCREENS where LS020_SCREEN_NAME=?";
	
	public static String Query_ScreenAccess = " select LS020_SCREEN_ID,LS120_ROLE_ID from LSDB021_SCN_LVL_ACCESS where LS120_ROLE_ID=? and LS020_SCREEN_ID=?";
	
	//Added for CR display Order Default Components in Main Features
	
	public static String Query_OrderDefaultComps = "select a.LS140_COMP_DESC from LSDB140_COMP a,LSDB405_ORDR_COMP b where a.LS140_COMP_SEQ_NO=b.LS140_COMP_SEQ_NO and   a.LS140_DISP_IN_SPEC='Y' and   b.LS150_DATA_LOC_TYPE_CODE='W' and   b.LS400_ORDR_KEY=?";
	//Modified for CR_118
	public static String SP_SELECT_MODEL = "{call PK_LSDB_MODEL.SP_SELECT_MODEL(?, ?, ?, ?, ?, ?, ?) }";
	//Added a parameter for CR_97 for Change Control Type
	public static String SP_INSERT_MODEL = "{call PK_LSDB_MODEL.SP_INSERT_MODEL(?, ?, ?, ?, ?, ?, ?, ? ,? ) }";
	//	Added a parameter for CR_97 for Change Control Type
	//Modified for cr_106 geninfotestDraf and open flag and CR_118
	public static String SP_UPDATE_MODEL = "{call PK_LSDB_MODEL.SP_UPDATE_MODEL(?, ?, ?, ?, ?, ?, ? ,? ,? ,?,?,?,? ) }";
	//	Added a parameter for CR_97 for Change Control Type
	public static String SP_COPY_MODEL = "{call PK_LSDB_MODEL.SP_COPY_MODEL(?, ?, ?, ?, ?, ?, ? ,? ,? ,? ) }";
	
	/** * Queries for Section Maintenance Screen *********************** */
	public static String SP_SELECT_MDL_SECTION = "{call PK_LSDB_SECTION.SP_SELECT_MDL_SECTION(?, ?, ?, ?, ?, ?)}";
	
	public static String SP_INSERT_MDL_SECTION = "{call PK_LSDB_SECTION.SP_INSERT_MDL_SECTION(?,?,?,?,?,?,?)}";
	
	public static String SP_UPDATE_MDL_SECTION = "{call PK_LSDB_SECTION.SP_UPDATE_MDL_SECTION(?,?,?,?,?,?,?,?)}";
	
	/** * Queries for Section Maintenance Screen *********************** */
	
	public static String SP_INSERT_MDL_SPEC = "{call PK_LSDB_SPEC_ITEM.SP_INSERT_MDL_SPEC(?,?,?,?,?,?,?)}";
	
	public static String SP_INSERT_MDL_SPEC_ITEM = "{call PK_LSDB_SPEC_ITEM.SP_INSERT_MDL_SPEC_ITEM(?,?,?,?,?,?,?)}";
	
	public static String SP_UPDATE_MDL_SPEC_ITEM = "{call PK_LSDB_SPEC_ITEM.SP_UPDATE_MDL_SPEC_ITEM(?,?,?,?,?,?,?)}";
	
	//Added for LSDB_CR-65 by ka57588
	public static String SP_DELETE_MDL_SPEC_ITEM = "{call PK_LSDB_SPEC_ITEM.SP_DELETE_MDL_SPEC_ITEM(?,?,?,?,?)}";
	
	public static String SP_SELECT_MDL_SPEC_ITEM = "{call PK_LSDB_SPEC_ITEM.SP_SELECT_MDL_SPEC_ITEM(?,?,?,?,?,?)}";
	
	public static String SP_SELECT_DISTRIBUTOR = "{call PK_LSDB_DISTRIBUTOR.SP_SELECT_DISTRIBUTOR(?,?,?,?,?)}";
	//Added Param for CR_106 JG101007
	public static String SP_INSERT_DISTRIBUTOR = "{call PK_LSDB_DISTRIBUTOR.SP_INSERT_DISTRIBUTOR(?,?,?,?,?,?,?)}";
	//Added Param for CR_106 JG101007
	public static String SP_UPDATE_DISTRIBUTOR = "{call PK_LSDB_DISTRIBUTOR.SP_UPDATE_DISTRIBUTOR(?,?,?,?,?,?,?,?)}";
	
	/**
	 * SP_SELECT_CUSTOMER,SP_INSERT_CUSTOMER query's are modified based on
	 * LSDB_CR-46 Modified on 27-Aug-08 by ps57222
	 */
	
	public static String SP_SELECT_CUSTOMER = "{call PK_LSDB_CUSTOMER.SP_SELECT_CUSTOMER(?, ?, ?, ?, ?, ?,?) }";
	//Added one param for CR_106 JG101007
	public static String SP_INSERT_CUSTOMER = "{call PK_LSDB_CUSTOMER.SP_INSERT_CUSTOMER(?, ?, ?, ?, ?, ?,?,?,?)}";
	//	Added one param for CR_106 JG101007
	public static String SP_UPDATE_CUSTOMER = "{call PK_LSDB_CUSTOMER.SP_UPDATE_CUSTOMER(?, ?, ?, ?, ?, ?, ?, ?,?)}";
	//Added for CR_97 added pram on 15 march 2011 by SD41630 
	public static String SP_SELECT_COMPGRP = "{call PK_LSDB_COMPGRP.SP_SELECT_COMPGRP(?,?,?,?,?,?,?,?)}";
	
	/* Modified for Valid Component Group Flag Change Request */
	//Added one pram For CR_97 on 15 march 2011 by SD41630  
	public static String SP_INSERT_COMPGRP = "{call PK_LSDB_COMPGRP.SP_INSERT_COMPGRP(?,?,?,?,?,?,?,?,?,?,?)}";
	
	public static String SP_UPDATE_COMPGRP = "{call PK_LSDB_COMPGRP.SP_UPDATE_COMPGRP(?,?,?,?,?,?,?,?,?,?,?)}";
	
	//Added for LSDB_CR_67 by ka57588
	public static String SP_MDL_DELETE_COMPGRP = "{call PK_LSDB_COMPGRP.SP_MDL_DELETE_COMPGRP(?,?,?,?,?)}";
	
	/*
	 * ***************Ends Here
	 * ****************************************************
	 */
	
	public static String SP_SELECT_COMPONENT = "{call PK_LSDB_COMPONENT.SP_SELECT_COMPONENT(?,?,?,?,?,?,?)}";
	
	public static String SP_SELECT_MDL_COMPONENT = "{call PK_LSDB_MDL_CLAUSE.SP_SELECT_MDL_COMPONENT(?,?,?,?,?,?,?,?,?)}";
	
	/** *** Modified For Attach clause CR ****** */
	
	public static String SP_INSERT_COMPONENT = "{call PK_LSDB_COMPONENT.SP_INSERT_COMPONENT(?,?,?,?,?,?,?,?,?)}";
	
	public static String SP_UPDATE_COMPONENT = "{call PK_LSDB_COMPONENT.SP_UPDATE_COMPONENT(?,?,?,?,?,?,?,?,?,?)}";
	
	public static String SP_Delete_COMPONENT = "{call  PK_LSDB_COMPONENT.SP_MDL_DELETE_COMP(?,?,?,?,?)}";
	
	public static String SP_SELECT_MDL_SUBSECTION = "{call PK_LSDB_SUBSECTION.SP_SELECT_MDL_SUBSECTION(?,?,?,?,?,?,?)}";
	
	public static String SP_UPDATE_MDL_SUBSECTION = "{call PK_LSDB_SUBSECTION.SP_UPDATE_MDL_SUBSECTION(?,?,?,?,?,?,?,?,?)}";
	
	public static String SP_INSERT_MDL_SUBSECTION = "{call PK_LSDB_SUBSECTION. SP_INSERT_MDL_SUBSECTION(?,?,?,?,?,?,?,?)}";
	
	public static String SP_CREATE_ORDER_SPECS = "{call PK_LSDB_ORDER_SPECS.SP_CREATE_ORDER_SPECS(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	public static String Query_updateGenArrNotes = "update LSDB200_MODEL set LS200_GEN_ARGMNT_NOTES=? where LS200_MDL_SEQ_NO=?";
	
	public static String SP_UPDATE_GENARRANGEIMAGES = "{call PK_LSDB_GENARRANGEMENT.SP_UPDATE_GENARRANGEIMAGES(?,?,?,?,?,?,?)}";
	
	public static String Query_DisplayImage = "select LS170_IMG,LS170_IMG_CONTENT_TYPE from LSDB170_IMAGES where LS170_IMG_SEQ_NO=?";
	
	public static final String GET_SYS_DATE = "SELECT "
		+ "TO_CHAR(sysdate,'mm/dd/yyyy hh:mi:ss AM') LS170_UPDT_DATE from dual";
	
	public static String SP_SELECT_GENARRANGEIMAGES = "{CALL PK_LSDB_GEN_ARRANGEMENT. SP_SELECT_MDL_GEN_ARRANGEMENT(?,?,?,?,?,?)}";
	
	public static String Query_testing = "update LSDB170_IMAGES set LS170_IMG=? where LS170_IMG_SEQ_NO=?";
	
	public static String Query_EmptyImage = "INSERT INTO LSDB170_IMAGES(LS170_IMG_SEQ_NO,LS170_IMG_CONTENT_TYPE,LS170_IMG,LS170_UPDT_USER_ID,LS170_UPDT_DATE)VALUES(?,?,empty_blob(),?,?)";
	
	public static String Query_UpdateImage = "UPDATE LSDB170_IMAGES SET  LS170_IMG=?"
		+ "WHERE LS170_IMG_SEQ_NO = ";
	String Query_UpdateImgSeqNo = "UPDATE LSDB207_MDL_GEN_ARGMNT SET LS170_IMG_SEQ_NO=?,LS207_UPDT_USER_ID=?,LS207_UPDT_DATE= ? WHERE LS200_MDL_SEQ_NO=	? AND LS110_MDL_VIEW_SEQ_NO=? ";
	//Added For CR_101 updated
	public static String SP_DELETE_MDL_GEN_ARGMNT_VIEW = "{call PK_LSDB_GEN_ARRANGEMENT.SP_DELETE_MDL_GEN_ARGMNT_VIEW(?,?,?,?,?,?)}";
	public static String SP_INSERT_MDL_GEN_ARGMNT_VIEW = "{call PK_LSDB_GEN_ARRANGEMENT.SP_INSERT_MDL_GEN_ARGMNT_VIEW(?,?,?,?,?,?,?,?)}";
	public static String SP_UPDATE_MDL_GEN_ARGMNT_VIEW = "{call PK_LSDB_GEN_ARRANGEMENT.SP_UPDATE_MDL_GEN_ARGMNT_VIEW(?,?,?,?,?,?,?,?,?)}";
	public static String SP_UPDATE_ORDR_GEN_ARGMNT_VIEW="{call PK_LSDB_GEN_ARRANGEMENT.SP_UPDATE_ORDR_GEN_ARGMNT_VIEW(?,?,?,?,?,?,?,?,?,?)}";
	public static String SP_DELETE_ORDR_GEN_ARGMNT_VIEW="{call PK_LSDB_GEN_ARRANGEMENT.SP_DELETE_ORDR_GEN_ARGMNT_VIEW(?,?,?,?,?,?,?)}";
	public static String SP_INSERT_ORDR_GEN_ARGMNT_VIEW="{call PK_LSDB_GEN_ARRANGEMENT.SP_INSERT_ORDR_GEN_ARGMNT_VIEW(?,?,?,?,?,?,?,?,?)}";

	/** ************* Queries Used For GenArrImages ********* */
	
	public static String SP_SELECT_MODEL_PERF_CURVE = "{call PK_LSDB_PERF_CURVE.SP_SELECT_MODEL_PERF_CURVE(?,?,?,?,?,?)}";
	
	public static String Query_InsertPerfImgSeqNo = "INSERT INTO LSDB208_MDL_PERF_CURV( LS208_MDL_CURV_IMG_SEQ_NO ,LS200_MDL_SEQ_NO ,LS208_UPDT_USER_ID ,LS208_UPDT_DATE ,LS170_IMG_SEQ_NO,LS208_MDL_PDF_IMG_NAME,LS208_ORDER_BY_CODE)VALUES(?,?,?,?,?,?,?)";
	
	public static String Query_DeletePerfImgSeqNo = "DELETE FROM LSDB208_MDL_PERF_CURV  WHERE LS200_MDL_SEQ_NO=	? AND LS208_MDL_CURV_IMG_SEQ_NO=?";
	
	/**
	 * ***********************************Component Mapping Maintenance -
	 * Start************************************
	 */
	
	/* Modified for Valid Flag Change Request Dated 20.11.07 ******** */
	public static String SP_SELECT_COMP_MAPPING = "{call PK_LSDB_COMP_MAPPING.SP_SELECT_COMP_MAPPING(?,?,?,?,?,?,?,?,?)}";
	
	/* Modified for Valid Flag Change Request Dated 20.11.07 ******** */
	
	public static String SP_DELETE_COMP_MAPPING = "{call PK_LSDB_COMP_MAPPING.SP_DELETE_COMP_MAPPING(?,?,?,?,?,?,?)}";
	
	//Updated for CR-127
	public static String SP_INSERT_COMP_MAPPING = "{call PK_LSDB_COMP_MAPPING.SP_INSERT_COMP_MAPPING(?,?,?,?,?,?,?,?,?,?,?)}";
	
	/**
	 * ***********************************Component Mapping Maintenance -
	 * End**************************************
	 */
	
	/** * Queries for MainFeatureInformation Screen *********************** */
	
	public static String SP_SELECT_COMPONENTINFO = "{call PK_LSDB_COMPONENTINFO.SP_SELECT_COMPONENTINFO(?,?,?,?,?,?,?,?)}";
	
	public static String SP_INSERT_COMPONENTINFO = "{call PK_LSDB_COMPONENTINFO.SP_INSERT_COMPONENTINFO(?,?,?,?,?,?,?,?)}";
	
	public static String SP_UPDATE_COMPONENTINFO = "{call PK_LSDB_COMPONENTINFO.SP_UPDATE_COMPONENTINFO(?,?,?,?,?,?,?,?)}";
	
	/** Added for LSDB_CR-62 ********/
	public static String SP_SELECT_MDL_COMP_INFO = "{call PK_LSDB_COMPONENTINFO.SP_SELECT_MDL_COMP_INFO(?,?,?,?,?,?,?,?,?)}";
	
	public static String SP_UPDATE_COMP_INFO_DISP = "{call PK_LSDB_COMPONENTINFO.SP_UPDATE_COMP_INFO_DISP(?,?,?,?,?,?,?,?)}";
	
	/** * Queries for MainFeatureInformation Screen *********************** */
	
	/** * Queries for MainFeatureInformation Screen *********************** */
	
	public static String SP_SELECT_ORDER_SECTION = "{call PK_LSDB_SECTION.SP_SELECT_ORDER_SECTION(?, ?, ?, ?, ?, ?,?)}";
	
	/** These Queries are for displaying Section Details ********** */
	public static String SP_SELECT_ORDER_SUBSECTION = "{call PK_LSDB_SUBSECTION.SP_SELECT_ORDER_SUBSECTION(?, ?, ?, ?, ?, ?, ? ,?)}";
	
	public static String SP_SELECT_ORDER_COMPONENT = "{call PK_LSDB_ORDER_SPECS.SP_SELECT_ORDER_COMPONENT(?, ?, ?, ?, ?, ?, ? ,?)}";
	
	public static String SP_CLA_NUM = "{call PK_LSDB_ORDER_SPECS.SP_GENERATE_ORDER_CLAUSE_NO(?, ?, ?, ?, ?, ?, ? ,?)}";
	
	public static String SP_CLA_DISP = "{call PK_LSDB_ORDER_SPECS.SP_DISP_ORDER_CLAUSE_DETAILS(?, ?, ?, ?, ?, ?, ? ,?, ?, ?, ?)}";
	
	public static String SP_SAVE_ORDER_COMP = "{call PK_LSDB_ORDER_SPECS.SP_SAVE_ORDER_COMP(?, ?, ?, ?, ?, ?, ?, ?)}";
	
	/** Ends ** */
	
	/** * Queries for MainFeatureInformation Screen *********************** */
	
	/** * Queries for Spec Maintenance Screen *********************** */
	public static String SP_SELECT_ORDER_SPEC_ITEM = "{call PK_LSDB_SPEC_ITEM.SP_SELECT_ORDER_SPEC_ITEM(?,?,?,?,?,?,?,?)}";
	
	public static String SP_UPDATE_ORDER_SPEC_ITEM = "{call PK_LSDB_SPEC_ITEM.SP_UPDATE_ORDER_SPEC_ITEM(?,?,?,?,?,?,?,?,?)}";
	
	public static String SP_INSERT_ORDER_SPEC = "{call PK_LSDB_SPEC_ITEM.SP_INSERT_ORDER_SPEC(?,?,?,?,?,?,?,?)}";
	
	public static String SP_INSERT_ORDER_SPEC_ITEM = "{call PK_LSDB_SPEC_ITEM.SP_INSERT_ORDER_SPEC_ITEM(?,?,?,?,?,?,?,?,?)}";
	
	//public static String SP_UPDATE_ORDR_DETL = "{call
	// PK_LSDB_ORDER_SPECIFICATIONS.SP_UPDATE_ORDR_DETL(?,?,?,?,?,?,?,?,?)}";
	
	/** * Queries for Spec Maintenance Screen *********************** */
	
	/**
	 * **************************************Modify Spec - Start
	 * ***************************************************
	 */
	public static String SP_SELECT_WORK_ORDER = "{call PK_LSDB_ORDER_SPECS.SP_SELECT_WORK_ORDER(?,?,?,?,?,?,?,?,?,?,?)}";
	
	/***************************************************************************
	 * One More in parameter(Appendix Flag) is added for SP_UPDATE_ORDER based
	 * on the requirement for LSDB_CR-42 Added on 08-05-08 by ps57222
	 */
	/***************************************************************************
	 * One More in parameter(PDF Header Image Flag) is added for SP_UPDATE_ORDER based
	 * on the requirement for LSDB_CR-79 Added on 29-10-09 by rr68151
	 */
	//Added four parameters for CR_104 General Info Text, Custom Model Name and User Marker Flag, Preserve General information Text Flag
//Added for CR_106
	public static String SP_UPDATE_ORDER = "{call PK_LSDB_ORDER_SPECS.SP_UPDATE_ORDER(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	/**
	 * **************************************Modify Spec - End
	 * ***************************************************
	 */
	
	/**
	 * **************************************Copy Spec - Start
	 * ***************************************************
	 */
	//Added a parameter in CR_108 for Show latest published specs flag - Order Specific Clause report
	public static String SP_SELECT_ORDER = "{call PK_LSDB_ORDER_SPECS.SP_SELECT_ORDER(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	//Added for parameter for copymdl indicator ON/OFF
	public static String SP_COPY_ORDER_SPECS = "{call PK_LSDB_ORDER_SPECS.SP_COPY_ORDER_SPECS(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	//Added for parameter for copymdl indicator ON/OFF 
	public static String SP_COPY_MDL_SPECS = "{call PK_LSDB_ORDER_SPECS.SP_COPY_MDL_SPECS(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	/**
	 * **************************************Copy Spec - End
	 * ***************************************************
	 */
	
	/**
	 * **********************Select Clause
	 * Revision**************************************************************
	 */
	public static String SP_SELECT_MDL_CLAUSE = "{call PK_LSDB_MDL_CLAUSE.SP_SELECT_MDL_CLAUSE(?,?,?,?,?,?,?,?,?,?)}";
	
	public static String SP_APPLY_MDL_DEF_CLAUSE = "{call PK_LSDB_MDL_CLAUSE.SP_APPLY_MDL_DEF_CLAUSE(?,?,?,?,?,?,?)}";
	
	public static String Query_StdEquipment = "select LS060_STD_EQP_SEQ_NO,LS060_STD_EQP_DESC,LS060_STD_EQP_COMMENTS from LSDB060_STD_EQP order by LS060_STD_EQP_DESC";
	
	/**
	 * **********************Select Clause
	 * Revision**************************************************************
	 */
	/** * Queries for ModelAddClause Screen *********************** */
	//Changed for CR-74 VV49326 12-06-09
//	 Added param For CR_81 on 24-Dec-09 by ------- -->
	//Modified for CR_85
	public static String insert_Clause = "{call PK_LSDB_MDL_CLAUSE.SP_INSERT_MDL_CLAUSE(?,?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ?, ?, ?, ?, ?, ?, ? , ?,?, ?, ?, ?, ?, ?, ?, ? , ? ,?,?,?,?,?,?,?,?) }";
//	 Added param For CR_81 on 24-Dec-09 by ------- - Ends here-->
	
	/***************************************************************************
	 * In insert_Clause query one more in parameter clauseSource is added based
	 * on LSDB_CR-35 * Added on 04-April-08 by ps57222 Modified for LSDB_CR-48
	 * Added on 30-July-08 by ps57222
	 **************************************************************************/
	
	public static String SP_FETCH_PARENT_CLAUSE = "{Call PK_LSDB_MDL_CLAUSE.SP_SELECT_MDL_CLAUSE(?,?,?,?,?,?,?,?,?,?)}";
	
	/** * Queries for ModelAddClause Screen *********************** */
	/** * Queries for OrderGenArrange Screen *********************** */
	
	/*** Modified for LSDB_CR-74[Revision Markers] on 01-June-09 by ps57222 ***/
	
	public static String SP_SELECT_ORDER_GENARRANGEIMAGES = "{CALL PK_LSDB_GEN_ARRANGEMENT.SP_SELECT_ORDER_GEN_ARRANGE(?,?,?,?,?,?,?,?)}";
	
	/**
	 * Query_ORDER_UpdateImgSeqNo is Modified to update the imageseqno in
	 * LSDB404_ORDR_GEN_ARGMNT for the working spec only.previously it updated
	 * the imageseqno for snap shot versions also. Modified on 06-Oct-08 by
	 * ps57222
	 */
	public static String Query_ORDER_UpdateImgSeqNo = "UPDATE LSDB404_ORDR_GEN_ARGMNT SET LS170_IMG_SEQ_NO=?,LS404_UPDT_USER_ID=?,LS404_UPDT_DATE= ? WHERE LS400_ORDR_KEY=? AND LS110_MDL_VIEW_SEQ_NO=? AND LS150_DATA_LOC_TYPE_CODE=?";
	
	public static String Query_ORDER_UPDATE_NOTES = "update LSDB400_ORDR set LS400_GEN_ARGMNT_NOTES=? where LS400_ORDR_KEY=? AND LS150_DATA_LOC_TYPE_CODE='W'";
	
	/** * Queries for OrderGenArrange Screen *********************** */
	
	/**
	 * **********Queries for Modeland Copy
	 * Indicator****************************************************************
	 */
	public static String SP_SELECT_ORDER_CLAUSE = "{call PK_LSDB_ORDER_CLAUSE.SP_SELECT_ORDER_CLAUSE(?,?,?,?,?,?,?)}";
	
	public static String SP_ACCREJ_ORDER_CLAUSE = "{call PK_LSDB_ORDER_CLAUSE.SP_ACCREJ_ORDER_CLAUSE(?,?,?,?,?,?,?,?,?,?)}";
	
	/**
	 * **********Queries for Model and Copy
	 * Indicator***************************************************************
	 */
	/**
	 * **************************************Queries for New
	 * Indicator*************************************************
	 */
	public static String SP_SELECT_ORDER_NEW_CLAUSE = "{call PK_LSDB_ORDER_CLAUSE.SP_SELECT_ORDER_NEW_CLAUSE(?,?,?,?,?,?,?)}";
	
	public static String SP_ACCREJ_ORDER_NEW_CLAUSE = "{call PK_LSDB_ORDER_CLAUSE.SP_ACCREJ_ORDER_NEW_CLAUSE(?,?,?,?,?,?,?,?)}";
	
	/**
	 * **************************************Queries for New
	 * Indicator*************************************************
	 */
	/**
	 * **************************************Queries for OrderPerf
	 * Curve*************************************************
	 */
	
	/** SP_SELECT_ORDRPERFCURVEIMG Query is Modified For LSDB_CR-74 on 01-June-09 by ps57222 **/
	
	public static String SP_SELECT_ORDRPERFCURVEIMG = "{call PK_LSDB_PERF_CURVE.SP_SELECT_ORDER_PERF_CURVE(?,?,?,?,?,?,?,?)}";
	
	public static String Query_Insert_Ordr_Perf_ImgSeqNo = "INSERT INTO LSDB409_ORDR_PERF_CURV( LS409_ORDR_CURV_IMG_SEQ_NO ,LS400_ORDR_KEY ,LS409_UPDT_USER_ID,LS409_UPDT_DATE,LS170_IMG_SEQ_NO,LS150_DATA_LOC_TYPE_CODE,LS409_ORDR_PDF_IMG_NAME,LS409_ORDER_BY_CODE )VALUES(?,?,?,?,?,?,?,?)";
	
	public static String Query_Delete_Ordr_Perf_ImgSeqNo = "DELETE FROM LSDB409_ORDR_PERF_CURV  WHERE LS400_ORDR_KEY=	? AND LS409_ORDR_CURV_IMG_SEQ_NO=? AND LS150_DATA_LOC_TYPE_CODE='W'";
	
	/**
	 * **************************************Queries for OrderPerf
	 * Curve*************************************************
	 */
	
	/**
	 * **************************************Queries for OrderSection
	 * Popup*************************************************
	 */
	public static String SP_UPDATE_ORDR_SEC_POPUP = "{call PK_LSDB_ORDER_SPECS.SP_UPDATE_ORDER_PART_OF(?,?,?,?,?,?,?,?,?,?,?)}";
	
	/**
	 * **************************************Queries for OrderSection
	 * Popup*************************************************
	 */
	
	/**
	 * ******************************************Add Clause At Order/Model
	 * Level***************************************
	 */
	public static String SP_SELECT_ORDER_SECTIONS = "{call PK_LSDB_ORD_SECTION.SP_SELECT_SECTIONS(?,?,?,?,?,?,?)}";
	//Query modified for CR_97 for getting 2 OUT parameters
	public static String SP_INSERT_ORDER_CLAUSE = "{call PK_LSDB_ORDER_CLAUSE.SP_INSERT_ORDER_CLAUSE(?,?, ?, ?, ?, ?, ?, ?, ? ,?, ?, ?, ?, ?, ?, ?, ? , ?,?, ?, ?, ?, ?, ?, ?, ? , ? ,?,?,?,?,?,?,?)}";
	
	/**
	 * ******************************************Add Clause At Order/Model
	 * Level***************************************
	 */
	//CR 87
	public static String SP_CLAUSE_COMPARISON = "{call PK_LSDB_ORDER_SPECS.SP_SEARCH_ORDER_SPEC_COMP(?, ?, ?, ?, ?, ?, ?, ?,?,?) }";
	
	public static String SP_COMPONENT_COMPARISON = "{call PK_LSDB_ORDER_SPECS.SP_COMPARE_ORDER_COMPONENT(?, ?, ?, ?, ?, ?, ?, ?) }";
	
	/**
	 * ******************* Spec Status Next Level
	 * ******************************************
	 */
	public static String spec_status = "SELECT LS080_STATUS_DESC STATUS,LS080_SPEC_STATUS_CODE FROM LSDB080_SPEC_STATUS  WHERE LS080_SPEC_STATUS_CODE =?+1 UNION SELECT LS080_STATUS_DESC,LS080_SPEC_STATUS_CODE FROM LSDB080_SPEC_STATUS  WHERE ?=4 AND LS080_SPEC_STATUS_CODE =? ORDER BY LS080_SPEC_STATUS_CODE";
	
	public static String SP_PREPUBLISH_ORDER = "{call PK_LSDB_ORDER_SPECS.SP_PREPUBLISH_ORDER(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }";
	
	public static String SP_PUBLISH_ORDER = "{call PK_LSDB_ORDER_SPECS.SP_PUBLISH_ORDER(?, ?, ?, ?, ?, ?) }";
	
	/** Queries for MasterMaintenence Report * */
	public static String SP_SELECT_MDL_SPEC = "{call PK_LSDB_MDL_CLAUSE.SP_SELECT_MDL_SPEC(?,?,?,?,?,?,?)}";
	
	/** Queries for Master Spec By Model Report * */
	//Query Modified for CR_81 Locomotive and Power Products Enhancements by RR68151
	public static String SP_SELECT_MSTR_SPEC_BY_MDL = "{call PK_LSDB_MDL_CLAUSE.SP_SELECT_MSTR_SPEC_BY_MDL(?,?,?,?,?,?,?,?,?,?)}";
	
	//Added for LSDB_CR-77
	public static String SP_CUST_OPTN_CATLOG = "{call PK_LSDB_COMPGRP.SP_CUST_OPTN_CATLOG(?,?,?,?,?,?)}";
	/* added parem for CR_91 */
	/** Queries for History EDL POPUP * Changed by cm68219*/
	public static String SP_SEL_EDL_NO = "{call PK_LSDB_ORDER_SPECS.SP_SELECT_ORDER_EDL_NO(?,?,?, ?, ?, ?,?,?) }";
	
	/** Queries for Change Password * */
	public static String SP_UPDATE_PWD = "{call PK_LSDB_ADMN.SP_UPDATE_PWD(?, ?, ?, ?, ?,?,?) }";
	
	/**
	 * ******************************************Delete Clause At Order
	 * Level**************************************************
	 */
	//CR_68
	public static String SP_DELETE_ORDER_CLAUSE = "{call PK_LSDB_ORDER_CLAUSE.SP_DELETE_ORDER_CLAUSE(?, ?, ?, ?, ?, ?, ?, ?, ?,?) }";
	
	//Added for LSDB_CR-74 by ka57588
	//Extra parameter added in SP_MARK_ORDR_CLAUSE package for CR-109 
	public static String SP_MARK_ORDR_CLAUSE = "{call PK_LSDB_ORDER_CLAUSE.SP_MARK_ORDR_CLAUSE(?, ?, ?, ?, ?, ? ,?, ?) }";
	
	public static String SP_SELECT_ORDR_CLA_VER = "{call PK_LSDB_ORDER_CLAUSE.SP_SELECT_ORDR_CLA_VER(?, ?, ?, ?, ?, ?, ?, ?) }";
	
	public static String SP_UPDATE_CLAUSE_REASON ="{call PK_LSDB_ORDER_CLAUSE.SP_UPDATE_CLAUSE_REASON(?, ?, ?, ?, ?, ?, ?, ?) }";
	/**
	 * ******************************************Delete Clause At Order
	 * Level**************************************************
	 */
	
	/** ***************Generate PDF******************** */
	public static String SP_SUBMIT_PDFLOAD_JOB = "{call PK_LSDB_DBJOB.SP_SUBMIT_PDFLOAD_JOB(?, ?, ?, ?, ?, ?) }";
	
	public static String Query_Order_Spec_File = "INSERT INTO LSDB410_ORDR_SPEC_FILES( LS400_ORDR_KEY , LS090_FILE_TYPE_CODE ,LS410_FILE_LOC ,LS100_PRCS_STATUS_CODE , LS410_UPDT_USER_ID ,LS410_UPDT_DATE,LS150_DATA_LOC_TYPE_CODE )VALUES(?, ?, ?, ?, ?, ?, ?)";
	
	public static String SP_INSERT_ORDR_SPEC_FILES = "{call PK_LSDB_ORDER_SPECS.SP_INSERT_ORDR_SPEC_FILES(?, ?, ?, ?, ?, ?, ?, ?, ?) }";
	
	public static String Query_Fetch_Order_Sections = "SELECT CHR(B.LS201_SEC_CODE+64)||'.'||C.LS202_SUBSEC_CODE SUB_CODE,C.LS202_SUBSEC_NAME FROM LSDB412_ORDR_SUBSEC A,LSDB201_MDL_SEC B,LSDB202_MDL_SUBSEC C WHERE A.LS202_SUBSEC_SEQ_NO=? AND A.LS400_ORDR_KEY=?  AND A.LS150_DATA_LOC_TYPE_CODE=?  AND A.LS201_SEC_SEQ_NO=B.LS201_SEC_SEQ_NO  AND A.LS202_SUBSEC_SEQ_NO=C.LS202_SUBSEC_SEQ_NO";
	
	/** **************Delete Spec****** */
	public static String SP_DELETE_ORDER = "{call Pk_Lsdb_Order_Specs.SP_ORDER_DELETE_SPEC(?,?,?,?,?)}";
	
	/***************************************************************************
	 * deleteMainFeatures is added For LSDB_CR-32 Added on 07-April-08 by
	 * ps57222
	 *  
	 */
	public static String SP_DELETE_COMPONENTINFO = "{call PK_LSDB_COMPONENTINFO.SP_DELETE_COMPONENTINFO(?,?,?,?,?,?,?)}";
	
	/** **************Added for CR-33********************** */
	public static String SP_DELETE_ORDER_SPEC_ITEM = "{call PK_LSDB_SPEC_ITEM.SP_DELETE_ORDER_SPEC_ITEM(?,?,?,?,?,?,?)}";
	
	//Added for LSDB_CR-64 by ka57588
	//Starts here
	public static String SP_MDL_DELETE_SPEC = "{call PK_LSDB_SPEC_ITEM.SP_MDL_DELETE_SPEC(?,?,?,?,?,?)}";
	
	public static String SP_MDL_UPDATE_SPEC = "{call PK_LSDB_SPEC_ITEM.SP_MDL_UPDATE_SPEC(?,?,?,?,?,?,?)}";
	
	public static String SP_ORDR_DELETE_SPEC = "{call PK_LSDB_SPEC_ITEM.SP_ORDR_DELETE_SPEC(?,?,?,?,?,?,?)}";
	
	public static String SP_ORDR_UPDATE_SPEC = "{call PK_LSDB_SPEC_ITEM.SP_ORDR_UPDATE_SPEC(?,?,?,?,?,?,?,?)}";
	
	//Ends here
	
	/***************************************************************************
	 * SP_INSERT_ENGGDATA,SP_UPDATE_ENGGDATA,SP_DELETE_ENGGDATA is added For
	 * LSDB_CR-38 ** Added on 11-April-08 by ps57222
	 *  
	 **************************************************************************/
	
	public static String SP_INSERT_ENGGDATA = "{call PK_LSDB_ENGGDATA.SP_INSERT_ENGGDATA(?,?,?,?,?,?)}";
	
	public static String SP_UPDATE_ENGGDATA = "{call PK_LSDB_ENGGDATA.SP_UPDATE_ENGGDATA(?,?,?,?,?,?,?)}";
	
	public static String SP_DELETE_ENGGDATA = "{call PK_LSDB_ENGGDATA.SP_DELETE_ENGGDATA(?,?,?,?,?)}";
	
	/***************************************************************************
	 * SP_COMPARE_ORDR_DIFF_COMP is added For LSDB_CR-06 ** Added on 15-April-08
	 * by ps57222
	 *  
	 */
	public static String SP_COMPARE_ORDR_DIFF_COMP = "{call PK_LSDB_ORDER_SPECS.SP_COMPARE_ORDR_DIFF_COMP(?,?,?,?,?,?,?,?)}";
	
	/***************************************************************************
	 * SP_DELETE_TEMP_TABLE is added For LSDB_CR-34 ** Added on 21-April-08 by
	 * ps57222
	 *  
	 */
	public static String SP_DELETE_TEMP_TABLE = "{call PK_LSDB_ORDER_SPECS.SP_DELETE_TEMP_TABLE(?,?,?,?,?,?)}";
	
	//	Added for CR-4 Delete Section and Sub-Section
	
	/** **************Delete Section CR*************** */
	public static String SP_DELETE_MDL_SECTION = "{call PK_LSDB_SECTION.SP_DELETE_MDL_SECTION(?,?,?,?,?,?)}";
	
	/** *************Delete Sub Section CR************ */
	public static String SP_DELETE_MDL_SUBSECTION = "{call PK_LSDB_SUBSECTION.SP_DELETE_MDL_SUBSECTION(?,?,?,?,?,?,?)}";
	
	//	Added for CR-26 Component Group/Component Report
	//Added For CR_97 on 15 march 2011 by SD41630 
	//Added a parameter for CR_101 to include the order level component flag
	//Added For CR_104 added  modelseqno pram
	//Added For CR_108 Added two Parameters for Component in Orders report
	//Updated for CR-121
	public static String SP_SELECT_COMP_COMPGRP_REPORT = "{call PK_LSDB_COMPONENT.SP_SELECT_COMP_COMPGRP_REPORT(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	//CR_101 Ends here
	/***************************************************************************
	 * SP_SELECT_ORDR_APNDX_IMG is added For LSDB_CR-42 ** Added on 05-May-08 by
	 * ps57222
	 *  
	 */
	
	public static String SP_SELECT_ORDR_APNDX_IMG = "{call PK_LSDB_ORDER_APPENDIX_IMG.SP_SELECT_ORDR_APNDX_IMG(?,?,?,?,?,?,?,?)}";
	
	public static String SP_INSERT_ORDR_APNDX_IMG = "{call PK_LSDB_ORDER_APPENDIX_IMG.SP_INSERT_ORDR_APNDX_IMG(?,?,?,?,?,?,?,?,?)}";
	
	public static String SP_UPDATE_ORDR_APNDX_IMG = "{call PK_LSDB_ORDER_APPENDIX_IMG.SP_UPDATE_ORDR_APNDX_IMG(?,?,?,?,?,?,?,?,?)}";
	
	public static String SP_DELETE_ORDR_APNDX_IMG = "{call PK_LSDB_ORDER_APPENDIX_IMG.SP_DELETE_ORDR_APNDX_IMG(?,?,?,?,?,?,?)}";
	
	public static String SP_CLAUSE_APNDX_IMG = "{call PK_LSDB_ORDER_APPENDIX_IMG.SP_CLAUSE_APNDX_IMG(?,?,?,?,?,?,?,?,?)}";
	
	//Added for CR-42 Model Appendix Image Maintenance
	
	public static String SP_SELECT_MDL_APNDX_IMG = "{call PK_LSDB_APNDX_IMG.SP_SELECT_MDL_APNDX_IMG(?,?,?,?,?,?)}";
	
	public static String SP_DELETE_MDL_APNDX_IMG = "{call PK_LSDB_APNDX_IMG.SP_DELETE_MDL_APNDX_IMG(?,?,?,?,?,?)}";
	
	public static String SP_UPDATE_MDL_APNDX_IMG = "{call PK_LSDB_APNDX_IMG.SP_UPDATE_MDL_APNDX_IMG(?,?,?,?,?,?,?,?)}";
	
	public static String Query_InsertApndxImage = "INSERT INTO LSDB209_MDL_APNDX_IMG(LS200_MDL_SEQ_NO,LS170_IMG_SEQ_NO,LS209_IMG_NAME,LS209_IMG_DESC,LS209_UPDT_USER_ID,LS209_UPDT_DATE)VALUES(?,?,?,?,?,?)";
	
	public static String SP_INSERT_MDL_APNDX_IMG = "{call PK_LSDB_APNDX_IMG.SP_INSERT_MDL_APNDX_IMG(?,?,?,?,?,?,?,?)}";
	
	/***************************************************************************
	 * QUERRY_SELECT_REVISION is used to load the dropdown values from
	 * database,which is used in Modify Spec main screen. Added on 20-May-08 by
	 * ps57222
	 *  
	 */
	
	public static String QUERRY_SELECT_REVISION = "SELECT LS081_REV_VIEW_SEQ_NO,LS081_REV_VIEW_DESC FROM LSDB081_REV_VIEW_OPTION ORDER BY LS081_REV_VIEW_SEQ_NO";
	
	//Added for CR-51 User Role alert masg for Add Clause Order/Model
	
	public static String QUERRY_FETCH_ORDR_SPEC_TYPE = "SELECT LSDB200_MODEL.LS030_SPEC_TYPE_SEQ_NO FROM LSDB200_MODEL, LSDB400_ORDR  WHERE LSDB200_MODEL.LS200_MDL_SEQ_NO = LSDB400_ORDR.LS200_MDL_SEQ_NO AND  LSDB400_ORDR.LS400_ORDR_KEY = ? AND  LSDB400_ORDR.LS150_DATA_LOC_TYPE_CODE = 'W'";
	
	/***************************************************************************
	 * SP_DELETE_MDL_CLAUSE is used to delete the clause and it's subclauses
	 * 
	 * Added on 15-July-08 by ps57222
	 *  
	 */
	
	public static String SP_DELETE_MDL_CLAUSE = "{call PK_LSDB_MDL_CLAUSE.SP_DELETE_MDL_CLAUSE(?,?,?,?,?)}";
	
	/***************************************************************************
	 * SP_DELETE_MDL_CLAUSE_VER is used to delete the selected version of a
	 * clause and it's subclauses.
	 * 
	 * Added on 15-July-08 by ps57222
	 *  
	 */
	
	public static String SP_DELETE_MDL_CLAUSE_VER = "{call PK_LSDB_MDL_CLAUSE.SP_DELETE_MDL_CLAUSE_VER(?,?,?,?,?,?)}";
	
	/***************************************************************************
	 * SP_UPDATE_MDL_CLAUSE is used to update the selected version of a clause.
	 * 
	 * Added on 15-July-08 by si50968
	 *  
	 */
	public static String SP_UPDATE_MDL_CLAUSE = "{call PK_LSDB_MDL_CLAUSE.SP_UPDATE_MDL_CLAUSE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	//Added for CR-49 Accept Reject Delete Clause
	
	public static String SP_FETCH_MDL_DELETED_CLAUSE = "{call PK_LSDB_ORDER_CLAUSE.SP_FETCH_MDL_DELETED_CLAUSE(?,?,?,?,?,?,?)}";
	
	public static String SP_ACCRJT_DELETE_CLAUSE = "{call PK_LSDB_ORDER_CLAUSE.SP_ACCRJT_DELETE_CLAUSE(?,?,?,?,?,?,?,?)}";
	
	//Added for CR-55 Delete Distributor on date 19/09/08
	public static String SP_DELETE_DISTRIBUTOR = "{call PK_LSDB_DISTRIBUTOR.SP_DELETE_DISTRIBUTOR(?,?,?,?,?)}";
	
	/** * Queries For LSDB_CR-45(CR Form) ******** */
	/***************************************************************************
	 * Added For LSDB_CR-45(CR Form) Added on 20-Oct-08 by ps57222
	 **************************************************************************/
	
	public static String SP_CREATE_REQUEST = "{call PK_LSDB_CHNGE_CTRL.SP_CREATE_REQUEST(?,?,?,?,?,?)}";
	
	public static String SP_SEARCH_REQUEST = "{call PK_LSDB_CHNGE_CTRL.SP_SEARCH_REQUEST(?,?,?,?,?,?,?,?,?,?)}";
	
	public static String SP_SELECT_MODEL_REQ_DTLS = "{call PK_LSDB_CHNGE_CTRL.SP_SELECT_MODEL_REQ_DTLS(?,?,?,?,?,?)}";
	
	public static String SP_SELECT_COMPGRP_REQ_DTLS = "{call PK_LSDB_CHNGE_CTRL.SP_SELECT_COMPGRP_REQ_DTLS(?,?,?,?,?,?,?)}";
	
	public static String SP_SELECT_CLA_REQ_DTLS = "{call PK_LSDB_CHNGE_CTRL.SP_SELECT_CLA_REQ_DTLS(?,?,?,?,?,?,?)}";
	
	public static String SP_INSERT_CLA_REQ_DTLS = "{call PK_LSDB_CHNGE_CTRL.SP_INSERT_CLA_REQ_DTLS(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	public static String SP_INSERT_COMPGRP_COMP_DTLS = "{call PK_LSDB_CHNGE_CTRL.SP_INSERT_COMPGRP_COMP_DTLS(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	public static String SP_RESET_REQUEST = "{call PK_LSDB_CHNGE_CTRL.SP_RESET_REQUEST(?,?,?,?,?)}";
	
	public static String SP_UPDATE_STATUS_REQ_DTLS = "{call PK_LSDB_CHNGE_CTRL.SP_UPDATE_STATUS_REQ_DTLS(?,?,?,?,?,?,?)}";
	
	public static String REQ_STATUS = "SELECT a.LS502_STATUS_TYPE_SEQ_NO,a.LS502_STATUS_TYPE_DESC FROM LSDB502_STATUS a	ORDER BY a.LS502_STATUS_TYPE_SEQ_NO";
	
	/** Added for LSDB_CR-61 on 03-Dec-08 * */
	//Changed For CR_80
	public static String SP_SELECT_MDL_CLA_LEADCOMP = "{call PK_LSDB_MDL_CLAUSE.SP_SELECT_MDL_CLA_LEADCOMP(?,?,?,?,?,?,?,?,?,?,?)}";
	
	/** Added for LSDB_CR-63 on 10-Dec-08 by ps57222* */
	public static String SP_UPDATE_MDL_PERF_CURV = "{call PK_LSDB_PERF_CURVE.SP_UPDATE_MDL_PERF_CURV(?,?,?,?,?,?,?)}";
	
	public static String SP_UPDATE_ORDR_PERF_CURV = "{call PK_LSDB_PERF_CURVE.SP_UPDATE_ORDR_PERF_CURV(?,?,?,?,?,?,?,?)}";
	
	/** Added for CR-67 Unmap Component Grp**/
	
	public static String SP_UNMAP_COMP_GRP = "{call PK_LSDB_COMP_MAPPING.SP_UNMAP_COMP_GRP(?,?,?,?,?,?,?)}";
	//Change for CR-80//updated For CR_93
	public static String SP_SELECT_MDL_CLA_COMP_MAPP = "{call PK_LSDB_MDL_CLAUSE.SP_SELECT_MDL_CLA_COMP_MAPP(?,?,?,?,?,?,?,?,?)}";
	
	/**Added for CR-68 Order New Component**/
	public static String SP_VALIDATE_ORDR_COMP_NAME = "{call PK_LSDB_COMPONENT.SP_VALIDATE_ORDR_COMP_NAME(?,?,?,?,?,?,?,?,?)}";
	
	//Change for LSDB_CR-76 
	public static String SP_RESET_SPEC_STATUS = "{call PK_LSDB_ORDER_SPECS.SP_RESET_SPEC_STATUS(?,?,?,?,?,?)}";

	//Added for CR_80 CR Form Enhancements III on 25-Nov-09 by RR68151
	public static String SP_SELECT_EFF_CLAUSES = "{call PK_LSDB_CHNGE_CTRL.SP_SELECT_EFF_CLAUSES(?,?,?,?,?,?)}";
	
	public static String SP_REASSIGN_CR_FORM = "{call PK_LSDB_CHNGE_CTRL.SP_REASSIGN_CR_FORM(?,?,?,?,?,?,?)}";
	
	//Added For CR_81 on 24-Dec-09 by RR68151
	public static String Query_Comp_Group_Type = "SELECT LS131_COMP_GRP_TYP_SEQ, LS131_COMP_GRP_TYP_DESC FROM LSDB131_COMP_GRP_TYPE ORDER BY LS131_COMP_GRP_TYP_SEQ";

	public static String SP_SELECT_EDL_MAPP = "{call PK_LSDB_CLA_EDL_MAPP.SP_SELECT_EDL_MAPP(?,?,?,?,?,?)}";
	
	public static String SP_INSERT_EDL_MAPP = "{call PK_LSDB_CLA_EDL_MAPP.SP_INSERT_EDL_MAPP(?,?,?,?,?,?,?,?,?)}";
	
	public static String SP_UPDATE_EDL_MAPP = "{call PK_LSDB_CLA_EDL_MAPP.SP_UPDATE_EDL_MAPP(?,?,?,?,?,?,?,?,?)}";
	
	public static String SP_DELETE_EDL_MAPP = "{call PK_LSDB_CLA_EDL_MAPP.SP_DELETE_EDL_MAPP(?,?,?,?,?,?)}";
	
	public static String SP_SELECT_MDL_CLA_EDL_CHANGES = "{call PK_LSDB_ORDER_CLAUSE.SP_SELECT_MDL_CLA_EDL_CHANGES(?,?,?,?,?,?,?,?)}";

	public static String SP_ACCREJ_CLAUSE_EDL_CHANGES = "{call PK_LSDB_ORDER_CLAUSE.SP_ACCREJ_CLAUSE_EDL_CHANGES(?,?,?,?,?,?,?,?)}";
	/** Queries for View Characterisitic Group Report By Model Report  by sd41630* */
	//Updated for CR_85 
	public static String SP_SELECT_CHAR_RPT_SPEC_BY_MDL = "{call PK_LSDB_MDL_CLAUSE.SP_SELECT_CHAR_RPT_SPEC_BY_MDL(?,?,?,?,?,?,?)}";
	//CR-84 added 
	public static String SP_SELECT_SPEC_TYPES = "{call PK_LSDB_SPEC_TYPE.SP_SELECT_SPEC_TYPE(?,?,?,?,?,?,?)}";
	
	public static String SP_INSERT_SPEC_TYPE = "{call PK_LSDB_SPEC_TYPE.SP_INSERT_SPEC_TYPE(?,?,?,?,?,?)}";
	public static String SP_UPDATE_SPEC_TYPE = "{call PK_LSDB_SPEC_TYPE.SP_UPDATE_SPEC_TYPE(?,?,?,?,?,?,?,?)}";
	//Added for CR_86
	public static String SP_ONOFF_DYNMIC_CLAUSE_NO = "{call PK_LSDB_ORDER_SPECS.SP_ONOFF_DYNMIC_CLAUSE_NO(?,?,?,?,?,?,?)}";
	
	
	//CR_84 lines are ended
	
//	 Added param For CR_88 on 02july10 by  sd 41630------- -->
	//Modified for CR_85
	public static String SP_REARRANGE_MDL_CLAUSES = "{call PK_LSDB_MDL_CLAUSE.SP_REARRANGE_MDL_CLAUSES(?,?,?,?,?,?,?)}";
//	 Added param For CR_81 on 02july10 by ------- - Ends here-->
    
	//Adding for CR_90
	public static String SP_VALIDATE_REQ_COMPGRPS = "{call PK_LSDB_ORDER_SPECS.SP_VALIDATE_REQ_COMPGRPS(?, ?, ?, ?, ?, ?, ?, ?)}";
	public static String SP_VALIDATE_PUBLISH_SPEC = "{call PK_LSDB_ORDER_SPECS.SP_VALIDATE_PUBLISH_SPEC(?, ?, ?, ?, ?, ?)}";
    //End here CR_90	

	//Adding for CR_91
	//Added One Parameter for CR_106 On Demand Spec Supplement
	public static String SP_ORDER_PRE_SPEC_SUPPLIMENT = "{call PK_LSDB_ORDER_SPECS.SP_ORDER_PRE_SPEC_SUPPLIMENT(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
//Added for CR_92 to clamapping to image
	public static String SP_MAP_MDL_CLA_APNDX_IMG = "{call PK_LSDB_APNDX_IMG.SP_MAP_MDL_CLA_APNDX_IMG(?,?,?,?,?,?,?,?)}";
	public static String Query_Fetch_Model_Section = "SELECT   CHR(b.LS201_SEC_CODE+64)||'. '||b.LS201_SEC_NAME AS LS201_SEC_NAME,CHR(b.LS201_SEC_CODE+64)||'.'||a.LS202_SUBSEC_CODE||' '||a.LS202_SUBSEC_NAME AS LS202_SUBSEC_NAME FROM LSDB202_MDL_SUBSEC a, LSDB201_MDL_SEC b WHERE a.LS202_SUBSEC_SEQ_NO = ? AND a.LS201_SEC_SEQ_NO = b.LS201_SEC_SEQ_NO";
//Adding for CR_92
	public static String SP_UPDATE_SYS_MARKER = "{call PK_LSDB_ORDER_CLAUSE.SP_UPDATE_SYS_MARKER(?,?,?,?,?,?,?)}";
	public static String SP_DELETED_CLAUSES_HISTORY="{call PK_LSDB_ORDER_CLAUSE.SP_SELECT_ORDR_DELETED_CLAUSES(?,?,?,?,?,?)}";
	public static String SP_RETRIEVE_DELETED_CLAUSE="{call PK_LSDB_ORDER_CLAUSE.SP_RETRIVE_ORDR_DELETED_CLA(?,?,?,?,?,?,?,?)}";
	//Added For CR_93 
	public static String SP_REARRANGE_ORDR_COMP_INFO="{call PK_LSDB_COMPONENTINFO.SP_REARRANGE_ORDR_COMP_INFO(?,?,?,?,?,?,?)}";
	public static String SP_COPY_NEW_COMP_CLA="{call PK_LSDB_ORDER_CLAUSE.SP_COPY_NEW_COMP_CLA(?,?,?,?,?,?,?)}";
	public static String SP_COPY_ORDR_COMP_TO_MDL="{call PK_LSDB_COMPONENT.SP_COPY_ORDR_COMP_TO_MDL(?,?,?,?,?,?,?,?)}";
	//Added For CR_97  on 15 march 2011 by SD41630 
	public static String SP_LSDB_MDL_CLAUSE = "{call PK_LSDB_MDL_CLAUSE.SP_SELECT_ALL_CLA_TIED_TO_COMP(?,?,?,?,?,?,?)}";
	//Added for CR_97
	public static String SP_CREATE_NEW_ORDR_COMP_REQ="{call PK_LSDB_CHNGE_CTRL.SP_CREATE_NEW_ORDR_COMP_REQ(?,?,?,?,?,?,?,?,?,?,?,?)}";
	
//	Adding for CR_99
	public static String SP_INS_OR_DEL_SALES_VERSION = "{call PK_LSDB_ORDER_CLAUSE.SP_INS_OR_DEL_SALES_VERSION(?,?,?,?,?,?,?,?)}";
//Added for CR_99 for Regeneration of PDFs
	public static String SP_PDF_RESTART_JOB = "{call PK_LSDB_DBJOB.SP_VALIDATE_PDF_RESTART_JOB(?,?,?,?,?)}";
	//CR_99 Ends here
	
	//Added For CR_100 Suggestions Module
	public static String SP_INSERT_SUGGESTION = "{call PK_LSDB_SUGGESTIONS.SP_INSERT_SUGGESTION(?,?,?,?,?,?,?,?,?)}";
	//Updated for CR-127
	public static String SP_SELECT_SUGGESTION = "{call PK_LSDB_SUGGESTIONS.SP_SELECT_SUGGESTION(?,?,?,?,?,?,?)}";
	
	public static String SP_UPDATE_SUGGESTION = "{call PK_LSDB_SUGGESTIONS.SP_UPDATE_SUGGESTION(?,?,?,?,?,?,?,?)}";
	
	public static String SP_SELECT_SUGG_STATUS = "{call PK_LSDB_SUGGESTIONS.SP_SELECT_SUGG_STATUS(?,?,?,?,?)}";
	
	public static String SP_DELETE_ATTACHMENT = "{call PK_LSDB_SUGGESTIONS.SP_DELETE_ATTACHMENT(?,?,?,?,?,?)}";
	//Ends here for CR_100
	
	// Added for CR-101 - Revision Changes 
	public static String SP_SELECT_REVISION_HISTORY = "{call PK_LSDB_REVISION_HISTORY.SP_SELECT_REVISION_HISTORY(?,?,?,?,?,?)}";
	//Added FOR CR_104
	public static String SP_EMAIL_TO_1058_ADMN = "{call PK_LSDB_UTIL.SP_EMAIL_TO_1058_ADMN(?,?,?,?,?,?,?,?,?,?,?)}";
	//Added for CR_108 - Order Specific Clause Report
	public static String SP_SELECT_ORDR_SPECIFIC_CLA = "{call PK_LSDB_ORDER_CLAUSE.SP_SELECT_ORDR_SPECIFIC_CLA(?,?,?,?,?,?,?)}";
	//Added For CR_109
	public static String SP_ADD_NEW_SUBSEC_TO_ORDR="{call PK_LSDB_SUBSECTION.SP_ADD_NEW_SUBSEC_TO_ORDR(?,?,?,?,?,?,?)}";
	
	//Modified for CR_109 Comments - Added a parameter for sort flag.
	public static String SP_CLA_BY_COMPS = "{call PK_LSDB_MDL_CLAUSE.SP_CLA_BY_COMPS(?,?,?,?,?,?,?,?)}";
	//Ends here
	
	public static String Query_UpdateCurrLogin = "UPDATE LSDB010_EMD_USERS SET LS010_CURR_LOGGEDIN = SYSDATE WHERE LS010_USER_ID = ?";
	//Added For CR_111
	public static String SP_REARRANGE_ORDR_CLAUSES = "{call PK_LSDB_ORDER_CLAUSE.SP_REARRANGE_ORDR_CLAUSES(?,?,?,?,?,?,?)}";
	
//	Added for CR-112 to Add Message Of the Day in home screen
	public static String SP_SELECT_MSG = "{call PK_LSDB_ADMN.SP_SELECT_MSG(?,?,?,?,?)}";
	 
	public static String SP_SELECT_MSG1 = "{call SP_SELECT_MSG(?)}";
	
	public static String SP_INSERT_MSG = "{call PK_LSDB_ADMN.SP_INSERT_MSG(?,?,?,?,?)}";
	
	public static String SP_INSERT_MSG1 = "{call SP_INSERT_MSG(?,?,?,?,?)}";
	//Added for CR-113 - Clauses With Indicators Report
	public static String SP_SEL_CLA_WITH_INDICATORS = "{call PK_LSDB_ORDER_CLAUSE.SP_SELECT_CLA_WITH_INDICATORS(?,?,?,?,?,?) }";
	
	public static String SP_SELECT_CLA_IN_ORDRS = "{call PK_LSDB_MDL_CLAUSE.SP_SELECT_CLA_IN_ORDRS(?,?,?,?,?,?,?,?,?) }";
	
	//Added for CR-110 - Create,Modify & Search 1058 Request 
	
	public static String SP_SELECT_ORDRS_FOR_1058 = "{call PK_LSDB_1058.SP_SELECT_ORDRS_FOR_1058(?,?,?,?,?) }";
	
	public static String SP_CREATE_1058_LSDB_REQUEST =	"{call PK_LSDB_1058.SP_CREATE_1058_LSDB_REQUEST(?,?,?,?,?,?,?) }";
	
	public static String SP_CREATE_1058_NLSDB_REQUEST =	"{call PK_LSDB_1058.SP_CREATE_1058_NLSDB_REQUEST(?,?,?,?,?,?) }";
	
	public static String SP_SELECT_1058_REQUESTS = "{call PK_LSDB_1058.SP_SELECT_1058_REQUESTS(?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	public static String SP_SELECT_USR_GROUPS_FOR_1058 = "{call PK_LSDB_1058.SP_SELECT_USR_GROUPS_FOR_1058(?,?,?,?,?)}";
	
	public static String SP_INSERT_1058_USER_LIST = "{call PK_LSDB_1058.SP_INSERT_1058_USER_LIST(?,?,?,?,?,?,?)}";
	
	public static String SP_UPDATE_1058_STATUS = "{call PK_LSDB_1058.SP_UPDATE_1058_STATUS(?,?,?,?,?,?,?)}";
	
	public static String SP_SELECT_1058_STATUS="{call PK_LSDB_1058.SP_SELECT_1058_STATUS(?,?,?,?,?) }";
	
	public static String SP_SELECT_1058_CLA_CHNG_REQ = "{call PK_LSDB_1058.SP_SELECT_1058_CLA_CHNG_REQ(?,?,?,?,?,?,?)}";
	
	//Added for Create,Modify,Select & Delete Non-Lsdb 1058 Request
	
	public static String SP_INS_1058_NLSDB_CLA_CHNG_REQ="{call PK_LSDB_1058.SP_INS_1058_NLSDB_CLA_CHNG_REQ(?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	public static String SP_DEL_1058_NLSDB_CLA_CHNG_REQ="{call PK_LSDB_1058.SP_DEL_1058_NLSDB_CLA_CHNG_REQ(?,?,?,?,?,?)}";
	
	public static String SP_UPD_1058_NLSDB_CLA_CHNG_REQ="{call PK_LSDB_1058.SP_UPD_1058_NLSDB_CLA_CHNG_REQ(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	public static String SP_SEL_1058_NLSDB_CLA_CHNG_REQ="{call PK_LSDB_1058.SP_SEL_1058_NLSDB_CLA_CHNG_REQ(?,?,?,?,?,?,?)}";
	
	public static String SP_INSERT_1058_ATTACHMENT ="{call PK_LSDB_1058.SP_INSERT_1058_ATTACHMENT(?,?,?,?,?,?,?)}";
	
	public static String SP_DELETE_1058_ATTACHMENT ="{call PK_LSDB_1058.SP_DELETE_1058_ATTACHMENT(?,?,?,?,?,?)}";
	
	public static String SP_SELECT_1058_ATTACHMENTS ="{call PK_LSDB_1058.SP_SELECT_1058_ATTACHMENTS(?,?,?,?,?,?)}";
	
	public static String SP_SELECT_1058_REQ_CATEGORY ="{call PK_LSDB_1058.SP_SELECT_1058_REQ_CATEGORY(?,?,?,?,?)}";
	
	public static String SP_SELECT_1058_REQ_TYPES ="{call PK_LSDB_1058.SP_SELECT_1058_REQ_TYPES(?,?,?,?,?)}";
	
	public static String SP_SELECT_1058_CLA_CHNG_TYPES ="{call PK_LSDB_1058.SP_SELECT_1058_CLA_CHNG_TYPES(?,?,?,?,?)}";
	
	public static String SP_SELECT_SUBSEC_FOR_1058 ="{call PK_LSDB_1058.SP_SELECT_SUBSEC_FOR_1058(?,?,?,?,?,?,?,?)}";
	
	public static String SP_SELECT_SEC_FOR_1058="{call PK_LSDB_1058.SP_SELECT_SEC_FOR_1058(?,?,?,?,?,?,?)}";
	
	public static String SP_SELECT_COMPMAP_FOR_1058 ="{call PK_LSDB_1058.SP_SELECT_COMPMAP_FOR_1058(?,?,?,?,?,?,?,?,?)}";
	
	public static String SP_INSERT_1058_CLA_CHNG_REQ="{call PK_LSDB_1058.SP_INSERT_1058_CLA_CHNG_REQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	public static String SP_DELETE_1058_CHNG_REQ="{call PK_LSDB_1058.SP_DELETE_1058_CHNG_REQ(?,?,?,?,?,?)}"; //Updated CR-130
	
	public static String SP_SEL_1058_CHNG_CLA_DTLS="{call PK_LSDB_1058.SP_SEL_1058_CHNG_CLA_DTLS(?,?,?,?,?,?,?)}";	
	
	public static String SP_UPD_1058_CHNG_CLA_DTLS="{call PK_LSDB_1058.SP_UPD_1058_CHNG_CLA_DTLS(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	public static String SP_UPD_CHNG_TO_SPEC="{call PK_LSDB_1058.SP_UPD_CHNG_TO_SPEC(?,?,?,?,?,?)}"; //Updated for CR-130
	
	//Added	for approve and reject all sections
	
	public static String SP_UPDATE_SYSTEM_ENGR = "{call PK_LSDB_1058.SP_UPDATE_SYSTEM_ENGR(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";
	
	public static String SP_UPDATE_OPERATIONS_REP = "{call PK_LSDB_1058.SP_UPDATE_OPERATIONS_REP(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";
	
	public static String SP_UPDATE_FINANCE_REP = "{call PK_LSDB_1058.SP_UPDATE_FINANCE_REP(?,?,?,?,?,?,?,?,?) }";
	
	public static String SP_UPDATE_PROGRAM_MGR ="{call PK_LSDB_1058.SP_UPDATE_PROGRAM_MGR(?,?,?,?,?,?,?)}";
	
	public static String SP_UPDATE_PROPOSAL_MGR = "{call PK_LSDB_1058.SP_UPDATE_PROPOSAL_MGR(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";
	
	public static String SP_UPDATE_1058_DETAILS = "{call PK_LSDB_1058.SP_UPDATE_1058_DETAILS(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	public static String SP_INSERT_1058_ACTION ="{call PK_LSDB_1058.SP_INSERT_1058_ACTION(?,?,?,?,?,?,?)}";
	
	//Added for CR-117
	//Updated for CR-118
	public static String SP_INSERT_1058_LEGACY_REQ ="{call PK_LSDB_1058.SP_INSERT_1058_LEGACY_REQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	public static String SP_DELETE_1058_REQUEST ="{call PK_LSDB_1058.SP_DELETE_1058_REQUEST(?,?,?,?,?)}";
	
	//Added for CR-118
	public static String SP_SELECT_ORDR_CLA_WITH_EDL = "{call PK_LSDB_ORDER_CLAUSE.SP_SELECT_ORDR_CLA_WITH_EDL(?,?,?,?,?,?,?)}";
	
	public static String SP_SEL_COMPGRPS_FOR_COC ="{call PK_LSDB_COMPGRP.SP_SEL_COMPGRPS_FOR_COC(?,?,?,?,?,?)}";
	
	public static String SP_UPDATE_COMPGRPS_FOR_COC = "{call PK_LSDB_COMPGRP.SP_UPDATE_COMPGRPS_FOR_COC(?,?,?,?,?,?,?)}";
	
	//Added for CR-121
	public static String SP_SELECT_MLTPLE_ORDRS = "{call PK_LSDB_ORDER_SPECS.SP_SELECT_MLTPLE_ORDRS(?,?,?,?,?,?,?)}";
	
	public static String SP_SEL_SPEC_ITEM_MLTPL_ORDRS = "{call PK_LSDB_SPEC_ITEM.SP_SEL_SPEC_ITEM_MLTPL_ORDRS(?,?,?,?,?,?,?)}";
	
	public static String SP_SEL_COMP_FRM_MLTPL_ORDRS = "{call PK_LSDB_COMPONENT.SP_SEL_COMP_FRM_MLTPL_ORDRS(?,?,?,?,?,?,?)}";
	
	public static String SP_SELECT_COMP_SPEC_FOR_ORDRS = "{call PK_LSDB_ORDER_SPECS.SP_SELECT_COMP_SPEC_FOR_ORDRS(?,?,?,?,?,?,?)}";
	
	public static String SP_REARRANGE_ORDR_PERF_CURV="{call PK_LSDB_PERF_CURVE.SP_REARRANGE_ORDR_PERF_CURV(?,?,?,?,?,?,?)}";
	
	public static String SP_REARRANGE_MDL_PERF_CURV="{call PK_LSDB_PERF_CURVE.SP_REARRANGE_MDL_PERF_CURV(?,?,?,?,?,?)}";
	
	public static String SP_COPY_CLA_DETAILS="{call PK_LSDB_MDL_CLAUSE.SP_COPY_CLA_DETAILS(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	public static String SP_REMOVE_CLA_TEMP ="{call PK_LSDB_ORDER_CLAUSE.SP_REMOVE_CLA_TEMP(?,?,?,?,?,?,?,?)}";
	
	//Added for CR-126
	public static String SP_UPDATE_1058_LEGACY_REQ ="{call PK_LSDB_1058.SP_UPDATE_1058_LEGACY_REQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	public static String SP_SELECT_1058_REPORTS="{call PK_LSDB_1058.SP_SELECT_1058_REPORTS(?,?,?,?,?,?)}";
	
	//Added for CR-127
	public static String SP_SELECT_OPENPENDING_1058 ="{call PK_LSDB_1058.SP_SELECT_OPENPENDING_1058(?,?,?,?,?)}";
	public static String SP_SELECT_MDL_CLA_CHANGES ="{call PK_LSDB_1058.SP_SELECT_MDL_CLA_CHANGES(?,?,?,?,?,?,?,?)}";
	public static String SP_IMPORT_MDL_CLA_TO_1058 ="{call PK_LSDB_1058.SP_IMPORT_MDL_CLA_TO_1058(?,?,?,?,?,?,?,?)}";
	//Added for CR-128 QA Fix - This sets DBMS Session parameter
	public static String Query_Set_Dbms_Session_UserID = "{call dbms_session.set_identifier(?)}";
	
	//Added for CR-130
	public static String SP_SELECT_MDL_SUBSEC_CHANGES   = "{call PK_LSDB_1058.SP_SELECT_MDL_SUBSEC_CHANGES(?,?,?,?,?,?,?,?)}";
	public static String SP_IMPORT_MDL_SUBSEC_TO_1058   = "{call PK_LSDB_1058.SP_IMPORT_MDL_SUBSEC_TO_1058(?,?,?,?,?,?,?,?)}";
	public static String SP_SELECT_1058_SUBSEC_CHNG_REQ = "{call PK_LSDB_1058.SP_SELECT_1058_SUBSEC_CHNG_REQ(?,?,?,?,?,?,?)}";
	
	//added for CR-134
	public static String SP_SELECT_1058_TRANSITIONS ="{call PK_LSDB_1058.SP_SELECT_1058_TRANSITIONS(?,?,?,?,?,?,?)}";
	public static String SP_MARK_MDL_CLAUSE_VER   = "{call PK_LSDB_MDL_CLAUSE.SP_MARK_MDL_CLAUSE_VER(?,?,?,?,?,?,?,?)}";
	
	
	//Added for CR-135
	public static String SP_COMPARE_MDL_ORDR_CLA = "{call PK_LSDB_ORDER_SPECS.SP_COMPARE_MDL_ORDR_CLA(?,?,?,?,?,?,?,?)}";
}