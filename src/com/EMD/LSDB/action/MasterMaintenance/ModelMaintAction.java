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
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.form.MasterMaintenance.ModelMaintForm;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.SpecTypeVo;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This Action class has the business methods for the Model
 *          *********************************************** ```
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

public class ModelMaintAction extends EMDAction {
	
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
		int specTypeNo=-1;
		String strSpecTypeNo=null;
		LogUtil.logMessage("Entering ModelMaintAction.initLoad");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ModelMaintForm objModelMaintForm = (ModelMaintForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		try {
			
			
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types specTypeNo :" + specTypeNo);
			objModelMaintForm.setSpecTypeList(arlSpec);
//			CR_83 lines are started here
			
			LogUtil.logMessage("ModelMaintAction+ initLoad: before setting into form specTypeNo" + specTypeNo);
			if(objSession.getAttribute("SPEC_TYPE_NO")!=null)
			{
				strSpecTypeNo=(String)objSession.getAttribute("SPEC_TYPE_NO");
				specTypeNo=Integer.parseInt(strSpecTypeNo);
				LogUtil.logMessage("ModelMaintAction+ initLoad: before setting into form specTypeNo from seesion" + specTypeNo);
				objModelMaintForm.setSpecTypeNo(specTypeNo);
			}else if(specTypeNo!=-1 && specTypeNo!=0 && objSession.getAttribute("SPEC_TYPE_NO")==null)
			{
				objModelMaintForm.setSpecTypeNo(specTypeNo);
				strSpecTypeNo=(String) objHttpServletRequest.getParameter("specTypeNo");
				objSession.setAttribute("SPEC_TYPE_NO",strSpecTypeNo);
				specTypeNo=Integer.parseInt(strSpecTypeNo);
				objModelMaintForm.setSpecTypeNo(specTypeNo);
				LogUtil.logMessage("ModelMaintAction+ initLoad: before setting into form specTypeNo from request" + specTypeNo);
			}
			
			//Added for CR_118
			objModelMaintForm.setRoleID(objLoginVo.getRoleID());
			objModelMaintForm.setHideUnhideModel(0);
			//Added for CR_118 Ends
			
				//strSpecTypeNo=(String) objHttpServletRequest.getParameter("specTypeNo");
			// objModelMaintForm.setHideMessage("fromInitLoad");
//			CR_83 lines are ends here
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
	 * This method is for fetching the models
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
		
		LogUtil.logMessage("Entering ModelMaintAction.fetchModels");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ModelMaintForm objModelMaintForm = (ModelMaintForm) objActionForm;
		String strUserID = null;
		int specTypeNo=-1;
		String strSpecTypeNo=null;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			ArrayList arlModel = new ArrayList();
			ModelVo objModelVo = new ModelVo();
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			ArrayList arlSpecList = new ArrayList();
			
			// Getting User from the session
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			objSpecTypeVo.setUserID(strUserID);
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpecList = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpecList);
			objModelMaintForm.setSpecTypeList(arlSpecList);
			specTypeNo=objModelMaintForm.getSpecTypeNo();
			//CR_83 - holding spectypeno in the session 
			//CR_83 -lines are started  here
			if(specTypeNo!=-1 && specTypeNo!=0)
			{
				
				
				objModelMaintForm.setSpecTypeNo(specTypeNo);
				strSpecTypeNo=(String) objHttpServletRequest.getParameter("specTypeNo");
				objSession.setAttribute("SPEC_TYPE_NO",strSpecTypeNo);
				specTypeNo=Integer.parseInt(strSpecTypeNo);
				objModelMaintForm.setSpecTypeNo(specTypeNo);
			}
//			CR_83 -lines are started  here
			objModelVo.setSpecTypeSeqNo(objModelMaintForm.getSpecTypeNo());
			// Added for CR_101 to set the same value in To Model Specification type drop down
			objModelMaintForm.setToModelSpecTypeNo(specTypeNo);
			//CR_101 Ends here
			// Commented for LSDB_CR_56
			// objModelVo.setModelCustTypeSeqNo(objModelMaintForm.getModCustTypeSeqNo());
			
			//Added for CR_118 for fetching All models
			if(objModelMaintForm.isHdnModelMaintScreen()){
				LogUtil.logMessage("Model maintenance Screen");
				objModelVo.setModelFlag(ApplicationConstants.All_MODELS);
			}
			//Added for CR_118 Ends for fetching All models
			
			objModelVo.setUserID(strUserID);
			LogUtil
			.logMessage("((ModelMaintForm) objActionForm).getSpecTypeSeqNo() of ModelMaintAction :"
					+ objModelMaintForm.getSpecTypeNo());
			LogUtil
			.logMessage("Inside the fetchModels method of ModelMaintAction");
			ModelBI objModelBI = ServiceFactory.getModelBO();
			arlModel = objModelBI.fetchModels(objModelVo);
			objModelMaintForm.setModelList(arlModel);
			
			objModelMaintForm.setSpecTypeNo(objModelMaintForm.getSpecTypeNo());
			
			// Commented for LSDB_CR_56
			// objModelMaintForm.setModCustTypeSeqNo(objModelMaintForm.getModCustTypeSeqNo());
			
			objModelMaintForm.setHdnPrevSelSpec(objModelMaintForm
					.getSpecTypeNo());
			
			// Commented for LSDB_CR_56
			// objModelMaintForm.setHdnPrevSelCusSeq(objModelMaintForm.getModCustTypeSeqNo());
			
			//Added for CR_118
			objModelMaintForm.setRoleID(objLoginVo.getRoleID());
			objModelMaintForm.setHideUnhideModel(0);
			//Added for CR_118 Ends

			
			LogUtil.logMessage("Model List Size .." + arlModel.size());
		} catch (Exception objEx) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***************************************************************************
	 * This method is for inserting a new model
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
	
	public ActionForward insertModel(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException, ApplicationException {
		
		LogUtil.logMessage("Entering ModelMaintAction.insertModel");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ModelMaintForm objModelMaintForm = (ModelMaintForm) objActionForm;
		String strUserID = null;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			ArrayList arlModel = new ArrayList();
			int intSuccess = 0;
			String strModelName = null;
			String strModelDesc = null;
			String strHorsePower = null;
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			ArrayList arlSpecList = new ArrayList();
			ModelVo objModelVo = new ModelVo();
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			
			arlSpecList = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpecList);
			
			objModelMaintForm.setSpecTypeList(arlSpecList);
			
			// Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			strModelName = ApplicationUtil.trim(objModelMaintForm.getModName());
			strModelDesc = ApplicationUtil.trim(objModelMaintForm.getModDesc());
			strHorsePower = ApplicationUtil.trim(objModelMaintForm
					.getHorsePower());
			
			objModelVo.setSpecTypeSeqNo(((ModelMaintForm) objActionForm)
					.getSpecTypeNo());
			// Commented for LSDB_CR_56
			// objModelVo.setModelCustTypeSeqNo(((ModelMaintForm)
			// objActionForm).getModCustTypeSeqNo());
			objModelVo.setModelName(strModelName);
			objModelVo.setModelDesc(strModelDesc);
			objModelVo.setHpowerRate(strHorsePower);
			objModelVo.setUserID(strUserID);
			
			//Added for CR_97 for Change Control Type
			objModelVo.setChngCtrlTypeFlag(objModelMaintForm.getChangeControlFlag());
			//CR_97 Ends here
			ModelBI objModelBI = ServiceFactory.getModelBO();
			
			intSuccess = objModelBI.insertModel(objModelVo);
			
			objModelMaintForm.setModName("");
			objModelMaintForm.setHorsePower("");
			objModelMaintForm.setModDesc("");
			objModelVo = new ModelVo();
			objModelVo.setSpecTypeSeqNo(((ModelMaintForm) objActionForm)
					.getSpecTypeNo());
			//Added for CR_118 
			objModelVo.setModelFlag(ApplicationConstants.All_MODELS);
			//Added for CR_118 Ends
			
			// Commented for LSDB_CR_56
			// objModelVo.setModelCustTypeSeqNo(((ModelMaintForm)
			// objActionForm).getModCustTypeSeqNo());
			
			objModelMaintForm.setHdnPrevSelSpec(objModelMaintForm
					.getSpecTypeNo());
			
			// Commented for LSDB_CR_56
			// objModelMaintForm.setHdnPrevSelCusSeq(objModelMaintForm.getModCustTypeSeqNo());
			
			if (intSuccess == 0) {
				objModelMaintForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objModelMaintForm.setMessageID("" + intSuccess);
			}
			
			arlModel = objModelBI.fetchModels(objModelVo);
			objModelMaintForm.setModelList(arlModel);
			//Added for CR_118
			objModelMaintForm.setRoleID(objLoginVo.getRoleID());
			objModelMaintForm.setHideUnhideModel(0);
			//Added for CR_118 Ends
			
		} catch (Exception objEx) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForwardKey);
	}
	
	public ActionForward updateModel(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering ModelMaintAction.updateModel");
		String strForwardKey = ApplicationConstants.SUCCESS;
		String strUserID = null;
		ModelMaintForm objModelMaintForm = (ModelMaintForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			ArrayList arlModel = new ArrayList();
			String strModelName = null;
			String strModelDesc = null;
			String strHorsePower = null;
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpecList = new ArrayList();
			int intSuccess = 0;
			
			ModelVo objModelVo = new ModelVo();
			
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			
			arlSpecList = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpecList);
			
			objModelMaintForm.setSpecTypeList(arlSpecList);
			
			// Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			
			strModelName = ApplicationUtil.trim(objModelMaintForm
					.getHdnModelName());
			strModelDesc = ApplicationUtil.trim(objModelMaintForm
					.getHdnModelDesc());
			strHorsePower = ApplicationUtil.trim(objModelMaintForm
					.getHdnHorsePower());
			
			LogUtil.logMessage("strModelDesc :" + strModelDesc);
			
			objModelVo
			.setSpecTypeSeqNo(objModelMaintForm.getHdnSpecTypeSeqNo());
			
			// Commented for LSDB_CR_56
			// objModelVo.setModelCustTypeSeqNo(objModelMaintForm.getHdnCusTypeSeqNo());
			
			objModelVo.setModelSeqNo(objModelMaintForm.getModelSeqNo());
			objModelVo.setModelName(strModelName);
			objModelVo.setModelDesc(strModelDesc);
			objModelVo.setHpowerRate(strHorsePower);
			objModelVo.setUserID(strUserID);
			
			//Added for CR_97 for Change Control Type
			objModelVo.setChngCtrlTypeFlag(objModelMaintForm.getHdnChangeControlFlag());
			
			//CR_97 Ends here
			
			//Added for CR_118
			LogUtil.logMessage("value of objModelMaintForm.getHdnModelFlag() "+objModelMaintForm.getHdnModelFlag()+"check");
			LogUtil.logMessage("value of objModelMaintForm.isHideUnhideModel() "+objModelMaintForm.getHideUnhideModel()+"check");
			if(objModelMaintForm.getHideUnhideModel() == 1){
				if(objModelMaintForm.getHdnModelFlag() != null && !objModelMaintForm.getHdnModelFlag().equalsIgnoreCase("")){
					LogUtil.logMessage("in the if loop");	
					if(objModelMaintForm.getHdnModelFlag().equalsIgnoreCase("Y")){
						objModelVo.setModelFlag("N");
					}else{
						objModelVo.setModelFlag("Y");
					}
				}
			}else{
				objModelVo.setModelFlag(objModelMaintForm.getHdnModelFlag());
			}
			
			//Added for CR_118 Ends
			ModelBI objModelBI = ServiceFactory.getModelBO();
			intSuccess = objModelBI.updateModel(objModelVo);
			
			objModelMaintForm.setModName("");
			objModelMaintForm.setHorsePower("");
			objModelMaintForm.setModDesc("");
			
			objModelVo = new ModelVo();
			objModelVo.setSpecTypeSeqNo(((ModelMaintForm) objActionForm)
					.getSpecTypeNo());
			
			// Commented for LSDB_CR_56
			// objModelVo.setModelCustTypeSeqNo(((ModelMaintForm)
			// objActionForm).getModCustTypeSeqNo());
			objModelVo.setUserID(strUserID);
			
			objModelMaintForm.setHdnPrevSelSpec(objModelMaintForm
					.getSpecTypeNo());
			
			// Commented for LSDB_CR_56
			// objModelMaintForm.setHdnPrevSelCusSeq(objModelMaintForm.getModCustTypeSeqNo());
			
			//Added for CR_118
			objModelMaintForm.setRoleID(objLoginVo.getRoleID());
			objModelVo.setModelFlag(ApplicationConstants.All_MODELS);
			objModelMaintForm.setHideUnhideModel(0);
			//Added for CR_118 Ends
			
			if (intSuccess == 0) {
				objModelMaintForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objModelMaintForm.setMessageID("" + intSuccess);
			}
			arlModel = objModelBI.fetchModels(objModelVo);
			objModelMaintForm.setModelList(arlModel);
			
		} catch (Exception objEx) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForwardKey);
		
	}
/***************************************************************************
 * This method is for Copy Model 
 * 
 * @author Satyam Computer Services Ltd
 * @version 1.0
 * @param objActionMapping
 * @param objActionForm
 * @param objHttpServletRequest
 * @param objHttpServletResponse
 * @return ActionForward
 * @throws ApplicationException
 *************************************************************************/

	public ActionForward copyModel(ActionMapping objActionMapping,
		ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
		HttpServletResponse objHttpServletResponse)
	throws BusinessException, ApplicationException {
	
	LogUtil.logMessage("Entering ModelMaintAction.copyModel");
	String strForwardKey = ApplicationConstants.SUCCESS;
	ModelMaintForm objModelMaintForm = (ModelMaintForm) objActionForm;
	String strUserID = null;
	try {
		HttpSession objSession = objHttpServletRequest.getSession(false);
		ArrayList arlModel = new ArrayList();
		ArrayList arlSpecList = new ArrayList();
		
		int intSuccess = 0;
		
		String strModelName = null;
		String strModelDesc = null;
		String strHorsePower = null;
     	int toModelSpecTypeNo=objModelMaintForm.getToModelSpecTypeNo();
		
		SpecTypeVo objSpecTypeVo = new SpecTypeVo();		
		ModelVo objModelVo = new ModelVo();
		
		LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
		
        //Getting User from the session
		if (objLoginVo != null) {
			
			strUserID = objLoginVo.getUserID();
		}
		
		
		objSpecTypeVo.setUserID(strUserID);
		
		SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
		arlSpecList = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);		
		objModelMaintForm.setSpecTypeList(arlSpecList);
		
		LogUtil.logMessage("objArrList for Spec Types :" + arlSpecList);
		
			
			
		strModelName = ApplicationUtil.trim(objModelMaintForm.getModName());
		strModelDesc = ApplicationUtil.trim(objModelMaintForm.getModDesc());
		strHorsePower = ApplicationUtil.trim(objModelMaintForm
				.getHorsePower());
		//Modified for CR_101 to enable copy model across specification types
		objModelVo.setSpecTypeSeqNo(toModelSpecTypeNo);
		//Ends here
		objModelVo.setModelSeqNo(objModelMaintForm.getModelSeqNo());		
		objModelVo.setModelName(strModelName);
		objModelVo.setModelDesc(strModelDesc);
		objModelVo.setHpowerRate(strHorsePower);
		objModelVo.setUserID(strUserID);
		// Added for CR_97 for Change Control Type
		objModelVo.setChngCtrlTypeFlag(objModelMaintForm.getCopyChangeControl());
		// CR_97 Ends here
		
		ModelBI objModelBI = ServiceFactory.getModelBO();
		intSuccess = objModelBI.copyModel(objModelVo);
		
		objModelMaintForm.setModName("");
		objModelMaintForm.setHorsePower("");
		objModelMaintForm.setModDesc("");
		
		//Modified for CR_101 to enable copy model across specification types
		objModelMaintForm.setSpecTypeNo(toModelSpecTypeNo);
		objModelMaintForm.setHdnSpecTypeSeqNo(objModelMaintForm.getToModelSpecTypeNo());
		//Ends here
		
		if (intSuccess == 0) {
			objModelMaintForm
			.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
		} else {
			objModelMaintForm.setMessageID("" + intSuccess);
		}
		
		objModelVo = new ModelVo();
		objModelVo.setUserID(strUserID);
		//Modified for CR_101 to enable copy model across specification types
		objModelVo.setSpecTypeSeqNo(toModelSpecTypeNo);
		//Ends here
		arlModel = objModelBI.fetchModels(objModelVo);
		LogUtil.logMessage("objArrList for fetch models :" + arlModel);

	
	if (arlModel != null && arlModel.size() > 0) {
		objModelMaintForm.setModelList(arlModel);
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
