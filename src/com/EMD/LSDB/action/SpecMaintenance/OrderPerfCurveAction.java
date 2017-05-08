package com.EMD.LSDB.action.SpecMaintenance;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.EMD.LSDB.action.common.EMDAction;
import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.OrderBI;
import com.EMD.LSDB.bo.serviceinterface.OrderPerfCurveBI;
import com.EMD.LSDB.bo.serviceinterface.OrderSectionBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.constant.DatabaseConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.form.SpecMaintenance.OrderPerfCurveForm;
import com.EMD.LSDB.vo.common.FileVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.PerformanceCurveVO;
import com.EMD.LSDB.vo.common.RevisionVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.lowagie.text.Image;

/***************************************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This Action class has the business methods for the OrderPerfCurve
 **************************************************************************************************/
public class OrderPerfCurveAction extends EMDAction {
	
	/***************************************************************************
	 * This method is for Loadint the Order Perf Curve Screen
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
		LogUtil.logMessage("Inside the OrderPerfCurveAction:initLoad");
		String strForwardKey = ApplicationConstants.SUCCESS;
		PerformanceCurveVO objPerformanceCurveVO = new PerformanceCurveVO();
		OrderVO objOrderVO = new OrderVO();
		ArrayList arlImageList = null;
		ArrayList arlOrderList = new ArrayList();
		SectionVO objSectionVO = new SectionVO();
		ArrayList arlSectionList = new ArrayList();
		OrderPerfCurveForm objOrderPerfCurveForm = (OrderPerfCurveForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		int intOrderKey = 0;
		//String revCode = "";
		int intRevCode = 0;
		ArrayList arlRevList = new ArrayList();
		if (objHttpServletRequest.getParameter("orderKeyId") != null) {
			intOrderKey = Integer.parseInt(objHttpServletRequest
					.getParameter("orderKeyId"));
			objOrderPerfCurveForm.setOrderKey(intOrderKey);
		} else {
			intOrderKey = objOrderPerfCurveForm.getOrderKey();
		}
		
		/*if (objHttpServletRequest.getParameter("revCode") != null
		 && objHttpServletRequest.getParameter("revCode") != "") {
		 revCode = objHttpServletRequest.getParameter("revCode");
		 } else {
		 
		 revCode = "1";
		 }*/
		
		if (objOrderPerfCurveForm.getRevCode() == 0) {
			
			intRevCode = 1;//Default No revision
			
		} else {
			intRevCode = objOrderPerfCurveForm.getRevCode();
		}
		
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			
			/**
			 * populating the Revision drop down code starts here.
			 * Added For LSDB_CR-74(Revision Markers) on 01-June-09 by ps57222
			 */
			
			RevisionVO objRevisionVO = new RevisionVO();
			objRevisionVO.setUserID(objLoginVo.getUserID());
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			
			arlRevList = objOrderSectionBI.fetchRevision(objRevisionVO);
			
			if (arlRevList != null) {
				objOrderPerfCurveForm.setRevisionList(arlRevList);
			}
			
