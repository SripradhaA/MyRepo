/**
 * 
 */
package com.EMD.LSDB.action.admn;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.EMD.LSDB.action.common.EMDAction;
import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.ChangeRequest1058BI;
import com.EMD.LSDB.bo.serviceinterface.SuggestBI;
import com.EMD.LSDB.bo.serviceinterface.UserMaintBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.form.admn.UserMaintForm;
import com.EMD.LSDB.vo.admn.UserVO;
import com.EMD.LSDB.vo.common.ChangeRequest1058VO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.SuggestVO;

/**
 * @author VV49326
 *
 */
public class UserMaintAction extends EMDAction {
	
	public UserMaintAction() {
		
	}
	
	/*******************************************************************************************
	 *  * * *		This Method is used to fetch User Details
	 *  
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return     	ActionForward  		   
	 * @throws     	EMDException
	 
	 ******************************************************************************************/
	
	public ActionForward fetchUsers(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil.logMessage("Entering UserMaintAction:fetchUsers");
		System.out.println("Entering UserMaintAction:fetchUsers");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList arlUsers = null;
		//Added for CR-112
		ArrayList arlUserRoles = null;
		ArrayList arlEmailDet = null;
		ArrayList arlActivityLogList = null;
		UserMaintForm objUserMaintForm = (UserMaintForm) objActionForm;
		//Added for CR-113 for BroadCastEmail
		String strScreenID;
		
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			System.out.println(objLoginVo);
			//Added for CR-113 for BroadCastEmail
			strScreenID= Integer.toString(objLoginVo.getIntScreenId());
			if (strScreenID.equalsIgnoreCase("45")) {
				strForwardKey = ApplicationConstants.BROADCASTEMAIL;
			}//Added for CR-113 for BroadCastEmail
			//Added for CR-124 Starts
			if(strScreenID.equalsIgnoreCase("50")) {
				strForwardKey = ApplicationConstants.PURGEEMAIL;
							
				UserVO objUserVO = new UserVO();
				objUserVO.setUserID(objLoginVo.getUserID());
				UserMaintBI objUserMaintBO = ServiceFactory.getUserMaintBO();
				arlEmailDet = objUserMaintBO.fetchEmailDetails(objUserVO);
				
				if (arlEmailDet != null && arlEmailDet.size() > 0) {
					objUserMaintForm.setEmailDetails(arlEmailDet);
				}else{
					objUserMaintForm
					.setMessageID(ApplicationConstants.NO_RECORD_MESSAGE_ID);
				}
				
				if (arlEmailDet.size() == 0) {
					objUserMaintForm.setMethod("Users");
				}
				
			}
			//Added for CR-124 ends
			
			//Added for CR-126 Starts
			if (strScreenID.equalsIgnoreCase("52")) {
				
				strForwardKey = ApplicationConstants.MANAGE_EMAIL_SUBSCRIPTIONS;
				UserVO objUserVO = new UserVO();
				objUserVO.setUserID(objLoginVo.getUserID());
				System.out.println("Print the user id-------"+objLoginVo.getUserID().toString());
				
				UserMaintBI objUserMaintBO = ServiceFactory.getUserMaintBO();
				objUserMaintForm.setEmailPeriod(objUserMaintBO.fetchEmailPeriod(objUserVO));
			}
			//Added for CR-126 ends here
			
			/*Added for CR-128
			if(strScreenID.equalsIgnoreCase("54")) {
				strForwardKey = ApplicationConstants.SUCCESS;
			UserVO objUserVO = new UserVO();
			
			objUserVO.setUserID(objLoginVo.getUserID());
				UserMaintBI objUserBo = ServiceFactory.getUserMaintBO();
				arlActivityLogList = objUserBo.fetchActivityLog(objUserVO);
				
					if (arlActivityLogList != null && arlActivityLogList.size() > 0){
						objUserMaintForm.setActivityLog(arlActivityLogList);
						LogUtil.logMessage("arrayList Value:" + arlActivityLogList.size());
					}
				}
			
			//Added for CR-128*/
			UserVO objUserVO = new UserVO();
			
			objUserVO.setUserID(objLoginVo.getUserID());
			
			//Added for CR-112 for Sorting by Vb106565
			objUserVO.setOrderbyFlag(objUserMaintForm.getOrderbyFlag());
			//Added for CR-112 for Sorting ends here
			UserMaintBI objUserMaintBO = ServiceFactory.getUserMaintBO();
			arlUsers = objUserMaintBO.fetchUsers(objUserVO);
			 
			
			if (arlUsers != null && arlUsers.size() > 0) {
				objUserMaintForm.setUserDetails(arlUsers);
				//Added for CR-112 for Sorting by vb106565
				objUserMaintForm.setOrderbyFlag(objUserMaintForm.getOrderbyFlag());
				//Added for CR-112 for Sorting ends here
			}
			
			if (arlUsers.size() == 0) {
				objUserMaintForm.setMethod("Users");
			}
			
			//Added for CR-112
			arlUserRoles = objUserMaintBO.fetchUserRoles(objUserVO);
			
			if (arlUserRoles != null && arlUserRoles.size() > 0) {
				objUserMaintForm.setUserRolesList(arlUserRoles);
			}
			
			if (arlUserRoles.size() == 0) {
				objUserMaintForm.setMethod("Users");
			}
			
			if(objUserMaintForm.getColumnheader() == null)
			objUserMaintForm.setColumnheader("FirstName");
			
			//Added for CR-112 Ends Here
			
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage(" Exception occured in UserMaintAction:"
					+ objExp);
			System.out.println(" Exception occured in UserMaintAction:"
					+ objExp);
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			System.out.println("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		LogUtil.logMessage("key" + strForwardKey);
		System.out.println("key" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/*******************************************************************************************
	 *  * * *		This Method is used to delete User Details
	 *  
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return     	ActionForward  		   
	 * @throws     	EMDException
	 
	 ******************************************************************************************/
	
	public ActionForward deleteUser(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil.logMessage("Entering UserMaintAction:deleteUser");
		System.out.println("Entering UserMaintAction:deleteUser");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList arlUsers = null;
//		Added for CR-112
		ArrayList arlUserRoles = null;
		int intDeleteUser = 0;
		UserMaintForm objUserMaintForm = (UserMaintForm) objActionForm;
		
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			System.out.println(objLoginVo);
			
			UserVO objUserVO = new UserVO();
			
			objUserVO.setUserrId(ApplicationUtil.trim(objUserMaintForm
					.getHdnUserID()));
			objUserVO.setUserID(objLoginVo.getUserID());
			
			UserMaintBI objUserMaintBO = ServiceFactory.getUserMaintBO();
			intDeleteUser = objUserMaintBO.deleteUser(objUserVO);
			
			if (intDeleteUser == 0) {
				objUserMaintForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				objUserMaintForm.setUserId("");
				objUserMaintForm.setEmailId("");
				objUserMaintForm.setFirstName("");
				objUserMaintForm.setLocationName("");
				objUserMaintForm.setLastName("");
				objUserMaintForm.setPassword("");
				objUserMaintForm.setRoleid("");
			} else {
				objUserMaintForm.setMessageID("" + intDeleteUser);
				objUserMaintForm.setUserId("");
				objUserMaintForm.setEmailId("");
				objUserMaintForm.setFirstName("");
				objUserMaintForm.setLocationName("");
				objUserMaintForm.setLastName("");
				objUserMaintForm.setPassword("");
				objUserMaintForm.setRoleid("");
			}
			
			objUserVO.setUserID(objLoginVo.getUserID());
			//Added for CR-112 for Sorting by vb106565
			//objUserVO.setOrderbyFlag(objUserMaintForm.getOrderbyFlag());
			//Added for CR-112 for Sorting ends here
			UserMaintBI objjUserMaintBO = ServiceFactory.getUserMaintBO();
			arlUsers = objjUserMaintBO.fetchUsers(objUserVO);
			
			if (arlUsers != null && arlUsers.size() > 0) {
				objUserMaintForm.setUserDetails(arlUsers);
			}
			
			if (arlUsers.size() == 0) {
				objUserMaintForm.setMethod("Users");
			}
			
//			Added for CR-112
			arlUserRoles = objUserMaintBO.fetchUserRoles(objUserVO);
			
			if (arlUserRoles != null && arlUserRoles.size() > 0) {
				objUserMaintForm.setUserRolesList(arlUserRoles);
			}
			
			if (arlUserRoles.size() == 0) {
				objUserMaintForm.setMethod("Users");
			}
			//Added for CR-112 Ends Here
			
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage(" Exception occured in UserMaintAction:"
					+ objExp);
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			System.out.println("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		LogUtil.logMessage("key" + strForwardKey);
		System.out.println("key" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/*******************************************************************************************
	 *  * * *		This Method is used to insert User Details
	 *  
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return     	ActionForward  		   
	 * @throws     	EMDException
	 
	 ******************************************************************************************/
	
	public ActionForward insertUser(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil.logMessage("Enters into UserMaintAction:insertUser");
		System.out.println("Enters into UserMaintAction:insertUser");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList arlUsers = null;
//		Added for CR-112
		ArrayList arlUserRoles = null;
		int intDeleteUser = 0;
		UserMaintForm objUserMaintForm = (UserMaintForm) objActionForm;
		
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			
			UserVO objUserVO = new UserVO();
			//Added for CR_121 Starts Here
			InetAddress thisIp =InetAddress.getLocalHost();
			String strIPAddess=thisIp.getHostAddress();
			
			if(strIPAddess.equalsIgnoreCase(ApplicationConstants.SERVER_IP_PROD))
			{
				objUserVO.setServerType(ApplicationConstants.SERVER_PROD);
			}
			else if(strIPAddess.equalsIgnoreCase(ApplicationConstants.SERVER_IP_QA)){
				objUserVO.setServerType(ApplicationConstants.SERVER_QA);
			}
			else{
				objUserVO.setServerType(ApplicationConstants.SERVER_DEV);
			}
			//Added for CR_121 Ends Here
			objUserVO.setUserrId(ApplicationUtil.trim(objUserMaintForm
					.getUserId()));
			objUserVO.setEmaillId(ApplicationUtil.trim(objUserMaintForm
					.getEmailId()));
			objUserVO.setFirsttName(ApplicationUtil.trim(objUserMaintForm
					.getFirstName()));
			objUserVO.setLocation(ApplicationUtil.trim(objUserMaintForm
					.getLocationName()));
			objUserVO.setLasttName(ApplicationUtil.trim(objUserMaintForm
					.getLastName()));
			objUserVO.setContacttNo(objUserMaintForm.getContactNo());
			objUserVO.setPassword(ApplicationUtil.trim(objUserMaintForm
					.getPassword()));
			objUserVO.setRole(ApplicationUtil
					.trim(objUserMaintForm.getRoleid()));
			objUserVO.setUserID(objLoginVo.getUserID());
			
			UserMaintBI objUserMaintBO = ServiceFactory.getUserMaintBO();
			intDeleteUser = objUserMaintBO.insertUser(objUserVO);
			
			if (intDeleteUser == 0) {
				objUserMaintForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				objUserMaintForm.setUserId("");
				objUserMaintForm.setEmailId("");
				objUserMaintForm.setFirstName("");
				objUserMaintForm.setLocationName("");
				objUserMaintForm.setLastName("");
				objUserMaintForm.setPassword("");
				objUserMaintForm.setRoleid("");
				objUserMaintForm.setContactNo("");
				
			} else {
				objUserMaintForm.setMessageID("" + intDeleteUser);
				objUserMaintForm.setUserId(objUserMaintForm.getUserId());
				objUserMaintForm.setEmailId(objUserMaintForm.getEmailId());
				objUserMaintForm.setFirstName(objUserMaintForm.getFirstName());
				objUserMaintForm.setLocationName(objUserMaintForm
						.getLocationName());
				objUserMaintForm.setLastName(objUserMaintForm.getLastName());
				objUserMaintForm.setPassword(objUserMaintForm.getPassword());
				objUserMaintForm.setRoleid(objUserMaintForm.getRoleid());
				objUserMaintForm.setContactNo(objUserMaintForm.getContactNo());
				
			}
			
			objUserVO.setUserID(objLoginVo.getUserID());
			
			//Added for CR-112 for Sorting by vb106565
			objUserVO.setOrderbyFlag(objUserMaintForm.getOrderbyFlag());
			//Added for CR-112 for Sorting ends here
			
			UserMaintBI objjUserMaintBO = ServiceFactory.getUserMaintBO();
			arlUsers = objjUserMaintBO.fetchUsers(objUserVO);
			
			if (arlUsers != null && arlUsers.size() > 0) {
				objUserMaintForm.setUserDetails(arlUsers);
			}
			
			if (arlUsers.size() == 0) {
				objUserMaintForm.setMethod("Users");
			}
			
//			Added for CR-112
			arlUserRoles = objUserMaintBO.fetchUserRoles(objUserVO);
			
			if (arlUserRoles != null && arlUserRoles.size() > 0) {
				objUserMaintForm.setUserRolesList(arlUserRoles);
			}
			
			if (arlUserRoles.size() == 0) {
				objUserMaintForm.setMethod("Users");
			}
			//Added for CR-112 Ends Here
			
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage(" Exception occured in UserMaintAction:"
					+ objExp);
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		LogUtil.logMessage("key" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/*******************************************************************************************
	 *  * * *		This Method is used to update User Details
	 *  
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return     	ActionForward  		   
	 * @throws     	EMDException
	 
	 ******************************************************************************************/
	
	public ActionForward updateUser(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil.logMessage("Enters into UserMaintAction:updateUser");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList arlUsers = null;
//		Added for CR-112
		ArrayList arlUserRoles = null;
		int intDeleteUser = 0;
		UserMaintForm objUserMaintForm = (UserMaintForm) objActionForm;
		
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			
			UserVO objUserVO = new UserVO();
			
			objUserVO.setUserrId(ApplicationUtil.trim(objUserMaintForm
					.getHdnUserID()));
			objUserVO.setEmaillId(ApplicationUtil.trim(objUserMaintForm
					.getHdnEmail()));
			objUserVO.setFirsttName(ApplicationUtil.trim(objUserMaintForm
					.getHdnFirstName()));
			objUserVO.setLocation(ApplicationUtil.trim(objUserMaintForm
					.getHdnLocation()));
			objUserVO.setLasttName(ApplicationUtil.trim(objUserMaintForm
					.getHdnLastName()));
			objUserVO.setContacttNo(objUserMaintForm.getHdnContactNo());
			objUserVO.setRole(ApplicationUtil.trim(objUserMaintForm
					.getHdnUserRole()));
			objUserVO.setUserID(objLoginVo.getUserID());
			
			UserMaintBI objUserMaintBO = ServiceFactory.getUserMaintBO();
			intDeleteUser = objUserMaintBO.updateUser(objUserVO);
			
			if (intDeleteUser == 0) {
				objUserMaintForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				objUserMaintForm.setUserId("");
				objUserMaintForm.setEmailId("");
				objUserMaintForm.setFirstName("");
				objUserMaintForm.setLocationName("");
				objUserMaintForm.setLastName("");
				objUserMaintForm.setPassword("");
				objUserMaintForm.setRoleid("");
				// Added for CR-112
				LogUtil.logMessage("objLoginVo.getUserID():"+objLoginVo.getUserID()+" objLoginVo.getRoleID():"+objLoginVo.getRoleID());
				if(objLoginVo.getUserID().equalsIgnoreCase(objUserVO.getUserrId())){
				LogUtil.logMessage("objUserVO.getRole():"+objUserVO.getRole());
					objLoginVo.setRoleID(objUserVO.getRole());
				}
				
			} else {
				objUserMaintForm.setMessageID("" + intDeleteUser);
				objUserMaintForm.setMessageID("" + intDeleteUser);
				objUserMaintForm.setUserId("");
				objUserMaintForm.setEmailId("");
				objUserMaintForm.setFirstName("");
				objUserMaintForm.setLocationName("");
				objUserMaintForm.setLastName("");
				objUserMaintForm.setPassword("");
				objUserMaintForm.setRoleid("");
			}
			
			objUserVO.setUserID(objLoginVo.getUserID());
			
			//Added for CR-112 for Sorting by vb106565
			objUserVO.setOrderbyFlag(objUserMaintForm.getOrderbyFlag());
			//Added for CR-112 for Sorting ends here
			
			UserMaintBI objjUserMaintBO = ServiceFactory.getUserMaintBO();
			arlUsers = objjUserMaintBO.fetchUsers(objUserVO);
			
			if (arlUsers != null && arlUsers.size() > 0) {
				objUserMaintForm.setUserDetails(arlUsers);
			}
			
			if (arlUsers.size() == 0) {
				objUserMaintForm.setMethod("Users");
			}
			
//			Added for CR-112
			arlUserRoles = objUserMaintBO.fetchUserRoles(objUserVO);
			
			if (arlUserRoles != null && arlUserRoles.size() > 0) {
				objUserMaintForm.setUserRolesList(arlUserRoles);
			}
			
			if (arlUserRoles.size() == 0) {
				objUserMaintForm.setMethod("Users");
			}
			//Added for CR-112 Ends Here
			
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage(" Exception occured in UserMaintAction:"
					+ objExp);
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		LogUtil.logMessage("key" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	//Added for CR-113 for BroadCast Email
	/*******************************************************************************************
	 *  * * *		This Method is used to broadcastEmail to Selected users.
	 *  
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return     	ActionForward  		   
	 * @throws     	EMDException
	 
	 ******************************************************************************************/
	
	public ActionForward broadcastEmail(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into UserMaintAction:broadcastEmail");
		String strForwardKey = ApplicationConstants.BROADCASTEMAIL;
		ArrayList arlUsers = null;
		ArrayList arlUserRoles = null;
		String[] arrUserList = null;
		int intSendMail = 0;
		UserMaintForm objUserMaintForm = (UserMaintForm) objActionForm;
		
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			
			UserVO objUserVO = new UserVO();
			arrUserList = objHttpServletRequest.getParameterValues("userID");
			objUserVO.setUserList(arrUserList);
			objUserVO.setSubject(ApplicationUtil.trim(objUserMaintForm
					.getSubject()));
			String strPreBody = "<style>p {margin: 0;padding: 0;border: 0;}</style>";// Added for broadcast email issue (CR-130)
			objUserVO.setBody(strPreBody + ApplicationUtil.trim(objUserMaintForm
					.getBody()));
			objUserVO.setUserID(objLoginVo.getUserID());
			
			UserMaintBI objUserMaintBO = ServiceFactory.getUserMaintBO();
			intSendMail = objUserMaintBO.broadcastEmail(objUserVO);
			
			if (intSendMail == 0) {
				objUserMaintForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				objUserMaintForm.setRoleName("");
				objUserMaintForm.setUserId("");
				objUserMaintForm.setSubject("");
				objUserMaintForm.setBody("");
			} else {
				objUserMaintForm.setMessageID("" + intSendMail);
				objUserMaintForm.setUserId(objUserMaintForm.getUserId());
			}
			
			objUserVO.setUserID(objLoginVo.getUserID());
			
			//Added for CR-112 for Sorting by vb106565
			objUserVO.setOrderbyFlag(objUserMaintForm.getOrderbyFlag());
			//Added for CR-112 for Sorting ends here
			
			UserMaintBI objjUserMaintBO = ServiceFactory.getUserMaintBO();
			arlUsers = objjUserMaintBO.fetchUsers(objUserVO);
			
			if (arlUsers != null && arlUsers.size() > 0) {
				objUserMaintForm.setUserDetails(arlUsers);
			}
			
			if (arlUsers.size() == 0) {
				objUserMaintForm.setMethod("Users");
			}
			
//			Added for CR-112
			arlUserRoles = objUserMaintBO.fetchUserRoles(objUserVO);
			
			if (arlUserRoles != null && arlUserRoles.size() > 0) {
				objUserMaintForm.setUserRolesList(arlUserRoles);
			}
			
			if (arlUserRoles.size() == 0) {
				objUserMaintForm.setMethod("Users");
			}
			//Added for CR-112 Ends Here
			
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage(" Exception occured in UserMaintAction:"
					+ objExp);
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		LogUtil.logMessage("key" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
		
	}
	//Added for CR-113 for BroadCast Email Ends here
	
	//Added for Cr-124 Starts
	/*******************************************************************************************
	 *  * * *		This Method is used to Purgeselected Emails
	 *  
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return     	ActionForward  		   
	 * @throws     	EMDException
	 
	 ******************************************************************************************/
	
	public ActionForward purgeEmail(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into UserMaintAction:purgeEmail");
		String strForwardKey = ApplicationConstants.PURGEEMAIL;
		ArrayList arlEmailDet = null;
		String[] arrEmailList = null;
		int intPurgeEmail = 0;
		UserMaintForm objUserMaintForm = (UserMaintForm) objActionForm;
		
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			
			UserVO objUserVO = new UserVO();
			arrEmailList = objHttpServletRequest.getParameterValues("emailID");
			objUserVO.setSeqNos(arrEmailList);
			objUserVO.setSeqNo(objUserMaintForm
					.getHdnSeqNo());
			objUserVO.setUserID(objLoginVo.getUserID());
			
			UserMaintBI objUserMaintBO = ServiceFactory.getUserMaintBO();
			intPurgeEmail = objUserMaintBO.purgeEmail(objUserVO);
			

			if (intPurgeEmail == 0) {
				objUserMaintForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				objUserMaintForm.setSeqNo(0);
				objUserMaintForm.setToEmailId("");
				objUserMaintForm.setEmailSubject("");
				objUserMaintForm.setSentTime("");
				} else {
				objUserMaintForm.setSeqNo(intPurgeEmail);
				objUserMaintForm.setToEmailId("");
				objUserMaintForm.setEmailSubject("");
				objUserMaintForm.setSentTime("");
				}
			objUserVO.setUserID(objLoginVo.getUserID());
			
			UserMaintBI objjUserMaintBO = ServiceFactory.getUserMaintBO();
			arlEmailDet = objjUserMaintBO.fetchEmailDetails(objUserVO);
			
			if (arlEmailDet != null && arlEmailDet.size() > 0) {
				objUserMaintForm.setEmailDetails(arlEmailDet);
			}
			else{
				objUserMaintForm
				.setMessageID(ApplicationConstants.NO_RECORD_MESSAGE_ID);
			}
			
			if (arlEmailDet.size() == 0) {
				objUserMaintForm.setMethod("Users");
			}
					
			
	}
		
		
		catch (Exception objExp) {
			LogUtil.logMessage(" Exception occured in UserMaintAction:"
					+ objExp);
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		LogUtil.logMessage("key" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
		
	}
//	Added for Cr-124 ends here
	
//Added for CR-126
	/*******************************************************************************************
	 *  * * *		This Method is used to Save email subscriptions updated values
	 *  
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return     	ActionForward  		   
	 * @throws     	EMDException
	 
	 ******************************************************************************************/
	
	public ActionForward save(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into UserMaintAction:save");
		String strForwardKey = ApplicationConstants.MANAGE_EMAIL_SUBSCRIPTIONS;
		String[] arrUserIDList = null;
		String[] arrActionNoticeFlags = null;
		String[] arrInformationNoticeFlags = null;
		String[] arrCCNoticeFlags = null;
		
		int intManageEmailSubscr = 0;
		int nUpdateEmailPeriod = 0;
		UserMaintForm objUserMaintForm = (UserMaintForm) objActionForm;
		
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			
			UserVO objUserVO = new UserVO();
			arrUserIDList	 = objHttpServletRequest.getParameterValues("userrId");
			objUserVO.setUserList(arrUserIDList);
			arrActionNoticeFlags = objUserMaintForm.getHdnActionNoticeFlag()
											.split(ApplicationConstants.COMMA);
			arrInformationNoticeFlags = objUserMaintForm.getHdnInformationNoticeFlag()
											.split(ApplicationConstants.COMMA);
			arrCCNoticeFlags = objUserMaintForm.getHdnCCNoticeFlag()
											.split(ApplicationConstants.COMMA);
			
			for(int nTrim=0; nTrim < arrUserIDList.length;nTrim++){
				arrActionNoticeFlags[nTrim] = arrActionNoticeFlags[nTrim].trim();
				arrInformationNoticeFlags[nTrim] = arrInformationNoticeFlags[nTrim].trim();
				arrCCNoticeFlags[nTrim] = arrCCNoticeFlags[nTrim].trim();
				/*LogUtil.logMessage("arrActionNoticeFlags[nTrim]:"+arrActionNoticeFlags[nTrim]);
				LogUtil.logMessage("arrInformationNoticeFlags[nTrim]:"+arrInformationNoticeFlags[nTrim]);
				LogUtil.logMessage("arrCCNoticeFlags[nTrim]:"+arrCCNoticeFlags[nTrim]);*/
			}
			
			objUserVO.setActionNoticeFlags(arrActionNoticeFlags);
			objUserVO.setInformationNoticeFlags(arrInformationNoticeFlags);
			objUserVO.setCcNoticeFlags(arrCCNoticeFlags);
			
			/*LogUtil.logMessage("objUserVO.getUserList().length"+objUserVO.getUserList().length);
			LogUtil.logMessage("objUserVO.getActionNoticeFlags().length"+objUserVO.getActionNoticeFlags().length);
			LogUtil.logMessage("objUserVO.getInformationNoticeFlags().length"+objUserVO.getInformationNoticeFlags().length);
			LogUtil.logMessage("objUserVO.getCcNoticeFlags().length"+objUserVO.getCcNoticeFlags().length);*/
			objUserVO.setUserID(objLoginVo.getUserID());
			
			UserMaintBI objUserMaintBO = ServiceFactory.getUserMaintBO();
			intManageEmailSubscr = objUserMaintBO.save(objUserVO);
			
			int nEmailPeriod = objUserMaintForm.getEmailPeriod();
			LogUtil.logMessage("nEmailPeriod:"+nEmailPeriod);
			objUserVO.setEmailPeriod(nEmailPeriod);
			nUpdateEmailPeriod = objUserMaintBO.updateEmailPeriod(objUserVO);
			
			if (intManageEmailSubscr == 0 || nUpdateEmailPeriod == 0) {
					objUserMaintForm.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
					this.fetchUsers(objActionMapping,objActionForm,objHttpServletRequest,objHttpServletResponse);
			}
			
		}
		
		
		catch (Exception objExp) {
			LogUtil.logMessage(" Exception occured in UserMaintAction:"
					+ objExp);
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		LogUtil.logMessage("key" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	public ActionForward exportUserDetails(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
			throws ApplicationException {
		LogUtil
				.logMessage("Inside the UserMaintAction:exportUserDetails");
		String strForwardKey = ApplicationConstants.SUCCESS;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
				.getAttribute(ApplicationConstants.USER_IN_SESSION);
		int rowCount = 0;
		int intPos = 0;
		ArrayList arlUserDetails = new ArrayList();
		String prevLoggedIn;

		UserVO objUserVO = new UserVO();
		try {

			// create workbook
			HSSFWorkbook workBook = new HSSFWorkbook();
			HSSFSheet sheet = workBook.createSheet("User_Detail(s)");
					
			HSSFCellStyle cellSuggestionStyle = workBook.createCellStyle();
			
			HSSFFont secFont = workBook.createFont();
			secFont.setFontName(HSSFFont.FONT_ARIAL);
			secFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			secFont.setColor(HSSFColor.BLACK.index);		
			secFont.setFontHeightInPoints((short) 10);
			
			cellSuggestionStyle.setFont(secFont);
	        cellSuggestionStyle.setFillForegroundColor(HSSFColor.WHITE.index);
	        cellSuggestionStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);			
			
			HSSFCellStyle cellOtherStyle = workBook.createCellStyle();
			
			HSSFFont subSecFont = workBook.createFont();			
			subSecFont.setFontName(HSSFFont.FONT_ARIAL);
			subSecFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			subSecFont.setColor(HSSFColor.BLACK.index);		
			subSecFont.setFontHeightInPoints((short) 10);
			
			cellOtherStyle.setFont(subSecFont);
			cellOtherStyle.setFillForegroundColor(HSSFColor.WHITE.index);
			cellOtherStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);	
			
			HSSFFont otherFont = workBook.createFont();			
			otherFont.setFontName(HSSFFont.FONT_ARIAL);
			otherFont.setColor(HSSFColor.BLACK.index);		
			otherFont.setFontHeightInPoints((short) 10);
			
			cellOtherStyle.setFont(otherFont);
			cellOtherStyle.setWrapText(true);
			cellOtherStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			cellOtherStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
			HSSFCellStyle cellClauseStyle = workBook.createCellStyle();

			cellClauseStyle.setFont(otherFont);
			cellClauseStyle.setWrapText(true);
			cellClauseStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
			Iterator listIteratorUserDetails = null;	 
			HSSFRow row = sheet.createRow(rowCount);
			
			objUserVO.setUserID(objLoginVo.getUserID());
			
			UserMaintBI objUserMaintBI = ServiceFactory.getUserMaintBO();
			
			arlUserDetails = objUserMaintBI.fetchUsers(objUserVO);
			
			LogUtil.logMessage("arlUserDetails.size(): " + arlUserDetails.size());
			
			if (arlUserDetails.size() != 0) {
					createHeadingForUserDetails(workBook, row, sheet);
					listIteratorUserDetails = arlUserDetails.iterator();
					
				while (listIteratorUserDetails.hasNext()) {
					
					objUserVO = new UserVO();
					objUserVO = (UserVO) listIteratorUserDetails.next();
			
					rowCount++;
					intPos=0;
					row = sheet.createRow(rowCount);
						
						if (objUserVO.getPrevLoggedIn() != null) {
							prevLoggedIn = objUserVO.getPrevLoggedIn();
						} else {
							prevLoggedIn = "";
						}
						
										
						//Added for CR-121 by vb106565 starts here	 
						createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 0,new HSSFRichTextString(objUserVO.getUserrId()));
						//Added for CR-121 by vb106565 ends here	
						createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 1,new HSSFRichTextString(objUserVO.getFirsttName()));						
						createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 2,new HSSFRichTextString(objUserVO.getLasttName()));
						createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 3,new HSSFRichTextString(objUserVO.getLocation()));
						createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 4,new HSSFRichTextString(objUserVO.getEmaillId()));
						createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 5,new HSSFRichTextString(objUserVO.getContacttNo()));
						createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 6,new HSSFRichTextString(objUserVO.getRoleName()));
						createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 7,new HSSFRichTextString(objUserVO.getPrevLoggedIn()));
						
			}
		}
			
			    objHttpServletResponse.setContentType("application/vnd.ms-excel");
				objHttpServletResponse.setHeader("Content-disposition", "attachment; filename=User_Detail(s).xls");
				OutputStream out = null;
				out = objHttpServletResponse.getOutputStream();
				workBook.write(out);			
				
		} catch (Exception objEx) {
			objEx.printStackTrace();
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		LogUtil.logMessage("strForward :" + strForwardKey);
		if (strForwardKey.equalsIgnoreCase(ApplicationConstants.FAILURE))
			return objActionMapping.findForward(strForwardKey);
		else
			return null;
	}



	public void createHeadingForUserDetails(HSSFWorkbook workBook, HSSFRow row,
			HSSFSheet sheet) throws Exception {

	HSSFCellStyle cellHeadStyle = workBook.createCellStyle();

	HSSFFont headFont = workBook.createFont();
			
	headFont.setFontName(HSSFFont.FONT_ARIAL);
	headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	headFont.setColor(HSSFColor.BLACK.index);	
	headFont.setFontHeightInPoints((short) 11);

	cellHeadStyle.setWrapText(true);
	cellHeadStyle.setFont(headFont);
	cellHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	cellHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//	row = sheet.createRow(rowCount);
//	Updated for CR-118 QA-Fix

		final String[] columnHeadings = { "User ID","First Name", "Last Name", "Location", "Email Address","Contact Phone Number", "User Role","Last Logged in"};
		final int[] columnWidth = {3500,6300,6300,6300,10000,7000,7000,6300};
		for (int intPos=0;intPos < 8; intPos++)
			{
			 createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,intPos,new HSSFRichTextString(columnHeadings[intPos]));
				 sheet.setColumnWidth(intPos,columnWidth[intPos]);
			 }
	}

	/***************************************************************************
	* This method is used to create the cell with int value
	* 
	* @author Mahindra Satyam Ltd
	* @version 1.0
	* @param HSSFSheet, HSSFRow, HSSFCellStyle, int, int, String
	* @return HSSFCell
	**************************************************************************/


	protected HSSFCell createCell(HSSFRow row, HSSFCellStyle headerStyle,
		int cellType, int position, int cellValue) {
//	This function is used to create a cell with int value
	HSSFCell cell = null;
	cell = row.createCell(position);
	cell.setCellStyle(headerStyle);
	cell.setCellType(cellType);
	cell.setCellValue(cellValue);
	return cell;
	}

	/***************************************************************************
	* This method is used to create the cell with String value
	* 
	* @author Mahindra Satyam Ltd
	* @version 1.0
	* @param HSSFSheet, HSSFRow, HSSFCellStyle, int, int, String
	* @return HSSFCell
	**************************************************************************/

	protected HSSFCell createCell(HSSFRow row, HSSFCellStyle headerStyle,
		int cellType, int colPosition, HSSFRichTextString cellValue) {
//	This function is used to create a cell with String value
	HSSFCell cell = null;
	cell = row.createCell(colPosition);
	cell.setCellStyle(headerStyle);
	cell.setCellType(cellType);
	cell.setCellValue(cellValue);
	return cell;
	}
//Added for CR-126 Ends Here	
	
	
	//Added for CR-128
	public ActionForward fetchActivityLog(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil.logMessage("Entering UserMaintAction.fetchActivityLog");
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		UserMaintForm objUserMaintForm = (UserMaintForm) objActionForm;
		String strForwardKey = ApplicationConstants.ACTIVITY_LOG;
		String strScreenID;
		try {
			UserVO objUserVO = new UserVO();
			ArrayList arlActivityLogList = null;
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			// User Load starts here
			objUserVO.setUserID(objLoginVo.getUserID());
			strScreenID= Integer.toString(objLoginVo.getIntScreenId());
			LogUtil.logMessage("objLoginVo.getIntScreenId()" +strScreenID);
						
				UserMaintBI objUserBo = ServiceFactory.getUserMaintBO();
				arlActivityLogList = objUserBo.fetchActivityLog(objUserVO);
				LogUtil.logMessage("arrayList Value:" + arlActivityLogList.size());
					if (arlActivityLogList != null && arlActivityLogList.size() > 0){
						objUserMaintForm.setActivityLog(arlActivityLogList);
						LogUtil.logMessage("arrayList Value:" + arlActivityLogList.size());
				
				}
		}
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception Block in Action..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		LogUtil.logMessage("key" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
	}
	//Added for CR-128
}


