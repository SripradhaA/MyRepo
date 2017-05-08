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
import com.EMD.LSDB.bo.serviceinterface.ModelCompBI;
import com.EMD.LSDB.bo.serviceinterface.ModelCompGroupBI;
import com.EMD.LSDB.bo.serviceinterface.SpecTypeBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.form.MasterMaintenance.CompMaintForm;
import com.EMD.LSDB.vo.common.CompGroupVO;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.SpecTypeVo;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This Action class has the business methods for the Components
 ******************************************************************************/

public class CompMaintAction extends EMDAction {
	
	/***************************************************************************
	 * This Method is used to fetch the Component Group Details
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
		
		LogUtil.logMessage("Entering CompMaintAction.initLoad");
		String strForward = ApplicationConstants.SUCCESS;
		CompMaintForm objCompMaintForm = (CompMaintForm) objActionForm;
		ArrayList arlCompGrpList = new ArrayList();
		//CR_83 lines are started here
		int specTypeNo = -1;
		int compGrpSeqNo = -1;
		String compGrpTypeSeqNo = null;
		String strSpecTypeNo = null;
		String strUserID = "";
		SpecTypeVo objSpecTypeVo = new SpecTypeVo();
		strSpecTypeNo = (String) objHttpServletRequest
				.getParameter("specTypeNo");
		try {
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			
			CompGroupVO objCompGrpVo = new CompGroupVO();
			ModelCompGroupBI objCompGrpBo = ServiceFactory
			.getModelCompGroupBO();
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			// Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			
			objCompGrpVo.setUserID(strUserID);
				
			if (strSpecTypeNo != null) {
				objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);
				specTypeNo = Integer.parseInt(strSpecTypeNo);
				objCompMaintForm.setSpecTypeNo(specTypeNo);
		} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
				if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {
					strSpecTypeNo = (String) objSession
							.getAttribute("SPEC_TYPE_NO");
					specTypeNo = Integer.parseInt(strSpecTypeNo);
					objCompMaintForm.setSpecTypeNo(specTypeNo);
				}
			}
			if(objCompMaintForm.getSpecTypeNo()!=-1 && objCompMaintForm.getSpecTypeNo()!=0 )
			{
			specTypeNo = objCompMaintForm.getSpecTypeNo();
			}
//			CR_83 lines ends here
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objCompMaintForm.setSpecTypeList(arlSpec);
				objCompMaintForm.setSpecTypeNo(specTypeNo);
			}
		
			objCompGrpVo.setSpecTypeSeqNo(specTypeNo);	

			//Added For CR_81 on 28-Dec-09 by RR68151 to fetch Component Group Types
			ArrayList arlCompGrpType = new ArrayList();
			arlCompGrpType = objCompGrpBo.fetchCompGrpTypes(objCompGrpVo);
			if (arlCompGrpType.size() != 0) {
				objCompMaintForm.setCompGroupTypeList(arlCompGrpType);
				}
			if (objCompMaintForm.getCompgrpCat() != null)
				objCompGrpVo.setCompGrpTypeSeqNo(Integer.parseInt(objCompMaintForm.
						getCompgrpCat()));
			else	
				objCompGrpVo.setCompGrpTypeSeqNo(ApplicationConstants.COMPGRPTYPE_ZERO);
			
			LogUtil.logMessage("ComponentGroupTypeSeqNo : " + objCompGrpVo.getCompGrpTypeSeqNo());
			//Added For CR_81 on 28-Dec-09 by RR68151 - Ends here
			
			arlCompGrpList = objCompGrpBo.fetchCompGroups(objCompGrpVo);
			
			if (arlCompGrpList != null && arlCompGrpList.size() > 0) {
				objCompMaintForm.setCompGrpList(arlCompGrpList);
				objCompMaintForm.setHdnSelCompGrp(objCompMaintForm
						.getHdnSelCompGrp());
			}
			
		} catch (Exception objEx) {
			
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
		
	}
	
	/***************************************************************************
	 * This Method is used to fetch the Component Details For Selected Component
	 * Group
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws DataAccessException
	 * @throws ApplicationException
	 **************************************************************************/
	public ActionForward fetchComps(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering CompMaintAction.fetchComps");
		String strForward = ApplicationConstants.SUCCESS;
		CompMaintForm objCompMaintForm = (CompMaintForm) objActionForm;
		int specTypeNo=-1;
		String strSpecTypeNo = null;
		String strUserID = "";
		strSpecTypeNo = (String) objHttpServletRequest
				.getParameter("specTypeNo");
		try {
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			
			ArrayList arlCompGrpList = new ArrayList();
			ArrayList arlCompList = new ArrayList();
			
			CompGroupVO objCompGrpVo = new CompGroupVO();
			ComponentVO objComponentVo = new ComponentVO();
			SpecTypeVo  objSpecTypeVo =new SpecTypeVo();
			
			ModelCompGroupBI objCompGrpBo = ServiceFactory
			.getModelCompGroupBO();
			ModelCompBI objComponentBo = ServiceFactory.getModelCompBO();
					
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			// Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			
			// To fetch Component Groups
			objCompGrpVo.setUserID(strUserID);
			
			
			//Added For CR_97
			if (strSpecTypeNo != null) {
				objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);
				specTypeNo = Integer.parseInt(strSpecTypeNo);
				objCompMaintForm.setSpecTypeNo(specTypeNo);
		} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
				if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {
					strSpecTypeNo = (String) objSession
							.getAttribute("SPEC_TYPE_NO");
					specTypeNo = Integer.parseInt(strSpecTypeNo);
					objCompMaintForm.setSpecTypeNo(specTypeNo);
				}
			}
			
