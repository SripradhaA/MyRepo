/* AK49339
 * Created on Aug 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.action.SpecMaintenance;

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

import com.EMD.LSDB.action.common.EMDAction;
import com.EMD.LSDB.action.common.PDFView;
import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.OrderBI;
import com.EMD.LSDB.bo.serviceinterface.OrderSectionBI;
import com.EMD.LSDB.bo.serviceinterface.OrderSpecificationBI;
import com.EMD.LSDB.bo.serviceinterface.SpecTypeBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.constant.DatabaseConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.form.SpecMaintenance.OrderSpecificationForm;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.RevisionVO;
import com.EMD.LSDB.vo.common.SectionVO;
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
 * --------------------------------------------------------------------------------------------------------
 *     Date         Version  Modified by          	comments                              Remarks 
 * 02/02/2011        1.0      SD41630    Modified generateSpecificationPDF mehtod 
 *                                        for The generated Spec
 *                                        and the Preview Specifications PDF 
 *                                        should utmost be the same             	   Added for CR_94
 * **************************************************************************/
public class OrderSpecificationAction extends EMDAction implements PdfPageEvent {
	
	//Added for CR-130 header logo
	static String strHeaderFlag = ApplicationConstants.NO;
	
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
	
	public ActionForward fetchSpecificationItems(
			ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil
		.logMessage("Entering OrderSpecificationAction.fetchSpecificationItems");
		String strForwardKey = ApplicationConstants.SUCCESS;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		OrderSpecificationForm objOrderSpecificationForm = (OrderSpecificationForm) objActionForm;
		SpecificationVO objSpecificationVO = new SpecificationVO();
		ArrayList arlOrderList = new ArrayList();
		ArrayList arlSectionList = new ArrayList();
		SectionVO objSectionVO = new SectionVO();
		OrderVO objOrderVO = new OrderVO();
		int intorderKey = 0;
		String revCode = "";
		/* Added for CR-74 01-06-09 - Starts Here */
		ArrayList arlRevList = new ArrayList();
		RevisionVO objRevisionVO = new RevisionVO();
		/* Added for CR-74 01-06-09 - Ends Here */
		
		//Added toland into particular specification starts

		if ((String) objHttpServletRequest.getParameter("seqno") != null) {
			
			objHttpServletRequest
			.setAttribute("specseqno",
					(String) objHttpServletRequest
					.getParameter("seqno"));
			
		}
				
		//ends
		if (objHttpServletRequest.getParameter("orderKeyId") == null) {
			intorderKey = objOrderSpecificationForm.getOrderKey();
		} else {
			intorderKey = Integer.parseInt(objHttpServletRequest
					.getParameter("orderKeyId"));
		}
		LogUtil.logMessage("Order Key" + intorderKey);
		
		if (objHttpServletRequest.getParameter("revCode") != null
				&& objHttpServletRequest.getParameter("revCode") != "") {
			
			revCode = objHttpServletRequest.getParameter("revCode");
			
		} else {
			
			revCode = "1";
		}
		
		objOrderSpecificationForm.setOrderKey(intorderKey);
		
		// Commented for CR-74 01-06-09
		// objOrderSpecificationForm.setHdnRevViewCode(revCode);
		
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			/* Added for CR-74 01-06-09 - Starts Here */

			objRevisionVO.setUserID(objLoginVo.getUserID());

			OrderSectionBI objOrderSectionBO = ServiceFactory
					.getOrderSectionBO();

			arlRevList = objOrderSectionBO.fetchRevision(objRevisionVO);
			if (arlRevList != null) {
				objOrderSpecificationForm.setRevisionList(arlRevList);
				objOrderSpecificationForm.setRevCode(Integer.parseInt(revCode));
				objOrderSpecificationForm.setHdnRevViewCode(String
						.valueOf(objOrderSpecificationForm.getRevCode()));
			}
			LogUtil.logMessage("Rev Code"
					+ objOrderSpecificationForm.getHdnRevViewCode());
			/* Added for CR-74 01-06-09* - Ends Here */
			objSpecificationVO
			.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSpecificationVO.setOrderKey(objOrderSpecificationForm
					.getOrderKey());
			objSpecificationVO.setUserID(objLoginVo.getUserID());
			// Added for CR-74 01-06-09
			objSpecificationVO.setRevCode(objOrderSpecificationForm
					.getRevCode());
			OrderSpecificationBI objOrderSpecificationBO = ServiceFactory
			.getOrderSpecificationBO();
			ArrayList arlSpec = objOrderSpecificationBO
			.fetchSpecificationItems(objSpecificationVO);
			
			//Changed for CR-74 VV49326
			if (arlSpec != null && arlSpec.size() > 0) {
				objSpecificationVO = (SpecificationVO) arlSpec.get(0);
				objOrderSpecificationForm.setSpecList(arlSpec);
				
				objOrderSpecificationForm.setOrderHpDesc(ApplicationUtil
						.trim((objSpecificationVO.getHpDesc())));
				
				// Added for CR-74 VV49326 02-06-09
				objOrderSpecificationForm.setHpRatingMarkers(objSpecificationVO
						.getHpRatingMarkers());
				LogUtil.logMessage("objArrayListSpec :" + arlSpec);

				// Added for CR-76 VV49326 07-08-09
				objOrderSpecificationForm.setHpSysMarkFlag(objSpecificationVO
						.getHpSysMarkFlag());
				objOrderSpecificationForm.setHpSysMarkDesc(objSpecificationVO
						.getHpSysMarkDesc());

			}
			objSectionVO.setUserID(objLoginVo.getUserID());
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			objSectionVO.setOrderKey(intorderKey);
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null) {
				objOrderSpecificationForm.setSectionList(arlSectionList);
				
			}
			
			LogUtil.logMessage("ArrayList Value in OrderSectionAction:"
					+ objOrderSpecificationForm.getSectionList().size());
			
			/** ******************Ends Here ************************** */
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			LogUtil.logMessage("*******OrderKey inside Fetch orders:"
					+ objOrderSpecificationForm.getOrderKey());
			objOrderVO.setOrderKey(objOrderSpecificationForm.getOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objOrderSpecificationForm.setOrderList(arlOrderList);
			
			LogUtil
			.logMessage("*******OrderKey inside Fetch orders Arraylist size:"
					+ objOrderSpecificationForm.getOrderList().size());
			/** ******************Ends Here ************************** */
			
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
					objOrderSpecificationForm.setPerfCurveLinkFlag(ApplicationConstants.YES);
				else
					objOrderSpecificationForm.setPerfCurveLinkFlag(ApplicationConstants.NO);
				
				if(objjSpecTypeVo.isGenArrMaintDisPlayFlag())
					objOrderSpecificationForm.setGenArrLinkFlag(ApplicationConstants.YES);
				else
					objOrderSpecificationForm.setGenArrLinkFlag(ApplicationConstants.NO);
			}
			LogUtil.logMessage("objOrderSpecificationForm.getPerfCurveLinkFlag(): "
					+ objOrderSpecificationForm.getPerfCurveLinkFlag());
			
			LogUtil.logMessage("objOrderSpecificationForm.getGenArrLinkFlag(): "
					+ objOrderSpecificationForm.getGenArrLinkFlag());
			
