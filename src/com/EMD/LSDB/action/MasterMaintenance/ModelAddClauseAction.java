package com.EMD.LSDB.action.MasterMaintenance;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.EMD.LSDB.action.common.EMDAction;
import com.EMD.LSDB.bo.factory.ServiceFactory;
import com.EMD.LSDB.bo.serviceinterface.CustomerBI;
import com.EMD.LSDB.bo.serviceinterface.ModelAppendixBI;
import com.EMD.LSDB.bo.serviceinterface.ModelAssignEdlBI;
import com.EMD.LSDB.bo.serviceinterface.ModelBI;
import com.EMD.LSDB.bo.serviceinterface.ModelClauseBI;
import com.EMD.LSDB.bo.serviceinterface.ModelCompBI;
import com.EMD.LSDB.bo.serviceinterface.ModelCompGroupBI;
import com.EMD.LSDB.bo.serviceinterface.ModelSelectClauseRevBI;
import com.EMD.LSDB.bo.serviceinterface.ModelSubSectionBI;
import com.EMD.LSDB.bo.serviceinterface.SectionBI;
import com.EMD.LSDB.bo.serviceinterface.SpecTypeBI;
import com.EMD.LSDB.bo.serviceinterface.StandardEquipBI;
import com.EMD.LSDB.bo.serviceinterface.ViewSpecByModelBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.form.MasterMaintenance.ModelAddClauseForm;
import com.EMD.LSDB.form.MasterMaintenance.ModelAddClauseForm;
import com.EMD.LSDB.form.SpecMaintenance.OrderSectionForm;
import com.EMD.LSDB.vo.common.ClauseTableDataVO;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.CompGroupVO;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.CustomerVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.ModelAppendixVO;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SpecTypeVo;
import com.EMD.LSDB.vo.common.StandardEquipVO;
import com.EMD.LSDB.vo.common.SubSectionVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This Action class has the business methods for the Model
 ******************************************************************************/
/***************************************************************************
 * ------------------------------------------------------------------------------------------------------
 *     Date         version  modified by        comments                                          Remarks 
 * 19/01/2010        1.0      SD41630       Updated for handling null pointer                 Added for CR_81
 * ----------------------------------------------------------------------------------------------------------
 * 15/02/2010        2.0      RR68151       Updated for merging Clause Screens                Added for CR_83
 * 													 	 
 * 16/02/2010        2.1      SD41630    Added  for Model level, SpecificationType,            Added for CR_83
 * 										 Model, Section and Sub Section of values be 	
 *                                       maintain in the session and display in the
 *                                       screen where ever occurred
 * 05/05/2010        2.2      SD41630    Updated the insertClause method                        Added for CR_85
 * 05/05/2010        2.3      SD41630    Added a new  fetchCharCombntnEdlMap method              Added for CR_85										 
 * 05/07/2010        2.4      SD41630    updated inload for clause rearranging                  Added for CR_88
 * 										 and new updateReInsertClause method hasa added 
 * 										for update the rerearrange the clause 	
 * 15/03/2011      2.5     SD41630    loadComponent method has been updated and new              Added for CR_97
 * 									 new method fetchClauseDetails  has been added  	 
 * 										 	
 * --------------------------------------------------------------------------------------------------------
  **************************************************************************/

public class ModelAddClauseAction extends EMDAction {
	
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
		
		LogUtil.logMessage("Entering ModelAddClauseAction.initLoad");
		
		ArrayList arlCustomerList = null;
		ArrayList arlStandardEquipList = null;
		String strForwardKey = ApplicationConstants.SUCCESS;
		ModelAddClauseForm objModelAddClauseForm = (ModelAddClauseForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		
//		Added for CR-113 for clause in Ordersreport
		String strScreenID= Integer.toString(objLoginVo.getIntScreenId());
		if (strScreenID.equalsIgnoreCase("46")) {
			strForwardKey = ApplicationConstants.CLA_IN_ORDERS_REPORT;
		}
//		Added for CR-113 for clause in Ordersreport ends here
		
		ArrayList arlSpec = null;
//		CR_81 lines are started here
		ArrayList arlModelList = null;
		ArrayList arlSectionList = null;
		ArrayList arlSubSecList = null;
		
		int specTypeNo = -1;
		int modleSeqNo = -1;
		int sectionSeqNo = -1;
		int subSectionSeqNo = -1;
		
		String strSpecTypeNo = null;
		String strModleSeqNo = null;
		String strSectionSeqNo = null;
		String strSubSectionSeqNo = null;
		
		strSpecTypeNo = (String) objHttpServletRequest
		.getParameter("specTypeNo");
strModleSeqNo = (String) objHttpServletRequest
		.getParameter("modelSeqNo");
strSectionSeqNo = (String) objHttpServletRequest
		.getParameter("sectionSeqNo");
strSubSectionSeqNo = (String) objHttpServletRequest
		.getParameter("subSectionSeqNo");
try {

if (strSpecTypeNo != null) {
	LogUtil
			.logMessage(" Ends From request***********************************111");
	objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);

	specTypeNo = Integer.parseInt(strSpecTypeNo);
	LogUtil.logMessage("value of strSpecTypeNo from  session"
			+ strSpecTypeNo);
	objModelAddClauseForm.setSpecTypeNo(specTypeNo);

	LogUtil
			.logMessage(" Ends From request***********************************2222");
} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
	if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {

		LogUtil
				.logMessage(" Ends From request***********************************3333");
		strSpecTypeNo = (String) objSession
				.getAttribute("SPEC_TYPE_NO");
		specTypeNo = Integer.parseInt(strSpecTypeNo);
		objModelAddClauseForm.setSpecTypeNo(specTypeNo);
		LogUtil
				.logMessage(" Ends From request***********************************444");

	}
}

if (strModleSeqNo != null) {

	objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
	modleSeqNo = Integer.parseInt(strModleSeqNo);
	LogUtil.logMessage("value of MODEL_SEQ_NO from session "
			+ modleSeqNo);
	objModelAddClauseForm.setModelSeqNo(modleSeqNo);

} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {

	if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
		strModleSeqNo = (String) objSession
				.getAttribute("MODEL_SEQ_NO");
		modleSeqNo = Integer.parseInt(strModleSeqNo);
		objModelAddClauseForm.setModelSeqNo(modleSeqNo);
	}
}

if (strSectionSeqNo != null) {

	objSession.setAttribute("SECTION_SEQ_NO", strSectionSeqNo);
	sectionSeqNo = Integer.parseInt(strSectionSeqNo);
	LogUtil.logMessage("value of SECTION_SEQ_NO from session "
			+ strSectionSeqNo);

	objModelAddClauseForm.setSectionSeqNo(sectionSeqNo);
} else if (objSession.getAttribute("SECTION_SEQ_NO") != null) {
	if (strSectionSeqNo == null || "".equals(strSectionSeqNo)) {
		strSectionSeqNo = (String) objSession
				.getAttribute("SECTION_SEQ_NO");
		sectionSeqNo = Integer.parseInt(strSectionSeqNo);
		objModelAddClauseForm.setSectionSeqNo(sectionSeqNo);

	}
}

if (strSubSectionSeqNo != null) {
	objSession.setAttribute("SUB_SECTION_SEQ_NO", strSubSectionSeqNo);
	subSectionSeqNo = Integer.parseInt(strSubSectionSeqNo);
	objModelAddClauseForm.setSubSectionSeqNo(subSectionSeqNo);
	LogUtil.logMessage("value of SECTION_SEQ_NO from session "
			+ subSectionSeqNo);
} else if (objSession.getAttribute("SUB_SECTION_SEQ_NO") != null) {
	if (strSubSectionSeqNo == null || "".equals(strSubSectionSeqNo)) {
		strSubSectionSeqNo = (String) objSession
				.getAttribute("SUB_SECTION_SEQ_NO");
		objSession.setAttribute("SUB_SECTION_SEQ_NO",
				strSubSectionSeqNo);
		subSectionSeqNo = Integer.parseInt(strSubSectionSeqNo);
		objModelAddClauseForm.setSubSectionSeqNo(subSectionSeqNo);	
		LogUtil.logMessage("value of SUB_SECTION_SEQ_NO from session if SUB_SECTION_SEQ_NO is not null "
						+ strSubSectionSeqNo);
	}

}

specTypeNo = objModelAddClauseForm.getSpecTypeNo();
modleSeqNo = objModelAddClauseForm.getModelSeqNo();
sectionSeqNo = objModelAddClauseForm.getSectionSeqNo();
subSectionSeqNo = objModelAddClauseForm.getSubSectionSeqNo();

