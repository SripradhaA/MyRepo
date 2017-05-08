package com.EMD.LSDB.action.MasterMaintenance;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This Action class is used to fetch orders and displays the
 *          clause comparison details in pop up page and excel.
 ******************************************************************************/

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.EMD.LSDB.action.common.EMDAction;
import com.EMD.LSDB.action.common.PDFView;
import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.CustomerBI;
import com.EMD.LSDB.bo.serviceinterface.FileUploadBI;
import com.EMD.LSDB.bo.serviceinterface.ModelAppendixBI;
import com.EMD.LSDB.bo.serviceinterface.ModelBI;
import com.EMD.LSDB.bo.serviceinterface.ModelSubSectionBI;
import com.EMD.LSDB.bo.serviceinterface.OrderBI;
import com.EMD.LSDB.bo.serviceinterface.OrderSectionBI;
import com.EMD.LSDB.bo.serviceinterface.SectionBI;
import com.EMD.LSDB.bo.serviceinterface.SpecTypeBI;
import com.EMD.LSDB.bo.serviceinterface.ViewSpecByModelBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.dao.SpecMaintenance.OrderSectionDAO;
import com.EMD.LSDB.form.MasterMaintenance.MasterSpecByMdlForm;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.CompGroupVO;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.CustomerVO;
import com.EMD.LSDB.vo.common.FileVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.ModelAppendixVO;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SpecTypeVo;
import com.EMD.LSDB.vo.common.SubSectionVO;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.Region ;
import org.apache.poi.hssf.util.HSSFColor ;

import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;


//Import for new Rtf file 
import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.field.RtfPageNumber;
import com.lowagie.text.rtf.headerfooter.RtfHeaderFooter;


public class MasterSpecByMdlAction extends EMDAction {
	
	/***************************************************************************
	 *    Date         version  create by   modify by             comments                              Remarks 
	 * 19/01/2010        1.0      SD41630                 Added new mehtod for view characterisitic     Added for CR_81
	 * 													   group Report by model.   
	 * 													 	 
	 * 
	 ***************************************************************************/
	
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
	
	//Added for CR_114
	public static Font strFointSizeBoldEleven = new Font(Font.TIMES_ROMAN, 11,
			Font.BOLD, Color.BLACK);

	public static Font strFointSizeItalicElevenBlue = new Font(
			Font.TIMES_ROMAN, 11, Font.ITALIC, Color.BLUE);

	public static Font strFontSizeTen = new Font(Font.TIMES_ROMAN, 10, 0,
			Color.BLACK);
	
	public static Font strFontSizeItalicTen = new Font(Font.TIMES_ROMAN, 10, Font.ITALIC,
			Color.BLACK);

	public static Font strFontSizeBoldFourteen = new Font(Font.TIMES_ROMAN, 14,
			Font.BOLD, Color.BLACK);
	
	public static Font strFontSizeBoldTwentyFive = new Font(Font.TIMES_ROMAN, 25,
			Font.BOLD, Color.BLACK);

	public static Font strFontSizeBoldUnderlineEleven = new Font(
			Font.TIMES_ROMAN, 11, Font.BOLD, Color.BLACK);
	
	public static Font strFontSizeBoldRedEleven = new Font(
			Font.TIMES_ROMAN, 11, Font.BOLD, Color.RED);
	
	
	public static Font footerFont = new Font(Font.TIMES_ROMAN, 9, 0,
			Color.BLACK);
	
	public static Font footerFontBold = new Font(Font.TIMES_ROMAN, 9, Font.BOLD,
			Color.BLACK);

