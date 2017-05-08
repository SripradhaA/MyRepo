package com.EMD.LSDB.action.MasterMaintenance;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.EMD.LSDB.action.common.EMDAction;
import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.ModelAppendixBI;
import com.EMD.LSDB.bo.serviceinterface.ModelBI;
import com.EMD.LSDB.bo.serviceinterface.ModelSubSectionBI;
import com.EMD.LSDB.bo.serviceinterface.SectionBI;
import com.EMD.LSDB.bo.serviceinterface.SpecTypeBI;
import com.EMD.LSDB.bo.serviceinterface.ViewSpecByModelBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.constant.DatabaseConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.form.MasterMaintenance.ModelAppendixForm;
import com.EMD.LSDB.form.MasterMaintenance.ModelClauseDescPopUpForm;
import com.EMD.LSDB.vo.common.FileVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.ModelAppendixVO;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SpecTypeVo;
import com.EMD.LSDB.vo.common.SubSectionVO;
import com.lowagie.text.Image;

public class ModelAppendixMaintAction extends EMDAction {
	
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
	public ModelAppendixMaintAction() {
		
	}
	
	/*******************************************************************************************
	 * This method is for loading the spec types
	 * @author  Satyam Computer Services Ltd  
	 * @version 1.0  
	 * @param      objActionMapping    
	 * @param 	   objActionForm
	 * @param	   objHttpServletRequest
	 * @param      objHttpServletResponse
	 * @return     ActionForward 
	 * @throws     ApplicationException
	 ******************************************************************************************/
	
