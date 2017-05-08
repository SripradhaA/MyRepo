/**
 * 
 */
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
import com.EMD.LSDB.bo.serviceinterface.MainFeatureInfoBI;
import com.EMD.LSDB.bo.serviceinterface.OrderBI;
import com.EMD.LSDB.bo.serviceinterface.OrderClauseBI;
import com.EMD.LSDB.bo.serviceinterface.OrderSectionBI;
import com.EMD.LSDB.bo.serviceinterface.SpecStatusBI;
import com.EMD.LSDB.bo.serviceinterface.SpecTypeBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.constant.DatabaseConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.form.SpecMaintenance.MainFeatureInfoForm;
import com.EMD.LSDB.form.SpecMaintenance.OrderSectionForm;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.MainFeatureInfoVO;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.RevisionVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SpecTypeVo;

/**
 * @author ps57222
 *
 */

/*******************************************************************************
 * This method is for loading the ComponentInfo home page
 * 
 * @author Satyam Computer Services Ltd
 * @version 1.0
 * @param objActionMapping
 * @param objActionForm
 * @param objHttpServletRequest
 * @param objHttpServletResponse
 * @return ActionForward
 * @throws ApplicationException
 ******************************************************************************/
public class MainFeatureInfoAction extends EMDAction {
	
	public ActionForward fetchComponentDesc(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil
		.logMessage("Enters into MainFeatureInfoAction:fetchComponentDesc");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlOrderList = new ArrayList();
		SectionVO objSectionVO = new SectionVO();
		ArrayList arlMainFeaList = new ArrayList();
		ArrayList arlModelMainFeaList = new ArrayList();
		/* Added for CR-74 01-06-09 - Starts Here */
		ArrayList arlRevList = new ArrayList();
		RevisionVO objRevisionVO = new RevisionVO();
		/* Added for CR-74 01-06-09 - Ends Here */
		MainFeatureInfoVO objMainFeatureInfoVO = new MainFeatureInfoVO();
		MainFeatureInfoForm objMainFeatureInfoForm = (MainFeatureInfoForm) objActionForm;
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LogUtil.logMessage(objSession);
		OrderVO objOrderVO = null;
		String revCode = "";
		int intOrdeKey = 0;
		String strFlag="";
		//Added For CR_100
		int intSpecStatusCode = 0;
		if (objHttpServletRequest.getParameter("orderKeyId") == null) {
			intOrdeKey = objMainFeatureInfoForm.getOrderKey();
		} else {
			intOrdeKey = Integer.parseInt(objHttpServletRequest
					.getParameter("orderKeyId"));
		}
		
		if (objHttpServletRequest.getParameter("revCode") != null
				&& objHttpServletRequest.getParameter("revCode") != "") {
			revCode = String.valueOf(objHttpServletRequest
					.getParameter("revCode"));
		} else {
			
			revCode = "1";
		}
		
		if (objHttpServletRequest.getParameter("specStatus") != null) {
			objMainFeatureInfoForm.setSpecStatus(objHttpServletRequest
					.getParameter("specStatus"));
		} else {
			objMainFeatureInfoForm
			.setSpecStatus(ApplicationConstants.SPEC_STATUS_NO);
		}
		
		if (objHttpServletRequest.getParameter("actionType") != null) {
			objMainFeatureInfoForm.setActionType(objHttpServletRequest
					.getParameter("actionType"));
		}
		if(objHttpServletRequest.getParameter("flag")!=null)
		{
			strFlag=objHttpServletRequest.getParameter("flag");
		}
		objMainFeatureInfoForm.setOrderKey(intOrdeKey);
		
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			/* Added for CR-74 01-06-09 - Starts Here */
			
			objRevisionVO.setUserID(objLoginVo.getUserID());

			OrderSectionBI objOrderSectionBO = ServiceFactory
					.getOrderSectionBO();

			arlRevList = objOrderSectionBO.fetchRevision(objRevisionVO);
			if (arlRevList != null) {
				objMainFeatureInfoForm.setRevisionList(arlRevList);
				objMainFeatureInfoForm.setRevCode(Integer.parseInt(revCode));
				objMainFeatureInfoForm.setHdnRevViewCode(String
						.valueOf(objMainFeatureInfoForm.getRevCode()));
			}
			LogUtil.logMessage("Rev Code"
					+ objMainFeatureInfoForm.getHdnRevViewCode());
			/* Added for CR-74 01-06-09* - Ends Here */
			objMainFeatureInfoVO.setUserID(objLoginVo.getUserID());
			objMainFeatureInfoVO.setOrderKey(objMainFeatureInfoForm
					.getOrderKey());
			objMainFeatureInfoVO
			.setDataLocationType(DatabaseConstants.DATALOCATION);
			/** Added for CR-74 01-06-09 - Starts Here* */
			objMainFeatureInfoVO
					.setRevCode(objMainFeatureInfoForm.getRevCode());
			/** Added for CR-74 01-06-09 - Ends Here* */
			MainFeatureInfoBI objMainFeatureInfoBI = ServiceFactory
			.getMainfeatureInfoBO();
			arlMainFeaList = objMainFeatureInfoBI
			.fetchMainFeatures(objMainFeatureInfoVO);
			if (arlMainFeaList != null) {
				objMainFeatureInfoForm.setMainFeatureList(arlMainFeaList);
			}
			LogUtil.logMessage("ArrayList Value in MainFeatureInfoAction:"
					+ objMainFeatureInfoForm.getMainFeatureList().size());
			
			//Added for LSDB_CR-62 by ka57588
			objMainFeatureInfoVO.setDisplayInSpec(false);
			arlModelMainFeaList = objMainFeatureInfoBI
			.fetchModelMainFeatures(objMainFeatureInfoVO);
			if (arlModelMainFeaList != null) {
				objMainFeatureInfoForm
				.setModelMainFeatureList(arlModelMainFeaList);
			}
			//Ends 
			
			objSectionVO.setUserID(objLoginVo.getUserID());
			
			objSectionVO.setOrderKey(objMainFeatureInfoForm.getOrderKey());
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null) {
				objMainFeatureInfoForm.setSectionList(arlSectionList);
			}
			
			LogUtil.logMessage("ArrayList Value in OrderSectionAction:"
					+ objMainFeatureInfoForm.getSectionList().size());
			
			/** ******************Ends Here ************************** */
			
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			LogUtil.logMessage("*******OrderKey inside Fetch orders:"
					+ objMainFeatureInfoForm.getOrderKey());
			//Added For CR_100
			objOrderVO = new OrderVO();
			objOrderVO.setOrderKey(objMainFeatureInfoForm.getOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objMainFeatureInfoForm.setOrderList(arlOrderList);
			
			
			objMainFeatureInfoForm.setHdnRevViewCode(revCode);
			
			LogUtil.logMessage("Rev Code"
					+ objMainFeatureInfoForm.getHdnRevViewCode());
			
			LogUtil
			.logMessage("*******OrderKey inside Fetch orders Arraylist size:"
					+ objMainFeatureInfoForm.getOrderList().size());
			/** ******************Ends Here ************************** */
			
			//Added For CR_100
			OrderVO objOrderVOSpecStatus = null;
			if (arlOrderList != null && arlOrderList.size() > 0) {
				for (int i = 0; i < arlOrderList.size(); i++) {
					
					objOrderVOSpecStatus = (OrderVO) arlOrderList.get(i);
					intSpecStatusCode = objOrderVOSpecStatus
					.getSpecStatusCode();
					LogUtil
					.logMessage("*******OrderKey@@@@@@@@@@@t size:"
							+ objMainFeatureInfoForm.getOrderList().size());
					//					Added for CR-10 display of Reason based on Spec Status Code
					objMainFeatureInfoForm.setCurrentSpecStatus(intSpecStatusCode);
								objOrderVO.setDynamicNoFlag(objOrderVOSpecStatus.getDynamicNoFlag());
					LogUtil
					.logMessage("*******OrderKey$$$$$$$$$ size:"
							+ objMainFeatureInfoForm.getOrderList().size());
				}
				
			}
			SpecStatusBI objSpecStatusBI = ServiceFactory.getSpecStatusBO();
			ArrayList arlSpecStatus = objSpecStatusBI
			.fetchSpecNextStatus(objOrderVOSpecStatus);
			objMainFeatureInfoForm.setSpecStatusList(arlSpecStatus);
			//Added For CR_84
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			OrderVO objjOrderVO = (OrderVO) arlOrderList.get(0);
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			objSpecTypeVo.setSpectypeSeqno(objjOrderVO.getSpecTypeSeqNo());
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			ArrayList arlSpecType = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpecType.size() != 0) {
				
				SpecTypeVo objjSpecTypeVo = (SpecTypeVo) arlSpecType.get(0);
				if(objjSpecTypeVo.isGenArrMaintDisPlayFlag())
					objMainFeatureInfoForm.setGenArrLinkFlag(ApplicationConstants.YES);
				else
					objMainFeatureInfoForm.setGenArrLinkFlag(ApplicationConstants.NO);
				
				if(objjSpecTypeVo.isPerfCurveMaintDisPlayFlag())
					objMainFeatureInfoForm.setPerfCurveLinkFlag(ApplicationConstants.YES);
				else
					objMainFeatureInfoForm.setPerfCurveLinkFlag(ApplicationConstants.NO);
			}
			LogUtil.logMessage("objMainFeatureInfoForm.getPerfCurveLinkFlag(): "
					+ objMainFeatureInfoForm.getPerfCurveLinkFlag());
			
			LogUtil.logMessage("objMainFeatureInfoForm.getGenArrLinkFlag(): "
					+ objMainFeatureInfoForm.getGenArrLinkFlag());
			
			LogUtil.logMessage("objMainFeatureInfoForm.SpecTypeSeqNo:"
					+ objSpecTypeVo.getSpectypeSeqno());
			//Added For CR_84 - Ends here
			//Added for CR_93 for displying success message
			if(strFlag.equalsIgnoreCase("Y"))
			{
				objMainFeatureInfoForm.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			}
			//CR_93 Ends here
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in MainFeatureInfoAction..");
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
	 * This method is USED for Getting into the AddComponentInfo Page
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
	
