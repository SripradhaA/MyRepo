/* AK49339
 * Created on Aug 21, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.action.MasterMaintenance;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.record.formula.functions.Cell;
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
import com.EMD.LSDB.bo.serviceinterface.ChangeRequest1058BI;
import com.EMD.LSDB.bo.serviceinterface.ModelBI;
import com.EMD.LSDB.bo.serviceinterface.ModelCompBI;
import com.EMD.LSDB.bo.serviceinterface.ModelCompGroupBI;
import com.EMD.LSDB.bo.serviceinterface.OrderBI;
import com.EMD.LSDB.bo.serviceinterface.SpecTypeBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.form.MasterMaintenance.CompGroupMaintForm;
import com.EMD.LSDB.form.cr1058.SearchRequest1058Form;
import com.EMD.LSDB.vo.common.ChangeRequest1058VO;
import com.EMD.LSDB.vo.common.CompGroupVO;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.SpecTypeVo;
import com.EMD.LSDB.vo.common.SpecificationItemVO;
import com.EMD.LSDB.vo.common.SpecificationVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This Action class has the business methods for the Component
 *          Group
 * /***************************************************************************
 * ------------------------------------------------------------------------------------------------------
 *     Date            version       modify by                 comments                              Remarks 
 * 15/03/2011           1.0          SD41630           Added lines in the class             	Added for CR_97
 * 										
 * 										 
  **************************************************************************/


public class CompGroupMaintAction extends EMDAction {
	
	public ActionForward initLoadCompGroup(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		CompGroupMaintForm objCompGroupMaintForm = (CompGroupMaintForm) objActionForm;

		//Modified For CR_81 on 24-Dec-09 by RR68151
		LogUtil.logMessage("Entering CompGroupMaintAction:initLoadCompGroup");
		String strForwardKey = ApplicationConstants.SUCCESS;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		ArrayList arlSpec = new ArrayList();
		String strSpecTypeNo = null;
		int specTypeNo=-1;
		strSpecTypeNo = (String) objHttpServletRequest
		.getParameter("specTypeNo");
		try {
			//Added For CR_97 on 15 march 2011 by SD 41630 Start here 
			CompGroupVO objCompGroupVO = new CompGroupVO();
			objCompGroupVO.setUserID(objLoginVo.getUserID());
	
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
	if (strSpecTypeNo != null) {
		objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);
		specTypeNo = Integer.parseInt(strSpecTypeNo);
		objCompGroupMaintForm.setSpecTypeNo(specTypeNo);
	} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
		if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {
			strSpecTypeNo = (String) objSession
					.getAttribute("SPEC_TYPE_NO");
			specTypeNo = Integer.parseInt(strSpecTypeNo);
			objCompGroupMaintForm.setSpecTypeNo(specTypeNo);
		}
	}
		//	CR_97 code Lines ends here 
			
		
			
