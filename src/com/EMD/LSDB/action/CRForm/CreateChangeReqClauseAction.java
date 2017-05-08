/*
 * Created on Oct 20, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.action.CRForm;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.EMD.LSDB.action.common.EMDAction;
import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.ChangeRequestBI;
import com.EMD.LSDB.bo.serviceinterface.ModelBI;
import com.EMD.LSDB.bo.serviceinterface.ModelSelectClauseRevBI;
import com.EMD.LSDB.bo.serviceinterface.ModelSubSectionBI;
import com.EMD.LSDB.bo.serviceinterface.SectionBI;
import com.EMD.LSDB.bo.serviceinterface.SpecTypeBI;
import com.EMD.LSDB.bo.serviceinterface.StandardEquipBI;
import com.EMD.LSDB.bo.serviceinterface.ViewSpecByModelBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.form.CRForm.ComponentGroupRequestForm;
import com.EMD.LSDB.form.CRForm.CreateChangeClauseRequestForm;
import com.EMD.LSDB.vo.common.ClauseTableDataVO;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.ReqClauseVO;
import com.EMD.LSDB.vo.common.RequestModelVO;
import com.EMD.LSDB.vo.common.RequestVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SpecTypeVo;
import com.EMD.LSDB.vo.common.StandardEquipVO;
import com.EMD.LSDB.vo.common.SubSectionVO;

/**
 * @author PS57222
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class CreateChangeReqClauseAction extends EMDAction {
	
	/***************************************************************************
	 * This Method is used to fetch the RequestClauseDetails
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
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into CreateChangeReqClauseAction:initLoad");
		String strForwardKey = ApplicationConstants.SUCCESS;
		CreateChangeClauseRequestForm objCreateChngClaReqForm = (CreateChangeClauseRequestForm) objActionForm;
		ModelVo objModelVo = new ModelVo();
		ArrayList arlRequestList = new ArrayList();
		LoginVO objLoginVo = null;
		ArrayList arlStandardEquipList = new ArrayList();
		RequestVO objRequestVO = new RequestVO();
		SectionVO objSectionVO = new SectionVO();
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlSubSectionList = new ArrayList();
		ArrayList arlClauseList = new ArrayList();
		ArrayList arlReqClauseList = new ArrayList();
		ArrayList arlAllClauseList = new ArrayList();
		RequestModelVO objRequestModelVO = null;
		int intModelSeqNo = 0;
		int intSecSeqNo = 0;
		int intSubSecSeqNo = 0;
		int intFromClauseSeqNo = 0;
		int intFromVersionNo = 0;
		String strNewCompGrpName = "";
		String strNewCompName = "";
		String strReqUserID = null;
		String strUser = null;
		int intChangeTypeSeqNo = 0;
		int intReqID = 0;
		// Added For CR_80 to add Specification Type Dropdown
		int intSpecTypeNo = 0;
		// Added For CR_80 to add Specification Type Dropdown - Ends here
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LogUtil.logMessage(objSession);
			
			if (objSession != null) {
				objLoginVo = (LoginVO) objSession
				.getAttribute(ApplicationConstants.USER_IN_SESSION);
				
			}
			
			/** Getting ChangeRequestId From Request * */
			
			if ((String) objHttpServletRequest.getParameter("reqID") != null
					&& !"".equals((String) objHttpServletRequest
							.getParameter("reqID"))) {
				
				intReqID = Integer.parseInt(objHttpServletRequest.getParameter(
				"reqID").trim());
			}
			
			else if (objCreateChngClaReqForm.getHdnReqID() != null
					&& !"".equalsIgnoreCase(objCreateChngClaReqForm
							.getHdnReqID())) {
				
				intReqID = Integer.parseInt(objCreateChngClaReqForm
						.getHdnReqID().trim());
				
			}
			
			/** This part of code load the status details of the Request * */
			
			objRequestVO.setRequestID(intReqID);
			objRequestVO.setUserID(objLoginVo.getUserID());
			ChangeRequestBI objChangeRequestBO = ServiceFactory
			.getChangeRequestBO();
			arlRequestList = objChangeRequestBO
			.fetchRequestDetails(objRequestVO);
			/* Removed for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151
			///To set Not Valid and InProgress in same fashion
			RequestVO objReqVO = new RequestVO();
			ArrayList objjRequest = new ArrayList();
			
			for(int k=0;k<arlRequestList.size();k++){
				
				objReqVO=(RequestVO)arlRequestList.get(k);
	        
			if(objReqVO.getStatusTypeSeqNo() == 7){
				
				objReqVO.setStatusTypeSeqNo(1);
				
			}
			objjRequest.add(objReqVO);
			
			}
			
			
			if (objjRequest.size() != 0) {*/
				objCreateChngClaReqForm.setRequestList(arlRequestList);
			//}
			
			/**
			 * To Load the Request Model Details,like model ,section and
			 * Subsection seqno's.
			 */
			
			objRequestModelVO = objChangeRequestBO
			.fetchReqModelDetails(objRequestVO);
			
			/**
			 * This logic is for getting the model seq no and pass it to
			 * SectionVo to load the Section's *
			 */
			
			if (objRequestModelVO != null) {
				// Added For CR_80 to add Specification Type Dropdown
				intSpecTypeNo = objRequestModelVO.getSpecTypeNo();
				// Added For CR_80 to add Specification Type Dropdown - Ends here		
				intModelSeqNo = objRequestModelVO.getModelSeqNo();
				intSecSeqNo = objRequestModelVO.getSectionSeqNo();
				intSubSecSeqNo = objRequestModelVO.getSubSectionSeqNo();
				
				/*
				 * Change for Model apply flag
				 */
				if (objRequestModelVO.isApplyModelFlag()) {
					
					objCreateChngClaReqForm.setApplyModelFlag(true);
				} else {
					objCreateChngClaReqForm.setApplyModelFlag(false);
				}
			}
			LogUtil.logMessage("intSpecTypeSeqNo :" + intSpecTypeNo);
			LogUtil.logMessage("intModelSeqNo :" + intModelSeqNo);
			LogUtil.logMessage("intSecSeqNo :" + intSecSeqNo);
			LogUtil.logMessage("intSubSecSeqNo :" + intSubSecSeqNo);
			
			/*******************************************************************
			 * This logic is for Enabling and disabling the preview button in
			 * JSP Preview Button should be enabled only if the flag value is
			 * 'Y'
			 ******************************************************************/
			// Modified For CR_80 by adding spectypeno to if loop
			if (intSpecTypeNo == 0 && intModelSeqNo == 0 && intSecSeqNo == 0 && intSubSecSeqNo == 0) {
				intSpecTypeNo = -1 ;
				objCreateChngClaReqForm.setReqModelSaved("N");
				
			} else {
				
				objCreateChngClaReqForm.setReqModelSaved("Y");
			}
			
			LogUtil.logMessage("ReqModelSaved:"
					+ objCreateChngClaReqForm.getReqModelSaved());
			
			/**
			 * This logic is for enabling and disabling of Approve,Reject,Onhold
			 * and Complete Buttons's *
			 */
			
			if (ApplicationConstants.ADMN.equals(objLoginVo.getRoleID())
					|| ApplicationConstants.MSE.equals(objLoginVo.getRoleID()))
				objCreateChngClaReqForm.setRoleID(ApplicationConstants.ADMN);
			else
				objCreateChngClaReqForm.setRoleID("OTHR");//ASM,SE
			
			LogUtil.logMessage("RoleId Request:"
					+ objCreateChngClaReqForm.getRoleID());
			
			/** This is for getting request user ID * */
			
			if (arlRequestList != null && arlRequestList.size() > 0) {
				
				for (int i = 0; i < arlRequestList.size(); i++) {
					RequestVO objReq = (RequestVO) arlRequestList.get(i);
					strReqUserID = objReq.getReqUserID();
					objCreateChngClaReqForm.setReason(objReq.getReason());
					objCreateChngClaReqForm.setAdminComments(objReq
							.getAdminComments());
					objCreateChngClaReqForm.setRequestDesc(objReq
							.getRequestDesc());
					
				}
			}
			
			/*******************************************************************
			 * This part is for checking logged in user ID and Request Created
			 * User ID If equal then user have provision to modify request, else
			 * only preview available
			 ******************************************************************/
			
			strUser = objLoginVo.getUserID();
			
			LogUtil.logMessage("Login Usert:" + strUser);
			LogUtil.logMessage("Request Usert:" + strReqUserID);
			
			if (strReqUserID != null && strUser != null) {
				
				if (strReqUserID.trim().equals(strUser.trim())) {
					objCreateChngClaReqForm.setUserOwnRequest("Y");
				} else {
					objCreateChngClaReqForm.setUserOwnRequest("N");
				}
			}
			
			LogUtil.logMessage("User Own Request:"
					+ objCreateChngClaReqForm.getUserOwnRequest());

			// Added For CR_80 to add Specification Type Dropdown
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objCreateChngClaReqForm.setSpecTypeList(arlSpec);
			}
			// Added For CR_80 to add Specification Type Dropdown - Ends here	
			

			objModelVo.setUserID(objLoginVo.getUserID());
			// Added For CR_80 to add Specification Type Dropdown
			objModelVo.setSpecTypeSeqNo(intSpecTypeNo);
			// Added For CR_80 to add Specification Type Dropdown - Ends here
			ModelBI objModelBO = ServiceFactory.getModelBO();
			ArrayList arlModelList = objModelBO.fetchModels(objModelVo);
			if (arlModelList.size() != 0) {
				objCreateChngClaReqForm.setModelList(arlModelList);
			}
			
			objSectionVO.setModelSeqNo(intModelSeqNo);
			objSectionVO.setUserID(objLoginVo.getUserID());
			
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			arlSectionList = objSectionBO.fetchSections(objSectionVO);
			if (arlSectionList.size() != 0) {
				objCreateChngClaReqForm.setSectionList(arlSectionList);
			}
			
			SubSectionVO objSubSectionVO = new SubSectionVO();
			objSubSectionVO.setModelSeqNo(intModelSeqNo);
			objSubSectionVO.setSecSeqNo(intSecSeqNo);
			objSubSectionVO.setUserID(objLoginVo.getUserID());
			
			ModelSubSectionBI objModelSubSecBO = ServiceFactory
			.getSubSecMaintBO();
			arlSubSectionList = objModelSubSecBO
			.fetchSubSections(objSubSectionVO);
			
			if (arlSubSectionList.size() != 0) {
				objCreateChngClaReqForm.setSubSectionList(arlSubSectionList);
			}
			
			/**
			 * To load the ComponentGroup and component Details.
			 */
			
			objRequestModelVO = null;
			/** Reseting the RequestModelVo to null * */
			
			objRequestModelVO = objChangeRequestBO
			.fetchReqCompGrpDetails(objRequestVO);
			
			if (objRequestModelVO != null) {
				objCreateChngClaReqForm.setCompGrpNameVO(objRequestModelVO);
				
			}
			
			objRequestModelVO = null;
			/** Reseting the RequestModelVo to null * */
			objRequestModelVO = objChangeRequestBO
			.fetchReqClauseDetails(objRequestVO);
			
			/*******************************************************************
			 * *First time when we enters into Clause Screen Change type Seq
			 * number is set it to 4 for New Clause option after clause details
			 * is saved then we get it from table.
			 ******************************************************************/
			
			if (objRequestModelVO != null) {
				
				objCreateChngClaReqForm.setCompColourFlag(objRequestModelVO
						.getCompGrpLinkColorFlag());
				LogUtil.logMessage("Colour Flag :"
						+ objRequestModelVO.getCompGrpLinkColorFlag());

				if (objRequestModelVO.getReqClauseVO() != null) {
					intFromClauseSeqNo = objRequestModelVO.getReqClauseVO()
					.getFromClauseSeqNo();
					intFromVersionNo = objRequestModelVO.getReqClauseVO()
					.getFromClauseVerNo();
					arlReqClauseList.add(objRequestModelVO.getReqClauseVO());
					objCreateChngClaReqForm.setReqClauseList(arlReqClauseList);
					
					intChangeTypeSeqNo = objRequestModelVO.getReqClauseVO()
					.getChangeTypeSeqNO();
					objCreateChngClaReqForm.setClauseNo(objRequestModelVO
							.getReqClauseVO().getFromClauseNo());
					
				}
			}
			if (intChangeTypeSeqNo == 0) {
				objCreateChngClaReqForm.setChangeTypeSeqNO(4);
			} else {
				objCreateChngClaReqForm.setChangeTypeSeqNO(intChangeTypeSeqNo);
			}
			
			LogUtil.logMessage("intFromClauseSeqNo :" + intFromClauseSeqNo);
			LogUtil.logMessage("intFromVersionNo :" + intFromVersionNo);
			
			if (intFromClauseSeqNo != 0 && intFromVersionNo != 0) {
				
				ClauseVO objClauseVO = new ClauseVO();
				LogUtil.logMessage("ModelSeqNo in FetchClauseVersion**"
						+ intModelSeqNo);
				objClauseVO.setModelSeqNo(intModelSeqNo);
				LogUtil.logMessage("SubSectionSeqNo in FetchClauseVersion**"
						+ intSubSecSeqNo);
				objClauseVO.setSubSectionSeqNo(intSubSecSeqNo);
				LogUtil.logMessage("ClauseSeqNo in FetchClauseVersion**"
						+ intFromClauseSeqNo);
				
				objClauseVO.setClauseSeqNo(intFromClauseSeqNo);
				LogUtil.logMessage("Version No in FetchClauseVersion**"
						+ intFromVersionNo);
				objClauseVO.setVersionNo(intFromVersionNo);
				objClauseVO.setUserID(objLoginVo.getUserID());

				//Added For CR_80 by RR68151
				objCreateChngClaReqForm.setVersionNo(intFromVersionNo);
				
				ModelSelectClauseRevBI objModelSelectClauseRevBO = ServiceFactory
				.getModelSelectClauseRevBO();
				arlAllClauseList = objModelSelectClauseRevBO
				.fetchClauseVersions(objClauseVO);
				
				if (arlAllClauseList != null && arlAllClauseList.size() > 0) {
					/*Modified for CR_80 CR Form Enhancements III on 25-Nov-09 by RR68151
					if (objCreateChngClaReqForm.getChangeTypeSeqNO() != ApplicationConstants.CHANGE_TYPE_DESC_DELETE_CLAUSE_VERSION)
						arlClauseList = (ArrayList) arlAllClauseList.get(1);
					else*/
						arlClauseList = (ArrayList) arlAllClauseList.get(0);
					//Modified for CR_80 CR Form Enhancements III on 25-Nov-09 by RR68151 - Ends here
					objCreateChngClaReqForm.setClauseList(arlClauseList);
					LogUtil.logMessage("size of From Clause List:"
							+ arlClauseList.size());
					
				}
			}
			//Added For Specification Drop down CR_80
			if (intSpecTypeNo == 0)	{
				objCreateChngClaReqForm.setSpecTypeNo(-1);
			} else {
				objCreateChngClaReqForm.setSpecTypeNo(intSpecTypeNo);
			}
			//Added For Specification Drop down CR_80 - Ends here
			if (intModelSeqNo == 0) {
				objCreateChngClaReqForm.setModelSeqNo(-1);
			} else {
				objCreateChngClaReqForm.setModelSeqNo(intModelSeqNo);
			}
			
			if (intSecSeqNo == 0) {
				objCreateChngClaReqForm.setSectionSeqNo(-1);
			} else {
				objCreateChngClaReqForm.setSectionSeqNo(intSecSeqNo);
			}
			
			if (intSubSecSeqNo == 0) {
				objCreateChngClaReqForm.setSubSectionSeqNo(-1);
			} else {
				objCreateChngClaReqForm.setSubSectionSeqNo(intSubSecSeqNo);
			}
			
			StandardEquipVO objStandardEquipVO = new StandardEquipVO();
			objStandardEquipVO.setUserID(objLoginVo.getUserID());
			StandardEquipBI objStandardEquipBO = ServiceFactory
			.getStandardEquipBO();
			arlStandardEquipList = objStandardEquipBO
			.fetchStandardEquipment(objStandardEquipVO);
			
			if (arlStandardEquipList.size() != 0) {
				objCreateChngClaReqForm
				.setStdEquipmentList(arlStandardEquipList);
			}
			//Adding for CR_94
			{
			RequestVO objNewReqVO = new RequestVO();
			objNewReqVO.setStatusTypeSeqNo(2);
			objNewReqVO.setLastName("All");
			objNewReqVO.setRequestID(0);
			ChangeRequestBI objNewChangeRequestBI = ServiceFactory.getChangeRequestBO();
			ArrayList arlSearchList = new ArrayList();
			arlSearchList = objNewChangeRequestBI.fetchRequestDetails(objNewReqVO);
			objCreateChngClaReqForm.setSearchList(arlSearchList);
			}
			//Ends here CR_94
			
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in CreateChangeReqClauseAction..");
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
	 * This method is used to load the sections for the selected model.
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
	
	public ActionForward fetchSections(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil
		.logMessage("Enters into CreateChangeReqClauseAction:fetchSections");
		String strForwardKey = ApplicationConstants.SUCCESS;
		CreateChangeClauseRequestForm objCreateChngClaReqForm = (CreateChangeClauseRequestForm) objActionForm;
		ModelVo objModelVo = new ModelVo();
		ArrayList arlRequestList = new ArrayList();
		ArrayList arlSectionList = new ArrayList();
		LoginVO objLoginVo = null;
		ArrayList arlStandardEquipList = new ArrayList();
		RequestVO objRequestVO = new RequestVO();
		SectionVO objSectionVO = new SectionVO();
		String strReqUserID = null;
		String strUser = null;
		int intReqID = 0;
		int intModelSeqNo = 0;
		int intSecSeqNo = 0;
		int intSubSecSeqNo = 0;
		RequestModelVO objRequestModelVO = null;
		// Added For CR_80 to add Specification Type Dropdown
		int intSpecTypeNo = 0;
		// Added For CR_80 to add Specification Type Dropdown - Ends here
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LogUtil.logMessage(objSession);
			
			if (objSession != null) {
				objLoginVo = (LoginVO) objSession
				.getAttribute(ApplicationConstants.USER_IN_SESSION);
				
			}
			
			objModelVo.setUserID(objLoginVo.getUserID());
			
			if (objCreateChngClaReqForm.getHdnReqID() != null
					&& !"".equalsIgnoreCase(objCreateChngClaReqForm
							.getHdnReqID())) {
				
				intReqID = Integer.parseInt(objCreateChngClaReqForm
						.getHdnReqID().trim());
				
			}
			
			/** This part of code load the status details of the Request * */
			
			objRequestVO.setRequestID(intReqID);
			objRequestVO.setUserID(objLoginVo.getUserID());
			ChangeRequestBI objChangeRequestBO = ServiceFactory
			.getChangeRequestBO();
			arlRequestList = objChangeRequestBO
			.fetchRequestDetails(objRequestVO);
			/* Removed for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151
			///To set Not Valid and InProgress in same fashion
			RequestVO objReqVO = new RequestVO();
			ArrayList objjRequest = new ArrayList();
			
			for(int k=0;k<arlRequestList.size();k++){
				
				objReqVO=(RequestVO)arlRequestList.get(k);
			
			if(objReqVO.getStatusTypeSeqNo() == 7){
				
				objReqVO.setStatusTypeSeqNo(1);
				
			}
			objjRequest.add(objReqVO);
			
			}
			
			
			if (objjRequest.size() != 0) {*/
				objCreateChngClaReqForm.setRequestList(arlRequestList);
			//}
			
			/**
			 * To Load the Request Model Details,like model ,section and
			 * Subsection seqno's.
			 */
			
			objRequestModelVO = objChangeRequestBO
			.fetchReqModelDetails(objRequestVO);
			
			/**
			 * This logic is for getting the model seq no and pass it to
			 * SectionVo to load the Section's *
			 */
			
			LogUtil.logMessage("objRequestModelVO :" + objRequestModelVO);
			
			if (objRequestModelVO != null) {
				// Added For CR_80 to add Specification Type Dropdown
				intSpecTypeNo = objRequestModelVO.getSpecTypeNo();
				// Added For CR_80 to add Specification Type Dropdown - Ends here
				intModelSeqNo = objRequestModelVO.getModelSeqNo();
				intSecSeqNo = objRequestModelVO.getSectionSeqNo();
				intSubSecSeqNo = objRequestModelVO.getSubSectionSeqNo();
			}
			
			LogUtil.logMessage("intModelSeqNo :" + intModelSeqNo);
			LogUtil.logMessage("intSecSeqNo :" + intSecSeqNo);
			LogUtil.logMessage("intSubSecSeqNo :" + intSubSecSeqNo);
			
			/*******************************************************************
			 * This logic is for Enabling and disabling the preview button in
			 * JSP Preview Button should be enabled only if the flag value is
			 * 'Y'
			 ******************************************************************/
			// Modified For CR_80 by adding Spectype in if loop
			if (intSpecTypeNo == 0 && intModelSeqNo == 0 && intSecSeqNo == 0 && intSubSecSeqNo == 0) {
				
				objCreateChngClaReqForm.setReqModelSaved("N");
				
			} else {
				
				objCreateChngClaReqForm.setReqModelSaved("Y");
			}
			
			LogUtil.logMessage("ReqModelSaved:"
					+ objCreateChngClaReqForm.getReqModelSaved());
			
			objRequestModelVO = null;
			/** Reseting the RequestModelVo to null * */
			
			objRequestModelVO = objChangeRequestBO
			.fetchReqCompGrpDetails(objRequestVO);
			
			if (objRequestModelVO != null) {
				objCreateChngClaReqForm.setCompGrpNameVO(objRequestModelVO);
				
			}
			
			/** Button Level Access* */
			/**
			 * This logic is for enabling and disabling of Approve,Reject,Onhold
			 * and Complete Buttons's *
			 */
			
			if (ApplicationConstants.ADMN.equals(objLoginVo.getRoleID())
					|| ApplicationConstants.MSE.equals(objLoginVo.getRoleID()))
				objCreateChngClaReqForm.setRoleID(ApplicationConstants.ADMN);
			
			else
				objCreateChngClaReqForm.setRoleID("OTHR");//ASM,SE
			
			/** This is for getting request user ID * */
			
			if (arlRequestList != null && arlRequestList.size() > 0) {
				
				for (int i = 0; i < arlRequestList.size(); i++) {
					RequestVO objReq = (RequestVO) arlRequestList.get(i);
					strReqUserID = objReq.getReqUserID();
					objCreateChngClaReqForm.setReason(objReq.getReason());
					objCreateChngClaReqForm.setAdminComments(objReq
							.getAdminComments());
					
				}
			}
			
			/*******************************************************************
			 * This part is for checking logged in user ID and Request Created
			 * User ID If equal then user have provision to modify request, else
			 * only preview available
			 ******************************************************************/
			
			strUser = objLoginVo.getUserID();
			if (strReqUserID != null && strUser != null) {
				
				if (strReqUserID.trim().equals(strUser.trim())) {
					objCreateChngClaReqForm.setUserOwnRequest("Y");
				} else {
					objCreateChngClaReqForm.setUserOwnRequest("N");
				}
			}

			// Added For CR_80 to add Specification Type Dropdown
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objCreateChngClaReqForm.setSpecTypeList(arlSpec);
			}	

			objModelVo.setSpecTypeSeqNo(intSpecTypeNo);	
			// Added For CR_80 to add Specification Type Dropdown - Ends here
			ModelBI objModelBO = ServiceFactory.getModelBO();
			ArrayList arlModelList = objModelBO.fetchModels(objModelVo);
			if (arlModelList.size() != 0) {
				objCreateChngClaReqForm.setModelList(arlModelList);
			}
			StandardEquipVO objStandardEquipVO = new StandardEquipVO();
			objStandardEquipVO.setUserID(objLoginVo.getUserID());
			StandardEquipBI objStandardEquipBO = ServiceFactory
			.getStandardEquipBO();
			arlStandardEquipList = objStandardEquipBO
			.fetchStandardEquipment(objStandardEquipVO);
			
			if (arlStandardEquipList.size() != 0) {
				objCreateChngClaReqForm
				.setStdEquipmentList(arlStandardEquipList);
			}
			
			objSectionVO.setModelSeqNo(objCreateChngClaReqForm.getModelSeqNo());
			objSectionVO.setUserID(objLoginVo.getUserID());
			
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			arlSectionList = objSectionBO.fetchSections(objSectionVO);
			if (arlSectionList.size() != 0) {
				objCreateChngClaReqForm.setSectionList(arlSectionList);
			}
			//Adding for CR_94
			{
			RequestVO objNewReqVO = new RequestVO();
			objNewReqVO.setStatusTypeSeqNo(2);
			objNewReqVO.setLastName("All");
			objNewReqVO.setRequestID(0);
			ChangeRequestBI objNewChangeRequestBI = ServiceFactory.getChangeRequestBO();
			ArrayList arlSearchList = new ArrayList();
			arlSearchList = objNewChangeRequestBI.fetchRequestDetails(objNewReqVO);
			objCreateChngClaReqForm.setSearchList(arlSearchList);
			}
			//Ends here CR_94
			
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in CreateChangeReqClauseAction:fetchSections..");
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
	 * This method is used to load the subsections for the selected section.
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
	
	public ActionForward fetchSubSections(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil
		.logMessage("Enters into CreateChangeReqClauseAction:fetchSubSections");
		String strForwardKey = ApplicationConstants.SUCCESS;
		CreateChangeClauseRequestForm objCreateChngClaReqForm = (CreateChangeClauseRequestForm) objActionForm;
		ModelVo objModelVo = new ModelVo();
		ArrayList arlRequestList = new ArrayList();
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlSubSectionList = new ArrayList();
		LoginVO objLoginVo = null;
		ArrayList arlStandardEquipList = new ArrayList();
		RequestVO objRequestVO = new RequestVO();
		SectionVO objSectionVO = new SectionVO();
		String strReqUserID = null;
		String strUser = null;
		int intReqID = 0;
		int intModelSeqNo = 0;
		int intSecSeqNo = 0;
		int intSubSecSeqNo = 0;
		RequestModelVO objRequestModelVO = null;
		// Added For CR_80 to add Specification Type Dropdown
		int intSpecTypeNo = 0;
		// Added For CR_80 to add Specification Type Dropdown - Ends here
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LogUtil.logMessage(objSession);
			
			if (objSession != null) {
				objLoginVo = (LoginVO) objSession
				.getAttribute(ApplicationConstants.USER_IN_SESSION);
				
			}
			
			objModelVo.setUserID(objLoginVo.getUserID());
			
			if (objCreateChngClaReqForm.getHdnReqID() != null
					&& !"".equalsIgnoreCase(objCreateChngClaReqForm
							.getHdnReqID())) {
				
				intReqID = Integer.parseInt(objCreateChngClaReqForm
						.getHdnReqID().trim());
				
			}
			
			/** This part of code load the status details of the Request * */
			
			objRequestVO.setRequestID(intReqID);
			objRequestVO.setUserID(objLoginVo.getUserID());
			ChangeRequestBI objChangeRequestBO = ServiceFactory
			.getChangeRequestBO();
			arlRequestList = objChangeRequestBO
			.fetchRequestDetails(objRequestVO);
			/* Removed for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151
			///To set Not Valid and InProgress in same fashion
			RequestVO objReqVO = new RequestVO();
			ArrayList objjRequest = new ArrayList();
			
			for(int k=0;k<arlRequestList.size();k++){
				
				objReqVO=(RequestVO)arlRequestList.get(k);
				
			if(objReqVO.getStatusTypeSeqNo() == 7){
				
				objReqVO.setStatusTypeSeqNo(1);
				
			}
			objjRequest.add(objReqVO);
			
			}
			
			
			if (objjRequest.size() != 0) {*/
			objCreateChngClaReqForm.setRequestList(arlRequestList);
			//}
			
			/**
			 * To Load the Request Model Details,like model ,section and
			 * Subsection seqno's.
			 */
			
			objRequestModelVO = objChangeRequestBO
			.fetchReqModelDetails(objRequestVO);
			
			/**
			 * This logic is for getting the model seq no and pass it to
			 * SectionVo to load the Section's *
			 */
			
			LogUtil.logMessage("objRequestModelVO :" + objRequestModelVO);
			
			if (objRequestModelVO != null) {
				// Added For CR_80 to add Specification Type Dropdown
				intSpecTypeNo = objRequestModelVO.getSpecTypeNo();
				// Added For CR_80 to add Specification Type Dropdown - Ends here			
				intModelSeqNo = objRequestModelVO.getModelSeqNo();
				intSecSeqNo = objRequestModelVO.getSectionSeqNo();
				intSubSecSeqNo = objRequestModelVO.getSubSectionSeqNo();
			}
			
			LogUtil.logMessage("intModelSeqNo :" + intModelSeqNo);
			LogUtil.logMessage("intSecSeqNo :" + intSecSeqNo);
			LogUtil.logMessage("intSubSecSeqNo :" + intSubSecSeqNo);
			
			/*******************************************************************
			 * This logic is for Enabling and disabling the preview button in
			 * JSP Preview Button should be enabled only if the flag value is
			 * 'Y'
			 ******************************************************************/
			// Modified For CR_80 by adding Spectype in if loop
			if (intSpecTypeNo == 0 && intModelSeqNo == 0 && intSecSeqNo == 0 && intSubSecSeqNo == 0) {
				
				objCreateChngClaReqForm.setReqModelSaved("N");
				
			} else {
				
				objCreateChngClaReqForm.setReqModelSaved("Y");
			}
			
			LogUtil.logMessage("ReqModelSaved:"
					+ objCreateChngClaReqForm.getReqModelSaved());
			
			objRequestModelVO = null;
			/** Reseting the RequestModelVo to null * */
			
			objRequestModelVO = objChangeRequestBO
			.fetchReqCompGrpDetails(objRequestVO);
			
			if (objRequestModelVO != null) {
				objCreateChngClaReqForm.setCompGrpNameVO(objRequestModelVO);
				
			}
			
			/** Button Level Access* */
			/**
			 * This logic is for enabling and disabling of Approve,Reject,Onhold
			 * and Complete Buttons's *
			 */
			
			if (ApplicationConstants.ADMN.equals(objLoginVo.getRoleID())
					|| ApplicationConstants.MSE.equals(objLoginVo.getRoleID()))
				objCreateChngClaReqForm.setRoleID(ApplicationConstants.ADMN);
			else
				objCreateChngClaReqForm.setRoleID("OTHR");//ASM,SE
			
			/** This is for getting request user ID * */
			
			if (arlRequestList != null && arlRequestList.size() > 0) {
				
				for (int i = 0; i < arlRequestList.size(); i++) {
					RequestVO objReq = (RequestVO) arlRequestList.get(i);
					strReqUserID = objReq.getReqUserID();
					objCreateChngClaReqForm.setReason(objReq.getReason());
					objCreateChngClaReqForm.setAdminComments(objReq
							.getAdminComments());
				}
			}
			
			/*******************************************************************
			 * This part is for checking logged in user ID and Request Created
			 * User ID If equal then user have provision to modify request, else
			 * only preview available
			 ******************************************************************/
			
			strUser = objLoginVo.getUserID();
			if (strReqUserID != null && strUser != null) {
				
				if (strReqUserID.trim().equals(strUser.trim())) {
					objCreateChngClaReqForm.setUserOwnRequest("Y");
				} else {
					objCreateChngClaReqForm.setUserOwnRequest("N");
				}
			}
			// Added For CR_80 to add Specification Type Dropdown
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objCreateChngClaReqForm.setSpecTypeList(arlSpec);
			}

			objModelVo.setSpecTypeSeqNo(intSpecTypeNo);	
			// Added For CR_80 to add Specification Type Dropdown - Ends here	
			ModelBI objModelBO = ServiceFactory.getModelBO();
			ArrayList arlModelList = objModelBO.fetchModels(objModelVo);
			if (arlModelList.size() != 0) {
				objCreateChngClaReqForm.setModelList(arlModelList);
			}
			StandardEquipVO objStandardEquipVO = new StandardEquipVO();
			objStandardEquipVO.setUserID(objLoginVo.getUserID());
			StandardEquipBI objStandardEquipBO = ServiceFactory
			.getStandardEquipBO();
			arlStandardEquipList = objStandardEquipBO
			.fetchStandardEquipment(objStandardEquipVO);
			
			if (arlStandardEquipList.size() != 0) {
				objCreateChngClaReqForm
				.setStdEquipmentList(arlStandardEquipList);
			}
			
			objSectionVO.setModelSeqNo(objCreateChngClaReqForm.getModelSeqNo());
			objSectionVO.setUserID(objLoginVo.getUserID());
			
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			arlSectionList = objSectionBO.fetchSections(objSectionVO);
			if (arlSectionList.size() != 0) {
				objCreateChngClaReqForm.setSectionList(arlSectionList);
			}
			
			SubSectionVO objSubSectionVO = new SubSectionVO();
			objSubSectionVO.setModelSeqNo(objCreateChngClaReqForm
					.getModelSeqNo());
			objSubSectionVO.setSecSeqNo(objCreateChngClaReqForm
					.getSectionSeqNo());
			objSubSectionVO.setUserID(objLoginVo.getUserID());
			
			ModelSubSectionBI objModelSubSecBO = ServiceFactory
			.getSubSecMaintBO();
			arlSubSectionList = objModelSubSecBO
			.fetchSubSections(objSubSectionVO);
			
			if (arlSubSectionList.size() != 0) {
				objCreateChngClaReqForm.setSubSectionList(arlSubSectionList);
			}
			//Adding for CR_94
			{
			RequestVO objNewReqVO = new RequestVO();
			objNewReqVO.setStatusTypeSeqNo(2);
			objNewReqVO.setLastName("All");
			objNewReqVO.setRequestID(0);
			ChangeRequestBI objNewChangeRequestBI = ServiceFactory.getChangeRequestBO();
			ArrayList arlSearchList = new ArrayList();
			arlSearchList = objNewChangeRequestBI.fetchRequestDetails(objNewReqVO);
			objCreateChngClaReqForm.setSearchList(arlSearchList);
			}
			//Ends here CR_94
			
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in CreateChangeReqClauseAction:fetchSubSections..");
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
	 * This method is used to select the clause for the Modify Clause
	 * option(From part).
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
	
	public ActionForward selectFromClause(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		LogUtil
		.logMessage("Inside the CreateChangeReqClauseAction:selectFromClause");
		ArrayList arlParentList = new ArrayList();
		String strForwardKey = ApplicationConstants.SUCCESS;
		String strUserID = "";
		int intModelSeqNo = 0;
		int intSectionSeqNo = 0;
		int intSubSectionSeqNo = 0;
		String strSelectedSection = "";
		String strSelectedModel = "";
		String strSelectedSubSection = "";
		int intCompGrpSeqNo = 0;
		int intCompSeqNo = 0;
		String strSpecType = "";
		ArrayList arlClauseList = new ArrayList();
		int intChangeTypeSeqNo = 0;
		
		/*
		 * ModelSeqNo,SectionSeqNo And SubSectionSeqNo are getting as a request
		 * parameters to generate the clause numbers for the selected subsection
		 * in selectFromClause pop-up screen. This action calls the
		 * ViewSpecByModel DAO in order to reuse the clause generation logic at
		 * Model Level.
		 *  
		 */
		
		if (objHttpServletRequest
				.getParameter(ApplicationConstants.SELECTED_MODEL_NAME) != null) {
			strSelectedModel = objHttpServletRequest
			.getParameter(ApplicationConstants.SELECTED_MODEL_NAME);
		}
		
		if (objHttpServletRequest
				.getParameter(ApplicationConstants.SELECTED_SECTION_NAME) != null) {
			strSelectedSection = objHttpServletRequest
			.getParameter(ApplicationConstants.SELECTED_SECTION_NAME);
		}
		
		if (objHttpServletRequest
				.getParameter(ApplicationConstants.SELECTED_SUB_SECTION_NAME) != null) {
			strSelectedSubSection = objHttpServletRequest
			.getParameter(ApplicationConstants.SELECTED_SUB_SECTION_NAME);
		}
		
		if (objHttpServletRequest
				.getParameter(ApplicationConstants.SELECTED_SUB_SECTION_ID) != null) {
			intSubSectionSeqNo = Integer
			.parseInt(objHttpServletRequest
					.getParameter(ApplicationConstants.SELECTED_SUB_SECTION_ID));
		}
		if (objHttpServletRequest.getParameter("selectedSectionID") != null) {
			intSectionSeqNo = Integer.parseInt(objHttpServletRequest
					.getParameter("selectedSectionID"));
		}
		if (objHttpServletRequest.getParameter("selectedModelID") != null) {
			intModelSeqNo = Integer.parseInt(objHttpServletRequest
					.getParameter("selectedModelID"));
		}
		
		if (objHttpServletRequest.getParameter("CompGrpSeqNo") != null) {
			intCompGrpSeqNo = Integer.parseInt(objHttpServletRequest
					.getParameter("CompGrpSeqNo"));
		}
		
		if (objHttpServletRequest.getParameter("CompSeqNo") != null) {
			intCompSeqNo = Integer.parseInt(objHttpServletRequest
					.getParameter("CompSeqNo"));
		}
		if (objHttpServletRequest.getParameter("ChangeTypeSeqNo") != null) {
			intChangeTypeSeqNo = Integer.parseInt(objHttpServletRequest
					.getParameter("ChangeTypeSeqNo"));
		}
		
		SubSectionVO objSubSectionVO = new SubSectionVO();
		try {
			CreateChangeClauseRequestForm objCreateChngClaReqForm = (CreateChangeClauseRequestForm) objActionForm;
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			
			objCreateChngClaReqForm.setHdnModelSeqNo(intModelSeqNo);
			objCreateChngClaReqForm.setHdnSubSectionSeqNo(intSubSectionSeqNo);
			objCreateChngClaReqForm.setSectionSeqNo(intSectionSeqNo);
			objCreateChngClaReqForm.setChangeTypeSeqNO(intChangeTypeSeqNo);
			ClauseVO objClauseVO = new ClauseVO();
			LogUtil.logMessage("ModelSeqNo in SelectFromClause**"
					+ objCreateChngClaReqForm.getHdnModelSeqNo());
			objClauseVO.setModelSeqNo(objCreateChngClaReqForm
					.getHdnModelSeqNo());
			LogUtil.logMessage("SubSectionSeqNo in SelectFromClause**"
					+ objCreateChngClaReqForm.getHdnSubSectionSeqNo());
			objClauseVO.setSubSectionSeqNo(objCreateChngClaReqForm
					.getHdnSubSectionSeqNo());
			LogUtil.logMessage("ChangeType in SelectFromClause**"
					+ intChangeTypeSeqNo);
			//Adding for CR_94
			{
			RequestVO objNewReqVO = new RequestVO();
			objNewReqVO.setStatusTypeSeqNo(2);
			objNewReqVO.setLastName("All");
			objNewReqVO.setRequestID(0);
			ChangeRequestBI objNewChangeRequestBI = ServiceFactory.getChangeRequestBO();
			ArrayList arlSearchList = new ArrayList();
			arlSearchList = objNewChangeRequestBI.fetchRequestDetails(objNewReqVO);
			objCreateChngClaReqForm.setSearchList(arlSearchList);
			}
			//Ends here CR_94
			/* Removed for CR_80 CR Form Enhancements III on 25-Nov-09 by RR68151
			 * if (intChangeTypeSeqNo == ApplicationConstants.CHANGE_TYPE_DESC_DEFAULT_VERSION) {
				
				intCompGrpSeqNo = 0;
				intCompSeqNo = 0;
			}*/
			
			// Added for CR_80 CR Form Enhancements III on 26-Nov-09 by RR68151
			if (Integer.parseInt(objHttpServletRequest.getParameter("CompGrpChngTypeSeqNo"))
					== ApplicationConstants.CHANGE_TYPE_NOT_REQUIRED) 		
				objClauseVO.setCoreClausesFlag(ApplicationConstants.YES);		
			else		
				objClauseVO.setCoreClausesFlag(ApplicationConstants.NO);
			
			objClauseVO.setLeadCompGrpSeqNo(intCompGrpSeqNo);
			objClauseVO.setComponentSeqNo(String.valueOf(intCompSeqNo));
			LogUtil.logMessage("hdnCompGrpSeqNo No in SelectFromClause**"
					+ intCompGrpSeqNo);
			
			LogUtil.logMessage("hdnComponentSeqNo No in SelectFromClause**"
					+ intCompSeqNo);
			
			objClauseVO.setDefaultFlag(ApplicationConstants.YES);
			objClauseVO.setUserID(objLoginVo.getUserID());
			
			ModelSelectClauseRevBI objModelSelectClauseRevBO = ServiceFactory
			.getModelSelectClauseRevBO();
			arlParentList = objModelSelectClauseRevBO
			.fetchClauseForLeadComp(objClauseVO);
			
			if (arlParentList.size() != 0 && arlParentList != null) {
				strForwardKey = ApplicationConstants.CRForm_Select_Clause;
				arlClauseList = (ArrayList) arlParentList.get(0);
				objCreateChngClaReqForm.setComponentVO(arlClauseList);
				objCreateChngClaReqForm.setModelName(strSelectedModel);
				objCreateChngClaReqForm.setSectionName(strSelectedSection);
				objCreateChngClaReqForm
				.setSubSectionName(strSelectedSubSection);
				LogUtil
				.logMessage("Size Of ClauseList:"
						+ arlClauseList.size());
				if (arlClauseList.size() == 0) {
					objCreateChngClaReqForm
					.setMessageID(ApplicationConstants.NO_RECORDS_FOUND);
				}
				return objActionMapping.findForward(strForwardKey);
			}
			
		}
		
		catch (Exception objExp) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
			
		}
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***************************************************************************
	 * This method is used to fetch the default version of the selected clause.
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
	
	public ActionForward fetchDefaultVersion(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil
		.logMessage("Enters into CreateChangeReqClauseAction:fetchDefaultVersion");
		String strForwardKey = ApplicationConstants.SUCCESS;
		CreateChangeClauseRequestForm objCreateChngClaReqForm = (CreateChangeClauseRequestForm) objActionForm;
		ModelVo objModelVo = new ModelVo();
		ArrayList arlRequestList = new ArrayList();
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlSubSectionList = new ArrayList();
		LoginVO objLoginVo = null;
		ArrayList arlStandardEquipList = new ArrayList();
		RequestVO objRequestVO = new RequestVO();
		SectionVO objSectionVO = new SectionVO();
		ArrayList arlAllClauseList = new ArrayList();
		ArrayList arlClauseList = new ArrayList();
		String strReqUserID = null;
		String strUser = null;
		int intReqID = 0;
		int intModelSeqNo = 0;
		int intSecSeqNo = 0;
		int intSubSecSeqNo = 0;
		RequestModelVO objRequestModelVO = null;
		// Added For CR_80 to add Specification Type Dropdown
		int intSpecTypeNo = 0;
		// Added For CR_80 to add Specification Type Dropdown - Ends here	
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LogUtil.logMessage(objSession);
			
			if (objSession != null) {
				objLoginVo = (LoginVO) objSession
				.getAttribute(ApplicationConstants.USER_IN_SESSION);
				
			}
			
			objModelVo.setUserID(objLoginVo.getUserID());
			
			if (objCreateChngClaReqForm.getHdnReqID() != null
					&& !"".equalsIgnoreCase(objCreateChngClaReqForm
							.getHdnReqID())) {
				
				intReqID = Integer.parseInt(objCreateChngClaReqForm
						.getHdnReqID().trim());
				
			}
			
			/** This part of code load the status details of the Request * */
			
			objRequestVO.setRequestID(intReqID);
			objRequestVO.setUserID(objLoginVo.getUserID());
			ChangeRequestBI objChangeRequestBO = ServiceFactory
			.getChangeRequestBO();
			arlRequestList = objChangeRequestBO
			.fetchRequestDetails(objRequestVO);
			/* Removed for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151
			///To set Not Valid and InProgress in same fashion
			RequestVO objReqVO = new RequestVO();
			ArrayList objjRequest = new ArrayList();
			
			for(int k=0;k<arlRequestList.size();k++){
				
				objReqVO=(RequestVO)arlRequestList.get(k);
			
			if(objReqVO.getStatusTypeSeqNo() == 7){
				
				objReqVO.setStatusTypeSeqNo(1);
				
			}
			objjRequest.add(objReqVO);
			
			}
			
			
			if (objjRequest.size() != 0) {*/
				objCreateChngClaReqForm.setRequestList(arlRequestList);
			//}
			
			/**
			 * To Load the Request Model Details,like model ,section and
			 * Subsection seqno's.
			 */
			
			objRequestModelVO = objChangeRequestBO
			.fetchReqModelDetails(objRequestVO);
			
			/**
			 * This logic is for getting the model seq no and pass it to
			 * SectionVo to load the Section's *
			 */
			
			LogUtil.logMessage("objRequestModelVO :" + objRequestModelVO);
			
			if (objRequestModelVO != null) {
				// Added For CR_80 to add Specification Type Dropdown
				intSpecTypeNo = objRequestModelVO.getSpecTypeNo();
				// Added For CR_80 to add Specification Type Dropdown - Ends here				
				intModelSeqNo = objRequestModelVO.getModelSeqNo();
				intSecSeqNo = objRequestModelVO.getSectionSeqNo();
				intSubSecSeqNo = objRequestModelVO.getSubSectionSeqNo();
			}
			
			LogUtil.logMessage("intModelSeqNo :" + intModelSeqNo);
			LogUtil.logMessage("intSecSeqNo :" + intSecSeqNo);
			LogUtil.logMessage("intSubSecSeqNo :" + intSubSecSeqNo);
			
			/*******************************************************************
			 * This logic is for Enabling and disabling the preview button in
			 * JSP Preview Button should be enabled only if the flag value is
			 * 'Y'
			 ******************************************************************/
			// Modified For CR_80 to add SpecType in if loop	
			if (intSpecTypeNo == 0 && intModelSeqNo == 0 && intSecSeqNo == 0 && intSubSecSeqNo == 0) {
				
				objCreateChngClaReqForm.setReqModelSaved("N");
				
			} else {
				
				objCreateChngClaReqForm.setReqModelSaved("Y");
				
				/*
				 * Change for Model apply flag
				 */
				if (objRequestModelVO.isApplyModelFlag()) {
					
					objCreateChngClaReqForm.setApplyModelFlag(true);
				} else {
					objCreateChngClaReqForm.setApplyModelFlag(false);
				}
			}
			
			LogUtil.logMessage("ReqModelSaved:"
					+ objCreateChngClaReqForm.getReqModelSaved());
			
			objRequestModelVO = null;
			/** Reseting the RequestModelVo to null * */
			
			objRequestModelVO = objChangeRequestBO
			.fetchReqCompGrpDetails(objRequestVO);
			
			if (objRequestModelVO != null) {
				objCreateChngClaReqForm.setCompGrpNameVO(objRequestModelVO);
				
			}
			
			/** Button Level Access* */
			/**
			 * This logic is for enabling and disabling of Approve,Reject,Onhold
			 * and Complete Buttons's *
			 */
			
			if (ApplicationConstants.ADMN.equals(objLoginVo.getRoleID())
					|| ApplicationConstants.MSE.equals(objLoginVo.getRoleID()))
				objCreateChngClaReqForm.setRoleID(ApplicationConstants.ADMN);
			
			else
				objCreateChngClaReqForm.setRoleID("OTHR");//ASM,SE
			
			/** This is for getting request user ID * */
			
			if (arlRequestList != null && arlRequestList.size() > 0) {
				
				for (int i = 0; i < arlRequestList.size(); i++) {
					RequestVO objReq = (RequestVO) arlRequestList.get(i);
					strReqUserID = objReq.getReqUserID();
					objCreateChngClaReqForm.setReason(objReq.getReason());
					objCreateChngClaReqForm.setAdminComments(objReq
							.getAdminComments());
				}
			}
			
			/*******************************************************************
			 * This part is for checking logged in user ID and Request Created
			 * User ID If equal then user have provision to modify request, else
			 * only preview available
			 ******************************************************************/
			
			strUser = objLoginVo.getUserID();
			if (strReqUserID != null && strUser != null) {
				
				if (strReqUserID.trim().equals(strUser.trim())) {
					objCreateChngClaReqForm.setUserOwnRequest("Y");
				} else {
					objCreateChngClaReqForm.setUserOwnRequest("N");
				}
			}

			// Added For CR_80 to add Specification Type Dropdown
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objCreateChngClaReqForm.setSpecTypeList(arlSpec);
			}
			
			objModelVo.setSpecTypeSeqNo(intSpecTypeNo);	
			// Added For CR_80 to add Specification Type Dropdown - Ends here	
			ModelBI objModelBO = ServiceFactory.getModelBO();
			ArrayList arlModelList = objModelBO.fetchModels(objModelVo);
			if (arlModelList.size() != 0) {
				objCreateChngClaReqForm.setModelList(arlModelList);
			}
			StandardEquipVO objStandardEquipVO = new StandardEquipVO();
			objStandardEquipVO.setUserID(objLoginVo.getUserID());
			StandardEquipBI objStandardEquipBO = ServiceFactory
			.getStandardEquipBO();
			arlStandardEquipList = objStandardEquipBO
			.fetchStandardEquipment(objStandardEquipVO);
			
			if (arlStandardEquipList.size() != 0) {
				objCreateChngClaReqForm
				.setStdEquipmentList(arlStandardEquipList);
			}
			
			objSectionVO.setModelSeqNo(objCreateChngClaReqForm
					.getHdnModelSeqNo());
			objSectionVO.setUserID(objLoginVo.getUserID());
			
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			arlSectionList = objSectionBO.fetchSections(objSectionVO);
			if (arlSectionList.size() != 0) {
				objCreateChngClaReqForm.setSectionList(arlSectionList);
			}
			
			SubSectionVO objSubSectionVO = new SubSectionVO();
			objSubSectionVO.setModelSeqNo(objCreateChngClaReqForm
					.getHdnModelSeqNo());
			objSubSectionVO.setSecSeqNo(objCreateChngClaReqForm
					.getHdnSectionSeqNo());
			objSubSectionVO.setUserID(objLoginVo.getUserID());
			ModelSubSectionBI objModelSubSecBO = ServiceFactory
			.getSubSecMaintBO();
			arlSubSectionList = objModelSubSecBO
			.fetchSubSections(objSubSectionVO);
			
			if (arlSubSectionList.size() != 0) {
				objCreateChngClaReqForm.setSubSectionList(arlSubSectionList);
			}
			
			ClauseVO objClauseVO = new ClauseVO();
			LogUtil.logMessage("ModelSeqNo in FetchClauseVersion**"
					+ objCreateChngClaReqForm.getHdnModelSeqNo());
			objClauseVO.setModelSeqNo(objCreateChngClaReqForm
					.getHdnModelSeqNo());
			LogUtil.logMessage("SubSectionSeqNo in FetchClauseVersion**"
					+ objCreateChngClaReqForm.getHdnSubSectionSeqNo());
			objClauseVO.setSubSectionSeqNo(objCreateChngClaReqForm
					.getHdnSubSectionSeqNo());
			LogUtil.logMessage("ClauseSeqNo in FetchClauseVersion**"
					+ objCreateChngClaReqForm.getHdnClauseSeqNo());
			objClauseVO.setClauseSeqNo(objCreateChngClaReqForm
					.getHdnClauseSeqNo());
			LogUtil.logMessage("Version No in FetchClauseVersion**"
					+ objCreateChngClaReqForm.getHdnVersionNo());

			objClauseVO.setUserID(objLoginVo.getUserID());
			
			ModelSelectClauseRevBI objModelSelectClauseRevBO = ServiceFactory
			.getModelSelectClauseRevBO();
			//Added CR_80 to get the no Of Versions of Clause
			ArrayList arlAllVersionList = objModelSelectClauseRevBO
			.fetchClauseVersions(objClauseVO);
			ArrayList arlAllVersion = (ArrayList) arlAllVersionList.get(0);
			objClauseVO.setVersionNo(objCreateChngClaReqForm.getHdnVersionNo());
			arlAllClauseList = objModelSelectClauseRevBO
			.fetchClauseVersions(objClauseVO);

			/*******************************************************************
			 * Here the RequestClauseVo is populated by getting the values from
			 * the clauseVo to populate the same details for CreateTo part also.
			 ******************************************************************/
			
			if (arlAllClauseList != null && arlAllClauseList.size() > 0) {

				//arlAllVersion = (ArrayList) arlClauseList.get(0);
				//Added For CR_80 by RR68151
				arlClauseList = (ArrayList) arlAllClauseList.get(1);
				if (arlClauseList == null || arlClauseList.size() == 0){
					objCreateChngClaReqForm.setDefaultFlag(ApplicationConstants.NO);
					arlClauseList = (ArrayList) arlAllClauseList.get(0);
				}
				else{
					objCreateChngClaReqForm.setDefaultFlag(ApplicationConstants.YES);
				}
				objCreateChngClaReqForm.setNoOfClaVersion(arlAllVersion.size());
				objCreateChngClaReqForm.setClauseList(arlClauseList);
				if (objCreateChngClaReqForm.getChangeTypeSeqNO() != ApplicationConstants.CHANGE_TYPE_DESC_DEFAULT_VERSION
					 //Modified for CR_80 CR Form Enhancements III on 25-Nov-09 by RR68151
						&& objCreateChngClaReqForm.getChangeTypeSeqNO() != ApplicationConstants.CHANGE_TYPE_DESC_DELETE_CLAUSE_VERSION) {

					ClauseVO objClauseVo = new ClauseVO();
					ReqClauseVO objReqClauseVO = new ReqClauseVO();
					ArrayList arlReqList = new ArrayList();
					//int[] test = new int[4];
					objClauseVo = (ClauseVO) arlClauseList.get(0);
					objReqClauseVO.setToParentClauseNo("");
					objReqClauseVO.setToParentClaDesc("");
					objReqClauseVO.setToParentClauseNo("");
					objReqClauseVO.setToClaVerClauseNo("");
					objReqClauseVO.setToClaVerDesc("");
					objReqClauseVO.setToClauseDesc(objClauseVo
							.getClauseDescForTextArea());
					LogUtil.logMessage("ToClauseDesc:"
							+ objReqClauseVO.getToClauseDesc());
					objReqClauseVO.setTableData(objClauseVo
							.getTableArrayData1());
					objReqClauseVO.setClaComp(objClauseVo.getCompName());
					objReqClauseVO.setToComponentSeqNo(objClauseVo
							.getComponentSeqNo());
					/*Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151
					objReqClauseVO.setToDefaultFlag(objClauseVo
							.getDefaultFlag());*/
					objReqClauseVO.setToRefEDLNo(objClauseVo.getRefEDLNo());
					objReqClauseVO.setToEdlNo(objClauseVo.getEdlNo());
					objReqClauseVO.setPartOfClaNo(objClauseVo.getPartOfCode());
					objReqClauseVO.setPartOfClaSeqNo(objClauseVo
							.getReqpartOfClaSeqNo());
					objReqClauseVO.setPartOfSubSecSeqNo(objClauseVo
							.getPartOfSeqNo());
					objReqClauseVO.setTodwONumber(objClauseVo.getDwONumber());
					LogUtil.logMessage("TodwONumber:"
							+ objReqClauseVO.getTodwONumber());
					objReqClauseVO.setTopriceBookNumber(objClauseVo
							.getPriceBookNumber());
					objReqClauseVO.setToPartNumber(objClauseVo.getPartNumber());
					objReqClauseVO.setToStdEquipSeqNo(objClauseVo
							.getStandardEquipmentSeqNo());
					objReqClauseVO.setToComments(objClauseVo.getComments());
					arlReqList.add(objReqClauseVO);
					LogUtil.logMessage("Size of ReqClause List:"
							+ arlReqList.size());
					if (arlReqList != null && arlReqList.size() > 0) {
						LogUtil.logMessage("Size of ReqClause List:"
								+ arlReqList.size());
						objCreateChngClaReqForm.setReqClauseList(arlReqList);
					}
					LogUtil
					.logMessage("Size of ReqClause List:"
							+ objCreateChngClaReqForm
							.getReqClauseList().size());
				}
			}
			
			if (objCreateChngClaReqForm.getReqModelSaved() == "Y") {
				//Added For Specification Drop down CR_80
				if (intSpecTypeNo == 0)	{
					objCreateChngClaReqForm.setSpecTypeNo(-1);
				} else {
					objCreateChngClaReqForm.setSpecTypeNo(intSpecTypeNo);
				}
				//Added For Specification Drop down CR_80 - Ends here
				if (intModelSeqNo == 0) {
					objCreateChngClaReqForm.setModelSeqNo(-1);
				} else {
					objCreateChngClaReqForm.setModelSeqNo(intModelSeqNo);
				}
				
				if (intSecSeqNo == 0) {
					objCreateChngClaReqForm.setSectionSeqNo(-1);
				} else {
					objCreateChngClaReqForm.setSectionSeqNo(intSecSeqNo);
				}
				
				if (intSubSecSeqNo == 0) {
					objCreateChngClaReqForm.setSubSectionSeqNo(-1);
				} else {
					objCreateChngClaReqForm.setSubSectionSeqNo(intSubSecSeqNo);
				}
				//Adding for CR_94
				{
				RequestVO objNewReqVO = new RequestVO();
				objNewReqVO.setStatusTypeSeqNo(2);
				objNewReqVO.setLastName("All");
				objNewReqVO.setRequestID(0);
				ChangeRequestBI objNewChangeRequestBI = ServiceFactory.getChangeRequestBO();
				ArrayList arlSearchList = new ArrayList();
				arlSearchList = objNewChangeRequestBI.fetchRequestDetails(objNewReqVO);
				objCreateChngClaReqForm.setSearchList(arlSearchList);
				}
				//Ends here CR_94
			}
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in CreateChangeReqClauseAction:fetchSubSections..");
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
	 * This method is used to insert the RequestClauseDetails.
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
	
	public ActionForward saveClause(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Inside CreateChangeReqClauseAction :saveClause ");
		
		String strForwardKey = ApplicationConstants.SUCCESS;
		CreateChangeClauseRequestForm objCreateChngClaReqForm = (CreateChangeClauseRequestForm) objActionForm;
		ActionForward actionRedirect = null;
		int intReqID = 0;
		try {
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			ArrayList arlModelList = new ArrayList();
			ArrayList arlCustomerList = new ArrayList();
			ArrayList arlStandardEquipList = new ArrayList();
			ArrayList arlSectionList = new ArrayList();
			ArrayList arlSubSectionList = new ArrayList();
			ReqClauseVO objClauseVO = new ReqClauseVO();
			RequestModelVO objRequestModelVO = new RequestModelVO();
			SectionVO objSecMaintVO = new SectionVO();
			String strUserID = null;
			String strClauseDesc = null;
			String strComments = null;
			String strReason = null;
			int intSuccess = 0;
			String[] clauseTableArray1, clauseTableArray2, clauseTableArray3, clauseTableArray4, clauseTableArray5 = null;
			ClauseTableDataVO objTableDataVO = null;
			ArrayList arlTableList = new ArrayList();
			ArrayList arlRequestList = new ArrayList();
			RequestVO objRequestVO = new RequestVO();
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			ArrayList arlSpec = null;
			
			// Getting User from the session
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			
			if (objCreateChngClaReqForm.getHdnReqID() != null
					&& !"".equalsIgnoreCase(objCreateChngClaReqForm
							.getHdnReqID())) {
				
				intReqID = Integer.parseInt(objCreateChngClaReqForm
						.getHdnReqID().trim());
				
			}
			
			StandardEquipVO objStandardEquipVO = new StandardEquipVO();
			objStandardEquipVO.setUserID(objLoginVo.getUserID());
			StandardEquipBI objStandardEquipBO = ServiceFactory
			.getStandardEquipBO();
			arlStandardEquipList = objStandardEquipBO
			.fetchStandardEquipment(objStandardEquipVO);
			
			if (arlStandardEquipList.size() != 0) {
				objCreateChngClaReqForm
				.setStdEquipmentList(arlStandardEquipList);
			}
			
			clauseTableArray1 = objCreateChngClaReqForm
			.getClauseTableDataArray1();
			
			/*
			 * ArrayList arlSelCompSeqNo = new ArrayList(); StringTokenizer
			 * stCompSeqNo = null;
			 * 
			 * if ((objCreateChngClaReqForm.getToComponentSeqNo() != null)) {
			 * stCompSeqNo = new StringTokenizer(objCreateChngClaReqForm
			 * .getToComponentSeqNo(), "~"); String strToken = null; while
			 * (stCompSeqNo.hasMoreTokens()) { strToken =
			 * stCompSeqNo.nextToken();
			 * 
			 * if (strToken != null && !"null".equals(strToken)) {
			 * arlSelCompSeqNo.add(strToken); } } }
			 * 
			 * String[] strComponentSeqNo = new String[arlSelCompSeqNo.size()];
			 * if (arlSelCompSeqNo != null && arlSelCompSeqNo.size() > 0) { for
			 * (int counter = 0; counter < arlSelCompSeqNo.size(); counter++) {
			 * strComponentSeqNo[counter] = String.valueOf(arlSelCompSeqNo
			 * .get(counter).toString()); } }
			 */
			objClauseVO.setComponentSeqNo(objCreateChngClaReqForm
					.getToComponentSeqNo().split("~"));
			
			if (clauseTableArray1 == null || clauseTableArray1.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData1(null);
			} else {
				
				for (int counter = 0; counter < clauseTableArray1.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData1(objCreateChngClaReqForm
							.getClauseTableDataArray1());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
				
			}
			arlTableList.add(objTableDataVO);
			
			clauseTableArray2 = objCreateChngClaReqForm
			.getClauseTableDataArray2();
			
			if (clauseTableArray2 == null || clauseTableArray2.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData2(null);
			} else {
				for (int counter = 0; counter < clauseTableArray2.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData2(objCreateChngClaReqForm
							.getClauseTableDataArray2());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
				
			}
			arlTableList.add(objTableDataVO);
			
			clauseTableArray3 = objCreateChngClaReqForm
			.getClauseTableDataArray3();
			
			if (clauseTableArray3 == null || clauseTableArray3.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData3(null);
			} else {
				for (int counter = 0; counter < clauseTableArray3.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData3(objCreateChngClaReqForm
							.getClauseTableDataArray3());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
				
			}
			arlTableList.add(objTableDataVO);
			clauseTableArray4 = objCreateChngClaReqForm
			.getClauseTableDataArray4();
			
			if (clauseTableArray4 == null || clauseTableArray4.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData4(null);
			} else {
				for (int counter = 0; counter < clauseTableArray4.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData4(objCreateChngClaReqForm
							.getClauseTableDataArray4());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
				
			}
			arlTableList.add(objTableDataVO);
			clauseTableArray5 = objCreateChngClaReqForm
			.getClauseTableDataArray5();
			
			if (clauseTableArray5 == null || clauseTableArray5.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData5(null);
			} else {
				for (int counter = 0; counter < clauseTableArray5.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData5(objCreateChngClaReqForm
							.getClauseTableDataArray5());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
			}
			
			arlTableList.add(objTableDataVO);
			
			strClauseDesc = objCreateChngClaReqForm.getToClauseDesc();
			strComments = objCreateChngClaReqForm.getToComments();
			strReason = objCreateChngClaReqForm.getReason();
			
			if (strClauseDesc != null && !"".equals(strClauseDesc)) {
				strClauseDesc = strClauseDesc.trim();
			}
			if (strComments != null && !"".equals(strComments)) {
				strComments = strComments.trim();
			}
			if (strReason != null && !"".equals(strReason)) {
				
				strReason = strReason.trim();
			}
			
			int standardEquipSeqNo = objCreateChngClaReqForm
			.getStandardEquipmentSeqNo();
			if (standardEquipSeqNo != -1) {
				objClauseVO.setToStdEquipSeqNo(objCreateChngClaReqForm
						.getStandardEquipmentSeqNo());
			}
			LogUtil.logMessage("StandardEquipSeqNo in Action:"
					+ objCreateChngClaReqForm.getStandardEquipmentSeqNo());
			objClauseVO.setFromClauseNo(objCreateChngClaReqForm.getClauseNo());
			LogUtil.logMessage("FromClauseNo:"
					+ objCreateChngClaReqForm.getClauseNo());
			objClauseVO.setFromClauseSeqNo(objCreateChngClaReqForm
					.getHdnClauseSeqNo());
			LogUtil.logMessage("FromClauseSeqNo:"
					+ objCreateChngClaReqForm.getHdnClauseSeqNo());
			objClauseVO.setFromClauseVerNo(objCreateChngClaReqForm
					.getVersionNo()); //Modified for CR_80 by RR68151
			LogUtil.logMessage("FromClauseVerNo:"
					+ objCreateChngClaReqForm.getHdnVersionNo());
			objClauseVO.setToParentClaSeqNo(objCreateChngClaReqForm
					.getParentClauseSeqNo());
			LogUtil.logMessage("ToParentClaSeqNo:"
					+ objCreateChngClaReqForm.getParentClauseSeqNo());
			objClauseVO.setToParentClaDesc(objCreateChngClaReqForm
					.getToParentClaDesc());
			LogUtil.logMessage("ToParentClaDesc:"
					+ objCreateChngClaReqForm.getToParentClaDesc());
			objClauseVO.setToParentClauseNo(objCreateChngClaReqForm
					.getToParentClauseNo());
			LogUtil.logMessage("ToParentClauseNo:"
					+ objCreateChngClaReqForm.getToParentClauseNo());
			objClauseVO.setToClaVerClauseNo(objCreateChngClaReqForm
					.getToClaVerClauseNo());
			LogUtil.logMessage("ToClaVerClauseNo:"
					+ objCreateChngClaReqForm.getToClaVerClauseNo());
			objClauseVO.setToClaVerDesc(objCreateChngClaReqForm
					.getToClaVerDesc());
			LogUtil.logMessage("ToClaVerDesc:"
					+ objCreateChngClaReqForm.getToClaVerDesc());
			objClauseVO.setToClaVerSeqNo(objCreateChngClaReqForm
					.getToClaVerSeqNo());
			LogUtil.logMessage("ToClaVerSeqNo:"
					+ objCreateChngClaReqForm.getToClaVerSeqNo());
			objClauseVO.setToClauseNo(objCreateChngClaReqForm.getToClauseNo());
			LogUtil.logMessage("ToClauseNo:"
					+ objCreateChngClaReqForm.getToClauseNo());
			
			if (objCreateChngClaReqForm.getChangeTypeSeqNO() == ApplicationConstants.CHANGE_TYPE_DESC_DEFAULT_VERSION	
					//Modified for CR_80 CR Form Enhancements III on 25-Nov-09 by RR68151
					|| objCreateChngClaReqForm.getChangeTypeSeqNO() == ApplicationConstants.CHANGE_TYPE_DESC_DELETE_CLAUSE_VERSION) {
				objClauseVO.setToClauseDesc(objCreateChngClaReqForm
						.getToClaVerDesc());
			} else {
				objClauseVO.setToClauseDesc(strClauseDesc);
			}
			
			LogUtil.logMessage("ToClauseDesc:" + strClauseDesc);
			objClauseVO.setTableDataVO(arlTableList);
			/*Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151
			objClauseVO.setToDefaultFlag(objCreateChngClaReqForm
					.getToDefaultFlag());
			LogUtil.logMessage("ToDefaultFlag:"
					+ objCreateChngClaReqForm.getToDefaultFlag());*/
			objClauseVO.setToRefEDLNo(objCreateChngClaReqForm.getToRefEDLNo());
			objClauseVO.setToEdlNo(objCreateChngClaReqForm.getToEdlNo());
			objClauseVO.setPartOfClaSeqNo(objCreateChngClaReqForm
					.getPartOfclaSeqNo());
			objClauseVO.setPartOfCode(objCreateChngClaReqForm.getPartOfCode());
			
			objClauseVO
			.setPartOfSeqNo(objCreateChngClaReqForm.getPartOfSeqNo());
			
			objClauseVO
			.setTodwONumber(objCreateChngClaReqForm.getTodwONumber());
			LogUtil.logMessage("TodwONumber:"
					+ objCreateChngClaReqForm.getTodwONumber());
			objClauseVO.setToPartNumber(objCreateChngClaReqForm
					.getToPartNumber());
			LogUtil.logMessage("ToPartNumber:"
					+ objCreateChngClaReqForm.getToPartNumber());
			objClauseVO.setTopriceBookNumber(objCreateChngClaReqForm
					.getTopriceBookNumber());
			LogUtil.logMessage("TopriceBookNumber:"
					+ objCreateChngClaReqForm.getTopriceBookNumber());
			
			objClauseVO.setToComments(objCreateChngClaReqForm.getToComments());
			LogUtil.logMessage("ToComments:"
					+ objCreateChngClaReqForm.getToComments());
			objClauseVO.setReason(objCreateChngClaReqForm.getReason());
			
			objClauseVO.setChangeTypeSeqNO(objCreateChngClaReqForm
					.getChangeTypeSeqNO());
			
			objClauseVO.setUserID(strUserID);
			objRequestModelVO.setRequestID(intReqID);
			
			LogUtil.logMessage("HdnModelSeqNo:"
					+ objCreateChngClaReqForm.getHdnModelSeqNo());
			LogUtil.logMessage("HdnSectionSeqNo:"
					+ objCreateChngClaReqForm.getHdnSectionSeqNo());
			LogUtil.logMessage("HdnSubSectionSeqNo:"
					+ objCreateChngClaReqForm.getHdnSubSectionSeqNo());
			
			/*
			 * Change for Model apply flag
			 */
			if (objCreateChngClaReqForm.isApplyModelFlag()) {
				
				objRequestModelVO.setApplyModelFlag(true);
			} else {
				objRequestModelVO.setApplyModelFlag(false);
			}
			
			objRequestModelVO.setModelSeqNo(objCreateChngClaReqForm
					.getHdnModelSeqNo());
			objRequestModelVO.setSectionSeqNo(objCreateChngClaReqForm
					.getHdnSectionSeqNo());
			objRequestModelVO.setSubSectionSeqNo(objCreateChngClaReqForm
					.getHdnSubSectionSeqNo());
			
			objRequestModelVO.setAdminComments(objCreateChngClaReqForm
					.getHdnAdminComments());
			objRequestModelVO.setRequestDesc(objCreateChngClaReqForm
					.getRequestDesc());
			
			objRequestModelVO.setReqClauseVO(objClauseVO);
			
			objRequestVO.setRequestID(intReqID);
			objRequestVO.setUserID(objLoginVo.getUserID());
			ChangeRequestBI objChangeRequestBO = ServiceFactory
			.getChangeRequestBO();
			arlRequestList = objChangeRequestBO
			.fetchRequestDetails(objRequestVO);
			/* Removed for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151
			///To set Not Valid and InProgress in same fashion
			RequestVO objReqVO = new RequestVO();
			ArrayList objjRequest = new ArrayList();
			
			for(int k=0;k<arlRequestList.size();k++){
				
				objReqVO=(RequestVO)arlRequestList.get(k);
			if(objReqVO.getStatusTypeSeqNo() == 7){
				
				objReqVO.setStatusTypeSeqNo(1);
				
			}
			objjRequest.add(objReqVO);
			
			}
			
			
			if (objjRequest.size() != 0) {*/
				objCreateChngClaReqForm.setRequestList(arlRequestList);
			//}
			intSuccess = objChangeRequestBO
			.insertClaReqDetails(objRequestModelVO);
			
			if (intSuccess == 0) {
				objCreateChngClaReqForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				objCreateChngClaReqForm.setToComponentSeqNo("");
			} else {
				objCreateChngClaReqForm.setMessageID("" + intSuccess);
				objCreateChngClaReqForm.setClauseNo("");
				objCreateChngClaReqForm.setComments("");
				objCreateChngClaReqForm.setToClauseNo("");
				objCreateChngClaReqForm.setToClauseDesc("");
				objCreateChngClaReqForm.setStandardEquipmentSeqNo(-1);
				objCreateChngClaReqForm.setToParentClauseNo("");
				objCreateChngClaReqForm.setToClaVerClauseNo("");
				objCreateChngClaReqForm.setToComments("");
				objCreateChngClaReqForm.setToComponentSeqNo("");
				objCreateChngClaReqForm.setToClaVerDesc("");
				objCreateChngClaReqForm.setToParentClaDesc("");
			}
			
		}
		
		catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		//Added to include save function from alert
		actionRedirect = new ActionForward(
				"searchRequestComponentGroup.do?method=fetchComponentGroupDetails"
				+ "&reqID=" + intReqID, true /* REDIRECT */
		);
		
		if (objCreateChngClaReqForm.getAlertFlag1() != null
				&& objCreateChngClaReqForm.getAlertFlag1().equals("Y")
				&& strForwardKey.equalsIgnoreCase(ApplicationConstants.SUCCESS)) {
			
			return actionRedirect;
			
		}
		
		if (strForwardKey.equalsIgnoreCase(ApplicationConstants.SUCCESS))
			return initLoad(objActionMapping, objActionForm,
					objHttpServletRequest, objHttpServletResponse);
		else
			return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***************************************************************************
	 * This method is used to select the Parent Clause for adding new clause.
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
	
	public ActionForward selectParentClause(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		LogUtil
		.logMessage("Inside the CreateChangeReqClauseAction:selectParentClause");
		ArrayList arlParentList = new ArrayList();
		String strForwardKey = ApplicationConstants.SUCCESS;
		String strUserID = "";
		int intModelSeqNo = 0;
		int intSectionSeqNo = 0;
		int intSubSectionSeqNo = 0;
		String strSelectedSection = "";
		String strSelectedModel = "";
		String strSelectedSubSection = "";
		//Added for LSDB_CR_46
		String strSpecType = "";
		
		/*
		 * ModelSeqNo,SectionSeqNo And SubSectionSeqNo are getting as a request
		 * parameters to generate the clause numbers for the selected subsection
		 * in parent clause pop-up screen. This action calls the ViewSpecByModel
		 * DAO in order to reuse the clause generation logic at Model Level.
		 *  
		 */
		
		if (objHttpServletRequest
				.getParameter(ApplicationConstants.SELECTED_MODEL_NAME) != null) {
			strSelectedModel = objHttpServletRequest
			.getParameter(ApplicationConstants.SELECTED_MODEL_NAME);
		}
		
		if (objHttpServletRequest
				.getParameter(ApplicationConstants.SELECTED_SECTION_NAME) != null) {
			strSelectedSection = objHttpServletRequest
			.getParameter(ApplicationConstants.SELECTED_SECTION_NAME);
		}
		
		if (objHttpServletRequest
				.getParameter(ApplicationConstants.SELECTED_SUB_SECTION_NAME) != null) {
			strSelectedSubSection = objHttpServletRequest
			.getParameter(ApplicationConstants.SELECTED_SUB_SECTION_NAME);
		}
		
		if (objHttpServletRequest
				.getParameter(ApplicationConstants.SELECTED_SUB_SECTION_ID) != null) {
			intSubSectionSeqNo = Integer
			.parseInt(objHttpServletRequest
					.getParameter(ApplicationConstants.SELECTED_SUB_SECTION_ID));
		}
		if (objHttpServletRequest.getParameter("selectedSectionID") != null) {
			intSectionSeqNo = Integer.parseInt(objHttpServletRequest
					.getParameter("selectedSectionID"));
		}
		if (objHttpServletRequest.getParameter("selectedModelID") != null) {
			intModelSeqNo = Integer.parseInt(objHttpServletRequest
					.getParameter("selectedModelID"));
		}
		
		SubSectionVO objSubSectionVO = new SubSectionVO();
		try {
			CreateChangeClauseRequestForm objCreateChngClaReqForm = (CreateChangeClauseRequestForm) objActionForm;
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			
			objCreateChngClaReqForm.setHdnModelSeqNo(intModelSeqNo);
			objCreateChngClaReqForm.setHdnSubSectionSeqNo(intSubSectionSeqNo);
			objCreateChngClaReqForm.setSectionSeqNo(intSectionSeqNo);
			objSubSectionVO.setModelSeqNo(intModelSeqNo);
			objSubSectionVO.setUserID(strUserID);
			objSubSectionVO.setSecSeqNo(intSectionSeqNo);
			objSubSectionVO.setSubSecSeqNo(intSubSectionSeqNo);
			ViewSpecByModelBI objViewSpecByModelBO = ServiceFactory
			.getViewSpecByModelBO();
			arlParentList = (objViewSpecByModelBO
					.viewMasterSpecByModel(objSubSectionVO));
			
			if (arlParentList.size() != 0) {
				strForwardKey = ApplicationConstants.CRForm_Select_Parent_Clause;
				
				objCreateChngClaReqForm.setComponentVO(arlParentList);
				objCreateChngClaReqForm.setModelName(strSelectedModel);
				objCreateChngClaReqForm.setSectionName(strSelectedSection);
				objCreateChngClaReqForm
				.setSubSectionName(strSelectedSubSection);
				return objActionMapping.findForward(strForwardKey);
			} else {
				strForwardKey = ApplicationConstants.CRForm_Select_Parent_Clause;
				
				objCreateChngClaReqForm.setModelName(strSelectedModel);
				objCreateChngClaReqForm.setSectionName(strSelectedSection);
				objCreateChngClaReqForm
				.setSubSectionName(strSelectedSubSection);
				objCreateChngClaReqForm
				.setMessageID(ApplicationConstants.NO_RECORDS_FOUND);
				return objActionMapping.findForward(strForwardKey);
				
			}
			
		}
		
		catch (Exception objExp) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
			
		}
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***************************************************************************
	 * This method is used to select the Clause Version for Change default
	 * version option.
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
	
	public ActionForward selectClauseVersions(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		LogUtil
		.logMessage("Inside the CreateChangeReqClauseAction:selectClauseVersion");
		ArrayList arlParentList = new ArrayList();
		String strForwardKey = ApplicationConstants.CRForm_Select_Clause_Version;
		String strUserID = "";
		int intModelSeqNo = 0;
		int intSectionSeqNo = 0;
		int intSubSectionSeqNo = 0;
		int intClauseSeqNo = 0;
		String strSelectedSection = "";
		String strSelectedModel = "";
		String strSelectedSubSection = "";
		//Added for LSDB_CR_46
		String strSpecType = "";
		//Added for CR_80 CR Form Enhancements III on 24-Nov-09 by RR68151
		String strchngToFlag = "";
		ArrayList arlAllVersion = new ArrayList();
		ArrayList arlAllClauseList = new ArrayList();
		
		/*
		 * ModelSeqNo,SectionSeqNo And SubSectionSeqNo are getting as a request
		 * parameters to generate the clause numbers for the selected subsection
		 * in parent clause pop-up screen. This action calls the ViewSpecByModel
		 * DAO in order to reuse the clause generation logic at Model Level.
		 *  
		 */
		
		if (objHttpServletRequest
				.getParameter(ApplicationConstants.SELECTED_MODEL_NAME) != null) {
			strSelectedModel = objHttpServletRequest
			.getParameter(ApplicationConstants.SELECTED_MODEL_NAME);
		}
		
		if (objHttpServletRequest.getParameter("selectedClauseID") != null) {
			intClauseSeqNo = Integer.parseInt(objHttpServletRequest
					.getParameter("selectedClauseID"));
		}
		
		if (objHttpServletRequest
				.getParameter(ApplicationConstants.SELECTED_SECTION_NAME) != null) {
			strSelectedSection = objHttpServletRequest
			.getParameter(ApplicationConstants.SELECTED_SECTION_NAME);
		}
		
		if (objHttpServletRequest
				.getParameter(ApplicationConstants.SELECTED_SUB_SECTION_NAME) != null) {
			strSelectedSubSection = objHttpServletRequest
			.getParameter(ApplicationConstants.SELECTED_SUB_SECTION_NAME);
		}
		
		if (objHttpServletRequest
				.getParameter(ApplicationConstants.SELECTED_SUB_SECTION_ID) != null) {
			intSubSectionSeqNo = Integer
			.parseInt(objHttpServletRequest
					.getParameter(ApplicationConstants.SELECTED_SUB_SECTION_ID));
		}
		if (objHttpServletRequest.getParameter("selectedSectionID") != null) {
			intSectionSeqNo = Integer.parseInt(objHttpServletRequest
					.getParameter("selectedSectionID"));
		}
		if (objHttpServletRequest.getParameter("selectedModelID") != null) {
			intModelSeqNo = Integer.parseInt(objHttpServletRequest
					.getParameter("selectedModelID"));
		}
		//Added for CR_80 CR Form Enhancements III on 24-Nov-09 by RR68151
		if (objHttpServletRequest.getParameter("chngToFlag") != null) {
				strchngToFlag = objHttpServletRequest
					.getParameter("chngToFlag");
		}
		
		
		SubSectionVO objSubSectionVO = new SubSectionVO();
		try {
			CreateChangeClauseRequestForm objCreateChngClaReqForm = (CreateChangeClauseRequestForm) objActionForm;
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			
			objCreateChngClaReqForm.setHdnModelSeqNo(intModelSeqNo);
			objCreateChngClaReqForm.setHdnSubSectionSeqNo(intSubSectionSeqNo);
			objCreateChngClaReqForm.setSectionSeqNo(intSectionSeqNo);
			objCreateChngClaReqForm.setModelName(strSelectedModel);
			objCreateChngClaReqForm.setSectionName(strSelectedSection);
			objCreateChngClaReqForm.setSubSectionName(strSelectedSubSection);
			objCreateChngClaReqForm.setHdnClauseSeqNo(intClauseSeqNo);
			//Added for CR_80 CR Form Enhancements III on 24-Nov-09 by RR68151
			objCreateChngClaReqForm.setChngToFlag(strchngToFlag);
			objCreateChngClaReqForm.setHdnSectionSeqNo(intSectionSeqNo);
			ClauseVO objClauseVO = new ClauseVO();
			LogUtil.logMessage("ModelSeqNo in FetchClauseVersion**"
					+ objCreateChngClaReqForm.getHdnModelSeqNo());
			objClauseVO.setModelSeqNo(objCreateChngClaReqForm
					.getHdnModelSeqNo());
			LogUtil.logMessage("SubSectionSeqNo in FetchClauseVersion**"
					+ objCreateChngClaReqForm.getHdnSubSectionSeqNo());
			objClauseVO.setSubSectionSeqNo(objCreateChngClaReqForm
					.getHdnSubSectionSeqNo());
			//Added for CR_80 CR Form Enhancements III on 24-Nov-09 by RR68151
			objClauseVO.setSectionSeqNo(objCreateChngClaReqForm
					.getHdnSectionSeqNo());
			LogUtil.logMessage("ClauseSeqNo in FetchClauseVersion**"
					+ objCreateChngClaReqForm.getHdnClauseSeqNo());
			
			objClauseVO.setClauseSeqNo(objCreateChngClaReqForm
					.getHdnClauseSeqNo());
			
			objClauseVO.setUserID(objLoginVo.getUserID());
			
			ModelSelectClauseRevBI objModelSelectClauseRevBO = ServiceFactory
			.getModelSelectClauseRevBO();
			arlAllClauseList = objModelSelectClauseRevBO
			.fetchClauseVersions(objClauseVO);
			
			if (arlAllClauseList != null && arlAllClauseList.size() > 0) {
				
				arlAllVersion = (ArrayList) arlAllClauseList.get(0);
				//Added for CR_80 CR Form Enhancements III on 24-Nov-09 by RR68151
				objCreateChngClaReqForm.setNoOfClaVersion(arlAllVersion.size());
				LogUtil.logMessage("Size of ArrayList:" + arlAllVersion.size());
				objCreateChngClaReqForm.setClauseVersions(arlAllVersion);
				strForwardKey = ApplicationConstants.CRForm_Select_Clause_Version;
				
			}
			
		}
		
		catch (Exception objExp) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
			
		}
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***************************************************************************
	 * This method is deleting the request
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
	
	public ActionForward resetRequest(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Entering CreateChangeReqClauseAction.resetRequest");
		String strForwardKey = null;
		CreateChangeClauseRequestForm objCreateChngClaReqForm = (CreateChangeClauseRequestForm) objActionForm;
		int intReqID = 0;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		int intSuccess = 0;
		try {
			
			RequestVO objRequestVO = new RequestVO();
			ModelVo objModelVo = new ModelVo();
			//Added For CR_80 CR Form Enhancements III by RR68151
			ClauseVO objClauseVO = new ClauseVO();
			
			ChangeRequestBI objChangeRequestBI = ServiceFactory
			.getChangeRequestBO();
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if (objCreateChngClaReqForm.getHdnReqID() != null
					&& !"".equalsIgnoreCase(objCreateChngClaReqForm
							.getHdnReqID())) {
				
				intReqID = Integer.parseInt(objCreateChngClaReqForm
						.getHdnReqID().trim());
				
			}
			LogUtil.logMessage("intReqID :" + intReqID);
			
			objRequestVO.setUserID(objLoginVo.getUserID());
			objRequestVO.setRequestID(intReqID);
			
			intSuccess = objChangeRequestBI.resetRequest(objRequestVO);
			
			if (intSuccess == 0) {
				objCreateChngClaReqForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				objCreateChngClaReqForm.setClauseNo("");
				objCreateChngClaReqForm.setComments("");
				objCreateChngClaReqForm.setToClauseNo("");
				objCreateChngClaReqForm.setToClauseDesc("");
				objCreateChngClaReqForm.setStandardEquipmentSeqNo(-1);
				objCreateChngClaReqForm.setToParentClauseNo("");
				objCreateChngClaReqForm.setToClaVerClauseNo("");
				objCreateChngClaReqForm.setToComments("");
				objCreateChngClaReqForm.setToComponentSeqNo("");
				objCreateChngClaReqForm.setToClaVerDesc("");
				objCreateChngClaReqForm.setToParentClaDesc("");
				//Added for CR_80 CR Form Enhancements III on 25-Nov-09 by RR68151
				objCreateChngClaReqForm.setHdnCompGrpSeqNo(0);
				objCreateChngClaReqForm.setHdnCompSeqNo(0);
				objClauseVO.setCoreClausesFlag(ApplicationConstants.NO);
				//Added for CR_80 CR Form Enhancements III on 25-Nov-09 by RR68151 - Ends Here
			} else {
				objCreateChngClaReqForm.setMessageID("" + intSuccess);
			}
			
			strForwardKey = ApplicationConstants.SUCCESS;
		} catch (Exception objExp) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		
		if (strForwardKey.equalsIgnoreCase(ApplicationConstants.SUCCESS))
			return initLoad(objActionMapping, objActionForm,
					objHttpServletRequest, objHttpServletResponse);
		else
			return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***************************************************************************
	 * This method is used to update the request status
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
	
	public ActionForward saveRequestStatus(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil
		.logMessage("Entering CreateChangeReqClauseAction.saveRequestStatus");
		String strForwardKey = null;
		CreateChangeClauseRequestForm objCreateChngClaReqForm = (CreateChangeClauseRequestForm) objActionForm;
		int intReqID = 0;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		int intSuccess = 0;
		int intStatusSeqNo = 0;
		try {
			
			RequestVO objRequestVO = new RequestVO();
			ModelVo objModelVo = new ModelVo();
			
			ChangeRequestBI objChangeRequestBI = ServiceFactory
			.getChangeRequestBO();
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if (objCreateChngClaReqForm.getHdnReqID() != null
					&& !"".equalsIgnoreCase(objCreateChngClaReqForm
							.getHdnReqID())) {
				
				intReqID = Integer.parseInt(objCreateChngClaReqForm
						.getHdnReqID().trim());
				
			}
			LogUtil.logMessage("intReqID :" + intReqID);
			
			if ((String) objHttpServletRequest.getParameter("statusid") != null
					&& !"".equalsIgnoreCase((String) objHttpServletRequest
							.getParameter("statusid"))) {
				intStatusSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("statusid"));
			}
			objRequestVO.setStatusTypeSeqNo(intStatusSeqNo);
			objRequestVO.setAdminComments(objCreateChngClaReqForm
					.getHdnAdminComments());
			objRequestVO.setUserID(objLoginVo.getUserID());
			objRequestVO.setRequestID(intReqID);
			
			intSuccess = objChangeRequestBI.saveRequestStatus(objRequestVO);
			
			if (intSuccess == 0) {
				objCreateChngClaReqForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objCreateChngClaReqForm.setMessageID("" + intSuccess);
			}
			
			strForwardKey = ApplicationConstants.SUCCESS;
		} catch (Exception objExp) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		
		if (strForwardKey.equalsIgnoreCase(ApplicationConstants.SUCCESS))
			return initLoad(objActionMapping, objActionForm,
					objHttpServletRequest, objHttpServletResponse);
		else
			return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***************************************************************************
	 * This method is used to fetch the default version of the clause for
	 * selected Lead Component.
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
	
	public ActionForward fetchLeadCompDefaultVersion(
			ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil
		.logMessage("Enters into CreateChangeReqClauseAction:fetchLeadCompDefaultVersion");
		String strForwardKey = ApplicationConstants.SUCCESS;
		CreateChangeClauseRequestForm objCreateChngClaReqForm = (CreateChangeClauseRequestForm) objActionForm;
		ModelVo objModelVo = new ModelVo();
		ArrayList arlRequestList = new ArrayList();
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlSubSectionList = new ArrayList();
		LoginVO objLoginVo = null;
		ArrayList arlStandardEquipList = new ArrayList();
		RequestVO objRequestVO = new RequestVO();
		SectionVO objSectionVO = new SectionVO();
		ArrayList arlAllClauseList = new ArrayList();
		ArrayList arlClauseList = new ArrayList();
		String strReqUserID = null;
		String strUser = null;
		int intReqID = 0;
		int intModelSeqNo = 0;
		int intSecSeqNo = 0;
		int intSubSecSeqNo = 0;
		RequestModelVO objRequestModelVO = null;
		// Added For CR_80 to add Specification Type Dropdown
		int intSpecTypeNo = 0;
		// Added For CR_80 to add Specification Type Dropdown - Ends here	
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LogUtil.logMessage(objSession);
			
			if (objSession != null) {
				objLoginVo = (LoginVO) objSession
				.getAttribute(ApplicationConstants.USER_IN_SESSION);
				
			}
			
			objModelVo.setUserID(objLoginVo.getUserID());
			
			if (objCreateChngClaReqForm.getHdnReqID() != null
					&& !"".equalsIgnoreCase(objCreateChngClaReqForm
							.getHdnReqID())) {
				
				intReqID = Integer.parseInt(objCreateChngClaReqForm
						.getHdnReqID().trim());
				
			}
			
			/** This part of code load the status details of the Request * */
			
			objRequestVO.setRequestID(intReqID);
			objRequestVO.setUserID(objLoginVo.getUserID());
			ChangeRequestBI objChangeRequestBO = ServiceFactory
			.getChangeRequestBO();
			arlRequestList = objChangeRequestBO
			.fetchRequestDetails(objRequestVO);
			/* Removed for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151
			///To set Not Valid and InProgress in same fashion
			RequestVO objReqVO = new RequestVO();
			ArrayList objjRequest = new ArrayList();
			
			for(int k=0;k<arlRequestList.size();k++){
				
				objReqVO=(RequestVO)arlRequestList.get(k);
			if(objReqVO.getStatusTypeSeqNo() == 7){
				
				objReqVO.setStatusTypeSeqNo(1);
				
			}
			objjRequest.add(objReqVO);
			
			}
			
			
			if (objjRequest.size() != 0) {*/
				objCreateChngClaReqForm.setRequestList(arlRequestList);
			//}
			
			/**
			 * To Load the Request Model Details,like model ,section and
			 * Subsection seqno's.
			 */
			
			objRequestModelVO = objChangeRequestBO
			.fetchReqModelDetails(objRequestVO);
			
			/**
			 * This logic is for getting the model seq no and pass it to
			 * SectionVo to load the Section's *
			 */
			
			LogUtil.logMessage("objRequestModelVO :" + objRequestModelVO);
			
			if (objRequestModelVO != null) {
				// Added For CR_80 to add Specification Type Dropdown
				intSpecTypeNo = objRequestModelVO.getSpecTypeNo();
				// Added For CR_80 to add Specification Type Dropdown - Ends here				
				intModelSeqNo = objRequestModelVO.getModelSeqNo();
				intSecSeqNo = objRequestModelVO.getSectionSeqNo();
				intSubSecSeqNo = objRequestModelVO.getSubSectionSeqNo();
			}
			
			LogUtil.logMessage("intModelSeqNo :" + intModelSeqNo);
			LogUtil.logMessage("intSecSeqNo :" + intSecSeqNo);
			LogUtil.logMessage("intSubSecSeqNo :" + intSubSecSeqNo);
			
			/*******************************************************************
			 * This logic is for Enabling and disabling the preview button in
			 * JSP Preview Button should be enabled only if the flag value is
			 * 'Y'
			 ******************************************************************/
			// Modified For CR_80 by adding spectypeno in if loop	
			if (intSpecTypeNo == 0 && intModelSeqNo == 0 && intSecSeqNo == 0 && intSubSecSeqNo == 0) {
				
				objCreateChngClaReqForm.setReqModelSaved("N");
				
			} else {
				
				objCreateChngClaReqForm.setReqModelSaved("Y");
				
				/*
				 * Change for Model apply flag
				 */
				if (objRequestModelVO.isApplyModelFlag()) {
					
					objCreateChngClaReqForm.setApplyModelFlag(true);
				} else {
					objCreateChngClaReqForm.setApplyModelFlag(false);
				}
			}
			
			LogUtil.logMessage("ReqModelSaved:"
					+ objCreateChngClaReqForm.getReqModelSaved());
			
			objRequestModelVO = null;
			/** Reseting the RequestModelVo to null * */
			
			objRequestModelVO = objChangeRequestBO
			.fetchReqCompGrpDetails(objRequestVO);
			
			if (objRequestModelVO != null) {
				objCreateChngClaReqForm.setCompGrpNameVO(objRequestModelVO);
				
			}
			
			/** Button Level Access* */
			/**
			 * This logic is for enabling and disabling of Approve,Reject,Onhold
			 * and Complete Buttons's *
			 */
			
			if (ApplicationConstants.ADMN.equals(objLoginVo.getRoleID())
					|| ApplicationConstants.MSE.equals(objLoginVo.getRoleID()))
				objCreateChngClaReqForm.setRoleID(ApplicationConstants.ADMN);
			
			else
				objCreateChngClaReqForm.setRoleID("OTHR");//ASM,SE
			
			/** This is for getting request user ID * */
			
			if (arlRequestList != null && arlRequestList.size() > 0) {
				
				for (int i = 0; i < arlRequestList.size(); i++) {
					RequestVO objReq = (RequestVO) arlRequestList.get(i);
					strReqUserID = objReq.getReqUserID();
					objCreateChngClaReqForm.setReason(objReq.getReason());
					objCreateChngClaReqForm.setAdminComments(objReq
							.getAdminComments());
				}
			}
			
			/*******************************************************************
			 * This part is for checking logged in user ID and Request Created
			 * User ID If equal then user have provision to modify request, else
			 * only preview available
			 ******************************************************************/
			
			strUser = objLoginVo.getUserID();
			if (strReqUserID != null && strUser != null) {
				
				if (strReqUserID.trim().equals(strUser.trim())) {
					objCreateChngClaReqForm.setUserOwnRequest("Y");
				} else {
					objCreateChngClaReqForm.setUserOwnRequest("N");
				}
			}

			// Added For CR_80 to add Specification Type Dropdown
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objCreateChngClaReqForm.setSpecTypeList(arlSpec);
			}	

			objModelVo.setSpecTypeSeqNo(intSpecTypeNo);		
			// Added For CR_80 to add Specification Type Dropdown - Ends here	
			ModelBI objModelBO = ServiceFactory.getModelBO();
			ArrayList arlModelList = objModelBO.fetchModels(objModelVo);
			if (arlModelList.size() != 0) {
				objCreateChngClaReqForm.setModelList(arlModelList);
			}
			StandardEquipVO objStandardEquipVO = new StandardEquipVO();
			objStandardEquipVO.setUserID(objLoginVo.getUserID());
			StandardEquipBI objStandardEquipBO = ServiceFactory
			.getStandardEquipBO();
			arlStandardEquipList = objStandardEquipBO
			.fetchStandardEquipment(objStandardEquipVO);
			
			if (arlStandardEquipList.size() != 0) {
				objCreateChngClaReqForm
				.setStdEquipmentList(arlStandardEquipList);
			}
			
			objSectionVO.setModelSeqNo(objCreateChngClaReqForm
					.getHdnModelSeqNo());
			objSectionVO.setUserID(objLoginVo.getUserID());
			
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			arlSectionList = objSectionBO.fetchSections(objSectionVO);
			if (arlSectionList.size() != 0) {
				objCreateChngClaReqForm.setSectionList(arlSectionList);
			}
			
			SubSectionVO objSubSectionVO = new SubSectionVO();
			objSubSectionVO.setModelSeqNo(objCreateChngClaReqForm
					.getHdnModelSeqNo());
			objSubSectionVO.setSecSeqNo(objCreateChngClaReqForm
					.getHdnSectionSeqNo());
			objSubSectionVO.setUserID(objLoginVo.getUserID());
			
			ModelSubSectionBI objModelSubSecBO = ServiceFactory
			.getSubSecMaintBO();
			arlSubSectionList = objModelSubSecBO
			.fetchSubSections(objSubSectionVO);
			
			if (arlSubSectionList.size() != 0) {
				objCreateChngClaReqForm.setSubSectionList(arlSubSectionList);
			}
			
			ClauseVO objClauseVO = new ClauseVO();
			LogUtil.logMessage("ModelSeqNo in fetchLeadCompDefaultVersion**"
					+ objCreateChngClaReqForm.getHdnModelSeqNo());
			objClauseVO.setModelSeqNo(objCreateChngClaReqForm
					.getHdnModelSeqNo());
			LogUtil
			.logMessage("SubSectionSeqNo in fetchLeadCompDefaultVersion**"
					+ objCreateChngClaReqForm.getHdnSubSectionSeqNo());
			objClauseVO.setSubSectionSeqNo(objCreateChngClaReqForm
					.getHdnSubSectionSeqNo());
			
			LogUtil
			.logMessage("hdnCompGrpSeqNo No in fetchLeadCompDefaultVersion**"
					+ objCreateChngClaReqForm.getHdnCompGrpSeqNo());
			objClauseVO.setLeadCompGrpSeqNo(objCreateChngClaReqForm
					.getHdnCompGrpSeqNo());
			LogUtil
			.logMessage("hdnComponentSeqNo No in fetchLeadCompDefaultVersion**"
					+ objCreateChngClaReqForm.getHdnCompSeqNo());
			objClauseVO.setComponentSeqNo(String
					.valueOf(objCreateChngClaReqForm.getHdnCompSeqNo()));
			objClauseVO.setDefaultFlag(ApplicationConstants.YES);
			objClauseVO.setUserID(objLoginVo.getUserID());
			
			ModelSelectClauseRevBI objModelSelectClauseRevBO = ServiceFactory
			.getModelSelectClauseRevBO();
			arlAllClauseList = objModelSelectClauseRevBO
			.fetchClauseForLeadComp(objClauseVO);
			
			LogUtil.logMessage("ArrayList Size******:"
					+ arlAllClauseList.size());
			ArrayList arlAllVersion = new ArrayList();
			arlAllVersion = (ArrayList) arlAllClauseList.get(0);
			if (arlAllVersion.size() == 0) {
				objCreateChngClaReqForm.setMessageID("1");
			}
			/*******************************************************************
			 * Here the RequestClauseVo is populated by getting the values from
			 * the clauseVo to populate the same details for CreateTo part also.
			 ******************************************************************/
			LogUtil.logMessage("ArrayList Size******:" + arlAllVersion.size());
			if (arlAllClauseList != null && arlAllClauseList.size() > 0) {
				
				LogUtil.logMessage("Inside******:");
				arlClauseList = (ArrayList) arlAllClauseList.get(0);
				if (arlClauseList.size() > 0
						&& objCreateChngClaReqForm.getChangeTypeSeqNO() != ApplicationConstants.CHANGE_TYPE_DESC_DEFAULT_VERSION
						&& objCreateChngClaReqForm.getChangeTypeSeqNO() != ApplicationConstants.CHANGE_TYPE_DESC_DELETE_CLAUSE_VERSION) {
					objCreateChngClaReqForm.setClauseList(arlClauseList);
					
					LogUtil.logMessage("Inside******1:");
					ClauseVO objClauseVo = new ClauseVO();
					ReqClauseVO objReqClauseVO = new ReqClauseVO();
					ArrayList arlReqList = new ArrayList();
					int[] test = new int[4];
					objClauseVo = (ClauseVO) arlClauseList.get(0);
					objReqClauseVO.setToParentClauseNo("");
					objReqClauseVO.setToParentClaDesc("");
					objReqClauseVO.setToParentClauseNo("");
					objReqClauseVO.setToClaVerClauseNo("");
					objReqClauseVO.setToClaVerDesc("");
					objReqClauseVO.setToClauseDesc(objClauseVo
							.getClauseDescForTextArea());
					LogUtil.logMessage("ToClauseDesc:"
							+ objReqClauseVO.getToClauseDesc());
					objReqClauseVO.setTableData(objClauseVo
							.getTableArrayData1());
					objReqClauseVO.setClaComp(objClauseVo.getCompName());
					objReqClauseVO.setToComponentSeqNo(objClauseVo
							.getComponentSeqNo());
					/*Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151
					objReqClauseVO.setToDefaultFlag(objClauseVo
							.getDefaultFlag());*/
					objReqClauseVO.setToRefEDLNo(objClauseVo.getRefEDLNo());
					objReqClauseVO.setToEdlNo(objClauseVo.getEdlNo());
					objReqClauseVO.setPartOfClaNo(objClauseVo.getPartOfCode());
					objReqClauseVO.setPartOfClaSeqNo(objClauseVo
							.getReqpartOfClaSeqNo());
					objReqClauseVO.setPartOfSubSecSeqNo(objClauseVo
							.getPartOfSeqNo());
					objReqClauseVO.setTodwONumber(objClauseVo.getDwONumber());
					LogUtil.logMessage("TodwONumber:"
							+ objReqClauseVO.getTodwONumber());
					objReqClauseVO.setTopriceBookNumber(objClauseVo
							.getPriceBookNumber());
					objReqClauseVO.setToPartNumber(objClauseVo.getPartNumber());
					objReqClauseVO.setToStdEquipSeqNo(objClauseVo
							.getStandardEquipmentSeqNo());
					objReqClauseVO.setToComments(objClauseVo.getComments());
					arlReqList.add(objReqClauseVO);
					LogUtil.logMessage("Size of ReqClause List:"
							+ arlReqList.size());
					if (arlReqList != null && arlReqList.size() > 0) {
						LogUtil.logMessage("Size of ReqClause List:"
								+ arlReqList.size());
						objCreateChngClaReqForm.setReqClauseList(arlReqList);
					}
					LogUtil
					.logMessage("Size of ReqClause List:"
							+ objCreateChngClaReqForm
							.getReqClauseList().size());
					
				}
			}
			if (objCreateChngClaReqForm.getReqModelSaved() == "Y") {
				//Added For Specification Drop down CR_80
				if (intSpecTypeNo == 0)	{
					objCreateChngClaReqForm.setSpecTypeNo(-1);
				} else {
					objCreateChngClaReqForm.setSpecTypeNo(intSpecTypeNo);
				}
				//Added For Specification Drop down CR_80 - Ends here
				if (intModelSeqNo == 0) {
					objCreateChngClaReqForm.setModelSeqNo(-1);
				} else {
					objCreateChngClaReqForm.setModelSeqNo(intModelSeqNo);
				}
				
				if (intSecSeqNo == 0) {
					objCreateChngClaReqForm.setSectionSeqNo(-1);
				} else {
					objCreateChngClaReqForm.setSectionSeqNo(intSecSeqNo);
				}
				
				if (intSubSecSeqNo == 0) {
					objCreateChngClaReqForm.setSubSectionSeqNo(-1);
				} else {
					objCreateChngClaReqForm.setSubSectionSeqNo(intSubSecSeqNo);
				}
			}
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in CreateChangeReqClauseAction:fetchLeadCompDefaultVersion..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		
		return objActionMapping.findForward(strForwardKey);
	}
	
	// Added For CR_80 to add Specification Type Dropdown
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
	
	public ActionForward fetchModels(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Inside the CreateChangeReqClauseAction:fetchModels");
		String strForwardKey = ApplicationConstants.SUCCESS;

		CreateChangeClauseRequestForm objCreateChngClaReqForm = (CreateChangeClauseRequestForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		try {
			ArrayList arlStandardEquipList = new ArrayList();
			ArrayList arlRequestList = new ArrayList();
			ModelVo objModelVo = new ModelVo();
			RequestVO objRequestVO = new RequestVO();
			RequestModelVO objRequestModelVO = null;
			int intModelSeqNo = 0;
			int intSecSeqNo = 0;
			int intSubSecSeqNo = 0;
			int intReqID = 0;
			int intSpecTypeNo = 0;
			String strHdnSpecTypeNo = null;
			int intHdnSpecTypeNo = 0;
			
			//This is for comparing logged in user ID and Request Created User ID
			String strUser = null;
			String strReqUserID = null;
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
	
			strHdnSpecTypeNo = (String) objHttpServletRequest
			.getParameter("hdnSpecTypeNo");
			if (strHdnSpecTypeNo != null && !"".equals(strHdnSpecTypeNo)) {
				
				intHdnSpecTypeNo = Integer.parseInt(strHdnSpecTypeNo);
			}
			
			if (objCreateChngClaReqForm.getHdnReqID() != null
					&& !"".equalsIgnoreCase(objCreateChngClaReqForm
							.getHdnReqID())) {
				
				intReqID = Integer.parseInt(objCreateChngClaReqForm
						.getHdnReqID().trim());
				
			}
			/** This part of code load the status details of the Request * */
			
			objRequestVO.setRequestID(intReqID);
			objRequestVO.setUserID(objLoginVo.getUserID());
			ChangeRequestBI objChangeRequestBO = ServiceFactory
			.getChangeRequestBO();
			arlRequestList = objChangeRequestBO
			.fetchRequestDetails(objRequestVO);

			objCreateChngClaReqForm.setRequestList(arlRequestList);
			
			/**
			 * To Load the Request Model Details,like model ,section and
			 * Subsection seqno's.
			 */
			
			objRequestModelVO = objChangeRequestBO
			.fetchReqModelDetails(objRequestVO);
			
			/**
			 * This logic is for getting the model seq no and pass it to
			 * SectionVo to load the Section's *
			 */
			
			LogUtil.logMessage("objRequestModelVO :" + objRequestModelVO);
			
			if (objRequestModelVO != null) {
				intSpecTypeNo = objRequestModelVO.getSpecTypeNo();
				intModelSeqNo = objRequestModelVO.getModelSeqNo();
				intSecSeqNo = objRequestModelVO.getSectionSeqNo();
				intSubSecSeqNo = objRequestModelVO.getSubSectionSeqNo();
			}
			
			LogUtil.logMessage("intSpecTypeNo :" + intSpecTypeNo);
			LogUtil.logMessage("intModelSeqNo :" + intModelSeqNo);
			LogUtil.logMessage("intSecSeqNo :" + intSecSeqNo);
			LogUtil.logMessage("intSubSecSeqNo :" + intSubSecSeqNo);
			
			/*******************************************************************
			 * This logic is for Enabling and disabling the preview button in
			 * JSP Preview Button should be enabled only if the flag value is
			 * 'Y'
			 ******************************************************************/
			
			if (intSpecTypeNo == 0 && intModelSeqNo == 0 && intSecSeqNo == 0 && intSubSecSeqNo == 0) {
				
				objCreateChngClaReqForm.setReqModelSaved("N");
				
			} else {
				
				objCreateChngClaReqForm.setReqModelSaved("Y");
			}
			
			LogUtil.logMessage("ReqModelSaved:"
					+ objCreateChngClaReqForm.getReqModelSaved());
			
			objRequestModelVO = null;
			/** Reseting the RequestModelVo to null * */
			
			objRequestModelVO = objChangeRequestBO
			.fetchReqCompGrpDetails(objRequestVO);
			
			if (objRequestModelVO != null) {
				objCreateChngClaReqForm.setCompGrpNameVO(objRequestModelVO);
				
			}
			
			/** Button Level Access* */
			/**
			 * This logic is for enabling and disabling of Approve,Reject,Onhold
			 * and Complete Buttons's *
			 */
			
			if (ApplicationConstants.ADMN.equals(objLoginVo.getRoleID())
					|| ApplicationConstants.MSE.equals(objLoginVo.getRoleID()))
				objCreateChngClaReqForm.setRoleID(ApplicationConstants.ADMN);
			
			else
				objCreateChngClaReqForm.setRoleID("OTHR");//ASM,SE
			
			/** This is for getting request user ID * */
			
			if (arlRequestList != null && arlRequestList.size() > 0) {
				
				for (int i = 0; i < arlRequestList.size(); i++) {
					RequestVO objReq = (RequestVO) arlRequestList.get(i);
					strReqUserID = objReq.getReqUserID();
					objCreateChngClaReqForm.setReason(objReq.getReason());
					objCreateChngClaReqForm.setAdminComments(objReq
							.getAdminComments());
					
				}
			}
			
			/*******************************************************************
			 * This part is for checking logged in user ID and Request Created
			 * User ID If equal then user have provision to modify request, else
			 * only preview available
			 ******************************************************************/
			
			strUser = objLoginVo.getUserID();
			if (strReqUserID != null && strUser != null) {
				
				if (strReqUserID.trim().equals(strUser.trim())) {
					objCreateChngClaReqForm.setUserOwnRequest("Y");
				} else {
					objCreateChngClaReqForm.setUserOwnRequest("N");
				}
			}
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objCreateChngClaReqForm.setSpecTypeList(arlSpec);
			}
			
			objModelVo.setUserID(objLoginVo.getUserID());
			objModelVo.setSpecTypeSeqNo(intHdnSpecTypeNo);
			ModelBI objModelBO = ServiceFactory.getModelBO();
			if (objModelBO.fetchModels(objModelVo) != null) {
				ArrayList arlModelList = objModelBO.fetchModels(objModelVo);
				objCreateChngClaReqForm.setModelList(arlModelList);
			}
			
			StandardEquipVO objStandardEquipVO = new StandardEquipVO();
			objStandardEquipVO.setUserID(objLoginVo.getUserID());
			StandardEquipBI objStandardEquipBO = ServiceFactory
			.getStandardEquipBO();
			arlStandardEquipList = objStandardEquipBO
			.fetchStandardEquipment(objStandardEquipVO);
			
			if (arlStandardEquipList.size() != 0) {
				objCreateChngClaReqForm
				.setStdEquipmentList(arlStandardEquipList);
			}
			//Adding for CR_94
			{
			RequestVO objNewReqVO = new RequestVO();
			objNewReqVO.setStatusTypeSeqNo(2);
			objNewReqVO.setLastName("All");
			objNewReqVO.setRequestID(0);
			ChangeRequestBI objNewChangeRequestBI = ServiceFactory.getChangeRequestBO();
			ArrayList arlSearchList = new ArrayList();
			arlSearchList = objNewChangeRequestBI.fetchRequestDetails(objNewReqVO);
			objCreateChngClaReqForm.setSearchList(arlSearchList);
			}
			//Ends here CR_94
									
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in CreateChangeReqClauseAction:fetchModels");
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
	
	// Added For CR_80 to add Specification Type Dropdown - Ends here
	
}