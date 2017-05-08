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
import com.EMD.LSDB.bo.serviceinterface.ModelBI;
import com.EMD.LSDB.bo.serviceinterface.ModelCompBI;
import com.EMD.LSDB.bo.serviceinterface.ModelCompGroupBI;
import com.EMD.LSDB.bo.serviceinterface.ModelCompMapBI;
import com.EMD.LSDB.bo.serviceinterface.ModelSubSectionBI;
import com.EMD.LSDB.bo.serviceinterface.SectionBI;
import com.EMD.LSDB.bo.serviceinterface.SpecTypeBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.form.MasterMaintenance.CompGroupMaintForm;
import com.EMD.LSDB.form.MasterMaintenance.CompMapMaintForm;
import com.EMD.LSDB.vo.common.CompGroupVO;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SpecTypeVo;
import com.EMD.LSDB.vo.common.SubSectionVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This Action class has the business methods for the Component
 *          Mappings
 ******************************************************************************/
/***************************************************************************
 * ------------------------------------------------------------------------------------------------------
 *     Date         version  create by          	comments                              Remarks 
 * 16/02/2010        1.0      SD41630    Added  for Model level, SpecificationType,   Added for CR_83
 *                                       Model, Section and Sub Section of values be
 *                                       maintain in the session and display in the
 *                                       screen where ever occurred      
 * 											 	 
 * --------------------------------------------------------------------------------------------------------
 * **************************************************************************/
