package com.EMD.LSDB.action.MasterMaintenance;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.EMD.LSDB.action.common.EMDAction;
import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.SpecTypeBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.form.MasterMaintenance.SpecTypeMaintForm;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.SpecTypeVo;

public class SpecTypeMaintenanceAction extends EMDAction{

	/***************************************************************************
	 * This method is for fetching the Specification Types
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
	
	public ActionForward fetchSpecTypeDetails(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering SpecTypeMaintenanceAction.fetchSpecTypeDetails");
		String strForwardKey = ApplicationConstants.SUCCESS;
		SpecTypeMaintForm objSpecMapTypeMaintForm = (SpecTypeMaintForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		ArrayList arlSpec = new ArrayList();
		try{
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				LogUtil.logMessage("inside if arraylist is not equel to zero:::"+arlSpec.size());
				objSpecMapTypeMaintForm.setSpecTypeList(arlSpec);
			}
			LogUtil.logMessage("ended SpecTypeMaintenanceAction.fetchSpecTypeDetails");
			
		} catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***************************************************************************
	 * This method is for inserting a new Specification Type
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
	
	public ActionForward insertSpecTypeDetails(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException, ApplicationException {
		LogUtil.logMessage("Entering insertSpecTypeDetails.insertSpecTypeDetails");
		String strForwardKey = ApplicationConstants.SUCCESS;
		SpecTypeMaintForm objSpecTypeMaintForm = (SpecTypeMaintForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		ArrayList arlSpec = new ArrayList();
		int intStatusCode = 0;
		try{
			
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
		
			objSpecTypeVo.setSpectypename(objSpecTypeMaintForm.getSpecTypeName());
			
			if(objSpecTypeMaintForm.getSpecTypeDesc()!=null){
				objSpecTypeVo.setComments(objSpecTypeMaintForm.getSpecTypeDesc());
			}else{
				objSpecTypeVo.setComments("");
			}
			
			LogUtil.logMessage("Entering insertSpecTypeDetails.SpecTypeName=="+objSpecTypeMaintForm.getSpecTypeName());
			LogUtil.logMessage("Entering insertSpecTypeDetails.Comments"+objSpecTypeMaintForm.getSpecTypeDesc());
			
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			intStatusCode = objSpecTypeBI.insertSpecTypeDetails(objSpecTypeVo);
			
			if (intStatusCode == 0) {
				objSpecTypeMaintForm.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				objSpecTypeMaintForm.setSpecTypeName("");
				objSpecTypeMaintForm.setSpecTypeDesc("");
			} else {
				objSpecTypeMaintForm.setMessageID("" + intStatusCode);
			}
			
			objSpecTypeVo.setSpectypeSeqno(0);
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objSpecTypeMaintForm.setSpecTypeList(arlSpec);
			}
			
		} catch (Exception objEx) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForwardKey);
	}
	
	/****************************************************************************
	 * This method is for updating an existing Specification Type
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
	public ActionForward updateSpecTypeDetails(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException, ApplicationException {
		
		LogUtil.logMessage("Entering SpecTypemaintAction.updateSpecTypeDetails");
		
		String strForward = ApplicationConstants.SUCCESS;
		
		SpecTypeMaintForm objSpecTypeMaintForm = (SpecTypeMaintForm) objActionForm;
		ArrayList arlSpec = new ArrayList();
		try {
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			ArrayList arlScreenList = new ArrayList();
			
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());

			int intStatusCode = 0;
			
			int intSpectypeSeqNo = Integer.parseInt(objSpecTypeMaintForm.getSpectypeSeqno());
			String strSpectypeName = ApplicationUtil.trim(objSpecTypeMaintForm.getHdnSelSpectypeName());
			String strComments = ApplicationUtil.trim(objSpecTypeMaintForm.getHdnSpecDesc());
								
			if (objSpecTypeMaintForm.isHdnSelCustFlag()) {
				arlScreenList.add("8");
				}
			if (objSpecTypeMaintForm.isHdnSelDistFlag()) {
				arlScreenList.add("9");
				}
			if (objSpecTypeMaintForm.isHdnSelGenArrFlag()) {
				arlScreenList.add("18");
				}
			if (objSpecTypeMaintForm.isHdnSelPerfCurveFlag()) {
				arlScreenList.add("19");
				}
			
			objSpecTypeVo.setSpectypeSeqno(intSpectypeSeqNo);
			objSpecTypeVo.setSpectypename(strSpectypeName);
			objSpecTypeVo.setComments(strComments);
			String [] arlScreenArry =new String[arlScreenList.size()];
			int i=0;
			for ( Iterator iter = arlScreenList.iterator(); iter.hasNext();) {
					
				String element = (String) iter.next();
				arlScreenArry[i]=element;
				i++;
			}
			
			objSpecTypeVo.setScreenIdList(arlScreenArry);
			intStatusCode = objSpecTypeBI.updateSpecTypeDetails(objSpecTypeVo);
					
			if (intStatusCode == 0) {
				objSpecTypeMaintForm.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				objSpecTypeMaintForm.setSpecTypeName("");
				objSpecTypeMaintForm.setSpecTypeDesc("");
			} else {
				objSpecTypeMaintForm.setMessageID("" + intStatusCode);
				objSpecTypeMaintForm.setSpecTypeName("");
				objSpecTypeMaintForm.setSpecTypeDesc("");
			}
			objSpecTypeVo.setSpectypeSeqno(0);					
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objSpecTypeMaintForm.setSpecTypeList(arlSpec);
			}
				
		}
		catch(Exception objEx){
			
			strForward  = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo=new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage()+"");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
	}

}
