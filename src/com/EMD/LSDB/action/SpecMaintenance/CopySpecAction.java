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
import com.EMD.LSDB.bo.serviceinterface.DistributorBI;
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
import com.EMD.LSDB.form.SpecMaintenance.CopySpecForm;
import com.EMD.LSDB.vo.common.CustomerVO;
import com.EMD.LSDB.vo.common.DistributorVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.SpecTypeVo;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This Action class has the business methods for Copy Spec
 ******************************************************************************/

public class CopySpecAction extends EMDAction {
	
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
		
		LogUtil.logMessage("Entering CopySpecAction.initLoad");
		String strForward = ApplicationConstants.SUCCESS;
		CopySpecForm objCopySpecForm = (CopySpecForm) objActionForm;
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
				objCopySpecForm.setSpecTypeList(arlSpecList);
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
		
		LogUtil.logMessage("Entering CopySpecAction.fetchModels");
		String strForward = ApplicationConstants.SUCCESS;
		CopySpecForm objCopySpecForm = (CopySpecForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			
			ModelVo objModelVo = new ModelVo();
			ModelBI objModelBo = ServiceFactory.getModelBO();
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			
			ArrayList arlModelList = new ArrayList();
			ArrayList arlSpecList = new ArrayList();
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
				objCopySpecForm.setSpecTypeList(arlSpecList);
			}
			
