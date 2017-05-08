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
import com.EMD.LSDB.bo.serviceinterface.ModelBI;
import com.EMD.LSDB.bo.serviceinterface.SectionBI;
import com.EMD.LSDB.bo.serviceinterface.SpecTypeBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.form.MasterMaintenance.SecMaintForm;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SpecTypeVo;

/**
 * @author PS57222 This class is used to Search,Add and update Section in Model
 *         Level
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
public class SecMaintAction extends EMDAction {
	
	/***************************************************************************
	 * * * * This Method is used to populate the SpecificationType Dropdown on
	 * PageLoad
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
		
		LogUtil.logMessage("Inside the initLoad method of SectionMaintenance");
		String strForwardKey = ApplicationConstants.SUCCESS;
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		SecMaintForm objSecMaintForm = (SecMaintForm) objActionForm;

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
			objSecMaintForm.setSpecTypeNo(specTypeNo);
		
		} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
			if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {
					strSpecTypeNo = (String) objSession
						.getAttribute("SPEC_TYPE_NO");
				specTypeNo = Integer.parseInt(strSpecTypeNo);
				objSecMaintForm.setSpecTypeNo(specTypeNo);
					}
		}

		if (strModleSeqNo != null) {

			objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
			modleSeqNo = Integer.parseInt(strModleSeqNo);
			LogUtil.logMessage("value of MODEL_SEQ_NO from session "
					+ modleSeqNo);
			objSecMaintForm.setModelSeqNo(modleSeqNo);

		} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {

			if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
				strModleSeqNo = (String) objSession
						.getAttribute("MODEL_SEQ_NO");
				modleSeqNo = Integer.parseInt(strModleSeqNo);
				objSecMaintForm.setModelSeqNo(modleSeqNo);
			}
		}
		if (specTypeNo != -1 && specTypeNo != 0) {

			LogUtil.logMessage("in side modleSeqNo" + modleSeqNo);

			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			objModelVo.setSpecTypeSeqNo(specTypeNo);
			ModelBI objModelBO = ServiceFactory.getModelBO();
			if (objModelBO.fetchModels(objModelVo) != null) {
				arlModelList = objModelBO.fetchModels(objModelVo);
				objSecMaintForm.setModelList(arlModelList);
				objSecMaintForm.setModelSeqNo(modleSeqNo);
				LogUtil.logMessage("end of the if modleSeqNo" + modleSeqNo);
			}

		}	
		//CR_83 lines area ends here
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objSecMaintForm.setSpecTypeList(arlSpec);
			}
			
					
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
		SecMaintForm objSecMaintForm = (SecMaintForm) objActionForm;
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
		objSecMaintForm.setSpecTypeNo(specTypeNo);
	} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
		if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {
				strSpecTypeNo = (String) objSession
					.getAttribute("SPEC_TYPE_NO");
			specTypeNo = Integer.parseInt(strSpecTypeNo);
			objSecMaintForm.setSpecTypeNo(specTypeNo);
		}
	}

	if (strModleSeqNo != null) {

		objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
		modleSeqNo = Integer.parseInt(strModleSeqNo);
		objSecMaintForm.setModelSeqNo(modleSeqNo);

	} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {

		if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
			strModleSeqNo = (String) objSession
					.getAttribute("MODEL_SEQ_NO");
			modleSeqNo = Integer.parseInt(strModleSeqNo);
			objSecMaintForm.setModelSeqNo(modleSeqNo);
		}
	}
	
	//CR_83 lines area ends here
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objSecMaintForm.setSpecTypeList(arlSpec);
				//objSecMaintForm.setSpecTypeNo(specTypeNo);
			}
		if (specTypeNo != -1 && specTypeNo != 0) {
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			objModelVo.setSpecTypeSeqNo(objSecMaintForm.getSpecTypeNo());
			ModelBI objModelBO = ServiceFactory.getModelBO();
			if (objModelBO.fetchModels(objModelVo) != null) {
				 arlModelList = objModelBO.fetchModels(objModelVo);
				objSecMaintForm.setModelList(arlModelList);
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
	
	/***************************************************************************
	 * * * * This Method is used to Search the Sections Based on Model
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward fetchSections(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil
		.logMessage("Enters Into SectionMaintAction:FetchSection Method");
		String strForwardKey = ApplicationConstants.SUCCESS;
		//CR_83 linesare stared here 
		String strSpecTypeNo=null;
		String strModleSeqNo=null;
		ArrayList arlSectionList = null;
		int specTypeNo = -1;
		int modleSeqNo = -1;
		ArrayList arlModelList = null;
//		CR_83 linesare ends here 
		SecMaintForm objSecMaintForm = (SecMaintForm) objActionForm;
		SectionVO objSectionVO = new SectionVO();
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LogUtil.logMessage(objSession);
		
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		LogUtil.logMessage(objLoginVo);
//		CR_83 lines are added ahere 
		strSpecTypeNo = (String) objHttpServletRequest
		.getParameter("specTypeNo");
strModleSeqNo = (String) objHttpServletRequest
		.getParameter("modelSeqNo");

if (strSpecTypeNo != null) {
	objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);
	specTypeNo = Integer.parseInt(strSpecTypeNo);
	objSecMaintForm.setSpecTypeNo(specTypeNo);
} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
	if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {
		strSpecTypeNo = (String) objSession
				.getAttribute("SPEC_TYPE_NO");
		specTypeNo = Integer.parseInt(strSpecTypeNo);
		objSecMaintForm.setSpecTypeNo(specTypeNo);
	}
}

if (strModleSeqNo != null) {
	objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
	modleSeqNo = Integer.parseInt(strModleSeqNo);
	objSecMaintForm.setModelSeqNo(modleSeqNo);
} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {

	if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
		strModleSeqNo = (String) objSession
				.getAttribute("MODEL_SEQ_NO");
		modleSeqNo = Integer.parseInt(strModleSeqNo);
		objSecMaintForm.setModelSeqNo(modleSeqNo);
	}
}

//CR_83 lines area ends here
		
		objSecMaintForm.setHdSelectedSpecType(objSecMaintForm
				.getHdSelectedSpecType());
		LogUtil.logMessage("Spectype:"
				+ objSecMaintForm.getHdSelectedSpecType());
		objSecMaintForm.setHdPreSelectedModel(objSecMaintForm.getModelSeqNo());
		objSectionVO.setUserID(objLoginVo.getUserID());
		LogUtil.logMessage("UserId in sectionMaint search method"
				+ objSectionVO.getUserID());
		objSectionVO.setModelSeqNo(objSecMaintForm.getModelSeqNo());
		LogUtil
		.logMessage("ModelSequenceNO:"
				+ objSecMaintForm.getModelSeqNo());
		LogUtil.logMessage("Model Sequence NO in SectionVO:"
				+ objSectionVO.getModelSeqNo());
		LogUtil.logMessage("Model Sequence NO in SectionVO:"
				+ objSecMaintForm.getHdPreSelectedModel());
		LogUtil.logMessage(objSectionVO.getUserID());
		
		try {
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objSecMaintForm.setSpecTypeList(arlSpec);
			}
			if(specTypeNo!=-1 && specTypeNo !=0)
			{
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			objModelVo.setSpecTypeSeqNo(objSecMaintForm.getSpecTypeNo());
			ModelBI objModelBO = ServiceFactory.getModelBO();
			if (objModelBO.fetchModels(objModelVo) != null) {
				 arlModelList = objModelBO.fetchModels(objModelVo);
				objSecMaintForm.setModelList(arlModelList);
			}
		}
			
			if(modleSeqNo!=-1 && modleSeqNo !=0)
			{
				SectionBI objSectionBO = ServiceFactory.getSectionBO();
			objSectionVO.setModelSeqNo(modleSeqNo);
			objSectionVO.setUserID(objLoginVo.getUserID());
			arlSectionList = objSectionBO.fetchSections(objSectionVO);
			LogUtil
			.logMessage("ReEnters into SectionMAintenance Search Method");
			LogUtil.logMessage("ResultList in action Fetchsections method:"
					+ arlSectionList);
			if (arlSectionList != null) {
				objSecMaintForm.setSectionList(arlSectionList);
				
			}
		}

			
			LogUtil
			.logMessage("SectionVo in Action(objSecMaintForm.setSectionvo):"
					+ objSecMaintForm.getSectionList());
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in SecMaintAction:fetchsections");
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
	 * * * * This Method is used to Add Sections Based on Model
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward insertSection(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Entering  SectionMaintAction:insertSection ");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intStatusCode;
		ArrayList arlSectionList = new ArrayList();
		
		SecMaintForm objSecMaintForm = (SecMaintForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LogUtil.logMessage(objSession);
		
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		LogUtil.logMessage(objLoginVo);
		objSecMaintForm.setHdPreSelectedModel(objSecMaintForm.getModelSeqNo());
		try {
			
			SectionVO objSectionVO = new SectionVO();
			objSectionVO.setUserID(objLoginVo.getUserID());
			LogUtil.logMessage("UserId in sectionMaint search method"
					+ objLoginVo.getUserID());
			objSectionVO.setModelSeqNo(objSecMaintForm.getModelSeqNo());
			LogUtil.logMessage("ModelSequenceNo:"
					+ objSecMaintForm.getModelSeqNo());
			
			String strSectionName = objSecMaintForm.getSecName();
			String strSecctionDesc = objSecMaintForm.getSecComments();
			
			LogUtil.logMessage("SectionName Before trim:" + strSectionName);
			LogUtil.logMessage("SectionComments Before triming:"
					+ strSecctionDesc);
			objSectionVO.setSectionName(ApplicationUtil.trim(strSectionName));
			objSectionVO.setSectionComments(ApplicationUtil
					.trim(strSecctionDesc));
			
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			intStatusCode = objSectionBO.insertSection(objSectionVO);
			LogUtil.logMessage("IS Data Added successfully:" + intStatusCode);
			
			if (intStatusCode == 0) {
				objSecMaintForm.setSecName("");
				objSecMaintForm.setSecComments("");
				objSecMaintForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			}
			
			if (intStatusCode > 0) {
				objSecMaintForm.setMessageID("" + intStatusCode);
			}
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objSecMaintForm.setSpecTypeList(arlSpec);
			}
			
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			objModelVo.setSpecTypeSeqNo(objSecMaintForm.getSpecTypeNo());
			ModelBI objModelBO = ServiceFactory.getModelBO();
			if (objModelBO.fetchModels(objModelVo) != null) {
				ArrayList arlModelList = objModelBO.fetchModels(objModelVo);
				objSecMaintForm.setModelList(arlModelList);
			}
			
			arlSectionList = objSectionBO.fetchSections(objSectionVO);
			LogUtil.logMessage("SectionList:" + arlSectionList);
			objSecMaintForm.setSectionList(arlSectionList);
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in SecMaintAction:insertSection");
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
	 * * * * This Method is used to Update the Section Based on Model
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward updateSection(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil
		.logMessage("Enters into SectionMaintAction:UpdateSection Method");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intStatusCode;
		SecMaintForm objSecMaintForm = (SecMaintForm) objActionForm;
		ArrayList arlSectionList = new ArrayList();
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LogUtil.logMessage(objSession);
		
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		LogUtil.logMessage(objLoginVo);
		objSecMaintForm.setHdPreSelectedModel(objSecMaintForm.getModelSeqNo());
		try {
			
			SectionVO objSectionVO = new SectionVO();
			
			objSectionVO.setUserID(objLoginVo.getUserID());
			LogUtil.logMessage("Userid inside UpdateSection method"
					+ objSectionVO.getUserID());
			objSectionVO.setSectionSeqNo(objSecMaintForm.getSectionSeqNo());
			LogUtil.logMessage("SectionSeqNo inside UpdateSection method"
					+ objSecMaintForm.getSectionSeqNo());
			objSectionVO.setModelSeqNo(objSecMaintForm.getModelSeqNo());
			LogUtil.logMessage("ModelSequenceNo:"
					+ objSecMaintForm.getModelSeqNo());
			String strSectionName = objSecMaintForm.getHdsection();
			String strSectionDesc = objSecMaintForm.getHdSecComments();
			LogUtil.logMessage("section name value:" + strSectionName);
			objSectionVO.setSectionName(ApplicationUtil.trim(strSectionName));
			objSectionVO.setSectionComments(ApplicationUtil
					.trim(strSectionDesc));
			
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			
			intStatusCode = objSectionBO.updateSection(objSectionVO);
			
			if (intStatusCode == 0) {
				objSecMaintForm.setSecName("");
				objSecMaintForm.setSecComments("");
				objSecMaintForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			}
			
			if (intStatusCode > 0) {
				objSecMaintForm.setMessageID("" + intStatusCode);
			}
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objSecMaintForm.setSpecTypeList(arlSpec);
			}
			
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			objModelVo.setSpecTypeSeqNo(objSecMaintForm.getSpecTypeNo());
			ModelBI objModelBO = ServiceFactory.getModelBO();
			if (objModelBO.fetchModels(objModelVo) != null) {
				ArrayList arlModelList = objModelBO.fetchModels(objModelVo);
				objSecMaintForm.setModelList(arlModelList);
			}
			
			arlSectionList = objSectionBO.fetchSections(objSectionVO);
			LogUtil.logMessage("SectionList:" + arlSectionList);
			objSecMaintForm.setSectionList(arlSectionList);
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in SecMaintAction:insertSection");
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
	 * * * * This Method is used to delete the Section Based on Model for CR-4
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward deleteSection(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil
		.logMessage("Enters into SectionMaintAction:DeleteSection Method");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intStatusCode;
		SecMaintForm objSecMaintForm = (SecMaintForm) objActionForm;
		ArrayList arlSectionList = new ArrayList();
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LogUtil.logMessage(objSession);
		
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		LogUtil.logMessage(objLoginVo);
		
		objSecMaintForm.setHdPreSelectedModel(objSecMaintForm.getModelSeqNo());
		try {
			
			SectionVO objSectionVO = new SectionVO();
			
			objSectionVO.setUserID(objLoginVo.getUserID());
			objSectionVO.setSectionSeqNo(objSecMaintForm.getSectionSeqNo());
			objSectionVO.setModelSeqNo(objSecMaintForm.getModelSeqNo());
			
			LogUtil.logMessage("Userid inside DeleteSection method"
					+ objSectionVO.getUserID());
			LogUtil.logMessage("SectionSeqNo inside DeleteSection method"
					+ objSecMaintForm.getSectionSeqNo());
			LogUtil.logMessage("ModelSequenceNo inside DeleteSection method"
					+ objSecMaintForm.getModelSeqNo());
			
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			intStatusCode = objSectionBO.deleteSection(objSectionVO);
			
			if (intStatusCode == 0) {
				objSecMaintForm.setSecName("");
				objSecMaintForm.setSecComments("");
				objSecMaintForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			}
			
			if (intStatusCode > 0) {
				objSecMaintForm.setMessageID("" + intStatusCode);
			}
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objSecMaintForm.setSpecTypeList(arlSpec);
			}
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			objModelVo.setSpecTypeSeqNo(objSecMaintForm.getSpecTypeNo());
			ModelBI objModelBO = ServiceFactory.getModelBO();
			if (objModelBO.fetchModels(objModelVo) != null) {
				ArrayList arlModelList = objModelBO.fetchModels(objModelVo);
				objSecMaintForm.setModelList(arlModelList);
			}
			
			arlSectionList = objSectionBO.fetchSections(objSectionVO);
			LogUtil.logMessage("SectionList:" + arlSectionList);
			objSecMaintForm.setSectionList(arlSectionList);
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in SecMaintAction:deleteSection");
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