
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
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.form.SpecMaintenance.AcceptRejectClauseForm;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.LoginVO;

/**
 * @author RR68151
 *
 */
/***********************************************************************
---------------------------------------------------------------------------------------------------
*    Date     Version   Modified by    	 Comments                 			Remarks 
* 19/01/2010    1.0      RR68151       Initial Version     				Added for CR_81
* 										
* 													 	 
* 
* -------------------------------------------------------------------------------------------------
**************************************************************************/


public class EdlIndAcceptRejectAction extends EMDAction {
	
	/*******************************************************************************************
	 *  * * *		This Method is used to bring the old and new EDL/RefEDL Numbers for a Clause
	 *  
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return     	ActionForward  		   
	 * @throws     	EMDException
	 
	 ******************************************************************************************/
	
	public ActionForward fetchClauseEdlChanges(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil
		.logMessage("Entering EdlIndAcceptRejectAction:fetchClauseEdlChanges");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList arlClauseList = null;
		int intOrderKey = 0;
		int intClauseSeqNo = 0;
		int intSpecStatusCode = 0;
		int intsubSecSeqNo=0;
		AcceptRejectClauseForm objAcceptRejectClauseForm = (AcceptRejectClauseForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			
			if(objHttpServletRequest.getParameter("subsecseqno") !=null){
			       intsubSecSeqNo = Integer.parseInt(objHttpServletRequest
					.getParameter("subsecseqno"));
		    }
			if (objHttpServletRequest.getParameter("orderKey") != null) {
				
				intOrderKey = Integer.parseInt(objHttpServletRequest
						.getParameter("orderKey"));
			}
			if (objHttpServletRequest.getParameter("clauseSeq") != null) {
				
				intClauseSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("clauseSeq"));
			}
			
			if (objHttpServletRequest.getParameter("specCode") != null) {
				
				intSpecStatusCode = Integer.parseInt(objHttpServletRequest
						.getParameter("specCode"));
			}
			
			LogUtil.logMessage("intOrderKey :" + intOrderKey);
			LogUtil.logMessage("intClauseSeqNo :" + intClauseSeqNo);
			
			int intsecSeq = Integer.parseInt(objHttpServletRequest
					.getParameter("secSeq"));
			
			int intrevCode = Integer.parseInt(objHttpServletRequest
					.getParameter("revCode"));
			
			objAcceptRejectClauseForm.setOrderKey(intOrderKey);
			objAcceptRejectClauseForm.setClauseSeqNo(intClauseSeqNo);
			objAcceptRejectClauseForm.setSecSeq(intsecSeq);
			objAcceptRejectClauseForm.setRevCode(intrevCode);
			objAcceptRejectClauseForm.setSpecStatusCode(intSpecStatusCode);
			objAcceptRejectClauseForm.setSubSecSeqNo(intsubSecSeqNo);
			ClauseVO objClauseVO = new ClauseVO();
			
			objClauseVO.setClauseSeqNo(intClauseSeqNo);
			objClauseVO.setOrderKey(intOrderKey);
			objClauseVO.setUserID(objLoginVo.getUserID());
			
			AcceptRejectClauseBI objAcceptRejectClauseBO = ServiceFactory
			.getAcceptRejectClauseBO();
			arlClauseList = objAcceptRejectClauseBO
			.fetchClauseEdlChanges(objClauseVO);
			
			if (arlClauseList != null && arlClauseList.size() > 0) {
				
				LogUtil.logMessage("Size of Array List" + arlClauseList.size());

				objAcceptRejectClauseForm.setClauseVersions((ArrayList) arlClauseList.get(0));
				objAcceptRejectClauseForm.setSpecStatusCode(intSpecStatusCode);
				objAcceptRejectClauseForm.setSubSecSeqNo(intsubSecSeqNo);		
			}
			else {
				objAcceptRejectClauseForm.setMethod("fetchClauseEdlChanges");
				objAcceptRejectClauseForm.setSpecStatusCode(intSpecStatusCode);	
			}
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in EdlIndAcceptRejectAction:fetchClauseEdlChanges..");
			objExp.printStackTrace();
			strForwardKey = ApplicationConstants.FAILURE;
		}
		LogUtil.logMessage("key" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/*******************************************************************************************
	 *  * * *		This Method is used to Update the Clause Edl Changes at Order Level
	 *  
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return     	ActionForward  		   
	 * @throws     	EMDException
	 
	 ******************************************************************************************/
	
	public ActionForward updateClauseEdlChange(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil
		.logMessage("Enters into EdlIndAcceptRejectAction:updateClauseEdlChange Method");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intUpdate = 0;
		AcceptRejectClauseForm objAcceptRejectClauseForm = (AcceptRejectClauseForm) objActionForm;
		int intOrderKey = 0;
		int intClauseSeqNo = 0;
		String strRedirectFlag=null;
		
		int intsubSecSeqNo=0;
		ActionForward actionRedirect = null;

		try {
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			
			if (objHttpServletRequest.getParameter("orderKey") != null) {
				
				intOrderKey = Integer.parseInt(objHttpServletRequest
						.getParameter("orderKey"));
			}
			
			if(objHttpServletRequest.getParameter("subsecno") !=null){
				
			       intsubSecSeqNo = Integer.parseInt(objHttpServletRequest
					.getParameter("subsecno"));
		        }
			
			if (objHttpServletRequest.getParameter("clauseSeq") != null) {
				
				intClauseSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("clauseSeq"));
			}
			LogUtil.logMessage("Clause SeqNO: "+intClauseSeqNo);
			
			int intsecSeq = Integer.parseInt(objHttpServletRequest
					.getParameter("secSeq"));
			int intrevCode = Integer.parseInt(objHttpServletRequest
					.getParameter("revCode"));
			
			objAcceptRejectClauseForm.setOrderKey(intOrderKey);
			objAcceptRejectClauseForm.setClauseSeqNo(intClauseSeqNo);
			objAcceptRejectClauseForm.setSecSeq(intsecSeq);
			objAcceptRejectClauseForm.setRevCode(intrevCode);
			objAcceptRejectClauseForm.setSubSecSeqNo(intsubSecSeqNo);
			
			ClauseVO objClauseVO = new ClauseVO();
			
			objClauseVO.setOrderKey(objAcceptRejectClauseForm.getOrderKey());
			objClauseVO.setClauseSeqNo(objAcceptRejectClauseForm
					.getClauseSeqNo());
			objClauseVO.setFlag(objAcceptRejectClauseForm.getFlag());
			objClauseVO.setReason(objAcceptRejectClauseForm.getReason());
			objClauseVO.setUserID(objLoginVo.getUserID());

			AcceptRejectClauseBI objAcceptRejectClauseBO = ServiceFactory
			.getAcceptRejectClauseBO();
			intUpdate = objAcceptRejectClauseBO.updateClauseEdlChange(objClauseVO);

			if (intUpdate == 0) {
				strRedirectFlag="Y";
				objAcceptRejectClauseForm.setReason("");
				actionRedirect = new ActionForward("OrderSection.do?method=fetchSectionDetails&orderKey="+intOrderKey+"&secSeq="+intsecSeq+"&revCode="+intrevCode+"&subsecno="+intsubSecSeqNo, true /* REDIRECT */);	
				
			} else {
				strRedirectFlag="N";
				objAcceptRejectClauseForm.setMessageID("" + intUpdate);
			}
		}
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in EdlIndAcceptRejectAction:updateClauseEdlChange.");
			strForwardKey = ApplicationConstants.FAILURE;
			LogUtil
			.logMessage("Exception: " + objExp);
		}
		if(strRedirectFlag!=null && strRedirectFlag.equalsIgnoreCase("Y")){
			LogUtil.logMessage("FORWARD"+strRedirectFlag);
			return actionRedirect;
		    }else{
		    	LogUtil.logMessage("FORWARD"+strForwardKey);
			return objActionMapping.findForward(strForwardKey);	
			
		}
		
	}
	
}
