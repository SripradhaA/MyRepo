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
import com.EMD.LSDB.bo.serviceinterface.ModelSubSectionBI;
import com.EMD.LSDB.bo.serviceinterface.SectionBI;
import com.EMD.LSDB.bo.serviceinterface.ViewSpecByModelBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.form.MasterMaintenance.PartOfForm;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SubSectionVO;

public class PartOfAction extends EMDAction {
	
	/***************************************************************************
	 * This method is for loading details of Parts.
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException,BusinessException
	 **************************************************************************/
	public ActionForward insertPartOf(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException, ApplicationException {
		LogUtil.logMessage("Inside the PartOfAction: AddpartOf");
		String strForwardKey = ApplicationConstants.SUCCESS;
		String strSpecType = "";
		int intIndex = 0;
		String strSelectedModel = "";
		int intSelectedModelSeqNo = 0;
		// Modified for LSDB_CR_45 CRForm Change
		
		if (objHttpServletRequest.getParameter(ApplicationConstants.SPEC_TYPE) != null) {
			strSpecType = (String) objHttpServletRequest
			.getParameter(ApplicationConstants.SPEC_TYPE);
		}
		
		if (objHttpServletRequest
				.getParameter(ApplicationConstants.SELECTED_MODEL_NAME) != null) {
			strSelectedModel = objHttpServletRequest
			.getParameter(ApplicationConstants.SELECTED_MODEL_NAME);
		}
		
		ArrayList arlSectionList;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		
		if (objHttpServletRequest
				.getParameter(ApplicationConstants.SELECTED_MODEL_ID) != null) {
			intSelectedModelSeqNo = Integer.parseInt(objHttpServletRequest
					.getParameter(ApplicationConstants.SELECTED_MODEL_ID));
		}
		
		ClauseVO objClauseVO = new ClauseVO();
		SectionVO objSecMaintVO = new SectionVO();
		if ((String) objHttpServletRequest
				.getParameter(ApplicationConstants.TEXT_BOX_INDEX) != null) {
			intIndex = Integer.parseInt(objHttpServletRequest
					.getParameter(ApplicationConstants.TEXT_BOX_INDEX));
		}
		
		try {
			PartOfForm objPartOfForm = (PartOfForm) objActionForm;
			objSecMaintVO.setModelSeqNo(intSelectedModelSeqNo);
			objSecMaintVO.setUserID(objLoginVo.getUserID());
			// Getting User from the session
			
			objClauseVO.setModelName(strSelectedModel);
			objClauseVO.setModelSeqNo(intSelectedModelSeqNo);
			objSecMaintVO.setModelSeqNo(intSelectedModelSeqNo);
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			arlSectionList = objSectionBO.fetchSections(objSecMaintVO);
			if (arlSectionList.size() != 0) {
				objPartOfForm.setModelName(strSelectedModel);
				objPartOfForm.setSectionList(arlSectionList);
				objPartOfForm.setTextBoxIndex(intIndex);
				objPartOfForm.setModelSeqNo(intSelectedModelSeqNo);
			}
			objPartOfForm.setModelName(strSelectedModel);
			objPartOfForm.setSpecType(strSpecType);
			objPartOfForm.setHdnspecType(strSpecType);
			
		} catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
			
		}
		
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***************************************************************************
	 * This method is for loading SubSections based on Sections
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
	
	public ActionForward SubSectionLoad(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		ArrayList arlSectionList;
		ArrayList arlSubSectionList = new ArrayList();
		LogUtil.logMessage("Inside the PartOfAction:SubSectionLoad ");
		String strForwardKey = ApplicationConstants.SUCCESS;
		PartOfForm objPartOfForm = (PartOfForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		try {
			SubSectionVO objSubSecVO = new SubSectionVO();
			objSubSecVO.setModelSeqNo(objPartOfForm.getModelSeqNo());
			objSubSecVO.setSecSeqNo(objPartOfForm.getSectionSeqNo());
			objSubSecVO.setUserID(objLoginVo.getUserID());
			ModelSubSectionBI objModelSubSecBO = ServiceFactory
			.getSubSecMaintBO();
			arlSubSectionList = objModelSubSecBO.fetchSubSections(objSubSecVO);
			SectionVO objSectionVO = new SectionVO();
			arlSectionList = new ArrayList();
			
			if (arlSubSectionList.size() != 0) {
				objPartOfForm.setSubSectionList(arlSubSectionList);
			}
			
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			objSectionVO.setModelSeqNo(objPartOfForm.getModelSeqNo());
			objSectionVO.setUserID(objLoginVo.getUserID());
			arlSectionList = objSectionBO.fetchSections(objSectionVO);
			objPartOfForm.setSectionList(arlSectionList);
			objPartOfForm.setSpecType(objPartOfForm.getHdnspecType());
			
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
	
	/***************************************************************************
	 * This method is for loading Clauses based on SubSections
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
	
	public ActionForward loadClauses(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		ArrayList arlSectionList;
		ArrayList arlSubSectionList = new ArrayList();
		ArrayList arlParentList;
		LogUtil.logMessage("Inside the PartOfAction:loadClauses ");
		String strForwardKey = ApplicationConstants.SUCCESS;
		PartOfForm objPartOfForm = (PartOfForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		SectionVO objSectionVO = new SectionVO();
		arlSectionList = new ArrayList();
		try {
			SubSectionVO objSubSecVO = new SubSectionVO();
			objSubSecVO.setModelSeqNo(objPartOfForm.getModelSeqNo());
			objSubSecVO.setSecSeqNo(objPartOfForm.getSectionSeqNo());
			objSubSecVO.setUserID(objLoginVo.getUserID());
			objSubSecVO.setSubSecSeqNo(objPartOfForm.getSubSectionSeqNo());
			LogUtil.logMessage("SubSection SeqNO:"
					+ objSubSecVO.getSubSecSeqNo());
			ViewSpecByModelBI objViewSpecByModelBO = ServiceFactory
			.getViewSpecByModelBO();
			arlParentList = (objViewSpecByModelBO
					.viewMasterSpecByModel(objSubSecVO));
			
			if (arlParentList.size() != 0) {
				
				objPartOfForm.setClauseList(arlParentList);
				
			} else {
				
				objPartOfForm
				.setMessageID(ApplicationConstants.NO_RECORDS_FOUND);
				
			}
			
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			objSectionVO.setModelSeqNo(objPartOfForm.getModelSeqNo());
			objSectionVO.setUserID(objLoginVo.getUserID());
			arlSectionList = objSectionBO.fetchSections(objSectionVO);
			
			if (arlSectionList.size() != 0) {
				objPartOfForm.setSectionList(arlSectionList);
				
			}
			ModelSubSectionBI objModelSubSecBO = ServiceFactory
			.getSubSecMaintBO();
			arlSubSectionList = objModelSubSecBO.fetchSubSections(objSubSecVO);
			
			if (arlSubSectionList.size() != 0) {
				objPartOfForm.setSubSectionList(arlSubSectionList);
			}
			objPartOfForm.setSpecType(objPartOfForm.getHdnspecType());
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
	
}