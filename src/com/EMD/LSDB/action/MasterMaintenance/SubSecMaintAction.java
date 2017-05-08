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
import com.EMD.LSDB.bo.serviceinterface.ModelSubSectionBI;
import com.EMD.LSDB.bo.serviceinterface.SectionBI;
import com.EMD.LSDB.bo.serviceinterface.SpecTypeBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.form.MasterMaintenance.SubSecMaintForm;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SpecTypeVo;
import com.EMD.LSDB.vo.common.SubSectionVO;

/**
 * @author vv49326
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
public class SubSecMaintAction extends EMDAction {
	
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
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering SubSecMaintAction.initLoad");
		String strForwardKey = ApplicationConstants.SUCCESS;
		SubSecMaintForm objSubSecMaintForm = (SubSecMaintForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		ArrayList arlModelList = null;
		ArrayList arlSectionList = null;
		ArrayList arlSubSecList = null;
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
		try{
		strSpecTypeNo = (String) objHttpServletRequest
		.getParameter("specTypeNo");
strModleSeqNo = (String) objHttpServletRequest
		.getParameter("modelseqno");
strSectionSeqNo = (String) objHttpServletRequest
		.getParameter("sectionseqno");


if (strSpecTypeNo != null) {
	objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);
	specTypeNo = Integer.parseInt(strSpecTypeNo);
	LogUtil.logMessage("value of strSpecTypeNo from  session"
			+ strSpecTypeNo);
	objSubSecMaintForm.setSpecTypeNo(specTypeNo);
} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
	if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {
		strSpecTypeNo = (String) objSession
				.getAttribute("SPEC_TYPE_NO");
		specTypeNo = Integer.parseInt(strSpecTypeNo);
		objSubSecMaintForm.setSpecTypeNo(specTypeNo);
	}
}

if (strModleSeqNo != null) {

	objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
	modleSeqNo = Integer.parseInt(strModleSeqNo);
	objSubSecMaintForm.setModelseqno(modleSeqNo);
} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {
	if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
		strModleSeqNo = (String) objSession
				.getAttribute("MODEL_SEQ_NO");
		modleSeqNo = Integer.parseInt(strModleSeqNo);
		objSubSecMaintForm.setModelseqno(modleSeqNo);
	}
}

if (strSectionSeqNo != null) {

	objSession.setAttribute("SECTION_SEQ_NO", strSectionSeqNo);
	sectionSeqNo = Integer.parseInt(strSectionSeqNo);
	objSubSecMaintForm.setSectionseqno(sectionSeqNo);
} else if (objSession.getAttribute("SECTION_SEQ_NO") != null) {
	if (strSectionSeqNo == null || "".equals(strSectionSeqNo)) {
		strSectionSeqNo = (String) objSession
				.getAttribute("SECTION_SEQ_NO");
		sectionSeqNo = Integer.parseInt(strSectionSeqNo);
		objSubSecMaintForm.setSectionseqno(sectionSeqNo);

	}
}
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objSubSecMaintForm.setSpecTypeList(arlSpec);
			}
			
			if (specTypeNo != -1 && specTypeNo != 0) {

				LogUtil.logMessage("in side modleSeqNo" + modleSeqNo);

				ModelVo objModelVo = new ModelVo();
				objModelVo.setUserID(objLoginVo.getUserID());
				objModelVo.setSpecTypeSeqNo(specTypeNo);
				ModelBI objModelBO = ServiceFactory.getModelBO();
				if (objModelBO.fetchModels(objModelVo) != null) {
					arlModelList = objModelBO.fetchModels(objModelVo);
					objSubSecMaintForm.setListModels(arlModelList);
					objSubSecMaintForm.setModelseqno(modleSeqNo);
     			}

			}
			
			if (modleSeqNo != -1 && modleSeqNo != 0) {
				SectionVO objSectionMaintVo = new SectionVO();
				objSectionMaintVo.setModelSeqNo(modleSeqNo);
				objSectionMaintVo.setUserID(objLoginVo.getUserID());
				SectionBI objSectionMaintBO = ServiceFactory.getSectionBO();
				arlSectionList = objSectionMaintBO
						.fetchSections(objSectionMaintVo);
				if (arlSectionList != null)
					objSubSecMaintForm.setListSections(arlSectionList);
				objSubSecMaintForm.setSectionseqno(sectionSeqNo);
			}
			
			if (subSectionSeqNo != -1 && subSectionSeqNo != 0) {
				SubSectionVO objSubSecMaintVO = new SubSectionVO();
				objSubSecMaintVO.setModelSeqNo(modleSeqNo);
				objSubSecMaintVO.setSecSeqNo(sectionSeqNo);
				objSubSecMaintVO.setUserID(objLoginVo.getUserID());
				ModelSubSectionBI objSubSecMaintBO = ServiceFactory
						.getSubSecMaintBO();
				arlSubSecList = objSubSecMaintBO
						.fetchSubSections(objSubSecMaintVO);
				if (arlSubSecList != null && arlSubSecList.size() != 0) {
					objSubSecMaintForm.setSubsections(arlSubSecList);
					objSubSecMaintForm.setSubsecseqno(subSectionSeqNo);
			}

				else {

					objSubSecMaintForm.setMethod("SubSections");

				}
			}
			
		}
		
		catch (Exception objEx) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
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
	 * fetchModels method is added based on LSDB_CR-46 specTypeSeqVo is
	 * Populated based in all the actions based on the requirement of LSDB_CR-46
	 * Added on 28-Aug-08 by ps57222
	 */
	
	public ActionForward fetchModels(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Inside the SectionMaintenance:fetchModels");
		String strForwardKey = ApplicationConstants.SUCCESS;
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		SubSecMaintForm objSubSecMaintForm = (SubSecMaintForm) objActionForm;
		String strSpecTypeNo=null;
		
		
		try {
			
			objSubSecMaintForm.setHdSelectedSpecType(objSubSecMaintForm
					.getHdSelectedSpecType());
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objSubSecMaintForm.setSpecTypeList(arlSpec);
			}
			
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			objModelVo.setSpecTypeSeqNo(objSubSecMaintForm.getSpecTypeNo());
			ModelBI objModelBO = ServiceFactory.getModelBO();
			if (objModelBO.fetchModels(objModelVo) != null) {
				ArrayList arlModelList = objModelBO.fetchModels(objModelVo);
				objSubSecMaintForm.setListModels(arlModelList);
			}
			
//			CR_83 holding the value from form in the sesiion 
			
			strSpecTypeNo=(String) objHttpServletRequest.getParameter("specTypeNo");
			objSession.setAttribute("SPEC_TYPE_NO",strSpecTypeNo);
			
				//CR_83 lines are ends here
			
			
			
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
	 * * * * This Method is used to populate the Section Dropdown on PageLoad
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward SectionLoad(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering SubSecMaintAction.SecLoad");
		String strForwardKey = ApplicationConstants.SUCCESS;
		SubSecMaintForm objSubSecMaintForm = (SubSecMaintForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		
		int specTypeNo = -1;
		int modleSeqNo = -1;
		int sectionSeqNo = -1;
		int subSectionSeqNo = -1;
		
		String strSpecTypeNo = null;
		String strModleSeqNo = null;
		String strSectionSeqNo = null;
		
		String strSubSectionSeqNo = null;
		
		try{
		strSpecTypeNo = (String) objHttpServletRequest
		.getParameter("specTypeNo");
strModleSeqNo = (String) objHttpServletRequest
		.getParameter("modelseqno");
strSectionSeqNo = (String) objHttpServletRequest
		.getParameter("sectionSeqNo");
strSubSectionSeqNo = (String) objHttpServletRequest
.getParameter("subSectionSeqNo");

if (strSpecTypeNo != null) {
	objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);
	specTypeNo = Integer.parseInt(strSpecTypeNo);
	objSubSecMaintForm.setSpecTypeNo(specTypeNo);
} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
	if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {
		strSpecTypeNo = (String) objSession
				.getAttribute("SPEC_TYPE_NO");
		specTypeNo = Integer.parseInt(strSpecTypeNo);
		objSubSecMaintForm.setSpecTypeNo(specTypeNo);
	}
}
if (strModleSeqNo != null) {
	objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
	modleSeqNo = Integer.parseInt(strModleSeqNo);
	objSubSecMaintForm.setModelseqno(modleSeqNo);
} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {

	if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
		strModleSeqNo = (String) objSession
				.getAttribute("MODEL_SEQ_NO");
		modleSeqNo = Integer.parseInt(strModleSeqNo);
		objSubSecMaintForm.setModelseqno(modleSeqNo);
	}
}

if (strSectionSeqNo != null) {

	objSession.setAttribute("SECTION_SEQ_NO", strSectionSeqNo);
	sectionSeqNo = Integer.parseInt(strSectionSeqNo);
	objSubSecMaintForm.setSectionseqno(sectionSeqNo);
} else if (objSession.getAttribute("SECTION_SEQ_NO") != null) {
	if (strSectionSeqNo == null || "".equals(strSectionSeqNo)) {
		strSectionSeqNo = (String) objSession
				.getAttribute("SECTION_SEQ_NO");
		sectionSeqNo = Integer.parseInt(strSectionSeqNo);
		objSubSecMaintForm.setSectionseqno(sectionSeqNo);

	}
}
if (strSubSectionSeqNo != null) {

	objSession.setAttribute("SECTION_SEQ_NO", strSubSectionSeqNo);
	subSectionSeqNo = Integer.parseInt(strSubSectionSeqNo);
	objSubSecMaintForm.setSubsecseqno(subSectionSeqNo);
} else if (objSession.getAttribute("SECTION_SEQ_NO") != null) {
	if (strSubSectionSeqNo == null || "".equals(strSubSectionSeqNo)) {
		strSubSectionSeqNo = (String) objSession
				.getAttribute("SECTION_SEQ_NO");
		subSectionSeqNo = Integer.parseInt(strSubSectionSeqNo);
		objSubSecMaintForm.setSubsecseqno(subSectionSeqNo);

	}
}



			SectionVO objSectionMaintVo = new SectionVO();
			ArrayList arlModelList = null;
			ArrayList arlSectionList = null;
			
			//Model Load starts here
			if(specTypeNo!=-1 && specTypeNo!=0)
			{
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			objModelVo.setSpecTypeSeqNo(objSubSecMaintForm.getSpecTypeNo());
			ModelBI objModelBO = ServiceFactory.getModelBO();
			if (objModelBO.fetchModels(objModelVo) != null) {
				arlModelList = objModelBO.fetchModels(objModelVo);
				objSubSecMaintForm.setListModels(arlModelList);
			}
		}
			
			//Model Load Ends here
			
				if(modleSeqNo!=-1 && modleSeqNo!=0)
			{
			//Section Load starts here
			objSectionMaintVo.setModelSeqNo(modleSeqNo);
			objSectionMaintVo.setUserID(objLoginVo.getUserID());
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			arlSectionList = objSectionBO.fetchSections(objSectionMaintVo);
			if (arlSectionList != null) {
				objSubSecMaintForm.setOnLoad("Onload");
				objSubSecMaintForm.setListSections(arlSectionList);
			}
			}
			//Section Load Ends here
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objSubSecMaintForm.setSpecTypeList(arlSpec);
			}
			
			

			
		}
		
		catch (Exception objEx) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***************************************************************************
	 * * * * This Method is used to Search the Sub Sections Based at Model level
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	public ActionForward fetchSubSections(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering SubSecMaintAction.Search");
		String strForwardKey = ApplicationConstants.SUCCESS;
		SubSecMaintForm objSubSecMaintForm = (SubSecMaintForm) objActionForm;
		String strSpecTypeNo=null;
		String strModleSeqNo=null;
		String strSecSeqNo=null;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			SubSectionVO objSubSecMaintVO = new SubSectionVO();
			SectionVO objSectionMaintVo = new SectionVO();
			
			ArrayList arlModelList = null;
			ArrayList arlSectionList = null;
			ArrayList arlSubSecList = null;
			
			String strUserID = null;
			
			//Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objSubSecMaintForm.setSpecTypeList(arlSpec);
			}
			
			//Model load starts here
			
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			objModelVo.setSpecTypeSeqNo(objSubSecMaintForm.getSpecTypeNo());
			ModelBI objModelBO = ServiceFactory.getModelBO();
			if (objModelBO.fetchModels(objModelVo) != null) {
				arlModelList = objModelBO.fetchModels(objModelVo);
				objSubSecMaintForm.setListModels(arlModelList);
			}
			
			//Ends here
			
			//Section Load
			objSectionMaintVo.setModelSeqNo(objSubSecMaintForm.getModelseqno());
			objSectionMaintVo.setUserID(objLoginVo.getUserID());
			SectionBI objSectionMaintBO = ServiceFactory.getSectionBO();
			arlSectionList = objSectionMaintBO.fetchSections(objSectionMaintVo);
			if (arlSectionList != null)
				objSubSecMaintForm.setListSections(arlSectionList);
			
			//Section Load Ends Here
			
			//Sub Section fetch Starts Here
			
			objSubSecMaintVO.setModelSeqNo(objSubSecMaintForm.getModelseqno());
			objSubSecMaintVO.setSecSeqNo(objSubSecMaintForm.getSectionseqno());
			objSubSecMaintVO.setUserID(strUserID);
			
			ModelSubSectionBI objSubSecMaintBO = ServiceFactory
			.getSubSecMaintBO();
			arlSubSecList = objSubSecMaintBO.fetchSubSections(objSubSecMaintVO);
			
			if (arlSubSecList != null && arlSubSecList.size() != 0) {
				objSubSecMaintForm
				.setHdnModel(objSubSecMaintForm.getHdnModel());
				objSubSecMaintForm.setHdnSection(objSubSecMaintForm
						.getHdnSection());
				
				objSubSecMaintForm.setHdnSelectedSec(objSubSecMaintForm
						.getSectionseqno());
				
				objSubSecMaintForm.setSubsections(arlSubSecList);
			}
			
			else {
				
				objSubSecMaintForm.setMethod("SubSections");
				
			}
			
			
