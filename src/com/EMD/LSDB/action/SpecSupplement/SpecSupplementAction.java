/* AK49339
 * Created on Aug 24, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.action.SpecSupplement;

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
import com.EMD.LSDB.bo.serviceinterface.ModelBI;
import com.EMD.LSDB.bo.serviceinterface.OrderBI;
import com.EMD.LSDB.bo.serviceinterface.SpecTypeBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.constant.DatabaseConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.form.SpecSupplement.SpecSupplementForm;
import com.EMD.LSDB.vo.common.CustomerVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.SpecTypeVo;

public class SpecSupplementAction extends EMDAction {
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
		
		LogUtil.logMessage("Entering CopySpecAction.initLoad");
		String strForward = ApplicationConstants.SUCCESS;
		SpecSupplementForm objSpecSupplementForm = (SpecSupplementForm) objActionForm;
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
				objSpecSupplementForm.setSpecTypeList(arlSpecList);
			}
			CustomerVO objCustomerVO = new CustomerVO();
			CustomerBI objCustomerBI = ServiceFactory.getCustomerBO();
			
			ArrayList arlCustList = new ArrayList();
			
			objCustomerVO.setUserID(objLoginVo.getUserID());
			// To Fetch Customers
			arlCustList = objCustomerBI.fetchCustomers(objCustomerVO);
			
			if (arlCustList != null && arlCustList.size() > 0) {
				objSpecSupplementForm.setCustList(arlCustList);
			}
		} catch (Exception objExp) {
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
		
	}
	
	/***************************************************************************
	 * This Method is used to fetch the Customers based on Specification Type
	 * Added for CR-46 PM&I Spec
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward fetchCustomers(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering CopySpecAction.fetchCustomers");
		String strForward = ApplicationConstants.SUCCESS;
		SpecSupplementForm objSpecSupplementForm = (SpecSupplementForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			
			ArrayList arlSpecList = null;
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			// To Fetch Specification Types
			arlSpecList = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			
			if (arlSpecList != null && arlSpecList.size() > 0) {
				objSpecSupplementForm.setSpecTypeList(arlSpecList);
				objSpecSupplementForm.setSpectypeSeqno(objSpecSupplementForm
						.getSpectypeSeqno());
			}
			CustomerVO objCustomerVO = new CustomerVO();
			CustomerBI objCustomerBI = ServiceFactory.getCustomerBO();
			
			ArrayList arlCustList = new ArrayList();
			
			objCustomerVO.setUserID(objLoginVo.getUserID());
			
			// Added for CR-46 PM&I Spec
			objCustomerVO.setSpecTypeSeqNo(objSpecSupplementForm
					.getSpectypeSeqno());
			LogUtil.logMessage("Specification Type Sequence No"
					+ objSpecSupplementForm.getSpectypeSeqno());
			
			// To Fetch Customers
			arlCustList = objCustomerBI.fetchCustomers(objCustomerVO);
			
			if (arlCustList != null && arlCustList.size() > 0) {
				objSpecSupplementForm.setCustList(arlCustList);
			}
		} catch (Exception objExp) {
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
		
		LogUtil.logMessage("Entering SpecSupplement.fetchOrders");
		String strForward = ApplicationConstants.SUCCESS;
		SpecSupplementForm objSpecSupplementForm = (SpecSupplementForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			
			ModelVo objModelVo = new ModelVo();
			ModelBI objModelBo = ServiceFactory.getModelBO();
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			
			CustomerVO objCustVo = new CustomerVO();
			CustomerBI objCustBo = ServiceFactory.getCustomerBO();
			ArrayList arlSpecList = new ArrayList();
			ArrayList arlModelList = new ArrayList();
			ArrayList arlCustList = new ArrayList();
			
			ArrayList arlOrderList = new ArrayList();
			OrderBI objOrderBo = ServiceFactory.getOrderBO();
			OrderVO objOrderVo = new OrderVO();
			
			String strUserID = "";
			
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
				objSpecSupplementForm.setSpecTypeList(arlSpecList);
			}
			
			// To fetch Models
			objModelVo.setSpecTypeSeqNo(objSpecSupplementForm
					.getSpectypeSeqno());
			objModelVo.setUserID(strUserID);
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null && arlModelList.size() > 0) {
				objSpecSupplementForm.setModelList(arlModelList);
			}
			
			objCustVo.setUserID(strUserID);
			
			// Added for CR-46 PM&I Spec
			objCustVo
			.setSpecTypeSeqNo(objSpecSupplementForm.getSpectypeSeqno());
			
			// To fetch Customers
			arlCustList = objCustBo.fetchCustomers(objCustVo);
			
			if (arlCustList != null && arlCustList.size() > 0) {
				objSpecSupplementForm.setCustList(arlCustList);
				
			}
			
			// To fetch Orders
			objOrderVo.setSpecTypeSeqNo(objSpecSupplementForm
					.getSpectypeSeqno());
			objOrderVo.setCusSeqNo(objSpecSupplementForm.getCustSeqNo());
			
			objOrderVo.setOrderNo(ApplicationUtil.trim(objSpecSupplementForm
					.getOrderNo()));
			objOrderVo
			.setDataLocTypeCode(DatabaseConstants.DATALOCATION_SNAP_SHOT);
			objOrderVo.setUserID(strUserID);
			
			arlOrderList = objOrderBo.fetchOrders(objOrderVo);
			
			if (arlOrderList != null && arlOrderList.size() > 0) {
				objSpecSupplementForm.setOrderList(arlOrderList);
			}
			objSpecSupplementForm.setSpecType(objSpecSupplementForm
					.getSpecType());
			objSpecSupplementForm.setCustName(objSpecSupplementForm
					.getCustName());
			
		} catch (Exception objExp) {
			
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
	}
}
