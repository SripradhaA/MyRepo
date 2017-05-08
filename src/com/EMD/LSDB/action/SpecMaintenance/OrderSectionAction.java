/**
 * 
 */
package com.EMD.LSDB.action.SpecMaintenance;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONObject;

import com.EMD.LSDB.action.common.EMDAction;
import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.MainFeatureInfoBI;
import com.EMD.LSDB.bo.serviceinterface.ModelAssignEdlBI;
import com.EMD.LSDB.bo.serviceinterface.OrderBI;
import com.EMD.LSDB.bo.serviceinterface.OrderClauseBI;
import com.EMD.LSDB.bo.serviceinterface.OrderSectionBI;
import com.EMD.LSDB.bo.serviceinterface.SpecStatusBI;
import com.EMD.LSDB.bo.serviceinterface.SpecTypeBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.constant.DatabaseConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.form.SpecMaintenance.MainFeatureInfoForm;
import com.EMD.LSDB.form.SpecMaintenance.OrderSectionForm;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.MainFeatureInfoVO;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.RevisionVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SpecTypeVo;

/**
 * @author ps57222
 *  
 */
/***************************************************************************
 * ------------------------------------------------------------------------------------------------------
 *     Date         version  modified by        comments                              Remarks 
 * 03/05/2010        1.0      SD41630        remove the reserved clause        Added for CR_86
 * 											 and refresh the same order.	
 * 16/03/2012        1.1      SD41630        Added a turnON /OFF method for 
 *                                           CR_106 for display logos 
 *                                           (customer and distributor)        Added for CR_86
 * 											 	
 * ----------------------------------------------------------------------------------------------------------
 **************************************************************************/
public class OrderSectionAction extends EMDAction {
	
	/***************************************************************************
	 * This method is Used for Fetching Sections in order level
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
		
		LogUtil.logMessage("Enters into OrderSectionAction:fetchSections");
		String strForwardKey = ApplicationConstants.SUCCESS;
		
		ArrayList arlSectionList = new ArrayList();
		SectionVO objSectionVO = new SectionVO();
		OrderSectionForm objOrderSectionForm = (OrderSectionForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LogUtil.logMessage(objSession);
		/** ******* Used for test Work ********* */
		
		int intOrdeKey = 1;
		
		//objSectionVO.setOrderKey(intOrdeKey);
		
