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
import com.EMD.LSDB.bo.serviceinterface.AppendixBI;
import com.EMD.LSDB.bo.serviceinterface.ModelAppendixBI;
import com.EMD.LSDB.bo.serviceinterface.OrderClauseBI;
import com.EMD.LSDB.bo.serviceinterface.StandardEquipBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.constant.DatabaseConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.form.SpecMaintenance.AddClauseRevForm;
import com.EMD.LSDB.vo.common.AppendixVO;
import com.EMD.LSDB.vo.common.ClauseTableDataVO;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.ModelAppendixVO;
import com.EMD.LSDB.vo.common.StandardEquipVO;

/**
 * @author ps57222
 * 
 */
public class AddClauseRevAction extends EMDAction {
	
	/***************************************************************************
	 * * * * This Method is used to populate the Clause Details on PageLoad
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
		LogUtil.logMessage("Enters into AddClauseRevisionAction:fetchClauses");
		String strForwardKey = ApplicationConstants.SUCCESS;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		AddClauseRevForm objAddClauseRevForm = (AddClauseRevForm) objActionForm;
		
		ArrayList arlEquipList = new ArrayList();
		ArrayList arlClauseList = null;
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		LogUtil.logMessage(objLoginVo);
		StandardEquipVO objStdEquipmentVO = new StandardEquipVO();
		ClauseVO objClauseVO = new ClauseVO();
		
		String strSecCode = "";
		
		if (objHttpServletRequest.getParameter("clauseSeq") != null) {
			objAddClauseRevForm.setClauseSeqNo(Integer
					.parseInt(objHttpServletRequest.getParameter("clauseSeq")
							.toString()));
			LogUtil.logMessage("clauseSeq:"
					+ objHttpServletRequest.getParameter("clauseSeq"));
		}
		
		if (objHttpServletRequest.getParameter("secSeq") != null) {
			objAddClauseRevForm.setSecSeq(Integer
					.parseInt(objHttpServletRequest.getParameter("secSeq")
							.toString()));
			LogUtil.logMessage("secSeq:"
					+ objHttpServletRequest.getParameter("secSeq"));
		}
		
		if (objHttpServletRequest.getParameter("secCode") != null) {
			strSecCode = objHttpServletRequest.getParameter("secCode")
			.toString();
			objAddClauseRevForm.setSecCode(strSecCode);
			LogUtil.logMessage("secCode:"
					+ objHttpServletRequest.getParameter("secCode"));
		}
		
		if (objHttpServletRequest.getParameter("revCode") != null) {
			objAddClauseRevForm.setRevCode(Integer
					.parseInt(objHttpServletRequest.getParameter("revCode")));
			LogUtil.logMessage("revCode:"
					+ objHttpServletRequest.getParameter("revCode"));
		}
		
		if (objHttpServletRequest.getParameter("orderkey") != null) {
			objAddClauseRevForm.setOrderKey(Integer
					.parseInt(objHttpServletRequest.getParameter("orderkey")
							.toString()));
			LogUtil.logMessage("orderkey:"
					+ objHttpServletRequest.getParameter("orderkey"));
		}
		if (objHttpServletRequest.getParameter("custSeqNo") != null) {
			objAddClauseRevForm.setCustomerSeqNo(Integer
					.parseInt(objHttpServletRequest.getParameter("custSeqNo")
							.toString()));
			LogUtil.logMessage("custSeqNo:"
					+ objHttpServletRequest.getParameter("custSeqNo"));
		}
		
		objClauseVO.setClauseSeqNo(objAddClauseRevForm.getClauseSeqNo());
		LogUtil.logMessage("ClauseSeqNo in AddClauseRevAction:fetchClauses:"
				+ objAddClauseRevForm.getClauseSeqNo());
		objClauseVO.setUserID(objLoginVo.getUserID());
		objClauseVO.setOrderKey(objAddClauseRevForm.getOrderKey());
		
		try {
			
			OrderClauseBI objOrderClauseBI = ServiceFactory.getOrderClauseBO();
			arlClauseList = objOrderClauseBI.fetchClauses(objClauseVO);
			
			if (arlClauseList != null) {
				objAddClauseRevForm.setClauseList(arlClauseList);
			}
			LogUtil.logMessage("Size of ArrayList:"
					+ objAddClauseRevForm.getClauseList().size());
			
			for (int counter = 0; counter < arlClauseList.size(); counter++) {
				objClauseVO = (ClauseVO) arlClauseList.get(counter);
				objAddClauseRevForm.setSecSeq(objClauseVO.getSectionSeqNo());
				
			}
			
			StandardEquipBI lStandardEquipBO = ServiceFactory
			.getStandardEquipBO();
			
			objStdEquipmentVO.setUserID(objLoginVo.getUserID());
			arlEquipList = lStandardEquipBO
			.fetchStandardEquipment(objStdEquipmentVO);
			objAddClauseRevForm.setStdEquipmentList(arlEquipList);
			
			LogUtil.logMessage("Size of ArrayList:"
					+ objAddClauseRevForm.getStdEquipmentList().size());
			
		} catch (Exception objExp) {
			objExp.printStackTrace();
			LogUtil
			.logMessage("Enters into Exception Block in AddClauseRevAction");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objErrorInfo.getMessage();
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	public ActionForward insertClause(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		
		LogUtil.logMessage("Enters into AddClauseRevAction:insertClause");
		
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intOrderKey = 0;
		int intClauseSeqNo = 0;
		int intsecSeq = 0;
		int intrevCode = 0;
		int intsubSecSeqNo=0;
		//Added for CR_114
		int intClauseVerNo =0;
		//Added for CR_114 Ends Here
		/*Added as per CR 73 to return to Modify Spec  Screen by CM68219 starts*/
		ActionForward actionRedirect = null;
		String strRedirectFlag=null;