			if(objCompMaintForm.getSpecTypeNo()!=-1 && objCompMaintForm.getSpecTypeNo()!=0 )
			{
			specTypeNo = objCompMaintForm.getSpecTypeNo();
			}
//			CR_83 lines ends here
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objCompMaintForm.setSpecTypeList(arlSpec);
				objCompMaintForm.setSpecTypeNo(specTypeNo);
			}
		
			objCompGrpVo.setSpecTypeSeqNo(specTypeNo);	
			
			

			//Added For CR_81 on 28-Dec-09 by RR68151 to fetch Component Group Types
			ArrayList arlCompGrpType = new ArrayList();
			arlCompGrpType = objCompGrpBo.fetchCompGrpTypes(objCompGrpVo);
			if (arlCompGrpType.size() != 0) {
				objCompMaintForm.setCompGroupTypeList(arlCompGrpType);
				}
			objCompGrpVo.setCompGrpTypeSeqNo(Integer.parseInt(objCompMaintForm.
					getCompgrpCat()));
			//Added For CR_81 on 28-Dec-09 by RR68151 - Ends here
			
			arlCompGrpList = objCompGrpBo.fetchCompGroups(objCompGrpVo);
			
			if (arlCompGrpList != null && arlCompGrpList.size() > 0) {
				objCompMaintForm.setCompGrpList(arlCompGrpList);
				objCompMaintForm.setHdnSelCompGrp(objCompMaintForm
						.getHdnSelCompGrp());
			}
			
			// To fetch Components
			objComponentVo.setComponentGroupSeqNo(objCompMaintForm
					.getCompGrpSeqNo());
			//Removed line for CR_81 by RR68151
			//objComponentVo.setCharacterisationFlag("");
			objComponentVo.setUserID(strUserID);
			arlCompList = objComponentBo.fetchComps(objComponentVo);
			
			if (arlCompList != null && arlCompList.size() > 0) {
				objCompMaintForm.setCompList(arlCompList);
				objCompMaintForm.setCompGrpSeqNo(objCompMaintForm
						.getCompGrpSeqNo());
			}
		} catch (Exception objEx) {
			
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
		
	}
	
	/***************************************************************************
	 * This Method is used to insert a new Component For a Selected Component
	 * Group.
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws BusinessException
	 * @throws ApplicationException
	 **************************************************************************/
	public ActionForward insertComp(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException, ApplicationException {
		
		LogUtil.logMessage("Entering CompMaintAction.insertComp");
		
		String strForward = ApplicationConstants.SUCCESS;
		int specTypeNo=-1;
		String strSpecTypeNo = null;
		String strUserID = "";
		strSpecTypeNo = (String) objHttpServletRequest
				.getParameter("specTypeNo");
		CompMaintForm objCompMaintForm = (CompMaintForm) objActionForm;
		try {
			HttpSession objSession = objHttpServletRequest.getSession(false);
			
			ArrayList arlCompGrpList = new ArrayList();
			ArrayList arlCompList = new ArrayList();
			
			int intStatus = 0;
			CompGroupVO objCompGrpVo = new CompGroupVO();
			ComponentVO objComponentVo = new ComponentVO();
			SpecTypeVo objSpecTypeVo=new SpecTypeVo();
			
			ModelCompGroupBI objCompGrpBo = ServiceFactory
			.getModelCompGroupBO();
			ModelCompBI objComponentBo = ServiceFactory.getModelCompBO();
							
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			// Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			objCompMaintForm
			.setCompGrpSeqNo(objCompMaintForm.getCompGrpSeqNo());
			
			// To fetch Component Groups
			objCompGrpVo.setUserID(strUserID);
			
//			Added For CR_97
			if (strSpecTypeNo != null) {
				objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);
				specTypeNo = Integer.parseInt(strSpecTypeNo);
				objCompMaintForm.setSpecTypeNo(specTypeNo);
		} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
				if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {
					strSpecTypeNo = (String) objSession
							.getAttribute("SPEC_TYPE_NO");
					specTypeNo = Integer.parseInt(strSpecTypeNo);
					objCompMaintForm.setSpecTypeNo(specTypeNo);
				}
			}
			
			if(objCompMaintForm.getSpecTypeNo()!=-1 && objCompMaintForm.getSpecTypeNo()!=0 )
			{
			specTypeNo = objCompMaintForm.getSpecTypeNo();
			}
//			CR_83 lines ends here
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objCompMaintForm.setSpecTypeList(arlSpec);
				objCompMaintForm.setSpecTypeNo(specTypeNo);
			}
		
			objCompGrpVo.setSpecTypeSeqNo(specTypeNo);	
			

			//Added For CR_81 on 28-Dec-09 by RR68151 to fetch Component Group Types
			ArrayList arlCompGrpType = new ArrayList();
			arlCompGrpType = objCompGrpBo.fetchCompGrpTypes(objCompGrpVo);
			if (arlCompGrpType.size() != 0) {
				objCompMaintForm.setCompGroupTypeList(arlCompGrpType);
				}
			objCompGrpVo.setCompGrpTypeSeqNo(Integer.parseInt(objCompMaintForm.
					getCompgrpCat()));
			//Added For CR_81 on 28-Dec-09 by RR68151 - Ends here
			
			arlCompGrpList = objCompGrpBo.fetchCompGroups(objCompGrpVo);
			
			if (arlCompGrpList != null && arlCompGrpList.size() > 0) {
				objCompMaintForm.setCompGrpList(arlCompGrpList);
			}
			
			// To insert New Component
			
			objComponentVo.setComponentGroupSeqNo(objCompMaintForm
					.getCompGrpSeqNo());
			objComponentVo.setComponentName(ApplicationUtil
					.trim(objCompMaintForm.getComponent()));
			objComponentVo.setComments(ApplicationUtil.trim(objCompMaintForm
					.getCompDesc()));
			objComponentVo.setComponentIdentifier(ApplicationUtil
					.trim(objCompMaintForm.getCompIdentifier()));
			if (objCompMaintForm.isDisplaySpec()) {
				objComponentVo.setDisplayInSpecFlag(true);
			} else {
				objComponentVo.setDisplayInSpecFlag(false);
			}
			objComponentVo.setUserID(strUserID);
			
			intStatus = objComponentBo.insertComp(objComponentVo);
			
			if (intStatus == 0) {
				objCompMaintForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				// To reset Values in Form
				objCompMaintForm.setComponent("");
				objCompMaintForm.setCompDesc("");
				objCompMaintForm.setCompIdentifier("");
				objCompMaintForm.setDisplaySpec(false);
				objCompMaintForm.setHdnSelCompGrp(objCompMaintForm
						.getHdnSelCompGrp());
			} else {
				objCompMaintForm.setMessageID("" + intStatus);
				// To retain Values in Form
				objCompMaintForm.setComponent(objCompMaintForm.getComponent());
				objCompMaintForm.setCompDesc(objCompMaintForm.getCompDesc());
				objCompMaintForm.setCompIdentifier(objCompMaintForm
						.getCompIdentifier());
				objCompMaintForm.setDisplaySpec(objCompMaintForm
						.isDisplaySpec());
				objCompMaintForm.setHdnSelCompGrp(objCompMaintForm
						.getHdnSelCompGrp());
			}
			
			// To fetch Components
			objComponentVo.setComponentGroupSeqNo(objCompMaintForm
					.getCompGrpSeqNo());
			//Removed line for CR_81 by RR68151
			//objComponentVo.setCharacterisationFlag("");
			objComponentVo.setUserID(strUserID);
			arlCompList = objComponentBo.fetchComps(objComponentVo);
			
			objCompMaintForm.setCompList(arlCompList);
			objCompMaintForm
			.setCompGrpSeqNo(objCompMaintForm.getCompGrpSeqNo());
			
		} catch (Exception objEx) {
			
			strForward = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage() + "");
			LogUtil.logError(objErrorInfo);
		}
		
		return objActionMapping.findForward(strForward);
	}
	
	/***************************************************************************
	 * This Method is used to Modify Component Details
	 * 
	 * @param objActionMapping
	 * @param objActionForm
	 * @param objHttpServletRequest
	 * @param objHttpServletResponse
	 * @return ActionForward
	 * @throws BusinessException
	 * @throws ApplicationException
	 **************************************************************************/
	public ActionForward updateComp(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws BusinessException, ApplicationException {
		
		LogUtil.logMessage("Entering CompMaintAction.updateComp");
		int specTypeNo=-1;
		String strSpecTypeNo = null;
		String strUserID = "";
		strSpecTypeNo = (String) objHttpServletRequest
				.getParameter("specTypeNo");
		String strForward = ApplicationConstants.SUCCESS;
		CompMaintForm objCompMaintForm = (CompMaintForm) objActionForm;
		try {
			
			HttpSession objSession = objHttpServletRequest.getSession(false);
			ArrayList arlCompGrpList = new ArrayList();
			
			CompGroupVO objCompGrpVo = new CompGroupVO();
			ModelCompGroupBI objCompGrpBo = ServiceFactory
			.getModelCompGroupBO();
			SpecTypeVo objSpecTypeVo =new SpecTypeVo();
			ComponentVO objComponentVo = new ComponentVO();
			ModelCompBI objComponentBo = ServiceFactory.getModelCompBO();
			
			boolean blnDispSpec;
			int intStatus = 0;
			// Getting Values From CompMaintForm
			int intCompSeqNo = Integer.parseInt(objCompMaintForm
					.getComponentSeqNo());
			String strCompName = ApplicationUtil.trim(objCompMaintForm
					.getHdnCompName());
			String strComments = ApplicationUtil.trim(objCompMaintForm
					.getHdnCompDesc());
			String strCompIdentifier = ApplicationUtil.trim(objCompMaintForm
					.getHdnCompIdentifier());
			objCompMaintForm
			.setCompGrpSeqNo(objCompMaintForm.getCompGrpSeqNo());
			if (objCompMaintForm.isHdnDispSpec()) {
				blnDispSpec = true;
			} else {
				blnDispSpec = false;
			}
			
			LoginVO objLoginVo = (LoginVO) objSession
			.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			// Getting User from the session
			if (objLoginVo != null) {
				
				strUserID = objLoginVo.getUserID();
			}
			
			// To fetch Component Groups
			objCompGrpVo.setUserID(strUserID);
			
//			Added For CR_97
			if (strSpecTypeNo != null) {
				objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);
				specTypeNo = Integer.parseInt(strSpecTypeNo);
				objCompMaintForm.setSpecTypeNo(specTypeNo);
		} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
				if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {
					strSpecTypeNo = (String) objSession
							.getAttribute("SPEC_TYPE_NO");
					specTypeNo = Integer.parseInt(strSpecTypeNo);
					objCompMaintForm.setSpecTypeNo(specTypeNo);
				}
			}
			
			if(objCompMaintForm.getSpecTypeNo()!=-1 && objCompMaintForm.getSpecTypeNo()!=0 )
			{
			specTypeNo = objCompMaintForm.getSpecTypeNo();
			}
//			CR_83 lines ends here
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objCompMaintForm.setSpecTypeList(arlSpec);
				objCompMaintForm.setSpecTypeNo(specTypeNo);
			}
		
			objCompGrpVo.setSpecTypeSeqNo(specTypeNo);	
	
			//Added For CR_81 on 28-Dec-09 by RR68151 to fetch Component Group Types
			ArrayList arlCompGrpType = new ArrayList();
			arlCompGrpType = objCompGrpBo.fetchCompGrpTypes(objCompGrpVo);
			if (arlCompGrpType.size() != 0) {
				objCompMaintForm.setCompGroupTypeList(arlCompGrpType);
				}
			objCompGrpVo.setCompGrpTypeSeqNo(Integer.parseInt(objCompMaintForm.
					getCompgrpCat()));
			//Added For CR_81 on 28-Dec-09 by RR68151 - Ends here
			
			arlCompGrpList = objCompGrpBo.fetchCompGroups(objCompGrpVo);
			
			if (arlCompGrpList != null && arlCompGrpList.size() > 0) {
				objCompMaintForm.setCompGrpList(arlCompGrpList);
			}
			
			// To Update Components
			objComponentVo.setComponentGroupSeqNo(objCompMaintForm
					.getCompGrpSeqNo());
			objComponentVo.setComponentSeqNo(intCompSeqNo);
			objComponentVo.setComponentName(strCompName);
			objComponentVo.setComments(strComments);
			objComponentVo.setComponentIdentifier(strCompIdentifier);
			objComponentVo.setDisplayInSpecFlag(blnDispSpec);
			objComponentVo.setUserID(strUserID);
			
			intStatus = objComponentBo.updateComp(objComponentVo);
			
			if (intStatus == 0) {
				objCompMaintForm
				.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			} else {
				objCompMaintForm.setMessageID("" + intStatus);
			}
			
			ArrayList arlCompList = new ArrayList();
			ComponentVO objCompVo = new ComponentVO();
			// To fetch Components
			objCompVo
			.setComponentGroupSeqNo(objCompMaintForm.getCompGrpSeqNo());
			//Removed line for CR_81 by RR68151
			//objComponentVo.setCharacterisationFlag("");
			objComponentVo.setUserID(strUserID);
			arlCompList = objComponentBo.fetchComps(objCompVo);
			objCompMaintForm.setCompList(arlCompList);
						
		}
		catch(Exception objEx){
			
			strForward  = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo=new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage()+"");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
	}


