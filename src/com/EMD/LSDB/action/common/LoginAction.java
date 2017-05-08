package com.EMD.LSDB.action.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
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
import com.EMD.LSDB.form.common.EMDForm;
import com.EMD.LSDB.form.common.LoginForm;
import com.EMD.LSDB.form.cr1058.SearchRequest1058Form;
import com.EMD.LSDB.vo.admn.UserVO;
import com.EMD.LSDB.vo.common.LoginVO;

public class LoginAction extends DispatchAction {
	
	public ActionForward findUser(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		String strForwardKey = ApplicationConstants.SUCCESS;
		ActionForward actionRedirect = null;
		try {
			LogUtil.logMessage("Inside the findUser method of LoginAction");
			HttpSession objSession = objHttpServletRequest.getSession(false);
			
			if (objSession != null){
				UserVO objUserExistVO = new UserVO();
				LoginVO objLoginExistVO = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
				if (objLoginExistVO != null) {
					objUserExistVO.setUserID(objLoginExistVO.getUserID());
					objUserExistVO.setUserrId(objLoginExistVO.getUserID());
					objUserExistVO.setRole(objLoginExistVO.getRoleID());
					objUserExistVO.setLoginFlag(ApplicationConstants.NO);
					
					UserMaintBI objUserMainBO = ServiceFactory.getUserMaintBO();
					int intUpdateLogout = objUserMainBO.updateUser(objUserExistVO);
					if(intUpdateLogout != 0){
						strForwardKey = ApplicationConstants.FAILURE;
					}
				}
			} else			
				objSession = objHttpServletRequest.getSession(true);
			
			ActionErrors objActionErrors = null;
			LoginVO objLoginVO = new LoginVO();
			LoginForm objLoginForm = (LoginForm) objActionForm;
			objLoginVO.setUserID(objLoginForm.getUserID());
			objLoginVO.setPassword(objLoginForm.getPassword());
			LoginBI lLoginActionBo = ServiceFactory.getLoginBO();
			
			if (objLoginVO.getUserID() == null
					|| objLoginVO.getPassword() == null
					|| "".equals(objLoginVO.getUserID())
					|| "".equals(objLoginVO.getPassword())) {
				LogUtil.logMessage("Enters into Empty block:");
				objActionErrors = new ActionErrors();
				objActionErrors.add(ApplicationConstants.FAILURE,
						new ActionError("error.invalid.user"));
				saveErrors(objHttpServletRequest, objActionErrors);
				//objLoginForm.setUserID("");
				objLoginForm.setPassword("");
				strForwardKey = ApplicationConstants.FAILURE;
				
			} else {
				if (lLoginActionBo.findUser(objLoginVO)) {
					//Added for CR-112 for threw error message for disabled user
					if(objLoginVO.getRoleID().equalsIgnoreCase("DSBD")){
						LogUtil.logMessage("Enters into inside if block of loginAction:");
						objActionErrors = new ActionErrors();
						objActionErrors.add(ApplicationConstants.FAILURE,
								new ActionError("error.disabled.user"));
						saveErrors(objHttpServletRequest, objActionErrors);
						// objLoginForm.setUserID("");
						objLoginForm.setPassword("");
						strForwardKey = ApplicationConstants.FAILURE;
						
					}else{
					LogUtil.logMessage("Enters into inside else block Of loginAction :");
					objSession.setAttribute(
							ApplicationConstants.USER_IN_SESSION, objLoginVO);
					//Added for CR_100 to display the UserId in Suggestion Box 
					objSession.setAttribute("UserId", objLoginVO
							.getUserID());
					objSession.setAttribute("FirstName", objLoginVO
							.getFirstName());
					objSession.setAttribute("LastName", objLoginVO
							.getLastName());
					//Added for CR_100 to display the UserId + First Name + LastName in Suggestion Box 				
					objSession.setAttribute("FullName", objLoginVO.getUserID().trim()+" - "+objLoginVO.getFirstName().trim()+" "+objLoginVO.getLastName().trim());
					// Added For CR_100 to avoid homepage screenId being unset
					objSession.setAttribute("screenid", ApplicationConstants.HOMEPAGE_SCREENID);
					
					//If condition Added for CR-126
					
					//LogUtil.logMessage("objLoginForm.getEmailFlag():"+objLoginForm.getEmailFlag());
					//LogUtil.logMessage("objActionMapping.getAttribute():"+objActionMapping.getAttribute());
					
					if(objLoginForm.getEmailFlag().equalsIgnoreCase("true")){	
						ActionForward actionForward = objActionMapping
						.findForward(strForwardKey);
						actionRedirect = new ActionForward(actionForward.getName(),
								"SearchRequest1058Action.do"
								+ "?method=fetchDetails&EmailFlag="+objLoginForm.getEmailFlag(), true  //REDIRECT 
						);
					}else{
						LogUtil.logMessage("objLoginForm.getEmailFlag():"+objLoginForm.getEmailFlag());
						//Added for CR-112 to Add Message Of the Day in home screen
						UserVO objUserVO = new UserVO();
						objUserVO.setUserID(objLoginVO.getUserID());
						objUserVO.setUserrId(objLoginVO.getUserID());
						objUserVO.setRole(objLoginVO.getRoleID());
						objUserVO.setLoginFlag(ApplicationConstants.YES);
						
						UserMaintBI objUserMaintBO = ServiceFactory.getUserMaintBO();
						int intUpdateUser = objUserMaintBO.updateUser(objUserVO);
						if(intUpdateUser != 0){
							strForwardKey = ApplicationConstants.FAILURE;
						}
						//LogUtil.logMessage("strFowardKey in redirect" +strForwardKey);
						ActionForward actionForward = objActionMapping
						.findForward(strForwardKey);
						
						actionRedirect = new ActionForward(actionForward.getName(),
								"LogoutAction.do"
								+ "?method=homePage", true  //REDIRECT 
						);
						//Added for CR-112 to Add Message Of the Day in home screen  Ends here
						}
						//Added for CR-134 starts
						if(objLoginVO.getResetFlag().equalsIgnoreCase("Y")){
							LogUtil.logMessage("inside");
							actionRedirect = null;
							strForwardKey = ApplicationConstants.CHANGEPSW;
						}
						//Added for CR-134 ends
					}

				} else {
					LogUtil.logMessage("Enters into else block:");
					objActionErrors = new ActionErrors();
					objActionErrors.add(ApplicationConstants.FAILURE,
							new ActionError("error.invalid.user"));
					saveErrors(objHttpServletRequest, objActionErrors);
					// objLoginForm.setUserID("");
					objLoginForm.setPassword("");
					strForwardKey = ApplicationConstants.FAILURE;
					
				}
			}
			String struri = objHttpServletRequest.getRequestURI();
			String strActionName = struri
			.substring(struri.lastIndexOf("/") + 1);
			LogUtil.logMessage("strActionName" + strActionName);
			LogUtil.logMessage("struri" + struri);
			
		} catch (Exception objExp) {
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objErrorInfo);
			// strForwardKey = ApplicationConstants.FAILURE;
			strForwardKey = "errorpage";
			
		}
		if (actionRedirect == null){
			return objActionMapping.findForward(strForwardKey);
		}
		else{
			return actionRedirect;
		}
		
		
	}
	
}
