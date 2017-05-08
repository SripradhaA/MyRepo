
package com.EMD.LSDB.action.History; 

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.EMD.LSDB.action.common.EMDAction;
import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.RevisionChangesBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.form.History.RevisionChangesForm;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.RevisionChangesVO;


public class RevisionChangesAction extends EMDAction {

	/***************************************************************************
	 * This Method is used to fetch the Revision Details
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward fetchRevisions(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering RevisionChangesAction.fetchRevisions");
		String strForward = ApplicationConstants.SUCCESS;
		ArrayList arlRevisions = new ArrayList();
		RevisionChangesForm objRevisionChangesForm = (RevisionChangesForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			RevisionChangesVO objRevisionChangesVO = new RevisionChangesVO();
			
			objRevisionChangesVO.setUserID(objLoginVo.getUserID());

			LogUtil.logMessage("User Id in Revision Changes Action = "+objRevisionChangesVO.getUserID());
			
			RevisionChangesBI objRevisionChangesBO = ServiceFactory.getRevisionChangesBO();
			
			arlRevisions = objRevisionChangesBO.fetchRevisions(objRevisionChangesVO);
			
			if (arlRevisions != null && arlRevisions.size() > 0) {
				objRevisionChangesForm.setRevisionDetails(arlRevisions);
				
				LogUtil.logMessage("Values in Revision Details of History Form --------> " + objRevisionChangesForm.getRevisionDetails());
			}
			
		} catch (Exception objExp) {
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
		
	}
}