	public ActionForward initLoad(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil.logMessage("Entering ModelAppendixMaintAction.initLoad");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ModelAppendixForm objModelAppendixForm = (ModelAppendixForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		
		int specTypeNo = -1;
		int modleSeqNo = -1;
		String strSpecTypeNo = null;
		String strModleSeqNo = null;
		ArrayList arlModelList = null;
		
		
		
		try {
			
			//CR_83 lines are added ahere 
			strSpecTypeNo = (String) objHttpServletRequest
			.getParameter("specTypeNo");
	strModleSeqNo = (String) objHttpServletRequest
			.getParameter("modelSeqNo");
		
	if (strSpecTypeNo != null) {
		objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);

		specTypeNo = Integer.parseInt(strSpecTypeNo);
		objModelAppendixForm.setSpecTypeNo(specTypeNo);

	} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
		if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {

			
			strSpecTypeNo = (String) objSession
					.getAttribute("SPEC_TYPE_NO");
			specTypeNo = Integer.parseInt(strSpecTypeNo);
			objModelAppendixForm.setSpecTypeNo(specTypeNo);
		}
	}

	if (strModleSeqNo != null) {

		objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
		modleSeqNo = Integer.parseInt(strModleSeqNo);
		objModelAppendixForm.setModelSeqNo(modleSeqNo);

	} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {

		if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
			strModleSeqNo = (String) objSession
					.getAttribute("MODEL_SEQ_NO");
			modleSeqNo = Integer.parseInt(strModleSeqNo);
			objModelAppendixForm.setModelSeqNo(modleSeqNo);
		}
	}
	if (objHttpServletRequest.getParameter("DivID") != null) {
		objModelAppendixForm.setClauseDes(objHttpServletRequest
				.getParameter("DivID"));
		
	}
	if (specTypeNo != -1 && specTypeNo != 0) {
		ModelVo objModelVo = new ModelVo();
		objModelVo.setUserID(objLoginVo.getUserID());
		objModelVo.setSpecTypeSeqNo(specTypeNo);
		ModelBI objModelBO = ServiceFactory.getModelBO();
		if (objModelBO.fetchModels(objModelVo) != null) {
			arlModelList = objModelBO.fetchModels(objModelVo);
			objModelAppendixForm.setListModels(arlModelList);
			objModelAppendixForm.setModelSeqNo(modleSeqNo);
		}

	}	
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			objModelAppendixForm.setSpecTypeList(arlSpec);
					
		} catch (Exception objExp) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/*******************************************************************************************
	 *  * * *		This Method is used to populate the Model Dropdown on PageLoad
	 *  
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return     	ActionForward  		   
	 * @throws     	EMDException
	 
	 ******************************************************************************************/
	
	public ActionForward fetchModels(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering ModelAppendixMaintAction.fetchModels");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ModelAppendixForm objModelAppendixForm = (ModelAppendixForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		int specTypeNo = -1;
		String strSpecTypeNo = null;
		String strModleSeqNo = null;
		int modleSeqNo = -1;
		
		
		try {
			strSpecTypeNo=(String) objHttpServletRequest.getParameter("specTypeNo");
			strModleSeqNo=(String) objHttpServletRequest.getParameter("modelSeqNo");
			
/*//CR_83 holding the value from form in the sesiion 
			
			//objModelMaintForm.setSpecTypeNo(specTypeNo);
			if(strSpecTypeNo!=null)
			{			
			objSession.setAttribute("SPEC_TYPE_NO",strSpecTypeNo);
			specTypeNo=Integer.parseInt(strSpecTypeNo);
			objModelAppendixForm.setSpecTypeNo(specTypeNo);
			}
			
			
			objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
			modleSeqNo = Integer.parseInt(strModleSeqNo);
			objModelAppendixForm.setSpecTypeNo(modleSeqNo);
			
				//CR_83 lines are ends here	
*/			
		
			
			if (strSpecTypeNo != null) {
				
				objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);
				specTypeNo = Integer.parseInt(strSpecTypeNo);
				objModelAppendixForm.setSpecTypeNo(specTypeNo);
				
			} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
				if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {

					strSpecTypeNo = (String) objSession
							.getAttribute("SPEC_TYPE_NO");
					specTypeNo = Integer.parseInt(strSpecTypeNo);
					objModelAppendixForm.setSpecTypeNo(specTypeNo);
				}
			}

			if (strModleSeqNo != null) {

				objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
				modleSeqNo = Integer.parseInt(strModleSeqNo);
				objModelAppendixForm.setModelSeqNo(modleSeqNo);

			} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {

				if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
					strModleSeqNo = (String) objSession
							.getAttribute("MODEL_SEQ_NO");
					modleSeqNo = Integer.parseInt(strModleSeqNo);
					objModelAppendixForm.setModelSeqNo(modleSeqNo);
				}
			}
			/****Added for CR-46 PM&I Spec for loading Specification Type Drop Down - Starts Here*********/
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			
			objModelAppendixForm.setSpecTypeList(arlSpec);
			objModelAppendixForm.setSpecTypeNo(objModelAppendixForm
					.getSpecTypeNo());
			
			/****Added for CR-46 PM&I Spec for loading Specification Type Drop Down - Ends Here****/
			
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			
			//Added for CR- 46 PM&I Spec to load Models based on Spec Type
			objModelVo.setSpecTypeSeqNo(objModelAppendixForm.getSpecTypeNo());
			
			ArrayList arlModelList = null;
			ModelBI objModelBo = ServiceFactory.getModelBO();
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null)
				objModelAppendixForm.setListModels(arlModelList);
			objModelAppendixForm.setHdnSelSpecType(objModelAppendixForm
					.getHdnSelSpecType());
			
	
			
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelAppendixMaintAction..");
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
	
	/*******************************************************************************************
	 *  * * *		This Method is used to fetch Appendix Images 
	 *  
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return     	ActionForward  		   
	 * @throws     	EMDException
	 
	 ******************************************************************************************/
	
	public ActionForward fetchAppendixImages(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil
		.logMessage("Entering ModelAppendixMaintAction:fetchAppendixImages");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList arlImageList = null;
		
		ModelAppendixForm objModelAppendixForm = (ModelAppendixForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		LogUtil.logMessage(objLoginVo);
		String strSpecTypeNo = null;
		String strModleSeqNo = null;
		
		try {
			
//			CR_83 holding the value from form in the sesiion 
			
			//objModelMaintForm.setSpecTypeNo(specTypeNo);
			strSpecTypeNo=(String) objHttpServletRequest.getParameter("specTypeNo");
			strModleSeqNo=(String) objHttpServletRequest.getParameter("modelSeqNo");
			objSession.setAttribute("SPEC_TYPE_NO",strSpecTypeNo);
			objSession.setAttribute("MODEL_SEQ_NO",strModleSeqNo);
			
				//CR_83 lines are ends here	
			
			/****Added for CR-46 PM&I Spec for loading Specification Type Drop Down - Starts Here****/
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpec);
			objModelAppendixForm.setSpecTypeList(arlSpec);
			objModelAppendixForm.setSpecTypeNo(objModelAppendixForm
					.getSpecTypeNo());
			
			/****Added for CR-46 PM&I Spec for loading Specification Type Drop Down - Ends Here****/
			
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			
			//Added for CR- 46 PM&I Spec to load Models based on Spec Type
			objModelVo.setSpecTypeSeqNo(objModelAppendixForm.getSpecTypeNo());
			
			ArrayList arlModelList = null;
			ModelBI objModelBo = ServiceFactory.getModelBO();
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null) {
				
				objModelAppendixForm.setListModels(arlModelList);
				objModelAppendixForm.setSelectedModel(objModelAppendixForm
						.getSelectedModel());
				
			}
			
			ModelAppendixVO objModelAppendixVO = new ModelAppendixVO();
			
			objModelAppendixVO.setModelSeqNo(objModelAppendixForm
					.getModelSeqNo());
			objModelAppendixVO.setUserID(objLoginVo.getUserID());
			
			ModelAppendixBI objModelAppendixBO = ServiceFactory
			.getModelAppendixBO();
			arlImageList = objModelAppendixBO
			.fetchAppendixImages(objModelAppendixVO);
			
			if (arlImageList != null && arlImageList.size() > 0) {
				
				objModelAppendixForm.setListImages(arlImageList);
				objModelAppendixForm.setSelectedModel(objModelAppendixForm
						.getSelectedModel());
				objModelAppendixForm.setHdnModelSeqNo(objModelAppendixForm
						.getModelSeqNo());
				objModelAppendixForm.setHdnSelSpecType(objModelAppendixForm
						.getHdnSelSpecType());
				
			}
			
			if (arlImageList.size() == 0) {
				
				objModelAppendixForm.setSelectedModel(objModelAppendixForm
						.getSelectedModel());
				objModelAppendixForm.setMethod("NoAppendixImage");
				objModelAppendixForm.setHdnModelSeqNo(objModelAppendixForm
						.getModelSeqNo());
				objModelAppendixForm.setHdnSelSpecType(objModelAppendixForm
						.getHdnSelSpecType());
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
	
	/*******************************************************************************************
	 *  * * *		This Method is used to Upload Appendix Image
	 *  
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return     	ActionForward  		   
	 * @throws     	EMDException
	 
	 ******************************************************************************************/
	
	public ActionForward uploadAppendixImage(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException,
			FileNotFoundException, IOException {
		
		LogUtil
		.logMessage("Enters into ModelAppendixAction:uploadAppendixImage");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intUpdated = 0;
		boolean blnFlag = true;/*Added for CR_97*/
		ModelAppendixForm objModelAppendixForm = (ModelAppendixForm) objActionForm;
		ModelAppendixVO objModelAppendixVO = new ModelAppendixVO();
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		try {
			
			/****Added for CR-46 PM&I Spec for loading Specification Type Drop Down - Starts Here****/
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			objModelAppendixForm.setSpecTypeList(arlSpec);
			objModelAppendixForm.setSpecTypeNo(objModelAppendixForm
					.getSpecTypeNo());
			
			/****Added for CR-46 PM&I Spec for loading Specification Type Drop Down - Ends Here****/
			
			/******* for Loading DropDown code Starts here****************/
			
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			
			//Added for CR- 46 PM&I Spec to load Models based on Spec Type
			objModelVo.setSpecTypeSeqNo(objModelAppendixForm.getSpecTypeNo());
			
			ArrayList arlModelList = null;
			ModelBI objModelBo = ServiceFactory.getModelBO();
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null) {
				objModelAppendixForm.setListModels(arlModelList);
				objModelAppendixForm.setSelectedModel(objModelAppendixForm
						.getSelectedModel());
				
			}
			
			/****************** for Loading DropDown code Ends here **********/
			
			FormFile objFormFile = objModelAppendixForm.getTheFile();
			
			FileVO objFileVO = new FileVO();
			objFileVO.setFileName(objFormFile.getFileName());
			objFileVO.setuploadedFile(objFormFile.getFileData());
			objFileVO.setFileLength(objFormFile.getFileSize());
			objFileVO.setFileStream(objFormFile.getInputStream());
			objFileVO.setContentType(objFormFile.getContentType());
			objModelAppendixVO.setFileVO(objFileVO);
			objModelAppendixVO.setUserID(objLoginVo.getUserID());
			objModelAppendixVO.setModelSeqNo(objModelAppendixForm
					.getModelSeqNo());
			objModelAppendixVO.setUserID(objLoginVo.getUserID());
			objModelAppendixVO.setImageName(objModelAppendixForm.getImgName());
			objModelAppendixVO.setImageDesc(objModelAppendixForm.getImgDesc());
			
			ModelAppendixBI objModelAppendixBO = ServiceFactory
			.getModelAppendixBO();
			
			/* Added & Modified for Image Validation. Modified for CR_97 */ 
			if (!("application/pdf").equalsIgnoreCase(objFormFile
					.getContentType())) {
			byte byteImage[] = objModelAppendixForm.getTheFile().getFileData();
			Image image = Image.getInstance(byteImage);
			
			float width = image.getScaledWidth();
			float height = image.getScaledHeight();
			if (width > 550.0 || height > 600.0) {
				
				objModelAppendixForm
				.setMessageID(ApplicationConstants.IMAGE_SIZE_VALIDATION);
				blnFlag=false;
				} 
			}
			if(blnFlag) {
				intUpdated = objModelAppendixBO
				.uploadAppendixImage(objModelAppendixVO);
				if (intUpdated == 0) {
					objModelAppendixForm
					.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
					objModelAppendixForm.setImgName("");
					objModelAppendixForm.setImgDesc("");
				} else {
					objModelAppendixForm.setMessageID("" + intUpdated);
				}
			}/* CR_97 Modifcation ends here */
			objModelAppendixVO.setModelSeqNo(objModelAppendixForm
					.getModelSeqNo());
			objModelAppendixVO.setUserID(objLoginVo.getUserID());
			
			ArrayList objImagelist = objModelAppendixBO
			.fetchAppendixImages(objModelAppendixVO);
			
			if (objImagelist != null && objImagelist.size() > 0) {
				
				objModelAppendixForm.setListImages(objImagelist);
				objModelAppendixForm.setSelectedModel(objModelAppendixForm
						.getSelectedModel());
				objModelAppendixForm.setHdnModelSeqNo(objModelAppendixForm
						.getModelSeqNo());
				objModelAppendixForm.setHdnSelSpecType(objModelAppendixForm
						.getHdnSelSpecType());
				
			}
			
			if (objImagelist.size() == 0) {
				
				objModelAppendixForm.setSelectedModel(objModelAppendixForm
						.getSelectedModel());
				objModelAppendixForm.setMethod("NoAppendixImage");
				objModelAppendixForm.setHdnModelSeqNo(objModelAppendixForm
						.getModelSeqNo());
			}
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelAppendixAction.."
					+ objExp);
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/*******************************************************************************************
	 *  * * *		This Method is used to Delete Appendix Image
	 *  
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return     	ActionForward  		   
	 * @throws     	EMDException
	 
	 ******************************************************************************************/
	
	public ActionForward deleteAppendixImage(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException,
			FileNotFoundException, IOException {
		
		LogUtil
		.logMessage("Enters into ModelAppendixAction:deleteAppendixImage");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intUpdated = 0;
		
		ModelAppendixForm objModelAppendixForm = (ModelAppendixForm) objActionForm;
		ModelAppendixVO objModelAppendixVO = new ModelAppendixVO();
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		try {
			
			/****Added for CR-46 PM&I Spec for loading Specification Type Drop Down - Starts Here****/
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			objModelAppendixForm.setSpecTypeList(arlSpec);
			objModelAppendixForm.setSpecTypeNo(objModelAppendixForm
					.getSpecTypeNo());
			
			/****Added for CR-46 PM&I Spec for loading Specification Type Drop Down - Ends Here****/
			
			/******* for Loading DropDown code Starts here****************/
			
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			
			//Added for CR-46 PM&I Spec to load Models based on Spec Type
			objModelVo.setSpecTypeSeqNo(objModelAppendixForm.getSpecTypeNo());
			
			ArrayList arlModelList = null;
			ModelBI objModelBo = ServiceFactory.getModelBO();
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null) {
				objModelAppendixForm.setListModels(arlModelList);
				objModelAppendixForm.setSelectedModel(objModelAppendixForm
						.getSelectedModel());
				
			}
			
			/****************** for Loading DropDown code Ends here **********/
			
			objModelAppendixVO.setModelSeqNo(objModelAppendixForm
					.getModelSeqNo());
			objModelAppendixVO.setUserID(objLoginVo.getUserID());
			objModelAppendixVO.setImgSeqNo(objModelAppendixForm
					.getHdnImgSeqNo());
			
			ModelAppendixBI objModelAppendixBO = ServiceFactory
			.getModelAppendixBO();
			intUpdated = objModelAppendixBO
			.deleteAppendixImage(objModelAppendixVO);
			
			if (intUpdated == 0) {
				objModelAppendixForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objModelAppendixForm.setMessageID("" + intUpdated);
			}
			
			objModelAppendixVO.setModelSeqNo(objModelAppendixForm
					.getModelSeqNo());
			objModelAppendixVO.setUserID(objLoginVo.getUserID());
			
			ArrayList objImagelist = objModelAppendixBO
			.fetchAppendixImages(objModelAppendixVO);
			
			if (objImagelist != null && objImagelist.size() > 0) {
				
				objModelAppendixForm.setListImages(objImagelist);
				objModelAppendixForm.setSelectedModel(objModelAppendixForm
						.getSelectedModel());
				objModelAppendixForm.setHdnModelSeqNo(objModelAppendixForm
						.getModelSeqNo());
				objModelAppendixForm.setHdnSelSpecType(objModelAppendixForm
						.getHdnSelSpecType());
				
			}
			
			if (objImagelist.size() == 0) {
				
				objModelAppendixForm.setSelectedModel(objModelAppendixForm
						.getSelectedModel());
				objModelAppendixForm.setMethod("NoAppendixImage");
				objModelAppendixForm.setHdnModelSeqNo(objModelAppendixForm
						.getModelSeqNo());
				objModelAppendixForm.setHdnSelSpecType(objModelAppendixForm
						.getHdnSelSpecType());
			}
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelAppendixAction..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/*******************************************************************************************
	 *  * * *		This Method is used to Update Appendix Image
	 *  
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return     	ActionForward  		   
	 * @throws     	EMDException
	 
	 ******************************************************************************************/
	
	public ActionForward updateAppendixImage(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException,
			FileNotFoundException, IOException {
		
		LogUtil
		.logMessage("Enters into ModelAppendixAction:updateAppendixImage");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intUpdated = 0;
		
		ModelAppendixForm objModelAppendixForm = (ModelAppendixForm) objActionForm;
		ModelAppendixVO objModelAppendixVO = new ModelAppendixVO();
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		try {
			
			/****Added for CR-46 PM&I Spec for loading Specification Type Drop Down - Starts Here****/
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			objModelAppendixForm.setSpecTypeList(arlSpec);
			objModelAppendixForm.setSpecTypeNo(objModelAppendixForm
					.getSpecTypeNo());
			
			/****Added for CR-46 PM&I Spec for loading Specification Type Drop Down - Ends Here****/
			
			/******* for Loading DropDown code Starts here****************/
			
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			
			//Added for CR-46 PM&I Spec to load Models based on Spec Type
			objModelVo.setSpecTypeSeqNo(objModelAppendixForm.getSpecTypeNo());
			
			ArrayList arlModelList = null;
			ModelBI objModelBo = ServiceFactory.getModelBO();
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null) {
				objModelAppendixForm.setListModels(arlModelList);
				objModelAppendixForm.setSelectedModel(objModelAppendixForm
						.getSelectedModel());
				
			}
			
			/****************** for Loading DropDown code Ends here **********/
			
			objModelAppendixVO.setModelSeqNo(objModelAppendixForm
					.getModelSeqNo());
			objModelAppendixVO.setImgSeqNo(objModelAppendixForm
					.getHdnImgSeqNo());
			objModelAppendixVO.setImageName(objModelAppendixForm
					.getHdnImageName());
			objModelAppendixVO.setImageDesc(objModelAppendixForm
					.getHdnImageDesc());
			objModelAppendixVO.setUserID(objLoginVo.getUserID());
			
			ModelAppendixBI objModelAppendixBO = ServiceFactory
			.getModelAppendixBO();
			intUpdated = objModelAppendixBO
			.updateAppendixImage(objModelAppendixVO);
			
			if (intUpdated == 0) {
				objModelAppendixForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objModelAppendixForm.setMessageID("" + intUpdated);
			}
			
			objModelAppendixVO.setModelSeqNo(objModelAppendixForm
					.getModelSeqNo());
			objModelAppendixVO.setUserID(objLoginVo.getUserID());
			
			ArrayList objImagelist = objModelAppendixBO
			.fetchAppendixImages(objModelAppendixVO);
			
			if (objImagelist != null && objImagelist.size() > 0) {
				
				objModelAppendixForm.setListImages(objImagelist);
				objModelAppendixForm.setSelectedModel(objModelAppendixForm
						.getSelectedModel());
				objModelAppendixForm.setHdnModelSeqNo(objModelAppendixForm
						.getModelSeqNo());
				objModelAppendixForm.setHdnSelSpecType(objModelAppendixForm
						.getHdnSelSpecType());
				
			}
			
			if (objImagelist.size() == 0) {
				
				objModelAppendixForm.setSelectedModel(objModelAppendixForm
						.getSelectedModel());
				objModelAppendixForm.setMethod("NoAppendixImage");
				objModelAppendixForm.setHdnModelSeqNo(objModelAppendixForm
						.getModelSeqNo());
				objModelAppendixForm.setHdnSelSpecType(objModelAppendixForm
						.getHdnSelSpecType());
			}
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelAppendixAction..");
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
	 * This method is used to Map the Appendix Image with clause selected at
	 * ODELLevel
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
	//CR_92
	public ActionForward saveModelClaMappings(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into AppendixAction:saveMappings");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList objImagelist = new ArrayList();
		ModelAppendixForm objModelAppendixForm = (ModelAppendixForm) objActionForm;
		ModelAppendixVO objModelAppendixVO = new ModelAppendixVO();
		LoginVO objLoginVo = null;
		int intStatusCode;
			
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
		if (objSession != null) {
				objLoginVo = (LoginVO) objSession
				.getAttribute(ApplicationConstants.USER_IN_SESSION);
				
			}
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			objModelAppendixForm.setSpecTypeList(arlSpec);
			objModelAppendixForm.setSpecTypeNo(objModelAppendixForm
					.getSpecTypeNo());
			
			/****Added for CR-46 PM&I Spec for loading Specification Type Drop Down - Ends Here****/
			
			/******* for Loading DropDown code Starts here****************/
			
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			
			//Added for CR-46 PM&I Spec to load Models based on Spec Type
			objModelVo.setSpecTypeSeqNo(objModelAppendixForm.getSpecTypeNo());
			
			ArrayList arlModelList = null;
			ModelBI objModelBo = ServiceFactory.getModelBO();
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null) {
				objModelAppendixForm.setListModels(arlModelList);
				objModelAppendixForm.setSelectedModel(objModelAppendixForm
						.getSelectedModel());
				
			}
			objModelAppendixVO.setModelSeqNo(objModelAppendixForm.getHdnModelSeqNo());
			objModelAppendixVO.setImgSeqNo(objModelAppendixForm.getImgSeqNo());
			objModelAppendixVO.setClauseSeqNo(objModelAppendixForm.getClauseSeqNo());
			//objModelAppendixVO.setSubSectionSeqNo(objModelAppendixForm.getSubSectionSeqNo());
			objModelAppendixVO.setVersionNo(objModelAppendixForm.getVersionNo());
			objModelAppendixVO.setUserID(objLoginVo.getUserID());
			ModelAppendixBI objModelAppendixBI = ServiceFactory
			.getModelAppendixBO();
			intStatusCode = objModelAppendixBI.saveModelClaMappings(objModelAppendixVO);
			if (intStatusCode == 0) {
				objModelAppendixForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				objModelAppendixForm.setClauseSeqNo(0);
				objModelAppendixForm.setVersionNo(0);
			}
			
			if (intStatusCode > 0) {
				objModelAppendixForm.setMessageID("" + intStatusCode);
			}
			objModelAppendixVO.setModelSeqNo(objModelAppendixForm.getHdnModelSeqNo());
			objModelAppendixVO.setUserID(objLoginVo.getUserID());
			objImagelist = objModelAppendixBI.fetchAppendixImages(objModelAppendixVO);
			
			
           if (objImagelist != null && objImagelist.size() > 0) {
				
				objModelAppendixForm.setListImages(objImagelist);
				objModelAppendixForm.setSelectedModel(objModelAppendixForm
						.getSelectedModel());
				objModelAppendixForm.setHdnModelSeqNo(objModelAppendixForm
						.getModelSeqNo());
				objModelAppendixForm.setHdnSelSpecType(objModelAppendixForm
						.getHdnSelSpecType());
				
			}
			
			if (objImagelist.size() == 0) {
				objModelAppendixForm.setSelectedModel(objModelAppendixForm
						.getSelectedModel());
				objModelAppendixForm.setMethod("NoAppendixImage");
				objModelAppendixForm.setHdnModelSeqNo(objModelAppendixForm
						.getModelSeqNo());
				objModelAppendixForm.setHdnSelSpecType(objModelAppendixForm
						.getHdnSelSpecType());
			}
			
											
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in AppendixAction..");
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
