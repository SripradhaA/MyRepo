/*
 * Created on Jun 21, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
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
import com.EMD.LSDB.bo.serviceinterface.DistributorBI;
import com.EMD.LSDB.bo.serviceinterface.ModelBI;
import com.EMD.LSDB.bo.serviceinterface.OrderBI;
import com.EMD.LSDB.bo.serviceinterface.SpecTypeBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.form.SpecMaintenance.CreateSpecForm;
import com.EMD.LSDB.vo.common.CustomerVO;
import com.EMD.LSDB.vo.common.DistributorVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SpecTypeVo;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This Action class has the business methods for the Model
 *          *********************************************** ```
 ******************************************************************************/

public class CreateSpecAction extends EMDAction {
	
	/***************************************************************************
	 * This method is for loading the Create Spec
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
	
	public ActionForward initLoad(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil.logMessage("Entering CreateSpecAction.initLoad");
		String strForwardKey = ApplicationConstants.SUCCESS;
		CreateSpecForm objCreateSpecForm = (CreateSpecForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			ArrayList arlSpec = new ArrayList();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpec);	
			objCreateSpecForm.setSpecTypeList(arlSpec);
			
		}
		
		catch (Exception objExp) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***************************************************************************
	 * This method is for fetching the models,Distributor,Customer
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
	
	/**
	 * Models Should be loaded based the specification type,
	 * The association between model and customertype is removed 
	 * based on the requirement of LSDB_CR-56
	 * Modified on 25-Aug-08
	 * by ps57222
	 */
	
	public ActionForward populate(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil.logMessage("Entering CreateSpecAction.populate");
		String strForwardKey = ApplicationConstants.SUCCESS;
		CreateSpecForm objCreateSpecForm = (CreateSpecForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			ArrayList arlModel = new ArrayList();
			ArrayList arlCusList = new ArrayList();
			ArrayList arlDisList = new ArrayList();
			
			ModelVo objModelVo = new ModelVo();
			CustomerVO objCustomerVO = new CustomerVO();
			DistributorVO objDistributorVO = new DistributorVO();
			
			//Loading Spec types
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			ArrayList arlSpec = new ArrayList();
			
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpec);
			objCreateSpecForm.setSpecTypeList(arlSpec);
			
			LogUtil.logMessage("objCreateSpecForm.getSpecTypeNo():"
					+ objCreateSpecForm.getSpecTypeNo());
			LogUtil.logMessage("objCreateSpecForm.getModCustTypeSeqNo():"
					+ objCreateSpecForm.getModCustTypeSeqNo());
			
			/**
			 * The Customer drop down should be loaded based on the customer
			 * type seqno and Specificationtypeseqno, this change is applicable
			 * only if the Specifictaion type is Locomotive. If at all it's PM&I
			 * then the drop down should be loaded with domestic customers by
			 * default. (Requirement changed for LSDB_CR-46[PM&I] changed on
			 * 02-09-08 by si50968)
			 */
			//Added For CR_84
			objSpecTypeVo.setSpectypeSeqno(objCreateSpecForm.getSpecTypeNo());
			ArrayList arlSpecType = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpecType.size() != 0) {
				
				SpecTypeVo objjSpecTypeVo = (SpecTypeVo) arlSpecType.get(0);
				if(objjSpecTypeVo.isDistMaintDisPlayFlag())
					objCreateSpecForm.setDistributorFlag(ApplicationConstants.YES);
				else
					objCreateSpecForm.setDistributorFlag(ApplicationConstants.NO);
			}
			LogUtil.logMessage("objCreateSpecForm.getDistributorFlag(): "
					+ objCreateSpecForm.getDistributorFlag());
			
			//This is for Customer
			CustomerBI objCustomerBI = ServiceFactory.getCustomerBO();
			objCustomerVO.setSpecTypeSeqNo(objCreateSpecForm.getSpecTypeNo());
			
			if (objCreateSpecForm.getDistributorFlag().equalsIgnoreCase(ApplicationConstants.NO)){
			//if ("1".equals(String.valueOf(objCreateSpecForm.getSpecTypeNo()))) {
				objCustomerVO.setCustTypeSeqNo(objCreateSpecForm
						.getModCustTypeSeqNo());
			} else {
				objCustomerVO.setCustTypeSeqNo(1);
				
			}
			
			objCustomerVO.setUserID(objLoginVo.getUserID());
			arlCusList = objCustomerBI.fetchCustomers(objCustomerVO);
			LogUtil.logMessage("objArrList for Customer :" + arlCusList);
			objCreateSpecForm.setCustomerList(arlCusList);
			
