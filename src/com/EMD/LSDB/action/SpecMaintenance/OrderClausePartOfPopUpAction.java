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
import com.EMD.LSDB.bo.serviceinterface.OrderSectionBI;
import com.EMD.LSDB.bo.serviceinterface.OrderSectionPopUpBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.constant.DatabaseConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.form.SpecMaintenance.OrderClausePartOfPopUpForm;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SubSectionVO;

public class OrderClausePartOfPopUpAction extends EMDAction {
	
	public ActionForward initLoad(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException, ApplicationException {
		ArrayList arlSectionList;
		LogUtil.logMessage("Inside the OrderClausePartOfPopUpAction:initLoad ");
		String strForwardKey = ApplicationConstants.SUCCESS;
		String strUserID = "";
		OrderClausePartOfPopUpForm objOrderClausePartOfPopUpForm = (OrderClausePartOfPopUpForm) objActionForm;
		int intIndex = 0;
		int intModelSeqNo = Integer.parseInt(objHttpServletRequest
				.getParameter(ApplicationConstants.MODEL_SEQ_NO));
		int intOrderKey = Integer.parseInt(objHttpServletRequest
				.getParameter(ApplicationConstants.ORDER_KEY));
		String strOrderNum = objHttpServletRequest
		.getParameter(ApplicationConstants.ORDER_NUMBER);
		ClauseVO objClauseVO = new ClauseVO();
		SectionVO objSecMaintVO = new SectionVO();
		if ((String) objHttpServletRequest
				.getParameter(ApplicationConstants.TEXT_BOX_INDEX) != null) {
			intIndex = Integer.parseInt(objHttpServletRequest
					.getParameter(ApplicationConstants.TEXT_BOX_INDEX));
		}
		objOrderClausePartOfPopUpForm.setOrderKey(intOrderKey);
		objOrderClausePartOfPopUpForm.setOrderNo(strOrderNum);
		objOrderClausePartOfPopUpForm.setModelSeqNo(intModelSeqNo);
		objOrderClausePartOfPopUpForm.setTextBoxIndex(intIndex);
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			// Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			
			objClauseVO.setModelSeqNo(intModelSeqNo);
			objSecMaintVO.setModelSeqNo(intModelSeqNo);
			objSecMaintVO.setOrderKey(intOrderKey);
			objSecMaintVO.setUserID(objLoginVo.getUserID());
			//DataLocType Updated for CR-121
			if(objHttpServletRequest.getParameter("dataLocType")!= null){
				//LogUtil.logMessage("dataLocType "+objHttpServletRequest.getParameter("dataLocType"));
				objSecMaintVO.setDataLocationType(objHttpServletRequest.getParameter("dataLocType"));
			}else{
				objSecMaintVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			}
			OrderSectionBI objOrderSectionBO = ServiceFactory
			.getOrderSectionBO();
			arlSectionList = objOrderSectionBO.fetchOrdSections(objSecMaintVO);
			if (arlSectionList.size() != 0) {
				objOrderClausePartOfPopUpForm.setSectionList(arlSectionList);
				objOrderClausePartOfPopUpForm.setOrderKey(intOrderKey);
				objOrderClausePartOfPopUpForm.setOrderNo(strOrderNum);
				objOrderClausePartOfPopUpForm.setModelSeqNo(intModelSeqNo);
				objOrderClausePartOfPopUpForm.setTextBoxIndex(intIndex);
			} else {
				
				objOrderClausePartOfPopUpForm.setOrderKey(intOrderKey);
				objOrderClausePartOfPopUpForm.setOrderNo(strOrderNum);
				objOrderClausePartOfPopUpForm.setModelSeqNo(intModelSeqNo);
				objOrderClausePartOfPopUpForm.setTextBoxIndex(intIndex);
			}
			
		} catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
			
		}
		