		/** ******************************** */
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			objSectionVO.setUserID(objLoginVo.getUserID());
			objSectionVO.setOrderKey(intOrdeKey);
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null) {
				objOrderSectionForm.setSectionList(arlSectionList);
			}
			LogUtil.logMessage("ArrayList Value in OrderSectionAction:"
					+ objOrderSectionForm.getSectionList().size());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in OrderSectionAction..");
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
	 * This method is Used for Fetching Sections Details in order level
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
	
	public ActionForward fetchSectionDetails(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil
		.logMessage("Enters into OrderSectionAction:fetchSectionDetails");
		String strForwardKey = ApplicationConstants.SECTION_VIEW;
		
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlSectionDetails = null;
		ArrayList arlOrderList = null;
		ArrayList arlRevList = null;
		SectionVO objSectionVO = new SectionVO();
		OrderVO objOrderVO = null;
		OrderSectionForm objOrderSectionForm = (OrderSectionForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LogUtil.logMessage(objSession);
		
		/** ******* Used for test Work ********* */
		
		int intOrdeKey = 0;
		int intSecSeqNo = 0;
		int intRevCode = 0;
		String strSecCode = null;
		String strSecName = null;
		int intSpecStatusCode = 0;
		int intReqID=0;//Added for CR_97
		
		//objSectionVO.setOrderKey(intOrdeKey);
		
		/** ******************************** */
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			//Added for bringing back to the visited Save Component
			
			if ((String) objHttpServletRequest.getParameter("subsecno") != null) {
				
				objHttpServletRequest
				.setAttribute("subsecseqno",
						(String) objHttpServletRequest
						.getParameter("subsecno"));
				
			}
			//Ends here
			
			if ((String) objHttpServletRequest.getParameter("orderKey") != null) {
				
				intOrdeKey = Integer.parseInt(objHttpServletRequest
						.getParameter("orderKey"));
				
			} else {
				
				intOrdeKey = objOrderSectionForm.getOrderKey();
				
			}
			if ((String) objHttpServletRequest.getParameter("secSeq") != null) {
				
				intSecSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("secSeq"));
				
			} else {
				
				intSecSeqNo = objOrderSectionForm.getOrderSecSeqNo();
				
			}
			/*
			 * if((String)objHttpServletRequest.getParameter("secCode")!=null){
			 * 
			 * strSecCode = objHttpServletRequest.getParameter("secCode");
			 * 
			 * }else{
			 * 
			 * strSecCode = objOrderSectionForm.getOrderSecCode(); }
			 */
			
			/*
			 * if((String)objHttpServletRequest.getParameter("secName")!=null){
			 * 
			 * strSecName = objHttpServletRequest.getParameter("secName");
			 * }else{
			 * 
			 * strSecName = objOrderSectionForm.getOrderSecName(); }
			 */
			
			/*	if (objOrderSectionForm.getRevCode() == 0) {
			 
			 intRevCode = 1;//Default No revision
			 
			 } else*/
			if ((String) objHttpServletRequest.getParameter("revCode") != null) {
				
				intRevCode = Integer.parseInt(objHttpServletRequest
						.getParameter("revCode"));
			}
			
			else {
				intRevCode = 1;
			}
			/* Added for CR_97 for NEW CR Form Req ID*/
			if ((String) objHttpServletRequest.getParameter("newReqID") != null) {
				
				intReqID = Integer.parseInt(objHttpServletRequest.getParameter("newReqID"));
				objOrderSectionForm.setNewReqID(intReqID);
				
			} 
			/* CR_97 Ends here*/
			objOrderSectionForm.setOrderKey(intOrdeKey);
			objOrderSectionForm.setOrderSecCode(strSecCode);
			objOrderSectionForm.setOrderSecName(strSecName);
			objOrderSectionForm.setOrderSecSeqNo(intSecSeqNo);
			objOrderSectionForm.setRevCode(intRevCode);
			
			objSectionVO.setUserID(objLoginVo.getUserID());
			objSectionVO.setSectionSeqNo(intSecSeqNo);
			objSectionVO.setSectionCode(strSecCode);
			objSectionVO.setSectionName(strSecName);
			objSectionVO.setOrderKey(intOrdeKey);
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSectionVO.setRevisionInput(intRevCode);//default set as No
			// revision
			//objSectionVO.setSectionSeqNo();
			
			/**
			 * populating the Revision drop down code starts here.
			 */
			
			RevisionVO objRevisionVO = new RevisionVO();
			objRevisionVO.setUserID(objLoginVo.getUserID());
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			
			arlRevList = objOrderSectionBI.fetchRevision(objRevisionVO);
			
			if (arlRevList != null) {
				objOrderSectionForm.setRevisionList(arlRevList);
			}
			
			/**
			 * Ends here
			 */
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null) {
				objOrderSectionForm.setSectionList(arlSectionList);
			}
			
			//Added for CR_109 to bring New Subsections to Order
			objSectionVO.setNewSubSecFlag(ApplicationConstants.YES);
			//Added for CR_109 to bring New Subsections to Order
			arlSectionDetails = objOrderSectionBI
			.fetchSectionDetails(objSectionVO);
			
			objOrderSectionForm.setSecDetail(arlSectionDetails);
					
			objOrderVO = new OrderVO();
						
			objOrderVO.setOrderKey(intOrdeKey);
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			
			objOrderSectionForm.setOrderList(arlOrderList);
			//This is for getting the Spec Status Code to be passed to get the
			// next status
			OrderVO objOrderVOSpecStatus = null;
			if (arlOrderList != null && arlOrderList.size() > 0) {
				for (int i = 0; i < arlOrderList.size(); i++) {
					
					objOrderVOSpecStatus = (OrderVO) arlOrderList.get(i);
					intSpecStatusCode = objOrderVOSpecStatus
					.getSpecStatusCode();
					//					Added for CR-10 display of Reason based on Spec Status Code
					objOrderSectionForm.setCurrentSpecStatus(intSpecStatusCode);
								objOrderVO.setDynamicNoFlag(objOrderVOSpecStatus.getDynamicNoFlag());
				}
				
			}
			objOrderVOSpecStatus.setUserID(objLoginVo.getUserID());
			SpecStatusBI objSpecStatusBI = ServiceFactory.getSpecStatusBO();
			ArrayList arlSpecStatus = objSpecStatusBI
			.fetchSpecNextStatus(objOrderVOSpecStatus);
			objOrderSectionForm.setSpecStatus(arlSpecStatus);
			objOrderSectionForm.setHdnRevViewCode(String.valueOf(intRevCode));
			objOrderSectionForm.setRevCode(intRevCode);

			//Added For CR_84
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			OrderVO objjOrderVO = (OrderVO) arlOrderList.get(0);
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			objSpecTypeVo.setSpectypeSeqno(objjOrderVO.getSpecTypeSeqNo());
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			ArrayList arlSpecType = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpecType.size() != 0) {
				
				SpecTypeVo objjSpecTypeVo = (SpecTypeVo) arlSpecType.get(0);
				
				if(objjSpecTypeVo.isPerfCurveMaintDisPlayFlag())
					objOrderSectionForm.setPerfCurveLinkFlag(ApplicationConstants.YES);
				else
					objOrderSectionForm.setPerfCurveLinkFlag(ApplicationConstants.NO);
			}
			
			//Added For CR_84 - Ends here
			
			//Added for CR-121
			objOrderSectionForm.setRoleID(objLoginVo.getRoleID());
			
		} catch (Exception objEx) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***************************************************************************
	 * This method is Used for saving the component in order level
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
	
	public ActionForward saveComponent(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into OrderSectionAction:saveComponent");
		String strForwardKey = ApplicationConstants.SECTION_VIEW;
		OrderVO objOrderVO = null;
		ArrayList arlOrderList = null;
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlSectionDetails = null;
		ArrayList arlRevList = null;
		SectionVO objSectionVO = new SectionVO();
		HttpSession objSession = objHttpServletRequest.getSession(false);
		OrderSectionForm objOrderSectionForm = (OrderSectionForm) objActionForm;
		int intOrdeKey = 0;
		int intSecSeqNo = 0;
		int intRevCode = 0;
		String strSecCode = null;
		String strSecName = null;
						
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
						
			objOrderVO = new OrderVO();
			
			//Added for bringing back to the visited Save Component
			
			if ((String) objHttpServletRequest.getParameter("subsecno") != null) {
				
				objHttpServletRequest
				.setAttribute("subsecseqno",
						(String) objHttpServletRequest
						.getParameter("subsecno"));
				
			}
			//Ends here
			
			if ((String) objHttpServletRequest.getParameter("orderKey") != null) {
				
				intOrdeKey = Integer.parseInt(objHttpServletRequest
						.getParameter("orderKey"));
				
			} else {
				
				intOrdeKey = objOrderSectionForm.getOrderKey();
				
			}
			if ((String) objHttpServletRequest.getParameter("secSeq") != null) {
				
				intSecSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("secSeq"));
				
			} else {
				
				intSecSeqNo = objOrderSectionForm.getOrderSecSeqNo();
				
			}
			if ((String) objHttpServletRequest.getParameter("secCode") != null) {
				
				strSecCode = objHttpServletRequest.getParameter("secCode");
				
			} else {
				
				strSecCode = objOrderSectionForm.getOrderSecCode();
			}
			
			if ((String) objHttpServletRequest.getParameter("secName") != null) {
				
				strSecName = objHttpServletRequest.getParameter("secName");
			} else {
				
				strSecName = objOrderSectionForm.getOrderSecName();
			}
			
			if (objOrderSectionForm.getRevCode() == 0) {
				
				intRevCode = 1;//Default No revision
				
			} else {
				intRevCode = objOrderSectionForm.getRevCode();
			}
		
			objOrderSectionForm.setOrderKey(intOrdeKey);
			objOrderSectionForm.setOrderSecCode(strSecCode);
			objOrderSectionForm.setOrderSecName(strSecName);
			objOrderSectionForm.setOrderSecSeqNo(intSecSeqNo);
			objOrderSectionForm.setRevCode(intRevCode);
			
			objSectionVO.setUserID(objLoginVo.getUserID());
			objSectionVO.setSectionSeqNo(intSecSeqNo);
			objSectionVO.setOrderKey(intOrdeKey);
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSectionVO.setRevisionInput(intRevCode);//default set as No
			// revision
			
				objSectionVO.setComponentGroupSeqNo(objOrderSectionForm
					.getComponentGroupSeqNo());
			
			objSectionVO.setCompSeqNo(objOrderSectionForm.getCompSeqNo());
			
			//Remove it
			for (int i = 0; i < objSectionVO.getComponentGroupSeqNo().length; i++) {
				
				LogUtil.logMessage("Component Group Seq No :"
						+ objSectionVO.getComponentGroupSeqNo()[i]);
			}
			for (int i = 0; i < objSectionVO.getCompSeqNo().length; i++) {
				
				LogUtil.logMessage("Component Seq No :"
						+ objSectionVO.getCompSeqNo()[i]);
			}
			
			//Ends
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			//Get all the components
			
			SectionVO objSection = objOrderSectionBI.saveComponent(objSectionVO);
			
			//Added for LSDB_CR_71 for Server Side Component Validation
			if(objSection!=null){
								
				if(objSection.getMessageID()==0){
					
					objOrderSectionForm.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
					
				}else{
					
					objOrderSectionForm.setCompErrorMessage(objSection.getMessage());
					
				}
			}
			
			//Commented out for LSDB_CR_71 for Server Side Component Validation			
			/*if (intSuccess == 0) {
				objOrderSectionForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objOrderSectionForm.setMessageID("" + intSuccess);
			}
			*/
			//Ends
			
			//This is for displaying section list
			
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null) {
				objOrderSectionForm.setSectionList(arlSectionList);
			}
			
			/**
			 * populating the Revision drop down code starts here.
			 */
			
			RevisionVO objRevisionVO = new RevisionVO();
			objRevisionVO.setUserID(objLoginVo.getUserID());
			
			arlRevList = objOrderSectionBI.fetchRevision(objRevisionVO);
			
			if (arlRevList != null) {
				objOrderSectionForm.setRevisionList(arlRevList);
			}
			/**
			 * Ends Here
			 */
			//Added for CR_109 to bring New Subsections to Order
			objSectionVO.setNewSubSecFlag(ApplicationConstants.YES);
			//Added for CR_109 to bring New Subsections to Order
			arlSectionDetails = objOrderSectionBI
			.fetchSectionDetails(objSectionVO);
			objOrderSectionForm.setSecDetail(arlSectionDetails);
			objOrderVO.setOrderKey(objOrderSectionForm.getOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			
			//Displaying Order Info
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objOrderSectionForm.setOrderList(arlOrderList);
			
			//This is for getting the Spec Status Code to be passed to get the
			// next status
			int intSpecStatusCode = 0;
			OrderVO objOrderVOSpecStatus = null;
			if (arlOrderList != null && arlOrderList.size() > 0) {
				for (int i = 0; i < arlOrderList.size(); i++) {
					
					objOrderVOSpecStatus = (OrderVO) arlOrderList.get(i);
					intSpecStatusCode = objOrderVOSpecStatus
					.getSpecStatusCode();
				}
				
			}
			objOrderVOSpecStatus.setUserID(objLoginVo.getUserID());
			SpecStatusBI objSpecStatusBI = ServiceFactory.getSpecStatusBO();
			ArrayList arlSpecStatus = objSpecStatusBI
			.fetchSpecNextStatus(objOrderVOSpecStatus);
			objOrderSectionForm.setSpecStatus(arlSpecStatus);
			
		}
		
		catch (Exception objEx) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForwardKey);
	}
	//Upadated for CR_100
	/***************************************************************************
	 * This method is Used for publishing the spec
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
	
	public ActionForward publish(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		String strForwardKey = ApplicationConstants.RELOAD_SPEC;
		LogUtil.logMessage("Enters into OrderSectionAction:publish");
		
		OrderVO objOrderVO = new OrderVO();
		HttpSession objSession = objHttpServletRequest.getSession(false);
		OrderSectionForm objOrderSectionForm = (OrderSectionForm) objActionForm;
		ActionForward actionRedirect = null;
		ArrayList arlOrderList = null;
		ArrayList arlRevList = null;
		OrderVO objOrderVOSpecStatus = null;
		String strSpecStatus = "";
		String strCusCode = "";
		String strOrderNO = "";
		String strModelName = "";
		int intQuantity = 0;
		int intModelSeqNo = 0;
		String strActionType = null;
		
		int intOrdeKey = 0;
		int intSecSeqNo = 0;
		int intRevCode = 1;
		String strSecCode = null;
		String strSecName = null;
		String strRevFlag=null;
		String strFinalFlag=null;
		int intSpecStatusCode = 0;
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlSectionDetails = null;
		SectionVO objSectionVO = new SectionVO();
		
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
						
			if (objHttpServletRequest.getParameter("finalFlag") != null
					&& objHttpServletRequest.getParameter("finalFlag") != "") {
				strFinalFlag = objHttpServletRequest.getParameter("finalFlag");
				objOrderVO.setFinalFlag(strFinalFlag);
			} else {
				
				objOrderVO.setFinalFlag(objOrderSectionForm.getFinalFlag());
			}
			
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			
			if (objHttpServletRequest.getParameter("orderKey") != null
					&& objHttpServletRequest.getParameter("orderKey") != "") {
				intOrdeKey = Integer.parseInt(objHttpServletRequest
						.getParameter("orderKey"));
				objOrderVO.setOrderKey(intOrdeKey);
			} else {
				
				objOrderVO.setOrderKey(objOrderSectionForm.getOrderKey());
			}
			LogUtil.logMessage("Enters into OrderSectionAction:publish:orderKey");
			if (objHttpServletRequest.getParameter("revFlag") != null
					&& objHttpServletRequest.getParameter("revFlag") != "") {
				strRevFlag = String.valueOf(objHttpServletRequest
						.getParameter("revFlag"));
				objOrderVO.setRevFlag(strRevFlag);
			} else {
				
				objOrderVO.setRevFlag(objOrderSectionForm.getRevFlag());
			}
			LogUtil.logMessage("Enters into OrderSectionAction:publish:revFlag");
			
			if (objHttpServletRequest.getParameter("revCode") != null
					&& objHttpServletRequest.getParameter("revCode") != "") {
				intRevCode = Integer.parseInt(objHttpServletRequest
						.getParameter("revCode"));
				objOrderVO.setRevCode(intRevCode);
			} else {
				
				objOrderVO.setRevCode(objOrderSectionForm.getRevCode());
			}
			LogUtil.logMessage("Enters into OrderSectionAction:publish:revCode");
			if (objHttpServletRequest.getParameter("specStatusCode") != null
					&& objHttpServletRequest.getParameter("specStatusCode") != "") {
				intSpecStatusCode = Integer.parseInt(objHttpServletRequest
						.getParameter("specStatusCode"));
				objOrderVO.setSpecStatusCode(intSpecStatusCode);
			} else {
				LogUtil.logMessage("Enters into OrderSectionAction:publish:specStatusCode"+objOrderSectionForm.getSpecStatusCode());
				objOrderVO.setSpecStatusCode(objOrderSectionForm.getSpecStatusCode());
			}
			LogUtil.logMessage("Enters into OrderSectionAction:publish:specStatusCode");
				
			
			//Added for CR
			objOrderVO.setModelSeqNo(Integer
					.parseInt((String) objHttpServletRequest
							.getParameter("modSeqNo")));
			//Ends
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			
			OrderVO objOrder = objOrderBI.publishOrder(objOrderVO);
			ArrayList arlCustomerVO = null;
			if (arlOrderList != null && arlOrderList.size() > 0) {
				
				for (int i = 0; i < arlOrderList.size(); i++) {
					
					objOrderVOSpecStatus = (OrderVO) arlOrderList.get(i);
					strSpecStatus = objOrderVOSpecStatus.getStatusDesc();
					strCusCode = objOrderVOSpecStatus.getSapCusCode();
					strOrderNO = objOrderVOSpecStatus.getOrderNo();
					intQuantity = objOrderVOSpecStatus.getQuantity();
					intModelSeqNo = objOrderVOSpecStatus.getModelSeqNo();
					strModelName = objOrderVOSpecStatus.getModelName();
					arlCustomerVO = objOrderVOSpecStatus.getCustomerVo();
					
				}
			}
			
			objOrderVO.setOrderNo(strOrderNO);
			objOrderVO.setQuantity(intQuantity);
			objOrderVO.setSapCusCode(strCusCode);
			objOrderVO.setStatusDesc(strSpecStatus);
			objOrderVO.setCustomerVo(arlCustomerVO);
			objOrderVO.setModelName(strModelName);
			objOrderVO.setModelSeqNo(intModelSeqNo);
			objOrderVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			if ((String) objHttpServletRequest.getParameter("actionType") != null) {
				
				strActionType = objHttpServletRequest
				.getParameter("actionType");
			}
			
			//This part is for server side validation to check for mandatory
			// component check
			if (objOrderVO.getMessage() != null) {
				
				objOrderSectionForm.setErrorMessage(objOrderVO.getMessage());
				
				if ((String) objHttpServletRequest.getParameter("orderKey") != null) {
					
					intOrdeKey = Integer.parseInt(objHttpServletRequest
							.getParameter("orderKey"));
					
				} else {
					
					intOrdeKey = objOrderSectionForm.getOrderKey();
					
				}
				if ((String) objHttpServletRequest.getParameter("secSeq") != null) {
					
					intSecSeqNo = Integer.parseInt(objHttpServletRequest
							.getParameter("secSeq"));
					
				} else {
					
					intSecSeqNo = objOrderSectionForm.getOrderSecSeqNo();
					
				}
				//commented for CR_90
				/*if (objOrderSectionForm.getRevCode() == 0) {
					
					intRevCode = 1;//Default No revision
					
				} else if ((String) objHttpServletRequest
						.getParameter("revCode") != null) {
					
					intRevCode = Integer.parseInt(objHttpServletRequest
							.getParameter("revCode"));
				}
				
				else {
					intRevCode = objOrderSectionForm.getRevCode();
				}*/
				
				objOrderSectionForm.setOrderKey(intOrdeKey);
				objOrderSectionForm.setOrderSecCode(strSecCode);
				objOrderSectionForm.setOrderSecName(strSecName);
				objOrderSectionForm.setOrderSecSeqNo(intSecSeqNo);
				objOrderSectionForm.setRevCode(intRevCode);
				
				objSectionVO.setUserID(objLoginVo.getUserID());
				objSectionVO.setSectionSeqNo(intSecSeqNo);
				objSectionVO.setSectionCode(strSecCode);
				objSectionVO.setSectionName(strSecName);
				objSectionVO.setOrderKey(intOrdeKey);
				objSectionVO
				.setDataLocationType(DatabaseConstants.DATALOCATION);
				objSectionVO.setRevisionInput(intRevCode);//default set as No
				// revision
				//objSectionVO.setSectionSeqNo();
				
				OrderSectionBI objOrderSectionBI = ServiceFactory
				.getOrderSectionBO();
				arlSectionList = objOrderSectionBI
				.fetchOrdSections(objSectionVO);
				
				if (arlSectionList != null) {
					objOrderSectionForm.setSectionList(arlSectionList);
				}
				
				/**
				 * populating the Revision drop down code starts here.
				 */
				
				RevisionVO objRevisionVO = new RevisionVO();
				objRevisionVO.setUserID(objLoginVo.getUserID());
				
				arlRevList = objOrderSectionBI.fetchRevision(objRevisionVO);
				
				if (arlRevList != null) {
					objOrderSectionForm.setRevisionList(arlRevList);
				}
				/**
				 * Ends Here
				 */
				//Added for CR_109 to bring New Subsections to Order
				objSectionVO.setNewSubSecFlag(ApplicationConstants.YES);
				//Added for CR_109 to bring New Subsections to Order
				arlSectionDetails = objOrderSectionBI
				.fetchSectionDetails(objSectionVO);
				objOrderSectionForm.setSecDetail(arlSectionDetails);
				objOrderVO = new OrderVO();
				objOrderVO.setOrderKey(intOrdeKey);
				objOrderVO.setUserID(objLoginVo.getUserID());
				objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
				arlOrderList = objOrderBI.fetchOrders(objOrderVO);
				objOrderSectionForm.setOrderList(arlOrderList);
				//This is for getting the Spec Status Code to be passed to get
				// the next status
				objOrderVOSpecStatus = null;
				if (arlOrderList != null && arlOrderList.size() > 0) {
					for (int i = 0; i < arlOrderList.size(); i++) {
						
						objOrderVOSpecStatus = (OrderVO) arlOrderList.get(i);
						intSpecStatusCode = objOrderVOSpecStatus
						.getSpecStatusCode();
					}
					
				}
				objOrderVOSpecStatus.setUserID(objLoginVo.getUserID());
				SpecStatusBI objSpecStatusBI = ServiceFactory.getSpecStatusBO();
				ArrayList arlSpecStatus = objSpecStatusBI
				.fetchSpecNextStatus(objOrderVOSpecStatus);
				objOrderSectionForm.setSpecStatus(arlSpecStatus);
				
				actionRedirect = objActionMapping.findForward("sectionview");
				
			} else {
				
				ActionForward actionForward = objActionMapping
				.findForward(strForwardKey);
				
				actionRedirect = new ActionForward(actionForward.getName(),
						actionForward.getPath()
						+ "?method=fetchComponentDesc&orderKeyId="
						+ objOrder.getOrderKey() + "&specStatus="
						+ objOrderSectionForm.getSpecStatusCode()
						+ "&actionType=" + strActionType, true /* REDIRECT */
				);
			}
			LogUtil.logMessage("actionRedirect :" + actionRedirect);
			
		} catch (Exception objEx) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		//return objActionMapping.findForward(strForwardKey);
		return actionRedirect;
		
	}
	
	/***************************************************************************
	 * This method is Used for deleting Clause Details in order level
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
	
	public ActionForward deleteClauseDetails(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil
		.logMessage("Enters into OrderSectionAction:deleteClauseDetails");
		String strForwardKey = ApplicationConstants.CLAUSE_REASON_VIEW;
		
		//Commented for LSDB_CR-74
		/*ArrayList arlSectionList = new ArrayList();
		ArrayList arlSectionDetails = null;
		ArrayList arlOrderList = null;
		ArrayList arlRevList = null;*/
		//SectionVO objSectionVO = new SectionVO();
		ClauseVO objClauseVo = new ClauseVO();
		//OrderVO objOrderVO = null;
		OrderSectionForm objOrderSectionForm = (OrderSectionForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		int intVersionNo = 0;
		int intOrdeKey = 0;
		int intClauseSeqNo = 0;
		int intSecSeqNo = 0;
		int intRevCode = 0;
		String strSecCode = null;
		String strSecName = null;
		//int intSpecStatusCode = 0;
		int intDeleteClause = 0;
		int intSubSecSeqNo = 0;
		String strFlag = null;
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if ((String) objHttpServletRequest.getParameter("orderKey") != null) {
				intOrdeKey = Integer.parseInt(objHttpServletRequest
						.getParameter("orderKey"));
			} else {
				intOrdeKey = objOrderSectionForm.getOrderKey();
			}
			
			if ((String) objHttpServletRequest.getParameter("clauseSeq") != null) {
				intClauseSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("clauseSeq").toString());
			}
			//Added for LSDB_CR-74
			if ((String) objHttpServletRequest.getParameter("clauseVersionNo") != null) {
				intVersionNo = Integer.parseInt(objHttpServletRequest
						.getParameter("clauseVersionNo").toString());
			}
			
			if ((String) objHttpServletRequest.getParameter("secSeq") != null) {
				intSecSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("secSeq"));
			} else {
				intSecSeqNo = objOrderSectionForm.getOrderSecSeqNo();
			}
			
			if (objOrderSectionForm.getRevCode() == 0) {
				intRevCode = 1;//Default No revision
			} else if ((String) objHttpServletRequest.getParameter("revCode") != null) {
				intRevCode = Integer.parseInt(objHttpServletRequest
						.getParameter("revCode"));
			} else {
				intRevCode = objOrderSectionForm.getRevCode();
			}
			
			if ((String) objHttpServletRequest.getParameter("subsecseqno") != null) {
				intSubSecSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("subsecseqno").toString());
				
				//Added for landing to subsection
				objHttpServletRequest.setAttribute("subsecseqno",
						(String) objHttpServletRequest
						.getParameter("subsecseqno"));
				
				//Ends here
				
			}
			if ((String) objHttpServletRequest.getParameter("flag") != null) {
				strFlag = objHttpServletRequest
						.getParameter("flag");
				objHttpServletRequest.setAttribute("flag",
						strFlag);
							
			}
			
			
			// To delete Clause
			
			objClauseVo.setOrderKey(intOrdeKey);
			objClauseVo.setClauseSeqNo(intClauseSeqNo);
			objClauseVo.setSubSectionSeqNo(intSubSecSeqNo);
			objClauseVo.setDataLocationType(DatabaseConstants.DATALOCATION);
			objClauseVo.setUserID(objLoginVo.getUserID());
