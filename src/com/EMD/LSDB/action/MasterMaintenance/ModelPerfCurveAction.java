/**
 * 
 */
package com.EMD.LSDB.action.MasterMaintenance;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.EMD.LSDB.action.common.EMDAction;
import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.ModelBI;
import com.EMD.LSDB.bo.serviceinterface.ModelPerfCurveBI;
import com.EMD.LSDB.bo.serviceinterface.SpecTypeBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.form.MasterMaintenance.ModelGenArrangeForm;
import com.EMD.LSDB.form.MasterMaintenance.ModelPerfCurveForm;
import com.EMD.LSDB.vo.common.FileVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.PerformanceCurveVO;
import com.EMD.LSDB.vo.common.SpecTypeVo;
import com.lowagie.text.Image;
import com.EMD.LSDB.common.util.ApplicationUtil;

/**
 * @author VV49326
 *  
 */
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
public class ModelPerfCurveAction extends EMDAction {
	
	public ModelPerfCurveAction() {
		
	}
	
	/***************************************************************************
	 * * * * This Method is used to populate the Model Dropdown on PageLoad
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
		
		LogUtil.logMessage("Inside the initLoad method of GenArrangeMaintenance");
		String strForwardKey = ApplicationConstants.SUCCESS;
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		ModelPerfCurveForm objPerfCurveModelForm = (ModelPerfCurveForm) objActionForm;
		
		int specTypeNo = -1;
		int modleSeqNo = -1;
		String strSpecTypeNo = null;
		String strModleSeqNo = null;
		ArrayList arlModelList = null;
		
		
		try {
				
				
//				CR_83 lines are added ahere 
				strSpecTypeNo = (String) objHttpServletRequest
				.getParameter("specTypeNo");
		strModleSeqNo = (String) objHttpServletRequest
				.getParameter("modelSeqNo");
		
		if (strSpecTypeNo != null) {
			
			specTypeNo = Integer.parseInt(strSpecTypeNo);
			LogUtil.logMessage("value of strSpecTypeNo from  session"
					+ strSpecTypeNo);
			objPerfCurveModelForm.setSpecTypeNo(specTypeNo);
		
		} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
			if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {
					strSpecTypeNo = (String) objSession
						.getAttribute("SPEC_TYPE_NO");
				specTypeNo = Integer.parseInt(strSpecTypeNo);
				objPerfCurveModelForm.setSpecTypeNo(specTypeNo);
					}
		}

		if (strModleSeqNo != null) {

			objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
			modleSeqNo = Integer.parseInt(strModleSeqNo);
			LogUtil.logMessage("value of MODEL_SEQ_NO from session "
					+ modleSeqNo);
			objPerfCurveModelForm.setModelSeqNo(modleSeqNo);

		} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {

			if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
				strModleSeqNo = (String) objSession
						.getAttribute("MODEL_SEQ_NO");
				modleSeqNo = Integer.parseInt(strModleSeqNo);
				objPerfCurveModelForm.setModelSeqNo(modleSeqNo);
			}
		}
//		CR_84 lines are started here 
		boolean specTypeExists = false;
		SpecTypeVo objSpecTypeVo = new SpecTypeVo();
		objSpecTypeVo.setUserID(objLoginVo.getUserID());
		String strScreenId = "";
		if (objHttpServletRequest.getParameter("screenid") != null) {
			strScreenId = objHttpServletRequest
			.getParameter("screenid");
			objSpecTypeVo.setStrScreenID(strScreenId);
			
		}
		
		
		ArrayList arlSpec = new ArrayList();
		SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
		arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
		if (arlSpec.size() != 0) {
			objPerfCurveModelForm.setSpecTypeList(arlSpec);
			LogUtil.logMessage("SpecType ArrayList Size : " + arlSpec.size());
			SpecTypeVo objjSpecTypeVo = new SpecTypeVo();
			for (int i=0;i<arlSpec.size();i++)	{
			objjSpecTypeVo = (SpecTypeVo) arlSpec.get(i);
			if (specTypeNo == objjSpecTypeVo.getSpectypeSeqno()){
				LogUtil.logMessage("Boolean : " + specTypeNo);
				specTypeExists = true;
				break;
				}
			}
		}
//		CR_84 lines ends here
		/**
		 * Based on LSDB_CR-46 only Locomotive models should loaded for
		 * performance curves. so here specificationtype is forcibly set it
		 * as 1. Added on 02-Sep-08 by si50968
		 */
		//CR_84 is added LXO in the Specification type ,now locomaotive and LXO models will loaded for performance curves
		if (specTypeNo != -1 && specTypeNo != 0 && specTypeExists) {
			LogUtil.logMessage("in side modleSeqNo" + modleSeqNo);

			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			objModelVo.setSpecTypeSeqNo(specTypeNo);
			ModelBI objModelBO = ServiceFactory.getModelBO();
			if (objModelBO.fetchModels(objModelVo) != null) {
				arlModelList = objModelBO.fetchModels(objModelVo);
				objPerfCurveModelForm.setListModels(arlModelList);
				objPerfCurveModelForm.setModelSeqNo(modleSeqNo);
				LogUtil.logMessage("end of the if modleSeqNo" + modleSeqNo);
			}
			//}
		}
		
