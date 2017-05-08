package com.EMD.LSDB.action.SpecComparison;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This Action class is used to fetch orders and displays the
 *          clause comparison details in pop up page and excel.
 ******************************************************************************/

import java.io.FileReader;
import java.io.OutputStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.hssf.util.HSSFColor;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.EMD.LSDB.action.common.EMDAction;
import com.EMD.LSDB.bo.SpecComparison.ClauseCompareBO;
import com.EMD.LSDB.bo.SpecMaintenance.OrderBO;
import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.ModelBI;
import com.EMD.LSDB.bo.serviceinterface.OrderSectionBI;
import com.EMD.LSDB.bo.serviceinterface.SpecTypeBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.form.SpecComparison.ClauseCompareForm;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SpecTypeVo;
import com.EMD.LSDB.vo.common.SubSectionVO;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;

public class ClauseCompareAction extends EMDAction {
	
	/***************************************************************************
	 * This Method is used to load all the specification type present in the
	 * database
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
		
		LogUtil.logMessage("Entering ClauseCompareAction.initLoad");
		String strForward = ApplicationConstants.SUCCESS;
		ClauseCompareForm objClauseComparsionForm = (ClauseCompareForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		try {
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			/** SpecTypeBI instance is obtained. */
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			
			ArrayList arlSpecList = new ArrayList();
			/** To Fetch Specification Types */
			arlSpecList = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			
			if (arlSpecList != null && arlSpecList.size() > 0) {
				objClauseComparsionForm.setSpecTypeList(arlSpecList);
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
		
		LogUtil.logMessage("Entering ClauseCompareAction.fetchModels");
		String strForward = ApplicationConstants.SUCCESS;
		ClauseCompareForm objClauseCompareForm = (ClauseCompareForm) objActionForm;
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
				objClauseCompareForm.setSpecTypeList(arlSpecList);
			}
			
			/** To fetch Models for a specification type. */
			objModelVo
			.setSpecTypeSeqNo(objClauseCompareForm.getSpectypeSeqno());
			objModelVo.setUserID(objLoginVo.getUserID());
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null && arlModelList.size() > 0) {
				objClauseCompareForm.setModelList(arlModelList);
			}
			
			objClauseCompareForm.setHdnSelSpecType(objClauseCompareForm
					.getHdnSelSpecType());
		} catch (Exception objExp) {
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
		
	}
	
	/***************************************************************************
	 * This Method is used to fetch all the orders matching the search criteria
	 * specified.
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward fetchOrdersForSpecComparison(
			ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil
		.logMessage("Entering ClauseCompareAction.fetchOrdersForSpecComparison");
		String strForward = ApplicationConstants.SUCCESS;
		ClauseCompareForm objClauseCompareForm = (ClauseCompareForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			ModelVo objModelVo = new ModelVo();
			ModelBI objModelBo = ServiceFactory.getModelBO();
			
			ArrayList arlSpecList = new ArrayList();
			ArrayList arlModelList = new ArrayList();
			String strUserID = ApplicationConstants.EMPTY_STRING;
			
			/** Getting User from the session */
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			/** To Fetch Specification Types */
			objSpecTypeVo.setUserID(strUserID);
			arlSpecList = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpecList != null && arlSpecList.size() > 0) {
				objClauseCompareForm.setSpecTypeList(arlSpecList);
			}
			
			/** To fetch Models for a sepcification type. */
			objModelVo
			.setSpecTypeSeqNo(objClauseCompareForm.getSpectypeSeqno());
			objModelVo.setUserID(strUserID);
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null && arlModelList.size() > 0) {
				objClauseCompareForm.setModelList(arlModelList);
			}
			
			objClauseCompareForm.setHdnSelSpecType(objClauseCompareForm
					.getHdnSelSpecType());
			
			/** Search parameters for the orders are set into the OrderVO. */
			OrderVO objOrderVo = new OrderVO();
			objOrderVo.setUserID(strUserID);
			objOrderVo.setSpecTypeSeqNo(Integer.parseInt(objClauseCompareForm
					.getHdnSelSpecType()));
			objOrderVo.setOrderNo(objClauseCompareForm.getHdnorderNo().trim());
			objOrderVo.setDataLocTypeCode(ApplicationConstants.EMPTY_STRING);
			objOrderVo.setModelSelected(objClauseCompareForm.getModelSeqNos());
//			Change for LSDb_CR-87
			objOrderVo.setSortByFlag(objClauseCompareForm.getSortByFlag());
			//Added for CR_104
			if(objClauseCompareForm.isClauseShowLatFlag())
			{objOrderVo.setShowLatestFlag("Y");	
			objClauseCompareForm.setClauseShowLatONOFF("Yes");
			}
			else
			{objOrderVo.setShowLatestFlag("N");
			objClauseCompareForm.setClauseShowLatONOFF("No");
			}
			//CR_104 Ends here
			
			/** Instance of OrderBO is obtained. */
			OrderBO objOrderBo = OrderBO.getInstance();
			
			/**
			 * The orders matching the search criteria are fetched from the
			 * database.
			 */
			ArrayList orderList = objOrderBo
			.fetchOrdersForSpecComparison(objOrderVo);