//Added CR_86 for  removing  reserved clause 
			//setting value into VO 
			if(strFlag.equals("RC"))
			{
				objClauseVo.setRemoveClause("Y");
			}else {
				objClauseVo.setRemoveClause(null);
				
			}
						
			LogUtil.logMessage("intOrdeKey::::" + intOrdeKey);
			LogUtil.logMessage("intClauseSeqNo:::" + intClauseSeqNo);
			LogUtil.logMessage("intSubSecSeqNo:::" + intSubSecSeqNo);
			LogUtil.logMessage("dataloc::::"
					+ objClauseVo.getDataLocationType());
			//Added for LSDB_CR-74 by KA57588
			objClauseVo.setVersionNo(intVersionNo);
			objClauseVo.setReason(objOrderSectionForm.getReason());
			
			OrderClauseBI objOrderClauseBo = ServiceFactory.getOrderClauseBO();
			intDeleteClause = objOrderClauseBo.deleteClause(objClauseVo);
						
			objOrderSectionForm.setOrderKey(intOrdeKey);
			objOrderSectionForm.setOrderSecCode(strSecCode);
			objOrderSectionForm.setOrderSecName(strSecName);
			objOrderSectionForm.setOrderSecSeqNo(intSecSeqNo);
			objOrderSectionForm.setSubSecSeqNo(intSubSecSeqNo);
			objOrderSectionForm.setClauseSeqNo(intClauseSeqNo);
			objOrderSectionForm.setClauseVersionNo(intVersionNo);
			objOrderSectionForm.setRevCode(intRevCode);
			if(strFlag.equals("RC"))
			{	//Commented for LSDB_CR-74
				//Removed Commented part for LSDB_CR-86 to remove Reserved Clauses
			ArrayList arlSectionList = new ArrayList();
			ArrayList arlSectionDetails = null;
			ArrayList arlOrderList = null;
			ArrayList arlRevList = null;
			SectionVO objSectionVO = new SectionVO();
			OrderVO objOrderVO = null;
			int intSpecStatusCode = 0;
			
			objSectionVO.setUserID(objLoginVo.getUserID());
			objSectionVO.setSectionSeqNo(intSecSeqNo);
			objSectionVO.setSectionCode(strSecCode);
			objSectionVO.setSectionName(strSecName);
			objSectionVO.setOrderKey(intOrdeKey);
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSectionVO.setRevisionInput(intRevCode);//default set as No
			// revision
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null) {
				objOrderSectionForm.setSectionList(arlSectionList);
			}
			
			
			
			RevisionVO objRevisionVO = new RevisionVO();
			objRevisionVO.setUserID(objLoginVo.getUserID());
			
			arlRevList = objOrderSectionBI.fetchRevision(objRevisionVO);
			
			if (arlRevList != null) {
				objOrderSectionForm.setRevisionList(arlRevList);
			}
			//Added for CR_109 to bring New Subsections to Order
			objSectionVO.setNewSubSecFlag(ApplicationConstants.YES);
			//Added for CR_109 to bring New Subsections to Order
			
			arlSectionDetails = objOrderSectionBI
			.fetchSectionDetails(objSectionVO);
			
			objOrderSectionForm.setSecDetail(arlSectionDetails);
			
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			objOrderVO = new OrderVO();
			
			objOrderVO.setOrderKey(intOrdeKey);
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objOrderSectionForm.setOrderList(arlOrderList);
			
			//This is for getting the Spec Status Code to be passed to get the
			// next status
			OrderVO objOrderVOSpecStatus = null;
			if (arlOrderList != null && arlOrderList.size() > 0) {
				for (int i = 0; i < arlOrderList.size(); i++) {
					objOrderVOSpecStatus = (OrderVO) arlOrderList.get(i);
					intSpecStatusCode = objOrderVOSpecStatus
					.getSpecStatusCode();
				}
			}
			objOrderVOSpecStatus.setUserID(objLoginVo.getUserID());
			SpecStatusBI objSpecStatusBI = ServiceFactory.getSpecStatusBO();
			ArrayList arlSpecStatus = objSpecStatusBI
			.fetchSpecNextStatus(objOrderVOSpecStatus);
			objOrderSectionForm.setSpecStatus(arlSpecStatus);
			
