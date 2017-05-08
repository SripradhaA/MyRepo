package com.EMD.LSDB.action.cr1058;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.json.JSONException;
import org.json.JSONObject;

import com.EMD.LSDB.action.common.EMDAction;
import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.ChangeRequest1058BI;
import com.EMD.LSDB.bo.serviceinterface.ModelCompBI;
import com.EMD.LSDB.bo.serviceinterface.ModelCompMapBI;
import com.EMD.LSDB.bo.serviceinterface.ModelSelectClauseRevBI;
import com.EMD.LSDB.bo.serviceinterface.OrderSectionBI;
import com.EMD.LSDB.bo.serviceinterface.OrderSectionPopUpBI;
import com.EMD.LSDB.bo.serviceinterface.StandardEquipBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.constant.DatabaseConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.form.cr1058.ChangeRequest1058Form;


import com.EMD.LSDB.vo.admn.UserVO;
import com.EMD.LSDB.vo.common.ChangeRequest1058AttachmentsVO;
import com.EMD.LSDB.vo.common.ClauseTableDataVO;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.CompGroupVO;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.FileVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.ChangeRequest1058VO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.StandardEquipVO;
import com.EMD.LSDB.vo.common.SubSectionVO;



public class ChangeRequest1058Action extends EMDAction{
	public ChangeRequest1058Action() {
		
	}
	
	/***************************************************************************
	 * This Method is used to populate the Order No on Page Load
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 * 
	 **************************************************************************/
	

    public ActionForward initLoad(ActionMapping objActionMapping,
								  ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
                                  HttpServletResponse objHttpServletResponse) throws EMDException {
           LogUtil.logMessage("Entering Create1058RequestAction.initLoad");
                                                
           HttpSession objSession = objHttpServletRequest.getSession(false);
           String strForwardKey = ApplicationConstants.SUCCESS;
           
           LogUtil.logMessage(objSession);
                                                
           try {
                LoginVO objLoginVo = (LoginVO) objSession
                .getAttribute(ApplicationConstants.USER_IN_SESSION);
                LogUtil.logMessage(objLoginVo);

			} catch (Exception objExp) {
					LogUtil.logMessage("Enters into Exception Block in Action..");
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
	 * This Method is used to populate the Order No on Page Load
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 * 
	 **************************************************************************/
	

    public ActionForward loadOrdersFor1058(ActionMapping objActionMapping,
								  ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
                                  HttpServletResponse objHttpServletResponse) throws EMDException {
           LogUtil.logMessage("Entering Create1058RequestAction.initLoad");
                                                
           HttpSession objSession = objHttpServletRequest.getSession(false);
           ChangeRequest1058Form objChangeRequest1058Form = (ChangeRequest1058Form) objActionForm;
           String strForwardKey = ApplicationConstants.SUCCESS;
           ArrayList arlorderNoList = new ArrayList();
   		   JSONObject obJSON=new JSONObject();
           
           LogUtil.logMessage(objSession);
                                                
           try {
                LoginVO objLoginVo = (LoginVO) objSession
                .getAttribute(ApplicationConstants.USER_IN_SESSION);
                LogUtil.logMessage(objLoginVo);
                
				ChangeRequest1058VO changeRequest1058VO = new ChangeRequest1058VO();
				changeRequest1058VO.setUserID(objLoginVo.getUserID());
                
				ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
				arlorderNoList = objChangeRequest1058BI.fetchCreate1058RequestOrderNo(changeRequest1058VO);
				if (arlorderNoList != null) {
					objChangeRequest1058Form.setArlRequestDetails1058(arlorderNoList);
					obJSON.put("orderNoList",arlorderNoList);
					LogUtil.logMessage("arrayList Value:" + arlorderNoList.size());                                                                
				}
				objHttpServletResponse.setContentType("text/javascript");
			    objHttpServletResponse.getWriter().write(obJSON.toString());
			} catch (Exception objExp) {
					LogUtil.logMessage("Enters into Exception Block in Action..");
                    ErrorInfo objErrorInfo = new ErrorInfo();
                    String strError = objExp.getMessage();
                    objErrorInfo.setMessage(strError);
                    LogUtil.logMessage("Error Message:" + strError);
                    LogUtil.logError(objExp, objErrorInfo);
                    strForwardKey = ApplicationConstants.FAILURE;
			}
            return null;
    } 
                
/***************************************************************************
* This Method is used to Create 1058 Request for LSDB Orders
* 
* @param objActionMapping
* @param objActionForm
* @param objHttpServletRequest
* @param objHttpServletResponse
* @return ActionForward
* @throws DataAccessException
* @throws ApplicationException
**************************************************************************/		
public ActionForward create1058LSDBRequest(ActionMapping objActionMapping,
	   ActionForm objActionForm, 
	   HttpServletRequest objHttpServletRequest,
	   HttpServletResponse objHttpServletResponse) 
	   throws EMDException {
	 LogUtil.logMessage("Enters into ChangeRequest1058Action:create1058LSDBRequest");
	String strForwardKey = ApplicationConstants.CREATE;
	ActionForward actionRedirect = null;
	ChangeRequest1058Form objChangeRequest1058Form = (ChangeRequest1058Form) objActionForm;
	int SeqNo1058 = 0;
	try{
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
		LogUtil.logMessage(objLoginVo);
		ChangeRequest1058VO changeRequest1058VO = new ChangeRequest1058VO();
		changeRequest1058VO.setUserID(objLoginVo.getUserID());
		LogUtil.logMessage("Order No:"+objChangeRequest1058Form.getOrderNo());
		LogUtil.logMessage("Order Key:"+objChangeRequest1058Form.getOrderKey());
		changeRequest1058VO.setOrderKey(objChangeRequest1058Form.getOrderKey());
		changeRequest1058VO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
		ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
		SeqNo1058 = objChangeRequest1058BI.create1058LSDBRequest(changeRequest1058VO);
		objChangeRequest1058Form.setSeqNo1058(SeqNo1058);
		LogUtil.logMessage("From create1058LSDBRequest objChangeRequest1058Form.getSeqNo1058():"+objChangeRequest1058Form.getSeqNo1058());
		ActionForward actionForward = objActionMapping.findForward(strForwardKey);
		
		actionRedirect = new ActionForward(actionForward.getName(),
				"ChangeRequest1058Action.do"
				+ "?method=fetch1058Details"+ "&seqNo=" + SeqNo1058 , true  //REDIRECT 
		);
		
	} catch (Exception objExp) {
		LogUtil.logMessage("Enters into Exception Block in Action..");
		ErrorInfo objErrorInfo = new ErrorInfo();
		String strError = objExp.getMessage();
		objErrorInfo.setMessage(strError);
		LogUtil.logMessage("Error Message:" + strError);
		LogUtil.logError(objExp, objErrorInfo);
		strForwardKey = ApplicationConstants.FAILURE;
	}
	if (actionRedirect == null)
		return objActionMapping.findForward(strForwardKey);
	else
		return actionRedirect;
}

/***************************************************************************
* This Method is used to Create 1058 Request for Non-LSDB Orders
* 
* @param objActionMapping
* @param objActionForm
* @param objHttpServletRequest
* @param objHttpServletResponse
* @return ActionForward
* @throws DataAccessException
* @throws ApplicationException
**************************************************************************/	
	public ActionForward create1058NonLSDBRequest(ActionMapping objActionMapping,
		   ActionForm objActionForm, 
		   HttpServletRequest objHttpServletRequest,
		   HttpServletResponse objHttpServletResponse) 
		   throws EMDException {
	LogUtil.logMessage("Enters into ChangeRequest1058Action:create1058NonLSDBRequest");
	String strForwardKey = ApplicationConstants.CREATE;
	ActionForward actionRedirect = null;
	ChangeRequest1058Form objChangeRequest1058Form = (ChangeRequest1058Form) objActionForm;
	int SeqNo1058 = 0;
	
	try{
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			
			ChangeRequest1058VO changeRequest1058VO = new ChangeRequest1058VO();
			changeRequest1058VO.setUserID(objLoginVo.getUserID());
			LogUtil.logMessage("Order No:"+objChangeRequest1058Form.getOrderNo());
			changeRequest1058VO.setOrderNo(objChangeRequest1058Form.getOrderNo());
			ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
			SeqNo1058 = objChangeRequest1058BI.create1058NonLSDBRequest(changeRequest1058VO);
			objChangeRequest1058Form.setSeqNo1058(SeqNo1058);
			LogUtil.logMessage("From create1058LSDBRequest objChangeRequest1058Form.getSeqNo1058():"+objChangeRequest1058Form.getSeqNo1058());
			ActionForward actionForward = objActionMapping.findForward(strForwardKey);
			
			actionRedirect = new ActionForward(actionForward.getName(),
					"ChangeRequest1058Action.do"
					+ "?method=fetch1058Details"+ "&seqNo=" + SeqNo1058 , true  //REDIRECT 
			);
			
	} catch (Exception objExp) {
		LogUtil.logMessage("Enters into Exception Block in Action..");
		ErrorInfo objErrorInfo = new ErrorInfo();
		String strError = objExp.getMessage();
		objErrorInfo.setMessage(strError);
		LogUtil.logMessage("Error Message:" + strError);
		LogUtil.logError(objExp, objErrorInfo);
		strForwardKey = ApplicationConstants.FAILURE;
	}
		if (actionRedirect == null)
			return objActionMapping.findForward(strForwardKey);
		else
			return actionRedirect;
			}

	
	/*******************************************************************************************
	 *  * * *		This Method is used to fetch1058Details from DB
	 *  
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return     	ActionForward  		   
	 * @throws     	EMDException
	 
	 ******************************************************************************************/
	
	public ActionForward fetch1058Details(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into ChangeRequest1058Action:fetchRequestDetails");
		String strForwardKey = ApplicationConstants.CREATE;
		ArrayList arlRequest1058List = new ArrayList();
		ArrayList arlCategories =new ArrayList();
		ArrayList arlRequestTypes =new ArrayList();
		ArrayList arlClauseChangeTypes  =new ArrayList();
		ArrayList arlReqSpecChngClauses =new ArrayList();
		ArrayList arlRepresentativeList = new ArrayList();
		ArrayList arlMdlClaChangesList =  new ArrayList();//Added for CR-127
		ArrayList arlImportSubSecList   = new ArrayList();//Added for CR-130
		ArrayList arl1058SubSecList   = new ArrayList();//Added for CR-130
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LogUtil.logMessage(objSession);
		ChangeRequest1058VO objChangeRequest1058VO =new ChangeRequest1058VO();
		try {
			LoginVO objLoginVO = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVO);
			
			ChangeRequest1058Form objChangeRequest1058Form = (ChangeRequest1058Form) objActionForm;
			objChangeRequest1058VO.setUserID(objLoginVO.getUserID());
			
			objChangeRequest1058Form.setRole(objLoginVO.getRoleID());
			
			 if(ApplicationConstants.ADMN.equals(objLoginVO.getRoleID())
					|| ApplicationConstants.MSE.equals(objLoginVO.getRoleID())
					|| ApplicationConstants.SE.equals(objLoginVO.getRoleID()))
				objChangeRequest1058Form.setRoleID(ApplicationConstants.VALID_USER);
			 else
				 objChangeRequest1058Form.setRoleID(objLoginVO.getRoleID());
			
			objChangeRequest1058VO.setOrderNo(objChangeRequest1058Form
					.getOrderNo());
			objChangeRequest1058VO.setCustSeqNo(objChangeRequest1058Form
					.getCustSeqNo());
			objChangeRequest1058VO.setMdlSeqNo(objChangeRequest1058Form
					.getMdlSeqNo());
			objChangeRequest1058VO.setStatusSeqNo(objChangeRequest1058Form
					.getStatusSeqNo());
			objChangeRequest1058VO.setAwApproval(objChangeRequest1058Form
					.getAwaitingApprvrUser());
			objChangeRequest1058VO.setNonLsdbFlag(objChangeRequest1058Form
					.getNonLsdbFlag());
			if(objHttpServletRequest.getParameter("seqNo")!=null){
				objChangeRequest1058VO.setSeqNo1058(Integer.parseInt(objHttpServletRequest.getParameter("seqNo")));
			}
			
			ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
			arlRequest1058List = objChangeRequest1058BI.fetch1058Details(objChangeRequest1058VO);
							
			if (arlRequest1058List != null) {
				LogUtil.logMessage(arlRequest1058List);
				objChangeRequest1058Form.setRequestDetailsList(arlRequest1058List);
			}
			LogUtil.logMessage("ArrayList Value in ChangeRequest1058Action:"
					+ objChangeRequest1058Form.getRequestDetailsList().size());
			
			//Request 1058 Categories
			arlCategories = objChangeRequest1058BI.fetch1058RequestCategories(objLoginVO);

			if (arlCategories != null) {
				LogUtil.logMessage("Request Categories "+arlCategories);
				objChangeRequest1058Form.setCategories(arlCategories);
			}

			//Request 1058 Request Types
			arlRequestTypes = objChangeRequest1058BI.fetch1058RequestTypes(objLoginVO);
			
			if (arlRequestTypes != null) {
				LogUtil.logMessage("Request Types "+arlRequestTypes);
				objChangeRequest1058Form.setReqTypes(arlRequestTypes);
			}
			
			//Request LSDB Clause Change Types			
			arlClauseChangeTypes = objChangeRequest1058BI.fetch1058ClauseChangeTypes(objLoginVO);
			
			if (arlClauseChangeTypes != null) {
				LogUtil.logMessage("Clause Change Types "+arlClauseChangeTypes);
				objChangeRequest1058Form.setClauseChangeTypes(arlClauseChangeTypes);
			}
			
			objChangeRequest1058VO.setClaChngReqSeq(0);
			
			//Request LSDB Clause Changes Details
			arlReqSpecChngClauses=objChangeRequest1058BI.fetchLsdbClauseDetails(objChangeRequest1058VO);
			if (arlReqSpecChngClauses != null) {
				LogUtil.logMessage("Requested Specification Change "+arlReqSpecChngClauses);
				objChangeRequest1058Form.setReqSpecChngClauseList(arlReqSpecChngClauses);
			}

			//Request Non LSDB Clause Changes Details				
			ArrayList arlClauseList = objChangeRequest1058BI
					.selectClauseNonLsdb(objChangeRequest1058VO);
			objChangeRequest1058Form.setClaList(arlClauseList);
			LogUtil.logMessage("arlClauseList=" + arlClauseList.size());
			
			//RepresentativeList Details
			//Added For CR-120
			UserVO objUserVO = new UserVO();
			objUserVO.setUserID(objLoginVO.getUserID());
			objUserVO.setOrderbyFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
			arlRepresentativeList = objChangeRequest1058BI.fetchRepresentativeDetails(objUserVO);
			
			
			if (arlRepresentativeList != null) {
				LogUtil.logMessage(arlRepresentativeList);
				objChangeRequest1058Form.setRepresentativeList(arlRepresentativeList);
			}
			LogUtil.logMessage("ArrayList Value in ChangeRequest1058Action:"
					+ objChangeRequest1058Form.getRepresentativeList().size());
			//Model Clause Details
			//Added for Cr-127 starts here
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}

			arlMdlClaChangesList = objChangeRequest1058BI.fetch1058MdlClaChanges(objChangeRequest1058VO);
				if (arlMdlClaChangesList != null) {
					
					objChangeRequest1058Form.setMdlClaChangesList(arlMdlClaChangesList);
					objChangeRequest1058Form.setMdlClaChangeListSize(arlMdlClaChangesList.size());
				}
			LogUtil.logMessage("model clause change list" +arlMdlClaChangesList.size());
			
			for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
			}	
			//Added for CR-127 Ends here
			
			//Added for CR-130 starts here
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			arlImportSubSecList = objChangeRequest1058BI.fetchSubSectionList(objChangeRequest1058VO);
				if (arlImportSubSecList != null) {
					
					objChangeRequest1058Form.setImportSubSecList(arlImportSubSecList);
					objChangeRequest1058Form.setImportSubSecListSize(arlImportSubSecList.size());
				}
			LogUtil.logMessage("Import Sub Section List" +arlImportSubSecList.size());
			
			for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
	}
			
			//1058 SubSection Details
			objChangeRequest1058VO.setSubSecChngReqSeq(0);
			
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			
			arl1058SubSecList = objChangeRequest1058BI.fetch1058SubSectionList(objChangeRequest1058VO);
				if (arl1058SubSecList != null) {
					objChangeRequest1058Form.setSubSec1058List(arl1058SubSecList);
				}
			LogUtil.logMessage("Import 1058 Sub Section List" +arl1058SubSecList.size());
			
			for (int cnt = 0; cnt < arl1058SubSecList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arl1058SubSecList.get(cnt);
				//LogUtil.logMessage("objChangeRequest1058VO.getSubSecCode():" +objChangeRequest1058VO.getSubSecCode());
			}
			//Added for CR-130 Ends here
	
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ChangeRequest1058Action..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/*******************************************************************************************
	 *  * * *		This Method is used to insert Representative for sections
	 *  
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return     	ActionForward  		   
	 * @throws     	EMDException
	 
	 ******************************************************************************************/
	
	public ActionForward insertRepresentative(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058Action:insertUser");
		String strForwardKey = ApplicationConstants.CREATE;
		String[] arrRepList = null;
		int intInsertRep = 0;
		int intUpdate1058details = 0;
		ArrayList arlCategories = new ArrayList();
		ArrayList arlRequestTypes = new ArrayList();
		ArrayList arlClauseChangeTypes = new ArrayList();
		ArrayList arlReqSpecChngClauses = new ArrayList();
		ArrayList arlRequest1058List = new ArrayList();
		ArrayList arlRepresentativeList = new ArrayList();
		ArrayList arlMdlClaChangesList = new ArrayList();//Added for CR-127
		ArrayList arlImportSubSecList   = new ArrayList();//Added for CR-130
		ArrayList arl1058SubSecList   = new ArrayList();//Added for CR-130
		
		ChangeRequest1058VO objChangeRequest1058VO = new ChangeRequest1058VO();
		ChangeRequest1058Form objChangeRequest1058Form = (ChangeRequest1058Form) objActionForm;
		
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVO = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
				
			objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
			objChangeRequest1058VO.setUserID(objLoginVO.getUserID());			
			
			if (objChangeRequest1058Form.getStatusSeqNo() == 1) {
				objChangeRequest1058VO.setOrderNo(objChangeRequest1058Form.getOrderNo());
				objChangeRequest1058VO.setCustName(objChangeRequest1058Form.getCustName());
				objChangeRequest1058VO.setMdlName(objChangeRequest1058Form.getMdlName());
				objChangeRequest1058VO.setOrderQty(objChangeRequest1058Form.getOrderQty());
				objChangeRequest1058VO.setReqTypeSeqNo(objChangeRequest1058Form.getReqTypeSeqNo());
				objChangeRequest1058VO.setCategorySeqNo(objChangeRequest1058Form.getCategorySeqNo());
				objChangeRequest1058VO.setGenDesc(objChangeRequest1058Form.getGenDesc());
				objChangeRequest1058VO.setUnitNumber(objChangeRequest1058Form.getUnitNumber());
				objChangeRequest1058VO.setMcrNumber(objChangeRequest1058Form.getMcrNumber());
				objChangeRequest1058VO.setRoadNumber(objChangeRequest1058Form.getRoadNumber());
				objChangeRequest1058VO.setMcrReq(objChangeRequest1058Form.getMcrReq());
				objChangeRequest1058VO.setSpecRev(objChangeRequest1058Form.getSpecRev());
				objChangeRequest1058VO.setNonLsdbFlag(objChangeRequest1058Form.getHdnNonLsdbFlag());
				objChangeRequest1058VO.setSaveOnlyFlag(ApplicationConstants.NO);
				intUpdate1058details  = objChangeRequest1058BI.update1058details(objChangeRequest1058VO);
			}
			arrRepList = objHttpServletRequest.getParameterValues("repList");
			
			objChangeRequest1058VO.setRepList(arrRepList);
			if (objChangeRequest1058Form.getStatusSeqNo() != 1) {
				objChangeRequest1058VO.setSaveOnlyFlag(ApplicationConstants.REASSIGN);
			}
			intInsertRep  = objChangeRequest1058BI.insertRepresentative(objChangeRequest1058VO);
			
			if(intInsertRep == 0){
  				 objChangeRequest1058Form.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);               	
            }
			
			objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
			arlRequest1058List = objChangeRequest1058BI.fetch1058Details(objChangeRequest1058VO);
			
			if (arlRequest1058List != null) {
				LogUtil.logMessage(arlRequest1058List);
				objChangeRequest1058Form.setRequestDetailsList(arlRequest1058List);
			}
			LogUtil.logMessage("ArrayList Value in ChangeRequest1058Action:"
					+ objChangeRequest1058Form.getRequestDetailsList().size());
			
			//Request 1058 Categories
			arlCategories = objChangeRequest1058BI.fetch1058RequestCategories(objLoginVO);

			if (arlCategories != null) {
				LogUtil.logMessage("Request Categories "+arlCategories);
				objChangeRequest1058Form.setCategories(arlCategories);
			}

			//Request 1058 Request Types
			arlRequestTypes = objChangeRequest1058BI.fetch1058RequestTypes(objLoginVO);
			
			if (arlRequestTypes != null) {
				LogUtil.logMessage("Request Types "+arlRequestTypes);
				objChangeRequest1058Form.setReqTypes(arlRequestTypes);
			}
			
			//Request LSDB Clause Change Types			
			arlClauseChangeTypes = objChangeRequest1058BI.fetch1058ClauseChangeTypes(objLoginVO);
			
			if (arlClauseChangeTypes != null) {
				LogUtil.logMessage("Clause Change Types "+arlClauseChangeTypes);
				objChangeRequest1058Form.setClauseChangeTypes(arlClauseChangeTypes);
			}
			
			objChangeRequest1058VO.setClaChngReqSeq(0);
			