	public ActionForward loadAddCompInfo(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil.logMessage("Enters into MainFeatureInfoAction:loadAddCompInfo");
		String strForwardKey = "";
		MainFeatureInfoForm objMainFeatureInfoForm = (MainFeatureInfoForm) objActionForm;
		try {
			//objMainFeatureInfoForm.setOrderNo(objMainFeatureInfoForm.getOrderNo());
			
			objMainFeatureInfoForm.setCompDesc("");
			objMainFeatureInfoForm.setHdnRevViewCode(objMainFeatureInfoForm
					.getHdnRevViewCode());
			strForwardKey = ApplicationConstants.COMPINFO;
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
	 * This method is for Inserting the ComponentInfo
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
	public ActionForward insertComponentDesc(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil
		.logMessage("Enters into MainFeatureInfoAction:insertComponentDesc");
		String strForwardKey = ApplicationConstants.COMPINFO;
		int intStatusCode=0;
		MainFeatureInfoVO objMainFeatureInfoVO = new MainFeatureInfoVO();
		MainFeatureInfoForm objMainFeatureInfoForm = (MainFeatureInfoForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LogUtil.logMessage(objSession);
		int intOrdeKey = 0;
		String revCode = "";
		String strFlag="";//Added for CR_93
		int intCpyFrmCompSeqNo=0;
		/*Added as per CR 73 to return to General Info Screen by CM68219*/
		String strRedirectFlag=null;
		/*Added as per CR 73 to return to General Info Screen by CM68219 ends*/
		if (objHttpServletRequest.getParameter("orderKeyId") == null) {
			intOrdeKey = objMainFeatureInfoForm.getOrderKey();
		} else {
			intOrdeKey = Integer.parseInt(objHttpServletRequest
					.getParameter("orderKeyId"));
		}
		
		if (objHttpServletRequest.getParameter("revCode") != null
				&& objHttpServletRequest.getParameter("revCode") != "") {
			revCode = String.valueOf(objHttpServletRequest
					.getParameter("revCode"));
		} else {
			
			revCode = "1";
		}
		//Added for CR_93
		if(objHttpServletRequest.getParameter("insFlag")!=null)
		{
			strFlag=objHttpServletRequest.getParameter("insFlag");
		}
		if(strFlag.equalsIgnoreCase("N"))
		{
			if(objHttpServletRequest.getParameter("compSeqNo")!=null)
			{
				intCpyFrmCompSeqNo = Integer.parseInt(objHttpServletRequest.getParameter("compSeqNo"));
			}
		}
		//CR_93 Ends here
		objMainFeatureInfoForm.setOrderKey(intOrdeKey);
		//Changed for CR-74 
		objMainFeatureInfoForm.setHdnRevViewCode(objMainFeatureInfoForm.getHdnRevViewCode());
		ActionForward actionRedirect = null;
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			objMainFeatureInfoVO.setUserID(objLoginVo.getUserID());
			objMainFeatureInfoVO.setOrderKey(objMainFeatureInfoForm
					.getOrderKey());
			objMainFeatureInfoVO.setCompDesc(ApplicationUtil
					.trim(objMainFeatureInfoForm.getCompDesc()));
			objMainFeatureInfoVO.setCpyFrmCompSeqNo(intCpyFrmCompSeqNo);
			objMainFeatureInfoVO
			.setDataLocationType(DatabaseConstants.DATALOCATION);
			MainFeatureInfoBI objMainFeatureInfoBI = ServiceFactory
			.getMainfeatureInfoBO();
			intStatusCode = objMainFeatureInfoBI
			.insertMainFeatures(objMainFeatureInfoVO);
			/*Added as per CR 73 to return to General Info Screen by CM68219 starts*/
			if (intStatusCode == 0) {
				strRedirectFlag="Y";
				objMainFeatureInfoForm.setCompDesc("");
				//CR_93 - Modified the actionRedirect by passing a flag for displaying success messgae after insertion
		        actionRedirect = new ActionForward("MainFeatureInfo.do?method=fetchComponentDesc&orderKeyId="+intOrdeKey+"&revCode="+objMainFeatureInfoForm.getHdnRevViewCode()+"&flag="+"Y", true /* REDIRECT */);
		    }
			 else {
			    	strRedirectFlag="N";
			    	objMainFeatureInfoForm.setMessageID("" + intStatusCode);
			}
			/*Added as per CR 73 to return to General Info Screen by CM68219 ends*/
			objMainFeatureInfoForm.setHdnRevViewCode(objMainFeatureInfoForm
					.getHdnRevViewCode());
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception Block in Action..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		/*Added as per CR 73 to return to General Info Screen by CM68219 starts*/
		if(strRedirectFlag!=null && strRedirectFlag.equalsIgnoreCase("Y")){
			LogUtil.logMessage("FORWARD"+strRedirectFlag);
			return actionRedirect;
		   
		    }else{
		    	LogUtil.logMessage("FORWARD"+strForwardKey);
			return objActionMapping.findForward(strForwardKey);	
		
		  }
		/*Added as per CR 73 to return to General Info Screen by CM68219 ends*/
}
	
	/***************************************************************************
	 * Added for LSDB_CR-62
	 * This method is for Update Display in Spec flag
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException
	 **************************************************************************/
	public ActionForward updateDisplayInSpec(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil
		.logMessage("Enters into MainFeatureInfoAction:updateDisplayInSpec");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intStatusCode;
		ArrayList arlMainFeaList = new ArrayList();
		ArrayList arlModelMainFeaList = new ArrayList();
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlOrderList = new ArrayList();
		OrderVO objOrderVO = new OrderVO();
		SectionVO objSectionVO = new SectionVO();
		MainFeatureInfoVO objMainFeatureInfoVO = new MainFeatureInfoVO();
		MainFeatureInfoForm objMainFeatureInfoForm = (MainFeatureInfoForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LogUtil.logMessage(objSession);
		int intOrdeKey = 0;
		/* Added for CR-74 01-06-09 - Starts Here */
		ArrayList arlRevList = new ArrayList();
		RevisionVO objRevisionVO = new RevisionVO();
		/* Added for CR-74 01-06-09 - Ends Here */
		
		if (objHttpServletRequest.getParameter("orderKeyId") == null) {
			intOrdeKey = objMainFeatureInfoForm.getOrderKey();
		} else {
			intOrdeKey = Integer.parseInt(objHttpServletRequest
					.getParameter("orderKeyId"));
		}
		
		objMainFeatureInfoForm.setOrderKey(intOrdeKey);
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			objMainFeatureInfoVO.setUserID(objLoginVo.getUserID());
			objMainFeatureInfoVO.setOrderKey(objMainFeatureInfoForm
					.getOrderKey());
			objMainFeatureInfoVO
			.setDataLocationType(DatabaseConstants.DATALOCATION);
			objMainFeatureInfoVO.setCompSeqNo(objMainFeatureInfoForm
					.getCompSeqNo());
			objMainFeatureInfoVO.setDisplayInSpec(objMainFeatureInfoForm
					.isHdnDispSpec());
			//Added for CR-74 VV49326 04-06-09
			objMainFeatureInfoVO.setRevCode(objMainFeatureInfoForm.getRevCode());
			MainFeatureInfoBI objMainFeatureInfoBI = ServiceFactory
			.getMainfeatureInfoBO();
			intStatusCode = objMainFeatureInfoBI
			.updateDisplayInSpec(objMainFeatureInfoVO);
			
			if (intStatusCode == 0) {
				
				objMainFeatureInfoForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			}
			
			if (intStatusCode > 0) {
				objMainFeatureInfoForm.setMessageID("" + intStatusCode);
			}
			
			objMainFeatureInfoBI = ServiceFactory.getMainfeatureInfoBO();
			arlMainFeaList = objMainFeatureInfoBI
			.fetchMainFeatures(objMainFeatureInfoVO);
			if (arlMainFeaList != null) {
				objMainFeatureInfoForm.setMainFeatureList(arlMainFeaList);
			}
			
			//Added for LSDB_CR-62 by ka57588
			objMainFeatureInfoVO.setDisplayInSpec(false);
			arlModelMainFeaList = objMainFeatureInfoBI
			.fetchModelMainFeatures(objMainFeatureInfoVO);
			if (arlModelMainFeaList != null) {
				objMainFeatureInfoForm
				.setModelMainFeatureList(arlModelMainFeaList);
			}
			//Ends 
			
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			
			objSectionVO.setOrderKey(objMainFeatureInfoForm.getOrderKey());
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSectionVO.setUserID(objLoginVo.getUserID());
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null) {
				objMainFeatureInfoForm.setSectionList(arlSectionList);
			}
			
			LogUtil.logMessage("ArrayList Value in OrderSectionAction:"
					+ objMainFeatureInfoForm.getSectionList().size());
			/** ****************** Ends Here ***************************** */
			
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			LogUtil.logMessage("*******OrderKey inside Fetch orders:"
					+ objMainFeatureInfoForm.getOrderKey());
			objOrderVO.setOrderKey(objMainFeatureInfoForm.getOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objMainFeatureInfoForm.setOrderList(arlOrderList);
			
			LogUtil
			.logMessage("*******OrderKey inside Fetch orders Arraylist size:"
					+ objMainFeatureInfoForm.getOrderList().size());
			/** ******************Ends Here ************************** */
			LogUtil.logMessage("ArrayList Value in MainFeatureInfoAction:"
					+ objMainFeatureInfoForm.getOrderList().size());
			// Commented for CR-74 01-06-09
			// objMainFeatureInfoForm.setHdnRevViewCode(objMainFeatureInfoForm
			// .getHdnRevViewCode());
			/* Added for CR-74 01-06-09 - Starts Here */
			
			objRevisionVO.setUserID(objLoginVo.getUserID());

			OrderSectionBI objOrderSectionBO = ServiceFactory
					.getOrderSectionBO();

			arlRevList = objOrderSectionBO.fetchRevision(objRevisionVO);
			if (arlRevList != null) {
				objMainFeatureInfoForm.setRevisionList(arlRevList);
				objMainFeatureInfoForm.setRevCode(objMainFeatureInfoForm
						.getRevCode());
				objMainFeatureInfoForm.setHdnRevViewCode(String
						.valueOf(objMainFeatureInfoForm.getRevCode()));
			}
			LogUtil.logMessage("Rev Code"
					+ objMainFeatureInfoForm.getHdnRevViewCode());
			/* Added for CR-74 01-06-09* - Ends Here */
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception Block in Action..");
			LogUtil.logMessage(" Exception occured in Action:" + objExp);
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
	 * This method is for Update the ComponentInfo
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
	public ActionForward updateComponentDesc(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil
		.logMessage("Enters into MainFeatureInfoAction:updateComponentDesc");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intStatusCode;
		int intSpecStatusCode=0;
		ArrayList arlMainFeaList = new ArrayList();
		ArrayList arlModelMainFeaList = new ArrayList();
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlOrderList = new ArrayList();
		OrderVO objOrderVO = new OrderVO();
		SectionVO objSectionVO = new SectionVO();
		MainFeatureInfoVO objMainFeatureInfoVO = new MainFeatureInfoVO();
		MainFeatureInfoForm objMainFeatureInfoForm = (MainFeatureInfoForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LogUtil.logMessage(objSession);
		int intOrdeKey = 0;
		/* Added for CR-74 01-06-09 - Starts Here */
		ArrayList arlRevList = new ArrayList();
		RevisionVO objRevisionVO = new RevisionVO();
		/* Added for CR-74 01-06-09 - Ends Here */
		
		if (objHttpServletRequest.getParameter("orderKeyId") == null) {
			intOrdeKey = objMainFeatureInfoForm.getOrderKey();
		} else {
			intOrdeKey = Integer.parseInt(objHttpServletRequest
					.getParameter("orderKeyId"));
		}
		
		objMainFeatureInfoForm.setOrderKey(intOrdeKey);
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			/* Added for CR-74 01-06-09 - Starts Here */
			
			objRevisionVO.setUserID(objLoginVo.getUserID());

			OrderSectionBI objOrderSectionBO = ServiceFactory
					.getOrderSectionBO();

			arlRevList = objOrderSectionBO.fetchRevision(objRevisionVO);
			if (arlRevList != null) {
				objMainFeatureInfoForm.setRevisionList(arlRevList);
				objMainFeatureInfoForm.setRevCode(objMainFeatureInfoForm
						.getRevCode());
				objMainFeatureInfoForm.setHdnRevViewCode(String
						.valueOf(objMainFeatureInfoForm.getRevCode()));
			}
			LogUtil.logMessage("Rev Code"
					+ objMainFeatureInfoForm.getHdnRevViewCode());
			/* Added for CR-74 01-06-09* - Ends Here */
			objMainFeatureInfoVO.setUserID(objLoginVo.getUserID());
			objMainFeatureInfoVO.setOrderKey(objMainFeatureInfoForm
					.getOrderKey());
			objMainFeatureInfoVO
			.setDataLocationType(DatabaseConstants.DATALOCATION);
			objMainFeatureInfoVO.setCompinfoSeqNo(objMainFeatureInfoForm
					.getCompinfoSeqNo());
			objMainFeatureInfoVO.setHdCompDesc(ApplicationUtil
					.trim(objMainFeatureInfoForm.getHdCompDesc()));
			LogUtil
			.logMessage("ComponentDesc in MainFeatureInfoAction:updateComponentDesc:"
					+ objMainFeatureInfoVO.getHdCompDesc());
			LogUtil
			.logMessage("CompinfoSeqNo in MainFeatureInfoAction:updateComponentDesc:"
					+ objMainFeatureInfoVO.getCompinfoSeqNo());
			// Added for CR-74 VV49326 01-06-09
			objMainFeatureInfoVO
					.setRevCode(objMainFeatureInfoForm.getRevCode());
			MainFeatureInfoBI objMainFeatureInfoBI = ServiceFactory
			.getMainfeatureInfoBO();
			intStatusCode = objMainFeatureInfoBI
			.updateMainFeatures(objMainFeatureInfoVO);
			
			if (intStatusCode == 0) {
				
				objMainFeatureInfoForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			}
			
			if (intStatusCode > 0) {
				objMainFeatureInfoForm.setMessageID("" + intStatusCode);
			}
			
			objMainFeatureInfoBI = ServiceFactory.getMainfeatureInfoBO();
			arlMainFeaList = objMainFeatureInfoBI
			.fetchMainFeatures(objMainFeatureInfoVO);
			if (arlMainFeaList != null) {
				objMainFeatureInfoForm.setMainFeatureList(arlMainFeaList);
			}
			LogUtil.logMessage("ArrayList Value in MainFeatureInfoAction:"
					+ objMainFeatureInfoForm.getMainFeatureList().size());
			
			//Added for LSDB_CR-62 by ka57588
			objMainFeatureInfoVO.setDisplayInSpec(false);
			arlModelMainFeaList = objMainFeatureInfoBI
			.fetchModelMainFeatures(objMainFeatureInfoVO);
			if (arlModelMainFeaList != null) {
				objMainFeatureInfoForm
				.setModelMainFeatureList(arlModelMainFeaList);
			}
			//Ends 
			
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			
			objSectionVO.setOrderKey(objMainFeatureInfoForm.getOrderKey());
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSectionVO.setUserID(objLoginVo.getUserID());
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null) {
				objMainFeatureInfoForm.setSectionList(arlSectionList);
			}
			
			LogUtil.logMessage("ArrayList Value in OrderSectionAction:"
					+ objMainFeatureInfoForm.getSectionList().size());
			/** ****************** Ends Here ***************************** */
			
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			LogUtil.logMessage("*******OrderKey inside Fetch orders:"
					+ objMainFeatureInfoForm.getOrderKey());
			objOrderVO.setOrderKey(objMainFeatureInfoForm.getOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objMainFeatureInfoForm.setOrderList(arlOrderList);
			
			LogUtil
			.logMessage("*******OrderKey inside Fetch orders Arraylist size:"
					+ objMainFeatureInfoForm.getOrderList().size());
			/** ******************Ends Here ************************** */
			LogUtil.logMessage("ArrayList Value in MainFeatureInfoAction:"
					+ objMainFeatureInfoForm.getOrderList().size());
//			Added For CR_100
			OrderVO objOrderVOSpecStatus = null;
			if (arlOrderList != null && arlOrderList.size() > 0) {
				for (int i = 0; i < arlOrderList.size(); i++) {
					
					objOrderVOSpecStatus = (OrderVO) arlOrderList.get(i);
					intSpecStatusCode = objOrderVOSpecStatus
					.getSpecStatusCode();
					LogUtil
					.logMessage("*******OrderKey@@@@@@@@@@@t size:"
							+ objMainFeatureInfoForm.getOrderList().size());
					//					Added for CR-10 display of Reason based on Spec Status Code
					objMainFeatureInfoForm.setCurrentSpecStatus(intSpecStatusCode);
								objOrderVO.setDynamicNoFlag(objOrderVOSpecStatus.getDynamicNoFlag());
					LogUtil
					.logMessage("*******OrderKey$$$$$$$$$ size:"
							+ objMainFeatureInfoForm.getOrderList().size());
				}
				
			}
			SpecStatusBI objSpecStatusBI = ServiceFactory.getSpecStatusBO();
			ArrayList arlSpecStatus = objSpecStatusBI
			.fetchSpecNextStatus(objOrderVOSpecStatus);
			objMainFeatureInfoForm.setSpecStatusList(arlSpecStatus);
			
			// Commented for CR-74 01-06-09
			// objMainFeatureInfoForm.setHdnRevViewCode(objMainFeatureInfoForm
			// .getHdnRevViewCode());
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception Block in Action..");
			LogUtil.logMessage(" Exception occured in Action:" + objExp);
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
	 * This method is used to Delete the selected ComponentInfo
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
	
	public ActionForward deleteComponentDesc(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil
		.logMessage("Enters into MainFeatureInfoAction:deleteComponentDesc");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intStatusCode;
		int intSpecStatusCode =0;
		ArrayList arlMainFeaList = new ArrayList();
		ArrayList arlModelMainFeaList = new ArrayList();
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlOrderList = new ArrayList();
		OrderVO objOrderVO = new OrderVO();
		SectionVO objSectionVO = new SectionVO();
		MainFeatureInfoVO objMainFeatureInfoVO = new MainFeatureInfoVO();
		MainFeatureInfoForm objMainFeatureInfoForm = (MainFeatureInfoForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LogUtil.logMessage(objSession);
		int intOrdeKey = 0;
		/* Added for CR-74 01-06-09 - Starts Here */
		ArrayList arlRevList = new ArrayList();
		RevisionVO objRevisionVO = new RevisionVO();
		/* Added for CR-74 01-06-09 - Ends Here */
		
		if (objHttpServletRequest.getParameter("orderKeyId") == null) {
			intOrdeKey = objMainFeatureInfoForm.getOrderKey();
		} else {
			intOrdeKey = Integer.parseInt(objHttpServletRequest
					.getParameter("orderKeyId"));
		}
		
		objMainFeatureInfoForm.setOrderKey(intOrdeKey);
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			/* Added for CR-74 01-06-09 - Starts Here */
			
			objRevisionVO.setUserID(objLoginVo.getUserID());

			OrderSectionBI objOrderSectionBO = ServiceFactory
					.getOrderSectionBO();

			arlRevList = objOrderSectionBO.fetchRevision(objRevisionVO);
			if (arlRevList != null) {
				objMainFeatureInfoForm.setRevisionList(arlRevList);
				objMainFeatureInfoForm.setRevCode(objMainFeatureInfoForm
						.getRevCode());
				objMainFeatureInfoForm.setHdnRevViewCode(String
						.valueOf(objMainFeatureInfoForm.getRevCode()));
			}
			LogUtil.logMessage("Rev Code"
					+ objMainFeatureInfoForm.getHdnRevViewCode());
			/* Added for CR-74 01-06-09* - Ends Here */
			objMainFeatureInfoVO.setUserID(objLoginVo.getUserID());
			objMainFeatureInfoVO.setOrderKey(objMainFeatureInfoForm
					.getOrderKey());
			objMainFeatureInfoVO
			.setDataLocationType(DatabaseConstants.DATALOCATION);
			objMainFeatureInfoVO.setCompinfoSeqNo(objMainFeatureInfoForm
					.getCompinfoSeqNo());
			
			LogUtil
			.logMessage("CompinfoSeqNo in MainFeatureInfoAction:deleteComponentDesc:"
					+ objMainFeatureInfoVO.getCompinfoSeqNo());
			// Added for CR-74 VV49326 01-06-09
			objMainFeatureInfoVO
					.setRevCode(objMainFeatureInfoForm.getRevCode());
			MainFeatureInfoBI objMainFeatureInfoBI = ServiceFactory
			.getMainfeatureInfoBO();
			intStatusCode = objMainFeatureInfoBI
			.deleteMainFeatures(objMainFeatureInfoVO);
			
			if (intStatusCode == 0) {
				
				objMainFeatureInfoForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			}
			
			if (intStatusCode > 0) {
				objMainFeatureInfoForm.setMessageID("" + intStatusCode);
			}
			
			objMainFeatureInfoBI = ServiceFactory.getMainfeatureInfoBO();
			arlMainFeaList = objMainFeatureInfoBI
			.fetchMainFeatures(objMainFeatureInfoVO);
			if (arlMainFeaList != null) {
				objMainFeatureInfoForm.setMainFeatureList(arlMainFeaList);
			}
			LogUtil.logMessage("ArrayList Value in MainFeatureInfoAction:"
					+ objMainFeatureInfoForm.getMainFeatureList().size());
			
			//Added for LSDB_CR-62 by ka57588
			objMainFeatureInfoVO.setDisplayInSpec(false);
			arlModelMainFeaList = objMainFeatureInfoBI
			.fetchModelMainFeatures(objMainFeatureInfoVO);
			if (arlModelMainFeaList != null) {
				objMainFeatureInfoForm
				.setModelMainFeatureList(arlModelMainFeaList);
			}
			//Ends 
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			
			objSectionVO.setOrderKey(objMainFeatureInfoForm.getOrderKey());
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSectionVO.setUserID(objLoginVo.getUserID());
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null) {
				objMainFeatureInfoForm.setSectionList(arlSectionList);
			}
			
			LogUtil.logMessage("ArrayList Value in OrderSectionAction:"
					+ objMainFeatureInfoForm.getSectionList().size());
			/** ****************** Ends Here ***************************** */
			
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			LogUtil.logMessage("*******OrderKey inside Fetch orders:"
					+ objMainFeatureInfoForm.getOrderKey());
			objOrderVO.setOrderKey(objMainFeatureInfoForm.getOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objMainFeatureInfoForm.setOrderList(arlOrderList);
			
			LogUtil
			.logMessage("*******OrderKey inside Fetch orders Arraylist size:"
					+ objMainFeatureInfoForm.getOrderList().size());
			/** ******************Ends Here ************************** */
			LogUtil.logMessage("ArrayList Value in MainFeatureInfoAction:"
					+ objMainFeatureInfoForm.getOrderList().size());
			
			//Added For CR_Fix
			//Added For CR_100
			OrderVO objOrderVOSpecStatus = null;
			if (arlOrderList != null && arlOrderList.size() > 0) {
				for (int i = 0; i < arlOrderList.size(); i++) {
					
					objOrderVOSpecStatus = (OrderVO) arlOrderList.get(i);
					intSpecStatusCode = objOrderVOSpecStatus
					.getSpecStatusCode();
					LogUtil
					.logMessage("*******OrderKey@@@@@@@@@@@t size:"
							+ objMainFeatureInfoForm.getOrderList().size());
					//					Added for CR-10 display of Reason based on Spec Status Code
					objMainFeatureInfoForm.setCurrentSpecStatus(intSpecStatusCode);
								objOrderVO.setDynamicNoFlag(objOrderVOSpecStatus.getDynamicNoFlag());
					LogUtil
					.logMessage("*******OrderKey$$$$$$$$$ size:"
							+ objMainFeatureInfoForm.getOrderList().size());
				}
				
			}
			SpecStatusBI objSpecStatusBI = ServiceFactory.getSpecStatusBO();
			ArrayList arlSpecStatus = objSpecStatusBI
			.fetchSpecNextStatus(objOrderVOSpecStatus);
			objMainFeatureInfoForm.setSpecStatusList(arlSpecStatus);
			
			// Commented for CR-74 01-06-09
			// objMainFeatureInfoForm.setHdnRevViewCode(objMainFeatureInfoForm
			// .getHdnRevViewCode());
		}
		
		catch (Exception objExp) {
			
			LogUtil
			.logMessage(" Exception occured in MainFeatureInfoAction:deleteComponentDesc"
					+ objExp);
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		
		return objActionMapping.findForward(strForwardKey);
	}
	
	//Added For CR_79 Adding PDF Header Image by RR68151
	
	/***************************************************************************
	 * This method is Used for turn ON/OFF 
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
	
	public ActionForward turnPDFHeaderImage(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		String strForwardKey = ApplicationConstants.SUCCESS;
		LogUtil.logMessage("Enters into MainFeatureInfoAction:turnPDFHeaderImage");

		int intStatusCode;
		int intSpecStatusCode=0;
		ArrayList arlMainFeaList = new ArrayList();
		ArrayList arlModelMainFeaList = new ArrayList();
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlOrderList = new ArrayList();
		OrderVO objOrderVO = new OrderVO();
		SectionVO objSectionVO = new SectionVO();
		MainFeatureInfoVO objMainFeatureInfoVO = new MainFeatureInfoVO();
		MainFeatureInfoForm objMainFeatureInfoForm = (MainFeatureInfoForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		int intOrdeKey = 0;
		int intRevCode = 0;
		String strPDFHeaderFlag = null;
		ArrayList arlRevList = new ArrayList();
		RevisionVO objRevisionVO = new RevisionVO();
		
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);

			if (objHttpServletRequest.getParameter("orderKeyId") == null) {
				intOrdeKey = objMainFeatureInfoForm.getOrderKey();
			} else {
				intOrdeKey = Integer.parseInt(objHttpServletRequest
						.getParameter("orderKeyId"));
			}
			
			objMainFeatureInfoForm.setOrderKey(intOrdeKey);
			
			if ((String) objHttpServletRequest.getParameter("flag") != null) {
				
				strPDFHeaderFlag = (String) objHttpServletRequest.getParameter("flag");
			}
			
			objOrderVO.setOrderKey(intOrdeKey);
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			objOrderVO.setPdfHeaderFlag(strPDFHeaderFlag);
			
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			intStatusCode = objOrderBI.updateOrders(objOrderVO);
			
			if (intStatusCode == 0) {
				
				objMainFeatureInfoForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			}
			
			if (intStatusCode > 0) {
				objMainFeatureInfoForm.setMessageID("" + intStatusCode);
			}

			if (objMainFeatureInfoForm.getRevCode() == 0) {
				intRevCode = 1;//Default No revision
			} else if ((String) objHttpServletRequest.getParameter("revCode") != null) {
				intRevCode = Integer.parseInt(objHttpServletRequest
						.getParameter("revCode"));
			} else {
				intRevCode = objMainFeatureInfoForm.getRevCode();
			}			
			objRevisionVO.setUserID(objLoginVo.getUserID());

			OrderSectionBI objOrderSectionBO = ServiceFactory
					.getOrderSectionBO();

			arlRevList = objOrderSectionBO.fetchRevision(objRevisionVO);
			if (arlRevList != null) {
				objMainFeatureInfoForm.setRevisionList(arlRevList);
				objMainFeatureInfoForm.setRevCode(intRevCode);
				objMainFeatureInfoForm.setHdnRevViewCode(String
						.valueOf(objMainFeatureInfoForm.getRevCode()));
			}
			LogUtil.logMessage("Rev Code"
					+ objMainFeatureInfoForm.getHdnRevViewCode());

			objMainFeatureInfoVO.setUserID(objLoginVo.getUserID());
			objMainFeatureInfoVO.setOrderKey(objMainFeatureInfoForm
					.getOrderKey());
			objMainFeatureInfoVO
			.setDataLocationType(DatabaseConstants.DATALOCATION);

			objMainFeatureInfoVO
					.setRevCode(objMainFeatureInfoForm.getRevCode());

			MainFeatureInfoBI objMainFeatureInfoBI = ServiceFactory
			.getMainfeatureInfoBO();
			arlMainFeaList = objMainFeatureInfoBI
			.fetchMainFeatures(objMainFeatureInfoVO);
			if (arlMainFeaList != null) {
				objMainFeatureInfoForm.setMainFeatureList(arlMainFeaList);
			}
			LogUtil.logMessage("ArrayList Value in MainFeatureInfoAction:"
					+ objMainFeatureInfoForm.getMainFeatureList().size());

			objMainFeatureInfoVO.setDisplayInSpec(false);
			arlModelMainFeaList = objMainFeatureInfoBI
			.fetchModelMainFeatures(objMainFeatureInfoVO);
			if (arlModelMainFeaList != null) {
				objMainFeatureInfoForm
				.setModelMainFeatureList(arlModelMainFeaList);
			}
			
			objSectionVO.setUserID(objLoginVo.getUserID());
			
			objSectionVO.setOrderKey(objMainFeatureInfoForm.getOrderKey());
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null) {
				objMainFeatureInfoForm.setSectionList(arlSectionList);
			}
			
			LogUtil.logMessage("ArrayList Value in OrderSectionAction:"
					+ objMainFeatureInfoForm.getSectionList().size());
			
			/** ******************Ends Here ************************** */

			LogUtil.logMessage("*******OrderKey inside Fetch orders:"
					+ objMainFeatureInfoForm.getOrderKey());
			objOrderVO.setOrderKey(objMainFeatureInfoForm.getOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);

			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objMainFeatureInfoForm.setOrderList(arlOrderList);
//			Added For CR_100
			OrderVO objOrderVOSpecStatus = null;
			if (arlOrderList != null && arlOrderList.size() > 0) {
				for (int i = 0; i < arlOrderList.size(); i++) {
					
					objOrderVOSpecStatus = (OrderVO) arlOrderList.get(i);
					intSpecStatusCode = objOrderVOSpecStatus
					.getSpecStatusCode();
					LogUtil
					.logMessage("*******OrderKey@@@@@@@@@@@t size:"
							+ objMainFeatureInfoForm.getOrderList().size());
					//					Added for CR-10 display of Reason based on Spec Status Code
					objMainFeatureInfoForm.setCurrentSpecStatus(intSpecStatusCode);
								objOrderVO.setDynamicNoFlag(objOrderVOSpecStatus.getDynamicNoFlag());
					LogUtil
					.logMessage("*******OrderKey$$$$$$$$$ size:"
							+ objMainFeatureInfoForm.getOrderList().size());
				}
				
			}
			SpecStatusBI objSpecStatusBI = ServiceFactory.getSpecStatusBO();
			ArrayList arlSpecStatus = objSpecStatusBI
			.fetchSpecNextStatus(objOrderVOSpecStatus);
			objMainFeatureInfoForm.setSpecStatusList(arlSpecStatus);
			
			
			
		} catch (Exception objExp) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			LogUtil
			.logMessage(" Exception occured in MainFeatureInfoAction:turnPDFHeaderImage"
					+ objExp);
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);

		}
		return objActionMapping.findForward(strForwardKey);
	}
	
	//	Added For CR_79 Adding PDF Header Image by RR68151 - Ends here
	/***************************************************************************
	 * This method is for Inserting the ComponentInfo
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
	public ActionForward rearrangeCompDesc(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into MainFeatureInfoAction:rearrangeCompDesc");
		
		String strForwardKey = ApplicationConstants.COMPINFO;
		int intStatusCode=0;
		int intOrdeKey = 0;
		String revCode = "";
		String strFlag="";
		String strRowIndexList=null;
		ArrayList arlCompSeqNoList = new ArrayList();
		String strRedirectFlag=null;
		
		MainFeatureInfoVO objMainFeatureInfoVO = new MainFeatureInfoVO();
		MainFeatureInfoForm objMainFeatureInfoForm = (MainFeatureInfoForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LogUtil.logMessage(objSession);

		if (objHttpServletRequest.getParameter("orderKeyId") == null) {
			intOrdeKey = objMainFeatureInfoForm.getOrderKey();
		} else {
			intOrdeKey = Integer.parseInt(objHttpServletRequest
					.getParameter("orderKeyId"));
		}
		
		if (objHttpServletRequest.getParameter("revCode") != null
				&& objHttpServletRequest.getParameter("revCode") != "") {
			revCode = String.valueOf(objHttpServletRequest
					.getParameter("revCode"));
		} else {
			
			revCode = "1";
		}
		
		strRowIndexList=objMainFeatureInfoForm.getRowIndexList();
		objMainFeatureInfoForm.setOrderKey(intOrdeKey);
		objMainFeatureInfoForm.setHdnRevViewCode(objMainFeatureInfoForm.getHdnRevViewCode());
		
		ActionForward actionRedirect = null;
		try {
			LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			
			objMainFeatureInfoVO.setUserID(objLoginVo.getUserID());
			objMainFeatureInfoVO.setOrderKey(objMainFeatureInfoForm.getOrderKey());
			objMainFeatureInfoVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			String[] token = strRowIndexList.split ("\\,");
			 for (int i=0; i < token.length; i++){
				 arlCompSeqNoList.add(token[i]);
			 }
			
			String [] arlClaSeqNoArry =new String[arlCompSeqNoList.size()];
			int i=0;
			for ( Iterator iter = arlCompSeqNoList.iterator(); iter.hasNext();) {
					
				String element = (String) iter.next();
				arlClaSeqNoArry[i]=element;
				i++;
			}
			objMainFeatureInfoVO.setCompSeqNoList(arlClaSeqNoArry);
			
			MainFeatureInfoBI objMainFeatureInfoBI = ServiceFactory.getMainfeatureInfoBO();
			intStatusCode = objMainFeatureInfoBI.rearrangeCompDesc(objMainFeatureInfoVO);
			
			if (intStatusCode == 0) {
				strRedirectFlag="Y";
				objMainFeatureInfoForm.setCompDesc("");
				
		        actionRedirect = new ActionForward("MainFeatureInfo.do?method=fetchComponentDesc&orderKeyId="+intOrdeKey+"&revCode="+objMainFeatureInfoForm.getHdnRevViewCode()+"&flag="+strRedirectFlag, true /* REDIRECT */);
		    }
			 else {
			    	strRedirectFlag="N";
			    	objMainFeatureInfoForm.setMessageID("" + intStatusCode);
			}
			objMainFeatureInfoForm.setHdnRevViewCode(objMainFeatureInfoForm
					.getHdnRevViewCode());
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception Block in Action..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		if(strRedirectFlag!=null && strRedirectFlag.equalsIgnoreCase("Y")){
			LogUtil.logMessage("FORWARD"+strRedirectFlag);
			return actionRedirect;
		   
		    }else{
		    	LogUtil.logMessage("FORWARD"+strForwardKey);
			return objActionMapping.findForward(strForwardKey);	
		
		  }
}
//	Added For CR_100 
	/***************************************************************************
	 * This method is for Inserting the ComponentInfo
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
	public ActionForward publish(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into MainFeatureInfoAction:publish");
		
		String strForwardKey = ApplicationConstants.SUCCESS;
		int modSeqNo = 0;
		int orderKey = 0;
		int revCode=1;
		String revFlag=null;
		ActionForward actionRedirect = null;
		int specStatusCode=0;
		String finalFlag="";
		String actionType = null;
		
		MainFeatureInfoForm objMainFeatureInfoForm = (MainFeatureInfoForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		modSeqNo = Integer.parseInt(objHttpServletRequest
				.getParameter("modSeqNo"));
		if (objHttpServletRequest.getParameter("orderKey") != null
				&& objHttpServletRequest.getParameter("orderKey") != "") {
			orderKey = Integer.parseInt(objHttpServletRequest
					.getParameter("orderKey"));
			objMainFeatureInfoForm.setOrderKey(orderKey);
		} else {
			orderKey=objMainFeatureInfoForm.getOrderKey();
		}
				
		try {
			actionType=objMainFeatureInfoForm.getActionType();
			specStatusCode=objMainFeatureInfoForm.getSpecStatusCode();
			revFlag=objMainFeatureInfoForm.getRevFlag();
			if(null!=(objMainFeatureInfoForm.getFinalFlag())){
			finalFlag=objMainFeatureInfoForm.getFinalFlag();
			}else{
				finalFlag="";
			}
			revCode=objMainFeatureInfoForm.getRevCode();
			LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			actionRedirect = new ActionForward("OrderSection.do?method=publish&modSeqNo="+modSeqNo+"&revCode="+revCode+"&specStatusCode="+specStatusCode+"&revFlag="+revFlag+"&finalFlag="+finalFlag+"&orderKey="+orderKey+"&actionType="+actionType, true /* REDIRECT */);
		    			
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception Block in Action..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			  			
		}
	return actionRedirect; 
		    
}
	
	
//	Added For CR_104
	/***************************************************************************
	 * This method is for Inserting the ComponentInfo
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
	public ActionForward publishAndNotification(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into MainFeatureInfoAction:publishAndNotification");
		
		String strForwardKey = ApplicationConstants.SUCCESS;
		int modSeqNo = 0;
		int orderKey = 0;
		int revCode=1;
		String revFlag=null;
		ActionForward actionRedirect = null;
		int specStatusCode=0;
		String finalFlag="";
		String actionType = null;
		int intStatusCode=0;
		String strRedirectFlag=null;
		
		MainFeatureInfoForm objMainFeatureInfoForm = (MainFeatureInfoForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		modSeqNo = Integer.parseInt(objHttpServletRequest
				.getParameter("modSeqNo"));
		if (objHttpServletRequest.getParameter("orderKey") != null
				&& objHttpServletRequest.getParameter("orderKey") != "") {
			orderKey = Integer.parseInt(objHttpServletRequest
					.getParameter("orderKey"));
			objMainFeatureInfoForm.setOrderKey(orderKey);
		} else {
			orderKey=objMainFeatureInfoForm.getOrderKey();
		}	
		
		try {
			
			
			actionType=objMainFeatureInfoForm.getActionType();
			specStatusCode=objMainFeatureInfoForm.getSpecStatusCode();
			revFlag=objMainFeatureInfoForm.getRevFlag();
			if(null!=(objMainFeatureInfoForm.getFinalFlag())){
			finalFlag=objMainFeatureInfoForm.getFinalFlag();
			}else{
				finalFlag="";
			}
			revCode=objMainFeatureInfoForm.getRevCode();
			LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			MainFeatureInfoVO objMainFeatureInfoVO=new MainFeatureInfoVO();
			objMainFeatureInfoVO.setOrderKey(orderKey);
			objMainFeatureInfoVO.setSubject(objMainFeatureInfoForm.getSubject());
			objMainFeatureInfoVO.setBodyCont(objMainFeatureInfoForm.getBodyCont());
			objMainFeatureInfoVO.setRevFlag(revFlag);
			objMainFeatureInfoVO.setFinalFlag(finalFlag);
			objMainFeatureInfoVO.setRevCode(revCode);
			objMainFeatureInfoVO.setUserID(objLoginVo.getUserID());
			
			MainFeatureInfoBI objMainFeatureInfoBI = ServiceFactory.getMainfeatureInfoBO();
			MainFeatureInfoVO objMainFeatureInfo = objMainFeatureInfoBI.publishAndNotification(objMainFeatureInfoVO);
			if (intStatusCode == 0) {
				strRedirectFlag="Y";
				objMainFeatureInfoForm.setCompDesc("");
				
		        actionRedirect = new ActionForward("MainFeatureInfo.do?method=fetchComponentDesc&orderKeyId="+objMainFeatureInfo.getOrderKey()+"&revCode="+revCode+"&flag="+strRedirectFlag, true /* REDIRECT */);
		    }
			 else {
			    	strRedirectFlag="N";
			    	objMainFeatureInfoForm.setMessageID("" + intStatusCode);
			}
       
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception Block in Action..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			  			
		}
		if (strRedirectFlag.equalsIgnoreCase("Y"))
			return actionRedirect;
		else
			return objActionMapping.findForward(strForwardKey);
	}		
	//Added For CR_104 Saving General Information Text by RR68151
	
	/***************************************************************************
	 * This method is Used for Edit General Information Text for PDFs
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
	
	public ActionForward saveGenInfoText(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		String strForwardKey = ApplicationConstants.SUCCESS;
		LogUtil.logMessage("Enters into MainFeatureInfoAction:saveGenInfoText");

		int intStatusCode;
		int intSpecStatusCode=0;
		ArrayList arlMainFeaList = new ArrayList();
		ArrayList arlModelMainFeaList = new ArrayList();
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlOrderList = new ArrayList();
		OrderVO objOrderVO = new OrderVO();
		SectionVO objSectionVO = new SectionVO();
		MainFeatureInfoVO objMainFeatureInfoVO = new MainFeatureInfoVO();
		MainFeatureInfoForm objMainFeatureInfoForm = (MainFeatureInfoForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		int intRevCode = 0;
		ArrayList arlRevList = new ArrayList();
		RevisionVO objRevisionVO = new RevisionVO();
		
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);
			
			objOrderVO.setOrderKey(objMainFeatureInfoForm.getOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			objOrderVO.setGenInfoText(objMainFeatureInfoForm.getGenInfoText());
			//Added for CR_104 - General Information Text Flag 
			objOrderVO.setPresrveGenInfoFlag(objMainFeatureInfoForm.getPresrveGenInfoFlag());
			//Ends here
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			intStatusCode = objOrderBI.updateOrders(objOrderVO);
			
			if (intStatusCode == 0) {
				
				objMainFeatureInfoForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			}
			
			if (intStatusCode > 0) {
				objMainFeatureInfoForm.setMessageID("" + intStatusCode);
			}

			if (objMainFeatureInfoForm.getRevCode() == 0) {
				intRevCode = 1;//Default No revision
			} else if ((String) objHttpServletRequest.getParameter("revCode") != null) {
				intRevCode = Integer.parseInt(objHttpServletRequest
						.getParameter("revCode"));
			} else {
				intRevCode = objMainFeatureInfoForm.getRevCode();
			}			
			objRevisionVO.setUserID(objLoginVo.getUserID());

			OrderSectionBI objOrderSectionBO = ServiceFactory
					.getOrderSectionBO();

			arlRevList = objOrderSectionBO.fetchRevision(objRevisionVO);
			if (arlRevList != null) {
				objMainFeatureInfoForm.setRevisionList(arlRevList);
				objMainFeatureInfoForm.setRevCode(intRevCode);
				objMainFeatureInfoForm.setHdnRevViewCode(String
						.valueOf(objMainFeatureInfoForm.getRevCode()));
			}
			LogUtil.logMessage("Rev Code"
					+ objMainFeatureInfoForm.getHdnRevViewCode());

			objMainFeatureInfoVO.setUserID(objLoginVo.getUserID());
			objMainFeatureInfoVO.setOrderKey(objMainFeatureInfoForm
					.getOrderKey());
			objMainFeatureInfoVO
			.setDataLocationType(DatabaseConstants.DATALOCATION);

			objMainFeatureInfoVO
					.setRevCode(objMainFeatureInfoForm.getRevCode());

			MainFeatureInfoBI objMainFeatureInfoBI = ServiceFactory
			.getMainfeatureInfoBO();
			arlMainFeaList = objMainFeatureInfoBI
			.fetchMainFeatures(objMainFeatureInfoVO);
			if (arlMainFeaList != null) {
				objMainFeatureInfoForm.setMainFeatureList(arlMainFeaList);
			}
			LogUtil.logMessage("ArrayList Value in MainFeatureInfoAction:"
					+ objMainFeatureInfoForm.getMainFeatureList().size());

			objMainFeatureInfoVO.setDisplayInSpec(false);
			arlModelMainFeaList = objMainFeatureInfoBI
			.fetchModelMainFeatures(objMainFeatureInfoVO);
			if (arlModelMainFeaList != null) {
				objMainFeatureInfoForm
				.setModelMainFeatureList(arlModelMainFeaList);
			}
			
			objSectionVO.setUserID(objLoginVo.getUserID());
			
			objSectionVO.setOrderKey(objMainFeatureInfoForm.getOrderKey());
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null) {
				objMainFeatureInfoForm.setSectionList(arlSectionList);
			}
			
			LogUtil.logMessage("ArrayList Value in OrderSectionAction:"
					+ objMainFeatureInfoForm.getSectionList().size());
			
			/** ******************Ends Here ************************** */

			LogUtil.logMessage("*******OrderKey inside Fetch orders:"
					+ objMainFeatureInfoForm.getOrderKey());
			objOrderVO.setOrderKey(objMainFeatureInfoForm.getOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);

			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objMainFeatureInfoForm.setOrderList(arlOrderList);
			
			OrderVO objOrderVOSpecStatus = null;
			if (arlOrderList != null && arlOrderList.size() > 0) {
				for (int i = 0; i < arlOrderList.size(); i++) {
					
					objOrderVOSpecStatus = (OrderVO) arlOrderList.get(i);
					intSpecStatusCode = objOrderVOSpecStatus
					.getSpecStatusCode();
					
					objMainFeatureInfoForm.setCurrentSpecStatus(intSpecStatusCode);
								objOrderVO.setDynamicNoFlag(objOrderVOSpecStatus.getDynamicNoFlag());
				}
				
			}
			SpecStatusBI objSpecStatusBI = ServiceFactory.getSpecStatusBO();
			ArrayList arlSpecStatus = objSpecStatusBI
			.fetchSpecNextStatus(objOrderVOSpecStatus);
			objMainFeatureInfoForm.setSpecStatusList(arlSpecStatus);
			
		} catch (Exception objExp) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			LogUtil
			.logMessage(" Exception occured in MainFeatureInfoAction:saveGenInfoText"
					+ objExp);
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);

		}
		return objActionMapping.findForward(strForwardKey);
	}
	
