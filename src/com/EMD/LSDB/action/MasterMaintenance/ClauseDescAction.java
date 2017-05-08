/**
 * 
 */
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
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.form.MasterMaintenance.ClauseDescForm;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SubSectionVO;

/**
 * @author ps57222
 * 
 */
public class ClauseDescAction extends EMDAction {
	
	/***************************************************************************
	 * * * * This Method is used to populate the Section Dropdown on PageLoad
	 * Based on selected model
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
		LogUtil.logMessage("Enters into ClauseDescAction:initLoad");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList arlSectionList = new ArrayList();
		
		String strModelName = objHttpServletRequest.getParameter("ModelName");
		LogUtil.logMessage("ModelName in ClauseDescAction:initLoad:"
				+ strModelName);
		int intModelSeqNo = Integer.parseInt(objHttpServletRequest
				.getParameter("ModelSeqNo"));
		LogUtil.logMessage("ModelSeqNo in ClauseDescAction:initLoad:"
				+ intModelSeqNo);
		ClauseDescForm objClauseDescForm = (ClauseDescForm) objActionForm;
		objClauseDescForm.setModelName(strModelName);
		objClauseDescForm.setModelSeqNo(intModelSeqNo);
		
		SectionVO objSectionVO = new SectionVO();
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LogUtil.logMessage(objSession);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		LogUtil.logMessage(objLoginVo);
		objSectionVO.setUserID(objLoginVo.getUserID());
		LogUtil.logMessage("UserId in ClauseDescAction:initLoad:"
				+ objSectionVO.getUserID());
		objSectionVO.setModelSeqNo(objClauseDescForm.getModelSeqNo());
		LogUtil.logMessage("Model SequenceNo ClauseDescAction:initLoad"
				+ objClauseDescForm.getModelSeqNo());
		
		try {
			SectionBI objSectionBO = null;
			objSectionBO = ServiceFactory.getSectionBO();
			arlSectionList = objSectionBO.fetchSections(objSectionVO);
			LogUtil.logMessage("ResultList in ClauseDescAction:initLoad:"
					+ arlSectionList);
			if (arlSectionList != null) {
				objClauseDescForm.setSectionList(arlSectionList);
			}
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception Block in Action..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objErrorInfo.getMessage();
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***************************************************************************
	 * * * * This Method is used to populate the SubSection Dropdown Based on
	 * selected Section
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 * 
	 **************************************************************************/
	
	public ActionForward loadSubSection(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil.logMessage("Enters into ClauseDescAction:initLoad");
		ArrayList arlSubSecList = new ArrayList();
		ArrayList arlSectionList = new ArrayList();
		
		String strForwardKey = ApplicationConstants.SUCCESS;
		ClauseDescForm objClauseDescForm = (ClauseDescForm) objActionForm;
		try {
			SubSectionVO objSubSectionVO = new SubSectionVO();
			
			LogUtil.logMessage("SectionSeqNo in ClauseDescAction:initLoad"
					+ objClauseDescForm.getSectionSeqNo());
			objSubSectionVO.setSecSeqNo(objClauseDescForm.getSectionSeqNo());
			ModelSubSectionBI lSubSecMaintBO = ServiceFactory
			.getSubSecMaintBO();
			arlSubSecList = lSubSecMaintBO.fetchSubSections(objSubSectionVO);
			
			if (arlSubSecList != null) {
				objClauseDescForm.setSubSectionList(arlSubSecList);
			}
			SectionVO objSectionVO = new SectionVO();
			objSectionVO.setModelSeqNo(objClauseDescForm.getModelSeqNo());
			LogUtil.logMessage("Model SequenceNo ClauseDescAction:initLoad"
					+ objClauseDescForm.getModelSeqNo());
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			arlSectionList = objSectionBO.fetchSections(objSectionVO);
			LogUtil.logMessage("ResultList in ClauseDescAction:initLoad:"
					+ arlSectionList);
			if (arlSectionList != null) {
				objClauseDescForm.setSectionList(arlSectionList);
			}
			
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception Block in Action..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objErrorInfo.getMessage();
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		
		return objActionMapping.findForward(strForwardKey);
	}
	
}
