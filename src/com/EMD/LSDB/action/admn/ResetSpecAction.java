package com.EMD.LSDB.action.admn;

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
import com.EMD.LSDB.form.SpecMaintenance.ModifySpecForm;
import com.EMD.LSDB.form.admn.ResetSpecForm;
import com.EMD.LSDB.vo.common.CustomerVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.SpecTypeVo;
/*******************************************************************************************
 * @author  Satyam Computer Services Ltd  
 * @version 1.0  
 * This Action class has the business methods for Reset Spec
 ******************************************************************************************/
public class ResetSpecAction extends EMDAction{

	/*************************************************************************************
	 * This Method is used to fetch the Specification Type Details
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 *************************************************************************************/
	
	public ActionForward initLoad(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering ResetSpecAction.initLoad");
		String strForward = ApplicationConstants.SUCCESS;
		ResetSpecForm objResetSpecForm = (ResetSpecForm) objActionForm;
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
				objResetSpecForm.setSpecTypeList(arlSpecList);
			}
			
		} catch (Exception objExp) {
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
		
	}
	
	/*************************************************************************************
	 * This Method is used to fetch the Model Details
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 *************************************************************************************/
	
	public ActionForward fetchModels(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering ResetSpecAction.fetchModels");
		String strForward = ApplicationConstants.SUCCESS;
		ResetSpecForm objResetSpecForm = (ResetSpecForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			
			ModelVo objModelVo = new ModelVo();
			ModelBI objModelBo = ServiceFactory.getModelBO();
			
			ArrayList arlSpecList = new ArrayList();
			ArrayList arlModelList = new ArrayList();
			/*Added as per CR 75*/
			ArrayList arlCustomerList = new ArrayList();
			
			String strUserID = "";
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			//Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			
			objSpecTypeVo.setUserID(strUserID);
			// To Fetch Specification Types
			arlSpecList = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			
			if (arlSpecList != null && arlSpecList.size() > 0) {
				objResetSpecForm.setSpecTypeList(arlSpecList);
			}
			
			//To fetch Models
			objModelVo.setSpecTypeSeqNo(objResetSpecForm.getSpectypeSeqno());
			objModelVo.setUserID(strUserID);
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null && arlModelList.size() > 0) {
				objResetSpecForm.setModelList(arlModelList);
			}
			
			objResetSpecForm.setHdnSelSpecType(objResetSpecForm
					.getHdnSelSpecType());
			/*Added to include customer drop down for search criteria by cm68219*/
			
			if(objResetSpecForm.getSpectypeSeqno()!=-1){
				CustomerVO objCustomerVO = new CustomerVO();
				objCustomerVO.setUserID(objLoginVo.getUserID());
				objCustomerVO.setSpecTypeSeqNo(objResetSpecForm.getSpectypeSeqno());
				CustomerBI objCustomerBO = ServiceFactory.getCustomerBO();
				arlCustomerList = objCustomerBO.fetchCustomers(objCustomerVO);
				
				if (arlCustomerList != null && arlCustomerList.size() > 0) {
				    objResetSpecForm.setCustomerList(arlCustomerList);
				
				}
			}
			/* Added*/
			
		} catch (Exception objExp) {
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
		
	}
	
	/*************************************************************************************
	 * This Method is used to fetch Orders
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 *************************************************************************************/
	public ActionForward fetchOrders(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering ResetSpecAction.fetchOrders");
		String strForward = ApplicationConstants.SUCCESS;
		ResetSpecForm objResetSpecForm = (ResetSpecForm) objActionForm;
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
			
			//Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			objSpecTypeVo.setUserID(strUserID);
			// To Fetch Specification Types
			arlSpecList = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			
			if (arlSpecList != null && arlSpecList.size() > 0) {
				objResetSpecForm.setSpecTypeList(arlSpecList);
			}
			
			//To fetch Models
			objModelVo.setSpecTypeSeqNo(objResetSpecForm.getSpectypeSeqno());
			objModelVo.setUserID(strUserID);
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null && arlModelList.size() > 0) {
				objResetSpecForm.setModelList(arlModelList);
			}
			
             /*Added to include  customer drop down for search criteria by cm68219*/
			
			CustomerVO objCustomerVO = new CustomerVO();
			objCustomerVO.setUserID(objLoginVo.getUserID());
			objCustomerVO.setSpecTypeSeqNo(objResetSpecForm.getSpectypeSeqno());
			CustomerBI objCustomerBO = ServiceFactory.getCustomerBO();
			arlCustomerList = objCustomerBO.fetchCustomers(objCustomerVO);
			
			if (arlCustomerList != null && arlCustomerList.size() > 0) {
			    objResetSpecForm.setCustomerList(arlCustomerList);
			    objResetSpecForm.setHdnSelectedCustomers(objResetSpecForm
						.getHdnSelectedCustomers());
			    objResetSpecForm.setCustSeqNo(objResetSpecForm.getCustSeqNo());
			}

			
			//To fetch Orders
			objOrderVo.setCustSeqNos(objResetSpecForm.getCustSeqNo());
			
			objOrderVo.setSpecTypeSeqNo(objResetSpecForm.getSpectypeSeqno());
			objOrderVo.setModelSeqNo(objResetSpecForm.getModelSeqNo());
			objOrderVo.setOrderNo(ApplicationUtil.trim(objResetSpecForm
					.getOrderNo()));
			objOrderVo.setUserID(strUserID);
						
			arlOrderList = objOrderBo.fetchOrdersModifySpec(objOrderVo);
			
			if (arlOrderList != null && arlOrderList.size() > 0) {
				objResetSpecForm.setOrderList(arlOrderList);
		
			}
			
			objResetSpecForm
			.setHdnSelModel(objResetSpecForm.getHdnSelModel());
			objResetSpecForm.setHdnSelSpecType(objResetSpecForm
					.getHdnSelSpecType());
			objResetSpecForm.setHdnorderNo(objResetSpecForm.getHdnorderNo());
			
		} catch (Exception objExp) {
			
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
	}

	/*************************************************************************************
	 * This Method is used to reset the spec status
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 *************************************************************************************/
	public ActionForward resetSpecStatus(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering ResetSpecAction.fetchOrders");
		String strForward = ApplicationConstants.SUCCESS;
		ResetSpecForm objResetSpecForm = (ResetSpecForm) objActionForm;
		try{
			int intSuccess = 0;
			OrderBI objOrderBo = ServiceFactory.getOrderBO();
			OrderVO objOrderVo = new OrderVO();
			HttpSession objSession = objHttpServletRequest.getSession(false);
			String strUserID = "";
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			//Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			LogUtil.logMessage("From Order Key :"+objResetSpecForm.getFromOrderKey());
			LogUtil.logMessage("To Order Key :"+objResetSpecForm.getHdnToOrderkey());
			LogUtil.logMessage("strUserID:"+strUserID);
			objOrderVo.setFromOrderKey(objResetSpecForm.getFromOrderKey());
			objOrderVo.setToOrderkey(objResetSpecForm.getHdnToOrderkey());
			objOrderVo.setUserID(strUserID);
			intSuccess = objOrderBo.resetSpecStatus(objOrderVo);
			if (intSuccess == 0) {
				objResetSpecForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objResetSpecForm.setMessageID("" + intSuccess);
			}
		}
		catch (Exception objExp) {
			
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		if (strForward.equalsIgnoreCase(ApplicationConstants.SUCCESS))
			return fetchOrders(objActionMapping, objActionForm,
					objHttpServletRequest, objHttpServletResponse);
		else
			return objActionMapping.findForward(strForward);
				
	}
	
		
}
