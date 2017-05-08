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
import com.EMD.LSDB.bo.serviceinterface.ModelAddClauseRevBI;
import com.EMD.LSDB.bo.serviceinterface.ModelBI;
import com.EMD.LSDB.bo.serviceinterface.StandardEquipBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.form.MasterMaintenance.ModelAddClauseRevForm;
import com.EMD.LSDB.vo.common.ClauseTableDataVO;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.StandardEquipVO;

/**
 * @author ps57222
 * 
 */
public class ModelAddClauseRevAction extends EMDAction {
	
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
	
	public ActionForward initLoad(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil.logMessage("Enters into ModelAddClauseRevAction:initLoad");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList arlModelList = new ArrayList();
		
		ModelAddClauseRevForm objModAddClauseForm = (ModelAddClauseRevForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		
		try {
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			ModelBI objModelBO = ServiceFactory.getModelBO();
			if (objModelBO.fetchModels(objModelVo) != null) {
				arlModelList = objModelBO.fetchModels(objModelVo);
				objModAddClauseForm.setModelList(arlModelList);
				
			}
		} catch (BusinessException objBusExp) {
			LogUtil
			.logMessage("Enters into BusinessException Block in ModelAddClauseRevAction..");
			String strError = objBusExp.getErrorInfo().getMessageID();
			LogUtil.logMessage("Error Message:" + strError);
			if (strError != null) {
				objModAddClauseForm.setMessageID(strError);
				return objActionMapping.getInputForward();
			}
			
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception Block in Action..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objErrorInfo.getMessage();
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***************************************************************************
	 * * * * This Method is used to Fetch the Clause Details for Selected Clause
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 * 
	 **************************************************************************/
	
	public ActionForward fetchClauses(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil.logMessage("Enters into ModelAddClauseRevAction:fetchClauses");
		String strForwardKey = ApplicationConstants.SUCCESS;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		ModelAddClauseRevForm objModelAddClauseRevForm = (ModelAddClauseRevForm) objActionForm;
		ArrayList arlModelList = new ArrayList();
		ArrayList arlEquipList = new ArrayList();
		ArrayList arlClauseList = null;
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		LogUtil.logMessage(objLoginVo);
		
		StandardEquipVO objStdEquipmentVO = new StandardEquipVO();
		objStdEquipmentVO.setUserID(objLoginVo.getUserID());
		ClauseVO objClauseVO = new ClauseVO();
		objModelAddClauseRevForm.setHdPreSelectedModel(objModelAddClauseRevForm
				.getModelSeqNo());
		
		objClauseVO.setClauseSeqNo(objModelAddClauseRevForm.getClauseSeqNo());
		objClauseVO.setUserID(objLoginVo.getUserID());
		objClauseVO.setModelSeqNo(objModelAddClauseRevForm.getModelSeqNo());
		objClauseVO.setSubSectionSeqNo(objModelAddClauseRevForm
				.getSubSectionSeqNo());
		objClauseVO.setVersionNo(objModelAddClauseRevForm.getVersionNo());
		
		try {
			ModelAddClauseRevBI objModelAddClauseRevBI = ServiceFactory
			.getModelAddClauseRevBO();
			arlClauseList = objModelAddClauseRevBI.fetchClauses(objClauseVO);
			LogUtil
			.logMessage("ClauseVo value in ModelAddClauseRevAction:fetchClauses"
					+ arlClauseList.size());
			if (arlClauseList != null) {
				objModelAddClauseRevForm.setClauseList(arlClauseList);
			}
			
			LogUtil
			.logMessage("ClauseVo value in ModelAddClauseRevAction:fetchClauses"
					+ objModelAddClauseRevForm.getClauseList().size());
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			
			ModelBI objModelBO = ServiceFactory.getModelBO();
			if (objModelBO.fetchModels(objModelVo) != null) {
				arlModelList = objModelBO.fetchModels(objModelVo);
				objModelAddClauseRevForm.setModelList(arlModelList);
				
			}
			
			StandardEquipBI lStandardEquipBO = ServiceFactory
			.getStandardEquipBO();
			arlEquipList = lStandardEquipBO
			.fetchStandardEquipment(objStdEquipmentVO);
			objModelAddClauseRevForm.setStdEquipmentList(arlEquipList);
			
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelAddClauseRevAction");
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
	 * * * * This Method is used to Add new version for Selected Clause
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 * 
	 **************************************************************************/
	
	public ActionForward insertClause(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		
		LogUtil.logMessage("Enters into ModelAddClauseRevAction:insertClause");
		
		String strForwardKey = ApplicationConstants.SUCCESS;
		try {
			ModelAddClauseRevForm objModelAddClauseRevForm = (ModelAddClauseRevForm) objActionForm;
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
			objModelAddClauseRevForm
			.setHdPreSelectedModel(objModelAddClauseRevForm
					.getModelSeqNo());
			
			objClauseVO.setUserID(objLoginVo.getUserID());
			StandardEquipVO objStandardEquipmentVO = new StandardEquipVO();
			objStandardEquipmentVO.setUserID(objLoginVo.getUserID());
			
			objClauseVO.setModelSeqNo(objModelAddClauseRevForm.getModelSeqNo());
			objClauseVO.setClauseSeqNo(objModelAddClauseRevForm
					.getClauseSeqNo());
			ArrayList arlStandardEquipList = new ArrayList();
			objClauseVO.setSubSectionSeqNo(objModelAddClauseRevForm
					.getSubSectionSeqNo());
			
			objClauseVO.setComponentVO(arlCompVO);
			objClauseVO.setClauseDesc(ApplicationUtil
					.trim(objModelAddClauseRevForm.getClauseDescForTextArea()));
			LogUtil.logMessage("ClauseDesc in ModelAddClauseRevAction:"
					+ objModelAddClauseRevForm.getClauseDescForTextArea());
			objClauseVO.setComments(ApplicationUtil
					.trim(objModelAddClauseRevForm.getComments()));
			LogUtil.logMessage("Comments in ModelAddClauseRevAction:"
					+ objModelAddClauseRevForm.getComments());
			objClauseVO.setReason(ApplicationUtil.trim(objModelAddClauseRevForm
					.getReason()));
			LogUtil.logMessage("Reason in ModelAddClauseRevAction:"
					+ objModelAddClauseRevForm.getReason());
			objClauseVO.setPartNumber(objModelAddClauseRevForm.getPartNumber());
			LogUtil.logMessage("PartNumber in ModelAddClauseRevAction:"
					+ objModelAddClauseRevForm.getPartNumber());
			objClauseVO.setPriceBookNumber(objModelAddClauseRevForm
					.getPriceBookNumber());
			LogUtil.logMessage("PriceBookNumber in ModelAddClauseRevAction:"
					+ objModelAddClauseRevForm.getPriceBookNumber());
			objClauseVO.setDwONumber(objModelAddClauseRevForm.getDwONumber());
			LogUtil.logMessage("DwONumber in ModelAddClauseRevAction:"
					+ objModelAddClauseRevForm.getDwONumber());
			objClauseVO.setRefEDLNo(objModelAddClauseRevForm.getRefEDLNo());
			LogUtil.logMessage("RefEDLNo in ModelAddClauseRevAction:"
					+ objModelAddClauseRevForm.getRefEDLNo().length);
			objClauseVO.setEdlNo(objModelAddClauseRevForm.getEdlNo());
			LogUtil.logMessage("EdlNo in ModelAddClauseRevAction:"
					+ objModelAddClauseRevForm.getEdlNo().length);
			objClauseVO.setPartOfCode(objModelAddClauseRevForm.getPartOfCode());
			LogUtil.logMessage("PartOfCode in ModelAddClauseRevAction:"
					+ objModelAddClauseRevForm.getPartOfCode().length);
			objClauseVO.setPartOfSeqNo(objModelAddClauseRevForm
					.getPartOfSeqNo());
			LogUtil.logMessage("PartOfSeqNo in ModelAddClauseRevAction:"
					+ objModelAddClauseRevForm.getPartOfSeqNo().length);
			
			int standardEquipSeqNo = objModelAddClauseRevForm
			.getStandardEquipmentSeqNo();
			if (standardEquipSeqNo != -1) {
				objStandardEquipmentVO
				.setStandardEquipmentSeqNo(objModelAddClauseRevForm
						.getStandardEquipmentSeqNo());
				arlStandardEquipList.add(objStandardEquipmentVO);
				objClauseVO.setObjStandardEquipVO(arlStandardEquipList);
			} else {
				objClauseVO.setObjStandardEquipVO(null);
			}
			clauseTableArray1 = objModelAddClauseRevForm
			.getClauseTableDataArray1();
			
			if (clauseTableArray1 == null || clauseTableArray1.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData1(null);
			} else {
				
				for (int counter = 0; counter < clauseTableArray1.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData1(objModelAddClauseRevForm
							.getClauseTableDataArray1());
					LogUtil.logMessage("Table Data Value:"
							+ objModelAddClauseRevForm
							.getClauseTableDataArray1()[counter]);
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
				
			}
			arlTableList.add(objTableDataVO);
			
			clauseTableArray2 = objModelAddClauseRevForm
			.getClauseTableDataArray2();
			if (clauseTableArray2 == null || clauseTableArray2.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData2(null);
			} else {
				for (int counter = 0; counter < clauseTableArray2.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData2(objModelAddClauseRevForm
							.getClauseTableDataArray2());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
				
			}
			arlTableList.add(objTableDataVO);
			
			clauseTableArray3 = objModelAddClauseRevForm
			.getClauseTableDataArray3();
			
			if (clauseTableArray3 == null || clauseTableArray3.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData3(null);
			} else {
				for (int counter = 0; counter < clauseTableArray3.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData3(objModelAddClauseRevForm
							.getClauseTableDataArray3());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
				
			}
			arlTableList.add(objTableDataVO);
			clauseTableArray4 = objModelAddClauseRevForm
			.getClauseTableDataArray4();
			
			if (clauseTableArray4 == null || clauseTableArray4.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData4(null);
			} else {
				for (int counter = 0; counter < clauseTableArray4.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData4(objModelAddClauseRevForm
							.getClauseTableDataArray4());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
				
			}
			arlTableList.add(objTableDataVO);
			clauseTableArray5 = objModelAddClauseRevForm
			.getClauseTableDataArray5();
			
			if (clauseTableArray5 == null || clauseTableArray5.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData5(null);
			} else {
				for (int counter = 0; counter < clauseTableArray5.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData5(objModelAddClauseRevForm
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
			ModelAddClauseRevBI objModelAddClauseRevBI = ServiceFactory
			.getModelAddClauseRevBO();
			intStatusCode = objModelAddClauseRevBI.insertClause(objClauseVO);
			
			if (intStatusCode == 0) {
				objModelAddClauseRevForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				objModelAddClauseRevForm
				.setModelSeqNo(ApplicationConstants.Reset_Dropdown);
			}
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			ModelBI objModelBO = ServiceFactory.getModelBO();
			if (objModelBO.fetchModels(objModelVo) != null) {
				arlModelList = objModelBO.fetchModels(objModelVo);
				objModelAddClauseRevForm.setModelList(arlModelList);
				
			}
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelAddClauseRevAction..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
}
