package com.EMD.LSDB.action.MasterMaintenance;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.EMD.LSDB.action.common.EMDAction;
import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.CustomerBI;
import com.EMD.LSDB.bo.serviceinterface.ModelAppendixBI;
import com.EMD.LSDB.bo.serviceinterface.ModelBI;
import com.EMD.LSDB.bo.serviceinterface.ModelSubSectionBI;
import com.EMD.LSDB.bo.serviceinterface.SectionBI;
import com.EMD.LSDB.bo.serviceinterface.SpecTypeBI;
import com.EMD.LSDB.bo.serviceinterface.ViewSpecByModelBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.form.MasterMaintenance.CustOptCatForm;
import com.EMD.LSDB.form.MasterMaintenance.MasterSpecByMdlForm;
//import com.EMD.LSDB.form.MasterMaintenance.MasterMaintReportForm;
// com.EMD.LSDB.form.MasterMaintenance.MasterSpecByMdlForm;
//import com.EMD.LSDB.form.SpecMaintenance.ModifySpecForm;
import com.EMD.LSDB.vo.common.CustomerVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.ModelAppendixVO;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SpecTypeVo;
import com.EMD.LSDB.vo.common.SubSectionVO;

/*******************************************************************************************
 * @author  Satyam Computer Services Ltd  
 * @version 1.0  
 * This Action class has the methods for retriveing the models and model specifications for
 * Master Maintenence report.
 ******************************************************************************************/

public class CustOptCatAction extends EMDAction {
	
	/***********************************************************************************************
	 * This method is used to retrieve the list of all Specification Types Added for CR-46 PM&I Spec
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException,BusinessException
	 **********************************************************************************************/
	
	public ActionForward initLoad(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		
		LogUtil.logMessage("Inside CustOptCatAction:initLoad");
		
		String strForwardKey = ApplicationConstants.SUCCESS;
		CustOptCatForm objCustOptCatForm = (CustOptCatForm) objActionForm;
		try {
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpec);
			objCustOptCatForm.setSpecTypeList(arlSpec);
			
		} catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***********************************************************************************************
	 * This method is used to retrieve the list of all models 
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException,BusinessException
	 **********************************************************************************************/
	