//			Added CR_86 for  refresh the page after remove the reserved clause  .
			
			 strForwardKey = ApplicationConstants.SECTION_VIEW;
			}
			 
			
			
			if (intDeleteClause == 0) {
				objOrderSectionForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objOrderSectionForm.setMessageID("" + intDeleteClause);
			}
			
			
		} catch (Exception objEx) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	
	/***************************************************************************
	 * This method is Used for update reason at the order level
	 * Added for LSDB_CR_74
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward updateReason(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil
		.logMessage("Enters into OrderSectionAction:updateReason");
		String strForwardKey = ApplicationConstants.CLAUSE_REASON_VIEW;
		ClauseVO objClauseVo = new ClauseVO();
		OrderSectionForm objOrderSectionForm = (OrderSectionForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		int intVersionNo = 0;
		int intOrdeKey = 0;
		int intClauseSeqNo = 0;
		int intSecSeqNo = 0;
		int intRevCode = 0;
		String strSecCode = null;
		String strSecName = null;
		int intDeleteClause = 0;
		int intSubSecSeqNo = 0;
		String strFlag = null;
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if ((String) objHttpServletRequest.getParameter("orderKey") != null) {
				intOrdeKey = Integer.parseInt(objHttpServletRequest
						.getParameter("orderKey"));
			} else {
				intOrdeKey = objOrderSectionForm.getOrderKey();
			}
			
			if ((String) objHttpServletRequest.getParameter("clauseSeq") != null) {
				intClauseSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("clauseSeq").toString());
			}
			if ((String) objHttpServletRequest.getParameter("clauseVersionNo") != null) {
				intVersionNo = Integer.parseInt(objHttpServletRequest
						.getParameter("clauseVersionNo").toString());
			}
			
			if ((String) objHttpServletRequest.getParameter("secSeq") != null) {
				intSecSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("secSeq"));
			} else {
				intSecSeqNo = objOrderSectionForm.getOrderSecSeqNo();
			}
			
			if (objOrderSectionForm.getRevCode() == 0) {
				intRevCode = 1;//Default No revision
			} else if ((String) objHttpServletRequest.getParameter("revCode") != null) {
				intRevCode = Integer.parseInt(objHttpServletRequest
						.getParameter("revCode"));
			} else {
				intRevCode = objOrderSectionForm.getRevCode();
			}
			
			if ((String) objHttpServletRequest.getParameter("subsecseqno") != null) {
				intSubSecSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("subsecseqno").toString());
				
				//Added for landing to subsection
				objHttpServletRequest.setAttribute("subsecseqno",
						(String) objHttpServletRequest
						.getParameter("subsecseqno"));
				
				//Ends here
				
			}
			if ((String) objHttpServletRequest.getParameter("flag") != null) {
				strFlag = objHttpServletRequest
						.getParameter("flag");
				objHttpServletRequest.setAttribute("flag",
						strFlag);
							
			}
			
			// To delete Clause
			
			objClauseVo.setOrderKey(intOrdeKey);
			objClauseVo.setClauseSeqNo(intClauseSeqNo);
			objClauseVo.setSubSectionSeqNo(intSubSecSeqNo);
			objClauseVo.setDataLocationType(DatabaseConstants.DATALOCATION);
			objClauseVo.setUserID(objLoginVo.getUserID());
			
			OrderClauseBI objOrderClauseBo = ServiceFactory.getOrderClauseBO();
					
			//Added for LSDb_CR-74
			objClauseVo.setVersionNo(intVersionNo);
			objClauseVo.setReason(objOrderSectionForm.getReason());
			int intUpdateReason = objOrderClauseBo.updateClauseReason(objClauseVo);
			objOrderSectionForm.setOrderKey(intOrdeKey);
			objOrderSectionForm.setOrderSecCode(strSecCode);
			objOrderSectionForm.setOrderSecName(strSecName);
			objOrderSectionForm.setOrderSecSeqNo(intSecSeqNo);
			objOrderSectionForm.setSubSecSeqNo(intSubSecSeqNo);
			objOrderSectionForm.setClauseSeqNo(intClauseSeqNo);
			objOrderSectionForm.setClauseVersionNo(intVersionNo);
			objOrderSectionForm.setRevCode(intRevCode);
			
			if (intUpdateReason == 0) {
				objOrderSectionForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objOrderSectionForm.setMessageID("" + intDeleteClause);
			}
		} catch (Exception objEx) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***************************************************************************
	 * This method is for displaying Clause Reason
	 * Added for LSDB_CR-74 by ka57588
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException
	 **************************************************************************/
	public ActionForward displayClauseReason(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil
		.logMessage("Enters into OrderSectionAction:displayClauseReason");
		String strForwardKey = ApplicationConstants.CLAUSE_REASON_VIEW;
		OrderSectionForm objOrderSectionForm = (OrderSectionForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		ClauseVO objClauseVo = new ClauseVO();
		int intOrdeKey = 0;
		int intClauseSeqNo = 0;
		int intVersionNo = 0;
		int intSecSeqNo = 0;
		int intRevCode = 0;
		String strSecCode = null;
		String strSecName = null;
		int intSubSecSeqNo = 0;
		String strFlag = null;
		//Added For CR-79 to remove reason validation by RR68151 
		int intSpecStatusCode = 0;
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if ((String) objHttpServletRequest.getParameter("orderKey") != null) {
				intOrdeKey = Integer.parseInt(objHttpServletRequest
						.getParameter("orderKey"));
			} else {
				intOrdeKey = objOrderSectionForm.getOrderKey();
			}
			
			if ((String) objHttpServletRequest.getParameter("clauseSeq") != null) {
				intClauseSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("clauseSeq").toString());
			}
			
			if ((String) objHttpServletRequest.getParameter("clauseVersionNo") != null) {
				intVersionNo = Integer.parseInt(objHttpServletRequest
						.getParameter("clauseVersionNo").toString());
			}
			
			
			if ((String) objHttpServletRequest.getParameter("secSeq") != null) {
				intSecSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("secSeq"));
			} else {
				intSecSeqNo = objOrderSectionForm.getOrderSecSeqNo();
			}
			
			if (objOrderSectionForm.getRevCode() == 0) {
				intRevCode = 1;//Default No revision
			} else if ((String) objHttpServletRequest.getParameter("revCode") != null) {
				intRevCode = Integer.parseInt(objHttpServletRequest
						.getParameter("revCode"));
			} else {
				intRevCode = objOrderSectionForm.getRevCode();
			}
			
			if ((String) objHttpServletRequest.getParameter("subsecseqno") != null) {
				intSubSecSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("subsecseqno").toString());
							
			}
			
			if ((String) objHttpServletRequest.getParameter("flag") != null) {
				strFlag = objHttpServletRequest
						.getParameter("flag");
				objHttpServletRequest.setAttribute("flag",
						strFlag);
							
			}

			//Added For CR-79 to remove reason validation by RR68151 
			if ((String) objHttpServletRequest.getParameter("specCode") != null) {
				intSpecStatusCode = Integer.parseInt(objHttpServletRequest
						.getParameter("specCode"));
			} else {
				intSpecStatusCode = objOrderSectionForm.getCurrentSpecStatus();
			}
							
			objOrderSectionForm.setOrderKey(intOrdeKey);
			objOrderSectionForm.setOrderSecCode(strSecCode);
			objOrderSectionForm.setOrderSecName(strSecName);
			objOrderSectionForm.setOrderSecSeqNo(intSecSeqNo);
			objOrderSectionForm.setSubSecSeqNo(intSubSecSeqNo);
			objOrderSectionForm.setClauseSeqNo(intClauseSeqNo);
			objOrderSectionForm.setClauseVersionNo(intVersionNo);
			objOrderSectionForm.setRevCode(intRevCode);
			objOrderSectionForm.setCurrentSpecStatus(intSpecStatusCode);
			
			objClauseVo.setOrderKey(intOrdeKey);
			objClauseVo.setClauseSeqNo(intClauseSeqNo);
			objClauseVo.setVersionNo(intVersionNo);
			objClauseVo.setSubSectionSeqNo(intSubSecSeqNo);
			objClauseVo.setDataLocationType(DatabaseConstants.DATALOCATION);
			objClauseVo.setUserID(objLoginVo.getUserID());
			
			OrderClauseBI objOrderClauseBo = ServiceFactory.getOrderClauseBO();
			ArrayList arlClause = objOrderClauseBo.displayClauseReason(objClauseVo);
			objOrderSectionForm.setClauseDetail(arlClause);
						
		} catch (Exception objEx) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
		
	/***************************************************************************
	 * This method is for updating user level marker
	 * Added for LSDB_CR-74 by ka57588
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward updateUserMarker(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil
		.logMessage("Enters into OrderSectionAction:updateUserMarker");
		String strForwardKey = ApplicationConstants.SECTION_VIEW;
		
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlSectionDetails = null;
		ArrayList arlOrderList = null;
		ArrayList arlRevList = null;
		SectionVO objSectionVO = new SectionVO();
		ClauseVO objClauseVo = new ClauseVO();
		OrderVO objOrderVO = null;
		OrderSectionForm objOrderSectionForm = (OrderSectionForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		int intOrdeKey = 0;
		int intClauseSeqNo = 0;
		int intSecSeqNo = 0;
		int intRevCode = 0;
		String strSecCode = null;
		String strSecName = null;
		int intUserMark = 0;
		int intSubSecSeqNo = 0;
		String strUserMarkFlag = null;
		
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if ((String) objHttpServletRequest.getParameter("orderKey") != null) {
				intOrdeKey = Integer.parseInt(objHttpServletRequest
						.getParameter("orderKey"));
			} else {
				intOrdeKey = objOrderSectionForm.getOrderKey();
			}
			
			if ((String) objHttpServletRequest.getParameter("clauseSeq") != null) {
				intClauseSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("clauseSeq").toString());
			}
			if ((String) objHttpServletRequest.getParameter("secSeq") != null) {
				intSecSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("secSeq"));
			} else {
				intSecSeqNo = objOrderSectionForm.getOrderSecSeqNo();
			}
			
			if (objOrderSectionForm.getRevCode() == 0) {
				intRevCode = 1;//Default No revision
			} else if ((String) objHttpServletRequest.getParameter("revCode") != null) {
				intRevCode = Integer.parseInt(objHttpServletRequest
						.getParameter("revCode"));
			} else {
				intRevCode = objOrderSectionForm.getRevCode();
			}
			
			if ((String) objHttpServletRequest.getParameter("subsecseqno") != null) {
				intSubSecSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("subsecseqno").toString());
				
				//Added for landing to subsection
				objHttpServletRequest.setAttribute("subsecseqno",
						(String) objHttpServletRequest
						.getParameter("subsecseqno"));
				
				//Ends here
				
			}
			//Added for LSDB_CR-74 by KA57588 --Starts here
			if ((String) objHttpServletRequest.getParameter("usermarkflag") != null) {
				strUserMarkFlag = objHttpServletRequest
						.getParameter("usermarkflag");
			}
			//Ends here
			
			
			objClauseVo.setOrderKey(intOrdeKey);
			objClauseVo.setClauseSeqNo(intClauseSeqNo);
			objClauseVo.setSubSectionSeqNo(intSubSecSeqNo);
			objClauseVo.setDataLocationType(DatabaseConstants.DATALOCATION);
			objClauseVo.setUserID(objLoginVo.getUserID());
			//Added for LSDB_CR-74 by KA57588 --starts here
			objClauseVo.setFlag(strUserMarkFlag);//User Marker Flag
			//Ends here
			//Added for CR_109
			objClauseVo.setMarkClaReason(objOrderSectionForm.getMarkClaReason());
			//Added for CR_109 Ends here
			
			
			OrderClauseBI objOrderClauseBo = ServiceFactory.getOrderClauseBO();
			intUserMark = objOrderClauseBo.updateUserMarker(objClauseVo);
			
			objOrderSectionForm.setOrderKey(intOrdeKey);
			objOrderSectionForm.setOrderSecCode(strSecCode);
			objOrderSectionForm.setOrderSecName(strSecName);
			objOrderSectionForm.setOrderSecSeqNo(intSecSeqNo);
			objOrderSectionForm.setRevCode(intRevCode);
			
			objSectionVO.setUserID(objLoginVo.getUserID());
			objSectionVO.setSectionSeqNo(intSecSeqNo);
			objSectionVO.setSectionCode(strSecCode);
			objSectionVO.setSectionName(strSecName);
			objSectionVO.setOrderKey(intOrdeKey);
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSectionVO.setRevisionInput(intRevCode);//default set as No
			// revision
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null) {
				objOrderSectionForm.setSectionList(arlSectionList);
			}
			
			/**
			 * populating the Revision drop down code starts here.
			 */
			
			RevisionVO objRevisionVO = new RevisionVO();
			objRevisionVO.setUserID(objLoginVo.getUserID());
			
			arlRevList = objOrderSectionBI.fetchRevision(objRevisionVO);
			
			if (arlRevList != null) {
				objOrderSectionForm.setRevisionList(arlRevList);
			}
			/**
			 * Ends Here
			 */
			//Added for CR_109 to bring New Subsections to Order
			objSectionVO.setNewSubSecFlag(ApplicationConstants.YES);
			//Added for CR_109 to bring New Subsections to Order

			arlSectionDetails = objOrderSectionBI
			.fetchSectionDetails(objSectionVO);
			
			objOrderSectionForm.setSecDetail(arlSectionDetails);
			
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			objOrderVO = new OrderVO();
			
			objOrderVO.setOrderKey(intOrdeKey);
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objOrderSectionForm.setOrderList(arlOrderList);
			
			//This is for getting the Spec Status Code to be passed to get the
			// next status
			OrderVO objOrderVOSpecStatus = null;
			if (arlOrderList != null && arlOrderList.size() > 0) {
				for (int i = 0; i < arlOrderList.size(); i++) {
					objOrderVOSpecStatus = (OrderVO) arlOrderList.get(i);
					
				}
			}
			objOrderVOSpecStatus.setUserID(objLoginVo.getUserID());
			SpecStatusBI objSpecStatusBI = ServiceFactory.getSpecStatusBO();
			ArrayList arlSpecStatus = objSpecStatusBI
			.fetchSpecNextStatus(objOrderVOSpecStatus);
			objOrderSectionForm.setSpecStatus(arlSpecStatus);
			
			if (intUserMark == 0) {
				objOrderSectionForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objOrderSectionForm.setMessageID("" + intUserMark);
			}
		} catch (Exception objEx) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	//Added For CR_79 Adding PDF Header Image by RR68151
	
	/***************************************************************************
	 * This method is Used for turn ON/OFF 
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
	
	public ActionForward turnPDFHeaderImage(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		String strForwardKey = ApplicationConstants.SECTION_VIEW;
		LogUtil.logMessage("Enters into OrderSectionAction:turnPDFHeaderImage");

		ArrayList arlSectionList = new ArrayList();
		ArrayList arlSectionDetails = new ArrayList();
		ArrayList arlOrderList = new ArrayList();
		ArrayList arlRevList = new ArrayList();
		SectionVO objSectionVO = new SectionVO();
		OrderVO objOrderVO = new OrderVO();
		OrderSectionForm objOrderSectionForm = (OrderSectionForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		int intOrdeKey = 0;
		int intStatusCode = 0;
		int intRevCode = 0;
		int intSecSeqNo = 0;
		String strPDFHeaderFlag = null;
		String strSecCode = null;
		String strSecName = null;
		
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if ((String) objHttpServletRequest.getParameter("orderKey") != null) {
				intOrdeKey = Integer.parseInt(objHttpServletRequest
						.getParameter("orderKey"));
			} else {
				intOrdeKey = objOrderSectionForm.getOrderKey();
			}
			
			if (objOrderSectionForm.getRevCode() == 0) {
				intRevCode = 1;//Default No revision
			} else if ((String) objHttpServletRequest.getParameter("revCode") != null) {
				intRevCode = Integer.parseInt(objHttpServletRequest
						.getParameter("revCode"));
			} else {
				intRevCode = objOrderSectionForm.getRevCode();
			}			

			if ((String) objHttpServletRequest.getParameter("flag") != null) {
				
				strPDFHeaderFlag = (String) objHttpServletRequest.getParameter("flag");
			}
			
			objOrderVO.setOrderKey(intOrdeKey);
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			objOrderVO.setPdfHeaderFlag(strPDFHeaderFlag);
			
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			intStatusCode = objOrderBI.updateOrders(objOrderVO);
			
			if (intStatusCode == 0) {
				objOrderSectionForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objOrderSectionForm.setMessageID("" + intStatusCode);
			}

			intSecSeqNo = objOrderSectionForm.getOrderSecSeqNo();
			strSecCode = objOrderSectionForm.getOrderSecCode();
			strSecName = objOrderSectionForm.getOrderSecName();
			objOrderSectionForm.setOrderKey(intOrdeKey);
			objOrderSectionForm.setOrderSecCode(strSecCode);
			objOrderSectionForm.setOrderSecName(strSecName);
			objOrderSectionForm.setOrderSecSeqNo(intSecSeqNo);
			objOrderSectionForm.setRevCode(intRevCode);
			
			objSectionVO.setUserID(objLoginVo.getUserID());
			objSectionVO.setSectionSeqNo(intSecSeqNo);
			objSectionVO.setOrderKey(intOrdeKey);
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSectionVO.setRevisionInput(intRevCode);//default set as No revision
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null) {
				objOrderSectionForm.setSectionList(arlSectionList);
			}
			
			/**
			 * populating the Revision drop down code starts here.
			 */
			
			RevisionVO objRevisionVO = new RevisionVO();
			objRevisionVO.setUserID(objLoginVo.getUserID());
			
			arlRevList = objOrderSectionBI.fetchRevision(objRevisionVO);
			
			if (arlRevList != null) {
				objOrderSectionForm.setRevisionList(arlRevList);
			}
			/**
			 * Ends Here
			 */
			//Added for CR_109 to bring New Subsections to Order
			objSectionVO.setNewSubSecFlag(ApplicationConstants.YES);
			//Added for CR_109 to bring New Subsections to Order
	
			arlSectionDetails = objOrderSectionBI
			.fetchSectionDetails(objSectionVO);
			objOrderSectionForm.setSecDetail(arlSectionDetails);
			objOrderVO.setOrderKey(objOrderSectionForm.getOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			
			//Displaying Order Info
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objOrderSectionForm.setOrderList(arlOrderList);
			
			//This is for getting the Spec Status Code to be passed to get the
			// next status
			int intSpecStatusCode = 0;
			OrderVO objOrderVOSpecStatus = null;
			if (arlOrderList != null && arlOrderList.size() > 0) {
				for (int i = 0; i < arlOrderList.size(); i++) {
					
					objOrderVOSpecStatus = (OrderVO) arlOrderList.get(i);
					intSpecStatusCode = objOrderVOSpecStatus
					.getSpecStatusCode();
				}
				
			}
			objOrderVOSpecStatus.setUserID(objLoginVo.getUserID());
			SpecStatusBI objSpecStatusBI = ServiceFactory.getSpecStatusBO();
			ArrayList arlSpecStatus = objSpecStatusBI
			.fetchSpecNextStatus(objOrderVOSpecStatus);
			objOrderSectionForm.setSpecStatus(arlSpecStatus);
			
		} catch (Exception objExp) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			LogUtil
			.logMessage(" Exception occured in OrderSectionAction:turnPDFHeaderImage"
					+ objExp);
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);

		}
		return objActionMapping.findForward(strForwardKey);
	}
	//	Added For CR_79 Adding PDF Header Image by RR68151 - Ends here
	
	

	//Added For CR_79 Adding PDF Header Image by RR68151
	
	/***************************************************************************
	 * This method is Used for turn ON/OFF 
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
	
	public ActionForward turnDynamicNumOnOff(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		String strForwardKey = ApplicationConstants.SECTION_VIEW;
		LogUtil.logMessage("Enters into OrderSectionAction:turnDynamicNo");

		ArrayList arlSectionList = new ArrayList();
		ArrayList arlSectionDetails = new ArrayList();
		ArrayList arlOrderList = new ArrayList();
		ArrayList arlRevList = new ArrayList();
		SectionVO objSectionVO = new SectionVO();
		OrderVO objOrderVO = new OrderVO();
		OrderSectionForm objOrderSectionForm = (OrderSectionForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		int intOrdeKey = 0;
		int intStatusCode = 0;
		int intRevCode = 0;
		int intSecSeqNo = 0;
		//String strPDFHeaderFlag = null;
		String strDynamicNoFlag = null;
		String strSecCode = null;
		String strSecName = null;
		
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			intOrdeKey = objOrderSectionForm.getOrderKey();
			intRevCode = objOrderSectionForm.getRevCode();
					
		if ((String) objHttpServletRequest.getParameter("flag") != null) {
				
				strDynamicNoFlag = (String) objHttpServletRequest.getParameter("flag");
			}
			
			objOrderVO.setOrderKey(intOrdeKey);
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			//objOrderVO.setPdfHeaderFlag(strPDFHeaderFlag);
			objOrderVO.setDynamicNoFlag(strDynamicNoFlag);
			
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			intStatusCode = objOrderBI.turnDynamicNumOnOff(objOrderVO);
			
			if (intStatusCode == 0) {
				objOrderSectionForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objOrderSectionForm.setMessageID("" + intStatusCode);
			}

			intSecSeqNo = objOrderSectionForm.getOrderSecSeqNo();
			strSecCode = objOrderSectionForm.getOrderSecCode();
			strSecName = objOrderSectionForm.getOrderSecName();
			objOrderSectionForm.setOrderKey(intOrdeKey);
			objOrderSectionForm.setOrderSecCode(strSecCode);
			objOrderSectionForm.setOrderSecName(strSecName);
			objOrderSectionForm.setOrderSecSeqNo(intSecSeqNo);
			objOrderSectionForm.setRevCode(intRevCode);
			
			objSectionVO.setUserID(objLoginVo.getUserID());
			objSectionVO.setSectionSeqNo(intSecSeqNo);
			objSectionVO.setOrderKey(intOrdeKey);
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSectionVO.setRevisionInput(intRevCode);//default set as No revision
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null) {
				objOrderSectionForm.setSectionList(arlSectionList);
			}
			
			/**
			 * populating the Revision drop down code starts here.
			 */
			
			RevisionVO objRevisionVO = new RevisionVO();
			objRevisionVO.setUserID(objLoginVo.getUserID());
			
			arlRevList = objOrderSectionBI.fetchRevision(objRevisionVO);
			
			if (arlRevList != null) {
				objOrderSectionForm.setRevisionList(arlRevList);
			}
			/**
			 * Ends Here
			 */
			//Added for CR_109 to bring New Subsections to Order
			objSectionVO.setNewSubSecFlag(ApplicationConstants.YES);
			//Added for CR_109 to bring New Subsections to Order

			arlSectionDetails = objOrderSectionBI
			.fetchSectionDetails(objSectionVO);
			objOrderSectionForm.setSecDetail(arlSectionDetails);
			objOrderVO.setOrderKey(objOrderSectionForm.getOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			
			//Displaying Order Info
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objOrderSectionForm.setOrderList(arlOrderList);
			
			//This is for getting the Spec Status Code to be passed to get the
			// next status
			int intSpecStatusCode = 0;
			OrderVO objOrderVOSpecStatus = null;
			if (arlOrderList != null && arlOrderList.size() > 0) {
				for (int i = 0; i < arlOrderList.size(); i++) {
					
					objOrderVOSpecStatus = (OrderVO) arlOrderList.get(i);
					intSpecStatusCode = objOrderVOSpecStatus
					.getSpecStatusCode();
				}
				
			}
			objOrderVOSpecStatus.setUserID(objLoginVo.getUserID());
			SpecStatusBI objSpecStatusBI = ServiceFactory.getSpecStatusBO();
			ArrayList arlSpecStatus = objSpecStatusBI
			.fetchSpecNextStatus(objOrderVOSpecStatus);
			objOrderSectionForm.setSpecStatus(arlSpecStatus);
			
		} catch (Exception objExp) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			LogUtil
			.logMessage(" Exception occured in OrderSectionAction:turnPDFHeaderImage"
					+ objExp);
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);

		}
		return objActionMapping.findForward(strForwardKey);
	}
	//	Added For CR_79 Adding PDF Header Image by RR68151 - Ends here
	
	/***************************************************************************
	 * * * * This Method is used to fetch the Characteristic Groups 
	 * that could be tied to the Characteristic Group Clause
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward fetchCharCombntnEdlMap(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering CharacteristicEDLMapAction.fetchCharCombntnMap");
		String strForwardKey = ApplicationConstants.FETCH_CHAR_COMBNTN_EDL_MAP;
		OrderSectionForm objOrderSectionForm = (OrderSectionForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		int intClauseSeqNo=0;
		try {
			
			if ((String) objHttpServletRequest.getParameter("clauseSeq") != null) {
				 intClauseSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("clauseSeq").toString());
			}
			
			ClauseVO objClauseVO = new ClauseVO();
			ArrayList arlCharGrpCombntn = new ArrayList();
			
			objClauseVO.setClauseSeqNo(intClauseSeqNo);
			objClauseVO.setUserID(objLoginVo.getUserID());
			ModelAssignEdlBI objModelAssignEdlBI = ServiceFactory.getModelAssignEdlBO();
			arlCharGrpCombntn = objModelAssignEdlBI.fetchCharGrpCombntn(objClauseVO);

			if (arlCharGrpCombntn != null && arlCharGrpCombntn.size() > 0)
				objOrderSectionForm.setCharGrpCombntnList(arlCharGrpCombntn);
			else
				objOrderSectionForm.setMethod("fetchCharCombntnEdlMap");	
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in CharacteristicEDLMapAction.fetchCharCombntnMap");
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

	
	//Added For CR_90
	
	/***************************************************************************
	 * This method is Used for validating Save Component for required Component 
	 * Groups not present the spec
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
	
	public ActionForward validateSaveComponent(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into OrderSectionAction:validateSaveComponent");
		String strForwardKey = ApplicationConstants.SECTION_VIEW;
		OrderVO objOrderVO = null;
		SectionVO objSectionVO = new SectionVO();
		HttpSession objSession = objHttpServletRequest.getSession(false);
		OrderSectionForm objOrderSectionForm = (OrderSectionForm) objActionForm;
		int intOrdeKey=0;
						
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
						
			objOrderVO = new OrderVO();
			
			if ((String) objHttpServletRequest.getParameter("subsecno") != null) {
				
			objHttpServletRequest.setAttribute("subsecseqno",(String) objHttpServletRequest.getParameter("subsecno"));
				
			}
			LogUtil.logMessage((String) objHttpServletRequest.getParameter("subsecno"));
			
			if ((String) objHttpServletRequest.getParameter("orderKey") != null) {
				
				intOrdeKey = Integer.parseInt(objHttpServletRequest.getParameter("orderKey"));
				
			}
			objOrderSectionForm.setOrderKey(intOrdeKey);
			objSectionVO.setUserID(objLoginVo.getUserID());
			objSectionVO.setOrderKey(intOrdeKey);
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			objSectionVO.setComponentGroupSeqNo(objOrderSectionForm
					.getComponentGroupSeqNo());
			
			objSectionVO.setCompSeqNo(objOrderSectionForm.getCompSeqNo());
			
			//Remove it
			for (int i = 0; i < objSectionVO.getComponentGroupSeqNo().length; i++) {
				
				LogUtil.logMessage("Component Group Seq No :"
						+ objSectionVO.getComponentGroupSeqNo()[i]);
			}
			for (int i = 0; i < objSectionVO.getCompSeqNo().length; i++) {
				
				LogUtil.logMessage("Component Seq No :"
						+ objSectionVO.getCompSeqNo()[i]);
			}
			//Ends
			OrderSectionBI objOrderSectionBI = ServiceFactory.getOrderSectionBO();
			//Get all the components
			
			SectionVO objSection = objOrderSectionBI.saveRequiredComponent(objSectionVO);
			
			if(objSection!=null){
								
				if(objSection.getMessageID()==0){
					
					objOrderSectionForm.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
					
				}else{
					
					objOrderSectionForm.setCompErrorMessage(objSection.getMessage());
					
				}
			}
			
			String strCompGroup = new JSONObject().put("Components",objOrderSectionForm.getCompErrorMessage()).toString();
			LogUtil.logMessage("strCompGroup :"	+ strCompGroup);
			objHttpServletResponse.setContentType("text/javascript");
		    objHttpServletResponse.getWriter().write(strCompGroup);
		}
		
		catch (Exception objEx) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return null;
	}
	
	/***************************************************************************
	 * This method is Used for validating publish for required Component Groups
	 * not present the spec
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
	
	public ActionForward validatePublish(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		String strForwardKey = ApplicationConstants.RELOAD_SPEC;
		LogUtil.logMessage("Enters into OrderSectionAction:validatePublish");
		
		OrderVO objOrderVO = new OrderVO();
		HttpSession objSession = objHttpServletRequest.getSession(false);
		OrderSectionForm objOrderSectionForm = (OrderSectionForm) objActionForm;		
		
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			objOrderVO.setModelSeqNo(Integer.parseInt((String) objHttpServletRequest.getParameter("modelSeqNo")));
			objOrderVO.setOrderKey(Integer.parseInt((String) objHttpServletRequest.getParameter("orderKey")));
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			objOrderVO = objOrderBI.validatePublishSpec(objOrderVO);
			
			//This part is for server side validation to check for mandatory component check
			if (objOrderVO.getMessage() != null) {				
				objOrderSectionForm.setErrorMessage(objOrderVO.getMessage());
			}
			
			String strCompGroup = new JSONObject().put("Components",objOrderSectionForm.getErrorMessage()).toString();			
			LogUtil.logMessage("strCompGroup :"	+ strCompGroup);
			objHttpServletResponse.setContentType("text/javascript");
		    objHttpServletResponse.getWriter().write(strCompGroup);
			
		} catch (Exception objEx) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return null;
		
	}
	/***************************************************************************
	 * This method is for updating system generated revision marker
	 * Added for LSDB_CR_92 by rj85495
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward updateSysMarker(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil
		.logMessage("Enters into OrderSectionAction:updateSysMarker");
		String strForwardKey = ApplicationConstants.SECTION_VIEW;
		
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlSectionDetails = null;
		ArrayList arlOrderList = null;
		ArrayList arlRevList = null;
		
		SectionVO objSectionVO = new SectionVO();
		ClauseVO objClauseVo = new ClauseVO();
		OrderVO objOrderVO = null;
		
		OrderSectionForm objOrderSectionForm = (OrderSectionForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		int intOrdeKey = 0;
		int intClauseSeqNo = 0;
		int intSecSeqNo = 0;
		int intRevCode = 0;
		int intSysMark = 0;
		int intSubSecSeqNo = 0;
		
		String strSecCode = null;
		String strSecName = null;
		String strSysEnDisableFlag = null;
		
		try {
			LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if ((String) objHttpServletRequest.getParameter("orderKey") != null) {
				intOrdeKey = Integer.parseInt(objHttpServletRequest.getParameter("orderKey"));
			}
			else {
				intOrdeKey = objOrderSectionForm.getOrderKey();
			}
			
			if ((String) objHttpServletRequest.getParameter("clauseSeq") != null) {
				intClauseSeqNo = Integer.parseInt(objHttpServletRequest.getParameter("clauseSeq").toString());
			}
			
			if ((String) objHttpServletRequest.getParameter("secSeq") != null) {
				intSecSeqNo = Integer.parseInt(objHttpServletRequest.getParameter("secSeq"));
			}
			else {
				intSecSeqNo = objOrderSectionForm.getOrderSecSeqNo();
			}
			
			if (objOrderSectionForm.getRevCode() == 0) {
				intRevCode = 1;
			}
			else if ((String) objHttpServletRequest.getParameter("revCode") != null) {
				intRevCode = Integer.parseInt(objHttpServletRequest.getParameter("revCode"));
			}
			else {
				intRevCode = objOrderSectionForm.getRevCode();
			}
			
			if ((String) objHttpServletRequest.getParameter("subsecseqno") != null)	{
				intSubSecSeqNo = Integer.parseInt(objHttpServletRequest.getParameter("subsecseqno").toString());
				objHttpServletRequest.setAttribute("subsecseqno",
										(String) objHttpServletRequest.getParameter("subsecseqno"));
			}
			
			if ((String) objHttpServletRequest.getParameter("sysEnDisableFlag") != null) {
				strSysEnDisableFlag = objHttpServletRequest.getParameter("sysEnDisableFlag");
			}
			
			objClauseVo.setOrderKey(intOrdeKey);
			objClauseVo.setClauseSeqNo(intClauseSeqNo);
			objClauseVo.setSubSectionSeqNo(intSubSecSeqNo);
			objClauseVo.setDataLocationType(DatabaseConstants.DATALOCATION);
			objClauseVo.setUserID(objLoginVo.getUserID());
			objClauseVo.setSysMarkFlag(strSysEnDisableFlag);//User Marker Flag
			OrderClauseBI objOrderClauseBo = ServiceFactory.getOrderClauseBO();
			intSysMark = objOrderClauseBo.updateSysMarker(objClauseVo);
			
			objOrderSectionForm.setOrderKey(intOrdeKey);
			objOrderSectionForm.setOrderSecCode(strSecCode);
			objOrderSectionForm.setOrderSecName(strSecName);
			objOrderSectionForm.setOrderSecSeqNo(intSecSeqNo);
			objOrderSectionForm.setRevCode(intRevCode);
			
			objSectionVO.setUserID(objLoginVo.getUserID());
			objSectionVO.setSectionSeqNo(intSecSeqNo);
			objSectionVO.setSectionCode(strSecCode);
			objSectionVO.setSectionName(strSecName);
			objSectionVO.setOrderKey(intOrdeKey);
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSectionVO.setRevisionInput(intRevCode);
			
			OrderSectionBI objOrderSectionBI = ServiceFactory.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null)	{
				objOrderSectionForm.setSectionList(arlSectionList);
			}
			
			RevisionVO objRevisionVO = new RevisionVO();
			objRevisionVO.setUserID(objLoginVo.getUserID());
			
			arlRevList = objOrderSectionBI.fetchRevision(objRevisionVO);
			
			if (arlRevList != null)	{
				objOrderSectionForm.setRevisionList(arlRevList);
			}
			//Added for CR_109 to bring New Subsections to Order
			objSectionVO.setNewSubSecFlag(ApplicationConstants.YES);
			//Added for CR_109 to bring New Subsections to Order

			arlSectionDetails = objOrderSectionBI.fetchSectionDetails(objSectionVO);
			
			objOrderSectionForm.setSecDetail(arlSectionDetails);
			
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			objOrderVO = new OrderVO();
			
			objOrderVO.setOrderKey(intOrdeKey);
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objOrderSectionForm.setOrderList(arlOrderList);
			
			//This is for getting the Spec Status Code to be passed to get the next status
			OrderVO objOrderVOSpecStatus = null;
			
			if (arlOrderList != null && arlOrderList.size() > 0)
			{
				for (int i = 0; i < arlOrderList.size(); i++) 
				{
					objOrderVOSpecStatus = (OrderVO) arlOrderList.get(i);
				}
			}
			
			objOrderVOSpecStatus.setUserID(objLoginVo.getUserID());
			
			SpecStatusBI objSpecStatusBI = ServiceFactory.getSpecStatusBO();
			ArrayList arlSpecStatus = objSpecStatusBI.fetchSpecNextStatus(objOrderVOSpecStatus);
			
			objOrderSectionForm.setSpecStatus(arlSpecStatus);
			
			if (intSysMark == 0) {
				objOrderSectionForm.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			}
			else {
				objOrderSectionForm.setMessageID("" + intSysMark);
			}
		} 
		catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForwardKey);
	}
	/***************************************************************************
	 * This method is for populating the deleted clauses history
	 * Added for LSDB_CR_92 by rj85495
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward deletedClausesHistoy(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into OrderSectionAction:deletedClausesHistoy");
		String strForwardKey = ApplicationConstants.DELETED_CLAUSES_HISTORY_VIEW;
		
		ClauseVO objClauseVo = new ClauseVO();
		
		OrderSectionForm objOrderSectionForm = (OrderSectionForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		ArrayList arlClauseDetails=null;
		
		int intOrdeKey = 0;
		int intSecSeqNo=0;
		//Added for CR-113 QA Fix
		String strRedirectGenInfoPageFlag = null;
		
		try {
			LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if ((String) objHttpServletRequest.getParameter("orderKey") != null){
				intOrdeKey = Integer.parseInt(objHttpServletRequest.getParameter("orderKey"));
			}
			else {
				intOrdeKey = objOrderSectionForm.getOrderKey();
			}
			if ((String) objHttpServletRequest.getParameter("secSeqNo") != null) {
				intSecSeqNo = Integer.parseInt(objHttpServletRequest.getParameter("secSeqNo").toString());
			}
			LogUtil.logMessage("Sec Seq No::::" + intSecSeqNo);
			
			//Added for CR-113 QA Fix
			if ((String) objHttpServletRequest.getParameter("RedirectGenInfoPageFlag") != null) {
				strRedirectGenInfoPageFlag = objHttpServletRequest.getParameter("RedirectGenInfoPageFlag");
				objOrderSectionForm.setRedirectGenInfoPageFlag(strRedirectGenInfoPageFlag);
			}else{
				objOrderSectionForm.setRedirectGenInfoPageFlag(ApplicationConstants.NO);
			}
			LogUtil.logMessage("strRedirectGenInfoPageFlag::::" + strRedirectGenInfoPageFlag);
			//Added for CR-113 QA Fix Ends Here
				
			objClauseVo.setOrderKey(intOrdeKey);
			objClauseVo.setDataLocationType(DatabaseConstants.DATALOCATION);
			objClauseVo.setUserID(objLoginVo.getUserID());
						
			LogUtil.logMessage("intOrdeKey::::" + intOrdeKey);
			LogUtil.logMessage("dataloc::::"+ objClauseVo.getDataLocationType());
			
			OrderClauseBI objOrderClauseBo = ServiceFactory.getOrderClauseBO();
			arlClauseDetails = objOrderClauseBo.deletedClausesHistoy(objClauseVo);
			
			if (arlClauseDetails != null) {
				objOrderSectionForm.setClauseDetail(arlClauseDetails);
				objOrderSectionForm.setOrderKey(intOrdeKey);
				objOrderSectionForm.setOrderSecSeqNo(intSecSeqNo);
			} 
			LogUtil.logMessage("ArrayList size in action : "+objOrderSectionForm.getClauseDetail().size());
		} 
		catch (Exception objEx)
		{
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForwardKey);
	}
	/***************************************************************************
	 * This method is to retrieve the deleted clause
	 * Added for LSDB_CR-92 by rj85495
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward retrieveDeletedClause(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into OrderSectionAction:retrieveDeletedClause");
		String strForwardKey = ApplicationConstants.SECTION_VIEW;
		
		ClauseVO objClauseVo = new ClauseVO();
		OrderVO objOrderVO = null;
		
		OrderSectionForm objOrderSectionForm = (OrderSectionForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		ArrayList arlSectionList=new ArrayList();
		ArrayList arlRevList=new ArrayList();
		ArrayList arlSectionDetails=new ArrayList();
		ArrayList arlOrderList=new ArrayList();
		
		int intOrdeKey = 0;
		int intClauseSeqNo=0;
		int intStatus=0;
		int intSecSeqNo=0;
		int intRevCode=1;
		int intSpecStatusCode = 0;
		
		String strCurrRevFalg=null;
		String strSecCode=null;
		String strSecName=null;
		
		try	{
			LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if ((String) objHttpServletRequest.getParameter("orderKey") != null) {
				intOrdeKey = Integer.parseInt(objHttpServletRequest.getParameter("orderKey"));
			}
			else {
				intOrdeKey = objOrderSectionForm.getOrderKey();
			}
			LogUtil.logMessage("intOrdeKey::::" + intOrdeKey);
			
			if ((String) objHttpServletRequest.getParameter("clauseSeqNo") != null)	{
				intClauseSeqNo = Integer.parseInt(objHttpServletRequest.getParameter("clauseSeqNo").toString());
			}
			LogUtil.logMessage("clauseSeq::::" + intClauseSeqNo);
			
			if ((String) objHttpServletRequest.getParameter("currRevFlag") != null)	{
				strCurrRevFalg = objHttpServletRequest.getParameter("currRevFlag");
			}
			LogUtil.logMessage("currRevFlag::::" + strCurrRevFalg);
			
			if ((String) objHttpServletRequest.getParameter("secCode") != null)	{
				strSecCode = objHttpServletRequest.getParameter("secCode");
			}
			LogUtil.logMessage("Sec code::::" + strSecCode);
			
			if ((String) objHttpServletRequest.getParameter("secName") != null)	{
				strSecName = objHttpServletRequest.getParameter("secName");
			}
			LogUtil.logMessage("Sec Name::::" + strSecName);
			
			if ((String) objHttpServletRequest.getParameter("secSeqNo") != null) {
				intSecSeqNo = Integer.parseInt(objHttpServletRequest.getParameter("secSeqNo").toString());
			}
			LogUtil.logMessage("Sec Seq No::::" + intSecSeqNo);
			
			//Added for bringing back to the retrieved clause SubSection
			if ((String) objHttpServletRequest.getParameter("subSecSeqNo") != null) {
				objHttpServletRequest.setAttribute("subsecseqno",
						(String) objHttpServletRequest.getParameter("subSecSeqNo"));
			}
			LogUtil.logMessage("Sub Sec Seq no :"+objHttpServletRequest.getAttribute("subsecseqno"));
			//Ends here
			objClauseVo.setOrderKey(intOrdeKey);
			objClauseVo.setDataLocationType(DatabaseConstants.DATALOCATION);
			objClauseVo.setUserID(objLoginVo.getUserID());
			objClauseVo.setClauseSeqNo(intClauseSeqNo);
			objClauseVo.setCurrRevFlag(strCurrRevFalg);
			objClauseVo.setReason(" ");
			OrderClauseBI objOrderClauseBo = ServiceFactory.getOrderClauseBO();
			intStatus=objOrderClauseBo.retrieveDeletedClause(objClauseVo);
			
			if (intStatus == 0)	{
				objOrderSectionForm.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				
				objOrderSectionForm.setOrderKey(intOrdeKey);
				objOrderSectionForm.setOrderSecCode(strSecCode);
				objOrderSectionForm.setOrderSecName(strSecName);
				objOrderSectionForm.setOrderSecSeqNo(intSecSeqNo);
				objOrderSectionForm.setRevCode(intRevCode);
				
				SectionVO objSectionVO = new SectionVO(); 
				
				objSectionVO.setUserID(objLoginVo.getUserID());
				objSectionVO.setSectionSeqNo(intSecSeqNo);
				objSectionVO.setSectionCode(strSecCode);
				objSectionVO.setSectionName(strSecName);
				objSectionVO.setOrderKey(intOrdeKey);
				objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
				objSectionVO.setRevisionInput(intRevCode);//default set as No revision
				
				/**
				 * populating the Revision drop down code starts here.
				 */
				
				RevisionVO objRevisionVO = new RevisionVO();
				objRevisionVO.setUserID(objLoginVo.getUserID());
				
				OrderSectionBI objOrderSectionBI = ServiceFactory
				.getOrderSectionBO();
				
				arlRevList = objOrderSectionBI.fetchRevision(objRevisionVO);
				
				if (arlRevList != null) {
					objOrderSectionForm.setRevisionList(arlRevList);
				}
				
				/**
				 * Ends here
				 */
				arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
				
				if (arlSectionList != null) {
					objOrderSectionForm.setSectionList(arlSectionList);
				}
				//Added for CR_109 to bring New Subsections to Order
				objSectionVO.setNewSubSecFlag(ApplicationConstants.YES);
				//Added for CR_109 to bring New Subsections to Order

				arlSectionDetails = objOrderSectionBI
				.fetchSectionDetails(objSectionVO);
				
				objOrderSectionForm.setSecDetail(arlSectionDetails);
						
				objOrderVO = new OrderVO();
							
				objOrderVO.setOrderKey(intOrdeKey);
				objOrderVO.setUserID(objLoginVo.getUserID());
				objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
				OrderBI objOrderBI = ServiceFactory.getOrderBO();
				arlOrderList = objOrderBI.fetchOrders(objOrderVO);
				
				objOrderSectionForm.setOrderList(arlOrderList);
				//This is for getting the Spec Status Code to be passed to get the
				// next status
				OrderVO objOrderVOSpecStatus = null;
				if (arlOrderList != null && arlOrderList.size() > 0) {
					for (int i = 0; i < arlOrderList.size(); i++) {
						
						objOrderVOSpecStatus = (OrderVO) arlOrderList.get(i);
						intSpecStatusCode = objOrderVOSpecStatus
						.getSpecStatusCode();
						//					Added for CR-10 display of Reason based on Spec Status Code
						objOrderSectionForm.setCurrentSpecStatus(intSpecStatusCode);
									objOrderVO.setDynamicNoFlag(objOrderVOSpecStatus.getDynamicNoFlag());
					}
					
				}
				objOrderVOSpecStatus.setUserID(objLoginVo.getUserID());
				SpecStatusBI objSpecStatusBI = ServiceFactory.getSpecStatusBO();
				ArrayList arlSpecStatus = objSpecStatusBI
				.fetchSpecNextStatus(objOrderVOSpecStatus);
				objOrderSectionForm.setSpecStatus(arlSpecStatus);
				objOrderSectionForm.setHdnRevViewCode(String.valueOf(intRevCode));
				objOrderSectionForm.setRevCode(intRevCode);

				SpecTypeVo objSpecTypeVo = new SpecTypeVo();
				OrderVO objjOrderVO = (OrderVO) arlOrderList.get(0);
				objSpecTypeVo.setUserID(objLoginVo.getUserID());
				objSpecTypeVo.setSpectypeSeqno(objjOrderVO.getSpecTypeSeqNo());
				SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
				ArrayList arlSpecType = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
				if (arlSpecType.size() != 0) {
					
					SpecTypeVo objjSpecTypeVo = (SpecTypeVo) arlSpecType.get(0);
					
					if(objjSpecTypeVo.isPerfCurveMaintDisPlayFlag())
						objOrderSectionForm.setPerfCurveLinkFlag(ApplicationConstants.YES);
					else
						objOrderSectionForm.setPerfCurveLinkFlag(ApplicationConstants.NO);
				}
				
			}
			else
			{
				strForwardKey = ApplicationConstants.DELETED_CLAUSES_HISTORY_VIEW;
				OrderClauseBI objOrderClauseBO = ServiceFactory.getOrderClauseBO();
				
				ArrayList arlClauseDetails = objOrderClauseBO.deletedClausesHistoy(objClauseVo);
				
				if (arlClauseDetails != null)
				{
					objOrderSectionForm.setClauseDetail(arlClauseDetails);
					objOrderSectionForm.setOrderKey(intOrdeKey);
				}

				objOrderSectionForm.setMessageID("" + intStatus);
				//Added for CR-113 for QA-Fix
				objOrderSectionForm.setRedirectGenInfoPageFlag(objOrderSectionForm.getRedirectGenInfoPageFlag());
				LogUtil.logMessage("objOrderSectionForm.getRedirectGenInfoPageFlag():"+objOrderSectionForm.getRedirectGenInfoPageFlag());	
			}
		}
		catch (Exception objEx)
		{
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForwardKey);
	}
	/***************************************************************************
	 * This method is Used for Fetching Sections Details in order level
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
	
	public ActionForward saveNewOrderComp(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil
		.logMessage("Enters into OrderSectionAction:saveNewOrderComp");
		String strForwardKey = ApplicationConstants.SECTION_VIEW;
		
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlSectionDetails = null;
		ArrayList arlOrderList = null;
		ArrayList arlRevList = null;
		SectionVO objSectionVO = new SectionVO();
		OrderVO objOrderVO = null;
		OrderSectionForm objOrderSectionForm = (OrderSectionForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LogUtil.logMessage(objSession);
		
		/** ******* Used for test Work ********* */
		
		int intOrdeKey = 0;
		int intSecSeqNo = 0;
		int intRevCode = 0;
		String strSecCode = null;
		String strSecName = null;
		String strNewOrderNo = null;
		int intSpecStatusCode = 0;
		int intCompSeqNo=0;
		int intStat=0;
		String strRedirectFlag=null;
		String subSecSeqNo=null;
		ActionForward actionRedirect = null;
		
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			//Added for bringing back to the visited Save Component
			
			if ((String) objHttpServletRequest.getParameter("subsecno") != null) {
				subSecSeqNo=objHttpServletRequest.getParameter("subsecno");
				objHttpServletRequest
				.setAttribute("subsecseqno",
						(String) objHttpServletRequest
						.getParameter("subsecno"));
				
			}
			//Ends here
			
			if ((String) objHttpServletRequest.getParameter("orderKey") != null) {
				
				intOrdeKey = Integer.parseInt(objHttpServletRequest
						.getParameter("orderKey"));
				
			} else {
				
				intOrdeKey = objOrderSectionForm.getOrderKey();
				
			}
			if ((String) objHttpServletRequest.getParameter("secSeq") != null) {
				
				intSecSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("secSeq"));
				
			} else {
				
				intSecSeqNo = objOrderSectionForm.getOrderSecSeqNo();
				
			}
			
			if ((String) objHttpServletRequest.getParameter("revCode") != null) {
				
				intCompSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("revCode"));
			}
			
			else {
				intRevCode = 1;
			}
			
			if ((String) objHttpServletRequest.getParameter("compSeqNo") != null) {
				
				intCompSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("compSeqNo"));
			}
			
			strNewOrderNo = objOrderSectionForm.getNewOrderNo();
			
			objOrderSectionForm.setOrderKey(intOrdeKey);
			objOrderSectionForm.setOrderSecCode(strSecCode);
			objOrderSectionForm.setOrderSecName(strSecName);
			objOrderSectionForm.setOrderSecSeqNo(intSecSeqNo);
			objOrderSectionForm.setRevCode(intRevCode);
			
			objSectionVO.setUserID(objLoginVo.getUserID());
			objSectionVO.setSectionSeqNo(intSecSeqNo);
			objSectionVO.setSectionCode(strSecCode);
			objSectionVO.setSectionName(strSecName);
			objSectionVO.setOrderKey(intOrdeKey);
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSectionVO.setRevisionInput(intRevCode);//default set as No
			
			/**
			 * code for saving the NEW orderComponent
			 */
			
			ComponentVO objCompVO = new ComponentVO();
			
			objCompVO.setOrderKey(intOrdeKey);
			objCompVO.setNewOrderNo(strNewOrderNo);
			objCompVO.setComponentSeqNo(intCompSeqNo);
			objCompVO.setUserID(objLoginVo.getUserID());
			
			OrderSectionBI objOrderSecBI = ServiceFactory.getOrderSectionBO();
			intStat = objOrderSecBI.saveNewOrderComp(objCompVO);
			LogUtil.logMessage("status after execution: "+intStat);
			
			/**
			 * Ends here if success(intStat==0) then everything else to execute
			 */
			if (intStat == 0) {
				strRedirectFlag="Y";
				
		        actionRedirect = new ActionForward("OrderSection.do?method=fetchSectionDetails&orderKey="+intOrdeKey
                        +"&secSeq="+intSecSeqNo+"&revCode=1"+"&subsecno="+subSecSeqNo, true /* REDIRECT */);
		    }
			 else {
			    	strRedirectFlag="N";
			    	objOrderSectionForm.setMessageID("" + intStat);
			 	}
			
			
				
		} catch (Exception objEx) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		if(strRedirectFlag!=null && strRedirectFlag.equalsIgnoreCase("Y")){
			LogUtil.logMessage("FORWARD"+strRedirectFlag);
			return actionRedirect;
		   
		    }else{
		    	LogUtil.logMessage("FORWARD"+strForwardKey);
			return objActionMapping.findForward(strForwardKey);	
		
		  }
		
	}
	/***************************************************************************
	 * This method is for updating user level marker
	 * Added for LSDB_CR-99 by SD41630
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward addDelSalesVerEngg(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil
		.logMessage("Enters into OrderSectionAction:addAndDeleteClaVerSalesEnggPDF");
		String strForwardKey = ApplicationConstants.SECTION_VIEW;
		
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlSectionDetails = null;
		ArrayList arlOrderList = null;
		ArrayList arlRevList = null;
		SectionVO objSectionVO = new SectionVO();
		ClauseVO objClauseVo = new ClauseVO();
		OrderVO objOrderVO = null;
		OrderSectionForm objOrderSectionForm = (OrderSectionForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		int intOrdeKey = 0;
		int intClauseSeqNo = 0;
		int intSecSeqNo = 0;
		int intRevCode = 0;
		String strSecCode = null;
		String strSecName = null;
		int intSalesVer = 0;
		int intSubSecSeqNo = 0;
		
		//Added For CR_99 
		String strSalesVerFLAG=null;
		String strSalesVerDescription=null;
		
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if ((String) objHttpServletRequest.getParameter("orderKey") != null) {
				intOrdeKey = Integer.parseInt(objHttpServletRequest
						.getParameter("orderKey"));
			} else {
				intOrdeKey = objOrderSectionForm.getOrderKey();
			}
			
			if ((String) objHttpServletRequest.getParameter("clauseSeq") != null) {
				intClauseSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("clauseSeq").toString());
			}
			if ((String) objHttpServletRequest.getParameter("secSeq") != null) {
				intSecSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("secSeq"));
			} else {
				intSecSeqNo = objOrderSectionForm.getOrderSecSeqNo();
			}
			
			if (objOrderSectionForm.getRevCode() == 0) {
				intRevCode = 1;//Default No revision
			} else if ((String) objHttpServletRequest.getParameter("revCode") != null) {
				intRevCode = Integer.parseInt(objHttpServletRequest
						.getParameter("revCode"));
			} else {
				intRevCode = objOrderSectionForm.getRevCode();
			}
			
			if ((String) objHttpServletRequest.getParameter("subsecseqno") != null) {
				intSubSecSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("subsecseqno").toString());
				
				//Added for landing to subsection
				objHttpServletRequest.setAttribute("subsecseqno",
						(String) objHttpServletRequest
						.getParameter("subsecseqno"));
				
				//Ends here
				
			}
			
			if ((String) objHttpServletRequest.getParameter("salesVerFLAG") != null) {
				strSalesVerFLAG = objHttpServletRequest
						.getParameter("salesVerFLAG");
			}
						
			strSalesVerDescription = ApplicationUtil.trim(objOrderSectionForm.getSalesVerDescription());
			
			objClauseVo.setOrderKey(intOrdeKey);
			objClauseVo.setClauseSeqNo(intClauseSeqNo);
			objClauseVo.setSalesClaDesc(strSalesVerDescription);
			objClauseVo.setSalesVerFLAG(strSalesVerFLAG);
			objClauseVo.setUserID(objLoginVo.getUserID());
			OrderClauseBI objOrderClauseBo = ServiceFactory.getOrderClauseBO();
			intSalesVer = objOrderClauseBo.addDelSalesVerEnggSpec(objClauseVo);
			
			if (intSalesVer == 0) {
				objOrderSectionForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objOrderSectionForm.setMessageID("" + intSalesVer);
			}
			
			objOrderSectionForm.setOrderKey(intOrdeKey);
			objOrderSectionForm.setOrderSecCode(strSecCode);
			objOrderSectionForm.setOrderSecName(strSecName);
			objOrderSectionForm.setOrderSecSeqNo(intSecSeqNo);
			objOrderSectionForm.setRevCode(intRevCode);
			
			objSectionVO.setUserID(objLoginVo.getUserID());
			objSectionVO.setSectionSeqNo(intSecSeqNo);
			objSectionVO.setSectionCode(strSecCode);
			objSectionVO.setSectionName(strSecName);
			objSectionVO.setOrderKey(intOrdeKey);
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSectionVO.setRevisionInput(intRevCode);//default set as No
			// revision
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null) {
				objOrderSectionForm.setSectionList(arlSectionList);
			}
			
			/**
			 * populating the Revision drop down code starts here.
			 */
			
			RevisionVO objRevisionVO = new RevisionVO();
			objRevisionVO.setUserID(objLoginVo.getUserID());
			
			arlRevList = objOrderSectionBI.fetchRevision(objRevisionVO);
			
			if (arlRevList != null) {
				objOrderSectionForm.setRevisionList(arlRevList);
			}
			//Added for CR_109 to bring New Subsections to Order
			objSectionVO.setNewSubSecFlag(ApplicationConstants.YES);
			//Added for CR_109 to bring New Subsections to Order

			arlSectionDetails = objOrderSectionBI
			.fetchSectionDetails(objSectionVO);
			
			objOrderSectionForm.setSecDetail(arlSectionDetails);
			
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			objOrderVO = new OrderVO();
			
			objOrderVO.setOrderKey(intOrdeKey);
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objOrderSectionForm.setOrderList(arlOrderList);
			
			//This is for getting the Spec Status Code to be passed to get the
			// next status
			OrderVO objOrderVOSpecStatus = null;
			if (arlOrderList != null && arlOrderList.size() > 0) {
				for (int i = 0; i < arlOrderList.size(); i++) {
					objOrderVOSpecStatus = (OrderVO) arlOrderList.get(i);
					
				}
			}
			objOrderVOSpecStatus.setUserID(objLoginVo.getUserID());
			SpecStatusBI objSpecStatusBI = ServiceFactory.getSpecStatusBO();
			ArrayList arlSpecStatus = objSpecStatusBI
			.fetchSpecNextStatus(objOrderVOSpecStatus);
			objOrderSectionForm.setSpecStatus(arlSpecStatus);
			
		} catch (Exception objEx) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