			// To fetch Models
			objModelVo.setSpecTypeSeqNo(objCopySpecForm.getSpectypeSeqno());
			objModelVo.setUserID(strUserID);
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null && arlModelList.size() > 0) {
				objCopySpecForm.setModelList(arlModelList);
			}
			/* added for customer drop down by cm68219 */
			
			if (objCopySpecForm.getSpectypeSeqno() != -1) {
				CustomerVO objCustomerVO = new CustomerVO();
				objCustomerVO.setUserID(objLoginVo.getUserID());
				objCustomerVO.setSpecTypeSeqNo(objCopySpecForm.getSpectypeSeqno());
				CustomerBI objCustomerBO = ServiceFactory.getCustomerBO();
				arlCustomerList = objCustomerBO.fetchCustomers(objCustomerVO);
				
				if (arlCustomerList != null && arlCustomerList.size() > 0) {
					objCopySpecForm.setCustList(arlCustomerList);
					
				}
			}
			/* added */
			objCopySpecForm.setHdnSelSpecType(objCopySpecForm.getHdnSelSpecType());
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
	
	/**
	 * Methods fetchOrders,copySpec,CopySpecFromModel,loadCustomers are Modified
	 * based on the requirement of LSDB_CR-56 Modified on 25-Aug-08 by ps57222
	 */
	
	public ActionForward fetchOrders(ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest, HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering CopySpecAction.fetchOrders");
		String strForward = ApplicationConstants.SUCCESS;
		CopySpecForm objCopySpecForm = (CopySpecForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			
			ModelVo objModelVo = new ModelVo();
			ModelBI objModelBo = ServiceFactory.getModelBO();
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			
			DistributorVO objDistVo = new DistributorVO();
			DistributorBI objDistBo = ServiceFactory.getDistributorBO();
			
			CustomerVO objCustVo = new CustomerVO();
			CustomerBI objCustBo = ServiceFactory.getCustomerBO();
			
			ArrayList arlModelList = new ArrayList();
			ArrayList arlSpecList = new ArrayList();
			
			ArrayList arlDistList = new ArrayList();
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
				objCopySpecForm.setSpecTypeList(arlSpecList);
			}
			
			// To fetch Models
			objModelVo.setSpecTypeSeqNo(objCopySpecForm.getSpectypeSeqno());
			objModelVo.setUserID(strUserID);
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null && arlModelList.size() > 0) {
				objCopySpecForm.setModelList(arlModelList);
			}
			
			objDistVo.setUserID(strUserID);
			// To fetch Distributors
			arlDistList = objDistBo.fetchDistributors(objDistVo);
			
			if (arlDistList != null && arlDistList.size() > 0) {
				objCopySpecForm.setDistList(arlDistList);
			}
			
			//Added For CR_84
			objSpecTypeVo.setSpectypeSeqno(objCopySpecForm.getSpectypeSeqno());
			ArrayList arlSpecType = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpecType.size() != 0) {
				
				SpecTypeVo objjSpecTypeVo = (SpecTypeVo) arlSpecType.get(0);
				if(objjSpecTypeVo.isDistMaintDisPlayFlag())
					objCopySpecForm.setDistributorFlag(ApplicationConstants.YES);
				else
					objCopySpecForm.setDistributorFlag(ApplicationConstants.NO);
			}
			LogUtil.logMessage("objCopySpecForm.getDistributorFlag(): "
					+ objCopySpecForm.getDistributorFlag());
			//Added For CR_84 - Ends here
			/**
			 * The Customer drop down should be loaded based on the customer
			 * type seqno and Specificationtypeseqno, this change is applicable
			 * only if the Specifictaion type is Locomotive. If at all it's PM&I
			 * then the drop down should be loaded with domestic customers by
			 * default. (Requirement changed for LSDB_CR-46[PM&I] changed on
			 * 02-09-08 by si50968)
			 */
			if (objCopySpecForm.getDistributorFlag().equalsIgnoreCase(ApplicationConstants.YES)){
			//if ("2".equals(String.valueOf(objCopySpecForm.getSpectypeSeqno()))) {
				objCustVo.setCustTypeSeqNo(1);
				objCustVo.setUserID(strUserID);
				objCustVo.setSpecTypeSeqNo(objCopySpecForm.getSpectypeSeqno());
				// To fetch Customers
				arlCustList = objCustBo.fetchCustomers(objCustVo);
			}
			else if (objCopySpecForm.getDistributorFlag().equalsIgnoreCase(ApplicationConstants.NO)){
			//else if ("1".equals(String.valueOf(objCopySpecForm.getSpectypeSeqno()))) {
				//objCustVo.setCustTypeSeqNo(objCopySpecForm.getCustTypeSeqNo());
				objCustVo.setUserID(strUserID);
				objCustVo.setSpecTypeSeqNo(objCopySpecForm.getSpectypeSeqno());
				// To fetch Customers
				arlCustList = objCustBo.fetchCustomers(objCustVo);
				
			}
			
			if (arlCustList != null && arlCustList.size() > 0) {
				objCopySpecForm.setCustList(arlCustList);
				objCopySpecForm.setCustomerSeqNO(objCopySpecForm.getCustomerSeqNO());
				/*Added to populate customers in the lower drop down*/
				if (objCopySpecForm.getDistributorFlag().equalsIgnoreCase(ApplicationConstants.YES)){
				//if ("2".equals(String.valueOf(objCopySpecForm.getSpectypeSeqno()))) {
					objCopySpecForm.setCustomerList(arlCustList);
				}
				
			}
			objCopySpecForm.setHdnSelectedCustomers(objCopySpecForm.getHdnSelectedCustomers());
			objCopySpecForm.setHdnPreSelectedCustomer(objCopySpecForm.getCustomerSeqNO());
			/* Added by cm68219 to add customer drop down for search criteria */
			objOrderVo.setCusSeqNo(objCopySpecForm.getCustomerSeqNO());
			
			/* Added */
			objCopySpecForm.setHdPreSelectedModel(objCopySpecForm.getModelSeqNo());
			// To fetch Orders
			objOrderVo.setSpecTypeSeqNo(objCopySpecForm.getSpectypeSeqno());
			objOrderVo.setModelSeqNo(objCopySpecForm.getModelSeqNo());
			objOrderVo.setOrderNo(ApplicationUtil.trim(objCopySpecForm.getOrderNo()));
			
			objOrderVo.setDataLocTypeCode(DatabaseConstants.DATALOCATION_SNAP_SHOT);
			objOrderVo.setUserID(strUserID);
			
			arlOrderList = objOrderBo.fetchOrders(objOrderVo);
			
			if (arlOrderList != null && arlOrderList.size() > 0) {
				objCopySpecForm.setOrderList(arlOrderList);
			}
			
			objCopySpecForm.setHdnSelModel(objCopySpecForm.getHdnSelModel());
			objCopySpecForm.setHdnSelSpecType(objCopySpecForm.getHdnSelSpecType());
			
		} catch (Exception objExp) {
			
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
	}
	
	/***************************************************************************
	 * This Method is used to Copy details of the existing spec at Order level
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
	public ActionForward copySpec(ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest, HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering CopySpecAction.fetchOrders");
		String strForward = ApplicationConstants.SUCCESS;
		CopySpecForm objCopySpecForm = (CopySpecForm) objActionForm;
		boolean copyMdlIndFlag=false;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			
			ModelVo objModelVo = new ModelVo();
			ModelBI objModelBo = ServiceFactory.getModelBO();
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			
			DistributorVO objDistVo = new DistributorVO();
			DistributorBI objDistBo = ServiceFactory.getDistributorBO();
			
			CustomerVO objCustVo = new CustomerVO();
			CustomerBI objCustBo = ServiceFactory.getCustomerBO();
			
			ArrayList arlModelList = new ArrayList();
			ArrayList arlSpecList = new ArrayList();
			
			ArrayList arlDistList = new ArrayList();
			ArrayList arlCustList = new ArrayList();
			
			ArrayList arlOrderList = new ArrayList();
			OrderBI objOrderBo = ServiceFactory.getOrderBO();
			OrderVO objOrderVo = new OrderVO();
			
			String strUserID = "";
			
			int intCopySpec = 0;
			
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
				objCopySpecForm.setSpecTypeList(arlSpecList);
			}
			
			// To fetch Models
			objModelVo.setSpecTypeSeqNo(objCopySpecForm.getSpectypeSeqno());
			objModelVo.setUserID(strUserID);
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null && arlModelList.size() > 0) {
				objCopySpecForm.setModelList(arlModelList);
			}
			objDistVo.setUserID(strUserID);
			// To fetch Distributors
			arlDistList = objDistBo.fetchDistributors(objDistVo);
			
			if (arlDistList != null && arlDistList.size() > 0) {
				objCopySpecForm.setDistList(arlDistList);
			}
			
			/**
			 * The Customer drop down should be loaded based on the customer
			 * type seqno and Specificationtypeseqno, this change is applicable
			 * only if the Specifictaion type is Locomotive. If at all it's PM&I
			 * then the drop down should be loaded with domestic customers by
			 * default. (Requirement changed for LSDB_CR-46[PM&I] changed on
			 * 02-09-08 by si50968)
			 */
			//Added For CR_84
			if (objCopySpecForm.getDistributorFlag().equalsIgnoreCase(ApplicationConstants.YES)){
			//if ("2".equals(String.valueOf(objCopySpecForm.getSpectypeSeqno()))) {
				objCustVo.setCustTypeSeqNo(1);
				objCustVo.setUserID(strUserID);
				objCustVo.setSpecTypeSeqNo(objCopySpecForm.getSpectypeSeqno());
				// To fetch Customers
				arlCustList = objCustBo.fetchCustomers(objCustVo);
			}
			else if (objCopySpecForm.getDistributorFlag().equalsIgnoreCase(ApplicationConstants.NO)){
			//else if ("1".equals(String.valueOf(objCopySpecForm.getSpectypeSeqno()))) {
				//objCustVo.setCustTypeSeqNo(objCopySpecForm.getCustTypeSeqNo());
				objCustVo.setUserID(strUserID);
				objCustVo.setSpecTypeSeqNo(objCopySpecForm.getSpectypeSeqno());
				// To fetch Customers
				arlCustList = objCustBo.fetchCustomers(objCustVo);
				
			}
			
			if (arlCustList != null && arlCustList.size() > 0) {
				objCopySpecForm.setCustList(arlCustList);
				objCopySpecForm.setCustomerSeqNO(objCopySpecForm.getCustomerSeqNO());
				/*Added to populate customers in the lower drop down*/
				if (objCopySpecForm.getDistributorFlag().equalsIgnoreCase(ApplicationConstants.YES)){
				//if ("2".equals(String.valueOf(objCopySpecForm.getSpectypeSeqno()))) {
					objCopySpecForm.setCustomerList(arlCustList);
				}
				
			}
			objCopySpecForm.setHdnSelectedCustomers(objCopySpecForm.getHdnSelectedCustomers());
			
			// To Copy Orders
			// Added For CR_108 
			copyMdlIndFlag=objCopySpecForm.isCopyMdlIndFlag();
			if (copyMdlIndFlag){
				objOrderVo.setCopyMdlIndFlag("Y");
				}
			else {
				objOrderVo.setCopyMdlIndFlag("N");
			}
			objOrderVo.setOrderKey(objCopySpecForm.getHdnOrderKey()); //Modified for CR_131
			objOrderVo.setSpecTypeSeqNo(objCopySpecForm.getSpectypeSeqno());
			objOrderVo.setDistSeqNo(objCopySpecForm.getDistSeqNo());
			objOrderVo.setCusSeqNo(objCopySpecForm.getCustSeqNo());
			objOrderVo.setModelSeqNo(objCopySpecForm.getModelSeqNo());
			objOrderVo.setSapCusCode(ApplicationUtil.trim(objCopySpecForm.getSapCustCode()));
			objOrderVo.setOrderNo(ApplicationUtil.trim(objCopySpecForm.getOrderNumber()));
			objOrderVo.setQuantity(Integer.parseInt(ApplicationUtil.trim(String.valueOf(objCopySpecForm.getQuantity()))));
			objOrderVo.setUserID(strUserID);
			intCopySpec = objOrderBo.insertCopySpec(objOrderVo);
			
			// To fetch Orders
			objOrderVo = new OrderVO();
			objOrderVo.setCusSeqNo(objCopySpecForm.getCustomerSeqNO());
			objOrderVo.setSpecTypeSeqNo(objCopySpecForm.getSpectypeSeqno());
			objOrderVo.setModelSeqNo(objCopySpecForm.getModelSeqNo());
			objOrderVo.setOrderNo(ApplicationUtil.trim(objCopySpecForm.getOrderNo()));
			objOrderVo.setDataLocTypeCode(DatabaseConstants.DATALOCATION_SNAP_SHOT);
			objOrderVo.setUserID(strUserID);
			
			arlOrderList = objOrderBo.fetchOrders(objOrderVo);
			
			if (arlOrderList != null && arlOrderList.size() > 0) {
				objCopySpecForm.setOrderList(arlOrderList);
			}
			
			objCopySpecForm.setHdnSelModel(objCopySpecForm.getHdnSelModel());
			objCopySpecForm.setHdnSelSpecType(objCopySpecForm.getHdnSelSpecType());
			//Added for customer drop downbelow to display select after loading
			objCopySpecForm.setCustSeqNo(-1);
			objCopySpecForm.setDistSeqNo(-1);
			
			if (intCopySpec == 0) {
				objCopySpecForm.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objCopySpecForm.setMessageID("" + intCopySpec);
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
	 * This Method is used to Copy the model level details to the Order level.
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
	public ActionForward copySpecFromModel(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws DataAccessException,
			ApplicationException {
		
		LogUtil.logMessage("Entering CopySpecAction.copySpecFromModel");
		String strForward = ApplicationConstants.SUCCESS;
		CopySpecForm objCopySpecForm = (CopySpecForm) objActionForm;
		boolean copyMdlIndFlag=false;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			
			ModelVo objModelVo = new ModelVo();
			ModelBI objModelBo = ServiceFactory.getModelBO();
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			
			DistributorVO objDistVo = new DistributorVO();
			DistributorBI objDistBo = ServiceFactory.getDistributorBO();
			
			CustomerVO objCustVo = new CustomerVO();
			CustomerBI objCustBo = ServiceFactory.getCustomerBO();
			
			ArrayList arlModelList = new ArrayList();
			ArrayList arlSpecList = new ArrayList();
			
			ArrayList arlDistList = new ArrayList();
			ArrayList arlCustList = new ArrayList();
			
			ArrayList arlOrderList = new ArrayList();
			OrderBI objOrderBo = ServiceFactory.getOrderBO();
			OrderVO objOrderVo = new OrderVO();
			
			String strUserID = "";
			//String strOrderKey = "";
			int intCopySpecFrmModel = 0;
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
				objCopySpecForm.setSpecTypeList(arlSpecList);
			}
			
			// To fetch Models
			objModelVo.setSpecTypeSeqNo(objCopySpecForm.getSpectypeSeqno());
			objModelVo.setUserID(strUserID);
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null && arlModelList.size() > 0) {
				objCopySpecForm.setModelList(arlModelList);
			}
			objDistVo.setUserID(strUserID);
			// To fetch Distributors
			arlDistList = objDistBo.fetchDistributors(objDistVo);
			
			if (arlDistList != null && arlDistList.size() > 0) {
				objCopySpecForm.setDistList(arlDistList);
			}
			
			/**
			 * The Customer drop down should be loaded based on the customer
			 * type seqno and Specificationtypeseqno, this change is applicable
			 * only if the Specifictaion type is Locomotive. If at all it's PM&I
			 * then the drop down should be loaded with domestic customers by
			 * default. (Requirement changed for LSDB_CR-46[PM&I] changed on
			 * 02-09-08 by si50968)
			 */
			//Modified For CR_84
			if (objCopySpecForm.getDistributorFlag().equalsIgnoreCase(ApplicationConstants.YES)){
			//if ("2".equals(String.valueOf(objCopySpecForm.getSpectypeSeqno()))) {
				objCustVo.setCustTypeSeqNo(1);
				objCustVo.setUserID(strUserID);
				objCustVo.setSpecTypeSeqNo(objCopySpecForm.getSpectypeSeqno());
				// To fetch Customers
				arlCustList = objCustBo.fetchCustomers(objCustVo);
			}
			else if (objCopySpecForm.getDistributorFlag().equalsIgnoreCase(ApplicationConstants.NO)){
			//else if ("1".equals(String.valueOf(objCopySpecForm.getSpectypeSeqno()))) {
				//objCustVo.setCustTypeSeqNo(objCopySpecForm.getCustTypeSeqNo());
				objCustVo.setUserID(strUserID);
				objCustVo.setSpecTypeSeqNo(objCopySpecForm.getSpectypeSeqno());
				// To fetch Customers
				arlCustList = objCustBo.fetchCustomers(objCustVo);
				
			}
			
			if (arlCustList != null && arlCustList.size() > 0) {
				objCopySpecForm.setCustList(arlCustList);
				objCopySpecForm.setCustomerSeqNO(objCopySpecForm.getCustomerSeqNO());
				/*Added to populate customers in the lower drop down*/
				if (objCopySpecForm.getDistributorFlag().equalsIgnoreCase(ApplicationConstants.YES)){
				//if ("2".equals(String.valueOf(objCopySpecForm.getSpectypeSeqno()))) {
					objCopySpecForm.setCustomerList(arlCustList);
					
				}
				
			}
			objCopySpecForm.setHdnSelectedCustomers(objCopySpecForm.getHdnSelectedCustomers());
			
			// To Copy Orders
			
			
			// Added For CR_108 
			copyMdlIndFlag=objCopySpecForm.isCopyMdlIndFlag();
			if (copyMdlIndFlag){
				objOrderVo.setCopyMdlIndFlag("Y");
				}
			else {
				objOrderVo.setCopyMdlIndFlag("N");
			}
			objOrderVo.setOrderKey(objCopySpecForm.getHdnOrderKey()); //Modified for CR_131
			objOrderVo.setSpecTypeSeqNo(objCopySpecForm.getSpectypeSeqno());
			objOrderVo.setDistSeqNo(objCopySpecForm.getDistSeqNo());
			objOrderVo.setCusSeqNo(objCopySpecForm.getCustSeqNo());
			objOrderVo.setModelSeqNo(objCopySpecForm.getModelSeqNo());
			objOrderVo.setSapCusCode(ApplicationUtil.trim(objCopySpecForm.getSapCustCode()));
			objOrderVo.setOrderNo(ApplicationUtil.trim(objCopySpecForm.getOrderNumber()));
			objOrderVo.setQuantity(Integer.parseInt(ApplicationUtil.trim(String.valueOf(objCopySpecForm.getQuantity()))));
			objOrderVo.setUserID(strUserID);
						
			
			intCopySpecFrmModel = objOrderBo.insertCopySpecFromModel(objOrderVo);
			
			if (intCopySpecFrmModel == 0) {
				objCopySpecForm.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objCopySpecForm.setMessageID("" + intCopySpecFrmModel);
			}
			
			// To fetch Orders
			objOrderVo = new OrderVO();
			objOrderVo.setCusSeqNo(objCopySpecForm.getCustomerSeqNO());
			objOrderVo.setSpecTypeSeqNo(objCopySpecForm.getSpectypeSeqno());
			objOrderVo.setModelSeqNo(objCopySpecForm.getModelSeqNo());
			objOrderVo.setOrderNo(ApplicationUtil.trim(objCopySpecForm.getOrderNo()));
			objOrderVo.setDataLocTypeCode(DatabaseConstants.DATALOCATION_SNAP_SHOT);
			objOrderVo.setUserID(strUserID);
			
			arlOrderList = objOrderBo.fetchOrders(objOrderVo);
			
			if (arlOrderList != null && arlOrderList.size() > 0) {
				objCopySpecForm.setOrderList(arlOrderList);
			}
			
			objCopySpecForm.setHdnSelModel(objCopySpecForm.getHdnSelModel());
			objCopySpecForm.setHdnSelSpecType(objCopySpecForm.getHdnSelSpecType());
			
			//Added for customer drop down below to display select after loading
			objCopySpecForm.setCustSeqNo(-1);
			objCopySpecForm.setDistSeqNo(-1);
			
		} catch (Exception objExp) {
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForward);
	}
	
	/***************************************************************************
	 * This Method is used to load Customers based on customer type seq no
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
	public ActionForward loadCustomers(ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest, HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering CopySpecAction.loadCustomers");
		String strForward = ApplicationConstants.SUCCESS;
		CopySpecForm objCopySpecForm = (CopySpecForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			
			ModelVo objModelVo = new ModelVo();
			ModelBI objModelBo = ServiceFactory.getModelBO();
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			
			DistributorVO objDistVo = new DistributorVO();
			DistributorBI objDistBo = ServiceFactory.getDistributorBO();
			
			CustomerVO objCustVo = new CustomerVO();
			CustomerBI objCustBo = ServiceFactory.getCustomerBO();
			
			ArrayList arlModelList = new ArrayList();
			ArrayList arlSpecList = new ArrayList();
			
			ArrayList arlDistList = new ArrayList();
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
				objCopySpecForm.setSpecTypeList(arlSpecList);
			}
			
			// To fetch Models
			objModelVo.setSpecTypeSeqNo(objCopySpecForm.getSpectypeSeqno());
			objModelVo.setUserID(strUserID);
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null && arlModelList.size() > 0) {
				objCopySpecForm.setModelList(arlModelList);
			}
			
			objDistVo.setUserID(strUserID);
			// To fetch Distributors
			arlDistList = objDistBo.fetchDistributors(objDistVo);
			
			if (arlDistList != null && arlDistList.size() > 0) {
				objCopySpecForm.setDistList(arlDistList);
			}
			
			/**
			 * The Customer drop down should be loaded based on the customer
			 * type seqno and Specificationtypeseqno, this change is applicable
			 * only if the Specifictaion type is Locomotive. If at all it's PM&I
			 * then the drop down should be loaded with domestic customers by
			 * default. (Requirement changed for LSDB_CR-46[PM&I] changed on
			 * 02-09-08 by si50968)
			 */
			
			objCustVo.setSpecTypeSeqNo(objCopySpecForm.getSpectypeSeqno());
			if (objCopySpecForm.getDistributorFlag().equalsIgnoreCase(ApplicationConstants.NO)){
			//if ("1".equals(String.valueOf(objCopySpecForm.getSpectypeSeqno()))) {
				objCustVo.setSpecTypeSeqNo(objCopySpecForm.getSpectypeSeqno());
				objCustVo.setUserID(strUserID);
				// To fetch Customers
				arlCustList = objCustBo.fetchCustomers(objCustVo);
			}else if (objCopySpecForm.getDistributorFlag().equalsIgnoreCase(ApplicationConstants.YES)){
			//else if ("2".equals(String.valueOf(objCopySpecForm.getSpectypeSeqno()))) {
				objCustVo.setCustTypeSeqNo(1);
				objCustVo.setUserID(strUserID);
				objCustVo.setSpecTypeSeqNo(objCopySpecForm.getSpectypeSeqno());
				// To fetch Customers
				arlCustList = objCustBo.fetchCustomers(objCustVo);
			}
			
			if (arlCustList != null && arlCustList.size() > 0) {
				objCopySpecForm.setCustList(arlCustList);
				objCopySpecForm.setCustomerSeqNO(objCopySpecForm.getCustomerSeqNO());
				
			}
			
			//To fetch Custmers for below drop down
			objCustVo.setCustTypeSeqNo(objCopySpecForm.getCustTypeSeqNo());
			objCustVo.setUserID(strUserID);
			objCustVo.setSpecTypeSeqNo(objCopySpecForm.getSpectypeSeqno());
			// To fetch Customers
			arlCustList = objCustBo.fetchCustomers(objCustVo);
			
			if (arlCustList != null && arlCustList.size() > 0) {
				objCopySpecForm.setCustomerList(arlCustList);
				objCopySpecForm.setCustomerSeqNO(objCopySpecForm.getCustomerSeqNO());
				objCopySpecForm.setHdnSelectedCustomers(objCopySpecForm.getHdnSelectedCustomers());
				objCopySpecForm.setHdnPreSelectedCustomer(objCopySpecForm.getHdnPreSelectedCustomer());
			}
			
			objCopySpecForm.setHdPreSelectedModel(objCopySpecForm.getModelSeqNo());
			// To fetch Orders
			objOrderVo.setCusSeqNo(objCopySpecForm.getCustomerSeqNO());
			objOrderVo.setSpecTypeSeqNo(objCopySpecForm.getSpectypeSeqno());
			objOrderVo.setModelSeqNo(objCopySpecForm.getModelSeqNo());
			objOrderVo.setOrderNo(ApplicationUtil.trim(objCopySpecForm.getOrderNo()));
			objOrderVo.setDataLocTypeCode(DatabaseConstants.DATALOCATION_SNAP_SHOT);
			objOrderVo.setUserID(strUserID);
			
			arlOrderList = objOrderBo.fetchOrders(objOrderVo);
			
			if (arlOrderList != null && arlOrderList.size() > 0) {
				objCopySpecForm.setOrderList(arlOrderList);
			}
			
			objCopySpecForm.setHdnSelModel(objCopySpecForm.getHdnSelModel());
			objCopySpecForm.setHdnSelSpecType(objCopySpecForm.getHdnSelSpecType());			
			
		} catch (Exception objExp) {
			
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
	}
}