			/**
			 * Ends here
			 */
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
				objOrderPerfCurveForm.setSectionList(arlSectionList);
			}
			
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setOrderKey(objOrderPerfCurveForm.getOrderKey());
			objOrderVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objPerformanceCurveVO.setUserID(objLoginVo.getUserID());
			objPerformanceCurveVO.setOrderKey(intOrderKey);
			objPerformanceCurveVO.setRevisionInput(intRevCode);
			//Added to include Data Location Type VV49326 09-06-09
			objPerformanceCurveVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			OrderPerfCurveBI objOrderPerfCurveBO = ServiceFactory
			.getOrderPerfCurveBO();
			arlImageList = objOrderPerfCurveBO
			.fetchPerfCurveImages(objPerformanceCurveVO);
			if (arlImageList != null && arlImageList.size() > 0) {
				
				objPerformanceCurveVO = new PerformanceCurveVO();
				objOrderPerfCurveForm.setResultList(arlImageList);
			}
			if (arlImageList.size() == 0) {
				LogUtil.logMessage("No Images for the Order Key selected");
				objOrderPerfCurveForm
				.setMessageID(ApplicationConstants.NO_ORDER_GENARRANGE_IMAGE);
			}
			
			objOrderVO.setOrderKey(objOrderPerfCurveForm.getOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objOrderPerfCurveForm.setOrderList(arlOrderList);
			
			/** ******************Ends Here ************************** */
			
			//Added for CR-65 View Current Spec button 
			objOrderPerfCurveForm.setHdnRevViewCode(String.valueOf(intRevCode));
			
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in OrderPerfCurveAction..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
		}
		LogUtil.logMessage("Leaves From OrderPerfCurveAction: InitLoad");
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***************************************************************************
	 * This method is for Uploading Image in the Order Perf Curve Screen
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
	
	public ActionForward uploadPerfCurveImage(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException,
			FileNotFoundException, IOException {
		
		LogUtil.logMessage("Enters into OrderPerfCurveAction:uploadImage");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intUpdated = 0;
		//Added for Image Size validation by VV49326 on 29-05-08
		boolean imgSize = true;
		OrderPerfCurveForm objOrderPerfCurveForm = (OrderPerfCurveForm) objActionForm;
		PerformanceCurveVO objPerformanceCurveVO = new PerformanceCurveVO();
		SectionVO objSectionVO = new SectionVO();
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlOrderList = new ArrayList();
		OrderVO objOrderVO = new OrderVO();
		//Added for CR_121
		String strRowIndexList="";
		//Added for CR_121 Ends
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		ArrayList arlRevList = new ArrayList();
		int intRevCode = 0;
		try {
			
			FormFile objFormFile = objOrderPerfCurveForm.getTheFile();
			FileVO objFileVO = new FileVO();
			objFileVO.setFileName(objFormFile.getFileName());
			
			LogUtil.logMessage("FileName in OrderPerfCurveAction :"
					+ objFormFile.getFileName());
			
			objFileVO.setuploadedFile(objFormFile.getFileData());
			objFileVO.setFileLength(objFormFile.getFileSize());
			LogUtil.logMessage("FileSize in OrderPerfCurveAction :"
					+ objFormFile.getFileSize());
			objFileVO.setFileStream(objFormFile.getInputStream());
			objFileVO.setContentType(objFormFile.getContentType());
			LogUtil.logMessage("ContentType in OrderPerfCurveAction :"
					+ objFormFile.getContentType());
			
			//Added for Image Size validation by VV49326 on 29-05-08 - Starts Here
			/** Modified for uploading Performance cuve in pdf format change on Nov-21-08 by ps57222***/
			
			if (!("application/pdf").equalsIgnoreCase(objFormFile
					.getContentType())) {
				
				byte byteImage[] = objOrderPerfCurveForm.getTheFile()
				.getFileData();
				Image image = Image.getInstance(byteImage);
				
				float width = image.getScaledWidth();
				float height = image.getScaledHeight();
				LogUtil.logMessage("width:" + width);
				LogUtil.logMessage("height:" + height);
				LogUtil.logMessage("Enters into Loop:");
				if (width > 550.0 || height > 600.0) {
					
					imgSize = false;
					objOrderPerfCurveForm
					.setMessageID(ApplicationConstants.IMAGE_SIZE_VALIDATION);
				}
				
			}
			//Added for Image Size validation by VV49326 on 29-05-08 - Ends Here
			
			objPerformanceCurveVO.setFileVO(objFileVO);
			objPerformanceCurveVO.setUserID(objLoginVo.getUserID());
			objPerformanceCurveVO.setOrderKey(objOrderPerfCurveForm
					.getOrderKey());
			
			LogUtil.logMessage("Calls The UploadImage Method In Action:");
			
			objPerformanceCurveVO.setOrderKey(objOrderPerfCurveForm
					.getOrderKey());
			objPerformanceCurveVO.setCurveSeqNo(objOrderPerfCurveForm
					.getCurveSeqNo());
			objPerformanceCurveVO.setUserID(objLoginVo.getUserID());
			
			OrderPerfCurveBI objOrderPerfCurveBO = ServiceFactory
			.getOrderPerfCurveBO();
			
			//Added for CR_121
			objPerformanceCurveVO.setOrderByCode(objOrderPerfCurveForm.getNoOfPerfCurve());
			
			//	Added for Image Size validation by VV49326 on 29-05-08
			if (imgSize) {
				
				intUpdated = objOrderPerfCurveBO
				.uploadPerfCurveImage(objPerformanceCurveVO);
				
				if (intUpdated == 0) {
					objOrderPerfCurveForm
					.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
					objOrderPerfCurveForm.setFilePath("");
					
				} else {
					objOrderPerfCurveForm.setMessageID("" + intUpdated);
				}
				
			}
			/** Added For LSDB_CR-74[Revision Markers] on 01-June-09 by ps57222 **/
			
			if (objOrderPerfCurveForm.getRevCode() == 0) {
				
				intRevCode = 1;//Default No revision
				
			} else {
				intRevCode = objOrderPerfCurveForm.getRevCode();
			}
			
			objPerformanceCurveVO.setRevisionInput(intRevCode);
			objPerformanceCurveVO.setOrderKey(objOrderPerfCurveForm
					.getOrderKey());
			objPerformanceCurveVO.setUserID(objLoginVo.getUserID());
            //Added to include Data Location Type VV49326 09-06-09
			objPerformanceCurveVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			ArrayList objImagelist = objOrderPerfCurveBO
			.fetchPerfCurveImages(objPerformanceCurveVO);
			
			if (objImagelist != null && objImagelist.size() > 0) {
				LogUtil.logMessage("ArrayList not null");
				objOrderPerfCurveForm.setResultList(objImagelist);
				
			}
			
			if (objImagelist.size() == 0) {
				objOrderPerfCurveForm.setMethod("fetchPerfCurveImages");
				LogUtil.logMessage("Method value:"
						+ objOrderPerfCurveForm.getMethod());
				
			}
			
			/**
			 * populating the Revision drop down code starts here.
			 * Added For LSDB_CR-74(Revision Markers) on 01-June-09 by ps57222
			 */
			
			RevisionVO objRevisionVO = new RevisionVO();
			objRevisionVO.setUserID(objLoginVo.getUserID());
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			
			arlRevList = objOrderSectionBI.fetchRevision(objRevisionVO);
			
			if (arlRevList != null) {
				objOrderPerfCurveForm.setRevisionList(arlRevList);
			}
			
			/**
			 * Ends here
			 */
			
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			objSectionVO.setUserID(objLoginVo.getUserID());
			objSectionVO.setOrderKey(objPerformanceCurveVO.getOrderKey());
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			objOrderSectionBI = ServiceFactory.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null) {
				objOrderPerfCurveForm.setSectionList(arlSectionList);
			}
			
			objOrderVO.setOrderKey(objOrderPerfCurveForm.getOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objOrderPerfCurveForm.setOrderList(arlOrderList);
			
			/** ******************Ends Here ************************** */
			
			//Added for CR-65 
			objOrderPerfCurveForm.setHdnRevViewCode(objOrderPerfCurveForm
					.getHdnRevViewCode());
			
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception Block in Action.");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		LogUtil
		.logMessage("Leaves From OrderPerfCurveAction:uploadPerfCurveImage");
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***************************************************************************
	 * This method is for Deleting Image in the Order Perf Curve Screen
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
	public ActionForward deletePerfCurveImage(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil
		.logMessage("Enters into OrderPerfCurveAction:deleteImage Method");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intDeleteImg = 0;
		OrderPerfCurveForm objOrderPerfCurveForm = (OrderPerfCurveForm) objActionForm;
		PerformanceCurveVO objPerformanceCurveVO = new PerformanceCurveVO();
		SectionVO objSectionVO = new SectionVO();
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlOrderList = new ArrayList();
		OrderVO objOrderVO = new OrderVO();
		ArrayList arlRevList = new ArrayList();
		int intRevCode = 0;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			objPerformanceCurveVO.setOrderKey(objOrderPerfCurveForm
					.getOrderKey());
			objPerformanceCurveVO.setCurveSeqNo(objOrderPerfCurveForm
					.getCurveSeqNo());
			objPerformanceCurveVO.setUserID(objLoginVo.getUserID());
			
			OrderPerfCurveBI objOrderPerfCurveBO = ServiceFactory
			.getOrderPerfCurveBO();
			intDeleteImg = objOrderPerfCurveBO
			.deletePerfCurveImage(objPerformanceCurveVO);
			
			if (intDeleteImg == 0) {
				objOrderPerfCurveForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objOrderPerfCurveForm.setErrorMessage("" + intDeleteImg);
			}
			
			/** Added For LSDB_CR-74[Revision Markers] on 01-June-09 by ps57222 **/
			
			if (objOrderPerfCurveForm.getRevCode() == 0) {
				
				intRevCode = 1;//Default No revision
				
			} else {
				intRevCode = objOrderPerfCurveForm.getRevCode();
			}
			
			objPerformanceCurveVO.setRevisionInput(intRevCode);
			objPerformanceCurveVO.setOrderKey(objOrderPerfCurveForm
					.getOrderKey());
			objPerformanceCurveVO.setUserID(objLoginVo.getUserID());
            //Added to include Data Location Type VV49326 09-06-09
			objPerformanceCurveVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			ArrayList objImagelist = objOrderPerfCurveBO
			.fetchPerfCurveImages(objPerformanceCurveVO);
			
			if (objImagelist != null && objImagelist.size() > 0) {
				LogUtil.logMessage("ArrayList not null");
				objOrderPerfCurveForm.setResultList(objImagelist);
				
			}
			
			if (objImagelist.size() == 0) {
				objOrderPerfCurveForm.setMethod("fetchPerfCurveImages");
				LogUtil.logMessage("Method value:"
						+ objOrderPerfCurveForm.getMethod());
				
			}
			
			/**
			 * populating the Revision drop down code starts here.
			 * Added For LSDB_CR-74(Revision Markers) on 01-June-09 by ps57222
			 */
			
			RevisionVO objRevisionVO = new RevisionVO();
			objRevisionVO.setUserID(objLoginVo.getUserID());
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			
			arlRevList = objOrderSectionBI.fetchRevision(objRevisionVO);
			
			if (arlRevList != null) {
				objOrderPerfCurveForm.setRevisionList(arlRevList);
			}
			
			/**
			 * Ends here
			 */
			
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			objSectionVO.setUserID(objLoginVo.getUserID());
			objSectionVO.setOrderKey(objPerformanceCurveVO.getOrderKey());
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			objOrderSectionBI = ServiceFactory.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null) {
				objOrderPerfCurveForm.setSectionList(arlSectionList);
			}
			
			objOrderVO.setOrderKey(objOrderPerfCurveForm.getOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objOrderPerfCurveForm.setOrderList(arlOrderList);
			
			/** ******************Ends Here ************************** */
			
			//Added for CR-65 
			objOrderPerfCurveForm.setHdnRevViewCode(objOrderPerfCurveForm
					.getHdnRevViewCode());
			
		} catch (Exception objExp) {
			LogUtil.logMessage(" Exception occured in Action:" + objExp);
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		
		LogUtil
		.logMessage("Leaves From OrderPerfCurveAction:deletePerfCurveImage");
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***************************************************************************
	 * This method is for Upadte the Image Name in the Order Perf Curve Screen
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
	public ActionForward modifyImageName(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into OrderPerfCurveAction:modifyImageName ");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intUpdateImg = 0;
		OrderPerfCurveForm objOrderPerfCurveForm = (OrderPerfCurveForm) objActionForm;
		PerformanceCurveVO objPerformanceCurveVO = new PerformanceCurveVO();
		SectionVO objSectionVO = new SectionVO();
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlOrderList = new ArrayList();
		OrderVO objOrderVO = new OrderVO();
		ArrayList arlRevList = new ArrayList();
		int intRevCode = 0;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			objPerformanceCurveVO.setOrderKey(objOrderPerfCurveForm
					.getOrderKey());
			objPerformanceCurveVO.setCurveSeqNo(objOrderPerfCurveForm
					.getCurveSeqNo());
			objPerformanceCurveVO.setImageName(objOrderPerfCurveForm
					.getHdnImageName());
			objPerformanceCurveVO
			.setDataLocationType(DatabaseConstants.DATALOCATION);
			objPerformanceCurveVO.setUserID(objLoginVo.getUserID());
			
			OrderPerfCurveBI objOrderPerfCurveBO = ServiceFactory
			.getOrderPerfCurveBO();
			intUpdateImg = objOrderPerfCurveBO
			.modifyPerfCurveImageName(objPerformanceCurveVO);
			
			if (intUpdateImg == 0) {
				objOrderPerfCurveForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objOrderPerfCurveForm.setMessageID("" + intUpdateImg);
			}
			
			/** Added For LSDB_CR-74[Revision Markers] on 01-June-09 by ps57222 **/
			
			if (objOrderPerfCurveForm.getRevCode() == 0) {
				
				intRevCode = 1;//Default No revision
				
			} else {
				intRevCode = objOrderPerfCurveForm.getRevCode();
			}
			
			objPerformanceCurveVO.setRevisionInput(intRevCode);
			objPerformanceCurveVO.setOrderKey(objOrderPerfCurveForm
					.getOrderKey());
			objPerformanceCurveVO.setUserID(objLoginVo.getUserID());
            //Added to include Data Location Type VV49326 09-06-09
			objPerformanceCurveVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			ArrayList objImagelist = objOrderPerfCurveBO
			.fetchPerfCurveImages(objPerformanceCurveVO);
			
			if (objImagelist != null && objImagelist.size() > 0) {
				LogUtil.logMessage("ArrayList not null");
				objOrderPerfCurveForm.setResultList(objImagelist);
				
			}
			
			if (objImagelist.size() == 0) {
				objOrderPerfCurveForm.setMethod("fetchPerfCurveImages");
				LogUtil.logMessage("Method value:"
						+ objOrderPerfCurveForm.getMethod());
				
			}
			
			/**
			 * populating the Revision drop down code starts here.
			 * Added For LSDB_CR-74(Revision Markers) on 01-June-09 by ps57222
			 */
			
			RevisionVO objRevisionVO = new RevisionVO();
			objRevisionVO.setUserID(objLoginVo.getUserID());
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			
			arlRevList = objOrderSectionBI.fetchRevision(objRevisionVO);
			
			if (arlRevList != null) {
				objOrderPerfCurveForm.setRevisionList(arlRevList);
			}
			
			/**
			 * Ends here
			 */
			
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			objSectionVO.setUserID(objLoginVo.getUserID());
			objSectionVO.setOrderKey(objPerformanceCurveVO.getOrderKey());
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			objOrderSectionBI = ServiceFactory.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null) {
				objOrderPerfCurveForm.setSectionList(arlSectionList);
			}
			
			objOrderVO.setOrderKey(objOrderPerfCurveForm.getOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objOrderPerfCurveForm.setOrderList(arlOrderList);
			
			/** ******************Ends Here ************************** */
			
			//Added for CR-65 
			objOrderPerfCurveForm.setHdnRevViewCode(objOrderPerfCurveForm
					.getHdnRevViewCode());
			
		} catch (Exception objExp) {
			LogUtil.logMessage(" Exception occured in Action:" + objExp);
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		
		LogUtil.logMessage("Leaves From OrderPerfCurveAction:modifyImageName");
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	//Added for CR_121
	/***************************************************************************
	 * This method is for Rearrange Order Performance Curves
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
	
	public ActionForward saveRearrangedPerfCurve(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil.logMessage("Inside the OrderPerfCurveAction:saveRearrangedPerfCurve");
		String strForwardKey = ApplicationConstants.SUCCESS;
		
		PerformanceCurveVO objPerformanceCurveVO = new PerformanceCurveVO();
		PerformanceCurveVO objPerformanceCurveVOforRearrange = new PerformanceCurveVO();
		OrderVO objOrderVO = new OrderVO();
		ArrayList arlImageList = null;
		ArrayList arlOrderList = new ArrayList();
		SectionVO objSectionVO = new SectionVO();
		ArrayList arlSectionList = new ArrayList();
		ArrayList perfCurveList = new ArrayList();
		OrderPerfCurveForm objOrderPerfCurveForm = (OrderPerfCurveForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		int intOrderKey = 0;
		//String revCode = "";
		int intUpdated=0;
		int intRevCode = 0;
		ArrayList arlRevList = new ArrayList();
		if (objHttpServletRequest.getParameter("orderKeyId") != null) {
			intOrderKey = Integer.parseInt(objHttpServletRequest
					.getParameter("orderKeyId"));
			objOrderPerfCurveForm.setOrderKey(intOrderKey);
		} else {
			intOrderKey = objOrderPerfCurveForm.getOrderKey();
		}
		
		/*if (objHttpServletRequest.getParameter("revCode") != null
		 && objHttpServletRequest.getParameter("revCode") != "") {
		 revCode = objHttpServletRequest.getParameter("revCode");
		 } else {
		 
		 revCode = "1";
		 }*/
		
		if (objOrderPerfCurveForm.getRevCode() == 0) {
			
			intRevCode = 1;//Default No revision
			
		} else {
			intRevCode = objOrderPerfCurveForm.getRevCode();
		}
			
		
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			
			
			objPerformanceCurveVOforRearrange.setOrderKey(intOrderKey);
			objPerformanceCurveVOforRearrange.setDataLocationType(DatabaseConstants.DATALOCATION);
			objPerformanceCurveVOforRearrange.setUserID(objLoginVo.getUserID());
			
			String strRowIndexList=objOrderPerfCurveForm.getRowIndexList();
			String[] token = strRowIndexList.split ("\\,");
			 for (int i=0; i < token.length; i++){
				 perfCurveList.add(token[i]);
			 }
			
			String [] arlPerfCurveArray =new String[perfCurveList.size()];
			int i=0;
			for ( Iterator iter = perfCurveList.iterator(); iter.hasNext();) {
					
				String element = (String) iter.next();
				arlPerfCurveArray[i]=element;
				i++;
			}
			objPerformanceCurveVOforRearrange.setPerfCurveList(arlPerfCurveArray);
			OrderPerfCurveBI objOrderPerfCurveBI=ServiceFactory.getOrderPerfCurveBO();
			intUpdated = objOrderPerfCurveBI.reArrangePerfCurve(objPerformanceCurveVOforRearrange);
			
			if (intUpdated == 0) {
				objOrderPerfCurveForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				//objOrderPerfCurveForm.setFilePath("");
				
			} else {
				objOrderPerfCurveForm.setMessageID("" + intUpdated);
			}
			
			
			/**
			 * populating the Revision drop down code starts here.
			 * Added For LSDB_CR-74(Revision Markers) on 01-June-09 by ps57222
			 */
			
			RevisionVO objRevisionVO = new RevisionVO();
			objRevisionVO.setUserID(objLoginVo.getUserID());
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			
			arlRevList = objOrderSectionBI.fetchRevision(objRevisionVO);
			
			if (arlRevList != null) {
				objOrderPerfCurveForm.setRevisionList(arlRevList);
			}
			
			/**
			 * Ends here
			 */
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
				objOrderPerfCurveForm.setSectionList(arlSectionList);
			}
			
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setOrderKey(objOrderPerfCurveForm.getOrderKey());
			objOrderVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objPerformanceCurveVO.setUserID(objLoginVo.getUserID());
			objPerformanceCurveVO.setOrderKey(intOrderKey);
			objPerformanceCurveVO.setRevisionInput(intRevCode);
			//Added to include Data Location Type VV49326 09-06-09
			objPerformanceCurveVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			OrderPerfCurveBI objOrderPerfCurveBO = ServiceFactory
			.getOrderPerfCurveBO();
			arlImageList = objOrderPerfCurveBO
			.fetchPerfCurveImages(objPerformanceCurveVO);
			if (arlImageList != null && arlImageList.size() > 0) {
				
				objPerformanceCurveVO = new PerformanceCurveVO();
				objOrderPerfCurveForm.setResultList(arlImageList);
			}
			if (arlImageList.size() == 0) {
				LogUtil.logMessage("No Images for the Order Key selected");
				objOrderPerfCurveForm
				.setMessageID(ApplicationConstants.NO_ORDER_GENARRANGE_IMAGE);
			}
			
			objOrderVO.setOrderKey(objOrderPerfCurveForm.getOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objOrderPerfCurveForm.setOrderList(arlOrderList);
			
			/** ******************Ends Here ************************** */
			
			//Added for CR-65 View Current Spec button 
			objOrderPerfCurveForm.setHdnRevViewCode(String.valueOf(intRevCode));
			
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in OrderPerfCurveAction..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
		}
		LogUtil.logMessage("Leaves From OrderPerfCurveAction: InitLoad");
		return objActionMapping.findForward(strForwardKey);
	}
	
}