//	Added For CR_104
	/***************************************************************************
	 * This method is for editing notification mail and then publish
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
	public ActionForward ordSecPublishAndNotification(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into OrderSectionAction:ordSecPublishAndNotification");
		
		String strForwardKey = ApplicationConstants.SUCCESS;
		int modSeqNo = 0;
		int orderKey = 0;
		int revCode=1;
		String revFlag=null;
		ActionForward actionRedirect = null;
		int specStatusCode=0;
		String finalFlag="";
		String actionType = null;
		int intStatusCode=0;
		String strRedirectFlag=null;
		String strActionType=null;
		
		OrderSectionForm objOrderSectionForm = (OrderSectionForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		OrderVO objOrderVO= new OrderVO();
		modSeqNo = Integer.parseInt(objHttpServletRequest
				.getParameter("modSeqNo"));
		if (objHttpServletRequest.getParameter("orderKey") != null
				&& objHttpServletRequest.getParameter("orderKey") != "") {
			orderKey = Integer.parseInt(objHttpServletRequest
					.getParameter("orderKey"));
			objOrderSectionForm.setOrderKey(orderKey);
		} else {
			orderKey=objOrderSectionForm.getOrderKey();
		}
				
		if ((String) objHttpServletRequest.getParameter("actionType") != null) {
			
			strActionType = objHttpServletRequest
			.getParameter("actionType");
		}
		
		if (objHttpServletRequest.getParameter("revCode") != null
				&& objHttpServletRequest.getParameter("revCode") != "") {
			revCode = Integer.parseInt(objHttpServletRequest
					.getParameter("revCode"));
			objOrderVO.setRevCode(revCode);
		} else {
			
			objOrderVO.setRevCode(objOrderSectionForm.getRevCode());
		}
		try {
			
			
			specStatusCode=objOrderSectionForm.getSpecStatusCode();
			revFlag=objOrderSectionForm.getRevFlag();
			if(null!=(objOrderSectionForm.getFinalFlag())){
			finalFlag=objOrderSectionForm.getFinalFlag();
			}else{
				finalFlag="";
			}
			
			LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			//OrderVO objOrderVO = new OrderVO();
			
			objOrderVO.setOrderKey(orderKey);
			objOrderVO.setSubject(objOrderSectionForm.getSubject());
			objOrderVO.setBodyCont(objOrderSectionForm.getBodyCont());
			objOrderVO.setRevFlag(revFlag);
			objOrderVO.setFinalFlag(finalFlag);
			objOrderVO.setRevCode(revCode);
			objOrderVO.setUserID(objLoginVo.getUserID());
			
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			OrderVO objOrder = objOrderBI.ordSecPublishAndNotification(objOrderVO);
			if (intStatusCode == 0) {
				strRedirectFlag="Y";
				
				actionRedirect = new ActionForward("MainFeatureInfo.do?method=fetchComponentDesc&orderKeyId="
						+ objOrder.getOrderKey() + "&specStatus="
						+ objOrderSectionForm.getSpecStatusCode()
						+ "&actionType=" + strActionType, true /* REDIRECT */
				);
			
				
		    }
			 else {
			    	strRedirectFlag="N";
			    	objOrderSectionForm.setMessageID("" + intStatusCode);
			}
       
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception Block in Action..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			  			
		}
	return actionRedirect;
	}
	
	
