/**
 * 
 */
package com.EMD.LSDB.action.MasterMaintenance;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.EMD.LSDB.bo.serviceinterface.ModelGenArrangeBI;
import com.EMD.LSDB.bo.serviceinterface.ModelSelectClauseRevBI;
import com.EMD.LSDB.bo.serviceinterface.ModelSubSectionBI;
import com.EMD.LSDB.bo.serviceinterface.SectionBI;
import com.EMD.LSDB.bo.serviceinterface.SpecTypeBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.form.MasterMaintenance.ModelAddClauseForm;
import com.EMD.LSDB.form.MasterMaintenance.ModelGenArrangeForm;
import com.EMD.LSDB.form.MasterMaintenance.SecMaintForm;
import com.EMD.LSDB.form.common.EMDForm;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.FileVO;
import com.EMD.LSDB.vo.common.GenArrangeVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SpecTypeVo;
import com.EMD.LSDB.vo.common.StandardEquipVO;
import com.EMD.LSDB.vo.common.SubSectionVO;

/**
 * @author PS57222
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
 * 13/09/2011        1.0      SD41630    Removed upload method and added new          Added for CR_101
 * 										 updateMdlGenArgmntViewDtls For Updated 
 * 										 modle view details.	 
 *       																		 	 
 * --------------------------------------------------------------------------------------------------------
 * **************************************************************************/
public class ModelGenArrangeAction extends EMDAction {
	
