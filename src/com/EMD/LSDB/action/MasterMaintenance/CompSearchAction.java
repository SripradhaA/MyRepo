package com.EMD.LSDB.action.MasterMaintenance;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.EMD.LSDB.action.common.EMDAction;
import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.ModelCompBI;
import com.EMD.LSDB.bo.serviceinterface.ModelCompGroupBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.form.MasterMaintenance.CompSearchForm;
import com.EMD.LSDB.vo.common.CompGroupVO;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.LoginVO;

public class CompSearchAction extends EMDAction {
	/***********************************************************************************************
	 * This method is for loading the Add Component Screen
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws BusinessException
	 **********************************************************************************************/
	public ActionForward initLoad(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		
		LogUtil.logMessage("Inside CompSearchAction:initLoad");
		String strForwardKey = ApplicationConstants.SUCCESS;
		
		try {
			CompSearchForm objCompSearchForm = (CompSearchForm) objActionForm;
			int intmodelSeqNo = 0;
			intmodelSeqNo = Integer.parseInt(objHttpServletRequest
					.getParameter("selectedModelID"));
			objCompSearchForm.setModelSeqNo(intmodelSeqNo);
			objCompSearchForm.setModelSeqNo(objCompSearchForm.getModelSeqNo());
			//Added For CR_81 on 07-Jan-2010 by RR68151
			//Updated CompGroupTypeSeqNo instead of -1 to 0 for CR-114
			objCompSearchForm.setCompGroupTypeSeqNo(0);
			//Added For CR_81 on 07-Jan-2010 by RR68151 - Ends here
			//Added for CR-114
			fetchComps(objActionMapping,objActionForm,objHttpServletRequest,objHttpServletResponse);
		}
		
		catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***********************************************************************************************
	 * This method is for the action to be performed on selecting the Components
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException,BusinessException
	 **********************************************************************************************/
	public ActionForward fetchComps(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		LogUtil.logMessage("In Action Class Comp Search Action :fetchComps");
		CompSearchForm objCompSearchForm = (CompSearchForm) objActionForm;
		String strForwardKey = ApplicationConstants.SUCCESS;
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		
		objCompSearchForm.setModelSeqNo(objCompSearchForm.getModelSeqNo());
		
		try {
			ArrayList arlComp = new ArrayList();
			ComponentVO objCompVO = new ComponentVO();
			objCompVO.setUserID(objLoginVo.getUserID());
			
			objCompSearchForm.setModelSeqNo(objCompSearchForm.getModelSeqNo());
			/* Modified For CR_81 Locomotive and Power products Enhancements by RR68151
			if (objCompSearchForm.getCharacterisationFlag().equalsIgnoreCase(
			"All")) {
				objCompVO.setCharacterisationFlag(null);
				
			} else {
				objCompVO.setCharacterisationFlag(objCompSearchForm
						.getCharacterisationFlag());
			}*/
			
			objCompVO.setCompGrpTypeSeqNo(objCompSearchForm.getCompGroupTypeSeqNo());
			// Modified For CR_81 by RR68151 - Ends here
			
			objCompVO.setModelSeqNo(objCompSearchForm.getModelSeqNo());
			ModelCompBI objModelCompBO = ServiceFactory.getModelCompBO();
			arlComp = objModelCompBO.fetchModelComps(objCompVO);
			if (arlComp != null && arlComp.size() > 0) {
				objCompSearchForm.setCompList(arlComp);
				objCompSearchForm.setModelSeqNo(objCompSearchForm
						.getModelSeqNo());
			} else {
				objCompSearchForm
				.setMessageID(ApplicationConstants.NO_RECORDS_FOUND);
				objCompSearchForm.setCompList(null);
				objCompSearchForm.setModelSeqNo(objCompSearchForm
						.getModelSeqNo());
			}
		} catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
			
		}
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
	}
}
