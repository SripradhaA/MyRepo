package com.EMD.LSDB.action.SpecMaintenance;

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
import com.EMD.LSDB.bo.serviceinterface.OrderBI;
import com.EMD.LSDB.bo.serviceinterface.OrderClauseBI;
import com.EMD.LSDB.bo.serviceinterface.OrderSectionBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.constant.DatabaseConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.form.SpecMaintenance.OrderSectionForm;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SubSectionVO;

public class OrderClaReArrangeAction extends EMDAction {

	/***************************************************************************
	 * This method is for initial loading of the Screen
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws BusinessException
	 **************************************************************************/
	public ActionForward initLoad(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
			throws BusinessException {

		LogUtil.logMessage("Inside OrderClaReArrangeAction:initLoad");
		String strForwardKey = ApplicationConstants.SUCCESS;
		OrderSectionForm objOrderSectionForm = (OrderSectionForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		int intOrderKey = 0;
		int intSecSeqNo = 0;
		int intRevCode = 0;
		int intSubSecSeqNo = 0;
		ArrayList arlSectionDetails = null;
		ArrayList arlOrderList = new ArrayList();
		OrderVO objOrderVO = new OrderVO();
		SectionVO objSectionVO = new SectionVO();
		LoginVO objLoginVo = (LoginVO) objSession
				.getAttribute(ApplicationConstants.USER_IN_SESSION);
		try {


			if ((String) objHttpServletRequest.getParameter("orderkey") != null) {
				intOrderKey = Integer.parseInt(objHttpServletRequest
						.getParameter("orderkey"));
			} else {
				intOrderKey = objOrderSectionForm.getOrderKey();
			}
			
			if ((String) objHttpServletRequest.getParameter("secSeq") != null) {
				intSecSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("secSeq"));
			} else {
				intSecSeqNo = objOrderSectionForm.getOrderSecSeqNo();
			}
			
			if (objOrderSectionForm.getRevCode() == 0) {
				intRevCode = 1;//Default No revision
			} else if ((String) objHttpServletRequest.getParameter("revCode") != null) {
				intRevCode = Integer.parseInt(objHttpServletRequest
						.getParameter("revCode"));
			} else {
				intRevCode = objOrderSectionForm.getRevCode();
			}
			
			if ((String) objHttpServletRequest.getParameter("subsecseqno") != null) {
				intSubSecSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("subsecseqno").toString());
				
				//Added for landing to subsection
				objHttpServletRequest.setAttribute("subsecseqno",
						(String) objHttpServletRequest
						.getParameter("subsecseqno"));
				
				//Ends here
				
			}
			
			objOrderVO.setOrderKey(objOrderSectionForm.getOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objOrderSectionForm.setOrderList(arlOrderList);
			
			objSectionVO.setUserID(objLoginVo.getUserID());
			objSectionVO.setSectionSeqNo(intSecSeqNo);
			objSectionVO.setOrderKey(intOrderKey);
			objSectionVO.setSubSecSeqNo(intSubSecSeqNo);
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSectionVO.setRevisionInput(intRevCode);
			//Added for CR_127 for getting the parent & child clauses
			objSectionVO.setRearrFlag("Y");
			//CR_127 Ends here
			
			OrderSectionBI objOrderSectionBI = ServiceFactory.getOrderSectionBO();
			arlSectionDetails = objOrderSectionBI.fetchSectionDetails(objSectionVO);
			//Added for CR-127
			for (int i=0;i<arlSectionDetails.size();i++) {
				LogUtil.logMessage("arlSectionDetails.size()" + arlSectionDetails.size());
				SubSectionVO objSubSectionVO = (SubSectionVO) arlSectionDetails.get(i);
				if (objSubSectionVO.getSubSecSeqNo() == intSubSecSeqNo) {
					LogUtil.logMessage("objSubSectionVO.getSubSecName()" + objSubSectionVO.getSubSecName());					
					if (objSubSectionVO.getChildClausesList() != null){
						LogUtil.logMessage("objSubSectionVO.getChildClausesList().size()" + objSubSectionVO.getChildClausesList().size());
						objOrderSectionForm.setChildClaList(objSubSectionVO.getChildClausesList());
					}
				}
			}
			//Added for CR-127 ends here
			objOrderSectionForm.setSecDetail(arlSectionDetails);
			
			objOrderSectionForm.setOrderKey(intOrderKey);
			objOrderSectionForm.setOrderSecSeqNo(intSecSeqNo);
			objOrderSectionForm.setSubSecSeqNo(intSubSecSeqNo);
		}

		catch (Exception objExp) {
			strForwardKey = ApplicationConstants.FAILURE;
			objExp.printStackTrace();
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}

		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);

	}
	
