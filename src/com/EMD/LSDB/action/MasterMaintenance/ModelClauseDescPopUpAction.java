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
import com.EMD.LSDB.bo.serviceinterface.ModelAssignEdlBI;
import com.EMD.LSDB.bo.serviceinterface.ModelSubSectionBI;
import com.EMD.LSDB.bo.serviceinterface.SectionBI;
import com.EMD.LSDB.bo.serviceinterface.ViewSpecByModelBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.form.MasterMaintenance.ModelAddClauseForm;
import com.EMD.LSDB.form.MasterMaintenance.ModelClauseDescPopUpForm;
import com.EMD.LSDB.form.SpecMaintenance.OrderSectionForm;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SubSectionVO;
/***************************************************************************
 * ------------------------------------------------------------------------------------------------------
 *     Date         version  create by          	comments                              Remarks 
 * 16/02/2010        1.0      SD41630    Added  for Model level, SpecificationType,    Added for CR_83
 *                                       Model, Section and Sub Section of values be
 *                                       maintain in the session and display in the
 *                                       screen where ever occurred      
 * 24/03/2010        1.1       SD41630    Added a method viewCharCluasebyModel
 *                                       for getting char clause desc  details		    Added for CR_85		
 *  04/05/2010       1.2       SD41630    Updated  method for SubSectionLoad            Added for CR_85
 *  04/05/2010       1.3       SD41630    Added new  methods as followes                 Added for CR_85
 *                                       initLoadPopUp and 	fetchCharCombntnEdlView										 	 
 * --------------------------------------------------------------------------------------------------------
 * **************************************************************************/

public class ModelClauseDescPopUpAction extends EMDAction {
	
