package com.EMD.LSDB.action.cr1058;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import javax.servlet.ServletOutputStream;
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
import org.apache.struts.upload.FormFile;
import org.json.JSONObject;

//import sun.misc.Request;

import com.EMD.LSDB.action.common.EMDAction;
import com.EMD.LSDB.action.common.PDFView;
import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.ChangeRequest1058BI;
import com.EMD.LSDB.bo.serviceinterface.ChangeRequestBI;
import com.EMD.LSDB.bo.serviceinterface.CustomerBI;
import com.EMD.LSDB.bo.serviceinterface.ModelBI;
import com.EMD.LSDB.bo.serviceinterface.ModelSelectClauseRevBI;
import com.EMD.LSDB.bo.serviceinterface.UserMaintBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.dao.common.DBHelper;
import com.EMD.LSDB.form.CRForm.ModifyChangeRequestForm;
import com.EMD.LSDB.form.cr1058.ChangeRequest1058Form;
import com.EMD.LSDB.form.cr1058.SearchRequest1058Form;
import com.EMD.LSDB.vo.admn.UserVO;
import com.EMD.LSDB.vo.common.ChangeRequest1058AttachmentsVO;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.CustomerVO;
import com.EMD.LSDB.vo.common.FileVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.ChangeRequest1058VO;
import com.EMD.LSDB.vo.common.RequestModelVO;
import com.EMD.LSDB.vo.common.RequestVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfDestination;
import com.lowagie.text.pdf.PdfOutline;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class SearchRequest1058Action extends EMDAction{
	
//	Added for PDF by SM105772 
	public static Font strFointSizeBoldTen = new Font(Font.TIMES_ROMAN, 10,
			Font.BOLD, Color.BLACK);
	
	public static Font strFointSizeTen = new Font(Font.TIMES_ROMAN, 11, 0,
			Color.BLACK);
	
	public static Font strFontRedSizeTen = new Font(Font.TIMES_ROMAN, 11, 0,
			Color.RED);
	
	public static Font strFointSizeEight = new Font(Font.TIMES_ROMAN, 10, 0,
			Color.BLACK);
	
	public static Font strFontSizeBoldEleven = new Font(Font.TIMES_ROMAN, 11,
			Font.BOLD, Color.BLACK);
	
	public static Font strFontSizeNoBoldTen = new Font(Font.TIMES_ROMAN, 12, 0,
			Color.BLACK);
	
	public static Font strFontSizeBoldTen = new Font(Font.TIMES_ROMAN, 11,
			Font.BOLD, Color.BLACK);
	
	public static Font strFontSizeBoldtwelve = new Font(Font.TIMES_ROMAN, 12,
			Font.BOLD, Color.BLACK);
	
	public static Font strFointSizeNoBoldTen1 = new Font(Font.TIMES_ROMAN, 10,
			0, Color.BLACK);
	
	public static Font strSSFointSizeNine = new Font(Font.TIMES_ROMAN, 10, 0,
			Color.BLACK);
	
	public static Font strFlagFont;
	
	public static Font strClaNoFlagFont;
	
	public static Font strFontSizeEight = new Font(Font.TIMES_ROMAN, 8, 0,
			Color.BLACK);
	
	public static String strPastClauseNo = "";
	
	public static String strPreClauseNo = "";
	
	public static String strPastClauseDesc = "";
	
	public static String strPreClauseDesc = "";
	
	public static String strPastReason = "";
	
	public static String strPreReason = "";
		
	public static String strChngFromSpecSec= "";
	
	public static String strChngFromClaDesc= "";
	
	public static String strChngFromEnggData= "";
	
	public static String strChngToSpecSec= "";
	
	public static String strChngToClaDesc= "";
	
	public static String strChngToEnggData= "";
	
	public static String strnLsdbReason= "";
	
	public static String strChangeAffectsWeight= "";
	
	public static String strChangeAffectsClear= "";
	
	public static String strMcrReq="";
	
	public static String strDollar="$";
	
	public static BigDecimal bdActualSellPrice;
	
	public static BigDecimal bdWorkOrderUSD;
	
	public static BigDecimal bdToolingCostUSD;
	
	public static BigDecimal bdScrapCostUSD;
	
	public static BigDecimal bdReWorkCostUSD;
	
	public static BigDecimal bdSellPriceCustomer;
	
	public static BigDecimal bdMarkUp;
	
	//Added for CR-130
	public static String strSubSecReason = ""; 
	public static String strClauseNo = "";
	public static String strClauseDesc = "";
	public static String strEnggData = "";
	//Added for CR-130 ends here

	//private static ChangeRequest1058Form objImageDetailsVO;
	
	
	
	public ActionForward initLoad(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		LogUtil.logMessage("Entering Search1058Requestaction:initload");
		String strForwardKey = ApplicationConstants.SUCCESS;
		SearchRequest1058Form objSearchRequest1058Form = (SearchRequest1058Form)objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		//Added for CR-117 for Legacy 1058 Requests
		String strScreenID;
		try
		{			
			//Added for CR-117 for Legacy 1058 Requests
			strScreenID= Integer.toString(objLoginVo.getIntScreenId());
			if (strScreenID.equalsIgnoreCase("48")) {
				
				LogUtil.logMessage("Entering Search1058Requestaction:initload: fetching Status list");			
				ChangeRequest1058VO objChangeRequest1058VO=new ChangeRequest1058VO();
				ArrayList arlStatusList 	  = new ArrayList();
				objChangeRequest1058VO.setUserID(objLoginVo.getUserID());			
				ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
				arlStatusList = objChangeRequest1058BI.fetch1058Status(objChangeRequest1058VO);
				LogUtil.logMessage("" + arlStatusList.size());
				objSearchRequest1058Form.setStatusList(arlStatusList);
				//Added For CR-120
				ArrayList arlIssuedByUserList = new ArrayList();
				UserVO objUserVO = new UserVO();
				objUserVO.setUserID(objLoginVo.getUserID());
				objUserVO.setOrderbyFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
				arlIssuedByUserList = objChangeRequest1058BI.fetchIssuedByUserList(objUserVO);
				objSearchRequest1058Form.setIssuedByUserList(arlIssuedByUserList);
				
				//Added for CR-126
				LogUtil.logMessage("objSearchRequest1058Form.getEditLegacyFlag()"+
						objSearchRequest1058Form.getEditLegacyFlag());
				if(objSearchRequest1058Form.getEditLegacyFlag() == null){
					objSearchRequest1058Form.setEditLegacyFlag("N");
				}
				if(objSearchRequest1058Form.getEditLegacyFlag().equals("Y") && 
				   objSearchRequest1058Form.getEditLegacyFlag() != null){
					
					String str1058SeqNO = objHttpServletRequest.getParameter("1058SeqNo");
					ArrayList arlEditLegacyList = new ArrayList(); 
					objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
					objChangeRequest1058VO.setSeqNo1058(Integer.parseInt(str1058SeqNO));
					arlEditLegacyList = objChangeRequest1058BI.fetch1058Details(objChangeRequest1058VO);	
					LogUtil.logMessage("arlEditLegacyList.size():"+arlEditLegacyList.size());
					objSearchRequest1058Form.setEditLegacyList(arlEditLegacyList);
					
				}
				strForwardKey = ApplicationConstants.LEGACY1058REQUESTS;
			}else if(strScreenID.equalsIgnoreCase("51")){
				LogUtil
				.logMessage("Entering Search1058Requestaction:initload: fetching 1058 details");
				strForwardKey = ApplicationConstants.REPORTS; //Added for CR-126 ends here
			}else{
			
			LogUtil.logMessage("Entering Search1058Requestaction:initload:fetching customer list");			
			CustomerVO objCustomerVO=new CustomerVO();
			ArrayList arlCustomerList = new ArrayList();
			objCustomerVO.setUserID(objLoginVo.getUserID());			
			CustomerBI objCustomerBI = ServiceFactory.getCustomerBO();
			arlCustomerList = objCustomerBI.fetchCustomers(objCustomerVO);			
			LogUtil.logMessage("" + arlCustomerList.size());
			
			objSearchRequest1058Form.setCustNameList(arlCustomerList);
			
			LogUtil.logMessage("Entering Search1058Requestaction:initload: fetching user list");
			UserVO objUserVO=new UserVO();			
			ArrayList arlUserList = new ArrayList();
			//Added for CR-117 for Sorting Lastname in Awaiting action on Drop Down
			objUserVO.setOrderbyFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
			objSearchRequest1058Form.setRole(objLoginVo.getRoleID());
			objUserVO.setUserID(objLoginVo.getUserID());			
			UserMaintBI objUserBI = ServiceFactory.getUserMaintBO();
			arlUserList = objUserBI.fetchUsers(objUserVO);
			LogUtil.logMessage("" + arlUserList.size());
			objSearchRequest1058Form.setUserList(arlUserList);
			objSearchRequest1058Form.setAwApproval(objLoginVo.getUserID());
			
			LogUtil.logMessage("Entering Search1058Requestaction:initload: fetching Model list");
			ModelVo objModelVO=new ModelVo();
			ArrayList arlModelList = new ArrayList();
			objModelVO.setUserID(objLoginVo.getUserID());			
			ModelBI objModelBI = ServiceFactory.getModelBO();
			arlModelList = objModelBI.fetchModels(objModelVO);
			LogUtil.logMessage("" + arlModelList.size());
			objSearchRequest1058Form.setMdlNameList(arlModelList);
			
			LogUtil.logMessage("Entering Search1058Requestaction:initload: fetching Status list");			
			ChangeRequest1058VO objChangeRequest1058VO=new ChangeRequest1058VO();
			ArrayList arlStatusList = new ArrayList();	
			objChangeRequest1058VO.setUserID(objLoginVo.getUserID());			
			ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
			arlStatusList = objChangeRequest1058BI.fetch1058Status(objChangeRequest1058VO);
			LogUtil.logMessage("" + arlStatusList.size());
			
			objSearchRequest1058Form.setStatusList(arlStatusList);
			}		
		}
		
		catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForwardKey);

}
	
	public ActionForward fetchDetails(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		LogUtil.logMessage("Entering Search1058Requestaction:fetchDetails");
		String strForwardKey = ApplicationConstants.SUCCESS;
		SearchRequest1058Form objSearchRequest1058Form = (SearchRequest1058Form)objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		try
		{
			ArrayList arlRequestList = new ArrayList();			
			ChangeRequest1058VO objChangeRequest1058VO=new ChangeRequest1058VO();
			objChangeRequest1058VO.setUserID(objLoginVo.getUserID());
			
			LogUtil.logMessage("Entering Search1058Requestaction:fetchDetails:fetching customer list");			
			CustomerVO objCustomerVO=new CustomerVO();
			ArrayList arlCustomerList = new ArrayList();
			objCustomerVO.setUserID(objLoginVo.getUserID());			
			CustomerBI objCustomerBI = ServiceFactory.getCustomerBO();
			arlCustomerList = objCustomerBI.fetchCustomers(objCustomerVO);			
			LogUtil.logMessage("" + arlCustomerList.size());
			
			objSearchRequest1058Form.setCustNameList(arlCustomerList);
			
			LogUtil.logMessage("Entering Search1058Requestaction:fetchDetails: fetching user list");
			UserVO objUserVO=new UserVO();			
			ArrayList arlUserList = new ArrayList();
			//Added for CR-117 for Sorting Lastname in Awaiting action on Drop Down
			objUserVO.setOrderbyFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
			objUserVO.setUserID(objLoginVo.getUserID());			
			UserMaintBI objUserBI = ServiceFactory.getUserMaintBO();
			arlUserList = objUserBI.fetchUsers(objUserVO);
			LogUtil.logMessage("" + arlUserList.size());
			objSearchRequest1058Form.setUserList(arlUserList);
			
			LogUtil.logMessage("Entering Search1058Requestaction:fetchDetails: fetching Model list");
			ModelVo objModelVO=new ModelVo();
			ArrayList arlModelList = new ArrayList();
			objModelVO.setUserID(objLoginVo.getUserID());			
			ModelBI objModelBI = ServiceFactory.getModelBO();
			arlModelList = objModelBI.fetchModels(objModelVO);
			LogUtil.logMessage("" + arlModelList.size());
			objSearchRequest1058Form.setMdlNameList(arlModelList);
			
			LogUtil.logMessage("Entering Search1058Requestaction:fetchDetails: fetching Status list");			
			objChangeRequest1058VO=new ChangeRequest1058VO();
			ArrayList arlStatusList = new ArrayList();	
			objChangeRequest1058VO.setUserID(objLoginVo.getUserID());			
			ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
			arlStatusList = objChangeRequest1058BI.fetch1058Status(objChangeRequest1058VO);
			LogUtil.logMessage("" + arlStatusList.size());
			
			objSearchRequest1058Form.setStatusList(arlStatusList);
			
			
			objChangeRequest1058VO.setCustSeqNo(objSearchRequest1058Form.getCustSeqNo());
			objChangeRequest1058VO.setModelSeqNos(objSearchRequest1058Form.getModelSeqNos());
			objChangeRequest1058VO.setStatusSeqNos(objSearchRequest1058Form.getStatusSeqNos());
			
			//Added for CR-126
			
			//Modified for issue with having the firstname instead of the userid Changed "FirstName" -> "UserId"
			String strSessionUserId = objSession.getAttribute("UserId").toString();
			String strSessionLastName  = objSession.getAttribute("LastName").toString();
			String strFormFirstName    = objSearchRequest1058Form.getAwApproval();
			strSessionUserId = strSessionUserId.trim();
			if(strFormFirstName != null)	strFormFirstName = strFormFirstName.trim();
			strSessionLastName = strSessionLastName.trim();
			
			
			String strEmailFlag = objHttpServletRequest.getParameter("EmailFlag");
			LogUtil.logMessage("strEmailFlag:"+strEmailFlag);
			if(strEmailFlag == null || strEmailFlag == "" || strEmailFlag.length() == 0){
				strEmailFlag = "false";
			}
			//LogUtil.logMessage("strEmailFlag:"+strEmailFlag);
			if(strEmailFlag == "true" || strEmailFlag.equalsIgnoreCase("true")){
				objChangeRequest1058VO.setAwApproval(strSessionUserId);
				objSearchRequest1058Form.setHdnSelectedAwApproval(strSessionLastName);
				objSearchRequest1058Form.setAwApproval(strSessionUserId); //Added for issue with dropdown not loaded when loading from email
			}else{
				if(strFormFirstName != null) //Added for CR-128
				objChangeRequest1058VO.setAwApproval(strFormFirstName);
			}
			 /*if(strFormFirstName == null){
				 objChangeRequest1058VO.setAwApproval(strSessionFirstName);
			 	 objSearchRequest1058Form.setHdnSelectedAwApproval(strSessionLastName);
			 }*/	 
			/* Commented for CR-128 if(strFormFirstName != null){
				if(strSessionFirstName == strFormFirstName
					|| strSessionFirstName.equalsIgnoreCase(strFormFirstName)){
					objChangeRequest1058VO.setAwApproval(strSessionFirstName);
					objSearchRequest1058Form.setHdnSelectedAwApproval(strSessionLastName);
				}else{
					objChangeRequest1058VO.setAwApproval(strFormFirstName);
				 }
			 }*/
			 //Added for CR-126 ends here
			 
			objChangeRequest1058VO.setOrderNo(objSearchRequest1058Form.getOrderNo());
			
			objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();			
			arlRequestList = objChangeRequest1058BI.fetch1058Details(objChangeRequest1058VO);	
			LogUtil.logMessage("arlRequestList.size():"+arlRequestList.size());
			
			objSearchRequest1058Form.setRequestList(arlRequestList);
			
			}catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForwardKey);

}
	/***************************************************************************
	 * This method is for loading the PDF
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws BusinessException
	 * @author VV49326
	 **************************************************************************/
	public ActionForward createPDF(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		LogUtil.logMessage("Inside the SearchRequest1058Action:CreatePDF");
		SearchRequest1058Form objSearchRequest1058Form = (SearchRequest1058Form)objActionForm;
		String strForwardKey = ApplicationConstants.SUCCESS;
		int i = 0;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		String strUserID = null;
		String[] seqNo = null;
		//HttpServletRequest strRequestUrl=null;
		
		
		try {
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			//Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			//To get Request ID Values from Servlet Request 
			LogUtil.logMessage("chk1058SeqNo value:"
					+ objHttpServletRequest.getParameterValues("chk1058SeqNo"));
			//Added for CR-127 starts here
			if (objHttpServletRequest.getParameterValues("chk1058SeqNo") != null)
			{
				seqNo = objHttpServletRequest.getParameterValues("chk1058SeqNo");
			}else{
					LogUtil.logMessage("SeqNo " +objHttpServletRequest.getParameter("seqNo"));
					String strSeqNO = objHttpServletRequest.getParameter("seqNo");
					LogUtil.logMessage("before seqno:");
					seqNo = new String[1];
					seqNo[0] = strSeqNO;
					LogUtil.logMessage("1058 seqNo Values Array length"
							+ seqNo.length);
			}
			//Added for CR-127 ends here
			
			LogUtil.logMessage("1058 seqNo Values Array length"
					+ seqNo.length);
			if (seqNo.length > 0) {
				LogUtil.logMessage("1058 seqNo Values Array length"
						+ seqNo.length);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				Document document = new Document(PageSize.A4, 7, 0, 40, 120);//Updated for PDF Logo Changes - CR-130
				PdfWriter writer = PdfWriter.getInstance(document, baos);
				
                
				document.addAuthor("EMD-LSDB");
				document.addSubject("EMD-LSDB SEARCH 1058 REQUEST PDF ");
				document.addCreationDate();
				document.open();
				document.newPage();
				
				//Commented as handled in OnEndPage Event
				//Show Header and Footer
				//showHeaderFooter(document);
				
				//Show PDF document			
				for (i = 0; i < seqNo.length; i++) {
					
					LogUtil.logMessage("SeqNo" + seqNo[i]);
					
					if (seqNo[i] != null && (!"".equals(seqNo[i]))) {
						
						PdfDestination d1 = new PdfDestination(
								PdfDestination.FIT);
						PdfOutline root = writer.getRootOutline();
						LogUtil.logMessage("1058seqNo from Form" + seqNo[i]);
						LogUtil.logMessage("seqNo.length" + seqNo.length); 
						LogUtil.logMessage("Error is here"); 
						//Generate PDF main document
						showChangeControl(document, writer, d1, root, seqNo[i],
								strUserID, i ,seqNo.length,objHttpServletRequest);
					

					}
				}
				
				document.close();
				
				// setting the content type
				objHttpServletResponse.setContentType("application/pdf");
				objHttpServletResponse.setHeader("Content-disposition",
				"attachment;filename=1058SearchRequest.pdf");
				objHttpServletResponse.setContentLength(baos.size());
				
				// write ByteArrayOutputStream to the ServletOutputStream
				ServletOutputStream out = objHttpServletResponse.getOutputStream();
				baos.writeTo(out);
				out.flush();
			}
			
		} catch (Exception objExp) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
			
		}
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***************************************************************************
	 * This method is for generating the PDF main page
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param Document
	 * @param PdfWriter
	 * @param PdfWriter
	 * @param PdfOutline
	 * @param String
	 * @throws Exception
	 * @author VV49326
	 **************************************************************************/
	private static void showChangeControl(Document document, PdfWriter writer,
			PdfDestination d1, PdfOutline root, String seqNo, String strUserID,
			int i,int seqNoLength,HttpServletRequest objHttpServletRequest) throws Exception {
		//Added for CR-135 -- For PDF atttachments -- To fetch the URL
		String strRequestUrl = objHttpServletRequest.getScheme() +"://"+ objHttpServletRequest.getServerName() + ":" + objHttpServletRequest.getServerPort();
		
		
		
		LogUtil
		.logMessage("Inside the Search1058Requestaction:showChangeControl");
		
		ChangeRequest1058VO objChangeRequest1058Vo = new ChangeRequest1058VO();
		ChangeRequest1058VO objChangeRequest1058VO = new ChangeRequest1058VO();
		ChangeRequest1058VO objClauseDetailVO = new ChangeRequest1058VO();
		
		ArrayList arlReqCommonDetails 	= new ArrayList();
		ArrayList arlLsdbClauseDetails 	= new ArrayList();
		ArrayList arlSubSectionDetails 	= new ArrayList(); //Added for CR-130
		Iterator commonListIterator		= null;//Added for CR-130
		Iterator clauseListIterator		= null;//Added for CR-130
		ArrayList arlSubSecClauseDetails = new ArrayList();//Added for CR-130
		ArrayList arlNLsdbClauseDetails = new ArrayList();
		ArrayList arlFromTableData = new ArrayList();
		ArrayList arlFromData = new ArrayList();
		Integer intFromColCnt = new Integer(0);
		Integer intToColCnt = new Integer(0);
		ArrayList arlToTableData = new ArrayList();
		ArrayList arlToData = new ArrayList();
		int cnt = 0;
		int lsdbcnt = 0;
		int nSubSecCnt = 0;//Added for CR-130
		int nLsdbcnt = 0;
		int intModelSeqNo = 0;
		int intSubSecSeqNo = 0;
		int intToHeader = 0;
		int toTabColCnt = 0;
		int intHeader = 0;
		Font fontHeader;
		Font fontToHeader;
		//For Request Details
		String strOrderNumber = "";
		String strCustName = "";
		String strQuantity = "";
		String strModelName = "";
		String strStatus = "";
		String strSpecRev = "";
		
		String strWaterMarkFlag=ApplicationConstants.NO;
		String strPDFHeaderFlag=ApplicationConstants.YES;
		
		// Table for Header in first Page
		PdfPTable header = new PdfPTable(1);
		header.setWidthPercentage(80);
		
		
		//Table for Request ID and Status
		PdfPTable pdfRequestHeading = new PdfPTable(4);
		pdfRequestHeading.setWidthPercentage(80);
		
		//Table for Request Model Details
		int[] reqMdl = { 48, 2, 50 };
		PdfPTable pdfReqMdl = new PdfPTable(3);
		pdfReqMdl.setWidths(reqMdl);
		pdfReqMdl.setWidthPercentage(80);
		
		//Table for Request Description
		int[] reqDesc = { 48, 2, 50 };
		PdfPTable pdfReqDesc = new PdfPTable(3);
		pdfReqDesc.setWidths(reqDesc);
		pdfReqDesc.setWidthPercentage(80);
		
		//Table for Request Submitted Info
		int[] submittedInfoWidth = { 30, 2, 28, 10, 30 };
		PdfPTable submittedInfo = new PdfPTable(5);
		submittedInfo.setWidths(submittedInfoWidth);
		submittedInfo.setWidthPercentage(80);
		
		//Table for Request CompGrp Change Type
		int[] compGrpChange = { 60, 40 };
		PdfPTable reqCompGrpChngeType = new PdfPTable(2);
		reqCompGrpChngeType.setWidthPercentage(80);
		reqCompGrpChngeType.setWidths(compGrpChange);
		
		int[] width = { 14, 14, 50, 14 };
		int[] reqChngWidth = { 28, 28, 28, 16 };
		int[] sectionsWidth = { 28, 28, 28, 16 };
		int[] actionsWidth = { 28, 28, 16, 28 };
		//int[] attach = {25, 25, 25, 25};
		
		//Table for Request Details
		PdfPTable reqDetails = new PdfPTable(2);
		reqDetails.setWidthPercentage(90);
		
		//Table for User Details
		PdfPTable userDetails = new PdfPTable(2);
		userDetails.setWidthPercentage(90);
		
		//Table for Requested change  Details
		PdfPTable reqChange = new PdfPTable(4);
		reqChange.setWidthPercentage(90);
		reqChange.setWidths(reqChngWidth);
		
		/*//Table for Attachments CR-135
		PdfPTable attachment = new PdfPTable(2);
		attachment.setWidthPercentage(90);*/
		
		
		//Table for RequestSpecChange  Details
		/*PdfPTable reqSpecChange = new PdfPTable(4);
		reqSpecChange.setWidthPercentage(90);
		reqSpecChange.setWidths(width);*/
		
		//Table for RequestEffectivity Details
		PdfPTable reqEffectivity = new PdfPTable(3);
		reqEffectivity.setWidthPercentage(90);
		
		//Table for SystemEngineering Section Details
		PdfPTable sysEnggSection = new PdfPTable(4);
		sysEnggSection.setWidths(sectionsWidth);
		sysEnggSection.setWidthPercentage(90);
		
		
		//Table for OperationSection Details
		PdfPTable opSection = new PdfPTable(4);
		opSection.setWidths(sectionsWidth);
		opSection.setWidthPercentage(90);
		
		
		//Table for FinanceSection Details
		PdfPTable finSection = new PdfPTable(4);
		finSection.setWidths(sectionsWidth);
		finSection.setWidthPercentage(90);
		
		//Table for ProgramManagerSection Details
		PdfPTable prgMgrSection = new PdfPTable(4);
		prgMgrSection.setWidths(sectionsWidth);
		prgMgrSection.setWidthPercentage(90);
		
		//Table for ProposalManagerSection Details
		PdfPTable propMgrSection = new PdfPTable(4);
		propMgrSection.setWidths(sectionsWidth);
		propMgrSection.setWidthPercentage(90);
		
		//Table for ActionRecord Details
		PdfPTable actionDetails = new PdfPTable(4);
		actionDetails.setWidthPercentage(90);
		actionDetails.setWidths(actionsWidth);
		
		
		//Table for From Details
		int clauseWidth[] = { 35, 65 };
		PdfPTable pdfFromClause = new PdfPTable(2);
		pdfFromClause.setWidths(clauseWidth);
		pdfFromClause.setWidthPercentage(80);
		
		//Table for From Details
		
		PdfPTable pdfToClause = new PdfPTable(2);
		pdfToClause.setWidths(clauseWidth);
		pdfToClause.setWidthPercentage(80);
		
		//Table for Reason
		int[] rea = { 30, 2, 68 };
		PdfPTable pdfReason = new PdfPTable(3);
		pdfReason.setWidths(rea);
		pdfReason.setWidthPercentage(80);
		
		//Table for CR-59 added for Administrator Comments
		int[] comments = { 30, 2, 68 };
		PdfPTable pdfComents = new PdfPTable(3);
		pdfComents.setWidths(comments);
		pdfComents.setWidthPercentage(80);
		
		//		Table for Request Model Details
		int[] reqApplyModel = { 48, 2, 50 };
		PdfPTable pdfApplyModel = new PdfPTable(3);
		pdfApplyModel.setWidths(reqApplyModel);
		pdfApplyModel.setWidthPercentage(80);
		
		//Added for CR_80 CR Form Enhancements III - color flag on 20-Nov-09 by RR68151
		Phrase enggData = new Phrase();
		String strFromClauseVerNo = "";

		//Table for Effected Items
		int[] effItem = { 28, 10, 62 };
		PdfPTable effItemsTable = new PdfPTable(3);
		effItemsTable.setWidthPercentage(80);
		effItemsTable.setWidths(effItem);
		
//		Added for CR-130
		FontFactory.register("/images/times.ttf",BaseFont.HELVETICA);
		FontFactory.register("/images/timesbd.ttf",BaseFont.HELVETICA_BOLD);
		FontFactory.register("/images/timesi.ttf",BaseFont.HELVETICA_OBLIQUE);
		FontFactory.register("/images/timesbi.ttf",BaseFont.HELVETICA_BOLDOBLIQUE);
		
		BaseFont customBaseFont = BaseFont.createFont("/images/times.ttf",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
		Font customFont = new Font(customBaseFont,12);
		//Added for CR-130 ends
		
		try {
			objChangeRequest1058Vo.setSeqNo1058(Integer.parseInt(seqNo.trim()));
			LogUtil.logMessage("seqNo: " + objChangeRequest1058Vo.getSeqNo1058());
			
			if (seqNoLength != 1){
				PDFView objMyPage = new PDFView(strWaterMarkFlag,strPDFHeaderFlag,"","");
				writer.setPageEvent(objMyPage);
				}
			
			objChangeRequest1058Vo.setUserID(strUserID);
			
			//To fetch 1058 Request Details
			ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
			arlReqCommonDetails = objChangeRequest1058BI.fetch1058Details(objChangeRequest1058Vo);
			
			
			LogUtil.logMessage("Request 1058 Size" + arlReqCommonDetails.size());
			
			if (arlReqCommonDetails != null & arlReqCommonDetails.size() > 0) {

				for (cnt = 0; cnt < arlReqCommonDetails.size(); cnt++) {

					objChangeRequest1058VO = (ChangeRequest1058VO) arlReqCommonDetails.get(cnt);
					
					LogUtil.logMessage(Integer.toString(objChangeRequest1058VO.getSeqNo1058()));
					LogUtil.logMessage(objChangeRequest1058VO.getStatusDesc());
					
					/* To generate Header in first Page - Starts Here*/
					if (i == 0) {
						
						document.newPage();
						PdfPCell cel = new PdfPCell(new Paragraph(
								"LOCOMOTIVE SPECIFICATION CHANGE REQUEST “1058”",
								strFontSizeBoldtwelve));
						cel.setHorizontalAlignment(Element.ALIGN_CENTER);
						cel.setBorderColor(new Color(255, 255, 255));
						header.addCell(cel);
						document.add(header);
						document.add(new Paragraph("\n"));
					}
					/* To generate Header in first Page - Starts Here*/
					
					
					
					/*For creating bookmark for every Request ID - Starts Here*/
					if (i > 0) {
						document.newPage();
						document.add(new Paragraph("\n"));
					}
					d1 = new PdfDestination(PdfDestination.FIT);
					root = writer.getRootOutline();
					PdfOutline oline1 = new PdfOutline(root, d1, objChangeRequest1058VO
							.getOrderNo());
					oline1.setOpen(false);
					
					//Request Details Section
					
					PdfPCell reqDetail = new PdfPCell(new Phrase(
							"REQUEST DETAILS", strFontSizeBoldEleven));
					reqDetail.setFixedHeight(32f);
					reqDetail.setBackgroundColor(new Color(184, 204, 228));
					reqDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					reqDetail.setHorizontalAlignment(Element.ALIGN_CENTER);
					reqDetail.setColspan(2);
					reqDetails.addCell(reqDetail);
					
					reqDetail = new PdfPCell(new Phrase("Order number",
							strFontSizeBoldEleven));
					reqDetail.setFixedHeight(28f);
					reqDetail.setBackgroundColor(new Color(213, 213, 213));
					reqDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					reqDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqDetails.addCell(reqDetail);
					
					reqDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getOrderNo(),
							strFointSizeTen));
					reqDetail.setBackgroundColor(new Color(255, 255, 255));
					reqDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					reqDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqDetails.addCell(reqDetail);
					
					//Added For CR-120 for created on field
					reqDetail = new PdfPCell(new Phrase("Created on",
							strFontSizeBoldEleven));
					reqDetail.setFixedHeight(28f);
					reqDetail.setBackgroundColor(new Color(213, 213, 213));
					reqDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					reqDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqDetails.addCell(reqDetail);
					
					reqDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getCreatedOn(),
							strFointSizeTen));
					reqDetail.setBackgroundColor(new Color(255, 255, 255));
					reqDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					reqDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqDetails.addCell(reqDetail);
					//Added For CR-120 for created on field Ends Here
					
					reqDetail = new PdfPCell(new Phrase("1058 #",
							strFontSizeBoldEleven));
					reqDetail.setFixedHeight(28f);
					reqDetail.setBackgroundColor(new Color(213, 213, 213));
					reqDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					reqDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqDetails.addCell(reqDetail);
					
					reqDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getId1058(),
							strFointSizeTen));
					reqDetail.setBackgroundColor(new Color(255, 255, 255));
					reqDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					reqDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqDetails.addCell(reqDetail);
					
					reqDetail = new PdfPCell(new Phrase("Customer name",
							strFontSizeBoldEleven));
					reqDetail.setFixedHeight(28f);
					reqDetail.setBackgroundColor(new Color(213, 213, 213));
					reqDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					reqDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqDetails.addCell(reqDetail);
					
					reqDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getCustName(),
							strFointSizeTen));
					reqDetail.setBackgroundColor(new Color(255, 255, 255));
					reqDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					reqDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqDetails.addCell(reqDetail);
					
					reqDetail = new PdfPCell(new Phrase(
							"Quantity", strFontSizeBoldEleven));
					reqDetail.setFixedHeight(28f);
					reqDetail.setBackgroundColor(new Color(213, 213, 213));
					reqDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					reqDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqDetails.addCell(reqDetail);
					
					reqDetail = new PdfPCell(new Phrase(Integer.toString(objChangeRequest1058VO.getOrderQty()),
							strFointSizeTen));
					reqDetail.setBackgroundColor(new Color(255, 255, 255));
					reqDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					reqDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqDetails.addCell(reqDetail);
					
					reqDetail = new PdfPCell(new Phrase(
							"Model", strFontSizeBoldEleven));
					reqDetail.setFixedHeight(28f);
					reqDetail.setBackgroundColor(new Color(213, 213, 213));
					reqDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					reqDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqDetails.addCell(reqDetail);
					
					reqDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getCustMdlName(),
							strFointSizeTen));
					reqDetail.setBackgroundColor(new Color(255, 255, 255));
					reqDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					reqDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqDetails.addCell(reqDetail);
					
					reqDetail = new PdfPCell(new Phrase(
							"Status", strFontSizeBoldEleven));
					reqDetail.setFixedHeight(28f);
					reqDetail.setBackgroundColor(new Color(213, 213, 213));
					reqDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					reqDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqDetails.addCell(reqDetail);
					
					if(objChangeRequest1058VO.getStatusSeqNo() == 1){
						reqDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getStatusDesc(),
								new Font(Font.TIMES_ROMAN, 11, Font.BOLD,new Color(4, 74, 252))));
					}else if(objChangeRequest1058VO.getStatusSeqNo() == 2 
						  || objChangeRequest1058VO.getStatusSeqNo() > 5 
						  && objChangeRequest1058VO.getStatusSeqNo() != 10){
						reqDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getStatusDesc(),
								new Font(Font.TIMES_ROMAN, 11, Font.BOLD,new Color(180, 186, 5))));
					}else if(objChangeRequest1058VO.getStatusSeqNo() == 3){
						reqDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getStatusDesc(),
								new Font(Font.TIMES_ROMAN, 11, Font.BOLD,new Color(0, 255, 128))));
					}else if(objChangeRequest1058VO.getStatusSeqNo() == 4){
						reqDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getStatusDesc(),
								new Font(Font.TIMES_ROMAN, 11, Font.BOLD,new Color(250, 88, 88))));
					}else if(objChangeRequest1058VO.getStatusSeqNo() == 5){
						reqDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getStatusDesc(),
								new Font(Font.TIMES_ROMAN, 11, Font.BOLD,new Color(255, 191, 0))));
					}else if(objChangeRequest1058VO.getStatusSeqNo() == 10){ //Added for CR-117 to Cancel Status
						reqDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getStatusDesc(),
								new Font(Font.TIMES_ROMAN, 11, Font.BOLD,new Color(139, 137, 137))));
					}	
					reqDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					reqDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqDetails.addCell(reqDetail);
					
					reqDetail = new PdfPCell(new Phrase(
							"Specification revision", strFontSizeBoldEleven));
					reqDetail.setFixedHeight(28f);
					reqDetail.setBackgroundColor(new Color(213, 213, 213));
					reqDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					reqDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqDetails.addCell(reqDetail);
					
					reqDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getSpecRev(),
							strFointSizeTen));
					reqDetail.setBackgroundColor(new Color(255, 255, 255));
					reqDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					reqDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqDetails.addCell(reqDetail);
					
					
					//Added for Footer and header EMD-Logo
					if (seqNoLength == 1){
					PDFView objMyPage = new PDFView(strWaterMarkFlag,strPDFHeaderFlag,objChangeRequest1058VO.getId1058(),"");
					writer.setPageEvent(objMyPage);
					}
					//Added for Footer and header EMD-Logo Ends here
					
					document.add(reqDetails);
					document.add(new Paragraph("\n"));
					
					/*For RequestDetails Desc - Ends Here*/
					
					//User Deatils Section
					
					PdfPCell userDetail = new PdfPCell(new Phrase(
							"USER DETAILS", strFontSizeBoldEleven));
					userDetail.setBackgroundColor(new Color(184, 204, 228));
					userDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					userDetail.setHorizontalAlignment(Element.ALIGN_CENTER);
					userDetail.setColspan(2);
					userDetail.setFixedHeight(32f);
					userDetails.addCell(userDetail);
					
					userDetail = new PdfPCell(new Phrase("Issued by",
							strFontSizeBoldEleven));
					userDetail.setBackgroundColor(new Color(213, 213, 213));
					userDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					userDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					userDetail.setFixedHeight(28f);
					userDetails.addCell(userDetail);
					
					userDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getUserName(),
							strFointSizeTen));
					userDetail.setBackgroundColor(new Color(255, 255, 255));
					userDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					userDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					userDetails.addCell(userDetail);
					
					userDetail = new PdfPCell(new Phrase("Phone#",
							strFontSizeBoldEleven));
					userDetail.setBackgroundColor(new Color(213, 213, 213));
					userDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					userDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					userDetail.setFixedHeight(28f);
					userDetails.addCell(userDetail);
					
					userDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getContactNo(),
							strFointSizeTen));
					userDetail.setBackgroundColor(new Color(255, 255, 255));
					userDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					userDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					userDetails.addCell(userDetail);
					
					document.add(userDetails);
					document.add(new Paragraph("\n"));
					
					//Requested Change Section Here
					PdfPCell reqChangeDetail = new PdfPCell(new Phrase(
							"REQUESTED CHANGE", strFontSizeBoldEleven));
					reqChangeDetail.setBackgroundColor(new Color(184, 204, 228));
					reqChangeDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					reqChangeDetail.setHorizontalAlignment(Element.ALIGN_CENTER);
					reqChangeDetail.setColspan(4);
					reqChangeDetail.setFixedHeight(32f);
					reqChange.addCell(reqChangeDetail);
					
					reqChangeDetail = new PdfPCell(new Phrase("Request From",
							strFontSizeBoldEleven));
					reqChangeDetail.setBackgroundColor(new Color(213, 213, 213));
					reqChangeDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					reqChangeDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqChangeDetail.setFixedHeight(28f);
					reqChange.addCell(reqChangeDetail);
					reqChange.setSplitRows(true);
					
					reqChangeDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getReqFrom(),
							strFointSizeTen));
					reqChangeDetail.setBackgroundColor(new Color(255, 255, 255));
					reqChangeDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					reqChangeDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqChange.addCell(reqChangeDetail);
					
					reqChangeDetail = new PdfPCell(new Phrase("Request Type",
							strFontSizeBoldEleven));
					reqChangeDetail.setBackgroundColor(new Color(213, 213, 213));
					reqChangeDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					reqChangeDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqChangeDetail.setFixedHeight(28f);
					reqChange.addCell(reqChangeDetail);
					
					reqChangeDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getReqTypeDesc(),
							strFointSizeTen));
					reqChangeDetail.setBackgroundColor(new Color(255, 255, 255));
					reqChangeDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					reqChangeDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqChange.addCell(reqChangeDetail);
					reqChange.setSplitRows(false);
					
					reqChangeDetail = new PdfPCell(new Phrase("General Description",
							strFontSizeBoldEleven));
					reqChangeDetail.setBackgroundColor(new Color(213, 213, 213));
					reqChangeDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					reqChangeDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqChangeDetail.setFixedHeight(28f);
					reqChange.addCell(reqChangeDetail);
					
					reqChangeDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getGenDesc(),
							strFointSizeTen));
					reqChangeDetail.setBackgroundColor(new Color(255, 255, 255));
					reqChangeDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					reqChangeDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqChangeDetail.setColspan(3);
					reqChange.addCell(reqChangeDetail);
					
					
					
					//Added for Attachments
					reqChangeDetail = new PdfPCell(new Phrase("Attachments",
							strFontSizeBoldEleven));
					reqChangeDetail.setBackgroundColor(new Color(213, 213, 213));
					reqChangeDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					reqChangeDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqChangeDetail.setFixedHeight(32f);
					reqChange.addCell(reqChangeDetail);
					LogUtil.logMessage("Enters into attachments in Searchrequest1058");
					
					ArrayList arlAttachList1058VO = objChangeRequest1058VO.getAttachmentsList();
					//ActionMapping objActionMapping;	
					if (arlAttachList1058VO.size() > 0)	{

						String strImgName1 = "";
						int seq = 0;
						String emdlsdb = ApplicationConstants.emdlsdb;
						
						Phrase phrase = new Phrase();
						for (int nAttCnt = 0; nAttCnt < arlAttachList1058VO.size(); nAttCnt++) {
							//Added for CR-135 PDF Attachments
							
							Chunk comma=new Chunk(" , ");	
							ChangeRequest1058AttachmentsVO objImageDetailsVO = (ChangeRequest1058AttachmentsVO) arlAttachList1058VO.get(nAttCnt);
							strImgName1=objImageDetailsVO.getImgName();
							LogUtil.logMessage("Image name 1"+strImgName1);
							seq = objImageDetailsVO.getImgSeqNo();
							LogUtil.logMessage("Image seq 1"+seq);
							LogUtil.logMessage("emdlsdb"+ emdlsdb);
							
							Font blue = new Font(Font.TIMES_ROMAN, 10f, Font.NORMAL, new Color(0, 0, 250));
							Chunk chunk=new Chunk(strImgName1,blue);	
						    chunk.setAnchor(strRequestUrl+emdlsdb+"/DownLoadImage.do?method=downloadImage&ImageSeqNo="+seq+"&download=Y&ImageName="+strImgName1);
							chunk.setUnderline(0.7f, -2f);
							
							phrase.add(chunk);
							
							
							if (nAttCnt + 1 != arlAttachList1058VO.size())
								phrase.add(comma);
							
							//Added for CR-135 PDF Attachments till here
								
						}
						reqChangeDetail = new PdfPCell(phrase);
						reqChangeDetail.setBackgroundColor(new Color(255, 255, 255));
						reqChangeDetail.setVerticalAlignment(Element.ALIGN_TOP);
						reqChangeDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						reqChangeDetail.setColspan(3);
						//reqChangeDetail.setFixedHeight(32f);
						reqChange.addCell(reqChangeDetail);
					}
					 
					else{
						reqChangeDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						reqChangeDetail.setBackgroundColor(new Color(255, 255, 255));
						reqChangeDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						reqChangeDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						reqChangeDetail.setColspan(3);
						reqChange.addCell(reqChangeDetail);
					}
					document.add(reqChange);
					
					
					
				
					//Request Specification Change(s)
                    LogUtil.logMessage("objChangeRequest1058VO.getNonLsdbFlag():"+objChangeRequest1058VO.getNonLsdbFlag());
					if(objChangeRequest1058VO.getNonLsdbFlag().equalsIgnoreCase("N")){
						
						ArrayList arlClauseHistory=new ArrayList();
						ArrayList arlAllClauseHistory = new ArrayList();
						ClauseVO objCommonClauseVO = new ClauseVO();
						ClauseVO objFromClauseVO = new ClauseVO();
						ClauseVO objToClauseVO = new ClauseVO();						
						
						arlLsdbClauseDetails = objChangeRequest1058BI.fetchLsdbClauseDetails(objChangeRequest1058VO);
						LogUtil.logMessage("arlLsdbClauseDetails.size()" + arlLsdbClauseDetails.size());

						
						if (arlLsdbClauseDetails != null & arlLsdbClauseDetails.size() > 0) {
	
							for (lsdbcnt = 0; lsdbcnt < arlLsdbClauseDetails.size(); lsdbcnt++) {
								
								LogUtil.logMessage("arlLsdbClauseDetails current pointer : " +lsdbcnt);
								
								arlAllClauseHistory = (ArrayList)arlLsdbClauseDetails.get(lsdbcnt);
								
								objCommonClauseVO = (ClauseVO) arlAllClauseHistory.get(0);
								objFromClauseVO = (ClauseVO) arlAllClauseHistory.get(1);
								objToClauseVO = (ClauseVO) arlAllClauseHistory.get(2);
								
									PdfPTable subsec = new PdfPTable(4);
									subsec.setWidthPercentage(90);
									subsec.setWidths(width);
								    
									if(lsdbcnt == 0){
										PdfPCell headerRow = new PdfPCell(new Phrase(
												"REQUESTED SPECIFICATION CHANGE(s)", strFontSizeBoldEleven));
										headerRow.setBackgroundColor(new Color(184, 204, 228));
										headerRow.setVerticalAlignment(Element.ALIGN_MIDDLE);
										headerRow.setHorizontalAlignment(Element.ALIGN_CENTER);
										headerRow.setColspan(4);
										headerRow.setFixedHeight(32f);
										subsec.addCell(headerRow);
								    }
								    
								    PdfPCell c = new PdfPCell(new Phrase("Description",
											strFontSizeBoldTen));
									c.setBackgroundColor(new Color(213, 213, 213));
									c.setVerticalAlignment(Element.ALIGN_TOP);
									c.setHorizontalAlignment(Element.ALIGN_CENTER);
									c.setFixedHeight(28f);
									subsec.addCell(c);
									
									c = new PdfPCell(new Phrase("Specification Section",
											strFontSizeBoldTen));
									c.setBackgroundColor(new Color(213, 213, 213));
									c.setVerticalAlignment(Element.ALIGN_TOP);
									c.setHorizontalAlignment(Element.ALIGN_CENTER);
									c.setFixedHeight(28f);
									subsec.addCell(c);
									
									c = new PdfPCell(new Phrase("Clause Description", strFontSizeBoldTen));
									c.setBackgroundColor(new Color(213, 213, 213));
									c.setVerticalAlignment(Element.ALIGN_TOP);
									c.setHorizontalAlignment(Element.ALIGN_CENTER);
									c.setFixedHeight(28f);
									subsec.addCell(c);
									
									c = new PdfPCell(new Phrase("Engineering Data",strFontSizeBoldTen));
									c.setBackgroundColor(new Color(213, 213, 213));
									c.setVerticalAlignment(Element.ALIGN_TOP);
									c.setHorizontalAlignment(Element.ALIGN_CENTER);
									c.setFixedHeight(28f);
									subsec.addCell(c);
									
									c = new PdfPCell(new Phrase("Change From", strFontSizeBoldTen));
									c.setBackgroundColor(new Color(213, 213, 213));
									c.setVerticalAlignment(Element.ALIGN_TOP);
									c.setHorizontalAlignment(Element.ALIGN_CENTER);
									c.setFixedHeight(28f);
									subsec.addCell(c);
									
									strPreReason = objCommonClauseVO.getStrPreReason();	
										
										if(objFromClauseVO !=null){
						
											LogUtil.logMessage("objFromClauseVO is not null");
											
											strPastClauseNo = objFromClauseVO.getStrPastClauseNo();
											strPastClauseDesc = objFromClauseVO.getStrPastClauseDesc(); 
											String strOrderDelFlag = objFromClauseVO.getClauseDelFlag();
											String strModDelFlag = objFromClauseVO.getStrModDelFlag();
											
											LogUtil.logMessage("strPastClauseNo" + strPastClauseNo);
																
											if (strPastClauseNo != null) {
												c = new PdfPCell(new Phrase(strPastClauseNo,
														strFointSizeNoBoldTen1));
												c.setVerticalAlignment(Element.ALIGN_TOP);
												subsec.addCell(c);
											} else {
												c = new PdfPCell(new Phrase("", strFointSizeNoBoldTen1));
												c.setVerticalAlignment(Element.ALIGN_TOP);
												subsec.addCell(c);
											}

											//Modified for CR_111 to add borders for Table Data
											ArrayList arlTabData = new ArrayList();
											Integer intTabColCnt = new Integer(0);							
											int tabColCount = 1;
											if (objFromClauseVO.getTableArrayData1() != null)	{
												arlTabData = getTableDataColumnsCount(objFromClauseVO.getTableArrayData1());
					
												if (arlTabData != null && arlTabData.size() > 0) {
													intTabColCnt = (Integer) arlTabData.get(0);
												}
					
												if (intTabColCnt.intValue() == 0) {
													tabColCount = 1;
												} else {
													tabColCount = intTabColCnt.intValue();
												}							
											}
											
											PdfPTable TBData = new PdfPTable(tabColCount);
											TBData.setTotalWidth(95);
											
											if (strPastClauseDesc != null) {
												
												if (strPastClauseDesc.startsWith("<p>"))
													{			
														
														Paragraph strPastHtmlClauseDesc = new Paragraph();
														strPastHtmlClauseDesc.setFont(customFont);//Edited for CR-130
														ArrayList p = HTMLWorker.parseToList(new StringReader(strPastClauseDesc), null);//Edited for CR-130
													    
														//Commented for CR-130
														/*String strFileName = ApplicationUtil.strConvertToHTMLFormat(strPastClauseDesc);
														StyleSheet styles = new StyleSheet();
													    styles.loadTagStyle("p","size","10px");
													    styles.loadTagStyle("p","face","Times");*/
													    
													    for (int k1 = 0; k1 < p.size(); ++k1) {
													    	strPastHtmlClauseDesc.add((Element) p.get(k1));
													    }
													    
													    PdfPCell c1 = new PdfPCell(strPastHtmlClauseDesc);
														c.setVerticalAlignment(Element.ALIGN_TOP);
														c1.setColspan(tabColCount);
														TBData.addCell(c1);
													}
												else
													{
														LogUtil.logMessage("custom Font");
														PdfPCell c1 = new PdfPCell(new Phrase(strPastClauseDesc,
															customFont));//Edited for CR-130
														c.setVerticalAlignment(Element.ALIGN_TOP);
														c1.setColspan(tabColCount);
														TBData.addCell(c1);
													}
											}
												
											ArrayList arlTableRows = (ArrayList) objFromClauseVO.getTableArrayData1();	
											if (arlTableRows != null && arlTableRows.size() > 0) {
												
											for (int k= 0; k < arlTableRows.size(); k++) {
												if (intHeader == 0) {
													fontHeader = new Font(Font.TIMES_ROMAN, 10, Font.BOLD, Color.BLACK);
												} else {
													fontHeader = strFointSizeNoBoldTen1;
												}
												
												ArrayList arlTableColumns = (ArrayList) arlTableRows.get(k);

												if (tabColCount == 1
														|| tabColCount == 2
														|| tabColCount == 3
														|| tabColCount == 4
														|| tabColCount == 5) {
													PdfPCell c1 = new PdfPCell(new Phrase((String) arlTableColumns.get(0), fontHeader));
													c1.setBorderColor(new Color(0, 0, 0));
													TBData.addCell(c1);
												}

												if (tabColCount == 2
														|| tabColCount == 3
														|| tabColCount == 4
														|| tabColCount == 5) {
													PdfPCell c1 = new PdfPCell(new Phrase((String) arlTableColumns.get(1), fontHeader));
													c1.setBorderColor(new Color(0, 0, 0));
													TBData.addCell(c1);
												}

												if (tabColCount == 3
														|| tabColCount == 4
														|| tabColCount == 5) {
													PdfPCell c1 = new PdfPCell(new Phrase((String) arlTableColumns.get(2), fontHeader));
													c1.setBorderColor(new Color(0, 0, 0));
													TBData.addCell(c1);
												}

												if (tabColCount == 4
														|| tabColCount == 5) {
													PdfPCell c1 = new PdfPCell(new Phrase((String) arlTableColumns.get(3), fontHeader));
													c1.setBorderColor(new Color(0, 0, 0));
													TBData.addCell(c1);
												}

												if (tabColCount == 5) {
													PdfPCell c1 = new PdfPCell(new Phrase((String) arlTableColumns.get(4), fontHeader));
													c1.setBorderColor(new Color(0, 0, 0));
													TBData.addCell(c1);
												}
												
												intHeader++;
												}
											}
											
											if (!"Y".equals(strOrderDelFlag)
													&& !"D".equals(strModDelFlag)) {
												c = new PdfPCell(TBData);
												c.setVerticalAlignment(Element.ALIGN_TOP);
												subsec.addCell(c);
											} else {
												c = new PdfPCell(new Phrase("RESERVED",
														strFointSizeNoBoldTen1));
												c.setVerticalAlignment(Element.ALIGN_TOP);
												subsec.addCell(c);
											}
											
											
												String strEngData = "";					
												strEngData = objFromClauseVO.getStrEngData();						
												
											
													c = new PdfPCell(new Phrase(strEngData,
															strFointSizeNoBoldTen1));
													c.setVerticalAlignment(Element.ALIGN_TOP);
													subsec.addCell(c);
																		
										}
										
										c = new PdfPCell(new Phrase("Change To", strFontSizeBoldTen));
										c.setBackgroundColor(new Color(213, 213, 213));
										c.setVerticalAlignment(Element.ALIGN_TOP);
										c.setHorizontalAlignment(Element.ALIGN_CENTER);
										c.setFixedHeight(28f);
										subsec.addCell(c);
										
										
										if(objToClauseVO != null){
																
											LogUtil.logMessage("objToClauseVO is not null");
											
											strPreClauseNo = objToClauseVO.getStrPreClauseNo();
											strPreClauseDesc = objToClauseVO.getStrPreClauseDesc();
											String strOrderDelFlag = objToClauseVO.getClauseDelFlag();
											String strModDelFlag = objToClauseVO.getStrModDelFlag();
											
											if (strPreClauseNo != null) {
												c = new PdfPCell(new Phrase(strPreClauseNo,
														strFointSizeNoBoldTen1));
												c.setVerticalAlignment(Element.ALIGN_TOP);
												subsec.addCell(c);
											} else {
												c = new PdfPCell(new Phrase("", strFointSizeNoBoldTen1));
												c.setVerticalAlignment(Element.ALIGN_TOP);
												subsec.addCell(c);
											}

											//Modified for CR_111 to add borders for Table Data
											ArrayList arlTabData = new ArrayList();
											Integer intTabColCnt = new Integer(0);							
											int tabColCount = 1;
											
											if (objToClauseVO.getTableArrayData1() != null)	{
												arlTabData = getTableDataColumnsCount(objToClauseVO.getTableArrayData1());
					
												if (arlTabData != null && arlTabData.size() > 0) {
													intTabColCnt = (Integer) arlTabData.get(0);
												}
					
												if (intTabColCnt.intValue() == 0) {
													tabColCount = 1;
												} else {
													tabColCount = intTabColCnt.intValue();
												}							
											}
											
											PdfPTable TBData = new PdfPTable(tabColCount);
											TBData.setTotalWidth(95);
											
											if (strPreClauseDesc != null) {
												if (strPreClauseDesc.startsWith("<p>"))
												{
													Paragraph strPreHtmlClauseDesc = new Paragraph();
													strPreHtmlClauseDesc.setFont(customFont);//Edited for CR-130
													ArrayList p = HTMLWorker.parseToList(new StringReader(strPreClauseDesc), null);//Edited for CR-130
												    
													//Comented for CR-130
													/*String strFileName = ApplicationUtil.strConvertToHTMLFormat(strPreClauseDesc);
													StyleSheet styles = new StyleSheet();
												    styles.loadTagStyle("p","size","10px");
												    styles.loadTagStyle("p","face","Times");*/
													
												    for (int k1 = 0; k1 < p.size(); ++k1) {
												    	strPreHtmlClauseDesc.add((Element) p.get(k1));
												    }
												    
												    PdfPCell c1 = new PdfPCell(strPreHtmlClauseDesc);				    
													c1.setVerticalAlignment(Element.ALIGN_TOP);
													c1.setColspan(tabColCount);
													TBData.addCell(c1);
												}
												else
												{
													LogUtil.logMessage("custom Font");
													PdfPCell c1 = new PdfPCell(new Phrase(strPreClauseDesc,
															customFont));//Edited for cR-130 
													c1.setVerticalAlignment(Element.ALIGN_TOP);
													c1.setColspan(tabColCount);
													TBData.addCell(c1);
												}
											}
											
											ArrayList arlTableRowstToCla = (ArrayList) objToClauseVO.getTableArrayData1();	
											
											if (arlTableRowstToCla != null && arlTableRowstToCla.size() > 0) {
												
												for (int k= 0; k < arlTableRowstToCla.size(); k++) {
													
													if (intHeader == 0) {
														fontHeader = new Font(Font.TIMES_ROMAN, 10, Font.BOLD, Color.BLACK);
													} else {
														fontHeader = strFointSizeNoBoldTen1;
													}
													
													ArrayList arlTableColumns = (ArrayList) arlTableRowstToCla.get(k);
													
													if (tabColCount == 1
															|| tabColCount == 2
															|| tabColCount == 3
															|| tabColCount == 4
															|| tabColCount == 5) {
														PdfPCell c1 = new PdfPCell(new Phrase((String) arlTableColumns.get(0), fontHeader));
														c1.setBorderColor(new Color(0, 0, 0));
														TBData.addCell(c1);
													}

													if (tabColCount == 2
															|| tabColCount == 3
															|| tabColCount == 4
															|| tabColCount == 5) {
														PdfPCell c1 = new PdfPCell(new Phrase((String) arlTableColumns.get(1), fontHeader));
														c1.setBorderColor(new Color(0, 0, 0));
														TBData.addCell(c1);
													}

													if (tabColCount == 3
															|| tabColCount == 4
															|| tabColCount == 5) {
														PdfPCell c1 = new PdfPCell(new Phrase((String) arlTableColumns.get(2), fontHeader));
														c1.setBorderColor(new Color(0, 0, 0));
														TBData.addCell(c1);
													}

													if (tabColCount == 4
															|| tabColCount == 5) {
														PdfPCell c1 = new PdfPCell(new Phrase((String) arlTableColumns.get(3), fontHeader));
														c1.setBorderColor(new Color(0, 0, 0));
														TBData.addCell(c1);
													}

													if (tabColCount == 5) {
														PdfPCell c1 = new PdfPCell(new Phrase((String) arlTableColumns.get(4), fontHeader));
														c1.setBorderColor(new Color(0, 0, 0));
														TBData.addCell(c1);
													}
													
													intHeader++;
												}
											}
											
											if (!"Y".equals(strOrderDelFlag)
													&& !"D".equals(strModDelFlag)) {
												c = new PdfPCell(TBData);
												c.setVerticalAlignment(Element.ALIGN_TOP);
												subsec.addCell(c);
											} else {
												c = new PdfPCell(new Phrase("RESERVED",
														strFointSizeNoBoldTen1));
												c.setVerticalAlignment(Element.ALIGN_TOP);
												subsec.addCell(c);
											}
					
											
												String strEngData = "";		
																
												strEngData = objToClauseVO.getStrEngData();
													
											
													c = new PdfPCell(new Phrase(strEngData,
															strSSFointSizeNine));
													c.setVerticalAlignment(Element.ALIGN_TOP);
													subsec.addCell(c);
											
										}
										
										c = new PdfPCell(new Phrase("Reason", strFontSizeBoldTen));
										c.setBackgroundColor(new Color(213, 213, 213));
										c.setVerticalAlignment(Element.ALIGN_TOP);
										c.setHorizontalAlignment(Element.ALIGN_CENTER);
										c.setFixedHeight(28f);
										subsec.addCell(c);
										
										c = new PdfPCell(new Phrase(strPreReason, strSSFointSizeNine));
										c.setVerticalAlignment(Element.ALIGN_TOP);
										c.setColspan(3);
										subsec.addCell(c);
										//document.add(new Paragraph("\n"));
										if (writer.fitsPage(subsec)) {
											document.add(new Paragraph("\n"));
											document.add(subsec);
											//document.add(new Paragraph("\n"));
										} else {											
											document.newPage();
											document.add(new Paragraph("\n"));
											document.add(subsec);
											//document.add(new Paragraph("\n"));
										}
							}		
						}
						/*Added for CR-130 Starts here*/
						
						objChangeRequest1058VO.setSubSecChngReqSeq(0);
						arlSubSectionDetails = objChangeRequest1058BI.fetch1058SubSectionList(objChangeRequest1058VO);
						LogUtil.logMessage("arlSubSectionDetails.size()" + arlSubSectionDetails.size());
						
						if (arlSubSectionDetails != null & arlSubSectionDetails.size() > 0) {
							
							commonListIterator = arlSubSectionDetails.iterator();
							while (commonListIterator.hasNext()){
							    	
								objChangeRequest1058VO = new ChangeRequest1058VO();
								objChangeRequest1058VO = (ChangeRequest1058VO) commonListIterator.next();
								
									PdfPTable subsec = new PdfPTable(4);
									subsec.setWidthPercentage(90);
									subsec.setWidths(width);
								    
									if(nSubSecCnt == 0 && arlLsdbClauseDetails.size() == 0){
										PdfPCell headerRow = new PdfPCell(new Phrase(
												"REQUESTED SPECIFICATION CHANGE(s)", strFontSizeBoldEleven));
										headerRow.setBackgroundColor(new Color(184, 204, 228));
										headerRow.setVerticalAlignment(Element.ALIGN_MIDDLE);
										headerRow.setHorizontalAlignment(Element.ALIGN_CENTER);
										headerRow.setColspan(4);
										headerRow.setFixedHeight(32f);
										subsec.addCell(headerRow);
								    }
									
									PdfPCell headerRow = new PdfPCell(new Phrase(
											objChangeRequest1058VO.getSubSecCode()+"."+objChangeRequest1058VO.getSubSecName(), strFontSizeBoldEleven));
									headerRow.setBackgroundColor(new Color(184, 204, 228));
									headerRow.setVerticalAlignment(Element.ALIGN_MIDDLE);
									headerRow.setHorizontalAlignment(Element.ALIGN_CENTER);
									headerRow.setColspan(4);
									headerRow.setFixedHeight(32f);
									subsec.addCell(headerRow);
								    
								    PdfPCell c = new PdfPCell(new Phrase("Clause Number",
											strFontSizeBoldTen));
									c.setBackgroundColor(new Color(213, 213, 213));
									c.setVerticalAlignment(Element.ALIGN_TOP);
									c.setHorizontalAlignment(Element.ALIGN_CENTER);
									c.setFixedHeight(28f);
									subsec.addCell(c);
									
								
									c = new PdfPCell(new Phrase("Clause Description", strFontSizeBoldTen));
									c.setBackgroundColor(new Color(213, 213, 213));
									c.setVerticalAlignment(Element.ALIGN_TOP);
									c.setHorizontalAlignment(Element.ALIGN_CENTER);
									c.setFixedHeight(28f);
									c.setColspan(2);
									subsec.addCell(c);
									
									c = new PdfPCell(new Phrase("Engineering Data",strFontSizeBoldTen));
									c.setBackgroundColor(new Color(213, 213, 213));
									c.setVerticalAlignment(Element.ALIGN_TOP);
									c.setHorizontalAlignment(Element.ALIGN_CENTER);
									c.setFixedHeight(28f);
									subsec.addCell(c);
									
									arlSubSecClauseDetails = objChangeRequest1058VO.getClaDetailList();
									clauseListIterator = arlSubSecClauseDetails.iterator();
									
									while (clauseListIterator.hasNext()){
										objClauseDetailVO = new ChangeRequest1058VO();
										objClauseDetailVO = (ChangeRequest1058VO) clauseListIterator.next();
										
										if(objClauseDetailVO !=null){
											
											LogUtil.logMessage("objFromClauseVO is not null");
											
											strClauseNo = objClauseDetailVO.getClauseNumber();
											strClauseDesc = objClauseDetailVO.getChangeFromClaDesc(); 
											
											LogUtil.logMessage("strPastClauseNo" + strClauseNo);
																
											if (strClauseNo != null) {
												c = new PdfPCell(new Phrase(strClauseNo,
														strFointSizeNoBoldTen1));
												c.setVerticalAlignment(Element.ALIGN_TOP);
												subsec.addCell(c);
											} else {
												c = new PdfPCell(new Phrase("", strFointSizeNoBoldTen1));
												c.setVerticalAlignment(Element.ALIGN_TOP);
												subsec.addCell(c);
											}
											
											ArrayList arlTabData = new ArrayList();
											Integer intTabColCnt = new Integer(0);							
											int tabColCount = 1;
											if (objClauseDetailVO.getTableArrayData1() != null)	{
												arlTabData = getTableDataColumnsCount(objClauseDetailVO.getTableArrayData1());
					
												if (arlTabData != null && arlTabData.size() > 0) {
													intTabColCnt = (Integer) arlTabData.get(0);
												}
					
												if (intTabColCnt.intValue() == 0) {
													tabColCount = 1;
												} else {
													tabColCount = intTabColCnt.intValue();
												}							
											}
											
											PdfPTable TBData = new PdfPTable(tabColCount);
											TBData.setTotalWidth(95);

											
											if (strClauseDesc != null) {
												
												if (strClauseDesc.startsWith("<p>"))
													{						    
														Paragraph strPastHtmlClauseDesc = new Paragraph();
														strPastHtmlClauseDesc.setFont(customFont);//Edited for CR-130
														ArrayList p = HTMLWorker.parseToList(new StringReader(strClauseDesc), null);//Edited for CR-130
													    
														//Commented for CR-130
														/*String strFileName = ApplicationUtil.strConvertToHTMLFormat(strClauseDesc);
														StyleSheet styles = new StyleSheet();
													    styles.loadTagStyle("p","size","10px");
													    styles.loadTagStyle("p","face","Times");*/
														
													    for (int k1 = 0; k1 < p.size(); ++k1) {
													    	strPastHtmlClauseDesc.add((Element) p.get(k1));
													    }
													    
													    PdfPCell c1 = new PdfPCell(strPastHtmlClauseDesc);
														c.setVerticalAlignment(Element.ALIGN_TOP);
														c1.setColspan(tabColCount);
														TBData.addCell(c1);
													}
												else
													{
														LogUtil.logMessage("custom Font");
														PdfPCell c1 = new PdfPCell(new Phrase(strPastClauseDesc,
															customFont));//Edited for CR-130
														c.setVerticalAlignment(Element.ALIGN_TOP);
														c1.setColspan(tabColCount);
														TBData.addCell(c1);
													}
											}
											
												ArrayList arlTableRows = (ArrayList) objClauseDetailVO.getTableArrayData1();	
												if (arlTableRows != null && arlTableRows.size() > 0) {
													
												for (int k= 0; k < arlTableRows.size(); k++) {
													if (intHeader == 0) {
														fontHeader = new Font(Font.TIMES_ROMAN, 10, Font.BOLD, Color.BLACK);
													} else {
														fontHeader = strFointSizeNoBoldTen1;
													}
													
													ArrayList arlTableColumns = (ArrayList) arlTableRows.get(k);

													if (tabColCount == 1
															|| tabColCount == 2
															|| tabColCount == 3
															|| tabColCount == 4
															|| tabColCount == 5) {
														PdfPCell c1 = new PdfPCell(new Phrase((String) arlTableColumns.get(0), fontHeader));
														c1.setBorderColor(new Color(0, 0, 0));
														TBData.addCell(c1);
													}

													if (tabColCount == 2
															|| tabColCount == 3
															|| tabColCount == 4
															|| tabColCount == 5) {
														PdfPCell c1 = new PdfPCell(new Phrase((String) arlTableColumns.get(1), fontHeader));
														c1.setBorderColor(new Color(0, 0, 0));
														TBData.addCell(c1);
													}

													if (tabColCount == 3
															|| tabColCount == 4
															|| tabColCount == 5) {
														PdfPCell c1 = new PdfPCell(new Phrase((String) arlTableColumns.get(2), fontHeader));
														c1.setBorderColor(new Color(0, 0, 0));
														TBData.addCell(c1);
													}

													if (tabColCount == 4
															|| tabColCount == 5) {
														PdfPCell c1 = new PdfPCell(new Phrase((String) arlTableColumns.get(3), fontHeader));
														c1.setBorderColor(new Color(0, 0, 0));
														TBData.addCell(c1);
													}

													if (tabColCount == 5) {
														PdfPCell c1 = new PdfPCell(new Phrase((String) arlTableColumns.get(4), fontHeader));
														c1.setBorderColor(new Color(0, 0, 0));
														TBData.addCell(c1);
													}
													
													intHeader++;
													}
												}
												
													c = new PdfPCell(TBData);
													c.setVerticalAlignment(Element.ALIGN_TOP);
													c.setColspan(2);
													subsec.addCell(c);
													
										
												String strEnggData = "";					
												
												
												if(objClauseDetailVO.getChangeFromEdlNo() != null){
													strEnggData = strEnggData + objClauseDetailVO.getChangeFromEdlNo();
												}
												
												if(objClauseDetailVO.getChangeFromRefEdlNo() != null){
													strEnggData = strEnggData + objClauseDetailVO.getChangeFromRefEdlNo();
												}
												
												if(objClauseDetailVO.getChangeFromPartOfNo() != null){
													strEnggData = strEnggData + objClauseDetailVO.getChangeFromPartOfNo();
												}
												
												if(objClauseDetailVO.getChangeFromDwoNo() != null){
													strEnggData = strEnggData + "Dwo No " + objClauseDetailVO.getChangeFromDwoNo()+ "\n";
												}
												if(objClauseDetailVO.getChangeFromPartNo() != null){
													strEnggData = strEnggData + "Part No " + objClauseDetailVO.getChangeFromPartNo()+ "\n";
												}
												if(objClauseDetailVO.getChangeFromPriceBookNo() != null){
													strEnggData = strEnggData + "Price Book No " + objClauseDetailVO.getChangeFromPriceBookNo() + "\n";
												}
												if(objClauseDetailVO.getChangeFromEquip() == null){
													objClauseDetailVO.setChangeFromEquip("");
												}else{
													strEnggData = strEnggData + objClauseDetailVO.getChangeFromEquip() + "\n";
												}
												if(objClauseDetailVO.getChangeFromEngiComments() == null){
													objClauseDetailVO.setChangeFromEngiComments("");
												}else{
													strEnggData = strEnggData + objClauseDetailVO.getChangeFromEngiComments();
												}
												
											
													c = new PdfPCell(new Phrase(strEnggData,
															strFointSizeNoBoldTen1));
													c.setVerticalAlignment(Element.ALIGN_TOP);
													subsec.addCell(c);
																		
										}
										
									}
										
										strSubSecReason = objChangeRequest1058VO.getReasonSubSec();
										
										c = new PdfPCell(new Phrase("Reason", strFontSizeBoldTen));
										c.setBackgroundColor(new Color(213, 213, 213));
										c.setVerticalAlignment(Element.ALIGN_TOP);
										c.setHorizontalAlignment(Element.ALIGN_CENTER);
										c.setFixedHeight(28f);
										subsec.addCell(c);
										
										c = new PdfPCell(new Phrase(strSubSecReason, strSSFointSizeNine));
										c.setVerticalAlignment(Element.ALIGN_TOP);
										c.setColspan(3);
										subsec.addCell(c);
										
										//document.add(new Paragraph("\n"));
										if (writer.fitsPage(subsec)) {
											document.add(new Paragraph("\n"));
											document.add(subsec);
											//document.add(new Paragraph("\n"));
										} else {											
											document.newPage();
											document.add(new Paragraph("\n"));
											document.add(subsec);
											//document.add(new Paragraph("\n"));
										}
							}		
						}
						/*Added for CR-130 Ends here*/
						
						
					}else{
						
						arlNLsdbClauseDetails = objChangeRequest1058BI.fetchNLsdbClauseDetails(objChangeRequest1058VO);
						LogUtil.logMessage("arlNLsdbClauseDetails.size()" + arlNLsdbClauseDetails.size());
						ChangeRequest1058VO objNLsdbChangeRequest1058VO = new ChangeRequest1058VO();
						
						if (arlNLsdbClauseDetails != null & arlNLsdbClauseDetails.size() > 0) {

							for (nLsdbcnt = 0; nLsdbcnt < arlNLsdbClauseDetails.size(); nLsdbcnt++) {
								
								LogUtil.logMessage("arlNLsdbClauseDetails current pointer : " +nLsdbcnt);
								
								objNLsdbChangeRequest1058VO = (ChangeRequest1058VO) arlNLsdbClauseDetails.get(nLsdbcnt);
								
									PdfPTable reqSpecChange = new PdfPTable(4);
									reqSpecChange.setWidthPercentage(90);
									reqSpecChange.setWidths(width);
								
									if(nLsdbcnt == 0){
									PdfPCell headerRow = new PdfPCell(new Phrase(
											"REQUESTED SPECIFICATION CHANGE(s)", strFontSizeBoldEleven));
									headerRow.setBackgroundColor(new Color(184, 204, 228));
									headerRow.setVerticalAlignment(Element.ALIGN_MIDDLE);
									headerRow.setHorizontalAlignment(Element.ALIGN_CENTER);
									headerRow.setColspan(4);
									headerRow.setFixedHeight(32f);
					             	reqSpecChange.addCell(headerRow);	
									} 
									PdfPCell reqSpecDetail = new PdfPCell(new Phrase("Description",
											strFontSizeBoldTen));
									reqSpecDetail.setBackgroundColor(new Color(213, 213, 213));
									reqSpecDetail.setVerticalAlignment(Element.ALIGN_TOP);
									reqSpecDetail.setHorizontalAlignment(Element.ALIGN_CENTER);
									reqSpecDetail.setFixedHeight(28f);
									reqSpecChange.addCell(reqSpecDetail);
									
									reqSpecDetail = new PdfPCell(new Phrase("Specification Section",
											strFontSizeBoldTen));
									reqSpecDetail.setBackgroundColor(new Color(213, 213, 213));
									reqSpecDetail.setVerticalAlignment(Element.ALIGN_TOP);
									reqSpecDetail.setHorizontalAlignment(Element.ALIGN_CENTER);
									reqSpecDetail.setFixedHeight(28f);
									reqSpecChange.addCell(reqSpecDetail);
									
									reqSpecDetail = new PdfPCell(new Phrase("Clause Description",strFontSizeBoldTen));
									reqSpecDetail.setBackgroundColor(new Color(213, 213, 213));
									reqSpecDetail.setVerticalAlignment(Element.ALIGN_TOP);
									reqSpecDetail.setHorizontalAlignment(Element.ALIGN_CENTER);
									reqSpecDetail.setFixedHeight(28f);
									reqSpecChange.addCell(reqSpecDetail);
									
									reqSpecDetail = new PdfPCell(new Phrase("Engineering Data",strFontSizeBoldTen));
									reqSpecDetail.setBackgroundColor(new Color(213, 213, 213));
									reqSpecDetail.setVerticalAlignment(Element.ALIGN_TOP);
									reqSpecDetail.setHorizontalAlignment(Element.ALIGN_CENTER);
									reqSpecDetail.setFixedHeight(28f);
									reqSpecChange.addCell(reqSpecDetail);
									
									reqSpecDetail = new PdfPCell(new Phrase("Change From", strFontSizeBoldTen));
									reqSpecDetail.setBackgroundColor(new Color(213, 213, 213));
									reqSpecDetail.setVerticalAlignment(Element.ALIGN_TOP);
									reqSpecDetail.setHorizontalAlignment(Element.ALIGN_CENTER);
									reqSpecDetail.setFixedHeight(28f);
									reqSpecChange.addCell(reqSpecDetail);
									
									strChngFromSpecSec = objNLsdbChangeRequest1058VO.getChngFromSpecSec();
									if(strChngFromSpecSec != null){
										reqSpecDetail = new PdfPCell(new Phrase(strChngFromSpecSec,
																	strFointSizeTen));
										reqSpecDetail.setBackgroundColor(new Color(255, 255, 255));
										reqSpecDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
										reqSpecDetail.setHorizontalAlignment(Element.ALIGN_LEFT);									
										reqSpecChange.addCell(reqSpecDetail);
									}else{
										reqSpecDetail = new PdfPCell(new Phrase("",
													strFointSizeTen));
										reqSpecDetail.setBackgroundColor(new Color(255, 255, 255));
										reqSpecDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
										reqSpecDetail.setHorizontalAlignment(Element.ALIGN_LEFT);									
										reqSpecChange.addCell(reqSpecDetail);
									}
									
									strChngFromClaDesc = objNLsdbChangeRequest1058VO.getChngFromClaDesc();
									//Added for CR-130 Starts
									LogUtil.logMessage("strChngFromClaDesc "+strChngFromClaDesc);
									
									if (strChngFromClaDesc != null) {
										if (strChngFromClaDesc.startsWith("<p>"))
										{
											Paragraph strPastHtmlClauseDesc = new Paragraph();
											strPastHtmlClauseDesc.setFont(customFont);
											
											ArrayList p = HTMLWorker.parseToList(new StringReader(strChngFromClaDesc), null);
										    
										    for (int k1 = 0; k1 < p.size(); ++k1) {
										    	strPastHtmlClauseDesc.add((Element) p.get(k1));
										    }
										    
										    PdfPCell c1 = new PdfPCell(strPastHtmlClauseDesc);				    
										    c1.setBackgroundColor(new Color(255, 255, 255));
										    c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
										    c1.setHorizontalAlignment(Element.ALIGN_LEFT);
											reqSpecChange.addCell(c1);
										}
										else
										{
											PdfPCell c1 = new PdfPCell(new Phrase(strChngFromClaDesc,
													customFont));
											c1.setBackgroundColor(new Color(255, 255, 255));
										    c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
										    c1.setHorizontalAlignment(Element.ALIGN_LEFT);
											reqSpecChange.addCell(c1);
										}
									}else{
											PdfPCell c1 = new PdfPCell(new Phrase("",
													customFont));
											c1.setBackgroundColor(new Color(255, 255, 255));
										    c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
										    c1.setHorizontalAlignment(Element.ALIGN_LEFT);
											reqSpecChange.addCell(c1);
									}//Added for CR-130 ends
									/*if(strChngFromClaDesc != null){
										reqSpecDetail = new PdfPCell(new Phrase(strChngFromClaDesc,
																		customFont));
										reqSpecDetail.setBackgroundColor(new Color(255, 255, 255));
										reqSpecDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
										reqSpecDetail.setHorizontalAlignment(Element.ALIGN_LEFT);									
										reqSpecChange.addCell(reqSpecDetail);
									}else{
										reqSpecDetail = new PdfPCell(new Phrase("",
												customFont));
										reqSpecDetail.setBackgroundColor(new Color(255, 255, 255));
										reqSpecDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
										reqSpecDetail.setHorizontalAlignment(Element.ALIGN_LEFT);									
										reqSpecChange.addCell(reqSpecDetail);
									}*/
									
									strChngFromEnggData = objNLsdbChangeRequest1058VO.getChngFromEnggData();
									if(strChngFromEnggData != null){
										reqSpecDetail = new PdfPCell(new Phrase(strChngFromEnggData,
												strFointSizeTen));
										reqSpecDetail.setBackgroundColor(new Color(255, 255, 255));
										reqSpecDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
										reqSpecDetail.setHorizontalAlignment(Element.ALIGN_LEFT);									
										reqSpecChange.addCell(reqSpecDetail);
									}else{
										reqSpecDetail = new PdfPCell(new Phrase("",
												strFointSizeTen));
										reqSpecDetail.setBackgroundColor(new Color(255, 255, 255));
										reqSpecDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
										reqSpecDetail.setHorizontalAlignment(Element.ALIGN_LEFT);									
										reqSpecChange.addCell(reqSpecDetail);
									}
									
									reqSpecDetail = new PdfPCell(new Phrase("Change To", strFontSizeBoldTen));
									reqSpecDetail.setBackgroundColor(new Color(213, 213, 213));
									reqSpecDetail.setVerticalAlignment(Element.ALIGN_TOP);
									reqSpecDetail.setHorizontalAlignment(Element.ALIGN_CENTER);
									reqSpecDetail.setFixedHeight(28f);
									reqSpecChange.addCell(reqSpecDetail);
									
									strChngToSpecSec = objNLsdbChangeRequest1058VO.getChngToSpecSec();
									if(strChngToSpecSec != null){
										reqSpecDetail = new PdfPCell(new Phrase(strChngToSpecSec,
												strFointSizeTen));
										reqSpecDetail.setBackgroundColor(new Color(255, 255, 255));
										reqSpecDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
										reqSpecDetail.setHorizontalAlignment(Element.ALIGN_LEFT);									
										reqSpecChange.addCell(reqSpecDetail);
									}else{
										reqSpecDetail = new PdfPCell(new Phrase("",
												strFointSizeTen));
										reqSpecDetail.setBackgroundColor(new Color(255, 255, 255));
										reqSpecDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
										reqSpecDetail.setHorizontalAlignment(Element.ALIGN_LEFT);									
										reqSpecChange.addCell(reqSpecDetail);
									}
									
									strChngToClaDesc = objNLsdbChangeRequest1058VO.getChngToClaDesc();
									//Added for CR-130 Starts
									if (strChngToClaDesc != null) {
										if (strChngToClaDesc.startsWith("<p>"))
										{
											Paragraph strPreHtmlClauseDesc = new Paragraph();
											strPreHtmlClauseDesc.setFont(customFont);
											
											ArrayList p = HTMLWorker.parseToList(new StringReader(strChngToClaDesc), null);
										    
										    for (int k1 = 0; k1 < p.size(); ++k1) {
										    	strPreHtmlClauseDesc.add((Element) p.get(k1));
										    }
										    
										    PdfPCell c1 = new PdfPCell(strPreHtmlClauseDesc);				    
										    c1.setBackgroundColor(new Color(255, 255, 255));
										    c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
										    c1.setHorizontalAlignment(Element.ALIGN_LEFT);
											reqSpecChange.addCell(c1);
										}
										else
										{
											PdfPCell c1 = new PdfPCell(new Phrase(strPreClauseDesc,
													customFont));
											c1.setBackgroundColor(new Color(255, 255, 255));
										    c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
										    c1.setHorizontalAlignment(Element.ALIGN_LEFT);
											reqSpecChange.addCell(c1);
										}
									}else{
										PdfPCell c1 = new PdfPCell(new Phrase("",
												customFont));
										c1.setBackgroundColor(new Color(255, 255, 255));
									    c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
									    c1.setHorizontalAlignment(Element.ALIGN_LEFT);
										reqSpecChange.addCell(c1);
									}//Added for CR-130 ends
				                    /*if(strChngToClaDesc != null){
				                    	LogUtil.logMessage("into nlsdb change to");
										reqSpecDetail = new PdfPCell(new Phrase(strChngToClaDesc,
																		customFont));
										reqSpecDetail.setBackgroundColor(new Color(255, 255, 255));
										reqSpecDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
										reqSpecDetail.setHorizontalAlignment(Element.ALIGN_LEFT);									
										reqSpecChange.addCell(reqSpecDetail);
				                    }else{
				                    	reqSpecDetail = new PdfPCell(new Phrase("",
												customFont));
										reqSpecDetail.setBackgroundColor(new Color(255, 255, 255));
										reqSpecDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
										reqSpecDetail.setHorizontalAlignment(Element.ALIGN_LEFT);									
										reqSpecChange.addCell(reqSpecDetail);
				                    }*/
				                    
									strChngToEnggData = objNLsdbChangeRequest1058VO.getChngToEnggData();
									if(strChngToEnggData != null){
										reqSpecDetail = new PdfPCell(new Phrase(strChngToEnggData,
												strFointSizeTen));
										reqSpecDetail.setBackgroundColor(new Color(255, 255, 255));
										reqSpecDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
										reqSpecDetail.setHorizontalAlignment(Element.ALIGN_LEFT);									
										reqSpecChange.addCell(reqSpecDetail);
									}else{
										reqSpecDetail = new PdfPCell(new Phrase("",
												strFointSizeTen));
										reqSpecDetail.setBackgroundColor(new Color(255, 255, 255));
										reqSpecDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
										reqSpecDetail.setHorizontalAlignment(Element.ALIGN_LEFT);									
										reqSpecChange.addCell(reqSpecDetail);
									}
													
									reqSpecDetail = new PdfPCell(new Phrase("Reason", strFontSizeBoldTen));
									reqSpecDetail.setBackgroundColor(new Color(213, 213, 213));
									reqSpecDetail.setVerticalAlignment(Element.ALIGN_TOP);
									reqSpecDetail.setHorizontalAlignment(Element.ALIGN_CENTER);
									reqSpecDetail.setFixedHeight(28f);
									reqSpecChange.addCell(reqSpecDetail);
										
									strnLsdbReason = objNLsdbChangeRequest1058VO.getNLsdbReason();
									
									if(strnLsdbReason != null){
										reqSpecDetail = new PdfPCell(new Phrase(strnLsdbReason,
												strFointSizeTen));
										reqSpecDetail.setBackgroundColor(new Color(255, 255, 255));
										reqSpecDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
										reqSpecDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
										reqSpecDetail.setColspan(3);
										reqSpecChange.addCell(reqSpecDetail);
										
									}else{
										reqSpecDetail = new PdfPCell(new Phrase("",
												strFointSizeTen));
										reqSpecDetail.setBackgroundColor(new Color(255, 255, 255));
										reqSpecDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
										reqSpecDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
										reqSpecDetail.setColspan(3);
										reqSpecChange.addCell(reqSpecDetail);
									}
									
									
									LogUtil.logMessage("strChngFromSpecSec:" +strChngFromSpecSec);  
									LogUtil.logMessage("strChngFromClaDesc:" +strChngFromClaDesc);
									LogUtil.logMessage("strChngFromEnggData:" +strChngFromEnggData);
									LogUtil.logMessage("strChngToSpecSec:" +strChngToSpecSec);
									LogUtil.logMessage("strChngToClaDesc:" +strChngToClaDesc);
									LogUtil.logMessage("strChngToEnggData:" +strChngToEnggData);
									LogUtil.logMessage("strnLsdbReason:" +strnLsdbReason);
									//document.add(new Paragraph("\n"));
									if (writer.fitsPage(reqSpecChange)) {
										document.add(new Paragraph("\n"));
										document.add(reqSpecChange);
										//document.add(new Paragraph("\n"));
									} else {
										document.newPage();
										document.add(new Paragraph("\n"));
										document.add(reqSpecChange);
										//document.add(new Paragraph("\n"));
									}
						
							}
							
							
							
						}
					
					}
					
					
					//Requested Effectivity Section
					LogUtil.logMessage("objChangeRequest1058VO.getUnitNumber():"+objChangeRequest1058VO.getUnitNumber());
					PdfPCell reqEffectDetail = new PdfPCell(new Phrase(
							"REQUESTED  EFFECTIVITY", strFontSizeBoldEleven));
					reqEffectDetail.setBackgroundColor(new Color(184, 204, 228));
					reqEffectDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					reqEffectDetail.setHorizontalAlignment(Element.ALIGN_CENTER);
					reqEffectDetail.setColspan(3);
					reqEffectDetail.setFixedHeight(32f);
					reqEffectivity.addCell(reqEffectDetail);
					
					reqEffectDetail = new PdfPCell(new Phrase("Unit Numbers",
							strFontSizeBoldEleven));
					reqEffectDetail.setBackgroundColor(new Color(213, 213, 213));
					reqEffectDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					reqEffectDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqEffectDetail.setFixedHeight(28f);
					reqEffectivity.addCell(reqEffectDetail);
					
					reqEffectDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getUnitNumber(),
							strFointSizeTen));
					reqEffectDetail.setBackgroundColor(new Color(255, 255, 255));
					reqEffectDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					reqEffectDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqEffectDetail.setColspan(2);
					reqEffectivity.addCell(reqEffectDetail);
					
					reqEffectDetail = new PdfPCell(new Phrase("Road Numbers",
							strFontSizeBoldEleven));
					reqEffectDetail.setBackgroundColor(new Color(213, 213, 213));
					reqEffectDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					reqEffectDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqEffectDetail.setFixedHeight(28f);
					reqEffectivity.addCell(reqEffectDetail);
					
					reqEffectDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getRoadNumber(),
							strFointSizeTen));
					reqEffectDetail.setBackgroundColor(new Color(255, 255, 255));
					reqEffectDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					reqEffectDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqEffectDetail.setColspan(2);
					reqEffectivity.addCell(reqEffectDetail);

					reqEffectDetail = new PdfPCell(new Phrase("MCR Number",
							strFontSizeBoldEleven));
					reqEffectDetail.setBackgroundColor(new Color(213, 213, 213));
					reqEffectDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					reqEffectDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqEffectDetail.setFixedHeight(28f);
					reqEffectivity.addCell(reqEffectDetail);
					
					reqEffectDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getMcrNumber(),
							strFointSizeTen));
					reqEffectDetail.setBackgroundColor(new Color(255, 255, 255));
					reqEffectDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					reqEffectDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqEffectivity.addCell(reqEffectDetail);
					
					strMcrReq = Integer.toString(objChangeRequest1058VO.getMcrReq()).trim();
					if(strMcrReq.equalsIgnoreCase("1")){
					strMcrReq = "Required";	
					}else if(strMcrReq.equalsIgnoreCase("2")){
					strMcrReq = "Not Required";	
					}else{
					strMcrReq = "";	
					}
					
					reqEffectDetail = new PdfPCell(new Phrase(strMcrReq,
							strFointSizeTen));
					reqEffectDetail.setBackgroundColor(new Color(255, 255, 255));
					reqEffectDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					reqEffectDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqEffectivity.addCell(reqEffectDetail);
					
					//document.add(new Paragraph("\n"));
					if (writer.fitsPage(reqEffectivity)) {
						document.add(new Paragraph("\n"));
						document.add(reqEffectivity);
						//document.add(new Paragraph("\n"));
					} else {
						document.newPage();
						document.add(new Paragraph("\n"));
						document.add(reqEffectivity);
						//document.add(new Paragraph("\n"));
					}
					
					LogUtil.logMessage("ReqEffectivity Section:");
					
					//System Engineering Section
					LogUtil.logMessage("objChangeRequest1058VO.getStatusSeqNo():"+objChangeRequest1058VO.getStatusSeqNo());
					if(objChangeRequest1058VO.getStatusSeqNo() > 1) {
						
					PdfPCell sysEnggDetail = new PdfPCell(new Phrase(
							"SYSTEM ENGINEER SECTION", strFontSizeBoldEleven));
					sysEnggDetail.setBackgroundColor(new Color(184, 204, 228));
					sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					sysEnggDetail.setHorizontalAlignment(Element.ALIGN_CENTER);
					sysEnggDetail.setColspan(4);
					sysEnggDetail.setFixedHeight(32f);
					sysEnggSection.addCell(sysEnggDetail);
					
					sysEnggDetail = new PdfPCell(new Phrase("Systems Engineer",
							strFontSizeBoldEleven));
					sysEnggDetail.setBackgroundColor(new Color(213, 213, 213));
					sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					sysEnggDetail.setFixedHeight(28f);
					sysEnggSection.addCell(sysEnggDetail);
					
					if(objChangeRequest1058VO.getSysEnggUserName()!= null){
						sysEnggDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getSysEnggUserName(),
								strFointSizeTen));
						sysEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						sysEnggSection.addCell(sysEnggDetail);
					}else{
						sysEnggDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						sysEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						sysEnggSection.addCell(sysEnggDetail);
					}
					LogUtil.logMessage("objChangeRequest1058VO.getSysEnggUserName():"+objChangeRequest1058VO.getSysEnggUserName());
					
					sysEnggDetail = new PdfPCell(new Phrase("Date Received",
							strFontSizeBoldEleven));
					sysEnggDetail.setBackgroundColor(new Color(213, 213, 213));
					sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					sysEnggDetail.setFixedHeight(28f);
					sysEnggSection.addCell(sysEnggDetail);
					
					if(objChangeRequest1058VO.getSysEnggDateReceived()!= null){
						sysEnggDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getSysEnggDateReceived(),
								strFointSizeTen));
						sysEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						sysEnggSection.addCell(sysEnggDetail);
					}else{
						sysEnggDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						sysEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						sysEnggSection.addCell(sysEnggDetail);
					}
					
					LogUtil.logMessage("Date received");
					
					sysEnggDetail = new PdfPCell(new Phrase("Date Completed",
							strFontSizeBoldEleven));
					sysEnggDetail.setBackgroundColor(new Color(213, 213, 213));
					sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					sysEnggDetail.setFixedHeight(28f);
					sysEnggSection.addCell(sysEnggDetail);
					
					if(objChangeRequest1058VO.getSysEnggDateCompleted() !=  null){
						sysEnggDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getSysEnggDateCompleted(),
								strFointSizeTen));
						sysEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						sysEnggDetail.setColspan(3);
						sysEnggSection.addCell(sysEnggDetail);
					}else{
						sysEnggDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						sysEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						sysEnggDetail.setColspan(3);
						sysEnggSection.addCell(sysEnggDetail);
					}
					LogUtil.logMessage("Date Completed");
					
					sysEnggDetail = new PdfPCell(new Phrase("Systems Engineer Comments",
							strFontSizeBoldEleven));
					sysEnggDetail.setBackgroundColor(new Color(213, 213, 213));
					sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					sysEnggDetail.setFixedHeight(28f);
					sysEnggSection.addCell(sysEnggDetail);
					
					if(objChangeRequest1058VO.getSystemEngComment() != null){
					sysEnggDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getSystemEngComment(),
							strFointSizeTen));
					sysEnggDetail.setBackgroundColor(new Color(255, 255, 255));
					sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					sysEnggDetail.setColspan(3);
					sysEnggSection.addCell(sysEnggDetail);
					}else{
						sysEnggDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						sysEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						sysEnggDetail.setColspan(3);
						sysEnggSection.addCell(sysEnggDetail);
					}
					LogUtil.logMessage("SysEngg Comments");
					
					
					sysEnggDetail = new PdfPCell(new Phrase("Part # Added",
							strFontSizeBoldEleven));
					sysEnggDetail.setBackgroundColor(new Color(213, 213, 213));
					sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					sysEnggDetail.setFixedHeight(28f);
					sysEnggSection.addCell(sysEnggDetail);
					
					if(objChangeRequest1058VO.getPartNoAdded() != null){
						sysEnggDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getPartNoAdded(),
								strFointSizeTen));
						sysEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						sysEnggDetail.setColspan(3);
						sysEnggSection.addCell(sysEnggDetail);
					}else{
						sysEnggDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						sysEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						sysEnggDetail.setColspan(3);
						sysEnggSection.addCell(sysEnggDetail);
					}
					
					LogUtil.logMessage("Partno Added");
					
					sysEnggDetail = new PdfPCell(new Phrase("Part # Deleted",
							strFontSizeBoldEleven));
					sysEnggDetail.setBackgroundColor(new Color(213, 213, 213));
					sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					sysEnggDetail.setFixedHeight(28f);
					sysEnggSection.addCell(sysEnggDetail);
					
					if(objChangeRequest1058VO.getPartNoDeleted() != null){
					sysEnggDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getPartNoDeleted(),
							strFointSizeTen));
					sysEnggDetail.setBackgroundColor(new Color(255, 255, 255));
					sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					sysEnggDetail.setColspan(3);
					sysEnggSection.addCell(sysEnggDetail);
					}else{
						sysEnggDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						sysEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						sysEnggDetail.setColspan(3);
						sysEnggSection.addCell(sysEnggDetail);
					}
					LogUtil.logMessage("Partno Deleted");
					
					sysEnggDetail = new PdfPCell(new Phrase("Est. Design Hrs",
							strFontSizeBoldEleven));
					sysEnggDetail.setBackgroundColor(new Color(213, 213, 213));
					sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					sysEnggDetail.setFixedHeight(28f);
					sysEnggSection.addCell(sysEnggDetail);
					
					if( objChangeRequest1058VO.getDesignHrs() != null){
						sysEnggDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getDesignHrs(),
								strFointSizeTen));
						sysEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						sysEnggDetail.setColspan(3);
						sysEnggSection.addCell(sysEnggDetail);
					}else{
						sysEnggDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						sysEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						sysEnggDetail.setColspan(3);
						sysEnggSection.addCell(sysEnggDetail);
					}
					LogUtil.logMessage("Design Hr");
					
						sysEnggDetail = new PdfPCell(new Phrase("Change Affects WEIGHT",
								strFontSizeBoldEleven));
						sysEnggDetail.setBackgroundColor(new Color(213, 213, 213));
						sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						sysEnggDetail.setFixedHeight(28f);
						sysEnggSection.addCell(sysEnggDetail);
						
						if(objChangeRequest1058VO.getChangeAffectsWeight() != null)
						{
							strChangeAffectsWeight = objChangeRequest1058VO.getChangeAffectsWeight().trim();
							
							if(strChangeAffectsWeight.equalsIgnoreCase("Y")){
								strChangeAffectsWeight = "Yes";
							}else if(strChangeAffectsWeight.equalsIgnoreCase("N")){
								strChangeAffectsWeight = "No";
							}else{
								strChangeAffectsWeight = "";
							}
							
							
							sysEnggDetail = new PdfPCell(new Phrase(strChangeAffectsWeight,
									strFointSizeTen));
							sysEnggDetail.setBackgroundColor(new Color(255, 255, 255));
							sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
							sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
							sysEnggDetail.setColspan(3);
							sysEnggSection.addCell(sysEnggDetail);
						}else{
							sysEnggDetail = new PdfPCell(new Phrase("",
									strFointSizeTen));
							sysEnggDetail.setBackgroundColor(new Color(255, 255, 255));
							sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
							sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
							sysEnggDetail.setColspan(3);
							sysEnggSection.addCell(sysEnggDetail);
							
						}	
						LogUtil.logMessage("Change Affects Weight");
						
						
						sysEnggDetail = new PdfPCell(new Phrase("Change Affects CLEARANCE",
								strFontSizeBoldEleven));
						sysEnggDetail.setBackgroundColor(new Color(213, 213, 213));
						sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						sysEnggDetail.setFixedHeight(28f);
						sysEnggSection.addCell(sysEnggDetail);
						
						if(objChangeRequest1058VO.getChangeAffectsClear() != null){
							
							strChangeAffectsClear = objChangeRequest1058VO.getChangeAffectsClear().trim();
							
							if(strChangeAffectsClear.equalsIgnoreCase("Y")){
								strChangeAffectsClear = "Yes";
							}else if(strChangeAffectsClear.equalsIgnoreCase("N")){
								strChangeAffectsClear = "No";
							}else{
								strChangeAffectsClear = "";
							}
							
							sysEnggDetail = new PdfPCell(new Phrase(strChangeAffectsClear,
									strFointSizeTen));
							sysEnggDetail.setBackgroundColor(new Color(255, 255, 255));
							sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
							sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
							sysEnggDetail.setColspan(3);
							sysEnggSection.addCell(sysEnggDetail);
						
						}else{
							sysEnggDetail = new PdfPCell(new Phrase("",
									strFointSizeTen));
							sysEnggDetail.setBackgroundColor(new Color(255, 255, 255));
							sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
							sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
							sysEnggDetail.setColspan(3);
							sysEnggSection.addCell(sysEnggDetail);
							
						}
						
						LogUtil.logMessage("Change Affects Clearance");
					
						sysEnggDetail = new PdfPCell(new Phrase("Est. Drafting Hrs",
							strFontSizeBoldEleven));
						sysEnggDetail.setBackgroundColor(new Color(213, 213, 213));
						sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						sysEnggDetail.setFixedHeight(28f);
						sysEnggSection.addCell(sysEnggDetail);
					
					if( objChangeRequest1058VO.getDraftingHrs() != null){
						sysEnggDetail = new PdfPCell(new Phrase((objChangeRequest1058VO.getDraftingHrs()),
								strFointSizeTen));
						sysEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						sysEnggDetail.setColspan(3);
						sysEnggSection.addCell(sysEnggDetail);
					}else{
						sysEnggDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						sysEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						sysEnggDetail.setColspan(3);
						sysEnggSection.addCell(sysEnggDetail);
					}
					LogUtil.logMessage("Est. Drafting hours");
					
						sysEnggDetail = new PdfPCell(new Phrase("Drawing Due Date",
								strFontSizeBoldEleven));
						sysEnggDetail.setBackgroundColor(new Color(213, 213, 213));
						sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						sysEnggDetail.setFixedHeight(28f);
						sysEnggSection.addCell(sysEnggDetail);
					
					if(objChangeRequest1058VO.getDrawingDueDate() != null){
						sysEnggDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getDrawingDueDate(),
								strFointSizeTen));
						sysEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						sysEnggDetail.setColspan(3);
						sysEnggSection.addCell(sysEnggDetail);
					}else{
						sysEnggDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						sysEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						sysEnggDetail.setColspan(3);
						sysEnggSection.addCell(sysEnggDetail);
					}
					
					LogUtil.logMessage("Drawing Due Date");
					
						sysEnggDetail = new PdfPCell(new Phrase("Est. Completion Date",
								strFontSizeBoldEleven));
						sysEnggDetail.setBackgroundColor(new Color(213, 213, 213));
						sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						sysEnggDetail.setFixedHeight(28f);
						sysEnggSection.addCell(sysEnggDetail);
					
					if(objChangeRequest1058VO.getCompletionDate() != null){
						sysEnggDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getCompletionDate(),
								strFointSizeTen));
						sysEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						sysEnggDetail.setColspan(3);
						sysEnggSection.addCell(sysEnggDetail);
					}else{
						sysEnggDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						sysEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						sysEnggDetail.setColspan(3);
						sysEnggSection.addCell(sysEnggDetail);
					}
					
					LogUtil.logMessage("Est. Completion Date");
					
					sysEnggDetail = new PdfPCell(new Phrase("Work Order (USD)",
							strFontSizeBoldEleven));
					sysEnggDetail.setBackgroundColor(new Color(213, 213, 213));
					sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					sysEnggDetail.setFixedHeight(28f);
					sysEnggSection.addCell(sysEnggDetail);
					
					if(objChangeRequest1058VO.getWorkOrderUSD() != null){
					bdWorkOrderUSD = objChangeRequest1058VO.getWorkOrderUSD();
					bdWorkOrderUSD = bdWorkOrderUSD.setScale(2, BigDecimal.ROUND_HALF_UP);
					
					String strChkWorkOrderMinus = bdWorkOrderUSD.toString();
					LogUtil.logMessage("Before  strChkWorkOrderMinus :" + strChkWorkOrderMinus);
					strChkWorkOrderMinus = strChkWorkOrderMinus.substring(0,1);
					LogUtil.logMessage("After  strChkWorkOrderMinus :" + strChkWorkOrderMinus);
					if(strChkWorkOrderMinus.equalsIgnoreCase("-")){
						sysEnggDetail = new PdfPCell(new Phrase(strChkWorkOrderMinus + strDollar + 
								bdWorkOrderUSD.toString().trim().substring(1,bdWorkOrderUSD.toString().length()),
							strFointSizeTen));
					}else{
						sysEnggDetail = new PdfPCell(new Phrase(strDollar + bdWorkOrderUSD.toString().trim(),
								strFointSizeTen));	
					}
					
					sysEnggDetail.setBackgroundColor(new Color(255, 255, 255));
					sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					sysEnggDetail.setColspan(3);
					sysEnggSection.addCell(sysEnggDetail);
					}else{
						sysEnggDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						sysEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						sysEnggDetail.setColspan(3);
						sysEnggSection.addCell(sysEnggDetail);
					}
					
					LogUtil.logMessage("Work Order USD");
					
					sysEnggDetail = new PdfPCell(new Phrase("Earliest Station Affected",
							strFontSizeBoldEleven));
					sysEnggDetail.setBackgroundColor(new Color(213, 213, 213));
					sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					sysEnggDetail.setFixedHeight(28f);
					sysEnggSection.addCell(sysEnggDetail);
					
					
					if(objChangeRequest1058VO.getStationAffected() != null){
						sysEnggDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getStationAffected(),
								strFointSizeTen));
						sysEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						sysEnggDetail.setColspan(3);
						sysEnggSection.addCell(sysEnggDetail);
					}else{
						sysEnggDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						sysEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						sysEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						sysEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						sysEnggDetail.setColspan(3);
						sysEnggSection.addCell(sysEnggDetail);
					}
					
					LogUtil.logMessage("Earliest Station Affected");
					//document.add(new Paragraph("\n"));
					if (writer.fitsPage(sysEnggSection)) {
						document.add(new Paragraph("\n"));
						document.add(sysEnggSection);
						//document.add(new Paragraph("\n"));
					} else {
						document.newPage();
						document.add(new Paragraph("\n"));
						document.add(sysEnggSection);
						//document.add(new Paragraph("\n"));
					}
					
					LogUtil.logMessage("System Engineer Section:");
					
					//Operations Section
					PdfPCell oprEnggDetail = new PdfPCell(new Phrase(
							"OPERATIONS SECTION", strFontSizeBoldEleven));
					oprEnggDetail.setBackgroundColor(new Color(184, 204, 228));
					oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					oprEnggDetail.setHorizontalAlignment(Element.ALIGN_CENTER);
					oprEnggDetail.setColspan(4);
					oprEnggDetail.setFixedHeight(32f);
					opSection.addCell(oprEnggDetail);
					
					oprEnggDetail = new PdfPCell(new Phrase("Material Management",
							strFontSizeBoldEleven));
					oprEnggDetail.setBackgroundColor(new Color(213, 213, 213));
					oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					oprEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					oprEnggDetail.setFixedHeight(28f);
					opSection.addCell(oprEnggDetail);
					
					if(objChangeRequest1058VO.getOprEnggUserName() != null){
						oprEnggDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getOprEnggUserName(),
								strFointSizeTen));
						oprEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						oprEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						opSection.addCell(oprEnggDetail);
					}else{
						oprEnggDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						oprEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						oprEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						opSection.addCell(oprEnggDetail);
					}
					
					oprEnggDetail = new PdfPCell(new Phrase("Date Received",
							strFontSizeBoldEleven));
					oprEnggDetail.setBackgroundColor(new Color(213, 213, 213));
					oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					oprEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					oprEnggDetail.setFixedHeight(28f);
					opSection.addCell(oprEnggDetail);
					
					if(objChangeRequest1058VO.getOprEnggDateReceived() != null){
						oprEnggDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getOprEnggDateReceived(),
								strFointSizeTen));
						oprEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						oprEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						opSection.addCell(oprEnggDetail);
					}else{
						oprEnggDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						oprEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						oprEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						opSection.addCell(oprEnggDetail);
					}
					
					oprEnggDetail = new PdfPCell(new Phrase("Date Completed",
							strFontSizeBoldEleven));
					oprEnggDetail.setBackgroundColor(new Color(213, 213, 213));
					oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					oprEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					oprEnggDetail.setFixedHeight(28f);
					opSection.addCell(oprEnggDetail);
					
					if(objChangeRequest1058VO.getOprEnggDateCompleted() != null){
						oprEnggDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getOprEnggDateCompleted(),
								strFointSizeTen));
						oprEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						oprEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						oprEnggDetail.setColspan(3);
						opSection.addCell(oprEnggDetail);
					}else{
						oprEnggDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						oprEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						oprEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						oprEnggDetail.setColspan(3);
						opSection.addCell(oprEnggDetail);
					}
					
					oprEnggDetail = new PdfPCell(new Phrase("Operations Comments",
							strFontSizeBoldEleven));
					oprEnggDetail.setBackgroundColor(new Color(213, 213, 213));
					oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					oprEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					oprEnggDetail.setFixedHeight(28f);
					opSection.addCell(oprEnggDetail);
					
					if(objChangeRequest1058VO.getOperationComments() !=null ){
						oprEnggDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getOperationComments(),
								strFointSizeTen));
						oprEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						oprEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						oprEnggDetail.setColspan(3);
						opSection.addCell(oprEnggDetail);
					}else{
						oprEnggDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						oprEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						oprEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						oprEnggDetail.setColspan(3);
						opSection.addCell(oprEnggDetail);
					}
					
					
					oprEnggDetail = new PdfPCell(new Phrase("Disposition of Surplus Material",
							strFontSizeBoldEleven));
					oprEnggDetail.setBackgroundColor(new Color(213, 213, 213));
					oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					oprEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					oprEnggDetail.setFixedHeight(28f);
					opSection.addCell(oprEnggDetail);
					
					if(objChangeRequest1058VO.getDisposExcessMaterial() != null){
						oprEnggDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getDisposExcessMaterial(),
								strFointSizeTen));
						oprEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						oprEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						oprEnggDetail.setColspan(3);
						opSection.addCell(oprEnggDetail);
					}else{
						oprEnggDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						oprEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						oprEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						oprEnggDetail.setColspan(3);
						opSection.addCell(oprEnggDetail);
					}
					
					oprEnggDetail = new PdfPCell(new Phrase("Est. Labor Impact (Hours)",
							strFontSizeBoldEleven));
					oprEnggDetail.setBackgroundColor(new Color(213, 213, 213));
					oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					oprEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					oprEnggDetail.setFixedHeight(28f);
					opSection.addCell(oprEnggDetail);
					
					if(objChangeRequest1058VO.getLaborImpact() != null){
						oprEnggDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getLaborImpact(),
								strFointSizeTen));
						oprEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						oprEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						oprEnggDetail.setColspan(3);
						opSection.addCell(oprEnggDetail);
					}else{
						oprEnggDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						oprEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						oprEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						oprEnggDetail.setColspan(3);
						opSection.addCell(oprEnggDetail);
					}
					
					oprEnggDetail = new PdfPCell(new Phrase("Supplier Affected Costs/ Fees/ Effectivity",
							strFontSizeBoldEleven));
					oprEnggDetail.setBackgroundColor(new Color(213, 213, 213));
					oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					oprEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					oprEnggDetail.setFixedHeight(28f);
					opSection.addCell(oprEnggDetail);
					
					if(objChangeRequest1058VO.getSupplierAffectedCost() != null){
						oprEnggDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getSupplierAffectedCost(),
								strFointSizeTen));
						oprEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						oprEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						oprEnggDetail.setColspan(3);
						opSection.addCell(oprEnggDetail);
					}else{
						oprEnggDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						oprEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						oprEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						oprEnggDetail.setColspan(3);
						opSection.addCell(oprEnggDetail);
					}
					
					oprEnggDetail = new PdfPCell(new Phrase("Recommended Effective Delivery (Unit #)",
							strFontSizeBoldEleven));
					oprEnggDetail.setBackgroundColor(new Color(213, 213, 213));
					oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					oprEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					oprEnggDetail.setFixedHeight(28f);
					opSection.addCell(oprEnggDetail);
					
					if(objChangeRequest1058VO.getRecEffectivityDel() !=  null){
						oprEnggDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getRecEffectivityDel(),
								strFointSizeTen));
						oprEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						oprEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						oprEnggDetail.setColspan(3);
						opSection.addCell(oprEnggDetail);
					}else{
						oprEnggDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						oprEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						oprEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						oprEnggDetail.setColspan(3);
						opSection.addCell(oprEnggDetail);
					}
					
					oprEnggDetail = new PdfPCell(new Phrase("Tooling Cost (USD)",
							strFontSizeBoldEleven));
					oprEnggDetail.setBackgroundColor(new Color(213, 213, 213));
					oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					oprEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					oprEnggDetail.setFixedHeight(28f);
					opSection.addCell(oprEnggDetail);
					
					LogUtil.logMessage("objChangeRequest1058VO.getToolingCostUSD():" + objChangeRequest1058VO.getToolingCostUSD());
					
					if(objChangeRequest1058VO.getToolingCostUSD() != null){
					bdToolingCostUSD =objChangeRequest1058VO.getToolingCostUSD();
					bdToolingCostUSD = bdToolingCostUSD.setScale(2, BigDecimal.ROUND_HALF_UP);
					
					String strChkToolingCostMinus = bdToolingCostUSD.toString();
					LogUtil.logMessage("Before  strChkToolingCostMinus :" + strChkToolingCostMinus);
					strChkToolingCostMinus = strChkToolingCostMinus.substring(0,1);
					LogUtil.logMessage("After  strChkToolingCostMinus :" + strChkToolingCostMinus);
					if(strChkToolingCostMinus.equalsIgnoreCase("-")){
						oprEnggDetail = new PdfPCell(new Phrase(strChkToolingCostMinus + strDollar + 
								bdToolingCostUSD.toString().trim().substring(1,bdToolingCostUSD.toString().length()),
								strFointSizeTen));
					}else{
						oprEnggDetail = new PdfPCell(new Phrase(strDollar + bdToolingCostUSD.toString().trim(),
							strFointSizeTen));
					}
					oprEnggDetail.setBackgroundColor(new Color(255, 255, 255));
					oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					oprEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					oprEnggDetail.setColspan(3);
					opSection.addCell(oprEnggDetail);
					}else{
						oprEnggDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						oprEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						oprEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						oprEnggDetail.setColspan(3);
						opSection.addCell(oprEnggDetail);
					}
					
					//LogUtil.logMessage("objChangeRequest1058VO.getScrapCostUSD():" + objChangeRequest1058VO.getScrapCostUSD());
					
					oprEnggDetail = new PdfPCell(new Phrase("Est. Scrap in $'s (USD)",
							strFontSizeBoldEleven));
					oprEnggDetail.setBackgroundColor(new Color(213, 213, 213));
					oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					oprEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					oprEnggDetail.setFixedHeight(28f);
					opSection.addCell(oprEnggDetail);
					
					if(objChangeRequest1058VO.getScrapCostUSD() != null){
					bdScrapCostUSD = objChangeRequest1058VO.getScrapCostUSD();
					bdScrapCostUSD = bdScrapCostUSD.setScale(2, BigDecimal.ROUND_HALF_UP);
					
					String strChkScrapCostMinus = bdScrapCostUSD.toString();
					LogUtil.logMessage("Before  strChkScrapCostMinus :" + strChkScrapCostMinus);
					strChkScrapCostMinus = strChkScrapCostMinus.substring(0,1);
					LogUtil.logMessage("After  strChkScrapCostMinus :" + strChkScrapCostMinus);
					if(strChkScrapCostMinus.equalsIgnoreCase("-")){
						oprEnggDetail = new PdfPCell(new Phrase(strChkScrapCostMinus + strDollar + 
								bdScrapCostUSD.toString().trim().substring(1,bdScrapCostUSD.toString().length()),
								strFointSizeTen));
					}else{
						oprEnggDetail = new PdfPCell(new Phrase(strDollar + bdScrapCostUSD.toString().trim(),
							strFointSizeTen));
					}
					oprEnggDetail.setBackgroundColor(new Color(255, 255, 255));
					oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					oprEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					oprEnggDetail.setColspan(3);
					opSection.addCell(oprEnggDetail);
					}else{
						oprEnggDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						oprEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						oprEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						oprEnggDetail.setColspan(3);
						opSection.addCell(oprEnggDetail);
					}
					
					oprEnggDetail = new PdfPCell(new Phrase("Rework Expense (USD)",
							strFontSizeBoldEleven));
					oprEnggDetail.setBackgroundColor(new Color(213, 213, 213));
					oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					oprEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					oprEnggDetail.setFixedHeight(28f);
					opSection.addCell(oprEnggDetail);
					
					if(objChangeRequest1058VO.getReworkCost() != null){
					bdReWorkCostUSD =objChangeRequest1058VO.getReworkCost();
					bdReWorkCostUSD = bdReWorkCostUSD.setScale(2, BigDecimal.ROUND_HALF_UP);
					
					String strChkReWorkCostMinus = bdReWorkCostUSD.toString();
					LogUtil.logMessage("Before  strChkReWorkCostMinus :" + strChkReWorkCostMinus);
					strChkReWorkCostMinus = strChkReWorkCostMinus.substring(0,1);
					LogUtil.logMessage("After  strChkReWorkCostMinus :" + strChkReWorkCostMinus);
					if(strChkReWorkCostMinus.equalsIgnoreCase("-")){
						oprEnggDetail = new PdfPCell(new Phrase(strChkReWorkCostMinus + strDollar + 
								bdReWorkCostUSD.toString().trim().substring(1,bdReWorkCostUSD.toString().length()),
								strFointSizeTen));
					}else{
						oprEnggDetail = new PdfPCell(new Phrase(strDollar + bdReWorkCostUSD.toString().trim(),
							strFointSizeTen));
					}
					oprEnggDetail.setBackgroundColor(new Color(255, 255, 255));
					oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					oprEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					oprEnggDetail.setColspan(3);
					opSection.addCell(oprEnggDetail);
					}else{
						oprEnggDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						oprEnggDetail.setBackgroundColor(new Color(255, 255, 255));
						oprEnggDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						oprEnggDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						oprEnggDetail.setColspan(3);
						opSection.addCell(oprEnggDetail);
					}
					//document.add(new Paragraph("\n"));
					if (writer.fitsPage(opSection)) {
						document.add(new Paragraph("\n"));
						document.add(opSection);
						//document.add(new Paragraph("\n"));
					} else {
						document.newPage();
						document.add(new Paragraph("\n"));
						document.add(opSection);
						//document.add(new Paragraph("\n"));
					}
					
					LogUtil.logMessage("Operation Section:");
					
					//Finance Manager Section
					
					PdfPCell financeDetail = new PdfPCell(new Phrase(
							"FINANCE SECTION", strFontSizeBoldEleven));
					financeDetail.setBackgroundColor(new Color(184, 204, 228));
					financeDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					financeDetail.setHorizontalAlignment(Element.ALIGN_CENTER);
					financeDetail.setColspan(4);
					financeDetail.setFixedHeight(32f);
					finSection.addCell(financeDetail);
					
					financeDetail = new PdfPCell(new Phrase("Finance Contact",
							strFontSizeBoldEleven));
					financeDetail.setBackgroundColor(new Color(213, 213, 213));
					financeDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					financeDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					financeDetail.setFixedHeight(28f);
					finSection.addCell(financeDetail);
					
					if(objChangeRequest1058VO.getFinanceUserName() != null){
					financeDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getFinanceUserName(),
							strFointSizeTen));
					financeDetail.setBackgroundColor(new Color(255, 255, 255));
					financeDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					financeDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					finSection.addCell(financeDetail);
					}else{
						financeDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						financeDetail.setBackgroundColor(new Color(255, 255, 255));
						financeDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						financeDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						finSection.addCell(financeDetail);
					}
					
					financeDetail = new PdfPCell(new Phrase("Date Received",
							strFontSizeBoldEleven));
					financeDetail.setBackgroundColor(new Color(213, 213, 213));
					financeDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					financeDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					financeDetail.setFixedHeight(28f);
					finSection.addCell(financeDetail);
					
					if(objChangeRequest1058VO.getFinanceDateReceived() != null){
						financeDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getFinanceDateReceived(),
								strFointSizeTen));
						financeDetail.setBackgroundColor(new Color(255, 255, 255));
						financeDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						financeDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						finSection.addCell(financeDetail);
					}else{
						financeDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						financeDetail.setBackgroundColor(new Color(255, 255, 255));
						financeDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						financeDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						finSection.addCell(financeDetail);
					}
					
					financeDetail = new PdfPCell(new Phrase("Date Completed",
							strFontSizeBoldEleven));
					financeDetail.setBackgroundColor(new Color(213, 213, 213));
					financeDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					financeDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					financeDetail.setFixedHeight(28f);
					finSection.addCell(financeDetail);
					
					if(objChangeRequest1058VO.getFinanceDateCompleted() != null){
						financeDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getFinanceDateCompleted(),
								strFointSizeTen));
						financeDetail.setBackgroundColor(new Color(255, 255, 255));
						financeDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						financeDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						financeDetail.setColspan(3);
						finSection.addCell(financeDetail);
					}else{
						financeDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						financeDetail.setBackgroundColor(new Color(255, 255, 255));
						financeDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						financeDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						financeDetail.setColspan(3);
						finSection.addCell(financeDetail);
					}
					
					financeDetail = new PdfPCell(new Phrase("Finance Comments",
							strFontSizeBoldEleven));
					financeDetail.setBackgroundColor(new Color(213, 213, 213));
					financeDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					financeDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					financeDetail.setFixedHeight(28f);
					finSection.addCell(financeDetail);
					
					if(objChangeRequest1058VO.getFinanceComments() != null){
					financeDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getFinanceComments(),
							strFointSizeTen));
					financeDetail.setBackgroundColor(new Color(255, 255, 255));
					financeDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					financeDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					financeDetail.setColspan(3);
					finSection.addCell(financeDetail);
					}else{
						financeDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						financeDetail.setBackgroundColor(new Color(255, 255, 255));
						financeDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						financeDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						financeDetail.setColspan(3);
						finSection.addCell(financeDetail);
					}
					
					financeDetail = new PdfPCell(new Phrase("Est. Product Cost Change",
							strFontSizeBoldEleven));
					financeDetail.setBackgroundColor(new Color(213, 213, 213));
					financeDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					financeDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					financeDetail.setFixedHeight(28f);
					finSection.addCell(financeDetail);
					
					if(objChangeRequest1058VO.getProdCostChange() != null){
					financeDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getProdCostChange(),
							strFointSizeTen));
					financeDetail.setBackgroundColor(new Color(255, 255, 255));
					financeDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					financeDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					financeDetail.setColspan(3);
					finSection.addCell(financeDetail);
					}else{
						financeDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						financeDetail.setBackgroundColor(new Color(255, 255, 255));
						financeDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						financeDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						financeDetail.setColspan(3);
						finSection.addCell(financeDetail);
					}
					
					financeDetail = new PdfPCell(new Phrase("Est. Product Cost Change for Supplier",
							strFontSizeBoldEleven));
					financeDetail.setBackgroundColor(new Color(213, 213, 213));
					financeDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					financeDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					financeDetail.setFixedHeight(28f);
					finSection.addCell(financeDetail);
					
					if(objChangeRequest1058VO.getProdCostChangeSup() != null){
					financeDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getProdCostChangeSup(),
							strFointSizeTen));
					financeDetail.setBackgroundColor(new Color(255, 255, 255));
					financeDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					financeDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					financeDetail.setColspan(3);
					finSection.addCell(financeDetail);
					}else{
						financeDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						financeDetail.setBackgroundColor(new Color(255, 255, 255));
						financeDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						financeDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						financeDetail.setColspan(3);
						finSection.addCell(financeDetail);
					}
					//document.add(new Paragraph("\n"));
					if (writer.fitsPage(finSection)) {
						document.add(new Paragraph("\n"));
						document.add(finSection);
						//document.add(new Paragraph("\n"));
					} else {
						document.newPage();
						document.add(new Paragraph("\n"));
						document.add(finSection);
						//document.add(new Paragraph("\n"));
					}
					
					LogUtil.logMessage("Finance Section:");
					
					//Program Manager Section
					
					PdfPCell pgMgrDetail = new PdfPCell(new Phrase(
							"PROGRAM MANAGER SECTION", strFontSizeBoldEleven));
					pgMgrDetail.setBackgroundColor(new Color(184, 204, 228));
					pgMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					pgMgrDetail.setHorizontalAlignment(Element.ALIGN_CENTER);
					pgMgrDetail.setColspan(4);
					pgMgrDetail.setFixedHeight(32f);
					prgMgrSection.addCell(pgMgrDetail);
					
					pgMgrDetail = new PdfPCell(new Phrase("Program Manager",
							strFontSizeBoldEleven));
					pgMgrDetail.setBackgroundColor(new Color(213, 213, 213));
					pgMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					pgMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					pgMgrDetail.setFixedHeight(28f);
					prgMgrSection.addCell(pgMgrDetail);
					
					if(objChangeRequest1058VO.getPgmMgrUserName() != null){
					pgMgrDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getPgmMgrUserName(),
							strFointSizeTen));
					pgMgrDetail.setBackgroundColor(new Color(255, 255, 255));
					pgMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					pgMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					prgMgrSection.addCell(pgMgrDetail);
					}else{
						pgMgrDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						pgMgrDetail.setBackgroundColor(new Color(255, 255, 255));
						pgMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						pgMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						prgMgrSection.addCell(pgMgrDetail);
					}
					
					pgMgrDetail = new PdfPCell(new Phrase("Date Received",
							strFontSizeBoldEleven));
					pgMgrDetail.setBackgroundColor(new Color(213, 213, 213));
					pgMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					pgMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					pgMgrDetail.setFixedHeight(28f);
					prgMgrSection.addCell(pgMgrDetail);
					
					if(objChangeRequest1058VO.getProgManagerDateReceived() != null){
					pgMgrDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getProgManagerDateReceived(),
							strFointSizeTen));
					pgMgrDetail.setBackgroundColor(new Color(255, 255, 255));
					pgMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					pgMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					prgMgrSection.addCell(pgMgrDetail);
					}else{
						pgMgrDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						pgMgrDetail.setBackgroundColor(new Color(255, 255, 255));
						pgMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						pgMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						prgMgrSection.addCell(pgMgrDetail);
					}
					
					pgMgrDetail = new PdfPCell(new Phrase("Date Completed",
							strFontSizeBoldEleven));
					pgMgrDetail.setBackgroundColor(new Color(213, 213, 213));
					pgMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					pgMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					pgMgrDetail.setFixedHeight(28f);
					prgMgrSection.addCell(pgMgrDetail);
					
					if(objChangeRequest1058VO.getProgManagerDateCompleted() != null){
					pgMgrDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getProgManagerDateCompleted(),
							strFointSizeTen));
					pgMgrDetail.setBackgroundColor(new Color(255, 255, 255));
					pgMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					pgMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					pgMgrDetail.setColspan(3);
					prgMgrSection.addCell(pgMgrDetail);
					}else{
						pgMgrDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						pgMgrDetail.setBackgroundColor(new Color(255, 255, 255));
						pgMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						pgMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						pgMgrDetail.setColspan(3);
						prgMgrSection.addCell(pgMgrDetail);
					}
					
					pgMgrDetail = new PdfPCell(new Phrase("Program Manager Comments",
							strFontSizeBoldEleven));
					pgMgrDetail.setBackgroundColor(new Color(213, 213, 213));
					pgMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					pgMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					pgMgrDetail.setFixedHeight(28f);
					prgMgrSection.addCell(pgMgrDetail);
					
					if(objChangeRequest1058VO.getProgManagerComments() != null){
					pgMgrDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getProgManagerComments(),
							strFointSizeTen));
					pgMgrDetail.setBackgroundColor(new Color(255, 255, 255));
					pgMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					pgMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					pgMgrDetail.setColspan(3);
					prgMgrSection.addCell(pgMgrDetail);
					}else{
						pgMgrDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						pgMgrDetail.setBackgroundColor(new Color(255, 255, 255));
						pgMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						pgMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						pgMgrDetail.setColspan(3);
						prgMgrSection.addCell(pgMgrDetail);
					}
					//document.add(new Paragraph("\n"));
					if (writer.fitsPage(prgMgrSection)) {
						document.add(new Paragraph("\n"));
						document.add(prgMgrSection);
						//document.add(new Paragraph("\n"));
					} else {
						document.newPage();
						document.add(new Paragraph("\n"));
						document.add(prgMgrSection);
						//document.add(new Paragraph("\n"));
					}
					
					LogUtil.logMessage("Program Manager Section:");
					
					//Proposal Manager Section
					PdfPCell propMgrDetail = new PdfPCell(new Phrase(
							"PROPOSAL MANAGER SECTION", strFontSizeBoldEleven));
					propMgrDetail.setBackgroundColor(new Color(184, 204, 228));
					propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					propMgrDetail.setHorizontalAlignment(Element.ALIGN_CENTER);
					propMgrDetail.setColspan(4);
					propMgrDetail.setFixedHeight(32f);
					propMgrSection.addCell(propMgrDetail);
					
					propMgrDetail = new PdfPCell(new Phrase("Proposal Manager",
							strFontSizeBoldEleven));
					propMgrDetail.setBackgroundColor(new Color(213, 213, 213));
					propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					propMgrDetail.setFixedHeight(28f);
					propMgrSection.addCell(propMgrDetail);
					
					if(objChangeRequest1058VO.getPropMgrUserName() != null){
					propMgrDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getPropMgrUserName(),
							strFointSizeTen));
					propMgrDetail.setBackgroundColor(new Color(255, 255, 255));
					propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					propMgrSection.addCell(propMgrDetail);
					}else{
						propMgrDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						propMgrDetail.setBackgroundColor(new Color(255, 255, 255));
						propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						propMgrSection.addCell(propMgrDetail);
					}
					
					propMgrDetail = new PdfPCell(new Phrase("Date Received",
							strFontSizeBoldEleven));
					propMgrDetail.setBackgroundColor(new Color(213, 213, 213));
					propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					propMgrDetail.setFixedHeight(28f);
					propMgrSection.addCell(propMgrDetail);
					
					if(objChangeRequest1058VO.getPropManagerDateReceived() != null){
					propMgrDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getPropManagerDateReceived(),
							strFointSizeTen));
					propMgrDetail.setBackgroundColor(new Color(255, 255, 255));
					propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					propMgrSection.addCell(propMgrDetail);
					}else{
						propMgrDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						propMgrDetail.setBackgroundColor(new Color(255, 255, 255));
						propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						propMgrSection.addCell(propMgrDetail);
					}
					
					propMgrDetail = new PdfPCell(new Phrase("Date Completed",
							strFontSizeBoldEleven));
					propMgrDetail.setBackgroundColor(new Color(213, 213, 213));
					propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					propMgrDetail.setFixedHeight(28f);
					propMgrSection.addCell(propMgrDetail);
					
					if(objChangeRequest1058VO.getPropManagerDateCompleted() != null){
					propMgrDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getPropManagerDateCompleted(),
							strFointSizeTen));
					propMgrDetail.setBackgroundColor(new Color(255, 255, 255));
					propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					propMgrDetail.setColspan(3);
					propMgrSection.addCell(propMgrDetail);
					}else{
						propMgrDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						propMgrDetail.setBackgroundColor(new Color(255, 255, 255));
						propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						propMgrDetail.setColspan(3);
						propMgrSection.addCell(propMgrDetail);
					}
					
					propMgrDetail = new PdfPCell(new Phrase("Proposal Manager Comments",
							strFontSizeBoldEleven));
					propMgrDetail.setBackgroundColor(new Color(213, 213, 213));
					propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					propMgrDetail.setFixedHeight(28f);
					propMgrSection.addCell(propMgrDetail);
					
					if(objChangeRequest1058VO.getPropManagerComments() != null){
					propMgrDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getPropManagerComments(),
							strFointSizeTen));
					propMgrDetail.setBackgroundColor(new Color(255, 255, 255));
					propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					propMgrDetail.setColspan(3);
					propMgrSection.addCell(propMgrDetail);
					}else{
						propMgrDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						propMgrDetail.setBackgroundColor(new Color(255, 255, 255));
						propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						propMgrDetail.setColspan(3);
						propMgrSection.addCell(propMgrDetail);
					}
					
					propMgrDetail = new PdfPCell(new Phrase("Sell Price Submitted to Customer (USD)",
							strFontSizeBoldEleven));
					propMgrDetail.setBackgroundColor(new Color(213, 213, 213));
					propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					propMgrDetail.setFixedHeight(28f);
					propMgrSection.addCell(propMgrDetail);
					
					if(objChangeRequest1058VO.getSellpriceCustomer() != null){
					bdSellPriceCustomer = objChangeRequest1058VO.getSellpriceCustomer();
					bdSellPriceCustomer = bdSellPriceCustomer.setScale(2, BigDecimal.ROUND_HALF_UP);
					
					String strChkSellPriceMinus = bdSellPriceCustomer.toString();
					LogUtil.logMessage("Before  strChkSellPriceMinus :" + strChkSellPriceMinus);
					strChkSellPriceMinus = strChkSellPriceMinus.substring(0,1);
					LogUtil.logMessage("After  strChkSellPriceMinus :" + strChkSellPriceMinus);
					if(strChkSellPriceMinus.equalsIgnoreCase("-")){
						propMgrDetail = new PdfPCell(new Phrase(strChkSellPriceMinus + strDollar + 
								bdSellPriceCustomer.toString().trim().substring(1,bdSellPriceCustomer.toString().length()),
								strFointSizeTen));
					}else{
						propMgrDetail = new PdfPCell(new Phrase(strDollar + bdSellPriceCustomer.toString().trim(),
							strFointSizeTen));
					}
					propMgrDetail.setBackgroundColor(new Color(255, 255, 255));
					propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					propMgrDetail.setColspan(3);
					propMgrSection.addCell(propMgrDetail);
					}else{
						propMgrDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						propMgrDetail.setBackgroundColor(new Color(255, 255, 255));
						propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						propMgrDetail.setColspan(3);
						propMgrSection.addCell(propMgrDetail);
					}
					propMgrDetail = new PdfPCell(new Phrase("Mark Up",
							strFontSizeBoldEleven));
					propMgrDetail.setBackgroundColor(new Color(213, 213, 213));
					propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					propMgrDetail.setFixedHeight(28f);
					propMgrSection.addCell(propMgrDetail);
					
					if(objChangeRequest1058VO.getMarkUp() != null){
					bdMarkUp = objChangeRequest1058VO.getMarkUp();
					bdMarkUp = bdMarkUp.setScale(2, BigDecimal.ROUND_HALF_UP);
					
					propMgrDetail = new PdfPCell(new Phrase(bdMarkUp.toString().trim() + "%",
							strFointSizeTen));
					propMgrDetail.setBackgroundColor(new Color(255, 255, 255));
					propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					propMgrDetail.setColspan(3);
					propMgrSection.addCell(propMgrDetail);
					}else{
						propMgrDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						propMgrDetail.setBackgroundColor(new Color(255, 255, 255));
						propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						propMgrDetail.setColspan(3);
						propMgrSection.addCell(propMgrDetail);
					}
					propMgrDetail = new PdfPCell(new Phrase("Customer Approval",
							strFontSizeBoldEleven));
					propMgrDetail.setBackgroundColor(new Color(213, 213, 213));
					propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					propMgrDetail.setFixedHeight(28f);
					propMgrSection.addCell(propMgrDetail);
					
					if(objChangeRequest1058VO.getCustomerApprovalReq() != null){
					propMgrDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getCustomerApprovalReq(),
							strFointSizeTen));
					propMgrDetail.setBackgroundColor(new Color(255, 255, 255));
					propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					propMgrDetail.setColspan(3);
					propMgrSection.addCell(propMgrDetail);
					}else{
						propMgrDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						propMgrDetail.setBackgroundColor(new Color(255, 255, 255));
						propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						propMgrDetail.setColspan(3);
						propMgrSection.addCell(propMgrDetail);
					}
					if(objChangeRequest1058VO.getCustomerApprovalReq() != null 
					&& objChangeRequest1058VO.getCustomerApprovalReq().trim().equalsIgnoreCase("Required")){
						
					propMgrDetail = new PdfPCell(new Phrase("Customer Decision",
							strFontSizeBoldEleven));
					propMgrDetail.setBackgroundColor(new Color(213, 213, 213));
					propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					propMgrDetail.setFixedHeight(28f);
					propMgrSection.addCell(propMgrDetail);
					
					if(objChangeRequest1058VO.getCustomerDecision() != null){
					propMgrDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getCustomerDecision(),
							strFointSizeTen));
					propMgrDetail.setBackgroundColor(new Color(255, 255, 255));
					propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					propMgrDetail.setColspan(3);
					propMgrSection.addCell(propMgrDetail);
					}else{
						propMgrDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						propMgrDetail.setBackgroundColor(new Color(255, 255, 255));
						propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						propMgrDetail.setColspan(3);
						propMgrSection.addCell(propMgrDetail);
					}
					
					propMgrDetail = new PdfPCell(new Phrase("Count of Days",
							strFontSizeBoldEleven));
					propMgrDetail.setBackgroundColor(new Color(213, 213, 213));
					propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					propMgrDetail.setFixedHeight(28f);
					propMgrSection.addCell(propMgrDetail);
					
					if(objChangeRequest1058VO.getCountDays() != 0){
					propMgrDetail = new PdfPCell(new Phrase(Integer.toString(objChangeRequest1058VO.getCountDays()),
							strFointSizeTen));
					propMgrDetail.setBackgroundColor(new Color(255, 255, 255));
					propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					propMgrDetail.setColspan(3);
					propMgrSection.addCell(propMgrDetail);
					}else{
						propMgrDetail = new PdfPCell(new Phrase(Integer.toString(objChangeRequest1058VO.getCountDays()),
								strFointSizeTen));
						propMgrDetail.setBackgroundColor(new Color(255, 255, 255));
						propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						propMgrDetail.setColspan(3);
						propMgrSection.addCell(propMgrDetail);
					}
					
					propMgrDetail = new PdfPCell(new Phrase("Customer Decision Date",
							strFontSizeBoldEleven));
					propMgrDetail.setBackgroundColor(new Color(213, 213, 213));
					propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					propMgrDetail.setFixedHeight(28f);
					propMgrSection.addCell(propMgrDetail);
					
					if(objChangeRequest1058VO.getCustomerDecisionDate() != null){
					propMgrDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getCustomerDecisionDate(),
							strFointSizeTen));
					propMgrDetail.setBackgroundColor(new Color(255, 255, 255));
					propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					propMgrDetail.setColspan(3);
					propMgrSection.addCell(propMgrDetail);
					}else{
						propMgrDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						propMgrDetail.setBackgroundColor(new Color(255, 255, 255));
						propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						propMgrDetail.setColspan(3);
						propMgrSection.addCell(propMgrDetail);
					}
					
					}
					
					propMgrDetail = new PdfPCell(new Phrase("Actual Sell Price",
							strFontSizeBoldEleven));
					propMgrDetail.setBackgroundColor(new Color(213, 213, 213));
					propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					propMgrDetail.setFixedHeight(28f);
					propMgrSection.addCell(propMgrDetail);
					
					if(objChangeRequest1058VO.getActualSellPrice() != null){ 
					bdActualSellPrice = objChangeRequest1058VO.getActualSellPrice();
					bdActualSellPrice = bdActualSellPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
					
					String strChkActualSellPriceMinus = bdActualSellPrice.toString();
					LogUtil.logMessage("Before  strChkActualSellPriceMinus :" + strChkActualSellPriceMinus);
					strChkActualSellPriceMinus = strChkActualSellPriceMinus.substring(0,1);
					LogUtil.logMessage("After  strChkActualSellPriceMinus :" + strChkActualSellPriceMinus);
					if(strChkActualSellPriceMinus.equalsIgnoreCase("-")){
						propMgrDetail = new PdfPCell(new Phrase(strChkActualSellPriceMinus + strDollar + 
								bdActualSellPrice.toString().trim().substring(1,bdActualSellPrice.toString().length()),
								strFointSizeTen));
					}else{
						propMgrDetail = new PdfPCell(new Phrase(strDollar + bdActualSellPrice.toString().trim(),
							strFointSizeTen));
					}	
					propMgrDetail.setBackgroundColor(new Color(255, 255, 255));
					propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					propMgrDetail.setColspan(3);
					propMgrSection.addCell(propMgrDetail);
					}else{
						propMgrDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						propMgrDetail.setBackgroundColor(new Color(255, 255, 255));
						propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						propMgrDetail.setColspan(3);
						propMgrSection.addCell(propMgrDetail);
					}
					
					if(objChangeRequest1058VO.getCustomerApprovalReq() != null 
					   && objChangeRequest1058VO.getCustomerApprovalReq().trim().equalsIgnoreCase("Required")){
					
					propMgrDetail = new PdfPCell(new Phrase("Customer Approval Details",
							strFontSizeBoldEleven));
					propMgrDetail.setBackgroundColor(new Color(213, 213, 213));
					propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					propMgrDetail.setFixedHeight(28f);
					propMgrSection.addCell(propMgrDetail);
					
					if(objChangeRequest1058VO.getCustApprovalDet() != null){
					propMgrDetail = new PdfPCell(new Phrase(objChangeRequest1058VO.getCustApprovalDet(),
							strFointSizeTen));
					propMgrDetail.setBackgroundColor(new Color(255, 255, 255));
					propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					propMgrDetail.setColspan(3);
					propMgrSection.addCell(propMgrDetail);
					}else{
						propMgrDetail = new PdfPCell(new Phrase("",
								strFointSizeTen));
						propMgrDetail.setBackgroundColor(new Color(255, 255, 255));
						propMgrDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						propMgrDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
						propMgrDetail.setColspan(3);
						propMgrSection.addCell(propMgrDetail);
					}
					}
					//document.add(new Paragraph("\n"));
					if (writer.fitsPage(propMgrSection)) {
						document.add(new Paragraph("\n"));
						document.add(propMgrSection);
						//document.add(new Paragraph("\n"));
					} else {
						document.newPage();
						document.add(new Paragraph("\n"));
						document.add(propMgrSection);
						//document.add(new Paragraph("\n"));
					}
					LogUtil.logMessage("ProPosal Manager Section:");
					
					}
					//Action Record Details Section 
					
					LogUtil.logMessage("Action Detail Section:");
					if(objChangeRequest1058VO.getActionList()!= null){
					
					ArrayList arlActionDetails1058VO = objChangeRequest1058VO.getActionList();
					LogUtil.logMessage("Action Detail Before Size Fetching:");
					//LogUtil.logMessage("arlActionDetails1058VO.size():"+arlActionDetails1058VO.size());
					
					if (arlActionDetails1058VO != null & arlActionDetails1058VO.size() > 0)	{
						
						LogUtil.logMessage("arlActionDetails1058VO.size():"+arlActionDetails1058VO.size());

						PdfPCell actionDetail = new PdfPCell(new Phrase(
								"ACTION RECORD", strFontSizeBoldEleven));
						actionDetail.setBackgroundColor(new Color(184, 204, 228));
						actionDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						actionDetail.setHorizontalAlignment(Element.ALIGN_CENTER);
						actionDetail.setColspan(4);
						actionDetail.setFixedHeight(32f);
						actionDetails.addCell(actionDetail);	
						
						actionDetail = new PdfPCell(new Phrase("User",
								strFontSizeBoldEleven));
						actionDetail.setBackgroundColor(new Color(213, 213, 213));
						actionDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						actionDetail.setHorizontalAlignment(Element.ALIGN_CENTER);
						actionDetail.setFixedHeight(28f);
						actionDetails.addCell(actionDetail);
						
						actionDetail = new PdfPCell(new Phrase("Section",
								strFontSizeBoldEleven));
						actionDetail.setBackgroundColor(new Color(213, 213, 213));
						actionDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						actionDetail.setHorizontalAlignment(Element.ALIGN_CENTER);
						actionDetail.setFixedHeight(28f);
						actionDetails.addCell(actionDetail);
						
						actionDetail = new PdfPCell(new Phrase("Action",
								strFontSizeBoldEleven));
						actionDetail.setBackgroundColor(new Color(213, 213, 213));
						actionDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						actionDetail.setHorizontalAlignment(Element.ALIGN_CENTER);
						actionDetail.setFixedHeight(28f);
						actionDetails.addCell(actionDetail);
						
						actionDetail = new PdfPCell(new Phrase("Action date",
								strFontSizeBoldEleven));
						actionDetail.setBackgroundColor(new Color(213, 213, 213));
						actionDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						actionDetail.setHorizontalAlignment(Element.ALIGN_CENTER);
						actionDetail.setFixedHeight(28f);
						actionDetails.addCell(actionDetail);
						
					LogUtil.logMessage("Before For loop arlActionDetails1058VO.size():"+arlActionDetails1058VO.size());
					
					for (int nCnt = 0; nCnt < arlActionDetails1058VO.size(); nCnt++) {
						
					LogUtil.logMessage("After For loop arlActionDetails1058VO.size():"+arlActionDetails1058VO.size());
					
					ChangeRequest1058VO obj1058ActionRecordsVO = (ChangeRequest1058VO) arlActionDetails1058VO.get(nCnt);
					
					actionDetail = new PdfPCell(new Phrase(obj1058ActionRecordsVO.getUserName(),
							strFointSizeTen));
					actionDetail.setBackgroundColor(new Color(255, 255, 255));
					actionDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					actionDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					actionDetail.setFixedHeight(18f);
					actionDetails.addCell(actionDetail);
					
					actionDetail = new PdfPCell(new Phrase(obj1058ActionRecordsVO.getSection(),
							strFointSizeTen));
					actionDetail.setBackgroundColor(new Color(255, 255, 255));
					actionDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					actionDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					actionDetails.addCell(actionDetail);
					
					actionDetail = new PdfPCell(new Phrase(obj1058ActionRecordsVO.getAction(),
							strFointSizeTen));
					actionDetail.setBackgroundColor(new Color(255, 255, 255));
					actionDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					actionDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					actionDetails.addCell(actionDetail);
					
					actionDetail = new PdfPCell(new Phrase(obj1058ActionRecordsVO.getActionDate(),
							strFointSizeTen));
					actionDetail.setBackgroundColor(new Color(255, 255, 255));
					actionDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					actionDetail.setHorizontalAlignment(Element.ALIGN_LEFT);
					actionDetails.addCell(actionDetail);
					}
					
					//document.add(new Paragraph("\n"));
					if (writer.fitsPage(actionDetails)) {
						document.add(new Paragraph("\n"));
						document.add(actionDetails);
						//document.add(new Paragraph("\n"));
					} else {						
						document.newPage();
						document.add(new Paragraph("\n"));
						document.add(actionDetails);
						//document.add(new Paragraph("\n"));
					}
					LogUtil.logMessage("Action Detail Section:");
					}
					}
				}
				
			}
			
			
		} catch (Exception objExp) {
			//throw new Exception(objExp);
			LogUtil.logMessage("Enters into Exception Block in SearchRequest1058Action..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
		}		
	}
	
	/***************************************************************************
	 * This method is for Add new line to PDF data
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param document
	 * @return void
	 * @throws Exception
	 * @author SM105772
	 **************************************************************************/
	
	private static void addNewLine(Document document) throws Exception {
		try	{
			Phrase phrase = new Phrase();
			phrase.add("\n");
			document.add(phrase);
		}
		catch (Exception objExp) {
			throw objExp;
		}
	}
	
	
	/***************************************************************************
	 * This method is for getting Clause Table Data Column Count
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ArrayList
	 * @return ArrayList
	 * @throws Exception
	 * @author VV49326
	 **************************************************************************/
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
	
	//Added For CR-124 Starts here	
	public ActionForward check1058FileExist(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into SearchRequest1058Action:check1058FileExist");
		String strForwardKey=ApplicationConstants.LEGACY1058REQUESTS;
		HttpSession objSession = objHttpServletRequest.getSession(false);
						
		try {
			
			LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
						
			SearchRequest1058Form objSearchRequest1058Form = (SearchRequest1058Form)objActionForm;
						
			if ((String) objHttpServletRequest.getParameter("fileName") != null) {
				
				objHttpServletRequest.setAttribute("file",(String) objHttpServletRequest.getParameter("fileName"));
				}
			LogUtil.logMessage((String) objHttpServletRequest.getParameter("fileName"));
			
			FileVO objFileVO = new FileVO();
			objFileVO.setFileName(objHttpServletRequest.getParameter("fileName"));
		
			//Get the servers upload directory real path name
			String filePath = ApplicationConstants.LEGACY_FILE_LOC_PATH;
			
		    //create the upload folder if not exists
		    File folder = new File(filePath);
		    if(!folder.exists()){
		    	folder.mkdir();
		    }
		    
		    String fileName = objFileVO.getFileName();
	    	LogUtil.logMessage("objFileVO.getFileName():   "+objFileVO.getFileName());
	    	LogUtil.logMessage("Server path:" +filePath);
	    	
	    	//Added for CR-120 for Upload LeagcyReport Filename Fix
	    	String strSpecialChrArray[] = {" ","#","^","`","%"};
	    	
	    	for(int nSpchk=0;nSpchk < strSpecialChrArray.length ; nSpchk++)
	    	{
		    	String strFind = strSpecialChrArray[nSpchk];
		    	String strReplace = "_"; 
		    	String strResult = ""; 
		    	int i; 
			    	do { // replace all matching substrings
			    		i = fileName.indexOf(strFind); 
				    	if(i != -1) { 
				    	strResult = fileName.substring(0, i); 
				    	strResult = strResult + strReplace; 
				    	strResult = strResult + fileName.substring(i + strFind.length()); 
				    	fileName  = strResult; 
				    	} 
			    	} while(i != -1);
			    	{
			    	}
	    	}
	     	LogUtil.logMessage("fileName:" +fileName);
	     	//Added for CR-120 for Upload LeagcyReport Filename Fix Ends Here
	     	
	    	String strServerPathwithFilename = ApplicationConstants.LEGACY_FILE_SERVER_PATH + fileName;
		    LogUtil.logMessage("strServerPathwithFilename"+strServerPathwithFilename);
		    		
		    File newFile = new File(filePath, fileName);
			LogUtil.logMessage("newFile.exists():"+newFile.exists());
			boolean blnChkFileExist;
			blnChkFileExist = newFile.exists();
			LogUtil.logMessage("blnChkFileExist:"+blnChkFileExist);
			objSearchRequest1058Form.setFileExist(blnChkFileExist);
			
			if(blnChkFileExist)
			{
				LogUtil.logMessage("Inside if ");
				String strFileExists = new JSONObject().put("ChkFileExist",objSearchRequest1058Form.isFileExist()).toString();
				LogUtil.logMessage("strFileExists :"+ strFileExists);
				objHttpServletResponse.setContentType("text/javascript");
			    objHttpServletResponse.getWriter().write(strFileExists);
			}
			else{
				LogUtil.logMessage("Inside else ");
				String strFileExists = new JSONObject().put("ChkFileExist",objSearchRequest1058Form.isFileExist()).toString();
				LogUtil.logMessage("strFileExists :"+ strFileExists);
				objHttpServletResponse.setContentType("text/javascript");
			    objHttpServletResponse.getWriter().write(strFileExists);
			}
			
		}catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in SearchRequest1058Action..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
		}
		return null;
		}
	//Added for CR_124 Ends here
	
	//Added For CR-117
	public ActionForward uploadLegacyReport(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into SearchRequest1058Action:uploadLegacyReport");
		String strAttachment=null;
		String strForwardKey=ApplicationConstants.LEGACY1058REQUESTS;
		boolean blnFlag = true;
		ArrayList arlAttachmentReturned =new ArrayList();
		ChangeRequest1058AttachmentsVO objChangeRequest1058AttachmentsVO=null;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		int intUploadLegacyReport = 0;
				
		try {
			
			LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			
			
			SearchRequest1058Form objSearchRequest1058Form = (SearchRequest1058Form)objActionForm;
			ChangeRequest1058VO objChangeRequest1058VO =new ChangeRequest1058VO();
			objChangeRequest1058VO.setUserID(objLoginVo.getUserID());
			//LogUtil.logMessage("File in the form total "+objChangeRequest1058Form.());
			objChangeRequest1058VO.setOrderNo(ApplicationUtil.trim(objSearchRequest1058Form
					.getOrderNo()));
			objChangeRequest1058VO.setNumber1058(ApplicationUtil.trim(objSearchRequest1058Form
					.getNumber1058()));
			objChangeRequest1058VO.setCustomer(ApplicationUtil.trim(objSearchRequest1058Form
					.getCustomer()));
			objChangeRequest1058VO.setModel(ApplicationUtil.trim(objSearchRequest1058Form
					.getModel()));
			objChangeRequest1058VO.setStatusSeqNo(Integer.parseInt(objSearchRequest1058Form
					.getStatus()));
			objChangeRequest1058VO.setSpecRev(ApplicationUtil.trim(objSearchRequest1058Form
					.getSpecRev()));
			//Added for CR-120
			if(objSearchRequest1058Form.getIssuedBy() == null 
			|| objSearchRequest1058Form.getIssuedBy().equalsIgnoreCase("-1")
			|| objSearchRequest1058Form.getIssuedBy().equalsIgnoreCase("")){
				objChangeRequest1058VO.setIssuedBy(objLoginVo.getUserID());	
			}else{
				objChangeRequest1058VO.setIssuedBy(ApplicationUtil.trim(objSearchRequest1058Form
					.getIssuedBy()));
			}
			objChangeRequest1058VO.setGenDesc(ApplicationUtil.trim(objSearchRequest1058Form
					.getGenDesc()));
			if(objSearchRequest1058Form.getActualSellPrice().equalsIgnoreCase("") || 
			   objSearchRequest1058Form.getActualSellPrice() == null)
			{
				objChangeRequest1058VO.setActualSellPrice(new BigDecimal("0.00"));
			}else{
				objChangeRequest1058VO.setActualSellPrice(new BigDecimal(objSearchRequest1058Form.getActualSellPrice()));
			}
			
			//Added for CR_118
			
			objChangeRequest1058VO.setDesignHrs(objSearchRequest1058Form.getDesignHrs());
			objChangeRequest1058VO.setDraftingHrs(objSearchRequest1058Form.getDraftingHrs());
			
			if(objSearchRequest1058Form.getWorkOrderUSD().equalsIgnoreCase("") || 
					objSearchRequest1058Form.getWorkOrderUSD() == null)
					{
						objChangeRequest1058VO.setWorkOrderUSD(new BigDecimal("0.00"));
					}else{
						objChangeRequest1058VO.setWorkOrderUSD(new BigDecimal(objSearchRequest1058Form.getWorkOrderUSD()));
					}
			//Added for CR_118 Ends
			
			//Added for CR-120 ends here	
			
			LogUtil.logMessage("File in the form "+objSearchRequest1058Form.getUploadAttachment());
			
			FormFile objFormFile =objSearchRequest1058Form.getUploadAttachment();
			FileVO objFileVO = new FileVO();
			
			objFileVO.setFileName(objFormFile.getFileName());
			objFileVO.setuploadedFile(objFormFile.getFileData());
			objFileVO.setFileLength(objFormFile.getFileSize());
			
			objFileVO.setFileStream(objFormFile.getInputStream());
			objFileVO.setContentType(objFormFile.getContentType());
			
			LogUtil.logMessage("objFileVO.getFileName():   "+objFileVO.getFileName());
			LogUtil.logMessage("objFileVO.getFileLength():   "+objFileVO.getFileLength());
			LogUtil.logMessage("objFileVO.getContentType():   "+objFileVO.getContentType());
			ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
			
			//Get the servers upload directory real path name
			String filePath = ApplicationConstants.LEGACY_FILE_LOC_PATH;
			
		    //create the upload folder if not exists
		    File folder = new File(filePath);
		    if(!folder.exists()){
		    	folder.mkdir();
		    }
		    
	    	String fileName = objFileVO.getFileName();
	    	LogUtil.logMessage("Server path:" +filePath);
	    	
	    	//Added for CR-120 for Upload LeagcyReport Filename Fix
	    	String strSpecialChrArray[] = {" ","#","^","`","%"};
	    	
	    	for(int nSpchk=0;nSpchk < strSpecialChrArray.length ; nSpchk++)
	    	{
		    	String strFind = strSpecialChrArray[nSpchk];
		    	String strReplace = "_"; 
		    	String strResult = ""; 
		    	int i; 
			    	do { // replace all matching substrings
			    		i = fileName.indexOf(strFind); 
				    	if(i != -1) { 
				    	strResult = fileName.substring(0, i); 
				    	strResult = strResult + strReplace; 
				    	strResult = strResult + fileName.substring(i + strFind.length()); 
				    	fileName  = strResult; 
				    	} 
			    	} while(i != -1);
			    	{
			    	}
	    	}
	     	LogUtil.logMessage("fileName:" +fileName);
	     	//Added for CR-120 for Upload LeagcyReport Filename Fix Ends Here
	     	
	     	String strServerPathwithFilename = ApplicationConstants.LEGACY_FILE_SERVER_PATH + fileName;
		    LogUtil.logMessage("strServerPathwithFilename"+strServerPathwithFilename);
		    objChangeRequest1058VO.setUploadAttachment(strServerPathwithFilename);
		    
		    LogUtil.logMessage("Entering Search1058Requestaction:initload: fetching Status list");			
			ArrayList arlStatusList = new ArrayList();	
			objChangeRequest1058VO.setUserID(objLoginVo.getUserID());			
			arlStatusList = objChangeRequest1058BI.fetch1058Status(objChangeRequest1058VO);
			LogUtil.logMessage("" + arlStatusList.size());
			objSearchRequest1058Form.setStatusList(arlStatusList);
			//Added For CR-120
			ArrayList arlIssuedByUserList = new ArrayList();
			UserVO objUserVO = new UserVO();
			objUserVO.setUserID(objLoginVo.getUserID());
			objUserVO.setOrderbyFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
			arlIssuedByUserList = objChangeRequest1058BI.fetchIssuedByUserList(objUserVO);
			objSearchRequest1058Form.setIssuedByUserList(arlIssuedByUserList);
			
		    File newFile = new File(filePath, fileName);
			LogUtil.logMessage("newFile.exists():"+newFile.exists());
			boolean blnChkFileExist;
			blnChkFileExist = newFile.exists();
			LogUtil.logMessage("blnChkFileExist:"+blnChkFileExist);
			objSearchRequest1058Form.setFileExist(blnChkFileExist);
			
				if (objFileVO.getFileLength() > 1024*1024*10)	{
					LogUtil.logMessage("Size is big");
					objSearchRequest1058Form.setMessageID(ApplicationConstants.FILESIZELARGE);
					objSearchRequest1058Form.setOrderNo(objSearchRequest1058Form.getOrderNo());
					objSearchRequest1058Form.setNumber1058(objSearchRequest1058Form.getNumber1058());
					objSearchRequest1058Form.setCustomer(objSearchRequest1058Form.getCustomer());
					objSearchRequest1058Form.setModel(objSearchRequest1058Form.getModel());
					objSearchRequest1058Form.setStatusSeqNo(Integer.parseInt(objSearchRequest1058Form.getStatus()));
					LogUtil.logMessage("Entering Search1058Requestaction:initload: fetching Status list");			
					objChangeRequest1058VO.setUserID(objLoginVo.getUserID());			
					arlStatusList = objChangeRequest1058BI.fetch1058Status(objChangeRequest1058VO);
					LogUtil.logMessage("" + arlStatusList.size());
					objSearchRequest1058Form.setStatusList(arlStatusList);
					objSearchRequest1058Form.setSpecRev(objSearchRequest1058Form.getSpecRev());
					objSearchRequest1058Form.setIssuedBy(objSearchRequest1058Form.getIssuedBy());
			  }//Commented for CR_124
				/*else if(blnChkFileExist){
					objSearchRequest1058Form.setMessageID(ApplicationConstants.FILEEXISTS);	
					objSearchRequest1058Form.setOrderNo(objSearchRequest1058Form.getOrderNo());
					objSearchRequest1058Form.setNumber1058(objSearchRequest1058Form.getNumber1058());
					objSearchRequest1058Form.setCustomer(objSearchRequest1058Form.getCustomer());
					objSearchRequest1058Form.setModel(objSearchRequest1058Form.getModel());
					objSearchRequest1058Form.setStatusSeqNo(Integer.parseInt(objSearchRequest1058Form.getStatus()));
					LogUtil.logMessage("Entering Search1058Requestaction:initload: fetching Status list");			
					objChangeRequest1058VO.setUserID(objLoginVo.getUserID());			
					arlStatusList = objChangeRequest1058BI.fetch1058Status(objChangeRequest1058VO);
					LogUtil.logMessage("" + arlStatusList.size());
					objSearchRequest1058Form.setStatusList(arlStatusList);
					objSearchRequest1058Form.setSpecRev(objSearchRequest1058Form.getSpecRev());
					objSearchRequest1058Form.setIssuedBy(objSearchRequest1058Form.getIssuedBy());
			 }*/else{	
				    intUploadLegacyReport = objChangeRequest1058BI.uploadLegacyReport(objChangeRequest1058VO);
				
					if (intUploadLegacyReport == 0) {
							//File write
							
								//if(!newFile.exists()){
							          FileOutputStream fos = new FileOutputStream(newFile);
							          fos.write(objFormFile.getFileData());
							          fos.flush();
							          fos.close();
							          LogUtil.logMessage("file wrote successfully");	
							        //} 
								
								objSearchRequest1058Form
								.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
								objSearchRequest1058Form.setOrderNo("");
								objSearchRequest1058Form.setNumber1058("");
								objSearchRequest1058Form.setCustomer("");
								objSearchRequest1058Form.setModel("");
								objSearchRequest1058Form.setSpecRev("");
								objSearchRequest1058Form.setHdnStatus(0);
								objSearchRequest1058Form.setHdnIssuedBy("0");
								objSearchRequest1058Form.setGenDesc("");
								objSearchRequest1058Form.setActualSellPrice("");
							//objSearchRequest1058Form.setUploadAttachment(0);
						
					}else{
						objSearchRequest1058Form.setMessageID("" + intUploadLegacyReport);
						objSearchRequest1058Form.setOrderNo(objSearchRequest1058Form.getOrderNo());
						objSearchRequest1058Form.setNumber1058(objSearchRequest1058Form.getNumber1058());
						objSearchRequest1058Form.setCustomer(objSearchRequest1058Form.getCustomer());
						objSearchRequest1058Form.setModel(objSearchRequest1058Form.getModel());
						objSearchRequest1058Form.setSpecRev(objSearchRequest1058Form.getSpecRev());
						objSearchRequest1058Form.setIssuedBy(objSearchRequest1058Form.getIssuedBy());
						objSearchRequest1058Form.setGenDesc(objSearchRequest1058Form.getGenDesc());
						objSearchRequest1058Form.setActualSellPrice(objSearchRequest1058Form.getActualSellPrice());
						objSearchRequest1058Form.setStatusSeqNo(Integer.parseInt(objSearchRequest1058Form.getStatus()));
						objSearchRequest1058Form.setUploadAttachment(objSearchRequest1058Form.getUploadAttachment());
					}
			 }
		
		
		}catch(OutOfMemoryError oom){
			LogUtil.logMessage("Size is big Access Denied");
			objHttpServletResponse.setContentType("text/plain");
			try{
				objHttpServletResponse.getWriter().write(ApplicationConstants.FILESIZETOOLARGE);
				objHttpServletResponse.flushBuffer();
			}
			catch(IOException ioe){
				LogUtil
				.logMessage("Enters into Exception Block in SearchRequest1058Action..");
				ErrorInfo objErrorInfo = new ErrorInfo();
				String strError = ioe.getMessage();
				objErrorInfo.setMessage(strError);
				LogUtil.logMessage("Error Message:" + strError);
				LogUtil.logError(ioe, objErrorInfo);
				strForwardKey = ApplicationConstants.FAILURE;
			}
		}catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in SearchRequest1058Action..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
		}
		return objActionMapping.findForward(strForwardKey);
		}
	/***************************************************************************
	 * This method is for delete 1058 request(s)
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws BusinessException
	 * @author VV49326
	 **************************************************************************/
	public ActionForward delete1058Request(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		LogUtil.logMessage("Inside the SearchRequest1058Action:delete1058Request");
		String strForwardKey = ApplicationConstants.SUCCESS;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		int intDelete1058Request = 0;
		ChangeRequest1058VO objChangeRequest1058VO = new ChangeRequest1058VO();
		SearchRequest1058Form objSearchRequest1058Form = (SearchRequest1058Form)objActionForm;
		try {
			
			UserVO objUserVO = new UserVO();
			objUserVO.setUserID(objLoginVo.getUserID());
			
			objChangeRequest1058VO.setUserID(objLoginVo.getUserID());
			objChangeRequest1058VO.setSeqNo(objHttpServletRequest.getParameterValues("chk1058SeqNo"));
			ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
			intDelete1058Request = objChangeRequest1058BI.delete1058Request(objChangeRequest1058VO);
			
			if (intDelete1058Request == 0) {
				objSearchRequest1058Form
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			}else{
				objSearchRequest1058Form.setMessageID("" + intDelete1058Request);
			}
			
			//this.fetchDetails(objActionMapping,objActionForm,objHttpServletRequest,objHttpServletResponse);
			ArrayList arlRequestList = new ArrayList();			
			LogUtil.logMessage("Entering Search1058Requestaction:fetchDetails:fetching customer list");			
			CustomerVO objCustomerVO=new CustomerVO();
			ArrayList arlCustomerList = new ArrayList();
			objCustomerVO.setUserID(objLoginVo.getUserID());			
			CustomerBI objCustomerBI = ServiceFactory.getCustomerBO();
			arlCustomerList = objCustomerBI.fetchCustomers(objCustomerVO);			
			LogUtil.logMessage("" + arlCustomerList.size());
			
			objSearchRequest1058Form.setCustNameList(arlCustomerList);
			
			LogUtil.logMessage("Entering Search1058Requestaction:fetchDetails: fetching user list");
			ArrayList arlUserList = new ArrayList();
			//Added for CR-117 for Sorting Lastname in Awaiting action on Drop Down
			objUserVO.setOrderbyFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
			objUserVO.setUserID(objLoginVo.getUserID());			
			UserMaintBI objUserBI = ServiceFactory.getUserMaintBO();
			arlUserList = objUserBI.fetchUsers(objUserVO);
			LogUtil.logMessage("" + arlUserList.size());
			objSearchRequest1058Form.setUserList(arlUserList);
			
			LogUtil.logMessage("Entering Search1058Requestaction:fetchDetails: fetching Model list");
			ModelVo objModelVO=new ModelVo();
			ArrayList arlModelList = new ArrayList();
			objModelVO.setUserID(objLoginVo.getUserID());			
			ModelBI objModelBI = ServiceFactory.getModelBO();
			arlModelList = objModelBI.fetchModels(objModelVO);
			LogUtil.logMessage("" + arlModelList.size());
			objSearchRequest1058Form.setMdlNameList(arlModelList);
			
			LogUtil.logMessage("Entering Search1058Requestaction:fetchDetails: fetching Status list");			
			objChangeRequest1058VO=new ChangeRequest1058VO();
			ArrayList arlStatusList = new ArrayList();	
			objChangeRequest1058VO.setUserID(objLoginVo.getUserID());			
			arlStatusList = objChangeRequest1058BI.fetch1058Status(objChangeRequest1058VO);
			LogUtil.logMessage("" + arlStatusList.size());
			
			objSearchRequest1058Form.setStatusList(arlStatusList);
						
			objChangeRequest1058VO.setCustSeqNo(objSearchRequest1058Form.getCustSeqNo());
			objChangeRequest1058VO.setModelSeqNos(objSearchRequest1058Form.getModelSeqNos());
			objChangeRequest1058VO.setStatusSeqNo(objSearchRequest1058Form.getStatusSeqNo());
			objChangeRequest1058VO.setAwApproval(objSearchRequest1058Form.getAwApproval());
			objChangeRequest1058VO.setOrderNo(objSearchRequest1058Form.getOrderNo());
			arlRequestList = objChangeRequest1058BI.fetch1058Details(objChangeRequest1058VO);	
			LogUtil.logMessage("arlRequestList.size():"+arlRequestList.size());
			LogUtil.logMessage("objSearchRequest1058Form.getStatusSeqNo:"+objSearchRequest1058Form.getStatusSeqNo());
			objSearchRequest1058Form.setRequestList(arlRequestList);	
			
			
		}catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in SearchRequest1058Action..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
		}
		return objActionMapping.findForward(strForwardKey);
	}
	
	//Added For CR-117 ends Here
	
	//Added for CR-118
	/***************************************************************************
	 * This method is for View Seleceted Summary Report for 1058
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws BusinessException
	 * @author VV49326
	 **************************************************************************/
	public ActionForward viewSelecetedSummaryReport(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		LogUtil.logMessage("Inside the SearchRequest1058Action:viewSelecetedSummaryReport");
		String strForwardKey = ApplicationConstants.VIEW_SELECTED_SUMMARY_REPORT;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		
		ChangeRequest1058VO objChangeRequest1058VO = new ChangeRequest1058VO();
		SearchRequest1058Form objSearchRequest1058Form = (SearchRequest1058Form)objActionForm;
		try {
			ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
			/*String seqNos = null;
			seqNos = objHttpServletRequest.getParameter("seqnos");
			seqNo = seqNos.split(ApplicationConstants.COMMA);
			*/
			String[] seqNo = null;
			ArrayList arlRequestList = new ArrayList();			
			seqNo = objHttpServletRequest.getParameterValues("chk1058SeqNo");
			objChangeRequest1058VO.setUserID(objLoginVo.getUserID());
			
			if (seqNo.length > 0) {
			
				for (int i = 0; i < seqNo.length; i++) {
					
					LogUtil.logMessage("SeqNo" + seqNo[i]);
					
					if (seqNo[i] != null && (!"".equals(seqNo[i]))) {
						
						objChangeRequest1058VO.setSeqNo1058(Integer.parseInt(seqNo[i]));
						arlRequestList.add(objChangeRequest1058BI.fetch1058Details(objChangeRequest1058VO));
					}
				}		
			}	
			LogUtil.logMessage("arlRequestList.size():"+arlRequestList.size());
			LogUtil.logMessage("objSearchRequest1058Form.getStatusSeqNo:"+objSearchRequest1058Form.getStatusSeqNo());
			objSearchRequest1058Form.setRequestList(arlRequestList);
			LogUtil.logMessage("objSearchRequest1058Form.getRequestList().size():"+objSearchRequest1058Form.getRequestList().size());
			
			
		}catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in SearchRequest1058Action..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
		}
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***************************************************************************
	 * This method is for Export Seleceted Summary Report for 1058
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws BusinessException
	 * @author VV49326
	 **************************************************************************/
	public ActionForward exportSummaryReport(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		LogUtil.logMessage("Inside the SearchRequest1058Action:exportSummaryReport");
		String strForwardKey = ApplicationConstants.SUCCESS;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		int rowCount=0;
		int intPos=0;
		String strGenDesc= null;
		
		ChangeRequest1058VO objChangeRequest1058VO = new ChangeRequest1058VO();
		try {
			
//			create workbook
			HSSFWorkbook workBook = new HSSFWorkbook();
			HSSFSheet sheet = workBook.createSheet("1058Summary_Report");//create sheet
			
			HSSFCellStyle cellStyle = workBook.createCellStyle();										
			
			HSSFFont Font = workBook.createFont();			
			Font.setFontName(HSSFFont.FONT_ARIAL);
			Font.setColor(HSSFColor.BLACK.index);		
			Font.setFontHeightInPoints((short) 10);
			
			cellStyle.setFont(Font);
			cellStyle.setWrapText(true);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			HSSFCellStyle cellGenDescStyle = workBook.createCellStyle();
			
			cellGenDescStyle.setFont(Font);
			cellGenDescStyle.setWrapText(true);
			cellGenDescStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
			ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
			
			String[] seqNo = null;
			ArrayList arlCommonList = new ArrayList();
			ArrayList arlRequestList = new ArrayList();			
			seqNo = objHttpServletRequest.getParameterValues("chk1058SeqNo");
			objChangeRequest1058VO.setUserID(objLoginVo.getUserID());
			
			if (seqNo.length > 0) {
			
				for (int i = 0; i < seqNo.length; i++) {
					
					LogUtil.logMessage("SeqNo" + seqNo[i]);
					
					if (seqNo[i] != null && (!"".equals(seqNo[i]))) {
						
						objChangeRequest1058VO.setSeqNo1058(Integer.parseInt(seqNo[i]));
						arlCommonList.add(objChangeRequest1058BI.fetch1058Details(objChangeRequest1058VO));
					}
				}		
			}	
			//LogUtil.logMessage("arlCommonList.size():"+arlCommonList.size());
			Iterator listCommonList = null;
			Iterator listRequestList = null;
			HSSFRow row = sheet.createRow(rowCount);
			if (arlCommonList.size() != 0) {
				
				createHeadingForClauses(workBook,row,sheet);
				listCommonList = arlCommonList.iterator();
				
				while (listCommonList.hasNext()) {
					 
					arlRequestList = new ArrayList();
					arlRequestList = (ArrayList)listCommonList.next();
					//LogUtil.logMessage("arlRequestList.size():"+arlRequestList.size());
					listRequestList = arlRequestList.iterator();
					
					while (listRequestList.hasNext()) {
						
						objChangeRequest1058VO = new ChangeRequest1058VO();
						objChangeRequest1058VO = (ChangeRequest1058VO) listRequestList.next();
						rowCount++;
						intPos=0;
						row = sheet.createRow(rowCount);
						
						//LogUtil.logMessage("objChangeRequest1058VO.getOrderNo():" + objChangeRequest1058VO.getOrderNo());
						//LogUtil.logMessage("objChangeRequest1058VO.getId1058():" + objChangeRequest1058VO.getId1058());
						
						createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, 0,new HSSFRichTextString(objChangeRequest1058VO.getOrderNo()));//0th column
						createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, 1,new HSSFRichTextString(objChangeRequest1058VO.getId1058()));//1st column
						if(objChangeRequest1058VO.getCustName() == null){
							createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, 2,new HSSFRichTextString(""));
						}else{
							createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, 2,new HSSFRichTextString(objChangeRequest1058VO.getCustName()));//2nd column
						}
						//LogUtil.logMessage("objChangeRequest1058VO.getCustName():" + objChangeRequest1058VO.getCustName());
						if(objChangeRequest1058VO.getStatusDesc() == null){
							createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, 3,new HSSFRichTextString(""));
						}else{
							createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, 3,new HSSFRichTextString(objChangeRequest1058VO.getStatusDesc()));//3rd column
						}
						//LogUtil.logMessage("objChangeRequest1058VO.getStatusDesc():" + objChangeRequest1058VO.getStatusDesc());
						if(objChangeRequest1058VO.getSpecRev() == null) {
							createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, 4,new HSSFRichTextString(""));
						}else{
							createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, 4,new HSSFRichTextString(objChangeRequest1058VO.getSpecRev()));//4th column
						}
						//LogUtil.logMessage("objChangeRequest1058VO.getSpecRev():" + objChangeRequest1058VO.getSpecRev());
						if(objChangeRequest1058VO.getMdlName() == null){
							createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, 5,new HSSFRichTextString(""));
						}else{
							createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, 5,new HSSFRichTextString(objChangeRequest1058VO.getMdlName()));//5th column
						}
						//LogUtil.logMessage("objChangeRequest1058VO.getMdlName():" + objChangeRequest1058VO.getMdlName());
						
						if(objChangeRequest1058VO.getGenDesc()!=null && objChangeRequest1058VO.getGenDesc()!=""){
							strGenDesc = getRefinedGenDesc(objChangeRequest1058VO.getGenDesc());
						}else{
							if(objChangeRequest1058VO.getGenDesc() == null){
								strGenDesc = "";
							}else{
								strGenDesc = objChangeRequest1058VO.getGenDesc();
							}
						}
						strGenDesc = strGenDesc.trim();
						//LogUtil.logMessage("objChangeRequest1058VO.getGenDesc()" + objChangeRequest1058VO.getGenDesc());
						HSSFRichTextString richTextGenDesc = new HSSFRichTextString(strGenDesc); 
						createGenDescCell(row,cellGenDescStyle ,HSSFCell.CELL_TYPE_STRING, 6,richTextGenDesc);//6th column
						
						if(objChangeRequest1058VO.getDesignHrs() == null){
							createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, 7,new HSSFRichTextString(""));
						}else{
							createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, 7,new HSSFRichTextString(objChangeRequest1058VO.getDesignHrs()));//7th column
						}
						//LogUtil.logMessage("objChangeRequest1058VO.getDesignHrs()" + objChangeRequest1058VO.getGenDesc());
						
						if(objChangeRequest1058VO.getDraftingHrs() == null){
							createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, 8,new HSSFRichTextString(""));
						}else{
							createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, 8,new HSSFRichTextString(objChangeRequest1058VO.getDraftingHrs()));//8th column	
						}
						//LogUtil.logMessage("objChangeRequest1058VO.getDraftingHrs()" + objChangeRequest1058VO.getDraftingHrs());
						
						if(objChangeRequest1058VO.getWorkOrderUSD() == null){
							createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, 9,new HSSFRichTextString(""));
						}else{
							bdWorkOrderUSD = objChangeRequest1058VO.getWorkOrderUSD();
							bdWorkOrderUSD = bdWorkOrderUSD.setScale(2, BigDecimal.ROUND_HALF_UP);
							
							String strChkWorkOrderMinus = bdWorkOrderUSD.toString();
							LogUtil.logMessage("Before  strChkWorkOrderMinus :" + strChkWorkOrderMinus);
							strChkWorkOrderMinus = strChkWorkOrderMinus.substring(0,1);
							LogUtil.logMessage("After  strChkWorkOrderMinus :" + strChkWorkOrderMinus);
							if(strChkWorkOrderMinus.equalsIgnoreCase("-")){
								createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, 9,new HSSFRichTextString(
										strChkWorkOrderMinus + strDollar + bdWorkOrderUSD.toString().trim()
										.substring(1,bdWorkOrderUSD.toString().length())));//9th column
							}else{
								createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, 9,
										new HSSFRichTextString(strDollar + bdWorkOrderUSD.toString().trim()));//9th column
							}
						}
						//LogUtil.logMessage("objChangeRequest1058VO.getWorkOrderUSD()" + objChangeRequest1058VO.getWorkOrderUSD());
		
					}
				}
			}	
				objHttpServletResponse.setContentType("application/vnd.ms-excel");
				objHttpServletResponse.setHeader("Content-disposition", "attachment; filename=1058Summary_Report.xls");
				OutputStream out = null;
				out = objHttpServletResponse.getOutputStream();
				
				workBook.write(out);
			
			
		}catch (Exception objEx) {
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
			
			final String[] columnHeadings = { "Order Number","1058 Number","Customer Name","Status","Specification Revision"
					 ,"Model","General Description" ,"Est.Design Hrs","Est.Drafting Hrs","Work Order(USD)"};
			
			final int[] columnWidth = {5500, 5500 , 6000, 5500
										,3500,5500 ,
										11500,4000,4000
										,4000 };
			for (int intPos=0;intPos < 10; intPos++)
				{
					 createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,intPos,new HSSFRichTextString(columnHeadings[intPos]));
					 sheet.setColumnWidth(intPos,columnWidth[intPos]);
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
		 * This method is used to get the General Description using HTML Parser
		 * 
		 * @author Mahindra Satyam Ltd
		 * @version 1.0
		 * @param String
		 * @return String
		 * @throws Exception
		 **************************************************************************/
		
		public String getRefinedGenDesc(String strGenDesc) throws Exception
		{
			String strRefinedGenDesc = "";
			Paragraph paraGenDesc = new Paragraph();
		    paraGenDesc.setKeepTogether(true);
			if (!strGenDesc.startsWith("<p>"))
				strGenDesc = strGenDesc.replaceAll("\n","<br/>");
			strRefinedGenDesc=ApplicationUtil.strConvertToHTMLFormat(strGenDesc);
			StyleSheet styles = new StyleSheet();
		    styles.loadTagStyle("p","size","12px");
		    styles.loadTagStyle("p","face","Times");
		    styles.loadTagStyle("strong","font-weight", "bold"); 
		    styles.loadTagStyle("em","font-style", "italic");
			ArrayList p = HTMLWorker.parseToList(new FileReader(strRefinedGenDesc), styles);
		    for (int k1 = 0; k1 < p.size(); ++k1) 
				{
		    	paraGenDesc.add((Element) p.get(k1));
			    }
		    strRefinedGenDesc = paraGenDesc.getContent();
			LogUtil.logMessage("returning from strhtmlclauseconvert :");
			return strRefinedGenDesc;
		}
		
		/***************************************************************************
		 * This method is used to create the cell with String value
		 * 
		 * @author Mahindra Satyam Ltd
		 * @version 1.0
		 * @param HSSFSheet, HSSFRow, HSSFCellStyle, int, int, String
		 * @return HSSFCell
		 **************************************************************************/
		
		protected HSSFCell createGenDescCell(HSSFRow row, HSSFCellStyle headerStyle,
				int cellType, int colPosition, HSSFRichTextString cellValue) {
			//This function is used to create a cell with String value
			HSSFCell cell = null;
			cell = row.createCell(colPosition);
			cell.setCellStyle(headerStyle);
			cell.setCellType(cellType);
			cell.setCellValue(cellValue);
			return cell;
		}
		
	//Added for CR-118 ends Here
		
	//Added for CR-126
		public ActionForward updateLegacyReport(ActionMapping objActionMapping,
				ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
				HttpServletResponse objHttpServletResponse) throws EMDException {
			
			LogUtil.logMessage("Enters into SearchRequest1058Action:updateLegacyReport");
			String strAttachment=null;
			String strForwardKey=ApplicationConstants.LEGACY1058REQUESTS;
			boolean blnFlag = true;
			ArrayList arlAttachmentReturned =new ArrayList();
			ChangeRequest1058AttachmentsVO objChangeRequest1058AttachmentsVO=null;
			HttpSession objSession = objHttpServletRequest.getSession(false);
			int intUploadLegacyReport = 0;
					
			try {
				
				LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
				LogUtil.logMessage(objLoginVo);
				
				
				SearchRequest1058Form objSearchRequest1058Form = (SearchRequest1058Form)objActionForm;
				ChangeRequest1058VO objChangeRequest1058VO =new ChangeRequest1058VO();
				objChangeRequest1058VO.setSeqNo1058(Integer.parseInt(objHttpServletRequest.getParameter("1058SeqNo")));
				objChangeRequest1058VO.setUserID(objLoginVo.getUserID());
				objChangeRequest1058VO.setOrderNo(ApplicationUtil.trim(objSearchRequest1058Form
						.getOrderNo()));
				objChangeRequest1058VO.setNumber1058(ApplicationUtil.trim(objSearchRequest1058Form
						.getNumber1058()));
				objChangeRequest1058VO.setCustomer(ApplicationUtil.trim(objSearchRequest1058Form
						.getCustomer()));
				objChangeRequest1058VO.setModel(ApplicationUtil.trim(objSearchRequest1058Form
						.getModel()));
				objChangeRequest1058VO.setStatusSeqNo(Integer.parseInt(objSearchRequest1058Form
						.getStatus()));
				objChangeRequest1058VO.setSpecRev(ApplicationUtil.trim(objSearchRequest1058Form
						.getSpecRev()));
				
				if(objSearchRequest1058Form.getIssuedBy() == null 
				|| objSearchRequest1058Form.getIssuedBy().equalsIgnoreCase("-1")
				|| objSearchRequest1058Form.getIssuedBy().equalsIgnoreCase("")){
					objChangeRequest1058VO.setIssuedBy(objLoginVo.getUserID());	
				}else{
					objChangeRequest1058VO.setIssuedBy(ApplicationUtil.trim(objSearchRequest1058Form
						.getIssuedBy()));
				}
				objChangeRequest1058VO.setGenDesc(ApplicationUtil.trim(objSearchRequest1058Form
						.getGenDesc()));
				if(objSearchRequest1058Form.getActualSellPrice().equalsIgnoreCase("") || 
				   objSearchRequest1058Form.getActualSellPrice() == null)
				{
					objChangeRequest1058VO.setActualSellPrice(new BigDecimal("0.00"));
				}else{
					objChangeRequest1058VO.setActualSellPrice(new BigDecimal(objSearchRequest1058Form.getActualSellPrice()));
				}
				
				objChangeRequest1058VO.setDesignHrs(objSearchRequest1058Form.getDesignHrs());
				objChangeRequest1058VO.setDraftingHrs(objSearchRequest1058Form.getDraftingHrs());
				
				if(objSearchRequest1058Form.getWorkOrderUSD().equalsIgnoreCase("") || 
						objSearchRequest1058Form.getWorkOrderUSD() == null)
						{
							objChangeRequest1058VO.setWorkOrderUSD(new BigDecimal("0.00"));
						}else{
							objChangeRequest1058VO.setWorkOrderUSD(new BigDecimal(objSearchRequest1058Form.getWorkOrderUSD()));
						}
					
				
				LogUtil.logMessage("File in the form "+objSearchRequest1058Form.getUploadAttachment());
				LogUtil.logMessage("objSearchRequest1058Form.getUploadAttachment().getFileSize():"+objSearchRequest1058Form.getUploadAttachment().getFileSize());
				ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
				
				if(objSearchRequest1058Form.getUploadAttachment().getFileSize() > 0 ){
					
				
				FormFile objFormFile =objSearchRequest1058Form.getUploadAttachment();
				FileVO objFileVO = new FileVO();
				
				objFileVO.setFileName(objFormFile.getFileName());
				objFileVO.setuploadedFile(objFormFile.getFileData());
				objFileVO.setFileLength(objFormFile.getFileSize());
				
				objFileVO.setFileStream(objFormFile.getInputStream());
				objFileVO.setContentType(objFormFile.getContentType());
				
				LogUtil.logMessage("objFileVO.getFileName():   "+objFileVO.getFileName());
				LogUtil.logMessage("objFileVO.getFileLength():   "+objFileVO.getFileLength());
				LogUtil.logMessage("objFileVO.getContentType():   "+objFileVO.getContentType());
				
				
				//Get the servers upload directory real path name
				String filePath = ApplicationConstants.LEGACY_FILE_LOC_PATH;
				
			    //create the upload folder if not exists
			    File folder = new File(filePath);
			    if(!folder.exists()){
			    	folder.mkdir();
			    }
			    
		    	String fileName = objFileVO.getFileName();
		    	LogUtil.logMessage("Server path:" +filePath);
		    	
		    	//Added for CR-120 for Upload LeagcyReport Filename Fix
		    	String strSpecialChrArray[] = {" ","#","^","`","%"};
		    	
		    	for(int nSpchk=0;nSpchk < strSpecialChrArray.length ; nSpchk++)
		    	{
			    	String strFind = strSpecialChrArray[nSpchk];
			    	String strReplace = "_"; 
			    	String strResult = ""; 
			    	int i; 
				    	do { // replace all matching substrings
				    		i = fileName.indexOf(strFind); 
					    	if(i != -1) { 
					    	strResult = fileName.substring(0, i); 
					    	strResult = strResult + strReplace; 
					    	strResult = strResult + fileName.substring(i + strFind.length()); 
					    	fileName  = strResult; 
					    	} 
				    	} while(i != -1);
				    	{
				    	}
		    	}
		     	LogUtil.logMessage("fileName:" +fileName);
		     	//Added for CR-120 for Upload LeagcyReport Filename Fix Ends Here
		     	
		     	String strServerPathwithFilename = ApplicationConstants.LEGACY_FILE_SERVER_PATH + fileName;
			    LogUtil.logMessage("strServerPathwithFilename"+strServerPathwithFilename);
			    objChangeRequest1058VO.setUploadAttachment(strServerPathwithFilename);
			    
			    LogUtil.logMessage("Entering Search1058Requestaction:initload: fetching Status list");			
				ArrayList arlStatusList = new ArrayList();	
				objChangeRequest1058VO.setUserID(objLoginVo.getUserID());			
				arlStatusList = objChangeRequest1058BI.fetch1058Status(objChangeRequest1058VO);
				LogUtil.logMessage("" + arlStatusList.size());
				objSearchRequest1058Form.setStatusList(arlStatusList);
				//Added For CR-120
				ArrayList arlIssuedByUserList = new ArrayList();
				UserVO objUserVO = new UserVO();
				objUserVO.setUserID(objLoginVo.getUserID());
				objUserVO.setOrderbyFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
				arlIssuedByUserList = objChangeRequest1058BI.fetchIssuedByUserList(objUserVO);
				objSearchRequest1058Form.setIssuedByUserList(arlIssuedByUserList);
				
			    File newFile = new File(filePath, fileName);
				LogUtil.logMessage("newFile.exists():"+newFile.exists());
				boolean blnChkFileExist;
				blnChkFileExist = newFile.exists();
				LogUtil.logMessage("blnChkFileExist:"+blnChkFileExist);
				objSearchRequest1058Form.setFileExist(blnChkFileExist);
				
					if (objFileVO.getFileLength() > 1024*1024*10)	{
						LogUtil.logMessage("Size is big");
						objSearchRequest1058Form.setMessageID(ApplicationConstants.FILESIZELARGE);
						objSearchRequest1058Form.setOrderNo(objSearchRequest1058Form.getOrderNo());
						objSearchRequest1058Form.setNumber1058(objSearchRequest1058Form.getNumber1058());
						objSearchRequest1058Form.setCustomer(objSearchRequest1058Form.getCustomer());
						objSearchRequest1058Form.setModel(objSearchRequest1058Form.getModel());
						objSearchRequest1058Form.setStatusSeqNo(Integer.parseInt(objSearchRequest1058Form.getStatus()));
						LogUtil.logMessage("Entering Search1058Requestaction:initload: fetching Status list");			
						objChangeRequest1058VO.setUserID(objLoginVo.getUserID());			
						arlStatusList = objChangeRequest1058BI.fetch1058Status(objChangeRequest1058VO);
						LogUtil.logMessage("" + arlStatusList.size());
						objSearchRequest1058Form.setStatusList(arlStatusList);
						objSearchRequest1058Form.setSpecRev(objSearchRequest1058Form.getSpecRev());
						objSearchRequest1058Form.setIssuedBy(objSearchRequest1058Form.getIssuedBy());
				  }else{	
					    intUploadLegacyReport = objChangeRequest1058BI.updateLegacyReport(objChangeRequest1058VO);
					
						if (intUploadLegacyReport == 0) {
								
									FileOutputStream fos = new FileOutputStream(newFile);
								    fos.write(objFormFile.getFileData());
								    fos.flush();
								    fos.close();
								    LogUtil.logMessage("file wrote successfully");	
								 	
									objSearchRequest1058Form
									.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
								
									String str1058SeqNO = objHttpServletRequest.getParameter("1058SeqNo");
									ArrayList arlEditLegacyList = new ArrayList(); 
									objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
									objChangeRequest1058VO.setSeqNo1058(Integer.parseInt(str1058SeqNO));
									arlEditLegacyList = objChangeRequest1058BI.fetch1058Details(objChangeRequest1058VO);	
									LogUtil.logMessage("arlEditLegacyList.size():"+arlEditLegacyList.size());
									objSearchRequest1058Form.setEditLegacyList(arlEditLegacyList);
								
							
						}else{
							objSearchRequest1058Form.setMessageID("" + intUploadLegacyReport);
							String str1058SeqNO = objHttpServletRequest.getParameter("1058SeqNo");
							ArrayList arlEditLegacyList = new ArrayList(); 
							objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
							objChangeRequest1058VO.setSeqNo1058(Integer.parseInt(str1058SeqNO));
							arlEditLegacyList = objChangeRequest1058BI.fetch1058Details(objChangeRequest1058VO);	
							LogUtil.logMessage("arlEditLegacyList.size():"+arlEditLegacyList.size());
							objSearchRequest1058Form.setEditLegacyList(arlEditLegacyList);
						}
				 }
				}else{
					intUploadLegacyReport = objChangeRequest1058BI.updateLegacyReport(objChangeRequest1058VO);
					LogUtil.logMessage("intUploadLegacyReport:"+intUploadLegacyReport);
					if (intUploadLegacyReport == 0) {
								objSearchRequest1058Form.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
								
								ArrayList arlStatusList 	  = new ArrayList();
								objChangeRequest1058VO.setUserID(objLoginVo.getUserID());			
								arlStatusList = objChangeRequest1058BI.fetch1058Status(objChangeRequest1058VO);
								LogUtil.logMessage("" + arlStatusList.size());
								objSearchRequest1058Form.setStatusList(arlStatusList);
								
								ArrayList arlIssuedByUserList = new ArrayList();
								UserVO objUserVO = new UserVO();
								objUserVO.setUserID(objLoginVo.getUserID());
								objUserVO.setOrderbyFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
								arlIssuedByUserList = objChangeRequest1058BI.fetchIssuedByUserList(objUserVO);
								objSearchRequest1058Form.setIssuedByUserList(arlIssuedByUserList);
								LogUtil.logMessage("Before set objSearchRequest1058Form.getEditLegacyFlag()"+
										objSearchRequest1058Form.getEditLegacyFlag());
								objSearchRequest1058Form.setEditLegacyFlag("Y");
								LogUtil.logMessage("After set objSearchRequest1058Form.getEditLegacyFlag()"+
										objSearchRequest1058Form.getEditLegacyFlag());
								String str1058SeqNO = objHttpServletRequest.getParameter("1058SeqNo");
								ArrayList arlEditLegacyList = new ArrayList(); 
								objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
								objChangeRequest1058VO.setSeqNo1058(Integer.parseInt(str1058SeqNO));
								arlEditLegacyList = objChangeRequest1058BI.fetch1058Details(objChangeRequest1058VO);	
								LogUtil.logMessage("arlEditLegacyList.size():"+arlEditLegacyList.size());
								objSearchRequest1058Form.setEditLegacyList(arlEditLegacyList);
					}else{
						objSearchRequest1058Form.setMessageID("" + intUploadLegacyReport);
						ArrayList arlStatusList 	  = new ArrayList();
						objChangeRequest1058VO.setUserID(objLoginVo.getUserID());			
						arlStatusList = objChangeRequest1058BI.fetch1058Status(objChangeRequest1058VO);
						LogUtil.logMessage("" + arlStatusList.size());
						objSearchRequest1058Form.setStatusList(arlStatusList);
						
						ArrayList arlIssuedByUserList = new ArrayList();
						UserVO objUserVO = new UserVO();
						objUserVO.setUserID(objLoginVo.getUserID());
						objUserVO.setOrderbyFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
						arlIssuedByUserList = objChangeRequest1058BI.fetchIssuedByUserList(objUserVO);
						objSearchRequest1058Form.setIssuedByUserList(arlIssuedByUserList);
						LogUtil.logMessage("Else Before set objSearchRequest1058Form.getEditLegacyFlag()"+
								objSearchRequest1058Form.getEditLegacyFlag());
						objSearchRequest1058Form.setEditLegacyFlag("Y");
						LogUtil.logMessage("Else After set objSearchRequest1058Form.getEditLegacyFlag()"+
								objSearchRequest1058Form.getEditLegacyFlag());
						String str1058SeqNO = objHttpServletRequest.getParameter("1058SeqNo");
						ArrayList arlEditLegacyList = new ArrayList(); 
						objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
						objChangeRequest1058VO.setSeqNo1058(Integer.parseInt(str1058SeqNO));
						objChangeRequest1058VO.setOrderNo("");
						arlEditLegacyList = objChangeRequest1058BI.fetch1058Details(objChangeRequest1058VO);	
						LogUtil.logMessage("arlEditLegacyList.size():"+arlEditLegacyList.size());
						objSearchRequest1058Form.setEditLegacyList(arlEditLegacyList);
					}		
				}
			
			}catch(OutOfMemoryError oom){
				LogUtil.logMessage("Size is big Access Denied");
				objHttpServletResponse.setContentType("text/plain");
				try{
					objHttpServletResponse.getWriter().write(ApplicationConstants.FILESIZETOOLARGE);
					objHttpServletResponse.flushBuffer();
				}
				catch(IOException ioe){
					LogUtil
					.logMessage("Enters into Exception Block in SearchRequest1058Action..");
					ErrorInfo objErrorInfo = new ErrorInfo();
					String strError = ioe.getMessage();
					objErrorInfo.setMessage(strError);
					LogUtil.logMessage("Error Message:" + strError);
					LogUtil.logError(ioe, objErrorInfo);
					strForwardKey = ApplicationConstants.FAILURE;
				}
			}catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception Block in SearchRequest1058Action..");
				ErrorInfo objErrorInfo = new ErrorInfo();
				String strError = objExp.getMessage();
				objErrorInfo.setMessage(strError);
				LogUtil.logMessage("Error Message:" + strError);
				LogUtil.logError(objExp, objErrorInfo);
				strForwardKey = ApplicationConstants.FAILURE;
			}
			return objActionMapping.findForward(strForwardKey);
			}
		
		public ActionForward viewUnapproved1058withClausesApplied(
				ActionMapping objActionMapping, ActionForm objActionForm,
				HttpServletRequest objHttpServletRequest,
				HttpServletResponse objHttpServletResponse)
				throws ApplicationException {
			LogUtil
					.logMessage("Inside the SearchRequest1058Action:viewUnapproved1058withClausesApplied");
			String strForwardKey = ApplicationConstants.VIEW_UNAPPROVED_1058_WITH_CLAUSE_APPLIED;
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
					.getAttribute(ApplicationConstants.USER_IN_SESSION);

			ChangeRequest1058VO objChangeRequest1058VO = new ChangeRequest1058VO();
			SearchRequest1058Form objSearchRequest1058Form = (SearchRequest1058Form) objActionForm;
			try {
				ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory
						.getChangeRequest1058BO();
				ArrayList arlReportDetailsList = new ArrayList();
				objChangeRequest1058VO.setUserID(objLoginVo.getUserID());

				arlReportDetailsList=objChangeRequest1058BI.fetch1058ReportDetails(objChangeRequest1058VO);
				
				LogUtil.logMessage("arlReportDetailsList.size():"
								+ arlReportDetailsList.size());
				
				if (arlReportDetailsList != null && arlReportDetailsList.size() > 0) {
					objSearchRequest1058Form.setUnapproved1058ReportDetails(arlReportDetailsList);
					LogUtil.logMessage("ArrayListt" + objSearchRequest1058Form.getUnapproved1058ReportDetails());
				}
				else{
					objSearchRequest1058Form
					.setMessageID(ApplicationConstants.NO_RECORD_MESSAGE_ID);
				}

			} catch (Exception objExp) {
				LogUtil
						.logMessage("Enters into Exception Block in SearchRequest1058Action..");
				ErrorInfo objErrorInfo = new ErrorInfo();
				String strError = objExp.getMessage();
				objErrorInfo.setMessage(strError);
				LogUtil.logMessage("Error Message:" + strError);
				LogUtil.logError(objExp, objErrorInfo);
				strForwardKey = ApplicationConstants.FAILURE;
			}
			return objActionMapping.findForward(strForwardKey);
		}
		
		
		public ActionForward export1058ClauseAppliedtoSpec(ActionMapping objActionMapping,
				ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
				HttpServletResponse objHttpServletResponse)
				throws ApplicationException {
			LogUtil
			.logMessage("Inside the SearchRequest1058Action:Export1058ClauseAppliedtoSpec");
			String strForwardKey = ApplicationConstants.SUCCESS;
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
					.getAttribute(ApplicationConstants.USER_IN_SESSION);
			int rowCount = 1;
			int intPos = 0;
			int intcol=0;
			ChangeRequest1058VO objChangeRequest1058VO = new ChangeRequest1058VO();
			try {
				// create workbook
				HSSFWorkbook workBook = new HSSFWorkbook();
				HSSFSheet sheet = workBook.createSheet("Clauses applied to spec from unapproved 1058s");
						
				HSSFCellStyle cellHeadStyle = workBook.createCellStyle();
				HSSFFont headFont = workBook.createFont();
				
				headFont.setFontName(HSSFFont.FONT_ARIAL);
				headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				headFont.setColor(HSSFColor.BLACK.index);	
				headFont.setFontHeightInPoints((short) 10);
				
				cellHeadStyle.setWrapText(false);
				cellHeadStyle.setFont(headFont);
				cellHeadStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
				cellHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				
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
				
				
				
						ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory
						.getChangeRequest1058BO();
						
						ArrayList arlCommonList = new ArrayList();
						ArrayList arlReportList = new ArrayList();
						
						objChangeRequest1058VO.setUserID(objLoginVo.getUserID());

						arlCommonList.add(objChangeRequest1058BI.fetch1058ReportDetails(objChangeRequest1058VO));
						Iterator listCommonList = null;
						Iterator listRequestList = null;
						
						if (arlCommonList.size() != 0) {

							createHeadingForClausesAppliedToSpec(workBook, sheet);
							listCommonList = arlCommonList.iterator();
							
							while (listCommonList.hasNext()) {

								arlReportList = new ArrayList();
								arlReportList = (ArrayList) listCommonList.next();
								
								listRequestList = arlReportList.iterator();

								while (listRequestList.hasNext()) {

									objChangeRequest1058VO = new ChangeRequest1058VO();
									objChangeRequest1058VO = (ChangeRequest1058VO) listRequestList.next();
									rowCount++;
									intcol++;
									intPos = 0;
									String strFromEnggData = "";
									HSSFRow row = sheet.createRow(rowCount);
							
							
							createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, intPos+0,new HSSFRichTextString(objChangeRequest1058VO.getNumber1058()));
							createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, intPos+1,new HSSFRichTextString(objChangeRequest1058VO.getStatusDesc()));
							createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, intPos+2,new HSSFRichTextString(objChangeRequest1058VO.getSpecRev()));						
							createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, intPos+3,new HSSFRichTextString(objChangeRequest1058VO.getCustName()));
								if (objChangeRequest1058VO.getIssuedBy() == null) {
									createCell(row, cellOtherStyle,HSSFCell.CELL_TYPE_STRING, intPos+4,new HSSFRichTextString(""));
								} else {
									createCell(row, cellOtherStyle,HSSFCell.CELL_TYPE_STRING, intPos+4,new HSSFRichTextString(objChangeRequest1058VO.getIssuedBy()));
								}
							createCell(row,cellHeadStyle ,HSSFCell.CELL_TYPE_STRING, intPos+5,new HSSFRichTextString("Change From"));	
							createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, intPos+6,new HSSFRichTextString(objChangeRequest1058VO.getChangeFromClaNo()));

								HSSFRichTextString richTextClaDesc = new HSSFRichTextString("");
								if(objChangeRequest1058VO.getChangeFromClaDesc()!=null && objChangeRequest1058VO.getChangeFromClaDesc()!=""){
									richTextClaDesc = new HSSFRichTextString(ApplicationUtil.getRefinedClauseDesc(objChangeRequest1058VO.getChangeFromClaDesc().trim()));
								}
		                    createCell(row,cellClauseStyle ,HSSFCell.CELL_TYPE_STRING, intPos+7,richTextClaDesc);							
								if((objChangeRequest1058VO.getChangeFromEdlNo()!=null)|| (objChangeRequest1058VO.getChangeFromRefEdlNo()!=null) ||(objChangeRequest1058VO.getChangeFromPartOfNo()!=null)
										||(objChangeRequest1058VO.getChangeFromPartNo()!=null)||(objChangeRequest1058VO.getChangeFromDwoNo()!=null)
										||(objChangeRequest1058VO.getChangeFromPriceBookNo()!=null)||(objChangeRequest1058VO.getChangeFromEquip()!=null)||(objChangeRequest1058VO.getChangeFromEngiComments()!=null))
								{
									if(objChangeRequest1058VO.getChangeFromEdlNo() == null){
										objChangeRequest1058VO.setChangeFromEdlNo("");
									}else{
										strFromEnggData = strFromEnggData + objChangeRequest1058VO.getChangeFromEdlNo();
									}
									
									if(objChangeRequest1058VO.getChangeFromRefEdlNo() == null){
										objChangeRequest1058VO.setChangeFromRefEdlNo("");
									}else{
										strFromEnggData = strFromEnggData + objChangeRequest1058VO.getChangeFromRefEdlNo();
									}
									if(objChangeRequest1058VO.getChangeFromPartOfNo() == null){
										objChangeRequest1058VO.setChangeFromPartOfNo("");
									}else{
										strFromEnggData = strFromEnggData + objChangeRequest1058VO.getChangeFromPartOfNo();
									}								
									if(objChangeRequest1058VO.getChangeFromDwoNo() == null){
										objChangeRequest1058VO.setChangeFromDwoNo("");
									}else{
										strFromEnggData = strFromEnggData + "Dwo No " + objChangeRequest1058VO.getChangeFromDwoNo()+ "\n";
									}
									if(objChangeRequest1058VO.getChangeFromPartNo() == null){
										objChangeRequest1058VO.setChangeFromPartNo("");
									}else{
										strFromEnggData = strFromEnggData + "Part No " + objChangeRequest1058VO.getChangeFromPartNo()+ "\n";
									}
									if(objChangeRequest1058VO.getChangeFromPriceBookNo() == null){
										objChangeRequest1058VO.setChangeFromPriceBookNo("");
									}else{
										strFromEnggData = strFromEnggData + "Price Book No " + objChangeRequest1058VO.getChangeFromPriceBookNo() + "\n";
									}
									if(objChangeRequest1058VO.getChangeFromEquip() == null){
										objChangeRequest1058VO.setChangeFromEquip("");
									}else{
										strFromEnggData = strFromEnggData + objChangeRequest1058VO.getChangeFromEquip() + "\n";
									}
									if(objChangeRequest1058VO.getChangeFromEngiComments() == null){
										objChangeRequest1058VO.setChangeFromEngiComments("");
									}else{
										strFromEnggData = strFromEnggData + objChangeRequest1058VO.getChangeFromEngiComments();
									}
									createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, intPos+8,new HSSFRichTextString(strFromEnggData));								
								}
								for(int intCellPos=0;intCellPos<=4;intCellPos++){
									sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum()+2,intCellPos,intCellPos));
								}
								
							rowCount++;
							HSSFRow secondRow = sheet.createRow(rowCount);		
							createCell(secondRow,cellHeadStyle ,HSSFCell.CELL_TYPE_STRING, intPos+5,new HSSFRichTextString("Change To"));
							createCell(secondRow,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, intPos+6,new HSSFRichTextString(objChangeRequest1058VO.getChangeToClaNo()));
							HSSFRichTextString richTextToClaDesc = new HSSFRichTextString("");
								if(objChangeRequest1058VO.getChangeToClaDesc()!=null && objChangeRequest1058VO.getChangeToClaDesc()!=""){
									richTextToClaDesc = new HSSFRichTextString(ApplicationUtil.getRefinedClauseDesc(objChangeRequest1058VO.getChangeToClaDesc().trim()));
								}
	                        createCell(secondRow,cellClauseStyle ,HSSFCell.CELL_TYPE_STRING, intPos+7,richTextToClaDesc);
							strFromEnggData = "";
							
							if((objChangeRequest1058VO.getChangeToEdlNo()!=null)|| (objChangeRequest1058VO.getChangeToRefEdlNo()!=null) ||(objChangeRequest1058VO.getChangeToPartOfNo()!=null)
									||(objChangeRequest1058VO.getChangeToPartNo()!=null)||(objChangeRequest1058VO.getChangeToDwoNo()!=null)
									||(objChangeRequest1058VO.getChangeToPriceBookNo()!=null)||(objChangeRequest1058VO.getChangeToEquip()!=null)||(objChangeRequest1058VO.getChangeToEngiComments()!=null))
							{
								if(objChangeRequest1058VO.getChangeToEdlNo() == null){
									objChangeRequest1058VO.setChangeToEdlNo("");
								}else{
									strFromEnggData = strFromEnggData + objChangeRequest1058VO.getChangeToEdlNo();
								}
								
								if(objChangeRequest1058VO.getChangeToRefEdlNo() == null){
									objChangeRequest1058VO.setChangeToRefEdlNo("");
								}else{
									strFromEnggData = strFromEnggData + objChangeRequest1058VO.getChangeToRefEdlNo();
								}
								if(objChangeRequest1058VO.getChangeToPartOfNo() == null){
									objChangeRequest1058VO.setChangeToPartOfNo("");
								}else{
									strFromEnggData = strFromEnggData + objChangeRequest1058VO.getChangeToPartOfNo();
								}								
								if(objChangeRequest1058VO.getChangeToDwoNo() == null){
									objChangeRequest1058VO.setChangeToDwoNo("");
								}else{
									strFromEnggData = strFromEnggData + "Dwo No " + objChangeRequest1058VO.getChangeToDwoNo()+ "\n";
								}
								if(objChangeRequest1058VO.getChangeToPartNo() == null){
									objChangeRequest1058VO.setChangeToPartNo("");
								}else{
									strFromEnggData = strFromEnggData + "Part No " + objChangeRequest1058VO.getChangeToPartNo()+ "\n";
								}
								if(objChangeRequest1058VO.getChangeToPriceBookNo() == null){
									objChangeRequest1058VO.setChangeToPriceBookNo("");
								}else{
									strFromEnggData = strFromEnggData + "Price Book No " + objChangeRequest1058VO.getChangeToPriceBookNo() + "\n";
								}
								if(objChangeRequest1058VO.getChangeToEquip() == null){
									objChangeRequest1058VO.setChangeToEquip("");
								}else{
									strFromEnggData = strFromEnggData + objChangeRequest1058VO.getChangeToEquip() + "\n";
								}
								if(objChangeRequest1058VO.getChangeToEngiComments() == null){
									objChangeRequest1058VO.setChangeToEngiComments("");
								}else{
									strFromEnggData = strFromEnggData + objChangeRequest1058VO.getChangeToEngiComments();
								}
								createCell(secondRow,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, intPos+8,new HSSFRichTextString(strFromEnggData));								
							}
							
							rowCount++;
							HSSFRow thirdRow = sheet.createRow(rowCount);
							createCell(thirdRow,cellHeadStyle ,HSSFCell.CELL_TYPE_STRING, intPos+5,new HSSFRichTextString("Reason"));
							createCell(thirdRow,cellClauseStyle ,HSSFCell.CELL_TYPE_STRING, intPos+6,new HSSFRichTextString(objChangeRequest1058VO.getReason()));
							sheet.addMergedRegion(new CellRangeAddress(thirdRow.getRowNum(),thirdRow.getRowNum(),intPos+6,intPos+8));
							
							}
				}
			}
						
				
				    objHttpServletResponse.setContentType("application/vnd.ms-excel");
					objHttpServletResponse.setHeader("Content-disposition", "attachment; filename= Clauses applied to spec from unapproved 1058s.xls");
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
		
		public void createHeadingForClausesAppliedToSpec(HSSFWorkbook workBook, HSSFSheet sheet) throws Exception {

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
		
		HSSFRow row = sheet.createRow(0);
		HSSFRow secondRow = sheet.createRow(1);

			final String[] columnHeadings = { "1058 Number", "1058 Status","Spec Revision", "Customer Name", "Issued By","Clause Details"};
			final int[] columnWidth = {3500,5000,5000,8000,5000,5000};
			for (int intPos=0;intPos < columnHeadings.length ; intPos++)
				{			
				
					createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,intPos,new HSSFRichTextString(columnHeadings[intPos]));
					sheet.setColumnWidth(intPos,columnWidth[intPos]);
					if(intPos <= 4)
						sheet.addMergedRegion(new CellRangeAddress(0,1,intPos,intPos));
					if (intPos==5)
						sheet.addMergedRegion(new CellRangeAddress(0,0,intPos,intPos+3));
					
					
				}	
			
			final String[] columnSubHeadings = {"","","","","","Type","Clause number","Clause Description","Engineering Data"};
			final int[] columnNewWidth = {3500,5000,5000,8000,5000,3500,3000,18000,5000};
			for (int intPos=0;intPos < columnSubHeadings.length ; intPos++)
				{
					createCell(secondRow,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,intPos,new HSSFRichTextString(columnSubHeadings[intPos]));
					sheet.setColumnWidth(intPos,columnNewWidth[intPos]);
				}
		}
		
		
		
		public ActionForward view1058ClauseAppliedtoSpec(
				ActionMapping objActionMapping, ActionForm objActionForm,
				HttpServletRequest objHttpServletRequest,
				HttpServletResponse objHttpServletResponse)
				throws ApplicationException {
			LogUtil
					.logMessage("Inside the SearchRequest1058Action:view1058ClauseAppliedtoSpec");
			String strForwardKey = ApplicationConstants.VIEW_1058_CLAUSE_APPLIED_TO_SPEC;
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
					.getAttribute(ApplicationConstants.USER_IN_SESSION);

			ChangeRequest1058VO objChangeRequest1058VO = new ChangeRequest1058VO();
			SearchRequest1058Form objSearchRequest1058Form = (SearchRequest1058Form) objActionForm;
			try {
				ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory
						.getChangeRequest1058BO();
				ArrayList arlReportDetailsList = new ArrayList();
				objChangeRequest1058VO.setUserID(objLoginVo.getUserID());
				objChangeRequest1058VO.setOrderbyFlag(objSearchRequest1058Form.getOrderbyFlag());//Added for CR-127 Sorting

				arlReportDetailsList=objChangeRequest1058BI.fetch1058ReportDetails(objChangeRequest1058VO);
				
				LogUtil.logMessage("arlReportDetailsList.size():"
								+ arlReportDetailsList.size());
				
				
				if (arlReportDetailsList != null && arlReportDetailsList.size() > 0) {
					objSearchRequest1058Form.setClausesAppliedtoSpec(arlReportDetailsList);
					LogUtil.logMessage("ArrayListt" + objSearchRequest1058Form.getClausesAppliedtoSpec());
					}			
				else{
					objSearchRequest1058Form
					.setMessageID(ApplicationConstants.NO_RECORD_MESSAGE_ID);
				}
				//Added for CR-127
				if(objSearchRequest1058Form.getColumnheader() == null)
					objSearchRequest1058Form.setColumnheader("1058Number");

			} catch (Exception objExp) {
				LogUtil
						.logMessage("Enters into Exception Block in SearchRequest1058Action..");
				ErrorInfo objErrorInfo = new ErrorInfo();
				String strError = objExp.getMessage();
				objErrorInfo.setMessage(strError);
				LogUtil.logMessage("Error Message:" + strError);
				LogUtil.logError(objExp, objErrorInfo);
				strForwardKey = ApplicationConstants.FAILURE;
			}
			return objActionMapping.findForward(strForwardKey);
		}
		
		
		
		public ActionForward exportUnapproved1058withClausesApplied(ActionMapping objActionMapping,
				ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
				HttpServletResponse objHttpServletResponse)
				throws ApplicationException {
			LogUtil
			.logMessage("Inside the SearchRequest1058Action:export1058DetailsToExcel");
			String strForwardKey = ApplicationConstants.SUCCESS;
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
					.getAttribute(ApplicationConstants.USER_IN_SESSION);
			int rowCount = 0;
			int intPos = 0;
			ArrayList arl1058Details = new ArrayList();
			ChangeRequest1058VO objChangeRequest1058VO = new ChangeRequest1058VO();
			try {

				// create workbook
				HSSFWorkbook workBook = new HSSFWorkbook();
				HSSFSheet sheet = workBook.createSheet("Unapproved 1058's with clauses applied");
						
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
				
						ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory
						.getChangeRequest1058BO();
						
						ArrayList arlCommonList = new ArrayList();
						ArrayList arlReportList = new ArrayList();
						
						objChangeRequest1058VO.setUserID(objLoginVo.getUserID());

						arlCommonList.add(objChangeRequest1058BI.fetch1058ReportDetails(objChangeRequest1058VO));
								
							
						
						
						Iterator listCommonList = null;
						Iterator listRequestList = null;
						HSSFRow row = sheet.createRow(rowCount);
						if (arlCommonList.size() != 0) {

							createHeadingForUnapproved1058(workBook, row, sheet);
							listCommonList = arlCommonList.iterator();

							while (listCommonList.hasNext()) {

								arlReportList = new ArrayList();
								arlReportList = (ArrayList) listCommonList.next();
								
								listRequestList = arlReportList.iterator();

								while (listRequestList.hasNext()) {

									objChangeRequest1058VO = new ChangeRequest1058VO();
									objChangeRequest1058VO = (ChangeRequest1058VO) listRequestList
											.next();
									rowCount++;
									intPos = 0;
									row = sheet.createRow(rowCount);
									int rowspan=3;
							//sheet.addMergedRegion(new CellRangeAddress(row,rowspan-1,0,new HSSFRichTextString(objChangeRequest1058VO.getOrderNo())));					
							createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 0,new HSSFRichTextString(objChangeRequest1058VO.getNumber1058()));
							createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 1,new HSSFRichTextString(objChangeRequest1058VO.getStatusDesc()));
							createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 2,new HSSFRichTextString(objChangeRequest1058VO.getCustName()));
							if (objChangeRequest1058VO.getSysEngineerName() == null) {
								createCell(row, cellOtherStyle,
										HSSFCell.CELL_TYPE_STRING, 3,
										new HSSFRichTextString(""));
							} else {
								createCell(row, cellOtherStyle,
										HSSFCell.CELL_TYPE_STRING, 3,
										new HSSFRichTextString(
												objChangeRequest1058VO
														.getSysEngineerName()));						
				
							}
				}
			}
						}
				
				    objHttpServletResponse.setContentType("application/vnd.ms-excel");
					objHttpServletResponse.setHeader("Content-disposition", "attachment; filename=Unapproved 1058's with clauses applied.xls");
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
		
		public void createHeadingForUnapproved1058(HSSFWorkbook workBook, HSSFRow row,
				HSSFSheet sheet) throws Exception {

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
//		row = sheet.createRow(rowCount);
//		Updated for CR-118 QA-Fix

			final String[] columnHeadings = {"1058 Number", "1058 Status", "Customer Name", "System Engineer Name"};
			final int[] columnWidth = {3500,6000,6000,6300};
			for (int intPos=0;intPos < 4; intPos++)
				{
				 createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,intPos,new HSSFRichTextString(columnHeadings[intPos]));
					 sheet.setColumnWidth(intPos,columnWidth[intPos]);
				 }
		}
	
		
	//Added for CR-126 Ends Here	
	
		
		//Added for CR-127 Starts Here
		
		public ActionForward viewPending1058s(
				ActionMapping objActionMapping, ActionForm objActionForm,
				HttpServletRequest objHttpServletRequest,
				HttpServletResponse objHttpServletResponse)
				throws ApplicationException {
			LogUtil
					.logMessage("Inside the SearchRequest1058Action:viewPending1058s");
			String strForwardKey = ApplicationConstants.VIEW_PENDING_1058s;
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
					.getAttribute(ApplicationConstants.USER_IN_SESSION);

			ChangeRequest1058VO objChangeRequest1058VO = new ChangeRequest1058VO();
			SearchRequest1058Form objSearchRequest1058Form = (SearchRequest1058Form) objActionForm;
			try {
				ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory
						.getChangeRequest1058BO();
				ArrayList arlReportDetailsList = new ArrayList();
				objChangeRequest1058VO.setUserID(objLoginVo.getUserID());

				arlReportDetailsList=objChangeRequest1058BI.fetch1058PendingReportDetails(objChangeRequest1058VO);
				
				LogUtil.logMessage("arlReportDetailsList.size():"
								+ arlReportDetailsList.size());
				
				if (arlReportDetailsList != null && arlReportDetailsList.size() > 0) {
					objSearchRequest1058Form.setPending1058sList(arlReportDetailsList);
					LogUtil.logMessage("ArrayListt" + objSearchRequest1058Form.getPending1058sList());
				}
				else{
					objSearchRequest1058Form
					.setMessageID(ApplicationConstants.NO_RECORD_MESSAGE_ID);
				}

			} catch (Exception objExp) {
				LogUtil
						.logMessage("Enters into Exception Block in SearchRequest1058Action..");
				ErrorInfo objErrorInfo = new ErrorInfo();
				String strError = objExp.getMessage();
				objErrorInfo.setMessage(strError);
				LogUtil.logMessage("Error Message:" + strError);
				LogUtil.logError(objExp, objErrorInfo);
				strForwardKey = ApplicationConstants.FAILURE;
			}
			return objActionMapping.findForward(strForwardKey);
		}
		
		public ActionForward exportPending1058s(ActionMapping objActionMapping,
				ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
				HttpServletResponse objHttpServletResponse)
				throws ApplicationException {
			LogUtil
			.logMessage("Inside the SearchRequest1058Action:exportPending1058DetailsToExcel");
			String strForwardKey = ApplicationConstants.SUCCESS;
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
					.getAttribute(ApplicationConstants.USER_IN_SESSION);
			int rowCount = 0;
			int intPos = 0;
			ArrayList arl1058Details = new ArrayList();
			ChangeRequest1058VO objChangeRequest1058VO = new ChangeRequest1058VO();
			try {

				// create workbook
				HSSFWorkbook workBook = new HSSFWorkbook();
				HSSFSheet sheet = workBook.createSheet("Open(or)Pending 1058s");
						
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
				
						ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory
						.getChangeRequest1058BO();
						
						ArrayList arlCommonList = new ArrayList();
						ArrayList arlReportList = new ArrayList();
						
						objChangeRequest1058VO.setUserID(objLoginVo.getUserID());

						arlCommonList.add(objChangeRequest1058BI.fetch1058PendingReportDetails(objChangeRequest1058VO));
								
							
						
						
						Iterator listCommonList = null;
						Iterator listRequestList = null;
						HSSFRow row = sheet.createRow(rowCount);
						if (arlCommonList.size() != 0) {

							createHeadingForPending1058(workBook, row, sheet);
							listCommonList = arlCommonList.iterator();

							while (listCommonList.hasNext()) {

								arlReportList = new ArrayList();
								arlReportList = (ArrayList) listCommonList.next();
								
								listRequestList = arlReportList.iterator();

								while (listRequestList.hasNext()) {

									objChangeRequest1058VO = new ChangeRequest1058VO();
									objChangeRequest1058VO = (ChangeRequest1058VO) listRequestList
											.next();
									rowCount++;
									intPos = 0;
									row = sheet.createRow(rowCount);
									int rowspan=3;
							//sheet.addMergedRegion(new CellRangeAddress(row,rowspan-1,0,new HSSFRichTextString(objChangeRequest1058VO.getOrderNo())));					
							createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 0,new HSSFRichTextString(objChangeRequest1058VO.getId1058()));
							createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 1,new HSSFRichTextString(objChangeRequest1058VO.getCustName()));
							if (objChangeRequest1058VO.getSysEngineerName() == null) {
								createCell(row, cellOtherStyle,HSSFCell.CELL_TYPE_STRING,2,new HSSFRichTextString(""));
							} else {
								createCell(row, cellOtherStyle,HSSFCell.CELL_TYPE_STRING, 2,new HSSFRichTextString(objChangeRequest1058VO.getSysEngineerName()));
							}
							createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 3,new HSSFRichTextString(objChangeRequest1058VO.getStatusDesc()));
							createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 4,new HSSFRichTextString(objChangeRequest1058VO.getAwaitingActionOnName()));
							createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 5,new HSSFRichTextString(objChangeRequest1058VO.getLastTransc()));
				}
			}
						}
				
				    objHttpServletResponse.setContentType("application/vnd.ms-excel");
					objHttpServletResponse.setHeader("Content-disposition", "attachment; filename=Open(or)Pending1058s.xls");
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
		
		public void createHeadingForPending1058(HSSFWorkbook workBook, HSSFRow row,
				HSSFSheet sheet) throws Exception {

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


			final String[] columnHeadings = { "1058 Number","Customer Name", "System Engineer Name","1058 Status", "Awaiting an action from staff members", "Number of days since last transitions"};
			final int[] columnWidth = {3500,5000,5000,5000,7000,6300};
			for (int intPos=0;intPos < 6; intPos++)
				{
				 createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,intPos,new HSSFRichTextString(columnHeadings[intPos]));
					 sheet.setColumnWidth(intPos,columnWidth[intPos]);
				 }
		}
	//Added for CR-127 ends here
		
	//Added for CR-134 starts here
		public ActionForward showSummary1058Transitioned(
				ActionMapping objActionMapping, ActionForm objActionForm,
				HttpServletRequest objHttpServletRequest,
				HttpServletResponse objHttpServletResponse)
				throws ApplicationException {
			LogUtil
					.logMessage("Inside the SearchRequest1058Action:showSummary1058Transitioned");
			String strForwardKey = ApplicationConstants.REPORTS;
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
					.getAttribute(ApplicationConstants.USER_IN_SESSION);

			ChangeRequest1058VO objChangeRequest1058VO = new ChangeRequest1058VO();
			SearchRequest1058Form objSearchRequest1058Form = (SearchRequest1058Form) objActionForm;
			try {
				ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory
						.getChangeRequest1058BO();
				ArrayList arlTransReportDetailsList = new ArrayList();
				objChangeRequest1058VO.setUserID(objLoginVo.getUserID());
				
				objChangeRequest1058VO.setFromDate(objSearchRequest1058Form.getFromDate());
				objChangeRequest1058VO.setToDate(objSearchRequest1058Form.getToDate());
				
				LogUtil.logMessage("values" + objChangeRequest1058VO.getFromDate() + objChangeRequest1058VO.getToDate());
				
				arlTransReportDetailsList=objChangeRequest1058BI.fetch1058TransitionReportDetails(objChangeRequest1058VO);
				
				LogUtil.logMessage("arlTransReportDetailsList.size():"
								+ arlTransReportDetailsList.size());
				
				if (arlTransReportDetailsList != null && arlTransReportDetailsList.size() > 0) {
					objSearchRequest1058Form.setTransitionSummary1058List(arlTransReportDetailsList);
					LogUtil.logMessage("ArrayListt" + objSearchRequest1058Form.getTransitionSummary1058List());
				}
				else{
					objSearchRequest1058Form
					.setMessageID(ApplicationConstants.NO_RECORD_MESSAGE_ID);
				}

			} catch (Exception objExp) {
				LogUtil
						.logMessage("Enters into Exception Block in SearchRequest1058Action..");
				ErrorInfo objErrorInfo = new ErrorInfo();
				String strError = objExp.getMessage();
				objErrorInfo.setMessage(strError);
				LogUtil.logMessage("Error Message:" + strError);
				LogUtil.logError(objExp, objErrorInfo);
				strForwardKey = ApplicationConstants.FAILURE;
			}
			return objActionMapping.findForward(strForwardKey);
		}
	//Added for CR-134 ends here
		
}