	//Added for CR_114 Ends
	
	
	public ActionForward initLoad(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering ClauseCompareAction.initLoad");
		String strForward = ApplicationConstants.SUCCESS;
		MasterSpecByMdlForm objMasterSpecByMdlForm = (MasterSpecByMdlForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		try {
			ArrayList arlCustomerList = new ArrayList();
			ArrayList arlSpec = new ArrayList();
			
			/*ModelVo objModelVO = new ModelVo();
			 objModelVO.setUserID(objLoginVo.getUserID());
			 ArrayList arlModelList = new ArrayList();
			 ModelBI objModelBO = ServiceFactory.getModelBO();
			 arlModelList = objModelBO.fetchModels(objModelVO);
			 objMasterSpecByMdlForm.setModelList(arlModelList);*/
			
			/****Added for CR-46 PM&I Spec for loading Specification Type Drop Down - Starts Here****/
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			
			objMasterSpecByMdlForm.setSpecTypeList(arlSpec);
			objMasterSpecByMdlForm.setSpecTypeNo(objMasterSpecByMdlForm
					.getSpecTypeNo());
			
			/****Added for CR-46 PM&I Spec for loading Specification Type Drop Down - Ends Here****/
			
			CustomerVO objCustomerVO = new CustomerVO();
			objCustomerVO.setUserID(objLoginVo.getUserID());
			
			CustomerBI objCustomerBO = ServiceFactory.getCustomerBO();
			arlCustomerList = objCustomerBO.fetchCustomers(objCustomerVO);
			
			objMasterSpecByMdlForm.setCustomerList(arlCustomerList);
			
		} catch (Exception objExp) {
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
		
	}
	
	/***************************************************************************
	 * This Method is used to load Models and Customers based on the Specification Type from
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
	
	public ActionForward fetchModelsAndCustomers(
			ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil
		.logMessage("Entering ClauseCompareAction.fetchModelsAndCustomers");
		String strForward = ApplicationConstants.SUCCESS;
		MasterSpecByMdlForm objMasterSpecByMdlForm = (MasterSpecByMdlForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		try {
			ArrayList arlCustomerList = new ArrayList();
			ArrayList arlSpec = new ArrayList();
			
			/****Added for CR-46 PM&I Spec for loading Specification Type Drop Down - Starts Here****/
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			
			objMasterSpecByMdlForm.setSpecTypeList(arlSpec);
			objMasterSpecByMdlForm.setSpecTypeNo(objMasterSpecByMdlForm
					.getSpecTypeNo());
			
			/****Added for CR-46 PM&I Spec for loading Specification Type Drop Down - Ends Here****/
			
			ModelVo objModelVO = new ModelVo();
			objModelVO.setUserID(objLoginVo.getUserID());
			
			//Added for CR-46 PM&I Spec load Models and Customer based on Specification Type
			objModelVO.setSpecTypeSeqNo(objMasterSpecByMdlForm.getSpecTypeNo());
			
			ArrayList arlModelList = new ArrayList();
			ModelBI objModelBO = ServiceFactory.getModelBO();
			arlModelList = objModelBO.fetchModels(objModelVO);
			objMasterSpecByMdlForm.setModelList(arlModelList);
			
			CustomerVO objCustomerVO = new CustomerVO();
			objCustomerVO.setUserID(objLoginVo.getUserID());
			
			//Added for CR-46 PM&I Spec
			objCustomerVO.setSpecTypeSeqNo(objMasterSpecByMdlForm
					.getSpecTypeNo());
			
			CustomerBI objCustomerBO = ServiceFactory.getCustomerBO();
			arlCustomerList = objCustomerBO.fetchCustomers(objCustomerVO);
			
			objMasterSpecByMdlForm.setCustomerList(arlCustomerList);
			
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
	
	public ActionForward fetchOrdersForMasterSpecReport(
			ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil
		.logMessage("Entering fetchOrdersForMasterSpecReport.fetchOrders");
		String strForward = ApplicationConstants.SUCCESS;
		MasterSpecByMdlForm objMasterSpecByMdlForm = (MasterSpecByMdlForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			OrderVO objOrderVO = new OrderVO();
			ArrayList arlOrderList = new ArrayList();
			String strUserID = ApplicationConstants.EMPTY_STRING;
			ArrayList arlSpec = new ArrayList();
			
			/** Getting User from the session */
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			
			/****Added for CR-46 PM&I Spec for loading Specification Type Drop Down - Starts Here****/
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			
			objMasterSpecByMdlForm.setSpecTypeList(arlSpec);
			objMasterSpecByMdlForm.setSpecTypeNo(objMasterSpecByMdlForm
					.getSpecTypeNo());
			
			/****Added for CR-46 PM&I Spec for loading Specification Type Drop Down - Ends Here****/
			
			ModelVo objModelVO = new ModelVo();
			objModelVO.setUserID(objLoginVo.getUserID());
			
			CustomerVO objCustomerVO = new CustomerVO();
			objCustomerVO.setUserID(objLoginVo.getUserID());
			
			//Added for CR-46 PM&I Spec
			objModelVO.setSpecTypeSeqNo(objMasterSpecByMdlForm.getSpecTypeNo());
			objCustomerVO.setSpecTypeSeqNo(objMasterSpecByMdlForm
					.getSpecTypeNo());
			
			ArrayList arlModelList1 = new ArrayList();
			ArrayList arlCustomerList = new ArrayList();
			
			//Fetch Models
			ModelBI objModelBO = ServiceFactory.getModelBO();
			arlModelList1 = objModelBO.fetchModels(objModelVO);
			
			//Fetch Customers
			CustomerBI objCustomerBO = ServiceFactory.getCustomerBO();
			arlCustomerList = objCustomerBO.fetchCustomers(objCustomerVO);
			
			objMasterSpecByMdlForm.setModelList(arlModelList1);
			objMasterSpecByMdlForm.setCustomerList(arlCustomerList);
			objMasterSpecByMdlForm
			.setHdnSelectedMdlNames(objMasterSpecByMdlForm
					.getHdnSelectedMdlNames());
			objMasterSpecByMdlForm
			.setHdnSelectedCustomers(objMasterSpecByMdlForm
					.getHdnSelectedCustomers());
			//Fetch Orders    
			objOrderVO.setModelSeqNo(objMasterSpecByMdlForm.getModelSeqNo());
			objOrderVO.setCustSeqNos(objMasterSpecByMdlForm.getCustomerSeq());
			
			objOrderVO.setUserID(strUserID);
			
			OrderBI objOrderBo = ServiceFactory.getOrderBO();
			arlOrderList = objOrderBo.fetchOrdersModifySpec(objOrderVO);
			
			if (arlOrderList.size() > 0) {
				
				objMasterSpecByMdlForm.setOrderList(arlOrderList);
			}
			objMasterSpecByMdlForm
			.setHdnSelectedMdlNames(objMasterSpecByMdlForm
					.getHdnSelectedMdlNames());
			objMasterSpecByMdlForm
			.setHdnSelectedCustomers(objMasterSpecByMdlForm
					.getHdnSelectedCustomers());
			objMasterSpecByMdlForm.setModelSeqNo(objMasterSpecByMdlForm
					.getModelSeqNo());
			objMasterSpecByMdlForm.setCustSeqNos(objMasterSpecByMdlForm
					.getCustSeqNos());
			objMasterSpecByMdlForm.setSpecTypeNo(objMasterSpecByMdlForm
					.getSpecTypeNo());
			objMasterSpecByMdlForm.setHdnSelSpecType(objMasterSpecByMdlForm
					.getHdnSelSpecType());
			
		} catch (Exception objExp) {
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
		
	}
	
	/***********************************************************************************************************
	 * This method is to used retrieve the Sections, Subsections Clauses for the selected model 
	 * and show it in the Pop up screen
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException,BusinessException
	 ************************************************************************************************************/
	
	public ActionForward viewMasterSpecByModel(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		
		/** Declarations **/
		LogUtil
		.logMessage("Inside MasterSpecBymdlAction:viewMasterSpecByModel");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList sectionList = new ArrayList();
		ArrayList subSectionList;
		ArrayList specList;
		ArrayList modelSpecList = new ArrayList();
		ArrayList modelSubsectionList = new ArrayList();
		ArrayList compList 	 = new ArrayList();
		ArrayList ordList 	 = new ArrayList(); //Added for CR-130
		ArrayList emptyList  = new ArrayList(); 
		int modelSeqNo;
		String modelName = null;
		String orderkeys = null;
		String strExporttoExcel = null; //Added for CR-130
		String strClauseDesc = null;//Added for CR-130
		int intPos = 0;//Added for CR-130
		
		//Added for CR-46 PM&I Spec
		String specType = null;
		
		MasterSpecByMdlForm objMasterSpecByMdlForm = (MasterSpecByMdlForm) objActionForm;
		
		/** Getting the Modelsequence Number and Model name from the request parameter **/
		
		modelSeqNo = Integer.parseInt(objHttpServletRequest
				.getParameter("modelSeqNo"));
		modelName = objHttpServletRequest.getParameter("modelName");
		
		//Added for CR-46 PM&I Spec
		specType = objHttpServletRequest
		.getParameter(ApplicationConstants.SELECTED_SPEC_TYPE);
		
		orderkeys = objHttpServletRequest.getParameter("orderkeys");
		strExporttoExcel = objHttpServletRequest.getParameter("action"); //Added for CR-130
		LogUtil.logMessage("orderkeys :" + orderkeys);
		LogUtil.logMessage("strExporttoExcel :" + strExporttoExcel);
		
		int intLen = 0;
		LogUtil.logMessage("Model Seq No :" + modelSeqNo);
		LogUtil.logMessage("ModelName :" + modelName);
		LogUtil.logMessage("orderKeys :" + orderkeys);
		
		ClauseVO objClauseVO = new ClauseVO();//Added for CR-130
		ComponentVO objcomponentVO = new ComponentVO();//Added for CR-130
		CompGroupVO objcompGroupVO = new CompGroupVO();//Added for CR-130
		OrderVO objOrderVO 		   = new OrderVO();;//Added for CR-130
		
		SectionVO objSectionVO = new SectionVO();
		SubSectionVO objSubSecMaintVO = new SubSectionVO();
		objSectionVO.setModelSeqNo(modelSeqNo);
		objSectionVO.setModelName(modelName);
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		try {
			/** Setting the user Id **/
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			objSectionVO.setUserID(objLoginVo.getUserID());
			
			//Added for PM&I
			objMasterSpecByMdlForm.setHdnSelSpecType(objMasterSpecByMdlForm
					.getHdnSelSpecType());
			
			/** Fetching the sections available for the selected model **/
			
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			sectionList = objSectionBO.fetchSections(objSectionVO);
			Iterator listIterator = null;
			Iterator subSectionListIterator = null; //Added for CR-130
			Iterator specListIterator		= null; //Added for CR-130
			Iterator compListIterator		= null; //Added for CR-130
			Iterator ordListIterator		= null; //Added for CR-130
			
			/** For each section get the subsections and clauses **/
			 // Added for CR-130
				//create workbook
				HSSFWorkbook workBook = new HSSFWorkbook();	
				int nHeadcount = 0; 
				int nrowCount = 0;
				HSSFSheet sheet = null;
				if(orderkeys != null && !orderkeys.trim().equalsIgnoreCase(""))
					sheet = workBook.createSheet("Spec By Customer Report");
				 else
				    sheet = workBook.createSheet("Spec By Model Report");
				HSSFRow row = sheet.createRow(nrowCount);
				 // Added for CR-130 ends here	 
			
			if (sectionList.size() != 0) {
				
				// Added for CR-130 Starts here	 
				
				HSSFCellStyle cellHeadStyle      		= workBook.createCellStyle();
				HSSFCellStyle cellNormalStyle    		= workBook.createCellStyle();
				HSSFCellStyle cellSectionStyle   		= workBook.createCellStyle();
				HSSFCellStyle cellSubSectionStyle		= workBook.createCellStyle();
				HSSFCellStyle cellHeadingStyle         	= workBook.createCellStyle();
				HSSFCellStyle cellFirstRowStyle  	   	= workBook.createCellStyle();
				HSSFCellStyle cellFirstRowClauseStyle  	= workBook.createCellStyle();
				HSSFCellStyle cellFirstRowItalicStyle  	= workBook.createCellStyle();
				HSSFCellStyle cellSecondRowStyle 		= workBook.createCellStyle();
				HSSFCellStyle cellSecondRowClauseStyle 	= workBook.createCellStyle();
				HSSFCellStyle cellSecondRowItalicStyle 	= workBook.createCellStyle();
				HSSFCellStyle cellItalicStyle 	 		= workBook.createCellStyle();
				HSSFCellStyle cellRedStyle 		 		= workBook.createCellStyle();
				HSSFCellStyle cellItalicRedStyle 		= workBook.createCellStyle();
				HSSFCellStyle cellTabHeadStyle 			= workBook.createCellStyle();
				HSSFCellStyle cellFirstRowTabHeadStyle	= workBook.createCellStyle();
				HSSFCellStyle cellSecondRowTabHeadStyle = workBook.createCellStyle();
				
				HSSFFont otherFont = workBook.createFont();			
				otherFont.setFontName(HSSFFont.FONT_ARIAL);
				otherFont.setColor(HSSFColor.BLACK.index);		
				otherFont.setFontHeightInPoints((short) 10);
				
				HSSFFont italicFont = workBook.createFont();			
				italicFont.setFontName(HSSFFont.FONT_ARIAL);
				italicFont.setColor(HSSFColor.BLACK.index);		
				italicFont.setFontHeightInPoints((short) 10);
				italicFont.setItalic(true);
				
				cellNormalStyle.setWrapText(true);
				cellNormalStyle.setFont(otherFont);
				cellNormalStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellNormalStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				
				cellFirstRowStyle.setWrapText(true);
				cellFirstRowStyle.setFont(otherFont);
				cellFirstRowStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellFirstRowStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				
				HSSFPalette firstpalette = ((HSSFWorkbook)workBook).getCustomPalette(); 
				firstpalette.setColorAtIndex((short)60, (byte)233, (byte)233, (byte)233); 
				cellFirstRowStyle.setFillForegroundColor(firstpalette.getColor(60).getIndex()); 
				cellFirstRowStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				LogUtil.logMessage("after cellFirstRowStyle :" );
				
				cellFirstRowClauseStyle.setWrapText(true);
				cellFirstRowClauseStyle.setFont(otherFont);
				cellFirstRowClauseStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				cellFirstRowClauseStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
				
				HSSFPalette firstRowClausepalette = ((HSSFWorkbook)workBook).getCustomPalette(); 
				firstRowClausepalette.setColorAtIndex((short)61, (byte)233, (byte)233, (byte)233); 
				cellFirstRowClauseStyle.setFillForegroundColor(firstRowClausepalette.getColor(61).getIndex()); 
				cellFirstRowClauseStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				LogUtil.logMessage("after cellFirstRowClauseStyle :" );
				
				cellFirstRowItalicStyle.setWrapText(true);
				cellFirstRowItalicStyle.setFont(italicFont);
				cellFirstRowItalicStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellFirstRowItalicStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				
				HSSFPalette firstRowItalicpalette = ((HSSFWorkbook)workBook).getCustomPalette(); 
				firstRowItalicpalette.setColorAtIndex((short)62, (byte)233, (byte)233, (byte)233); 
				cellFirstRowItalicStyle.setFillForegroundColor(firstRowItalicpalette.getColor(62).getIndex()); 
				cellFirstRowItalicStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				LogUtil.logMessage("after cellFirstRowItalicStyle :" );
				
				
				cellSecondRowStyle.setWrapText(true);
				cellSecondRowStyle.setFont(otherFont);
				cellSecondRowStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellSecondRowStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				
				HSSFPalette secondpalette = ((HSSFWorkbook)workBook).getCustomPalette(); 
				secondpalette.setColorAtIndex((short)63, (byte)205, (byte)205, (byte)205); 
				cellSecondRowStyle.setFillForegroundColor(secondpalette.getColor(63).getIndex()); 
				cellSecondRowStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				LogUtil.logMessage("after cellSecondRowStyle :" );
				
				cellSecondRowClauseStyle.setWrapText(true);
				cellSecondRowClauseStyle.setFont(otherFont);
				cellSecondRowClauseStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				cellSecondRowClauseStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
				
				HSSFPalette SecondRowClausepalette = ((HSSFWorkbook)workBook).getCustomPalette(); 
				SecondRowClausepalette.setColorAtIndex((short)54, (byte)205, (byte)205, (byte)205); 
				cellSecondRowClauseStyle.setFillForegroundColor(SecondRowClausepalette.getColor(54).getIndex()); 
				cellSecondRowClauseStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				LogUtil.logMessage("after cellSecondRowClauseStyle :" );
				
				cellSecondRowItalicStyle.setWrapText(true);
				cellSecondRowItalicStyle.setFont(italicFont);
				cellSecondRowItalicStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellSecondRowItalicStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				
				HSSFPalette SecondRowItalicpalette = ((HSSFWorkbook)workBook).getCustomPalette(); 
				SecondRowItalicpalette.setColorAtIndex((short)55, (byte)205, (byte)205, (byte)205); 
				cellSecondRowItalicStyle.setFillForegroundColor(SecondRowItalicpalette.getColor(55).getIndex()); 
				cellSecondRowItalicStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				LogUtil.logMessage("after cellSecondRowItalicStyle :" );
				
				HSSFFont claTabFont = workBook.createFont();			
				claTabFont.setFontName(HSSFFont.FONT_ARIAL);
				claTabFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				claTabFont.setColor(HSSFColor.BLACK.index);		
				claTabFont.setFontHeightInPoints((short) 10);
				
				cellTabHeadStyle.setFont(claTabFont);
				cellTabHeadStyle.setWrapText(true);
				cellTabHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellTabHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				
				cellFirstRowTabHeadStyle.setFont(claTabFont);
				cellFirstRowTabHeadStyle.setWrapText(true);
				cellFirstRowTabHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellFirstRowTabHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				
				HSSFPalette FirstRowTabheadpalette = ((HSSFWorkbook)workBook).getCustomPalette(); 
				FirstRowTabheadpalette.setColorAtIndex((short)40, (byte)233, (byte)233, (byte)233); 
				cellFirstRowTabHeadStyle.setFillForegroundColor(FirstRowTabheadpalette.getColor(40).getIndex()); 
				cellFirstRowTabHeadStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				
				cellSecondRowTabHeadStyle.setFont(claTabFont);
				cellSecondRowTabHeadStyle.setWrapText(true);
				cellSecondRowTabHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellSecondRowTabHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				
				HSSFPalette SecondRowTabheadpalette = ((HSSFWorkbook)workBook).getCustomPalette(); 
				SecondRowTabheadpalette.setColorAtIndex((short)41, (byte)205, (byte)205, (byte)205); 
				cellSecondRowTabHeadStyle.setFillForegroundColor(SecondRowTabheadpalette.getColor(41).getIndex()); 
				cellSecondRowTabHeadStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				
				HSSFFont italicRedFont = workBook.createFont();			
				italicRedFont.setFontName(HSSFFont.FONT_ARIAL);
				italicRedFont.setColor(HSSFColor.RED.index);		
				italicRedFont.setFontHeightInPoints((short) 10);
				italicRedFont.setItalic(true);
				
				cellItalicRedStyle.setWrapText(true);
				cellItalicRedStyle.setFont(italicRedFont);
				cellItalicRedStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellItalicRedStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				
				cellItalicStyle.setWrapText(true);
				cellItalicStyle.setFont(italicFont);
				cellItalicStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellItalicStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				
				LogUtil.logMessage("after cellItalicStyle :" );
				
				
				HSSFFont redFont = workBook.createFont();			
				redFont.setFontName(HSSFFont.FONT_ARIAL);
				redFont.setColor(HSSFColor.RED.index);		
				redFont.setFontHeightInPoints((short) 10);
				redFont.setItalic(true);
				
				cellRedStyle.setWrapText(true);
				cellRedStyle.setFont(redFont);
				cellRedStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellRedStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				
				
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
				LogUtil.logMessage("after cellSectionStyle :" );
				
				cellSubSectionStyle.setWrapText(true);
				cellSubSectionStyle.setFont(headFont);
				cellSubSectionStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellSubSectionStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				
				HSSFPalette subSecPalette = ((HSSFWorkbook)workBook).getCustomPalette(); 
				subSecPalette.setColorAtIndex((short)50, (byte)210, (byte)221, (byte)249); 
				cellSubSectionStyle.setFillForegroundColor(subSecPalette.getColor(50).getIndex()); 
				cellSubSectionStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				LogUtil.logMessage("after cellSubSectionStyle :" );
				
				cellHeadingStyle.setWrapText(true);
				cellHeadingStyle.setFont(headFont);
				cellHeadingStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellHeadingStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				
				HSSFPalette headPalette = ((HSSFWorkbook)workBook).getCustomPalette(); 
				headPalette.setColorAtIndex((short)51, (byte)162, (byte)192, (byte)224); 
				cellHeadingStyle.setFillForegroundColor(headPalette.getColor(51).getIndex()); 
				cellHeadingStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				LogUtil.logMessage("after cellHeadingStyle :" );
				
				cellHeadingStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				cellHeadingStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
				cellHeadingStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
				cellHeadingStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				
				HSSFCellStyle cellClauseStyle = workBook.createCellStyle();
				HSSFCellStyle cellOtherStyle   = workBook.createCellStyle();
				
				cellOtherStyle.setFont(otherFont);
				cellOtherStyle.setWrapText(true);
				cellOtherStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellOtherStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				
				cellClauseStyle.setFont(otherFont);
				cellClauseStyle.setWrapText(true);
				cellClauseStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				cellClauseStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
				
				
				//Added for CR-130 Ends here 	 
				
				listIterator = sectionList.iterator();
				int nSecCount = 0;
				//outerloop:
				while (listIterator.hasNext()) {
					specList = new ArrayList();
					subSectionList = new ArrayList();
					objSectionVO = new SectionVO();
					objSubSecMaintVO = new SubSectionVO();
					objSectionVO = (SectionVO) listIterator.next();
					objSubSecMaintVO.setModelSeqNo(modelSeqNo);
					objSubSecMaintVO
					.setSecSeqNo(objSectionVO.getSectionSeqNo());
					
					if (orderkeys != null && !"".equals(orderkeys)) {
						String strOrderKeys[] = orderkeys
						.split(ApplicationConstants.COMMA);
						objSubSecMaintVO.setOrderkeys(strOrderKeys);
					}
					
					objSubSecMaintVO.setUserID(objLoginVo.getUserID());
					
					/** Get the subsection list for  current section **/
					
					ModelSubSectionBI objSubSecMaintBO = ServiceFactory
					.getSubSecMaintBO();
					subSectionList = objSubSecMaintBO
					.fetchSubSections(objSubSecMaintVO);
					subSectionListIterator = subSectionList.iterator();  //Added for CR-130
					
					/** Get the clauses for all the subsections in the current section **/
					
					ViewSpecByModelBI objViewSpecByModelBO = ServiceFactory
					.getViewSpecByModelBO();
					specList = (objViewSpecByModelBO
							.viewMasterSpecByModel(objSubSecMaintVO));
					
					/** Add the sub sections for the current section to the list **/
					
					if (subSectionList.size() != 0) {
						modelSubsectionList.add(subSectionList);
					}else{
						modelSubsectionList.add(emptyList);
					}
					
					/** Add the clauses for the subsections of the current section to the list **/
					
					modelSpecList.add(specList);
					
					LogUtil.logMessage("strExporttoExcel:" + strExporttoExcel);
					
					/* Added for CR-130 */
					if(strExporttoExcel != null ){
						
						int nSpanRows = 0;
						
						if(nHeadcount == 0){
							
						createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString(""));
						sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,9));
						nrowCount++;
						row = sheet.createRow(nrowCount);
						if(orderkeys != null && !orderkeys.trim().equalsIgnoreCase(""))
							createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString("Spec By Customer"));
						else
							createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString("Spec By Model"));
						sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,9));
						nrowCount++;
						row = sheet.createRow(nrowCount);
						createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString(""));
						sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,9));
						nrowCount++;
						row = sheet.createRow(nrowCount);
						createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString("Specification Type :"+specType+"        Model : "+modelName));
						sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,9));
						nrowCount++;
						row = sheet.createRow(nrowCount);
						createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString(""));
						sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,9));
						nrowCount++;
						row = sheet.createRow(nrowCount);
						createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString("Master Clauses : All the Clauses shown below are Master Clauses."));
						sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,9));
						nrowCount++;
						row = sheet.createRow(nrowCount);
						HSSFRichTextString richRedString = new HSSFRichTextString("Version *: Denotes the Master version/Default version used in the corresponding Order.");
						createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,0,richRedString);
						richRedString.applyFont(8,9,redFont);
						richRedString.applyFont(9,85,headFont);
						sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,9));
						nrowCount++;
						row = sheet.createRow(nrowCount);
						createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString("Components in ITALICS: Component names in ITALICS denotes that it is not a lead Component."));
						sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,9));
						nrowCount++;
						row = sheet.createRow(nrowCount);
						createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString("Tool Tip : Clause Description for each individual order shall be shown as a Tool Tip on mouse over of Clause Version."));
						sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,9));
						nrowCount++;
						row = sheet.createRow(nrowCount);
						createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString(""));
						sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,9));
						nrowCount++;
						}
						
						row = sheet.createRow(nrowCount);
						createCell(row,cellSectionStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString(objSectionVO.getSectionCode()+"."+objSectionVO.getSectionName()));
						sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,9));
						nrowCount++;
						int nSubSecCount = 0;
						
						while (subSectionListIterator.hasNext()) {
							
							LogUtil.logMessage("inside subSectionListIterator :" );
							SubSectionVO objSubSecVO = new SubSectionVO();
							objSubSecVO = (SubSectionVO) subSectionListIterator.next();
							
							if( nSubSecCount == 0){
							 row = sheet.createRow(nrowCount);
							 createCell(row,cellSubSectionStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString(objSubSecVO.getSubSecCode()+"."+objSubSecVO.getSubSecName()));
							 LogUtil.logMessage("objSubSecVO.getSubSecCode():"+objSubSecVO.getSubSecCode());
							 sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,9));
							 nrowCount++;
							 row = sheet.createRow(nrowCount);
							 final String[] columnHeadings = {"Component Group", "Component", "Default Component","Clause No", "Clause Description"
									,"Engineering Data"};
							 final int[] columnWidth = {7000,7000,6000,6000,8300,6000};
							 int nheadColCount = 0;
							 for (int nintPos=0;nintPos < 6; nintPos++)
								{
									LogUtil.logMessage("columnHeadings[intPos]: nheadColCount:"+columnHeadings[nintPos]+" "+nheadColCount);
									if(columnHeadings[nintPos] == "Clause Description") {
										createCell(row,cellHeadingStyle,HSSFCell.CELL_TYPE_STRING,nheadColCount++,new HSSFRichTextString(columnHeadings[nintPos]));	
										createCell(row,cellHeadingStyle,HSSFCell.CELL_TYPE_STRING,nheadColCount++,new HSSFRichTextString(""));
										createCell(row,cellHeadingStyle,HSSFCell.CELL_TYPE_STRING,nheadColCount++,new HSSFRichTextString(""));
										createCell(row,cellHeadingStyle,HSSFCell.CELL_TYPE_STRING,nheadColCount++,new HSSFRichTextString(""));
										createCell(row,cellHeadingStyle,HSSFCell.CELL_TYPE_STRING,nheadColCount++,new HSSFRichTextString(""));
										sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),nheadColCount-5,nheadColCount-1));
									}else{
										createCell(row,cellHeadingStyle,HSSFCell.CELL_TYPE_STRING,nheadColCount,new HSSFRichTextString(columnHeadings[nintPos]));
										sheet.setColumnWidth(nheadColCount,columnWidth[nintPos]);
										nheadColCount++;
										//if(nintPos == 5)sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),nheadColCount-1,nheadColCount));
									}
								}
							 LogUtil.logMessage("orderkeys :" + orderkeys);
							 if(orderkeys != null && !orderkeys.trim().equalsIgnoreCase("")){
								 nrowCount++;
								 row = sheet.createRow(nrowCount);
								 final String[] ordColumnHeadings  = {"Order No", "Spec Status", "Customer","Version"};
								 final int[] ordColumnWidth = {5000,5000,8000,5000};
								 int nordHeadColCount = 0;
								 for (int nordIntPos=0;nordIntPos < 4; nordIntPos++)
									{
										LogUtil.logMessage("ordColumnHeadings[nordIntPos]: nordHeadColCount:"+ordColumnHeadings[nordIntPos]+" "+nordHeadColCount);
										if(ordColumnHeadings[nordIntPos] == "Order No") {
											createCell(row,cellHeadingStyle,HSSFCell.CELL_TYPE_STRING,nordHeadColCount++,new HSSFRichTextString(ordColumnHeadings[nordIntPos]));
											createCell(row,cellHeadingStyle,HSSFCell.CELL_TYPE_STRING,nordHeadColCount++,new HSSFRichTextString(""));
											sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),nordHeadColCount-2,nordHeadColCount-1));
										}else if(ordColumnHeadings[nordIntPos] == "Customer") {
											createCell(row,cellHeadingStyle,HSSFCell.CELL_TYPE_STRING,nordHeadColCount++,new HSSFRichTextString(ordColumnHeadings[nordIntPos]));
											createCell(row,cellHeadingStyle,HSSFCell.CELL_TYPE_STRING,nordHeadColCount++,new HSSFRichTextString(""));
											createCell(row,cellHeadingStyle,HSSFCell.CELL_TYPE_STRING,nordHeadColCount++,new HSSFRichTextString(""));
											createCell(row,cellHeadingStyle,HSSFCell.CELL_TYPE_STRING,nordHeadColCount++,new HSSFRichTextString(""));
											createCell(row,cellHeadingStyle,HSSFCell.CELL_TYPE_STRING,nordHeadColCount++,new HSSFRichTextString(""));
											createCell(row,cellHeadingStyle,HSSFCell.CELL_TYPE_STRING,nordHeadColCount++,new HSSFRichTextString(""));
											sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),nordHeadColCount-6,nordHeadColCount-1));
										}else{
											createCell(row,cellHeadingStyle,HSSFCell.CELL_TYPE_STRING,nordHeadColCount,new HSSFRichTextString(ordColumnHeadings[nordIntPos]));
											sheet.setColumnWidth(nordHeadColCount,ordColumnWidth[nordIntPos]);
											nordHeadColCount++;
											//if(nintPos == 5)sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),nheadColCount-1,nheadColCount));
										}
					
				}
			}
							}
							
							int nBgColorCount = 0;
							specListIterator = specList.iterator();
							while (specListIterator.hasNext()){
							    	
								objClauseVO = new ClauseVO();
								objClauseVO = (ClauseVO) specListIterator.next();
								
								if (objClauseVO.getSubSectionSeqNo() == objSubSecVO.getSubSecSeqNo()){
								
								compList = objClauseVO.getCompGroupVO();
								compListIterator = compList.iterator();
								
								LogUtil.logMessage("row.getRowNum():"+row.getRowNum());
								LogUtil.logMessage("objcompGroupVO.getLeadFlag():"+objcompGroupVO.getLeadFlag());
								LogUtil.logMessage("nBgColorCount count:"+nBgColorCount);
								if(nBgColorCount % 2 == 0){
									cellNormalStyle  = cellFirstRowStyle; 
									cellClauseStyle  = cellFirstRowClauseStyle;
									cellItalicStyle  = cellFirstRowItalicStyle;
									cellTabHeadStyle = cellFirstRowTabHeadStyle;
								}else{
									cellNormalStyle  = cellSecondRowStyle;
									cellClauseStyle  = cellSecondRowClauseStyle;
									cellItalicStyle  = cellSecondRowItalicStyle;
									cellTabHeadStyle = cellSecondRowTabHeadStyle;
								}
								if (compList.size() != 0){
								while (compListIterator.hasNext()){
									objcompGroupVO = new CompGroupVO();
									objcomponentVO = new ComponentVO();
									
									objcompGroupVO = (CompGroupVO) compListIterator.next();
									objcomponentVO = (ComponentVO) objcompGroupVO.getCompVO();
									
									nrowCount++;
									intPos = 0;
									row = sheet.createRow(nrowCount);
									
									if(objcompGroupVO.getLeadFlag().equalsIgnoreCase("Y"))
									{												
										//if(compList.size() == 1) nSpanRows = 2;
										//if(compList.size() > 1) nSpanRows = 1;
										
										LogUtil.logMessage("objcompGroupVO.getValidFlag():"+objcompGroupVO.getValidFlag());
										HSSFRichTextString richRedString = null;
										if(objcompGroupVO.getValidFlag() != null && objcompGroupVO.getValidFlag().equalsIgnoreCase("Y")){
											richRedString = new HSSFRichTextString("*" + objcompGroupVO.getComponentGroupName());
											createCell(row,cellNormalStyle,HSSFCell.CELL_TYPE_STRING,0,richRedString);
											richRedString.applyFont(0,1,italicRedFont);
										}else{
											richRedString = new HSSFRichTextString(objcompGroupVO.getComponentGroupName());
											createCell(row,cellNormalStyle,HSSFCell.CELL_TYPE_STRING,0,richRedString);
											richRedString.applyFont(0,objcompGroupVO.getComponentGroupName().length(),italicFont);
										}	
											//createCell(row,cellItalicRedStyle ,HSSFCell.CELL_TYPE_STRING, 0,new HSSFRichTextString(objcompGroupVO.getComponentGroupName()));
										/*}else{
											createCell(row,cellItalicStyle ,HSSFCell.CELL_TYPE_STRING, 0,new HSSFRichTextString(objcompGroupVO.getComponentGroupName()));
										}*/
										
										LogUtil.logMessage("objcompGroupVO.getComponentGroupName():"+objcompGroupVO.getComponentGroupName());
										
										if(objcompGroupVO.getComponentGroupName().equalsIgnoreCase("")){
											createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, 0,new HSSFRichTextString(""));
											LogUtil.logMessage("objcompGroupVO.getLeadFlag():"+objcompGroupVO.getLeadFlag());
											LogUtil.logMessage("row.getRowNum():"+row.getRowNum());
											LogUtil.logMessage("nSpanRows:"+nSpanRows);
											//sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),nSpanRows,nSpanRows));
										}
										
										LogUtil.logMessage("objcomponentVO.getComponentName():"+objcomponentVO.getComponentName());
										
										if(!objcomponentVO.getComponentName().equalsIgnoreCase("")){
											createCell(row,cellItalicStyle ,HSSFCell.CELL_TYPE_STRING, 1,new HSSFRichTextString(objcomponentVO.getComponentName()));
										}else{
											createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, 1,new HSSFRichTextString(""));
										}
										if(objcomponentVO.isDefaultFlag()){
											createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, 2,new HSSFRichTextString("X"));
											//sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),nBgColorCount,nBgColorCount+2));
										}else{
											createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, 2,new HSSFRichTextString(""));
											//sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),nBgColorCount,nBgColorCount+2));
										}
									}else{
										//if(compList.size() == 1) nSpanRows = 2;
										//if(compList.size() > 1) nSpanRows = 1;
										
										HSSFRichTextString richRedString = null;
										if(objcompGroupVO.getValidFlag() != null && objcompGroupVO.getValidFlag().equalsIgnoreCase("Y")){
											richRedString = new HSSFRichTextString("*" + objcompGroupVO.getComponentGroupName());
											createCell(row,cellNormalStyle,HSSFCell.CELL_TYPE_STRING,0,richRedString);
											richRedString.applyFont(0,1,redFont);
										}else{
											richRedString = new HSSFRichTextString(objcompGroupVO.getComponentGroupName());
											createCell(row,cellNormalStyle,HSSFCell.CELL_TYPE_STRING,0,richRedString);
											richRedString.applyFont(0,objcompGroupVO.getComponentGroupName().length(),otherFont);
										}	
										if(objcompGroupVO.getComponentGroupName().equalsIgnoreCase("")){
											createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, 0,new HSSFRichTextString(""));
											LogUtil.logMessage("objcompGroupVO.getLeadFlag():"+objcompGroupVO.getLeadFlag());
											LogUtil.logMessage("row.getRowNum():"+row.getRowNum());
											LogUtil.logMessage("nSpanRows:"+nSpanRows);
											//sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),nSpanRows,nSpanRows));
										}
										createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, 1,new HSSFRichTextString(objcomponentVO.getComponentName()));
										
										if(objcomponentVO.isDefaultFlag()){
											createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, 2,new HSSFRichTextString("X"));
											//sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),nBgColorCount,nBgColorCount+2));
										}else{
											createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, 2,new HSSFRichTextString(""));
											//sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),nBgColorCount,nBgColorCount+2));
										}
										
									}	
									//nBgColorCount++;
								}
								}else{
									nrowCount++;
									row = sheet.createRow(nrowCount);
									createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, 0,new HSSFRichTextString(""));
									createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, 1,new HSSFRichTextString(""));
									createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, 2,new HSSFRichTextString(""));
								}

								LogUtil.logMessage("compList.size():"+compList.size());
								LogUtil.logMessage("objClauseVO.getClauseNum():"+objClauseVO.getClauseNum());
								LogUtil.logMessage("nrowCount:"+nrowCount);
								if (compList.size()<=1) 
									nSpanRows = 0;
								else{
									nSpanRows = compList.size()-1;
									row = sheet.getRow(nrowCount-nSpanRows);
								}
								
								createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, 3,new HSSFRichTextString(objClauseVO.getClauseNum()));
								if(nSpanRows > 0)
								sheet.addMergedRegion(new CellRangeAddress(nrowCount-1,(nrowCount-1)+nSpanRows,3,3));						        
						        
								if(objClauseVO.getClauseDesc()!=null && objClauseVO.getClauseDesc()!=""){
									strClauseDesc = getRefinedClauseDesc(objClauseVO.getClauseDesc());
								}else{
									strClauseDesc = objClauseVO.getClauseDesc();
								}
								
								strClauseDesc = strClauseDesc.trim();
								
								HSSFRichTextString richTextClaDesc = new HSSFRichTextString(strClauseDesc); 
								createClauseDescCell(row,cellClauseStyle ,HSSFCell.CELL_TYPE_STRING, 4,richTextClaDesc);//2nd column
								if(nSpanRows > 0)
									sheet.addMergedRegion(new CellRangeAddress(nrowCount-1,(nrowCount-1)+nSpanRows,4,8));
								else
									sheet.addMergedRegion(new CellRangeAddress(nrowCount,nrowCount,4,8));
								
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
								
								if (arlTableRows != null && arlTableRows.size() > 0) {
								for (int k= 0; k < arlTableRows.size(); k++) {
									nrowCount++;
									row = sheet.createRow(nrowCount);
									int colCount = 4;
									
									ArrayList arlTableColumns = (ArrayList) arlTableRows.get(k);
									
									for (int j= 0; j < colCount; j++){
										createCell(row,cellTabHeadStyle ,HSSFCell.CELL_TYPE_STRING, j,new HSSFRichTextString(""));
									}
									
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

									createCell(row,cellTabHeadStyle ,HSSFCell.CELL_TYPE_STRING, colCount,new HSSFRichTextString(""));
									
									}
									nrowCount = nrowCount - arlTableRows.size();
									row = sheet.getRow(nrowCount);
								}
								
								
								//Engineering Data
									String strEnggData = "";
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
									if(objClauseVO.getEngDataComment() == null){
										objClauseVO.setEngDataComment("");
									}else{
										strEnggData = strEnggData + objClauseVO.getEngDataComment();
									}
									createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING,9,new HSSFRichTextString(strEnggData));
									if(nSpanRows > 0)
									sheet.addMergedRegion(new CellRangeAddress(nrowCount-1,(nrowCount-1)+nSpanRows,9,9));
									
									nrowCount = nrowCount + arlTableRows.size();
									
									if(orderkeys != null && !orderkeys.trim().equalsIgnoreCase("")){
										ordList = objClauseVO.getOrderVO();
										ordListIterator = ordList.iterator();
										if (ordList.size() != 0){
										while (ordListIterator.hasNext()){
												objOrderVO = new OrderVO();
												objOrderVO = (OrderVO) ordListIterator.next();
												nrowCount++;
												intPos = 0;
												row = sheet.createRow(nrowCount);
												createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, 0,new HSSFRichTextString(objOrderVO.getOrderNo()));
												sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,1));
												createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, 2,new HSSFRichTextString(objOrderVO.getStatusDesc()));
												createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, 3,new HSSFRichTextString(objOrderVO.getCustomerName()));
												sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),3,8));
												HSSFRichTextString richVersionString = null;
												if(objOrderVO.getVersionIndicator().equalsIgnoreCase("Y")){
													richVersionString = new HSSFRichTextString("V"+Integer.toString(objOrderVO.getVersionNo())+ "*");
												}else{
													richVersionString = new HSSFRichTextString("V"+Integer.toString(objOrderVO.getVersionNo()));
												}
												createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, 9,richVersionString);
												
											}
										 
										}
									
									}
									nBgColorCount++;
								}
							}
							 nrowCount++;
							 row = sheet.createRow(nrowCount);
							 createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString(""));
							 sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,9));
							 nrowCount++;
						}
						nSubSecCount++;
						nHeadcount++;
						nSecCount++;
					/* Added for CR-130 ends here*/
					
					}
					//break outerloop;
				}
			}	
			/* Added for CR-130 */
			LogUtil.logMessage("strExporttoExcel :" + strExporttoExcel);
			if(strExporttoExcel != null ){
				    objHttpServletResponse.setContentType("application/vnd.ms-excel");
				    if(orderkeys != null && !orderkeys.trim().equalsIgnoreCase(""))
				    	objHttpServletResponse.setHeader("Content-disposition", "attachment; filename= SpecByCustomerReport.xls");
				    else
				    	objHttpServletResponse.setHeader("Content-disposition", "attachment; filename= SpecByModelReport.xls");	
					OutputStream out = null;
					out = objHttpServletResponse.getOutputStream();
					workBook.write(out);
				LogUtil.logMessage("strForwardKey :" + strForwardKey);
				if (strForwardKey.equalsIgnoreCase(ApplicationConstants.FAILURE))
					return objActionMapping.findForward(strForwardKey);
				else
					return null;	
		    }
			/* Added for CR-130 ends here*/
					
			/** Setting the Values to the action form **/
			
			objMasterSpecByMdlForm.setSectionList(sectionList);
			
			if (modelSubsectionList.size() != 0) {
				
				objMasterSpecByMdlForm
				.setModelSubSectionList(modelSubsectionList);
			}
			objMasterSpecByMdlForm.setModelSpecList(modelSpecList);
			objMasterSpecByMdlForm.setModelSeqNo(modelSeqNo);
			objMasterSpecByMdlForm.setModelName(modelName);
			objMasterSpecByMdlForm.setHnOrderKey(orderkeys);//This is for checking whether we need to display order info
			objMasterSpecByMdlForm.setHdnSelSpecType(specType);
			
		} catch (Exception objEx) {
			objEx.printStackTrace();
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***********************************************************************************************************
	 * This method is to used to view Customer Option Catalog for the selected model 
	 * and show it in the Pop up screen
	 * Added for LSDB_CR-77
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException,BusinessException
	 ************************************************************************************************************/
	
	public ActionForward viewCustomerOptionCatalog(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		//Edited for CR_118
		/** Declarations **/
		LogUtil
		.logMessage("Inside MasterSpecBymdlAction:viewCustomerOptionCatalog");
		String strForwardKey = ApplicationConstants.SUCCESS;
		
		String modelName = null;
		String specType = null;
		int saveCOC;
		String AM_PM = "";
		int modelSeqNo;
		ArrayList arlCustomerOption = new ArrayList();
		ArrayList arlImageList = new ArrayList();
		ModelVo objModelVO = new ModelVo();
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		int specTypeSeqNo;
		ArrayList arlCompGroups=new ArrayList();
		int updateStatus;
		
		//Added for CR_114
		Document document = new Document(PageSize.A4, 7, 0, 25, 100);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		//Added for CR_114 Ends
		
		try {
			MasterSpecByMdlForm objMasterSpecByMdlForm = (MasterSpecByMdlForm) objActionForm;
			modelSeqNo = Integer.parseInt(objHttpServletRequest
					.getParameter("modelSeqNo"));
			modelName = objHttpServletRequest.getParameter("modelName");
					
			specType = objHttpServletRequest
			.getParameter(ApplicationConstants.SELECTED_SPEC_TYPE);
			
			specTypeSeqNo = Integer.parseInt(objHttpServletRequest.getParameter(ApplicationConstants.SPEC_TYPE_NO));
			saveCOC = Integer.parseInt(objHttpServletRequest.getParameter("saveCOC"));
			
			LogUtil.logMessage("Model Seq No :" + modelSeqNo);
			LogUtil.logMessage("ModelName :" + modelName);
			LogUtil.logMessage("specType :" + specType);
			LogUtil.logMessage("specTypeSeqNo :" + specTypeSeqNo);
			
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			objModelVO.setModelSeqNo(modelSeqNo);
			objModelVO.setUserID(objLoginVo.getUserID());
			objModelVO.setModelName(modelName);
			
			
			
			if(saveCOC==1){
					
				int componentGrplength =objHttpServletRequest.getParameterValues("hdnCompGroupSeqNo").length;
				int[] componentGrpSeqNos = new int[componentGrplength];
				String[] tempComponentGrpSeqNos =new String[componentGrplength];
				String[] dispInCOCFlags =new String[componentGrplength];
				
				
					tempComponentGrpSeqNos =objHttpServletRequest.getParameterValues("hdnCompGroupSeqNo");
					for(int i =0;i<tempComponentGrpSeqNos.length;i++){
						if(tempComponentGrpSeqNos[i]!=null){
							componentGrpSeqNos[i]=Integer.parseInt(tempComponentGrpSeqNos[i]);
						}
						LogUtil.logMessage("flag is "+objHttpServletRequest.getParameterValues("hdnDispInCOC")[i]);
						dispInCOCFlags[i]=objHttpServletRequest.getParameterValues("hdnDispInCOC")[i];
					}
					LogUtil.logMessage("componentGrpSeqNos's length "+componentGrpSeqNos.length);
					LogUtil.logMessage("componentGrpSeqNos "+componentGrpSeqNos);
					
					
					objModelVO.setComponentGrpSeqNos(componentGrpSeqNos);
					objModelVO.setDispInCOCFlags(dispInCOCFlags);
					
				ViewSpecByModelBI objViewSpecByModelBO = ServiceFactory
				.getViewSpecByModelBO();
				
				updateStatus =objViewSpecByModelBO.updateCompGroupsInCOC(objModelVO);
					LogUtil.logMessage("updateStatus "+updateStatus);
					if (updateStatus == 0) {
						objMasterSpecByMdlForm
						.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
					} else {
						objMasterSpecByMdlForm.setMessageID("" + updateStatus);
					}
					
					/*
					arlCompGroups = objViewSpecByModelBO.editCompGroupInCOC(objModelVO);
					if(arlCompGroups.size()>0){
						objCustOptCatForm.setCompGroupList(arlCompGroups);
					}
					*/
			}
			
			/** Fetching the sections available for the selected model **/
			
			ViewSpecByModelBI objViewSpecByModelBO = ServiceFactory
			.getViewSpecByModelBO();
			
			arlCustomerOption = objViewSpecByModelBO.viewCustomerOptionCatalog(objModelVO);
			
			/** Setting the Values to the action form **/
						
			objMasterSpecByMdlForm.setCusOptionCatalogList(arlCustomerOption);
			//This part is for displaying Model Appendix images
			ModelAppendixVO objModelAppendixVO = new ModelAppendixVO();
			objModelAppendixVO.setModelSeqNo(modelSeqNo);
			objModelAppendixVO.setUserID(objLoginVo.getUserID());
			ModelAppendixBI objModelAppendixBO = ServiceFactory
			.getModelAppendixBO();
			arlImageList = objModelAppendixBO
			.fetchAppendixImages(objModelAppendixVO);
			
			//start from here
				
			
			
			generateCOC(document,baos,arlCustomerOption,arlImageList,modelName,specType);
			
			objHttpServletResponse.setContentType("application/rtf");
			objHttpServletResponse.setHeader("Content-disposition",
					"attachment;filename=CustomerOptionCatalog.rtf");

			objHttpServletResponse.setContentLength(baos.size());

			// write ByteArrayOutputStream to the ServletOutputStream
			ServletOutputStream out = objHttpServletResponse
					.getOutputStream();
			baos.writeTo(out);
			out.flush();
			
		} catch (Exception objEx) {
			objEx.printStackTrace();
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		if (strForwardKey.equalsIgnoreCase(ApplicationConstants.FAILURE))
			return objActionMapping.findForward(strForwardKey);
		else
			return null;
	}
	
	/***********************************************************************************************************
	 * This method is to used retrieve the Sections, Subsections Clauses for the selected model 
	 * and show it in the Pop up screen for CR_81
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException,BusinessException
	 ************************************************************************************************************/
	
	public ActionForward viewCharGrpRptbyModel(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		
		/** Declarations **/
		LogUtil
		.logMessage("Inside ViewCharGrpRptbyModel");
		String strForwardKey = ApplicationConstants.CHAR_GRP_RPT_SUCCESS;
		ArrayList sectionList = new ArrayList();
		ArrayList subSectionList;
		ArrayList specList;
		ArrayList modelSpecList = new ArrayList();
		ArrayList modelSubsectionList = new ArrayList();
		int modelSeqNo;
		String modelName = null;
		
		
		
		String specType = null;
		
		MasterSpecByMdlForm objMasterSpecByMdlForm = (MasterSpecByMdlForm) objActionForm;
		
		/** Getting the Modelsequence Number and Model name from the request parameter **/
		specType = objHttpServletRequest
		.getParameter(ApplicationConstants.SELECTED_SPEC_TYPE);
		modelSeqNo = Integer.parseInt(objHttpServletRequest
				.getParameter("modelSeqNo"));
		modelName = objHttpServletRequest.getParameter("modelName");
		
		specType = objHttpServletRequest
		.getParameter(ApplicationConstants.SELECTED_SPEC_TYPE);
		
		LogUtil.logMessage("spec type :" + ApplicationConstants.SELECTED_SPEC_TYPE);
		
		LogUtil.logMessage("Model Seq No :" + modelSeqNo);
		LogUtil.logMessage("ModelName :" + modelName);
		
		
		SectionVO objSectionVO = new SectionVO();
		SubSectionVO objSubSecMaintVO = new SubSectionVO();
		objSectionVO.setModelSeqNo(modelSeqNo);
		objSectionVO.setModelName(modelName);
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		try {
			/** Setting the user Id **/
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			objSectionVO.setUserID(objLoginVo.getUserID());
			
			//Added for PM&I
			objMasterSpecByMdlForm.setHdnSelSpecType(objMasterSpecByMdlForm
					.getHdnSelSpecType());
			
			/** Fetching the sections available for the selected model **/
			
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			sectionList = objSectionBO.fetchSections(objSectionVO);
			Iterator listIterator = null;
			
			/** For each section get the subsections and clauses **/
			
			if (sectionList.size() != 0) {
				listIterator = sectionList.iterator();
				while (listIterator.hasNext()) {
					specList = new ArrayList();
					subSectionList = new ArrayList();
					objSectionVO = new SectionVO();
					objSubSecMaintVO = new SubSectionVO();
					objSectionVO = (SectionVO) listIterator.next();
					objSubSecMaintVO.setModelSeqNo(modelSeqNo);
					objSubSecMaintVO
					.setSecSeqNo(objSectionVO.getSectionSeqNo());
					
										
					objSubSecMaintVO.setUserID(objLoginVo.getUserID());
					
					/** Get the subsection list for  current section **/
					
					ModelSubSectionBI objSubSecMaintBO = ServiceFactory
					.getSubSecMaintBO();
					subSectionList = objSubSecMaintBO
					.fetchSubSections(objSubSecMaintVO);
					
					/** Get the clauses for all the subsections in the current section **/
					
					ViewSpecByModelBI objViewSpecByModelBO = ServiceFactory
					.getViewSpecByModelBO();
					specList = (objViewSpecByModelBO
							.viewCharactersiticGrpRpt(objSubSecMaintVO));
					
					/** Add the sub sections for the current section to the list **/
					
					if (subSectionList.size() != 0) {
						modelSubsectionList.add(subSectionList);
					}
					
					/** Add the clauses for the subsections of the current section to the list **/
					
					modelSpecList.add(specList);
					
				}
			}
			/** Setting the Values to the action form **/
			
			objMasterSpecByMdlForm.setSectionList(sectionList);
			
			if (modelSubsectionList.size() != 0) {
				
				objMasterSpecByMdlForm
				.setModelSubSectionList(modelSubsectionList);
			}
			objMasterSpecByMdlForm.setModelSpecList(modelSpecList);
			objMasterSpecByMdlForm.setModelSeqNo(modelSeqNo);
			objMasterSpecByMdlForm.setModelName(modelName);
			objMasterSpecByMdlForm.setHdnSelSpecType(specType);
			
			LogUtil.logMessage("after seting spec type  in form :" + objMasterSpecByMdlForm.getHdnSelSpecType());
						
			
			
		} catch (Exception objEx) {
			objEx.printStackTrace();
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
	}
	//CR_101 starts here
	/***********************************************************************************************************
	 * This method is used to generate a EXCEL to DOORS excel report - for CR_101 by RJ85495
	 * @author Mahindra Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException,BusinessException
	 ************************************************************************************************************/
	
	public ActionForward excelToDoors(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		
		LogUtil.logMessage("Inside MasterSpecBymdlAction:excelToDoors");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList sectionList = new ArrayList();
		ArrayList subSectionList;
		ArrayList specList;
		int modelSeqNo;
		int rowCount=0;
		int intPos=0;
		int clauseCount=0;
		String modelName = null;
		String orderkeys = null;
		ArrayList arlCompGroup =new ArrayList();
		String strClauseDesc= null;
		String specType = null;
		
		MasterSpecByMdlForm objMasterSpecByMdlForm = (MasterSpecByMdlForm) objActionForm;
				
		modelSeqNo = Integer.parseInt(objHttpServletRequest.getParameter("modelSeqNo"));
		modelName = objHttpServletRequest.getParameter("modelName");
		
		specType = objHttpServletRequest.getParameter(ApplicationConstants.SELECTED_SPEC_TYPE);
		
		orderkeys = objHttpServletRequest.getParameter("orderkeys");

		SectionVO objSectionVO = new SectionVO();
		SubSectionVO objSubSecMaintVO = new SubSectionVO();
		ClauseVO objClauseVO = new ClauseVO();
		ComponentVO objcomponentVO = new ComponentVO();
		CompGroupVO objcompGroupVO = new CompGroupVO();
		objSectionVO.setModelSeqNo(modelSeqNo);
		objSectionVO.setModelName(modelName);
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		try {
			//create workbook
			HSSFWorkbook workBook = new HSSFWorkbook();
			HSSFSheet sheet = workBook.createSheet("DOORS");//create sheet

			HSSFCellStyle cellSecStyle = workBook.createCellStyle();
			
			HSSFFont secFont = workBook.createFont();
			secFont.setFontName(HSSFFont.FONT_ARIAL);
			secFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			secFont.setColor(HSSFColor.BLACK.index);		
			secFont.setFontHeightInPoints((short) 13);
			
			cellSecStyle.setFont(secFont);
			cellSecStyle.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
			cellSecStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellSecStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			HSSFCellStyle cellSubSecStyle = workBook.createCellStyle();
			
			HSSFFont subSecFont = workBook.createFont();			
			subSecFont.setFontName(HSSFFont.FONT_ARIAL);
			subSecFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			subSecFont.setColor(HSSFColor.BLACK.index);		
			subSecFont.setFontHeightInPoints((short) 11);
			
			cellSubSecStyle.setFont(subSecFont);
			cellSubSecStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
			cellSubSecStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellSubSecStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			
			HSSFCellStyle cellOtherStyle = workBook.createCellStyle();										
			
			HSSFFont otherFont = workBook.createFont();			
			otherFont.setFontName(HSSFFont.FONT_ARIAL);
			otherFont.setColor(HSSFColor.BLACK.index);		
			otherFont.setFontHeightInPoints((short) 10);
			
			cellOtherStyle.setFont(otherFont);
			cellOtherStyle.setWrapText(true);
			cellOtherStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellOtherStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
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

			HSSFCellStyle cellClauseStyle = workBook.createCellStyle();
			
			cellClauseStyle.setFont(otherFont);
			cellClauseStyle.setWrapText(true);
			cellClauseStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			 
			LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			objSectionVO.setUserID(objLoginVo.getUserID());
			
			objMasterSpecByMdlForm.setHdnSelSpecType(objMasterSpecByMdlForm.getHdnSelSpecType());
			
			/** Fetching the sections available for the selected model **/
			
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			sectionList = objSectionBO.fetchSections(objSectionVO);
			Iterator listIteratorSection = null;
			Iterator listIteratorSubSection = null;
			Iterator listIteratorClauses = null;
			Iterator listIteratorCompGroup = null;
			/** For each section get the subsections and clauses **/
			//Made Modifications to remove all the rows other than clauses for CR_101 fix
			HSSFRow row = sheet.createRow(rowCount);

			if (sectionList.size() != 0) {
				listIteratorSection = sectionList.iterator();
				
				while (listIteratorSection.hasNext()) {
					 
					specList = new ArrayList();
					subSectionList = new ArrayList();
					
					objSectionVO = new SectionVO();
					objSectionVO = (SectionVO) listIteratorSection.next();
					
					//rowCount+=2; now i value will be @ start 2
					//HSSFRow row = sheet.createRow((short) rowCount);for the first time this would be the 3rd row in EXCEL document 
					
					//createCellSectionSubSectionNames(sheet,row,cellSecStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString(objSectionVO.getSectionDisplay()),rowCount);
					
					objSubSecMaintVO = new SubSectionVO();
					objSubSecMaintVO.setModelSeqNo(modelSeqNo);
					objSubSecMaintVO.setSecSeqNo(objSectionVO.getSectionSeqNo());
					
					if (orderkeys != null && !"".equals(orderkeys)) {
						String strOrderKeys[] = orderkeys.split(ApplicationConstants.COMMA);
						objSubSecMaintVO.setOrderkeys(strOrderKeys);
					}
					
					objSubSecMaintVO.setUserID(objLoginVo.getUserID());
					
					/** Get the subsection list for  current section **/
					
					ModelSubSectionBI objSubSecMaintBO = ServiceFactory	.getSubSecMaintBO();
					subSectionList = objSubSecMaintBO.fetchSubSections(objSubSecMaintVO);
					
					/** Get the clauses for all the subsections in the current section **/
					
					ViewSpecByModelBI objViewSpecByModelBO = ServiceFactory.getViewSpecByModelBO();
					specList = (objViewSpecByModelBO.viewMasterSpecByModel(objSubSecMaintVO));
					
					/** Add the sub sections for the current section to the list **/
					
					if (subSectionList.size() != 0) {
						listIteratorSubSection = subSectionList.iterator();
						while (listIteratorSubSection.hasNext()) {
							objSubSecMaintVO = new SubSectionVO();
							objSubSecMaintVO = (SubSectionVO) listIteratorSubSection.next();
							
							//rowCount+=2;//incrementing i to 4
							//row = sheet.createRow((short) (rowCount));// for the first time this would be positioned to 5th row in EXCEL document
							
							//createCellSectionSubSectionNames(sheet,row,cellSubSecStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString(objSubSecMaintVO.getSubSecDisplay()),rowCount);
							
							/** Add the clauses for the subsections of the current section to the list **/
							if (specList.size() != 0) {								
								
								if (clauseCount==0)	
									createHeadingForClauses(workBook,row,sheet);
								
								//rowCount++;								
								
								listIteratorClauses = specList.iterator();
								
								while (listIteratorClauses.hasNext()) {
									objClauseVO = new ClauseVO();
									objClauseVO = (ClauseVO) listIteratorClauses.next();
									if (objClauseVO.getSubSectionSeqNo()==objSubSecMaintVO.getSubSecSeqNo()){
										clauseCount++;										
										//if (clauseCount==1)
										//	createHeadingForClauses(workBook,row,sheet);
										rowCount++;
										intPos=0;
										row = sheet.createRow(rowCount);
										
										createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_NUMERIC, 0,objClauseVO.getClauseSeqNo());//0th column
										createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 1,new HSSFRichTextString(objClauseVO.getClauseNum()));//1st column
																				
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

										createClauseDescCell(row,cellClauseStyle ,HSSFCell.CELL_TYPE_STRING, 2,richTextClaDesc);//2nd column
										//sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),2,6)); Commented for CR-135

										if (arlTableRows != null && arlTableRows.size() > 0) {
										
										
										for (int k= 0; k < arlTableRows.size(); k++) {
											rowCount++;
											row = sheet.createRow(rowCount);
											int colCount = 2;
											
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
											/*if (tabColCount == 1
													|| tabColCount == 2
													|| tabColCount == 3
													|| tabColCount == 4
													|| tabColCount == 5) {
												createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, colCount,new HSSFRichTextString((String) arlTableColumns.get(0)));													
											}
											
											if (tabColCount == 2
													|| tabColCount == 3
													|| tabColCount == 4
													|| tabColCount == 5) {
												createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, colCount,new HSSFRichTextString((String) arlTableColumns.get(1)));
											}

											if (tabColCount == 3
													|| tabColCount == 4
													|| tabColCount == 5) {
												createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, colCount,new HSSFRichTextString((String) arlTableColumns.get(2)));
											}

											if (tabColCount == 4
													|| tabColCount == 5) {
												createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, colCount,new HSSFRichTextString((String) arlTableColumns.get(3)));
											}

											if (tabColCount == 5) {
												createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, colCount,new HSSFRichTextString((String) arlTableColumns.get(4)));
											}
											colCount++;*/
											
											}
										
									
										}
										
										
										
										arlCompGroup = objClauseVO.getCompGroupVO();
										if (arlCompGroup.size() != 0) {
										listIteratorCompGroup = arlCompGroup.iterator();
											while (listIteratorCompGroup.hasNext()){
												objcompGroupVO = new CompGroupVO();
												objcomponentVO = new ComponentVO();
												objcompGroupVO = (CompGroupVO) listIteratorCompGroup.next();
												objcomponentVO = objcompGroupVO.getCompVO();
												if("N".equals(objcompGroupVO.getLeadFlag())){
													createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 7,new HSSFRichTextString(objcompGroupVO.getComponentGroupName()));//3rd column
													createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 8,new HSSFRichTextString(objcomponentVO.getComponentName()));//4th column
												}else 	if("Y".equals(objcompGroupVO.getLeadFlag())){
													createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 9,new HSSFRichTextString(objcompGroupVO.getComponentGroupName()));//5th column
													createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 10,new HSSFRichTextString(objcomponentVO.getComponentName()));//6th column
													}
											  }
										}
										
										createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 11,new HSSFRichTextString(objClauseVO.getStandardEquipmentDesc()));//7th column
										createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 12,new HSSFRichTextString(objClauseVO.getEngDataComment()));//8th column
										
										ArrayList arlEDLNO = objClauseVO.getEdlNO();
										if (arlEDLNO.size() > 0) {
											intPos=13;
											for (int n = 0; n < arlEDLNO.size(); n++) {
												intPos++;
												createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, intPos,new HSSFRichTextString(arlEDLNO.get(n).toString()));
												}
											}
										ArrayList arlRefEDLNO = objClauseVO.getRefEDLNO();
										if (arlRefEDLNO.size() > 0) {
											intPos=16;
											for (int n = 0; n < arlRefEDLNO.size(); n++) {
												intPos++;
												createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, intPos,new HSSFRichTextString(arlRefEDLNO.get(n).toString()));
											}
										}
									}
								}
							}
						}
					}
				  //rowCount++;
				}
				objHttpServletResponse.setContentType("application/vnd.ms-excel");
				objHttpServletResponse.setHeader("Content-disposition", "attachment; filename= "+modelName+"_"+"MasterSpec.xls");
				OutputStream out = null;
				out = objHttpServletResponse.getOutputStream();
				workBook.write(out);
			}
		} catch (Exception objEx) {
			objEx.printStackTrace();
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		if (strForwardKey.equalsIgnoreCase(ApplicationConstants.FAILURE))
			return objActionMapping.findForward(strForwardKey);
		else
			return null;
	}	
	
	/***********************************************************************************************************
	 * This method is used to generate a EXCEL to DOORS excel report - for CR_101 by RJ85495
	 * @author Mahindra Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException,BusinessException
	 ************************************************************************************************************/
	
	public ActionForward excelSpecToDoors(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		
		LogUtil.logMessage("Inside MasterSpecBymdlAction:excelSpecToDoors");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList sectionList = new ArrayList();
		ArrayList subSectionList;
		ArrayList specList;
		int modelSeqNo;
		int rowCount=0;
		int intPos=0;
		int clauseCount=0;
		
		String modelName = null;
		String orderKey = null;
		String orderNo = null;
		String dataLocFlag = null;
		ArrayList arlCompGroup =new ArrayList();
		String strClauseDesc= null;
		String strOrderClauseDelFlag = null;
		String specType = null;
		
		ArrayList arlAllSections = new ArrayList();
		ArrayList arlAllSubSections = new ArrayList();
		ArrayList arlAllClauses = new ArrayList();
		
		HashMap hmAllSections = null;
		HashMap hmAllSubSections = null;
		HashMap hmAllClauses = null;
		
		MasterSpecByMdlForm objMasterSpecByMdlForm = (MasterSpecByMdlForm) objActionForm;
				
		modelSeqNo = Integer.parseInt(objHttpServletRequest.getParameter("modelSeqNo"));
		modelName = objHttpServletRequest.getParameter("modelName");
		specType = objHttpServletRequest.getParameter(ApplicationConstants.SELECTED_SPEC_TYPE);
		orderKey = objHttpServletRequest.getParameter("orderKey");
		orderNo = objHttpServletRequest.getParameter("orderNo");
		dataLocFlag = objHttpServletRequest.getParameter("dataLocFlag");
		LogUtil.logMessage("orderNo:" + orderNo);
		LogUtil.logMessage("orderKey:" + orderKey);
		LogUtil.logMessage("dataLocFlag:" + dataLocFlag);
		SectionVO objSectionVO = new SectionVO();
		SubSectionVO objSubSecMaintVO = new SubSectionVO();
		ClauseVO objClauseVO = new ClauseVO();
		ComponentVO objcomponentVO = new ComponentVO();
		CompGroupVO objcompGroupVO = new CompGroupVO();
		objSectionVO.setModelName(modelName);
		/*
		String[] strOrderKeys = orderkeys.split(ApplicationConstants.COMMA);
		int orderSize = strOrderKeys.length;
		
		objSectionVO.setOrderKey(Integer.parseInt(strOrderKeys[0]));
		LogUtil.logMessage("strOrderKeys[0] " + strOrderKeys[0]+"strOrderKeys[1]"+strOrderKeys[1]);
		*/
		
		objSectionVO.setOrderKey(Integer.parseInt(orderKey));
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		try {
			
			//create workbook
			HSSFWorkbook workBook = new HSSFWorkbook();
			HSSFSheet sheet = workBook.createSheet(orderNo);//create sheet
			
			HSSFCellStyle cellSecStyle = workBook.createCellStyle();
			
			HSSFFont secFont = workBook.createFont();
			secFont.setFontName(HSSFFont.FONT_ARIAL);
			secFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			secFont.setColor(HSSFColor.BLACK.index);		
			secFont.setFontHeightInPoints((short) 13);
			
			cellSecStyle.setFont(secFont);
			cellSecStyle.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
			cellSecStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellSecStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			HSSFCellStyle cellSubSecStyle = workBook.createCellStyle();
			
			HSSFFont subSecFont = workBook.createFont();			
			subSecFont.setFontName(HSSFFont.FONT_ARIAL);
			subSecFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			subSecFont.setColor(HSSFColor.BLACK.index);		
			subSecFont.setFontHeightInPoints((short) 11);
			
			cellSubSecStyle.setFont(subSecFont);
			cellSubSecStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
			cellSubSecStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellSubSecStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			
			HSSFCellStyle cellOtherStyle = workBook.createCellStyle();										
			
			HSSFFont otherFont = workBook.createFont();			
			otherFont.setFontName(HSSFFont.FONT_ARIAL);
			otherFont.setColor(HSSFColor.BLACK.index);		
			otherFont.setFontHeightInPoints((short) 10);
			
			cellOtherStyle.setFont(otherFont);
			cellOtherStyle.setWrapText(true);
			cellOtherStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellOtherStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
			//Added For Inserting table data DOORS issue
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
			//Ends
			
			HSSFCellStyle cellClauseStyle = workBook.createCellStyle();
			
			cellClauseStyle.setFont(otherFont);
			cellClauseStyle.setWrapText(true);
			cellClauseStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			 
			LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			objSectionVO.setUserID(objLoginVo.getUserID());
			//objSectionVO.setDataLocationType(ApplicationConstants.DATA_lOCATION_TYPE);
			objSectionVO.setDataLocationType(dataLocFlag);
			objMasterSpecByMdlForm.setHdnSelSpecType(objMasterSpecByMdlForm.getHdnSelSpecType());
			
			/** Fetching the sections available for the selected model **/
			
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			OrderSectionBI objOrderSectionBo = ServiceFactory.getOrderSectionBO();
			sectionList = objOrderSectionBo.fetchOrdSections(objSectionVO);
			Iterator listIteratorSection = null;
			Iterator listIteratorSubSection = null;
			Iterator listIteratorClauses = null;
			Iterator listIteratorCompGroup = null;
			/** For each section get the subsections and clauses **/
			//Made Modifications to remove all the rows other than clauses for CR_101 fix
			HSSFRow row = sheet.createRow(rowCount);
			
			if (sectionList.size() != 0) {
				
				createHeadingForClauses(workBook,row,sheet);
				listIteratorSection = sectionList.iterator();
				
				while (listIteratorSection.hasNext()) {
					 
					specList = new ArrayList();
					subSectionList = new ArrayList();
					
					objSectionVO = new SectionVO();
					objSectionVO = (SectionVO) listIteratorSection.next();
					
					//rowCount+=2; //now i value will be @ start 2
					//row = sheet.createRow((short) rowCount);//for the first time this would be the 3rd row in EXCEL document 
					
					//createCellSectionSubSectionNames(sheet,row,cellSecStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString(objSectionVO.getSectionDisplay()),rowCount);
					
					objSubSecMaintVO = new SubSectionVO();
					objSubSecMaintVO.setModelSeqNo(modelSeqNo);
					objSubSecMaintVO.setSecSeqNo(objSectionVO.getSectionSeqNo());
					objSubSecMaintVO.setUserID(objLoginVo.getUserID());
					
					LogUtil.logMessage("objSubSecMaintVO.setSecSeqNo " + objSubSecMaintVO.getSecSeqNo());
					
					/** Get the subsection list for  current section 
					
					ModelSubSectionBI objSubSecMaintBO = ServiceFactory	.getSubSecMaintBO();
					subSectionList = objSubSecMaintBO.fetchSubSections(objSubSecMaintVO);**/
					
					subSectionList = OrderSectionDAO.fetchSectionDetails(objSectionVO);
					
					/** Get the clauses for all the subsections in the current section 
					
					ViewSpecByModelBI objViewSpecByModelBO = ServiceFactory.getViewSpecByModelBO();
					specList = (objViewSpecByModelBO.viewMasterSpecByModel(objSubSecMaintVO));
					
					/** Add the sub sections for the current section to the list **/
					
					if (subSectionList.size() != 0) {
						listIteratorSubSection = subSectionList.iterator();
						while (listIteratorSubSection.hasNext()) {
							objSubSecMaintVO = new SubSectionVO();
							objSubSecMaintVO = (SubSectionVO) listIteratorSubSection.next();
							LogUtil.logMessage("objSubSecMaintVO.getSubSecSeqNo " + objSubSecMaintVO.getSubSecSeqNo());
							//rowCount+=2;//incrementing i to 4
							//row = sheet.createRow((short) (rowCount));// for the first time this would be positioned to 5th row in EXCEL document
							
							//createCellSectionSubSectionNames(sheet,row,cellSubSecStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString(objSubSecMaintVO.getSubSecDisplay()),rowCount);
							
							/** Add the clauses for the subsections of the current section to the list **/
							specList = objSubSecMaintVO.getClauseGroup();
							clauseCount=0;
							
							if (specList.size() != 0) {			
								
								/*if (clauseCount == 0 && chkClauseCnt == 0)
								{
								LogUtil.logMessage("clauseCount:" + clauseCount);
								LogUtil.logMessage("chkClauseCnt:" + chkClauseCnt);
								createHeadingForClauses(workBook,row,sheet);
								chkClauseCnt++;
								}*/
								/*if (clauseCount==0)	{
									rowCount++;
									row = sheet.createRow(rowCount);
									createHeadingForClauses(workBook,row,sheet);
								}*/						
								LogUtil.logMessage("specList.size " + specList.size());
								listIteratorClauses = specList.iterator();
								
								while (listIteratorClauses.hasNext()) {
									objClauseVO = new ClauseVO();
									objClauseVO = (ClauseVO) listIteratorClauses.next();
									LogUtil.logMessage("objClauseVO.getClauseSeqNo() " + objClauseVO.getClauseSeqNo());
									LogUtil.logMessage("objClauseVO.getClauseNum() " + objClauseVO.getClauseNum());
									if (objClauseVO.getSubSectionSeqNo()==objSubSecMaintVO.getSubSecSeqNo()){
										clauseCount++;										
										/*if (clauseCount==1)
											createHeadingForClauses(workBook,row,sheet);*/
										rowCount++;
										intPos=0;
										row = sheet.createRow(rowCount);
										strOrderClauseDelFlag = objClauseVO.getClauseDelFlag();
										LogUtil.logMessage("strOrderClauseDelFlag: " + strOrderClauseDelFlag);
										if(strOrderClauseDelFlag !=null && strOrderClauseDelFlag.equalsIgnoreCase("Y")){
											createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 0,new HSSFRichTextString(""));//0th column
											createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 1,new HSSFRichTextString(objClauseVO.getClauseNum()));//1st column
											createCell(row,cellClauseStyle,HSSFCell.CELL_TYPE_STRING, 2,new HSSFRichTextString("RESERVED"));//2nd column
											createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 3,new HSSFRichTextString(""));//3rd column
											createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 4,new HSSFRichTextString(""));//4th column
											createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 5,new HSSFRichTextString(""));//5th column
											createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 6,new HSSFRichTextString(""));//6th column
											createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 3,new HSSFRichTextString(""));//3rd column
											createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 4,new HSSFRichTextString(""));//4th column
											createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 5,new HSSFRichTextString(""));//5th column
											createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 6,new HSSFRichTextString(""));//6th column
											createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 7,new HSSFRichTextString(""));//7th column
											createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 8,new HSSFRichTextString(""));//8th column
											
											ArrayList arlEDLNO = objClauseVO.getEdlNO();
											if (arlEDLNO.size() > 0) {
												intPos=8;
												for (int n = 0; n < arlEDLNO.size(); n++) {
													intPos++;
													createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, intPos,new HSSFRichTextString(""));
													}
												}
											ArrayList arlRefEDLNO = objClauseVO.getRefEDLNO();
											if (arlRefEDLNO.size() > 0) {
												intPos=11;
												for (int n = 0; n < arlRefEDLNO.size(); n++) {
													intPos++;
													createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, intPos,new HSSFRichTextString(""));
												}
											}
										}else{
										createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_NUMERIC, 0,objClauseVO.getClauseSeqNo());//0th column
										
										createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 1,new HSSFRichTextString(objClauseVO.getClauseNum()));//1st column
																				
										if(objClauseVO.getClauseDesc()!=null && objClauseVO.getClauseDesc()!=""){
											strClauseDesc = getRefinedClauseDesc(objClauseVO.getClauseDesc());
										}else{
											strClauseDesc = objClauseVO.getClauseDesc();
										}
										strClauseDesc = strClauseDesc.trim();
										LogUtil.logMessage("objClauseVO.getClauseDesc()" + objClauseVO.getClauseDesc());
										
										HSSFRichTextString richTextClaDesc = new HSSFRichTextString(strClauseDesc); 
										
										//Added for Doors issue
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
										
										createClauseDescCell(row,cellClauseStyle ,HSSFCell.CELL_TYPE_STRING, 2,richTextClaDesc);//2nd column
										