public class CompMapMaintAction extends EMDAction {
	/***************************************************************************
	 * This method is for loading the spec types
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

	public ActionForward initLoad(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
			throws ApplicationException {
		ArrayList arlModelList = null;
		ArrayList arlSectionList = null;
		ArrayList arlSubSecList = null;
		ArrayList arlCompGrpList = new ArrayList();
		//CR_83 lines are started here
		int specTypeNo = -1;
		int modleSeqNo = -1;
		int sectionSeqNo = -1;
		int subSectionSeqNo = -1;
		int compGrpSeqNo = -1;
		String compGrpTypeSeqNo = null;
		String strSpecTypeNo = null;
		String strModleSeqNo = null;
		String strSectionSeqNo = null;
		String strSubSectionSeqNo = null;

		LogUtil.logMessage("Entering CompMapMaintAction.initLoad");
		String strForwardKey = ApplicationConstants.SUCCESS;
		CompMapMaintForm objCompMapMaintForm = (CompMapMaintForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
				.getAttribute(ApplicationConstants.USER_IN_SESSION);

		SpecTypeVo objSpecTypeVo = new SpecTypeVo();
		strSpecTypeNo = (String) objHttpServletRequest
				.getParameter("specTypeNo");
		strModleSeqNo = (String) objHttpServletRequest
				.getParameter("modelSeqNo");
		strSectionSeqNo = (String) objHttpServletRequest
				.getParameter("sectionSeqNo");
		strSubSectionSeqNo = (String) objHttpServletRequest
				.getParameter("subSectionSeqNo");
		try {
		if (objSession.getAttribute("SUB_SECTION_SEQ_NO") != null) {
			LogUtil
			.logMessage(" before getting SUB_SECTION_SEQ_NO value ***********************************"+subSectionSeqNo);
			strSubSectionSeqNo = (String) objSession
					.getAttribute("SUB_SECTION_SEQ_NO");
			objSession.setAttribute("SUB_SECTION_SEQ_NO", strSubSectionSeqNo);
			subSectionSeqNo = Integer.parseInt(strSubSectionSeqNo);
			objCompMapMaintForm.setSubSectionSeqNo(subSectionSeqNo);
			}
		if (strSpecTypeNo != null) {
			objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);
			specTypeNo = Integer.parseInt(strSpecTypeNo);
			objCompMapMaintForm.setSpecTypeNo(specTypeNo);
	} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
			if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {
				strSpecTypeNo = (String) objSession
						.getAttribute("SPEC_TYPE_NO");
				specTypeNo = Integer.parseInt(strSpecTypeNo);
				objCompMapMaintForm.setSpecTypeNo(specTypeNo);
			}
		}

		if (strModleSeqNo != null) {
			objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
			modleSeqNo = Integer.parseInt(strModleSeqNo);
			objCompMapMaintForm.setModelSeqNo(modleSeqNo);
		} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {
			if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
				strModleSeqNo = (String) objSession
						.getAttribute("MODEL_SEQ_NO");
				modleSeqNo = Integer.parseInt(strModleSeqNo);
				objCompMapMaintForm.setModelSeqNo(modleSeqNo);
			}
		}

		if (strSectionSeqNo != null) {
			objSession.setAttribute("SECTION_SEQ_NO", strSectionSeqNo);
			sectionSeqNo = Integer.parseInt(strSectionSeqNo);
			objCompMapMaintForm.setSectionSeqNo(sectionSeqNo);
		} else if (objSession.getAttribute("SECTION_SEQ_NO") != null) {
			if (strSectionSeqNo == null || "".equals(strSectionSeqNo)) {
				strSectionSeqNo = (String) objSession
						.getAttribute("SECTION_SEQ_NO");
				sectionSeqNo = Integer.parseInt(strSectionSeqNo);
				objCompMapMaintForm.setSectionSeqNo(sectionSeqNo);

			}
		}

		if (strSubSectionSeqNo != null) {
			objSession.setAttribute("SUB_SECTION_SEQ_NO", strSubSectionSeqNo);
			subSectionSeqNo = Integer.parseInt(strSubSectionSeqNo);
			objCompMapMaintForm.setSubSectionSeqNo(subSectionSeqNo);
		} else if (objSession.getAttribute("SUB_SECTION_SEQ_NO") != null) {
			if (strSubSectionSeqNo == null || "".equals(strSubSectionSeqNo)) {
				strSubSectionSeqNo = (String) objSession
						.getAttribute("SUB_SECTION_SEQ_NO");
				objSession.setAttribute("SUB_SECTION_SEQ_NO",
						strSubSectionSeqNo);
				subSectionSeqNo = Integer.parseInt(strSubSectionSeqNo);
				objCompMapMaintForm.setSubSectionSeqNo(subSectionSeqNo);
			}

		}
		
		specTypeNo = objCompMapMaintForm.getSpecTypeNo();
		modleSeqNo = objCompMapMaintForm.getModelSeqNo();
		sectionSeqNo = objCompMapMaintForm.getSectionSeqNo();
		subSectionSeqNo = objCompMapMaintForm.getSubSectionSeqNo();
		compGrpSeqNo = objCompMapMaintForm.getCompGrpSeqNo();
		compGrpTypeSeqNo = objCompMapMaintForm.getCompgrpCat();
//		CR_83 lines ends here
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objCompMapMaintForm.setSpecTypeList(arlSpec);
				objCompMapMaintForm.setSpecTypeNo(specTypeNo);
			}

			//CR_83 Lines started here
			if (specTypeNo != -1 && specTypeNo != 0) {
				ModelVo objModelVo = new ModelVo();
				objModelVo.setUserID(objLoginVo.getUserID());
				objModelVo.setSpecTypeSeqNo(specTypeNo);
				ModelBI objModelBO = ServiceFactory.getModelBO();
				if (objModelBO.fetchModels(objModelVo) != null) {
					arlModelList = objModelBO.fetchModels(objModelVo);
					objCompMapMaintForm.setModelList(arlModelList);
					objCompMapMaintForm.setModelSeqNo(modleSeqNo);
							if (modleSeqNo != -1 && modleSeqNo != 0) {
								SectionVO objSectionMaintVo = new SectionVO();
								objSectionMaintVo.setModelSeqNo(modleSeqNo);
								objSectionMaintVo.setUserID(objLoginVo.getUserID());
								SectionBI objSectionMaintBO = ServiceFactory.getSectionBO();
								arlSectionList = objSectionMaintBO
										.fetchSections(objSectionMaintVo);
									if (arlSectionList != null){
										objCompMapMaintForm.setSectionList(arlSectionList);
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
													objCompMapMaintForm.setSubSectionList(arlSubSecList);
													//objCompMapMaintForm.setSubSectionSeqNo(subSectionSeqNo);
												}
				
												else {	
													objCompMapMaintForm.setMethod("SubSections");
												}
											}
							}
						}
			}
				CompGroupVO objCompGrpVo = new CompGroupVO();
				ModelCompGroupBI objCompGrpBo = ServiceFactory
						.getModelCompGroupBO();
				objCompGrpVo.setUserID(objLoginVo.getUserID());
				ArrayList arlCompGrpType = new ArrayList();
				arlCompGrpType = objCompGrpBo.fetchCompGrpTypes(objCompGrpVo);
				if (arlCompGrpType.size() != 0) {
					objCompMapMaintForm.setCompGroupTypeList(arlCompGrpType);
				}
				if (objCompMapMaintForm.getCompgrpCat() != null) {
					objCompGrpVo.setCompGrpTypeSeqNo(Integer
							.parseInt(objCompMapMaintForm.getCompgrpCat()));

					compGrpTypeSeqNo = Integer.toString(objCompGrpVo
							.getCompGrpTypeSeqNo());
				} else {
					objCompGrpVo
							.setCompGrpTypeSeqNo(ApplicationConstants.COMPGRPTYPE_ZERO);
					compGrpTypeSeqNo = Integer.toString(objCompGrpVo
							.getCompGrpTypeSeqNo());
				}
				//Added for CR_97
				objCompGrpVo.setSpecTypeSeqNo(specTypeNo);
				//CR_97 Ends here
				arlCompGrpList = objCompGrpBo.fetchCompGroups(objCompGrpVo);

				if (arlCompGrpList != null && arlCompGrpList.size() > 0) {
					objCompMapMaintForm.setCompGrpList(arlCompGrpList);
				}
			}
			//CR_83 lines ends here
			/*
			 * commented For CR_83 SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			 * objSpecTypeVo.setUserID(objLoginVo.getUserID()); ArrayList
			 * arlSpec = new ArrayList(); SpecTypeBI objSpecTypeBI =
			 * ServiceFactory.getSpecTypeBO(); arlSpec =
			 * objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			 * LogUtil.logMessage("objArrList for Spec Types :" + arlSpec);
			 * objCompMapMaintForm.setSpecTypeList(arlSpec);
			 */
			// CR_83 lines are started here
			// objCompMapMaintForm.setCompGrpList(objCompMapMaintForm.getHdnSelCompGrp());
			// objModelMaintForm.setHideMessage("fromInitLoad");
			// CR_81- Lines are ends here
		} catch (Exception objExp) {

			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}

		LogUtil.logMessage("strForwardKey :" + strForwardKey);

		return objActionMapping.findForward(strForwardKey);

	}

	/***************************************************************************
	 * This Method is used to fetch the Model and Component Group Details
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
	public ActionForward fetchModels(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
			throws DataAccessException, ApplicationException {

		LogUtil.logMessage("Entering CompMapMaintAction.fetchModels");
		String strForward = ApplicationConstants.SUCCESS;
		CompMapMaintForm objCompMapMaintForm = (CompMapMaintForm) objActionForm;
//		CR_83 lines are started here
		int specTypeNo = -1;
		int modleSeqNo = -1;
		int sectionSeqNo = -1;
		int subSectionSeqNo = -1;
		int compGrpSeqNo = -1;
		String compGrpTypeSeqNo = null;
		String strSpecTypeNo = null;
		String strModleSeqNo = null;
		String strSectionSeqNo = null;
		String strSubSectionSeqNo = null;
		ArrayList arlSectionList = null;
		ArrayList arlSubSecList = null;
		//CR_83 lines  ends here
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
//CR_83 limes started here
		SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			strSpecTypeNo = (String) objHttpServletRequest
					.getParameter("specTypeNo");
			strModleSeqNo = (String) objHttpServletRequest
					.getParameter("modelSeqNo");
			strSectionSeqNo = (String) objHttpServletRequest
					.getParameter("sectionSeqNo");
			strSubSectionSeqNo = (String) objHttpServletRequest
					.getParameter("subSectionSeqNo");
					
			if (objSession.getAttribute("SUB_SECTION_SEQ_NO") != null) {
				
				strSubSectionSeqNo = (String) objSession
						.getAttribute("SUB_SECTION_SEQ_NO");
				objSession.setAttribute("SUB_SECTION_SEQ_NO", strSubSectionSeqNo);
				subSectionSeqNo = Integer.parseInt(strSubSectionSeqNo);
				objCompMapMaintForm.setSubSectionSeqNo(subSectionSeqNo);
				
			}
			if (strSpecTypeNo != null) {
				objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);
				specTypeNo = Integer.parseInt(strSpecTypeNo);
				objCompMapMaintForm.setSpecTypeNo(specTypeNo);
			} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
				if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {
					strSpecTypeNo = (String) objSession
							.getAttribute("SPEC_TYPE_NO");
					specTypeNo = Integer.parseInt(strSpecTypeNo);
					objCompMapMaintForm.setSpecTypeNo(specTypeNo);
			      }
			}

			if (strModleSeqNo != null ) {
			
				objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
				modleSeqNo = Integer.parseInt(strModleSeqNo);
				objCompMapMaintForm.setModelSeqNo(modleSeqNo);
				
			} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {
				if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
					strModleSeqNo = (String) objSession
							.getAttribute("MODEL_SEQ_NO");
					modleSeqNo = Integer.parseInt(strModleSeqNo);
					objCompMapMaintForm.setModelSeqNo(modleSeqNo);
				}
			}
			if (strSectionSeqNo != null) {
				
				objSession.setAttribute("SECTION_SEQ_NO", strSectionSeqNo);
				sectionSeqNo = Integer.parseInt(strSectionSeqNo);
				objCompMapMaintForm.setSectionSeqNo(sectionSeqNo);
				
			} else if (objSession.getAttribute("SECTION_SEQ_NO") != null) {
				if (strSectionSeqNo == null || "".equals(strSectionSeqNo)) {
					if(modleSeqNo!=-1 && modleSeqNo!=0){
					strSectionSeqNo = (String) objSession
							.getAttribute("SECTION_SEQ_NO");
					sectionSeqNo = Integer.parseInt(strSectionSeqNo);
					objCompMapMaintForm.setSectionSeqNo(sectionSeqNo);
					}
				}
			}

			if (strSubSectionSeqNo != null) {
			
				
				objSession.setAttribute("SUB_SECTION_SEQ_NO", strSubSectionSeqNo);
				subSectionSeqNo = Integer.parseInt(strSubSectionSeqNo);
				objCompMapMaintForm.setSubSectionSeqNo(subSectionSeqNo);
				
			   } else if (objSession.getAttribute("SUB_SECTION_SEQ_NO") != null) {
				if (strSubSectionSeqNo == null || "".equals(strSubSectionSeqNo)) {
					if(sectionSeqNo!=-1 && sectionSeqNo!=0){
					strSubSectionSeqNo = (String) objSession
							.getAttribute("SUB_SECTION_SEQ_NO");
					objSession.setAttribute("SUB_SECTION_SEQ_NO",
							strSubSectionSeqNo);
					subSectionSeqNo = Integer.parseInt(strSubSectionSeqNo);
					objCompMapMaintForm.setSubSectionSeqNo(subSectionSeqNo);
					}
				}

			}
			
			specTypeNo = objCompMapMaintForm.getSpecTypeNo();
			modleSeqNo = objCompMapMaintForm.getModelSeqNo();
			sectionSeqNo = objCompMapMaintForm.getSectionSeqNo();
			subSectionSeqNo = objCompMapMaintForm.getSubSectionSeqNo();
			compGrpSeqNo = objCompMapMaintForm.getCompGrpSeqNo();
			compGrpTypeSeqNo = objCompMapMaintForm.getCompgrpCat();
			
		//			CR_83 lines ends here
			
			ModelVo objModelVo = new ModelVo();
			ModelBI objModelBo = ServiceFactory.getModelBO();

			CompGroupVO objCompGrpVo = new CompGroupVO();
			ModelCompGroupBI objCompGrpBo = ServiceFactory
					.getModelCompGroupBO();

			ArrayList arlModelList = new ArrayList();
			ArrayList arlCompGrpList = new ArrayList();

			ArrayList arlSpec = new ArrayList();
		//	SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();

			String strUserID = "";

			LoginVO objLoginVo = (LoginVO) objSession
					.getAttribute(ApplicationConstants.USER_IN_SESSION);

			// Getting User from the session
			if (objLoginVo != null) {

				strUserID = objLoginVo.getUserID();
			}

			/**
			 * **Added for CR-46 PM&I Spec for loading Specification Type Drop
			 * Down - Starts Here***
			 */

			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);

			objCompMapMaintForm.setSpecTypeList(arlSpec);
			objCompMapMaintForm.setSpecTypeNo(objCompMapMaintForm
					.getSpecTypeNo());

			/**
			 * **Added for CR-46 PM&I Spec for loading Specification Type Drop
			 * Down - Ends Here***
			 */
			if (specTypeNo != -1 && specTypeNo != 0) {
			// To fetch Models
			objModelVo.setUserID(strUserID);

			// Added for CR-46 PM&I Spec to load Models based on Spec Type
			objModelVo.setSpecTypeSeqNo(objCompMapMaintForm.getSpecTypeNo());

			arlModelList = objModelBo.fetchModels(objModelVo);

			if (arlModelList != null && arlModelList.size() > 0) {
				objCompMapMaintForm.setModelList(arlModelList);
				objCompMapMaintForm.setHdnSelModel(objCompMapMaintForm
						.getHdnSelModel());
				//CR_83 lines started here
						if (modleSeqNo != -1 && modleSeqNo != 0) {
							SectionVO objSectionMaintVo = new SectionVO();
							objSectionMaintVo.setModelSeqNo(modleSeqNo);
							objSectionMaintVo.setUserID(objLoginVo.getUserID());
							SectionBI objSectionMaintBO = ServiceFactory.getSectionBO();
							arlSectionList = objSectionMaintBO
									.fetchSections(objSectionMaintVo);
							if (arlSectionList != null)
								objCompMapMaintForm.setSectionList(arlSectionList);
										if (sectionSeqNo != -1 && sectionSeqNo != 0 ) {
											SubSectionVO objSubSecMaintVO = new SubSectionVO();
											objSubSecMaintVO.setModelSeqNo(modleSeqNo);
											objSubSecMaintVO.setSecSeqNo(sectionSeqNo);
											objSubSecMaintVO.setUserID(objLoginVo.getUserID());
					
											ModelSubSectionBI objSubSecMaintBO = ServiceFactory
													.getSubSecMaintBO();
											arlSubSecList = objSubSecMaintBO
													.fetchSubSections(objSubSecMaintVO);
					
											if (arlSubSecList != null && arlSubSecList.size() != 0) {
					
												objCompMapMaintForm.setSubSectionList(arlSubSecList);
												//objCompMapMaintForm.setSubSectionSeqNo(subSectionSeqNo);
											}
					
											else {
					
												objCompMapMaintForm.setMethod("SubSections");
					
											}
							}
					}
			}
			
			}

			// To fetch Component Groups
			objCompGrpVo.setUserID(strUserID);

			// Added For CR_81 on 28-Dec-09 by RR68151 to fetch Component Group
			// Types
			ArrayList arlCompGrpType = new ArrayList();
			arlCompGrpType = objCompGrpBo.fetchCompGrpTypes(objCompGrpVo);
			if (arlCompGrpType.size() != 0) {
				objCompMapMaintForm.setCompGroupTypeList(arlCompGrpType);
			}

			if (objCompMapMaintForm.getCompgrpCat()!= null && !("".equals(objCompMapMaintForm.getCompgrpCat()))){
				
				objCompGrpVo.setCompGrpTypeSeqNo(Integer
						.parseInt(objCompMapMaintForm.getCompgrpCat()));
			}else{
				objCompGrpVo
						.setCompGrpTypeSeqNo(ApplicationConstants.COMPGRPTYPE_ZERO);
			}
			objCompGrpVo.setSpecTypeSeqNo(specTypeNo);
		
			// Added For CR_81 on 28-Dec-09 by RR68151 - Ends here

			arlCompGrpList = objCompGrpBo.fetchCompGroups(objCompGrpVo);

			if (arlCompGrpList != null && arlCompGrpList.size() > 0) {
				objCompMapMaintForm.setCompGrpList(arlCompGrpList);
			}

			
			//CR_83 lines  ends here
		} catch (Exception objExp) {
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);

	}

	/***************************************************************************
	 * This Method is used to fetch the Section Details
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
	public ActionForward sectionLoad(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
			throws DataAccessException, ApplicationException {

		LogUtil.logMessage("Entering CompMapMaintAction.sectionLoad");
		String strForward = ApplicationConstants.SUCCESS;
		CompMapMaintForm objCompMapMaintForm = (CompMapMaintForm) objActionForm;
//		CR_83 lines are started here
		int specTypeNo = -1;
		int modleSeqNo = -1;
		int sectionSeqNo = -1;
		int subSectionSeqNo = -1;
		int compGrpSeqNo = -1;
		String compGrpTypeSeqNo = null;
		String strSpecTypeNo = null;
		String strModleSeqNo = null;
		String strSectionSeqNo = null;
		String strSubSectionSeqNo = null;
		ArrayList arlSectionList = null;
		ArrayList arlSubSecList = null;
		//CR_83 lines  ends here
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
//CR_83 limes started here


			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			strSpecTypeNo = (String) objHttpServletRequest
					.getParameter("specTypeNo");
			strModleSeqNo = (String) objHttpServletRequest
					.getParameter("modelSeqNo");
			strSectionSeqNo = (String) objHttpServletRequest
					.getParameter("sectionSeqNo");
			strSubSectionSeqNo = (String) objHttpServletRequest
					.getParameter("subSectionSeqNo");
			
			if (objSession.getAttribute("SUB_SECTION_SEQ_NO") != null) {
				strSubSectionSeqNo = (String) objSession
						.getAttribute("SUB_SECTION_SEQ_NO");
				objSession.setAttribute("SUB_SECTION_SEQ_NO", strSubSectionSeqNo);
				subSectionSeqNo = Integer.parseInt(strSubSectionSeqNo);
				objCompMapMaintForm.setSubSectionSeqNo(subSectionSeqNo);
			}
			if (strSpecTypeNo != null) {
					objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);
     			specTypeNo = Integer.parseInt(strSpecTypeNo);
				objCompMapMaintForm.setSpecTypeNo(specTypeNo);
				} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
				if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {
					strSpecTypeNo = (String) objSession
							.getAttribute("SPEC_TYPE_NO");
					specTypeNo = Integer.parseInt(strSpecTypeNo);
					objCompMapMaintForm.setSpecTypeNo(specTypeNo);
				}
			}

			if (strModleSeqNo != null) {

				objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
				modleSeqNo = Integer.parseInt(strModleSeqNo);
				objCompMapMaintForm.setModelSeqNo(modleSeqNo);

			} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {

				if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
					strModleSeqNo = (String) objSession
							.getAttribute("MODEL_SEQ_NO");
					modleSeqNo = Integer.parseInt(strModleSeqNo);
					objCompMapMaintForm.setModelSeqNo(modleSeqNo);
				}
			}

			if (strSectionSeqNo != null) {

				objSession.setAttribute("SECTION_SEQ_NO", strSectionSeqNo);
				sectionSeqNo = Integer.parseInt(strSectionSeqNo);
				objCompMapMaintForm.setSectionSeqNo(sectionSeqNo);
			} else if (objSession.getAttribute("SECTION_SEQ_NO") != null) {
				if (strSectionSeqNo == null || "".equals(strSectionSeqNo)) {
					strSectionSeqNo = (String) objSession
							.getAttribute("SECTION_SEQ_NO");
					sectionSeqNo = Integer.parseInt(strSectionSeqNo);
					objCompMapMaintForm.setSectionSeqNo(sectionSeqNo);

				}
			}

			if (strSubSectionSeqNo != null) {
				objSession.setAttribute("SUB_SECTION_SEQ_NO", strSubSectionSeqNo);
				subSectionSeqNo = Integer.parseInt(strSubSectionSeqNo);
				objCompMapMaintForm.setSubSectionSeqNo(subSectionSeqNo);
				} else if (objSession.getAttribute("SUB_SECTION_SEQ_NO") != null) {
				if (strSubSectionSeqNo == null || "".equals(strSubSectionSeqNo)) {
					strSubSectionSeqNo = (String) objSession
							.getAttribute("SUB_SECTION_SEQ_NO");
					objSession.setAttribute("SUB_SECTION_SEQ_NO",
							strSubSectionSeqNo);
					subSectionSeqNo = Integer.parseInt(strSubSectionSeqNo);
					objCompMapMaintForm.setSubSectionSeqNo(subSectionSeqNo);
				}

			}
			
			specTypeNo = objCompMapMaintForm.getSpecTypeNo();
			modleSeqNo = objCompMapMaintForm.getModelSeqNo();
			sectionSeqNo = objCompMapMaintForm.getSectionSeqNo();
			subSectionSeqNo = objCompMapMaintForm.getSubSectionSeqNo();
			compGrpSeqNo = objCompMapMaintForm.getCompGrpSeqNo();
			compGrpTypeSeqNo = objCompMapMaintForm.getCompgrpCat();
//			CR_83 lines ends here

			ModelVo objModelVo = new ModelVo();
			ModelBI objModelBo = ServiceFactory.getModelBO();

			CompGroupVO objCompGrpVo = new CompGroupVO();
			ModelCompGroupBI objCompGrpBo = ServiceFactory
					.getModelCompGroupBO();

			SectionVO objSectionVo = new SectionVO();
			SectionBI objSectionBo = ServiceFactory.getSectionBO();

			ArrayList arlModelList = new ArrayList();
			ArrayList arlCompGrpList = new ArrayList();
			//ArrayList arlSectionList = new ArrayList();

			ArrayList arlSpec = new ArrayList();
			//SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();

			String strUserID = "";

			LoginVO objLoginVo = (LoginVO) objSession
					.getAttribute(ApplicationConstants.USER_IN_SESSION);

			// Getting User from the session
			if (objLoginVo != null) {

				strUserID = objLoginVo.getUserID();
			}

			/**
			 * **Added for CR-46 PM&I Spec for loading Specification Type Drop
			 * Down - Starts Here***
			 */

			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);

			objCompMapMaintForm.setSpecTypeList(arlSpec);
			objCompMapMaintForm.setSpecTypeNo(objCompMapMaintForm
					.getSpecTypeNo());

			/**
			 * **Added for CR-46 PM&I Spec for loading Specification Type Drop
			 * Down - Ends Here***
			 */

			// To get Models
			objModelVo.setUserID(strUserID);

			// Added for CR-46 PM&I Spec to load Models based on Spec Type
			objModelVo.setSpecTypeSeqNo(objCompMapMaintForm.getSpecTypeNo());

			arlModelList = objModelBo.fetchModels(objModelVo);

			if (arlModelList != null && arlModelList.size() > 0) {
				objCompMapMaintForm.setModelList(arlModelList);
				objCompMapMaintForm.setHdnSelModel(objCompMapMaintForm
						.getHdnSelModel());
			}

			// To fetch Component Groups
			objCompGrpVo.setUserID(strUserID);

			// Added For CR_81 on 28-Dec-09 by RR68151 to fetch Component Group
			// Types
			ArrayList arlCompGrpType = new ArrayList();
			arlCompGrpType = objCompGrpBo.fetchCompGrpTypes(objCompGrpVo);
			if (arlCompGrpType.size() != 0) {
				objCompMapMaintForm.setCompGroupTypeList(arlCompGrpType);
			}
			//Added For CR_97 on 15 march 2011 by SD 41630 Start here 
			if (objCompMapMaintForm.getCompgrpCat()!= null && !("".equals(objCompMapMaintForm.getCompgrpCat()))){
				objCompGrpVo.setCompGrpTypeSeqNo(Integer
						.parseInt(objCompMapMaintForm.getCompgrpCat()));}
			else{
				objCompGrpVo
						.setCompGrpTypeSeqNo(ApplicationConstants.COMPGRPTYPE_ZERO);
			}
			
			// Added For CR_81 on 28-Dec-09 by RR68151 - Ends here
			objCompGrpVo.setSpecTypeSeqNo(objCompMapMaintForm
					.getSpecTypeNo());

			arlCompGrpList = objCompGrpBo.fetchCompGroups(objCompGrpVo);

			if (arlCompGrpList != null && arlCompGrpList.size() > 0) {
				objCompMapMaintForm.setCompGrpList(arlCompGrpList);
			}