//////////////////////////////////////////////////////////////////

	public ActionForward deleteComp(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse) throws BusinessException,ApplicationException{
	
		LogUtil.logMessage("Entering CompMaintAction.deleteComp");
		int intStatus=0;
		int specTypeNo=-1;
		String strSpecTypeNo = null;
		String strUserID = "";
		strSpecTypeNo = (String) objHttpServletRequest
				.getParameter("specTypeNo");
		String strForward = ApplicationConstants.SUCCESS;
		CompMaintForm objCompMaintForm = (CompMaintForm) objActionForm;
		try{
			
			HttpSession objSession=objHttpServletRequest.getSession(false);
			ArrayList arlCompGrpList = new ArrayList();
			
			CompGroupVO objCompGrpVo = new CompGroupVO();
			ModelCompGroupBI objCompGrpBo = ServiceFactory.getModelCompGroupBO();
			SpecTypeVo objSpecTypeVo=new SpecTypeVo();
			ComponentVO objComponentVo = new ComponentVO();
			ModelCompBI objComponentBo = ServiceFactory.getModelCompBO();
			
			int intCompSeqNo = Integer.parseInt(objCompMaintForm.getComponentSeqNo());
			
			objCompMaintForm.setCompGrpSeqNo(objCompMaintForm.getCompGrpSeqNo());
			
			LoginVO objLoginVo = (LoginVO)objSession.getAttribute(ApplicationConstants.USER_IN_SESSION);
			
			//Getting User from the session
			if(objLoginVo != null){
				
				strUserID = objLoginVo.getUserID();
			}
			//To fetch Component Groups
			objCompGrpVo.setUserID(strUserID);
			
//			Added For CR_97
			if (strSpecTypeNo != null) {
				objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);
				specTypeNo = Integer.parseInt(strSpecTypeNo);
				objCompMaintForm.setSpecTypeNo(specTypeNo);
		} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
				if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {
					strSpecTypeNo = (String) objSession
							.getAttribute("SPEC_TYPE_NO");
					specTypeNo = Integer.parseInt(strSpecTypeNo);
					objCompMaintForm.setSpecTypeNo(specTypeNo);
				}
			}
			
			if(objCompMaintForm.getSpecTypeNo()!=-1 && objCompMaintForm.getSpecTypeNo()!=0 )
			{
			specTypeNo = objCompMaintForm.getSpecTypeNo();
			}