		/*Added as per CR 73 to return to Modify Spec Screen by CM68219 ends*/
		try {
			AddClauseRevForm objAddClauseRevForm = (AddClauseRevForm) objActionForm;
			HttpSession objSession = objHttpServletRequest.getSession(false);
			ClauseVO objClauseVO = new ClauseVO();
			String[] clauseTableArray1, clauseTableArray2, clauseTableArray3, clauseTableArray4, clauseTableArray5 = null;
			ClauseTableDataVO objTableDataVO = null;
			ArrayList arlTableList = new ArrayList();
			
			ArrayList arlCompVO = new ArrayList();
			ArrayList arlEquipList = new ArrayList();
			ArrayList arlClauseList = null;
			StandardEquipVO objStdEquipmentVO = new StandardEquipVO();
			StandardEquipVO objStandardEquipmentVO = new StandardEquipVO();
			ArrayList arlStandardEquipList = new ArrayList();
			int intStatusCode = 0;
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			objClauseVO.setUserID(objLoginVo.getUserID());
			if(objHttpServletRequest.getParameter("subsecno") !=null){
				
			       intsubSecSeqNo = Integer.parseInt(objHttpServletRequest
					.getParameter("subsecno"));
		    }
               if (objHttpServletRequest.getParameter("orderKey") != null) {
				
			 intOrderKey = Integer.parseInt(objHttpServletRequest
						.getParameter("orderKey"));
			}
			if (objHttpServletRequest.getParameter("clauseSeq") != null) {
				
				 intClauseSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("clauseSeq"));
			}
			 intsecSeq = Integer.parseInt(objHttpServletRequest
					.getParameter("secSeq"));
			 intrevCode = Integer.parseInt(objHttpServletRequest
					.getParameter("revCode"));
			 objAddClauseRevForm.setOrderKey(intOrderKey);
			 objAddClauseRevForm.setClauseSeqNo(intClauseSeqNo);
			objClauseVO.setOrderKey(objAddClauseRevForm.getOrderKey());
			objClauseVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objClauseVO.setModelSeqNo(objAddClauseRevForm.getModelSeqNo());
			objClauseVO.setClauseSeqNo(objAddClauseRevForm.getClauseSeqNo());
			objClauseVO.setSubSectionSeqNo(objAddClauseRevForm
					.getSubSectionSeqNo());
			
			objClauseVO.setComponentVO(arlCompVO);
			objClauseVO.setClauseDesc(ApplicationUtil.trim(objAddClauseRevForm
					.getClauseDesc()));
			LogUtil.logMessage("ClauseDesc in ModelAddClauseRevAction:"
					+ objAddClauseRevForm.getClauseDesc());
			objClauseVO.setComments(ApplicationUtil.trim(objAddClauseRevForm
					.getComments()));
			LogUtil.logMessage("Comments in ModelAddClauseRevAction:"
					+ objAddClauseRevForm.getComments());
			
