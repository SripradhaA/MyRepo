package com.EMD.LSDB.action.SpecComparison;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This Action class is used to fetch orders and displays the
 *          component comparison details in pop up page and excel.
 ******************************************************************************/

import java.io.OutputStream;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
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
import com.EMD.LSDB.bo.SpecComparison.ComponentCompareBO;
import com.EMD.LSDB.bo.SpecMaintenance.OrderBO;
import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.ComponentCompareBI;
import com.EMD.LSDB.bo.serviceinterface.HistoryEdlPopUpBI;
import com.EMD.LSDB.bo.serviceinterface.ModelBI;
import com.EMD.LSDB.bo.serviceinterface.SpecTypeBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.form.SpecComparison.ComponentCompareForm;
import com.EMD.LSDB.form.SpecMaintenance.OrderSpecificClauseForm;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SpecTypeVo;
import com.EMD.LSDB.vo.common.SubSectionVO;

public class ComponentCompareAction extends EMDAction {
	
	/***************************************************************************
	 * This Method is used to load all the models present in the database
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
	//CR_84-Updated For CR_84
	public ActionForward initLoad(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering ComponentCompareAction.initLoad");
		String strForward = ApplicationConstants.SUCCESS;
		ComponentCompareForm objComponentCompareForm = (ComponentCompareForm) objActionForm;
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
				objComponentCompareForm.setSpecTypeList(arlSpecList);
			}
			
		} catch (Exception objExp) {
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
		
	}
	//Added New Method For FetchModels For CR_84 
	public ActionForward fetchModels(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering ComponentCompareAction.fetchModels");
		String strForward = ApplicationConstants.SUCCESS;
		ComponentCompareForm objComponentCompareForm = (ComponentCompareForm) objActionForm;
		ArrayList arlSpecList = new ArrayList();
		ArrayList arlModelList = new ArrayList();
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			ModelVo objModelVo = new ModelVo();
			
			ModelBI objModelBo = ServiceFactory.getModelBO();
			String strUserID = ApplicationConstants.EMPTY_STRING;
			/** Getting User from the session */
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			
/** Getting User from the session */
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			/** SpecTypeBI instace is obtained. */
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			
			/** UserID is set in the ModelVO. */
			objModelVo.setUserID(strUserID);
			/** To Fetch Specification Types */
			arlSpecList = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			
			if (arlSpecList != null && arlSpecList.size() > 0) {
				objComponentCompareForm.setSpecTypeList(arlSpecList);
			}
			/** To fetch Models for a specification type. */
			objModelVo
			.setSpecTypeSeqNo(objComponentCompareForm.getSpectypeSeqno());
			/** The list of all models present in the database is obtained. */
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null && arlModelList.size() > 0) {
				objComponentCompareForm.setModelList(arlModelList);
			}
			
			/** The obtained list of models is set in the action form. */
			objComponentCompareForm.setHdnSelSpecType(objComponentCompareForm
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
	//CR_84 Updateded
	public ActionForward fetchOrdersForComponentComparison(
			ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil
		.logMessage("Entering ComponentCompareAction.fetchOrdersForComparison");
		String strForward = ApplicationConstants.SUCCESS;
		ComponentCompareForm objComponentCompareForm = (ComponentCompareForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			//CR-84 lines are started here 
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			//CR-84 lines are ends here 
			ModelVo objModelVo = new ModelVo();
			ModelBI objModelBo = ServiceFactory.getModelBO();
			ArrayList arlSpecList = new ArrayList();
			ArrayList arlModelList = new ArrayList();
			String strUserID = ApplicationConstants.EMPTY_STRING;
			//CR_84 lines are started here
			/** Getting User from the session */
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			objSpecTypeVo.setUserID(strUserID);
			arlSpecList = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpecList != null && arlSpecList.size() > 0) {
				objComponentCompareForm.setSpecTypeList(arlSpecList);
			}
			
			/** To fetch Models for a sepcification type. */
			objModelVo
			.setSpecTypeSeqNo(objComponentCompareForm.getSpectypeSeqno());
			//C-84 Lines are ends here
			/** UserID is set in the ModelVO. */
			objModelVo.setUserID(strUserID);
			
			/** The list of all models present in the database is obtained. */
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			/** The obtained list of models is set in the action form. */
			if (arlModelList != null && arlModelList.size() > 0) {
				objComponentCompareForm.setModelList(arlModelList);
			}
			objComponentCompareForm.setHdnSelSpecType(objComponentCompareForm
					.getHdnSelSpecType());
			
			/** To Fetch orders for the specified search criteria. */
			OrderVO objOrderVo = new OrderVO();
			
			/**
			 * Search parameters set in the form are set into the OrderVO to
			 * fetch the result.
			 */
			objOrderVo.setUserID(strUserID);
			objOrderVo.setOrderNo(objComponentCompareForm.getHdnorderNo());
			objOrderVo
			.setDataLocTypeCode(ApplicationConstants.DATA_lOCATION_TYPE);
			objOrderVo.setModelSelected(objComponentCompareForm
					.getModelSeqNos());
			
//			Change for LSDb_CR-87
			objOrderVo.setSortByFlag(objComponentCompareForm.getSortByFlag());
			
			//Added for CR_104
			//Added for CR_104 fix 
			if (objComponentCompareForm.isCompShowLatFlag())
			{objOrderVo.setShowLatestFlag("Y");	
			objComponentCompareForm.setCompShowLatONOFF("Yes");
			}
			else
			{
				objOrderVo.setShowLatestFlag("N");
				objComponentCompareForm.setCompShowLatONOFF("No");
			}
			//CR_104 Ends here
			/** OrderBO instance is created. */
			OrderBO objOrderBo = OrderBO.getInstance();
			
			/** List of oreders matching the search criteria are obtained. */
			ArrayList arlOrderList = objOrderBo
			.fetchOrdersForSpecComparison(objOrderVo);
	
//			Change for LSDb_CR-87
			if (arlOrderList != null && arlOrderList.size() > 0) {
				objComponentCompareForm.setOrderList(arlOrderList);
				
				objComponentCompareForm.setSortByFlag(objComponentCompareForm.getSortByFlag());
			}
			
			
			/** Obtained list of orders are set in the Action Form. */
			objComponentCompareForm.setOrderList(arlOrderList);
			
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
	
	public ActionForward fetchSectionComponentsForComparison(
			ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		ArrayList arlSectionList = new ArrayList();
		
		ComponentCompareForm objComponentCompareForm = (ComponentCompareForm) objActionForm;
		ArrayList arlSelectedList = new ArrayList();
		LogUtil
		.logMessage("Entering ComponentCompareAction.fetchOrdersForComparison");
		String strForward = ApplicationConstants.POP_UP;
		String strUserID = ApplicationConstants.EMPTY_STRING;
		
		/** Getting User from the session */
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		
		/** UserID is set in the ModelVO. */
		if (objLoginVo != null) {
			strUserID = objLoginVo.getUserID();
		}
		try {
			
			/** To get the selected order number information from request scope. */
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
			
			/**
			 * To get the information of individual order number information and
			 * store it in OrderVO
			 */
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
				objOrder.setDataLocTypeCode("S");
				arlSelectedList.add(objOrder);
			}
			
			/** To fetch section detais of the selected order numbers. */
			objComponentCompareForm.setSelectedOrderList(arlSelectedList);
			ComponentCompareBO objComponentCompareBo = ComponentCompareBO
			.getInstance();
			arlSectionList = objComponentCompareBo
			.fetchSectionsAndSubSections(arlSelectedList);
			objComponentCompareForm.setCompareOrderList(arlSectionList);
			objComponentCompareForm.setOrderListSize(orderSize);
			
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
	
	/*public ActionForward fetchSectionComponentsForExcelComparison(
			ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		ArrayList arlSectionList = new ArrayList();
		
		ComponentCompareForm objComponentCompareForm = (ComponentCompareForm) objActionForm;
		ArrayList arlSelectedList = new ArrayList();
		LogUtil
		.logMessage("Entering ComponentCompareAction.fetchOrdersForComparison");
		String strForward = ApplicationConstants.POP_UP_FOR_EXCEL;
		String strUserID = "";
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		/** Getting User from the session */
		/*if (objLoginVo != null) {
			strUserID = objLoginVo.getUserID();
		}
		try {
			
			/** To get the selected order number information from request scope. */
			/*String orderKeys = objHttpServletRequest
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
			
			/**
			 * To get the information of individual order number informations
			 * and store it in OrderVO
			 */
			/*for (int count = 0; count < orderSize; count++) {
				OrderVO objOrder = new OrderVO();
				objOrder.setOrderNo(orderNumbers[count]);
				objOrder.setCustomerName(customerNames[count]);
				objOrder.setSpecTypeName(specType[count]);
				objOrder.setModelName(modelNames[count]);
				objOrder.setOrderKey(Integer.parseInt(orders[count]));
				objOrder.setSelectedSectionSeqNo(Integer
						.parseInt(sections[count]));
				objOrder.setUserID(strUserID);
				objOrder.setDataLocTypeCode("S");
				arlSelectedList.add(objOrder);
			}
			
			objComponentCompareForm.setSelectedOrderList(arlSelectedList);
			ComponentCompareBO objComponentCompareBo = ComponentCompareBO
			.getInstance();
			
			/** To fetch section detais of the selected order numbers. */
			/*arlSectionList = objComponentCompareBo
			.fetchSectionsAndSubSections(arlSelectedList);
			objComponentCompareForm.setCompareOrderList(arlSectionList);
			objComponentCompareForm.setOrderListSize(orderSize);
			
		} catch (Exception objExp) {
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
		
		}*/

	
	
	public ActionForward fetchSectionComponentsForExcelComparison(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		LogUtil.logMessage("Inside the Component Compare Action : VIEW REPORT IN EXCEL");
		ComponentCompareForm objComponentCompareForm = (ComponentCompareForm) objActionForm;
		
		String strForwardKey = ApplicationConstants.SUCCESS;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		int rowCount = 0;
		int intcol=0;
		String strUserID = "";
		ArrayList arlSelectedList = new ArrayList();
		ComponentVO objComponent = new ComponentVO();
		ClauseVO objClauseVO = new ClauseVO();
		OrderVO objOrder = new OrderVO();
		try{
			LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
			if (objLoginVo != null) 
			{
				strUserID = objLoginVo.getUserID();
			}
			
			/** To get the selected order number information from request scope. */
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
			String[] orders = orderKeys.split(ApplicationConstants.COMMA);
			String[] sections = sectionIds.split(ApplicationConstants.COMMA);
			String[] orderNumbers = selectedOrderNumber.split(ApplicationConstants.COMMA);
			String[] customerNamesParam = selectedCustomerNames.split(ApplicationConstants.COMMA);
			//Added for production issue for customername Fix
			String customerNames[] = new String[customerNamesParam.length];
			for (int index =0; index < customerNamesParam.length; index++){
				LogUtil.logMessage("customerNames[index] Before replace:" +customerNamesParam[index]);
				customerNames[index] = (customerNamesParam[index]).toString().replace('^',',');
				LogUtil.logMessage("customerNames[index]: After replace " +customerNames[index]);
			}
	     	//Added for production issue for customername Fix Ends Here
			String[] specType = selectedSpecType.split(ApplicationConstants.COMMA);
			String[] modelNames = selectedModelNames.split(ApplicationConstants.COMMA);
			int orderSize = orders.length;
			/**
			 * To get the information of individual order number informations
			 * and store it in OrderVO
			 */
			for (int count = 0; count < orderSize; count++) {
				objOrder = new OrderVO();
				objOrder.setOrderNo(orderNumbers[count]);
				objOrder.setCustomerName(customerNames[count]);
				objOrder.setSpecTypeName(specType[count]);
				objOrder.setModelName(modelNames[count]);
				objOrder.setOrderKey(Integer.parseInt(orders[count]));
				objOrder.setSelectedSectionSeqNo(Integer
						.parseInt(sections[count]));
				objOrder.setUserID(strUserID);
				objOrder.setDataLocTypeCode("S");
				arlSelectedList.add(objOrder);
			}
			
			objComponentCompareForm.setSelectedOrderList(arlSelectedList);
			
			
			HSSFWorkbook workBook = new HSSFWorkbook();
			HSSFSheet sheet = workBook.createSheet("Component Comparison Report");//create sheet
			
			HSSFCellStyle cellHeadStyle = workBook.createCellStyle();
			HSSFCellStyle cellHeadStyleCenter = workBook.createCellStyle();
			HSSFFont headFont = workBook.createFont();
			
			headFont.setFontName(HSSFFont.FONT_ARIAL);
			headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			headFont.setColor(HSSFColor.BLACK.index);	
			headFont.setFontHeightInPoints((short) 10);
			
			cellHeadStyle.setWrapText(false);
			cellHeadStyle.setFont(headFont);
			cellHeadStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			cellHeadStyleCenter.setWrapText(false);
			cellHeadStyleCenter.setFont(headFont);
			cellHeadStyleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			
			HSSFPalette palette = ((HSSFWorkbook)workBook).getCustomPalette(); 
			palette.setColorAtIndex((short)57, (byte)210, (byte)221, (byte)249); 
			cellHeadStyle.setFillForegroundColor(palette.getColor(57).getIndex()); 
			cellHeadStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
			cellHeadStyleCenter.setFillForegroundColor(palette.getColor(57).getIndex()); 
			cellHeadStyleCenter.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
			
			HSSFCellStyle cellStyle = workBook.createCellStyle();										
			
			HSSFFont Font = workBook.createFont();			
			Font.setFontName(HSSFFont.FONT_ARIAL);
			Font.setColor(HSSFColor.BLACK.index);		
			Font.setFontHeightInPoints((short) 10);
			
			cellStyle.setFont(Font);
			cellStyle.setWrapText(true);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);

			HSSFCellStyle cellGreenStyle = workBook.createCellStyle();
			
			HSSFFont GreenFont = workBook.createFont();			
			GreenFont.setFontName(HSSFFont.FONT_ARIAL);
			GreenFont.setColor(HSSFColor.WHITE.index);		
			GreenFont.setFontHeightInPoints((short) 10);
			
			cellGreenStyle.setFont(GreenFont);
			cellGreenStyle.setWrapText(true);
			cellGreenStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			cellGreenStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellGreenStyle.setFillForegroundColor(HSSFColor.GREEN.index);
			
			HSSFCellStyle cellLightGreenStyle = workBook.createCellStyle();
			
			cellLightGreenStyle.setFont(Font);
			cellLightGreenStyle.setWrapText(true);
			cellLightGreenStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			cellLightGreenStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellLightGreenStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
			
			HSSFCellStyle cellCompHeadStyle = workBook.createCellStyle();
			
			HSSFFont headCompFont = workBook.createFont();
			
			headCompFont.setFontName(HSSFFont.FONT_ARIAL);
			headCompFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			headCompFont.setColor(HSSFColor.BLACK.index);	
			headCompFont.setFontHeightInPoints((short) 10);
			
			cellCompHeadStyle.setFont(headCompFont);
			cellCompHeadStyle.setWrapText(true);
			cellCompHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellCompHeadStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellCompHeadStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
			
			ComponentCompareBI objComponentCompareBI = ServiceFactory.getComponentCompareBO();
			
			int colWidth = orders.length * 2;
			
			ArrayList arlCompCmpreList = new ArrayList();
			ArrayList arlCompCompareList = new ArrayList();
			arlCompCmpreList = objComponentCompareBI.fetchSectionsAndSubSections(arlSelectedList);
									
			Iterator listCompCmpreList = null;
			Iterator ListCompCmparisonList = null;
			Iterator ListOrderList = null;
			Iterator ListFinalList = null;
			HSSFRow row = sheet.createRow(rowCount);
			rowCount=0;
			int nFirstOrdCnt = 0;
			LogUtil.logMessage("arlCompCmpreList.size() : "+ arlCompCmpreList.size());
			if (arlCompCmpreList.size() != 0) {
			createHeadingForComponentComparison(workBook,row,sheet,colWidth);
				
				ListOrderList = arlSelectedList.iterator();
				while(ListOrderList.hasNext()){
					objOrder = new OrderVO();
					objOrder = (OrderVO) ListOrderList.next();
				rowCount=0;
				rowCount++;
				if(nFirstOrdCnt == 0)
					row = sheet.createRow(rowCount);
				else
					row = sheet.getRow(rowCount);
				createCell(row,cellHeadStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString("Order Number"));
				intcol++;
				createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(objOrder.getOrderNo()));
				rowCount++;
				intcol--;
				
				if(nFirstOrdCnt == 0)
					row = sheet.createRow(rowCount);
				else
					row = sheet.getRow(rowCount);
				createCell(row,cellHeadStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString("Model Name"));
				intcol++;
				createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(objOrder.getModelName()));
				rowCount++;
				intcol--;
				
				if(nFirstOrdCnt == 0)
					row = sheet.createRow(rowCount);
				else
					row = sheet.getRow(rowCount);
				createCell(row,cellHeadStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString("Customer Name"));
				intcol++;
				createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(objOrder.getCustomerName()));
				rowCount++;
				intcol--; 
				
				if(nFirstOrdCnt == 0)
					row = sheet.createRow(rowCount);
				else
					row = sheet.getRow(rowCount);
				createCell(row,cellHeadStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString("Spec Status"));
				intcol++;
				createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(objOrder.getSpecTypeName()));
				
				rowCount = rowCount + 2;
				intcol--;
				if(nFirstOrdCnt == 0)
					row = sheet.createRow(rowCount);
				else
					row = sheet.getRow(rowCount);
				createCell(row,cellHeadStyleCenter ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString("COMPONENT GROUP"));
				intcol++;
				createCell(row,cellHeadStyleCenter ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString("COMPONENT"));
				intcol++;
				
				nFirstOrdCnt++;
				}
								
				listCompCmpreList = arlCompCmpreList.iterator();
				
				while (listCompCmpreList.hasNext()) {
					arlCompCompareList = new ArrayList();
					arlCompCompareList = (ArrayList)listCompCmpreList.next();
					LogUtil.logMessage("arlCompCompareList.size() : "+ arlCompCompareList.size());
					ListCompCmparisonList = arlCompCompareList.iterator();
					
						int nFirstSecCnt=0;
						rowCount++;
						intcol=0;
						int nSecRow = 0;
						
						while(ListCompCmparisonList.hasNext()){
							
								objClauseVO = new ClauseVO();
								
								objClauseVO = (ClauseVO) ListCompCmparisonList.next();
								LogUtil.logMessage("rowCount AT begin : "+ rowCount);

								if(nFirstSecCnt == 0)
									row = sheet.createRow(rowCount);
								else
									row = sheet.getRow(rowCount);

								nSecRow = rowCount; //For use to reset the rowcount value to this section rownumber
								if(objClauseVO.getSectionName() != null){
									LogUtil.logMessage("objClauseVO.getSectionName() : "+ objClauseVO.getSectionName());
									createCell(row,cellCompHeadStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(objClauseVO.getSectionName()));
									sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),intcol,intcol+1));
									rowCount++;
								} else {
									LogUtil.logMessage("objClauseVO.getSectionName() : "+ objClauseVO.getSectionName());
									createCell(row,cellCompHeadStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(""));
									sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),intcol,intcol+1));
									rowCount++;
								}
								
								if(nFirstSecCnt == 0)
									row = sheet.createRow(rowCount);
								else
									row = sheet.getRow(rowCount);
								
								if(objClauseVO.getSubSectionName() != null){
									LogUtil.logMessage("objClauseVO.getSubSectionName() : "+objClauseVO.getSubSectionName());
									createCell(row,cellHeadStyleCenter ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(objClauseVO.getSubSectionName()));
									sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),intcol,intcol+1));
									rowCount++;
								} else {
									LogUtil.logMessage("objClauseVO.getSubSectionName() : "+objClauseVO.getSubSectionName());
									createCell(row,cellHeadStyleCenter ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(""));
									sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),intcol,intcol+1));
									rowCount++;
		
	}
																
								LogUtil.logMessage("rowCount AT end : "+ rowCount);
								ArrayList arlSmpleList = null;
								
								if(objClauseVO.getComponentVO() != null){ 
									
									arlSmpleList = (ArrayList) objClauseVO.getComponentVO();
									ListFinalList = arlSmpleList.iterator();
									
									int nFirstCompCnt = 0;		
									
									while(ListFinalList.hasNext()){
										
										objComponent = new ComponentVO();
										objComponent = (ComponentVO) ListFinalList.next();
										
										if (rowCount > sheet.getLastRowNum())
											row = sheet.createRow(rowCount);
										else
											row = sheet.getRow(rowCount);
										
										if(objComponent.getComponentGroupName()!= null){											
											createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(objComponent.getComponentGroupName()));
											intcol++;
											if(objComponent.getComponentName()!= null){
												createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(objComponent.getComponentName()));
												intcol--;
											}
											rowCount++;
										} else {
											createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(""));
											intcol++;
											if(objComponent.getComponentName()!= null){
												createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(""));
												intcol--;
											}
											rowCount++;
										}
										nFirstCompCnt++;
									  }
								}
								nFirstSecCnt++;
								
								if(nFirstSecCnt % arlCompCompareList.size() == 0){		
									intcol = 0;										
									if (rowCount < sheet.getLastRowNum())
										rowCount = sheet.getLastRowNum()+1;
									else
										rowCount = rowCount + 1;
								} else {									
									intcol=intcol+2;
									rowCount = nSecRow;
								}
							}
					}
				
			}
			
		    objHttpServletResponse.setContentType("application/vnd.ms-excel");
			objHttpServletResponse.setHeader("Content-disposition", "attachment; filename= ComponentComparisonReport.xls");
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
	
	public void createHeadingForComponentComparison(HSSFWorkbook workBook,HSSFRow row,HSSFSheet sheet,int colWidth) 
	throws Exception{
	
	HSSFCellStyle cellTitleHeadStyle = workBook.createCellStyle();
	
	HSSFFont headFont = workBook.createFont();
	
	headFont.setFontName(HSSFFont.FONT_ARIAL);
	headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	headFont.setColor(HSSFColor.BLACK.index);	
	headFont.setFontHeightInPoints((short) 10);
	
	cellTitleHeadStyle.setWrapText(true);
	cellTitleHeadStyle.setFont(headFont);
	cellTitleHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	cellTitleHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	
	
	/*final String[] columnHeadings = { "Component Comparison/Report"};
	
	createCell(row,cellTitleHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString(columnHeadings[0]));
	sheet.addMergedRegion(new CellRangeAddress(0,0,0,colWidth-1)); */
	
	for (int intPos=0;intPos < colWidth; intPos++)
	{
		 sheet.setColumnWidth(intPos,8000);
	}
}
	
	/***************************************************************************
	 * This Method is used to fetch the difference in component for the selected
	 * orders
	 * 
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward diffComponentsComparison(
			ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		ArrayList arlCompList = new ArrayList();
		
		ComponentCompareForm objComponentCompareForm = (ComponentCompareForm) objActionForm;
		ArrayList arlSelectedList = new ArrayList();
		LogUtil
		.logMessage("Entering ComponentCompareAction.diffComponentsComparison");
		String strForward = ApplicationConstants.POP_UP_DIFF_COMP;
		String strUserID = ApplicationConstants.EMPTY_STRING;
		int intModelSeqNo = 0;
		int intSectionSeqNo = 0;
		
		try {
			
			/** Getting User from the session */
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			/** UserID is set in the ModelVO. */
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			
			/** To get the selected order number information from request scope. */
			
			String strSectionName = objHttpServletRequest
			.getParameter("sectionName");
			
			String[] arrModSeqNo = objHttpServletRequest.getParameter(
			"modelseqNo").split(ApplicationConstants.COMMA);
			
			/*
			 * Split the String into String array for getting the modelSeqNo
			 */
			
			String[] arrOrders = objHttpServletRequest.getParameter(
					ApplicationConstants.ORDER_KEYS).split(
							ApplicationConstants.COMMA);
			String[] arrSections = objHttpServletRequest.getParameter(
					ApplicationConstants.SECTION_IDS).split(
							ApplicationConstants.COMMA);
			String[] arrOrderNumbers = objHttpServletRequest.getParameter(
					ApplicationConstants.ORDER_NUMBERS).split(
							ApplicationConstants.COMMA);
			String[] customerNamesParam = objHttpServletRequest.getParameter(
					ApplicationConstants.CUST_NAME).split(
							ApplicationConstants.COMMA);
			//Added for production issue for customername Fix
			String arrCustomerNames[] = new String[customerNamesParam.length];
			//LogUtil.logMessage("selectedCustomerNames:" +selectedCustomerNames);
			for (int index =0; index < customerNamesParam.length; index++){
				//LogUtil.logMessage("customerNames[index] Before replace:" +customerNamesParam[index]);
				arrCustomerNames[index] = (customerNamesParam[index]).toString().replace('^',',');
				//LogUtil.logMessage("customerNames[index]: After replace " +customerNames[index]);
			}
	     	//Added for production issue for customername Fix Ends Here
			String[] arrSpecType = objHttpServletRequest.getParameter(
					ApplicationConstants.SELECTED_SPEC_TYPE).split(
							ApplicationConstants.COMMA);
			String[] arrModelNames = objHttpServletRequest.getParameter(
					ApplicationConstants.SELECTED_MODELS).split(
							ApplicationConstants.COMMA);
			int intOrderSize = arrOrders.length;
			
			/**
			 * This String Array contains two model sequence numbers with same
			 * value so loop through it get one value and break the loop *
			 */
			
			if (arrModSeqNo.length != 0 && arrModSeqNo != null) {
				
				for (int count = 0; count < arrModSeqNo.length; count++) {
					
					intModelSeqNo = Integer.parseInt(arrModSeqNo[count]);
					// LogUtil.logMessage("ModelSeqNo in Action:" +
					// intModelSeqNo);
					break;
				}
				
			}
			/**
			 * This String Array contains two section sequence numbers with same
			 * value so loop through it get one value and break the loop *
			 */
			
			if (arrSections.length != 0 && arrSections != null) {
				
				for (int count = 0; count < arrSections.length; count++) {
					
					intSectionSeqNo = Integer.parseInt(arrSections[count]);
					// LogUtil.logMessage("sectionSeqNo in Action:"+
					// intSectionSeqNo);
					break;
				}
				
			}
			
			/**
			 * To get the information of individual order number information and
			 * store it in OrderVO. This part of code is used to display the
			 * order details in top of the page.
			 */
			for (int count = 0; count < intOrderSize; count++) {
				OrderVO objOrder = new OrderVO();
				objOrder.setOrderNo(arrOrderNumbers[count]);
				objOrder.setCustomerName(arrCustomerNames[count]);
				objOrder.setSpecTypeName(arrSpecType[count]);
				objOrder.setModelName(arrModelNames[count]);
				arlSelectedList.add(objOrder);
			}
			
			/** OrderVo used to fetch componentDifferenceComparison ** */
			
			OrderVO objOrderVO = new OrderVO();
			objOrderVO.setModelSeqNo(intModelSeqNo);
			objOrderVO.setSelectedSectionSeqNo(intSectionSeqNo);
			objOrderVO.setUserID(strUserID);
			objOrderVO.setOrderKeys(arrOrders);
			
			/** To fetch section detais of the selected order numbers. */
			objComponentCompareForm.setSelectedOrderList(arlSelectedList);
			objComponentCompareForm.setSectionName(strSectionName);
			
			ComponentCompareBO objComponentCompareBo = ComponentCompareBO
			.getInstance();
			arlCompList = objComponentCompareBo
			.fetchDifferencecomponent(objOrderVO);
			
			objComponentCompareForm.setCompareOrderList(arlCompList);
			objComponentCompareForm.setOrderListSize(intOrderSize);
			
		} catch (Exception objExp) {
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
		
	}
	
	/* Added for EDL Comparision and EDL comparision for EXcel by cm68219 */
	/***************************************************************************
	 * This Method is used to fetch EDL numbers for sections of the selected
	 * order numbers.
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward fetchEdlNumbersForComparison(
			ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		ArrayList arlEdlList = new ArrayList();
		ArrayList arlWholeEdlList = new ArrayList();
		ArrayList arlFinalList = new ArrayList();
		ArrayList arlSections = new ArrayList();
		ArrayList arlSubsections = new ArrayList();
		ComponentCompareForm objComponentCompareForm = (ComponentCompareForm) objActionForm;
		ArrayList arlSelectedList = new ArrayList();
		
		LogUtil
		.logMessage("Entering ComponentCompareAction.fetchEdlNumbersForComparison");
		String strForward = ApplicationConstants.EDL_POP_UP;
		String strUserID = ApplicationConstants.EMPTY_STRING;
		
		/** Getting User from the session */
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		if (objLoginVo != null) {
			strUserID = objLoginVo.getUserID();
		}
		try {
			
			/** To get the selected order number information from request scope. */
			
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
			
			/**
			 * To get the information of individual order number informations
			 * and store it in OrderVO
			 */
			for (int count = 0; count < orderSize; count++) {
				
				ClauseVO objClauseVO = new ClauseVO();
				objClauseVO.setOrderNo(orderNumbers[count]);
				objClauseVO.setCustomerName(customerNames[count]);
				objClauseVO.setSpecTypeName(specType[count]);
				objClauseVO.setModelName(modelNames[count]);
				objClauseVO.setOrderKey(Integer.parseInt(orders[count]));
				objClauseVO.setSectionSeqNo(Integer.parseInt(sections[count]));
				objClauseVO.setUserID(strUserID);
				objClauseVO.setDataLocationType("S");
				
				arlSelectedList.add(objClauseVO);
				
				HistoryEdlPopUpBI objHistoryEdlPopUpBO = ServiceFactory
				.getHistoryEdlPopUpBO();
				arlEdlList = objHistoryEdlPopUpBO.fetchEdlNo(objClauseVO);
				arlWholeEdlList.add(arlEdlList);
				LogUtil.logMessage("Selected OrderList Size"
						+ arlWholeEdlList.size());
				
			}
			
			objComponentCompareForm.setSelectedOrderList(arlSelectedList);
			
			/**
			 * This logic is used to compare the EDL numbers with the selected
			 * orders by taking the first order as base.If the EDL number is
			 * present in more than one selected order then we are setting the
			 * flag as "Y" in ClauseVO to high light the EDL number column with
			 * green color in JSP. Added For LSDB_CR-75 on 22-May-09 by ps57222
			 */
			
			/** Starts Here * */
			
			ArrayList arlWhole = new ArrayList();
			ArrayList arlFirstOrder = null;
			/** Modified for LSDB_CR-74 * */
			if (arlWholeEdlList != null && arlWholeEdlList.size() > 0) {
				if (arlWholeEdlList.size() > 1) {
					for (int count = 0; count < arlWholeEdlList.size(); count++) {
						if (count == 0) {
							arlFirstOrder = (ArrayList) arlWholeEdlList.get(0);
							
						} else if (count <= arlWholeEdlList.size() - 1) {
							ArrayList arlRemainOrder  = compareEDL(
									arlFirstOrder, (ArrayList) arlWholeEdlList
									.get(count));
							arlWhole.add(arlRemainOrder.get(1));
							arlFirstOrder = (ArrayList) arlRemainOrder.get(0);
						}
						
					}
					arlWhole.add(0, arlFirstOrder);
				} else {
					arlWhole.add(0, arlEdlList);
				}
				
				/** ENDS Here * */
				
				/**
				 * This logic is to set the values obtained for each orders in
				 * to a list which contains the subsection details sequence
				 * wise.
				 */
				/***************************************************************
				 * /
				 *  /* This will handle n > 1 orders, where n is the order
				 * number
				 **************************************************************/
				SectionVO objSecEmptyVo = new SectionVO();
				ClauseVO objClaEmptyVo = new ClauseVO();
				int maxsize = 0;
				for (int k = 0; k < arlWholeEdlList.size(); k++) {
					ArrayList arlsec = (ArrayList) arlWholeEdlList.get(k);
					if (arlsec.size() > maxsize) {
						maxsize = arlsec.size();
					}
					
				}
				/**
				 * Modified for CR_105
				 * This logic is to set the values obtained for each orders in
				 * to a list which contains the clause details sequence
				 * wise. This works by checking the max clause size between the 
				 * similar subsections of multiple orders and setting the empty
				 * ClauseVo if the respective subsection does not have max clauses.
				 * To reach the clauses similar setup should be done by finding max
				 * sections and subsections.
				 * 
				 * Orderlist --> A Sectionlist --> A.1 SubSectionlist --> A.1.xx Clauselist
				 * 
				 * Added by RR68151 05-Jan-12
				 */
				SectionVO objMainSectionVO = new SectionVO();
				SubSectionVO objMainSubSecVo = new SubSectionVO();
				ArrayList arlMainSubsections = new ArrayList();	
				ArrayList arlMainClauses = new ArrayList();	
				ArrayList arlClauses = new ArrayList();	
				String[] strSectionNames = new String[arlWholeEdlList.size()];
				String[] strSubSectionNames = new String[arlWholeEdlList.size()];
				
				for (int count = 0; count < maxsize; count++) {
					int maxsubsecsize = 0;	
					for (int k1 = 0; k1 < arlWholeEdlList.size(); k1++) {
						ArrayList arlsec = (ArrayList) arlWholeEdlList
						.get(k1);
						if (count < arlsec.size()) {
							SectionVO objSectionVo = (SectionVO) arlsec.get(count);
							ArrayList arlSubSection = (ArrayList) objSectionVo.getSubSec();
							if (arlSubSection.size() > maxsubsecsize) {
								maxsubsecsize = arlSubSection.size();
							}
						} else {
							arlSections.add(objSecEmptyVo);
						}

						if (k1==arlWholeEdlList.size()-1)	{	
							LogUtil.logMessage("maxsubsecsize"+ maxsubsecsize);
							for (int subcount = 0; subcount < maxsubsecsize; subcount++) {	
								int maxclasize = 0;									
								for (int k2 = 0; k2 < arlWholeEdlList.size(); k2++) {										
									ArrayList arlsection = (ArrayList) arlWholeEdlList.get(k2);
									if (count < arlsection.size()) {
										SectionVO objSectionVO = (SectionVO) arlsection.get(count);
										ArrayList arlSubSec = (ArrayList) objSectionVO.getSubSec();		
										if (subcount < arlSubSec.size()) {
											SubSectionVO objSubSecVo = (SubSectionVO) arlSubSec.get(subcount);	
											ArrayList arlClauseVo = (ArrayList) objSubSecVo.getClauseGroup();													
											if (arlClauseVo.size() > maxclasize) {
												maxclasize = arlClauseVo.size();
											}
											strSectionNames[k2] = objSectionVO.getSectionName();
											strSubSectionNames[k2] = objSubSecVo.getSubSecName();
											
										} else {
											strSubSectionNames[k2] = "";	
										}
									} else {
										strSectionNames[k2] = "";
									}
									if (k2==arlWholeEdlList.size()-1)	{	
										for (int clacount = 0; clacount < maxclasize; clacount++) {	
											for (int k3 = 0; k3 < arlWholeEdlList.size(); k3++) {
												ArrayList arlsections = (ArrayList) arlWholeEdlList.get(k3);
												if (count < arlsections.size()) {
													SectionVO objSectionVOs = (SectionVO) arlsections.get(count);
													ArrayList arlSubSecs = (ArrayList) objSectionVOs.getSubSec();
													if (subcount < arlSubSecs.size()) {
														SubSectionVO objSubSecVos = (SubSectionVO) arlSubSecs.get(subcount);
														ArrayList arlClauseVos = (ArrayList) objSubSecVos.getClauseGroup();
														if (clacount < arlClauseVos.size()) {
															arlClauses.add(arlClauseVos.get(clacount));
														}
														else	{
															arlClauses.add(objClaEmptyVo);
														}
													}else	{
														arlClauses.add(objClaEmptyVo);
													}
												}else	{
													arlClauses.add(objClaEmptyVo);
												}
											}
											arlMainClauses.add(arlClauses);
											arlClauses = new ArrayList();
										}
									objMainSubSecVo.setSubSecNames(strSubSectionNames);
									objMainSubSecVo.setClauseGroup(arlMainClauses);
									arlSubsections.add(objMainSubSecVo);
									objMainSubSecVo = new SubSectionVO();
									arlMainClauses = new ArrayList();
									strSubSectionNames = new String[arlWholeEdlList.size()];
									}
								}
								arlMainSubsections.add(arlSubsections);
								arlSubsections = new ArrayList();
							}
							objMainSectionVO.setSecNames(strSectionNames);
							objMainSectionVO.setSubSec(arlMainSubsections);
							arlSections.add(objMainSectionVO);
							arlMainSubsections = new ArrayList();
							strSectionNames = new String[arlWholeEdlList.size()];
						}
					}
					arlFinalList.add(arlSections);
					arlSections = new ArrayList();
					objMainSectionVO = new SectionVO();
				}
			}
			LogUtil.logMessage("arlFinalList.size():"+ arlFinalList.size());
			objComponentCompareForm.setCompareOrderList(arlFinalList);
			objComponentCompareForm.setOrderListSize(orderSize);
			
		} catch (Exception objExp) {
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
	}
	
	/***************************************************************************
	 * This Method is used to export EDL List into EX-CEL format.
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward fetchEdlNumberForExcelComparison(
			ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		ArrayList arlEdlList = new ArrayList();
		ArrayList arlWholeEdlList = new ArrayList();
		ArrayList arlFinalList = new ArrayList();
		ArrayList arlSections = new ArrayList();
		ArrayList arlSubsections = new ArrayList();
		ComponentCompareForm objComponentCompareForm = (ComponentCompareForm) objActionForm;
		ArrayList arlSelectedList = new ArrayList();
		
		LogUtil
		.logMessage("Entering ComponentCompareAction.fetchEdlNumbersForComparison");
		String strForward = ApplicationConstants.SUCCESS;
		String strUserID = ApplicationConstants.EMPTY_STRING;
		
		ClauseVO objClauseVO = new ClauseVO();
		
		/** Getting User from the session */
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		if (objLoginVo != null) {
			strUserID = objLoginVo.getUserID();
		}
		try {
			
			/** To get the selected order number information from request scope. */
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
			
			/**
			 * To get the information of individual order number informations
			 * and store it in OrderVO
			 */
			for (int count = 0; count < orderSize; count++) {
				objClauseVO = new ClauseVO();
				objClauseVO.setOrderNo(orderNumbers[count]);
				objClauseVO.setCustomerName(customerNames[count]);
				objClauseVO.setSpecTypeName(specType[count]);
				objClauseVO.setModelName(modelNames[count]);
				objClauseVO.setOrderKey(Integer.parseInt(orders[count]));
				objClauseVO.setSectionSeqNo(Integer.parseInt(sections[count]));
				objClauseVO.setUserID(strUserID);
				objClauseVO.setDataLocationType("S");
				arlSelectedList.add(objClauseVO);
				
				HistoryEdlPopUpBI objHistoryEdlPopUpBO = ServiceFactory
				.getHistoryEdlPopUpBO();
				arlEdlList = objHistoryEdlPopUpBO.fetchEdlNo(objClauseVO);
				arlWholeEdlList.add(arlEdlList);
				
			}
			
			objComponentCompareForm.setSelectedOrderList(arlSelectedList);
			
			/**
			 * This logic is used to compare the EDL numbers with the selected
			 * orders by taking the first order as base.If the EDL number is
			 * present in more than one selected order then we are setting the
			 * flag as "Y" in ClauseVO to high light the EDL number column with
			 * green color in JSP. Added For LSDB_CR-75 on 22-May-09 by ps57222
			 */
			
			/** Starts Here * */
			
			ArrayList arlWhole = new ArrayList();
			ArrayList arlFirstOrder = null;
			/** Modified for LSDB_CR-74 * */
			if (arlWholeEdlList != null) {
				if (arlWholeEdlList.size() > 1) {
					for (int count = 0; count < arlWholeEdlList.size(); count++) {
						if (count == 0) {
							arlFirstOrder = (ArrayList) arlWholeEdlList.get(0);
							
						} else if (count <= arlWholeEdlList.size() - 1) {
							ArrayList arlRemainOrder = compareEDL(
										arlFirstOrder, (ArrayList) arlWholeEdlList
										.get(count));
							arlWhole.add(arlRemainOrder.get(1));
							arlFirstOrder = (ArrayList) arlRemainOrder.get(0);
						}
						
					}
					arlWhole.add(0, arlFirstOrder);
				} else {
					arlWhole.add(0, arlEdlList);
				}
				
				/** ENDS Here * */
				
				/**
				 * This logic is to set the values obtained for each orders in
				 * to a list which contains the subsection details sequence
				 * wise.
				 */
				
				 /*This will handle n > 1 orders, where n is the order number **/
				SectionVO objSecEmptyVo = new SectionVO();
				ClauseVO objClaEmptyVo = new ClauseVO();
				int maxsize = 0;
				for (int k = 0; k < arlWholeEdlList.size(); k++) {
					ArrayList arlsec = (ArrayList) arlWholeEdlList.get(k);
					if (arlsec.size() > maxsize) {
						maxsize = arlsec.size();
					}
					
				}

				/**
				 * Modified for CR_105
				 * This logic is to set the values obtained for each orders in
				 * to a list which contains the clause details sequence
				 * wise. This works by checking the max clause size between the 
				 * similar subsections of multiple orders and setting the empty
				 * ClauseVo if the respective subsection does not have max clauses.
				 * To reach the clauses similar setup should be done by finding max
				 * sections and subsections.
				 * 
				 * Orderlist --> A Sectionlist --> A.1 SubSectionlist --> A.1.xx Clauselist
				 * 
				 * Added by RR68151 05-Jan-12
				 */
				SectionVO objMainSectionVO = new SectionVO();
				SubSectionVO objMainSubSecVo = new SubSectionVO();
				ArrayList arlMainSubsections = new ArrayList();	
				ArrayList arlMainClauses = new ArrayList();	
				ArrayList arlClauses = new ArrayList();	
				String[] strSectionNames = new String[arlWholeEdlList.size()];
				String[] strSubSectionNames = new String[arlWholeEdlList.size()];
				
				for (int count = 0; count < maxsize; count++) {
					int maxsubsecsize = 0;	
					for (int k1 = 0; k1 < arlWholeEdlList.size(); k1++) {
						ArrayList arlsec = (ArrayList) arlWholeEdlList
						.get(k1);
						if (count < arlsec.size()) {
							SectionVO objSectionVo = (SectionVO) arlsec.get(count);
							ArrayList arlSubSection = (ArrayList) objSectionVo.getSubSec();
							if (arlSubSection.size() > maxsubsecsize) {
								maxsubsecsize = arlSubSection.size();
							}
						} else {
							arlSections.add(objSecEmptyVo);
						}

						if (k1==arlWholeEdlList.size()-1)	{	
							LogUtil.logMessage("maxsubsecsize"+ maxsubsecsize);
							for (int subcount = 0; subcount < maxsubsecsize; subcount++) {	
								int maxclasize = 0;									
								for (int k2 = 0; k2 < arlWholeEdlList.size(); k2++) {										
									ArrayList arlsection = (ArrayList) arlWholeEdlList.get(k2);
									if (count < arlsection.size()) {
										SectionVO objSectionVO = (SectionVO) arlsection.get(count);
										ArrayList arlSubSec = (ArrayList) objSectionVO.getSubSec();		
										if (subcount < arlSubSec.size()) {
											SubSectionVO objSubSecVo = (SubSectionVO) arlSubSec.get(subcount);	
											ArrayList arlClauseVo = (ArrayList) objSubSecVo.getClauseGroup();													
											if (arlClauseVo.size() > maxclasize) {
												maxclasize = arlClauseVo.size();
											}
											strSectionNames[k2] = objSectionVO.getSectionName();
											strSubSectionNames[k2] = objSubSecVo.getSubSecName();
											
										} else {
											strSubSectionNames[k2] = "";	
										}
									} else {
										strSectionNames[k2] = "";
									}
									if (k2==arlWholeEdlList.size()-1)	{	
										for (int clacount = 0; clacount < maxclasize; clacount++) {	
											for (int k3 = 0; k3 < arlWholeEdlList.size(); k3++) {
												ArrayList arlsections = (ArrayList) arlWholeEdlList.get(k3);
												if (count < arlsections.size()) {
													SectionVO objSectionVOs = (SectionVO) arlsections.get(count);
													ArrayList arlSubSecs = (ArrayList) objSectionVOs.getSubSec();
													if (subcount < arlSubSecs.size()) {
														SubSectionVO objSubSecVos = (SubSectionVO) arlSubSecs.get(subcount);
														ArrayList arlClauseVos = (ArrayList) objSubSecVos.getClauseGroup();
														if (clacount < arlClauseVos.size()) {
															arlClauses.add(arlClauseVos.get(clacount));
														}
														else	{
															arlClauses.add(objClaEmptyVo);
														}
													}else	{
														arlClauses.add(objClaEmptyVo);
													}
												}else	{
													arlClauses.add(objClaEmptyVo);
												}
											}
											arlMainClauses.add(arlClauses);
											arlClauses = new ArrayList();
										}
									objMainSubSecVo.setSubSecNames(strSubSectionNames);
									objMainSubSecVo.setClauseGroup(arlMainClauses);
									arlSubsections.add(objMainSubSecVo);
									objMainSubSecVo = new SubSectionVO();
									arlMainClauses = new ArrayList();
									strSubSectionNames = new String[arlWholeEdlList.size()];
									}
								}
								arlMainSubsections.add(arlSubsections);
								arlSubsections = new ArrayList();
							}
							objMainSectionVO.setSecNames(strSectionNames);
							objMainSectionVO.setSubSec(arlMainSubsections);
							arlSections.add(objMainSectionVO);
							arlMainSubsections = new ArrayList();
							strSectionNames = new String[arlWholeEdlList.size()];
						}
					}
					arlFinalList.add(arlSections);
					arlSections = new ArrayList();
					objMainSectionVO = new SectionVO();
				}
			}
			LogUtil.logMessage("arlFinalList:" + arlFinalList.size());
			
			objComponentCompareForm.setCompareOrderList(arlFinalList);
			objComponentCompareForm.setOrderListSize(orderSize);
			
			HSSFWorkbook workBook = new HSSFWorkbook();
			HSSFSheet sheet = workBook.createSheet("EDL Comparison Report");//create sheet
			
			HSSFCellStyle cellHeadStyle = workBook.createCellStyle();
			HSSFCellStyle cellHeadStyleCntr = workBook.createCellStyle();
			HSSFCellStyle cellHeadStyleCenter = workBook.createCellStyle();
			HSSFCellStyle cellClauseDescStyle = workBook.createCellStyle();
			HSSFFont headFont = workBook.createFont();
			HSSFFont ClaFont = workBook.createFont();
			
			headFont.setFontName(HSSFFont.FONT_ARIAL);
			headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			headFont.setColor(HSSFColor.BLACK.index);	
			headFont.setFontHeightInPoints((short) 10);
			
			ClaFont.setFontName(HSSFFont.FONT_ARIAL);
			//ClaFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			ClaFont.setColor(HSSFColor.BLACK.index);	
			ClaFont.setFontHeightInPoints((short) 10);
			ClaFont.setItalic(true);
			
			cellClauseDescStyle.setWrapText(true);
			cellClauseDescStyle.setFont(ClaFont);
			
			cellHeadStyle.setWrapText(false);
			cellHeadStyle.setFont(headFont);
			
			cellHeadStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			
			cellHeadStyleCntr.setWrapText(false);
			cellHeadStyleCntr.setFont(headFont);
			cellHeadStyleCntr.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			
			//cellHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			cellHeadStyleCenter.setWrapText(true);
			cellHeadStyleCenter.setFont(headFont);
			cellHeadStyleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			
			HSSFPalette palette = ((HSSFWorkbook)workBook).getCustomPalette(); 
			palette.setColorAtIndex((short)57, (byte)210, (byte)221, (byte)249); 
			palette.setColorAtIndex((short)55, (byte)162, (byte)192, (byte)224); 
			cellHeadStyle.setFillForegroundColor(palette.getColor(57).getIndex()); 
			cellHeadStyleCntr.setFillForegroundColor(palette.getColor(57).getIndex()); 
			cellHeadStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
			cellHeadStyleCntr.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
			cellHeadStyleCenter.setFillForegroundColor(palette.getColor(55).getIndex()); 
			cellHeadStyleCenter.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
			
			HSSFCellStyle cellStyle = workBook.createCellStyle();										
			HSSFCellStyle cellLabelsStyle = workBook.createCellStyle();	
			
			HSSFFont Font = workBook.createFont();	
			Font.setFontName(HSSFFont.FONT_ARIAL);
			Font.setColor(HSSFColor.BLACK.index);		
			Font.setFontHeightInPoints((short) 10);
			
			cellLabelsStyle.setFont(Font);
			cellLabelsStyle.setWrapText(true);
			cellLabelsStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			
			cellStyle.setFont(Font);
			cellStyle.setWrapText(true);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			//cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			HSSFCellStyle cellGreenStyle = workBook.createCellStyle();
			
			HSSFFont GreenFont = workBook.createFont();			
			GreenFont.setFontName(HSSFFont.FONT_ARIAL);
			GreenFont.setColor(HSSFColor.WHITE.index);		
			GreenFont.setFontHeightInPoints((short) 10);
			
			cellGreenStyle.setFont(GreenFont);
			cellGreenStyle.setWrapText(true);
			cellGreenStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			cellGreenStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellGreenStyle.setFillForegroundColor(HSSFColor.GREEN.index);
			
			HSSFCellStyle cellLightGreenStyle = workBook.createCellStyle();
			
			cellLightGreenStyle.setFont(Font);
			cellLightGreenStyle.setWrapText(true);
			cellLightGreenStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFPalette palette1 = ((HSSFWorkbook)workBook).getCustomPalette(); 
			palette1.setColorAtIndex((short)45, (byte)0, (byte)204, (byte)51); 
			cellLightGreenStyle.setFillForegroundColor(palette.getColor(45).getIndex()); 
			cellLightGreenStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			
			HSSFCellStyle cellCompHeadStyle = workBook.createCellStyle();
			
			HSSFFont headCompFont = workBook.createFont();
			
			headCompFont.setFontName(HSSFFont.FONT_ARIAL);
			headCompFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			headCompFont.setColor(HSSFColor.BLACK.index);	
			headCompFont.setFontHeightInPoints((short) 10);
			
			cellCompHeadStyle.setFont(headCompFont);
			cellCompHeadStyle.setWrapText(true);
			cellCompHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellCompHeadStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellCompHeadStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
						
			int colWidth = orderSize * 4;
			
			int rowCount=0;
			int intcol =0;
			int nSecColCnt =0;
			int nDynamicColCnt = 0;
			int nDynamicClauseColCnt = 0;
			HSSFRow row = sheet.createRow(rowCount);
			if(arlSelectedList!= null){
			Iterator ListOrderList = null;
			ListOrderList = arlSelectedList.iterator();
			createHeadingForEDLComparison(workBook,row,sheet,colWidth);
			int nFirstOrdCnt =0;
			while(ListOrderList.hasNext()){
				objClauseVO = new ClauseVO();
				objClauseVO = (ClauseVO) ListOrderList.next();
			rowCount=1;
			
			
			if(nFirstOrdCnt  == 0)
				row = sheet.createRow(rowCount);
			else
				row = sheet.getRow(rowCount);
			createCell(row,cellHeadStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString("Order Number"));//0th column
			intcol++;
			createCell(row,cellLabelsStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(objClauseVO.getOrderNo()));
			sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),intcol,intcol+2));
			rowCount++;
			intcol--;
			
			if(nFirstOrdCnt == 0)
				row = sheet.createRow(rowCount);
			else
				row = sheet.getRow(rowCount);
			createCell(row,cellHeadStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString("Model Name"));//1st column
			intcol++;
			createCell(row,cellLabelsStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(objClauseVO.getModelName()));//2nd column
			sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),intcol,intcol+2));
			rowCount++;
			intcol--;
			
			
			if(nFirstOrdCnt == 0)
				row = sheet.createRow(rowCount);
			else
				row = sheet.getRow(rowCount);
			createCell(row,cellHeadStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString("Customer Name"));//1st column
			intcol++;
			createCell(row,cellLabelsStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(objClauseVO.getCustomerName()));//2nd column
			sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),intcol,intcol+2));
			rowCount++;
			intcol--;
			
			if(nFirstOrdCnt == 0)
				row = sheet.createRow(rowCount);
			else
				row = sheet.getRow(rowCount);
			createCell(row,cellHeadStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString("Spec Status"));//1st column
			intcol++;
			createCell(row,cellLabelsStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(objClauseVO.getSpecTypeName()));//2nd column
			sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),intcol,intcol+2));
			
			
			rowCount = rowCount + 2;
			intcol--;
			if(nFirstOrdCnt == 0)
				row = sheet.createRow(rowCount);
			else
				row = sheet.getRow(rowCount);
			createCell(row,cellHeadStyleCenter ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString("Clause Number"));//1st column
			sheet.setColumnWidth(intcol,3500);
			intcol++;
			HSSFRichTextString strClaDes = new HSSFRichTextString("Component/Clause Description");
			createCell(row,cellHeadStyleCenter ,HSSFCell.CELL_TYPE_STRING, intcol,strClaDes);//2nd column
			strClaDes.applyFont(0, 9, headFont);
			strClaDes.applyFont(10, 27, ClaFont);
			sheet.setColumnWidth(intcol,6300);
			intcol++;
			createCell(row,cellHeadStyleCenter ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString("EDL No"));//3rd column
			sheet.setColumnWidth(intcol,3000);
			intcol++;
			createCell(row,cellHeadStyleCenter ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString("Ref EDL No"));//4th column
			sheet.setColumnWidth(intcol,3000);
			intcol++;
			LogUtil.logMessage("intcol " +intcol);
			LogUtil.logMessage("rowCount " +rowCount);
			nFirstOrdCnt++;
			}
			
			ArrayList arlEDLCompareList = new ArrayList();
			ArrayList arlSectionList = new ArrayList();
			ArrayList arlSubsectionList = new ArrayList();
			ArrayList arlClauseList = new ArrayList();
			ArrayList subsecList = new ArrayList();
						
			Iterator listEDLCmpreList = null;
			Iterator ListsectionList = null;
			Iterator ListSubsectionList = null;
			Iterator ListClauseList = null;
			Iterator subSectionListIterator= null;
			Iterator ListClaList= null;
			intcol= intcol-4;
			int nColSpace = (arlSelectedList.size()*4)-1;
			if(arlSelectedList.size() > 1)
				nDynamicColCnt = (arlSelectedList.size()-1)*4;
			
			listEDLCmpreList = arlFinalList.iterator();
			while (listEDLCmpreList.hasNext()) {
				arlEDLCompareList = new ArrayList();
				arlEDLCompareList = (ArrayList)listEDLCmpreList.next();
				LogUtil.logMessage("arlEDLCompareList.size() : "+ arlEDLCompareList.size());
				
				ListsectionList = arlEDLCompareList.iterator();
				while(ListsectionList.hasNext())
				{
					SectionVO objMainSectionVO = new SectionVO();
					objMainSectionVO = (SectionVO) ListsectionList.next();
					arlSectionList = (ArrayList)objMainSectionVO.getSubSec();
					if (arlSectionList != null)	{
						LogUtil.logMessage("arlSectionList.size() : "+ arlSectionList.size());
						String[] strSecNames= objMainSectionVO.getSecNames();
						
						for(int i=0;i<strSecNames.length;i++){
							LogUtil.logMessage("nSecColCnt " +nSecColCnt);
							LogUtil.logMessage("rowCount " +rowCount);
							if(nSecColCnt == 0){
								rowCount++;
								row = sheet.createRow(rowCount);
								createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, nSecColCnt,new HSSFRichTextString(""));
								sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),nSecColCnt,nColSpace));
								rowCount++;
								row = sheet.createRow(rowCount);
							}else
								row = sheet.getRow(rowCount);
							LogUtil.logMessage("strSecNames[i] "+ strSecNames[i]);
							if(strSecNames[i] != null || strSecNames[i] != ""){
								createCell(row,cellHeadStyleCenter ,HSSFCell.CELL_TYPE_STRING, nSecColCnt,new HSSFRichTextString(strSecNames[i]));
								sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),nSecColCnt,nSecColCnt+3));
							}
							if (arlSelectedList.size() > 1){ 
								if(nSecColCnt == nDynamicColCnt) 
									nSecColCnt = 0;
								else  
									nSecColCnt = nSecColCnt + 4;
							}
						}
						ListSubsectionList = arlSectionList.iterator();
						while(ListSubsectionList.hasNext()){
							subsecList = new ArrayList();
							subsecList = (ArrayList)ListSubsectionList.next();
							//LogUtil.logMessage("subsecList.size() : "+ subsecList.size());
							
							subSectionListIterator = subsecList.iterator();
							
							while(subSectionListIterator.hasNext()){
							SubSectionVO objMainSubSecVo = new SubSectionVO();
							objMainSubSecVo = (SubSectionVO) subSectionListIterator.next();
											
							arlSubsectionList = (ArrayList)objMainSubSecVo.getClauseGroup();
		
							LogUtil.logMessage("arlSubsectionList.size() : "+ arlSubsectionList.size());
							String[] strSubsecNames= objMainSubSecVo.getSubSecNames();
							
							for(int i=0;i<strSubsecNames.length;i++){
								LogUtil.logMessage("strSubsecNames[i] "+ strSubsecNames[i]);
								LogUtil.logMessage("nSecColCnt " +nSecColCnt);
								if(nSecColCnt == 0){
									rowCount++;
									row = sheet.createRow(rowCount);
									createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, nSecColCnt,new HSSFRichTextString(""));
									sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),nSecColCnt,nColSpace));
									rowCount++;
									row = sheet.createRow(rowCount);
									LogUtil.logMessage("rowCount " +rowCount);
								}else{
									row = sheet.getRow(rowCount);
							    }
								if(strSubsecNames[i] != null || strSubsecNames[i] != ""){
									/*createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, nSecColCnt,new HSSFRichTextString(""));
									sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),nSecColCnt,nSecColCnt+3));
									rowCount++;
									row = sheet.createRow(rowCount);*/
									createCell(row,cellHeadStyleCntr ,HSSFCell.CELL_TYPE_STRING, nSecColCnt,new HSSFRichTextString(strSubsecNames[i]));
									sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),nSecColCnt,nSecColCnt+3));
								}	
								if (arlSelectedList.size() > 1){ 
									if(nSecColCnt == nDynamicColCnt)  
										nSecColCnt = 0;
									else
										nSecColCnt = nSecColCnt + 4;
								}
								
							}
							ListClauseList = arlSubsectionList.iterator();
							while(ListClauseList.hasNext()){
								arlClauseList = new ArrayList();
								arlClauseList = (ArrayList)ListClauseList.next();
								//LogUtil.logMessage("arlClauseList.size() : "+ arlClauseList.size());
								
								ListClaList = arlClauseList.iterator();
								nDynamicClauseColCnt = arlSelectedList.size()*4;
								while(ListClaList.hasNext()){
									//LogUtil.logMessage("b4");
									ClauseVO objClaVO = new ClauseVO();
									objClaVO = (ClauseVO) ListClaList.next();
									
									//arlClaList = (ArrayList)ListClaList.next();
									LogUtil.logMessage("objClaVO.getClauseNum() : "+ objClaVO.getClauseNum());
									LogUtil.logMessage("objClaVO.getEdlNum() : "+ objClaVO.getEdlNum());
									LogUtil.logMessage("objClaVO.getRefEdlNum() : "+ objClaVO.getRefEdlNum());
									LogUtil.logMessage("nSecColCnt " +nSecColCnt);
									LogUtil.logMessage("rowCount " +rowCount);
									if(nSecColCnt == 0){
										rowCount++;
										row = sheet.createRow(rowCount);
										LogUtil.logMessage("rowCount " +rowCount);
									}else{
										row = sheet.getRow(rowCount);
								    }
									createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, nSecColCnt,new HSSFRichTextString(objClaVO.getClauseNum()));
									nSecColCnt++;
									
									if(objClaVO.getClauseDesc()!= null){
										createCell(row,cellClauseDescStyle ,HSSFCell.CELL_TYPE_STRING, nSecColCnt,new HSSFRichTextString(objClaVO.getClauseDesc()));
										nSecColCnt++;
									}
									else{
										createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, nSecColCnt,new HSSFRichTextString(objClaVO.getComponentName()));
										nSecColCnt++;
									}
									if(objClaVO.getFlag()== "Y")
										createCell(row,cellLightGreenStyle ,HSSFCell.CELL_TYPE_STRING, nSecColCnt,new HSSFRichTextString(objClaVO.getEdlNum()));
									else
										createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, nSecColCnt,new HSSFRichTextString(objClaVO.getEdlNum()));
									nSecColCnt++;
									
									createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, nSecColCnt,new HSSFRichTextString(objClaVO.getRefEdlNum()));
									nSecColCnt++;
									if(nSecColCnt == nDynamicClauseColCnt)  
										nSecColCnt = 0;
								
								}
						
							}	
						}	
					}
				} 
			  }
			}
			}
			
			
			 	objHttpServletResponse.setContentType("application/vnd.ms-excel");
				objHttpServletResponse.setHeader("Content-disposition", "attachment; filename= EDLComparisonReport.xls");
				OutputStream out = null;
				out = objHttpServletResponse.getOutputStream();
				workBook.write(out);		
			
		} catch (Exception objExp) {
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		if (strForward.equalsIgnoreCase(ApplicationConstants.FAILURE))
		return objActionMapping.findForward(strForward);
		else
			return null;
	}
	
	/***************************************************************************
	 * compareEDL Method is used to compare the EDL number for the Selected
	 * Orders Added For LSDB_CR-75 on 22-May-09
	 * 
	 * @param ArrayList
	 * @param ArrayList
	 **************************************************************************/
	
	private ArrayList compareEDL(ArrayList arlFirstOrder,
			ArrayList arlRemainOrder) throws Exception {
		
		ArrayList arlremainorder = null;
		ArrayList arlClause = new ArrayList();
		ArrayList arlConstrut = new ArrayList();
		ArrayList arlWhole = new ArrayList();
		LogUtil.logMessage("Entering ComponentCompareAction.compareEDL");
		try {
			
			for (int secCount = 0; secCount < arlFirstOrder.size(); secCount++) {

				SectionVO objSectionVo = (SectionVO) arlFirstOrder
				.get(secCount);

				ArrayList arlSubSection = (ArrayList) objSectionVo.getSubSec();

				for (int subSecCount = 0; subSecCount < arlSubSection.size(); subSecCount++) {
					SubSectionVO objSubSectionVo = (SubSectionVO) arlSubSection.get(subSecCount);
					
					ArrayList arlClauseVo = (ArrayList) objSubSectionVo.getClauseGroup();
					
					if (arlClauseVo != null) {

						for (int clauseCount = 0; clauseCount < arlClauseVo.size(); clauseCount++) {
							
							ClauseVO objClauseVO = (ClauseVO) arlClauseVo
							.get(clauseCount);
							
							if (objClauseVO != null) {

								String strEdlNo = objClauseVO.getEdlNum();
								
								arlremainorder = construtRemainingOrder(
										arlRemainOrder, strEdlNo);
								
								String strEDLExist = (String) arlremainorder
								.get(arlremainorder.size() - 1);
								
								LogUtil.logMessage(objClauseVO.getClauseNum() + " " + strEdlNo);
								
								if (strEDLExist != null
										&& "Y".equalsIgnoreCase(strEDLExist)) {
									
									objClauseVO.setFlag("Y");
								}
							}
							arlClause.add(objClauseVO);
						}
					}
					objSubSectionVo.setClauseGroup(arlClause);
					arlClause = new ArrayList();
				}
				arlConstrut.add(objSectionVo);
				objSectionVo = null;
				
			}
			/** * Modified for LSDB_CR-74 on 14-July-09 * */
			arlWhole.add(arlConstrut);
			if (arlremainorder != null) {
				arlremainorder.remove(arlremainorder.size() - 1);
				arlWhole.add(arlremainorder);
			} else {
				arlWhole.add(new ArrayList());
			}
			
		} catch (Exception objException) {
			
			throw objException;
		}
		
		return arlWhole;
	}
	
	/***************************************************************************
	 * construtRemainingOrder Method is used to set the flag in ClauseVo and
	 * reconstruct it back. Added For LSDB_CR-75 on 22-May-09
	 * 
	 * @param ArrayList
	 * @param String
	 **************************************************************************/
	public ArrayList construtRemainingOrder(ArrayList arlRemainOrder,
			String strEldNo) throws Exception {
		
		ArrayList arlConstrut = new ArrayList();
		ArrayList arlClause = new ArrayList();
		String strEDLExist = "N";
		LogUtil
		.logMessage("Entering ComponentCompareAction.construtRemainingOrder");
		try {
			
			for (int secCount = 0; secCount < arlRemainOrder.size(); secCount++) {

				SectionVO objSectionVO = (SectionVO) arlRemainOrder
				.get(secCount);

				ArrayList arlSubSection = (ArrayList) objSectionVO.getSubSec();
				
				for (int subSecCount = 0; subSecCount < arlSubSection.size(); subSecCount++) {
					
					SubSectionVO objSubSectionVO = (SubSectionVO) arlSubSection.get(subSecCount);

					ArrayList arlclauseVo = (ArrayList) objSubSectionVO.getClauseGroup();
					
					for (int clauseCount = 0; clauseCount < arlclauseVo.size(); clauseCount++) {
						
						ClauseVO objClauseVO = (ClauseVO) arlclauseVo
						.get(clauseCount);
						if (objClauseVO != null) {
							String strRemainEdlNo = objClauseVO.getEdlNum();

							LogUtil.logMessage(objClauseVO.getClauseNum() + " " + strRemainEdlNo);
							
							if (strEldNo != null
									&& strRemainEdlNo != null
									&& strEldNo.trim()
									.equals(strRemainEdlNo.trim())) {
								
								objClauseVO.setFlag("Y");
								strEDLExist = "Y";
							}
						}
						arlClause.add(objClauseVO);
						}
					objSubSectionVO.setClauseGroup(arlClause);
					arlClause = new ArrayList();
				}
				arlConstrut.add(objSectionVO);
				objSectionVO = null;
			}
			arlConstrut.add(strEDLExist);
		} catch (Exception objException) {
			
			throw objException;
			
		}
		
		return arlConstrut;
	}
	
	public void createHeadingForEDLComparison(HSSFWorkbook workBook,HSSFRow row,HSSFSheet sheet,int colWidth) 
	throws Exception{
	
	HSSFCellStyle cellTitleHeadStyle = workBook.createCellStyle();
	
	HSSFFont headFont = workBook.createFont();
	
	headFont.setFontName(HSSFFont.FONT_ARIAL);
	headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	headFont.setColor(HSSFColor.BLACK.index);	
	headFont.setFontHeightInPoints((short) 10);
	
	cellTitleHeadStyle.setWrapText(true);
	cellTitleHeadStyle.setFont(headFont);
	cellTitleHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	cellTitleHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	
	
	/*final String[] columnHeadings = { "Component Comparison/Report"};
	
	createCell(row,cellTitleHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString(columnHeadings[0]));
	sheet.addMergedRegion(new CellRangeAddress(0,0,0,colWidth-1)); */
	
	for (int intPos=0;intPos < colWidth; intPos++)
	{
		 sheet.setColumnWidth(intPos,5000);
	}
}
}