			//Added For CR_97 on 15 march 2011 by SD 41630 Start here 
			
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objCompGroupMaintForm.setSpecTypeList(arlSpec);
			}
			
			//	CR_97 code Lines ends here 
			ArrayList arlCompGrpType = new ArrayList();
			ModelCompGroupBI objModelCompGroupBO = ServiceFactory.getModelCompGroupBO();
			arlCompGrpType = objModelCompGroupBO.fetchCompGrpTypes(objCompGroupVO);
			if (arlCompGrpType.size() != 0) {
				objCompGroupMaintForm.setCompGroupTypeList(arlCompGrpType);
				}	
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
	 * This method is for fetching the component groups
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
	
	public ActionForward fetchCompGroups(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering CompGroupMaintAction.Search");
		String strForwardKey = ApplicationConstants.SUCCESS;
		CompGroupMaintForm objCompGroupMaintForm = (CompGroupMaintForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		int specTypeNo=-1;
		String strSpecTypeNo = null; 
		try {
			ArrayList arlSpec = new ArrayList();
			ArrayList arlCompGrps = new ArrayList();
			CompGroupVO objCompGroupVO = new CompGroupVO();
			objCompGroupVO.setUserID(objLoginVo.getUserID());
			//Added for CR_97 on march by sd41630 starts here
			objCompGroupVO.setSpecTypeSeqNo(objCompGroupMaintForm.getSpecTypeNo());
				
			strSpecTypeNo = (String) objHttpServletRequest
				.getParameter("specTypeNo");
		

		if (strSpecTypeNo != null) {
			objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);
			specTypeNo = Integer.parseInt(strSpecTypeNo);
			objCompGroupMaintForm.setSpecTypeNo(specTypeNo);
		} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
			if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {
				strSpecTypeNo = (String) objSession
						.getAttribute("SPEC_TYPE_NO");
				specTypeNo = Integer.parseInt(strSpecTypeNo);
				objCompGroupMaintForm.setSpecTypeNo(specTypeNo);
			}
		}
//		CR_97 lines ends here	
							
				
				//Added CR_97 on march by SD41630 starts here 
				SpecTypeVo objSpecTypeVo = new SpecTypeVo();
				objSpecTypeVo.setUserID(objLoginVo.getUserID());
				SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
				arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
				if (arlSpec.size() != 0) {
					objCompGroupMaintForm.setSpecTypeList(arlSpec);
				}
			ArrayList arlCompGrpType = new ArrayList();
			ModelCompGroupBI objModelCompGroupBO = ServiceFactory.getModelCompGroupBO();
			arlCompGrpType = objModelCompGroupBO.fetchCompGrpTypes(objCompGroupVO);
			if (arlCompGrpType.size() != 0) {
				objCompGroupMaintForm.setCompGroupTypeList(arlCompGrpType);
			}
			
			objCompGroupVO.setCompGrpTypeSeqNo(Integer.parseInt(objCompGroupMaintForm.
					getCompgrpCat()));

				
			arlCompGrps = objModelCompGroupBO.fetchCompGroups(objCompGroupVO);
			if (arlCompGrps != null && arlCompGrps.size() > 0) {
				objCompGroupMaintForm.setCompgroupList(arlCompGrps);
				objCompGroupMaintForm.setHdncompgrpCat(objCompGroupMaintForm
						.getHdncompgrpCat());
			
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
	 * This method is for inserting a new component group
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
	
	public ActionForward insertCompGroup(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException, ApplicationException {
		
		LogUtil.logMessage("Entering CompGroupMaintAction.insertCompGroup");
		String strForwardKey = ApplicationConstants.SUCCESS;
		CompGroupMaintForm objCompGroupMaintForm = (CompGroupMaintForm) objActionForm;
		String strUserID = null;
     //Added For CR_97 on 15 march 2011 by SD 41630 Start here 
		ArrayList arlSpec = new ArrayList();
		String strSpecTypeNo = null;
		int specTypeNo=-1;
		strSpecTypeNo = (String) objHttpServletRequest
		.getParameter("specTypeNo");
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			ArrayList arlCompGrps = new ArrayList();
			int intSuccess = 0;
			CompGroupVO objCompGroupVO = new CompGroupVO();
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			// Getting User from the session
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			specTypeNo=objCompGroupMaintForm.getSpecTypeNo();
					
			//Ends here CR_97
			strSpecTypeNo=Integer.toString(specTypeNo);
			objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);
			 objCompGroupVO.setSpecTypeSeqNo(specTypeNo);
				
			//Changed for CR_81
			//objCompGroupMaintForm.setHdncharzFlag(objCompGroupMaintForm
			//		.getCharzFlag());
			objCompGroupVO.setCompGrpTypeSeqNo(objCompGroupMaintForm.
					getCompGroupTypeSeqNo());
			//Changed for CR_81 - Ends here
			
			/*
			 * This Piece of code is added for Valid Component Group Flag Change
			 * Request
			 */
			//Updated for CR-127
			objCompGroupVO.setValidFlag("");
			
			/*
			 * ***************Ends Here
			 * ****************************************************
			 */
			objCompGroupVO.setComponentGroupName(ApplicationUtil
					.trim(objCompGroupMaintForm.getCompgrpName()));
			objCompGroupVO.setComments(ApplicationUtil
					.trim(objCompGroupMaintForm.getCompgrpDesc()));
			objCompGroupVO.setCharacterisationFlag(objCompGroupMaintForm
					.getCharzFlag());
			
			objCompGroupVO.setComponentGroupIdentifier(ApplicationUtil
					.trim(objCompGroupMaintForm.getCompgrpIdentifier()));
			//Change for LSDB_CR-77
			if(objCompGroupMaintForm.isDisplayCOCFlag()){
				objCompGroupVO.setDisplayInCOCFlag(true);
			}else{
				objCompGroupVO.setDisplayInCOCFlag(false);
			}
			// Ends
			objCompGroupVO.setUserID(strUserID);
			ModelCompGroupBI objModelCompGroupBI = ServiceFactory
			.getModelCompGroupBO();
			intSuccess = objModelCompGroupBI.insertCompGroup(objCompGroupVO);
			
			if (intSuccess == 0) {
				
				objCompGroupMaintForm.setCompgrpName("");
				objCompGroupMaintForm.setCompgrpDesc("");
				objCompGroupMaintForm.setCompgrpIdentifier("");
				objCompGroupMaintForm.setCharzFlag("");
				objCompGroupMaintForm.setValidFlag("");
				objCompGroupMaintForm.setDisplayCOCFlag(false);
				objCompGroupMaintForm.setCompGroupTypeSeqNo(0);//Added For CR_81
				// arlCompGrps=objModelCompGroupBI.fetchCompGroups(objCompGroupVO);
				objCompGroupMaintForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objCompGroupMaintForm.setMessageID("" + intSuccess);
				
			}
			
			objCompGroupVO = new CompGroupVO();
			objCompGroupVO.setUserID(objLoginVo.getUserID());
		
			ArrayList arlCompGrpType = new ArrayList();
			arlCompGrpType = objModelCompGroupBI.fetchCompGrpTypes(objCompGroupVO);
			if (arlCompGrpType.size() != 0) {
				objCompGroupMaintForm.setCompGroupTypeList(arlCompGrpType);
			}
			
			objCompGroupVO.setCompGrpTypeSeqNo(Integer.parseInt(objCompGroupMaintForm.
					getCompgrpCat()));
//			Added CR_97 on march 2011 by Sd41630 lines are strats here

			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objCompGroupMaintForm.setSpecTypeList(arlSpec);
			}
			
			
//			CR_97 lines Ends CR_97
			objCompGroupVO.setSpecTypeSeqNo(specTypeNo);//Added for CR_97
			arlCompGrps = objModelCompGroupBI.fetchCompGroups(objCompGroupVO);
			if (arlCompGrps != null && arlCompGrps.size() > 0) {
				objCompGroupMaintForm.setCompgroupList(arlCompGrps);
				objCompGroupMaintForm.setHdncompgrpCat(objCompGroupMaintForm
						.getHdncompgrpCat());
			}
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
	 * This method is for updating a component group
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
	
	public ActionForward updateCompGroup(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		LogUtil.logMessage("Entering CompGroupMaintAction.Modify");
		String strForwardKey = ApplicationConstants.SUCCESS;
		String strUserID = null;
//		Added For CR_97 on 15 march 2011 by SD 41630 Start here 
		ArrayList arlSpec = new ArrayList();
		String strSpecTypeNo = null;
		int specTypeNo=-1;
		strSpecTypeNo = (String) objHttpServletRequest
		.getParameter("specTypeNo");
		CompGroupMaintForm objCompGroupMaintForm = (CompGroupMaintForm) objActionForm;
		
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			ArrayList arlCompGrps = new ArrayList();
			int intSuccess = 0;
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			// Getting User from the session
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			CompGroupVO objCompGroupVO = new CompGroupVO();
			objCompGroupVO.setComponentgroupCat(objCompGroupMaintForm
					.getCompgrpCat());
			objCompGroupVO.setComponentGroupSeqNo(Integer
					.parseInt(objCompGroupMaintForm.getComponentGroupSeqNo()));
			objCompGroupVO.setComponentGroupName(ApplicationUtil
					.trim(objCompGroupMaintForm.getHdncompgrpName()));
			objCompGroupVO.setComments(ApplicationUtil
					.trim(objCompGroupMaintForm.getHdncompgrpDesc()));
			objCompGroupVO.setComponentGroupIdentifier(ApplicationUtil
					.trim(objCompGroupMaintForm.getHdncompgrpIdentifier()));
			/*Modified for CR_81 by RR68151
			objCompGroupVO.setCharacterisationFlag(objCompGroupMaintForm
					.getHdncharacterisationFlag());*/
			objCompGroupVO.setCompGrpTypeSeqNo(objCompGroupMaintForm.
					getHdncompGroupTypeSeqNo());
			//Modified for CR_81 Ends here
			/*
			 * This Piece of code is added for Valid Component Group Flag Change
			 * Request
			 */
			//Added for CR_97
			specTypeNo=objCompGroupMaintForm.getSpecTypeNo();
			//CR_97 Ends here
			//Updated for CR-127
			objCompGroupVO
			.setValidFlag("");
			
			//Change for LSDB_CR-77
			if(objCompGroupMaintForm.isHdnDisplayInCOC()){
				objCompGroupVO.setDisplayInCOCFlag(true);
				
			}else{
				objCompGroupVO.setDisplayInCOCFlag(false);
			}
			//Ends
			
			/*
			 * ***************Ends Here
			 * ****************************************************
			 */
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			if (strSpecTypeNo != null) {
				objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);
				specTypeNo = Integer.parseInt(strSpecTypeNo);
				objCompGroupMaintForm.setSpecTypeNo(specTypeNo);
			} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
				if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {
					strSpecTypeNo = (String) objSession
							.getAttribute("SPEC_TYPE_NO");
					specTypeNo = Integer.parseInt(strSpecTypeNo);
					objCompGroupMaintForm.setSpecTypeNo(specTypeNo);
				}
			}
				//	CR_97 code Lines ends here 
					
				
					
					//Added For CR_97 on 15 march 2011 by SD 41630 Start here 
					
					objSpecTypeVo.setUserID(objLoginVo.getUserID());
					SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
					arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
					if (arlSpec.size() != 0) {
						objCompGroupMaintForm.setSpecTypeList(arlSpec);
					}
					
					//	CR_97 code Lines ends here 
			
			objCompGroupVO.setUserID(strUserID);
			ModelCompGroupBI objModelCompGroupBI = ServiceFactory
			.getModelCompGroupBO();
			intSuccess = objModelCompGroupBI.updateCompGroup(objCompGroupVO);
			
			if (intSuccess == 0) {
				
				objCompGroupMaintForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objCompGroupMaintForm.setMessageID("" + intSuccess);
			}
			
			CompGroupVO objjCompGroupVO = new CompGroupVO();
			objjCompGroupVO.setUserID(objLoginVo.getUserID());
			
			//objjCompGroupVO.setComponentgroupCat(objCompGroupMaintForm
			//		.getCompgrpCat());
			ModelCompGroupBI objModelCompGroupBO = ServiceFactory
			.getModelCompGroupBO();
			
			//Modified For CR_81 on 24-Dec-09 by RR68151
			ArrayList arlCompGrpType = new ArrayList();
			arlCompGrpType = objModelCompGroupBO.fetchCompGrpTypes(objjCompGroupVO);
			if (arlCompGrpType.size() != 0) {
				objCompGroupMaintForm.setCompGroupTypeList(arlCompGrpType);
			}
			
			objjCompGroupVO.setCompGrpTypeSeqNo(Integer.parseInt(objCompGroupMaintForm.
					getCompgrpCat()));
			//Modified For CR_81 on 24-Dec-09 by RR68151 - Ends here
			objjCompGroupVO.setSpecTypeSeqNo(specTypeNo);//Added for CR_97
			arlCompGrps = objModelCompGroupBO.fetchCompGroups(objjCompGroupVO);
			if (arlCompGrps != null && arlCompGrps.size() > 0) {
				objCompGroupMaintForm.setCompgroupList(arlCompGrps);
				objCompGroupMaintForm.setHdncompgrpCat(objCompGroupMaintForm
						.getHdncompgrpCat());
				LogUtil.logMessage("list"
						+ objCompGroupMaintForm.getCompgroupList());
				// objCompGroupMaintForm.setHdncharacterisationFlag(objCompGroupMaintForm.isHdncharacterisationFlag());
				LogUtil.logMessage("Component group List Size .."
						+ arlCompGrps.size());
			}
			/*
			 * objCompGroupMaintForm.setCompgrpName(ApplicationUtil.trim((objCompGroupVO.getComponentGroupName())));
			 * objCompGroupMaintForm.setCompgrpDesc(ApplicationUtil.trim((objCompGroupVO.getComments())));
			 * objCompGroupMaintForm.setCompgrpIdentifier(ApplicationUtil.trim((objCompGroupVO.getComponentGroupIdentifier())));
			 * objCompGroupMaintForm.setHdncharzFlag(objCompGroupMaintForm.getCharzFlag());
			 */
		} catch (Exception objEx) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForwardKey);
		
	}
	
	
	/***************************************************************************
	 * Added for LSDB_CR_67 by ka57588
	 * This method is for deleting a component group
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws ApplicationException
	 **************************************************************************/
	
	public ActionForward deleteCompGroup(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		LogUtil.logMessage("Entering CompGroupMaintAction.deleteCompGroup");
		String strForwardKey = ApplicationConstants.SUCCESS;
		String strUserID = null;
//		Added For CR_97 on 15 march 2011 by SD 41630 Start here 
		ArrayList arlSpec = new ArrayList();
		String strSpecTypeNo = null;
		int specTypeNo=-1;
		strSpecTypeNo = (String) objHttpServletRequest
		.getParameter("specTypeNo");
		CompGroupMaintForm objCompGroupMaintForm = (CompGroupMaintForm) objActionForm;
		
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			ArrayList arlCompGrps = new ArrayList();
			int intSuccess = 0;
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			// Getting User from the session
			if (objLoginVo != null) {
				strUserID = objLoginVo.getUserID();
			}
			CompGroupVO objCompGroupVO = new CompGroupVO();
			//Added for CR_97
			specTypeNo=objCompGroupMaintForm.getSpecTypeNo();
			//CR_97 Ends here
			
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			if (strSpecTypeNo != null) {
				objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);
				specTypeNo = Integer.parseInt(strSpecTypeNo);
				objCompGroupMaintForm.setSpecTypeNo(specTypeNo);
			} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
				if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {
					strSpecTypeNo = (String) objSession
							.getAttribute("SPEC_TYPE_NO");
					specTypeNo = Integer.parseInt(strSpecTypeNo);
					objCompGroupMaintForm.setSpecTypeNo(specTypeNo);
				}
			}
				//	CR_97 code Lines ends here 
					
				
					
					//Added For CR_97 on 15 march 2011 by SD 41630 Start here 
					
					objSpecTypeVo.setUserID(objLoginVo.getUserID());
					SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
					arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
					if (arlSpec.size() != 0) {
						objCompGroupMaintForm.setSpecTypeList(arlSpec);
					}
					
					//	CR_97 code Lines ends here 
			
			
			objCompGroupVO.setComponentgroupCat(objCompGroupMaintForm
					.getCompgrpCat());
			objCompGroupVO.setComponentGroupSeqNo(Integer
					.parseInt(objCompGroupMaintForm.getComponentGroupSeqNo()));
			objCompGroupVO.setUserID(strUserID);
			ModelCompGroupBI objModelCompGroupBI = ServiceFactory
			.getModelCompGroupBO();
			intSuccess = objModelCompGroupBI.deleteCompGroup(objCompGroupVO);
			
			if (intSuccess == 0) {
				
				objCompGroupMaintForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objCompGroupMaintForm.setMessageID("" + intSuccess);
			}
			
			CompGroupVO objjCompGroupVO = new CompGroupVO();
			objjCompGroupVO.setUserID(objLoginVo.getUserID());
			
			//objjCompGroupVO.setComponentgroupCat(objCompGroupMaintForm
			//		.getCompgrpCat());
			ModelCompGroupBI objModelCompGroupBO = ServiceFactory
			.getModelCompGroupBO();
			
			//Modified For CR_81 on 24-Dec-09 by RR68151
			ArrayList arlCompGrpType = new ArrayList();
			arlCompGrpType = objModelCompGroupBO.fetchCompGrpTypes(objjCompGroupVO);
			if (arlCompGrpType.size() != 0) {
				objCompGroupMaintForm.setCompGroupTypeList(arlCompGrpType);
			}
			
			objjCompGroupVO.setCompGrpTypeSeqNo(Integer.parseInt(objCompGroupMaintForm.
					getCompgrpCat()));
			//Modified For CR_81 on 24-Dec-09 by RR68151 - Ends here
			objjCompGroupVO.setSpecTypeSeqNo(specTypeNo);//Added for CR_97
			arlCompGrps = objModelCompGroupBO.fetchCompGroups(objjCompGroupVO);
			if (arlCompGrps != null && arlCompGrps.size() > 0) {
				objCompGroupMaintForm.setCompgroupList(arlCompGrps);
				objCompGroupMaintForm.setHdncompgrpCat(objCompGroupMaintForm
						.getHdncompgrpCat());
				LogUtil.logMessage("list"
						+ objCompGroupMaintForm.getCompgroupList());
				LogUtil.logMessage("Component group List Size .."
						+ arlCompGrps.size());
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
	 * This method is for fetching the component groups for report Added for
	 * CR-26
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
	
	public ActionForward fetchCompGrpReport(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering CompGroupMaintAction.fetchCompGrpReport");
		String strForwardKey = ApplicationConstants.COMP_GRP_REPORT;
		CompGroupMaintForm objCompGroupMaintForm = (CompGroupMaintForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		int specTypeNo=0;
		//Added for CR_101 to include order level components
		boolean includeOrderComp=false;
		String ordComp="M"; 
		//String strSpecTypeNo=null;
		//CR_101 Ends here
		ArrayList arlSpec = null;
		ArrayList arlModelList = new ArrayList();
		String hdnSelectedMdlNames=null;
		
		try {
			//Added FOr CR_104
			ModelVo objModelVo = new ModelVo();
			ModelBI objModelBo = ServiceFactory.getModelBO();
			
			ArrayList arlCompGrps = new ArrayList();
			
			CompGroupVO objCompGroupVO = new CompGroupVO();
			objCompGroupVO.setUserID(objLoginVo.getUserID());
			LogUtil.logMessage("Entering CompGroupMaintAction.fetchCompGrpReport objCompGroupMaintForm.getHdnSelectedMdlNames()"+
						objCompGroupMaintForm.getHdnSelectedMdlNames());
			
			hdnSelectedMdlNames=objCompGroupMaintForm.getHdnSelectedMdlNames();
			
			//Added For CR_97 on march 2011 by SD41630	lines starts here
			
			
			specTypeNo=objCompGroupMaintForm.getSpecTypeNo();
			LogUtil.logMessage("Entering CompGroupMaintAction.fetchCompGrpReport specTypeNo"+specTypeNo);
			if(specTypeNo!=0){
				objCompGroupVO.setSpecTypeSeqNo(objCompGroupMaintForm.getSpecTypeNo());
			}else{
				objCompGroupVO.setSpecTypeSeqNo(specTypeNo);
			}
			
			
			
			
			// Added for CR_101 To include order level components in Component Group / Component report
			includeOrderComp=objCompGroupMaintForm.isIncludeOrderFlag();
			if(includeOrderComp)
			{	objCompGroupVO.setIncOrderComp(null);	
				//added for CR_104 fix
				objCompGroupMaintForm.setIncludeOrderONOFF("Yes");
				objCompGroupMaintForm.setHdnIncludeOrderFlag(true);
			}
			else
			{	objCompGroupVO.setIncOrderComp(ordComp);
				objCompGroupMaintForm.setIncludeOrderONOFF("No");
				objCompGroupMaintForm.setHdnIncludeOrderFlag(false);
			}	
			//CR_101 Ends here
			//AddedFor CR_104
			if(objCompGroupMaintForm
					.getModelSeqNos()!=null){
			objCompGroupVO.setModelSelected(objCompGroupMaintForm
					.getModelSeqNos());
			LogUtil.logMessage("Entering CompGroupMaintAction.fetchCompGrpReport getModelSeqNos()"+objCompGroupMaintForm.getModelSeqNos());
		}
				//Lines Ends here CR_97
			LogUtil.logMessage("Entering CompGroupMaintAction.fetchCompGrpReport getModelSeqNos() is NULL ==="+objCompGroupMaintForm.getModelSeqNos());
					SpecTypeVo objSpecTypeVo = new SpecTypeVo();
					objSpecTypeVo.setUserID(objLoginVo.getUserID());
					SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
					arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
					if (arlSpec.size() != 0) {
						objCompGroupMaintForm.setSpecTypeList(arlSpec);
					}
			//comment for CR_104
			ModelCompGroupBI objModelCompGroupBO = ServiceFactory.getModelCompGroupBO();
			arlCompGrps = objModelCompGroupBO.fetchCompGrpReport(objCompGroupVO);
			if (arlCompGrps != null && arlCompGrps.size() > 0) {
				objCompGroupMaintForm.setCompgroupList(arlCompGrps);
			}else{
				objCompGroupMaintForm
				.setMessageID(ApplicationConstants.NO_RECORD_MESSAGE_ID);
			}
					
//					Added For CR_104
					/** To fetch Models for a specification type. */
					objModelVo
					.setSpecTypeSeqNo(objCompGroupMaintForm.getSpecTypeNo());
					/** The list of all models present in the database is obtained. */
					arlModelList = objModelBo.fetchModels(objModelVo);
					
					if (arlModelList != null && arlModelList.size() > 0) {
						objCompGroupMaintForm.setModelList(arlModelList);
					}
					
					/** The obtained list of models is set in the action form. */
					objCompGroupMaintForm.setHdnSelSpecType(objCompGroupMaintForm
							.getHdnSelSpecType());
										
			
		} catch (Exception objEx) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForwardKey);
	}
	
	/***************************************************************************
	 * This method is for fetching the component groups for report in Excel
	 * Added for CR-26
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
	
	public ActionForward fetchCompGrpReportExcel(
			ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering CompGroupMaintAction.fetchCompGrpReportExcel");
		String strForwardKey = ApplicationConstants.COMP_GRP_REPORT_EXCEL;
		CompGroupMaintForm objCompGroupMaintForm = (CompGroupMaintForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		int specTypeNo=0;
		ArrayList arlSpec = null;
		String strSpecTypeNo = null;
		//strSpecTypeNo = (String) objHttpServletRequest.getParameter("specTypeNo");
		//Added for CR_101 to include order level components
		//Added For CR_104 

		String orderComp="M";
		
		//String incOrderComp = (String) objHttpServletRequest.getParameter("incOrdFlag");
		//String strSelModelNos = (String) objHttpServletRequest.getParameter("strSelModelNos");
		//String strSelModelNames = (String) objHttpServletRequest.getParameter("strSelModelNames");
		//LogUtil.logMessage("Entering CompGroupMaintAction.fetchCompGrpReportExcel incOrderComp"+incOrderComp);
		boolean includeOrderComp=false;
	//	int [] arlMdlSeqNoArry=null;
		
			try {
			
			//specTypeNo = Integer.parseInt(strSpecTypeNo);
			//objCompGroupMaintForm.setSpecTypeNo(specTypeNo);
			ArrayList arlCompGrps = new ArrayList();
			
			CompGroupVO objCompGroupVO = new CompGroupVO();
			objCompGroupVO.setUserID(objLoginVo.getUserID());
			
				//Added For CR_97 on march 2011 by SD41630	lines starts here
			specTypeNo=objCompGroupMaintForm.getSpecTypeNo();
			objCompGroupVO.setSpecTypeSeqNo(specTypeNo);
				//Added for CR_101 to include order level component
			//	LogUtil.logMessage("Entering CompGroupMaintAction.fetchCompGrpReportExcel strSelModelNos"+strSelModelNos);
				/*if(strSelModelNos!=null && strSelModelNos!="")
				arlMdlSeqNoArry =ApplicationUtil.convertStrWithCommaSepTointArray(strSelModelNos);
				*/
//										
				/*
				if(objCompGroupMaintForm
						.getModelSeqNos()==null){
				objCompGroupVO.setModelSelected(arlMdlSeqNoArry);
				}else{
					objCompGroupVO.setModelSelected(objCompGroupMaintForm
							.getModelSeqNos());
				}
				LogUtil.logMessage("Entering CompGroupMaintAction.fetchCompGrpReportExcel getModelSeqNos()"+objCompGroupVO.getModelSelected());
				 //	Added for CR_101 To include order level components in Component Group / Component report
				if("Y".equalsIgnoreCase(incOrderComp))
				{	objCompGroupVO.setIncOrderComp(null);
				
				includeOrderComp=true;
				
				}
				else
				{	objCompGroupVO.setIncOrderComp(orderComp);
				includeOrderComp=false;
				}	*/
				//CR_101 Ends here			
				LogUtil.logMessage("The value current flag :----------->"+objCompGroupVO.getIncOrderComp());	

				includeOrderComp=objCompGroupMaintForm.isIncludeOrderFlag();
				if(includeOrderComp)
				{	objCompGroupVO.setIncOrderComp(null);	}
				else
				{	objCompGroupVO.setIncOrderComp(orderComp);	}	
				//CR_101 Ends here
				//AddedFor CR_104
				if(objCompGroupMaintForm
						.getModelSeqNos()!=null){
				objCompGroupVO.setModelSelected(objCompGroupMaintForm
						.getModelSeqNos());
				
			}
				
			ModelCompGroupBI objModelCompGroupBO = ServiceFactory
			.getModelCompGroupBO();
			arlCompGrps = objModelCompGroupBO
			.fetchCompGrpReport(objCompGroupVO);
			if (arlCompGrps != null && arlCompGrps.size() > 0) {
				objCompGroupMaintForm.setCompgroupList(arlCompGrps);
											
				
			}
			objCompGroupMaintForm.setHdnSelSpecTypeNo(specTypeNo);
			objCompGroupMaintForm.setHdnIncludeOrderFlag(includeOrderComp);
			objCompGroupMaintForm.setHdnSelectedMdlNames(objCompGroupMaintForm.getSelectedMdlNames());
			
			
		} catch (Exception objEx) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForwardKey);
	}
	