			objClauseVO
			.setCustomerSeqNo(objAddClauseRevForm.getCustomerSeqNo());
			LogUtil.logMessage("CustomerSeqNo in ModelAddClauseRevAction:"
					+ objAddClauseRevForm.getCustomerSeqNo());
			
			objClauseVO.setReason(ApplicationUtil.trim(objAddClauseRevForm
					.getReason()));
			LogUtil.logMessage("Reason in ModelAddClauseRevAction:"
					+ objAddClauseRevForm.getReason());
			objClauseVO.setPartNumber(objAddClauseRevForm.getPartNumber());
			LogUtil.logMessage("PartNumber in ModelAddClauseRevAction:"
					+ objAddClauseRevForm.getPartNumber());
			objClauseVO.setPriceBookNumber(objAddClauseRevForm
					.getPriceBookNumber());
			LogUtil.logMessage("PriceBookNumber in ModelAddClauseRevAction:"
					+ objAddClauseRevForm.getPriceBookNumber());
			objClauseVO.setDwONumber(objAddClauseRevForm.getDwONumber());
			LogUtil.logMessage("DwONumber in ModelAddClauseRevAction:"
					+ objAddClauseRevForm.getDwONumber());
			objClauseVO.setRefEDLNo(objAddClauseRevForm.getRefEDLNo());
			LogUtil.logMessage("RefEDLNo in ModelAddClauseRevAction:"
					+ objAddClauseRevForm.getRefEDLNo().length);
			objClauseVO.setEdlNo(objAddClauseRevForm.getEdlNo());
			LogUtil.logMessage("EdlNo in ModelAddClauseRevAction:"
					+ objAddClauseRevForm.getEdlNo().length);
			objClauseVO.setClauseNoArray(objAddClauseRevForm.getPartOf());
			LogUtil.logMessage("PartOfCode in ModelAddClauseRevAction:"
					+ objAddClauseRevForm.getPartOf().length);
			objClauseVO.setPartOfSeqNo(objAddClauseRevForm.getPartOfSeqNo());
			LogUtil.logMessage("PartOfSeqNo in ModelAddClauseRevAction:"
					+ objAddClauseRevForm.getPartOfSeqNo().length);
			objClauseVO.setClauseSeqNoArray(objAddClauseRevForm
					.getClauseSeqNum());
			int standardEquipSeqNo = objAddClauseRevForm
			.getStandardEquipmentSeqNo();
			if (standardEquipSeqNo != -1) {
				objStandardEquipmentVO
				.setStandardEquipmentSeqNo(objAddClauseRevForm
						.getStandardEquipmentSeqNo());
				arlStandardEquipList.add(objStandardEquipmentVO);
				objClauseVO.setObjStandardEquipVO(arlStandardEquipList);
			} else {
				objClauseVO.setObjStandardEquipVO(null);
			}
			LogUtil
			.logMessage("StandardEquipmentSeqNo in ModelAddClauseRevAction:"
					+ objAddClauseRevForm.getStandardEquipmentSeqNo());
			
			clauseTableArray1 = objAddClauseRevForm.getClauseTableDataArray1();
			
