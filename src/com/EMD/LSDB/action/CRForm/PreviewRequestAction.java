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
import com.EMD.LSDB.bo.serviceinterface.ModelSelectClauseRevBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.form.CRForm.PreviewRequestForm;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.RequestModelVO;
import com.EMD.LSDB.vo.common.RequestVO;

public class PreviewRequestAction extends EMDAction {
	
	/***************************************************************************
	 * This Method is used to fetch preview requestDetails
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
	public ActionForward initLoad(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering PreviewRequestAction.initLoad");
		String strForward = ApplicationConstants.SUCCESS;
		PreviewRequestForm objPreviewRequestForm = (PreviewRequestForm) objActionForm;
		String strUserID = "";
		
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			RequestVO objReqestVO = new RequestVO();
			RequestModelVO objRequestModelVO = new RequestModelVO();
			RequestModelVO objRequestCompGrpVO = new RequestModelVO();
			RequestModelVO objRequestClauseVO = new RequestModelVO();
			ClauseVO objRequestFrmClauseVO = new ClauseVO();
			ArrayList arlEffClauseList = new ArrayList();
			ArrayList arlClauseList = new ArrayList();
			ArrayList arlRequestList = new ArrayList();
			int intReqID = 0;
			// Getting User from the session
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			// To fetch request Details
			
			if ((String) objHttpServletRequest.getParameter("reqID") != null
					&& !"".equals((String) objHttpServletRequest
							.getParameter("reqID"))) {
				
				intReqID = Integer.parseInt(objHttpServletRequest.getParameter(
				"reqID").trim());
				
			}
			
			else if (objPreviewRequestForm.getReqID() != null
					&& !"".equalsIgnoreCase(objPreviewRequestForm.getReqID())) {
				
				intReqID = Integer.parseInt(objPreviewRequestForm.getReqID()
						.trim());
				
			}
			LogUtil.logMessage("RequestID:" + intReqID);
			
			objReqestVO.setRequestID(intReqID);
			objReqestVO.setUserID(strUserID);
			ChangeRequestBI objChangeRequestBO = ServiceFactory
			.getChangeRequestBO();
			arlRequestList = objChangeRequestBO
			.fetchRequestDetails(objReqestVO);
			objPreviewRequestForm.setRequestList(arlRequestList);
			LogUtil.logMessage("Inside action Of Preview Request"
					+ arlRequestList.size());
			
			// To fetch model details
			// objReqestVO.setRequestID(Integer.parseInt(objPreviewRequestForm.getReqID()));
			// ChangeRequestBI objChangeMdlRequestBO =
			// ServiceFactory.getChangeRequestBO();
			objRequestModelVO = objChangeRequestBO
			.fetchReqModelDetails(objReqestVO);
			
			objPreviewRequestForm.setRequestModelVO(objRequestModelVO);
			
			// To Fetch Component Group and Component Details
			objRequestCompGrpVO = objChangeRequestBO
			.fetchReqCompGrpDetails(objReqestVO);
			
			objPreviewRequestForm.setRequestCompGroupVO(objRequestCompGrpVO);
			
			// To Fetch Clause Details
			objRequestClauseVO = objChangeRequestBO
			.fetchReqClauseDetails(objReqestVO);
			objPreviewRequestForm.setRequestClauseVO(objRequestClauseVO);

			// Added for CR_80 CR Form Enhancements III on 25-Nov-09 by RR68151
			arlEffClauseList = objChangeRequestBO.fetchEffectedClauses(objReqestVO);
			if (arlEffClauseList.size() > 0) 
				objPreviewRequestForm.setEffClauseList(arlEffClauseList);	
			// Added for CR_80 CR Form Enhancements III on 25-Nov-09 by RR68151 - Ends Here	
			
			// Fetch From Values
			
			if (objRequestClauseVO != null
					&& objRequestClauseVO.getReqClauseVO() != null) {
				if (objRequestClauseVO.getReqClauseVO().getFromClauseSeqNo() != 0
						&& objRequestClauseVO.getReqClauseVO()
						.getFromClauseVerNo() != 0) {
					objRequestFrmClauseVO.setModelSeqNo(objRequestModelVO
							.getModelSeqNo());
					objRequestFrmClauseVO.setSubSectionSeqNo(objRequestModelVO
							.getSubSectionSeqNo());
					
					objRequestFrmClauseVO.setClauseSeqNo(objRequestClauseVO
							.getReqClauseVO().getFromClauseSeqNo());
					
					objRequestFrmClauseVO.setVersionNo(objRequestClauseVO
							.getReqClauseVO().getFromClauseVerNo());
					
					LogUtil.logMessage(" VerNo"
							+ objRequestFrmClauseVO.getClauseSeqNo()
							+ objRequestFrmClauseVO.getModelSeqNo()
							+ objRequestFrmClauseVO.getSubSectionSeqNo()
							+ objRequestFrmClauseVO.getVersionNo());
					LogUtil.logMessage("Seq No" + objRequestFrmClauseVO);
					ModelSelectClauseRevBI objModelSelectClauseRevBI = ServiceFactory
					.getModelSelectClauseRevBO();
					ArrayList arlFromClause = objModelSelectClauseRevBI
					.fetchClauseVersions(objRequestFrmClauseVO);
					LogUtil.logMessage("IClause Desc Arry List  ##"
							+ arlFromClause.size());
					// Fetch From Values
					if (arlFromClause != null && arlFromClause.size() > 0) {
						arlClauseList = (ArrayList) arlFromClause.get(1);
					}
					LogUtil.logMessage("IClause Desc Arry List  ##"
							+ arlClauseList.size());
					if (arlClauseList.size() > 0) {
						objPreviewRequestForm
						.setRequestFrmClauseVO(arlClauseList);
						
					}
				}
			}
			
		} catch (Exception objEx) {
			
			strForward = ApplicationConstants.FAILURE;
			objEx.printStackTrace();
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
		
	}
}