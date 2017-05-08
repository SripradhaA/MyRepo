package com.EMD.LSDB.action.SpecMaintenance;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
import org.apache.struts.upload.FormFile;

import com.EMD.LSDB.action.common.EMDAction;
import com.EMD.LSDB.action.common.PDFView;
import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.FileUploadBI;
import com.EMD.LSDB.bo.serviceinterface.OrderBI;
import com.EMD.LSDB.bo.serviceinterface.OrderGenArrangeBI;
import com.EMD.LSDB.bo.serviceinterface.OrderSectionBI;
import com.EMD.LSDB.bo.serviceinterface.SpecTypeBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.constant.DatabaseConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.form.CRForm.ModifyChangeRequestForm;
import com.EMD.LSDB.form.SpecMaintenance.OrderGenArrangeForm;
import com.EMD.LSDB.vo.common.FileVO;
import com.EMD.LSDB.vo.common.GenArrangeVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.RevisionVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SpecTypeVo;
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
import com.lowagie.text.pdf.PdfOutline;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class OrderGenArrangeAction extends EMDAction {
	
	//Added Variables for CR_101 to preview General Arrangmenet PDFs
	
	public static Font strFontNoUnderLine = new Font(Font.TIMES_ROMAN, 12,
			Font.BOLD, Color.BLACK);

	public static Font strFointSizeBoldNineRed = new Font(Font.TIMES_ROMAN, 9,
			Font.BOLD, Color.RED);
	
	public static Font strFointSizeBoldSixBlack = new Font(Font.TIMES_ROMAN, 6,
			Font.BOLD, Color.BLACK);
	
	/***************************************************************************
	 * This method is for Loadint the Order General Arrange Screen
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException
	 **************************************************************************/
	public ActionForward initLoad(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Inside the GenArrangeMaintenance:initLoad");
		String strForwardKey = ApplicationConstants.SUCCESS;
		GenArrangeVO objGenArrangeVO = new GenArrangeVO();
		OrderVO objOrderVO = new OrderVO();
		ArrayList objArrayList = null;
		ArrayList arlOrderList = new ArrayList();
		SectionVO objSectionVO = new SectionVO();
		ArrayList arlSectionList = new ArrayList();
		OrderGenArrangeForm objOrderGenArrangeForm = (OrderGenArrangeForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		int intOrderKey = 0;
		// String revCode = "";
		int intRevCode = 0;
		ArrayList arlRevList = new ArrayList();
		
		/*
		 * if (objHttpServletRequest.getParameter("revCode") != null &&
		 * objHttpServletRequest.getParameter("revCode") != "") {
		 * 
		 * revCode = objHttpServletRequest.getParameter("revCode");
		 *  } else {
		 * 
		 * revCode = "1";
		 *  }
		 */
		
		objOrderGenArrangeForm.setOrderKey(intOrderKey);
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			
			if (objHttpServletRequest.getParameter("orderKeyId") != null) {
				intOrderKey = Integer.parseInt(objHttpServletRequest
						.getParameter("orderKeyId"));
				objOrderGenArrangeForm.setOrderKey(intOrderKey);
			} else {
				intOrderKey = objOrderGenArrangeForm.getOrderKey();
			}
			
			if (objOrderGenArrangeForm.getRevCode() == 0) {
				
				intRevCode = 1;// Default No revision
			} else {
				intRevCode = objOrderGenArrangeForm.getRevCode();
			}
			/**
			 * populating the Revision drop down code starts here. Added For
			 * LSDB_CR-74(Revision Markers) on 01-June-09 by ps57222
			 */
			
			RevisionVO objRevisionVO = new RevisionVO();
			objRevisionVO.setUserID(objLoginVo.getUserID());
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			
			arlRevList = objOrderSectionBI.fetchRevision(objRevisionVO);
			
			if (arlRevList != null) {
				objOrderGenArrangeForm.setRevisionList(arlRevList);
			}
			
			/**
			 * Ends here
			 */
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setOrderKey(objOrderGenArrangeForm.getOrderKeyID());
			objOrderVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			objGenArrangeVO.setUserID(objLoginVo.getUserID());
			objGenArrangeVO.setOrderKey(intOrderKey);
			objGenArrangeVO.setRevisionInput(intRevCode);
			//Added to include Data Location Type VV49326 09-06-09
			objGenArrangeVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			OrderGenArrangeBI objOrderGenArrBO = ServiceFactory
			.getOrderGenArrBO();
			objArrayList = objOrderGenArrBO.fetchGenArrImages(objGenArrangeVO);
			LogUtil.logMessage("FileVOModelGenArrangeAction :search method:"
					+ objGenArrangeVO.getFileVO().getImageSeqNo());
			
			for (int counter = 0; counter < objArrayList.size(); counter++) {
				objGenArrangeVO = (GenArrangeVO) objArrayList.get(counter);
				
				// Commented on 20May08
				// objGenArrangeVO = (GenArrangeVO) objArrayList.get(2);
				
				objOrderGenArrangeForm.setGenArgmntNotes(objGenArrangeVO
						.getGenArrangeNotes());
				objOrderGenArrangeForm.setGnNotesRevCode(objGenArrangeVO
						.getGnNotesRevCode());
				
				/**Added for CR-76 VV49326 - Starts Here**/
				objOrderGenArrangeForm.setNotesMarkFlag(objGenArrangeVO
						.getNotesMarkFlag());
				
				objOrderGenArrangeForm.setNotesMarkDesc(objGenArrangeVO
						.getNotesMarkDesc());
				LogUtil.logMessage("Notes DESC");
				
			}
			if (objArrayList != null) {
				objGenArrangeVO = new GenArrangeVO();
				objOrderGenArrangeForm.setResultList(objArrayList);
				
			}
			
			if (objArrayList.size() == 0) {
				LogUtil.logMessage("No Images for the Order Key selected");
				objOrderGenArrangeForm
				.setMessageID(ApplicationConstants.NO_ORDER_GENARRANGE_IMAGE);
			}
			
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			objSectionVO.setUserID(objLoginVo.getUserID());
			objSectionVO.setOrderKey(intOrderKey);
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			objOrderSectionBI = ServiceFactory.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null) {
				objOrderGenArrangeForm.setSectionList(arlSectionList);
			}
			
			objOrderVO.setOrderKey(objOrderGenArrangeForm.getOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objOrderGenArrangeForm.setOrderList(arlOrderList);
			
			/** ******************Ends Here ************************** */
			objOrderGenArrangeForm
			.setHdnRevViewCode(String.valueOf(intRevCode));
			LogUtil.logMessage("HdnRevViewCode:"
					+ objOrderGenArrangeForm.getHdnRevViewCode());
			
			//Added For CR_84
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			OrderVO objjOrderVO = (OrderVO) arlOrderList.get(0);
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			objSpecTypeVo.setSpectypeSeqno(objjOrderVO.getSpecTypeSeqNo());
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			ArrayList arlSpecType = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpecType.size() != 0) {
				
				SpecTypeVo objjSpecTypeVo = (SpecTypeVo) arlSpecType.get(0);
				
				if(objjSpecTypeVo.isPerfCurveMaintDisPlayFlag())
					objOrderGenArrangeForm.setPerfCurveLinkFlag(ApplicationConstants.YES);
				else
					objOrderGenArrangeForm.setPerfCurveLinkFlag(ApplicationConstants.NO);
			}
			LogUtil.logMessage("objOrderGenArrangeForm.getPerfCurveLinkFlag(): "
					+ objOrderGenArrangeForm.getPerfCurveLinkFlag());
			
			LogUtil.logMessage("objOrderGenArrangeForm.SpecTypeSeqNo:"
					+ objSpecTypeVo.getSpectypeSeqno());
			//Added For CR_84 - Ends here
			
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in MainFeatureInfoAction..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		
		LogUtil.logMessage("Leaves From OrderGenArrangeAction: InitLoad");
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***************************************************************************
	 * This method is to update Images in Order General Arrange Screen
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException,EMDException,FileNotFoundException
	 **************************************************************************/
	public ActionForward uploadOrdGenArgmntViewDtls(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException,
			FileNotFoundException, IOException {
		
		LogUtil.logMessage("Enters into OrderGenArrangeAction:updateImage");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intStatusCode = 0;
		OrderGenArrangeForm objOrderGenArrangeForm = (OrderGenArrangeForm) objActionForm;
		GenArrangeVO objGenArrangeVO = new GenArrangeVO();
		SectionVO objSectionVO = new SectionVO();
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlOrderList = new ArrayList();
		OrderVO objOrderVO = new OrderVO();
		FormFile objFormFile = null;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		int intRevCode = 0;
		ArrayList arlRevList = new ArrayList();
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		int intOrderKey=0;
		try {
			
			intOrderKey=objOrderGenArrangeForm.getOrderKey();
			FileVO objFileVO = new FileVO();
			
					
				   objFormFile =objOrderGenArrangeForm.getMdlNewViewImage(); 
					
					if (objFormFile.getFileSize() != 0)
					{
						objFileVO.setFileName(objFormFile.getFileName());
						LogUtil.logMessage("FileName in action :"
								+ objFormFile.getFileName());
						
						objFileVO.setFileLength(objFormFile.getFileSize());
						LogUtil.logMessage("FileSize in action :"
								+ objFormFile.getFileSize());
						objFileVO.setFileStream(objFormFile.getInputStream());
						
						objFileVO.setContentType(objFormFile.getContentType());
						LogUtil.logMessage("ContentType in action :"
								+ objFormFile.getContentType());
					}
			
			objGenArrangeVO.setFileVO(objFileVO);
			objGenArrangeVO.setUserID(objLoginVo.getUserID());
			LogUtil.logMessage("User Name In OrderGenArrangeACtion:"
					+ objGenArrangeVO.getUserID());
			objGenArrangeVO.setOrderKey(intOrderKey);
			LogUtil.logMessage("OrderKey In OrderGenArrangeAction:"
					+ objGenArrangeVO.getOrderKey());
				objGenArrangeVO.setMdlNewViewName(objOrderGenArrangeForm.getMdlNewViewName());
			LogUtil.logMessage("getMdlNewViewName In OrderGenArrangeACtion:"
					+ objGenArrangeVO.getMdlNewViewName());
			objGenArrangeVO.setMdlNewViewNotes(objOrderGenArrangeForm.getMdlNewViewNotes());
			LogUtil.logMessage("getMdlNewViewName In OrderGenArrangeACtion:"
					+ objGenArrangeVO.getMdlNewViewNotes());
			LogUtil.logMessage("Calls The UpDateImage Method In Action:");
			OrderGenArrangeBI objOrderGenArrangeBO = ServiceFactory
			.getOrderGenArrBO();
			objGenArrangeVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			intStatusCode = objOrderGenArrangeBO.uploadOrdGenArgmntViewDtls(objGenArrangeVO);
			LogUtil.logMessage("successMessage in Action:" + intStatusCode);
			
			if (intStatusCode == 0) {
				objOrderGenArrangeForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			}else  {
				String strStatusCode=String.valueOf(intStatusCode);
				objOrderGenArrangeForm.setMessageID(strStatusCode);
			}
			
			if (objOrderGenArrangeForm.getRevCode() == 0) {
				
				intRevCode = 1;// Default No revision
			} else {
				intRevCode = objOrderGenArrangeForm.getRevCode();
			}
			/**
			 * populating the Revision drop down code starts here. Added For
			 * LSDB_CR-74(Revision Markers) on 01-June-09 by ps57222
			 */
			
			RevisionVO objRevisionVO = new RevisionVO();
			objRevisionVO.setUserID(objLoginVo.getUserID());
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			
			arlRevList = objOrderSectionBI.fetchRevision(objRevisionVO);
			
			if (arlRevList != null) {
				objOrderGenArrangeForm.setRevisionList(arlRevList);
			}
			
			/**
			 * Ends here
			 */
			
			/** ***** for ReLoading Images code Starts here*************** */
			objGenArrangeVO.setRevisionInput(intRevCode);
			//Added to include Data Location Type VV49326 09-06-09
			objGenArrangeVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			ArrayList objArrayList = objOrderGenArrangeBO
			.fetchGenArrImages(objGenArrangeVO);
			
			for (int counter = 0; counter < objArrayList.size(); counter++) {
				objGenArrangeVO = (GenArrangeVO) objArrayList.get(counter);
				
				// Commented on 20May08
				// objGenArrangeVO = (GenArrangeVO) objArrayList.get(2);
				
				objOrderGenArrangeForm.setGenArgmntNotes(objGenArrangeVO
						.getGenArrangeNotes());
				objOrderGenArrangeForm.setGnNotesRevCode(objGenArrangeVO
						.getGnNotesRevCode());
				
				/**Added for CR-76 VV49326 - Starts Here**/
				objOrderGenArrangeForm.setNotesMarkFlag(objGenArrangeVO
						.getNotesMarkFlag());
				
				objOrderGenArrangeForm.setNotesMarkDesc(objGenArrangeVO
						.getNotesMarkDesc());
				LogUtil.logMessage("Notes DESC");
				
			}
			if (objArrayList != null) {
				objGenArrangeVO = new GenArrangeVO();
				objOrderGenArrangeForm.setResultList(objArrayList);
				
			}
			
			if (objArrayList.size() == 0) {
				LogUtil.logMessage("No Images for the Order Key selected");
				objOrderGenArrangeForm
				.setMessageID(ApplicationConstants.NO_ORDER_GENARRANGE_IMAGE);
			}
			/** ***** for ReLoading Images code Ends here*************** */
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			objSectionVO.setUserID(objLoginVo.getUserID());
			
			objSectionVO.setOrderKey(objGenArrangeVO.getOrderKey());
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			objOrderSectionBI = ServiceFactory.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null) {
				objOrderGenArrangeForm.setSectionList(arlSectionList);
			}
			
			objOrderVO.setOrderKey(intOrderKey);
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objOrderGenArrangeForm.setOrderList(arlOrderList);
			
			LogUtil
			.logMessage("*******OrderKey inside Fetch orders Arraylist size:"
					+ objOrderGenArrangeForm.getOrderList().size());
			/** ******************Ends Here ************************** */
			
			// Added for CR-65
			objOrderGenArrangeForm.setHdnRevViewCode(objOrderGenArrangeForm
					.getHdnRevViewCode());
			objOrderGenArrangeForm.setMdlNewViewName("");
			objOrderGenArrangeForm.setMdlNewViewNotes("");
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception Block in Action..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		LogUtil.logMessage("Leaves From OrderGenArrangeAction: updateImage");
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***************************************************************************
	 * This method is to update Notes in Order General Arrange Screen
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException,EMDException,FileNotFoundException
	 **************************************************************************/
	public ActionForward updateNotes(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException,
			FileNotFoundException, IOException {
		LogUtil.logMessage("Inside  OrderGenArrangeAction:updateNotes ");
		
		String strForwardKey = ApplicationConstants.SUCCESS;
		
		int intStatusCode = 0;
		OrderGenArrangeForm objOrderGenArrangeForm = (OrderGenArrangeForm) objActionForm;
		GenArrangeVO objGenArrangeVO = new GenArrangeVO();
		SectionVO objSectionVO = new SectionVO();
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlOrderList = new ArrayList();
		OrderVO objOrderVO = new OrderVO();
		ArrayList arlArrayList = new ArrayList();
		ArrayList arlRevList = new ArrayList();
		int intRevCode = 0;
		int intOrderKey=0;
		
		try {
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			intOrderKey=objOrderGenArrangeForm.getOrderKey();
			String strGenArrangeNotes = objOrderGenArrangeForm
			.getGenArgmntNotes();
			objGenArrangeVO.setGenArrangeNotes(ApplicationUtil
					.trim(strGenArrangeNotes));
			objGenArrangeVO.setOrderKey(intOrderKey);
			
			objGenArrangeVO.setUserID(objLoginVo.getUserID());
			
			OrderGenArrangeBI objOrderGenArrangeBO = ServiceFactory
			.getOrderGenArrBO();
			intStatusCode = objOrderGenArrangeBO.updateNotes(objGenArrangeVO);
			LogUtil.logMessage("Updated Value:" + intStatusCode);
			
			if (intStatusCode == 0) {
				objOrderGenArrangeForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			}
			
			objGenArrangeVO.setOrderKey(objOrderGenArrangeForm.getOrderKey());
			
			LogUtil.logMessage("UserID in ModelGenArrangeAction search method:"
					+ objGenArrangeVO.getUserID());
			
			/** Added For LSDB_CR-74[Revision Markers] on 01-June-09 by ps57222 * */
			
			if (objOrderGenArrangeForm.getRevCode() == 0) {
				
				intRevCode = 1;// Default No revision
			} else {
				intRevCode = objOrderGenArrangeForm.getRevCode();
			}
			/**
			 * populating the Revision drop down code starts here. Added For
			 * LSDB_CR-74(Revision Markers) on 01-June-09 by ps57222
			 */
			
			RevisionVO objRevisionVO = new RevisionVO();
			objRevisionVO.setUserID(objLoginVo.getUserID());
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			
			arlRevList = objOrderSectionBI.fetchRevision(objRevisionVO);
			
			if (arlRevList != null) {
				objOrderGenArrangeForm.setRevisionList(arlRevList);
			}
			
			/**
			 * Ends here
			 */
			objGenArrangeVO.setRevisionInput(intRevCode);
			//Added to include Data Location Type VV49326 09-06-09
			objGenArrangeVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			arlArrayList = objOrderGenArrangeBO
			.fetchGenArrImages(objGenArrangeVO);
			
			if (arlArrayList != null) {
				objGenArrangeVO = new GenArrangeVO();
				objOrderGenArrangeForm.setResultList(arlArrayList);
			}
			for (int counter = 0; counter < arlArrayList.size(); counter++) {
					
				objGenArrangeVO = (GenArrangeVO) arlArrayList.get(counter);
				//Commented During testing of CR_101
				//objGenArrangeVO = (GenArrangeVO) arlArrayList.get(2);
					
				objGenArrangeVO.setGenArrangeNotes(objGenArrangeVO
						.getGenArrangeNotes());
				objOrderGenArrangeForm.setGnNotesRevCode(objGenArrangeVO
						.getGnNotesRevCode());
				
				//Added for CR-76
				objOrderGenArrangeForm.setNotesMarkFlag(objGenArrangeVO
						.getNotesMarkFlag());
				
				objOrderGenArrangeForm.setNotesMarkDesc(objGenArrangeVO
						.getNotesMarkDesc());
				}
			
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			objSectionVO.setUserID(objLoginVo.getUserID());
			
			objSectionVO.setOrderKey(objGenArrangeVO.getOrderKey());
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			objOrderSectionBI = ServiceFactory.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null) {
				objOrderGenArrangeForm.setSectionList(arlSectionList);
			}
			
			objOrderVO.setOrderKey(intOrderKey);
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objOrderGenArrangeForm.setOrderList(arlOrderList);
			
			/** ******************Ends Here ************************** */
			
			// Added for CR-65
			objOrderGenArrangeForm.setHdnRevViewCode(objOrderGenArrangeForm
					.getHdnRevViewCode());
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception Block in Action..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
		}
		
		LogUtil.logMessage("Leaves From OrderGenArrangeAction: updateNotes");
		return objActionMapping.findForward(strForwardKey);
	}
	
	
	
	//Added For CR_101
	/***************************************************************************
	 * This method is to update Images in Order General Arrange Screen
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException,EMDException,FileNotFoundException
	 **************************************************************************/
	public ActionForward updateOrdGenArgmntViewDtls(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException,
			FileNotFoundException, IOException {
		
		LogUtil.logMessage("Enters into OrderGenArrangeAction:updateOrdGenArgmntViewDtls");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intStatusCode = 0;
		OrderGenArrangeForm objOrderGenArrangeForm = (OrderGenArrangeForm) objActionForm;
		GenArrangeVO objGenArrangeVO = new GenArrangeVO();
		SectionVO objSectionVO = new SectionVO();
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlOrderList = new ArrayList();
		OrderVO objOrderVO = new OrderVO();
		//FormFile objFormFile = null;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		int intRevCode = 0;
		ArrayList arlRevList = new ArrayList();
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		int intOrderKey=0;
		
		try {
			intOrderKey=objOrderGenArrangeForm.getOrderKey();
			//FileVO objFileVO = new FileVO();
			
			//List myFiles =(List) objModelGenArrangeForm.getMdlNewViewImage();
			
			//LogUtil.logMessage("Length in action .getHdnFileName():" + myFiles.size());
			
			//FormFile objFormFile =objOrderGenArrangeForm.getMdlNewViewImage(); 
					
			   FileVO objFileVO = new FileVO();
				
				List myFiles =(List) objOrderGenArrangeForm.getViewImg();
				
				LogUtil.logMessage("Length in action .getHdnFileName():" + myFiles.size());
				
				for(int i=0;i<myFiles.size();i++){
				   
				   if(myFiles.get(i)!=null){
					   
					    LogUtil.logMessage("Index News :" + i);
					   
						FormFile objFormFile =(FormFile) myFiles.get(i) ; 
						
						if (objFormFile.getFileSize() != 0)
						{
							objFileVO.setFileName(objFormFile.getFileName());
							LogUtil.logMessage("FileName in action :"
									+ objFormFile.getFileName());
							
							objFileVO.setFileLength(objFormFile.getFileSize());
							LogUtil.logMessage("FileSize in action :"
									+ objFormFile.getFileSize());
							objFileVO.setFileStream(objFormFile.getInputStream());
							
							objFileVO.setContentType(objFormFile.getContentType());
							LogUtil.logMessage("ContentType in action :"
									+ objFormFile.getContentType());
						}
				   } 
				} 
			
			
			objGenArrangeVO.setFileVO(objFileVO);
			objGenArrangeVO.setUserID(objLoginVo.getUserID());
			LogUtil.logMessage("User Name In OrderGenArrangeACtion:"
					+ objGenArrangeVO.getUserID());
			objGenArrangeVO.setOrderKey(objOrderGenArrangeForm.getOrderKey());
			LogUtil.logMessage("OrderKey In OrderGenArrangeAction:"
					+ objGenArrangeVO.getOrderKey());
			objGenArrangeVO.setModelViewSeqNo(objOrderGenArrangeForm.getHdSelectedMdlViewSeqNo());
			
			/*objGenArrangeVO.setMdlNewViewName(objOrderGenArrangeForm.getMdlNewViewName());
			LogUtil.logMessage("ModelView In OrderGenArrangeACtion:"
					+ objGenArrangeVO.getModelView());
			objGenArrangeVO.setMdlNewViewNotes(objOrderGenArrangeForm.getMdlNewViewNotes());
			LogUtil.logMessage("ModelView In OrderGenArrangeACtion:"
								+ objGenArrangeVO.getMdlViewNotes());*/
			
			objGenArrangeVO.setModelView(objOrderGenArrangeForm.getHdSelectedMdlView());
			objGenArrangeVO.setMdlViewNotes(objOrderGenArrangeForm.getHdSelectedMdlViewNotes());
			
			objGenArrangeVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			LogUtil.logMessage("Calls The UpDateImage Method In Action:");
			OrderGenArrangeBI objOrderGenArrangeBO = ServiceFactory
			.getOrderGenArrBO();
			intStatusCode = objOrderGenArrangeBO.updateOrdGenArgmntViewDtls(objGenArrangeVO);
			
			LogUtil.logMessage("successMessage in Action:" + intStatusCode);
			
			if (intStatusCode == 0) {
				objOrderGenArrangeForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			}else  {
				String strStatusCode=String.valueOf(intStatusCode);
				objOrderGenArrangeForm.setMessageID(strStatusCode);
			}
			
			if (objOrderGenArrangeForm.getRevCode() == 0) {
				
				intRevCode = 1;// Default No revision
			} else {
				intRevCode = objOrderGenArrangeForm.getRevCode();
			}
			/**
			 * populating the Revision drop down code starts here. Added For
			 * LSDB_CR-74(Revision Markers) on 01-June-09 by ps57222
			 */
			
			RevisionVO objRevisionVO = new RevisionVO();
			objRevisionVO.setUserID(objLoginVo.getUserID());
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			
			arlRevList = objOrderSectionBI.fetchRevision(objRevisionVO);
			
			if (arlRevList != null) {
				objOrderGenArrangeForm.setRevisionList(arlRevList);
			}
			
			/**
			 * Ends here
			 */
			
			/** ***** for ReLoading Images code Starts here*************** */
			objGenArrangeVO.setRevisionInput(intRevCode);
			//Added to include Data Location Type VV49326 09-06-09
			objGenArrangeVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			ArrayList objArrayList = objOrderGenArrangeBO
			.fetchGenArrImages(objGenArrangeVO);
			
			for (int counter = 0; counter < objArrayList.size(); counter++) {
				objGenArrangeVO = (GenArrangeVO) objArrayList.get(counter);
				
				// Commented on 20May08
				// objGenArrangeVO = (GenArrangeVO) objArrayList.get(2);
				
				objOrderGenArrangeForm.setGenArgmntNotes(objGenArrangeVO
						.getGenArrangeNotes());
				objOrderGenArrangeForm.setGnNotesRevCode(objGenArrangeVO
						.getGnNotesRevCode());
				
				/**Added for CR-76 VV49326 - Starts Here**/
				objOrderGenArrangeForm.setNotesMarkFlag(objGenArrangeVO
						.getNotesMarkFlag());
				
				objOrderGenArrangeForm.setNotesMarkDesc(objGenArrangeVO
						.getNotesMarkDesc());
				LogUtil.logMessage("Notes DESC");
				
			}
			if (objArrayList != null) {
				objGenArrangeVO = new GenArrangeVO();
				objOrderGenArrangeForm.setResultList(objArrayList);
				
			}
			
			if (objArrayList.size() == 0) {
				LogUtil.logMessage("No Images for the Order Key selected");
				objOrderGenArrangeForm
				.setMessageID(ApplicationConstants.NO_ORDER_GENARRANGE_IMAGE);
			}
			/** ***** for ReLoading Images code Ends here*************** */
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			objSectionVO.setUserID(objLoginVo.getUserID());
			
			objSectionVO.setOrderKey(intOrderKey);
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			objOrderSectionBI = ServiceFactory.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null) {
				objOrderGenArrangeForm.setSectionList(arlSectionList);
			}
			
			objOrderVO.setOrderKey(objOrderGenArrangeForm.getOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objOrderGenArrangeForm.setOrderList(arlOrderList);
			
			LogUtil
			.logMessage("*******OrderKey inside Fetch orders Arraylist size:"
					+ objOrderGenArrangeForm.getOrderList().size());
			/** ******************Ends Here ************************** */
			
			// Added for CR-65
			objOrderGenArrangeForm.setHdnRevViewCode(objOrderGenArrangeForm
					.getHdnRevViewCode());
			objOrderGenArrangeForm.setMdlNewViewName("");
			objOrderGenArrangeForm.setMdlNewViewNotes("");
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception Block in Action..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		LogUtil.logMessage("Leaves From OrderGenArrangeAction: updateImage");
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	
	
	
	
	
	
	/***************************************************************************
	 * This method is to update Notes in Order General Arrange Screen
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException,EMDException,FileNotFoundException
	 **************************************************************************/
	public ActionForward delOrdGenArgmtView(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException,
			FileNotFoundException, IOException {
		LogUtil.logMessage("Inside  OrderGenArrangeAction:delOrdGenArgmtView ");
		
		String strForwardKey = ApplicationConstants.SUCCESS;
		
		int intStatusCode = 0;
		OrderGenArrangeForm objOrderGenArrangeForm = (OrderGenArrangeForm) objActionForm;
		GenArrangeVO objGenArrangeVO = new GenArrangeVO();
		SectionVO objSectionVO = new SectionVO();
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlOrderList = new ArrayList();
		OrderVO objOrderVO = new OrderVO();
		ArrayList arlArrayList = new ArrayList();
		ArrayList arlRevList = new ArrayList();
		int intRevCode = 0;
		int intOrderKey=0;
		try {
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			intOrderKey=objOrderGenArrangeForm.getOrderKey();
			
			objGenArrangeVO.setUserID(objLoginVo.getUserID());
			objGenArrangeVO.setOrderKey(objOrderGenArrangeForm.getOrderKey());
			objGenArrangeVO.setModelViewSeqNo(objOrderGenArrangeForm.getHdSelectedMdlViewSeqNo());
			objGenArrangeVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			LogUtil.logMessage("Calls The delOrdGenArgmtView Method In Action:");
			
			OrderGenArrangeBI objOrderGenArrangeBO = ServiceFactory
			.getOrderGenArrBO();
			intStatusCode = objOrderGenArrangeBO.deleteOrdGenArgmtView(objGenArrangeVO);
			LogUtil.logMessage("Updated Value:" + intStatusCode);
			
LogUtil.logMessage("successMessage in Action:" + intStatusCode);
			
			if (intStatusCode == 0) {
				objOrderGenArrangeForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			}else  {
				String strStatusCode=String.valueOf(intStatusCode);
				objOrderGenArrangeForm.setMessageID(strStatusCode);
			}
			
			if (objOrderGenArrangeForm.getRevCode() == 0) {
				
				intRevCode = 1;// Default No revision
			} else {
				intRevCode = objOrderGenArrangeForm.getRevCode();
			}
			/**
			 * populating the Revision drop down code starts here. Added For
			 * LSDB_CR-74(Revision Markers) on 01-June-09 by ps57222
			 */
			
			RevisionVO objRevisionVO = new RevisionVO();
			objRevisionVO.setUserID(objLoginVo.getUserID());
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			
			arlRevList = objOrderSectionBI.fetchRevision(objRevisionVO);
			
			if (arlRevList != null) {
				objOrderGenArrangeForm.setRevisionList(arlRevList);
			}
			
			/**
			 * Ends here
			 */
			
			/** ***** for ReLoading Images code Starts here*************** */
			objGenArrangeVO.setRevisionInput(intRevCode);
			//Added to include Data Location Type VV49326 09-06-09
			objGenArrangeVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			ArrayList objArrayList = objOrderGenArrangeBO
			.fetchGenArrImages(objGenArrangeVO);
			for (int counter = 0; counter < objArrayList.size(); counter++) {
				objGenArrangeVO = (GenArrangeVO) objArrayList.get(counter);
				
				// Commented on 20May08
				// objGenArrangeVO = (GenArrangeVO) objArrayList.get(2);
				
				objOrderGenArrangeForm.setGenArgmntNotes(objGenArrangeVO
						.getGenArrangeNotes());
				objOrderGenArrangeForm.setGnNotesRevCode(objGenArrangeVO
						.getGnNotesRevCode());
				
				/**Added for CR-76 VV49326 - Starts Here**/
				objOrderGenArrangeForm.setNotesMarkFlag(objGenArrangeVO
						.getNotesMarkFlag());
				
				objOrderGenArrangeForm.setNotesMarkDesc(objGenArrangeVO
						.getNotesMarkDesc());
				LogUtil.logMessage("Notes DESC");
				
			}
			if (objArrayList != null) {
				objGenArrangeVO = new GenArrangeVO();
				objOrderGenArrangeForm.setResultList(objArrayList);
				
			}
			
			if (objArrayList.size() == 0) {
				LogUtil.logMessage("No Images for the Order Key selected");
				objOrderGenArrangeForm
				.setMessageID(ApplicationConstants.NO_ORDER_GENARRANGE_IMAGE);
			}
			/** ***** for ReLoading Images code Ends here*************** */
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			objSectionVO.setUserID(objLoginVo.getUserID());
			
			objSectionVO.setOrderKey(intOrderKey);
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			objOrderSectionBI = ServiceFactory.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null) {
				objOrderGenArrangeForm.setSectionList(arlSectionList);
			}
			
			objOrderVO.setOrderKey(objOrderGenArrangeForm.getOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objOrderGenArrangeForm.setOrderList(arlOrderList);
			
			LogUtil
			.logMessage("*******OrderKey inside Fetch orders Arraylist size:"
					+ objOrderGenArrangeForm.getOrderList().size());
			/** ******************Ends Here ************************** */
			
			// Added for CR-65
			objOrderGenArrangeForm.setHdnRevViewCode(objOrderGenArrangeForm
					.getHdnRevViewCode());
			
			objOrderGenArrangeForm.setMdlNewViewName("");
			objOrderGenArrangeForm.setMdlNewViewNotes("");
			
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception Block in Action..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
		}
		
		LogUtil.logMessage("Leaves From OrderGenArrangeAction: delOrdGenArgmtView");
		return objActionMapping.findForward(strForwardKey);
	}
	
	//Added for CR_101
	/***************************************************************************
	 * This method is for loading the General Arrangement PDF
	 * 
	 * @author Mahindra Satyam
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws BusinessException
	 **************************************************************************/
	public ActionForward previewGenArngmentPDF(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		LogUtil.logMessage("Inside the OrderGenArrangeAction:previewGenArngmentPDF");
		String strForwardKey = ApplicationConstants.SUCCESS;
		OrderGenArrangeForm objOrderGenArrangeForm = (OrderGenArrangeForm) objActionForm;
		GenArrangeVO objGenArrangeVO = new GenArrangeVO();
		HttpSession objSession = objHttpServletRequest.getSession(false);
		String strUserID = null;
		int intRevCode = 0;ArrayList arlGenArngmtList = new ArrayList();
		String[] arlModelViewNotes = {"","","","","","",""};	
		String strGenArrangeNotes = "";
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
		String strImgMarkFlag = "";
		String strImgMarkDesc = "";
		String strNotesMarkFlag = "";
		String strNotesMarkDesc = "";		
		int[] intAdtlGenArrSeqNo = new int[4];
		try {
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			//Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			
			OrderVO objOrderVO = new OrderVO();
			OrderVO objjOrderVO = new OrderVO();
			ArrayList arlOrderList = new ArrayList();
		
			objOrderVO.setOrderKey(objOrderGenArrangeForm.getOrderKey());
			objOrderVO.setUserID(strUserID);
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);

			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			if (arlOrderList != null && arlOrderList.size() > 0) {

				objjOrderVO = (OrderVO) arlOrderList.get(0);
				LogUtil.logMessage("OrderList Size" + arlOrderList.size());
			}
			
			String strHeaderFlag = objjOrderVO.getPdfHeaderFlag();
			String strModelName = objjOrderVO.getModelName();

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Document document = new Document(PageSize.A4, 7, 0, 40, 100);//Edited for CR-130 PDF logo alignment issue
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			
			String strWaterMarkFlag=ApplicationConstants.YES;
			PDFView objMyPage = new PDFView(strWaterMarkFlag,strHeaderFlag,"","");
			writer.setPageEvent(objMyPage);
			
			document.addAuthor("EMD-LSDB");
			document.addSubject("EMD-LSDB Preview General Arrangement PDF ");
			document.addCreationDate();
			document.open();
			document.newPage();
			
			objGenArrangeVO.setOrderKey(objOrderGenArrangeForm.getOrderKey());
			
			OrderGenArrangeBI objOrderGenArrBO = ServiceFactory.getOrderGenArrBO();
			FileUploadBI objFileUploadBO = ServiceFactory.getFileUploadBO();
		
			PdfPTable pdftloc = new PdfPTable(2);
			pdftloc.setWidthPercentage(85);
		
			Cell cel = new Cell();
			Table tloc = new Table(2);
			tloc.setTableFitsPage(true);
		
			PdfPTable marker = new PdfPTable(10);
			float[] img = { 25, 75 };
			Table imgMark = new Table(2);
			imgMark.setWidths(img);
		
			// To fetch Order General Arrangements
			if (objOrderGenArrangeForm.getRevCode() == 0) {
				
				intRevCode = 1;// Default No revision
			} else {
				intRevCode = objOrderGenArrangeForm.getRevCode();
			}
			objGenArrangeVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objGenArrangeVO.setRevisionInput(intRevCode);
			objGenArrangeVO.setOrderKey(objOrderGenArrangeForm.getOrderKey());
			objGenArrangeVO.setUserID(strUserID);
		
			arlGenArngmtList = objOrderGenArrBO.fetchGenArrImages(objGenArrangeVO);
					
			if (arlGenArngmtList != null && arlGenArngmtList.size() > 0) {

				for (cnt = 0; cnt < arlGenArngmtList.size(); cnt++) {
					GenArrangeVO objjGenArrangeVO = (GenArrangeVO) arlGenArngmtList
							.get(cnt);
					strNotesMarkFlag = objjGenArrangeVO.getNotesMarkFlag();
					strNotesMarkDesc = objjGenArrangeVO.getNotesMarkDesc();
					if (objjGenArrangeVO.getImgMarkFlag() != null
							&& (objjGenArrangeVO.getImgMarkFlag()
									.equalsIgnoreCase("Y"))) {

						strImgMarkFlag = objjGenArrangeVO.getImgMarkFlag();
						strImgMarkDesc = objjGenArrangeVO.getImgMarkDesc();
					}

					ArrayList arlGenArrangment = (ArrayList) objjGenArrangeVO
							.getRevCode();
					if (arlGenArrangment != null && arlGenArrangment.size() > 0) {

						for (int j = 0; j < arlGenArrangment.size(); j++) {

							colGenArrangement.add((String) arlGenArrangment
									.get(j));
						}
					}

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
					pdfCel.setColspan(10);
					marker.addCell(pdfCel);
				} else {
					pdfCel = new PdfPCell(new Paragraph("",
							strFointSizeBoldNineRed));
					pdfCel.setBorderColor(new Color(255, 255, 255));
					pdfCel.setLeading(0f, 1.0f);
					pdfCel.setColspan(10);
					marker.addCell(pdfCel);
				}
				
				pdfCel = new PdfPCell(marker);
				pdfCel.setHorizontalAlignment(Element.ALIGN_CENTER);
				pdfCel.setBorderColor(new Color(255, 255, 255));
				pdfCel.setColspan(2);
				pdftloc.addCell(pdfCel);

				document.add(pdftloc);

				// To display Top View
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
				
				cel = new Cell(new Phrase(11f,
						arlModelViewNotes[1], strFontNoUnderLine));
				cel.setBorderColor(new Color(255, 255, 255));
				cel.setHorizontalAlignment(Element.ALIGN_LEFT);
				cel.setVerticalAlignment(Element.ALIGN_TOP);
				tloc.addCell(cel);
				
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
				
				tloc.setBorderColor(new Color(255, 255, 255));
				
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
			
			document.close();
			
			// setting the content type
			objHttpServletResponse.setContentType("application/pdf");
			objHttpServletResponse.setHeader("Content-disposition",
			"attachment;filename=PreviewGeneralArrangement.pdf");
			objHttpServletResponse.setContentLength(baos.size());
			
			// write ByteArrayOutputStream to the ServletOutputStream
			ServletOutputStream out = objHttpServletResponse
			.getOutputStream();
			baos.writeTo(out);
			out.flush();
			
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception Block in previewGenArngmentPDF Action..");
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
			
		}
		return objActionMapping.findForward(strForwardKey);
	}	

	/***************************************************************************
	 * This method is used to download the General Arrangement Image
	 * 
	 * @author Mahindra Satyam Ltd
	 * @version 1.0
	 * @param FileVO
	 *            object
	 * @return byte array
	 * @throws Exception
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
	 * @author Mahindra Satyam Ltd
	 * @version 1.0
	 * @table , table object
	 **************************************************************************/
	private static void setTableProperties(Table header) {
		header.setBorderColor(new Color(255, 255, 255));
		header.setBorder(1);
		header.setPadding(1);
		header.setSpacing(1);
		header.setBorderWidth(0);

	}
	
	/***************************************************************************
	 * This method is used to set cell padding
	 * 
	 * @author Mahindra Satyam Ltd
	 * @version 1.0
	 * @param PdfPCell
	 **************************************************************************/
	private static void setCellPadding(PdfPCell cell) {

		cell.setPadding(5);

	}
	//Added For CR_101 - Ends here
}
