package com.EMD.LSDB.action.common;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONObject;

import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.ModelClauseBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.LoginVO;

public class CommonAppUtilAction extends EMDAction {

	/***************************************************************************
	 * This method is for validating the clause description via AJAX for CR_99
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0 by RJ85495 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws BusinessException
	 **************************************************************************/
	
	public ActionForward validateClauseDescriptionThruAJAX(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		LogUtil.logMessage("Entering CommonAppUtilAction.validateClauseDescriptionThruAJAX");
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
		
		ArrayList arlValidatedClause = null;
		
		String strClauseDescription = null;
		String strClauseDescriptionResult=null;
		
		try {
			strClauseDescription = objHttpServletRequest.getParameter("clauseDesc");
			
			ClauseVO objClauseVo = new ClauseVO();
			
			ModelClauseBI objModelClauseBO = ServiceFactory.getModelClauseBO();
			
			objClauseVo.setUserID(objLoginVo.getUserID());
			objClauseVo.setClauseDesc(strClauseDescription);
			
			arlValidatedClause=objModelClauseBO.validateClauseDescription(objClauseVo);
			
			if (arlValidatedClause != null &&arlValidatedClause.size() > 0) {
				strClauseDescriptionResult = new JSONObject().put("Clause",arlValidatedClause).toString();
			}
			
			objHttpServletResponse.getWriter().write(strClauseDescriptionResult); 			
		    
		} catch (Exception objExp) {
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return null;
	}

}