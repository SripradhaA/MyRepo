package com.EMD.LSDB.action.SpecMaintenance;

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
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.form.SpecMaintenance.OrderCompSearchForm;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.LoginVO;

public class OrderCompSearchAction extends EMDAction {
	
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
		
		LogUtil.logMessage("Inside OrderCompSearchAction:initLoad");
		String strForwardKey = ApplicationConstants.SUCCESS;
		
		try {
			OrderCompSearchForm objOrderCompSearchForm = (OrderCompSearchForm) objActionForm;
			int intmodelSeqNo = 0;
			intmodelSeqNo = Integer.parseInt(objHttpServletRequest
					.getParameter("selectedModelID"));
			objOrderCompSearchForm.setModelSeqNo(intmodelSeqNo);
			objOrderCompSearchForm.setModelSeqNo(objOrderCompSearchForm
					.getModelSeqNo());
			//Added For CR_81 on 07-Jan-2010 by RR68151
			//Updated CompGroupTypeSeqNo instead of -1 to 0 for CR-114
			objOrderCompSearchForm.setCompGroupTypeSeqNo(0);
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
		LogUtil
		.logMessage("In Action Class Order Comp Search Action :fetchComps");
		OrderCompSearchForm objOrderCompSearchForm = (OrderCompSearchForm) objActionForm;
		String strForwardKey = ApplicationConstants.SUCCESS;
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			ArrayList arlComp = new ArrayList();
			ComponentVO objCompVO = new ComponentVO();
			objOrderCompSearchForm.setModelSeqNo(objOrderCompSearchForm
					.getModelSeqNo());
			/* Modified For CR_81 Locomotive and Power products Enhancements by RR68151
			objCompVO.setCharacterisationFlag(objOrderCompSearchForm
					.getCharacterisationFlag());
			if (objOrderCompSearchForm.getCharacterisationFlag()
					.equalsIgnoreCase("All")) {
				objCompVO.setCharacterisationFlag(null);
			}*/
			objCompVO.setCompGrpTypeSeqNo(objOrderCompSearchForm.getCompGroupTypeSeqNo());
			// Modified For CR_81 by RR68151 - Ends here
			objCompVO.setUserID(objLoginVo.getUserID());
			objCompVO.setModelSeqNo(objOrderCompSearchForm.getModelSeqNo());
			ModelCompBI objModelCompBO = ServiceFactory.getModelCompBO();
			arlComp = objModelCompBO.fetchModelComps(objCompVO);
			if (arlComp != null && arlComp.size() > 0) {
				objOrderCompSearchForm.setCompList(arlComp);
				objOrderCompSearchForm.setModelSeqNo(objOrderCompSearchForm
						.getModelSeqNo());
			} else {
				objOrderCompSearchForm
				.setMessageID(ApplicationConstants.NO_RECORDS_FOUND);
				objOrderCompSearchForm.setCompList(null);
				objOrderCompSearchForm.setModelSeqNo(objOrderCompSearchForm
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