if (arlTableRows != null && arlTableRows.size() > 0) {
											
											
											for (int k= 0; k < arlTableRows.size(); k++) {
												rowCount++;
												row = sheet.createRow(rowCount);
												int colCount = 2;
												
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
										
										//Added for DOORS issue

										LogUtil.logMessage("objClauseVO.getCompName()" + objClauseVO.getCompName());
										if(objClauseVO.getCompName() != null){
										arlCompGroup = objClauseVO.getCompName();
										String strCompGroupName = "";
										String strCompName = "";
										if (arlCompGroup.size() != 0) {
										listIteratorCompGroup = arlCompGroup.iterator();
											while (listIteratorCompGroup.hasNext()){
												objcomponentVO = new ComponentVO();
												objcomponentVO = (ComponentVO) listIteratorCompGroup.next();
												if("N".equals(objcomponentVO.getCompLeadFlag())){
													createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 7,new HSSFRichTextString(objcomponentVO.getComponentGroupName()));//3rd column--Changed from 3 to 7 for DOORS issue fix 
													createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 8,new HSSFRichTextString(objcomponentVO.getComponentName()));//4th column--Changed from 4 to 8 for DOORS issue fix 
												}else 	if("Y".equals(objcomponentVO.getCompLeadFlag())){
													strCompGroupName = strCompGroupName  + objcomponentVO.getComponentGroupName() +  ", ";
													strCompName = strCompName + objcomponentVO.getComponentName()+ ", ";
													}
											  }
											LogUtil.logMessage("Before strCompGroupName:" + strCompGroupName);
											LogUtil.logMessage("Before strCompName:" + strCompName);
											if(!strCompGroupName.equalsIgnoreCase("") && !strCompName.equalsIgnoreCase("")){
												strCompGroupName = strCompGroupName.substring(0,strCompGroupName.lastIndexOf(","));
												strCompName = strCompName.substring(0,strCompName.lastIndexOf(","));
												LogUtil.logMessage("After strCompGroupName:" + strCompGroupName);
												LogUtil.logMessage("After strCompName:" + strCompName);
												createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 9,new HSSFRichTextString(strCompGroupName));//5th column--Changed from 5 to 9 for DOORS issue fix
												createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 10,new HSSFRichTextString(strCompName));//6th column--Changed from 6 to 10 for DOORS issue fix
											}
										}
										}else{
											createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 7,new HSSFRichTextString(""));//3rd column
											createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 8,new HSSFRichTextString(""));//4th column
											createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 9,new HSSFRichTextString(""));//5th column
											createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 10,new HSSFRichTextString(""));//6th column
										}
										
										createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 11,new HSSFRichTextString(objClauseVO.getStandardEquipmentDesc()));//7th column--Changed from 7 to 11 for DOORS issue fix
										createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 12,new HSSFRichTextString(objClauseVO.getEngDataComment()));//8th column--Changed from 8 to 12 for DOORS issue fix
										
										ArrayList arlEDLNO = objClauseVO.getEdlNO();
										if (arlEDLNO.size() > 0) {
											intPos=13;
											for (int n = 0; n < arlEDLNO.size(); n++) {
												intPos++;
												createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, intPos,new HSSFRichTextString(arlEDLNO.get(n).toString()));
												}
											}
										ArrayList arlRefEDLNO = objClauseVO.getRefEDLNO();
										if (arlRefEDLNO.size() > 0) {
											intPos=16;
											for (int n = 0; n < arlRefEDLNO.size(); n++) {
												intPos++;
												createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, intPos,new HSSFRichTextString(arlRefEDLNO.get(n).toString()));
											}
										}
										}
										/*ArrayList arlPartOF = objClauseVO.getPartOF();
										if (arlPartOF.size() > 0) {
											intPos=10;
											for (int n = 0; n < arlPartOF.size(); n++) {
												intPos++;
												SubSectionVO objSubSecVO = (SubSectionVO) arlPartOF.get(n);
												createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, intPos,new HSSFRichTextString(objSubSecVO.getSubSecCode()));
											}
										}*/
									
								}
							}
						}
					}
					}
					
				 // rowCount++;
				}
			}
				objHttpServletResponse.setContentType("application/vnd.ms-excel");
				objHttpServletResponse.setHeader("Content-disposition", "attachment; filename= "+orderNo+"_"+"Spec.xls");
				OutputStream out = null;
				out = objHttpServletResponse.getOutputStream();
				workBook.write(out);
					
			
		} catch (Exception objEx) {
			objEx.printStackTrace();
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
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
	
	/***************************************************************************
	 * This method is used to create the Section or SubSection Heading
	 * 
	 * @author Mahindra Satyam Ltd
	 * @version 1.0
	 * @param HSSFSheet, HSSFRow, HSSFCellStyle, int, int, String, int
	 * @return HSSFCell
	 **************************************************************************/
	
	protected HSSFCell createCellSectionSubSectionNames(HSSFSheet sheet,HSSFRow row, HSSFCellStyle headerStyle,
			int cellType, int position, HSSFRichTextString cellValue,int rowCount) {
		HSSFCell cell = null;
		cell = row.createCell(position);
		cell.setCellStyle(headerStyle);
		cell.setCellType(cellType);
		cell.setCellValue(cellValue);
		sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,0,13));
		 //for section name to be displayed merging cells from 0th column in 3rd row to 15th column in same row 
		return cell;
	}
	
	/***************************************************************************
	 * This method is used to Create the Heading for each SubSection
	 * 
	 * @author Mahindra Satyam Ltd
	 * @version 1.0
	 * @param HSSFWorkbook, HSSFRow, HSSFSheet
	 * @return void
	 * @throws Exception
	 **************************************************************************/
	
	public void createHeadingForClauses(HSSFWorkbook workBook,HSSFRow row,HSSFSheet sheet) 
		throws Exception{
		int colCount=0;
		HSSFCellStyle cellHeadStyle = workBook.createCellStyle();
		
		HSSFFont headFont = workBook.createFont();
		
		headFont.setFontName(HSSFFont.FONT_ARIAL);
		headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headFont.setColor(HSSFColor.BLACK.index);	
		headFont.setFontHeightInPoints((short) 10);
		
		cellHeadStyle.setWrapText(true);
		cellHeadStyle.setFont(headFont);
		cellHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		final String[] columnHeadings = { "Unique Identifier","Clause Number","Clause description","Component Group","Component Value"
				 ,"Related Component Group","Related Component Value" 
				 ,"Engineering Data","Engineering Data Comments","New EDL Number 1",
				 "New EDL Number 2", "New EDL Number 3","Reference EDL Number 1","Reference EDL Number 2","Reference EDL Number 3"
				 };
		final int[] columnWidth = {3500, 2200 , 12000, 3500
									,3500,3500 ,
									4000,4000,5000
									,2700 ,2700,2700,2700,2700,2700};		
		for (int intPos=0;intPos < 15; intPos++)
			{
				 createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,colCount,new HSSFRichTextString(columnHeadings[intPos]));
				 if (intPos==2){
				 	 //sheet.addMergedRegion(new CellRangeAddress(0,0,intPos,6)); commented for CR-135
				 	 colCount=colCount+4;	//Edited for CR-135
				 }
				 colCount++;
				 sheet.setColumnWidth(intPos,columnWidth[intPos]);
			 }
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
	//CR_101 Ends here
	
	//Added for CR_114
	
	private static void generateCOC(Document document,ByteArrayOutputStream baos,ArrayList arlCustomerOption,ArrayList imageList,String modelName,String specType) throws EMDException, Exception{
		String AM_PM ="";
		String strClauseDescWithHTMLTag="";
		ArrayList arlSubSec;
		Iterator itSection;
		Iterator itSubSection;
		Iterator itCompGrp;
		Iterator itComponent;
		Iterator itClauses;
		Iterator itAppendixImages;
		strFontSizeBoldUnderlineEleven.setStyle(Font.UNDERLINE);
		
		SectionVO objSectionVO;
		SubSectionVO objSubSectionVO;
		CompGroupVO objCompGroupVO;
		ComponentVO objComponentVO;
		ClauseVO objClauseVO;
		
		ModelAppendixVO  objModelAppendixVO;
		FileVO objFileVO;
		FileVO objjFileVO;
		FileUploadBI objFileUploadBO = ServiceFactory.getFileUploadBO();
		
		
		
		Table table;
		Cell c;
		Phrase p;
		int nClauseRewrite; //Added for CR-118 QA Fix
		
		RtfWriter2.getInstance(document, baos);
		document.addAuthor("EMD-LSDB");
		document.addSubject("EMD-LSDB Customer Option Catalog Report");
		document.addCreationDate();
		document.open();
		document.setHeader(generateHeaderForCOC());
		document.setFooter(generateFooterForCOC());
		//First Page
		
		
		table = new Table(1);
		c = new Cell(new Phrase(modelName+" Customer Option Catalog",strFontSizeBoldFourteen));
		c.setBorder(Rectangle.NO_BORDER);
		c.setVerticalAlignment(Element.ALIGN_TOP);
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c);
		document.add(table);

		table = new Table(4);
		table.setDefaultCellBorder(Rectangle.NO_BORDER);
		table.setAbsWidth("95");
		

		//table.setSpaceInsideCell(2);
		table.setAlignment(Element.ALIGN_CENTER);
		//table.setSpaceBetweenCells(2);
		table.setBorder(Rectangle.NO_BORDER);

		table.addCell(new Cell(""));
		table.addCell(new Cell(new Phrase("Specification Type",
				strFointSizeBoldEleven)));

		c = new Cell(new Phrase(": "+specType, strFointSizeBoldEleven));
		c.setColspan(2);
		table.addCell(c);

		table.addCell(new Cell(""));
		table
				.addCell(new Cell(new Phrase("Model",
						strFointSizeBoldEleven)));

		c = new Cell(new Phrase(": "+modelName, strFointSizeBoldEleven));
		c.setColspan(2);
		table.addCell(c);

		table.addCell(new Cell(""));
		table.addCell(new Phrase("Customer", strFointSizeBoldEleven));

		c = new Cell(new Phrase(": ", strFointSizeBoldEleven));
		c.setColspan(2);
		table.addCell(c);

		table.addCell(new Cell(""));
		table.addCell(new Phrase("Order Number", strFointSizeBoldEleven));

		c = new Cell(new Phrase(": ", strFointSizeBoldEleven));
		c.setColspan(2);
		table.addCell(c);

		table.addCell(new Cell(""));
		table.addCell(new Cell(new Phrase("Quantity",
				strFointSizeBoldEleven)));

		c = new Cell(new Phrase(": ", strFointSizeBoldEleven));
		c.setColspan(2);
		table.addCell(c);

		table.addCell(new Cell(""));
		table.addCell(new Cell(new Phrase("Date of Creation",
				strFointSizeBoldEleven)));

		// Report generation date
		SimpleDateFormat sdfAtCreation = new SimpleDateFormat();
		sdfAtCreation.applyPattern("MM/dd/yyyy HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		String currentTime = sdfAtCreation.format(calendar.getTime());
		if (calendar.get(Calendar.AM_PM) == 0)
			AM_PM = "AM";
		else
			AM_PM = "PM";

		c = new Cell(new Phrase(": " + currentTime + " " + AM_PM,
				strFointSizeBoldEleven));
		c.setColspan(2);
		table.addCell(c);

		document.add(table);
		
		//First Page Ends
		
		if(arlCustomerOption.size()>0){
			LogUtil.logMessage("Values in arlCustomerOption are " + arlCustomerOption);
			LogUtil.logMessage("Section Starts");
			//Section Starts
			itSection =arlCustomerOption.iterator();
			while(itSection.hasNext()){
				document.newPage();
				
				table = new Table(4);
				float[] colsWidth = {1f,1f,1f,65f};
				table.setWidths(colsWidth);			
				table.setDefaultCellBorder(Rectangle.NO_BORDER);
				table.setAbsWidth("95");
				//table.setSpaceInsideCell(2);
				table.setAlignment(Element.ALIGN_CENTER);
				//table.setSpaceBetweenCells(2);
				table.setBorder(Rectangle.NO_BORDER);
						
				objSectionVO =(SectionVO)itSection.next();
				
				c = new Cell();
				c.setColspan(4);
				c.add(new Phrase(objSectionVO.getSectionName(),strFontSizeBoldUnderlineEleven));
				table.addCell(c);
				
				table.addCell(new Cell(""));
				
				c = new Cell(new Phrase("Comments", strFointSizeItalicElevenBlue));
				c.setColspan(3);
				table.addCell(c);
				
				//Add the section Comments
				table.addCell(new Cell(""));
				c = new Cell(new Phrase(objSectionVO.getSectionComments(), strFontSizeTen));
				c.setColspan(3);
				table.addCell(c);
				
				c = new Cell("");
				c.setColspan(4);
				table.addCell(c);
				
				
				LogUtil.logMessage("Subsection Starts");
				//Subsection Starts
				arlSubSec =objSectionVO.getSubSec();
				
				if(arlSubSec.size()>0){
					LogUtil.logMessage("Values in arlSubSec are " + arlSubSec);
					itSubSection =arlSubSec.iterator();
					while (itSubSection.hasNext()){
						objSubSectionVO =(SubSectionVO)itSubSection.next();
						
						table.addCell(new Cell(""));
						table.addCell(new Cell(""));
						c =new Cell(new Phrase(objSubSectionVO.getSubSecName(),strFontSizeBoldUnderlineEleven)); 
						c.setColspan(2);
						
						table.addCell(c);
						
						LogUtil.logMessage("Component Group Start");
						//Component Group Start
						if(objSubSectionVO.getCompGroup()!=null){
							LogUtil.logMessage("Values of ComponentGroups are " + objSubSectionVO.getCompGroup());
							itCompGrp =objSubSectionVO.getCompGroup().iterator();
							while(itCompGrp.hasNext()){
								objCompGroupVO =(CompGroupVO)itCompGrp.next();
								
								table.addCell(new Cell(""));
								table.addCell(new Cell(""));
								table.addCell(new Cell(""));
								c =new Cell("");
								if(objCompGroupVO.getValidFlag().equalsIgnoreCase("Y")){
									p =new Phrase("*",strFontSizeBoldRedEleven);
									c.add(p);
								}
								p =new Phrase(objCompGroupVO.getComponentGroupName(),strFontSizeBoldUnderlineEleven);
								c.add(p);
								table.addCell(c);
								
								LogUtil.logMessage("Component start");
								//Component start
								
								if(objCompGroupVO.getComponent() !=null){
									LogUtil.logMessage("Values of Components are " + objCompGroupVO.getComponent());
									itComponent =objCompGroupVO.getComponent().iterator();
									while(itComponent.hasNext()){
										boolean checkClauses =false;
										objComponentVO =(ComponentVO)itComponent.next();
										
										table.addCell(new Cell(""));
										table.addCell(new Cell(""));
										c =new Cell(new Chunk("\u00b7",strFontSizeBoldTwentyFive));
										c.setVerticalAlignment(Element.ALIGN_TOP);
										table.addCell(c);
//										
										if(objComponentVO.getCompDefFlag().equalsIgnoreCase("Y")){
											p=new Phrase(objComponentVO.getComponentName(),strFontSizeTen);
										}else{
											p=new Phrase(objComponentVO.getComponentName(),strFontSizeItalicTen);
										}
										c =new Cell(p);
										c.setVerticalAlignment(Element.ALIGN_MIDDLE);
										table.addCell(c);
										
										LogUtil.logMessage("Clause Starts");
										//Clause Starts
										if(objComponentVO.getClauseVOList()!=null){
											itClauses =objComponentVO.getClauseVOList().iterator();
											nClauseRewrite = 0; //Added for CR-118 QA Fix
											while(itClauses.hasNext()){
												checkClauses=true;
												if (nClauseRewrite > 0){//Added for CR-118 QA- fix
													table.addCell(new Cell(""));
													table.addCell(new Cell(""));
													c =new Cell(new Chunk("\u00b7",strFontSizeBoldTwentyFive));
													c.setVerticalAlignment(Element.ALIGN_TOP);
													table.addCell(c);
//													
													if(objComponentVO.getCompDefFlag().equalsIgnoreCase("Y")){
														p=new Phrase(objComponentVO.getComponentName(),strFontSizeTen);
													}else{
														p=new Phrase(objComponentVO.getComponentName(),strFontSizeItalicTen);
													}
													c =new Cell(p);
													c.setVerticalAlignment(Element.ALIGN_MIDDLE);
													table.addCell(c);
												}
												objClauseVO =(ClauseVO)itClauses.next();
												c=new Cell("");
												c.setColspan(3);
												table.addCell(c);
												
												Table ClauseTable =new Table(1);
												ClauseTable.setBorder(Rectangle.NO_BORDER);
												ClauseTable.setDefaultCellBorder(Rectangle.NO_BORDER);
												//Updated for CR-118
												String strClauseDesc = String.valueOf(objClauseVO.getClauseDesc());
												if(strClauseDesc!=null && strClauseDesc!=""){
														strClauseDesc = strClauseDesc.replaceAll("  ","&nbsp;&nbsp;").replaceAll("\n","<br>").replaceAll("\n\\n","<br>");
														Paragraph strPastHtmlClauseDesc= new Paragraph();
														if (strClauseDesc.startsWith("<p>"))
														{						    
															String strFileName = ApplicationUtil.strConvertToHTMLFormat(strClauseDesc);
															StyleSheet styles = new StyleSheet();
															 styles.loadTagStyle("p","size","10px");
															 styles.loadTagStyle("p","face","Times");
															if(!objComponentVO.getCompDefFlag().equalsIgnoreCase("Y")){
																styles.loadTagStyle("p","font-style","italic");
															}
															ArrayList charData = HTMLWorker.parseToList(new FileReader(strFileName), styles);
														    for (int k1 = 0; k1 < charData.size(); ++k1) {
														    	strPastHtmlClauseDesc.add((Element)charData.get(k1));
														    }
														    strClauseDesc = strPastHtmlClauseDesc.getContent(); 
														}else{
														 	strClauseDesc = objClauseVO.getClauseDesc();
														}
												}
												//LogUtil.logMessage("strClauseDesc:"+strClauseDesc);
												strClauseDesc = strClauseDesc.trim();
												ClauseTable.addCell(new Cell(strClauseDesc));
//												// Updated for CR-118 Ends Here 
												LogUtil.logMessage("Table Data Starts");
												//Table Data
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
												
												Table TBData = new Table(tabColCount);
												//TBData.setTotalWidth(95);
												//Set the width of the table data table
												
												int intHeader = 0;
												Font fontHeader;
												
												ArrayList arlTableRows = (ArrayList) objClauseVO.getTableArrayData1();	
												if (arlTableRows != null && arlTableRows.size() > 0) {
													
												for (int k= 0; k < arlTableRows.size(); k++) {
													if (intHeader == 0) {
														fontHeader = new Font(Font.TIMES_ROMAN, 10, Font.BOLD, Color.BLACK);
													} else {
														fontHeader = strFontSizeTen;
													}
													
													ArrayList arlTableColumns = (ArrayList) arlTableRows.get(k);

													if (tabColCount == 1
															|| tabColCount == 2
															|| tabColCount == 3
															|| tabColCount == 4
															|| tabColCount == 5) {
														c = new Cell(new Phrase((String) arlTableColumns.get(0), fontHeader));
														c.setBorderColor(new Color(0, 0, 0));
														TBData.addCell(c);
													}

													if (tabColCount == 2
															|| tabColCount == 3
															|| tabColCount == 4
															|| tabColCount == 5) {
														c = new Cell(new Phrase((String) arlTableColumns.get(1), fontHeader));
														c.setBorderColor(new Color(0, 0, 0));
														TBData.addCell(c);
													}

													if (tabColCount == 3
															|| tabColCount == 4
															|| tabColCount == 5) {
														c = new Cell(new Phrase((String) arlTableColumns.get(2), fontHeader));
														c.setBorderColor(new Color(0, 0, 0));
														TBData.addCell(c);
													}

													if (tabColCount == 4
															|| tabColCount == 5) {
														c = new Cell(new Phrase((String) arlTableColumns.get(3), fontHeader));
														c.setBorderColor(new Color(0, 0, 0));
														TBData.addCell(c);
													}

													if (tabColCount == 5) {
														c = new Cell(new Phrase((String) arlTableColumns.get(4), fontHeader));
														c.setBorderColor(new Color(0, 0, 0));
														TBData.addCell(c);
													}
													
													intHeader++;
													}
												
												//ClauseTable.addCell(new Cell(TBData));
												ClauseTable.insertTable(TBData);
												}
												
												
												
												//c =new Cell(ClauseTable);
												//table.addCell(c);
												table.insertTable(ClauseTable);
												nClauseRewrite++; 
												
											}
										}
										//Empty space if there is no clause;
										if(!checkClauses){
											LogUtil.logMessage("Empty Spaces");
											c = new Cell("");
											c.setColspan(4);
											table.addCell(c);
										}											
									}
								}
							}
						}
					}
					
				}
				document.add(table);
			}
			//document.add(table);
		}
		
		//Appendix Image Starts
		LogUtil.logMessage("Appendix Image Starts");
		document.newPage();
		
		if(imageList.size()>0){
			int count =0;
			table =new Table(1);
			c = new Cell(new Phrase("Appendix",strFointSizeBoldEleven));
			c.setBorder(Rectangle.NO_BORDER);
			c.setVerticalAlignment(Element.ALIGN_TOP);
			c.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c);
			document.add(table);
			
			itAppendixImages =imageList.iterator();
			while(itAppendixImages.hasNext()){
				LogUtil.logMessage("Appendix Image no. "+count);
				objModelAppendixVO =(ModelAppendixVO)itAppendixImages.next();
				objFileVO =new FileVO();
				objFileVO.setImageSeqNo(objModelAppendixVO.getFileVO().getImageSeqNo());
				objjFileVO = objFileUploadBO.downloadImage(objFileVO);
				
				LogUtil.logMessage("Appendix Image no. "+count);
				Image image = null;
				byte[] imageView = fetchGenArrangment(objjFileVO);
				
				if(count>0){
					document.newPage();
				}
				
				LogUtil.logMessage("value of imageView "+imageView);
				
				
				table =new Table(1);
				
				if (imageView != null) {
					c = new Cell(new Phrase("\n"));
					c.setBorderColor(new Color(255, 255, 255));
					//c.setColspan(10);
					table.addCell(c);
					image = Image.getInstance(imageView);
					LogUtil.logMessage("value of image "+image);
					image.setAlignment(Element.ALIGN_CENTER);
					if (image.getWidth() > 400 || image.getHeight() > 650) {
						image.scaleToFit(400.0f, 650.0f);
					}
					
					c = new Cell(image);
					c.setHorizontalAlignment(Element.ALIGN_CENTER);
					c.setBorderColor(new Color(255, 255, 255));
					//c.setColspan(10);
					table.addCell(c);
					
					c = new Cell(new Phrase("\n"));
					c.setBorderColor(new Color(255, 255, 255));
					//cel.setColspan(10);
					table.addCell(c);
				
				}
				
				document.add(table);
			count++;
			}
			
			
		}
		
		document.close();
		
	}
	
	private static HeaderFooter generateHeaderForCOC() throws BadElementException, MalformedURLException, IOException{
		Image img = Image.getInstance(MasterSpecByMdlAction.class.getClassLoader().getResource("images/Header.jpg"));
		img.setAlignment(Image.MIDDLE);
		img.scalePercent(100);
		Chunk chunk1 = new Chunk(img, 0, 0);
		HeaderFooter header = new HeaderFooter(new Phrase(chunk1), false);
		header.setAlignment(Element.ALIGN_CENTER);
		header.setBorder(Rectangle.NO_BORDER);
		return header;
	}
	
	private static HeaderFooter generateFooterForCOC() throws BadElementException {
		footerFontBold.setStyle(Font.UNDERLINE);
		Table table =new Table(4);
		table.setDefaultCellBorder(Rectangle.NO_BORDER);
		table.setAbsWidth("100");
		table.setAlignment(Element.ALIGN_RIGHT);
		table.setBorder(Rectangle.NO_BORDER);
		
		Cell c=new Cell(new Phrase("Proprietary Notice: ©Electro Motive Diesel, Inc 2013.",footerFontBold));
		c.setColspan(4);
		table.addCell(c);
		
		c=new Cell(new Phrase("Information contained in this document is proprietary to Electro-Motive Diesel, Inc. No part or whole of this document may be disclosed to third parties, copied or reproduced in any manner without prior written permission of Electro-Motive Diesel, Inc.",footerFont));
		c.setColspan(4);
		table.addCell(c);
		
		Phrase footPhrase = new Phrase("", footerFont);
		footPhrase.add(new RtfPageNumber());
		c = new Cell(footPhrase);
		c.setColspan(2);
		c.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(c);

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh.mm.ss aa");
		String sDate = sdf.format(date);

		c = new Cell(new Phrase(sDate, footerFont));
		c.setColspan(2);
		c.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(c);

		RtfHeaderFooter footer = new RtfHeaderFooter(table);
			return footer;
		
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
	
	
	private static byte[] fetchGenArrangment(FileVO objFileVO) throws Exception {

		try {
			InputStream sImage = objFileVO.getFileStream();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] rb = new byte[1024];
			int ch = 0;

			// get content of a document
			while ((ch = sImage.read(rb)) != -1) {
				output.write(rb, 0, ch);
			}

			byte[] buf = output.toByteArray();

			output.reset();
			output.flush();

			return buf;

		} catch (Exception objExp) {

			throw objExp;
		}

	}
	
	//Added for CR_114 Ends
	
	//Added for CR-130
	public ActionForward viewCharGrpRptbyModelExcel(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		
		/** Declarations **/
		LogUtil
		.logMessage("Inside ViewCharGrpRptbyModel");
		String strForwardKey = ApplicationConstants.CHAR_GRP_RPT_SUCCESS;
		ArrayList sectionList = new ArrayList();
		ArrayList subSectionList;
		ArrayList specList = new ArrayList();
		ArrayList modelSpecList = new ArrayList();
		ArrayList modelSubsectionList = new ArrayList();
		int modelSeqNo;
		String modelName = null;
		
		String specType = null;
		
		MasterSpecByMdlForm objMasterSpecByMdlForm = (MasterSpecByMdlForm) objActionForm;
		
		/** Getting the Modelsequence Number and Model name from the request parameter **/
		specType = objHttpServletRequest
		.getParameter(ApplicationConstants.SELECTED_SPEC_TYPE);
		modelSeqNo = Integer.parseInt(objHttpServletRequest
				.getParameter("modelSeqNo"));
		modelName = objHttpServletRequest.getParameter("modelName");
		
		specType = objHttpServletRequest
		.getParameter(ApplicationConstants.SELECTED_SPEC_TYPE);
		
		LogUtil.logMessage("spec type :" + ApplicationConstants.SELECTED_SPEC_TYPE);
		
		LogUtil.logMessage("Model Seq No :" + modelSeqNo);
		LogUtil.logMessage("ModelName :" + modelName);
		
		
		SectionVO objSectionVO = new SectionVO();
		SubSectionVO objSubSecMaintVO = new SubSectionVO();
		objSectionVO.setModelSeqNo(modelSeqNo);
		objSectionVO.setModelName(modelName);
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		try {
			/** Setting the user Id **/
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			objSectionVO.setUserID(objLoginVo.getUserID());
			
			//Added for PM&I
			objMasterSpecByMdlForm.setHdnSelSpecType(objMasterSpecByMdlForm
					.getHdnSelSpecType());
			
			/** Fetching the sections available for the selected model **/
			
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			sectionList = objSectionBO.fetchSections(objSectionVO);
			Iterator listIterator = null;
			
			LogUtil.logMessage("sectionList first"+sectionList.size());
			
			/** For each section get the subsections and clauses **/
			
			if (sectionList.size() != 0) {
				listIterator = sectionList.iterator();
				while (listIterator.hasNext()) {
					
					subSectionList = new ArrayList();
					objSectionVO = new SectionVO();
					objSubSecMaintVO = new SubSectionVO();
					objSectionVO = (SectionVO) listIterator.next();
					objSubSecMaintVO.setModelSeqNo(modelSeqNo);
					objSubSecMaintVO
					.setSecSeqNo(objSectionVO.getSectionSeqNo());
					
										
					objSubSecMaintVO.setUserID(objLoginVo.getUserID());
					
					/** Get the subsection list for  current section **/
					
					ModelSubSectionBI objSubSecMaintBO = ServiceFactory
					.getSubSecMaintBO();
					subSectionList = objSubSecMaintBO
					.fetchSubSections(objSubSecMaintVO);
					
					/** Add the sub sections for the current section to the list **/
					
					//if (subSectionList.size() != 0) {
					modelSubsectionList.add(subSectionList);
					//}
					
				}

				
				/** Get the clauses for all the subsections in the current section **/
				
				ViewSpecByModelBI objViewSpecByModelBO = ServiceFactory
				.getViewSpecByModelBO();
				specList = (objViewSpecByModelBO
						.viewCharactersiticGrpRpt(objSubSecMaintVO));				
				//modelSpecList.add(specList);
			}
			/** Setting the Values to the action form **/
			
			objMasterSpecByMdlForm.setSectionList(sectionList);
			
			if (modelSubsectionList.size() != 0) {
				
				objMasterSpecByMdlForm
				.setModelSubSectionList(modelSubsectionList);
			}
			objMasterSpecByMdlForm.setModelSpecList(modelSpecList);
			objMasterSpecByMdlForm.setModelSeqNo(modelSeqNo);
			objMasterSpecByMdlForm.setModelName(modelName);
			objMasterSpecByMdlForm.setHdnSelSpecType(specType);
			
			LogUtil.logMessage("after seting spec type  in form :" + objMasterSpecByMdlForm.getHdnSelSpecType());
						
			HSSFWorkbook workBook = new HSSFWorkbook();
			HSSFSheet sheet = workBook.createSheet("CharacteristicGroupReport");//create sheet
			
			HSSFCellStyle cellHeadStyle    = workBook.createCellStyle();
			HSSFCellStyle cellNormalStyle  = workBook.createCellStyle();
			HSSFCellStyle cellSimpleStyle = workBook.createCellStyle();
			HSSFCellStyle cellStyle = workBook.createCellStyle();
			HSSFCellStyle cellCompHeadStyle = workBook.createCellStyle();
			HSSFCellStyle cellTabHeadStyle = workBook.createCellStyle();
			
			HSSFFont headFont = workBook.createFont();
			HSSFFont redFont = workBook.createFont();
			HSSFFont normalFont = workBook.createFont();
			HSSFFont Font = workBook.createFont();
			
			Font.setFontName(HSSFFont.FONT_ARIAL);
			Font.setColor(HSSFColor.BLACK.index);		
			Font.setFontHeightInPoints((short) 10);
			
			headFont.setFontName(HSSFFont.FONT_ARIAL);
			headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			headFont.setColor(HSSFColor.BLACK.index);	
			headFont.setFontHeightInPoints((short) 10);
			
			redFont.setFontName(HSSFFont.FONT_ARIAL);
			redFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			redFont.setColor(HSSFColor.RED.index);	
			redFont.setFontHeightInPoints((short) 10);
	
			normalFont.setFontName(HSSFFont.FONT_ARIAL);
			normalFont.setColor(HSSFColor.BLACK.index);	
			normalFont.setFontHeightInPoints((short) 10);
	
			cellNormalStyle.setWrapText(true);
			cellNormalStyle.setFont(normalFont);
			cellNormalStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellNormalStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
			cellSimpleStyle.setWrapText(true);
			cellSimpleStyle.setFont(normalFont);
			cellSimpleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellSimpleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
			cellStyle.setFont(normalFont);
			cellStyle.setWrapText(true);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			HSSFPalette palette = ((HSSFWorkbook)workBook).getCustomPalette(); 
			palette.setColorAtIndex((short)50, (byte)210, (byte)221, (byte)249); 
			cellStyle.setFillForegroundColor(palette.getColor(50).getIndex()); 
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			
			cellHeadStyle.setWrapText(true);
			cellHeadStyle.setFont(headFont);
			cellHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			palette.setColorAtIndex((short)52, (byte)205, (byte)201, (byte)201); 
			cellHeadStyle.setFillForegroundColor(palette.getColor(51).getIndex()); 
			cellHeadStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			
			HSSFCellStyle cellLabelsStyle = workBook.createCellStyle();	
			
			cellLabelsStyle.setFont(Font);
			cellLabelsStyle.setWrapText(true);
			cellLabelsStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			
			
			HSSFCellStyle cellGreyStyle = workBook.createCellStyle();
			
			cellGreyStyle.setFont(Font);
			cellGreyStyle.setWrapText(true);
			cellGreyStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFPalette palette1 = ((HSSFWorkbook)workBook).getCustomPalette(); 
			palette1.setColorAtIndex((short)47, (byte)192, (byte)192, (byte)192); 
			palette1.setColorAtIndex((short)40, (byte)87, (byte)128, (byte)174); 
			cellGreyStyle.setFillForegroundColor(palette1.getColor(47).getIndex()); 
			cellGreyStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellGreyStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			/*cellGreyStyle.setRightBorderColor(palette1.getColor(40).getIndex());
			cellGreyStyle.setLeftBorderColor(palette1.getColor(40).getIndex());
			cellGreyStyle.setTopBorderColor(palette1.getColor(40).getIndex());
			cellGreyStyle.setBottomBorderColor(palette1.getColor(40).getIndex());
			cellGreyStyle.setBorderBottom(palette1.getColor(40).getIndex());
			cellGreyStyle.setBorderLeft(palette1.getColor(40).getIndex());
			cellGreyStyle.setBorderRight(palette1.getColor(40).getIndex());
			cellGreyStyle.setBorderTop(palette1.getColor(40).getIndex());
			cellGreyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			cellGreyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cellGreyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			cellGreyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);*/
			
			HSSFCellStyle cellLightGreyStyle = workBook.createCellStyle();
			
			cellLightGreyStyle.setFont(Font);
			cellLightGreyStyle.setWrapText(true);
			cellLightGreyStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			palette1.setColorAtIndex((short)45, (byte)224, (byte)224, (byte)224); 
			cellLightGreyStyle.setFillForegroundColor(palette1.getColor(45).getIndex()); 
			cellLightGreyStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellLightGreyStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			/*cellLightGreyStyle.setRightBorderColor(palette1.getColor(40).getIndex());
			cellLightGreyStyle.setLeftBorderColor(palette1.getColor(40).getIndex());
			cellLightGreyStyle.setTopBorderColor(palette1.getColor(40).getIndex());
			cellLightGreyStyle.setBottomBorderColor(palette1.getColor(40).getIndex());
			cellLightGreyStyle.setBorderBottom(palette1.getColor(40).getIndex());
			cellLightGreyStyle.setBorderLeft(palette1.getColor(40).getIndex());
			cellLightGreyStyle.setBorderRight(palette1.getColor(40).getIndex());
			cellLightGreyStyle.setBorderTop(palette1.getColor(40).getIndex());
			cellLightGreyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			cellLightGreyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cellLightGreyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			cellLightGreyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);*/
			
			cellCompHeadStyle.setFont(headFont);
			cellCompHeadStyle.setWrapText(true);
			cellCompHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellCompHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			cellCompHeadStyle.setFillForegroundColor(palette1.getColor(47).getIndex()); 
			cellCompHeadStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			/*cellCompHeadStyle.setRightBorderColor(palette1.getColor(40).getIndex());
			cellCompHeadStyle.setLeftBorderColor(palette1.getColor(40).getIndex());
			cellCompHeadStyle.setTopBorderColor(palette1.getColor(40).getIndex());
			cellCompHeadStyle.setBottomBorderColor(palette1.getColor(40).getIndex());
			cellCompHeadStyle.setBorderBottom(palette1.getColor(40).getIndex());
			cellCompHeadStyle.setBorderLeft(palette1.getColor(40).getIndex());
			cellCompHeadStyle.setBorderRight(palette1.getColor(40).getIndex());
			cellCompHeadStyle.setBorderTop(palette1.getColor(40).getIndex());
			cellCompHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			cellCompHeadStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cellCompHeadStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			cellCompHeadStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);*/
			
			cellTabHeadStyle.setFont(headFont);
			cellTabHeadStyle.setWrapText(true);
			cellTabHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellTabHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			cellTabHeadStyle.setFillForegroundColor(palette1.getColor(45).getIndex()); 
			cellTabHeadStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			/*cellTabHeadStyle.setRightBorderColor(palette1.getColor(40).getIndex());
			cellTabHeadStyle.setLeftBorderColor(palette1.getColor(40).getIndex());
			cellTabHeadStyle.setTopBorderColor(palette1.getColor(40).getIndex());
			cellTabHeadStyle.setBottomBorderColor(palette1.getColor(40).getIndex());
			cellTabHeadStyle.setBorderBottom(palette1.getColor(40).getIndex());
			cellTabHeadStyle.setBorderLeft(palette1.getColor(40).getIndex());
			cellTabHeadStyle.setBorderRight(palette1.getColor(40).getIndex());
			cellTabHeadStyle.setBorderTop(palette1.getColor(40).getIndex());
			cellTabHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			cellTabHeadStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cellTabHeadStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			cellTabHeadStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);*/
			ClauseVO objClauseVO = new ClauseVO();
			
			Iterator ListSecList = null;
			Iterator ListSpecList = null;
			Iterator ListCompList = null;
			Iterator ListCompGrpList = null;
			ArrayList arlSecList = new ArrayList();
			ArrayList arlSpecList = new ArrayList();
			ArrayList arlCompGrpList = new ArrayList();
			ArrayList arlCompList = new ArrayList();
			
			int nRowCount=0;
			int intCol =0;
			int nStartRow=0;	

			
			HSSFRow row = sheet.createRow(nRowCount);
			
			createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString("View Characteristic Group Report"));
			sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),intCol,intCol+9));
			nRowCount = nRowCount+2;
			row = sheet.createRow(nRowCount);
			
			createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString("Specification Type: " +specType));
			sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),intCol,intCol+9));
			nRowCount++;
			row = sheet.createRow(nRowCount);
			
			createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString("Model: " +modelName));
			sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),intCol,intCol+9));
			nRowCount = nRowCount+2;
			row = sheet.createRow(nRowCount);
			
			createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString("All the Clauses shown below are Characteristic Core Clauses"));
			sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),intCol,intCol+9));
			
			LogUtil.logMessage("sectionList"+sectionList.size());
			LogUtil.logMessage("modelSubsectionList"+modelSubsectionList.size());
			LogUtil.logMessage("specList"+specList.size());
			
			for (int i=0;i<sectionList.size();i++){
				
				SectionVO objSecVO = new SectionVO();
				objSecVO = (SectionVO) sectionList.get(i);
				LogUtil.logMessage(objSecVO.getSectionName());
				
				nRowCount++;
				row = sheet.createRow(nRowCount);
				createCell(row,cellSimpleStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString(""));
				sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),intCol,intCol+9));
				nRowCount++;
				row = sheet.createRow(nRowCount);
				
				createCell(row,cellHeadStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString(objSecVO.getSectionDisplay()));
				sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),intCol,intCol+9));
										
				SubSectionVO objSubSectionVO = new SubSectionVO();
							
				ArrayList arlSubsectionList = (ArrayList) modelSubsectionList.get(i);
				
				if (arlSubsectionList != null) {
					
					Iterator listSubSec = arlSubsectionList.iterator();
					
					while (listSubSec.hasNext()){
						
						objSubSectionVO = (SubSectionVO) listSubSec.next();				
						LogUtil.logMessage(objSubSectionVO.getSubSecName());
						
						nRowCount++;
						row = sheet.createRow(nRowCount);
						
						createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString(objSubSectionVO.getSubSecDisplay()));
						sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),intCol,intCol+9));
						nRowCount++;
						row = sheet.createRow(nRowCount);
						
						createHeadingForCharGrpRep(workBook,row,sheet);
						nRowCount++;
						row = sheet.createRow(nRowCount);
						
						int nCounter=0;						
						for (int j=0;j<specList.size();j++){	
							
							objClauseVO = (ClauseVO) specList.get(j);							
							
							String strEnggData = "";
							String strCharEDL = "";
							int nTblDataSize = 0;
							
							if (objClauseVO.getSubSectionSeqNo() == objSubSectionVO.getSubSecSeqNo()) {
								
								if(objClauseVO.getCompGroupVO()!=null){
									arlCompGrpList =(ArrayList) objClauseVO.getCompGroupVO();
									ListCompGrpList = arlCompGrpList.iterator();
									LogUtil.logMessage("arlCompGrpList.size()" +arlCompGrpList.size());
									int nrowSpanCount=0;
									int nSpanCount=0;
									
									
									while(ListCompGrpList.hasNext()){
										CompGroupVO objCompGrpVO = new CompGroupVO();
										objCompGrpVO = (CompGroupVO) ListCompGrpList.next();
										if(objCompGrpVO.getCompVO()!=null){
											ComponentVO objCompVo = new ComponentVO();
											objCompVo = (ComponentVO)objCompGrpVO.getCompVO();
											
											if(nCounter%2 == 0){
												cellNormalStyle = cellLightGreyStyle;
												cellLabelsStyle = cellTabHeadStyle;
											}else{
												cellNormalStyle = cellGreyStyle;
												cellLabelsStyle = cellCompHeadStyle;
											}
											
											HSSFRichTextString richTextClaDesc = new HSSFRichTextString("");
											if(objClauseVO.getClauseDesc()!=null && objClauseVO.getClauseDesc()!=""){
												richTextClaDesc = new HSSFRichTextString(ApplicationUtil.getRefinedClauseDesc(objClauseVO.getClauseDesc().trim()));
											}
											
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
											nTblDataSize = nTblDataSize + arlTableRows.size();
											LogUtil.logMessage("nTblDataSize "+nTblDataSize);
											LogUtil.logMessage("arlTableRows "+arlTableRows.size());
											
											if(nrowSpanCount==0){
												nSpanCount = arlCompGrpList.size()-1;
												nStartRow=row.getRowNum();
												LogUtil.logMessage("nSpanCount " +nSpanCount);
											}
											
											LogUtil.logMessage("nrowSpanCount " +nrowSpanCount);
											
											HSSFRichTextString strCompGrpName = new HSSFRichTextString("*"+objCompVo.getComponentGroupName());
											if((objCompVo.getValidationFlag()!=null)&&(objCompVo.getValidationFlag().equalsIgnoreCase("Y"))){
												createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, intCol,strCompGrpName);
												strCompGrpName.applyFont(0,1,redFont);
											}else
												createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString(objCompVo.getComponentGroupName()));
											if (arlTableRows != null && arlTableRows.size() > 0) {
												sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum()+arlTableRows.size(),intCol,intCol));
											}
											intCol++;
											createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString(objCompVo.getComponentName()));
											if (arlTableRows != null && arlTableRows.size() > 0) {
												sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum()+arlTableRows.size(),intCol,intCol));
											}
											intCol++;
											
											if(objClauseVO.getCharEdlNo() != null && objClauseVO.getCharEdlNo()!= "" ){
												strCharEDL = strCharEDL + "EDL " + objClauseVO.getCharEdlNo()+ "\n";
												LogUtil.logMessage("strCharEDL 1 " + strCharEDL);
											}
											if(objClauseVO.getCharRefEDLNo() !=null && objClauseVO.getCharRefEDLNo() != ""){
												strCharEDL = strCharEDL + "(ref EDL " + objClauseVO.getCharRefEDLNo()+")"+"\n";
												LogUtil.logMessage("strCharEDL " +objClauseVO.getCharRefEDLNo());
											}
											createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString(strCharEDL));
											if (arlTableRows != null && arlTableRows.size() > 0) {
												sheet.addMergedRegion(new CellRangeAddress(nStartRow,nStartRow+nSpanCount+arlTableRows.size(),intCol,intCol));
											}else if(nrowSpanCount == nSpanCount)
												sheet.addMergedRegion(new CellRangeAddress(nStartRow,nStartRow+nSpanCount,intCol,intCol));
											intCol++;
											
											createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString(objClauseVO.getClauseNum()));
											if (arlTableRows != null && arlTableRows.size() > 0) {
												sheet.addMergedRegion(new CellRangeAddress(nStartRow,nStartRow+nSpanCount+arlTableRows.size(),intCol,intCol));
											}
											else if(nrowSpanCount == nSpanCount)
												sheet.addMergedRegion(new CellRangeAddress(nStartRow,nStartRow+nSpanCount,intCol,intCol));
											intCol++;
											
											createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, intCol,richTextClaDesc);
											intCol++;
											createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString(""));
											intCol++;
											createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString(""));
											intCol++;
											createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString(""));
											intCol++;
											createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString(""));
											intCol++;
											
											if(nrowSpanCount == nSpanCount)
													sheet.addMergedRegion(new CellRangeAddress(nStartRow,nStartRow+nSpanCount,intCol-5,intCol-1));
												//sheet.addMergedRegion(new CellRangeAddress(nStartRow,nStartRow+nSpanCount,intCol,intCol+4));
											//intCol=intCol+5;
											//intCol++;
											
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
													strEnggData = strEnggData +"Part of "+ arlPartOF.get(n).toString()+"\n";
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
											
											LogUtil.logMessage("strEnggData "+strEnggData);
											
											createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString(strEnggData));
											if (arlTableRows != null && arlTableRows.size() > 0) {
												sheet.addMergedRegion(new CellRangeAddress(nStartRow,nStartRow+nSpanCount+arlTableRows.size(),intCol,intCol));
											}
											else if(nrowSpanCount == nSpanCount)
												sheet.addMergedRegion(new CellRangeAddress(nStartRow,nStartRow+nSpanCount,intCol,intCol));
											
											if (arlTableRows != null && arlTableRows.size() > 0) {	
												LogUtil.logMessage("arlTableRows1 "+arlTableRows.size());
													 for (int k= 0; k < arlTableRows.size(); k++) {
														 nRowCount++;
															if (nRowCount > sheet.getLastRowNum())
																row = sheet.createRow(nRowCount);
															else    
																row = sheet.getRow(nRowCount);
															
															int colCount = 4;
														
														ArrayList arlTableColumns = (ArrayList) arlTableRows.get(k);
														if (k==0){
															createCell(row,cellLabelsStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(0)));
															LogUtil.logMessage("arlTableColumns.get(0)"+arlTableColumns.get(0));
														}else
															createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(0)));
														
														if (k==0)
															createCell(row,cellLabelsStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(1)));
														else
															createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(1)));
									
														if (k==0)
															createCell(row,cellLabelsStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(2)));
														else
															createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(2)));
									
														if (k==0)
															createCell(row,cellLabelsStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(3)));
														else
															createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(3)));
														
														if (k==0)
															createCell(row,cellLabelsStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(4)));
														else
															createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(4)));
														colCount = colCount - 4;
													}
												}
											
											nrowSpanCount++;									
										}nRowCount++;
										row = sheet.createRow(nRowCount);
										intCol=0;
										strEnggData = "";
										
										LogUtil.logMessage("nCounter in "+nCounter);
										
									}nCounter++;
									LogUtil.logMessage("nCounter out "+nCounter);
								}
							}
						}
					}
				}
			}
			
			objHttpServletResponse.setContentType("application/vnd.ms-excel");
			objHttpServletResponse.setHeader("Content-disposition", "attachment; filename= CharacteristicGroupReport.xls");
			OutputStream out = null;
			out = objHttpServletResponse.getOutputStream();
			workBook.write(out);
		
		} catch (Exception objEx) {
			objEx.printStackTrace();
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		if (strForwardKey.equalsIgnoreCase(ApplicationConstants.FAILURE))
			return objActionMapping.findForward(strForwardKey);
		else
			return null;
	}
	
	
	public void createHeadingForCharGrpRep(HSSFWorkbook workBook, HSSFRow row,
			HSSFSheet sheet) throws Exception {

	HSSFCellStyle cellHeadStyle = workBook.createCellStyle();

	HSSFFont headFont = workBook.createFont();
			
	headFont.setFontName(HSSFFont.FONT_ARIAL);
	headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	headFont.setColor(HSSFColor.BLACK.index);	
	headFont.setFontHeightInPoints((short) 10);

	HSSFPalette palette = ((HSSFWorkbook)workBook).getCustomPalette(); 
	palette.setColorAtIndex((short)51, (byte)162, (byte)192, (byte)224); 
	cellHeadStyle.setFillForegroundColor(palette.getColor(51).getIndex()); 
	cellHeadStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

	cellHeadStyle.setWrapText(true);
	cellHeadStyle.setFont(headFont);
	cellHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	cellHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	cellHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	cellHeadStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	cellHeadStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	cellHeadStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//	row = sheet.createRow(rowCount);
//	Updated for CR-118 QA-Fix

		final String[] columnHeadings = {"Characterstic Component Group", "Characterstic Component", "Characterstic Combination EDl #","Clause No","Clause Description","","","","","Engineering Data"};
		final int[] columnWidth = {5000,5000,5000,2000,2000,2000,2000,2000,2000,6000};
		for (int intPos=0;intPos < 10; intPos++)
			{
			 createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,intPos,new HSSFRichTextString(columnHeadings[intPos]));
				 sheet.setColumnWidth(intPos,columnWidth[intPos]);
				 
			 }
		sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),4,8));
	}
	
	
}