//Added For CR_104 - Preserve User Markers by ER91220
	
	/***************************************************************************
	 * This method is Used for turn ON/OFF Preserve user Markers
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

	public ActionForward turnUserMarker(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		String strForwardKey = ApplicationConstants.SECTION_VIEW;
		
		LogUtil.logMessage("Enters into the function for Turn User Marker FlagEnters into the function for Turn User Marker FlagEnters into the function for Turn User Marker Flag");
		
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlSectionDetails = new ArrayList();
		ArrayList arlOrderList = new ArrayList();
		ArrayList arlRevList = new ArrayList();
		SectionVO objSectionVO = new SectionVO();
		OrderVO objOrderVO = new OrderVO();
		OrderSectionForm objOrderSectionForm = (OrderSectionForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		int intOrdeKey = 0;
		int intStatusCode = 0;
		int intRevCode = 0;
		int intSecSeqNo = 0;
		String strUserMarkerFlag = null;
		String strSecCode = null;
		String strSecName = null;
		
		try {
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if ((String) objHttpServletRequest.getParameter("orderKey") != null) {
				intOrdeKey = Integer.parseInt(objHttpServletRequest
						.getParameter("orderKey"));
			} else {
				intOrdeKey = objOrderSectionForm.getOrderKey();
			}
			
			if (objOrderSectionForm.getRevCode() == 0) {
				intRevCode = 1;//Default No revision
			} else if ((String) objHttpServletRequest.getParameter("revCode") != null) {
				intRevCode = Integer.parseInt(objHttpServletRequest
						.getParameter("revCode"));
			} else {
				intRevCode = objOrderSectionForm.getRevCode();
			}			

			if ((String) objHttpServletRequest.getParameter("flag") != null) {
				
				strUserMarkerFlag = (String) objHttpServletRequest.getParameter("flag");
				
			}
			
			objOrderVO.setOrderKey(intOrdeKey);
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			objOrderVO.setUserMarkerFlag(strUserMarkerFlag);
			
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			intStatusCode = objOrderBI.updateOrders(objOrderVO);
			
			if (intStatusCode == 0) {
				objOrderSectionForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objOrderSectionForm.setMessageID("" + intStatusCode);
			}

			intSecSeqNo = objOrderSectionForm.getOrderSecSeqNo();
			strSecCode = objOrderSectionForm.getOrderSecCode();
			strSecName = objOrderSectionForm.getOrderSecName();
			objOrderSectionForm.setOrderKey(intOrdeKey);
			objOrderSectionForm.setOrderSecCode(strSecCode);
			objOrderSectionForm.setOrderSecName(strSecName);
			objOrderSectionForm.setOrderSecSeqNo(intSecSeqNo);
			objOrderSectionForm.setRevCode(intRevCode);
			
			objSectionVO.setUserID(objLoginVo.getUserID());
			objSectionVO.setSectionSeqNo(intSecSeqNo);
			objSectionVO.setOrderKey(intOrdeKey);
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSectionVO.setRevisionInput(intRevCode);//default set as No revision
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null) {
				objOrderSectionForm.setSectionList(arlSectionList);
			}
			
			/**
			 * populating the Revision drop down code starts here.
			 */
			
			RevisionVO objRevisionVO = new RevisionVO();
			objRevisionVO.setUserID(objLoginVo.getUserID());
			
			arlRevList = objOrderSectionBI.fetchRevision(objRevisionVO);
			
			if (arlRevList != null) {
				objOrderSectionForm.setRevisionList(arlRevList);
			}
			/**
			 * Ends Here
			 */
			//Added for CR_109 to bring New Subsections to Order
			objSectionVO.setNewSubSecFlag(ApplicationConstants.YES);
			//Added for CR_109 to bring New Subsections to Order

			arlSectionDetails = objOrderSectionBI
			.fetchSectionDetails(objSectionVO);
			objOrderSectionForm.setSecDetail(arlSectionDetails);
			objOrderVO.setOrderKey(objOrderSectionForm.getOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			
			//Displaying Order Info
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objOrderSectionForm.setOrderList(arlOrderList);
			
			//This is for getting the Spec Status Code to be passed to get the
			// next status
			int intSpecStatusCode = 0;
			OrderVO objOrderVOSpecStatus = null;
			if (arlOrderList != null && arlOrderList.size() > 0) {
				for (int i = 0; i < arlOrderList.size(); i++) {
					
					objOrderVOSpecStatus = (OrderVO) arlOrderList.get(i);
					intSpecStatusCode = objOrderVOSpecStatus
					.getSpecStatusCode();
				}
				
			}
			objOrderVOSpecStatus.setUserID(objLoginVo.getUserID());
			SpecStatusBI objSpecStatusBI = ServiceFactory.getSpecStatusBO();
			ArrayList arlSpecStatus = objSpecStatusBI
			.fetchSpecNextStatus(objOrderVOSpecStatus);
			objOrderSectionForm.setSpecStatus(arlSpecStatus);
			
		} catch (Exception objExp) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			LogUtil
			.logMessage(" Exception occured in OrderSectionAction:turnUserMarkerFlag"
					+ objExp);
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);

		}
		return objActionMapping.findForward(strForwardKey);
	}
	
