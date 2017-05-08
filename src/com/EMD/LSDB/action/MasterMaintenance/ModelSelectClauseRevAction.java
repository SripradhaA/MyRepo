/**
 * 
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
import com.EMD.LSDB.bo.serviceinterface.ModelBI;
import com.EMD.LSDB.bo.serviceinterface.ModelClauseBI;
import com.EMD.LSDB.bo.serviceinterface.ModelSelectClauseRevBI;
import com.EMD.LSDB.bo.serviceinterface.SpecTypeBI;
import com.EMD.LSDB.bo.serviceinterface.StandardEquipBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.form.MasterMaintenance.ModelSelectClauseRevForm;
import com.EMD.LSDB.vo.common.ClauseTableDataVO;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.SpecTypeVo;
import com.EMD.LSDB.vo.common.StandardEquipVO;

/**
 * @author VV49326
 *  
 */
/***************************************************************************
 * ------------------------------------------------------------------------------------------------------
 *     Date         version  create by   modify by             comments                              Remarks 
 * 19/01/2010        1.0      SD41630                  updated insertClause method for     Added for CR_81
 * 													   handling null pointer  .   
 * 													 	 
 * 
 * --------------------------------------------------------------------------------------------------------
  **************************************************************************/
public class ModelSelectClauseRevAction extends EMDAction {
	
	public ModelSelectClauseRevAction() {
		
	}
	
	/*******************************************************************************************
	 * Added for LSDB_CR_46 PM&I change
	 * This method is for loading the spec types
	 * @author  Satyam Computer Services Ltd  
	 * @version 1.0  
	 * @param      objActionMapping    
	 * @param 	   objActionForm
	 * @param	   objHttpServletRequest
	 * @param      objHttpServletResponse
	 * @return     ActionForward 
	 * @throws     ApplicationException
	 ******************************************************************************************/
	
