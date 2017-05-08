package com.EMD.LSDB.action.common;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;

import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.LoginBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.form.MasterMaintenance.ModelAddClauseForm;
import com.EMD.LSDB.form.common.EMDForm;
import com.EMD.LSDB.form.common.LoginForm;
import com.EMD.LSDB.vo.common.EMDVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.dao.common.DBHelper;

public class EMDAction extends DispatchAction {
	
	static ArrayList objArrayList;
	
	boolean isValidUser = false;
	
	String strNotAuthorised = "NotAuthorised";
	
	public ActionForward execute(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException,
			ServletException {
		
		LogUtil.logMessage("Inside BaseActionExecuteMethod......");
		HttpSession objSession = objHttpServletRequest.getSession(false);
		EMDVO objEMDVO = new EMDVO();
		// Getting the User ID from the session and set
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		EMDForm objEmdForm = (EMDForm) objActionForm;//Added for CR-121
		String strForwardKey = ApplicationConstants.SUCCESS;
		ActionForward actionRedirect = null; //added for CR-126
		
		// Getting User from the session
		if (objLoginVo != null) {
			
			objEMDVO.setUserID(objLoginVo.getUserID());
			
			LogUtil.logMessage("objEMDVO.getUserID in EMD Action......"
					+ objEMDVO.getUserID());
		}
		
		if(objEMDVO.getUserID() == null || objSession == null){
			//Added for CR-126	
			String strEmailFlag = objHttpServletRequest.getParameter("EmailFlag");
			//LogUtil.logMessage("strEmailFlag:"+strEmailFlag);
			if(strEmailFlag == null || strEmailFlag == "" || strEmailFlag.length() == 0
					|| objEMDVO.getUserID() != null){
				strEmailFlag = "false";
			}
			//LogUtil.logMessage("strEmailFlag:"+strEmailFlag);
			if(strEmailFlag.equalsIgnoreCase("true")){
				ActionForward actionForward = objActionMapping
				.findForward(strForwardKey);
				return actionRedirect = new ActionForward(actionForward.getName(),
						"Login.do?EmailFlag="+strEmailFlag, true  //REDIRECT 
				);
			}else{
				ActionForward actionForward = objActionMapping
				.findForward(strForwardKey);
				return actionRedirect = new ActionForward(actionForward.getName(),
						"Login.do", true  //REDIRECT 
				);
			}
			//Added for CR-126 Ends here
		}else{
			objSession.setAttribute("screenid",ApplicationConstants.SEARCH1058_SCREENID); //Added for CR-130 QA-fix
		}
		
		
		try {
				boolean isAuthendicated = screenAccess(objHttpServletRequest);
				if (isAuthendicated) {
					LogUtil
					.logMessage("Inside BaseAction ExecuteMethod True......");
					
					//Added for Help text
					if (objHttpServletRequest.getParameter("screenid") != null) {
						objSession.setAttribute("screenid",objHttpServletRequest
							.getParameter("screenid"));
						//CR_84 Added Screen ID into Vo
						int intScreenID=Integer.parseInt((String)objSession.getAttribute("screenid"));
					  	objLoginVo.setIntScreenId(intScreenID);
					  	objEmdForm.setIntScreenId(intScreenID);//Added for CR-121
					}else if(objEmdForm.getIntScreenId() != 0){//Else IF Added for CR-121
						if(objLoginVo.getIntScreenId() !=  objEmdForm.getIntScreenId()){
							objLoginVo.setIntScreenId(objEmdForm.getIntScreenId());
						}
					}
					//Ends
					//Added for CR-128 QA Fix - This sets DBMS Session parameter
					try{
						DBHelper.setSessionUserID(objLoginVo);
					}catch (Exception objExp) {
						objExp.printStackTrace();
						LogUtil
						.logMessage("Enters into Exception Block in EMDAction");
						ErrorInfo objErrorInfo = new ErrorInfo();
						String strError = objErrorInfo.getMessage();
						LogUtil.logMessage("Error Message:" + strError);
						LogUtil.logError(objExp, objErrorInfo);
						strForwardKey = ApplicationConstants.FAILURE;
					}
					//Added for CR-128 QA Fix - This sets DBMS Session parameter ends here
					return super.execute(objActionMapping, objActionForm,
							objHttpServletRequest, objHttpServletResponse);
				} else {
					LogUtil
					.logMessage("Inside BaseAction ExecuteMethod False......");
					String strScreenId = "";
					if (objHttpServletRequest.getParameter("screenid") != null) {
						strScreenId = objHttpServletRequest
						.getParameter("screenid");
						//CR_84 maintain the Screen id 
						int intScreenID=Integer.parseInt((String)objSession.getAttribute("screenid"));
						objLoginVo.setIntScreenId(intScreenID);
						
					}
					//Added for CR-112
					else{
						strScreenId =  (String) objSession.getAttribute("screenid");
					}
					//Added for CR-112 ends here
					objHttpServletRequest.setAttribute("ScreenName",
							MessageResources.getMessageResources("EmdLsdb")
							.getMessage(strScreenId));
					LogUtil.logMessage("Screen Name:"
							+ objHttpServletRequest.getAttribute("ScreenName"));
					return objActionMapping.findForward(strNotAuthorised);
				}
		} catch (DataAccessException objDAE) {
			throw new DataAccessException(objDAE);
		} catch (Exception objExcep) {
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExcep.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objErrorInfo);
			return objActionMapping.findForward(ApplicationConstants.FAILURE);
			//throw new ApplicationException(objExcep);
		}
		
	}
	
	public boolean screenAccess(HttpServletRequest objRequest)
	throws EMDException {
		LogUtil.logMessage("Entered EMDAction::screenAccess method......");
		objArrayList = new ArrayList();
		String strRoleID = null;
		
		int intScreenID = 0;
		HttpSession objSession = objRequest.getSession(false);
		if ((LoginVO) objSession
				.getAttribute(ApplicationConstants.USER_IN_SESSION) != null) {
			LoginVO objLognVO = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			strRoleID = objLognVO.getRoleID();
		}
		if (objRequest.getParameter("screenid") != null) {
			intScreenID = Integer.parseInt(objRequest.getParameter("screenid"));
			objSession.setAttribute("screenid", objRequest
					.getParameter("screenid"));
			
		} else {
			
			intScreenID = Integer.parseInt((String) objSession
					.getAttribute("screenid"));
		}
		LogUtil.logMessage("strRoleID......" + strRoleID);
		LogUtil.logMessage("strScreenID......" + intScreenID);
		objArrayList.add(strRoleID);
		objArrayList.add(new Integer(intScreenID));
		try {
			LoginBI lLoginActionBo = ServiceFactory.getLoginBO();
			LogUtil.logMessage("lLoginActionBo:........." + lLoginActionBo);
			isValidUser = lLoginActionBo.screenAccess(objArrayList);
			
			LogUtil.logMessage("Exit EMDAction::screenAccess method......");
			return isValidUser;
			
		} catch (Exception objExcep) {
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExcep.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objErrorInfo);
			throw new ApplicationException(objExcep);
		}
		
	}
	
}
