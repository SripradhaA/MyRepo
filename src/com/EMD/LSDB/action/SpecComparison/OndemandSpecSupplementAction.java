/* ER91220	
 * Created on March 08, 2012
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.action.SpecComparison;

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
import com.EMD.LSDB.form.SpecComparison.OndemandSpecSupplementForm;
import com.EMD.LSDB.vo.common.CustomerVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.SpecTypeVo;

public class OndemandSpecSupplementAction extends EMDAction {
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
		OndemandSpecSupplementForm objOndemandSpecSupplementForm = (OndemandSpecSupplementForm) objActionForm;
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
				objOndemandSpecSupplementForm.setSpecTypeList(arlSpecList);
			}
			CustomerVO objCustomerVO = new CustomerVO();
			CustomerBI objCustomerBI = ServiceFactory.getCustomerBO();
			
			ArrayList arlCustList = new ArrayList();
			
			objCustomerVO.setUserID(objLoginVo.getUserID());
			// To Fetch Customers
			arlCustList = objCustomerBI.fetchCustomers(objCustomerVO);
			
			if (arlCustList != null && arlCustList.size() > 0) {
				objOndemandSpecSupplementForm.setCustList(arlCustList);
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
		
		LogUtil.logMessage("Entering OndemandSpecSupplementAction.fetchCustomers");
		String strForward = ApplicationConstants.SUCCESS;
		OndemandSpecSupplementForm objOndemandSpecSupplementForm = (OndemandSpecSupplementForm) objActionForm;
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
				objOndemandSpecSupplementForm.setSpecTypeList(arlSpecList);
				objOndemandSpecSupplementForm.setSpectypeSeqno(objOndemandSpecSupplementForm
						.getSpectypeSeqno());
			}
			CustomerVO objCustomerVO = new CustomerVO();
			CustomerBI objCustomerBI = ServiceFactory.getCustomerBO();
			
			ArrayList arlCustList = new ArrayList();
			
			objCustomerVO.setUserID(objLoginVo.getUserID());
			

			objCustomerVO.setSpecTypeSeqNo(objOndemandSpecSupplementForm
					.getSpectypeSeqno());
			LogUtil.logMessage("Specification Type Sequence No"
					+ objOndemandSpecSupplementForm.getSpectypeSeqno());
			
			// To Fetch Customers
			arlCustList = objCustomerBI.fetchCustomers(objCustomerVO);
			
			if (arlCustList != null && arlCustList.size() > 0) {
				objOndemandSpecSupplementForm.setCustList(arlCustList);
				
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
		OndemandSpecSupplementForm objOndemandSpecSupplementForm = (OndemandSpecSupplementForm) objActionForm;
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
				objOndemandSpecSupplementForm.setSpecTypeList(arlSpecList);
			}
			
			// To fetch Models
			objModelVo.setSpecTypeSeqNo(objOndemandSpecSupplementForm
					.getSpectypeSeqno());
			objModelVo.setUserID(strUserID);
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null && arlModelList.size() > 0) {
				objOndemandSpecSupplementForm.setModelList(arlModelList);
			}
			
			objCustVo.setUserID(strUserID);
			objCustVo.setSpecTypeSeqNo(objOndemandSpecSupplementForm.getSpectypeSeqno());
			
			// To fetch Customers
			ArrayList arlCustList = new ArrayList();
			CustomerBI objCustomerBI = ServiceFactory.getCustomerBO();
			arlCustList = objCustomerBI.fetchCustomers(objCustVo);
			if (arlCustList != null && arlCustList.size() > 0) {
				objOndemandSpecSupplementForm.setCustList(arlCustList);
			}
			// To fetch Orders
			objOrderVo.setSpecTypeSeqNo(objOndemandSpecSupplementForm.getSpectypeSeqno());
			objOrderVo.setCusSeqNo(objOndemandSpecSupplementForm.getCustSeqNo());
			
			objOrderVo.setOrderNo(ApplicationUtil.trim(objOndemandSpecSupplementForm.getOrderNo()));
			objOrderVo.setDataLocTypeCode(DatabaseConstants.DATALOCATION_SNAP_SHOT);
			objOrderVo.setUserID(strUserID);
			//Added For CR_108 - sortting
			objOrderVo.setSortBy(objOndemandSpecSupplementForm.getSortByFlag());
			objOrderVo.setModelSeqnos(objOndemandSpecSupplementForm.getModelSeqNos());
			objOrderVo.setModelSelected(objOndemandSpecSupplementForm.getModelSeqNos());
			
			arlOrderList = objOrderBo.fetchOrders(objOrderVo);
			
			if (arlOrderList != null && arlOrderList.size() > 0) {
				objOndemandSpecSupplementForm.setOrderList(arlOrderList);
			}
			objOndemandSpecSupplementForm.setSpecType(objOndemandSpecSupplementForm.getSpecType());
			objOndemandSpecSupplementForm.setCustName(objOndemandSpecSupplementForm.getCustName());
			
		} catch (Exception objExp) {
			
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
	}
	
	
	/***************************************************************************
	 * This Method is used to load all the models present in the database for a
	 * specification type.
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward fetchModels(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering OndemandSpecSupplement.fetchModels");
		String strForward = ApplicationConstants.SUCCESS;
		OndemandSpecSupplementForm objOndemandSpecSupplementForm = (OndemandSpecSupplementForm) objActionForm;
		try {
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			/** LoginVO is obtained from session. */
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			/** Getting User from the session */
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			/** SpecTypeBI instace is obtained. */
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			ModelVo objModelVo = new ModelVo();
			
			/** ModelBI instance is obtained. */
			ModelBI objModelBo = ServiceFactory.getModelBO();
			
			ArrayList arlSpecList = new ArrayList();
			ArrayList arlModelList = new ArrayList();
			
			/** To Fetch Specification Types */
			arlSpecList = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			
			if (arlSpecList != null && arlSpecList.size() > 0) {
				objOndemandSpecSupplementForm.setSpecTypeList(arlSpecList);
			}
			
			/** To fetch Models for a specification type. */
			objModelVo.setSpecTypeSeqNo(objOndemandSpecSupplementForm.getSpectypeSeqno());
			objModelVo.setUserID(objLoginVo.getUserID());
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null && arlModelList.size() > 0) {
				objOndemandSpecSupplementForm.setModelList(arlModelList);
			}
			
			objOndemandSpecSupplementForm.setHdnSelSpecType(objOndemandSpecSupplementForm.getHdnSelSpecType());
			
			//*****To Fetch Customers*********
			
			CustomerVO objCustomerVO = new CustomerVO();
			CustomerBI objCustomerBI = ServiceFactory.getCustomerBO();
			
			ArrayList arlCustList = new ArrayList();
			
			objCustomerVO.setUserID(objLoginVo.getUserID());
			// To Fetch Customers
			arlCustList = objCustomerBI.fetchCustomers(objCustomerVO);
			
			if (arlCustList != null && arlCustList.size() > 0) {
				objOndemandSpecSupplementForm.setCustList(arlCustList);
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