//Added For CR_104 - Preserve User Markers by ER91220
	
	/***************************************************************************
	 * This method is Used for turn ON/OFF Preserve user Markers
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

	public ActionForward turnUserMarker(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		String strForwardKey = ApplicationConstants.SUCCESS;
		LogUtil.logMessage("Enters into MainFeatureInfoAction:turnUserMarker");

		int intStatusCode;
		int intSpecStatusCode=0;
		ArrayList arlMainFeaList = new ArrayList();
		ArrayList arlModelMainFeaList = new ArrayList();
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlOrderList = new ArrayList();
		OrderVO objOrderVO = new OrderVO();
		SectionVO objSectionVO = new SectionVO();
		MainFeatureInfoVO objMainFeatureInfoVO = new MainFeatureInfoVO();
		MainFeatureInfoForm objMainFeatureInfoForm = (MainFeatureInfoForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		int intOrdeKey = 0;
		int intRevCode = 0;
		String strUserMarkerFlag = null;
		ArrayList arlRevList = new ArrayList();
		RevisionVO objRevisionVO = new RevisionVO();
		
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);

			if (objHttpServletRequest.getParameter("orderKeyId") == null) {
				intOrdeKey = objMainFeatureInfoForm.getOrderKey();
			} else {
				intOrdeKey = Integer.parseInt(objHttpServletRequest
						.getParameter("orderKeyId"));
			}
			
			objMainFeatureInfoForm.setOrderKey(intOrdeKey);
			
			if ((String) objHttpServletRequest.getParameter("flag") != null) {
				
				strUserMarkerFlag = (String) objHttpServletRequest.getParameter("flag");
			}
			
			objOrderVO.setOrderKey(intOrdeKey);
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			objOrderVO.setUserMarkerFlag(strUserMarkerFlag);
			
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			intStatusCode = objOrderBI.updateOrders(objOrderVO);
			
			if (intStatusCode == 0) {
				
				objMainFeatureInfoForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			}
			
			if (intStatusCode > 0) {
				objMainFeatureInfoForm.setMessageID("" + intStatusCode);
			}

			if (objMainFeatureInfoForm.getRevCode() == 0) {
				intRevCode = 1;//Default No revision
			} else if ((String) objHttpServletRequest.getParameter("revCode") != null) {
				intRevCode = Integer.parseInt(objHttpServletRequest
						.getParameter("revCode"));
			} else {
				intRevCode = objMainFeatureInfoForm.getRevCode();
			}			
			objRevisionVO.setUserID(objLoginVo.getUserID());

			OrderSectionBI objOrderSectionBO = ServiceFactory
					.getOrderSectionBO();

			arlRevList = objOrderSectionBO.fetchRevision(objRevisionVO);
			if (arlRevList != null) {
				objMainFeatureInfoForm.setRevisionList(arlRevList);
				objMainFeatureInfoForm.setRevCode(intRevCode);
				objMainFeatureInfoForm.setHdnRevViewCode(String
						.valueOf(objMainFeatureInfoForm.getRevCode()));
			}
			LogUtil.logMessage("Rev Code"
					+ objMainFeatureInfoForm.getHdnRevViewCode());

			objMainFeatureInfoVO.setUserID(objLoginVo.getUserID());
			objMainFeatureInfoVO.setOrderKey(objMainFeatureInfoForm
					.getOrderKey());
			objMainFeatureInfoVO
			.setDataLocationType(DatabaseConstants.DATALOCATION);

			objMainFeatureInfoVO
					.setRevCode(objMainFeatureInfoForm.getRevCode());

			MainFeatureInfoBI objMainFeatureInfoBI = ServiceFactory
			.getMainfeatureInfoBO();
			arlMainFeaList = objMainFeatureInfoBI
			.fetchMainFeatures(objMainFeatureInfoVO);
			if (arlMainFeaList != null) {
				objMainFeatureInfoForm.setMainFeatureList(arlMainFeaList);
			}
			LogUtil.logMessage("ArrayList Value in MainFeatureInfoAction:"
					+ objMainFeatureInfoForm.getMainFeatureList().size());

			objMainFeatureInfoVO.setDisplayInSpec(false);
			arlModelMainFeaList = objMainFeatureInfoBI
			.fetchModelMainFeatures(objMainFeatureInfoVO);
			if (arlModelMainFeaList != null) {
				objMainFeatureInfoForm
				.setModelMainFeatureList(arlModelMainFeaList);
			}
			
			objSectionVO.setUserID(objLoginVo.getUserID());
			
			objSectionVO.setOrderKey(objMainFeatureInfoForm.getOrderKey());
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null) {
				objMainFeatureInfoForm.setSectionList(arlSectionList);
			}
			
			LogUtil.logMessage("ArrayList Value in OrderSectionAction:"
					+ objMainFeatureInfoForm.getSectionList().size());
			
			/** ******************Ends Here ************************** */

			LogUtil.logMessage("*******OrderKey inside Fetch orders:"
					+ objMainFeatureInfoForm.getOrderKey());
			objOrderVO.setOrderKey(objMainFeatureInfoForm.getOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);

			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objMainFeatureInfoForm.setOrderList(arlOrderList);
