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
import com.EMD.LSDB.bo.serviceinterface.CustomerBI;
import com.EMD.LSDB.bo.serviceinterface.ModelBI;
import com.EMD.LSDB.bo.serviceinterface.OrderBI;
import com.EMD.LSDB.bo.serviceinterface.SpecTypeBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.form.SpecMaintenance.DeleteSpecForm;
import com.EMD.LSDB.vo.common.CustomerVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.SpecTypeVo;

/**
 * @author VV49326
 * 
 */
public class DeleteSpecAction extends EMDAction {
	
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
	
	public ActionForward initLoad(ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest, HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering DeleteSpecAction.initLoad");
		String strForward = ApplicationConstants.SUCCESS;
		DeleteSpecForm objDeleteSpecForm = (DeleteSpecForm) objActionForm;
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
				objDeleteSpecForm.setSpecTypeList(arlSpecList);
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
	 * This Method is used to fetch the Model Details
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward fetchModels(ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest, HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering DeleteSpecAction.fetchModels");
		String strForward = ApplicationConstants.SUCCESS;
		DeleteSpecForm objDeleteSpecForm = (DeleteSpecForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			
			ModelVo objModelVo = new ModelVo();
			ModelBI objModelBo = ServiceFactory.getModelBO();
			
			ArrayList arlSpecList = new ArrayList();
			ArrayList arlModelList = new ArrayList();
			/*Added as per Cr 75*/
			ArrayList arlCustomerList = new ArrayList();
			
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
				objDeleteSpecForm.setSpecTypeList(arlSpecList);
			}
			
			// To fetch Models
			objModelVo.setSpecTypeSeqNo(objDeleteSpecForm.getSpectypeSeqno());
			objModelVo.setUserID(strUserID);
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null && arlModelList.size() > 0) {
				objDeleteSpecForm.setModelList(arlModelList);
			}
			/*Added to include customer drop down for secarch criteria by cm68219*/
			
			if (objDeleteSpecForm.getSpectypeSeqno() != -1) {
				CustomerVO objCustomerVO = new CustomerVO();
				objCustomerVO.setUserID(objLoginVo.getUserID());
				objCustomerVO.setSpecTypeSeqNo(objDeleteSpecForm.getSpectypeSeqno());
				CustomerBI objCustomerBO = ServiceFactory.getCustomerBO();
				arlCustomerList = objCustomerBO.fetchCustomers(objCustomerVO);
				
				if (arlCustomerList != null && arlCustomerList.size() > 0) {
					objDeleteSpecForm.setCustomerList(arlCustomerList);
					
				}
			}
			/* added*/
			
			objDeleteSpecForm.setHdnSelSpecType(objDeleteSpecForm.getHdnSelSpecType());
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
	public ActionForward fetchOrders(ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest, HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering DeleteSpecAction.fetchOrders");
		String strForward = ApplicationConstants.SUCCESS;
		DeleteSpecForm objDeleteSpecForm = (DeleteSpecForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			
			ModelVo objModelVo = new ModelVo();
			ModelBI objModelBo = ServiceFactory.getModelBO();
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			
			ArrayList arlModelList = new ArrayList();
			ArrayList arlSpecList = new ArrayList();
			/*Added as per Cr 75*/
			ArrayList arlCustomerList = new ArrayList();
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
				objDeleteSpecForm.setSpecTypeList(arlSpecList);
			}
			
			// To fetch Models
			objModelVo.setSpecTypeSeqNo(objDeleteSpecForm.getSpectypeSeqno());
			objModelVo.setUserID(strUserID);
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null && arlModelList.size() > 0) {
				objDeleteSpecForm.setModelList(arlModelList);
			}
			/*Added to include customer drop down for search criteria by cm68219*/
			
			CustomerVO objCustomerVO = new CustomerVO();
			objCustomerVO.setUserID(objLoginVo.getUserID());
			objCustomerVO.setSpecTypeSeqNo(objDeleteSpecForm.getSpectypeSeqno());
			CustomerBI objCustomerBO = ServiceFactory.getCustomerBO();
			arlCustomerList = objCustomerBO.fetchCustomers(objCustomerVO);
			
			if (arlCustomerList != null && arlCustomerList.size() > 0) {
				objDeleteSpecForm.setCustomerList(arlCustomerList);
				objDeleteSpecForm.setHdnSelectedCustomers(objDeleteSpecForm
						.getHdnSelectedCustomers());
				objDeleteSpecForm.setCustSeqNo(objDeleteSpecForm.getCustSeqNo());
			}
			
			/* Added*/
			
			// To fetch Orders
			/* Added by cm68219 to add  customer drop down for search criteria*/
			//objDeleteSpecForm.setHdnSelectedCustomers(objDeleteSpecForm
			//.getHdnSelectedCustomers());
			objOrderVo.setCustSeqNos(objDeleteSpecForm.getCustSeqNo());
			/*added*/
			objOrderVo.setSpecTypeSeqNo(objDeleteSpecForm.getSpectypeSeqno());
			objOrderVo.setModelSeqNo(objDeleteSpecForm.getModelSeqNo());
			objOrderVo.setOrderNo(ApplicationUtil.trim(objDeleteSpecForm.getOrderNum()));
			objOrderVo.setSpecStatusCode(2);
			objOrderVo.setUserID(strUserID);
			
			arlOrderList = objOrderBo.fetchOrdersModifySpec(objOrderVo);
			
			if (arlOrderList != null && arlOrderList.size() > 0) {
				objDeleteSpecForm.setOrderList(arlOrderList);
			}
			
			objDeleteSpecForm.setHdnSelModel(objDeleteSpecForm.getHdnSelModel());
			objDeleteSpecForm.setHdnSelSpecType(objDeleteSpecForm.getHdnSelSpecType());
			objDeleteSpecForm.setHdnorderNo(objDeleteSpecForm.getHdnorderNo());
			
		} catch (Exception objExp) {
			
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
	}
	
