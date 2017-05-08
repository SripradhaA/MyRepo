/*
 * Created on Jun 21, 2007
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
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.form.CRForm.ComponentGroupRequestForm;
import com.EMD.LSDB.vo.common.CompGroupVO;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.RequestCompGrpVO;
import com.EMD.LSDB.vo.common.RequestCompVO;
import com.EMD.LSDB.vo.common.RequestModelVO;
import com.EMD.LSDB.vo.common.RequestVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SpecTypeVo;
import com.EMD.LSDB.vo.common.SubSectionVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This Action class has the business methods for the Create Change
 *          Request Form *********************************************** ```
 ******************************************************************************/

public class ComponentGroupRequestAction extends EMDAction {
	
	/***************************************************************************
	 * This method is for loading section based on the model
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
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil
		.logMessage("Entering ComponentGroupRequestAction.fetchSections");
		String strForwardKey = null;
		ComponentGroupRequestForm objComponentGroupRequestForm = (ComponentGroupRequestForm) objActionForm;
		int intReqID = 0;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		try {
			ArrayList arlSectionList = null;
			ModelVo objModelVo = new ModelVo();
			RequestVO objRequestVO = new RequestVO();
			SectionVO objSectionMaintVo = new SectionVO();
			RequestModelVO objRequestModelVO = null;
			int intModelSeqNo = 0;
			int intSecSeqNo = 0;
			int intSubSecSeqNo = 0;
			//Added For CR_80 to add Specification Type Dropdown
			int intSpecTypeNo = 0;
			// Added For CR_80 to add Specification Type Dropdown - Ends here
			//This is for comparing logged in user ID and Request Created User
			// ID
			String strUser = null;
			String strReqUserID = null;
			
			//Added for disabling Model, section and Sub section dropdown
			String strHdnModelSeqNo = null;
			//Added For CR_80 to add Specification Type Dropdown
			String strHdnSpecTypeNo = null;
			int intHdnSpecTypeNo = 0;
			// Added For CR_80 to add Specification Type Dropdown - Ends here
			int intHdnModelSeqNo = 0;
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if (objComponentGroupRequestForm.getHdnReqID() != null
					&& !"".equalsIgnoreCase(objComponentGroupRequestForm
							.getHdnReqID())) {
				
				intReqID = Integer.parseInt(objComponentGroupRequestForm
						.getHdnReqID().trim());
				
			}
			
			LogUtil.logMessage("intReqID :" + intReqID);
			
			//Added for disabling Model, section and Sub section dropdown
			strHdnModelSeqNo = (String) objHttpServletRequest
			.getParameter("hdnModelSeqNo");
			if (strHdnModelSeqNo != null && !"".equals(strHdnModelSeqNo)) {
				
				intHdnModelSeqNo = Integer.parseInt(strHdnModelSeqNo);
			}

			//Added For CR_80 to add Specification Type Dropdown	
			strHdnSpecTypeNo = (String) objHttpServletRequest
			.getParameter("hdnSpecTypeNo");
			if (strHdnSpecTypeNo != null && !"".equals(strHdnSpecTypeNo)) {
				
				intHdnSpecTypeNo = Integer.parseInt(strHdnSpecTypeNo);
			}
			//Added For CR_80 to add Specification Type Dropdown - Ends here
			
			objRequestVO.setUserID(objLoginVo.getUserID());
			objRequestVO.setRequestID(intReqID);
			
			ChangeRequestBI objChangeRequestBI = ServiceFactory
			.getChangeRequestBO();
			
			ModelBI objModelBI = ServiceFactory.getModelBO();
			
			ArrayList objRequest = objChangeRequestBI
			.fetchRequestDetails(objRequestVO);
			/* Removed for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151
			///To Use Not Valid and InProgress in same fashion
			RequestVO objReqVO = new RequestVO();
			ArrayList objjRequest = new ArrayList();
			
			for(int k=0;k<objRequest.size();k++){
				
				objReqVO=(RequestVO)objRequest.get(k);
            
			if(objReqVO.getStatusTypeSeqNo() == 7){
				
				objReqVO.setStatusTypeSeqNo(1);
				
			}
			objjRequest.add(objReqVO);
			
			}*/
			
			objComponentGroupRequestForm.setRequestDetails(objRequest);
	
			//This logic is for getting the model seq no and pass to Section
			objRequestModelVO = objChangeRequestBI
			.fetchReqModelDetails(objRequestVO);
			LogUtil.logMessage("objRequestModelVO :" + objRequestModelVO);
			
			if (objRequestModelVO != null) {
				//Added For Specification DropDown CR_80
				intSpecTypeNo = objRequestModelVO.getSpecTypeNo();
				// Added For CR_80 to add Specification Type Dropdown - Ends here
				intModelSeqNo = objRequestModelVO.getModelSeqNo();
				intSecSeqNo = objRequestModelVO.getSectionSeqNo();
				intSubSecSeqNo = objRequestModelVO.getSubSectionSeqNo();
			}
			
			//This part is for display submit button if model, sec and sub sec
			// details saved
			//Modified For CR_80 to add Specification Type Dropdown
			if (intSpecTypeNo == 0 && intModelSeqNo == 0 && intSecSeqNo == 0 && intSubSecSeqNo == 0) {
				
				objComponentGroupRequestForm.setReqModelSaved("N");
				
			} else {
				
				objComponentGroupRequestForm.setReqModelSaved("Y");
			}
			
			if (ApplicationConstants.ADMN.equals(objLoginVo.getRoleID())
					|| ApplicationConstants.MSE.equals(objLoginVo.getRoleID()))
				objComponentGroupRequestForm
				.setRoleID(ApplicationConstants.ADMN);//This is for
			// approve,
			// Reject, onhold,
			// complete enable
			else
				objComponentGroupRequestForm.setRoleID("OTHR");//ASM,SE
			//Ends
			
			// Added For CR_80 to add Specification Type Dropdown
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objComponentGroupRequestForm.setSpecTypeList(arlSpec);
			}
			// Added For CR_80 to add Specification Type Dropdown - Ends here	
			
			objModelVo.setUserID(objLoginVo.getUserID());
			// Added For CR_80 to add Specification Type Dropdown
			objModelVo.setSpecTypeSeqNo(intHdnSpecTypeNo);
			// Added For CR_80 to add Specification Type Dropdown - Ends here
			ArrayList objModel = objModelBI.fetchModels(objModelVo);
			
			objComponentGroupRequestForm.setModelList(objModel);
			
			objSectionMaintVo.setModelSeqNo(intHdnModelSeqNo);
			objSectionMaintVo.setUserID(objLoginVo.getUserID());
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			arlSectionList = objSectionBO.fetchSections(objSectionMaintVo);
			if (arlSectionList != null) {
				objComponentGroupRequestForm.setListSections(arlSectionList);
			}
			
			//This is for getting request ID reason
			
			if (objRequest != null && objRequest.size() > 0) {
				
				for (int i = 0; i < objRequest.size(); i++) {
					RequestVO objReq = (RequestVO) objRequest.get(i);
					strReqUserID = objReq.getReqUserID();
				}
			}
			
			//This part is for checking logged in user ID and Request Created
			// User ID
			//If equal then user have provision to modify request, else only
			// preview available
			strUser = objLoginVo.getUserID();
			if (strReqUserID != null && strUser != null) {
				
				if (strReqUserID.equals(strUser)) {
					objComponentGroupRequestForm.setUserOwnRequest("Y");
				} else {
					objComponentGroupRequestForm.setUserOwnRequest("N");
				}
			}
			
			//Ends
			
			//taking color flag from the form
			if (objComponentGroupRequestForm.getClauseColorFlag() == null
					|| "".equals(objComponentGroupRequestForm
							.getClauseColorFlag()))
				objComponentGroupRequestForm.setClauseColorFlag("N");
			//Modified for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151
			//Default option set as Not Required
			objComponentGroupRequestForm.setCompGroupChangeTypeSeqNo(7);//Default
			// set
			// as
			// New - Not Required
			objComponentGroupRequestForm.setCompChangeTypeSeqNo(7);//Default
			// set as New - Not Required
			objComponentGroupRequestForm.setNewCompGrpName("");
			objComponentGroupRequestForm.setNewCompName("");
			
			objComponentGroupRequestForm.setNewCompGrpChacFlag("N");
			objComponentGroupRequestForm.setNewCompGrpValidFlag("N");
			objComponentGroupRequestForm.setNewCompDefaultFlag("false");
			
			strForwardKey = ApplicationConstants.SUCCESS;
			//Adding for CR_94
			{
			RequestVO objNewReqVO = new RequestVO();
			objNewReqVO.setStatusTypeSeqNo(2);
			objNewReqVO.setLastName("All");
			objNewReqVO.setRequestID(0);
			ChangeRequestBI objNewChangeRequestBI = ServiceFactory.getChangeRequestBO();
			ArrayList arlSearchList = new ArrayList();
			arlSearchList = objNewChangeRequestBI.fetchRequestDetails(objNewReqVO);
			objComponentGroupRequestForm.setSearchList(arlSearchList);
			}
			//Ends here CR_94
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
	 * This method is for loading Subsection based on the Section
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
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil
		.logMessage("Entering ComponentGroupRequestAction.fetchSubSections");
		String strForwardKey = null;
		ComponentGroupRequestForm objComponentGroupRequestForm = (ComponentGroupRequestForm) objActionForm;
		int intReqID = 0;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		try {
			ArrayList arlSectionList = null;
			ArrayList arlSubSecList = null;
			ModelVo objModelVo = new ModelVo();
			RequestVO objRequestVO = new RequestVO();
			SectionVO objSectionMaintVo = new SectionVO();
			SubSectionVO objSubSecMaintVO = new SubSectionVO();
			RequestModelVO objRequestModelVO = null;
			int intModelSeqNo = 0;
			int intSecSeqNo = 0;
			int intSubSecSeqNo = 0;
			//Added For CR_80 to add Specification Type Dropdown
			int intSpecTypeNo = 0;
			// Added For CR_80 to add Specification Type Dropdown - Ends here
			//This is for comparing logged in user ID and Request Created User
			// ID
			String strUser = null;
			String strReqUserID = null;
			
			//Added for disabling Model, section and Sub section dropdown
			String strHdnModelSeqNo = null;
			String strHdnSecSeqNo = null;
			//Added For CR_80 to add Specification Type Dropdown
			String strHdnSpecTypeNo = null;
			int intHdnSpecTypeNo = 0;
			// Added For CR_80 to add Specification Type Dropdown - Ends here
			int intHdnModelSeqNo = 0;
			int intHdnSecSeqNo = 0;
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if (objComponentGroupRequestForm.getHdnReqID() != null
					&& !"".equalsIgnoreCase(objComponentGroupRequestForm
							.getHdnReqID())) {
				
				intReqID = Integer.parseInt(objComponentGroupRequestForm
						.getHdnReqID().trim());
				
			}
			
			LogUtil.logMessage("intReqID :" + intReqID);
			
			//Added for disabling Model, section and Sub section dropdown
			strHdnModelSeqNo = (String) objHttpServletRequest
			.getParameter("hdnModelSeqNo");
			strHdnSecSeqNo = (String) objHttpServletRequest
			.getParameter("hdnSecSeqNo");
			if (strHdnModelSeqNo != null && !"".equals(strHdnModelSeqNo)) {
				
				intHdnModelSeqNo = Integer.parseInt(strHdnModelSeqNo);
			}
			if (strHdnSecSeqNo != null && !"".equals(strHdnSecSeqNo)) {
				
				intHdnSecSeqNo = Integer.parseInt(strHdnSecSeqNo);
			}

			//Added For CR_80 to add Specification Type Dropdown	
			strHdnSpecTypeNo = (String) objHttpServletRequest
			.getParameter("hdnSpecTypeNo");
			if (strHdnSpecTypeNo != null && !"".equals(strHdnSpecTypeNo)) {
				
				intHdnSpecTypeNo = Integer.parseInt(strHdnSpecTypeNo);
			}
			//Added For CR_80 to add Specification Type Dropdown - Ends here
			
			objRequestVO.setUserID(objLoginVo.getUserID());
			objRequestVO.setRequestID(intReqID);
			
			ChangeRequestBI objChangeRequestBI = ServiceFactory
			.getChangeRequestBO();
			
			ModelBI objModelBI = ServiceFactory.getModelBO();
			
			ArrayList objRequest = objChangeRequestBI
			.fetchRequestDetails(objRequestVO);
			/* Removed for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151
			///To set Not Valid and InProgress in same fashion
			RequestVO objReqVO = new RequestVO();
			ArrayList objjRequest = new ArrayList();
			
			for(int k=0;k<objRequest.size();k++){
				
				objReqVO=(RequestVO)objRequest.get(k);
				
			if(objReqVO.getStatusTypeSeqNo() == 7){
				
				objReqVO.setStatusTypeSeqNo(1);
				
			}
			objjRequest.add(objReqVO);
			
			}*/
			
			objComponentGroupRequestForm.setRequestDetails(objRequest);
			
			//This logic is for getting the model seq no and pass to Section
			objRequestModelVO = objChangeRequestBI
			.fetchReqModelDetails(objRequestVO);
			LogUtil.logMessage("objRequestModelVO :" + objRequestModelVO);
			
			if (objRequestModelVO != null) {
				//Added For CR_80 to add Specification Type Dropdown
				intSpecTypeNo = objRequestModelVO.getSpecTypeNo();
				// Added For CR_80 to add Specification Type Dropdown - Ends here
				intModelSeqNo = objRequestModelVO.getModelSeqNo();
				intSecSeqNo = objRequestModelVO.getSectionSeqNo();
				intSubSecSeqNo = objRequestModelVO.getSubSectionSeqNo();
			}
			
			//This part is for display submit button if model, sec and sub sec
			// details saved
			//Modified For CR_80 to add Specification Type Dropdown
			if (intSpecTypeNo == 0 && intModelSeqNo == 0 && intSecSeqNo == 0 && intSubSecSeqNo == 0) {
				
				objComponentGroupRequestForm.setReqModelSaved("N");
				
			} else {
				
				objComponentGroupRequestForm.setReqModelSaved("Y");
			}
			
			if (ApplicationConstants.ADMN.equals(objLoginVo.getRoleID())
					|| ApplicationConstants.MSE.equals(objLoginVo.getRoleID()))
				objComponentGroupRequestForm
				.setRoleID(ApplicationConstants.ADMN);//This is for
			// approve,
			// Reject, onhold,
			// complete enable
			else
				objComponentGroupRequestForm.setRoleID("OTHR");//ASM,SE
			//Ends

			// Added For CR_80 to add Specification Type Dropdown
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objComponentGroupRequestForm.setSpecTypeList(arlSpec);
			}
			// Added For CR_80 to add Specification Type Dropdown - Ends here	
			
			objModelVo.setUserID(objLoginVo.getUserID());
			// Added For CR_80 to add Specification Type Dropdown
			objModelVo.setSpecTypeSeqNo(intHdnSpecTypeNo);
			// Added For CR_80 to add Specification Type Dropdown - Ends here
			ArrayList objModel = objModelBI.fetchModels(objModelVo);
			
			objComponentGroupRequestForm.setModelList(objModel);
			
			objSectionMaintVo.setModelSeqNo(intHdnModelSeqNo);
			objSectionMaintVo.setUserID(objLoginVo.getUserID());
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			arlSectionList = objSectionBO.fetchSections(objSectionMaintVo);
			if (arlSectionList != null) {
				objComponentGroupRequestForm.setListSections(arlSectionList);
			}
			
			ModelSubSectionBI objjSubSecMaintBO = ServiceFactory
			.getSubSecMaintBO();
			
			objSubSecMaintVO.setModelSeqNo(intHdnModelSeqNo);
			objSubSecMaintVO.setSecSeqNo(intHdnSecSeqNo);
			objSubSecMaintVO.setUserID(objLoginVo.getUserID());
			
			arlSubSecList = objjSubSecMaintBO
			.fetchSubSections(objSubSecMaintVO);
			
			objComponentGroupRequestForm.setSubSectionList(arlSubSecList);
			
			//This is for getting request ID reason
			
			if (objRequest != null && objRequest.size() > 0) {
				
				for (int i = 0; i < objRequest.size(); i++) {
					RequestVO objReq = (RequestVO) objRequest.get(i);
					strReqUserID = objReq.getReqUserID();
				}
			}
			
			//This part is for checking logged in user ID and Request Created
			// User ID
			//If equal then user have provision to modify request, else only
			// preview available
			strUser = objLoginVo.getUserID();
			if (strReqUserID != null && strUser != null) {
				
				if (strReqUserID.equals(strUser)) {
					objComponentGroupRequestForm.setUserOwnRequest("Y");
				} else {
					objComponentGroupRequestForm.setUserOwnRequest("N");
				}
			}
			
			//Ends
			
			//taking color flag from the form
			if (objComponentGroupRequestForm.getClauseColorFlag() == null
					|| "".equals(objComponentGroupRequestForm
							.getClauseColorFlag()))
				objComponentGroupRequestForm.setClauseColorFlag("N");
