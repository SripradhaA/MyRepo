/**
 * 
 */
package com.EMD.LSDB.action.SpecMaintenance;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.FileReader;
import java.io.StringReader;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.EMD.LSDB.action.common.PDFView;
import com.EMD.LSDB.action.common.EMDAction;
import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.AppendixBI;
import com.EMD.LSDB.bo.serviceinterface.FileUploadBI;
import com.EMD.LSDB.bo.serviceinterface.MainFeatureInfoBI;
import com.EMD.LSDB.bo.serviceinterface.OrderBI;
import com.EMD.LSDB.bo.serviceinterface.OrderGenArrangeBI;
import com.EMD.LSDB.bo.serviceinterface.OrderPerfCurveBI;
import com.EMD.LSDB.bo.serviceinterface.OrderSectionBI;
import com.EMD.LSDB.bo.serviceinterface.OrderSpecificationBI;
import com.EMD.LSDB.bo.serviceinterface.SpecTypeBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.constant.DatabaseConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.vo.common.AppendixVO;
import com.EMD.LSDB.vo.common.FileVO;
import com.EMD.LSDB.vo.common.GenArrangeVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.MainFeatureInfoVO;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.PerformanceCurveVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SpecSuppVO;
import com.EMD.LSDB.vo.common.SpecTypeVo;
import com.EMD.LSDB.vo.common.SpecificationItemVO;
import com.EMD.LSDB.vo.common.SpecificationVO;
import com.EMD.LSDB.vo.common.SubSectionVO;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfDestination;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfOutline;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

/***************************************************************************
 * --------------------------------------------------------------------------------------------------------
 *     Date         Version  Modified by          	comments                              Remarks 
 * 09/03/2010        1.0      RR68151    Modified to accomodate addition of new   	   Added for CR_84
 *                                       Specification Types. Rephrased all SpecType
 *                                       condition checks accordingly.   
 * 											 	 
 * --------------------------------------------------------------------------------------------------------
 * 09/07/2010        1.1      BM85529    Modified for providing RichText (Bold,        Added for CR_88  
 *                                       Italic,Underline) outputs in PDF reports.
 * 16/03/2012        1.2      SD41630    Modified for customer and distributor logs    Added for CR_106
 * 										 added in the showHeaderInformation page.* 
 * --------------------------------------------------------------------------------------------------------
 * **************************************************************************/
public class GenerateProofReadingDraftAction extends EMDAction {
	
	public static String strModelName = "";

	public static int intQuantity = 0;

	public static String strGenArrangeNotes = "";

	public static String strSpecType = "";

	public static int intStatusCode = 0;

	public static String strCustomerName = "";

	public static String strCustomerCode = "";

	public static String strOrderNo = "";

	public static String strOrderKey = "";

	public static int revInput = 0;

	public static String appendixFlag = "";

	public static String strDistributorName = "";

	public static int intSpecTypeSeqNo = 0;
	
	


	
	public static Font strSSFointSizeNine = new Font(Font.TIMES_ROMAN, 10, 0,
			Color.BLACK);

		
	// CR 91
	
	public static String strPresentRevCode = "";
	
	public static String strPastClauseNo = "";
	
	public static String strPreClauseNo = "";
	
	public static String strPastClauseDesc = "";
	
	public static String strPreClauseDesc = "";
	
	public static String strPastReason = "";
	
	public static String strPreReason = "";
	
	public static String strPastRevCode = "";
	
	public static String strPublishDate = "";
	
	public static String strPastSpecStatusDesc = "";
	
	public static String strPresentSpecStatusDesc = "";
	
	//Added for Spec Suppliment Header Change
	
	public static String strQtyInWords = "";
	
	public static String strOpeningDate = "";

	public static String flag = "";

	public static ArrayList alSections = new ArrayList();

	public static ArrayList alSecionNo = new ArrayList();

	public static Font strFontNoUnderLine = new Font(Font.TIMES_ROMAN, 12,
			Font.BOLD, Color.BLACK);

	public static Font strFontUnderLine = new Font(Font.TIMES_ROMAN, 12,
			Font.UNDERLINE, Color.BLACK);

	public static Font strFontUnderLineTwelve = new Font(Font.TIMES_ROMAN, 12,
			Font.UNDERLINE, Color.BLACK);

	public static Font strFointSizeNine = new Font(Font.TIMES_ROMAN, 8, 0,
			Color.BLACK);

	public static Font strFointSizeNoBold = new Font(Font.TIMES_ROMAN, 12, 0,
			Color.BLACK);

	public static Font strFontBoldWithUnderLine = new Font(Font.TIMES_ROMAN,
			12, Font.BOLD + Font.UNDERLINE, Color.BLACK);

	public static Font strFointSizeBoldNine = new Font(Font.TIMES_ROMAN, 9,
			Font.BOLD, Color.BLACK);

	public static Font strFointSizeBoldNineItalic = new Font(Font.TIMES_ROMAN,
			9, Font.BOLDITALIC, Color.BLACK);

	public static Font strFontSizeBoldTen = new Font(Font.TIMES_ROMAN, 11,
			Font.BOLD, Color.BLACK);

	public static Font strFontUnderLineTen = new Font(Font.TIMES_ROMAN, 11,
			Font.UNDERLINE, Color.BLACK);

	public static Font strFontSizeNoBoldTen = new Font(Font.TIMES_ROMAN, 12, 0,
			Color.BLACK);

	public static Font strFointSizeNoBoldTen1 = new Font(Font.TIMES_ROMAN, 10,
			0, Color.BLACK);

	public static Font strFointSizeBoldNineRed = new Font(Font.TIMES_ROMAN, 9,
			Font.BOLD, Color.RED);

	public static Font strFointSizeBoldNineBlack = new Font(Font.TIMES_ROMAN,
			9, Font.BOLD, Color.BLACK);

	public static Font strFontBOLD = new Font(Font.TIMES_ROMAN, 12, Font.BOLD,
			Color.BLACK);

	public static Font strFointSizeEight = new Font(Font.TIMES_ROMAN, 8, 0,
			Color.BLACK);

	// Added for LSDB_CR-74 by KA57588

	public static Font strFointSizeBoldSixBlack = new Font(Font.TIMES_ROMAN, 6,
			Font.BOLD, Color.BLACK);

	// Added For CR.No.79 To add EMD Header Image to PDF on 02-Nov-2009 by RR68151
	
	public static String strHeaderFlag = "";
	
    // Added For CR.No.84 New Specification Type additions on 02-Mar-2010 by RR68151
	
	public static String strSpecDistributorMapFlag = "";
	
	public static String strSpecPerfCurvMapFlag = "";
	
	public static String strSpecGenArrMapFlag = "";
	
	// Added For CR.No.99 to show HorsePower in General Information Text
	
	public static String strHorsePower = "";
	
	// Added For CR.No.104 to bring up Custom Model Name and General Information Text
	
	public static String strGenInfoText = "";
	
	public static String strCustMdlName = "";
	
	//dded for CR_106
	public static int intCustImageSeqNo = 0;
	public static int intDistImageSeqNo = 0;
    public static String strCustLogoFlag = "Y";
	public static String strDistriLogoFlag = "Y";
	
	//Added for CR-135
	public static String strPrevOrderNo = "";
	public static String strPrevCustName = "";
	public static String strPrevModelName = "";
	public static String strPrevCustCode = "";
	public static String strPrevQuantity = "";
	public static String strPrevQtyInWords = "";
	public static String strPresentPrevSpecStatusDesc = "";
	public static String strPrevRevCode = "";	
	/***************************************************************************
	 * This method is for generating ProofReading Draft PDF
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
	public ActionForward createProofReadingPDF(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
			throws ApplicationException {

		LogUtil
				.logMessage("Inside the GenerateProofReadingDraft:createProofReadingPDF");

		HttpSession objSession = objHttpServletRequest.getSession(false);
		String strUserID = null;
		String pdfType = null;
		int intRevCode = 0;
		int intOrderKey = 0;
		//Added for CR_106 -On demand Spec Supplement - previous order to compare different version of same or different order 
		int intPrevOrderKey = 0;
		String strForwardKey = ApplicationConstants.SUCCESS;

		try {

			LoginVO objLoginVo = (LoginVO) objSession
					.getAttribute(ApplicationConstants.USER_IN_SESSION);

			// Getting User from the session
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}

			// To get Order Key,Rev Code and Pdf type(Sales or Engg) from
			// Request
			if (objHttpServletRequest.getParameter("orderKey") != null) {
				intOrderKey = Integer.parseInt((objHttpServletRequest
						.getParameter("orderKey").toString().trim()));
			}

			if (objHttpServletRequest.getParameter("pdfType") != null) {
				pdfType = objHttpServletRequest.getParameter("pdfType")
						.toString().trim();

			}

			if (objHttpServletRequest.getParameter("revCode") != null) {
				intRevCode = Integer.parseInt(objHttpServletRequest
						.getParameter("revCode").toString().trim());
			}
			//Added for CR_106 - Ondemand Spec Supplement
			if (objHttpServletRequest.getParameter("prevOrderKey") != null) {
				intPrevOrderKey = Integer.parseInt((objHttpServletRequest
						.getParameter("prevOrderKey").toString().trim()));
			}
			
			/*** CR 91 View ProofReading Spec Supplement ***/
			
			OrderVO objOrderVO = new OrderVO();
			OrderVO objjOrderVO = new OrderVO();
			ArrayList arlOrderList = new ArrayList();
			ArrayList arlPrevOrderList = new ArrayList();//Added for Cr-135
			
			objOrderVO.setOrderKey(intOrderKey);
			objOrderVO.setPrevOrderKey(intPrevOrderKey);//Added for Cr-135

