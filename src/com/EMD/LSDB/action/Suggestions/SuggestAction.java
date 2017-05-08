/* AK49339
 * Created on Aug 24, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.action.Suggestions;

import java.io.IOException;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletOutputStream;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.EMD.LSDB.action.common.EMDAction;
import com.EMD.LSDB.bo.SpecComparison.ClauseCompareBO;
import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.OrderSectionBI;
import com.EMD.LSDB.bo.serviceinterface.SuggestBI;
import com.EMD.LSDB.bo.serviceinterface.UserMaintBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.form.SpecComparison.ClauseCompareForm;
import com.EMD.LSDB.form.Suggestions.SuggestForm;
import com.EMD.LSDB.vo.admn.UserVO;
import com.EMD.LSDB.vo.common.FileVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SuggestVO;
import java.util.Iterator;



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

public class SuggestAction extends EMDAction {
	/***************************************************************************
	 * This Method is used to submit the User Suggestions
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward submitSuggestion(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		LogUtil.logMessage("Entering SuggestAction.submitSuggestion");
		
		int intSubmitSuggestion = 0;
        //Added for CR-118
		ArrayList arlAllSuggestions = new ArrayList();
		
		SuggestForm objSuggestForm = (SuggestForm) objActionForm;
		
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			
			LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage("Entering SuggestAction.setUserID: " + objLoginVo.getUserID());
			
			SuggestVO objSuggestVO = new SuggestVO();
			
			FileVO objFileVO = new FileVO();
			FormFile objFormFile = objSuggestForm.getUploadedFile();
			
			objFileVO.setuploadedFile(objFormFile.getFileData());
			objFileVO.setFileName(objFormFile.getFileName());
			objFileVO.setContentType(objFormFile.getContentType());
			objFileVO.setFileStream(objFormFile.getInputStream());
			objFileVO.setFileLength(objFormFile.getFileSize());
			
			LogUtil.logMessage("objFileVO.getFileName():   "+objFileVO.getFileName());
			LogUtil.logMessage("objFileVO.getFileLength():   "+objFileVO.getFileLength());
			LogUtil.logMessage("objFileVO.getContentType():   "+objFileVO.getContentType());
			
			if (objFileVO.getFileLength() > 1024*1024)	{
			
				objHttpServletResponse.setContentType("text/plain");
			    objHttpServletResponse.getWriter().write(ApplicationConstants.FILESIZETOOLARGE);
			}
			else {
			
			objSuggestVO.setFileVO(objFileVO);
			
			objSuggestVO.setUserID(objLoginVo.getUserID());
			objSuggestVO.setScreenName(objSuggestForm.getScreenName());
			objSuggestVO.setSuggestions(objSuggestForm.getSuggestions());
			
			SuggestBI objSuggestBI = ServiceFactory.getSuggestBO();
			
			intSubmitSuggestion = objSuggestBI.submitSuggestion(objSuggestVO);
			
			if (intSubmitSuggestion != 0) {
				objSuggestForm.setMessageID(ApplicationConstants.SUCCESS);
			}			

			objHttpServletResponse.setContentType("text/plain");
		    objHttpServletResponse.getWriter().write(ApplicationConstants.SUCCESS);
			
			}
			
		} catch (Exception objExp) {
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "error");
			LogUtil.logError(objErrorInfo);
			objHttpServletResponse.setContentType("text/plain");
			try{
				objHttpServletResponse.getWriter().write(ApplicationConstants.FAILURE);
			}
			catch(IOException objIoExp){
				objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(objExp.getMessage() + "error");
				LogUtil.logError(objErrorInfo);
			}
		}
		return null;
	}
	
	/***************************************************************************
	 * This Method is used to fetch the Suggestion Details
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward fetchSuggestions(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering SuggestAction.fetchSuggestions");
		String strForward = ApplicationConstants.SUCCESS;
		ArrayList arlSuggestions = new ArrayList();
		ArrayList arlSuggStatus = new ArrayList();
		ArrayList arlUsersList = new ArrayList();
		SuggestForm objSuggestForm = (SuggestForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			SuggestVO objSuggestVO = new SuggestVO();
									
			if (objSuggestForm.getSuggestStatusCode() != 0)	
				objSuggestVO.setSuggestStatusCode(objSuggestForm.getSuggestStatusCode());
			 else
				objSuggestVO.setSuggestStatusCode(ApplicationConstants.PENDING_ONE);
						
			LogUtil.logMessage("SuggestStatusCode : " + objSuggestVO.getSuggestStatusCode());
			
			objSuggestVO.setUserID(objLoginVo.getUserID());
			//Added for CR-127
			//LogUtil.logMessage("objSuggestForm.getSelectedInitiator() : " + objSuggestForm.getSelectedInitiator());
			if(objSuggestForm.getSelectedInitiator() == null){
				objSuggestForm.setSelectedInitiator("0");
			}else{
				objSuggestVO.setSelectedInitiator(objSuggestForm.getSelectedInitiator());
			}
			
			SuggestBI objSuggestBI = ServiceFactory.getSuggestBO();
			
			arlSuggestions = objSuggestBI.fetchSuggestions(objSuggestVO);
			
			if (arlSuggestions != null || arlSuggestions.size() != 0) {
				objSuggestForm.setSuggestionList(arlSuggestions);
			}
			
			//Added for CR-127
			UserVO objUserVO = new UserVO();
			
			objUserVO.setUserID(objLoginVo.getUserID());
			objUserVO.setOrderbyFlag(0);
			UserMaintBI objUserMaintBO = ServiceFactory.getUserMaintBO();
			arlUsersList = objUserMaintBO.fetchUsers(objUserVO);
			objSuggestForm.setInitiatorList(arlUsersList);
			//Added for CR-127 Ends here
			
			
			/*arlSuggStatus = objSuggestBI.fetchSuggestionStatus(objSuggestVO);
			
			if (arlSuggStatus != null || arlSuggStatus.size() != 0) {
				objSuggestForm.setSuggestionStatusList(arlSuggStatus);
			}*/
			
			objSuggestForm.setSuggestStatusCode(objSuggestVO.getSuggestStatusCode());
			
		} catch (Exception objExp) {
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
		
	}
