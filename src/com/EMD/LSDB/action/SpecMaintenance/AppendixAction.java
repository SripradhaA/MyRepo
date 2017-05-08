/*
 * Created on May 5, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.action.SpecMaintenance;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.EMD.LSDB.action.common.EMDAction;
import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.AppendixBI;
import com.EMD.LSDB.bo.serviceinterface.OrderBI;
import com.EMD.LSDB.bo.serviceinterface.OrderSectionBI;
import com.EMD.LSDB.bo.serviceinterface.SpecTypeBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.constant.DatabaseConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.form.SpecMaintenance.AppendixForm;
import com.EMD.LSDB.vo.common.AppendixVO;
import com.EMD.LSDB.vo.common.FileVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SpecTypeVo;
import com.lowagie.text.Image;

/**
 * @author PS57222
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AppendixAction extends EMDAction {
	
	/***************************************************************************
	 * This method is used for loading the Appendix Images at Order Level
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
	
	public ActionForward fetchImage(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into AppendixAction:fetchImage");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlOrderList = new ArrayList();
		SectionVO objSectionVO = new SectionVO();
		ArrayList arlImageList = new ArrayList();
		AppendixVO objAppendixVO = new AppendixVO();
		AppendixForm objAppendixForm = (AppendixForm) objActionForm;
		OrderVO objOrderVO = new OrderVO();
		LoginVO objLoginVo = null;
		int intOrdeKey = 0;
		
		String revCode = "";
		LogUtil.logMessage("Order Key:"
				+ objHttpServletRequest.getParameter("orderKeyId"));
		if (objHttpServletRequest.getParameter("orderKeyId") != null) {
			intOrdeKey = Integer.parseInt(objHttpServletRequest
					.getParameter("orderKeyId"));
			
		} else {
			intOrdeKey = objAppendixForm.getOrderKey();
		}
		
		if (objHttpServletRequest.getParameter("revCode") != null
				&& objHttpServletRequest.getParameter("revCode") != "") {
			
			revCode = String.valueOf(objHttpServletRequest
					.getParameter("revCode"));
			
		} else {
			
			revCode = "1";
			
		}
		objAppendixForm.setOrderKey(intOrdeKey);
		objAppendixForm.setHdnOrderKey(intOrdeKey);
		objAppendixForm.setOrderNo(objAppendixForm.getOrderNo());
		LogUtil.logMessage("Order Key:" + objAppendixForm.getOrderKey());
		LogUtil.logMessage("OrderNo in AppendixAction:fetchImage:"
				+ objAppendixForm.getOrderNo());
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LogUtil.logMessage(objSession);
			
			if (objSession != null) {
				objLoginVo = (LoginVO) objSession
				.getAttribute(ApplicationConstants.USER_IN_SESSION);
				
			}
			objAppendixVO.setUserID(objLoginVo.getUserID());
			objAppendixVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objAppendixVO.setOrderKey(objAppendixForm.getOrderKey());
			
			AppendixBI objAppendixBI = ServiceFactory.getAppendixBO();
			arlImageList = objAppendixBI.fetchImage(objAppendixVO);
			if (arlImageList != null) {
				objAppendixForm.setImageList(arlImageList);
			}
			
			objSectionVO.setUserID(objLoginVo.getUserID());
			
			objSectionVO.setOrderKey(objAppendixForm.getOrderKey());
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			LogUtil
			.logMessage("Size of SectionList in AppendixAction:fetchImage:"
					+ arlSectionList.size());
			if (arlSectionList != null) {
				objAppendixForm.setSectionList(arlSectionList);
			}
			
			/**
			 * ****** This part of code is used to Display the Spec
			 * status,customer name,SAP customer code and Order Number*****
			 */
			LogUtil.logMessage("*******OrderKey inside Fetch orders:"
					+ objAppendixForm.getOrderKey());
			objOrderVO.setOrderKey(objAppendixForm.getOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			LogUtil
			.logMessage("Size of OrderList in AppendixAction:fetchImage:"
					+ arlOrderList.size());
			if (arlOrderList != null) {
				objAppendixForm.setOrderList(arlOrderList);
			}
			
			objAppendixForm.setHdnRevViewCode(revCode);

			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			OrderVO objjOrderVO = (OrderVO) arlOrderList.get(0);
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			objSpecTypeVo.setSpectypeSeqno(objjOrderVO.getSpecTypeSeqNo());
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			ArrayList arlSpecType = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpecType.size() != 0) {
				
				SpecTypeVo objjSpecTypeVo = (SpecTypeVo) arlSpecType.get(0);
				
				if(objjSpecTypeVo.isPerfCurveMaintDisPlayFlag())
					objAppendixForm.setPerfCurveLinkFlag(ApplicationConstants.YES);
				else
					objAppendixForm.setPerfCurveLinkFlag(ApplicationConstants.NO);
			}
			LogUtil.logMessage("objOrderSectionForm.getPerfCurveLinkFlag(): "
					+ objAppendixForm.getPerfCurveLinkFlag());
			
			LogUtil.logMessage("objMainFeatureInfoForm.SpecTypeSeqNo:"
					+ objSpecTypeVo.getSpectypeSeqno());
			//Added For CR_84 - Ends here
			
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in AppendixAction..");
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
	 * This method is used to Uploading the Appendix Images at Order Level
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
	
	public ActionForward addImage(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into AppendixAction:addImage");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlOrderList = new ArrayList();
		SectionVO objSectionVO = new SectionVO();
		ArrayList arlImageList = new ArrayList();
		AppendixVO objAppendixVO = new AppendixVO();
		AppendixForm objAppendixForm = (AppendixForm) objActionForm;
		OrderVO objOrderVO = new OrderVO();
		LoginVO objLoginVo = null;
		FormFile objFormFile = null;
		int intOrdeKey = 0;
		int intStatusCode;
		boolean blnDimension = true;
		
		intOrdeKey = objAppendixForm.getHdnOrderKey();
		LogUtil.logMessage("OrderKey in AppendixAction:addImage:"
				+ objAppendixForm.getHdnOrderKey());
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LogUtil.logMessage(objSession);
			
			if (objSession != null) {
				objLoginVo = (LoginVO) objSession
				.getAttribute(ApplicationConstants.USER_IN_SESSION);
				
			}
			
			objFormFile = objAppendixForm.getImageSource();
			
			FileVO objFileVO = new FileVO();
			objFileVO.setFileName(objFormFile.getFileName());
			LogUtil.logMessage("FileName in action :"
					+ objFormFile.getFileName());
			
			/*******************************************************************
			 * This part of code is used to get the dimensions of a image if
			 * image Resolution exceeds 550*600, then it throw an alert message
			 * and it should not upload the image into Table
			 */
			/* Added & Modified for CR_97 */ 
			if (!("application/pdf").equalsIgnoreCase(objFormFile
					.getContentType())) {
			byte byteImage[] = objFormFile.getFileData();
			Image objImage = Image.getInstance(byteImage);
			
			float width = objImage.getScaledWidth();
			float height = objImage.getScaledHeight();
			
				if (width > 550.0 || height > 600.0) 
				{
				objAppendixForm.setMessageID(ApplicationConstants.IMAGE_SIZE_VALIDATION);
				blnDimension = false;
				}
			}/* CR_97 Modification Ends here */
			/**
			 * Ends Here
			 */
			
			objFileVO.setFileLength(objFormFile.getFileSize());
			LogUtil.logMessage("FileSize in action :"
					+ objFormFile.getFileSize());
			objFileVO.setFileStream(objFormFile.getInputStream());
			objFileVO.setContentType(objFormFile.getContentType());
			LogUtil.logMessage("ContentType in action :"
					+ objFormFile.getContentType());
			objAppendixVO.setFileVO(objFileVO);
			objAppendixVO.setUserID(objLoginVo.getUserID());
			objAppendixVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objAppendixVO.setOrderKey(objAppendixForm.getHdnOrderKey());
			objAppendixVO.setImageName(objAppendixForm.getImgName());
			objAppendixVO.setImageDesc(objAppendixForm.getImgDesc());
			
			AppendixBI objAppendixBI = ServiceFactory.getAppendixBO();
			if (blnDimension) {
				
				intStatusCode = objAppendixBI.addImage(objAppendixVO);
				
				LogUtil.logMessage("successMessage in Action:" + intStatusCode);
				
				if (intStatusCode == 0) {
					objAppendixForm
					.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
					objAppendixForm.setImgName("");
					objAppendixForm.setImgDesc("");
				}
				
				if (intStatusCode > 0) {
					objAppendixForm.setMessageID("" + intStatusCode);
				}
			}
			objSectionVO.setUserID(objLoginVo.getUserID());
			
			objSectionVO.setOrderKey(objAppendixForm.getHdnOrderKey());
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			LogUtil
			.logMessage("Size of SectionList in AppendixAction:addImage:"
					+ arlSectionList.size());
			if (arlSectionList != null) {
				objAppendixForm.setSectionList(arlSectionList);
			}
			
			/**
			 * ****** This part of code is used to Display the Spec
			 * status,customer name,SAP customer code and Order Number*****
			 */
			LogUtil.logMessage("*******OrderKey inside Fetch orders:"
					+ objAppendixForm.getOrderKey());
			objOrderVO.setOrderKey(objAppendixForm.getHdnOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			LogUtil.logMessage("Size of OrderList in AppendixAction:addImage:"
					+ arlOrderList.size());
			if (arlOrderList != null) {
				objAppendixForm.setOrderList(arlOrderList);
			}
			
			arlImageList = objAppendixBI.fetchImage(objAppendixVO);
			if (arlImageList != null) {
				objAppendixForm.setImageList(arlImageList);
			}
			/*******************************************************************
			 * Setting The hidden orderKey into the form property (Orderkey)
			 * b'coz all the links getting the orderKey Value from the Orderkey
			 * property of the <html:link>
			 */
			objAppendixForm.setOrderKey(objAppendixForm.getHdnOrderKey());
			objAppendixForm.setClauseSeqNo(0);
			objAppendixForm.setVersionNo(0);
			
			// Added for CR-65
			objAppendixForm.setHdnRevViewCode(objAppendixForm
					.getHdnRevViewCode());
			
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in AppendixAction..");
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
	 * This method is used to Modify the Appendix Image name and Description at
	 * Order Level
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
		
		LogUtil.logMessage("Enters into AppendixAction:modifyImageName");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlOrderList = new ArrayList();
		SectionVO objSectionVO = new SectionVO();
		ArrayList arlImageList = new ArrayList();
		AppendixVO objAppendixVO = new AppendixVO();
		AppendixForm objAppendixForm = (AppendixForm) objActionForm;
		OrderVO objOrderVO = new OrderVO();
		LoginVO objLoginVo = null;
		FormFile objFormFile = null;
		int intOrdeKey = 0;
		int intStatusCode;
		
		intOrdeKey = objAppendixForm.getHdnOrderKey();
		LogUtil.logMessage("OrderKey in AppendixAction:modifyImageName:"
				+ objAppendixForm.getHdnOrderKey());
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LogUtil.logMessage(objSession);
			
			if (objSession != null) {
				objLoginVo = (LoginVO) objSession
				.getAttribute(ApplicationConstants.USER_IN_SESSION);
				
			}
			objAppendixVO.setUserID(objLoginVo.getUserID());
			objAppendixVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objAppendixVO.setOrderKey(objAppendixForm.getHdnOrderKey());
			objAppendixVO.setImageName(objAppendixForm.getAppImageName());
			objAppendixVO.setImageDesc(objAppendixForm.getAppImageDesc());
			objAppendixVO.setImageSeqNo(objAppendixForm.getImageSeqNo());
			
			AppendixBI objAppendixBI = ServiceFactory.getAppendixBO();
			intStatusCode = objAppendixBI.modifyImageName(objAppendixVO);
			
			if (intStatusCode == 0) {
				objAppendixForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			}
			
			if (intStatusCode > 0) {
				objAppendixForm.setMessageID("" + intStatusCode);
			}
			
			objSectionVO.setUserID(objLoginVo.getUserID());
			
			objSectionVO.setOrderKey(objAppendixForm.getHdnOrderKey());
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			LogUtil
			.logMessage("Size of SectionList in AppendixAction:modifyImageName:"
					+ arlSectionList.size());
			if (arlSectionList != null) {
				objAppendixForm.setSectionList(arlSectionList);
			}
			
			/**
			 * ****** This part of code is used to Display the Spec
			 * status,customer name,SAP customer code and Order Number*****
			 */
			LogUtil.logMessage("*******OrderKey inside Fetch orders:"
					+ objAppendixForm.getHdnOrderKey());
			objOrderVO.setOrderKey(objAppendixForm.getHdnOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			LogUtil
			.logMessage("Size of OrderList in AppendixAction:modifyImageName:"
					+ arlOrderList.size());
			if (arlOrderList != null) {
				objAppendixForm.setOrderList(arlOrderList);
			}
			
			arlImageList = objAppendixBI.fetchImage(objAppendixVO);
			if (arlImageList != null) {
				objAppendixForm.setImageList(arlImageList);
			}
			
			/*******************************************************************
			 * Setting The hidden orderKey into the form property (Orderkey)
			 * b'coz all the links getting the orderKey Value from the Orderkey
			 * property of the <html:link>
			 */
			
			objAppendixForm.setOrderKey(objAppendixForm.getHdnOrderKey());
			objAppendixForm.setClauseSeqNo(0);
			objAppendixForm.setVersionNo(0);
			
			// Added for CR-65
			objAppendixForm.setHdnRevViewCode(objAppendixForm
					.getHdnRevViewCode());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in AppendixAction..");
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
	 * This method is used to delete the Appendix Image at Order Level
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
	
	public ActionForward deleteImage(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into AppendixAction:deleteImage");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlOrderList = new ArrayList();
		SectionVO objSectionVO = new SectionVO();
		ArrayList arlImageList = new ArrayList();
		AppendixVO objAppendixVO = new AppendixVO();
		AppendixForm objAppendixForm = (AppendixForm) objActionForm;
		OrderVO objOrderVO = new OrderVO();
		LoginVO objLoginVo = null;
		FormFile objFormFile = null;
		int intOrdeKey = 0;
		int intStatusCode;
		
		intOrdeKey = objAppendixForm.getHdnOrderKey();
		LogUtil.logMessage("OrderKey in AppendixAction:deleteImage:"
				+ objAppendixForm.getHdnOrderKey());
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LogUtil.logMessage(objSession);
			
			if (objSession != null) {
				objLoginVo = (LoginVO) objSession
				.getAttribute(ApplicationConstants.USER_IN_SESSION);
				
			}
			objAppendixVO.setUserID(objLoginVo.getUserID());
			objAppendixVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objAppendixVO.setOrderKey(objAppendixForm.getHdnOrderKey());
			LogUtil.logMessage("ImageSeqNo in AppendixAction:deleteImage"
					+ objAppendixForm.getImageSeqNo());
			objAppendixVO.setImageSeqNo(objAppendixForm.getImageSeqNo());
			
			AppendixBI objAppendixBI = ServiceFactory.getAppendixBO();
			intStatusCode = objAppendixBI.deleteImage(objAppendixVO);
			
			if (intStatusCode == 0) {
				objAppendixForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			}
			
			if (intStatusCode > 0) {
				objAppendixForm.setMessageID("" + intStatusCode);
			}
			
			objSectionVO.setUserID(objLoginVo.getUserID());
			
			objSectionVO.setOrderKey(objAppendixForm.getHdnOrderKey());
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			LogUtil
			.logMessage("Size of SectionList in AppendixAction:modifyImageName:"
					+ arlSectionList.size());
			if (arlSectionList != null) {
				objAppendixForm.setSectionList(arlSectionList);
			}
			
			/**
			 * ****** This part of code is used to Display the Spec
			 * status,customer name,SAP customer code and Order Number*****
			 */
			LogUtil.logMessage("*******OrderKey inside Fetch orders:"
					+ objAppendixForm.getHdnOrderKey());
			objOrderVO.setOrderKey(objAppendixForm.getHdnOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			LogUtil
			.logMessage("Size of OrderList in AppendixAction:modifyImageName:"
					+ arlOrderList.size());
			if (arlOrderList != null) {
				objAppendixForm.setOrderList(arlOrderList);
			}
			
			arlImageList = objAppendixBI.fetchImage(objAppendixVO);
			if (arlImageList != null) {
				objAppendixForm.setImageList(arlImageList);
			}
			
			/*******************************************************************
			 * Setting The hidden orderKey into the form property (Orderkey)
			 * b'coz all the links getting the orderKey Value from the Orderkey
			 * property of the <html:link>
			 */
			
			objAppendixForm.setOrderKey(objAppendixForm.getHdnOrderKey());
			objAppendixForm.setClauseSeqNo(0);
			objAppendixForm.setVersionNo(0);
			
			// Added for CR-65
			objAppendixForm.setHdnRevViewCode(objAppendixForm
					.getHdnRevViewCode());
			
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in AppendixAction..");
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
	 * This method is used to Map the Appendix Image with clause selected at
	 * orderLevel
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
	
	public ActionForward saveMappings(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into AppendixAction:saveMappings");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlOrderList = new ArrayList();
		SectionVO objSectionVO = new SectionVO();
		ArrayList arlImageList = new ArrayList();
		AppendixVO objAppendixVO = new AppendixVO();
		AppendixForm objAppendixForm = (AppendixForm) objActionForm;
		OrderVO objOrderVO = new OrderVO();
		LoginVO objLoginVo = null;
		FormFile objFormFile = null;
		int intOrdeKey = 0;
		int intStatusCode;
		
		intOrdeKey = objAppendixForm.getHdnOrderKey();
		LogUtil.logMessage("OrderKey in AppendixAction:saveMappings:"
				+ objAppendixForm.getHdnOrderKey());
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LogUtil.logMessage(objSession);
			
			if (objSession != null) {
				objLoginVo = (LoginVO) objSession
				.getAttribute(ApplicationConstants.USER_IN_SESSION);
				
			}
			LogUtil.logMessage("ImageSeqNo in AppendixAction:saveMappings"
					+ objAppendixForm.getImageSeqNo());
			LogUtil.logMessage("ClauseSeqNo in AppendixAction:saveMappings"
					+ objAppendixForm.getClauseSeqNo());
			LogUtil.logMessage("VersionNo in AppendixAction:saveMappings"
					+ objAppendixForm.getVersionNo());
			objAppendixVO.setOrderKey(objAppendixForm.getHdnOrderKey());
			objAppendixVO.setImageSeqNo(objAppendixForm.getImageSeqNo());
			objAppendixVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objAppendixVO.setClauseSeqNo(objAppendixForm.getClauseSeqNo());
			objAppendixVO.setVersionNo(objAppendixForm.getVersionNo());
			objAppendixVO.setUserID(objLoginVo.getUserID());
			
			AppendixBI objAppendixBI = ServiceFactory.getAppendixBO();
			intStatusCode = objAppendixBI.saveMappings(objAppendixVO);
			
			if (intStatusCode == 0) {
				objAppendixForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				objAppendixForm.setClauseSeqNo(0);
				objAppendixForm.setVersionNo(0);
			}
			
			if (intStatusCode > 0) {
				objAppendixForm.setMessageID("" + intStatusCode);
			}
			
			objSectionVO.setUserID(objLoginVo.getUserID());
			
			objSectionVO.setOrderKey(objAppendixForm.getHdnOrderKey());
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			LogUtil
			.logMessage("Size of SectionList in AppendixAction:saveMappings:"
					+ arlSectionList.size());
			if (arlSectionList != null) {
				objAppendixForm.setSectionList(arlSectionList);
			}
			
			/**
			 * ****** This part of code is used to Display the Spec
			 * status,customer name,SAP customer code and Order Number*****
			 */
			LogUtil.logMessage("*******OrderKey inside Fetch orders:"
					+ objAppendixForm.getHdnOrderKey());
			objOrderVO.setOrderKey(objAppendixForm.getHdnOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			LogUtil
			.logMessage("Size of OrderList in AppendixAction:saveMappings:"
					+ arlOrderList.size());
			if (arlOrderList != null) {
				objAppendixForm.setOrderList(arlOrderList);
			}
			
			arlImageList = objAppendixBI.fetchImage(objAppendixVO);
			if (arlImageList != null) {
				objAppendixForm.setImageList(arlImageList);
			}
			
			/*******************************************************************
			 * Setting The hidden orderKey into the form property (Orderkey)
			 * b'coz all the links getting the orderKey Value from the Orderkey
			 * property of the <html:link>
			 */
			
			objAppendixForm.setOrderKey(objAppendixForm.getHdnOrderKey());
			
			// Added for CR-65
			objAppendixForm.setHdnRevViewCode(objAppendixForm
					.getHdnRevViewCode());
			
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in AppendixAction..");
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
	 * This method is used to Turn ON/OFF appendix button to decide whether
	 * Appendix Images are displayed in PDF or not.
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
	
	public ActionForward turnAppendix(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into AppendixAction:turnAppendix");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlOrderList = new ArrayList();
		SectionVO objSectionVO = new SectionVO();
		ArrayList arlImageList = new ArrayList();
		AppendixVO objAppendixVO = new AppendixVO();
		AppendixForm objAppendixForm = (AppendixForm) objActionForm;
		OrderVO objOrderVO = new OrderVO();
		LoginVO objLoginVo = null;
		FormFile objFormFile = null;
		int intOrdeKey = 0;
		int intStatusCode;
		
		intOrdeKey = objAppendixForm.getHdnOrderKey();
		LogUtil.logMessage("OrderKey in AppendixAction:turnAppendix:"
				+ objAppendixForm.getHdnOrderKey());
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LogUtil.logMessage(objSession);
			
			if (objSession != null) {
				objLoginVo = (LoginVO) objSession
				.getAttribute(ApplicationConstants.USER_IN_SESSION);
				
			}
			objAppendixVO.setUserID(objLoginVo.getUserID());
			objAppendixVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objAppendixVO.setOrderKey(objAppendixForm.getHdnOrderKey());
			objAppendixVO.setImageName(objAppendixForm.getAppImageName());
			objAppendixVO.setImageDesc(objAppendixForm.getAppImageDesc());
			objAppendixVO.setImageSeqNo(objAppendixForm.getImageSeqNo());
			
			objOrderVO.setOrderKey(objAppendixForm.getHdnOrderKey());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			LogUtil.logMessage("Appendix Flag in AppendixAction:turnAppendix"
					+ objAppendixForm.getToggleFlag());
			objOrderVO.setAppendixFlag(objAppendixForm.getToggleFlag());
			objOrderVO.setUserID(objLoginVo.getUserID());
			
			AppendixBI objAppendixBI = ServiceFactory.getAppendixBO();
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			
			/**
			 * ****** This part of code is used to Turn ON/OFF flag in Order
			 * Table to check whether appendix images are needs to be displayed
			 * in PDF or not.
			 */
			
			intStatusCode = objOrderBI.updateOrders(objOrderVO);
			
			if (intStatusCode == 0) {
				objAppendixForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			}
			
			if (intStatusCode > 0) {
				objAppendixForm.setMessageID("" + intStatusCode);
			}
			
			objSectionVO.setUserID(objLoginVo.getUserID());
			objSectionVO.setOrderKey(objAppendixForm.getHdnOrderKey());
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			LogUtil
			.logMessage("Size of SectionList in AppendixAction:turnAppendix:"
					+ arlSectionList.size());
			if (arlSectionList != null) {
				objAppendixForm.setSectionList(arlSectionList);
			}
			
			/**
			 * ****** This part of code is used to Display the Spec
			 * status,customer name,SAP customer code and Order Number*****
			 */
			LogUtil.logMessage("*******OrderKey inside Fetch orders:"
					+ objAppendixForm.getHdnOrderKey());
			objOrderVO.setOrderKey(objAppendixForm.getHdnOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			
			LogUtil
			.logMessage("Size of OrderList in AppendixAction:turnAppendix:"
					+ arlOrderList.size());
			if (arlOrderList != null) {
				objAppendixForm.setOrderList(arlOrderList);
			}
			
			arlImageList = objAppendixBI.fetchImage(objAppendixVO);
			if (arlImageList != null) {
				objAppendixForm.setImageList(arlImageList);
			}
			
			/*******************************************************************
			 * Setting The hidden orderKey into the form property (Orderkey)
			 * b'coz all the links getting the orderKey Value from the Orderkey
			 * property of the <html:link>
			 */
			
			objAppendixForm.setOrderKey(objAppendixForm.getHdnOrderKey());
			objAppendixForm.setClauseSeqNo(0);
			objAppendixForm.setVersionNo(0);
			
			// Added for CR-65
			objAppendixForm.setHdnRevViewCode(objAppendixForm
					.getHdnRevViewCode());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in AppendixAction..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		
		return objActionMapping.findForward(strForwardKey);
	}
//	Added for CR_91 to view Model Appendix images @ Order level
	/*******************************************************************************************
	 *  * * *		This Method is used to fetch Model Appendix Images 
	 *  
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return     	ActionForward  		   
	 * @throws     	EMDException
	 
	 ******************************************************************************************/
	
	public ActionForward fetchModelAppendixImages(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil
		.logMessage("Entering AppendixAction:fetchModelAppendixImages");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList arlImageList = null;
		
		AppendixForm objAppendixForm = (AppendixForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		LogUtil.logMessage(objLoginVo);
		String strModelSeqNo = null;
		String strModelName = null;
		try {
			
			strModelSeqNo = (String) objHttpServletRequest.getParameter("modelSeqNo");
			objSession.setAttribute("MODEL_SEQ_NO",strModelSeqNo);
			strModelName = (String) objHttpServletRequest.getParameter("modelName");
			AppendixVO objAppendixVO = new AppendixVO();
			LogUtil.logMessage("Model Seq no "+strModelSeqNo + " Model Name "+ strModelName);
			objAppendixForm.setModelSeqNo(strModelSeqNo);
			objAppendixForm.setModelName(strModelName);
			objAppendixVO.setModelSeqNo(strModelSeqNo);
			objAppendixVO.setModelName(strModelName);
			objAppendixVO.setUserID(objLoginVo.getUserID());
			
			AppendixBI objAppendixBO = ServiceFactory
			.getAppendixBO();
			arlImageList = objAppendixBO
			.fetchModelAppendixImages(objAppendixVO);
			
			if (arlImageList != null && arlImageList.size() > 0) {
				LogUtil.logMessage("Array list not empty.....");
				objAppendixForm.setImageList(arlImageList);
				objAppendixForm.setModelName(objAppendixForm
						.getModelName());
				objAppendixForm.setModelSeqNo(objAppendixForm
						.getModelSeqNo());
				
			}
			
			if (arlImageList.size() == 0) {
				LogUtil.logMessage("ArrayList empty.....");
				objAppendixForm.setModelName(objAppendixForm
						.getModelName());
				objAppendixForm.setMethod("NoAppendixImage");
				objAppendixForm.setModelSeqNo(objAppendixForm
						.getModelSeqNo());
			}
			LogUtil.logMessage("Leaving Appendix Action: fetchModelAppendixImages");
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in Appendix Action..fetchModelAppendixImages");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
}