//			CR_83 holding the value from form in the sesiion 
			
			//objModelMaintForm.setSpecTypeNo(specTypeNo);
			strSpecTypeNo=(String) objHttpServletRequest.getParameter("specTypeNo");
			strModleSeqNo=(String) objHttpServletRequest.getParameter("modelseqno");
			strSecSeqNo=(String) objHttpServletRequest.getParameter("sectionseqno");
			objSession.setAttribute("SPEC_TYPE_NO",strSpecTypeNo);
			objSession.setAttribute("MODEL_SEQ_NO",strModleSeqNo);
			objSession.setAttribute("SECTION_SEQ_NO",strSecSeqNo);
			
				//CR_83 lines are ends he
			//Sub Section fetch Ends Here
			
		} catch (Exception objEx) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***************************************************************************
	 * * * * This Method is used to Add Sub Sections Based at Model Level
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward insertSubSection(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering SubSecMaintAction.insertSubSection");
		String strForwardKey = ApplicationConstants.SUCCESS;
		SubSecMaintForm objSubSecMaintForm = (SubSecMaintForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			
			SubSectionVO objSubSecMaintVO = new SubSectionVO();
			SectionVO objSectionMaintVo = new SectionVO();
			
			ArrayList arlModelList = null;
			ArrayList arlSectionList = null;
			ArrayList arlSubSecList = null;
			
			int intSuccess = 0;
			String strUserID = null;
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			//Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objSubSecMaintForm.setSpecTypeList(arlSpec);
			}
			
			//Model load starts here
			
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			objModelVo.setSpecTypeSeqNo(objSubSecMaintForm.getSpecTypeNo());
			ModelBI objModelBO = ServiceFactory.getModelBO();
			if (objModelBO.fetchModels(objModelVo) != null) {
				arlModelList = objModelBO.fetchModels(objModelVo);
				objSubSecMaintForm.setListModels(arlModelList);
			}
			//Model Load Ends here
			
			//Section Load
			
			objSectionMaintVo.setModelSeqNo(objSubSecMaintForm.getModelseqno());
			objSectionMaintVo.setUserID(strUserID);
			SectionBI objSectionMaintBO = ServiceFactory.getSectionBO();
			arlSectionList = objSectionMaintBO.fetchSections(objSectionMaintVo);
			if (arlSectionList != null) {
				objSubSecMaintForm.setOnLoad("Onload");
				objSubSecMaintForm.setListSections(arlSectionList);
				
			}
			//Section Load ends here
			
			//SubSection Insert Starts Here
			
			objSubSecMaintVO.setModelSeqNo(objSubSecMaintForm.getModelseqno());
			objSubSecMaintVO.setSecSeqNo(objSubSecMaintForm.getSectionseqno());
			objSubSecMaintVO.setSubSecName(ApplicationUtil
					.trim(objSubSecMaintForm.getSubsecname()));
			objSubSecMaintVO.setSubSecDesc(ApplicationUtil
					.trim(objSubSecMaintForm.getComments()));
			objSubSecMaintVO.setUserID(strUserID);
			
			ModelSubSectionBI objSubSecMaintBO = ServiceFactory
			.getSubSecMaintBO();
			intSuccess = objSubSecMaintBO.insertSubSection(objSubSecMaintVO);
			
			if (intSuccess == 0) {
				objSubSecMaintForm.setComments("");
				objSubSecMaintForm.setSubsecname("");
				objSubSecMaintForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objSubSecMaintForm.setMessageID("" + intSuccess);
			}
			
			LogUtil.logMessage("Success Message"
					+ objSubSecMaintForm.getMessageID());
			
			//SubSection Ends here
			
			//Sub Section Load Starts Here
			
			objSubSecMaintForm.setOnLoad("Onload");
			objSubSecMaintForm.setHdnModel(objSubSecMaintForm.getHdnModel());
			objSubSecMaintForm
			.setHdnSection(objSubSecMaintForm.getHdnSection());
			objSubSecMaintForm.setHdnSelectedSec(objSubSecMaintForm
					.getSectionseqno());
			
			objSubSecMaintVO.setModelSeqNo(objSubSecMaintForm.getModelseqno());
			objSubSecMaintVO.setSecSeqNo(objSubSecMaintForm.getSectionseqno());
			objSubSecMaintVO.setUserID(strUserID);
			
			ModelSubSectionBI objjSubSecMaintBO = ServiceFactory
			.getSubSecMaintBO();
			arlSubSecList = objjSubSecMaintBO
			.fetchSubSections(objSubSecMaintVO);
			
			if (arlSubSecList != null) {
				
				objSubSecMaintForm.setSubsections(arlSubSecList);
				objSubSecMaintForm.setSuccessMessage(true);
				
			}
			
		}
		
		catch (Exception objEx) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***************************************************************************
	 * * * * This Method is used to Update Sub Sections Based at Model Level
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward updateSubSection(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering SubSecMaintAction.updateSubSection");
		String strForwardKey = ApplicationConstants.SUCCESS;
		SubSecMaintForm objSubSecMaintForm = (SubSecMaintForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			SubSectionVO objSubSecMaintVO = new SubSectionVO();
			SectionVO objSectionMaintVo = new SectionVO();
			
			ArrayList arlModelList = null;
			ArrayList arlSectionList = null;
			ArrayList arlSubSecList = null;
			
			int intSuccess = 0;
			String strUserID = null;
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			//Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objSubSecMaintForm.setSpecTypeList(arlSpec);
			}
			
			// Model load starts here
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			objModelVo.setSpecTypeSeqNo(objSubSecMaintForm.getSpecTypeNo());
			ModelBI objModelBO = ServiceFactory.getModelBO();
			if (objModelBO.fetchModels(objModelVo) != null) {
				arlModelList = objModelBO.fetchModels(objModelVo);
				objSubSecMaintForm.setListModels(arlModelList);
			}
			
			// Model load Ends here
			
			//Section Load Starts here
			
			objSectionMaintVo.setModelSeqNo(objSubSecMaintForm.getModelseqno());
			objSectionMaintVo.setUserID(objLoginVo.getUserID());
			SectionBI objSectionMaintBO = ServiceFactory.getSectionBO();
			arlSectionList = objSectionMaintBO.fetchSections(objSectionMaintVo);
			
			if (arlSectionList != null)
				objSubSecMaintForm.setListSections(arlSectionList);
			
			//ends here
			
			//Subsection Modify Starts Here
			
			objSubSecMaintVO.setModelSeqNo(objSubSecMaintForm.getModelseqno());
			objSubSecMaintVO.setSecSeqNo(objSubSecMaintForm.getSectionseqno());
			objSubSecMaintVO
			.setSubSecSeqNo(objSubSecMaintForm.getSubsecseqno());
			objSubSecMaintVO.setSubSecName(ApplicationUtil
					.trim(objSubSecMaintForm.getHdnsectionName()));
			objSubSecMaintVO.setSubSecDesc(ApplicationUtil
					.trim(objSubSecMaintForm.getHdnSectionComments()));
			objSubSecMaintVO.setUserID(strUserID);
			
			ModelSubSectionBI objSubSecMaintBO = ServiceFactory
			.getSubSecMaintBO();
			intSuccess = objSubSecMaintBO.updateSubSection(objSubSecMaintVO);
			objSubSecMaintForm.setComments("");
			objSubSecMaintForm.setSubsecname("");
			
			if (intSuccess == 0) {
				objSubSecMaintForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objSubSecMaintForm.setMessageID("" + intSuccess);
			}
			
			LogUtil.logMessage("Message" + objSubSecMaintForm.getMessageID());
			
			// Sub Section Modify ends here
			
			//Sub Section Load Starts here
			
			objSubSecMaintForm.setHdnModel(objSubSecMaintForm.getHdnModel());
			objSubSecMaintForm
			.setHdnSection(objSubSecMaintForm.getHdnSection());
			objSubSecMaintForm.setHdnSelectedSec(objSubSecMaintForm
					.getSectionseqno());
			
			objSubSecMaintVO.setModelSeqNo(objSubSecMaintForm.getModelseqno());
			objSubSecMaintVO.setSecSeqNo(objSubSecMaintForm.getSectionseqno());
			objSubSecMaintVO.setUserID(strUserID);
			
			ModelSubSectionBI objjSubSecMaintBO = ServiceFactory
			.getSubSecMaintBO();
			arlSubSecList = objjSubSecMaintBO
			.fetchSubSections(objSubSecMaintVO);
			
			if (arlSubSecList != null) {
				objSubSecMaintForm.setSubsections(arlSubSecList);
				objSubSecMaintForm.setSuccessMessage(true);
				
			}
			// Section Load Ends Here
			
		} catch (Exception objEx) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***************************************************************************
	 * * * * This Method is used to Delete Sub Sections Based at Model Level
	 * Added for CR-4
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward deleteSubSection(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering SubSecMaintAction.deleteSubSection");
		String strForwardKey = ApplicationConstants.SUCCESS;
		SubSecMaintForm objSubSecMaintForm = (SubSecMaintForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			
			SubSectionVO objSubSecMaintVO = new SubSectionVO();
			SectionVO objSectionMaintVo = new SectionVO();
			
			ArrayList arlModelList = null;
			ArrayList arlSectionList = null;
			ArrayList arlSubSecList = null;
			
			int intSuccess = 0;
			String strUserID = null;
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			//Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objSubSecMaintForm.setSpecTypeList(arlSpec);
			}
			
			// Model load starts here
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			objModelVo.setSpecTypeSeqNo(objSubSecMaintForm.getSpecTypeNo());
			ModelBI objModelBO = ServiceFactory.getModelBO();
			if (objModelBO.fetchModels(objModelVo) != null) {
				arlModelList = objModelBO.fetchModels(objModelVo);
				objSubSecMaintForm.setListModels(arlModelList);
			}
			
			// Model load Ends here
			
			//Section Load Starts here
			
			objSectionMaintVo.setModelSeqNo(objSubSecMaintForm.getModelseqno());
			objSectionMaintVo.setUserID(objLoginVo.getUserID());
			SectionBI objSectionMaintBO = ServiceFactory.getSectionBO();
			arlSectionList = objSectionMaintBO.fetchSections(objSectionMaintVo);
			
			if (arlSectionList != null)
				objSubSecMaintForm.setListSections(arlSectionList);
			
			//ends here
			
			//Subsection Delete Starts Here
			
			objSubSecMaintVO.setModelSeqNo(objSubSecMaintForm.getModelseqno());
			objSubSecMaintVO.setSecSeqNo(objSubSecMaintForm.getSectionseqno());
			objSubSecMaintVO
			.setSubSecSeqNo(objSubSecMaintForm.getSubsecseqno());
			objSubSecMaintVO.setUserID(strUserID);
			
			ModelSubSectionBI objSubSecMaintBO = ServiceFactory
			.getSubSecMaintBO();
			intSuccess = objSubSecMaintBO.deleteSubSection(objSubSecMaintVO);
			objSubSecMaintForm.setComments("");
			objSubSecMaintForm.setSubsecname("");
			
			if (intSuccess == 0) {
				objSubSecMaintForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objSubSecMaintForm.setMessageID("" + intSuccess);
			}
			
			LogUtil.logMessage("Message" + objSubSecMaintForm.getMessageID());
			
			// Sub Section Delete ends here
			
			//Sub Section Load Starts here
			
			objSubSecMaintForm.setHdnModel(objSubSecMaintForm.getHdnModel());
			objSubSecMaintForm
			.setHdnSection(objSubSecMaintForm.getHdnSection());
			objSubSecMaintForm.setHdnSelectedSec(objSubSecMaintForm
					.getSectionseqno());
			
			objSubSecMaintVO.setModelSeqNo(objSubSecMaintForm.getModelseqno());
			objSubSecMaintVO.setSecSeqNo(objSubSecMaintForm.getSectionseqno());
			objSubSecMaintVO.setUserID(strUserID);
			
			ModelSubSectionBI objjSubSecMaintBO = ServiceFactory
			.getSubSecMaintBO();
			arlSubSecList = objjSubSecMaintBO
			.fetchSubSections(objSubSecMaintVO);
			
			if (arlSubSecList != null) {
				objSubSecMaintForm.setSubsections(arlSubSecList);
				objSubSecMaintForm.setSuccessMessage(true);
				
			}
			// Sub Section Load Ends Here
			
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