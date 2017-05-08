package com.EMD.LSDB.action.SpecMaintenance;

import java.io.FileReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.EMD.LSDB.action.common.EMDAction;
import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.CustomerBI;
import com.EMD.LSDB.bo.serviceinterface.DistributorBI;
import com.EMD.LSDB.bo.serviceinterface.ModelBI;
import com.EMD.LSDB.bo.serviceinterface.OrderBI;
import com.EMD.LSDB.bo.serviceinterface.OrderClauseBI;
import com.EMD.LSDB.bo.serviceinterface.SpecTypeBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.constant.DatabaseConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.form.SpecMaintenance.OrderSpecificClauseForm;
import com.EMD.LSDB.vo.common.ChangeRequest1058VO;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.CustomerVO;
import com.EMD.LSDB.vo.common.DistributorVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.SpecTypeVo;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This Action class has the business methods for Order Specific Clause Report
 * created at CR_108
 ******************************************************************************/

public class OrderSpecificClauseAction extends EMDAction {
	
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
		
		LogUtil.logMessage("Entering Order Specific Clause Action & method =  initLoad");
		String strForward = ApplicationConstants.SUCCESS;
		OrderSpecificClauseForm objOrderSpecificClauseForm = (OrderSpecificClauseForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			
			ArrayList arlSpecList = new ArrayList();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			// To Fetch Specification Types
			arlSpecList = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			
			if (arlSpecList != null && arlSpecList.size() > 0) {
				objOrderSpecificClauseForm.setSpecTypeList(arlSpecList);
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
		
		LogUtil.logMessage("Entering OrderSpecificClauseAction.fetchModels");
		String strForward = ApplicationConstants.SUCCESS;
		OrderSpecificClauseForm objOrderSpecificClauseForm = (OrderSpecificClauseForm) objActionForm;
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
				objOrderSpecificClauseForm.setSpecTypeList(arlSpecList);
			}
			
			// To fetch Models
			objModelVo.setSpecTypeSeqNo(objOrderSpecificClauseForm.getSpectypeSeqno());
			objModelVo.setUserID(strUserID);
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null && arlModelList.size() > 0) {
				objOrderSpecificClauseForm.setModelList(arlModelList);
			}
			
			if (objOrderSpecificClauseForm.getSpectypeSeqno() != -1) {
				CustomerVO objCustomerVO = new CustomerVO();
				objCustomerVO.setUserID(objLoginVo.getUserID());
				objCustomerVO.setSpecTypeSeqNo(objOrderSpecificClauseForm.getSpectypeSeqno());
				CustomerBI objCustomerBO = ServiceFactory.getCustomerBO();
				arlCustomerList = objCustomerBO.fetchCustomers(objCustomerVO);
				
				if (arlCustomerList != null && arlCustomerList.size() > 0) {
					objOrderSpecificClauseForm.setCustList(arlCustomerList);
					
				}
			}
			/* added */
			objOrderSpecificClauseForm.setHdnSelSpecType(objOrderSpecificClauseForm.getHdnSelSpecType());
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
		