//Added For CR_104 
	/***************************************************************************
	 * This method is for fetching the model for report Added for
	 * CR-104
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
	
	public ActionForward initLoadSpecTypesAndModels(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering CompGroupMaintAction.initLoadModels");
		String strForwardKey = ApplicationConstants.COMP_GRP_REPORT;
		CompGroupMaintForm objCompGroupMaintForm = (CompGroupMaintForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		int specTypeNo=0;
		//Added for CR_101 to include order level components
		boolean includeOrderComp=false;
		String ordComp="M"; 
		//String strSpecTypeNo=null;
		//CR_101 Ends here
		ArrayList arlSpec = null;
		ArrayList arlModelList = new ArrayList();
		String hdnSelectedMdlNames=null;
		
		try {
			//Added FOr CR_104
			ModelVo objModelVo = new ModelVo();
			ModelBI objModelBo = ServiceFactory.getModelBO();
			
			ArrayList arlCompGrps = new ArrayList();
			
			CompGroupVO objCompGroupVO = new CompGroupVO();
			objCompGroupVO.setUserID(objLoginVo.getUserID());
			LogUtil.logMessage("Entering CompGroupMaintAction.initLoadModels objCompGroupMaintForm.getHdnSelectedMdlNames()"+
						objCompGroupMaintForm.getHdnSelectedMdlNames());
			
			hdnSelectedMdlNames=objCompGroupMaintForm.getHdnSelectedMdlNames();
			
			//Added For CR_97 on march 2011 by SD41630	lines starts here
			
			
			specTypeNo=objCompGroupMaintForm.getSpecTypeNo();
			LogUtil.logMessage("Entering CompGroupMaintAction.initLoadModels specTypeNo"+specTypeNo);
			if(specTypeNo!=0){
				objCompGroupVO.setSpecTypeSeqNo(objCompGroupMaintForm.getSpecTypeNo());
			}else{
				objCompGroupVO.setSpecTypeSeqNo(specTypeNo);
			}
			
			
			
			
			// Added for CR_101 To include order level components in Component Group / Component report
			includeOrderComp=objCompGroupMaintForm.isIncludeOrderFlag();
			if(includeOrderComp)
			{	objCompGroupVO.setIncOrderComp(null);	}
			else
			{	objCompGroupVO.setIncOrderComp(ordComp);	}	
			//CR_101 Ends here
			//AddedFor CR_104
			if(objCompGroupMaintForm
					.getModelSeqNos()!=null){
			objCompGroupVO.setModelSelected(objCompGroupMaintForm
					.getModelSeqNos());
			LogUtil.logMessage("Entering CompGroupMaintAction.initLoadModels getModelSeqNos()"+objCompGroupMaintForm.getModelSeqNos());
		}
				//Lines Ends here CR_97
			LogUtil.logMessage("Entering CompGroupMaintAction.initLoadModels getModelSeqNos() is NULL ==="+objCompGroupMaintForm.getModelSeqNos());
					SpecTypeVo objSpecTypeVo = new SpecTypeVo();
					objSpecTypeVo.setUserID(objLoginVo.getUserID());
					SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
					arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
					if (arlSpec.size() != 0) {
						objCompGroupMaintForm.setSpecTypeList(arlSpec);
					}
			//comment for CR_104
			/*ModelCompGroupBI objModelCompGroupBO = ServiceFactory.getModelCompGroupBO();
			arlCompGrps = objModelCompGroupBO.fetchCompGrpReport(objCompGroupVO);
			if (arlCompGrps != null && arlCompGrps.size() > 0) {
				objCompGroupMaintForm.setCompgroupList(arlCompGrps);
			}*/
					
