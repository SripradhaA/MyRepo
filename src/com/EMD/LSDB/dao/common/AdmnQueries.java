/**
 * 
 */
package com.EMD.LSDB.dao.common;

/**
 * @author VV49326
 * 
 */
public interface AdmnQueries {
	//Modifyed for CR_94
	public static String SP_FETCH_USER = "{call PK_LSDB_ADMN.SP_SELECT_USER(?,?,?,?,?,?)}";
	public static String SP_FETCH_USER1 = "{call SP_SELECT_USER(?,?)}";
	public static String SP_FETCH_USER2 = "{? = call sp_select_user(?,?)}";
	public static String SP_FETCH_USER12 = "{call sp_select_user_111(?,?,?)}";
	public static String SP_DELETE_USER = "{call PK_LSDB_ADMN.SP_DELETE_USER(?,?,?,?,?)}";
	public static String SP_DELETE_USER_OLD = "{call SP_DELETE_USER(?,?,?)}";
	public static String SP_DELETE_USER_1 = "{call public.sp_delete_user(?,?) }";
	
	public static String SP_INSERT_USER = "{call PK_LSDB_ADMN.SP_INSERT_USER(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	public static String SP_INSERT_USER1 = "{call SP_INSERT_USER(?,?,?,?,?,?,?,?,?,?,?)}";
	
	public static String SP_INSERT_USER2 = "{call sp_insert_user(?,?,?,?,?,?,?,?,?,?)}";
	
	public static String SP_UPDATE_USER = "{call PK_LSDB_ADMN.SP_UPDATE_USER(?,?,?,?,?,?,?,?,?,?,?)}";
	
	public static String SP_UPDATE_USER1 = "{call SP_UPDATE_USER(?,?,?,?,?,?,?,?,?,?,?)}";
	
	public static String SP_UPDATE_USER2 = "{call SP_UPDATE_USER(?,?,?,?,?,?,?,?,?,?,?)}";
	
	//Added for CR_112 to bring the User Role ID & Role Names
	public static String SP_SELECT_USER_ROLES = "{call PK_LSDB_ADMN.SP_SELECT_USER_ROLES(?,?,?,?,?)}";
	
	//Added for CR-113 to send email 
	public static String SP_BROADCAST_EMAIL = "{call PK_LSDB_UTIL.SP_BROADCAST_EMAIL(?,?,?,?,?,?,?)}";
	
	//Added for Cr-124 to get email details
	public static String SP_SELECT_EMAILDTLS ="{call PK_LSDB_ADMN.SP_SELECT_EMAILDTLS(?,?,?,?,?)}";
	
	public static String SP_DELETE_EMAILDTLS ="{call PK_LSDB_ADMN.SP_DELETE_EMAILDTLS(?,?,?,?,?)}";
	
	//Added for CR-126 for manage email subscriptions
	public static String SP_UPDATE_EMAIL_SUBSCRIPTIONS ="{call PK_LSDB_ADMN.SP_UPDATE_EMAIL_SUBSCRIPTIONS(?,?,?,?,?,?,?,?)}";
	
	public static String SP_SELECT_EMAIL_REMINDER ="{call PK_LSDB_ADMN.SP_SELECT_EMAIL_REMINDER(?,?,?,?,?)}";
	
	public static String SP_UPDATE_EMAIL_REMINDER ="{call PK_LSDB_ADMN.SP_UPDATE_EMAIL_REMINDER(?,?,?,?,?)}";
	
	//Added for Cr-128 for Master Maintenance Activity log
	public static String SP_FETCH_ACTIVITY_LOG = "{call PK_LSDB_ADMN.SP_SELECT_ACTIVITY_REPORT(?,?,?,?,?)}";
}