//	Added for CR-118 by vb106565
	/***************************************************************************
	 * This Method is used to export the Suggestion Details
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
	public ActionForward suggestionsToExcel(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		LogUtil.logMessage("Inside SuggestAction.suggestionsToExcel");
		String strForward = ApplicationConstants.SUCCESS;
		ArrayList arlSuggestions = new ArrayList();
		SuggestForm objSuggestForm = (SuggestForm) objActionForm;
		int rowCount=0;
		int intPos=0;
		String suggestStatus = null;
		String firstName = null;
		String lastName = null;
		String adminComments = null;
		String strSuggestions= null;
		//Added for CR-121 by vb106565 starts here
		String suggestID=null;
		//Added for CR-121 by vb106565 ends here
		
		try {
						
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			SuggestVO objSuggestVO = new SuggestVO();
			
			  if (objSuggestForm.getSuggestStatusCode() != 0) {
				objSuggestVO.setSuggestStatusCode(objSuggestForm.getSuggestStatusCode());
				}
				else if (objSuggestForm.getSuggestStatusCode() == 0) {
				objSuggestVO.setSuggestStatusCode(0) ;
				}
				//  LogUtil.logMessage("suggestStatus:" +suggestStatus); 
							
			
			if (objSuggestForm.getSuggestStatusCode() == 0) {
				suggestStatus = "All";
			} else if (objSuggestForm.getSuggestStatusCode() ==1){
				suggestStatus = "Pending";
			} else if (objSuggestForm.getSuggestStatusCode() ==2){
				suggestStatus = "Accepted";
			} else if (objSuggestForm.getSuggestStatusCode() ==3){
				suggestStatus = "Rejected";
			} else if (objSuggestForm.getSuggestStatusCode() ==4){
				suggestStatus = "Completed";
			}
			
			
			HSSFWorkbook workBook = new HSSFWorkbook();
			HSSFSheet sheet = workBook.createSheet(suggestStatus+"_"+"Suggestion(s)");
					
			HSSFCellStyle cellSuggestionStyle = workBook.createCellStyle();
			
			HSSFFont secFont = workBook.createFont();
			secFont.setFontName(HSSFFont.FONT_ARIAL);
			secFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			secFont.setColor(HSSFColor.BLACK.index);		
			secFont.setFontHeightInPoints((short) 13);
			
			cellSuggestionStyle.setFont(secFont);
	        cellSuggestionStyle.setFillForegroundColor(HSSFColor.WHITE.index);
	        cellSuggestionStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);			
			
			HSSFCellStyle cellOtherStyle = workBook.createCellStyle();
			
			HSSFFont subSecFont = workBook.createFont();			
			subSecFont.setFontName(HSSFFont.FONT_ARIAL);
			subSecFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			subSecFont.setColor(HSSFColor.BLACK.index);		
			subSecFont.setFontHeightInPoints((short) 11);
			
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
			
			Iterator listIteratorSuggestion = null;	 
			HSSFRow row = sheet.createRow(rowCount);
			
			LogUtil.logMessage("SuggestStatusCode : " + objSuggestVO.getSuggestStatusCode());
			
			objSuggestVO.setUserID(objLoginVo.getUserID());
			
			SuggestBI objSuggestBI = ServiceFactory.getSuggestBO();
			
			arlSuggestions = objSuggestBI.fetchSuggestions(objSuggestVO);
			
			LogUtil.logMessage("arlSuggestions.size(): " + arlSuggestions.size());
			
			if (arlSuggestions.size() != 0) {
					createHeadingForSuggestion(workBook,row,sheet,suggestStatus);//Updated for CR-118 QA-Fix
					listIteratorSuggestion = arlSuggestions.iterator();
					
				while (listIteratorSuggestion.hasNext()) {
					
					objSuggestVO = new SuggestVO();
					objSuggestVO = (SuggestVO) listIteratorSuggestion.next();
			
					rowCount++;
					intPos=0;
					row = sheet.createRow(rowCount);
						
						if (objSuggestVO.getFirstName() != null) {
							firstName = objSuggestVO.getFirstName();
						} else {
							firstName = "";
						}
						
						if (objSuggestVO.getLastName() != null) {
							lastName = objSuggestVO.getLastName();
						} else {
							lastName = "";
						}
						
						if (objSuggestVO.getAdminComments() != null) {
							 adminComments = objSuggestVO.getAdminComments().replaceAll("\\n|\\r", " ");
							 } else {
							 adminComments = "";
							 }
						
						//Added for CR-121 by vb106565 starts here	 
						createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_NUMERIC, 0,objSuggestVO.getSuggestId());
						//Added for CR-121 by vb106565 ends here	
						createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 1,new HSSFRichTextString(firstName.trim()+" "+lastName.trim()));						
						createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 2,new HSSFRichTextString(objSuggestVO.getScreenName()));
						
						if(objSuggestVO.getSuggestions()!=null && objSuggestVO.getSuggestions()!=""){
							strSuggestions = getRefinedSuggestions(objSuggestVO.getSuggestions());
						}else{
							strSuggestions = objSuggestVO.getSuggestions();
						}
						strSuggestions = strSuggestions.trim();
						
						HSSFRichTextString richTextSuggestions = new HSSFRichTextString(strSuggestions); 
						createSuggestionsCell(row,cellClauseStyle ,HSSFCell.CELL_TYPE_STRING, 3,richTextSuggestions);//2nd column
						
						//Added for CR-118 QA-Fix
						if(suggestStatus == "All"){
							if (objSuggestVO.getSuggestStatusCode() == 0) {
								createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 4,new HSSFRichTextString("All"));
							} else if (objSuggestVO.getSuggestStatusCode() ==1){
								createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 4,new HSSFRichTextString("Pending"));
							} else if (objSuggestVO.getSuggestStatusCode() ==2){
								createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 4,new HSSFRichTextString("Accepted"));
							} else if (objSuggestVO.getSuggestStatusCode() ==3){
								createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 4,new HSSFRichTextString("Rejected"));
							} else if (objSuggestVO.getSuggestStatusCode() ==4){
								createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 4,new HSSFRichTextString("Completed"));
							}
							
							if (objSuggestVO.getImgSeqNo() == 0 )
								createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 5,new HSSFRichTextString("N"));//4th column
							else
								createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 5,new HSSFRichTextString("Y"));//4th column
							
								createCell(row,cellClauseStyle ,HSSFCell.CELL_TYPE_STRING, 6,new HSSFRichTextString(adminComments));//5th column
						}else{
							if (objSuggestVO.getImgSeqNo() == 0 )
								createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 4,new HSSFRichTextString("N"));//3rd column
							else
								createCell(row,cellOtherStyle ,HSSFCell.CELL_TYPE_STRING, 4,new HSSFRichTextString("Y"));//3rd column
							
								createCell(row,cellClauseStyle ,HSSFCell.CELL_TYPE_STRING, 5,new HSSFRichTextString(adminComments));//5th column
						}
						//Added for CR-118 QA-Fix Ends Here
											
						
						   
					
				}
			}
			
			    objHttpServletResponse.setContentType("application/vnd.ms-excel");
				objHttpServletResponse.setHeader("Content-disposition", "attachment; filename= "+suggestStatus+"_"+"Suggestion(s).xls");
				OutputStream out = null;
				out = objHttpServletResponse.getOutputStream();
				workBook.write(out);			
				
		} catch (Exception objEx) {
			objEx.printStackTrace();
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		LogUtil.logMessage("strForward :" + strForward);
		if (strForward.equalsIgnoreCase(ApplicationConstants.FAILURE))
			return objActionMapping.findForward(strForward);
		else
			return null;
	}
	/***************************************************************************
	 * This method is used to Create the Heading for  Suggestion
	 * 
	 * @author Mahindra Satyam Ltd
	 * @version 1.0
	 * @param HSSFWorkbook, HSSFRow, HSSFSheet
	 * @return void
	 * @throws Exception
	 **************************************************************************/

	
	public void createHeadingForSuggestion(HSSFWorkbook workBook,HSSFRow row,HSSFSheet sheet,String suggestStatus) 
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
		//row = sheet.createRow(rowCount);
		//Updated for CR-118 QA-Fix
		if(suggestStatus == "All"){
			final String[] columnHeadings = { "Unique ID","User Name", "Screen Name", "Suggestion Description", "Suggestion State","Attachments (Y/N)", "Administrator Comments"};
			final int[] columnWidth = {3500,6300,7700,155000,6300,5200,9000};
			for (int intPos=0;intPos < 7; intPos++)
				{
				 createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,intPos,new HSSFRichTextString(columnHeadings[intPos]));
					 sheet.setColumnWidth(intPos,columnWidth[intPos]);
				 }
		}else{
			final String[] columnHeadings = {"Unique ID", "User Name", "Screen Name", "Suggestion Description", "Attachments (Y/N)", "Administrator Comments"};
			final int[] columnWidth = {3500,6300,7700,155000,5200,9000};
			for (int intPos=0;intPos < 6; intPos++)
				{
				 createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,intPos,new HSSFRichTextString(columnHeadings[intPos]));
					 sheet.setColumnWidth(intPos,columnWidth[intPos]);
				 }
			
		}
		//Updated for CR-118 QA-Fix Ends Here
	}
	
	/***************************************************************************
	 * This method is used to create the cell with int value
	 * 
	 * @author Mahindra Satyam Ltd
	 * @version 1.0
	 * @param HSSFSheet, HSSFRow, HSSFCellStyle, int, int, String
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
	
	protected HSSFCell createSuggestionsCell(HSSFRow row, HSSFCellStyle headerStyle,
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
	
	public String getRefinedSuggestions(String strSuggestions) throws Exception
	{
		String strRefinedSuggestions = "";
		Paragraph paraClauseDesc = new Paragraph();
	    paraClauseDesc.setKeepTogether(true);
		if (!strSuggestions.startsWith("<p>"))
			strSuggestions = strSuggestions.replaceAll("\n","<br/>");
		strRefinedSuggestions=ApplicationUtil.strConvertToHTMLFormat(strSuggestions);
		StyleSheet styles = new StyleSheet();
	    styles.loadTagStyle("p","size","12px");
	    styles.loadTagStyle("p","face","Times");
	    styles.loadTagStyle("strong","font-weight", "bold"); 
	    styles.loadTagStyle("em","font-style", "italic");
		ArrayList p = HTMLWorker.parseToList(new FileReader(strRefinedSuggestions), styles);
	    for (int k1 = 0; k1 < p.size(); ++k1) 
			{
	    	paraClauseDesc.add((Element) p.get(k1));
		    }
	    strRefinedSuggestions = paraClauseDesc.getContent();
		LogUtil.logMessage("returning from strhtmlclauseconvert :");
		return strRefinedSuggestions;
	}
	
	
	/***************************************************************************
	 * This Method is used to update the Suggestion Details
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward updateSuggestion(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering SuggestAction.updateSuggestion");
		String strForward = ApplicationConstants.SUCCESS;
		int intStatus = 0;
		ArrayList arlSuggestions = new ArrayList();
		ArrayList arlSuggStatus = new ArrayList();;
		ArrayList arlUsersList = new ArrayList();
		SuggestForm objSuggestForm = (SuggestForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			SuggestVO objSuggestVO = new SuggestVO();
									
			objSuggestVO.setSuggestId(objSuggestForm.getSuggestId());		
			objSuggestVO.setSuggestStatusCode(objSuggestForm.getUpdateStatusCode());
			objSuggestVO.setSuggestions(objSuggestForm.getSuggestions());
			objSuggestVO.setAdminComments(objSuggestForm.getHdnAdminComments());
			objSuggestVO.setUserID(objLoginVo.getUserID());
			
			SuggestBI objSuggestBI = ServiceFactory.getSuggestBO();
			
			intStatus = objSuggestBI.updateSuggestion(objSuggestVO);
			
			if (intStatus == 0) 
				objSuggestForm.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			else	
				objSuggestForm.setMessageID("" + intStatus);
			
			objSuggestVO.setSuggestStatusCode(objSuggestForm.getSuggestStatusCode());
			//Added for CR-127
			//LogUtil.logMessage("objSuggestForm.getSelectedInitiator() : " + objSuggestForm.getSelectedInitiator());
			if(objSuggestForm.getSelectedInitiator() == null){
				objSuggestForm.setSelectedInitiator("0");
			}else{
				objSuggestVO.setSelectedInitiator(objSuggestForm.getSelectedInitiator());
			}
			
			arlSuggestions = objSuggestBI.fetchSuggestions(objSuggestVO);
			
			if (arlSuggestions != null || arlSuggestions.size() != 0) {
				objSuggestForm.setSuggestionList(arlSuggestions);
			}
			
			arlSuggStatus = objSuggestBI.fetchSuggestionStatus(objSuggestVO);
			
			if (arlSuggStatus != null || arlSuggStatus.size() != 0) {
				objSuggestForm.setSuggestionStatusList(arlSuggStatus);
			}
			
			objSuggestForm.setSuggestStatusCode(objSuggestVO.getSuggestStatusCode());
			//Added for CR-127
			UserVO objUserVO = new UserVO();
			
			objUserVO.setUserID(objLoginVo.getUserID());
			objUserVO.setOrderbyFlag(0);
			UserMaintBI objUserMaintBO = ServiceFactory.getUserMaintBO();
			arlUsersList = objUserMaintBO.fetchUsers(objUserVO);
			objSuggestForm.setInitiatorList(arlUsersList);
			//Added for CR-127 Ends here
			
		} catch (Exception objExp) {
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
	}
	
	/***************************************************************************
	 * This Method is used to delete the attachment tied to Suggestion
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward deleteAttachment(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering SuggestAction.deleteAttachment");
		String strForward = ApplicationConstants.SUCCESS;
		int intStatus = 0;
		ArrayList arlSuggestions = new ArrayList();
		ArrayList arlSuggStatus = new ArrayList();;
		SuggestForm objSuggestForm = (SuggestForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			SuggestVO objSuggestVO = new SuggestVO();
									
			objSuggestVO.setSuggestId(objSuggestForm.getSuggestId());		
			objSuggestVO.setImgSeqNo(objSuggestForm.getImgSeqNo());
			objSuggestVO.setUserID(objLoginVo.getUserID());
			
			SuggestBI objSuggestBI = ServiceFactory.getSuggestBO();
			
			intStatus = objSuggestBI.deleteAttachment(objSuggestVO);
			
			if (intStatus == 0) 
				objSuggestForm.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			else	
				objSuggestForm.setMessageID("" + intStatus);
			
			objSuggestVO.setSuggestStatusCode(objSuggestForm.getSuggestStatusCode());
			
			arlSuggestions = objSuggestBI.fetchSuggestions(objSuggestVO);
			
			if (arlSuggestions != null || arlSuggestions.size() != 0) {
				objSuggestForm.setSuggestionList(arlSuggestions);
			}
			
			arlSuggStatus = objSuggestBI.fetchSuggestionStatus(objSuggestVO);
			
			if (arlSuggStatus != null || arlSuggStatus.size() != 0) {
				objSuggestForm.setSuggestionStatusList(arlSuggStatus);
			}
			
			objSuggestForm.setSuggestStatusCode(objSuggestVO.getSuggestStatusCode());
			
		} catch (Exception objExp) {
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
	}
}