//					Added For CR_104
					/** To fetch Models for a specification type. */
					objModelVo
					.setSpecTypeSeqNo(objCompGroupMaintForm.getSpecTypeNo());
					/** The list of all models present in the database is obtained. */
					arlModelList = objModelBo.fetchModels(objModelVo);
					
					if (arlModelList != null && arlModelList.size() > 0) {
						objCompGroupMaintForm.setModelList(arlModelList);
					}else{
						objCompGroupMaintForm
						.setMessageID(ApplicationConstants.NO_RECORD_MESSAGE_ID);
					}
					
					/** The obtained list of models is set in the action form. */
					objCompGroupMaintForm.setHdnSelSpecType(objCompGroupMaintForm
							.getHdnSelSpecType());
										
			
		} catch (Exception objEx) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForwardKey);
	}
	
	//Added For CR_108 
	/***************************************************************************
	 * This method is for fetching the Models, Component Groups, Components CR-108
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
	
	public ActionForward initLoadCompInOrder(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering CompGroupMaintAction.initLoadCompInOrder");
		String strForwardKey = ApplicationConstants.COMP_IN_ORDER_REPORT;
		CompGroupMaintForm objCompGroupMaintForm = (CompGroupMaintForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		int specTypeNo=0;
		ArrayList arlSpec = null;
		ArrayList arlModelList = new ArrayList();
		
		try {
			ArrayList arlCompGrps = new ArrayList();
			ArrayList arlComps = new ArrayList();
			ModelVo objModelVo = new ModelVo();
			ModelBI objModelBo = ServiceFactory.getModelBO();
						
			CompGroupVO objCompGroupVO = new CompGroupVO();
			ComponentVO objCompVO = new ComponentVO();
			objCompGroupVO.setUserID(objLoginVo.getUserID());
			objCompVO.setUserID(objLoginVo.getUserID());
						
			if(objCompGroupMaintForm.getSpecTypeNo() != 0){
				objCompGroupVO.setSpecTypeSeqNo(objCompGroupMaintForm.getSpecTypeNo());
			}else{
				objCompGroupVO.setSpecTypeSeqNo(specTypeNo);
			}
			
			if(objCompGroupMaintForm
					.getModelSeqNo()!=0){
				objCompGroupVO.setModelSeqNo(objCompGroupMaintForm
					.getModelSeqNo());			
			}else{
				objCompGroupVO.setModelSeqNo(-1);	
			}
	
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objCompGroupMaintForm.setSpecTypeList(arlSpec);
			}
			
			objModelVo.setSpecTypeSeqNo(objCompGroupMaintForm.getSpecTypeNo());
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null && arlModelList.size() > 0) {
				objCompGroupMaintForm.setModelList(arlModelList);
			}else{
				objCompGroupMaintForm
				.setMessageID(ApplicationConstants.NO_RECORD_MESSAGE_ID);
			}
			
			ModelCompBI objModelCompBO = ServiceFactory.getModelCompBO();

			if(objCompGroupMaintForm.getModelSeqNo() == 0 || objCompGroupMaintForm.getModelSeqNo() == -1) {
				
				ModelCompGroupBI objModelCompGroupBO = ServiceFactory.getModelCompGroupBO();
				arlCompGrps = objModelCompGroupBO.fetchCompGroups(objCompGroupVO);
				if (arlCompGrps != null && arlCompGrps.size() > 0) {
					objCompGroupMaintForm.setCompgroupList(arlCompGrps);				
					}
				
				if(objCompGroupMaintForm.getCompGroupSeqNo() != 0)	{
					objCompVO.setComponentGroupSeqNo(objCompGroupMaintForm.getCompGroupSeqNo());
					arlComps = objModelCompBO.fetchComps(objCompVO);
					if (arlComps != null && arlComps.size() > 0) {
						objCompGroupMaintForm.setCompList(arlComps);			
					}
				}
				
			}else {
				objCompVO.setModelSeqNo(objCompGroupMaintForm.getModelSeqNo());
				
				arlCompGrps = objModelCompBO.fetchModelComps(objCompVO);				
				if (arlCompGrps != null && arlCompGrps.size() > 0) {
					objCompGroupMaintForm.setCompgroupList(arlCompGrps);
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

	/***************************************************************************
	 * This method is for fetching the components in Orders Added for CR-108
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
	
	/*public ActionForward fetchCompInOrderReport(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering CompGroupMaintAction.fetchCompInOrderReport");
		String strForwardKey = ApplicationConstants.COMP_IN_ORDER_REPORT;
		CompGroupMaintForm objCompGroupMaintForm = (CompGroupMaintForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		int specTypeNo=0;
		ArrayList arlSpec = null;
		ArrayList arlModelList = new ArrayList();
		
		try {
			String forwardToExcel = objHttpServletRequest.getParameter("Excel");
			if (forwardToExcel.equalsIgnoreCase("Y"))
				strForwardKey = ApplicationConstants.COMP_IN_ORDER_EXCEL;
			ArrayList arlCompGrps = new ArrayList();
			ArrayList arlComps = new ArrayList();
			ModelVo objModelVo = new ModelVo();
			ModelBI objModelBo = ServiceFactory.getModelBO();
						
			CompGroupVO objCompGroupVO = new CompGroupVO();
			ComponentVO objCompVO = new ComponentVO();
			objCompGroupVO.setUserID(objLoginVo.getUserID());
			objCompVO.setUserID(objLoginVo.getUserID());
						
			if(objCompGroupMaintForm.getSpecTypeNo() != 0){
				objCompGroupVO.setSpecTypeSeqNo(objCompGroupMaintForm.getSpecTypeNo());
			}else{
				objCompGroupVO.setSpecTypeSeqNo(specTypeNo);
			}
			
			if(objCompGroupMaintForm
					.getModelSeqNo()!=0){
				objCompGroupVO.setModelSeqNo(objCompGroupMaintForm
					.getModelSeqNo());			
			}else{
				objCompGroupVO.setModelSeqNo(-1);	
			}
	
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objCompGroupMaintForm.setSpecTypeList(arlSpec);
			}
			
			objModelVo.setSpecTypeSeqNo(objCompGroupMaintForm.getSpecTypeNo());
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			if (arlModelList != null && arlModelList.size() > 0) {
				objCompGroupMaintForm.setModelList(arlModelList);
			}else{
				objCompGroupMaintForm
				.setMessageID(ApplicationConstants.NO_RECORD_MESSAGE_ID);
			}

			ModelCompGroupBI objModelCompGroupBO = ServiceFactory.getModelCompGroupBO();
			ModelCompBI objModelCompBO = ServiceFactory.getModelCompBO();
			//Added for CR-121 for sorting by Vb106565 starts here 		
			 objCompGroupVO.setOrderbyCompgrpFlag(objCompGroupMaintForm.getOrderbyCompGroupFlag()); 
			 if (objCompGroupMaintForm.getOrderbyCompGroupFlag() == 0){
				 objCompGroupMaintForm.setColumnheader("CompGroup");
				 objCompGroupMaintForm.setOrderbyCompGroupFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
			 }				 
			 objCompGroupVO.setOrderbyCompFlag(objCompGroupMaintForm.getOrderbyCompFlag());
			 //Added for CR-121 for sorting by Vb106565 ends here  

			if(objCompGroupMaintForm.getModelSeqNo() == 0 || objCompGroupMaintForm.getModelSeqNo() == -1) {
				
				arlCompGrps = objModelCompGroupBO.fetchCompGroups(objCompGroupVO);
				if (arlCompGrps != null && arlCompGrps.size() > 0) {
					objCompGroupMaintForm.setCompgroupList(arlCompGrps);				
					}
				
				if(objCompGroupMaintForm.getCompGroupSeqNo() != 0)	{
					objCompVO.setComponentGroupSeqNo(objCompGroupMaintForm.getCompGroupSeqNo());
					arlComps = objModelCompBO.fetchComps(objCompVO);
					if (arlComps != null && arlComps.size() > 0) {
						objCompGroupMaintForm.setCompList(arlComps);			
					}
				}
				
			}else {
				objCompVO.setModelSeqNo(objCompGroupMaintForm.getModelSeqNo());
				int[] modelSeqNos = new int[1];
				modelSeqNos[0]=objCompGroupMaintForm.getModelSeqNo();
				objCompGroupVO.setModelSelected(modelSeqNos);
				
				arlCompGrps = objModelCompBO.fetchModelComps(objCompVO);				
				if (arlCompGrps != null && arlCompGrps.size() > 0) {
					objCompGroupMaintForm.setCompgroupList(arlCompGrps);
				}

			}

			if(objCompGroupMaintForm.getCompGroupSeqNo() != -1 && objCompGroupMaintForm.getCompGroupSeqNo() != 0){
				objCompGroupVO.setComponentGroupSeqNo(objCompGroupMaintForm.getCompGroupSeqNo());
				objCompGroupVO.setComponentSeqNo(objCompGroupMaintForm.getComponentSeqNo());
			}
			
			if(objCompGroupMaintForm.isShowLatSpecFlag()){
				objCompGroupMaintForm.setHdnShowLatSpecFlag("Yes");	
				objCompGroupVO.setShowLatestPubSpecFlag(ApplicationConstants.YES);
			}
			else{
				objCompGroupMaintForm.setHdnShowLatSpecFlag("No");		
				objCompGroupVO.setShowLatestPubSpecFlag(ApplicationConstants.NO);
			}
			
			arlCompGrps = objModelCompGroupBO.fetchCompGrpReport(objCompGroupVO);
			if (arlCompGrps != null && arlCompGrps.size() > 0) {
				objCompGroupMaintForm.setCompInOrdList(arlCompGrps);
			}else{
				objCompGroupMaintForm
				.setMessageID(ApplicationConstants.NO_RECORD_MESSAGE_ID);
			}
			
		} catch (Exception objEx) {
			
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForwardKey);
	}*/
	
	//Added for CR-121
	/***************************************************************************
	 * This method is for View Seleceted Summary Report for 1058
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws BusinessException
	 * @author VV49326
	 **************************************************************************/
	public ActionForward viewCmpInOrdMap(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws ApplicationException {
		LogUtil.logMessage("Inside the CompGroupMaintAction:viewCmpInOrdMap");
		String strForwardKey = ApplicationConstants.VIEW_COMP_IN_ORDER_MAP_REPORT;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		
		CompGroupVO objCompGroupVO = new CompGroupVO();
		OrderVO objOrderVO = new OrderVO();
		CompGroupMaintForm objCompGroupMaintForm = (CompGroupMaintForm)objActionForm;
		 
		try {
			ModelCompGroupBI objModelCompGroupBO = ServiceFactory.getModelCompGroupBO();
			OrderBI objOrderBO = ServiceFactory.getOrderBO();
			
			String[] orderKeys = null;
						
			orderKeys = objHttpServletRequest.getParameterValues("orderKey");
			
			objCompGroupVO.setOrderKeys(orderKeys);
			objCompGroupVO.setDataLocationType("S");
			objCompGroupVO.setUserID(objLoginVo.getUserID());
			
			objOrderVO.setOrderKeys(orderKeys);
			objOrderVO.setDataLocTypeCode("S");
			objOrderVO.setUserID(objLoginVo.getUserID());
			
			/*
			LogUtil.logMessage("objOrderBO.fetchMultipleOrders(objOrderVO):"+
					objOrderBO.fetchMultipleOrders(objOrderVO));
			LogUtil.logMessage("objModelCompGroupBO.viewCompInOrdMap(objCompGroupVO):"+
								objModelCompGroupBO.viewCompInOrdMap(objCompGroupVO));
			LogUtil.logMessage("objModelCompGroupBO.viewCompGrpInOrdMap(objCompGroupVO):"+
								objModelCompGroupBO.viewCompGrpInOrdMap(objCompGroupVO));*/
			
			objCompGroupMaintForm.setOrderList(objOrderBO.fetchMultipleOrders(objOrderVO));
			objCompGroupMaintForm.setSpecItemList(objModelCompGroupBO.viewCompInOrdMap(objCompGroupVO));
			objCompGroupMaintForm.setCompgroupList(objModelCompGroupBO.viewCompGrpInOrdMap(objCompGroupVO));
			
			
		}catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in CompGroupMaintAction..");
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
	 * This method is for fetching the Models, Component Groups, Components CR-108
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
	
	public ActionForward exportCompinOrdMap(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering CompGroupMaintAction.exportCompinOrdMap");
		String strForwardKey = ApplicationConstants.SUCCESS;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		int rowCount=0;
		int prevRowCount=0;
		int itemCount=0;
		int intcol=0;
		int intPos=0;
		
		CompGroupVO objCompGroupVO = new CompGroupVO();
		SpecificationVO objSpecificationVO =new SpecificationVO();
		SpecificationItemVO objSpecificationItemVO =new SpecificationItemVO();
		ComponentVO objComponentVO =new ComponentVO();
		OrderVO objOrderVO = new OrderVO();
		
		try {
			
			//create workbook
			HSSFWorkbook workBook = new HSSFWorkbook();
			HSSFSheet sheet = workBook.createSheet("Components In Orders Map Report");//create sheet
			
			HSSFCellStyle cellHeadStyle = workBook.createCellStyle();
			HSSFFont headFont = workBook.createFont();
			
			headFont.setFontName(HSSFFont.FONT_ARIAL);
			headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			headFont.setColor(HSSFColor.BLACK.index);	
			headFont.setFontHeightInPoints((short) 10);
			
			cellHeadStyle.setWrapText(false);
			cellHeadStyle.setFont(headFont);
			cellHeadStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			//cellHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
			HSSFPalette palette = ((HSSFWorkbook)workBook).getCustomPalette(); 
			palette.setColorAtIndex((short)57, (byte)210, (byte)221, (byte)249); 
			cellHeadStyle.setFillForegroundColor(palette.getColor(57).getIndex()); 
			cellHeadStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
			
			HSSFCellStyle cellStyle = workBook.createCellStyle();										
			
			HSSFFont Font = workBook.createFont();			
			Font.setFontName(HSSFFont.FONT_ARIAL);
			Font.setColor(HSSFColor.BLACK.index);		
			Font.setFontHeightInPoints((short) 10);
			
			cellStyle.setFont(Font);
			cellStyle.setWrapText(true);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			//cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			HSSFCellStyle cellGreenStyle = workBook.createCellStyle();
			
			HSSFFont GreenFont = workBook.createFont();			
			GreenFont.setFontName(HSSFFont.FONT_ARIAL);
			GreenFont.setColor(HSSFColor.WHITE.index);		
			GreenFont.setFontHeightInPoints((short) 10);
			
			cellGreenStyle.setFont(GreenFont);
			cellGreenStyle.setWrapText(true);
			cellGreenStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			cellGreenStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellGreenStyle.setFillForegroundColor(HSSFColor.GREEN.index);
			
			HSSFCellStyle cellLightGreenStyle = workBook.createCellStyle();
			
			cellLightGreenStyle.setFont(Font);
			cellLightGreenStyle.setWrapText(true);
			cellLightGreenStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			cellLightGreenStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellLightGreenStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
			
			HSSFCellStyle cellCompHeadStyle = workBook.createCellStyle();
			
			HSSFFont headCompFont = workBook.createFont();
			
			headCompFont.setFontName(HSSFFont.FONT_ARIAL);
			headCompFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			headCompFont.setColor(HSSFColor.BLACK.index);	
			headCompFont.setFontHeightInPoints((short) 10);
			
			cellCompHeadStyle.setFont(headCompFont);
			cellCompHeadStyle.setWrapText(true);
			cellCompHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellCompHeadStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellCompHeadStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
			
			ModelCompGroupBI objModelCompGroupBO = ServiceFactory.getModelCompGroupBO();
			OrderBI objOrderBO = ServiceFactory.getOrderBO();
			
			String[] orderKeys = null;
			
			orderKeys = objHttpServletRequest.getParameterValues("orderKey");
			
			int colWidth = orderKeys.length * 2;
			
			ArrayList arlOrdList 		= new ArrayList();
			ArrayList arlSpecList 		= new ArrayList();
			ArrayList arlCmpGrpList 	= new ArrayList();
			ArrayList arlComponentList 	= new ArrayList();
			ArrayList arlOrderList 		= new ArrayList();
			ArrayList arlSpecItemsList 	= new ArrayList();
			ArrayList arlItemsList 		= new ArrayList();
			ArrayList arlCompGrpsList 	= new ArrayList();
			
			objCompGroupVO.setOrderKeys(orderKeys);
			objCompGroupVO.setDataLocationType("S");
			objCompGroupVO.setUserID(objLoginVo.getUserID());
			
			objOrderVO.setOrderKeys(orderKeys);
			objOrderVO.setDataLocTypeCode("S");
			objOrderVO.setUserID(objLoginVo.getUserID());
			
			arlOrdList.add(objOrderBO.fetchMultipleOrders(objOrderVO));
			arlSpecList.add(objModelCompGroupBO.viewCompInOrdMap(objCompGroupVO));
			arlCmpGrpList.add(objModelCompGroupBO.viewCompGrpInOrdMap(objCompGroupVO));
			
			/*LogUtil.logMessage("arlOrdList.size():"+arlOrdList.size());
			LogUtil.logMessage("arlSpecList.size():"+arlSpecList.size());
			LogUtil.logMessage("arlCmpGrpList.size():"+arlCmpGrpList.size());*/
			
			
			Iterator listSpecDescList 	= null;
			Iterator listSpecItemsList 	= null;
			Iterator listItemsList	    = null;
			Iterator listCompList	    = null;
			Iterator listComponentList	= null;
			Iterator listOrdList    	= null;
			Iterator listOrderDetList   = null;
			Iterator listSpecList    	= null;
			Iterator listCmpGrpList   	= null;
			
			HSSFRow row = sheet.createRow(rowCount);
			
			if (arlOrdList.size() != 0) {
				
				createHeadingForClauses(workBook,row,sheet,colWidth);
				listOrdList = arlOrdList.iterator();
				int counter=0;
				rowCount++;
				while (listOrdList.hasNext()) {
					 
					arlOrderList = new ArrayList();
					arlOrderList = (ArrayList)listOrdList.next();
					//LogUtil.logMessage("arlOrderList.size():"+arlOrderList.size());
					listOrderDetList = arlOrderList.iterator();
					if (counter!=0)
						rowCount=1;
					while (listOrderDetList.hasNext()) {
						objOrderVO = new OrderVO();
						objOrderVO = (OrderVO) listOrderDetList.next();
						
						intPos=0;
						if (counter!=0)	{
							row = sheet.getRow(rowCount);
							intcol++;
						}
						else
							row = sheet.createRow(rowCount);
							
						/*LogUtil.logMessage("Before++ intcol:"+intcol);
						LogUtil.logMessage("Before++ rowCount:"+rowCount);*/
						
						createCell(row,cellHeadStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString("Order Number"));//0th column
						intcol++;
						createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(objOrderVO.getOrderNo()));//0th column
						
						rowCount++;
						intcol--;
						
						/*LogUtil.logMessage("intcol:"+intcol);
						LogUtil.logMessage("rowCount:"+rowCount);*/
						
						if (counter!=0)
							row = sheet.getRow(rowCount);
						else
							row = sheet.createRow(rowCount);
						
						createCell(row,cellHeadStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString("Customer Name"));//1st column
						intcol++;
						createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(objOrderVO.getCustomerName()));//2nd column
						
						rowCount++;
						intcol--;
						
						/*LogUtil.logMessage("intcol:"+intcol);
						LogUtil.logMessage("rowCount:"+rowCount);*/
						
						if (counter!=0)
							row = sheet.getRow(rowCount);
						else
							row = sheet.createRow(rowCount);
						
						createCell(row,cellHeadStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString("Spec Status"));//1st column
						intcol++;
						createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(objOrderVO.getStatusDesc()));//2nd column
						
						rowCount = rowCount-2;
						counter++;
						}
					}
				}
			
			if (arlSpecList.size() != 0) {
				listSpecList = arlSpecList.iterator();
				int counter=0;
				int itemcounter=0;
				rowCount++;
				while (listSpecList.hasNext()) {
					arlSpecList = new ArrayList();
					arlSpecList = (ArrayList)listSpecList.next();
					listSpecDescList = arlSpecList.iterator();
					counter=0;
					while (listSpecDescList.hasNext()) {
						
						objCompGroupVO = new CompGroupVO();
						objCompGroupVO = (CompGroupVO) listSpecDescList.next();
												
							arlSpecItemsList = new ArrayList();
							arlSpecItemsList = objCompGroupVO.getSpecificationItemVO();
							//LogUtil.logMessage("arlSpecItemsList.size():"+arlSpecItemsList.size());

							counter=0;
							itemcounter=0;
							rowCount = sheet.getLastRowNum();
							listSpecItemsList = arlSpecItemsList.iterator();
							
							while (listSpecItemsList.hasNext()) {
								objSpecificationVO = new SpecificationVO();
								objSpecificationVO = (SpecificationVO) listSpecItemsList.next();
								if (counter==0)	{	
									rowCount++;
									intcol=0;
									row = sheet.createRow(rowCount);
								}else{
									rowCount=rowCount-itemCount;
									row = sheet.getRow(rowCount);
								}

								/*LogUtil.logMessage("SpecBefore++ rowCount:"+rowCount);
								LogUtil.logMessage("SpecBefore++ intcol:"+intcol);*/
								
								if(objSpecificationVO.getSpecSeqNo() != 0)
									createCell(row,cellHeadStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(objCompGroupVO.getSpecDesc()));//3 column
									intcol=intcol+2;
								
								arlItemsList   = objSpecificationVO.getSpecItemList();
								itemCount = arlItemsList.size();
								listItemsList = arlItemsList.iterator();
								while(listItemsList.hasNext()){
									objSpecificationItemVO = new SpecificationItemVO();
									objSpecificationItemVO = (SpecificationItemVO) listItemsList.next();
									
									if (itemcounter==0)	{	
										rowCount++;
										intcol=intcol-2;
										prevRowCount = rowCount;
										LogUtil.logMessage("objSpecificationVO.getItemCount():"+objSpecificationVO.getItemCount());
										for(int i=0;i<objSpecificationVO.getItemCount();i++){
											row = sheet.createRow(rowCount);
											rowCount++;
										}
										rowCount = prevRowCount;
									}
									else{		
										intcol=intcol-2;
										rowCount++;
									}
									/*LogUtil.logMessage("itembefore++ rowCount:"+rowCount);
									LogUtil.logMessage("itembefore++ intcol:"+intcol);*/
									row = sheet.getRow(rowCount);
									createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(objSpecificationItemVO.getItemDesc()));
									intcol++;

									/*LogUtil.logMessage("valuebefore++ rowCount:"+rowCount);
									LogUtil.logMessage("valuebefore++ intcol:"+intcol);*/
									createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(objSpecificationItemVO.getItemValue()));
									intcol++;
									
									itemcounter++;
									}
								counter++;
								}
								}
							}
					}
					
					
			
			if (arlCmpGrpList.size() != 0) {
				
				
				listCmpGrpList = arlCmpGrpList.iterator();
				int counter=0;
				int itemcounter=0;
				while (listCmpGrpList.hasNext()) {

					//LogUtil.logMessage("Entering.ComponentGroupHeader():");
					arlCompGrpsList = new ArrayList();
					arlCompGrpsList = (ArrayList)listCmpGrpList.next();
					listCompList 	= arlCompGrpsList.iterator();
					rowCount=sheet.getLastRowNum()+1;
					intcol=0;
					row = sheet.createRow(rowCount);

					/*LogUtil.logMessage("Compgrpheader rowCount:"+rowCount);
					LogUtil.logMessage("Compgrpheader intcol:"+intcol);*/
					
					while (listCompList.hasNext()) {
						
						objCompGroupVO = new CompGroupVO();
						objCompGroupVO = (CompGroupVO) listCompList.next();					
						
						arlComponentList = new ArrayList();
						arlComponentList = objCompGroupVO.getComponentVO();
						listComponentList = arlComponentList.iterator();
						//LogUtil.logMessage("arlComponentList.size():"+arlComponentList.size());
						if (counter == 0){
							for(int i=0;i<arlComponentList.size();i++){
								createCell(row,cellCompHeadStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString("COMPONENT GROUP"));
								intcol++;
								createCell(row,cellCompHeadStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString("COMPONENT"));
								intcol++;
							}
						}
						rowCount++;
						intcol=0;
						itemcounter=0;
						while (listComponentList.hasNext()) {
								objComponentVO = new ComponentVO();
								objComponentVO = (ComponentVO) listComponentList.next();								
								
								if (itemcounter!=0)
									row = sheet.getRow(rowCount);
								else
									row = sheet.createRow(rowCount);

								//LogUtil.logMessage("Comp rowCount:"+rowCount);
								//LogUtil.logMessage("Comp intcol:"+intcol);
									if(objComponentVO.getComponentGroupSeqNo() != 0)
									createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(objCompGroupVO.getComponentGroupName()));//3 column
									intcol++;
									if(objComponentVO.getComponentGroupSeqNo() != 0){
										if(objComponentVO.getCompColorFlag().equalsIgnoreCase("GREEN"))
											createCell(row,cellGreenStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(objComponentVO.getComponentName()));
										if(objComponentVO.getCompColorFlag().equalsIgnoreCase("LIGHT_GREEN"))
											createCell(row,cellLightGreenStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(objComponentVO.getComponentName()));
										if(objComponentVO.getCompColorFlag().equalsIgnoreCase("NONE"))	
											createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, intcol,new HSSFRichTextString(objComponentVO.getComponentName()));
									}
									intcol++;
								itemcounter++;
						}
						counter++;
					}
				}
			}	
				
				objHttpServletResponse.setContentType("application/vnd.ms-excel");
				objHttpServletResponse.setHeader("Content-disposition", "attachment; filename=ComponentsInOrdersMapReport.xls");
				OutputStream out = null;
				out = objHttpServletResponse.getOutputStream();
				workBook.write(out);
			
			
		}catch (Exception objEx) {
			objEx.printStackTrace();
			strForwardKey = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		LogUtil.logMessage("strForwardKey :" + strForwardKey);
		if (strForwardKey.equalsIgnoreCase(ApplicationConstants.FAILURE))
			return objActionMapping.findForward(strForwardKey);
		else
			return null;
	
}

	/***************************************************************************
	 * This method is used to create the cell with int value
	 * 
	 * @author Mahindra Satyam Ltd
	 * @version 1.0
	 * @param HSSFSheet, HSSFRow, HSSFCellStyle, int, int, int
	 * @return HSSFCell
	 **************************************************************************/
	
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
	
	/***************************************************************************
	 * This method is used to create the cell with String value
	 * 
	 * @author Mahindra Satyam Ltd
	 * @version 1.0
	 * @param HSSFSheet, HSSFRow, HSSFCellStyle, int, int, String
	 * @return HSSFCell
	 **************************************************************************/
	
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
	
	/***************************************************************************
	 * This method is used to create the cell with String value
	 * 
	 * @author Mahindra Satyam Ltd
	 * @version 1.0
	 * @param HSSFSheet, HSSFRow, HSSFCellStyle, int, int, String
	 * @return HSSFCell
	 **************************************************************************/
	
	protected HSSFCell createGenDescCell(HSSFRow row, HSSFCellStyle headerStyle,
			int cellType, int colPosition, HSSFRichTextString cellValue) {
		//This function is used to create a cell with String value
		HSSFCell cell = null;
		cell = row.createCell(colPosition);
		cell.setCellStyle(headerStyle);
		cell.setCellType(cellType);
		cell.setCellValue(cellValue);
		return cell;
	}
	

	/***************************************************************************
	 * This method is used to Create the Heading for each SubSection
	 * 
	 * @author Mahindra Satyam Ltd
	 * @version 1.0
	 * @param HSSFWorkbook, HSSFRow, HSSFSheet
	 * @return void
	 * @throws Exception
	 **************************************************************************/
	
	public void createHeadingForClauses(HSSFWorkbook workBook,HSSFRow row,HSSFSheet sheet,int colWidth) 
		throws Exception{
		
		HSSFCellStyle cellTitleHeadStyle = workBook.createCellStyle();
		
		HSSFFont headFont = workBook.createFont();
		
		headFont.setFontName(HSSFFont.FONT_ARIAL);
		headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headFont.setColor(HSSFColor.BLACK.index);	
		headFont.setFontHeightInPoints((short) 10);
		
		cellTitleHeadStyle.setWrapText(true);
		cellTitleHeadStyle.setFont(headFont);
		cellTitleHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellTitleHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		
		final String[] columnHeadings = { "Components in Orders Map Report"};
		
		createCell(row,cellTitleHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString(columnHeadings[0]));
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,colWidth-1)); 
		
		for (int intPos=0;intPos < colWidth; intPos++)
		{
			 sheet.setColumnWidth(intPos,5500);
		}
	}
	
	//Added for CR-128
	/***************************************************************************
	 * This method is for fetching the component groups for report in Excel
	 * Added for CR-26
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
	
	public ActionForward exportCompGrpReportExcel(
			ActionMapping objActionMapping, ActionForm objActionForm,
			HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering CompGroupMaintAction.exportCompGrpReportExcel");
		String strForwardKey = ApplicationConstants.SUCCESS;
		CompGroupMaintForm objCompGroupMaintForm = (CompGroupMaintForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		int specTypeNo=0;
		ArrayList arlSpec = null;
		String strSpecTypeNo = null;
		int rowCount=0;
		int prevRowCount=0;
		int itemCount=0;
		int intcol=0;
		int intPos=0; 

		String orderComp="M";
		
		boolean includeOrderComp=false;
	//	int [] arlMdlSeqNoArry=null;
		
			try {
			
//				create workbook
				HSSFWorkbook workBook = new HSSFWorkbook();
				HSSFSheet sheet = workBook.createSheet("Component Group - Component Report");//create sheet
				
				HSSFCellStyle cellHeadStyle = workBook.createCellStyle();
				HSSFFont headFont = workBook.createFont();
				
				headFont.setFontName(HSSFFont.FONT_ARIAL);
				headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				headFont.setColor(HSSFColor.BLACK.index);	
				headFont.setFontHeightInPoints((short) 10);
				
				cellHeadStyle.setWrapText(false);
				cellHeadStyle.setFont(headFont);
				cellHeadStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
				//cellHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				
				HSSFPalette palette = ((HSSFWorkbook)workBook).getCustomPalette(); 
				palette.setColorAtIndex((short)57, (byte)210, (byte)221, (byte)249); 
				cellHeadStyle.setFillForegroundColor(palette.getColor(57).getIndex()); 
				cellHeadStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
				
				HSSFCellStyle cellStyle = workBook.createCellStyle();										
				
				HSSFFont Font = workBook.createFont();			
				Font.setFontName(HSSFFont.FONT_ARIAL);
				Font.setColor(HSSFColor.BLACK.index);		
				Font.setFontHeightInPoints((short) 10);
				
				cellStyle.setFont(Font);
				cellStyle.setWrapText(true);
				cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

				HSSFCellStyle cellRedStyle = workBook.createCellStyle();
				
				HSSFFont GreenFont = workBook.createFont();			
				GreenFont.setFontName(HSSFFont.FONT_ARIAL);
				GreenFont.setColor(HSSFColor.RED.index);		
				GreenFont.setFontHeightInPoints((short) 10);
				
				cellRedStyle.setFont(GreenFont);
				cellRedStyle.setWrapText(true);
				cellRedStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellRedStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				
				Iterator cmpGrpListIterator   	 = null;
				Iterator componentListIterator	 = null;
				Iterator mdlAffectedListIterator = null;
				Iterator mdlDefaultListIterator	 = null;
				
				ArrayList arlCompGrps = new ArrayList();
				ArrayList arlComponentList 	= new ArrayList();
				ArrayList arlMdlAffectedList 	= new ArrayList();
				ArrayList arlMdlDefaultList 	= new ArrayList();
				
				CompGroupVO objCompGroupVO = new CompGroupVO();
				ComponentVO objComponentVO = new ComponentVO();
				objCompGroupVO.setUserID(objLoginVo.getUserID());
			
				//Added For CR_97 on march 2011 by SD41630	lines starts here
				specTypeNo=objCompGroupMaintForm.getSpecTypeNo();
				objCompGroupVO.setSpecTypeSeqNo(specTypeNo);
				
				
				includeOrderComp=objCompGroupMaintForm.isIncludeOrderFlag();
				if(includeOrderComp)
				{	objCompGroupVO.setIncOrderComp(null);	
					objCompGroupMaintForm.setIncludeOrderONOFF("Yes");
					objCompGroupMaintForm.setHdnIncludeOrderFlag(true);
				}
				else
				{	objCompGroupVO.setIncOrderComp(orderComp);
					objCompGroupMaintForm.setIncludeOrderONOFF("No");
					objCompGroupMaintForm.setHdnIncludeOrderFlag(false);
				}	
				
				/*LogUtil.logMessage("objCompGroupMaintForm.getHdnSelSpecTypeName():"+objCompGroupMaintForm.getHdnSelSpecTypeName());
				LogUtil.logMessage("objCompGroupMaintForm.getHdnSelectedMdlNames():"+objCompGroupMaintForm.getHdnSelectedMdlNames());
				LogUtil.logMessage("objCompGroupMaintForm.getIncludeOrderONOFF():"+objCompGroupMaintForm.getIncludeOrderONOFF());*/
				
				String strSpecType = objCompGroupMaintForm.getHdnSelSpecTypeName();
				String strMdlNames = objCompGroupMaintForm.getHdnSelectedMdlNames();
				String strOrdComp = objCompGroupMaintForm.getIncludeOrderONOFF();
				
				if(objCompGroupMaintForm
						.getModelSeqNos()!=null){
				objCompGroupVO.setModelSelected(objCompGroupMaintForm
						.getModelSeqNos());
				
				}
				
				
				
				ModelCompGroupBI objModelCompGroupBO = ServiceFactory
				.getModelCompGroupBO();
				arlCompGrps = objModelCompGroupBO
				.fetchCompGrpReport(objCompGroupVO);
				
				HSSFRow row = sheet.createRow(rowCount);
				
				if (arlCompGrps.size() != 0) {
					
					createHeadingForCompGroupReport(workBook,row,sheet,strSpecType,strMdlNames,strOrdComp);
					cmpGrpListIterator = arlCompGrps.iterator();
					rowCount = row.getRowNum()+ 2;	
					while (cmpGrpListIterator.hasNext()) {
						//LogUtil.logMessage(" row.getRowNum() :" +  row.getRowNum());
						intPos = 0;				
						rowCount++;
						row = sheet.createRow(rowCount);
						 
						objCompGroupVO = new CompGroupVO();
						objCompGroupVO = (CompGroupVO) cmpGrpListIterator.next();
						
						arlComponentList = (ArrayList) objCompGroupVO.getComponentVO();
						//LogUtil.logMessage("arlComponentList :" + arlComponentList.size());
						int nrowNum = row.getRowNum();
						int nrowSpan = arlComponentList.size()-1;

						/*createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, 0,new HSSFRichTextString(objCompGroupVO.getComponentGroupName()));
						//sheet.addMergedRegion(new CellRangeAddress(nrowNum,nrowNum+nrowSpan,0,0));
						createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, 1,new HSSFRichTextString(objCompGroupVO.getComponentGroupIdentifier()));
						//sheet.addMergedRegion(new CellRangeAddress(nrowNum,nrowNum+nrowSpan,1,1));
						createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, 2,new HSSFRichTextString(objCompGroupVO.getCompGrpTypeName()));
						//sheet.addMergedRegion(new CellRangeAddress(nrowNum,nrowNum+nrowSpan,2,2));*/
						
						componentListIterator = arlComponentList.iterator();
						String strCompName = "";
						String strCompValIdentifier = "";
						String strMdlAffected = "";		
						String strMdlDefault = "";		
						while (componentListIterator.hasNext()){
							objComponentVO = new ComponentVO();
							objComponentVO = (ComponentVO) componentListIterator.next();
							
							strCompName = objComponentVO.getComponentName();
							strCompValIdentifier = objComponentVO.getComponentIdentifier();

							if (strCompName != ""){
								if(objComponentVO.getCompSourceFlag().equalsIgnoreCase("O")){
									createCell(row,cellRedStyle ,HSSFCell.CELL_TYPE_STRING, 3,new HSSFRichTextString(strCompName));
								}else{ 
									createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, 3,new HSSFRichTextString(strCompName));
								}
							}else{
								createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, 3,new HSSFRichTextString(""));
							}
							if (strCompValIdentifier != ""){
								createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, 4,new HSSFRichTextString(strCompValIdentifier));
							}else{
								createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, 4,new HSSFRichTextString(""));
							}
							arlMdlAffectedList = (ArrayList) objComponentVO.getModelsAffected();
							//LogUtil.logMessage("arlMdlAffectedList :" + arlMdlAffectedList.size());
							strMdlAffected = "";
							strMdlDefault = "";
							if(arlMdlAffectedList != null && arlMdlAffectedList.size() > 0){
								for (int k= 0; k < arlMdlAffectedList.size(); k++) {
									strMdlAffected = strMdlAffected + " " +  arlMdlAffectedList.get(k);
								}
								if (strMdlAffected != ""){	
									createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, 5,new HSSFRichTextString(strMdlAffected));
								}else{
									createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, 5,new HSSFRichTextString(""));
								}	
							}
							arlMdlDefaultList = (ArrayList) objComponentVO.getModelDefault();
							//LogUtil.logMessage("arlMdlDefaultList :" + arlMdlDefaultList.size());
							if(arlMdlDefaultList != null && arlMdlDefaultList.size() > 0){
								for (int k= 0; k < arlMdlDefaultList.size(); k++) {
									strMdlDefault = strMdlDefault + " " +  arlMdlDefaultList.get(k);
								}
								if (strMdlDefault != ""){	
									createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, 6,new HSSFRichTextString(strMdlDefault));
								}else{
									createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, 6,new HSSFRichTextString(""));
								}
							}

							if (rowCount < (nrowNum+nrowSpan))	{	
								rowCount++;
								//LogUtil.logMessage("Create row here at " + rowCount);
								row = sheet.createRow(rowCount);
							}
						}						
						row = sheet.getRow(nrowNum);
						createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, 0,new HSSFRichTextString(objCompGroupVO.getComponentGroupName()));
						sheet.addMergedRegion(new CellRangeAddress(nrowNum,nrowNum+nrowSpan,0,0));
						createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, 1,new HSSFRichTextString(objCompGroupVO.getComponentGroupIdentifier()));
						sheet.addMergedRegion(new CellRangeAddress(nrowNum,nrowNum+nrowSpan,1,1));
						createCell(row,cellStyle ,HSSFCell.CELL_TYPE_STRING, 2,new HSSFRichTextString(objCompGroupVO.getCompGrpTypeName()));
						sheet.addMergedRegion(new CellRangeAddress(nrowNum,nrowNum+nrowSpan,2,2));
						
						
					}
				}	
			
				//LogUtil.logMessage("end :");
			    objHttpServletResponse.setContentType("application/vnd.ms-excel");
				objHttpServletResponse.setHeader("Content-disposition", "attachment; filename= Component Group - Component Report.xls");
				OutputStream out = null;
				out = objHttpServletResponse.getOutputStream();
				workBook.write(out);
					
			}catch (Exception objExp) {
				strForwardKey = ApplicationConstants.FAILURE;
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(objExp.getMessage() + "");
				LogUtil.logError(objErrorInfo);
				}
			LogUtil.logMessage("strForwardKey :" + strForwardKey);
			if (strForwardKey.equalsIgnoreCase(ApplicationConstants.FAILURE))
				return objActionMapping.findForward(strForwardKey);
			else
				return null;
	}
	
	/***************************************************************************
	 * This method is used to Create the Heading for each SubSection
	 * 
	 * @author Mahindra Satyam Ltd
	 * @version 1.0
	 * @param HSSFWorkbook, HSSFRow, HSSFSheet
	 * @return void
	 * @throws Exception
	 **************************************************************************/
	
	public void createHeadingForCompGroupReport(HSSFWorkbook workBook,HSSFRow row,HSSFSheet sheet,String spectype,String mdlname,String ordcomp) 
		throws Exception{
		
		HSSFCellStyle cellTitleHeadStyle = workBook.createCellStyle();
		
		HSSFFont headFont = workBook.createFont();
		
		headFont.setFontName(HSSFFont.FONT_ARIAL);
		headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headFont.setColor(HSSFColor.BLACK.index);	
		headFont.setFontHeightInPoints((short) 10);
		
		cellTitleHeadStyle.setWrapText(true);
		cellTitleHeadStyle.setFont(headFont);
		cellTitleHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellTitleHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		int rowCount = 0;
		createCell(row,cellTitleHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString("Component Group / Component Report"));
		sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,6));
		row.setHeight((short)500);
		rowCount++;
		row = sheet.createRow(rowCount);
		createCell(row,cellTitleHeadStyle,HSSFCell.CELL_TYPE_STRING,0,new HSSFRichTextString("Specification Type :"+spectype+"        Model(s) : "+mdlname+"        Include Order Components : "+ordcomp));
		sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,6));
		row.setHeight((short)700);
		rowCount++;
		row = sheet.createRow(rowCount);
		
		final String[] columnHeadings = {"Component Group", "Component Group Identifier", "Component Group Type","Component Value",
				"Component Value Identifier","Models Affected","Model Default"};
		final int[] columnWidth = {5500,8300,6500,5500,8300,5000,5000};
		for (int intPos=0;intPos < 7; intPos++)
			{
			 LogUtil.logMessage("columnHeadings[intPos]:"+columnHeadings[intPos]);
			 createCell(row,cellTitleHeadStyle,HSSFCell.CELL_TYPE_STRING,intPos,new HSSFRichTextString(columnHeadings[intPos]));
			 sheet.setColumnWidth(intPos,columnWidth[intPos]);
			 LogUtil.logMessage("after createcell");
			} 
		row.setHeight((short)500);
	}
	
	//Added for CR-128 Ends here
	