	public ActionForward fetchModels(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		
		/** Declarations **/
		LogUtil.logMessage("Inside CustOptCatAction:fetchModels");
		ArrayList modelList;
		String strForwardKey = ApplicationConstants.SUCCESS;
		CustOptCatForm objCustOptCatForm = (CustOptCatForm) objActionForm;
		ModelVo objModelVO = new ModelVo();
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			objModelVO.setUserID(objLoginVo.getUserID());
			
			/****Added for CR-46 PM&I Spec for loading Specification Type Drop Down - Starts Here****/
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			
			objCustOptCatForm.setSpecTypeList(arlSpec);
			objCustOptCatForm.setSpecTypeNo(objCustOptCatForm
					.getSpecTypeNo());
			/****Added for CR-46 PM&I Spec for loading Specification Type Drop Down - Ends Here****/
			
			//Added for CR-46 PM&I Spec to load mOdels based on Spec TYpe
			objModelVO.setSpecTypeSeqNo(objCustOptCatForm
					.getSpecTypeNo());
			LogUtil.logMessage("Spec Type Seq No"
					+ objCustOptCatForm.getSpecTypeNo());
			/** Fetching all the models available from the DB **/
			
			ModelBI objModelBO = ServiceFactory.getModelBO();
			modelList = objModelBO.fetchModels(objModelVO);
			
			/** Set the model list to the action form **/
			
			objCustOptCatForm.setModelList(modelList);
			objCustOptCatForm.setSpecTypeNo(objCustOptCatForm
					.getSpecTypeNo());
			objCustOptCatForm.setModelSeqNo(objCustOptCatForm
					.getModelSeqNo());
			//Added for CR-118
			objCustOptCatForm.setModelName("");
			objCustOptCatForm.setHdnSelSpecType("");
			//objCustOptCatForm.setShowDeletedClauses("Y");
			
		} catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		
		return objActionMapping.findForward(strForwardKey);
	}
	/***********************************************************************************************************
	 * This method is to used to view Customer Option Catalog for the selected model 
	 * and show it in the Pop up screen
	 * Added for LSDB_CR-77
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException,BusinessException
	 ************************************************************************************************************/
	
	public ActionForward viewCustomerOptionCatalog(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		
		/** Declarations **/
		LogUtil
		.logMessage("Inside CustOptCatAction:viewCustomerOptionCatalog");
		String strForwardKey = ApplicationConstants.SUCCESS;
		
		String modelName = null;
		String specType = null;
		String AM_PM = "";
		int modelSeqNo;
		int specTypeSeqNo;
		int saveCOC;
		ArrayList arlCustomerOption = new ArrayList();
		ArrayList arlImageList = new ArrayList();
		ModelVo objModelVO = new ModelVo();
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		try {
			CustOptCatForm objCustOptCatForm = (CustOptCatForm) objActionForm;
			modelSeqNo = Integer.parseInt(objHttpServletRequest
					.getParameter("modelSeqNo"));
			modelName = objHttpServletRequest.getParameter("modelName");
					
			specType = objHttpServletRequest
			.getParameter(ApplicationConstants.SELECTED_SPEC_TYPE);
			
			LogUtil.logMessage("Model Seq No :" + modelSeqNo);
			LogUtil.logMessage("ModelName :" + modelName);
			LogUtil.logMessage("specType :" + specType);
			//Added for CR_118
			specTypeSeqNo = Integer.parseInt(objHttpServletRequest.getParameter(ApplicationConstants.SPEC_TYPE_NO));
			saveCOC = Integer.parseInt(objHttpServletRequest.getParameter("saveCOC"));
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			objModelVO.setModelSeqNo(modelSeqNo);
			objModelVO.setUserID(objLoginVo.getUserID());
			
					
			/** Fetching the sections available for the selected model **/
			
			ViewSpecByModelBI objViewSpecByModelBO = ServiceFactory
			.getViewSpecByModelBO();
			
			arlCustomerOption = objViewSpecByModelBO.viewCustomerOptionCatalog(objModelVO);
			
			/** Setting the Values to the action form **/
						
			objCustOptCatForm.setCusOptionCatalogList(arlCustomerOption);
			//This part is for displaying Model Appendix images
			ModelAppendixVO objModelAppendixVO = new ModelAppendixVO();
			objModelAppendixVO.setModelSeqNo(modelSeqNo);
			objModelAppendixVO.setUserID(objLoginVo.getUserID());
			ModelAppendixBI objModelAppendixBO = ServiceFactory
			.getModelAppendixBO();
			arlImageList = objModelAppendixBO
			.fetchAppendixImages(objModelAppendixVO);
			
			if (arlImageList != null && arlImageList.size() > 0) {
				
				objCustOptCatForm.setListImages(arlImageList);
			}
			
			objCustOptCatForm.setModelName(modelName);
			objCustOptCatForm.setHdnSelSpecType(specType);
			//Report generation date
			SimpleDateFormat sdf = new SimpleDateFormat();
	        sdf.applyPattern("MM/dd/yyyy HH:mm:ss");
	        Calendar calendar = Calendar.getInstance();
	        String currentTime = sdf.format(calendar.getTime());
	        if(calendar.get(Calendar.AM_PM) == 0)
	        	AM_PM = "AM";
	        else
	        	AM_PM = "PM";
		                  
	        objCustOptCatForm.setReportCreationDate(currentTime+" "+AM_PM);
			
		} catch (Exception objEx) {
			objEx.printStackTrace();
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
	}
	
	//Added for CR_118
	public ActionForward editCompGroupInCOC(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		
		LogUtil.logMessage("Inside CustOptCatAction:editCompGroupInCOC");
		
		String strForwardKey = ApplicationConstants.SUCCESS;
		String modelName = null;
		String specType = null;
		int specTypeSeqNo;
		int modelSeqNo;
		ArrayList arlCompGroups=new ArrayList();
		ModelVo objModelVO = new ModelVo();
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		try {
			
			CustOptCatForm objCustOptCatForm = (CustOptCatForm) objActionForm;
			
			modelSeqNo = Integer.parseInt(objHttpServletRequest
					.getParameter("modelSeqNo"));
			modelName = objHttpServletRequest.getParameter("modelName");
					
			specType = objHttpServletRequest
			.getParameter(ApplicationConstants.SELECTED_SPEC_TYPE);
			
			
			
			specTypeSeqNo = Integer.parseInt(objHttpServletRequest.getParameter(ApplicationConstants.SPEC_TYPE_NO));
			
			LogUtil.logMessage("Model Seq No :" + modelSeqNo);
			LogUtil.logMessage("ModelName :" + modelName);
			LogUtil.logMessage("specType :" + specType);
			LogUtil.logMessage("specTypeSeqNo :" + specTypeSeqNo);
			
			LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			objModelVO.setModelSeqNo(modelSeqNo);
			objModelVO.setModelName(modelName);
			objModelVO.setUserID(objLoginVo.getUserID());
			
			ViewSpecByModelBI objViewSpecByModelBO = ServiceFactory
			.getViewSpecByModelBO();
			arlCompGroups = objViewSpecByModelBO.editCompGroupInCOC(objModelVO);
			if(arlCompGroups.size()>0){
				objCustOptCatForm.setCompGroupList(arlCompGroups);
			}else{
				objCustOptCatForm.setMessageID(ApplicationConstants.NO_RECORDS_FOUND);
			}
			
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpec);
			objCustOptCatForm.setSpecTypeList(arlSpec);
			
			objModelVO =new ModelVo();
			
			objModelVO.setSpecTypeSeqNo(objCustOptCatForm
					.getSpecTypeNo());
			LogUtil.logMessage("Spec Type Seq No"
					+ objCustOptCatForm.getSpecTypeNo());
			/** Fetching all the models available from the DB **/
			
			ModelBI objModelBO = ServiceFactory.getModelBO();
			ArrayList modelList = objModelBO.fetchModels(objModelVO);
			
			/** Set the model list to the action form **/
			
			objCustOptCatForm.setModelList(modelList);
			
			
			objCustOptCatForm.setModelName(modelName);
			objCustOptCatForm.setModelSeqNo(modelSeqNo);
			objCustOptCatForm.setHdnSelSpecType(specType);
			objCustOptCatForm.setSpecTypeNo(specTypeSeqNo);
			
		} catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		
		return objActionMapping.findForward(strForwardKey);
	}
	/*
	public ActionForward updateCompGroupsInCOC(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		
		LogUtil.logMessage("Inside CustOptCatAction:updateCompGroupsInCOC");
		
		String strForwardKey = ApplicationConstants.SUCCESS;
		String modelName = null;
		String specType = null;
		int specTypeSeqNo;
		int modelSeqNo;
		ArrayList arlCompGroups=new ArrayList();
		ModelVo objModelVO = new ModelVo();
		int updateStatus;
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		try {
			
			int componentGrplength =objHttpServletRequest.getParameterValues("hdnCompGroupSeqNo").length;
			int[] componentGrpSeqNos = new int[componentGrplength];
			String[] tempComponentGrpSeqNos =new String[componentGrplength];
			String[] dispInCOCFlags =new String[componentGrplength];
			
			
			
			CustOptCatForm objCustOptCatForm = (CustOptCatForm) objActionForm;
			
			
			modelSeqNo = Integer.parseInt(objHttpServletRequest
					.getParameter("modelSeqNo"));
			modelName = objHttpServletRequest.getParameter("modelName");
						specType = objHttpServletRequest
			.getParameter(ApplicationConstants.SELECTED_SPEC_TYPE);
			
			
			
			specTypeSeqNo = Integer.parseInt(objHttpServletRequest.getParameter(ApplicationConstants.SPEC_TYPE_NO));
			
			LogUtil.logMessage("Model Seq No :" + modelSeqNo);
			LogUtil.logMessage("ModelName :" + modelName);
			LogUtil.logMessage("specType :" + specType);
			LogUtil.logMessage("specTypeSeqNo :" + specTypeSeqNo);
			
			LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			objModelVO.setModelSeqNo(modelSeqNo);
			objModelVO.setModelName(modelName);
			objModelVO.setUserID(objLoginVo.getUserID());
			
			LogUtil.logMessage("Testing 1");
			try{
				tempComponentGrpSeqNos =objHttpServletRequest.getParameterValues("hdnCompGroupSeqNo");
				for(int i =0;i<tempComponentGrpSeqNos.length;i++){
					if(tempComponentGrpSeqNos[i]!=null){
					componentGrpSeqNos[i]=Integer.parseInt(tempComponentGrpSeqNos[i]);
					}
					LogUtil.logMessage("flag is "+objHttpServletRequest.getParameterValues("hdnDispInCOC")[i]);
					dispInCOCFlags[i]=objHttpServletRequest.getParameterValues("hdnDispInCOC")[i];
				}
				LogUtil.logMessage("componentGrpSeqNos's length "+componentGrpSeqNos.length);
				LogUtil.logMessage("componentGrpSeqNos "+componentGrpSeqNos);
				
			}
			catch(Exception e){
				LogUtil.logMessage("This is the exception "+e);
			}
			LogUtil.logMessage("Testing 2");
			
			objModelVO.setComponentGrpSeqNos(componentGrpSeqNos);
			objModelVO.setDispInCOCFlags(dispInCOCFlags);
			
			LogUtil.logMessage("Testing 3");
			ViewSpecByModelBI objViewSpecByModelBO = ServiceFactory
			.getViewSpecByModelBO();
			
			updateStatus =objViewSpecByModelBO.updateCompGroupsInCOC(objModelVO);
			LogUtil.logMessage("updateStatus "+updateStatus);
			if (updateStatus == 0) {
				objCustOptCatForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objCustOptCatForm.setMessageID("" + updateStatus);
			}
			
			LogUtil.logMessage("Testing 4");
			
			arlCompGroups = objViewSpecByModelBO.editCompGroupInCOC(objModelVO);
			if(arlCompGroups.size()>0){
				objCustOptCatForm.setCompGroupList(arlCompGroups);
			}
			
			LogUtil.logMessage("Testing 5");
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpec);
			objCustOptCatForm.setSpecTypeList(arlSpec);
			
			objModelVO =new ModelVo();
			
			objModelVO.setSpecTypeSeqNo(objCustOptCatForm
					.getSpecTypeNo());
			LogUtil.logMessage("Spec Type Seq No"
					+ objCustOptCatForm.getSpecTypeNo());

			
			ModelBI objModelBO = ServiceFactory.getModelBO();
			ArrayList modelList = objModelBO.fetchModels(objModelVO);
			
			
			
			objCustOptCatForm.setModelList(modelList);
			
			
			objCustOptCatForm.setModelName(modelName);
			objCustOptCatForm.setModelSeqNo(modelSeqNo);
			objCustOptCatForm.setHdnSelSpecType(specType);
			objCustOptCatForm.setSpecTypeNo(specTypeSeqNo);
			
		} catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		
		return objActionMapping.findForward(strForwardKey);
	}
	*/
	
	//Added for CR_118 Ends
	
}