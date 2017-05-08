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
import com.EMD.LSDB.bo.serviceinterface.MainFeatureInfoBI;
import com.EMD.LSDB.bo.serviceinterface.OrderBI;
import com.EMD.LSDB.bo.serviceinterface.OrderGenArrangeBI;
import com.EMD.LSDB.bo.serviceinterface.OrderPerfCurveBI;
import com.EMD.LSDB.bo.serviceinterface.OrderSectionBI;
import com.EMD.LSDB.bo.serviceinterface.OrderSpecificationBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.constant.DatabaseConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.form.SpecMaintenance.ViewSpecByOrderForm;
import com.EMD.LSDB.vo.common.AppendixVO;
import com.EMD.LSDB.vo.common.GenArrangeVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.MainFeatureInfoVO;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.PerformanceCurveVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SpecificationVO;

public class ViewSpecByOrderAction extends EMDAction {

	/***************************************************************************
	 * This Method is used to fetch the Details
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
	public ActionForward initLoad(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
			throws DataAccessException, ApplicationException {

		LogUtil.logMessage("Entering ViewSpecByOrderAction.initLoad");
		String strForward = ApplicationConstants.SUCCESS;
		ViewSpecByOrderForm objViewSpecByOrder = (ViewSpecByOrderForm) objActionForm;
		int intOrderKey = 0;
		int intRevCode = 0;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);

			OrderBI objOrderBI = ServiceFactory.getOrderBO();
			OrderVO objOrderVO = new OrderVO();
			ArrayList arlOrderList = new ArrayList();

			OrderSectionBI objOrderSectionBo = ServiceFactory
					.getOrderSectionBO();
			SectionVO objSectionVo = new SectionVO();
			SectionVO objSecVo = new SectionVO();
			ArrayList arlSectionList = new ArrayList();
			ArrayList arlSubSecList = new ArrayList();

			MainFeatureInfoBI objMainFeatureInfoBI = ServiceFactory
					.getMainfeatureInfoBO();
			MainFeatureInfoVO objMainFeatureInfoVO = new MainFeatureInfoVO();
			ArrayList arlMainFeaList = new ArrayList();
			ArrayList arlOrderDefComps = new ArrayList();

			OrderSpecificationBI objOrderSpecificationBO = ServiceFactory
					.getOrderSpecificationBO();
			SpecificationVO objSpecificationVO = new SpecificationVO();
			ArrayList arlSpecList = new ArrayList();

			OrderGenArrangeBI objOrderGenArrBO = ServiceFactory
					.getOrderGenArrBO();
			GenArrangeVO objGenArrangeVO = new GenArrangeVO();
			ArrayList objGenArngmtList = new ArrayList();

			OrderPerfCurveBI objOrderPerfCurveBO = ServiceFactory
					.getOrderPerfCurveBO();
			PerformanceCurveVO objPerformanceCurveVO = new PerformanceCurveVO();
			ArrayList arlPerCurveList = new ArrayList();

			ArrayList arlSecDetList = new ArrayList();
			String strUserID = "";

			ArrayList arlSecSeqNo = new ArrayList();
			ArrayList arlSecName = new ArrayList();
			ArrayList arlSecCode = new ArrayList();

			LoginVO objLoginVo = (LoginVO) objSession
					.getAttribute(ApplicationConstants.USER_IN_SESSION);

			// Getting User from the session
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}

			// Getting Order Key and Revision Code From request object
			if (objHttpServletRequest.getParameter("orderKey") != null) {
				intOrderKey = Integer.parseInt(objHttpServletRequest
						.getParameter("orderKey").toString().trim());
			}

			if ((String) objHttpServletRequest.getParameter("revCode") != null) {

				intRevCode = Integer.parseInt(objHttpServletRequest
						.getParameter("revCode"));
			}

			// To fetch General Info

			objMainFeatureInfoVO.setOrderKey(intOrderKey);
			objMainFeatureInfoVO
					.setDataLocationType(DatabaseConstants.DATALOCATION);
			objMainFeatureInfoVO.setUserID(strUserID);
			// Added for CR-74 VV49326 09-06-09
			objMainFeatureInfoVO.setRevCode(intRevCode);

			arlMainFeaList = objMainFeatureInfoBI
					.fetchMainFeatures(objMainFeatureInfoVO);

			if (arlMainFeaList != null && arlMainFeaList.size() > 0) {
				objViewSpecByOrder.setMainFeaList(arlMainFeaList);
			}

			// Added for CR fetch Order Components to display in Main Features

			// Commented for LSDB_CR-62
			// arlOrderDefComps=objMainFeatureInfoBI.fetchOrderDispComps(objMainFeatureInfoVO);
			// Changed for LSDB_CR-62
			objMainFeatureInfoVO.setDisplayInSpec(true);
			arlOrderDefComps = objMainFeatureInfoBI
					.fetchModelMainFeatures(objMainFeatureInfoVO);
			if (arlOrderDefComps != null && arlOrderDefComps.size() > 0) {

				objViewSpecByOrder.setOrderDefCompsList(arlOrderDefComps);
			}

			// To fetch Order

			objOrderVO.setOrderKey(intOrderKey);
			objOrderVO.setUserID(strUserID);
			objOrderVO.setDataLocTypeCode(DatabaseConstants.DATALOCATION);

			arlOrderList = objOrderBI.fetchOrders(objOrderVO);
			if (arlOrderList != null && arlOrderList.size() > 0) {
				objViewSpecByOrder.setOrderList(arlOrderList);
			}

			// To fetch Sections

			objSectionVo.setOrderKey(intOrderKey);
			objSectionVo.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSectionVo.setUserID(strUserID);
			arlSectionList = objOrderSectionBo.fetchOrdSections(objSectionVo);

			if (arlSectionList != null && arlSectionList.size() > 0) {

				for (int i = 0; i < arlSectionList.size(); i++) {
					objSecVo = (SectionVO) arlSectionList.get(i);
					arlSecSeqNo.add(new Integer(objSecVo.getSectionSeqNo()));
					arlSecName.add(objSecVo.getSectionName());
					arlSecCode.add(objSecVo.getSectionCode());
				}
				objViewSpecByOrder.setSectionList(arlSectionList);
			}

			// To fetch Specification Items

			objSpecificationVO.setOrderKey(intOrderKey);
			objSpecificationVO
					.setDataLocationType(DatabaseConstants.DATALOCATION);
			objSpecificationVO.setUserID(strUserID);
			// Added for CR-74 VV49326 09-06-09
			objSpecificationVO.setRevCode(intRevCode);
			arlSpecList = objOrderSpecificationBO
					.fetchSpecificationItems(objSpecificationVO);

			if (arlSpecList != null && arlSpecList.size() > 0) {
				objViewSpecByOrder.setSpecList(arlSpecList);
			}

			// To fetch Order General Arrangements

			objGenArrangeVO.setOrderKey(intOrderKey);
			objGenArrangeVO.setUserID(strUserID);
			// Added for CR-74 VV49326 09-06-09
			objGenArrangeVO.setRevisionInput(intRevCode);
			objGenArrangeVO.setDataLocationType(DatabaseConstants.DATALOCATION);

			objGenArngmtList = objOrderGenArrBO
					.fetchGenArrImages(objGenArrangeVO);

			if (objGenArngmtList != null && objGenArngmtList.size() > 0) {
				objViewSpecByOrder.setGenArgmntList(objGenArngmtList);
			}

			// To fetch Sub Section , Components and Clauses

			if (arlSecSeqNo != null && arlSecSeqNo.size() > 0) {
				for (int cnt = 0; cnt < arlSecSeqNo.size(); cnt++) {
					SectionVO objSectVo = new SectionVO();
					objSectVo.setOrderKey(Integer
							.parseInt(objHttpServletRequest.getParameter(
									"orderKey").toString().trim()));
					objSectVo.setSectionSeqNo(Integer.parseInt(arlSecSeqNo.get(
							cnt).toString()));
					objSectVo
							.setDataLocationType(DatabaseConstants.DATALOCATION);
					objSectVo.setSectionName(arlSecName.get(cnt).toString());
					objSectVo.setRevisionInput(intRevCode);
					objSectVo.setUserID(strUserID);
					arlSubSecList = objOrderSectionBo
							.fetchSectionDetails(objSectVo);

					// if(arlSubSecList.size()==0){
					// SubSectionVO objSubSecVo = new SubSectionVO();
					// ClauseVO objClauseVo = new ClauseVO();
					// ArrayList arlClauseList = new ArrayList();
					// objClauseVo.setClauseNum("");
					// arlClauseList.add(objClauseVo);
					// objSubSecVo.setClauseGroup(arlClauseList);
					// objSubSecVo.setSecCode(arlSecCode.get(cnt).toString());
					// objSubSecVo.setSecName(objSectVo.getSectionName());
					// arlSubSecList.add(objSubSecVo);
					// }
					arlSecDetList.add(arlSubSecList);

					arlSubSecList = new ArrayList();
				}
				objViewSpecByOrder.setSubSectionList(arlSubSecList);

				objViewSpecByOrder.setSecDetailList(arlSecDetList);
			}

			// To fetch Performance Curves

			objPerformanceCurveVO.setUserID(objLoginVo.getUserID());
			objPerformanceCurveVO.setOrderKey(intOrderKey);
			// Added for CR-74 VV49326 09-06-09
			objPerformanceCurveVO.setRevisionInput(intRevCode);
			objPerformanceCurveVO
					.setDataLocationType(DatabaseConstants.DATALOCATION);

			arlPerCurveList = objOrderPerfCurveBO
					.fetchPerfCurveImages(objPerformanceCurveVO);
			if (arlPerCurveList != null && arlPerCurveList.size() > 0) {
				objViewSpecByOrder.setPerCurveList(arlPerCurveList);
			}

			// Added for LSDB_CR_42
			ArrayList artlImageList = null;
			AppendixVO objAppendixVO = new AppendixVO();
			objAppendixVO.setUserID(objLoginVo.getUserID());
			objAppendixVO.setDataLocationType(DatabaseConstants.DATALOCATION);
			objAppendixVO.setOrderKey(intOrderKey);
			AppendixBI objAppendixBI = ServiceFactory.getAppendixBO();
			artlImageList = objAppendixBI.fetchImage(objAppendixVO);
			if (artlImageList != null && artlImageList.size() > 0) {

				objViewSpecByOrder.setClauseImageList(artlImageList);
			}

			// Added for LSDB_CR_46 PM&I Change
			int OrderSpecType = objOrderBI.fetchOrderSpecType(objOrderVO);
			LogUtil.logMessage("OrderSpecType :" + OrderSpecType);
			if (OrderSpecType > 0) {

				objViewSpecByOrder.setSpecTypeNo(OrderSpecType);

			}
			// Ends

		} catch (Exception objEx) {

			strForward = ApplicationConstants.FAILURE;
			objEx.printStackTrace();
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);

	}
}