//			Change for LSDb_CR-87
			if (orderList != null && orderList.size() > 0) {
				objClauseCompareForm.setOrderList(orderList);
				
				objClauseCompareForm.setSortByFlag(objClauseCompareForm.getSortByFlag());
			}
			
			
			
			objClauseCompareForm.setOrderList(orderList);
			
		} catch (Exception objExp) {
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
		
	}
	
	/***************************************************************************
	 * This Method is used to section details of the selected order numbers.
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward fetchOrdersForClauseComparison(
			ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		ArrayList sectionList = new ArrayList();
		
		ClauseCompareForm objClauseCompareForm = (ClauseCompareForm) objActionForm;
		ArrayList selectedList = new ArrayList();
		ArrayList selectedOrderList = new ArrayList();
		LogUtil
		.logMessage("Entering ClauseCompareAction.fetchOrdersForClauseComparison");
		String strForward = ApplicationConstants.POP_UP;
		String strUserID = ApplicationConstants.EMPTY_STRING;
		
		/** Getting User from the session */
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		if (objLoginVo != null) {
			strUserID = objLoginVo.getUserID();
		}
		try {
			
			/**
			 * The selected order information are fetched from the request
			 * parameters
			 */
			String orderKeys = objHttpServletRequest
			.getParameter(ApplicationConstants.ORDER_KEYS);
			String sectionIds = objHttpServletRequest
			.getParameter(ApplicationConstants.SECTION_IDS);
			String selectedCustomerNames = objHttpServletRequest
			.getParameter(ApplicationConstants.CUST_NAME);
			String selectedModelNames = objHttpServletRequest
			.getParameter(ApplicationConstants.SELECTED_MODELS);
			String selectedSpecType = objHttpServletRequest
			.getParameter(ApplicationConstants.SELECTED_SPEC_TYPE);
			String selectedOrderNumber = objHttpServletRequest
			.getParameter(ApplicationConstants.ORDER_NUMBERS);
			String selectedSection = objHttpServletRequest
			.getParameter(ApplicationConstants.SELECTED_SECTION);
			String[] orders = orderKeys.split(ApplicationConstants.COMMA);
			String[] sections = sectionIds.split(ApplicationConstants.COMMA);
			String[] orderNumbers = selectedOrderNumber
			.split(ApplicationConstants.COMMA);
			String[] customerNamesParam = selectedCustomerNames
			.split(ApplicationConstants.COMMA);
			//Added for production issue for customername Fix
			String customerNames[] = new String[customerNamesParam.length];
			//LogUtil.logMessage("selectedCustomerNames:" +selectedCustomerNames);
			for (int index =0; index < customerNamesParam.length; index++){
				//LogUtil.logMessage("customerNames[index] Before replace:" +customerNamesParam[index]);
				customerNames[index] = (customerNamesParam[index]).toString().replace('^',',');
				//LogUtil.logMessage("customerNames[index]: After replace " +customerNames[index]);
			}
	     	//Added for production issue for customername Fix Ends Here
			String[] specType = selectedSpecType
			.split(ApplicationConstants.COMMA);
			String[] modelNames = selectedModelNames
			.split(ApplicationConstants.COMMA);
			int orderSize = orders.length;
			String[] sectionNames = selectedSection
			.split(ApplicationConstants.COMMA);
           
			/** The fetched search criteria are set into the OrderVO. */
			for (int count = 0; count < orderSize; count++) {
				OrderVO objOrder = new OrderVO();
				objOrder.setOrderNo(orderNumbers[count]);
				objOrder.setCustomerName(customerNames[count]);
				objOrder.setSpecTypeName(specType[count]);
				objOrder.setModelName(modelNames[count]);
				objOrder.setOrderKey(Integer.parseInt(orders[count]));
				
				objOrder.setSelectedSectionSeqNo(Integer
						.parseInt(sections[count]));
				objOrder.setUserID(strUserID);
				objOrder
				.setDataLocTypeCode(ApplicationConstants.DATA_lOCATION_TYPE);
				selectedOrderList.add(objOrder);
			}
			
			/** The fetched search criteria are set into the SubSectionVO. */
			for (int count = 0; count < orderSize; count++) {
				
				SectionVO objSectionVo = new SectionVO();
				ArrayList arlSectionList = new ArrayList();
				objSectionVo.setModelName(modelNames[count]);
				objSectionVo.setOrderKey(Integer.parseInt(orders[count]));
				objSectionVo.setSectionSeqNo(Integer.parseInt(sections[count]));
				objSectionVo.setUserID(strUserID);
				 /*Added for CR 75 for AllSections by cm68219*/
				OrderSectionBI objOrderSectionBo = ServiceFactory
				.getOrderSectionBO();
				objSectionVo
				.setDataLocationType(ApplicationConstants.DATA_lOCATION_TYPE);
				  /*Checking whether user selected "Allsection" or individual section by cm68219*/ 
				if(objSectionVo.getSectionSeqNo() > 0){
					arlSectionList= new ArrayList();
					arlSectionList.add(objSectionVo);
				}else{
					/*Added to bring AllSectons for OrderKey by cm68219*/
					arlSectionList = objOrderSectionBo.fetchOrdSections(objSectionVo);
				}
				   /*Passing ArrayList*/
				selectedList.add(arlSectionList);
			}
					
			objClauseCompareForm.setSelectedOrderList(selectedOrderList);
			
			/** ClauseCompareBO instance is obtained. */
			ClauseCompareBO objClauseCompareBo = ClauseCompareBO.getInstance();
			
			/** The complete section details for the selected orders are fetched */
			sectionList = objClauseCompareBo.fetchSectionsAndSubSections(
					selectedList, sectionNames);
			objClauseCompareForm.setSelectedSection(selectedSection);
			objClauseCompareForm.setCompareOrderList(sectionList);
			objClauseCompareForm.setOrderListSize(orderSize);
			
		} catch (Exception objExp) {
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
		
	}
	
	/***************************************************************************
	 * This Method is used to section details of the selected order numbers.
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward fnCompareClauseComparisonForExcel(
			ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		ArrayList sectionList = new ArrayList();
		
		ClauseCompareForm objClauseCompareForm = (ClauseCompareForm) objActionForm;
		ArrayList selectedList = new ArrayList();
		ArrayList selectedOrderList = new ArrayList();
		LogUtil
		.logMessage("Entering ClauseCompareAction.fetchOrdersForClauseComparisonForExcel");
		String strForward = ApplicationConstants.POP_UP_FOR_EXCEL;
		String strUserID = ApplicationConstants.EMPTY_STRING;
		
		/** Getting User from the session */
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		if (objLoginVo != null) {
			strUserID = objLoginVo.getUserID();
		}
		try {
			/**
			 * The selected order information are fetched from the request
			 * parameters
			 */
			String orderKeys = objHttpServletRequest
			.getParameter(ApplicationConstants.ORDER_KEYS);
			String sectionIds = objHttpServletRequest
			.getParameter(ApplicationConstants.SECTION_IDS);
			String selectedCustomerNames = objHttpServletRequest
			.getParameter(ApplicationConstants.CUST_NAME);
			String selectedModelNames = objHttpServletRequest
			.getParameter(ApplicationConstants.SELECTED_MODELS);
			String selectedSpecType = objHttpServletRequest
			.getParameter(ApplicationConstants.SELECTED_SPEC_TYPE);
			String selectedOrderNumber = objHttpServletRequest
			.getParameter(ApplicationConstants.ORDER_NUMBERS);
			String selectedSection = objHttpServletRequest
			.getParameter(ApplicationConstants.SELECTED_SECTION);
			String[] orders = orderKeys.split(ApplicationConstants.COMMA);
			String[] sections = sectionIds.split(ApplicationConstants.COMMA);
			String[] orderNumbers = selectedOrderNumber
			.split(ApplicationConstants.COMMA);
			String[] customerNamesParam = selectedCustomerNames
			.split(ApplicationConstants.COMMA);
			//Added for production issue for customername Fix
			String customerNames[] = new String[customerNamesParam.length];
			//LogUtil.logMessage("selectedCustomerNames:" +selectedCustomerNames);
			for (int index =0; index < customerNamesParam.length; index++){
				//LogUtil.logMessage("customerNames[index] Before replace:" +customerNamesParam[index]);
				customerNames[index] = (customerNamesParam[index]).toString().replace('^',',');
				//LogUtil.logMessage("customerNames[index]: After replace " +customerNames[index]);
			}
	     	//Added for production issue for customername Fix Ends Here
			String[] specType = selectedSpecType
			.split(ApplicationConstants.COMMA);
			String[] modelNames = selectedModelNames
			.split(ApplicationConstants.COMMA);
			String[] sectionNames = selectedSection
			.split(ApplicationConstants.COMMA);
			int orderSize = orders.length;
			
			/** The fetched search criteria are set into the OrderVO. */
			for (int count = 0; count < orderSize; count++) {
				OrderVO objOrder = new OrderVO();
				objOrder.setOrderNo(orderNumbers[count]);
				objOrder.setCustomerName(customerNames[count]);
				objOrder.setSpecTypeName(specType[count]);
				objOrder.setModelName(modelNames[count]);
				objOrder.setOrderKey(Integer.parseInt(orders[count]));
				
				objOrder.setSelectedSectionSeqNo(Integer
						.parseInt(sections[count]));
				objOrder.setUserID(strUserID);
				objOrder
				.setDataLocTypeCode(ApplicationConstants.DATA_lOCATION_TYPE);
				selectedOrderList.add(objOrder);
			}
			
			/** The fetched search criteria are set into the SubSectionVO. */
			for (int count = 0; count < orderSize; count++) {
				
				SectionVO objSectionVo = new SectionVO();
				ArrayList arlSectionList = new ArrayList();
				objSectionVo.setModelName(modelNames[count]);
				objSectionVo.setOrderKey(Integer.parseInt(orders[count]));
				objSectionVo.setSectionSeqNo(Integer.parseInt(sections[count]));
				objSectionVo.setUserID(strUserID);
				   /*Added for CR 75 for AllSections by cm68219*/
				OrderSectionBI objOrderSectionBo = ServiceFactory
				.getOrderSectionBO();
				objSectionVo
				.setDataLocationType(ApplicationConstants.DATA_lOCATION_TYPE);
				  /*Checking whether user selected "Allsection" or individual section by cm68219*/ 
				if(objSectionVo.getSectionSeqNo() > 0){
					arlSectionList= new ArrayList();
					arlSectionList.add(objSectionVo);
				}else{
					/*Added to bring AllSectons for OrderKey by cm68219*/
					arlSectionList = objOrderSectionBo.fetchOrdSections(objSectionVo);
				}
				   /*Passing ArrayList*/
				selectedList.add(arlSectionList);
			}
					
			objClauseCompareForm.setSelectedOrderList(selectedOrderList);
			
			/** ClauseCompareBO instance is obtained. */
			ClauseCompareBO objClauseCompareBo = ClauseCompareBO.getInstance();
			
			/** The complete section details for the selected orders are fetched */
			sectionList = objClauseCompareBo.fetchSectionsAndSubSections(
					selectedList, sectionNames);
			objClauseCompareForm.setSelectedSection(selectedSection);
			objClauseCompareForm.setCompareOrderList(sectionList);
			objClauseCompareForm.setOrderListSize(orderSize);
			
		} catch (Exception objExp) {
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
		
	}
	
	//Added for CR-128
	/***************************************************************************
	 * This Method is used to section details of the selected order numbers.
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward exportClauseComparisonForExcel(
			ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil
		.logMessage("Entering ClauseCompareAction.exportClauseComparisonForExcel");
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList sectionList = new ArrayList();
		
		ClauseCompareForm objClauseCompareForm = (ClauseCompareForm) objActionForm;
		ArrayList selectedList = new ArrayList();
		ArrayList subsecList = new ArrayList();
		ArrayList selectedOrderList = new ArrayList();
		ArrayList clauseList = new ArrayList();
		
		String strUserID = ApplicationConstants.EMPTY_STRING;
		String strClauseDelFlag = null;
		String strClauseDesc = null;
		/** Getting User from the session */
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		if (objLoginVo != null) {
			strUserID = objLoginVo.getUserID();
		}
		
		try {
			/**
			 * The selected order information are fetched from the request
			 * parameters
			 */
			String orderKeys = objHttpServletRequest
			.getParameter(ApplicationConstants.ORDER_KEYS);
			String sectionIds = objHttpServletRequest
			.getParameter(ApplicationConstants.SECTION_IDS);
			String selectedCustomerNames = objHttpServletRequest
			.getParameter(ApplicationConstants.CUST_NAME);
			String selectedModelNames = objHttpServletRequest
			.getParameter(ApplicationConstants.SELECTED_MODELS);
			String selectedSpecType = objHttpServletRequest
			.getParameter(ApplicationConstants.SELECTED_SPEC_TYPE);
			String selectedOrderNumber = objHttpServletRequest
			.getParameter(ApplicationConstants.ORDER_NUMBERS);
			String selectedSection = objHttpServletRequest
			.getParameter(ApplicationConstants.SELECTED_SECTION);
			String[] orders = orderKeys.split(ApplicationConstants.COMMA);
			String[] sections = sectionIds.split(ApplicationConstants.COMMA);
			String[] orderNumbers = selectedOrderNumber
			.split(ApplicationConstants.COMMA);
			String[] customerNamesParam = selectedCustomerNames
			.split(ApplicationConstants.COMMA);
			//Added for production issue for customername Fix
			String customerNames[] = new String[customerNamesParam.length];
			//LogUtil.logMessage("selectedCustomerNames:" +selectedCustomerNames);
			for (int index =0; index < customerNamesParam.length; index++){
				//LogUtil.logMessage("customerNames[index] Before replace:" +customerNamesParam[index]);
				customerNames[index] = (customerNamesParam[index]).toString().replace('^',',');
				//LogUtil.logMessage("customerNames[index]: After replace " +customerNames[index]);
			}
	     	//Added for production issue for customername Fix Ends Here
			String[] specType = selectedSpecType
			.split(ApplicationConstants.COMMA);
			String[] modelNames = selectedModelNames
			.split(ApplicationConstants.COMMA);
			String[] sectionNames = selectedSection
			.split(ApplicationConstants.COMMA);
			int orderSize = orders.length;
			
			/** The fetched search criteria are set into the OrderVO. */
			for (int count = 0; count < orderSize; count++) {
				OrderVO objOrder = new OrderVO();
				objOrder.setOrderNo(orderNumbers[count]);
				objOrder.setCustomerName(customerNames[count]);
				objOrder.setSpecTypeName(specType[count]);
				objOrder.setModelName(modelNames[count]);
				objOrder.setOrderKey(Integer.parseInt(orders[count]));
				
				objOrder.setSelectedSectionSeqNo(Integer
						.parseInt(sections[count]));
				objOrder.setUserID(strUserID);
				objOrder
				.setDataLocTypeCode(ApplicationConstants.DATA_lOCATION_TYPE);
				selectedOrderList.add(objOrder);
			}
			
			/** The fetched search criteria are set into the SubSectionVO. */
			for (int count = 0; count < orderSize; count++) {
				
				SectionVO objSectionVo = new SectionVO();
				ArrayList arlSectionList = new ArrayList();
				objSectionVo.setModelName(modelNames[count]);
				objSectionVo.setOrderKey(Integer.parseInt(orders[count]));
				objSectionVo.setSectionSeqNo(Integer.parseInt(sections[count]));
				objSectionVo.setUserID(strUserID);
				   /*Added for CR 75 for AllSections by cm68219*/
				OrderSectionBI objOrderSectionBo = ServiceFactory
				.getOrderSectionBO();
				objSectionVo
				.setDataLocationType(ApplicationConstants.DATA_lOCATION_TYPE);
				  /*Checking whether user selected "Allsection" or individual section by cm68219*/ 
				if(objSectionVo.getSectionSeqNo() > 0){
					arlSectionList= new ArrayList();
					arlSectionList.add(objSectionVo);
				}else{
					/*Added to bring AllSectons for OrderKey by cm68219*/
					arlSectionList = objOrderSectionBo.fetchOrdSections(objSectionVo);
				}
				   /*Passing ArrayList*/
				selectedList.add(arlSectionList);
			}
					
			objClauseCompareForm.setSelectedOrderList(selectedOrderList);
			
			/** ClauseCompareBO instance is obtained. */
			ClauseCompareBO objClauseCompareBo = ClauseCompareBO.getInstance();
			
			/** The complete section details for the selected orders are fetched */
			sectionList = objClauseCompareBo.fetchSectionsAndSubSections(
					selectedList, sectionNames);
			
			Iterator selectedOrderListIterator	= null;
			Iterator sectionListIterator 		= null;
			Iterator subSectionListIterator 	= null;
			Iterator clauseListIterator			= null;
			
			OrderVO objOrderVO = new OrderVO();
			SubSectionVO objSubSecVO = new SubSectionVO();
			ClauseVO objClauseVO = new ClauseVO();
			
			//create workbook
			HSSFWorkbook workBook = new HSSFWorkbook();
			HSSFSheet sheet = workBook.createSheet("Clause Comparison Report");
			
			HSSFCellStyle cellHeadStyle    = workBook.createCellStyle();
			HSSFCellStyle cellNormalStyle  = workBook.createCellStyle();
			HSSFCellStyle cellSectionStyle = workBook.createCellStyle();
			
			HSSFFont headFont = workBook.createFont();
			
			headFont.setFontName(HSSFFont.FONT_ARIAL);
			headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			headFont.setColor(HSSFColor.BLACK.index);	
			headFont.setFontHeightInPoints((short) 10);

			cellHeadStyle.setWrapText(true);
			cellHeadStyle.setFont(headFont);
			cellHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
			cellSectionStyle.setWrapText(true);
			cellSectionStyle.setFont(headFont);
			cellSectionStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellSectionStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
			HSSFPalette palette = ((HSSFWorkbook)workBook).getCustomPalette(); 
			palette.setColorAtIndex((short)57, (byte)205, (byte)201, (byte)201); 
			cellSectionStyle.setFillForegroundColor(palette.getColor(57).getIndex()); 
			cellSectionStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			
			HSSFFont normalFont = workBook.createFont();
			
			normalFont.setFontName(HSSFFont.FONT_ARIAL);
			normalFont.setColor(HSSFColor.BLACK.index);	
			normalFont.setFontHeightInPoints((short) 10);

			cellNormalStyle.setWrapText(true);
			cellNormalStyle.setFont(normalFont);
			cellNormalStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellNormalStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
			HSSFCellStyle cellClauseStyle = workBook.createCellStyle();
			
			HSSFFont otherFont = workBook.createFont();			
			otherFont.setFontName(HSSFFont.FONT_ARIAL);
			otherFont.setColor(HSSFColor.BLACK.index);		
			otherFont.setFontHeightInPoints((short) 10);
			
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

			int nSelectedOrderList = selectedOrderList.size();
			
			int nrowCount = 0;
			int nrowCountMax = 0;//Max Row Count Fix
			int nMaintainRowCount = 0;
			int ncolCount = 0;
			HSSFRow row = sheet.createRow(nrowCount);
			LogUtil.logMessage("orderKeys:" +orderKeys);
			LogUtil.logMessage("selectedOrderList.size():" +  selectedOrderList.size());
			createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString("Clause Comparison Report"));
			if(nSelectedOrderList == 2){
				sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,13));
			}else{
				sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,20));
			}
			nrowCount++;
			final String[] columnHeadings = {"Clause Number","Clause Description","Engineering Data"};
			final int[] columnWidth = {3000,8300,6000};
			int nheadColCount = 0;
			int nclauseColCount = 0;
			int nTblDataSize = 0;
			HashMap objChkClaNumber = new HashMap();
			if (selectedOrderList.size() != 0) {
				selectedOrderListIterator = selectedOrderList.iterator();
				int nFirstOrderCnt = 0;
				while (selectedOrderListIterator.hasNext()) {
					objOrderVO = new OrderVO();
					objOrderVO = (OrderVO) selectedOrderListIterator.next();
					if(nFirstOrderCnt == 0){
						sheet.setColumnWidth(ncolCount,6300);
						row = sheet.createRow(nrowCount);
					}else{
						row = sheet.getRow(nrowCount);
					}	
					createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,ncolCount,new HSSFRichTextString("Order Number"));
					ncolCount++;
					createCell(row,cellNormalStyle,HSSFCell.CELL_TYPE_STRING,ncolCount,new HSSFRichTextString(objOrderVO.getOrderNo()));
					sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),ncolCount,ncolCount+5));
					nrowCount++;
					LogUtil.logMessage(" inside :nrowCount" +  nrowCount);
					if(nFirstOrderCnt == 0)
						row = sheet.createRow(nrowCount);
					else
						row = sheet.getRow(nrowCount);
					ncolCount--; 
					createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,ncolCount,new HSSFRichTextString("Model"));
					ncolCount++;
					createCell(row,cellNormalStyle,HSSFCell.CELL_TYPE_STRING,ncolCount,new HSSFRichTextString(objOrderVO.getModelName()));
					sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),ncolCount,ncolCount+5));
					nrowCount++;
					LogUtil.logMessage(" inside :nrowCount" +  nrowCount);
					if(nFirstOrderCnt == 0)
						row = sheet.createRow(nrowCount);
					else
						row = sheet.getRow(nrowCount);
					ncolCount--; 
					createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,ncolCount,new HSSFRichTextString("Customer Name"));
					ncolCount++; 
					createCell(row,cellNormalStyle,HSSFCell.CELL_TYPE_STRING,ncolCount,new HSSFRichTextString(objOrderVO.getCustomerName()));
					sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),ncolCount,ncolCount+5));
					nrowCount++;
					LogUtil.logMessage(" inside :nrowCount" +  nrowCount);
					if(nFirstOrderCnt == 0)
						row = sheet.createRow(nrowCount);
					else
						row = sheet.getRow(nrowCount);
					ncolCount--; 
					createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,ncolCount,new HSSFRichTextString("Spec Status"));
					ncolCount++; 
					createCell(row,cellNormalStyle,HSSFCell.CELL_TYPE_STRING,ncolCount,new HSSFRichTextString(objOrderVO.getSpecTypeName()));
					sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),ncolCount,ncolCount+5));
					nrowCount++;
					LogUtil.logMessage(" inside :nrowCount" +  nrowCount);
					nMaintainRowCount = nrowCount;
					nrowCount = nrowCount - 4;
					ncolCount = ncolCount + 6;

					nFirstOrderCnt++;
					sheet.setColumnWidth(ncolCount,6300);
				}
				nrowCount = nMaintainRowCount;
				LogUtil.logMessage(" inside :nrowCount" +  nrowCount);
				LogUtil.logMessage(" sectionList.size():" +  sectionList.size());
				row = sheet.createRow(nrowCount);
				createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString(""));
				if(nSelectedOrderList == 2){
					sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,13));
				}else{
					sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,20));
				}
				nrowCount++;
				ncolCount = 0;
				outerloop:
				if (sectionList.size() != 0) {
					sectionListIterator = sectionList.iterator();
					int nSecCount = 0;		
					int nSecRow = 0;

					while (sectionListIterator.hasNext()) {
						subsecList = new ArrayList();
						subsecList = (ArrayList)sectionListIterator.next();
						LogUtil.logMessage("subsecList.size():"+subsecList.size());
						subSectionListIterator = subsecList.iterator();
						while(subSectionListIterator.hasNext())
						{								
							objSubSecVO = new SubSectionVO();
							objSubSecVO = (SubSectionVO) subSectionListIterator.next();
							
							LogUtil.logMessage("objSubSecVO.getSecCode():" +  objSubSecVO.getSecCode());
							LogUtil.logMessage("objSubSecVO.getSubSecCode():" +  objSubSecVO.getSubSecCode());
							
							//if (objSubSecVO.getSecName() != null && objSubSecVO.getSubSecName() != null){								
							
							if(nSecCount % nSelectedOrderList == 0 || nSecCount == 0){ 					
								row = sheet.createRow(nrowCount);
							}else{
								row = sheet.getRow(nrowCount);
							}
							LogUtil.logMessage("nSecCount:" +  nSecCount);
								
							nSecRow = nrowCount; //For use to reset the rowcount value to this section rownumber
							sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),ncolCount,ncolCount+6));
							if(objSubSecVO.getSecName() != "" || objSubSecVO.getSecName() != null )	{
								nrowCount++;
								if(nSecCount % nSelectedOrderList == 0 || nSecCount == 0){
									row = sheet.createRow(nrowCount);
								} else {
									row = sheet.getRow(nrowCount);
								}
								createCell(row,cellSectionStyle,HSSFCell.CELL_TYPE_STRING,ncolCount,new HSSFRichTextString(objSubSecVO.getSecName()));
							}else{
								createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,ncolCount,new HSSFRichTextString(""));
							}
							sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),ncolCount,ncolCount+6));
							LogUtil.logMessage("objSubSecVO.getSecName():" +  objSubSecVO.getSecName());
							nrowCount++;
							
							if(nSecCount % nSelectedOrderList == 0 || nSecCount == 0){
								row = sheet.createRow(nrowCount);
							}else{
								row = sheet.getRow(nrowCount);
							}
							if(objSubSecVO.getSubSecName() != "" || objSubSecVO.getSubSecName() != null )	{
								createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,ncolCount,new HSSFRichTextString(objSubSecVO.getSubSecName()));
							}else{
								createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,ncolCount,new HSSFRichTextString(""));
							}
							sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),ncolCount,ncolCount+6));
							LogUtil.logMessage("objSubSecVO.getSubSecName():" +  objSubSecVO.getSubSecName());
							//Clause Detail Section
							nrowCount++;
								
							if(nSecCount % nSelectedOrderList == 0 || nSecCount == 0){
								row = sheet.createRow(nrowCount);
							}else{
								row = sheet.getRow(nrowCount);
							}
						 	clauseList = objSubSecVO.getClauseGroup();
						 	
						 	if (clauseList != null)	{
						 		
							 	clauseListIterator = clauseList.iterator();	
								
								 for (int nintPos=0;nintPos < 3; nintPos++)
									{
										if(columnHeadings[nintPos] == "Clause Description") {
											createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,nheadColCount,new HSSFRichTextString(columnHeadings[nintPos]));	
											sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),nheadColCount,nheadColCount+4));
											nheadColCount = nheadColCount + 5;
										}else{
											createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,nheadColCount,new HSSFRichTextString(columnHeadings[nintPos]));
											sheet.setColumnWidth(nheadColCount,columnWidth[nintPos]);
											nheadColCount++;
										}
									}
								 	
									while (clauseListIterator.hasNext()){
										objClauseVO = new ClauseVO();
										objClauseVO = (ClauseVO) clauseListIterator.next();
										nrowCount++;
										
										if(nSecCount % nSelectedOrderList == 0 || nSecCount == 0){
											row = sheet.createRow(nrowCount);
											objChkClaNumber.put(objClauseVO.getClauseNum(),new Integer(nrowCount));
										}else{
											if (nrowCount > sheet.getLastRowNum())
												row = sheet.createRow(nrowCount);
											else    
												row = sheet.getRow(nrowCount);
										}
	
										String strEnggData = "";
										strClauseDelFlag = objClauseVO.getClauseDelFlag();
										
										LogUtil.logMessage(objClauseVO.getClauseNum() + " " + objChkClaNumber.get(objClauseVO.getClauseNum()) + " of order " + nSecCount);
																				
										if(strClauseDelFlag !=null && strClauseDelFlag.equalsIgnoreCase("Y")){
											createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, nclauseColCount,new HSSFRichTextString(objClauseVO.getClauseNum()));
											nclauseColCount++;
											createCell(row,cellClauseStyle,HSSFCell.CELL_TYPE_STRING, nclauseColCount,new HSSFRichTextString("RESERVED"));//2nd column
											sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),nclauseColCount,nclauseColCount+4));
											nclauseColCount = nclauseColCount+ 5;
											createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING,nclauseColCount,new HSSFRichTextString(""));
										}else{						
											createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, nclauseColCount,new HSSFRichTextString(objClauseVO.getClauseNum()));
											nclauseColCount++;
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
											createClauseDescCell(row,cellClauseStyle ,HSSFCell.CELL_TYPE_STRING, nclauseColCount,richTextClaDesc);//3rd column
											sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),nclauseColCount,nclauseColCount+4));
											nclauseColCount = nclauseColCount+ 5;
											
											//Engineering Data
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
													objSubSecVO = new SubSectionVO();
													objSubSecVO = (SubSectionVO) arlPartOF.get(n);
													strEnggData = strEnggData +"Part of "+ objSubSecVO.getSubSecCode() + "\n";
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
											if(objClauseVO.getEngDataComment() == null){
												objClauseVO.setEngDataComment("");
											}else{
												strEnggData = strEnggData + objClauseVO.getEngDataComment();
											}
											createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING,nclauseColCount,new HSSFRichTextString(strEnggData));
											nTblDataSize = nTblDataSize + arlTableRows.size();
											
											//LogUtil.logMessage("nTblDataSize in clause loop:"+nTblDataSize);
											if (arlTableRows != null && arlTableRows.size() > 0) {
													//LogUtil.logMessage("Inside  table data :");
													//LogUtil.logMessage("arlTableRows.size():"+arlTableRows.size());
												 for (int k= 0; k < arlTableRows.size(); k++) {
													nrowCount++;
													if (nrowCount > sheet.getLastRowNum())
														row = sheet.createRow(nrowCount);
													else    
														row = sheet.getRow(nrowCount);
													int colCount = nclauseColCount -5;
													
													
													ArrayList arlTableColumns = (ArrayList) arlTableRows.get(k);
													if (k==0)
														createCell(row,cellTabHeadStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(0)));
													else
														createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(0)));
													
													if (k==0)
														createCell(row,cellTabHeadStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(1)));
													else
														createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(1)));
					
													if (k==0)
														createCell(row,cellTabHeadStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(2)));
													else
														createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(2)));
					
													if (k==0)
														createCell(row,cellTabHeadStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(3)));
													else
														createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(3)));
													
													if (k==0)
														createCell(row,cellTabHeadStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(4)));
													else
														createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(4)));
					
													colCount = colCount - 4;
												}
											}
										}
										nclauseColCount = nclauseColCount - 6;
									}
						 		}
								nSecCount++;
								//if(nSelectedOrderList == 2){
								ncolCount = ncolCount + 7;
								nclauseColCount = nclauseColCount + 7;
								nTblDataSize = 0;
								
								if (nrowCount > nrowCountMax)
									nrowCountMax = nrowCount;
								
								nrowCount = nrowCountMax;
								if(nSecCount % nSelectedOrderList == 0){
									nheadColCount = 0;
									ncolCount = 0;
									nclauseColCount = 0;
									nrowCount = nrowCount + 1;
								} else {
									nrowCount = nSecRow;
								}
								LogUtil.logMessage("After calculation of nrowCount :" + nrowCount);
						//}
					  }		
					}
				}	
			}			
		    objHttpServletResponse.setContentType("application/vnd.ms-excel");
			objHttpServletResponse.setHeader("Content-disposition", "attachment; filename= Clause Comparison Report.xls");
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
		//LogUtil.logMessage("returning from strhtmlclauseconvert :");
		return strRefinedClauseDesc;
	}
	
	private static ArrayList getTableDataColumnsCount(ArrayList arlTableCol)
	throws Exception {
		
		//LogUtil.logMessage("Inside getTableDataColumnsCount");
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
			//LogUtil.logMessage("Col Cnt" + new Integer(intCnt + 1));
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
	
	 /**
	    * Shift all <code>Cells</code> in the given range of row and column indexes
	    * down by the given number of rows.  This will leave empty
	    * <code>Cells</code> behind.
	    *
	    * @param sheet The <code>Sheet</code> on which to move <code>Cells</code>.
	    * @param colStart The 0-based column index on which to start moving cells.
	    * @param colEnd The 0-based column index on which to end moving cells.
	    * @param rowStart The 0-based row index on which to start moving cells.
	    * @param rowEnd The 0-based row index on which to end moving cells.
	    * @param numRows The number of columns to move <code>Cells</code> down.
	    */
	   private static void shiftCellsDown(HSSFSheet sheet, int colStart, int colEnd,
	      int rowStart, int rowEnd, int numRows)
	   {
	      
		   LogUtil.logMessage("    Shifting cells down in rows " + rowStart +
	            " to " + rowEnd + ", cells " + colStart +
	            " to " + colEnd + " by " + numRows + " rows.");
	      int newRowIndex;
	      HSSFRow oldRow, newRow;
	      HSSFCell cell, newCell;
	      for (int rowIndex = rowEnd; rowIndex >= rowStart; rowIndex--)
	      {
	         newRowIndex = rowIndex + numRows;
	         oldRow = sheet.getRow(rowIndex);
	         if (oldRow == null)
	            oldRow = sheet.createRow(rowIndex);
	         newRow = sheet.getRow(newRowIndex);
	         if (newRow == null)
	            newRow = sheet.createRow(newRowIndex);
	         for (int colIndex = colStart; colIndex <= colEnd; colIndex++)
	         {
	            cell = oldRow.getCell(colIndex);
	            if (cell == null)
	               cell = oldRow.createCell(colIndex);
	            newCell = newRow.getCell(colIndex);
	            if (newCell == null)
	               newCell = newRow.createCell(colIndex);
	            copyCell(cell, newCell);

	            // Remove the just copied Cell if we detect that it won't be
	            // overwritten by future loops.
	            if (rowIndex < rowStart + numRows && rowIndex <= rowEnd)
	               removeCell(oldRow, cell);
	         }
	      }

	      shiftMergedRegionsInRange(sheet, colStart, colEnd, rowStart, rowEnd, 0, numRows, true, true);
	   }

	   /**
	    * Removes the given <code>Cell</code> from the given <code>Row</code>.
	    * Also removes any <code>Comment</code>.
	    * @param row The <code>Row</code> on which to remove a <code>Cell</code>.
	    * @param cell The <code>Cell</code> to remove.
	    */
	   private static void removeCell(HSSFRow row, HSSFCell cell)
	   {
	      cell.removeCellComment();
	      row.removeCell(cell);
	   }

	   /**
	    * Copy the contents of the old <code>Cell</code> to the new
	    * <code>Cell</code>, including borders, cell styles, etc.
	    * @param oldCell The source <code>Cell</code>.
	    * @param newCell The destination <code>Cell</code>.
	    */
	   private static void copyCell(HSSFCell oldCell, HSSFCell newCell)
	   {
	      newCell.setCellStyle(oldCell.getCellStyle());

	      switch(oldCell.getCellType())
	      {
	      case HSSFCell.CELL_TYPE_STRING:
	         newCell.setCellValue(oldCell.getRichStringCellValue());
	         break;
	      case HSSFCell.CELL_TYPE_NUMERIC:
	         newCell.setCellValue(oldCell.getNumericCellValue());
	         break;
	      case HSSFCell.CELL_TYPE_BLANK:
	         newCell.setCellType(HSSFCell.CELL_TYPE_BLANK);
	         break;
	      case HSSFCell.CELL_TYPE_FORMULA:
	         newCell.setCellFormula(oldCell.getCellFormula());
	         break;
	      case HSSFCell.CELL_TYPE_BOOLEAN:
	         newCell.setCellValue(oldCell.getBooleanCellValue());
	         break;
	      case HSSFCell.CELL_TYPE_ERROR:
	         newCell.setCellErrorValue(oldCell.getErrorCellValue());
	         break;
	      default:
	         break;
	      }
	   }  
	   
	   /**
	    * Shifts all merged regions found in the given range by the given number
	    * of rows and columns (usually one of those two will be zero).
	    * @param sheet The <code>Sheet</code> on which to shift merged regions.
	    * @param left The 0-based index of the column on which to start shifting
	    *    merged regions.
	    * @param right The 0-based index of the column on which to end shifting
	    *    merged regions.
	    * @param top The 0-based index of the row on which to start shifting
	    *    merged regions.
	    * @param bottom The 0-based index of the row on which to end shifting
	    *    merged regions.
	    * @param numCols The number of columns to shift the merged region (can be
	    *    negative).
	    * @param numRows The number of rows to shift the merged region (can be
	    *    negative).
	    * @param remove Determines whether to remove the old merged region,
	    *    resulting in a shift, or not to remove the old merged region,
	    *    resulting in a copy.
	    * @param add Determines whether to add the new merged region, resulting in
	    *    a copy, or not to add the new merged region, resulting in a shift.
	    */
	   private static void shiftMergedRegionsInRange(HSSFSheet sheet,
	      int left, int right, int top, int bottom, int numCols, int numRows,
	      boolean remove, boolean add)
	   {
	      
		   LogUtil.logMessage("      sMRIR: left " + left + ", right " + right +
	            ", top " + top + ", bottom " + bottom + ", numCols " + numCols +
	            ", numRows " + numRows + ", remove " + remove + ", add " + add);
	      if (numCols == 0 && numRows == 0 && remove && add)
	         return;
	      
	      ArrayList regions = new ArrayList();
	      for (int i = 0; i < sheet.getNumMergedRegions(); i++)
	      {
	    	 CellRangeAddress  region = sheet.getMergedRegion(i);
	         if (isCellAddressWhollyContained(region, left, right, top, bottom))
	         {
	            regions.add(region);
	            // Remove this range, if desired.
	            if (remove)
	            {
	            	LogUtil.logMessage("Removing merged region: " + region);
	               sheet.removeMergedRegion(i);
	               // Must try this index again!
	               i--;
	            }
	         }
	      }
	      // If desired, add a new region with the shifted version.
	      if (add)
	      {
	    	  LogUtil.logMessage("regions.size():"+regions.size());
	        for(int i=0;i<regions.size();i++)	 
	         {
	        	CellRangeAddress newRegion = new CellRangeAddress(
	        			top+numRows,
	        			top+numRows,
	        			left+1,
	        			right-1);
	        	LogUtil.logMessage("Adding adjusted merged region: " + newRegion + ".");
	            sheet.addMergedRegion(newRegion);
	            top++;
	         }
	      }
	   }
	   
	   /**
	    * Determines whether the given <code>CellRangeAddress</code>, representing
	    * a merged region, is wholly contained in the given area of
	    * <code>Cells</code>.  If <code>left</code> &gt;= <code>right</code>, then
	    * this will search the entire row(s).
	    * @param mergedRegion The <code>CellRangeAddress</code> merged region.
	    * @param left The 0-based column index on which to start searching for
	    *    merged regions.
	    * @param right The 0-based column index on which to stop searching for
	    *    merged regions.
	    * @param top The 0-based row index on which to start searching for
	    *    merged regions.
	    * @param bottom The 0-based row index on which to stop searching for
	    *    merged regions.
	    * @return <code>true</code> if wholly contained, <code>false</code>
	    *    otherwise.
	    */
	   private static boolean isCellAddressWhollyContained(CellRangeAddress mergedRegion,
	      int left, int right, int top, int bottom)
	   {
	      return (mergedRegion.getFirstRow() >= top && mergedRegion.getLastRow() <= bottom &&
	          mergedRegion.getFirstColumn() >= left && mergedRegion.getLastColumn() <= right);
	   }
	   
	  	//Added for CR-128 Ends here   
	
	   //Added for CR-135 Starts here
	   /***************************************************************************
		 * This method is used to get al the clause mismatch when order clause and model defaults compared
		 **************************************************************************/
	   public ActionForward fetchOrderVsModelDeltaDetails(
				ActionMapping objActionMapping, ActionForm objActionForm,
				HttpServletRequest objHttpServletRequest,
				HttpServletResponse objHttpServletResponse)
		throws DataAccessException, ApplicationException {
		   
		    HttpSession objSession = objHttpServletRequest.getSession(false);
			ArrayList arlOrderModelDet = null;
			
			ClauseCompareForm objClauseCompareForm = (ClauseCompareForm) objActionForm;
			ArrayList selectedList = new ArrayList();
			ArrayList selectedOrderList = new ArrayList();
			LogUtil.logMessage("Entering ClauseCompareAction.fetchOrderVsModelDeltaDetails");
			String strForward = ApplicationConstants.ORDER_MODEL_POP_UP;
			String strUserID = ApplicationConstants.EMPTY_STRING;
			
			/** Getting User from the session */
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			try{
				LogUtil.logMessage("Inside try");
				String orderKey = objHttpServletRequest
				.getParameter(ApplicationConstants.ORDER_KEYS);
				String sectionId = objHttpServletRequest
				.getParameter(ApplicationConstants.SECTION_IDS);
				String selectedModelName = objHttpServletRequest
				.getParameter(ApplicationConstants.SELECTED_MODELS);
				String selectedSpecType = objHttpServletRequest
				.getParameter(ApplicationConstants.SELECTED_SPEC_TYPE);
				String selectedOrderNumber = objHttpServletRequest
				.getParameter(ApplicationConstants.ORDER_NUMBERS);
				String dataLocType = objHttpServletRequest
				.getParameter(ApplicationConstants.DATA_LOC_TYPE);
				
				LogUtil.logMessage("DataLocType" +dataLocType);
				objClauseCompareForm.setHdnorderNo(selectedOrderNumber);
				objClauseCompareForm.setHdnSelModel(selectedModelName);
				objClauseCompareForm.setHdnSelSpecType(selectedSpecType);
				
				OrderVO objOrder = new OrderVO();
				
				/** The fetched search criteria are set into the OrderVO. */
				if(orderKey!= null){
					
					objOrder.setOrderNo(selectedOrderNumber);
					objOrder.setSpecTypeName(selectedSpecType);
					objOrder.setModelName(selectedModelName);
					objOrder.setOrderKey(Integer.parseInt(orderKey));
					objOrder.setSelectedSectionSeqNo(Integer.parseInt(sectionId));
					objOrder.setUserID(strUserID);
					objOrder.setDataLocTypeCode(dataLocType);
					selectedOrderList.add(objOrder);
				}
				
				
				objClauseCompareForm.setSelectedOrderList(selectedOrderList);
				
				ClauseCompareBO objClauseCompareBo = ClauseCompareBO.getInstance();
				
				arlOrderModelDet = objClauseCompareBo.fetchOrderVsModelDetails(objOrder);
				
				if (arlOrderModelDet != null && arlOrderModelDet.size() > 0) {
					objClauseCompareForm.setOrderModelDetList(arlOrderModelDet);
					
				}
				else{
					objClauseCompareForm
					.setMessageID(ApplicationConstants.NO_RECORD_MESSAGE_ID);
				}
				
				LogUtil.logMessage("ArrayListt" + objClauseCompareForm.getOrderModelDetList());
			} catch (Exception objExp) {
				strForward = ApplicationConstants.FAILURE;
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(objExp.getMessage() + "");
				LogUtil.logError(objErrorInfo);
			}
		   
		   return objActionMapping.findForward(strForward);
		   
	   }
	//Added for CR-135 ends here.
	   
}