/*
 * Created on May 5, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
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
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.form.SpecMaintenance.AppendixForm;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SubSectionVO;

/**
 * @author PS57222
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ShowAppendixImageAction extends EMDAction {
	
	/***************************************************************************
	 * This method is used for loading the Appendix Images at Pop-Up Screen
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
	
	public ActionForward showImage(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into ShowAppendixImageAction:showImage");
		String strForwardKey = ApplicationConstants.SUCCESS;
		AppendixForm objAppendixForm = (AppendixForm) objActionForm;
		
		if (objHttpServletRequest.getParameter("imageSeqNo") != null) {
			objAppendixForm.setImageSeqNo(Integer
					.parseInt(objHttpServletRequest.getParameter("imageSeqNo")
							.toString()));
			LogUtil.logMessage("imageSeqNo:"
					+ objHttpServletRequest.getParameter("imageSeqNo"));
		}
		
		if (objHttpServletRequest.getParameter("imageName") != null) {
			objAppendixForm.setImageName(objHttpServletRequest
					.getParameter("imageName"));
			LogUtil.logMessage("secSeq:"
					+ objHttpServletRequest.getParameter("imageName"));
		}
		
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***************************************************************************
	 * This method is for loading Sections based on the Order selected
	 * 
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
	
	public ActionForward initLoad(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException, ApplicationException {
		
		LogUtil.logMessage("Inside the ShowAppendixImageAction: InitLoad");
		String strForwardKey = ApplicationConstants.SELECT_CLAUSE;
		String strUserID = "";
		int intOrderKey = 0;
		String strOrderNo = "";
		
		AppendixForm objAppendixForm = (AppendixForm) objActionForm;
		
		ArrayList arlSectionList;
		
		if (objHttpServletRequest.getParameter("orderkey") != null) {
			intOrderKey = Integer.parseInt(objHttpServletRequest
					.getParameter("orderkey"));
			
		}
		if (objHttpServletRequest.getParameter("OrderNo") != null) {
			strOrderNo = objHttpServletRequest.getParameter("OrderNo");
			
		}
		if (objHttpServletRequest.getParameter("DivID") != null) {
			objAppendixForm.setClauseDes(objHttpServletRequest
					.getParameter("DivID"));
			
		}
		
		objAppendixForm.setHdnOrderKey(intOrderKey);
		objAppendixForm.setOrderNo(strOrderNo);
		LogUtil.logMessage("OrderKey in ShowAppendix:"
				+ objAppendixForm.getHdnOrderKey());
		LogUtil.logMessage("OrderNo in ShowAppendix:"
				+ objAppendixForm.getOrderNo());
		
		ClauseVO objClauseVO = new ClauseVO();
		SectionVO objSectionVO = new SectionVO();
		try {
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			objSectionVO.setUserID(objLoginVo.getUserID());
			
			objSectionVO.setOrderKey(objAppendixForm.getHdnOrderKey());
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			/**
			 * ****** This part of code is used to load the sections in
			 * orderlevel to populate the dropdown in orderClausedecpopup
			 * screen*****
			 */
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			LogUtil
			.logMessage("Size of SectionList in ShowAppendixImageAction: InitLoad:"
					+ arlSectionList.size());
			if (arlSectionList != null) {
				objAppendixForm.setSectionList(arlSectionList);
			}
			
			if (arlSectionList.size() != 0) {
				
				objAppendixForm.setSectionList(arlSectionList);
			}
			objAppendixForm.setHdnOrderKey(objAppendixForm.getHdnOrderKey());
			objAppendixForm.setOrderNo(objAppendixForm.getOrderNo());
		} catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
			
		}
		
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***************************************************************************
	 * This method is for loading SubSections based on Sections selected
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
		String strUserID = "";
		LogUtil
		.logMessage("Inside the ShowAppendixImageAction:SubSectionLoad ");
		String strForwardKey = ApplicationConstants.SELECT_CLAUSE;
		AppendixForm objAppendixForm = (AppendixForm) objActionForm;
		
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			// Getting User from the session
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			SectionVO objSectionVO = new SectionVO();
			
			objAppendixForm.setModelName(objAppendixForm.getModelName());
			LogUtil.logMessage("OrderKey in SubSection load"
					+ objAppendixForm.getHdnOrderKey());
			LogUtil.logMessage("SectionSeqNo in SubSection load"
					+ objAppendixForm.getSectionSeqNo());
			
			objSectionVO.setUserID(strUserID);
			objSectionVO.setSectionSeqNo(objAppendixForm.getSectionSeqNo());
			objSectionVO.setOrderKey(objAppendixForm.getHdnOrderKey());
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			arlSubSectionList = objOrderSectionBI
			.fetchOrdSubSections(objSectionVO);
			LogUtil
			.logMessage("Size of SectionList in ShowAppendixImageAction:SubSectionLoad:"
					+ arlSubSectionList.size());
			
			if (arlSubSectionList.size() != 0) {
				objAppendixForm.setSubSectionList(arlSubSectionList);
			}
			
			objOrderSectionBI = ServiceFactory.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			LogUtil
			.logMessage("Size of SectionList in ShowAppendixImageAction:SubSectionLoad:"
					+ arlSectionList.size());
			if (arlSectionList != null) {
				objAppendixForm.setSectionList(arlSectionList);
			}
			
			objAppendixForm.setHdnOrderKey(objAppendixForm.getHdnOrderKey());
			
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
	 * This method is for loading Clauses based on Sections and subsections
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
	
	public ActionForward loadClauseDesc(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		ArrayList arlSectionList;
		ArrayList arlSubSectionList = new ArrayList();
		String strUserID = "";
		LogUtil
		.logMessage("Inside the ShowAppendixImageAction:loadClauseDesc ");
		String strForwardKey = ApplicationConstants.SELECT_CLAUSE;
		AppendixForm objAppendixForm = (AppendixForm) objActionForm;
		ArrayList arlClauseDetail = new ArrayList();
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			SectionVO objSectionVO = new SectionVO();
			SubSectionVO objSubSectionVO = null;
			
			objSectionVO.setUserID(strUserID);
			objSectionVO.setSectionSeqNo(objAppendixForm.getSectionSeqNo());
			objSectionVO.setSubSecSeqNo(objAppendixForm.getSubSecSeqNo());
			objSectionVO.setOrderKey(objAppendixForm.getHdnOrderKey());
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			OrderSectionPopUpBI objOrderSectionPopUpBO = ServiceFactory
			.getOrderSectionPopUpBO();
			arlClauseDetail = objOrderSectionPopUpBO.fetchClauses(objSectionVO);
			
			if (arlClauseDetail != null) {
				objAppendixForm.setClauseDetail(arlClauseDetail);
				
			}
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			arlSubSectionList = objOrderSectionBI
			.fetchOrdSubSections(objSectionVO);
			LogUtil
			.logMessage("Size of SectionList in ShowAppendixImageAction:loadClauseDesc:"
					+ arlSubSectionList.size());
			
			if (arlSubSectionList.size() != 0) {
				objAppendixForm.setSubSectionList(arlSubSectionList);
			}
			
			objOrderSectionBI = ServiceFactory.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			LogUtil
			.logMessage("Size of SectionList in ShowAppendixImageAction:loadClauseDesc:"
					+ arlSectionList.size());
			if (arlSectionList != null) {
				objAppendixForm.setSectionList(arlSectionList);
			}
			
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
	 * This method is for loading the the Clauses for the subsection seqno
	 * passed
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
	public ActionForward viewMapping(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		LogUtil.logMessage("Inside the showAppendixImageAction:viewMapping");
		ArrayList arlClauseList = new ArrayList();
		ArrayList arlSubSecDetails = new ArrayList();
		String strForwardKey = ApplicationConstants.VIEW_MAP;
		String strUserID = "";
		String strSubSecName = "";
		String strSubSecCode = "";
		int intSubSecSeqNo = 0;
		int intsecSeqNo = 0;
		int intClauseSeqNo = 0;
		int intVersionNo = 0;
		int intOrderkey = 0;
		String strSubSecnameDisplay = "";
		
		try {
			
			if (objHttpServletRequest.getParameter("orderKey") != null) {
				intOrderkey = Integer.parseInt(objHttpServletRequest
						.getParameter("orderKey"));
			}
			
			if (objHttpServletRequest
					.getParameter(ApplicationConstants.SUB_SEC_SEQ_NO) != null) {
				intSubSecSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter(ApplicationConstants.SUB_SEC_SEQ_NO));
			}
			
			LogUtil.logMessage("Sub Sec Seq No is *****" + intSubSecSeqNo);
			SubSectionVO objSubSectionVO = new SubSectionVO();
			
			if (objHttpServletRequest
					.getParameter(ApplicationConstants.SEC_SEQ_NO) != null) {
				intsecSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter(ApplicationConstants.SEC_SEQ_NO));
			}
			LogUtil.logMessage("Sec Seq No is *****" + intsecSeqNo);
			
			if (objHttpServletRequest
					.getParameter(ApplicationConstants.CLAUSE_SEQ_NO) != null) {
				intClauseSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter(ApplicationConstants.CLAUSE_SEQ_NO));
			}
			LogUtil.logMessage("Clause Seq No is *****" + intClauseSeqNo);
			
			if (objHttpServletRequest
					.getParameter(ApplicationConstants.VERSION_NO) != null) {
				intVersionNo = Integer.parseInt(objHttpServletRequest
						.getParameter(ApplicationConstants.VERSION_NO));
			}
			LogUtil.logMessage("Version No is *****" + intVersionNo);
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			
			ClauseVO objClauseVO = new ClauseVO();
			SectionVO objSectionVO = new SectionVO();
			AppendixForm objAppendixForm = (AppendixForm) objActionForm;
			
			objSubSectionVO.setUserID(strUserID);
			objSubSectionVO.setSubSecSeqNo(intSubSecSeqNo);
			objSubSectionVO.setOrderKey(intOrderkey);
			objSubSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			OrderSectionBI objOrderSectionBO = ServiceFactory
			.getOrderSectionBO();
			arlSubSecDetails = objOrderSectionBO
			.fetchSubSectionDetails(objSubSectionVO);
			if (arlSubSecDetails.size() != 0) {
				
				strSubSecName = objSubSectionVO.getSubSecName();
				strSubSecCode = objSubSectionVO.getSubSecCode();
				strSubSecnameDisplay = strSubSecCode + "." + strSubSecName;
			}
			
			objAppendixForm.setHdnOrderKey(intOrderkey);
			objAppendixForm.setSubSectionName(strSubSecnameDisplay);
			objAppendixForm.setClauseSeqNo(intClauseSeqNo);
			objAppendixForm.setVersionNo(intVersionNo);
			
			objSectionVO.setSubSecSeqNo(intSubSecSeqNo);
			objSectionVO.setOrderKey(intOrderkey);
			objSectionVO.setSectionSeqNo(intsecSeqNo);
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSectionVO.setUserID(strUserID);
			
			OrderSectionPopUpBI objOrderSectionPopUpBO = ServiceFactory
			.getOrderSectionPopUpBO();
			arlClauseList = objOrderSectionPopUpBO.fetchClauses(objSectionVO);
			
			if (arlClauseList != null) {
				objAppendixForm.setClauseDetail(arlClauseList);
			}
			
		} catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
			
		}
		
		return objActionMapping.findForward(strForwardKey);
	}
	
}