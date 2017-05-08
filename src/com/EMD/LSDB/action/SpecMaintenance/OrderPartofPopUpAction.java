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
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.form.SpecMaintenance.OrderSectionPopUpForm;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SubSectionVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This Action class has the business methods for the OrderPartOf
 *          PopUp
 ******************************************************************************/
public class OrderPartofPopUpAction extends EMDAction {
	
	/***************************************************************************
	 * This method is for loading the OrderPartOf PopUp
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws BusinessException
	 **************************************************************************/
	public ActionForward fetchClauses(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		LogUtil.logMessage("Inside the OrderPartofPopUpAction:fetchClauses");
		ArrayList arlPartOfList = new ArrayList();
		ArrayList arlSubSecDetails = new ArrayList();
		String strForwardKey = ApplicationConstants.SUCCESS;
		String strUserID = "";
		String strSubSecName = "";
		String strSubSecCode = "";
		int intSubSecSeqNo = Integer.parseInt(objHttpServletRequest
				.getParameter(ApplicationConstants.SUB_SEC_SEQ_NO));
		LogUtil.logMessage("Sub Sec Seq No is *****" + intSubSecSeqNo);
		SubSectionVO objSubSectionVO = new SubSectionVO();
		
		int intOrderKey = Integer.parseInt(objHttpServletRequest
				.getParameter(ApplicationConstants.ORDER_KEY));
		LogUtil.logMessage("Order Key is *****" + intOrderKey);
		
		int intsecSeqNo = Integer.parseInt(objHttpServletRequest
				.getParameter(ApplicationConstants.SEC_SEQ_NO));
		LogUtil.logMessage("Sec Seq No is *****" + intsecSeqNo);
		
		int intParentSecSeqNo = Integer.parseInt(objHttpServletRequest
				.getParameter(ApplicationConstants.PARENT_SEC_SEQ_NO));
		LogUtil.logMessage("Parent Sec Seq No is *****" + intParentSecSeqNo);
		
		int intClauseSeqNo = Integer.parseInt(objHttpServletRequest
				.getParameter(ApplicationConstants.CLAUSE_SEQ_NO));
		LogUtil.logMessage("Clause Seq No is *****" + intClauseSeqNo);
		
		int intVersionNo = Integer.parseInt(objHttpServletRequest
				.getParameter(ApplicationConstants.VERSION_NO));
		LogUtil.logMessage("Version No is *****" + intVersionNo);
		
		int intRevCode = Integer.parseInt(objHttpServletRequest
				.getParameter(ApplicationConstants.REVISION_CODE));
		LogUtil.logMessage("Revision Code is *****" + intRevCode);
		
		int partOfSeqNo = Integer.parseInt(objHttpServletRequest
				.getParameter("partOfClaSeqNo"));
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			
			ClauseVO objClauseVO = new ClauseVO();
			SectionVO objSectionVO = new SectionVO();
			OrderSectionPopUpForm objOrderSectionPopUpForm = (OrderSectionPopUpForm) objActionForm;
			
			objOrderSectionPopUpForm.setPartOfClauseSeqNo(partOfSeqNo);
			objOrderSectionPopUpForm
			.setPartOfClauseSeqNo(objOrderSectionPopUpForm
					.getPartOfClauseSeqNo());
			
			objSubSectionVO.setUserID(strUserID);
			objSubSectionVO.setSubSecSeqNo(intSubSecSeqNo);
			objSubSectionVO.setOrderKey(intOrderKey);
			objSubSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			OrderSectionBI objOrderSectionBO = ServiceFactory
			.getOrderSectionBO();
			arlSubSecDetails = objOrderSectionBO
			.fetchSubSectionDetails(objSubSectionVO);
			if (arlSubSecDetails.size() != 0) {
				
				strSubSecName = objSubSectionVO.getSubSecName();
				strSubSecCode = objSubSectionVO.getSubSecCode();
			}
			
			objOrderSectionPopUpForm.setOrderKey(intOrderKey);
			objOrderSectionPopUpForm.setSubSecName(strSubSecName);
			objOrderSectionPopUpForm.setSubSecCode(strSubSecCode);
			objOrderSectionPopUpForm.setSubSecSeqNo(intSubSecSeqNo);
			objOrderSectionPopUpForm.setParentClauseSeqNo(intClauseSeqNo);
			objOrderSectionPopUpForm.setSecSeqNo(intsecSeqNo);
			objOrderSectionPopUpForm.setVersionNo(intVersionNo);
			objOrderSectionPopUpForm.setRevCode(intRevCode);
			objOrderSectionPopUpForm.setParentSecSeqNo(intParentSecSeqNo);
			objOrderSectionPopUpForm
			.setPartOfClauseSeqNo(objOrderSectionPopUpForm
					.getPartOfClauseSeqNo());
			
			objClauseVO.setSubSectionName(objOrderSectionPopUpForm
					.getSubSecName());
			objClauseVO.setSubSectionSeqNo(intSubSecSeqNo);
			objClauseVO.setOrderKey(intOrderKey);
			
			objSectionVO.setSubSecSeqNo(intSubSecSeqNo);
			objSectionVO.setOrderKey(intOrderKey);
			objSectionVO.setSectionSeqNo(intsecSeqNo);
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSectionVO.setUserID(strUserID);
			
			OrderSectionPopUpBI objOrderSectionPopUpBO = ServiceFactory
			.getOrderSectionPopUpBO();
			arlPartOfList = objOrderSectionPopUpBO.fetchClauses(objSectionVO);
			
			if (arlPartOfList.size() != 0) {
				objOrderSectionPopUpForm.setClauseGroup(arlPartOfList);
				objOrderSectionPopUpForm.setOrderKey(intOrderKey);
				objOrderSectionPopUpForm.setSubSecName(strSubSecName);
				objOrderSectionPopUpForm.setSubSecCode(strSubSecCode);
				objOrderSectionPopUpForm.setVersionNo(intVersionNo);
				objOrderSectionPopUpForm.setRevCode(intRevCode);
				objOrderSectionPopUpForm.setSubSecSeqNo(intSubSecSeqNo);
				objOrderSectionPopUpForm.setParentClauseSeqNo(intClauseSeqNo);
				objOrderSectionPopUpForm.setSecSeqNo(intsecSeqNo);
				objOrderSectionPopUpForm
				.setPartOfClauseSeqNo(objOrderSectionPopUpForm
						.getPartOfClauseSeqNo());
				return objActionMapping.findForward(strForwardKey);
				
			} else {
				strForwardKey = ApplicationConstants.ORDER_SECTION_POPUP;
				objOrderSectionPopUpForm.setOrderKey(intOrderKey);
				objOrderSectionPopUpForm.setSubSecName(strSubSecName);
				objOrderSectionPopUpForm.setSubSecCode(strSubSecCode);
				objOrderSectionPopUpForm.setSecSeqNo(intsecSeqNo);
				objOrderSectionPopUpForm.setVersionNo(intVersionNo);
				objOrderSectionPopUpForm.setRevCode(intRevCode);
				
				objOrderSectionPopUpForm
				.setMessageID(ApplicationConstants.NO_PART_OF_ORDER_SECTION);
				objOrderSectionPopUpForm
				.setPartOfClauseSeqNo(objOrderSectionPopUpForm
						.getPartOfClauseSeqNo());
				return objActionMapping.findForward(strForwardKey);
				
			}
			
		}
		