//CR_81 lines are started here

			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			arlSpec = fetchSpecType(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpec);
			//objModelMaintForm.setHideMessage("fromInitLoad");
			CustomerVO objCustomerVO = new CustomerVO();
			objCustomerVO.setUserID(objLoginVo.getUserID());
			
			StandardEquipVO objStandardEquipVO = new StandardEquipVO();
			objStandardEquipVO.setUserID(objLoginVo.getUserID());
			arlCustomerList = new ArrayList();
			arlStandardEquipList = new ArrayList();
			
			arlCustomerList = fetchCustomer(objCustomerVO);
			arlStandardEquipList = fetchStandardEquipment(objStandardEquipVO);
			objModelAddClauseForm.setSpecTypeList(arlSpec);
			objModelAddClauseForm.setCustomerList(arlCustomerList);
			objModelAddClauseForm
			.setStandardEquipmentList(arlStandardEquipList);
			
			//Added For CR_83 to merge clause Screens
			objModelAddClauseForm.setModifyClauseFlag(ApplicationConstants.NO);
			//Added For CR_88 on 28-jun-10 by Sd41630
			if(objModelAddClauseForm.getChildFlag()!=null){
			}else{
			objModelAddClauseForm.setChildFlag(ApplicationConstants.YES);
			}
			if (specTypeNo != -1 && specTypeNo != 0) {

				LogUtil.logMessage("in side modleSeqNo" + modleSeqNo);

				ModelVo objModelVo = new ModelVo();
				objModelVo.setUserID(objLoginVo.getUserID());
				objModelVo.setSpecTypeSeqNo(specTypeNo);
				ModelBI objModelBO = ServiceFactory.getModelBO();
				if (objModelBO.fetchModels(objModelVo) != null) {
					arlModelList = objModelBO.fetchModels(objModelVo);
					objModelAddClauseForm.setModelList(arlModelList);
					objModelAddClauseForm.setModelSeqNo(modleSeqNo);
					LogUtil.logMessage("end of the if modleSeqNo" + modleSeqNo);
					if (modleSeqNo != -1 && modleSeqNo != 0) {

						LogUtil.logMessage("in side if sectionSeqNo" + sectionSeqNo);

						SectionVO objSectionMaintVo = new SectionVO();
						objSectionMaintVo.setModelSeqNo(modleSeqNo);
						objSectionMaintVo.setUserID(objLoginVo.getUserID());
						SectionBI objSectionMaintBO = ServiceFactory.getSectionBO();
						arlSectionList = objSectionMaintBO
								.fetchSections(objSectionMaintVo);
						if (arlSectionList != null){
							objModelAddClauseForm.setSectionList(arlSectionList);
						objModelAddClauseForm.setSectionSeqNo(sectionSeqNo);
						LogUtil.logMessage("end of the if sectionSeqNo" + sectionSeqNo);
						if (sectionSeqNo != -1 && sectionSeqNo != 0) {
							LogUtil.logMessage("in side if subSectionSeqNo"
									+ subSectionSeqNo);
							SubSectionVO objSubSecMaintVO = new SubSectionVO();
							objSubSecMaintVO.setModelSeqNo(modleSeqNo);
							objSubSecMaintVO.setSecSeqNo(sectionSeqNo);
							objSubSecMaintVO.setUserID(objLoginVo.getUserID());

							ModelSubSectionBI objSubSecMaintBO = ServiceFactory
									.getSubSecMaintBO();
							arlSubSecList = objSubSecMaintBO
									.fetchSubSections(objSubSecMaintVO);

							if (arlSubSecList != null && arlSubSecList.size() != 0) {

								objModelAddClauseForm.setSubSectionList(arlSubSecList);
								
								if (subSectionSeqNo != -1 && subSectionSeqNo != 0) {
								
								objModelAddClauseForm.setSubSectionSeqNo(subSectionSeqNo);

								LogUtil.logMessage("end of the if subSectionSeqNo"
										+ subSectionSeqNo);
								
								objSubSecMaintVO.setSubSecSeqNo(objModelAddClauseForm.getSubSectionSeqNo());
								//Added for CR_94 for getting the parent & child clauses
								objSubSecMaintVO.setRearrFlag("N");
								//CR_94 Ends here
								ArrayList arlClaList = fetchClauseDesc(objSubSecMaintVO);
								
								ComponentVO objComponentVO = new ComponentVO();
								objComponentVO.setUserID(objLoginVo.getUserID());
								objComponentVO.setSubSectionSeqNo(objModelAddClauseForm
										.getSubSectionSeqNo());
								objComponentVO.setModelSeqNo(objModelAddClauseForm.getModelSeqNo());

								ModelCompBI objModelCompBO = ServiceFactory.getModelCompBO();
								ArrayList arlCompGroupList = objModelCompBO.fetchModelComps(objComponentVO);
									
								if (arlCompGroupList != null && arlCompGroupList.size() > 0) {
									objModelAddClauseForm.setCompGroupList(arlCompGroupList);
								}
								//Added for CR_88 on 5July10
								if (arlClaList != null && arlClaList.size() > 0) {
									//Added & modified for CR_94
									objModelAddClauseForm.setComponentVO((ArrayList) arlClaList.get(0));
									objModelAddClauseForm.setParentClaList((ArrayList) arlClaList.get(1));
									objModelAddClauseForm.setChildClaList((ArrayList) arlClaList.get(2));
									//CR_94 Ends here
								}else{
									objModelAddClauseForm.setMethod("updateReInsertClause");
									}
								}

							else {

								objModelAddClauseForm.setMethod("SubSections");

							}

						}
						
						}
						}
					}

					
				}

			}

			
		} catch (Exception objExp) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}

		return objActionMapping.findForward(strForwardKey);

	}
	
	/***************************************************************************
	 * This method is for loading the Model
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
	public ActionForward fetchModels(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		ArrayList arlModelList, arlCustomerList, arlStandardEquipList;
		
		LogUtil.logMessage("Inside ModelAddClauseAction:initLoad");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ModelAddClauseForm objModelAddClauseForm = (ModelAddClauseForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
//		Added for CR-113 for clause in Ordersreport
		String strScreenID= Integer.toString(objLoginVo.getIntScreenId());
		if (strScreenID.equalsIgnoreCase("46")) {
			strForwardKey = ApplicationConstants.CLA_IN_ORDERS_REPORT;
		}
//		Added for CR-113 for clause in Ordersreport ends here
		ArrayList arlSpec = null;
//		CR_81 lines are started here
		//ArrayList arlModelList = null;
		ArrayList arlSectionList = null;
		ArrayList arlSubSecList = null;
		int specTypeNo = -1;
		int modleSeqNo = -1;
		int sectionSeqNo = -1;
		int subSectionSeqNo = -1;
		
		String strSpecTypeNo = null;
		String strModleSeqNo = null;
		String strSectionSeqNo = null;
		String strSubSectionSeqNo = null;
		//CR_81 lines are started here
		strSpecTypeNo = (String) objHttpServletRequest
		.getParameter("specTypeNo");
strModleSeqNo = (String) objHttpServletRequest
		.getParameter("modelSeqNo");
strSectionSeqNo = (String) objHttpServletRequest
		.getParameter("sectionSeqNo");
strSubSectionSeqNo = (String) objHttpServletRequest
		.getParameter("subSectionSeqNo");
try {

if (strSpecTypeNo != null) {
		objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);
	specTypeNo = Integer.parseInt(strSpecTypeNo);
	objModelAddClauseForm.setSpecTypeNo(specTypeNo);
	} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
	if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {
		strSpecTypeNo = (String) objSession
				.getAttribute("SPEC_TYPE_NO");
		specTypeNo = Integer.parseInt(strSpecTypeNo);
		objModelAddClauseForm.setSpecTypeNo(specTypeNo);
			}
}

if (strModleSeqNo != null) {

	objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
	modleSeqNo = Integer.parseInt(strModleSeqNo);
	LogUtil.logMessage("value of MODEL_SEQ_NO from session "
			+ modleSeqNo);
	objModelAddClauseForm.setModelSeqNo(modleSeqNo);

} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {

	if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
		strModleSeqNo = (String) objSession
				.getAttribute("MODEL_SEQ_NO");
		modleSeqNo = Integer.parseInt(strModleSeqNo);
		objModelAddClauseForm.setModelSeqNo(modleSeqNo);
	}
}

if (strSectionSeqNo != null) {

	objSession.setAttribute("SECTION_SEQ_NO", strSectionSeqNo);
	sectionSeqNo = Integer.parseInt(strSectionSeqNo);
	
	objModelAddClauseForm.setSectionSeqNo(sectionSeqNo);
} else if (objSession.getAttribute("SECTION_SEQ_NO") != null) {
	if (strSectionSeqNo == null || "".equals(strSectionSeqNo)) {
		strSectionSeqNo = (String) objSession
				.getAttribute("SECTION_SEQ_NO");
		sectionSeqNo = Integer.parseInt(strSectionSeqNo);
		objModelAddClauseForm.setSectionSeqNo(sectionSeqNo);

	}
}

if (strSubSectionSeqNo != null) {
	objSession.setAttribute("SUB_SECTION_SEQ_NO", strSubSectionSeqNo);
	subSectionSeqNo = Integer.parseInt(strSubSectionSeqNo);
	objModelAddClauseForm.setSubSectionSeqNo(subSectionSeqNo);
	
} else if (objSession.getAttribute("SUB_SECTION_SEQ_NO") != null) {
	if (strSubSectionSeqNo == null || "".equals(strSubSectionSeqNo)) {
		strSubSectionSeqNo = (String) objSession
				.getAttribute("SUB_SECTION_SEQ_NO");
		objSession.setAttribute("SUB_SECTION_SEQ_NO",
				strSubSectionSeqNo);
		subSectionSeqNo = Integer.parseInt(strSubSectionSeqNo);
		objModelAddClauseForm.setSubSectionSeqNo(subSectionSeqNo);
			}

}

specTypeNo = objModelAddClauseForm.getSpecTypeNo();
modleSeqNo = objModelAddClauseForm.getModelSeqNo();
sectionSeqNo = objModelAddClauseForm.getSectionSeqNo();
subSectionSeqNo = objModelAddClauseForm.getSubSectionSeqNo();



			
			CustomerVO objCustomerVO = new CustomerVO();
			objCustomerVO.setUserID(objLoginVo.getUserID());
			
			StandardEquipVO objStandardEquipVO = new StandardEquipVO();
			objStandardEquipVO.setUserID(objLoginVo.getUserID());
			
			arlModelList = new ArrayList();
			arlCustomerList = new ArrayList();
			arlStandardEquipList = new ArrayList();
			
			//Added for LSDB_CR_46
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			arlSpec = fetchSpecType(objSpecTypeVo);
			
			arlCustomerList = fetchCustomer(objCustomerVO);
			arlStandardEquipList = fetchStandardEquipment(objStandardEquipVO);
			
			objModelAddClauseForm.setSpecTypeList(arlSpec);
			LogUtil.logMessage("In Customer List");
			objModelAddClauseForm.setCustomerList(arlCustomerList);
			LogUtil.logMessage("In StandardEquipList List");
			objModelAddClauseForm
			.setStandardEquipmentList(arlStandardEquipList);
			
			//Added For CR_83 to merge clause Screens
			LogUtil.logMessage("ModifyClauseFlag :" + objModelAddClauseForm.getModifyClauseFlag());
			objModelAddClauseForm.setModifyClauseFlag(objModelAddClauseForm.getModifyClauseFlag());
			//Added For CR_88 on 28-jun-10 by Sd41630
			LogUtil.logMessage("***ChildFlag*** :" + objModelAddClauseForm.getChildFlag());
			objModelAddClauseForm.setChildFlag(objModelAddClauseForm.getChildFlag());
			
			if (specTypeNo != -1 && specTypeNo != 0) {

				LogUtil.logMessage("in side modleSeqNo" + modleSeqNo);

				ModelVo objModelVo = new ModelVo();
				objModelVo.setUserID(objLoginVo.getUserID());
				objModelVo.setSpecTypeSeqNo(specTypeNo);
				ModelBI objModelBO = ServiceFactory.getModelBO();
				if (objModelBO.fetchModels(objModelVo) != null) {
					arlModelList = objModelBO.fetchModels(objModelVo);
					objModelAddClauseForm.setModelList(arlModelList);
					objModelAddClauseForm.setModelSeqNo(modleSeqNo);
					LogUtil.logMessage("end of the if modleSeqNo" + modleSeqNo);
					if (modleSeqNo != -1 && modleSeqNo != 0) {

						LogUtil.logMessage("in side if sectionSeqNo" + sectionSeqNo);

						SectionVO objSectionMaintVo = new SectionVO();
						objSectionMaintVo.setModelSeqNo(modleSeqNo);
						objSectionMaintVo.setUserID(objLoginVo.getUserID());
						SectionBI objSectionMaintBO = ServiceFactory.getSectionBO();
						arlSectionList = objSectionMaintBO
								.fetchSections(objSectionMaintVo);
						if (arlSectionList != null){
							objModelAddClauseForm.setSectionList(arlSectionList);
						objModelAddClauseForm.setSectionSeqNo(sectionSeqNo);
						LogUtil.logMessage("end of the if sectionSeqNo" + sectionSeqNo);
						if (sectionSeqNo != -1 && sectionSeqNo != 0) {
							LogUtil.logMessage("in side if subSectionSeqNo"
									+ subSectionSeqNo);
							SubSectionVO objSubSecMaintVO = new SubSectionVO();
							objSubSecMaintVO.setModelSeqNo(modleSeqNo);
							objSubSecMaintVO.setSecSeqNo(sectionSeqNo);
							objSubSecMaintVO.setUserID(objLoginVo.getUserID());

							ModelSubSectionBI objSubSecMaintBO = ServiceFactory
									.getSubSecMaintBO();
							arlSubSecList = objSubSecMaintBO
									.fetchSubSections(objSubSecMaintVO);

							if (arlSubSecList != null && arlSubSecList.size() != 0) {

								objModelAddClauseForm.setSubSectionList(arlSubSecList);
								
								if (subSectionSeqNo != -1 && subSectionSeqNo != 0) {
								
								objModelAddClauseForm.setSubSectionSeqNo(subSectionSeqNo);

								LogUtil.logMessage("end of the if subSectionSeqNo"
										+ subSectionSeqNo);
								
								objSubSecMaintVO.setSubSecSeqNo(objModelAddClauseForm.getSubSectionSeqNo());
								//Added for CR_94 for getting the parent & child clauses
								objSubSecMaintVO.setRearrFlag("N");
								//CR_94 Ends here
								ArrayList arlClaList = fetchClauseDesc(objSubSecMaintVO);
								
								ComponentVO objComponentVO = new ComponentVO();
								objComponentVO.setUserID(objLoginVo.getUserID());
								objComponentVO.setSubSectionSeqNo(objModelAddClauseForm
										.getSubSectionSeqNo());
								objComponentVO.setModelSeqNo(objModelAddClauseForm.getModelSeqNo());

								ModelCompBI objModelCompBO = ServiceFactory.getModelCompBO();
								ArrayList arlCompGroupList = objModelCompBO.fetchModelComps(objComponentVO);
									
								if (arlCompGroupList != null && arlCompGroupList.size() > 0) {
									objModelAddClauseForm.setCompGroupList(arlCompGroupList);
								}
								//Added for CR_88 on 5July10
								if (arlClaList != null && arlClaList.size() > 0) {
									//Added & modified for CR_94
									objModelAddClauseForm.setComponentVO((ArrayList) arlClaList.get(0));
									objModelAddClauseForm.setParentClaList((ArrayList) arlClaList.get(1));
									objModelAddClauseForm.setChildClaList((ArrayList) arlClaList.get(2));
									//CR_94 Ends here
								}else{
									objModelAddClauseForm.setMethod("updateReInsertClause");
									}
								}

							else {

								objModelAddClauseForm.setMethod("SubSections");

							}

						}
						
						}
						}
					}

					
				}

			}

			
			
		}
		
		catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***************************************************************************
	 * This method is for loading fetchSpecType List.
	 * 
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
	
	/***************************************************************************
	 * This method is for loading StandardEquipment List.
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objStandardEquipVO
	 * @return ArrayList
	 * @throws BusinessException
	 **************************************************************************/
	
	private ArrayList fetchStandardEquipment(StandardEquipVO objStandardEquipVO)
	throws Exception {
		ArrayList arlEquipList = new ArrayList();
		
		StandardEquipBI objStandardEquipBO = ServiceFactory
		.getStandardEquipBO();
		arlEquipList = objStandardEquipBO
		.fetchStandardEquipment(objStandardEquipVO);
		
		return arlEquipList;
	}
	
	/***************************************************************************
	 * This method is for loading Customer List.
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objCustomerVO
	 * @return ArrayList
	 * @throws BusinessException
	 **************************************************************************/
	private ArrayList fetchCustomer(CustomerVO objCustomerVO) throws Exception {
		ArrayList arlCustList = new ArrayList();
		
		CustomerBI objCustomerBO = ServiceFactory.getCustomerBO();
		arlCustList = objCustomerBO.fetchCustomers(objCustomerVO);
		return arlCustList;
		
	}
	
	/***************************************************************************
	 * This method is for loading Model List.
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objModelVO
	 * @return ArrayList
	 * @throws BusinessException
	 **************************************************************************/
	private ArrayList fetchModel(ModelVo objModelVO) throws Exception {
		ArrayList arlModList = new ArrayList();
		
		ModelBI objModelBO = ServiceFactory.getModelBO();
		arlModList = objModelBO.fetchModels(objModelVO);
		
		return arlModList;
	}
	
	/***************************************************************************
	 * This method is for loading Sections based on Models
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
	
	public ActionForward SectionLoad(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		ArrayList arlModelList, arlCustomerList, arlStandardEquipList;
		ArrayList arlSectionList = new ArrayList();
		LogUtil.logMessage("Inside ModelAddClauseAction :SectionLoad  ");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ModelAddClauseForm objModelAddClauseForm = (ModelAddClauseForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
//		Added for CR-113 for clause in Ordersreport
		String strScreenID= Integer.toString(objLoginVo.getIntScreenId());
		if (strScreenID.equalsIgnoreCase("46")) {
			strForwardKey = ApplicationConstants.CLA_IN_ORDERS_REPORT;
		}
//		Added for CR-113 for clause in Ordersreport ends here
		ArrayList arlSpec = null;
//		CR_81 lines are started here
		ArrayList arlSubSecList = null;
		int specTypeNo = -1;
		int modleSeqNo = -1;
		int sectionSeqNo = -1;
		int subSectionSeqNo = -1;
		
		String strSpecTypeNo = null;
		String strModleSeqNo = null;
		String strSectionSeqNo = null;
		String strSubSectionSeqNo = null;
		//CR_81 lines are started here
		strSpecTypeNo = (String) objHttpServletRequest
		.getParameter("specTypeNo");
strModleSeqNo = (String) objHttpServletRequest
		.getParameter("modelSeqNo");
strSectionSeqNo = (String) objHttpServletRequest
		.getParameter("sectionSeqNo");
strSubSectionSeqNo = (String) objHttpServletRequest
		.getParameter("subSectionSeqNo");
try {

if (strSpecTypeNo != null) {
	LogUtil
			.logMessage(" Ends From request***********************************111");
	objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);

	specTypeNo = Integer.parseInt(strSpecTypeNo);
	LogUtil.logMessage("value of strSpecTypeNo from  session"
			+ strSpecTypeNo);
	objModelAddClauseForm.setSpecTypeNo(specTypeNo);

	LogUtil
			.logMessage(" Ends From request***********************************2222");
} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
	if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {

		LogUtil
				.logMessage(" Ends From request***********************************3333");
		strSpecTypeNo = (String) objSession
				.getAttribute("SPEC_TYPE_NO");
		specTypeNo = Integer.parseInt(strSpecTypeNo);
		objModelAddClauseForm.setSpecTypeNo(specTypeNo);
		LogUtil
				.logMessage(" Ends From request***********************************444");

	}
}

if (strModleSeqNo != null) {

	objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
	modleSeqNo = Integer.parseInt(strModleSeqNo);
	objModelAddClauseForm.setModelSeqNo(modleSeqNo);

} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {

	if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
		strModleSeqNo = (String) objSession
				.getAttribute("MODEL_SEQ_NO");
		modleSeqNo = Integer.parseInt(strModleSeqNo);
		objModelAddClauseForm.setModelSeqNo(modleSeqNo);
	}
}

if (strSectionSeqNo != null) {

	objSession.setAttribute("SECTION_SEQ_NO", strSectionSeqNo);
	sectionSeqNo = Integer.parseInt(strSectionSeqNo);
	LogUtil.logMessage("value of SECTION_SEQ_NO from session "
			+ strSectionSeqNo);

	objModelAddClauseForm.setSectionSeqNo(sectionSeqNo);
} else if (objSession.getAttribute("SECTION_SEQ_NO") != null) {
	if (strSectionSeqNo == null || "".equals(strSectionSeqNo)) {
		strSectionSeqNo = (String) objSession
				.getAttribute("SECTION_SEQ_NO");
		sectionSeqNo = Integer.parseInt(strSectionSeqNo);
		objModelAddClauseForm.setSectionSeqNo(sectionSeqNo);

	}
}

if (strSubSectionSeqNo != null) {
	objSession.setAttribute("SUB_SECTION_SEQ_NO", strSubSectionSeqNo);
	subSectionSeqNo = Integer.parseInt(strSubSectionSeqNo);
	objModelAddClauseForm.setSubSectionSeqNo(subSectionSeqNo);
	} else if (objSession.getAttribute("SUB_SECTION_SEQ_NO") != null) {
	if (strSubSectionSeqNo == null || "".equals(strSubSectionSeqNo)) {
		strSubSectionSeqNo = (String) objSession
				.getAttribute("SUB_SECTION_SEQ_NO");
		objSession.setAttribute("SUB_SECTION_SEQ_NO",
				strSubSectionSeqNo);
		subSectionSeqNo = Integer.parseInt(strSubSectionSeqNo);
		objModelAddClauseForm.setSubSectionSeqNo(subSectionSeqNo);
		
	}

}

specTypeNo = objModelAddClauseForm.getSpecTypeNo();
modleSeqNo = objModelAddClauseForm.getModelSeqNo();
sectionSeqNo = objModelAddClauseForm.getSectionSeqNo();
subSectionSeqNo = objModelAddClauseForm.getSubSectionSeqNo();


			SectionVO objSecMaintVO = new SectionVO();
			objSecMaintVO.setUserID(objLoginVo.getUserID());
			objSecMaintVO.setModelSeqNo(objModelAddClauseForm.getModelSeqNo());
			
			ModelVo objModelMaintVO = new ModelVo();
			objModelMaintVO.setUserID(objLoginVo.getUserID());
			CustomerVO objCustomerVO = new CustomerVO();
			objCustomerVO.setUserID(objLoginVo.getUserID());
			StandardEquipVO objStandardEquipVO = new StandardEquipVO();
			objStandardEquipVO.setUserID(objLoginVo.getUserID());
			
			arlModelList = new ArrayList();
			arlCustomerList = new ArrayList();
			arlStandardEquipList = new ArrayList();
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			arlSectionList = objSectionBO.fetchSections(objSecMaintVO);
			if (arlSectionList.size() != 0) {
				objModelAddClauseForm.setSectionList(arlSectionList);
			}
			ModelBI objModelBO = ServiceFactory.getModelBO();
			StandardEquipBI objStandardEquipBO = ServiceFactory
			.getStandardEquipBO();
			CustomerBI objCustomerBO = ServiceFactory.getCustomerBO();
			
			//Added for LSDB_CR_46
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			arlSpec = fetchSpecType(objSpecTypeVo);
			objModelMaintVO.setSpecTypeSeqNo(objModelAddClauseForm
					.getSpecTypeNo());
			//Ends
			
			arlModelList = objModelBO.fetchModels(objModelMaintVO);
			arlStandardEquipList = objStandardEquipBO
			.fetchStandardEquipment(objStandardEquipVO);
			arlCustomerList = objCustomerBO.fetchCustomers(objCustomerVO);
			
			objModelAddClauseForm.setSpecTypeList(arlSpec);
			objModelAddClauseForm.setModelList(arlModelList);
			objModelAddClauseForm.setCustomerList(arlCustomerList);
			objModelAddClauseForm
			.setStandardEquipmentList(arlStandardEquipList);
			
			objModelAddClauseForm.setParentClauseSeqNo(0);
			
			//Added For CR_83 to merge clause Screens
			LogUtil.logMessage("ModifyClauseFlag :" + objModelAddClauseForm.getModifyClauseFlag());
			objModelAddClauseForm.setModifyClauseFlag(objModelAddClauseForm.getModifyClauseFlag());
			//Added For CR_88 on 28-jun-10 by Sd41630
			objModelAddClauseForm.setChildFlag(objModelAddClauseForm.getChildFlag());
			//CR_83 lines area started here 
			if (sectionSeqNo != -1 && sectionSeqNo != 0) {
				LogUtil.logMessage("in side if subSectionSeqNo"
						+ subSectionSeqNo);
				SubSectionVO objSubSecMaintVO = new SubSectionVO();
				objSubSecMaintVO.setModelSeqNo(modleSeqNo);
				objSubSecMaintVO.setSecSeqNo(sectionSeqNo);
				objSubSecMaintVO.setUserID(objLoginVo.getUserID());

				ModelSubSectionBI objSubSecMaintBO = ServiceFactory
						.getSubSecMaintBO();
				arlSubSecList = objSubSecMaintBO
						.fetchSubSections(objSubSecMaintVO);

				if (arlSubSecList != null && arlSubSecList.size() != 0) {

					objModelAddClauseForm.setSubSectionList(arlSubSecList);
					objModelAddClauseForm.setSubSectionSeqNo(subSectionSeqNo);

					LogUtil.logMessage("end of the if subSectionSeqNo"
							+ subSectionSeqNo);
				}

				else {

					objModelAddClauseForm.setMethod("SubSections");

				}
			}
				//CR_83 lines area ended here
			return objActionMapping.findForward(strForwardKey);
		}
		
		catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
			
		}
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	/***************************************************************************
	 * This method is for loading SubSections based on Sections
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
	
	public ActionForward SubSectionLoad(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		ArrayList arlModelList, arlSectionList, arlCustomerList, arlStandardEquipList;
		ArrayList arlSubSectionList = new ArrayList();
		LogUtil.logMessage("Inside ModelAddClauseAction :SubSectionLoad ");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ModelAddClauseForm objModelAddClauseForm = (ModelAddClauseForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
//		Added for CR-113 for clause in Ordersreport
		String strScreenID= Integer.toString(objLoginVo.getIntScreenId());
		//LogUtil.logMessage("strScreenID"+strScreenID);
		if (strScreenID.equalsIgnoreCase("46")) {
			strForwardKey = ApplicationConstants.CLA_IN_ORDERS_REPORT;
		}
//		Added for CR-113 for clause in Ordersreport ends here
		ArrayList arlSpec = null;
//		CR_81 lines are started here
		ArrayList arlSubSecList = null;
		int specTypeNo = -1;
		int modleSeqNo = -1;
		int sectionSeqNo = -1;
		int subSectionSeqNo = -1;
		
		String strSpecTypeNo = null;
		String strModleSeqNo = null;
		String strSectionSeqNo = null;
		String strSubSectionSeqNo = null;
		//CR_81 lines are started here
		strSpecTypeNo = (String) objHttpServletRequest
		.getParameter("specTypeNo");
strModleSeqNo = (String) objHttpServletRequest
		.getParameter("modelSeqNo");
strSectionSeqNo = (String) objHttpServletRequest
		.getParameter("sectionSeqNo");
strSubSectionSeqNo = (String) objHttpServletRequest
		.getParameter("subSectionSeqNo");
try {

if (strSpecTypeNo != null) {
	objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);
	specTypeNo = Integer.parseInt(strSpecTypeNo);
	objModelAddClauseForm.setSpecTypeNo(specTypeNo);

} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
	if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {

		strSpecTypeNo = (String) objSession
				.getAttribute("SPEC_TYPE_NO");
		specTypeNo = Integer.parseInt(strSpecTypeNo);
		objModelAddClauseForm.setSpecTypeNo(specTypeNo);
		}
}


if (strModleSeqNo != null) {

	objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
	modleSeqNo = Integer.parseInt(strModleSeqNo);
	objModelAddClauseForm.setModelSeqNo(modleSeqNo);

} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {

	if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
		strModleSeqNo = (String) objSession
				.getAttribute("MODEL_SEQ_NO");
		modleSeqNo = Integer.parseInt(strModleSeqNo);
		objModelAddClauseForm.setModelSeqNo(modleSeqNo);
	}
}

if (strSectionSeqNo != null) {

	objSession.setAttribute("SECTION_SEQ_NO", strSectionSeqNo);
	sectionSeqNo = Integer.parseInt(strSectionSeqNo);
	objModelAddClauseForm.setSectionSeqNo(sectionSeqNo);
} else if (objSession.getAttribute("SECTION_SEQ_NO") != null) {
	if (strSectionSeqNo == null || "".equals(strSectionSeqNo)) {
		strSectionSeqNo = (String) objSession
				.getAttribute("SECTION_SEQ_NO");
		sectionSeqNo = Integer.parseInt(strSectionSeqNo);
		objModelAddClauseForm.setSectionSeqNo(sectionSeqNo);

	}
}

if (strSubSectionSeqNo != null) {
	objSession.setAttribute("SUB_SECTION_SEQ_NO", strSubSectionSeqNo);
	subSectionSeqNo = Integer.parseInt(strSubSectionSeqNo);
	objModelAddClauseForm.setSubSectionSeqNo(subSectionSeqNo);
	} else if (objSession.getAttribute("SUB_SECTION_SEQ_NO") != null) {
	if (strSubSectionSeqNo == null || "".equals(strSubSectionSeqNo)) {
		strSubSectionSeqNo = (String) objSession
				.getAttribute("SUB_SECTION_SEQ_NO");
		objSession.setAttribute("SUB_SECTION_SEQ_NO",
				strSubSectionSeqNo);
		subSectionSeqNo = Integer.parseInt(strSubSectionSeqNo);
		objModelAddClauseForm.setSubSectionSeqNo(subSectionSeqNo);
			}

}

specTypeNo = objModelAddClauseForm.getSpecTypeNo();
modleSeqNo = objModelAddClauseForm.getModelSeqNo();
sectionSeqNo = objModelAddClauseForm.getSectionSeqNo();
subSectionSeqNo = objModelAddClauseForm.getSubSectionSeqNo();


			SubSectionVO objSubSectionVO = new SubSectionVO();
			objSubSectionVO
			.setModelSeqNo(objModelAddClauseForm.getModelSeqNo());
			objSubSectionVO
			.setSecSeqNo(objModelAddClauseForm.getSectionSeqNo());
			objSubSectionVO.setUserID(objLoginVo.getUserID());
			
			ModelSubSectionBI objModelSubSecBO = ServiceFactory
			.getSubSecMaintBO();
			arlSubSectionList = objModelSubSecBO
			.fetchSubSections(objSubSectionVO);
			ModelVo objModelMaintVO = new ModelVo();
			objModelMaintVO.setUserID(objLoginVo.getUserID());
			
			CustomerVO objCustomerVO = new CustomerVO();
			objCustomerVO.setUserID(objLoginVo.getUserID());
			
			SectionVO objSectionVO = new SectionVO();
			objSectionVO.setUserID(objLoginVo.getUserID());
			
			StandardEquipVO objStandardEquipVO = new StandardEquipVO();
			objStandardEquipVO.setUserID(objLoginVo.getUserID());
			
			arlModelList = new ArrayList();
			arlSectionList = new ArrayList();
			arlCustomerList = new ArrayList();
			arlStandardEquipList = new ArrayList();
			if (arlSubSectionList.size() != 0) {
				objModelAddClauseForm.setSubSectionList(arlSubSectionList);
			}
			
			ModelBI objModelBO = ServiceFactory.getModelBO();
			StandardEquipBI objStandardEquipBO = ServiceFactory
			.getStandardEquipBO();
			CustomerBI objCustomerBO = ServiceFactory.getCustomerBO();
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			
			//Added for LSDB_CR_46
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			arlSpec = fetchSpecType(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpec);
			
			LogUtil.logMessage("objModelAddClauseForm.getSpecTypeNo() :"
					+ objModelAddClauseForm.getSpecTypeNo());
			objModelMaintVO.setSpecTypeSeqNo(objModelAddClauseForm
					.getSpecTypeNo());
			//Ends
			
			arlModelList = objModelBO.fetchModels(objModelMaintVO);
			arlStandardEquipList = objStandardEquipBO
			.fetchStandardEquipment(objStandardEquipVO);
			arlCustomerList = objCustomerBO.fetchCustomers(objCustomerVO);
			objSectionVO.setModelSeqNo(objModelAddClauseForm.getModelSeqNo());
			arlSectionList = objSectionBO.fetchSections(objSectionVO);
			
			objModelAddClauseForm.setSpecTypeList(arlSpec);
			objModelAddClauseForm.setModelList(arlModelList);
			objModelAddClauseForm.setCustomerList(arlCustomerList);
			objModelAddClauseForm
			.setStandardEquipmentList(arlStandardEquipList);
			objModelAddClauseForm.setSectionList(arlSectionList);
			objModelAddClauseForm.setParentClauseSeqNo(0);

			//Added For CR_83 to merge clause Screens
			LogUtil.logMessage("ModifyClauseFlag :" + objModelAddClauseForm.getModifyClauseFlag());
			objModelAddClauseForm.setModifyClauseFlag(objModelAddClauseForm.getModifyClauseFlag());
			//Added For CR_88 on 28-jun-10 by Sd41630
			objModelAddClauseForm.setChildFlag(objModelAddClauseForm.getChildFlag());
			
			//CR_83 lines area started here 
			if (sectionSeqNo != -1 && sectionSeqNo != 0) {
				LogUtil.logMessage("in side if subSectionSeqNo"
						+ subSectionSeqNo);
				SubSectionVO objSubSecMaintVO = new SubSectionVO();
				objSubSecMaintVO.setModelSeqNo(modleSeqNo);
				objSubSecMaintVO.setSecSeqNo(sectionSeqNo);
				objSubSecMaintVO.setUserID(objLoginVo.getUserID());

				ModelSubSectionBI objSubSecMaintBO = ServiceFactory
						.getSubSecMaintBO();
				arlSubSecList = objSubSecMaintBO
						.fetchSubSections(objSubSecMaintVO);

				if (arlSubSecList != null && arlSubSecList.size() != 0) {

					objModelAddClauseForm.setSubSectionList(arlSubSecList);
					objModelAddClauseForm.setSubSectionSeqNo(subSectionSeqNo);

					LogUtil.logMessage("end of the if subSectionSeqNo"
							+ subSectionSeqNo);
				}

				else {

					objModelAddClauseForm.setMethod("SubSections");

				}
			}
				//CR_83 lines area ended here
			
			
			return objActionMapping.findForward(strForwardKey);
			
			
			
		}
		
		catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
			
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
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
		
		LogUtil.logMessage("Inside ModelAddClauseAction :InsertClause ");
		
		String strForwardKey = ApplicationConstants.SUCCESS;
		try {
			ModelAddClauseForm objModelAddClauseForm = (ModelAddClauseForm) objActionForm;
			HttpSession objSession = objHttpServletRequest.getSession(false);
			ArrayList arlModelList = new ArrayList();
			ArrayList arlCustomerList = new ArrayList();
			ArrayList arlStandardEquipList = new ArrayList();
			ArrayList arlSectionList = new ArrayList();
			ArrayList arlSubSectionList = new ArrayList();
			ArrayList arlClaList = new ArrayList(); //Added For CR_83
			ArrayList arlLeadCompClaList=null;
			ClauseVO objClauseVO = new ClauseVO();
			SectionVO objSecMaintVO = new SectionVO();
			String strUserID = null;
			String strClauseDesc = null;
			String strComments = null;
			String strReason = null;
			int intSuccess = 0;
			//Added For CR_97 
			int leadComponentSeqNo=-1;
			String[] clauseTableArray1, clauseTableArray2, clauseTableArray3, clauseTableArray4, clauseTableArray5 = null;
			ClauseTableDataVO objTableDataVO = null;
	 	ArrayList arlTableList = new ArrayList();
	 	String[] arlEDLNos = { "", "", "" };
		String[] arlRefEDLNos = { "", "", "" };
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			ArrayList arlSpec = null;
			
			// Getting User from the session
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			
					
			
			StandardEquipVO objStandardEquipmentVO = new StandardEquipVO();
			objStandardEquipmentVO.setUserID(strUserID);
			
			clauseTableArray1 = objModelAddClauseForm
			.getClauseTableDataArray1();
			ArrayList arlSelCompSeqNo = new ArrayList();
			StringTokenizer stCompSeqNo = null;
			
			if ((objModelAddClauseForm.getComponentSeqNo() != null)) {
				stCompSeqNo = new StringTokenizer(objModelAddClauseForm
						.getComponentSeqNo(), "~");
				while (stCompSeqNo.hasMoreTokens()) {
					arlSelCompSeqNo.add(stCompSeqNo.nextToken());
				}
			}
			
//			 Added For CR_81 on 24-Dec-09 by  Sd41630 setting characterisitic group or normal group ------- -->
			if((objModelAddClauseForm.isSelectCGCFlag())){
			objClauseVO.setSelectCGCFlag("Y");
			LogUtil.logMessage("Inside ModelAddClauseAction :InsertClause  + Characteristic Group Clause is checked ");
			}
			else {
				objClauseVO.setSelectCGCFlag("N");
				LogUtil.logMessage("Inside ModelAddClauseAction :InsertClause  + Characteristic Group Clause is unchecked ");
			}
			// Added For CR_81 on 24-Dec-09 by  sd41630------- - Ends here-->
			/* Added for Attach Clause CR-Begin */
			/* Added for Attach Clause CR-Begin */
			//Added Condition For CR_81 Hierarchy Issue by RR68151 
			if(!(objModelAddClauseForm.isSelectCGCFlag()))	{
				
				if (objModelAddClauseForm.getLeadComponentSeqNo() != -1) {
	
					arlSelCompSeqNo.add(String.valueOf(objModelAddClauseForm
							.getLeadComponentSeqNo()));
					//Added for CR-121
					objClauseVO.setLeadComponentSeqNo(objModelAddClauseForm
							.getLeadComponentSeqNo());
					
				}
				
				if (objModelAddClauseForm.getCompGroupSeqNo() != -1) {
					objClauseVO.setLeadCompGrpSeqNo(objModelAddClauseForm
							.getCompGroupSeqNo());
				}
			}
			
			/* Added for Attach Clause CR-End */
			
			if (clauseTableArray1 == null || clauseTableArray1.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData1(null);
			} else {
				
				for (int counter = 0; counter < clauseTableArray1.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData1(objModelAddClauseForm
							.getClauseTableDataArray1());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
				
			}
			arlTableList.add(objTableDataVO);
			
			clauseTableArray2 = objModelAddClauseForm
			.getClauseTableDataArray2();
			if (clauseTableArray2 == null || clauseTableArray2.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData2(null);
			} else {
				for (int counter = 0; counter < clauseTableArray2.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData2(objModelAddClauseForm
							.getClauseTableDataArray2());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
				
			}
			arlTableList.add(objTableDataVO);
			
			clauseTableArray3 = objModelAddClauseForm
			.getClauseTableDataArray3();
			
			if (clauseTableArray3 == null || clauseTableArray3.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData3(null);
			} else {
				for (int counter = 0; counter < clauseTableArray3.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData3(objModelAddClauseForm
							.getClauseTableDataArray3());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
				
			}
			arlTableList.add(objTableDataVO);
			clauseTableArray4 = objModelAddClauseForm
			.getClauseTableDataArray4();
			
			if (clauseTableArray4 == null || clauseTableArray4.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData4(null);
			} else {
				for (int counter = 0; counter < clauseTableArray4.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData4(objModelAddClauseForm
							.getClauseTableDataArray4());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
				
			}
			arlTableList.add(objTableDataVO);
			clauseTableArray5 = objModelAddClauseForm
			.getClauseTableDataArray5();
			
			if (clauseTableArray5 == null || clauseTableArray5.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData5(null);
			} else {
				for (int counter = 0; counter < clauseTableArray5.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO.setTableArrayData5(objModelAddClauseForm
							.getClauseTableDataArray5());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
			}
			
			arlTableList.add(objTableDataVO);
			
			strClauseDesc = objModelAddClauseForm.getClauseDesc();
			strComments = objModelAddClauseForm.getComments();
			strReason = objModelAddClauseForm.getReason();
			
			if (strClauseDesc != null && !"".equals(strClauseDesc)) {
				strClauseDesc = strClauseDesc.trim();
			}
			if (strComments != null && !"".equals(strComments)) {
				strComments = strComments.trim();
			}
			if (strReason != null && !"".equals(strReason)) {
				
				strReason = strReason.trim();
			}
			objClauseVO.setModelSeqNo(((ModelAddClauseForm) objActionForm)
					.getModelSeqNo());
			
			int customerSeqNo = objModelAddClauseForm.getCustomerSeqNo();
			if (customerSeqNo != -1 || customerSeqNo != 0) {
				objClauseVO
				.setCustomerSeqNo(((ModelAddClauseForm) objActionForm)
						.getCustomerSeqNo());
			}
			objClauseVO.setSectionSeqNo(((ModelAddClauseForm) objActionForm)
					.getSectionSeqNo());
			objClauseVO.setSubSectionSeqNo(((ModelAddClauseForm) objActionForm)
					.getSubSectionSeqNo());
			objClauseVO
			.setSubSectionSeqCode(((ModelAddClauseForm) objActionForm)
					.getSubSectionSeqCode());
			int standardEquipSeqNo = objModelAddClauseForm
			.getStandardEquipmentSeqNo();
			if (standardEquipSeqNo != -1) {
				objStandardEquipmentVO
				.setStandardEquipmentSeqNo(((ModelAddClauseForm) objActionForm)
						.getStandardEquipmentSeqNo());
				arlStandardEquipList.add(objStandardEquipmentVO);
				objClauseVO.setObjStandardEquipVO(arlStandardEquipList);
			} else {
				objClauseVO.setObjStandardEquipVO(null);
			}
			
			objClauseVO.setComponentVO(arlSelCompSeqNo);
			
			objClauseVO
			.setParentClauseSeqNo(((ModelAddClauseForm) objActionForm)
					.getParentClauseSeqNo());
			objClauseVO.setClauseDesc(strClauseDesc);
			objClauseVO.setTableDataVO(arlTableList);
			
//			 Added For CR_81 on 24-Dec-09 by  SD41630 started here--------->
		
			
			 if(objModelAddClauseForm.getRefEDLNo()!=null && objModelAddClauseForm.getRefEDLNo().length>0)
			 {
				 
				 LogUtil.logMessage("Inside ModelAddClauseAction :InsertClause  +objModelAddClauseForm.getRefEDLNo() "+objModelAddClauseForm.getRefEDLNo());
			objClauseVO.setRefEDLNo(((ModelAddClauseForm) objActionForm)
					.getRefEDLNo());
			 }
			else{
				objClauseVO.setRefEDLNo(arlRefEDLNos);
				LogUtil.logMessage("Inside ModelAddClauseAction :InsertClause  +objModelAddClauseForm.getRefEDLNo() if null & length <=0 "+objClauseVO.getRefEDLNo());
				
			}
			if(objModelAddClauseForm.getEdlNo()!=null && objModelAddClauseForm.getEdlNo().length>0)
			{
				LogUtil.logMessage("Inside ModelAddClauseAction :InsertClause  +objModelAddClauseForm.getEDLNo()  "+objClauseVO.getEdlNo());
			objClauseVO.setEdlNo(((ModelAddClauseForm) objActionForm)
					.getEdlNo());
			}else{
				
				objClauseVO.setEdlNo(arlEDLNos);
				LogUtil.logMessage("Inside ModelAddClauseAction :InsertClause  +objModelAddClauseForm.getEDLNo() if null & length <=0 "+objClauseVO.getEdlNo());
			}
//			 Added For CR_81 on 24-Dec-09 by ------- - Ends here-->
			
			if (objModelAddClauseForm.getPartOfSeqNo().length != 0) {
				objClauseVO.setPartOfSeqNo(((ModelAddClauseForm) objActionForm)
						.getPartOfSeqNo());
			}
			if (objModelAddClauseForm.getPartOfCode().length != 0) {
				objClauseVO.setPartOfCode(((ModelAddClauseForm) objActionForm)
						.getPartOfCode());
			}
			/*******************************************************************
			 * Added For LSDB_CR-48 Added on 30-July-08 by ps57222
			 *  
			 ******************************************************************/
			if (objModelAddClauseForm.getPartOfclaSeqNo().length != 0) {
				objClauseVO
				.setPartOfClaSeqNo(((ModelAddClauseForm) objActionForm)
						.getPartOfclaSeqNo());
			}
			
			objClauseVO.setDwONumber(((ModelAddClauseForm) objActionForm)
					.getDwONumber());
			objClauseVO.setPartNumber(((ModelAddClauseForm) objActionForm)
					.getPartNumber());
			
			objClauseVO.setPriceBookNumber(((ModelAddClauseForm) objActionForm)
					.getPriceBookNumber());
			objClauseVO.setComments(strComments);
			objClauseVO.setReason(strReason);
			
			/*******************************************************************
			 * Added For LSDB_CR-35 Added on 04-April-08 by ps57222
			 *  
			 ******************************************************************/
			
			objClauseVO
			.setClauseSource(ApplicationConstants.CLAUSE_SOURCE_MODEL);
			
			objClauseVO.setUserID(strUserID);
			ModelVo objModelMaintVO = new ModelVo();
			objModelMaintVO.setUserID(strUserID);
			
			CustomerVO objCustomerVO = new CustomerVO();
			objCustomerVO.setUserID(strUserID);
			
			StandardEquipVO objStandardEquipVO = new StandardEquipVO();
			objStandardEquipVO.setUserID(strUserID);
			
			//Added for LSDB_CR_46
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			arlSpec = fetchSpecType(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpec);
			
			LogUtil.logMessage("objModelAddClauseForm.getSpecTypeNo() :"
					+ objModelAddClauseForm.getSpecTypeNo());
			objModelMaintVO.setSpecTypeSeqNo(objModelAddClauseForm
					.getSpecTypeNo());
			//Ends
			
			arlModelList = fetchModel(objModelMaintVO);
			arlCustomerList = fetchCustomer(objCustomerVO);
			arlStandardEquipList = fetchStandardEquipment(objStandardEquipVO);
			objModelAddClauseForm.setSpecTypeList(arlSpec);
			objModelAddClauseForm.setModelList(arlModelList);
			objModelAddClauseForm.setCustomerList(arlCustomerList);
			
			objModelAddClauseForm
			.setStandardEquipmentList(arlStandardEquipList);
			
			//Added to retain values in drop down after inserting clause
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			objSecMaintVO.setModelSeqNo(((ModelAddClauseForm) objActionForm)
					.getModelSeqNo());
			objSecMaintVO.setUserID(objLoginVo.getUserID());
			arlSectionList = objSectionBO.fetchSections(objSecMaintVO);
			if (arlSectionList.size() != 0) {
				objModelAddClauseForm.setSectionList(arlSectionList);
			}
			
			//Added to retain values in drop down after inserting clause
			SubSectionVO objSubSectionVO = new SubSectionVO();
			ModelSubSectionBI objModelSubSecBO = ServiceFactory
			.getSubSecMaintBO();
			objSubSectionVO.setModelSeqNo(((ModelAddClauseForm) objActionForm)
					.getModelSeqNo());
			objSubSectionVO.setSecSeqNo(((ModelAddClauseForm) objActionForm)
					.getSectionSeqNo());
			objSubSectionVO.setUserID(objLoginVo.getUserID());
			arlSubSectionList = objModelSubSecBO
			.fetchSubSections(objSubSectionVO);
			if (arlSubSectionList.size() != 0) {
				objModelAddClauseForm.setSubSectionList(arlSubSectionList);
			}
			
			//Added For CR_85 set the value in the Vo for add clause to  combination of components  
			
			objClauseVO.setLinkClaSeqNo(objModelAddClauseForm.getHdnCharClaSeqNo());
			//CR 88
			/**
			 * For Convert String values of 'Combination Sequence no' in array formart using StringTokenizer
			 */
			//objClauseVO.setCombntnSeqNo(objModelAddClauseForm.getHdnCombntnSeqNo());
			//String xx=objModelAddClauseForm.getHdnCombntnSeqNo()+"";
			ArrayList arlCombntnSeqNo = new ArrayList();			
			int arrCombSeqNo1[]=null;
			
			StringTokenizer stCompSeqNo1 = null;
			String s=null;
			ArrayList intCombntnSeqNoList = new ArrayList();
			//if ((objModelAddClauseForm.getHdnCombntnSeqNoArr() != null)) {
			if ((objModelAddClauseForm.getHdnCombntnSeqNoArr().length()!=0)) {
				
				stCompSeqNo1 = new StringTokenizer(objModelAddClauseForm
						.getHdnCombntnSeqNoArr(), "~");
				
				int counter = 0;
				arrCombSeqNo1 = new int[stCompSeqNo1.countTokens()];
				while (stCompSeqNo1.hasMoreTokens()) {
					s=stCompSeqNo1.nextToken();
					arrCombSeqNo1[counter]=Integer.parseInt(s);
					intCombntnSeqNoList.add(s);
					counter++;
				}
			objClauseVO.setCombntnSeqNoVO(arrCombSeqNo1);
			objClauseVO.setSelectCGCFlag("C");
			objModelAddClauseForm.setCharCombnList(intCombntnSeqNoList);
			
			}else{					
			
		    //arrCombSeqNo1 = new int[0];
				arrCombSeqNo1 = null;
			objClauseVO.setCombntnSeqNoVO(arrCombSeqNo1);
			}
			//Added for CR-121
			objClauseVO.setSubClaExistsFlag(objModelAddClauseForm.getLeadSubClaExistsFlag());
			objClauseVO.setHdnClauseSeqNo(objModelAddClauseForm
					.getHdnClauseSeqNo());
			//Added for CR-121 ends here
			ModelClauseBI lModelClauseBO = ServiceFactory.getModelClauseBO();
			intSuccess = lModelClauseBO.insertClause(objClauseVO);
			
			//Added For CR_83 for merging of Add and Modify Clause Screens
			objSubSectionVO.setSubSecSeqNo(objModelAddClauseForm.getSubSectionSeqNo());
			arlClaList = fetchClauseDesc(objSubSectionVO);
			objModelAddClauseForm.setComponentVO(arlClaList);
			//Added For CR_83 for merging of Add and Modify Clause Screens - Ends here
			
			//Added For fixing Component Group Load during CR_84 testing
			ComponentVO objComponentVO = new ComponentVO();
			
			objComponentVO.setUserID(objLoginVo.getUserID());
			objComponentVO.setSubSectionSeqNo(objModelAddClauseForm
					.getSubSectionSeqNo());
			objComponentVO.setModelSeqNo(objModelAddClauseForm.getModelSeqNo());

			ModelCompBI objModelCompBO = ServiceFactory.getModelCompBO();
			ArrayList arlCompGroupList = objModelCompBO.fetchModelComps(objComponentVO);
			
			if (arlCompGroupList != null && arlCompGroupList.size() > 0) {
				objModelAddClauseForm.setCompGroupList(arlCompGroupList);
			}
			//Added For CR_97 on 15 march 2011 by sd41630 lines starts here
			objComponentVO.setComponentGroupSeqNo(objModelAddClauseForm.getCompGroupSeqNo());
			leadComponentSeqNo=objModelAddClauseForm.getLeadComponentSeqNo();
			objComponentVO.setComponentSeqNo(leadComponentSeqNo);
			arlLeadCompClaList = objModelCompBO.viewLeadComponentClausesByModel(objComponentVO);
			objModelAddClauseForm.setLeadComponentVO(arlLeadCompClaList);
			objModelAddClauseForm.setLeadComponentSeqNo(leadComponentSeqNo);
			//CR_97 lines ends here
			if (intSuccess == 0) {
				objClauseVO = new ClauseVO();
				objModelAddClauseForm.setClauseDesc("");
				objModelAddClauseForm.setComments("");
				objModelAddClauseForm.setReason("");
				//objModelAddClauseForm.setComponentVO(null); Modified for CR_83
				objModelAddClauseForm.setComponentSeqNo("");
				//Added to retain values in drop down after inserting clause
				objModelAddClauseForm
				.setModelSeqNo(((ModelAddClauseForm) objActionForm)
						.getModelSeqNo());
				objModelAddClauseForm
				.setSectionSeqNo(((ModelAddClauseForm) objActionForm)
						.getSectionSeqNo());
				objModelAddClauseForm
				.setSubSectionSeqNo(((ModelAddClauseForm) objActionForm)
						.getSubSectionSeqNo());
				objModelAddClauseForm.setCustomerSeqNo(-1);
				objModelAddClauseForm.setStandardEquipmentSeqNo(-1);
				objModelAddClauseForm.setParentClauseSeqNo(0);
				objModelAddClauseForm.setDwONumber(0);
				objHttpServletRequest.setAttribute(
						ApplicationConstants.MODEL_SEQ_NO, new Integer(
								((ModelAddClauseForm) objActionForm)
								.getModelSeqNo()));
				objHttpServletRequest.setAttribute(
						ApplicationConstants.CUSTOMER_SEQ_NO, new Integer(
								((ModelAddClauseForm) objActionForm)
								.getCustomerSeqNo()));
				objHttpServletRequest.setAttribute(
						ApplicationConstants.SECTION_SEQ_NO, new Integer(
								((ModelAddClauseForm) objActionForm)
								.getSectionSeqNo()));
				objHttpServletRequest.setAttribute(
						ApplicationConstants.SUB_SECTION_SEQ_NO, new Integer(
								((ModelAddClauseForm) objActionForm)
								.getSubSectionSeqNo()));
				objHttpServletRequest.setAttribute(
						ApplicationConstants.STANDARD_EQUIPMENT_SEQ_NO,
						new Integer(((ModelAddClauseForm) objActionForm)
								.getStandardEquipmentSeqNo()));
				
				objModelAddClauseForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				//CR 88
				/**
				 * Setting the values for null after inserted the combination sequence number
				 */
				objModelAddClauseForm.setHdnCombntnSeqNoArr("");
				
			}
			if (intSuccess > 0) {
				objModelAddClauseForm.setMessageID(String.valueOf(intSuccess));
				//Added to retain values in drop down after inserting clause
				objModelAddClauseForm
				.setModelSeqNo(((ModelAddClauseForm) objActionForm)
						.getModelSeqNo());
				objModelAddClauseForm
				.setSectionSeqNo(((ModelAddClauseForm) objActionForm)
						.getSectionSeqNo());
				objModelAddClauseForm
				.setSubSectionSeqNo(((ModelAddClauseForm) objActionForm)
						.getSubSectionSeqNo());
			}
			//Added For CR_83 to merge clause Screens
			LogUtil.logMessage("ModifyClauseFlag :" + objModelAddClauseForm.getModifyClauseFlag());
			objModelAddClauseForm.setModifyClauseFlag(ApplicationConstants.NO);
			
		}
		
		catch (Exception objEx) {
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***************************************************************************
	 * This method is for loading details of Parts.
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
	 * ApplicationException {
	 * 
	 * LogUtil.logMessage("Inside ModelAddClauseAction :addComponent ");
	 * 
	 * String strForward = "addComponent"; try {
	 * 
	 * HttpSession objSession = objHttpServletRequest.getSession(false);
	 * 
	 * CompSearchForm objCompSearchForm = (CompSearchForm )objActionForm; int
	 * intmodelSeqNo=0; intmodelSeqNo =
	 * Integer.parseInt(objHttpServletRequest.getParameter("selectedModelID"));
	 * objCompSearchForm.setModelSeqNo(intmodelSeqNo);
	 * objCompSearchForm.setModelSeqNo(objCompSearchForm.getModelSeqNo());
	 * 
	 * }catch (Exception objEx) { strForward = ApplicationConstants.FAILURE;
	 * ErrorInfo objErrorInfo = new ErrorInfo();
	 * objErrorInfo.setMessage(objEx.getMessage() + "");
	 * LogUtil.logError(objErrorInfo); }
	 * 
	 * LogUtil.logMessage("Inside the ModelAddClauseAction : Add Component");
	 * return objActionMapping.findForward("strForward"); }
	 */
	
	/** ********* Attach To Clause CR Starts Here ******************* */
	
	public ActionForward loadComponentGroup(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		LogUtil.logMessage("Inside ModelAddClauseAction :loadComponentGroup");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ModelAddClauseForm objModelAddClauseForm = (ModelAddClauseForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
//		Added for CR-113 for clause in Ordersreport
		String strScreenID= Integer.toString(objLoginVo.getIntScreenId());
		if (strScreenID.equalsIgnoreCase("46")) {
			strForwardKey = ApplicationConstants.CLA_IN_ORDERS_REPORT;
		}
//		Added for CR-113 for clause in Ordersreport ends here
		ArrayList arlCompGroupList = new ArrayList();
		ArrayList arlModelList = new ArrayList();
		ArrayList arlCustomerList = new ArrayList();
		ArrayList arlStandardEquipList = new ArrayList();
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlSubSectionList = new ArrayList();
		ArrayList arlClaList = new ArrayList(); //Added For CR_83
		//Cr_83 lines are started here
			
		String strSpecTypeNo = null;
		String strModleSeqNo = null;
		String strSectionSeqNo = null;
		String strSubSectionSeqNo = null;
		//CR_81 lines are started here
		strSpecTypeNo = (String) objHttpServletRequest
		.getParameter("specTypeNo");
		strModleSeqNo = (String) objHttpServletRequest
		.getParameter("modelSeqNo");
		strSectionSeqNo = (String) objHttpServletRequest
		.getParameter("sectionSeqNo");
		strSubSectionSeqNo = (String) objHttpServletRequest
		.getParameter("subSectionSeqNo");
		
		LogUtil.logMessage("mODELSeqNo :" + strModleSeqNo);
//Cr_83 lines are ends here
		ComponentVO objComponentVO = new ComponentVO();
		
		objComponentVO.setUserID(objLoginVo.getUserID());
		objComponentVO.setSubSectionSeqNo(objModelAddClauseForm
				.getSubSectionSeqNo());
		objComponentVO.setModelSeqNo(objModelAddClauseForm.getModelSeqNo());
		ArrayList arlSpec = null;
		
		try {
			ModelCompBI objModelCompBO = ServiceFactory.getModelCompBO();
			arlCompGroupList = objModelCompBO.fetchModelComps(objComponentVO);
			
			if (arlCompGroupList != null && arlCompGroupList.size() > 0) {
				objModelAddClauseForm.setCompGroupList(arlCompGroupList);
			}
			
			ModelVo objModelVO = new ModelVo();
			objModelVO.setUserID(objLoginVo.getUserID());
			
			CustomerVO objCustomerVO = new CustomerVO();
			objCustomerVO.setUserID(objLoginVo.getUserID());
			
			StandardEquipVO objStandardEquipVO = new StandardEquipVO();
			objStandardEquipVO.setUserID(objLoginVo.getUserID());
			
			SectionVO objSectionVO = new SectionVO();
			objSectionVO.setUserID(objLoginVo.getUserID());
			objSectionVO.setModelSeqNo(objModelAddClauseForm.getModelSeqNo());
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			arlSectionList = objSectionBO.fetchSections(objSectionVO);
			objModelAddClauseForm.setSectionList(arlSectionList);
			
			SubSectionVO objSubSectionVO = new SubSectionVO();
			objSubSectionVO
			.setModelSeqNo(objModelAddClauseForm.getModelSeqNo());
			objSubSectionVO
			.setSecSeqNo(objModelAddClauseForm.getSectionSeqNo());
			objSubSectionVO.setUserID(objLoginVo.getUserID());
			
			
			ModelSubSectionBI objModelSubSecBO = ServiceFactory
			.getSubSecMaintBO();
			arlSubSectionList = objModelSubSecBO
			.fetchSubSections(objSubSectionVO);
			objModelAddClauseForm.setSubSectionList(arlSubSectionList);
			
			//Added for LSDB_CR_46
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			arlSpec = fetchSpecType(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpec);
			
			LogUtil.logMessage("objModelAddClauseForm.getSpecTypeNo() :"
					+ objModelAddClauseForm.getSpecTypeNo());
			objModelVO.setSpecTypeSeqNo(objModelAddClauseForm.getSpecTypeNo());
			//Ends
			
			arlModelList = fetchModel(objModelVO);
			arlCustomerList = fetchCustomer(objCustomerVO);
			//Added For CR_83 for merging of Add and Modify Clause Screens
			objSubSectionVO.setSubSecSeqNo(objModelAddClauseForm.getSubSectionSeqNo());
			arlClaList = fetchClauseDesc(objSubSectionVO);
			objModelAddClauseForm.setComponentVO(arlClaList);
			//Added For CR_83 for merging of Add and Modify Clause Screens - Ends here
			
			arlStandardEquipList = fetchStandardEquipment(objStandardEquipVO);
			objModelAddClauseForm.setSpecTypeList(arlSpec);
			LogUtil.logMessage("In Model List");
			objModelAddClauseForm.setModelList(arlModelList);
			LogUtil.logMessage("In Customer List");
			objModelAddClauseForm.setCustomerList(arlCustomerList);
			LogUtil.logMessage("In StandardEquipList List");
			objModelAddClauseForm
			.setStandardEquipmentList(arlStandardEquipList);
			objModelAddClauseForm.setParentClauseSeqNo(0);
			objModelAddClauseForm.setStandardEquipmentSeqNo(0);			
			//Added For CR_83 to merge clause Screens
			LogUtil.logMessage("ModifyClauseFlag :" + objModelAddClauseForm.getModifyClauseFlag());
			//Modified flags for CR_121 to load Add Clause properly
			objModelAddClauseForm.setModifyClauseFlag(ApplicationConstants.NO);
			//CR_83 lines are started here 
			
			//Added for CR_88
			objModelAddClauseForm.setChildFlag(ApplicationConstants.YES);
			
			//CR_83 lines are started here 
			objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);
			objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
			objSession.setAttribute("SECTION_SEQ_NO", strSectionSeqNo);
			objSession.setAttribute("SUB_SECTION_SEQ_NO", strSubSectionSeqNo);
			//CR_83 lines are ends here 
			
		} catch (Exception objEx) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForwardKey);
	}
	
	public ActionForward loadComponent(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		LogUtil.logMessage("Inside ModelAddClauseAction :loadComponent");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ModelAddClauseForm objModelAddClauseForm = (ModelAddClauseForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		ArrayList arlCompGroupList = new ArrayList();
		ArrayList arlModelList = new ArrayList();
		ArrayList arlCustomerList = new ArrayList();
		ArrayList arlStandardEquipList = new ArrayList();
		ArrayList arlSectionList = new ArrayList();
		ArrayList arlSubSectionList = new ArrayList();
		ArrayList arlClaList = new ArrayList(); 
		ArrayList arlSpec = null;
		ArrayList arlLeadCompClaList =null;
		String strLeadCompFlag="N";
		
		int leadComponentSeqNo=-1;
		try {
			if((String) objHttpServletRequest
					.getParameter("LeadCompFlag")!=null && (String) objHttpServletRequest
					.getParameter("LeadCompFlag")!=""){
			strLeadCompFlag = (String) objHttpServletRequest
			.getParameter("LeadCompFlag");}
			
		ComponentVO objComponentVO = new ComponentVO();
		
		objComponentVO.setUserID(objLoginVo.getUserID());
		objComponentVO.setModelSeqNo(objModelAddClauseForm.getModelSeqNo());
		objComponentVO.setSubSectionSeqNo(objModelAddClauseForm
				.getSubSectionSeqNo());
		
		ModelCompBI objModelCompBO = ServiceFactory.getModelCompBO();
		arlCompGroupList = objModelCompBO.fetchModelComps(objComponentVO);
		
		if (arlCompGroupList != null && arlCompGroupList.size() > 0) {
			objModelAddClauseForm.setCompGroupList(arlCompGroupList);
		}
		
		if(strLeadCompFlag.equalsIgnoreCase("Y")){
		objComponentVO.setComponentGroupSeqNo(objModelAddClauseForm.getCompGroupSeqNo());
		leadComponentSeqNo=objModelAddClauseForm.getLeadComponentSeqNo();
		objComponentVO.setComponentSeqNo(leadComponentSeqNo);
		
		}
		
		
		
			
			ModelVo objModelVO = new ModelVo();
			objModelVO.setUserID(objLoginVo.getUserID());
			
			CustomerVO objCustomerVO = new CustomerVO();
			objCustomerVO.setUserID(objLoginVo.getUserID());
			
			StandardEquipVO objStandardEquipVO = new StandardEquipVO();
			objStandardEquipVO.setUserID(objLoginVo.getUserID());
			
			SectionVO objSectionVO = new SectionVO();
			objSectionVO.setUserID(objLoginVo.getUserID());
			objSectionVO.setModelSeqNo(objModelAddClauseForm.getModelSeqNo());
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			arlSectionList = objSectionBO.fetchSections(objSectionVO);
			objModelAddClauseForm.setSectionList(arlSectionList);
			
			SubSectionVO objSubSectionVO = new SubSectionVO();
			objSubSectionVO
			.setModelSeqNo(objModelAddClauseForm.getModelSeqNo());
			objSubSectionVO
			.setSecSeqNo(objModelAddClauseForm.getSectionSeqNo());
			objSubSectionVO.setUserID(objLoginVo.getUserID());
			
			ModelSubSectionBI objModelSubSecBO = ServiceFactory
			.getSubSecMaintBO();
			arlSubSectionList = objModelSubSecBO
			.fetchSubSections(objSubSectionVO);
			objModelAddClauseForm.setSubSectionList(arlSubSectionList);
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			arlSpec = fetchSpecType(objSpecTypeVo);
			objModelVO.setSpecTypeSeqNo(objModelAddClauseForm.getSpecTypeNo());
					
			arlModelList = fetchModel(objModelVO);
			arlCustomerList = fetchCustomer(objCustomerVO);
			//Added For CR_83 for merging of Add and Modify Clause Screens
			objSubSectionVO.setSubSecSeqNo(objModelAddClauseForm.getSubSectionSeqNo());
			arlClaList = fetchClauseDesc(objSubSectionVO);
			objModelAddClauseForm.setComponentVO(arlClaList);
			//Added For CR_83 for merging of Add and Modify Clause Screens - Ends here
			arlStandardEquipList = fetchStandardEquipment(objStandardEquipVO);
//			Added For CR_97 on 15 march 2011 by SD 41630 Start here 
			if(strLeadCompFlag.equalsIgnoreCase("Y")){
			
			arlLeadCompClaList = objModelCompBO.viewLeadComponentClausesByModel(objComponentVO);
			objModelAddClauseForm.setLeadComponentVO(arlLeadCompClaList);
			objModelAddClauseForm.setLeadComponentSeqNo(leadComponentSeqNo);
			
			}
//			CR_97 code Lines ends here 
			objModelAddClauseForm.setSpecTypeList(arlSpec);
			objModelAddClauseForm.setModelList(arlModelList);
			objModelAddClauseForm.setCustomerList(arlCustomerList);
			objModelAddClauseForm
			.setStandardEquipmentList(arlStandardEquipList);
			
			//Added For CR_83 to merge clause Screens
			objModelAddClauseForm.setModifyClauseFlag(ApplicationConstants.NO);
		} catch (Exception objEx) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	//Added For CR_83 for merging of Add and Modify Clause Screens
	/***************************************************************************
	 * This method is for loading Clause Descriptions List.
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSubSectionVO
	 * @return ArrayList
	 * @throws BusinessException
	 **************************************************************************/
	private ArrayList fetchClauseDesc(SubSectionVO objSubSectionVO) throws Exception {
		ArrayList arlClaList = new ArrayList();
		
		ViewSpecByModelBI objViewSpecByModelBO = ServiceFactory.getViewSpecByModelBO();
		arlClaList = (objViewSpecByModelBO.viewMasterSpecByModel(objSubSectionVO));
		
		return arlClaList;
	
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
		.logMessage("Enters into ModelAddClauseAction.fetchClauseVersions");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList arlClauseList = new ArrayList();
		ArrayList arlAllVersion = new ArrayList();
		ArrayList arlDefaultVersion = new ArrayList();
		ArrayList arlClaList = new ArrayList();
		
		ModelAddClauseForm objModelAddClauseForm = (ModelAddClauseForm) objActionForm;

		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		LogUtil.logMessage(objLoginVo);
//		Added for CR-113 for clause in Ordersreport
		String strScreenID= Integer.toString(objLoginVo.getIntScreenId());
		if (strScreenID.equalsIgnoreCase("46")) {
			strForwardKey = ApplicationConstants.CLA_IN_ORDERS_REPORT;
		}
//		Added for CR-113 for clause in Ordersreport ends here
		
		ArrayList arlSpec = null;
//		CR_81 lines are started here
		ArrayList arlModelList = null;
		ArrayList arlSectionList = null;
		ArrayList arlSubSecList = null;
		int specTypeNo = -1;
		int modleSeqNo = -1;
		int sectionSeqNo = -1;
		int subSectionSeqNo = -1;
		
		String strSpecTypeNo = null;
		String strModleSeqNo = null;
		String strSectionSeqNo = null;
		String strSubSectionSeqNo = null;
		//CR_81 lines are started here
		strSpecTypeNo = (String) objHttpServletRequest
		.getParameter("specTypeNo");
		strModleSeqNo = (String) objHttpServletRequest
				.getParameter("modelSeqNo");
		strSectionSeqNo = (String) objHttpServletRequest
				.getParameter("sectionSeqNo");
		strSubSectionSeqNo = (String) objHttpServletRequest
				.getParameter("subSectionSeqNo");
		try {
		
		if (strSpecTypeNo != null) {
			LogUtil
					.logMessage(" Ends From request***********************************111");
			objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);
		
			specTypeNo = Integer.parseInt(strSpecTypeNo);
			LogUtil.logMessage("value of strSpecTypeNo from  session"
					+ strSpecTypeNo);
			objModelAddClauseForm.setSpecTypeNo(specTypeNo);
		
			LogUtil
					.logMessage(" Ends From request***********************************2222");
		} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
			if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {
		
				LogUtil
						.logMessage(" Ends From request***********************************3333");
				strSpecTypeNo = (String) objSession
						.getAttribute("SPEC_TYPE_NO");
				specTypeNo = Integer.parseInt(strSpecTypeNo);
				objModelAddClauseForm.setSpecTypeNo(specTypeNo);
				LogUtil
						.logMessage(" Ends From request***********************************444");
		
			}
		}
		
		if (strModleSeqNo != null) {
		
			objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
			modleSeqNo = Integer.parseInt(strModleSeqNo);
			LogUtil.logMessage("value of MODEL_SEQ_NO from session "
					+ modleSeqNo);
			objModelAddClauseForm.setModelSeqNo(modleSeqNo);
		
		} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {
		
			if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
				strModleSeqNo = (String) objSession
						.getAttribute("MODEL_SEQ_NO");
				modleSeqNo = Integer.parseInt(strModleSeqNo);
				objModelAddClauseForm.setModelSeqNo(modleSeqNo);
			}
		}
		
		if (strSectionSeqNo != null) {
		
			objSession.setAttribute("SECTION_SEQ_NO", strSectionSeqNo);
			sectionSeqNo = Integer.parseInt(strSectionSeqNo);
			LogUtil.logMessage("value of SECTION_SEQ_NO from session "
					+ strSectionSeqNo);
		
			objModelAddClauseForm.setSectionSeqNo(sectionSeqNo);
		} else if (objSession.getAttribute("SECTION_SEQ_NO") != null) {
			if (strSectionSeqNo == null || "".equals(strSectionSeqNo)) {
				strSectionSeqNo = (String) objSession
						.getAttribute("SECTION_SEQ_NO");
				sectionSeqNo = Integer.parseInt(strSectionSeqNo);
				objModelAddClauseForm.setSectionSeqNo(sectionSeqNo);
		
			}
		}
		
		if (strSubSectionSeqNo != null) {
			objSession.setAttribute("SUB_SECTION_SEQ_NO", strSubSectionSeqNo);
			subSectionSeqNo = Integer.parseInt(strSubSectionSeqNo);
			objModelAddClauseForm.setSubSectionSeqNo(subSectionSeqNo);
			LogUtil.logMessage("value of SECTION_SEQ_NO from session "
					+ subSectionSeqNo);
		} else if (objSession.getAttribute("SUB_SECTION_SEQ_NO") != null) {
			if (strSubSectionSeqNo == null || "".equals(strSubSectionSeqNo)) {
				strSubSectionSeqNo = (String) objSession
						.getAttribute("SUB_SECTION_SEQ_NO");
				objSession.setAttribute("SUB_SECTION_SEQ_NO",
						strSubSectionSeqNo);
				subSectionSeqNo = Integer.parseInt(strSubSectionSeqNo);
				objModelAddClauseForm.setSubSectionSeqNo(subSectionSeqNo);
				LogUtil
						.logMessage("value of SUB_SECTION_SEQ_NO from session if SUB_SECTION_SEQ_NO is not null "
								+ strSubSectionSeqNo);
			}
		
		}
		
		specTypeNo = objModelAddClauseForm.getSpecTypeNo();
		modleSeqNo = objModelAddClauseForm.getModelSeqNo();
		sectionSeqNo = objModelAddClauseForm.getSectionSeqNo();
		subSectionSeqNo = objModelAddClauseForm.getSubSectionSeqNo();


			//Added for CR_93 to bring about Modify Clause Screen
			int intClaSeqNo = 0;
			if ((String) objHttpServletRequest
					.getParameter("claSeqNo") != null)
			intClaSeqNo = Integer.parseInt((String) objHttpServletRequest
					.getParameter("claSeqNo"));
			//Added For CR_93 - Ends 
			
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			//ArrayList arlModelList = null;
			//ArrayList arlSectionList = null;
			ArrayList arlSubSectionList = null;
			
			//Added for LSDB_CR_46
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			arlSpec = fetchSpecType(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpec);
			LogUtil.logMessage("objModelAddClauseForm.getSpecTypeNo() :"
					+ objModelAddClauseForm.getSpecTypeNo());
			objModelVo.setSpecTypeSeqNo(objModelAddClauseForm
					.getSpecTypeNo());
			
			objModelAddClauseForm.setSpecTypeList(arlSpec);
			
			ModelBI objModelBo = ServiceFactory.getModelBO();
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null) {
				objModelAddClauseForm.setModelList(arlModelList);
			}
			
			SectionVO objSectionVO = new SectionVO();
			objSectionVO.setUserID(objLoginVo.getUserID());
			objSectionVO.setModelSeqNo(objModelAddClauseForm.getModelSeqNo());
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			arlSectionList = objSectionBO.fetchSections(objSectionVO);
			objModelAddClauseForm.setSectionList(arlSectionList);
			
			SubSectionVO objSubSectionVO = new SubSectionVO();
			objSubSectionVO
			.setModelSeqNo(objModelAddClauseForm.getModelSeqNo());
			objSubSectionVO
			.setSecSeqNo(objModelAddClauseForm.getSectionSeqNo());
			objSubSectionVO.setUserID(objLoginVo.getUserID());
			
			
			ModelSubSectionBI objModelSubSecBO = ServiceFactory
			.getSubSecMaintBO();
			arlSubSectionList = objModelSubSecBO
			.fetchSubSections(objSubSectionVO);
			objModelAddClauseForm.setSubSectionList(arlSubSectionList);
			
			
			objSubSectionVO.setSubSecSeqNo(objModelAddClauseForm.getSubSectionSeqNo());
			arlClaList = fetchClauseDesc(objSubSectionVO);
			objModelAddClauseForm.setComponentVO(arlClaList);
			
			ClauseVO objClauseVO = new ClauseVO();
			LogUtil.logMessage("ModelSeqNo in FetchClauseVersion"
					+ objModelAddClauseForm.getModelSeqNo());
			objClauseVO.setModelSeqNo(objModelAddClauseForm
					.getModelSeqNo());
			LogUtil.logMessage("SubSectionSeqNo in FetchClauseVersion"
					+ objModelAddClauseForm.getSubSectionSeqNo());
			objClauseVO.setSubSectionSeqNo(objModelAddClauseForm
					.getSubSectionSeqNo());
			LogUtil.logMessage("ClauseSeqNo in FetchClauseVersion"
					+ objModelAddClauseForm.getHdnClauseSeqNo());
			//Added condition for CR_93 to bring Modify Clause Screen from COmponent Map Screen
			if (intClaSeqNo != 0)	{
				objModelAddClauseForm.setHdnClauseSeqNo(intClaSeqNo);
				objModelAddClauseForm.setClauseSeqNo(intClaSeqNo);
			}
			
			objClauseVO.setClauseSeqNo(objModelAddClauseForm
					.getHdnClauseSeqNo());
			
			objClauseVO.setUserID(objLoginVo.getUserID());
			
			ModelSelectClauseRevBI objModelSelectClauseRevBO = ServiceFactory
			.getModelSelectClauseRevBO();
			arlClauseList = objModelSelectClauseRevBO
			.fetchClauseVersions(objClauseVO);
			
			if (arlClauseList != null && arlClauseList.size() > 0) {
				
				arlAllVersion = (ArrayList) arlClauseList.get(0);
				arlDefaultVersion = (ArrayList) arlClauseList.get(1);
				objModelAddClauseForm.setClauseVersions(arlAllVersion);
				objModelAddClauseForm.setVersionNo(0);
				objModelAddClauseForm.setClauseList(arlDefaultVersion);
				objModelAddClauseForm.setHdnClauseSeqNo(objClauseVO
						.getClauseSeqNo());
				LogUtil.logMessage("After Loop ClauseSeqNo in FetchClauseVersion"
						+ objModelAddClauseForm.getHdnClauseSeqNo());
			}
			
			if (arlClauseList.size() == 0) {
				
				objModelAddClauseForm.setMethod("ClauseVersions");
				
			}
			StandardEquipVO objStandardEquipVO = new StandardEquipVO();
			objStandardEquipVO.setUserID(objLoginVo.getUserID());
			ArrayList arlStandardEquipList = new ArrayList();
			arlStandardEquipList = fetchStandardEquipment(objStandardEquipVO);
			objModelAddClauseForm.setStandardEquipmentList(arlStandardEquipList);
					
			objModelAddClauseForm.setModifyClauseFlag(ApplicationConstants.YES);
			//Added for CR_88 on 28Jun10 by Sd41630 
			objModelAddClauseForm.setChildFlag(ApplicationConstants.YES);
	}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelAddClauseAction..");
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
		.logMessage("Entering ModelAddClauseAction.UpdateApplyDefault");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intUpdate = 0;
		ArrayList arlClauseList = null;
		ModelAddClauseForm objModelAddClauseForm = (ModelAddClauseForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		LogUtil.logMessage(objLoginVo);
		ArrayList arlSpec = null;
		ArrayList arlClaList = null;
		
		try {
			
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			ArrayList arlModelList = null;
			ArrayList arlSectionList = null;
			ArrayList arlSubSectionList = null;
			
			//Added for LSDB_CR_46
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			arlSpec = fetchSpecType(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpec);
			LogUtil.logMessage("objModelAddClauseForm.getSpecTypeNo() :"
					+ objModelAddClauseForm.getSpecTypeNo());
			objModelVo.setSpecTypeSeqNo(objModelAddClauseForm
					.getSpecTypeNo());
			
			objModelAddClauseForm.setSpecTypeList(arlSpec);
			
			ModelBI objModelBo = ServiceFactory.getModelBO();
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null) {
				objModelAddClauseForm.setModelList(arlModelList);
			}
			
			SectionVO objSectionVO = new SectionVO();
			objSectionVO.setUserID(objLoginVo.getUserID());
			objSectionVO.setModelSeqNo(objModelAddClauseForm.getModelSeqNo());
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			arlSectionList = objSectionBO.fetchSections(objSectionVO);
			objModelAddClauseForm.setSectionList(arlSectionList);
			
			SubSectionVO objSubSectionVO = new SubSectionVO();
			objSubSectionVO
			.setModelSeqNo(objModelAddClauseForm.getModelSeqNo());
			objSubSectionVO
			.setSecSeqNo(objModelAddClauseForm.getSectionSeqNo());
			objSubSectionVO.setUserID(objLoginVo.getUserID());
			
			
			ModelSubSectionBI objModelSubSecBO = ServiceFactory
			.getSubSecMaintBO();
			arlSubSectionList = objModelSubSecBO
			.fetchSubSections(objSubSectionVO);
			objModelAddClauseForm.setSubSectionList(arlSubSectionList);
			
			ClauseVO objClauseVO = new ClauseVO();
			LogUtil.logMessage("ModelSeqNo in FetchClauseVersion"
					+ objModelAddClauseForm.getModelSeqNo());
			objClauseVO.setModelSeqNo(objModelAddClauseForm
					.getModelSeqNo());
			LogUtil.logMessage("SubSectionSeqNo in FetchClauseVersion"
					+ objModelAddClauseForm.getSubSectionSeqNo());
			objClauseVO.setSubSectionSeqNo(objModelAddClauseForm
					.getSubSectionSeqNo());
			LogUtil.logMessage("ClauseSeqNo in FetchClauseVersion"
					+ objModelAddClauseForm.getHdnClauseSeqNo());
			objClauseVO.setClauseSeqNo(objModelAddClauseForm
					.getHdnClauseSeqNo());
			
			objClauseVO.setVersionNo(objModelAddClauseForm
					.getHdnClauseVersionNo());
			LogUtil.logMessage("versionNo in ApplyDefault action:"
					+ objModelAddClauseForm.getHdnClauseVersionNo());
			objClauseVO.setReason(objModelAddClauseForm.getReason());
			objClauseVO.setUserID(objLoginVo.getUserID());
			
			
			
			ModelSelectClauseRevBI objModelSelectClauseRevBO = ServiceFactory
			.getModelSelectClauseRevBO();
			intUpdate = objModelSelectClauseRevBO
			.updateApplyDefaultClause(objClauseVO);
						
			if (intUpdate == 0) {
				objModelAddClauseForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objModelAddClauseForm.setMessageID("" + intUpdate);
			}
			
			//Added for CR_114
			LogUtil.logMessage("Value of MapAppendixImg boolean is "+objModelAddClauseForm.getMapAppendixImg());
			if(objModelAddClauseForm.getMapAppendixImg()==1){
				LogUtil.logMessage("Appendix Image Seq NO is " +objModelAddClauseForm.getAppendixImgSeqNo());
				//objClauseVO.setAppendixImgSeqNo(objModelAddClauseForm.getAppendixImgSeqNo());
				ModelAppendixVO objModelAppendixVO = new ModelAppendixVO();
				objModelAppendixVO.setModelSeqNo(objModelAddClauseForm.getModelSeqNo());
				objModelAppendixVO.setImgSeqNo(objModelAddClauseForm.getAppendixImgSeqNo());
				objModelAppendixVO.setClauseSeqNo(objModelAddClauseForm
						.getHdnClauseSeqNo());
				objModelAppendixVO.setVersionNo(objModelAddClauseForm
						.getHdnClauseVersionNo());
				objModelAppendixVO.setUserID(objLoginVo.getUserID());
				ModelAppendixBI objModelAppendixBI = ServiceFactory
				.getModelAppendixBO();
				intUpdate = objModelAppendixBI.saveModelClaMappings(objModelAppendixVO);
				if (intUpdate == 0) {
					objModelAddClauseForm
					.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				} else {
					objModelAddClauseForm.setMessageID("" + intUpdate);
				}
				
			}
			//Added for CR_114 Ends Here
			
			objSubSectionVO.setSubSecSeqNo(objModelAddClauseForm.getSubSectionSeqNo());
			arlClaList = fetchClauseDesc(objSubSectionVO);
			objModelAddClauseForm.setComponentVO(arlClaList);
			
			//Clause Versions fetch
			ClauseVO objjClauseVO = new ClauseVO();
			
			objjClauseVO.setModelSeqNo(objModelAddClauseForm
					.getModelSeqNo());
			objjClauseVO.setSubSectionSeqNo(objModelAddClauseForm
					.getSubSectionSeqNo());
			objjClauseVO.setClauseSeqNo(objModelAddClauseForm
					.getClauseSeqNo());
			objjClauseVO.setUserID(objLoginVo.getUserID());
			ModelSelectClauseRevBI objjModelSelectClauseRevBO = ServiceFactory
			.getModelSelectClauseRevBO();
			arlClauseList = objjModelSelectClauseRevBO
			.fetchClauseVersions(objjClauseVO);
			
			if (arlClauseList != null && arlClauseList.size() > 0) {
				objModelAddClauseForm
				.setClauseVersions((ArrayList) arlClauseList.get(0));
				objModelAddClauseForm
				.setClauseList((ArrayList) arlClauseList.get(1));
				objModelAddClauseForm.setHdnClauseSeqNo(objClauseVO
						.getClauseSeqNo());
				objModelAddClauseForm.setReason("");
				objModelAddClauseForm.setHdnClauseVersionNo(0);
				objModelAddClauseForm.setVersionNo(0);
			}
			
			if (arlClauseList.size() == 0) {
				objModelAddClauseForm.setMethod("ClauseVersions");
			}
			
			objModelAddClauseForm.setModifyClauseFlag(ApplicationConstants.YES);		

			StandardEquipVO objStandardEquipVO = new StandardEquipVO();
			objStandardEquipVO.setUserID(objLoginVo.getUserID());
			ArrayList arlStandardEquipList = new ArrayList();
			arlStandardEquipList = fetchStandardEquipment(objStandardEquipVO);
			objModelAddClauseForm.setStandardEquipmentList(arlStandardEquipList);
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelAddClauseAction..");
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
	
	public ActionForward deleteClause(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil.logMessage("Entering ModelAddClauseAction.deleteClause");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intDelete = 0;
		ModelAddClauseForm objModelAddClauseForm = (ModelAddClauseForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		LogUtil.logMessage(objLoginVo);
		ArrayList arlSpec = null;
		
		try {
			
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			ArrayList arlModelList = null;
			ArrayList arlSectionList = null;
			ArrayList arlSubSectionList = null;
			ArrayList arlClaList = null;
			
			//Added for LSDB_CR_46
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			arlSpec = fetchSpecType(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpec);
			LogUtil.logMessage("objModelAddClauseForm.getSpecTypeNo() :"
					+ objModelAddClauseForm.getSpecTypeNo());
			objModelVo.setSpecTypeSeqNo(objModelAddClauseForm
					.getSpecTypeNo());
			
			objModelAddClauseForm.setSpecTypeList(arlSpec);
			
			ModelBI objModelBo = ServiceFactory.getModelBO();
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null) {
				objModelAddClauseForm.setModelList(arlModelList);
			}
			
			SectionVO objSectionVO = new SectionVO();
			objSectionVO.setUserID(objLoginVo.getUserID());
			objSectionVO.setModelSeqNo(objModelAddClauseForm.getModelSeqNo());
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			arlSectionList = objSectionBO.fetchSections(objSectionVO);
			objModelAddClauseForm.setSectionList(arlSectionList);
			
			SubSectionVO objSubSectionVO = new SubSectionVO();
			objSubSectionVO
			.setModelSeqNo(objModelAddClauseForm.getModelSeqNo());
			objSubSectionVO
			.setSecSeqNo(objModelAddClauseForm.getSectionSeqNo());
			objSubSectionVO.setUserID(objLoginVo.getUserID());
	
			ModelSubSectionBI objModelSubSecBO = ServiceFactory
			.getSubSecMaintBO();
			arlSubSectionList = objModelSubSecBO
			.fetchSubSections(objSubSectionVO);
			objModelAddClauseForm.setSubSectionList(arlSubSectionList);
			
			ClauseVO objClauseVO = new ClauseVO();
			
			objClauseVO.setClauseSeqNo(objModelAddClauseForm
					.getHdnClauseSeqNo());
			LogUtil.logMessage("CSEQNO in deleteClause action:"
					+ objModelAddClauseForm.getClauseSeqNo());
			
			objClauseVO.setUserID(objLoginVo.getUserID());
			
			ModelSelectClauseRevBI objModelSelectClauseRevBO = ServiceFactory
			.getModelSelectClauseRevBO();
			intDelete = objModelSelectClauseRevBO.deleteClause(objClauseVO);
			
			if (intDelete == 0) {
				objModelAddClauseForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				objModelAddClauseForm.setHdnClauseSeqNo(0);
				objModelAddClauseForm.setClauseDesc("");
				objModelAddClauseForm.setVersionNo(0);
			} else {
				objModelAddClauseForm.setMessageID("" + intDelete);
			}
	
			objSubSectionVO.setSubSecSeqNo(objModelAddClauseForm.getSubSectionSeqNo());
			arlClaList = fetchClauseDesc(objSubSectionVO);
			if (arlClaList != null) {
			objModelAddClauseForm.setComponentVO(arlClaList);
			}
			
			objModelAddClauseForm.setModifyClauseFlag(ApplicationConstants.YES);	
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelAddClauseAction:deleteClause..");
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
		LogUtil.logMessage("Entering ModelAddClauseAction.deleteVersion");
		String strForwardKey = ApplicationConstants.SUCCESS;
		int intDelete = 0;
		ArrayList arlClauseList = null;
		ArrayList arlAllVersion = null;
		ArrayList arlDefaultVersion = null;
		ArrayList arlClaList = null;
		ModelAddClauseForm objModelAddClauseForm = (ModelAddClauseForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		LogUtil.logMessage(objLoginVo);
		
		ArrayList arlSpec = null;
		try {
			
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			ArrayList arlModelList = null;
			ArrayList arlSectionList = null;
			ArrayList arlSubSectionList = null;
			
			//Added for LSDB_CR_46
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			arlSpec = fetchSpecType(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpec);
			LogUtil.logMessage("objModelAddClauseForm.getSpecTypeNo() :"
					+ objModelAddClauseForm.getSpecTypeNo());
			objModelVo.setSpecTypeSeqNo(objModelAddClauseForm
					.getSpecTypeNo());
			
			objModelAddClauseForm.setSpecTypeList(arlSpec);
			
			ModelBI objModelBo = ServiceFactory.getModelBO();
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null) {
				objModelAddClauseForm.setModelList(arlModelList);
			}
			
			SectionVO objSectionVO = new SectionVO();
			objSectionVO.setUserID(objLoginVo.getUserID());
			objSectionVO.setModelSeqNo(objModelAddClauseForm.getModelSeqNo());
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			arlSectionList = objSectionBO.fetchSections(objSectionVO);
			objModelAddClauseForm.setSectionList(arlSectionList);
			
			SubSectionVO objSubSectionVO = new SubSectionVO();
			objSubSectionVO
			.setModelSeqNo(objModelAddClauseForm.getModelSeqNo());
			objSubSectionVO
			.setSecSeqNo(objModelAddClauseForm.getSectionSeqNo());
			objSubSectionVO.setUserID(objLoginVo.getUserID());
			
			
			ModelSubSectionBI objModelSubSecBO = ServiceFactory
			.getSubSecMaintBO();
			arlSubSectionList = objModelSubSecBO
			.fetchSubSections(objSubSectionVO);
			objModelAddClauseForm.setSubSectionList(arlSubSectionList);
			
			ClauseVO objClauseVO = new ClauseVO();
			
			objClauseVO.setClauseSeqNo(objModelAddClauseForm
					.getHdnClauseSeqNo());
			LogUtil
			.logMessage("ClauseSeqNo in ModelAddClauseAction.deleteVersion:"
					+ objModelAddClauseForm.getHdnClauseSeqNo());
			LogUtil
			.logMessage("VersionNo in ModelAddClauseAction.deleteVersion:"
					+ objModelAddClauseForm
					.getHdnClauseVersionNo());
			objClauseVO.setVersionNo(objModelAddClauseForm
					.getHdnClauseVersionNo());
			objClauseVO.setUserID(objLoginVo.getUserID());
			
			ModelSelectClauseRevBI objModelSelectClauseRevBO = ServiceFactory
			.getModelSelectClauseRevBO();
			intDelete = objModelSelectClauseRevBO.deleteVersion(objClauseVO);
			
			if (intDelete == 0) {
				
				objModelAddClauseForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				objModelAddClauseForm.setVersionNo(0);
			}
			
			if (intDelete > 0) {
				objModelAddClauseForm.setMessageID("" + intDelete);
			}

			objSubSectionVO.setSubSecSeqNo(objModelAddClauseForm.getSubSectionSeqNo());
			arlClaList = fetchClauseDesc(objSubSectionVO);
			objModelAddClauseForm.setComponentVO(arlClaList);
			
			ClauseVO objFetchClauseVO = new ClauseVO();
			objFetchClauseVO.setClauseSeqNo(objModelAddClauseForm
					.getHdnClauseSeqNo());
			objFetchClauseVO.setModelSeqNo(objModelAddClauseForm
					.getModelSeqNo());
			objFetchClauseVO.setSubSectionSeqNo(objModelAddClauseForm
					.getSubSectionSeqNo());
			objFetchClauseVO.setUserID(objLoginVo.getUserID());
			
			arlClauseList = objModelSelectClauseRevBO
			.fetchClauseVersions(objFetchClauseVO);
			
			if (arlClauseList != null && arlClauseList.size() > 0) {
				
				arlAllVersion = (ArrayList) arlClauseList.get(0);
				arlDefaultVersion = (ArrayList) arlClauseList.get(1);
				objModelAddClauseForm.setClauseVersions(arlAllVersion);
				objModelAddClauseForm.setClauseList(arlDefaultVersion);
				objModelAddClauseForm.setHdnClauseSeqNo(objClauseVO
						.getClauseSeqNo());
				
			}
			
			if (arlClauseList.size() == 0) {
				
				objModelAddClauseForm.setMethod("ClauseVersions");
				
			}
			StandardEquipVO objStandardEquipVO = new StandardEquipVO();
			objStandardEquipVO.setUserID(objLoginVo.getUserID());
			ArrayList arlStandardEquipList = new ArrayList();
			arlStandardEquipList = fetchStandardEquipment(objStandardEquipVO);
			objModelAddClauseForm.setStandardEquipmentList(arlStandardEquipList);
					
			objModelAddClauseForm.setModifyClauseFlag(ApplicationConstants.YES);	
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelAddClauseAction:deleteVersion..");
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
	 * * * * This Method is used to add a Clause Version
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward insertClauseVersion(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException {
		
		LogUtil
		.logMessage("Enters into ModelAddClauseAction:insertClauseVersion");
		
		String strForwardKey = ApplicationConstants.SUCCESS;
		String[] arlEDLNos = { "", "", "" };
		String[] arlRefEDLNos = { "", "", "" };
				
		try {
			ModelAddClauseForm objModelAddClauseForm = (ModelAddClauseForm) objActionForm;
			HttpSession objSession = objHttpServletRequest.getSession(false);
			ClauseVO objClauseVO = new ClauseVO();
			String[] clauseTableArray1, clauseTableArray2, clauseTableArray3, clauseTableArray4, clauseTableArray5 = null;
			ClauseTableDataVO objTableDataVO = null;
			ArrayList arlTableList = new ArrayList();
			ArrayList arlModelList = new ArrayList();
			ArrayList arlCompVO = new ArrayList();
			ArrayList arlClaList = new ArrayList();
			ArrayList arlSpec = new ArrayList();
			ArrayList arlSectionList = null;
			ArrayList arlSubSectionList = null;
			
			int intStatusCode = 0;
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			objClauseVO.setUserID(objLoginVo.getUserID());
			StandardEquipVO objStandardEquipmentVO = new StandardEquipVO();
			objStandardEquipmentVO.setUserID(objLoginVo.getUserID());
			
			objClauseVO.setModelSeqNo(objModelAddClauseForm
					.getModelSeqNo());
			objClauseVO.setClauseSeqNo(objModelAddClauseForm
					.getClauseSeqNo());
			ArrayList arlStandardEquipList = new ArrayList();
			objClauseVO.setSubSectionSeqNo(objModelAddClauseForm
					.getSubSectionSeqNo());
			
			objClauseVO.setComponentVO(arlCompVO);
			objClauseVO.setClauseDesc(ApplicationUtil
					.trim(objModelAddClauseForm
							.getClauseDescForTextArea()));
			LogUtil.logMessage("ClauseDesc in ModelAddClauseAction:"
					+ objModelAddClauseForm.getClauseDescForTextArea());
			objClauseVO.setComments(ApplicationUtil
					.trim(objModelAddClauseForm.getComments()));
			LogUtil.logMessage("Comments in ModelAddClauseAction:"
					+ objModelAddClauseForm.getComments());
			objClauseVO.setReason(ApplicationUtil
					.trim(objModelAddClauseForm.getReason()));
			LogUtil.logMessage("Reason in ModelAddClauseAction:"
					+ objModelAddClauseForm.getReason());
			objClauseVO.setPartNumber(objModelAddClauseForm
					.getPartNumber());
			LogUtil.logMessage("PartNumber in ModelAddClauseAction:"
					+ objModelAddClauseForm.getPartNumber());
			objClauseVO.setPriceBookNumber(objModelAddClauseForm
					.getPriceBookNumber());
			LogUtil.logMessage("PriceBookNumber in ModelAddClauseAction:"
					+ objModelAddClauseForm.getPriceBookNumber());
			objClauseVO
			.setDwONumber(objModelAddClauseForm.getDwONumber());
			LogUtil.logMessage("DwONumber in ModelAddClauseAction:"
					+ objModelAddClauseForm.getDwONumber());
			//Added For CR_81 on 24-Dec-09 by  SD41630------- -->
			if(objModelAddClauseForm.getRefEDLNo()!=null && objModelAddClauseForm.getRefEDLNo().length>0)
				{
					
					objClauseVO.setRefEDLNo(objModelAddClauseForm.getRefEDLNo());
				}
			else
				{							
					objClauseVO.setRefEDLNo(arlRefEDLNos);
					
				}
				
			LogUtil.logMessage("RefEDLNo in ModelAddClauseAction:");
			if(objModelAddClauseForm.getEdlNo()!=null && objModelAddClauseForm.getEdlNo().length>0)
			{
			objClauseVO.setEdlNo(objModelAddClauseForm.getEdlNo());
			}
			else{
				LogUtil.logMessage("Edlno in side if null in ModelAddClauseAction: length" + arlEDLNos.length);
				objClauseVO.setEdlNo(arlEDLNos);
				
			}
			//Added For CR_81 on 24-Dec-09 by SD 41630------- - Ends here-->
			LogUtil.logMessage("EdlNo in ModelAddClauseAction:");
			objClauseVO.setPartOfCode(objModelAddClauseForm
					.getPartOfCode());
			LogUtil.logMessage("PartOfCode in ModelAddClauseAction:"
					+ objModelAddClauseForm.getPartOfCode().length);
			objClauseVO.setPartOfSeqNo(objModelAddClauseForm
					.getPartOfSeqNo());
			
			LogUtil.logMessage("PartOfSeqNo in ModelAddClauseAction:"
					+ objModelAddClauseForm.getPartOfSeqNo().length);
			
			/**
			 * PartOfClaSeqNo is added for LSDB_CR-48(Part Of CR) Added on
			 * 04-Aug-08 by ps57222
			 */
			
			objClauseVO.setPartOfClaSeqNo(objModelAddClauseForm
					.getPartOfclaSeqNo());
			
			LogUtil.logMessage("PartOfClaSeqNo in ModelAddClauseAction:"
					+ objModelAddClauseForm.getPartOfclaSeqNo().length);
			
			objClauseVO.setDefaultFlag(objModelAddClauseForm
					.getApplyToDefault());
			
			LogUtil
			.logMessage("APply as Default flag in ModelAddClauseAction:"
					+ objModelAddClauseForm.getPartOfSeqNo().length);
			
			/*******************************************************************
			 * Added For LSDB_CR-35 Added on 04-April-08 by ps57222
			 *  
			 ******************************************************************/
			
			objClauseVO
			.setClauseSource(ApplicationConstants.CLAUSE_SOURCE_MODEL);
			
			int standardEquipSeqNo = objModelAddClauseForm
			.getStandardEquipmentSeqNo();
			if (standardEquipSeqNo != -1) {
				objStandardEquipmentVO
				.setStandardEquipmentSeqNo(objModelAddClauseForm
						.getStandardEquipmentSeqNo());
				arlStandardEquipList.add(objStandardEquipmentVO);
				objClauseVO.setObjStandardEquipVO(arlStandardEquipList);
			} else {
				objClauseVO.setObjStandardEquipVO(null);
			}
			clauseTableArray1 = objModelAddClauseForm
			.getClauseTableDataArray1();
			
			if (clauseTableArray1 == null || clauseTableArray1.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData1(null);
			} else {
				
				for (int counter = 0; counter < clauseTableArray1.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO
					.setTableArrayData1(objModelAddClauseForm
							.getClauseTableDataArray1());
					LogUtil.logMessage("Table Data Value:"
							+ objModelAddClauseForm
							.getClauseTableDataArray1()[counter]);
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
				
			}
			arlTableList.add(objTableDataVO);
			
			clauseTableArray2 = objModelAddClauseForm
			.getClauseTableDataArray2();
			if (clauseTableArray2 == null || clauseTableArray2.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData2(null);
			} else {
				for (int counter = 0; counter < clauseTableArray2.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO
					.setTableArrayData2(objModelAddClauseForm
							.getClauseTableDataArray2());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
				
			}
			arlTableList.add(objTableDataVO);
			
			clauseTableArray3 = objModelAddClauseForm
			.getClauseTableDataArray3();
			
			if (clauseTableArray3 == null || clauseTableArray3.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData3(null);
			} else {
				for (int counter = 0; counter < clauseTableArray3.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO
					.setTableArrayData3(objModelAddClauseForm
							.getClauseTableDataArray3());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
				
			}
			arlTableList.add(objTableDataVO);
			clauseTableArray4 = objModelAddClauseForm
			.getClauseTableDataArray4();
			
			if (clauseTableArray4 == null || clauseTableArray4.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData4(null);
			} else {
				for (int counter = 0; counter < clauseTableArray4.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO
					.setTableArrayData4(objModelAddClauseForm
							.getClauseTableDataArray4());
					if (counter != 0) {
						objTableDataVO.setHeaderFlag('N');
					} else {
						objTableDataVO.setHeaderFlag('Y');
					}
				}
				
			}
			arlTableList.add(objTableDataVO);
			clauseTableArray5 = objModelAddClauseForm
			.getClauseTableDataArray5();
			
			if (clauseTableArray5 == null || clauseTableArray5.length == 0) {
				objTableDataVO = new ClauseTableDataVO();
				objTableDataVO.setTableArrayData5(null);
			} else {
				for (int counter = 0; counter < clauseTableArray5.length; counter++) {
					objTableDataVO = new ClauseTableDataVO();
					objTableDataVO
					.setTableArrayData5(objModelAddClauseForm
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
			
			//Added for CR_114
			LogUtil.logMessage("Value of MapAppendixImg is "+objModelAddClauseForm.getMapAppendixImg());
			if(objModelAddClauseForm.getMapAppendixImg()==1){
				objClauseVO.setMapAppendixImg(true);
				LogUtil.logMessage("Appendix Image Seq NO is " +objModelAddClauseForm.getAppendixImgSeqNo());
				objClauseVO.setAppendixImgSeqNo(objModelAddClauseForm.getAppendixImgSeqNo());
			}
			//Added for CR_114 Ends Here
			
			
			ModelClauseBI lModelClauseBO = ServiceFactory.getModelClauseBO();
			intStatusCode = lModelClauseBO.insertClause(objClauseVO);
			
			if (intStatusCode == 0) {
				objModelAddClauseForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				objModelAddClauseForm.setVersionNo(0);
			}
			else
			{
				//Adding this for CR_92 notifying user for characters entered
				objModelAddClauseForm.setMessageID("" + intStatusCode);
			}
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			
			//Added for LSDB_CR_46
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			arlSpec = fetchSpecType(objSpecTypeVo);
			LogUtil.logMessage("objArrList for Spec Types :" + arlSpec);
			LogUtil.logMessage("objModelAddClauseForm.getSpecTypeNo() :"
					+ objModelAddClauseForm.getSpecTypeNo());
			objModelVo.setSpecTypeSeqNo(objModelAddClauseForm
					.getSpecTypeNo());
			
			objModelAddClauseForm.setSpecTypeList(arlSpec);
			
			ModelBI objModelBo = ServiceFactory.getModelBO();
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null) {
				objModelAddClauseForm.setModelList(arlModelList);
			}
			
			SectionVO objSectionVO = new SectionVO();
			objSectionVO.setUserID(objLoginVo.getUserID());
			objSectionVO.setModelSeqNo(objModelAddClauseForm.getModelSeqNo());
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			arlSectionList = objSectionBO.fetchSections(objSectionVO);
			objModelAddClauseForm.setSectionList(arlSectionList);
			
			SubSectionVO objSubSectionVO = new SubSectionVO();
			objSubSectionVO
			.setModelSeqNo(objModelAddClauseForm.getModelSeqNo());
			objSubSectionVO
			.setSecSeqNo(objModelAddClauseForm.getSectionSeqNo());
			objSubSectionVO.setUserID(objLoginVo.getUserID());
			
			
			ModelSubSectionBI objModelSubSecBO = ServiceFactory
			.getSubSecMaintBO();
			arlSubSectionList = objModelSubSecBO
			.fetchSubSections(objSubSectionVO);
			objModelAddClauseForm.setSubSectionList(arlSubSectionList);

			objSubSectionVO.setSubSecSeqNo(objModelAddClauseForm.getSubSectionSeqNo());
			arlClaList = fetchClauseDesc(objSubSectionVO);
			objModelAddClauseForm.setComponentVO(arlClaList);

			ClauseVO objFetchClauseVO = new ClauseVO();
			objFetchClauseVO.setClauseSeqNo(objModelAddClauseForm
					.getHdnClauseSeqNo());
			objFetchClauseVO.setModelSeqNo(objModelAddClauseForm
					.getModelSeqNo());
			objFetchClauseVO.setSubSectionSeqNo(objModelAddClauseForm
					.getSubSectionSeqNo());
			objFetchClauseVO.setUserID(objLoginVo.getUserID());

			ModelSelectClauseRevBI objModelSelectClauseRevBO = ServiceFactory
			.getModelSelectClauseRevBO();
			ArrayList arlClauseList = objModelSelectClauseRevBO
			.fetchClauseVersions(objFetchClauseVO);
			
			if (arlClauseList != null && arlClauseList.size() > 0) {
				
				ArrayList arlAllVersion = (ArrayList) arlClauseList.get(0);
				ArrayList arlDefaultVersion = (ArrayList) arlClauseList.get(1);
				objModelAddClauseForm.setClauseVersions(arlAllVersion);
				objModelAddClauseForm.setClauseList(arlDefaultVersion);
				objModelAddClauseForm.setHdnClauseSeqNo(objClauseVO
						.getClauseSeqNo());
				
			}
			
			if (arlClauseList.size() == 0) {			
				objModelAddClauseForm.setMethod("ClauseVersions");		
			}
			
			arlStandardEquipList = fetchStandardEquipment(objStandardEquipmentVO);
			objModelAddClauseForm.setStandardEquipmentList(arlStandardEquipList);
					
			objModelAddClauseForm.setModifyClauseFlag(ApplicationConstants.YES);
			
		}
		
		catch (Exception objExp) {
						LogUtil
			.logMessage("Enters into Exception Block in ModelAddClauseAction..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
			
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	//Added for CR_85
	
	
	/***************************************************************************
	 * This Method is used to fetch the Characteristic combinationEDls 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward fetchCharCombntnEdlMap(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering ModelAddClauseAction.fetchCharCombntnMap");
		String strForwardKey = ApplicationConstants.FETCH_CHAR_COMBNTN_EDL_MAP;
		ModelAddClauseForm objModelAddClauseForm = (ModelAddClauseForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		int intClauseSeqNo=0;
		//int intCombntnSeqNo=0;
		// CR 88
		String intCombntnSeqNo;
		ArrayList charCombnList = new ArrayList();
	//	List wordList = Arrays.asList(intCombntnSeqNo);
		try {
			
			if ((String) objHttpServletRequest.getParameter("clauseSeq") != null) {
				 intClauseSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("clauseSeq").toString());
			}
						
			StringTokenizer stCompSeqNo = null;
			
			//CR 88
			//objModelAddClauseForm.setHdnCombntnSeqNoArr(objModelAddClauseForm.getHdnCombntnSeqNoArr());
			//if (((String) objHttpServletRequest.getParameter("combntnSeq") != null)) {
	        /**
	         * convert "combination sequence no" values to arraylist
	         */	
			if ((objHttpServletRequest.getParameter("combntnSeq") != null)) {
				stCompSeqNo = new StringTokenizer(objHttpServletRequest.getParameter("combntnSeq"), "~");
				while (stCompSeqNo.hasMoreTokens()) {
					charCombnList.add(stCompSeqNo.nextToken());
				
				}
			}
			
		
/*			if ((String) objHttpServletRequest.getParameter("combntnSeq") != null) {
				 intCombntnSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("combntnSeq").toString());
			}*/
			
			ClauseVO objClauseVO = new ClauseVO();
			ArrayList arlCharGrpCombntn = new ArrayList();
			objClauseVO.setClauseSeqNo(intClauseSeqNo);
			objClauseVO.setUserID(objLoginVo.getUserID());
			//CR 88
			ModelAssignEdlBI objModelAssignEdlBI = ServiceFactory.getModelAssignEdlBO();
			arlCharGrpCombntn = objModelAssignEdlBI.fetchCharGrpCombntn(objClauseVO);
			
			ClauseVO objClauseVO1=(ClauseVO)arlCharGrpCombntn.get(0);
			
			
			if (arlCharGrpCombntn != null && arlCharGrpCombntn.size() > 0){
				objModelAddClauseForm.setCharGrpCombntnList(arlCharGrpCombntn);
				objModelAddClauseForm.setCharCombnList(charCombnList);
			}
			else{
				objModelAddClauseForm.setMethod("fetchCharCombntnEdlMap");
				//objModelAddClauseForm.setCharCombnList(charCombnList);
			}
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelAddClauseAction.fetchCharCombntnMap");
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

	//CR_85
	//Added for CR_88 on 02/07/10 by SD41630
/***************************************************************************
	 * This Method is used to fetch the Characteristic combinationEDls 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param claSeqnoList
	 * @return String
 * @throws EMDException,
	Exception 
	 *  
	 **************************************************************************/
	
	public static String strClaSeqNoListFormat(String claSeqnoList) throws EMDException,
	Exception {
		
		   StringBuffer lipsum =new StringBuffer();
	         int intEndIndex=0;
	         String strToken=null;
	         String strClaSeqnoList=null;
	         String[] token = claSeqnoList.split ("\\&");
	         int intStartIndex=0;
	         try{
	         for (int i=0; i < token.length; i++){
	        	 strToken=token[i].toString();
	        	intStartIndex=strToken.indexOf("=");
	        	 intEndIndex=strToken.length();
	        	 if(intStartIndex>=0 && intEndIndex>1){
	        	  String strClaSeqno=strToken.substring(intStartIndex+1,intEndIndex);
	        	   		 
	        	 lipsum.append(strClaSeqno);
	        	 lipsum.append(",");
	        	 strClaSeqnoList=lipsum.toString();
	        	 }
	        			        	 }
	        if(strClaSeqnoList!=null){
	        int strlength=strClaSeqnoList.length();
	         strClaSeqnoList=strClaSeqnoList.substring(1,strlength-1);
	         strClaSeqnoList.trim();
	            }
	         }catch (Exception objExp) {
	 			LogUtil
				.logMessage("enters into catch block in ModelBo:ApplicationException"
						+ objExp.getMessage());
				throw new Exception(objExp.getMessage());
			}	
		return strClaSeqnoList;
	}
	//Added for CR_88 on 2july10 by sd41630
	
	
	/*******************************************************************************************
	 * Added for LSDB_CR_88 PM&I change
	 * This method is for lupdateReInsertClause
	 * @author  Satyam Computer Services Ltd  
	 * @version 1.0  
	 * @param      objActionMapping    
	 * @param 	   objActionForm
	 * @param	   objHttpServletRequest
	 * @param      objHttpServletResponse
	 * @return     ActionForward 
	 * @throws     ApplicationException
	 ******************************************************************************************/
	
	public ActionForward updateReInsertClause(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		
		LogUtil.logMessage("Entering ModelAddClauseAction.updateReInsertClause");
		
		ArrayList arlCustomerList = null;
		ArrayList arlStandardEquipList = null;
		String strForwardKey = ApplicationConstants.SUCCESS;
		ModelAddClauseForm objModelAddClauseForm = (ModelAddClauseForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		ArrayList arlClaSeqNoList = new ArrayList();
		ClauseVO objClauseVO = new ClauseVO();
		ArrayList arlSpec = null;
//		CR_81 lines are started here
		ArrayList arlModelList = null;
		ArrayList arlSectionList = null;
		ArrayList arlSubSecList = null;
		int intSuccess = 0;
		
		int specTypeNo = -1;
		int modleSeqNo = -1;
		int sectionSeqNo = -1;
		int subSectionSeqNo = -1;
		
		String strSpecTypeNo = null;
		String strModleSeqNo = null;
		String strSectionSeqNo = null;
		String strSubSectionSeqNo = null;
		//Added For CR_88 
		String strClaSeqNoList = null;
		
		strSpecTypeNo = (String) objHttpServletRequest
		.getParameter("specTypeNo");
strModleSeqNo = (String) objHttpServletRequest
		.getParameter("modelSeqNo");
strSectionSeqNo = (String) objHttpServletRequest
		.getParameter("sectionSeqNo");
strSubSectionSeqNo = (String) objHttpServletRequest
		.getParameter("subSectionSeqNo");
try {

if (strSpecTypeNo != null) {
	LogUtil
			.logMessage(" Ends From request***********************************111");
	objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);

	specTypeNo = Integer.parseInt(strSpecTypeNo);
	LogUtil.logMessage("value of strSpecTypeNo from  session"
			+ strSpecTypeNo);
	objModelAddClauseForm.setSpecTypeNo(specTypeNo);

	LogUtil
			.logMessage(" Ends From request***********************************2222");
} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
	if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {

		LogUtil
				.logMessage(" Ends From request***********************************3333");
		strSpecTypeNo = (String) objSession
				.getAttribute("SPEC_TYPE_NO");
		specTypeNo = Integer.parseInt(strSpecTypeNo);
		objModelAddClauseForm.setSpecTypeNo(specTypeNo);
		LogUtil
				.logMessage(" Ends From request***********************************444");

	}
}

if (strModleSeqNo != null) {

	objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
	modleSeqNo = Integer.parseInt(strModleSeqNo);
	LogUtil.logMessage("value of MODEL_SEQ_NO from session "
			+ modleSeqNo);
	objModelAddClauseForm.setModelSeqNo(modleSeqNo);

} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {

	if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
		strModleSeqNo = (String) objSession
				.getAttribute("MODEL_SEQ_NO");
		modleSeqNo = Integer.parseInt(strModleSeqNo);
		objModelAddClauseForm.setModelSeqNo(modleSeqNo);
	}
}

if (strSectionSeqNo != null) {

	objSession.setAttribute("SECTION_SEQ_NO", strSectionSeqNo);
	sectionSeqNo = Integer.parseInt(strSectionSeqNo);
	LogUtil.logMessage("value of SECTION_SEQ_NO from session "
			+ strSectionSeqNo);

	objModelAddClauseForm.setSectionSeqNo(sectionSeqNo);
} else if (objSession.getAttribute("SECTION_SEQ_NO") != null) {
	if (strSectionSeqNo == null || "".equals(strSectionSeqNo)) {
		strSectionSeqNo = (String) objSession
				.getAttribute("SECTION_SEQ_NO");
		sectionSeqNo = Integer.parseInt(strSectionSeqNo);
		objModelAddClauseForm.setSectionSeqNo(sectionSeqNo);

	}
}

if (strSubSectionSeqNo != null) {
	objSession.setAttribute("SUB_SECTION_SEQ_NO", strSubSectionSeqNo);
	subSectionSeqNo = Integer.parseInt(strSubSectionSeqNo);
	objModelAddClauseForm.setSubSectionSeqNo(subSectionSeqNo);
	LogUtil.logMessage("value of SECTION_SEQ_NO from session "
			+ subSectionSeqNo);
} else if (objSession.getAttribute("SUB_SECTION_SEQ_NO") != null) {
	if (strSubSectionSeqNo == null || "".equals(strSubSectionSeqNo)) {
		strSubSectionSeqNo = (String) objSession
				.getAttribute("SUB_SECTION_SEQ_NO");
		objSession.setAttribute("SUB_SECTION_SEQ_NO",
				strSubSectionSeqNo);
		subSectionSeqNo = Integer.parseInt(strSubSectionSeqNo);
		objModelAddClauseForm.setSubSectionSeqNo(subSectionSeqNo);	
		LogUtil.logMessage("value of SUB_SECTION_SEQ_NO from session if SUB_SECTION_SEQ_NO is not null "
						+ strSubSectionSeqNo);
	}

}

specTypeNo = objModelAddClauseForm.getSpecTypeNo();
modleSeqNo = objModelAddClauseForm.getModelSeqNo();
sectionSeqNo = objModelAddClauseForm.getSectionSeqNo();
subSectionSeqNo = objModelAddClauseForm.getSubSectionSeqNo();
strClaSeqNoList=objModelAddClauseForm.getRowIndexList();


			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			arlSpec = fetchSpecType(objSpecTypeVo);
			CustomerVO objCustomerVO = new CustomerVO();
			objCustomerVO.setUserID(objLoginVo.getUserID());
			
			StandardEquipVO objStandardEquipVO = new StandardEquipVO();
			objStandardEquipVO.setUserID(objLoginVo.getUserID());
			arlCustomerList = new ArrayList();
			arlStandardEquipList = new ArrayList();
			
			arlCustomerList = fetchCustomer(objCustomerVO);
			arlStandardEquipList = fetchStandardEquipment(objStandardEquipVO);
			objModelAddClauseForm.setSpecTypeList(arlSpec);
			objModelAddClauseForm.setCustomerList(arlCustomerList);
			objModelAddClauseForm
			.setStandardEquipmentList(arlStandardEquipList);
			objModelAddClauseForm.setModifyClauseFlag(ApplicationConstants.NO);
			if(objModelAddClauseForm.getChildFlag()!=null){
				}else{
			objModelAddClauseForm.setChildFlag(ApplicationConstants.YES);
				}
			if (specTypeNo != -1 && specTypeNo != 0) {

				LogUtil.logMessage("in side modleSeqNo" + modleSeqNo);

				ModelVo objModelVo = new ModelVo();
				objModelVo.setUserID(objLoginVo.getUserID());
				objModelVo.setSpecTypeSeqNo(specTypeNo);
				ModelBI objModelBO = ServiceFactory.getModelBO();
				if (objModelBO.fetchModels(objModelVo) != null) {
					arlModelList = objModelBO.fetchModels(objModelVo);
					objModelAddClauseForm.setModelList(arlModelList);
					objModelAddClauseForm.setModelSeqNo(modleSeqNo);
					LogUtil.logMessage("end of the if modleSeqNo" + modleSeqNo);
				}

			}
	
			if (modleSeqNo != -1 && modleSeqNo != 0) {

				LogUtil.logMessage("in side if sectionSeqNo" + sectionSeqNo);

				SectionVO objSectionMaintVo = new SectionVO();
				objSectionMaintVo.setModelSeqNo(modleSeqNo);
				objSectionMaintVo.setUserID(objLoginVo.getUserID());
				SectionBI objSectionMaintBO = ServiceFactory.getSectionBO();
				arlSectionList = objSectionMaintBO
						.fetchSections(objSectionMaintVo);
				if (arlSectionList != null)
					objModelAddClauseForm.setSectionList(arlSectionList);
				objModelAddClauseForm.setSectionSeqNo(sectionSeqNo);
				LogUtil.logMessage("end of the if sectionSeqNo" + sectionSeqNo);
			}

			if (sectionSeqNo != -1 && sectionSeqNo != 0) {
				LogUtil.logMessage("in side if subSectionSeqNo"
						+ subSectionSeqNo);
				SubSectionVO objSubSecMaintVO = new SubSectionVO();
				objSubSecMaintVO.setModelSeqNo(modleSeqNo);
				objSubSecMaintVO.setSecSeqNo(sectionSeqNo);
				objSubSecMaintVO.setUserID(objLoginVo.getUserID());

				ModelSubSectionBI objSubSecMaintBO = ServiceFactory
						.getSubSecMaintBO();
				arlSubSecList = objSubSecMaintBO
						.fetchSubSections(objSubSecMaintVO);

				if (arlSubSecList != null && arlSubSecList.size() != 0) {

					objModelAddClauseForm.setSubSectionList(arlSubSecList);
					
					if (subSectionSeqNo != -1 && subSectionSeqNo != 0) {
					
					objModelAddClauseForm.setSubSectionSeqNo(subSectionSeqNo);

					LogUtil.logMessage("end of the if subSectionSeqNo"
							+ subSectionSeqNo);
					
					objSubSecMaintVO.setSubSecSeqNo(objModelAddClauseForm.getSubSectionSeqNo());
														
					objClauseVO.setModelSeqNo(objSubSecMaintVO.getModelSeqNo());
					objClauseVO.setSubSectionSeqNo(objSubSecMaintVO.getSubSecSeqNo());
					objClauseVO.setUserID(objSubSecMaintVO.getUserID());
					
					//String strClaSeqNoList=strClaSeqNoListFormat(strRowIndexList);//Modified for CR_94 & CR_88
					
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
					ModelCompBI objModelCompBO = ServiceFactory.getModelCompBO();
					ModelClauseBI ModelClauseBO = ServiceFactory.getModelClauseBO();
					
					intSuccess = ModelClauseBO.updateRearrangeClauses(objClauseVO);
					objModelAddClauseForm
					.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
					
					objModelAddClauseForm.setRowIndexList("");
					//Added for CR_94 for getting the parent & child clauses
					objSubSecMaintVO.setRearrFlag("N");
					//CR_94 Ends here
					ArrayList arlClaList = fetchClauseDesc(objSubSecMaintVO);
					
					
					ComponentVO objComponentVO = new ComponentVO();
					objComponentVO.setUserID(objLoginVo.getUserID());
					objComponentVO.setSubSectionSeqNo(objModelAddClauseForm
							.getSubSectionSeqNo());
					objComponentVO.setModelSeqNo(objModelAddClauseForm.getModelSeqNo());

					
					ArrayList arlCompGroupList = objModelCompBO.fetchModelComps(objComponentVO);
						
					if (arlCompGroupList != null && arlCompGroupList.size() > 0) {
						objModelAddClauseForm.setCompGroupList(arlCompGroupList);
					}
					
						if (arlClaList != null && arlClaList.size() > 0) {
							//Added & modified for CR_94
							objModelAddClauseForm.setComponentVO((ArrayList) arlClaList.get(0));
							objModelAddClauseForm.setParentClaList((ArrayList) arlClaList.get(1));
							objModelAddClauseForm.setChildClaList((ArrayList) arlClaList.get(2));
							//CR_94 Ends here
						}else{
							objModelAddClauseForm.setMethod("updateReInsertClause");
							}
					
					
					}
					
				}
				

				else {

					objModelAddClauseForm.setMethod("updateReInsertClause");

				}

			}
			

			
		} catch (Exception objExp) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objExp.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		LogUtil.logMessage("***********strForwardKey***********"+strForwardKey);
		return objActionMapping.findForward(strForwardKey);

	}
	
	
	
	
	
	/***************************************************************************
	 * * * * This Method is used to fetch Clause Details added For CR_97
	 * 
	 * @author SD 41630
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward fetchClauseDetails(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		LogUtil
		.logMessage("Enters into ModelAddClauseAction.fetchClauseDetails");
		ModelAddClauseForm objModelAddClauseForm = (ModelAddClauseForm) objActionForm;

		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		String strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList arlClauseList = new ArrayList();
		ArrayList arlDefaultVersion = new ArrayList();
		ArrayList arlClaList = new ArrayList();
		ArrayList arlSpec = null;
		ArrayList arlCompGroupList = null;
		ArrayList arlCustomerList=null;
		ArrayList arlModelList = null;
		ArrayList arlSectionList = null;
		ArrayList arlLeadCompClaList =null;
		
		int specTypeNo = -1;
		int modleSeqNo = -1;
		int sectionSeqNo = -1;
		int subSectionSeqNo = -1;
		int leadComponentSeqNo=-1;
		
		String strSpecTypeNo = null;
		String strModleSeqNo = null;
		String strSectionSeqNo = null;
		String strSubSectionSeqNo = null;
			
		
		strSpecTypeNo = (String) objHttpServletRequest
		.getParameter("specTypeNo");
		strModleSeqNo = (String) objHttpServletRequest
				.getParameter("modelSeqNo");
		strSectionSeqNo = (String) objHttpServletRequest
				.getParameter("sectionSeqNo");
		strSubSectionSeqNo = (String) objHttpServletRequest
				.getParameter("subSectionSeqNo");
		
		try {
						
			//strSubSectionSeqNo = (String) objHttpServletRequest.getParameter("subSectionSeqNo");
		
		
		if (strSpecTypeNo != null) {
			
			objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);
			specTypeNo = Integer.parseInt(strSpecTypeNo);
			objModelAddClauseForm.setSpecTypeNo(specTypeNo);
		
		} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
			if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {
		
				strSpecTypeNo = (String) objSession
						.getAttribute("SPEC_TYPE_NO");
				specTypeNo = Integer.parseInt(strSpecTypeNo);
				objModelAddClauseForm.setSpecTypeNo(specTypeNo);
	
			}
		}
		
		if (strModleSeqNo != null) {
		
			objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
			modleSeqNo = Integer.parseInt(strModleSeqNo);
     		objModelAddClauseForm.setModelSeqNo(modleSeqNo);
		
		} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {
		
			if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
				strModleSeqNo = (String) objSession
						.getAttribute("MODEL_SEQ_NO");
				modleSeqNo = Integer.parseInt(strModleSeqNo);
				objModelAddClauseForm.setModelSeqNo(modleSeqNo);
			}
		}
		
		if (strSectionSeqNo != null) {
		
			objSession.setAttribute("SECTION_SEQ_NO", strSectionSeqNo);
			sectionSeqNo = Integer.parseInt(strSectionSeqNo);
			objModelAddClauseForm.setSectionSeqNo(sectionSeqNo);
		} else if (objSession.getAttribute("SECTION_SEQ_NO") != null) {
			if (strSectionSeqNo == null || "".equals(strSectionSeqNo)) {
				strSectionSeqNo = (String) objSession
						.getAttribute("SECTION_SEQ_NO");
				sectionSeqNo = Integer.parseInt(strSectionSeqNo);
				objModelAddClauseForm.setSectionSeqNo(sectionSeqNo);
		
			}
		}
		
		if (strSubSectionSeqNo != null) {
			objSession.setAttribute("SUB_SECTION_SEQ_NO", strSubSectionSeqNo);
			subSectionSeqNo = Integer.parseInt(strSubSectionSeqNo);
			objModelAddClauseForm.setSubSectionSeqNo(subSectionSeqNo);
		} else if (objSession.getAttribute("SUB_SECTION_SEQ_NO") != null) {
			if (strSubSectionSeqNo == null || "".equals(strSubSectionSeqNo)) {
				strSubSectionSeqNo = (String) objSession
						.getAttribute("SUB_SECTION_SEQ_NO");
				objSession.setAttribute("SUB_SECTION_SEQ_NO",
						strSubSectionSeqNo);
				subSectionSeqNo = Integer.parseInt(strSubSectionSeqNo);
				objModelAddClauseForm.setSubSectionSeqNo(subSectionSeqNo);
				
			}
		
		}
		
		specTypeNo = objModelAddClauseForm.getSpecTypeNo();
		modleSeqNo = objModelAddClauseForm.getModelSeqNo();
		sectionSeqNo = objModelAddClauseForm.getSectionSeqNo();
		subSectionSeqNo = objModelAddClauseForm.getSubSectionSeqNo();
			
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSubSectionList = null;
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			arlSpec = fetchSpecType(objSpecTypeVo);
					
			objModelVo.setSpecTypeSeqNo(objModelAddClauseForm
					.getSpecTypeNo());
			
			objModelAddClauseForm.setSpecTypeList(arlSpec);
			
			ModelBI objModelBo = ServiceFactory.getModelBO();
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null) {
				objModelAddClauseForm.setModelList(arlModelList);
			}
			
			SectionVO objSectionVO = new SectionVO();
			objSectionVO.setUserID(objLoginVo.getUserID());
			objSectionVO.setModelSeqNo(objModelAddClauseForm.getModelSeqNo());
			SectionBI objSectionBO = ServiceFactory.getSectionBO();
			arlSectionList = objSectionBO.fetchSections(objSectionVO);
			objModelAddClauseForm.setSectionList(arlSectionList);
			
			SubSectionVO objSubSectionVO = new SubSectionVO();
			objSubSectionVO
			.setModelSeqNo(objModelAddClauseForm.getModelSeqNo());
			objSubSectionVO
			.setSecSeqNo(objModelAddClauseForm.getSectionSeqNo());
			objSubSectionVO.setUserID(objLoginVo.getUserID());
			
			
			ModelSubSectionBI objModelSubSecBO = ServiceFactory
			.getSubSecMaintBO();
			arlSubSectionList = objModelSubSecBO
			.fetchSubSections(objSubSectionVO);
			objModelAddClauseForm.setSubSectionList(arlSubSectionList);
			
			CustomerVO objCustomerVO = new CustomerVO();
			objCustomerVO.setUserID(objLoginVo.getUserID());
			arlCustomerList = fetchCustomer(objCustomerVO);
			if (arlCustomerList != null && arlCustomerList.size() > 0) {
				objModelAddClauseForm.setCustomerList(arlCustomerList);
			}
			
			ComponentVO objComponentVO = new ComponentVO();
			
			objComponentVO.setUserID(objLoginVo.getUserID());
			objComponentVO.setModelSeqNo(objModelAddClauseForm.getModelSeqNo());
			objComponentVO.setSubSectionSeqNo(objModelAddClauseForm
					.getSubSectionSeqNo());
			
			ModelCompBI objModelCompBO = ServiceFactory.getModelCompBO();
			arlCompGroupList = objModelCompBO.fetchModelComps(objComponentVO);
			
			if (arlCompGroupList != null && arlCompGroupList.size() > 0) {
				objModelAddClauseForm.setCompGroupList(arlCompGroupList);
			}
			
			objComponentVO.setComponentGroupSeqNo(objModelAddClauseForm.getCompGroupSeqNo());
			leadComponentSeqNo=objModelAddClauseForm.getLeadComponentSeqNo();
			objComponentVO.setComponentSeqNo(leadComponentSeqNo);
			arlLeadCompClaList = objModelCompBO.viewLeadComponentClausesByModel(objComponentVO);
			objModelAddClauseForm.setLeadComponentVO(arlLeadCompClaList);
			objModelAddClauseForm.setLeadComponentSeqNo(leadComponentSeqNo);
			
			
			objSubSectionVO.setSubSecSeqNo(objModelAddClauseForm.getSubSectionSeqNo());
			arlClaList = fetchClauseDesc(objSubSectionVO);
			objModelAddClauseForm.setComponentVO(arlClaList);
			
			ClauseVO objClauseVO = new ClauseVO();
			
			objClauseVO.setModelSeqNo(Integer.parseInt(objHttpServletRequest
					.getParameter("leadClaMdlSeq").toString()));
			
			objClauseVO.setSubSectionSeqNo(Integer.parseInt(objHttpServletRequest
					.getParameter("leadClaSubSecSeq").toString()));
			
			
			objClauseVO.setClauseSeqNo(objModelAddClauseForm
					.getHdnClauseSeqNo());
			
			
			objClauseVO.setUserID(objLoginVo.getUserID());
			
			ModelSelectClauseRevBI objModelSelectClauseRevBO = ServiceFactory
			.getModelSelectClauseRevBO();
			arlClauseList = objModelSelectClauseRevBO
			.fetchClauseVersions(objClauseVO);
			
			if (arlClauseList != null && arlClauseList.size() > 0) {
				
				arlDefaultVersion = (ArrayList) arlClauseList.get(1);
				objModelAddClauseForm.setClauseList(arlDefaultVersion);
				objModelAddClauseForm.setHdnClauseSeqNo(objClauseVO
						.getClauseSeqNo());
				}
			
			if (arlClauseList.size() == 0) {
				
				objModelAddClauseForm.setMethod("ClauseVersions");
				
			}
			StandardEquipVO objStandardEquipVO = new StandardEquipVO();
			objStandardEquipVO.setUserID(objLoginVo.getUserID());
			ArrayList arlStandardEquipList = new ArrayList();
			arlStandardEquipList = fetchStandardEquipment(objStandardEquipVO);
			objModelAddClauseForm.setStandardEquipmentList(arlStandardEquipList);	
			objClauseVO.setModelSeqNo(modleSeqNo);
			objClauseVO.setSubSectionSeqNo(subSectionSeqNo);
			
	}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelAddClauseAction..");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	//Added for CR-113 for Clauses in orders report
	
	/***************************************************************************
	 * This Method is used to search clauseDetails 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
/*	public ActionForward search(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering ModelAddClauseAction.search");
		String strForwardKey = ApplicationConstants.CLA_IN_ORDERS_REPORT;
		ModelAddClauseForm objModelAddClauseForm = (ModelAddClauseForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		ArrayList arlOrderList = null;
		
		try {
			LogUtil.logMessage("objModelAddClauseForm.getHdnClauseVersionNo():"+objModelAddClauseForm.getHdnClauseVersionNo());
			String forwardToExcel = objHttpServletRequest.getParameter("Excel");
			//Added for CR-121
			String strWindowFlag  = objHttpServletRequest.getParameter("windowflag");
			
			ClauseVO objClauseVO = new ClauseVO();
			if (forwardToExcel.equalsIgnoreCase("Y"))
				strForwardKey = ApplicationConstants.CLA_IN_ORDERS_REPORT_EXCEL;
			
            //Added for CR-121 ends here
			objClauseVO.setClauseSeqNo(objModelAddClauseForm
					.getHdnClauseSeqNo());
			objClauseVO.setVersionNo(objModelAddClauseForm
					.getHdnClauseVersionNo());
			//objClauseVO.setShowLatestpubFlag(ApplicationConstants.NO);
			//Added for CR-113 QA fix
			if(objModelAddClauseForm.isShowLatSpecFlag()){
				objModelAddClauseForm.setHdnShowLatSpecFlag("Yes");	
				objClauseVO.setShowLatestPubSpecFlag(ApplicationConstants.YES);
			}
			else{
				//if added for CR-121
				if(strWindowFlag != null){
				if (strWindowFlag.equalsIgnoreCase("Y") && forwardToExcel.equalsIgnoreCase("N")){
					objModelAddClauseForm.setHdnShowLatSpecFlag("Yes");	
				 	objClauseVO.setShowLatestPubSpecFlag(ApplicationConstants.YES);
				 }
				}else{	
				objModelAddClauseForm.setHdnShowLatSpecFlag("No");		
				objClauseVO.setShowLatestPubSpecFlag(ApplicationConstants.NO);
				}
			}
			//Added for CR-113 QA fix Ends Here
			objClauseVO.setOrderbyFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
			objClauseVO.setUserID(objLoginVo.getUserID());
			
			ModelBI objModelBO = ServiceFactory.getModelBO();
			arlOrderList = objModelBO.search(objClauseVO);
			
			if (arlOrderList != null) {
				objModelAddClauseForm.setHdnSelectedSpecType(objModelAddClauseForm.getHdnSelectedSpecType());
				objModelAddClauseForm.setHdnSelectedModel(objModelAddClauseForm.getHdnSelectedModel());
				objModelAddClauseForm.setHdnSelectedSection(objModelAddClauseForm.getHdnSelectedSection());
				objModelAddClauseForm.setHdnSelectedSubSection(objModelAddClauseForm.getHdnSelectedSubSection());
				objModelAddClauseForm.setHdnClauseVersionNo(objClauseVO.getVersionNo());
				objModelAddClauseForm.setOrderList(arlOrderList);
			}
			
			this.fetchClauseVersions(objActionMapping,objActionForm,objHttpServletRequest,objHttpServletResponse);
			objModelAddClauseForm.setVersionNo(objClauseVO.getVersionNo());
		
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelAddClauseAction.search");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
		}
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		
		return objActionMapping.findForward(strForwardKey);
	}*/
	//Added for CR-130
	public ActionForward search(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering ModelAddClauseAction.search");
		String strForwardKey = ApplicationConstants.CLA_IN_ORDERS_REPORT;
		ModelAddClauseForm objModelAddClauseForm = (ModelAddClauseForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		ArrayList arlOrderList = null;
		String forwardToExcel = objHttpServletRequest.getParameter("Excel");
		
		try {
			LogUtil.logMessage("objModelAddClauseForm.getHdnClauseVersionNo():"+objModelAddClauseForm.getHdnClauseVersionNo());
			
			//Added for CR-121
			String strWindowFlag  = objHttpServletRequest.getParameter("windowflag");
			LogUtil.logMessage("forwardToExcel "+forwardToExcel);
			ClauseVO objClauseVO = new ClauseVO();
			if (forwardToExcel.equalsIgnoreCase("Y"))
				strForwardKey = ApplicationConstants.SUCCESS;
			
            //Added for CR-121 ends here
			objClauseVO.setClauseSeqNo(objModelAddClauseForm
					.getHdnClauseSeqNo());
			objClauseVO.setVersionNo(objModelAddClauseForm
					.getHdnClauseVersionNo());
			//objClauseVO.setShowLatestpubFlag(ApplicationConstants.NO);
			//Added for CR-113 QA fix
			if(objModelAddClauseForm.isShowLatSpecFlag()){
				objModelAddClauseForm.setHdnShowLatSpecFlag("Yes");	
				objClauseVO.setShowLatestPubSpecFlag(ApplicationConstants.YES);
			}
			else{
				//if added for CR-121
				if(strWindowFlag != null){
				if (strWindowFlag.equalsIgnoreCase("Y") && forwardToExcel.equalsIgnoreCase("N")){
					objModelAddClauseForm.setHdnShowLatSpecFlag("Yes");	
				 	objClauseVO.setShowLatestPubSpecFlag(ApplicationConstants.YES);
				 }
				}else{	
				objModelAddClauseForm.setHdnShowLatSpecFlag("No");		
				objClauseVO.setShowLatestPubSpecFlag(ApplicationConstants.NO);
				}
			}
			//Added for CR-113 QA fix Ends Here
			objClauseVO.setOrderbyFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
			objClauseVO.setUserID(objLoginVo.getUserID());
			
			ModelBI objModelBO = ServiceFactory.getModelBO();
			arlOrderList = objModelBO.search(objClauseVO);
			
			if (arlOrderList != null) {
				objModelAddClauseForm.setHdnSelectedSpecType(objModelAddClauseForm.getHdnSelectedSpecType());
				objModelAddClauseForm.setHdnSelectedModel(objModelAddClauseForm.getHdnSelectedModel());
				objModelAddClauseForm.setHdnSelectedSection(objModelAddClauseForm.getHdnSelectedSection());
				objModelAddClauseForm.setHdnSelectedSubSection(objModelAddClauseForm.getHdnSelectedSubSection());
				objModelAddClauseForm.setHdnClauseVersionNo(objClauseVO.getVersionNo());
				objModelAddClauseForm.setOrderList(arlOrderList);
			}
			
			this.fetchClauseVersions(objActionMapping,objActionForm,objHttpServletRequest,objHttpServletResponse);
			objModelAddClauseForm.setVersionNo(objClauseVO.getVersionNo());
			
			if (forwardToExcel.equalsIgnoreCase("Y")){
			HSSFWorkbook workBook = new HSSFWorkbook();
			HSSFSheet sheet = workBook.createSheet("ClauseVersionInOrdersReport");
			
			HSSFCellStyle cellHeadStyle    = workBook.createCellStyle();
			HSSFCellStyle cellNormalStyle  = workBook.createCellStyle();
			HSSFCellStyle cellHeadingStyle = workBook.createCellStyle();
			HSSFCellStyle cellStyle = workBook.createCellStyle();
			
			HSSFFont headFont = workBook.createFont();
			
			headFont.setFontName(HSSFFont.FONT_ARIAL);
			headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			headFont.setColor(HSSFColor.BLACK.index);	
			headFont.setFontHeightInPoints((short) 10);

			cellHeadStyle.setWrapText(true);
			cellHeadStyle.setFont(headFont);
			cellHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
			cellHeadingStyle.setWrapText(true);
			cellHeadingStyle.setFont(headFont);
			cellHeadingStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			cellHeadingStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
			HSSFFont normalFont = workBook.createFont();
			
			normalFont.setFontName(HSSFFont.FONT_ARIAL);
			normalFont.setColor(HSSFColor.BLACK.index);	
			normalFont.setFontHeightInPoints((short) 10);

			cellNormalStyle.setWrapText(true);
			cellNormalStyle.setFont(normalFont);
			cellNormalStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellNormalStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
			cellStyle.setFont(normalFont);
			cellStyle.setWrapText(true);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
			HSSFCellStyle cellTabHeadStyle = workBook.createCellStyle();										
			
			HSSFFont claTabFont = workBook.createFont();			
			claTabFont.setFontName(HSSFFont.FONT_ARIAL);
			claTabFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			claTabFont.setColor(HSSFColor.BLACK.index);		
			claTabFont.setFontHeightInPoints((short) 10);
			
			cellTabHeadStyle.setFont(claTabFont);
			cellTabHeadStyle.setWrapText(true);
			cellTabHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellTabHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
			Iterator ListOrderList = null;
			Iterator ListClauseList = null;
			ArrayList arlClaList = new ArrayList();
			ArrayList arlOrdrList = new ArrayList();
			int nrowCount=0;
			int intCol =0;
			int nTblDataSize = 0;
			HSSFRow row = sheet.createRow(nrowCount);
			
			if (arlOrderList != null) {
				ListClauseList = arlOrderList.iterator();
				while(ListClauseList.hasNext()){				
					arlClaList = new ArrayList();
				objClauseVO= new ClauseVO();
				objClauseVO = (ClauseVO)ListClauseList.next();
				if(objClauseVO.getOrderList()!=null){
					arlOrdrList = (ArrayList) objClauseVO.getOrderList();
					ListOrderList = arlOrdrList.iterator();
					
					//LogUtil.logMessage("objClauseVO.getClauseDesc:"+objClauseVO.getClauseDesc());
				createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString("Clause Version In Orders Report"));
				sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),intCol,intCol+2));
				nrowCount = nrowCount+2;
				row = sheet.createRow(nrowCount);
				
				createCell(row,cellHeadingStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString("Specification Type:"));
				intCol++;
				createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString(objModelAddClauseForm.getHdnSelectedSpecType()));
				sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),intCol,intCol+1));
				intCol--;
				nrowCount++;
				row = sheet.createRow(nrowCount);
				
				createCell(row,cellHeadingStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString("Model:"));
				intCol++;
				createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString(objModelAddClauseForm.getHdnSelectedModel()));
				sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),intCol,intCol+1));
				intCol--;
				nrowCount++;
				row = sheet.createRow(nrowCount);
				
				createCell(row,cellHeadingStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString("Section:"));
				intCol++;
				createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString(objModelAddClauseForm.getHdnSelectedSection()));
				sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),intCol,intCol+1));
				intCol--;
				nrowCount++;
				row = sheet.createRow(nrowCount);
				
				createCell(row,cellHeadingStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString("SubSection:"));
				intCol++;
				createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString(objModelAddClauseForm.getHdnSelectedSubSection()));
				sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),intCol,intCol+1));
				intCol--;
				nrowCount++;
				row = sheet.createRow(nrowCount);
				
				createCell(row,cellHeadingStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString("Clause Description:"));
				intCol++;
				HSSFRichTextString richTextClaDesc = new HSSFRichTextString("");
				if(objClauseVO.getClauseDesc()!=null && objClauseVO.getClauseDesc()!=""){
					richTextClaDesc = new HSSFRichTextString(ApplicationUtil.getRefinedClauseDesc(objClauseVO.getClauseDesc().trim()));
				}
				createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intCol,richTextClaDesc);
				sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),intCol,intCol+1));
				
				intCol--;
				nrowCount++;
				row = sheet.createRow(nrowCount);
				
				createCell(row,cellHeadingStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString("Show latest published spec only:"));
				intCol++;
				createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString(objModelAddClauseForm.getHdnShowLatSpecFlag()));
				sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),intCol,intCol+1));
				intCol--;
				nrowCount=nrowCount+2;
				row = sheet.createRow(nrowCount);
				
				createHeadingClaVersionInOrdersRep(workBook,row,sheet);
				nrowCount++;
				row = sheet.createRow(nrowCount);
				
				while(ListOrderList.hasNext()){
						OrderVO objOrderVO = new OrderVO();
						objOrderVO = (OrderVO) ListOrderList.next();
					
						LogUtil.logMessage("objOrderVO.getOrderNo():"+objOrderVO.getOrderNo());
						LogUtil.logMessage("objOrderVO.getStatusDesc():"+objOrderVO.getStatusDesc());
						LogUtil.logMessage("objOrderVO.getCustomerName():"+objOrderVO.getCustomerName());
						
						createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString(objOrderVO.getOrderNo()));
						intCol++;
						createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString(objOrderVO.getStatusDesc()));
						intCol++;
						createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString(objOrderVO.getCustomerName()));
						nrowCount++;
						row = sheet.createRow(nrowCount);
						intCol=0;
					}
					
			}
			}
			}
			objHttpServletResponse.setContentType("application/vnd.ms-excel");
			objHttpServletResponse.setHeader("Content-disposition", "attachment; filename= ClauseVersionInOrdersReport.xls");
			OutputStream out = null;
			out = objHttpServletResponse.getOutputStream();
			workBook.write(out);
		}
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelAddClauseAction.search");
			ErrorInfo objErrorInfo = new ErrorInfo();
			String strError = objExp.getMessage();
			objErrorInfo.setMessage(strError);
			LogUtil.logMessage("Error Message:" + strError);
			LogUtil.logError(objExp, objErrorInfo);
			strForwardKey = ApplicationConstants.FAILURE;
		}
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		
		if (forwardToExcel.equalsIgnoreCase("Y")){
			if (strForwardKey.equalsIgnoreCase(ApplicationConstants.FAILURE))
				return objActionMapping.findForward(strForwardKey);
			else
				return null;
		}else if (forwardToExcel.equalsIgnoreCase("N"))
			return objActionMapping.findForward(strForwardKey);
		else
			return null;
	}
		
	protected HSSFCell createCell(HSSFRow row, HSSFCellStyle headerStyle,
			int cellType, int position, int cellValue) {
		//This function is used to create a cell with int value
		HSSFCell cell = null;
		cell = row.createCell(position);
		cell.setCellStyle(headerStyle);
		cell.setCellType(cellType);
		cell.setCellValue(cellValue);
		return cell;
	}
	
	protected HSSFCell createCell(HSSFRow row, HSSFCellStyle headerStyle,
			int cellType, int colPosition, HSSFRichTextString cellValue) {
		//This function is used to create a cell with String value
		HSSFCell cell = null;
		cell = row.createCell(colPosition);
		cell.setCellStyle(headerStyle);
		cell.setCellType(cellType);
		cell.setCellValue(cellValue);
		return cell;
	}
	
	public void createHeadingClaVersionInOrdersRep(HSSFWorkbook workBook, HSSFRow row,
			HSSFSheet sheet) throws Exception {

	HSSFCellStyle cellHeadStyle = workBook.createCellStyle();

	HSSFFont headFont = workBook.createFont();
			
	headFont.setFontName(HSSFFont.FONT_ARIAL);
	headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	headFont.setColor(HSSFColor.BLACK.index);	
	headFont.setFontHeightInPoints((short) 11);

	cellHeadStyle.setWrapText(true);
	cellHeadStyle.setFont(headFont);
	cellHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	cellHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//	row = sheet.createRow(rowCount);
//	Updated for CR-118 QA-Fix

		final String[] columnHeadings = {"Order", "Spec Status", "Customer Name"};
		final int[] columnWidth = {10000,6000,10000};
		for (int intPos=0;intPos < 3; intPos++)
			{
			 createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,intPos,new HSSFRichTextString(columnHeadings[intPos]));
				 sheet.setColumnWidth(intPos,columnWidth[intPos]);
				 
			 }
	}
	