//			Added For CR_100
			OrderVO objOrderVOSpecStatus = null;
			if (arlOrderList != null && arlOrderList.size() > 0) {
				for (int i = 0; i < arlOrderList.size(); i++) {
					
					objOrderVOSpecStatus = (OrderVO) arlOrderList.get(i);
					intSpecStatusCode = objOrderVOSpecStatus
					.getSpecStatusCode();
					LogUtil
					.logMessage("*******OrderKey@@@@@@@@@@@t size:"
							+ objMainFeatureInfoForm.getOrderList().size());
					//					Added for CR-10 display of Reason based on Spec Status Code
					objMainFeatureInfoForm.setCurrentSpecStatus(intSpecStatusCode);
								objOrderVO.setDynamicNoFlag(objOrderVOSpecStatus.getDynamicNoFlag());
					LogUtil
					.logMessage("*******OrderKey$$$$$$$$$ size:"
							+ objMainFeatureInfoForm.getOrderList().size());
				}
				
			}
			SpecStatusBI objSpecStatusBI = ServiceFactory.getSpecStatusBO();
			ArrayList arlSpecStatus = objSpecStatusBI
			.fetchSpecNextStatus(objOrderVOSpecStatus);
			objMainFeatureInfoForm.setSpecStatusList(arlSpecStatus);
			
			
			
		} catch (Exception objExp) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			LogUtil
			.logMessage(" Exception occured in MainFeatureInfoAction:turnUserMarker"
					+ objExp);
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);

		}
		return objActionMapping.findForward(strForwardKey);
	}
	