			objOrderVO.setUserID(strUserID);
			//Modified for CR_106 - Ondemand Spec Supplement
			if (intPrevOrderKey != 0)
				{objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION_SNAP_SHOT);}
			else
				{objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);}
			LogUtil.logMessage("The value of Datalocation type = "+objOrderVO.getDataLocTypeCode());		
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			
			//Added for CR-135 starts
			arlPrevOrderList = objOrderBI.fetchPrevOrders(objOrderVO);

			if (arlPrevOrderList != null && arlPrevOrderList.size() > 0) {

				objjOrderVO = (OrderVO) arlPrevOrderList.get(0);
				objOrderVO.setPrevOrderNo(objjOrderVO.getPrevOrderNo());
				objOrderVO.setPrevOrderKey(intPrevOrderKey);
				objOrderVO.setPrevCustomerName(objjOrderVO.getPrevCustomerName());
				objOrderVO.setPrevModelName(objjOrderVO.getPrevModelName());
				objOrderVO.setPrevQuantity(objjOrderVO.getPrevQuantity());
				objOrderVO.setPrevCustCode(objjOrderVO.getPrevCustCode());
				objOrderVO.setUserID(strUserID);
				
				LogUtil.logMessage("prevOrdervalue" +objjOrderVO.getPrevOrderNo());
				LogUtil.logMessage("OrderList Size" + arlPrevOrderList.size());
			}
			//Added for CR-135 ends
			if (arlOrderList != null && arlOrderList.size() > 0) {

				objjOrderVO = (OrderVO) arlOrderList.get(0);
				
				//Added for CR-118
				objOrderVO.setOrderKey(intOrderKey);
				objOrderVO.setUserID(strUserID);
				
				if (intPrevOrderKey != 0)
					objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION_SNAP_SHOT);
				else
					objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
				objOrderVO.setPrevOrderKey(intPrevOrderKey);
				
				objOrderBI  = ServiceFactory.getOrderBO();
				SpecSuppVO objSpSupVersionVO = new SpecSuppVO();
				objSpSupVersionVO = objOrderBI.getVersions(objOrderVO);
							
				strPresentRevCode = objSpSupVersionVO.getStrPresentRevCode();
				strPresentSpecStatusDesc = objSpSupVersionVO.getStrPresentSpecStatusDesc();
				if(strPresentRevCode == null)
					strPresentRevCode = "";
				
				//Added for CR-118 ends here
				
				LogUtil.logMessage("OrderList Size" + arlOrderList.size());
			}
			//Added For CR_104 need to display custom madel name in palce of model name
			//commented for CR_104
			//strModelName = objjOrderVO.getModelName();
        	strModelName = objjOrderVO.getCustMdlName();
			strSpecType = strPresentSpecStatusDesc + strPresentRevCode; //Updated for CR-118
			intStatusCode = objjOrderVO.getSpecStatusCode();
			strCustomerName = objjOrderVO.getCustomerName();
			strCustomerCode = objjOrderVO.getSapCusCode();
			strOrderNo = objjOrderVO.getOrderNo();
			intQuantity = objjOrderVO.getQuantity();
			revInput = objjOrderVO.getRevCode();
			appendixFlag = objjOrderVO.getAppendixFlag();
			strDistributorName = objjOrderVO.getDistributorName();
			intSpecTypeSeqNo = objjOrderVO.getSpecTypeSeqNo();			
			strHeaderFlag = objjOrderVO.getPdfHeaderFlag();
			//Added For CR_104 to bring Custom Model Name and General Info Text
			//strGenInfoText = objjOrderVO.getGenInfoText();
			//Updated for CR-118
			strGenInfoText = objjOrderVO.getGenInfoTextForProofReading();
			strCustMdlName = objjOrderVO.getCustMdlName();
			intCustImageSeqNo =objjOrderVO.getCustImageSeqNo();
			intDistImageSeqNo =objjOrderVO.getDistImageSeqNo();
			strCustLogoFlag =objjOrderVO.getCustLogoFlag();
			strDistriLogoFlag =objjOrderVO.getDistriLogoFlag();
			//Added for CR-135
			strPrevOrderNo = objOrderVO.getPrevOrderNo();
			LogUtil.logMessage("strPrevOrderNo :"+strPrevOrderNo);
			strPrevCustName = objOrderVO.getPrevCustomerName();
			LogUtil.logMessage("strPrevCustName :"+strPrevCustName);
			strPrevModelName = objOrderVO.getPrevModelName();
			LogUtil.logMessage("strPrevModelName :"+strPrevModelName);
			strPrevCustCode = objOrderVO.getPrevCustCode();
			LogUtil.logMessage("strPrevCustCode :"+strPrevCustCode);
			strPrevQuantity = objOrderVO.getPrevQuantity();
			LogUtil.logMessage("strPrevQuantity :"+strPrevQuantity);
			//Ends CR-135
			//Modified for CR_106 to bring Sales Supplement changes
			if (pdfType.equalsIgnoreCase("SalesSupp")) {
				
				GenerateSpecSupplimentPDF(intOrderKey,intPrevOrderKey,strUserID,intRevCode,pdfType,objHttpServletResponse);
				
				}
			else if (pdfType.equalsIgnoreCase("EngrSupp")) {
				
				GenerateSpecSupplimentPDF(intOrderKey,intPrevOrderKey,strUserID,intRevCode,pdfType,objHttpServletResponse);
				
				}
			//Modified for CR_109 to bring Marked Clauses PDF
			else if (pdfType.equalsIgnoreCase("MarkedClauses"))	{
				
				GenerateMarkedClausesPDF(strUserID, intOrderKey, pdfType, objHttpServletResponse);
				
				}
			else {
				// to generate for first time without Index Page Details
				GeneratePDF(strUserID, intOrderKey, pdfType, intRevCode, "N",
						objHttpServletResponse);
	
				// To generate with Index Page details
				GeneratePDF(strUserID, intOrderKey, pdfType, intRevCode, "Y",
						objHttpServletResponse);
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
	 * This method is for generating ProofReading Draft PDF
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param UserID
	 * @param OrderKey
	 * @param PdfType
	 * @param RevisionCode
	 * @param Flag
	 * @param HttpServletResponse
	 * @throws Exception
	 * @author VV49326
	 **************************************************************************/

	private static void GeneratePDF(String strUserID, int intOrderKey,
			String strPdfType, int intRevCode, String strFlag,
			HttpServletResponse objHttpServletResponse) throws Exception {

		LogUtil.logMessage("Inside GenerateProofReadingDraft.GeneratePDF");
		
		String strSpecFileName = "";
		StringBuffer sbSpecFileName = null;
		try {

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Document document = new Document(PageSize.A4, 7, 0, 40, 100);//Top Margin edited for CR-130 PDF logo alignment issue
			PdfWriter writer = PdfWriter.getInstance(document, baos);

			FontFactory.registerDirectories();

			document.addAuthor("EMD-LSDB");
			document.addSubject("EMD-LSDB Proofreading Draft PDF ");
			document.addCreationDate();
			document.open();
			
			//Added For CR.No.79 To add EMD Header Image to PDF on 02-Nov-2009 by RR68151
			String strWaterMarkFlag = ApplicationConstants.YES;
			//Modified for CR_100 To add Order Number to PDF Footer
			//Updated for CR_101 to bring Spec Revision Type
			PDFView objMyPage = new PDFView(strWaterMarkFlag,strHeaderFlag,strOrderNo,strSpecType);
			writer.setPageEvent(objMyPage);
			//Added For CR.No.79 To add EMD Header Image to PDF on 02-Nov-2009 by RR68151 - Ends here
			
		    // Added For CR.No.84 New Specification Type additions on 02-Mar-2010 by RR68151
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setSpectypeSeqno(intSpecTypeSeqNo);
			objSpecTypeVo.setUserID(strUserID);
		
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			ArrayList arlSpecTypeList = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpecTypeList != null && arlSpecTypeList.size() > 0) {

				SpecTypeVo objjSpecTypeVo = (SpecTypeVo) arlSpecTypeList.get(0);
				LogUtil.logMessage("SpecTypeList Size" + arlSpecTypeList.size());
				if (objjSpecTypeVo.isDistMaintDisPlayFlag())
					strSpecDistributorMapFlag = ApplicationConstants.YES;
				if (objjSpecTypeVo.isPerfCurveMaintDisPlayFlag())
					strSpecPerfCurvMapFlag = ApplicationConstants.YES;
				if (objjSpecTypeVo.isGenArrMaintDisPlayFlag())	
					strSpecGenArrMapFlag = ApplicationConstants.YES;	
			}
			
		    // Added For CR.No.84 New Specification Type additions on 02-Mar-2010 by RR68151 - Ends here
			
			LogUtil.logMessage("Order Key" + intOrderKey + "  Pdf Type"
					+ strPdfType);

			// To get PDF File Name
			sbSpecFileName = new StringBuffer();
			
			if (strPdfType.equalsIgnoreCase("Engg")) {

				sbSpecFileName.append(strOrderNo);
				sbSpecFileName.append("_");
				sbSpecFileName.append(strSpecType.trim());
				sbSpecFileName.append("_Proofreading ");
				sbSpecFileName.append("Engr.pdf");
				strSpecFileName = sbSpecFileName.toString();
				LogUtil.logMessage("File Name" + strSpecFileName);

			} else {
				sbSpecFileName = new StringBuffer();
				sbSpecFileName.append(strOrderNo);
				sbSpecFileName.append("_");
				sbSpecFileName.append(strSpecType.trim());
				sbSpecFileName.append("_Proofreading ");
				sbSpecFileName.append("Sales.pdf");
				strSpecFileName = sbSpecFileName.toString();
				LogUtil.logMessage("File Name" + strSpecFileName);
			}

			PdfPTable pTable = new PdfPTable(10);
			showHeaderInformation(pTable, document, strSpecType,
					strCustomerName, strCustomerCode, strOrderNo, strModelName,
					intQuantity, strFlag);

			// GENERAL INFORMATION Book Mark
			PdfDestination d1 = new PdfDestination(PdfDestination.FIT);
			PdfOutline root = writer.getRootOutline();
			PdfOutline olineGenInfo = new PdfOutline(root, d1,
					"I. GENERAL INFORMATION");

			if (strFlag.equalsIgnoreCase("N")) {
				alSecionNo = new ArrayList();
				alSections = new ArrayList();
				alSecionNo.add(new Integer(writer.getCurrentPageNumber()));
				alSections.add("I" + ". " + "GENERAL INFORMATION:");// Adding
				// all the
				// Sections
				// in the
				// List
			}

			showGeneralInformation(pTable, document, intOrderKey,
					strCustomerName, writer, strUserID, strFlag, intRevCode);

			showSpecification(pTable, document, intOrderKey, strModelName,
					strUserID, strFlag, intRevCode);

			// General Arrangement will display for Locomotive spec
		    // Added For CR.No.84 New Specification Type additions on 02-Mar-2010 by RR68151
			
			if (strSpecGenArrMapFlag.equalsIgnoreCase(ApplicationConstants.YES))	{
			//if (intSpecTypeSeqNo == 1) {
				showGeneralArrangement(document, intOrderKey, strUserID,
						strFlag, intRevCode, writer);

			}

			PdfPTable t = new PdfPTable(6);
			t.setWidthPercentage(85);
			showIndexPage(alSections, alSecionNo, t, document, writer);
			t.deleteBodyRows();

			document.newPage();
			showAllSectionInformation(document, writer, d1, root, flag,
					intOrderKey, strPdfType, intRevCode, strUserID, strFlag,
					appendixFlag);

			if (appendixFlag != null && !"".equals(appendixFlag)
					&& "Y".equalsIgnoreCase(appendixFlag)) {
				document.newPage();
				PdfDestination appendix = new PdfDestination(PdfDestination.FIT);
				PdfOutline rootAppdx = writer.getRootOutline();
				PdfOutline olineAppdx = new PdfOutline(rootAppdx, appendix,
						"APPENDIX");
				//Added For CR_101 to hide Appendix Images in Sales Spec
				showAppendix(document, strPdfType, intOrderKey, writer, strUserID, strFlag);
			}

			// Performance Curve Book Mark
		    // Added For CR.No.84 New Specification Type additions on 02-Mar-2010 by RR68151
			if (strSpecPerfCurvMapFlag.equalsIgnoreCase(ApplicationConstants.YES))	{
			//if (intSpecTypeSeqNo == 1) {
				// document.newPage();
				showPerformanceCurve(document, intOrderKey, writer, strUserID,
						strFlag, intRevCode);
			}
			
			document.close();

			if (strFlag.equalsIgnoreCase("Y")) {

				// setting the content type
				objHttpServletResponse.setContentType("application/pdf");
				objHttpServletResponse.setHeader("Content-disposition",
						"attachment;filename=" + strSpecFileName);

				objHttpServletResponse.setContentLength(baos.size());

				// write ByteArrayOutputStream to the ServletOutputStream
				ServletOutputStream out = objHttpServletResponse
						.getOutputStream();
				baos.writeTo(out);
				out.flush();

			}

		} catch (Exception objExp) {
			throw objExp;
		}

	}

	/***************************************************************************
	 * This method is used to show the Header Information
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @table , table object
	 * @document, document
	 * @param UserID
	 * @param Spec
	 *            type
	 * @param CustomerName
	 * @param Order
	 *            Number
	 * @param Flag,Model
	 *            Name,Quantity
	 * @throws Exception
	 * @author VV49326
	 **************************************************************************/

	/**
	 * @param header
	 * @param document
	 * @param strSpecType
	 * @param strCustomerName
	 * @param strCustomerCode
	 * @param strOrderNo
	 * @param strModelName
	 * @param intQuantity
	 * @param strFlag
	 * @throws Exception
	 */
	private static void showHeaderInformation(PdfPTable header,
			Document document, String strSpecType, String strCustomerName,
			String strCustomerCode, String strOrderNo, String strModelName,
			int intQuantity, String strFlag) throws Exception {

		LogUtil
				.logMessage("Inside GenerateProofReadingDraft.showHeaderInformation");
		
		String strQuantity = "";
		String strHeaderType = "";
		FileVO objFileVO = new FileVO();
		FileVO objjFileVO = new FileVO();
		try {
			FileUploadBI objFileUploadBO = ServiceFactory.getFileUploadBO();
			DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
			String strDate = df.format(new Date());

			if (intQuantity != 0)
				strQuantity = "(" + intQuantity + ") ";

			PdfPCell cel = new PdfPCell(new Paragraph(strDate,
					strFointSizeNoBold));
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(10);
			header.addCell(cel);

			cel = new PdfPCell(new Phrase("\n"));
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(10);
			header.addCell(cel);
			//Modified for LSDB_CR_84 for addition of new Specification types
			if (intSpecTypeSeqNo == 2) {
				strHeaderType = "ENGINE SPECIFICATION";
			}
			//Modified for CR_101 for LXO Specification Type
			else if (intSpecTypeSeqNo == 3){
				strHeaderType = "LOCOMOTIVE EXPORT ORDER KIT SPECIFICATION";
			}
			else{
				strHeaderType = "LOCOMOTIVE SPECIFICATION";
			}
			//CR_101 Ends here	
			cel = new PdfPCell(new Paragraph(strHeaderType + " – "
					+ strSpecType, strFontNoUnderLine));
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(10);
			header.addCell(cel);

			cel = new PdfPCell(new Paragraph("Covering", strFointSizeNoBold));
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(10);
			header.addCell(cel);

			// Distributor will be displayed for PM&I Spec
			//Modified for LSDB_CR_84 for addition of new Specification types
			if (strSpecDistributorMapFlag.equalsIgnoreCase(ApplicationConstants.YES)) {
			//if (intSpecTypeSeqNo == 2) {
				cel = new PdfPCell(new Paragraph(strDistributorName,
						strFointSizeNoBold));
				cel.setHorizontalAlignment(Element.ALIGN_CENTER);
				cel.setBorderColor(new Color(255, 255, 255));
				cel.setColspan(10);
				header.addCell(cel);
				// CR 87
				//strCustomerName = "for the " + strCustomerName;
//				Added For CR_106
				if(intDistImageSeqNo!=0 && strDistriLogoFlag.equalsIgnoreCase("Y")){
					
				objFileVO.setImageSeqNo(intDistImageSeqNo);
				objjFileVO = objFileUploadBO.downloadImage(objFileVO);
				Image image = null;
				byte[] imageView = fetchGenArrangment(objjFileVO);
				if (imageView != null) {
					cel = new PdfPCell(new Phrase("\n"));
					cel.setBorderColor(new Color(255, 255, 255));
					cel.setColspan(10);
					header.addCell(cel);
					image = Image.getInstance(imageView);
					image.setAlignment(Element.ALIGN_CENTER);
					cel = new PdfPCell(image);
					cel.setHorizontalAlignment(Element.ALIGN_CENTER);
					cel.setBorderColor(new Color(255, 255, 255));
					cel.setColspan(10);
					header.addCell(cel);;
					cel = new PdfPCell(new Phrase("\n"));
					cel.setBorderColor(new Color(255, 255, 255));
					cel.setColspan(10);
					header.addCell(cel);
				} /*else {
					cel = new PdfPCell();
					cel.setBorderColor(new Color(255, 255, 255));
					cel.setColspan(10);
					header.addCell(cel);
				}
			}else {
				cel = new PdfPCell();
				cel.setBorderColor(new Color(255, 255, 255));
				cel.setColspan(10);
				cel.setColspan(10);
				header.addCell(cel);;*/
			}
				
				strCustomerName = strCustomerName;
			}

			// Ends

			cel = new PdfPCell(new Paragraph(strCustomerName,
					strFointSizeNoBold));
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(10);
			header.addCell(cel);
			
			//Added For CR_106
			if(intCustImageSeqNo!=0 && strCustLogoFlag.equalsIgnoreCase("Y")){
				
			objFileVO.setImageSeqNo(intCustImageSeqNo);
			objjFileVO = objFileUploadBO.downloadImage(objFileVO);
			Image image = null;
			byte[] imageView = fetchGenArrangment(objjFileVO);
			if (imageView != null) {
				cel = new PdfPCell(new Phrase("\n"));
				cel.setBorderColor(new Color(255, 255, 255));
				cel.setColspan(10);
				header.addCell(cel);
				image = Image.getInstance(imageView);
     			image.setAlignment(Element.ALIGN_CENTER);
				cel = new PdfPCell(image);
				cel.setHorizontalAlignment(Element.ALIGN_CENTER);
				cel.setBorderColor(new Color(255, 255, 255));
				cel.setColspan(10);
				header.addCell(cel);
				cel = new PdfPCell(new Phrase("\n"));
				cel.setBorderColor(new Color(255, 255, 255));
				cel.setColspan(10);
				header.addCell(cel);
			}/* else {
				cel = new PdfPCell();
				cel.setBorderColor(new Color(255, 255, 255));
				//tloc.addCell(cel);
				cel.setColspan(10);
				header.addCell(cel);
			}
		}else {
			cel = new PdfPCell();
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(10);
			//tloc.addCell(cel);
			cel.setColspan(10);
			header.addCell(cel);;*/
		}
			
			
			

			cel = new PdfPCell(new Paragraph(strQuantity + strModelName,
					strFointSizeNoBold));
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(10);
			header.addCell(cel);

			cel = new PdfPCell(new Paragraph("EMD Customer Code No. "
					+ strCustomerCode, strFointSizeNoBold));
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(10);
			header.addCell(cel);

			cel = new PdfPCell(new Paragraph("EMD Order No. " + strOrderNo,
					strFontUnderLine));
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(10);
			header.addCell(cel);

			cel = new PdfPCell(new Phrase("\n"));
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(10);
			header.addCell(cel);

			document.add(header);
		} catch (Exception objExp) {
			throw objExp;
		}

	}

	/***************************************************************************
	 * This method is used to show the General Information
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @table table object
	 * @document, document
	 * @param UserID
	 * @param OrderKey
	 * @param PdfType
	 * @param RevisionCode
	 * @param Flag
	 * @param HttpServletResponse
	 * @throws Exception
	 * @author VV49326
	 **************************************************************************/

	private static void showGeneralInformation(PdfPTable header,
			Document document, int intOrderKey, String customerName,
			PdfWriter writer, String strUserID, String strFlag, int intRevCode)
			throws Exception {

		LogUtil
				.logMessage("Inside GenerateProofReadingDraft.showGeneralInformation");
		ArrayList arlMainFeaList = new ArrayList();
		ArrayList arlOrderDefComps = new ArrayList();

		try {
			MainFeatureInfoBI objMainFeatureInfoBI = ServiceFactory
					.getMainfeatureInfoBO();
			MainFeatureInfoVO objMainFeatureInfoVO = new MainFeatureInfoVO();

			header = new PdfPTable(25);
			header.setWidthPercentage(100);

			// Added for CR-76 VV49326
			String strSysMarkFlag = "";
			String strSysMarkDesc = "";
			PdfPTable marker = new PdfPTable(3);

			PdfPCell cel = new PdfPCell(new Paragraph("", strFontNoUnderLine));
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(2);
			header.addCell(cel);

			cel = new PdfPCell(new Paragraph("I.", strFontNoUnderLine));
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel.setColspan(1);
			cel.setBorderColor(new Color(255, 255, 255));
			header.addCell(cel);

			cel = new PdfPCell(new Paragraph("GENERAL INFORMATION:",
					strFontUnderLine));
			cel.setHorizontalAlignment(Element.ALIGN_LEFT);
			cel.setColspan(20);
			cel.setBorderColor(new Color(255, 255, 255));
			header.addCell(cel);

			cel = new PdfPCell(new Paragraph("", strFointSizeNoBold));
			cel.setHorizontalAlignment(Element.ALIGN_LEFT);
			cel.setColspan(2);
			cel.setBorderColor(new Color(255, 255, 255));
			header.addCell(cel);

			cel = new PdfPCell(new Phrase("\n"));// new line add
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(25);
			header.addCell(cel);

			cel = new PdfPCell(new Paragraph("", strFointSizeNoBold));
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(3);
			header.addCell(cel);
			
			//Edited For CR_104 to bring Custom Model Name and General Info Text
			
			//Added for CR_99 to change the General Information
				
			/*if (intStatusCode>2)
			{			
			if (intSpecTypeSeqNo == 2) {
				cel = new PdfPCell(
						new Paragraph(
								"This specification is issued by Electro-Motive's Inquiry Group to authorize construction of this engine in accordance with the specific options and modifications as detailed herein and mutually agreed to with Electro-Motive Diesel,Inc.",
								strFointSizeNoBold));

			} else {
				cel = new PdfPCell(
						new Paragraph(
								"This specification is issued by Electro-Motive's Locomotive Inquiry Group to authorize construction of these locomotives in accordance with the specific options and modifications as detailed herein and mutually agreed to between "
										+ customerName
										+ " and Electro-Motive Diesel.",
								strFointSizeNoBold));
			}
			}
			else if (intStatusCode==1) {
				if (intSpecTypeSeqNo == 2) {
					cel = new PdfPCell(
							new Paragraph(
									"This DRAFT specification is issued by Electro-Motive's Power Products Inquiry Group to provide a specification of the Power Products thet EMD will design for "
										+ customerName
										+ ". This DRAFT specification is a product description of the EMD "
										+ strHorsePower
										+ " Power Product being offered. A final specification will be issued for submittal with the contract once a settlement of technical details with "
										+ customerName
										+" has taken place.",
									strFointSizeNoBold));

				} else {
					cel = new PdfPCell(
							new Paragraph(
									"This DRAFT specification is issued by Electro-Motive's Locomotive Inquiry Group to provide a specification of these locomotives that EMD will design for "
											+ customerName
											+ ". This DRAFT specification is a product description of the EMD "
											+ strHorsePower
											+" being offered. A final specification will be issued for submittal with the contract once a settlement of technical details with "
											+ customerName
											+" has taken place.",
									strFointSizeNoBold));
				}
			}
			else if (intStatusCode==2) {
				if (intSpecTypeSeqNo == 2) {
					cel = new PdfPCell(
							new Paragraph(
									"This PRELIMINARY specification is issued by Electro-Motive's Power Products Inquiry Group to provide a specification of the Power Products thet EMD will design for "
										+customerName
										+". This PRELIMINARY specification is a product description of the EMD "
										+ strHorsePower
										+" Power Product being offered. A final specification will be issued for submittal with the contract once a settlement of technical details with "
										+customerName
										+" has taken place.",
									strFointSizeNoBold));

				} else {
					cel = new PdfPCell(
							new Paragraph(
									"This PRELIMINARY specification is issued by Electro-Motive's Locomotive Inquiry Group to provide a specification of these locomotives that EMD will design for "
											+ customerName
											+ ". This PRELIMINARY specification is a product description of the EMD "
											+ strHorsePower
											+" being offered. A final specification will be issued for submittal with the contract once a settlement of technical details with "
											+customerName
											+" has taken place.",
									strFointSizeNoBold));
				}
			}*/
			
			cel = new PdfPCell(new Paragraph(strGenInfoText,strFointSizeNoBold));
				
			//CR_99 Changes Ends Here.
			//CR_104 Changes Ends here.
			
			cel.setHorizontalAlignment(Element.ALIGN_LEFT);
			cel.setColspan(20);
			cel.setBorderColor(new Color(255, 255, 255));
			header.addCell(cel);

			cel = new PdfPCell(new Paragraph("", strFointSizeNoBold));
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel.setColspan(2);
			cel.setBorderColor(new Color(255, 255, 255));
			header.addCell(cel);

			cel = new PdfPCell(new Paragraph("", strFointSizeNoBold));
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(3);
			header.addCell(cel);
			//Modified for LSDB_CR_84 for addition of new Specification types
			if (intSpecTypeSeqNo == 2) {
				cel = new PdfPCell(new Paragraph(
						"This order provides the following main features:",
						strFointSizeNoBold));

			} else {
				cel = new PdfPCell(
						new Paragraph(
								"This locomotive is equipped with the following main features:",
								strFointSizeNoBold));
			}
			cel.setHorizontalAlignment(Element.ALIGN_LEFT);
			cel.setColspan(22);
			cel.setBorderColor(new Color(255, 255, 255));
			header.addCell(cel);

			cel = new PdfPCell(new Phrase("\n"));
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(25);
			header.addCell(cel);

			objMainFeatureInfoVO.setOrderKey(intOrderKey);
			objMainFeatureInfoVO
					.setDataLocationType(DatabaseConstants.DATALOCATION);
			objMainFeatureInfoVO.setUserID(strUserID);
			// Added for LSDB_CR-74
			objMainFeatureInfoVO.setRevCode(intRevCode);

			// To fetch Model General Info
			arlOrderDefComps = objMainFeatureInfoBI
					.fetchMainFeatures(objMainFeatureInfoVO);
			LogUtil.logMessage("Oder Comp List Size" + arlOrderDefComps.size());

			StringBuffer sbRevCode = null;
			// To fetch Order General Info
//			commented for CR_93
//			objMainFeatureInfoVO.setDisplayInSpec(true);
//			// Added for LSDB_CR-74
//			objMainFeatureInfoVO.setRevCode(intRevCode);
//			arlMainFeaList = objMainFeatureInfoBI
//					.fetchModelMainFeatures(objMainFeatureInfoVO);
//
//			if (arlMainFeaList != null && arlMainFeaList.size() > 0) {
//
//				for (int i = 0; i < arlMainFeaList.size(); i++) {
//					MainFeatureInfoVO objjMainFeatureInfoVO = (MainFeatureInfoVO) arlMainFeaList
//							.get(i);
//
//					// Added for CR-76 VV49326
//					strSysMarkFlag = "";
//					strSysMarkDesc = "";
//					strSysMarkFlag = objjMainFeatureInfoVO.getSysMarkFlag();
//					strSysMarkDesc = objjMainFeatureInfoVO.getSysMarkDesc();
//					marker = new PdfPTable(3);
//
//					// Added for LSDB_CR-74 by KA57588
//					ArrayList arlRevMarker = (ArrayList) objjMainFeatureInfoVO
//							.getRevMarkers();
//					sbRevCode = new StringBuffer();
//
//					// Added for CR-76
//					if (strSysMarkFlag != null && !"".equals(strSysMarkDesc)) {
//
//						if (strSysMarkFlag.equalsIgnoreCase("Y")) {
//
//							cel = new PdfPCell(new Paragraph("",
//									strFointSizeBoldNineRed));
//							cel.setHorizontalAlignment(Element.ALIGN_LEFT);
//							cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
//							cel.setBorderColor(new Color(255, 255, 255));
//							cel.setColspan(1);
//							marker.addCell(cel);
//
//							cel = new PdfPCell(new Paragraph(strSysMarkDesc,
//									strFointSizeBoldSixBlack));
//							cel.setBorderColor(new Color(255, 255, 255));
//							cel.setBackgroundColor(new Color(59, 185, 255));
//							cel.setHorizontalAlignment(Element.ALIGN_CENTER);
//							cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
//							cel.setColspan(2);
//							setCellPadding(cel);
//							marker.addCell(cel);
//						}
//
//					} else {
//
//						cel = new PdfPCell(new Paragraph("",
//								strFointSizeBoldNineRed));
//						cel.setHorizontalAlignment(Element.ALIGN_LEFT);
//						cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
//						cel.setBorderColor(new Color(255, 255, 255));
//						cel.setColspan(1);
//						marker.addCell(cel);
//
//						cel = new PdfPCell(new Paragraph("",
//								strFointSizeBoldNineRed));
//						cel.setHorizontalAlignment(Element.ALIGN_CENTER);
//						cel.setBorderColor(new Color(255, 255, 255));
//						cel.setColspan(2);
//						marker.addCell(cel);
//					}
//
//					if (arlRevMarker != null && arlRevMarker.size() > 0) {
//
//						for (int j = 0; j < arlRevMarker.size(); j++) {
//
//							sbRevCode.append((String) arlRevMarker.get(j));
//							if (j < arlRevMarker.size() - 1)
//								sbRevCode.append("\n");
//						}
//						cel = new PdfPCell(new Paragraph("",
//								strFointSizeBoldNineRed));
//						cel.setHorizontalAlignment(Element.ALIGN_CENTER);
//						cel.setBorderColor(new Color(255, 255, 255));
//						cel.setColspan(1);
//						// Changed for CR-76
//						marker.addCell(cel);
//
//						cel = new PdfPCell(new Paragraph(sbRevCode.toString(),
//								strFointSizeBoldNineRed));
//						cel.setHorizontalAlignment(Element.ALIGN_LEFT);
//						cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
//						cel.setBorderColor(new Color(255, 255, 255));
//						// Changed for CR-76
//						cel.setColspan(2);
//						marker.addCell(cel);
//					} else {
//						cel = new PdfPCell(new Paragraph("",
//								strFointSizeBoldNineRed));
//						cel.setHorizontalAlignment(Element.ALIGN_CENTER);
//						cel.setBorderColor(new Color(255, 255, 255));
//						cel.setColspan(1);
//						// Changed for CR-76
//						marker.addCell(cel);
//
//						cel = new PdfPCell(new Paragraph("",
//								strFointSizeBoldNineRed));
//						cel.setHorizontalAlignment(Element.ALIGN_CENTER);
//						cel.setBorderColor(new Color(255, 255, 255));
//						// Changed for CR-76
//						cel.setColspan(2);
//						marker.addCell(cel);
//
//					}
//					// Ends
//					// Added for CR-76
//					cel = new PdfPCell(marker);
//					cel.setHorizontalAlignment(Element.ALIGN_CENTER);
//					cel.setBorderColor(new Color(255, 255, 255));
//					cel.setColspan(3);
//					header.addCell(cel);
//
//					// Model level
//					if (objjMainFeatureInfoVO.getCompDesc() != null) {
//
//						/*
//						 * cel = new PdfPCell( new Paragraph("",
//						 * strFointSizeNoBold));
//						 * cel.setHorizontalAlignment(Element.ALIGN_CENTER);
//						 * cel.setBorderColor(new Color(255, 255, 255));
//						 * cel.setColspan(1); header.addCell(cel);
//						 */
//
//						cel = new PdfPCell(new Paragraph("-"
//								+ objjMainFeatureInfoVO.getCompDesc(),
//								strFointSizeNoBold));
//						cel.setHorizontalAlignment(Element.ALIGN_LEFT);
//						cel.setColspan(22);
//						cel.setBorderColor(new Color(255, 255, 255));
//						header.addCell(cel);
//
//					}
//				}
//				// Ends
//
//			}

			if (arlOrderDefComps != null && arlOrderDefComps.size() > 0) {

				for (int k = 0; k < arlOrderDefComps.size(); k++) {

					MainFeatureInfoVO objjMainFeatureInfoVo = (MainFeatureInfoVO) arlOrderDefComps
							.get(k);
					// Added for CR-76 VV49326
					strSysMarkFlag = "";
					strSysMarkDesc = "";
					strSysMarkFlag = objjMainFeatureInfoVo.getSysMarkFlag();
					strSysMarkDesc = objjMainFeatureInfoVo.getSysMarkDesc();
					marker = new PdfPTable(3);

					// Added for LSDB_CR-74 by KA57588
					ArrayList arlRevMarker = (ArrayList) objjMainFeatureInfoVo
							.getRevMarkers();
					sbRevCode = new StringBuffer();

					// Added for CR-76
					if (strSysMarkFlag != null && !"".equals(strSysMarkDesc)) {

						if (strSysMarkFlag.equalsIgnoreCase("Y")) {

							cel = new PdfPCell(new Paragraph("",
									strFointSizeBoldNineRed));
							cel.setHorizontalAlignment(Element.ALIGN_LEFT);
							cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cel.setBorderColor(new Color(255, 255, 255));
							// Changed for CR-76
							cel.setColspan(1);
							marker.addCell(cel);

							cel = new PdfPCell(new Paragraph(strSysMarkDesc,
									strFointSizeBoldSixBlack));
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setBackgroundColor(new Color(59, 185, 255));
							cel.setHorizontalAlignment(Element.ALIGN_CENTER);
							cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cel.setColspan(2);
							setCellPadding(cel);
							marker.addCell(cel);
						}

					} else {

						cel = new PdfPCell(new Paragraph("",
								strFointSizeBoldNineRed));
						cel.setHorizontalAlignment(Element.ALIGN_LEFT);
						cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cel.setBorderColor(new Color(255, 255, 255));
						cel.setColspan(1);
						marker.addCell(cel);

						cel = new PdfPCell(new Paragraph("",
								strFointSizeBoldNineRed));
						cel.setHorizontalAlignment(Element.ALIGN_CENTER);
						cel.setBorderColor(new Color(255, 255, 255));
						cel.setColspan(2);
						marker.addCell(cel);
					}

					if (arlRevMarker != null && arlRevMarker.size() > 0) {

						for (int j = 0; j < arlRevMarker.size(); j++) {

							sbRevCode.append((String) arlRevMarker.get(j));
							if (j < arlRevMarker.size() - 1)
								sbRevCode.append("\n");
						}

						cel = new PdfPCell(new Paragraph("",
								strFointSizeBoldNineRed));
						cel.setHorizontalAlignment(Element.ALIGN_CENTER);
						cel.setBorderColor(new Color(255, 255, 255));
						cel.setColspan(1);
						// Changed for CR-76
						marker.addCell(cel);

						cel = new PdfPCell(new Paragraph(sbRevCode.toString(),
								strFointSizeBoldNineRed));
						cel.setHorizontalAlignment(Element.ALIGN_LEFT);
						cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cel.setBorderColor(new Color(255, 255, 255));
						cel.setColspan(2);
						// Changed for CR-76
						marker.addCell(cel);
					} else {
						cel = new PdfPCell(new Paragraph("",
								strFointSizeBoldNineRed));
						cel.setHorizontalAlignment(Element.ALIGN_CENTER);
						cel.setBorderColor(new Color(255, 255, 255));
						cel.setColspan(1);
						// Changed for CR-76
						marker.addCell(cel);

						cel = new PdfPCell(new Paragraph("",
								strFointSizeBoldNineRed));
						cel.setHorizontalAlignment(Element.ALIGN_CENTER);
						cel.setBorderColor(new Color(255, 255, 255));
						cel.setColspan(2);
						// Changed for CR-76
						marker.addCell(cel);

					}
					// Ends

					// Added for CR-76
					cel = new PdfPCell(marker);
					cel.setHorizontalAlignment(Element.ALIGN_CENTER);
					cel.setBorderColor(new Color(255, 255, 255));
					cel.setColspan(3);
					header.addCell(cel);

					// Order level
					if (objjMainFeatureInfoVo.getComponentDesc() != null) {
						/*
						 * cel = new PdfPCell( new Paragraph("",
						 * strFointSizeNoBold));
						 * cel.setHorizontalAlignment(Element.ALIGN_CENTER);
						 * cel.setBorderColor(new Color(255, 255, 255));
						 * header.addCell(cel);
						 */
						cel = new PdfPCell(new Paragraph("-"
								+ objjMainFeatureInfoVo.getComponentDesc(),
								strFointSizeNoBold));
						cel.setHorizontalAlignment(Element.ALIGN_LEFT);
						cel.setColspan(22);
						cel.setBorderColor(new Color(255, 255, 255));
						header.addCell(cel);
					}

				}
				// Ends

			}

			document.add(header);

		} catch (Exception objExp) {

			throw objExp;
			// Ends
		}
	}

	/***************************************************************************
	 * This method is used to show the Specification Information
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param table
	 *            object
	 * @param document
	 * @param UserID
	 * @param Order
	 *            Key
	 * @param Model
	 *            Name
	 * @param Order
	 *            Number
	 * @param Flag
	 * @throws Exception
	 * @author VV49326
	 **************************************************************************/

	private static void showSpecification(PdfPTable table, Document document,
			int intOrderKey, String strModelName, String strUserID,
			String strFlag, int intRevCode) throws Exception {
		LogUtil
				.logMessage("Inside GenerateProofReadingDraft.showSpecification");
		SpecificationItemVO objSpecificationitemVO = null;
		SpecificationVO objSpecVO = null;
		//String strHorsePower = "";
		ArrayList arlSpecList = new ArrayList();
		StringBuffer sbHPRating = null;
		StringBuffer sbSpecItem = null;
		// Added for CR-76
		String strHpSysMarkFlag = "";
		String strHpSysMarkDesc = "";
		String strItemMarkFlag = "";
		String strItemMarkDesc = "";
		PdfPTable marker = new PdfPTable(3);

		try {

			OrderSpecificationBI objOrderSpecificationBO = ServiceFactory
					.getOrderSpecificationBO();
			SpecificationVO objSpecificationVO = new SpecificationVO();

			document.newPage();
			table = new PdfPTable(25);
			table.setWidthPercentage(100);

			PdfPCell cel = new PdfPCell(new Paragraph("", strFontNoUnderLine));
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(3);
			;
			table.addCell(cel);

			cel = new PdfPCell(new Paragraph(strModelName
					+ " GENERAL INFORMATION AND IDENTIFICATION",
					strFontNoUnderLine));
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(22);
			table.addCell(cel);
			document.add(table);

			// To fetch Specification Items

			objSpecificationVO.setOrderKey(intOrderKey);
			objSpecificationVO
					.setDataLocationType(DatabaseConstants.DATALOCATION);
			// Added for LSDB_CR-74 by Ka57588 -- Starts
			objSpecificationVO.setRevCode(intRevCode);
			// Ends
			objSpecificationVO.setUserID(strUserID);
			arlSpecList = objOrderSpecificationBO
					.fetchSpecificationItems(objSpecificationVO);
			if (arlSpecList != null && arlSpecList.size() > 0) {

				for (int i = 0; i < arlSpecList.size(); i++) {
					objSpecVO = (SpecificationVO) arlSpecList.get(i);
					ArrayList arlSpecitem = new ArrayList();

					if (i == 0) {
						strHorsePower = objSpecVO.getHpDesc();
						table = new PdfPTable(25);
						table.setWidthPercentage(100);
						sbHPRating = new StringBuffer();

						// Added for CR-76
						strHpSysMarkFlag = objSpecVO.getHpSysMarkFlag();
						strHpSysMarkDesc = objSpecVO.getHpSysMarkDesc();

						marker = new PdfPTable(3);
						// Added for CR-76
						if (strHpSysMarkFlag != null
								&& strHpSysMarkFlag.equalsIgnoreCase("Y")) {

							cel = new PdfPCell(new Paragraph("",
									strFointSizeBoldNineRed));
							cel.setHorizontalAlignment(Element.ALIGN_LEFT);
							cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cel.setBorderColor(new Color(255, 255, 255));
							// Changed for CR-76
							cel.setColspan(1);
							marker.addCell(cel);

							cel = new PdfPCell(new Paragraph(strHpSysMarkDesc,
									strFointSizeBoldSixBlack));
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setBackgroundColor(new Color(59, 185, 255));
							cel.setHorizontalAlignment(Element.ALIGN_CENTER);
							cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cel.setColspan(2);
							setCellPadding(cel);
							marker.addCell(cel);

						} else {

							cel = new PdfPCell(new Paragraph("",
									strFointSizeBoldNineRed));
							cel.setHorizontalAlignment(Element.ALIGN_LEFT);
							cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setColspan(1);
							marker.addCell(cel);

							cel = new PdfPCell(new Paragraph("",
									strFointSizeBoldNineRed));
							cel.setHorizontalAlignment(Element.ALIGN_CENTER);
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setColspan(2);
							marker.addCell(cel);
						}

						// Added for LSDB_CR-74 by Ka57588 -- Starts
						ArrayList arlhorsePower = (ArrayList) objSpecVO
								.getHpRatingMarkers();
						if (arlhorsePower != null && arlhorsePower.size() > 0) {

							for (int j = 0; j < arlhorsePower.size(); j++) {

								sbHPRating
										.append((String) arlhorsePower.get(j));
								if (j < arlhorsePower.size() - 1)
									sbHPRating.append("\n");
							}

							cel = new PdfPCell(new Paragraph("",
									strFointSizeBoldNineRed));
							cel.setHorizontalAlignment(Element.ALIGN_CENTER);
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setColspan(1);
							// Changed for CR-76
							marker.addCell(cel);

							cel = new PdfPCell(new Paragraph(sbHPRating
									.toString(), strFointSizeBoldNineRed));
							cel.setHorizontalAlignment(Element.ALIGN_LEFT);
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setColspan(2);
							// Changed for CR-76
							marker.addCell(cel);
						} else {
							cel = new PdfPCell(new Paragraph("",
									strFointSizeBoldNineRed));
							cel.setHorizontalAlignment(Element.ALIGN_CENTER);
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setColspan(1);
							// Changed for CR-76
							marker.addCell(cel);

							cel = new PdfPCell(new Paragraph("",
									strFointSizeBoldNineRed));
							cel.setHorizontalAlignment(Element.ALIGN_CENTER);
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setColspan(2);
							// Changed for CR-76
							marker.addCell(cel);

						}
						// Ends

						// Added for CR-76
						cel = new PdfPCell(marker);
						cel.setHorizontalAlignment(Element.ALIGN_CENTER);
						cel.setBorderColor(new Color(255, 255, 255));
						cel.setColspan(3);
						table.addCell(cel);

						cel = new PdfPCell(new Paragraph(strHorsePower,
								strFointSizeNoBold));
						cel.setBorderColor(new Color(255, 255, 255));
						cel.setColspan(22);
						table.addCell(cel);
						document.add(table);

					}

					// This is for adding newline space
					table = new PdfPTable(25);
					table.setWidthPercentage(100);
					cel = new PdfPCell(new Paragraph("", strFontSizeNoBoldTen));// new
					// line
					// add
					cel.setBorderColor(new Color(255, 255, 255));
					cel.setColspan(25);
					table.addCell(cel);
					document.add(table);

					table = new PdfPTable(25);
					table.setWidthPercentage(100);

					cel = new PdfPCell(new Paragraph("",
							strFointSizeBoldNineRed));
					cel.setHorizontalAlignment(Element.ALIGN_CENTER);
					cel.setBorderColor(new Color(255, 255, 255));
					cel.setColspan(1);
					table.addCell(cel);

					cel = new PdfPCell(new Paragraph("",
							strFointSizeBoldNineRed));
					cel.setHorizontalAlignment(Element.ALIGN_CENTER);
					cel.setBorderColor(new Color(255, 255, 255));
					cel.setColspan(2);
					table.addCell(cel);

					cel = new PdfPCell(new Paragraph(objSpecVO.getSpecName(),
							strFontBOLD));
					cel.setBorderColor(new Color(255, 255, 255));
					cel.setVerticalAlignment(Element.ALIGN_TOP);
					cel.setLeading(0f, 1.5f);// Added for Underline issue
					cel.setColspan(22);
					table.addCell(cel);

					// addNewLine(table);
					document.add(table);
					arlSpecitem = objSpecVO.getSpecItem();

					for (int cnt = 0; cnt < arlSpecitem.size(); cnt++) {
						objSpecificationitemVO = (SpecificationItemVO) arlSpecitem
								.get(cnt);

						table = new PdfPTable(25);
						table.setWidthPercentage(100);
						sbSpecItem = new StringBuffer();
						// Added for CR-76
						strItemMarkFlag = "";
						strItemMarkDesc = "";
						marker = new PdfPTable(3);
						strItemMarkFlag = objSpecificationitemVO
								.getSysMarkFlag();
						strItemMarkDesc = objSpecificationitemVO
								.getSysMarkDesc();

						if (strItemMarkFlag != null
								&& strItemMarkFlag.equalsIgnoreCase("Y")) {

							cel = new PdfPCell(new Paragraph("",
									strFointSizeBoldNineRed));
							cel.setHorizontalAlignment(Element.ALIGN_LEFT);
							cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cel.setBorderColor(new Color(255, 255, 255));
							// Changed for CR-76
							cel.setColspan(1);
							marker.addCell(cel);

							cel = new PdfPCell(new Paragraph(strItemMarkDesc,
									strFointSizeBoldSixBlack));
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setBackgroundColor(new Color(59, 185, 255));
							cel.setHorizontalAlignment(Element.ALIGN_CENTER);
							cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cel.setColspan(2);
							setCellPadding(cel);
							marker.addCell(cel);

						} else {

							cel = new PdfPCell(new Paragraph("",
									strFointSizeBoldNineRed));
							cel.setHorizontalAlignment(Element.ALIGN_LEFT);
							cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setColspan(1);
							marker.addCell(cel);

							cel = new PdfPCell(new Paragraph("",
									strFointSizeBoldNineRed));
							cel.setHorizontalAlignment(Element.ALIGN_CENTER);
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setColspan(2);
							marker.addCell(cel);
						}

						// Added for LSDB_CR-74 by Ka57588 -- Starts
						ArrayList arlSpecItem = (ArrayList) objSpecificationitemVO
								.getItemMarker();

						if (arlSpecItem != null && arlSpecItem.size() > 0) {
							for (int j = 0; j < arlSpecItem.size(); j++) {
								sbSpecItem.append((String) arlSpecItem.get(j));
								if (j < arlSpecItem.size() - 1)
									sbSpecItem.append("\n");
							}

							cel = new PdfPCell(new Paragraph("",
									strFointSizeBoldNineRed));
							cel.setHorizontalAlignment(Element.ALIGN_CENTER);
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setColspan(1);
							// Changed for CR-76
							marker.addCell(cel);

							cel = new PdfPCell(new Paragraph(sbSpecItem
									.toString(), strFointSizeBoldNineRed));
							cel.setHorizontalAlignment(Element.ALIGN_LEFT);
							cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setColspan(2);
							// Changed for CR-76
							marker.addCell(cel);

						} else {

							cel = new PdfPCell(new Paragraph("",
									strFointSizeBoldNineRed));
							cel.setHorizontalAlignment(Element.ALIGN_CENTER);
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setColspan(1);
							// Changed for CR-76
							marker.addCell(cel);

							cel = new PdfPCell(new Paragraph("",
									strFointSizeBoldNineRed));
							cel.setHorizontalAlignment(Element.ALIGN_LEFT);
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setColspan(2);
							// Changed for CR-76
							marker.addCell(cel);

						}
						// Ends

						// Added for CR-76
						cel = new PdfPCell(marker);
						cel.setHorizontalAlignment(Element.ALIGN_CENTER);
						cel.setBorderColor(new Color(255, 255, 255));
						cel.setColspan(3);
						table.addCell(cel);

						cel = new PdfPCell(new Paragraph(objSpecificationitemVO
								.getItemDesc(), strFointSizeNoBold));
						cel.setBorderColor(new Color(255, 255, 255));
						cel.setColspan(14);
						table.addCell(cel);

						cel = new PdfPCell(new Paragraph(objSpecificationitemVO
								.getItemValue(), strFontSizeNoBoldTen));
						cel.setBorderColor(new Color(255, 255, 255));
						cel.setColspan(8);
						table.addCell(cel);
						document.add(table);

					}

				}
			}
		} catch (Exception objExp) {

			throw objExp;
		}

	}

	/***************************************************************************
	 * This method is used to show the General Arrangement Information
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param table
	 *            object
	 * @param document
	 * @param UserID
	 * @param Order
	 *            Key
	 * @param Flag
	 * @throws Exception
	 * @author VV49326
	 **************************************************************************/

	private static void showGeneralArrangement(Document document,
			int intOrderKey, String strUserID, String strFlag, int intRevCode,
			PdfWriter writer) throws Exception {

		LogUtil
				.logMessage("Inside GenerateProofReadingDraft.showGeneralArrangement");
		ArrayList arlGenArngmtList = new ArrayList();
		
		String[] arlModelViewNotes = { "", "", "","", "", "","" };
		
		int intTopViewSeqNo = 0;
		int intFrontViewSeqNo = 0;
		int intSideViewSeqNo = 0;
		int cnt=0;
		int loccnt = 0;
		FileVO objFileVO = new FileVO();
		FileVO objjFileVO = new FileVO();
		StringBuffer sbGenArrangement = new StringBuffer();
		StringBuffer sbGenArrangeNotes = null;
		Set colGenArrangement = new HashSet(); // Eliminate Duplicate Elements
		// Added for CR-76
		String strImgMarkFlag = "";
		String strImgMarkDesc = "";
		String strNotesMarkFlag = "";
		String strNotesMarkDesc = "";		
		int[] intAdtlGenArrSeqNo = new int[4];
		try {

			OrderGenArrangeBI objOrderGenArrBO = ServiceFactory
					.getOrderGenArrBO();
			GenArrangeVO objGenArrangeVO = new GenArrangeVO();

			FileUploadBI objFileUploadBO = ServiceFactory.getFileUploadBO();

			PdfPTable pdftloc = new PdfPTable(2);
			pdftloc.setWidthPercentage(85);

			Cell cel = new Cell();
			Table tloc = new Table(2);
			tloc.setTableFitsPage(true);
			document.newPage();

			// Added for CR-76
			PdfPTable marker = new PdfPTable(10);
			float[] img = { 25, 75 };
			Table imgMark = new Table(2);
			imgMark.setWidths(img);
			// PdfPTable notesMarker = new PdfPTable(10);

			// To fetch Order General Arrangements
			objGenArrangeVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objGenArrangeVO.setRevisionInput(intRevCode);
			objGenArrangeVO.setOrderKey(intOrderKey);
			objGenArrangeVO.setUserID(strUserID);

			arlGenArngmtList = objOrderGenArrBO
					.fetchGenArrImages(objGenArrangeVO);

			if (arlGenArngmtList != null && arlGenArngmtList.size() > 0) {

				for (cnt = 0; cnt < arlGenArngmtList.size(); cnt++) {
					GenArrangeVO objjGenArrangeVO = (GenArrangeVO) arlGenArngmtList
							.get(cnt);
					// Added for CR-76
					strNotesMarkFlag = objjGenArrangeVO.getNotesMarkFlag();
					strNotesMarkDesc = objjGenArrangeVO.getNotesMarkDesc();
                    //Changed after issue fix 14-08-09
					if (objjGenArrangeVO.getImgMarkFlag() != null
							&& (objjGenArrangeVO.getImgMarkFlag()
									.equalsIgnoreCase("Y"))) {

						strImgMarkFlag = objjGenArrangeVO.getImgMarkFlag();
						strImgMarkDesc = objjGenArrangeVO.getImgMarkDesc();
					}

					// Added for LSDB_CR-74 by KA57588 - Starts
					ArrayList arlGenArrangment = (ArrayList) objjGenArrangeVO
							.getRevCode();
					if (arlGenArrangment != null && arlGenArrangment.size() > 0) {

						for (int j = 0; j < arlGenArrangment.size(); j++) {

							colGenArrangement.add((String) arlGenArrangment
									.get(j));
						}
					}

					// Ends

					// To get Top View Image Seq No
					if (cnt == 0) {
						ArrayList arlNotes = (ArrayList) objjGenArrangeVO
								.getGnNotesRevCode();

						if (arlNotes != null && arlNotes.size() > 0) {
							sbGenArrangeNotes = new StringBuffer();
							for (int j = 0; j < arlNotes.size(); j++) {

								if (j == 0)
									sbGenArrangeNotes.append((String) arlNotes
											.get(j));
								else
									sbGenArrangeNotes.append(","
											+ (String) arlNotes.get(j));
							}

						}

						intTopViewSeqNo = objjGenArrangeVO.getFileVO()
								.getImageSeqNo();
						strGenArrangeNotes = objjGenArrangeVO
								.getGenArrangeNotes();
					}

					// To get Front View Image Seq No
					if (cnt == 1) {
						intFrontViewSeqNo = objjGenArrangeVO.getFileVO()
								.getImageSeqNo();
					}

					// To get Side View Image Seq No
					if (cnt == 2) {
						intSideViewSeqNo = objjGenArrangeVO.getFileVO()
								.getImageSeqNo();
					}
					
					// To get Additional Gen Arrngment Image Seq No
					if (cnt > 2) {
						LogUtil.logMessage("Inside Additional Gen Arrngment Loop : " + loccnt +
								objjGenArrangeVO.getFileVO()
								.getImageSeqNo());
						
						intAdtlGenArrSeqNo[loccnt] = objjGenArrangeVO.getFileVO()
						.getImageSeqNo();
						loccnt++;
					}
					arlModelViewNotes[cnt]=objjGenArrangeVO.getMdlViewNotes();
				}

				PdfPCell pdfCel = new PdfPCell(new Paragraph(strModelName
						+ " GENERAL INFORMATION AND IDENTIFICATION",
						strFontNoUnderLine));
				pdfCel.setBorderColor(new Color(255, 255, 255));
				pdfCel.setColspan(2);
				pdftloc.addCell(pdfCel);
				// addNewLine(pdftloc);
				// Changed for CR-76
				// document.add(pdftloc);

				// Added for LSDB_CR-74 by KA57588 -- Starts here
				SortedSet sortGenArrangment = new TreeSet(colGenArrangement);
				Iterator iteGenArrangement = sortGenArrangment.iterator();
				int indx = 0;
				while (iteGenArrangement.hasNext()) {

					if (indx == 0)
						sbGenArrangement.append((String) iteGenArrangement
								.next());
					else
						sbGenArrangement.append(","
								+ (String) iteGenArrangement.next());

					indx++;
				}

				// Added for CR-76
				if (strImgMarkFlag != null
						&& strImgMarkFlag.equalsIgnoreCase("Y")) {

					PdfPCell c = new PdfPCell(new Paragraph(strImgMarkDesc,
							strFointSizeBoldSixBlack));
					c.setBorderColor(new Color(255, 255, 255));
					c.setBackgroundColor(new Color(59, 185, 255));
					c.setHorizontalAlignment(Element.ALIGN_CENTER);
					c.setVerticalAlignment(Element.ALIGN_MIDDLE);
					c.setColspan(1);
					setCellPadding(c);
					marker.addCell(c);

					c = new PdfPCell(new Paragraph("", strFointSizeBoldNineRed));
					c.setHorizontalAlignment(Element.ALIGN_LEFT);
					c.setVerticalAlignment(Element.ALIGN_MIDDLE);
					c.setBorderColor(new Color(255, 255, 255));
					c.setColspan(9);
					marker.addCell(c);

				} else {

					PdfPCell c = new PdfPCell(new Paragraph("",
							strFointSizeBoldNineRed));
					c.setHorizontalAlignment(Element.ALIGN_LEFT);
					c.setVerticalAlignment(Element.ALIGN_MIDDLE);
					c.setBorderColor(new Color(255, 255, 255));
					c.setColspan(10);
					marker.addCell(c);

				}

				if (sbGenArrangement != null && sbGenArrangement.length() > 0) {
					pdfCel = new PdfPCell(new Paragraph("["
							+ sbGenArrangement.toString() + "]",
							strFointSizeBoldNineRed));
					pdfCel.setBorderColor(new Color(255, 255, 255));
					pdfCel.setLeading(0f, 1.0f);
					// Changed for CR-76
					pdfCel.setColspan(10);
					marker.addCell(pdfCel);
				} else {
					pdfCel = new PdfPCell(new Paragraph("",
							strFointSizeBoldNineRed));
					pdfCel.setBorderColor(new Color(255, 255, 255));
					pdfCel.setLeading(0f, 1.0f);
					// Changed for CR-76
					pdfCel.setColspan(10);
					marker.addCell(pdfCel);
				}
				// Added for CR-76
				pdfCel = new PdfPCell(marker);
				pdfCel.setHorizontalAlignment(Element.ALIGN_CENTER);
				pdfCel.setBorderColor(new Color(255, 255, 255));
				pdfCel.setColspan(2);
				pdftloc.addCell(pdfCel);

				document.add(pdftloc);
				// Ends here

				// To display Top View//use this for CR_106
				if (intTopViewSeqNo != 0) {
					objFileVO.setImageSeqNo(intTopViewSeqNo);
					objjFileVO = objFileUploadBO.downloadImage(objFileVO);
					Image image = null;
					byte[] imageView = fetchGenArrangment(objjFileVO);
					if (imageView != null) {
						image = Image.getInstance(imageView);
						image.setAbsolutePosition(140f, 620f);
						// The dimension (200*250) is set for max for the top
						// view
						// image if it exceeds.
						if (image.getWidth() > 200 || image.getHeight() > 250) {
							image.scaleToFit(200.0f, 250.0f);
						}
						// image.scalePercent(75f,80f);
						image.setAlignment(Element.ALIGN_CENTER);
						cel = new Cell(image);
						cel.setBorderColor(new Color(255, 255, 255));
						setTableProperties(tloc);
						tloc.addCell(cel);
					} else {
						cel = new Cell();
						cel.setBorderColor(new Color(255, 255, 255));
						tloc.addCell(cel);
					}
				}else {
					cel = new Cell();
					cel.setBorderColor(new Color(255, 255, 255));
					tloc.addCell(cel);
				}
				// To display Side View
				if (intSideViewSeqNo != 0) {
					objFileVO.setImageSeqNo(intSideViewSeqNo);
					objjFileVO = objFileUploadBO.downloadImage(objFileVO);
					Image image = null;
					byte[] imageView = fetchGenArrangment(objjFileVO);
					if (imageView != null) {
						image = Image.getInstance(imageView);
						image.setAbsolutePosition(380f, 330f);
						// The dimension (400*650) is set for max for the Side
						// view
						// image if it exceeds.
						if (image.getWidth() > 400 || image.getHeight() > 650) {
							image.scaleToFit(400.0f, 650.0f);
						}

						cel = new Cell(image);
						cel.setRowspan(7);
						cel.setBorderColor(new Color(255, 255, 255));
						setTableProperties(tloc);
						tloc.addCell(cel);
					} else {
						cel = new Cell();
						cel.setRowspan(7);
						cel.setBorderColor(new Color(255, 255, 255));
						tloc.addCell(cel);
					}					
				}else {
					cel = new Cell();
					cel.setRowspan(7);
					cel.setBorderColor(new Color(255, 255, 255));
					tloc.addCell(cel);
				}			
				
				//Updated for CR_101 to being General Arrangment Notes for all images
				cel = new Cell(new Phrase(11f,
						arlModelViewNotes[0], strFontNoUnderLine));
				cel.setBorderColor(new Color(255, 255, 255));
				cel.setHorizontalAlignment(Element.ALIGN_LEFT);
				cel.setVerticalAlignment(Element.ALIGN_TOP);
				tloc.addCell(cel);
				
				// To display Front View
				if (intFrontViewSeqNo != 0) {
					objFileVO.setImageSeqNo(intFrontViewSeqNo);
					objjFileVO = objFileUploadBO.downloadImage(objFileVO);
					Image image = null;
					byte[] imageView = fetchGenArrangment(objjFileVO);
					if (imageView != null) {
						image = Image.getInstance(imageView);
						image.setAbsolutePosition(140f, 300f);
						image.setAlignment(Element.ALIGN_CENTER);
						// The dimension (200*250) is set for max for the Front
						// view
						// image if it exceeds.
						if (image.getWidth() > 200 || image.getHeight() > 250) {
							image.scaleToFit(200.0f, 250.0f);
						}

						cel = new Cell(image);
						cel.setBorderColor(new Color(255, 255, 255));
						setTableProperties(tloc);
						tloc.addCell(cel);
					} else {
						cel = new Cell();
						cel.setBorderColor(new Color(255, 255, 255));
						tloc.addCell(cel);
					}
				}else {
					cel = new Cell();
					cel.setBorderColor(new Color(255, 255, 255));
					tloc.addCell(cel);
				}
				//Updated for CR_101 to being General Arrangment Notes for all images
				cel = new Cell(new Phrase(11f,
						arlModelViewNotes[1], strFontNoUnderLine));
				cel.setBorderColor(new Color(255, 255, 255));
				cel.setHorizontalAlignment(Element.ALIGN_LEFT);
				cel.setVerticalAlignment(Element.ALIGN_TOP);
				tloc.addCell(cel);

				/* Commented and Added New Code to match the PDF Creation
				Added and changed for CR-76* 

				PdfContentByte cb = writer.getDirectContent();
				BaseFont bf = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)
						.getCalculatedBaseFont(true);
				cb.beginText();
				cb.setFontAndSize(bf, 12);
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "Note", 77, 280,
						0);
				cb.endText();
				cb.stroke();

				if (strNotesMarkFlag != null
						&& strNotesMarkFlag.equalsIgnoreCase("Y")) {

					Rectangle rec = new Rectangle(77, 260, 127, 275);
					rec.setBackgroundColor(new Color(59, 185, 255));
					rec.setBorderColor(new Color(255, 255, 255));
					cb.rectangle(rec);
					cb.stroke();

					BaseFont bf1 = FontFactory.getFont(FontFactory.TIMES_ROMAN,
							12).getCalculatedBaseFont(true);
					cb.beginText();
					cb.setFontAndSize(bf1, 7);
					cb.setRGBColorFill(0, 0, 0);
					cb.showTextAligned(PdfContentByte.ALIGN_LEFT,
							strNotesMarkDesc, 84, 264, 0);
					cb.endText();
					cb.stroke();
				}

				PdfPTable notes = new PdfPTable(1);
				notes.setSpacingAfter(2);

				if (sbGenArrangeNotes != null) {

					PdfPCell phraseAfter = new PdfPCell(new Phrase(11f, "["
							+ sbGenArrangeNotes.toString() + "]",
							strFointSizeBoldNineRed));
					phraseAfter.setBorderColor(new Color(255, 255, 255));
					phraseAfter.setHorizontalAlignment(Element.ALIGN_LEFT);
					phraseAfter.setVerticalAlignment(Element.ALIGN_TOP);
					notes.addCell(phraseAfter);

				}

				PdfPCell phrase = new PdfPCell(new Phrase(11f,
						strGenArrangeNotes, strFontNoUnderLine));
				phrase.setBorderColor(new Color(255, 255, 255));
				phrase.setHorizontalAlignment(Element.ALIGN_LEFT);
				phrase.setVerticalAlignment(Element.ALIGN_TOP);
				notes.addCell(phrase);
				notes.setTotalWidth(200);
				notes.writeSelectedRows(0, -1, 77, 250, writer
						.getDirectContent());
				/** Added and changed for CR-76* */

				if (strGenArrangeNotes != null) {
					
					Table tGenArr = new Table(6);
					
					cel = new Cell(new Paragraph("Note", strFontNoUnderLine));
					cel.setBorderColor(new Color(255, 255, 255));
					tloc.addCell(cel);
					
					if(strNotesMarkFlag != null
							&& strNotesMarkFlag.equalsIgnoreCase("Y")){											
						cel = new Cell(new Paragraph(strNotesMarkDesc,
								strFointSizeBoldSixBlack));
						cel.setBorderColor(new Color(255, 255, 255));
						cel.setBackgroundColor(new Color(59, 185, 255));
						cel.setHorizontalAlignment(Element.ALIGN_CENTER);
						cel.setVerticalAlignment(Element.ALIGN_TOP);
						tGenArr.addCell(cel);
						
						cel = new Cell();
						cel.setBorderColor(new Color(255, 255, 255));
						cel.setColspan(5);
						tGenArr.addCell(cel);
						
						tloc.insertTable(tGenArr);						
					}else{
						cel = new Cell();
						cel.setBorderColor(new Color(255, 255, 255));
						tloc.addCell(cel);
					}
					
					cel = new Cell(new Paragraph(strGenArrangeNotes,
							strFontNoUnderLine));
					cel.setBorderColor(new Color(255, 255, 255));
					tloc.addCell(cel);
				}else{
					cel = new Cell();
					cel.setBorderColor(new Color(255, 255, 255));
					cel.setRowspan(3);
					tloc.addCell(cel);
				}
				
				cel = new Cell();
				cel.setBorderColor(new Color(255, 255, 255));
				tloc.addCell(cel);
				
				cel = new Cell(new Phrase(11f,
						arlModelViewNotes[2], strFontNoUnderLine));
				cel.setBorderColor(new Color(255, 255, 255));
				cel.setHorizontalAlignment(Element.ALIGN_LEFT);
				cel.setVerticalAlignment(Element.ALIGN_TOP);
				tloc.addCell(cel);
				document.add(tloc);

				if (cnt > 2) {
					
					document.newPage();
					tloc = new Table(2);
					tloc.setTableFitsPage(true);
					
					for (int i=0;i<loccnt;i++)
					{
						objFileVO.setImageSeqNo(intAdtlGenArrSeqNo[i]);
						objjFileVO = objFileUploadBO.downloadImage(objFileVO);
						Image image = null;
						byte[] imageView = fetchGenArrangment(objjFileVO);
						if (imageView != null) {
							image = Image.getInstance(imageView);
							image.setAbsolutePosition(380f, 330f);
							// The dimension (400*650) is set for max for the Side
							// view
							// image if it exceeds.
							if (image.getWidth() > 400 || image.getHeight() > 650) {
								image.scaleToFit(400.0f, 650.0f);
							}
							cel = new Cell(image);
							cel.setBorderColor(new Color(255,255,255));
							cel.setRowspan(7);
							setTableProperties(tloc);
							tloc.addCell(cel);
						}
						if ((i%2) != 0){
						
							cel = new Cell(new Phrase(11f,
									arlModelViewNotes[i+2], strFontNoUnderLine));
							cel.setBorderColor(new Color(255,255,255));
							setTableProperties(tloc);
							tloc.addCell(cel);
							
							cel = new Cell(new Phrase(11f,
									arlModelViewNotes[i+3], strFontNoUnderLine));
							cel.setBorderColor(new Color(255,255,255));
							setTableProperties(tloc);
							tloc.addCell(cel);
							document.add(tloc);
							
							document.newPage();
							tloc = new Table(2);
							tloc.setTableFitsPage(true);
						}
						else if ((loccnt==1 || loccnt==3) && (i==loccnt-1)){
							
							cel = new Cell("");
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setRowspan(8);
							tloc.addCell(cel);
							
							cel = new Cell(new Phrase(11f,
									arlModelViewNotes[i+3], strFontNoUnderLine));
							cel.setBorderColor(new Color(255,255,255));
							setTableProperties(tloc);
							tloc.addCell(cel);
							
							document.add(tloc);							
							document.newPage();
						}
					}
					
				}
			}

		} catch (Exception objExp) {

			throw objExp;
		}

	}

	/***************************************************************************
	 * This method is used to download the General Arrangement Image
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param FileVO
	 *            object
	 * @return byte array
	 * @throws Exception
	 * @author VV49326
	 **************************************************************************/
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

	/***************************************************************************
	 * This method is used to set the alignment for Table
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @table , table object
	 * @author VV49326
	 **************************************************************************/
	private static void setTableProperties(Table header) {
		header.setBorderColor(new Color(255, 255, 255));
		header.setBorder(1);
		header.setPadding(1);
		header.setSpacing(1);
		header.setBorderWidth(0);

	}

	/***************************************************************************
	 * This method is used to add new Line
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param table
	 *            object
	 * @author VV49326
	 **************************************************************************/
	private static void addNewLine(PdfPTable tloc) {
		PdfPCell cel = new PdfPCell(new Phrase("\n"));// new line add
		cel.setBorderColor(new Color(255, 255, 255));
		cel.setColspan(2);
		tloc.addCell(cel);

	}

	/***************************************************************************
	 * This method is used to show the Index Page
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param Section
	 *            ArrayList
	 * @param Section
	 *            Number ArrayList
	 * @param table
	 *            object
	 * @param document
	 * @param writer
	 * @author VV49326
	 **************************************************************************/

	private static void showIndexPage(ArrayList alSections,
			ArrayList alSecionNo, PdfPTable t, Document document,
			PdfWriter writer) throws Exception {

		try {

			int intSecNoSize = 0;
			int intSecNameSize = 0;
			Paragraph indexPage = new Paragraph("");

			document.newPage();
			if (alSections != null && alSections != null) {
				intSecNoSize = alSecionNo.size();
				intSecNameSize = alSections.size();
			}

			PdfPCell c2 = new PdfPCell(new Paragraph("INDEX",
					strFontBoldWithUnderLine));
			c2.setHorizontalAlignment(Element.ALIGN_LEFT);
			c2.setBorderColor(new Color(255, 255, 255));

			c2.setColspan(5);

			t.addCell(c2);

			c2 = new PdfPCell(new Paragraph("PAGE", strFontBoldWithUnderLine));
			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			c2.setBorderColor(new Color(255, 255, 255));
			t.addCell(c2);

			c2 = new PdfPCell(new Phrase("\n\n"));// new line add
			c2.setBorderColor(new Color(255, 255, 255));
			c2.setColspan(6);
			t.addCell(c2);

			for (int sec = 0; sec < intSecNameSize; sec++) {
				PdfPCell c1 = new PdfPCell(new Paragraph(String
						.valueOf(alSections.get(sec)), strFointSizeNoBold));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);
				c1.setBorderColor(new Color(255, 255, 255));
				c1.setColspan(5);

				t.addCell(c1);

				c1 = new PdfPCell(new Paragraph(String.valueOf(alSecionNo
						.get(sec)), strFointSizeNoBold));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setBorderColor(new Color(255, 255, 255));

				t.addCell(c1);

				if (sec < intSecNameSize - 1) {
					c1 = new PdfPCell(new Phrase("\n"));
					c1.setBorderColor(new Color(255, 255, 255));
					c1.setColspan(6);

					t.addCell(c1);

				}

			}
			indexPage.add(t);
			document.add(indexPage);
			indexPage.clear();
			t.deleteBodyRows();
		}

		catch (Exception objExp) {
			throw objExp;
		}
	}

	/***************************************************************************
	 * This method is used to show the Specification Information
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param writer
	 *            object
	 * @param document
	 * @param UserID
	 * @param Order
	 *            Key
	 * @param Revision
	 *            Input
	 * @param Appendix
	 *            Flag
	 * @param PdfDestination,PdfOutline
	 * @param Flag
	 * @throws Exception
	 * @author VV49326
	 **************************************************************************/
	private static void showAllSectionInformation(Document document,
			PdfWriter writer, PdfDestination d1, PdfOutline root, String flag,
			int intOrderKey, String strSpec, int intRevInput, String strUserID,
			String strFlag, String appendixFlag) throws Exception {

		LogUtil
				.logMessage("Inside GenerateProofReadingPDF.showAllSectionInformation");
		int intSecSeqNO = 0;
		String strSecSeqCode = null;
		String strSecName = null;
		int intClauseSecNo = 0;
		String strClauseNum = "";
		String strClauseDesc = "";
		String strClauseImageName = "";
		String strFileName="";
		int intDWO = 0;
		int intPartNO = 0;
		int intPriceBookNo = 0;
		int intSubSeqNo = 0;
		Integer intTabColCnt = new Integer(0);
		// Added after Sub Section name Issue 16-Apr-09 VV49326
		String strClaExistsFlag = "N";

		// Added for LSDB_CR-74
		String strModelClauseDelFlag = "";
		String strOrderClauseDelFlag = "";
		
		

		

		try {
			SectionVO objSectionVo = new SectionVO();
			SectionVO objSecVo = new SectionVO();
			ArrayList arlSectionList = new ArrayList();
			ArrayList arlSubSecList = new ArrayList();
			OrderSectionBI objOrderSectionBo = ServiceFactory
					.getOrderSectionBO();

			PdfPTable sec = new PdfPTable(25);

			sec.setWidthPercentage(100);

			String strOptionHeader = "";
			//Modified for LSDB_CR_84 for addition of new Specification types
			if (intSpecTypeSeqNo == 2) {
				strOptionHeader = "ENGINE";
			} else {
				strOptionHeader = "LOCOMOTIVE";
			}

			// Generate Order Clause Nos
			// Commented out Clause Number Generation Proc for LSDB_CR-74 by
			// KA57588 -- Starts here
			/*
			 * objSectionVo.setOrderKey(intOrderKey);
			 * objSectionVo.setDataLocationType(DatabaseConstants.DATALOCATION);
			 * objSectionVo.setUserID(strUserID); int intClaNo =
			 * objOrderSectionBo.generateOrdrClauseNo(objSectionVo);
			 */
			// Commented out Clause Number Generation Proc for LSDB_CR-74 by
			// KA57588 -- Ends here
			// To fetch Sections
			objSectionVo.setOrderKey(intOrderKey);
			objSectionVo.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSectionVo.setUserID(strUserID);
			arlSectionList = objOrderSectionBo.fetchOrdSections(objSectionVo);

			if (arlSectionList != null && arlSectionList.size() > 0) {

				for (int i = 0; i < arlSectionList.size(); i++) {

					objSecVo = (SectionVO) arlSectionList.get(i);

					intSecSeqNO = objSecVo.getSectionSeqNo();
					strSecSeqCode = objSecVo.getSectionCode();
					strSecName = objSecVo.getSectionName();

					if (i == 0) {

						if (strFlag.equalsIgnoreCase("N")) {
							alSecionNo.add(new Integer(writer
									.getCurrentPageNumber()));
							alSections.add("II . " + strOptionHeader
									+ " OPTIONS AND MODIFICATIONS");
						}

						d1 = new PdfDestination(PdfDestination.FIT);
						root = writer.getRootOutline();
						PdfOutline olineLocoOption = new PdfOutline(root, d1,
								"II. " + strOptionHeader
										+ " OPTIONS AND MODIFICATIONS");

						PdfPCell cOption = new PdfPCell(new Paragraph("",
								strFontUnderLineTen));
						cOption.setBorderColor(new Color(255, 255, 255));
						cOption.setColspan(3);
						setCellPadding(cOption);
						sec.addCell(cOption);

						cOption = new PdfPCell(new Paragraph("II.",
								strFontSizeBoldTen));
						cOption.setBorderColor(new Color(255, 255, 255));
						cOption.setColspan(3);
						setCellPadding(cOption);
						sec.addCell(cOption);

						cOption = new PdfPCell(new Paragraph(strOptionHeader
								+ " OPTIONS AND MODIFICATIONS",
								strFontUnderLineTen));
						cOption.setBorderColor(new Color(255, 255, 255));
						cOption.setColspan(12);
						setCellPadding(cOption);
						sec.addCell(cOption);

						cOption = new PdfPCell(new Paragraph("",
								strFointSizeNoBold));
						cOption.setBorderColor(new Color(255, 255, 255));
						cOption.setColspan(7);
						setCellPadding(cOption);
						sec.addCell(cOption);

						document.add(sec);
						sec.deleteBodyRows();
					} else {
						//Modified for LSDB_CR_84 for addition of new Specification types
						if ("Engg".equalsIgnoreCase(strSpec)
								|| (intSpecTypeSeqNo != 2
										&& "Sales".equalsIgnoreCase(strSpec) && !"Z"
										.equalsIgnoreCase(strSecSeqCode))
								|| (intSpecTypeSeqNo == 2 && "Sales"
										.equalsIgnoreCase(strSpec)))

							document.newPage();
					}
					//Modified for LSDB_CR_84 for addition of new Specification types
					if ("Engg".equalsIgnoreCase(strSpec)
							|| (intSpecTypeSeqNo != 2
									&& "Sales".equalsIgnoreCase(strSpec) && !"Z"
									.equalsIgnoreCase(strSecSeqCode))
							|| (intSpecTypeSeqNo == 2 && "Sales"
									.equalsIgnoreCase(strSpec))) {

						// Adding all the Sections in the List
						if (strFlag.equalsIgnoreCase("N")) {
							alSecionNo.add(new Integer(writer
									.getCurrentPageNumber()));
							alSections.add(strSecSeqCode + ". " + strSecName);
						}

						PdfPCell c = new PdfPCell(new Paragraph("",
								strFontUnderLineTen));
						c.setBorderColor(new Color(255, 255, 255));
						c.setColspan(3);
						setCellPadding(c);
						sec.addCell(c);

						c = new PdfPCell(new Paragraph(String
								.valueOf(strSecSeqCode), strFontSizeBoldTen));
						c.setBorderColor(new Color(255, 255, 255));
						c.setLeading(0f, 1.5f);
						c.setColspan(3);
						setCellPadding(c);
						sec.addCell(c);

						c = new PdfPCell(new Paragraph(String
								.valueOf(strSecName), strFontUnderLineTen));
						c.setBorderColor(new Color(255, 255, 255));
						c.setLeading(0f, 1.5f);
						c.setColspan(13);
						setCellPadding(c);
						sec.addCell(c);

						c = new PdfPCell(new Paragraph("", strFointSizeNoBold));
						c.setBorderColor(new Color(255, 255, 255));
						c.setColspan(6);
						setCellPadding(c);
						sec.addCell(c);

						document.add(sec);
						sec.deleteBodyRows();

						// }

						// This is for adding Book Mark for Section
						d1 = new PdfDestination(PdfDestination.FIT);
						root = writer.getRootOutline();
						PdfOutline oline1 = new PdfOutline(root, d1,
								strSecSeqCode + ". " + strSecName);
						oline1.setOpen(false);

						SectionVO objjSectionVO = new SectionVO();
						objjSectionVO.setOrderKey(intOrderKey);
						objjSectionVO
								.setDataLocationType(DatabaseConstants.DATALOCATION);
						objjSectionVO.setSectionSeqNo(intSecSeqNO);
						objjSectionVO.setUserID(strUserID);
						arlSubSecList = objOrderSectionBo
								.fetchOrdSubSections(objjSectionVO);
						//Added For CR_99
						if ("Sales".equalsIgnoreCase(strSpec))
							objjSectionVO.setIntPdfType(2);
						else
							objjSectionVO.setIntPdfType(1);
						// Clause fetch is moved section wise to reduce
						// generation time
						objjSectionVO.setSectionSeqNo(intSecSeqNO);
						objjSectionVO.setRevisionInput(intRevInput);
						objjSectionVO.setUserID(strUserID);
						
						SubSectionVO objSubSecVo = objOrderSectionBo
								.fetchClauseDetails(objjSectionVO);
						ArrayList arlClaList = new ArrayList();
						// Clause fetch is moved section wise to reduce
						// generation time
						PdfPTable tmpTable = new PdfPTable(25);
						tmpTable.setWidthPercentage(100);

						PdfPTable subsecClause = new PdfPTable(25);
						subsecClause.setWidthPercentage(100);

						if (arlSubSecList.size() > 0) {

							for (int k = 0; k < arlSubSecList.size(); k++) {

								SubSectionVO objSubSecVO = (SubSectionVO) arlSubSecList
										.get(k);

								int intSubSecSeqNo = objSubSecVO
										.getSubSecSeqNo();
								String strSubSecSeqCode = objSubSecVO
										.getSubSecCode();
								String strSubSecName = objSubSecVO
										.getSubSecName();

								d1 = new PdfDestination(PdfDestination.FIT);
								PdfOutline oline2 = new PdfOutline(oline1, d1,
										strSubSecSeqCode + " " + strSubSecName);
								c = new PdfPCell(new Paragraph("",
										strFontSizeBoldTen));
								c.setBorderColor(new Color(255, 255, 255));
								c.setColspan(3);
								setCellPadding(c);
								subsecClause.addCell(c);

								c = new PdfPCell(new Paragraph(
										strSubSecSeqCode, strFontSizeBoldTen));
								c.setBorderColor(new Color(255, 255, 255));
								c.setLeading(0f, 1.5f);// Added for Underline
								// issue
								c.setColspan(3);
								setCellPadding(c);
								subsecClause.addCell(c);

								c = new PdfPCell(new Paragraph(String
										.valueOf(strSubSecName),
										strFontUnderLineTwelve));
								c.setBorderColor(new Color(255, 255, 255));
								c.setLeading(0f, 1.5f);// Added for Underline
								// issue
								c.setColspan(12);
								setCellPadding(c);
								subsecClause.addCell(c);

								c = new PdfPCell(new Paragraph("",
										strFointSizeNoBold));
								c.setBorderColor(new Color(255, 255, 255));
								c.setColspan(7);
								setCellPadding(c);
								subsecClause.addCell(c);

								// Commented after Sub Section name issue
								// document.add(subsecClause);
								// subsecClause.deleteBodyRows();

								// Clause fetch is moved to section wise

								/*
								 * objjSectionVO.setSectionSeqNo(intSecSeqNO);
								 * objjSectionVO.setSubSecSeqNo(intSubSecSeqNo);
								 * objjSectionVO.setRevisionInput(intRevInput);
								 * objjSectionVO.setUserID(strUserID);
								 * SubSectionVO objSubSecVo = objOrderSectionBo
								 * .fetchClauseDetails(objjSectionVO); ArrayList
								 * arlClaList = new ArrayList();
								 */

								// Clause fetch is moved to section wise
								arlClaList = objSubSecVo.getClauseGroup();
								// Added after Sub Section name Issue 16-Apr-09
								// VV49326
								if (arlClaList.size() > 0) {

									for (int h = 0; h < arlClaList.size(); h++) {

										// Added after Sub Section name Issue
										// 16-Apr-09 VV49326
										strClaExistsFlag = "N";
										ClauseVO objClauseVO = (ClauseVO) arlClaList
												.get(h);
										// Added to display only current Sub
										// Section Clauses
										intSubSeqNo = objClauseVO
												.getSubSectionSeqNo();
										if (intSubSeqNo == intSubSecSeqNo) {
											// Added after Sub Section name
											// Issue 16-Apr-09 VV49326
											strClaExistsFlag = "Y";
											ArrayList arlTableData = new ArrayList();
											ArrayList arlTabData = new ArrayList();
											intClauseSecNo = objClauseVO
													.getClauseSeqNo();
											strClauseNum = objClauseVO
													.getClauseNum();
											strClauseDesc = objClauseVO
													.getClauseDesc();
											intDWO = objClauseVO.getDwONumber();
											intPartNO = objClauseVO
													.getPartNumber();
											intPriceBookNo = objClauseVO
													.getPriceBookNumber();
											// Added for LSDB_CR-74
											strModelClauseDelFlag = objClauseVO
													.getDeleteFlag();
											strOrderClauseDelFlag = objClauseVO
													.getClauseDelFlag();

											arlTabData = getTableDataColumnsCount(objClauseVO
													.getTableArrayData1());

											if (arlTabData != null
													&& arlTabData.size() > 0) {

												intTabColCnt = (Integer) arlTabData
														.get(0);

											}

											arlTableData = objClauseVO
													.getTableArrayData1();

											String strRevNo = "";
											strClauseImageName = "";

											if (intPriceBookNo != 0) {
												c = new PdfPCell(
														new Paragraph(
																String
																		.valueOf(intPriceBookNo),
																strFointSizeBoldNineBlack));
												c.setBorderColor(new Color(255,
														255, 255));
												c.setColspan(1);
												c.setNoWrap(true);
												c
														.setHorizontalAlignment(Element.ALIGN_LEFT);
												setCellPadding(c);
												subsecClause.addCell(c);
											} else {
												c = new PdfPCell(
														new Paragraph("",
																strFointSizeBoldNineBlack));
												c.setBorderColor(new Color(255,
														255, 255));
												c.setColspan(1);
												c.setNoWrap(true);
												c
														.setHorizontalAlignment(Element.ALIGN_LEFT);
												setCellPadding(c);
												subsecClause.addCell(c);
											}

											// Added for LSDB_CR-74 -- Starts
											// here
											PdfPTable marker = new PdfPTable(2);

											if (objClauseVO.getUserMarkFlag() != null
													&& !"".equals(objClauseVO
															.getUserMarkFlag())) {

												if ("Y"
														.equalsIgnoreCase(objClauseVO
																.getUserMarkFlag())) {
													c = new PdfPCell(
															new Paragraph(
																	"MARKED",
																	strFointSizeBoldSixBlack));
													c.setBorderColor(new Color(
															0, 0, 160));
													c.setColspan(2);
													c.setNoWrap(true);
													// c.setHorizontalAlignment(Element.ALIGN_CENTER);
													setCellPadding(c);
													marker.addCell(c);
												}
											}
											if (objClauseVO.getSysMarkFlag() != null
													&& !"".equals(objClauseVO
															.getSysMarkFlag())) {

												if ("Y"
														.equalsIgnoreCase(objClauseVO
																.getSysMarkFlag())) {
													//Issue for Sales Spec Should not show System Marker Desc
													if("Engg".equalsIgnoreCase(strSpec)||("Sales".equalsIgnoreCase(strSpec) && "Y".equalsIgnoreCase(objClauseVO.getSaleSysMarker())) ){
														c = new PdfPCell(
																new Paragraph(
																		objClauseVO
																				.getSysMarkDesc(),
																		strFointSizeBoldSixBlack));
														c.setBorderColor(new Color(
																255, 255, 255));
														c
																.setBackgroundColor(new Color(
																		59, 185,
																		255));
														c.setColspan(2);
														c
																.setHorizontalAlignment(Element.ALIGN_CENTER);
														setCellPadding(c);
	
														
														marker.addCell(c);
													}
												}
											}

											// Ends here

											ArrayList arlRevisionNo = objClauseVO
													.getRevCode();
											if (arlRevisionNo.size() > 0) {

												for (int n = 0; n < arlRevisionNo
														.size(); n++) {

													strRevNo += arlRevisionNo
															.get(n);
													strRevNo += "\n\n";

												}
											}

											if (!"".equals(strRevNo)
													&& strRevNo != null) {

												c = new PdfPCell(
														new Paragraph(
																String
																		.valueOf(strRevNo),
																strFointSizeBoldNineRed));
												c.setBorderColorTop(new Color(
														0, 0, 160));
												c.setBorder(0);
												c.setBorderColorLeft(new Color(
														255, 255, 255));
												c
														.setBorderColorRight(new Color(
																255, 255, 255));
												c
														.setBorderColorBottom(new Color(
																255, 255, 255));

												c.setColspan(2);
												c.setNoWrap(true);
												c.setLeading(0f, 0.5f);
												c.setPaddingLeft(0);
												// c.setHorizontalAlignment(Element.ALIGN_LEFT);
												// c.setVerticalAlignment(Element.ALIGN_TOP);
												setCellPadding(c);
												marker.addCell(c);
											} else {
												c = new PdfPCell(
														new Paragraph("",
																strFointSizeBoldNineRed));
												c.setBorderColorTop(new Color(
														0, 0, 160));
												c.setBorder(0);
												c.setBorderColorLeft(new Color(
														255, 255, 255));
												c
														.setBorderColorRight(new Color(
																255, 255, 255));
												c
														.setBorderColorBottom(new Color(
																255, 255, 255));
												c.setLeading(0f, 0.5f);
												c.setColspan(2);
												c
														.setHorizontalAlignment(Element.ALIGN_LEFT);
												setCellPadding(c);
												marker.addCell(c);
											}

											// Added for LSDB_CR-74 by KA57588
											// -- Starts here
											c = new PdfPCell(marker);
											c.setBorderColor(new Color(255,
													255, 255));
											c.setColspan(2);
											c
													.setHorizontalAlignment(Element.ALIGN_LEFT);
											c
													.setVerticalAlignment(Element.ALIGN_TOP);
											setCellPadding(c);
											subsecClause.addCell(c);
											// Ends here

											c = new PdfPCell(
													new Paragraph(
															String
																	.valueOf(strClauseNum),
															strFontSizeBoldTen));
											c.setBorderColor(new Color(255,
													255, 255));
											c.setColspan(3);
											setCellPadding(c);
											subsecClause.addCell(c);

											// Added for LSDB_CR-74 by KA57588
											// -- Starts here

											if (strOrderClauseDelFlag != null
													&& "Y"
															.equalsIgnoreCase(strOrderClauseDelFlag)) {

												if (strModelClauseDelFlag != null
														&& !"D"
																.equalsIgnoreCase(strModelClauseDelFlag)) {

													PdfPCell c1 = new PdfPCell(
															new Paragraph(
																	"RESERVED",
																	strFontSizeNoBoldTen));
													c1
															.setBorderColor(new Color(
																	255, 255,
																	255));
													c1.setColspan(15);
													c1
															.setVerticalAlignment(Element.ALIGN_TOP);
													alignLeft(c1);
													//Modified for CR_111
													setCellPadding(c1);
													subsecClause.addCell(c1);
												}
											}

											if (strOrderClauseDelFlag != null
													&& "Y"
															.equalsIgnoreCase(strOrderClauseDelFlag)) {

												if (strModelClauseDelFlag != null
														&& "D"
																.equalsIgnoreCase(strModelClauseDelFlag)) {

													PdfPCell c1 = new PdfPCell(
															new Paragraph(
																	"RESERVED",
																	strFontSizeNoBoldTen));
													c1
															.setBorderColor(new Color(
																	255, 255,
																	255));
													c1.setColspan(15);
													c1
															.setVerticalAlignment(Element.ALIGN_TOP);
													alignLeft(c1);
													//Modified for CR_111
													setCellPadding(c1);
													subsecClause.addCell(c1);
												}
											}

											// Ends here
											if (strOrderClauseDelFlag != null
													&& !"Y"
															.equalsIgnoreCase(strOrderClauseDelFlag)) {
												if ((strModelClauseDelFlag != null && !"D"
														.equalsIgnoreCase(strModelClauseDelFlag))
														|| (strModelClauseDelFlag != null && "D"
																.equalsIgnoreCase(strModelClauseDelFlag))) {

													int tabColCount = 0;

													if (intTabColCnt.intValue() == 0) {

														tabColCount = 1;
													} else {

														tabColCount = intTabColCnt
																.intValue();
													}

													PdfPTable TBData = new PdfPTable(
															tabColCount);
													TBData
															.setWidthPercentage(100);
													
													//Added for CR-130													
													FontFactory.register("/images/times.ttf",BaseFont.HELVETICA);
													FontFactory.register("/images/timesbd.ttf",BaseFont.HELVETICA_BOLD);
													FontFactory.register("/images/timesi.ttf",BaseFont.HELVETICA_OBLIQUE);
													FontFactory.register("/images/timesbi.ttf",BaseFont.HELVETICA_BOLDOBLIQUE);
													
													BaseFont customBaseFont = BaseFont.createFont("/images/times.ttf",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
													
													Font customFont = new Font(customBaseFont,12);
													//Added for CR-130 ends
													
													if (strClauseDesc != null
															&& !"".equals(strClauseDesc)) {
														
														// Added for CR-88 by BM85529
														if (strClauseDesc.startsWith("<p>"))
															{
															
																												
																Paragraph paraClauseDesc = new Paragraph();
																paraClauseDesc.setFont(customFont);//Edited for CR-130
																ArrayList p = HTMLWorker.parseToList(new StringReader(strClauseDesc), null);//Added for CR-129 pdf issue fix
																
																/*strFileName=ApplicationUtil.strConvertToHTMLFormat(strClauseDesc);
																StyleSheet styles = new StyleSheet();
															    styles.loadTagStyle("p","size","12px");
															    
															    ArrayList p = HTMLWorker.parseToList(new StringReader(strClauseDesc), styles);*/
															    /*Commented for CR-129 pdf issue fix*/
														    
														    for (int k1 = 0; k1 < p.size(); ++k1) {
														    	paraClauseDesc.add((Element) p.get(k1));
														    	
														    }
														   
															PdfPCell c1 = new PdfPCell(paraClauseDesc);
															c1.setBorderColor(new Color(255,255,255));
															alignLeft(c1);
															c1.setColspan(tabColCount);
															c1.setLeading(0f,1.0f);//Fixing line spacing to be same in history and proof reading
															TBData.addCell(c1);
															}
														else
															{
															PdfPCell c1 = new PdfPCell(new Paragraph(String
																	.valueOf(strClauseDesc),
																	customFont));//Edited for CR-130
															c1.setBorderColor(new Color(255, 255, 255));
															alignLeft(c1);
															c1.setColspan(tabColCount);
															c1.setLeading(0f,1.0f);//Fixing line spacing to be same in history and proof reading
															TBData.addCell(c1);
															}
														
                                                        // Ends here
														
														/*PdfPCell c1 = new PdfPCell(
																new Paragraph(
																		String
																				.valueOf(strClauseDesc),
																		strFontSizeNoBoldTen));
														c1
																.setBorderColor(new Color(
																		255,
																		255,
																		255));
														alignLeft(c1);
														c1
																.setColspan(tabColCount);*/

													} else {

														PdfPCell c1 = new PdfPCell(
																new Paragraph(
																		"",
																		strFontSizeNoBoldTen));
														c1
																.setBorderColor(new Color(
																		255,
																		255,
																		255));
														alignLeft(c1);
														c1
																.setColspan(tabColCount);
														c1.setLeading(0f,1.0f);//Fixing line spacing to be same in history and proof reading
														TBData.addCell(c1);
													}

													int intHeader = 0;
													Font fontHeader;

													if (arlTableData != null
															&& arlTableData
																	.size() > 0) {

														for (int tab = 0; tab < arlTableData
																.size(); tab++) {

															ArrayList arlTabColData = (ArrayList) arlTableData
																	.get(tab);

															if (intHeader == 0)
																fontHeader = strFontSizeBoldTen;
															else
																fontHeader = strFontSizeNoBoldTen;

															if (tabColCount == 1
																	|| tabColCount == 2
																	|| tabColCount == 3
																	|| tabColCount == 4
																	|| tabColCount == 5) {

																PdfPCell c1 = new PdfPCell(
																		new Paragraph(
																				(String) arlTabColData
																						.get(0),
																				fontHeader));
																//Modified for CR_111 to add borders for Table Data
																//c1.setBorder(PdfPCell.TOP);
																c1
																		.setBorderColor(new Color(
																				0,
																				0,
																				0));
																TBData
																		.addCell(c1);
															}

															if (tabColCount == 2
																	|| tabColCount == 3
																	|| tabColCount == 4
																	|| tabColCount == 5) {

																PdfPCell c1 = new PdfPCell(
																		new Paragraph(
																				(String) arlTabColData
																						.get(1),
																				fontHeader));
																//Modified for CR_111 to add borders for Table Data
																//c1.setBorder(PdfPCell.TOP);
																c1
																		.setBorderColor(new Color(
																				0,
																				0,
																				0));
																TBData
																		.addCell(c1);
															}

															if (tabColCount == 3
																	|| tabColCount == 4
																	|| tabColCount == 5) {

																PdfPCell c1 = new PdfPCell(
																		new Paragraph(
																				(String) arlTabColData
																						.get(2),
																				fontHeader));
																//Modified for CR_111 to add borders for Table Data
																//c1.setBorder(PdfPCell.TOP);
																c1
																		.setBorderColor(new Color(
																				0,
																				0,
																				0));
																TBData
																		.addCell(c1);
															}

															if (tabColCount == 4
																	|| tabColCount == 5) {

																PdfPCell c1 = new PdfPCell(
																		new Paragraph(
																				(String) arlTabColData
																						.get(3),
																				fontHeader));
																//Modified for CR_111 to add borders for Table Data
																//c1.setBorder(PdfPCell.TOP);
																c1
																		.setBorderColor(new Color(
																				0,
																				0,
																				0));
																TBData
																		.addCell(c1);
															}

															if (tabColCount == 5) {

																PdfPCell c1 = new PdfPCell(
																		new Paragraph(
																				(String) arlTabColData
																						.get(4),
																				fontHeader));
																//Modified for CR_111 to add borders for Table Data
																//c1.setBorder(PdfPCell.TOP);
																c1
																		.setBorderColor(new Color(
																				0,
																				0,
																				0));
																TBData
																		.addCell(c1);
															}

															intHeader++;
														}
													}

													// This part is for
													// concatenating clause
													// Image
													// name to the clause
													if (appendixFlag != null
															&& !""
																	.equals(appendixFlag)
															&& "Y"
																	.equalsIgnoreCase(appendixFlag)) {

														strClauseImageName = objClauseVO
																.getClauseImageName();

														if (strClauseImageName != null
																&& !""
																		.equals(strClauseImageName)) {
															strClauseImageName = "(Refer To Appendix Image:"
																	+ strClauseImageName
																	+ ")";
															PdfPCell c1 = new PdfPCell(
																	new Paragraph(
																			new Paragraph(
																					strClauseImageName,
																					strFontSizeBoldTen)));
															c1
																	.setBorderColor(new Color(
																			255,
																			255,
																			255));
															c1
																	.setColspan(tabColCount);
															alignLeft(c1);
															TBData.addCell(c1);

														}

													}

													PdfPCell c1 = new PdfPCell(
															TBData);
													c1
															.setBorderColor(new Color(
																	255, 255,
																	255));
													c1.setColspan(15);
													c1
															.setVerticalAlignment(Element.ALIGN_TOP);
													alignLeft(c1);
													setCellPaddingTab(c1);
													subsecClause.addCell(c1);
												}
											}
											// Commented after Sub Section name
											// issue
											// document.add(subsecClause);
											// subsecClause.deleteBodyRows();
											
											//Added and Modified For CR_81 to bring Char Edl and RefEdl - Starts here
											
											String strCharstcGrpFlag = objClauseVO.getSelectCGCFlag();

											String strEngData = "";
											
											if ("Y".equals(strCharstcGrpFlag))	{
												
												String strCharEdlRefEdlNo = "";
												
/*												if (objClauseVO.getCharRefEDLNo() != null
														&&	!"".equals(objClauseVO.getCharRefEDLNo()))
													
													strCharEdlRefEdlNo += "ref: EDL " + objClauseVO.getCharRefEDLNo()
																				+ "\n";
													*/
												
												if (objClauseVO.getCharEdlNo() != null 
														&&	!"".equals(objClauseVO.getCharEdlNo()))
													
													strCharEdlRefEdlNo += "EDL " + objClauseVO.getCharEdlNo()
																				+ "\n";				

												//	CR 87
												if (objClauseVO.getCharRefEDLNo() != null
														&&	!"".equals(objClauseVO.getCharRefEDLNo()))
														strCharEdlRefEdlNo += "(ref EDL " + objClauseVO.getCharRefEDLNo()
																				+ ")"+ "\n";	
												
												if (strCharEdlRefEdlNo != null
														&& !"".equals(strCharEdlRefEdlNo)) 
													strEngData += strCharEdlRefEdlNo;
												else
													strEngData += "EDL Undeveloped" + "\n";											
											
											}
											//Modified For CR_85
											//Modified for CR_88 Changed N to C
											if ("C".equals(strCharstcGrpFlag))	{
												
												String strCharEdlRefEdlNo = "";		
												
												if (objClauseVO.getCharEdlNo() != null 
														&&	!"".equals(objClauseVO.getCharEdlNo()))
													
													strCharEdlRefEdlNo += "EDL " + objClauseVO.getCharEdlNo()
																				+ "\n";	
												
												if (objClauseVO.getCharRefEDLNo() != null
														&&	!"".equals(objClauseVO.getCharRefEDLNo()))
														strCharEdlRefEdlNo += "(ref EDL " + objClauseVO.getCharRefEDLNo()
																				+ ")"+ "\n";	
												
												if (strCharEdlRefEdlNo != null
														&& !"".equals(strCharEdlRefEdlNo)) 
													strEngData += strCharEdlRefEdlNo;
											}
											//Modified For CR_85 Ends here
											//Moved from bottom to accomdate Char Edl and RefEdl for CR_81
												
/*												if (arlRefEDLNO.size() > 0) {
													for (int n = 0; n < arlRefEDLNO
															.size(); n++) {
														strEngData += "ref: EDL "
																+ arlRefEDLNO
																		.get(n)
																		.toString();
														strEngData += "\n";
													}
												}*/
		
												ArrayList arlEDLNO = objClauseVO
														.getEdlNO();
												if (arlEDLNO.size() > 0) {
													for (int n = 0; n < arlEDLNO
															.size(); n++) {
														strEngData += "EDL "
																+ arlEDLNO.get(n)
																		.toString();
														strEngData += "\n";
													}
												}
												//CR 87				
												ArrayList arlRefEDLNO = objClauseVO
												.getRefEDLNO();
												
												if (arlRefEDLNO.size() > 0) {
													for (int n = 0; n < arlRefEDLNO
															.size(); n++) {
													strEngData += "(ref EDL "
															+ arlRefEDLNO
																	.get(n)
																	.toString();
													strEngData += ")"+"\n";
													}
												}
											//Added and Modified For CR_81 to bring Char Edl and RefEdl - Ends here
											
											ArrayList arlPartOf = objClauseVO
													.getPartOF();
											if (arlPartOf.size() > 0) {
												for (int n = 0; n < arlPartOf
														.size(); n++) {
													SubSectionVO objSubSectionVo = (SubSectionVO) arlPartOf
															.get(n);
													//CR 87
													strEngData += "Part of "
															+ objSubSectionVo
																	.getSubSecCode();
													strEngData += "\n";
												}
											}

											if (objClauseVO.getDwONumber() != 0) {
												strEngData += "DWO "
														+ objClauseVO
																.getDwONumber();
												strEngData += "\n";
											}

											if (objClauseVO.getPartNumber() != 0) {
												strEngData += "Part No "
														+ objClauseVO
																.getPartNumber();
												strEngData += "\n";
											}

											if (objClauseVO
													.getStandardEquipmentDesc() != null) {
												strEngData += objClauseVO
														.getStandardEquipmentDesc();
												strEngData += "\n";
											}

											if (objClauseVO.getEngDataComment() != null) {
												strEngData += objClauseVO
														.getEngDataComment();
												strEngData += "\n";
											}

											// Condition Checked for LSDB_CR-74
											// by KA57588
											if (strOrderClauseDelFlag != null
													&& !"Y"
															.equalsIgnoreCase(strOrderClauseDelFlag)) {
												if ((strModelClauseDelFlag != null && !"D"
														.equalsIgnoreCase(strModelClauseDelFlag))
														|| (strModelClauseDelFlag != null && "D"
																.equalsIgnoreCase(strModelClauseDelFlag))) {

													if ("Engg"
															.equalsIgnoreCase(strSpec)) {
														if (strEngData != null
																&& !""
																		.equals(strEngData)) {

															c = new PdfPCell(
																	new Paragraph(
																			(strEngData == null) ? ""
																					: strEngData,
																			strFointSizeBoldNine));
															c.setColspan(4);
															c
																	.setBorderColor(new Color(
																			255,
																			255,
																			255));
															alignLeft(c);
															setCellPadding(c);
															subsecClause
																	.addCell(c);
														} else {
															c = new PdfPCell(
																	new Paragraph(
																			"",
																			strFointSizeBoldNine));
															c.setColspan(4);
															c
																	.setBorderColor(new Color(
																			255,
																			255,
																			255));
															alignLeft(c);
															setCellPadding(c);
															subsecClause
																	.addCell(c);

														}
													} else {
														c = new PdfPCell(
																new Paragraph(
																		"",
																		strFointSizeBoldNine));
														c.setColspan(4);
														c
																.setBorderColor(new Color(
																		255,
																		255,
																		255));
														alignLeft(c);
														setCellPadding(c);
														subsecClause.addCell(c);
													}
												}// if
											}// if
											else {

												c = new PdfPCell(new Paragraph(
														"",
														strFointSizeBoldNine));
												c.setColspan(4);
												c.setBorderColor(new Color(255,
														255, 255));
												alignLeft(c);
												setCellPadding(c);
												subsecClause.addCell(c);
											}

											c = new PdfPCell(subsecClause);
											c.setColspan(25);
											tmpTable.addCell(c);
											if (!writer.fitsPage(tmpTable)) {
												document.newPage();
											}
											document.add(tmpTable);
											tmpTable = new PdfPTable(25);
											tmpTable.setWidthPercentage(100);
											subsecClause = new PdfPTable(25);
											subsecClause
													.setWidthPercentage(100);

										}// current subsection clause check
									}
									// Added after Sub Section name Issue
									// 16-Apr-09 VV49326
									// SubSection Exists and No Clauses Exists
									// for Sub Section Check
									if (!strClaExistsFlag.equalsIgnoreCase("Y")) {
										document.add(subsecClause);
										subsecClause = new PdfPTable(25);
										subsecClause.setWidthPercentage(100);

									}

									// Added after Sub Section name Issue
									// 16-Apr-09 VV49326
									// SubSection Exists and No Clauses Exists
									// for Section Check
								} else {

									document.add(subsecClause);
									subsecClause = new PdfPTable(25);
									subsecClause.setWidthPercentage(100);

								}

							}
						}

					}// SALES & ENG SPEC condition ends
				}

			}

			// Delete Order temp table
			// Commented out Clause Number Generation Proc for LSDB_CR-74 by
			// KA57588 -- Starts here
			/*
			 * objSectionVo.setOrderKey(intOrderKey);
			 * objSectionVo.setDataLocationType(DatabaseConstants.DATALOCATION);
			 * objSectionVo.setUserID(strUserID); int intDelTemptab =
			 * objOrderSectionBo .deleteTempTabData(objSectionVo);
			 */
			// Commented out Clause Number Generation Proc for LSDB_CR-74 by
			// KA57588 -- Ends here
		} catch (Exception objExp) {
			throw objExp;
		}

	}

	/***************************************************************************
	 * This method is used to set cell padding
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param PdfPCell
	 * @author VV49326
	 **************************************************************************/
	private static void setCellPadding(PdfPCell cell) {

		cell.setPadding(5);

	}

	/***************************************************************************
	 * This method is used to set cell padding
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param PdfPCell
	 * @author VV49326
	 **************************************************************************/
	private static void setCellPaddingTab(PdfPCell cell) {

		cell.setPadding(3);

	}

	/***************************************************************************
	 * This method is used to show Appendix images
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param document
	 * @param strPdfType
	 * @param Orderkey
	 * @param writer
	 * @param flag
	 * @author VV49326
	 **************************************************************************/

	private static void showAppendix(Document document, String strPdfType,int intOrderKey,
			PdfWriter writer, String strUserID, String strFlag)
			throws Exception {

		ArrayList artlImageList = null;
		AppendixVO objAppendixVO = new AppendixVO();

		try {

			objAppendixVO.setUserID(strUserID);
			objAppendixVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objAppendixVO.setOrderKey(intOrderKey);
			AppendixBI objAppendixBI = ServiceFactory.getAppendixBO();
			artlImageList = objAppendixBI.fetchImage(objAppendixVO);

			/*PdfPTable tappendix = new PdfPTable(10);
			tappendix.setWidthPercentage(100);

			PdfPCell cappendix = new PdfPCell(new Paragraph("",
					strFontNoUnderLine));
			cappendix.setBorderColor(new Color(255, 255, 255));
			tappendix.addCell(cappendix);
			cappendix = new PdfPCell(
					new Paragraph("APPENDIX", strFontUnderLine));

			cappendix.setBorderColor(new Color(255, 255, 255));
			cappendix.setColspan(9);
			tappendix.addCell(cappendix);

			cappendix = new PdfPCell(new Phrase("\n"));// new line add
			cappendix.setBorderColor(new Color(255, 255, 255));
			cappendix.setColspan(10);
			tappendix.addCell(cappendix);
			document.add(tappendix);
			tappendix = new PdfPTable(10);
			tappendix.setWidthPercentage(100);*///Commented for CR_97

			if (strFlag.equalsIgnoreCase("N")) {
				alSecionNo.add(new Integer(writer.getCurrentPageNumber()));
				alSections.add("APPENDIX");
			}
			//fetchAppendix(artlImageList, cappendix, tappendix, document, writer);Commented for CR_97
			//Added Parameter for CR_101 to hide Appendix Images in Sales Spec
			LogUtil.logMessage("Before fetchAppendix method in generateproofreading.... ");
			fetchAppendix(artlImageList, strPdfType, document, writer);
		} catch (Exception objExp) {

			throw objExp;
		}

	}

	/***************************************************************************
	 * This method is used to fetch Appendix images
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @version 1.1 by RJ85495 modified the parameters
	 * @param document
	 * @param ArrayList
	 * @param strPdfType
	 * @param PdfPCell
	 * @param writer
	 * @author VV49326
	 **************************************************************************/
	private static void fetchAppendix(ArrayList arlAppendix, String strPdfType, 
		Document document, PdfWriter writer)
			throws SQLException, Exception {
		LogUtil.logMessage("Inside fetchAppendix method before bytearrayouptut. ");
		ByteArrayOutputStream byteArrayOutputStream = null;
		Image image = null;

		ByteArrayOutputStream output = null;

		FileUploadBI objFileUploadBO = ServiceFactory.getFileUploadBO();
		FileVO objFileVO = new FileVO();
		FileVO objjFileVO = new FileVO();
		int intAppCnt=0;//Added for CR_97
		PdfContentByte cb = writer.getDirectContent();
		try {
            
			if (arlAppendix.size() > 0) {
				
				LogUtil.logMessage("Inside if condition of fetchAppendix ");
				for (int cnt = 0; cnt < arlAppendix.size(); cnt++) {
					
					LogUtil.logMessage("Inside for loop 1 ");
					LogUtil.logMessage("arlAppendix.size() "+ arlAppendix.size());
					
					AppendixVO objAappendixVO = (AppendixVO) arlAppendix.get(cnt);
					
					LogUtil.logMessage("arlAppendix.size() "+ arlAppendix.size());
					//tAppendix = new PdfPTable(20);
					//Added For CR_101 to hide Appendix Images from Sales Spec
					LogUtil.logMessage("arlAppendix.size() "+ arlAppendix.size());
					String strSalesDispFlag = objAappendixVO.getSalesDispFlag();
					
					LogUtil.logMessage("strSalesDispFlag "+strSalesDispFlag);
					LogUtil.logMessage("strPdfType "+ strPdfType);
					
					if (("Engg".equalsIgnoreCase(strPdfType)) || 
							("Sales".equalsIgnoreCase(strPdfType) && "Y".equalsIgnoreCase(strSalesDispFlag)))
					{
						LogUtil.logMessage("Inside if loop 1 ");
						image = null;
						output = null;
						int intImageSeqNo = objAappendixVO.getImageSeqNo();
						String strImageName = objAappendixVO.getImageName();
						String strImageDesc = objAappendixVO.getImageDesc();
						/* Added for CR_97 for appending PDF files to Spec */
						String strContentType = objAappendixVO.getFileVO().getContentType();
						/* CR_97 Ends here */
						if (intImageSeqNo != 0) {
							
							LogUtil.logMessage("Inside if loop 2  ");
							document.newPage();//Added for CR_97
							objFileVO.setImageSeqNo(intImageSeqNo);
							objjFileVO = objFileUploadBO.downloadImage(objFileVO);
							InputStream sImage = objjFileVO.getFileStream();
							PdfPTable tAppendix = new PdfPTable(10);
							tAppendix.setWidthPercentage(100);
							PdfPCell cel = new PdfPCell(
									new Paragraph("", strFontNoUnderLine));
							cel.setBorderColor(new Color(255, 255,
									255));
							tAppendix.addCell(cel);
							
	
							/* Added & Modified for CR_97 for appending PDF files */
							if (strContentType.equalsIgnoreCase("application/pdf")) {
								
								PdfReader reader = new PdfReader(sImage); // byte
								LogUtil.logMessage("Before for condition reader.getnoofpages ");
								for (int x = 1; x <= reader.getNumberOfPages(); x++) {
									PdfImportedPage page = writer.getImportedPage(reader, x);
									//Adding for CR_97
									LogUtil.logMessage("Inside for loop  2");
									if (intAppCnt == 0 && x == 1) {
										Rectangle psize = reader.getPageSize(1);
										float width = psize.getHeight();
										float height = psize.getWidth();
										cb.addTemplate(page, .5f, 0, 0, .5f, width,height);
	
										BaseFont bf = FontFactory.getFont(
												FontFactory.TIMES_ROMAN, 12).getCalculatedBaseFont(true);
	
										cb.beginText();
										cb.setFontAndSize(bf, 12);
										cb.showTextAligned(	PdfContentByte.ALIGN_LEFT,"APPENDIX", 70, 800, 0);
										cb.endText();
	
										cb.setLineWidth(0.5f);
										cb.moveTo(70, 795);
										cb.lineTo(130, 795);
										cb.stroke();
	
										}
									//Commented and Added Resize PDF Code for CR_101
									if (x==1){
										cel = new PdfPCell(new Paragraph(String.valueOf(strImageName),
												strFontSizeBoldTen));
										cel.setColspan(20);
										cel.setBorderColor(new Color(255, 255, 255));
										alignCenter(cel);
										tAppendix.addCell(cel);
										document.add(tAppendix);
									} 
									LogUtil.logMessage("After for loop ");
	 
									Image imgPDF = Image.getInstance(page);
									imgPDF.setAlignment(Element.ALIGN_CENTER);								
									if (imgPDF.getHeight() > 600 || imgPDF.getWidth() > 550)
										imgPDF.scaleToFit(620.0f,670.0f);
									document.add(imgPDF);
								}
							} else {
								
							output = new ByteArrayOutputStream();
							byte[] rb = new byte[1024];
							int ch = 0;
							// get content of a document
							while ((ch = sImage.read(rb)) != -1) {
								output.write(rb, 0, ch);
							}
	
							byte[] buf = output.toByteArray();
							byteArrayOutputStream = new ByteArrayOutputStream();
							byteArrayOutputStream.write(buf);
							image = Image.getInstance(byteArrayOutputStream
									.toByteArray());
	
							if (image.getScaledWidth() > 550
									|| image.getScaledHeight() > 600) {
	
								throw new Exception(
										"(Image Seq No-"
												+ intImageSeqNo
												+ ",Image Name-"
												+ strImageName
												+ ")- Image size should be lesser or equal (550*600) in pixels");
	
							}
							// Ends here
							//Adding for CR_97
							if (intAppCnt == 0) {
								cel = new PdfPCell(new Paragraph(
										"APPENDIX", strFontUnderLine));
								cel.setBorderColor(new Color(255, 255,255));
								cel.setColspan(9);
								tAppendix.addCell(cel);
	
								cel = new PdfPCell(new Paragraph(
									"\n",strFontNoUnderLine));
								cel.setBorderColor(new Color(255, 255,255));
								cel.setColspan(10);
								tAppendix.addCell(cel);
							}
							//Ends here CR_97
							cel = new PdfPCell(new Paragraph(String
									.valueOf(strImageName), strFontSizeBoldTen));
							cel.setColspan(20);
							cel.setBorderColor(new Color(255, 255, 255));
							alignCenter(cel);
							tAppendix.addCell(cel);
	
							cel = new PdfPCell(
									new Paragraph("", strFontSizeBoldTen));
							cel.setColspan(20);
							cel.setBorderColor(new Color(255, 255, 255));
							alignCenter(cel);
							tAppendix.addCell(cel);
	
							// image.setAbsolutePosition(10f,720f);
	
							// image.setAlignment(Element.ALIGN_LEFT);
							cel = new PdfPCell(image);// true denotes fit to cell
							cel.setColspan(20);
							cel.setBorderColor(new Color(255, 255, 255));
							alignCenter(cel);
							tAppendix.addCell(cel);
	
							cel = new PdfPCell(
									new Paragraph("", strFontSizeBoldTen));
							cel.setColspan(20);
							cel.setBorderColor(new Color(255, 255, 255));
							alignCenter(cel);
							tAppendix.addCell(cel);
	
							cel = new PdfPCell(new Paragraph(String
									.valueOf((strImageDesc == null) ? ""
											: strImageDesc), strFontSizeNoBoldTen));
							cel.setColspan(20);
							cel.setBorderColor(new Color(255, 255, 255));
							alignCenter(cel);
							tAppendix.addCell(cel);
	
							cel = new PdfPCell(
									new Paragraph("", strFontSizeBoldTen));
							cel.setColspan(20);
							cel.setBorderColor(new Color(255, 255, 255));
							alignCenter(cel);
							tAppendix.addCell(cel);
	
							cel = new PdfPCell(
									new Paragraph("", strFontSizeBoldTen));
							cel.setColspan(20);
							cel.setBorderColor(new Color(255, 255, 255));
							alignCenter(cel);
							tAppendix.addCell(cel);
	
							if (!writer.fitsPage(tAppendix)) {
								document.newPage();
							}
							document.add(tAppendix);
							output.reset();
							output.flush();
							}
							intAppCnt++;
							//Modification for CR_97 Ends here
						}
					}
					else {
						LogUtil.logMessage("Inside else of null strdispflag  ");
						PdfPTable tAppendix = new PdfPTable(10);
						tAppendix.setWidthPercentage(100);
						
						PdfPCell cel = new PdfPCell(
								new Paragraph("", strFontNoUnderLine));
						cel.setBorderColor(new Color(255, 255,
								255));
						tAppendix.addCell(cel);
						
						cel = new PdfPCell(new Paragraph(
								"APPENDIX", strFontUnderLine));
						cel.setBorderColor(new Color(255, 255,255));
						cel.setColspan(9);
						tAppendix.addCell(cel);
						
						document.add(tAppendix);				
					}


				}

			} //PDF appendix page is generated with just heading so as to ensure bookmarks are applied right Modified for CR - 135
			else {
				
				PdfPTable tAppendix = new PdfPTable(10);
				tAppendix.setWidthPercentage(100);
				
				PdfPCell cel = new PdfPCell(
						new Paragraph("", strFontNoUnderLine));
				cel.setBorderColor(new Color(255, 255,
						255));
				tAppendix.addCell(cel);
				
				cel = new PdfPCell(new Paragraph(
						"APPENDIX", strFontUnderLine));
				cel.setBorderColor(new Color(255, 255,255));
				cel.setColspan(9);
				tAppendix.addCell(cel);
				
				document.add(tAppendix);				
			}

		} catch (Exception objExp) {

			throw objExp;
		}

	}

	/***************************************************************************
	 * This method is used to set alignment for PdfPCell
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param PdfPCell
	 * @author VV49326
	 **************************************************************************/
	private static void alignCenter(PdfPCell c) {
		c.setHorizontalAlignment(Element.ALIGN_CENTER);

	}

	/***************************************************************************
	 * This method is used to show Appendix images
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param document
	 * @param Order
	 *            key
	 * @param writer
	 * @param flag
	 * @param UserID
	 * @author VV49326
	 **************************************************************************/

	private static void showPerformanceCurve(Document document,
			int intOrderKey, PdfWriter writer, String strUserID,
			String strFlag, int intRevCode) throws Exception {

		try {
			
			LogUtil.logMessage("Inside showPerformanceCurve ");

			OrderPerfCurveBI objOrderPerfCurveBO = ServiceFactory
					.getOrderPerfCurveBO();
			PerformanceCurveVO objPerformanceCurveVO = new PerformanceCurveVO();
			ArrayList arlPerCurveList = new ArrayList();
			int intPerfCurve = 0;
			Set colPerfCurve = new HashSet();
			StringBuffer sbPerfCurve = new StringBuffer();

			FileUploadBI objFileUploadBO = ServiceFactory.getFileUploadBO();
			FileVO objFileVO = new FileVO();
			FileVO objjFileVO = new FileVO();

			// Added for CR-76
			String strImgMarker = "";
			String strImgMarkDesc = "";

			// To fetch Performance Curves
			objPerformanceCurveVO
					.setDataLocationType(DatabaseConstants.DATALOCATION);
			objPerformanceCurveVO.setRevisionInput(intRevCode);
			objPerformanceCurveVO.setUserID(strUserID);
			objPerformanceCurveVO.setOrderKey(intOrderKey);

			arlPerCurveList = objOrderPerfCurveBO
					.fetchPerfCurveImages(objPerformanceCurveVO);
			
			//
			LogUtil.logMessage("After fetchPerfCurveImages ");
			LogUtil.logMessage("After arlPerCurveList " +arlPerCurveList);
			/*If condition added for PDF issue with performance curve empty  for CR-125 Dated 01-Aug-2014*/
			if ( arlPerCurveList.size() == 0) {
				
				document.newPage();
				
				PdfPTable tPerfCurve = new PdfPTable(10);
				tPerfCurve.setWidthPercentage(100);
				// setTableProperties(tPerfCurve);

				PdfPCell cPerfCurve = new PdfPCell(
						new Paragraph("", strFontNoUnderLine));
				cPerfCurve.setBorderColor(new Color(255, 255,
						255));
				tPerfCurve.addCell(cPerfCurve);
				cPerfCurve = new PdfPCell(new Paragraph(
						"PERFORMANCE CURVES", strFontUnderLine));

				cPerfCurve.setBorderColor(new Color(255, 255,
						255));
				cPerfCurve.setColspan(9);
				tPerfCurve.addCell(cPerfCurve);

				/*
				 * cPerfCurve = new PdfPCell(new
				 * Phrase("\n"));// new // line // add
				 * cPerfCurve.setBorderColor(new Color(255, 255,
				 * 255)); cPerfCurve.setColspan(10);
				 * tPerfCurve.addCell(cPerfCurve);
				 */

				// Changed for CR-76
				/*
				 * cPerfCurve = new PdfPCell(new Paragraph( "",
				 * strFontNoUnderLine));
				 * cPerfCurve.setBorderColor(new Color(255, 255,
				 * 255)); tPerfCurve.addCell(cPerfCurve);
				 */

				// Added for CR-76
				cPerfCurve = new PdfPCell(new Paragraph("\n",
						strFontNoUnderLine));
				cPerfCurve.setBorderColor(new Color(255, 255,
						255));
				cPerfCurve.setColspan(10);
				tPerfCurve.addCell(cPerfCurve);

				if (strImgMarker != null
						&& strImgMarker.equalsIgnoreCase("Y")) {

					cPerfCurve = new PdfPCell(new Paragraph("",
							strFontNoUnderLine));
					cPerfCurve.setBorderColor(new Color(255,
							255, 255));
					cPerfCurve.setColspan(1);
					tPerfCurve.addCell(cPerfCurve);

					PdfPCell c = new PdfPCell(new Paragraph(
							strImgMarkDesc,
							strFointSizeBoldSixBlack));
					c.setBorderColor(new Color(255, 255, 255));
					c
							.setBackgroundColor(new Color(59,
									185, 255));
					c
							.setHorizontalAlignment(Element.ALIGN_CENTER);
					c
							.setVerticalAlignment(Element.ALIGN_MIDDLE);
					c.setColspan(1);
					setCellPadding(c);
					tPerfCurve.addCell(c);

					c = new PdfPCell(new Paragraph("",
							strFontNoUnderLine));
					c.setBorderColor(new Color(255, 255, 255));
					c.setColspan(8);
					tPerfCurve.addCell(c);

				} else {
					cPerfCurve = new PdfPCell(new Paragraph("",
							strFontNoUnderLine));
					cPerfCurve.setBorderColor(new Color(255,
							255, 255));
					cPerfCurve.setColspan(10);
					tPerfCurve.addCell(cPerfCurve);

				}

				// Added for CR-76
				if (sbPerfCurve != null
						&& sbPerfCurve.length() > 0) {
					cPerfCurve = new PdfPCell(new Paragraph("",
							strFontNoUnderLine));
					cPerfCurve.setBorderColor(new Color(255,
							255, 255));
					// Changed for CR-76
					cPerfCurve.setColspan(1);
					tPerfCurve.addCell(cPerfCurve);

					cPerfCurve = new PdfPCell(new Paragraph("["
							+ sbPerfCurve.toString() + "]",
							strFointSizeBoldNineRed));
					cPerfCurve.setLeading(0f, 1.0f);
					cPerfCurve.setBorderColor(new Color(255,
							255, 255));
					cPerfCurve.setColspan(9);
					tPerfCurve.addCell(cPerfCurve);

					// Changed for CR-76
					/*
					 * cPerfCurve = new PdfPCell(new
					 * Phrase("\n"));// new // line // add
					 * cPerfCurve.setBorderColor(new Color(255,
					 * 255, 255)); cPerfCurve.setColspan(10);
					 * tPerfCurve.addCell(cPerfCurve);
					 */

				} else {

					cPerfCurve = new PdfPCell(new Phrase("\n"));// new
					// line
					// add
					cPerfCurve.setBorderColor(new Color(255,
							255, 255));
					cPerfCurve.setColspan(10);
					tPerfCurve.addCell(cPerfCurve);
				}

				document.add(tPerfCurve);
				if (strFlag.equalsIgnoreCase("N")) {
					alSecionNo.add(new Integer(writer
							.getCurrentPageNumber()));
					alSections.add("PERFORMANCE CURVES");
				}

			}
			/*If condition added for PDF issue with performance curve empty for CR-125 Dated 01-Aug-2014 Ends here*/
			
			
			if (arlPerCurveList.size() > 0) { //Added for CR-125 Production fix dated 01-Aug-2014
			
				ByteArrayOutputStream byteArrayOutputStream = null;
				InputStream sImage = null;
				ByteArrayOutputStream output = null;
				PdfContentByte cb = writer.getDirectContent();
			
			

			// Added for LSDB_CR-74 by KA57588 -- Starts here
			// This is for getting all the Performance Curve Revision

			//if (arlPerCurveList.size() > 0) { //Commented for for CR-125 Production fix dated 01-Aug-2014

				for (int cnt = 0; cnt < arlPerCurveList.size(); cnt++) {

					PerformanceCurveVO objjPerformanceCurveVO = (PerformanceCurveVO) arlPerCurveList
							.get(cnt);

					// Added for CR-76 and changed after issue fix 14-08-09
					if (objjPerformanceCurveVO.getSysMarkFlag() != null
							&& objjPerformanceCurveVO.getSysMarkFlag()
									.equalsIgnoreCase("Y")) {

						strImgMarker = objjPerformanceCurveVO.getSysMarkFlag();
						strImgMarkDesc = objjPerformanceCurveVO
								.getSysMarkDesc();
					}

					// Added for LSDB_CR-74 by KA57588 --Starts here
					ArrayList arlPerfCurveRev = (ArrayList) objjPerformanceCurveVO
							.getRevCode();

					if (arlPerfCurveRev != null && arlPerfCurveRev.size() > 0) {

						for (int j = 0; j < arlPerfCurveRev.size(); j++) {

							colPerfCurve.add((String) arlPerfCurveRev.get(j));
						}
					}
				}
			//} Commented for CR-125 Production fix dated 01-Aug-2014
			
			
			SortedSet sortPerfCurve = new TreeSet(colPerfCurve);
			Iterator itPerfCurve = sortPerfCurve.iterator();
			int indx = 0;
			while (itPerfCurve.hasNext()) {

				if (indx == 0)
					sbPerfCurve.append((String) itPerfCurve.next());
				else
					sbPerfCurve.append("," + (String) itPerfCurve.next());

				indx++;

			}
			// Ends here
			
			//if (arlPerCurveList.size() > 0) { //Commented for CR-125 Production fix dated 01-Aug-2014
		
				for (int cnt = 0; cnt < arlPerCurveList.size(); cnt++) {

					PerformanceCurveVO objjPerformanceCurveVO = (PerformanceCurveVO) arlPerCurveList
							.get(cnt);

					// Commented for Bookmark Issue 15-Apr-09 VV49326
					/*
					 * if (intPerfCurve == 0) { PdfDestination d2 = new
					 * PdfDestination( PdfDestination.FIT); PdfOutline root1 =
					 * writer.getRootOutline(); PdfOutline oline1 = new
					 * PdfOutline(root1, d2, "PERFORMANCE CURVES"); }
					 */

					int intImageSeqNo = objjPerformanceCurveVO.getFileVO()
							.getImageSeqNo();

					String strContentType = objjPerformanceCurveVO.getFileVO()
							.getContentType();

					if (intImageSeqNo != 0) {
						document.newPage();
						// Added after bookmark Issue 15-Apr-09 VV49326
						if (intPerfCurve == 0) {
							PdfDestination d2 = new PdfDestination(
									PdfDestination.FIT);
							PdfOutline root1 = writer.getRootOutline();
							PdfOutline oline1 = new PdfOutline(root1, d2,
									"PERFORMANCE CURVES");
						}
						objFileVO.setImageSeqNo(intImageSeqNo);
						objjFileVO = objFileUploadBO.downloadImage(objFileVO);
						sImage = objjFileVO.getFileStream();

						if (strContentType.equalsIgnoreCase("application/pdf")) {

							PdfReader reader = new PdfReader(sImage); // byte
							// array
							// from clob
							for (int x = 1; x <= reader.getNumberOfPages(); x++) {
								PdfImportedPage page = writer.getImportedPage(
										reader, x);

								if (intPerfCurve == 0 && x == 1) {
									Rectangle psize = reader.getPageSize(1);
									float width = psize.getHeight();
									float height = psize.getWidth();
									cb.addTemplate(page, .5f, 0, 0, .5f, width,
											height);

									BaseFont bf = FontFactory.getFont(
											FontFactory.TIMES_ROMAN, 12)
											.getCalculatedBaseFont(true);

									cb.beginText();
									cb.setFontAndSize(bf, 12);
									cb.showTextAligned(
											PdfContentByte.ALIGN_CENTER,
											"PERFORMANCE CURVES", 140, 790, 0);//Edited for PDF logo alignment issue
									cb.endText();

									cb.setLineWidth(0.5f);
									cb.moveTo(70, 785);//Edited for PDF logo alignment issue
									cb.lineTo(210, 785);//Edited for PDF logo alignment issue
									cb.stroke();

									// Added for CR-76
									PdfPTable perfCurMark = new PdfPTable(1);
									if (strImgMarker != null
											&& strImgMarker.equals("Y")) {

										Rectangle rec = new Rectangle(70, 770,
												120, 785);
										rec.setBackgroundColor(new Color(59,
												185, 255));
										rec.setBorderColor(new Color(255, 255,
												255));
										cb.rectangle(rec);
										cb.stroke();

										BaseFont bf1 = FontFactory.getFont(
												FontFactory.TIMES_ROMAN, 12)
												.getCalculatedBaseFont(true);
										cb.beginText();
										cb.setFontAndSize(bf1, 7);
										cb.setRGBColorFill(0, 0, 0);
										cb.showTextAligned(
												PdfContentByte.ALIGN_LEFT,
												strImgMarkDesc, 77, 774, 0);
										cb.endText();
										cb.stroke();

									}

									if (sbPerfCurve != null
											&& sbPerfCurve.length() > 0) {
										BaseFont bf1 = FontFactory.getFont(
												FontFactory.TIMES_ROMAN, 12)
												.getCalculatedBaseFont(true);
										cb.beginText();
										cb.setFontAndSize(bf1, 9);
										cb.setRGBColorFill(255, 0, 0);
										cb.showTextAligned(
												PdfContentByte.ALIGN_LEFT, "["
														+ sbPerfCurve
																.toString()
														+ "]", 70, 760, 0);
										cb.endText();
										cb.stroke();
									}

									if (strFlag.equalsIgnoreCase("N")) {
										alSecionNo.add(new Integer(writer
												.getCurrentPageNumber()));
										alSections.add("PERFORMANCE CURVES");
									}

								}

								Image image = Image.getInstance(page);
								image.setAlignment(Element.ALIGN_CENTER);
								document.add(image);
							}
						} else {

							output = new ByteArrayOutputStream();
							byte[] rb = new byte[1024];
							int ch = 0;
							// get content of a document
							while ((ch = sImage.read(rb)) != -1) {
								output.write(rb, 0, ch);
							}

							byte[] buf = output.toByteArray();
							byteArrayOutputStream = new ByteArrayOutputStream();
							byteArrayOutputStream.write(buf);
							Image image = Image
									.getInstance(byteArrayOutputStream
											.toByteArray());

							if (image.getScaledWidth() > 550
									|| image.getScaledHeight() > 600) {

								throw new Exception(
										"(Performance Curve Image Seq No -"
												+ intImageSeqNo
												+ ")- Image size should be lesser or equal (550*600) in pixels");

							}
							// Ends here

							if (intPerfCurve == 0) {

								PdfPTable tPerfCurve = new PdfPTable(10);
								tPerfCurve.setWidthPercentage(100);
								// setTableProperties(tPerfCurve);

								PdfPCell cPerfCurve = new PdfPCell(
										new Paragraph("", strFontNoUnderLine));
								cPerfCurve.setBorderColor(new Color(255, 255,
										255));
								tPerfCurve.addCell(cPerfCurve);
								cPerfCurve = new PdfPCell(new Paragraph(
										"PERFORMANCE CURVES", strFontUnderLine));

								cPerfCurve.setBorderColor(new Color(255, 255,
										255));
								cPerfCurve.setColspan(9);
								tPerfCurve.addCell(cPerfCurve);

								/*
								 * cPerfCurve = new PdfPCell(new
								 * Phrase("\n"));// new // line // add
								 * cPerfCurve.setBorderColor(new Color(255, 255,
								 * 255)); cPerfCurve.setColspan(10);
								 * tPerfCurve.addCell(cPerfCurve);
								 */

								// Changed for CR-76
								/*
								 * cPerfCurve = new PdfPCell(new Paragraph( "",
								 * strFontNoUnderLine));
								 * cPerfCurve.setBorderColor(new Color(255, 255,
								 * 255)); tPerfCurve.addCell(cPerfCurve);
								 */

								// Added for CR-76
								cPerfCurve = new PdfPCell(new Paragraph("\n",
										strFontNoUnderLine));
								cPerfCurve.setBorderColor(new Color(255, 255,
										255));
								cPerfCurve.setColspan(10);
								tPerfCurve.addCell(cPerfCurve);

								if (strImgMarker != null
										&& strImgMarker.equalsIgnoreCase("Y")) {

									cPerfCurve = new PdfPCell(new Paragraph("",
											strFontNoUnderLine));
									cPerfCurve.setBorderColor(new Color(255,
											255, 255));
									cPerfCurve.setColspan(1);
									tPerfCurve.addCell(cPerfCurve);

									PdfPCell c = new PdfPCell(new Paragraph(
											strImgMarkDesc,
											strFointSizeBoldSixBlack));
									c.setBorderColor(new Color(255, 255, 255));
									c
											.setBackgroundColor(new Color(59,
													185, 255));
									c
											.setHorizontalAlignment(Element.ALIGN_CENTER);
									c
											.setVerticalAlignment(Element.ALIGN_MIDDLE);
									c.setColspan(1);
									setCellPadding(c);
									tPerfCurve.addCell(c);

									c = new PdfPCell(new Paragraph("",
											strFontNoUnderLine));
									c.setBorderColor(new Color(255, 255, 255));
									c.setColspan(8);
									tPerfCurve.addCell(c);

								} else {
									cPerfCurve = new PdfPCell(new Paragraph("",
											strFontNoUnderLine));
									cPerfCurve.setBorderColor(new Color(255,
											255, 255));
									cPerfCurve.setColspan(10);
									tPerfCurve.addCell(cPerfCurve);

								}

								// Added for CR-76
								if (sbPerfCurve != null
										&& sbPerfCurve.length() > 0) {
									cPerfCurve = new PdfPCell(new Paragraph("",
											strFontNoUnderLine));
									cPerfCurve.setBorderColor(new Color(255,
											255, 255));
									// Changed for CR-76
									cPerfCurve.setColspan(1);
									tPerfCurve.addCell(cPerfCurve);

									cPerfCurve = new PdfPCell(new Paragraph("["
											+ sbPerfCurve.toString() + "]",
											strFointSizeBoldNineRed));
									cPerfCurve.setLeading(0f, 1.0f);
									cPerfCurve.setBorderColor(new Color(255,
											255, 255));
									cPerfCurve.setColspan(9);
									tPerfCurve.addCell(cPerfCurve);

									// Changed for CR-76
									/*
									 * cPerfCurve = new PdfPCell(new
									 * Phrase("\n"));// new // line // add
									 * cPerfCurve.setBorderColor(new Color(255,
									 * 255, 255)); cPerfCurve.setColspan(10);
									 * tPerfCurve.addCell(cPerfCurve);
									 */

								} else {

									cPerfCurve = new PdfPCell(new Phrase("\n"));// new
									// line
									// add
									cPerfCurve.setBorderColor(new Color(255,
											255, 255));
									cPerfCurve.setColspan(10);
									tPerfCurve.addCell(cPerfCurve);
								}

								document.add(tPerfCurve);
								if (strFlag.equalsIgnoreCase("N")) {
									alSecionNo.add(new Integer(writer
											.getCurrentPageNumber()));
									alSections.add("PERFORMANCE CURVES");
								}

							}

							image.setAlignment(Element.ALIGN_CENTER);
							document.add(image);
							output.reset();
							output.flush();
							// Added after prod issue
							byteArrayOutputStream.reset();
							byteArrayOutputStream.flush();
						}

						intPerfCurve++;
					}
				}
			}

		} catch (Exception objExp) {
			throw objExp;
		}
		LogUtil.logMessage("outside showPerformanceCurve ");

	}

	/***************************************************************************
	 * This method is for getting Clause Table Data Column Count
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ArrayList
	 * @return ArrayList
	 * @throws Exception
	 * @author VV49326
	 **************************************************************************/
	private static ArrayList getTableDataColumnsCount(ArrayList arlTableCol)
			throws Exception {

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
						// colCount = colCount + 1;

					}

				}
				if (intCnt < colCount) {
					intCnt = colCount;
				}

			}

			arlOut.add(new Integer(intCnt + 1));

			arlOut.add(arlWholeData);

		} catch (Exception objExp) {

			throw objExp;

		} finally {
				
			try {
				// closeSQLObjects(rsTableCol, null, null);
			} catch (Exception objExp) {
				LogUtil.logMessage("AAAAAAAAAAAAAA");
				throw objExp;
			}
		}

		return arlOut;
	}

	/***************************************************************************
	 * This method is used to set alignment for PdfPCell
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param PdfPCell
	 * @author VV49326
	 **************************************************************************/

	private static void alignLeft(PdfPCell c1) {
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);

	}

	/***************************************************************************
	 * This method is used to Generate Spec Supplement  
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param Orderkey, strRevCod, strUserID, objHttpServletResponse
	 * @author Sekar
	 **************************************************************************/
	//Modified for CR_106 to bring Sales Supplement changes
	public static void GenerateSpecSupplimentPDF(int intOrderKey,int intPrevOrderKey,String strUserID,int strRevCod,
			String pdfType,HttpServletResponse objHttpServletResponse)
	throws SQLException, Exception {
		
		LogUtil.logMessage("GenerateSpecSupplimentPDF :::: inside ");
				
		PdfWriter writer = null;
		
		String strSpecFileName = "";
		OrderVO objOrderVO = new OrderVO();
		ArrayList arlSpecSupplementList = new ArrayList();	
		
		StringBuffer sbFile = new StringBuffer();
		String strWaterMarkFlag="";
		try {
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Document document = new Document(PageSize.A4, 7, 0, 34, 100);//Edited for CR-130 PDF logo alignment issue
			writer = PdfWriter.getInstance(document, baos);
			FontFactory.registerDirectories();
			
			document.addAuthor("EMD-LSDB");
			document.addSubject("EMD-LSDB Proofreading Draft PDF ");
			document.addCreationDate();
			document.open();

			// To fetch Versions
			
			objOrderVO.setOrderKey(intOrderKey);
			objOrderVO.setUserID(strUserID);
			//Modified for CR_106 - On Demand Spec Supplement
			if (intPrevOrderKey != 0)
				objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION_SNAP_SHOT);
			else
				objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			objOrderVO.setPrevOrderKey(intPrevOrderKey);
			
			OrderBI objOrderBI  = ServiceFactory.getOrderBO();
			SpecSuppVO objSpSupVersionVO = new SpecSuppVO();
			objSpSupVersionVO = objOrderBI.getVersions(objOrderVO);
						
			strPastRevCode = objSpSupVersionVO.getStrPastRevCode();
			strPresentRevCode = objSpSupVersionVO.getStrPresentRevCode();
			strPublishDate = objSpSupVersionVO.getStrPublishDate();
			strPastSpecStatusDesc = objSpSupVersionVO.getStrPastSpecStatusDesc();
			strPresentSpecStatusDesc = objSpSupVersionVO.getStrPresentSpecStatusDesc();
			strQtyInWords = objSpSupVersionVO.getStrQtyInWords();
			strOpeningDate = objSpSupVersionVO.getStrOpeningDate();	
			
			//Added for CR-135 starts
			if(intPrevOrderKey!=0){
				SpecSuppVO objSpSupPervVersionVO = new SpecSuppVO();
				objSpSupPervVersionVO = objOrderBI.getPrevVersions(objOrderVO);	
				strPrevQtyInWords = objSpSupPervVersionVO.getStrQtyInWords();
				strPresentPrevSpecStatusDesc = objSpSupPervVersionVO.getStrPresentSpecStatusDesc();
				strPrevRevCode = objSpSupPervVersionVO.getStrPresentRevCode();
				LogUtil.logMessage("strPrevRevCode " +strPrevRevCode);
			}
			//Added for CR-135 ends here
			//Modified to add name for On Demand Spec Supplement
			if (intPrevOrderKey == 0)	{			
				if (!strPresentSpecStatusDesc.equals("Final")&& intStatusCode >= 3) {
					
					sbFile.append(strOrderNo);
					sbFile.append("_");
					sbFile.append("REV");
					sbFile.append("_");
					sbFile.append(strPresentRevCode);
					
				} else {
					
					sbFile.append(strOrderNo);
					sbFile.append("_");
					sbFile.append(strPresentSpecStatusDesc);
					
				}
			} else	{
				if (!strPresentSpecStatusDesc.equals("Final")&& intStatusCode > 3) {
					
					sbFile.append(strOrderNo);
					sbFile.append("_");
					sbFile.append("REV");
					sbFile.append("_");
					sbFile.append(strPresentRevCode);
					
				} else {
					
					sbFile.append(strOrderNo);
					sbFile.append("_");
					sbFile.append(strPresentSpecStatusDesc);
					if (strPresentRevCode!=null)
						sbFile.append(strPresentRevCode);					
				}
			}
			//Modified for CR_106 to bring Sales Supplement changes
			if ("EngrSupp".equalsIgnoreCase(pdfType))	
				sbFile.append("_Engineering_Supplement.pdf");
			else if ("SalesSupp".equalsIgnoreCase(pdfType))	
				sbFile.append("_Sales_Supplement.pdf");
				
			strSpecFileName = sbFile.toString();
			//Modified for CR_106 - Ondemand Spec Supplement
			if (intPrevOrderKey!=0)
			{ strWaterMarkFlag = ApplicationConstants.NO ;}
			else
			{ strWaterMarkFlag = ApplicationConstants.YES;}
			//CR_106 Ends here
			//Modified for CR_100 to add Order Number to PDF Footer for Proof Reading
			PDFView objMyPage = new PDFView(strWaterMarkFlag,strHeaderFlag,strOrderNo,strPresentSpecStatusDesc);
						
			writer.setPageEvent(objMyPage);			
			
			document.newPage();
			PdfDestination d1 = new PdfDestination(PdfDestination.FIT);
			PdfOutline root = writer.getRootOutline();
			
			Table header = new Table(10);
			setTableProperties(header);
			
			showHeaderInformationSpecSuppliment(header, document,
					strPresentRevCode, strCustomerName, strCustomerCode,
					strOrderNo,strPrevOrderNo, strModelName, intQuantity,strPrevCustName,
					strPrevCustCode,strPrevQuantity,strPrevModelName,strPrevRevCode);//Parameters added for Cr-135
			
			document.add(header);
			
			document.newPage();			
			
			objOrderBI = ServiceFactory.getOrderBO();
			
			arlSpecSupplementList = objOrderBI.fetchSpecSupplementDetails(objOrderVO);
			//Modified for CR_106 to bring Sales Supplement changes			
			fetchSpecSupplementDetails(intOrderKey, strUserID,arlSpecSupplementList,
					document, writer, pdfType);
			
			document.close();

				// setting the content type
				objHttpServletResponse.setContentType("application/pdf");
				objHttpServletResponse.setHeader("Content-disposition",
						"attachment;filename=" + strSpecFileName);

				objHttpServletResponse.setContentLength(baos.size());

				// write ByteArrayOutputStream to the ServletOutputStream
				ServletOutputStream out = objHttpServletResponse
						.getOutputStream();
				baos.writeTo(out);
				out.flush();					
			
		}catch (Exception objExp) {
			throw objExp;
		}
	}
	
	/***************************************************************************
	 * This method is used to Show Header Information for Spec Supplement
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param Table header,
			Document document, String strRevCode, String strCustomerName,
			String strCustomerCode, String strOrderNo, String strModelName,
			int intQuantity
	 * @author Sekar
	 **************************************************************************/
	
	private static void showHeaderInformationSpecSuppliment(Table header,
			Document document, String strRevCode, String strCustomerName,
			String strCustomerCode, String strOrderNo, String strPrevOrderNo, String strModelName,
			int intQuantity, String strPrevCustName, String strPrevCustCode, String strPrevQuantity, 
			String strPrevModelName, String strPrevRevCode ) throws Exception {
		
		String strQuantity = "";
		String strPastRev = "";
		//Added for CR-135
		String strFinalOrderNo = "";	
		String strFinalCustName = "";
		String strFinalQuantity = "";
		String strFinalCustCode = "";
		String strFinalModelName = "";
		String strFinalSpecStatusDesc = "";
		
		try {
			java.util.Date date = new java.util.Date();
			DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
			String strDate = df.format(new Date());
			
			if (intQuantity != 0)
				strQuantity = "(" + intQuantity + ") ";
            
			if (strPrevQuantity != null)
				strPrevQuantity = "(" + strPrevQuantity + ") ";//If condition added for CR-135
			
			StringBuffer sbPresentRev = new StringBuffer();
			
			/*
			 * Modified for LSDB_CR-74 from DRAFT1
			 */
			sbPresentRev.append("\"");
			sbPresentRev.append(strPresentSpecStatusDesc);
			if(strRevCode != null && !"null".equals(strRevCode)){
				sbPresentRev.append(" ");
				sbPresentRev.append((strRevCode==null)? "" :strRevCode);
				
			}			
			/** Modified for Spec Supplement Description to eliminate Quotes 
			else{
			
				strRevCode = "";
			}
			*/
			sbPresentRev.append("\"");
			//Ends
			StringBuffer sbPreRevCode=new StringBuffer();
			if(strRevCode != null && !"null".equals(strRevCode)){
				sbPreRevCode.append("\"");
				sbPreRevCode.append((strRevCode==null)? "" :strRevCode);
				sbPreRevCode.append("\"");
			}
			//Added for CR-135
			StringBuffer sbPrevRevCode=new StringBuffer();
			if(strPrevRevCode != null && !"null".equals(strPrevRevCode)){
				sbPrevRevCode.append("\"");
				sbPrevRevCode.append((strPrevRevCode==null)? "" :strPrevRevCode);
				sbPrevRevCode.append("\"");
			}
			//Ends			
			StringBuffer sbPastRev = new StringBuffer();
			sbPastRev.append("\"");
			sbPastRev.append(strPastSpecStatusDesc);
			if (strPastRevCode != null && !"null".equals(strPastRevCode)) {
				sbPastRev.append(" ");
				sbPastRev.append((strPastRevCode==null)? "" :strPastRevCode);
				
			}
			sbPastRev.append("\"");
			
			String[] strPastDate = strPublishDate.split(",");
			
			strPublishDate = strPastDate[0] + "," + " " + strPastDate[1];
			
			StringBuffer sbpastRevDate = new StringBuffer();
			if (strPublishDate != null) {
				//Removed spaces for fixing spill over of lines
				sbpastRevDate.append("\"");
				sbpastRevDate.append(strPublishDate);
				sbpastRevDate.append("\"");
				
			}
			
			StringBuffer sbOpeningDate = new StringBuffer();
			if (strOpeningDate != null) {
				//Removed spaces for fixing spill over of lines
				sbOpeningDate.append("\"");
				sbOpeningDate.append(strOpeningDate);
				sbOpeningDate.append("\"");
				
			}
			
			strPastRev = (sbPastRev.toString() == null) ? "" : sbPastRev
					.toString();
			
			//Added for LSDB_CR_46 PM&I Change
			String strHeadSpecType = "";
			String strHeaderSpecLowerCase = "";
			//Modified for LSDB_CR_84 For adding new Specification Types
			if (intSpecTypeSeqNo == 2) {
				strHeadSpecType = ApplicationConstants.PM_I_SPEC_SUPPLIMENT;
				strHeaderSpecLowerCase = "Engine Specification";
            //Added for LSDB_CR_101 for adding LXO Specfication Type
			} else if (intSpecTypeSeqNo == 3)	{
				strHeadSpecType = ApplicationConstants.LXO_SPEC_SUPPLIMENT;
				strHeaderSpecLowerCase = "Locomotive Export Order Kit Specification";
			} else {
				strHeadSpecType = ApplicationConstants.LOCOMOTIVE_SPEC_SUPPLIMENT;
				strHeaderSpecLowerCase = "Locomotive Specification";
			}
			
			/** Spec Supplement Descriptionis modified for LSDB_CR-74 on 23-June-09 by ps57222 **/
			/** Modified for Spec Supplement Issue to change Description to Draft/Opening  **/
			String strSpecOrigin = "";
			if (intStatusCode >= 4) {
				strSpecOrigin += "Opening";
			}else{
				strSpecOrigin += "Draft";
			}
			
			String strSpecDesc = "Please refer to the" + " " + strSpecOrigin + " "
			/** Modified for Spec Supplement Issue to change Description to Draft/Opening - Ends here **/
			+ strHeaderSpecLowerCase + " dated " + sbOpeningDate.toString()
			+ " and" 
			//Commented lines to avoid wrong places of carriage return while working with LXO Orders
			//+ "\n"
			+ " Specification Supplement Revisions through " + "" + sbPastRev.toString()
			+ " dated " + sbpastRevDate + " and"
			//+ "\n" 
			+ " note that the following " + sbPresentRev.toString() 
			+ " changes are hereby released";
			
			if (intStatusCode >= 3) {
				strSpecDesc += " for production.";
			}else{
				strSpecDesc +=".";
			}
            
			
			//Added for CR-135 starts
			if(strPrevOrderNo != null){
				if(strOrderNo.equals(strPrevOrderNo)){
					strFinalOrderNo = strOrderNo;
					strFinalModelName = strModelName;
					strFinalCustName = strCustomerName;
					strFinalCustCode = strCustomerCode;
					strFinalQuantity = strQuantity;
					strFinalSpecStatusDesc = strPresentSpecStatusDesc + " " + sbPreRevCode.toString();
				}else{
					strFinalOrderNo = strOrderNo + " & " + strPrevOrderNo;
					strFinalModelName = strModelName + " & " + strPrevModelName;
					strFinalCustName = strCustomerName + " & " + strPrevCustName;
					strFinalCustCode = strCustomerCode + " & " + strPrevCustCode;
					strFinalQuantity = strQuantity + "& " + strPrevQtyInWords + " " + strPrevQuantity + " ";
					strFinalSpecStatusDesc = strPresentSpecStatusDesc + " " + sbPreRevCode.toString() + " & "
												+ strPresentPrevSpecStatusDesc + " " + sbPrevRevCode.toString();
				}
			}else{
				strFinalOrderNo = strOrderNo;
				strFinalModelName = strModelName;
				strFinalCustName = strCustomerName;
				strFinalCustCode = strCustomerCode;
				strFinalQuantity = strQuantity;
				strFinalSpecStatusDesc = strPresentSpecStatusDesc + " " + sbPreRevCode.toString();
			}
			//Added for CR-135 ends
			
			Cell cel = new Cell(new Paragraph(strDate, strFointSizeNoBold));
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(10);
			header.addCell(cel);
			
			cel = new Cell(new Paragraph(strHeadSpecType + " – " + ""
					+ strFinalSpecStatusDesc,
					strFontNoUnderLine));
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(10);
			header.addCell(cel);
			
			cel = new Cell(new Paragraph(
					"covering changes in constructions details for",
					strFointSizeNoBold));
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(10);
			header.addCell(cel);
			
			cel = new Cell(new Paragraph(strFinalCustName, strFointSizeNoBold));
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(10);
			header.addCell(cel);
			
			cel = new Cell(new Paragraph(strQtyInWords + " " + strFinalQuantity
					+ " ", strFointSizeNoBold));//Edited for CR-135
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(10);
			header.addCell(cel);
			
			cel = new Cell(new Paragraph(strFinalModelName, strFointSizeNoBold));//Edited for CR-135
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(10);
			header.addCell(cel);
			
			cel = new Cell(new Paragraph("EMD Customer Code No. "
					+ strFinalCustCode, strFointSizeNoBold));
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(10);
			header.addCell(cel);
			
						
			cel = new Cell(new Paragraph("EMD Order No. " +strFinalOrderNo ,strFontUnderLine));//Edited for CR-135
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(10);
			header.addCell(cel);
			
			cel = new Cell(new Paragraph(strSpecDesc, strFointSizeNoBold));
			cel.setHorizontalAlignment(Element.ALIGN_LEFT);
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(10);
			header.addCell(cel);
			
		} catch (Exception objExp) {
			throw objExp;
		}
	}
	
	/***************************************************************************
	 * This method is used to fetch all the Spec Supplement Details
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param int orderKey,
			String strUserID, ArrayList arlSpecSupplementList, Document document,
			PdfWriter writer
	 * @author Sekar
	 **************************************************************************/
	
	private static void fetchSpecSupplementDetails(int orderKey,
			String strUserID, ArrayList arlSpecSupplementList, Document document,
			PdfWriter writer, String pdfType) throws SQLException, Exception {
		
		try {
			
			if (arlSpecSupplementList != null && arlSpecSupplementList.size() > 0) {
				
				showSpecSupplementMainFeatures(arlSpecSupplementList,document, writer);
				
			    showSpecSupplementHpRating(arlSpecSupplementList, document, writer);
			    
			    showSpecSupplementSpecifications(arlSpecSupplementList, document, writer);
			    
			    showSpecSupplementGenArrtNotes(arlSpecSupplementList, document, writer);
			   
			    showSpecSupplementGenArrangement(arlSpecSupplementList, document, writer);
			    //Modified for CR_106 to bring Sales Supplement changes
			    showClauseHistory(arlSpecSupplementList, document, writer, pdfType);
			    
			    showSpecSupplementPerfCurves(arlSpecSupplementList, document, writer);
			}
			
		}catch (Exception objExp) {
			throw objExp;
		} 
		
	}
	