		return objActionMapping.findForward(strForwardKey);
	}
	
	public ActionForward SubSectionLoad(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		
		ArrayList arlSectionList;
		ArrayList arlSubSectionList = new ArrayList();
		LogUtil
		.logMessage("Inside the OrderClausePartOfPopUpAction:SubSectionLoad ");
		String strForwardKey = ApplicationConstants.SUCCESS;
		String strUserID = "";
		OrderClausePartOfPopUpForm objOrderClausePartOfPopUpForm = (OrderClausePartOfPopUpForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			// Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			
			SectionVO objSectionVO = new SectionVO();
			ClauseVO objClauseVO = new ClauseVO();
			objSectionVO.setModelSeqNo(objOrderClausePartOfPopUpForm
					.getModelSeqNo());
			objSectionVO.setOrderKey(objOrderClausePartOfPopUpForm
					.getOrderKey());
			objSectionVO.setUserID(objLoginVo.getUserID());
			//DataLocType Updated for CR-121
			if(objHttpServletRequest.getParameter("dataLocType")!= null){
				//LogUtil.logMessage("dataLocType "+objHttpServletRequest.getParameter("dataLocType"));
				objSectionVO.setDataLocationType(objHttpServletRequest.getParameter("dataLocType"));
			}else{
				objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			}
			
			objSectionVO.setSectionSeqNo(objOrderClausePartOfPopUpForm
					.getSectionSeqNo());
			OrderSectionBI objOrderSectionBO = ServiceFactory
			.getOrderSectionBO();
			arlSubSectionList = objOrderSectionBO
			.fetchSectionDetails(objSectionVO);
			LogUtil.logMessage("arlSubSectionList.size():SubSectionLoad"+arlSubSectionList.size());
			arlSectionList = new ArrayList();
			objClauseVO.setOrderNo(objOrderClausePartOfPopUpForm.getOrderNo());
			
			if (arlSubSectionList.size() != 0) {
				objOrderClausePartOfPopUpForm
				.setSubSectionList(arlSubSectionList);
			} else {
				objOrderClausePartOfPopUpForm.setOrderNo(objClauseVO
						.getOrderNo());
				
			}
			objSectionVO.setModelSeqNo(objOrderClausePartOfPopUpForm
					.getModelSeqNo());
			objSectionVO.setOrderKey(objOrderClausePartOfPopUpForm
					.getOrderKey());
			objSectionVO.setUserID(objLoginVo.getUserID());
			
			arlSectionList = objOrderSectionBO.fetchOrdSections(objSectionVO);
			objOrderClausePartOfPopUpForm.setSectionList(arlSectionList);
			objOrderClausePartOfPopUpForm.setOrderNo(objClauseVO.getOrderNo());
			return objActionMapping.findForward(strForwardKey);
			
		}
		
		catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
			
		}
		
		return objActionMapping.findForward(strForwardKey);
	}
	
	public ActionForward fetchClause(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		LogUtil
		.logMessage("Inside the OrderClausePartofPopUpAction:fetchClauses");
		ArrayList arlPartOfList = new ArrayList();
		ArrayList arlSectionList;
		ArrayList arlSubSectionList = new ArrayList();
		String strForwardKey = ApplicationConstants.SUCCESS;
		String strUserID = "";
		ClauseVO objClauseVO = new ClauseVO();
		SectionVO objSectionVO = new SectionVO();
		SubSectionVO objSubSecVO = new SubSectionVO();
		OrderClausePartOfPopUpForm objOrderClausePartOfPopUpForm = (OrderClausePartOfPopUpForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			objClauseVO.setOrderNo(objOrderClausePartOfPopUpForm.getOrderNo());
			
			objSectionVO.setOrderKey(objOrderClausePartOfPopUpForm
					.getOrderKey());
			objSectionVO.setSectionSeqNo(objOrderClausePartOfPopUpForm
					.getSectionSeqNo());
			//DataLocType Updated for CR-121
			if(objHttpServletRequest.getParameter("dataLocType")!= null){
				//LogUtil.logMessage("dataLocType "+objHttpServletRequest.getParameter("dataLocType"));
				objSectionVO.setDataLocationType(objHttpServletRequest.getParameter("dataLocType"));
			}else{
				objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			}
			
			objSectionVO.setModelSeqNo(objOrderClausePartOfPopUpForm
					.getModelSeqNo());
			objSectionVO.setOrderKey(objOrderClausePartOfPopUpForm
					.getOrderKey());
			objSectionVO.setUserID(objLoginVo.getUserID());
			
			
			OrderSectionBI objOrderSectionBO = ServiceFactory
			.getOrderSectionBO();
			arlSectionList = objOrderSectionBO.fetchOrdSections(objSectionVO);
			objOrderClausePartOfPopUpForm.setSectionList(arlSectionList);
			objSectionVO.setSectionSeqNo(objOrderClausePartOfPopUpForm
					.getSectionSeqNo());
			arlSubSectionList = objOrderSectionBO
			.fetchSectionDetails(objSectionVO);
			LogUtil.logMessage("arlSubSectionList.size():fetchClauses"+arlSubSectionList.size());
			objOrderClausePartOfPopUpForm.setSubSectionList(arlSubSectionList);
			
			objSectionVO.setSubSecSeqNo(objOrderClausePartOfPopUpForm
					.getSubSectionSeqNo());
			
			OrderSectionPopUpBI objOrderSectionPopUpBO = ServiceFactory
			.getOrderSectionPopUpBO();
			arlPartOfList = objOrderSectionPopUpBO.fetchClauses(objSectionVO);
			if (arlPartOfList.size() != 0) {
				objOrderClausePartOfPopUpForm.setClauseGroup(arlPartOfList);
				objOrderClausePartOfPopUpForm.setOrderNo(objClauseVO
						.getOrderNo());
				objClauseVO.setOrderKey(objOrderClausePartOfPopUpForm
						.getOrderKey());
				
			} else {
				
				objOrderClausePartOfPopUpForm
				.setMessageID(ApplicationConstants.NO_PART_OF_ORDER_SECTION);
				objOrderClausePartOfPopUpForm.setOrderNo(objClauseVO
						.getOrderNo());
				
			}
			
			
		} catch (Exception objExp) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
			
		}
		return objActionMapping.findForward(strForwardKey);
	}
}