//Added For CR_106
	
	/***************************************************************************
	 * This method is Used for turnlogo  ON/OFF 
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

	public ActionForward turnLogoONOFF(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		String strForwardKey = ApplicationConstants.SUCCESS;
		LogUtil.logMessage("Enters into MainFeatureInfoAction:turnLogoONOFF");

		int intStatusCode;
		int intSpecStatusCode=0;
		ArrayList arlMainFeaList = new ArrayList();
		ArrayList arlModelMainFeaList = new ArrayList();
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlOrderList = new ArrayList();
		OrderVO objOrderVO = new OrderVO();
		SectionVO objSectionVO = new SectionVO();
		MainFeatureInfoVO objMainFeatureInfoVO = new MainFeatureInfoVO();
		MainFeatureInfoForm objMainFeatureInfoForm = (MainFeatureInfoForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		int intOrdeKey = 0;
		int intRevCode = 0;
		//String strUserMarkerFlag = null;
		String strCustLogoFlag=null;
		String strDistriLogoFlag=null;
		ArrayList arlRevList = new ArrayList();
		RevisionVO objRevisionVO = new RevisionVO();
		
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			LogUtil.logMessage(objLoginVo);

			if (objHttpServletRequest.getParameter("orderKeyId") == null) {
				intOrdeKey = objMainFeatureInfoForm.getOrderKey();
			} else {
				intOrdeKey = Integer.parseInt(objHttpServletRequest
						.getParameter("orderKeyId"));
			}
			
			objMainFeatureInfoForm.setOrderKey(intOrdeKey);
			
			if ((String) objHttpServletRequest.getParameter("distriLogoFlag") != null) {
				
				strDistriLogoFlag = (String) objHttpServletRequest.getParameter("distriLogoFlag");
			}
			
if ((String) objHttpServletRequest.getParameter("custLogoFlag") != null) {
				
	strCustLogoFlag = (String) objHttpServletRequest.getParameter("custLogoFlag");
			}

/*if ((String) objHttpServletRequest.getParameter("flag") != null) {
	
	strUserMarkerFlag = (String) objHttpServletRequest.getParameter("flag");
}*/

			
			objOrderVO.setOrderKey(intOrdeKey);
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			//objOrderVO.setUserMarkerFlag(strUserMarkerFlag);
			if(strCustLogoFlag !=null ||strCustLogoFlag !="" )
			{
			objOrderVO.setCustLogoFlag(strCustLogoFlag);
			}
			if(strDistriLogoFlag !=null ||strDistriLogoFlag !="" )
			{
				objOrderVO.setDistriLogoFlag(strDistriLogoFlag);
			}
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			
			intStatusCode = objOrderBI.updateOrders(objOrderVO);
			
			if (intStatusCode == 0) {
				
				objMainFeatureInfoForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			}
			
			if (intStatusCode > 0) {
				objMainFeatureInfoForm.setMessageID("" + intStatusCode);
			}

			if (objMainFeatureInfoForm.getRevCode() == 0) {
				intRevCode = 1;//Default No revision
			} else if ((String) objHttpServletRequest.getParameter("revCode") != null) {
				intRevCode = Integer.parseInt(objHttpServletRequest
						.getParameter("revCode"));
			} else {
				intRevCode = objMainFeatureInfoForm.getRevCode();
			}			
			objRevisionVO.setUserID(objLoginVo.getUserID());

			OrderSectionBI objOrderSectionBO = ServiceFactory
					.getOrderSectionBO();

			arlRevList = objOrderSectionBO.fetchRevision(objRevisionVO);
			if (arlRevList != null) {
				objMainFeatureInfoForm.setRevisionList(arlRevList);
				objMainFeatureInfoForm.setRevCode(intRevCode);
				objMainFeatureInfoForm.setHdnRevViewCode(String
						.valueOf(objMainFeatureInfoForm.getRevCode()));
			}
			LogUtil.logMessage("Rev Code"
					+ objMainFeatureInfoForm.getHdnRevViewCode());

			objMainFeatureInfoVO.setUserID(objLoginVo.getUserID());
			objMainFeatureInfoVO.setOrderKey(objMainFeatureInfoForm
					.getOrderKey());
			objMainFeatureInfoVO
			.setDataLocationType(DatabaseConstants.DATALOCATION);

			objMainFeatureInfoVO
					.setRevCode(objMainFeatureInfoForm.getRevCode());

			MainFeatureInfoBI objMainFeatureInfoBI = ServiceFactory
			.getMainfeatureInfoBO();
			arlMainFeaList = objMainFeatureInfoBI
			.fetchMainFeatures(objMainFeatureInfoVO);
			if (arlMainFeaList != null) {
				objMainFeatureInfoForm.setMainFeatureList(arlMainFeaList);
			}
			LogUtil.logMessage("ArrayList Value in MainFeatureInfoAction:"
					+ objMainFeatureInfoForm.getMainFeatureList().size());

			objMainFeatureInfoVO.setDisplayInSpec(false);
			arlModelMainFeaList = objMainFeatureInfoBI
			.fetchModelMainFeatures(objMainFeatureInfoVO);
			if (arlModelMainFeaList != null) {
				objMainFeatureInfoForm
				.setModelMainFeatureList(arlModelMainFeaList);
			}
			
			objSectionVO.setUserID(objLoginVo.getUserID());
			
			objSectionVO.setOrderKey(objMainFeatureInfoForm.getOrderKey());
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			
			/**
			 * ****** This part of code is used to Display the sections in
			 * orderlevel with color indication flag*****
			 */
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null) {
				objMainFeatureInfoForm.setSectionList(arlSectionList);
			}
			
			LogUtil.logMessage("ArrayList Value in OrderSectionAction:"
					+ objMainFeatureInfoForm.getSectionList().size());
			
			/** ******************Ends Here ************************** */

			LogUtil.logMessage("*******OrderKey inside Fetch orders:"
					+ objMainFeatureInfoForm.getOrderKey());
			objOrderVO.setOrderKey(objMainFeatureInfoForm.getOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);

			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objMainFeatureInfoForm.setOrderList(arlOrderList);