//Added for CR-130
public ActionForward fetchCompInOrderReport(ActionMapping objActionMapping,
		ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
		HttpServletResponse objHttpServletResponse)
throws DataAccessException, ApplicationException {
	
	LogUtil.logMessage("Entering CompGroupMaintAction.fetchCompInOrderReport");
	String strForwardKey = ApplicationConstants.COMP_IN_ORDER_REPORT;
	CompGroupMaintForm objCompGroupMaintForm = (CompGroupMaintForm) objActionForm;
	HttpSession objSession = objHttpServletRequest.getSession(false);
	LoginVO objLoginVo = (LoginVO) objSession
	.getAttribute(ApplicationConstants.USER_IN_SESSION);
	int specTypeNo=0;
	ArrayList arlSpec = null;
	ArrayList arlModelList = new ArrayList();
	String forwardToExcel = objHttpServletRequest.getParameter("Excel");
	LogUtil.logMessage("forwardToExcel" +forwardToExcel);
	try {
		//forwardToExcel = objHttpServletRequest.getParameter("Excel");
		if (forwardToExcel.equalsIgnoreCase("Y"))
			strForwardKey = ApplicationConstants.SUCCESS;
		ArrayList arlCompGrps = new ArrayList();
		ArrayList arlComps = new ArrayList();
		ModelVo objModelVo = new ModelVo();
		ModelBI objModelBo = ServiceFactory.getModelBO();
					
		CompGroupVO objCompGroupVO = new CompGroupVO();
		ComponentVO objCompVO = new ComponentVO();
		objCompGroupVO.setUserID(objLoginVo.getUserID());
		objCompVO.setUserID(objLoginVo.getUserID());
					
		if(objCompGroupMaintForm.getSpecTypeNo() != 0){
			objCompGroupVO.setSpecTypeSeqNo(objCompGroupMaintForm.getSpecTypeNo());
		}else{
			objCompGroupVO.setSpecTypeSeqNo(specTypeNo);
		}
		
		if(objCompGroupMaintForm
				.getModelSeqNo()!=0){
			objCompGroupVO.setModelSeqNo(objCompGroupMaintForm
				.getModelSeqNo());			
		}else{
			objCompGroupVO.setModelSeqNo(-1);	
		}

		SpecTypeVo objSpecTypeVo = new SpecTypeVo();
		objSpecTypeVo.setUserID(objLoginVo.getUserID());
		SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
		arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
		if (arlSpec.size() != 0) {
			objCompGroupMaintForm.setSpecTypeList(arlSpec);
		}
		
		objModelVo.setSpecTypeSeqNo(objCompGroupMaintForm.getSpecTypeNo());
		arlModelList = objModelBo.fetchModels(objModelVo);
		
		if (arlModelList != null && arlModelList.size() > 0) {
			objCompGroupMaintForm.setModelList(arlModelList);
		}else{
			objCompGroupMaintForm
			.setMessageID(ApplicationConstants.NO_RECORD_MESSAGE_ID);
		}

		ModelCompGroupBI objModelCompGroupBO = ServiceFactory.getModelCompGroupBO();
		ModelCompBI objModelCompBO = ServiceFactory.getModelCompBO();
		//Added for CR-121 for sorting by Vb106565 starts here 		
		 objCompGroupVO.setOrderbyCompgrpFlag(objCompGroupMaintForm.getOrderbyCompGroupFlag()); 
		 if (objCompGroupMaintForm.getOrderbyCompGroupFlag() == 0){
			 objCompGroupMaintForm.setColumnheader("CompGroup");
			 objCompGroupMaintForm.setOrderbyCompGroupFlag(ApplicationConstants.ORDERBY_FLAG_ONE);
		 }				 
		 objCompGroupVO.setOrderbyCompFlag(objCompGroupMaintForm.getOrderbyCompFlag());
		 //Added for CR-121 for sorting by Vb106565 ends here  

		if(objCompGroupMaintForm.getModelSeqNo() == 0 || objCompGroupMaintForm.getModelSeqNo() == -1) {
			
			arlCompGrps = objModelCompGroupBO.fetchCompGroups(objCompGroupVO);
			if (arlCompGrps != null && arlCompGrps.size() > 0) {
				objCompGroupMaintForm.setCompgroupList(arlCompGrps);				
				}
			
			if(objCompGroupMaintForm.getCompGroupSeqNo() != 0)	{
				objCompVO.setComponentGroupSeqNo(objCompGroupMaintForm.getCompGroupSeqNo());
				arlComps = objModelCompBO.fetchComps(objCompVO);
				if (arlComps != null && arlComps.size() > 0) {
					objCompGroupMaintForm.setCompList(arlComps);			
				}
			}
			
		}else {
			objCompVO.setModelSeqNo(objCompGroupMaintForm.getModelSeqNo());
			int[] modelSeqNos = new int[1];
			modelSeqNos[0]=objCompGroupMaintForm.getModelSeqNo();
			objCompGroupVO.setModelSelected(modelSeqNos);
			
			arlCompGrps = objModelCompBO.fetchModelComps(objCompVO);				
			if (arlCompGrps != null && arlCompGrps.size() > 0) {
				objCompGroupMaintForm.setCompgroupList(arlCompGrps);
			}

		}

		if(objCompGroupMaintForm.getCompGroupSeqNo() != -1 && objCompGroupMaintForm.getCompGroupSeqNo() != 0){
			objCompGroupVO.setComponentGroupSeqNo(objCompGroupMaintForm.getCompGroupSeqNo());
			objCompGroupVO.setComponentSeqNo(objCompGroupMaintForm.getComponentSeqNo());
		}
		
		if(objCompGroupMaintForm.isShowLatSpecFlag()){
			objCompGroupMaintForm.setHdnShowLatSpecFlag("Yes");	
			objCompGroupVO.setShowLatestPubSpecFlag(ApplicationConstants.YES);
		}
		else{
			objCompGroupMaintForm.setHdnShowLatSpecFlag("No");		
			objCompGroupVO.setShowLatestPubSpecFlag(ApplicationConstants.NO);
		}
		
		arlCompGrps = objModelCompGroupBO.fetchCompGrpReport(objCompGroupVO);
		if (arlCompGrps != null && arlCompGrps.size() > 0) {
			objCompGroupMaintForm.setCompInOrdList(arlCompGrps);
		}else{
			objCompGroupMaintForm
			.setMessageID(ApplicationConstants.NO_RECORD_MESSAGE_ID);
		}
		
		if (forwardToExcel.equalsIgnoreCase("Y")){
		
			HSSFWorkbook workBook = new HSSFWorkbook();
			HSSFSheet sheet = workBook.createSheet("ComponentInOrdersReport");//create sheet
			
			//HSSFCellStyle cellHeadStyle    = workBook.createCellStyle();
			HSSFCellStyle cellNormalStyle  = workBook.createCellStyle();
			//HSSFCellStyle cellHeadingStyle = workBook.createCellStyle();
			HSSFCellStyle cellStyle = workBook.createCellStyle();
			
			HSSFFont headFont = workBook.createFont();
			HSSFFont redFont = workBook.createFont();
			HSSFFont normalFont = workBook.createFont();
			
			headFont.setFontName(HSSFFont.FONT_ARIAL);
			headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			headFont.setColor(HSSFColor.BLACK.index);	
			headFont.setFontHeightInPoints((short) 10);
			
			redFont.setFontName(HSSFFont.FONT_ARIAL);
			redFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			redFont.setColor(HSSFColor.RED.index);	
			redFont.setFontHeightInPoints((short) 10);
	
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
			
			Iterator ListOrdrList = null;
			Iterator ListCompList = null;
			Iterator ListCompGrpList = null;
			ArrayList arlOrdrList = new ArrayList();
			ArrayList arlCompGrpList = new ArrayList();
			ArrayList arlCompList = new ArrayList();
			int nrowCount=0;
			int intCol =0;
			int nStartRow=0;	
			
			HSSFRow row = sheet.createRow(nrowCount);
			
			createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString("Component In Orders Report"));
			sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),intCol,intCol+4));
			nrowCount = nrowCount+2;
			row = sheet.createRow(nrowCount);
						
			createHeadingCompInOrdrsReport(workBook,row,sheet);
			nrowCount++;
			row = sheet.createRow(nrowCount);
			int nOrdrListSize = 0;
			int nCompListSize = 0;
			int nCounter = 0;
			int nCompCount =0;
			int sheetCount = 1;
			boolean createSheet = false;
			ListCompGrpList = arlCompGrps.iterator();
			while(ListCompGrpList.hasNext()){				
				arlCompGrpList = new ArrayList();
				objCompGroupVO= new CompGroupVO();
				objCompGroupVO = (CompGroupVO)ListCompGrpList.next();
				int nrowSpanCount=0;
				
				if (createSheet)	{		
					sheet = workBook.createSheet("ComponentInOrdersReport_" + sheetCount);
					nrowCount = -1;
					nrowCount++;
					row = sheet.createRow(nrowCount);
					createHeadingCompInOrdrsReport(workBook,row,sheet);
					nrowCount++;
					row = sheet.createRow(nrowCount);
					sheetCount++;
					createSheet=false;
				}
				
				if(objCompGroupVO.getComponentVO()!=null){
					arlCompList = (ArrayList) objCompGroupVO.getComponentVO();
					ListCompList = arlCompList.iterator();				
					nCompListSize = (arlCompList.size())-1;
					//LogUtil.logMessage("nCompListSize " +nCompListSize);
					int nSpanCount =0;
					nSpanCount = nCompListSize;
					
					int ntestCount = 0 ;
					if(arlCompList.size()!=0){
					for (int j=0;j<arlCompList.size();j++) {
					//while(ListCompList.hasNext()){
						objCompVO = new ComponentVO();
						//objCompVO = (ComponentVO) ListCompList.next();
						objCompVO = (ComponentVO) arlCompList.get(j);
						
						if (j == nCompListSize)	{
							int predictedRowNum = row.getRowNum() + (objCompVO.getOrdersUsed().size())/3 + 1;
							//LogUtil.logMessage("Predicted RowNum "+predictedRowNum);			
							if (predictedRowNum > 62001)
								createSheet = true;
						}
							
						
						if(objCompVO.getOrdersUsed()!=null){
							arlOrdrList =(ArrayList) objCompVO.getOrdersUsed();
							ListOrdrList = arlOrdrList.iterator();
							
							nOrdrListSize = ((arlOrdrList.size())/3);
							if(nOrdrListSize!=0)
								nOrdrListSize=nOrdrListSize-1;
												
						nSpanCount = nSpanCount+nOrdrListSize;
															
						createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString(objCompGroupVO.getComponentGroupName()));

						
						if(nrowSpanCount==0){
							ntestCount = nrowSpanCount + nCompListSize;
							nStartRow=row.getRowNum();
							//LogUtil.logMessage("nStartRow "+nStartRow);
						}
						if(ntestCount == nrowSpanCount){
							sheet.addMergedRegion(new CellRangeAddress(nStartRow,nStartRow+nSpanCount,intCol,intCol));
							//LogUtil.logMessage("after comp grp merge");
						}
						nrowSpanCount++;
						intCol++;		
						
						HSSFRichTextString strComponentName = new HSSFRichTextString(objCompVO.getComponentName());
						if(objCompVO.getCompSourceFlag().equalsIgnoreCase("O")){
							createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, intCol,strComponentName);
							sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum()+nOrdrListSize,intCol,intCol));
							strComponentName.applyFont(redFont);
							//LogUtil.logMessage("after comp  merge");
						}else if(objCompVO.getCompSourceFlag().equalsIgnoreCase("M")){
							createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, intCol,strComponentName);
							sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum()+nOrdrListSize,intCol,intCol));
							//LogUtil.logMessage("after comp  merge");
						}else{
							createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString(""));
							//LogUtil.logMessage("after comp  merge");
						}
						intCol++;
										
						int i = 0;
						if(arlOrdrList!=null){
						while (i < arlOrdrList.size()) {
							int nrow=i%3;
							 if(nrow==0){
									intCol=2;
								if(i==0){
									row = sheet.getRow(nrowCount);
								}
								else{
									nrowCount++;
									row = sheet.createRow(nrowCount);
									
								
								}
								createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString(arlOrdrList.get(i).toString()));
								intCol++;
							}else if(nrow==1){
								row = sheet.getRow(nrowCount);
								createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString(arlOrdrList.get(i).toString()));
								intCol++;
							}else if(nrow==2){
								row = sheet.getRow(nrowCount);
								createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString(arlOrdrList.get(i).toString()));
								intCol++;
							}else
								createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString(""));
							i++;
							
							
						}
						}
						else{
							createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString(""));
						}
						
						}
						nrowCount++;
						row = sheet.createRow(nrowCount);
						intCol=0;
						
						
					
					
					}
				} else {
					LogUtil.logMessage("inside");
					createCell(row,cellNormalStyle ,HSSFCell.CELL_TYPE_STRING, intCol,new HSSFRichTextString(objCompGroupVO.getComponentGroupName()));
					nrowCount++;
					row = sheet.createRow(nrowCount);
				}
				} 
				nCompCount++;				
				//LogUtil.logMessage("nCompCount "+nCompCount);
			}
			
			objHttpServletResponse.setContentType("application/vnd.ms-excel");
			objHttpServletResponse.setHeader("Content-disposition", "attachment; filename= ComponentInOrdersReport.xls");
			OutputStream out = null;
			out = objHttpServletResponse.getOutputStream();
			workBook.write(out);
		}
		
	} catch (Exception objEx) {
		
		strForwardKey = ApplicationConstants.FAILURE;
		ErrorInfo objErrorInfo = new ErrorInfo();
		objErrorInfo.setMessage(objEx.getMessage() + "");
		LogUtil.logError(objErrorInfo);
	}
	LogUtil.logMessage("strForwardKey" +strForwardKey);
	
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

public void createHeadingCompInOrdrsReport(HSSFWorkbook workBook, HSSFRow row,
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
					
				final String[] columnHeadings = {"Component Group","Component","Order number", "Spec Status", "Customer Name"};
				final int[] columnWidth = {9000,8000,8000,8000,8000};
				for (int intPos=0;intPos < 5; intPos++)
					{
					 createCell(row,cellHeadStyle,HSSFCell.CELL_TYPE_STRING,intPos,new HSSFRichTextString(columnHeadings[intPos]));
						 sheet.setColumnWidth(intPos,columnWidth[intPos]);
						 
					 }
				}
	
}	
	
