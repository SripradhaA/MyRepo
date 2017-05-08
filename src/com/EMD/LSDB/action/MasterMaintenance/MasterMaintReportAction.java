package com.EMD.LSDB.action.MasterMaintenance;

import java.io.FileReader;
import java.io.IOException;
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
import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.ModelBI;
import com.EMD.LSDB.bo.serviceinterface.ModelSubSectionBI;
import com.EMD.LSDB.bo.serviceinterface.SectionBI;
import com.EMD.LSDB.bo.serviceinterface.SpecTypeBI;
import com.EMD.LSDB.bo.serviceinterface.ViewSpecByModelBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.form.MasterMaintenance.MasterMaintReportForm;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.CompGroupVO;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SpecTypeVo;
import com.EMD.LSDB.vo.common.SubSectionVO;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;

/*******************************************************************************************
 * @author  Satyam Computer Services Ltd  
 * @version 1.0  
 * This Action class has the methods for retriveing the models and model specifications for
 * Master Maintenence report.
 ******************************************************************************************/

public class MasterMaintReportAction extends EMDAction {
	
	/***********************************************************************************************
	 * This method is used to retrieve the list of all Specification Types Added for CR-46 PM&I Spec
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException,BusinessException
	 **********************************************************************************************/
	
	public ActionForward initLoad(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		
		LogUtil.logMessage("Inside MasterMaintReportAction:initLoad");
		
		String strForwardKey = ApplicationConstants.SUCCESS;
		MasterMaintReportForm objMasterMaintReportForm = (MasterMaintReportForm) objActionForm;
		try {
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpec);
			objMasterMaintReportForm.setSpecTypeList(arlSpec);
			//Added for CR-113 @vipul to turn on/off deleted clause versions			
			objMasterMaintReportForm.setChkBoxStatus(true);
			
		} catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***********************************************************************************************
	 * This method is used to retrieve the list of all models 
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException,BusinessException
	 **********************************************************************************************/
	
	public ActionForward fetchModels(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		
		/** Declarations **/
		LogUtil.logMessage("Inside MasterMaintReportAction:fetchModels");
		ArrayList modelList;
		String strForwardKey = ApplicationConstants.SUCCESS;
		MasterMaintReportForm objMasterMaintReportForm = (MasterMaintReportForm) objActionForm;
		ModelVo objModelVO = new ModelVo();
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			objModelVO.setUserID(objLoginVo.getUserID());
			
			/****Added for CR-46 PM&I Spec for loading Specification Type Drop Down - Starts Here****/
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			
			objMasterMaintReportForm.setSpecTypeList(arlSpec);
			objMasterMaintReportForm.setSpecTypeNo(objMasterMaintReportForm
					.getSpecTypeNo());
			/****Added for CR-46 PM&I Spec for loading Specification Type Drop Down - Ends Here****/
			
			//Added for CR-46 PM&I Spec to load mOdels based on Spec TYpe
			objModelVO.setSpecTypeSeqNo(objMasterMaintReportForm
					.getSpecTypeNo());
			LogUtil.logMessage("Spec Type Seq No"
					+ objMasterMaintReportForm.getSpecTypeNo());
			/** Fetching all the models available from the DB **/
			
			ModelBI objModelBO = ServiceFactory.getModelBO();
			modelList = objModelBO.fetchModels(objModelVO);
			
			/** Set the model list to the action form **/
			
			objMasterMaintReportForm.setModelList(modelList);
			objMasterMaintReportForm.setSpecTypeNo(objMasterMaintReportForm
					.getSpecTypeNo());
			objMasterMaintReportForm.setModelSeqNo(objMasterMaintReportForm
					.getModelSeqNo());
			objMasterMaintReportForm.setShowDeletedClauses("Y");
			
		} catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***********************************************************************************************************
	 * This method is to used retrieve the Sections, Subsections & Specification Clauses for the selected model 
	 * and show it in the Pop up screen
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException,BusinessException
	 ************************************************************************************************************/
	
