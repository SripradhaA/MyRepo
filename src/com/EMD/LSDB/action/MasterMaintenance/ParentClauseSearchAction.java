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
import com.EMD.LSDB.bo.serviceinterface.ViewSpecByModelBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.form.MasterMaintenance.ParentClauseForm;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.SubSectionVO;

public class ParentClauseSearchAction extends EMDAction {
	
	/***********************************************************************************************
	 * This method is for loading Clause details based on SubSections
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
	
	public ActionForward ParentClauseOpen(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		LogUtil
		.logMessage("Inside the ParentClauseSearchAction:ParentClauseOpen");
		ArrayList arlParentList = new ArrayList();
		String strForwardKey = ApplicationConstants.SUCCESS;
		String strUserID = "";
		int intModelSeqNo = 0;
		int intSectionSeqNo = 0;
		int intSubSectionSeqNo = 0;
		String strSelectedSection = "";
		String strSelectedModel = "";
		String strSelectedSubSection = "";
		//Added for LSDB_CR_46
		String strSpecType = "";
		
		if (objHttpServletRequest.getParameter(ApplicationConstants.SPEC_TYPE) != null) {
			
			strSpecType = objHttpServletRequest
			.getParameter(ApplicationConstants.SPEC_TYPE);
		}
		
		/*
		 * ModelSeqNo,SectionSeqNo And SubSectionSeqNo are getting as a request parameters
		 * to generate the clause numbers for the selected subsection in parent clause pop-up screen.
		 * This action calls the ViewSpecByModel DAO in order to reuse the clause generation logic at Model Level.
		 * Changes made as per the requirement of LSDB_CR-50
		 * Added on 26-June-08
		 * Added by ps57222
		 */
		
		if (objHttpServletRequest
				.getParameter(ApplicationConstants.SELECTED_MODEL_NAME) != null) {
			strSelectedModel = objHttpServletRequest
			.getParameter(ApplicationConstants.SELECTED_MODEL_NAME);
		}
		
		if (objHttpServletRequest
				.getParameter(ApplicationConstants.SELECTED_SECTION_NAME) != null) {
			strSelectedSection = objHttpServletRequest
			.getParameter(ApplicationConstants.SELECTED_SECTION_NAME);
		}
		
		if (objHttpServletRequest
				.getParameter(ApplicationConstants.SELECTED_SUB_SECTION_NAME) != null) {
			strSelectedSubSection = objHttpServletRequest
			.getParameter(ApplicationConstants.SELECTED_SUB_SECTION_NAME);
		}
		
		if (objHttpServletRequest
				.getParameter(ApplicationConstants.SELECTED_SUB_SECTION_ID) != null) {
			intSubSectionSeqNo = Integer
			.parseInt(objHttpServletRequest
					.getParameter(ApplicationConstants.SELECTED_SUB_SECTION_ID));
		}
		if (objHttpServletRequest.getParameter("selectedSectionID") != null) {
			intSectionSeqNo = Integer.parseInt(objHttpServletRequest
					.getParameter("selectedSectionID"));
		}
		if (objHttpServletRequest.getParameter("selectedModelID") != null) {
			intModelSeqNo = Integer.parseInt(objHttpServletRequest
					.getParameter("selectedModelID"));
		}
		
		SubSectionVO objSubSectionVO = new SubSectionVO();
		try {
			ParentClauseForm objParentClauseForm = (ParentClauseForm) objActionForm;
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			
			objSubSectionVO.setModelSeqNo(intModelSeqNo);
			objSubSectionVO.setUserID(strUserID);
			objSubSectionVO.setSecSeqNo(intSectionSeqNo);
			objSubSectionVO.setSubSecSeqNo(intSubSectionSeqNo);
			ViewSpecByModelBI objViewSpecByModelBO = ServiceFactory
			.getViewSpecByModelBO();
			arlParentList = (objViewSpecByModelBO
					.viewMasterSpecByModel(objSubSectionVO));
			
			if (arlParentList.size() != 0) {
				strForwardKey = ApplicationConstants.PARENT_CLAUSE;
				//Added for LSDB_CR_46
				objParentClauseForm.setSpecType(strSpecType);
				objParentClauseForm.setComponentVO(arlParentList);
				objParentClauseForm.setModelName(strSelectedModel);
				objParentClauseForm.setSectionName(strSelectedSection);
				objParentClauseForm.setSubSectionName(strSelectedSubSection);
				return objActionMapping.findForward(strForwardKey);
			} else {
				strForwardKey = ApplicationConstants.PARENT_CLAUSE;
				//Added for LSDB_CR_46
				objParentClauseForm.setSpecType(strSpecType);
				objParentClauseForm.setModelName(strSelectedModel);
				objParentClauseForm.setSectionName(strSelectedSection);
				objParentClauseForm.setSubSectionName(strSelectedSubSection);
				objParentClauseForm
				.setMessageID(ApplicationConstants.NO_RECORDS_FOUND);
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
	
}