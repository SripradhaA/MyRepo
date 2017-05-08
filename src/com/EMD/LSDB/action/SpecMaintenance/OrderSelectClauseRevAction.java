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

import com.EMD.LSDB.action.common.EMDAction;
import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.AcceptRejectClauseBI;
import com.EMD.LSDB.bo.serviceinterface.AppendixBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.constant.DatabaseConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.form.SpecMaintenance.AcceptRejectClauseForm;
import com.EMD.LSDB.vo.common.AppendixVO;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.LoginVO;

/**
 * @author AK49339
 *
 */
public class OrderSelectClauseRevAction extends EMDAction {
	
	/*******************************************************************************************
	 *  * * *		This Method is used to Search all versions of Clause
	 *  
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return     	ActionForward  		   
	 * @throws     	EMDException
	 
	 ******************************************************************************************/
	
	public ActionForward fetchClauseVersions(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil
		.logMessage("Enters into OrderSelectClauseRevAction:fetchClauseVersions");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList arlClauseList = null;
		int intOrderKey = 0;
		int intClauseSeqNo = 0;
		int intsecSeq = 0;
		int intrevCode = 0;
		//int intsubSecSeqNo=0;
		//int intmodelSeqNo=0;
		AcceptRejectClauseForm objAcceptRejectClauseForm = (AcceptRejectClauseForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			LogUtil.logMessage("orderkey:"
					+ objHttpServletRequest.getParameter("orderKey"));
			
			/* Added for landing subsection
			 * subsecno, request parameter used to carry back to the subsection part of modify spec
			 * where user was working 
			 */
			
			if ((String) objHttpServletRequest.getParameter("subsecno") != null) {
				
				objAcceptRejectClauseForm.setSubsecseqno(Integer
						.parseInt(objHttpServletRequest
								.getParameter("subsecno")));
			}
			//Ends
			if (objHttpServletRequest.getParameter("orderKey") != null) {
				
				intOrderKey = Integer.parseInt(objHttpServletRequest
						.getParameter("orderKey"));
				objAcceptRejectClauseForm.setOrderKey(intOrderKey);
			} else {
				objAcceptRejectClauseForm.setOrderKey(objAcceptRejectClauseForm
						.getOrderKey());
			}
			
			if (objHttpServletRequest.getParameter("clauseSeq") != null) {
				
				intClauseSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("clauseSeq"));
				objAcceptRejectClauseForm.setClauseSeqNo(intClauseSeqNo);
				LogUtil.logMessage("" + intClauseSeqNo);
			} else {
				objAcceptRejectClauseForm
				.setClauseSeqNo(objAcceptRejectClauseForm
						.getClauseSeqNo());
				LogUtil.logMessage("Test ClauseSeqNo:"
						+ objAcceptRejectClauseForm.getClauseSeqNo());
			}
			
			LogUtil.logMessage("SecSEQ:"
					+ objHttpServletRequest.getParameter("secSeq"));
			if (objHttpServletRequest.getParameter("secSeq") != null) {
				intsecSeq = Integer.parseInt(objHttpServletRequest
						.getParameter("secSeq"));
				objAcceptRejectClauseForm.setSecSeq(intsecSeq);
			} else {
				objAcceptRejectClauseForm.setSecSeq(objAcceptRejectClauseForm
						.getSecSeq());
			}
			
			if (objHttpServletRequest.getParameter("revCode") != null) {
				intrevCode = Integer.parseInt(objHttpServletRequest
						.getParameter("revCode"));
				objAcceptRejectClauseForm.setRevCode(intrevCode);
			} else {
				objAcceptRejectClauseForm.setRevCode(objAcceptRejectClauseForm
						.getRevCode());
			}
			
			/** Added For CustomerSeqNo Bug Fixing**/
			
			if (objHttpServletRequest.getParameter("custSeqNo") != null) {
				
				objAcceptRejectClauseForm.setCustomerSeqNo(Integer
						.parseInt(objHttpServletRequest
								.getParameter("custSeqNo")));
			} else {
				objAcceptRejectClauseForm
				.setCustomerSeqNo(objAcceptRejectClauseForm
						.getCustomerSeqNo());
			}
			
			/** Added For CustomerSeqNo Bug Fixing**/
			LogUtil.logMessage("check...");
			//Added for CR-10 display of Reason based on Spec Status Code
			
			if (objHttpServletRequest.getParameter("specCode") != null) {
				
				objAcceptRejectClauseForm.setSpecStatusCode(Integer
						.parseInt(objHttpServletRequest
								.getParameter("specCode")));
			} else {
				objAcceptRejectClauseForm
				.setSpecStatusCode(objAcceptRejectClauseForm
						.getSpecStatusCode());
			}
			
			ClauseVO objClauseVO = new ClauseVO();
			
			objClauseVO.setClauseSeqNo(objAcceptRejectClauseForm
					.getClauseSeqNo());
			objClauseVO.setOrderKey(objAcceptRejectClauseForm.getOrderKey());
			objClauseVO.setUserID(objLoginVo.getUserID());
			
			AcceptRejectClauseBI objAcceptRejectClauseBO = ServiceFactory
			.getAcceptRejectClauseBO();
			arlClauseList = objAcceptRejectClauseBO
			.fetchClauseVersions(objClauseVO);
			
			if (arlClauseList != null && arlClauseList.size() > 0) {
				
				LogUtil.logMessage("Size of Array List" + arlClauseList.size());
				objAcceptRejectClauseForm.setClauseVersions(arlClauseList);
				
			}
			
			//To Set Selected Clause Version No in hidden field
			for (int j = 0; j < arlClauseList.size(); j++) {
				objClauseVO = (ClauseVO) arlClauseList.get(j);
				if (objClauseVO.getDefaultFlag().equalsIgnoreCase("Y")) {
					objAcceptRejectClauseForm.setHdnClauseVersionNo(objClauseVO
							.getVersionNo());
				}
			}
			
			if (arlClauseList.size() == 0) {
				objAcceptRejectClauseForm.setMethod("ClauseVersions");
				
			}
			
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage(" Exception occured in Action:" + objExp);
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		LogUtil.logMessage("key" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/*******************************************************************************************
	 *  * * *		This Method is used to Apply a particular Clause version as default
	 *  
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return     	ActionForward  		   
	 * @throws     	EMDException
	 
	 ******************************************************************************************/
	
	public ActionForward updateClauseChange(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil
		.logMessage("Enters into OrderSelectClauseRevAction:updateClauseChange Method");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intUpdate = 0;
		int intUpdateForAppendixImageRemap =0;
		int intOrderKey = 0;
		int intClauseSeqNo = 0;
		int intsecSeq = 0;
		int intrevCode = 0;
		int intsubSecSeqNo=0;
		//Added for CR_114
		int mapAppendixImg =0;
		int appendixImgSeqNo=0;
		//Added for CR_114 Ends Here
		/*Added as per CR 73 to return to General Info Screen by CM68219 starts*/
		ActionForward actionRedirect = null;
		String strRedirectFlag=null;
		/*Added as per CR 73 to return to General Info Screen by CM68219 starts*/
		ArrayList arlClauseList = null;
		AcceptRejectClauseForm objAcceptRejectClauseForm = (AcceptRejectClauseForm) objActionForm;
		
		try {
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			
			if (objHttpServletRequest.getParameter("orderKey") != null) {
				
				intOrderKey = Integer.parseInt(objHttpServletRequest
						.getParameter("orderKey"));
			}
			if (objHttpServletRequest.getParameter("clauseSeq") != null) {
				
				intClauseSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("clauseSeq"));
			}
			if(objHttpServletRequest.getParameter("subsecno") !=null){
				
			       intsubSecSeqNo = Integer.parseInt(objHttpServletRequest
					.getParameter("subsecno"));
		    }
             if (objHttpServletRequest.getParameter("revCode") != null) {
 				intrevCode = Integer.parseInt(objHttpServletRequest
 						.getParameter("revCode"));
 				objAcceptRejectClauseForm.setRevCode(intrevCode);
 			} else {
 				objAcceptRejectClauseForm.setRevCode(objAcceptRejectClauseForm
 						.getRevCode());
 			}
             if (objHttpServletRequest.getParameter("secSeq") != null) {
 				intsecSeq = Integer.parseInt(objHttpServletRequest
 						.getParameter("secSeq"));
 				objAcceptRejectClauseForm.setSecSeq(intsecSeq);
 			} else {
 				objAcceptRejectClauseForm.setSecSeq(objAcceptRejectClauseForm
 						.getSecSeq());
 			}
          
			objAcceptRejectClauseForm.setOrderKey(intOrderKey);
			objAcceptRejectClauseForm.setClauseSeqNo(intClauseSeqNo);
			objAcceptRejectClauseForm.setIndFlag("N");
			
			ClauseVO objClauseVO = new ClauseVO();
			
			objClauseVO.setOrderKey(objAcceptRejectClauseForm.getOrderKey());
			objClauseVO.setClauseSeqNo(objAcceptRejectClauseForm
					.getClauseSeqNo());
			objClauseVO.setVersionNo(objAcceptRejectClauseForm
					.getHdnClauseVersionNo());
			objClauseVO.setFlag(objAcceptRejectClauseForm.getFlag());
			objClauseVO.setIndFlag(objAcceptRejectClauseForm.getIndFlag());
			objClauseVO.setReason(objAcceptRejectClauseForm.getReason());
			objClauseVO.setUserID(objLoginVo.getUserID());
			
			AcceptRejectClauseBI objAcceptRejectClauseBO = ServiceFactory
			.getAcceptRejectClauseBO();
			intUpdate = objAcceptRejectClauseBO.updateClauseChange(objClauseVO);
			
			//Added for CR_114
			
			LogUtil.logMessage("Value of MapAppendixImg is "+objAcceptRejectClauseForm.getMapAppendixImg());
			if(objAcceptRejectClauseForm.getMapAppendixImg()==1){
				AppendixVO objAppendixVO = new AppendixVO();
				
				objAppendixVO.setOrderKey(intOrderKey);
				objAppendixVO.setImageSeqNo(objAcceptRejectClauseForm.getAppendixImgSeqNo());
				objAppendixVO.setDataLocationType(DatabaseConstants.DATALOCATION);
				objAppendixVO.setClauseSeqNo(intClauseSeqNo);
				objAppendixVO.setVersionNo(objAcceptRejectClauseForm
						.getHdnClauseVersionNo());
				objAppendixVO.setUserID(objLoginVo.getUserID());
				
				AppendixBI objAppendixBI = ServiceFactory.getAppendixBO();
				intUpdateForAppendixImageRemap = objAppendixBI.saveMappings(objAppendixVO);
				
				if (intUpdateForAppendixImageRemap == 0) {
					objAcceptRejectClauseForm
					.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
					
				}
				
				if (intUpdateForAppendixImageRemap > 0) {
					objAcceptRejectClauseForm.setMessageID("" + intUpdateForAppendixImageRemap);
				}
				
				
			}
			//Added for CR_114 Ends Here
			
			objAcceptRejectClauseForm.setReason("");
			/*Added as per CR 73 to return to Modify Spec Screen by CM68219 starts*/
			if (intUpdate == 0) {
				strRedirectFlag="Y";
				objAcceptRejectClauseForm.setReason("");
				actionRedirect = new ActionForward("OrderSection.do?method=fetchSectionDetails&orderKey="+intOrderKey+"&secSeq="+intsecSeq+"&revCode="+intrevCode+"&subsecno="+intsubSecSeqNo, true /* REDIRECT */);	
			} else {
				strRedirectFlag="N";
				objAcceptRejectClauseForm.setMessageID("" + intUpdate);
			
			//Clause Versions fetch
			ClauseVO objjClauseVO = new ClauseVO();
			if (objHttpServletRequest.getParameter("orderKey") != null) {
				
				intOrderKey = Integer.parseInt(objHttpServletRequest
						.getParameter("orderKey"));
			}
			if (objHttpServletRequest.getParameter("clauseSeq") != null) {
				
				intClauseSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("clauseSeq"));
			}
			
			LogUtil.logMessage("intOrderKey :" + intOrderKey);
			LogUtil.logMessage("intClauseSeqNo :" + intClauseSeqNo);
			
			objClauseVO.setClauseSeqNo(intClauseSeqNo);
			objClauseVO.setOrderKey(intOrderKey);
			objClauseVO.setUserID(objLoginVo.getUserID());
						
			arlClauseList = objAcceptRejectClauseBO
			.fetchClauseVersions(objClauseVO);
			
			if (arlClauseList != null && arlClauseList.size() > 0) {
				
				LogUtil.logMessage("Size of Array List" + arlClauseList.size());
				objAcceptRejectClauseForm.setClauseVersions(arlClauseList);
				
			}
			
			//To Set Selected Clause Version No in hidden field
			for (int j = 0; j < arlClauseList.size(); j++) {
				objClauseVO = (ClauseVO) arlClauseList.get(j);
				if (objClauseVO.getDefaultFlag().equalsIgnoreCase("Y")) {
					objAcceptRejectClauseForm.setHdnClauseVersionNo(objClauseVO
							.getVersionNo());
				}
			}
			
			if (arlClauseList.size() == 0) {
				objAcceptRejectClauseForm.setMethod("ClauseVersions");
				
			}
			/*Added as per CR 73 to return to Modify Spec Screen by CM68219 starts*/
			}
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage(" Exception occured in Action:" + objExp);
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		/*Added as per CR 73 to return to Modify Spec Screen by CM68219 starts*/
		if(strRedirectFlag!=null && strRedirectFlag.equalsIgnoreCase("Y")){
			LogUtil.logMessage("FORWARD"+strRedirectFlag);
			return actionRedirect;
		   
		    }else{
		    	LogUtil.logMessage("FORWARD"+strForwardKey);
			return objActionMapping.findForward(strForwardKey);	
			
		}
		/*Added as per CR 73 to return to Modify Spec Screen by CM68219 starts*/
	}
	
}
