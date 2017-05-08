/* AK49339
 * Created on Aug 24, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
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
import com.EMD.LSDB.bo.serviceinterface.AcceptRejectNewClauseBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.form.SpecMaintenance.AcceptRejectNewClauseForm;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.LoginVO;

public class AcceptRejectNewClauseAction extends EMDAction {
	
	public AcceptRejectNewClauseAction() {
		
	}
	
	/*******************************************************************************************
	 *  * * *		This Method is used to Search all versions of Clause
	 *  
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return     	ActionForward  		   
	 * @throws     	EMDException
	 
	 ******************************************************************************************/
	
	public ActionForward fetchNewClauses(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil
		.logMessage("Enters into AcceptRejectNewClauseAction:fetch Method");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList arlNewClauseList = null;
		
		AcceptRejectNewClauseForm objAcceptRejectNewClauseForm = (AcceptRejectNewClauseForm) objActionForm;
		
		try {
			//int intorderKey=1;
			int intorderKey = Integer.parseInt(objHttpServletRequest
					.getParameter("orderKey"));
			int intsubSecSeqNo = Integer.parseInt(objHttpServletRequest
					.getParameter("subSecSeqNo"));
			int intsecSeq = Integer.parseInt(objHttpServletRequest
					.getParameter("secSeq"));
			int intrevCode = Integer.parseInt(objHttpServletRequest
					.getParameter("revCode"));
			//String strsecCode=objHttpServletRequest.getParameter("secCode");					
			//String strsecName=objHttpServletRequest.getParameter("secName");
			
			/**
			 * Reason TextArea in AcceptrejectNewClause screen is visible only for 
			 * the spec status opening and above, to check this specStatusCode attribute is added
			 * Added on 05-June-08
			 * by ps57222  
			 * 
			 */
			
			if (objHttpServletRequest.getParameter("specCode") != null) {
				objAcceptRejectNewClauseForm
				.setSpecStatusCode(objHttpServletRequest
						.getParameter("specCode"));
			}
			LogUtil
			.logMessage("SpecStatus code in Accept reject new Clause Action:"
					+ objAcceptRejectNewClauseForm.getSpecStatusCode());
			
			objAcceptRejectNewClauseForm.setOrderKey(intorderKey);
			objAcceptRejectNewClauseForm.setSubSecSeqNo(intsubSecSeqNo);
			objAcceptRejectNewClauseForm.setSecSeq(intsecSeq);
			objAcceptRejectNewClauseForm.setRevCode(intrevCode);
			//objAcceptRejectNewClauseForm.setSecCode(strsecCode);
			//objAcceptRejectNewClauseForm.setSecName(strsecName);			
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			
			ClauseVO objClauseVO = new ClauseVO();
			
			objClauseVO.setOrderKey(objAcceptRejectNewClauseForm.getOrderKey());
			objClauseVO.setSubSectionSeqNo(objAcceptRejectNewClauseForm
					.getSubSecSeqNo());
			objClauseVO.setUserID(objLoginVo.getUserID());
			
			AcceptRejectNewClauseBI objAcceptRejectNewClauseBO = ServiceFactory
			.getAcceptRejectNewClauseBO();
			arlNewClauseList = objAcceptRejectNewClauseBO
			.fetchNewClauses(objClauseVO);
			
			if (arlNewClauseList != null && arlNewClauseList.size() > 0) {
				objAcceptRejectNewClauseForm.setNewClauseList(arlNewClauseList);
			}
			
			if (arlNewClauseList.size() == 0) {
				objAcceptRejectNewClauseForm.setMethod("ClauseVersions");
			}
			
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception Block in Action..");
			LogUtil.logMessage(" Exception occured in Action:" + objExp);
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
	
	public ActionForward updateNewClauseChange(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil
		.logMessage("Enters into AcceptRejectNewClauseAction:updateClauseChange Method");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intUpdate = 0;
		int intClauseSeqNo=-1;
		ArrayList arlClauseList = null;
		AcceptRejectNewClauseForm objAcceptRejectNewClauseForm = (AcceptRejectNewClauseForm) objActionForm;
		/*Added as per CR 73 to return to Modify Spec Screen by CM68219 starts*/
		//Changed default to 'N' CR_90
        String strRedirectFlag="N";
        String strFlag=null;
        int intrevCode = 1; //CR_90

		ActionForward actionRedirect = null;
		 /*Added as per CR 73 to return to Modify Spec Screen by CM68219 ends*/
		try {		
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			
			int intorderKey = Integer.parseInt(objHttpServletRequest
					.getParameter("orderKey"));
			int intsubSecSeqNo = Integer.parseInt(objHttpServletRequest
					.getParameter("subsecno"));
			int intsecSeq = Integer.parseInt(objHttpServletRequest
					.getParameter("secSeq"));
			//CR_90
			if((objHttpServletRequest
					.getParameter("revCode"))!=null &&(objHttpServletRequest.getParameter("revCode")!=""))
					{
				 intrevCode = Integer.parseInt(objHttpServletRequest.getParameter("revCode"));
				}

			if((objHttpServletRequest
					.getParameter("clauseSeqNo"))!=null &&(objHttpServletRequest.getParameter("clauseSeqNo")!=""))
					{
				 intClauseSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("clauseSeqNo"));
				 objAcceptRejectNewClauseForm.setClauseSeqNo(intClauseSeqNo);
					}
			
			if((objHttpServletRequest
					.getParameter("flag"))!=null &&(objHttpServletRequest.getParameter("flag")!=""))
					{
				strFlag = (String) objHttpServletRequest
				.getParameter("flag");
				objAcceptRejectNewClauseForm.setFlag(strFlag);
					}
					
			objAcceptRejectNewClauseForm.setOrderKey(intorderKey);
			objAcceptRejectNewClauseForm.setSubSecSeqNo(intsubSecSeqNo);
			objAcceptRejectNewClauseForm.setSecSeq(intsecSeq);
			objAcceptRejectNewClauseForm.setRevCode(intrevCode);
			
			ClauseVO objClauseVO = new ClauseVO();
			
			LogUtil.logMessage("objAcceptRejectNewClauseForm.getClauseSeqNo():"
					+ objAcceptRejectNewClauseForm.getClauseSeqNo());
			objClauseVO.setOrderKey(objAcceptRejectNewClauseForm.getOrderKey());
			objClauseVO.setClauseSeqNo(objAcceptRejectNewClauseForm.getClauseSeqNo());
			objClauseVO.setFlag(objAcceptRejectNewClauseForm.getFlag());
			objClauseVO.setReason(objAcceptRejectNewClauseForm.getReason());
			objClauseVO.setUserID(objLoginVo.getUserID());
			
			AcceptRejectNewClauseBI objAcceptRejectNewClauseBO = ServiceFactory
			.getAcceptRejectNewClauseBO();
			intUpdate = objAcceptRejectNewClauseBO
			.updateNewClauseChange(objClauseVO);
			/*Added as per CR 73 to return to Modify Spec Screen by CM68219 starts*/
			if (intUpdate == 0) {
				objAcceptRejectNewClauseForm.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objAcceptRejectNewClauseForm.setMessageID("" + intUpdate);
			}
			
			//Clause Versions fetch
			ClauseVO objjClauseVO = new ClauseVO();
			
			objjClauseVO
			.setOrderKey(objAcceptRejectNewClauseForm.getOrderKey());
			objjClauseVO.setSubSectionSeqNo(intsubSecSeqNo);
			objjClauseVO.setUserID(objLoginVo.getUserID());
			AcceptRejectNewClauseBI objjAcceptRejectNewClauseBO = ServiceFactory
			.getAcceptRejectNewClauseBO();
			arlClauseList = objjAcceptRejectNewClauseBO
			.fetchNewClauses(objjClauseVO);
			
			if (arlClauseList != null && arlClauseList.size() > 0) {
				objAcceptRejectNewClauseForm.setNewClauseList(arlClauseList);
				objAcceptRejectNewClauseForm.setClauseSeqNo(objClauseVO
						.getClauseSeqNo());
				strRedirectFlag="N";
			}
			
			if (arlClauseList.size() == 0) {
				//Moved from top for CR_90 by Sathish
				strRedirectFlag="Y";
				actionRedirect = new ActionForward("OrderSection.do?method=fetchSectionDetails&orderKey="+intorderKey+"&secSeq="+intsecSeq+"&revCode="+intrevCode+"&subsecno="+intsubSecSeqNo, true );
				objAcceptRejectNewClauseForm.setMethod("ClauseVersions");
			}
			
			/*Added as per CR 73 to return to Modify Spec Screen by CM68219 ends*/
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception Block in Action..");
			LogUtil.logMessage(" Exception occured in Action:" + objExp);
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
	}
}