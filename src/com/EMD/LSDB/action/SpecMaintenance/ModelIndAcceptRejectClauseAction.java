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
 * @author VV49326
 *
 */
public class ModelIndAcceptRejectClauseAction extends EMDAction {
	
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
		.logMessage("Entering ModelIndAcceptRejectClauseAction:fetchClauseVersions");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList arlClauseList = null;
		int intOrderKey = 0;
		int intClauseSeqNo = 0;
		int intSpecStatusCode = 0;
		/* Added for  landing into particular sub section on 13/02/09 as per CR 71 by cm68219*/
		int intsubSecSeqNo =0;
		AcceptRejectClauseForm objAcceptRejectClauseForm = (AcceptRejectClauseForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			
			/* Added for  landing into particular sub section on 13/02/09 as per CR 71 by cm68219*/
			
			if(objHttpServletRequest.getParameter("subsecseqno") !=null){
		        intsubSecSeqNo = Integer.parseInt(objHttpServletRequest
					.getParameter("subsecseqno").toString().trim());
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
			
			if (objHttpServletRequest.getParameter("specCode") != null) {
				
				intSpecStatusCode = Integer.parseInt(objHttpServletRequest
						.getParameter("specCode"));
			}
			
			objAcceptRejectClauseForm.setOrderKey(intOrderKey);
			objAcceptRejectClauseForm.setClauseSeqNo(intClauseSeqNo);
			objAcceptRejectClauseForm.setSecSeq(intsecSeq);
			objAcceptRejectClauseForm.setRevCode(intrevCode);
			objAcceptRejectClauseForm.setSpecStatusCode(intSpecStatusCode);
			/* Added for  landing into particular sub section on  13/02/09 as per CR 71 by cm68219*/
			objAcceptRejectClauseForm.setSubSecSeqNo(intsubSecSeqNo);
			ClauseVO objClauseVO = new ClauseVO();
			
			objClauseVO.setClauseSeqNo(intClauseSeqNo);
			objClauseVO.setOrderKey(intOrderKey);
			objClauseVO.setUserID(objLoginVo.getUserID());
			
			AcceptRejectClauseBI objAcceptRejectClauseBO = ServiceFactory
			.getAcceptRejectClauseBO();
			arlClauseList = objAcceptRejectClauseBO
			.fetchClauseVersions(objClauseVO);
			
			if (arlClauseList != null && arlClauseList.size() > 0) {
				
				LogUtil.logMessage("Size of Array List" + arlClauseList.size());
				objAcceptRejectClauseForm.setClauseVersions(arlClauseList);
				objAcceptRejectClauseForm.setSpecStatusCode(intSpecStatusCode);
				/* Added for  landing into particular sub section on  13/02/09 as per CR 71 by cm68219*/
				objAcceptRejectClauseForm.setSubSecSeqNo(intsubSecSeqNo);
			}
			
			//To Set Selected Clause Version No in hidden field
			for (int j = 0; j < arlClauseList.size(); j++) {
				objClauseVO = (ClauseVO) arlClauseList.get(j);
				if (objClauseVO.getDefaultFlag().equalsIgnoreCase("Y")) {
					objAcceptRejectClauseForm.setHdnClauseVersionNo(objClauseVO
							.getVersionNo());
				}
			}
			
			// To Set Model Default Clause Version No in hidden field
			for (int j = 0; j < arlClauseList.size(); j++) {
				objClauseVO = (ClauseVO) arlClauseList.get(j);
				if (objClauseVO.getModelDefFlag().equalsIgnoreCase("Y")) {
					objAcceptRejectClauseForm.setVersion(objClauseVO
							.getVersionNo());
				}
			}
			
			if (arlClauseList.size() == 0) {
				objAcceptRejectClauseForm.setMethod("ClauseVersions");
				objAcceptRejectClauseForm.setSpecStatusCode(intSpecStatusCode);
				
			}
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage(" Exception occured in ModelIndAcceptRejectClauseAction:"
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
		.logMessage("Entering ModelIndAcceptRejectClauseAction:UpdateClauseChange");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intUpdate = 0;
		int intOrderKey = 0;
		int intClauseSeqNo = 0;
		int intsubSecSeqNo =0;


		/*Added as per CR 73 to return to Modify Spec  Screen by CM68219 starts*/
		String strRedirectFlag=null;
		ActionForward actionRedirect = null;
		/*Added as per CR 73 to return to Modify Spec Screen by CM68219 ends*/
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
			/*Added as per CR 73 to return to Modify Spec  Screen by CM68219 starts*/		
                       if ((String) objHttpServletRequest.getParameter("subsecno") != null) {
				
				
				
				 intsubSecSeqNo = Integer.parseInt(objHttpServletRequest
							.getParameter("subsecno").toString().trim());
				
			}
                         /*Added as per CR 73 to return to Modify Spec Screen by CM68219 ends*/
			int intsecSeq = Integer.parseInt(objHttpServletRequest
					.getParameter("secSeq"));
			int intrevCode = Integer.parseInt(objHttpServletRequest
					.getParameter("revCode"));
			
			objAcceptRejectClauseForm.setSecSeq(intsecSeq);
			objAcceptRejectClauseForm.setRevCode(intrevCode);
			objAcceptRejectClauseForm.setOrderKey(intOrderKey);
			objAcceptRejectClauseForm.setClauseSeqNo(intClauseSeqNo);
			objAcceptRejectClauseForm.setSubSecSeqNo(intsubSecSeqNo);
			objAcceptRejectClauseForm.setIndFlag("N");
			
			ClauseVO objClauseVO = new ClauseVO();
			
			objClauseVO.setOrderKey(objAcceptRejectClauseForm.getOrderKey());
			objClauseVO.setClauseSeqNo(objAcceptRejectClauseForm
					.getClauseSeqNo());
			//Added fro CR-134 starts here
			if (objAcceptRejectClauseForm.getDelModFlag()!= null){
				if(objAcceptRejectClauseForm.getDelModFlag().equalsIgnoreCase("Y")){
					LogUtil.logMessage("Inside if delmod flag");
					objClauseVO.setVersionNo(objAcceptRejectClauseForm.getModVersionNo());
					LogUtil.logMessage("objAcceptRejectClauseForm.getVersionNo()" +objAcceptRejectClauseForm.getModVersionNo());
				}else{
					LogUtil.logMessage("Inside else delmod flag");
					objClauseVO.setVersionNo(objAcceptRejectClauseForm.getVersion());
				}
			}else{
				objClauseVO.setVersionNo(objAcceptRejectClauseForm.getVersion());
			}
			LogUtil.logMessage("Version No in Action"+ objClauseVO.getVersionNo());
			//Added for CR-134 ends here
			objClauseVO.setFlag(objAcceptRejectClauseForm.getFlag());
			objClauseVO.setIndFlag(objAcceptRejectClauseForm.getIndFlag());
			objClauseVO.setReason(objAcceptRejectClauseForm.getReason());
			objClauseVO.setUserID(objLoginVo.getUserID());
			
			AcceptRejectClauseBI objAcceptRejectClauseBO = ServiceFactory
			.getAcceptRejectClauseBO();
			intUpdate = objAcceptRejectClauseBO.updateClauseChange(objClauseVO);
			
			/*Added as per CR 73 to return to Modify Spec  Screen by CM68219 starts*/
			if (intUpdate == 0) {
								
//				Added for CR_114
				LogUtil.logMessage("Value of MapAppendixImg boolean is "+objAcceptRejectClauseForm.getMapAppendixImg());
				if(objAcceptRejectClauseForm.getMapAppendixImg()==1){
					LogUtil.logMessage("Appendix Image Seq NO is " +objAcceptRejectClauseForm.getAppendixImgSeqNo());
					//objClauseVO.setAppendixImgSeqNo(objModelAddClauseForm.getAppendixImgSeqNo());
					AppendixVO objAppendixVO = new AppendixVO();
					objAppendixVO.setOrderKey(intOrderKey);
					objAppendixVO.setImageSeqNo(objAcceptRejectClauseForm.getAppendixImgSeqNo());
					objAppendixVO.setDataLocationType(DatabaseConstants.DATALOCATION);
					objAppendixVO.setClauseSeqNo(intClauseSeqNo);
					objAppendixVO.setVersionNo(objAcceptRejectClauseForm.getVersion());
					objAppendixVO.setUserID(objLoginVo.getUserID());
					
					AppendixBI objAppendixBI = ServiceFactory.getAppendixBO();
					intUpdate = objAppendixBI.saveMappings(objAppendixVO);
					
					if (intUpdate == 0) {
						objAcceptRejectClauseForm
						.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
						}
					
					if (intUpdate > 0) {
						objAcceptRejectClauseForm.setMessageID("" + intUpdate);
					}
					
				}
				//Added for CR_114 Ends Here
				
				
				
				objAcceptRejectClauseForm.setReason("");
				strRedirectFlag="Y";
				LogUtil.logMessage("SubSecSeqNo Vale"+intsubSecSeqNo);
				actionRedirect = new ActionForward("OrderSection.do?method=fetchSectionDetails&orderKey="+intOrderKey+"&secSeq="+intsecSeq+"&revCode="+intrevCode+"&subsecno="+intsubSecSeqNo, true /* REDIRECT */);
				
			} else {
				objAcceptRejectClauseForm.setMessageID("" + intUpdate);
				strRedirectFlag="N";
			
			//Clause Versions fetch
			
			if (objHttpServletRequest.getParameter("orderKey") != null) {
				
				intOrderKey = Integer.parseInt(objHttpServletRequest
						.getParameter("orderKey"));
			}
			if (objHttpServletRequest.getParameter("clauseSeq") != null) {
				
				intClauseSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("clauseSeq"));
			}
			
			objClauseVO.setClauseSeqNo(intClauseSeqNo);
			objClauseVO.setOrderKey(intOrderKey);
			objClauseVO.setUserID(objLoginVo.getUserID());
			
			AcceptRejectClauseBI objjAcceptRejectClauseBO = ServiceFactory
			.getAcceptRejectClauseBO();
			arlClauseList = objjAcceptRejectClauseBO
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
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Exception occured in ModelIndAcceptRejectClauseAction:"
					+ objExp);
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
		}
		
		/*Added as per CR 73 to return to Modify Spec  Screen by CM68219 starts*/
		if(strRedirectFlag!=null && strRedirectFlag.equalsIgnoreCase("Y")){
			LogUtil.logMessage("FORWARD"+strRedirectFlag);
			return actionRedirect;
		   
		    }else{
		    	LogUtil.logMessage("FORWARD"+strForwardKey);
			return objActionMapping.findForward(strForwardKey);	
			
		}
		
		/*Added as per CR 73 to return to Modify Spec Screen by CM68219 ends*/
	}
	
}