//			CR_83 lines ends here
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			ArrayList arlSpec = new ArrayList();
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			if (arlSpec.size() != 0) {
				objCompMaintForm.setSpecTypeList(arlSpec);
				objCompMaintForm.setSpecTypeNo(specTypeNo);
			}
		
			objCompGrpVo.setSpecTypeSeqNo(specTypeNo);	

			//Added For CR_81 on 28-Dec-09 by RR68151 to fetch Component Group Types
			ArrayList arlCompGrpType = new ArrayList();
			arlCompGrpType = objCompGrpBo.fetchCompGrpTypes(objCompGrpVo);
			if (arlCompGrpType.size() != 0) {
				objCompMaintForm.setCompGroupTypeList(arlCompGrpType);
				}
			objCompGrpVo.setCompGrpTypeSeqNo(Integer.parseInt(objCompMaintForm.
					getCompgrpCat()));
			//Added For CR_81 on 28-Dec-09 by RR68151 - Ends here
			
			arlCompGrpList = objCompGrpBo.fetchCompGroups(objCompGrpVo);
			
			if(arlCompGrpList!= null && arlCompGrpList.size()>0){
				objCompMaintForm.setCompGrpList(arlCompGrpList);
			}
			
			//To Update Components
			objComponentVo.setComponentGroupSeqNo(objCompMaintForm.getCompGrpSeqNo());
			objComponentVo.setComponentSeqNo(intCompSeqNo);
			
			objComponentVo.setUserID(strUserID);
			
			
			intStatus = objComponentBo.deleteComp(objComponentVo);
			
			if(intStatus==0){
				objCompMaintForm.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
			}
			else{
				objCompMaintForm.setMessageID(""+intStatus);
			}
				
			ArrayList arlCompList = new ArrayList();
			ComponentVO objCompVo = new ComponentVO();
			//To fetch Components
			objCompVo.setComponentGroupSeqNo(objCompMaintForm.getCompGrpSeqNo());
			
			objComponentVo.setUserID(strUserID);
			arlCompList = objComponentBo.fetchComps(objCompVo);
			objCompMaintForm.setCompList(arlCompList);
							
		}
		catch(Exception objEx){
			
			strForward  = ApplicationConstants.FAILURE;
			ErrorInfo objErrorInfo=new ErrorInfo();
			objErrorInfo.setMessage(objEx.getMessage()+"");
			LogUtil.logError(objErrorInfo);
		}
		return objActionMapping.findForward(strForward);
	}
	
}