	/***************************************************************************
	 * This method is for loading Sections based on Model Selected in Parent
	 * Window
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
		LogUtil.logMessage("Inside the ModelClauseDescPopUpAction: InitLoad");
		String strForwardKey = ApplicationConstants.SUCCESS;
		String strUserID = "";
		String strSpecType = "";
		String strSelectedModel = objHttpServletRequest
		.getParameter(ApplicationConstants.SELECTED_MODEL_NAME);
		ArrayList arlSectionList;
		int intSelectedModelSeqNo = Integer.parseInt(objHttpServletRequest
				.getParameter(ApplicationConstants.SELECTED_MODEL_ID));
		
		
		ClauseVO objClauseVO = new ClauseVO();
		SectionVO objSecMaintVO = new SectionVO();

			ModelClauseDescPopUpForm objModelClauseDescPopUpForm = (ModelClauseDescPopUpForm) objActionForm;
			objModelClauseDescPopUpForm.setModelSeqNo(intSelectedModelSeqNo);
			objSecMaintVO.setModelSeqNo(intSelectedModelSeqNo);
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			
			
//			CR_81 lines are started here
			ArrayList arlModelList = null;
		//	ArrayList arlSectionList = null;
			ArrayList arlSubSecList = null;
			int specTypeNo = -1;
			int modleSeqNo = -1;
			int sectionSeqNo = -1;
			int subSectionSeqNo = -1;
			String strModleSeqNo = null;
			String strSectionSeqNo = null;
			String strSubSectionSeqNo = null;
			
			strModleSeqNo = (String) objHttpServletRequest
			.getParameter(ApplicationConstants.SELECTED_MODEL_ID);
			
			
			strSectionSeqNo =(String) objSession.getAttribute("SECTION_SEQ_NO");
			strSubSectionSeqNo=(String) objSession.getAttribute("SUB_SECTION_SEQ_NO");
			
			
	
	try {
		if (strModleSeqNo != null) {

			objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
			intSelectedModelSeqNo = Integer.parseInt(strModleSeqNo);
			objModelClauseDescPopUpForm.setModelSeqNo(intSelectedModelSeqNo);

		} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {

			if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
				strModleSeqNo = (String) objSession
						.getAttribute("MODEL_SEQ_NO");
				intSelectedModelSeqNo = Integer.parseInt(strModleSeqNo);
				objModelClauseDescPopUpForm.setModelSeqNo(intSelectedModelSeqNo);
			}
		}

	if (strSectionSeqNo != null ) {

		objSession.setAttribute("SECTION_SEQ_NO", strSectionSeqNo);
		sectionSeqNo = Integer.parseInt(strSectionSeqNo);
				objModelClauseDescPopUpForm.setSectionSeqNo(sectionSeqNo);
	} else if (objSession.getAttribute("SECTION_SEQ_NO") != null) {
		
		if (strSectionSeqNo == null || "".equals(strSectionSeqNo)) {
			strSectionSeqNo = (String) objSession.getAttribute("SECTION_SEQ_NO");
			sectionSeqNo = -1;
			objModelClauseDescPopUpForm.setSectionSeqNo(sectionSeqNo);

		}
	}

	if (strSubSectionSeqNo != null ) {
		objSession.setAttribute("SUB_SECTION_SEQ_NO", strSubSectionSeqNo);
		subSectionSeqNo = Integer.parseInt(strSubSectionSeqNo);
		objModelClauseDescPopUpForm.setSubSecSeqNo(subSectionSeqNo);
			} else if (objSession.getAttribute("SUB_SECTION_SEQ_NO") != null) {
		if (strSubSectionSeqNo == null || "".equals(strSubSectionSeqNo)) {
			
			strSubSectionSeqNo = (String) objSession.getAttribute("SUB_SECTION_SEQ_NO");
			subSectionSeqNo = -1;
			objModelClauseDescPopUpForm.setSubSecSeqNo(subSectionSeqNo);
				}

	}
	if (objHttpServletRequest.getParameter("DivID") != null) {
		objModelClauseDescPopUpForm.setClauseDes(objHttpServletRequest
				.getParameter("DivID"));
		
	}
	sectionSeqNo = objModelClauseDescPopUpForm.getSectionSeqNo();
	subSectionSeqNo = objModelClauseDescPopUpForm.getSubSecSeqNo();

//	CR_81 lines are started here
			
			// Added for LSDB_CR_46 PM&I Change
			strSpecType = (String) objHttpServletRequest
			.getParameter(ApplicationConstants.SPEC_TYPE);
			
			// Getting User from the session
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			
			//Modified for CR_81 Locomotive and PowerProducts enhancements by RR68151
			if ((String) objHttpServletRequest
					.getParameter("ChrstcFlag") != null) {
				objModelClauseDescPopUpForm.setHdnClaChrstcFlag((String) objHttpServletRequest
				.getParameter("ChrstcFlag"));
			}
			LogUtil.logMessage("Charstc Flag " + (String) objHttpServletRequest
					.getParameter("ChrstcFlag"));
			//Modified for CR_81 Locomotive and PowerProducts enhancements by RR68151 - Ends here
				objClauseVO.setModelName(strSelectedModel);
			objClauseVO.setUserID(objLoginVo.getUserID());
			objClauseVO.setModelSeqNo(intSelectedModelSeqNo);
			objSecMaintVO.setModelSeqNo(intSelectedModelSeqNo);
			objSecMaintVO.setUserID(objLoginVo.getUserID());
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			arlSectionList = objSectionBO.fetchSections(objSecMaintVO);
			
			if (arlSectionList.size() != 0) {
				
				objModelClauseDescPopUpForm.setSectionList(arlSectionList);
			}
			// } else {
			// objModelClauseDescPopUpForm
			// .setMessageID(ApplicationConstants.NO_SECTION_MESSAGE_ID);
			// }
			// Added for LSDB_CR_46 PM&I change
			
			objModelClauseDescPopUpForm.setSpecType(strSpecType);
			objModelClauseDescPopUpForm.setHdnspecType(strSpecType);
			objModelClauseDescPopUpForm.setModelName(strSelectedModel);
			objModelClauseDescPopUpForm.setHdnModelName(strSelectedModel);
			objModelClauseDescPopUpForm.setModelSeqNo(intSelectedModelSeqNo);
			//CR_83 lines are started here 
				if(strModleSeqNo !=null)
			{
				intSelectedModelSeqNo = Integer.parseInt(strModleSeqNo);
			}
			if (intSelectedModelSeqNo != -1 && intSelectedModelSeqNo != 0) {
				SectionVO objSectionMaintVo = new SectionVO();
				
				objSectionMaintVo.setModelSeqNo(intSelectedModelSeqNo);
				objSectionMaintVo.setUserID(objLoginVo.getUserID());
				SectionBI objSectionMaintBO = ServiceFactory.getSectionBO();
				arlSectionList = objSectionMaintBO
						.fetchSections(objSectionMaintVo);
				if (arlSectionList != null)
					objModelClauseDescPopUpForm.setSectionList(arlSectionList);
				objModelClauseDescPopUpForm.setSectionSeqNo(sectionSeqNo);
					}

			if (sectionSeqNo != -1 && sectionSeqNo != 0) {
					SubSectionVO objSubSecMaintVO = new SubSectionVO();
				objSubSecMaintVO.setModelSeqNo(intSelectedModelSeqNo);
				objSubSecMaintVO.setSecSeqNo(sectionSeqNo);
				objSubSecMaintVO.setUserID(objLoginVo.getUserID());

				ModelSubSectionBI objSubSecMaintBO = ServiceFactory
						.getSubSecMaintBO();
				arlSubSecList = objSubSecMaintBO
						.fetchSubSections(objSubSecMaintVO);

				if (arlSubSecList != null && arlSubSecList.size() != 0) {

					objModelClauseDescPopUpForm.setSubSectionList(arlSubSecList);
					objModelClauseDescPopUpForm.setSubSecSeqNo(subSectionSeqNo);
					objSubSecMaintVO.setSubSecSeqNo(subSectionSeqNo);
					objSubSecMaintVO.setClaCharstcFlag(objModelClauseDescPopUpForm.
								getHdnClaChrstcFlag());

					LogUtil.logMessage("Charstc Flag " + objModelClauseDescPopUpForm.
							getHdnClaChrstcFlag());
					//Modified for CR_81 Locomotive and PowerProducts enhancements by RR68151 - Ends here		
					ViewSpecByModelBI objViewSpecByModelBO = ServiceFactory
					.getViewSpecByModelBO();
					ArrayList arlParentList = (objViewSpecByModelBO
							.viewMasterSpecByModel(objSubSecMaintVO));
					
					if (arlParentList.size() != 0) {
						objModelClauseDescPopUpForm.setComponentVO(arlParentList);
					} else {
						objModelClauseDescPopUpForm
						.setMessageID(ApplicationConstants.NO_PARENT_CLAUSE);						
					}
				}

				else {

					objModelClauseDescPopUpForm.setMethod("SubSections");

				}
			
			
			}
			//CR-83 lines are ends here
			LogUtil.logMessage("MOdel Seq Nio in form"
					+ objModelClauseDescPopUpForm.getModelName());
			
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
		String strUserID = "";
		LogUtil
		.logMessage("Inside theModelClauseDescPopUpAction:SubSectionLoad ");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ModelClauseDescPopUpForm objModelClauseDescPopUpForm = (ModelClauseDescPopUpForm) objActionForm;
		String strModelName = objModelClauseDescPopUpForm.getHdnModelName();
		//CR_81 lines are started here
		ArrayList arlModelList = null;
	    //ArrayList arlSectionList = null;
		ArrayList arlSubSecList = null;
		int specTypeNo = -1;
		int modleSeqNo = -1;
		int sectionSeqNo = -1;
		int subSectionSeqNo = -1;
		String strModleSeqNo = null;
		String strSectionSeqNo = null;
		String strSubSectionSeqNo = null;
		String popupFlag="";
		//CR_83 lines ends here
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			//Added For CR_85 to pass value for change the forwarkey
			if ((String) objHttpServletRequest.getParameter("popupFlag") != null) {
				popupFlag =(String)objHttpServletRequest
						.getParameter("popupFlag");
			}	
			if(popupFlag.equalsIgnoreCase("Y"))
				strForwardKey = ApplicationConstants.POPUP_SUCCESS;
//CR_83 started here
			strModleSeqNo=(String) objSession.getAttribute("MODEL_SEQ_NO");
			strSubSectionSeqNo=(String) objSession.getAttribute("SUB_SECTION_SEQ_NO");

		sectionSeqNo = objModelClauseDescPopUpForm.getSectionSeqNo();
		LogUtil.logMessage("value of SECTION_SEQ_NO" + sectionSeqNo);
			if(sectionSeqNo!=-1 && sectionSeqNo !=0)
			{
				objSession.setAttribute("SECTION_SEQ_NO", Integer.toString(sectionSeqNo));
			}
	
			// Getting User from the session
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			SubSectionVO objSubSecVO = new SubSectionVO();
			objSubSecVO.setUserID(strUserID);
			objSubSecVO.setModelSeqNo(objModelClauseDescPopUpForm
					.getModelSeqNo());
			objModelClauseDescPopUpForm.setModelName(strModelName);
			LogUtil.logMessage("Model Name  set is " + strModelName);
			objSubSecVO.setSecSeqNo(objModelClauseDescPopUpForm
					.getSectionSeqNo());
			LogUtil.logMessage("Section Seq No set is "
					+ objModelClauseDescPopUpForm.getSectionSeqNo());
			ModelSubSectionBI objModelSubSecBO = ServiceFactory
			.getSubSecMaintBO();
			arlSubSectionList = objModelSubSecBO.fetchSubSections(objSubSecVO);
			SectionVO objSectionVO = new SectionVO();
			objSectionVO.setUserID(strUserID);
			arlSectionList = new ArrayList();
			
			if (arlSubSectionList.size() != 0) {
				objModelClauseDescPopUpForm
				.setSubSectionList(arlSubSectionList);
			}
			
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			objSectionVO.setModelSeqNo(objModelClauseDescPopUpForm
					.getModelSeqNo());
			arlSectionList = objSectionBO.fetchSections(objSectionVO);
			objModelClauseDescPopUpForm.setSectionList(arlSectionList);
			objModelClauseDescPopUpForm.setSpecType(objModelClauseDescPopUpForm
					.getHdnspecType());
			objModelClauseDescPopUpForm.setModelName(strModelName);
			objModelClauseDescPopUpForm.setHdnModelName(strModelName);
			
//			CR_83 lines are started here 
			
			if (sectionSeqNo != -1 && sectionSeqNo != 0) {
				SubSectionVO objSubSecMaintVO = new SubSectionVO();
				objSubSecMaintVO.setModelSeqNo(modleSeqNo);
				objSubSecMaintVO.setSecSeqNo(sectionSeqNo);
				objSubSecMaintVO.setUserID(objLoginVo.getUserID());

				ModelSubSectionBI objSubSecMaintBO = ServiceFactory
						.getSubSecMaintBO();
				arlSubSecList = objSubSecMaintBO
						.fetchSubSections(objSubSecMaintVO);

				if (arlSubSecList != null && arlSubSecList.size() != 0) {
					objModelClauseDescPopUpForm.setSubSectionList(arlSubSecList);
					objModelClauseDescPopUpForm.setSubSecSeqNo(subSectionSeqNo);
				}
				else {
					objModelClauseDescPopUpForm.setMethod("SubSections");
				}
			}
			//CR-83 lines are ends here
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
	 * This method is for loading Clause Desc based on SubSections
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
	public ActionForward LoadClauseDesc(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		LogUtil
		.logMessage("Inside theModelClauseDescPopUpAction:LoadClauseDesc ");
		String strUserID = "";
		ArrayList arlParentList, arlSectionList, arlSubSectionList = new ArrayList();
		String strForwardKey = ApplicationConstants.SUCCESS;
		ModelClauseDescPopUpForm objModelClauseDescPopUpForm = (ModelClauseDescPopUpForm) objActionForm;
		String strModelName = objModelClauseDescPopUpForm.getHdnModelName();
		SectionVO objSectionVO = new SectionVO();
		SubSectionVO objSubSectionVO = new SubSectionVO();
		int subsectionSeqNo = -1;
		String popupFlag="";
		//Added for CR_92 starts here
		int intModelSeqNo = 0;
		int intSubSecSeqNo = 0;
		int intsecSeqNo = 0;
		int intClauseSeqNo = 0;
		int intVersionNo = 0;
		String selectedSpecType=null;
		String selectedModelName=null;
		String strSubSecName = "";
		String strSubSecCode = "";
		String strSubSecnameDisplay = "";
		ClauseVO objClauseVO = new ClauseVO();
		//Ends here
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
			if ((String) objHttpServletRequest.getParameter("popupFlag") != null) {
				popupFlag =(String)objHttpServletRequest
						.getParameter("popupFlag");
			}	
			if(popupFlag.equalsIgnoreCase("Y"))
				strForwardKey = ApplicationConstants.POPUP_SUCCESS;
			//Added for CR_92 starts here
			else if(popupFlag.equalsIgnoreCase("N"))
			{
				strForwardKey = ApplicationConstants.VIEW_MAP_CLAUSE;
				LogUtil.logMessage("Forward key "+ strForwardKey);
				if (objHttpServletRequest
						.getParameter(ApplicationConstants.MODEL_SEQ_NO) != null) {
					intModelSeqNo = Integer.parseInt(objHttpServletRequest
							.getParameter(ApplicationConstants.MODEL_SEQ_NO));
				}
				//Added for CR_92
				if (objHttpServletRequest
						.getParameter(ApplicationConstants.SELECTED_SPEC_TYPE) != null) {
					selectedSpecType = (String)(objHttpServletRequest
							.getParameter(ApplicationConstants.SELECTED_SPEC_TYPE));
				}
				LogUtil.logMessage("Model Seq No is *****" + selectedSpecType);
				if (objHttpServletRequest
						.getParameter(ApplicationConstants.SELECTED_MODEL_NAME) != null) {
					selectedModelName = (String)(objHttpServletRequest
							.getParameter(ApplicationConstants.SELECTED_MODEL_NAME));
				}
				LogUtil.logMessage("Model Seq No is *****" + selectedModelName);
				if (objHttpServletRequest
						.getParameter(ApplicationConstants.SUB_SEC_SEQ_NO) != null) {
					intSubSecSeqNo = Integer.parseInt(objHttpServletRequest
							.getParameter(ApplicationConstants.SUB_SEC_SEQ_NO));
				}
				LogUtil.logMessage("Sub Sec Seq No is *****" + intSubSecSeqNo);
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
				LogUtil.logMessage("***** Setting values into form *****");
				objModelClauseDescPopUpForm.setSubSecSeqNo(intSubSecSeqNo);
				objModelClauseDescPopUpForm.setSectionSeqNo(intsecSeqNo);
				objModelClauseDescPopUpForm.setModelSeqNo(intModelSeqNo);
				objClauseVO.setClauseSeqNo(intClauseSeqNo);
				LogUtil.logMessage("Clause Seq No in Clause VO is *****" + objClauseVO.getClauseSeqNo());
			}
			//Ends here
//CR_83 lines are started here
			subsectionSeqNo = objModelClauseDescPopUpForm.getSubSecSeqNo();
			if(subsectionSeqNo!=-1 && subsectionSeqNo !=0)
			{
				objSession.setAttribute("SUB_SECTION_SEQ_NO", Integer.toString(subsectionSeqNo));
			}
			//CR_83 lines are ends here	
			// Getting User from the session
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			
			/*
			 * 
			 * This action calls the ViewSpecByModel DAO in order to reuse the
			 * clause generation logic at Model Level. Changes made as per the
			 * requirement of LSDB_CR-50 Added on 26-June-08 Added by ps57222
			 */
			
