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
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.form.SpecMaintenance.AcceptRejectClauseForm;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.LoginVO;

/**
 * @author VV49326
 *
 */
public class AcceptRejectDeleteClauseAction extends EMDAction {
	
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
		.logMessage("Entering AcceptRejectDeleteClauseAction:fetchClauseVersions");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList arlClauseList = null;
		int intOrderKey = 0;
		int intClauseSeqNo = 0;
		int intSpecStatusCode = 0;
		/* Added for  landing into particular sub section on 13/02/09 as per CR 71 by cm68219*/
		int   intsubSecSeqNo=0;
		AcceptRejectClauseForm objAcceptRejectClauseForm = (AcceptRejectClauseForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			
			/* Added for  landing into particular sub section on 13/02/09 as per CR 71 by cm68219*/
			
			 intsubSecSeqNo = Integer.parseInt(objHttpServletRequest
					.getParameter("subSecSeqNo"));
			if (objHttpServletRequest.getParameter("orderKey") != null) {
				
				intOrderKey = Integer.parseInt(objHttpServletRequest
						.getParameter("orderKey"));
			}
			if (objHttpServletRequest.getParameter("clauseSeq") != null) {
				
				intClauseSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("clauseSeq"));
			}
			int intsecSeq = Integer.parseInt(objHttpServletRequest
					.getParameter("secSeq"));
			int intrevCode = Integer.parseInt(objHttpServletRequest
					.getParameter("revCode"));
			
			//Added for LSDB_CR-74
			if (objHttpServletRequest.getParameter("specCode") != null) {
				/*Modified for CR_79 issue with Reason validation on 04-Nov-09 by RR68151
				objAcceptRejectClauseForm.setSpecStatusCode(Integer.parseInt(objHttpServletRequest
						.getParameter("specCode")));*/
				intSpecStatusCode = Integer.parseInt(objHttpServletRequest
						.getParameter("specCode"));
			}
			
			
			objAcceptRejectClauseForm.setOrderKey(intOrderKey);
			objAcceptRejectClauseForm.setClauseSeqNo(intClauseSeqNo);
			objAcceptRejectClauseForm.setSecSeq(intsecSeq);
			objAcceptRejectClauseForm.setRevCode(intrevCode);
			objAcceptRejectClauseForm.setSpecStatusCode(intSpecStatusCode);
			/* Added for  landing into particular sub section on 13/02/09 as per CR 71 by cm68219*/
			objAcceptRejectClauseForm.setSubSecSeqNo(intsubSecSeqNo);
			ClauseVO objClauseVO = new ClauseVO();
			
			objClauseVO.setClauseSeqNo(intClauseSeqNo);
			objClauseVO.setOrderKey(intOrderKey);
			objClauseVO.setUserID(objLoginVo.getUserID());
			
			AcceptRejectClauseBI objAcceptRejectClauseBO = ServiceFactory
			.getAcceptRejectClauseBO();
			arlClauseList = objAcceptRejectClauseBO
			.fetchDeletedClauseVersions(objClauseVO);
			
			if (arlClauseList != null && arlClauseList.size() > 0) {
				
				LogUtil.logMessage("Size of Array List" + arlClauseList.size());
				objAcceptRejectClauseForm.setClauseVersions(arlClauseList);
				objAcceptRejectClauseForm.setSpecStatusCode(intSpecStatusCode);
				
				ClauseVO objClauseVo = (ClauseVO) arlClauseList.get(0);
				objAcceptRejectClauseForm
				.setFlag(objClauseVo.getClauseSource());
				objAcceptRejectClauseForm.setOrderKey(objAcceptRejectClauseForm
						.getOrderKey());
				LogUtil.logMessage("OrderKey"
						+ objAcceptRejectClauseForm.getOrderKey());
				objAcceptRejectClauseForm
				.setClauseSeqNo(objAcceptRejectClauseForm
						.getClauseSeqNo());
				objAcceptRejectClauseForm.setSecSeq(objAcceptRejectClauseForm
						.getSecSeq());
				objAcceptRejectClauseForm.setRevCode(objAcceptRejectClauseForm
						.getRevCode());
				objAcceptRejectClauseForm
				.setSpecStatusCode(objAcceptRejectClauseForm
						.getSpecStatusCode());
				/* Added for  landing into particular sub section on  13/02/09 as per CR 71 by cm68219*/
				objAcceptRejectClauseForm.setSubSecSeqNo(intsubSecSeqNo);
				
			}
			
			if (arlClauseList.size() == 0) {
				objAcceptRejectClauseForm.setMethod("ClauseVersions");
				objAcceptRejectClauseForm.setSpecStatusCode(intSpecStatusCode);
				objAcceptRejectClauseForm.setOrderKey(objAcceptRejectClauseForm
						.getOrderKey());
				objAcceptRejectClauseForm
				.setClauseSeqNo(objAcceptRejectClauseForm
						.getClauseSeqNo());
				objAcceptRejectClauseForm.setSecSeq(objAcceptRejectClauseForm
						.getSecSeq());
				objAcceptRejectClauseForm.setRevCode(objAcceptRejectClauseForm
						.getRevCode());
				objAcceptRejectClauseForm
				.setSpecStatusCode(objAcceptRejectClauseForm
						.getSpecStatusCode());
			/* Added for  landing into particular sub section on 13/02/09 as per CR 71 by cm68219*/
				objAcceptRejectClauseForm.setSubSecSeqNo(intsubSecSeqNo);
			}
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage(" Exception occured in AcceptRejectDeleteClauseAction:"
					+ objExp);
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
	 *  * * *		This Method is used to Accept/Reject Clause Deletion at Model Level
	 *  
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return     	ActionForward  		   
	 * @throws     	EMDException
	 