	public ModelGenArrangeAction() {
		
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
		ModelGenArrangeForm objModelGenArrangeForm = (ModelGenArrangeForm) objActionForm;
		
		int specTypeNo = -1;
		int modleSeqNo = -1;
		String strSpecTypeNo = null;
		String strModleSeqNo = null;
		ArrayList arlModelList = null;
		String strScreenId = "";
		
		
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
			objModelGenArrangeForm.setSpecTypeNo(specTypeNo);
		
		} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
			if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {
					strSpecTypeNo = (String) objSession
						.getAttribute("SPEC_TYPE_NO");
				specTypeNo = Integer.parseInt(strSpecTypeNo);
				objModelGenArrangeForm.setSpecTypeNo(specTypeNo);
					}
		}

		if (strModleSeqNo != null) {

			objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
			modleSeqNo = Integer.parseInt(strModleSeqNo);
			LogUtil.logMessage("value of MODEL_SEQ_NO from session "
					+ modleSeqNo);
			objModelGenArrangeForm.setModelSeqNo(modleSeqNo);

		} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {

			if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
				strModleSeqNo = (String) objSession
						.getAttribute("MODEL_SEQ_NO");
				modleSeqNo = Integer.parseInt(strModleSeqNo);
				objModelGenArrangeForm.setModelSeqNo(modleSeqNo);
			}
		}

		//CR_83 lines area ends here
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
//			CR_84 lines ae starts here
			if (objHttpServletRequest.getParameter("screenid") != null) {
				strScreenId = objHttpServletRequest
				.getParameter("screenid");
				objSpecTypeVo.setStrScreenID(strScreenId);
				
			}
			
			//CR_84 lines ae ends here
			boolean specTypeExists = false;
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objModelGenArrangeForm.setSpecTypeList(arlSpec);
				LogUtil.logMessage("SpecType ArrayList Size : " + arlSpec.size());
				SpecTypeVo objjSpecTypeVo = new SpecTypeVo();
				for (int i=0;i<arlSpec.size();i++)	{
				objjSpecTypeVo = (SpecTypeVo) arlSpec.get(i);
				if (specTypeNo == objjSpecTypeVo.getSpectypeSeqno()){
					specTypeExists = true;
					break;
					}
				}
			}
			
			if (specTypeNo != -1 && specTypeNo != 0 && specTypeExists) {

				LogUtil.logMessage("in side modleSeqNo" + modleSeqNo);

				ModelVo objModelVo = new ModelVo();
				objModelVo.setUserID(objLoginVo.getUserID());
				objModelVo.setSpecTypeSeqNo(specTypeNo);
				ModelBI objModelBO = ServiceFactory.getModelBO();
				if (objModelBO.fetchModels(objModelVo) != null) {
					arlModelList = objModelBO.fetchModels(objModelVo);
					objModelGenArrangeForm.setModelList(arlModelList);
					objModelGenArrangeForm.setModelSeqNo(modleSeqNo);
					LogUtil.logMessage("end of the if modleSeqNo" + modleSeqNo);
				}

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
		
		LogUtil.logMessage("Inside the SectionMaintenance:fetchModels");
		String strForwardKey = ApplicationConstants.SUCCESS;
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		ModelGenArrangeForm objModelGenArrangeForm = (ModelGenArrangeForm) objActionForm;
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
		objModelGenArrangeForm.setSpecTypeNo(specTypeNo);
	} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
		if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {
				strSpecTypeNo = (String) objSession
					.getAttribute("SPEC_TYPE_NO");
			specTypeNo = Integer.parseInt(strSpecTypeNo);
			objModelGenArrangeForm.setSpecTypeNo(specTypeNo);
		}
	}

	if (strModleSeqNo != null) {

		objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
		modleSeqNo = Integer.parseInt(strModleSeqNo);
		objModelGenArrangeForm.setModelSeqNo(modleSeqNo);

	} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {

		if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
			strModleSeqNo = (String) objSession
					.getAttribute("MODEL_SEQ_NO");
			modleSeqNo = Integer.parseInt(strModleSeqNo);
			objModelGenArrangeForm.setModelSeqNo(modleSeqNo);
		}
	}
	
	//CR_83 lines area ends here
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
      //CR_84 lines ae started here- added screen id 
			if(objLoginVo.getIntScreenId()!=0)
			{
			//objSpecTypeVo.setScreenID(objLoginVo.getIntScreenId());
			String strScreenId= Integer.toString(objLoginVo.getIntScreenId());
			objSpecTypeVo.setStrScreenID(strScreenId);
			
			}
							
			//CR_84 lines ends here
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objModelGenArrangeForm.setSpecTypeList(arlSpec);
				//objSecMaintForm.setSpecTypeNo(specTypeNo);
			}
		if (specTypeNo != -1 && specTypeNo != 0) {
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			objModelVo.setSpecTypeSeqNo(objModelGenArrangeForm.getSpecTypeNo());
			ModelBI objModelBO = ServiceFactory.getModelBO();
			if (objModelBO.fetchModels(objModelVo) != null) {
				 arlModelList = objModelBO.fetchModels(objModelVo);
				 objModelGenArrangeForm.setModelList(arlModelList);
				//objSecMaintForm.setModelSeqNo(modleSeqNo);
			}
			}		
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
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil
		.logMessage("Inside the initLoad method of GenArrangeMaintenance");
		String strForwardKey = ApplicationConstants.SUCCESS;
		
		ModelGenArrangeForm objModelGenArrangeForm = (ModelGenArrangeForm) objActionForm;
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		//CR_83 lines area started here
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
				objModelGenArrangeForm.setModelSeqNo(modleSeqNo);

			} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {

				if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
					strModleSeqNo = (String) objSession
							.getAttribute("MODEL_SEQ_NO");
					modleSeqNo = Integer.parseInt(strModleSeqNo);
					objModelGenArrangeForm.setModelSeqNo(modleSeqNo);
				}
			}
			
			//CR_83 Lines area ends here
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());			
			//Added For CR_84
			objModelVo.setSpecTypeSeqNo(objModelGenArrangeForm.getSpecTypeNo());
			ModelBI objModelBO = ServiceFactory.getModelBO();
			if (objModelBO.fetchModels(objModelVo) != null) {
				ArrayList arlModelList = objModelBO.fetchModels(objModelVo);
				
				objModelGenArrangeForm.setModelList(arlModelList);
				
			}
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block ModelGenArrangeAction:initLoad");
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
	 * * * * This Method is used to search GenralArrangementImages
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 * 
	 **************************************************************************/
	
	public ActionForward fetchGenArrImages(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil.logMessage("Enters into ModelGenArrangeAction:Search Method");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList objArrayList = null;
		ModelGenArrangeForm objModelGenArrangeForm = (ModelGenArrangeForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		LogUtil.logMessage(objLoginVo);
		
		String strSpecTypeNo=null;
		String strModleSeqNo=null;
		ArrayList arlSectionList = null;
		int specTypeNo = -1;
		int modleSeqNo = -1;
		ArrayList arlModelList = null;
			

			
			try {	
			strSpecTypeNo = (String) objHttpServletRequest
			.getParameter("specTypeNo");
	strModleSeqNo = (String) objHttpServletRequest
			.getParameter("modelSeqNo");

	if (strSpecTypeNo != null) {
		objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);
		specTypeNo = Integer.parseInt(strSpecTypeNo);
		objModelGenArrangeForm.setSpecTypeNo(specTypeNo);
	} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
		if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {
			strSpecTypeNo = (String) objSession
					.getAttribute("SPEC_TYPE_NO");
			specTypeNo = Integer.parseInt(strSpecTypeNo);
			objModelGenArrangeForm.setSpecTypeNo(specTypeNo);
		}
	}

	if (strModleSeqNo != null) {
		objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
		modleSeqNo = Integer.parseInt(strModleSeqNo);
		objModelGenArrangeForm.setModelSeqNo(modleSeqNo);
	} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {

		if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
			strModleSeqNo = (String) objSession
					.getAttribute("MODEL_SEQ_NO");
			modleSeqNo = Integer.parseInt(strModleSeqNo);
			objModelGenArrangeForm.setModelSeqNo(modleSeqNo);
		}
	}
			
			
			//CR_84 lines are started here -passsing screen id
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
//			CR_84 lines ae started here- added screen id 
			if(objLoginVo.getIntScreenId()!=0)
			{
			
			String strScreenId= Integer.toString(objLoginVo.getIntScreenId());
			objSpecTypeVo.setStrScreenID(strScreenId);
			
			}
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objModelGenArrangeForm.setSpecTypeList(arlSpec);
			}