	/***************************************************************************
	 * This method is for Saving the Clause Rearrange at Order Level
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws BusinessException
	 **************************************************************************/
	public ActionForward saveOrderClaReArrange(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
			throws BusinessException {

		LogUtil.logMessage("Inside OrderClaReArrangeAction:saveOrderClaReArrange");
		String strForwardKey = ApplicationConstants.SUCCESS;
		OrderSectionForm objOrderSectionForm = (OrderSectionForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		int intOrderKey = 0;
		int intSecSeqNo = 0;
		int intRevCode = 0;
		int intSubSecSeqNo = 0;
		int intSuccess;
		String strClaSeqNoList = null;
		ArrayList arlSectionDetails = null;
		ArrayList arlOrderList = new ArrayList();
		ArrayList arlClaSeqNoList = new ArrayList();
		OrderVO objOrderVO = new OrderVO();
		ClauseVO objClauseVO = new ClauseVO();
		SectionVO objSectionVO = new SectionVO();
		LoginVO objLoginVo = (LoginVO) objSession
				.getAttribute(ApplicationConstants.USER_IN_SESSION);
		try {


			if ((String) objHttpServletRequest.getParameter("orderkey") != null) {
				intOrderKey = Integer.parseInt(objHttpServletRequest
						.getParameter("orderkey"));
			} else {
				intOrderKey = objOrderSectionForm.getOrderKey();
			}
			
			if ((String) objHttpServletRequest.getParameter("secSeq") != null) {
				intSecSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("secSeq"));
			} else {
				intSecSeqNo = objOrderSectionForm.getOrderSecSeqNo();
			}
			
			if (objOrderSectionForm.getRevCode() == 0) {
				intRevCode = 1;//Default No revision
			} else if ((String) objHttpServletRequest.getParameter("revCode") != null) {
				intRevCode = Integer.parseInt(objHttpServletRequest
						.getParameter("revCode"));
			} else {
				intRevCode = objOrderSectionForm.getRevCode();
			}
			
			if ((String) objHttpServletRequest.getParameter("subsecseqno") != null) {
				intSubSecSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("subsecseqno").toString());
				
				objHttpServletRequest.setAttribute("subsecseqno",
						(String) objHttpServletRequest
						.getParameter("subsecseqno"));
			}
			
			objOrderVO.setOrderKey(objOrderSectionForm.getOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objOrderSectionForm.setOrderList(arlOrderList);
			
			objClauseVO.setOrderKey(objOrderSectionForm.getOrderKey());
			objClauseVO.setSubSectionSeqNo(intSubSecSeqNo);
			objClauseVO.setUserID(objLoginVo.getUserID());
			strClaSeqNoList = objOrderSectionForm.getRowIndexList();
			
			String[] token = strClaSeqNoList.split ("\\,");
			 for (int i=0; i < token.length; i++){
					 arlClaSeqNoList.add(token[i]);
			 }
			String [] arlClaSeqNoArry =new String[arlClaSeqNoList.size()];
			int i=0;
			for ( Iterator iter = arlClaSeqNoList.iterator(); iter.hasNext();) {
					
				String element = (String) iter.next();
				arlClaSeqNoArry[i]=element;
				i++;
			}
			objClauseVO.setClaSeqNoList(arlClaSeqNoArry);
			OrderClauseBI OrderClauseBO = ServiceFactory.getOrderClauseBO();
			
			intSuccess = OrderClauseBO.saveOrderClaReArrange(objClauseVO);
			objOrderSectionForm.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			
			objOrderSectionForm.setRowIndexList("");
			
			objSectionVO.setUserID(objLoginVo.getUserID());
			objSectionVO.setSectionSeqNo(intSecSeqNo);
			objSectionVO.setOrderKey(intOrderKey);
			objSectionVO.setSubSecSeqNo(intSubSecSeqNo);
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSectionVO.setRevisionInput(intRevCode);
			//Added for CR-127
			objSectionVO.setRearrFlag("Y");
			
			OrderSectionBI objOrderSectionBI = ServiceFactory.getOrderSectionBO();
			arlSectionDetails = objOrderSectionBI.fetchSectionDetails(objSectionVO);
			
			//Added for CR-127
			for (int j=0;j<arlSectionDetails.size();j++) {
				LogUtil.logMessage("arlSectionDetails.size()" + arlSectionDetails.size());
				SubSectionVO objSubSectionVO = (SubSectionVO) arlSectionDetails.get(j);
				if (objSubSectionVO.getSubSecSeqNo() == intSubSecSeqNo) {
					LogUtil.logMessage("objSubSectionVO.getSubSecName()" + objSubSectionVO.getSubSecName());					
					if (objSubSectionVO.getChildClausesList() != null){
						LogUtil.logMessage("objSubSectionVO.getChildClausesList().size()" + objSubSectionVO.getChildClausesList().size());
						objOrderSectionForm.setChildClaList(objSubSectionVO.getChildClausesList());
					}
				}
			}
			//Added for CR-127 ends here
			objOrderSectionForm.setSecDetail(arlSectionDetails);
			
			objOrderSectionForm.setOrderKey(intOrderKey);
			objOrderSectionForm.setOrderSecSeqNo(intSecSeqNo);
			objOrderSectionForm.setSubSecSeqNo(intSubSecSeqNo);
		}

		catch (Exception objExp) {
			strForwardKey = ApplicationConstants.FAILURE;
			objExp.printStackTrace();
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}

		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);

	}
}