//			CR_97 code Lines ends here 
			if (modleSeqNo != -1 && modleSeqNo != 0) {
			// To get Sections
			objSectionVo.setModelSeqNo(objCompMapMaintForm.getModelSeqNo());
			objSectionVo.setUserID(strUserID);

			arlSectionList = objSectionBo.fetchSections(objSectionVo);

			if (arlSectionList != null && arlSectionList.size() > 0) {
				objCompMapMaintForm.setSectionList(arlSectionList);
				objCompMapMaintForm.setHdnSelModel(objCompMapMaintForm
						.getHdnSelModel());
			}
			}
			
//			CR_83 lines started here
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
					objCompMapMaintForm.setSubSectionList(arlSubSecList);
					objCompMapMaintForm.setSubSectionSeqNo(subSectionSeqNo);
				}else {
				objCompMapMaintForm.setMethod("SubSections");
				}
		}
			
			//CR_83 lines  ends here
		} catch (Exception objExp) {
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);

	}

	/***************************************************************************
	 * This Method is used to fetch the SubSection Details
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
	public ActionForward subSectionLoad(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
			throws DataAccessException, ApplicationException {

		LogUtil.logMessage("Entering CompMapMaintAction.subSectionLoad");
		String strForward = ApplicationConstants.SUCCESS;
		CompMapMaintForm objCompMapMaintForm = (CompMapMaintForm) objActionForm;
		
//		CR_83 lines are started here
		int specTypeNo = -1;
		int modleSeqNo = -1;
		int sectionSeqNo = -1;
		int subSectionSeqNo = -1;
		int compGrpSeqNo = -1;
		String compGrpTypeSeqNo = null;
		String strSpecTypeNo = null;
		String strModleSeqNo = null;
		String strSectionSeqNo = null;
		String strSubSectionSeqNo = null;
		ArrayList arlSectionList = null;
		//ArrayList arlSubSecList = null;
		//CR_83 lines  ends here
//		CR_83 lines  ends here
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
//CR_83 limes started here


			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			strSpecTypeNo = (String) objHttpServletRequest
					.getParameter("specTypeNo");
			strModleSeqNo = (String) objHttpServletRequest
					.getParameter("modelSeqNo");
			strSectionSeqNo = (String) objHttpServletRequest
					.getParameter("sectionSeqNo");
			strSubSectionSeqNo = (String) objHttpServletRequest
					.getParameter("subSectionSeqNo");
			
			if (objSession.getAttribute("SUB_SECTION_SEQ_NO") != null) {
				strSubSectionSeqNo = (String) objSession
						.getAttribute("SUB_SECTION_SEQ_NO");
				objSession.setAttribute("SUB_SECTION_SEQ_NO", strSubSectionSeqNo);
				subSectionSeqNo = Integer.parseInt(strSubSectionSeqNo);
				objCompMapMaintForm.setSubSectionSeqNo(subSectionSeqNo);
							
			}

			

			if (strSpecTypeNo != null) {
				objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);

				specTypeNo = Integer.parseInt(strSpecTypeNo);
				LogUtil.logMessage("value of strSpecTypeNo from  session"
						+ strSpecTypeNo);
				objCompMapMaintForm.setSpecTypeNo(specTypeNo);
		
			} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
				if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {
					strSpecTypeNo = (String) objSession
							.getAttribute("SPEC_TYPE_NO");
					specTypeNo = Integer.parseInt(strSpecTypeNo);
					objCompMapMaintForm.setSpecTypeNo(specTypeNo);
					}
			}

			if (strModleSeqNo != null) {

				objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
				modleSeqNo = Integer.parseInt(strModleSeqNo);
				objCompMapMaintForm.setModelSeqNo(modleSeqNo);

			} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {

				if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
					strModleSeqNo = (String) objSession
							.getAttribute("MODEL_SEQ_NO");
					modleSeqNo = Integer.parseInt(strModleSeqNo);
					objCompMapMaintForm.setModelSeqNo(modleSeqNo);
				}
			}

			if (strSectionSeqNo != null) {

				objSession.setAttribute("SECTION_SEQ_NO", strSectionSeqNo);
				sectionSeqNo = Integer.parseInt(strSectionSeqNo);
				objCompMapMaintForm.setSectionSeqNo(sectionSeqNo);
			} else if (objSession.getAttribute("SECTION_SEQ_NO") != null) {
				if (strSectionSeqNo == null || "".equals(strSectionSeqNo)) {
					strSectionSeqNo = (String) objSession
							.getAttribute("SECTION_SEQ_NO");
					sectionSeqNo = Integer.parseInt(strSectionSeqNo);
					objCompMapMaintForm.setSectionSeqNo(sectionSeqNo);

				}
			}

			if (strSubSectionSeqNo != null) {
				objSession.setAttribute("SUB_SECTION_SEQ_NO", strSubSectionSeqNo);
				subSectionSeqNo = Integer.parseInt(strSubSectionSeqNo);
				objCompMapMaintForm.setSubSectionSeqNo(subSectionSeqNo);
					} else if (objSession.getAttribute("SUB_SECTION_SEQ_NO") != null) {
				if (strSubSectionSeqNo == null || "".equals(strSubSectionSeqNo)) {
					strSubSectionSeqNo = (String) objSession
							.getAttribute("SUB_SECTION_SEQ_NO");
					objSession.setAttribute("SUB_SECTION_SEQ_NO",
							strSubSectionSeqNo);
					subSectionSeqNo = Integer.parseInt(strSubSectionSeqNo);
					objCompMapMaintForm.setSubSectionSeqNo(subSectionSeqNo);
					}

			}
			
			specTypeNo = objCompMapMaintForm.getSpecTypeNo();
			modleSeqNo = objCompMapMaintForm.getModelSeqNo();
			sectionSeqNo = objCompMapMaintForm.getSectionSeqNo();
			subSectionSeqNo = objCompMapMaintForm.getSubSectionSeqNo();
			compGrpSeqNo = objCompMapMaintForm.getCompGrpSeqNo();
			compGrpTypeSeqNo = objCompMapMaintForm.getCompgrpCat();
//			CR_83 lines ends here
			ModelVo objModelVo = new ModelVo();
			ModelBI objModelBo = ServiceFactory.getModelBO();

			CompGroupVO objCompGrpVo = new CompGroupVO();
			ModelCompGroupBI objCompGrpBo = ServiceFactory
					.getModelCompGroupBO();

			SectionVO objSectionVo = new SectionVO();
			SectionBI objSectionBo = ServiceFactory.getSectionBO();

			SubSectionVO objSubSectionVo = new SubSectionVO();
			ModelSubSectionBI objModelSubSectionBo = ServiceFactory
					.getSubSecMaintBO();

			ArrayList arlModelList = new ArrayList();
			ArrayList arlCompGrpList = new ArrayList();
			//CR_83 commented 
			//ArrayList arlSectionList = new ArrayList();
			ArrayList arlSubSectionList = new ArrayList();

			ArrayList arlSpec = new ArrayList();
//			CR_83 commented 
			//SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();

			String strUserID = "";

			LoginVO objLoginVo = (LoginVO) objSession
					.getAttribute(ApplicationConstants.USER_IN_SESSION);

			// Getting User from the session
			if (objLoginVo != null) {

				strUserID = objLoginVo.getUserID();
			}

			/**
			 * **Added for CR-46 PM&I Spec for loading Specification Type Drop
			 * Down - Starts Here***
			 */

			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);

			objCompMapMaintForm.setSpecTypeList(arlSpec);
			objCompMapMaintForm.setSpecTypeNo(objCompMapMaintForm
					.getSpecTypeNo());
			/**
			 * **Added for CR-46 PM&I Spec for loading Specification Type Drop
			 * Down - Ends Here***
			 */