			//Request LSDB Clause Changes Details
			if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.NO)){
				arlReqSpecChngClauses=objChangeRequest1058BI.fetchLsdbClauseDetails(objChangeRequest1058VO);
				if (arlReqSpecChngClauses != null) {
					LogUtil.logMessage("Requested Specification Change "+arlReqSpecChngClauses);
					objChangeRequest1058Form.setReqSpecChngClauseList(arlReqSpecChngClauses);
				}
			}

			//Request Non LSDB Clause Changes Details
			if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.YES)){
				
				ArrayList arlClauseList = objChangeRequest1058BI
						.selectClauseNonLsdb(objChangeRequest1058VO);
				objChangeRequest1058Form.setClaList(arlClauseList);
				LogUtil.logMessage("arlClauseList=" + arlClauseList.size());
			}
			
			//RepresentativeList Details
			//Added For CR-120
			UserVO objUserVO = new UserVO();
			objUserVO.setUserID(objLoginVO.getUserID());
			objUserVO.setOrderbyFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
			arlRepresentativeList = objChangeRequest1058BI.fetchRepresentativeDetails(objUserVO);
				
			if (arlRepresentativeList != null) {
				LogUtil.logMessage(arlRepresentativeList);
				objChangeRequest1058Form.setRepresentativeList(arlRepresentativeList);
			}
			LogUtil.logMessage("ArrayList Value in ChangeRequest1058Action:"
					+ objChangeRequest1058Form.getRepresentativeList().size());
			//Added for CR-127 starts here
			//Model Clause Changes Details
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			arlMdlClaChangesList = objChangeRequest1058BI.fetch1058MdlClaChanges(objChangeRequest1058VO);
			
				if (arlMdlClaChangesList != null) {
					objChangeRequest1058Form.setMdlClaChangesList(arlMdlClaChangesList);
				}
			LogUtil.logMessage("model clause change list" +arlMdlClaChangesList.size());
			for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
			}
			//Added for CR-127 ends here
			
			//Added for CR-130 starts here
			//SubSection Details
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
		}
			arlImportSubSecList = objChangeRequest1058BI.fetchSubSectionList(objChangeRequest1058VO);
				if (arlImportSubSecList != null) {
					
					objChangeRequest1058Form.setImportSubSecList(arlImportSubSecList);
					objChangeRequest1058Form.setImportSubSecListSize(arlImportSubSecList.size());
				}
			LogUtil.logMessage("Import Sub Section List" +arlImportSubSecList.size());
			
			for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
			}
			
			//1058 SubSection Details
			objChangeRequest1058VO.setSubSecChngReqSeq(0);
			
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			arl1058SubSecList = objChangeRequest1058BI.fetch1058SubSectionList(objChangeRequest1058VO);
				if (arl1058SubSecList != null) {
					
					objChangeRequest1058Form.setSubSec1058List(arl1058SubSecList);
				}
			LogUtil.logMessage("Import Sub Section List" +arl1058SubSecList.size());
			
			for (int cnt = 0; cnt < arl1058SubSecList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arl1058SubSecList.get(cnt);
			}
			//Added for CR-130 ends here
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage(" Exception occured in ChangeRequest1058Action:"
					+ objExp);
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		LogUtil.logMessage("key" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/*******************************************************************************************
	 *  * * *		This Method is used to save 1058 details to DB
	 *  
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return     	ActionForward  		   
	 * @throws     	EMDException
	 
	 ******************************************************************************************/
	
	public ActionForward save(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into ChangeRequest1058Action:save");
		String strForwardKey = ApplicationConstants.CREATE;
		String[] arrRepList = null;
		int	intInsertRep = 0;
		int intoprDetails = 0;
		int intpropMgrDetails = 0;
		int intpgmMgrDetails = 0;
		int intfinDetails = 0;
		int intsysEngDetails = 0;
		int intUpdate1058details = 0;
		int intinsertActionRecord = 0;
		int intComplete = 0;
		ArrayList arlRequest1058List = new ArrayList();
		ArrayList arlCategories =new ArrayList();
		ArrayList arlRequestTypes =new ArrayList();
		ArrayList arlClauseChangeTypes  =new ArrayList();
		ArrayList arlReqSpecChngClauses=new ArrayList();
		ArrayList arlRepresentativeList = new ArrayList();
		ArrayList arlMdlClaChangesList = new ArrayList();//Added for CR-127
		ArrayList arlImportSubSecList   = new ArrayList();//Added for CR-130
		ArrayList arl1058SubSecList   = new ArrayList();//Added for CR-130
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LogUtil.logMessage(objSession);
		ChangeRequest1058VO objChangeRequest1058VO =new ChangeRequest1058VO();
		try {
			LoginVO objLoginVO = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVO);
			
			ChangeRequest1058Form objChangeRequest1058Form = (ChangeRequest1058Form) objActionForm;
			ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
			
			objChangeRequest1058VO.setUserID(objLoginVO.getUserID());			
			objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
			objChangeRequest1058VO.setOrderNo(objChangeRequest1058Form.getOrderNo());
			objChangeRequest1058VO.setCustName(objChangeRequest1058Form.getCustName());
			objChangeRequest1058VO.setMdlName(objChangeRequest1058Form.getMdlName());
			objChangeRequest1058VO.setOrderQty(objChangeRequest1058Form.getOrderQty());
			objChangeRequest1058VO.setReqTypeSeqNo(objChangeRequest1058Form.getReqTypeSeqNo());
			objChangeRequest1058VO.setCategorySeqNo(objChangeRequest1058Form.getCategorySeqNo());
			objChangeRequest1058VO.setGenDesc(objChangeRequest1058Form.getGenDesc());
			objChangeRequest1058VO.setUnitNumber(objChangeRequest1058Form.getUnitNumber());
			objChangeRequest1058VO.setMcrNumber(objChangeRequest1058Form.getMcrNumber());
			objChangeRequest1058VO.setRoadNumber(objChangeRequest1058Form.getRoadNumber());
			objChangeRequest1058VO.setMcrReq(objChangeRequest1058Form.getMcrReq());			
			objChangeRequest1058VO.setSpecRev(objChangeRequest1058Form.getSpecRev());
			objChangeRequest1058VO.setNonLsdbFlag(objChangeRequest1058Form.getHdnNonLsdbFlag());
			
			intUpdate1058details  = objChangeRequest1058BI.update1058details(objChangeRequest1058VO);
			
			if (objChangeRequest1058Form.getStatusSeqNo() == ApplicationConstants.PENDING_ONE ) {
				arrRepList = objHttpServletRequest.getParameterValues("repList");
				objChangeRequest1058VO.setRepList(arrRepList);			
				objChangeRequest1058VO.setSaveOnlyFlag(ApplicationConstants.YES);
				intInsertRep  = objChangeRequest1058BI.insertRepresentative(objChangeRequest1058VO);
				
				objChangeRequest1058VO.setSectionSeqNo(ApplicationConstants.SECTION_SEQ_NO_ZERO);
				objChangeRequest1058VO.setSectionStatus(ApplicationConstants.SUBMITTED_TWO);
				
				intinsertActionRecord = objChangeRequest1058BI.insert1058ActionRecord(objChangeRequest1058VO);
				
				if(intinsertActionRecord == 0){
	  				 objChangeRequest1058Form.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);               	
	            }
			}			
			else {
				objChangeRequest1058VO.setSectionStatus(ApplicationConstants.SECTION_SEQ_NO_ZERO);
				objChangeRequest1058VO.setSystemEngComment(objChangeRequest1058Form.getSystemEngComment());
				objChangeRequest1058VO.setPartNoAdded(objChangeRequest1058Form.getPartNoAdded());
				objChangeRequest1058VO.setPartNoDeleted(objChangeRequest1058Form.getPartNoDeleted());
				objChangeRequest1058VO.setChangeAffectsWeight(objChangeRequest1058Form.getChangeAffectsWeight());
				objChangeRequest1058VO.setChangeAffectsClear(objChangeRequest1058Form.getChangeAffectsClear());
				objChangeRequest1058VO.setDesignHrs(objChangeRequest1058Form.getDesignHrs());
				objChangeRequest1058VO.setDraftingHrs(objChangeRequest1058Form.getDraftingHrs());
				objChangeRequest1058VO.setDrawingDueDate(objChangeRequest1058Form.getDrawingDueDate());
				objChangeRequest1058VO.setCompletionDate(objChangeRequest1058Form.getCompletionDate());
				if(objChangeRequest1058Form.getWorkOrderUSD().equalsIgnoreCase("") || objChangeRequest1058Form.getWorkOrderUSD() == null)
				{
					objChangeRequest1058VO.setWorkOrderUSD(new BigDecimal("0.00"));	
				}else{
					objChangeRequest1058VO.setWorkOrderUSD(new BigDecimal(objChangeRequest1058Form.getWorkOrderUSD()));
				}
				objChangeRequest1058VO.setStationAffected(objChangeRequest1058Form.getStationAffected());
				objChangeRequest1058VO.setUserID(objLoginVO.getUserID());
				
				intsysEngDetails  = objChangeRequest1058BI.sysEngrSecDetails(objChangeRequest1058VO);
				
				objChangeRequest1058VO.setOperationComments(objChangeRequest1058Form.getOperationComments());
				objChangeRequest1058VO.setDisposExcessMaterial(objChangeRequest1058Form.getDisposExcessMaterial());
				objChangeRequest1058VO.setSupplierAffectedCost(objChangeRequest1058Form.getSupplierAffectedCost());
				objChangeRequest1058VO.setLaborImpact(objChangeRequest1058Form.getLaborImpact());
				objChangeRequest1058VO.setRecEffectivityDel(objChangeRequest1058Form.getRecEffectivityDel());
				if(objChangeRequest1058Form.getToolingCostUSD().equalsIgnoreCase("") || objChangeRequest1058Form.getToolingCostUSD() == null)
				{
					objChangeRequest1058VO.setToolingCostUSD(new BigDecimal("0.00"));
				}else{
					objChangeRequest1058VO.setToolingCostUSD(new BigDecimal(objChangeRequest1058Form.getToolingCostUSD()));
				}
				if(objChangeRequest1058Form.getScrapCostUSD().equalsIgnoreCase("") || objChangeRequest1058Form.getScrapCostUSD() == null)
				{
					objChangeRequest1058VO.setScrapCostUSD(new BigDecimal("0.00"));
				}else{
					objChangeRequest1058VO.setScrapCostUSD(new BigDecimal(objChangeRequest1058Form.getScrapCostUSD()));
				}
				if(objChangeRequest1058Form.getReworkCost().equalsIgnoreCase("") || objChangeRequest1058Form.getReworkCost() == null)
				{
					objChangeRequest1058VO.setReworkCost(new BigDecimal("0.00"));
				}else{
					objChangeRequest1058VO.setReworkCost(new BigDecimal(objChangeRequest1058Form.getReworkCost()));
				}
				intoprDetails   = objChangeRequest1058BI.operationsSecDetails(objChangeRequest1058VO);
				
				objChangeRequest1058VO.setFinanceComments(objChangeRequest1058Form.getFinanceComments());
				objChangeRequest1058VO.setProdCostChange(objChangeRequest1058Form.getProdCostChange());
				objChangeRequest1058VO.setProdCostChangeSup(objChangeRequest1058Form.getProdCostChangeSup());
							
				intfinDetails   = objChangeRequest1058BI.financeSecDetails(objChangeRequest1058VO);
				
				objChangeRequest1058VO.setProgManagerComments(objChangeRequest1058Form.getProgManagerComments());
				
				intpgmMgrDetails   = objChangeRequest1058BI.programMgrSecDetails(objChangeRequest1058VO);
				
				objChangeRequest1058VO.setPropManagerComments(objChangeRequest1058Form.getPropManagerComments());
				if(objChangeRequest1058Form.getSellpriceCustomer().equalsIgnoreCase("") || objChangeRequest1058Form.getSellpriceCustomer() == null)
				{
					objChangeRequest1058VO.setSellpriceCustomer(new BigDecimal("0.00"));
				}else{
					objChangeRequest1058VO.setSellpriceCustomer(new BigDecimal(objChangeRequest1058Form.getSellpriceCustomer()));
				}
				if(objChangeRequest1058Form.getMarkUp().equalsIgnoreCase("") || objChangeRequest1058Form.getMarkUp() == null)
				{
					objChangeRequest1058VO.setMarkUp(new BigDecimal("0.00"));
				}else{
					objChangeRequest1058VO.setMarkUp(new BigDecimal(objChangeRequest1058Form.getMarkUp()));
				}
				objChangeRequest1058VO.setCustomerApprovalReq(objChangeRequest1058Form.getCustomerApprovalReq());
				objChangeRequest1058VO.setCustomerDecision(objChangeRequest1058Form.getCustomerDecision());
				objChangeRequest1058VO.setCustomerDecisionDate(objChangeRequest1058Form.getCustomerDecisionDate());
				objChangeRequest1058VO.setCustomerApprovalDet(objChangeRequest1058Form.getCustApprovalDet());
				if(objChangeRequest1058Form.getActualSellPrice().equalsIgnoreCase("") 
				   || objChangeRequest1058Form.getActualSellPrice() == null)
				{
					objChangeRequest1058VO.setActualSellPrice(new BigDecimal("0.00"));
				}else{
					objChangeRequest1058VO.setActualSellPrice(new BigDecimal(objChangeRequest1058Form.getActualSellPrice()));
				}
				
				intpropMgrDetails   = objChangeRequest1058BI.proposalMgrSecDetails(objChangeRequest1058VO);	
				
				LogUtil.logMessage(" objChangeRequest1058Form.getStatusSeqNo(): " + objChangeRequest1058Form.getStatusSeqNo());
							
				objChangeRequest1058VO.setSectionSeqNo(ApplicationConstants.SECTION_SEQ_NO_ZERO);
				objChangeRequest1058VO.setSectionStatus(ApplicationConstants.SUBMITTED_TWO);
				
				intinsertActionRecord = objChangeRequest1058BI.insert1058ActionRecord(objChangeRequest1058VO);
				
				if(intinsertActionRecord == 0){
	  				 objChangeRequest1058Form.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);               	
	            }
			}
			
			arlRequest1058List = objChangeRequest1058BI.fetch1058Details(objChangeRequest1058VO);			
				
			if (arlRequest1058List != null) {
				LogUtil.logMessage(arlRequest1058List);
				objChangeRequest1058Form.setRequestDetailsList(arlRequest1058List);
			}
			LogUtil.logMessage("ArrayList Value in ChangeRequest1058Action:"
					+ objChangeRequest1058Form.getRequestDetailsList().size());
			
			//Request 1058 Categories
			arlCategories = objChangeRequest1058BI.fetch1058RequestCategories(objLoginVO);

			if (arlCategories != null) {
				LogUtil.logMessage("Request Categories "+arlCategories);
				objChangeRequest1058Form.setCategories(arlCategories);
			}

			//Request 1058 Request Types
			arlRequestTypes = objChangeRequest1058BI.fetch1058RequestTypes(objLoginVO);
			
			if (arlRequestTypes != null) {
				LogUtil.logMessage("Request Types "+arlRequestTypes);
				objChangeRequest1058Form.setReqTypes(arlRequestTypes);
			}
			
			//Request LSDB Clause Change Types			
			arlClauseChangeTypes = objChangeRequest1058BI.fetch1058ClauseChangeTypes(objLoginVO);
			
			if (arlClauseChangeTypes != null) {
				LogUtil.logMessage("Clause Change Types "+arlClauseChangeTypes);
				objChangeRequest1058Form.setClauseChangeTypes(arlClauseChangeTypes);
			}
			
			objChangeRequest1058VO.setClaChngReqSeq(0);
			
			//Request LSDB Clause Changes Details
			if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.NO)){
				arlReqSpecChngClauses=objChangeRequest1058BI.fetchLsdbClauseDetails(objChangeRequest1058VO);
				if (arlReqSpecChngClauses != null) {
					LogUtil.logMessage("Requested Specification Change "+arlReqSpecChngClauses);
					objChangeRequest1058Form.setReqSpecChngClauseList(arlReqSpecChngClauses);
				}
			}

			//Request Non LSDB Clause Changes Details
			if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.YES)){
				
				ArrayList arlClauseList = objChangeRequest1058BI
						.selectClauseNonLsdb(objChangeRequest1058VO);
				objChangeRequest1058Form.setClaList(arlClauseList);
				LogUtil.logMessage("arlClauseList=" + arlClauseList.size());
			}
			
			
			//RepresentativeList Details
			//Added For CR-120
			UserVO objUserVO = new UserVO();
			objUserVO.setUserID(objLoginVO.getUserID());
			objUserVO.setOrderbyFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
			arlRepresentativeList = objChangeRequest1058BI.fetchRepresentativeDetails(objUserVO);
				
			if (arlRepresentativeList != null) {
				LogUtil.logMessage(arlRepresentativeList);
				objChangeRequest1058Form.setRepresentativeList(arlRepresentativeList);
			}
			LogUtil.logMessage("ArrayList Value in ChangeRequest1058Action:"
					+ objChangeRequest1058Form.getRepresentativeList().size());
			//Added for CR-127 starts here
			//Model Clause Changes Details
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			
			arlMdlClaChangesList = objChangeRequest1058BI.fetch1058MdlClaChanges(objChangeRequest1058VO);
			if (arlMdlClaChangesList != null) {
				objChangeRequest1058Form.setMdlClaChangesList(arlMdlClaChangesList);
			}
			LogUtil.logMessage("model clause change list" +arlMdlClaChangesList.size());
			for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
			}
			//Added for CR-127 ends here
			
			//Added for CR-130 starts here
			//SubSection Details
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			arlImportSubSecList = objChangeRequest1058BI.fetchSubSectionList(objChangeRequest1058VO);
				if (arlImportSubSecList != null) {
					
					objChangeRequest1058Form.setImportSubSecList(arlImportSubSecList);
					objChangeRequest1058Form.setImportSubSecListSize(arlImportSubSecList.size());
				}
			LogUtil.logMessage("Import Sub Section List" +arlImportSubSecList.size());
			
			for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
			}
			
			//1058 SubSection Details
			objChangeRequest1058VO.setSubSecChngReqSeq(0);
			
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			arl1058SubSecList = objChangeRequest1058BI.fetch1058SubSectionList(objChangeRequest1058VO);
				if (arl1058SubSecList != null) {
					
					objChangeRequest1058Form.setSubSec1058List(arl1058SubSecList);
				}
			LogUtil.logMessage("Import Sub Section List" +arl1058SubSecList.size());
			
			for (int cnt = 0; cnt < arl1058SubSecList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arl1058SubSecList.get(cnt);
			}
			//Added for CR-130 ends here
			
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ChangeRequest1058Action..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/*******************************************************************************************
	 *  * * *		This Method is used to complete 1058 request
	 *  
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return     	ActionForward  		   
	 * @throws     	EMDException
	 
	 ******************************************************************************************/
	
	public ActionForward complete(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into ChangeRequest1058Action:complete");
		String strForwardKey = ApplicationConstants.CREATE;
		int intComplete = 0;
		ArrayList arlRequest1058List = new ArrayList();
		ArrayList arlRepresentativeList = new ArrayList();
		ArrayList arlCategories = new ArrayList();
		ArrayList arlRequestTypes = new ArrayList();
		ArrayList arlClauseChangeTypes = new ArrayList();
		ArrayList arlReqSpecChngClauses = new ArrayList();
		ArrayList arlMdlClaChangesList = new ArrayList();//Added for CR-127
		ArrayList arlImportSubSecList   = new ArrayList();//Added for CR-130
		ArrayList arl1058SubSecList   = new ArrayList();//Added for CR-130
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LogUtil.logMessage(objSession);
		ChangeRequest1058VO objChangeRequest1058VO =new ChangeRequest1058VO();
		try {
			LoginVO objLoginVO = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVO);
			
			ChangeRequest1058Form objChangeRequest1058Form = (ChangeRequest1058Form) objActionForm;
			objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
			objChangeRequest1058VO.setUserID(objLoginVO.getUserID());
			
			if(objChangeRequest1058VO.getSeqNo1058()==0){
				objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
				objChangeRequest1058VO.setOrderNo(objChangeRequest1058Form.getOrderNo());
				objChangeRequest1058VO.setCustSeqNo(objChangeRequest1058Form.getCustSeqNo());
				objChangeRequest1058VO.setMdlSeqNo(objChangeRequest1058Form.getMdlSeqNo());
				objChangeRequest1058VO.setStatusSeqNo(objChangeRequest1058Form.getStatusSeqNo());
				objChangeRequest1058VO.setAwaitingApprvrUser(objChangeRequest1058Form.getAwaitingApprvrUser());
				objChangeRequest1058VO.setNonLsdbFlag(objChangeRequest1058Form.getNonLsdbFlag());
			}
			ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();

			if(objHttpServletRequest.getParameter("statusSeqNo").equalsIgnoreCase("5")){
				objChangeRequest1058VO.setSectionSeqNo(ApplicationConstants.SECTION_SEQ_NO_ZERO);
				objChangeRequest1058VO.setSectionStatus(ApplicationConstants.SECTION_STATUS_COMPLETE);
				intComplete  = objChangeRequest1058BI.complete(objChangeRequest1058VO);
			}//Added for CR-117 to cancel state
			else if(objHttpServletRequest.getParameter("statusSeqNo").equalsIgnoreCase("10")){
				objChangeRequest1058VO.setSectionSeqNo(ApplicationConstants.SECTION_SEQ_NO_ZERO);
				objChangeRequest1058VO.setSectionStatus(ApplicationConstants.SECTION_STATUS_CANCEL);
				intComplete  = objChangeRequest1058BI.complete(objChangeRequest1058VO);
			}//Added for CR-117 to cancel state ends here
			else{
				objChangeRequest1058VO.setSectionSeqNo(ApplicationConstants.SECTION_SEQ_NO_ZERO);
				objChangeRequest1058VO.setSectionStatus(ApplicationConstants.PENDING_ONE);
				intComplete  = objChangeRequest1058BI.complete(objChangeRequest1058VO);
			}
			
			if(intComplete == 0){
  				 objChangeRequest1058Form.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);               	
            }		
			
			arlRequest1058List = objChangeRequest1058BI.fetch1058Details(objChangeRequest1058VO);
				
			if (arlRequest1058List != null) {
				LogUtil.logMessage(arlRequest1058List);
				objChangeRequest1058Form.setRequestDetailsList(arlRequest1058List);
			}
			LogUtil.logMessage("ArrayList Value in ChangeRequest1058Action:"
					+ objChangeRequest1058Form.getRequestDetailsList().size());
			
			//Request 1058 Categories
			arlCategories = objChangeRequest1058BI.fetch1058RequestCategories(objLoginVO);

			if (arlCategories != null) {
				LogUtil.logMessage("Request Categories "+arlCategories);
				objChangeRequest1058Form.setCategories(arlCategories);
			}

			//Request 1058 Request Types
			arlRequestTypes = objChangeRequest1058BI.fetch1058RequestTypes(objLoginVO);
			
			if (arlRequestTypes != null) {
				LogUtil.logMessage("Request Types "+arlRequestTypes);
				objChangeRequest1058Form.setReqTypes(arlRequestTypes);
			}
			
			//Request LSDB Clause Change Types			
			arlClauseChangeTypes = objChangeRequest1058BI.fetch1058ClauseChangeTypes(objLoginVO);
			
			if (arlClauseChangeTypes != null) {
				LogUtil.logMessage("Clause Change Types "+arlClauseChangeTypes);
				objChangeRequest1058Form.setClauseChangeTypes(arlClauseChangeTypes);
			}
			
			objChangeRequest1058VO.setClaChngReqSeq(0);
			
			//Request LSDB Clause Changes Details
			if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.NO)){
				arlReqSpecChngClauses=objChangeRequest1058BI.fetchLsdbClauseDetails(objChangeRequest1058VO);
				if (arlReqSpecChngClauses != null) {
					LogUtil.logMessage("Requested Specification Change "+arlReqSpecChngClauses);
					objChangeRequest1058Form.setReqSpecChngClauseList(arlReqSpecChngClauses);
				}
			}

			//Request Non LSDB Clause Changes Details
			if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.YES)){
				
				ArrayList arlClauseList = objChangeRequest1058BI
						.selectClauseNonLsdb(objChangeRequest1058VO);
				objChangeRequest1058Form.setClaList(arlClauseList);
				LogUtil.logMessage("arlClauseList=" + arlClauseList.size());
			}
			//RepresentativeList Details
			//Added For CR-120
			UserVO objUserVO = new UserVO();
			objUserVO.setUserID(objLoginVO.getUserID());
			objUserVO.setOrderbyFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
			arlRepresentativeList = objChangeRequest1058BI.fetchRepresentativeDetails(objUserVO);
				
			if (arlRepresentativeList != null) {
				LogUtil.logMessage(arlRepresentativeList);
				objChangeRequest1058Form.setRepresentativeList(arlRepresentativeList);
			}
			LogUtil.logMessage("ArrayList Value in ChangeRequest1058Action:"
					+ objChangeRequest1058Form.getRepresentativeList().size());
			//Added for CR-127 starts here
			//Model Clause Changes Details
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			
			arlMdlClaChangesList = objChangeRequest1058BI.fetch1058MdlClaChanges(objChangeRequest1058VO);
			if (arlMdlClaChangesList != null) {
				objChangeRequest1058Form.setMdlClaChangesList(arlMdlClaChangesList);
			}
			LogUtil.logMessage("model clause change list" +arlMdlClaChangesList.size());
			for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
			}
			//Added for CR-127 ends here
			
			//Added for CR-130 starts here
			//SubSection Details
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			arlImportSubSecList = objChangeRequest1058BI.fetchSubSectionList(objChangeRequest1058VO);
				if (arlImportSubSecList != null) {
					
					objChangeRequest1058Form.setImportSubSecList(arlImportSubSecList);
					objChangeRequest1058Form.setImportSubSecListSize(arlImportSubSecList.size());
				}
			LogUtil.logMessage("Import Sub Section List" +arlImportSubSecList.size());
			
			for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
			}
			
			//1058 SubSection Details
			objChangeRequest1058VO.setSubSecChngReqSeq(0);
			
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			arl1058SubSecList = objChangeRequest1058BI.fetch1058SubSectionList(objChangeRequest1058VO);
				if (arl1058SubSecList != null) {
					
					objChangeRequest1058Form.setSubSec1058List(arl1058SubSecList);
				}
			LogUtil.logMessage("Import Sub Section List" +arl1058SubSecList.size());
			
			for (int cnt = 0; cnt < arl1058SubSecList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arl1058SubSecList.get(cnt);
			}
			//Added for CR-130 ends here
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ChangeRequest1058Action..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	//added for cr-110----- @108447 ---start
	/***************************************************************************
		 * This Method is used to add Clauses in Non Lsdb 1058 request
		 * 
		 * @author Satyam Computer Services Ltd
		 * @version 1.0
		 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
		 * @return ActionForward
		 * @throws EMDException
		 * 
		 **************************************************************************/

		public ActionForward addClauseNonLsdb(ActionMapping objActionMapping,
				ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
				HttpServletResponse objHttpServletResponse) throws EMDException {

			LogUtil.logMessage("Enters into ChangeRequest1058Action:fetchRequestDetails");
			String strForwardKey = ApplicationConstants.CREATE;
			ArrayList arlRepresentativeList = new ArrayList();
			ArrayList arlClauseList = new ArrayList();
			ArrayList arlCategories =new ArrayList();
			ArrayList arlRequestTypes =new ArrayList();
			ArrayList arlClauseChangeTypes  =new ArrayList();
			ArrayList arlReqSpecChngClauses=new ArrayList();
			ArrayList arlMdlClaChangesList = new ArrayList();//Added for CR-127
			ArrayList arlImportSubSecList   = new ArrayList();//Added for CR-130
			ArrayList arl1058SubSecList   = new ArrayList();//Added for CR-130
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			ChangeRequest1058VO objChangeRequest1058VO = new ChangeRequest1058VO();
			int intAddUser = 0;
			LogUtil.logMessage(objSession);
			try {
				LoginVO objLoginVO = (LoginVO) objSession
						.getAttribute(ApplicationConstants.USER_IN_SESSION);
				LogUtil.logMessage(objLoginVO);

				ChangeRequest1058Form objChangeRequest1058Form = (ChangeRequest1058Form) objActionForm;
				
				objChangeRequest1058VO.setUserID(objLoginVO.getUserID());
				objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
				ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
				
				if (objChangeRequest1058Form.getStatusSeqNo() == ApplicationConstants.PENDING_ONE ) {
												
					objChangeRequest1058VO.setOrderNo(objChangeRequest1058Form.getOrderNo());
					objChangeRequest1058VO.setCustName(objChangeRequest1058Form.getCustName());
					objChangeRequest1058VO.setMdlName(objChangeRequest1058Form.getMdlName());
					objChangeRequest1058VO.setOrderQty(objChangeRequest1058Form.getOrderQty());
					objChangeRequest1058VO.setReqTypeSeqNo(objChangeRequest1058Form.getReqTypeSeqNo());
					objChangeRequest1058VO.setCategorySeqNo(objChangeRequest1058Form.getCategorySeqNo());
					objChangeRequest1058VO.setGenDesc(objChangeRequest1058Form.getGenDesc());
					objChangeRequest1058VO.setUnitNumber(objChangeRequest1058Form.getUnitNumber());
					objChangeRequest1058VO.setMcrNumber(objChangeRequest1058Form.getMcrNumber());
					objChangeRequest1058VO.setRoadNumber(objChangeRequest1058Form.getRoadNumber());
					objChangeRequest1058VO.setMcrReq(objChangeRequest1058Form.getMcrReq());			
					objChangeRequest1058VO.setSpecRev(objChangeRequest1058Form.getSpecRev());
					objChangeRequest1058VO.setNonLsdbFlag(objChangeRequest1058Form.getHdnNonLsdbFlag());
	
					
					int intUpdate1058details  = objChangeRequest1058BI.update1058details(objChangeRequest1058VO);
					
				}
				
				objChangeRequest1058VO.setChangeFromSpec(objChangeRequest1058Form
						.getChangeFromSpec());
				objChangeRequest1058VO
						.setChangeFromClaDesc(objChangeRequest1058Form
								.getChangeFromClaDesc());
				objChangeRequest1058VO
						.setChangeFromEngData(objChangeRequest1058Form
								.getChangeFromEngData());
				objChangeRequest1058VO.setChangeToSpec(objChangeRequest1058Form
						.getChangeToSpec());
				objChangeRequest1058VO.setChangeToClaDesc(objChangeRequest1058Form
						.getChangeToClaDesc());
				objChangeRequest1058VO.setChangeToEngData(objChangeRequest1058Form
						.getChangeToEngData());
				objChangeRequest1058VO.setChangeClaReason(objChangeRequest1058Form
						.getChangeClaReason());
				intAddUser = objChangeRequest1058BI.addClauseNonLsdb(objChangeRequest1058VO);
				
                if(intAddUser == 0){
                	
                 //Reset Form Fields
   				 objChangeRequest1058Form.setChangeFromSpec("");
   				 objChangeRequest1058Form.setChangeFromClaDesc("");
   				 objChangeRequest1058Form.setChangeFromEngData("");
   				 objChangeRequest1058Form.setChangeToSpec("");
   				 objChangeRequest1058Form.setChangeToClaDesc("");
   				 objChangeRequest1058Form.setChangeToEngData("");
   				 objChangeRequest1058Form.setChangeClaReason("");
   				 objChangeRequest1058Form.setHdnSelectedChangeReqSeqNo(0);
   				 objChangeRequest1058Form.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
                	
                }else{
                	 objChangeRequest1058Form.setMessageID("" +intAddUser);
                	 objChangeRequest1058Form.setChangeFromSpec(objChangeRequest1058Form.getChangeFromSpec());
      				 objChangeRequest1058Form.setChangeFromClaDesc(objChangeRequest1058Form.getChangeFromClaDesc());
      				 objChangeRequest1058Form.setChangeFromEngData(objChangeRequest1058Form.getChangeFromEngData());
      				 objChangeRequest1058Form.setChangeToSpec(objChangeRequest1058Form.getChangeToSpec());
      				 objChangeRequest1058Form.setChangeToClaDesc(objChangeRequest1058Form.getChangeToClaDesc());
      				 objChangeRequest1058Form.setChangeToEngData(objChangeRequest1058Form.getChangeToEngData());
      				 objChangeRequest1058Form.setChangeClaReason(objChangeRequest1058Form.getChangeClaReason());
      				 objChangeRequest1058Form.setHdnSelectedChangeReqSeqNo(objChangeRequest1058Form.getHdnSelectedChangeReqSeqNo());
                }
				
				// fetching existing details
				objChangeRequest1058VO.setOrderNo(objChangeRequest1058Form
						.getOrderNo());
				objChangeRequest1058VO.setCustSeqNo(objChangeRequest1058Form
						.getCustSeqNo());
				objChangeRequest1058VO.setMdlSeqNo(objChangeRequest1058Form
						.getMdlSeqNo());
				objChangeRequest1058VO.setStatusSeqNo(objChangeRequest1058Form
						.getStatusSeqNo());
				objChangeRequest1058VO.setAwApproval(objChangeRequest1058Form
						.getAwaitingApprvrUser());
				objChangeRequest1058VO.setNonLsdbFlag(objChangeRequest1058Form
						.getNonLsdbFlag());

				ArrayList arlRequest1058List = objChangeRequest1058BI
						.fetch1058Details(objChangeRequest1058VO);
				objChangeRequest1058Form.setRequestDetailsList(arlRequest1058List);
				
				//Request 1058 Categories
				arlCategories = objChangeRequest1058BI.fetch1058RequestCategories(objLoginVO);

				if (arlCategories != null) {
					LogUtil.logMessage("Request Categories "+arlCategories);
					objChangeRequest1058Form.setCategories(arlCategories);
				}

				//Request 1058 Request Types
				arlRequestTypes = objChangeRequest1058BI.fetch1058RequestTypes(objLoginVO);
				
				if (arlRequestTypes != null) {
					LogUtil.logMessage("Request Types "+arlRequestTypes);
					objChangeRequest1058Form.setReqTypes(arlRequestTypes);
				}
				
				//Request LSDB Clause Change Types			
				arlClauseChangeTypes = objChangeRequest1058BI.fetch1058ClauseChangeTypes(objLoginVO);
				
				if (arlClauseChangeTypes != null) {
					LogUtil.logMessage("Clause Change Types "+arlClauseChangeTypes);
					objChangeRequest1058Form.setClauseChangeTypes(arlClauseChangeTypes);
				}
				
				objChangeRequest1058VO.setClaChngReqSeq(0);
				
				//Request LSDB Clause Changes Details
				if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.NO)){
					arlReqSpecChngClauses=objChangeRequest1058BI.fetchLsdbClauseDetails(objChangeRequest1058VO);
					if (arlReqSpecChngClauses != null) {
						LogUtil.logMessage("Requested Specification Change "+arlReqSpecChngClauses);
						objChangeRequest1058Form.setReqSpecChngClauseList(arlReqSpecChngClauses);
					}
				}

				//Request Non LSDB Clause Changes Details
				if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.YES)){
					
					arlClauseList = objChangeRequest1058BI
							.selectClauseNonLsdb(objChangeRequest1058VO);
					objChangeRequest1058Form.setClaList(arlClauseList);
					LogUtil.logMessage("arlClauseList=" + arlClauseList.size());
				}
				
				//RepresentativeList Details
				//Added For CR-120
				UserVO objUserVO = new UserVO();
				objUserVO.setUserID(objLoginVO.getUserID());
				objUserVO.setOrderbyFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
				arlRepresentativeList = objChangeRequest1058BI.fetchRepresentativeDetails(objUserVO);
					
				if (arlRepresentativeList != null) {
					LogUtil.logMessage(arlRepresentativeList);
					objChangeRequest1058Form.setRepresentativeList(arlRepresentativeList);
				}
				LogUtil.logMessage("ArrayList Value in ChangeRequest1058Action:"
						+ objChangeRequest1058Form.getRepresentativeList().size());

				

				LogUtil.logMessage("arlClauseList=" + arlClauseList.size());
				//Added for CR-127 starts here
				//Model Clause Changes Details
				for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
					objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
				}
				arlMdlClaChangesList = objChangeRequest1058BI.fetch1058MdlClaChanges(objChangeRequest1058VO);
				if (arlMdlClaChangesList != null) {
					objChangeRequest1058Form.setMdlClaChangesList(arlMdlClaChangesList);
				}
				LogUtil.logMessage("model clause change list" +arlMdlClaChangesList.size());
				for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
					objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
				}
				//Added for CR-127 ends here
				
				//Added for CR-130 starts here
				//SubSection Details
				for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
					objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
				}
				arlImportSubSecList = objChangeRequest1058BI.fetchSubSectionList(objChangeRequest1058VO);
					if (arlImportSubSecList != null) {
						
						objChangeRequest1058Form.setImportSubSecList(arlImportSubSecList);
						objChangeRequest1058Form.setImportSubSecListSize(arlImportSubSecList.size());
					}
				LogUtil.logMessage("Import Sub Section List" +arlImportSubSecList.size());
				
				for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
					objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
				}
				
				//1058 SubSection Details
				objChangeRequest1058VO.setSubSecChngReqSeq(0);
				
				for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
					objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
				}
				arl1058SubSecList = objChangeRequest1058BI.fetch1058SubSectionList(objChangeRequest1058VO);
					if (arl1058SubSecList != null) {
						
						objChangeRequest1058Form.setSubSec1058List(arl1058SubSecList);
					}
				LogUtil.logMessage("Import Sub Section List" +arl1058SubSecList.size());
				
				for (int cnt = 0; cnt < arl1058SubSecList.size(); cnt++) {
					objChangeRequest1058VO = (ChangeRequest1058VO) arl1058SubSecList.get(cnt);
				}
				//Added for CR-130 ends here
			} catch (Exception objExp) {
				LogUtil
						.logMessage("Enters into Exception Block in ChangeRequest1058Action..");
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
		 * * * * This Method is used to update Clauses in Non Lsdb 1058 request
		 * 
		 * @author Satyam Computer Services Ltd
		 * @version 1.0
		 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
		 * @return ActionForward
		 * @throws EMDException
		 * 
		 **************************************************************************/
		public ActionForward updateClauseNonLsdb(ActionMapping objActionMapping,
				ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
				HttpServletResponse objHttpServletResponse) throws EMDException {

			LogUtil
					.logMessage("Enters into ChangeRequest1058Action:fetchRequestDetails");
			String strForwardKey = ApplicationConstants.CREATE;
			ArrayList arlClauseList = new ArrayList();
			ArrayList arlCategories =new ArrayList();
			ArrayList arlRequestTypes =new ArrayList();
			ArrayList arlClauseChangeTypes  =new ArrayList();
			ArrayList arlReqSpecChngClauses=new ArrayList();
			ArrayList arlRepresentativeList = new ArrayList();
			ArrayList arlMdlClaChangesList = new ArrayList();//Added for CR-127
			ArrayList arlImportSubSecList   = new ArrayList();//Added for CR-130
			ArrayList arl1058SubSecList   = new ArrayList();//Added for CR-130
			
			int intUpdateUser = 0;
			HttpSession objSession = objHttpServletRequest.getSession(false);
			ChangeRequest1058VO objChangeRequest1058VO = new ChangeRequest1058VO();
			LogUtil.logMessage(objSession);
			try {
				LoginVO objLoginVO = (LoginVO) objSession
						.getAttribute(ApplicationConstants.USER_IN_SESSION);
				LogUtil.logMessage(objLoginVO);

				ChangeRequest1058Form objChangeRequest1058Form = (ChangeRequest1058Form) objActionForm;
				objChangeRequest1058VO.setUserID(objLoginVO.getUserID());
				objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());			

				ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
				
				if (objChangeRequest1058Form.getStatusSeqNo() == ApplicationConstants.PENDING_ONE ) {
												
					objChangeRequest1058VO.setOrderNo(objChangeRequest1058Form.getOrderNo());
					objChangeRequest1058VO.setCustName(objChangeRequest1058Form.getCustName());
					objChangeRequest1058VO.setMdlName(objChangeRequest1058Form.getMdlName());
					objChangeRequest1058VO.setOrderQty(objChangeRequest1058Form.getOrderQty());
					objChangeRequest1058VO.setReqTypeSeqNo(objChangeRequest1058Form.getReqTypeSeqNo());
					objChangeRequest1058VO.setCategorySeqNo(objChangeRequest1058Form.getCategorySeqNo());
					objChangeRequest1058VO.setGenDesc(objChangeRequest1058Form.getGenDesc());
					objChangeRequest1058VO.setUnitNumber(objChangeRequest1058Form.getUnitNumber());
					objChangeRequest1058VO.setMcrNumber(objChangeRequest1058Form.getMcrNumber());
					objChangeRequest1058VO.setRoadNumber(objChangeRequest1058Form.getRoadNumber());
					objChangeRequest1058VO.setMcrReq(objChangeRequest1058Form.getMcrReq());			
					objChangeRequest1058VO.setSpecRev(objChangeRequest1058Form.getSpecRev());
					objChangeRequest1058VO.setNonLsdbFlag(objChangeRequest1058Form.getHdnNonLsdbFlag());
					
					int intUpdate1058details  = objChangeRequest1058BI.update1058details(objChangeRequest1058VO);
		
				}
				
				objChangeRequest1058VO
						.setClaChangeReqSeqNo(objChangeRequest1058Form
								.getHdnSelectedChangeReqSeqNo());
				objChangeRequest1058VO.setChangeFromSpec(objChangeRequest1058Form
						.getChangeFromSpec());
				objChangeRequest1058VO
						.setChangeFromClaDesc(objChangeRequest1058Form
								.getChangeFromClaDesc());
				objChangeRequest1058VO
						.setChangeFromEngData(objChangeRequest1058Form
								.getChangeFromEngData());
				objChangeRequest1058VO.setChangeToSpec(objChangeRequest1058Form
						.getChangeToSpec());
				objChangeRequest1058VO.setChangeToClaDesc(objChangeRequest1058Form
						.getChangeToClaDesc());
				objChangeRequest1058VO.setChangeToEngData(objChangeRequest1058Form
						.getChangeToEngData());
				objChangeRequest1058VO.setChangeClaReason(objChangeRequest1058Form
						.getChangeClaReason());

				intUpdateUser = objChangeRequest1058BI.updateClauseNonLsdb(objChangeRequest1058VO);
				
				if(intUpdateUser == 0){
                	
	                 //Reset Form Fields
	   				 objChangeRequest1058Form.setChangeFromSpec("");
	   				 objChangeRequest1058Form.setChangeFromClaDesc("");
	   				 objChangeRequest1058Form.setChangeFromEngData("");
	   				 objChangeRequest1058Form.setChangeToSpec("");
	   				 objChangeRequest1058Form.setChangeToClaDesc("");
	   				 objChangeRequest1058Form.setChangeToEngData("");
	   				 objChangeRequest1058Form.setChangeClaReason("");
	   				 objChangeRequest1058Form.setHdnSelectedChangeReqSeqNo(0);
	   				 objChangeRequest1058Form.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
	                	
	                }else{
	                	 objChangeRequest1058Form.setMessageID("" +intUpdateUser);
	                	 objChangeRequest1058Form.setChangeFromSpec(objChangeRequest1058Form.getChangeFromSpec());
	      				 objChangeRequest1058Form.setChangeFromClaDesc(objChangeRequest1058Form.getChangeFromClaDesc());
	      				 objChangeRequest1058Form.setChangeFromEngData(objChangeRequest1058Form.getChangeFromEngData());
	      				 objChangeRequest1058Form.setChangeToSpec(objChangeRequest1058Form.getChangeToSpec());
	      				 objChangeRequest1058Form.setChangeToClaDesc(objChangeRequest1058Form.getChangeToClaDesc());
	      				 objChangeRequest1058Form.setChangeToEngData(objChangeRequest1058Form.getChangeToEngData());
	      				 objChangeRequest1058Form.setChangeClaReason(objChangeRequest1058Form.getChangeClaReason());
	      				 objChangeRequest1058Form.setHdnSelectedChangeReqSeqNo(objChangeRequest1058Form.getHdnSelectedChangeReqSeqNo());
	                }

				// fetching existing details
				objChangeRequest1058VO.setOrderNo(objChangeRequest1058Form
						.getOrderNo());
				objChangeRequest1058VO.setCustSeqNo(objChangeRequest1058Form
						.getCustSeqNo());
				objChangeRequest1058VO.setMdlSeqNo(objChangeRequest1058Form
						.getMdlSeqNo());
				objChangeRequest1058VO.setStatusSeqNo(objChangeRequest1058Form
						.getStatusSeqNo());
				objChangeRequest1058VO.setAwApproval(objChangeRequest1058Form
						.getAwaitingApprvrUser());
				objChangeRequest1058VO.setNonLsdbFlag(objChangeRequest1058Form
						.getNonLsdbFlag());

				ArrayList arlRequest1058List = objChangeRequest1058BI
						.fetch1058Details(objChangeRequest1058VO);
				objChangeRequest1058Form.setRequestDetailsList(arlRequest1058List);
				objChangeRequest1058Form.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				
				//Request 1058 Categories
				arlCategories = objChangeRequest1058BI.fetch1058RequestCategories(objLoginVO);

				if (arlCategories != null) {
					LogUtil.logMessage("Request Categories "+arlCategories);
					objChangeRequest1058Form.setCategories(arlCategories);
				}

				//Request 1058 Request Types
				arlRequestTypes = objChangeRequest1058BI.fetch1058RequestTypes(objLoginVO);
				
				if (arlRequestTypes != null) {
					LogUtil.logMessage("Request Types "+arlRequestTypes);
					objChangeRequest1058Form.setReqTypes(arlRequestTypes);
				}
				
				//Request LSDB Clause Change Types			
				arlClauseChangeTypes = objChangeRequest1058BI.fetch1058ClauseChangeTypes(objLoginVO);
				
				if (arlClauseChangeTypes != null) {
					LogUtil.logMessage("Clause Change Types "+arlClauseChangeTypes);
					objChangeRequest1058Form.setClauseChangeTypes(arlClauseChangeTypes);
				}
				
				objChangeRequest1058VO.setClaChngReqSeq(0);
				
				//Request LSDB Clause Changes Details
				if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.NO)){
					arlReqSpecChngClauses=objChangeRequest1058BI.fetchLsdbClauseDetails(objChangeRequest1058VO);
					if (arlReqSpecChngClauses != null) {
						LogUtil.logMessage("Requested Specification Change "+arlReqSpecChngClauses);
						objChangeRequest1058Form.setReqSpecChngClauseList(arlReqSpecChngClauses);
					}
				}

				//Request Non LSDB Clause Changes Details
				if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.YES)){
					
					arlClauseList = objChangeRequest1058BI
							.selectClauseNonLsdb(objChangeRequest1058VO);
					objChangeRequest1058Form.setClaList(arlClauseList);
					LogUtil.logMessage("arlClauseList=" + arlClauseList.size());
				}
				
				//RepresentativeList Details
				//Added For CR-120
				UserVO objUserVO = new UserVO();
				objUserVO.setUserID(objLoginVO.getUserID());
				objUserVO.setOrderbyFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
				arlRepresentativeList = objChangeRequest1058BI.fetchRepresentativeDetails(objUserVO);
					
				if (arlRepresentativeList != null) {
					LogUtil.logMessage(arlRepresentativeList);
					objChangeRequest1058Form.setRepresentativeList(arlRepresentativeList);
				}
				LogUtil.logMessage("ArrayList Value in ChangeRequest1058Action:"
						+ objChangeRequest1058Form.getRepresentativeList().size());
				
				//Added for CR-127 starts here
				//Model Clause Changes Details
				for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
					objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
				}
				
				arlMdlClaChangesList = objChangeRequest1058BI.fetch1058MdlClaChanges(objChangeRequest1058VO);
				if (arlMdlClaChangesList != null) {
					objChangeRequest1058Form.setMdlClaChangesList(arlMdlClaChangesList);
				}
				LogUtil.logMessage("model clause change list" +arlMdlClaChangesList.size());
				for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
					objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
				}
				//Added for CR-127 ends here
				//Added for CR-130 starts here
				//SubSection Details
				for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
					objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
				}
				arlImportSubSecList = objChangeRequest1058BI.fetchSubSectionList(objChangeRequest1058VO);
					if (arlImportSubSecList != null) {
						
						objChangeRequest1058Form.setImportSubSecList(arlImportSubSecList);
						objChangeRequest1058Form.setImportSubSecListSize(arlImportSubSecList.size());
					}
				LogUtil.logMessage("Import Sub Section List" +arlImportSubSecList.size());
				
				for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
					objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
				}
				
				//1058 SubSection Details
				objChangeRequest1058VO.setSubSecChngReqSeq(0);
				
				for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
					objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
				}
				arl1058SubSecList = objChangeRequest1058BI.fetch1058SubSectionList(objChangeRequest1058VO);
					if (arl1058SubSecList != null) {
						
						objChangeRequest1058Form.setSubSec1058List(arl1058SubSecList);
					}
				LogUtil.logMessage("Import Sub Section List" +arl1058SubSecList.size());
				
				for (int cnt = 0; cnt < arl1058SubSecList.size(); cnt++) {
					objChangeRequest1058VO = (ChangeRequest1058VO) arl1058SubSecList.get(cnt);
				}
				//Added for CR-130 ends here
			} catch (Exception objExp) {
				LogUtil
						.logMessage("Enters into Exception Block in ChangeRequest1058Action..");
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
		 * * * * This Method is used to delete Clauses in Non Lsdb 1058 request
		 * 
		 * @author Satyam Computer Services Ltd
		 * @version 1.0
		 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
		 * @return ActionForward
		 * @throws EMDException
		 * 
		 **************************************************************************/
		public ActionForward deleteClauseNonLsdb(ActionMapping objActionMapping,
				ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
				HttpServletResponse objHttpServletResponse) throws EMDException {

			LogUtil.logMessage("Enters into ChangeRequest1058Action:deleteClauseNonLsdb");
			String strForwardKey = ApplicationConstants.CREATE;
			ArrayList arlClauseList = new ArrayList();
			ArrayList arlCategories =new ArrayList();
			ArrayList arlRequestTypes =new ArrayList();
			ArrayList arlClauseChangeTypes  =new ArrayList();
			ArrayList arlReqSpecChngClauses=new ArrayList();
			ArrayList arlRepresentativeList = new ArrayList();
			ArrayList arlMdlClaChangesList = new ArrayList();//Added for CR-127
			ArrayList arlImportSubSecList   = new ArrayList();//Added for CR-130
			ArrayList arl1058SubSecList   = new ArrayList();//Added for CR-130
			
			int intDeleteUser = 0;
			HttpSession objSession = objHttpServletRequest.getSession(false);
			ChangeRequest1058VO objChangeRequest1058VO = new ChangeRequest1058VO();
			LogUtil.logMessage(objSession);
			try {
				LoginVO objLoginVO = (LoginVO) objSession
						.getAttribute(ApplicationConstants.USER_IN_SESSION);
				LogUtil.logMessage(objLoginVO);

				ChangeRequest1058Form objChangeRequest1058Form = (ChangeRequest1058Form) objActionForm;
				objChangeRequest1058VO.setUserID(objLoginVO.getUserID());
				objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());

				ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
				
				if (objChangeRequest1058Form.getStatusSeqNo() == ApplicationConstants.PENDING_ONE ) {
												
					objChangeRequest1058VO.setOrderNo(objChangeRequest1058Form.getOrderNo());
					objChangeRequest1058VO.setCustName(objChangeRequest1058Form.getCustName());
					objChangeRequest1058VO.setMdlName(objChangeRequest1058Form.getMdlName());
					objChangeRequest1058VO.setOrderQty(objChangeRequest1058Form.getOrderQty());
					objChangeRequest1058VO.setReqTypeSeqNo(objChangeRequest1058Form.getReqTypeSeqNo());
					objChangeRequest1058VO.setCategorySeqNo(objChangeRequest1058Form.getCategorySeqNo());
					objChangeRequest1058VO.setGenDesc(objChangeRequest1058Form.getGenDesc());
					objChangeRequest1058VO.setUnitNumber(objChangeRequest1058Form.getUnitNumber());
					objChangeRequest1058VO.setMcrNumber(objChangeRequest1058Form.getMcrNumber());
					objChangeRequest1058VO.setRoadNumber(objChangeRequest1058Form.getRoadNumber());
					objChangeRequest1058VO.setMcrReq(objChangeRequest1058Form.getMcrReq());			
					objChangeRequest1058VO.setSpecRev(objChangeRequest1058Form.getSpecRev());
					objChangeRequest1058VO.setNonLsdbFlag(objChangeRequest1058Form.getHdnNonLsdbFlag());
					
					int intUpdate1058details  = objChangeRequest1058BI.update1058details(objChangeRequest1058VO);
		
				}
				
				objChangeRequest1058VO
						.setClaChangeReqSeqNo(objChangeRequest1058Form
								.getHdnSelectedChangeReqSeqNo());
				intDeleteUser = objChangeRequest1058BI.deleteClauseNonLsdb(objChangeRequest1058VO);
				
				if(intDeleteUser == 0){
                	
	                 //Reset Form Fields
	   				 objChangeRequest1058Form.setChangeFromSpec("");
	   				 objChangeRequest1058Form.setChangeFromClaDesc("");
	   				 objChangeRequest1058Form.setChangeFromEngData("");
	   				 objChangeRequest1058Form.setChangeToSpec("");
	   				 objChangeRequest1058Form.setChangeToClaDesc("");
	   				 objChangeRequest1058Form.setChangeToEngData("");
	   				 objChangeRequest1058Form.setChangeClaReason("");
	   				 objChangeRequest1058Form.setHdnSelectedChangeReqSeqNo(0);
	   				 objChangeRequest1058Form.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
	                	
	                }else{
	                	 objChangeRequest1058Form.setMessageID("" +intDeleteUser);
	                	 objChangeRequest1058Form.setChangeFromSpec(objChangeRequest1058Form.getChangeFromSpec());
	      				 objChangeRequest1058Form.setChangeFromClaDesc(objChangeRequest1058Form.getChangeFromClaDesc());
	      				 objChangeRequest1058Form.setChangeFromEngData(objChangeRequest1058Form.getChangeFromEngData());
	      				 objChangeRequest1058Form.setChangeToSpec(objChangeRequest1058Form.getChangeToSpec());
	      				 objChangeRequest1058Form.setChangeToClaDesc(objChangeRequest1058Form.getChangeToClaDesc());
	      				 objChangeRequest1058Form.setChangeToEngData(objChangeRequest1058Form.getChangeToEngData());
	      				 objChangeRequest1058Form.setChangeClaReason(objChangeRequest1058Form.getChangeClaReason());
	      				 objChangeRequest1058Form.setHdnSelectedChangeReqSeqNo(objChangeRequest1058Form.getHdnSelectedChangeReqSeqNo());
	                }

				// fetching existing details
				objChangeRequest1058VO.setOrderNo(objChangeRequest1058Form
						.getOrderNo());
				objChangeRequest1058VO.setCustSeqNo(objChangeRequest1058Form
						.getCustSeqNo());
				objChangeRequest1058VO.setMdlSeqNo(objChangeRequest1058Form
						.getMdlSeqNo());
				objChangeRequest1058VO.setStatusSeqNo(objChangeRequest1058Form
						.getStatusSeqNo());
				objChangeRequest1058VO.setAwApproval(objChangeRequest1058Form
						.getAwaitingApprvrUser());
				objChangeRequest1058VO.setNonLsdbFlag(objChangeRequest1058Form
						.getNonLsdbFlag());

				ArrayList arlRequest1058List = objChangeRequest1058BI
						.fetch1058Details(objChangeRequest1058VO);
				objChangeRequest1058Form.setRequestDetailsList(arlRequest1058List);
				objChangeRequest1058Form.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				
				//Request 1058 Categories
				arlCategories = objChangeRequest1058BI.fetch1058RequestCategories(objLoginVO);

				if (arlCategories != null) {
					LogUtil.logMessage("Request Categories "+arlCategories);
					objChangeRequest1058Form.setCategories(arlCategories);
				}

				//Request 1058 Request Types
				arlRequestTypes = objChangeRequest1058BI.fetch1058RequestTypes(objLoginVO);
				
				if (arlRequestTypes != null) {
					LogUtil.logMessage("Request Types "+arlRequestTypes);
					objChangeRequest1058Form.setReqTypes(arlRequestTypes);
				}
				
				//Request LSDB Clause Change Types			
				arlClauseChangeTypes = objChangeRequest1058BI.fetch1058ClauseChangeTypes(objLoginVO);
				
				if (arlClauseChangeTypes != null) {
					LogUtil.logMessage("Clause Change Types "+arlClauseChangeTypes);
					objChangeRequest1058Form.setClauseChangeTypes(arlClauseChangeTypes);
				}
				
				objChangeRequest1058VO.setClaChngReqSeq(0);
				
				//Request LSDB Clause Changes Details
				if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.NO)){
					arlReqSpecChngClauses=objChangeRequest1058BI.fetchLsdbClauseDetails(objChangeRequest1058VO);
					if (arlReqSpecChngClauses != null) {
						LogUtil.logMessage("Requested Specification Change "+arlReqSpecChngClauses);
						objChangeRequest1058Form.setReqSpecChngClauseList(arlReqSpecChngClauses);
					}
				}

				//Request Non LSDB Clause Changes Details
				if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.YES)){
					
					arlClauseList = objChangeRequest1058BI
							.selectClauseNonLsdb(objChangeRequest1058VO);
					objChangeRequest1058Form.setClaList(arlClauseList);
					LogUtil.logMessage("arlClauseList=" + arlClauseList.size());
				}
				
				//RepresentativeList Details
				//Added For CR-120
				UserVO objUserVO = new UserVO();
				objUserVO.setUserID(objLoginVO.getUserID());
				objUserVO.setOrderbyFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
				arlRepresentativeList = objChangeRequest1058BI.fetchRepresentativeDetails(objUserVO);
					
				if (arlRepresentativeList != null) {
					LogUtil.logMessage(arlRepresentativeList);
					objChangeRequest1058Form.setRepresentativeList(arlRepresentativeList);
				}
				LogUtil.logMessage("ArrayList Value in ChangeRequest1058Action:"
						+ objChangeRequest1058Form.getRepresentativeList().size());
				
				//Added for CR-127 starts here 
				//Model Clause Changes Details
				for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
					objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
				}
				arlMdlClaChangesList = objChangeRequest1058BI.fetch1058MdlClaChanges(objChangeRequest1058VO);
				if (arlMdlClaChangesList != null) {
					objChangeRequest1058Form.setMdlClaChangesList(arlMdlClaChangesList);
				}
				LogUtil.logMessage("model clause change list" +arlMdlClaChangesList.size());
				for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
					objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
				}
				//Added for CR-127 ends here
				//Added for CR-130 starts here
				//SubSection Details
				for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
					objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
				}
				arlImportSubSecList = objChangeRequest1058BI.fetchSubSectionList(objChangeRequest1058VO);
					if (arlImportSubSecList != null) {
						
						objChangeRequest1058Form.setImportSubSecList(arlImportSubSecList);
						objChangeRequest1058Form.setImportSubSecListSize(arlImportSubSecList.size());
					}
				LogUtil.logMessage("Import Sub Section List" +arlImportSubSecList.size());
				
				for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
					objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
				}
				//1058 SubSection Details
				objChangeRequest1058VO.setSubSecChngReqSeq(0);
				
				for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
					objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
				}
				arl1058SubSecList = objChangeRequest1058BI.fetch1058SubSectionList(objChangeRequest1058VO);
					if (arl1058SubSecList != null) {
						
						objChangeRequest1058Form.setSubSec1058List(arl1058SubSecList);
					}
				LogUtil.logMessage("Import Sub Section List" +arl1058SubSecList.size());
				
				for (int cnt = 0; cnt < arl1058SubSecList.size(); cnt++) {
					objChangeRequest1058VO = (ChangeRequest1058VO) arl1058SubSecList.get(cnt);
				}
				//Added for CR-130 ends here
			} catch (Exception objExp) {
				LogUtil
						.logMessage("Enters into Exception Block in ChangeRequest1058Action..");
				ErrorInfo objErrorInfo = new ErrorInfo();
				String strError = objExp.getMessage();
				objErrorInfo.setMessage(strError);
				LogUtil.logMessage("Error Message:" + strError);
				LogUtil.logError(objExp, objErrorInfo);
				strForwardKey = ApplicationConstants.FAILURE;
			}

			return objActionMapping.findForward(strForwardKey);
		}
		
		//----@cr110---@108447---end
		
		//Added by @vb106565 for CR-110 Starts here
		/*******************************************************************************************
		 *  * * *		This Method is used to Systems Engineer Section Details from DB
		 *  
		 * @author  	Satyam Computer Services Ltd  
		 * @version 	1.0  
		 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
		 * @return     	ActionForward  		   
		 * @throws     	EMDException
		 
		 ******************************************************************************************/
		
		
		public ActionForward sysEngrSecDetails(ActionMapping objActionMapping,
				   ActionForm objActionForm, 
				   HttpServletRequest objHttpServletRequest,
				   HttpServletResponse objHttpServletResponse) 
				   throws EMDException {
			LogUtil.logMessage("Enters into ChangeRequest1058Action:sysEngrSecDetails");
			String strForwardKey = ApplicationConstants.CREATE;
			int intsysEngDetails = 0;
			ArrayList arlRequest1058List = new ArrayList();
			ArrayList arlCategories =new ArrayList();
			ArrayList arlRequestTypes =new ArrayList();
			ArrayList arlClauseChangeTypes  =new ArrayList();
			ArrayList arlReqSpecChngClauses=new ArrayList();
			ArrayList arlRepresentativeList = new ArrayList();
			ArrayList arlMdlClaChangesList = new ArrayList();//Added for CR-127
			ArrayList arlImportSubSecList   = new ArrayList();//Added for CR-130
			ArrayList arl1058SubSecList   = new ArrayList();//Added for CR-130
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			try{
				LoginVO objLoginVO = (LoginVO) objSession
				.getAttribute(ApplicationConstants.USER_IN_SESSION);
				
				LogUtil.logMessage(objLoginVO);
				
				ChangeRequest1058VO objChangeRequest1058VO = new ChangeRequest1058VO();
				ChangeRequest1058Form objChangeRequest1058Form = (ChangeRequest1058Form) objActionForm;
				ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
				
				objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());  				
				LogUtil.logMessage("Section Status Seq :"+objChangeRequest1058Form.getSectionStatusSeq());
				objChangeRequest1058VO.setSectionStatus(objChangeRequest1058Form.getSectionStatusSeq());
				LogUtil.logMessage("System Eng Comment:"+objChangeRequest1058Form.getSystemEngComment());
				objChangeRequest1058VO.setSystemEngComment(objChangeRequest1058Form.getSystemEngComment());
				LogUtil.logMessage("Part No Added:"+objChangeRequest1058Form.getPartNoAdded());
				objChangeRequest1058VO.setPartNoAdded(objChangeRequest1058Form.getPartNoAdded());
				LogUtil.logMessage("Part No Deleted:"+objChangeRequest1058Form.getPartNoDeleted());
				objChangeRequest1058VO.setPartNoDeleted(objChangeRequest1058Form.getPartNoDeleted());
				LogUtil.logMessage("Change Affects Weight:"+objChangeRequest1058Form.getChangeAffectsWeight());
				objChangeRequest1058VO.setChangeAffectsWeight(objChangeRequest1058Form.getChangeAffectsWeight());
				LogUtil.logMessage("Change Affects Clear:"+objChangeRequest1058Form.getChangeAffectsClear());
				objChangeRequest1058VO.setChangeAffectsClear(objChangeRequest1058Form.getChangeAffectsClear());
				LogUtil.logMessage("Design Hrs:"+objChangeRequest1058Form.getDesignHrs());
				objChangeRequest1058VO.setDesignHrs(objChangeRequest1058Form.getDesignHrs());
				LogUtil.logMessage("DraftingHrs:"+objChangeRequest1058Form.getDraftingHrs());
				objChangeRequest1058VO.setDraftingHrs(objChangeRequest1058Form.getDraftingHrs());
				LogUtil.logMessage("Drawing Due Date:"+objChangeRequest1058Form.getDrawingDueDate());
				objChangeRequest1058VO.setDrawingDueDate(objChangeRequest1058Form.getDrawingDueDate());
				LogUtil.logMessage("Completion Date:"+objChangeRequest1058Form.getCompletionDate());
				objChangeRequest1058VO.setCompletionDate(objChangeRequest1058Form.getCompletionDate());
				LogUtil.logMessage("WorkOrderUSD:"+objChangeRequest1058Form.getWorkOrderUSD());
				if(objChangeRequest1058Form.getWorkOrderUSD().equalsIgnoreCase("") || objChangeRequest1058Form.getWorkOrderUSD() == null)
				{
					objChangeRequest1058VO.setWorkOrderUSD(new BigDecimal("0.00"));	
				}else{
					objChangeRequest1058VO.setWorkOrderUSD(new BigDecimal(objChangeRequest1058Form.getWorkOrderUSD()));
				}
				LogUtil.logMessage("StationAffected:"+objChangeRequest1058Form.getStationAffected());
				objChangeRequest1058VO.setStationAffected(objChangeRequest1058Form.getStationAffected());
				objChangeRequest1058VO.setUserID(objLoginVO.getUserID());
				
				intsysEngDetails  = objChangeRequest1058BI.sysEngrSecDetails(objChangeRequest1058VO);
				
				if(intsysEngDetails == 0){
	  				 objChangeRequest1058Form.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);               	
	            }
				
				arlRequest1058List = objChangeRequest1058BI.fetch1058Details(objChangeRequest1058VO);
				
				if (arlRequest1058List != null) {
					LogUtil.logMessage(arlRequest1058List);
					objChangeRequest1058Form.setRequestDetailsList(arlRequest1058List);
					
				}
				LogUtil.logMessage("ArrayList Value in ChangeRequest1058Action:"
						+ objChangeRequest1058Form.getRequestDetailsList().size());
				
				//Request 1058 Categories
				arlCategories = objChangeRequest1058BI.fetch1058RequestCategories(objLoginVO);

				if (arlCategories != null) {
					LogUtil.logMessage("Request Categories "+arlCategories);
					objChangeRequest1058Form.setCategories(arlCategories);
				}

				//Request 1058 Request Types
				arlRequestTypes = objChangeRequest1058BI.fetch1058RequestTypes(objLoginVO);
				
				if (arlRequestTypes != null) {
					LogUtil.logMessage("Request Types "+arlRequestTypes);
					objChangeRequest1058Form.setReqTypes(arlRequestTypes);
				}
				
				//Request LSDB Clause Change Types			
				arlClauseChangeTypes = objChangeRequest1058BI.fetch1058ClauseChangeTypes(objLoginVO);
				
				if (arlClauseChangeTypes != null) {
					LogUtil.logMessage("Clause Change Types "+arlClauseChangeTypes);
					objChangeRequest1058Form.setClauseChangeTypes(arlClauseChangeTypes);
				}
				
				objChangeRequest1058VO.setClaChngReqSeq(0);
				
				//Request LSDB Clause Changes Details
				if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.NO)){
					arlReqSpecChngClauses=objChangeRequest1058BI.fetchLsdbClauseDetails(objChangeRequest1058VO);
					if (arlReqSpecChngClauses != null) {
						LogUtil.logMessage("Requested Specification Change "+arlReqSpecChngClauses);
						objChangeRequest1058Form.setReqSpecChngClauseList(arlReqSpecChngClauses);
					}
				}

				//Request Non LSDB Clause Changes Details
				if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.YES)){
					
					ArrayList arlClauseList = objChangeRequest1058BI
							.selectClauseNonLsdb(objChangeRequest1058VO);
					objChangeRequest1058Form.setClaList(arlClauseList);
					LogUtil.logMessage("arlClauseList=" + arlClauseList.size());
				}
				//Added For CR-120
				UserVO objUserVO = new UserVO();
				objUserVO.setUserID(objLoginVO.getUserID());
				objUserVO.setOrderbyFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
				arlRepresentativeList = objChangeRequest1058BI.fetchRepresentativeDetails(objUserVO);
				
				if (arlRepresentativeList != null) {
					LogUtil.logMessage(arlRepresentativeList);
					objChangeRequest1058Form.setRepresentativeList(arlRepresentativeList);
				}
				LogUtil.logMessage("ArrayList Value in ChangeRequest1058Action:"
						+ objChangeRequest1058Form.getRepresentativeList().size());
				
				  if(ApplicationConstants.ADMN.equals(objLoginVO.getRoleID())
							|| ApplicationConstants.MSE.equals(objLoginVO.getRoleID())
							|| ApplicationConstants.SE.equals(objLoginVO.getRoleID()))
						objChangeRequest1058Form.setRoleID(ApplicationConstants.VALID_USER);
					 else
						 objChangeRequest1058Form.setRoleID(objLoginVO.getRoleID()); 
					LogUtil.logMessage("RoleId Request:"
							+ objChangeRequest1058Form.getRoleID());
					
					//Added for CR-127 starts here
					//Model Clause Changes Details
					for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
					}
					arlMdlClaChangesList = objChangeRequest1058BI.fetch1058MdlClaChanges(objChangeRequest1058VO);
					if (arlMdlClaChangesList != null) {
						objChangeRequest1058Form.setMdlClaChangesList(arlMdlClaChangesList);
					}
					LogUtil.logMessage("model clause change list" +arlMdlClaChangesList.size());
					for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
					}
					//Added for CR-127 ends here
					//Added for CR-130 starts here
					//SubSection Details
					for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
					}
					arlImportSubSecList = objChangeRequest1058BI.fetchSubSectionList(objChangeRequest1058VO);
						if (arlImportSubSecList != null) {
							
							objChangeRequest1058Form.setImportSubSecList(arlImportSubSecList);
							objChangeRequest1058Form.setImportSubSecListSize(arlImportSubSecList.size());
						}
					LogUtil.logMessage("Import Sub Section List" +arlImportSubSecList.size());
					
					for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
					}
					
					//1058 SubSection Details
					objChangeRequest1058VO.setSubSecChngReqSeq(0);
					
					for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
					}
					arl1058SubSecList = objChangeRequest1058BI.fetch1058SubSectionList(objChangeRequest1058VO);
						if (arl1058SubSecList != null) {
							
							objChangeRequest1058Form.setSubSec1058List(arl1058SubSecList);
						}
					LogUtil.logMessage("Import Sub Section List" +arl1058SubSecList.size());
					
					for (int cnt = 0; cnt < arl1058SubSecList.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arl1058SubSecList.get(cnt);
					}
					//Added for CR-130 ends here
			 
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
			return objActionMapping.findForward(strForwardKey);
		}

		/*******************************************************************************************
		 *  * * *		This Method is used to Operations Section Details from DB
		 *  
		 * @author  	Satyam Computer Services Ltd  
		 * @version 	1.0  
		 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
		 * @return     	ActionForward  		   
		 * @throws     	EMDException
		 
		 ******************************************************************************************/
		
		public ActionForward operationsSecDetails(ActionMapping objActionMapping,
				   ActionForm objActionForm, 
				   HttpServletRequest objHttpServletRequest,
				   HttpServletResponse objHttpServletResponse) 
				   throws EMDException {
			LogUtil.logMessage("Enters into ChangeRequest1058Action:operationsSecDetails");
			String strForwardKey = ApplicationConstants.CREATE;
			int intoprDetails = 0;
			ArrayList arlRequest1058List = new ArrayList();
			ArrayList arlCategories =new ArrayList();
			ArrayList arlRequestTypes =new ArrayList();
			ArrayList arlClauseChangeTypes  =new ArrayList();
			ArrayList arlReqSpecChngClauses=new ArrayList();
			ArrayList arlRepresentativeList = new ArrayList();
			ArrayList arlMdlClaChangesList = new ArrayList();//Added for CR-127
			ArrayList arlImportSubSecList   = new ArrayList();//Added for CR-130
			ArrayList arl1058SubSecList   = new ArrayList();//Added for CR-130
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			ChangeRequest1058Form objChangeRequest1058Form = (ChangeRequest1058Form) objActionForm;
			ChangeRequest1058VO objChangeRequest1058VO = new ChangeRequest1058VO();
			
			LogUtil.logMessage("The Seq	No in VO is:"+objChangeRequest1058VO.getSeqNo1058());
			objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
			
					try{
				
				LoginVO objLoginVO = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
				LogUtil.logMessage(objLoginVO);
				objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
						LogUtil.logMessage("UserID :"+objLoginVO.getUserID());
						objChangeRequest1058VO.setUserID(objLoginVO.getUserID());
						objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
					
					LogUtil.logMessage("Section Status Seq :"+objChangeRequest1058Form.getSectionStatusSeq());
					objChangeRequest1058VO.setSectionStatus(objChangeRequest1058Form.getSectionStatusSeq());
					
					LogUtil.logMessage("Operation Comments :"+objChangeRequest1058Form.getOperationComments());
					objChangeRequest1058VO.setOperationComments(objChangeRequest1058Form.getOperationComments());
					LogUtil.logMessage("Dispos Excess Material:"+objChangeRequest1058Form.getDisposExcessMaterial());
					objChangeRequest1058VO.setDisposExcessMaterial(objChangeRequest1058Form.getDisposExcessMaterial());
					LogUtil.logMessage("Supplier Affected Cost:"+objChangeRequest1058Form.getSupplierAffectedCost());
					objChangeRequest1058VO.setSupplierAffectedCost(objChangeRequest1058Form.getSupplierAffectedCost());
					LogUtil.logMessage("Labor Impact :"+objChangeRequest1058Form.getLaborImpact());
					objChangeRequest1058VO.setLaborImpact(objChangeRequest1058Form.getLaborImpact());
					LogUtil.logMessage("Rec Effectivity Del:"+objChangeRequest1058Form.getRecEffectivityDel());
					objChangeRequest1058VO.setRecEffectivityDel(objChangeRequest1058Form.getRecEffectivityDel());
					LogUtil.logMessage("Tooling Cost USD :"+objChangeRequest1058Form.getToolingCostUSD());
					if(objChangeRequest1058Form.getToolingCostUSD().equalsIgnoreCase("") || objChangeRequest1058Form.getToolingCostUSD() == null)
					{
						objChangeRequest1058VO.setToolingCostUSD(new BigDecimal("0.00"));
					}else{
					objChangeRequest1058VO.setToolingCostUSD(new BigDecimal(objChangeRequest1058Form.getToolingCostUSD()));
					}
					LogUtil.logMessage("Scrap Cost USD :"+objChangeRequest1058Form.getScrapCostUSD());
					if(objChangeRequest1058Form.getScrapCostUSD().equalsIgnoreCase("") || objChangeRequest1058Form.getScrapCostUSD() == null)
					{
						objChangeRequest1058VO.setScrapCostUSD(new BigDecimal("0.00"));
					}else{
					objChangeRequest1058VO.setScrapCostUSD(new BigDecimal(objChangeRequest1058Form.getScrapCostUSD()));
					}
					LogUtil.logMessage("Rework Cost :"+objChangeRequest1058Form.getReworkCost());
					if(objChangeRequest1058Form.getReworkCost().equalsIgnoreCase("") || objChangeRequest1058Form.getReworkCost() == null)
					{
						objChangeRequest1058VO.setReworkCost(new BigDecimal("0.00"));
					}else{
					objChangeRequest1058VO.setReworkCost(new BigDecimal(objChangeRequest1058Form.getReworkCost()));
					}
					ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();	
				
				intoprDetails   = objChangeRequest1058BI.operationsSecDetails(objChangeRequest1058VO);
				
				if(intoprDetails == 0){
	  				 objChangeRequest1058Form.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);               	
	            }
				
				objChangeRequest1058VO.setUserID(objLoginVO.getUserID());
				arlRequest1058List = objChangeRequest1058BI.fetch1058Details(objChangeRequest1058VO);
				
				if (arlRequest1058List != null) {
					LogUtil.logMessage(arlRequest1058List);
					objChangeRequest1058Form.setRequestDetailsList(arlRequest1058List);
					
				}
				LogUtil.logMessage("ArrayList Value in ChangeRequest1058Action:"
						+ objChangeRequest1058Form.getRequestDetailsList().size());
				
				//Request 1058 Categories
				arlCategories = objChangeRequest1058BI.fetch1058RequestCategories(objLoginVO);

				if (arlCategories != null) {
					LogUtil.logMessage("Request Categories "+arlCategories);
					objChangeRequest1058Form.setCategories(arlCategories);
				}

				//Request 1058 Request Types
				arlRequestTypes = objChangeRequest1058BI.fetch1058RequestTypes(objLoginVO);
				
				if (arlRequestTypes != null) {
					LogUtil.logMessage("Request Types "+arlRequestTypes);
					objChangeRequest1058Form.setReqTypes(arlRequestTypes);
				}
				
				//Request LSDB Clause Change Types			
				arlClauseChangeTypes = objChangeRequest1058BI.fetch1058ClauseChangeTypes(objLoginVO);
				
				if (arlClauseChangeTypes != null) {
					LogUtil.logMessage("Clause Change Types "+arlClauseChangeTypes);
					objChangeRequest1058Form.setClauseChangeTypes(arlClauseChangeTypes);
				}
				
				objChangeRequest1058VO.setClaChngReqSeq(0);
				
				//Request LSDB Clause Changes Details
				if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.NO)){
					arlReqSpecChngClauses=objChangeRequest1058BI.fetchLsdbClauseDetails(objChangeRequest1058VO);
					if (arlReqSpecChngClauses != null) {
						LogUtil.logMessage("Requested Specification Change "+arlReqSpecChngClauses);
						objChangeRequest1058Form.setReqSpecChngClauseList(arlReqSpecChngClauses);
					}
				}

				//Request Non LSDB Clause Changes Details
				if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.YES)){
					
					ArrayList arlClauseList = objChangeRequest1058BI
							.selectClauseNonLsdb(objChangeRequest1058VO);
					objChangeRequest1058Form.setClaList(arlClauseList);
					LogUtil.logMessage("arlClauseList=" + arlClauseList.size());
				}
				//Added For CR-120
				UserVO objUserVO = new UserVO();
				objUserVO.setUserID(objLoginVO.getUserID());
				objUserVO.setOrderbyFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
				arlRepresentativeList = objChangeRequest1058BI.fetchRepresentativeDetails(objUserVO);
				
				if (arlRepresentativeList != null) {
					LogUtil.logMessage(arlRepresentativeList);
					objChangeRequest1058Form.setRepresentativeList(arlRepresentativeList);
				}
				LogUtil.logMessage("ArrayList Value in ChangeRequest1058Action:"
						+ objChangeRequest1058Form.getRepresentativeList().size());
				
				if(ApplicationConstants.ADMN.equals(objLoginVO.getRoleID())
						|| ApplicationConstants.MSE.equals(objLoginVO.getRoleID())
						|| ApplicationConstants.SE.equals(objLoginVO.getRoleID()))
					objChangeRequest1058Form.setRoleID(ApplicationConstants.VALID_USER);
				 else
					 objChangeRequest1058Form.setRoleID(objLoginVO.getRoleID()); 
				LogUtil.logMessage("RoleId Request:"
						+ objChangeRequest1058Form.getRoleID());

				//Added for CR-127 starts here
				//Model Clause Changes Details
				for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
					objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
				}
				arlMdlClaChangesList = objChangeRequest1058BI.fetch1058MdlClaChanges(objChangeRequest1058VO);
				if (arlMdlClaChangesList != null) {
					objChangeRequest1058Form.setMdlClaChangesList(arlMdlClaChangesList);
				}
				LogUtil.logMessage("model clause change list" +arlMdlClaChangesList.size());
				for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
					objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
				}
				//Added for CR-127 ends here
				//Added for CR-130 starts here
				//SubSection Details
				for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
					objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
				}
				arlImportSubSecList = objChangeRequest1058BI.fetchSubSectionList(objChangeRequest1058VO);
					if (arlImportSubSecList != null) {
						
						objChangeRequest1058Form.setImportSubSecList(arlImportSubSecList);
						objChangeRequest1058Form.setImportSubSecListSize(arlImportSubSecList.size());
					}
				LogUtil.logMessage("Import Sub Section List" +arlImportSubSecList.size());
				
				for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
					objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
				}
				
				//1058 SubSection Details
				objChangeRequest1058VO.setSubSecChngReqSeq(0);
				
				for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
					objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
				}
				arl1058SubSecList = objChangeRequest1058BI.fetch1058SubSectionList(objChangeRequest1058VO);
					if (arl1058SubSecList != null) {
						
						objChangeRequest1058Form.setSubSec1058List(arl1058SubSecList);
					}
				LogUtil.logMessage("Import Sub Section List" +arl1058SubSecList.size());
				
				for (int cnt = 0; cnt < arl1058SubSecList.size(); cnt++) {
					objChangeRequest1058VO = (ChangeRequest1058VO) arl1058SubSecList.get(cnt);
				}
				//Added for CR-130 ends here
			} catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception Block in Action..");
				ErrorInfo objErrorInfo = new ErrorInfo();
				String strError = objExp.getMessage();
				objErrorInfo.setMessage(strError);
				LogUtil.logMessage("Error Message:" + strError);
				LogUtil.logError(objExp, objErrorInfo);
				strForwardKey = ApplicationConstants.FAILURE;
			}
			return objActionMapping.findForward(strForwardKey);
		}

		 

		/*******************************************************************************************
		 *  * * *		This Method is used to finance Section Details from DB
		 *  
		 * @author  	Satyam Computer Services Ltd  
		 * @version 	1.0  
		 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
		 * @return     	ActionForward  		   
		 * @throws     	EMDException
		 
		 ******************************************************************************************/
		public ActionForward financeSecDetails(ActionMapping objActionMapping,
					   ActionForm objActionForm, 
					   HttpServletRequest objHttpServletRequest,
					   HttpServletResponse objHttpServletResponse) 
					   throws EMDException {
				LogUtil.logMessage("Enters into ChangeRequest1058Action:financeSecDetails");
				String strForwardKey = ApplicationConstants.CREATE;
				int intfinDetails = 0;
				ArrayList arlRequest1058List = new ArrayList();
				ArrayList arlCategories =new ArrayList();
				ArrayList arlRequestTypes =new ArrayList();
				ArrayList arlClauseChangeTypes  =new ArrayList();
				ArrayList arlReqSpecChngClauses=new ArrayList();
				ArrayList arlRepresentativeList = new ArrayList();
				ArrayList arlMdlClaChangesList = new ArrayList();//Added for CR-127
				ArrayList arlImportSubSecList   = new ArrayList();//Added for CR-130
				ArrayList arl1058SubSecList   = new ArrayList();//Added for CR-130
				
				HttpSession objSession = objHttpServletRequest.getSession(false);
				ChangeRequest1058Form objChangeRequest1058Form = (ChangeRequest1058Form) objActionForm;
				ChangeRequest1058VO objChangeRequest1058VO = new ChangeRequest1058VO();
				
				LogUtil.logMessage("The Seq	No in VO is:"+objChangeRequest1058VO.getSeqNo1058());
				objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
				
				try{
					
					LoginVO objLoginVO = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
					LogUtil.logMessage(objLoginVO);
					
					 objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
							LogUtil.logMessage("UserID :"+objLoginVO.getUserID());
							objChangeRequest1058VO.setUserID(objLoginVO.getUserID());
						
						objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
						
						LogUtil.logMessage("Section Status Seq :"+objChangeRequest1058Form.getSectionStatusSeq());
						objChangeRequest1058VO.setSectionStatus(objChangeRequest1058Form.getSectionStatusSeq());
						
					LogUtil.logMessage("Finance Comments:"+objChangeRequest1058Form.getFinanceComments());
					objChangeRequest1058VO.setFinanceComments(objChangeRequest1058Form.getFinanceComments());
					LogUtil.logMessage("Prod Cost Change :"+objChangeRequest1058Form.getProdCostChange());
					objChangeRequest1058VO.setProdCostChange(objChangeRequest1058Form.getProdCostChange());
					LogUtil.logMessage("Prod Cost Change Sup :"+objChangeRequest1058Form.getProdCostChangeSup());
					objChangeRequest1058VO.setProdCostChangeSup(objChangeRequest1058Form.getProdCostChangeSup());
					
					ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
					
					intfinDetails   = objChangeRequest1058BI.financeSecDetails(objChangeRequest1058VO);
					
					if(intfinDetails == 0){
		  				 objChangeRequest1058Form.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);               	
		            }
					
					objChangeRequest1058VO.setUserID(objLoginVO.getUserID());
					arlRequest1058List = objChangeRequest1058BI.fetch1058Details(objChangeRequest1058VO);
					
					if (arlRequest1058List != null) {
						LogUtil.logMessage(arlRequest1058List);
						objChangeRequest1058Form.setRequestDetailsList(arlRequest1058List);
						
					}
					LogUtil.logMessage("ArrayList Value in ChangeRequest1058Action:"
							+ objChangeRequest1058Form.getRequestDetailsList().size());
					
					//Request 1058 Categories
					arlCategories = objChangeRequest1058BI.fetch1058RequestCategories(objLoginVO);

					if (arlCategories != null) {
						LogUtil.logMessage("Request Categories "+arlCategories);
						objChangeRequest1058Form.setCategories(arlCategories);
					}

					//Request 1058 Request Types
					arlRequestTypes = objChangeRequest1058BI.fetch1058RequestTypes(objLoginVO);
					
					if (arlRequestTypes != null) {
						LogUtil.logMessage("Request Types "+arlRequestTypes);
						objChangeRequest1058Form.setReqTypes(arlRequestTypes);
					}
					
					//Request LSDB Clause Change Types			
					arlClauseChangeTypes = objChangeRequest1058BI.fetch1058ClauseChangeTypes(objLoginVO);
					
					if (arlClauseChangeTypes != null) {
						LogUtil.logMessage("Clause Change Types "+arlClauseChangeTypes);
						objChangeRequest1058Form.setClauseChangeTypes(arlClauseChangeTypes);
					}
					
					objChangeRequest1058VO.setClaChngReqSeq(0);
					
					//Request LSDB Clause Changes Details
					if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.NO)){
						arlReqSpecChngClauses=objChangeRequest1058BI.fetchLsdbClauseDetails(objChangeRequest1058VO);
						if (arlReqSpecChngClauses != null) {
							LogUtil.logMessage("Requested Specification Change "+arlReqSpecChngClauses);
							objChangeRequest1058Form.setReqSpecChngClauseList(arlReqSpecChngClauses);
						}
					}

					//Request Non LSDB Clause Changes Details
					if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.YES)){
						
						ArrayList arlClauseList = objChangeRequest1058BI
								.selectClauseNonLsdb(objChangeRequest1058VO);
						objChangeRequest1058Form.setClaList(arlClauseList);
						LogUtil.logMessage("arlClauseList=" + arlClauseList.size());
					}
					//Added For CR-120
					UserVO objUserVO = new UserVO();
					objUserVO.setUserID(objLoginVO.getUserID());
					objUserVO.setOrderbyFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
					arlRepresentativeList = objChangeRequest1058BI.fetchRepresentativeDetails(objUserVO);
					
					if (arlRepresentativeList != null) {
						LogUtil.logMessage(arlRepresentativeList);
						objChangeRequest1058Form.setRepresentativeList(arlRepresentativeList);
					}
					LogUtil.logMessage("ArrayList Value in ChangeRequest1058Action:"
							+ objChangeRequest1058Form.getRepresentativeList().size());
					
					if(ApplicationConstants.ADMN.equals(objLoginVO.getRoleID())
							|| ApplicationConstants.MSE.equals(objLoginVO.getRoleID())
							|| ApplicationConstants.SE.equals(objLoginVO.getRoleID()))
						objChangeRequest1058Form.setRoleID(ApplicationConstants.VALID_USER);
					 else
						 objChangeRequest1058Form.setRoleID(objLoginVO.getRoleID()); 
					LogUtil.logMessage("RoleId Request:"
							+ objChangeRequest1058Form.getRoleID());

					//Added for CR-127 starts here
					//Model Clause Changes Details
					for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
					}
					arlMdlClaChangesList = objChangeRequest1058BI.fetch1058MdlClaChanges(objChangeRequest1058VO);
					if (arlMdlClaChangesList != null) {
						objChangeRequest1058Form.setMdlClaChangesList(arlMdlClaChangesList);
					}
					LogUtil.logMessage("model clause change list" +arlMdlClaChangesList.size());
					for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
					}
					//Added for CR-127 ends here
					//Added for CR-130 starts here
					//SubSection Details
					for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
					}
					arlImportSubSecList = objChangeRequest1058BI.fetchSubSectionList(objChangeRequest1058VO);
						if (arlImportSubSecList != null) {
							
							objChangeRequest1058Form.setImportSubSecList(arlImportSubSecList);
							objChangeRequest1058Form.setImportSubSecListSize(arlImportSubSecList.size());
						}
					LogUtil.logMessage("Import Sub Section List" +arlImportSubSecList.size());
					
					for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
					}
					
					//1058 SubSection Details
					objChangeRequest1058VO.setSubSecChngReqSeq(0);
					
					for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
					}
					arl1058SubSecList = objChangeRequest1058BI.fetch1058SubSectionList(objChangeRequest1058VO);
						if (arl1058SubSecList != null) {
							
							objChangeRequest1058Form.setSubSec1058List(arl1058SubSecList);
						}
					LogUtil.logMessage("Import Sub Section List" +arl1058SubSecList.size());
					
					for (int cnt = 0; cnt < arl1058SubSecList.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arl1058SubSecList.get(cnt);
					}
					//Added for CR-130 ends here
				} catch (Exception objExp) {
					LogUtil.logMessage("Enters into Exception Block in Action..");
					ErrorInfo objErrorInfo = new ErrorInfo();
					String strError = objExp.getMessage();
					objErrorInfo.setMessage(strError);
					LogUtil.logMessage("Error Message:" + strError);
					LogUtil.logError(objExp, objErrorInfo);
					strForwardKey = ApplicationConstants.FAILURE;
				}
				return objActionMapping.findForward(strForwardKey);
			}
			
		
		/*******************************************************************************************
		 *  * * *		This Method is used to Program Manager Section Details from DB
		 *  
		 * @author  	Satyam Computer Services Ltd  
		 * @version 	1.0  
		 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
		 * @return     	ActionForward  		   
		 * @throws     	EMDException
		 
		 ******************************************************************************************/
		
			
			 
		public ActionForward programMgrSecDetails(ActionMapping objActionMapping,
					   ActionForm objActionForm, 
					   HttpServletRequest objHttpServletRequest,
					   HttpServletResponse objHttpServletResponse) 
					   throws EMDException {
				LogUtil.logMessage("Enters into ChangeRequest1058Action:programMgrSecDetails");
				ArrayList arlRequest1058List = new ArrayList();
				ArrayList arlCategories =new ArrayList();
				ArrayList arlRequestTypes =new ArrayList();
				ArrayList arlClauseChangeTypes  =new ArrayList();
				ArrayList arlReqSpecChngClauses=new ArrayList();
				ArrayList arlRepresentativeList = new ArrayList();
				ArrayList arlMdlClaChangesList = new ArrayList();//Added for CR-127
				ArrayList arlImportSubSecList   = new ArrayList();//Added for CR-130
				ArrayList arl1058SubSecList   = new ArrayList();//Added for CR-130
				
				String strForwardKey = ApplicationConstants.CREATE;
				int intpgmMgrDetails = 0;
				HttpSession objSession = objHttpServletRequest.getSession(false);
				ChangeRequest1058Form objChangeRequest1058Form = (ChangeRequest1058Form) objActionForm;
				ChangeRequest1058VO objChangeRequest1058VO = new ChangeRequest1058VO();
				
				LogUtil.logMessage("The Seq	No in VO is:"+objChangeRequest1058VO.getSeqNo1058());
				objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
				
						try{
					
					LoginVO objLoginVO = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
					LogUtil.logMessage(objLoginVO);
					
					 objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
							LogUtil.logMessage("UserID :"+objLoginVO.getUserID());
							objChangeRequest1058VO.setUserID(objLoginVO.getUserID());
							
					objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
					LogUtil.logMessage("Section Status Seq :"+objChangeRequest1058Form.getSectionStatusSeq());
					objChangeRequest1058VO.setSectionStatus(objChangeRequest1058Form.getSectionStatusSeq());
					LogUtil.logMessage("Prog Manager Comments:"+objChangeRequest1058Form.getProgManagerComments());
					objChangeRequest1058VO.setProgManagerComments(objChangeRequest1058Form.getProgManagerComments());
					
					
					ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
					
					
					intpgmMgrDetails   = objChangeRequest1058BI.programMgrSecDetails(objChangeRequest1058VO);
					
					if(intpgmMgrDetails == 0){
		  				 objChangeRequest1058Form.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);               	
		            }
					
					objChangeRequest1058VO.setUserID(objLoginVO.getUserID());
					arlRequest1058List = objChangeRequest1058BI.fetch1058Details(objChangeRequest1058VO);
					
					
					if (arlRequest1058List != null) {
						LogUtil.logMessage(arlRequest1058List);
						objChangeRequest1058Form.setRequestDetailsList(arlRequest1058List);
						
					}
					LogUtil.logMessage("ArrayList Value in ChangeRequest1058Action:"
							+ objChangeRequest1058Form.getRequestDetailsList().size());
					
					//Request 1058 Categories
					arlCategories = objChangeRequest1058BI.fetch1058RequestCategories(objLoginVO);

					if (arlCategories != null) {
						LogUtil.logMessage("Request Categories "+arlCategories);
						objChangeRequest1058Form.setCategories(arlCategories);
					}

					//Request 1058 Request Types
					arlRequestTypes = objChangeRequest1058BI.fetch1058RequestTypes(objLoginVO);
					
					if (arlRequestTypes != null) {
						LogUtil.logMessage("Request Types "+arlRequestTypes);
						objChangeRequest1058Form.setReqTypes(arlRequestTypes);
					}
					
					//Request LSDB Clause Change Types			
					arlClauseChangeTypes = objChangeRequest1058BI.fetch1058ClauseChangeTypes(objLoginVO);
					
					if (arlClauseChangeTypes != null) {
						LogUtil.logMessage("Clause Change Types "+arlClauseChangeTypes);
						objChangeRequest1058Form.setClauseChangeTypes(arlClauseChangeTypes);
					}
					
					objChangeRequest1058VO.setClaChngReqSeq(0);
					
					//Request LSDB Clause Changes Details
					if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.NO)){
						arlReqSpecChngClauses=objChangeRequest1058BI.fetchLsdbClauseDetails(objChangeRequest1058VO);
						if (arlReqSpecChngClauses != null) {
							LogUtil.logMessage("Requested Specification Change "+arlReqSpecChngClauses);
							objChangeRequest1058Form.setReqSpecChngClauseList(arlReqSpecChngClauses);
						}
					}

					//Request Non LSDB Clause Changes Details
					if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.YES)){
						
						ArrayList arlClauseList = objChangeRequest1058BI
								.selectClauseNonLsdb(objChangeRequest1058VO);
						objChangeRequest1058Form.setClaList(arlClauseList);
						LogUtil.logMessage("arlClauseList=" + arlClauseList.size());
					}
					//Added For CR-120
					UserVO objUserVO = new UserVO();
					objUserVO.setUserID(objLoginVO.getUserID());
					objUserVO.setOrderbyFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
					arlRepresentativeList = objChangeRequest1058BI.fetchRepresentativeDetails(objUserVO);
					
					if (arlRepresentativeList != null) {
						LogUtil.logMessage(arlRepresentativeList);
						objChangeRequest1058Form.setRepresentativeList(arlRepresentativeList);
					}
					LogUtil.logMessage("ArrayList Value in ChangeRequest1058Action:"
							+ objChangeRequest1058Form.getRepresentativeList().size());
					
					if(ApplicationConstants.ADMN.equals(objLoginVO.getRoleID())
							|| ApplicationConstants.MSE.equals(objLoginVO.getRoleID())
							|| ApplicationConstants.SE.equals(objLoginVO.getRoleID()))
						objChangeRequest1058Form.setRoleID(ApplicationConstants.VALID_USER);
					 else
						 objChangeRequest1058Form.setRoleID(objLoginVO.getRoleID()); 
					LogUtil.logMessage("RoleId Request:"
							+ objChangeRequest1058Form.getRoleID());

					//Added for CR-127 starts here
					//Model Clause Changes Details
					for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
					}
					arlMdlClaChangesList = objChangeRequest1058BI.fetch1058MdlClaChanges(objChangeRequest1058VO);
					if (arlMdlClaChangesList != null) {
						objChangeRequest1058Form.setMdlClaChangesList(arlMdlClaChangesList);
					}
					LogUtil.logMessage("model clause change list" +arlMdlClaChangesList.size());
					for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
					}
			//Added for CR-127 ends here
					//Added for CR-130 starts here
					//SubSection Details
					for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
					}
					arlImportSubSecList = objChangeRequest1058BI.fetchSubSectionList(objChangeRequest1058VO);
						if (arlImportSubSecList != null) {
							
							objChangeRequest1058Form.setImportSubSecList(arlImportSubSecList);
							objChangeRequest1058Form.setImportSubSecListSize(arlImportSubSecList.size());
						}
					LogUtil.logMessage("Import Sub Section List" +arlImportSubSecList.size());
					
					for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
					}
					
					//1058 SubSection Details
					objChangeRequest1058VO.setSubSecChngReqSeq(0);
					
					for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
					}
					arl1058SubSecList = objChangeRequest1058BI.fetch1058SubSectionList(objChangeRequest1058VO);
						if (arl1058SubSecList != null) {
							
							objChangeRequest1058Form.setSubSec1058List(arl1058SubSecList);
						}
					LogUtil.logMessage("Import Sub Section List" +arl1058SubSecList.size());
					
					for (int cnt = 0; cnt < arl1058SubSecList.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arl1058SubSecList.get(cnt);
					}
					//Added for CR-130 ends here
				} catch (Exception objExp) {
					LogUtil.logMessage("Enters into Exception Block in Action..");
					ErrorInfo objErrorInfo = new ErrorInfo();
					String strError = objExp.getMessage();
					objErrorInfo.setMessage(strError);
					LogUtil.logMessage("Error Message:" + strError);
					LogUtil.logError(objExp, objErrorInfo);
					strForwardKey = ApplicationConstants.FAILURE;
				}
				return objActionMapping.findForward(strForwardKey);
			}
		
		
		
		/*******************************************************************************************
		 *  * * *		This Method is used to proposal Manager Section Details from DB
		 *  
		 * @author  	Satyam Computer Services Ltd  
		 * @version 	1.0  
		 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
		 * @return     	ActionForward  		   
		 * @throws     	EMDException
		 
		 ******************************************************************************************/
		public ActionForward proposalMgrSecDetails(ActionMapping objActionMapping,
					   ActionForm objActionForm, 
					   HttpServletRequest objHttpServletRequest,
					   HttpServletResponse objHttpServletResponse) 
					   throws EMDException {
				LogUtil.logMessage("Enters into ChangeRequest1058Action:proposalMgrSecDetails");
				ArrayList arlRequest1058List = new ArrayList();
				ArrayList arlCategories =new ArrayList();
				ArrayList arlRequestTypes =new ArrayList();
				ArrayList arlClauseChangeTypes  =new ArrayList();
				ArrayList arlReqSpecChngClauses=new ArrayList();
				ArrayList arlRepresentativeList = new ArrayList();
				ArrayList arlMdlClaChangesList = new ArrayList();//Added for CR-127
				ArrayList arlImportSubSecList   = new ArrayList();//Added for CR-130
				ArrayList arl1058SubSecList   = new ArrayList();//Added for CR-130
				
				String strForwardKey = ApplicationConstants.CREATE;
				int intpropMgrDetails = 0;
				HttpSession objSession = objHttpServletRequest.getSession(false);
				ChangeRequest1058Form objChangeRequest1058Form = (ChangeRequest1058Form) objActionForm;
				ChangeRequest1058VO objChangeRequest1058VO = new ChangeRequest1058VO();
								
				try{
					
					LoginVO objLoginVO = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);

				    objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
					objChangeRequest1058VO.setUserID(objLoginVO.getUserID());
					objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
					objChangeRequest1058VO.setSectionStatus(objChangeRequest1058Form.getSectionStatusSeq());
					objChangeRequest1058VO.setPropManagerComments(objChangeRequest1058Form.getPropManagerComments());
					if(objChangeRequest1058Form.getSellpriceCustomer().equalsIgnoreCase("") || objChangeRequest1058Form.getSellpriceCustomer() == null)
					{
						objChangeRequest1058VO.setSellpriceCustomer(new BigDecimal("0.00"));
					}else{
						objChangeRequest1058VO.setSellpriceCustomer(new BigDecimal(objChangeRequest1058Form.getSellpriceCustomer()));
					}
					if(objChangeRequest1058Form.getMarkUp().equalsIgnoreCase("") || objChangeRequest1058Form.getMarkUp() == null)
					{
						objChangeRequest1058VO.setMarkUp(new BigDecimal("0.00"));
					}else{
						objChangeRequest1058VO.setMarkUp(new BigDecimal(objChangeRequest1058Form.getMarkUp()));
					}
					objChangeRequest1058VO.setCustomerApprovalReq(objChangeRequest1058Form.getCustomerApprovalReq());
					objChangeRequest1058VO.setCustomerDecision(objChangeRequest1058Form.getCustomerDecision());
					objChangeRequest1058VO.setCustomerDecisionDate(objChangeRequest1058Form.getCustomerDecisionDate());
					objChangeRequest1058VO.setCustomerApprovalDet(objChangeRequest1058Form.getCustApprovalDet());
					if(objChangeRequest1058Form.getActualSellPrice().equalsIgnoreCase("") || objChangeRequest1058Form.getActualSellPrice() == null)
					{
						objChangeRequest1058VO.setActualSellPrice(new BigDecimal("0.00"));
					}else{
						objChangeRequest1058VO.setActualSellPrice(new BigDecimal(objChangeRequest1058Form.getActualSellPrice()));
					}
					ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
					intpropMgrDetails   = objChangeRequest1058BI.proposalMgrSecDetails(objChangeRequest1058VO);	
					
					if(intpropMgrDetails == 0){
		  				 objChangeRequest1058Form.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);               	
		            }				
					
					objChangeRequest1058VO.setUserID(objLoginVO.getUserID());
					arlRequest1058List = objChangeRequest1058BI.fetch1058Details(objChangeRequest1058VO);

					if (arlRequest1058List != null) {
						LogUtil.logMessage(arlRequest1058List);
						objChangeRequest1058Form.setRequestDetailsList(arlRequest1058List);
						
					}
					LogUtil.logMessage("ArrayList Value in ChangeRequest1058Action:"
							+ objChangeRequest1058Form.getRequestDetailsList().size());
					
					//Request 1058 Categories
					arlCategories = objChangeRequest1058BI.fetch1058RequestCategories(objLoginVO);

					if (arlCategories != null) {
						LogUtil.logMessage("Request Categories "+arlCategories);
						objChangeRequest1058Form.setCategories(arlCategories);
					}

					//Request 1058 Request Types
					arlRequestTypes = objChangeRequest1058BI.fetch1058RequestTypes(objLoginVO);
					
					if (arlRequestTypes != null) {
						LogUtil.logMessage("Request Types "+arlRequestTypes);
						objChangeRequest1058Form.setReqTypes(arlRequestTypes);
					}
					
					//Request LSDB Clause Change Types			
					arlClauseChangeTypes = objChangeRequest1058BI.fetch1058ClauseChangeTypes(objLoginVO);
					
					if (arlClauseChangeTypes != null) {
						LogUtil.logMessage("Clause Change Types "+arlClauseChangeTypes);
						objChangeRequest1058Form.setClauseChangeTypes(arlClauseChangeTypes);
					}
					
					objChangeRequest1058VO.setClaChngReqSeq(0);
					
					//Request LSDB Clause Changes Details
					if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.NO)){
						arlReqSpecChngClauses=objChangeRequest1058BI.fetchLsdbClauseDetails(objChangeRequest1058VO);
						if (arlReqSpecChngClauses != null) {
							LogUtil.logMessage("Requested Specification Change "+arlReqSpecChngClauses);
							objChangeRequest1058Form.setReqSpecChngClauseList(arlReqSpecChngClauses);
						}
					}

					//Request Non LSDB Clause Changes Details
					if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.YES)){
						
						ArrayList arlClauseList = objChangeRequest1058BI
								.selectClauseNonLsdb(objChangeRequest1058VO);
						objChangeRequest1058Form.setClaList(arlClauseList);
						LogUtil.logMessage("arlClauseList=" + arlClauseList.size());
					}
					//Added For CR-120
					UserVO objUserVO = new UserVO();
					objUserVO.setUserID(objLoginVO.getUserID());
					objUserVO.setOrderbyFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
					arlRepresentativeList = objChangeRequest1058BI.fetchRepresentativeDetails(objUserVO);
					
					if (arlRepresentativeList != null) {
						LogUtil.logMessage(arlRepresentativeList);
						objChangeRequest1058Form.setRepresentativeList(arlRepresentativeList);
					}
					LogUtil.logMessage("ArrayList Value in ChangeRequest1058Action:"
							+ objChangeRequest1058Form.getRepresentativeList().size());
					
					if(ApplicationConstants.ADMN.equals(objLoginVO.getRoleID())
							|| ApplicationConstants.MSE.equals(objLoginVO.getRoleID())
							|| ApplicationConstants.SE.equals(objLoginVO.getRoleID()))
						objChangeRequest1058Form.setRoleID(ApplicationConstants.VALID_USER);
					 else
						 objChangeRequest1058Form.setRoleID(objLoginVO.getRoleID()); 
					LogUtil.logMessage("RoleId Request:"
							+ objChangeRequest1058Form.getRoleID());

					//Added for CR-127 starts here
					//Model Clause Changes Details
					for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
					}
					arlMdlClaChangesList = objChangeRequest1058BI.fetch1058MdlClaChanges(objChangeRequest1058VO);
					if (arlMdlClaChangesList != null) {
						objChangeRequest1058Form.setMdlClaChangesList(arlMdlClaChangesList);
					}
					LogUtil.logMessage("model clause change list" +arlMdlClaChangesList.size());
					for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
					}
					//Added for CR-127 ends here
					//Added for CR-130 starts here
					//SubSection Details
					for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
					}
					arlImportSubSecList = objChangeRequest1058BI.fetchSubSectionList(objChangeRequest1058VO);
						if (arlImportSubSecList != null) {
							
							objChangeRequest1058Form.setImportSubSecList(arlImportSubSecList);
							objChangeRequest1058Form.setImportSubSecListSize(arlImportSubSecList.size());
						}
					LogUtil.logMessage("Import Sub Section List" +arlImportSubSecList.size());
					
					for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
					}
					
					//1058 SubSection Details
					objChangeRequest1058VO.setSubSecChngReqSeq(0);
					
					for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
					}
					arl1058SubSecList = objChangeRequest1058BI.fetch1058SubSectionList(objChangeRequest1058VO);
						if (arl1058SubSecList != null) {
							
							objChangeRequest1058Form.setSubSec1058List(arl1058SubSecList);
						}
					LogUtil.logMessage("Import Sub Section List" +arl1058SubSecList.size());
					
					for (int cnt = 0; cnt < arl1058SubSecList.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arl1058SubSecList.get(cnt);
					}
					//Added for CR-130 ends here
				} catch (Exception objExp) {
					LogUtil.logMessage("Enters into Exception Block in Action..");
					ErrorInfo objErrorInfo = new ErrorInfo();
					String strError = objExp.getMessage();
					objErrorInfo.setMessage(strError);
					LogUtil.logMessage("Error Message:" + strError);
					LogUtil.logError(objExp, objErrorInfo);
					strForwardKey = ApplicationConstants.FAILURE;
				}
				return objActionMapping.findForward(strForwardKey);
			}
		//Added by @vb106565 for CR-110 Ends here
		
		
		
		//Added by @rr108354 for CR-110 Starts here
		
		
		public ActionForward uploadAttachment(ActionMapping objActionMapping,
				ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
				HttpServletResponse objHttpServletResponse) throws EMDException {
			
			LogUtil.logMessage("Enters into ChangeRequest1058Action:uploadAttachment");
			String strAttachment=null;
			String strForwardKey=null;
			boolean blnFlag = true;
			ArrayList arlAttachmentReturned =new ArrayList();
			ChangeRequest1058AttachmentsVO objChangeRequest1058AttachmentsVO=null;
			HttpSession objSession = objHttpServletRequest.getSession(false);
					
			try {
				
				LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
				LogUtil.logMessage(objLoginVo);
				
				
				ChangeRequest1058Form objChangeRequest1058Form = (ChangeRequest1058Form) objActionForm;
				ChangeRequest1058VO objChangeRequest1058VO =new ChangeRequest1058VO();
				objChangeRequest1058VO.setUserID(objLoginVo.getUserID());
				//LogUtil.logMessage("File in the form total "+objChangeRequest1058Form.());
				
				
				objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
				LogUtil.logMessage("File in the form "+objChangeRequest1058Form.getAttachmentSource());
				FormFile objFormFile =objChangeRequest1058Form.getAttachmentSource();
				
				FileVO objFileVO = new FileVO();
				
				objFileVO.setFileName(objFormFile.getFileName());
				LogUtil.logMessage("FileName in action :"+ objFormFile.getFileName());
				
				objFileVO.setuploadedFile(objFormFile.getFileData());
				objFileVO.setFileLength(objFormFile.getFileSize());
				
				objFileVO.setFileStream(objFormFile.getInputStream());
				objFileVO.setContentType(objFormFile.getContentType());
				
				LogUtil.logMessage("objFileVO.getFileName():   "+objFileVO.getFileName());
				LogUtil.logMessage("objFileVO.getFileLength():   "+objFileVO.getFileLength());
				LogUtil.logMessage("objFileVO.getContentType():   "+objFileVO.getContentType());
				
				if (objFileVO.getFileLength() > 1024*1024*5)	{
					LogUtil.logMessage("Size is big");
					objHttpServletResponse.setContentType("text/plain");
					objHttpServletResponse.getWriter().write(ApplicationConstants.FILESIZETOOLARGE);
					
					objHttpServletResponse.flushBuffer();
				  }
				else {
					
					if(blnFlag){
					
					objChangeRequest1058VO.setFileToAttach(objFileVO);
					ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
					arlAttachmentReturned =objChangeRequest1058BI.uploadAttachment(objChangeRequest1058VO);
					LogUtil
						.logMessage("list in action return"+arlAttachmentReturned);
					strAttachment=new JSONObject().put("attachment",arlAttachmentReturned).toString();
				
					}
				//strAttachment="hello";
				
				objHttpServletResponse.flushBuffer();
			    objHttpServletResponse.setContentType("text/javascript");
			    objHttpServletResponse.getWriter().write(strAttachment);
				}
			}catch(OutOfMemoryError oom){
				LogUtil.logMessage("Size is big Access Denied");
				objHttpServletResponse.setContentType("text/plain");
				try{
					objHttpServletResponse.getWriter().write(ApplicationConstants.FILESIZETOOLARGE);
					objHttpServletResponse.flushBuffer();
				}
				catch(IOException ioe){
					LogUtil
					.logMessage("Enters into Exception Block in ChangeRequest1058Action..");
					ErrorInfo objErrorInfo = new ErrorInfo();
					String strError = ioe.getMessage();
					objErrorInfo.setMessage(strError);
					LogUtil.logMessage("Error Message:" + strError);
					LogUtil.logError(ioe, objErrorInfo);
					strForwardKey = ApplicationConstants.FAILURE;
				}
			}catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception Block in ChangeRequest1058Action..");
				ErrorInfo objErrorInfo = new ErrorInfo();
				String strError = objExp.getMessage();
				objErrorInfo.setMessage(strError);
				LogUtil.logMessage("Error Message:" + strError);
				LogUtil.logError(objExp, objErrorInfo);
				strForwardKey = ApplicationConstants.FAILURE;
			}
			return null;
			}
		
	/***************************************************************************
	 * This method is Used for deleting and fetching the attachments from 1058
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
	
	
	public ActionForward deleteAttachment(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058Action:deleteAttachment");
		
		String strAttachment=null;
		String strForwardKey=null;
		ArrayList arlAttachmentReturned =new ArrayList();
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LogUtil.logMessage(objSession);
		try {
			LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		LogUtil.logMessage(objLoginVo);
		ChangeRequest1058VO objChangeRequest1058VO =new ChangeRequest1058VO();
		objChangeRequest1058VO.setUserID(objLoginVo.getUserID());
		objChangeRequest1058VO.setSeqNo1058(Integer.parseInt(objHttpServletRequest.getParameter("seqNo")));
		objChangeRequest1058VO.setAttachmentToDelete(Integer.parseInt(objHttpServletRequest.getParameter("param")));
		
		ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
		arlAttachmentReturned=objChangeRequest1058BI.deleteAttachment(objChangeRequest1058VO);
		LogUtil
		.logMessage("list in action return"+arlAttachmentReturned);
			strAttachment=new JSONObject().put("attachment",arlAttachmentReturned).toString();
		
		objHttpServletResponse.flushBuffer();
	    objHttpServletResponse.setContentType("text/javascript");
	    objHttpServletResponse.getWriter().write(strAttachment);
		
	} catch (Exception objExp) {
		LogUtil
		.logMessage("Enters into Exception Block in ChangeRequest1058Action..");
		ErrorInfo objErrorInfo = new ErrorInfo();
		String strError = objExp.getMessage();
		objErrorInfo.setMessage(strError);
		LogUtil.logMessage("Error Message:" + strError);
		LogUtil.logError(objExp, objErrorInfo);
		strForwardKey = ApplicationConstants.FAILURE;
	}
	return null;
}
	
	
	
	public ActionForward standardEquipmentThruAJAX(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
			throws DataAccessException, ApplicationException, JSONException {

		LogUtil.logMessage("Entering ChangeRequest1058Action.standardEquipmentThruAJAX");
		String strForward = ApplicationConstants.SUCCESS;
		//ChangeRequest1058Form objChangeRequest1058Form = (ChangeRequest1058Form) objActionForm;
		ArrayList arlStandardEquipList = null;
		//CR_83 lines  ends here
		JSONObject obJSON=new JSONObject();
		String strStdEquips = null;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			// To get Sections
			String strUserID = "";

			LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);

			// Getting User from the session
			if (objLoginVo != null) 
			{
					strUserID = objLoginVo.getUserID();
			}
			
			StandardEquipVO objStandardEquipVO = new StandardEquipVO();
			StandardEquipBI objStandardEquipBO = ServiceFactory
					.getStandardEquipBO();
			objStandardEquipVO.setUserID(objLoginVo.getUserID());
			arlStandardEquipList = objStandardEquipBO
					.fetchStandardEquipment(objStandardEquipVO);
			
			LogUtil.logMessage("Arraylist returned in standardEquipmentThruAJAX"+arlStandardEquipList);
			//arlSectionList = objSectionBo.fetchSections(objSectionVo);

			//if (arlSectionList != null && arlSectionList.size() > 0) {
			strStdEquips = new JSONObject().put("StandardEquipment",arlStandardEquipList).toString();
			//}
			/*else{
				SectionVO objSectionVO=new SectionVO();
				objSectionVO.setSectionSeqNo(-1);
				objSectionVO.setSectionName("---Select---");
			}
			*/
			objHttpServletResponse.flushBuffer();
		    objHttpServletResponse.setContentType("text/javascript");
		    objHttpServletResponse.getWriter().write(strStdEquips);
			
			//CR_83 lines  ends here
		} catch (Exception objExp) {
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
			objHttpServletResponse.setContentType("text/javascript");
			obJSON.append("F",strForward);
		}
		return null;
	}
	
	
	public ActionForward sectionLoadThruAJAX(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
			throws DataAccessException, ApplicationException, JSONException {

		LogUtil.logMessage("Entering ChangeRequest1058Action.sectionLoadThruAJAX");
		String strForward = ApplicationConstants.SUCCESS;
		//ChangeRequest1058Form objChangeRequest1058Form = (ChangeRequest1058Form) objActionForm;
		ArrayList arlSectionList = null;
		//CR_83 lines  ends here
		JSONObject obJSON=new JSONObject();
		String strSections = null;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			// To get Sections
			String strUserID = "";

			LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);

			// Getting User from the session
			if (objLoginVo != null) 
			{
					strUserID = objLoginVo.getUserID();
			}
			int intModelSeqNo = Integer.parseInt((String) objHttpServletRequest.getParameter("Model"));
			int intOrdeKey =Integer.parseInt((String) objHttpServletRequest.getParameter("OrderKey"));
			String dataLocType =(String) objHttpServletRequest.getParameter("DataLocType");
			
			
			SectionVO objSectionVo = new SectionVO();
			//SectionBI objSectionBo = ServiceFactory.getSectionBO();
			objSectionVo.setModelSeqNo(intModelSeqNo);
			objSectionVo.setUserID(strUserID);
			objSectionVo.setOrderKey(intOrdeKey);
			objSectionVo.setDataLocationType(dataLocType);
			
			
			
			
			//OrderSectionBI objOrderSectionBI = ServiceFactory.getOrderSectionBO();
			
			ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
			arlSectionList = objChangeRequest1058BI.fetchOrderSections1058(objSectionVo);
			
			LogUtil.logMessage("Arraylist returned in sectionLoadThruAJAX"+arlSectionList);
			//arlSectionList = objSectionBo.fetchSections(objSectionVo);

			//if (arlSectionList != null && arlSectionList.size() > 0) {
			strSections = new JSONObject().put("Sections",arlSectionList).toString();
			//}
			/*else{
				SectionVO objSectionVO=new SectionVO();
				objSectionVO.setSectionSeqNo(-1);
				objSectionVO.setSectionName("---Select---");
			}
			*/
			objHttpServletResponse.flushBuffer();
		    objHttpServletResponse.setContentType("text/javascript");
		    objHttpServletResponse.getWriter().write(strSections);
			
			//CR_83 lines  ends here
		} catch (Exception objExp) {
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
			objHttpServletResponse.setContentType("text/javascript");
			obJSON.append("F",strForward);
		}
		return null;
	}
	
	public ActionForward subSectionLoadThruAJAX(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
			throws DataAccessException, ApplicationException, JSONException {

		LogUtil.logMessage("Entering ChangeRequest1058Action.subSectionLoadThruAJAX");
		String strForward = ApplicationConstants.SUCCESS;
		JSONObject obJSON=new JSONObject();
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);

			//SubSectionVO objSubSectionVo = new SubSectionVO();
			//ModelSubSectionBI objModelSubSectionBo = ServiceFactory.getSubSecMaintBO();

			ArrayList arlSubSectionList = new ArrayList();

			String strUserID = "";

			LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);

			// Getting User from the session
			if (objLoginVo != null) 
			{
				strUserID = objLoginVo.getUserID();
			}
			int intModelSeqNo = Integer.parseInt((String) objHttpServletRequest.getParameter("Model"));
			int intSecSeqNo = Integer.parseInt((String) objHttpServletRequest.getParameter("Section"));
			int intOrdeKey =Integer.parseInt((String) objHttpServletRequest.getParameter("OrderKey"));
			String dataLocType =(String) objHttpServletRequest.getParameter("DataLocType");
			
			
			SectionVO objSectionVo = new SectionVO();
			//SectionBI objSectionBo = ServiceFactory.getSectionBO();
			ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
			
			objSectionVo.setModelSeqNo(intModelSeqNo);
			objSectionVo.setUserID(strUserID);
			objSectionVo.setOrderKey(intOrdeKey);
			objSectionVo.setDataLocationType(dataLocType);
			objSectionVo.setSectionSeqNo(intSecSeqNo);
			arlSubSectionList = objChangeRequest1058BI.fetchOrderSubSections1058(objSectionVo);
			
			//arlSubSectionList= objOrderSectionBI
			//.fetchSectionDetails(objSectionVo);
			LogUtil.logMessage("Arraylist returned in subSectionLoadThruAJAX"+arlSubSectionList);
			
			String strSubSections = null;
			//if (arlSubSectionList != null && arlSubSectionList.size() > 0) {
				strSubSections = new JSONObject().put("SubSections",arlSubSectionList).toString();
			//}
			/*else{
				SubSectionVO objSubSectionVO=new SubSectionVO();
				objSubSectionVO.setSubSecSeqNo(-1);
				objSubSectionVO.setSubSecName("---Select---");
				
			}
			*/
			objHttpServletResponse.flushBuffer();
		    objHttpServletResponse.setContentType("text/javascript");
		    objHttpServletResponse.getWriter().write(strSubSections);			
			
		} catch (Exception objExp) {
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
			objHttpServletResponse.setContentType("text/javascript");
			obJSON.append("F",strForward);
		}
		LogUtil.logMessage("strForwardKey :" + strForward);
   	return null;

	}
	
	public ActionForward ComponentGroupThruAJAX(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
			throws DataAccessException, ApplicationException, JSONException {

		LogUtil.logMessage("Entering ChangeRequest1058Action.ComponentGroupThruAJAX");
		String strForward = ApplicationConstants.SUCCESS;
		JSONObject obJSON=new JSONObject();
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);

			ComponentVO objComponentVO = new ComponentVO();
			
			ArrayList arlCompGrpList = new ArrayList();

			String strUserID = "";

			LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);

			// Getting User from the session
			if (objLoginVo != null) 
			{
				strUserID = objLoginVo.getUserID();
			}
			
			ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
			
			int intModelSeqNo = Integer.parseInt((String) objHttpServletRequest.getParameter("Model"));
			int intSecSeqNo = Integer.parseInt((String) objHttpServletRequest.getParameter("Section"));
			int intSubSecSeqNo = Integer.parseInt((String) objHttpServletRequest.getParameter("SubSection"));
			int intOrdeKey =Integer.parseInt((String) objHttpServletRequest.getParameter("OrderKey"));
			String dataLocType =(String) objHttpServletRequest.getParameter("DataLocType");
						
			// To get SubSections
			LogUtil.logMessage("intSubSecSeqNo "+intSubSecSeqNo);
			
			objComponentVO.setUserID(strUserID);
			objComponentVO.setModelSeqNo(intModelSeqNo);
			objComponentVO.setSubSectionSeqNo(intSubSecSeqNo);
			objComponentVO.setOrderKey(intOrdeKey);
			objComponentVO.setDataLocationType(dataLocType);
			objComponentVO.setReviseCheck(0);
			
			arlCompGrpList = objChangeRequest1058BI.fetchComponentGrpMap1058(objComponentVO);
			LogUtil.logMessage("arrayList of component returned in action "+arlCompGrpList);
			String strCompGroups = null;
			//if (arlCompGrpList != null && arlCompGrpList.size() > 0) {
				strCompGroups = new JSONObject().put("ComponentGroups",arlCompGrpList).toString();
			//}
			/*else{
				CompGroupVO objCompGroupVo = new CompGroupVO();
				objCompGroupVo.setComponentGroupSeqNo(-1);
				objCompGroupVo.setComponentGroupName("---Select---");
				arlCompGrpList.add(objCompGroupVo);
				strCompGroups = new JSONObject().put("ComponentGroups",arlCompGrpList).toString();
			}*/
			objHttpServletResponse.flushBuffer();
		    objHttpServletResponse.setContentType("text/javascript");
		    objHttpServletResponse.getWriter().write(strCompGroups);			
			
		} catch (Exception objExp) {
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
			objHttpServletResponse.setContentType("text/javascript");
			obJSON.append("F",strForward);
		}
		LogUtil.logMessage("strForwardKey :" + strForward);
   	return null;

	}
	
	public ActionForward ComponentsThruAJAX(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
			throws DataAccessException, ApplicationException, JSONException {

		LogUtil.logMessage("Entering ChangeRequest1058Action.ComponentsThruAJAX");
		String strForward = ApplicationConstants.SUCCESS;
		JSONObject obJSON=new JSONObject();
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);

			ComponentVO objComponentVO = new ComponentVO();
			ModelCompBI objModelCompBO = ServiceFactory.getModelCompBO();

			ArrayList arlCompGrpList = new ArrayList();
			ArrayList arlCompList = new ArrayList();
			String strUserID = "";

			LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);

			// Getting User from the session
			if (objLoginVo != null) 
			{
				strUserID = objLoginVo.getUserID();
			}
			
			ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
			int intCompGrpSeqNo =Integer.parseInt((String) objHttpServletRequest.getParameter("ComponentGroup"));
			int intModelSeqNo = Integer.parseInt((String) objHttpServletRequest.getParameter("Model"));
			int intSecSeqNo = Integer.parseInt((String) objHttpServletRequest.getParameter("Section"));
			int intSubSecSeqNo = Integer.parseInt((String) objHttpServletRequest.getParameter("SubSection"));
			int intOrdeKey =Integer.parseInt((String) objHttpServletRequest.getParameter("OrderKey"));
			String dataLocType =(String) objHttpServletRequest.getParameter("DataLocType");
						
			// To get SubSections
			LogUtil.logMessage("intSubSecSeqNo "+intSubSecSeqNo);
			
			objComponentVO.setUserID(strUserID);
			objComponentVO.setModelSeqNo(intModelSeqNo);
			objComponentVO.setSubSectionSeqNo(intSubSecSeqNo);
			objComponentVO.setOrderKey(intOrdeKey);
			objComponentVO.setDataLocationType(dataLocType);
			objComponentVO.setComponentGroupSeqNo(intCompGrpSeqNo);
			objComponentVO.setReviseCheck(0);
			
			arlCompGrpList = objChangeRequest1058BI.fetchComponentGrpMap1058(objComponentVO);
			LogUtil.logMessage("arrayList of component returned in action "+arlCompGrpList);
			String strCompGroups = null;
			if(arlCompGrpList.size() > 0){
			CompGroupVO objCompGroupVo = (CompGroupVO) arlCompGrpList.get(0);
			ArrayList arlComponents =objCompGroupVo.getComponentVO();
			strCompGroups = new JSONObject().put("Components",arlComponents).toString();
			}else{
			ArrayList arlComponents = new ArrayList();	
			strCompGroups = new JSONObject().put("Components",arlComponents).toString();	
			}
			objHttpServletResponse.flushBuffer();
		    objHttpServletResponse.setContentType("text/javascript");
		    objHttpServletResponse.getWriter().write(strCompGroups);
			
		} catch (Exception objExp) {
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
			objHttpServletResponse.setContentType("text/javascript");
			obJSON.append("F",strForward);
		}
		LogUtil.logMessage("strForwardKey :" + strForward);
   	return null;

	}
	
	
	public ActionForward getParentClause(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into ChangeRequest1058Action:getParentClause");
		String strForwardKey = ApplicationConstants.ParentClause;
		ArrayList arlParentClause8List = new ArrayList();
				ChangeRequest1058Form objChangeRequest1058Form = (ChangeRequest1058Form) objActionForm;
			//objChangeRequest1058VO.setUserID(objLoginVo.getUserID());
			
			ArrayList arlPartOfList = new ArrayList();
			ArrayList arlSectionList;
			ArrayList arlSubSectionList = new ArrayList();
			//String strForwardKey = ApplicationConstants.SUCCESS;
			String strUserID = "";
			ClauseVO objClauseVO = new ClauseVO();
			SectionVO objSectionVO = new SectionVO();
			SubSectionVO objSubSecVO = new SubSectionVO();
			//OrderClausePartOfPopUpForm objOrderClausePartOfPopUpForm = (OrderClausePartOfPopUpForm) objActionForm;
			try {
				HttpSession objSession = objHttpServletRequest.getSession(false);
				LoginVO objLoginVo = (LoginVO) objSession
				.getAttribute(ApplicationConstants.USER_IN_SESSION);
				if (objLoginVo != null) {
					strUserID = objLoginVo.getUserID();
				}
				if(objHttpServletRequest.getParameter("OrderNum")!=null){
					LogUtil.logMessage("Order Number "+objHttpServletRequest.getParameter("OrderNum"));
					objClauseVO.setOrderNo(objHttpServletRequest.getParameter("OrderNum"));
				}else{
				objClauseVO.setOrderNo(objChangeRequest1058Form.getOrderNo());}
				
				if(objHttpServletRequest.getParameter("subSectionSeqNo")!=null){
					LogUtil.logMessage("subSectionSeqNo "+objHttpServletRequest.getParameter("subSectionSeqNo"));
					objSectionVO.setSubSecSeqNo(Integer.parseInt(objHttpServletRequest.getParameter("subSectionSeqNo")));
				}else{
				objSectionVO.setSubSecSeqNo(objChangeRequest1058Form
						.getSubSectionSeqNo());}
				
				if(objHttpServletRequest.getParameter("orderKey")!=null){
					LogUtil.logMessage("orderKey "+objHttpServletRequest.getParameter("orderKey"));
					objSectionVO.setOrderKey(Integer.parseInt(objHttpServletRequest.getParameter("orderKey")));
				}else{
				objSectionVO.setOrderKey(objChangeRequest1058Form
						.getOrderKey());}
				if(objHttpServletRequest.getParameter("sectionSeqNo")!=null){
					LogUtil.logMessage("sectionSeqNo "+objHttpServletRequest.getParameter("sectionSeqNo"));
					objSectionVO.setSectionSeqNo(Integer.parseInt(objHttpServletRequest.getParameter("sectionSeqNo")));
				}else{
				objSectionVO.setSectionSeqNo(objChangeRequest1058Form
						.getSectionSeqNo());}
				//DataLocType Updated for CR-121
				if(objHttpServletRequest.getParameter("dataLocType")!=null){
					//LogUtil.logMessage("dataLocType "+objHttpServletRequest.getParameter("dataLocType"));
					objSectionVO.setDataLocationType(objHttpServletRequest.getParameter("dataLocType"));
				}
				objSectionVO.setUserID(strUserID);
				OrderSectionPopUpBI objOrderSectionPopUpBO = ServiceFactory
				.getOrderSectionPopUpBO();
				arlPartOfList = objOrderSectionPopUpBO.fetchClauses(objSectionVO);
				if (arlPartOfList.size() != 0) {
					
					objChangeRequest1058Form.setClauseGroup(arlPartOfList);
					objChangeRequest1058Form.setOrderNo(objClauseVO
							.getOrderNo());
					objClauseVO.setOrderKey(objChangeRequest1058Form
							.getOrderKey());
					
				} else {
					
					objChangeRequest1058Form
					.setMessageID(ApplicationConstants.NO_PART_OF_ORDER_SECTION);
					objChangeRequest1058Form.setOrderNo(objClauseVO
							.getOrderNo());
					
				}
				
				objSectionVO.setModelSeqNo(objChangeRequest1058Form
						.getMdlSeqNo());
				objSectionVO.setOrderKey(objChangeRequest1058Form
						.getOrderKey());
				objSectionVO.setUserID(objLoginVo.getUserID());
				
				OrderSectionBI objOrderSectionBO = ServiceFactory
				.getOrderSectionBO();
				arlSectionList = objOrderSectionBO.fetchOrdSections(objSectionVO);
				objSectionVO.setSectionSeqNo(objChangeRequest1058Form
						.getSectionSeqNo());
				arlSubSectionList = objOrderSectionBO
				.fetchSectionDetails(objSectionVO);
				if(objHttpServletRequest.getParameter("sectionName")!=null){
					LogUtil.logMessage("sectionName "+objHttpServletRequest.getParameter("sectionName"));
					objChangeRequest1058Form.setHdnSectionName(objHttpServletRequest.getParameter("sectionName"));
				}
				if(objHttpServletRequest.getParameter("subSectionName")!=null){
					LogUtil.logMessage("subSectionName "+objHttpServletRequest.getParameter("subSectionName"));
					objChangeRequest1058Form.setHdnSubSectionName(objHttpServletRequest.getParameter("subSectionName"));
				}
				
			
				LogUtil.logMessage("Done with fetch clause ");
			
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ChangeRequest1058Action..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	
	public ActionForward saveAddClause(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into ChangeRequest1058Action:saveAddClause");
		String strForwardKey = ApplicationConstants.CREATE;
		ArrayList arlRequest1058List=new ArrayList();
		ArrayList arlCategories =new ArrayList();
		ArrayList arlRequestTypes =new ArrayList();
		ArrayList arlClauseChangeTypes  =new ArrayList();
		ArrayList arlReqSpecChngClauses=new ArrayList();
		ArrayList arlRepresentativeList = new ArrayList();
		ArrayList arlMdlClaChangesList = new ArrayList();//Added for CR-127
		ArrayList arlImportSubSecList   = new ArrayList();//Added for CR-130
		ArrayList arl1058SubSecList   = new ArrayList();//Added for CR-130
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LogUtil.logMessage(objSession);
		int insertStatus=0;
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			ChangeRequest1058Form objChangeRequest1058Form = (ChangeRequest1058Form) objActionForm;
			ChangeRequest1058VO objChangeRequest1058VO =new ChangeRequest1058VO();
			objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
			objChangeRequest1058VO.setUserID(objLoginVo.getUserID());
			ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
			
			if (objChangeRequest1058Form.getStatusSeqNo() == ApplicationConstants.PENDING_ONE ) {
											
				objChangeRequest1058VO.setOrderNo(objChangeRequest1058Form.getOrderNo());
				objChangeRequest1058VO.setCustName(objChangeRequest1058Form.getCustName());
				objChangeRequest1058VO.setMdlName(objChangeRequest1058Form.getMdlName());
				objChangeRequest1058VO.setOrderQty(objChangeRequest1058Form.getOrderQty());
				objChangeRequest1058VO.setReqTypeSeqNo(objChangeRequest1058Form.getReqTypeSeqNo());
				objChangeRequest1058VO.setCategorySeqNo(objChangeRequest1058Form.getCategorySeqNo());
				objChangeRequest1058VO.setGenDesc(objChangeRequest1058Form.getGenDesc());
				objChangeRequest1058VO.setUnitNumber(objChangeRequest1058Form.getUnitNumber());
				objChangeRequest1058VO.setMcrNumber(objChangeRequest1058Form.getMcrNumber());
				objChangeRequest1058VO.setRoadNumber(objChangeRequest1058Form.getRoadNumber());
				objChangeRequest1058VO.setMcrReq(objChangeRequest1058Form.getMcrReq());			
				objChangeRequest1058VO.setSpecRev(objChangeRequest1058Form.getSpecRev());
				objChangeRequest1058VO.setNonLsdbFlag(objChangeRequest1058Form.getHdnNonLsdbFlag());
				
				int intUpdate1058details  = objChangeRequest1058BI.update1058details(objChangeRequest1058VO);
	
			}
			
			ClauseVO objClauseVO = new ClauseVO();
			
			String strUserID = null;
			String strClauseDesc = null;
			String strComments = null;
			String strReason = null;
			
			int intSuccess = 0;

			String[] clauseTableArray1, clauseTableArray2, clauseTableArray3, clauseTableArray4, clauseTableArray5 = null;
			StandardEquipVO objStandardEquipVO = new StandardEquipVO();
			ClauseTableDataVO objTableDataVO = null;
			ArrayList arlStandardEquipList = new ArrayList();
			ArrayList arlTableList = new ArrayList();
			ArrayList arlSelCompSeqNo = new ArrayList();
			StringTokenizer stCompSeqNo = null;
			// Added for CR-74 VV49326 04-06-09
			String[] stCompName = null;
			ArrayList arlCompName = new ArrayList();
			ArrayList arlTabData = new ArrayList();
			ArrayList arlClauseVO = new ArrayList();
			String[] partOf =new String[4];
			int[] partOfSeqNo=new int[4];
			
			String[] clauseSeqNum =new String[4];
			
			if ((objChangeRequest1058Form.getComponentSeqNo() != null)) {
				LogUtil.logMessage("Component Seq NO"+objChangeRequest1058Form.getComponentSeqNo());
				stCompSeqNo = new StringTokenizer(objChangeRequest1058Form
						.getComponentSeqNo(), "~");

				while (stCompSeqNo.hasMoreTokens()) {
					arlSelCompSeqNo.add(stCompSeqNo.nextToken());
				}
				//Check Here 
				
				
				// Added for CR-74 VV49326 04-06-09
				/**
				 * To retain Components Tied To Clause after Serverside
				 * validation-Starts Here*
				 */
				if (objChangeRequest1058Form.getComponentSeqNo() != null
						&& !(objChangeRequest1058Form.getComponentSeqNo()
								.equals(""))) {
					stCompName = objChangeRequest1058Form.getHdnComponentName()
							.split("~");
					for (int i = 0; i < stCompName.length; i++) {
						ComponentVO objCompVo = new ComponentVO();
						objCompVo.setComponentSeqNo(Integer
								.parseInt((String) arlSelCompSeqNo.get(i)));
						objCompVo.setComponentDescription(stCompName[i]);
						arlCompName.add(objCompVo);
					}
				}

			}
			if(objChangeRequest1058Form.getHdnLeadComponentSeqNoinAdd()!=0){
				arlSelCompSeqNo.add(""+objChangeRequest1058Form.getHdnLeadComponentSeqNoinAdd());
			}
			if(objChangeRequest1058Form.getHdnLeadComponentNameinAdd()!=null){
				arlCompName.add(objChangeRequest1058Form.getHdnLeadComponentNameinAdd());
			}
			LogUtil.logMessage("Component names"+arlCompName);
			objChangeRequest1058Form.setComponentVO(arlCompName);
			LogUtil.logMessage("arlSelCompSeqNo size :"
					+ arlSelCompSeqNo.size());
			
			if (objChangeRequest1058Form.getComponentGrpSeqNoinAdd() != -1) {
				LogUtil.logMessage("Lead Component group Seq number"+objChangeRequest1058Form.getComponentGrpSeqNoinAdd());
				objClauseVO.setLeadCompGrpSeqNo(objChangeRequest1058Form.getComponentGrpSeqNoinAdd());
			}
			
			clauseTableArray1 = objChangeRequest1058Form.getClauseTableDataArray1Add();
			if (clauseTableArray1 == null || clauseTableArray1.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData1(null);
			} else {

				for (int counter = 0; counter < clauseTableArray1.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData1(objChangeRequest1058Form.getClauseTableDataArray1Add());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}

			}
			arlTableList.add(objTableDataVO);
			
			clauseTableArray2 = objChangeRequest1058Form.getClauseTableDataArray2Add();

			if (clauseTableArray2 == null || clauseTableArray2.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData2(null);
			} else {
				for (int counter = 0; counter < clauseTableArray2.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData2(objChangeRequest1058Form.getClauseTableDataArray2Add());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}

			}
			arlTableList.add(objTableDataVO);

			// To Get Table Data3 Values From Form
			clauseTableArray3 = objChangeRequest1058Form.getClauseTableDataArray3Add();

			if (clauseTableArray3 == null || clauseTableArray3.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData3(null);
			} else {
				for (int counter = 0; counter < clauseTableArray3.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData3(objChangeRequest1058Form.getClauseTableDataArray3Add());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}

			}
			arlTableList.add(objTableDataVO);

			// To Get Table Data4 Values From Form
			clauseTableArray4 = objChangeRequest1058Form.getClauseTableDataArray4Add();

			if (clauseTableArray4 == null || clauseTableArray4.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData4(null);
			} else {
				for (int counter = 0; counter < clauseTableArray4.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData4(objChangeRequest1058Form.getClauseTableDataArray4Add());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}

			}
			arlTableList.add(objTableDataVO);

			// To Get Table Data5 Values From Form
			clauseTableArray5 = objChangeRequest1058Form.getClauseTableDataArray5Add();

			if (clauseTableArray5 == null || clauseTableArray5.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData5(null);
			} else {
				for (int counter = 0; counter < clauseTableArray5.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData5(objChangeRequest1058Form.getClauseTableDataArray5Add());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
			}
			arlTableList.add(objTableDataVO);
			
			if (clauseTableArray1 != null) {
				for (int k = 0; k < clauseTableArray1.length; k++) {
					ArrayList arlDataRows = new ArrayList();
					if (clauseTableArray1 != null
							&& clauseTableArray1.length > k
							&& clauseTableArray1[k] != null) {
						arlDataRows.add(clauseTableArray1[k]);

					} else {
						arlDataRows.add("");
					}

					if (clauseTableArray2 != null
							&& clauseTableArray2.length > k
							&& clauseTableArray2[k] != null) {
						arlDataRows.add(clauseTableArray2[k]);

					} else {
						arlDataRows.add("");
					}

					if (clauseTableArray3 != null
							&& clauseTableArray3.length > k
							&& clauseTableArray3[k] != null) {
						arlDataRows.add(clauseTableArray3[k]);
					} else {
						arlDataRows.add("");
					}
					if (clauseTableArray4 != null
							&& clauseTableArray4.length > k
							&& clauseTableArray4[k] != null) {
						arlDataRows.add(clauseTableArray4[k]);
					} else {
						arlDataRows.add("");
					}
					if (clauseTableArray5 != null
							&& clauseTableArray5.length > k
							&& clauseTableArray5[k] != null) {
						arlDataRows.add(clauseTableArray5[k]);
					} else {
						arlDataRows.add("");
					}
					arlTabData.add(arlDataRows);
				}

			}
			
				// Getting User from the session
				if (objLoginVo != null) {
						strUserID = objLoginVo.getUserID();
					}
				
				strClauseDesc = objChangeRequest1058Form.getAddClauseDescription();
				LogUtil.logMessage("Clause Description"+strClauseDesc);
				
				strComments = objChangeRequest1058Form.getCommentsAdd();
				LogUtil.logMessage("Comments"+strComments);
				
				strReason = objChangeRequest1058Form.getReasonAdd();
				LogUtil.logMessage("Reason"+strReason);
				
				if (strClauseDesc != null && !"".equals(strClauseDesc)) {
					strClauseDesc = ApplicationUtil.trim(strClauseDesc);
				}
				if (strComments != null && !"".equals(strComments)) {
					strComments = ApplicationUtil.trim(strComments);
				}
				if (strReason != null && !"".equals(strReason)) {

					strReason = ApplicationUtil.trim(strReason);
				}
				
				if (objChangeRequest1058Form.getStandardEquipmentSeqNoAdd() != -1) {
					objStandardEquipVO
							.setStandardEquipmentSeqNo(objChangeRequest1058Form.getStandardEquipmentSeqNoAdd());
					arlStandardEquipList.add(objStandardEquipVO);
					LogUtil.logMessage("Standard Equip "+arlStandardEquipList);
					objClauseVO.setObjStandardEquipVO(arlStandardEquipList);
				} else {
					objClauseVO.setObjStandardEquipVO(null);
				}
				
				objClauseVO.setOrderKey(objChangeRequest1058Form.getOrderKey());
				LogUtil.logMessage("Order Key "+objClauseVO.getOrderKey());
				objClauseVO.setDataLocationType(objChangeRequest1058Form.getDataLocType());//Updated for datalocation issue CR-121
				objClauseVO.setModelSeqNo(objChangeRequest1058Form.getMdlSeqNo());
				LogUtil.logMessage("Model SeqNo"+objClauseVO.getModelSeqNo());
				objClauseVO.setSubSectionSeqNo(objChangeRequest1058Form.getSubSectionSeqNo());
				LogUtil.logMessage("SubSection Seq No"+objClauseVO.getSubSectionSeqNo());
				objClauseVO.setParentClauseSeqNo(objChangeRequest1058Form.getHdnParentClauseSeqNo());
				LogUtil.logMessage("Parent Clause Seq No"+objClauseVO.getParentClauseSeqNo());
				objClauseVO.setClauseDesc(strClauseDesc);
				objClauseVO.setComponentVO(arlSelCompSeqNo);
				objClauseVO.setEdlNo(objChangeRequest1058Form.getNewEdlNoAdd());
				LogUtil.logMessage("EDL NOs"+objClauseVO.getEdlNo());
				objClauseVO.setRefEDLNo(objChangeRequest1058Form.getRefEdlNoAdd());
				LogUtil.logMessage("Ref EDL NOs"+objClauseVO.getRefEDLNo());
				//check for part of to only four
				if(objHttpServletRequest.getParameter("reviseCheck")==null){
				
				for (int i = 4; i < objChangeRequest1058Form.getPartOf().length-4; i++) {
					LogUtil.logMessage("part of value--->"+i+" "
							+ objChangeRequest1058Form.getPartOf()[i]);
				if(objChangeRequest1058Form.getPartOf()[i]!=null){
					partOf[i-4]=objChangeRequest1058Form.getPartOf()[i];
				}
				
				}
				//check for partof seq no to only four
				for (int i = 4; i < objChangeRequest1058Form.getPartOfSeqNo().length-4; i++) {
					LogUtil.logMessage("part of seq	no value--->"+i+" "
							+ objChangeRequest1058Form.getPartOfSeqNo()[i]);
					if(objChangeRequest1058Form.getPartOfSeqNo()[i]!=0){
					partOfSeqNo[i-4]=objChangeRequest1058Form.getPartOfSeqNo()[i];}
				}
				
				for (int i = 4; i < objChangeRequest1058Form.getClauseSeqNum().length-4; i++) {
					LogUtil.logMessage("clause no value--->"+i+" "
							+ objChangeRequest1058Form.getClauseSeqNum()[i]);
					if(objChangeRequest1058Form.getClauseSeqNum()[i]!=null){
						clauseSeqNum[i-4]=objChangeRequest1058Form.getClauseSeqNum()[i];}
				}
				}
				else{
					
					objClauseVO.setClauseChangeReqSeqNo(objChangeRequest1058Form.getClauseChangeRequestSeqNo());
					
					for (int i = 0; i < objChangeRequest1058Form.getPartOf().length; i++) {
						LogUtil.logMessage("part of value--->"+i+" "
								+ objChangeRequest1058Form.getPartOf()[i]);
					if(objChangeRequest1058Form.getPartOf()[i]!=null){
						partOf[i]=objChangeRequest1058Form.getPartOf()[i];
					}
					
					}
					//check for partof seq no to only four
					for (int i = 0; i < objChangeRequest1058Form.getPartOfSeqNo().length; i++) {
						LogUtil.logMessage("part of seq	no value--->"+i+" "
								+ objChangeRequest1058Form.getPartOfSeqNo()[i]);
						if(objChangeRequest1058Form.getPartOfSeqNo()[i]!=0){
						partOfSeqNo[i]=objChangeRequest1058Form.getPartOfSeqNo()[i];}
					}
					
					for (int i = 0; i < objChangeRequest1058Form.getClauseSeqNum().length; i++) {
						LogUtil.logMessage("clause no value--->"+i+" "
								+ objChangeRequest1058Form.getClauseSeqNum()[i]);
						if(objChangeRequest1058Form.getClauseSeqNum()[i]!=null){
							clauseSeqNum[i]=objChangeRequest1058Form.getClauseSeqNum()[i];}
					}
					
				}
				//Clause Seq number left on purpose from previous code
				
//				check for part of to only four
				if (objChangeRequest1058Form.getPartOfSeqNo() != null) {
					if (objChangeRequest1058Form.getPartOfSeqNo().length != 0) {
						
						objClauseVO.setPartOfSeqNo(partOfSeqNo);
					}

				}
				
//				check for partof seq no to only four
				if (objChangeRequest1058Form.getPartOf().length != 0) {
					objClauseVO.setClauseNoArray(partOf);
					
				}
				
				if (objChangeRequest1058Form.getClauseSeqNum().length != 0) {
					objClauseVO.setClauseSeqNoArray(clauseSeqNum);
				}
				
				if (objChangeRequest1058Form.getDwoNoAdd() != null
						&& !objChangeRequest1058Form.getDwoNoAdd().equals("")) {
					String strDwoNo = ApplicationUtil.trim(objChangeRequest1058Form
							.getDwoNoAdd());
					LogUtil.logMessage("DWO Number"+strDwoNo);
					objClauseVO.setDwONumber(Integer.parseInt(strDwoNo));
				}
				
				if (objChangeRequest1058Form.getPartNoAdd() != null
						&& !objChangeRequest1058Form.getPartNoAdd().equals("")) {
					String strPartNo = objChangeRequest1058Form.getPartNoAdd();
					LogUtil.logMessage("Part Number"+strPartNo);
					objClauseVO.setPartNumber(Integer.parseInt(strPartNo));
				}

				if (objChangeRequest1058Form.getPriceBookNoAdd() != null
						&& !objChangeRequest1058Form.getPriceBookNoAdd().equals("")) {
					String strPriceNo = objChangeRequest1058Form.getPriceBookNoAdd();
					LogUtil.logMessage("Price book Number"+strPriceNo);
					objClauseVO.setPriceBookNumber(Integer.parseInt(strPriceNo));
				}
				
				objClauseVO.setComments(strComments);
				objClauseVO.setReason(strReason);

				objClauseVO.setTableDataVO(arlTableList);

				objClauseVO.setUserID(strUserID);
				objClauseVO.setTableArrayData1(arlTabData);
				
				objClauseVO.setSectionSeqNo(objChangeRequest1058Form.getSectionSeqNo());
				LogUtil.logMessage("SectionSeq NO"+objClauseVO.getSectionSeqNo());
				objClauseVO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
				LogUtil.logMessage("seq no 1058 "+objClauseVO.getSeqNo1058());
				objClauseVO.setClauseChangeType(objChangeRequest1058Form.getClauseChangeType());
				LogUtil.logMessage("Clause Change Type"+objClauseVO.getClauseChangeType());
				

				if(objHttpServletRequest.getParameter("reviseCheck")!=null){
				insertStatus=objChangeRequest1058BI.updateClause(objClauseVO);
				}
				else{
				insertStatus =objChangeRequest1058BI.saveClause(objClauseVO);
				}
				LogUtil.logMessage("Insert/Update Status "+insertStatus);
				
				if(insertStatus>0){
					objChangeRequest1058Form.setMessageID(String.valueOf(insertStatus));
				}else {
					objChangeRequest1058Form.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				}
			
			arlRequest1058List = objChangeRequest1058BI.fetch1058Details(objChangeRequest1058VO);
			if (arlRequest1058List != null) {
				LogUtil.logMessage(arlRequest1058List);
				objChangeRequest1058Form.setRequestDetailsList(arlRequest1058List);
			}
			objChangeRequest1058Form.setReviseClauseCheck(0);
			objChangeRequest1058Form.setCheckCompChangeClauses(0);
			objChangeRequest1058Form.setCheckVersionClause(0);
			
			LogUtil.logMessage("Requests in ChangeRequest1058Action:"
					+ objChangeRequest1058Form.getRequestDetailsList().size());
			
			//Request 1058 Categories
			arlCategories = objChangeRequest1058BI.fetch1058RequestCategories(objLoginVo);

			if (arlCategories != null) {
				LogUtil.logMessage("Request Categories "+arlCategories);
				objChangeRequest1058Form.setCategories(arlCategories);
			}

			//Request 1058 Request Types
			arlRequestTypes = objChangeRequest1058BI.fetch1058RequestTypes(objLoginVo);
			
			if (arlRequestTypes != null) {
				LogUtil.logMessage("Request Types "+arlRequestTypes);
				objChangeRequest1058Form.setReqTypes(arlRequestTypes);
			}
			
			//Request LSDB Clause Change Types			
			arlClauseChangeTypes = objChangeRequest1058BI.fetch1058ClauseChangeTypes(objLoginVo);
			
			if (arlClauseChangeTypes != null) {
				LogUtil.logMessage("Clause Change Types "+arlClauseChangeTypes);
				objChangeRequest1058Form.setClauseChangeTypes(arlClauseChangeTypes);
			}
			
			objChangeRequest1058VO.setClaChngReqSeq(0);
			
			//Request LSDB Clause Changes Details
			if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.NO)){
				arlReqSpecChngClauses=objChangeRequest1058BI.fetchLsdbClauseDetails(objChangeRequest1058VO);
				if (arlReqSpecChngClauses != null) {
					LogUtil.logMessage("Requested Specification Change "+arlReqSpecChngClauses);
					objChangeRequest1058Form.setReqSpecChngClauseList(arlReqSpecChngClauses);
				}
			}

			//Request Non LSDB Clause Changes Details
			if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.YES)){
				
				ArrayList arlClauseList = objChangeRequest1058BI
						.selectClauseNonLsdb(objChangeRequest1058VO);
				objChangeRequest1058Form.setClaList(arlClauseList);
				LogUtil.logMessage("arlClauseList=" + arlClauseList.size());
			}
			
			//RepresentativeList Details
			//Added For CR-120
			UserVO objUserVO = new UserVO();
			objUserVO.setUserID(objLoginVo.getUserID());
			objUserVO.setOrderbyFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
			arlRepresentativeList = objChangeRequest1058BI.fetchRepresentativeDetails(objUserVO);
				
			if (arlRepresentativeList != null) {
				LogUtil.logMessage(arlRepresentativeList);
				objChangeRequest1058Form.setRepresentativeList(arlRepresentativeList);
			}
			LogUtil.logMessage("ArrayList Value in ChangeRequest1058Action:"
					+ objChangeRequest1058Form.getRepresentativeList().size());

			//Added for CR-127 starts here
			//Model Clause Changes Details
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			
			arlMdlClaChangesList = objChangeRequest1058BI.fetch1058MdlClaChanges(objChangeRequest1058VO);
			if (arlMdlClaChangesList != null) {
				objChangeRequest1058Form.setMdlClaChangesList(arlMdlClaChangesList);
			}
			LogUtil.logMessage("model clause change list" +arlMdlClaChangesList.size());
			for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
			}
			//Added for CR-127 ends here
			//Added for CR-130 starts here
			//SubSection Details
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			arlImportSubSecList = objChangeRequest1058BI.fetchSubSectionList(objChangeRequest1058VO);
				if (arlImportSubSecList != null) {
					
					objChangeRequest1058Form.setImportSubSecList(arlImportSubSecList);
					objChangeRequest1058Form.setImportSubSecListSize(arlImportSubSecList.size());
				}
			LogUtil.logMessage("Import Sub Section List" +arlImportSubSecList.size());
			
			for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
			}
			
			//1058 SubSection Details
			objChangeRequest1058VO.setSubSecChngReqSeq(0);
			
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			arl1058SubSecList = objChangeRequest1058BI.fetch1058SubSectionList(objChangeRequest1058VO);
				if (arl1058SubSecList != null) {
					
					objChangeRequest1058Form.setSubSec1058List(arl1058SubSecList);
				}
			LogUtil.logMessage("Import Sub Section List" +arl1058SubSecList.size());
			
			for (int cnt = 0; cnt < arl1058SubSecList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arl1058SubSecList.get(cnt);
			}
			//Added for CR-130 ends here
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ChangeRequest1058Action..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	
	
	public ActionForward saveModifyClause(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into ChangeRequest1058Action:saveModifyClause");
		String strForwardKey = ApplicationConstants.CREATE;
		ArrayList arlRequest1058List=new ArrayList();
		ArrayList arlCategories =new ArrayList();
		ArrayList arlRequestTypes =new ArrayList();
		ArrayList arlClauseChangeTypes  =new ArrayList();
		ArrayList arlReqSpecChngClauses=new ArrayList();
		ArrayList arlRepresentativeList = new ArrayList();
		ArrayList arlMdlClaChangesList = new ArrayList();//Added for CR-127
		ArrayList arlImportSubSecList   = new ArrayList();//Added for CR-130
		ArrayList arl1058SubSecList   = new ArrayList();//Added for CR-130
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LogUtil.logMessage(objSession);
		int insertStatus=0;
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			ChangeRequest1058Form objChangeRequest1058Form = (ChangeRequest1058Form) objActionForm;
			ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
			ChangeRequest1058VO objChangeRequest1058VO = new ChangeRequest1058VO();
			objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
			objChangeRequest1058VO.setUserID(objLoginVo.getUserID());
			
			if (objChangeRequest1058Form.getStatusSeqNo() == ApplicationConstants.PENDING_ONE ) {
											
				objChangeRequest1058VO.setOrderNo(objChangeRequest1058Form.getOrderNo());
				objChangeRequest1058VO.setCustName(objChangeRequest1058Form.getCustName());
				objChangeRequest1058VO.setMdlName(objChangeRequest1058Form.getMdlName());
				objChangeRequest1058VO.setOrderQty(objChangeRequest1058Form.getOrderQty());
				objChangeRequest1058VO.setReqTypeSeqNo(objChangeRequest1058Form.getReqTypeSeqNo());
				objChangeRequest1058VO.setCategorySeqNo(objChangeRequest1058Form.getCategorySeqNo());
				objChangeRequest1058VO.setGenDesc(objChangeRequest1058Form.getGenDesc());
				objChangeRequest1058VO.setUnitNumber(objChangeRequest1058Form.getUnitNumber());
				objChangeRequest1058VO.setMcrNumber(objChangeRequest1058Form.getMcrNumber());
				objChangeRequest1058VO.setRoadNumber(objChangeRequest1058Form.getRoadNumber());
				objChangeRequest1058VO.setMcrReq(objChangeRequest1058Form.getMcrReq());			
				objChangeRequest1058VO.setSpecRev(objChangeRequest1058Form.getSpecRev());
				objChangeRequest1058VO.setNonLsdbFlag(objChangeRequest1058Form.getHdnNonLsdbFlag());
				
				int intUpdate1058details  = objChangeRequest1058BI.update1058details(objChangeRequest1058VO);
	
			}
			
			ClauseVO objClauseVO = new ClauseVO();
			
			String strUserID = null;
			String strClauseDesc = null;
			String strComments = null;
			String strReason = null;
			
			int intSuccess = 0;

			String[] clauseTableArray1, clauseTableArray2, clauseTableArray3, clauseTableArray4, clauseTableArray5 = null;
			StandardEquipVO objStandardEquipVO = new StandardEquipVO();
			ClauseTableDataVO objTableDataVO = null;
			ArrayList arlStandardEquipList = new ArrayList();
			ArrayList arlTableList = new ArrayList();
			ArrayList arlTabData = new ArrayList();
			String[] partOf =new String[4];
			int[] partOfSeqNo=new int[4];
			
			String[] clauseSeqNum =new String[4];
			
//			 To Get Table Data1 Values From Form
			clauseTableArray1 = objChangeRequest1058Form.getClauseTableDataArray1Mod();
			if (clauseTableArray1 == null || clauseTableArray1.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData1(null);
			} else {

				for (int counter = 0; counter < clauseTableArray1.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData1(objChangeRequest1058Form.getClauseTableDataArray1Mod());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}

			}
			arlTableList.add(objTableDataVO);
			
//			 To Get Table Data2 Values From Form
			clauseTableArray2 = objChangeRequest1058Form.getClauseTableDataArray2Mod();

			if (clauseTableArray2 == null || clauseTableArray2.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData2(null);
			} else {
				for (int counter = 0; counter < clauseTableArray2.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData2(objChangeRequest1058Form.getClauseTableDataArray2Mod());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}

			}
			arlTableList.add(objTableDataVO);

			// To Get Table Data3 Values From Form
			clauseTableArray3 = objChangeRequest1058Form.getClauseTableDataArray3Mod();

			if (clauseTableArray3 == null || clauseTableArray3.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData3(null);
			} else {
				for (int counter = 0; counter < clauseTableArray3.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData3(objChangeRequest1058Form.getClauseTableDataArray3Mod());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}

			}
			arlTableList.add(objTableDataVO);

			// To Get Table Data4 Values From Form
			clauseTableArray4 = objChangeRequest1058Form.getClauseTableDataArray4Mod();

			if (clauseTableArray4 == null || clauseTableArray4.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData4(null);
			} else {
				for (int counter = 0; counter < clauseTableArray4.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData4(objChangeRequest1058Form.getClauseTableDataArray4Mod());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}

			}
			arlTableList.add(objTableDataVO);

			// To Get Table Data5 Values From Form
			clauseTableArray5 = objChangeRequest1058Form.getClauseTableDataArray5Mod();

			if (clauseTableArray5 == null || clauseTableArray5.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData5(null);
			} else {
				for (int counter = 0; counter < clauseTableArray5.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData5(objChangeRequest1058Form.getClauseTableDataArray5Mod());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
			}
			arlTableList.add(objTableDataVO);
			
			/**
			 * Added for CR-74 To arrange Table Data from coloumn wise to row
			 * wise - Starts Here*
			 */
			if (clauseTableArray1 != null) {
				for (int k = 0; k < clauseTableArray1.length; k++) {
					ArrayList arlDataRows = new ArrayList();
					if (clauseTableArray1 != null
							&& clauseTableArray1.length > k
							&& clauseTableArray1[k] != null) {
						arlDataRows.add(clauseTableArray1[k]);

					} else {
						arlDataRows.add("");
					}

					if (clauseTableArray2 != null
							&& clauseTableArray2.length > k
							&& clauseTableArray2[k] != null) {
						arlDataRows.add(clauseTableArray2[k]);

					} else {
						arlDataRows.add("");
					}

					if (clauseTableArray3 != null
							&& clauseTableArray3.length > k
							&& clauseTableArray3[k] != null) {
						arlDataRows.add(clauseTableArray3[k]);
					} else {
						arlDataRows.add("");
					}
					if (clauseTableArray4 != null
							&& clauseTableArray4.length > k
							&& clauseTableArray4[k] != null) {
						arlDataRows.add(clauseTableArray4[k]);
					} else {
						arlDataRows.add("");
					}
					if (clauseTableArray5 != null
							&& clauseTableArray5.length > k
							&& clauseTableArray5[k] != null) {
						arlDataRows.add(clauseTableArray5[k]);
					} else {
						arlDataRows.add("");
					}
					arlTabData.add(arlDataRows);
				}

			}
			
			
				// Getting User from the session
				if (objLoginVo != null) {
						strUserID = objLoginVo.getUserID();
					}
				
				strClauseDesc = objChangeRequest1058Form.getClauseDescriptionMod();
				LogUtil.logMessage("Clause Description"+strClauseDesc);
				
				strComments = objChangeRequest1058Form.getCommentsMod();
				LogUtil.logMessage("Comments"+strComments);
				
				strReason = objChangeRequest1058Form.getReasonMod();
				LogUtil.logMessage("Reason"+strReason);
				
				if (strClauseDesc != null && !"".equals(strClauseDesc)) {
					strClauseDesc = ApplicationUtil.trim(strClauseDesc);
				}
				if (strComments != null && !"".equals(strComments)) {
					strComments = ApplicationUtil.trim(strComments);
				}
				if (strReason != null && !"".equals(strReason)) {

					strReason = ApplicationUtil.trim(strReason);
				}
				
				if (objChangeRequest1058Form.getStandardEquipmentSeqNoMod() != -1) {
					objStandardEquipVO
							.setStandardEquipmentSeqNo(objChangeRequest1058Form.getStandardEquipmentSeqNoMod());
					arlStandardEquipList.add(objStandardEquipVO);
					LogUtil.logMessage("Standard Equip "+arlStandardEquipList);
					objClauseVO.setObjStandardEquipVO(arlStandardEquipList);
				} else {
					objClauseVO.setObjStandardEquipVO(null);
				}
				
				objClauseVO.setOrderKey(objChangeRequest1058Form.getOrderKey());
				LogUtil.logMessage("Order Key "+objClauseVO.getOrderKey());
				objClauseVO.setDataLocationType(objChangeRequest1058Form.getDataLocType());//Updated for datalocation issue CR-121
				objClauseVO.setModelSeqNo(objChangeRequest1058Form.getMdlSeqNo());
				LogUtil.logMessage("Model SeqNo"+objClauseVO.getModelSeqNo());
				//objClauseVO.setSubSectionSeqNo(objChangeRequest1058Form.getSubSectionSeqNo());
				//LogUtil.logMessage("SubSection Seq No"+objClauseVO.getSubSectionSeqNo());
				//objClauseVO.setParentClauseSeqNo(objChangeRequest1058Form.getHdnParentClauseSeqNo());
				//LogUtil.logMessage("Parent Clause Seq No"+objClauseVO.getParentClauseSeqNo());
				objClauseVO.setClauseDesc(strClauseDesc);
				//objClauseVO.setComponentVO(arlSelCompSeqNo);
				objClauseVO.setEdlNo(objChangeRequest1058Form.getNewEdlNoMod());
				LogUtil.logMessage("EDL NOs"+objClauseVO.getEdlNo());
				objClauseVO.setRefEDLNo(objChangeRequest1058Form.getRefEdlNoMod());
				LogUtil.logMessage("Ref EDL NOs"+objClauseVO.getRefEDLNo());
				//check for part of to only four
				
				
				if(objHttpServletRequest.getParameter("reviseCheck")==null){
				
					
					LogUtil.logMessage("Normal Add Part");
				
				for (int i = 0; i < objChangeRequest1058Form.getPartOf().length-8; i++) {
					LogUtil.logMessage("part of value--->"+i+" "
							+ objChangeRequest1058Form.getPartOf()[i]);
				if(objChangeRequest1058Form.getPartOf()[i]!=null){
					partOf[i]=objChangeRequest1058Form.getPartOf()[i];
				}
				
				}
				//check for partof seq no to only four
				
				
				for (int i = 0; i < objChangeRequest1058Form.getPartOfSeqNo().length-8; i++) {
					LogUtil.logMessage("part of seq	no value--->"+i+" "
							+ objChangeRequest1058Form.getPartOfSeqNo()[i]);
					if(objChangeRequest1058Form.getPartOfSeqNo()[i]!=0){
					partOfSeqNo[i]=objChangeRequest1058Form.getPartOfSeqNo()[i];}
				}
				
				for (int i = 0; i < objChangeRequest1058Form.getClauseSeqNum().length-8; i++) {
					LogUtil.logMessage("clause no value--->"+i+" "
							+ objChangeRequest1058Form.getClauseSeqNum()[i]);
					if(objChangeRequest1058Form.getClauseSeqNum()[i]!=null){
						clauseSeqNum[i]=objChangeRequest1058Form.getClauseSeqNum()[i];}
				}
				
				}
				else{
					LogUtil.logMessage("Revise Part");
					LogUtil.logMessage("Clause Change Request SeqNo "+objChangeRequest1058Form.getClauseChangeRequestSeqNo());
					objClauseVO.setClauseChangeReqSeqNo(objChangeRequest1058Form.getClauseChangeRequestSeqNo());
					
					for (int i = 0; i < objChangeRequest1058Form.getPartOf().length; i++) {
						LogUtil.logMessage("part of value--->"+i+" "
								+ objChangeRequest1058Form.getPartOf()[i]);
					if(objChangeRequest1058Form.getPartOf()[i]!=null){
						partOf[i]=objChangeRequest1058Form.getPartOf()[i];
					}
					
					}
					//check for partof seq no to only four
					for (int i = 0; i < objChangeRequest1058Form.getPartOfSeqNo().length; i++) {
						LogUtil.logMessage("part of seq	no value--->"+i+" "
								+ objChangeRequest1058Form.getPartOfSeqNo()[i]);
						if(objChangeRequest1058Form.getPartOfSeqNo()[i]!=0){
						partOfSeqNo[i]=objChangeRequest1058Form.getPartOfSeqNo()[i];}
					}
					
					for (int i = 0; i < objChangeRequest1058Form.getClauseSeqNum().length; i++) {
						LogUtil.logMessage("clause no value--->"+i+" "
								+ objChangeRequest1058Form.getClauseSeqNum()[i]);
						if(objChangeRequest1058Form.getClauseSeqNum()[i]!=null){
							clauseSeqNum[i]=objChangeRequest1058Form.getClauseSeqNum()[i];}
					}
					
				}
					
				
//				check for part of to only four
				if (objChangeRequest1058Form.getPartOfSeqNo() != null) {
					if (objChangeRequest1058Form.getPartOfSeqNo().length != 0) {
						
						objClauseVO.setPartOfSeqNo(partOfSeqNo);
					}

				}
				
//				check for partof seq no to only four
				if (objChangeRequest1058Form.getPartOf().length != 0) {
					objClauseVO.setClauseNoArray(partOf);
					
				}
				
				if (objChangeRequest1058Form.getClauseSeqNum().length != 0) {
					objClauseVO.setClauseSeqNoArray(clauseSeqNum);
				}
				
				if (objChangeRequest1058Form.getDwoNoMod() != null
						&& !objChangeRequest1058Form.getDwoNoMod().equals("")) {
					String strDwoNo = ApplicationUtil.trim(objChangeRequest1058Form
							.getDwoNoMod());
					LogUtil.logMessage("DWO Number"+strDwoNo);
					objClauseVO.setDwONumber(Integer.parseInt(strDwoNo));
				}
				
				if (objChangeRequest1058Form.getPartNoMod() != null
						&& !objChangeRequest1058Form.getPartNoMod().equals("")) {
					String strPartNo = objChangeRequest1058Form.getPartNoMod();
					LogUtil.logMessage("Part Number"+strPartNo);
					objClauseVO.setPartNumber(Integer.parseInt(strPartNo));
				}

				if (objChangeRequest1058Form.getPriceBookNoMod() != null
						&& !objChangeRequest1058Form.getPriceBookNoMod().equals("")) {
					String strPriceNo = objChangeRequest1058Form.getPriceBookNoMod();
					LogUtil.logMessage("Price book Number"+strPriceNo);
					objClauseVO.setPriceBookNumber(Integer.parseInt(strPriceNo));
				}
				
				objClauseVO.setComments(strComments);
				objClauseVO.setReason(strReason);

				objClauseVO.setTableDataVO(arlTableList);

				objClauseVO.setUserID(strUserID);
				objClauseVO.setTableArrayData1(arlTabData);
				
				objClauseVO.setSectionSeqNo(objChangeRequest1058Form.getSectionSeqNo());
				LogUtil.logMessage("SectionSeq NO"+objClauseVO.getSectionSeqNo());
				objClauseVO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
				LogUtil.logMessage("seq no 1058 "+objClauseVO.getSeqNo1058());
				objClauseVO.setClauseChangeType(objChangeRequest1058Form.getClauseChangeType());
				LogUtil.logMessage("Clause Change Type"+objClauseVO.getClauseChangeType());
				
				LogUtil.logMessage("Clause to modify Number objChangeRequest1058Form.getHdnClauseToModify() "+objChangeRequest1058Form.getHdnClauseToModify());
				objClauseVO.setChangeFromClaNo(objChangeRequest1058Form.getHdnClauseToModify());
				LogUtil.logMessage("Clause to modify Number"+objClauseVO.getChangeFromClaNo());
				objClauseVO.setChangeFromClaSeqNo(objChangeRequest1058Form.getHdnClauseToModifySeqNo());
				
				if (objChangeRequest1058Form.getHdnVersionNoMod() != 0){
					LogUtil.logMessage("Clause Version Number is"+objChangeRequest1058Form.getHdnVersionNoMod());
					objClauseVO.setChangeToClaVerNo(objChangeRequest1058Form.getHdnVersionNoMod());
				}

				
					if(objHttpServletRequest.getParameter("reviseCheck")!=null){
					insertStatus=objChangeRequest1058BI.updateClause(objClauseVO);
					}
					else{
					insertStatus =objChangeRequest1058BI.saveClause(objClauseVO);
					}
					LogUtil.logMessage("Insert/Update Status "+insertStatus);
					
					if(insertStatus>0){
						objChangeRequest1058Form.setMessageID(String.valueOf(insertStatus));
					}else {
						objChangeRequest1058Form.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
					}
			
			arlRequest1058List = objChangeRequest1058BI.fetch1058Details(objChangeRequest1058VO);
			if (arlRequest1058List != null) {
				LogUtil.logMessage(arlRequest1058List);
				objChangeRequest1058Form.setRequestDetailsList(arlRequest1058List);
			}
			objChangeRequest1058Form.setReviseClauseCheck(0);
			objChangeRequest1058Form.setCheckCompChangeClauses(0);
			objChangeRequest1058Form.setCheckVersionClause(0);
			
			LogUtil.logMessage("Requests in ChangeRequest1058Action:"
					+ objChangeRequest1058Form.getRequestDetailsList().size());
			
			//Request 1058 Categories
			arlCategories = objChangeRequest1058BI.fetch1058RequestCategories(objLoginVo);

			if (arlCategories != null) {
				LogUtil.logMessage("Request Categories "+arlCategories);
				objChangeRequest1058Form.setCategories(arlCategories);
			}

			//Request 1058 Request Types
			arlRequestTypes = objChangeRequest1058BI.fetch1058RequestTypes(objLoginVo);
			
			if (arlRequestTypes != null) {
				LogUtil.logMessage("Request Types "+arlRequestTypes);
				objChangeRequest1058Form.setReqTypes(arlRequestTypes);
			}
			
			//Request LSDB Clause Change Types			
			arlClauseChangeTypes = objChangeRequest1058BI.fetch1058ClauseChangeTypes(objLoginVo);
			
			if (arlClauseChangeTypes != null) {
				LogUtil.logMessage("Clause Change Types "+arlClauseChangeTypes);
				objChangeRequest1058Form.setClauseChangeTypes(arlClauseChangeTypes);
			}
			
			objChangeRequest1058VO.setClaChngReqSeq(0);
			
			//Request LSDB Clause Changes Details
			if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.NO)){
				arlReqSpecChngClauses=objChangeRequest1058BI.fetchLsdbClauseDetails(objChangeRequest1058VO);
				if (arlReqSpecChngClauses != null) {
					LogUtil.logMessage("Requested Specification Change "+arlReqSpecChngClauses);
					objChangeRequest1058Form.setReqSpecChngClauseList(arlReqSpecChngClauses);
				}
			}

			//Request Non LSDB Clause Changes Details
			if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.YES)){
				
				ArrayList arlClauseList = objChangeRequest1058BI
						.selectClauseNonLsdb(objChangeRequest1058VO);
				objChangeRequest1058Form.setClaList(arlClauseList);
				LogUtil.logMessage("arlClauseList=" + arlClauseList.size());
			}
			
			//RepresentativeList Details
			//Added For CR-120
			UserVO objUserVO = new UserVO();
			objUserVO.setUserID(objLoginVo.getUserID());
			objUserVO.setOrderbyFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
			arlRepresentativeList = objChangeRequest1058BI.fetchRepresentativeDetails(objUserVO);
				
			if (arlRepresentativeList != null) {
				LogUtil.logMessage(arlRepresentativeList);
				objChangeRequest1058Form.setRepresentativeList(arlRepresentativeList);
			}
			LogUtil.logMessage("ArrayList Value in ChangeRequest1058Action:"
					+ objChangeRequest1058Form.getRepresentativeList().size());
			
			//Added for CR-127 starts here
			//Model Clause Changes Details
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			
			arlMdlClaChangesList = objChangeRequest1058BI.fetch1058MdlClaChanges(objChangeRequest1058VO);
			if (arlMdlClaChangesList != null) {
				objChangeRequest1058Form.setMdlClaChangesList(arlMdlClaChangesList);
			}
			LogUtil.logMessage("model clause change list" +arlMdlClaChangesList.size());
			for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
			}
			//Added for CR-127 ends here
			//Added for CR-130 starts here
			//SubSection Details
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			arlImportSubSecList = objChangeRequest1058BI.fetchSubSectionList(objChangeRequest1058VO);
				if (arlImportSubSecList != null) {
					
					objChangeRequest1058Form.setImportSubSecList(arlImportSubSecList);
					objChangeRequest1058Form.setImportSubSecListSize(arlImportSubSecList.size());
				}
			LogUtil.logMessage("Import Sub Section List" +arlImportSubSecList.size());
			
			for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
			}
			
			//1058 SubSection Details
			objChangeRequest1058VO.setSubSecChngReqSeq(0);
			
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			arl1058SubSecList = objChangeRequest1058BI.fetch1058SubSectionList(objChangeRequest1058VO);
				if (arl1058SubSecList != null) {
					
					objChangeRequest1058Form.setSubSec1058List(arl1058SubSecList);
				}
			LogUtil.logMessage("Import Sub Section List" +arl1058SubSecList.size());
			
			for (int cnt = 0; cnt < arl1058SubSecList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arl1058SubSecList.get(cnt);
			}
			//Added for CR-130 ends here
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ChangeRequest1058Action..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	
	
	public ActionForward saveDeleteClause(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into ChangeRequest1058Action:saveDeleteClause");
		String strForwardKey = ApplicationConstants.CREATE;
		ArrayList arlRequest1058List=new ArrayList();
		ArrayList arlCategories =new ArrayList();
		ArrayList arlRequestTypes =new ArrayList();
		ArrayList arlClauseChangeTypes  =new ArrayList();
		ArrayList arlReqSpecChngClauses=new ArrayList();
		ArrayList arlRepresentativeList = new ArrayList();
		ArrayList arlMdlClaChangesList = new ArrayList();//Added for CR-127
		ArrayList arlImportSubSecList   = new ArrayList();//Added for CR-130
		ArrayList arl1058SubSecList   = new ArrayList();//Added for CR-130
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LogUtil.logMessage(objSession);
		int insertStatus=0;
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			ChangeRequest1058Form objChangeRequest1058Form = (ChangeRequest1058Form) objActionForm;
			ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
			ChangeRequest1058VO objChangeRequest1058VO =new ChangeRequest1058VO();
			objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
			objChangeRequest1058VO.setUserID(objLoginVo.getUserID());
			
			if (objChangeRequest1058Form.getStatusSeqNo() == ApplicationConstants.PENDING_ONE ) {
											
				objChangeRequest1058VO.setOrderNo(objChangeRequest1058Form.getOrderNo());
				objChangeRequest1058VO.setCustName(objChangeRequest1058Form.getCustName());
				objChangeRequest1058VO.setMdlName(objChangeRequest1058Form.getMdlName());
				objChangeRequest1058VO.setOrderQty(objChangeRequest1058Form.getOrderQty());
				objChangeRequest1058VO.setReqTypeSeqNo(objChangeRequest1058Form.getReqTypeSeqNo());
				objChangeRequest1058VO.setCategorySeqNo(objChangeRequest1058Form.getCategorySeqNo());
				objChangeRequest1058VO.setGenDesc(objChangeRequest1058Form.getGenDesc());
				objChangeRequest1058VO.setUnitNumber(objChangeRequest1058Form.getUnitNumber());
				objChangeRequest1058VO.setMcrNumber(objChangeRequest1058Form.getMcrNumber());
				objChangeRequest1058VO.setRoadNumber(objChangeRequest1058Form.getRoadNumber());
				objChangeRequest1058VO.setMcrReq(objChangeRequest1058Form.getMcrReq());			
				objChangeRequest1058VO.setSpecRev(objChangeRequest1058Form.getSpecRev());
				objChangeRequest1058VO.setNonLsdbFlag(objChangeRequest1058Form.getHdnNonLsdbFlag());
				
				int intUpdate1058details  = objChangeRequest1058BI.update1058details(objChangeRequest1058VO);
	
			}
			
			ClauseVO objClauseVO = new ClauseVO();
			
			String strUserID = null;
			String strReason = null;
			
				// Getting User from the session
				if (objLoginVo != null) {
						strUserID = objLoginVo.getUserID();
					}
				strReason = objChangeRequest1058Form.getReasonDel();
				LogUtil.logMessage("Reason"+strReason);
				
				
				if (strReason != null && !"".equals(strReason)) {

					strReason = ApplicationUtil.trim(strReason);
				}
				
				if(objHttpServletRequest.getParameter("reviseCheck")!=null){
					LogUtil.logMessage("Revise Part");
					LogUtil.logMessage("Clause Change Request SeqNo "+objChangeRequest1058Form.getClauseChangeRequestSeqNo());
					objClauseVO.setClauseChangeReqSeqNo(objChangeRequest1058Form.getClauseChangeRequestSeqNo());
					
				}
				objClauseVO.setOrderKey(objChangeRequest1058Form.getOrderKey());
				LogUtil.logMessage("Order Key "+objClauseVO.getOrderKey());
				objClauseVO.setDataLocationType(objChangeRequest1058Form.getDataLocType());//Updated for datalocation issue CR-121
				objClauseVO.setModelSeqNo(objChangeRequest1058Form.getMdlSeqNo());
				LogUtil.logMessage("Model SeqNo"+objClauseVO.getModelSeqNo());
				//check for part of to only four
				objClauseVO.setReason(strReason);
				objClauseVO.setUserID(strUserID);
				
				objClauseVO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
				LogUtil.logMessage("seq no 1058 "+objClauseVO.getSeqNo1058());
				objClauseVO.setClauseChangeType(objChangeRequest1058Form.getClauseChangeType());
				LogUtil.logMessage("Clause Change Type"+objClauseVO.getClauseChangeType());
				
				LogUtil.logMessage("Clause to Delete Number objChangeRequest1058Form.getHdnClauseToDelete() "+objChangeRequest1058Form.getHdnClauseToDelete());
				objClauseVO.setChangeFromClaNo(objChangeRequest1058Form.getHdnClauseToDelete());
				LogUtil.logMessage("Clause to Delete Number"+objClauseVO.getChangeFromClaNo());
				objClauseVO.setChangeFromClaSeqNo(objChangeRequest1058Form.getHdnClauseToDeleteSeqNo());
				LogUtil.logMessage("Clause to Delete Seq Number "+objClauseVO.getChangeFromClaSeqNo());
				
				if(objHttpServletRequest.getParameter("reviseCheck")!=null){
					insertStatus=objChangeRequest1058BI.updateDeleteClause(objClauseVO);
				}
				else{
				insertStatus =objChangeRequest1058BI.saveDeleteClause(objClauseVO);
				}
				LogUtil.logMessage("Insert Status "+insertStatus);
				if(insertStatus>0){
					objChangeRequest1058Form.setMessageID(String.valueOf(insertStatus));
				}else {
					objChangeRequest1058Form.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				}
			
			arlRequest1058List = objChangeRequest1058BI.fetch1058Details(objChangeRequest1058VO);
			if (arlRequest1058List != null) {
				LogUtil.logMessage(arlRequest1058List);
				objChangeRequest1058Form.setRequestDetailsList(arlRequest1058List);
			}
			
			
			objChangeRequest1058Form.setReviseClauseCheck(0);
			objChangeRequest1058Form.setCheckCompChangeClauses(0);
			objChangeRequest1058Form.setCheckVersionClause(0);
			
			LogUtil.logMessage("Requests in ChangeRequest1058Action:"
					+ objChangeRequest1058Form.getRequestDetailsList().size());
			
			//Request 1058 Categories
			arlCategories = objChangeRequest1058BI.fetch1058RequestCategories(objLoginVo);

			if (arlCategories != null) {
				LogUtil.logMessage("Request Categories "+arlCategories);
				objChangeRequest1058Form.setCategories(arlCategories);
			}

			//Request 1058 Request Types
			arlRequestTypes = objChangeRequest1058BI.fetch1058RequestTypes(objLoginVo);
			
			if (arlRequestTypes != null) {
				LogUtil.logMessage("Request Types "+arlRequestTypes);
				objChangeRequest1058Form.setReqTypes(arlRequestTypes);
			}
			
			//Request LSDB Clause Change Types			
			arlClauseChangeTypes = objChangeRequest1058BI.fetch1058ClauseChangeTypes(objLoginVo);
			
			if (arlClauseChangeTypes != null) {
				LogUtil.logMessage("Clause Change Types "+arlClauseChangeTypes);
				objChangeRequest1058Form.setClauseChangeTypes(arlClauseChangeTypes);
			}
			
			objChangeRequest1058VO.setClaChngReqSeq(0);
			
			//Request LSDB Clause Changes Details
			if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.NO)){
				arlReqSpecChngClauses=objChangeRequest1058BI.fetchLsdbClauseDetails(objChangeRequest1058VO);
				if (arlReqSpecChngClauses != null) {
					LogUtil.logMessage("Requested Specification Change "+arlReqSpecChngClauses);
					objChangeRequest1058Form.setReqSpecChngClauseList(arlReqSpecChngClauses);
				}
			}

			//Request Non LSDB Clause Changes Details
			if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.YES)){
				
				ArrayList arlClauseList = objChangeRequest1058BI
						.selectClauseNonLsdb(objChangeRequest1058VO);
				objChangeRequest1058Form.setClaList(arlClauseList);
				LogUtil.logMessage("arlClauseList=" + arlClauseList.size());
			}
			
			//RepresentativeList Details
			//Added For CR-120
			UserVO objUserVO = new UserVO();
			objUserVO.setUserID(objLoginVo.getUserID());
			objUserVO.setOrderbyFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
			arlRepresentativeList = objChangeRequest1058BI.fetchRepresentativeDetails(objUserVO);
				
			if (arlRepresentativeList != null) {
				LogUtil.logMessage(arlRepresentativeList);
				objChangeRequest1058Form.setRepresentativeList(arlRepresentativeList);
			}
			LogUtil.logMessage("ArrayList Value in ChangeRequest1058Action:"
					+ objChangeRequest1058Form.getRepresentativeList().size());

			//Added for CR-127 starts here
			//Model Clause Changes Details
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			
			arlMdlClaChangesList = objChangeRequest1058BI.fetch1058MdlClaChanges(objChangeRequest1058VO);
			if (arlMdlClaChangesList != null) {
				objChangeRequest1058Form.setMdlClaChangesList(arlMdlClaChangesList);
			}
			LogUtil.logMessage("model clause change list" +arlMdlClaChangesList.size());
			for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
			}
			//Added for CR-127 ends here
			//Added for CR-130 starts here
			//SubSection Details
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			arlImportSubSecList = objChangeRequest1058BI.fetchSubSectionList(objChangeRequest1058VO);
				if (arlImportSubSecList != null) {
					
					objChangeRequest1058Form.setImportSubSecList(arlImportSubSecList);
					objChangeRequest1058Form.setImportSubSecListSize(arlImportSubSecList.size());
				}
			LogUtil.logMessage("Import Sub Section List" +arlImportSubSecList.size());
			
			for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
			}
			
			//1058 SubSection Details
			objChangeRequest1058VO.setSubSecChngReqSeq(0);
			
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			arl1058SubSecList = objChangeRequest1058BI.fetch1058SubSectionList(objChangeRequest1058VO);
				if (arl1058SubSecList != null) {
					
					objChangeRequest1058Form.setSubSec1058List(arl1058SubSecList);
				}
			LogUtil.logMessage("Import Sub Section List" +arl1058SubSecList.size());
			
			for (int cnt = 0; cnt < arl1058SubSecList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arl1058SubSecList.get(cnt);
			}
			//Added for CR-130 ends here
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ChangeRequest1058Action..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}

	
	
	public ActionForward saveChangeComponent(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into ChangeRequest1058Action:saveChangeComponent");
		String strForwardKey = ApplicationConstants.CREATE;
		ArrayList arlRequest1058List=new ArrayList();
		ArrayList arlCategories =new ArrayList();
		ArrayList arlRequestTypes =new ArrayList();
		ArrayList arlClauseChangeTypes  =new ArrayList();
		ArrayList arlReqSpecChngClauses=new ArrayList();
		ArrayList arlRepresentativeList = new ArrayList();
		ArrayList arlMdlClaChangesList = new ArrayList();//Added for CR-127
		ArrayList arlImportSubSecList   = new ArrayList();//Added for CR-130
		ArrayList arl1058SubSecList   = new ArrayList();//Added for CR-130
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LogUtil.logMessage(objSession);
		int insertStatus=0;
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			ChangeRequest1058Form objChangeRequest1058Form = (ChangeRequest1058Form) objActionForm;
			ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
			ChangeRequest1058VO objChangeRequest1058VO =new ChangeRequest1058VO();
			objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
			objChangeRequest1058VO.setUserID(objLoginVo.getUserID());
			
			if (objChangeRequest1058Form.getStatusSeqNo() == ApplicationConstants.PENDING_ONE ) {
											
				objChangeRequest1058VO.setOrderNo(objChangeRequest1058Form.getOrderNo());
				objChangeRequest1058VO.setCustName(objChangeRequest1058Form.getCustName());
				objChangeRequest1058VO.setMdlName(objChangeRequest1058Form.getMdlName());
				objChangeRequest1058VO.setOrderQty(objChangeRequest1058Form.getOrderQty());
				objChangeRequest1058VO.setReqTypeSeqNo(objChangeRequest1058Form.getReqTypeSeqNo());
				objChangeRequest1058VO.setCategorySeqNo(objChangeRequest1058Form.getCategorySeqNo());
				objChangeRequest1058VO.setGenDesc(objChangeRequest1058Form.getGenDesc());
				objChangeRequest1058VO.setUnitNumber(objChangeRequest1058Form.getUnitNumber());
				objChangeRequest1058VO.setMcrNumber(objChangeRequest1058Form.getMcrNumber());
				objChangeRequest1058VO.setRoadNumber(objChangeRequest1058Form.getRoadNumber());
				objChangeRequest1058VO.setMcrReq(objChangeRequest1058Form.getMcrReq());			
				objChangeRequest1058VO.setSpecRev(objChangeRequest1058Form.getSpecRev());
				objChangeRequest1058VO.setNonLsdbFlag(objChangeRequest1058Form.getHdnNonLsdbFlag());
				
				int intUpdate1058details  = objChangeRequest1058BI.update1058details(objChangeRequest1058VO);
	
			}
			
			ClauseVO objClauseVO = new ClauseVO();
			
			String strUserID = null;
			String strClauseDesc = null;
			String strComments = null;
			String strReason = null;
			
			int intSuccess = 0;

			String[] clauseTableArray1, clauseTableArray2, clauseTableArray3, clauseTableArray4, clauseTableArray5 = null;
			StandardEquipVO objStandardEquipVO = new StandardEquipVO();
			ClauseTableDataVO objTableDataVO = null;
			ArrayList arlStandardEquipList = new ArrayList();
			ArrayList arlTableList = new ArrayList();
			ArrayList arlSelCompSeqNo = new ArrayList();
			// Added for CR-74 VV49326 04-06-09
			ArrayList arlCompName = new ArrayList();
			ArrayList arlTabData = new ArrayList();
			String[] partOf =new String[4];
			int[] partOfSeqNo=new int[4];
			
			String[] clauseSeqNum =new String[4];
			
			/*if ((objChangeRequest1058Form.getComponentSeqNo() != null)) {
				LogUtil.logMessage("Component Seq NO"+objChangeRequest1058Form.getComponentSeqNo());
				stCompSeqNo = new StringTokenizer(objChangeRequest1058Form
						.getComponentSeqNo(), "~");

				while (stCompSeqNo.hasMoreTokens()) {
					arlSelCompSeqNo.add(stCompSeqNo.nextToken());
				}
				//Check Here 
				
				
				// Added for CR-74 VV49326 04-06-09
				
				if (objChangeRequest1058Form.getComponentSeqNo() != null
						&& !(objChangeRequest1058Form.getComponentSeqNo()
								.equals(""))) {
					stCompName = objChangeRequest1058Form.getHdnComponentName()
							.split("~");
					for (int i = 0; i < stCompName.length; i++) {
						ComponentVO objCompVo = new ComponentVO();
						objCompVo.setComponentSeqNo(Integer
								.parseInt((String) arlSelCompSeqNo.get(i)));
						objCompVo.setComponentDescription(stCompName[i]);
						arlCompName.add(objCompVo);

					}

				}
				
				

			} */
			
			if(objChangeRequest1058Form.getHdnLeadComponentSeqNoinAdd()!=0){
				LogUtil.logMessage("LeadComponent Seq NO   :"
						+ objChangeRequest1058Form.getHdnLeadComponentSeqNoinAdd());
				arlSelCompSeqNo.add(""+objChangeRequest1058Form.getHdnLeadComponentSeqNoinAdd());
			}
			if(objChangeRequest1058Form.getHdnLeadComponentNameinAdd()!=null){
				LogUtil.logMessage("LeadComponent Name NO   :"
						+ objChangeRequest1058Form.getHdnLeadComponentNameinAdd());
				arlCompName.add(objChangeRequest1058Form.getHdnLeadComponentNameinAdd());
			}
			LogUtil.logMessage("Component names"+arlCompName);
			objChangeRequest1058Form.setComponentVO(arlCompName);
			LogUtil.logMessage("arlSelCompSeqNo size :"
					+ arlSelCompSeqNo.size());
			
			
			if (objChangeRequest1058Form.getComponentGrpSeqNoinAdd() != -1) {
				LogUtil.logMessage("Lead Component group Seq number"+objChangeRequest1058Form.getComponentGrpSeqNoinAdd());
				objClauseVO.setLeadCompGrpSeqNo(objChangeRequest1058Form.getComponentGrpSeqNoinAdd());
			}
			
			/** Added for CR-68 Order New Component*
			objClauseVO.setOrderCompName(objAddClauseOrderForm
					.getValidCompName()); */
			
//			 To Get Table Data1 Values From Form
			clauseTableArray1 = objChangeRequest1058Form.getClauseTableDataArray1ChngComp();
			if (clauseTableArray1 == null || clauseTableArray1.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData1(null);
			} else {

				for (int counter = 0; counter < clauseTableArray1.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData1(objChangeRequest1058Form.getClauseTableDataArray1ChngComp());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}

			}
			arlTableList.add(objTableDataVO);
			
//			 To Get Table Data2 Values From Form
			clauseTableArray2 = objChangeRequest1058Form.getClauseTableDataArray2ChngComp();

			if (clauseTableArray2 == null || clauseTableArray2.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData2(null);
			} else {
				for (int counter = 0; counter < clauseTableArray2.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData2(objChangeRequest1058Form.getClauseTableDataArray2ChngComp());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}

			}
			arlTableList.add(objTableDataVO);

			// To Get Table Data3 Values From Form
			clauseTableArray3 = objChangeRequest1058Form.getClauseTableDataArray3ChngComp();

			if (clauseTableArray3 == null || clauseTableArray3.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData3(null);
			} else {
				for (int counter = 0; counter < clauseTableArray3.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData3(objChangeRequest1058Form.getClauseTableDataArray3ChngComp());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}

			}
			arlTableList.add(objTableDataVO);

			// To Get Table Data4 Values From Form
			clauseTableArray4 = objChangeRequest1058Form.getClauseTableDataArray4ChngComp();

			if (clauseTableArray4 == null || clauseTableArray4.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData4(null);
			} else {
				for (int counter = 0; counter < clauseTableArray4.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData4(objChangeRequest1058Form.getClauseTableDataArray4ChngComp());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}

			}
			arlTableList.add(objTableDataVO);

			// To Get Table Data5 Values From Form
			clauseTableArray5 = objChangeRequest1058Form.getClauseTableDataArray5ChngComp();

			if (clauseTableArray5 == null || clauseTableArray5.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData5(null);
			} else {
				for (int counter = 0; counter < clauseTableArray5.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData5(objChangeRequest1058Form.getClauseTableDataArray5ChngComp());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
			}
			arlTableList.add(objTableDataVO);
			
			/**
			 * Added for CR-74 To arrange Table Data from coloumn wise to row
			 * wise - Starts Here*
			 */
			if (clauseTableArray1 != null) {
				for (int k = 0; k < clauseTableArray1.length; k++) {
					ArrayList arlDataRows = new ArrayList();
					if (clauseTableArray1 != null
							&& clauseTableArray1.length > k
							&& clauseTableArray1[k] != null) {
						arlDataRows.add(clauseTableArray1[k]);

					} else {
						arlDataRows.add("");
					}

					if (clauseTableArray2 != null
							&& clauseTableArray2.length > k
							&& clauseTableArray2[k] != null) {
						arlDataRows.add(clauseTableArray2[k]);

					} else {
						arlDataRows.add("");
					}

					if (clauseTableArray3 != null
							&& clauseTableArray3.length > k
							&& clauseTableArray3[k] != null) {
						arlDataRows.add(clauseTableArray3[k]);
					} else {
						arlDataRows.add("");
					}
					if (clauseTableArray4 != null
							&& clauseTableArray4.length > k
							&& clauseTableArray4[k] != null) {
						arlDataRows.add(clauseTableArray4[k]);
					} else {
						arlDataRows.add("");
					}
					if (clauseTableArray5 != null
							&& clauseTableArray5.length > k
							&& clauseTableArray5[k] != null) {
						arlDataRows.add(clauseTableArray5[k]);
					} else {
						arlDataRows.add("");
					}
					arlTabData.add(arlDataRows);
				}

			}
			
			
				// Getting User from the session
				if (objLoginVo != null) {
						strUserID = objLoginVo.getUserID();
					}
				
				strClauseDesc = objChangeRequest1058Form.getClauseDescriptionChngComp();
				LogUtil.logMessage("Clause Description"+strClauseDesc);
				
				strComments = objChangeRequest1058Form.getCommentsChngComp();
				LogUtil.logMessage("Comments"+strComments);
				
				strReason = objChangeRequest1058Form.getReasonChngComp();
				LogUtil.logMessage("Reason"+strReason);
				
				if (strClauseDesc != null && !"".equals(strClauseDesc)) {
					strClauseDesc = ApplicationUtil.trim(strClauseDesc);
				}
				if (strComments != null && !"".equals(strComments)) {
					strComments = ApplicationUtil.trim(strComments);
				}
				if (strReason != null && !"".equals(strReason)) {

					strReason = ApplicationUtil.trim(strReason);
				}
				
				if (objChangeRequest1058Form.getStandardEquipmentSeqNoChngComp() != -1) {
					objStandardEquipVO
							.setStandardEquipmentSeqNo(objChangeRequest1058Form.getStandardEquipmentSeqNoChngComp());
					arlStandardEquipList.add(objStandardEquipVO);
					LogUtil.logMessage("Standard Equip "+arlStandardEquipList);
					objClauseVO.setObjStandardEquipVO(arlStandardEquipList);
				} else {
					objClauseVO.setObjStandardEquipVO(null);
				}
				
				objClauseVO.setOrderKey(objChangeRequest1058Form.getOrderKey());
				LogUtil.logMessage("Order Key "+objClauseVO.getOrderKey());
				objClauseVO.setDataLocationType(objChangeRequest1058Form.getDataLocType());//Updated for datalocation issue CR-121
				objClauseVO.setModelSeqNo(objChangeRequest1058Form.getMdlSeqNo());
				LogUtil.logMessage("Model SeqNo"+objClauseVO.getModelSeqNo());
				objClauseVO.setSubSectionSeqNo(objChangeRequest1058Form.getSubSectionSeqNo());
				LogUtil.logMessage("SubSection Seq No"+objClauseVO.getSubSectionSeqNo());
				//objClauseVO.setParentClauseSeqNo(objChangeRequest1058Form.getHdnParentClauseSeqNo());
				//LogUtil.logMessage("Parent Clause Seq No"+objClauseVO.getParentClauseSeqNo());
				objClauseVO.setClauseDesc(strClauseDesc);
				objClauseVO.setComponentVO(arlSelCompSeqNo);
				objClauseVO.setEdlNo(objChangeRequest1058Form.getNewEdlNoChngComp());
				LogUtil.logMessage("EDL NOs"+objClauseVO.getEdlNo());
				objClauseVO.setRefEDLNo(objChangeRequest1058Form.getRefEdlNoChngComp());
				LogUtil.logMessage("Ref EDL NOs"+objClauseVO.getRefEDLNo());
				
				
				if(objHttpServletRequest.getParameter("reviseCheck")==null){
					LogUtil.logMessage("Normal Part");
				//check for part of to only four
				for (int i = 8; i < objChangeRequest1058Form.getPartOf().length; i++) {
					LogUtil.logMessage("part of value--->"+i+" "
							+ objChangeRequest1058Form.getPartOf()[i]);
				if(objChangeRequest1058Form.getPartOf()[i]!=null){
					partOf[i-8]=objChangeRequest1058Form.getPartOf()[i];
				}
				
				}
				//check for partof seq no to only four
				for (int i = 8; i < objChangeRequest1058Form.getPartOfSeqNo().length; i++) {
					LogUtil.logMessage("part of seq	no value--->"+i+" "
							+ objChangeRequest1058Form.getPartOfSeqNo()[i]);
					if(objChangeRequest1058Form.getPartOfSeqNo()[i]!=0){
					partOfSeqNo[i-8]=objChangeRequest1058Form.getPartOfSeqNo()[i];}
				}
				
				for (int i = 8; i < objChangeRequest1058Form.getClauseSeqNum().length; i++) {
					LogUtil.logMessage("clause no value--->"+i+" "
							+ objChangeRequest1058Form.getClauseSeqNum()[i]);
					if(objChangeRequest1058Form.getClauseSeqNum()[i]!=null){
						clauseSeqNum[i-8]=objChangeRequest1058Form.getClauseSeqNum()[i];}
				}
				
				//Clause Seq number left on purpose from previous code
				

				
				}
				else{
					LogUtil.logMessage("Revise Part");
					LogUtil.logMessage("Clause Change Request SeqNo "+objChangeRequest1058Form.getClauseChangeRequestSeqNo());
					objClauseVO.setClauseChangeReqSeqNo(objChangeRequest1058Form.getClauseChangeRequestSeqNo());
					
					for (int i = 0; i < objChangeRequest1058Form.getPartOf().length; i++) {
						LogUtil.logMessage("part of value--->"+i+" "
								+ objChangeRequest1058Form.getPartOf()[i]);
					if(objChangeRequest1058Form.getPartOf()[i]!=null){
						partOf[i]=objChangeRequest1058Form.getPartOf()[i];
					}
					
					}
					//check for partof seq no to only four
					for (int i = 0; i < objChangeRequest1058Form.getPartOfSeqNo().length; i++) {
						LogUtil.logMessage("part of seq	no value--->"+i+" "
								+ objChangeRequest1058Form.getPartOfSeqNo()[i]);
						if(objChangeRequest1058Form.getPartOfSeqNo()[i]!=0){
						partOfSeqNo[i]=objChangeRequest1058Form.getPartOfSeqNo()[i];}
					}
					
					for (int i = 0; i < objChangeRequest1058Form.getClauseSeqNum().length; i++) {
						LogUtil.logMessage("clause no value--->"+i+" "
								+ objChangeRequest1058Form.getClauseSeqNum()[i]);
						if(objChangeRequest1058Form.getClauseSeqNum()[i]!=null){
							clauseSeqNum[i]=objChangeRequest1058Form.getClauseSeqNum()[i];}
					}
					
					
				}
				
				
//				check for part of to only four
				if (objChangeRequest1058Form.getPartOfSeqNo() != null) {
					if (objChangeRequest1058Form.getPartOfSeqNo().length != 0) {
						
						objClauseVO.setPartOfSeqNo(partOfSeqNo);
					}

				}
				
//				check for partof seq no to only four
				if (objChangeRequest1058Form.getPartOf().length != 0) {
					objClauseVO.setClauseNoArray(partOf);
					
				}
				
				if (objChangeRequest1058Form.getClauseSeqNum().length != 0) {
					objClauseVO.setClauseSeqNoArray(clauseSeqNum);
				}
				
				if (objChangeRequest1058Form.getDwoNoChngComp() != null
						&& !objChangeRequest1058Form.getDwoNoChngComp().equals("")) {
					String strDwoNo = ApplicationUtil.trim(objChangeRequest1058Form
							.getDwoNoChngComp());
					LogUtil.logMessage("DWO Number"+strDwoNo);
					objClauseVO.setDwONumber(Integer.parseInt(strDwoNo));
				}
				
				if (objChangeRequest1058Form.getPartNoChngComp() != null
						&& !objChangeRequest1058Form.getPartNoChngComp().equals("")) {
					String strPartNo = objChangeRequest1058Form.getPartNoChngComp();
					LogUtil.logMessage("Part Number"+strPartNo);
					objClauseVO.setPartNumber(Integer.parseInt(strPartNo));
				}

				if (objChangeRequest1058Form.getPriceBookChngComp() != null
						&& !objChangeRequest1058Form.getPriceBookChngComp().equals("")) {
					String strPriceNo = objChangeRequest1058Form.getPriceBookChngComp();
					LogUtil.logMessage("Price book Number"+strPriceNo);
					objClauseVO.setPriceBookNumber(Integer.parseInt(strPriceNo));
				}
				
				objClauseVO.setComments(strComments);
				objClauseVO.setReason(strReason);

				objClauseVO.setTableDataVO(arlTableList);

				objClauseVO.setUserID(strUserID);
				objClauseVO.setTableArrayData1(arlTabData);
				LogUtil.logMessage("old component seq No. "+objChangeRequest1058Form.getHdnOldComponentSeqNo());
				objClauseVO.setOldComponentSeqNo(objChangeRequest1058Form.getHdnOldComponentSeqNo());
				
				objClauseVO.setSectionSeqNo(objChangeRequest1058Form.getSectionSeqNo());
				LogUtil.logMessage("SectionSeq NO"+objClauseVO.getSectionSeqNo());
				objClauseVO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
				LogUtil.logMessage("seq no 1058 "+objClauseVO.getSeqNo1058());
				objClauseVO.setClauseChangeType(objChangeRequest1058Form.getClauseChangeType());
				LogUtil.logMessage("Clause Change Type"+objClauseVO.getClauseChangeType());
				
				//Clause Version Number
				if (objChangeRequest1058Form.getClauseToVerNo() != 0){
					LogUtil.logMessage("Clause Version Number is"+objChangeRequest1058Form.getClauseToVerNo());
					objClauseVO.setChangeToClaVerNo(objChangeRequest1058Form.getClauseToVerNo());
				}
				
				if (objChangeRequest1058Form.getClauseToSeqNo() != 0){
					LogUtil.logMessage("Change To Clause Seq No Number is"+objChangeRequest1058Form.getClauseToSeqNo());
					objClauseVO.setChangeToClaSeqNo(objChangeRequest1058Form.getClauseToSeqNo());
				}
				
				if(objHttpServletRequest.getParameter("reviseCheck")!=null){
					insertStatus=objChangeRequest1058BI.updateClause(objClauseVO);
					}
					else{
				insertStatus =objChangeRequest1058BI.saveClause(objClauseVO);
					}
				LogUtil.logMessage("Insert/Update Status "+insertStatus);
				if(insertStatus>0){
					objChangeRequest1058Form.setMessageID(String.valueOf(insertStatus));
				}else {
					objChangeRequest1058Form.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				}
			
			arlRequest1058List = objChangeRequest1058BI.fetch1058Details(objChangeRequest1058VO);
			if (arlRequest1058List != null) {
				LogUtil.logMessage(arlRequest1058List);
				objChangeRequest1058Form.setRequestDetailsList(arlRequest1058List);
			}
			objChangeRequest1058Form.setReviseClauseCheck(0);
			objChangeRequest1058Form.setCheckCompChangeClauses(0);
			objChangeRequest1058Form.setCheckVersionClause(0);
			
			LogUtil.logMessage("Requests in ChangeRequest1058Action:"
					+ objChangeRequest1058Form.getRequestDetailsList().size());
			
			//Request 1058 Categories
			arlCategories = objChangeRequest1058BI.fetch1058RequestCategories(objLoginVo);

			if (arlCategories != null) {
				LogUtil.logMessage("Request Categories "+arlCategories);
				objChangeRequest1058Form.setCategories(arlCategories);
			}

			//Request 1058 Request Types
			arlRequestTypes = objChangeRequest1058BI.fetch1058RequestTypes(objLoginVo);
			
			if (arlRequestTypes != null) {
				LogUtil.logMessage("Request Types "+arlRequestTypes);
				objChangeRequest1058Form.setReqTypes(arlRequestTypes);
			}
			
			//Request LSDB Clause Change Types			
			arlClauseChangeTypes = objChangeRequest1058BI.fetch1058ClauseChangeTypes(objLoginVo);
			
			if (arlClauseChangeTypes != null) {
				LogUtil.logMessage("Clause Change Types "+arlClauseChangeTypes);
				objChangeRequest1058Form.setClauseChangeTypes(arlClauseChangeTypes);
			}
			
			objChangeRequest1058VO.setClaChngReqSeq(0);
			
			//Request LSDB Clause Changes Details
			if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.NO)){
				arlReqSpecChngClauses=objChangeRequest1058BI.fetchLsdbClauseDetails(objChangeRequest1058VO);
				if (arlReqSpecChngClauses != null) {
					LogUtil.logMessage("Requested Specification Change "+arlReqSpecChngClauses);
					objChangeRequest1058Form.setReqSpecChngClauseList(arlReqSpecChngClauses);
				}
			}

			//Request Non LSDB Clause Changes Details
			if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.YES)){
				
				ArrayList arlClauseList = objChangeRequest1058BI
						.selectClauseNonLsdb(objChangeRequest1058VO);
				objChangeRequest1058Form.setClaList(arlClauseList);
				LogUtil.logMessage("arlClauseList=" + arlClauseList.size());
			}
			
			//RepresentativeList Details
			//Added For CR-120
			UserVO objUserVO = new UserVO();
			objUserVO.setUserID(objLoginVo.getUserID());
			objUserVO.setOrderbyFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
			arlRepresentativeList = objChangeRequest1058BI.fetchRepresentativeDetails(objUserVO);
				
			if (arlRepresentativeList != null) {
				LogUtil.logMessage(arlRepresentativeList);
				objChangeRequest1058Form.setRepresentativeList(arlRepresentativeList);
			}
			LogUtil.logMessage("ArrayList Value in ChangeRequest1058Action:"
					+ objChangeRequest1058Form.getRepresentativeList().size());

			//Added for CR-127 starts here
			//Model Clause Changes Details
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			
			arlMdlClaChangesList = objChangeRequest1058BI.fetch1058MdlClaChanges(objChangeRequest1058VO);
			if (arlMdlClaChangesList != null) {
				objChangeRequest1058Form.setMdlClaChangesList(arlMdlClaChangesList);
			}
			LogUtil.logMessage("model clause change list" +arlMdlClaChangesList.size());
			for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
			}
			//Added for CR-127 ends here
			//Added for CR-130 starts here
			//SubSection Details
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			arlImportSubSecList = objChangeRequest1058BI.fetchSubSectionList(objChangeRequest1058VO);
				if (arlImportSubSecList != null) {
					
					objChangeRequest1058Form.setImportSubSecList(arlImportSubSecList);
					objChangeRequest1058Form.setImportSubSecListSize(arlImportSubSecList.size());
				}
			LogUtil.logMessage("Import Sub Section List" +arlImportSubSecList.size());
			
			for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
			}
			
			//1058 SubSection Details
			objChangeRequest1058VO.setSubSecChngReqSeq(0);
			
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			arl1058SubSecList = objChangeRequest1058BI.fetch1058SubSectionList(objChangeRequest1058VO);
				if (arl1058SubSecList != null) {
					
					objChangeRequest1058Form.setSubSec1058List(arl1058SubSecList);
				}
			LogUtil.logMessage("Import Sub Section List" +arl1058SubSecList.size());
			
			for (int cnt = 0; cnt < arl1058SubSecList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arl1058SubSecList.get(cnt);
			}
			//Added for CR-130 ends here
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ChangeRequest1058Action..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	public ActionForward deleteSelectedClause(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058Action:deleteSelectedClause");
		
		String strForwardKey = ApplicationConstants.CREATE;
		ArrayList arlRequest1058List=new ArrayList();
		ArrayList arlCategories =new ArrayList();
		ArrayList arlRequestTypes =new ArrayList();
		ArrayList arlClauseChangeTypes  =new ArrayList();
		ArrayList arlReqSpecChngClauses=new ArrayList();
		ArrayList arlRepresentativeList = new ArrayList();
		ArrayList arlMdlClaChangesList = new ArrayList();//Added for CR-127
		ArrayList arlImportSubSecList   = new ArrayList();//Added for CR-130
		ArrayList arl1058SubSecList   = new ArrayList();//Added for CR-130
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LogUtil.logMessage(objSession);
		int deleteStatus=0;
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			ChangeRequest1058Form objChangeRequest1058Form = (ChangeRequest1058Form) objActionForm;
			ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
			int intChngClaSeqNo=Integer.parseInt(objHttpServletRequest.getParameter("chngClaSeqNo"));
			LogUtil.logMessage("value of change Clause Seq NO in action "+intChngClaSeqNo);
			
			ChangeRequest1058VO objChangeRequest1058VO =new ChangeRequest1058VO();
			objChangeRequest1058VO.setUserID(objLoginVo.getUserID());
			objChangeRequest1058VO.setClaChngReqSeq(intChngClaSeqNo);
			objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
						
			if (objChangeRequest1058Form.getStatusSeqNo() == ApplicationConstants.PENDING_ONE ) {
											
				objChangeRequest1058VO.setOrderNo(objChangeRequest1058Form.getOrderNo());
				objChangeRequest1058VO.setCustName(objChangeRequest1058Form.getCustName());
				objChangeRequest1058VO.setMdlName(objChangeRequest1058Form.getMdlName());
				objChangeRequest1058VO.setOrderQty(objChangeRequest1058Form.getOrderQty());
				objChangeRequest1058VO.setReqTypeSeqNo(objChangeRequest1058Form.getReqTypeSeqNo());
				objChangeRequest1058VO.setCategorySeqNo(objChangeRequest1058Form.getCategorySeqNo());
				objChangeRequest1058VO.setGenDesc(objChangeRequest1058Form.getGenDesc());
				objChangeRequest1058VO.setUnitNumber(objChangeRequest1058Form.getUnitNumber());
				objChangeRequest1058VO.setMcrNumber(objChangeRequest1058Form.getMcrNumber());
				objChangeRequest1058VO.setRoadNumber(objChangeRequest1058Form.getRoadNumber());
				objChangeRequest1058VO.setMcrReq(objChangeRequest1058Form.getMcrReq());			
				objChangeRequest1058VO.setSpecRev(objChangeRequest1058Form.getSpecRev());
				objChangeRequest1058VO.setNonLsdbFlag(objChangeRequest1058Form.getHdnNonLsdbFlag());
				
				int intUpdate1058details  = objChangeRequest1058BI.update1058details(objChangeRequest1058VO);
	
			}
			
			deleteStatus=objChangeRequest1058BI.deleteSelectedClause(objChangeRequest1058VO);
			
			LogUtil.logMessage("Delete Status "+deleteStatus);
			if(deleteStatus>0){
			objChangeRequest1058Form.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			}
			
			
			arlRequest1058List = objChangeRequest1058BI.fetch1058Details(objChangeRequest1058VO);
			if (arlRequest1058List != null) {
				LogUtil.logMessage(arlRequest1058List);
				objChangeRequest1058Form.setRequestDetailsList(arlRequest1058List);
			}
			objChangeRequest1058Form.setReviseClauseCheck(0);
			objChangeRequest1058Form.setCheckCompChangeClauses(0);
			objChangeRequest1058Form.setCheckVersionClause(0);
			
			LogUtil.logMessage("Requests in ChangeRequest1058Action:"
					+ objChangeRequest1058Form.getRequestDetailsList().size());
			
			//Request 1058 Categories
			arlCategories = objChangeRequest1058BI.fetch1058RequestCategories(objLoginVo);

			if (arlCategories != null) {
				LogUtil.logMessage("Request Categories "+arlCategories);
				objChangeRequest1058Form.setCategories(arlCategories);
			}

			//Request 1058 Request Types
			arlRequestTypes = objChangeRequest1058BI.fetch1058RequestTypes(objLoginVo);
			
			if (arlRequestTypes != null) {
				LogUtil.logMessage("Request Types "+arlRequestTypes);
				objChangeRequest1058Form.setReqTypes(arlRequestTypes);
			}
			
			//Request LSDB Clause Change Types			
			arlClauseChangeTypes = objChangeRequest1058BI.fetch1058ClauseChangeTypes(objLoginVo);
			
			if (arlClauseChangeTypes != null) {
				LogUtil.logMessage("Clause Change Types "+arlClauseChangeTypes);
				objChangeRequest1058Form.setClauseChangeTypes(arlClauseChangeTypes);
			}
			
			objChangeRequest1058VO.setClaChngReqSeq(0);
			
			//Request LSDB Clause Changes Details
			if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.NO)){
				arlReqSpecChngClauses=objChangeRequest1058BI.fetchLsdbClauseDetails(objChangeRequest1058VO);
				if (arlReqSpecChngClauses != null) {
					LogUtil.logMessage("Requested Specification Change "+arlReqSpecChngClauses);
					objChangeRequest1058Form.setReqSpecChngClauseList(arlReqSpecChngClauses);
				}
			}

			//Request Non LSDB Clause Changes Details
			if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.YES)){
				
				ArrayList arlClauseList = objChangeRequest1058BI
						.selectClauseNonLsdb(objChangeRequest1058VO);
				objChangeRequest1058Form.setClaList(arlClauseList);
				LogUtil.logMessage("arlClauseList=" + arlClauseList.size());
			}
			
			//RepresentativeList Details
			//Added For CR-120
			UserVO objUserVO = new UserVO();
			objUserVO.setUserID(objLoginVo.getUserID());
			objUserVO.setOrderbyFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
			arlRepresentativeList = objChangeRequest1058BI.fetchRepresentativeDetails(objUserVO);
				
			if (arlRepresentativeList != null) {
				LogUtil.logMessage(arlRepresentativeList);
				objChangeRequest1058Form.setRepresentativeList(arlRepresentativeList);
			}
			LogUtil.logMessage("ArrayList Value in ChangeRequest1058Action:"
					+ objChangeRequest1058Form.getRepresentativeList().size());

			//Added for CR-127 starts here
			//Model Clause Changes Details
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			arlMdlClaChangesList = objChangeRequest1058BI.fetch1058MdlClaChanges(objChangeRequest1058VO);
			if (arlMdlClaChangesList != null) {
				objChangeRequest1058Form.setMdlClaChangesList(arlMdlClaChangesList);
			}
			LogUtil.logMessage("model clause change list" +arlMdlClaChangesList.size());
			for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
			}
		
			//Added for CR-127 ends here
			//Added for CR-130 starts here
			//SubSection Details
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			arlImportSubSecList = objChangeRequest1058BI.fetchSubSectionList(objChangeRequest1058VO);
				if (arlImportSubSecList != null) {
					
					objChangeRequest1058Form.setImportSubSecList(arlImportSubSecList);
					objChangeRequest1058Form.setImportSubSecListSize(arlImportSubSecList.size());
				}
			LogUtil.logMessage("Import Sub Section List" +arlImportSubSecList.size());
			
			for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
			}
			
			//1058 SubSection Details
			objChangeRequest1058VO.setSubSecChngReqSeq(0);
			
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			arl1058SubSecList = objChangeRequest1058BI.fetch1058SubSectionList(objChangeRequest1058VO);
				if (arl1058SubSecList != null) {
					
					objChangeRequest1058Form.setSubSec1058List(arl1058SubSecList);
				}
			LogUtil.logMessage("Import Sub Section List" +arl1058SubSecList.size());
			
			for (int cnt = 0; cnt < arl1058SubSecList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arl1058SubSecList.get(cnt);
			}
			//Added for CR-130 ends here
	} catch (Exception objExp) {
		LogUtil
		.logMessage("Enters into Exception Block in ChangeRequest1058Action..");
		ErrorInfo objErrorInfo = new ErrorInfo();
		String strError = objExp.getMessage();
		objErrorInfo.setMessage(strError);
		LogUtil.logMessage("Error Message:" + strError);
		LogUtil.logError(objExp, objErrorInfo);
		strForwardKey = ApplicationConstants.FAILURE;
	}
	return objActionMapping.findForward(strForwardKey);
}
	
	
	public ActionForward reviseSelectedClause(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058Action:reviseSelectedClause");
		
		String strForwardKey = ApplicationConstants.CREATE;
		ArrayList arlRequest1058List=new ArrayList();
		ArrayList arlCategories =new ArrayList();
		ArrayList arlRequestTypes =new ArrayList();
		ArrayList arlClauseChangeTypes  =new ArrayList();
		ArrayList arlReqSpecChngClauses=new ArrayList();
		ArrayList arlRepresentativeList = new ArrayList();
		ArrayList arlStandardEquipList=new ArrayList();
		ArrayList arlReviseClauseDetails =new ArrayList();
		ArrayList arlCompGrpList=new ArrayList();
		ArrayList arlMdlClaChangesList = new ArrayList();//Added for CR-127
		ArrayList arlImportSubSecList = new ArrayList();//Added for CR-130
		ArrayList arl1058SubSecList   = new ArrayList();//Added for CR-130
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LogUtil.logMessage(objSession);
		int deleteStatus=0;
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			ChangeRequest1058Form objChangeRequest1058Form = (ChangeRequest1058Form) objActionForm;
			ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
			
			ChangeRequest1058VO objChangeRequest1058VO =new ChangeRequest1058VO();
			objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
			objChangeRequest1058VO.setUserID(objLoginVo.getUserID());
			
			if (objChangeRequest1058Form.getStatusSeqNo() == ApplicationConstants.PENDING_ONE ) {
											
				objChangeRequest1058VO.setOrderNo(objChangeRequest1058Form.getOrderNo());
				objChangeRequest1058VO.setCustName(objChangeRequest1058Form.getCustName());
				objChangeRequest1058VO.setMdlName(objChangeRequest1058Form.getMdlName());
				objChangeRequest1058VO.setOrderQty(objChangeRequest1058Form.getOrderQty());
				objChangeRequest1058VO.setReqTypeSeqNo(objChangeRequest1058Form.getReqTypeSeqNo());
				objChangeRequest1058VO.setCategorySeqNo(objChangeRequest1058Form.getCategorySeqNo());
				objChangeRequest1058VO.setGenDesc(objChangeRequest1058Form.getGenDesc());
				objChangeRequest1058VO.setUnitNumber(objChangeRequest1058Form.getUnitNumber());
				objChangeRequest1058VO.setMcrNumber(objChangeRequest1058Form.getMcrNumber());
				objChangeRequest1058VO.setRoadNumber(objChangeRequest1058Form.getRoadNumber());
				objChangeRequest1058VO.setMcrReq(objChangeRequest1058Form.getMcrReq());			
				objChangeRequest1058VO.setSpecRev(objChangeRequest1058Form.getSpecRev());
				objChangeRequest1058VO.setNonLsdbFlag(objChangeRequest1058Form.getHdnNonLsdbFlag());
				
				int intUpdate1058details  = objChangeRequest1058BI.update1058details(objChangeRequest1058VO);
	
			}
			
			int intChngClaSeqNo=Integer.parseInt(objHttpServletRequest.getParameter("chngClaSeqNo"));
			objChangeRequest1058VO.setClaChngReqSeq(intChngClaSeqNo);
			LogUtil.logMessage("value of 1058 Seq NO in action "+objChangeRequest1058VO.getSeqNo1058());
			
			arlReviseClauseDetails=objChangeRequest1058BI.reviseSelectedClause(objChangeRequest1058VO);
			
			if (arlReviseClauseDetails != null) {
				LogUtil.logMessage("Revise clause Details List "+arlReviseClauseDetails);
				objChangeRequest1058Form.setReviseClauseDetailsList(arlReviseClauseDetails);
				ClauseVO objClauseVORev =(ClauseVO)arlReviseClauseDetails.get(0);
				objChangeRequest1058Form.setHdnSectionName(objClauseVORev.getSectionName());
				objChangeRequest1058Form.setHdnSubSectionName(objClauseVORev.getSubSectionName());
				objChangeRequest1058Form.setReviseClauseCheck(1);
				if(objClauseVORev.getClauseChangeType()==4){
					objChangeRequest1058Form.setCheckCompChangeClauses(1);
				}else{
					objChangeRequest1058Form.setCheckCompChangeClauses(0);
				}
				objChangeRequest1058Form.setCheckVersionClause(0);
				objChangeRequest1058Form.setClauseChangeRequestSeqNo(objChangeRequest1058VO.getClaChngReqSeq());				
			}
			
			ComponentVO objComponentVO =new ComponentVO();
			objComponentVO.setClauseVOList(arlReviseClauseDetails);
			objComponentVO.setUserID(objLoginVo.getUserID());
			objComponentVO.setOrderKey(objChangeRequest1058Form.getOrderKey());
			objComponentVO.setReviseCheck(1);
			objComponentVO.setDataLocationType(objChangeRequest1058Form.getDataLocType());//Updated for datalocation issue CR-121
			
			arlCompGrpList = objChangeRequest1058BI.fetchComponentGrpMap1058(objComponentVO);
			if (arlCompGrpList != null) {
				LogUtil.logMessage("Components group List in Action"+arlCompGrpList);
				if(arlCompGrpList.size()>0){
				CompGroupVO objCompGroupVo = (CompGroupVO) arlCompGrpList.get(0);
				
				ArrayList arlComponents =objCompGroupVo.getComponentVO();
				LogUtil.logMessage("Components List in Action"+arlCompGrpList);
				objChangeRequest1058Form.setComponentVO(arlComponents);
				}
			}
			
			
			StandardEquipVO objStandardEquipVO = new StandardEquipVO();
			StandardEquipBI objStandardEquipBO = ServiceFactory
					.getStandardEquipBO();
			objStandardEquipVO.setUserID(objLoginVo.getUserID());
			arlStandardEquipList = objStandardEquipBO
					.fetchStandardEquipment(objStandardEquipVO);
			
			objChangeRequest1058Form.setStandardEquipList(arlStandardEquipList);
			
			
			
			LogUtil.logMessage("Sequence No. after revise clause detail fetch in form "+objChangeRequest1058Form.getSeqNo1058());
			LogUtil.logMessage("Sequence No. after revise clause detail fetch in vo "+objChangeRequest1058VO.getSeqNo1058());
			//objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
			
			arlRequest1058List = objChangeRequest1058BI.fetch1058Details(objChangeRequest1058VO);
			if (arlRequest1058List != null) {
				LogUtil.logMessage(arlRequest1058List);
				objChangeRequest1058Form.setRequestDetailsList(arlRequest1058List);
			}
			
			LogUtil.logMessage("Requests in ChangeRequest1058Action:"
					+ objChangeRequest1058Form.getRequestDetailsList().size());
			
			//Request 1058 Categories
			arlCategories = objChangeRequest1058BI.fetch1058RequestCategories(objLoginVo);

			if (arlCategories != null) {
				LogUtil.logMessage("Request Categories "+arlCategories);
				objChangeRequest1058Form.setCategories(arlCategories);
			}

			//Request 1058 Request Types
			arlRequestTypes = objChangeRequest1058BI.fetch1058RequestTypes(objLoginVo);
			
			if (arlRequestTypes != null) {
				LogUtil.logMessage("Request Types "+arlRequestTypes);
				objChangeRequest1058Form.setReqTypes(arlRequestTypes);
			}
			
			//Request LSDB Clause Change Types			
			arlClauseChangeTypes = objChangeRequest1058BI.fetch1058ClauseChangeTypes(objLoginVo);
			
			if (arlClauseChangeTypes != null) {
				LogUtil.logMessage("Clause Change Types "+arlClauseChangeTypes);
				objChangeRequest1058Form.setClauseChangeTypes(arlClauseChangeTypes);
			}
			
			objChangeRequest1058VO.setClaChngReqSeq(0);
			
			//Request LSDB Clause Changes Details
			if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.NO)){
				arlReqSpecChngClauses=objChangeRequest1058BI.fetchLsdbClauseDetails(objChangeRequest1058VO);
				if (arlReqSpecChngClauses != null) {
					LogUtil.logMessage("Requested Specification Change "+arlReqSpecChngClauses);
					objChangeRequest1058Form.setReqSpecChngClauseList(arlReqSpecChngClauses);
				}
			}

			//Request Non LSDB Clause Changes Details
			if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.YES)){
				
				ArrayList arlClauseList = objChangeRequest1058BI
						.selectClauseNonLsdb(objChangeRequest1058VO);
				objChangeRequest1058Form.setClaList(arlClauseList);
				LogUtil.logMessage("arlClauseList=" + arlClauseList.size());
			}
			
			//RepresentativeList Details
			//Added For CR-120
			UserVO objUserVO = new UserVO();
			objUserVO.setUserID(objLoginVo.getUserID());
			objUserVO.setOrderbyFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
			arlRepresentativeList = objChangeRequest1058BI.fetchRepresentativeDetails(objUserVO);
				
			if (arlRepresentativeList != null) {
				LogUtil.logMessage(arlRepresentativeList);
				objChangeRequest1058Form.setRepresentativeList(arlRepresentativeList);
			}
			LogUtil.logMessage("ArrayList Value in ChangeRequest1058Action:"
					+ objChangeRequest1058Form.getRepresentativeList().size());
			
			objChangeRequest1058Form.setSeqNo1058(objChangeRequest1058VO.getSeqNo1058());

			//Added for CR-127 starts here
			//Model Clause Changes Details
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			arlMdlClaChangesList = objChangeRequest1058BI.fetch1058MdlClaChanges(objChangeRequest1058VO);
			if (arlMdlClaChangesList != null) {
				objChangeRequest1058Form.setMdlClaChangesList(arlMdlClaChangesList);
			}
			LogUtil.logMessage("model clause change list" +arlMdlClaChangesList.size());
			for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
			}
			//Added for CR-127 ends here
			//Added for CR-130 starts here
			//SubSection Details
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			arlImportSubSecList = objChangeRequest1058BI.fetchSubSectionList(objChangeRequest1058VO);
				if (arlImportSubSecList != null) {
					
					objChangeRequest1058Form.setImportSubSecList(arlImportSubSecList);
					objChangeRequest1058Form.setImportSubSecListSize(arlImportSubSecList.size());
				}
			LogUtil.logMessage("Import Sub Section List" +arlImportSubSecList.size());
			
			for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
			}
			
			//1058 SubSection Details
			objChangeRequest1058VO.setSubSecChngReqSeq(0);
			
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			arl1058SubSecList = objChangeRequest1058BI.fetch1058SubSectionList(objChangeRequest1058VO);
				if (arl1058SubSecList != null) {
					
					objChangeRequest1058Form.setSubSec1058List(arl1058SubSecList);
				}
			LogUtil.logMessage("Import Sub Section List" +arl1058SubSecList.size());
			
			for (int cnt = 0; cnt < arl1058SubSecList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arl1058SubSecList.get(cnt);
			}
			//Added for CR-130 ends here
	} catch (Exception objExp) {
		LogUtil
		.logMessage("Enters into Exception Block in ChangeRequest1058Action..");
		ErrorInfo objErrorInfo = new ErrorInfo();
		String strError = objExp.getMessage();
		objErrorInfo.setMessage(strError);
		LogUtil.logMessage("Error Message:" + strError);
		LogUtil.logError(objExp, objErrorInfo);
		strForwardKey = ApplicationConstants.FAILURE;
	}
	return objActionMapping.findForward(strForwardKey);
}
	
	
	
	public ActionForward updateClausesToSpec(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058Action:updateClausesToSpec");
		
		String strForwardKey = ApplicationConstants.CREATE;
		ArrayList arlRequest1058List=new ArrayList();
		ArrayList arlCategories =new ArrayList();
		ArrayList arlRequestTypes =new ArrayList();
		ArrayList arlClauseChangeTypes  =new ArrayList();
		ArrayList arlReqSpecChngClauses=new ArrayList();
		ArrayList arlRepresentativeList = new ArrayList();
		ArrayList arlMdlClaChangesList = new ArrayList();//Added for CR-127
		ArrayList arlImportSubSecList   = new ArrayList();//Added for CR-130
		ArrayList arl1058SubSecList   = new ArrayList();//Added for CR-130
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		int intChngClaSeqNo=0;
		
		LogUtil.logMessage(objSession);
		int updateStatus=0;
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			ChangeRequest1058Form objChangeRequest1058Form = (ChangeRequest1058Form) objActionForm;
			ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
			if(objHttpServletRequest.getParameter("chngClaSeqNo")!=null){
				intChngClaSeqNo=Integer.parseInt(objHttpServletRequest.getParameter("chngClaSeqNo"));
			}
			
			LogUtil.logMessage("value of change Clause Seq NO in action "+intChngClaSeqNo);
			ChangeRequest1058VO objChangeRequest1058VO =new ChangeRequest1058VO();
			objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
			objChangeRequest1058VO.setUserID(objLoginVo.getUserID());
			
			if (objChangeRequest1058Form.getStatusSeqNo() == ApplicationConstants.PENDING_ONE ) {
											
				objChangeRequest1058VO.setOrderNo(objChangeRequest1058Form.getOrderNo());
				objChangeRequest1058VO.setCustName(objChangeRequest1058Form.getCustName());
				objChangeRequest1058VO.setMdlName(objChangeRequest1058Form.getMdlName());
				objChangeRequest1058VO.setOrderQty(objChangeRequest1058Form.getOrderQty());
				objChangeRequest1058VO.setReqTypeSeqNo(objChangeRequest1058Form.getReqTypeSeqNo());
				objChangeRequest1058VO.setCategorySeqNo(objChangeRequest1058Form.getCategorySeqNo());
				objChangeRequest1058VO.setGenDesc(objChangeRequest1058Form.getGenDesc());
				objChangeRequest1058VO.setUnitNumber(objChangeRequest1058Form.getUnitNumber());
				objChangeRequest1058VO.setMcrNumber(objChangeRequest1058Form.getMcrNumber());
				objChangeRequest1058VO.setRoadNumber(objChangeRequest1058Form.getRoadNumber());
				objChangeRequest1058VO.setMcrReq(objChangeRequest1058Form.getMcrReq());			
				objChangeRequest1058VO.setSpecRev(objChangeRequest1058Form.getSpecRev());
				objChangeRequest1058VO.setNonLsdbFlag(objChangeRequest1058Form.getHdnNonLsdbFlag());
				
				int intUpdate1058details  = objChangeRequest1058BI.update1058details(objChangeRequest1058VO);
			}
			
			objChangeRequest1058VO.setClaChngReqSeq(intChngClaSeqNo);
			
			updateStatus=objChangeRequest1058BI.updateClausesToSpec(objChangeRequest1058VO);
			
			LogUtil.logMessage("Update Status "+updateStatus);
			if(updateStatus>0){
				objChangeRequest1058Form.setMessageID(String.valueOf(updateStatus));
			}else {
				objChangeRequest1058Form.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			}
			
			arlRequest1058List = objChangeRequest1058BI.fetch1058Details(objChangeRequest1058VO);
			if (arlRequest1058List != null) {
				LogUtil.logMessage(arlRequest1058List);
				objChangeRequest1058Form.setRequestDetailsList(arlRequest1058List);
			}
			
			objChangeRequest1058Form.setReviseClauseCheck(0);
			objChangeRequest1058Form.setCheckCompChangeClauses(0);
			objChangeRequest1058Form.setCheckVersionClause(0);
			
			LogUtil.logMessage("Requests in ChangeRequest1058Action:"
					+ objChangeRequest1058Form.getRequestDetailsList().size());
			
			//Request 1058 Categories
			arlCategories = objChangeRequest1058BI.fetch1058RequestCategories(objLoginVo);

			if (arlCategories != null) {
				LogUtil.logMessage("Request Categories "+arlCategories);
				objChangeRequest1058Form.setCategories(arlCategories);
			}

			//Request 1058 Request Types
			arlRequestTypes = objChangeRequest1058BI.fetch1058RequestTypes(objLoginVo);
			
			if (arlRequestTypes != null) {
				LogUtil.logMessage("Request Types "+arlRequestTypes);
				objChangeRequest1058Form.setReqTypes(arlRequestTypes);
			}
			
			//Request LSDB Clause Change Types			
			arlClauseChangeTypes = objChangeRequest1058BI.fetch1058ClauseChangeTypes(objLoginVo);
			
			if (arlClauseChangeTypes != null) {
				LogUtil.logMessage("Clause Change Types "+arlClauseChangeTypes);
				objChangeRequest1058Form.setClauseChangeTypes(arlClauseChangeTypes);
			}
			
			objChangeRequest1058VO.setClaChngReqSeq(0);
			
			//Request LSDB Clause Changes Details
			if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.NO)){
				arlReqSpecChngClauses=objChangeRequest1058BI.fetchLsdbClauseDetails(objChangeRequest1058VO);
				if (arlReqSpecChngClauses != null) {
					LogUtil.logMessage("Requested Specification Change "+arlReqSpecChngClauses);
					objChangeRequest1058Form.setReqSpecChngClauseList(arlReqSpecChngClauses);
				}
			}

			//Request Non LSDB Clause Changes Details
			if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.YES)){
				
				ArrayList arlClauseList = objChangeRequest1058BI
						.selectClauseNonLsdb(objChangeRequest1058VO);
				objChangeRequest1058Form.setClaList(arlClauseList);
				LogUtil.logMessage("arlClauseList=" + arlClauseList.size());
			}
			
			//RepresentativeList Details
			//Added For CR-120
			UserVO objUserVO = new UserVO();
			objUserVO.setUserID(objLoginVo.getUserID());
			objUserVO.setOrderbyFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
			arlRepresentativeList = objChangeRequest1058BI.fetchRepresentativeDetails(objUserVO);
				
			if (arlRepresentativeList != null) {
				LogUtil.logMessage(arlRepresentativeList);
				objChangeRequest1058Form.setRepresentativeList(arlRepresentativeList);
			}
			LogUtil.logMessage("ArrayList Value in ChangeRequest1058Action:"
					+ objChangeRequest1058Form.getRepresentativeList().size());
			
			//Added for CR-127 starts here
			//Model Clause Changes Details
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			arlMdlClaChangesList = objChangeRequest1058BI.fetch1058MdlClaChanges(objChangeRequest1058VO);
			if (arlMdlClaChangesList != null) {
				objChangeRequest1058Form.setMdlClaChangesList(arlMdlClaChangesList);
				objChangeRequest1058Form.setMdlClaChangeListSize(arlMdlClaChangesList.size());
			}
			LogUtil.logMessage("model clause change list" +arlMdlClaChangesList.size());
			for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
			}
			//Added for CR-127 ends here
			//Added for CR-130 starts here
			//SubSection Details
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			arlImportSubSecList = objChangeRequest1058BI.fetchSubSectionList(objChangeRequest1058VO);
				if (arlImportSubSecList != null) {
					
					objChangeRequest1058Form.setImportSubSecList(arlImportSubSecList);
					objChangeRequest1058Form.setImportSubSecListSize(arlImportSubSecList.size());
				}
			LogUtil.logMessage("Import Sub Section List" +arlImportSubSecList.size());
			
			for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
			}
			
			//1058 SubSection Details
			objChangeRequest1058VO.setSubSecChngReqSeq(0);
			
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			arl1058SubSecList = objChangeRequest1058BI.fetch1058SubSectionList(objChangeRequest1058VO);
				if (arl1058SubSecList != null) {
					
					objChangeRequest1058Form.setSubSec1058List(arl1058SubSecList);
				}
			LogUtil.logMessage("Import Sub Section List" +arl1058SubSecList.size());
			
			for (int cnt = 0; cnt < arl1058SubSecList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arl1058SubSecList.get(cnt);
			}
			//Added for CR-130 ends here
	} catch (Exception objExp) {
		LogUtil
		.logMessage("Enters into Exception Block in ChangeRequest1058Action..");
		ErrorInfo objErrorInfo = new ErrorInfo();
		String strError = objExp.getMessage();
		objErrorInfo.setMessage(strError);
		LogUtil.logMessage("Error Message:" + strError);
		LogUtil.logError(objExp, objErrorInfo);
		strForwardKey = ApplicationConstants.FAILURE;
	}
	return objActionMapping.findForward(strForwardKey);
}
	
	
	public ActionForward getClausesForChangeComponent(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058Action:getClausesForChangeComponent");
		
		String strForwardKey = ApplicationConstants.CREATE;
		ArrayList arlRequest1058List=new ArrayList();
		ArrayList arlCategories =new ArrayList();
		ArrayList arlRequestTypes =new ArrayList();
		ArrayList arlClauseChangeTypes  =new ArrayList();
		ArrayList arlReqSpecChngClauses=new ArrayList();
		ArrayList arlRepresentativeList = new ArrayList();
		ArrayList arlCompGrpDtls=new ArrayList();
		ArrayList arlChangeCompClause =new ArrayList();
		
		ArrayList arlSectionList=new ArrayList(); 
		ArrayList arlSubSectionList=new ArrayList();
		ArrayList arlCompGrpList=new ArrayList();
		ArrayList arlStandardEquipList=new ArrayList();
		ArrayList arlMdlClaChangesList = new ArrayList();//Added for CR-127
		ArrayList arlImportSubSecList   = new ArrayList();//Added for CR-130
		ArrayList arl1058SubSecList   = new ArrayList();//Added for CR-130
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		ComponentVO objComponentVO=new ComponentVO();
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			ChangeRequest1058Form objChangeRequest1058Form = (ChangeRequest1058Form) objActionForm;
			ChangeRequest1058VO objChangeRequest1058VO =new ChangeRequest1058VO();
			objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
			objChangeRequest1058VO.setUserID(objLoginVo.getUserID());
			ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
			
			if (objChangeRequest1058Form.getStatusSeqNo() == ApplicationConstants.PENDING_ONE ) {
											
				objChangeRequest1058VO.setOrderNo(objChangeRequest1058Form.getOrderNo());
				objChangeRequest1058VO.setCustName(objChangeRequest1058Form.getCustName());
				objChangeRequest1058VO.setMdlName(objChangeRequest1058Form.getMdlName());
				objChangeRequest1058VO.setOrderQty(objChangeRequest1058Form.getOrderQty());
				objChangeRequest1058VO.setReqTypeSeqNo(objChangeRequest1058Form.getReqTypeSeqNo());
				objChangeRequest1058VO.setCategorySeqNo(objChangeRequest1058Form.getCategorySeqNo());
				objChangeRequest1058VO.setGenDesc(objChangeRequest1058Form.getGenDesc());
				objChangeRequest1058VO.setUnitNumber(objChangeRequest1058Form.getUnitNumber());
				objChangeRequest1058VO.setMcrNumber(objChangeRequest1058Form.getMcrNumber());
				objChangeRequest1058VO.setRoadNumber(objChangeRequest1058Form.getRoadNumber());
				objChangeRequest1058VO.setMcrReq(objChangeRequest1058Form.getMcrReq());			
				objChangeRequest1058VO.setSpecRev(objChangeRequest1058Form.getSpecRev());
				objChangeRequest1058VO.setNonLsdbFlag(objChangeRequest1058Form.getHdnNonLsdbFlag());
				
				int intUpdate1058details  = objChangeRequest1058BI.update1058details(objChangeRequest1058VO);
	
			}
			
			int sectionSeqNO =Integer.parseInt(objChangeRequest1058Form.getSections());
			int subSectionSeqNo =Integer.parseInt(objChangeRequest1058Form.getSubSections());
			int componentGroupSeqNo =Integer.parseInt(objChangeRequest1058Form.getComponentGroup());
			int ComponentSeqNo =Integer.parseInt(objChangeRequest1058Form.getComponents());
			
			objComponentVO.setModelSeqNo(objChangeRequest1058Form.getMdlSeqNo());
			objComponentVO.setComponentGroupSeqNo(Integer.parseInt(objChangeRequest1058Form.getComponentGroup()));
			objComponentVO.setComponentSeqNo(Integer.parseInt(objChangeRequest1058Form.getComponents()));
			
			ModelCompMapBI objModelCompMapBO = ServiceFactory.getModelCompMapBO();					
			arlCompGrpDtls = objModelCompMapBO.fetchCompClauseMapDtls(objComponentVO);
			
			if (arlCompGrpDtls != null) {
				LogUtil.logMessage(arlCompGrpDtls);
				objChangeRequest1058Form.setCheckCompChangeClauses(1);
				CompGroupVO objCompGroupVO =(CompGroupVO)arlCompGrpDtls.get(0);
				ArrayList arlCompList=objCompGroupVO.getComponent();
				
				if(arlCompList.size()!= 0)
				{
				ComponentVO objComponentForChngCompVO=(ComponentVO)arlCompList.get(0);
				ArrayList arlClauseVO =objComponentForChngCompVO.getClauseVOList();
				
				ClauseVO objClauseVO =null;
				if(arlClauseVO.size() != 0){
					objClauseVO =(ClauseVO)arlClauseVO.get(0);
					objChangeRequest1058Form.setStandardEquipmentSeqNoChngComp(objClauseVO.getStandardEquipmentSeqNo());
					
					arlChangeCompClause.add(objClauseVO);
					objChangeRequest1058Form.setChangeComponentClause(arlChangeCompClause);
				}
				else{
					objChangeRequest1058Form.setChangeComponentClause(null);
					
				}

				}
				else{
					objChangeRequest1058Form.setChangeComponentClause(null);
				}			
				objChangeRequest1058Form.setSectionSeqNo(sectionSeqNO);
				objChangeRequest1058Form.setSubSectionSeqNo(subSectionSeqNo);
				objChangeRequest1058Form.setComponentGrpSeqNoinAdd(componentGroupSeqNo);
				objChangeRequest1058Form.setHdnLeadComponentSeqNoinAdd(ComponentSeqNo);
			} 
			//Added for CR_128 to make Change Component Clause to empty
			if (objComponentVO.getComponentSeqNo() == -1 ) {
				objChangeRequest1058Form.setChangeComponentClause(null);
			}
			
			int intModelSeqNo = objChangeRequest1058Form.getMdlSeqNo();
			int intOrdeKey =objChangeRequest1058Form.getOrderKey();
			String dataLocType = objChangeRequest1058Form.getDataLocType();//Updated for datalocation issue CR-121
			
			SectionVO objSectionVo = new SectionVO();
			objSectionVo.setModelSeqNo(intModelSeqNo);
			objSectionVo.setUserID(objLoginVo.getUserID());
			objSectionVo.setOrderKey(intOrdeKey);
			objSectionVo.setDataLocationType(dataLocType);
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVo);
			LogUtil.logMessage("Arraylist returned in For change component clause SECTIONS"+arlSectionList);
			objChangeRequest1058Form.setSectionList(arlSectionList);
			
			objSectionVo = new SectionVO();
			
			objSectionVo.setModelSeqNo(intModelSeqNo);
			objSectionVo.setUserID(objLoginVo.getUserID());
			objSectionVo.setOrderKey(intOrdeKey);
			objSectionVo.setDataLocationType(dataLocType);
			objSectionVo.setSectionSeqNo(sectionSeqNO);
			arlSubSectionList = objChangeRequest1058BI.fetchOrderSubSections1058(objSectionVo);
			objChangeRequest1058Form.setSubSectionList(arlSubSectionList);
						
			objComponentVO = new ComponentVO();
			objComponentVO.setUserID(objLoginVo.getUserID());
			objComponentVO.setModelSeqNo(intModelSeqNo);
			objComponentVO.setSubSectionSeqNo(subSectionSeqNo);
			objComponentVO.setOrderKey(intOrdeKey);
			objComponentVO.setDataLocationType(dataLocType);
			objComponentVO.setReviseCheck(0);
			
			arlCompGrpList = objChangeRequest1058BI.fetchComponentGrpMap1058(objComponentVO);
			LogUtil.logMessage("arrayList of component returned in action "+arlCompGrpList);
			objChangeRequest1058Form.setComponentGrpList(arlCompGrpList);
			
			objComponentVO = new ComponentVO();
			arlCompGrpList = new ArrayList();
			objComponentVO.setUserID(objLoginVo.getUserID());
			objComponentVO.setModelSeqNo(intModelSeqNo);
			objComponentVO.setSubSectionSeqNo(subSectionSeqNo);
			objComponentVO.setOrderKey(intOrdeKey);
			objComponentVO.setDataLocationType(dataLocType);
			objComponentVO.setComponentGroupSeqNo(componentGroupSeqNo);
			objComponentVO.setReviseCheck(0);
			
			arlCompGrpList = objChangeRequest1058BI.fetchComponentGrpMap1058(objComponentVO);
			
			CompGroupVO objCompGroupVo = (CompGroupVO) arlCompGrpList.get(0);
			ArrayList arlComponents =objCompGroupVo.getComponentVO();
			objChangeRequest1058Form.setComponentList(arlComponents);
			
			
			StandardEquipVO objStandardEquipVO = new StandardEquipVO();
			StandardEquipBI objStandardEquipBO = ServiceFactory
					.getStandardEquipBO();
			objStandardEquipVO.setUserID(objLoginVo.getUserID());
			arlStandardEquipList = objStandardEquipBO
					.fetchStandardEquipment(objStandardEquipVO);
			
			objChangeRequest1058Form.setStandardEquipList(arlStandardEquipList);
						
			arlRequest1058List = objChangeRequest1058BI.fetch1058Details(objChangeRequest1058VO);
			if (arlRequest1058List != null) {
				LogUtil.logMessage(arlRequest1058List);
				objChangeRequest1058Form.setRequestDetailsList(arlRequest1058List);
			}
			objChangeRequest1058Form.setReviseClauseCheck(0);
			objChangeRequest1058Form.setCheckVersionClause(0);
			
			LogUtil.logMessage("Requests in ChangeRequest1058Action:"
					+ objChangeRequest1058Form.getRequestDetailsList().size());
			
			//Request 1058 Categories
			arlCategories = objChangeRequest1058BI.fetch1058RequestCategories(objLoginVo);

			if (arlCategories != null) {
				LogUtil.logMessage("Request Categories "+arlCategories);
				objChangeRequest1058Form.setCategories(arlCategories);
			}

			//Request 1058 Request Types
			arlRequestTypes = objChangeRequest1058BI.fetch1058RequestTypes(objLoginVo);
			
			if (arlRequestTypes != null) {
				LogUtil.logMessage("Request Types "+arlRequestTypes);
				objChangeRequest1058Form.setReqTypes(arlRequestTypes);
			}
			
			//Request LSDB Clause Change Types			
			arlClauseChangeTypes = objChangeRequest1058BI.fetch1058ClauseChangeTypes(objLoginVo);
			
			if (arlClauseChangeTypes != null) {
				LogUtil.logMessage("Clause Change Types "+arlClauseChangeTypes);
				objChangeRequest1058Form.setClauseChangeTypes(arlClauseChangeTypes);
			}
			
			objChangeRequest1058VO.setClaChngReqSeq(0);
			
			//Request LSDB Clause Changes Details
			if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.NO)){
				arlReqSpecChngClauses=objChangeRequest1058BI.fetchLsdbClauseDetails(objChangeRequest1058VO);
				if (arlReqSpecChngClauses != null) {
					LogUtil.logMessage("Requested Specification Change "+arlReqSpecChngClauses);
					objChangeRequest1058Form.setReqSpecChngClauseList(arlReqSpecChngClauses);
				}
			}

			//Request Non LSDB Clause Changes Details
			if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.YES)){
				
				ArrayList arlClauseList = objChangeRequest1058BI
						.selectClauseNonLsdb(objChangeRequest1058VO);
				objChangeRequest1058Form.setClaList(arlClauseList);
				LogUtil.logMessage("arlClauseList=" + arlClauseList.size());
			}
			
			//RepresentativeList Details
			//Added For CR-120
			UserVO objUserVO = new UserVO();
			objUserVO.setUserID(objLoginVo.getUserID());
			objUserVO.setOrderbyFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
			arlRepresentativeList = objChangeRequest1058BI.fetchRepresentativeDetails(objUserVO);
				
			if (arlRepresentativeList != null) {
				LogUtil.logMessage(arlRepresentativeList);
				objChangeRequest1058Form.setRepresentativeList(arlRepresentativeList);
			}
			LogUtil.logMessage("ArrayList Value in ChangeRequest1058Action:"
					+ objChangeRequest1058Form.getRepresentativeList().size());

			//Added for CR-127 starts here
			//Model Clause Changes Details
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			arlMdlClaChangesList = objChangeRequest1058BI.fetch1058MdlClaChanges(objChangeRequest1058VO);
			if (arlMdlClaChangesList != null) {
				objChangeRequest1058Form.setMdlClaChangesList(arlMdlClaChangesList);
			}
			LogUtil.logMessage("model clause change list" +arlMdlClaChangesList.size());
			for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
			}
			//Added for CR-127 ends here
			//Added for CR-130 starts here
			//SubSection Details
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			arlImportSubSecList = objChangeRequest1058BI.fetchSubSectionList(objChangeRequest1058VO);
				if (arlImportSubSecList != null) {
					
					objChangeRequest1058Form.setImportSubSecList(arlImportSubSecList);
					objChangeRequest1058Form.setImportSubSecListSize(arlImportSubSecList.size());
				}
			LogUtil.logMessage("Import Sub Section List" +arlImportSubSecList.size());
			
			for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
			}
			
			//1058 SubSection Details
			objChangeRequest1058VO.setSubSecChngReqSeq(0);
			
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			arl1058SubSecList = objChangeRequest1058BI.fetch1058SubSectionList(objChangeRequest1058VO);
				if (arl1058SubSecList != null) {
					
					objChangeRequest1058Form.setSubSec1058List(arl1058SubSecList);
				}
			LogUtil.logMessage("Import Sub Section List" +arl1058SubSecList.size());
			
			for (int cnt = 0; cnt < arl1058SubSecList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arl1058SubSecList.get(cnt);
			}
			//Added for CR-130 ends here
	} catch (Exception objExp) {
		LogUtil
		.logMessage("Enters into Exception Block in ChangeRequest1058Action..");
		ErrorInfo objErrorInfo = new ErrorInfo();
		String strError = objExp.getMessage();
		objErrorInfo.setMessage(strError);
		LogUtil.logMessage("Error Message:" + strError);
		LogUtil.logError(objExp, objErrorInfo);
		strForwardKey = ApplicationConstants.FAILURE;
	}
	return objActionMapping.findForward(strForwardKey);
}

	
	public ActionForward selectClauseVersions(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws EMDException {
		LogUtil
		.logMessage("Inside the ChangeRequest1058Action:selectClauseVersion");
		String strForwardKey = ApplicationConstants.CR1058_Select_Clause_Version;
		String strUserID = "";
		int intSectionSeqNo = 0;
		int intSubSectionSeqNo = 0;
		int intClauseSeqNo = 0;
		int intModelSeqNo=0;
		String strSelectedSection = "";
		String strSelectedSubSection = "";
		ArrayList arlAllVersion = new ArrayList();
		ArrayList arlAllClauseList = new ArrayList();
		
		/*
		 * ModelSeqNo,SectionSeqNo And SubSectionSeqNo are getting as a request
		 * parameters to generate the clause numbers for the selected subsection
		 * in parent clause pop-up screen. This action calls the ViewSpecByModel
		 * DAO in order to reuse the clause generation logic at Model Level.
		 *  
		 */
		
		
		
		if (objHttpServletRequest.getParameter("selectedClauseID") != null) {
			intClauseSeqNo = Integer.parseInt(objHttpServletRequest
					.getParameter("selectedClauseID"));
		}
		
		if (objHttpServletRequest
				.getParameter(ApplicationConstants.SELECTED_SECTION_NAME) != null) {
			strSelectedSection = objHttpServletRequest
			.getParameter(ApplicationConstants.SELECTED_SECTION_NAME);
		}
		
		if (objHttpServletRequest
				.getParameter(ApplicationConstants.SELECTED_SUB_SECTION_NAME) != null) {
			strSelectedSubSection = objHttpServletRequest
			.getParameter(ApplicationConstants.SELECTED_SUB_SECTION_NAME);
		}
		
		if (objHttpServletRequest
				.getParameter(ApplicationConstants.SELECTED_SUB_SECTION_ID) != null) {
			intSubSectionSeqNo = Integer
			.parseInt(objHttpServletRequest
					.getParameter(ApplicationConstants.SELECTED_SUB_SECTION_ID));
		}
		if (objHttpServletRequest.getParameter("selectedSectionID") != null) {
			intSectionSeqNo = Integer.parseInt(objHttpServletRequest
					.getParameter("selectedSectionID"));
		}
		if (objHttpServletRequest.getParameter("selectedModelID") != null) {
			intModelSeqNo = Integer.parseInt(objHttpServletRequest
					.getParameter("selectedModelID"));
		}
		
		if (objHttpServletRequest.getParameter("selectedClauseSeqNo") != null) {
			intClauseSeqNo = Integer.parseInt(objHttpServletRequest
					.getParameter("selectedClauseSeqNo"));
		}

		try {
			ChangeRequest1058Form objChangeRequest1058Form = (ChangeRequest1058Form) objActionForm;
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			
			objChangeRequest1058Form.setMdlSeqNo(intModelSeqNo);
			objChangeRequest1058Form.setSubSectionSeqNo(intSubSectionSeqNo);
			objChangeRequest1058Form.setSectionSeqNo(intSectionSeqNo);
			objChangeRequest1058Form.setHdnSectionName(strSelectedSection);
			objChangeRequest1058Form.setHdnSubSectionName(strSelectedSubSection);
			ClauseVO objClauseVO = new ClauseVO();
			LogUtil.logMessage("ModelSeqNo in FetchClauseVersion**"
					+ objChangeRequest1058Form.getMdlSeqNo());
			objClauseVO.setModelSeqNo(objChangeRequest1058Form.getMdlSeqNo());
			LogUtil.logMessage("SubSectionSeqNo in FetchClauseVersion**"
					+ objChangeRequest1058Form.getSubSectionSeqNo());
			objClauseVO.setSubSectionSeqNo(objChangeRequest1058Form
					.getSubSectionSeqNo());
			objClauseVO.setSectionSeqNo(objChangeRequest1058Form
					.getSectionSeqNo());
			LogUtil.logMessage("ClauseSeqNo in FetchClauseVersion**"
					+ intClauseSeqNo);
			
			objClauseVO.setClauseSeqNo(intClauseSeqNo);
			
			objClauseVO.setUserID(objLoginVo.getUserID());
			
			ModelSelectClauseRevBI objModelSelectClauseRevBO = ServiceFactory
			.getModelSelectClauseRevBO();
			arlAllClauseList = objModelSelectClauseRevBO
			.fetchClauseVersions(objClauseVO);
			
			if (arlAllClauseList != null && arlAllClauseList.size() > 0) {
				
				arlAllVersion = (ArrayList) arlAllClauseList.get(0);
				
				objChangeRequest1058Form.setNoOfClaVersion(arlAllVersion.size());
				LogUtil.logMessage("Size of ArrayList:" + arlAllVersion.size());
				objChangeRequest1058Form.setClauseVersions(arlAllVersion);
				strForwardKey = ApplicationConstants.CR1058_Select_Clause_Version;
				
			}
			
		}
		
		catch (Exception objExp) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
			
		}
		return objActionMapping.findForward(strForwardKey);
	}
	
	
	public ActionForward fetchVersionClause(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws EMDException {
		LogUtil
		.logMessage("Inside the ChangeRequest1058Action:fetchVersionClause");
		String strForwardKey = ApplicationConstants.CREATE;
		String strUserID = "";
		int intVersionNo=0;
		ArrayList arlRequest1058List = new ArrayList();
		ArrayList arlCategories =new ArrayList();
		ArrayList arlRequestTypes =new ArrayList();
		ArrayList arlClauseChangeTypes  =new ArrayList();
		ArrayList arlReqSpecChngClauses =new ArrayList();
		ArrayList arlRepresentativeList = new ArrayList();
		ArrayList arlStandardEquipList = new ArrayList();
		ArrayList arlAllVersion = new ArrayList();
		ArrayList arlDefaultVersion=new ArrayList();
		ArrayList arlAllClauseList = new ArrayList();
		ArrayList arlMdlClaChangesList = new ArrayList();//Added for CR-127
		ArrayList arlImportSubSecList   = new ArrayList();//Added for CR-130
		ArrayList arl1058SubSecList   = new ArrayList();//Added for CR-130
		
		try {
			ChangeRequest1058Form objChangeRequest1058Form = (ChangeRequest1058Form) objActionForm;
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			
			ChangeRequest1058VO objChangeRequest1058VO =new ChangeRequest1058VO();
			objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
			objChangeRequest1058VO.setUserID(objLoginVo.getUserID());
			ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
			
			if (objChangeRequest1058Form.getStatusSeqNo() == ApplicationConstants.PENDING_ONE ) {
				
				objChangeRequest1058VO.setOrderNo(objChangeRequest1058Form.getOrderNo());
				objChangeRequest1058VO.setCustName(objChangeRequest1058Form.getCustName());
				objChangeRequest1058VO.setMdlName(objChangeRequest1058Form.getMdlName());
				objChangeRequest1058VO.setOrderQty(objChangeRequest1058Form.getOrderQty());
				objChangeRequest1058VO.setReqTypeSeqNo(objChangeRequest1058Form.getRequestType());
				objChangeRequest1058VO.setCategorySeqNo(objChangeRequest1058Form.getRequestFrom());
				objChangeRequest1058VO.setGenDesc(objChangeRequest1058Form.getGenDesc());
				objChangeRequest1058VO.setUnitNumber(objChangeRequest1058Form.getUnitNumber());
				objChangeRequest1058VO.setMcrNumber(objChangeRequest1058Form.getMcrNumber());
				objChangeRequest1058VO.setRoadNumber(objChangeRequest1058Form.getRoadNumber());
				objChangeRequest1058VO.setMcrReq(objChangeRequest1058Form.getMcrnumberReq());			
				objChangeRequest1058VO.setSpecRev(objChangeRequest1058Form.getSpecRev());
				objChangeRequest1058VO.setNonLsdbFlag(objChangeRequest1058Form.getHdnNonLsdbFlag());
				
				int intUpdate1058details  = objChangeRequest1058BI.update1058details(objChangeRequest1058VO);
	
			}
					
			ClauseVO objClauseVO = new ClauseVO();
			LogUtil.logMessage("ModelSeqNo in FetchClauseVersion**"
					+ objChangeRequest1058Form.getMdlSeqNo());
			objClauseVO.setModelSeqNo(objChangeRequest1058Form.getMdlSeqNo());
			LogUtil.logMessage("SubSectionSeqNo in FetchClauseVersion**"
					+ objChangeRequest1058Form.getSubSectionSeqNo());
			objClauseVO.setSubSectionSeqNo(objChangeRequest1058Form
					.getSubSectionSeqNo());
			//Added for CR_80 CR Form Enhancements III on 24-Nov-09 by RR68151
			objClauseVO.setSectionSeqNo(objChangeRequest1058Form
					.getSectionSeqNo());
			
			LogUtil.logMessage("ClauseSeqNo in FetchClauseVersion**"
					+ objChangeRequest1058Form.getHdnClauseToModifySeqNo());
			
			objClauseVO.setClauseSeqNo(objChangeRequest1058Form.getHdnClauseToModifySeqNo());
			
			if (objHttpServletRequest.getParameter("versionNo") != null) {
				intVersionNo = Integer.parseInt(objHttpServletRequest
						.getParameter("versionNo"));
			}
			LogUtil.logMessage("Clause Version no. in FetchClauseVersion**"
					+ intVersionNo);
			objClauseVO.setVersionNo(intVersionNo);
			objClauseVO.setUserID(objLoginVo.getUserID());
			
			ModelSelectClauseRevBI objModelSelectClauseRevBO = ServiceFactory
			.getModelSelectClauseRevBO();
			arlAllClauseList = objModelSelectClauseRevBO
			.fetchClauseVersions(objClauseVO);
			
			if (arlAllClauseList != null && arlAllClauseList.size() > 0) {
				
				if(intVersionNo!=0){
				arlAllVersion = (ArrayList) arlAllClauseList.get(0);
				//Added for CR_80 CR Form Enhancements III on 24-Nov-09 by RR68151
				objChangeRequest1058Form.setNoOfClaVersion(arlAllVersion.size());
				LogUtil.logMessage("Size of ArrayList:" + arlAllVersion.size());
				objChangeRequest1058Form.setVersionClauseDetails(arlAllVersion);
				ClauseVO objClauseVOAll =(ClauseVO)arlAllVersion.get(0);
				objChangeRequest1058Form.setStandardEquipmentSeqNoMod(objClauseVOAll.getStandardEquipmentSeqNo());
				
				}
				else{
					arlDefaultVersion =(ArrayList) arlAllClauseList.get(1);
					objChangeRequest1058Form.setNoOfClaVersion(arlDefaultVersion.size());
					LogUtil.logMessage("Size of ArrayList:" + arlDefaultVersion.size());
					objChangeRequest1058Form.setVersionClauseDetails(arlDefaultVersion);
					ClauseVO objClauseVODef =(ClauseVO)arlDefaultVersion.get(0);
					objChangeRequest1058Form.setStandardEquipmentSeqNoMod(objClauseVODef.getStandardEquipmentSeqNo());
					
				}
				objChangeRequest1058Form.setCheckVersionClause(1);
				strForwardKey = ApplicationConstants.CREATE;
				if(objHttpServletRequest.getParameter("reviseCheck")!=null){
					objChangeRequest1058Form.setReviseClauseCheck(Integer.parseInt((String) objHttpServletRequest.getParameter("reviseCheck")));
					
					if (objChangeRequest1058Form.getReviseClauseCheck() == 1) {
						ArrayList arlReviseClauseDetails = null;
						//ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();						
						//ChangeRequest1058VO objChangeRequest1058VO = new ChangeRequest1058VO();
						objChangeRequest1058VO.setClaChngReqSeq(objChangeRequest1058Form.getClauseChangeRequestSeqNo());
						objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
						objChangeRequest1058VO.setUserID(strUserID);
						
						arlReviseClauseDetails=objChangeRequest1058BI.reviseSelectedClause(objChangeRequest1058VO);

						if (arlReviseClauseDetails != null) {
							objChangeRequest1058Form.setReviseClauseDetailsList(arlReviseClauseDetails);
							ClauseVO objClauseVORev =(ClauseVO)arlReviseClauseDetails.get(0);
							objChangeRequest1058Form.setHdnSectionName(objClauseVORev.getSectionName());
							objChangeRequest1058Form.setHdnSubSectionName(objClauseVORev.getSubSectionName());
							objChangeRequest1058Form.setCheckCompChangeClauses(0);
							objChangeRequest1058Form.setCheckVersionClause(1);
						}
					}
				}
				
			}
			
			StandardEquipVO objStandardEquipVO = new StandardEquipVO();
			StandardEquipBI objStandardEquipBO = ServiceFactory
					.getStandardEquipBO();
			objStandardEquipVO.setUserID(objLoginVo.getUserID());
			arlStandardEquipList = objStandardEquipBO
					.fetchStandardEquipment(objStandardEquipVO);
			
			objChangeRequest1058Form.setStandardEquipList(arlStandardEquipList);
			
			LogUtil.logMessage("Seq No 1058 "
					+ objChangeRequest1058Form.getSeqNo1058());
			
			objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
			
			arlRequest1058List = objChangeRequest1058BI.fetch1058Details(objChangeRequest1058VO);
							
			if (arlRequest1058List != null) {
				LogUtil.logMessage(arlRequest1058List);
				objChangeRequest1058Form.setRequestDetailsList(arlRequest1058List);
			}
			LogUtil.logMessage("ArrayList Value in ChangeRequest1058Action:"
					+ objChangeRequest1058Form.getRequestDetailsList().size());
			
			
			//Request 1058 Categories
			arlCategories = objChangeRequest1058BI.fetch1058RequestCategories(objLoginVo);

			if (arlCategories != null) {
				LogUtil.logMessage("Request Categories "+arlCategories);
				objChangeRequest1058Form.setCategories(arlCategories);
			}

			//Request 1058 Request Types
			arlRequestTypes = objChangeRequest1058BI.fetch1058RequestTypes(objLoginVo);
			
			if (arlRequestTypes != null) {
				LogUtil.logMessage("Request Types "+arlRequestTypes);
				objChangeRequest1058Form.setReqTypes(arlRequestTypes);
			}
			
			//Request LSDB Clause Change Types			
			arlClauseChangeTypes = objChangeRequest1058BI.fetch1058ClauseChangeTypes(objLoginVo);
			
			if (arlClauseChangeTypes != null) {
				LogUtil.logMessage("Clause Change Types "+arlClauseChangeTypes);
				objChangeRequest1058Form.setClauseChangeTypes(arlClauseChangeTypes);
			}
			
			objChangeRequest1058VO.setClaChngReqSeq(0);
			
			//Request LSDB Clause Changes Details
			arlReqSpecChngClauses=objChangeRequest1058BI.fetchLsdbClauseDetails(objChangeRequest1058VO);
			if (arlReqSpecChngClauses != null) {
				LogUtil.logMessage("Requested Specification Change "+arlReqSpecChngClauses);
				objChangeRequest1058Form.setReqSpecChngClauseList(arlReqSpecChngClauses);
			}

			//Request Non LSDB Clause Changes Details				
			ArrayList arlClauseList = objChangeRequest1058BI
					.selectClauseNonLsdb(objChangeRequest1058VO);
			objChangeRequest1058Form.setClaList(arlClauseList);
			LogUtil.logMessage("arlClauseList=" + arlClauseList.size());
			
			//RepresentativeList Details
			//Added For CR-120
			UserVO objUserVO = new UserVO();
			objUserVO.setUserID(objLoginVo.getUserID());
			objUserVO.setOrderbyFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
			arlRepresentativeList = objChangeRequest1058BI.fetchRepresentativeDetails(objUserVO);
				
			if (arlRepresentativeList != null) {
				LogUtil.logMessage(arlRepresentativeList);
				objChangeRequest1058Form.setRepresentativeList(arlRepresentativeList);
			}
			LogUtil.logMessage("ArrayList Value in ChangeRequest1058Action:"
					+ objChangeRequest1058Form.getRepresentativeList().size());

			//Added for CR-127 starts here
			//Model Clause Changes Details
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			arlMdlClaChangesList = objChangeRequest1058BI.fetch1058MdlClaChanges(objChangeRequest1058VO);
			if (arlMdlClaChangesList != null) {
				objChangeRequest1058Form.setMdlClaChangesList(arlMdlClaChangesList);
			}
			LogUtil.logMessage("model clause change list" +arlMdlClaChangesList.size());
			for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
			}
			//Added for CR-127 ends here
			//Added for CR-130 starts here
			//SubSection Details
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			arlImportSubSecList = objChangeRequest1058BI.fetchSubSectionList(objChangeRequest1058VO);
				if (arlImportSubSecList != null) {
					
					objChangeRequest1058Form.setImportSubSecList(arlImportSubSecList);
					objChangeRequest1058Form.setImportSubSecListSize(arlImportSubSecList.size());
				}
			LogUtil.logMessage("Import Sub Section List" +arlImportSubSecList.size());
			
			for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
			}
			
			//1058 SubSection Details
			objChangeRequest1058VO.setSubSecChngReqSeq(0);
			
			for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
			}
			arl1058SubSecList = objChangeRequest1058BI.fetch1058SubSectionList(objChangeRequest1058VO);
				if (arl1058SubSecList != null) {
					
					objChangeRequest1058Form.setSubSec1058List(arl1058SubSecList);
				}
			LogUtil.logMessage("Import Sub Section List" +arl1058SubSecList.size());
			
			for (int cnt = 0; cnt < arl1058SubSecList.size(); cnt++) {
				objChangeRequest1058VO = (ChangeRequest1058VO) arl1058SubSecList.get(cnt);
			}
			//Added for CR-130 ends here
		}
		
		catch (Exception objExp) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
			
		}
		return objActionMapping.findForward(strForwardKey);
	}
	
	

		//Added by @rr108354 for CR-110 Ends here
		
	
	//Added for CR-127 starts here
	public ActionForward addMdlClauseChange(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		LogUtil.logMessage("Enters into ChangeRequest1058Action:addMdlClauseChange()");
		String strForwardKey = ApplicationConstants.CREATE;
		ArrayList arlRequest1058List=new ArrayList();
		ArrayList arlCategories =new ArrayList();
		ArrayList arlRequestTypes =new ArrayList();
		ArrayList arlClauseChangeTypes  =new ArrayList();
		ArrayList arlReqSpecChngClauses=new ArrayList();
		ArrayList arlRepresentativeList = new ArrayList();
		ArrayList arlMdlClaChangesList = new ArrayList();
		ArrayList arl1058SubSecList   = new ArrayList();//Added for CR-130
		ArrayList arlImportSubSecList = new ArrayList();//Added for CR-130

		HttpSession objSession = objHttpServletRequest.getSession(false);
		String strReason=null;
		int updateStatus=0;
		LogUtil.logMessage(objSession);
		int claSeqNumlength =objHttpServletRequest.getParameterValues("mdlClaSeqNo").length;
		int[] claSeqNos = new int[claSeqNumlength];
		String[] tempClaSeqNos =new String[claSeqNumlength];
		int[] claChangeType = new int[claSeqNumlength];
		String[] tempClaChangeType =new String[claSeqNumlength];
		try {
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			ChangeRequest1058Form objChangeRequest1058Form = (ChangeRequest1058Form) objActionForm;
			ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
		
			ChangeRequest1058VO objChangeRequest1058VO =new ChangeRequest1058VO();
						
			tempClaSeqNos = objHttpServletRequest.getParameterValues("mdlClaSeqNo");
			for(int i =0;i<tempClaSeqNos.length;i++){
				if(tempClaSeqNos[i]!=null){
					claSeqNos[i]=Integer.parseInt(tempClaSeqNos[i]);
					LogUtil.logMessage(tempClaSeqNos[i]);
				}
			}
			tempClaChangeType =objHttpServletRequest.getParameterValues("changeTypeSeqNo");
			LogUtil.logMessage("tempClaChangeType"+tempClaChangeType);
			LogUtil.logMessage("length "+tempClaChangeType.length);
			for(int i =0;i<tempClaChangeType.length;i++){
				if(tempClaChangeType[i]!=null){
					claChangeType[i]=Integer.parseInt(tempClaChangeType[i]);
					LogUtil.logMessage(tempClaChangeType[i]);
				}
			}
			
			strReason = objChangeRequest1058Form.getReasonMdl();
				if (strReason != null && !"".equals(strReason)) {
					strReason = ApplicationUtil.trim(strReason);
				}
			
			objChangeRequest1058VO.setClaSeqNo(claSeqNos);
			objChangeRequest1058VO.setClaChangeType(claChangeType);
			objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
			objChangeRequest1058VO.setUserID(objLoginVo.getUserID());
			objChangeRequest1058VO.setReason(strReason);
			
				if (objChangeRequest1058Form.getStatusSeqNo() == ApplicationConstants.PENDING_ONE){
						
						objChangeRequest1058VO.setOrderNo(objChangeRequest1058Form.getOrderNo());
						objChangeRequest1058VO.setCustName(objChangeRequest1058Form.getCustName());
						objChangeRequest1058VO.setMdlName(objChangeRequest1058Form.getMdlName());
						objChangeRequest1058VO.setOrderQty(objChangeRequest1058Form.getOrderQty());
						objChangeRequest1058VO.setReqTypeSeqNo(objChangeRequest1058Form.getReqTypeSeqNo());
						objChangeRequest1058VO.setCategorySeqNo(objChangeRequest1058Form.getCategorySeqNo());
						objChangeRequest1058VO.setGenDesc(objChangeRequest1058Form.getGenDesc());
						objChangeRequest1058VO.setUnitNumber(objChangeRequest1058Form.getUnitNumber());
						objChangeRequest1058VO.setMcrNumber(objChangeRequest1058Form.getMcrNumber());
						objChangeRequest1058VO.setRoadNumber(objChangeRequest1058Form.getRoadNumber());
						objChangeRequest1058VO.setMcrReq(objChangeRequest1058Form.getMcrReq());			
						objChangeRequest1058VO.setSpecRev(objChangeRequest1058Form.getSpecRev());
						objChangeRequest1058VO.setNonLsdbFlag(objChangeRequest1058Form.getHdnNonLsdbFlag());
						
						int intUpdate1058details  = objChangeRequest1058BI.update1058details(objChangeRequest1058VO);
					}
					
					updateStatus=objChangeRequest1058BI.importModelClaChanges(objChangeRequest1058VO);
					
					LogUtil.logMessage("Update Status "+updateStatus);
					if(updateStatus>0){
						objChangeRequest1058Form.setMessageID(String.valueOf(updateStatus));
					}else {
						objChangeRequest1058Form.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
					}
					
					// fetch 1058 Details
					arlRequest1058List = objChangeRequest1058BI.fetch1058Details(objChangeRequest1058VO);
					if (arlRequest1058List != null) {
						LogUtil.logMessage(arlRequest1058List);
						objChangeRequest1058Form.setRequestDetailsList(arlRequest1058List);
					}
									
					objChangeRequest1058Form.setReviseClauseCheck(0);
					objChangeRequest1058Form.setCheckCompChangeClauses(0);
					objChangeRequest1058Form.setCheckVersionClause(0);
					
					LogUtil.logMessage("Requests in ChangeRequest1058Action:"
							+ objChangeRequest1058Form.getRequestDetailsList().size());
					
					//Request 1058 Categories
					arlCategories = objChangeRequest1058BI.fetch1058RequestCategories(objLoginVo);

					if (arlCategories != null) {
						LogUtil.logMessage("Request Categories "+arlCategories);
						objChangeRequest1058Form.setCategories(arlCategories);
					}

					//Request 1058 Request Types
					arlRequestTypes = objChangeRequest1058BI.fetch1058RequestTypes(objLoginVo);
					
					if (arlRequestTypes != null) {
						LogUtil.logMessage("Request Types "+arlRequestTypes);
						objChangeRequest1058Form.setReqTypes(arlRequestTypes);
					}
					
					//Request LSDB Clause Change Types			
					arlClauseChangeTypes = objChangeRequest1058BI.fetch1058ClauseChangeTypes(objLoginVo);
					
					if (arlClauseChangeTypes != null) {
						LogUtil.logMessage("Clause Change Types "+arlClauseChangeTypes);
						objChangeRequest1058Form.setClauseChangeTypes(arlClauseChangeTypes);
					}
					
					objChangeRequest1058VO.setClaChngReqSeq(0);
					
					//Request LSDB Clause Changes Details
					if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.NO)){
						arlReqSpecChngClauses=objChangeRequest1058BI.fetchLsdbClauseDetails(objChangeRequest1058VO);
						if (arlReqSpecChngClauses != null) {
							LogUtil.logMessage("Requested Specification Change "+arlReqSpecChngClauses.size());
							objChangeRequest1058Form.setReqSpecChngClauseList(arlReqSpecChngClauses);
						}
					}

					//Request Non LSDB Clause Changes Details
					if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.YES)){
						
						ArrayList arlClauseList = objChangeRequest1058BI
								.selectClauseNonLsdb(objChangeRequest1058VO);
						objChangeRequest1058Form.setClaList(arlClauseList);
						LogUtil.logMessage("arlClauseList=" + arlClauseList.size());
					}
					
					//RepresentativeList Details
					//Added For CR-120
					UserVO objUserVO = new UserVO();
					objUserVO.setUserID(objLoginVo.getUserID());
					objUserVO.setOrderbyFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
					arlRepresentativeList = objChangeRequest1058BI.fetchRepresentativeDetails(objUserVO);
						
					if (arlRepresentativeList != null) {
						LogUtil.logMessage(arlRepresentativeList);
						objChangeRequest1058Form.setRepresentativeList(arlRepresentativeList);
					}
					LogUtil.logMessage("ArrayList Value in ChangeRequest1058Action:"
							+ objChangeRequest1058Form.getRepresentativeList().size());

					//Model Clause Changes Details
					for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
						}
					
					arlMdlClaChangesList = objChangeRequest1058BI.fetch1058MdlClaChanges(objChangeRequest1058VO);
					if (arlMdlClaChangesList != null) {
						objChangeRequest1058Form.setMdlClaChangesList(arlMdlClaChangesList);
					}
					LogUtil.logMessage("model clause change list" +arlMdlClaChangesList.size());
					for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
					}
					
					//Added for CR-130
					//SubSection Details
					for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
					}
					arlImportSubSecList = objChangeRequest1058BI.fetchSubSectionList(objChangeRequest1058VO);
						if (arlImportSubSecList != null) {
							
							objChangeRequest1058Form.setImportSubSecList(arlImportSubSecList);
							objChangeRequest1058Form.setImportSubSecListSize(arlImportSubSecList.size());
						}
					LogUtil.logMessage("Import Sub Section List" +arlImportSubSecList.size());
					
					for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
					}	
					
					
					//1058 SubSection Details
					objChangeRequest1058VO.setSubSecChngReqSeq(0);
					
					for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
					}
					arl1058SubSecList = objChangeRequest1058BI.fetch1058SubSectionList(objChangeRequest1058VO);
						if (arl1058SubSecList != null) {
							
							objChangeRequest1058Form.setSubSec1058List(arl1058SubSecList);
						}
					LogUtil.logMessage("Import Sub Section List" +arl1058SubSecList.size());
					
					for (int cnt = 0; cnt < arl1058SubSecList.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arl1058SubSecList.get(cnt);
					}
					//Added for CR-130 ends here

			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception Block in ChangeRequest1058Action..");
				ErrorInfo objErrorInfo = new ErrorInfo();
				String strError = objExp.getMessage();
				objErrorInfo.setMessage(strError);
				LogUtil.logMessage("Error Message:" + strError);
				LogUtil.logError(objExp, objErrorInfo);
				strForwardKey = ApplicationConstants.FAILURE;
			}
			return objActionMapping.findForward(strForwardKey);
	}
	
	//Added for CR-127 ends here
	
	//	Added for CR-130 starts here
	
	public ActionForward addMdlSubSecChanges(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		LogUtil.logMessage("Enters into ChangeRequest1058Action:addMdlSubSecChanges()");
		String strForwardKey = ApplicationConstants.CREATE;
		ArrayList arlRequest1058List=new ArrayList();
		ArrayList arlCategories =new ArrayList();
		ArrayList arlRequestTypes =new ArrayList();
		ArrayList arlClauseChangeTypes  =new ArrayList();
		ArrayList arlReqSpecChngClauses=new ArrayList();
		ArrayList arlRepresentativeList = new ArrayList();
		ArrayList arlMdlClaChangesList = new ArrayList();
		ArrayList arl1058SubSecList   = new ArrayList();
		ArrayList arlImportSubSecList = new ArrayList();

		HttpSession objSession = objHttpServletRequest.getSession(false);
		String strReason=null;
		int updateStatus=0;
		LogUtil.logMessage(objSession);
		int subSecSeqNumlength =objHttpServletRequest.getParameterValues("subSecSeqNo").length;
		int[] subSeqNos = new int[subSecSeqNumlength];
		String[] tempSubSecSeqNos =new String[subSecSeqNumlength];
		int[] claChangeType = new int[subSecSeqNumlength];
		String[] tempClaChangeType =new String[subSecSeqNumlength];
		
		try {
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			ChangeRequest1058Form objChangeRequest1058Form = (ChangeRequest1058Form) objActionForm;
			ChangeRequest1058BI objChangeRequest1058BI = ServiceFactory.getChangeRequest1058BO();
		
			ChangeRequest1058VO objChangeRequest1058VO =new ChangeRequest1058VO();
						
			tempSubSecSeqNos = objHttpServletRequest.getParameterValues("subSecSeqNo");
			for(int i =0;i<tempSubSecSeqNos.length;i++){
				if(tempSubSecSeqNos[i]!=null){
					subSeqNos[i]=Integer.parseInt(tempSubSecSeqNos[i]);
					LogUtil.logMessage(tempSubSecSeqNos[i]);
				}
			}
			
			LogUtil.logMessage("secChangeTypeSeqNo value:"
					+ objHttpServletRequest.getParameterValues("secChangeTypeSeqNo"));
			
			tempClaChangeType =objHttpServletRequest.getParameterValues("secChangeTypeSeqNo");
			LogUtil.logMessage("tempClaChangeType"+tempClaChangeType);
			LogUtil.logMessage("length "+tempClaChangeType.length);
			for(int i =0;i<tempClaChangeType.length;i++){
				if(tempClaChangeType[i]!=null){
					claChangeType[i]=Integer.parseInt(tempClaChangeType[i]);
					LogUtil.logMessage("tempClaChangeType[i]"+tempClaChangeType[i]);
				}
			}
			
			strReason = objChangeRequest1058Form.getReasonSubSec();
				if (strReason != null && !"".equals(strReason)) {
					strReason = ApplicationUtil.trim(strReason);
				}
			
			objChangeRequest1058VO.setSubSecSeqNo(subSeqNos);
			objChangeRequest1058VO.setClaChangeType(claChangeType);
			objChangeRequest1058VO.setSeqNo1058(objChangeRequest1058Form.getSeqNo1058());
			objChangeRequest1058VO.setUserID(objLoginVo.getUserID());
			objChangeRequest1058VO.setReason(strReason);
			
				if (objChangeRequest1058Form.getStatusSeqNo() == ApplicationConstants.PENDING_ONE){
						
						objChangeRequest1058VO.setOrderNo(objChangeRequest1058Form.getOrderNo());
						objChangeRequest1058VO.setCustName(objChangeRequest1058Form.getCustName());
						objChangeRequest1058VO.setMdlName(objChangeRequest1058Form.getMdlName());
						objChangeRequest1058VO.setOrderQty(objChangeRequest1058Form.getOrderQty());
						objChangeRequest1058VO.setReqTypeSeqNo(objChangeRequest1058Form.getReqTypeSeqNo());
						objChangeRequest1058VO.setCategorySeqNo(objChangeRequest1058Form.getCategorySeqNo());
						objChangeRequest1058VO.setGenDesc(objChangeRequest1058Form.getGenDesc());
						objChangeRequest1058VO.setUnitNumber(objChangeRequest1058Form.getUnitNumber());
						objChangeRequest1058VO.setMcrNumber(objChangeRequest1058Form.getMcrNumber());
						objChangeRequest1058VO.setRoadNumber(objChangeRequest1058Form.getRoadNumber());
						objChangeRequest1058VO.setMcrReq(objChangeRequest1058Form.getMcrReq());			
						objChangeRequest1058VO.setSpecRev(objChangeRequest1058Form.getSpecRev());
						objChangeRequest1058VO.setNonLsdbFlag(objChangeRequest1058Form.getHdnNonLsdbFlag());
						
						int intUpdate1058details  = objChangeRequest1058BI.update1058details(objChangeRequest1058VO);
					}
					
					updateStatus=objChangeRequest1058BI.importModelSubSectionChanges(objChangeRequest1058VO);
					
					LogUtil.logMessage("Update Status "+updateStatus);
					if(updateStatus>0){
						objChangeRequest1058Form.setMessageID(String.valueOf(updateStatus));
					}else {
						objChangeRequest1058Form.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
					}
					
					// fetch 1058 Details
					arlRequest1058List = objChangeRequest1058BI.fetch1058Details(objChangeRequest1058VO);
					if (arlRequest1058List != null) {
						LogUtil.logMessage(arlRequest1058List);
						objChangeRequest1058Form.setRequestDetailsList(arlRequest1058List);
					}
									
					objChangeRequest1058Form.setReviseClauseCheck(0);
					objChangeRequest1058Form.setCheckCompChangeClauses(0);
					objChangeRequest1058Form.setCheckVersionClause(0);
					
					LogUtil.logMessage("Requests in ChangeRequest1058Action:"
							+ objChangeRequest1058Form.getRequestDetailsList().size());
					
					//Request 1058 Categories
					arlCategories = objChangeRequest1058BI.fetch1058RequestCategories(objLoginVo);

					if (arlCategories != null) {
						LogUtil.logMessage("Request Categories "+arlCategories);
						objChangeRequest1058Form.setCategories(arlCategories);
					}

					//Request 1058 Request Types
					arlRequestTypes = objChangeRequest1058BI.fetch1058RequestTypes(objLoginVo);
					
					if (arlRequestTypes != null) {
						LogUtil.logMessage("Request Types "+arlRequestTypes);
						objChangeRequest1058Form.setReqTypes(arlRequestTypes);
					}
					
					//Request LSDB Clause Change Types			
					arlClauseChangeTypes = objChangeRequest1058BI.fetch1058ClauseChangeTypes(objLoginVo);
					
					if (arlClauseChangeTypes != null) {
						LogUtil.logMessage("Clause Change Types "+arlClauseChangeTypes);
						objChangeRequest1058Form.setClauseChangeTypes(arlClauseChangeTypes);
					}
					
					objChangeRequest1058VO.setClaChngReqSeq(0);
					
					//Request LSDB Clause Changes Details
					if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.NO)){
						arlReqSpecChngClauses=objChangeRequest1058BI.fetchLsdbClauseDetails(objChangeRequest1058VO);
						if (arlReqSpecChngClauses != null) {
							LogUtil.logMessage("Requested Specification Change "+arlReqSpecChngClauses.size());
							objChangeRequest1058Form.setReqSpecChngClauseList(arlReqSpecChngClauses);
						}
					}

					//Request Non LSDB Clause Changes Details
					if (objChangeRequest1058Form.getHdnNonLsdbFlag().equalsIgnoreCase(ApplicationConstants.YES)){
						
						ArrayList arlClauseList = objChangeRequest1058BI
								.selectClauseNonLsdb(objChangeRequest1058VO);
						objChangeRequest1058Form.setClaList(arlClauseList);
						LogUtil.logMessage("arlClauseList=" + arlClauseList.size());
					}
					
					//RepresentativeList Details
					//Added For CR-120
					UserVO objUserVO = new UserVO();
					objUserVO.setUserID(objLoginVo.getUserID());
					objUserVO.setOrderbyFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
					arlRepresentativeList = objChangeRequest1058BI.fetchRepresentativeDetails(objUserVO);
						
					if (arlRepresentativeList != null) {
						LogUtil.logMessage(arlRepresentativeList);
						objChangeRequest1058Form.setRepresentativeList(arlRepresentativeList);
					}
					LogUtil.logMessage("ArrayList Value in ChangeRequest1058Action:"
							+ objChangeRequest1058Form.getRepresentativeList().size());

					//Model Clause Changes Details
					for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
						}
					
					arlMdlClaChangesList = objChangeRequest1058BI.fetch1058MdlClaChanges(objChangeRequest1058VO);
					if (arlMdlClaChangesList != null) {
						objChangeRequest1058Form.setMdlClaChangesList(arlMdlClaChangesList);
					}
					LogUtil.logMessage("model clause change list" +arlMdlClaChangesList.size());
					for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
					}
					
					//SubSection Details
					for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
					}
					arlImportSubSecList = objChangeRequest1058BI.fetchSubSectionList(objChangeRequest1058VO);
						if (arlImportSubSecList != null) {
							
							objChangeRequest1058Form.setImportSubSecList(arlImportSubSecList);
							objChangeRequest1058Form.setImportSubSecListSize(arlImportSubSecList.size());
						}
					LogUtil.logMessage("Import Sub Section List" +arlImportSubSecList.size());
					
					for (int cnt = 0; cnt < arlMdlClaChangesList.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arlMdlClaChangesList.get(cnt);
					}	
					
					
					//1058 SubSection Details
					objChangeRequest1058VO.setSubSecChngReqSeq(0);
					
					for (int cnt = 0; cnt < arlRequest1058List.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arlRequest1058List.get(cnt);
					}
					arl1058SubSecList = objChangeRequest1058BI.fetch1058SubSectionList(objChangeRequest1058VO);
						if (arl1058SubSecList != null) {
							
							objChangeRequest1058Form.setSubSec1058List(arl1058SubSecList);
						}
					LogUtil.logMessage("Import 1058 Sub Section List" +arl1058SubSecList.size());
					
					for (int cnt = 0; cnt < arl1058SubSecList.size(); cnt++) {
						objChangeRequest1058VO = (ChangeRequest1058VO) arl1058SubSecList.get(cnt);
					}

			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception Block in ChangeRequest1058Action..");
				ErrorInfo objErrorInfo = new ErrorInfo();
				String strError = objExp.getMessage();
				objErrorInfo.setMessage(strError);
				LogUtil.logMessage("Error Message:" + strError);
				LogUtil.logError(objExp, objErrorInfo);
				strForwardKey = ApplicationConstants.FAILURE;
			}
			return objActionMapping.findForward(strForwardKey);
	}
	
	//	Added for CR-130 ends here

}
