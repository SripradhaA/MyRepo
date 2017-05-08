/*
 * Created on Jun 21, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.action.MasterMaintenance;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.EMD.LSDB.action.SpecMaintenance.OrderSpecificationAction;
import com.EMD.LSDB.action.common.EMDAction;
import com.EMD.LSDB.action.common.PDFView;
import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.ModelBI;
import com.EMD.LSDB.bo.serviceinterface.ModelSpecificationBI;
import com.EMD.LSDB.bo.serviceinterface.SpecTypeBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.form.MasterMaintenance.ModelSpecificationForm;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.SpecTypeVo;
import com.EMD.LSDB.vo.common.SpecificationItemVO;
import com.EMD.LSDB.vo.common.SpecificationVO;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfDestination;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfOutline;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEvent;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This Action class has the business methods for the Model
 *          Specification
 ******************************************************************************/
/***************************************************************************
 * ------------------------------------------------------------------------------------------------------
 *     Date         version  modified by        comments                              Remarks 
 * 19/01/2010        1.0      SD41630       Updated for handling null pointer       Added for CR_81
 * ----------------------------------------------------------------------------------------------------------
 * 15/02/2010        2.0      RR68151       Updated for merging Clause Screens        Added for CR_83
 * 													 	 
 * 16/02/2010        2.1      SD41630    Added  for Model level, SpecificationType,   Added for CR_83
 * 										 Model, Section and Sub Section of values be 	
 *                                       maintain in the session and display in the
 *                                       screen where ever occurred
 * --------------------------------------------------------------------------------------------------------
  **************************************************************************/
public class ModelSpecificationAction extends EMDAction implements PdfPageEvent  {
	