//			Modified for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151
			//Default option set as Not Required
			objComponentGroupRequestForm.setCompGroupChangeTypeSeqNo(7);//Default
			// set
			// as
			// New - Not Required
			objComponentGroupRequestForm.setCompChangeTypeSeqNo(7);//Default
			// set as New - Not Required
			objComponentGroupRequestForm.setNewCompGrpName("");
			objComponentGroupRequestForm.setNewCompName("");
			
			objComponentGroupRequestForm.setNewCompGrpChacFlag("N");
			objComponentGroupRequestForm.setNewCompGrpValidFlag("N");
			
			objComponentGroupRequestForm.setNewCompDefaultFlag("false");
			
			strForwardKey = ApplicationConstants.SUCCESS;
			//Adding for CR_94
			{
			RequestVO objNewReqVO = new RequestVO();
			objNewReqVO.setStatusTypeSeqNo(2);
			objNewReqVO.setLastName("All");
			objNewReqVO.setRequestID(0);
			ChangeRequestBI objNewChangeRequestBI = ServiceFactory.getChangeRequestBO();
			ArrayList arlSearchList = new ArrayList();
			arlSearchList = objNewChangeRequestBI.fetchRequestDetails(objNewReqVO);
			objComponentGroupRequestForm.setSearchList(arlSearchList);
			}
			//Ends here CR_94
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
	 * This method is for loading Available Component Group based on the model,
	 * section and subsection
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
	
	public ActionForward fetchCompGrps(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil
		.logMessage("Entering ComponentGroupRequestAction.fetchCompGrps");
		String strForwardKey = null;
		ComponentGroupRequestForm objComponentGroupRequestForm = (ComponentGroupRequestForm) objActionForm;
		int intReqID = 0;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		try {
			ArrayList arlSectionList = null;
			ArrayList arlSubSecList = null;
			ModelVo objModelVo = new ModelVo();
			RequestVO objRequestVO = new RequestVO();
			SectionVO objSectionMaintVo = new SectionVO();
			SubSectionVO objSubSecMaintVO = new SubSectionVO();
			ComponentVO objComponentVo = new ComponentVO();
			RequestModelVO objRequestModelVO = null;
			int intModelSeqNo = 0;
			int intSecSeqNo = 0;
			int intSubSecSeqNo = 0;
			//Added For CR_80 to add Specification Type Dropdown
			int intSpecTypeNo = 0;
			// Added For CR_80 to add Specification Type Dropdown - Ends here
			//This is for comparing logged in user ID and Request Created User
			// ID
			String strUser = null;
			String strReqUserID = null;
			
			//Added for disabling Model, section and Sub section dropdown
			String strHdnModelSeqNo = null;
			String strHdnSecSeqNo = null;
			String strHdnSubSecSeqNo = null;
			//Added For CR_80 to add Specification Type Dropdown
			String strHdnSpecTypeNo = null;
			int intHdnSpecTypeNo = 0;
			// Added For CR_80 to add Specification Type Dropdown - Ends here
			int intHdnModelSeqNo = 0;
			int intHdnSecSeqNo = 0;
			int intHdnSubSecSeqNo = 0;
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if (objComponentGroupRequestForm.getHdnReqID() != null
					&& !"".equalsIgnoreCase(objComponentGroupRequestForm
							.getHdnReqID())) {
				
				intReqID = Integer.parseInt(objComponentGroupRequestForm
						.getHdnReqID().trim());
				
			}
			
			LogUtil.logMessage("intReqID :" + intReqID);
			
			//Added for disabling Model, section and Sub section dropdown
			strHdnModelSeqNo = (String) objHttpServletRequest
			.getParameter("hdnModelSeqNo");
			strHdnSecSeqNo = (String) objHttpServletRequest
			.getParameter("hdnSecSeqNo");
			strHdnSubSecSeqNo = (String) objHttpServletRequest
			.getParameter("hdnSubSecSeqNo");
			if (strHdnModelSeqNo != null && !"".equals(strHdnModelSeqNo)) {
				
				intHdnModelSeqNo = Integer.parseInt(strHdnModelSeqNo);
			}
			if (strHdnSecSeqNo != null && !"".equals(strHdnSecSeqNo)) {
				
				intHdnSecSeqNo = Integer.parseInt(strHdnSecSeqNo);
			}
			if (strHdnSubSecSeqNo != null && !"".equals(strHdnSubSecSeqNo)) {
				
				intHdnSubSecSeqNo = Integer.parseInt(strHdnSubSecSeqNo);
			}

			//Added For CR_80 to add Specification Type Dropdown	
			strHdnSpecTypeNo = (String) objHttpServletRequest
			.getParameter("hdnSpecTypeNo");
			if (strHdnSpecTypeNo != null && !"".equals(strHdnSpecTypeNo)) {
				
				intHdnSpecTypeNo = Integer.parseInt(strHdnSpecTypeNo);
			}
			//Added For CR_80 to add Specification Type Dropdown - Ends here
			
			objRequestVO.setUserID(objLoginVo.getUserID());
			objRequestVO.setRequestID(intReqID);
			
			ChangeRequestBI objChangeRequestBI = ServiceFactory
			.getChangeRequestBO();
			
			ModelBI objModelBI = ServiceFactory.getModelBO();
			
			ArrayList objRequest = objChangeRequestBI
			.fetchRequestDetails(objRequestVO);
			/* Removed for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151
			///To set Not Valid and InProgress in same fashion
			RequestVO objReqVO = new RequestVO();
			ArrayList objjRequest = new ArrayList();
			
			for(int k=0;k<objRequest.size();k++){
				
				objReqVO=(RequestVO)objRequest.get(k);
				
			if(objReqVO.getStatusTypeSeqNo() == 7){
				
				objReqVO.setStatusTypeSeqNo(1);
				
			}
			objjRequest.add(objReqVO);
			
			}*/
			
			objComponentGroupRequestForm.setRequestDetails(objRequest);
			
			//This logic is for getting the model seq no and pass to Section
			objRequestModelVO = objChangeRequestBI
			.fetchReqModelDetails(objRequestVO);
			LogUtil.logMessage("objRequestModelVO :" + objRequestModelVO);
			
			if (objRequestModelVO != null) {
				//Added For CR_80 to add Specification Type Dropdown
				intSpecTypeNo = objRequestModelVO.getSpecTypeNo();
				// Added For CR_80 to add Specification Type Dropdown - Ends here
				intModelSeqNo = objRequestModelVO.getModelSeqNo();
				intSecSeqNo = objRequestModelVO.getSectionSeqNo();
				intSubSecSeqNo = objRequestModelVO.getSubSectionSeqNo();
			}
			
			//This part is for display submit button if model, sec and sub sec
			// details saved
			//Modified For CR_80 to add Specification Type Dropdown
			if (intSpecTypeNo == 0 && intModelSeqNo == 0 && intSecSeqNo == 0 && intSubSecSeqNo == 0) {
				
				objComponentGroupRequestForm.setReqModelSaved("N");
				
			} else {
				
				objComponentGroupRequestForm.setReqModelSaved("Y");
			}
			
			if (ApplicationConstants.ADMN.equals(objLoginVo.getRoleID())
					|| ApplicationConstants.MSE.equals(objLoginVo.getRoleID()))
				objComponentGroupRequestForm
				.setRoleID(ApplicationConstants.ADMN);//This is for
			// approve,
			// Reject, onhold,
			// complete enable
			else
				objComponentGroupRequestForm.setRoleID("OTHR");//ASM,SE
			//Ends

			// Added For CR_80 to add Specification Type Dropdown
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objComponentGroupRequestForm.setSpecTypeList(arlSpec);
			}
			// Added For CR_80 to add Specification Type Dropdown - Ends here	
			
			objModelVo.setUserID(objLoginVo.getUserID());
			// Added For CR_80 to add Specification Type Dropdown
			objModelVo.setSpecTypeSeqNo(intHdnSpecTypeNo);
			// Added For CR_80 to add Specification Type Dropdown - Ends here
			ArrayList objModel = objModelBI.fetchModels(objModelVo);
			
			objComponentGroupRequestForm.setModelList(objModel);
			
			objSectionMaintVo.setModelSeqNo(intHdnModelSeqNo);
			objSectionMaintVo.setUserID(objLoginVo.getUserID());
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			arlSectionList = objSectionBO.fetchSections(objSectionMaintVo);
			if (arlSectionList != null) {
				objComponentGroupRequestForm.setListSections(arlSectionList);
			}
			
			ModelSubSectionBI objjSubSecMaintBO = ServiceFactory
			.getSubSecMaintBO();
			
			LogUtil.logMessage("ModelSeqNo :" + intHdnModelSeqNo);
			LogUtil.logMessage("SectionSeqNo :" + intHdnSecSeqNo);
			objSubSecMaintVO.setModelSeqNo(intHdnModelSeqNo);
			objSubSecMaintVO.setSecSeqNo(intHdnSecSeqNo);
			objSubSecMaintVO.setUserID(objLoginVo.getUserID());
			
			arlSubSecList = objjSubSecMaintBO
			.fetchSubSections(objSubSecMaintVO);
			
			objComponentGroupRequestForm.setSubSectionList(arlSubSecList);
			
			ModelCompBI objModelCompBI = ServiceFactory.getModelCompBO();
			
			LogUtil.logMessage("SubSectionSeqNo :" + intHdnSubSecSeqNo);
			
			objComponentVo.setModelSeqNo(intHdnModelSeqNo);
			objComponentVo.setSubSectionSeqNo(intHdnSubSecSeqNo);
			objComponentVo.setUserID(objLoginVo.getUserID());
			ArrayList objCompGroup = objModelCompBI
			.fetchModelComps(objComponentVo);
			objComponentGroupRequestForm.setCompGrpList(objCompGroup);
			
			//This is for getting request ID reason
			
			if (objRequest != null && objRequest.size() > 0) {
				
				for (int i = 0; i < objRequest.size(); i++) {
					RequestVO objReq = (RequestVO) objRequest.get(i);
					strReqUserID = objReq.getReqUserID();
				}
			}
			
			//This part is for checking logged in user ID and Request Created
			// User ID
			//If equal then user have provision to modify request, else only
			// preview available
			strUser = objLoginVo.getUserID();
			if (strReqUserID != null && strUser != null) {
				
				if (strReqUserID.equals(strUser)) {
					objComponentGroupRequestForm.setUserOwnRequest("Y");
				} else {
					objComponentGroupRequestForm.setUserOwnRequest("N");
				}
			}
			
			//Ends
			
			//taking color flag from the form
			if (objComponentGroupRequestForm.getClauseColorFlag() == null
					|| "".equals(objComponentGroupRequestForm
							.getClauseColorFlag()))
				objComponentGroupRequestForm.setClauseColorFlag("N");
//			Modified for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151
			//Default option set as Not Required
			objComponentGroupRequestForm.setCompGroupChangeTypeSeqNo(7);//Default
			// set
			// as
			// New - Not Required
			objComponentGroupRequestForm.setCompChangeTypeSeqNo(7);//Default
			// set as New - Not Required
			
			objComponentGroupRequestForm.setNewCompGrpName("");
			objComponentGroupRequestForm.setNewCompName("");
			
			objComponentGroupRequestForm.setNewCompGrpChacFlag("N");
			objComponentGroupRequestForm.setNewCompGrpValidFlag("N");
			
			objComponentGroupRequestForm.setNewCompDefaultFlag("false");
			
			strForwardKey = ApplicationConstants.SUCCESS;
			//Adding for CR_94
			{
			RequestVO objNewReqVO = new RequestVO();
			objNewReqVO.setStatusTypeSeqNo(2);
			objNewReqVO.setLastName("All");
			objNewReqVO.setRequestID(0);
			ChangeRequestBI objNewChangeRequestBI = ServiceFactory.getChangeRequestBO();
			ArrayList arlSearchList = new ArrayList();
			arlSearchList = objNewChangeRequestBI.fetchRequestDetails(objNewReqVO);
			objComponentGroupRequestForm.setSearchList(arlSearchList);
			}
			//Ends here CR_94
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
	 * This method is for loading Available Components based on the model,
	 * section and subsection, comp group
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
	
	public ActionForward fetchComps(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil.logMessage("Entering ComponentGroupRequestAction.fetchComps");
		String strForwardKey = null;
		ComponentGroupRequestForm objComponentGroupRequestForm = (ComponentGroupRequestForm) objActionForm;
		int intReqID = 0;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		try {
			ArrayList arlSectionList = null;
			ArrayList arlSubSecList = null;
			ModelVo objModelVo = new ModelVo();
			RequestVO objRequestVO = new RequestVO();
			SectionVO objSectionMaintVo = new SectionVO();
			SubSectionVO objSubSecMaintVO = new SubSectionVO();
			ComponentVO objComponentVo = new ComponentVO();
			RequestModelVO objRequestModelVO = null;
			int intModelSeqNo = 0;
			int intSecSeqNo = 0;
			int intSubSecSeqNo = 0;
			//Added For CR_80 to add Specification Type Dropdown
			int intSpecTypeNo = 0;
			// Added For CR_80 to add Specification Type Dropdown - Ends here
			//This is for comparing logged in user ID and Request Created User
			// ID
			String strUser = null;
			String strReqUserID = null;
			
			//	Added for disabling Model, section and Sub section dropdown
			String strHdnModelSeqNo = null;
			String strHdnSecSeqNo = null;
			String strHdnSubSecSeqNo = null;
			//Added For CR_80 to add Specification Type Dropdown
			String strHdnSpecTypeNo = null;
			int intHdnSpecTypeNo = 0;
			// Added For CR_80 to add Specification Type Dropdown - Ends here
			int intHdnModelSeqNo = 0;
			int intHdnSecSeqNo = 0;
			int intHdnSubSecSeqNo = 0;
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if (objComponentGroupRequestForm.getHdnReqID() != null
					&& !"".equalsIgnoreCase(objComponentGroupRequestForm
							.getHdnReqID())) {
				
				intReqID = Integer.parseInt(objComponentGroupRequestForm
						.getHdnReqID().trim());
				
			}
			
			LogUtil.logMessage("intReqID :" + intReqID);
			
			//Added for disabling Model, section and Sub section dropdown
			strHdnModelSeqNo = (String) objHttpServletRequest
			.getParameter("hdnModelSeqNo");
			strHdnSecSeqNo = (String) objHttpServletRequest
			.getParameter("hdnSecSeqNo");
			strHdnSubSecSeqNo = (String) objHttpServletRequest
			.getParameter("hdnSubSecSeqNo");
			if (strHdnModelSeqNo != null && !"".equals(strHdnModelSeqNo)) {
				
				intHdnModelSeqNo = Integer.parseInt(strHdnModelSeqNo);
			}
			if (strHdnSecSeqNo != null && !"".equals(strHdnSecSeqNo)) {
				
				intHdnSecSeqNo = Integer.parseInt(strHdnSecSeqNo);
			}
			if (strHdnSubSecSeqNo != null && !"".equals(strHdnSubSecSeqNo)) {
				
				intHdnSubSecSeqNo = Integer.parseInt(strHdnSubSecSeqNo);
			}

			//Added For CR_80 to add Specification Type Dropdown	
			strHdnSpecTypeNo = (String) objHttpServletRequest
			.getParameter("hdnSpecTypeNo");
			if (strHdnSpecTypeNo != null && !"".equals(strHdnSpecTypeNo)) {
				
				intHdnSpecTypeNo = Integer.parseInt(strHdnSpecTypeNo);
			}
			//Added For CR_80 to add Specification Type Dropdown - Ends here
			
			objRequestVO.setUserID(objLoginVo.getUserID());
			objRequestVO.setRequestID(intReqID);
			
			ChangeRequestBI objChangeRequestBI = ServiceFactory
			.getChangeRequestBO();
			
			ModelBI objModelBI = ServiceFactory.getModelBO();
			
			ArrayList objRequest = objChangeRequestBI
			.fetchRequestDetails(objRequestVO);
			/* Removed for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151
			///To set Not Valid and InProgress in same fashion
			RequestVO objReqVO = new RequestVO();
			ArrayList objjRequest = new ArrayList();
			
			for(int k=0;k<objRequest.size();k++){
				
				objReqVO=(RequestVO)objRequest.get(k);
			
			if(objReqVO.getStatusTypeSeqNo() == 7){
				
				objReqVO.setStatusTypeSeqNo(1);
				
			}
			objjRequest.add(objReqVO);
			
			}*/
			
			objComponentGroupRequestForm.setRequestDetails(objRequest);
			
			//This logic is for getting the model seq no and pass to Section
			objRequestModelVO = objChangeRequestBI
			.fetchReqModelDetails(objRequestVO);
			LogUtil.logMessage("objRequestModelVO :" + objRequestModelVO);
			
			if (objRequestModelVO != null) {
				//Added For CR_80 to add Specification Type Dropdown
				intSpecTypeNo = objRequestModelVO.getSpecTypeNo();
				// Added For CR_80 to add Specification Type Dropdown - Ends here
				intModelSeqNo = objRequestModelVO.getModelSeqNo();
				intSecSeqNo = objRequestModelVO.getSectionSeqNo();
				intSubSecSeqNo = objRequestModelVO.getSubSectionSeqNo();
				
			}
			
			//This part is for display submit button if model, sec and sub sec
			// details saved
			//Modified For CR_80 to add Specification Type Dropdown
			if (intSpecTypeNo == 0 && intModelSeqNo == 0 && intSecSeqNo == 0 && intSubSecSeqNo == 0) {
				
				objComponentGroupRequestForm.setReqModelSaved("N");
				
			} else {
				
				objComponentGroupRequestForm.setReqModelSaved("Y");
				
				/*
				 * Change for Model apply flag
				 */
				if (objRequestModelVO.isApplyModelFlag()) {
					
					objComponentGroupRequestForm.setApplyModelFlag(true);
				} else {
					objComponentGroupRequestForm.setApplyModelFlag(false);
				}
			}
			
			if (ApplicationConstants.ADMN.equals(objLoginVo.getRoleID())
					|| ApplicationConstants.MSE.equals(objLoginVo.getRoleID()))
				objComponentGroupRequestForm
				.setRoleID(ApplicationConstants.ADMN);//This is for
			// approve,
			// Reject, onhold,
			// complete enable
			else
				objComponentGroupRequestForm.setRoleID("OTHR");//ASM,SE
			//Ends

			// Added For CR_80 to add Specification Type Dropdown
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objComponentGroupRequestForm.setSpecTypeList(arlSpec);
			}
			// Added For CR_80 to add Specification Type Dropdown - Ends here	
			
			objModelVo.setUserID(objLoginVo.getUserID());
			// Added For CR_80 to add Specification Type Dropdown
			objModelVo.setSpecTypeSeqNo(intHdnSpecTypeNo);
			// Added For CR_80 to add Specification Type Dropdown - Ends here
			ArrayList objModel = objModelBI.fetchModels(objModelVo);
			
			objComponentGroupRequestForm.setModelList(objModel);
			
			objSectionMaintVo.setModelSeqNo(intHdnModelSeqNo);
			objSectionMaintVo.setUserID(objLoginVo.getUserID());
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			arlSectionList = objSectionBO.fetchSections(objSectionMaintVo);
			if (arlSectionList != null) {
				objComponentGroupRequestForm.setListSections(arlSectionList);
			}
			
			ModelSubSectionBI objjSubSecMaintBO = ServiceFactory
			.getSubSecMaintBO();
			
			objSubSecMaintVO.setModelSeqNo(intHdnModelSeqNo);
			objSubSecMaintVO.setSecSeqNo(intHdnSecSeqNo);
			objSubSecMaintVO.setUserID(objLoginVo.getUserID());
			
			arlSubSecList = objjSubSecMaintBO
			.fetchSubSections(objSubSecMaintVO);
			
			objComponentGroupRequestForm.setSubSectionList(arlSubSecList);
			
			ModelCompBI objModelCompBI = ServiceFactory.getModelCompBO();
			
			objComponentVo.setModelSeqNo(intHdnModelSeqNo);
			objComponentVo.setSubSectionSeqNo(intHdnSubSecSeqNo);
			objComponentVo.setUserID(objLoginVo.getUserID());
			ArrayList objCompGroup = objModelCompBI
			.fetchModelComps(objComponentVo);
			objComponentGroupRequestForm.setCompGrpList(objCompGroup);
			
			LogUtil.logMessage("CompGrpSeqNo :"
					+ objComponentGroupRequestForm.getCompGrpSeqNo());
			
			//	Set as zero
			if (objComponentGroupRequestForm.getCompGrpSeqNo() <= 0) {
				
				objComponentVo.setComponentGroupSeqNo(0);
			} else {
				objComponentVo
				.setComponentGroupSeqNo(objComponentGroupRequestForm
						.getCompGrpSeqNo());
				
			}
			
			ModelCompMapBI objModelCompMapBI = ServiceFactory
			.getModelCompMapBO();
			ArrayList objCompOutter = objModelCompMapBI
			.fetchCompMap(objComponentVo);
			
			ArrayList objComp = new ArrayList();
			
			if (objCompOutter != null && objCompOutter.size() > 0) {
				if ((ArrayList) objCompOutter.get(0) != null) {
					
					objComp = (ArrayList) objCompOutter.get(0);//All the Comp
					// Info//All the
					// Comp Info
				}
			}
			
			objComponentGroupRequestForm.setCompList(objComp);
			
			//This is for fetching the selected Component Group details
			
			ModelCompGroupBI objModelCompGroupBI = ServiceFactory
			.getModelCompGroupBO();
			CompGroupVO objCompGroupVO = new CompGroupVO();
			//Set as zero
			//if(objComponentGroupRequestForm.getCompGrpSeqNo()<=0){
			
			//objCompGroupVO.setComponentGroupSeqNo(0);
			//}else{
			objCompGroupVO.setComponentGroupSeqNo(objComponentGroupRequestForm
					.getCompGrpSeqNo());
			
			//}
			objCompGroupVO.setUserID(objLoginVo.getUserID());
			ArrayList objCompGroupDetail = objModelCompGroupBI
			.fetchCompGroups(objCompGroupVO);
			
			if (objCompGroupDetail != null && objCompGroupDetail.size() > 0) {
				
				for (int i = 0; i < objCompGroupDetail.size(); i++) {
					
					CompGroupVO compGroup = (CompGroupVO) objCompGroupDetail
					.get(i);
					if (compGroup != null) {
						
						objComponentGroupRequestForm
						.setOldCompGrpName(compGroup
								.getComponentGroupName());
						objComponentGroupRequestForm
						.setOldCompGrpChacFlag(compGroup
								.getCharacterisationFlag());
						objComponentGroupRequestForm
						.setOldCompGrpValidFlag(compGroup
								.getValidFlag());
						
						objComponentGroupRequestForm
						.setNewCompGrpName(compGroup
								.getComponentGroupName());
						objComponentGroupRequestForm
						.setNewCompGrpChacFlag(compGroup
								.getCharacterisationFlag());
						objComponentGroupRequestForm
						.setNewCompGrpValidFlag(compGroup
								.getValidFlag());
					}
				}
			} else {// Added
				//Reset
				
				objComponentGroupRequestForm.setOldCompGrpName("");
				objComponentGroupRequestForm.setOldCompGrpChacFlag("N");
				objComponentGroupRequestForm.setOldCompGrpValidFlag("N");
				
				objComponentGroupRequestForm.setNewCompGrpName("");
				objComponentGroupRequestForm.setNewCompGrpChacFlag("N");
				objComponentGroupRequestForm.setNewCompGrpValidFlag("N");
			}
			
			LogUtil
			.logMessage("objComponentGroupRequestForm.getCompGroupChangeTypeSeqNo():"
					+ objComponentGroupRequestForm
					.getCompGroupChangeTypeSeqNo());
			LogUtil
			.logMessage("objComponentGroupRequestForm.getNewCompGrpChacFlag():"
					+ objComponentGroupRequestForm
					.getNewCompGrpChacFlag());
			LogUtil
			.logMessage("objComponentGroupRequestForm.getNewCompGrpValidFlag():"
					+ objComponentGroupRequestForm
					.getNewCompGrpValidFlag());
			
			//This is for getting request ID reason
			
			if (objRequest != null && objRequest.size() > 0) {
				
				for (int i = 0; i < objRequest.size(); i++) {
					RequestVO objReq = (RequestVO) objRequest.get(i);
					strReqUserID = objReq.getReqUserID();
				}
			}
			
			//This part is for checking logged in user ID and Request Created
			// User ID
			//If equal then user have provision to modify request, else only
			// preview available
			strUser = objLoginVo.getUserID();
			if (strReqUserID != null && strUser != null) {
				
				if (strReqUserID.equals(strUser)) {
					objComponentGroupRequestForm.setUserOwnRequest("Y");
				} else {
					objComponentGroupRequestForm.setUserOwnRequest("N");
				}
			}
			
			//Ends
			
			objComponentGroupRequestForm
			.setCompGroupChangeTypeSeqNo(objComponentGroupRequestForm
					.getCompGroupChangeTypeSeqNo());
			//objComponentGroupRequestForm.setCompGroupDetail(objCompGroupDetail);
			
			//taking color flag from the form
			if (objComponentGroupRequestForm.getClauseColorFlag() == null
					|| "".equals(objComponentGroupRequestForm
							.getClauseColorFlag()))
				objComponentGroupRequestForm.setClauseColorFlag("N");
			
			objComponentGroupRequestForm.setNewCompName("");
			//objComponentGroupRequestForm.setOldCompDefaultFlag("false");
			objComponentGroupRequestForm.setNewCompDefaultFlag("false");
			LogUtil.logMessage("intSpecTypeNo :" + intHdnSpecTypeNo);
			LogUtil.logMessage("intHdnModelSeqNo :" + intHdnModelSeqNo);
			LogUtil.logMessage("intHdnSecSeqNo :" + intHdnSecSeqNo);
			LogUtil.logMessage("intHdnSubSecSeqNo :" + intHdnSubSecSeqNo);
			//Added For CR_80 to add Specification Type Dropdown
			objComponentGroupRequestForm.setSpecTypeNo(intHdnSpecTypeNo);
			// Added For CR_80 to add Specification Type Dropdown - Ends here
			objComponentGroupRequestForm.setModelSeqNo(intHdnModelSeqNo);
			objComponentGroupRequestForm.setSectionSeqNo(intHdnSecSeqNo);
			objComponentGroupRequestForm.setSubSectionSeqNo(intHdnSubSecSeqNo);
			
			strForwardKey = ApplicationConstants.SUCCESS;
			//Adding for CR_94
			{
			RequestVO objNewReqVO = new RequestVO();
			objNewReqVO.setStatusTypeSeqNo(2);
			objNewReqVO.setLastName("All");
			objNewReqVO.setRequestID(0);
			ChangeRequestBI objNewChangeRequestBI = ServiceFactory.getChangeRequestBO();
			ArrayList arlSearchList = new ArrayList();
			arlSearchList = objNewChangeRequestBI.fetchRequestDetails(objNewReqVO);
			objComponentGroupRequestForm.setSearchList(arlSearchList);
			}
			//Ends here CR_94
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
	 * This method is for loading the selected component details
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
	
	public ActionForward loadSelectedComponent(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil
		.logMessage("Entering ComponentGroupRequestAction.loadSelectedComponent");
		String strForwardKey = null;
		ComponentGroupRequestForm objComponentGroupRequestForm = (ComponentGroupRequestForm) objActionForm;
		int intReqID = 0;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		try {
			int intCompGrpSeqNo = 0;
			ArrayList arlSectionList = null;
			ArrayList arlSubSecList = null;
			ModelVo objModelVo = new ModelVo();
			RequestVO objRequestVO = new RequestVO();
			SectionVO objSectionMaintVo = new SectionVO();
			SubSectionVO objSubSecMaintVO = new SubSectionVO();
			ComponentVO objComponentVo = new ComponentVO();
			RequestModelVO objRequestModelVO = null;
			int intModelSeqNo = 0;
			int intSecSeqNo = 0;
			int intSubSecSeqNo = 0;
			//Added For CR_80 to add Specification Type Dropdown
			int intSpecTypeNo = 0;
			// Added For CR_80 to add Specification Type Dropdown - Ends here
			//This is for comparing logged in user ID and Request Created User
			// ID
			String strUser = null;
			String strReqUserID = null;
			
			//Added for disabling Model, section and Sub section dropdown
			String strHdnModelSeqNo = null;
			String strHdnSecSeqNo = null;
			String strHdnSubSecSeqNo = null;
			//Added For CR_80 to add Specification Type Dropdown
			String strHdnSpecTypeNo = null;
			int intHdnSpecTypeNo = 0;
			// Added For CR_80 to add Specification Type Dropdown - Ends here
			int intHdnModelSeqNo = 0;
			int intHdnSecSeqNo = 0;
			int intHdnSubSecSeqNo = 0;
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if (objComponentGroupRequestForm.getHdnReqID() != null
					&& !"".equalsIgnoreCase(objComponentGroupRequestForm
							.getHdnReqID())) {
				
				intReqID = Integer.parseInt(objComponentGroupRequestForm
						.getHdnReqID().trim());
				
			}
			
			LogUtil.logMessage("intReqID :" + intReqID);
			
			//Added for disabling Model, section and Sub section dropdown
			strHdnModelSeqNo = (String) objHttpServletRequest
			.getParameter("hdnModelSeqNo");
			strHdnSecSeqNo = (String) objHttpServletRequest
			.getParameter("hdnSecSeqNo");
			strHdnSubSecSeqNo = (String) objHttpServletRequest
			.getParameter("hdnSubSecSeqNo");
			if (strHdnModelSeqNo != null && !"".equals(strHdnModelSeqNo)) {
				
				intHdnModelSeqNo = Integer.parseInt(strHdnModelSeqNo);
			}
			if (strHdnSecSeqNo != null && !"".equals(strHdnSecSeqNo)) {
				
				intHdnSecSeqNo = Integer.parseInt(strHdnSecSeqNo);
			}
			if (strHdnSubSecSeqNo != null && !"".equals(strHdnSubSecSeqNo)) {
				
				intHdnSubSecSeqNo = Integer.parseInt(strHdnSubSecSeqNo);
			}

			//Added For CR_80 to add Specification Type Dropdown	
			strHdnSpecTypeNo = (String) objHttpServletRequest
			.getParameter("hdnSpecTypeNo");
			if (strHdnSpecTypeNo != null && !"".equals(strHdnSpecTypeNo)) {
				
				intHdnSpecTypeNo = Integer.parseInt(strHdnSpecTypeNo);
			}
			//Added For CR_80 to add Specification Type Dropdown - Ends here
			
			objRequestVO.setUserID(objLoginVo.getUserID());
			objRequestVO.setRequestID(intReqID);
			
			ChangeRequestBI objChangeRequestBI = ServiceFactory
			.getChangeRequestBO();
			
			ModelBI objModelBI = ServiceFactory.getModelBO();
			
			ArrayList objRequest = objChangeRequestBI
			.fetchRequestDetails(objRequestVO);
			/* Removed for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151
			///To set Not Valid and InProgress in same fashion
			RequestVO objReqVO = new RequestVO();
			ArrayList objjRequest = new ArrayList();
			
			for(int k=0;k<objRequest.size();k++){
				
				objReqVO=(RequestVO)objRequest.get(k);
							
			if(objReqVO.getStatusTypeSeqNo() == 7){
				
				objReqVO.setStatusTypeSeqNo(1);
				
			}
			objjRequest.add(objReqVO);
			
			}*/
			
			objComponentGroupRequestForm.setRequestDetails(objRequest);
			
			//This logic is for getting the model seq no and pass to Section
			objRequestModelVO = objChangeRequestBI
			.fetchReqModelDetails(objRequestVO);
			LogUtil.logMessage("objRequestModelVO :" + objRequestModelVO);
			
			if (objRequestModelVO != null) {
				//Added For CR_80 to add Specification Type Dropdown
				intSpecTypeNo = objRequestModelVO.getSpecTypeNo();
				// Added For CR_80 to add Specification Type Dropdown - Ends here
				intModelSeqNo = objRequestModelVO.getModelSeqNo();
				intSecSeqNo = objRequestModelVO.getSectionSeqNo();
				intSubSecSeqNo = objRequestModelVO.getSubSectionSeqNo();
				
			}
			
			//This part is for display submit button if model, sec and sub sec
			// details saved
			//Modified For CR_80 to add Specification Type Dropdown	
			if (intSpecTypeNo == 0 && intModelSeqNo == 0 && intSecSeqNo == 0 && intSubSecSeqNo == 0) {
				
				objComponentGroupRequestForm.setReqModelSaved("N");
				
			} else {
				
				objComponentGroupRequestForm.setReqModelSaved("Y");
				
				/*
				 * Change for Model apply flag
				 */
				if (objRequestModelVO.isApplyModelFlag()) {
					
					objComponentGroupRequestForm.setApplyModelFlag(true);
				} else {
					objComponentGroupRequestForm.setApplyModelFlag(false);
				}
				
			}
			
			if (ApplicationConstants.ADMN.equals(objLoginVo.getRoleID())
					|| ApplicationConstants.MSE.equals(objLoginVo.getRoleID()))
				objComponentGroupRequestForm
				.setRoleID(ApplicationConstants.ADMN);//This is for
			// approve,
			// Reject, onhold,
			// complete enable
			else
				objComponentGroupRequestForm.setRoleID("OTHR");//ASM,SE
			//Ends

			// Added For CR_80 to add Specification Type Dropdown
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objComponentGroupRequestForm.setSpecTypeList(arlSpec);
			}
			// Added For CR_80 to add Specification Type Dropdown - Ends here	
			
			objModelVo.setUserID(objLoginVo.getUserID());
			// Added For CR_80 to add Specification Type Dropdown
			objModelVo.setSpecTypeSeqNo(intHdnSpecTypeNo);	
			// Added For CR_80 to add Specification Type Dropdown - Ends here
			ArrayList objModel = objModelBI.fetchModels(objModelVo);
			
			objComponentGroupRequestForm.setModelList(objModel);
			
			objSectionMaintVo.setModelSeqNo(intHdnModelSeqNo);
			objSectionMaintVo.setUserID(objLoginVo.getUserID());
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			arlSectionList = objSectionBO.fetchSections(objSectionMaintVo);
			if (arlSectionList != null) {
				objComponentGroupRequestForm.setListSections(arlSectionList);
			}
			
			ModelSubSectionBI objjSubSecMaintBO = ServiceFactory
			.getSubSecMaintBO();
			
			objSubSecMaintVO.setModelSeqNo(intHdnModelSeqNo);
			objSubSecMaintVO.setSecSeqNo(intHdnSecSeqNo);
			objSubSecMaintVO.setUserID(objLoginVo.getUserID());
			
			arlSubSecList = objjSubSecMaintBO
			.fetchSubSections(objSubSecMaintVO);
			
			objComponentGroupRequestForm.setSubSectionList(arlSubSecList);
			
			ModelCompBI objModelCompBI = ServiceFactory.getModelCompBO();
			
			objComponentVo.setModelSeqNo(intHdnModelSeqNo);
			objComponentVo.setSubSectionSeqNo(intHdnSubSecSeqNo);
			objComponentVo.setUserID(objLoginVo.getUserID());
			ArrayList objCompGroup = objModelCompBI
			.fetchModelComps(objComponentVo);
			objComponentGroupRequestForm.setCompGrpList(objCompGroup);
			
			if ((String) objHttpServletRequest.getParameter("hdnCompGrpSeqNo") != null
					&& !"".equals((String) objHttpServletRequest
							.getParameter("hdnCompGrpSeqNo"))) {
				
				intCompGrpSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("hdnCompGrpSeqNo"));
			}
			if (intCompGrpSeqNo <= 0) {//set zero for -1 value
				intCompGrpSeqNo = 0;
			}
			
			LogUtil.logMessage("intCompGrpSeqNo :" + intCompGrpSeqNo);
			
			objComponentVo.setComponentGroupSeqNo(intCompGrpSeqNo);
			
			ModelCompMapBI objModelCompMapBI = ServiceFactory
			.getModelCompMapBO();
			
			ArrayList objCompOutter = objModelCompMapBI
			.fetchCompMap(objComponentVo);
			ArrayList objComp = new ArrayList();
			
			if (objCompOutter != null && objCompOutter.size() > 0) {
				if ((ArrayList) objCompOutter.get(0) != null) {
					
					objComp = (ArrayList) objCompOutter.get(0);//All the Comp
					// Info//All the
					// Comp Info
				}
			}
			
			objComponentGroupRequestForm.setCompList(objComp);
			
			//This is for fetching the selected Component Group details
			ModelCompGroupBI objModelCompGroupBI = ServiceFactory
			.getModelCompGroupBO();
			CompGroupVO objCompGroupVO = new CompGroupVO();
			objCompGroupVO.setComponentGroupSeqNo(intCompGrpSeqNo);
			objCompGroupVO.setUserID(objLoginVo.getUserID());
			ArrayList objCompGroupDetail = objModelCompGroupBI
			.fetchCompGroups(objCompGroupVO);
			
			if (objCompGroupDetail != null && objCompGroupDetail.size() > 0) {
				
				for (int i = 0; i < objCompGroupDetail.size(); i++) {
					
					CompGroupVO compGroup = (CompGroupVO) objCompGroupDetail
					.get(i);
					if (compGroup != null) {
						
						objComponentGroupRequestForm
						.setOldCompGrpName(compGroup
								.getComponentGroupName());
						objComponentGroupRequestForm
						.setOldCompGrpChacFlag(compGroup
								.getCharacterisationFlag());
						objComponentGroupRequestForm
						.setOldCompGrpValidFlag(compGroup
								.getValidFlag());
						
						//this is for loading the component group as eradonly
						// in the to part
						if (objComponentGroupRequestForm
								.getCompGroupChangeTypeSeqNo() == 2) {
							
							objComponentGroupRequestForm
							.setNewCompGrpName(compGroup
									.getComponentGroupName());
							objComponentGroupRequestForm
							.setNewCompGrpChacFlag(compGroup
									.getCharacterisationFlag());
							objComponentGroupRequestForm
							.setNewCompGrpValidFlag(compGroup
									.getValidFlag());
						}
					}
				}
			} else {// Added
				//Reset
				
				objComponentGroupRequestForm.setOldCompGrpName("");
				objComponentGroupRequestForm.setOldCompGrpChacFlag("N");
				objComponentGroupRequestForm.setOldCompGrpValidFlag("N");
				
				objComponentGroupRequestForm.setNewCompGrpName("");
				objComponentGroupRequestForm.setNewCompGrpChacFlag("N");
				objComponentGroupRequestForm.setNewCompGrpValidFlag("N");
			}
			
			//objComponentGroupRequestForm.setCompGroupDetail(objCompGroupDetail);
			
			//This is for selected Component Details
			boolean flag = false;
			if (objComp != null && objComp.size() > 0) {
				
				for (int i = 0; i < objComp.size(); i++) {
					
					ComponentVO objComponentVO = (ComponentVO) objComp.get(i);
					
					if (objComponentVO != null) {
						
						if (objComponentVO.getComponentSeqNo() == objComponentGroupRequestForm
								.getCompSeqNo()) {
							
							objComponentGroupRequestForm
							.setOldCompName(objComponentVO
									.getComponentName());
							objComponentGroupRequestForm
							.setOldCompDefaultFlag(String
									.valueOf(objComponentVO
											.isDefaultFlag()));
							objComponentGroupRequestForm
							.setNewCompName(objComponentVO
									.getComponentName());
							objComponentGroupRequestForm
							.setNewCompDefaultFlag(String
									.valueOf(objComponentVO
											.isDefaultFlag()));
							flag = true;
							break;
						}
						
					}
				}
				
			}
			
			if (!flag) {
				
				objComponentGroupRequestForm.setOldCompName("");
				objComponentGroupRequestForm.setOldCompDefaultFlag("false");
				objComponentGroupRequestForm.setNewCompName("");
				objComponentGroupRequestForm.setNewCompDefaultFlag("false");
			}
			
			//This is for getting request ID reason
			
			if (objRequest != null && objRequest.size() > 0) {
				
				for (int i = 0; i < objRequest.size(); i++) {
					RequestVO objReq = (RequestVO) objRequest.get(i);
					strReqUserID = objReq.getReqUserID();
				}
			}
			
			//This part is for checking logged in user ID and Request Created
			// User ID
			//If equal then user have provision to modify request, else only
			// preview available
			strUser = objLoginVo.getUserID();
			if (strReqUserID != null && strUser != null) {
				
				if (strReqUserID.equals(strUser)) {
					objComponentGroupRequestForm.setUserOwnRequest("Y");
				} else {
					objComponentGroupRequestForm.setUserOwnRequest("N");
				}
			}
			
			//Ends
			
			objComponentGroupRequestForm
			.setCompGroupChangeTypeSeqNo(objComponentGroupRequestForm
					.getCompGroupChangeTypeSeqNo());
			objComponentGroupRequestForm
			.setCompChangeTypeSeqNo(objComponentGroupRequestForm
					.getCompChangeTypeSeqNo());
			
			//taking color flag from the form
			if (objComponentGroupRequestForm.getClauseColorFlag() == null
					|| "".equals(objComponentGroupRequestForm
							.getClauseColorFlag()))
				objComponentGroupRequestForm.setClauseColorFlag("N");
			LogUtil.logMessage("intHdnSpecTypeSeqNo :" + intHdnSpecTypeNo);		
			LogUtil.logMessage("intHdnModelSeqNo :" + intHdnModelSeqNo);
			LogUtil.logMessage("intHdnSecSeqNo :" + intHdnSecSeqNo);
			LogUtil.logMessage("intHdnSubSecSeqNo :" + intHdnSubSecSeqNo);
			//Added For CR_80 to add Specification Type Dropdown
			objComponentGroupRequestForm.setSpecTypeNo(intHdnSpecTypeNo);
			// Added For CR_80 to add Specification Type Dropdown - Ends here
			objComponentGroupRequestForm.setModelSeqNo(intHdnModelSeqNo);
			objComponentGroupRequestForm.setSectionSeqNo(intHdnSecSeqNo);
			objComponentGroupRequestForm.setSubSectionSeqNo(intHdnSubSecSeqNo);
			
			//Added to take the user to the same working area
			objComponentGroupRequestForm.setTableID("Component");
			
			strForwardKey = ApplicationConstants.SUCCESS;
			//Adding for CR_94
			{
			RequestVO objNewReqVO = new RequestVO();
			objNewReqVO.setStatusTypeSeqNo(2);
			objNewReqVO.setLastName("All");
			objNewReqVO.setRequestID(0);
			ChangeRequestBI objNewChangeRequestBI = ServiceFactory.getChangeRequestBO();
			ArrayList arlSearchList = new ArrayList();
			arlSearchList = objNewChangeRequestBI.fetchRequestDetails(objNewReqVO);
			objComponentGroupRequestForm.setSearchList(arlSearchList);
			}
			//Ends here CR_94
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
	 * This method is for saving Component Group and component details the
	 * selected component details
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
	
	public ActionForward saveRequestComponentGroup(
			ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil
		.logMessage("Entering ComponentGroupRequestAction.saveRequestComponentGroup");
		String strForwardKey = null;
		ComponentGroupRequestForm objComponentGroupRequestForm = (ComponentGroupRequestForm) objActionForm;
		int intReqID = 0;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		//Added for changing alert functionality
		ActionForward actionRedirect = null;
		
		try {
			RequestCompGrpVO objRequestCompGrpVO = new RequestCompGrpVO();
			RequestCompVO objRequestCompVO = new RequestCompVO();
			RequestModelVO objRequestModelVO = new RequestModelVO();
			
			//Values taken for disabling fields
			int intCompGrpSeqNo = 0;
			String strOldCompGrpName = null;
			String strOldCompGrpChacFlag = null;
			String strOldCompGrpValidFlag = null;
			String strNewCompGrpName = null;
			String strNewCompGrpChacFlag = null;
			String strNewCompGrpValidFlag = null;
			int intCompSeqNo = 0;
			String strOldCompName = null;
			String strOldCompDefaultFlag = null;
			String strNewCompName = null;
			String strNewCompDefaultFlag = null;
			
			//Added for disabling Model, section and Sub section dropdown
			String strHdnModelSeqNo = null;
			String strHdnSecSeqNo = null;
			String strHdnSubSecSeqNo = null;
			//Added For CR_80 to add Specification Type Dropdown
			String strHdnSpecTypeNo = null;
			int intHdnSpecTypeNo = 0;
			// Added For CR_80 to add Specification Type Dropdown - Ends here
			int intHdnModelSeqNo = 0;
			int intHdnSecSeqNo = 0;
			int intHdnSubSecSeqNo = 0;
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if (objComponentGroupRequestForm.getHdnReqID() != null
					&& !"".equalsIgnoreCase(objComponentGroupRequestForm
							.getHdnReqID())) {
				
				intReqID = Integer.parseInt(objComponentGroupRequestForm
						.getHdnReqID().trim());
				
			}
			//Old Component Group Seq No
			if ((String) objHttpServletRequest.getParameter("hdnCompGrpSeqNo") != null
					&& !"".equals((String) objHttpServletRequest
							.getParameter("hdnCompGrpSeqNo"))) {
				
				intCompGrpSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("hdnCompGrpSeqNo"));
			}
			
			//Old Comp Seq No
			if ((String) objHttpServletRequest.getParameter("hdnCompSeqNo") != null
					&& !"".equals((String) objHttpServletRequest
							.getParameter("hdnCompSeqNo"))) {
				
				intCompSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("hdnCompSeqNo"));
			}
			
			//Old Component Group Details
			if ((String) objHttpServletRequest
					.getParameter("hdnOldCompGrpName") != null
					&& !"".equals((String) objHttpServletRequest
							.getParameter("hdnOldCompGrpName"))) {
				
				strOldCompGrpName = objHttpServletRequest.getParameter(
				"hdnOldCompGrpName").trim();
			}
			
			if ((String) objHttpServletRequest
					.getParameter("hdnOldCompGrpChacFlag") != null
					&& !"".equals((String) objHttpServletRequest
							.getParameter("hdnOldCompGrpChacFlag"))) {
				
				strOldCompGrpChacFlag = objHttpServletRequest
				.getParameter("hdnOldCompGrpChacFlag");
			}
			
			if ((String) objHttpServletRequest
					.getParameter("hdnOldCompGrpValidFlag") != null
					&& !"".equals((String) objHttpServletRequest
							.getParameter("hdnOldCompGrpValidFlag"))) {
				
				strOldCompGrpValidFlag = objHttpServletRequest
				.getParameter("hdnOldCompGrpValidFlag");
			}
			
			//	New Component Group Details
			if ((String) objHttpServletRequest
					.getParameter("hdnNewCompGrpName") != null
					&& !"".equals((String) objHttpServletRequest
							.getParameter("hdnNewCompGrpName"))) {
				
				strNewCompGrpName = objHttpServletRequest.getParameter(
				"hdnNewCompGrpName").trim();
			}
			
			if ((String) objHttpServletRequest
					.getParameter("hdnNewCompGrpChacFlag") != null
					&& !"".equals((String) objHttpServletRequest
							.getParameter("hdnNewCompGrpChacFlag"))) {
				
				strNewCompGrpChacFlag = objHttpServletRequest
				.getParameter("hdnNewCompGrpChacFlag");
			}
			
			if ((String) objHttpServletRequest
					.getParameter("hdnNewCompGrpValidFlag") != null
					&& !"".equals((String) objHttpServletRequest
							.getParameter("hdnNewCompGrpValidFlag"))) {
				
				strNewCompGrpValidFlag = objHttpServletRequest
				.getParameter("hdnNewCompGrpValidFlag");
			}
			
			//old Component Details
			if ((String) objHttpServletRequest.getParameter("hdnOldCompName") != null
					&& !"".equals((String) objHttpServletRequest
							.getParameter("hdnOldCompName"))) {
				
				strOldCompName = objHttpServletRequest.getParameter(
				"hdnOldCompName").trim();
			}
			
			if ((String) objHttpServletRequest
					.getParameter("hdnOldCompDefaultFlag") != null
					&& !"".equals((String) objHttpServletRequest
							.getParameter("hdnOldCompDefaultFlag"))) {
				
				strOldCompDefaultFlag = objHttpServletRequest
				.getParameter("hdnOldCompDefaultFlag");
			}
			
			//New Component Details
			if ((String) objHttpServletRequest.getParameter("hdnNewCompName") != null
					&& !"".equals((String) objHttpServletRequest
							.getParameter("hdnNewCompName"))) {
				
				strNewCompName = objHttpServletRequest.getParameter(
				"hdnNewCompName").trim();
			}
			
			if ((String) objHttpServletRequest
					.getParameter("hdnNewCompDefaultFlag") != null
					&& !"".equals((String) objHttpServletRequest
							.getParameter("hdnNewCompDefaultFlag"))) {
				
				strNewCompDefaultFlag = objHttpServletRequest
				.getParameter("hdnNewCompDefaultFlag");
			}
			
			//Added for disabling Model, section and Sub section dropdown
			strHdnModelSeqNo = (String) objHttpServletRequest
			.getParameter("hdnModelSeqNo");
			strHdnSecSeqNo = (String) objHttpServletRequest
			.getParameter("hdnSecSeqNo");
			strHdnSubSecSeqNo = (String) objHttpServletRequest
			.getParameter("hdnSubSecSeqNo");
			if (strHdnModelSeqNo != null && !"".equals(strHdnModelSeqNo)) {
				
				intHdnModelSeqNo = Integer.parseInt(strHdnModelSeqNo);
			}
			if (strHdnSecSeqNo != null && !"".equals(strHdnSecSeqNo)) {
				
				intHdnSecSeqNo = Integer.parseInt(strHdnSecSeqNo);
			}
			if (strHdnSubSecSeqNo != null && !"".equals(strHdnSubSecSeqNo)) {
				
				intHdnSubSecSeqNo = Integer.parseInt(strHdnSubSecSeqNo);
			}

			//Added For CR_80 to add Specification Type Dropdown	
			strHdnSpecTypeNo = (String) objHttpServletRequest
			.getParameter("hdnSpecTypeNo");
			if (strHdnSpecTypeNo != null && !"".equals(strHdnSpecTypeNo)) {
				
				intHdnSpecTypeNo = Integer.parseInt(strHdnModelSeqNo);
			}
			//Added For CR_80 to add Specification Type Dropdown - Ends here
			
			LogUtil.logMessage("intReqID :" + intReqID);
			LogUtil.logMessage("Model Seq No :" + intHdnModelSeqNo);
			LogUtil.logMessage("Sec Seq No :" + intHdnSecSeqNo);
			LogUtil.logMessage("Sub Sec Seq No :" + intHdnSubSecSeqNo);
			LogUtil.logMessage("Comp Group Change Type :"
					+ objComponentGroupRequestForm
					.getCompGroupChangeTypeSeqNo());
			LogUtil.logMessage("Component Grp Seq No Old :" + intCompGrpSeqNo);
			LogUtil.logMessage("Old Comp Group Name :" + strOldCompGrpName);
			LogUtil.logMessage("Old Comp Group Char Flag :"
					+ strOldCompGrpChacFlag);
			LogUtil.logMessage("Old Comp Group Valid Flag :"
					+ strOldCompGrpValidFlag);
			LogUtil.logMessage("New Comp Group Name :" + strNewCompGrpName);
			LogUtil.logMessage("New Comp Group Char Flag :"
					+ strNewCompGrpChacFlag);
			LogUtil.logMessage("New Comp Group Valid Flag :"
					+ strNewCompGrpValidFlag);
			
			LogUtil.logMessage("Comp Change Type :"
					+ objComponentGroupRequestForm.getCompChangeTypeSeqNo());
			LogUtil.logMessage("Component Seq No Old :" + intCompSeqNo);
			LogUtil.logMessage("Old Comp Name :" + strOldCompName);
			LogUtil.logMessage("Old Comp Default Flag :"
					+ strOldCompDefaultFlag);
			LogUtil.logMessage("New Comp Group Name :" + strNewCompName);
			LogUtil.logMessage("New Comp Group Char Flag :"
					+ strNewCompDefaultFlag);
			LogUtil.logMessage("Reason :"
					+ objComponentGroupRequestForm.getReason());
			
			LogUtil.logMessage("Apply Model Flag :"
					+ objComponentGroupRequestForm.isApplyModelFlag());
			
			//Component Info details
			objRequestCompVO.setChangeTypeSeqNo(objComponentGroupRequestForm
					.getCompChangeTypeSeqNo());
			objRequestCompVO.setOldCompSeqNo(intCompSeqNo);
			objRequestCompVO.setOldCompName(strOldCompName);
			objRequestCompVO.setOldCompDefaultFlag(strOldCompDefaultFlag);
			objRequestCompVO.setNewCompName(strNewCompName);
			objRequestCompVO.setNewCompDefaultFlag(strNewCompDefaultFlag);
			
			//Component Group Info Details
			objRequestCompGrpVO.setChangeTypeSeqNo(objComponentGroupRequestForm
					.getCompGroupChangeTypeSeqNo());
			objRequestCompGrpVO.setOldCompGrpSeqNo(intCompGrpSeqNo);
			objRequestCompGrpVO.setOldCompGrpName(strOldCompGrpName);
			objRequestCompGrpVO.setOldCompGrpChacFlag(strOldCompGrpChacFlag);
			objRequestCompGrpVO.setOldCompGrpValidFlag(strOldCompGrpValidFlag);
			objRequestCompGrpVO.setNewCompGrpName(strNewCompGrpName);
			objRequestCompGrpVO.setNewCompGrpChacFlag(strNewCompGrpChacFlag);
			objRequestCompGrpVO.setNewCompGrpValidFlag(strNewCompGrpValidFlag);
			objRequestCompGrpVO.setRequestCompVO(objRequestCompVO);
			
			/*
			 * Change for Model apply flag
			 */
			if (objComponentGroupRequestForm.isApplyModelFlag()) {
				
				objRequestModelVO.setApplyModelFlag(true);
			} else {
				objRequestModelVO.setApplyModelFlag(false);
			}
			
			objRequestModelVO.setModelSeqNo(intHdnModelSeqNo);
			objRequestModelVO.setSectionSeqNo(intHdnSecSeqNo);
			objRequestModelVO.setSubSectionSeqNo(intHdnSubSecSeqNo);
			
			objRequestModelVO.setRequestCompGrpVO(objRequestCompGrpVO);
			objRequestModelVO.setUserID(objLoginVo.getUserID());
			objRequestModelVO.setRequestID(intReqID);
			objRequestModelVO.setReason(objComponentGroupRequestForm
					.getReason());
			
			/**
			 * Added for Admin commnets & Request Desc update enchancement
			 */
			
			objRequestModelVO.setAdminComments(objComponentGroupRequestForm
					.getHdnAdminComments());
			objRequestModelVO.setRequestDesc(objComponentGroupRequestForm
					.getRequestDesc());
			
			ChangeRequestBI objChangeRequestBI = ServiceFactory
			.getChangeRequestBO();
			int intSuccess = objChangeRequestBI
			.insertCompGrpReqDetails(objRequestModelVO);
			
			if (intSuccess == 0) {
				objComponentGroupRequestForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objComponentGroupRequestForm.setMessageID("" + intSuccess);
			}
			
			//Added to take the user to the same working area
			objComponentGroupRequestForm.setTableID("");
			
			strForwardKey = ApplicationConstants.SUCCESS;
		} catch (Exception objExp) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		
		actionRedirect = new ActionForward(
				"CreateChangeReqClauseAction.do?method=initLoad" + "&reqID="
				+ intReqID, true /* REDIRECT */
		);
		
		LogUtil.logMessage("Alert Flag"
				+ objComponentGroupRequestForm.getAlertFlag());
		
		if (objComponentGroupRequestForm.getAlertFlag() != null
				&& objComponentGroupRequestForm.getAlertFlag().equals("Y")
				&& strForwardKey.equalsIgnoreCase(ApplicationConstants.SUCCESS)) {
			
			return actionRedirect;
			
		}
		if (strForwardKey.equalsIgnoreCase(ApplicationConstants.SUCCESS))
			return fetchComponentGroupDetails(objActionMapping, objActionForm,
					objHttpServletRequest, objHttpServletResponse);
		else
			return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***************************************************************************
	 * This method is for fetching Component Group and component details
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
	
	public ActionForward fetchComponentGroupDetails(
			ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil
		.logMessage("Entering ComponentGroupRequestAction.fetchReqCompGrpDetails");
		String strForwardKey = null;
		ComponentGroupRequestForm objComponentGroupRequestForm = (ComponentGroupRequestForm) objActionForm;
		int intReqID = 0;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		try {
			ArrayList arlSectionList = null;
			ArrayList arlSubSecList = null;
			ModelVo objModelVo = new ModelVo();
			SectionVO objSectionMaintVo = new SectionVO();
			SubSectionVO objSubSecMaintVO = new SubSectionVO();
			ComponentVO objComponentVo = new ComponentVO();
			RequestModelVO objRequestModelVO = null;
			RequestVO objRequestVO = new RequestVO();
			
			//Values taken for disabling fields
			int intCompGrpSeqNo = 0;
			int intCompGroupChangeTypeSeqNo = 0;
			String strOldCompGrpName = null;
			String strOldCompGrpChacFlag = null;
			String strOldCompGrpValidFlag = null;
			String strNewCompGrpName = null;
			String strNewCompGrpChacFlag = null;
			String strNewCompGrpValidFlag = null;
			
			int intCompSeqNo = 0;
			int intCompChangeTypeSeqNo = 0;
			String strOldCompName = null;
			String strOldCompDefaultFlag = null;
			String strNewCompName = null;
			String strNewCompDefaultFlag = null;
			
			String strClauseColorFlag = null;

			// Added For CR_80 to add Specification Type Dropdown
			int intSpecTypeNo = 0;
			// Added For CR_80 to add Specification Type Dropdown - Ends here
			int intModelSeqNo = 0;
			int intSecSeqNo = 0;
			int intSubSecSeqNo = 0;
			
			//This is for comparing logged in user ID and Request Created User
			// ID
			String strUser = null;
			String strReqUserID = null;
			
			ChangeRequestBI objChangeRequestBI = ServiceFactory
			.getChangeRequestBO();
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if ((String) objHttpServletRequest.getParameter("reqID") != null
					&& !"".equals((String) objHttpServletRequest
							.getParameter("reqID"))) {
				
				intReqID = Integer.parseInt(objHttpServletRequest.getParameter(
				"reqID").trim());
			}
			
			else if (objComponentGroupRequestForm.getHdnReqID() != null
					&& !"".equalsIgnoreCase(objComponentGroupRequestForm
							.getHdnReqID())) {
				
				intReqID = Integer.parseInt(objComponentGroupRequestForm
						.getHdnReqID().trim());
				
			}
			LogUtil.logMessage("intReqID :" + intReqID);
			
			objRequestVO.setUserID(objLoginVo.getUserID());
			objRequestVO.setRequestID(intReqID);

			ModelBI objModelBI = ServiceFactory.getModelBO();
			
			ArrayList objRequest = objChangeRequestBI
			.fetchRequestDetails(objRequestVO);
			/* Removed for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151
			///To set Not Valid and InProgress in same fashion
			RequestVO objReqVO = new RequestVO();
			ArrayList objjRequest = new ArrayList();
			
			for(int k=0;k<objRequest.size();k++){
				
				objReqVO=(RequestVO)objRequest.get(k);
			
			if(objReqVO.getStatusTypeSeqNo() == 7){
				
				objReqVO.setStatusTypeSeqNo(1);
				
			}
			objjRequest.add(objReqVO);
			
			}*/
			
			objComponentGroupRequestForm.setRequestDetails(objRequest);
			
			objRequestModelVO = objChangeRequestBI
			.fetchReqModelDetails(objRequestVO);
			
			//This logic is for getting the model seq no and pass to Section
			
			LogUtil.logMessage("objRequestModelVO :" + objRequestModelVO);
			
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
					
					objComponentGroupRequestForm.setApplyModelFlag(true);
				} else {
					objComponentGroupRequestForm.setApplyModelFlag(false);
				}
			}
			
			LogUtil.logMessage("intModelSeqNo :" + intModelSeqNo);
			LogUtil.logMessage("intSecSeqNo :" + intSecSeqNo);
			LogUtil.logMessage("intSubSecSeqNo :" + intSubSecSeqNo);
			
			//This part is for display submit button if model, sec and sub sec
			// details saved
			//Modified For CR_80 to add Specification Type Dropdown	
			if (intSpecTypeNo == 0 && intModelSeqNo == 0 && intSecSeqNo == 0 && intSubSecSeqNo == 0) {
				intSpecTypeNo = -1 ;
				objComponentGroupRequestForm.setReqModelSaved("N");
				
			} else {
				
				objComponentGroupRequestForm.setReqModelSaved("Y");
			}
			
			if (ApplicationConstants.ADMN.equals(objLoginVo.getRoleID())
					|| ApplicationConstants.MSE.equals(objLoginVo.getRoleID()))
				objComponentGroupRequestForm
				.setRoleID(ApplicationConstants.ADMN);//This is for
			// approve,
			// Reject, onhold,
			// complete enable
			else
				objComponentGroupRequestForm.setRoleID("OTHR");//ASM,SE
			
			/*
			 * int intReqStatusID = getRequestStatusID(objRequest);
			 * 
			 * if((intReqStatusID == ApplicationConstants.APPROVED_FOUR) ||
			 * (intReqStatusID ==ApplicationConstants.REJECTED_FIVE)){
			 * 
			 * objComponentGroupRequestForm.setApproveRejectHide("Y"); }
			 */
			//Ends
			LogUtil
			.logMessage("objComponentGroupRequestForm.getReqModelSaved() :"
					+ objComponentGroupRequestForm.getReqModelSaved());
			LogUtil.logMessage("objComponentGroupRequestForm.getRoleID() :"
					+ objComponentGroupRequestForm.getRoleID());

			objRequestModelVO = null;
			objRequestModelVO = objChangeRequestBI
			.fetchReqCompGrpDetails(objRequestVO);
			
			//This block is for getting all the values from VO and set in the
			// form
			if (objRequestModelVO != null) {
				
				if (objRequestModelVO.getRequestCompGrpVO() != null) {
					
					intCompGrpSeqNo = objRequestModelVO.getRequestCompGrpVO()
					.getOldCompGrpSeqNo();
					intCompGroupChangeTypeSeqNo = objRequestModelVO
					.getRequestCompGrpVO().getChangeTypeSeqNo();
					strOldCompGrpName = objRequestModelVO.getRequestCompGrpVO()
					.getOldCompGrpName();
					strOldCompGrpChacFlag = objRequestModelVO
					.getRequestCompGrpVO().getOldCompGrpChacFlag();
					strOldCompGrpValidFlag = objRequestModelVO
					.getRequestCompGrpVO().getOldCompGrpValidFlag();
					
					strNewCompGrpName = objRequestModelVO.getRequestCompGrpVO()
					.getNewCompGrpName();
					strNewCompGrpChacFlag = objRequestModelVO
					.getRequestCompGrpVO().getNewCompGrpChacFlag();
					strNewCompGrpValidFlag = objRequestModelVO
					.getRequestCompGrpVO().getNewCompGrpValidFlag();
					
				}
				
				if (objRequestModelVO.getRequestCompVO() != null) {
					
					intCompSeqNo = objRequestModelVO.getRequestCompVO()
					.getOldCompSeqNo();
					intCompChangeTypeSeqNo = objRequestModelVO
					.getRequestCompVO().getChangeTypeSeqNo();
					
					strOldCompName = objRequestModelVO.getRequestCompVO()
					.getOldCompName();
					strOldCompDefaultFlag = objRequestModelVO
					.getRequestCompVO().getOldCompDefaultFlag();
					
					if ("Y".equalsIgnoreCase(strOldCompDefaultFlag)) {
						strOldCompDefaultFlag = "true";
					} else if ("N".equalsIgnoreCase(strOldCompDefaultFlag)) {
						strOldCompDefaultFlag = "false";
					}
					strNewCompName = objRequestModelVO.getRequestCompVO()
					.getNewCompName();
					strNewCompDefaultFlag = objRequestModelVO
					.getRequestCompVO().getNewCompDefaultFlag();
					
					if ("Y".equalsIgnoreCase(strNewCompDefaultFlag)) {
						strNewCompDefaultFlag = "true";
					} else if ("N".equalsIgnoreCase(strNewCompDefaultFlag)) {
						
						strNewCompDefaultFlag = "false";
					}
					
				}
				
				//This is for Clause Color flag
				if (objRequestModelVO.getClaGrpLinkColorFlag() != null)
					strClauseColorFlag = objRequestModelVO
					.getClaGrpLinkColorFlag();
			}
			
			//This is for default set
			if (strNewCompGrpChacFlag == null
					|| "".equals(strNewCompGrpChacFlag))
				objComponentGroupRequestForm.setNewCompGrpChacFlag("N");
			
			if (strNewCompGrpValidFlag == null
					|| "".equals(strNewCompGrpValidFlag))
				objComponentGroupRequestForm.setNewCompGrpValidFlag("N");
			
			if (strNewCompDefaultFlag == null
					|| "".equals(strNewCompDefaultFlag))
				objComponentGroupRequestForm.setNewCompDefaultFlag("false");

			// Added For CR_80 to add Specification Type Dropdown
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objComponentGroupRequestForm.setSpecTypeList(arlSpec);
			}
			// Added For CR_80 to add Specification Type Dropdown - Ends here	
			
			objModelVo.setUserID(objLoginVo.getUserID());
			// Added For CR_80 to add Specification Type Dropdown
			objModelVo.setSpecTypeSeqNo(intSpecTypeNo);
			// Added For CR_80 to add Specification Type Dropdown - Ends here
			ArrayList objModel = objModelBI.fetchModels(objModelVo);
			
			objComponentGroupRequestForm.setModelList(objModel);
			
			objSectionMaintVo.setModelSeqNo(intModelSeqNo);
			objSectionMaintVo.setUserID(objLoginVo.getUserID());
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			arlSectionList = objSectionBO.fetchSections(objSectionMaintVo);
			if (arlSectionList != null) {
				objComponentGroupRequestForm.setListSections(arlSectionList);
			}
			
			ModelSubSectionBI objjSubSecMaintBO = ServiceFactory
			.getSubSecMaintBO();
			
			objSubSecMaintVO.setModelSeqNo(intModelSeqNo);
			objSubSecMaintVO.setSecSeqNo(intSecSeqNo);
			objSubSecMaintVO.setUserID(objLoginVo.getUserID());
			
			arlSubSecList = objjSubSecMaintBO
			.fetchSubSections(objSubSecMaintVO);
			
			objComponentGroupRequestForm.setSubSectionList(arlSubSecList);
			
			ModelCompBI objModelCompBI = ServiceFactory.getModelCompBO();
			
			objComponentVo.setModelSeqNo(intModelSeqNo);
			objComponentVo.setSubSectionSeqNo(intSubSecSeqNo);
			objComponentVo.setUserID(objLoginVo.getUserID());
			ArrayList objCompGroup = objModelCompBI
			.fetchModelComps(objComponentVo);
			objComponentGroupRequestForm.setCompGrpList(objCompGroup);
			
			LogUtil.logMessage("intCompGrpSeqNo :" + intCompGrpSeqNo);

			objComponentVo.setComponentGroupSeqNo(intCompGrpSeqNo);
			
			ModelCompMapBI objModelCompMapBI = ServiceFactory
			.getModelCompMapBO();
			
			ArrayList objCompOutter = objModelCompMapBI
			.fetchCompMap(objComponentVo);
			ArrayList objComp = new ArrayList();

			//Modified for CR_80 to check the arraylist size for greater than 1 bcoz of valid flag by RR68151
			if (objCompOutter != null && objCompOutter.size() > 1) {

				if ((ArrayList) objCompOutter.get(0) != null) {
					objComp = (ArrayList) objCompOutter.get(0);//All the Comp
					// Info//All the
					// Comp Info
				}
			}

			objComponentGroupRequestForm.setCompList(objComp);
			//This is for fetching the selected Component Group details
			objComponentGroupRequestForm.setOldCompGrpName(strOldCompGrpName);
			objComponentGroupRequestForm
			.setOldCompGrpChacFlag(strOldCompGrpChacFlag);
			objComponentGroupRequestForm
			.setOldCompGrpValidFlag(strOldCompGrpValidFlag);

			objComponentGroupRequestForm.setNewCompGrpName(strNewCompGrpName);
			objComponentGroupRequestForm
			.setNewCompGrpChacFlag(strNewCompGrpChacFlag);
			objComponentGroupRequestForm
			.setNewCompGrpValidFlag(strNewCompGrpValidFlag);
			
			//objComponentGroupRequestForm.setCompGroupDetail(objCompGroupDetail);
			
			//This is for selected Component Details
			
			objComponentGroupRequestForm.setOldCompName(strOldCompName);
			objComponentGroupRequestForm
			.setOldCompDefaultFlag(strOldCompDefaultFlag);
			objComponentGroupRequestForm.setNewCompName(strNewCompName);
			objComponentGroupRequestForm
			.setNewCompDefaultFlag(strNewCompDefaultFlag);
			
			//This is for displaying reason
			
			if (objRequest != null && objRequest.size() > 0) {
				
				for (int i = 0; i < objRequest.size(); i++) {
					RequestVO objReq = (RequestVO) objRequest.get(i);
					objComponentGroupRequestForm.setReason(objReq.getReason());
					objComponentGroupRequestForm.setAdminComments(objReq
							.getAdminComments());
					strReqUserID = objReq.getReqUserID();
					objComponentGroupRequestForm.setRequestDesc(objReq
							.getRequestDesc());
					
				}
			}
			
			//This part is for checking logged in user ID and Request Created
			// User ID
			//If equal then user have provision to modify request, else only
			// preview available
			strUser = objLoginVo.getUserID();
			if (strReqUserID != null && strUser != null) {
				
				if (strReqUserID.equals(strUser)) {
					objComponentGroupRequestForm.setUserOwnRequest("Y");
				} else {
					objComponentGroupRequestForm.setUserOwnRequest("N");
				}
			}
			
			//Ends

			/* Modified For CR_80 to add Specification Type Dropdown

			if (intModelSeqNo == 0)
				objComponentGroupRequestForm.setModelSeqNo(-1);
			else*/
			//Added For CR_80 to add Specification Type Dropdown
			if (intSpecTypeNo == 0)
			objComponentGroupRequestForm.setSpecTypeNo(-1);
			else
			objComponentGroupRequestForm.setSpecTypeNo(intSpecTypeNo);
			// Added For CR_80 to add Specification Type Dropdown - Ends here
			objComponentGroupRequestForm.setModelSeqNo(intModelSeqNo);
			
			objComponentGroupRequestForm.setSectionSeqNo(intSecSeqNo);
			objComponentGroupRequestForm.setSubSectionSeqNo(intSubSecSeqNo);
			objComponentGroupRequestForm.setCompGrpSeqNo(intCompGrpSeqNo);
			objComponentGroupRequestForm.setCompSeqNo(intCompSeqNo);
			//Modified for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151
			if (intCompGroupChangeTypeSeqNo == 0)
				objComponentGroupRequestForm.setCompGroupChangeTypeSeqNo(7);
			else
				objComponentGroupRequestForm
				.setCompGroupChangeTypeSeqNo(intCompGroupChangeTypeSeqNo);
			
			LogUtil
			.logMessage("objComponentGroupRequestForm.getCompChangeTypeSeqNo() :"
					+ objComponentGroupRequestForm
					.getCompChangeTypeSeqNo());

			//Modified for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151
			if (intCompChangeTypeSeqNo == 0)
				objComponentGroupRequestForm.setCompChangeTypeSeqNo(7);
			else
				objComponentGroupRequestForm
				.setCompChangeTypeSeqNo(intCompChangeTypeSeqNo);
			
			LogUtil.logMessage("strClauseColorFlag :" + strClauseColorFlag);

			if (strClauseColorFlag == null || "".equals(strClauseColorFlag))
				objComponentGroupRequestForm.setClauseColorFlag("N");//Clause
			// Color
			// Flag
			else
				objComponentGroupRequestForm
				.setClauseColorFlag(strClauseColorFlag);
			//Adding for CR_94
			{
			LogUtil.logMessage("in search list for request id page...");
			RequestVO objNewReqVO = new RequestVO();
			objNewReqVO.setStatusTypeSeqNo(2);
			objNewReqVO.setLastName("All");
			objNewReqVO.setRequestID(0);
			ChangeRequestBI objNewChangeRequestBI = ServiceFactory.getChangeRequestBO();
			ArrayList arlSearchList = new ArrayList();
			arlSearchList = objNewChangeRequestBI.fetchRequestDetails(objNewReqVO);
			objComponentGroupRequestForm.setSearchList(arlSearchList);
			LogUtil.logMessage("end of search list for request id page...");
			}
			//Ends here CR_94
			strForwardKey = ApplicationConstants.SUCCESS;
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
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil.logMessage("Entering ComponentGroupRequestAction.resetRequest");
		String strForwardKey = null;
		ComponentGroupRequestForm objComponentGroupRequestForm = (ComponentGroupRequestForm) objActionForm;
		int intReqID = 0;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		int intSuccess = 0;
		try {
			
			RequestVO objRequestVO = new RequestVO();
			
			ChangeRequestBI objChangeRequestBI = ServiceFactory
			.getChangeRequestBO();
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if (objComponentGroupRequestForm.getHdnReqID() != null
					&& !"".equalsIgnoreCase(objComponentGroupRequestForm
							.getHdnReqID())) {
				
				intReqID = Integer.parseInt(objComponentGroupRequestForm
						.getHdnReqID().trim());
				
			}
			LogUtil.logMessage("intReqID :" + intReqID);
			
			objRequestVO.setUserID(objLoginVo.getUserID());
			objRequestVO.setRequestID(intReqID);
			
			intSuccess = objChangeRequestBI.resetRequest(objRequestVO);
			
			if (intSuccess == 0) {
				objComponentGroupRequestForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objComponentGroupRequestForm.setMessageID("" + intSuccess);
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
			return fetchComponentGroupDetails(objActionMapping, objActionForm,
					objHttpServletRequest, objHttpServletResponse);
		else
			return objActionMapping.findForward(strForwardKey);
		
		//return objActionMapping.findForward(strForwardKey);
		
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
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil
		.logMessage("Entering ComponentGroupRequestAction.saveRequestStatus");
		String strForwardKey = null;
		ComponentGroupRequestForm objComponentGroupRequestForm = (ComponentGroupRequestForm) objActionForm;
		int intReqID = 0;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		int intSuccess = 0;
		int intStatusSeqNo = 0;
		try {
			
			RequestVO objRequestVO = new RequestVO();
			
			ChangeRequestBI objChangeRequestBI = ServiceFactory
			.getChangeRequestBO();
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if (objComponentGroupRequestForm.getHdnReqID() != null
					&& !"".equalsIgnoreCase(objComponentGroupRequestForm
							.getHdnReqID())) {
				
				intReqID = Integer.parseInt(objComponentGroupRequestForm
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
			objRequestVO.setAdminComments(objComponentGroupRequestForm
					.getHdnAdminComments());
			objRequestVO.setUserID(objLoginVo.getUserID());
			objRequestVO.setRequestID(intReqID);
			
			intSuccess = objChangeRequestBI.saveRequestStatus(objRequestVO);
			
			if (intSuccess == 0) {
				objComponentGroupRequestForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objComponentGroupRequestForm.setMessageID("" + intSuccess);
			}
			
			//Added to take the user to the same working area
			objComponentGroupRequestForm.setTableID("");
			
			strForwardKey = ApplicationConstants.SUCCESS;
		} catch (Exception objExp) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		
		if (strForwardKey.equalsIgnoreCase(ApplicationConstants.SUCCESS))
			return fetchComponentGroupDetails(objActionMapping, objActionForm,
					objHttpServletRequest, objHttpServletResponse);
		else
			return objActionMapping.findForward(strForwardKey);
		
		//return objActionMapping.findForward(strForwardKey);
		
	}
	
	//Added for CR_80 CR Form Enhancements III on 25-Nov-09 by RR68151
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
	public ActionForward fetchEffectedClauses(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil.logMessage("Entering ComponentGroupRequestAction.fetchEffectedClauses");
		String strForwardKey = ApplicationConstants.CRFORM_EFFECTED_CLAUSES;
		ComponentGroupRequestForm objComponentGroupRequestForm = (ComponentGroupRequestForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		
		String strUserID=null;
		ComponentVO objComponentVO = new ComponentVO();
		ArrayList arlCompGrpDtls = new ArrayList();
		int intHdnModelSeqNo = 0;
		int intHdnCompGrpSeqNo = 0;
		int intHdnCompSeqNo = 0;
		try {
			
			if(objLoginVo != null){
				strUserID=objLoginVo.getUserID();
				
			}

			if ((String) objHttpServletRequest.getParameter("hdnModelSeqNo") != null 
					&& !"".equals((String) objHttpServletRequest
							.getParameter("hdnModelSeqNo"))) {
				
				intHdnModelSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("hdnModelSeqNo"));
			}
			
			if ((String) objHttpServletRequest.getParameter("hdnCompGrpSeqNo") != null
					&& !"".equals((String) objHttpServletRequest
							.getParameter("hdnCompGrpSeqNo"))) {
				
				intHdnCompGrpSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("hdnCompGrpSeqNo"));
			}
			
			if ((String) objHttpServletRequest.getParameter("hdnCompSeqNo") != null
					&& !"".equals((String) objHttpServletRequest
							.getParameter("hdnCompSeqNo"))) {
				
				intHdnCompSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("hdnCompSeqNo"));
			}
			
			objComponentVO.setModelSeqNo(intHdnModelSeqNo);
			objComponentVO.setComponentGroupSeqNo(intHdnCompGrpSeqNo);
			objComponentVO.setComponentSeqNo(intHdnCompSeqNo);
			objComponentVO.setUserID(strUserID);
			
			ModelCompMapBI objModelCompMapBO=ServiceFactory.getModelCompMapBO();
			arlCompGrpDtls=objModelCompMapBO.fetchCompClauseMapDtls(objComponentVO);
			
			if(arlCompGrpDtls!=null && arlCompGrpDtls.size()>0){

				CompGroupVO objjCompGrpVO = (CompGroupVO)arlCompGrpDtls.get(0);
				
				objComponentGroupRequestForm.setHdnSelModel(objjCompGrpVO.getModelName());
				objComponentGroupRequestForm.setHdnSelSec(objjCompGrpVO.getSecCode()+"."+objjCompGrpVO.getSectionName());
				objComponentGroupRequestForm.setHdnSelSubSec(objjCompGrpVO.getSecCode()+"."+objjCompGrpVO.getSubSectionCode()+"."+objjCompGrpVO.getSubSectionName());
				objComponentGroupRequestForm.setSubSectionSeqNo(objjCompGrpVO.getSubSectionSeqNo());
				objComponentGroupRequestForm.setValidFlag(objjCompGrpVO.getLeadFlag());
				objComponentGroupRequestForm.setHdnSelCompGrp(objjCompGrpVO.getComponentGroupName());
				
				LogUtil.logMessage("SubSection "+objComponentGroupRequestForm.getSubSectionSeqNo());
				
				objComponentGroupRequestForm.setCompGrpSeqNo(objjCompGrpVO.getComponentGroupSeqNo());
				
				//Contains ArrayList of ComponentVO's
				objComponentGroupRequestForm.setCompList(objjCompGrpVO.getComponent());
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
	//Added for CR_80 CR Form Enhancements III on 25-Nov-09 by RR68151 - Ends Here
	
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
		
		LogUtil.logMessage("Inside the ComponentGroupRequestAction:fetchModels");
		String strForwardKey = ApplicationConstants.SUCCESS;
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		try {
			
			ModelVo objModelVo = new ModelVo();
			RequestVO objRequestVO = new RequestVO();
			RequestModelVO objRequestModelVO = null;
			ComponentGroupRequestForm objComponentGroupRequestForm = (ComponentGroupRequestForm) objActionForm;
			int intModelSeqNo = 0;
			int intSecSeqNo = 0;
			int intSubSecSeqNo = 0;
			int intSpecTypeNo = 0;
			int intReqID = 0;
			String strHdnSpecTypeNo = null;
			int intHdnSpecTypeNo = 0;
			
			//This is for comparing logged in user ID and Request Created User ID
			String strUser = null;
			String strReqUserID = null;
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if (objComponentGroupRequestForm.getHdnReqID() != null
					&& !"".equalsIgnoreCase(objComponentGroupRequestForm
							.getHdnReqID())) {
				
				intReqID = Integer.parseInt(objComponentGroupRequestForm
						.getHdnReqID().trim());
				
			}
			
			LogUtil.logMessage("intReqID :" + intReqID);
			
			objRequestVO.setUserID(objLoginVo.getUserID());
			objRequestVO.setRequestID(intReqID);
			
			ChangeRequestBI objChangeRequestBI = ServiceFactory
			.getChangeRequestBO();
			
			ArrayList objRequest = objChangeRequestBI
			.fetchRequestDetails(objRequestVO);
			
			objComponentGroupRequestForm.setRequestDetails(objRequest);
	
			//This logic is for getting the model seq no and pass to Section
			objRequestModelVO = objChangeRequestBI
			.fetchReqModelDetails(objRequestVO);
			LogUtil.logMessage("objRequestModelVO :" + objRequestModelVO);
			
			if (objRequestModelVO != null) {
				intSpecTypeNo = objRequestModelVO.getSpecTypeNo();
				intModelSeqNo = objRequestModelVO.getModelSeqNo();
				intSecSeqNo = objRequestModelVO.getSectionSeqNo();
				intSubSecSeqNo = objRequestModelVO.getSubSectionSeqNo();
			}
			
			
			//This part is for display submit button if model, sec and sub sec
			// details saved
			
			if (intSpecTypeNo == 0 && intModelSeqNo == 0 && intSecSeqNo == 0 && intSubSecSeqNo == 0) {
				
				objComponentGroupRequestForm.setReqModelSaved("N");
				
			} else {
				
				objComponentGroupRequestForm.setReqModelSaved("Y");
			}
			
			if (ApplicationConstants.ADMN.equals(objLoginVo.getRoleID())
					|| ApplicationConstants.MSE.equals(objLoginVo.getRoleID()))
				objComponentGroupRequestForm
				.setRoleID(ApplicationConstants.ADMN);//This is for
			// approve,
			// Reject, onhold,
			// complete enable
			else
				objComponentGroupRequestForm.setRoleID("OTHR");//ASM,SE
			//Ends

			//Added For CR_80 to add Specification Type Dropdown	
			strHdnSpecTypeNo = (String) objHttpServletRequest
			.getParameter("hdnSpecTypeNo");
			if (strHdnSpecTypeNo != null && !"".equals(strHdnSpecTypeNo)) {
				
				intHdnSpecTypeNo = Integer.parseInt(strHdnSpecTypeNo);
			}
			//Added For CR_80 to add Specification Type Dropdown - Ends here
		
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objComponentGroupRequestForm.setSpecTypeList(arlSpec);
			}
			
			objModelVo.setUserID(objLoginVo.getUserID());
			objModelVo.setSpecTypeSeqNo(intHdnSpecTypeNo);
			ModelBI objModelBO = ServiceFactory.getModelBO();
			if (objModelBO.fetchModels(objModelVo) != null) {
				ArrayList arlModelList = objModelBO.fetchModels(objModelVo);
				objComponentGroupRequestForm.setModelList(arlModelList);
			}
						
			//This is for getting request ID reason
			
			if (objRequest != null && objRequest.size() > 0) {
				
				for (int i = 0; i < objRequest.size(); i++) {
					RequestVO objReq = (RequestVO) objRequest.get(i);
					strReqUserID = objReq.getReqUserID();
				}
			}
			
			//This part is for checking logged in user ID and Request Created
			// User ID
			//If equal then user have provision to modify request, else only
			// preview available
			strUser = objLoginVo.getUserID();
			if (strReqUserID != null && strUser != null) {
				
				if (strReqUserID.equals(strUser)) {
					objComponentGroupRequestForm.setUserOwnRequest("Y");
				} else {
					objComponentGroupRequestForm.setUserOwnRequest("N");
				}
			}
			
			//Ends
			
			//taking color flag from the form
			if (objComponentGroupRequestForm.getClauseColorFlag() == null
					|| "".equals(objComponentGroupRequestForm
							.getClauseColorFlag()))
				objComponentGroupRequestForm.setClauseColorFlag("N");
			//Default option set as Not Required
			objComponentGroupRequestForm.setCompGroupChangeTypeSeqNo(7);
			//Default set as Not Required
			objComponentGroupRequestForm.setCompChangeTypeSeqNo(7);
			//Default set as Not Required
			objComponentGroupRequestForm.setNewCompGrpName("");
			objComponentGroupRequestForm.setNewCompName("");
			
			objComponentGroupRequestForm.setNewCompGrpChacFlag("N");
			objComponentGroupRequestForm.setNewCompGrpValidFlag("N");
			objComponentGroupRequestForm.setNewCompDefaultFlag("false");
		
			LogUtil
			.logMessage("SpecTypeSeqNo " + intHdnSpecTypeNo);
			//Adding for CR_94
			{
			RequestVO objNewReqVO = new RequestVO();
			objNewReqVO.setStatusTypeSeqNo(2);
			objNewReqVO.setLastName("All");
			objNewReqVO.setRequestID(0);
			ChangeRequestBI objNewChangeRequestBI = ServiceFactory.getChangeRequestBO();
			ArrayList arlSearchList = new ArrayList();
			arlSearchList = objNewChangeRequestBI.fetchRequestDetails(objNewReqVO);
			objComponentGroupRequestForm.setSearchList(arlSearchList);
			}
			//Ends here CR_94
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ComponentGroupRequestAction:fetchModels");
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