if(specTypeNo!=-1 && specTypeNo!=0){
			// To get Models
			objModelVo.setUserID(strUserID);

			// Added for CR-46 PM&I Spec to load Models based on Spec Type
			objModelVo.setSpecTypeSeqNo(objCompMapMaintForm.getSpecTypeNo());// Added
			// for
			// CR-46
			// PM&I

			arlModelList = objModelBo.fetchModels(objModelVo);

			if (arlModelList != null && arlModelList.size() > 0) {
				objCompMapMaintForm.setModelList(arlModelList);
				objCompMapMaintForm.setHdnSelModel(objCompMapMaintForm
						.getHdnSelModel());
				objCompMapMaintForm.setHdnSelSec(objCompMapMaintForm
						.getHdnSelSec());
				objCompMapMaintForm.setHdnSelModel(objCompMapMaintForm
						.getHdnSelModel());
			}
}

			// To fetch Component Groups
			objCompGrpVo.setUserID(strUserID);

			// Added For CR_81 on 28-Dec-09 by RR68151 to fetch Component Group
			// Types
			ArrayList arlCompGrpType = new ArrayList();
			arlCompGrpType = objCompGrpBo.fetchCompGrpTypes(objCompGrpVo);
			if (arlCompGrpType.size() != 0) {
				objCompMapMaintForm.setCompGroupTypeList(arlCompGrpType);
			}
			
//				//Added For CR_97 on 15 march 2011 by SD 41630 Start here 
				if (objCompMapMaintForm.getCompgrpCat()!= null && !("".equals(objCompMapMaintForm.getCompgrpCat())))
				objCompGrpVo.setCompGrpTypeSeqNo(Integer
						.parseInt(objCompMapMaintForm.getCompgrpCat()));
			else
				objCompGrpVo
						.setCompGrpTypeSeqNo(ApplicationConstants.COMPGRPTYPE_ZERO);