	public ActionForward fetchSpecByModel(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		
		/** Declarations **/
		
		LogUtil.logMessage("Inside MasterMaintReportAction:fetchSpecByModel");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList sectionList = new ArrayList();
		ArrayList subSectionList;
		ArrayList specList;
		ArrayList modelSpecList = new ArrayList();
		ArrayList modelSubsectionList = new ArrayList();
		//Added for CR-113 @vipul to turn on/off deleted clause versions------- 	
		String strShowDelClause;
		int modelSeqNo;
		String modelName = null;
		
		//Added for CR-46 PM&I Spec
		String specType = null;
		
		MasterMaintReportForm objMasterMaintReportForm = (MasterMaintReportForm) objActionForm;
		
		/** Getting the Modelsequence Number and Model name from the request parameter **/
		
		modelSeqNo = Integer.parseInt(objHttpServletRequest
				.getParameter(ApplicationConstants.SELECTED_MODEL));
		modelName = objHttpServletRequest
		.getParameter(ApplicationConstants.MODEL_NAME);
		
		//Added for CR-46 PM&I Spec
		specType = objHttpServletRequest
		.getParameter(ApplicationConstants.SELECTED_SPEC_TYPE);
		
		//Added for CR-113 @vipul to turn on/off deleted clause versions
		strShowDelClause = objHttpServletRequest.getParameter("showDelClause");
		
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
							.fetchSpecByModel(objSubSecMaintVO));
					
					/** Add the sub sections for the current section to the list **/
					
					if (subSectionList.size() != 0) {
						modelSubsectionList.add(subSectionList);
					}
					
					/** Add the clauses for the subsections of the current section to the list **/
					
