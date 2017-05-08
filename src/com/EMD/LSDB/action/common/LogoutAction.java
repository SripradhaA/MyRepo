/**
 * 
 */
package com.EMD.LSDB.action.common;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.LoginBI;
import com.EMD.LSDB.bo.serviceinterface.UserMaintBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.form.admn.UserMaintForm;
import com.EMD.LSDB.vo.admn.UserVO;
import com.EMD.LSDB.vo.common.EMDVO;
import com.EMD.LSDB.vo.common.LoginVO;

/**
 * @author PS57222
 *
 */
public class LogoutAction extends DispatchAction {
	
	/*******************************************************************************************
	 *  * * *		This Method is used to Inavlid the Session 
	 *  
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return     	ActionForward  		   
	 * @throws     	EMDException
	 
	 ******************************************************************************************/
	
	public ActionForward logout(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil.logMessage("Inside the logout method of LogoutAction");
		String strForwardKey = ApplicationConstants.SUCCESS;
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		LogUtil.logMessage("Session Value Befor Inavlidate:" + objSession);
		if (objSession != null) {
			//Added for CR_109
			LoginVO objLoginVO = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if (objLoginVO != null) {
				try {
					UserVO objUserVO = new UserVO();
					objUserVO.setUserID(objLoginVO.getUserID());
					objUserVO.setUserrId(objLoginVO.getUserID());
					objUserVO.setRole(objLoginVO.getRoleID());
					objUserVO.setLoginFlag(ApplicationConstants.NO);
					
					UserMaintBI objUserMaintBO = ServiceFactory.getUserMaintBO();
					int intUpdateUser = objUserMaintBO.updateUser(objUserVO);
					if(intUpdateUser != 0){
						strForwardKey = ApplicationConstants.FAILURE;
					}
				}
				catch(Exception objExp){
					ErrorInfo objErrorInfo = new ErrorInfo();
					String strError = objExp.getMessage();
					objErrorInfo.setMessage(strError);
					LogUtil.logMessage("Error Message:" + strError);
					LogUtil.logError(objErrorInfo);
					strForwardKey = ApplicationConstants.FAILURE;
				}
			}
			//Added for CR_109  Ends here
			objSession.invalidate();
			
			LogUtil.logMessage("Session Value After Inavlidate:");
		}
		
		LogUtil.logMessage("Session Value After Inavlidate:" + objSession);
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/*******************************************************************************************
	 *  * * *		This Method is used to get the homepage  
	 *  
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return     	ActionForward  		   
	 * @throws     	EMDException
	 
	 ******************************************************************************************/
	
	public ActionForward homePage(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil.logMessage("Inside the homePage method of LogoutAction");
		String strForwardKey = ApplicationConstants.HOME_PAGE;
		try{
		//Added for Help text
		HttpSession objSession = objHttpServletRequest.getSession(false);
		if (objHttpServletRequest.getParameter("screenid") != null) {
			objSession.setAttribute("screenid",objHttpServletRequest
				.getParameter("screenid"));
		}
		//Ends
		
//		Added for CR-112 to Add Message Of the Day in home screen
		
		LoginVO objLoginVO = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		UserMaintForm objUserMaintForm = (UserMaintForm) objActionForm;
		LoginBI lLoginActionBo = ServiceFactory.getLoginBO();
		
		ArrayList arlMessage = lLoginActionBo.fetchMessage(objLoginVO);
		
		if(arlMessage.size() != 0 && arlMessage != null){
			UserVO objUserMsgVO = (UserVO) arlMessage.get(0);
			objUserMaintForm.setHdnMessage(objUserMsgVO.getMessage());
			objUserMaintForm.setRoleID(objLoginVO.getRoleID());
		}
		
		
		} catch(Exception objExp){
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objErrorInfo);
			// strForwardKey = ApplicationConstants.FAILURE;
			strForwardKey = "errorpage";
		}
//		Added for CR-112 to Add Message Of the Day in home screen Ends Here
		
		return objActionMapping.findForward(strForwardKey);
	}
	
	/*******************************************************************************************
	 *  * * *		This Method is used to get the help content 
	 *  
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return     	ActionForward  		   
	 * @throws     	EMDException
	 
	 ******************************************************************************************/
	
	public ActionForward help(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil.logMessage("Inside the help method of LogoutAction");
		String strForwardKey = ApplicationConstants.HELP_PAGE;
		
		LogUtil.logMessage("Forward key Value:" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
	}
//	Added for CR-112 to Add Message Of the Day in home screen
	/*******************************************************************************************
	 *  * * *		This Method is used to insert Message of the Day 
	 *  
	 * @author  	Mahindra Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return     	ActionForward  		   
	 * @throws     	EMDException
	 
	 ******************************************************************************************/
	
	public ActionForward insertMessage(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil.logMessage("Enters into LogoutAction:insertMessage");
		String strForwardKey = ApplicationConstants.MOD;
		ActionForward actionRedirect = null;
		UserMaintForm objUserMaintForm = (UserMaintForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			
			EMDVO objEMDVO = new EMDVO();
			
			objEMDVO.setMessage(ApplicationUtil.trim(objUserMaintForm.getHdnMessage()));
					
			objEMDVO.setUserID(objLoginVo.getUserID());
			
			LoginBI objLoginActionBo = ServiceFactory.getLoginBO();
			int intInsertMsg = objLoginActionBo.insertMessage(objEMDVO);
			
			if (intInsertMsg == 0) {
				objUserMaintForm.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} 
			
			ActionForward actionForward = objActionMapping.findForward(strForwardKey);
			
			actionRedirect = new ActionForward(actionForward.getName(),
					"LogoutAction.do"
					+ "?method=homePage", true  //REDIRECT 
			);
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage(" Exception occured in LogoutAction:"
					+ objExp);
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		if (actionRedirect == null)
			return objActionMapping.findForward(strForwardKey);
		else
			return actionRedirect;
		
	}
	
}
