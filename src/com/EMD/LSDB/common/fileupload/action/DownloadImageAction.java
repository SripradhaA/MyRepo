/**
 * 
 */
package com.EMD.LSDB.common.fileupload.action;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//Added for CR - 135
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.FileUploadBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.vo.common.FileVO;
//Added for CR - 135
import com.EMD.LSDB.vo.common.LoginVO;
import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

/**
 * @author PS57222
 *
 */
public class DownloadImageAction extends DispatchAction {
	
	/*******************************************************************************************
	 *  * * *		This Method is used to DownLoad the images from the LSDB170_IMAGES table
	 *  			ImageSeqNo is passed from jsp it takes that as key and retrive the images 
	 *  			from the table.
	 *  
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return     	ActionForward  		   
	 * @throws     	EMDException
	
	 ******************************************************************************************/
	
	public ActionForward downloadImage(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException,
			FileNotFoundException, IOException {
		
		LogUtil
		.logMessage("Enters into DownloadImageAction:downloadImage method");

		//Added for CR-135 PDF Attachment starts here
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
		LogUtil.logMessage("objLoginVo " + objLoginVo.getUserID());
		//Ends here for CR - 135
		
		FileVO objFileVO = new FileVO();
		byte[] arrBuff = null;
		String strRequestUrl = objHttpServletRequest.getQueryString();
		LogUtil.logMessage("strRequestUrl" + strRequestUrl);
		String strContentType = null;
		//Added for CR - 135
		String strForwardKey = null;
		
		int intLength= 0;
		String strImageSeqNo = objHttpServletRequest.getParameter("ImageSeqNo");
		
		if (strImageSeqNo.length() > 0)
			strImageSeqNo = strImageSeqNo.replaceAll("'", "");
		
		int intImageSeqNo = Integer.parseInt(strImageSeqNo);
		LogUtil.logMessage("ImageSeqNo in DownloadImageAction:downloadImage"
				+ intImageSeqNo);
		
		if (intImageSeqNo == 0) {
			return null;
		}
		
		try {
			objFileVO.setImageSeqNo(intImageSeqNo);
			LogUtil
			.logMessage("ImageSeqNo in DownloadImageAction:downloadImage:objFileVO:"
					+ objFileVO.getImageSeqNo());
			FileUploadBI objFileUploadBI = ServiceFactory.getFileUploadBO();
			
			FileVO objResultobjFileVO = objFileUploadBI
			.downloadImage(objFileVO);
			strContentType = objResultobjFileVO.getContentType();
			
			//Added for CR - 135 attachments from here
			if (strContentType != null)
			{
				ServletOutputStream objServletOutputStream;
				InputStream objInputStream;
				objServletOutputStream = objHttpServletResponse.getOutputStream();
			//Ends here	
				objInputStream = objResultobjFileVO.getFileStream();
				int intLen = objResultobjFileVO.getFileLength();
				objServletOutputStream = objHttpServletResponse.getOutputStream();
			
				arrBuff = new byte[32 * 1024];
				//Added for CR_91 to give user the option to save image
				String strFlag = "N";
				
				objHttpServletResponse.setContentLength(intLen);
                				
				if (objHttpServletRequest.getParameter("download") != null)	{
					strFlag = objHttpServletRequest.getParameter("download");
					}
				if (strFlag.equalsIgnoreCase("Y")) {
					String strImageName = "";
					if (objHttpServletRequest.getParameter("ImageName") != null)	{
						strImageName = objHttpServletRequest.getParameter("ImageName");
						//Added for CR - 135
						LogUtil.logMessage("str Image Name with extension" + strImageName);
						}
					LogUtil.logMessage("strContentType" + strContentType);
					if(strImageName.lastIndexOf('.')!=-1){
						strImageName =  strImageName.substring(0,strImageName.lastIndexOf('.'));
					}
					LogUtil.logMessage("str Image Name with out extension" + strImageName);
					
					
					if (strContentType.equalsIgnoreCase("image/pjpeg"))	{
						objHttpServletResponse.setContentType("image/jpeg");
						objHttpServletResponse.setHeader("Content-Disposition",
							"attachment; filename=" + strImageName + ".jpg");
					}
					else if(strContentType.equalsIgnoreCase("image/x-png")){
						objHttpServletResponse.setContentType("image/tiff");
						objHttpServletResponse.setHeader("Content-Disposition",
								"attachment; filename=" +strImageName + ".tif");
					}
					else if (strContentType.startsWith("image"))	{
						objHttpServletResponse.setContentType(strContentType);
						objHttpServletResponse.setHeader("Content-Disposition",
								"attachment; filename=" + strImageName + "." + strContentType.substring(6));
					}/* Added  for CR_97 */
					else if (strContentType.equalsIgnoreCase("application/PDF"))	{
						objHttpServletResponse.setContentType(strContentType);
						objHttpServletResponse.setHeader("Content-Disposition",
								"attachment; filename=" + strImageName+ ".PDF");
					}/*CR_97 Ends here */
					//Added for CR-135 starts here
					else if (strContentType.equalsIgnoreCase("application/msword"))	{
						LogUtil.logMessage("Inside else if condition of word" + strImageName);
						objHttpServletResponse.setContentType(strContentType);
						objHttpServletResponse.setHeader("Content-Disposition",
								"attachment; filename=" + strImageName+ ".doc");
						LogUtil.logMessage("Prints the image name" + strImageName);
					}
					else if (strContentType.equalsIgnoreCase("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))	{
						LogUtil.logMessage("Inside else if condition of word" + strImageName);
						objHttpServletResponse.setContentType(strContentType);
						objHttpServletResponse.setHeader("Content-Disposition",
								"attachment; filename=" + strImageName+ ".docx");
						LogUtil.logMessage("Prints the image name" + strImageName);
					}
					else if (strContentType.equalsIgnoreCase("text/plain"))	{
						objHttpServletResponse.setContentType(strContentType);
						objHttpServletResponse.setHeader("Content-Disposition",
								"attachment; filename=" + strImageName+ ".txt");
					}
					else if (strContentType.equalsIgnoreCase("application/vnd.ms-excel"))	{
						objHttpServletResponse.setContentType(strContentType);
						objHttpServletResponse.setHeader("Content-Disposition",
								"attachment; filename=" + strImageName+ ".xls");
					}
					else if (strContentType.equalsIgnoreCase("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))	{
						objHttpServletResponse.setContentType(strContentType);
						objHttpServletResponse.setHeader("Content-Disposition",
								"attachment; filename=" + strImageName+ ".xlsx");
					}
					else if (strContentType.equalsIgnoreCase("application/vnd.ms-powerpoint"))	{
						objHttpServletResponse.setContentType(strContentType);
						objHttpServletResponse.setHeader("Content-Disposition",
								"attachment; filename=" + strImageName+ ".ppt");
					}
					else if (strContentType.equalsIgnoreCase("application/vnd.openxmlformats-officedocument.presentationml.presentation"))	{
						objHttpServletResponse.setContentType(strContentType);
						objHttpServletResponse.setHeader("Content-Disposition",
								"attachment; filename=" + strImageName+ ".pptx");
					}
					else if (strContentType.equalsIgnoreCase("application/rtf"))	{
						objHttpServletResponse.setContentType(strContentType);
						objHttpServletResponse.setHeader("Content-Disposition",
								"attachment; filename=" + strImageName+ ".rtf");
					}
					else if (strContentType.equalsIgnoreCase("image/gif"))	{
						objHttpServletResponse.setContentType(strContentType);
						objHttpServletResponse.setHeader("Content-Disposition",
								"attachment; filename=" + strImageName+ ".gif");
					}//Added for CR-135 ends here
					else	{
						objHttpServletResponse.setContentType(strContentType);
						objHttpServletResponse.setHeader("Content-Disposition",
								"file; filename=" + strImageName);
					}
					
				}
				else{
					objHttpServletResponse.setContentLength(intLen);
					LogUtil.logMessage("length of content..."+ intLen);
					objHttpServletResponse.setContentType(strContentType);
					
					
				}//ENDs here...
				
				while ((intLength = objInputStream.read(arrBuff)) != -1) {
					objServletOutputStream.write(arrBuff, 0, intLength);
					}
				objServletOutputStream.flush();
				objInputStream.close();
				objServletOutputStream.close();
			} else {//Else part Added for CR - 135 PDF attachments
				
				strForwardKey = ApplicationConstants.FAILURE;
			}
			
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into DownloadImageAction:downloadImage AppException Block");
			objAppExp.printStackTrace();
			strForwardKey = ApplicationConstants.FAILURE;
		    //return objActionMapping.findForward(strForwardKey);//Commented for CR - 135 PDF Attachments
		} catch (Exception objExp) {
			objExp.printStackTrace();
			LogUtil.logMessage("Enters into Exception block for attachments");
			//Added for CR-135 PDF Attachments
			strForwardKey = ApplicationConstants.FAILURE;
			/*ErrorInfo errorInfoObj = new ErrorInfo();
			LogUtil.logMessage("After error info");
			errorInfoObj.setMessage(objExp.getMessage());
			//throw new EMDException(errorInfoObj);//Commented for CR-135 PDF Attachments*/
		}
		//Added for CR - 135 PDF Attachments if part and else part
		LogUtil.logMessage("Leaves From DownloadImageAction:downloadImage Method");
		if (strForwardKey.equalsIgnoreCase(ApplicationConstants.FAILURE))	{
			return objActionMapping.findForward(strForwardKey);
		}
		else
			return null;
	}
	
	public ActionForward downloadPDF(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException,
			FileNotFoundException, IOException {
		
		LogUtil
		.logMessage("Enters into DownloadImageAction:downloadPDF method");
		
		String strForwardKey = ApplicationConstants.SUCCESS;
		FileVO objFileVO = new FileVO();
		byte[] arrBuff = null;
		ServletOutputStream objServletOutputStream;
		InputStream objInputStream;
		objServletOutputStream = objHttpServletResponse.getOutputStream();
		String strRequestUrl = objHttpServletRequest.getQueryString();
		LogUtil.logMessage("strRequestUrl" + strRequestUrl);
		String strContentType = null;
		int intLength = 0;
		int dot =0;
		String strImageSeqNo = objHttpServletRequest.getParameter("ImageSeqNo");
		
		String strImageName = objHttpServletRequest.getParameter("ImageName");
		
		LogUtil.logMessage("image name :"+strImageName);
		
		if (strImageSeqNo.length() > 0)
			strImageSeqNo = strImageSeqNo.replaceAll("'", "");
		
		int intImageSeqNo = Integer.parseInt(strImageSeqNo);
		LogUtil.logMessage("ImageSeqNo in DownloadImageAction:downloadPDF"
				+ intImageSeqNo);
		
		if (intImageSeqNo == 0) {
			return null;
		}
		
		try {
			
			//test
			synchronized (this) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				Document document = new Document(PageSize.A4, 25, 25, 25, 5);
				PdfWriter writer = PdfWriter.getInstance(document, baos);
				
				document.addCreationDate();
				document.open();
				document.newPage();
				
				//test
				objFileVO.setImageSeqNo(intImageSeqNo);
				LogUtil
				.logMessage("ImageSeqNo in DownloadImageAction:downloadPDF:objFileVO:"
						+ objFileVO.getImageSeqNo());
				FileUploadBI objFileUploadBI = ServiceFactory.getFileUploadBO();
				
				FileVO objResultobjFileVO = objFileUploadBI
				.downloadImage(objFileVO);
				strContentType = objResultobjFileVO.getContentType();
				objInputStream = objResultobjFileVO.getFileStream();
				
				PdfReader reader = new PdfReader(objInputStream);
				
				for (int x = 1; x <= reader.getNumberOfPages(); x++) {
					PdfImportedPage page = writer.getImportedPage(reader, x);
					Image image = Image.getInstance(page);
					document.add(image);
				}
				
				document.close();
				/*String userAgent = objHttpServletRequest.getHeader("User-Agent");
				 LogUtil.logMessage("userAgent :"+userAgent);	
				 StringTokenizer stToken = new StringTokenizer(userAgent , ";");
				 boolean version = false;
				 while(stToken.hasMoreTokens()){
				 String strToken = stToken.nextToken(); 
				 strToken = strToken.trim();
				 LogUtil.logMessage("strToken :"+strToken);	
				 if(strToken.equals("MSIE 7.0")){
				 version =  true;
				 break;
				 }
				 }
				 
				 LogUtil.logMessage("version :"+version);*/
				
				objHttpServletResponse.setContentType("application/pdf");
				/* Fxing pre-existing bug */
				if(strImageName.indexOf(".")==-1){
					strImageName+=".PDF";
				}
				/* Fix Ends here */
				//This check is for IE7
				//if(version){
				objHttpServletResponse.setHeader("Content-disposition",
						"attachment;filename="+strImageName.replaceAll(" ","%20"));
				/*}else{
				 objHttpServletResponse.setHeader("Content-disposition",
				 "attachment;filename="+strImageName);
				 }*/
				objHttpServletResponse.setContentLength(baos.size());
				ServletOutputStream out = objHttpServletResponse
				.getOutputStream();
				baos.writeTo(out);
				out.flush();
			}
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into DownloadImageAction:downloadPDF AppException Block");
			objAppExp.printStackTrace();
			strForwardKey = ApplicationConstants.FAILURE;
			return objActionMapping.findForward(strForwardKey);
		} catch (Exception objExp) {
			objExp.printStackTrace();
			ErrorInfo errorInfoObj = new ErrorInfo();
			errorInfoObj.setMessage(objExp.getMessage());
			throw new EMDException(errorInfoObj);//Commented for CR - 135 
			//Added for CR - 135 from here PDF Attachments 
			//strForwardKey = ApplicationConstants.FAILURE;
			//return objActionMapping.findForward(strForwardKey);
			//Till here
		}
		LogUtil
		.logMessage("Leaves From DownloadImageAction:downloadPDF Method");
		return objActionMapping.findForward(strForwardKey);
	}
}