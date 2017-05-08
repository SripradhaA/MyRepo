/* AK49339
 * Created on Aug 9, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.action.MasterMaintenance;

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
import com.EMD.LSDB.bo.serviceinterface.CustomerBI;
import com.EMD.LSDB.bo.serviceinterface.SpecTypeBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.form.MasterMaintenance.CustMaintForm;
import com.EMD.LSDB.vo.common.CustomerVO;
import com.EMD.LSDB.vo.common.FileVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.SpecTypeVo;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This Action class has the business methods for the Customer
 ******************************************************************************/
/***************************************************************************
 * ------------------------------------------------------------------------------------------------------
 *     Date         version  create by          	comments                              Remarks 
 * 19/02/2010        1.0      SD41630    Added  for Model level, SpecificationType,   Added for CR_83
 *                                       Model, Section and Sub Section of values be
 *                                       maintain in the session and display in the
 *                                       screen where ever occurred      
 * 											 	 
 * --------------------------------------------------------------------------------------------------------
 * **************************************************************************/


public class CustMaintAction extends EMDAction {
	
	public ActionForward initLoad(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		String strForwardKey = ApplicationConstants.SUCCESS;
		CustMaintForm objCustMaintForm = (CustMaintForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		int specTypeNo=-1;
		String strSpecTypeNo=null;
		
		
		
		try {
			
			/**
			 * On PageLoad Specificationtype dropdown is populated
			 * based on the requirement of LSDB_CR-46
			 * Modified on 28-Aug-08
			 * by ps57222  
			 */
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			//CR_84 lines ae started here
			String strScreenId = "";
			if (objHttpServletRequest.getParameter("screenid") != null) {
				strScreenId = objHttpServletRequest
				.getParameter("screenid");
				objSpecTypeVo.setStrScreenID(strScreenId);
				
			}
			
			//CR_84 lines ends here
			
		       
			
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objCustMaintForm.setSpecTypeList(arlSpec);
			}
			//CR_83 Lines area started here
			
			if(objSession.getAttribute("SPEC_TYPE_NO")!=null)
			{
				strSpecTypeNo=(String)objSession.getAttribute("SPEC_TYPE_NO");
				specTypeNo=Integer.parseInt(strSpecTypeNo);
				
				objCustMaintForm.setSpecTypeNo(specTypeNo);
			}else if(specTypeNo!=-1 && specTypeNo!=0 && objSession.getAttribute("SPEC_TYPE_NO")==null)
			{
				objCustMaintForm.setSpecTypeNo(specTypeNo);
				strSpecTypeNo=(String) objHttpServletRequest.getParameter("specTypeNo");
				objSession.setAttribute("SPEC_TYPE_NO",strSpecTypeNo);
				specTypeNo=Integer.parseInt(strSpecTypeNo);
				objCustMaintForm.setSpecTypeNo(specTypeNo);
				
			} 
			//CR_83 Lines area ends here
			
		} catch (Exception objExp) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***************************************************************************
	 * This method is for fetching the customers
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
	
	public ActionForward fetchCustomers(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering CustMaintAction.fetchCustomers");
		String strForwardKey = ApplicationConstants.SUCCESS;
		CustMaintForm objCustMaintForm = (CustMaintForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		int specTypeNo=-1;
		String strSpecTypeNo=null;
		ArrayList arlImageList=null;
		try {
			ArrayList arlCustomerList = new ArrayList();
			CustomerVO objCustomerVO = new CustomerVO();
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			//
//			CR_84 lines ae started here 
			//Added ScreenID 
			if(objLoginVo.getIntScreenId()!=0)
			{
			//objSpecTypeVo.setScreenID(objLoginVo.getIntScreenId());
			String strScreenId= Integer.toString(objLoginVo.getIntScreenId());
			objSpecTypeVo.setStrScreenID(strScreenId);
			
			}
	//CR_84 lines ends here
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objCustMaintForm.setSpecTypeList(arlSpec);
			}
			//CR_83 - holding spectypeno in the session 
			
					
				
				strSpecTypeNo=(String) objHttpServletRequest.getParameter("specTypeNo");
				objSession.setAttribute("SPEC_TYPE_NO",strSpecTypeNo);
				specTypeNo=Integer.parseInt(strSpecTypeNo);
				objCustMaintForm.setSpecTypeNo(specTypeNo);
			
//			CR_83 -lines are started  here
			
			/**
			 * specTypeSeqNo is set into CustomeerVo based on LSDB_CR-46 Added
			 * on 27-Aug-08 by ps57222
			 */
			
			objCustomerVO.setSpecTypeSeqNo(objCustMaintForm.getSpecTypeNo());
			objCustomerVO.setUserID(objLoginVo.getUserID());
			//Updated For CR_84
			if (objCustMaintForm.getSpecTypeNo() != 2) {
				objCustomerVO.setCustTypeSeqNo(objCustMaintForm
						.getCustTypeSeqNo());
			} else {
				objCustomerVO.setCustTypeSeqNo(1);
				objCustMaintForm.setCustTypeSeqNo(1);
			}
			
			CustomerBI objCustomerBI = ServiceFactory.getCustomerBO();
			arlCustomerList = objCustomerBI.fetchCustomers(objCustomerVO);
			
			objCustMaintForm.setCustList(arlCustomerList);
			objCustMaintForm.setHdncustomertypeseqNo(objCustMaintForm.getCustTypeSeqNo());
			objCustMaintForm.setHdPreSelectedSpecType(objCustMaintForm.getSpecTypeNo());
			LogUtil
			.logMessage("Customer List Size .."
					+ arlCustomerList.size());
			
			
		} catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***************************************************************************
	 * This method is for inserting a new customer
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
	
	public ActionForward insertCustomer(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException, ApplicationException {
		LogUtil.logMessage("Entering CustMaintAction.insertCustomer");
		String strForwardKey = ApplicationConstants.SUCCESS;
		CustMaintForm objCustMaintForm = (CustMaintForm) objActionForm;
		String strUserID = null;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			ArrayList arlCustomerList = new ArrayList();
			CustomerVO objCustomerVO = new CustomerVO();
			int intSuccess = 0;
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			//Getting User from the session
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			/**
			 * specTypeSeqNo is set into CustomeerVo based on LSDB_CR-46 Added
			 * on 27-Aug-08 by ps57222
			 */
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			//Added Screen Id For CR_84 
			if(objLoginVo.getIntScreenId()!=0)
			{
			String strScreenId= Integer.toString(objLoginVo.getIntScreenId());
			objSpecTypeVo.setStrScreenID(strScreenId);
			
			}
				
			//CR_84 lines ends here
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objCustMaintForm.setSpecTypeList(arlSpec);
			}
			//Updated For CR_84		
			if (objCustMaintForm.getSpecTypeNo() != 2) {
				objCustomerVO.setCustTypeSeqNo(objCustMaintForm
						.getCustTypeSeqNo());
			} else {
				objCustomerVO.setCustTypeSeqNo(1);
				objCustMaintForm.setCustTypeSeqNo(1);
			}
			LogUtil.logMessage("CustType SeqNo in Action:"
					+ objCustMaintForm.getCustTypeSeqNo());
			//Added for CR_106 by JG101007
			FormFile objFormFile = objCustMaintForm.getTheFile();
			
			FileVO objFileVO = new FileVO();
			objFileVO.setFileName(objFormFile.getFileName());
			objFileVO.setuploadedFile(objFormFile.getFileData());
			objFileVO.setFileLength(objFormFile.getFileSize());
			objFileVO.setFileStream(objFormFile.getInputStream());
			objFileVO.setContentType(objFormFile.getContentType());
			objCustomerVO.setFileVO(objFileVO);
			//Added for CR_106 by JG101007 -- Ends here
			
			objCustomerVO.setSpecTypeSeqNo(objCustMaintForm.getSpecTypeNo());
			objCustomerVO.setCustomerName(ApplicationUtil.trim(objCustMaintForm
					.getCustName()));
			objCustomerVO.setCustomerDesc(ApplicationUtil.trim(objCustMaintForm
					.getCustDesc()));
			objCustomerVO.setUserID(strUserID);
			
			CustomerBI objCustomerBI = ServiceFactory.getCustomerBO();
			intSuccess = objCustomerBI.insertCustomer(objCustomerVO);
			
						
			if (intSuccess == 0) {
				objCustMaintForm.setCustName("");
				objCustMaintForm.setCustDesc("");
				objCustMaintForm.setFilePath("");
				objCustMaintForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objCustMaintForm.setMessageID("" + intSuccess);
			}
			
			arlCustomerList = objCustomerBI.fetchCustomers(objCustomerVO);
			objCustMaintForm.setCustList(arlCustomerList);
			objCustMaintForm.setHdncustomertypeseqNo(objCustMaintForm.getCustTypeSeqNo());
			objCustMaintForm.setHdPreSelectedSpecType(objCustMaintForm.getSpecTypeNo());
			LogUtil.logMessage("Inside CustMaintAction.insertCustomer :"
					+ arlCustomerList);
		} catch (Exception objEx) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***************************************************************************
	 * This method is for updating a new customer
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
	
	public ActionForward updateCustomer(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		LogUtil.logMessage("Entering CustMaintAction.updateCustomer");
		String strForwardKey = ApplicationConstants.SUCCESS;
		String strUserID = null;
		CustMaintForm objCustMaintForm = (CustMaintForm) objActionForm;
		
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			ArrayList arlCustomerList = new ArrayList();
			int intSuccess = 0;
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			//Getting User from the session
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());

			//Screen has Addded  For CR_84  
			if(objLoginVo.getIntScreenId()!=0)
			{
			
			String strScreenId= Integer.toString(objLoginVo.getIntScreenId());
			objSpecTypeVo.setStrScreenID(strScreenId);
			
			}
			//CR_84 lines ends here
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objCustMaintForm.setSpecTypeList(arlSpec);
			}
			
			CustomerVO objCustomerVO = new CustomerVO();
			//Updated For CR_84
			if (objCustMaintForm.getSpecTypeNo() != 2) {
				objCustomerVO.setCustTypeSeqNo(objCustMaintForm
						.getCustTypeSeqNo());
			} else {
				objCustomerVO.setCustTypeSeqNo(1);
				objCustMaintForm.setCustTypeSeqNo(1);
			}
			//Added for CR_106 by JG101007
			FormFile objFormFile = objCustMaintForm.getTheModifyFile();
			
			FileVO objFileVO = new FileVO();
			objFileVO.setFileName(objFormFile.getFileName());
			objFileVO.setuploadedFile(objFormFile.getFileData());
			objFileVO.setFileLength(objFormFile.getFileSize());
			objFileVO.setFileStream(objFormFile.getInputStream());
			objFileVO.setContentType(objFormFile.getContentType());
			objCustomerVO.setFileVO(objFileVO);
			CustomerBI objCustomerBI = ServiceFactory.getCustomerBO();
					
			objCustomerVO.setSpecTypeSeqNo(objCustMaintForm.getSpecTypeNo());
			
			objCustomerVO.setCustomerSeqNo(Integer.parseInt(objCustMaintForm
					.getCustomerSeqNo()));
			objCustomerVO.setCustomerName(ApplicationUtil.trim(objCustMaintForm
					.getHdncustName()));
			objCustomerVO.setCustomerDesc(ApplicationUtil.trim(objCustMaintForm
					.getHdncustDesc()));
			
			if(objFileVO.getFileLength()!=0){
				
				objCustomerVO.setImageSeqNo(objCustMaintForm.getHdnImageSeqNo());
			}else {
				
				objCustomerVO.setImageSeqNo(0);
			}
			
			objCustomerVO.setUserID(strUserID);
			
				//Added for CR_106 by JG101007 -- Ends here
		
			intSuccess = objCustomerBI.updateCustomer(objCustomerVO);
			
			
			
			if (intSuccess == 0) {
				objCustMaintForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				objCustMaintForm.setFilePath("");
				objCustMaintForm.setHdnImageSeqNo(0);
			} else {
				objCustMaintForm.setMessageID("" + intSuccess);
			}
			
			objCustMaintForm.setCustName("");
			objCustMaintForm.setCustDesc("");
			objCustMaintForm.setFilePath("");
			objCustMaintForm.setHdnImageSeqNo(0);
			arlCustomerList = objCustomerBI.fetchCustomers(objCustomerVO);
			objCustMaintForm.setCustList(arlCustomerList);
			objCustMaintForm.setHdncustomertypeseqNo(objCustMaintForm.getCustTypeSeqNo());
			objCustMaintForm.setHdPreSelectedSpecType(objCustMaintForm
					.getSpecTypeNo());
			
		} catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	//Added for CR_106 by JG101007
	public ActionForward showImage(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into CustMaintAction:showImage");
		//String strForwardKey = ApplicationConstants.SUCCESS;
		String strForwardKey = ApplicationConstants.IMAGE;
		CustMaintForm objCustMaintForm = (CustMaintForm) objActionForm;
		
		if (objHttpServletRequest.getParameter("imageSeqNo") != null) {
			objCustMaintForm.setImageSeqNo(Integer
					.parseInt(objHttpServletRequest.getParameter("imageSeqNo")
							.toString()));
			LogUtil.logMessage("imageSeqNo:"
					+ objHttpServletRequest.getParameter("imageSeqNo"));
		}
		
		if (objHttpServletRequest.getParameter("custName") != null) {
			objCustMaintForm.setCustName(objHttpServletRequest
					.getParameter("custName"));
		}
		
		return objActionMapping.findForward(strForwardKey);
	}
	

}
