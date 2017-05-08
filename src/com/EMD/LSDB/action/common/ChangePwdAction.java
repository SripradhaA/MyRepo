/* AK49339
 * Created on Aug 9, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.action.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.ChangePwdBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.form.common.ChangePwdForm;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.PasswordVO;

public class ChangePwdAction extends EMDAction {
	public ActionForward initLoad(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		String strForward = ApplicationConstants.SUCCESS;
		ChangePwdForm objChangePwdForm = (ChangePwdForm) objActionForm;
		return objActionMapping.findForward(strForward);
		
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
	
	public ActionForward updatePwd(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		LogUtil.logMessage("Entering ChangePwd.Modify");
		String strForwardKey = ApplicationConstants.SUCCESS;
		String strUserID = null;
		int intSuccess = 0;
		ChangePwdForm objChangePwdForm = (ChangePwdForm) objActionForm;
		
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			// Getting User from the session
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			PasswordVO objPasswordVO = new PasswordVO();
			objPasswordVO.setNewPassword(objChangePwdForm.getNewPassword());
			objPasswordVO.setUserID(strUserID);// This is for update user id.
			objPasswordVO.setUserId(strUserID);// This user id is for changing
			// the password.In this case
			// both will be the same.
			objPasswordVO.setFlag(ApplicationConstants.CHANGEPASSWORD_FLAG);
			ChangePwdBI objChangePwdBI = ServiceFactory.getChangePwdBO();
			intSuccess = objChangePwdBI.updatePwd(objPasswordVO);
			objChangePwdForm.setNewPassword("");
			objChangePwdForm.setConfirmPassword("");
			if (intSuccess == 0) {
				objChangePwdForm
				.setMessageID(ApplicationConstants.SUCCESS_PASSWORD_ID);
				LogUtil.logMessage("" + objChangePwdForm.getMessageID());
			} else {
				objChangePwdForm.setMessageID("" + intSuccess);
			}
			
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception Block in Action..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
		}
		LogUtil.logMessage("key" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
	}
}
