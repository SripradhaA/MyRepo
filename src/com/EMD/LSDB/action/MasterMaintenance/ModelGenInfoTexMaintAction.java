/*
 * Created on Jun 21, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
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
import com.EMD.LSDB.bo.serviceinterface.ModelBI;
import com.EMD.LSDB.bo.serviceinterface.SpecTypeBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.form.MasterMaintenance.MdlGenInfoTexMaintForm;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.SpecTypeVo;
import com.EMD.LSDB.vo.common.SpecificationItemVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This Action class has the business methods for the Model
 *          Specification
 ******************************************************************************/
/***************************************************************************
 * ------------------------------------------------------------------------------------------------------
 *     Date         version  modified by        comments                                Remarks 
 * 16/03/2012        1.0      SD41630       Added for model Genaral
 *                                          info text Darft and Open maintanance        Added for CR_106
 * ----------------------------------------------------------------------------------------------------------
 **************************************************************************/
public class ModelGenInfoTexMaintAction extends EMDAction {
	
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
		
		LogUtil.logMessage("Entering ModelGenInfoTexMaintAction.initLoad");
		String strForwardKey = ApplicationConstants.SUCCESS;
		MdlGenInfoTexMaintForm objMdlGenInfoTexMaintForm = (MdlGenInfoTexMaintForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
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
		LogUtil.logMessage("value of strSpecTypeNo from  session"
				+ strSpecTypeNo);
		objMdlGenInfoTexMaintForm.setSpecTypeNo(specTypeNo);

	} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
		if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {

				strSpecTypeNo = (String) objSession
					.getAttribute("SPEC_TYPE_NO");
			specTypeNo = Integer.parseInt(strSpecTypeNo);
			objMdlGenInfoTexMaintForm.setSpecTypeNo(specTypeNo);
			
		}
	}

	if (strModleSeqNo != null) {

		objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
		modleSeqNo = Integer.parseInt(strModleSeqNo);
		LogUtil.logMessage("value of MODEL_SEQ_NO from session "
				+ modleSeqNo);
		objMdlGenInfoTexMaintForm.setModelSeqNo(modleSeqNo);

	} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {

		if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
			strModleSeqNo = (String) objSession
					.getAttribute("MODEL_SEQ_NO");
			modleSeqNo = Integer.parseInt(strModleSeqNo);
			objMdlGenInfoTexMaintForm.setModelSeqNo(modleSeqNo);
		}
	}			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpec);
			objMdlGenInfoTexMaintForm.setSpecTypeList(arlSpec);
			// objModelMaintForm.setHideMessage("fromInitLoad");
			
			//Added For CR_106 Models not loading
			if (specTypeNo != -1 && specTypeNo != 0) {

				LogUtil.logMessage("in side modleSeqNo" + modleSeqNo);

				ModelVo objModelVo = new ModelVo();
				objModelVo.setUserID(objLoginVo.getUserID());
				objModelVo.setSpecTypeSeqNo(specTypeNo);
				ModelBI objModelBO = ServiceFactory.getModelBO();
				if (objModelBO.fetchModels(objModelVo) != null) {
					arlModelList = objModelBO.fetchModels(objModelVo);
					objMdlGenInfoTexMaintForm.setModelList(arlModelList);
					objMdlGenInfoTexMaintForm.setModelSeqNo(modleSeqNo);
					LogUtil.logMessage("end of the if modleSeqNo" + modleSeqNo);
				}

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
	 * This method is for loading the the specifiaction home page
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
	
	public ActionForward fetchModels(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil.logMessage("Entering ModelGenInfoTexMaintAction.fetchModels");
		String strForwardKey = ApplicationConstants.SUCCESS;
		MdlGenInfoTexMaintForm objMdlGenInfoTexMaintForm = (MdlGenInfoTexMaintForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		try {
			
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpec);
			objMdlGenInfoTexMaintForm.setSpecTypeList(arlSpec);
			objMdlGenInfoTexMaintForm.setSpecTypeNo(objMdlGenInfoTexMaintForm
					.getSpecTypeNo());
			objMdlGenInfoTexMaintForm
			.setHdnSelSpecType(objMdlGenInfoTexMaintForm
					.getHdnSelSpecType());
						
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			
			// Added for CR- 46 PM&I Spec to load Models based on Spec Type
			objModelVo.setSpecTypeSeqNo(objMdlGenInfoTexMaintForm
					.getSpecTypeNo());
			
			ModelBI objModelBO = ServiceFactory.getModelBO();
			ArrayList arlModel = objModelBO.fetchModels(objModelVo);
			if(arlModel.size()>0){
				LogUtil.logMessage("objMdlGenInfoTexMaintForm.getMethod() :"
						+ arlModel.size());
			objMdlGenInfoTexMaintForm.setModelList(arlModel);
			objMdlGenInfoTexMaintForm.setMethod(" ");
			}else{
			objMdlGenInfoTexMaintForm.setMethod("fetchModels");
			}
			LogUtil.logMessage("objMdlGenInfoTexMaintForm.getMethod() :"
					+ objMdlGenInfoTexMaintForm.getMethod());
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
	 * This method is for fetching the SpecificationItems
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

	public ActionForward fetchGenInfoTextItems(
			ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil
		.logMessage("Entering ModelGenInfoTexMaintAction.fetchSpecificationItems");
		String strForwardKey = ApplicationConstants.SUCCESS;
		MdlGenInfoTexMaintForm objMdlGenInfoTexMaintForm = (MdlGenInfoTexMaintForm) objActionForm;
		//SpecificationVO objSpecificationVO = new SpecificationVO();
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		int specTypeNo=-1;
		String strSpecTypeNo=null;
		String strModleSeqNo=null;
		try {
			
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpecType = new ArrayList();
			
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpecType = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpecType);
			objMdlGenInfoTexMaintForm.setSpecTypeList(arlSpecType);
			objMdlGenInfoTexMaintForm.setSpecTypeNo(objMdlGenInfoTexMaintForm
					.getSpecTypeNo());
			objMdlGenInfoTexMaintForm
			.setHdnSelSpecType(objMdlGenInfoTexMaintForm
					.getHdnSelSpecType());
			
			
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			
			// Added for CR-46 PM&I Spec
			objModelVo.setSpecTypeSeqNo(objMdlGenInfoTexMaintForm
					.getSpecTypeNo());
			
			ModelBI objModelBO = ServiceFactory.getModelBO();
			ArrayList arlModel = objModelBO.fetchModels(objModelVo);
			objMdlGenInfoTexMaintForm.setModelList(arlModel);
			objMdlGenInfoTexMaintForm.setModelSeqNo(objMdlGenInfoTexMaintForm
					.getModelSeqNo());
			objMdlGenInfoTexMaintForm
			.setHdnPrevSelModel(objMdlGenInfoTexMaintForm
					.getModelSeqNo());
						objMdlGenInfoTexMaintForm.setModSpecList(arlModel);
			objMdlGenInfoTexMaintForm.setSpecTypeNo(objMdlGenInfoTexMaintForm
					.getSpecTypeNo());
			objMdlGenInfoTexMaintForm.setModelSeqNo(objMdlGenInfoTexMaintForm
					.getModelSeqNo());
			objMdlGenInfoTexMaintForm
			.setHdnSelSpecType(objMdlGenInfoTexMaintForm
					.getHdnSelSpecType());
			LogUtil.logMessage("objArrayListSpec :" + arlModel);
//			CR_83 holding the value from form in the sesiion 
			
			//objModelMaintForm.setSpecTypeNo(specTypeNo);
			strSpecTypeNo=(String) objHttpServletRequest.getParameter("specTypeNo");
			strModleSeqNo=(String) objHttpServletRequest.getParameter("modelSeqNo");
		
			objSession.setAttribute("SPEC_TYPE_NO",strSpecTypeNo);
			objSession.setAttribute("MODEL_SEQ_NO",strModleSeqNo);
			
				//CR_83 lines are ends here
		} catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***************************************************************************
	 * This method is for updating item for a specification
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
	
	public ActionForward updateGenInfoTextDraftAndOpen(
			ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil
		.logMessage("Entering ModelGenInfoTexMaintAction.updateGenInfoTextDraftAndOpen");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intSuccess = 0;
		MdlGenInfoTexMaintForm objMdlGenInfoTexMaintForm = (MdlGenInfoTexMaintForm) objActionForm;
		//SpecificationVO objSpecificationVO = new SpecificationVO();
		SpecificationItemVO objSpecificationItemVO = new SpecificationItemVO();
		
		String strUserID = null;
		try {
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpecType = new ArrayList();
			
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpecType = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpecType);
			objMdlGenInfoTexMaintForm.setSpecTypeList(arlSpecType);
			objMdlGenInfoTexMaintForm.setSpecTypeNo(objMdlGenInfoTexMaintForm
					.getSpecTypeNo());
			objMdlGenInfoTexMaintForm
			.setHdnSelSpecType(objMdlGenInfoTexMaintForm
					.getHdnSelSpecType());
			
			
			ModelVo objModelVo = new ModelVo();
			
			objModelVo.setUserID(objLoginVo.getUserID());
			
			// Added for CR-46
			objModelVo.setSpecTypeSeqNo(objMdlGenInfoTexMaintForm
					.getSpecTypeNo());
			objModelVo.setModelSeqNo(objMdlGenInfoTexMaintForm.getModelSeqNo());
					
			objModelVo.setGenInfoTextDraft(objMdlGenInfoTexMaintForm.getGenInfoTextDraft());
			objModelVo.setGenInfoTextOpen(objMdlGenInfoTexMaintForm.getGenInfoTextOpen());
			ModelBI objModelBO = ServiceFactory.getModelBO();
			
			intSuccess = objModelBO.updateModel(objModelVo);
			objModelVo.setIntSuccess(intSuccess);
			ArrayList arlModel = objModelBO.fetchModels(objModelVo);
			objMdlGenInfoTexMaintForm.setModelList(arlModel);
			objMdlGenInfoTexMaintForm.setModelSeqNo(objMdlGenInfoTexMaintForm
					.getModelSeqNo());
			objMdlGenInfoTexMaintForm.setModSpecList(arlModel);
			// Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			
			
			objModelVo.setUserID(strUserID);
			LogUtil.logMessage("strUserID :" + strUserID);
			
			
			if (intSuccess == 0) {
				objMdlGenInfoTexMaintForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objMdlGenInfoTexMaintForm.setMessageID("" + intSuccess);
			}
			
			
			
			
			
		} catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
	}
	

	

}