			if (clauseTableArray1 == null || clauseTableArray1.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData1(null);
			} else {
				
				for (int counter = 0; counter < clauseTableArray1.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData1(objAddClauseRevForm
							.getClauseTableDataArray1());
					
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
				
			}
			arlTableList.add(objTableDataVO);
			
			clauseTableArray2 = objAddClauseRevForm.getClauseTableDataArray2();
			if (clauseTableArray2 == null || clauseTableArray2.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData2(null);
			} else {
				for (int counter = 0; counter < clauseTableArray2.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData2(objAddClauseRevForm
							.getClauseTableDataArray2());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
				
			}
			arlTableList.add(objTableDataVO);
			
			clauseTableArray3 = objAddClauseRevForm.getClauseTableDataArray3();
			
			if (clauseTableArray3 == null || clauseTableArray3.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData3(null);
			} else {
				for (int counter = 0; counter < clauseTableArray3.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData3(objAddClauseRevForm
							.getClauseTableDataArray3());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
				
			}
			arlTableList.add(objTableDataVO);
			clauseTableArray4 = objAddClauseRevForm.getClauseTableDataArray4();
			
			if (clauseTableArray4 == null || clauseTableArray4.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData4(null);
			} else {
				for (int counter = 0; counter < clauseTableArray4.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData4(objAddClauseRevForm
							.getClauseTableDataArray4());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
				
			}
			arlTableList.add(objTableDataVO);
			clauseTableArray5 = objAddClauseRevForm.getClauseTableDataArray5();
			
			if (clauseTableArray5 == null || clauseTableArray5.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData5(null);
			} else {
				for (int counter = 0; counter < clauseTableArray5.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData5(objAddClauseRevForm
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
						
			
			OrderClauseBI objOrderClauseBI = ServiceFactory.getOrderClauseBO();
			intStatusCode = objOrderClauseBI.insertClause(objClauseVO);
			LogUtil.logMessage("Success Code:" + intStatusCode);
			/*Added as per CR 73 to return to Modify Spec  Screen by CM68219 starts*/
			//Modified for CR_114
			objOrderClauseBI = ServiceFactory.getOrderClauseBO();
			arlClauseList = objOrderClauseBI.fetchClauses(objClauseVO);
			//Modified for CR_114 Ends here
			if (intStatusCode == 0) {
				
				LogUtil.logMessage("Size of ArrayList: for Appendix Image Mapping"
						+ arlClauseList.size());
				
				for (int counter = 0; counter < arlClauseList.size(); counter++) {
					objClauseVO = (ClauseVO) arlClauseList.get(counter);
					intClauseVerNo =objClauseVO.getVersionNo();
					
				}
				
				//Added for CR_114
				LogUtil.logMessage("Value of MapAppendixImg boolean is "+objAddClauseRevForm.getMapAppendixImg());
				if(objAddClauseRevForm.getMapAppendixImg()==1){
					LogUtil.logMessage("Appendix Image Seq NO is " +objAddClauseRevForm.getAppendixImgSeqNo());
					//objClauseVO.setAppendixImgSeqNo(objModelAddClauseForm.getAppendixImgSeqNo());
					AppendixVO objAppendixVO = new AppendixVO();
					objAppendixVO.setOrderKey(intOrderKey);
					objAppendixVO.setImageSeqNo(objAddClauseRevForm.getAppendixImgSeqNo());
					objAppendixVO.setDataLocationType(DatabaseConstants.DATALOCATION);
					objAppendixVO.setClauseSeqNo(intClauseSeqNo);
					objAppendixVO.setVersionNo(intClauseVerNo);
					objAppendixVO.setUserID(objLoginVo.getUserID());
					
					AppendixBI objAppendixBI = ServiceFactory.getAppendixBO();
					intStatusCode = objAppendixBI.saveMappings(objAppendixVO);
					
					if (intStatusCode == 0) {
						objAddClauseRevForm
						.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
					}
					
					if (intStatusCode > 0) {
						objAddClauseRevForm.setMessageID("" + intStatusCode);
					}
					
				}
				//Added for CR_114 Ends Here
				
				
				strRedirectFlag="Y";
				actionRedirect = new ActionForward("OrderSection.do?method=fetchSectionDetails&orderKey="+intOrderKey+"&secSeq="+intsecSeq+"&revCode="+intrevCode+"&subsecno="+intsubSecSeqNo, true /* REDIRECT */);	
	                 }
			    else {
			    	strRedirectFlag="N";
			    	objAddClauseRevForm.setMessageID("" + intStatusCode);
			
			objAddClauseRevForm.setClauseList(arlClauseList);
			
			LogUtil.logMessage("Size of ArrayList:"
					+ objAddClauseRevForm.getClauseList().size());
			
			StandardEquipBI lStandardEquipBO = ServiceFactory
			.getStandardEquipBO();
			objStdEquipmentVO.setUserID(objLoginVo.getUserID());
			arlEquipList = lStandardEquipBO
			.fetchStandardEquipment(objStdEquipmentVO);
			objAddClauseRevForm.setStdEquipmentList(arlEquipList);
			
		}
			/*Added as per CR 73 to return to Modify Spec Screen by CM68219 ends*/
		}
		
		catch (Exception objExp) {
			objExp.printStackTrace();
			LogUtil
			.logMessage("Enters into Exception Block in ModelAddClauseRevAction..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objErrorInfo.getMessage();
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		
		/*Added as per CR 73 to return to Modify Spec  Screen by CM68219 starts*/
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
