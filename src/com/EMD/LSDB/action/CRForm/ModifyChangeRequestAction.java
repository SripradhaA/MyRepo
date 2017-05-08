package com.EMD.LSDB.action.CRForm;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Calendar;

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
import com.EMD.LSDB.bo.serviceinterface.ChangeRequestBI;
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
import com.EMD.LSDB.form.CRForm.ModifyChangeRequestForm;
import com.EMD.LSDB.vo.admn.UserVO;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.RequestModelVO;
import com.EMD.LSDB.vo.common.RequestVO;
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
import com.lowagie.text.pdf.PdfDestination;
import com.lowagie.text.pdf.PdfOutline;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/*******************************************************************************************
 * @author  Satyam Computer Services Ltd  
 * @version 1.0  
 * This Action class has the business methods for Modify Spec
 ******************************************************************************************/
public class ModifyChangeRequestAction extends EMDAction {
	
	//Added for PDF by VV49326 31/10/08
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
	
	public static Font strFlagFont;
	
	public static Font strClaNoFlagFont;
	
	public static Font strFontSizeEight = new Font(Font.TIMES_ROMAN, 8, 0,
			Color.BLACK);
	
	/*************************************************************************************
	 * This Method is used to fetch the Status Details
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 * @author CM68219
	 *************************************************************************************/
	
