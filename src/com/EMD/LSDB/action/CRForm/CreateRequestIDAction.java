/*
 * Created on Jun 21, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.action.CRForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.EMD.LSDB.action.common.EMDAction;
import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.ChangeRequestBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.form.CRForm.CreateRequestForm;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.RequestVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This Action class has the business methods for the Create Change
 *          Request Form *********************************************** ```
 ******************************************************************************/

public class CreateRequestIDAction extends EMDAction {
	
	/***************************************************************************
	 * This method is for ladning Create Change Requst Form
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward initLoad(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil.logMessage("Entering CreateRequestIDAction.initLoad");
		String strForwardKey = null;
		
		try {
			
			strForwardKey = ApplicationConstants.SUCCESS;
		} catch (Exception objExp) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***************************************************************************
	 * This method is for inserting a new model
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward createRequestID(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException, ApplicationException {
		
		LogUtil.logMessage("Entering CreateRequestIDAction.createRequestID");
		String strForwardKey = null;
		CreateRequestForm objChangeRequestForm = (CreateRequestForm) objActionForm;
		String strUserID = null;
		ActionForward actionRedirect = null;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			RequestVO objRequestVO = new RequestVO();
			int intReqID = 0;
			String strShortDescription = null;
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			// Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			strShortDescription = ApplicationUtil.trim(objChangeRequestForm
					.getShortDescription());
			
			objRequestVO.setRequestDesc(strShortDescription);
			objRequestVO.setUserID(strUserID);
			ChangeRequestBI objChangeRequestBI = ServiceFactory
			.getChangeRequestBO();
			
			intReqID = objChangeRequestBI.createRequestID(objRequestVO);
			
			actionRedirect = new ActionForward(
					"ComponentGroupRequest.do?method=fetchComponentGroupDetails"
					+ "&reqID=" + intReqID, true /* REDIRECT */
			);
			
			LogUtil.logMessage("actionRedirect :" + actionRedirect);
			
		} catch (Exception objEx) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return actionRedirect;
	}
	
}