//				CR_97 code Lines ends here 
				objCompGrpVo.setSpecTypeSeqNo(objCompMapMaintForm
						.getSpecTypeNo());
			
			// Added For CR_81 on 28-Dec-09 by RR68151 - Ends here

			arlCompGrpList = objCompGrpBo.fetchCompGroups(objCompGrpVo);

			if (arlCompGrpList != null && arlCompGrpList.size() > 0) {
				objCompMapMaintForm.setCompGrpList(arlCompGrpList);
			}
			if(modleSeqNo!=-1 && modleSeqNo!=0){
			// To get Sections
			objSectionVo.setModelSeqNo(objCompMapMaintForm.getModelSeqNo());
			objSectionVo.setUserID(strUserID);

			arlSectionList = objSectionBo.fetchSections(objSectionVo);

			if (arlSectionList != null && arlSectionList.size() > 0) {
				objCompMapMaintForm.setSectionList(arlSectionList);
			}
			}
			if(sectionSeqNo!=-1 && sectionSeqNo!=0){

			// To get SubSections
			objSubSectionVo.setModelSeqNo(objCompMapMaintForm.getModelSeqNo());
			objSubSectionVo.setSecSeqNo(objCompMapMaintForm.getSectionSeqNo());
			objSubSectionVo.setUserID(strUserID);

			arlSubSectionList = objModelSubSectionBo
					.fetchSubSections(objSubSectionVo);

			if (arlSubSectionList != null && arlSubSectionList.size() > 0) {
				objCompMapMaintForm.setSubSectionList(arlSubSectionList);
			}
			}
		} catch (Exception objExp) {
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);

	}

	/***************************************************************************
	 * This Method is used to fetch the Component Details For Selected
	 * Model,Section, SubSection and Component Group
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
	public ActionForward fetchCompMap(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
			throws DataAccessException, ApplicationException {

		LogUtil.logMessage("Entering CompMapMaintAction.fetchCompMap");
		String strForward = ApplicationConstants.SUCCESS;
		CompMapMaintForm objCompMapMaintForm = (CompMapMaintForm) objActionForm;
		
		String strSpecTypeNo = null;
		String strModleSeqNo = null;
		String strSectionSeqNo = null;
		String strSubSectionSeqNo = null;
		int specTypeNo=0;//Added for CR_97
		try {

			HttpSession objSession = objHttpServletRequest.getSession(false);

			ModelVo objModelVo = new ModelVo();
			ModelBI objModelBo = ServiceFactory.getModelBO();

			CompGroupVO objCompGrpVo = new CompGroupVO();
			ModelCompGroupBI objCompGrpBo = ServiceFactory
					.getModelCompGroupBO();

			SectionVO objSectionVo = new SectionVO();
			SectionBI objSectionBo = ServiceFactory.getSectionBO();

			SubSectionVO objSubSectionVo = new SubSectionVO();
			ModelSubSectionBI objModelSubSectionBo = ServiceFactory
					.getSubSecMaintBO();

			ModelCompMapBI objModelCompMapBo = ServiceFactory
					.getModelCompMapBO();

			ArrayList arlModelList = new ArrayList();
			ArrayList arlCompGrpList = new ArrayList();
			ArrayList arlSectionList = new ArrayList();
			ArrayList arlSubSectionList = new ArrayList();

			ArrayList arlCompList = new ArrayList();

			ArrayList arlSpec = new ArrayList();
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();

			ComponentVO objComponentVo = new ComponentVO();

			String strUserID = "";

			LoginVO objLoginVo = (LoginVO) objSession
					.getAttribute(ApplicationConstants.USER_IN_SESSION);

			// Getting User from the session
			if (objLoginVo != null) {

				strUserID = objLoginVo.getUserID();
			}

			/**
			 * **Added for CR-46 PM&I Spec for loading Specification Type Drop
			 * Down - Starts Here***
			 */

			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);

			objCompMapMaintForm.setSpecTypeList(arlSpec);
			objCompMapMaintForm.setSpecTypeNo(objCompMapMaintForm
					.getSpecTypeNo());
			//Added for CR_97
			specTypeNo=objCompMapMaintForm.getSpecTypeNo();
			//CR_97 Ends here
			/**
			 * **Added for CR-46 PM&I Spec for loading Specification Type Drop
			 * Down - Ends Here***
			 */

			// To get Models
			objModelVo.setUserID(strUserID);

			// Added for CR-46 PM&I Spec to load Models based on Spec Type
			objModelVo.setSpecTypeSeqNo(objCompMapMaintForm.getSpecTypeNo());// Added
			// for
			// CR-46
			// PM&I

			arlModelList = objModelBo.fetchModels(objModelVo);

			if (arlModelList != null && arlModelList.size() > 0) {
				objCompMapMaintForm.setModelList(arlModelList);
			}

			// To fetch Component Groups
			objCompGrpVo.setUserID(strUserID);

			// Added For CR_81 on 28-Dec-09 by RR68151 to fetch Component Group
			// Types
			ArrayList arlCompGrpType = new ArrayList();
			arlCompGrpType = objCompGrpBo.fetchCompGrpTypes(objCompGrpVo);
			if (arlCompGrpType.size() != 0) {
				objCompMapMaintForm.setCompGroupTypeList(arlCompGrpType);
			}
			if (objCompMapMaintForm.getCompgrpCat() != null)
				objCompGrpVo.setCompGrpTypeSeqNo(Integer
						.parseInt(objCompMapMaintForm.getCompgrpCat()));
			else
				objCompGrpVo
						.setCompGrpTypeSeqNo(ApplicationConstants.COMPGRPTYPE_ZERO);

			LogUtil.logMessage("ComponentGroupTypeSeqNo : "
					+ objCompGrpVo.getCompGrpTypeSeqNo());
			// Added For CR_81 on 28-Dec-09 by RR68151 - Ends here
			//Added for CR_97
			objCompGrpVo.setSpecTypeSeqNo(specTypeNo);
			//CR_97 Ends here
			
			arlCompGrpList = objCompGrpBo.fetchCompGroups(objCompGrpVo);

			if (arlCompGrpList != null && arlCompGrpList.size() > 0) {
				objCompMapMaintForm.setCompGrpList(arlCompGrpList);
			}

			// To get Sections
			objSectionVo.setModelSeqNo(objCompMapMaintForm.getModelSeqNo());
			objSectionVo.setUserID(strUserID);

			arlSectionList = objSectionBo.fetchSections(objSectionVo);

			if (arlSectionList != null && arlSectionList.size() > 0) {
				objCompMapMaintForm.setSectionList(arlSectionList);
			}

			// To get SubSections
			objSubSectionVo.setModelSeqNo(objCompMapMaintForm.getModelSeqNo());
			objSubSectionVo.setSecSeqNo(objCompMapMaintForm.getSectionSeqNo());
			objSubSectionVo.setUserID(strUserID);

			arlSubSectionList = objModelSubSectionBo
					.fetchSubSections(objSubSectionVo);

			if (arlSubSectionList != null && arlSubSectionList.size() > 0) {
				objCompMapMaintForm.setSubSectionList(arlSubSectionList);
			}

			// To get Components
			objComponentVo.setModelSeqNo(objCompMapMaintForm.getModelSeqNo());
			objComponentVo.setComponentGroupSeqNo(objCompMapMaintForm
					.getCompGrpSeqNo());
			objComponentVo.setSubSectionSeqNo(objCompMapMaintForm
					.getSubSectionSeqNo());
			objComponentVo.setUserID(strUserID);
			arlCompList = objModelCompMapBo.fetchCompMap(objComponentVo);

			/* This Piece of Code is Modified For Valid Flag Change Request */
			//Edited for CR_118 ,Display in COC flag
			if (arlCompList != null && arlCompList.size() > 0) {

				if (arlCompList.size() > 2) {
					objCompMapMaintForm.setCompList((ArrayList) arlCompList
							.get(0));
					objCompMapMaintForm.setValidFlag((String) arlCompList
							.get(1));
					objCompMapMaintForm.setDisplayCOCFlag((String) arlCompList
							.get(2));
					
				} else {
					objCompMapMaintForm.setValidFlag((String) arlCompList
							.get(0));
					objCompMapMaintForm.setDisplayCOCFlag((String) arlCompList
							.get(1));
				}
			}

			objCompMapMaintForm.setHdnSelModel(objCompMapMaintForm
					.getHdnSelModel());
			objCompMapMaintForm
					.setHdnSelSec(objCompMapMaintForm.getHdnSelSec());
			objCompMapMaintForm.setHdnSelSubSec(objCompMapMaintForm
					.getHdnSelSubSec());
			objCompMapMaintForm.setHdnSelCompGrp(objCompMapMaintForm
					.getHdnSelCompGrp());
			objCompMapMaintForm.setHdnSelSpecType(objCompMapMaintForm
					.getHdnSelSpecType());
			//CR_83 holding the value from form in the sesiion 
						
			//objModelMaintForm.setSpecTypeNo(specTypeNo);
			strSubSectionSeqNo=(String) objHttpServletRequest.getParameter("subSectionSeqNo");
			objSession.setAttribute("SUB_SECTION_SEQ_NO",strSubSectionSeqNo);
				//CR_83 lines are ends here		
		} catch (Exception objExp) {
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);

	}

	/***************************************************************************
	 * This Method is used to delete existing component mappings and to insert
	 * new Component Mappings For Selected Model,Section, SubSection and
	 * Component Group
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
	public ActionForward updateCompMap(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
			throws DataAccessException, ApplicationException {

		LogUtil.logMessage("Entering CompMapMaintAction.updateCompMap");
		String strForward = ApplicationConstants.SUCCESS;
		CompMapMaintForm objCompMapMaintForm = (CompMapMaintForm) objActionForm;

		String[] applyToModelFlagArray = null;
		String[] hdnCompValuesArray = null;
		String[] hdndefaultFlagArray = null;

		ArrayList arlCompVoList = new ArrayList();
		int intUpdateCompMap = 0;
		ComponentVO objComponentVo;
		try {

			HttpSession objSession = objHttpServletRequest.getSession(false);

			ModelVo objModelVo = new ModelVo();
			ModelBI objModelBo = ServiceFactory.getModelBO();

			CompGroupVO objCompGrpVo = new CompGroupVO();
			ModelCompGroupBI objCompGrpBo = ServiceFactory
					.getModelCompGroupBO();

			SectionVO objSectionVo = new SectionVO();
			SectionBI objSectionBo = ServiceFactory.getSectionBO();

			SubSectionVO objSubSectionVo = new SubSectionVO();
			ModelSubSectionBI objModelSubSectionBo = ServiceFactory
					.getSubSecMaintBO();

			ModelCompMapBI objModelCompMapBo = ServiceFactory
					.getModelCompMapBO();

			ArrayList arlModelList = new ArrayList();
			ArrayList arlCompGrpList = new ArrayList();
			ArrayList arlSectionList = new ArrayList();
			ArrayList arlSubSectionList = new ArrayList();

			ArrayList arlCompList = new ArrayList();

			ArrayList arlSpec = new ArrayList();
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();

			String strUserID = "";

			LoginVO objLoginVo = (LoginVO) objSession
					.getAttribute(ApplicationConstants.USER_IN_SESSION);

			// Getting User from the session
			if (objLoginVo != null) {

				strUserID = objLoginVo.getUserID();
			}

			/**
			 * **Added for CR-46 PM&I Spec for loading Specification Type Drop
			 * Down - Starts Here***
			 */

			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);

			objCompMapMaintForm.setSpecTypeList(arlSpec);
			objCompMapMaintForm.setSpecTypeNo(objCompMapMaintForm
					.getSpecTypeNo());

			/**
			 * **Added for CR-46 PM&I Spec for loading Specification Type Drop
			 * Down - Ends Here***
			 */

			// To get Models
			objModelVo.setUserID(strUserID);

			// Added for CR-46 PM&I Spec to load Models based on Spec Type
			objModelVo.setSpecTypeSeqNo(objCompMapMaintForm.getSpecTypeNo());

			arlModelList = objModelBo.fetchModels(objModelVo);

			if (arlModelList != null && arlModelList.size() > 0) {
				objCompMapMaintForm.setModelList(arlModelList);
				objCompMapMaintForm.setHdnSelModel(objCompMapMaintForm
						.getHdnSelModel());
			}

			// To fetch Component Groups
			objCompGrpVo.setUserID(strUserID);

			// Added For CR_81 on 28-Dec-09 by RR68151 to fetch Component Group
			// Types
			ArrayList arlCompGrpType = new ArrayList();
			arlCompGrpType = objCompGrpBo.fetchCompGrpTypes(objCompGrpVo);
			if (arlCompGrpType.size() != 0) {
				objCompMapMaintForm.setCompGroupTypeList(arlCompGrpType);
			}
			if (objCompMapMaintForm.getCompgrpCat() != null)
				objCompGrpVo.setCompGrpTypeSeqNo(Integer
						.parseInt(objCompMapMaintForm.getCompgrpCat()));
			else
				objCompGrpVo
						.setCompGrpTypeSeqNo(ApplicationConstants.COMPGRPTYPE_ZERO);

			LogUtil.logMessage("ComponentGroupTypeSeqNo : "
					+ objCompGrpVo.getCompGrpTypeSeqNo());
			// Added For CR_81 on 28-Dec-09 by RR68151 - Ends here
			//Added for CR_97
			objCompGrpVo.setSpecTypeSeqNo(objCompMapMaintForm.getSpecTypeNo());
			//CR_97 Ends here
			arlCompGrpList = objCompGrpBo.fetchCompGroups(objCompGrpVo);

			if (arlCompGrpList != null && arlCompGrpList.size() > 0) {
				objCompMapMaintForm.setCompGrpList(arlCompGrpList);
			}

			// To get Sections
			objSectionVo.setModelSeqNo(objCompMapMaintForm.getModelSeqNo());
			objSectionVo.setUserID(strUserID);

			arlSectionList = objSectionBo.fetchSections(objSectionVo);

			if (arlSectionList != null && arlSectionList.size() > 0) {
				objCompMapMaintForm.setSectionList(arlSectionList);
			}

			// To get SubSections
			objSubSectionVo.setModelSeqNo(objCompMapMaintForm.getModelSeqNo());
			objSubSectionVo.setSecSeqNo(objCompMapMaintForm.getSectionSeqNo());
			objSubSectionVo.setUserID(strUserID);

			arlSubSectionList = objModelSubSectionBo
					.fetchSubSections(objSubSectionVo);

			if (arlSubSectionList != null && arlSubSectionList.size() > 0) {
				objCompMapMaintForm.setSubSectionList(arlSubSectionList);
			}

			// To Update Component Mappings
			if (objHttpServletRequest.getParameterValues("hdnApplyModel") != null) {
				applyToModelFlagArray = objHttpServletRequest
						.getParameterValues("hdnApplyModel");
			}

			if (objHttpServletRequest.getParameterValues("hdnCompValues") != null) {
				hdnCompValuesArray = objHttpServletRequest
						.getParameterValues("hdnCompValues");
			}

			if (objHttpServletRequest.getParameterValues("hdnDefaultFlag") != null) {
				hdndefaultFlagArray = objHttpServletRequest
						.getParameterValues("hdnDefaultFlag");
			}

			//Added for CR_118
			LogUtil.logMessage("Display in COC flag in Action "+objCompMapMaintForm.getDisplayCOCFlag());
			if(objCompMapMaintForm.getDisplayCOCFlag()!=null){
				arlCompVoList.add(objCompMapMaintForm.getDisplayCOCFlag());
			}else{
				arlCompVoList.add("");
			}
			//Added for CR_118 Ends
			
			for (int i = 0; i < applyToModelFlagArray.length; i++) {
				if (hdnCompValuesArray[i].equals("1")) {

					objComponentVo = new ComponentVO();
					objComponentVo.setModelSeqNo(objCompMapMaintForm
							.getModelSeqNo());
					objComponentVo.setComponentSeqNo(Integer
							.parseInt(applyToModelFlagArray[i]));

					objComponentVo.setComponentGroupSeqNo(objCompMapMaintForm
							.getCompGrpSeqNo());
					objComponentVo.setSubSectionSeqNo(objCompMapMaintForm
							.getSubSectionSeqNo());
					if (hdndefaultFlagArray[i].equals("Y")) {
						objComponentVo.setDefaultFlag(true);
					} else {
						objComponentVo.setDefaultFlag(false);
					}
					objComponentVo.setUserID(strUserID);
					//Added for CR_127
					LogUtil.logMessage("Display in Valid flag in Action "+objCompMapMaintForm.getValidFlag());
					objComponentVo.setValidationFlag(objCompMapMaintForm
							.getValidFlag());
					arlCompVoList.add(objComponentVo);
				}
			}

			intUpdateCompMap = objModelCompMapBo.updateCompMap(arlCompVoList);

			// To get Components
			objComponentVo = new ComponentVO();
			objComponentVo.setModelSeqNo(objCompMapMaintForm.getModelSeqNo());
			objComponentVo.setComponentGroupSeqNo(objCompMapMaintForm
					.getCompGrpSeqNo());
			objComponentVo.setSubSectionSeqNo(objCompMapMaintForm
					.getSubSectionSeqNo());
			objComponentVo.setUserID(strUserID);
			arlCompList = objModelCompMapBo.fetchCompMap(objComponentVo);

			/* This Piece of Code is Modified For Valid Flag Change Request */
			if (arlCompList != null && arlCompList.size() > 0) {

				if (arlCompList.size() > 1) {
					objCompMapMaintForm.setCompList((ArrayList) arlCompList
							.get(0));
					objCompMapMaintForm.setValidFlag((String) arlCompList
							.get(1));
				} else {
					objCompMapMaintForm.setValidFlag((String) arlCompList
							.get(0));
				}
			}

			if (intUpdateCompMap == 0) {
				objCompMapMaintForm
						.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objCompMapMaintForm.setMessageID("" + intUpdateCompMap);
			}

		} catch (Exception objExp) {
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);

	}

	/***************************************************************************
	 * This method is for loading the Unmap Component Group Screen
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

	public ActionForward viewCompGrpMapping(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
			throws ApplicationException {

		LogUtil.logMessage("Entering CompMapMaintAction.viewCompGrpMapping");
		String strForwardKey = ApplicationConstants.UNMAPCOMPONENT;
		CompMapMaintForm objCompMapMaintForm = (CompMapMaintForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
				.getAttribute(ApplicationConstants.USER_IN_SESSION);
		
		String strUserID = null;
		ComponentVO objComponentVO = new ComponentVO();
		ArrayList arlCompGrpDtls = new ArrayList();
		int specTypeNo = -1;
		int modelSeqNo = -1;
		int sectionSeqNo = -1;
		int subSectionSeqNo = -1;
		int compGrpSeqNo = -1;
		String compGrpTypeSeqNo = null;
		String strSpecTypeNo = null;
		String strModleSeqNo = null;
		String strSectionSeqNo = null;
		String strSubSectionSeqNo = null;
		String compSourceFlag =null;
		// Added for CR_80 CR Form Enhancements III on 25-Nov-09 by RR68151
		// CR_83 lines area started here
		
		strSpecTypeNo=(String) objHttpServletRequest.getParameter("specTypeNo");
		strModleSeqNo=(String) objHttpServletRequest.getParameter("modelSeqNo");
		strSectionSeqNo=(String) objHttpServletRequest.getParameter("sectionSeqNo");
		strSubSectionSeqNo=(String) objHttpServletRequest.getParameter("subSectionSeqNo");
		objSession.setAttribute("SPEC_TYPE_NO",strSpecTypeNo);
		objSession.setAttribute("MODEL_SEQ_NO",strModleSeqNo);
		objSession.setAttribute("SECTION_SEQ_NO",strSectionSeqNo);
		objSession.setAttribute("SUB_SECTION_SEQ_NO",strSubSectionSeqNo);
		specTypeNo=Integer.parseInt(strSpecTypeNo);
		modelSeqNo=Integer.parseInt(strModleSeqNo);
		sectionSeqNo=Integer.parseInt(strSectionSeqNo);
		subSectionSeqNo=Integer.parseInt(strSubSectionSeqNo);
		objCompMapMaintForm.setSpecTypeNo(specTypeNo);
		objCompMapMaintForm.setModelSeqNo(modelSeqNo);
		objCompMapMaintForm.setSectionSeqNo(sectionSeqNo);
		objCompMapMaintForm.setSubSectionSeqNo(subSectionSeqNo);
		
		
		 specTypeNo = objCompMapMaintForm.getSpecTypeNo();
		 modelSeqNo = objCompMapMaintForm.getModelSeqNo();
		 sectionSeqNo = objCompMapMaintForm.getSectionSeqNo();
		 subSectionSeqNo = objCompMapMaintForm.getSubSectionSeqNo();
		 
		int intCompSeqNo = 0;
		try {

			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();

			}
			objCompMapMaintForm.setSpecTypeNo(objCompMapMaintForm
					.getSpecTypeNo());
			objCompMapMaintForm.setSectionSeqNo(objCompMapMaintForm
					.getSectionSeqNo());
			objCompMapMaintForm.setSubSectionSeqNo(objCompMapMaintForm
					.getSubSectionSeqNo());

			objCompMapMaintForm.setHdnSelSpecType(objCompMapMaintForm
					.getHdnSelSpecType());
			objCompMapMaintForm.setHdnSelModel(objCompMapMaintForm
					.getHdnSelModel());
			objCompMapMaintForm.setHdnSelCompGrp(objCompMapMaintForm
					.getHdnSelCompGrp());

			objCompMapMaintForm.setModelSeqNo(objCompMapMaintForm
					.getModelSeqNo());
			objCompMapMaintForm.setCompGrpSeqNo(objCompMapMaintForm
					.getCompGrpSeqNo());

			objComponentVO.setModelSeqNo(objCompMapMaintForm.getModelSeqNo());
			objComponentVO.setComponentGroupSeqNo(objCompMapMaintForm
					.getCompGrpSeqNo());
			objComponentVO.setUserID(strUserID);

			objComponentVO.setComponentSeqNo(intCompSeqNo);
			
			//Added For CR_93 getting from model only 	
			compSourceFlag = objCompMapMaintForm.getCompSourceFlag();
			objComponentVO.setCompSourceFlag(compSourceFlag);
			
			LogUtil.logMessage("**CompMapMaintAction.viewCompGrpMapping:compSourceFlag "+
					compSourceFlag);

			ModelCompMapBI objModelCompMapBO = ServiceFactory.getModelCompMapBO();					
			arlCompGrpDtls = objModelCompMapBO.fetchCompClauseMapDtls(objComponentVO);
			
			if (arlCompGrpDtls.size() == 0) {

				objCompMapMaintForm.setSpecTypeNo(specTypeNo);
				objCompMapMaintForm.setModelSeqNo(modelSeqNo);
				objCompMapMaintForm.setSectionSeqNo(sectionSeqNo);
				objCompMapMaintForm.setSubSectionSeqNo(subSectionSeqNo);

				objCompMapMaintForm
						.setMethod(ApplicationConstants.UNMAPCOMPONENT);
				objCompMapMaintForm.setHdnSelSec("");
				objCompMapMaintForm.setHdnSelSubSec("");
				objCompMapMaintForm.setValidFlag("");
				objCompMapMaintForm.setSubSectionSeqNo(0);

			}

			if (arlCompGrpDtls != null && arlCompGrpDtls.size() > 0) {

				objCompMapMaintForm.setCompGrpList(arlCompGrpDtls);

				CompGroupVO objjCompGrpVO = (CompGroupVO) arlCompGrpDtls.get(0);

				objCompMapMaintForm.setSpecTypeNo(specTypeNo);
				objCompMapMaintForm.setModelSeqNo(modelSeqNo);
				objCompMapMaintForm.setSectionSeqNo(sectionSeqNo);

				objCompMapMaintForm.setHdnSelSec(objjCompGrpVO.getSecCode()
						+ "." + objjCompGrpVO.getSectionName());
				objCompMapMaintForm.setHdnSelSubSec(objjCompGrpVO.getSecCode()
						+ "." + objjCompGrpVO.getSubSectionCode() + "."
						+ objjCompGrpVO.getSubSectionName());
				objCompMapMaintForm.setValidFlag(objjCompGrpVO.getLeadFlag());
				objCompMapMaintForm.setSectionSeqNo(objjCompGrpVO.getSectionSeqNo());//Added For CR_93
				objCompMapMaintForm.setSubSectionSeqNo(objjCompGrpVO
						.getSubSectionSeqNo());
				LogUtil.logMessage("SubSection Seq No"
						+ objCompMapMaintForm.getSubSectionSeqNo());

				objCompMapMaintForm.setCompGrpSeqNo(objjCompGrpVO
						.getComponentGroupSeqNo());
				//Added to Display No Order Components for CR_93
				if (objjCompGrpVO.getComponent().size() == 0 || objjCompGrpVO.getComponent() == null)		
					objCompMapMaintForm.setMethod(ApplicationConstants.COPY_ORDER_COMPONENT);
				
				// Contains ArrayList of ComponentVO's
				objCompMapMaintForm.setCompList(objjCompGrpVO.getComponent());
			}
			//Added For CR_93
			if("O".equalsIgnoreCase(compSourceFlag)){
				 strForwardKey = ApplicationConstants.COPY_ORDER_COMPONENT;
			}
			
		} catch (Exception objExp) {

			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}

		LogUtil.logMessage("strForwardKey :" + strForwardKey);

		return objActionMapping.findForward(strForwardKey);

	}

	/***************************************************************************
	 * This method is for Unmapping the Component Group
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

	public ActionForward unMapComponentGroup(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
			throws ApplicationException {

		LogUtil.logMessage("Entering CompMapMaintAction.unMapComponentGroup");

		String strForwardKey = ApplicationConstants.UNMAPCOMPONENT;
		CompMapMaintForm objCompMapMaintForm = (CompMapMaintForm) objActionForm;

		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
				.getAttribute(ApplicationConstants.USER_IN_SESSION);

		ComponentVO objComponentVO = new ComponentVO();
		int intSuccess = 0;
		String strUserID = null;

		ArrayList arlCompGrpDtls = new ArrayList();
		try {
			if (objLoginVo.getUserID() != null) {
				strUserID = objLoginVo.getUserID();
			}

			objCompMapMaintForm.setHdnSelSpecType(objCompMapMaintForm
					.getHdnSelSpecType());
			objCompMapMaintForm.setHdnSelModel(objCompMapMaintForm
					.getHdnSelModel());
			objCompMapMaintForm.setHdnSelCompGrp(objCompMapMaintForm
					.getHdnSelCompGrp());
			objCompMapMaintForm.setModelSeqNo(objCompMapMaintForm
					.getModelSeqNo());

			objCompMapMaintForm.setCompGrpSeqNo(objCompMapMaintForm
					.getCompGrpSeqNo());

			objComponentVO.setModelSeqNo(objCompMapMaintForm.getModelSeqNo());
			objComponentVO.setComponentGroupSeqNo(objCompMapMaintForm
					.getCompGrpSeqNo());
			objComponentVO.setSubSectionSeqNo(objCompMapMaintForm
					.getSubSectionSeqNo());
			objComponentVO.setUserID(strUserID);

			ModelCompMapBI objModelCompMapBO = ServiceFactory
					.getModelCompMapBO();
			intSuccess = objModelCompMapBO.unMapComponentGrp(objComponentVO);

			if (intSuccess == 0) {
				objCompMapMaintForm
						.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);

			} else {
				objCompMapMaintForm.setMessageID("" + intSuccess);
			}

			LogUtil.logMessage("Message" + objCompMapMaintForm.getMessageID());

			objComponentVO.setModelSeqNo(objCompMapMaintForm.getModelSeqNo());
			objComponentVO.setComponentGroupSeqNo(objCompMapMaintForm
					.getCompGrpSeqNo());
			objComponentVO.setUserID(strUserID);

			objModelCompMapBO = ServiceFactory.getModelCompMapBO();
			arlCompGrpDtls = objModelCompMapBO
					.fetchCompClauseMapDtls(objComponentVO);

			if (arlCompGrpDtls != null && arlCompGrpDtls.size() > 0) {

				objCompMapMaintForm.setCompGrpList(arlCompGrpDtls);

				CompGroupVO objjCompGrpVO = (CompGroupVO) arlCompGrpDtls.get(0);

				objCompMapMaintForm.setHdnSelSec(objjCompGrpVO.getSecCode()
						+ "." + objjCompGrpVO.getSectionName());
				objCompMapMaintForm.setHdnSelSubSec(objjCompGrpVO.getSecCode()
						+ "." + objjCompGrpVO.getSubSectionCode() + "."
						+ objjCompGrpVO.getSubSectionName());
				objCompMapMaintForm.setValidFlag(objjCompGrpVO.getLeadFlag());
				objCompMapMaintForm.setSubSectionSeqNo(objjCompGrpVO
						.getSubSectionSeqNo());
				LogUtil.logMessage("SubSection Seq No"
						+ objCompMapMaintForm.getSubSectionSeqNo());

				objCompMapMaintForm.setCompGrpSeqNo(objjCompGrpVO
						.getComponentGroupSeqNo());
			}

			if (arlCompGrpDtls.size() == 0) {

				objCompMapMaintForm.setHdnSelSec("");
				objCompMapMaintForm.setHdnSelSubSec("");
				objCompMapMaintForm.setValidFlag("");
				objCompMapMaintForm.setSubSectionSeqNo(0);

			}

		} catch (Exception objExp) {

			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}

		LogUtil.logMessage("strForwardKey :" + strForwardKey);

		return objActionMapping.findForward(strForwardKey);

	}

	// Added For CR_81 Locomotive and Power Products Enhancements
	/***************************************************************************
	 * This method is for fetching the component groups based on Component Group
	 * Type
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

	public ActionForward fetchCompGroups(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
			throws DataAccessException, ApplicationException {

		LogUtil.logMessage("Entering CompMapMaintAction:fetchCompGroups");
		String strForwardKey = ApplicationConstants.SUCCESS;
		CompMapMaintForm objCompMapMaintForm = (CompMapMaintForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
				.getAttribute(ApplicationConstants.USER_IN_SESSION);
		try {

			ModelVo objModelVo = new ModelVo();
			ModelBI objModelBo = ServiceFactory.getModelBO();
			CompGroupVO objCompGroupVO = new CompGroupVO();

			SectionVO objSectionVo = new SectionVO();
			SectionBI objSectionBo = ServiceFactory.getSectionBO();

			SubSectionVO objSubSectionVo = new SubSectionVO();
			ModelSubSectionBI objModelSubSectionBo = ServiceFactory
					.getSubSecMaintBO();

			ArrayList arlModelList = new ArrayList();
			ArrayList arlSectionList = new ArrayList();
			ArrayList arlSubSectionList = new ArrayList();
			ArrayList arlCompGrps = new ArrayList();

			ArrayList arlSpec = new ArrayList();
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();

			String strUserID = "";

			// Getting User from the session
			if (objLoginVo != null) {

				strUserID = objLoginVo.getUserID();
			}

			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);

			objCompMapMaintForm.setSpecTypeList(arlSpec);
			objCompMapMaintForm.setSpecTypeNo(objCompMapMaintForm
					.getSpecTypeNo());

			// To get Models
			objModelVo.setUserID(strUserID);

			objModelVo.setSpecTypeSeqNo(objCompMapMaintForm.getSpecTypeNo());// Added

			arlModelList = objModelBo.fetchModels(objModelVo);

			if (arlModelList != null && arlModelList.size() > 0) {
				objCompMapMaintForm.setModelList(arlModelList);
				objCompMapMaintForm.setHdnSelModel(objCompMapMaintForm
						.getHdnSelModel());
				objCompMapMaintForm.setHdnSelSec(objCompMapMaintForm
						.getHdnSelSec());
				objCompMapMaintForm.setHdnSelModel(objCompMapMaintForm
						.getHdnSelModel());
			}

			// To fetch Component Groups
			objCompGroupVO.setUserID(strUserID);

			ArrayList arlCompGrpType = new ArrayList();
			ModelCompGroupBI objModelCompGroupBO = ServiceFactory
					.getModelCompGroupBO();
			arlCompGrpType = objModelCompGroupBO
					.fetchCompGrpTypes(objCompGroupVO);
			if (arlCompGrpType.size() != 0) {
				objCompMapMaintForm.setCompGroupTypeList(arlCompGrpType);
			}

			objCompGroupVO.setCompGrpTypeSeqNo(Integer
					.parseInt(objCompMapMaintForm.getCompgrpCat()));
			//Added For CR_97 on 15 march 2011 by SD 41630 Start here 
			objCompGroupVO.setSpecTypeSeqNo(objCompMapMaintForm
					.getSpecTypeNo());

			arlCompGrps = objModelCompGroupBO.fetchCompGroups(objCompGroupVO);
			if (arlCompGrps != null && arlCompGrps.size() > 0) {
				objCompMapMaintForm.setCompGrpList(arlCompGrps);

				
			}
			//CR_97 code Lines ends here 

			// To get Sections
			objSectionVo.setModelSeqNo(objCompMapMaintForm.getModelSeqNo());
			objSectionVo.setUserID(strUserID);

			arlSectionList = objSectionBo.fetchSections(objSectionVo);

			if (arlSectionList != null && arlSectionList.size() > 0) {
				objCompMapMaintForm.setSectionList(arlSectionList);
			}

			// To get SubSections
			objSubSectionVo.setModelSeqNo(objCompMapMaintForm.getModelSeqNo());
			objSubSectionVo.setSecSeqNo(objCompMapMaintForm.getSectionSeqNo());
			objSubSectionVo.setUserID(strUserID);

			arlSubSectionList = objModelSubSectionBo
					.fetchSubSections(objSubSectionVo);

			if (arlSubSectionList != null && arlSubSectionList.size() > 0) {
				objCompMapMaintForm.setSubSectionList(arlSubSectionList);
			}

		} catch (Exception objEx) {

			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}

		return objActionMapping.findForward(strForwardKey);
	}

	// Added For CR_81 Locomotive and Power Products Enhancements - Ends here
	
	/***************************************************************************
	 * This method is for loading the Unmap Component Group Screen
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

	public ActionForward copyCompOrdrToMdl(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
			throws ApplicationException {

		LogUtil.logMessage("Entering CompMapMaintAction.copyCompOrdrToMdl");
		String strForwardKey = ApplicationConstants.COPY_ORDER_COMPONENT;
		CompMapMaintForm objCompMapMaintForm = (CompMapMaintForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
				.getAttribute(ApplicationConstants.USER_IN_SESSION);
		
		String strUserID = null;
		ComponentVO objComponentVO = new ComponentVO();
		ArrayList arlCompGrpDtls = new ArrayList();		
		int intSuccess = 0;
		int intCompSeqNo = 0;
		
		try {

			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}		

			objComponentVO.setModelSeqNo(objCompMapMaintForm.getModelSeqNo());
			objComponentVO.setComponentGroupSeqNo(objCompMapMaintForm.getCompGrpSeqNo());
			objComponentVO.setUserID(strUserID);
			objComponentVO.setComponentSeqNo(objCompMapMaintForm.getComponentSeqNo());			
			objComponentVO.setCompSourceFlag(ApplicationConstants.CLAUSE_SOURCE_ORDER);

			ModelCompBI objModelCompBO = ServiceFactory.getModelCompBO();
			intSuccess = objModelCompBO.copyCompOrdrToMdl(objComponentVO);
			
			if (intSuccess == 0) {
				objCompMapMaintForm.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);	
				objCompMapMaintForm.setNewClaSeqNo(objComponentVO.getNewClaSeqNo());
			} else {
				objCompMapMaintForm.setMessageID("" + intSuccess);
			}
			
			//Empty the existing Component Seq No to bring all of the Order Components
			objComponentVO.setComponentSeqNo(intCompSeqNo);
			
			ModelCompMapBI objModelCompMapBO = ServiceFactory.getModelCompMapBO();
			arlCompGrpDtls = objModelCompMapBO.fetchCompClauseMapDtls(objComponentVO);
	
			if (arlCompGrpDtls.size() == 0) {
				
				objCompMapMaintForm.setSpecTypeNo(objCompMapMaintForm.getSpecTypeNo());
				objCompMapMaintForm.setModelSeqNo(objCompMapMaintForm.getModelSeqNo());
				objCompMapMaintForm.setSectionSeqNo(objCompMapMaintForm.getSectionSeqNo());
				objCompMapMaintForm.setSubSectionSeqNo(objCompMapMaintForm.getSubSectionSeqNo());		
				objCompMapMaintForm.setMethod(ApplicationConstants.UNMAPCOMPONENT);
				objCompMapMaintForm.setHdnSelSec("");
				objCompMapMaintForm.setHdnSelSubSec("");
				objCompMapMaintForm.setValidFlag("");
				objCompMapMaintForm.setSubSectionSeqNo(0);		
			}

			if (arlCompGrpDtls != null && arlCompGrpDtls.size() > 0) {
	
				objCompMapMaintForm.setCompGrpList(arlCompGrpDtls);
	
				CompGroupVO objjCompGrpVO = (CompGroupVO) arlCompGrpDtls.get(0);
	
				objCompMapMaintForm.setHdnSelSec(objjCompGrpVO.getSecCode()
						+ "." + objjCompGrpVO.getSectionName());
				objCompMapMaintForm.setHdnSelSubSec(objjCompGrpVO.getSecCode()
						+ "." + objjCompGrpVO.getSubSectionCode() + "."
						+ objjCompGrpVO.getSubSectionName());
				objCompMapMaintForm.setValidFlag(objjCompGrpVO.getLeadFlag());
				objCompMapMaintForm.setSectionSeqNo(objjCompGrpVO.getSectionSeqNo());
				objCompMapMaintForm.setSubSectionSeqNo(objjCompGrpVO.getSubSectionSeqNo());	
				objCompMapMaintForm.setCompGrpSeqNo(objjCompGrpVO.getComponentGroupSeqNo());
	
				// Contains ArrayList of ComponentVO's
				if (objjCompGrpVO.getComponent().size() == 0 || objjCompGrpVO.getComponent() == null)		
					objCompMapMaintForm.setMethod(ApplicationConstants.COPY_ORDER_COMPONENT);
				
					objCompMapMaintForm.setCompList(objjCompGrpVO.getComponent());
			}
			
		} catch (Exception objExp) {

			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}

		LogUtil.logMessage("strForwardKey :" + strForwardKey);

		return objActionMapping.findForward(strForwardKey);

	}
}
