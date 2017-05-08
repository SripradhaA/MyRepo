package com.EMD.LSDB.action.History;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
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
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.EMD.LSDB.action.common.EMDAction;
import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.HistoryEdlPopUpBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.dao.History.HistoryEdlPopUpDAO;
import com.EMD.LSDB.form.History.HistoryEdlPopUpForm;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.SubSectionVO;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This Action class has the business methods for the History Edl
 *          Pop up
 ******************************************************************************/
public class HistoryEdlAction extends EMDAction {
	
	/***************************************************************************
	 * This method is for loading the History Edl Pop up
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws BusinessException
	 **************************************************************************/
	public ActionForward fetchEdlNo(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		LogUtil.logMessage("Inside the HistoryEdlAction:fetchEdlNo");
		HistoryEdlPopUpForm objHistoryEdlPopUpForm = (HistoryEdlPopUpForm) objActionForm;
		ArrayList arlHistoryList = new ArrayList();
		String strForwardKey = ApplicationConstants.SUCCESS;
		String strUserID = "";
		String strOrderNo =null;
		String strSpecStatus =null;
		String strCustomerName =null;
		String strModelName =null;
		String dataLocationType=null;
		String fileName=null;
		try {
		if((String)objHttpServletRequest.getParameter("dataLocationType")!=null && (String)objHttpServletRequest.getParameter("dataLocationType")!="")
		{
			dataLocationType=(String)objHttpServletRequest.getParameter("dataLocationType");
		}else{
			dataLocationType="S";
		}
		LogUtil.logMessage("*******Inside the HistoryEdlAction:fetchEdlNo+dataLocationType==*********"+dataLocationType);
		ClauseVO objClauseVO = new ClauseVO();
		int intOrderKey=0;
		if(((String)objHttpServletRequest.getParameter("excelFormat")!=null) && ((String)objHttpServletRequest.getParameter("excelFormat")!=""))
			{
				if(objHttpServletRequest.getParameter("excelFormat").equalsIgnoreCase("Y"))
				{
					strForwardKey = ApplicationConstants.SUCCESS_EXCEL;
					LogUtil.logMessage("*******Inside +excelFormat="+(String)objHttpServletRequest.getParameter("excelFormat"));
				}
			}
	
	 if(dataLocationType.equalsIgnoreCase("S")&& dataLocationType!=null)
		{		objClauseVO.setDataLocationType(dataLocationType);
		}else 
		{		objClauseVO.setDataLocationType("W");
		}
		
					
		
		if((objHttpServletRequest.getParameter(ApplicationConstants.ORDER_NUMBER))!=null ||(objHttpServletRequest.getParameter(ApplicationConstants.ORDER_NUMBER))!="")
		{		
		strOrderNo = objHttpServletRequest
		.getParameter(ApplicationConstants.ORDER_NUMBER);
		}
		else{
			strOrderNo=objHistoryEdlPopUpForm.getOrderNo();
			
		}
		
		if((objHttpServletRequest
		.getParameter(ApplicationConstants.SPEC_STATUS))!=null ||( objHttpServletRequest
		.getParameter(ApplicationConstants.SPEC_STATUS))!="")
		{
		strSpecStatus = objHttpServletRequest
		.getParameter(ApplicationConstants.SPEC_STATUS);
		}else {
			strSpecStatus =objHistoryEdlPopUpForm.getSpecStatus();
		}
		if((objHttpServletRequest.getParameter(ApplicationConstants.CUSTOMER_NAME))!=null || (objHttpServletRequest
		.getParameter(ApplicationConstants.CUSTOMER_NAME))!="")
		{
			strCustomerName = objHttpServletRequest
			.getParameter(ApplicationConstants.CUSTOMER_NAME);
		}else{
		 strCustomerName = objHistoryEdlPopUpForm.getCustomerName();
		}
		if((Integer.parseInt(objHttpServletRequest
				.getParameter(ApplicationConstants.ORDER_KEY)))!=0)
						{
			intOrderKey = Integer.parseInt(objHttpServletRequest
					.getParameter(ApplicationConstants.ORDER_KEY));
						}else {
							intOrderKey = objHistoryEdlPopUpForm.getOrderKey();
						}
		LogUtil.logMessage("*******Inside the HistoryEdlAction:fetchEdlNo+intOrderKey==*********"+intOrderKey);
		
		if((objHttpServletRequest.getParameter(ApplicationConstants.MODEL_NAME))!=null || (objHttpServletRequest
				.getParameter(ApplicationConstants.MODEL_NAME))!="")
		{
		 strModelName = objHttpServletRequest
		.getParameter(ApplicationConstants.MODEL_NAME);
		}else
		{
			strModelName =objHistoryEdlPopUpForm.getModelName();
			
		}
		
		
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			objClauseVO.setOrderKey(intOrderKey);
			objClauseVO.setUserID(objLoginVo.getUserID());
			HistoryEdlPopUpBI objHistoryEdlPopUpBO = ServiceFactory
			.getHistoryEdlPopUpBO();
			arlHistoryList = objHistoryEdlPopUpBO.fetchEdlNo(objClauseVO);
			
			//CR_91 adding Filename to the attachment
			if(strForwardKey.equalsIgnoreCase(ApplicationConstants.SUCCESS_EXCEL))
			{
								
				objHttpServletResponse.setContentType("application/vnd.ms-excel");
				//Modified File Name for CR_106
				fileName=strOrderNo+"_"+strSpecStatus+"_"+"EDL_RefEDL_List.xls";
				objHttpServletResponse.setHeader("Content-disposition",
				"attachment;filename="+fileName);
				
				
			}
			
		
			LogUtil.logMessage("ForwardKey" + strForwardKey);
			if (arlHistoryList.size() != 0) {
				
				objHistoryEdlPopUpForm.setOrderNo(strOrderNo);
				objHistoryEdlPopUpForm.setSpecStatus(strSpecStatus);
				objHistoryEdlPopUpForm.setCustomerName(strCustomerName);
				objHistoryEdlPopUpForm.setOrderKey(intOrderKey);
				objHistoryEdlPopUpForm.setModelName(strModelName);
				objHistoryEdlPopUpForm.setClauseGroup(arlHistoryList);
				return objActionMapping.findForward(strForwardKey);
			} else {
				objHistoryEdlPopUpForm.setOrderNo(strOrderNo);
				objHistoryEdlPopUpForm.setSpecStatus(strSpecStatus);
				objHistoryEdlPopUpForm.setCustomerName(strCustomerName);
				objHistoryEdlPopUpForm.setOrderKey(intOrderKey);
				objHistoryEdlPopUpForm.setModelName(strModelName);
				objHistoryEdlPopUpForm
				.setMessageID(ApplicationConstants.NO_RECORDS_FOUND);
					
				
				return objActionMapping.findForward(strForwardKey);
				
			}
			
			
		} catch (Exception objExp) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
			
		}
		LogUtil.logMessage("*******HistoryEdlAction:fetchEdlNo END of Loop*********"+strForwardKey);
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***************************************************************************
	 * This method is for loading the History Edl Pop up in Excel
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws BusinessException
	 **************************************************************************/
	public ActionForward fetchEdlNoExcel(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		LogUtil.logMessage("Inside the HistoryEdlAction:fetchEdlNoExcel");
		ArrayList arlHistoryList = new ArrayList();
		String strForwardKey = ApplicationConstants.SUCCESS;
		String strUserID = "";
		
		ClauseVO objjClauseVO = new ClauseVO();
		StringBuffer strEdlContent = new StringBuffer();
		
		String strOrderNo = objHttpServletRequest
		.getParameter(ApplicationConstants.ORDER_NUMBER);
		
		String strSpecStatus = objHttpServletRequest
		.getParameter(ApplicationConstants.SPEC_STATUS);
		
		String strCustomerName = objHttpServletRequest
		.getParameter(ApplicationConstants.CUSTOMER_NAME);
		
		int intOrderKey = Integer.parseInt(objHttpServletRequest
				.getParameter(ApplicationConstants.ORDER_KEY));
		
		String strModelName = objHttpServletRequest
		.getParameter(ApplicationConstants.MODEL_NAME);
		
		
		
		ClauseVO objClauseVO = new ClauseVO();
		try {
			HistoryEdlPopUpForm objHistoryEdlPopUpForm = (HistoryEdlPopUpForm) objActionForm;
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			objClauseVO.setOrderKey(intOrderKey);
			objClauseVO.setUserID(objLoginVo.getUserID());
			HistoryEdlPopUpBI objHistoryEdlPopUpBO = ServiceFactory
			.getHistoryEdlPopUpBO();
			arlHistoryList = objHistoryEdlPopUpBO.fetchEdlNo(objClauseVO);
			
			if (arlHistoryList.size() != 0) {
				
				objHistoryEdlPopUpForm.setOrderNo(strOrderNo);
				objHistoryEdlPopUpForm.setSpecStatus(strSpecStatus);
				objHistoryEdlPopUpForm.setCustomerName(strCustomerName);
				objHistoryEdlPopUpForm.setOrderKey(intOrderKey);
				objHistoryEdlPopUpForm.setModelName(strModelName);
				objHistoryEdlPopUpForm.setClauseGroup(arlHistoryList);
				
			} else {
				objHistoryEdlPopUpForm.setOrderNo(strOrderNo);
				objHistoryEdlPopUpForm.setSpecStatus(strSpecStatus);
				objHistoryEdlPopUpForm.setCustomerName(strCustomerName);
				objHistoryEdlPopUpForm.setOrderKey(intOrderKey);
				objHistoryEdlPopUpForm.setModelName(strModelName);
				objHistoryEdlPopUpForm
				.setMessageID(ApplicationConstants.NO_RECORDS_FOUND);
				
			}
			
			if (arlHistoryList != null) {
				
				for (int i = 0; i < arlHistoryList.size(); i++) {
					
					objjClauseVO = (ClauseVO) arlHistoryList.get(i);
					
					String s = objjClauseVO.getSecCode();
					
					if (s != null) {
						
						strEdlContent.append("Section");
						strEdlContent.append(',');
						strEdlContent.append(s);
						
						strEdlContent.append(System
								.getProperty("line.separator"));
						
						for (int cnt = 0; cnt < objjClauseVO.getEdlNO().size(); cnt++) {
							
							String edl = objjClauseVO.getEdlNO().get(cnt)
							.toString();
							
							if (edl != null) {
								
								strEdlContent.append("EDL");
								strEdlContent.append(',');
								strEdlContent.append(edl);
								
							}
							
							strEdlContent.append(System
									.getProperty("line.separator"));
							
						}
						
					}
					
				}
				
			}
			
			byte buff[] = (strEdlContent.toString()).getBytes();
			LogUtil.logMessage("EDL List" + strEdlContent.toString());
			objHttpServletResponse.setContentType("text/csv");
			//Modified File Name for CR_106
			objHttpServletResponse.setHeader("Content-disposition",
			"attachment;filename=Edl_RefEdl_List.csv");
			
			ServletOutputStream os = objHttpServletResponse.getOutputStream();
			
			os.write(buff);
			os.flush();
			os.close();
			
		} catch (Exception objExp) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
			
		}
		return null;
	}
	
	//Added For CR-118
	/***************************************************************************
	 * This method is for loading the History Edl Pop up
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws BusinessException
	 **************************************************************************/
	public ActionForward fetchEDLNumberforCSV(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		LogUtil.logMessage("Inside the HistoryEdlAction:fetchEDLNumberforCSV");
		HistoryEdlPopUpForm objHistoryEdlPopUpForm = (HistoryEdlPopUpForm) objActionForm;
		ArrayList arlHistoryCSVList = new ArrayList();
		String strForwardKey = ApplicationConstants.SUCCESS;
		String strUserID = "";
		String strOrderNo =null;
		String strSpecStatus =null;
		String strCustomerName =null;
		String strModelName =null;
		String dataLocationType=null;
		String fileName=null;
		String strClauseDesc= null;
		
		
		if((String)objHttpServletRequest.getParameter("dataLocationType")!=null && (String)objHttpServletRequest.getParameter("dataLocationType")!="")
		{
			dataLocationType=(String)objHttpServletRequest.getParameter("dataLocationType");
		}else{
			dataLocationType="S";
		}
		//LogUtil.logMessage("*******Inside the HistoryEdlAction:fetchEDLNumberforCSV+dataLocationType==*********"+dataLocationType);
		
		ClauseVO objClauseVO = new ClauseVO();
		int intOrderKey=0;
		if(dataLocationType.equalsIgnoreCase("S")&& dataLocationType!=null)
			{	
				objClauseVO.setDataLocationType(dataLocationType);
			}else 
			{
				objClauseVO.setDataLocationType("W");
			}
		
		if((objHttpServletRequest.getParameter(ApplicationConstants.ORDER_NUMBER))!=null ||(objHttpServletRequest.getParameter(ApplicationConstants.ORDER_NUMBER))!="")
		{		
			strOrderNo = objHttpServletRequest.getParameter(ApplicationConstants.ORDER_NUMBER);
		}
		else{
			strOrderNo=objHistoryEdlPopUpForm.getOrderNo();
			
		}
		
		if((objHttpServletRequest.getParameter(ApplicationConstants.SPEC_STATUS))!=null 
			||( objHttpServletRequest.getParameter(ApplicationConstants.SPEC_STATUS))!="")
		{
			strSpecStatus = objHttpServletRequest.getParameter(ApplicationConstants.SPEC_STATUS);
		}else {
			strSpecStatus =objHistoryEdlPopUpForm.getSpecStatus();
		}
		if((objHttpServletRequest.getParameter(ApplicationConstants.CUSTOMER_NAME))!=null 
			|| (objHttpServletRequest.getParameter(ApplicationConstants.CUSTOMER_NAME))!="")
		{
			strCustomerName = objHttpServletRequest.getParameter(ApplicationConstants.CUSTOMER_NAME);
		}else{
			strCustomerName = objHistoryEdlPopUpForm.getCustomerName();
		}
		if((Integer.parseInt(objHttpServletRequest.getParameter(ApplicationConstants.ORDER_KEY)))!=0)
		{
			intOrderKey = Integer.parseInt(objHttpServletRequest.getParameter(ApplicationConstants.ORDER_KEY));
		}else{
			intOrderKey = objHistoryEdlPopUpForm.getOrderKey();
		}
		//LogUtil.logMessage("*******Inside the HistoryEdlAction:fnViewSAPEDLNumbertoCSV+intOrderKey==*********"+intOrderKey);
		
		if((objHttpServletRequest.getParameter(ApplicationConstants.MODEL_NAME))!=null 
			|| (objHttpServletRequest.getParameter(ApplicationConstants.MODEL_NAME))!="")
		{
			strModelName = objHttpServletRequest.getParameter(ApplicationConstants.MODEL_NAME);
		}else
		{
			strModelName =objHistoryEdlPopUpForm.getModelName();
		}
		
			HttpSession objSession = objHttpServletRequest.getSession(false);
			
	 try{
		 	LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			objClauseVO.setOrderKey(intOrderKey);
			objClauseVO.setUserID(objLoginVo.getUserID());
			
			arlHistoryCSVList = HistoryEdlPopUpDAO.fetchEDLNumberforCSV(objClauseVO);
			LogUtil.logMessage("arlHistoryCSVList.size:"+ arlHistoryCSVList.size());
			
			StringBuffer strEdlContent = new StringBuffer();
			
			if (arlHistoryCSVList != null) {
				
				for (int i = 0; i < arlHistoryCSVList.size(); i++) {
					
					objClauseVO = (ClauseVO) arlHistoryCSVList.get(i);
					if(i == 0){
					strEdlContent.append("Clause Number");
					strEdlContent.append(',');
					strEdlContent.append("Component / Clause Description");
					strEdlContent.append(',');
					strEdlContent.append("EDL Number1");
					strEdlContent.append(',');
					strEdlContent.append("EDL Number2");
					strEdlContent.append(',');
					strEdlContent.append("EDL Number3");
					}
					strEdlContent.append(System
							.getProperty("line.separator"));
					strEdlContent.append(objClauseVO.getClauseNum());
					strEdlContent.append(',');
					
					if(objClauseVO.getComponentName() == null 
					 || objClauseVO.getComponentName().equalsIgnoreCase("")){
						if(objClauseVO.getClauseDesc()!=null && objClauseVO.getClauseDesc()!=""){
							strClauseDesc = getRefinedClauseDesc(objClauseVO.getClauseDesc());
						}else{
							strClauseDesc = objClauseVO.getClauseDesc();
						}
						if (strClauseDesc.indexOf("\"") != -1 || strClauseDesc.indexOf(",") != -1) {
				    		strClauseDesc = strClauseDesc.replaceAll("\"", "\"\"");
				    	}
						strClauseDesc = strClauseDesc.trim();
				    	strEdlContent.append('"');
						strEdlContent.append(strClauseDesc);
						strEdlContent.append('"');
						//LogUtil.logMessage("strClauseDesc:" +strClauseDesc);
					}else{
						strEdlContent.append('"');
						strEdlContent.append(objClauseVO.getComponentName());
						strEdlContent.append('"');
					}
					
						for (int cnt = 0; cnt < objClauseVO.getEdlNO().size(); cnt++) {
							
							String edl = objClauseVO.getEdlNO().get(cnt)
							.toString();
							
							if (edl != null) {
								
								strEdlContent.append(',');
								strEdlContent.append(edl);
							}
						}
				}
				
			}
			
			byte buff[] = (strEdlContent.toString()).getBytes();
			LogUtil.logMessage("EDL List" + strEdlContent.toString());
			objHttpServletResponse.setContentType("text/csv");
			objHttpServletResponse.setHeader("Content-disposition", "attachment; filename= "+strOrderNo+"_"+"Edl_SAP_List.csv");
			ServletOutputStream os = objHttpServletResponse.getOutputStream();
			
			os.write(buff);
			os.flush();
			os.close();
			
		} catch (Exception objExp) {
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
		
		final String[] columnHeadings = { "Clause Number","Component / Clause Description","EDL Number 1",
				 						  "EDL Number 2", "EDL Number 3"};
		final int[] columnWidth = {3500, 9500, 2700 , 2700, 2700};
		for (int intPos=0;intPos < 5; intPos++)
			{
				 createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,intPos,new HSSFRichTextString(columnHeadings[intPos]));
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
		LogUtil.logMessage("returning from strhtmlclauseconvert :");
		return strRefinedClauseDesc;
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
	
	public static void echoAsCSV(HSSFSheet sheet) {    
	    HSSFRow row = null;      
	  for (int i = 0; i < sheet.getLastRowNum(); i++) {    
	        row = sheet.getRow(i);  
	     for (int j = 0; j < row.getLastCellNum(); j++) {  
	         System.out.print("\"" + row.getCell(j) + "\";");
	            } 
	         System.out.println();    
	    } 
	 }
	
	
	//Added For CR-118 Ends here
	
}