//			Added For CR_100
			OrderVO objOrderVOSpecStatus = null;
			if (arlOrderList != null && arlOrderList.size() > 0) {
				for (int i = 0; i < arlOrderList.size(); i++) {
					
					objOrderVOSpecStatus = (OrderVO) arlOrderList.get(i);
					intSpecStatusCode = objOrderVOSpecStatus
					.getSpecStatusCode();
					LogUtil
					.logMessage("*******OrderKey@@@@@@@@@@@t size:"
							+ objMainFeatureInfoForm.getOrderList().size());
					//					Added for CR-10 display of Reason based on Spec Status Code
					objMainFeatureInfoForm.setCurrentSpecStatus(intSpecStatusCode);
								objOrderVO.setDynamicNoFlag(objOrderVOSpecStatus.getDynamicNoFlag());
					LogUtil
					.logMessage("*******OrderKey$$$$$$$$$ size:"
							+ objMainFeatureInfoForm.getOrderList().size());
				}
				
			}
			SpecStatusBI objSpecStatusBI = ServiceFactory.getSpecStatusBO();
			ArrayList arlSpecStatus = objSpecStatusBI
			.fetchSpecNextStatus(objOrderVOSpecStatus);
			objMainFeatureInfoForm.setSpecStatusList(arlSpecStatus);
			
			
			
		} catch (Exception objExp) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			LogUtil
			.logMessage(" Exception occured in MainFeatureInfoAction:turnUserMarker"
					+ objExp);
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);

		}
		return objActionMapping.findForward(strForwardKey);
	}
	
	
	//Added For CR_113 for QA Fix
	
	/***************************************************************************
	 * This method is Used for turn ON/OFF 
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
	
	public ActionForward turnDynamicNumOnOff(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		String strForwardKey = ApplicationConstants.SUCCESS;
		LogUtil.logMessage("Enters into MainFeatureInfoAction:turnDynamicNo");

		ArrayList arlSectionList = new ArrayList();
		ArrayList arlSectionDetails = new ArrayList();
		ArrayList arlOrderList = new ArrayList();
		ArrayList arlRevList = new ArrayList();
		SectionVO objSectionVO = new SectionVO();
		OrderVO objOrderVO = new OrderVO();
		MainFeatureInfoForm objMainFeatureInfoForm = (MainFeatureInfoForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		
		int intOrdeKey = 0;
		int intStatusCode = 0;
		int intRevCode = 0;
		int intSecSeqNo = 0;
		//String strPDFHeaderFlag = null;
		String strDynamicNoFlag = null;
		String strSecCode = null;
		String strSecName = null;
		
		try {
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			intOrdeKey = objMainFeatureInfoForm.getOrderKey();
			intRevCode = objMainFeatureInfoForm.getRevCode();
					
		if ((String) objHttpServletRequest.getParameter("flag") != null) {
				
				strDynamicNoFlag = (String) objHttpServletRequest.getParameter("flag");
			}
			
			objOrderVO.setOrderKey(intOrdeKey);
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			//objOrderVO.setPdfHeaderFlag(strPDFHeaderFlag);
			objOrderVO.setDynamicNoFlag(strDynamicNoFlag);
			
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			intStatusCode = objOrderBI.turnDynamicNumOnOff(objOrderVO);
			
			if (intStatusCode == 0) {
				objMainFeatureInfoForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objMainFeatureInfoForm.setMessageID("" + intStatusCode);
			}

			intSecSeqNo = objMainFeatureInfoForm.getOrderSecSeqNo();
			strSecCode = objMainFeatureInfoForm.getOrderSecCode();
			strSecName = objMainFeatureInfoForm.getOrderSecName();
			objMainFeatureInfoForm.setOrderKey(intOrdeKey);
			objMainFeatureInfoForm.setOrderSecCode(strSecCode);
			objMainFeatureInfoForm.setOrderSecName(strSecName);
			objMainFeatureInfoForm.setOrderSecSeqNo(intSecSeqNo);
			objMainFeatureInfoForm.setRevCode(intRevCode);
			
			objSectionVO.setUserID(objLoginVo.getUserID());
			objSectionVO.setSectionSeqNo(intSecSeqNo);
			objSectionVO.setOrderKey(intOrdeKey);
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSectionVO.setRevisionInput(intRevCode);//default set as No revision
			
			OrderSectionBI objOrderSectionBI = ServiceFactory
			.getOrderSectionBO();
			arlSectionList = objOrderSectionBI.fetchOrdSections(objSectionVO);
			
			if (arlSectionList != null) {
				objMainFeatureInfoForm.setSectionList(arlSectionList);
			}
			
			/**
			 * populating the Revision drop down code starts here.
			 */
			
			RevisionVO objRevisionVO = new RevisionVO();
			objRevisionVO.setUserID(objLoginVo.getUserID());
			
			arlRevList = objOrderSectionBI.fetchRevision(objRevisionVO);
			
			if (arlRevList != null) {
				objMainFeatureInfoForm.setRevisionList(arlRevList);
			}
			/**
			 * Ends Here
			 */
			//Added for CR_109 to bring New Subsections to Order
			objSectionVO.setNewSubSecFlag(ApplicationConstants.YES);
			//Added for CR_109 to bring New Subsections to Order

			arlSectionDetails = objOrderSectionBI
			.fetchSectionDetails(objSectionVO);
			objMainFeatureInfoForm.setSecDetail(arlSectionDetails);
			objOrderVO.setOrderKey(objMainFeatureInfoForm.getOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			
			//Displaying Order Info
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			objMainFeatureInfoForm.setOrderList(arlOrderList);
			
			//This is for getting the Spec Status Code to be passed to get the
			// next status
			int intSpecStatusCode = 0;
			OrderVO objOrderVOSpecStatus = null;
			if (arlOrderList != null && arlOrderList.size() > 0) {
				for (int i = 0; i < arlOrderList.size(); i++) {
					
					objOrderVOSpecStatus = (OrderVO) arlOrderList.get(i);
					intSpecStatusCode = objOrderVOSpecStatus
					.getSpecStatusCode();
				}
				
			}
			objOrderVOSpecStatus.setUserID(objLoginVo.getUserID());
			SpecStatusBI objSpecStatusBI = ServiceFactory.getSpecStatusBO();
			ArrayList arlSpecStatus = objSpecStatusBI
			.fetchSpecNextStatus(objOrderVOSpecStatus);
			objMainFeatureInfoForm.setSpecStatusList(arlSpecStatus);
			
		} catch (Exception objExp) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			LogUtil
			.logMessage(" Exception occured in MainFeatureInfoAction:turnPDFHeaderImage"
					+ objExp);
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);

		}
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***************************************************************************
	 * This method is for populating the deleted clauses history
	 * Added for LSDB_CR_92 by rj85495
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward deletedClausesHistoy(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into MainFeatureInfoAction:deletedClausesHistoy");
		
		String strForwardKey = ApplicationConstants.SUCCESS;
		MainFeatureInfoForm objMainFeatureInfoForm = (MainFeatureInfoForm) objActionForm;
		
		ActionForward actionRedirect = null;
		int intOrdeKey = 0;
		int intSecSeqNo=0;
		String strRedirectGenInfoPageFlag= null;
		
		try {
			
			if ((String) objHttpServletRequest.getParameter("orderKey") != null){
				intOrdeKey = Integer.parseInt(objHttpServletRequest.getParameter("orderKey"));
			}
			else {
				intOrdeKey = objMainFeatureInfoForm.getOrderKey();
			}
			if ((String) objHttpServletRequest.getParameter("secSeqNo") != null) {
				intSecSeqNo = Integer.parseInt(objHttpServletRequest.getParameter("secSeqNo").toString());
			}
			LogUtil.logMessage("intOrdeKey::::" + intOrdeKey);
			LogUtil.logMessage("Sec Seq No::::" + intSecSeqNo);
			strRedirectGenInfoPageFlag="Y";
			actionRedirect = new ActionForward("OrderSection.do?method=deletedClausesHistoy"+"&orderKey="+intOrdeKey+"&secSeqNo="+intSecSeqNo+"&RedirectGenInfoPageFlag="+strRedirectGenInfoPageFlag, true /* REDIRECT */);
		} 
		catch (Exception objEx)
		{
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return actionRedirect;
	}
	
	/***************************************************************************
	 * This method is for populating the which are the Clauses with Indicators
	 * Added for CR_113 for view Clauses with Indicators
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward clausesWithIndicators(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into MainFeatureInfoAction:ClauseswithIndicators");
		String strForwardKey = ApplicationConstants.SUCCESS;
		MainFeatureInfoForm objMainFeatureInfoForm = (MainFeatureInfoForm) objActionForm;
		
		int intOrdeKey = 0;
		int intSecSeqNo=0;
		ActionForward actionRedirect = null;
		String strRedirectGenInfoPageFlag= null;
		
		try {
			
			if ((String) objHttpServletRequest.getParameter("orderKey") != null){
				intOrdeKey = Integer.parseInt(objHttpServletRequest.getParameter("orderKey"));
			}
			else {
				intOrdeKey = objMainFeatureInfoForm.getOrderKey();
			}
			if ((String) objHttpServletRequest.getParameter("secSeqNo") != null) {
				intSecSeqNo = Integer.parseInt(objHttpServletRequest.getParameter("secSeqNo").toString());
			}
			LogUtil.logMessage("intOrdeKey::::" + intOrdeKey);
			LogUtil.logMessage("Sec Seq No::::" + intSecSeqNo);
			strRedirectGenInfoPageFlag="Y";
			actionRedirect = new ActionForward("OrderSection.do?method=clausesWithIndicators"+"&orderKey="+intOrdeKey+"&secSeqNo="+intSecSeqNo+"&RedirectGenInfoPageFlag="+strRedirectGenInfoPageFlag, true /* REDIRECT */);
		} 
		catch (Exception objEx)
		{
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return actionRedirect;
	}
	//	Added For CR_113 QA fix- Ends here
	
}