/*
 * Created for CR_109 - Clauses By Components Report
 * */
package com.EMD.LSDB.action.MasterMaintenance;

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
import com.EMD.LSDB.bo.serviceinterface.ModelCompBI;
import com.EMD.LSDB.bo.serviceinterface.ModelCompGroupBI;
import com.EMD.LSDB.bo.serviceinterface.ModelClauseBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.form.MasterMaintenance.ClaByCompsForm;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.CompGroupVO;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.SubSectionVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This Action class has the business methods for the Components
 ******************************************************************************/

public class ClaByCompsAction extends EMDAction {
	
	/***************************************************************************
	 * This Method is used to fetch the Component Groups at page load
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
		
		LogUtil.logMessage("Entering Clause By Components Action Class : initLoad (method)");
		String strForward = ApplicationConstants.SUCCESS;
		ClaByCompsForm objClaByCompsForm = (ClaByCompsForm) objActionForm;
		ArrayList arlCompGrpList = new ArrayList();
		String strUserID = "";
		try {
			LogUtil.logMessage("Entering Clause By Components Action Class : (TRY)");
			HttpSession objSession = objHttpServletRequest.getSession(false);
			
			CompGroupVO objCompGrpVo = new CompGroupVO();
			
			ModelCompGroupBI objCompGrpBo = ServiceFactory.getModelCompGroupBO();
			
			LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
			if (objLoginVo != null) 
				{
					strUserID = objLoginVo.getUserID();
				}
			objCompGrpVo.setUserID(strUserID);
			
			objCompGrpVo.setCompGrpTypeSeqNo(ApplicationConstants.COMPGRPTYPE_ZERO);
			
			arlCompGrpList = objCompGrpBo.fetchCompGroups(objCompGrpVo);
			
			if (arlCompGrpList != null && arlCompGrpList.size() > 0) {
				objClaByCompsForm.setCompGrpList(arlCompGrpList);
				objClaByCompsForm.setHdnSelCompGrp(objClaByCompsForm
						.getHdnSelCompGrp());
			}
			
		} catch (Exception objEx) {
			
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
		
	}	
	
	/***************************************************************************
	 * This Method is used to fetch the Components for Selected Component
	 * Group
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
		
	public ActionForward fetchComps(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering Clauses By Components Action : method (fetchComps)");
		String strForward = ApplicationConstants.SUCCESS;
		ClaByCompsForm objClaByCompsForm = (ClaByCompsForm) objActionForm;
		String strUserID = "";
		try {
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			
			ArrayList arlCompList = new ArrayList();
			CompGroupVO objCompGrpVo = new CompGroupVO();
			ComponentVO objComponentVo = new ComponentVO();
						
			ModelCompBI objComponentBo = ServiceFactory.getModelCompBO();
					
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if (objLoginVo != null) 
			{
				strUserID = objLoginVo.getUserID();
			}
			// To fetch Components
			
			objCompGrpVo.setUserID(strUserID);
			ModelCompGroupBI objCompGrpBo = ServiceFactory.getModelCompGroupBO();
			objCompGrpVo.setCompGrpTypeSeqNo(ApplicationConstants.COMPGRPTYPE_ZERO);
			ArrayList arlCompGrpList = new ArrayList();
			arlCompGrpList = objCompGrpBo.fetchCompGroups(objCompGrpVo);
			
			if (arlCompGrpList != null && arlCompGrpList.size() > 0) {
				objClaByCompsForm.setCompGrpList(arlCompGrpList);
				objClaByCompsForm.setHdnSelCompGrp(objClaByCompsForm
						.getHdnSelCompGrp());
			}
			objComponentVo.setComponentGroupSeqNo(objClaByCompsForm.getCompGrpSeqNo());
			
			objComponentVo.setUserID(strUserID);
			arlCompList = objComponentBo.fetchComps(objComponentVo);
			
			if (arlCompList != null && arlCompList.size() > 0) {
				objClaByCompsForm.setCompList(arlCompList);				
			}
		} catch (Exception objEx) {
			
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
		
	}
	
	/**********************************************************************************
	 * This Method is used to fetch the Clause Descriptions of Selected Component Group
	 * and components to generate a Clauses By Components Report
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **********************************************************************************/
	
	
	public ActionForward fetchClauses(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering Clauses By Components Action : method (fetchClauses)");
		String strForward = ApplicationConstants.SUCCESS;
		ClaByCompsForm objClaByCompsForm = (ClaByCompsForm) objActionForm;
		String strUserID = "";
		try {
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			
			ArrayList arlCompList = new ArrayList();
			ArrayList arlClauseList = new ArrayList();
			CompGroupVO objCompGrpVo = new CompGroupVO();
			ComponentVO objComponentVo = new ComponentVO();
						
			ModelCompGroupBI objCompGrpBo = ServiceFactory.getModelCompGroupBO();
			ModelCompBI objComponentBo = ServiceFactory.getModelCompBO();
			ModelClauseBI objClauseBo = ServiceFactory.getModelClauseBO();
					
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if (objLoginVo != null) 
			{
				strUserID = objLoginVo.getUserID();
			}
			// To fetch Components
			objCompGrpVo.setUserID(strUserID);
			
			objCompGrpVo.setCompGrpTypeSeqNo(ApplicationConstants.COMPGRPTYPE_ZERO);
			ArrayList arlCompGrpList = new ArrayList();
			arlCompGrpList = objCompGrpBo.fetchCompGroups(objCompGrpVo);
			
			if (arlCompGrpList != null && arlCompGrpList.size() > 0) {
				objClaByCompsForm.setCompGrpList(arlCompGrpList);
				objClaByCompsForm.setHdnSelCompGrp(objClaByCompsForm
						.getHdnSelCompGrp());
			}
			objComponentVo.setComponentGroupSeqNo(objClaByCompsForm.getCompGrpSeqNo());
			
			objComponentVo.setUserID(strUserID);
			arlCompList = objComponentBo.fetchComps(objComponentVo);
			
			if (arlCompList != null && arlCompList.size() > 0) {
				objClaByCompsForm.setCompList(arlCompList);
				objClaByCompsForm.setHdnCompName(objClaByCompsForm.getHdnCompName());
				}
			objComponentVo.setComponentSeqNo(objClaByCompsForm.getComponentSeqNo() );
			//Added for CR_109 Comments to get the flag of sort
			objComponentVo.setSortByflag(objClaByCompsForm.getSortByFlag() );
			//Ends here
			
			
			arlClauseList = objClauseBo.fetchClauses(objComponentVo);
			if (arlClauseList != null && arlClauseList.size() > 0) {
				objClaByCompsForm.setClaList(arlClauseList);
				}
		} catch (Exception objEx) {
			
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
	}
	
	/**********************************************************************************
	 * This Method is used to fetch the Clause Descriptions of Selected Component Group
	 * and components to generate a Clauses By Components Report in an Excel sheet
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **********************************************************************************/
	
	
/*	public ActionForward fetchClausesforExcel(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering Clauses By Components Action : method (fetchClauses)");
		String strForward = ApplicationConstants.POP_UP_FOR_EXCEL;
		ClaByCompsForm objClaByCompsForm = (ClaByCompsForm) objActionForm;
		String strUserID = "";
		try {
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			
			ArrayList arlCompList = new ArrayList();
			ArrayList arlClauseList = new ArrayList();
			CompGroupVO objCompGrpVo = new CompGroupVO();
			ComponentVO objComponentVo = new ComponentVO();
						
			ModelCompGroupBI objCompGrpBo = ServiceFactory.getModelCompGroupBO();
			ModelCompBI objComponentBo = ServiceFactory.getModelCompBO();
			ModelClauseBI objClauseBo = ServiceFactory.getModelClauseBO();
					
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if (objLoginVo != null) 
			{
				strUserID = objLoginVo.getUserID();
			}
			// To fetch Components
			
			objCompGrpVo.setUserID(strUserID);
			
			objCompGrpVo.setCompGrpTypeSeqNo(ApplicationConstants.COMPGRPTYPE_ZERO);
			ArrayList arlCompGrpList = new ArrayList();
			arlCompGrpList = objCompGrpBo.fetchCompGroups(objCompGrpVo);
			
			if (arlCompGrpList != null && arlCompGrpList.size() > 0) {
				objClaByCompsForm.setCompGrpList(arlCompGrpList);
				objClaByCompsForm.setHdnSelCompGrp(objClaByCompsForm
						.getHdnSelCompGrp());
			}
			objComponentVo.setComponentGroupSeqNo(objClaByCompsForm.getCompGrpSeqNo());
			
			objComponentVo.setUserID(strUserID);
			arlCompList = objComponentBo.fetchComps(objComponentVo);
			
			if (arlCompList != null && arlCompList.size() > 0) {
				objClaByCompsForm.setCompList(arlCompList);
				objClaByCompsForm.setHdnCompName(objClaByCompsForm.getHdnCompName());
			}
			objComponentVo.setComponentSeqNo(objClaByCompsForm.getComponentSeqNo() );
			//Added for CR_109 Comments to get the flag of sort
			objComponentVo.setSortByflag(objClaByCompsForm.getSortByFlag() );
			//Ends here			
			
			
			arlClauseList = objClauseBo.fetchClauses(objComponentVo);
			if (arlClauseList != null && arlClauseList.size() > 0) {
				objClaByCompsForm.setClaList(arlClauseList);
								
			}
		} catch (Exception objEx) {
			
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
	}*/	

//Added for CR-130	
	
	
	public ActionForward fetchClausesforExcel(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering Clauses By Components Action : method (fetchClauses)");
		String strForward = ApplicationConstants.SUCCESS;
		ClaByCompsForm objClaByCompsForm = (ClaByCompsForm) objActionForm;
		String strUserID = "";
		try {
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			
			ArrayList arlCompList = new ArrayList();
			ArrayList arlClauseList = new ArrayList();
			CompGroupVO objCompGrpVo = new CompGroupVO();
			ComponentVO objComponentVo = new ComponentVO();
						
			ModelCompGroupBI objCompGrpBo = ServiceFactory.getModelCompGroupBO();
			ModelCompBI objComponentBo = ServiceFactory.getModelCompBO();
			ModelClauseBI objClauseBo = ServiceFactory.getModelClauseBO();
					
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			if (objLoginVo != null) 
			{
				strUserID = objLoginVo.getUserID();
			}
			// To fetch Components
			
			objCompGrpVo.setUserID(strUserID);
			
			objCompGrpVo.setCompGrpTypeSeqNo(ApplicationConstants.COMPGRPTYPE_ZERO);
			ArrayList arlCompGrpList = new ArrayList();
			arlCompGrpList = objCompGrpBo.fetchCompGroups(objCompGrpVo);
			
			if (arlCompGrpList != null && arlCompGrpList.size() > 0) {
				objClaByCompsForm.setCompGrpList(arlCompGrpList);
				objClaByCompsForm.setHdnSelCompGrp(objClaByCompsForm
						.getHdnSelCompGrp());
			}
			objComponentVo.setComponentGroupSeqNo(objClaByCompsForm.getCompGrpSeqNo());
			
			objComponentVo.setUserID(strUserID);
			arlCompList = objComponentBo.fetchComps(objComponentVo);
			
			if (arlCompList != null && arlCompList.size() > 0) {
				objClaByCompsForm.setCompList(arlCompList);
				objClaByCompsForm.setHdnCompName(objClaByCompsForm.getHdnCompName());
			}
			objComponentVo.setComponentSeqNo(objClaByCompsForm.getComponentSeqNo() );
			//Added for CR_109 Comments to get the flag of sort
			objComponentVo.setSortByflag(objClaByCompsForm.getSortByFlag() );
			//Ends here			
			
			
			arlClauseList = objClauseBo.fetchClauses(objComponentVo);
			if (arlClauseList != null && arlClauseList.size() > 0) {
				objClaByCompsForm.setClaList(arlClauseList);
								
			}
			
			HSSFWorkbook workBook = new HSSFWorkbook();
			HSSFSheet sheet = workBook.createSheet("Clauses By Comps Report");//create sheet
			
			HSSFCellStyle cellHeadStyle = workBook.createCellStyle();
			HSSFCellStyle cellHeadStyleCntr = workBook.createCellStyle();
			HSSFCellStyle cellHeadStyleCenter = workBook.createCellStyle();
			HSSFCellStyle cellClauseDescStyle1 = workBook.createCellStyle();
			HSSFCellStyle cellClauseDescStyle2 = workBook.createCellStyle();
			HSSFFont headFont = workBook.createFont();
			HSSFFont redFont = workBook.createFont();
			HSSFFont ClaFont = workBook.createFont();
		
			
			headFont.setFontName(HSSFFont.FONT_ARIAL);
			headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			headFont.setColor(HSSFColor.BLACK.index);	
			headFont.setFontHeightInPoints((short) 10);
			
			redFont.setFontName(HSSFFont.FONT_ARIAL);
			redFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			redFont.setColor(HSSFColor.RED.index);	
			redFont.setFontHeightInPoints((short) 10);
			
			ClaFont.setFontName(HSSFFont.FONT_ARIAL);
			//ClaFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			ClaFont.setColor(HSSFColor.BLACK.index);	
			ClaFont.setFontHeightInPoints((short) 10);
			ClaFont.setItalic(true);
			
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
			HSSFPalette palette1 = ((HSSFWorkbook)workBook).getCustomPalette(); 
			palette1.setColorAtIndex((short)50, (byte)210, (byte)221, (byte)249);
			cellStyle.setFillForegroundColor(palette.getColor(50).getIndex()); 
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			//cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			HSSFCellStyle cellGreyStyle = workBook.createCellStyle();
			
			cellGreyStyle.setFont(Font);
			cellGreyStyle.setWrapText(true);
			cellGreyStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			palette1.setColorAtIndex((short)47, (byte)192, (byte)192, (byte)192); 
			palette1.setColorAtIndex((short)40, (byte)87, (byte)128, (byte)174); 
			cellGreyStyle.setFillForegroundColor(palette.getColor(47).getIndex()); 
			cellGreyStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellGreyStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
			/*cellGreyStyle.setRightBorderColor(palette.getColor(40).getIndex());
			cellGreyStyle.setLeftBorderColor(palette.getColor(40).getIndex());
			cellGreyStyle.setTopBorderColor(palette.getColor(40).getIndex());
			cellGreyStyle.setBottomBorderColor(palette.getColor(40).getIndex());
			cellGreyStyle.setBorderBottom(palette.getColor(40).getIndex());
			cellGreyStyle.setBorderLeft(palette.getColor(40).getIndex());
			cellGreyStyle.setBorderRight(palette.getColor(40).getIndex());
			cellGreyStyle.setBorderTop(palette.getColor(40).getIndex());
			cellGreyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			cellGreyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cellGreyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			cellGreyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);*/
			
			cellClauseDescStyle1.setWrapText(true);
			cellClauseDescStyle1.setFont(Font);
			cellClauseDescStyle1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			cellClauseDescStyle1.setFillForegroundColor(palette.getColor(47).getIndex()); 
			cellClauseDescStyle1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellClauseDescStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
			/*cellClauseDescStyle1.setRightBorderColor(palette.getColor(40).getIndex());
			cellClauseDescStyle1.setLeftBorderColor(palette.getColor(40).getIndex());
			cellClauseDescStyle1.setTopBorderColor(palette.getColor(40).getIndex());
			cellClauseDescStyle1.setBottomBorderColor(palette.getColor(40).getIndex());
			cellClauseDescStyle1.setBorderBottom(palette.getColor(40).getIndex());
			cellClauseDescStyle1.setBorderLeft(palette.getColor(40).getIndex());
			cellClauseDescStyle1.setBorderRight(palette.getColor(40).getIndex());
			cellClauseDescStyle1.setBorderTop(palette.getColor(40).getIndex());
			cellClauseDescStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			cellClauseDescStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cellClauseDescStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);
			cellClauseDescStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);*/
			
			cellClauseDescStyle2.setWrapText(true);
			cellClauseDescStyle2.setFont(Font);
			cellClauseDescStyle2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			cellClauseDescStyle2.setFillForegroundColor(palette.getColor(45).getIndex()); 
			cellClauseDescStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellClauseDescStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
			/*cellClauseDescStyle2.setRightBorderColor(palette.getColor(40).getIndex());
			cellClauseDescStyle2.setLeftBorderColor(palette.getColor(40).getIndex());
			cellClauseDescStyle2.setTopBorderColor(palette.getColor(40).getIndex());
			cellClauseDescStyle2.setBottomBorderColor(palette.getColor(40).getIndex());
			cellClauseDescStyle2.setBorderBottom(palette.getColor(40).getIndex());
			cellClauseDescStyle2.setBorderLeft(palette.getColor(40).getIndex());
			cellClauseDescStyle2.setBorderRight(palette.getColor(40).getIndex());
			cellClauseDescStyle2.setBorderTop(palette.getColor(40).getIndex());
			cellClauseDescStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			cellClauseDescStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cellClauseDescStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
			cellClauseDescStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);*/
			
			HSSFCellStyle cellLightGreyStyle = workBook.createCellStyle();
			
			cellLightGreyStyle.setFont(Font);
			cellLightGreyStyle.setWrapText(true);
			cellLightGreyStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			palette1.setColorAtIndex((short)45, (byte)224, (byte)224, (byte)224); 
			cellLightGreyStyle.setFillForegroundColor(palette.getColor(45).getIndex()); 
			cellLightGreyStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellLightGreyStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
			/*cellLightGreyStyle.setRightBorderColor(palette.getColor(40).getIndex());
			cellLightGreyStyle.setLeftBorderColor(palette.getColor(40).getIndex());
			cellLightGreyStyle.setTopBorderColor(palette.getColor(40).getIndex());
			cellLightGreyStyle.setBottomBorderColor(palette.getColor(40).getIndex());
			cellLightGreyStyle.setBorderBottom(palette.getColor(40).getIndex());
			cellLightGreyStyle.setBorderLeft(palette.getColor(40).getIndex());
			cellLightGreyStyle.setBorderRight(palette.getColor(40).getIndex());
			cellLightGreyStyle.setBorderTop(palette.getColor(40).getIndex());
			cellLightGreyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			cellLightGreyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cellLightGreyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			cellLightGreyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);*/
			
			HSSFCellStyle cellCompHeadStyle = workBook.createCellStyle();
			
			HSSFFont headCompFont = workBook.createFont();
			
			headCompFont.setFontName(HSSFFont.FONT_ARIAL);
			headCompFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			headCompFont.setColor(HSSFColor.BLACK.index);	
			headCompFont.setFontHeightInPoints((short) 10);
			
			/*cellCompHeadStyle.setFont(headCompFont);
			cellCompHeadStyle.setWrapText(true);
			cellCompHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellCompHeadStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellCompHeadStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);*/
			
			
			HSSFCellStyle cellTabHeadStyle = workBook.createCellStyle();	
			
			
			
			HSSFFont claTabFont = workBook.createFont();			
			claTabFont.setFontName(HSSFFont.FONT_ARIAL);
			claTabFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			claTabFont.setColor(HSSFColor.BLACK.index);		
			claTabFont.setFontHeightInPoints((short) 10);
			
			cellCompHeadStyle.setFont(claTabFont);
			cellCompHeadStyle.setWrapText(true);
			cellCompHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellCompHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			cellCompHeadStyle.setFillForegroundColor(palette.getColor(47).getIndex()); 
			cellCompHeadStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			/*cellCompHeadStyle.setRightBorderColor(palette.getColor(40).getIndex());
			cellCompHeadStyle.setLeftBorderColor(palette.getColor(40).getIndex());
			cellCompHeadStyle.setTopBorderColor(palette.getColor(40).getIndex());
			cellCompHeadStyle.setBottomBorderColor(palette.getColor(40).getIndex());
			cellCompHeadStyle.setBorderBottom(palette.getColor(40).getIndex());
			cellCompHeadStyle.setBorderLeft(palette.getColor(40).getIndex());
			cellCompHeadStyle.setBorderRight(palette.getColor(40).getIndex());
			cellCompHeadStyle.setBorderTop(palette.getColor(40).getIndex());
			cellCompHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			cellCompHeadStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cellCompHeadStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			cellCompHeadStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);*/
			
			cellTabHeadStyle.setFont(claTabFont);
			cellTabHeadStyle.setWrapText(true);
			cellTabHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellTabHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			cellTabHeadStyle.setFillForegroundColor(palette.getColor(45).getIndex()); 
			cellTabHeadStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			/*cellTabHeadStyle.setRightBorderColor(palette.getColor(40).getIndex());
			cellTabHeadStyle.setLeftBorderColor(palette.getColor(40).getIndex());
			cellTabHeadStyle.setTopBorderColor(palette.getColor(40).getIndex());
			cellTabHeadStyle.setBottomBorderColor(palette.getColor(40).getIndex());
			cellTabHeadStyle.setBorderBottom(palette.getColor(40).getIndex());
			cellTabHeadStyle.setBorderLeft(palette.getColor(40).getIndex());
			cellTabHeadStyle.setBorderRight(palette.getColor(40).getIndex());
			cellTabHeadStyle.setBorderTop(palette.getColor(40).getIndex());
			cellTabHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			cellTabHeadStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cellTabHeadStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			cellTabHeadStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);*/
			
			HSSFCellStyle cellNormalStyle = workBook.createCellStyle();	
			
			HSSFFont normalFont = workBook.createFont();
			normalFont.setFontName(HSSFFont.FONT_ARIAL);
			normalFont.setColor(HSSFColor.BLACK.index);	
			normalFont.setFontHeightInPoints((short) 10);

			cellNormalStyle.setWrapText(true);
			cellNormalStyle.setFont(normalFont);
			cellNormalStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellNormalStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
			
			
			int nrowCount=0;
			int intcol =0;
			
			HSSFRow row = sheet.createRow(nrowCount);
			createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString("Clauses by Components Report"));
			sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),intcol,intcol+8));
			nrowCount = nrowCount+2;
			row = sheet.createRow(nrowCount);
			createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString("Component Group :" +objClaByCompsForm.getHdnSelCompGrp()+"      "+ "Component Name :" +objClaByCompsForm.getHdnCompName()));
			sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),intcol,intcol+8));
			nrowCount = nrowCount+2;
			row = sheet.createRow(nrowCount);
			createHeadingForClaByCompsRep(workBook,row,sheet);
			nrowCount++;
			row = sheet.createRow(nrowCount);
			LogUtil.logMessage("intcol "+intcol);
			LogUtil.logMessage("nrowCount "+nrowCount);
			
			ArrayList arlCompoList = new ArrayList();
			ArrayList arlClaList = new ArrayList();
			
			Iterator ListCompoList = null;
			Iterator ListClauseList = null;
			String strEnggData = "";
			int nCounter=0;
			int nTblDataSize = 0;
			
						
			ListClauseList = arlClauseList.iterator();
			while(ListClauseList.hasNext()){				
				arlClaList = new ArrayList();
			ClauseVO objClauseVO= new ClauseVO();
			objClauseVO = (ClauseVO)ListClauseList.next();
			if(objClauseVO.getComponentList()!=null){
				arlCompoList = (ArrayList) objClauseVO.getComponentList();
				ListCompoList = arlCompoList.iterator();
				
				while(ListCompoList.hasNext()){
					
					objComponentVo = new ComponentVO();
					objComponentVo = (ComponentVO) ListCompoList.next();
					
			HSSFRichTextString strCompGrpName = new HSSFRichTextString("*"+objComponentVo.getComponentGroupName());
			HSSFRichTextString strCompGrpName1 = new HSSFRichTextString(objComponentVo.getComponentGroupName());
			HSSFRichTextString strCompName = new HSSFRichTextString(objComponentVo.getComponentName());
			HSSFRichTextString richTextClaDesc = new HSSFRichTextString("");
			if(objClauseVO.getClauseDesc()!=null && objClauseVO.getClauseDesc()!=""){
				richTextClaDesc = new HSSFRichTextString(ApplicationUtil.getRefinedClauseDesc(objClauseVO.getClauseDesc().trim()));
			}
			
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
			
				
			if(nCounter%2 == 0){
				cellStyle = cellLightGreyStyle;
				cellLabelsStyle = cellTabHeadStyle;
				cellNormalStyle = cellClauseDescStyle2;
			}
				
			else{
				cellStyle = cellGreyStyle;
				cellLabelsStyle = cellCompHeadStyle;
				cellNormalStyle = cellClauseDescStyle1;
			}
			
				boolean compLeadChk = false;
				if(objComponentVo.getCompLeadFlag().equalsIgnoreCase("Y"))
					compLeadChk = true;
				else 
					compLeadChk = false;
				LogUtil.logMessage("RowCount"+nrowCount);
				
				createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(objClauseVO.getModelName()));
				
				if(objComponentVo.getCompLeadFlag().equalsIgnoreCase("Y")){
					sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum()+1,intcol,intcol));
					}
				if (arlTableRows != null && arlTableRows.size() > 0) {
					sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum()+arlTableRows.size(),intcol,intcol));
				}
				
				/*if (arlTableRows != null && arlTableRows.size() > 0) {
					for(int n=0;n<=arlTableRows.size();n++){
						if(n==0){
							LogUtil.logMessage("RowCount"+nrowCount);
							createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(objClauseVO.getModelName()));
						}
						else{
							nrowCount++;
							row = sheet.createRow(nrowCount);
							LogUtil.logMessage("RowCount"+nrowCount);
							createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString("empty cell"));
						}
					}
					//sheet.addMergedRegion(new CellRangeAddress(row.getRowNum()-arlTableRows.size(),row.getRowNum(),intcol,intcol));
					nrowCount = nrowCount-arlTableRows.size();
					row = sheet.createRow(nrowCount);
				}else{
					createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(objClauseVO.getModelName()));
				}*/
				
				intcol++;
				if((objComponentVo.getValidationFlag()!=null)&&(objComponentVo.getValidationFlag().equalsIgnoreCase("Y"))){
					createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intcol,strCompGrpName);
					strCompGrpName.applyFont(0,1,redFont);
				}else if(objComponentVo.getCompLeadFlag().equalsIgnoreCase("Y")){
					createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intcol,strCompGrpName1);
					strCompGrpName1.applyFont(ClaFont);
				}
				else{
					createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(objComponentVo.getComponentGroupName()));
				}
				if (arlTableRows != null && arlTableRows.size() > 0) {
					sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum()+arlTableRows.size(),intcol,intcol));
				}
				intcol++;
				createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intcol,strCompName);
				if(objComponentVo.getCompLeadFlag().equalsIgnoreCase("Y")){
					createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intcol,strCompName);
					strCompGrpName1.applyFont(ClaFont);
				}
				if (arlTableRows != null && arlTableRows.size() > 0) {
					sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum()+arlTableRows.size(),intcol,intcol));
				}
				/*if (arlTableRows != null && arlTableRows.size() > 0) {
					for(int n=0;n<=arlTableRows.size();n++){
						nrowCount++;
						row = sheet.createRow(nrowCount);
						createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString("empty cell"));
					}
					sheet.addMergedRegion(new CellRangeAddress(row.getRowNum()-arlTableRows.size(),row.getRowNum(),intcol,intcol));
					nrowCount = nrowCount-arlTableRows.size();
					row = sheet.createRow(nrowCount);
				}*/
				intcol++;
				LogUtil.logMessage("intcol"+intcol);
				createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, intcol,richTextClaDesc);
				intcol++;
				createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(""));
				intcol++;
				createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(""));
				intcol++;
				createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(""));
				intcol++;
				createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(""));
				
				sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),intcol-4,intcol));
				
				if(objComponentVo.getCompLeadFlag().equalsIgnoreCase("Y")){
					sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum()+1,intcol-4,intcol));
				}
				intcol++;
				createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(strEnggData));
				if(objComponentVo.getCompLeadFlag().equalsIgnoreCase("Y")){
					sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum()+1,intcol,intcol));
				}
				if (arlTableRows != null && arlTableRows.size() > 0) {
					sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum()+arlTableRows.size(),intcol,intcol));
				}
				if (arlTableRows != null && arlTableRows.size() > 0) {	
				LogUtil.logMessage("arlTableRows1 "+arlTableRows.size());
					 for (int k= 0; k < arlTableRows.size(); k++) {
						 nrowCount++;
							if (nrowCount > sheet.getLastRowNum())
								row = sheet.createRow(nrowCount);
							else    
								row = sheet.getRow(nrowCount);
							
							int colCount = 3;
						
						ArrayList arlTableColumns = (ArrayList) arlTableRows.get(k);
						if (k==0)
							createCell(row,cellLabelsStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(0)));
						else
							createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(0)));
						
						if (k==0)
							createCell(row,cellLabelsStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(1)));
						else
							createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(1)));
	
						if (k==0)
							createCell(row,cellLabelsStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(2)));
						else
							createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(2)));
	
						if (k==0)
							createCell(row,cellLabelsStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(3)));
						else
							createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(3)));
						
						if (k==0)
							createCell(row,cellLabelsStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(4)));
						else
							createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, colCount++,new HSSFRichTextString((String) arlTableColumns.get(4)));
						colCount = colCount - 4;
					}
				}
				
				if(compLeadChk == true)
					nCounter--;
				
				
	    	nrowCount++;
	    	nCounter++;
			strEnggData="";
			intcol=0;
			row = sheet.createRow(nrowCount);
			intcol=0;
			LogUtil.logMessage("intcol "+intcol);
			LogUtil.logMessage("nrowCount "+nrowCount);
					}
				}
			}
			
			objHttpServletResponse.setContentType("application/vnd.ms-excel");
			objHttpServletResponse.setHeader("Content-disposition", "attachment; filename= ClausesByCompsReport.xls");
			OutputStream out = null;
			out = objHttpServletResponse.getOutputStream();
			workBook.write(out);		
		
		} catch (Exception objEx) {
			
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		if (strForward.equalsIgnoreCase(ApplicationConstants.FAILURE))
		return objActionMapping.findForward(strForward);
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
	
	public void createHeadingForClaByCompsRep(HSSFWorkbook workBook, HSSFRow row,
			HSSFSheet sheet) throws Exception {

	HSSFCellStyle cellHeadStyle = workBook.createCellStyle();

	HSSFFont headFont = workBook.createFont();
			
	headFont.setFontName(HSSFFont.FONT_ARIAL);
	headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	headFont.setColor(HSSFColor.BLACK.index);	
	headFont.setFontHeightInPoints((short) 11);
	
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

		final String[] columnHeadings = {"Model Name", "Component Group", "Component","Clause Description","","","","","Engineering Data"};
		final int[] columnWidth = {3500,6000,6000,2000,2000,2000,2000,2000,6000};
		for (int intPos=0;intPos < 9; intPos++)
			{
			 createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,intPos,new HSSFRichTextString(columnHeadings[intPos]));
				 sheet.setColumnWidth(intPos,columnWidth[intPos]);
				 
			 }
		sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),3,7));
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
	
}
	