	public ActionForward initLoad(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil.logMessage("Entering ModelSelectClauseRevAction.initLoad");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ModelSelectClauseRevForm objModelSelectClauseRevForm = (ModelSelectClauseRevForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		ArrayList arlSpec = null;
		try {
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			arlSpec = fetchSpecType(objSpecTypeVo);
			objModelSelectClauseRevForm.setSpecTypeList(arlSpec);
			
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
	 * * * * This Method is used to populate the Model Dropdown on PageLoad
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward fetchModels(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering ModelSelectClauseRevAction.initLoad");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ModelSelectClauseRevForm objModelSelectClauseRevForm = (ModelSelectClauseRevForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		ArrayList arlSpec = null;
		try {
			
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			ArrayList arlModelList = null;
			ModelBI objModelBo = ServiceFactory.getModelBO();
			
			//Added for LSDB_CR_46
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			arlSpec = fetchSpecType(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpec);
			objModelSelectClauseRevForm.setClauseSeqNo(0);
			//Added for LSDB_CR_46
			objModelSelectClauseRevForm
			.setPrevSpecType(objModelSelectClauseRevForm
					.getSpecTypeNo());
			
			LogUtil.logMessage("objModelSelectClauseRevForm.getSpecTypeNo() :"
					+ objModelSelectClauseRevForm.getSpecTypeNo());
			objModelVo.setSpecTypeSeqNo(objModelSelectClauseRevForm
					.getSpecTypeNo());
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			objModelSelectClauseRevForm.setSpecTypeList(arlSpec);
			
			if (arlModelList != null)
				objModelSelectClauseRevForm.setListModels(arlModelList);
			objModelSelectClauseRevForm.setClauseDes("");
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelSelectClauseRevAction..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
		}
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***************************************************************************
	 * * * * This Method is used to Search all versions of Clause
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward fetchClauseVersions(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil
		.logMessage("Enters into ModelSelectClauseRevAction.fetchClauseVersions");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList arlClauseList = null;
		ArrayList arlAllVersion = null;
		ArrayList arlDefaultVersion = null;
		
		ModelSelectClauseRevForm objModelSelectClauseRevForm = (ModelSelectClauseRevForm) objActionForm;
		objModelSelectClauseRevForm.setClauseDes(objModelSelectClauseRevForm
				.getClauseDes());
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		LogUtil.logMessage(objLoginVo);
		
		//Added for LSDB_CR_46
		objModelSelectClauseRevForm.setPrevSpecType(objModelSelectClauseRevForm
				.getSpecTypeNo());
		
		objModelSelectClauseRevForm
		.setHdPreSelectedModel(objModelSelectClauseRevForm
				.getModelseqno());
		ArrayList arlSpec = null;
		try {
			
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			ArrayList arlModelList = null;
			
			//Added for LSDB_CR_46
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			arlSpec = fetchSpecType(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpec);
			LogUtil.logMessage("objModelSelectClauseRevForm.getSpecTypeNo() :"
					+ objModelSelectClauseRevForm.getSpecTypeNo());
			objModelVo.setSpecTypeSeqNo(objModelSelectClauseRevForm
					.getSpecTypeNo());
			
			objModelSelectClauseRevForm.setSpecTypeList(arlSpec);
			
			ModelBI objModelBo = ServiceFactory.getModelBO();
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null) {
				objModelSelectClauseRevForm.setListModels(arlModelList);
			}
			
			StandardEquipVO objStdEquipmentVO = new StandardEquipVO();
			objStdEquipmentVO.setUserID(objLoginVo.getUserID());
			ClauseVO objClauseVO = new ClauseVO();
			LogUtil.logMessage("ModelSeqNo in FetchClauseVersion"
					+ objModelSelectClauseRevForm.getModelseqno());
			objClauseVO.setModelSeqNo(objModelSelectClauseRevForm
					.getModelseqno());
			LogUtil.logMessage("SubSectionSeqNo in FetchClauseVersion"
					+ objModelSelectClauseRevForm.getSubSectionSeqNo());
			objClauseVO.setSubSectionSeqNo(objModelSelectClauseRevForm
					.getSubSectionSeqNo());
			LogUtil.logMessage("ClauseSeqNo in FetchClauseVersion"
					+ objModelSelectClauseRevForm.getClauseSeqNo());
			objClauseVO.setClauseSeqNo(objModelSelectClauseRevForm
					.getClauseSeqNo());
			
			objClauseVO.setUserID(objLoginVo.getUserID());
			
			ModelSelectClauseRevBI objModelSelectClauseRevBO = ServiceFactory
			.getModelSelectClauseRevBO();
			arlClauseList = objModelSelectClauseRevBO
			.fetchClauseVersions(objClauseVO);
			
			if (arlClauseList != null && arlClauseList.size() > 0) {
				
				arlAllVersion = (ArrayList) arlClauseList.get(0);
				arlDefaultVersion = (ArrayList) arlClauseList.get(1);
				objModelSelectClauseRevForm.setClauseVersions(arlAllVersion);
				objModelSelectClauseRevForm.setClauseList(arlDefaultVersion);
				objModelSelectClauseRevForm.setHdnClauseSeqNo(objClauseVO
						.getClauseSeqNo());
				objModelSelectClauseRevForm
				.setHdnModelName(objModelSelectClauseRevForm
						.getHdnModelName());
				
			}
			
			if (arlClauseList.size() == 0) {
				
				objModelSelectClauseRevForm.setMethod("ClauseVersions");
				
			}
			StandardEquipBI lStandardEquipBO = ServiceFactory
			.getStandardEquipBO();
			ArrayList arlEquipList = lStandardEquipBO
			.fetchStandardEquipment(objStdEquipmentVO);
			objModelSelectClauseRevForm.setStdEquipmentList(arlEquipList);
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelSelectClauseRevAction..");
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
	 * * * * This Method is used to Apply a particular Clause version as default
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward updateApplyDefaultClause(
			ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil
		.logMessage("Entering ModelSelectClauseRevAction.UpdateApplyDefault");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intUpdate = 0;
		ArrayList arlClauseList = null;
		ModelSelectClauseRevForm objModelSelectClauseRevForm = (ModelSelectClauseRevForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		LogUtil.logMessage(objLoginVo);
		ArrayList arlSpec = null;
		
		try {
			
			//Model Load Starts
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			ArrayList arlModelList = null;
			
			ModelBI objModelBo = ServiceFactory.getModelBO();
			
			//Added for LSDB_CR_46
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			arlSpec = fetchSpecType(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpec);
			LogUtil.logMessage("objModelSelectClauseRevForm.getSpecTypeNo() :"
					+ objModelSelectClauseRevForm.getSpecTypeNo());
			objModelVo.setSpecTypeSeqNo(objModelSelectClauseRevForm
					.getSpecTypeNo());
			
			objModelSelectClauseRevForm.setSpecTypeList(arlSpec);
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null) {
				objModelSelectClauseRevForm.setListModels(arlModelList);
			}
			//Model Load Ends here
			
			StandardEquipVO objStdEquipmentVO = new StandardEquipVO();
			objStdEquipmentVO.setUserID(objLoginVo.getUserID());
			
			ClauseVO objClauseVO = new ClauseVO();
			
			objClauseVO.setClauseSeqNo(objModelSelectClauseRevForm
					.getClauseSeqNo());
			LogUtil.logMessage("CSEQNO in ApplyDefault action:"
					+ objModelSelectClauseRevForm.getClauseSeqNo());
			objClauseVO.setVersionNo(objModelSelectClauseRevForm
					.getHdnClauseVersionNo());
			LogUtil.logMessage("versionNo in ApplyDefault action:"
					+ objModelSelectClauseRevForm.getHdnClauseVersionNo());
			objClauseVO.setReason(objModelSelectClauseRevForm.getReason());
			objClauseVO.setUserID(objLoginVo.getUserID());
			
			ModelSelectClauseRevBI objModelSelectClauseRevBO = ServiceFactory
			.getModelSelectClauseRevBO();
			intUpdate = objModelSelectClauseRevBO
			.updateApplyDefaultClause(objClauseVO);
			
			if (intUpdate == 0) {
				objModelSelectClauseRevForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objModelSelectClauseRevForm.setMessageID("" + intUpdate);
			}
			
			//Clause Versions fetch
			ClauseVO objjClauseVO = new ClauseVO();
			
			objjClauseVO.setModelSeqNo(objModelSelectClauseRevForm
					.getModelseqno());
			objjClauseVO.setSubSectionSeqNo(objModelSelectClauseRevForm
					.getSubSectionSeqNo());
			objjClauseVO.setClauseSeqNo(objModelSelectClauseRevForm
					.getClauseSeqNo());
			objjClauseVO.setUserID(objLoginVo.getUserID());
			ModelSelectClauseRevBI objjModelSelectClauseRevBO = ServiceFactory
			.getModelSelectClauseRevBO();
			arlClauseList = objjModelSelectClauseRevBO
			.fetchClauseVersions(objjClauseVO);
			
			if (arlClauseList != null && arlClauseList.size() > 0) {
				objModelSelectClauseRevForm
				.setClauseVersions((ArrayList) arlClauseList.get(0));
				objModelSelectClauseRevForm
				.setClauseList((ArrayList) arlClauseList.get(1));
				//objModelSelectClauseRevForm.setClauseVersions(arlClauseList);
				objModelSelectClauseRevForm.setHdnClauseSeqNo(objClauseVO
						.getClauseSeqNo());
				objModelSelectClauseRevForm
				.setHdnModelName(objModelSelectClauseRevForm
						.getHdnModelName());
				objModelSelectClauseRevForm.setReason("");
				objModelSelectClauseRevForm.setHdnClauseVersionNo(0);
				
			}
			
			if (arlClauseList.size() == 0) {
				objModelSelectClauseRevForm.setMethod("ClauseVersions");
			}
			
			StandardEquipBI lStandardEquipBO = ServiceFactory
			.getStandardEquipBO();
			ArrayList arlEquipList = lStandardEquipBO
			.fetchStandardEquipment(objStdEquipmentVO);
			objModelSelectClauseRevForm.setStdEquipmentList(arlEquipList);
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelSelectClauseRevAction..");
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
	
	public ActionForward insertClause(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		
		LogUtil
		.logMessage("Enters into ModelSelectClauseRevAction:insertClause");
		
		String strForwardKey = ApplicationConstants.SUCCESS;
		String[] arlEDLNos = { "", "", "" };
		String[] arlRefEDLNos = { "", "", "" };
				
		try {
			ModelSelectClauseRevForm objModelSelectClauseRevForm = (ModelSelectClauseRevForm) objActionForm;
			HttpSession objSession = objHttpServletRequest.getSession(false);
			ClauseVO objClauseVO = new ClauseVO();
			String[] clauseTableArray1, clauseTableArray2, clauseTableArray3, clauseTableArray4, clauseTableArray5 = null;
			ClauseTableDataVO objTableDataVO = null;
			ArrayList arlTableList = new ArrayList();
			ArrayList arlModelList = new ArrayList();
			ArrayList arlCompVO = new ArrayList();
			
			int intStatusCode = 0;
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			objModelSelectClauseRevForm
			.setHdPreSelectedModel(objModelSelectClauseRevForm
					.getModelseqno());
			
			objClauseVO.setUserID(objLoginVo.getUserID());
			StandardEquipVO objStandardEquipmentVO = new StandardEquipVO();
			objStandardEquipmentVO.setUserID(objLoginVo.getUserID());
			
			objClauseVO.setModelSeqNo(objModelSelectClauseRevForm
					.getModelseqno());
			objClauseVO.setClauseSeqNo(objModelSelectClauseRevForm
					.getClauseSeqNo());
			ArrayList arlStandardEquipList = new ArrayList();
			objClauseVO.setSubSectionSeqNo(objModelSelectClauseRevForm
					.getSubSectionSeqNo());
			
			objClauseVO.setComponentVO(arlCompVO);
			objClauseVO.setClauseDesc(ApplicationUtil
					.trim(objModelSelectClauseRevForm
							.getClauseDescForTextArea()));
			LogUtil.logMessage("ClauseDesc in ModelSelectClauseRevAction:"
					+ objModelSelectClauseRevForm.getClauseDescForTextArea());
			objClauseVO.setComments(ApplicationUtil
					.trim(objModelSelectClauseRevForm.getComments()));
			LogUtil.logMessage("Comments in ModelSelectClauseRevAction:"
					+ objModelSelectClauseRevForm.getComments());
			objClauseVO.setReason(ApplicationUtil
					.trim(objModelSelectClauseRevForm.getReason()));
			LogUtil.logMessage("Reason in ModelSelectClauseRevAction:"
					+ objModelSelectClauseRevForm.getReason());
			objClauseVO.setPartNumber(objModelSelectClauseRevForm
					.getPartNumber());
			LogUtil.logMessage("PartNumber in ModelSelectClauseRevAction:"
					+ objModelSelectClauseRevForm.getPartNumber());
			objClauseVO.setPriceBookNumber(objModelSelectClauseRevForm
					.getPriceBookNumber());
			LogUtil.logMessage("PriceBookNumber in ModelSelectClauseRevAction:"
					+ objModelSelectClauseRevForm.getPriceBookNumber());
			objClauseVO
			.setDwONumber(objModelSelectClauseRevForm.getDwONumber());
			LogUtil.logMessage("DwONumber in ModelSelectClauseRevAction:"
					+ objModelSelectClauseRevForm.getDwONumber());
//			 Added For CR_81 on 24-Dec-09 by  SD41630------- -->
			if(objModelSelectClauseRevForm.getRefEDLNo()!=null && objModelSelectClauseRevForm.getRefEDLNo().length>0)
			{
					
			objClauseVO.setRefEDLNo(objModelSelectClauseRevForm.getRefEDLNo());
			}else
			{							
				LogUtil.logMessage("RefEdlno in side if null in ModelSelectClauseRevAction: length" + arlRefEDLNos.length);
										
				objClauseVO.setRefEDLNo(arlRefEDLNos);
				//objClauseVO.setRefEDLNo(new String[]{"","",""});
					
			}
				
				
			LogUtil.logMessage("RefEDLNo in ModelSelectClauseRevAction:");
			if(objModelSelectClauseRevForm.getEdlNo()!=null && objModelSelectClauseRevForm.getEdlNo().length>0)
			{
			objClauseVO.setEdlNo(objModelSelectClauseRevForm.getEdlNo());
			}
			else{
				LogUtil.logMessage("Edlno in side if null in ModelSelectClauseRevAction: length" + arlEDLNos.length);
				objClauseVO.setEdlNo(arlEDLNos);
				
			}
//			 Added For CR_81 on 24-Dec-09 by SD 41630------- - Ends here-->
			LogUtil.logMessage("EdlNo in ModelSelectClauseRevAction:");
			objClauseVO.setPartOfCode(objModelSelectClauseRevForm
					.getPartOfCode());
			LogUtil.logMessage("PartOfCode in ModelSelectClauseRevAction:"
					+ objModelSelectClauseRevForm.getPartOfCode().length);
			objClauseVO.setPartOfSeqNo(objModelSelectClauseRevForm
					.getPartOfSeqNo());
			
			LogUtil.logMessage("PartOfSeqNo in ModelSelectClauseRevAction:"
					+ objModelSelectClauseRevForm.getPartOfSeqNo().length);
			
			/**
			 * PartOfClaSeqNo is added for LSDB_CR-48(Part Of CR) Added on
			 * 04-Aug-08 by ps57222
			 */
			
			objClauseVO.setPartOfClaSeqNo(objModelSelectClauseRevForm
					.getPartOfclaSeqNo());
			
			LogUtil.logMessage("PartOfClaSeqNo in ModelSelectClauseRevAction:"
					+ objModelSelectClauseRevForm.getPartOfclaSeqNo().length);
			
			objClauseVO.setDefaultFlag(objModelSelectClauseRevForm
					.getApplyToDefault());
			
			LogUtil
			.logMessage("APply as Default flag in ModelSelectClauseRevAction:"
					+ objModelSelectClauseRevForm.getPartOfSeqNo().length);
			
			/*******************************************************************
			 * Added For LSDB_CR-35 Added on 04-April-08 by ps57222
			 *  
			 ******************************************************************/
			
			objClauseVO
			.setClauseSource(ApplicationConstants.CLAUSE_SOURCE_MODEL);
			
			int standardEquipSeqNo = objModelSelectClauseRevForm
			.getStandardEquipmentSeqNo();
			if (standardEquipSeqNo != -1) {
				objStandardEquipmentVO
				.setStandardEquipmentSeqNo(objModelSelectClauseRevForm
						.getStandardEquipmentSeqNo());
				arlStandardEquipList.add(objStandardEquipmentVO);
				objClauseVO.setObjStandardEquipVO(arlStandardEquipList);
			} else {
				objClauseVO.setObjStandardEquipVO(null);
			}
			clauseTableArray1 = objModelSelectClauseRevForm
			.getClauseTableDataArray1();
			
			if (clauseTableArray1 == null || clauseTableArray1.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData1(null);
			} else {
				
				for (int counter = 0; counter < clauseTableArray1.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO
					.setTableArrayData1(objModelSelectClauseRevForm
							.getClauseTableDataArray1());
					LogUtil.logMessage("Table Data Value:"
							+ objModelSelectClauseRevForm
							.getClauseTableDataArray1()[counter]);
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
				
			}
			arlTableList.add(objTableDataVO);
			
			clauseTableArray2 = objModelSelectClauseRevForm
			.getClauseTableDataArray2();
			if (clauseTableArray2 == null || clauseTableArray2.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData2(null);
			} else {
				for (int counter = 0; counter < clauseTableArray2.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO
					.setTableArrayData2(objModelSelectClauseRevForm
							.getClauseTableDataArray2());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
				
			}
			arlTableList.add(objTableDataVO);
			
			clauseTableArray3 = objModelSelectClauseRevForm
			.getClauseTableDataArray3();
			
			if (clauseTableArray3 == null || clauseTableArray3.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData3(null);
			} else {
				for (int counter = 0; counter < clauseTableArray3.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO
					.setTableArrayData3(objModelSelectClauseRevForm
							.getClauseTableDataArray3());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
				
			}
			arlTableList.add(objTableDataVO);
			clauseTableArray4 = objModelSelectClauseRevForm
			.getClauseTableDataArray4();
			
			if (clauseTableArray4 == null || clauseTableArray4.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData4(null);
			} else {
				for (int counter = 0; counter < clauseTableArray4.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO
					.setTableArrayData4(objModelSelectClauseRevForm
							.getClauseTableDataArray4());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
				
			}
			arlTableList.add(objTableDataVO);
			clauseTableArray5 = objModelSelectClauseRevForm
			.getClauseTableDataArray5();
			
			if (clauseTableArray5 == null || clauseTableArray5.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData5(null);
			} else {
				for (int counter = 0; counter < clauseTableArray5.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO
					.setTableArrayData5(objModelSelectClauseRevForm
							.getClauseTableDataArray5());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
			}
			
			arlTableList.add(objTableDataVO);
			objClauseVO.setTableDataVO(arlTableList);
			
			ModelClauseBI lModelClauseBO = ServiceFactory.getModelClauseBO();
			intStatusCode = lModelClauseBO.insertClause(objClauseVO);
			
			if (intStatusCode == 0) {
				objModelSelectClauseRevForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				//objModelSelectClauseRevForm
				//	.setModelseqno(ApplicationConstants.Reset_Dropdown);
			}
			ModelVo objModelVo = new ModelVo();
			
			//Added for LSDB_CR_46
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			arlSpec = fetchSpecType(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpec);
			LogUtil.logMessage("objModelSelectClauseRevForm.getSpecTypeNo() :"
					+ objModelSelectClauseRevForm.getSpecTypeNo());
			objModelVo.setSpecTypeSeqNo(objModelSelectClauseRevForm
					.getSpecTypeNo());
			
			objModelSelectClauseRevForm.setSpecTypeList(arlSpec);
			
			objModelVo.setUserID(objLoginVo.getUserID());
			ModelBI objModelBO = ServiceFactory.getModelBO();
			if (objModelBO.fetchModels(objModelVo) != null) {
				arlModelList = objModelBO.fetchModels(objModelVo);
				objModelSelectClauseRevForm.setListModels(arlModelList);
				
			}
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelSelectClauseRevAction..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***************************************************************************
	 * * * * This Method is used to Delete a clause and all it's subclauses with
	 * versions
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward deleteClause(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil.logMessage("Entering ModelSelectClauseRevAction.deleteClause");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intDelete = 0;
		ArrayList arlClauseList = null;
		ModelSelectClauseRevForm objModelSelectClauseRevForm = (ModelSelectClauseRevForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		LogUtil.logMessage(objLoginVo);
		ArrayList arlSpec = null;
		
		try {
			
			//Model Load Starts
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			ArrayList arlModelList = null;
			
			ModelBI objModelBo = ServiceFactory.getModelBO();
			
			//Added for LSDB_CR_46
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			arlSpec = fetchSpecType(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpec);
			LogUtil.logMessage("objModelSelectClauseRevForm.getSpecTypeNo() :"
					+ objModelSelectClauseRevForm.getSpecTypeNo());
			objModelVo.setSpecTypeSeqNo(objModelSelectClauseRevForm
					.getSpecTypeNo());
			
			objModelSelectClauseRevForm.setSpecTypeList(arlSpec);
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null) {
				objModelSelectClauseRevForm.setListModels(arlModelList);
			}
			//Model Load Ends here
			
			ClauseVO objClauseVO = new ClauseVO();
			
			objClauseVO.setClauseSeqNo(objModelSelectClauseRevForm
					.getClauseSeqNo());
			LogUtil.logMessage("CSEQNO in deleteClause action:"
					+ objModelSelectClauseRevForm.getClauseSeqNo());
			
			objClauseVO.setUserID(objLoginVo.getUserID());
			
			ModelSelectClauseRevBI objModelSelectClauseRevBO = ServiceFactory
			.getModelSelectClauseRevBO();
			intDelete = objModelSelectClauseRevBO.deleteClause(objClauseVO);
			
			if (intDelete == 0) {
				objModelSelectClauseRevForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				objModelSelectClauseRevForm.setClauseSeqNo(0);
				objModelSelectClauseRevForm.setSubSectionSeqNo(0);
				objModelSelectClauseRevForm.setClauseDes("");
			} else {
				objModelSelectClauseRevForm.setMessageID("" + intDelete);
			}
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelSelectClauseRevAction:deleteClause..");
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
	
	/***************************************************************************
	 * * * * This Method is used to Delete a clause and all it's subclauses with
	 * versions
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward deleteVersion(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil.logMessage("Entering ModelSelectClauseRevAction.deleteVersion");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intDelete = 0;
		ArrayList arlClauseList = null;
		ArrayList arlAllVersion = null;
		ArrayList arlDefaultVersion = null;
		
		ModelSelectClauseRevForm objModelSelectClauseRevForm = (ModelSelectClauseRevForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		LogUtil.logMessage(objLoginVo);
		
		ArrayList arlSpec = null;
		try {
			
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			ArrayList arlModelList = null;
			
			ModelBI objModelBo = ServiceFactory.getModelBO();
			
			//Added for LSDB_CR_46
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			arlSpec = fetchSpecType(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpec);
			LogUtil.logMessage("objModelSelectClauseRevForm.getSpecTypeNo() :"
					+ objModelSelectClauseRevForm.getSpecTypeNo());
			objModelVo.setSpecTypeSeqNo(objModelSelectClauseRevForm
					.getSpecTypeNo());
			
			objModelSelectClauseRevForm.setSpecTypeList(arlSpec);
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null) {
				objModelSelectClauseRevForm.setListModels(arlModelList);
			}
			
			StandardEquipVO objStdEquipmentVO = new StandardEquipVO();
			objStdEquipmentVO.setUserID(objLoginVo.getUserID());
			
			ClauseVO objClauseVO = new ClauseVO();
			
			objClauseVO.setClauseSeqNo(objModelSelectClauseRevForm
					.getClauseSeqNo());
			LogUtil
			.logMessage("ClauseSeqNo in ModelSelectClauseRevAction.deleteVersion:"
					+ objModelSelectClauseRevForm.getClauseSeqNo());
			LogUtil
			.logMessage("VersionNo in ModelSelectClauseRevAction.deleteVersion:"
					+ objModelSelectClauseRevForm
					.getHdnClauseVersionNo());
			objClauseVO.setVersionNo(objModelSelectClauseRevForm
					.getHdnClauseVersionNo());
			objClauseVO.setUserID(objLoginVo.getUserID());
			
			ModelSelectClauseRevBI objModelSelectClauseRevBO = ServiceFactory
			.getModelSelectClauseRevBO();
			intDelete = objModelSelectClauseRevBO.deleteVersion(objClauseVO);
			
			if (intDelete == 0) {
				
				objModelSelectClauseRevForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			}
			
			if (intDelete > 0) {
				objModelSelectClauseRevForm.setMessageID("" + intDelete);
			}
			
			ClauseVO objFetchClauseVO = new ClauseVO();
			objFetchClauseVO.setClauseSeqNo(objModelSelectClauseRevForm
					.getClauseSeqNo());
			objFetchClauseVO.setModelSeqNo(objModelSelectClauseRevForm
					.getModelseqno());
			objFetchClauseVO.setSubSectionSeqNo(objModelSelectClauseRevForm
					.getSubSectionSeqNo());
			objFetchClauseVO.setUserID(objLoginVo.getUserID());
			
			arlClauseList = objModelSelectClauseRevBO
			.fetchClauseVersions(objFetchClauseVO);
			
			if (arlClauseList != null && arlClauseList.size() > 0) {
				
				arlAllVersion = (ArrayList) arlClauseList.get(0);
				arlDefaultVersion = (ArrayList) arlClauseList.get(1);
				objModelSelectClauseRevForm.setClauseVersions(arlAllVersion);
				objModelSelectClauseRevForm.setClauseList(arlDefaultVersion);
				objModelSelectClauseRevForm.setHdnClauseSeqNo(objClauseVO
						.getClauseSeqNo());
				objModelSelectClauseRevForm
				.setHdnModelName(objModelSelectClauseRevForm
						.getHdnModelName());
				
			}
			
			if (arlClauseList.size() == 0) {
				
				objModelSelectClauseRevForm.setMethod("ClauseVersions");
				
			}
			StandardEquipBI lStandardEquipBO = ServiceFactory
			.getStandardEquipBO();
			ArrayList arlEquipList = lStandardEquipBO
			.fetchStandardEquipment(objStdEquipmentVO);
			objModelSelectClauseRevForm.setStdEquipmentList(arlEquipList);
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelSelectClauseRevAction:deleteVersion..");
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
	
	//	 Added for LSDB_CR_53 - Update of Clause/sub clause version
	/***************************************************************************
	 * * * * This Method is used to Search only Selected version of a Clause
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward fetchSelectedClauseVersion(
			ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil
		.logMessage("Enters into ModelSelectClauseRevAction.fetchSelectedClauseVersion");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList arlClauseList = null;
		ArrayList arlAllVersion = null;
		//ArrayList arlDefaultVersion=null;
		ArrayList arlSpec = null;
		
		ModelSelectClauseRevForm objModelSelectClauseRevForm = (ModelSelectClauseRevForm) objActionForm;
		objModelSelectClauseRevForm.setClauseDes(objModelSelectClauseRevForm
				.getClauseDes());
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		LogUtil.logMessage(objLoginVo);
		objModelSelectClauseRevForm
		.setHdPreSelectedModel(objModelSelectClauseRevForm
				.getModelseqno());
		try {
			
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			ArrayList arlModelList = null;
			
			ModelBI objModelBo = ServiceFactory.getModelBO();
			
			//Added for LSDB_CR_46
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			arlSpec = fetchSpecType(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpec);
			LogUtil.logMessage("objModelSelectClauseRevForm.getSpecTypeNo() :"
					+ objModelSelectClauseRevForm.getSpecTypeNo());
			objModelVo.setSpecTypeSeqNo(objModelSelectClauseRevForm
					.getSpecTypeNo());
			
			objModelSelectClauseRevForm.setSpecTypeList(arlSpec);
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null) {
				objModelSelectClauseRevForm.setListModels(arlModelList);
			}
			
			StandardEquipVO objStdEquipmentVO = new StandardEquipVO();
			objStdEquipmentVO.setUserID(objLoginVo.getUserID());
			ClauseVO objClauseVO = new ClauseVO();
			
			objClauseVO.setModelSeqNo(objModelSelectClauseRevForm
					.getModelseqno());
			
			objClauseVO.setSubSectionSeqNo(objModelSelectClauseRevForm
					.getSubSectionSeqNo());
			
			objClauseVO.setClauseSeqNo(objModelSelectClauseRevForm
					.getClauseSeqNo());
			
			objClauseVO.setUserID(objLoginVo.getUserID());
			
			ModelSelectClauseRevBI objModelSelectClauseRevBO = ServiceFactory
			.getModelSelectClauseRevBO();
			arlClauseList = objModelSelectClauseRevBO
			.fetchClauseVersions(objClauseVO);
			
			if (arlClauseList != null && arlClauseList.size() > 0) {
				
				arlAllVersion = (ArrayList) arlClauseList.get(0);
				LogUtil.logMessage("Clause Desc" + arlAllVersion.size());
				objModelSelectClauseRevForm.setClauseVersions(arlAllVersion);
				for (int i = 0; i < arlAllVersion.size(); i++) {
					ClauseVO objLoadVersion = (ClauseVO) arlAllVersion.get(i);
					
					LogUtil.logMessage("Version in ArrayList"
							+ objLoadVersion.getVersionNo()
							+ "I value "
							+ i
							+ "Form Version"
							+ objModelSelectClauseRevForm
							.getHdnClauseVersionNo());
					if (objLoadVersion.getVersionNo() == objModelSelectClauseRevForm
							.getHdnClauseVersionNo()) {
						LogUtil.logMessage("Clause Desc");
						ArrayList arlLoadVersion = new ArrayList();
						arlLoadVersion.add(objLoadVersion);
						objModelSelectClauseRevForm
						.setClauseList(arlLoadVersion);
						break;
					}
				}
				
				objModelSelectClauseRevForm.setHdnClauseSeqNo(objClauseVO
						.getClauseSeqNo());
				objModelSelectClauseRevForm
				.setHdnModelName(objModelSelectClauseRevForm
						.getHdnModelName());
				
			}
			
			if (arlClauseList.size() == 0) {
				
				objModelSelectClauseRevForm.setMethod("SelectedClauseVersion");
				
			}
			StandardEquipBI lStandardEquipBO = ServiceFactory
			.getStandardEquipBO();
			ArrayList arlEquipList = lStandardEquipBO
			.fetchStandardEquipment(objStdEquipmentVO);
			objModelSelectClauseRevForm.setStdEquipmentList(arlEquipList);
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in SelectedClauseVersion..");
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
	 * * * * This Method is used to update Clause detailsof a Clause
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward updateClause(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		
		LogUtil
		.logMessage("Enters into ModelSelectClauseRevAction:updateClause");
		
		String strForwardKey = ApplicationConstants.SUCCESS;
		try {
			ModelSelectClauseRevForm objModelSelectClauseRevForm = (ModelSelectClauseRevForm) objActionForm;
			HttpSession objSession = objHttpServletRequest.getSession(false);
			ClauseVO objClauseVO = new ClauseVO();
			String[] clauseTableArray1, clauseTableArray2, clauseTableArray3, clauseTableArray4, clauseTableArray5 = null;
			ClauseTableDataVO objTableDataVO = null;
			ArrayList arlTableList = new ArrayList();
			ArrayList arlModelList = new ArrayList();
			ArrayList arlCompVO = new ArrayList();
			ArrayList arlSpec = null;
			int intStatusCode = 0;
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			objModelSelectClauseRevForm
			.setHdPreSelectedModel(objModelSelectClauseRevForm
					.getModelseqno());
			
			objClauseVO.setUserID(objLoginVo.getUserID());
			StandardEquipVO objStandardEquipmentVO = new StandardEquipVO();
			objStandardEquipmentVO.setUserID(objLoginVo.getUserID());
			
			LogUtil.logMessage("ClauseSeqNo in ModelSelectClauseRevAction:"
					+ objModelSelectClauseRevForm.getClauseSeqNo());
			objClauseVO.setClauseSeqNo(objModelSelectClauseRevForm
					.getClauseSeqNo());
			LogUtil.logMessage("VersionNo in ModelSelectClauseRevAction:"
					+ objModelSelectClauseRevForm.getHdnClauseVersionNo());
			objClauseVO.setVersionNo(objModelSelectClauseRevForm
					.getHdnClauseVersionNo());
			ArrayList arlStandardEquipList = new ArrayList();
			
			objClauseVO.setClauseDesc(ApplicationUtil
					.trim(objModelSelectClauseRevForm
							.getClauseDescForTextArea()));
			LogUtil.logMessage("ClauseDesc in ModelSelectClauseRevAction:"
					+ objModelSelectClauseRevForm.getClauseDescForTextArea());
			objClauseVO.setComments(ApplicationUtil
					.trim(objModelSelectClauseRevForm.getComments()));
			LogUtil.logMessage("Comments in ModelSelectClauseRevAction:"
					+ objModelSelectClauseRevForm.getComments());
			
			objClauseVO.setPartNumber(objModelSelectClauseRevForm
					.getPartNumber());
			LogUtil.logMessage("PartNumber in ModelSelectClauseRevAction:"
					+ objModelSelectClauseRevForm.getPartNumber());
			objClauseVO.setPriceBookNumber(objModelSelectClauseRevForm
					.getPriceBookNumber());
			LogUtil.logMessage("PriceBookNumber in ModelSelectClauseRevAction:"
					+ objModelSelectClauseRevForm.getPriceBookNumber());
			objClauseVO
			.setDwONumber(objModelSelectClauseRevForm.getDwONumber());
			LogUtil.logMessage("DwONumber in ModelSelectClauseRevAction:"
					+ objModelSelectClauseRevForm.getDwONumber());
			objClauseVO.setRefEDLNo(objModelSelectClauseRevForm.getRefEDLNo());
			LogUtil.logMessage("RefEDLNo in ModelSelectClauseRevAction:"
					+ objModelSelectClauseRevForm.getRefEDLNo().length);
			objClauseVO.setEdlNo(objModelSelectClauseRevForm.getEdlNo());
			LogUtil.logMessage("EdlNo in ModelSelectClauseRevAction:"
					+ objModelSelectClauseRevForm.getEdlNo().length);
			objClauseVO.setPartOfCode(objModelSelectClauseRevForm
					.getPartOfCode());
			LogUtil.logMessage("PartOfCode in ModelSelectClauseRevAction:"
					+ objModelSelectClauseRevForm.getPartOfCode().length);
			objClauseVO.setPartOfSeqNo(objModelSelectClauseRevForm
					.getPartOfSeqNo());
			LogUtil.logMessage("PartOfSeqNo in ModelSelectClauseRevAction:"
					+ objModelSelectClauseRevForm.getPartOfSeqNo().length);
			
			objClauseVO.setDefaultFlag(objModelSelectClauseRevForm
					.getApplyToDefault());
			
			LogUtil
			.logMessage("APply as Default flag in ModelSelectClauseRevAction:"
					+ objModelSelectClauseRevForm.getPartOfSeqNo().length);
			
			int standardEquipSeqNo = objModelSelectClauseRevForm
			.getStandardEquipmentSeqNo();
			if (standardEquipSeqNo != -1) {
				objStandardEquipmentVO
				.setStandardEquipmentSeqNo(objModelSelectClauseRevForm
						.getStandardEquipmentSeqNo());
				arlStandardEquipList.add(objStandardEquipmentVO);
				objClauseVO.setObjStandardEquipVO(arlStandardEquipList);
			} else {
				objClauseVO.setObjStandardEquipVO(null);
			}
			clauseTableArray1 = objModelSelectClauseRevForm
			.getClauseTableDataArray1();
			
			if (clauseTableArray1 == null || clauseTableArray1.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData1(null);
			} else {
				
				for (int counter = 0; counter < clauseTableArray1.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO
					.setTableArrayData1(objModelSelectClauseRevForm
							.getClauseTableDataArray1());
					LogUtil.logMessage("Table Data Value:"
							+ objModelSelectClauseRevForm
							.getClauseTableDataArray1()[counter]);
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
				
			}
			arlTableList.add(objTableDataVO);
			
			clauseTableArray2 = objModelSelectClauseRevForm
			.getClauseTableDataArray2();
			if (clauseTableArray2 == null || clauseTableArray2.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData2(null);
			} else {
				for (int counter = 0; counter < clauseTableArray2.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO
					.setTableArrayData2(objModelSelectClauseRevForm
							.getClauseTableDataArray2());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
				
			}
			arlTableList.add(objTableDataVO);
			
			clauseTableArray3 = objModelSelectClauseRevForm
			.getClauseTableDataArray3();
			
			if (clauseTableArray3 == null || clauseTableArray3.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData3(null);
			} else {
				for (int counter = 0; counter < clauseTableArray3.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO
					.setTableArrayData3(objModelSelectClauseRevForm
							.getClauseTableDataArray3());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
				
			}
			arlTableList.add(objTableDataVO);
			clauseTableArray4 = objModelSelectClauseRevForm
			.getClauseTableDataArray4();
			
			if (clauseTableArray4 == null || clauseTableArray4.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData4(null);
			} else {
				for (int counter = 0; counter < clauseTableArray4.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO
					.setTableArrayData4(objModelSelectClauseRevForm
							.getClauseTableDataArray4());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
				
			}
			arlTableList.add(objTableDataVO);
			clauseTableArray5 = objModelSelectClauseRevForm
			.getClauseTableDataArray5();
			
			if (clauseTableArray5 == null || clauseTableArray5.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData5(null);
			} else {
				for (int counter = 0; counter < clauseTableArray5.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO
					.setTableArrayData5(objModelSelectClauseRevForm
							.getClauseTableDataArray5());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
			}
			
			arlTableList.add(objTableDataVO);
			objClauseVO.setTableDataVO(arlTableList);
			
			ModelSelectClauseRevBI objModelSelectClauseRevBO = ServiceFactory
			.getModelSelectClauseRevBO();
			intStatusCode = objModelSelectClauseRevBO
			.updateClauseVersion(objClauseVO);
			
			LogUtil.logMessage("Status Code in update Action:" + intStatusCode);
			if (intStatusCode == 0) {
				objModelSelectClauseRevForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objModelSelectClauseRevForm.setMessageID("" + intStatusCode);
			}
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			ModelBI objModelBO = ServiceFactory.getModelBO();
			//Added for LSDB_CR_46
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			arlSpec = fetchSpecType(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpec);
			LogUtil.logMessage("objModelSelectClauseRevForm.getSpecTypeNo() :"
					+ objModelSelectClauseRevForm.getSpecTypeNo());
			objModelVo.setSpecTypeSeqNo(objModelSelectClauseRevForm
					.getSpecTypeNo());
			
			objModelSelectClauseRevForm.setSpecTypeList(arlSpec);
			
			if (objModelBO.fetchModels(objModelVo) != null) {
				arlModelList = objModelBO.fetchModels(objModelVo);
				objModelSelectClauseRevForm.setListModels(arlModelList);
				
			}
			
		}
		
		catch (Exception objExp) {
			objExp.printStackTrace();
			LogUtil
			.logMessage("Enters into Exception Block in ModelSelectClauseRevAction..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***************************************************************************
	 * Added for LSDB_CR_46
	 * This method is for loading fetchSpecType List.
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSpecTypeVo
	 * @return ArrayList
	 * @throws BusinessException
	 **************************************************************************/
	
	private ArrayList fetchSpecType(SpecTypeVo objSpecTypeVo) throws Exception {
		ArrayList arlSpecType = new ArrayList();
		SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
		arlSpecType = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
		return arlSpecType;
	}
	
}