//	Added for CR_79 PDFHeader Image to Spec Suppliment on 05-Nov-09 by RR68151 
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
	 * This method is used to fetch all the show SpecSupplement Hp Rating
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ArrayList arlSpecSupplementList,
				Document document, PdfWriter writer
	 * @author Sekar
	 **************************************************************************/
	
		private static void showSpecSupplementHpRating(ArrayList arlSpecSupplementList,
				Document document, PdfWriter writer) throws SQLException, Exception {
		try {
			ArrayList arlHpRating;
			
			if (arlSpecSupplementList != null && arlSpecSupplementList.size() > 0) {
				arlHpRating=new ArrayList();				
				arlHpRating=(ArrayList)arlSpecSupplementList.get(1);
				
				if (arlHpRating != null && arlHpRating.size() > 0) {
				for (int i = 0; i < arlHpRating.size(); i++) {
					SpecSuppVO objjHpRating = (SpecSuppVO) arlHpRating.get(i);
					
				String strFromHpRating = "";
				String strToHpRating = "";
				String strReason = "";
				strFromHpRating = objjHpRating.getStrFromHpRating();
				strToHpRating = objjHpRating.getStrToHpRating();
				strReason = objjHpRating.getStrReason();
				
				PdfPTable subsec = new PdfPTable(2);
				subsec.setWidthPercentage(90);
				int[] width = { 11, 60 };
				subsec.setWidths(width);
				
				PdfPCell c = new PdfPCell(new Phrase("Description",
						strFontSizeBoldTen));
				c.setBackgroundColor(new Color(239, 239, 239));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase("Horse Power Rating",
						strFontSizeBoldTen));
				c.setBackgroundColor(new Color(239, 239, 239));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase("Change From", strFontSizeBoldTen));
				c.setBackgroundColor(new Color(239, 239, 239));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase(strFromHpRating, strSSFointSizeNine));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase("Change To", strFontSizeBoldTen));
				c.setBackgroundColor(new Color(239, 239, 239));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase(strToHpRating, strSSFointSizeNine));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase("Reason", strFontSizeBoldTen));
				c.setBackgroundColor(new Color(239, 239, 239));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase(strReason, strSSFointSizeNine));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				subsec.addCell(c);
 
				addNewLine(document);
				
				if (!writer.fitsPage(subsec)) {
					document.newPage(); 
					addNewLine(document);
				}
				
				document.add(subsec);
			}
				}}
		}
		catch (Exception objExp) {
			
			LogUtil.logMessage("Exception in showSpecSupplementHpRating");
			throw objExp;
		}
	}

	/***************************************************************************
	 * This method will get ResultSet as an input from the FetchSpecSupplementDetails
	 * and it will generate the Specifications details in Spec Supplement PDF
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ArrayList arlSpecSupplementList,
				Document document, PdfWriter writer
	 * @author Sekar
	 **************************************************************************/
		
	private static void showSpecSupplementSpecifications(ArrayList arlSpecSupplementList,
				Document document, PdfWriter writer) throws SQLException, Exception {
			
		try {
			
			ArrayList arlSupplementSpec;
			
			if (arlSpecSupplementList != null && arlSpecSupplementList.size() > 0) {
				arlSupplementSpec=new ArrayList();
				
				arlSupplementSpec=(ArrayList)arlSpecSupplementList.get(2);
				
				if (arlSupplementSpec != null && arlSupplementSpec.size() > 0) {
					SpecSuppVO objSpecSuppVO;
				for (int i = 0; i < arlSupplementSpec.size(); i++) {
					 objSpecSuppVO = (SpecSuppVO) arlSupplementSpec
							.get(i);
				
				String strFromSpecItemDesc = "";
				String strToSpecItemDesc = "";
				String strFromSpecItemValue = "";
				String strToSpecItemValue = "";
				String strReason = "";
				if (objSpecSuppVO.getStrFromSpecItemDesc() != null) {
					strFromSpecItemDesc = objSpecSuppVO.getStrFromSpecItemDesc();
					
				}
				if (objSpecSuppVO.getStrFromSpecItemValue() != null) {
					strFromSpecItemValue = objSpecSuppVO.getStrFromSpecItemValue();
				}
				if (objSpecSuppVO.getStrToSpecItemDesc() != null) {
					strToSpecItemDesc = objSpecSuppVO.getStrToSpecItemDesc();
				}
				if (objSpecSuppVO.getStrToSpecItemValue()!= null) {
					strToSpecItemValue = objSpecSuppVO.getStrToSpecItemValue();
				}
				strReason = objSpecSuppVO.getStrReason();
				
				PdfPTable subsec = new PdfPTable(3);
				subsec.setWidthPercentage(90);
				int[] width = { 13, 50, 20 };
				subsec.setWidths(width);
				
				PdfPCell c = new PdfPCell(new Phrase("Description",
						strFontSizeBoldTen));
				c.setBackgroundColor(new Color(239, 239, 239));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase("Specification Item",
						strFontSizeBoldTen));
				c.setBackgroundColor(new Color(239, 239, 239));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase("Value", strFontSizeBoldTen));
				c.setBackgroundColor(new Color(239, 239, 239));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase("Change From", strFontSizeBoldTen));
				c.setBackgroundColor(new Color(239, 239, 239));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase(strFromSpecItemDesc,
						strSSFointSizeNine));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase(strFromSpecItemValue,
						strSSFointSizeNine));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase("Change To", strFontSizeBoldTen));
				c.setBackgroundColor(new Color(239, 239, 239));
				c.setVerticalAlignment(Element.ALIGN_CENTER);
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
				subsec.addCell(c);
				
				c = new PdfPCell(
						new Phrase(strToSpecItemDesc, strSSFointSizeNine));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase(strToSpecItemValue,
						strSSFointSizeNine));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase("Reason", strFontSizeBoldTen));
				c.setBackgroundColor(new Color(239, 239, 239));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase(strReason, strSSFointSizeNine));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				c.setColspan(2);
				subsec.addCell(c);

				addNewLine(document);
				
				if (!writer.fitsPage(subsec)) {
					document.newPage();
					addNewLine(document);
				}
							
				document.add(subsec);
			}
			
		}}
			
		}catch (Exception objExp) {
			LogUtil.logMessage("Exception in showSpecSupplementSpecifications");
			throw objExp;
		}
	}

	/***************************************************************************
	 * This method will get ResultSet as an input from the FetchSpecSupplementDetails
	 * and it will generate the Genral Arrangement tNotes details in Spec Supplement PDF
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ArrayList arlSpecSupplementList,
				Document document, PdfWriter writer
	 * @author Sekar
	 **************************************************************************/

		private static void showSpecSupplementGenArrtNotes(ArrayList arlSpecSupplementList,
				Document document, PdfWriter writer) throws SQLException, Exception {
			
		try {
			
			ArrayList arlpecSuppGen;
			
			if (arlSpecSupplementList != null && arlSpecSupplementList.size() > 0) {
				arlpecSuppGen=new ArrayList();
				
				arlpecSuppGen=(ArrayList)arlSpecSupplementList.get(3);
				
				if (arlpecSuppGen != null && arlpecSuppGen.size() > 0) {
				for (int i = 0; i < arlpecSuppGen.size(); i++) {
					SpecSuppVO objSpecSuppVO = (SpecSuppVO) arlpecSuppGen
							.get(i);
			
				String strFromGenArrNotes = "";
				String strToGenArrNotes = "";
				String strReason = "";
				
				strFromGenArrNotes = objSpecSuppVO.getStrFromGenArrNotes();
				strToGenArrNotes = objSpecSuppVO.getStrToGenArrNotes();
				strReason = objSpecSuppVO.getStrReason();
				
				PdfPTable subsec = new PdfPTable(2);
				subsec.setWidthPercentage(90);
				int[] width = { 11, 60 };
				subsec.setWidths(width);
				
				PdfPCell c = new PdfPCell(new Phrase("Description",
						strFontSizeBoldTen));
				c.setBackgroundColor(new Color(239, 239, 239));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase("General Arrangement",
						strFontSizeBoldTen));
				c.setBackgroundColor(new Color(239, 239, 239));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase("Change From", strFontSizeBoldTen));
				c.setBackgroundColor(new Color(239, 239, 239));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase(strFromGenArrNotes,
						strSSFointSizeNine));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase("Change To", strFontSizeBoldTen));
				c.setBackgroundColor(new Color(239, 239, 239));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase(strToGenArrNotes, strSSFointSizeNine));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase("Reason", strFontSizeBoldTen));
				c.setBackgroundColor(new Color(239, 239, 239));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase(strReason, strSSFointSizeNine));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				subsec.addCell(c);

				addNewLine(document);
				
				if (!writer.fitsPage(subsec)) {
					document.newPage();
					addNewLine(document);
				}
				
				document.add(subsec);
				}
			}
			}
		}catch (Exception objExp) {
			LogUtil.logMessage("Exception in showSpecSupplementGenArrtNotes");
			throw objExp;
		}
	}

	/***************************************************************************
	 * This method will get ResultSet as an input from the FetchSpecSupplementDetails
	 * and it will generate the Genral Arrangement Image change details in Spec Supplement PDF
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ArrayList arlSpecSupplementList,
				Document document, PdfWriter writer
	 * @author Sekar
	 **************************************************************************/

	private static void showSpecSupplementGenArrangement(ArrayList arlSpecSupplementList,
				Document document, PdfWriter writer) throws SQLException, Exception {
		try {
			
			ArrayList arlSpecSupplementGenArrang;
			
			if (arlSpecSupplementList != null && arlSpecSupplementList.size() > 0) {
				arlSpecSupplementGenArrang=new ArrayList();
				arlSpecSupplementGenArrang=(ArrayList)arlSpecSupplementList.get(4);
				
				if (arlSpecSupplementGenArrang != null && arlSpecSupplementGenArrang.size() > 0) {
				for (int i = 0; i < arlSpecSupplementGenArrang.size(); i++) {
					SpecSuppVO objjMainFeatureInfoVO = (SpecSuppVO) arlSpecSupplementGenArrang
							.get(i);
							
					String strFromView = "";
					String strToView = "";
					String strReason = "";
					
					strFromView = objjMainFeatureInfoVO.getStrFromView();
					strToView = objjMainFeatureInfoVO.getStrToView();
					strReason = objjMainFeatureInfoVO.getStrReason();
					
					PdfPTable subsec = new PdfPTable(2);
					subsec.setWidthPercentage(90);
					int[] width = { 11, 60 };
					subsec.setWidths(width);
					
					PdfPCell c = new PdfPCell(new Phrase("Description",
							strFontSizeBoldTen));
					c.setBackgroundColor(new Color(239, 239, 239));
					c.setVerticalAlignment(Element.ALIGN_TOP);
					c.setHorizontalAlignment(Element.ALIGN_CENTER);
					subsec.addCell(c);
					
					c = new PdfPCell(new Phrase("General Arrangement",
							strFontSizeBoldTen));
					c.setBackgroundColor(new Color(239, 239, 239));
					c.setVerticalAlignment(Element.ALIGN_TOP);
					c.setHorizontalAlignment(Element.ALIGN_CENTER);
					subsec.addCell(c);
					
					c = new PdfPCell(new Phrase("Change From", strFontSizeBoldTen));
					c.setBackgroundColor(new Color(239, 239, 239));
					c.setVerticalAlignment(Element.ALIGN_TOP);
					c.setHorizontalAlignment(Element.ALIGN_CENTER);
					subsec.addCell(c);
					
					c = new PdfPCell(new Phrase(strFromView, strSSFointSizeNine));
					c.setVerticalAlignment(Element.ALIGN_TOP);
					subsec.addCell(c);
					
					c = new PdfPCell(new Phrase("Change To", strFontSizeBoldTen));
					c.setBackgroundColor(new Color(239, 239, 239));
					c.setVerticalAlignment(Element.ALIGN_TOP);
					c.setHorizontalAlignment(Element.ALIGN_CENTER);
					subsec.addCell(c);
					
					c = new PdfPCell(new Phrase(strToView, strSSFointSizeNine));
					c.setVerticalAlignment(Element.ALIGN_TOP);
					subsec.addCell(c);
					
					c = new PdfPCell(new Phrase("Reason", strFontSizeBoldTen));
					c.setBackgroundColor(new Color(239, 239, 239));
					c.setVerticalAlignment(Element.ALIGN_TOP);
					c.setHorizontalAlignment(Element.ALIGN_CENTER);
					subsec.addCell(c);
					
					c = new PdfPCell(new Phrase(strReason, strSSFointSizeNine));
					c.setVerticalAlignment(Element.ALIGN_TOP);
					subsec.addCell(c);
	
					addNewLine(document);
					if (!writer.fitsPage(subsec)) {
						document.newPage();
						addNewLine(document);
					}
						
				document.add(subsec);
					}
					}
				}
		}catch (Exception objExp) {
			LogUtil.logMessage("Exception in showSpecSupplementGenArrangement");
			throw objExp;
		}
	}

	/***************************************************************************
	 * This method will get ResultSet as an input from the FetchSpecSupplementDetails
	 * and it will generate the performance Curve Changes in Spec Supplement PDF
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ArrayList arlSpecSupplementList,
				Document document, PdfWriter writer
	 * @author Sekar
	 **************************************************************************/
	
	private static void showSpecSupplementPerfCurves(ArrayList arlSpecSupplementList,
			Document document, PdfWriter writer) throws SQLException, Exception {	
		try {
					
			if (arlSpecSupplementList != null && arlSpecSupplementList.size() > 0) {
				ArrayList arlSpecSuppPerfCurves=new ArrayList();
				
				arlSpecSuppPerfCurves=(ArrayList)arlSpecSupplementList.get(6);
				
				if (arlSpecSuppPerfCurves != null && arlSpecSuppPerfCurves.size() > 0) {
				
				for (int i = 0; i < arlSpecSuppPerfCurves.size(); i++) {
					SpecSuppVO objSpecSuppPerfCurves = (SpecSuppVO) arlSpecSuppPerfCurves
							.get(i);

				String strFromPerfCurve = "";
				String strToPerfCurve = "";
				String strReason = "";

				if (objSpecSuppPerfCurves.getStrFromPerfCurve() != null) {
					strFromPerfCurve = objSpecSuppPerfCurves.getStrFromPerfCurve();
				}
				if (objSpecSuppPerfCurves.getStrToPerfCurve() != null) {
					strToPerfCurve = objSpecSuppPerfCurves.getStrToPerfCurve();
				}
				strReason = objSpecSuppPerfCurves.getStrReason();
				
				PdfPTable subsec = new PdfPTable(2);
				subsec.setWidthPercentage(90);
				int[] width = { 11, 60 };
				subsec.setWidths(width);
				
				PdfPCell c = new PdfPCell(new Phrase("Description",
						strFontSizeBoldTen));
				c.setBackgroundColor(new Color(239, 239, 239));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase("Performance Curve",
						strFontSizeBoldTen));
				c.setBackgroundColor(new Color(239, 239, 239));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase("Change From", strFontSizeBoldTen));
				c.setBackgroundColor(new Color(239, 239, 239));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase(strFromPerfCurve, strSSFointSizeNine));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase("Change To", strFontSizeBoldTen));
				c.setBackgroundColor(new Color(239, 239, 239));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase(strToPerfCurve, strSSFointSizeNine));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase("Reason", strFontSizeBoldTen));
				c.setBackgroundColor(new Color(239, 239, 239));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase(strReason, strSSFointSizeNine));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				subsec.addCell(c);
 
				addNewLine(document);
				
				if (!writer.fitsPage(subsec)) {
					document.newPage();
					addNewLine(document);
				}
							
				document.add(subsec);
				Phrase phrase = new Phrase();
				phrase.add("\n");
				document.add(phrase);
						}
					}
				}
		} catch (Exception objExp){
			throw objExp;
		}			
	}

	/***************************************************************************
	 * This method will get ResultSet as an input from the FetchSpecSupplementDetails
	 * and it will generate the Clause History details in Spec Supplement PDF
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ArrayList arlSpecSupplementList,
				Document document, PdfWriter writer
	 * @author Sekar
	 **************************************************************************/

	private static void showClauseHistory(ArrayList arlSpecSupplementList,
			Document document, PdfWriter writer, String pdfType) throws SQLException, Exception {
		
		ArrayList arlClauseHistory=new ArrayList();
		ArrayList arlAllClauseHistory = new ArrayList();
		ClauseVO objCommonClauseVO = new ClauseVO();
		ClauseVO objFromClauseVO = new ClauseVO();
		ClauseVO objToClauseVO = new ClauseVO();
		//Modified for CR_106 to bring Sales Supplement changes
		int intCols = 0;
		String strSalesSysMarker;
		try {
			//Added for CR-130
			FontFactory.register("/images/times.ttf",BaseFont.HELVETICA);
			FontFactory.register("/images/timesbd.ttf",BaseFont.HELVETICA_BOLD);
			FontFactory.register("/images/timesi.ttf",BaseFont.HELVETICA_OBLIQUE);
			FontFactory.register("/images/timesbi.ttf",BaseFont.HELVETICA_BOLDOBLIQUE);
			
			BaseFont customBaseFont = BaseFont.createFont("/images/times.ttf",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
			Font customFont = new Font(customBaseFont,12);
			//Added for CR-130 ends

			if (arlSpecSupplementList != null && arlSpecSupplementList.size() > 0) {
				
				arlClauseHistory = (ArrayList)arlSpecSupplementList.get(5);

				if (arlClauseHistory != null && arlClauseHistory.size() > 0) {
					
				for (int j= 0; j < arlClauseHistory.size(); j++) {
				
				LogUtil.logMessage("arlClauseHistory current pointer : " + j);
				
				arlAllClauseHistory = (ArrayList)arlClauseHistory.get(j);
				
				objCommonClauseVO = (ClauseVO) arlAllClauseHistory.get(0);
				objFromClauseVO = (ClauseVO) arlAllClauseHistory.get(1);
				objToClauseVO = (ClauseVO) arlAllClauseHistory.get(2);
				//Modified for CR_106 to bring Sales Supplement changes
				strSalesSysMarker = objCommonClauseVO.getSaleSysMarker();
				
				if ((strSalesSysMarker.equalsIgnoreCase(ApplicationConstants.YES) 
						&& "SalesSupp".equalsIgnoreCase(pdfType) && !"26".equalsIgnoreCase(objCommonClauseVO.getSecCode())) 
							|| "EngrSupp".equalsIgnoreCase(pdfType))	{
				
					if ("EngrSupp".equalsIgnoreCase(pdfType))
						intCols = 4;
					else if ("SalesSupp".equalsIgnoreCase(pdfType))
						intCols = 3;
					PdfPTable subsec = new PdfPTable(intCols);
					subsec.setWidthPercentage(90);
					if ("EngrSupp".equalsIgnoreCase(pdfType)){
						int[] width = { 14, 14, 50, 14 };
						subsec.setWidths(width);
					} else if ("SalesSupp".equalsIgnoreCase(pdfType)){
						int[] width = { 14, 14, 64 };
						subsec.setWidths(width);
					}						
					//Ends here for CR_106
					
					PdfPCell c = new PdfPCell(new Phrase("Description",
							strFontSizeBoldTen));
					c.setBackgroundColor(new Color(239, 239, 239));
					c.setVerticalAlignment(Element.ALIGN_TOP);
					c.setHorizontalAlignment(Element.ALIGN_CENTER);
					subsec.addCell(c);
					
					c = new PdfPCell(new Phrase("Specification Section",
							strFontSizeBoldTen));
					c.setBackgroundColor(new Color(239, 239, 239));
					c.setVerticalAlignment(Element.ALIGN_TOP);
					c.setHorizontalAlignment(Element.ALIGN_CENTER);
					subsec.addCell(c);
					
					c = new PdfPCell(new Phrase("Clause", strFontSizeBoldTen));
					c.setBackgroundColor(new Color(239, 239, 239));
					c.setVerticalAlignment(Element.ALIGN_TOP);
					c.setHorizontalAlignment(Element.ALIGN_CENTER);
					subsec.addCell(c);
					//Modified for CR_106 to bring Sales Supplement changes
					if ("EngrSupp".equalsIgnoreCase(pdfType)){
						c = new PdfPCell(new Phrase("Engineering Data",strFontSizeBoldTen));
						c.setBackgroundColor(new Color(239, 239, 239));
						c.setVerticalAlignment(Element.ALIGN_TOP);
						c.setHorizontalAlignment(Element.ALIGN_CENTER);
						subsec.addCell(c);
					}						
					//Ends here for CR_106
					c = new PdfPCell(new Phrase("Change From", strFontSizeBoldTen));
					c.setBackgroundColor(new Color(239, 239, 239));
					c.setVerticalAlignment(Element.ALIGN_TOP);
					c.setHorizontalAlignment(Element.ALIGN_CENTER);
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
							
							int intHeader = 0;
							Font fontHeader;
							if (strPastClauseDesc != null) {
								
								if (strPastClauseDesc.startsWith("<p>"))
									{						    
										Paragraph strPastHtmlClauseDesc = new Paragraph();
										strPastHtmlClauseDesc.setFont(customFont);//Added for CR-130
										ArrayList p = HTMLWorker.parseToList(new StringReader(strPastClauseDesc), null);//Edited for CR-130
									    
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
							
							if ("EngrSupp".equalsIgnoreCase(pdfType)){
								String strEngData = "";					
								strEngData = objFromClauseVO.getStrEngData();						
								
								if (!"Y".equals(strOrderDelFlag)
										&& !"D".equals(strModDelFlag)) {
									c = new PdfPCell(new Phrase(strEngData,
											strFointSizeNoBoldTen1));
									c.setVerticalAlignment(Element.ALIGN_TOP);
									subsec.addCell(c);
								} else {
									c = new PdfPCell(new Phrase("", strFointSizeNoBoldTen1));
									c.setVerticalAlignment(Element.ALIGN_TOP);
									subsec.addCell(c);
								}
							}
							
						}
						
						c = new PdfPCell(new Phrase("Change To", strFontSizeBoldTen));
						c.setBackgroundColor(new Color(239, 239, 239));
						c.setVerticalAlignment(Element.ALIGN_TOP);
						c.setHorizontalAlignment(Element.ALIGN_CENTER);
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
							
							int intHeader = 0;
							Font fontHeader;
							
							if (strPreClauseDesc != null) {
								if (strPreClauseDesc.startsWith("<p>"))
								{
									Paragraph strPreHtmlClauseDesc = new Paragraph();
									strPreHtmlClauseDesc.setFont(customFont);//Added for CR-130
									ArrayList p = HTMLWorker.parseToList(new StringReader(strPreClauseDesc), null);//Edited for CR-130
								    
								   /* ArrayList p = HTMLWorker.parseToList(new StringReader(strPastClauseDesc), null);//Edited for CR-130
									String strFileName = ApplicationUtil.strConvertToHTMLFormat(strPreClauseDesc);
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
									PdfPCell c1 = new PdfPCell(new Phrase(strPreClauseDesc,
											customFont));//Edited for CR-130
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
	
							if ("EngrSupp".equalsIgnoreCase(pdfType)){
								String strEngData = "";		
												
								strEngData = objToClauseVO.getStrEngData();
									
								if (!"Y".equals(strOrderDelFlag)
										&& !"D".equals(strModDelFlag)) {
									c = new PdfPCell(new Phrase(strEngData,
											strSSFointSizeNine));
									c.setVerticalAlignment(Element.ALIGN_TOP);
									subsec.addCell(c);
								} else {
									c = new PdfPCell(new Phrase("", strSSFointSizeNine));
									c.setVerticalAlignment(Element.ALIGN_TOP);
									subsec.addCell(c);
								}
							}
							
						}
						
						c = new PdfPCell(new Phrase("Reason", strFontSizeBoldTen));
						c.setBackgroundColor(new Color(239, 239, 239));
						c.setVerticalAlignment(Element.ALIGN_TOP);
						c.setHorizontalAlignment(Element.ALIGN_CENTER);
						subsec.addCell(c);
						
						c = new PdfPCell(new Phrase(strPreReason, strSSFointSizeNine));
						c.setVerticalAlignment(Element.ALIGN_TOP);
						c.setColspan(3);
						subsec.addCell(c);
		
						addNewLine(document);
						
						if (!writer.fitsPage(subsec)) {
							document.newPage(); 
							addNewLine(document);
						}
						
						document.add(subsec);
						Phrase phrase = new Phrase();
						phrase.add("\n");
						document.add(phrase);
						}
					}
				}
			}
		}catch (Exception objExp) {
			throw objExp;
		}
	}

	/***************************************************************************
	 * This method will get ResultSet as an input from the FetchSpecSupplementDetails
	 * and it will generate the Spec Supplement Main Features
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ArrayList arlSpecSupplementList,
				Document document, PdfWriter writer
	 * @author Sekar
	 **************************************************************************/
	
	private static void showSpecSupplementMainFeatures(ArrayList arlSpecSupplementList,
			Document document, PdfWriter writer) throws SQLException, Exception {
		
		try {
			
			ArrayList arlMainFeattList;
			
			if (arlSpecSupplementList != null && arlSpecSupplementList.size() > 0) {
				arlMainFeattList=new ArrayList();
				
				arlMainFeattList=(ArrayList)arlSpecSupplementList.get(0);
				
				if (arlMainFeattList != null && arlMainFeattList.size() > 0) {
				for (int i = 0; i < arlMainFeattList.size(); i++) {
					SpecSuppVO objjMainFeatureInfoVO = (SpecSuppVO) arlMainFeattList
							.get(i);
					
				String strFromMainInfo = "";
				String strToMainInfo = "";
				String strReason = "";
				
				strFromMainInfo = objjMainFeatureInfoVO.getFromMainInfo();
				strToMainInfo = objjMainFeatureInfoVO.getToMainInfo();
				strReason = objjMainFeatureInfoVO.getReason();
					
				PdfPTable subsec = new PdfPTable(2);
				subsec.setWidthPercentage(90);
				int[] width = { 11, 60 };
				subsec.setWidths(width);
				
				PdfPCell c = new PdfPCell(new Phrase("Description",
						strFontSizeBoldTen));
				c.setBackgroundColor(new Color(239, 239, 239));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase("Main Features Information",
						strFontSizeBoldTen));
				c.setBackgroundColor(new Color(239, 239, 239));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase("Change From", strFontSizeBoldTen));
				c.setBackgroundColor(new Color(239, 239, 239));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase(strFromMainInfo, strSSFointSizeNine));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase("Change To", strFontSizeBoldTen));
				c.setBackgroundColor(new Color(239, 239, 239));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase(strToMainInfo, strSSFointSizeNine));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase("Reason", strFontSizeBoldTen));
				c.setBackgroundColor(new Color(239, 239, 239));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
				subsec.addCell(c);
				
				c = new PdfPCell(new Phrase(strReason, strSSFointSizeNine));
				c.setVerticalAlignment(Element.ALIGN_TOP);
				subsec.addCell(c);
				
				addNewLine(document);
				
				if (!writer.fitsPage(subsec)) {
					document.newPage();
					addNewLine(document);
				}
				
				document.add(subsec);

					}
				}
			}
		}catch (Exception objExp) {
			LogUtil.logMessage("Catch block Exception :: showSpecSupplementMainFeatures");
			throw objExp;
			
		}
	}

	/***************************************************************************
	 * This method is for generating Marked Clauses PDF
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param UserID
	 * @param OrderKey
	 * @param PdfType
	 * @param HttpServletResponse
	 * @throws Exception
	 * @author RR68151
	 **************************************************************************/

	private static void GenerateMarkedClausesPDF(String strUserID, int intOrderKey,
			String strPdfType, HttpServletResponse objHttpServletResponse) throws Exception {

		LogUtil.logMessage("Inside GenerateProofReadingDraft.GenerateMarkedClausesPDF");
		
		String strSpecFileName = "";
		StringBuffer sbFile = new StringBuffer();
		try {

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Document document = new Document(PageSize.A4, 7, 0, 35, 100);//Edited for CR-130 PDF logo alignment issue
			PdfWriter writer = PdfWriter.getInstance(document, baos);

			FontFactory.registerDirectories();

			document.addAuthor("EMD-LSDB");
			document.addSubject("EMD-LSDB Marked Clauses PDF ");
			document.addCreationDate();
			document.open();
			
			String strWaterMarkFlag = ApplicationConstants.NO;
			PDFView objMyPage = new PDFView(strWaterMarkFlag,strHeaderFlag,strOrderNo,strSpecType);
			writer.setPageEvent(objMyPage);
			
			sbFile.append(strOrderNo);
			sbFile.append("_");
			sbFile.append(strSpecType.trim());
			sbFile.append("_Marked_List.pdf");
				
			strSpecFileName = sbFile.toString();
			
			document.newPage();
			
			Table header = new Table(10);
			setTableProperties(header);
			
			showHeaderInformationMarkedClauses(header, document,
					strPresentRevCode, strCustomerName, strCustomerCode,
					strOrderNo, strModelName, intQuantity);
			
			document.add(header);
			
			document.newPage();			
			
			int intSecSeqNO = 0;
			SectionVO objSectionVo = new SectionVO();
			SectionVO objSecVo = new SectionVO();
			ArrayList arlSectionList = new ArrayList();
			objSectionVo.setOrderKey(intOrderKey);
			objSectionVo.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSectionVo.setUserID(strUserID);
			OrderSectionBI objOrderSectionBo = ServiceFactory.getOrderSectionBO();
			arlSectionList = objOrderSectionBo.fetchOrdSections(objSectionVo);

			if (arlSectionList != null && arlSectionList.size() > 0) {

				for (int i = 0; i < arlSectionList.size(); i++) {

					objSecVo = (SectionVO) arlSectionList.get(i);

					intSecSeqNO = objSecVo.getSectionSeqNo();
			
					SectionVO objjSectionVO = new SectionVO();
					objjSectionVO.setOrderKey(intOrderKey);
					objjSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
					objjSectionVO.setSectionSeqNo(intSecSeqNO);
					objjSectionVO.setUserID(strUserID);
					objjSectionVO.setIntPdfType(1);
					objjSectionVO.setUserID(strUserID);
					
					objOrderSectionBo = ServiceFactory.getOrderSectionBO();
					SubSectionVO objSubSecVo = objOrderSectionBo.fetchClauseDetails(objjSectionVO);
					ArrayList arlClaList = new ArrayList();
					arlClaList = objSubSecVo.getClauseGroup();
					if (arlClaList.size() > 0) {
						
						for (int h = 0; h < arlClaList.size(); h++) {
							ArrayList arlTableData = new ArrayList();
							ArrayList arlTabData = new ArrayList();
							Integer intTabColCnt = new Integer(0);
							String strFileName = null;
							
							ClauseVO objClauseVO = (ClauseVO) arlClaList.get(h);

							if ("Y".equalsIgnoreCase(objClauseVO.getUserMarkFlag())){
							
							PdfPTable subsec = new PdfPTable(4);
							subsec.setWidthPercentage(90);
							int[] width = { 14, 14, 50, 14 };
							subsec.setWidths(width);
								
							PdfPCell c = new PdfPCell(new Phrase("Description",strFontSizeBoldTen));
							c.setBackgroundColor(new Color(239, 239, 239));
							c.setVerticalAlignment(Element.ALIGN_TOP);
							c.setHorizontalAlignment(Element.ALIGN_CENTER);
							subsec.addCell(c);
							
							c = new PdfPCell(new Phrase("Clause Number",strFontSizeBoldTen));
							c.setBackgroundColor(new Color(239, 239, 239));
							c.setVerticalAlignment(Element.ALIGN_TOP);
							c.setHorizontalAlignment(Element.ALIGN_CENTER);
							subsec.addCell(c);
							
							c = new PdfPCell(new Phrase("Clause Description", strFontSizeBoldTen));
							c.setBackgroundColor(new Color(239, 239, 239));
							c.setVerticalAlignment(Element.ALIGN_TOP);
							c.setHorizontalAlignment(Element.ALIGN_CENTER);
							subsec.addCell(c);
							
							c = new PdfPCell(new Phrase("Engineering Data",strFontSizeBoldTen));
							c.setBackgroundColor(new Color(239, 239, 239));
							c.setVerticalAlignment(Element.ALIGN_TOP);
							c.setHorizontalAlignment(Element.ALIGN_CENTER);
							subsec.addCell(c);
							
							c = new PdfPCell(new Phrase("MARKED", strFontSizeBoldTen));
							c.setBackgroundColor(new Color(239, 239, 239));
							c.setVerticalAlignment(Element.ALIGN_TOP);
							c.setHorizontalAlignment(Element.ALIGN_CENTER);
							subsec.addCell(c);
												
							String strClauseNum = objClauseVO.getClauseNum();
							String strClauseDesc = objClauseVO.getClauseDesc();
							String strModelClauseDelFlag = objClauseVO.getDeleteFlag();
							String strOrderClauseDelFlag = objClauseVO.getClauseDelFlag();
		
							arlTabData = getTableDataColumnsCount(objClauseVO.getTableArrayData1());
		
							if (arlTabData != null
									&& arlTabData.size() > 0) {
								intTabColCnt = (Integer) arlTabData
										.get(0);
							}
		
							arlTableData = objClauseVO.getTableArrayData1();
		
							c = new PdfPCell(new Phrase(strClauseNum,strFointSizeNoBoldTen1));
							c.setVerticalAlignment(Element.ALIGN_TOP);
							subsec.addCell(c);
							
							if (strOrderClauseDelFlag != null
									&& "Y".equalsIgnoreCase(strOrderClauseDelFlag)) {
		
								if (strModelClauseDelFlag != null
										&& !"D".equalsIgnoreCase(strModelClauseDelFlag)) {
		
									PdfPCell c1 = new PdfPCell(new Paragraph("RESERVED",strFontSizeNoBoldTen));
									subsec.addCell(c1);
								}
							}
		
							if (strOrderClauseDelFlag != null
									&& "Y".equalsIgnoreCase(strOrderClauseDelFlag)) {
		
								if (strModelClauseDelFlag != null
										&& "D".equalsIgnoreCase(strModelClauseDelFlag)) {
		
									PdfPCell c1 = new PdfPCell(new Paragraph("RESERVED",strFontSizeNoBoldTen));
									subsec.addCell(c1);
								}
							}
		
							if (strOrderClauseDelFlag != null
									&& !"Y".equalsIgnoreCase(strOrderClauseDelFlag)) {
								if ((strModelClauseDelFlag != null && !"D".equalsIgnoreCase(strModelClauseDelFlag))
										|| (strModelClauseDelFlag != null && "D".equalsIgnoreCase(strModelClauseDelFlag))) {
		
									int tabColCount = 0;
		
									if (intTabColCnt.intValue() == 0) {
										tabColCount = 1;
									} else {
										tabColCount = intTabColCnt.intValue();
									}
		
									PdfPTable TBData = new PdfPTable(tabColCount);
									TBData.setWidthPercentage(95);
		
									if (strClauseDesc != null
											&& !"".equals(strClauseDesc)) {
										
										if (strClauseDesc.startsWith("<p>"))
											{
																								
											Paragraph paraClauseDesc = new Paragraph();
											
											strFileName=ApplicationUtil.strConvertToHTMLFormat(strClauseDesc);
											StyleSheet styles = new StyleSheet();
										    styles.loadTagStyle("p","size","10px");
										    styles.loadTagStyle("p","face","Times");
											
										    ArrayList p = HTMLWorker.parseToList(new FileReader(strFileName), styles);
										    
										    for (int k1 = 0; k1 < p.size(); ++k1) {
										    	paraClauseDesc.add((Element) p.get(k1));
										     
										    }
										   
										    PdfPCell c1 = new PdfPCell(paraClauseDesc);
											c1.setVerticalAlignment(Element.ALIGN_TOP);
											c1.setColspan(tabColCount);
											TBData.addCell(c1);
											}
										else
											{
											
											PdfPCell c1 = new PdfPCell(new Phrase(String.valueOf(strClauseDesc),
													strFointSizeNoBoldTen1));
											c1.setVerticalAlignment(Element.ALIGN_TOP);
											c1.setColspan(tabColCount);
											TBData.addCell(c1);
											}
		
									} else {
		
										PdfPCell c1 = new PdfPCell(new Paragraph("",strFontSizeNoBoldTen));
										c1.setColspan(tabColCount);
										TBData.addCell(c1);
									}
		
									int intHeader = 0;
									Font fontHeader;
		
									if (arlTableData != null && arlTableData.size() > 0) {
		
										for (int tab = 0; tab < arlTableData.size(); tab++) {
		
											ArrayList arlTabColData = (ArrayList) arlTableData.get(tab);
		
											if (intHeader == 0)
												fontHeader = strFontSizeBoldTen;
											else
												fontHeader = strFontSizeNoBoldTen;
		
											if (tabColCount == 1
													|| tabColCount == 2
													|| tabColCount == 3
													|| tabColCount == 4
													|| tabColCount == 5) {
		
												PdfPCell c1 = new PdfPCell(new Paragraph((String) arlTabColData.get(0),
																fontHeader));
												//Modified for CR_111 to add borders for Table Data
												//c1.setBorder(PdfPCell.TOP);
												c1.setBorderColor(new Color(0,0,0));
												TBData.addCell(c1);
											}
		
											if (tabColCount == 2
													|| tabColCount == 3
													|| tabColCount == 4
													|| tabColCount == 5) {
		
												PdfPCell c1 = new PdfPCell(new Paragraph((String) arlTabColData.get(1),
																fontHeader));
												//Modified for CR_111 to add borders for Table Data
												//c1.setBorder(PdfPCell.TOP);
												c1.setBorderColor(new Color(0,0,0));
												TBData.addCell(c1);
											}
		
											if (tabColCount == 3
													|| tabColCount == 4
													|| tabColCount == 5) {
		
												PdfPCell c1 = new PdfPCell(new Paragraph((String) arlTabColData.get(2),
														fontHeader));
												//Modified for CR_111 to add borders for Table Data
												//c1.setBorder(PdfPCell.TOP);
												c1.setBorderColor(new Color(0,0,0));
												TBData.addCell(c1);
											}
		
											if (tabColCount == 4
													|| tabColCount == 5) {
		
												PdfPCell c1 = new PdfPCell(new Paragraph((String) arlTabColData.get(3),
														fontHeader));
												//Modified for CR_111 to add borders for Table Data
												//c1.setBorder(PdfPCell.TOP);
												c1.setBorderColor(new Color(0,0,0));
												TBData.addCell(c1);
											}
		
											if (tabColCount == 5) {
		
												PdfPCell c1 = new PdfPCell(new Paragraph((String) arlTabColData.get(4),
														fontHeader));
												//Modified for CR_111 to add borders for Table Data
												//c1.setBorder(PdfPCell.TOP);
												c1.setBorderColor(new Color(0,0,0));
												TBData.addCell(c1);
											}
											intHeader++;
										}
									}
		
									PdfPCell c1 = new PdfPCell(TBData);
									c1.setVerticalAlignment(Element.ALIGN_TOP);
									subsec.addCell(c1);
								}
							}
							
							String strCharstcGrpFlag = objClauseVO.getSelectCGCFlag();
		
							String strEngData = "";
							
							if ("Y".equals(strCharstcGrpFlag))	{
								
								String strCharEdlRefEdlNo = "";
														
								if (objClauseVO.getCharEdlNo() != null && !"".equals(objClauseVO.getCharEdlNo()))
									
									strCharEdlRefEdlNo += "EDL " + objClauseVO.getCharEdlNo()
																+ "\n";				
		
								if (objClauseVO.getCharRefEDLNo() != null && !"".equals(objClauseVO.getCharRefEDLNo()))
										strCharEdlRefEdlNo += "(ref EDL " + objClauseVO.getCharRefEDLNo()
																+ ")"+ "\n";	
								
								if (strCharEdlRefEdlNo != null
										&& !"".equals(strCharEdlRefEdlNo)) 
									strEngData += strCharEdlRefEdlNo;
								else
									strEngData += "EDL Undeveloped" + "\n";											
							
							}
							if ("C".equals(strCharstcGrpFlag))	{
								
								String strCharEdlRefEdlNo = "";		
								
								if (objClauseVO.getCharEdlNo() != null 
										&&	!"".equals(objClauseVO.getCharEdlNo()))
									
									strCharEdlRefEdlNo += "EDL " + objClauseVO.getCharEdlNo()
																+ "\n";	
								
								if (objClauseVO.getCharRefEDLNo() != null
										&&	!"".equals(objClauseVO.getCharRefEDLNo()))
										strCharEdlRefEdlNo += "(ref EDL " + objClauseVO.getCharRefEDLNo()
																+ ")"+ "\n";	
								
								if (strCharEdlRefEdlNo != null
										&& !"".equals(strCharEdlRefEdlNo)) 
									strEngData += strCharEdlRefEdlNo;
							}
		
							ArrayList arlEDLNO = objClauseVO.getEdlNO();
							
							if (arlEDLNO.size() > 0) {
								for (int n = 0; n < arlEDLNO.size(); n++) {
									strEngData += "EDL " + arlEDLNO.get(n).toString();
									strEngData += "\n";
								}
							}
								
							ArrayList arlRefEDLNO = objClauseVO.getRefEDLNO();
							
							if (arlRefEDLNO.size() > 0) {
								for (int n = 0; n < arlRefEDLNO.size(); n++) {
								strEngData += "(ref EDL " + arlRefEDLNO.get(n).toString();
								strEngData += ")"+"\n";
								}
							}
							
							ArrayList arlPartOf = objClauseVO.getPartOF();
							
							if (arlPartOf.size() > 0) {
								for (int n = 0; n < arlPartOf.size(); n++) {
									SubSectionVO objSubSectionVo = (SubSectionVO) arlPartOf.get(n);
									strEngData += "Part of " + objSubSectionVo.getSubSecCode();
									strEngData += "\n";
								}
							}
		
							if (objClauseVO.getDwONumber() != 0) {
								strEngData += "DWO " + objClauseVO.getDwONumber();
								strEngData += "\n";
							}
		
							if (objClauseVO.getPartNumber() != 0) {
								strEngData += "Part No " + objClauseVO.getPartNumber();
								strEngData += "\n";
							}
		
							if (objClauseVO.getStandardEquipmentDesc() != null) {
								strEngData += objClauseVO.getStandardEquipmentDesc();
								strEngData += "\n";
							}
		
							if (objClauseVO.getEngDataComment() != null) {
								strEngData += objClauseVO.getEngDataComment();
								strEngData += "\n";
							}
		
							if (strOrderClauseDelFlag != null
									&& !"Y".equalsIgnoreCase(strOrderClauseDelFlag)) {
								if ((strModelClauseDelFlag != null && !"D".equalsIgnoreCase(strModelClauseDelFlag))
										|| (strModelClauseDelFlag != null && "D".equalsIgnoreCase(strModelClauseDelFlag))) {
		
									if (strEngData != null && !"".equals(strEngData)) {
											c = new PdfPCell(new Phrase(strEngData,strFointSizeNoBoldTen1));
											c.setVerticalAlignment(Element.ALIGN_TOP);
											subsec.addCell(c);
										} else {
											c = new PdfPCell(new Phrase("", strSSFointSizeNine));
											c.setVerticalAlignment(Element.ALIGN_TOP);
											subsec.addCell(c);
										}
								}
							}
							else {
		
								c = new PdfPCell(new Phrase("", strSSFointSizeNine));
								c.setVerticalAlignment(Element.ALIGN_TOP);
								subsec.addCell(c);
							}
		
							c = new PdfPCell(new Phrase("Reason", strFontSizeBoldTen));
							c.setBackgroundColor(new Color(239, 239, 239));
							c.setVerticalAlignment(Element.ALIGN_TOP);
							c.setHorizontalAlignment(Element.ALIGN_CENTER);
							subsec.addCell(c);
							
							c = new PdfPCell(new Phrase(objClauseVO.getMarkClaReason(), strSSFointSizeNine));
							c.setVerticalAlignment(Element.ALIGN_TOP);
							c.setColspan(3);
							subsec.addCell(c);
			
							addNewLine(document);
							
							if (!writer.fitsPage(subsec)) {
								document.newPage(); 
								addNewLine(document);
							}
							
							document.add(subsec);
							Phrase phrase = new Phrase();
							phrase.add("\n");
							document.add(phrase);
							}
						}
					}
				}
			}
			document.close();

			objHttpServletResponse.setContentType("application/pdf");
			objHttpServletResponse.setHeader("Content-disposition",
					"attachment;filename=" + strSpecFileName);

			objHttpServletResponse.setContentLength(baos.size());

			ServletOutputStream out = objHttpServletResponse
					.getOutputStream();
			baos.writeTo(out);
			out.flush();

		} catch (Exception objExp) {
			throw objExp;
		}

	}

	/***************************************************************************
	 * This method is used to Show Header Information for Marked Clauses
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param Table header,
			Document document, String strRevCode, String strCustomerName,
			String strCustomerCode, String strOrderNo, String strModelName,
			int intQuantity
	 * @author RR68151
	 **************************************************************************/
	
	private static void showHeaderInformationMarkedClauses(Table header,
			Document document, String strRevCode, String strCustomerName,
			String strCustomerCode, String strOrderNo, String strModelName,
			int intQuantity) throws Exception {
		
		String strQuantity = "";
		
		try {

			DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
			String strDate = df.format(new Date());
			
			if (intQuantity != 0)
				strQuantity = "(" + intQuantity + ") ";
						
			String strHeadSpecType = "";
			
			if (intSpecTypeSeqNo == 2) {
				strHeadSpecType = ApplicationConstants.PM_I_MARKED_REPORT;
			} else if (intSpecTypeSeqNo == 3)	{
				strHeadSpecType = ApplicationConstants.LXO_MARKED_REPORT;
			} else {
				strHeadSpecType = ApplicationConstants.LOCOMOTIVE_MARKED_REPORT;
			}
						
			String strSpecDesc = "Please refer to the below clauses for the items that are 'marked'";
			
			Cell cel = new Cell(new Paragraph(strDate, strFointSizeNoBold));
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(10);
			header.addCell(cel);
			
			cel = new Cell(new Paragraph(strHeadSpecType + " – " + strSpecType,strFontNoUnderLine));
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(10);
			header.addCell(cel);
			
			cel = new Cell(new Paragraph("The marked clauses and comments from the following spec:",strFointSizeNoBold));
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(10);
			header.addCell(cel);
			
			cel = new Cell(new Paragraph(strCustomerName, strFointSizeNoBold));
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(10);
			header.addCell(cel);
			
			cel = new Cell(new Paragraph(strQtyInWords + " " + strQuantity
					+ " " + strModelName, strFointSizeNoBold));
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(10);
			header.addCell(cel);
			
			cel = new Cell(new Paragraph("EMD Customer Code No. "+ strCustomerCode, strFointSizeNoBold));
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(10);
			header.addCell(cel);
			
			
			
			cel = new Cell(new Paragraph("EMD Order No. " + strOrderNo,strFontUnderLine));
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(10);
			header.addCell(cel);
			
			cel = new Cell(new Paragraph(strSpecDesc, strFointSizeNoBold));
			cel.setHorizontalAlignment(Element.ALIGN_LEFT);
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(10);
			header.addCell(cel);
			
		} catch (Exception objExp) {
			throw objExp;
		}
	}
}