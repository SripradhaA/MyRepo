/**
 * 
 */
package com.EMD.LSDB.action.admn;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.EMD.LSDB.action.common.EMDAction;
import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.ChangePwdBI;
import com.EMD.LSDB.bo.serviceinterface.UserMaintBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.form.admn.ResetPasswordForm;
import com.EMD.LSDB.vo.admn.UserVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.PasswordVO;

/**
 * @author SI50968
 * 
 */

public class ResetPasswordAction extends EMDAction {
	
	public ResetPasswordAction() {
		
	}
	
	/***************************************************************************
	 * This Method is used to populate the UserID Dropdown on PageLoad
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 * 
	 **************************************************************************/
	
	public ActionForward initLoad(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil.logMessage("Entering ResetPasswordAction.initLoad");
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		ResetPasswordForm objResetPasswordForm = (ResetPasswordForm) objActionForm;
		String strForwardKey = ApplicationConstants.SUCCESS;
		try {
			UserVO objUserVo = new UserVO();
			ArrayList arlUserList = null;
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			// User Load starts here
			objUserVo.setUserID(objLoginVo.getUserID());
			UserMaintBI objUserBo = ServiceFactory.getUserMaintBO();
			arlUserList = objUserBo.fetchUsers(objUserVo);
			if (arlUserList != null)
				objResetPasswordForm.setUserDetails(arlUserList);
			LogUtil.logMessage("arrayList Value:" + arlUserList.size());
			LogUtil.logMessage(" Value:"
					+ objResetPasswordForm.getUserDetails());
			// User Load Ends here
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
		
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward ResetPassword(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		LogUtil.logMessage("Entering ResetPasswordAction.ResetPassword");
		String strForwardKey = ApplicationConstants.SUCCESS;
		String strUserID = null;
		int intSuccess = 0;
		ResetPasswordForm objChangePwdForm = (ResetPasswordForm) objActionForm;
		
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			ArrayList arlUserList = null;
			PasswordVO objPasswordVO = new PasswordVO();
			objPasswordVO.setUserId(objChangePwdForm.getUserId()); // This is
			// for the
			// reset the
			// password.
			objPasswordVO.setNewPassword(ApplicationConstants.RESETPASSWORD);
			objPasswordVO.setFlag(ApplicationConstants.RESETPASSWORD_FLAG);
			objPasswordVO.setUserID(objLoginVo.getUserID()); // This is for
			// update user
			// id.
			ChangePwdBI objChangePwdBI = ServiceFactory.getChangePwdBO();
			intSuccess = objChangePwdBI.updatePwd(objPasswordVO);
			//Updated for CR-112 for reset password message
			if (intSuccess == 0) {
				objChangePwdForm
				.setMessageID(ApplicationConstants.RESET_PASSWORD_ID);
				LogUtil.logMessage("" + objChangePwdForm.getMessageID());
			} else {
				objChangePwdForm.setMessageID("" + intSuccess);
			}
			
			UserVO objUserVo = new UserVO();
			objUserVo.setUserID(objLoginVo.getUserID());
			UserMaintBI objUserBo = ServiceFactory.getUserMaintBO();
			arlUserList = objUserBo.fetchUsers(objUserVo);
			if (arlUserList != null)
				objChangePwdForm.setUserDetails(arlUserList);
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception Block in Action..");
			LogUtil.logMessage(" Exception occured in Action:" + objExp);
			strForwardKey = ApplicationConstants.FAILURE;
		}
		LogUtil.logMessage("key" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
	}
}