			LogUtil.logMessage("objOrderSpecificationForm.SpecTypeSeqNo:"
					+ objSpecTypeVo.getSpectypeSeqno());
			//Added For CR_84 - Ends here
			
		} catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			objEx.printStackTrace();
			LogUtil.logError(objErrorInfo);
		}
		
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
		.logMessage("Entering OrderSpecificationAction.initLoadSpecification");
		String strForwardKey = "";
		OrderSpecificationForm objOrderSpecificationForm = (OrderSpecificationForm) objActionForm;
		try {
			// LogUtil.logMessage("OrderSpecificationForm.getHdnorderKey()
			// :"+objOrderSpecificationForm.getHdnorderKey());
			
			// This is for clear fields
			objOrderSpecificationForm.setOrderNo(objOrderSpecificationForm
					.getOrderNo());
			objOrderSpecificationForm.setOrderSpecName("");
			objOrderSpecificationForm.setOrderSpecItemDesc("");
			objOrderSpecificationForm.setOrderSpecItemValue("");
			// Ends
			strForwardKey = ApplicationConstants.SPEC_ORDR_FORWARD;
			
			objOrderSpecificationForm
			.setHdnRevViewCode(objOrderSpecificationForm
					.getHdnRevViewCode());
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
		.logMessage("Entering OrderSpecificationAction.insertSpecification");
		String strForwardKey = "";
		int intSuccess = 0;
		String revCode = "";
		OrderSpecificationForm objOrderSpecificationForm = (OrderSpecificationForm) objActionForm;
		SpecificationVO objSpecificationVO = new SpecificationVO();
		SpecificationItemVO objSpecificationItemVO = new SpecificationItemVO();
		String strUserID = "";
		int intorderKey = 0;
		/*Added as per CR 73 to return to General Info Screen by CM68219 starts*/
		ActionForward actionRedirect = null;
		String strRedirectFlag=null;
		/*Added as per CR 73 to return to General Info Screen by CM68219 ends*/
		if (objHttpServletRequest.getParameter("orderKeyId") == null) {
			intorderKey = objOrderSpecificationForm.getOrderKey();
		} else {
			intorderKey = Integer.parseInt(objHttpServletRequest
					.getParameter("orderKeyId"));
		}
		/*Added as per CR 73 to return to General Info Screen by CM68219 starts*/
		if (objHttpServletRequest.getParameter("revCode") != null
				&& objHttpServletRequest.getParameter("revCode") != "") {
			
			revCode = objHttpServletRequest.getParameter("revCode");
			
		} else {
			
			revCode = "1";
		}
		/*Added as per CR 73 to return to General Info Screen by CM68219 ends*/
		objOrderSpecificationForm.setOrderKey(intorderKey);
		objOrderSpecificationForm.setOrderNo(objOrderSpecificationForm
				.getOrderNo());
		//Changed for CR-74 VV49326 04-06-09
		objOrderSpecificationForm.setHdnRevViewCode(objOrderSpecificationForm.getHdnRevViewCode());
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			// Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			
			objSpecificationVO
			.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSpecificationVO.setOrderKey(objOrderSpecificationForm
					.getOrderKey());
			objSpecificationVO.setSpecName(ApplicationUtil
					.trim(objOrderSpecificationForm.getOrderSpecName()));
			objSpecificationItemVO.setItemDesc(ApplicationUtil
					.trim(objOrderSpecificationForm.getOrderSpecItemDesc()));
			objSpecificationItemVO.setItemValue(ApplicationUtil
					.trim(objOrderSpecificationForm.getOrderSpecItemValue()));
			objSpecificationVO.setUserID(strUserID);
			
			objSpecificationVO.setSpecificationItem(objSpecificationItemVO);
			
			OrderSpecificationBI objOrderSpecificationBI = ServiceFactory
			.getOrderSpecificationBO();
			intSuccess = objOrderSpecificationBI
			.insertSpecification(objSpecificationVO);
			LogUtil.logMessage("blnSuccess in Action ....." + intSuccess);
			objOrderSpecificationForm.setOrderSpecName(ApplicationUtil
					.trim(objSpecificationVO.getSpecName()));
			objOrderSpecificationForm.setOrderSpecItemDesc(ApplicationUtil
					.trim(objSpecificationItemVO.getItemDesc()));
			objOrderSpecificationForm.setOrderSpecItemValue(ApplicationUtil
					.trim(objSpecificationItemVO.getItemValue()));
			/*Added as per CR 73 to return to General Info Screen by CM68219 starts*/
			if (intSuccess == 0) {
				strRedirectFlag="Y";
				objOrderSpecificationForm.setOrderSpecName("");
				objOrderSpecificationForm.setOrderSpecItemDesc("");
				objOrderSpecificationForm.setOrderSpecItemValue("");
				//Changed for CR-74
				actionRedirect = new ActionForward("OrderSpecAction.do?method=fetchSpecificationItems&orderKeyId="+intorderKey+"&revCode="+objOrderSpecificationForm.getHdnRevViewCode(), true /* REDIRECT */);
				objOrderSpecificationForm.setOrderNo(objOrderSpecificationForm
						.getOrderNo());
				
			} else {
				strRedirectFlag="N";
				objOrderSpecificationForm.setMessageID("" + intSuccess);
				objOrderSpecificationForm.setOrderNo(objOrderSpecificationForm
						.getOrderNo());
			}
			/*Added as per CR 73 to return to General Info Screen by CM68219 ends*/
			strForwardKey = ApplicationConstants.SPEC_ORDR_FORWARD;
			objOrderSpecificationForm
			.setHdnRevViewCode(objOrderSpecificationForm
					.getHdnRevViewCode());
		}
		
		catch (Exception objExp) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		/*Added as per CR 73 to return to General Info Screen by CM68219 starts*/
		if(strRedirectFlag!=null && strRedirectFlag.equalsIgnoreCase("Y")){
			LogUtil.logMessage("FORWARD"+strRedirectFlag);
			return actionRedirect;
		   
		    }else{
		    	LogUtil.logMessage("FORWARD"+strForwardKey);
			return objActionMapping.findForward(strForwardKey);	
		    }
		/*Added as per CR 73 to return to General Info Screen by CM68219 ends*/
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
		.logMessage("Entering OrderSpecificationAction.initLoadSpecificationItem");
		String strForwardKey = "";
		OrderSpecificationForm objOrderSpecificationForm = (OrderSpecificationForm) objActionForm;
		int intorderKey = 0;
		if (objHttpServletRequest.getParameter("orderKeyId") == null) {
			intorderKey = objOrderSpecificationForm.getOrderKey();
		} else {
			intorderKey = Integer.parseInt(objHttpServletRequest
					.getParameter("orderKeyId"));
		}
		objOrderSpecificationForm.setOrderKey(intorderKey);
		try {
			LogUtil.logMessage("objOrderSpecificationForm.getHdnorderKey() :"
					+ objOrderSpecificationForm.getHdnorderKey());
			objOrderSpecificationForm.setOrderNo(objOrderSpecificationForm
					.getOrderNo());
			objOrderSpecificationForm.setHdnSpecName(objOrderSpecificationForm
					.getHdnSpecName());
			objOrderSpecificationForm.setHdnSpecSeqNo(objOrderSpecificationForm
					.getHdnSpecSeqNo());
			
			// This is for clearing the fields
			objOrderSpecificationForm.setOrderSpecItemDesc("");
			objOrderSpecificationForm.setOrderSpecItemValue("");
			// Ends
			strForwardKey = ApplicationConstants.SPEC_ORDR_ITEM_FORWARD;
			objOrderSpecificationForm
			.setHdnRevViewCode(objOrderSpecificationForm
					.getHdnRevViewCode());
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
	 * This method is for displaying specification Added for LSDB_CR-64
	 * 
	 * @author ka57588
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
		.logMessage("Entering OrderSpecificationAction.displayModifiedSpecification");
		String strForwardKey = "";
		OrderSpecificationForm objOrderSpecificationForm = (OrderSpecificationForm) objActionForm;
		int intorderKey = 0;
		
		if (objHttpServletRequest.getParameter("orderKeyId") == null) {
			intorderKey = objOrderSpecificationForm.getOrderKey();
		} else {
			intorderKey = Integer.parseInt(objHttpServletRequest
					.getParameter("orderKeyId"));
		}
		objOrderSpecificationForm.setOrderKey(intorderKey);
		
		try {
			LogUtil.logMessage("objOrderSpecificationForm.getHdnorderKey() :"
					+ objOrderSpecificationForm.getHdnorderKey());
			objOrderSpecificationForm.setOrderNo(objOrderSpecificationForm
					.getOrderNo());
			objOrderSpecificationForm
			.setOrderSpecName(objOrderSpecificationForm
					.getHdnSpecName());
			objOrderSpecificationForm.setHdnSpecSeqNo(objOrderSpecificationForm
					.getHdnSpecSeqNo());
			
			strForwardKey = ApplicationConstants.SPEC_ORDR_MODIFY_FORWARD;
			objOrderSpecificationForm
			.setHdnRevViewCode(objOrderSpecificationForm
					.getHdnRevViewCode());
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
	 * This method is for modifying specification Added for LSDB_CR-64
	 * 
	 * @author ka57588
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward updateSpecification(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil
		.logMessage("Entering OrderSpecificationAction.updateSpecification");
		String strForwardKey = ApplicationConstants.SPEC_ORDR_MODIFY_FORWARD;
		int intSuccess = 0;
		OrderSpecificationForm objOrderSpecificationForm = (OrderSpecificationForm) objActionForm;
		SpecificationVO objSpecificationVO = new SpecificationVO();
		String strUserID = "";
		int intorderKey = 0;
		String revCode = "";
		int intSeqno=0;
		/*Added as per CR 73 to return to General Info Screen by CM68219 starts*/
		ActionForward actionRedirect = null;
		String strRedirectFlag=null;
		if (objHttpServletRequest.getParameter("orderKeyId") == null) {
			intorderKey = objOrderSpecificationForm.getOrderKey();
		} else {
			intorderKey = Integer.parseInt(objHttpServletRequest
					.getParameter("orderKeyId"));
		}
		if (objHttpServletRequest.getParameter("seqno") == null) {
			intSeqno = objOrderSpecificationForm.getHdnSpecSeqNo();
		} else {
			intSeqno = Integer.parseInt(objHttpServletRequest
					.getParameter("seqno"));
		}
		if (objHttpServletRequest.getParameter("revCode") != null
				&& objHttpServletRequest.getParameter("revCode") != "") {
			
			revCode = objHttpServletRequest.getParameter("revCode");
			
		} else {
			
			revCode = "1";
		}
		/*Added as per CR 73 to return to General Info Screen by CM68219 ends*/
		objOrderSpecificationForm.setOrderKey(intorderKey);
		objOrderSpecificationForm.setHdnSpecSeqNo(objOrderSpecificationForm
				.getHdnSpecSeqNo());
		objOrderSpecificationForm.setOrderNo(objOrderSpecificationForm
				.getOrderNo());
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			// Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			
			objSpecificationVO.setSpecSeqNo(objOrderSpecificationForm
					.getHdnSpecSeqNo());
			objSpecificationVO.setSpecName(ApplicationUtil
					.trim(objOrderSpecificationForm.getOrderSpecName()));
			objSpecificationVO.setUserID(strUserID);
			objSpecificationVO
			.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSpecificationVO.setOrderKey(objOrderSpecificationForm
					.getOrderKey());
			
			OrderSpecificationBI objOrderSpecificationBI = ServiceFactory
			.getOrderSpecificationBO();
			
			intSuccess = objOrderSpecificationBI
			.updateSpecification(objSpecificationVO);
			
			objOrderSpecificationForm.setOrderSpecName(ApplicationUtil
					.trim(objSpecificationVO.getSpecName()));
			/*Added as per CR 73 to return to General Info Screen by CM68219 starts*/
			if (intSuccess == 0) {
				strRedirectFlag="Y";
				objOrderSpecificationForm.setOrderSpecItemDesc("");
				objOrderSpecificationForm.setOrderSpecItemValue("");
				//Changed for CR-74 VV49326 
				actionRedirect = new ActionForward("OrderSpecAction.do?method=fetchSpecificationItems&orderKeyId="+intorderKey+"&revCode="+objOrderSpecificationForm.getHdnRevViewCode()+"&seqno="+intSeqno, true /* REDIRECT */);
				objOrderSpecificationForm.setOrderNo(objOrderSpecificationForm
						.getOrderNo());
			} else {
				strRedirectFlag="N";
				objOrderSpecificationForm.setMessageID("" + intSuccess);
				objOrderSpecificationForm.setOrderNo(objOrderSpecificationForm
						.getOrderNo());
			}
			/*Added as per CR 73 to return to General Info Screen by CM68219 ends*/
			objOrderSpecificationForm
			.setHdnRevViewCode(objOrderSpecificationForm
					.getHdnRevViewCode());
			
		} catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		/*Added as per CR 73 to return to General Info Screen by CM68219 starts*/
		if(strRedirectFlag!=null && strRedirectFlag.equalsIgnoreCase("Y")){
			LogUtil.logMessage("FORWARD"+strRedirectFlag);
			return actionRedirect;
		   
		    }else{
		    	LogUtil.logMessage("FORWARD"+strForwardKey);
			return objActionMapping.findForward(strForwardKey);	
		    }
		/*Added as per CR 73 to return to General Info Screen by CM68219 ends*/
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
		.logMessage("Entering OrderSpecificationAction.insertSpecificationItem");
		String strForwardKey = ApplicationConstants.SPEC_ORDR_ITEM_FORWARD;
		int intSuccess = 0;
		OrderSpecificationForm objOrderSpecificationForm = (OrderSpecificationForm) objActionForm;
		SpecificationVO objSpecificationVO = new SpecificationVO();
		SpecificationItemVO objSpecificationItemVO = new SpecificationItemVO();
		String strUserID = "";
		int intorderKey = 0;
		int intSeqno=0;
		String revCode = "";
		/*Added as per CR 73 to return to General Info Screen by CM68219 starts*/
		ActionForward actionRedirect = null;
		String strRedirectFlag=null;
		/*Added as per CR 73 to return to General Info Screen by CM68219 ends*/
		if (objHttpServletRequest.getParameter("orderKeyId") == null) {
			intorderKey = objOrderSpecificationForm.getOrderKey();
		} else {
			intorderKey = Integer.parseInt(objHttpServletRequest
					.getParameter("orderKeyId"));
		}
		
		if (objHttpServletRequest.getParameter("seqno") == null) {
			intSeqno = objOrderSpecificationForm.getHdnSpecSeqNo();
		} else {
			intSeqno = Integer.parseInt(objHttpServletRequest
					.getParameter("seqno"));
		}
		/*Added as per CR 73 to return to General Info Screen by CM68219 starts*/
		if (objHttpServletRequest.getParameter("revCode") != null
				&& objHttpServletRequest.getParameter("revCode") != "") {
			
			revCode = objHttpServletRequest.getParameter("revCode");
			
		} else {
			
			revCode = "1";
		}
		/*Added as per CR 73 to return to General Info Screen by CM68219 ends*/
		objOrderSpecificationForm.setOrderKey(intorderKey);
		objOrderSpecificationForm.setOrderNo(objOrderSpecificationForm
				.getOrderNo());
		
        //Changed for CR-74 VV49326 04-06-09
		objOrderSpecificationForm.setHdnRevViewCode(objOrderSpecificationForm.getHdnRevViewCode());
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			// Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			
			objSpecificationVO.setSpecSeqNo(objOrderSpecificationForm
					.getHdnSpecSeqNo());
			objSpecificationItemVO.setItemDesc(ApplicationUtil
					.trim(objOrderSpecificationForm.getOrderSpecItemDesc()));
			objSpecificationItemVO.setItemValue(ApplicationUtil
					.trim(objOrderSpecificationForm.getOrderSpecItemValue()));
			objSpecificationVO.setUserID(strUserID);
			objSpecificationVO.setSpecificationItem(objSpecificationItemVO);
			objSpecificationVO
			.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSpecificationVO.setOrderKey(objOrderSpecificationForm
					.getOrderKey());
			
			OrderSpecificationBI objOrderSpecificationBI = ServiceFactory
			.getOrderSpecificationBO();
			intSuccess = objOrderSpecificationBI
			.insertSpecificationItem(objSpecificationVO);
			objOrderSpecificationForm.setOrderSpecItemDesc(ApplicationUtil
					.trim(objSpecificationItemVO.getItemDesc()));
			objOrderSpecificationForm.setOrderSpecItemValue(ApplicationUtil
					.trim(objSpecificationItemVO.getItemValue()));
			/*Added as per CR 73 to return to General Info Screen by CM68219 starts*/
			if (intSuccess == 0) {
				strRedirectFlag="Y";
				objOrderSpecificationForm.setOrderSpecItemDesc("");
				objOrderSpecificationForm.setOrderSpecItemValue("");
				//Changed for CR-74 VV49326 04-06-09
				actionRedirect = new ActionForward("OrderSpecAction.do?method=fetchSpecificationItems&orderKeyId="+intorderKey+"&revCode="+objOrderSpecificationForm.getHdnRevViewCode()+"&seqno="+intSeqno, true /* REDIRECT */);
				objOrderSpecificationForm.setOrderNo(objOrderSpecificationForm
						.getOrderNo());
			} else {
				strRedirectFlag="N";
				objOrderSpecificationForm.setMessageID("" + intSuccess);
				objOrderSpecificationForm.setOrderNo(objOrderSpecificationForm
						.getOrderNo());
			}
			/*Added as per CR 73 to return to General Info Screen by CM68219 ends*/
			objOrderSpecificationForm
			.setHdnRevViewCode(objOrderSpecificationForm
					.getHdnRevViewCode());
		} catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		/*Added as per CR 73 to return to General Info Screen by CM68219 starts*/
		if(strRedirectFlag!=null && strRedirectFlag.equalsIgnoreCase("Y")){
			LogUtil.logMessage("FORWARD"+strRedirectFlag);
			return actionRedirect;
		   
		    }else{
		    	LogUtil.logMessage("FORWARD"+strForwardKey);
			return objActionMapping.findForward(strForwardKey);	
		    }
		/*Added as per CR 73 to return to General Info Screen by CM68219 ends*/
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
		.logMessage("Entering OrderSpecificationAction.updateSpecificationItem");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intSuccess = 0;
		OrderSpecificationForm objOrderSpecificationForm = (OrderSpecificationForm) objActionForm;
		SpecificationVO objSpecificationVO = new SpecificationVO();
		SpecificationItemVO objSpecificationItemVO = new SpecificationItemVO();
		OrderVO objOrderVO = new OrderVO();
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlOrderList = new ArrayList();
		SectionVO objSectionVO = new SectionVO();
		String strUserID = null;
		int intorderKey = 0;
		/* Added for CR-74 01-06-09 - Starts Here */
		ArrayList arlRevList = new ArrayList();
		RevisionVO objRevisionVO = new RevisionVO();
		/* Added for CR-74 01-06-09 - Ends Here */
		if (objHttpServletRequest.getParameter("orderKeyId") == null) {
			intorderKey = objOrderSpecificationForm.getOrderKey();
		} else {
			intorderKey = Integer.parseInt(objHttpServletRequest
					.getParameter("orderKeyId"));
		}
		objOrderSpecificationForm.setOrderKey(intorderKey);
		LogUtil.logMessage("" + objOrderSpecificationForm.getOrderKey());
		try {
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			/* Added for CR-74 01-06-09 - Starts Here */

			objRevisionVO.setUserID(objLoginVo.getUserID());

			OrderSectionBI objOrderSectionBO = ServiceFactory
					.getOrderSectionBO();

			arlRevList = objOrderSectionBO.fetchRevision(objRevisionVO);
			if (arlRevList != null) {
				objOrderSpecificationForm.setRevisionList(arlRevList);
				objOrderSpecificationForm.setRevCode(objOrderSpecificationForm
						.getRevCode());
				objOrderSpecificationForm.setHdnRevViewCode(String
						.valueOf(objOrderSpecificationForm.getRevCode()));
			}
			LogUtil.logMessage("Rev Code"
					+ objOrderSpecificationForm.getHdnRevViewCode());
			/* Added for CR-74 01-06-09* - Ends Here */
			objSpecificationVO
			.setDataLocationType(DatabaseConstants.DATALOCATION);
			// Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			
			objSpecificationVO.setOrderKey(objOrderSpecificationForm
					.getOrderKey());
			objSpecificationItemVO.setItemSeqNo(objOrderSpecificationForm
					.getItemSeqNo());
			objSpecificationItemVO.setItemDesc(ApplicationUtil
					.trim(objOrderSpecificationForm.getHdnitemDesc()));
			objSpecificationItemVO.setItemValue(ApplicationUtil
					.trim(objOrderSpecificationForm.getHdnitemValue()));
			objSpecificationVO.setSpecificationItem(objSpecificationItemVO);
			objSpecificationVO.setUserID(strUserID);
			//Added for CR-74 VV49326 03-06-09
			objSpecificationVO.setRevCode(objOrderSpecificationForm.getRevCode());
			OrderSpecificationBI objOrderSpecificationBO = ServiceFactory
			.getOrderSpecificationBO();
			intSuccess = objOrderSpecificationBO
			.updateSpecificationItem(objSpecificationVO);
			ArrayList arlSpec = objOrderSpecificationBO
			.fetchSpecificationItems(objSpecificationVO);
			/**Changed for CR-74 VV49326 Issue Fix 08-07-09**/
			if (arlSpec != null && arlSpec.size() > 0) {
				objSpecificationVO = (SpecificationVO) arlSpec.get(0);
				objOrderSpecificationForm.setSpecList(arlSpec);
				
				objOrderSpecificationForm.setOrderHpDesc(ApplicationUtil
						.trim((objSpecificationVO.getHpDesc())));
				
				objOrderSpecificationForm.setHpRatingMarkers(objSpecificationVO
						.getHpRatingMarkers());
				LogUtil.logMessage("objArrayListSpec :" + arlSpec);
				
				//Added for CR-76 VV49326 07-08-09
				objOrderSpecificationForm.setHpSysMarkFlag(objSpecificationVO
						.getHpSysMarkFlag());
				objOrderSpecificationForm.setHpSysMarkDesc(objSpecificationVO
						.getHpSysMarkDesc());
			}
			objOrderSpecificationForm.setSpecList(arlSpec);
			
			if (intSuccess == 0) {
				objOrderSpecificationForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objOrderSpecificationForm.setMessageID("" + intSuccess);
			}
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			objSectionVO.setUserID(strUserID);
			objSectionVO.setOrderKey(intorderKey);
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null) {
				objOrderSpecificationForm.setSectionList(arlSectionList);
			}
			
			LogUtil.logMessage("ArrayList Value in OrderSectionAction:"
					+ objOrderSpecificationForm.getSectionList().size());
			/** ****************** Ends Here ***************************** */
			
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			LogUtil.logMessage("*******OrderKey inside Fetch orders:"
					+ objOrderSpecificationForm.getOrderKey());
			objOrderVO.setOrderKey(objOrderSpecificationForm.getOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objOrderSpecificationForm.setOrderList(arlOrderList);
			
			LogUtil
			.logMessage("*******OrderKey inside Fetch orders Arraylist size:"
					+ objOrderSpecificationForm.getOrderList().size());
			/** ******************Ends Here ************************** */
			
			objOrderSpecificationForm
			.setHdnRevViewCode(objOrderSpecificationForm
					.getHdnRevViewCode());
			
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
	
	public ActionForward updateHpRating(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil.logMessage("Entering OrderSpecificationAction.updateHpRating");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intSuccess = 0;
		OrderSpecificationForm objOrderSpecificationForm = (OrderSpecificationForm) objActionForm;
		SpecificationVO objSpecificationVO = new SpecificationVO();
		SpecificationItemVO objSpecificationItemVO = new SpecificationItemVO();
		ArrayList arlSectionList = new ArrayList();
		SectionVO objSectionVO = new SectionVO();
		String strUserID = null;
		OrderVO objOrderVO = new OrderVO();
		ArrayList arlOrderList = new ArrayList();
		int intorderKey = 0;
		if (objHttpServletRequest.getParameter("orderKeyId") == null) {
			intorderKey = objOrderSpecificationForm.getOrderKey();
		} else {
			intorderKey = Integer.parseInt(objHttpServletRequest
					.getParameter("orderKeyId"));
		}
		objOrderSpecificationForm.setOrderKey(intorderKey);
		/* Added for CR-74 01-06-09 - Starts Here */
		ArrayList arlRevList = new ArrayList();
		RevisionVO objRevisionVO = new RevisionVO();
		/* Added for CR-74 01-06-09 - Ends Here */
		try {
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			/* Added for CR-74 01-06-09 - Starts Here */

			objRevisionVO.setUserID(objLoginVo.getUserID());

			OrderSectionBI objOrderSectionBO = ServiceFactory
					.getOrderSectionBO();

			arlRevList = objOrderSectionBO.fetchRevision(objRevisionVO);
			if (arlRevList != null) {
				objOrderSpecificationForm.setRevisionList(arlRevList);
				objOrderSpecificationForm.setRevCode(objOrderSpecificationForm
						.getRevCode());
				objOrderSpecificationForm.setHdnRevViewCode(String
						.valueOf(objOrderSpecificationForm.getRevCode()));
			}
			LogUtil.logMessage("Rev Code"
					+ objOrderSpecificationForm.getRevCode());
			/* Added for CR-74 01-06-09* - Ends Here */
			objSpecificationVO
			.setDataLocationType(DatabaseConstants.DATALOCATION);
			// Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			
			objSpecificationVO.setOrderKey(objOrderSpecificationForm
					.getOrderKey());
			objSpecificationVO.setHpDesc(ApplicationUtil
					.trim(objOrderSpecificationForm.getOrderHpDesc()));
			LogUtil.logMessage("" + objSpecificationVO.getHpDesc());
			objSpecificationVO.setUserID(strUserID);
            //Added for CR-74 VV49326 03-06-09
			objSpecificationVO.setRevCode(objOrderSpecificationForm.getRevCode());
			OrderSpecificationBI objOrderSpecificationBO = ServiceFactory
			.getOrderSpecificationBO();
			intSuccess = objOrderSpecificationBO
			.updateHpRating(objSpecificationVO);
			
			objOrderSpecificationForm.setOrderHpDesc("");
			
			ArrayList arlSpec = objOrderSpecificationBO
			.fetchSpecificationItems(objSpecificationVO);
			objOrderSpecificationForm.setSpecList(arlSpec);
			if (arlSpec != null && arlSpec.size() >= 0) {
				objSpecificationVO = (SpecificationVO) arlSpec.get(0);
				objOrderSpecificationForm.setOrderHpDesc(ApplicationUtil
						.trim((objSpecificationVO.getHpDesc())));
                //Added for CR-74 VV49326 02-06-09
				objOrderSpecificationForm.setHpRatingMarkers(objSpecificationVO
						.getHpRatingMarkers());
				
				 
                //Added for CR-76 VV49326 07-08-09
				objOrderSpecificationForm.setHpSysMarkFlag(objSpecificationVO
						.getHpSysMarkFlag());
				objOrderSpecificationForm.setHpSysMarkDesc(objSpecificationVO
						.getHpSysMarkDesc());
			}
			
			if (intSuccess == 0) {
				objOrderSpecificationForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objOrderSpecificationForm.setMessageID("" + intSuccess);
			}
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			objSectionVO.setUserID(strUserID);
			objSectionVO.setOrderKey(intorderKey);
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null) {
				objOrderSpecificationForm.setSectionList(arlSectionList);
			}
			
			LogUtil.logMessage("ArrayList Value in OrderSectionAction:"
					+ objOrderSpecificationForm.getSectionList().size());
			/** ****************** Ends Here ***************************** */
			/** ******************Ends Here ************************** */
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			LogUtil.logMessage("*******OrderKey inside Fetch orders:"
					+ objOrderSpecificationForm.getOrderKey());
			objOrderVO.setOrderKey(objOrderSpecificationForm.getOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objOrderSpecificationForm.setOrderList(arlOrderList);
			
			LogUtil
			.logMessage("*******OrderKey inside Fetch orders Arraylist size:"
					+ objOrderSpecificationForm.getOrderList().size());
			/** ******************Ends Here ************************** */
			
			objOrderSpecificationForm
			.setHdnRevViewCode(objOrderSpecificationForm
					.getHdnRevViewCode());
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
	 * This method is for deleting item for a specification
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
	
	public ActionForward deleteSpecificationItem(
			ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil
		.logMessage("Entering OrderSpecificationAction.deleteSpecificationItem");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intSuccess = 0;
		OrderSpecificationForm objOrderSpecificationForm = (OrderSpecificationForm) objActionForm;
		SpecificationVO objSpecificationVO = new SpecificationVO();
		SpecificationItemVO objSpecificationItemVO = new SpecificationItemVO();
		OrderVO objOrderVO = new OrderVO();
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlOrderList = new ArrayList();
		SectionVO objSectionVO = new SectionVO();
		String strUserID = null;
		int intorderKey = 0;
		/* Added for CR-74 01-06-09 - Starts Here */
		ArrayList arlRevList = new ArrayList();
		RevisionVO objRevisionVO = new RevisionVO();
		/* Added for CR-74 01-06-09 - Ends Here */
		if (objHttpServletRequest.getParameter("orderKeyId") == null) {
			intorderKey = objOrderSpecificationForm.getOrderKey();
		} else {
			intorderKey = Integer.parseInt(objHttpServletRequest
					.getParameter("orderKeyId"));
		}
		objOrderSpecificationForm.setOrderKey(intorderKey);
		LogUtil.logMessage("" + objOrderSpecificationForm.getOrderKey());
		try {
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			/* Added for CR-74 01-06-09 - Starts Here */

			objRevisionVO.setUserID(objLoginVo.getUserID());

			OrderSectionBI objOrderSectionBO = ServiceFactory
					.getOrderSectionBO();

			arlRevList = objOrderSectionBO.fetchRevision(objRevisionVO);
			if (arlRevList != null) {
				objOrderSpecificationForm.setRevisionList(arlRevList);
				objOrderSpecificationForm.setRevCode(objOrderSpecificationForm
						.getRevCode());
				objOrderSpecificationForm.setHdnRevViewCode(String
						.valueOf(objOrderSpecificationForm.getRevCode()));
			}
			LogUtil.logMessage("Rev Code"
					+ objOrderSpecificationForm.getHdnRevViewCode());
			/* Added for CR-74 01-06-09* - Ends Here */
			objSpecificationVO
			.setDataLocationType(DatabaseConstants.DATALOCATION);
			// Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			
			objSpecificationVO.setOrderKey(objOrderSpecificationForm
					.getOrderKey());
			objSpecificationItemVO.setItemSeqNo(objOrderSpecificationForm
					.getItemSeqNo());
			
			objSpecificationVO.setSpecificationItem(objSpecificationItemVO);
			objSpecificationVO.setUserID(strUserID);
			
			LogUtil.logMessage("OREDR KEY" + objSpecificationVO.getOrderKey());
			LogUtil.logMessage("Spec Item Seq no"
					+ objSpecificationVO.getSpecificationItem().getItemSeqNo());
			LogUtil.logMessage("DataLOcationType"
					+ objSpecificationVO.getDataLocationType());
            //Added for CR-74 VV49326 03-06-09
			objSpecificationVO.setRevCode(objOrderSpecificationForm.getRevCode());
			
			OrderSpecificationBI objOrderSpecificationBO = ServiceFactory
			.getOrderSpecificationBO();
			intSuccess = objOrderSpecificationBO
			.deleteSpecificationItem(objSpecificationVO);
			ArrayList arlSpec = objOrderSpecificationBO
			.fetchSpecificationItems(objSpecificationVO);
			/**Changed for CR-74 VV49326 Issue Fix 08-07-09**/
			if (arlSpec != null && arlSpec.size() > 0) {
				objSpecificationVO = (SpecificationVO) arlSpec.get(0);
				objOrderSpecificationForm.setSpecList(arlSpec);
				
				objOrderSpecificationForm.setOrderHpDesc(ApplicationUtil
						.trim((objSpecificationVO.getHpDesc())));
				
				objOrderSpecificationForm.setHpRatingMarkers(objSpecificationVO
						.getHpRatingMarkers());
				LogUtil.logMessage("objArrayListSpec :" + arlSpec);
				
                //Added for CR-76 VV49326 07-08-09
				objOrderSpecificationForm.setHpSysMarkFlag(objSpecificationVO
						.getHpSysMarkFlag());
				objOrderSpecificationForm.setHpSysMarkDesc(objSpecificationVO
						.getHpSysMarkDesc());
				
				
			}
			objOrderSpecificationForm.setSpecList(arlSpec);
			
			if (intSuccess == 0) {
				objOrderSpecificationForm
				.setMessageID(ApplicationConstants.MESSAGE_ID);
			} else {
				objOrderSpecificationForm.setMessageID("" + intSuccess);
			}
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			objSectionVO.setUserID(strUserID);
			objSectionVO.setOrderKey(intorderKey);
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null) {
				objOrderSpecificationForm.setSectionList(arlSectionList);
			}
			
			LogUtil.logMessage("ArrayList Value in OrderSectionAction:"
					+ objOrderSpecificationForm.getSectionList().size());
			/** ****************** Ends Here ***************************** */
			
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			LogUtil.logMessage("*******OrderKey inside Fetch orders:"
					+ objOrderSpecificationForm.getOrderKey());
			objOrderVO.setOrderKey(objOrderSpecificationForm.getOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objOrderSpecificationForm.setOrderList(arlOrderList);
			
			LogUtil
			.logMessage("*******OrderKey inside Fetch orders Arraylist size:"
					+ objOrderSpecificationForm.getOrderList().size());
			/** ******************Ends Here ************************** */
			
			objOrderSpecificationForm
			.setHdnRevViewCode(objOrderSpecificationForm
					.getHdnRevViewCode());
			
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
		.logMessage("Entering OrderSpecificationAction.deleteSpecification");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intSuccess = 0;
		OrderSpecificationForm objOrderSpecificationForm = (OrderSpecificationForm) objActionForm;
		SpecificationVO objSpecificationVO = new SpecificationVO();
		SpecificationItemVO objSpecificationItemVO = new SpecificationItemVO();
		OrderVO objOrderVO = new OrderVO();
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlOrderList = new ArrayList();
		SectionVO objSectionVO = new SectionVO();
		String strUserID = null;
		int intorderKey = 0;
		/* Added for CR-74 01-06-09 - Starts Here */
		ArrayList arlRevList = new ArrayList();
		RevisionVO objRevisionVO = new RevisionVO();
		/* Added for CR-74 01-06-09 - Ends Here */
		if (objHttpServletRequest.getParameter("orderKeyId") == null) {
			intorderKey = objOrderSpecificationForm.getOrderKey();
		} else {
			intorderKey = Integer.parseInt(objHttpServletRequest
					.getParameter("orderKeyId"));
		}
		objOrderSpecificationForm.setOrderKey(intorderKey);
		LogUtil.logMessage("" + objOrderSpecificationForm.getOrderKey());
		try {
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			/* Added for CR-74 01-06-09 - Starts Here */

			objRevisionVO.setUserID(objLoginVo.getUserID());

			OrderSectionBI objOrderSectionBO = ServiceFactory
					.getOrderSectionBO();

			arlRevList = objOrderSectionBO.fetchRevision(objRevisionVO);
			if (arlRevList != null) {
				objOrderSpecificationForm.setRevisionList(arlRevList);
				objOrderSpecificationForm.setRevCode(objOrderSpecificationForm
						.getRevCode());
				objOrderSpecificationForm.setHdnRevViewCode(String
						.valueOf(objOrderSpecificationForm.getRevCode()));
			}
			LogUtil.logMessage("Rev Code"
					+ objOrderSpecificationForm.getHdnRevViewCode());
			/* Added for CR-74 01-06-09* - Ends Here */
			objSpecificationVO
			.setDataLocationType(DatabaseConstants.DATALOCATION);
			// Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			
			objSpecificationVO.setOrderKey(objOrderSpecificationForm
					.getOrderKey());
			objSpecificationVO.setSpecSeqNo(objOrderSpecificationForm
					.getHdnSpecSeqNo());
			objSpecificationVO.setUserID(strUserID);
			
			LogUtil.logMessage("OREDR KEY" + objSpecificationVO.getOrderKey());
			LogUtil.logMessage("Spec Seq no"
					+ objOrderSpecificationForm.getHdnSpecSeqNo());
			LogUtil.logMessage("DataLOcationType"
					+ objSpecificationVO.getDataLocationType());
            //Added for CR-74 VV49326 03-06-09
			objSpecificationVO.setRevCode(objOrderSpecificationForm.getRevCode());
			
			OrderSpecificationBI objOrderSpecificationBO = ServiceFactory
			.getOrderSpecificationBO();
			intSuccess = objOrderSpecificationBO
			.deleteSpecification(objSpecificationVO);
			ArrayList arlSpec = objOrderSpecificationBO
			.fetchSpecificationItems(objSpecificationVO);
            /**Changed for CR-74 VV49326 Issue Fix 08-07-09**/
			if (arlSpec != null && arlSpec.size() > 0) {
				objSpecificationVO = (SpecificationVO) arlSpec.get(0);
				objOrderSpecificationForm.setSpecList(arlSpec);
				
				objOrderSpecificationForm.setOrderHpDesc(ApplicationUtil
						.trim((objSpecificationVO.getHpDesc())));
				
				objOrderSpecificationForm.setHpRatingMarkers(objSpecificationVO
						.getHpRatingMarkers());
				LogUtil.logMessage("objArrayListSpec :" + arlSpec);
				
                //Added for CR-76 VV49326 07-08-09
				objOrderSpecificationForm.setHpSysMarkFlag(objSpecificationVO
						.getHpSysMarkFlag());
				objOrderSpecificationForm.setHpSysMarkDesc(objSpecificationVO
						.getHpSysMarkDesc());
			}
			objOrderSpecificationForm.setSpecList(arlSpec);
			
			if (intSuccess == 0) {
				objOrderSpecificationForm
				.setMessageID(ApplicationConstants.MESSAGE_ID);
			} else {
				objOrderSpecificationForm.setMessageID("" + intSuccess);
			}
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			objSectionVO.setUserID(strUserID);
			objSectionVO.setOrderKey(intorderKey);
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null) {
				objOrderSpecificationForm.setSectionList(arlSectionList);
			}
			
			LogUtil.logMessage("ArrayList Value in OrderSectionAction:"
					+ objOrderSpecificationForm.getSectionList().size());
			/** ****************** Ends Here ***************************** */
			
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			LogUtil.logMessage("*******OrderKey inside Fetch orders:"
					+ objOrderSpecificationForm.getOrderKey());
			objOrderVO.setOrderKey(objOrderSpecificationForm.getOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objOrderSpecificationForm.setOrderList(arlOrderList);
			
			LogUtil
			.logMessage("*******OrderKey inside Fetch orders Arraylist size:"
					+ objOrderSpecificationForm.getOrderList().size());
			/** ******************Ends Here ************************** */
			
			// Added for CR-74 VV49326 02-06-09
			// objOrderSpecificationForm
			// .setHdnRevViewCode(objOrderSpecificationForm
			// .getHdnRevViewCode());
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
		.logMessage("Inside the OrderSpecificationAction:previewSpecificationByPDF");
		String strForwardKey = ApplicationConstants.SUCCESS;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		String strUserID = null;
		String strOrderKey = null;
		String strModelName = null;
		//Added for CR_94
		int intOrderKey=0;
		int intRevCode=0;
		
		if (objHttpServletRequest.getParameter("orderKey") != null) {
			intOrderKey = Integer.parseInt((objHttpServletRequest
					.getParameter("orderKey").toString().trim()));
		}
		
		try {
			//Added for CR-74 VV49326 03-06-09
			OrderSpecificationForm objOrderSpecificationForm = (OrderSpecificationForm) objActionForm;
			SpecificationVO objSpecificationVO = new SpecificationVO();
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			// Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			// To get Request ID Values from Servlet Request
			
			strOrderKey = objHttpServletRequest.getParameter("orderkey");
			strModelName = objHttpServletRequest.getParameter("modelname");
			LogUtil.logMessage("strOrderKey :" + strOrderKey);
			LogUtil.logMessage("strModelName :" + strModelName);
			
			if (strOrderKey != null && !"".equals(strOrderKey)) {
				
				objSpecificationVO.setOrderKey(Integer.parseInt(strOrderKey));
			}
			
			objSpecificationVO
			.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSpecificationVO.setUserID(strUserID);
			//Added for CR-74 VV49326 03-06-09
			//modifyed for CR_94
			intRevCode=objOrderSpecificationForm.getRevCode();
			objSpecificationVO.setRevCode(intRevCode);
			//comments for CR_94
			/*OrderSpecificationBI objOrderSpecificationBO = ServiceFactory
			.getOrderSpecificationBO();
			ArrayList arlSpec = objOrderSpecificationBO
			.fetchSpecificationItems(objSpecificationVO);
			*/
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Document document = new Document(PageSize.A4, 7, 0, 40, 100);//Edited for CR-130
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			
			document.addAuthor("EMD-LSDB");
			document.addSubject("EMD-LSDB Specification PDF ");
			document.addCreationDate();
			writer.setPageEvent(new OrderSpecificationAction());
			document.open();
			// showHeaderFooter(document);
			document.newPage();
			
			// Show Header and Footer
			PdfDestination d1 = new PdfDestination(PdfDestination.FIT);
			PdfOutline root = writer.getRootOutline();
			// Generate PDF main document
			//Added for CR_94
			generateSpecificationPDF(document,intOrderKey,strModelName,strUserID,"N",intRevCode);
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
	 **************************************************************************//*
	//commits code  for CR_94 
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
		Font strFointSizeBoldNineRed = new Font(Font.TIMES_ROMAN, 9, Font.BOLD,
				Color.RED);
		//Added for CR-76 VV49326
		Font strFointSizeBoldSixBlack = new Font(Font.TIMES_ROMAN, 
				6,Font.BOLD, Color.BLACK);
		try {
			String strHorsePower = "";
			//Added for CR-76
			String strSysMarkFlag="";
			String strSysMarkDesc="";
			PdfPTable hpMarker = new PdfPTable(1);
			PdfPTable itemMarker = new PdfPTable(1);
			String strItemMarkFlag="";
			String strItemMarkDesc="";
			PdfPTable table = new PdfPTable(20);
			table.setWidthPercentage(85);
			/** Added for CR-74 VV49326 02-06-09 - Ends Here* 
			PdfPCell cel = new PdfPCell(new Paragraph("",
					strFointSizeBoldNineRed));
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cel.setColspan(2);
			table.addCell(cel);
			/** Added for CR-74 VV49326 02-06-09 - Ends Here* 
			cel = new PdfPCell(new Paragraph(strModelName
					+ " GENERAL INFORMATION AND IDENTIFICATION",
					strFontNoUnderLine));
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cel.setColspan(18);
			;
			table.addCell(cel);
			document.add(table);
			// Added for CR-74 VV49326 02-06-09
			ArrayList arlHpMarkers = new ArrayList();
			ArrayList arlItemMarkers = new ArrayList();
			String strHpMarker = "";
			String strItemMarker = "";
			
			if (objSpec != null && objSpec.size() > 0) {
				for (int spec = 0; spec < objSpec.size(); spec++) {
					
					SpecificationVO objSpecVO = (SpecificationVO) objSpec
					.get(spec);
					if (spec == 0) {
						strHorsePower = objSpecVO.getHpDesc();
						table = new PdfPTable(20);
						table.setWidthPercentage(85);
						/** Added for CR-74 VV49326 02-06-09 - Starts Here* 
						arlHpMarkers = new ArrayList();
						arlHpMarkers = objSpecVO.getHpRatingMarkers();
						if (arlHpMarkers.size() > 0 && arlHpMarkers != null) {
							for (int i = 0; i < arlHpMarkers.size(); i++) {
								if (i == 0) {
									strHpMarker = ApplicationUtil
											.trim((String) arlHpMarkers.get(i));
								} else {
									strHpMarker = strHpMarker
											+ "\n"
											+ ApplicationUtil
													.trim((String) arlHpMarkers
															.get(i));
								}
							}
						}
						
						 Added for CR-76 VV49326 - Starts Here
						strSysMarkFlag=objSpecVO.getHpSysMarkFlag();
						strSysMarkDesc=objSpecVO.getHpSysMarkDesc();
						
						if(strSysMarkFlag!= null && !"".equals(strSysMarkFlag)){
							
							if("Y".equalsIgnoreCase(strSysMarkFlag)){
								PdfPCell c = new PdfPCell(new Paragraph(strSysMarkDesc,
										strFointSizeBoldSixBlack));
								c.setBorderColor(new Color(255,255,255));
								c.setBackgroundColor(new Color(59,185,255));
								c.setHorizontalAlignment(Element.ALIGN_CENTER);		
								c.setVerticalAlignment(Element.ALIGN_MIDDLE);
							    setCellPadding(c);
							    hpMarker.addCell(c);
							}
						}
						 Added for CR-76 VV49326 - Ends Here

						if (strHpMarker != null && (!strHpMarker.equals(""))) {
							cel = new PdfPCell(new Paragraph(strHpMarker,
									strFointSizeBoldNineRed));
							cel.setBorderColor(new Color(255,255,255));
							cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
							//Changed for CR-76
							//cel.setColspan(2);
							hpMarker.addCell(cel);
						} else {
							cel = new PdfPCell(new Paragraph("",
									strFointSizeBoldNineRed));
							cel.setBorderColor(new Color(255,255,255));
							cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
							//Changed for CR-76
							//cel.setColspan(2);
							hpMarker.addCell(cel);
						}
						*//** Added for CR-74 VV49326 02-06-09 - Ends Here* *//*
						//Added for CR-76
						cel=new PdfPCell(hpMarker);
						cel.setColspan(2);
						cel.setBorderColor(new Color(255,255,255));
						setCellPadding(cel);
						table.addCell(cel);

						cel = new PdfPCell(new Paragraph(strHorsePower,
								strFointSizeNoBold));
						cel.setBorderColor(new Color(255, 255, 255));
						cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cel.setColspan(18);
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
					cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cel.setColspan(20);
					table.addCell(cel);
					document.add(table);

					table = new PdfPTable(20);
					table.setWidthPercentage(85);
					*//** Added for CR-74 VV49326 02-06-09 - Starts here* *//*
					cel = new PdfPCell(new Paragraph("",
							strFointSizeBoldNineRed));
					cel.setBorderColor(new Color(255, 255, 255));
					cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cel.setColspan(2);
					table.addCell(cel);
					*//** Added for CR-74 VV49326 02-06-09 - Ends Here* *//*
					cel = new PdfPCell(new Paragraph(objSpecVO.getSpecName(),
							strFontBOLD));
					cel.setBorderColor(new Color(255, 255, 255));
					cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cel.setLeading(0f, 1.5f);// Added for Underline issue
					cel.setColspan(15);
					table.addCell(cel);
					
					cel = new PdfPCell(new Paragraph("", strFontSizeNoBoldTen));
					cel.setBorderColor(new Color(255, 255, 255));
					cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cel.setColspan(3);
					table.addCell(cel);
					
					document.add(table);
					
					ArrayList objSpecItem = (ArrayList) objSpecVO.getSpecItem();
					if (objSpecItem != null && objSpecItem.size() > 0) {
						for (int item = 0; item < objSpecItem.size(); item++) {
							*//**Added for CR-76 - Starts Here**//*
							strItemMarkFlag="";
							strItemMarkDesc="";
							*//**Added for CR-76 - Ends Here**//*
							
							strItemMarker = "";
							SpecificationItemVO objSpecItemVO = (SpecificationItemVO) objSpecItem
									.get(item);
							//Added for CR-76
							strItemMarkFlag=objSpecItemVO.getSysMarkFlag();
							strItemMarkDesc=objSpecItemVO.getSysMarkDesc();
							itemMarker = new PdfPTable(1);
							
							table = new PdfPTable(20);
							table.setWidthPercentage(85);

							*//** Added for CR-74 VV49326 02-06-09 - Starts Here* *//*
							arlItemMarkers = new ArrayList();
							arlItemMarkers = objSpecItemVO.getItemMarker();
							if (arlItemMarkers.size() > 0
									&& arlItemMarkers != null) {

								for (int i = 0; i < arlItemMarkers.size(); i++) {

									if (i == 0) {
										strItemMarker = (String) arlItemMarkers
												.get(i);
									} else {
										strItemMarker = strItemMarker
												+ "\n"
												+ ApplicationUtil
														.trim((String) arlItemMarkers
																.get(i));
									}
								}

							}
							
							if(strItemMarkFlag!= null && !"".equals(strItemMarkFlag)){
								
								if("Y".equalsIgnoreCase(strItemMarkFlag)){
									PdfPCell c = new PdfPCell(new Paragraph(strItemMarkDesc,
											strFointSizeBoldSixBlack));
									c.setBorderColor(new Color(255,255,255));
									c.setBackgroundColor(new Color(59,185,255));
									c.setHorizontalAlignment(Element.ALIGN_CENTER);		
									c.setVerticalAlignment(Element.ALIGN_MIDDLE);
								    setCellPadding(c);
								    itemMarker.addCell(c);
								}
							}
							 Added for CR-76 VV49326 - Ends Here
                            
							if (strItemMarker != null
									&& !("".equals(strItemMarker))) {
								cel = new PdfPCell(new Paragraph(strItemMarker,
										strFointSizeBoldNineRed));
								cel.setBorderColor(new Color(255, 255, 255));
								cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
								cel.setColspan(2);
								//Changed for CR-76
								itemMarker.addCell(cel);
							} else {
								cel = new PdfPCell(new Paragraph("",
										strFointSizeBoldNineRed));
								cel.setBorderColor(new Color(255, 255, 255));
								cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
								cel.setColspan(2);
								//Changed for CR-76
								itemMarker.addCell(cel);
							}
							*//** Added for CR-74 VV49326 02-06-09 - Ends Here* *//*
							
							*//** Added for CR-76 VV49326 - Starts Here**//*
							cel=new PdfPCell(itemMarker);
							cel.setColspan(2);
							cel.setBorderColor(new Color(255,255,255));
							setCellPadding(cel);
							table.addCell(cel);
							*//** Added for CR-76 VV49326 - Ends Here**//*
							
							cel = new PdfPCell(new Paragraph(objSpecItemVO
									.getItemDesc(), strFointSizeNoBold));
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cel.setColspan(15);
							table.addCell(cel);

							cel = new PdfPCell(new Paragraph(objSpecItemVO
									.getItemValue(), strFontSizeNoBoldTen));
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cel.setColspan(3);
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
	
	*/
	/***************************************************************************
	 * Added for LSDB_CR-94 This method is for previewing the Specifcation in
	 * PDF
	 * 
	 * @author Ka57588
	 * @version 1.0
	 * @param document
	 * @param intOrderKey
	 * @param strModelName
	 * @param objSpec
	 * @throws Exception
	 **************************************************************************/
	
	private static void generateSpecificationPDF(Document document,int intOrderKey, String strModelName, String strUserID,
			String strFlag, int intRevCode)
	throws Exception {
		Font strFontNoUnderLine = new Font(Font.TIMES_ROMAN, 12, Font.BOLD,
				Color.BLACK);
		Font strFointSizeNoBold = new Font(Font.TIMES_ROMAN, 12, 0, Color.BLACK);
		Font strFontBOLD = new Font(Font.TIMES_ROMAN, 12, Font.BOLD,
				Color.BLACK);
		Font strFontSizeNoBoldTen = new Font(Font.TIMES_ROMAN, 12, 0,
				Color.BLACK);
		Font strFointSizeBoldNineRed = new Font(Font.TIMES_ROMAN, 9, Font.BOLD,
				Color.RED);
		Font strFointSizeBoldSixBlack = new Font(Font.TIMES_ROMAN, 
				6,Font.BOLD, Color.BLACK);
		LogUtil
		.logMessage("Inside GenerateProofReadingDraft.showSpecification");
			SpecificationItemVO objSpecificationitemVO = null;
			SpecificationVO objSpecVO = null;
			String strHorsePower = "";
			ArrayList arlSpecList = new ArrayList();
			ArrayList arlOrderList = new ArrayList();
			StringBuffer sbHPRating = null;
			StringBuffer sbSpecItem = null;
			String strHpSysMarkFlag = "";
			String strHpSysMarkDesc = "";
			String strItemMarkFlag = "";
			String strItemMarkDesc = "";
			PdfPTable marker = new PdfPTable(3);



		try {
		
			OrderSpecificationBI objOrderSpecificationBO = ServiceFactory
					.getOrderSpecificationBO();
			SpecificationVO objSpecificationVO = new SpecificationVO();
		
			//document.newPage();
			PdfPTable table = new PdfPTable(25);
			table.setWidthPercentage(100);
		
			PdfPCell cel = new PdfPCell(new Paragraph("", strFontNoUnderLine));
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(3);
			;
			table.addCell(cel);
		
			cel = new PdfPCell(new Paragraph(strModelName
					+ " GENERAL INFORMATION AND IDENTIFICATION",
					strFontNoUnderLine));
			cel.setBorderColor(new Color(255, 255, 255));
			cel.setColspan(22);
			table.addCell(cel);
			document.add(table);
			
			// To fetch Header Flag
			OrderVO objOrderVO = new OrderVO();
			OrderVO objjOrderVO = new OrderVO();
			objOrderVO.setOrderKey(intOrderKey);
			objOrderVO.setUserID(strUserID);
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			
			if (arlOrderList != null && arlOrderList.size() > 0) {				
				objjOrderVO = (OrderVO) arlOrderList.get(0);
			}
			strHeaderFlag = objjOrderVO.getPdfHeaderFlag();
			//To fetch Header Flag ends
		
			// To fetch Specification Items
		
			objSpecificationVO.setOrderKey(intOrderKey);
			objSpecificationVO
					.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSpecificationVO.setRevCode(intRevCode);
			objSpecificationVO.setUserID(strUserID);
			arlSpecList = objOrderSpecificationBO
					.fetchSpecificationItems(objSpecificationVO);
			if (arlSpecList != null && arlSpecList.size() > 0) {
		
				for (int i = 0; i < arlSpecList.size(); i++) {
					objSpecVO = (SpecificationVO) arlSpecList.get(i);
					ArrayList arlSpecitem = new ArrayList();
		
					if (i == 0) {
						strHorsePower = objSpecVO.getHpDesc();
						table = new PdfPTable(25);
						table.setWidthPercentage(100);
						sbHPRating = new StringBuffer();
		
						strHpSysMarkFlag = objSpecVO.getHpSysMarkFlag();
						strHpSysMarkDesc = objSpecVO.getHpSysMarkDesc();
		
						marker = new PdfPTable(3);
						
						if (strHpSysMarkFlag != null
								&& strHpSysMarkFlag.equalsIgnoreCase("Y")) {
		
							cel = new PdfPCell(new Paragraph("",
									strFointSizeBoldNineRed));
							cel.setHorizontalAlignment(Element.ALIGN_LEFT);
							cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cel.setBorderColor(new Color(255, 255, 255));
							
							cel.setColspan(1);
							marker.addCell(cel);
		
							cel = new PdfPCell(new Paragraph(strHpSysMarkDesc,
									strFointSizeBoldSixBlack));
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setBackgroundColor(new Color(59, 185, 255));
							cel.setHorizontalAlignment(Element.ALIGN_CENTER);
							cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cel.setColspan(2);
							setCellPadding(cel);
							marker.addCell(cel);
		
						} else {
		
							cel = new PdfPCell(new Paragraph("",
									strFointSizeBoldNineRed));
							cel.setHorizontalAlignment(Element.ALIGN_LEFT);
							cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setColspan(1);
							marker.addCell(cel);
		
							cel = new PdfPCell(new Paragraph("",
									strFointSizeBoldNineRed));
							cel.setHorizontalAlignment(Element.ALIGN_CENTER);
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setColspan(2);
							marker.addCell(cel);
						}
		
						ArrayList arlhorsePower = (ArrayList) objSpecVO
								.getHpRatingMarkers();
						if (arlhorsePower != null && arlhorsePower.size() > 0) {
		
							for (int j = 0; j < arlhorsePower.size(); j++) {
		
								sbHPRating
										.append((String) arlhorsePower.get(j));
								if (j < arlhorsePower.size() - 1)
									sbHPRating.append("\n");
							}
		
							cel = new PdfPCell(new Paragraph("",
									strFointSizeBoldNineRed));
							cel.setHorizontalAlignment(Element.ALIGN_CENTER);
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setColspan(1);
							marker.addCell(cel);
		
							cel = new PdfPCell(new Paragraph(sbHPRating
									.toString(), strFointSizeBoldNineRed));
							cel.setHorizontalAlignment(Element.ALIGN_LEFT);
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setColspan(2);
							marker.addCell(cel);
						} else {
							cel = new PdfPCell(new Paragraph("",
									strFointSizeBoldNineRed));
							cel.setHorizontalAlignment(Element.ALIGN_CENTER);
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setColspan(1);
							marker.addCell(cel);
		
							cel = new PdfPCell(new Paragraph("",
									strFointSizeBoldNineRed));
							cel.setHorizontalAlignment(Element.ALIGN_CENTER);
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setColspan(2);
							marker.addCell(cel);
		
						}
						
						cel = new PdfPCell(marker);
						cel.setHorizontalAlignment(Element.ALIGN_CENTER);
						cel.setBorderColor(new Color(255, 255, 255));
						cel.setColspan(3);
						table.addCell(cel);
		
						cel = new PdfPCell(new Paragraph(strHorsePower,
								strFointSizeNoBold));
						cel.setBorderColor(new Color(255, 255, 255));
						cel.setColspan(22);
						table.addCell(cel);
						document.add(table);
		
					}
		
					// This is for adding newline space
					table = new PdfPTable(25);
					table.setWidthPercentage(100);
					cel = new PdfPCell(new Paragraph("", strFontSizeNoBoldTen));// new
					// line
					// add
					cel.setBorderColor(new Color(255, 255, 255));
					cel.setColspan(25);
					table.addCell(cel);
					document.add(table);
		
					table = new PdfPTable(25);
					table.setWidthPercentage(100);
		
					cel = new PdfPCell(new Paragraph("",
							strFointSizeBoldNineRed));
					cel.setHorizontalAlignment(Element.ALIGN_CENTER);
					cel.setBorderColor(new Color(255, 255, 255));
					cel.setColspan(1);
					table.addCell(cel);
		
					cel = new PdfPCell(new Paragraph("",
							strFointSizeBoldNineRed));
					cel.setHorizontalAlignment(Element.ALIGN_CENTER);
					cel.setBorderColor(new Color(255, 255, 255));
					cel.setColspan(2);
					table.addCell(cel);
		
					cel = new PdfPCell(new Paragraph(objSpecVO.getSpecName(),
							strFontBOLD));
					cel.setBorderColor(new Color(255, 255, 255));
					cel.setVerticalAlignment(Element.ALIGN_TOP);
					cel.setLeading(0f, 1.5f);// Added for Underline issue
					cel.setColspan(22);
					table.addCell(cel);
		
					// addNewLine(table);
					document.add(table);
					arlSpecitem = objSpecVO.getSpecItem();
		
					for (int cnt = 0; cnt < arlSpecitem.size(); cnt++) {
						objSpecificationitemVO = (SpecificationItemVO) arlSpecitem
								.get(cnt);
		
						table = new PdfPTable(25);
						table.setWidthPercentage(100);
						sbSpecItem = new StringBuffer();
						// Added for CR-76
						strItemMarkFlag = "";
						strItemMarkDesc = "";
						marker = new PdfPTable(3);
						strItemMarkFlag = objSpecificationitemVO
								.getSysMarkFlag();
						strItemMarkDesc = objSpecificationitemVO
								.getSysMarkDesc();
		
						if (strItemMarkFlag != null
								&& strItemMarkFlag.equalsIgnoreCase("Y")) {
		
							cel = new PdfPCell(new Paragraph("",
									strFointSizeBoldNineRed));
							cel.setHorizontalAlignment(Element.ALIGN_LEFT);
							cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cel.setBorderColor(new Color(255, 255, 255));
							// Changed for CR-76
							cel.setColspan(1);
							marker.addCell(cel);
		
							cel = new PdfPCell(new Paragraph(strItemMarkDesc,
									strFointSizeBoldSixBlack));
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setBackgroundColor(new Color(59, 185, 255));
							cel.setHorizontalAlignment(Element.ALIGN_CENTER);
							cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cel.setColspan(2);
							setCellPadding(cel);
							marker.addCell(cel);
		
						} else {
		
							cel = new PdfPCell(new Paragraph("",
									strFointSizeBoldNineRed));
							cel.setHorizontalAlignment(Element.ALIGN_LEFT);
							cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setColspan(1);
							marker.addCell(cel);
		
							cel = new PdfPCell(new Paragraph("",
									strFointSizeBoldNineRed));
							cel.setHorizontalAlignment(Element.ALIGN_CENTER);
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setColspan(2);
							marker.addCell(cel);
						}
		
						ArrayList arlSpecItem = (ArrayList) objSpecificationitemVO
								.getItemMarker();
		
						if (arlSpecItem != null && arlSpecItem.size() > 0) {
							for (int j = 0; j < arlSpecItem.size(); j++) {
								sbSpecItem.append((String) arlSpecItem.get(j));
								if (j < arlSpecItem.size() - 1)
									sbSpecItem.append("\n");
							}
		
							cel = new PdfPCell(new Paragraph("",
									strFointSizeBoldNineRed));
							cel.setHorizontalAlignment(Element.ALIGN_CENTER);
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setColspan(1);
							marker.addCell(cel);
		
							cel = new PdfPCell(new Paragraph(sbSpecItem
									.toString(), strFointSizeBoldNineRed));
							cel.setHorizontalAlignment(Element.ALIGN_LEFT);
							cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setColspan(2);
							marker.addCell(cel);
		
						} else {
		
							cel = new PdfPCell(new Paragraph("",
									strFointSizeBoldNineRed));
							cel.setHorizontalAlignment(Element.ALIGN_CENTER);
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setColspan(1);
							marker.addCell(cel);
		
							cel = new PdfPCell(new Paragraph("",
									strFointSizeBoldNineRed));
							cel.setHorizontalAlignment(Element.ALIGN_LEFT);
							cel.setBorderColor(new Color(255, 255, 255));
							cel.setColspan(2);
							marker.addCell(cel);
		
						}
												
						cel = new PdfPCell(marker);
						cel.setHorizontalAlignment(Element.ALIGN_CENTER);
						cel.setBorderColor(new Color(255, 255, 255));
						cel.setColspan(3);
						table.addCell(cel);
		
						cel = new PdfPCell(new Paragraph(objSpecificationitemVO
								.getItemDesc(), strFointSizeNoBold));
						cel.setBorderColor(new Color(255, 255, 255));
						cel.setColspan(14);
						table.addCell(cel);
		
						cel = new PdfPCell(new Paragraph(objSpecificationitemVO
								.getItemValue(), strFontSizeNoBoldTen));
						cel.setBorderColor(new Color(255, 255, 255));
						cel.setColspan(8);
						table.addCell(cel);
						document.add(table);
		
					}
		
				}
			}
} catch (Exception objExp) {

	throw objExp;
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
		
		//Added for CR-130 header logo
		if ((ApplicationConstants.YES).equals(strHeaderFlag))	{
			
			Image image = null;

			try	{
				//Image modified to preserve scaling on 18-Nov-09 by RR68151
				//Image type-Scaling changed to png-62 as per New Header Image on 13-Nov-09 by RR68151
				//URL headerurl = PDFView.class.getClassLoader().getResource("images/Header.png");
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
						830f, writer.getDirectContent());//Edited for PDF logo allignment issue
				
			}
			catch (Exception objExp) {
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(objExp.getMessage() + "");
				LogUtil.logError(objErrorInfo);
			}
		}
		//CR-130 Ends
		
	}
	
	/***************************************************************************
	 * This method is used to set cell padding
	 * @author Satyam Computer Services Ltd
	 * @version 1.0 Added for CR-76
	 * @param PdfPCell
	 * @author VV49326
	 **************************************************************************/
	private static void setCellPadding(PdfPCell cell) {

		cell.setPadding(5);

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