		LogUtil.logMessage("Entering OrderSpecificClauseAction.fetchOrders");
		String strForward = ApplicationConstants.SUCCESS;
		OrderSpecificClauseForm objOrderSpecificClauseForm = (OrderSpecificClauseForm) objActionForm;
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
				objOrderSpecificClauseForm.setSpecTypeList(arlSpecList);
			}
			
			// To fetch Models
			objModelVo.setSpecTypeSeqNo(objOrderSpecificClauseForm.getSpectypeSeqno());
			objModelVo.setUserID(strUserID);
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null && arlModelList.size() > 0) {
				objOrderSpecificClauseForm.setModelList(arlModelList);
			}
			
			objDistVo.setUserID(strUserID);
			// To fetch Distributors
			arlDistList = objDistBo.fetchDistributors(objDistVo);
			
			if (arlDistList != null && arlDistList.size() > 0) {
				objOrderSpecificClauseForm.setDistList(arlDistList);
			}
			

			objSpecTypeVo.setSpectypeSeqno(objOrderSpecificClauseForm.getSpectypeSeqno());
			ArrayList arlSpecType = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpecType.size() != 0) {
				
				SpecTypeVo objjSpecTypeVo = (SpecTypeVo) arlSpecType.get(0);
				if(objjSpecTypeVo.isDistMaintDisPlayFlag())
					objOrderSpecificClauseForm.setDistributorFlag(ApplicationConstants.YES);
				else
					objOrderSpecificClauseForm.setDistributorFlag(ApplicationConstants.NO);
			}
			LogUtil.logMessage("objOrderSpecificClauseForm.getDistributorFlag(): "
					+ objOrderSpecificClauseForm.getDistributorFlag());
			/**
			 * The Customer drop down should be loaded based on the customer
			 * type seqno and Specificationtypeseqno, this change is applicable
			 * only if the Specifictaion type is Locomotive. If at all it's PM&I
			 * then the drop down should be loaded with domestic customers by
			 * default. (Requirement changed for LSDB_CR-46[PM&I] changed on
			 * 02-09-08 by si50968)
			 */
			if (objOrderSpecificClauseForm.getDistributorFlag().equalsIgnoreCase(ApplicationConstants.YES)){
			//if ("2".equals(String.valueOf(objOrderSpecificClauseForm.getSpectypeSeqno()))) {
				objCustVo.setCustTypeSeqNo(1);
				objCustVo.setUserID(strUserID);
				objCustVo.setSpecTypeSeqNo(objOrderSpecificClauseForm.getSpectypeSeqno());
				// To fetch Customers
				arlCustList = objCustBo.fetchCustomers(objCustVo);
			}
			else if (objOrderSpecificClauseForm.getDistributorFlag().equalsIgnoreCase(ApplicationConstants.NO)){
			//else if ("1".equals(String.valueOf(objOrderSpecificClauseForm.getSpectypeSeqno()))) {
				//objCustVo.setCustTypeSeqNo(objOrderSpecificClauseForm.getCustTypeSeqNo());
				objCustVo.setUserID(strUserID);
				objCustVo.setSpecTypeSeqNo(objOrderSpecificClauseForm.getSpectypeSeqno());
				// To fetch Customers
				arlCustList = objCustBo.fetchCustomers(objCustVo);
				
			}
			
			if (arlCustList != null && arlCustList.size() > 0) {
				objOrderSpecificClauseForm.setCustList(arlCustList);
				objOrderSpecificClauseForm.setCustomerSeqNO(objOrderSpecificClauseForm.getCustomerSeqNO());
				/*Added to populate customers in the lower drop down*/
				if (objOrderSpecificClauseForm.getDistributorFlag().equalsIgnoreCase(ApplicationConstants.YES)){
				//if ("2".equals(String.valueOf(objOrderSpecificClauseForm.getSpectypeSeqno()))) {
					objOrderSpecificClauseForm.setCustomerList(arlCustList);
				}
				
			}
			objOrderSpecificClauseForm.setHdnSelectedCustomers(objOrderSpecificClauseForm.getHdnSelectedCustomers());
			objOrderSpecificClauseForm.setHdnPreSelectedCustomer(objOrderSpecificClauseForm.getCustomerSeqNO());
			objOrderVo.setCusSeqNo(objOrderSpecificClauseForm.getCustomerSeqNO());
			
			/* Added */
			objOrderSpecificClauseForm.setHdPreSelectedModel(objOrderSpecificClauseForm.getModelSeqNo());
			// To fetch Orders
			objOrderVo.setSpecTypeSeqNo(objOrderSpecificClauseForm.getSpectypeSeqno());
			objOrderVo.setModelSeqNo(objOrderSpecificClauseForm.getModelSeqNo());
			objOrderVo.setOrderNo(ApplicationUtil.trim(objOrderSpecificClauseForm.getOrderNo()));
			objOrderSpecificClauseForm.setHdnOrderNo((ApplicationUtil.trim(objOrderSpecificClauseForm.getOrderNo())));
			
			objOrderVo.setDataLocTypeCode(DatabaseConstants.DATALOCATION_SNAP_SHOT);
			objOrderVo.setUserID(strUserID);
			
			if(objOrderSpecificClauseForm.isShowLatestFlag() )
			{
				objOrderVo.setShowLatestFlag("Y");	
				objOrderSpecificClauseForm.setHdnLatestFlag("Yes");
			}
			else
			{
				objOrderVo.setShowLatestFlag("N");
				objOrderSpecificClauseForm.setHdnLatestFlag("No");
			}
			
			arlOrderList = objOrderBo.fetchOrders(objOrderVo);
			
			if (arlOrderList != null && arlOrderList.size() > 0) {
				objOrderSpecificClauseForm.setOrderList(arlOrderList);
			}
			
			objOrderSpecificClauseForm.setHdnSelModel(objOrderSpecificClauseForm.getHdnSelModel());
			objOrderSpecificClauseForm.setHdnSelSpecType(objOrderSpecificClauseForm.getHdnSelSpecType());
			
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
		
		LogUtil.logMessage("Entering OrderSpecificClauseAction.loadCustomers");
		String strForward = ApplicationConstants.SUCCESS;
		OrderSpecificClauseForm objOrderSpecificClauseForm = (OrderSpecificClauseForm) objActionForm;
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
				objOrderSpecificClauseForm.setSpecTypeList(arlSpecList);
			}
			
			// To fetch Models
			objModelVo.setSpecTypeSeqNo(objOrderSpecificClauseForm.getSpectypeSeqno());
			objModelVo.setUserID(strUserID);
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null && arlModelList.size() > 0) {
				objOrderSpecificClauseForm.setModelList(arlModelList);
			}
			
			objDistVo.setUserID(strUserID);
			// To fetch Distributors
			arlDistList = objDistBo.fetchDistributors(objDistVo);
			
			if (arlDistList != null && arlDistList.size() > 0) {
				objOrderSpecificClauseForm.setDistList(arlDistList);
			}
			
			/**
			 * The Customer drop down should be loaded based on the customer
			 * type seqno and Specificationtypeseqno, this change is applicable
			 * only if the Specifictaion type is Locomotive. If at all it's PM&I
			 * then the drop down should be loaded with domestic customers by
			 * default. (Requirement changed for LSDB_CR-46[PM&I] changed on
			 * 02-09-08 by si50968)
			 */
			
			objCustVo.setSpecTypeSeqNo(objOrderSpecificClauseForm.getSpectypeSeqno());
			if (objOrderSpecificClauseForm.getDistributorFlag().equalsIgnoreCase(ApplicationConstants.NO)){
			//if ("1".equals(String.valueOf(objOrderSpecificClauseForm.getSpectypeSeqno()))) {
				objCustVo.setSpecTypeSeqNo(objOrderSpecificClauseForm.getSpectypeSeqno());
				objCustVo.setUserID(strUserID);
				// To fetch Customers
				arlCustList = objCustBo.fetchCustomers(objCustVo);
			}else if (objOrderSpecificClauseForm.getDistributorFlag().equalsIgnoreCase(ApplicationConstants.YES)){
			//else if ("2".equals(String.valueOf(objOrderSpecificClauseForm.getSpectypeSeqno()))) {
				objCustVo.setCustTypeSeqNo(1);
				objCustVo.setUserID(strUserID);
				objCustVo.setSpecTypeSeqNo(objOrderSpecificClauseForm.getSpectypeSeqno());
				// To fetch Customers
				arlCustList = objCustBo.fetchCustomers(objCustVo);
			}
			
			if (arlCustList != null && arlCustList.size() > 0) {
				objOrderSpecificClauseForm.setCustList(arlCustList);
				objOrderSpecificClauseForm.setCustomerSeqNO(objOrderSpecificClauseForm.getCustomerSeqNO());
				
			}
			
			//To fetch Custmers for below drop down
			objCustVo.setCustTypeSeqNo(objOrderSpecificClauseForm.getCustTypeSeqNo());
			objCustVo.setUserID(strUserID);
			objCustVo.setSpecTypeSeqNo(objOrderSpecificClauseForm.getSpectypeSeqno());
			// To fetch Customers
			arlCustList = objCustBo.fetchCustomers(objCustVo);
			
			if (arlCustList != null && arlCustList.size() > 0) {
				objOrderSpecificClauseForm.setCustomerList(arlCustList);
				objOrderSpecificClauseForm.setCustomerSeqNO(objOrderSpecificClauseForm.getCustomerSeqNO());
				objOrderSpecificClauseForm.setHdnSelectedCustomers(objOrderSpecificClauseForm.getHdnSelectedCustomers());
				objOrderSpecificClauseForm.setHdnPreSelectedCustomer(objOrderSpecificClauseForm.getHdnPreSelectedCustomer());
			}
			
			objOrderSpecificClauseForm.setHdPreSelectedModel(objOrderSpecificClauseForm.getModelSeqNo());
			// To fetch Orders
			objOrderVo.setCusSeqNo(objOrderSpecificClauseForm.getCustomerSeqNO());
			objOrderVo.setSpecTypeSeqNo(objOrderSpecificClauseForm.getSpectypeSeqno());
			objOrderVo.setModelSeqNo(objOrderSpecificClauseForm.getModelSeqNo());
			objOrderVo.setOrderNo(ApplicationUtil.trim(objOrderSpecificClauseForm.getOrderNo()));
			objOrderVo.setDataLocTypeCode(DatabaseConstants.DATALOCATION_SNAP_SHOT);
			objOrderVo.setUserID(strUserID);
			
			arlOrderList = objOrderBo.fetchOrders(objOrderVo);
			
			if (arlOrderList != null && arlOrderList.size() > 0) {
				objOrderSpecificClauseForm.setOrderList(arlOrderList);
			}
			
			objOrderSpecificClauseForm.setHdnSelModel(objOrderSpecificClauseForm.getHdnSelModel());
			objOrderSpecificClauseForm.setHdnSelSpecType(objOrderSpecificClauseForm.getHdnSelSpecType());
			
		} catch (Exception objExp) {
			
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
	}
	/***************************************************************************
	 * This Method is used to generate report
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward viewReport(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		LogUtil.logMessage("Inside the OrderSpecificClauseAction : VIEW REPORT");
		OrderSpecificClauseForm objOrderSpecificClauseForm = (OrderSpecificClauseForm) objActionForm;
		
		ArrayList arlClauseList = new ArrayList();
		String strForward = ApplicationConstants.POP_UP;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		String strUserID = "";
		
		try{
			LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
			if (objLoginVo != null) 
			{
				strUserID = objLoginVo.getUserID();
			}
			//strOrdrKeys = objHttpServletRequest.getParameterValues("orderKey");
			String strOrdrKeys = objHttpServletRequest.getParameter(ApplicationConstants.ORDER_KEYS);
			String[] orders = strOrdrKeys.split(ApplicationConstants.COMMA);
			String strSelSpecType = objHttpServletRequest.getParameter("specType");
			String strSelModel=objHttpServletRequest.getParameter("Model");
			objOrderSpecificClauseForm.setHdnSelSpecType(strSelSpecType);
			objOrderSpecificClauseForm.setHdnSelModel(strSelModel);
			String selectedOrderNumber = objHttpServletRequest.getParameter(ApplicationConstants.ORDER_NUMBERS);
			String[] orderNumbers =  selectedOrderNumber.split(ApplicationConstants.COMMA);
			//objOrderSpecificClauseForm.setOrderNumbers(orderNumbers);
			int orderSize = orders.length;
			LogUtil.logMessage("Length of the orderKeys = "+orderSize);
			OrderVO objOrderVO = new OrderVO();
			objOrderVO.setOrderKeys(orders);
			
			int orderNumsize = orderNumbers.length;
			String previousOrder = "";
			String selOrderNos = "";
			LogUtil.logMessage("Length of the Selected Orders = "+orderNumsize);
			for (int count = 0; count < orderNumsize; count++) {	
				if (!previousOrder.equalsIgnoreCase(orderNumbers[count])){
					previousOrder=orderNumbers[count];
					if (selOrderNos.equalsIgnoreCase(""))
						selOrderNos=previousOrder;
					else
						selOrderNos=selOrderNos+", "+previousOrder;
				}
			}
			
			objOrderSpecificClauseForm.setOrderNumberResult(selOrderNos);
			
			OrderClauseBI objOrderClauseBI = ServiceFactory.getOrderClauseBO();
			arlClauseList = objOrderClauseBI.fetchOrdrSpecificClauses(objOrderVO);
			
			
			if (arlClauseList != null && arlClauseList.size() > 0) {
				objOrderSpecificClauseForm.setArlClauseList(arlClauseList);
				//LogUtil.logMessage("The values of OrderKeys are ***===*** "+objOrderSpecificClauseForm.getOrdrKeyList());	
			}
			}
		
		catch (Exception objExp) {
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
			}
		return objActionMapping.findForward(strForward);
	}
	
	
	/***************************************************************************
	 * This Method is used to generate report
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward viewReportExcel(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		LogUtil.logMessage("Inside the OrderSpecificClauseAction : VIEW REPORT IN EXCEL");
		OrderSpecificClauseForm objOrderSpecificClauseForm = (OrderSpecificClauseForm) objActionForm;
		
		ArrayList arlClauseList = new ArrayList();
		String strForward = ApplicationConstants.POP_UP_FOR_EXCEL;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		String strUserID = "";
		int i=0;
			
		try{
			LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
			if (objLoginVo != null) 
			{
				strUserID = objLoginVo.getUserID();
			}
			String strOrdrKeys = objHttpServletRequest.getParameter(ApplicationConstants.ORDER_KEYS);
			String[] orders = strOrdrKeys.split(ApplicationConstants.COMMA);
			int orderSize = orders.length;
			LogUtil.logMessage("Length of the orderKeys = "+orderSize);
			OrderVO objOrderVO = new OrderVO();
			objOrderVO.setOrderKeys(orders);
			
			String strSelSpecType = objHttpServletRequest.getParameter("specType");
			String strSelModel=objHttpServletRequest.getParameter("Model");
			objOrderSpecificClauseForm.setHdnSelSpecType(strSelSpecType);
			objOrderSpecificClauseForm.setHdnSelModel(strSelModel);
			String selectedOrderNumber = objHttpServletRequest.getParameter(ApplicationConstants.ORDER_NUMBERS);
			String[] orderNumbers =  selectedOrderNumber.split(ApplicationConstants.COMMA);
			//objOrderSpecificClauseForm.setOrderNumbers(orderNumbers);
			
			int orderNumsize = orderNumbers.length;
			String previousOrder = "";
			String selOrderNos = "";
			//LogUtil.logMessage("Length of the Selected Orders = "+orderNumsize);
			for (int count = 0; count < orderNumsize; count++) {	
				if (!previousOrder.equalsIgnoreCase(orderNumbers[count])){
					previousOrder=orderNumbers[count];
					if (selOrderNos.equalsIgnoreCase(""))
						selOrderNos=previousOrder;
					else
						selOrderNos=selOrderNos+", "+previousOrder;
				}
			}
			
			objOrderSpecificClauseForm.setOrderNumberResult(selOrderNos);
			
			//LogUtil.logMessage("The Selected order are numbers = " + objOrderSpecificClauseForm.getOrderNumberResult());
			
			OrderClauseBI objOrderClauseBI = ServiceFactory.getOrderClauseBO();
			arlClauseList = objOrderClauseBI.fetchOrdrSpecificClauses(objOrderVO);
			
			
			if (arlClauseList != null && arlClauseList.size() > 0) {
				objOrderSpecificClauseForm.setArlClauseList(arlClauseList);
				}
			}
		
		catch (Exception objExp) {
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
			}
		return objActionMapping.findForward(strForward);
	}
	
	//Added for CR-127
	/***************************************************************************
	 * This Method is used to generate report
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward exportOrderSpecificClausesReport(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		LogUtil.logMessage("Inside the OrderSpecificClauseAction : VIEW REPORT IN EXCEL");
		OrderSpecificClauseForm objOrderSpecificClauseForm = (OrderSpecificClauseForm) objActionForm;
		
		ClauseVO objClauseVO = new ClauseVO();
		
		String strForwardKey = ApplicationConstants.SUCCESS;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		int rowCount = 0;
		int intPos = 0;
		String strUserID = "";
		int i=0;
		String strClauseDesc= null;
		String strOrderClauseDelFlag = null;
			
		try{
			LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
			if (objLoginVo != null) 
			{
				strUserID = objLoginVo.getUserID();
			}
			String strOrdrKeys = objHttpServletRequest.getParameter(ApplicationConstants.ORDER_KEYS);
			String[] orders = strOrdrKeys.split(ApplicationConstants.COMMA);
			int orderSize = orders.length;
			LogUtil.logMessage("Length of the orderKeys = "+orderSize);
			OrderVO objOrderVO = new OrderVO();
			objOrderVO.setOrderKeys(orders);
			
			String strSelSpecType = objHttpServletRequest.getParameter("specType");
			String strSelModel=objHttpServletRequest.getParameter("Model");
			objOrderSpecificClauseForm.setHdnSelSpecType(strSelSpecType);
			objOrderSpecificClauseForm.setHdnSelModel(strSelModel);
			String selectedOrderNumber = objHttpServletRequest.getParameter(ApplicationConstants.ORDER_NUMBERS);
			String[] orderNumbers =  selectedOrderNumber.split(ApplicationConstants.COMMA);
			//objOrderSpecificClauseForm.setOrderNumbers(orderNumbers);
			
			int orderNumsize = orderNumbers.length;
			String previousOrder = "";
			String selOrderNos = "";
			//LogUtil.logMessage("Length of the Selected Orders = "+orderNumsize);
			for (int count = 0; count < orderNumsize; count++) {	
				if (!previousOrder.equalsIgnoreCase(orderNumbers[count])){
					previousOrder=orderNumbers[count];
					if (selOrderNos.equalsIgnoreCase(""))
						selOrderNos=previousOrder;
					else
						selOrderNos=selOrderNos+", "+previousOrder;
				}
			}
			objOrderSpecificClauseForm.setOrderNumberResult(selOrderNos);
			
			//LogUtil.logMessage("The Selected order are numbers = " + objOrderSpecificClauseForm.getOrderNumberResult());
			
//			 create workbook
			HSSFWorkbook workBook = new HSSFWorkbook();
			HSSFSheet sheet = workBook.createSheet("Order Specific Clauses Report");
					
			HSSFCellStyle cellSuggestionStyle = workBook.createCellStyle();
			
			HSSFFont secFont = workBook.createFont();
			secFont.setFontName(HSSFFont.FONT_ARIAL);
			secFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			secFont.setColor(HSSFColor.BLACK.index);		
			secFont.setFontHeightInPoints((short) 10);
			
			cellSuggestionStyle.setFont(secFont);
	        cellSuggestionStyle.setFillForegroundColor(HSSFColor.WHITE.index);
	        cellSuggestionStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);			
			
			HSSFCellStyle cellOtherStyle = workBook.createCellStyle();
			
			HSSFFont subSecFont = workBook.createFont();			
			subSecFont.setFontName(HSSFFont.FONT_ARIAL);
			subSecFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			subSecFont.setColor(HSSFColor.BLACK.index);		
			subSecFont.setFontHeightInPoints((short) 10);
			
			cellOtherStyle.setFont(subSecFont);
			cellOtherStyle.setFillForegroundColor(HSSFColor.WHITE.index);
			cellOtherStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);	
			
			HSSFFont otherFont = workBook.createFont();			
			otherFont.setFontName(HSSFFont.FONT_ARIAL);
			otherFont.setColor(HSSFColor.BLACK.index);		
			otherFont.setFontHeightInPoints((short) 10);
			
			cellOtherStyle.setFont(otherFont);
			cellOtherStyle.setWrapText(true);
			cellOtherStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellOtherStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
			HSSFCellStyle cellClauseStyle = workBook.createCellStyle();

			cellClauseStyle.setFont(otherFont);
			cellClauseStyle.setWrapText(true);
			cellClauseStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
			HSSFCellStyle cellTabHeadStyle = workBook.createCellStyle();										
			
			HSSFFont claTabFont = workBook.createFont();			
			claTabFont.setFontName(HSSFFont.FONT_ARIAL);
			claTabFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			claTabFont.setColor(HSSFColor.BLACK.index);		
			claTabFont.setFontHeightInPoints((short) 10);
			
			cellTabHeadStyle.setFont(claTabFont);
			cellTabHeadStyle.setWrapText(true);
			cellTabHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellTabHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
			
			ArrayList arlClauseList = new ArrayList();
			
			OrderClauseBI objOrderClauseBI = ServiceFactory.getOrderClauseBO();
			arlClauseList = objOrderClauseBI.fetchOrdrSpecificClauses(objOrderVO);
			
			
			Iterator listClauseList = null;
			HSSFRow row = sheet.createRow(rowCount);
			if (arlClauseList.size() != 0) {
				
				createHeadingForOrderClauseSpecificReport(workBook, row, sheet,strSelSpecType,strSelModel,selOrderNos);
				listClauseList = arlClauseList.iterator();
				rowCount = row.getRowNum()+ 6;
				
				while (listClauseList.hasNext()) {

					objClauseVO = new ClauseVO();
					objClauseVO = (ClauseVO) listClauseList.next();
					//LogUtil.logMessage(" row.getRowNum() :" +  row.getRowNum());	
						rowCount++;
						intPos = 0;
						row = sheet.createRow(rowCount);
						String strEnggData = "";
						strOrderClauseDelFlag = objClauseVO.getClauseDelFlag();
						LogUtil.logMessage("strOrderClauseDelFlag: " + strOrderClauseDelFlag);
						if(strOrderClauseDelFlag !=null && strOrderClauseDelFlag.equalsIgnoreCase("Y")){
							createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 0,new HSSFRichTextString(objClauseVO.getOrderNo()));
							createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 1,new HSSFRichTextString(objClauseVO.getStatus()));
							createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 2,new HSSFRichTextString(objClauseVO.getClauseNum()));
							createCell(row,cellClauseStyle,HSSFCell.CELL_TYPE_STRING, 3,new HSSFRichTextString("RESERVED"));//2nd column
							createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 4,new HSSFRichTextString(""));//4th column
							createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 5,new HSSFRichTextString(""));//5th column
							createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 6,new HSSFRichTextString(""));//6th column
							createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 7,new HSSFRichTextString(""));//3rd column
							sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),3,7));
//							Engineering Data
							LogUtil.logMessage("Engineering Data Starts :");
								ArrayList arlEDLNO = objClauseVO.getEdlNO();
								if (arlEDLNO.size() > 0) {
									for (int n = 0; n < arlEDLNO.size(); n++) {
										strEnggData = strEnggData +"EDL "+ arlEDLNO.get(n).toString()+ "\n";
										}
									}
								ArrayList arlRefEDLNO = objClauseVO.getRefEDLNO();
								if (arlRefEDLNO.size() > 0) {
									for (int n = 0; n < arlRefEDLNO.size(); n++) {
										strEnggData = strEnggData +"(ref EDL "+ arlRefEDLNO.get(n).toString()+")"+"\n";
									}
								}
								
								ArrayList arlPartOF = objClauseVO.getPartOF();
								if (arlPartOF.size() > 0) {
									for (int n = 0; n < arlPartOF.size(); n++) {
										strEnggData = strEnggData +"Part of "+ arlPartOF.get(n).toString()+ "\n";
									}
								}
								
								if(objClauseVO.getDwONumber() != 0){
									strEnggData = strEnggData + "Dwo No " + objClauseVO.getDwONumber()+ "\n";
								}
								if(objClauseVO.getPartNumber() != 0){
									strEnggData = strEnggData + "Part No " + objClauseVO.getPartNumber()+ "\n";
								}
								if(objClauseVO.getPriceBookNumber() != 0){
									strEnggData = strEnggData + "Price Book No " + objClauseVO.getPriceBookNumber() + "\n";
								}
								if(objClauseVO.getStandardEquipmentDesc() == null){
									objClauseVO.setStandardEquipmentDesc("");
								}else{
									strEnggData = strEnggData + objClauseVO.getStandardEquipmentDesc() + "\n";
								}
								if(objClauseVO.getComments() == null){
									objClauseVO.setComments("");
								}else{
									strEnggData = strEnggData + objClauseVO.getComments();
								}
								createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING,8,new HSSFRichTextString(strEnggData));
								LogUtil.logMessage("Engineering Data End :");
						}else{		
				//sheet.addMergedRegion(new CellRangeAddress(row,rowspan-1,0,new HSSFRichTextString(objChangeRequest1058VO.getOrderNo())));					
				createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 0,new HSSFRichTextString(objClauseVO.getOrderNo()));
				createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 1,new HSSFRichTextString(objClauseVO.getStatus()));
				createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 2,new HSSFRichTextString(objClauseVO.getClauseNum()));
				if(objClauseVO.getClauseDesc()!=null && objClauseVO.getClauseDesc()!=""){
					strClauseDesc = getRefinedClauseDesc(objClauseVO.getClauseDesc());
				}else{
					strClauseDesc = objClauseVO.getClauseDesc();
				}
				
				strClauseDesc = strClauseDesc.trim();
				
				HSSFRichTextString richTextClaDesc = new HSSFRichTextString(strClauseDesc); 
				
				ArrayList arlTabData = new ArrayList();
				Integer intTabColCnt = new Integer(0);							
				int tabColCount = 1;
				if (objClauseVO.getTableArrayData1() != null)	{
					arlTabData = getTableDataColumnsCount(objClauseVO.getTableArrayData1());

					if (arlTabData != null && arlTabData.size() > 0) {
						intTabColCnt = (Integer) arlTabData.get(0);
					}

					if (intTabColCnt.intValue() == 0) {
						tabColCount = 1;
					} else {
						tabColCount = intTabColCnt.intValue();
					}							
				}
				
				ArrayList arlTableRows = (ArrayList) objClauseVO.getTableArrayData1();
				LogUtil.logMessage("arlTableRows :" + arlTableRows.size());	

				createClauseDescCell(row,cellClauseStyle ,HSSFCell.CELL_TYPE_STRING, 3,richTextClaDesc);//3rd column
				sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),3,7));
				
				//Engineering Data
				LogUtil.logMessage("Engineering Data Starts :");
					ArrayList arlEDLNO = objClauseVO.getEdlNO();
					if (arlEDLNO.size() > 0) {
						for (int n = 0; n < arlEDLNO.size(); n++) {
							strEnggData = strEnggData +"EDL "+ arlEDLNO.get(n).toString()+ "\n";
							}
						}
					ArrayList arlRefEDLNO = objClauseVO.getRefEDLNO();
					if (arlRefEDLNO.size() > 0) {
						for (int n = 0; n < arlRefEDLNO.size(); n++) {
							strEnggData = strEnggData +"(ref EDL "+ arlRefEDLNO.get(n).toString()+")"+"\n";
						}
					}
					
					ArrayList arlPartOF = objClauseVO.getPartOF();
					if (arlPartOF.size() > 0) {
						for (int n = 0; n < arlPartOF.size(); n++) {
							strEnggData = strEnggData +"Part of "+ arlPartOF.get(n).toString()+ "\n";
						}
					}
					
					if(objClauseVO.getDwONumber() != 0){
						strEnggData = strEnggData + "Dwo No " + objClauseVO.getDwONumber()+ "\n";
					}
					if(objClauseVO.getPartNumber() != 0){
						strEnggData = strEnggData + "Part No " + objClauseVO.getPartNumber()+ "\n";
					}
					if(objClauseVO.getPriceBookNumber() != 0){
						strEnggData = strEnggData + "Price Book No " + objClauseVO.getPriceBookNumber() + "\n";
					}
					if(objClauseVO.getStandardEquipmentDesc() == null){
						objClauseVO.setStandardEquipmentDesc("");
					}else{
						strEnggData = strEnggData + objClauseVO.getStandardEquipmentDesc() + "\n";
					}
					if(objClauseVO.getComments() == null){
						objClauseVO.setComments("");
					}else{
						strEnggData = strEnggData + objClauseVO.getComments();
					}
					createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING,8,new HSSFRichTextString(strEnggData));
					LogUtil.logMessage("Engineering Data End :");

				if (arlTableRows != null && arlTableRows.size() > 0) {
					LogUtil.logMessage("Inside  table data :");
					LogUtil.logMessage("arlTableRows.size():"+arlTableRows.size());
				for (int k= 0; k < arlTableRows.size(); k++) {
					rowCount++;
					row = sheet.createRow(rowCount);
					int colCount = 3;
					
					ArrayList arlTableColumns = (ArrayList) arlTableRows.get(k);
					if (k==0)
						createCell(row,cellTabHeadStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(0)));
					else
						createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(0)));
					
					if (k==0)
						createCell(row,cellTabHeadStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(1)));
					else
						createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(1)));

					if (k==0)
						createCell(row,cellTabHeadStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(2)));
					else
						createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(2)));

					if (k==0)
						createCell(row,cellTabHeadStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(3)));
					else
						createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(3)));
					
					if (k==0)
						createCell(row,cellTabHeadStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(4)));
					else
						createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(4)));

					LogUtil.logMessage("colCount1 :" + colCount);
					}
				}
				
			}
				
			}
				
			}
			
			
			LogUtil.logMessage("end :");
		    objHttpServletResponse.setContentType("application/vnd.ms-excel");
			objHttpServletResponse.setHeader("Content-disposition", "attachment; filename= OrderSpecificClausesReport.xls");
			OutputStream out = null;
			out = objHttpServletResponse.getOutputStream();
			workBook.write(out);
			
		}catch (Exception objExp) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
			}
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		if (strForwardKey.equalsIgnoreCase(ApplicationConstants.FAILURE))
			return objActionMapping.findForward(strForwardKey);
		else
			return null;
	}
	
	public void createHeadingForOrderClauseSpecificReport(HSSFWorkbook workBook, HSSFRow row,
			HSSFSheet sheet,String specType,String model,String OrderNos) throws Exception {

	HSSFCellStyle cellHeadStyle = workBook.createCellStyle();

	HSSFFont headFont = workBook.createFont();
			
	headFont.setFontName(HSSFFont.FONT_ARIAL);
	headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	headFont.setColor(HSSFColor.BLACK.index);	
	headFont.setFontHeightInPoints((short) 11);

	cellHeadStyle.setWrapText(true);
	cellHeadStyle.setFont(headFont);
	cellHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	cellHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	
	int rowCount = 0;
	createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString("View Order Specific Clauses Report"));
	sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,8));
	rowCount++;
	row = sheet.createRow(rowCount);
	LogUtil.logMessage(" row.getRowNum() :" +  row.getRowNum());
	createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString(""));
	sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,8));
	rowCount++;
	row = sheet.createRow(rowCount);
	createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString("All the Clauses below are only added to selected Order."));
	sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,8));
	rowCount++;
	row = sheet.createRow(rowCount);
	createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString(""));
	sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,8));
	rowCount++;
	row = sheet.createRow(rowCount);
	createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString("Specification Type :"+specType+"        Model : "+model+"        Order Number(s) : "+OrderNos));
	sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,8));
	rowCount++;
	row = sheet.createRow(rowCount);
	createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString(""));
	sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,8));
	rowCount++;
	row = sheet.createRow(rowCount);
	 
		final String[] columnHeadings = {"Order Number", "Spec Status", "Clause No","Clause Description", "Engineering Data"};
		final int[] columnWidth = {3500,6000,3500,6000,6300};
		for (int intPos=0;intPos < 5; intPos++)
			{
			LogUtil.logMessage("columnHeadings[intPos]:"+columnHeadings[intPos]);
			if(columnHeadings[intPos] == "Engineering Data"){
			 createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,8,new HSSFRichTextString(columnHeadings[intPos]));
			 sheet.setColumnWidth(8,6300);
			}else if(columnHeadings[intPos] == "Clause Description") {
			  createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,intPos,new HSSFRichTextString(columnHeadings[intPos]));	
			  sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),3,7));
			}else{
			 createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,intPos,new HSSFRichTextString(columnHeadings[intPos]));
			 sheet.setColumnWidth(intPos,columnWidth[intPos]);
			} 
			 
				 
			 }
	}
	
	/***************************************************************************
	 * This method is used to create the cell with int value
	 * 
	 * @author Mahindra Satyam Ltd
	 * @version 1.0
	 * @param HSSFSheet, HSSFRow, HSSFCellStyle, int, int, int
	 * @return HSSFCell
	 **************************************************************************/
	
	protected HSSFCell createCell(HSSFRow row, HSSFCellStyle headerStyle,
			int cellType, int position, int cellValue) {
		//This function is used to create a cell with int value
		HSSFCell cell = null;
		cell = row.createCell(position);
		cell.setCellStyle(headerStyle);
		cell.setCellType(cellType);
		cell.setCellValue(cellValue);
		return cell;
	}
	
	/***************************************************************************
	 * This method is used to create the cell with String value
	 * 
	 * @author Mahindra Satyam Ltd
	 * @version 1.0
	 * @param HSSFSheet, HSSFRow, HSSFCellStyle, int, int, String
	 * @return HSSFCell
	 **************************************************************************/
	
	protected HSSFCell createCell(HSSFRow row, HSSFCellStyle headerStyle,
			int cellType, int colPosition, HSSFRichTextString cellValue) {
		//This function is used to create a cell with String value
		HSSFCell cell = null;
		cell = row.createCell(colPosition);
		cell.setCellStyle(headerStyle);
		cell.setCellType(cellType);
		cell.setCellValue(cellValue);
		return cell;
	}
	
	/***************************************************************************
	 * This method is used to get the Clause Description using HTML Parser
	 * 
	 * @author Mahindra Satyam Ltd
	 * @version 1.0
	 * @param String
	 * @return String
	 * @throws Exception
	 **************************************************************************/
	
	public String getRefinedClauseDesc(String strClauseDesc) throws Exception
	{
		String strRefinedClauseDesc = "";
		Paragraph paraClauseDesc = new Paragraph();
	    paraClauseDesc.setKeepTogether(true);
		if (!strClauseDesc.startsWith("<p>"))
			strClauseDesc = strClauseDesc.replaceAll("\n","<br/>");
		strRefinedClauseDesc=ApplicationUtil.strConvertToHTMLFormat(strClauseDesc);
		StyleSheet styles = new StyleSheet();
	    styles.loadTagStyle("p","size","12px");
	    styles.loadTagStyle("p","face","Times");
	    styles.loadTagStyle("strong","font-weight", "bold"); 
	    styles.loadTagStyle("em","font-style", "italic");
		ArrayList p = HTMLWorker.parseToList(new FileReader(strRefinedClauseDesc), styles);
	    for (int k1 = 0; k1 < p.size(); ++k1) 
			{
	    	paraClauseDesc.add((Element) p.get(k1));
		    }
	    strRefinedClauseDesc = paraClauseDesc.getContent();
		LogUtil.logMessage("returning from strhtmlclauseconvert :");
		return strRefinedClauseDesc;
	}
	
	private static ArrayList getTableDataColumnsCount(ArrayList arlTableCol)
	throws Exception {
		
		LogUtil.logMessage("Inside getTableDataColumnsCount");
		int colCount = 0;
		ArrayList arlWholeData = new ArrayList();
		ArrayList arlOut = new ArrayList();
		int intCnt = 0;
		try {
			
			for (int x = 0; x < arlTableCol.size(); x++) {
				colCount = 0;
				ArrayList arlData = (ArrayList) arlTableCol.get(x);
				
				for (int i = 0; i < arlData.size(); i++) {
					
					if (arlData.get(i) != null && !("".equals(arlData.get(i)))) {
						
						colCount = i;
						//colCount = colCount + 1;
						
					}
					
				}
				if (intCnt < colCount) {
					intCnt = colCount;
				}
				
			}
			
			arlOut.add(new Integer(intCnt+1));
			LogUtil.logMessage("Col Cnt" + new Integer(intCnt + 1));
			arlOut.add(arlWholeData);
			
		} catch (Exception objExp) {
			
			throw objExp;
			
		} finally {
			
			try {
				//closeSQLObjects(rsTableCol, null, null);
			} catch (Exception objExp) {
				
				throw objExp;
			}
		}
		
		return arlOut;
	}
	
	/***************************************************************************
	 * This method is used to create the cell with String value
	 * 
	 * @author Mahindra Satyam Ltd
	 * @version 1.0
	 * @param HSSFSheet, HSSFRow, HSSFCellStyle, int, int, String
	 * @return HSSFCell
	 **************************************************************************/
	
	protected HSSFCell createClauseDescCell(HSSFRow row, HSSFCellStyle headerStyle,
			int cellType, int colPosition, HSSFRichTextString cellValue) {
		//This function is used to create a cell with String value
		HSSFCell cell = null;
		cell = row.createCell(colPosition);
		cell.setCellStyle(headerStyle);
		cell.setCellType(cellType);
		cell.setCellValue(cellValue);
		return cell;
	}
	
	//Added for CR-127 ends here
}