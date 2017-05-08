/*
 * Created on Apr 11, 2008
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

import com.EMD.LSDB.action.common.EMDAction;
import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.StandardEquipBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.form.MasterMaintenance.EngineeringDataForm;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.StandardEquipVO;

/**
 * @author ps57222
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class EngineeringDataAction extends EMDAction {
	
	public ActionForward initLoad(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		LogUtil.logMessage("Inside the EngineeringDataAction:initLoad");
		EngineeringDataForm objEngineeringDataForm = (EngineeringDataForm) objActionForm;
		ArrayList arlStandardEquipList;
		StandardEquipVO objStandardEquipVO = new StandardEquipVO();
		String strForwardKey = ApplicationConstants.SUCCESS;
		try {
			StandardEquipBI objStandardEquipBI = ServiceFactory
			.getStandardEquipBO();
			arlStandardEquipList = objStandardEquipBI
			.fetchStandardEquipment(objStandardEquipVO);
			objEngineeringDataForm
			.setStandardEquipmentList(arlStandardEquipList);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in EngineeringDataAction:initLoad");
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
	 * * * * This Method is used to insert the EngineeringData into the system
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward insertStandardEquipment(
			ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil
		.logMessage("Inside the EngineeringDataAction:insertStandardEquipment method ");
		String strForwardKey = ApplicationConstants.SUCCESS;
		StandardEquipVO objStandardEquipVO = new StandardEquipVO();
		ArrayList arlStandardEquipList = new ArrayList();
		int intStatusCode;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = null;
		try {
			
			if (objSession != null) {
				objLoginVo = (LoginVO) objSession
				.getAttribute(ApplicationConstants.USER_IN_SESSION);
			}
			
			EngineeringDataForm objEngineeringDataForm = (EngineeringDataForm) objActionForm;
			objStandardEquipVO.setStandardEquipmentDesc(objEngineeringDataForm
					.getStdEquipDesc());
			LogUtil.logMessage("StdEquipDesc:"
					+ objEngineeringDataForm.getStdEquipDesc());
			//Added for CR-144
			objStandardEquipVO.setStdEnggDataDesc(objEngineeringDataForm
					.getEnggDataDesc());
			LogUtil.logMessage("EnggDataDesc:"
					+ objEngineeringDataForm.getEnggDataDesc());
			//Added for CR-114 Ends here
			objStandardEquipVO.setUserID(objLoginVo.getUserID());
			
			StandardEquipBI objStandardEquipBI = ServiceFactory
			.getStandardEquipBO();
			intStatusCode = objStandardEquipBI
			.insertStandardEquipment(objStandardEquipVO);
			LogUtil.logMessage("IS Data Added successfully:" + intStatusCode);
			
			if (intStatusCode == 0) {
				
				objEngineeringDataForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				objEngineeringDataForm.setStdEquipDesc("");
				//Added for CR-114
				objEngineeringDataForm.setEnggDataDesc("");
			}
			
			if (intStatusCode > 0) {
				objEngineeringDataForm.setMessageID("" + intStatusCode);
			}
			objStandardEquipVO = new StandardEquipVO();
			arlStandardEquipList = objStandardEquipBI
			.fetchStandardEquipment(objStandardEquipVO);
			objEngineeringDataForm
			.setStandardEquipmentList(arlStandardEquipList);
			
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in EngineeringDataAction:insertStandardEquipment");
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
	 * * * * This Method is used to Modify the EngineeringData into the system
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward modifyStandardEquipment(
			ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil
		.logMessage("Inside the EngineeringDataAction:modifyStandardEquipment method ");
		String strForwardKey = ApplicationConstants.SUCCESS;
		StandardEquipVO objStandardEquipVO = new StandardEquipVO();
		ArrayList arlStandardEquipList = new ArrayList();
		int intStatusCode;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = null;
		try {
			
			if (objSession != null) {
				objLoginVo = (LoginVO) objSession
				.getAttribute(ApplicationConstants.USER_IN_SESSION);
			}
			
			EngineeringDataForm objEngineeringDataForm = (EngineeringDataForm) objActionForm;
			objStandardEquipVO.setStandardEquipmentSeqNo(objEngineeringDataForm
					.getStandardEquipmentSeqNo());
			objStandardEquipVO.setStandardEquipmentDesc(objEngineeringDataForm
					.getHdStdEquipDesc());
			//Added for CR-114
			objStandardEquipVO.setStdEnggDataDesc(objEngineeringDataForm
					.getHdEnggDataDesc());
			objStandardEquipVO.setUserID(objLoginVo.getUserID());
			
			StandardEquipBI objStandardEquipBI = ServiceFactory
			.getStandardEquipBO();
			intStatusCode = objStandardEquipBI
			.modifyStandardEquipment(objStandardEquipVO);
			LogUtil
			.logMessage("IS Data Modified successfully:"
					+ intStatusCode);
			
			if (intStatusCode == 0) {
				
				objEngineeringDataForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			}
			
			if (intStatusCode > 0) {
				objEngineeringDataForm.setMessageID("" + intStatusCode);
			}
			objStandardEquipVO = new StandardEquipVO();
			arlStandardEquipList = objStandardEquipBI
			.fetchStandardEquipment(objStandardEquipVO);
			objEngineeringDataForm
			.setStandardEquipmentList(arlStandardEquipList);
			
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in EngineeringDataAction:modifyStandardEquipment");
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
	 * * * * This Method is used to Delete the EngineeringData into the system
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward deleteStandardEquipment(
			ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil
		.logMessage("Inside the EngineeringDataAction:deleteStandardEquipment method ");
		String strForwardKey = ApplicationConstants.SUCCESS;
		StandardEquipVO objStandardEquipVO = new StandardEquipVO();
		ArrayList arlStandardEquipList = new ArrayList();
		int intStatusCode;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = null;
		try {
			
			if (objSession != null) {
				objLoginVo = (LoginVO) objSession
				.getAttribute(ApplicationConstants.USER_IN_SESSION);
			}
			
			EngineeringDataForm objEngineeringDataForm = (EngineeringDataForm) objActionForm;
			objStandardEquipVO.setStandardEquipmentSeqNo(objEngineeringDataForm
					.getStandardEquipmentSeqNo());
			LogUtil
			.logMessage("StdEquipSeqNo in EngineeringDataAction:deleteStdEquip:"
					+ objEngineeringDataForm
					.getStandardEquipmentSeqNo());
			objStandardEquipVO.setUserID(objLoginVo.getUserID());
			
			StandardEquipBI objStandardEquipBI = ServiceFactory
			.getStandardEquipBO();
			intStatusCode = objStandardEquipBI
			.deleteStandardEquipment(objStandardEquipVO);
			LogUtil.logMessage("IS Data Deleted successfully:" + intStatusCode);
			
			if (intStatusCode == 0) {
				
				objEngineeringDataForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			}
			
			if (intStatusCode > 0) {
				objEngineeringDataForm.setMessageID("" + intStatusCode);
			}
			objStandardEquipVO = new StandardEquipVO();
			arlStandardEquipList = objStandardEquipBI
			.fetchStandardEquipment(objStandardEquipVO);
			objEngineeringDataForm
			.setStandardEquipmentList(arlStandardEquipList);
			
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in EngineeringDataAction:deleteStandardEquipment");
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