			LogUtil.logMessage("END the initLoad method of ModelGenArrangeAction");
					
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in SecMaintAction:initLoad");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***************************************************************************
	 * * * * This Method is used to populate the Model Dropdown on selection of
	 * specification type
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	/**
	 * fetchModels method is added based on LSDB_CR-46
	 * specTypeSeqVo is Populated based in all the actions based on the requirement of LSDB_CR-46
	 * Added on 28-Aug-08
	 * by ps57222
	 */
	
	public ActionForward fetchModels(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Inside the GenArrangeMaintenance:fetchModels");
		String strForwardKey = ApplicationConstants.SUCCESS;
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		ModelPerfCurveForm objPerfCurveModelForm = (ModelPerfCurveForm) objActionForm;
		int specTypeNo = -1;
		int modleSeqNo = -1;
		String strSpecTypeNo = null;
		String strModleSeqNo = null;
		ArrayList arlModelList = null;
		try {
			
			
//			CR_83 lines are added ahere 
			strSpecTypeNo = (String) objHttpServletRequest
			.getParameter("specTypeNo");
	strModleSeqNo = (String) objHttpServletRequest
			.getParameter("modelSeqNo");
	
	if (strSpecTypeNo != null) {
		objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);
		specTypeNo = Integer.parseInt(strSpecTypeNo);
		objPerfCurveModelForm.setSpecTypeNo(specTypeNo);
	} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
		if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {
				strSpecTypeNo = (String) objSession
					.getAttribute("SPEC_TYPE_NO");
			specTypeNo = Integer.parseInt(strSpecTypeNo);
			objPerfCurveModelForm.setSpecTypeNo(specTypeNo);
		}
	}

	if (strModleSeqNo != null) {

		objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
		modleSeqNo = Integer.parseInt(strModleSeqNo);
		objPerfCurveModelForm.setModelSeqNo(modleSeqNo);

	} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {

		if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
			strModleSeqNo = (String) objSession
					.getAttribute("MODEL_SEQ_NO");
			modleSeqNo = Integer.parseInt(strModleSeqNo);
			objPerfCurveModelForm.setModelSeqNo(modleSeqNo);
		}
	}
	
//	CR_84 lines are started here 
	SpecTypeVo objSpecTypeVo = new SpecTypeVo();
	objSpecTypeVo.setUserID(objLoginVo.getUserID());
	if(objLoginVo.getIntScreenId()!=0)
	{
	//objSpecTypeVo.setScreenID(objLoginVo.getIntScreenId());
	String strScreenId= Integer.toString(objLoginVo.getIntScreenId());
	objSpecTypeVo.setStrScreenID(strScreenId);
	
	}
	
	ArrayList arlSpec = new ArrayList();
	SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
	arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
	if (arlSpec.size() != 0) {
		objPerfCurveModelForm.setSpecTypeList(arlSpec);
	}