	 ******************************************************************************************/
	
	public ActionForward updateDeleteClause(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil
		.logMessage("Entering AcceptRejectDeleteClauseAction:updateDeleteClause");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intUpdate = 0;
		AcceptRejectClauseForm objAcceptRejectClauseForm = (AcceptRejectClauseForm) objActionForm;
		int intsubSecSeqNo=0;
		int intClauseSeqNo=0;
		/*Added as per CR 73 to return to General Info Screen by CM68219 starts*/
		ActionForward actionRedirect = null;
                String strRedirectFlag=null;
                int intOrderKey =0;
        /*Added as per CR 73 to return to General Info Screen by CM68219 ends*/
		
		try {
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			
			if(objHttpServletRequest.getParameter("subsecno") !=null){
				
			       intsubSecSeqNo = Integer.parseInt(objHttpServletRequest
					.getParameter("subsecno"));
		       }
                       if (objHttpServletRequest.getParameter("orderKey") != null) {
				
			 intOrderKey = Integer.parseInt(objHttpServletRequest
						.getParameter("orderKey"));
			}
			if (objHttpServletRequest.getParameter("clauseSeq") != null) {
				
				 intClauseSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("clauseSeq"));
			}
			int intsecSeq = Integer.parseInt(objHttpServletRequest
					.getParameter("secSeq"));
			int intrevCode = Integer.parseInt(objHttpServletRequest
					.getParameter("revCode"));
			
			objAcceptRejectClauseForm.setSubSecSeqNo(intsubSecSeqNo);
			LogUtil
			.logMessage("Exception occured in AcceptRejectDeleteClauseAction:"
					+ intsubSecSeqNo);
			objAcceptRejectClauseForm.setOrderKey(intOrderKey);
			LogUtil
			.logMessage("Exception occured in AcceptRejectDeleteClauseAction:"
					+ intOrderKey);
			objAcceptRejectClauseForm.setClauseSeqNo(objAcceptRejectClauseForm
					.getClauseSeqNo());
			objAcceptRejectClauseForm.setVersion(objAcceptRejectClauseForm
					.getVersion());
			objAcceptRejectClauseForm.setFlag(objAcceptRejectClauseForm
					.getFlag());
			objAcceptRejectClauseForm.setSecSeq(objAcceptRejectClauseForm
					.getSecSeq());
			objAcceptRejectClauseForm.setRevCode(objAcceptRejectClauseForm
					.getRevCode());
			
			ClauseVO objClauseVO = new ClauseVO();
			//Change for LSDB_CR-74
			LogUtil.logMessage("objAcceptRejectClauseForm.getReason() :"+objAcceptRejectClauseForm.getReason());
			objClauseVO.setReason(objAcceptRejectClauseForm.getReason());
			
			objClauseVO.setOrderKey(objAcceptRejectClauseForm.getOrderKey());
			objClauseVO.setClauseSeqNo(objAcceptRejectClauseForm
					.getClauseSeqNo());
			objClauseVO.setFlag(objAcceptRejectClauseForm.getFlag());
			objClauseVO.setUserID(objLoginVo.getUserID());
			
			AcceptRejectClauseBI objAcceptRejectClauseBO = ServiceFactory
			.getAcceptRejectClauseBO();
			intUpdate = objAcceptRejectClauseBO.updateDeleteClause(objClauseVO);
			/*Added as per CR 73 to return to Modify Spec Screen by CM68219 starts*/
			if (intUpdate == 0) {
				strRedirectFlag="Y";
				objAcceptRejectClauseForm.setReason("");
				actionRedirect = new ActionForward("OrderSection.do?method=fetchSectionDetails&orderKey="+intOrderKey+"&secSeq="+intsecSeq+"&revCode="+intrevCode+"&subsecno="+intsubSecSeqNo+"&clauseSeq="+intClauseSeqNo, true /* REDIRECT */);
				
			} else {
				strRedirectFlag="N";
				objAcceptRejectClauseForm.setMessageID("" + intUpdate);
				
			}
			 /*Added as per CR 73 to return to Modify Spec Screen by CM68219 ends*/
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Exception occured in AcceptRejectDeleteClauseAction:"
					+ objExp);
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
		}
		/*Added as per CR 73 to return to General Info Screen by CM68219 starts*/
		if(strRedirectFlag!=null && strRedirectFlag.equalsIgnoreCase("Y")){
			LogUtil.logMessage("FORWARD"+strRedirectFlag);
			return actionRedirect;
		   
		    }else{
		    	LogUtil.logMessage("FORWARD"+strForwardKey);
			return objActionMapping.findForward(strForwardKey);	
			
		}
		 /*Added as per CR 73 to return to General Info Screen by CM68219 ends*/
	}
	
}