			// SubSectionVO objSubSectionVO = new SubSectionVO();
			objSubSectionVO.setUserID(strUserID);
			objSubSectionVO.setModelSeqNo(objModelClauseDescPopUpForm
					.getModelSeqNo());
			objModelClauseDescPopUpForm.setModelName(strModelName);
			LogUtil.logMessage("Model Name  set is " + strModelName);
			objSubSectionVO.setSecSeqNo(objModelClauseDescPopUpForm
					.getSectionSeqNo());
			LogUtil.logMessage("Section Seq No set is "
					+ objModelClauseDescPopUpForm.getSectionSeqNo());
			objSubSectionVO.setSubSecSeqNo(objModelClauseDescPopUpForm
					.getSubSecSeqNo());
			//Modified for CR_81 Locomotive and PowerProducts enhancements by RR68151
				objSubSectionVO.setClaCharstcFlag(objModelClauseDescPopUpForm.
						getHdnClaChrstcFlag());

			LogUtil.logMessage("Charstc Flag " + objModelClauseDescPopUpForm.
					getHdnClaChrstcFlag());
			//Modified for CR_81 Locomotive and PowerProducts enhancements by RR68151 - Ends here		
			ViewSpecByModelBI objViewSpecByModelBO = ServiceFactory
			.getViewSpecByModelBO();
			arlParentList = (objViewSpecByModelBO
					.viewMasterSpecByModel(objSubSectionVO));
			