			//This is for Model
			ModelBI objModelBI = ServiceFactory.getModelBO();
			objModelVo.setSpecTypeSeqNo(objCreateSpecForm.getSpecTypeNo());
			
			objModelVo.setUserID(objLoginVo.getUserID());
			arlModel = objModelBI.fetchModels(objModelVo);
			objCreateSpecForm.setModelList(arlModel);
			LogUtil.logMessage("objArrList for Model :" + arlModel);
			
			//Decide whether Distributor needs to be pouplated
			if (objCreateSpecForm.getDistributorFlag().equalsIgnoreCase(ApplicationConstants.YES)){
			//if ("2".equals(String.valueOf(objCreateSpecForm.getSpecTypeNo()))) {
				
				objDistributorVO.setUserID(objLoginVo.getUserID());
				DistributorBI objDistributorBI = ServiceFactory
				.getDistributorBO();
				arlDisList = objDistributorBI
				.fetchDistributors(objDistributorVO);
				LogUtil.logMessage("objArrList for Distributor :" + arlDisList);
				objCreateSpecForm.setDistributorList(arlDisList);
			}
			
			objCreateSpecForm.setOrderNo("");
			objCreateSpecForm.setSapCusCode("");
			
		} catch (Exception objEx) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForwardKey);
	}
	

	/***************************************************************************
	 * This method is for inserting a new Order
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.1 Exception Handling Issue Fix Done 21-Apr-09 by VV49326
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward insertOrder(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException, ApplicationException {
		
		LogUtil.logMessage("Entering CreateSpecAction.insertOrder");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int errorId = ApplicationConstants.ORDER_ID_EXIST;
		int intSuccess = 0;
		int specTypeSeqNo=0;
		String strUserID = null;
		ActionForward actionRedirect = null;

		CreateSpecForm objCreateSpecForm = (CreateSpecForm) objActionForm;
		
		// Added after Exception Handling Issue 21-Apr-09 VV49326
		String strRedirectFlag = "N";
		try {
			   HttpSession objSession = objHttpServletRequest.getSession(false);
			   ArrayList arlModel = new ArrayList();
			   ArrayList arlCusList = new ArrayList();
			   ArrayList arlDisList = new ArrayList();
			   ModelVo objModelVo = new ModelVo();
			   CustomerVO objCustomerVO = new CustomerVO();
			   DistributorVO objDistributorVO = new DistributorVO();
			   SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			   ArrayList arlSpecList = new ArrayList();
			   OrderVO objOrderVO = new OrderVO();
			   LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			   //Getting User from the session
			if (objLoginVo != null) {
			   strUserID = objLoginVo.getUserID();
			}
			   objSpecTypeVo.setUserID(strUserID);
			   SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			   arlSpecList = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			
			   LogUtil.logMessage("objArrList for Spec Types :" + arlSpecList);
			
			   objCreateSpecForm.setSpecTypeList(arlSpecList);
			
			   LogUtil.logMessage("objCreateSpecForm.getSpecTypeNo() :"
					+ objCreateSpecForm.getSpecTypeNo());
			   LogUtil.logMessage("objCreateSpecForm.getModCustTypeSeqNo() :"
					+ objCreateSpecForm.getModCustTypeSeqNo());
			   LogUtil.logMessage("objCreateSpecForm.getDistSeqNo() :"
					+ objCreateSpecForm.getDistSeqNo());
			   LogUtil.logMessage("objCreateSpecForm.getCusSeqNo() :"
					+ objCreateSpecForm.getCusSeqNo());
			   LogUtil.logMessage("objCreateSpecForm.getModelSeqNo() :"
					+ objCreateSpecForm.getModelSeqNo());
			   LogUtil.logMessage("objCreateSpecForm.getSapCusCode() :"
					+ objCreateSpecForm.getSapCusCode());
			   LogUtil.logMessage("objCreateSpecForm.getOrderNo() :"
					+ objCreateSpecForm.getOrderNo());
			   LogUtil.logMessage("objCreateSpecForm.getQuantity() :"
					+ objCreateSpecForm.getQuantity());
			
			   objOrderVO.setSpecTypeSeqNo(objCreateSpecForm.getSpecTypeNo());
			   specTypeSeqNo=objCreateSpecForm.getSpecTypeNo();
			//Added For CR_84
			if (objCreateSpecForm.getDistributorFlag().equalsIgnoreCase(ApplicationConstants.NO)){
			//if ("1".equals(String.valueOf(objCreateSpecForm.getSpecTypeNo()))) {
				objOrderVO.setModelCustTypeSeqNo(objCreateSpecForm.getModCustTypeSeqNo());
			} else {
				 objOrderVO.setModelCustTypeSeqNo(1);
			}
			    objOrderVO.setDistSeqNo(objCreateSpecForm.getDistSeqNo());
			    objOrderVO.setCusSeqNo(objCreateSpecForm.getCusSeqNo());
			    objOrderVO.setModelSeqNo(objCreateSpecForm.getModelSeqNo());
			    objOrderVO.setSapCusCode(ApplicationUtil.trim(objCreateSpecForm.getSapCusCode()));
			    objOrderVO.setOrderNo(ApplicationUtil.trim(objCreateSpecForm.getOrderNo()));
			    objOrderVO.setQuantity(Integer.parseInt(ApplicationUtil.trim(String.valueOf(objCreateSpecForm.getQuantity()))));
			    objOrderVO.setRevCode(ApplicationConstants.DEFAULT_REV_CODE);
			    objOrderVO.setUserID(strUserID);
			    OrderBI objOrderBI = ServiceFactory.getOrderBO();
			    intSuccess = objOrderBI.insertOrder(objOrderVO);
      
			//Added for redirecting to Model Info screen and 600 is the error id if at all order already exists
			
			if(intSuccess != errorId){

				// Added after Exception handling Issue 21-Apr-09 VV49326
				strRedirectFlag = "Y";

				actionRedirect = new ActionForward(
						"MainFeatureInfo.do?method=fetchComponentDesc"
								+ "&orderKeyId=" + intSuccess + "&specCode="
								+ specTypeSeqNo, true /* REDIRECT */);
			
			} else {

				// Added after Exception handling Issue 21-Apr-09 VV49326
				strRedirectFlag = "N";

				objCreateSpecForm.setMessageID("" + intSuccess);

				// Starts
				
				/**
				 * The Customer drop down should be loaded based on the customer
				 * type seqno and Specificationtypeseqno, this change is applicable
				 * only if the Specifictaion type is Locomotive. If at all it's PM&I
				 * then the drop down should be loaded with domestic customers by
				 * default. (Requirement changed for LSDB_CR-46[PM&I] changed on
				 * 02-09-08 by si50968)
				 */
				//This is for Customer
				    CustomerBI objCustomerBI = ServiceFactory.getCustomerBO();
				if (objCreateSpecForm.getDistributorFlag().equalsIgnoreCase(ApplicationConstants.NO))
				//if ("1".equals(String.valueOf(objCreateSpecForm.getSpecTypeNo())))
					objCustomerVO.setCustTypeSeqNo(objCreateSpecForm.getModCustTypeSeqNo());
				else
					objCustomerVO.setCustTypeSeqNo(1);
					objCustomerVO.setSpecTypeSeqNo(objCreateSpecForm.getSpecTypeNo());
				    objCustomerVO.setUserID(strUserID);
				    arlCusList = objCustomerBI.fetchCustomers(objCustomerVO);
				    LogUtil.logMessage("objArrList for Customer :" + arlCusList);
				    objCreateSpecForm.setCustomerList(arlCusList);
				
				    //This is for Model
				    ModelBI objModelBI = ServiceFactory.getModelBO();
				    objModelVo.setSpecTypeSeqNo(objCreateSpecForm.getSpecTypeNo());
				
				    objModelVo.setUserID(strUserID);
				    arlModel = objModelBI.fetchModels(objModelVo);
				    objCreateSpecForm.setModelList(arlModel);
				    LogUtil.logMessage("objArrList for Model :" + arlModel);
				
				    //Decide whether Distributor needs to be pouplated
			  if ("2".equals(String
						.valueOf(objCreateSpecForm.getSpecTypeNo()))) {
					
					objDistributorVO.setUserID(strUserID);
					DistributorBI objDistributorBI = ServiceFactory
					.getDistributorBO();
					arlDisList = objDistributorBI
					.fetchDistributors(objDistributorVO);
					LogUtil.logMessage("objArrList for Distributor :"
							+ arlDisList);
					objCreateSpecForm.setDistributorList(arlDisList);
					
				}
				
					LogUtil.logMessage("Inside the fecthtModel objModelVo :"+ arlModel);
			
		        } 
		}catch (Exception objEx) {
			
			     strForwardKey = ApplicationConstants.FAILURE;
			     ErrorInfo objErrorInfo = new ErrorInfo();
			     objErrorInfo.setMessage(objEx.getMessage() + "");
			     LogUtil.logError(objErrorInfo);
		      }
                //Added for redirecting to Model Info screen and 600 is the error id if at all order already exists 
		// Changed after Exception handlin Issue 21-Apr-09 VV49326
		if (strRedirectFlag.equalsIgnoreCase("Y")) {

			return actionRedirect;

		} else {
			     return objActionMapping.findForward(strForwardKey);
			  }
	}
	
	
	/***************************************************************************
	 * This method is for fetching the models based on the Specification type
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
	
	/**
	 * Methods populate,loadModels are Modified 
	 * based on the requirement of LSDB_CR-56
	 * Modified on 25-Aug-08
	 * by ps57222  
	 */
	
	public ActionForward loadModels(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil.logMessage("Entering CreateSpecAction.loadModels");
		String strForwardKey = ApplicationConstants.SUCCESS;
		CreateSpecForm objCreateSpecForm = (CreateSpecForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			ArrayList arlModel = new ArrayList();
			ArrayList arlCusList = new ArrayList();
			ArrayList arlDisList = new ArrayList();
			
			ModelVo objModelVo = new ModelVo();
			CustomerVO objCustomerVO = new CustomerVO();
			DistributorVO objDistributorVO = new DistributorVO();
			
			//Loading Spec types
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			ArrayList arlSpec = new ArrayList();
			
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpec);
			objCreateSpecForm.setSpecTypeList(arlSpec);
			
			LogUtil.logMessage("objCreateSpecForm.getSpecTypeNo():"
					+ objCreateSpecForm.getSpecTypeNo());
			LogUtil.logMessage("objCreateSpecForm.getModCustTypeSeqNo():"
					+ objCreateSpecForm.getModCustTypeSeqNo());
			
			//This is for Customer
			
			/**
			 * The Customer drop down should be loaded based on the customer
			 * type seqno and Specificationtypeseqno, this change is applicable
			 * only if the Specifictaion type is Locomotive. If at all it's PM&I
			 * then the drop down should be loaded with domestic customers by
			 * default. (Requirement changed for LSDB_CR-46[PM&I] changed on
			 * 02-09-08 by si50968)
			 */
			
			//Added For CR_84
			objSpecTypeVo.setSpectypeSeqno(objCreateSpecForm.getSpecTypeNo());
			ArrayList arlSpecType = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpecType.size() != 0) {
				
				SpecTypeVo objjSpecTypeVo = (SpecTypeVo) arlSpecType.get(0);
				if(objjSpecTypeVo.isDistMaintDisPlayFlag())
					objCreateSpecForm.setDistributorFlag(ApplicationConstants.YES);
				else
					objCreateSpecForm.setDistributorFlag(ApplicationConstants.NO);
			}
			LogUtil.logMessage("objCreateSpecForm.getDistributorFlag(): "
					+ objCreateSpecForm.getDistributorFlag());
			
			CustomerBI objCustomerBI = ServiceFactory.getCustomerBO();
			
			if (objCreateSpecForm.getDistributorFlag().equalsIgnoreCase(ApplicationConstants.YES)){
			//if ("2".equals(String.valueOf(objCreateSpecForm.getSpecTypeNo()))) {
				
				objCustomerVO.setCustTypeSeqNo(1);
				
				//objCustomerVO.setCustTypeSeqNo(objCreateSpecForm.getModCustTypeSeqNo());
				objCustomerVO.setSpecTypeSeqNo(objCreateSpecForm
						.getSpecTypeNo());
				objCustomerVO.setUserID(objLoginVo.getUserID());
				arlCusList = objCustomerBI.fetchCustomers(objCustomerVO);
				LogUtil.logMessage("objArrList for Customer :" + arlCusList);
				objCreateSpecForm.setCustomerList(arlCusList);
			}
			
			//This is for Model
			ModelBI objModelBI = ServiceFactory.getModelBO();
			objModelVo.setSpecTypeSeqNo(objCreateSpecForm.getSpecTypeNo());
			
			objModelVo.setUserID(objLoginVo.getUserID());
			arlModel = objModelBI.fetchModels(objModelVo);
			objCreateSpecForm.setModelList(arlModel);
			LogUtil.logMessage("objArrList for Model :" + arlModel);
			
			//Decide whether Distributor needs to be pouplated
			if (objCreateSpecForm.getDistributorFlag().equalsIgnoreCase(ApplicationConstants.YES)){
			//if ("2".equals(String.valueOf(objCreateSpecForm.getSpecTypeNo()))) {
				
				objDistributorVO.setUserID(objLoginVo.getUserID());
				DistributorBI objDistributorBI = ServiceFactory
				.getDistributorBO();
				arlDisList = objDistributorBI
				.fetchDistributors(objDistributorVO);
				LogUtil.logMessage("objArrList for Distributor :" + arlDisList);
				objCreateSpecForm.setDistributorList(arlDisList);
			}
			
			objCreateSpecForm.setOrderNo("");
			objCreateSpecForm.setSapCusCode("");
			
		} catch (Exception objEx) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForwardKey);
	}
}