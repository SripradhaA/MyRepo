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
import com.EMD.LSDB.common.constant.DatabaseConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.form.SpecMaintenance.ModifySpecForm;
import com.EMD.LSDB.vo.common.CustomerVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.SpecTypeVo;

/*******************************************************************************************
 * @author  Satyam Computer Services Ltd  
 * @version 1.0  
 * This Action class has the business methods for Modify Spec
 ******************************************************************************************/
public class ModifySpecAction extends EMDAction {
	
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
		
		LogUtil.logMessage("Entering ModifySpecAction.initLoad");
		String strForward = ApplicationConstants.SUCCESS;
		ModifySpecForm objModifySpecForm = (ModifySpecForm) objActionForm;
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
				objModifySpecForm.setSpecTypeList(arlSpecList);
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
		
		LogUtil.logMessage("Entering ModifySpecAction.fetchModels");
		String strForward = ApplicationConstants.SUCCESS;
		ModifySpecForm objModifySpecForm = (ModifySpecForm) objActionForm;
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
				objModifySpecForm.setSpecTypeList(arlSpecList);
			}
			
			//To fetch Models
			objModelVo.setSpecTypeSeqNo(objModifySpecForm.getSpectypeSeqno());
			objModelVo.setUserID(strUserID);
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null && arlModelList.size() > 0) {
				objModifySpecForm.setModelList(arlModelList);
			}
			
			objModifySpecForm.setHdnSelSpecType(objModifySpecForm
					.getHdnSelSpecType());
			/*Added to include customer drop down for search criteria by cm68219*/
			
			if(objModifySpecForm.getSpectypeSeqno()!=-1){
				CustomerVO objCustomerVO = new CustomerVO();
				objCustomerVO.setUserID(objLoginVo.getUserID());
				objCustomerVO.setSpecTypeSeqNo(objModifySpecForm.getSpectypeSeqno());
				CustomerBI objCustomerBO = ServiceFactory.getCustomerBO();
				arlCustomerList = objCustomerBO.fetchCustomers(objCustomerVO);
				
				if (arlCustomerList != null && arlCustomerList.size() > 0) {
				    objModifySpecForm.setCustomerList(arlCustomerList);
				
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
		
		LogUtil.logMessage("Entering ModifySpecAction.fetchOrders");
		String strForward = ApplicationConstants.SUCCESS;
		ModifySpecForm objModifySpecForm = (ModifySpecForm) objActionForm;
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
				objModifySpecForm.setSpecTypeList(arlSpecList);
			}
			
			//To fetch Models
			objModelVo.setSpecTypeSeqNo(objModifySpecForm.getSpectypeSeqno());
			objModelVo.setUserID(strUserID);
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null && arlModelList.size() > 0) {
				objModifySpecForm.setModelList(arlModelList);
			}
			
             /*Added to include  customer drop down for search criteria by cm68219*/
			
			CustomerVO objCustomerVO = new CustomerVO();
			objCustomerVO.setUserID(objLoginVo.getUserID());
			objCustomerVO.setSpecTypeSeqNo(objModifySpecForm.getSpectypeSeqno());
			CustomerBI objCustomerBO = ServiceFactory.getCustomerBO();
			arlCustomerList = objCustomerBO.fetchCustomers(objCustomerVO);
			
			if (arlCustomerList != null && arlCustomerList.size() > 0) {
			    objModifySpecForm.setCustomerList(arlCustomerList);
			    objModifySpecForm.setHdnSelectedCustomers(objModifySpecForm
						.getHdnSelectedCustomers());
			    objModifySpecForm.setCustSeqNo(objModifySpecForm.getCustSeqNo());
			}

			
			//To fetch Orders
			objOrderVo.setCustSeqNos(objModifySpecForm.getCustSeqNo());
			
			objOrderVo.setSpecTypeSeqNo(objModifySpecForm.getSpectypeSeqno());
			objOrderVo.setModelSeqNo(objModifySpecForm.getModelSeqNo());
			objOrderVo.setOrderNo(ApplicationUtil.trim(objModifySpecForm
					.getOrderNo()));
			objOrderVo.setUserID(strUserID);
			//Added for LSDB_CR-76
			objOrderVo.setSortBy(objModifySpecForm.getSortByFlag());
				
			arlOrderList = objOrderBo.fetchOrdersModifySpec(objOrderVo);
			
			if (arlOrderList != null && arlOrderList.size() > 0) {
				objModifySpecForm.setOrderList(arlOrderList);
				
				objModifySpecForm.setSortByFlag(objModifySpecForm.getSortByFlag());
			}
			
			objModifySpecForm
			.setHdnSelModel(objModifySpecForm.getHdnSelModel());
			objModifySpecForm.setHdnSelSpecType(objModifySpecForm
					.getHdnSelSpecType());
			objModifySpecForm.setHdnorderNo(objModifySpecForm.getHdnorderNo());
			
			//Added for LSDB_CR-76
			objModifySpecForm.setColumnheader(objModifySpecForm.getColumnheader());
			
		} catch (Exception objExp) {
			
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
	}
	
	/*************************************************************************************
	 * This Method is used to Update Orders
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 *************************************************************************************/
	public ActionForward updateOrders(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering ModifySpecAction.updateOrders");
		String strForward = ApplicationConstants.SUCCESS;
		ModifySpecForm objModifySpecForm = (ModifySpecForm) objActionForm;
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
			
			int intUpdOrd = 0;
			String strUserID = "";
			//String strOrderKey = "";
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
				objModifySpecForm.setSpecTypeList(arlSpecList);
			}
			
			//To fetch Models
			objModelVo.setSpecTypeSeqNo(objModifySpecForm.getSpectypeSeqno());
			objModelVo.setUserID(strUserID);
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null && arlModelList.size() > 0) {
				objModifySpecForm.setModelList(arlModelList);
			}
			/*Added for customer drop down by cm68219*/
			CustomerVO objCustomerVO = new CustomerVO();
			objCustomerVO.setUserID(objLoginVo.getUserID());
			objCustomerVO.setSpecTypeSeqNo(objModifySpecForm.getSpectypeSeqno());
			CustomerBI objCustomerBO = ServiceFactory.getCustomerBO();
			arlCustomerList = objCustomerBO.fetchCustomers(objCustomerVO);
			
			if (arlCustomerList != null && arlCustomerList.size() > 0) {
			    objModifySpecForm.setCustomerList(arlCustomerList);
			
			}
			//To Update Orders
			//if(objModifySpecForm.getOrderKey()!=null){
			//strOrderKey = ApplicationUtil.trim(objModifySpecForm.getOrderKey());
			objOrderVo.setOrderKey(objModifySpecForm.getOrderKey());
			//}
			objOrderVo.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			objOrderVo.setQuantity(objModifySpecForm.getHdnQty());
			objOrderVo.setUserID(strUserID);
			
			//Added for update SAP Customer Code CR
			objOrderVo.setSapCusCode(objModifySpecForm.getHdnSapCustCode());
			//Added custmodelname For CR_104 
			objOrderVo.setCustMdlName(objModifySpecForm.getHdnCustMdlName());
			intUpdOrd = objOrderBo.updateOrders(objOrderVo);
			
			//To fetch Orders
			objOrderVo = new OrderVO();
			objOrderVo.setSpecTypeSeqNo(objModifySpecForm.getSpectypeSeqno());
			objOrderVo.setModelSeqNo(objModifySpecForm.getModelSeqNo());
			objOrderVo.setOrderNo(ApplicationUtil.trim(objModifySpecForm
					.getHdnorderNo()));
			objOrderVo.setCustSeqNos(objModifySpecForm.getCustSeqNo());
			objOrderVo.setUserID(strUserID);
			//Added for LSDB_CR-76
			objOrderVo.setSortBy(objModifySpecForm.getSortByFlag());
			
			arlOrderList = objOrderBo.fetchOrdersModifySpec(objOrderVo);
			
			if (arlOrderList != null && arlOrderList.size() > 0) {
				objModifySpecForm.setOrderList(arlOrderList);
			}
			
			if (intUpdOrd == 0) {
				objModifySpecForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objModifySpecForm.setMessageID("" + intUpdOrd);
			}
			
			//Added for LSDB_CR-76
			objModifySpecForm.setColumnheader(objModifySpecForm.getColumnheader());
		} catch (Exception objExp) {
			
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
	}
}