			if (arlParentList.size() != 0) {
				objModelClauseDescPopUpForm.setComponentVO(arlParentList);
				objModelClauseDescPopUpForm
				.setSpecType(objModelClauseDescPopUpForm
						.getHdnspecType());
				objModelClauseDescPopUpForm.setModelName(strModelName);
				SectionBI objSectionBO = ServiceFactory.getSectionBO();
				objSectionVO.setModelSeqNo(objModelClauseDescPopUpForm
						.getModelSeqNo());
				objSectionVO.setUserID(strUserID);
				arlSectionList = objSectionBO.fetchSections(objSectionVO);
				objModelClauseDescPopUpForm.setSectionList(arlSectionList);
				ModelSubSectionBI objModelSubSecBO = ServiceFactory
				.getSubSecMaintBO();
				objSubSectionVO.setModelSeqNo(objModelClauseDescPopUpForm
						.getModelSeqNo());
				objSubSectionVO.setSecSeqNo(objModelClauseDescPopUpForm
						.getSectionSeqNo());
				objSubSectionVO.setUserID(strUserID);
				arlSubSectionList = objModelSubSecBO
				.fetchSubSections(objSubSectionVO);
				if (arlSubSectionList.size() != 0) {
					
					strSubSecName = objSubSectionVO.getSubSecName();
					strSubSecCode = objSubSectionVO.getSubSecCode();
					strSubSecnameDisplay = strSubSecCode + "." + strSubSecName;
				}
				if(popupFlag.equalsIgnoreCase("N"))
 				{
 					arlSubSectionList = objModelSubSecBO
 					.fetchModelSubSectionDetails(objSubSectionVO);
 					LogUtil.logMessage("$ : "+ objSubSectionVO.getSubSecDisplay());
 	                 if (arlSubSectionList.size() != 0) {
 						
 						strSubSecName = objSubSectionVO.getSubSecName();
 						//strSubSecCode = objSubSectionVO.getSubSecCode();
 						//strSubSecnameDisplay = strSubSecCode + "." + strSubSecName;
 						objModelClauseDescPopUpForm.setSubSecDisplayName(objSubSectionVO.getSubSecName());
 						LogUtil.logMessage("$%$%$%%$%$%$%$%%%%%%%%%%%%%%%%%%%% : "+ objModelClauseDescPopUpForm.getSubSecDisplayName());
 					}
 					
 					
 				}
				objModelClauseDescPopUpForm.setSubSecName(strSubSecnameDisplay);
				objModelClauseDescPopUpForm
				.setSubSectionList(arlSubSectionList);
				return objActionMapping.findForward(strForwardKey);
			} else {
				objModelClauseDescPopUpForm
				.setSpecType(objModelClauseDescPopUpForm
						.getHdnspecType());
				objModelClauseDescPopUpForm.setModelName(strModelName);
				SectionBI objSectionBO = ServiceFactory.getSectionBO();
				objSectionVO.setModelSeqNo(objModelClauseDescPopUpForm
						.getModelSeqNo());
				arlSectionList = objSectionBO.fetchSections(objSectionVO);
				objModelClauseDescPopUpForm.setSectionList(arlSectionList);
				ModelSubSectionBI objModelSubSecBO = ServiceFactory
				.getSubSecMaintBO();
				objSubSectionVO.setModelSeqNo(objModelClauseDescPopUpForm
						.getModelSeqNo());
				objSubSectionVO.setSecSeqNo(objModelClauseDescPopUpForm
						.getSectionSeqNo());
				arlSubSectionList = objModelSubSecBO
				.fetchSubSections(objSubSectionVO);
				   if (arlSubSectionList.size() != 0) {
					
					strSubSecName = objSubSectionVO.getSubSecName();
					strSubSecCode = objSubSectionVO.getSubSecCode();
					strSubSecnameDisplay = strSubSecCode + "." + strSubSecName;
				}
                 
 				if(popupFlag.equalsIgnoreCase("N"))
 				{
 					arlSubSectionList = objModelSubSecBO
 					.fetchModelSubSectionDetails(objSubSectionVO);
 				       if (arlSubSectionList.size() != 0) {
 						
 						strSubSecName = objSubSectionVO.getSubSecName();
 						objModelClauseDescPopUpForm.setSubSecDisplayName(objSubSectionVO.getSubSecName());
 					}
 					
 					
 				}
				objModelClauseDescPopUpForm.setSubSecName(strSubSecnameDisplay);
				objModelClauseDescPopUpForm
				.setSubSectionList(arlSubSectionList);
				//Added for CR_92
				
				objModelClauseDescPopUpForm.setHdnModelName(strModelName);
				objModelClauseDescPopUpForm.setHdnspecType(objModelClauseDescPopUpForm
						.getHdnspecType());
				objModelClauseDescPopUpForm
				.setMessageID(ApplicationConstants.NO_PARENT_CLAUSE);
				return objActionMapping.findForward(strForwardKey);
				
			}
		} catch (Exception objExp) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
			
		}
		
		return objActionMapping.findForward(strForwardKey);
	}
	
	//Added For CR_85 
	
	/***************************************************************************
	 * This method is for loading Clause Popup for adding Characteristic Clause
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
	public ActionForward initLoadPopUp(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException, ApplicationException {
		LogUtil.logMessage("Inside the ModelCharClauseDescPopUpAction: InitLoadPopup");
		String strForwardKey = ApplicationConstants.POPUP_SUCCESS;
		String strUserID = "";
		String strSpecType = "";
		String strSelectedModel = objHttpServletRequest
		.getParameter(ApplicationConstants.SELECTED_MODEL_NAME);
		ArrayList arlSectionList;
		int intSelectedModelSeqNo = Integer.parseInt(objHttpServletRequest
				.getParameter(ApplicationConstants.SELECTED_MODEL_ID));
		try {
			ClauseVO objClauseVO = new ClauseVO();
			SectionVO objSecMaintVO = new SectionVO();

			ModelClauseDescPopUpForm objModelClauseDescPopUpForm = (ModelClauseDescPopUpForm) objActionForm;
			objModelClauseDescPopUpForm.setModelSeqNo(intSelectedModelSeqNo);
			objSecMaintVO.setModelSeqNo(intSelectedModelSeqNo);
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			ArrayList arlModelList = null;
			ArrayList arlSubSecList = null;
			int specTypeNo = -1;
			int modleSeqNo = -1;
			int sectionSeqNo = -1;
			int subSectionSeqNo = -1;
			String strModleSeqNo = null;
			String strSectionSeqNo = null;
			String strSubSectionSeqNo = null;
			
			strModleSeqNo = (String) objHttpServletRequest
			.getParameter(ApplicationConstants.SELECTED_MODEL_ID);
			strSectionSeqNo =(String) objSession.getAttribute("SECTION_SEQ_NO");
			strSubSectionSeqNo=(String) objSession.getAttribute("SUB_SECTION_SEQ_NO");
			if (strModleSeqNo != null && !(strModleSeqNo.equals("0"))) {
				objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
				intSelectedModelSeqNo = Integer.parseInt(strModleSeqNo);
				objModelClauseDescPopUpForm.setModelSeqNo(intSelectedModelSeqNo);
				} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {
					if (strModleSeqNo == null || "".equals(strModleSeqNo) || strModleSeqNo.equals("0")) {
						strModleSeqNo = (String) objSession
							.getAttribute("MODEL_SEQ_NO");
					intSelectedModelSeqNo = Integer.parseInt(strModleSeqNo);
					objModelClauseDescPopUpForm.setModelSeqNo(intSelectedModelSeqNo);
				}
			}
	
			if (strSectionSeqNo != null ) {
		
				objSession.setAttribute("SECTION_SEQ_NO", strSectionSeqNo);
				sectionSeqNo = Integer.parseInt(strSectionSeqNo);
						objModelClauseDescPopUpForm.setSectionSeqNo(sectionSeqNo);
			} else if (objSession.getAttribute("SECTION_SEQ_NO") != null) {
				
				if (strSectionSeqNo == null || "".equals(strSectionSeqNo)) {
					strSectionSeqNo = (String) objSession.getAttribute("SECTION_SEQ_NO");
					sectionSeqNo = -1;
					objModelClauseDescPopUpForm.setSectionSeqNo(sectionSeqNo);
		
				}
			}

			if (strSubSectionSeqNo != null ) {
				objSession.setAttribute("SUB_SECTION_SEQ_NO", strSubSectionSeqNo);
				subSectionSeqNo = Integer.parseInt(strSubSectionSeqNo);
				objModelClauseDescPopUpForm.setSubSecSeqNo(subSectionSeqNo);
					} 
			else if (objSession.getAttribute("SUB_SECTION_SEQ_NO") != null) {
				if (strSubSectionSeqNo == null || "".equals(strSubSectionSeqNo)) {
					
					strSubSectionSeqNo = (String) objSession.getAttribute("SUB_SECTION_SEQ_NO");
					subSectionSeqNo = -1;
					objModelClauseDescPopUpForm.setSubSecSeqNo(subSectionSeqNo);
					}
			}
			
			sectionSeqNo = objModelClauseDescPopUpForm.getSectionSeqNo();
			subSectionSeqNo = objModelClauseDescPopUpForm.getSubSecSeqNo();
	
			strSpecType = (String) objHttpServletRequest
			.getParameter(ApplicationConstants.SPEC_TYPE);
				// Getting User from the session
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			
			if ((String) objHttpServletRequest
					.getParameter("ChrstcFlag") != null) {
				objModelClauseDescPopUpForm.setHdnClaChrstcFlag((String) objHttpServletRequest
				.getParameter("ChrstcFlag"));
			}
			
			objClauseVO.setModelName(strSelectedModel);
			objClauseVO.setUserID(objLoginVo.getUserID());
			objClauseVO.setModelSeqNo(intSelectedModelSeqNo);
			objSecMaintVO.setModelSeqNo(intSelectedModelSeqNo);
			objSecMaintVO.setUserID(objLoginVo.getUserID());
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			arlSectionList = objSectionBO.fetchSections(objSecMaintVO);
			
			if (arlSectionList.size() != 0) {
				
				objModelClauseDescPopUpForm.setSectionList(arlSectionList);
			}
						
			objModelClauseDescPopUpForm.setSpecType(strSpecType);
			objModelClauseDescPopUpForm.setHdnspecType(strSpecType);
			objModelClauseDescPopUpForm.setModelName(strSelectedModel);
			objModelClauseDescPopUpForm.setHdnModelName(strSelectedModel);
			objModelClauseDescPopUpForm.setModelSeqNo(intSelectedModelSeqNo);
			
				if(strModleSeqNo !=null)
			{
				intSelectedModelSeqNo = Integer.parseInt(strModleSeqNo);
			}
			if (intSelectedModelSeqNo != -1 && intSelectedModelSeqNo != 0) {
				SectionVO objSectionMaintVo = new SectionVO();
				
				objSectionMaintVo.setModelSeqNo(intSelectedModelSeqNo);
				objSectionMaintVo.setUserID(objLoginVo.getUserID());
				SectionBI objSectionMaintBO = ServiceFactory.getSectionBO();
				arlSectionList = objSectionMaintBO
						.fetchSections(objSectionMaintVo);
				if (arlSectionList != null)
					objModelClauseDescPopUpForm.setSectionList(arlSectionList);
				objModelClauseDescPopUpForm.setSectionSeqNo(sectionSeqNo);
					}

			if (sectionSeqNo != -1 && sectionSeqNo != 0) {
				
				SubSectionVO objSubSecMaintVO = new SubSectionVO();
				objSubSecMaintVO.setModelSeqNo(intSelectedModelSeqNo);
				objSubSecMaintVO.setSecSeqNo(sectionSeqNo);
				objSubSecMaintVO.setUserID(objLoginVo.getUserID());

				ModelSubSectionBI objSubSecMaintBO = ServiceFactory
						.getSubSecMaintBO();
				arlSubSecList = objSubSecMaintBO
						.fetchSubSections(objSubSecMaintVO);

				if (arlSubSecList != null && arlSubSecList.size() != 0) {

					objModelClauseDescPopUpForm.setSubSectionList(arlSubSecList);
					objModelClauseDescPopUpForm.setSubSecSeqNo(subSectionSeqNo);
					objSubSecMaintVO.setSubSecSeqNo(subSectionSeqNo);
					objSubSecMaintVO.setClaCharstcFlag(objModelClauseDescPopUpForm.
								getHdnClaChrstcFlag());
					ViewSpecByModelBI objViewSpecByModelBO = ServiceFactory
					.getViewSpecByModelBO();
					ArrayList arlParentList = (objViewSpecByModelBO.viewMasterSpecByModel(objSubSecMaintVO));
					
					if (arlParentList.size() != 0) {
						objModelClauseDescPopUpForm.setComponentVO(arlParentList);
					} else {
						objModelClauseDescPopUpForm
						.setMessageID(ApplicationConstants.NO_PARENT_CLAUSE);						
					}
				}

				else {
					objModelClauseDescPopUpForm.setMethod("SubSections");
				}
			}
			 
		} catch (Exception objEx) {
			objEx.printStackTrace();
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***************************************************************************
	 * * * * This Method is used to fetch the Characteristic Combination Edl# No
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward fetchCharCombntnEdlView(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering modelClauseDescPopupAction.fetchCharCombntnEdlView");
		String strForwardKey = ApplicationConstants.POPUP_SUCCESS;
		ModelClauseDescPopUpForm objModelClauseDescPopUpForm = (ModelClauseDescPopUpForm) objActionForm;
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		int intClauseSeqNo=0;
		String modelName="";
		String specType="";
		String sectionName="";
		String subSectionName="";
		String ChrstcFlag="";
		int selectedModelNo=0;
		try {
			if ((String) objHttpServletRequest.getParameter("clauseSeq") != null) {
				 intClauseSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("clauseSeq").toString());
			}	
			if ((String) objHttpServletRequest.getParameter("specType") != null) {
				specType =(String)objHttpServletRequest
						.getParameter("specType");
			}	
			if ((String) objHttpServletRequest.getParameter("selectedModelName") != null) {
				modelName =(String)objHttpServletRequest
						.getParameter("selectedModelName");
			}	
			if ((String) objHttpServletRequest.getParameter("selectedSectionName") != null) {
				sectionName =(String)objHttpServletRequest
						.getParameter("selectedSectionName");
			}
			if ((String) objHttpServletRequest.getParameter("selectedSubSectionName") != null) {
				subSectionName =(String)objHttpServletRequest
						.getParameter("selectedSubSectionName");
			}	
			if ((String) objHttpServletRequest.getParameter("selectedModelNo") != null) {
				selectedModelNo = Integer.parseInt(objHttpServletRequest
						.getParameter("selectedModelNo").toString());
			}	
			if ((String) objHttpServletRequest.getParameter("ChrstcFlag") != null) {
				ChrstcFlag =(String)objHttpServletRequest
						.getParameter("ChrstcFlag");
			}		
			
			ClauseVO objClauseVO = new ClauseVO();
			ArrayList arlCharGrpCombntn = new ArrayList();
			objClauseVO.setClauseSeqNo(intClauseSeqNo);
			objClauseVO.setUserID(objLoginVo.getUserID());
			ModelAssignEdlBI objModelAssignEdlBI = ServiceFactory.getModelAssignEdlBO();
			arlCharGrpCombntn = objModelAssignEdlBI.fetchCharGrpCombntn(objClauseVO);
			objModelClauseDescPopUpForm.setHdnClauseSeqNo(intClauseSeqNo);
			objModelClauseDescPopUpForm.setHdnspecType(specType);
			objModelClauseDescPopUpForm.setHdnModelName(modelName);
			objModelClauseDescPopUpForm.setSectionName(sectionName);
			objModelClauseDescPopUpForm.setSubSecName(subSectionName);
			objModelClauseDescPopUpForm.setHdnSectionName(sectionName);
			objModelClauseDescPopUpForm.setHdnSubSectionName(subSectionName);
			objModelClauseDescPopUpForm.setModelSeqNo(selectedModelNo);
			objModelClauseDescPopUpForm.setHdnClaChrstcFlag(ChrstcFlag);
			if (arlCharGrpCombntn != null && arlCharGrpCombntn.size() > 0){		
				objModelClauseDescPopUpForm.setCharGrpCombntnList(arlCharGrpCombntn);
				objModelClauseDescPopUpForm.setHdnClauseSeqNo(intClauseSeqNo);
				LogUtil.logMessage("arlCharGrpCombntn.size()"+arlCharGrpCombntn.size());	
			}
			
			objModelClauseDescPopUpForm.setMethod("fetchCharCombntnEdlView");
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in CharacteristicEDLMapAction.fetchCharCombntnEdlView");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
		}
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		
		return objActionMapping.findForward(strForwardKey);
	}
	
}