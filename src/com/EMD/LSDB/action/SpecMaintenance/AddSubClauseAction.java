/**
 * 
 */
package com.EMD.LSDB.action.SpecMaintenance;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.EMD.LSDB.action.common.EMDAction;
import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.OrderClauseBI;
import com.EMD.LSDB.bo.serviceinterface.StandardEquipBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.constant.DatabaseConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.form.SpecMaintenance.AddSubClauseForm;
import com.EMD.LSDB.vo.common.ClauseTableDataVO;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.StandardEquipVO;

/**
 * @author ps57222
 *
 */
public class AddSubClauseAction extends EMDAction {
	
	/*******************************************************************************************
	 *  * * *		This Method is used to populate the Clause Details on PageLoad
	 *  
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return     	ActionForward  		   
	 * @throws     	EMDException
	 
	 ******************************************************************************************/
	public ActionForward fetchClauses(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil.logMessage("Enters into AddSubClauseAction:fetchClauses");
		String strForwardKey = ApplicationConstants.SUCCESS;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		AddSubClauseForm objAddSubClauseForm = (AddSubClauseForm) objActionForm;
		
		ArrayList arlEquipList = new ArrayList();
		ArrayList arlClauseList = null;
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		LogUtil.logMessage(objLoginVo);
		StandardEquipVO objStdEquipmentVO = new StandardEquipVO();
		ClauseVO objClauseVO = new ClauseVO();
		
		if (objHttpServletRequest.getParameter("clauseSeq") != null) {
			objAddSubClauseForm.setParentClauseSeqNo(Integer
					.parseInt(objHttpServletRequest.getParameter("clauseSeq")
							.toString()));
		}
		
		if (objHttpServletRequest.getParameter("clauseSeq") != null) {
			objAddSubClauseForm.setClauseSeqNo(Integer
					.parseInt(objHttpServletRequest.getParameter("clauseSeq")
							.toString()));
		}
		
		if (objHttpServletRequest.getParameter("secSeq") != null) {
			objAddSubClauseForm.setSecSeq(Integer
					.parseInt(objHttpServletRequest.getParameter("secSeq")
							.toString()));
		}
		
		if (objHttpServletRequest.getParameter("revCode") != null) {
			objAddSubClauseForm.setRevCode(Integer
					.parseInt(objHttpServletRequest.getParameter("revCode")));
		}
		
		if (objHttpServletRequest.getParameter("orderkey") != null) {
			objAddSubClauseForm.setOrderKey(Integer
					.parseInt(objHttpServletRequest.getParameter("orderkey")
							.toString()));
		}
		
		if (objHttpServletRequest.getParameter("clauseNum") != null) {
			objAddSubClauseForm.setClauseNum(objHttpServletRequest
					.getParameter("clauseNum").toString());
		}
		/** Added For CustomerSeqNo Bug Fixing**/
		
		if (objHttpServletRequest.getParameter("custSeqNo") != null) {
			objAddSubClauseForm.setCustomerSeqNo(Integer
					.parseInt(objHttpServletRequest.getParameter("custSeqNo")));
		}
		
		LogUtil.logMessage("CustomerSeqNo in AddSubClauseAction:"
				+ objHttpServletRequest.getParameter("custSeqNo"));
		
		/** Added For CustomerSeqNo Bug Fixing**/
		
		objClauseVO.setClauseSeqNo(objAddSubClauseForm.getClauseSeqNo());
		LogUtil.logMessage("ClauseSeqNo in AddSubClauseAction:fetchClauses:"
				+ objAddSubClauseForm.getClauseSeqNo());
		objClauseVO.setUserID(objLoginVo.getUserID());
		objClauseVO.setOrderKey(objAddSubClauseForm.getOrderKey());
		
		try {
			
			OrderClauseBI objOrderClauseBI = ServiceFactory.getOrderClauseBO();
			arlClauseList = objOrderClauseBI.fetchClauses(objClauseVO);
			
			if (arlClauseList != null) {
				objAddSubClauseForm.setClauseList(arlClauseList);
			}
			LogUtil.logMessage("Size of ArrayList:"
					+ objAddSubClauseForm.getClauseList().size());
			
			StandardEquipBI lStandardEquipBO = ServiceFactory
			.getStandardEquipBO();
			objStdEquipmentVO.setUserID(objLoginVo.getUserID());
			arlEquipList = lStandardEquipBO
			.fetchStandardEquipment(objStdEquipmentVO);
			objAddSubClauseForm.setStdEquipmentList(arlEquipList);
			objOrderClauseBI = ServiceFactory.getOrderClauseBO();
			
		} catch (Exception objExp) {
			objExp.printStackTrace();
			LogUtil
			.logMessage("Enters into Exception Block in AddSubClauseAction");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objErrorInfo.getMessage();
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/*******************************************************************************************
	 *  * * *		This Method is used to Insert the Clause Details 
	 *  
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return     	ActionForward  		   
	 * @throws     	EMDException
	 
	 ******************************************************************************************/
	
	public ActionForward insertClause(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		
		LogUtil.logMessage("Enters into AddSubClauseAction:insertClause");
		int intsubSecSeqNo=0;
		int intClauseSeqNo=0;
		int intsecSeq=0;
		int intrevCode = 0;
		/*Added as per CR 73 to return to Modify Spec Screen by CM68219 starts*/
		ActionForward actionRedirect = null;
               String strRedirectFlag=null;
        /*Added as per CR 73 to return to Modify Spec Screen by CM68219 ends*/
               int intOrderKey =0;
		String strForwardKey = ApplicationConstants.SUCCESS;
		try {
			AddSubClauseForm objAddSubClauseForm = (AddSubClauseForm) objActionForm;
			HttpSession objSession = objHttpServletRequest.getSession(false);
			ClauseVO objClauseVO = new ClauseVO();
			if (objHttpServletRequest.getParameter("clauseSeq") != null) {
				intClauseSeqNo=Integer
				.parseInt(objHttpServletRequest.getParameter("clauseSeq")
						.toString());
				objAddSubClauseForm.setParentClauseSeqNo(intClauseSeqNo);
			}
			if(objHttpServletRequest.getParameter("subsecno") !=null){
				
			       intsubSecSeqNo = Integer.parseInt(objHttpServletRequest
					.getParameter("subsecno"));
		    }
			
			if (objHttpServletRequest.getParameter("clauseSeq") != null) {
				intClauseSeqNo=Integer
				.parseInt(objHttpServletRequest.getParameter("clauseSeq")
						.toString());
				objAddSubClauseForm.setClauseSeqNo(intClauseSeqNo);
			}
			
			if (objHttpServletRequest.getParameter("secSeq") != null) {
				 intsecSeq=Integer
				.parseInt(objHttpServletRequest.getParameter("secSeq")
						.toString());
				objAddSubClauseForm.setSecSeq(intsecSeq);
			}
			
			if (objHttpServletRequest.getParameter("revCode") != null) {
				 intrevCode=Integer
				.parseInt(objHttpServletRequest.getParameter("revCode"));
				objAddSubClauseForm.setRevCode(intrevCode);
			}
			
			if (objHttpServletRequest.getParameter("orderKey") != null) {
				intOrderKey=Integer
				.parseInt(objHttpServletRequest.getParameter("orderKey")
						.toString());
				objAddSubClauseForm.setOrderKey(intOrderKey);
			}
			String[] clauseTableArray1, clauseTableArray2, clauseTableArray3, clauseTableArray4, clauseTableArray5 = null;
			ClauseTableDataVO objTableDataVO = null;
			ArrayList arlTableList = new ArrayList();
			ArrayList arlClauseList = null;
			ArrayList arlEquipList = new ArrayList();
			ArrayList arlCompVO = new ArrayList();
			int intStatusCode = 0;
			StandardEquipVO objStandardEquipmentVO = new StandardEquipVO();
			
			ArrayList arlStandardEquipList = new ArrayList();
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			objClauseVO.setUserID(objLoginVo.getUserID());
			objClauseVO.setOrderKey(objAddSubClauseForm.getOrderKey());
			objClauseVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objClauseVO.setModelSeqNo(objAddSubClauseForm.getModelSeqNo());
			objClauseVO.setParentClauseSeqNo(objAddSubClauseForm
					.getClauseSeqNo());
			LogUtil.logMessage("Parent Clause SeqNo:"
					+ objClauseVO.getParentClauseSeqNo());
			
			objClauseVO.setSubSectionSeqNo(objAddSubClauseForm
					.getSubSectionSeqNo());
			
			objClauseVO.setComponentVO(arlCompVO);
			objClauseVO.setClauseDesc(ApplicationUtil.trim(objAddSubClauseForm
					.getNewClauseDesc()));
			LogUtil.logMessage("ClauseDesc in AddSubClauseAction:"
					+ objAddSubClauseForm.getNewClauseDesc());
			objClauseVO.setComments(ApplicationUtil.trim(objAddSubClauseForm
					.getComments()));
			LogUtil.logMessage("Comments in AddSubClauseAction:"
					+ objAddSubClauseForm.getComments());
			objClauseVO.setReason(ApplicationUtil.trim(objAddSubClauseForm
					.getReason()));
			LogUtil.logMessage("Reason in AddSubClauseAction:"
					+ objAddSubClauseForm.getReason());
			
			/** Added For CustomerSeqNo Bug Fixing**/
			
			objClauseVO
			.setCustomerSeqNo(objAddSubClauseForm.getCustomerSeqNo());
			LogUtil
			.logMessage("CustomerSeqNo in AddSubClauseAction:insertClause"
					+ objAddSubClauseForm.getCustomerSeqNo());
			
			/** Added For CustomerSeqNo Bug Fixing**/
			
			objClauseVO.setPartNumber(objAddSubClauseForm.getPartNumber());
			LogUtil.logMessage("PartNumber in AddSubClauseAction:"
					+ objAddSubClauseForm.getPartNumber());
			objClauseVO.setPriceBookNumber(objAddSubClauseForm
					.getPriceBookNumber());
			LogUtil.logMessage("PriceBookNumber in AddSubClauseAction:"
					+ objAddSubClauseForm.getPriceBookNumber());
			objClauseVO.setDwONumber(objAddSubClauseForm.getDwONumber());
			LogUtil.logMessage("DwONumber in AddSubClauseAction:"
					+ objAddSubClauseForm.getDwONumber());
			objClauseVO.setRefEDLNo(objAddSubClauseForm.getRefEDLNo());
			LogUtil.logMessage("RefEDLNo in AddSubClauseAction:"
					+ objAddSubClauseForm.getRefEDLNo().length);
			objClauseVO.setEdlNo(objAddSubClauseForm.getEdlNo());
			LogUtil.logMessage("EdlNo in AddSubClauseAction:"
					+ objAddSubClauseForm.getEdlNo().length);
			
			objClauseVO.setClauseNoArray(objAddSubClauseForm.getPartOf());
			LogUtil.logMessage("PartOfCode in ModelAddClauseRevAction:"
					+ objAddSubClauseForm.getPartOf().length);
			objClauseVO.setPartOfSeqNo(objAddSubClauseForm.getPartOfSeqNo());
			LogUtil.logMessage("PartOfSeqNo in ModelAddClauseRevAction:"
					+ objAddSubClauseForm.getPartOfSeqNo().length);
			objClauseVO.setClauseSeqNoArray(objAddSubClauseForm
					.getClauseSeqNum());
			int standardEquipSeqNo = objAddSubClauseForm
			.getStandardEquipmentSeqNo();
			if (standardEquipSeqNo != -1) {
				objStandardEquipmentVO
				.setStandardEquipmentSeqNo(objAddSubClauseForm
						.getStandardEquipmentSeqNo());
				arlStandardEquipList.add(objStandardEquipmentVO);
				objClauseVO.setObjStandardEquipVO(arlStandardEquipList);
			} else {
				objClauseVO.setObjStandardEquipVO(null);
			}
			
			clauseTableArray1 = objAddSubClauseForm.getClauseTableDataArray1();
			
			if (clauseTableArray1 == null || clauseTableArray1.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData1(null);
			} else {
				
				for (int counter = 0; counter < clauseTableArray1.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData1(objAddSubClauseForm
							.getClauseTableDataArray1());
					LogUtil
					.logMessage("Table Data Value:"
							+ objAddSubClauseForm
							.getClauseTableDataArray1()[counter]);
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
				
			}
			arlTableList.add(objTableDataVO);
			
			clauseTableArray2 = objAddSubClauseForm.getClauseTableDataArray2();
			if (clauseTableArray2 == null || clauseTableArray2.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData2(null);
			} else {
				for (int counter = 0; counter < clauseTableArray2.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData2(objAddSubClauseForm
							.getClauseTableDataArray2());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
				
			}
			arlTableList.add(objTableDataVO);
			
			clauseTableArray3 = objAddSubClauseForm.getClauseTableDataArray3();
			
			if (clauseTableArray3 == null || clauseTableArray3.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData3(null);
			} else {
				for (int counter = 0; counter < clauseTableArray3.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData3(objAddSubClauseForm
							.getClauseTableDataArray3());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
				
			}
			arlTableList.add(objTableDataVO);
			clauseTableArray4 = objAddSubClauseForm.getClauseTableDataArray4();
			
			if (clauseTableArray4 == null || clauseTableArray4.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData4(null);
			} else {
				for (int counter = 0; counter < clauseTableArray4.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData4(objAddSubClauseForm
							.getClauseTableDataArray4());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
				
			}
			arlTableList.add(objTableDataVO);
			clauseTableArray5 = objAddSubClauseForm.getClauseTableDataArray5();
			
			if (clauseTableArray5 == null || clauseTableArray5.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData5(null);
			} else {
				for (int counter = 0; counter < clauseTableArray5.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData5(objAddSubClauseForm
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
			
			//Added for CR-27 Change the functionality of Add Sub Clause at Order level
			objClauseVO
			.setClauseSource(ApplicationConstants.CLAUSE_SOURCE_ORDER);
			
			OrderClauseBI objOrderClauseBI = ServiceFactory.getOrderClauseBO();
			intStatusCode = objOrderClauseBI.insertClause(objClauseVO);
			LogUtil.logMessage("Success Code:" + intStatusCode);
			
			if (intStatusCode == 0) {
				strRedirectFlag="Y";
				actionRedirect = new ActionForward("OrderSection.do?method=fetchSectionDetails&orderKey="+intOrderKey+"&secSeq="+intsecSeq+"&revCode="+intrevCode+"&subsecno="+intsubSecSeqNo, true /* REDIRECT */);
			}else
			{
			strRedirectFlag="N";
			//Adding this for CR_92 notifying user for characters entered
			objAddSubClauseForm.setMessageID("" + intStatusCode);
			objClauseVO = new ClauseVO();
			objClauseVO.setClauseSeqNo(objAddSubClauseForm.getClauseSeqNo());
			LogUtil
			.logMessage("ClauseSeqNo in AddSubClauseAction:fetchClauses:"
					+ objAddSubClauseForm.getClauseSeqNo());
			objClauseVO.setUserID(objLoginVo.getUserID());
			objClauseVO.setOrderKey(objAddSubClauseForm.getOrderKey());
			
			arlClauseList = objOrderClauseBI.fetchClauses(objClauseVO);
			objAddSubClauseForm.setClauseList(arlClauseList);
			LogUtil.logMessage("Size of ArrayList:"
					+ objAddSubClauseForm.getClauseList().size());
			
			StandardEquipBI lStandardEquipBO = ServiceFactory
			.getStandardEquipBO();
			
			objStandardEquipmentVO.setUserID(objLoginVo.getUserID());
			arlEquipList = lStandardEquipBO
			.fetchStandardEquipment(objStandardEquipmentVO);
			objAddSubClauseForm.setStdEquipmentList(arlEquipList);
			
			objAddSubClauseForm
			.setClauseNum(objAddSubClauseForm.getClauseNum());
			objAddSubClauseForm.setNewClauseDesc("");
			objAddSubClauseForm.setClauseTableDataArray1(null);
			objAddSubClauseForm.setClauseTableDataArray2(null);
			objAddSubClauseForm.setClauseTableDataArray3(null);
			objAddSubClauseForm.setClauseTableDataArray4(null);
			objAddSubClauseForm.setClauseTableDataArray5(null);
			
			objAddSubClauseForm.setRefEDLNo(null);
			objAddSubClauseForm.setEdlNo(null);
			objAddSubClauseForm.setPartOf(null);
			objAddSubClauseForm.setPartOfSeqNo(null);
			objAddSubClauseForm.setClauseSeqNum(null);
			objAddSubClauseForm.setDwONumber(0);
			objAddSubClauseForm.setPartNumber(0);
			objAddSubClauseForm.setPriceBookNumber(0);
			objAddSubClauseForm
			.setStandardEquipmentSeqNo(ApplicationConstants.Reset_Dropdown);
			objAddSubClauseForm.setComments("");
			objAddSubClauseForm.setReason("");
			}
		}
		/*Added as per CR 73 to return to Modify Spec Screen by CM68219 ends*/
		catch (Exception objExp) {
			objExp.printStackTrace();
			LogUtil
			.logMessage("Enters into Exception Block in AddSubClauseAction..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objErrorInfo.getMessage();
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		/*Added as per CR 73 to return to Modify Spec Screen by CM68219 starts*/
		if(strRedirectFlag!=null && strRedirectFlag.equalsIgnoreCase("Y")){
			LogUtil.logMessage("FORWARD"+strRedirectFlag);
			return actionRedirect;
		   
		    }else{
		    	LogUtil.logMessage("FORWARD"+strForwardKey);
			return objActionMapping.findForward(strForwardKey);	
			
		}
		/*Added as per CR 73 to return to Modify Spec Screen by CM68219 ends*/
	}
	
}