////Added for CR-134 for Clauses in orders report
	
	/***************************************************************************
	 * This Method is used to updateUserMarker 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	public ActionForward updateUserMarker(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws EMDException {
		
		LogUtil.logMessage("Enters into ModelAddClauseAction:updateUserMarker");
		String strForwardKey = ApplicationConstants.SUCCESS;
		
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
		LogUtil.logMessage(objLoginVo);
				
		ArrayList arlRevList = null;
		SectionVO objSectionVO = new SectionVO();
		ClauseVO objClauseVo = new ClauseVO();
		OrderVO objOrderVO = null;
		
		ModelAddClauseForm objModelAddClauseForm = (ModelAddClauseForm) objActionForm;
		
		int intClauseSeqNo = 0;
		int intVersionNo = 0;
		String strUserMarkFlag = null;
		int intUserMark = 0;
		
		try {
						
			if ((String) objHttpServletRequest.getParameter("clauseseqno") != null) {
				intClauseSeqNo = Integer.parseInt(objHttpServletRequest.getParameter("clauseseqno"));
				LogUtil.logMessage("intClauseSeqNo"+intClauseSeqNo);
			} 
			
			if ((String) objHttpServletRequest.getParameter("versionno") != null) {
				intVersionNo = Integer.parseInt(objHttpServletRequest.getParameter("versionno").toString());
				LogUtil.logMessage("intVersionNo"+intVersionNo);
			}
			
			if ((String) objHttpServletRequest.getParameter("usermarkerflag") != null) {
				strUserMarkFlag = objHttpServletRequest	.getParameter("usermarkerflag");
				LogUtil.logMessage("strUserMarkFlag"+strUserMarkFlag);
			}
			
			objClauseVo.setClauseSeqNo(intClauseSeqNo);
			objClauseVo.setVersionNo(intVersionNo);
			objClauseVo.setUserID(objLoginVo.getUserID());
			objClauseVo.setUserMarkFlag(strUserMarkFlag);//User Marker Flag
			
			LogUtil.logMessage("getMarkClaReason"+objModelAddClauseForm.getMarkClaReason());
			objClauseVo.setMarkClaReason(objModelAddClauseForm.getMarkClaReason());
			
			ModelClauseBI objModelClauseBo = ServiceFactory.getModelClauseBO();
			intUserMark = objModelClauseBo.updateUserMarker(objClauseVo);
			
			if (intUserMark == 0) {
				objModelAddClauseForm.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objModelAddClauseForm.setMessageID("" + intUserMark);
			}
			
			this.fetchClauseVersions(objActionMapping,objActionForm,objHttpServletRequest,objHttpServletResponse);
		} catch (Exception objEx) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		LogUtil.logMessage("strForwardKey"+strForwardKey);
		return objActionMapping.findForward(strForwardKey);
		
	}
		
	
	
	

}
