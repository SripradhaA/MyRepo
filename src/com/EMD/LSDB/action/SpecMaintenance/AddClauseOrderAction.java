package com.EMD.LSDB.action.SpecMaintenance;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.EMD.LSDB.action.common.EMDAction;
import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.ModelCompBI;
import com.EMD.LSDB.bo.serviceinterface.ModelCompGroupBI;
import com.EMD.LSDB.bo.serviceinterface.ModelCompMapBI;
import com.EMD.LSDB.bo.serviceinterface.ModelSelectClauseRevBI;
import com.EMD.LSDB.bo.serviceinterface.OrderBI;
import com.EMD.LSDB.bo.serviceinterface.OrderClauseBI;
import com.EMD.LSDB.bo.serviceinterface.OrderSectionBI;
import com.EMD.LSDB.bo.serviceinterface.StandardEquipBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.constant.DatabaseConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.form.SpecMaintenance.AddClauseOrderForm;
import com.EMD.LSDB.vo.common.ClauseTableDataVO;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.CompGroupVO;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.StandardEquipVO;
import com.EMD.LSDB.vo.common.SubSectionVO;

public class AddClauseOrderAction extends EMDAction {

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
		ArrayList arlStandardEquipList;

		LogUtil.logMessage("Inside AddClauseOrderAction:initLoad");
		String strForwardKey = ApplicationConstants.SUCCESS;
		AddClauseOrderForm objAddClauseOrderForm = (AddClauseOrderForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
				.getAttribute(ApplicationConstants.USER_IN_SESSION);
		try {
			String strSecCode = "";
			String strSecName = "";
			String strSubSecCode = "";
			String strSubSecName = "";

			SectionVO objSectionVO = new SectionVO();

			OrderVO objOrderVO = new OrderVO();
			StandardEquipVO objStandardEquipVO = new StandardEquipVO();
			StandardEquipBI objStandardEquipBO = ServiceFactory
					.getStandardEquipBO();
			SubSectionVO objSubSectionVO = new SubSectionVO();
			/**
			 * Added for CR-68 Order New Component To fetch Model Components &
			 * Order Components*
			 */
			SubSectionVO obSubSectionVO = new SubSectionVO();
			SectionVO objSection = new SectionVO();
			Connection objConnection = null;
			//Unused variable commented
			//CompGroupVO objCompGrpVO = new CompGroupVO();

			ArrayList arlOrderList = new ArrayList();
			arlStandardEquipList = new ArrayList();
			ArrayList arlSectionList = new ArrayList();
			ArrayList arlSubSecDetails = new ArrayList();
			//Unused variable commented
			//ArrayList arlCompGroupList = null;
			objStandardEquipVO.setUserID(objLoginVo.getUserID());
			arlStandardEquipList = objStandardEquipBO
					.fetchStandardEquipment(objStandardEquipVO);

			if (arlStandardEquipList != null && arlStandardEquipList.size() > 0) {
				objAddClauseOrderForm
						.setStandardEquipmentList(arlStandardEquipList);
			}

			if (objHttpServletRequest.getParameter("secSeq") != null) {
				objAddClauseOrderForm.setHdnsecSeq(Integer
						.parseInt(objHttpServletRequest.getParameter("secSeq")
								.toString()));
			}

			if (objHttpServletRequest.getParameter("revCode") != null) {
				objAddClauseOrderForm.setHdnRevCode(objHttpServletRequest
						.getParameter("revCode").toString());
			}

			if (objHttpServletRequest.getParameter("orderkey") != null) {
				objAddClauseOrderForm.setHdnOrderKey(Integer
						.parseInt(objHttpServletRequest
								.getParameter("orderkey").toString()));
				LogUtil.logMessage("orderKey:"
						+ Integer.parseInt(objHttpServletRequest.getParameter(
								"orderkey").toString()));
			}

			if (objHttpServletRequest.getParameter("subsecseqno") != null) {
				LogUtil.logMessage("SubSectionSeqNo:"
						+ objHttpServletRequest.getParameter("subsecseqno")
								.toString());
				objAddClauseOrderForm.setHdnSubSecSeqNo(Integer
						.parseInt(objHttpServletRequest
								.getParameter("subsecseqno")));
			}

			/**
			 * ****** This part of code is used to get the order number and
			 * ModelSeqNo*****
			 */
			LogUtil.logMessage("*******OrderKey inside Fetch orders:"
					+ objAddClauseOrderForm.getOrderKey());
			objOrderVO.setOrderKey(objAddClauseOrderForm.getOrderKey());
			objOrderVO.setUserID(objLoginVo.getUserID());
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			LogUtil.logMessage("Size of ArrayList:" + arlOrderList.size());

			if (arlOrderList.size() != 0) {
				for (int counter = 0; counter < arlOrderList.size(); counter++) {
					objOrderVO = (OrderVO) arlOrderList.get(counter);
					objAddClauseOrderForm.setOrderNo(objOrderVO.getOrderNo());
					objAddClauseOrderForm
							.setHdnOrderNo(objOrderVO.getOrderNo());
					objAddClauseOrderForm.setHdnModelSeqNo(objOrderVO
							.getModelSeqNo());
					objAddClauseOrderForm.setSpecStatusCode(objOrderVO
							.getSpecStatusCode());
					//Added for CR_97 
					objAddClauseOrderForm.setChangeControlFlag(objOrderVO.getChangeControlFlag());
					// Ends here
					LogUtil.logMessage("SpecStatusCode in Action:"
							+ objAddClauseOrderForm.getSpecStatusCode());
					LogUtil.logMessage("ModelSeqNO in Action:"
							+ objOrderVO.getModelSeqNo());
					LogUtil.logMessage("OrderNO in Action:"
							+ objOrderVO.getOrderNo());

				}
			}

			/**
			 * Added for CR-68 Order New Component To fetch Model Components &
			 * Order Components*
			 */
			objSection.setOrderKey(objAddClauseOrderForm.getHdnOrderKey());
			objSection.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSection.setUserID(objLoginVo.getUserID());

			objSubSectionVO.setSubSecSeqNo(objAddClauseOrderForm
					.getHdnSubSecSeqNo());

			OrderSectionBI obOrderSectionBO = ServiceFactory
					.getOrderSectionBO();
			obSubSectionVO = obOrderSectionBO.fetchComponents(objSubSectionVO,
					objSection, objConnection, "N");

			if (obSubSectionVO != null) {

				objAddClauseOrderForm.setCompGroupList(obSubSectionVO
						.getCompGroup());

			}

			// Ends here

			/** ******************Ends Here ************************** */

			/**
			 * ****** This part of code is used to Display the Subsection name
			 * and code*****
			 */

			objSubSectionVO.setUserID(objLoginVo.getUserID());
			objSubSectionVO.setSubSecSeqNo(objAddClauseOrderForm
					.getHdnSubSecSeqNo());
			objSubSectionVO.setOrderKey(objAddClauseOrderForm.getOrderKey());
			objSubSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			OrderSectionBI objOrderSectionBO = ServiceFactory
					.getOrderSectionBO();
			arlSubSecDetails = objOrderSectionBO
					.fetchSubSectionDetails(objSubSectionVO);
			if (arlSubSecDetails.size() != 0) {

				for (int counter = 0; counter < arlSubSecDetails.size(); counter++) {
					objSubSectionVO = (SubSectionVO) arlSubSecDetails
							.get(counter);
					strSubSecCode = objSubSectionVO.getSubSecCode();
					strSubSecName = objSubSectionVO.getSubSecName();
					objAddClauseOrderForm.setSubSection(strSubSecCode.concat(
							".").concat(" ").concat(strSubSecName));
					objAddClauseOrderForm.setHdnSubsection(strSubSecCode
							.concat(".").concat(" ").concat(strSubSecName));
					LogUtil.logMessage("SubSecCode in Action:"
							+ objSubSectionVO.getSubSecCode());
					LogUtil.logMessage("SubSecName in Action:"
							+ objSubSectionVO.getSubSecName());
					LogUtil.logMessage("SubSection Name in Action:"
							+ objAddClauseOrderForm.getSubSection());
				}
			}

			/**
			 * ****** This part of code is used to Display the section name and
			 * code *****
			 */

			objSectionVO.setUserID(objLoginVo.getUserID());
			objSectionVO.setSectionSeqNo(objAddClauseOrderForm.getHdnsecSeq());
			objSectionVO.setOrderKey(objAddClauseOrderForm.getOrderKey());
			objSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objOrderSectionBO = ServiceFactory.getOrderSectionBO();
			arlSectionList = objOrderSectionBO
					.fetchSectionDetails(objSectionVO);

			if (arlSectionList.size() != 0) {

				for (int counter = 0; counter < arlSectionList.size(); counter++) {
					objSubSectionVO = (SubSectionVO) arlSectionList
							.get(counter);
					strSecCode = objSubSectionVO.getSecCode();
					strSecName = objSubSectionVO.getSecName();
					objAddClauseOrderForm.setSection(strSecCode.concat(".")
							.concat(" ").concat(strSecName));
					objAddClauseOrderForm.setHdnSection(strSecCode.concat(".")
							.concat(" ").concat(strSecName));
					LogUtil.logMessage("SecCode in Action:"
							+ objSubSectionVO.getSecCode());
					LogUtil.logMessage("SecName in Action:"
							+ objSubSectionVO.getSecName());
					LogUtil.logMessage("Section Name in Action:"
							+ objAddClauseOrderForm.getSection());
				}
			}

			/**
			 * ****** This part of Code is used to get User Role and Spec Type
			 * for the Order- CR 51*********
			 */
			objAddClauseOrderForm.setRoleID(objLoginVo.getRoleID());

			int OrderSpecType = objOrderBI.fetchOrderSpecType(objOrderVO);

			if (OrderSpecType > 0) {

				objAddClauseOrderForm.setSpecTypeSeqNo(OrderSpecType);

			}

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
	 * This method is for loading details of Components.
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException,BusinessException
	 **************************************************************************/
	/*
	 * public ActionForward addComponent(ActionMapping objActionMapping,
	 * ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
	 * HttpServletResponse objHttpServletResponse) throws BusinessException,
	 * ApplicationException { LogUtil.logMessage( "Inside the
	 * AddClauseOrderAction : Add Component" ); return
	 * objActionMapping.findForward( "addOrderComponent" ); }
	 */

	/***************************************************************************
	 * This method is for Inserting Clause details in the database.
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
	public ActionForward insertClause(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
			throws BusinessException {

		LogUtil.logMessage("Inside AddClauseOrderAction :InsertClause ");

		String strForwardKey = ApplicationConstants.SUCCESS;
		AddClauseOrderForm objAddClauseOrderForm = (AddClauseOrderForm) objActionForm;
		//Unused variable commented
		//ArrayList arlCompGroupList = new ArrayList();
		int intOrderKey = 0;
		
		int intClauseSeqNo = 0;
		int intsecSeq = 0;
		int intrevCode = 0;
		int intsubSecSeqNo = 0;

		/* Added as per CR 73 to return to Modify Spec Screen by CM68219 starts */
		ActionForward actionRedirect = null;
		String strRedirectFlag = null;
		/* Added as per CR 73 to return to Modify Spec Screen by CM68219 ends */
		try {

			HttpSession objSession = objHttpServletRequest.getSession(false);

			OrderClauseBI objOrderClauseBO = ServiceFactory.getOrderClauseBO();
			ClauseVO objClauseVO = new ClauseVO();

			String strUserID = null;
			String strClauseDesc = null;
			String strComments = null;
			String strReason = null;

			/**
			 * Added for CR-68 Order New Component To fetch Model Components &
			 * Order Components*
			 */
			SubSectionVO obSubSectionVO = new SubSectionVO();
			SectionVO objSection = new SectionVO();
			Connection objConnection = null;
			//Unused variable commented
			//CompGroupVO objCompGrpVO = new CompGroupVO();
			SubSectionVO objSubSectionVO = new SubSectionVO();

			int intSuccess = 0;

			String[] clauseTableArray1, clauseTableArray2, clauseTableArray3, clauseTableArray4, clauseTableArray5 = null;

			ClauseTableDataVO objTableDataVO = null;
			ArrayList arlTableList = new ArrayList();

			StandardEquipVO objStandardEquipVO = new StandardEquipVO();
			StandardEquipBI objStandardEquipBO = ServiceFactory
					.getStandardEquipBO();
			ArrayList arlStandardEquipList = new ArrayList();

			ArrayList arlSelCompSeqNo = new ArrayList();
			StringTokenizer stCompSeqNo = null;
			// Added for CR-74 VV49326 04-06-09
			String[] stCompName = null;
			ArrayList arlCompName = new ArrayList();
			ArrayList arlTabData = new ArrayList();
			ArrayList arlClauseVO = new ArrayList();

			if (objHttpServletRequest.getParameter("subsecno") != null) {

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
			// To Get Component Seq No list From Form
			if ((objAddClauseOrderForm.getComponentSeqNo() != null)) {

				stCompSeqNo = new StringTokenizer(objAddClauseOrderForm
						.getComponentSeqNo(), "~");

				while (stCompSeqNo.hasMoreTokens()) {
					arlSelCompSeqNo.add(stCompSeqNo.nextToken());
				}

				// Added for CR-74 VV49326 04-06-09
				/**
				 * To retain Components Tied To Clause after Serverside
				 * validation-Starts Here*
				 */
				if (objAddClauseOrderForm.getComponentSeqNo() != null
						&& !(objAddClauseOrderForm.getComponentSeqNo()
								.equals(""))) {
					stCompName = objAddClauseOrderForm.getHdnComponentName()
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
			objAddClauseOrderForm.setComponentVO(arlCompName);
			/**
			 * To retain Components Tied To Clause after Serverside
			 * validation-Ends Here*
			 */

			LogUtil.logMessage("arlSelCompSeqNo size :"
					+ arlSelCompSeqNo.size());

			/* Added for Attach Clause CR-Begin */

			/* Changed for CR-68 Order New Component */
			if (objAddClauseOrderForm.getLeadComponentSeqNo() > 0) {

				arlSelCompSeqNo.add(String.valueOf(objAddClauseOrderForm
						.getLeadComponentSeqNo()));

			}

			if (objAddClauseOrderForm.getCompGroupSeqNo() != -1) {
				objClauseVO.setLeadCompGrpSeqNo(objAddClauseOrderForm
						.getCompGroupSeqNo());
			}

			/** Added for CR-68 Order New Component* */
			objClauseVO.setOrderCompName(objAddClauseOrderForm
					.getValidCompName());

			/* Added for Attach Clause CR-End */

			// To Get Table Data1 Values From Form
			clauseTableArray1 = objAddClauseOrderForm
					.getClauseTableDataArray1();

			if (clauseTableArray1 == null || clauseTableArray1.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData1(null);
			} else {

				for (int counter = 0; counter < clauseTableArray1.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData1(objAddClauseOrderForm
							.getClauseTableDataArray1());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}

			}
			arlTableList.add(objTableDataVO);

			// To Get Table Data2 Values From Form
			clauseTableArray2 = objAddClauseOrderForm
					.getClauseTableDataArray2();

			if (clauseTableArray2 == null || clauseTableArray2.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData2(null);
			} else {
				for (int counter = 0; counter < clauseTableArray2.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData2(objAddClauseOrderForm
							.getClauseTableDataArray2());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}

			}
			arlTableList.add(objTableDataVO);

			// To Get Table Data3 Values From Form
			clauseTableArray3 = objAddClauseOrderForm
					.getClauseTableDataArray3();

			if (clauseTableArray3 == null || clauseTableArray3.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData3(null);
			} else {
				for (int counter = 0; counter < clauseTableArray3.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData3(objAddClauseOrderForm
							.getClauseTableDataArray3());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}

			}
			arlTableList.add(objTableDataVO);

			// To Get Table Data4 Values From Form
			clauseTableArray4 = objAddClauseOrderForm
					.getClauseTableDataArray4();

			if (clauseTableArray4 == null || clauseTableArray4.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData4(null);
			} else {
				for (int counter = 0; counter < clauseTableArray4.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData4(objAddClauseOrderForm
							.getClauseTableDataArray4());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}

			}
			arlTableList.add(objTableDataVO);

			// To Get Table Data5 Values From Form
			clauseTableArray5 = objAddClauseOrderForm
					.getClauseTableDataArray5();

			if (clauseTableArray5 == null || clauseTableArray5.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData5(null);
			} else {
				for (int counter = 0; counter < clauseTableArray5.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData5(objAddClauseOrderForm
							.getClauseTableDataArray5());
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
			/**
			 * Added for CR-74 To arrange Table Data from coloumn wise to row
			 * wise - Ends Here*
			 */

			LoginVO objLoginVo = (LoginVO) objSession
					.getAttribute(ApplicationConstants.USER_IN_SESSION);

			// Getting User from the session
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}

			strClauseDesc = objAddClauseOrderForm.getNewClauseDesc();
			strComments = objAddClauseOrderForm.getComments();
			strReason = objAddClauseOrderForm.getReason();

			if (strClauseDesc != null && !"".equals(strClauseDesc)) {
				strClauseDesc = ApplicationUtil.trim(strClauseDesc);
			}
			if (strComments != null && !"".equals(strComments)) {
				strComments = ApplicationUtil.trim(strComments);
			}
			if (strReason != null && !"".equals(strReason)) {

				strReason = ApplicationUtil.trim(strReason);
			}

			if (objAddClauseOrderForm.getStandardEquipmentSeqNo() != -1) {
				objStandardEquipVO
						.setStandardEquipmentSeqNo(objAddClauseOrderForm
								.getStandardEquipmentSeqNo());
				arlStandardEquipList.add(objStandardEquipVO);
				objClauseVO.setObjStandardEquipVO(arlStandardEquipList);
			} else {
				objClauseVO.setObjStandardEquipVO(null);
			}
			objAddClauseOrderForm.setHdnOrderKey(intOrderKey);

			objClauseVO.setOrderKey(objAddClauseOrderForm.getHdnOrderKey());
			objClauseVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objClauseVO.setModelSeqNo(objAddClauseOrderForm.getHdnModelSeqNo());
			objClauseVO.setSubSectionSeqNo(objAddClauseOrderForm
					.getHdnSubSecSeqNo());
			objClauseVO.setCustomerSeqNo(objAddClauseOrderForm
					.getHdnCustSeqNo());
			objClauseVO.setClauseSeqNo(0);
			objClauseVO.setParentClauseSeqNo(0);
			objClauseVO.setClauseDesc(strClauseDesc);
			objClauseVO.setComponentVO(arlSelCompSeqNo);
			objClauseVO.setEdlNo(objAddClauseOrderForm.getNewEdlNo());
			objClauseVO.setRefEDLNo(objAddClauseOrderForm.getRefEdlNo());

			for (int i = 0; i < objAddClauseOrderForm.getPartOf().length; i++) {
				LogUtil.logMessage("part of value--->"
						+ objAddClauseOrderForm.getPartOf()[i]);
			}

			for (int i = 0; i < objAddClauseOrderForm.getPartOfSeqNo().length; i++) {
				LogUtil.logMessage("part of seq	no value--->"
						+ objAddClauseOrderForm.getPartOfSeqNo()[i]);
			}

			for (int i = 0; i < objAddClauseOrderForm.getClauseSeqNum().length; i++) {
				LogUtil.logMessage("clause no value--->"
						+ objAddClauseOrderForm.getClauseSeqNum()[i]);
			}

			if (objAddClauseOrderForm.getPartOfSeqNo() != null) {
				if (objAddClauseOrderForm.getPartOfSeqNo().length != 0) {
					objClauseVO.setPartOfSeqNo(objAddClauseOrderForm
							.getPartOfSeqNo());
				}

			}

			if (objAddClauseOrderForm.getClauseSeqNum().length != 0) {
				objClauseVO.setClauseSeqNoArray(objAddClauseOrderForm
						.getClauseSeqNum());
			}

			if (objAddClauseOrderForm.getPartOf().length != 0) {
				objClauseVO.setClauseNoArray(objAddClauseOrderForm.getPartOf());
			}

			if (objAddClauseOrderForm.getDwoNo() != null
					&& !objAddClauseOrderForm.getDwoNo().equals("")) {
				String strDwoNo = ApplicationUtil.trim(objAddClauseOrderForm
						.getDwoNo());
				objClauseVO.setDwONumber(Integer.parseInt(strDwoNo));
			}

			if (objAddClauseOrderForm.getPartNo() != null
					&& !objAddClauseOrderForm.getPartNo().equals("")) {
				String strPartNo = objAddClauseOrderForm.getPartNo();
				objClauseVO.setPartNumber(Integer.parseInt(strPartNo));
			}

			if (objAddClauseOrderForm.getPriceBookNo() != null
					&& !objAddClauseOrderForm.getPriceBookNo().equals("")) {
				String strPriceNo = objAddClauseOrderForm.getPriceBookNo();
				objClauseVO.setPriceBookNumber(Integer.parseInt(strPriceNo));
			}

			objClauseVO.setComments(strComments);
			objClauseVO.setReason(strReason);

			objClauseVO.setTableDataVO(arlTableList);

			objClauseVO.setUserID(strUserID);
			// Added for CR74 vv49326 to retain Table Data after Serverside
			// validation
			objClauseVO.setTableArrayData1(arlTabData);

			/*******************************************************************
			 * Added For LSDB_CR-35 Added on 04-April-08 by ps57222
			 * 
			 ******************************************************************/

			if (objAddClauseOrderForm.getClauseSource() != null
					&& !objAddClauseOrderForm.getClauseSource().equals("")) {
				LogUtil.logMessage("ClauseSource:"
						+ objAddClauseOrderForm.getClauseSource());
				objClauseVO.setClauseSource(objAddClauseOrderForm
						.getClauseSource());
			}
			/* Added for CR_97 */
			if (objAddClauseOrderForm.getNewCRFlag() != null && !objAddClauseOrderForm.getNewCRFlag().equals("")) {
				
				LogUtil.logMessage("new CR Flag:"	+ objAddClauseOrderForm.getNewCRFlag());
				objClauseVO.setNewCRFlag(objAddClauseOrderForm	.getNewCRFlag());
				
			}
			objClauseVO.setSectionSeqNo(intsecSeq);
			/* CR_97 Ends here */
			// To insert Clause
			intSuccess = objOrderClauseBO.insertClause(objClauseVO);

			// To Fetch Standard Equipment List
			arlStandardEquipList = new ArrayList();

			objStandardEquipVO.setUserID(strUserID);
			arlStandardEquipList = objStandardEquipBO
					.fetchStandardEquipment(objStandardEquipVO);

			if (arlStandardEquipList != null && arlStandardEquipList.size() > 0) {
				objAddClauseOrderForm
						.setStandardEquipmentList(arlStandardEquipList);
			}

			/**
			 * Added for CR-68 Order New Component To fetch Model Components &
			 * Order Components*
			 */
			objSection.setOrderKey(objAddClauseOrderForm.getHdnOrderKey());
			objSection.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSection.setUserID(objLoginVo.getUserID());

			objSubSectionVO.setSubSecSeqNo(objAddClauseOrderForm
					.getHdnSubSecSeqNo());

			OrderSectionBI obOrderSectionBO = ServiceFactory
					.getOrderSectionBO();
			obSubSectionVO = obOrderSectionBO.fetchComponents(objSubSectionVO,
					objSection, objConnection, "N");

			if (obSubSectionVO != null) {

				objAddClauseOrderForm.setCompGroupList(obSubSectionVO
						.getCompGroup());

			}

			// Ends here

			// To reset Form Fields
			objAddClauseOrderForm.setOrderNo(objAddClauseOrderForm
					.getHdnOrderNo());
			objAddClauseOrderForm.setSection(objAddClauseOrderForm
					.getHdnSection());
			objAddClauseOrderForm.setSubSection(objAddClauseOrderForm
					.getHdnSubsection());
			objAddClauseOrderForm.setSpecStatusCode(objAddClauseOrderForm
					.getSpecStatusCode());
			LogUtil.logMessage("SpecStatusCode in Action:"
					+ objAddClauseOrderForm.getSpecStatusCode());
			// Changed for CR-74 VV49326 02-06-09 to retain fields after
			// serverside validadtion
			objAddClauseOrderForm.setNewClauseDesc(objAddClauseOrderForm
					.getNewClauseDesc());

			objAddClauseOrderForm.setClauseTableDataArray1(null);
			objAddClauseOrderForm.setClauseTableDataArray2(null);
			objAddClauseOrderForm.setClauseTableDataArray3(null);
			objAddClauseOrderForm.setClauseTableDataArray4(null);
			objAddClauseOrderForm.setClauseTableDataArray5(null);
			objAddClauseOrderForm.setComponent(null);
			objAddClauseOrderForm.setComponentSeqNo("");

			objAddClauseOrderForm.setRefEdlNo(null);
			objAddClauseOrderForm.setNewEdlNo(null);
			objAddClauseOrderForm.setPartOf(null);
			objAddClauseOrderForm.setPartOfSeqNo(null);
			objAddClauseOrderForm.setClauseSeqNum(null);
			objAddClauseOrderForm.setDwoNo(objAddClauseOrderForm.getDwoNo());
			objAddClauseOrderForm.setPartNo(objAddClauseOrderForm.getPartNo());
			objAddClauseOrderForm.setPriceBookNo(objAddClauseOrderForm
					.getPriceBookNo());
			objAddClauseOrderForm
					.setStandardEquipmentSeqNo(objAddClauseOrderForm
							.getStandardEquipmentSeqNo());
			objAddClauseOrderForm.setComments(objAddClauseOrderForm
					.getComments());
			objAddClauseOrderForm.setReason(objAddClauseOrderForm.getReason());
			// Added for CR-68 Order New Component
			objAddClauseOrderForm.setLeadComponentSeqNo(objAddClauseOrderForm
					.getLeadComponentSeqNo());
			LogUtil.logMessage("Lead Component Seq No"
					+ objAddClauseOrderForm.getLeadComponentSeqNo());
			objAddClauseOrderForm.setValidCompName(objAddClauseOrderForm
					.getValidCompName());

			if (intSuccess == 0) {
				strRedirectFlag = "Y";
				actionRedirect = new ActionForward(
						"OrderSection.do?method=fetchSectionDetails&orderKey="
								+ intOrderKey + "&secSeq=" + intsecSeq
								+ "&revCode=" + intrevCode + "&subsecno="
								+ intsubSecSeqNo, true /* REDIRECT */);
			} else {
				//Added for CR_97
				if("Y".equals(objAddClauseOrderForm.getNewCRFlag()))
				{
					strRedirectFlag = "Y";
					actionRedirect = new ActionForward(
							"OrderSection.do?method=fetchSectionDetails&orderKey="	+ intOrderKey + "&secSeq=" + intsecSeq
									+ "&revCode=" + intrevCode + "&subsecno="	+ intsubSecSeqNo+"&newReqID="+intSuccess, true /* REDIRECT */);
				}else{//Addition & modification for CR_97 Ends here
				// Changed for CR-74 VV49326 04-06-09
				//Added for CR_109 to load the search icon for loading all other components
				ComponentVO objComponentVo = new ComponentVO();		
				objComponentVo.setModelSeqNo(objAddClauseOrderForm.getHdnModelSeqNo());
				objComponentVo.setSubSectionSeqNo(objAddClauseOrderForm.getHdnSubSecSeqNo());
				objComponentVo.setComponentGroupSeqNo(objAddClauseOrderForm.getCompGroupSeqNo());
				
				objComponentVo.setUserID(strUserID);
				
				ModelCompMapBI objModelCompMapBo = ServiceFactory.getModelCompMapBO();
				ArrayList arlCompMapList = objModelCompMapBo.fetchCompMap(objComponentVo);

				if (arlCompMapList != null && arlCompMapList.size() > 0) {
					ArrayList arlCompList = (ArrayList) arlCompMapList.get(0);
					LogUtil.logMessage("arlCompList.size " + arlCompList.size());
					objAddClauseOrderForm.setModelCompVO(arlCompList);
				}
				//Added for CR_109 to load the search icon for loading all other components
				strRedirectFlag = "N";
				objAddClauseOrderForm.setMessageID("" + intSuccess);
				arlClauseVO.add(objClauseVO);
				objAddClauseOrderForm.setArlClauseVO(arlClauseVO);
				}

			}
			/*
			 * Added as per CR 73 to return to Modify Spec Screen by CM68219
			 * ends
			 */
		}

		catch (Exception objExp) {
			strForwardKey = ApplicationConstants.FAILURE;
			objExp.printStackTrace();
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}

		/* Added as per CR 73 to return to Modify Spec Screen by CM68219 starts */
		if (strRedirectFlag != null && strRedirectFlag.equalsIgnoreCase("Y")) {
			LogUtil.logMessage("FORWARD" + strRedirectFlag);
			return actionRedirect;

		} else {
			LogUtil.logMessage("FORWARD" + strForwardKey);
			return objActionMapping.findForward(strForwardKey);

		}
		/* Added as per CR 73 to return to Modify Spec Screen by CM68219 ends */
	}

	/***************************************************************************
	 * Added for CR********************************** * This method is for
	 * loading components.
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
	public ActionForward loadComponent(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
			throws BusinessException {
		LogUtil.logMessage("Inside AddClauseOrderAction :loadComponent");
		String strForwardKey = ApplicationConstants.SUCCESS;
		//Unused variable commented
		//ArrayList arlCompGroupList = new ArrayList();
		ArrayList arlStandardEquipList = null;
		//Unused variable commented
		//ComponentVO objComponentVO = new ComponentVO();
		StandardEquipVO objStandardEquipVO = new StandardEquipVO();

		/**
		 * Added for CR-68 Order New Component To fetch Model Components & Order
		 * Components*
		 */
		SubSectionVO obSubSectionVO = new SubSectionVO();
		SectionVO objSection = new SectionVO();
		Connection objConnection = null;
		//Unused variable commented
		//CompGroupVO objCompGrpVO = new CompGroupVO();
		SubSectionVO objSubSectionVO = new SubSectionVO();
		try {
			AddClauseOrderForm objAddClauseOrderForm = (AddClauseOrderForm) objActionForm;
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
					.getAttribute(ApplicationConstants.USER_IN_SESSION);
			StandardEquipBI objStandardEquipBO = ServiceFactory
					.getStandardEquipBO();
			//Unused variable commented
			//ModelCompBI objModelCompBO = ServiceFactory.getModelCompBO();
			// This is for retaining the Order No, Section and SubSection
			objAddClauseOrderForm.setOrderNo(objAddClauseOrderForm
					.getHdnOrderNo());
			objAddClauseOrderForm.setSection(objAddClauseOrderForm
					.getHdnSection());
			objAddClauseOrderForm.setSubSection(objAddClauseOrderForm
					.getHdnSubsection());

			/**
			 * Added for CR-68 Order New Component To fetch Model Components &
			 * Order Components*
			 */
			//Added for CR_97 to get the value of flag again from the form.
			objAddClauseOrderForm.setChangeControlFlag(objAddClauseOrderForm.getChangeControlFlag());
			// Ends here
					
			objSection.setOrderKey(objAddClauseOrderForm.getHdnOrderKey());
			objSection.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSection.setUserID(objLoginVo.getUserID());

			objSubSectionVO.setSubSecSeqNo(objAddClauseOrderForm
					.getHdnSubSecSeqNo());

			OrderSectionBI obOrderSectionBO = ServiceFactory
					.getOrderSectionBO();
			obSubSectionVO = obOrderSectionBO.fetchComponents(objSubSectionVO,
					objSection, objConnection, "N");

			if (obSubSectionVO != null) {
				
				objAddClauseOrderForm.setCompGroupList(obSubSectionVO
						.getCompGroup());
				objAddClauseOrderForm.setLeadComponentSeqNo(-1);
				// Added for Cr-74
				objAddClauseOrderForm.setNewClauseDesc("");
				objAddClauseOrderForm.setDwoNo("");
				objAddClauseOrderForm.setPartNo("");
				objAddClauseOrderForm.setPriceBookNo("");
				objAddClauseOrderForm.setComments("");
			}
			
			//Added For CR_109
			ComponentVO objComponentVo = new ComponentVO();		
			objComponentVo.setModelSeqNo(objAddClauseOrderForm.getHdnModelSeqNo());
			objComponentVo.setSubSectionSeqNo(objAddClauseOrderForm.getHdnSubSecSeqNo());
			objComponentVo.setComponentGroupSeqNo(objAddClauseOrderForm.getCompGroupSeqNo());
			objComponentVo.setUserID(objLoginVo.getUserID());
			
			ModelCompMapBI objModelCompMapBo = ServiceFactory.getModelCompMapBO();
			ArrayList arlCompMapList = objModelCompMapBo.fetchCompMap(objComponentVo);

			if (arlCompMapList != null && arlCompMapList.size() > 0) {
				ArrayList arlCompList = (ArrayList) arlCompMapList.get(0);
				LogUtil.logMessage("arlCompList.size " + arlCompList.size());
				objAddClauseOrderForm.setModelCompVO(arlCompList);
			}
			//Added For CR_109 - Ends here
			
			objStandardEquipVO.setUserID(objLoginVo.getUserID());
			arlStandardEquipList = objStandardEquipBO
					.fetchStandardEquipment(objStandardEquipVO);
			if (arlStandardEquipList != null && arlStandardEquipList.size() > 0) {
				objAddClauseOrderForm
						.setStandardEquipmentList(arlStandardEquipList);
				// Added for Cr-74
				objAddClauseOrderForm.setStandardEquipmentSeqNo(-1);
			}
		} catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForwardKey);
	}// Ends here

	/***************************************************************************
	 * Added for CR_68 Add Order New Component
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
	public ActionForward initLoadOrderComp(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
			throws BusinessException {
		LogUtil.logMessage("Inside AddClauseOrderAction :initLoadOrderComp");
		String strForwardKey = ApplicationConstants.ORDER_NEW_COMP;

		int intModelSeqNo = 0;
		int intSubSecSeqNo = 0;
		int intCompGrpSeqNo = 0;
		String strOrderNo = null;

		try {
			AddClauseOrderForm objAddClauseOrderForm = (AddClauseOrderForm) objActionForm;
			HttpSession objSession = objHttpServletRequest.getSession(false);
			LoginVO objLoginVo = (LoginVO) objSession
					.getAttribute(ApplicationConstants.USER_IN_SESSION);

			if (objHttpServletRequest.getParameter("compGrpSeqNo") != null) {

				intCompGrpSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("compGrpSeqNo"));

			}

			if (objHttpServletRequest.getParameter("modelSeqNo") != null) {

				intModelSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("modelSeqNo"));

			}

			if (objHttpServletRequest.getParameter("subSecSeqNo") != null) {

				intSubSecSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("subSecSeqNo"));

			}

			if (objHttpServletRequest.getParameter("orderNo") != null) {

				strOrderNo = objHttpServletRequest.getParameter("orderNo");

			}

			if (strOrderNo != null) {
				objAddClauseOrderForm.setHdnOrderNumber(strOrderNo);
			}
			objAddClauseOrderForm.setCompGroupSeqNo(intCompGrpSeqNo);
			objAddClauseOrderForm.setHdnModelSeqNo(intModelSeqNo);
			objAddClauseOrderForm.setHdnSubSecSeqNo(intSubSecSeqNo);
			/*
			 * objAddClauseOrderForm.setHdnOrderNumber(objAddClauseOrderForm
			 * .getHdnOrderNumber());
			 */

		} catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			objEx.printStackTrace();
			LogUtil.logError(objErrorInfo);
		}
		LogUtil.logMessage("strForwardKey" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
	}// Ends here

	/***************************************************************************
	 * Added for CR_68 Validate Order New Component
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
	public ActionForward validateOrderCompName(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
			throws BusinessException {
		LogUtil
				.logMessage("Inside AddClauseOrderAction :validateOrderCompName");
		String strForwardKey = ApplicationConstants.ORDER_NEW_COMP;
		String strUserID = null;
		ComponentVO objComponentVO = new ComponentVO();
		boolean blnValid = false;
		try {
			AddClauseOrderForm objAddClauseOrderForm = (AddClauseOrderForm) objActionForm;
			HttpSession objSession = objHttpServletRequest.getSession(false);

			LoginVO objLoginVo = (LoginVO) objSession
					.getAttribute(ApplicationConstants.USER_IN_SESSION);

			// Getting User from the session
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}

			objComponentVO.setModelSeqNo(objAddClauseOrderForm
					.getHdnModelSeqNo());
			objComponentVO.setSubSectionSeqNo(objAddClauseOrderForm
					.getHdnSubSecSeqNo());
			objComponentVO.setComponentGroupSeqNo(objAddClauseOrderForm
					.getCompGroupSeqNo());
			objComponentVO
					.setOrderNo(objAddClauseOrderForm.getHdnOrderNumber());

			objComponentVO.setComponentName(objAddClauseOrderForm
					.getComponentName());
			objComponentVO.setUserID(strUserID);

			OrderClauseBI objOrderClauseBO = ServiceFactory.getOrderClauseBO();
			blnValid = objOrderClauseBO.validateOrderComponent(objComponentVO);

			objAddClauseOrderForm.setComponentName(objAddClauseOrderForm
					.getComponentName());

			if (blnValid) {

				LogUtil.logMessage("Component Name is valid");
				objAddClauseOrderForm.setHdnOrdrNewComp("Y");

			} else if (!blnValid) {

				LogUtil.logMessage("Component Name is invalid");
				objAddClauseOrderForm.setHdnOrdrNewComp("N");

			}

		} catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		LogUtil.logMessage("strForwardKey" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
	}// Ends here

	/***************************************************************************
	 * Added for CR_109 to bring Clauses from Multiple Models
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
	public ActionForward loadAllComponents(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
			throws BusinessException {
		LogUtil.logMessage("Inside AddClauseOrderAction :loadAllComponents");
		String strForwardKey = ApplicationConstants.LOAD_ALL_COMP;
		String strUserID = null;
		int intModelSeqNo = 0;
		int intSubSecSeqNo = 0;
		int intCompGrpSeqNo = 0;
		int intSecSeqNo = 0;
		int intOrderKey = 0;
		try {
			AddClauseOrderForm objAddClauseOrderForm = (AddClauseOrderForm) objActionForm;
			HttpSession objSession = objHttpServletRequest.getSession(false);

			LoginVO objLoginVo = (LoginVO) objSession
					.getAttribute(ApplicationConstants.USER_IN_SESSION);

			// Getting User from the session
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}

			if (objHttpServletRequest.getParameter("orderKey") != null) {

				intOrderKey = Integer.parseInt(objHttpServletRequest
						.getParameter("orderKey"));

			}
			
			if (objHttpServletRequest.getParameter("compGrpSeqNo") != null) {

				intCompGrpSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("compGrpSeqNo"));

			}

			if (objHttpServletRequest.getParameter("modelSeqNo") != null) {

				intModelSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("modelSeqNo"));

			}

			if (objHttpServletRequest.getParameter("secSeqNo") != null) {

				intSecSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("secSeqNo"));

			}

			if (objHttpServletRequest.getParameter("subSecSeqNo") != null) {

				intSubSecSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("subSecSeqNo"));

			}

			ComponentVO objComponentVo = new ComponentVO();		
			objComponentVo.setModelSeqNo(intModelSeqNo);
			objComponentVo.setSubSectionSeqNo(intSubSecSeqNo);
			objComponentVo.setComponentGroupSeqNo(intCompGrpSeqNo);
			
			objComponentVo.setUserID(strUserID);
			
			ModelCompMapBI objModelCompMapBo = ServiceFactory.getModelCompMapBO();
			ArrayList arlCompMapList = objModelCompMapBo.fetchCompMap(objComponentVo);

			if (arlCompMapList != null && arlCompMapList.size() > 0) {
				ArrayList arlCompList = (ArrayList) arlCompMapList.get(0);
				LogUtil.logMessage("arlCompList.size " + arlCompList.size());
				objAddClauseOrderForm.setModelCompVO(arlCompList);
				objAddClauseOrderForm.setHdnModelSeqNo(intModelSeqNo);
				objAddClauseOrderForm.setHdnSubSecSeqNo(intSubSecSeqNo);
				objAddClauseOrderForm.setCompGroupSeqNo(intCompGrpSeqNo);
				objAddClauseOrderForm.setHdnsecSeq(intSecSeqNo);
				objAddClauseOrderForm.setHdnOrderKey(intOrderKey);
			}
			
		} catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}

		return objActionMapping.findForward(strForwardKey);
	}
	
	/***************************************************************************
	 * Added for CR_109 to bring Clauses from Multiple Models
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
	public ActionForward loadAllClauses(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
			throws BusinessException {
		LogUtil.logMessage("Inside AddClauseOrderAction :loadAllClauses");
		String strForwardKey = ApplicationConstants.LOAD_ALL_COMP;
		String strUserID = null;
		ArrayList arlLeadCompClaList = new ArrayList();
		try {
			AddClauseOrderForm objAddClauseOrderForm = (AddClauseOrderForm) objActionForm;
			HttpSession objSession = objHttpServletRequest.getSession(false);

			LoginVO objLoginVo = (LoginVO) objSession
					.getAttribute(ApplicationConstants.USER_IN_SESSION);

			// Getting User from the session
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			
			ComponentVO objComponentVO = new ComponentVO();
			
			objComponentVO.setUserID(objLoginVo.getUserID());
			objComponentVO.setModelSeqNo(objAddClauseOrderForm.getHdnModelSeqNo());
			objComponentVO.setSubSectionSeqNo(objAddClauseOrderForm.getHdnSubSecSeqNo());
			objComponentVO.setComponentGroupSeqNo(objAddClauseOrderForm.getCompGroupSeqNo());			

			objComponentVO.setUserID(strUserID);
			
			ModelCompMapBI objModelCompMapBo = ServiceFactory.getModelCompMapBO();
			ArrayList arlCompMapList = objModelCompMapBo.fetchCompMap(objComponentVO);			

			if (arlCompMapList != null && arlCompMapList.size() > 0) {
				ArrayList arlCompList = (ArrayList) arlCompMapList.get(0);
				objAddClauseOrderForm.setModelCompVO(arlCompList);
			}
			
			objComponentVO.setComponentSeqNo(objAddClauseOrderForm.getLeadComponentSeqNo());
						
			ModelCompBI objModelCompBO = ServiceFactory.getModelCompBO();
			arlLeadCompClaList = objModelCompBO.viewLeadComponentClausesByModel(objComponentVO);

			if (arlLeadCompClaList != null && arlLeadCompClaList.size() > 0) {
				objAddClauseOrderForm.setMethod("");
				objAddClauseOrderForm.setLeadCompClauseVO(arlLeadCompClaList);
			}
			else{
				objAddClauseOrderForm.setMethod(ApplicationConstants.LOAD_ALL_COMP);
			}				
			
		} catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}

		return objActionMapping.findForward(strForwardKey);
	}
	
	/***************************************************************************
	 * Added for CR_109 to load Clause details to Add Clause Page
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
	public ActionForward mapCompnFetchClause(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
			throws BusinessException {
		LogUtil.logMessage("Inside AddClauseOrderAction :mapCompnFetchClause");
		String strForwardKey = ApplicationConstants.SUCCESS;
		String strUserID = null;
		int intModelSeqNo = 0;
		int intSubSecSeqNo = 0;
		int intClauseSeqNo = 0;
		int intCompSeqNo = 0;
		int intCompGrpSeqNo = 0;
		int intStatusCode = 0;
		int intSecSeqNo = 0;
		int intOrderKey = 0;
		int intFromModelSeqNo = 0;
		int intFromSubSecSeqNo = 0;
		Connection objConnection = null;
		ArrayList arlClauseList = new ArrayList();
		ArrayList arlDefaultVersion = new ArrayList();
		ArrayList arlStandardEquipList = null;
		StandardEquipVO objStandardEquipVO = new StandardEquipVO();
		SubSectionVO obSubSectionVO = new SubSectionVO();
		SectionVO objSection = new SectionVO();
		SubSectionVO objSubSectionVO = new SubSectionVO();
		OrderVO objOrderVO = new OrderVO();
		ArrayList arlOrderList = new ArrayList();
		ArrayList arlSubSecDetails = new ArrayList();
		ArrayList arlSectionList = new ArrayList();
		String strSubSecCode = null;
		String strSubSecName = null;
		String strSecCode = null;
		String strSecName = null;
		try {
			AddClauseOrderForm objAddClauseOrderForm = (AddClauseOrderForm) objActionForm;
			HttpSession objSession = objHttpServletRequest.getSession(false);

			LoginVO objLoginVo = (LoginVO) objSession
					.getAttribute(ApplicationConstants.USER_IN_SESSION);

			// Getting User from the session
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}

			if (objHttpServletRequest.getParameter("orderKey") != null) {

				intOrderKey = Integer.parseInt(objHttpServletRequest
						.getParameter("orderKey"));

			}
			
			if (objHttpServletRequest.getParameter("clauseSeqNo") != null) {

				intClauseSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("clauseSeqNo"));

			}

			if (objHttpServletRequest.getParameter("modelSeqNo") != null) {

				intModelSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("modelSeqNo"));

			}

			if (objHttpServletRequest.getParameter("secSeqNo") != null) {

				intSecSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("secSeqNo"));

			}
			
			if (objHttpServletRequest.getParameter("subSecSeqNo") != null) {

				intSubSecSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("subSecSeqNo"));

			}
			
			if (objHttpServletRequest.getParameter("compSeqNo") != null) {

				intCompSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("compSeqNo"));

			}
			
			if (objHttpServletRequest.getParameter("compGrpSeqNo") != null) {

				intCompGrpSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("compGrpSeqNo"));

			}
			
			if (objHttpServletRequest.getParameter("frmModelSeqNo") != null) {

				intFromModelSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("frmModelSeqNo"));

			}
			
			if (objHttpServletRequest.getParameter("frmSubSecSeqNo") != null) {

				intFromSubSecSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("frmSubSecSeqNo"));

			}
			
			ComponentVO objComponentVO = new ComponentVO();
			
			objComponentVO.setModelSeqNo(intModelSeqNo);			
			objComponentVO.setSubSectionSeqNo(intSubSecSeqNo);			
			objComponentVO.setComponentSeqNo(intCompSeqNo);		
			objComponentVO.setComponentGroupSeqNo(intCompGrpSeqNo);
			//Added for CR-128 to add Component Validation Flag
			LogUtil.logMessage("objAddClauseOrderForm.getHdnValidFlag():"+objAddClauseOrderForm.getHdnValidFlag());
			objComponentVO.setValidationFlag(objAddClauseOrderForm.getHdnValidFlag());
			
			objComponentVO.setUserID(strUserID);
			
			ModelCompMapBI objModelCompMapBo = ServiceFactory.getModelCompMapBO();
			intStatusCode = objModelCompMapBo.insertCompMap(objComponentVO);	
						
			if (intStatusCode == 0 || intStatusCode == ApplicationConstants.COMP_MAP_EXISTS_ID) {
				
				objModelCompMapBo = ServiceFactory.getModelCompMapBO();
				ArrayList arlCompMapList = objModelCompMapBo.fetchCompMap(objComponentVO);

				if (arlCompMapList != null && arlCompMapList.size() > 0) {
					ArrayList arlCompList = (ArrayList) arlCompMapList.get(0);
					objAddClauseOrderForm.setModelCompVO(arlCompList);
				}
				
				ClauseVO objClauseVO = new ClauseVO();
				
				objClauseVO.setModelSeqNo(intFromModelSeqNo);			
				objClauseVO.setSubSectionSeqNo(intFromSubSecSeqNo);			
				objClauseVO.setClauseSeqNo(intClauseSeqNo);	
				objClauseVO.setDefaultFlag(ApplicationConstants.YES);
				
				objClauseVO.setUserID(strUserID);
				
				ModelSelectClauseRevBI objModelSelectClauseRevBO = ServiceFactory.getModelSelectClauseRevBO();
				arlClauseList = objModelSelectClauseRevBO.fetchClauseVersions(objClauseVO);
				
				if (arlClauseList != null && arlClauseList.size() > 0) {
					
					objOrderVO.setOrderKey(intOrderKey);
					objOrderVO.setUserID(objLoginVo.getUserID());
					objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);
					OrderBI objOrderBI = ServiceFactory.getOrderBO();
					arlOrderList = objOrderBI.fetchOrders(objOrderVO);

					if (arlOrderList.size() != 0) {
						for (int counter = 0; counter < arlOrderList.size(); counter++) {
							objOrderVO = (OrderVO) arlOrderList.get(counter);
							objAddClauseOrderForm.setOrderNo(objOrderVO.getOrderNo());
							objAddClauseOrderForm.setHdnOrderNo(objOrderVO.getOrderNo());
							objAddClauseOrderForm.setHdnModelSeqNo(objOrderVO.getModelSeqNo());
							objAddClauseOrderForm.setSpecStatusCode(objOrderVO.getSpecStatusCode());
							objAddClauseOrderForm.setChangeControlFlag(objOrderVO.getChangeControlFlag());
						}
					}
					
					objSubSectionVO.setUserID(objLoginVo.getUserID());
					objSubSectionVO.setSubSecSeqNo(intSubSecSeqNo);
					objSubSectionVO.setOrderKey(intOrderKey);
					objSubSectionVO.setDataLocationType(DatabaseConstants.DATALOCATION);
					
					OrderSectionBI objOrderSectionBO = ServiceFactory.getOrderSectionBO();
					arlSubSecDetails = objOrderSectionBO.fetchSubSectionDetails(objSubSectionVO);
					
					if (arlSubSecDetails.size() != 0) {

						for (int counter = 0; counter < arlSubSecDetails.size(); counter++) {
							objSubSectionVO = (SubSectionVO) arlSubSecDetails
									.get(counter);
							strSubSecCode = objSubSectionVO.getSubSecCode();
							strSubSecName = objSubSectionVO.getSubSecName();
							objAddClauseOrderForm.setSubSection(strSubSecCode.concat(
									".").concat(" ").concat(strSubSecName));
							objAddClauseOrderForm.setHdnSubsection(strSubSecCode
									.concat(".").concat(" ").concat(strSubSecName));
						}
					}

					/**
					 * ****** This part of code is used to Display the section name and
					 * code *****
					 */

					objSection.setUserID(objLoginVo.getUserID());
					objSection.setSectionSeqNo(intSecSeqNo);
					objSection.setOrderKey(intOrderKey);
					objSection.setDataLocationType(DatabaseConstants.DATALOCATION);
					objOrderSectionBO = ServiceFactory.getOrderSectionBO();
					arlSectionList = objOrderSectionBO
							.fetchSectionDetails(objSection);

					if (arlSectionList.size() != 0) {

						for (int counter = 0; counter < arlSectionList.size(); counter++) {
							objSubSectionVO = (SubSectionVO) arlSectionList
									.get(counter);
							strSecCode = objSubSectionVO.getSecCode();
							strSecName = objSubSectionVO.getSecName();
							objAddClauseOrderForm.setSection(strSecCode.concat(".")
									.concat(" ").concat(strSecName));
							objAddClauseOrderForm.setHdnSection(strSecCode.concat(".")
									.concat(" ").concat(strSecName));
						}
					}

					objAddClauseOrderForm.setRoleID(objLoginVo.getRoleID());

					int OrderSpecType = objOrderBI.fetchOrderSpecType(objOrderVO);

					if (OrderSpecType > 0) {

						objAddClauseOrderForm.setSpecTypeSeqNo(OrderSpecType);

					}

					objSection.setOrderKey(intOrderKey);
					objSection.setDataLocationType(DatabaseConstants.DATALOCATION);
					objSection.setUserID(objLoginVo.getUserID());

					objSubSectionVO.setSubSecSeqNo(intSubSecSeqNo);

					OrderSectionBI obOrderSectionBO = ServiceFactory
							.getOrderSectionBO();
					obSubSectionVO = obOrderSectionBO.fetchComponents(objSubSectionVO,
							objSection, objConnection, "N");
					
					if (obSubSectionVO != null) {
						objAddClauseOrderForm.setCompGroupList(obSubSectionVO
								.getCompGroup());
					}
					
					objStandardEquipVO.setUserID(objLoginVo.getUserID());
					
					StandardEquipBI objStandardEquipBO = ServiceFactory.getStandardEquipBO();
					arlStandardEquipList = objStandardEquipBO.fetchStandardEquipment(objStandardEquipVO);
					if (arlStandardEquipList != null && arlStandardEquipList.size() > 0) {
						objAddClauseOrderForm.setStandardEquipmentList(arlStandardEquipList);
					}
					
					arlDefaultVersion = (ArrayList) arlClauseList.get(1);
					LogUtil.logMessage("arlClauseList.size " + arlClauseList.size());
					objAddClauseOrderForm.setLeadClauseVO(arlDefaultVersion);
					objAddClauseOrderForm.setHdnClauseSeqNo(objClauseVO.getClauseSeqNo());
					objAddClauseOrderForm.setLeadComponentSeqNo(intCompSeqNo);
					objAddClauseOrderForm.setCompGroupSeqNo(intCompGrpSeqNo);
					objAddClauseOrderForm.setHdnOrderKey(intOrderKey);
					objAddClauseOrderForm.setOrderKey(intOrderKey);
					
				}
			}
			
		} catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}

		return objActionMapping.findForward(strForwardKey);
	}
	
}