//	CR_84 lines ends here
			/**
			 * Based on LSDB_CR-46 only Locomotive models should loaded for
			 * performance curves. so here specificationtype is forcibly set it
			 * as 1. Added on 02-Sep-08 by si50968
			 */
			//CR_84 is added LXO in the Specification type ,now locomaotive and LXO models will loaded for performance curves
		if (specTypeNo != -1 && specTypeNo != 0) {
			
			LogUtil.logMessage("before the GenArrangeMaintenance:fetchModels");
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			objModelVo.setSpecTypeSeqNo(objPerfCurveModelForm.getSpecTypeNo());
			ModelBI objModelBO = ServiceFactory.getModelBO();
			if (objModelBO.fetchModels(objModelVo) != null) {
				 arlModelList = objModelBO.fetchModels(objModelVo);
				 objPerfCurveModelForm.setListModels(arlModelList);
				//objSecMaintForm.setModelSeqNo(modleSeqNo);
			}
			}
		LogUtil.logMessage("after the GenArrangeMaintenance:fetchModels");
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in SecMaintAction:fetchModels");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		
		return objActionMapping.findForward(strForwardKey);
	}
	public ActionForward initLoad1(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering PerfCurveModelAction.initLoad");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ModelPerfCurveForm objPerfCurveModelForm = (ModelPerfCurveForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
//		CR_83 lines area started here
		String strModleSeqNo = null;
		int modleSeqNo = -1;
		try {
			
			strModleSeqNo = (String) objHttpServletRequest
			.getParameter("modelSeqNo");
			if (strModleSeqNo != null) {

				objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
				modleSeqNo = Integer.parseInt(strModleSeqNo);
				LogUtil.logMessage("value of MODEL_SEQ_NO from session "
						+ modleSeqNo);
				objPerfCurveModelForm.setModelSeqNo(modleSeqNo);

			} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {

				if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
					strModleSeqNo = (String) objSession
							.getAttribute("MODEL_SEQ_NO");
					modleSeqNo = Integer.parseInt(strModleSeqNo);
					objPerfCurveModelForm.setModelSeqNo(modleSeqNo);
				}
			}
			
			//CR_83 Lines area ends here	
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			
			/**
			 * Based on LSDB_CR-46 only Locomotive models should loaded for
			 * performance curves. so here specificationtype is forcibly set it
			 * as 1. Added on 02-Sep-08 by si50968
			 */
			//Added For CR_84
			objModelVo.setSpecTypeSeqNo(objPerfCurveModelForm.getSpecTypeNo());
			ArrayList arlModelList = null;
			ModelBI objModelBo = ServiceFactory.getModelBO();
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null)
				objPerfCurveModelForm.setListModels(arlModelList);
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelPerfCurveAction..");
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
	
	/***************************************************************************
	 * * * * This Method is used to fetch Performance Curve Images
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward fetchPerfCurveImages(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Entering PerfCurveAction:fetchPerfCurveImages");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList arlImageList = null;
		
		ModelPerfCurveForm objPerfCurveModelForm = (ModelPerfCurveForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		LogUtil.logMessage(objLoginVo);
		int specTypeNo = -1;
		int modleSeqNo = -1;
		String strSpecTypeNo = null;
		String strModleSeqNo = null;
		ArrayList arlModelList = null;
		try {
			
			
//			CR_83 lines are added ahere 
			strSpecTypeNo = (String) objHttpServletRequest
			.getParameter("specTypeNo");
	strModleSeqNo = (String) objHttpServletRequest
			.getParameter("modelSeqNo");
	
	if (strSpecTypeNo != null) {
		objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);
		specTypeNo = Integer.parseInt(strSpecTypeNo);
		objPerfCurveModelForm.setSpecTypeNo(specTypeNo);
	} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
		if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {
				strSpecTypeNo = (String) objSession
					.getAttribute("SPEC_TYPE_NO");
			specTypeNo = Integer.parseInt(strSpecTypeNo);
			objPerfCurveModelForm.setSpecTypeNo(specTypeNo);
		}
	}

	if (strModleSeqNo != null) {

		objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
		modleSeqNo = Integer.parseInt(strModleSeqNo);
		objPerfCurveModelForm.setModelSeqNo(modleSeqNo);

	} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {

		if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
			strModleSeqNo = (String) objSession
					.getAttribute("MODEL_SEQ_NO");
			modleSeqNo = Integer.parseInt(strModleSeqNo);
			objPerfCurveModelForm.setModelSeqNo(modleSeqNo);
		}
	}
	
	//CR_83 lines area ends here
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			if(objLoginVo.getIntScreenId()!=0)
			{
			//objSpecTypeVo.setScreenID(objLoginVo.getIntScreenId());
			String strScreenId= Integer.toString(objLoginVo.getIntScreenId());
			objSpecTypeVo.setStrScreenID(strScreenId);
			
			}
			
			
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objPerfCurveModelForm.setSpecTypeList(arlSpec);
			}
//			CR_84 lines ends here
			/**
			 * Based on LSDB_CR-46 only Locomotive models should loaded for
			 * performance curves. so here specificationtype is forcibly set it
			 * as 1. Added on 02-Sep-08 by si50968
			 */
			//CR_84 is added LXO in the Specification type ,now locomaotive and LXO models will loaded for performance curves
		if (specTypeNo != -1 && specTypeNo != 0) {
			
			LogUtil.logMessage("before the GenArrangeMaintenance:fetchModels");
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			objModelVo.setSpecTypeSeqNo(objPerfCurveModelForm.getSpecTypeNo());
			ModelBI objModelBO = ServiceFactory.getModelBO();
			if (objModelBO.fetchModels(objModelVo) != null) {
				 arlModelList = objModelBO.fetchModels(objModelVo);
				// objPerfCurveModelForm.setListModels(arlModelList);
				//objSecMaintForm.setModelSeqNo(modleSeqNo);
				 if (arlModelList != null) {
						
						objPerfCurveModelForm.setListModels(arlModelList);
						objPerfCurveModelForm.setSelectMdl(objPerfCurveModelForm
								.getSelectMdl());
						/*
						 * To display only Upload button before Search and to display
						 * both Upload and Delete after Search
						 */
						objPerfCurveModelForm.setHdnDisp(objPerfCurveModelForm
								.getHdnDisp());
						
					}
			}
			}
		LogUtil.logMessage("after the GenArrangeMaintenance:fetchModels");
			//CR-84 for commited
			
			/*ArrayList arlModelList = null;
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			
			*//**
			 * Based on LSDB_CR-46 only Locomotive models should loaded for
			 * performance curves. so here specificationtype is forcibly set it
			 * as 1. Added on 02-Sep-08 by si50968
			 *//*
			
			objModelVo.setSpecTypeSeqNo(1);
			
			ModelBI objModelBo = ServiceFactory.getModelBO();
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null) {
				
				objPerfCurveModelForm.setListModels(arlModelList);
				objPerfCurveModelForm.setSelectMdl(objPerfCurveModelForm
						.getSelectMdl());
				
				 * To display only Upload button before Search and to display
				 * both Upload and Delete after Search
				 
				objPerfCurveModelForm.setHdnDisp(objPerfCurveModelForm
						.getHdnDisp());
				
			}*/
			
			PerformanceCurveVO objPerformanceCurveVO = new PerformanceCurveVO();
			
			objPerformanceCurveVO.setModelSeqNo(objPerfCurveModelForm
					.getModelSeqNo());
			objPerformanceCurveVO.setUserID(objLoginVo.getUserID());
			
			ModelPerfCurveBI objPerformanceCurveBO = ServiceFactory
			.getModelPerformanceCurveBO();
			arlImageList = objPerformanceCurveBO
			.fetchPerfCurveImages(objPerformanceCurveVO);
			
			if (arlImageList != null && arlImageList.size() > 0) {
				
				objPerformanceCurveVO = new PerformanceCurveVO();
				objPerfCurveModelForm.setResultList(arlImageList);
				objPerfCurveModelForm.setSelectMdl(objPerfCurveModelForm
						.getSelectMdl());
				/* To display both Upload and Delete button after Search */
				objPerfCurveModelForm.setHdnDisp(objPerfCurveModelForm
						.getHdnDisp());
				
			}
			
			if (arlImageList.size() == 0) {
				
				objPerfCurveModelForm.setSelectMdl(objPerfCurveModelForm
						.getSelectMdl());
				/*
				 * To display only Upload button before Search and on No records
				 * found after Search
				 */
				objPerfCurveModelForm.setHdnDisp(0);
				
			}
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelPerfCurveAction..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***************************************************************************
	 * * * * This Method is used to Upload Performance Curve Image
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward uploadPerfCurveImage(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException,
			FileNotFoundException, IOException {
		
		LogUtil.logMessage("Enters into PerfCurveAction:uploadImage");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intUpdated = 0;
		
		ModelPerfCurveForm objPerfCurveModelForm = (ModelPerfCurveForm) objActionForm;
		PerformanceCurveVO objPerformanceCurveVO = new PerformanceCurveVO();
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		try {
			
			/** ***** for Loading DropDown code Starts here*************** */
			ArrayList arlModelList = null;
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			boolean blnFlag = true;
			/**
			 * Based on LSDB_CR-46 only Locomotive models should loaded for
			 * performance curves. so here specificationtype is forcibly set it
			 * as 1. Added on 02-Sep-08 by si50968
			 */
//			CR_84 lines are started here 
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			if(objLoginVo.getIntScreenId()!=0)
			{
			String strScreenId= Integer.toString(objLoginVo.getIntScreenId());
			objSpecTypeVo.setStrScreenID(strScreenId);
			
			}
			
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objPerfCurveModelForm.setSpecTypeList(arlSpec);
			}
//			CR_84 lines ends here
			
			objModelVo.setSpecTypeSeqNo(objPerfCurveModelForm.getSpecTypeNo());
			
			ModelBI objModelBo = ServiceFactory.getModelBO();
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null) {
				objPerfCurveModelForm.setListModels(arlModelList);
				objPerfCurveModelForm.setSelectMdl(objPerfCurveModelForm
						.getSelectMdl());
				LogUtil.logMessage("Selected Model:"
						+ objPerfCurveModelForm.getSelectMdl());
				objPerfCurveModelForm.setHdnDisp(objPerfCurveModelForm
						.getHdnDisp());
				LogUtil.logMessage("" + objPerfCurveModelForm.getHdnDisp());
				
			}
			
			/** **************** for Loading DropDown code Ends here ********* */
			
			FormFile objFormFile = objPerfCurveModelForm.getTheFile();
			
			FileVO objFileVO = new FileVO();
			objFileVO.setFileName(objFormFile.getFileName());
			
			LogUtil.logMessage("FileName in action :"
					+ objFormFile.getFileName());
			
			//objFileVO.setuploadedFile(objFormFile.getFileData());
			objFileVO.setFileLength(objFormFile.getFileSize());
			LogUtil.logMessage("FileSize in action :"
					+ objFormFile.getFileSize());
			objFileVO.setFileStream(objFormFile.getInputStream());
			objFileVO.setContentType(objFormFile.getContentType());
			LogUtil.logMessage("ContentType in action :"
					+ objFormFile.getContentType());
			
			
			//Added for Image Validation
			if (!("application/pdf").equalsIgnoreCase(objFormFile
					.getContentType())) {
				byte byteImage[] = objPerfCurveModelForm.getTheFile()
				.getFileData();
				Image image = Image.getInstance(byteImage);
				
				float width = image.getScaledWidth();
				float height = image.getScaledHeight();
				LogUtil.logMessage("width:" + width);
				LogUtil.logMessage("height:" + height);
				LogUtil.logMessage("ContentType in action :"
						+ objFormFile.getContentType());
				if (width > 550.0 || height > 600.0) {
					
					objPerfCurveModelForm
					.setMessageID(ApplicationConstants.IMAGE_SIZE_VALIDATION);
					blnFlag = false;
				}
			}else{
				//Fixing pre-existing bug: Seding just the file name
				String strFileName = ApplicationUtil.getFileName(objFormFile.getFileName());
				objFileVO.setFileName(strFileName);
				LogUtil.logMessage("value from ApplicationUtil.getFileName method"+strFileName);
				//Ends here
				}
			objPerformanceCurveVO.setFileVO(objFileVO);
			objPerformanceCurveVO.setUserID(objLoginVo.getUserID());
			objPerformanceCurveVO.setModelSeqNo(objPerfCurveModelForm
					.getModelSeqNo());
			objPerformanceCurveVO.setCurveSeqNo(objPerfCurveModelForm
					.getCurSeqNo());
			objPerformanceCurveVO.setUserID(objLoginVo.getUserID());
			
			//Added for CR_121
			objPerformanceCurveVO.setOrderByCode(objPerfCurveModelForm.getNoOfPerfCurve());
			//Added for CR_121 Ends
			
			ModelPerfCurveBI objPerformanceCurveBO = ServiceFactory
			.getModelPerformanceCurveBO();
			
			if (blnFlag) {
				intUpdated = objPerformanceCurveBO
				.uploadPerfCurveImage(objPerformanceCurveVO);
				
				if (intUpdated == 0) {
					objPerfCurveModelForm
					.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				} else {
					objPerfCurveModelForm.setMessageID("" + intUpdated);
				}
			}
			
			objPerformanceCurveVO.setModelSeqNo(objPerfCurveModelForm
					.getModelSeqNo());
			objPerformanceCurveVO.setUserID(objLoginVo.getUserID());
			
			ArrayList objImagelist = objPerformanceCurveBO
			.fetchPerfCurveImages(objPerformanceCurveVO);
			
			if (objImagelist != null) {
				
				objPerfCurveModelForm.setResultList(objImagelist);
				objPerfCurveModelForm.setSelectMdl(objPerfCurveModelForm
						.getSelectMdl());
				/* To display both Upload and Delete button after Search */
				objPerfCurveModelForm.setHdnDisp(objPerfCurveModelForm
						.getHdnDisp());
				
			}
			
			if (objImagelist.size() == 0) {
				/* String value set for no records on search */
				objPerfCurveModelForm.setMethod("fetchPerfCurveImages");
				objPerfCurveModelForm.setSelectMdl(objPerfCurveModelForm
						.getSelectMdl());
				/*
				 * To display only Upload button before Search and on No records
				 * found after Search
				 */
				objPerfCurveModelForm.setHdnDisp(0);
				
			}
			LogUtil.logMessage("objPerfCurveModelForm.getHdnDisp()"
					+ objPerfCurveModelForm.getHdnDisp());
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelPerfCurveAction..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***************************************************************************
	 * * * * This Method is used to Delete Performance Curve Image
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward deletePerfCurveImage(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil.logMessage("Entering PerfCurveAction:deletePerfCurveImage ");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intDeleteImg = 0;
		
		ModelPerfCurveForm objPerfCurveModelForm = (ModelPerfCurveForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		LogUtil.logMessage(objLoginVo);
		
		try {
//			CR_84 lines are started here 
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			if(objLoginVo.getIntScreenId()!=0)
			{
			String strScreenId= Integer.toString(objLoginVo.getIntScreenId());
			objSpecTypeVo.setStrScreenID(strScreenId);
			
			}
			
			
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objPerfCurveModelForm.setSpecTypeList(arlSpec);
			}
//			CR_84 lines ends here
			
			ArrayList arlModelList = null;
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			
			/**
			 * Based on LSDB_CR-46 only Locomotive models should loaded for
			 * performance curves. so here specificationtype is forcibly set it
			 * as 1. Added on 02-Sep-08 by si50968
			 */
			//Added For CR_84
			objModelVo.setSpecTypeSeqNo(objPerfCurveModelForm.getSpecTypeNo());
			
			ModelBI objModelBo = ServiceFactory.getModelBO();
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null) {
				
				objPerfCurveModelForm.setListModels(arlModelList);
				objPerfCurveModelForm.setSelectMdl(objPerfCurveModelForm
						.getSelectMdl());
				/*
				 * To display only Upload button before Search and to display
				 * both Upload and Delete after Search
				 */
				objPerfCurveModelForm.setHdnDisp(objPerfCurveModelForm
						.getHdnDisp());
				
			}
			
			PerformanceCurveVO objPerformanceCurveVO = new PerformanceCurveVO();
			
			objPerformanceCurveVO.setModelSeqNo(objPerfCurveModelForm
					.getModelSeqNo());
			objPerformanceCurveVO.setCurveSeqNo(objPerfCurveModelForm
					.getCurSeqNo());
			objPerformanceCurveVO.setUserID(objLoginVo.getUserID());
			
			ModelPerfCurveBI objPerformanceCurveBO = ServiceFactory
			.getModelPerformanceCurveBO();
			intDeleteImg = objPerformanceCurveBO
			.deletePerfCurveImage(objPerformanceCurveVO);
			
			if (intDeleteImg == 0) {
				objPerfCurveModelForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objPerfCurveModelForm.setErrorMessage("" + intDeleteImg);
			}
			objPerformanceCurveVO.setModelSeqNo(objPerfCurveModelForm
					.getModelSeqNo());
			objPerformanceCurveVO.setUserID(objLoginVo.getUserID());
			
			ArrayList objImagelist = objPerformanceCurveBO
			.fetchPerfCurveImages(objPerformanceCurveVO);
			
			if (objImagelist != null && objImagelist.size() > 0) {
				
				objPerfCurveModelForm.setResultList(objImagelist);
				objPerfCurveModelForm.setSelectMdl(objPerfCurveModelForm
						.getSelectMdl());
				/* To display both Upload and Delete button after Search */
				objPerfCurveModelForm.setHdnDisp(objPerfCurveModelForm
						.getHdnDisp());
				
			}
			
			if (objImagelist.size() == 0) {
				/* String value set for no records on search */
				objPerfCurveModelForm.setMethod("fetchPerfCurveImages");
				objPerfCurveModelForm.setSelectMdl(objPerfCurveModelForm
						.getSelectMdl());
				/*
				 * To display only Upload button before Search and on No records
				 * found after Search
				 */
				objPerfCurveModelForm.setHdnDisp(0);
				
			}
			
			return objActionMapping.findForward(strForwardKey);
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelPerfCurveAction..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***************************************************************************
	 * * * * This Method is used to modify Performance Curve ImageName
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward modifyImageName(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil.logMessage("Entering PerfCurveAction:modifyImageName ");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intDeleteImg = 0;
		
		ModelPerfCurveForm objPerfCurveModelForm = (ModelPerfCurveForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		LogUtil.logMessage(objLoginVo);
		
		try {
//			CR_84 lines are started here 
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			if(objLoginVo.getIntScreenId()!=0)
			{
			String strScreenId= Integer.toString(objLoginVo.getIntScreenId());
			objSpecTypeVo.setStrScreenID(strScreenId);
			
			}
			
			
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objPerfCurveModelForm.setSpecTypeList(arlSpec);
			}
//			CR_84 lines ends here
			ArrayList arlModelList = null;
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			
			/**
			 * Based on LSDB_CR-46 only Locomotive models should loaded for
			 * performance curves. so here specificationtype is forcibly set it
			 * as 1. Added on 02-Sep-08 by si50968
			 */
			//Updated  for CR_84
			objModelVo.setSpecTypeSeqNo(objPerfCurveModelForm.getSpecTypeNo());
			
			ModelBI objModelBo = ServiceFactory.getModelBO();
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null) {
				
				objPerfCurveModelForm.setListModels(arlModelList);
				objPerfCurveModelForm.setSelectMdl(objPerfCurveModelForm
						.getSelectMdl());
				/*
				 * To display only Upload button before Search and to display
				 * both Upload and Delete after Search
				 */
				objPerfCurveModelForm.setHdnDisp(objPerfCurveModelForm
						.getHdnDisp());
				
			}
			
			PerformanceCurveVO objPerformanceCurveVO = new PerformanceCurveVO();
			
			objPerformanceCurveVO.setModelSeqNo(objPerfCurveModelForm
					.getModelSeqNo());
			objPerformanceCurveVO.setCurveSeqNo(objPerfCurveModelForm
					.getCurveSeqNo());
			objPerformanceCurveVO.setImageName(objPerfCurveModelForm
					.getHdnImageName());
			objPerformanceCurveVO.setUserID(objLoginVo.getUserID());
			
			ModelPerfCurveBI objPerformanceCurveBO = ServiceFactory
			.getModelPerformanceCurveBO();
			intDeleteImg = objPerformanceCurveBO
			.modifyPerfCurveImageName(objPerformanceCurveVO);
			
			if (intDeleteImg == 0) {
				objPerfCurveModelForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objPerfCurveModelForm.setMessageID("" + intDeleteImg);
			}
			objPerformanceCurveVO.setModelSeqNo(objPerfCurveModelForm
					.getModelSeqNo());
			objPerformanceCurveVO.setUserID(objLoginVo.getUserID());
			
			ArrayList objImagelist = objPerformanceCurveBO
			.fetchPerfCurveImages(objPerformanceCurveVO);
			
			if (objImagelist != null && objImagelist.size() > 0) {
				
				objPerfCurveModelForm.setResultList(objImagelist);
				objPerfCurveModelForm.setSelectMdl(objPerfCurveModelForm
						.getSelectMdl());
				/* To display both Upload and Delete button after Search */
				objPerfCurveModelForm.setHdnDisp(objPerfCurveModelForm
						.getHdnDisp());
				
			}
			
			if (objImagelist.size() == 0) {
				/* String value set for no records on search */
				objPerfCurveModelForm.setMethod("fetchPerfCurveImages");
				objPerfCurveModelForm.setSelectMdl(objPerfCurveModelForm
						.getSelectMdl());
				/*
				 * To display only Upload button before Search and on No records
				 * found after Search
				 */
				objPerfCurveModelForm.setHdnDisp(0);
				
			}
			
			return objActionMapping.findForward(strForwardKey);
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelPerfCurveAction:modifyImageName.");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	//Added for CR_121
	/***************************************************************************
	 * * * * This Method is used to Rearrange Performance Curve 
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward saveRearrangedPerfCurve(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Entering PerfCurveAction:saveRearrangedPerfCurve");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList arlImageList = null;
		
		ModelPerfCurveForm objPerfCurveModelForm = (ModelPerfCurveForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		LogUtil.logMessage(objLoginVo);
		int specTypeNo = -1;
		int modleSeqNo = -1;
		int intUpdated = 0;
		String strSpecTypeNo = null;
		String strModleSeqNo = null;
		ArrayList arlModelList = null;
		ArrayList perfCurveList=new ArrayList();
		
		try {
			
			ModelPerfCurveBI objPerformanceCurveBO = ServiceFactory
			.getModelPerformanceCurveBO();
			
			strSpecTypeNo = (String) objHttpServletRequest
			.getParameter("specTypeNo");
	strModleSeqNo = (String) objHttpServletRequest
			.getParameter("modelSeqNo");
	
	if (strSpecTypeNo != null) {
		objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);
		specTypeNo = Integer.parseInt(strSpecTypeNo);
		objPerfCurveModelForm.setSpecTypeNo(specTypeNo);
	} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
		if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {
				strSpecTypeNo = (String) objSession
					.getAttribute("SPEC_TYPE_NO");
			specTypeNo = Integer.parseInt(strSpecTypeNo);
			objPerfCurveModelForm.setSpecTypeNo(specTypeNo);
		}
	}

	if (strModleSeqNo != null) {

		objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
		modleSeqNo = Integer.parseInt(strModleSeqNo);
		objPerfCurveModelForm.setModelSeqNo(modleSeqNo);

	} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {

		if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
			strModleSeqNo = (String) objSession
					.getAttribute("MODEL_SEQ_NO");
			modleSeqNo = Integer.parseInt(strModleSeqNo);
			objPerfCurveModelForm.setModelSeqNo(modleSeqNo);
		}
	}
	
	PerformanceCurveVO objPerformanceCurveVOforRearrange = new PerformanceCurveVO();
	objPerformanceCurveVOforRearrange.setModelSeqNo(modleSeqNo);
	String strRowIndexList=objPerfCurveModelForm.getRowIndexList();
	String[] token = strRowIndexList.split ("\\,");
	 for (int i=0; i < token.length; i++){
		 perfCurveList.add(token[i]);
	 }
	
	String [] arlPerfCurveArray =new String[perfCurveList.size()];
	int i=0;
	for ( Iterator iter = perfCurveList.iterator(); iter.hasNext();) {
			
		String element = (String) iter.next();
		arlPerfCurveArray[i]=element;
		i++;
	}
	objPerformanceCurveVOforRearrange.setPerfCurveList(arlPerfCurveArray);
	
	objPerformanceCurveVOforRearrange.setUserID(objLoginVo.getUserID());
	intUpdated = objPerformanceCurveBO.saveRearrangedPerfCurve(objPerformanceCurveVOforRearrange);
	
	if (intUpdated == 0) {
		objPerfCurveModelForm
		.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
		
	} else {
		objPerfCurveModelForm.setMessageID("" + intUpdated);
	}
	
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			if(objLoginVo.getIntScreenId()!=0)
			{
			String strScreenId= Integer.toString(objLoginVo.getIntScreenId());
			objSpecTypeVo.setStrScreenID(strScreenId);
			
			}
			
			
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objPerfCurveModelForm.setSpecTypeList(arlSpec);
			}
			if (specTypeNo != -1 && specTypeNo != 0) {
			
			LogUtil.logMessage("before the GenArrangeMaintenance:fetchModels");
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			objModelVo.setSpecTypeSeqNo(objPerfCurveModelForm.getSpecTypeNo());
			ModelBI objModelBO = ServiceFactory.getModelBO();
			if (objModelBO.fetchModels(objModelVo) != null) {
				 arlModelList = objModelBO.fetchModels(objModelVo);
				if (arlModelList != null) {
						
						objPerfCurveModelForm.setListModels(arlModelList);
						objPerfCurveModelForm.setSelectMdl(objPerfCurveModelForm
								.getSelectMdl());
						
						objPerfCurveModelForm.setHdnDisp(objPerfCurveModelForm
								.getHdnDisp());
						
					}
			}
			}
		LogUtil.logMessage("after the GenArrangeMaintenance:fetchModels");
			
			PerformanceCurveVO objPerformanceCurveVO = new PerformanceCurveVO();
			
			objPerformanceCurveVO.setModelSeqNo(objPerfCurveModelForm
					.getModelSeqNo());
			objPerformanceCurveVO.setUserID(objLoginVo.getUserID());
			
			
			arlImageList = objPerformanceCurveBO
			.fetchPerfCurveImages(objPerformanceCurveVO);
			
			if (arlImageList != null && arlImageList.size() > 0) {
				
				objPerformanceCurveVO = new PerformanceCurveVO();
				objPerfCurveModelForm.setResultList(arlImageList);
				objPerfCurveModelForm.setSelectMdl(objPerfCurveModelForm
						.getSelectMdl());
				objPerfCurveModelForm.setHdnDisp(objPerfCurveModelForm
						.getHdnDisp());
				
			}
			
			if (arlImageList.size() == 0) {
				
				objPerfCurveModelForm.setSelectMdl(objPerfCurveModelForm
						.getSelectMdl());
				
				objPerfCurveModelForm.setHdnDisp(0);
				
			}
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelPerfCurveAction..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
}