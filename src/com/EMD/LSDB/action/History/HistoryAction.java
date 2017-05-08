/**
 * 
 */
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
import com.EMD.LSDB.bo.serviceinterface.CustomerBI;
import com.EMD.LSDB.bo.serviceinterface.OrderBI;
import com.EMD.LSDB.bo.serviceinterface.SpecTypeBI;
import com.EMD.LSDB.bo.serviceinterface.ModelBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.constant.DatabaseConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.form.History.HistoryForm;
//import com.EMD.LSDB.form.MasterMaintenance.CustMaintForm;
import com.EMD.LSDB.vo.common.CustomerVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.SpecTypeVo;
import com.EMD.LSDB.vo.common.ModelVo;


/**
 * @author VV49326
 * 
 */
public class HistoryAction extends EMDAction {
	
		
	/***************************************************************************
	 * This Method is used to fetch the Specification Type Details
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
		
		LogUtil.logMessage("Entering HistoryAction.initLoad");
		String strForward = ApplicationConstants.SUCCESS;
		HistoryForm objHistoryForm = (HistoryForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			
			ArrayList arlSpecList = new ArrayList();
			
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			// To Fetch Specification Types
			arlSpecList = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			
			if (arlSpecList != null && arlSpecList.size() > 0) {
				objHistoryForm.setSpecTypeList(arlSpecList);
			}
			
		} catch (Exception objExp) {
			LogUtil.logMessage(" Exception occured in HistoryAction:" + objExp);
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
		
	}
	
	/***************************************************************************
	 * This Method is used to fetch Orders
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
	public ActionForward fetchOrders(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering HistoryAction.fetchOrders");
		String strForward = ApplicationConstants.SUCCESS;
		HistoryForm objHistoryForm = (HistoryForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			ArrayList arlSpecList = new ArrayList();
			ArrayList arlOrderList = new ArrayList();
			String strUserID = "";
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			
			OrderBI objOrderBo = ServiceFactory.getOrderBO();
			OrderVO objOrderVo = new OrderVO();
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			// Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			
			objSpecTypeVo.setUserID(strUserID);
			// To Fetch Specification Types
			arlSpecList = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			
			if (arlSpecList != null && arlSpecList.size() > 0) {
				objHistoryForm.setSpecTypeList(arlSpecList);
			}
			
			CustomerVO objCustomerVO = new CustomerVO();
			objCustomerVO.setSpecTypeSeqNo(objHistoryForm.getSpecTypeSeqno());
			objCustomerVO.setUserID(objLoginVo.getUserID());
			
			CustomerBI objCustomerBI = ServiceFactory.getCustomerBO();
			ArrayList arlCustomerList = objCustomerBI.fetchCustomers(objCustomerVO);
			
			objHistoryForm.setCustomerList(arlCustomerList);
			
			//Added for CR_112 - Model Multiselect list box 
			ArrayList arlModelList = new ArrayList();
			ModelVo objModelVo = new ModelVo();
			objModelVo.setSpecTypeSeqNo(objHistoryForm.getSpecTypeSeqno());
			
			//Added for CR_118
			objModelVo.setModelFlag(ApplicationConstants.All_MODELS);
			//Added for CR_118
			
			ModelBI objModelBI = ServiceFactory.getModelBO();
			arlModelList = objModelBI.fetchModels(objModelVo);
			
			objHistoryForm.setModelList(arlModelList);
			objOrderVo.setModelSeqnos(objHistoryForm.getModelSeqnos());
			//CR_112 Ends here
			
			// To fetch Orders
			objOrderVo.setSpecTypeSeqNo(objHistoryForm.getSpecTypeSeqno());
			LogUtil.logMessage("Spec Type Seq No"
					+ objHistoryForm.getSpecTypeSeqno());
			if (objHistoryForm.getSpecStatusSeqNo() == 1) {
				objOrderVo.setSpecStatusCode(0);
			} else {
				objOrderVo.setSpecStatusCode(objHistoryForm
						.getSpecStatusSeqNo());
			}
			objOrderVo.setOrderNo(ApplicationUtil.trim(objHistoryForm
					.getOrderNum()));
			objOrderVo
			.setDataLocTypeCode(DatabaseConstants.DATALOCATION_SNAP_SHOT);
			objOrderVo.setUserID(strUserID);
			//Added for LSDB_CR-76
			
			objOrderVo.setSortBy(objHistoryForm.getSortByFlag());
			objOrderVo.setCustomerSeqnos(objHistoryForm.getCustomerSeqnos());
			LogUtil.logMessage("objOrderVo.getCustomerSeqnos " + objOrderVo.getCustomerSeqnos());
			arlOrderList = objOrderBo.fetchOrders(objOrderVo);
			
			if (arlOrderList != null && arlOrderList.size() > 0) {
				LogUtil.logMessage("ArrayList not null" + arlOrderList.size());
				objHistoryForm.setSpecTypeSeqno(objHistoryForm
						.getSpecTypeSeqno());
				objHistoryForm.setSpecStatusSeqNo(objHistoryForm
						.getSpecStatusSeqNo());
				objHistoryForm.setOrderList(arlOrderList);
				objHistoryForm.setSortByFlag(objHistoryForm.getSortByFlag());
				LogUtil.logMessage("Array List in Form"
						+ objHistoryForm.getOrderList());
			}
			if (arlOrderList == null || arlOrderList.size() == 0) {
				objHistoryForm.setSpecTypeSeqno(objHistoryForm
						.getSpecTypeSeqno());
				objHistoryForm.setSpecStatusSeqNo(objHistoryForm
						.getSpecStatusSeqNo());
				objHistoryForm.setMessageID("fetchOrders");
				
			}
			//Added for LSDB_CR-76
			objHistoryForm.setColumnheader(objHistoryForm.getColumnheader());
			LogUtil.logMessage("Sort by flag value :"+objHistoryForm.getSortByFlag());
			
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in HistoryAction..");
			LogUtil.logMessage(" Exception occured in HistoryAction:" + objExp);
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
	}
	
	
	/***************************************************************************
	 * Added for CR_101 to bring the customer list in search criteria.
	 * This method is for fetching the customers
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
	
	public ActionForward fetchCustomers(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering HistoryAction.fetchCustomers");
		String strForwardKey = ApplicationConstants.SUCCESS;
		HistoryForm objHistoryForm = (HistoryForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			ArrayList arlCustomerList = new ArrayList();
			CustomerVO objCustomerVO = new CustomerVO();
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objHistoryForm.setSpecTypeList(arlSpec);
			}	
			
			objCustomerVO.setSpecTypeSeqNo(objHistoryForm.getSpecTypeSeqno());
			objCustomerVO.setUserID(objLoginVo.getUserID());
			
			CustomerBI objCustomerBI = ServiceFactory.getCustomerBO();
			arlCustomerList = objCustomerBI.fetchCustomers(objCustomerVO);
			
			objHistoryForm.setCustomerList(arlCustomerList);
			objHistoryForm.setHdnSpecTypeNme(objHistoryForm
					.getHdnSpecTypeNme());
			//Added for CR_112 - Model Multiselect list box 
			ArrayList arlModelList = new ArrayList();
			ModelVo objModelVo = new ModelVo();
			
			//Added for CR_118
			objModelVo.setModelFlag(ApplicationConstants.All_MODELS);
			//Added for CR_118
			objModelVo.setSpecTypeSeqNo(objHistoryForm.getSpecTypeSeqno());
			
			ModelBI objModelBI = ServiceFactory.getModelBO();
			arlModelList = objModelBI.fetchModels(objModelVo);
			
			objHistoryForm.setModelList(arlModelList);
			//CCR_112 Ends here
			
		} catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForwardKey);
	}
	
}
