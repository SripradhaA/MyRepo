/**
 * 
 */
package com.EMD.LSDB.common.framework.notificationservice;

/**
 * @author ps57222
 *
 */
/*
 * 
 */

public interface NotificationQueries {
	
	// Mail notification queries
	
	public final static String UPDATE_MAIL_STATUS = "update LSDB180_EMAIL_LOG set LS180_EMAIL_SENT_FLAG =?,LS180_EMAIL_TS = sysdate where LS180_EMAIL_SEQ_NO =?";
	
	public final static String UNSENT_MAILS = " select LS180_EMAIL_FROM_X,LS180_EMAIL_TO_X,LS180_EMAIL_CC_X,LS180_EMAIL_SUBJECT_X,LS180_EMAIL_BODY_X,LS180_EMAIL_SENT_FLAG,LS180_EMAIL_SEQ_NO from LSDB180_EMAIL_LOG where LS180_EMAIL_SENT_FLAG=? ORDER BY LS180_EMAIL_SEQ_NO ASC";
	
}