//			CR_84 lines ends here
			if(specTypeNo!=-1 && specTypeNo !=0)
			{
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			//Added For CR_84
			objModelVo.setSpecTypeSeqNo(objModelGenArrangeForm.getSpecTypeNo());
			ModelBI objModelBO = ServiceFactory.getModelBO();
			/*
			 * Added for LSDB_CR_46 PM&I Change Locomotive Models will only be
			 * populated -CR_84 commited
			 */
			//objModelVo.setSpecTypeSeqNo(ApplicationConstants.LOCOMOTIVE_ONE);
			if (objModelBO.fetchModels(objModelVo) != null) {
				 arlModelList = objModelBO.fetchModels(objModelVo);
				
				objModelGenArrangeForm.setModelList(arlModelList);
				
			}
			
			}
			GenArrangeVO objGenArrangeVO = new GenArrangeVO();
			
			objModelGenArrangeForm.setHdPreSelectedModel(objModelGenArrangeForm
					.getModelSeqNo());
			LogUtil.logMessage("PreVious Selected Model:"
					+ objModelGenArrangeForm.getHdPreSelectedModel());
			objGenArrangeVO.setModelSeqNo(objModelGenArrangeForm
					.getModelSeqNo());
			objGenArrangeVO.setUserID(objLoginVo.getUserID());
			LogUtil
			.logMessage("ModelSeqNo in ModelGenArrangeAction search method:"
					+ objGenArrangeVO.getModelSeqNo());
			LogUtil.logMessage("UserID in ModelGenArrangeAction search method:"
					+ objGenArrangeVO.getUserID());
			
			ModelGenArrangeBI objModelGenArrBO = ServiceFactory
			.getGenArrangeBO();
			if(modleSeqNo!=-1 && modleSeqNo !=0)
			{
			objArrayList = objModelGenArrBO.fetchGenArrImages(objGenArrangeVO);
			LogUtil.logMessage("FileVOModelGenArrangeAction search method:***:"
					+ objGenArrangeVO.getFileVO().getImageSeqNo());
			
			for (int i = 0; i < objArrayList.size(); i++) {
				objGenArrangeVO = (GenArrangeVO) objArrayList.get(i);
				LogUtil.logMessage("i :" + objGenArrangeVO.getModelViewSeqNo());
				LogUtil.logMessage("ViewSeqNo:"
						+ objGenArrangeVO.getModelViewSeqNo());
				LogUtil
				.logMessage("FileVOModelGenArrangeAction search method:***:"
						+ objGenArrangeVO.getFileVO().getImageSeqNo());
				LogUtil.logMessage("GenArrangeNotes :"
						+ objGenArrangeVO.getGenArrangeNotes());
				
				// commented on 20 May 08
				// objGenArrangeVO = (GenArrangeVO) objArrayList.get(2);
				
				objModelGenArrangeForm.setGenArgmntNotes(objGenArrangeVO
						.getGenArrangeNotes());
			}
			
			if (objArrayList != null) {
				objGenArrangeVO = new GenArrangeVO();
				objModelGenArrangeForm.setResultList(objArrayList);
				
			}
			
			LogUtil.logMessage("GenArrangeVO in Action:"
					+ objModelGenArrangeForm.getResultList());
		}
			}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelGenArrangeAction:fetchGenArrImages");
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
	 * * * * This Method is used to update the modele view image details 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 * 
	 **************************************************************************/
	
	/**
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return
	 * @throws EMDException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	/**
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return
	 * @throws EMDException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	//Added For CR_101 from updateview image dlts to UpdateMdlGenArgmntView
	public ActionForward updateMdlGenArgmntViewDtls(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException,
			FileNotFoundException, IOException {
		
		LogUtil.logMessage("Enters into ModelGenArrangeAction:updateMdlGenArgmntViewDtls");
		String strForwardKey = ApplicationConstants.SUCCESS;
		boolean blnUpdated = false;
		int intStatusCode = 0;
		ModelGenArrangeForm objModelGenArrangeForm = (ModelGenArrangeForm) objActionForm;
		GenArrangeVO objGenArrangeVO = new GenArrangeVO();
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LogUtil.logMessage(objSession);
		
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		LogUtil.logMessage(objLoginVo);
		
		//CR_84 lines are started here
		String strSpecTypeNo=null;
		String strModleSeqNo=null;
		ArrayList arlSectionList = null;
		int specTypeNo = -1;
		int modleSeqNo = -1;
		
			

			
			try {	
			strSpecTypeNo = (String) objHttpServletRequest
			.getParameter("specTypeNo");
	strModleSeqNo = (String) objHttpServletRequest
			.getParameter("modelSeqNo");

	if (strSpecTypeNo != null) {
		objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);
		specTypeNo = Integer.parseInt(strSpecTypeNo);
		objModelGenArrangeForm.setSpecTypeNo(specTypeNo);
	} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
		if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {
			strSpecTypeNo = (String) objSession
					.getAttribute("SPEC_TYPE_NO");
			specTypeNo = Integer.parseInt(strSpecTypeNo);
			objModelGenArrangeForm.setSpecTypeNo(specTypeNo);
		}
	}

	if (strModleSeqNo != null) {
		objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
		modleSeqNo = Integer.parseInt(strModleSeqNo);
		objModelGenArrangeForm.setModelSeqNo(modleSeqNo);
	} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {

		if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
			strModleSeqNo = (String) objSession
					.getAttribute("MODEL_SEQ_NO");
			modleSeqNo = Integer.parseInt(strModleSeqNo);
			objModelGenArrangeForm.setModelSeqNo(modleSeqNo);
		}
	}
	//CR_84 lines are ends here
		
		objModelGenArrangeForm.setHdPreSelectedModel(objModelGenArrangeForm
				.getModelSeqNo());
				
		FileVO objFileVO = new FileVO();
		
			List myFiles =(List) objModelGenArrangeForm.getViewImg();
			
			LogUtil.logMessage("Length in action .getHdnFileName():" + myFiles.size());
			
			for(int i=0;i<myFiles.size();i++){
			   
			   if(myFiles.get(i)!=null){
				   
				    LogUtil.logMessage("Index News :" + i);
				   
					FormFile objFormFile =(FormFile) myFiles.get(i) ; 
					
					if (objFormFile.getFileSize() != 0)
					{
						objFileVO.setFileName(objFormFile.getFileName());
						LogUtil.logMessage("FileName in action :"
								+ objFormFile.getFileName());
						
						objFileVO.setFileLength(objFormFile.getFileSize());
						LogUtil.logMessage("FileSize in action :"
								+ objFormFile.getFileSize());
						objFileVO.setFileStream(objFormFile.getInputStream());
						
						objFileVO.setContentType(objFormFile.getContentType());
						LogUtil.logMessage("ContentType in action :"
								+ objFormFile.getContentType());
					}
			   } 
			} 
			
			
			objGenArrangeVO.setFileVO(objFileVO);
			objGenArrangeVO.setUserID(objLoginVo.getUserID());
			
			objGenArrangeVO.setModelSeqNo(objModelGenArrangeForm
					.getModelSeqNo());
//			Added For CR_101
			objGenArrangeVO.setModelViewSeqNo(objModelGenArrangeForm
					.getHdSelectedMdlViewSeqNo());
			objGenArrangeVO.setModelView(objModelGenArrangeForm.getHdSelectedMdlView());
			objGenArrangeVO.setMdlViewNotes(objModelGenArrangeForm.getHdSelectedMdlViewNotes());
			ModelGenArrangeBI objModelGenArrBO = ServiceFactory
			.getGenArrangeBO();
			intStatusCode = objModelGenArrBO.updateMdlGenArgmntViewDtls(objGenArrangeVO);
			
			LogUtil.logMessage("successMessage in Action:" + intStatusCode);
			
			if (intStatusCode == 0) {
				objModelGenArrangeForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			}else  {
				String strStatusCode=String.valueOf(intStatusCode);
				objModelGenArrangeForm.setMessageID(strStatusCode);
			}
//			CR_84 lines ae started here -passing Screen id
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
//			CR_84 lines ae started here- added screen id 
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
				objModelGenArrangeForm.setSpecTypeList(arlSpec);
//				CR_84 lines ends here
			}
			/** ***** for Loading Model DropDown code Starts here*************** */
			
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			//Added For CR_84
			objModelVo.setSpecTypeSeqNo(objModelGenArrangeForm.getSpecTypeNo());
			ModelBI objModelBO = ServiceFactory.getModelBO();
			/*
			 * Added for LSDB_CR_46 PM&I Change Locomotive Models will only be
			 * populated
			 */
			if (objModelBO.fetchModels(objModelVo) != null) {
				
				ArrayList arlModelList = objModelBO.fetchModels(objModelVo);
				
				objModelGenArrangeForm.setModelList(arlModelList);
				
			}
			
			/** **************** for Loading DropDown code Ends here ********* */
			
			ArrayList arlArraylist = objModelGenArrBO
			.fetchGenArrImages(objGenArrangeVO);
			
			if (arlArraylist != null) {
				objModelGenArrangeForm.setResultList(arlArraylist);
				
			}
			objModelGenArrangeForm.setMdlNewViewName("");
			objModelGenArrangeForm.setMdlNewViewNotes("");
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelGenArrangeAction:UpdateMdlGenArgmntView");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
		}
		LogUtil.logMessage("Leaves From ModelGenArrangeAction:UpdateMdlGenArgmntView");
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***************************************************************************
	 * * * * This Method is used to Modify GenralArrangeMent Notes
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 * 
	 **************************************************************************/
	
	public ActionForward modifyGenArrNotes(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil
		.logMessage("Inside Modify method in ModelGenArrangeAction:modifyGenArrNotes ");
		
		String strForwardKey = ApplicationConstants.SUCCESS;
		
		int intStatusCode = 0;
		ModelGenArrangeForm objModelGenArrangeForm = (ModelGenArrangeForm) objActionForm;
		ArrayList arlArrayList = new ArrayList();
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		LogUtil.logMessage(objLoginVo);
		try {
			
			objModelGenArrangeForm.setHdPreSelectedModel(objModelGenArrangeForm
					.getModelSeqNo());
			LogUtil.logMessage("PreVious Selected Model:"
					+ objModelGenArrangeForm.getHdPreSelectedModel());
			GenArrangeVO objGenArrangeVO = new GenArrangeVO();
			String strGenArrangeNotes = objModelGenArrangeForm
			.getGenArgmntNotes();
			objGenArrangeVO.setGenArrangeNotes(ApplicationUtil
					.trim(strGenArrangeNotes));
			objGenArrangeVO.setModelSeqNo(objModelGenArrangeForm
					.getModelSeqNo());
			objGenArrangeVO.setUserID(objLoginVo.getUserID());
			
			LogUtil.logMessage("GenarrangementNotes in modifyGenArrangemnet:"
					+ objGenArrangeVO.getGenArrangeNotes());
			LogUtil.logMessage("ModelSeqNo in modifyGenArrangemnet:"
					+ objGenArrangeVO.getModelSeqNo());
			
			ModelGenArrangeBI objModelGenArrBO = ServiceFactory
			.getGenArrangeBO();
			intStatusCode = objModelGenArrBO.modifyGenArrNotes(objGenArrangeVO);
			LogUtil.logMessage("Updated Value:" + intStatusCode);
			
			if (intStatusCode == 0) {
				objModelGenArrangeForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			}
			
			/** ***** for Loading Model DropDown code Starts here*************** */
//			CR_84 lines are started here -passing Screen id  
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
//			CR_84 lines ae started here- added screen id 
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
				objModelGenArrangeForm.setSpecTypeList(arlSpec);
			}
//			CR_84 lines ends here
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			ModelBI objModelBO = ServiceFactory.getModelBO();
			/*
			 * Added for LSDB_CR_46 PM&I Change Locomotive Models will only be
			 * populated
			 */			
			//Added For CR_84
			objModelVo.setSpecTypeSeqNo(objModelGenArrangeForm.getSpecTypeNo());
			if (objModelBO.fetchModels(objModelVo) != null) {
				ArrayList arlModelList = objModelBO.fetchModels(objModelVo);
				
				objModelGenArrangeForm.setModelList(arlModelList);
				
			}
			
			/** **************** for Loading DropDown code Ends here ********* */
			
			objGenArrangeVO.setModelSeqNo(objModelGenArrangeForm
					.getModelSeqNo());
			objGenArrangeVO.setUserID(objLoginVo.getUserID());
			LogUtil
			.logMessage("ModelSeqNo in ModelGenArrangeAction search method:"
					+ objGenArrangeVO.getModelSeqNo());
			LogUtil.logMessage("UserID in ModelGenArrangeAction search method:"
					+ objGenArrangeVO.getUserID());
			
			ModelGenArrangeBI objModelGenArrBO1 = ServiceFactory
			.getGenArrangeBO();
			arlArrayList = objModelGenArrBO1.fetchGenArrImages(objGenArrangeVO);
			
			if (arlArrayList != null) {
				objGenArrangeVO = new GenArrangeVO();
				objModelGenArrangeForm.setResultList(arlArrayList);
				
				objGenArrangeVO = (GenArrangeVO) arlArrayList.get(2);
				objGenArrangeVO.setGenArrangeNotes(objGenArrangeVO
						.getGenArrangeNotes());
				objModelGenArrangeForm.setGenArgmntNotes(objGenArrangeVO
						.getGenArrangeNotes());
			}
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelGenArrangeAction:modifyGenArrNotes");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
		}
		return objActionMapping.findForward(strForwardKey);
	}
	
	
	
	/**
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return
	 * @throws EMDException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	/**
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return
	 * @throws EMDException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	//Added For CR_101 - deleteMdlGenArgmtView dlts 
	public ActionForward deleteMdlGenArgmtView(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException,
			FileNotFoundException, IOException {
		
		LogUtil.logMessage("Enters into ModelGenArrangeAction:deleteMdlGenArgmtView");
		String strForwardKey = ApplicationConstants.SUCCESS;
		
		int intStatusCode = 0;
		ModelGenArrangeForm objModelGenArrangeForm = (ModelGenArrangeForm) objActionForm;
		GenArrangeVO objGenArrangeVO = new GenArrangeVO();
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LogUtil.logMessage(objSession);
		
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		LogUtil.logMessage(objLoginVo);
		
		
		String strSpecTypeNo=null;
		String strModleSeqNo=null;
		int specTypeNo = -1;
		int modleSeqNo = -1;
		
			

			
			try {	
			strSpecTypeNo = (String) objHttpServletRequest
			.getParameter("specTypeNo");
	strModleSeqNo = (String) objHttpServletRequest
			.getParameter("modelSeqNo");

	if (strSpecTypeNo != null) {
		objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);
		specTypeNo = Integer.parseInt(strSpecTypeNo);
		objModelGenArrangeForm.setSpecTypeNo(specTypeNo);
	} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
		if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {
			strSpecTypeNo = (String) objSession
					.getAttribute("SPEC_TYPE_NO");
			specTypeNo = Integer.parseInt(strSpecTypeNo);
			objModelGenArrangeForm.setSpecTypeNo(specTypeNo);
		}
	}

	if (strModleSeqNo != null) {
		objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
		modleSeqNo = Integer.parseInt(strModleSeqNo);
		objModelGenArrangeForm.setModelSeqNo(modleSeqNo);
	} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {

		if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
			strModleSeqNo = (String) objSession
					.getAttribute("MODEL_SEQ_NO");
			modleSeqNo = Integer.parseInt(strModleSeqNo);
			objModelGenArrangeForm.setModelSeqNo(modleSeqNo);
		}
	}
		
		objModelGenArrangeForm.setHdPreSelectedModel(objModelGenArrangeForm
				.getModelSeqNo());
	
			objGenArrangeVO.setUserID(objLoginVo.getUserID());
			objGenArrangeVO.setModelSeqNo(objModelGenArrangeForm.getModelSeqNo());
			objGenArrangeVO.setModelViewSeqNo(objModelGenArrangeForm.getHdSelectedMdlViewSeqNo());
			ModelGenArrangeBI objModelGenArrBO = ServiceFactory.getGenArrangeBO();
			intStatusCode = objModelGenArrBO.deleteMdlGenArgmtView(objGenArrangeVO);
			
			LogUtil.logMessage("successMessage in Action:" + intStatusCode);
			
			if (intStatusCode == 0) {
				objModelGenArrangeForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			}else  {
				String strStatusCode=String.valueOf(intStatusCode);
				objModelGenArrangeForm.setMessageID(strStatusCode);
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
				objModelGenArrangeForm.setSpecTypeList(arlSpec);

			}
			/** ***** for Loading Model DropDown code Starts here*************** */
			
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			
			objModelVo.setSpecTypeSeqNo(objModelGenArrangeForm.getSpecTypeNo());
			ModelBI objModelBO = ServiceFactory.getModelBO();
			
			if (objModelBO.fetchModels(objModelVo) != null) {
				
				ArrayList arlModelList = objModelBO.fetchModels(objModelVo);
				
				objModelGenArrangeForm.setModelList(arlModelList);
				
			}
			
			ArrayList arlArraylist = objModelGenArrBO
			.fetchGenArrImages(objGenArrangeVO);
			
			if (arlArraylist != null) {
				objModelGenArrangeForm.setResultList(arlArraylist);
				
			}
			objModelGenArrangeForm.setMdlNewViewName("");
			objModelGenArrangeForm.setMdlNewViewNotes("");
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelGenArrangeAction:deleteMdlGenArgmtView");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
		}
		LogUtil.logMessage("Leaves From ModelGenArrangeAction:deleteMdlGenArgmtView");
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/**
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return
	 * @throws EMDException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	/**
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return
	 * @throws EMDException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	//Added For CR_101 from uploadImage image dlts to UpdateMdlGenArgmntView
	public ActionForward uploadImage(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException,
			FileNotFoundException, IOException {
		
		LogUtil.logMessage("Enters into ModelGenArrangeAction:uploadImage");
		String strForwardKey = ApplicationConstants.SUCCESS;
		boolean blnUpdated = false;
		int intStatusCode = 0;
		ModelGenArrangeForm objModelGenArrangeForm = (ModelGenArrangeForm) objActionForm;
		GenArrangeVO objGenArrangeVO = new GenArrangeVO();
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LogUtil.logMessage(objSession);
		
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		LogUtil.logMessage(objLoginVo);
		
		//CR_84 lines are started here
		String strSpecTypeNo=null;
		String strModleSeqNo=null;
		int specTypeNo = -1;
		int modleSeqNo = -1;
		
			
			
			try {	
			strSpecTypeNo = (String) objHttpServletRequest
			.getParameter("specTypeNo");
	strModleSeqNo = (String) objHttpServletRequest
			.getParameter("modelSeqNo");

	if (strSpecTypeNo != null) {
		objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);
		specTypeNo = Integer.parseInt(strSpecTypeNo);
		objModelGenArrangeForm.setSpecTypeNo(specTypeNo);
	} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
		if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {
			strSpecTypeNo = (String) objSession
					.getAttribute("SPEC_TYPE_NO");
			specTypeNo = Integer.parseInt(strSpecTypeNo);
			objModelGenArrangeForm.setSpecTypeNo(specTypeNo);
		}
	}

	if (strModleSeqNo != null) {
		objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
		modleSeqNo = Integer.parseInt(strModleSeqNo);
		objModelGenArrangeForm.setModelSeqNo(modleSeqNo);
	} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {

		if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
			strModleSeqNo = (String) objSession
					.getAttribute("MODEL_SEQ_NO");
			modleSeqNo = Integer.parseInt(strModleSeqNo);
			objModelGenArrangeForm.setModelSeqNo(modleSeqNo);
		}
	}
	//CR_84 lines are ends here
		
		objModelGenArrangeForm.setHdPreSelectedModel(objModelGenArrangeForm
				.getModelSeqNo());
				
		FileVO objFileVO = new FileVO();
		
			//List myFiles =(List) objModelGenArrangeForm.getMdlNewViewImage();
			
			//LogUtil.logMessage("Length in action .getHdnFileName():" + myFiles.size());
			
				    FormFile objFormFile =objModelGenArrangeForm.getMdlNewViewImage(); 
					
					if (objFormFile.getFileSize() != 0)
					{
						objFileVO.setFileName(objFormFile.getFileName());
						LogUtil.logMessage("FileName in action :"
								+ objFormFile.getFileName());
						
						objFileVO.setFileLength(objFormFile.getFileSize());
						LogUtil.logMessage("FileSize in action :"
								+ objFormFile.getFileSize());
						objFileVO.setFileStream(objFormFile.getInputStream());
						
						objFileVO.setContentType(objFormFile.getContentType());
						LogUtil.logMessage("ContentType in action :"
								+ objFormFile.getContentType());
					}
			
			
			
			objGenArrangeVO.setFileVO(objFileVO);
			objGenArrangeVO.setUserID(objLoginVo.getUserID());
			
			objGenArrangeVO.setModelSeqNo(objModelGenArrangeForm
					.getModelSeqNo());
			objGenArrangeVO.setModelViewSeqNo(objModelGenArrangeForm
					.getHdSelectedMdlViewSeqNo());
			
			objGenArrangeVO.setMdlNewViewName(objModelGenArrangeForm.getMdlNewViewName());
			objGenArrangeVO.setMdlNewViewNotes(objModelGenArrangeForm.getMdlNewViewNotes());
			ModelGenArrangeBI objModelGenArrBO = ServiceFactory
			.getGenArrangeBO();
			
			intStatusCode = objModelGenArrBO.uploadMdlGenArgmntViewDtls(objGenArrangeVO);
			
			LogUtil.logMessage("successMessage in Action:" + intStatusCode);
			
			if (intStatusCode == 0) {
				objModelGenArrangeForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			}else  {
				String strStatusCode=String.valueOf(intStatusCode);
				objModelGenArrangeForm.setMessageID(strStatusCode);
			}
//			CR_84 lines ae started here -passing Screen id
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
//			CR_84 lines ae started here- added screen id 
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
				objModelGenArrangeForm.setSpecTypeList(arlSpec);
//				CR_84 lines ends here
			}
			/** ***** for Loading Model DropDown code Starts here*************** */
			
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			//Added For CR_84
			objModelVo.setSpecTypeSeqNo(objModelGenArrangeForm.getSpecTypeNo());
			ModelBI objModelBO = ServiceFactory.getModelBO();
			/*
			 * Added for LSDB_CR_46 PM&I Change Locomotive Models will only be
			 * populated
			 */
			if (objModelBO.fetchModels(objModelVo) != null) {
				
				ArrayList arlModelList = objModelBO.fetchModels(objModelVo);
				
				objModelGenArrangeForm.setModelList(arlModelList);
				
			}
			
			/** **************** for Loading DropDown code Ends here ********* */
			
			ArrayList arlArraylist = objModelGenArrBO
			.fetchGenArrImages(objGenArrangeVO);
			
			if (arlArraylist != null) {
				objModelGenArrangeForm.setResultList(arlArraylist);
				
			}
			objModelGenArrangeForm.setMdlNewViewName("");
			objModelGenArrangeForm.setMdlNewViewNotes("");
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelGenArrangeAction:UpdateMdlGenArgmntView");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
		}
		LogUtil.logMessage("Leaves From ModelGenArrangeAction:UpdateMdlGenArgmntView");
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	
}
