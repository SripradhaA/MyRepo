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
import com.EMD.LSDB.bo.serviceinterface.DistributorBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.form.MasterMaintenance.CustMaintForm;
import com.EMD.LSDB.form.MasterMaintenance.DistMaintForm;
import com.EMD.LSDB.vo.common.DistributorVO;
import com.EMD.LSDB.vo.common.FileVO;
import com.EMD.LSDB.vo.common.LoginVO;

/*******************************************************************************************
 * @author  Satyam Computer Services Ltd  
 * @version 1.0  
 * This Action class has the business methods for the Distributor 
 ******************************************************************************************/

public class DistMaintAction extends EMDAction {
	
	/*************************************************************************************
	 * This Method is used to fetch the Distributor Details
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 *************************************************************************************/
	public ActionForward fetchDistributors(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering DistMaintAction.fetchDistributors");
		String strForward = ApplicationConstants.SUCCESS;
		DistMaintForm objDistMaintForm = (DistMaintForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			
			ArrayList arlDistList = new ArrayList();
			DistributorVO objDistributorVo = new DistributorVO();
			DistributorBI objDistributorBo = ServiceFactory.getDistributorBO();
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			String strUserID = "";
			
			//Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			
			objDistributorVo.setUserID(strUserID);
			arlDistList = objDistributorBo.fetchDistributors(objDistributorVo);
			
			if (arlDistList != null && arlDistList.size() > 0) {
				objDistMaintForm.setDistList(arlDistList);
			}
			
		} catch (Exception objExp) {
			
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
		
	}
	
	/***********************************************************************************
	 * This Method is used to insert a new Distributor.
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws BusinessException
	 * @throws ApplicationException
	 ************************************************************************************/
	public ActionForward insertDistributor(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException, ApplicationException {
		
		LogUtil.logMessage("Entering DistMaintAction.insertDistributor");
		String strForward = ApplicationConstants.SUCCESS;
		DistMaintForm objDistForm = (DistMaintForm) objActionForm;
		try {
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			DistributorVO objDistVo = new DistributorVO();
			
 			DistributorBI objDistributorBo = ServiceFactory.getDistributorBO();
			String strUserID = "";
			int intSuccess = 0;
			
			//Getting Values From DistMaintForm
			String strDistName = ApplicationUtil.trim(objDistForm
					.getDistributor());
			String strComments = ApplicationUtil
			.trim(objDistForm.getComments());
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			//Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			objDistVo.setDistributorName(strDistName);
			objDistVo.setDistributorDesc(strComments);
			objDistVo.setUserID(strUserID);
//			Added for CR_106 by JG101007
			FormFile objFormFile = objDistForm.getTheFile();
			
			FileVO objFileVO = new FileVO();
			objFileVO.setFileName(objFormFile.getFileName());
			objFileVO.setuploadedFile(objFormFile.getFileData());
			objFileVO.setFileLength(objFormFile.getFileSize());
			objFileVO.setFileStream(objFormFile.getInputStream());
			objFileVO.setContentType(objFormFile.getContentType());
			objDistVo.setFileVO(objFileVO);
			//Added for CR_106 by JG101007 -- Ends here
			intSuccess = objDistributorBo.insertDistributor(objDistVo);
			
			ArrayList arlDistList = new ArrayList();
			DistributorVO objDistributorVo = new DistributorVO();
			objDistributorVo.setUserID(strUserID);

			
			arlDistList = objDistributorBo.fetchDistributors(objDistributorVo);
			objDistForm.setDistList(arlDistList);
			
			if (intSuccess == 0) {
				objDistForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				objDistForm.setDistributor("");
				objDistForm.setComments("");
				objDistForm.setFilePath("");
			} else {
				objDistForm.setMessageID("" + intSuccess);
				objDistForm.setDistributor(objDistForm.getDistributor());
				objDistForm.setComments(objDistForm.getComments());
			}
			
		} catch (Exception objEx) {
			
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForward);
	}
	
	/**************************************************************************************
	 * This Method is used to Modify Distributor Details
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws BusinessException
	 * @throws ApplicationException
	 **************************************************************************************/
	public ActionForward updateDistributor(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException, ApplicationException {
		
		LogUtil.logMessage("Entering DistMaintAction.updateDistributor");
		
		String strForward = ApplicationConstants.SUCCESS;
		DistMaintForm objDistForm = (DistMaintForm) objActionForm;
		try {
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			DistributorVO objDistVo = new DistributorVO();
			DistributorBI objDistributorBo = ServiceFactory.getDistributorBO();
			String strUserID = "";
			int intSuccess = 0;
			
			//Getting Values From DistMaintForm
			int intDistSeqNo = Integer.parseInt(objDistForm
					.getDistributorSeqNo());
			String strDistName = ApplicationUtil.trim(objDistForm
					.getHdnDistName());
			String strComments = ApplicationUtil.trim(objDistForm
					.getHdnDistComments());
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			//Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			
			objDistVo.setDistributorSeqNo(intDistSeqNo);
			objDistVo.setDistributorName(strDistName);
			objDistVo.setDistributorDesc(strComments);
			objDistVo.setUserID(strUserID);
//			Added for CR_106 by JG101007
			FormFile objFormFile = 	objDistForm.getTheModifyFile();
			
			FileVO objFileVO = new FileVO();
			objFileVO.setFileName(objFormFile.getFileName());
			objFileVO.setuploadedFile(objFormFile.getFileData());
			LogUtil
			.logMessage("File Data"
					+objFormFile.getFileName() );
			objFileVO.setFileLength(objFormFile.getFileSize());
			objFileVO.setFileStream(objFormFile.getInputStream());
			objFileVO.setContentType(objFormFile.getContentType());
			objDistVo.setFileVO(objFileVO);
			LogUtil
			.logMessage("objFileVO.getFileLength()"
					+objFileVO.getFileLength() );
			if(objFileVO.getFileLength()!=0){
			objDistVo.setImageSeqNo(objDistForm.getHdnImageSeqNo());
			}else {
				objDistVo.setImageSeqNo(0);
			}
			intSuccess = objDistributorBo.updateDistributor(objDistVo);
			
			objDistForm.setDistributor("");
			objDistForm.setComments("");
			objDistForm.setFilePath("");
			objDistForm.setHdnImageSeqNo(0);
			
			//Added for CR_106 by JG101007 -- Ends here
			
			ArrayList arlDistList = new ArrayList();
			DistributorVO objDistributorVo = new DistributorVO();
			objDistributorVo.setUserID(strUserID);

			
			arlDistList = objDistributorBo.fetchDistributors(objDistributorVo);
			objDistributorVo.setImageSeqNo(	objDistForm.getHdnImageSeqNo());
			//DistMaintForm objDistMaintForm = (DistMaintForm) objActionForm;
			
			objDistForm.setDistList(arlDistList);
			
			if (intSuccess == 0) {
				objDistForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objDistForm.setMessageID("" + intSuccess);
			}
			
			
			
		} catch (Exception objEx) {
			
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForward);
	}
	
	/**************************************************************************************
	 * This Method is used to Delete Distributor Details For CR-55 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws BusinessException
	 * @throws ApplicationException
	 **************************************************************************************/
	public ActionForward deleteDistributor(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException, ApplicationException {
		
		LogUtil.logMessage("Entering DistMaintAction.deleteDistributor");
		
		String strForward = ApplicationConstants.SUCCESS;
		DistMaintForm objDistForm = (DistMaintForm) objActionForm;
		try {
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			DistributorVO objDistVo = new DistributorVO();
			DistributorBI objDistributorBo = ServiceFactory.getDistributorBO();
			ArrayList arlDistList = null;
			String strUserID = "";
			int intSuccess = 0;
			int intDistSeqNo = 0;
			
			//Getting Values From DistMaintForm
			
			if (objDistForm.getDistributorSeqNo() != null) {
				
				intDistSeqNo = Integer.parseInt(objDistForm
						.getDistributorSeqNo());
				
			}
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			//Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			
			objDistVo.setDistributorSeqNo(intDistSeqNo);
			objDistVo.setUserID(strUserID);
			
			intSuccess = objDistributorBo.deleteDistributor(objDistVo);
			
			/* Fecthing Distributor Details after Delete - Starts Here*/
			
			arlDistList = objDistributorBo.fetchDistributors(objDistVo);
			objDistForm.setDistList(arlDistList);
			
			/* Fecthing Distributor Details after Delete - Ends Here*/
			
			if (intSuccess == 0) {
				objDistForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objDistForm.setMessageID("" + intSuccess);
			}
			
		} catch (Exception objEx) {
			
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForward);
	}
	
//	Added for CR_106 by JG101007
	public ActionForward showImage(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into DistMaintAction:showImage");
		//String strForwardKey = ApplicationConstants.SUCCESS;
		String strForwardKey = ApplicationConstants.IMAGE;
		DistMaintForm objDistMaintForm = (DistMaintForm) objActionForm;
		
		if (objHttpServletRequest.getParameter("imageSeqNo") != null) {
			objDistMaintForm.setImageSeqNo(Integer
					.parseInt(objHttpServletRequest.getParameter("imageSeqNo")
							.toString()));
			LogUtil.logMessage("imageSeqNo:"
					+ objHttpServletRequest.getParameter("imageSeqNo"));
		}
		
		if (objHttpServletRequest.getParameter("Distributor") != null) {
			objDistMaintForm.setDistributor(objHttpServletRequest
					.getParameter("Distributor"));
			
			
			LogUtil.logMessage("Distributor:"
					+ objHttpServletRequest.getParameter("Distributor"));
		}
		
		return objActionMapping.findForward(strForwardKey);
	}
}