		catch (Exception objExp) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
			
		}
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***************************************************************************
	 * This method is for storing the OrderPartOf PopUp in database
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws BusinessException
	 **************************************************************************/
	public ActionForward savePartOf(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		LogUtil.logMessage("Inside the OrderPartofPopUpAction:savePartOf");
		String strForwardKey = ApplicationConstants.SUCCESS;
		String strUserID = "";
		OrderSectionPopUpForm objOrderSectionPopUpForm = (OrderSectionPopUpForm) objActionForm;
		int intSuccess, intRevCode = 0;
		String strSubSecCode, strSecCode = "";
		
		objOrderSectionPopUpForm.setPartOfClauseSeqNo(objOrderSectionPopUpForm
				.getPartOfClauseSeqNo());
		ArrayList arlPartOfList = new ArrayList();
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			// Getting User from the session
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			
			ClauseVO objClauseVO = new ClauseVO();
			SectionVO objSectionVO = new SectionVO();
			
			objClauseVO.setUserID(strUserID);
			objClauseVO.setParentClauseSeqNo(objOrderSectionPopUpForm
					.getParentClauseSeqNo());
			objClauseVO.setSectionSeqNo(objOrderSectionPopUpForm.getSecSeqNo());
			objClauseVO.setVersionNo(objOrderSectionPopUpForm.getVersionNo());
			objClauseVO.setSectionName(objOrderSectionPopUpForm.getSecName());
			
			strSecCode = objOrderSectionPopUpForm.getSecCode();
			intRevCode = objOrderSectionPopUpForm.getRevCode();
			
			LogUtil.logMessage("Version No:" + objClauseVO.getVersionNo());
			LogUtil.logMessage("SubSecCode():"
					+ objOrderSectionPopUpForm.getSubSecCode());
			
			objClauseVO.setSubSectionName(objOrderSectionPopUpForm
					.getSubSecName());
			objClauseVO.setOrderKey(objOrderSectionPopUpForm.getOrderKey());
			strSubSecCode = objOrderSectionPopUpForm.getSubSecCode();
			objClauseVO.setSubSectionSeqNo(objOrderSectionPopUpForm
					.getSubSecSeqNo());
			objClauseVO.setClauseSeqNo(objOrderSectionPopUpForm
					.getClauseSeqNo());
			objClauseVO.setPartOfClauseNo(objOrderSectionPopUpForm
					.getPartOfClauseSeqNo());
			
			objClauseVO.setClauseNum(objOrderSectionPopUpForm.getNumClause());
			LogUtil.logMessage("ClauseNum :" + objClauseVO.getClauseNum());
			objOrderSectionPopUpForm
			.setPartOfClauseSeqNo(objOrderSectionPopUpForm
					.getPartOfClauseSeqNo());
			
			OrderSectionPopUpBI objOrderSectionPopUpBo = ServiceFactory
			.getOrderSectionPopUpBO();
			intSuccess = objOrderSectionPopUpBo.savePartOf(objClauseVO);
			if (intSuccess == 0) {
				objOrderSectionPopUpForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				
				objSectionVO.setSubSecSeqNo(objClauseVO.getSubSectionSeqNo());
				objSectionVO.setOrderKey(objClauseVO.getOrderKey());
				objSectionVO.setSectionSeqNo(objClauseVO.getSectionSeqNo());
				objSectionVO
				.setDataLocationType(DatabaseConstants.DATALOCATION);
				objSectionVO.setUserID(strUserID);
				
				OrderSectionPopUpBI objOrderSectionPopUpBO = ServiceFactory
				.getOrderSectionPopUpBO();
				arlPartOfList = objOrderSectionPopUpBO
				.fetchClauses(objSectionVO);
				
				objOrderSectionPopUpForm.setClauseGroup(arlPartOfList);
				objOrderSectionPopUpForm.setOrderKey(objClauseVO.getOrderKey());
				objOrderSectionPopUpForm.setSubSecName(objClauseVO
						.getSubSectionName());
				objOrderSectionPopUpForm.setSubSecCode(strSubSecCode);
				objOrderSectionPopUpForm.setSubSecSeqNo(objClauseVO
						.getSubSectionSeqNo());
				objOrderSectionPopUpForm.setVersionNo(objClauseVO
						.getVersionNo());
				objOrderSectionPopUpForm.setParentClauseSeqNo(objClauseVO
						.getParentClauseSeqNo());
				objOrderSectionPopUpForm.setSecSeqNo(objClauseVO
						.getSectionSeqNo());
				objOrderSectionPopUpForm.setSecName(objClauseVO
						.getSectionName());
				objOrderSectionPopUpForm.setSecCode(strSecCode);
				objOrderSectionPopUpForm.setRevCode(intRevCode);
				objOrderSectionPopUpForm
				.setPartOfClauseSeqNo(objOrderSectionPopUpForm
						.getClauseSeqNo());
				// return objActionMapping.findForward(strForwardKey);
				
			}
			
			else {
				// objOrderSectionPopUpForm.setMessageID(ApplicationConstants.ORDER_SECTION_POPUP_FAILURE);
				
				objOrderSectionPopUpForm.setMessageID("" + intSuccess);
				
				objSectionVO.setSubSecSeqNo(objClauseVO.getSubSectionSeqNo());
				objSectionVO.setOrderKey(objClauseVO.getOrderKey());
				objSectionVO.setSectionSeqNo(objClauseVO.getSectionSeqNo());
				objSectionVO
				.setDataLocationType(DatabaseConstants.DATALOCATION);
				objSectionVO.setUserID(strUserID);
				
				OrderSectionPopUpBI objOrderSectionPopUpBO = ServiceFactory
				.getOrderSectionPopUpBO();
				arlPartOfList = objOrderSectionPopUpBO
				.fetchClauses(objSectionVO);
				
				objOrderSectionPopUpForm.setClauseGroup(arlPartOfList);
				objOrderSectionPopUpForm.setOrderKey(objClauseVO.getOrderKey());
				objOrderSectionPopUpForm.setSubSecName(objClauseVO
						.getSubSectionName());
				objOrderSectionPopUpForm.setSubSecCode(strSubSecCode);
				objOrderSectionPopUpForm.setSubSecSeqNo(objClauseVO
						.getSubSectionSeqNo());
				objOrderSectionPopUpForm.setVersionNo(objClauseVO
						.getVersionNo());
				objOrderSectionPopUpForm.setParentClauseSeqNo(objClauseVO
						.getParentClauseSeqNo());
				objOrderSectionPopUpForm.setSecSeqNo(objClauseVO
						.getSectionSeqNo());
				objOrderSectionPopUpForm.setSecName(objClauseVO
						.getSectionName());
				objOrderSectionPopUpForm.setSecCode(strSecCode);
				objOrderSectionPopUpForm.setRevCode(intRevCode);
				objOrderSectionPopUpForm
				.setPartOfClauseSeqNo(objOrderSectionPopUpForm
						.getPartOfClauseSeqNo());
				
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