					modelSpecList.add(specList);
					
				}
			}
			/** Setting the Values to the action form **/
			
			objMasterMaintReportForm.setSectionList(sectionList);
			
			if (modelSubsectionList.size() != 0) {
				
				objMasterMaintReportForm
				.setModelSubSectionList(modelSubsectionList);
			}
			objMasterMaintReportForm.setModelSpecList(modelSpecList);
			objMasterMaintReportForm.setModelSeqNo(modelSeqNo);
			objMasterMaintReportForm.setModelName(modelName);
			
			//Added for CR-113 @vipul to turn on/off deleted clause versions
			objMasterMaintReportForm.setShowDeletedClauses(strShowDelClause);
			//Added for CR-113 @vipul to turn on/off deleted clause versions ends here
			
			//Added for CR-46PM&I Spec
			objMasterMaintReportForm.setHdnSelSpecType(specType);
			
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
	
	//Added for CR-128
	/***********************************************************************************************************
	 * This method is to used retrieve the Sections, Subsections & Specification Clauses for the selected model 
	 * and show it in the Pop up screen
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException,BusinessException
	 ************************************************************************************************************/
	
	public ActionForward fetchMasterSpecExcel(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		
		/** Declarations **/
		
		LogUtil.logMessage("Inside MasterMaintReportAction:fetchMasterSpecExcel");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList sectionList = new ArrayList();
		ArrayList subSectionList;
		ArrayList specList;
		ArrayList modelSubsectionList = new ArrayList();	
		
		int modelSeqNo;
		String modelName = null;
		String strShowDelClause;
		int intPos = 0;
		String strClauseDesc = null;
		String strShowDelFlag = null;
		String strDefaultFlag = null;
		
		String specType = null;
				
		/** Getting the Modelsequence Number and Model name from the request parameter **/
		
		modelSeqNo = Integer.parseInt(objHttpServletRequest
				.getParameter(ApplicationConstants.SELECTED_MODEL));
		modelName = objHttpServletRequest
		.getParameter(ApplicationConstants.MODEL_NAME);
		
		specType = objHttpServletRequest
		.getParameter(ApplicationConstants.SELECTED_SPEC_TYPE);
		
		strShowDelClause = objHttpServletRequest.getParameter("showDelClause");
		
		SectionVO objSectionVO = new SectionVO();
		SubSectionVO objSubSecMaintVO = new SubSectionVO();
		ClauseVO objClauseVO = new ClauseVO();
		ComponentVO objComponentVO = new ComponentVO();
		objSectionVO.setModelSeqNo(modelSeqNo);
		objSectionVO.setModelName(modelName);
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		try {
			/** Setting the user Id **/
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			objSectionVO.setUserID(objLoginVo.getUserID());
			
			/** Fetching the sections available for the selected model **/
			
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			sectionList = objSectionBO.fetchSections(objSectionVO);
			Iterator sectionListIterator 		= null;
			Iterator subSectionListIterator 	= null;
			Iterator specListIterator			= null;
			Iterator listCompListIterator		= null;
			
			/** For each section get the subsections and clauses **/
			
			//create workbook
			HSSFWorkbook workBook = new HSSFWorkbook();			
			
			HSSFFont tbHeadFont = workBook.createFont();
			tbHeadFont.setFontName(HSSFFont.FONT_ARIAL);
			tbHeadFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			tbHeadFont.setColor(HSSFColor.BLACK.index);		
			tbHeadFont.setFontHeightInPoints((short) 10);	
			
			HSSFCellStyle cellOtherStyle   = workBook.createCellStyle();
			HSSFCellStyle cellDelFlagStyle = workBook.createCellStyle();
			
			HSSFFont delFlagFont = workBook.createFont();			
			delFlagFont.setFontName(HSSFFont.FONT_ARIAL);
			delFlagFont.setColor(HSSFColor.RED.index);		
			delFlagFont.setFontHeightInPoints((short) 10);
			
			HSSFFont tbDelFlagHeadFont = workBook.createFont();			
			tbDelFlagHeadFont.setFontName(HSSFFont.FONT_ARIAL);
			tbDelFlagHeadFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			tbDelFlagHeadFont.setColor(HSSFColor.RED.index);		
			tbDelFlagHeadFont.setFontHeightInPoints((short) 10);
			
			HSSFFont headFont = workBook.createFont();
			
			headFont.setFontName(HSSFFont.FONT_ARIAL);
			headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			headFont.setColor(HSSFColor.BLACK.index);	
			headFont.setFontHeightInPoints((short) 10);
			
			cellDelFlagStyle.setFont(delFlagFont);
			cellDelFlagStyle.setWrapText(true);
			cellDelFlagStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellDelFlagStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
			HSSFCellStyle cellDelClauseStyle = workBook.createCellStyle();
			cellDelClauseStyle.setFont(delFlagFont);
			cellDelClauseStyle.setWrapText(true);
			cellDelClauseStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			cellDelClauseStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
			HSSFCellStyle cellDefaultClauseStyle = workBook.createCellStyle();
			cellDefaultClauseStyle.setFont(headFont);
			cellDefaultClauseStyle.setWrapText(true);
			cellDefaultClauseStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			cellDefaultClauseStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
			HSSFFont otherFont = workBook.createFont();			
			otherFont.setFontName(HSSFFont.FONT_ARIAL);
			otherFont.setColor(HSSFColor.BLACK.index);		
			otherFont.setFontHeightInPoints((short) 10);
			
			cellOtherStyle.setFont(otherFont);
			cellOtherStyle.setWrapText(true);
			cellOtherStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellOtherStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
			HSSFFont subSecFont = workBook.createFont();			
			subSecFont.setFontName(HSSFFont.FONT_ARIAL);
			subSecFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			subSecFont.setColor(HSSFColor.BLACK.index);		
			subSecFont.setFontHeightInPoints((short) 10);
			
			cellOtherStyle.setFont(subSecFont);
			cellOtherStyle.setFillForegroundColor(HSSFColor.WHITE.index);
			cellOtherStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);	
			
			cellOtherStyle.setFont(otherFont);
			cellOtherStyle.setWrapText(true);
			cellOtherStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellOtherStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
			HSSFCellStyle cellClauseStyle = workBook.createCellStyle();

			cellClauseStyle.setFont(otherFont);
			cellClauseStyle.setWrapText(true);
			cellClauseStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			cellClauseStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			
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
			
			HSSFCellStyle cellHeadStyle    = workBook.createCellStyle();
			HSSFCellStyle cellNormalStyle  = workBook.createCellStyle();
			HSSFCellStyle cellSectionStyle = workBook.createCellStyle();
			
			

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
			
			HSSFCellStyle cellCommonStyle = workBook.createCellStyle();
			HSSFCellStyle cellTBHeadStyle = workBook.createCellStyle();

			int nHeadcount = 0;
			int nrowCount = 0;
			boolean chkShowDelFlag = false;
			boolean chkNormalFlag = false;
			HSSFSheet sheet = workBook.createSheet("Master Spec Report");
			HSSFRow row = sheet.createRow(nrowCount);
			if (sectionList.size() != 0) {
				//createHeadingForOrderClauseSpecificReport(workBook, row, sheet,specType,modelName);
				sectionListIterator = sectionList.iterator();
				int nSecCount = 0;
				//outerloop:
				while (sectionListIterator.hasNext()) {
					specList = new ArrayList();
					subSectionList = new ArrayList();
					objSectionVO = new SectionVO();
					objSubSecMaintVO = new SubSectionVO();
					objSectionVO = (SectionVO) sectionListIterator.next();
					objSubSecMaintVO.setModelSeqNo(modelSeqNo);
					objSubSecMaintVO
					.setSecSeqNo(objSectionVO.getSectionSeqNo());
					objSubSecMaintVO.setUserID(objLoginVo.getUserID());
					
					/** Get the subsection list for  current section **/
					
					ModelSubSectionBI objSubSecMaintBO = ServiceFactory
					.getSubSecMaintBO();
					subSectionList = objSubSecMaintBO
					.fetchSubSections(objSubSecMaintVO);
					subSectionListIterator = subSectionList.iterator();
					
					/** **/
					/** Get the clauses for all the subsections in the current section **/
					
					ViewSpecByModelBI objViewSpecByModelBO = ServiceFactory
					.getViewSpecByModelBO();
					specList = (objViewSpecByModelBO
							.fetchSpecByModel(objSubSecMaintVO));
					
					/** Add the sub sections for the current section to the list **/
					
					if (subSectionList.size() != 0) {
						modelSubsectionList.add(subSectionList);
					}
					LogUtil.logMessage("sectionList.size() :"+sectionList.size());
					LogUtil.logMessage("subSectionList.size()  :"+subSectionList.size() );
					LogUtil.logMessage("specList.size() :"+specList.size());
					
					/**Added for creating sheets for every section**/
					LogUtil.logMessage("Inside nrowCount :"+nrowCount);
				    // create a new sheet
					/*String strSpecialChrArray[] = {"+", "'","[", "]","~", "*", "?", ":","\"","\\","/"};
			    	String strSectionName = objSectionVO.getSectionName();
			    	for(int nSpchk=0;nSpchk < strSpecialChrArray.length ; nSpchk++)
			    	{
				    	String strFind = strSpecialChrArray[nSpchk];
				    	String strReplace = " "; 
				    	String strResult = ""; 
				    	int nSpChk; 
					    	do { // replace all matching substrings
					    		nSpChk = strSectionName.indexOf(strFind); 
						    	if(nSpChk != -1) { 
						    	strResult = strSectionName.substring(0, nSpChk); 
						    	strResult = strResult + strReplace; 
						    	strResult = strResult + strSectionName.substring(nSpChk + strFind.length()); 
						    	strSectionName  = strResult; 
						    	} 
					    	} while(nSpChk != -1);
					    	{
					    	}
			    	}
					
					HSSFSheet sheet = workBook.createSheet(objSectionVO.getSectionCode()+"."+strSectionName);
				    nrowCount = 0;
				    HSSFRow row = sheet.createRow(nrowCount);*/
				    
					/**Added for create heading section**/
					if(nHeadcount == 0){
						LogUtil.logMessage(" inside nHeadcount:nrowCount" +  nrowCount);
						createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString(""));
						sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,12));
						nrowCount++;
						row = sheet.createRow(nrowCount);
						createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString("View Spec By Model"));
						sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,12));
						nrowCount++;
						row = sheet.createRow(nrowCount);
						createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString(""));
						sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,12));
						nrowCount++;
						row = sheet.createRow(nrowCount);
						//Set up red color fonts
						if(strShowDelClause.equalsIgnoreCase("Y")){
							HSSFFont redFont = workBook.createFont();
							redFont.setColor(HSSFColor.RED.index);
							HSSFRichTextString richRedString = new HSSFRichTextString("NOTE: Clause versions in RED Color denotes that it is a Deleted Version.");
							createCell(row,cellNormalStyle,HSSFCell.CELL_TYPE_STRING,0,richRedString);
							richRedString.applyFont(25,28,redFont);
							sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,12));
							nrowCount++;
							row = sheet.createRow(nrowCount);
						}
						//Set up bold fonts
						HSSFFont boldFont = workBook.createFont();
						boldFont.setFontName(HSSFFont.FONT_ARIAL);
						boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
						boldFont.setColor(HSSFColor.BLACK.index);	
						boldFont.setFontHeightInPoints((short) 11);
						HSSFRichTextString richBoldString = new HSSFRichTextString("NOTE:Clause versions in BOLD denotes that it is a Default Version.");
						createCell(row,cellNormalStyle,HSSFCell.CELL_TYPE_STRING,0,richBoldString);
						richBoldString.applyFont(23,28,boldFont);
						sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,12));
						nrowCount++;
						row = sheet.createRow(nrowCount);
						createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString(""));
						sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,12));
						nrowCount++;
						row = sheet.createRow(nrowCount);
						createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString("Specification Type :"+specType+"        Model : "+modelName));
						sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,12));
						nrowCount++;
						row = sheet.createRow(nrowCount);
						createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString(""));
						sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,12));
					}
					nrowCount++;
					row = sheet.createRow(nrowCount);
					createCell(row,cellSectionStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString(objSectionVO.getSectionCode()+"."+objSectionVO.getSectionName()));
					sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,12));
					nrowCount++;
					int nSubSecCount = 0;
					while (subSectionListIterator.hasNext()) {

						SubSectionVO objSubSecVO = new SubSectionVO();
						objSubSecVO = (SubSectionVO) subSectionListIterator.next();
						
						if( nSubSecCount == 0){
						 row = sheet.createRow(nrowCount);
						 createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString(objSubSecVO.getSubSecCode()+"."+objSubSecVO.getSubSecName()));
						 sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,12));
						 nrowCount++;
						 row = sheet.createRow(nrowCount);
						 final String[] columnHeadings = {"LSDB Unique Clause Number", "Clause Version", "Clause Description","Engineering Data", "Component"
								,"Component Group","Customer","Modified By","Modified Date"};
						 final int[] columnWidth = {3000,3000,8300,8000,6000,6000,4000,3000,3000};
						 int nheadColCount = 0;
						 for (int nintPos=0;nintPos < 9; nintPos++)
							{
								//LogUtil.logMessage("columnHeadings[intPos]: nheadColCount:"+columnHeadings[nintPos]+" "+nheadColCount);
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
						}
						specListIterator = specList.iterator();
						while (specListIterator.hasNext()){
							
							objClauseVO = new ClauseVO();
							objClauseVO = (ClauseVO) specListIterator.next();
							
							if (objClauseVO.getSubSectionSeqNo() == objSubSecVO.getSubSecSeqNo())	{
							
							String strEnggData = "";
							strShowDelFlag = objClauseVO.getClauseDelFlag();
							strDefaultFlag = objClauseVO.getDefaultFlag();
							LogUtil.logMessage("strShowDelFlag: " + strShowDelFlag);
							LogUtil.logMessage("strShowDelClause: " + strShowDelClause);
							
							if(strShowDelFlag !=null && strShowDelFlag.equalsIgnoreCase("Y")){
								if(strShowDelClause.equalsIgnoreCase("Y")){
									cellCommonStyle = cellDelFlagStyle;
									chkShowDelFlag = true;
								}else{
									chkShowDelFlag = false;
									chkNormalFlag = false;
								}
							}else if(strDefaultFlag!=null && strDefaultFlag.equalsIgnoreCase("Y")){
								 cellCommonStyle = cellHeadStyle;
								 chkNormalFlag = true;
							}else{
								 cellCommonStyle = cellNormalStyle;
								 chkNormalFlag = true;
							}
							cellTBHeadStyle.setFont(tbDelFlagHeadFont);
							cellTBHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
							
							LogUtil.logMessage("objClauseVO.getClauseSeqNo(): " + objClauseVO.getClauseSeqNo());
							
							if(chkNormalFlag == true || chkShowDelFlag == true){
								
										nrowCount++;
										intPos = 0;
										row = sheet.createRow(nrowCount);
							
								        createCell(row,cellCommonStyle ,HSSFCell.CELL_TYPE_STRING, 0,new HSSFRichTextString(Integer.toString(objClauseVO.getClauseSeqNo())));
								        createCell(row,cellCommonStyle ,HSSFCell.CELL_TYPE_STRING, 1,new HSSFRichTextString(Integer.toString(objClauseVO.getVersionNo())));
								        
										if(objClauseVO.getClauseDesc()!=null && objClauseVO.getClauseDesc()!=""){
											strClauseDesc = getRefinedClauseDesc(objClauseVO.getClauseDesc());
										}else{
											strClauseDesc = objClauseVO.getClauseDesc();
										}
										
										strClauseDesc = strClauseDesc.trim();
										
										HSSFRichTextString richTextClaDesc = new HSSFRichTextString(strClauseDesc); 
										if(strShowDelFlag !=null && strShowDelFlag.equalsIgnoreCase("Y")){
											if(strShowDelClause.equalsIgnoreCase("Y"))
											createClauseDescCell(row,cellDelClauseStyle ,HSSFCell.CELL_TYPE_STRING, 2,richTextClaDesc);//2nd column
										}else if(strDefaultFlag!=null && strDefaultFlag.equalsIgnoreCase("Y")){
											createClauseDescCell(row,cellDefaultClauseStyle ,HSSFCell.CELL_TYPE_STRING, 2,richTextClaDesc);//2nd column
										}else{
											createClauseDescCell(row,cellClauseStyle ,HSSFCell.CELL_TYPE_STRING, 2,richTextClaDesc);//2nd column
										}
										sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),2,6));
										
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
											int colCount = 2;
											
											ArrayList arlTableColumns = (ArrayList) arlTableRows.get(k);
											if(strShowDelFlag !=null && strShowDelFlag.equalsIgnoreCase("Y")){
												if(strShowDelClause.equalsIgnoreCase("Y")){
													if (k==0)
														createCell(row,cellTBHeadStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(0)));
													else
														createCell(row,cellDelFlagStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(0)));
													
													if (k==0)
														createCell(row,cellTBHeadStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(1)));
													else
														createCell(row,cellDelFlagStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(1)));
				
													if (k==0)
														createCell(row,cellTBHeadStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(2)));
													else
														createCell(row,cellDelFlagStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(2)));
				
													if (k==0)
														createCell(row,cellTBHeadStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(3)));
													else
														createCell(row,cellDelFlagStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(3)));
													
													if (k==0)
														createCell(row,cellTBHeadStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(4)));
													else
														createCell(row,cellDelFlagStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(4)));
												}	
											}else{
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
			
											}
											}
										}
										
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
											createCell(row,cellCommonStyle ,HSSFCell.CELL_TYPE_STRING,7,new HSSFRichTextString(strEnggData));
											
											ArrayList arlCompList = (ArrayList) objClauseVO.getComponentVO();
											listCompListIterator = arlCompList.iterator();
											String strCompName = "";
											String strCompGrpName = "";									
											while (listCompListIterator.hasNext()){
												objComponentVO = new ComponentVO();
												objComponentVO = (ComponentVO) listCompListIterator.next();
												strCompName = strCompName + " " + objComponentVO.getComponentName();
												strCompGrpName = strCompGrpName + " " + objComponentVO.getComponentGroupName();
											}
											if (strCompName != "")
												createCell(row,cellCommonStyle ,HSSFCell.CELL_TYPE_STRING, 8,new HSSFRichTextString(strCompName));
											else
												createCell(row,cellCommonStyle ,HSSFCell.CELL_TYPE_STRING, 8,new HSSFRichTextString(""));
											if (strCompGrpName != "")
												createCell(row,cellCommonStyle ,HSSFCell.CELL_TYPE_STRING, 9,new HSSFRichTextString(strCompGrpName));
											else
												createCell(row,cellCommonStyle ,HSSFCell.CELL_TYPE_STRING, 9,new HSSFRichTextString(""));
											
											createCell(row,cellCommonStyle ,HSSFCell.CELL_TYPE_STRING, 10,new HSSFRichTextString(objClauseVO.getCustomerName()));
											createCell(row,cellCommonStyle ,HSSFCell.CELL_TYPE_STRING, 11,new HSSFRichTextString(objClauseVO.getModifiedBy()));	
											createCell(row,cellCommonStyle ,HSSFCell.CELL_TYPE_STRING, 12,new HSSFRichTextString(objClauseVO.getModifiedDate()));
							
									if(nrowCount == 65499) {
										 sheet = workBook.createSheet("MasterSpecReport(1)...");
										 nrowCount = -1;
										 nrowCount++;
										 row = sheet.createRow(nrowCount);
										 final String[] columnHeadings = {"LSDB Unique Clause Number", "Clause Version", "Clause Description","Engineering Data", "Component"
												,"Component Group","Customer","Modified By","Modified Date"};
										 final int[] columnWidth = {3000,3000,8300,8000,6000,6000,4000,3000,3000};
										 int nheadColCount = 0;
										 for (int nintPos=0;nintPos < 9; nintPos++)
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
									}
							}
						}
						}
						nrowCount++;
					}
					nSubSecCount++;
					nHeadcount++;
					nSecCount++;
				}
			}
			LogUtil.logMessage("end :");
		    objHttpServletResponse.setContentType("application/vnd.ms-excel");
			objHttpServletResponse.setHeader("Content-disposition", "attachment; filename= MasterSpecReport.xls");
			OutputStream out = null;
			out = objHttpServletResponse.getOutputStream();
			workBook.write(out);
			
		}catch (OutOfMemoryError objExp) {
		    // release some (all) of the above objects
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);	
			 long maxMemory = Runtime.getRuntime().maxMemory();
			 long freeMemory;
			 LogUtil.logMessage("maxMemory:"+maxMemory);
			 try{
				    Runtime r = Runtime.getRuntime(); 
				    freeMemory = r.freeMemory(); 
				}
				catch(Exception ioe){
					LogUtil
					.logMessage("Enters into Exception Block in SearchRequest1058Action..");
					ErrorInfo objError = new ErrorInfo();
					String strError = ioe.getMessage();
					objErrorInfo.setMessage(strError);
					LogUtil.logMessage("Error Message:" + strError);
					LogUtil.logError(ioe, objError);
					strForwardKey = ApplicationConstants.FAILURE;
				}

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
	
	/*public void createHeadingForOrderClauseSpecificReport(HSSFWorkbook workBook, HSSFRow row,
			HSSFSheet sheet,String specType,String model) throws Exception {

	HSSFCellStyle cellHeadStyle   = workBook.createCellStyle();
	HSSFCellStyle cellNormalStyle = workBook.createCellStyle();
	
	HSSFFont headFont = workBook.createFont();
			
	headFont.setFontName(HSSFFont.FONT_ARIAL);
	headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	headFont.setColor(HSSFColor.BLACK.index);	
	headFont.setFontHeightInPoints((short) 11);

	cellHeadStyle.setWrapText(true);
	cellHeadStyle.setFont(headFont);
	cellHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	cellHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	
	HSSFFont normalFont = workBook.createFont();
	
	normalFont.setFontName(HSSFFont.FONT_ARIAL);
	normalFont.setColor(HSSFColor.BLACK.index);	
	normalFont.setFontHeightInPoints((short) 11);

	cellNormalStyle.setWrapText(true);
	cellNormalStyle.setFont(normalFont);
	cellNormalStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	cellNormalStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	
	int rowCount = 0;
	createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString(""));
	sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,8));
	rowCount++;
	row = sheet.createRow(rowCount);
	createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString("View Spec By Model"));
	sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,8));
	rowCount++;
	row = sheet.createRow(rowCount);
	LogUtil.logMessage(" row.getRowNum() :" +  row.getRowNum());
	createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString(""));
	sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,8));
	rowCount++;
	row = sheet.createRow(rowCount);
	//Set up red color fonts
	HSSFFont redFont = workBook.createFont();
	redFont.setColor(HSSFColor.RED.index);
	HSSFRichTextString richRedString = new HSSFRichTextString("NOTE: Clause versions in RED Color denotes that it is a Deleted Version.");
	createCell(row,cellNormalStyle,HSSFCell.CELL_TYPE_STRING,0,richRedString);
	richRedString.applyFont(25,28,redFont);
	sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,8));
	rowCount++;
	row = sheet.createRow(rowCount);
	//Set up bold fonts
	HSSFFont boldFont = workBook.createFont();
	boldFont.setFontName(HSSFFont.FONT_ARIAL);
	boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	boldFont.setColor(HSSFColor.BLACK.index);	
	boldFont.setFontHeightInPoints((short) 11);
	HSSFRichTextString richBoldString = new HSSFRichTextString("NOTE:Clause versions in BOLD denotes that it is a Default Version.");
	createCell(row,cellNormalStyle,HSSFCell.CELL_TYPE_STRING,0,richBoldString);
	richBoldString.applyFont(23,28,boldFont);
	sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,8));
	rowCount++;
	row = sheet.createRow(rowCount);
	createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString(""));
	sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,8));
	rowCount++;
	row = sheet.createRow(rowCount);
	createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString("Specification Type :"+specType+"        Model : "+model));
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
	}*/
	
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
	
	//Added for CR-128 Ends here
}