	/***************************************************************************
	 * This Method is used to Delete Orders
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
	public ActionForward deleteOrders(ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest, HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering DeleteSpecAction.deleteOrders");
		String strForward = ApplicationConstants.SUCCESS;
		DeleteSpecForm objDeleteSpecForm = (DeleteSpecForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			
			ModelVo objModelVo = new ModelVo();
			ModelBI objModelBo = ServiceFactory.getModelBO();
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			
			ArrayList arlModelList = new ArrayList();
			ArrayList arlSpecList = new ArrayList();
			/*Added as per Cr 75*/
			ArrayList arlCustomerList = new ArrayList();
			ArrayList arlOrderList = new ArrayList();
			OrderBI objOrderBo = ServiceFactory.getOrderBO();
			OrderVO objOrderVo = new OrderVO();
			
			int intdeleteOrd = 0;
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
				objDeleteSpecForm.setSpecTypeList(arlSpecList);
			}
			
			// To fetch Models
			objModelVo.setSpecTypeSeqNo(objDeleteSpecForm.getSpectypeSeqno());
			objModelVo.setUserID(strUserID);
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null && arlModelList.size() > 0) {
				objDeleteSpecForm.setModelList(arlModelList);
			}
			/*added for customer drop down by cm68219*/
			
			CustomerVO objCustomerVO = new CustomerVO();
			objCustomerVO.setUserID(objLoginVo.getUserID());
			objCustomerVO.setSpecTypeSeqNo(objDeleteSpecForm.getSpectypeSeqno());
			CustomerBI objCustomerBO = ServiceFactory.getCustomerBO();
			arlCustomerList = objCustomerBO.fetchCustomers(objCustomerVO);
			
			if (arlCustomerList != null && arlCustomerList.size() > 0) {
				objDeleteSpecForm.setCustomerList(arlCustomerList);
				
			}
			
			/* added*/
			
			// To delete Orders
			objOrderVo.setOrderNo(objDeleteSpecForm.getHdnorderNo());
			objOrderVo.setUserID(strUserID);
			
			intdeleteOrd = objOrderBo.deleteOrders(objOrderVo);
			
			// To fetch Orders
			/* Added by cm68219 to add  customer for search criteria*/
			objDeleteSpecForm.setHdnSelectedCustomers(objDeleteSpecForm.getHdnSelectedCustomers());
			objOrderVo.setCustSeqNos(objDeleteSpecForm.getCustSeqNo());
			/*added*/
			objOrderVo.setSpecTypeSeqNo(objDeleteSpecForm.getSpectypeSeqno());
			objOrderVo.setModelSeqNo(objDeleteSpecForm.getModelSeqNo());
			objOrderVo.setOrderNo(ApplicationUtil.trim(objDeleteSpecForm.getOrderNum()));
			objOrderVo.setSpecStatusCode(2);
			objOrderVo.setUserID(strUserID);
			
			arlOrderList = objOrderBo.fetchOrdersModifySpec(objOrderVo);
			
			if (arlOrderList != null && arlOrderList.size() > 0) {
				objDeleteSpecForm.setOrderList(arlOrderList);
			}
			
			objDeleteSpecForm.setHdnSelModel(objDeleteSpecForm.getHdnSelModel());
			objDeleteSpecForm.setHdnSelSpecType(objDeleteSpecForm.getHdnSelSpecType());
			objDeleteSpecForm.setHdnorderNo(objDeleteSpecForm.getHdnorderNo());
			
			if (intdeleteOrd == 0) {
				objDeleteSpecForm.setMessageID(ApplicationConstants.DELETE_MESSAGE_ID);
			} else if(intdeleteOrd == 1033){ //else if added for CR-130
				objDeleteSpecForm.setMessageID(ApplicationConstants.MAP_1058_ID);
			} else { //Modified for CR_131
				objDeleteSpecForm.setMessageID("" + intdeleteOrd);
			}
			
			LogUtil.logMessage("objDeleteSpecForm.getMessageID" + objDeleteSpecForm.getMessageID());
			
		} catch (Exception objExp) {
			
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
	}
	
}