//Added For CR_106 for display logo
	
	/***************************************************************************
	 * This method is Used for turn ON/OFF 
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

	public ActionForward turnLogoONOFF(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		String strForwardKey = ApplicationConstants.SECTION_VIEW;
		
		LogUtil.logMessage("Enters into the function for Turn User Marker FlagEnters into the function for Turn User Marker FlagEnters into the function for Turn User Marker Flag");
		
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlSectionDetails = new ArrayList();
		ArrayList arlOrderList = new ArrayList();
		ArrayList arlRevList = new ArrayList();
		SectionVO objSectionVO = new SectionVO();
		OrderVO objOrderVO = new OrderVO();
		OrderSectionForm objOrderSectionForm = (OrderSectionForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		int intOrdeKey = 0;
		int intStatusCode = 0;
		int intRevCode = 0;
		int intSecSeqNo = 0;
		String strUserMarkerFlag = null;
		String strSecCode = null;
		String strSecName = null;
		//Added for CR_106 logo flag
		String strCustLogoFlag=null;
		String strDistriLogoFlag=null;
		
		try {
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if ((String) objHttpServletRequest.getParameter("orderKey") != null) {
				intOrdeKey = Integer.parseInt(objHttpServletRequest
						.getParameter("orderKey"));
			} else {
				intOrdeKey = objOrderSectionForm.getOrderKey();
			}
			
			if (objOrderSectionForm.getRevCode() == 0) {
				intRevCode = 1;//Default No revision
			} else if ((String) objHttpServletRequest.getParameter("revCode") != null) {
				intRevCode = Integer.parseInt(objHttpServletRequest
						.getParameter("revCode"));
			} else {
				intRevCode = objOrderSectionForm.getRevCode();
			}			

			if ((String) objHttpServletRequest.getParameter("distriLogoFlag") != null) {
							
				strDistriLogoFlag = (String) objHttpServletRequest.getParameter("distriLogoFlag");
				}
						
			if ((String) objHttpServletRequest.getParameter("custLogoFlag") != null) {
							
				strCustLogoFlag = (String) objHttpServletRequest.getParameter("custLogoFlag");
			   }
			
			objOrderVO.setOrderKey(intOrdeKey);
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			//objOrderVO.setUserMarkerFlag(strUserMarkerFlag);
			if(strCustLogoFlag !=null ||strCustLogoFlag !="" )
			{
			objOrderVO.setCustLogoFlag(strCustLogoFlag);
			}
			if(strDistriLogoFlag !=null ||strDistriLogoFlag !="" )
			{
				objOrderVO.setDistriLogoFlag(strDistriLogoFlag);
			}
			
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			intStatusCode = objOrderBI.updateOrders(objOrderVO);
			
			if (intStatusCode == 0) {
				objOrderSectionForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objOrderSectionForm.setMessageID("" + intStatusCode);
			}

			intSecSeqNo = objOrderSectionForm.getOrderSecSeqNo();
			strSecCode = objOrderSectionForm.getOrderSecCode();
			strSecName = objOrderSectionForm.getOrderSecName();
			objOrderSectionForm.setOrderKey(intOrdeKey);
			objOrderSectionForm.setOrderSecCode(strSecCode);
			objOrderSectionForm.setOrderSecName(strSecName);
			objOrderSectionForm.setOrderSecSeqNo(intSecSeqNo);
			objOrderSectionForm.setRevCode(intRevCode);
			
			objSectionVO.setUserID(objLoginVo.getUserID());
			objSectionVO.setSectionSeqNo(intSecSeqNo);
			objSectionVO.setOrderKey(intOrdeKey);
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSectionVO.setRevisionInput(intRevCode);//default set as No revision
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null) {
				objOrderSectionForm.setSectionList(arlSectionList);
			}
			
			/**
			 * populating the Revision drop down code starts here.
			 */
			
			RevisionVO objRevisionVO = new RevisionVO();
			objRevisionVO.setUserID(objLoginVo.getUserID());
			
			arlRevList = objOrderSectionBI.fetchRevision(objRevisionVO);
			
			if (arlRevList != null) {
				objOrderSectionForm.setRevisionList(arlRevList);
			}
			/**
			 * Ends Here
			 */
			//Added for CR_109 to bring New Subsections to Order
			objSectionVO.setNewSubSecFlag(ApplicationConstants.YES);
			//Added for CR_109 to bring New Subsections to Order

			arlSectionDetails = objOrderSectionBI
			.fetchSectionDetails(objSectionVO);
			objOrderSectionForm.setSecDetail(arlSectionDetails);
			objOrderVO.setOrderKey(objOrderSectionForm.getOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			
			//Displaying Order Info
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objOrderSectionForm.setOrderList(arlOrderList);
			
			//This is for getting the Spec Status Code to be passed to get the
			// next status
			int intSpecStatusCode = 0;
			OrderVO objOrderVOSpecStatus = null;
			if (arlOrderList != null && arlOrderList.size() > 0) {
				for (int i = 0; i < arlOrderList.size(); i++) {
					
					objOrderVOSpecStatus = (OrderVO) arlOrderList.get(i);
					intSpecStatusCode = objOrderVOSpecStatus
					.getSpecStatusCode();
				}
				
			}
			objOrderVOSpecStatus.setUserID(objLoginVo.getUserID());
			SpecStatusBI objSpecStatusBI = ServiceFactory.getSpecStatusBO();
			ArrayList arlSpecStatus = objSpecStatusBI
			.fetchSpecNextStatus(objOrderVOSpecStatus);
			objOrderSectionForm.setSpecStatus(arlSpecStatus);
			
		} catch (Exception objExp) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			LogUtil
			.logMessage(" Exception occured in OrderSectionAction:turnUserMarkerFlag"
					+ objExp);
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);

		}
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***************************************************************************
	 * Added for LSDB_CR-109 to bring New Subsections to Order by RR68151
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward addNewSubsecToOrder(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into OrderSectionAction:addNewSubsecToOrder");
		String strForwardKey = ApplicationConstants.SECTION_VIEW;

		ActionForward actionRedirect = null;
		SectionVO objSectionVO = new SectionVO();
		OrderSectionForm objOrderSectionForm = (OrderSectionForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		int intOrdeKey = 0;
		int intSecSeqNo = 0;
		int intRevCode = 0;
		int intAddNewSubSec = 0;
		int intSubSecSeqNo = 0;
		String strRedirectFlag = null;
		
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if ((String) objHttpServletRequest.getParameter("orderKey") != null) {
				intOrdeKey = Integer.parseInt(objHttpServletRequest
						.getParameter("orderKey"));
			} else {
				intOrdeKey = objOrderSectionForm.getOrderKey();
			}

			if ((String) objHttpServletRequest.getParameter("secSeq") != null) {
				intSecSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("secSeq"));
			} else {
				intSecSeqNo = objOrderSectionForm.getOrderSecSeqNo();
			}
			
			if (objOrderSectionForm.getRevCode() == 0) {
				intRevCode = 1;//Default No revision
			} else if ((String) objHttpServletRequest.getParameter("revCode") != null) {
				intRevCode = Integer.parseInt(objHttpServletRequest
						.getParameter("revCode"));
			} else {
				intRevCode = objOrderSectionForm.getRevCode();
			}
			
			if ((String) objHttpServletRequest.getParameter("subsecseqno") != null) {
				intSubSecSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("subsecseqno").toString());
				
				objHttpServletRequest.setAttribute("subsecseqno",(String) objHttpServletRequest
						.getParameter("subsecseqno"));	
			}
			
			objSectionVO.setUserID(objLoginVo.getUserID());
			objSectionVO.setOrderKey(intOrdeKey);
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSectionVO.setRevisionInput(intRevCode);
			objSectionVO.setSubSecSeqNo(intSubSecSeqNo);		
			
			OrderSectionBI objOrderSectionBI = ServiceFactory.getOrderSectionBO();
			intAddNewSubSec = objOrderSectionBI.addNewSubsecToOrder(objSectionVO);
			
			if (intAddNewSubSec == 0) {
				strRedirectFlag="Y";				
		        actionRedirect = new ActionForward("OrderSection.do?method=fetchSectionDetails&orderKey="+intOrdeKey
                        +"&secSeq="+intSecSeqNo+"&revCode=1"+"&subsecno="+intSubSecSeqNo, true /* REDIRECT */);
		    }
			 else {
		    	strRedirectFlag="N";
		    	objOrderSectionForm.setMessageID("" + intAddNewSubSec);
			 }
			
			if (intAddNewSubSec == 0) {
				objOrderSectionForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objOrderSectionForm.setMessageID("" + intAddNewSubSec);
			}
		} catch (Exception objEx) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		if(strRedirectFlag!=null && strRedirectFlag.equalsIgnoreCase("Y"))
			return actionRedirect;
		else
		    return objActionMapping.findForward(strForwardKey);		
	}
	
	/***************************************************************************
	 * This method is for populating the which are the Clauses with Indicators
	 * Added for CR_113 for view Clauses with Indicators
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward clausesWithIndicators(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into OrderSectionAction:ClauseswithIndicators");
		String strForwardKey = ApplicationConstants.CLAUSES_WITH_INDICATORS;
		
		ClauseVO objClauseVo = new ClauseVO();
		
		OrderSectionForm objOrderSectionForm = (OrderSectionForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		ArrayList arlSectionDetails=null;
		
		int intOrdeKey = 0;
		int intSecSeqNo=0;
		//Added for CR-113 QA Fix
		String strRedirectGenInfoPageFlag = null;
		
		try {
			LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if ((String) objHttpServletRequest.getParameter("orderKey") != null){
				intOrdeKey = Integer.parseInt(objHttpServletRequest.getParameter("orderKey"));
			}
			else {
				intOrdeKey = objOrderSectionForm.getOrderKey();
			}
			if ((String) objHttpServletRequest.getParameter("secSeqNo") != null) {
				intSecSeqNo = Integer.parseInt(objHttpServletRequest.getParameter("secSeqNo").toString());
			}
			LogUtil.logMessage("Sec Seq No::::" + intSecSeqNo);
			//Added for CR-113 QA Fix
			if ((String) objHttpServletRequest.getParameter("RedirectGenInfoPageFlag") != null) {
				strRedirectGenInfoPageFlag = objHttpServletRequest.getParameter("RedirectGenInfoPageFlag");
				objOrderSectionForm.setRedirectGenInfoPageFlag(strRedirectGenInfoPageFlag);
			}else{
				objOrderSectionForm.setRedirectGenInfoPageFlag(ApplicationConstants.NO);
			}
			LogUtil.logMessage("strRedirectGenInfoPageFlag::::" + strRedirectGenInfoPageFlag);
			//Added for CR-113 QA Fix Ends Here	
			objClauseVo.setOrderKey(intOrdeKey);
			objClauseVo.setDataLocationType(DatabaseConstants.DATALOCATION);
			objClauseVo.setUserID(objLoginVo.getUserID());
						
			LogUtil.logMessage("intOrdeKey::::" + intOrdeKey);
			LogUtil.logMessage("dataloc::::"+ objClauseVo.getDataLocationType());
			
			OrderClauseBI objOrderClauseBo = ServiceFactory.getOrderClauseBO();
			arlSectionDetails = objOrderClauseBo.clausesWithIndicators(objClauseVo);
			
			if (arlSectionDetails != null) {
				LogUtil.logMessage("Inside arlSectionDetails IF : ");
				objOrderSectionForm.setSecDetail(arlSectionDetails);
				objOrderSectionForm.setOrderKey(intOrdeKey);
				objOrderSectionForm.setOrderSecSeqNo(intSecSeqNo);
				LogUtil.logMessage("objOrderSectionForm.getSecDetail(): "+objOrderSectionForm.getSecDetail());
			} 
			LogUtil.logMessage("ArrayList size in action : "+objOrderSectionForm.getSecDetail().size());
		} 
		catch (Exception objEx)
		{
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForwardKey);
	}
	
	//Added for CR-121
	
	/***************************************************************************
	 * This method is for delete optional clauses
	 * Added for CR_121 for delete optional clauses
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward removeOptionalClauses(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into OrderSectionAction:removeOptionalClauses");
		String strForwardKey = ApplicationConstants.SECTION_VIEW;
		
		
		
		OrderSectionForm objOrderSectionForm = (OrderSectionForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		
		int intOrdeKey = 0;
		int intDeleteOptClause = 0;
		int intSecSeqNo = 0;
		int intRevCode = 1;
		String strSecCode = null;
		String strSecName = null;
		SectionVO objSectionVO = new SectionVO();
		ClauseVO objClauseVo = new ClauseVO();
		OrderVO objOrderVO = null;
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlSectionDetails = null;
		ArrayList arlOrderList = null;
		ArrayList arlRevList = null;
		
		try {
			LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if ((String) objHttpServletRequest.getParameter("orderKey") != null){
				intOrdeKey = Integer.parseInt(objHttpServletRequest.getParameter("orderKey"));
			}
			else {
				intOrdeKey = objOrderSectionForm.getOrderKey();
			}
				
			objClauseVo.setOrderKey(intOrdeKey);
			objClauseVo.setDataLocationType(DatabaseConstants.DATALOCATION);
			objClauseVo.setRemoveAllOptCla(ApplicationConstants.YES);
			objClauseVo.setUserID(objLoginVo.getUserID());
						
			LogUtil.logMessage("intOrdeKey::::" + intOrdeKey);
			LogUtil.logMessage("dataloc::::"+ objClauseVo.getDataLocationType());
			LogUtil.logMessage("RemoveAllOptClaFlag::::"+ objClauseVo.getRemoveAllOptCla());
			
			OrderClauseBI objOrderClauseBo = ServiceFactory.getOrderClauseBO();
			intDeleteOptClause = objOrderClauseBo.removeOptionalClauses(objClauseVo);
			
			LogUtil.logMessage("intDeleteOptClause : "+intDeleteOptClause);
			
			intSecSeqNo = objOrderSectionForm.getOrderSecSeqNo();
			
			objOrderSectionForm.setOrderKey(intOrdeKey);
			objOrderSectionForm.setOrderSecCode(strSecCode);
			objOrderSectionForm.setOrderSecName(strSecName);
			objOrderSectionForm.setOrderSecSeqNo(intSecSeqNo);
			objOrderSectionForm.setRevCode(intRevCode);
			
			objSectionVO.setUserID(objLoginVo.getUserID());
			objSectionVO.setSectionSeqNo(intSecSeqNo);
			objSectionVO.setSectionCode(strSecCode);
			objSectionVO.setSectionName(strSecName);
			objSectionVO.setOrderKey(intOrdeKey);
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSectionVO.setRevisionInput(intRevCode);//default set as No
			// revision
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null) {
				objOrderSectionForm.setSectionList(arlSectionList);
			}
			
			/**
			 * populating the Revision drop down code starts here.
			 */
			
			RevisionVO objRevisionVO = new RevisionVO();
			objRevisionVO.setUserID(objLoginVo.getUserID());
			
			arlRevList = objOrderSectionBI.fetchRevision(objRevisionVO);
			
			if (arlRevList != null) {
				objOrderSectionForm.setRevisionList(arlRevList);
			}
			/**
			 * Ends Here
			 */
			//Added for CR_109 to bring New Subsections to Order
			objSectionVO.setNewSubSecFlag(ApplicationConstants.YES);
			//Added for CR_109 to bring New Subsections to Order

			arlSectionDetails = objOrderSectionBI
			.fetchSectionDetails(objSectionVO);
			
			objOrderSectionForm.setSecDetail(arlSectionDetails);
			
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			objOrderVO = new OrderVO();
			
			objOrderVO.setOrderKey(intOrdeKey);
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objOrderSectionForm.setOrderList(arlOrderList);
			
			//This is for getting the Spec Status Code to be passed to get the
			// next status
			OrderVO objOrderVOSpecStatus = null;
			if (arlOrderList != null && arlOrderList.size() > 0) {
				for (int i = 0; i < arlOrderList.size(); i++) {
					objOrderVOSpecStatus = (OrderVO) arlOrderList.get(i);
					
				}
			}
			objOrderVOSpecStatus.setUserID(objLoginVo.getUserID());
			SpecStatusBI objSpecStatusBI = ServiceFactory.getSpecStatusBO();
			ArrayList arlSpecStatus = objSpecStatusBI
			.fetchSpecNextStatus(objOrderVOSpecStatus);
			objOrderSectionForm.setSpecStatus(arlSpecStatus);
			
			if (intDeleteOptClause == 0) {
				objOrderSectionForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objOrderSectionForm.setMessageID("" + intDeleteOptClause);
			}
			
		} 
		catch (Exception objEx)
		{
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForwardKey);
	}
	
	
}