	/***************************************************************************
	 * This method is for loading the spec types
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
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil.logMessage("Entering ModelSpecificationAction.initLoad");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ModelSpecificationForm objModelSpecificationForm = (ModelSpecificationForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		int specTypeNo = -1;
		int modleSeqNo = -1;
		String strSpecTypeNo = null;
		String strModleSeqNo = null;
		ArrayList arlModelList = null;
		try {
			
			
//			CR_83 lines are added ahere 
			strSpecTypeNo = (String) objHttpServletRequest
			.getParameter("specTypeNo");
	strModleSeqNo = (String) objHttpServletRequest
			.getParameter("modelSeqNo");
	
	if (strSpecTypeNo != null) {
		LogUtil
				.logMessage(" Ends From request***********************************111");
		objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);

		specTypeNo = Integer.parseInt(strSpecTypeNo);
		LogUtil.logMessage("value of strSpecTypeNo from  session"
				+ strSpecTypeNo);
		objModelSpecificationForm.setSpecTypeNo(specTypeNo);

		LogUtil
				.logMessage(" Ends From request***********************************2222");
	} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
		if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {

			LogUtil
					.logMessage(" Ends From request***********************************3333");
			strSpecTypeNo = (String) objSession
					.getAttribute("SPEC_TYPE_NO");
			specTypeNo = Integer.parseInt(strSpecTypeNo);
			objModelSpecificationForm.setSpecTypeNo(specTypeNo);
			LogUtil
					.logMessage(" Ends From request***********************************444");

		}
	}

	if (strModleSeqNo != null) {

		objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
		modleSeqNo = Integer.parseInt(strModleSeqNo);
		LogUtil.logMessage("value of MODEL_SEQ_NO from session "
				+ modleSeqNo);
		objModelSpecificationForm.setModelSeqNo(modleSeqNo);

	} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {

		if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
			strModleSeqNo = (String) objSession
					.getAttribute("MODEL_SEQ_NO");
			modleSeqNo = Integer.parseInt(strModleSeqNo);
			objModelSpecificationForm.setModelSeqNo(modleSeqNo);
		}
	}
	//Added For CR_114 Models not loading
	if (specTypeNo != -1 && specTypeNo != 0) {

		LogUtil.logMessage("in side modleSeqNo" + modleSeqNo);

		ModelVo objModelVo = new ModelVo();
		objModelVo.setUserID(objLoginVo.getUserID());
		objModelVo.setSpecTypeSeqNo(specTypeNo);
		ModelBI objModelBO = ServiceFactory.getModelBO();
		if (objModelBO.fetchModels(objModelVo) != null) {
			arlModelList = objModelBO.fetchModels(objModelVo);
			objModelSpecificationForm.setModelList(arlModelList);
			objModelSpecificationForm.setModelSeqNo(modleSeqNo);
			LogUtil.logMessage("end of the if modleSeqNo" + modleSeqNo);
		}

	}	
	//CR_83 lines area ends here
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpec);
			objModelSpecificationForm.setSpecTypeList(arlSpec);
			// objModelMaintForm.setHideMessage("fromInitLoad");
			
		} catch (Exception objExp) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***************************************************************************
	 * This method is for loading the the specifiaction home page
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
	
	public ActionForward fetchModels(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil.logMessage("Entering ModelSpecificationAction.fetchModels");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ModelSpecificationForm objModelSpecificationForm = (ModelSpecificationForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		try {
			
			/**
			 * **Added for CR-46 PM&I Spec for loading Specification Type Drop
			 * Down - Starts Here********
			 */
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpec);
			objModelSpecificationForm.setSpecTypeList(arlSpec);
			objModelSpecificationForm.setSpecTypeNo(objModelSpecificationForm
					.getSpecTypeNo());
			objModelSpecificationForm
			.setHdnSelSpecType(objModelSpecificationForm
					.getHdnSelSpecType());
			/**
			 * **Added for CR-46 PM&I Spec for loading Specification Type Drop
			 * Down - Ends Here***
			 */
			
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			
			// Added for CR- 46 PM&I Spec to load Models based on Spec Type
			objModelVo.setSpecTypeSeqNo(objModelSpecificationForm
					.getSpecTypeNo());
			
			ModelBI objModelBO = ServiceFactory.getModelBO();
			ArrayList arlModel = objModelBO.fetchModels(objModelVo);
			objModelSpecificationForm.setModelList(arlModel);
			LogUtil.logMessage("objModelSpecificationForm.getMethod() :"
					+ objModelSpecificationForm.getMethod());
		} catch (Exception objExp) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***************************************************************************
	 * This method is for loading the add specification screen
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
	
	public ActionForward initLoadSpecification(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil
		.logMessage("Entering ModelSpecificationAction.initLoadSpecification");
		String strForwardKey = "";
		ModelSpecificationForm objModelSpecificationForm = (ModelSpecificationForm) objActionForm;
		try {
			LogUtil.logMessage("modelSpecificationForm.getHdnModelName() :"
					+ objModelSpecificationForm.getHdnModelName());
			objModelSpecificationForm.setHdnModelName(objModelSpecificationForm
					.getHdnModelName());
			// This is for clear fields
			objModelSpecificationForm.setModSpecName("");
			objModelSpecificationForm.setModSpecItemDesc("");
			objModelSpecificationForm.setModSpecItemValue("");
			objModelSpecificationForm.setSpecTypeNo(objModelSpecificationForm
					.getSpecTypeNo());
			// Ends
			strForwardKey = ApplicationConstants.SPEC_FORWARD;
		} catch (Exception objExp) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***************************************************************************
	 * This method is for adding new specification for a model
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
	
	public ActionForward insertSpecification(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil
		.logMessage("Entering ModelSpecificationAction.insertSpecification");
		String strForwardKey = "";
		int intSuccess = 0;
		ModelSpecificationForm objModelSpecificationForm = (ModelSpecificationForm) objActionForm;
		SpecificationVO objSpecificationVO = new SpecificationVO();
		SpecificationItemVO objSpecificationItemVO = new SpecificationItemVO();
		String strUserID = "";
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			
			LogUtil.logMessage("modelSpecificationForm.getHdnModelName() :"
					+ objModelSpecificationForm.getHdnModelName());
			
			LogUtil.logMessage("modelSpecificationForm.getModelSeq no() :"
					+ objModelSpecificationForm.getModelSeqNo());
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			// Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			
			// objModelSpecificationForm.setHdnModelName(objModelSpecificationForm.getHdnModelName());
			objSpecificationVO.setModelSeqNo(objModelSpecificationForm
					.getModelSeqNo());
			objSpecificationVO.setSpecName(ApplicationUtil
					.trim(objModelSpecificationForm.getModSpecName()));
			
			objSpecificationItemVO.setModSpecItemDesc(ApplicationUtil
					.trim(objModelSpecificationForm.getModSpecItemDesc()));
			objSpecificationItemVO.setModSpecItemValue(ApplicationUtil
					.trim(objModelSpecificationForm.getModSpecItemValue()));
			objSpecificationItemVO.setUserID(strUserID);
			objSpecificationVO.setUserID(strUserID);
			objSpecificationVO.setSpecificationItem(objSpecificationItemVO);
			ModelSpecificationBI objModelSpecificationBI = ServiceFactory
			.getModelSpecificationBO();
			
			LogUtil
			.logMessage("before calling insertSpecification ModelSeq no() :"
					+ objSpecificationVO.getModelSeqNo());
			intSuccess = objModelSpecificationBI
			.insertSpecification(objSpecificationVO);
			
			LogUtil.logMessage("blnSuccess in Action ....." + intSuccess);
			objModelSpecificationForm.setHdnModelName(objModelSpecificationForm
					.getHdnModelName());
			if (intSuccess == 0) {
				objModelSpecificationForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				objModelSpecificationForm.setModSpecName("");
				objModelSpecificationForm.setModSpecItemDesc("");
				objModelSpecificationForm.setModSpecItemValue("");
				objModelSpecificationForm
				.setSpecTypeNo(objModelSpecificationForm
						.getSpecTypeNo());
				objModelSpecificationForm
				.setModelSeqNo(objModelSpecificationForm
						.getModelSeqNo());
				
			} else {
				objModelSpecificationForm.setMessageID("" + intSuccess);
			}
			
			strForwardKey = ApplicationConstants.SPEC_FORWARD;
		}
		
		catch (Exception objExp) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***************************************************************************
	 * This method is for loading the add specification screen
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
	
	public ActionForward displayModifiedSpecification(
			ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil
		.logMessage("Entering ModelSpecificationAction.displayModifiedSpecification");
		String strForwardKey = "";
		ModelSpecificationForm objModelSpecificationForm = (ModelSpecificationForm) objActionForm;
		try {
			LogUtil.logMessage("modelSpecificationForm.getHdnModelName() :"
					+ objModelSpecificationForm.getHdnModelName());
			objModelSpecificationForm.setHdnModelName(objModelSpecificationForm
					.getHdnModelName());
			// This is for clear fields
			objModelSpecificationForm.setHdnSpecSeqNo(objModelSpecificationForm
					.getHdnSpecSeqNo());
			objModelSpecificationForm.setModSpecName(objModelSpecificationForm
					.getHdnSpecName());
			objModelSpecificationForm.setSpecTypeNo(objModelSpecificationForm
					.getSpecTypeNo());
			// Ends
			strForwardKey = ApplicationConstants.MODIFY_MODEL_SPEC_FORWARD;
		} catch (Exception objExp) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***************************************************************************
	 * Added for LSDB_CR-64 This method is for updating the Specification
	 * 
	 * @author Ka57588
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws BusinessException
	 **************************************************************************/
	
	public ActionForward updateSpecification(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil
		.logMessage("Entering ModelSpecificationAction.updateSpecification");
		String strForwardKey = "";
		int intSuccess = 0;
		ModelSpecificationForm objModelSpecificationForm = (ModelSpecificationForm) objActionForm;
		SpecificationVO objSpecificationVO = new SpecificationVO();
		SpecificationItemVO objSpecificationItemVO = new SpecificationItemVO();
		String strUserID = "";
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			
			LogUtil.logMessage("modelSpecificationForm.getHdnModelName() :"
					+ objModelSpecificationForm.getHdnModelName());
			
			LogUtil.logMessage("modelSpecificationForm.getModelSeq no() :"
					+ objModelSpecificationForm.getModelSeqNo());
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			// Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			
			// objModelSpecificationForm.setHdnModelName(objModelSpecificationForm.getHdnModelName());
			objSpecificationVO.setModelSeqNo(objModelSpecificationForm
					.getModelSeqNo());
			objSpecificationVO.setSpecName(ApplicationUtil
					.trim(objModelSpecificationForm.getModSpecName()));
			objSpecificationVO.setSpecSeqNo(objModelSpecificationForm
					.getHdnSpecSeqNo());
			objSpecificationVO.setUserID(strUserID);
			
			ModelSpecificationBI objModelSpecificationBI = ServiceFactory
			.getModelSpecificationBO();
			
			intSuccess = objModelSpecificationBI
			.updateSpecification(objSpecificationVO);
			
			LogUtil.logMessage("blnSuccess in Action ....." + intSuccess);
			objModelSpecificationForm.setHdnModelName(objModelSpecificationForm
					.getHdnModelName());
			if (intSuccess == 0) {
				objModelSpecificationForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				
			} else {
				objModelSpecificationForm.setMessageID("" + intSuccess);
			}
			objModelSpecificationForm.setSpecTypeNo(objModelSpecificationForm
					.getSpecTypeNo());
			objModelSpecificationForm.setModelSeqNo(objModelSpecificationForm
					.getModelSeqNo());
			objModelSpecificationForm.setHdnSpecSeqNo(objModelSpecificationForm
					.getHdnSpecSeqNo());
			strForwardKey = ApplicationConstants.MODIFY_MODEL_SPEC_FORWARD;
		}
		
		catch (Exception objExp) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***************************************************************************
	 * This method is for loading the add specification item screen
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
	
	public ActionForward initLoadSpecificationItem(
			ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil
		.logMessage("Entering ModelSpecificationAction.initLoadSpecificationItem");
		String strForwardKey = "";
		ModelSpecificationForm objModelSpecificationForm = (ModelSpecificationForm) objActionForm;
		try {
			LogUtil.logMessage("modelSpecificationForm.getHdnModelName() :"
					+ objModelSpecificationForm.getHdnModelName());
			objModelSpecificationForm.setHdnModelName(objModelSpecificationForm
					.getHdnModelName());
			objModelSpecificationForm.setHdnSpecName(objModelSpecificationForm
					.getHdnSpecName());
			objModelSpecificationForm.setHdnSpecSeqNo(objModelSpecificationForm
					.getHdnSpecSeqNo());
			
			// This is for clearing the fields
			objModelSpecificationForm.setModSpecItemDesc("");
			objModelSpecificationForm.setModSpecItemValue("");
			// Ends
			strForwardKey = ApplicationConstants.SPEC_ITEM_FORWARD;
		} catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***************************************************************************
	 * This method is for adding new item for a specification
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
	
	public ActionForward insertSpecificationItem(
			ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil
		.logMessage("Entering ModelSpecificationAction.insertSpecificationItem");
		String strForwardKey = ApplicationConstants.SPEC_ITEM_FORWARD;
		int intSuccess = 0;
		ModelSpecificationForm objModelSpecificationForm = (ModelSpecificationForm) objActionForm;
		SpecificationVO objSpecificationVO = new SpecificationVO();
		SpecificationItemVO objSpecificationItemVO = new SpecificationItemVO();
		String strUserID = "";
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			// Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			
			// objModelSpecificationForm.setHdnModelName(objModelSpecificationForm.getHdnModelName());
			objSpecificationVO.setSpecSeqNo(objModelSpecificationForm
					.getHdnSpecSeqNo());
			objSpecificationItemVO.setModSpecItemDesc(ApplicationUtil
					.trim(objModelSpecificationForm.getModSpecItemDesc()));
			objSpecificationItemVO.setModSpecItemValue(ApplicationUtil
					.trim(objModelSpecificationForm.getModSpecItemValue()));
			objSpecificationVO.setUserID(strUserID);
			objSpecificationVO.setSpecificationItem(objSpecificationItemVO);
			ModelSpecificationBI objModelSpecificationBI = ServiceFactory
			.getModelSpecificationBO();
			
			intSuccess = objModelSpecificationBI
			.insertSpecificationItem(objSpecificationVO);
			
			objModelSpecificationForm.setHdnModelName(objModelSpecificationForm
					.getHdnModelName());
			objModelSpecificationForm.setHdnSpecName(objModelSpecificationForm
					.getHdnSpecName());
			if (intSuccess == 0) {
				objModelSpecificationForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				objModelSpecificationForm.setModSpecItemDesc("");
				objModelSpecificationForm.setModSpecItemValue("");
				objModelSpecificationForm
				.setSpecTypeNo(objModelSpecificationForm
						.getSpecTypeNo());
				objModelSpecificationForm
				.setModelSeqNo(objModelSpecificationForm
						.getModelSeqNo());
				
			} else {
				objModelSpecificationForm.setMessageID("" + intSuccess);
			}
			
		} catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***************************************************************************
	 * Added for LSDB_CR-64 This method is for deleting the Specification
	 * 
	 * @author Ka57588
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws BusinessException
	 **************************************************************************/
	
	public ActionForward deleteSpecification(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil
		.logMessage("Entering ModelSpecificationAction.deleteSpecification");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intSuccess = 0;
		ModelSpecificationForm objModelSpecificationForm = (ModelSpecificationForm) objActionForm;
		SpecificationVO objSpecificationVO = new SpecificationVO();
		String strUserID = null;
		try {
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpecType = new ArrayList();
			
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpecType = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			
			objModelSpecificationForm.setSpecTypeList(arlSpecType);
			objModelSpecificationForm.setSpecTypeNo(objModelSpecificationForm
					.getSpecTypeNo());
			objModelSpecificationForm
			.setHdnSelSpecType(objModelSpecificationForm
					.getHdnSelSpecType());
			
			ModelVo objModelVo = new ModelVo();
			
			objModelVo.setUserID(objLoginVo.getUserID());
			
			objModelVo.setSpecTypeSeqNo(objModelSpecificationForm
					.getSpecTypeNo());
			
			ModelBI objModelBO = ServiceFactory.getModelBO();
			ArrayList arlModel = objModelBO.fetchModels(objModelVo);
			objModelSpecificationForm.setModelList(arlModel);
			objModelSpecificationForm.setModelSeqNo(objModelSpecificationForm
					.getModelSeqNo());
			// Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			
			objSpecificationVO.setModelSeqNo(objModelSpecificationForm
					.getModelSeqNo());
			
			objSpecificationVO.setSpecSeqNo(objModelSpecificationForm
					.getHdnSpecSeqNo());
			objSpecificationVO.setUserID(strUserID);
			LogUtil.logMessage("strUserID :" + strUserID);
			ModelSpecificationBI objModelSpecificationBI = ServiceFactory
			.getModelSpecificationBO();
			
			intSuccess = objModelSpecificationBI
			.deleteSpecification(objSpecificationVO);
			objModelSpecificationForm
			.setHdnPrevSelModel(objModelSpecificationForm
					.getModelSeqNo());
			
			objModelSpecificationForm.setHdnModelName(objModelSpecificationForm
					.getHdnModelName());
			objModelSpecificationForm.setHdnSpecName(objModelSpecificationForm
					.getHdnSpecName());
			
			ModelSpecificationBI objModelSpecificationBO = ServiceFactory
			.getModelSpecificationBO();
			ArrayList arlSpec = objModelSpecificationBO
			.fetchSpecificationItems(objSpecificationVO);
			objModelSpecificationForm.setModSpecList(arlSpec);
			
			if (intSuccess == 0) {
				objModelSpecificationForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objModelSpecificationForm.setMessageID("" + intSuccess);
			}
			
		} catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***************************************************************************
	 * This method is for updating item for a specification
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
	
	public ActionForward updateSpecificationItem(
			ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil
		.logMessage("Entering ModelSpecificationAction.updateSpecificationItem");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intSuccess = 0;
		ModelSpecificationForm objModelSpecificationForm = (ModelSpecificationForm) objActionForm;
		SpecificationVO objSpecificationVO = new SpecificationVO();
		SpecificationItemVO objSpecificationItemVO = new SpecificationItemVO();
		
		String strUserID = null;
		try {
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			/**
			 * **Added for CR-46 PM&I Spec for loading Specification Type Drop
			 * Down - Starts Here********
			 */
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpecType = new ArrayList();
			
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpecType = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpecType);
			objModelSpecificationForm.setSpecTypeList(arlSpecType);
			objModelSpecificationForm.setSpecTypeNo(objModelSpecificationForm
					.getSpecTypeNo());
			objModelSpecificationForm
			.setHdnSelSpecType(objModelSpecificationForm
					.getHdnSelSpecType());
			/**
			 * **Added for CR-46 PM&I Spec for loading Specification Type Drop
			 * Down - Ends Here***
			 */
			
			ModelVo objModelVo = new ModelVo();
			
			objModelVo.setUserID(objLoginVo.getUserID());
			
			// Added for CR-46
			objModelVo.setSpecTypeSeqNo(objModelSpecificationForm
					.getSpecTypeNo());
			
			ModelBI objModelBO = ServiceFactory.getModelBO();
			ArrayList arlModel = objModelBO.fetchModels(objModelVo);
			objModelSpecificationForm.setModelList(arlModel);
			objModelSpecificationForm.setModelSeqNo(objModelSpecificationForm
					.getModelSeqNo());
			// Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			
			objSpecificationVO.setModelSeqNo(objModelSpecificationForm
					.getModelSeqNo());
			
			LogUtil.logMessage("fetchSpecificationItems method ModelSeqNo :"
					+ objSpecificationVO.getModelSeqNo());
			
			objSpecificationItemVO
			.setModSpecItemSeqNo(objModelSpecificationForm
					.getModSpecItemSeqNo());
			objSpecificationItemVO.setModSpecItemDesc(objModelSpecificationForm
					.getHdnSpecItemDesc());
			objSpecificationItemVO
			.setModSpecItemValue(objModelSpecificationForm
					.getHdnSpecItemValue());
			
			objSpecificationVO.setSpecificationItem(objSpecificationItemVO);
			objSpecificationVO.setUserID(strUserID);
			LogUtil.logMessage("strUserID :" + strUserID);
			ModelSpecificationBI objModelSpecificationBI = ServiceFactory
			.getModelSpecificationBO();
			
			intSuccess = objModelSpecificationBI
			.updateSpecificationItem(objSpecificationVO);
			objModelSpecificationForm
			.setHdnPrevSelModel(objModelSpecificationForm
					.getModelSeqNo());
			
			objModelSpecificationForm.setHdnModelName(objModelSpecificationForm
					.getHdnModelName());
			objModelSpecificationForm.setHdnSpecName(objModelSpecificationForm
					.getHdnSpecName());
			
			ModelSpecificationBI objModelSpecificationBO = ServiceFactory
			.getModelSpecificationBO();
			ArrayList arlSpec = objModelSpecificationBO
			.fetchSpecificationItems(objSpecificationVO);
			objModelSpecificationForm.setModSpecList(arlSpec);
			
			if (intSuccess == 0) {
				objModelSpecificationForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objModelSpecificationForm.setMessageID("" + intSuccess);
			}
			
		} catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***************************************************************************
	 * Added for LSDB_CR-64 This method is for deleting the Specification item
	 * 
	 * @author Ka57588
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws BusinessException
	 **************************************************************************/
	
	public ActionForward deleteSpecificationItem(
			ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil
		.logMessage("Entering ModelSpecificationAction.deleteSpecificationItem");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intSuccess = 0;
		ModelSpecificationForm objModelSpecificationForm = (ModelSpecificationForm) objActionForm;
		SpecificationVO objSpecificationVO = new SpecificationVO();
		SpecificationItemVO objSpecificationItemVO = new SpecificationItemVO();
		
		String strUserID = null;
		try {
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpecType = new ArrayList();
			
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpecType = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpecType);
			objModelSpecificationForm.setSpecTypeList(arlSpecType);
			objModelSpecificationForm.setSpecTypeNo(objModelSpecificationForm
					.getSpecTypeNo());
			objModelSpecificationForm
			.setHdnSelSpecType(objModelSpecificationForm
					.getHdnSelSpecType());
			
			ModelVo objModelVo = new ModelVo();
			
			objModelVo.setUserID(objLoginVo.getUserID());
			
			objModelVo.setSpecTypeSeqNo(objModelSpecificationForm
					.getSpecTypeNo());
			
			ModelBI objModelBO = ServiceFactory.getModelBO();
			ArrayList arlModel = objModelBO.fetchModels(objModelVo);
			objModelSpecificationForm.setModelList(arlModel);
			objModelSpecificationForm.setModelSeqNo(objModelSpecificationForm
					.getModelSeqNo());
			// Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			
			objSpecificationVO.setModelSeqNo(objModelSpecificationForm
					.getModelSeqNo());
			
			LogUtil.logMessage("fetchSpecificationItems method ModelSeqNo :"
					+ objSpecificationVO.getModelSeqNo());
			
			objSpecificationItemVO
			.setModSpecItemSeqNo(objModelSpecificationForm
					.getModSpecItemSeqNo());
			objSpecificationVO.setSpecificationItem(objSpecificationItemVO);
			objSpecificationVO.setUserID(strUserID);
			LogUtil.logMessage("strUserID :" + strUserID);
			ModelSpecificationBI objModelSpecificationBI = ServiceFactory
			.getModelSpecificationBO();
			
			intSuccess = objModelSpecificationBI
			.deleteSpecificationItem(objSpecificationVO);
			objModelSpecificationForm
			.setHdnPrevSelModel(objModelSpecificationForm
					.getModelSeqNo());
			
			objModelSpecificationForm.setHdnModelName(objModelSpecificationForm
					.getHdnModelName());
			objModelSpecificationForm.setHdnSpecName(objModelSpecificationForm
					.getHdnSpecName());
			
			ModelSpecificationBI objModelSpecificationBO = ServiceFactory
			.getModelSpecificationBO();
			ArrayList arlSpec = objModelSpecificationBO
			.fetchSpecificationItems(objSpecificationVO);
			objModelSpecificationForm.setModSpecList(arlSpec);
			
			if (intSuccess == 0) {
				objModelSpecificationForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objModelSpecificationForm.setMessageID("" + intSuccess);
			}
		} catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***************************************************************************
	 * This method is for fetching the SpecificationItems
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
	
	public ActionForward fetchSpecificationItems(
			ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil
		.logMessage("Entering ModelSpecificationAction.fetchSpecificationItems");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ModelSpecificationForm objModelSpecificationForm = (ModelSpecificationForm) objActionForm;
		SpecificationVO objSpecificationVO = new SpecificationVO();
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		int specTypeNo=-1;
		String strSpecTypeNo=null;
		String strModleSeqNo=null;
		try {
			
			/**
			 * **Added for CR-46 PM&I Spec for loading Specification Type Drop
			 * Down - Starts Here********
			 */
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpecType = new ArrayList();
			
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpecType = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpecType);
			objModelSpecificationForm.setSpecTypeList(arlSpecType);
			objModelSpecificationForm.setSpecTypeNo(objModelSpecificationForm
					.getSpecTypeNo());
			objModelSpecificationForm
			.setHdnSelSpecType(objModelSpecificationForm
					.getHdnSelSpecType());
			/**
			 * **Added for CR-46 PM&I Spec for loading Specification Type Drop
			 * Down - Ends Here***
			 */
			
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			
			// Added for CR-46 PM&I Spec
			objModelVo.setSpecTypeSeqNo(objModelSpecificationForm
					.getSpecTypeNo());
			
			ModelBI objModelBO = ServiceFactory.getModelBO();
			ArrayList arlModel = objModelBO.fetchModels(objModelVo);
			objModelSpecificationForm.setModelList(arlModel);
			objModelSpecificationForm.setModelSeqNo(objModelSpecificationForm
					.getModelSeqNo());
			objModelSpecificationForm
			.setHdnPrevSelModel(objModelSpecificationForm
					.getModelSeqNo());
			
			objSpecificationVO.setModelSeqNo(objModelSpecificationForm
					.getModelSeqNo());
			objSpecificationVO.setUserID(objLoginVo.getUserID());
			LogUtil.logMessage("fetchSpecificationItems method ModelSeqNo :"
					+ objSpecificationVO.getModelSeqNo());
			
			ModelSpecificationBI objModelSpecificationBO = ServiceFactory
			.getModelSpecificationBO();
			ArrayList arlSpec = objModelSpecificationBO
			.fetchSpecificationItems(objSpecificationVO);
			objModelSpecificationForm.setModSpecList(arlSpec);
			objModelSpecificationForm.setSpecTypeNo(objModelSpecificationForm
					.getSpecTypeNo());
			objModelSpecificationForm.setModelSeqNo(objModelSpecificationForm
					.getModelSeqNo());
			objModelSpecificationForm
			.setHdnSelSpecType(objModelSpecificationForm
					.getHdnSelSpecType());
			LogUtil.logMessage("objArrayListSpec :" + arlSpec);
//			CR_83 holding the value from form in the sesiion 
			
			//objModelMaintForm.setSpecTypeNo(specTypeNo);
			strSpecTypeNo=(String) objHttpServletRequest.getParameter("specTypeNo");
			strModleSeqNo=(String) objHttpServletRequest.getParameter("modelSeqNo");
		
			objSession.setAttribute("SPEC_TYPE_NO",strSpecTypeNo);
			objSession.setAttribute("MODEL_SEQ_NO",strModleSeqNo);
			
				//CR_83 lines are ends here
		} catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***************************************************************************
	 * Added for LSDB_CR-64 This method is for previewing the Specifcation in
	 * PDF
	 * 
	 * @author Ka57588
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws BusinessException
	 **************************************************************************/
	
	public ActionForward previewSpecificationByPDF(
			ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		LogUtil
		.logMessage("Inside the ModelSpecificationAction:previewSpecificationByPDF");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ModelSpecificationForm objModelSpecificationForm = (ModelSpecificationForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		String strUserID = null;
		String strOrderKey = null;
		String strModelName = "";
		try {
			SpecificationVO objSpecificationVO = new SpecificationVO();
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			// Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			// To get Request ID Values from Servlet Request
			strModelName = objModelSpecificationForm.getHdnModelName();
			LogUtil.logMessage("strModelName :" + strModelName);
			objSpecificationVO.setModelSeqNo(objModelSpecificationForm
					.getModelSeqNo());
			objSpecificationVO.setUserID(strUserID);
			ModelSpecificationBI objModelSpecificationBO = ServiceFactory
			.getModelSpecificationBO();
			ArrayList arlSpec = objModelSpecificationBO
			.fetchSpecificationItems(objSpecificationVO);
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Document document = new Document(PageSize.A4, 7, 0, 40, 100);//Edited for CR-130
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			
			document.addAuthor("EMD-LSDB");
			document.addSubject("EMD-LSDB Specification PDF ");
			document.addCreationDate();
			writer.setPageEvent(new ModelSpecificationAction());
			document.open();
			// showHeaderFooter(document);
			document.newPage();
			
			// Show Header and Footer
			PdfDestination d1 = new PdfDestination(PdfDestination.FIT);
			PdfOutline root = writer.getRootOutline();
			// Generate PDF main document
			generateSpecificationPDF(document, writer, strModelName, arlSpec);
			// showChangeControl(document , writer , d1 ,
			// root,req[i],strUserID,i);
			document.close();
			// setting the content type
			objHttpServletResponse.setContentType("application/pdf");
			objHttpServletResponse.setHeader("Content-disposition",
			"attachment;filename=specification.pdf");
			objHttpServletResponse.setContentLength(baos.size());
			
			// write ByteArrayOutputStream to the ServletOutputStream
			ServletOutputStream out = objHttpServletResponse.getOutputStream();
			baos.writeTo(out);
			out.flush();
			
		} catch (Exception objExp) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
			
		}
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***************************************************************************
	 * Added for LSDB_CR-64 This method is for previewing the Specifcation in
	 * PDF
	 * 
	 * @author Ka57588
	 * @version 1.0
	 * @param document
	 * @param writer
	 * @param strModelName
	 * @param objSpec
	 * @throws Exception
	 **************************************************************************/
	
	private static void generateSpecificationPDF(Document document,
			PdfWriter writer, String strModelName, ArrayList objSpec)
	throws Exception {
		Font strFontNoUnderLine = new Font(Font.TIMES_ROMAN, 12, Font.BOLD,
				Color.BLACK);
		Font strFointSizeNoBold = new Font(Font.TIMES_ROMAN, 12, 0, Color.BLACK);
		Font strFontBOLD = new Font(Font.TIMES_ROMAN, 12, Font.BOLD,
				Color.BLACK);
		Font strFontSizeNoBoldTen = new Font(Font.TIMES_ROMAN, 12, 0,
				Color.BLACK);
		try {
			String strHorsePower = "";
			PdfPTable table = new PdfPTable(20);
			table.setWidthPercentage(85);
			PdfPCell cel = new PdfPCell(new Paragraph(strModelName
					+ " GENERAL INFORMATION AND IDENTIFICATION",
					strFontNoUnderLine));
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(20);
			;
			table.addCell(cel);
			document.add(table);
			
			if (objSpec != null && objSpec.size() > 0) {
				for (int spec = 0; spec < objSpec.size(); spec++) {
					
					SpecificationVO objSpecVO = (SpecificationVO) objSpec
					.get(spec);
					if (spec == 0) {
						strHorsePower = objSpecVO.getHpDesc();
						table = new PdfPTable(20);
						table.setWidthPercentage(85);
						
						cel = new PdfPCell(new Paragraph(strHorsePower,
								strFointSizeNoBold));
						cel.setBorderColor(new Color(255, 255, 255));
						cel.setColspan(20);
						table.addCell(cel);
						document.add(table);
					}
					// This is for adding newline space
					table = new PdfPTable(20);
					table.setWidthPercentage(85);
					cel = new PdfPCell(new Paragraph("", strFontSizeNoBoldTen));// new
					// line
					// add
					cel.setBorderColor(new Color(255, 255, 255));
					cel.setColspan(20);
					table.addCell(cel);
					document.add(table);
					
					table = new PdfPTable(20);
					table.setWidthPercentage(85);
					cel = new PdfPCell(new Paragraph(objSpecVO.getSpecName(),
							strFontBOLD));
					cel.setBorderColor(new Color(255, 255, 255));
					cel.setVerticalAlignment(Element.ALIGN_TOP);
					cel.setLeading(0f, 1.5f);// Added for Underline issue
					cel.setColspan(17);
					table.addCell(cel);
					
					cel = new PdfPCell(new Paragraph("", strFontSizeNoBoldTen));
					cel.setBorderColor(new Color(255, 255, 255));
					cel.setColspan(3);
					table.addCell(cel);
					
					document.add(table);
					
					ArrayList objSpecItem = (ArrayList) objSpecVO.getSpecItem();
					if (objSpecItem != null && objSpecItem.size() > 0) {
						for (int item = 0; item < objSpecItem.size(); item++) {
							
							SpecificationItemVO objSpecItemVO = (SpecificationItemVO) objSpecItem
							.get(item);
							table = new PdfPTable(20);
							table.setWidthPercentage(85);
							
							cel = new PdfPCell(new Paragraph(objSpecItemVO
									.getModSpecItemDesc(), strFointSizeNoBold));
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setColspan(13);
							table.addCell(cel);
							
							cel = new PdfPCell(new Paragraph(objSpecItemVO
									.getModSpecItemValue(),
									strFontSizeNoBoldTen));
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setColspan(7);
							table.addCell(cel);
							document.add(table);
							
						}
					}
				}
			}
		} catch (Exception objExp) {
			throw new Exception(objExp);
		}
	}
	
	/*
	 * Even handler function
	 * 
	 */
	public void onEndPage(PdfWriter writer, Document document) {
		
		/*
		 * PdfContentByte cb1 = writer.getDirectContent(); BaseFont bf1 =
		 * FontFactory.getFont(FontFactory.TIMES_ROMAN,
		 * 12).getCalculatedBaseFont(true);
		 * 
		 * cb1.beginText(); cb1.setFontAndSize(bf1, 12);
		 * cb1.showTextAligned(PdfContentByte.ALIGN_LEFT, "pos", 10, 100 , 0);
		 * cb1.endText();
		 */
		
		Calendar c = Calendar.getInstance();
		
		Font strFointSizeEight = new Font(Font.TIMES_ROMAN, 8, 0, Color.BLACK);
		Rectangle page = document.getPageSize();
		Phrase whole = new Phrase();
		PdfPTable foot = new PdfPTable(1);
		foot.setSpacingAfter(2);
		PdfPCell phraseAfter = new PdfPCell(new Phrase(Chunk.NEWLINE));
		phraseAfter.setBorderColor(new Color(255, 255, 255));
		foot.addCell(phraseAfter);
		
		phraseAfter = new PdfPCell(new Phrase(Chunk.NEWLINE));
		phraseAfter.setBorderColor(new Color(255, 255, 255));
		foot.addCell(phraseAfter);
		
		phraseAfter = new PdfPCell(new Phrase(Chunk.NEWLINE));
		phraseAfter.setBorderColor(new Color(255, 255, 255));
		foot.addCell(phraseAfter);
		
		phraseAfter = new PdfPCell(new Phrase(11f, String.valueOf(document
				.getPageNumber()),
				new Font(Font.TIMES_ROMAN, 8, 0, Color.BLACK)));
		phraseAfter.setBorderColor(new Color(255, 255, 255));
		phraseAfter.setHorizontalAlignment(Element.ALIGN_CENTER);
		foot.addCell(phraseAfter);
		
		phraseAfter = new PdfPCell(new Phrase(11f,
				"Proprietary Notice: © Electro Motive Diesel, Inc "
				+ c.get(Calendar.YEAR) + ".", new Font(
						Font.TIMES_ROMAN, 8, Font.BOLD + Font.UNDERLINE,
						Color.BLACK)));
		phraseAfter.setBorderColor(new Color(255, 255, 255));
		// phraseAfter.setLeading(0f,1.5f);
		foot.addCell(phraseAfter);
		
		phraseAfter = new PdfPCell(
				new Phrase(
						11f,
						"Information contained in this document is proprietary to Electro-Motive Diesel, Inc. No part or whole of this document may be disclosed to third parties, copied or reproduced in any",
						strFointSizeEight));
		phraseAfter.setBorderColor(new Color(255, 255, 255));
		// phraseAfter.setLeading(0f,1.5f);
		foot.addCell(phraseAfter);
		
		phraseAfter = new PdfPCell(
				new Phrase(
						11f,
						"manner without prior written permission of Electro-Motive Diesel, Inc.",
						strFointSizeEight));
		phraseAfter.setBorderColor(new Color(255, 255, 255));
		foot.addCell(phraseAfter);
		
		foot.setTotalWidth(page.getWidth() - document.leftMargin()
				- document.rightMargin());
		foot.writeSelectedRows(0, -1, document.leftMargin(), document
				.bottomMargin(), writer.getDirectContent());
		
		//Added for CR-130 - PDF logo space
		Image image = null;

		try	{
			PdfImportedPage headerurl = writer.getImportedPage(new 
					PdfReader(PDFView.class.getClassLoader().getResource("images/Header.pdf")), 1);
			image = Image.getInstance(headerurl);
			//image.scalePercent(62);
			
			PdfPTable head = new PdfPTable(1);
			PdfPCell cel = new PdfPCell(image);//true denotes fit to cell
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel.setFixedHeight(30f);
			head.addCell(cel);
	
			head.setTotalWidth(page.getWidth() - document.leftMargin()
					- document.rightMargin());
			LogUtil.logMessage("xpos "+document.leftMargin());
			LogUtil.logMessage("ypos "+(page.getHeight() - document.topMargin() + head.getTotalHeight()-15));
			
			head.writeSelectedRows(0, -1, 7f,
					830f, writer.getDirectContent());
			
		}
		catch (Exception objExp) {
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		//Added for CR-130 ends here	
		
	}
	
	public void onCloseDocument(PdfWriter arg0, Document arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void onParagraph(PdfWriter arg0, Document arg1, float arg2) {
		// TODO Auto-generated method stub
		
	}
	
	public void onParagraphEnd(PdfWriter arg0, Document arg1, float arg2) {
		// TODO Auto-generated method stub
		
	}
	
	public void onChapter(PdfWriter arg0, Document arg1, float arg2,
			Paragraph arg3) {
		// TODO Auto-generated method stub
		
	}
	
	public void onChapterEnd(PdfWriter arg0, Document arg1, float arg2) {
		// TODO Auto-generated method stub
		
	}
	
	public void onSection(PdfWriter arg0, Document arg1, float arg2, int arg3,
			Paragraph arg4) {
		// TODO Auto-generated method stub
		
	}
	
	public void onSectionEnd(PdfWriter arg0, Document arg1, float arg2) {
		// TODO Auto-generated method stub
		
	}
	
	public void onGenericTag(PdfWriter arg0, Document arg1, Rectangle arg2,
			String arg3) {
		// TODO Auto-generated method stub
		
	}
	
	public void onOpenDocument(PdfWriter arg0, Document arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void onStartPage(PdfWriter arg0, Document arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