	public ActionForward initLoad(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Entering ModifyChangeRequestAction.initLoad");
		String strForward = ApplicationConstants.SUCCESS;
		ArrayList arlSeqlist = new ArrayList();
		UserVO objUserVO = new UserVO();
		ArrayList arlUsers = new ArrayList();
		ModifyChangeRequestForm objModifyChangeRequestForm = (ModifyChangeRequestForm) objActionForm;
		LogUtil.logMessage("Form obj" + objModifyChangeRequestForm);
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage("Last Name" + objLoginVo.getLastName());
			RequestVO objRequestVO = new RequestVO();
			

			
			ChangeRequestBI objChangeRequestBI = ServiceFactory
			.getChangeRequestBO();
			objRequestVO.setUserID(objLoginVo.getUserID());
			
			arlSeqlist = objChangeRequestBI.fetchStatus(objRequestVO);
			
			if (arlSeqlist != null && arlSeqlist.size() > 0) {
				
				objModifyChangeRequestForm.setStatusList(arlSeqlist);
				
			}
			
			
//			CR_94 lines ae started here fetchStatus by orderby name
			 int orderbyFlag=1;
			 objUserVO.setOrderbyFlag(orderbyFlag);
			//CR_94 lines ends here
			UserMaintBI objUserMaintBO = ServiceFactory.getUserMaintBO();
			arlUsers = objUserMaintBO.fetchUsers(objUserVO);
			
			if (arlUsers != null && arlUsers.size() > 0) {
				objModifyChangeRequestForm.setLastList(arlUsers);
			}
			
			LogUtil.logMessage("user role:"+objLoginVo.getRoleID());
			if (ApplicationConstants.ADMN.equals(objLoginVo.getRoleID())
					|| ApplicationConstants.MSE.equals(objLoginVo.getRoleID()))
				objModifyChangeRequestForm
				.setRoleID(ApplicationConstants.ADMN);//This is for
			// approve,
			// Reject, onhold,
			// complete enable
			else{
				objModifyChangeRequestForm.setRoleID("OTHR");//ASM,SE
				if (objLoginVo.getLastName() != null) {
					objModifyChangeRequestForm.setLastName(objLoginVo.getLastName()
							.trim());
				}
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
	 * This Method is used to fetch the Request Details
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 * @author CM68219
	 *************************************************************************************/
	public ActionForward searchReqDetails(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil
		.logMessage("Entering ModifyChangeRequestAction.searchReqDetails");
		String strForward = ApplicationConstants.SUCCESS;
		ModifyChangeRequestForm objModifyChangeRequestForm = (ModifyChangeRequestForm) objActionForm;
		
		RequestVO objRequestVO = new RequestVO();
		
		ArrayList arlRequestList = new ArrayList();
		ArrayList arlSeqlist = new ArrayList();
		
		UserVO objUserVO = new UserVO();
		ArrayList arlUsers = null;
		String strLastName =null;
		
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			ChangeRequestBI objChangeRequestBI = ServiceFactory
			.getChangeRequestBO();
			UserMaintBI objUserMaintBO = ServiceFactory.getUserMaintBO();
			
			if (objModifyChangeRequestForm.getStatusSeqNo() == -1) {
				objRequestVO.setStatusTypeSeqNo(0);
			} else {
				objRequestVO.setStatusTypeSeqNo(objModifyChangeRequestForm
						.getStatusSeqNo());
			}
			
			if (objModifyChangeRequestForm.getFromDate() != null) {
				
				objRequestVO.setReqSubFromDate(objModifyChangeRequestForm
						.getFromDate());
			}
			//Added CR_94
			strLastName=objModifyChangeRequestForm.getLastName();
			LogUtil
			.logMessage("Entering ModifyChangeRequestAction.searchReqDetails++++strLastName"+strLastName);
			objRequestVO.setLastName(objModifyChangeRequestForm.getLastName());
			
			if (objModifyChangeRequestForm.getToDate() != null) {
				objRequestVO.setReqSubToDate(objModifyChangeRequestForm
						.getToDate());
			}
			
			if (objModifyChangeRequestForm.getRequestId() != null
					&& !"".equals(objModifyChangeRequestForm.getRequestId()
							.trim())) {
				objRequestVO.setRequestID(Integer.parseInt(ApplicationUtil
						.trim(objModifyChangeRequestForm.getRequestId())));
			} else {
				objRequestVO.setRequestID(0);
			}

			
			objRequestVO.setUserID(objLoginVo.getUserID());
			
			arlRequestList = objChangeRequestBI
			.fetchRequestDetails(objRequestVO);
			
			if (arlRequestList.size() != 0) {
				objModifyChangeRequestForm.setRequestList(arlRequestList);
				
			}
			if (arlRequestList.size() == 0) {
				objModifyChangeRequestForm.setMethod("REQUEST");
				
			}
			LogUtil.logMessage("ArrayList Size"
					+ objModifyChangeRequestForm.getStatusSeqNo());
//			CR_94 lines ae started here fetchStatus by orderby name
			 int orderbyFlag=1;
			 objUserVO.setOrderbyFlag(orderbyFlag);
			//CR_94 lines ends here
			arlUsers = objUserMaintBO.fetchUsers(objUserVO);
			if (arlUsers != null && arlUsers.size() > 0) {
				objModifyChangeRequestForm.setLastList(arlUsers);
			}
			
			arlSeqlist = objChangeRequestBI.fetchStatus(objRequestVO);
			if (arlSeqlist != null && arlSeqlist.size() > 0) {
				objModifyChangeRequestForm.setStatusList(arlSeqlist);
				objModifyChangeRequestForm
				.setHdStatus(objModifyChangeRequestForm.getHdStatus());
				objModifyChangeRequestForm
				.setHdnLastName(objModifyChangeRequestForm
						.getLastName());
				
				if (objModifyChangeRequestForm.getRequestId() != null
						&& !"".equals(objModifyChangeRequestForm.getRequestId()
								.trim())) {
					objModifyChangeRequestForm.setHdnRequestId(Integer
							.parseInt(ApplicationUtil
									.trim(objModifyChangeRequestForm
											.getRequestId())));
					
				}
				objModifyChangeRequestForm
				.setHdnFromDate(objModifyChangeRequestForm
						.getFromDate());
				objModifyChangeRequestForm
				.setHdnToDate(objModifyChangeRequestForm.getToDate());
			}
			
			objModifyChangeRequestForm
			.setStatusSeqNo(objModifyChangeRequestForm.getStatusSeqNo());
			objModifyChangeRequestForm.setLastName(objModifyChangeRequestForm
					.getLastName().trim());
			
			if (objModifyChangeRequestForm.getRequestId() != null
					&& !"".equals(objModifyChangeRequestForm.getRequestId()
							.trim())) {
				objModifyChangeRequestForm.setRequestId(ApplicationUtil
						.trim(objModifyChangeRequestForm.getRequestId()));
			} else {
				
				objModifyChangeRequestForm.setRequestId("");
			}
			
			objModifyChangeRequestForm.setFromDate(objModifyChangeRequestForm
					.getFromDate());
			objModifyChangeRequestForm.setToDate(objModifyChangeRequestForm
					.getToDate());
			//Added for CR_94
			objModifyChangeRequestForm.setLastName(strLastName);
			//Added For CR_80 by RR68151 on 1-Dec-09
			objModifyChangeRequestForm.setAssigneeId("");
			//Added For CR_80 by RR68151 on 1-Dec-09 - Ends here
			
			
			
		} catch (Exception objExp) {
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			objExp.printStackTrace();
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
		
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
		LogUtil.logMessage("Inside the generatePDFCRFormAction:fetchPDF");
		ModifyChangeRequestForm objModifyChangeRequestForm = (ModifyChangeRequestForm) objActionForm;
		String strForwardKey = ApplicationConstants.SUCCESS;
		int i = 0;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		String strUserID = null;
		String[] req = null;
		try {
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			//Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			//To get Request ID Values from Servlet Request 
			
			req = objHttpServletRequest.getParameterValues("reqCnt");
			
			if (req.length > 0) {
				LogUtil.logMessage("Request ID Values Array length"
						+ req.length);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				Document document = new Document(PageSize.A4, 7, 0, 40, 100);//Edited for CR-130
				PdfWriter writer = PdfWriter.getInstance(document, baos);
				
                                //Added for moving PDFView file to common
				String strWaterMarkFlag="N";
				//Added For CR_79 Header Image to PDFs on 3-Nov-09 by RR68151
				String strPDFHeaderFlag=ApplicationConstants.YES; //Edited for CR-130
				//Modified for CR_100 to add Order Number to PDF Footer for Proof Reading
				//Modified for CR_101 to add Spec Status to the Footer for Proof Reading
				PDFView objMyPage = new PDFView(strWaterMarkFlag,strPDFHeaderFlag,"","");
				//Added For CR_79 Header Image to PDFs on 3-Nov-09 by RR68151 - Ends here
				writer.setPageEvent(objMyPage);
				
				document.addAuthor("EMD-LSDB");
				document.addSubject("EMD-LSDB CR Form PDF ");
				document.addCreationDate();
				document.open();
				document.newPage();
				
				//Commented as handled in OnEndPage Event
				//Show Header and Footer
				//showHeaderFooter(document);
				
				//Show PDF document			
				for (i = 0; i < req.length; i++) {
					
					LogUtil.logMessage("Request ID" + req[i]);
					
					if (req[i] != null && (!"".equals(req[i]))) {
						
						PdfDestination d1 = new PdfDestination(
								PdfDestination.FIT);
						PdfOutline root = writer.getRootOutline();
						LogUtil.logMessage("Request ID from Form" + req[i]);
						
						//Generate PDF main document
						showChangeControl(document, writer, d1, root, req[i],
								strUserID, i);
						
					}
				}
				
				document.close();
				
				// setting the content type
				objHttpServletResponse.setContentType("application/pdf");
				objHttpServletResponse.setHeader("Content-disposition",
				"attachment;filename=CRFormReport.pdf");
				objHttpServletResponse.setContentLength(baos.size());
				
				// write ByteArrayOutputStream to the ServletOutputStream
				ServletOutputStream out = objHttpServletResponse
				.getOutputStream();
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
			PdfDestination d1, PdfOutline root, String reqId, String strUserID,
			int i) throws Exception {
		
		LogUtil
		.logMessage("Inside the generatePDFCRFormAction:showChangeControl");
		RequestVO objRequestVO = new RequestVO();
		RequestModelVO objRequestModelVO = new RequestModelVO();
		RequestVO objRequestVo = null;
		ArrayList arlReqDetails = new ArrayList();
		ArrayList arlFromTableData = new ArrayList();
		ArrayList arlFromData = new ArrayList();
		Integer intFromColCnt = new Integer(0);
		Integer intToColCnt = new Integer(0);
		ArrayList arlToTableData = new ArrayList();
		ArrayList arlToData = new ArrayList();
		int cnt = 0;
		int intModelSeqNo = 0;
		int intSubSecSeqNo = 0;
		int intToHeader = 0;
		int toTabColCnt = 0;
		int intHeader = 0;
		Font fontHeader;
		Font fontToHeader;
		String strModel = "";
		String strSection = "";
		String strSubSection = "";
		
		String strSubmitBy = "";
		String strSubmitDate = "";
		String strApprovedBy = "";
		String strApprovedDate = "";
		
		String strCompGrpChngeType = "";
		String strOldCompGrpName = "";
		String strOldCompChac = "";
		String strOldCOmpVal = "";
		
		String strNewCompGrpName = "";
		String strNewCompGrpChac = "";
		String strNewCompGrpVal = "";
		String strNameColor = "";
		String strChacColor = "";
		String strReqColor = "";
		
		String strCompChngeType = "";
		String strOldCompName = "";
		String strOldCompDef = "";
		String strNewCompName = "";
		String strNewCompDef = "";
		
		String strCompNameFlag = "";
		String strCompDefFlag = "";
		
		String strFromClauseDesc = "";
		String strFromClauseNo = "";
		
		String strFromComp = "";
		String strEdl = "";
		String strRefEdl = "";
		String strPartOf = "";
		String strDWO = "";
		String strPartNo = "";
		String strPriceBookNo = "";
		String strStdEqp = "";
		String strComments = "";
		
		String strToParCla = "";
		String strToParClaDesc = "";
		String strToClaVerDesc = "";
		String strToNewClaNo = "";
		String strNewClaDesc = "";
		String strNewClaComp = "";
		/* Removed for CR_80 CRForm Enhancements III on 20-Nov-09 by RR68151 
		String strDefaultCla = "";

		String strToEdl = "";
		String strToRefEdl = "";
		String strToPartOf = "";
		String strToDWO = "";
		String strToPartNo = "";
		String strToPriceBookNo = "";
		String strToStdEqp = "";
		String strToComments = "";*/
		
		String strReason = "";
		
		//Added for CR-59
		String strAdmnCmnts = "";
		int intStatusTypeSeqNo = 0;
		boolean blnApplyModel = false;
		
		//Flags for Change To
		String strClaNoFlag = "";
		String strClaVerFlag = "";
		String strClaDescFlag = "";
		
		//Added For CR_80 Effected Clauses
		String strEffClaDesc = "";
		String strEffClaComp = "";
		String strEffClaCompDefault = "";
		
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
		
		//Table for Comp Grp Details
		PdfPTable reqFromCompGrp = new PdfPTable(2);
		reqFromCompGrp.setWidthPercentage(80);
		
		PdfPTable reqToCompGrp = new PdfPTable(2);
		reqToCompGrp.setWidthPercentage(80);
		
		//Table for Request CompGrp Change Type
		
		PdfPTable reqCompChngeType = new PdfPTable(2);
		reqCompChngeType.setWidthPercentage(80);
		reqCompChngeType.setWidths(compGrpChange);
		
		//Table for Comp  Details
		PdfPTable reqComp = new PdfPTable(2);
		reqComp.setWidthPercentage(80);
		
		//Table for To Comp  Details
		PdfPTable reqToComp = new PdfPTable(2);
		reqToComp.setWidthPercentage(80);
		
		//Table for Clause Details Heading
		PdfPTable reqClaChngeType = new PdfPTable(2);
		reqClaChngeType.setWidthPercentage(80);
		reqClaChngeType.setWidths(compGrpChange);
		
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
		
		try {
			
			//Added for CR-130
			FontFactory.register("/images/times.ttf",BaseFont.HELVETICA);
			FontFactory.register("/images/timesbd.ttf",BaseFont.HELVETICA_BOLD);
			FontFactory.register("/images/timesi.ttf",BaseFont.HELVETICA_OBLIQUE);
			FontFactory.register("/images/timesbi.ttf",BaseFont.HELVETICA_BOLDOBLIQUE);
			
			BaseFont customBaseFont = BaseFont.createFont("/images/times.ttf",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
			Font customFont = new Font(customBaseFont,12);
			//Added for CR-130 ends
			
			objRequestVO.setRequestID(Integer.parseInt(reqId.trim()));
			LogUtil.logMessage("Request ID" + objRequestVO.getRequestID());
			
			objRequestVO.setUserID(strUserID);
			
			//To fetch Request Details
			ChangeRequestBI objChangeRequestBI = ServiceFactory
			.getChangeRequestBO();
			arlReqDetails = objChangeRequestBI
			.fetchRequestDetails(objRequestVO);
			ClauseVO objClauseVO = new ClauseVO();
			
			LogUtil.logMessage("Request Size" + arlReqDetails.size());
			
			if (arlReqDetails != null & arlReqDetails.size() > 0) {

				for (cnt = 0; cnt < arlReqDetails.size(); cnt++) {

					objRequestVo = (RequestVO) arlReqDetails.get(cnt);
					
					LogUtil.logMessage(objRequestVo.getReqID());
					LogUtil.logMessage(objRequestVo.getStatusTypeDesc());
					
					strSubmitDate = objRequestVo.getReqUserDate();
					strApprovedDate = objRequestVo.getMasterSpecChangedDate();
					strReason = objRequestVo.getReason();
					
					if (objRequestVo != null
							&& objRequestVo.getAdminComments() != null) {
						
						//				Added for CR-59
						strAdmnCmnts = objRequestVo.getAdminComments();
						intStatusTypeSeqNo = objRequestVo.getStatusTypeSeqNo();

					}
					
					/* To generate Header in first Page - Starts Here*/
					if (i == 0) {
						
						document.newPage();
						PdfPCell cel = new PdfPCell(new Paragraph(
								"Change Control Form Report",
								strFontSizeBoldtwelve));
						cel.setHorizontalAlignment(Element.ALIGN_CENTER);
						cel.setBorderColor(new Color(255, 255, 255));
						header.addCell(cel);
						document.add(header);
						document.add(new Paragraph("\n"));
					}
					/* To generate Header in first Page - Starts Here*/
					
					/*For creating Request ID No and Status - Starts Here*/
					
					/*For creating bookmark for every Request ID - Starts Here*/
					if (i > 0) {
						document.newPage();
					}
					d1 = new PdfDestination(PdfDestination.FIT);
					root = writer.getRootOutline();
					PdfOutline oline1 = new PdfOutline(root, d1, objRequestVo
							.getReqID());
					oline1.setOpen(false);
					
					PdfPCell reqID = new PdfPCell(new Phrase(
							"Change Control ID :" + objRequestVo.getReqID(),
							strFontSizeBoldEleven));
					reqID.setBorderColor(new Color(255, 255, 255));
					reqID.setBackgroundColor(new Color(239, 239, 239));
					reqID.setVerticalAlignment(Element.ALIGN_LEFT);
					reqID.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqID.setColspan(2);
					pdfRequestHeading.addCell(reqID);
					
					PdfPCell statusDesc = new PdfPCell(new Phrase("Status : "
							+ objRequestVo.getStatusTypeDesc(),
							strFontSizeBoldEleven));
					statusDesc.setBorderColor(new Color(255, 255, 255));
					statusDesc.setBackgroundColor(new Color(239, 239, 239));
					statusDesc.setVerticalAlignment(Element.ALIGN_LEFT);
					statusDesc.setHorizontalAlignment(Element.ALIGN_LEFT);
					statusDesc.setColspan(2);
					pdfRequestHeading.addCell(statusDesc);
					
					document.add(pdfRequestHeading);
					/*For creating Request ID No and Status - Starts Here*/
					
					document.add(new Paragraph("\n"));
					
					/*To get Request Model Details - Starts Here*/
					
					LogUtil.logMessage("Request ID"
							+ objRequestVO.getRequestID());
					objChangeRequestBI = ServiceFactory.getChangeRequestBO();
					objRequestModelVO = objChangeRequestBI
					.fetchReqModelDetails(objRequestVO);

					PdfPCell model = new PdfPCell(new Phrase("Model",
							strFontSizeBoldEleven));
					model.setBorderColor(new Color(255, 255, 255));
					model.setVerticalAlignment(Element.ALIGN_LEFT);
					model.setHorizontalAlignment(Element.ALIGN_LEFT);
					pdfReqMdl.addCell(model);
					
					model = new PdfPCell(
							new Phrase(": ", strFontSizeBoldEleven));
					model.setBorderColor(new Color(255, 255, 255));
					model.setVerticalAlignment(Element.ALIGN_LEFT);
					model.setHorizontalAlignment(Element.ALIGN_LEFT);
					pdfReqMdl.addCell(model);
					
					if (objRequestModelVO.getModelName() != null) {

						strModel = objRequestModelVO.getModelName();
						strSection = objRequestModelVO.getSecCode() + "."
						+ objRequestModelVO.getSecName();
						strSubSection = objRequestModelVO.getSecCode() + "."
						+ objRequestModelVO.getSubSecCode() + " "
						+ objRequestModelVO.getSubSecName();
						intModelSeqNo = objRequestModelVO.getModelSeqNo();
						intSubSecSeqNo = objRequestModelVO.getSubSectionSeqNo();
						
						blnApplyModel = objRequestModelVO.isApplyModelFlag();
						LogUtil.logMessage("Flag"
								+ objRequestModelVO.isApplyModelFlag());
						
					}
					
					model = new PdfPCell(new Phrase(strModel, strFointSizeTen));
					model.setBorderColor(new Color(255, 255, 255));
					model.setVerticalAlignment(Element.ALIGN_LEFT);
					model.setHorizontalAlignment(Element.ALIGN_LEFT);
					pdfReqMdl.addCell(model);
					
					PdfPCell section = new PdfPCell(new Phrase("Section",
							strFontSizeBoldEleven));
					section.setBorderColor(new Color(255, 255, 255));
					section.setVerticalAlignment(Element.ALIGN_LEFT);
					section.setHorizontalAlignment(Element.ALIGN_LEFT);
					pdfReqMdl.addCell(section);
					
					section = new PdfPCell(new Phrase(": ",
							strFontSizeBoldEleven));
					section.setBorderColor(new Color(255, 255, 255));
					section.setVerticalAlignment(Element.ALIGN_LEFT);
					section.setHorizontalAlignment(Element.ALIGN_LEFT);
					pdfReqMdl.addCell(section);
					
					section = new PdfPCell(new Phrase(strSection,
							strFointSizeTen));
					section.setBorderColor(new Color(255, 255, 255));
					section.setVerticalAlignment(Element.ALIGN_LEFT);
					section.setHorizontalAlignment(Element.ALIGN_LEFT);
					pdfReqMdl.addCell(section);
					
					PdfPCell subSection = new PdfPCell(new Phrase(
							"Sub Section", strFontSizeBoldEleven));
					subSection.setBorderColor(new Color(255, 255, 255));
					subSection.setVerticalAlignment(Element.ALIGN_LEFT);
					subSection.setHorizontalAlignment(Element.ALIGN_LEFT);
					pdfReqMdl.addCell(subSection);
					
					subSection = new PdfPCell(new Phrase(": ",
							strFontSizeBoldEleven));
					subSection.setBorderColor(new Color(255, 255, 255));
					subSection.setVerticalAlignment(Element.ALIGN_LEFT);
					subSection.setHorizontalAlignment(Element.ALIGN_LEFT);
					pdfReqMdl.addCell(subSection);
					
					subSection = new PdfPCell(new Phrase(strSubSection,
							strFointSizeTen));
					subSection.setBorderColor(new Color(255, 255, 255));
					subSection.setVerticalAlignment(Element.ALIGN_LEFT);
					subSection.setHorizontalAlignment(Element.ALIGN_LEFT);
					pdfReqMdl.addCell(subSection);
					
					document.add(pdfReqMdl);
					
					/*To get Request Model Details - Ends Here*/
					
					/*For creating Request Desc - Starts Here*/
					
					PdfPCell Desc = new PdfPCell(new Phrase(
							"Short Description ", strFontSizeBoldEleven));
					Desc.setBorderColor(new Color(255, 255, 255));
					Desc.setVerticalAlignment(Element.ALIGN_LEFT);
					Desc.setHorizontalAlignment(Element.ALIGN_LEFT);
					pdfReqDesc.addCell(Desc);
					
					Desc = new PdfPCell(new Phrase(": ", strFontSizeBoldEleven));
					Desc.setBorderColor(new Color(255, 255, 255));
					Desc.setVerticalAlignment(Element.ALIGN_LEFT);
					Desc.setHorizontalAlignment(Element.ALIGN_LEFT);
					pdfReqDesc.addCell(Desc);
					
					Desc = new PdfPCell(new Phrase(objRequestVo
							.getRequestDesc(), strFointSizeTen));
					Desc.setBorderColor(new Color(255, 255, 255));
					Desc.setVerticalAlignment(Element.ALIGN_LEFT);
					Desc.setHorizontalAlignment(Element.ALIGN_LEFT);
					pdfReqDesc.addCell(Desc);
					
					document.add(pdfReqDesc);
					
					PdfPCell applyToModel = new PdfPCell(new Phrase(
							"This change effects both Ace/M-2 Models",
							strFontSizeBoldEleven));
					applyToModel.setBorderColor(new Color(255, 255, 255));
					applyToModel.setVerticalAlignment(Element.ALIGN_LEFT);
					applyToModel.setHorizontalAlignment(Element.ALIGN_LEFT);
					pdfApplyModel.addCell(applyToModel);
					
					applyToModel = new PdfPCell(new Phrase(": ",
							strFontSizeBoldEleven));
					applyToModel.setBorderColor(new Color(255, 255, 255));
					applyToModel.setVerticalAlignment(Element.ALIGN_LEFT);
					applyToModel.setHorizontalAlignment(Element.ALIGN_LEFT);
					pdfApplyModel.addCell(applyToModel);
					
					if (blnApplyModel) {
						applyToModel = new PdfPCell(new Phrase("Yes",
								strFointSizeTen));
						applyToModel.setBorderColor(new Color(255, 255, 255));
						applyToModel.setVerticalAlignment(Element.ALIGN_LEFT);
						applyToModel.setHorizontalAlignment(Element.ALIGN_LEFT);
						pdfApplyModel.addCell(applyToModel);
					} else {
						applyToModel = new PdfPCell(new Phrase("No",
								strFointSizeTen));
						applyToModel.setBorderColor(new Color(255, 255, 255));
						applyToModel.setVerticalAlignment(Element.ALIGN_LEFT);
						applyToModel.setHorizontalAlignment(Element.ALIGN_LEFT);
						pdfApplyModel.addCell(applyToModel);
					}
					
					document.add(pdfApplyModel);
					/*For creating Request Desc - Ends Here*/
					
					document.add(new Paragraph("\n"));
					
					/*For displaying Component Group Request Details - Starts Here*/
					objRequestVO.setRequestID(Integer.parseInt(reqId.trim()));
					
					LogUtil.logMessage("Request ID"
							+ objRequestVO.getRequestID());
					
					objRequestVO.setUserID(strUserID);
					
					objChangeRequestBI = ServiceFactory.getChangeRequestBO();
					RequestModelVO objjRequestModelVO = objChangeRequestBI
					.fetchReqCompGrpDetails(objRequestVO);
					
					LogUtil.logMessage("ArrayList Size" + objjRequestModelVO);
					
					PdfPCell compGrpChngeType = new PdfPCell(new Phrase(
							"Not Required/New/Existing/Revise/Delete Component Group :",
							strFontSizeBoldEleven));
					compGrpChngeType.setBorderColor(new Color(255, 255, 255));
					compGrpChngeType.setVerticalAlignment(Element.ALIGN_LEFT);
					compGrpChngeType.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqCompGrpChngeType.addCell(compGrpChngeType);
					
					LogUtil.logMessage("ArrayList Size" + objjRequestModelVO);
					if (objjRequestModelVO != null
							&& objjRequestModelVO.getRequestCompGrpVO() != null) {

						if (objjRequestModelVO.getRequestCompGrpVO()
								.getChangeTypeDesc() != null) {
							strCompGrpChngeType = objjRequestModelVO
							.getRequestCompGrpVO().getChangeTypeDesc();
						}
						if (objjRequestModelVO.getRequestCompGrpVO()
								.getOldCompGrpName() != null) {
							strOldCompGrpName = objjRequestModelVO
							.getRequestCompGrpVO().getOldCompGrpName();
						}
						
						if (objjRequestModelVO.getRequestCompGrpVO()
								.getOldCompGrpChacFlag() != null) {
							if (objjRequestModelVO.getRequestCompGrpVO()
									.getOldCompGrpChacFlag().equals("Y")) {
								strOldCompChac = "Yes";
							} else if (objjRequestModelVO.getRequestCompGrpVO()
									.getOldCompGrpChacFlag().equals("N")) {
								strOldCompChac = "No";
							}
						}
						
						if (objjRequestModelVO.getRequestCompGrpVO()
								.getOldCompGrpValidFlag() != null) {
							if (objjRequestModelVO.getRequestCompGrpVO()
									.getOldCompGrpValidFlag().equals("Y")) {
								strOldCOmpVal = "Yes";
							} else if (objjRequestModelVO.getRequestCompGrpVO()
									.getOldCompGrpValidFlag().equals("N")) {
								strOldCOmpVal = "No";
							}
							
						}
						
						if (objjRequestModelVO.getRequestCompGrpVO()
								.getNewCompGrpName() != null) {
							strNewCompGrpName = objjRequestModelVO
							.getRequestCompGrpVO().getNewCompGrpName();
						}
						
						if (objjRequestModelVO.getRequestCompGrpVO()
								.getNewCompGrpChacFlag() != null) {
							if (objjRequestModelVO.getRequestCompGrpVO()
									.getNewCompGrpChacFlag().equals("Y")) {
								strNewCompGrpChac = "Yes";
							} else if (objjRequestModelVO.getRequestCompGrpVO()
									.getNewCompGrpChacFlag().equals("N")) {
								strNewCompGrpChac = "No";
							}
						}
						
						if (objjRequestModelVO.getRequestCompGrpVO()
								.getNewCompGrpValidFlag() != null) {
							if (objjRequestModelVO.getRequestCompGrpVO()
									.getNewCompGrpValidFlag().equals("Y")) {
								strNewCompGrpVal = "Yes";
							} else if (objjRequestModelVO.getRequestCompGrpVO()
									.getNewCompGrpValidFlag().equals("N")) {
								strNewCompGrpVal = "No";
							}
						}
						
						if (objjRequestModelVO.getRequestCompGrpVO()
								.getCompGrpNameColorFlag() != null) {
							strNameColor = objjRequestModelVO
							.getRequestCompGrpVO()
							.getCompGrpNameColorFlag();
						}
						if (objjRequestModelVO.getRequestCompGrpVO()
								.getCompGrpCharColorFlag() != null) {
							strChacColor = objjRequestModelVO
							.getRequestCompGrpVO()
							.getCompGrpCharColorFlag();
						}
						
						if (objjRequestModelVO.getRequestCompGrpVO()
								.getCompGrpValdColorFlag() != null) {
							strReqColor = objjRequestModelVO
							.getRequestCompGrpVO()
							.getCompGrpValdColorFlag();
						}
						
					}
					
					LogUtil.logMessage("ArrayList Size3434"
							+ objjRequestModelVO);
					
					compGrpChngeType = new PdfPCell(new Phrase(
							strCompGrpChngeType, strFointSizeTen));
					compGrpChngeType.setBorderColor(new Color(255, 255, 255));
					compGrpChngeType.setVerticalAlignment(Element.ALIGN_BOTTOM);
					compGrpChngeType.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqCompGrpChngeType.addCell(compGrpChngeType);
					
					document.add(reqCompGrpChngeType);
					
					PdfPCell oldCompGrp = new PdfPCell(new Phrase(
							"Change From", strFontSizeBoldEleven));
					oldCompGrp.setBackgroundColor(new Color(223, 223, 223));
					oldCompGrp.setVerticalAlignment(Element.ALIGN_LEFT);
					oldCompGrp.setHorizontalAlignment(Element.ALIGN_LEFT);
					oldCompGrp.setColspan(2);
					reqFromCompGrp.addCell(oldCompGrp);
					
					oldCompGrp = new PdfPCell(new Phrase("Component Group",
							strFontSizeBoldEleven));
					oldCompGrp.setBackgroundColor(new Color(255, 255, 255));
					oldCompGrp.setVerticalAlignment(Element.ALIGN_LEFT);
					oldCompGrp.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqFromCompGrp.addCell(oldCompGrp);
					
					oldCompGrp = new PdfPCell(new Phrase(strOldCompGrpName,
							strFointSizeTen));
					oldCompGrp.setBackgroundColor(new Color(255, 255, 255));
					oldCompGrp.setVerticalAlignment(Element.ALIGN_LEFT);
					oldCompGrp.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqFromCompGrp.addCell(oldCompGrp);
					
					oldCompGrp = new PdfPCell(new Phrase("Characterization",
							strFontSizeBoldEleven));
					oldCompGrp.setBackgroundColor(new Color(255, 255, 255));
					oldCompGrp.setVerticalAlignment(Element.ALIGN_LEFT);
					oldCompGrp.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqFromCompGrp.addCell(oldCompGrp);
					
					oldCompGrp = new PdfPCell(new Phrase(strOldCompChac,
							strFointSizeTen));
					oldCompGrp.setBackgroundColor(new Color(255, 255, 255));
					oldCompGrp.setVerticalAlignment(Element.ALIGN_LEFT);
					oldCompGrp.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqFromCompGrp.addCell(oldCompGrp);
					
					oldCompGrp = new PdfPCell(new Phrase(
							"Required Component Group", strFontSizeBoldEleven));
					oldCompGrp.setBackgroundColor(new Color(255, 255, 255));
					oldCompGrp.setVerticalAlignment(Element.ALIGN_LEFT);
					oldCompGrp.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqFromCompGrp.addCell(oldCompGrp);
					
					oldCompGrp = new PdfPCell(new Phrase(strOldCOmpVal,
							strFointSizeTen));
					oldCompGrp.setBackgroundColor(new Color(255, 255, 255));
					oldCompGrp.setVerticalAlignment(Element.ALIGN_LEFT);
					oldCompGrp.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqFromCompGrp.addCell(oldCompGrp);
					
					document.add(reqFromCompGrp);
					
					document.add(new Paragraph("\n"));
					
					PdfPCell newCompGrp = new PdfPCell(new Phrase("Change To",
							strFontSizeBoldEleven));
					newCompGrp.setBackgroundColor(new Color(223, 223, 223));
					newCompGrp.setVerticalAlignment(Element.ALIGN_LEFT);
					newCompGrp.setHorizontalAlignment(Element.ALIGN_LEFT);
					newCompGrp.setColspan(2);
					reqToCompGrp.addCell(newCompGrp);
					
					newCompGrp = new PdfPCell(new Phrase("Component Group",
							strFontSizeBoldEleven));
					newCompGrp.setBackgroundColor(new Color(255, 255, 255));
					newCompGrp.setVerticalAlignment(Element.ALIGN_LEFT);
					newCompGrp.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqToCompGrp.addCell(newCompGrp);
					
					/*To give red colour from color flag - Starts Here*/
					
					if (strNameColor.equals("Y")) {
						
						newCompGrp = new PdfPCell(new Phrase(strNewCompGrpName,
								strFontRedSizeTen));
						newCompGrp.setBackgroundColor(new Color(255, 255, 255));
						newCompGrp.setVerticalAlignment(Element.ALIGN_LEFT);
						newCompGrp.setHorizontalAlignment(Element.ALIGN_LEFT);
						reqToCompGrp.addCell(newCompGrp);
					} else {
						
						newCompGrp = new PdfPCell(new Phrase(strNewCompGrpName,
								strFointSizeTen));
						newCompGrp.setBackgroundColor(new Color(255, 255, 255));
						newCompGrp.setVerticalAlignment(Element.ALIGN_LEFT);
						newCompGrp.setHorizontalAlignment(Element.ALIGN_LEFT);
						reqToCompGrp.addCell(newCompGrp);
					}
					
					/*To give red colour from color flag - Ends Here*/
					
					newCompGrp = new PdfPCell(new Phrase("Characterization",
							strFontSizeBoldEleven));
					newCompGrp.setBackgroundColor(new Color(255, 255, 255));
					newCompGrp.setVerticalAlignment(Element.ALIGN_LEFT);
					newCompGrp.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqToCompGrp.addCell(newCompGrp);
					
					/*To give red colour from color flag - Starts Here*/
					
					if (strChacColor.equals("Y")) {
						
						newCompGrp = new PdfPCell(new Phrase(strNewCompGrpChac,
								strFontRedSizeTen));
						newCompGrp.setBackgroundColor(new Color(255, 255, 255));
						newCompGrp.setVerticalAlignment(Element.ALIGN_LEFT);
						newCompGrp.setHorizontalAlignment(Element.ALIGN_LEFT);
						reqToCompGrp.addCell(newCompGrp);
					} else {
						
						newCompGrp = new PdfPCell(new Phrase(strNewCompGrpChac,
								strFointSizeTen));
						newCompGrp.setBackgroundColor(new Color(255, 255, 255));
						newCompGrp.setVerticalAlignment(Element.ALIGN_LEFT);
						newCompGrp.setHorizontalAlignment(Element.ALIGN_LEFT);
						reqToCompGrp.addCell(newCompGrp);
					}
					
					/*To give red colour from color flag - Starts Here*/
					
					newCompGrp = new PdfPCell(new Phrase(
							"Required Component Group", strFontSizeBoldEleven));
					newCompGrp.setBackgroundColor(new Color(255, 255, 255));
					newCompGrp.setVerticalAlignment(Element.ALIGN_LEFT);
					newCompGrp.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqToCompGrp.addCell(newCompGrp);
					
					/*To give red colour from color flag - Starts Here*/
					
					if (strReqColor.equals("Y")) {
						
						newCompGrp = new PdfPCell(new Phrase(strNewCompGrpVal,
								strFontRedSizeTen));
						newCompGrp.setBackgroundColor(new Color(255, 255, 255));
						newCompGrp.setVerticalAlignment(Element.ALIGN_LEFT);
						newCompGrp.setHorizontalAlignment(Element.ALIGN_LEFT);
						reqToCompGrp.addCell(newCompGrp);
					} else {
						
						newCompGrp = new PdfPCell(new Phrase(strNewCompGrpVal,
								strFointSizeTen));
						newCompGrp.setBackgroundColor(new Color(255, 255, 255));
						newCompGrp.setVerticalAlignment(Element.ALIGN_LEFT);
						newCompGrp.setHorizontalAlignment(Element.ALIGN_LEFT);
						reqToCompGrp.addCell(newCompGrp);
					}
					
					/*To give red colour from color flag - Starts Here*/
					
					document.add(reqToCompGrp);
					document.add(new Paragraph("\n"));
					
					/*To give red colour from color flag - Starts Here*/
					
					/*To give red colour from color flag - Starts Here*/
					
					/* Changes for CR_80 CR Form Enhancements III on 26-Nov-09 by RR68151 - Starts Here */
						
					ArrayList arlEffClauseList = objChangeRequestBI.fetchEffectedClauses(objRequestVO);

					if (arlEffClauseList != null & arlEffClauseList.size() > 0) {
						
					PdfPCell effItems = new PdfPCell(new Phrase(
								"Affected Items:",
								strFontSizeBoldEleven));
					effItems.setBorderColor(new Color(255, 255, 255));
					effItems.setVerticalAlignment(Element.ALIGN_LEFT);
					effItems.setHorizontalAlignment(Element.ALIGN_LEFT);
					effItems.setColspan(3);
					effItemsTable.addCell(effItems);	

					effItems = new PdfPCell(new Phrase("Components",
							strFontSizeBoldEleven));
					effItems.setBackgroundColor(new Color(223, 223, 223));
					effItems.setVerticalAlignment(Element.ALIGN_CENTER);
					effItems.setHorizontalAlignment(Element.ALIGN_CENTER);
					effItemsTable.addCell(effItems);
					
					effItems = new PdfPCell(new Phrase("Default",
							strFontSizeBoldEleven));
					effItems.setBackgroundColor(new Color(223, 223, 223));
					effItems.setVerticalAlignment(Element.ALIGN_CENTER);
					effItems.setHorizontalAlignment(Element.ALIGN_CENTER);
					effItemsTable.addCell(effItems);
					
					effItems = new PdfPCell(new Phrase("Clause Description",
							strFontSizeBoldEleven));
					effItems.setBackgroundColor(new Color(223, 223, 223));
					effItems.setVerticalAlignment(Element.ALIGN_CENTER);
					effItems.setHorizontalAlignment(Element.ALIGN_CENTER);
					effItemsTable.addCell(effItems);
					
					for (int cntEff=0; cntEff < arlEffClauseList.size(); cntEff++)	{
						
						ClauseVO objEffClauseVo = (ClauseVO) arlEffClauseList.get(cntEff);
						
						strEffClaDesc = objEffClauseVo.getClauseDesc();
						
						ArrayList arlEffClaCompVo = objEffClauseVo.getComponentVO();
						
						if (arlEffClaCompVo.size() > 0)	{
							
						strEffClaCompDefault = "";
								
						for (int b = 0; b < arlEffClaCompVo.size(); b++) {

								ComponentVO objEffClaCompVo = (ComponentVO) arlEffClaCompVo.get(b);

								if (b == 0) {
									
									strEffClaComp = objEffClaCompVo.getComponentName();
								}

								else	{
									
									strEffClaComp = strEffClaComp + " ;" + "\n" +
													objEffClaCompVo.getComponentName();
								}
								
								if (objEffClaCompVo.isDefaultFlag()){
									
									strEffClaCompDefault = "X";
								}
								
								}
						
							}

						effItems = new PdfPCell(new Phrase(strEffClaComp,
								strFointSizeTen));
						effItems.setBackgroundColor(new Color(255, 255, 255));
						effItems.setVerticalAlignment(Element.ALIGN_CENTER);
						effItems.setHorizontalAlignment(Element.ALIGN_CENTER);
						effItemsTable.addCell(effItems);
						
						effItems = new PdfPCell(new Phrase(strEffClaCompDefault,
								strFointSizeTen));
						effItems.setBackgroundColor(new Color(255, 255, 255));
						effItems.setVerticalAlignment(Element.ALIGN_CENTER);
						effItems.setHorizontalAlignment(Element.ALIGN_CENTER);
						effItemsTable.addCell(effItems);	
						
						effItems = new PdfPCell(new Phrase(strEffClaDesc,
								strFointSizeTen));
						effItems.setBackgroundColor(new Color(255, 255, 255));
						effItems.setVerticalAlignment(Element.ALIGN_LEFT);
						effItems.setHorizontalAlignment(Element.ALIGN_LEFT);
						effItemsTable.addCell(effItems);
						
						}
					
					}
					
					if (objjRequestModelVO != null
							&& objjRequestModelVO.getRequestCompGrpVO() != null){
						
						if (objjRequestModelVO.getRequestCompGrpVO().getChangeTypeSeqNo() 
								== ApplicationConstants.CHANGE_TYPE_DELETE_COMPGRP){
							
							if (writer.fitsPage(effItemsTable)) {
								document.add(effItemsTable);
								document.add(new Paragraph("\n"));
							} else {
								
								document.newPage();
								document.add(effItemsTable);
								document.add(new Paragraph("\n"));
							}
						}
					}
					/* Changes for CR_80 CR Form Enhancements III on 26-Nov-09 by RR68151 - Ends Here */
					
					PdfPCell chngeComp = new PdfPCell(new Phrase(
							"Not Required/New/Existing/Revise/Delete Component:",
							strFontSizeBoldEleven));
					chngeComp.setBorderColor(new Color(255, 255, 255));
					chngeComp.setVerticalAlignment(Element.ALIGN_LEFT);
					chngeComp.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqCompChngeType.addCell(chngeComp);
					
					if (objjRequestModelVO != null
							&& objjRequestModelVO.getRequestCompVO() != null) {
						
						if (objjRequestModelVO.getRequestCompVO()
								.getChangeTypeDesc() != null) {
							strCompChngeType = objjRequestModelVO
							.getRequestCompVO().getChangeTypeDesc();
						}
						
						if (objjRequestModelVO.getRequestCompVO()
								.getOldCompName() != null) {
							strOldCompName = objjRequestModelVO
							.getRequestCompVO().getOldCompName();
						}
						
						if (objjRequestModelVO.getRequestCompVO() != null
								&& objjRequestModelVO.getRequestCompVO()
								.getOldCompDefaultFlag() != null) {
							
							if (objjRequestModelVO.getRequestCompVO()
									.getOldCompDefaultFlag().equals("Y")) {
								strOldCompDef = "Yes";
							} else {
								strOldCompDef = "No";
							}
						}
						
						if (objjRequestModelVO.getRequestCompVO()
								.getNewCompName() != null) {
							strNewCompName = objjRequestModelVO
							.getRequestCompVO().getNewCompName();
						}
						
						if (objjRequestModelVO.getRequestCompVO() != null
								&& objjRequestModelVO.getRequestCompVO()
								.getNewCompDefaultFlag() != null) {
							if (objjRequestModelVO.getRequestCompVO()
									.getNewCompDefaultFlag().equals("Y")) {
								strNewCompDef = "Yes";
							} else {
								strNewCompDef = "No";
							}
						}
						
						if (objjRequestModelVO.getRequestCompVO()
								.getCompNameColorFlag() != null) {
							strCompNameFlag = objjRequestModelVO
							.getRequestCompVO().getCompNameColorFlag();
						}
						if (objjRequestModelVO.getRequestCompVO()
								.getCompDefColorFlag() != null) {
							strCompDefFlag = objjRequestModelVO
							.getRequestCompVO().getCompDefColorFlag();
						}
						
					}
					
					chngeComp = new PdfPCell(new Phrase(strCompChngeType,
							strFointSizeTen));
					chngeComp.setBorderColor(new Color(255, 255, 255));
					chngeComp.setVerticalAlignment(Element.ALIGN_LEFT);
					chngeComp.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqCompChngeType.addCell(chngeComp);
					
					document.add(reqCompChngeType);
					
					PdfPCell oldComp = new PdfPCell(new Phrase("Change From",
							strFontSizeBoldEleven));
					oldComp.setBackgroundColor(new Color(223, 223, 223));
					oldComp.setVerticalAlignment(Element.ALIGN_LEFT);
					oldComp.setHorizontalAlignment(Element.ALIGN_LEFT);
					oldComp.setColspan(2);
					reqComp.addCell(oldComp);
					
					oldComp = new PdfPCell(new Phrase("Component/Value",
							strFontSizeBoldEleven));
					oldComp.setBackgroundColor(new Color(255, 255, 255));
					oldComp.setVerticalAlignment(Element.ALIGN_LEFT);
					oldComp.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqComp.addCell(oldComp);
					
					oldComp = new PdfPCell(new Phrase(strOldCompName,
							strFointSizeTen));
					oldComp.setBackgroundColor(new Color(255, 255, 255));
					oldComp.setVerticalAlignment(Element.ALIGN_LEFT);
					oldComp.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqComp.addCell(oldComp);
					
					oldComp = new PdfPCell(new Phrase("Default Component",
							strFontSizeBoldEleven));
					oldComp.setBackgroundColor(new Color(255, 255, 255));
					oldComp.setVerticalAlignment(Element.ALIGN_LEFT);
					oldComp.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqComp.addCell(oldComp);
					
					oldComp = new PdfPCell(new Phrase(strOldCompDef,
							strFointSizeTen));
					oldComp.setBackgroundColor(new Color(255, 255, 255));
					oldComp.setVerticalAlignment(Element.ALIGN_LEFT);
					oldComp.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqComp.addCell(oldComp);
					
					document.add(reqComp);
					document.add(new Paragraph("\n"));
					
					PdfPCell newComp = new PdfPCell(new Phrase("Change To",
							strFontSizeBoldEleven));
					newComp.setBackgroundColor(new Color(223, 223, 223));
					newComp.setVerticalAlignment(Element.ALIGN_LEFT);
					newComp.setHorizontalAlignment(Element.ALIGN_LEFT);
					newComp.setColspan(2);
					reqToComp.addCell(newComp);
					
					newComp = new PdfPCell(new Phrase("Component/Value",
							strFontSizeBoldEleven));
					newComp.setBackgroundColor(new Color(255, 255, 255));
					newComp.setVerticalAlignment(Element.ALIGN_LEFT);
					newComp.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqToComp.addCell(newComp);
					
					if (strCompNameFlag.equals("Y")) {
						newComp = new PdfPCell(new Phrase(strNewCompName,
								strFontRedSizeTen));
						newComp.setBackgroundColor(new Color(255, 255, 255));
						newComp.setVerticalAlignment(Element.ALIGN_LEFT);
						newComp.setHorizontalAlignment(Element.ALIGN_LEFT);
						reqToComp.addCell(newComp);
					} else {
						
						newComp = new PdfPCell(new Phrase(strNewCompName,
								strFointSizeTen));
						newComp.setBackgroundColor(new Color(255, 255, 255));
						newComp.setVerticalAlignment(Element.ALIGN_LEFT);
						newComp.setHorizontalAlignment(Element.ALIGN_LEFT);
						reqToComp.addCell(newComp);
					}
					
					newComp = new PdfPCell(new Phrase("Default Component",
							strFontSizeBoldEleven));
					newComp.setBackgroundColor(new Color(255, 255, 255));
					newComp.setVerticalAlignment(Element.ALIGN_LEFT);
					newComp.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqToComp.addCell(newComp);
					
					if (strCompDefFlag.equals("Y")) {
						newComp = new PdfPCell(new Phrase(strNewCompDef,
								strFontRedSizeTen));
						newComp.setBackgroundColor(new Color(255, 255, 255));
						newComp.setVerticalAlignment(Element.ALIGN_LEFT);
						newComp.setHorizontalAlignment(Element.ALIGN_LEFT);
						reqToComp.addCell(newComp);
					} else {
						
						newComp = new PdfPCell(new Phrase(strNewCompDef,
								strFointSizeTen));
						newComp.setBackgroundColor(new Color(255, 255, 255));
						newComp.setVerticalAlignment(Element.ALIGN_LEFT);
						newComp.setHorizontalAlignment(Element.ALIGN_LEFT);
						reqToComp.addCell(newComp);
					}
					
					//Modified For CR_80 CR Corm Enhancements III by RR68151
					if (writer.fitsPage(reqToComp)) {
						document.add(reqToComp);
						document.add(new Paragraph("\n"));
					} else {
						
						document.newPage();
						document.add(reqToComp);
						document.add(new Paragraph("\n"));
					}
					
				  /*document.add(reqToComp);
					document.add(new Paragraph("\n"));
					document.newPage();
					Modified For CR_80 CR Corm Enhancements III by RR68151 - Ends here*/
					/*For displaying Request Component Details - Ends Here*/

					/* Changes for CR_80 CR Form Enhancements III on 26-Nov-09 by RR68151 - Starts Here */
					if (objjRequestModelVO != null
							&& objjRequestModelVO.getRequestCompVO() != null){

						if (objjRequestModelVO.getRequestCompVO().getChangeTypeSeqNo() 
								== ApplicationConstants.CHANGE_TYPE_DELETE_COMPGRP){
							
							if (writer.fitsPage(effItemsTable)) {
								document.add(effItemsTable);
								document.add(new Paragraph("\n"));
							} else {
								
								document.newPage();
								document.add(effItemsTable);
								document.add(new Paragraph("\n"));
							}
						}
					
					}
					/* Changes for CR_80 CR Form Enhancements III on 26-Nov-09 by RR68151 - Ends Here */
					
					/*For creating Clause - Starts Here*/
					/*Change From Clause Table - Starts Here*/
					objRequestVO.setRequestID(Integer.parseInt(reqId.trim()));
					
					LogUtil.logMessage("Request ID"
							+ objRequestVO.getRequestID());
					String strClaChngeType = "";
					
					objRequestVO.setUserID(strUserID);
					RequestModelVO obRequestModelVO = objChangeRequestBI
					.fetchReqClauseDetails(objRequestVO);
					
					if (obRequestModelVO != null
							&& obRequestModelVO.getReqClauseVO() != null) {
						strClaChngeType = obRequestModelVO.getReqClauseVO()
						.getChangeTypeDesc();
						
					}
					
					PdfPCell claHeading = new PdfPCell(new Phrase(
							"New Clause/Modify Clause/Change Default Version/Delete Clause Version/Delete Clause:",
							strFontSizeBoldEleven));
					claHeading.setBorderColor(new Color(255, 255, 255));
					claHeading.setVerticalAlignment(Element.ALIGN_LEFT);
					claHeading.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqClaChngeType.addCell(claHeading);
					
					claHeading = new PdfPCell(new Phrase(strClaChngeType,
							strFointSizeTen));
					claHeading.setBorderColor(new Color(255, 255, 255));
					claHeading.setVerticalAlignment(Element.ALIGN_BOTTOM);
					claHeading.setHorizontalAlignment(Element.ALIGN_LEFT);
					reqClaChngeType.addCell(claHeading);
					document.add(reqClaChngeType);
					
					if (obRequestModelVO != null
							&& obRequestModelVO.getReqClauseVO() != null) {
						
						objClauseVO.setModelSeqNo(intModelSeqNo);
						
						objClauseVO.setSubSectionSeqNo(intSubSecSeqNo);
						
						objClauseVO.setClauseSeqNo(obRequestModelVO
								.getReqClauseVO().getFromClauseSeqNo());
						
						objClauseVO.setVersionNo(obRequestModelVO
								.getReqClauseVO().getFromClauseVerNo());
						
						LogUtil.logMessage("Clause Seq No"
								+ obRequestModelVO.getReqClauseVO()
								.getFromClauseSeqNo());
						LogUtil.logMessage("Clause VerNo"
								+ obRequestModelVO.getReqClauseVO()
								.getFromClauseVerNo());
						
						if (obRequestModelVO.getReqClauseVO()
								.getFromClauseSeqNo() > 0
								&& obRequestModelVO.getReqClauseVO()
								.getFromClauseVerNo() > 0) {
							
							ModelSelectClauseRevBI objModelSelectClauseRevBI = ServiceFactory
							.getModelSelectClauseRevBO();
							ArrayList arlFromClause = objModelSelectClauseRevBI
							.fetchClauseVersions(objClauseVO);
							
							ArrayList arlClause = (ArrayList) arlFromClause
							.get(0);
							
							if (arlClause.size() > 0) {

								ClauseVO objjClauseVO = (ClauseVO) arlClause
								.get(0);
								
								if (objjClauseVO != null) {
									
									if (obRequestModelVO.getReqClauseVO()
											.getFromClauseNo() != null) {
										strFromClauseNo = obRequestModelVO
										.getReqClauseVO()
										.getFromClauseNo();
									}

									//Added for CR_80 CR Form Enhancements III on 23-Nov-09 by RR68151 - Ends Here
									if (obRequestModelVO.getReqClauseVO()
											.getFromClauseVerNo() != 0) {
										if(obRequestModelVO.getReqClauseVO()
												.getChangeTypeSeqNO() == 10){
											strFromClauseVerNo =  "ALL";
										}
										else{
											strFromClauseVerNo =  String.valueOf(obRequestModelVO
											.getReqClauseVO().getFromClauseVerNo());
										}
									}
									//Added for CR_80 CR Form Enhancements III on 23-Nov-09 by RR68151 - Ends Here
									
									if (objjClauseVO.getClauseDesc() != null) {
										strFromClauseDesc = objjClauseVO
										.getClauseDesc();
									}
									
									if (objjClauseVO != null
											&& objjClauseVO.getCompName() != null) {
										
										for (int b = 0; b < objjClauseVO
										.getCompName().size(); b++) {
											if (b == 0) {
												strFromComp = (String) objjClauseVO
												.getCompName().get(b);
												
											} else {
												strFromComp = strFromComp
												+ ","
												+ (String) objjClauseVO
												.getCompName()
												.get(b);
											}
										}
									}
									
									if (objjClauseVO.getEdlNO() != null) {
										for (int b = 0; b < objjClauseVO
										.getEdlNO().size(); b++) {
											
											if (b == 0) {
												
												strEdl = "EDL "
													+ (String) objjClauseVO
													.getEdlNO()
													.get(b) + "\n";
												
											} else {
												
												strEdl = strEdl
												+ "EDL "
												+ (String) objjClauseVO
												.getEdlNO()
												.get(b) + "\n";
											}
											
										}
									}
									
									if (objjClauseVO.getRefEDLNO() != null) {
										
										for (int b = 0; b < objjClauseVO
										.getRefEDLNO().size(); b++) {
											
											if (b == 0) {
											/*	strRefEdl = "ref:EDL "
													+ (String) objjClauseVO
													.getRefEDLNO()
													.get(b) + "\n";
											*/
												//CR 87
												strRefEdl = "(ref EDL "
													+ (String) objjClauseVO
													.getRefEDLNO()
													.get(b) +")"+ "\n";
											} else {
												
												/*strRefEdl = strRefEdl
												+ "ref:EDL "
												+ (String) objjClauseVO
												.getRefEDLNO()
												.get(b) + "\n";*/
												
												strRefEdl = strRefEdl
												+ "(ref EDL "
												+ (String) objjClauseVO
												.getRefEDLNO()
												.get(b) +")"+ "\n";
											}
										}
									}
									
									if (objjClauseVO.getPartOfClaNo() != null) {
										
										for (int b = 0; b < objjClauseVO
										.getPartOfClaNo().size(); b++) {
											if (objjClauseVO.getPartOfClaNo()
													.get(b) != null) {
												//CR 87 Of changed to of
												strPartOf = "Part of "
													+ (String) objjClauseVO
													.getPartOfClaNo()
													.get(b) + "\n";
											}
										}
									}
									if (objjClauseVO.getDwONumber() != 0) {
										strDWO = "DWO NO "
											+ String.valueOf(objjClauseVO
													.getDwONumber()) + "\n";
									}
									
									if (objjClauseVO.getPartNumber() != 0) {
										strPartNo = "Part NO "
											+ String.valueOf(objjClauseVO
													.getPartNumber())
													+ "\n";
									}
									
									if (objjClauseVO.getPriceBookNumber() != 0) {
										strPriceBookNo = "Price Book No "
											+ String.valueOf(objjClauseVO
													.getPriceBookNumber())
													+ "\n";
									}
									if (objjClauseVO.getStandardEquipmentDesc() != null) {
										strStdEqp = objjClauseVO
										.getStandardEquipmentDesc()
										+ "\n";
									}
									if (objjClauseVO.getComments() != null) {
										strComments = objjClauseVO
										.getComments()
										+ "\n";
									}
									
									if (objjClauseVO.getTableArrayData1() != null
											&& objjClauseVO
											.getTableArrayData1()
											.size() > 0) {
										
										LogUtil.logMessage("Table Dta"
												+ objjClauseVO
												.getTableArrayData1()
												.size());
										LogUtil.logMessage("Table Dta VAlue"
												+ objjClauseVO
												.getTableArrayData1()
												.get(0));
										
										arlFromTableData = getTableDataColumnsCount(objjClauseVO
												.getTableArrayData1());
										if (arlFromTableData != null
												&& arlFromTableData.size() > 0) {
											
											intFromColCnt = (Integer) arlFromTableData
											.get(0);
											
										}
										arlFromData = objjClauseVO
										.getTableArrayData1();
									}
									
								}
								
							}
						}
						
					}
					PdfPCell fromClause = new PdfPCell(new Phrase(
							"Change From", strFontSizeBoldEleven));
					//c.setBorderColor(new Color(255, 255, 255));
					fromClause.setBackgroundColor(new Color(223, 223, 223));
					fromClause.setVerticalAlignment(Element.ALIGN_TOP);
					fromClause.setHorizontalAlignment(Element.ALIGN_TOP);
					fromClause.setColspan(2);
					pdfFromClause.addCell(fromClause);
					
					fromClause = new PdfPCell(new Phrase("Clause Number",
							strFontSizeBoldEleven));
					fromClause.setBackgroundColor(new Color(255, 255, 255));
					fromClause.setVerticalAlignment(Element.ALIGN_LEFT);
					fromClause.setHorizontalAlignment(Element.ALIGN_LEFT);
					pdfFromClause.addCell(fromClause);
					
					fromClause = new PdfPCell(new Phrase(strFromClauseNo,
							strFointSizeTen));
					fromClause.setBackgroundColor(new Color(255, 255, 255));
					fromClause.setVerticalAlignment(Element.ALIGN_LEFT);
					fromClause.setHorizontalAlignment(Element.ALIGN_LEFT);
					pdfFromClause.addCell(fromClause);

					//Added for CR_80 CR Form Enhancements III on 23-Nov-09 by RR68151 - Ends Here
					fromClause = new PdfPCell(new Phrase("Clause Version",
							strFontSizeBoldEleven));
					fromClause.setBackgroundColor(new Color(255, 255, 255));
					fromClause.setVerticalAlignment(Element.ALIGN_LEFT);
					fromClause.setHorizontalAlignment(Element.ALIGN_LEFT);
					pdfFromClause.addCell(fromClause);
					
					fromClause = new PdfPCell(new Phrase(strFromClauseVerNo,
							strFointSizeTen));
					fromClause.setBackgroundColor(new Color(255, 255, 255));
					fromClause.setVerticalAlignment(Element.ALIGN_LEFT);
					fromClause.setHorizontalAlignment(Element.ALIGN_LEFT);
					pdfFromClause.addCell(fromClause);
					
					//Added for CR_80 CR Form Enhancements III on 23-Nov-09 by RR68151 - Ends Here
					
					fromClause = new PdfPCell(new Phrase("Clause Description",
							strFontSizeBoldEleven));
					fromClause.setBackgroundColor(new Color(255, 255, 255));
					fromClause.setVerticalAlignment(Element.ALIGN_LEFT);
					fromClause.setHorizontalAlignment(Element.ALIGN_LEFT);
					pdfFromClause.addCell(fromClause);
					
					int tabColCnt = 0;
					if (intFromColCnt.intValue() == 0) {
						tabColCnt = 1;
					} else {
						tabColCnt = intFromColCnt.intValue();
					}
					PdfPTable TBData = new PdfPTable(tabColCnt);
					TBData.setWidthPercentage(100);
					// Added for CR-111 for remove <p> tag to the output PDF
					if (strFromClauseDesc.startsWith("<p>"))
					{
						String strFileName = "";
						Paragraph paraClauseDesc = new Paragraph();
						paraClauseDesc.setFont(customFont);//Added for CR-130
						ArrayList p = HTMLWorker.parseToList(new StringReader(strFromClauseDesc), null);//Edited for CR-130
					    
						/*strFileName=ApplicationUtil.strConvertToHTMLFormat(strFromClauseDesc);
						StyleSheet styles = new StyleSheet();
					    styles.loadTagStyle("p","size","12px");
					    styles.loadTagStyle("p","face","Times");*/
					    
					    for (int k1 = 0; k1 < p.size(); ++k1) {
					    	paraClauseDesc.add((Element) p.get(k1));
					     
					    }
					   
						PdfPCell c1 = new PdfPCell(paraClauseDesc);
						c1.setBackgroundColor(new Color(255, 255, 255));
						c1.setHorizontalAlignment(Element.ALIGN_LEFT);
						c1.setBorder(PdfPCell.BOTTOM);
						c1.setBorderColor(Color.WHITE);
						c1.setColspan(tabColCnt);
						TBData.addCell(c1);
					}else{
					
						PdfPCell c1 = new PdfPCell(new Paragraph(strFromClauseDesc, customFont));//Edited for CR-130
						c1.setBackgroundColor(new Color(255, 255, 255));
						c1.setHorizontalAlignment(Element.ALIGN_LEFT);
						c1.setBorder(PdfPCell.BOTTOM);
						c1.setBorderColor(Color.WHITE);
						c1.setColspan(tabColCnt);
						TBData.addCell(c1);
					}
					
					// Added for CR-111 for remove <p> tag to the output PDF Ends Here				
					
					if (arlFromData != null && arlFromData.size() > 0) {
						
						for (int tab = 0; tab < arlFromData.size(); tab++) {
							
							ArrayList arlTableData = (ArrayList) arlFromData
							.get(tab);
							
							if (intHeader == 0)
								fontHeader = strFontSizeBoldTen;
							else
								fontHeader = strFontSizeNoBoldTen;
							
							LogUtil
							.logMessage("strClauseNum  Entering table data........"
									+ tab);
							
							if (tabColCnt == 1 || tabColCnt == 2
									|| tabColCnt == 3 || tabColCnt == 4
									|| tabColCnt == 5) {
								
								PdfPCell c1 = new PdfPCell(new Paragraph(
										(String) arlTableData.get(0),
										fontHeader));
								//Modified for CR_111 to add borders for Table Data
								//c1.setBorder(PdfPCell.TOP);
								c1.setBorderColor(new Color(0, 0, 0));
								TBData.addCell(c1);
							}
							
							if (tabColCnt == 2 || tabColCnt == 3
									|| tabColCnt == 4 || tabColCnt == 5) {
								
								PdfPCell c1 = new PdfPCell(new Paragraph(
										(String) arlTableData.get(1),
										fontHeader));
								//Modified for CR_111 to add borders for Table Data
								//c1.setBorder(PdfPCell.TOP);
								c1.setBorderColor(new Color(0, 0, 0));
								TBData.addCell(c1);
							}
							
							if (tabColCnt == 3 || tabColCnt == 4
									|| tabColCnt == 5) {
								
								PdfPCell c1 = new PdfPCell(new Paragraph(
										(String) arlTableData.get(2),
										fontHeader));
								//Modified for CR_111 to add borders for Table Data
								//c1.setBorder(PdfPCell.TOP);
								c1.setBorderColor(new Color(0, 0, 0));
								TBData.addCell(c1);
							}
							
							if (tabColCnt == 4 || tabColCnt == 5) {
								
								PdfPCell c1 = new PdfPCell(new Paragraph(
										(String) arlTableData.get(3),
										fontHeader));
								//Modified for CR_111 to add borders for Table Data
								//c1.setBorder(PdfPCell.TOP);
								c1.setBorderColor(new Color(0, 0, 0));
								TBData.addCell(c1);
							}
							
							if (tabColCnt == 5) {
								
								PdfPCell c1 = new PdfPCell(new Paragraph(
										(String) arlTableData.get(4),
										fontHeader));
								//Modified for CR_111 to add borders for Table Data
								//c1.setBorder(PdfPCell.TOP);
								c1.setBorderColor(new Color(0, 0, 0));
								TBData.addCell(c1);
							}
							
							intHeader++;
						}
					}
					
					fromClause = new PdfPCell((TBData));
					//fromClause.setBackgroundColor(new Color(255, 255, 255));
					fromClause.setBorderColor(Color.BLACK);
									
					fromClause.setVerticalAlignment(Element.ALIGN_LEFT);
					fromClause.setHorizontalAlignment(Element.ALIGN_LEFT);
					pdfFromClause.addCell(fromClause);
					
					fromClause = new PdfPCell(new Phrase(
							"Components Tied to Clause", strFontSizeBoldEleven));
					fromClause.setBackgroundColor(new Color(255, 255, 255));
					fromClause.setVerticalAlignment(Element.ALIGN_LEFT);
					fromClause.setHorizontalAlignment(Element.ALIGN_LEFT);
					pdfFromClause.addCell(fromClause);
					
					fromClause = new PdfPCell(new Phrase(strFromComp,
							strFointSizeTen));
					fromClause.setBackgroundColor(new Color(255, 255, 255));
					fromClause.setVerticalAlignment(Element.ALIGN_LEFT);
					fromClause.setHorizontalAlignment(Element.ALIGN_LEFT);
					pdfFromClause.addCell(fromClause);
					
					fromClause = new PdfPCell(new Phrase("Engineering Data",
							strFontSizeBoldEleven));
					fromClause.setBackgroundColor(new Color(255, 255, 255));
					fromClause.setVerticalAlignment(Element.ALIGN_LEFT);
					fromClause.setHorizontalAlignment(Element.ALIGN_LEFT);
					pdfFromClause.addCell(fromClause);
					//Modified for CR_80 to update the order of engineering data by RR68151
					fromClause = new PdfPCell(new Phrase(strRefEdl + "" 
							+ strEdl + "" + strPartOf + "" + strDWO + ""
							+ strPartNo + "" + strPriceBookNo + "" + strStdEqp
							+ "" + strComments, strFointSizeTen));
					fromClause.setBackgroundColor(new Color(255, 255, 255));
					fromClause.setVerticalAlignment(Element.ALIGN_LEFT);
					fromClause.setHorizontalAlignment(Element.ALIGN_LEFT);
					pdfFromClause.addCell(fromClause);
					
					if (writer.fitsPage(pdfFromClause)) {
						document.add(pdfFromClause);
						document.add(new Paragraph("\n"));
					} else {
						
						document.newPage();
						document.add(pdfFromClause);
						document.add(new Paragraph("\n"));
					}
					/*Change From Clause Table - Ends Here*/
					
					/*Change To Clause Table - Starts Here*/
					
					PdfPCell toClause = new PdfPCell(new Phrase("Change To",
							strFontSizeBoldEleven));
					//c.setBorderColor(new Color(255, 255, 255));
					toClause.setBackgroundColor(new Color(223, 223, 223));
					toClause.setVerticalAlignment(Element.ALIGN_TOP);
					toClause.setHorizontalAlignment(Element.ALIGN_TOP);
					toClause.setColspan(2);
					pdfToClause.addCell(toClause);
					
					toClause = new PdfPCell(new Phrase("Lead Component Group",
							strFontSizeBoldEleven));
					toClause.setBackgroundColor(new Color(255, 255, 255));
					//toClause.setBackgroundColor(new Color(223, 223, 223));
					toClause.setVerticalAlignment(Element.ALIGN_TOP);
					toClause.setHorizontalAlignment(Element.ALIGN_TOP);
					pdfToClause.addCell(toClause);
					
					if (obRequestModelVO != null
							&& obRequestModelVO.getReqClauseVO() != null
							&& obRequestModelVO.getReqClauseVO()
							.getChangeTypeDesc() != null 
							//Added for CR_80 by RR68151 to accomodate Delete Clause
							&& !(obRequestModelVO.getReqClauseVO()
							.getChangeTypeSeqNO() == 10)) {
						toClause = new PdfPCell(new Phrase(strNewCompGrpName,
								strFointSizeTen));
						toClause.setBackgroundColor(new Color(255, 255, 255));
						toClause.setVerticalAlignment(Element.ALIGN_TOP);
						toClause.setHorizontalAlignment(Element.ALIGN_TOP);
						pdfToClause.addCell(toClause);
					} else {
						toClause = new PdfPCell(new Phrase("", strFointSizeTen));
						toClause.setBackgroundColor(new Color(255, 255, 255));
						toClause.setVerticalAlignment(Element.ALIGN_TOP);
						toClause.setHorizontalAlignment(Element.ALIGN_TOP);
						pdfToClause.addCell(toClause);
						
					}
					
					toClause = new PdfPCell(new Phrase("Lead Component",
							strFontSizeBoldEleven));
					//c.setBorderColor(new Color(255, 255, 255));
					toClause.setBackgroundColor(new Color(255, 255, 255));
					toClause.setVerticalAlignment(Element.ALIGN_TOP);
					toClause.setHorizontalAlignment(Element.ALIGN_TOP);
					pdfToClause.addCell(toClause);
					
					if (obRequestModelVO != null
							&& obRequestModelVO.getReqClauseVO() != null
							&& obRequestModelVO.getReqClauseVO()
							.getChangeTypeDesc() != null
							//Added for CR_80 by RR68151 to accomodate Delete Clause
							&& !(obRequestModelVO.getReqClauseVO()
							.getChangeTypeSeqNO() == 10)) {
						toClause = new PdfPCell(new Phrase(strNewCompName,
								strFointSizeTen));
						//c.setBorderColor(new Color(255, 255, 255));
						toClause.setBackgroundColor(new Color(255, 255, 255));
						toClause.setVerticalAlignment(Element.ALIGN_TOP);
						toClause.setHorizontalAlignment(Element.ALIGN_TOP);
						pdfToClause.addCell(toClause);
					} else {
						toClause = new PdfPCell(new Phrase("", strFointSizeTen));
						//c.setBorderColor(new Color(255, 255, 255));
						toClause.setBackgroundColor(new Color(255, 255, 255));
						toClause.setVerticalAlignment(Element.ALIGN_TOP);
						toClause.setHorizontalAlignment(Element.ALIGN_TOP);
						pdfToClause.addCell(toClause);
					}
					
					if (obRequestModelVO != null
							&& obRequestModelVO.getReqClauseVO() != null) {
						
						if (obRequestModelVO.getReqClauseVO()
								.getToParentClauseNo() != null) {
							strToParCla = obRequestModelVO.getReqClauseVO()
							.getToParentClauseNo();
						}
						
						if (obRequestModelVO.getReqClauseVO()
								.getToParentClaDesc() != null) {
							strToParClaDesc = obRequestModelVO.getReqClauseVO()
							.getToParentClaDesc();
							
						}
						
						if (obRequestModelVO.getReqClauseVO()
								.getToClaVerClauseNo() != null) {
							
							strToClaVerDesc = obRequestModelVO.getReqClauseVO()
							.getToClaVerClauseNo()
							+ "\n";
							
						}
						if (obRequestModelVO.getReqClauseVO().getToClaVerDesc() != null) {
							//Added for CR-111 to remove <p> tag in clause version.
							if (obRequestModelVO.getReqClauseVO().getToClaVerDesc().startsWith("<p>"))
								strToClaVerDesc = "<p>" + strToClaVerDesc + "</p>" 
								+ obRequestModelVO.getReqClauseVO()
								.getToClaVerDesc();
							else
								strToClaVerDesc = strToClaVerDesc + obRequestModelVO.getReqClauseVO()
								.getToClaVerDesc();
						}
						
						if (obRequestModelVO.getReqClauseVO().getToClauseNo() != null) {
							strToNewClaNo = obRequestModelVO.getReqClauseVO()
							.getToClauseNo();
						}
						if (obRequestModelVO.getReqClauseVO().getToClauseDesc() != null) {
							strNewClaDesc = obRequestModelVO.getReqClauseVO()
							.getToClauseDesc();
						}
						
						if (obRequestModelVO.getReqClauseVO().getClaComp()
								.size() > 0) {
							for (int b = 0; b < obRequestModelVO
							.getReqClauseVO().getClaComp().size(); b++) {
								
								if (b == 0) {
									strNewClaComp = String
									.valueOf(obRequestModelVO
											.getReqClauseVO()
											.getClaComp().get(b));
								} else {
									strNewClaComp = strNewClaComp
									+ ","
									+ String.valueOf(obRequestModelVO
											.getReqClauseVO()
											.getClaComp().get(b));
								}
							}
							
						}

/*						if (obRequestModelVO.getReqClauseVO() != null
								&& obRequestModelVO.getReqClauseVO()
								.getRefEdlNo() != null) {
							
							for (int b = 0; b < obRequestModelVO
							.getReqClauseVO().getRefEdlNo().size(); b++) {
								
								Modified for CR_80 CR Form Enhancements III on 20-Nov-09 by RR68151								
								if (b == 0) {
									strToRefEdl = "ref:EDL "
										+ (String) obRequestModelVO
										.getReqClauseVO()
										.getRefEdlNo().get(b)
										+ "\n";
								} else {
									
									strToRefEdl = strToRefEdl
									+ "ref:EDL "
									+ (String) obRequestModelVO
									.getReqClauseVO()
									.getRefEdlNo().get(b)
									+ "\n";
								}

								if  ((obRequestModelVO.getReqClauseVO().getRefEdlClrFlag()[b]).equals(ApplicationConstants.YES)){
									
									enggData.add(new Chunk("ref:EDL ",strFointSizeTen));
									enggData.add(new Chunk((String) obRequestModelVO
											.getReqClauseVO()
											.getRefEdlNo().get(b)
											+ "\n",strFontRedSizeTen));
								}
								else
								{
									enggData.add(new Chunk("ref:EDL ",strFointSizeTen));
									enggData.add(new Chunk((String) obRequestModelVO
											.getReqClauseVO()
											.getRefEdlNo().get(b)
											+ "\n",strFointSizeTen));
								}
								//Modified for CR_80 CR Form Enhancements III on 20-Nov-09 by RR68151 - Ends here
							}
						}*/
						
						if (obRequestModelVO.getReqClauseVO() != null
								&& obRequestModelVO.getReqClauseVO().getEdlNo() != null) {
							
							for (int b = 0; b < obRequestModelVO
							.getReqClauseVO().getEdlNo().size(); b++) {
								
								/*Modified for CR_80 CR Form Enhancements III on 20-Nov-09 by RR68151			
								if (b == 0) {
									
									strToEdl = "EDL "
										+ (String) obRequestModelVO
										.getReqClauseVO()
										.getEdlNo().get(b) + "\n";
									
								} else {
									
									strToEdl = strToEdl
									+ "EDL "
									+ (String) obRequestModelVO
									.getReqClauseVO()
									.getEdlNo().get(b) + "\n";
								}*/

								if  ((obRequestModelVO.getReqClauseVO().getEdlClrFlag()[b]).equals(ApplicationConstants.YES)){
									
									enggData.add(new Chunk("EDL ",strFointSizeTen));
									enggData.add(new Chunk((String) obRequestModelVO
											.getReqClauseVO()
											.getEdlNo().get(b)
											+ "\n",strFontRedSizeTen));
								}
								else
								{
									enggData.add(new Chunk("EDL ",strFointSizeTen));
									enggData.add(new Chunk((String) obRequestModelVO
											.getReqClauseVO()
											.getEdlNo().get(b)
											+ "\n",strFointSizeTen));
								}
								//Modified for CR_80 CR Form Enhancements III on 20-Nov-09 by RR68151 - Ends here
							}
						}
						//CR 87 Start
						if (obRequestModelVO.getReqClauseVO() != null
								&& obRequestModelVO.getReqClauseVO()
								.getRefEdlNo() != null) {
							
							for (int b = 0; b < obRequestModelVO
							.getReqClauseVO().getRefEdlNo().size(); b++) {
								
								if  ((obRequestModelVO.getReqClauseVO().getRefEdlClrFlag()[b]).equals(ApplicationConstants.YES)){

									enggData.add(new Chunk("(ref EDL ",strFointSizeTen));
									enggData.add(new Chunk(")"));
									
									enggData.add(new Chunk((String) obRequestModelVO
											.getReqClauseVO()
											.getRefEdlNo().get(b)
											+ "\n",strFontRedSizeTen));
								}
								else
								{
									enggData.add(new Chunk("(ref EDL ",strFointSizeTen));
									enggData.add(new Chunk(")"));
									enggData.add(new Chunk((String) obRequestModelVO
											.getReqClauseVO()
											.getRefEdlNo().get(b)
											+ "\n",strFointSizeTen));
								}
								//Modified for CR_80 CR Form Enhancements III on 20-Nov-09 by RR68151 - Ends here
							}
						}
						//CR 87 End
						
						
						if (obRequestModelVO.getReqClauseVO() != null
								&& obRequestModelVO.getReqClauseVO()
								.getPartClaNo() != null) {
							
							for (int b = 0; b < obRequestModelVO
							.getReqClauseVO().getPartClaNo().size(); b++) {
								if (obRequestModelVO.getReqClauseVO()
										.getPartClaNo().get(b) != null) {
									
									/*Modified for CR_80 CR Form Enhancements III on 20-Nov-09 by RR68151	
									strToPartOf = "Part Of "
										+ (String) obRequestModelVO
										.getReqClauseVO()
										.getPartClaNo().get(b)
										+ "\n";*/
									
									if  ((obRequestModelVO.getReqClauseVO().getPartOfClrFlag()[b]).equals(ApplicationConstants.YES)){
										//enggData.add(new Chunk("Part Of ",strFointSizeTen));
										//CR 87
										enggData.add(new Chunk("Part of ",strFointSizeTen));
										enggData.add(new Chunk((String) obRequestModelVO
												.getReqClauseVO()
												.getPartClaNo().get(b)
												+ "\n",strFontRedSizeTen));
									}
									else
									{
										//enggData.add(new Chunk("Part Of ",strFointSizeTen));
										//CR 87
										enggData.add(new Chunk("Part of ",strFointSizeTen));										
										enggData.add(new Chunk((String) obRequestModelVO
												.getReqClauseVO()
												.getPartClaNo().get(b)
												+ "\n",strFointSizeTen));
									}
									//Modified for CR_80 CR Form Enhancements III on 20-Nov-09 by RR68151 - Ends here
								}
							}
						}
						
						if (obRequestModelVO.getReqClauseVO() != null
								&& obRequestModelVO.getReqClauseVO()
								.getTodwONumber() != 0) {
							/*Modified for CR_80 CR Form Enhancements III on 20-Nov-09 by RR68151
							strToDWO = "DWO NO "
								+ String.valueOf(obRequestModelVO
										.getReqClauseVO().getTodwONumber())
										+ "\n";*/

							if (obRequestModelVO.getReqClauseVO().getDwoNoClrFlag().equals(ApplicationConstants.YES)){
								enggData.add(new Chunk("DWO NO ",strFointSizeTen));
								enggData.add(new Chunk(String.valueOf(obRequestModelVO
										.getReqClauseVO().getTodwONumber())
										+ "\n",strFontRedSizeTen));
							}
							else
							{
								enggData.add(new Chunk("DWO NO ",strFointSizeTen));
								enggData.add(new Chunk(String.valueOf(obRequestModelVO
										.getReqClauseVO().getTodwONumber())
										+ "\n",strFointSizeTen));
							}
							//Modified for CR_80 CR Form Enhancements III on 20-Nov-09 by RR68151 - Ends here
						}
						
						if (obRequestModelVO.getReqClauseVO() != null
								&& obRequestModelVO.getReqClauseVO()
								.getToPartNumber() != 0) {
							/*Modified for CR_80 CR Form Enhancements III on 20-Nov-09 by RR68151
							strToPartNo = "Part NO "
								+ String
								.valueOf(obRequestModelVO
										.getReqClauseVO()
										.getToPartNumber()) + "\n";*/

							if (obRequestModelVO.getReqClauseVO().getPartNoClrFlag().equals(ApplicationConstants.YES)){
								enggData.add(new Chunk("Part NO ",strFointSizeTen));
								enggData.add(new Chunk(String
										.valueOf(obRequestModelVO
												.getReqClauseVO()
												.getToPartNumber()) + "\n",strFontRedSizeTen));
							}
							else
							{
								enggData.add(new Chunk("Part NO ",strFointSizeTen));
								enggData.add(new Chunk(String
										.valueOf(obRequestModelVO
												.getReqClauseVO()
												.getToPartNumber()) + "\n",strFointSizeTen));
							}
							//Modified for CR_80 CR Form Enhancements III on 20-Nov-09 by RR68151 - Ends here
							
						}
						
						if (obRequestModelVO.getReqClauseVO() != null
								&& obRequestModelVO.getReqClauseVO()
								.getTopriceBookNumber() != 0) {
							/*Modified for CR_80 CR Form Enhancements III on 20-Nov-09 by RR68151
							strToPriceBookNo = "Price Book No "
								+ String.valueOf(obRequestModelVO
										.getReqClauseVO()
										.getTopriceBookNumber()) + "\n";*/

							if (obRequestModelVO.getReqClauseVO().getPriceBookClrFlag().equals(ApplicationConstants.YES)){
								enggData.add(new Chunk("Price Book No ",strFointSizeTen));
								enggData.add(new Chunk(String.valueOf(obRequestModelVO
										.getReqClauseVO()
										.getTopriceBookNumber()) + "\n",strFontRedSizeTen));
							}
							else
							{
								enggData.add(new Chunk("Price Book No ",strFointSizeTen));
								enggData.add(new Chunk(String.valueOf(obRequestModelVO
										.getReqClauseVO()
										.getTopriceBookNumber()) + "\n",strFointSizeTen));
							}
							//Modified for CR_80 CR Form Enhancements III on 20-Nov-09 by RR68151 - Ends here
						}
						if (obRequestModelVO.getReqClauseVO() != null
								&& obRequestModelVO.getReqClauseVO()
								.getToStdEqpDesc() != null) {
							/*Modified for CR_80 CR Form Enhancements III on 20-Nov-09 by RR68151
							strToStdEqp = obRequestModelVO.getReqClauseVO()
							.getToStdEqpDesc()
							+ "\n";*/

							if (obRequestModelVO.getReqClauseVO().getStdEqpClrFlag().equals(ApplicationConstants.YES)){
								enggData.add(new Chunk(obRequestModelVO.getReqClauseVO()
										.getToStdEqpDesc()
										+ "\n",strFontRedSizeTen));
							}
							else
							{
								enggData.add(new Chunk(obRequestModelVO.getReqClauseVO()
										.getToStdEqpDesc()
										+ "\n",strFointSizeTen));
							}
							//Modified for CR_80 CR Form Enhancements III on 20-Nov-09 by RR68151 - Ends here
						}
						if (obRequestModelVO.getReqClauseVO() != null
								&& obRequestModelVO.getReqClauseVO()
								.getToComments() != null) {
							/*Modified for CR_80 CR Form Enhancements III on 20-Nov-09 by RR68151
							strToComments = obRequestModelVO.getReqClauseVO()
							.getToComments()
							+ "\n";*/

							if (obRequestModelVO.getReqClauseVO().getEnggDataClrFlag().equals(ApplicationConstants.YES)){
								enggData.add(new Chunk(obRequestModelVO.getReqClauseVO()
										.getToComments()
										+ "\n",strFontRedSizeTen));
							}
							else
							{
								enggData.add(new Chunk(obRequestModelVO.getReqClauseVO()
										.getToComments()
										+ "\n",strFointSizeTen));
							}
							//Modified for CR_80 CR Form Enhancements III on 20-Nov-09 by RR68151 - Ends here
						}
						
						if (obRequestModelVO.getReqClauseVO() != null
								&& obRequestModelVO.getReqClauseVO()
								.getTableData().size() > 0) {
							
							LogUtil.logMessage("New Clause Table Dta"
									+ obRequestModelVO.getReqClauseVO()
									.getTableData().size());
							LogUtil.logMessage("Table Dta VAlue"
									+ obRequestModelVO.getReqClauseVO()
									.getTableData().get(0));
							
							arlToTableData = getTableDataColumnsCount(obRequestModelVO
									.getReqClauseVO().getTableData());
							if (arlToTableData != null
									&& arlToTableData.size() > 0) {
								
								intToColCnt = (Integer) arlToTableData.get(0);
								
							}
							arlToData = obRequestModelVO.getReqClauseVO()
							.getTableData();
							
						}
						/* Removed For CR_80 CR Form Enhancements III by RR68151
						if (obRequestModelVO.getReqClauseVO()
								.getToDefaultFlag() != null) {
							if (obRequestModelVO.getReqClauseVO()
									.getToDefaultFlag().equals("Y")) {
								strDefaultCla = "Yes";
							} else {
								strDefaultCla = "No";
							}
						}*/
						
						if (obRequestModelVO.getReqClauseVO().getClaNoFlag() != null) {
							
							strClaNoFlag = obRequestModelVO.getReqClauseVO()
							.getClaNoFlag();
							LogUtil.logMessage("Cla No Flag" + strClaNoFlag);
							
						}
						
						if (obRequestModelVO.getReqClauseVO().getClaDescFlag() != null) {
							
							strClaDescFlag = obRequestModelVO.getReqClauseVO()
							.getClaDescFlag();
							LogUtil
							.logMessage("Cla Desc Flag"
									+ strClaDescFlag);
						}
						
						if (obRequestModelVO.getReqClauseVO().getVersionFlag() != null) {
							
							strClaVerFlag = obRequestModelVO.getReqClauseVO()
							.getVersionFlag();
							LogUtil.logMessage("Cla Ver Flag" + strClaVerFlag);
							
						}
						
					}
					
					toClause = new PdfPCell(new Phrase(
							"Parent Clause Description", strFontSizeBoldEleven));
					//c.setBorderColor(new Color(255, 255, 255));
					toClause.setBackgroundColor(new Color(255, 255, 255));
					toClause.setVerticalAlignment(Element.ALIGN_TOP);
					toClause.setHorizontalAlignment(Element.ALIGN_TOP);
					pdfToClause.addCell(toClause);
					
					toClause = new PdfPCell(new Phrase(strToParCla + "\n"
							+ strToParClaDesc, strFointSizeTen));
					//c.setBorderColor(new Color(255, 255, 255));
					toClause.setBackgroundColor(new Color(255, 255, 255));
					toClause.setVerticalAlignment(Element.ALIGN_TOP);
					toClause.setHorizontalAlignment(Element.ALIGN_TOP);
					pdfToClause.addCell(toClause);
					
					if (obRequestModelVO.getReqClauseVO() != null
							&& obRequestModelVO.getReqClauseVO()
							.getChangeTypeSeqNO() == 5) {
						toClause = new PdfPCell(new Phrase("Clause Version",
								strFontSizeBoldEleven));
						//c.setBorderColor(new Color(255, 255, 255));
						toClause.setBackgroundColor(new Color(255, 255, 255));
						toClause.setVerticalAlignment(Element.ALIGN_TOP);
						toClause.setHorizontalAlignment(Element.ALIGN_TOP);
						pdfToClause.addCell(toClause);
						
						toClause = new PdfPCell(new Phrase("", strFointSizeTen));
						//c.setBorderColor(new Color(255, 255, 255));
						toClause.setBackgroundColor(new Color(255, 255, 255));
						toClause.setVerticalAlignment(Element.ALIGN_TOP);
						toClause.setHorizontalAlignment(Element.ALIGN_TOP);
						pdfToClause.addCell(toClause);
						
					} else {
						
						if (strClaVerFlag.equals("Y")) {
							customFont = new Font(customBaseFont, 12, 0,Color.RED);
						} else {
							customFont = new Font(customBaseFont, 12, 0,Color.BLACK);
						}
						
						toClause = new PdfPCell(new Phrase("Clause Version",
								strFontSizeBoldEleven));
						//c.setBorderColor(new Color(255, 255, 255));
						toClause.setBackgroundColor(new Color(255, 255, 255));
						toClause.setVerticalAlignment(Element.ALIGN_TOP);
						toClause.setHorizontalAlignment(Element.ALIGN_TOP);
						pdfToClause.addCell(toClause);
						//Added for CR-111 for remove <p> tag to the output PDF
						if (strToClaVerDesc.startsWith("<p>"))
						{
							String strFileName = "";
							Paragraph paraClauseDesc = new Paragraph();
							paraClauseDesc.setFont(customFont);
							ArrayList p = HTMLWorker.parseToList(new StringReader(strToClaVerDesc), null);//Edited for CR-130
						    
							/*strFileName=ApplicationUtil.strConvertToHTMLFormat(strToClaVerDesc);
							StyleSheet styles = new StyleSheet();
						    styles.loadTagStyle("p","size","12px");
						    styles.loadTagStyle("p","face","Times");
						    if (strClaVerFlag.equals("Y")){
						    	styles.loadTagStyle("p","color", "#ff0000");
						    }*/
							
						    for (int k1 = 0; k1 < p.size(); ++k1) {
						    	paraClauseDesc.add((Element) p.get(k1));
						     
						    }
						   
							toClause = new PdfPCell(paraClauseDesc);
							toClause.setBackgroundColor(new Color(255, 255, 255));
							toClause.setVerticalAlignment(Element.ALIGN_TOP);
							toClause.setHorizontalAlignment(Element.ALIGN_TOP);
							pdfToClause.addCell(toClause);
						}
						else
						{
							toClause = new PdfPCell(new Phrase(strToClaVerDesc,
								customFont));//Edited for CR-130
							//c.setBorderColor(new Color(255, 255, 255));
							toClause.setBackgroundColor(new Color(255, 255, 255));
							toClause.setVerticalAlignment(Element.ALIGN_TOP);
							toClause.setHorizontalAlignment(Element.ALIGN_TOP);
							pdfToClause.addCell(toClause);
						}
						//Added for CR-111 for remove <p> tag to the output PDF ends here
						
					}
					
					if (obRequestModelVO.getReqClauseVO() != null
							&& obRequestModelVO.getReqClauseVO()
							.getChangeTypeSeqNO() == 6) {
						toClause = new PdfPCell(new Phrase("New Clause Number",
								strFontSizeBoldEleven));
						//c.setBorderColor(new Color(255, 255, 255));
						toClause.setBackgroundColor(new Color(255, 255, 255));
						toClause.setVerticalAlignment(Element.ALIGN_TOP);
						toClause.setHorizontalAlignment(Element.ALIGN_TOP);
						pdfToClause.addCell(toClause);
						
						toClause = new PdfPCell(new Phrase("", strFointSizeTen));
						//c.setBorderColor(new Color(255, 255, 255));
						toClause.setBackgroundColor(new Color(255, 255, 255));
						toClause.setVerticalAlignment(Element.ALIGN_TOP);
						toClause.setHorizontalAlignment(Element.ALIGN_TOP);
						pdfToClause.addCell(toClause);
						
						toClause = new PdfPCell(
								new Phrase("New Clause Description",
										strFontSizeBoldEleven));
						//c.setBorderColor(new Color(255, 255, 255));
						toClause.setBackgroundColor(new Color(255, 255, 255));
						toClause.setVerticalAlignment(Element.ALIGN_TOP);
						toClause.setHorizontalAlignment(Element.ALIGN_TOP);
						pdfToClause.addCell(toClause);
						
						toClause = new PdfPCell(new Phrase("", strFointSizeTen));
						//c.setBorderColor(new Color(255, 255, 255));
						toClause.setBackgroundColor(new Color(255, 255, 255));
						toClause.setVerticalAlignment(Element.ALIGN_TOP);
						toClause.setHorizontalAlignment(Element.ALIGN_TOP);
						pdfToClause.addCell(toClause);
						
					} else {
						
						if (strClaNoFlag.equals("Y")) {
							strClaNoFlagFont = new Font(Font.TIMES_ROMAN, 11,
									0, Color.RED);
						} else {
							strClaNoFlagFont = new Font(Font.TIMES_ROMAN, 11,
									0, Color.BLACK);
						}
						
						if (strClaDescFlag.equals("Y")) {
							customFont = new Font(customBaseFont, 12, 0,Color.RED);
						} else {
							customFont = new Font(customBaseFont, 12, 0,Color.BLACK);
						}
						
						toClause = new PdfPCell(new Phrase("New Clause Number",
								strFontSizeBoldEleven));
						//c.setBorderColor(new Color(255, 255, 255));
						toClause.setBackgroundColor(new Color(255, 255, 255));
						toClause.setVerticalAlignment(Element.ALIGN_TOP);
						toClause.setHorizontalAlignment(Element.ALIGN_TOP);
						pdfToClause.addCell(toClause);
						
						toClause = new PdfPCell(new Phrase(strToNewClaNo,
								strClaNoFlagFont));
						//c.setBorderColor(new Color(255, 255, 255));
						toClause.setBackgroundColor(new Color(255, 255, 255));
						toClause.setVerticalAlignment(Element.ALIGN_TOP);
						toClause.setHorizontalAlignment(Element.ALIGN_TOP);
						pdfToClause.addCell(toClause);
						
						toClause = new PdfPCell(
								new Phrase("New Clause Description",
										strFontSizeBoldEleven));
						toClause.setBackgroundColor(new Color(255, 255, 255));
						toClause.setVerticalAlignment(Element.ALIGN_TOP);
						toClause.setHorizontalAlignment(Element.ALIGN_TOP);
						pdfToClause.addCell(toClause);
						
						if (intToColCnt.intValue() == 0) {
							toTabColCnt = 1;
						} else {
							toTabColCnt = intToColCnt.intValue();
						}
						PdfPTable TBToData = new PdfPTable(toTabColCnt);
						TBToData.setWidthPercentage(100);
						//Added for CR-111 for remove <p> tag to the output PDF
						if (strNewClaDesc.startsWith("<p>"))
						{
							Paragraph paraClauseDesc = new Paragraph();
							paraClauseDesc.setFont(customFont);//Added for CR-130
						    
							ArrayList p = HTMLWorker.parseToList(new StringReader(strNewClaDesc), null);//Edited for CR-130
						    
						    for (int k1 = 0; k1 < p.size(); ++k1) {
						    	paraClauseDesc.add((Element) p.get(k1));
						    }
						   
							PdfPCell c1 = new PdfPCell(paraClauseDesc);
							c1.setBackgroundColor(new Color(255, 255, 255));
							c1.setHorizontalAlignment(Element.ALIGN_LEFT);
							c1.setColspan(toTabColCnt);
							c1.setBorder(PdfPCell.BOTTOM);
							c1.setBorderColor(Color.WHITE);
							TBToData.addCell(c1);
						}else{
							PdfPCell c1 = new PdfPCell(new Paragraph(strNewClaDesc,
									customFont));//Edited for CR-130
							c1.setBackgroundColor(new Color(255, 255, 255));
							c1.setHorizontalAlignment(Element.ALIGN_LEFT);
							c1.setColspan(toTabColCnt);
							c1.setBorder(PdfPCell.BOTTOM);
							c1.setBorderColor(Color.WHITE);
							TBToData.addCell(c1);
						}
						//Added for CR-111 for remove <p> tag to the output PDF ends here
						
						if (arlToData != null && arlToData.size() > 0) {
							
							for (int tab = 0; tab < arlToData.size(); tab++) {
								
								ArrayList arlTableData = (ArrayList) arlToData
								.get(tab);
								
								if (intToHeader == 0)
									fontToHeader = strFontSizeBoldTen;
								else
									fontToHeader = strFontSizeNoBoldTen;
								
								LogUtil
								.logMessage("strClauseNum  Entering table data........"
										+ tab);
								
								if (toTabColCnt == 1 || toTabColCnt == 2
										|| toTabColCnt == 3 || toTabColCnt == 4
										|| toTabColCnt == 5) {
									
									PdfPCell c1 = new PdfPCell(new Paragraph(
											(String) arlTableData.get(0),
											fontToHeader));
									//Modified for CR_111 to add borders for Table Data
									//c1.setBorder(PdfPCell.TOP);
									c1.setBorderColor(new Color(0, 0, 0));
									TBToData.addCell(c1);
								}
								
								if (toTabColCnt == 2 || toTabColCnt == 3
										|| toTabColCnt == 4 || toTabColCnt == 5) {
									
									PdfPCell c1 = new PdfPCell(new Paragraph(
											(String) arlTableData.get(1),
											fontToHeader));
									//Modified for CR_111 to add borders for Table Data
									//c1.setBorder(PdfPCell.TOP);
									c1.setBorderColor(new Color(0, 0, 0));
									TBToData.addCell(c1);
								}
								
								if (toTabColCnt == 3 || toTabColCnt == 4
										|| toTabColCnt == 5) {
									
									PdfPCell c1 = new PdfPCell(new Paragraph(
											(String) arlTableData.get(2),
											fontToHeader));
									//Modified for CR_111 to add borders for Table Data
									//c1.setBorder(PdfPCell.TOP);
									c1.setBorderColor(new Color(0, 0, 0));
									TBToData.addCell(c1);
								}
								
								if (toTabColCnt == 4 || toTabColCnt == 5) {
									
									PdfPCell c1 = new PdfPCell(new Paragraph(
											(String) arlTableData.get(3),
											fontToHeader));
									//Modified for CR_111 to add borders for Table Data
									//c1.setBorder(PdfPCell.TOP);
									c1.setBorderColor(new Color(0, 0, 0));
									TBToData.addCell(c1);
								}
								
								if (toTabColCnt == 5) {
									
									PdfPCell c1 = new PdfPCell(new Paragraph(
											(String) arlTableData.get(4),
											fontToHeader));
									//Modified for CR_111 to add borders for Table Data
									//c1.setBorder(PdfPCell.TOP);
									c1.setBorderColor(new Color(0, 0, 0));
									TBToData.addCell(c1);
								}
								
								intToHeader++;
							}
						}
						
						toClause = new PdfPCell((TBToData));
						toClause.setBackgroundColor(new Color(255, 255, 255));
						toClause.setBorderColor(Color.BLACK);
						toClause.setVerticalAlignment(Element.ALIGN_LEFT);
						toClause.setHorizontalAlignment(Element.ALIGN_LEFT);
						pdfToClause.addCell(toClause);
						
					}
					
					toClause = new PdfPCell(new Phrase(
							"Components Tied To Clause", strFontSizeBoldEleven));
					//c.setBorderColor(new Color(255, 255, 255));
					toClause.setBackgroundColor(new Color(255, 255, 255));
					toClause.setVerticalAlignment(Element.ALIGN_TOP);
					toClause.setHorizontalAlignment(Element.ALIGN_TOP);
					pdfToClause.addCell(toClause);
					
					toClause = new PdfPCell(new Phrase(strNewClaComp,
							strFointSizeTen));
					//c.setBorderColor(new Color(255, 255, 255));
					toClause.setBackgroundColor(new Color(255, 255, 255));
					toClause.setVerticalAlignment(Element.ALIGN_TOP);
					toClause.setHorizontalAlignment(Element.ALIGN_TOP);
					pdfToClause.addCell(toClause);
					
					toClause = new PdfPCell(new Phrase("Engineering Data",
							strFontSizeBoldEleven));
					//c.setBorderColor(new Color(255, 255, 255));
					toClause.setBackgroundColor(new Color(255, 255, 255));
					toClause.setVerticalAlignment(Element.ALIGN_TOP);
					toClause.setHorizontalAlignment(Element.ALIGN_TOP);
					pdfToClause.addCell(toClause);
					
					toClause = new PdfPCell(enggData);
							/*Removed for CR_80 CR Form Enhancements III on 20-Nov-09 by RR68151
							new Phrase(strToEdl + "" + strToRefEdl + ""
									+ strToPartOf + "" + strToDWO + ""
									+ strToPartNo + "" + strToPriceBookNo + ""
									+ strToStdEqp + "" + strToComments,
									strFointSizeTen));*/
					//c.setBorderColor(new Color(255, 255, 255));
					toClause.setBackgroundColor(new Color(255, 255, 255));
					toClause.setVerticalAlignment(Element.ALIGN_TOP);
					toClause.setHorizontalAlignment(Element.ALIGN_TOP);
					pdfToClause.addCell(toClause);
					/*Removed For CR_80 CR Form Enhancements III by RR68151
					toClause = new PdfPCell(new Phrase("Default Clause",
							strFontSizeBoldEleven));
					//c.setBorderColor(new Color(255, 255, 255));
					toClause.setBackgroundColor(new Color(255, 255, 255));
					toClause.setVerticalAlignment(Element.ALIGN_TOP);
					toClause.setHorizontalAlignment(Element.ALIGN_TOP);
					pdfToClause.addCell(toClause);
					
					toClause = new PdfPCell(new Phrase(strDefaultCla,
							strFointSizeTen));
					//c.setBorderColor(new Color(255, 255, 255));
					toClause.setBackgroundColor(new Color(255, 255, 255));
					toClause.setVerticalAlignment(Element.ALIGN_TOP);
					toClause.setHorizontalAlignment(Element.ALIGN_TOP);
					pdfToClause.addCell(toClause);*/
					
					if (writer.fitsPage(pdfToClause)) {
						document.add(pdfToClause);
						document.add(new Paragraph("\n"));
					} else {
						
						document.newPage();
						document.add(pdfToClause);
						document.add(new Paragraph("\n"));
					}
					
					document.add(new Paragraph("\n"));
					/*Change To Clause Table - Ends Here*/
					
					PdfPCell reason = new PdfPCell(new Phrase(
							"Reason for Change ", strFontSizeBoldEleven));
					reason.setBackgroundColor(new Color(255, 255, 255));
					reason.setBorderColor(new Color(255, 255, 255));
					reason.setVerticalAlignment(Element.ALIGN_LEFT);
					reason.setHorizontalAlignment(Element.ALIGN_LEFT);
					pdfReason.addCell(reason);
					
					reason = new PdfPCell(new Phrase(":", strFointSizeBoldTen));
					reason.setBackgroundColor(new Color(255, 255, 255));
					reason.setBorderColor(new Color(255, 255, 255));
					reason.setVerticalAlignment(Element.ALIGN_LEFT);
					reason.setHorizontalAlignment(Element.ALIGN_LEFT);
					pdfReason.addCell(reason);
					
					reason = new PdfPCell(
							new Phrase(strReason, strFointSizeTen));
					reason.setBackgroundColor(new Color(255, 255, 255));
					reason.setBorderColor(new Color(255, 255, 255));
					reason.setHorizontalAlignment(Element.ALIGN_LEFT);
					pdfReason.addCell(reason);
					document.add(pdfReason);
					document.add(new Paragraph("\n"));
					
					//Added for CR-59 Administrator Comments
					if (intStatusTypeSeqNo != 4 && intStatusTypeSeqNo != 6) {
						
						if (!strAdmnCmnts.equals("")) {
							
							PdfPCell admnCmts = new PdfPCell(new Phrase(
									"Administrator Comments ",
									strFontSizeBoldEleven));
							admnCmts
							.setBackgroundColor(new Color(255, 255, 255));
							admnCmts.setBorderColor(new Color(255, 255, 255));
							admnCmts.setVerticalAlignment(Element.ALIGN_LEFT);
							admnCmts.setHorizontalAlignment(Element.ALIGN_LEFT);
							pdfComents.addCell(admnCmts);
							
							admnCmts = new PdfPCell(new Phrase(":",
									strFointSizeBoldTen));
							admnCmts
							.setBackgroundColor(new Color(255, 255, 255));
							admnCmts.setBorderColor(new Color(255, 255, 255));
							admnCmts.setVerticalAlignment(Element.ALIGN_LEFT);
							admnCmts.setHorizontalAlignment(Element.ALIGN_LEFT);
							pdfComents.addCell(admnCmts);
							
							admnCmts = new PdfPCell(new Phrase(strAdmnCmnts,
									strFointSizeTen));
							admnCmts
							.setBackgroundColor(new Color(255, 255, 255));
							admnCmts.setBorderColor(new Color(255, 255, 255));
							admnCmts.setHorizontalAlignment(Element.ALIGN_LEFT);
							
							pdfComents.addCell(admnCmts);
							
							document.add(pdfComents);
							document.add(new Paragraph("\n"));
							
						}
						
					}
					
					/*For creating Clause - Ends Here*/
					
					/*For creating Request ID Submitted By and Date and Action Taken By and Data- Starts Here*/
					
					PdfPCell submitBy = new PdfPCell(new Phrase(
							"Submitted By ", strFointSizeBoldTen));
					submitBy.setBorderColor(new Color(255, 255, 255));
					submitBy.setVerticalAlignment(Element.ALIGN_LEFT);
					submitBy.setHorizontalAlignment(Element.ALIGN_LEFT);
					submittedInfo.addCell(submitBy);
					
					submitBy = new PdfPCell(
							new Phrase(":", strFointSizeBoldTen));
					submitBy.setBorderColor(new Color(255, 255, 255));
					submitBy.setVerticalAlignment(Element.ALIGN_LEFT);
					submitBy.setHorizontalAlignment(Element.ALIGN_LEFT);
					submittedInfo.addCell(submitBy);
					
					if (objRequestVo.getReqByUserLN() != null
							&& objRequestVo.getReqByUserFN() != null) {
						strSubmitBy = objRequestVo.getReqByUserLN().trim()
						+ " " + objRequestVo.getReqByUserFN().trim();
					}
					if (objRequestVo.getReqByUserLN() == null
							&& objRequestVo.getReqByUserFN() != null) {
						strSubmitBy = objRequestVo.getReqByUserFN().trim();
					}
					if (objRequestVo.getReqByUserLN() != null
							&& objRequestVo.getReqByUserFN() == null) {
						strSubmitBy = objRequestVo.getReqByUserLN().trim();
					}
					
					submitBy = new PdfPCell(new Phrase(strSubmitBy,
							strFointSizeTen));
					submitBy.setBorderColor(new Color(255, 255, 255));
					submitBy.setVerticalAlignment(Element.ALIGN_LEFT);
					submitBy.setHorizontalAlignment(Element.ALIGN_LEFT);
					submitBy.setNoWrap(true);
					submittedInfo.addCell(submitBy);
					
					PdfPCell submitDate = new PdfPCell(new Phrase("Date :",
							strFointSizeBoldTen));
					submitDate.setBorderColor(new Color(255, 255, 255));
					submitDate.setVerticalAlignment(Element.ALIGN_LEFT);
					submitDate.setHorizontalAlignment(Element.ALIGN_LEFT);
					submittedInfo.addCell(submitDate);
					
					submitDate = new PdfPCell(new Phrase(strSubmitDate,
							strFointSizeTen));
					submitDate.setBorderColor(new Color(255, 255, 255));
					submitDate.setVerticalAlignment(Element.ALIGN_LEFT);
					submitDate.setHorizontalAlignment(Element.ALIGN_LEFT);
					submitBy.setNoWrap(true);
					submittedInfo.addCell(submitDate);
					
					if (objRequestVo.getApproveByUserLN() != null
							&& objRequestVo.getApproveByUserFN() != null) {
						strApprovedBy = objRequestVo.getApproveByUserLN()
						.trim()
						+ " "
						+ objRequestVo.getApproveByUserFN().trim();
					}
					if (objRequestVo.getApproveByUserLN() == null
							&& objRequestVo.getApproveByUserFN() != null) {
						strApprovedBy = objRequestVo.getApproveByUserFN()
						.trim();
					}
					if (objRequestVo.getApproveByUserLN() != null
							&& objRequestVo.getApproveByUserFN() == null) {
						strApprovedBy = objRequestVo.getApproveByUserLN()
						.trim();
					}
					
					PdfPCell actionBy = new PdfPCell(new Phrase(
							"Master Spec Changed By ", strFointSizeBoldTen));
					actionBy.setBorderColor(new Color(255, 255, 255));
					actionBy.setVerticalAlignment(Element.ALIGN_LEFT);
					actionBy.setHorizontalAlignment(Element.ALIGN_LEFT);
					submittedInfo.addCell(actionBy);
					
					actionBy = new PdfPCell(
							new Phrase(":", strFointSizeBoldTen));
					actionBy.setBorderColor(new Color(255, 255, 255));
					actionBy.setVerticalAlignment(Element.ALIGN_LEFT);
					actionBy.setHorizontalAlignment(Element.ALIGN_LEFT);
					submittedInfo.addCell(actionBy);
					
					actionBy = new PdfPCell(new Phrase(strApprovedBy,
							strFointSizeTen));
					actionBy.setBorderColor(new Color(255, 255, 255));
					actionBy.setVerticalAlignment(Element.ALIGN_LEFT);
					actionBy.setHorizontalAlignment(Element.ALIGN_LEFT);
					actionBy.setNoWrap(true);
					submittedInfo.addCell(actionBy);
					
					PdfPCell actionDate = new PdfPCell(new Phrase("Date :",
							strFointSizeBoldTen));
					actionDate.setBorderColor(new Color(255, 255, 255));
					actionDate.setVerticalAlignment(Element.ALIGN_LEFT);
					actionDate.setHorizontalAlignment(Element.ALIGN_LEFT);
					submittedInfo.addCell(actionDate);
					
					submitDate = new PdfPCell(new Phrase(strApprovedDate,
							strFointSizeTen));
					submitDate.setBorderColor(new Color(255, 255, 255));
					submitDate.setVerticalAlignment(Element.ALIGN_LEFT);
					submitDate.setHorizontalAlignment(Element.ALIGN_LEFT);
					submitDate.setNoWrap(true);
					submittedInfo.addCell(submitDate);
					
					document.add(submittedInfo);
					
					/*For creating Request ID Submitted By and Date and Action Taken By and Data- Ends Here*/
					
				}
				
			}
			
		} catch (Exception objExp) {
			throw new Exception(objExp);
		}
		
	}

	/***************************************************************************
	 * This method is for generating the PDF Header & Footer
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param Document
	 * @throws Exception
	 * @author VV49326
	 **************************************************************************/
	
	private static void showHeaderFooter(Document document) throws Exception {
		
		Phrase whole = new Phrase();
		HeaderFooter footer = null;
		
		//Added for making year as dyanmic
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		try {
			
			Phrase phraseAfter = new Phrase();
			phraseAfter.add(Chunk.NEWLINE);
			whole.add(phraseAfter);
			
			phraseAfter = new Paragraph(11f,
					"Proprietary Notice: © Electro Motive Diesel, Inc "
					+ String.valueOf(year) + ".", new Font(
							Font.TIMES_ROMAN, 8, Font.BOLD + Font.UNDERLINE,
							Color.BLACK));
			
			whole.add(phraseAfter);
			
			phraseAfter = new Paragraph(11f, "\n");
			whole.add(phraseAfter);
			
			phraseAfter = new Paragraph(
					11f,
					"Information contained in this document is proprietary to Electro-Motive Diesel, Inc. No part or whole of this document may be disclosed to third parties, copied or reproduced in any",
					strFontSizeEight);
			
			whole.add(phraseAfter);
			
			phraseAfter = new Paragraph(11f, "\n");
			whole.add(phraseAfter);
			
			phraseAfter = new Paragraph(
					11f,
					"manner without prior written permission of Electro-Motive Diesel, Inc.",
					strFontSizeEight);
			
			whole.add(phraseAfter);
			
			footer = new HeaderFooter(whole, false);
			//footer.setPageNumber(1);
			footer.setAlignment(Element.ALIGN_LEFT);
			footer.disableBorderSide(3);
			document.setFooter(footer);
			
		} catch (Exception objExp) {
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

	//Added for CR_80 CR Form Enhancements III on 27-Nov-09 by RR68151
	/*************************************************************************************
	 * This Method is used to fetch the Re-assign a Change Request Form
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 * @author CM68219
	 *************************************************************************************/
	public ActionForward reAssignChangeRequest(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil
		.logMessage("Entering ModifyChangeRequestAction.reAssignChangeRequest");
		String strForward = ApplicationConstants.SUCCESS;
		ModifyChangeRequestForm objModifyChangeRequestForm = (ModifyChangeRequestForm) objActionForm;
		
		RequestVO objRequestVO = new RequestVO();
		ArrayList arlRequestList = new ArrayList();
		ArrayList arlSeqlist = new ArrayList();
		
		UserVO objUserVO = new UserVO();
		ArrayList arlUsers = null;
		int intStatus = 0;
		
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			ChangeRequestBI objChangeRequestBI = ServiceFactory
			.getChangeRequestBO();
			UserMaintBI objUserMaintBO = ServiceFactory.getUserMaintBO();
			
			if (objHttpServletRequest
					.getParameter(ApplicationConstants.REQUEST_ID) != null
					&& !"".equals(objHttpServletRequest
							.getParameter(ApplicationConstants.REQUEST_ID))) {
				objRequestVO.setRequestID(Integer.parseInt(objHttpServletRequest
								.getParameter(ApplicationConstants.REQUEST_ID).trim()));
			} else {
				objRequestVO.setRequestID(0);
			}
						
			objRequestVO.setAssigneeId(objModifyChangeRequestForm.getHdnAssigneeId());
			
			objRequestVO.setUserID(objLoginVo.getUserID());
			
			intStatus = objChangeRequestBI.reAssignChangeRequest(objRequestVO);
			
			if (intStatus == 0) {
				objModifyChangeRequestForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objModifyChangeRequestForm.setMessageID("" + intStatus);
			}
			
			objModifyChangeRequestForm.setAssigneeId("");
			
			arlUsers = objUserMaintBO.fetchUsers(objUserVO);
			if (arlUsers != null && arlUsers.size() > 0) {
				objModifyChangeRequestForm.setLastList(arlUsers);
			}
			
			arlSeqlist = objChangeRequestBI.fetchStatus(objRequestVO);
			if (arlSeqlist != null && arlSeqlist.size() > 0) {
				objModifyChangeRequestForm.setStatusList(arlSeqlist);
				objModifyChangeRequestForm
				.setHdStatus(objModifyChangeRequestForm.getHdStatus());
				objModifyChangeRequestForm
				.setHdnLastName(objModifyChangeRequestForm
						.getLastName());
				
				if (objModifyChangeRequestForm.getRequestId() != null
						&& !"".equals(objModifyChangeRequestForm.getRequestId()
								.trim())) {
					objModifyChangeRequestForm.setHdnRequestId(Integer
							.parseInt(ApplicationUtil
									.trim(objModifyChangeRequestForm
											.getRequestId())));
					
				}
				objModifyChangeRequestForm
				.setHdnFromDate(objModifyChangeRequestForm
						.getFromDate());
				objModifyChangeRequestForm
				.setHdnToDate(objModifyChangeRequestForm.getToDate());
			}
			
			objModifyChangeRequestForm
			.setStatusSeqNo(objModifyChangeRequestForm.getStatusSeqNo());
			objModifyChangeRequestForm.setLastName(objModifyChangeRequestForm
					.getLastName().trim());
			
			if (objModifyChangeRequestForm.getRequestId() != null
					&& !"".equals(objModifyChangeRequestForm.getRequestId()
							.trim())) {
				objModifyChangeRequestForm.setRequestId(ApplicationUtil
						.trim(objModifyChangeRequestForm.getRequestId()));
			} else {
				
				objModifyChangeRequestForm.setRequestId("");
			}
			
			objModifyChangeRequestForm.setFromDate(objModifyChangeRequestForm
					.getFromDate());
			objModifyChangeRequestForm.setToDate(objModifyChangeRequestForm
					.getToDate());
			
			LogUtil.logMessage("Assignee ID "
					+ objModifyChangeRequestForm.getHdnAssigneeId());
			
			if (objModifyChangeRequestForm.getStatusSeqNo() == -1) {
				objRequestVO.setStatusTypeSeqNo(0);
			} else {
				objRequestVO.setStatusTypeSeqNo(objModifyChangeRequestForm
						.getStatusSeqNo());
			}
			
			if (objModifyChangeRequestForm.getFromDate() != null) {
				
				objRequestVO.setReqSubFromDate(objModifyChangeRequestForm
						.getFromDate());
			}
			
			objRequestVO.setLastName(objModifyChangeRequestForm.getLastName());
			
			if (objModifyChangeRequestForm.getToDate() != null) {
				objRequestVO.setReqSubToDate(objModifyChangeRequestForm
						.getToDate());
			}
			
			if (objModifyChangeRequestForm.getRequestId() != null
					&& !"".equals(objModifyChangeRequestForm.getRequestId()
							.trim())) {
				objRequestVO.setRequestID(Integer.parseInt(ApplicationUtil
						.trim(objModifyChangeRequestForm.getRequestId())));
			} else {
				objRequestVO.setRequestID(0);
			}
			
			arlRequestList = objChangeRequestBI
			.fetchRequestDetails(objRequestVO);
			
			if (arlRequestList.size() != 0) {
				objModifyChangeRequestForm.setRequestList(arlRequestList);
				
			}
			if (arlRequestList.size() == 0) {
				objModifyChangeRequestForm.setMethod("REQUEST");
				
			}
			
		} catch (Exception objExp) {
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			objExp.printStackTrace();
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
		
	}
   //Added for CR_80 CR Form Enhancements III on 27-Nov-09 by RR68151 - Ends Here
}