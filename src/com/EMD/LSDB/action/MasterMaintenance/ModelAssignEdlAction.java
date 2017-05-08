/**
 * 
 */
package com.EMD.LSDB.action.MasterMaintenance;

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
import com.EMD.LSDB.bo.serviceinterface.ModelBI;
import com.EMD.LSDB.bo.serviceinterface.ModelClauseBI;
import com.EMD.LSDB.bo.serviceinterface.ModelAssignEdlBI;
import com.EMD.LSDB.bo.serviceinterface.ModelCompBI;
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
import com.EMD.LSDB.form.MasterMaintenance.ModelAssignEdlForm;
import com.EMD.LSDB.vo.common.ClauseTableDataVO;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SpecTypeVo;
import com.EMD.LSDB.vo.common.StandardEquipVO;
import com.EMD.LSDB.vo.common.SubSectionVO;

/***************************************************************************
 * ------------------------------------------------------------------------------------------------------
 *     Date         version  create by          	comments                              Remarks 
 *  04/05/2010       1.2       SD41630    Updated updateCharGrpCombntn method             Added for CR_85
 *  04/05/2010       1.3       SD41630    Added new  methods as followes                  Added for CR_85
 *                                       fetchLinkedClause and 	unlinkClause										 	 
 * --------------------------------------------------------------------------------------------------------
 * **************************************************************************/
public class ModelAssignEdlAction extends EMDAction {
	
	public ModelAssignEdlAction() {
		
	}
	
	/*******************************************************************************************
	 * Added for LSDB_CR_81 Locomotive and Power Products change
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
		
		LogUtil.logMessage("Entering ModelAssignEdlAction.initLoad");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ModelAssignEdlForm objModelAssignEdlForm = (ModelAssignEdlForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		ArrayList arlSpec = null;
		int specTypeNo = -1;
		int modleSeqNo = -1;
		String strSpecTypeNo = null;
		String strModleSeqNo = null;
		ArrayList arlModelList = null;
		
		
		
		try {
			
			//CR_83 lines are added ahere 
			strSpecTypeNo = (String) objHttpServletRequest
			.getParameter("specTypeNo");
	strModleSeqNo = (String) objHttpServletRequest
			.getParameter("modelseqno");
	
	if (strSpecTypeNo != null) {
		objSession.setAttribute("SPEC_TYPE_NO", strSpecTypeNo);
    	specTypeNo = Integer.parseInt(strSpecTypeNo);
		objModelAssignEdlForm.setSpecTypeNo(specTypeNo);

	} else if (objSession.getAttribute("SPEC_TYPE_NO") != null) {
		if (strSpecTypeNo == null || "".equals(strSpecTypeNo)) {

			strSpecTypeNo = (String) objSession
					.getAttribute("SPEC_TYPE_NO");
			specTypeNo = Integer.parseInt(strSpecTypeNo);
			objModelAssignEdlForm.setSpecTypeNo(specTypeNo);
			}
	}

	if (strModleSeqNo != null) {

		objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
		modleSeqNo = Integer.parseInt(strModleSeqNo);
		objModelAssignEdlForm.setModelseqno(modleSeqNo);

	} else if (objSession.getAttribute("MODEL_SEQ_NO") != null) {

		if (strModleSeqNo == null || "".equals(strModleSeqNo)) {
			strModleSeqNo = (String) objSession
					.getAttribute("MODEL_SEQ_NO");
			modleSeqNo = Integer.parseInt(strModleSeqNo);
			objModelAssignEdlForm.setModelseqno(modleSeqNo);
		}
	}
	if(specTypeNo!=-1 && specTypeNo!=0) //Updated for CR-118 for loading model from session
	{
		ModelVo objModelVo = new ModelVo();
		objModelVo.setUserID(objLoginVo.getUserID());
		objModelVo.setSpecTypeSeqNo(specTypeNo);
		ModelBI objModelBO = ServiceFactory.getModelBO();
		if (objModelBO.fetchModels(objModelVo) != null) {
			arlModelList = objModelBO.fetchModels(objModelVo);
			objModelAssignEdlForm.setListModels(arlModelList);
			objModelAssignEdlForm.setModelseqno(modleSeqNo);
			}

	}
	//CR_83 lines area ends here
			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);
			objModelAssignEdlForm.setSpecTypeList(arlSpec);
			
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
	 * * * * This Method is used to populate the Model Dropdown on PageLoad
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward fetchModels(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering ModelAssignEdlAction.fetchModels");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ModelAssignEdlForm objModelAssignEdlForm = (ModelAssignEdlForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		ArrayList arlSpec = null;
		int specTypeNo = -1;
		int modleSeqNo = -1;
		String strSpecTypeNo = null;
		String strModleSeqNo = null;
		try {
			
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			ArrayList arlModelList = null;
			ModelBI objModelBo = ServiceFactory.getModelBO();

			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);

			objModelAssignEdlForm
			.setPrevSpecType(objModelAssignEdlForm
					.getSpecTypeNo());
			
			LogUtil.logMessage("objModelAssignEdlForm.getSpecTypeNo() :"
					+ objModelAssignEdlForm.getSpecTypeNo());
			objModelVo.setSpecTypeSeqNo(objModelAssignEdlForm
					.getSpecTypeNo());
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			objModelAssignEdlForm.setSpecTypeList(arlSpec);
			
			if (arlModelList != null)
				objModelAssignEdlForm.setListModels(arlModelList);
			objModelAssignEdlForm.setClauseDes("");
			
//CR_83 holding the value from form in the sesiion 
			
			//objModelMaintForm.setSpecTypeNo(specTypeNo);
			if(strSpecTypeNo !=null){
			strSpecTypeNo=(String) objHttpServletRequest.getParameter("specTypeNo");
			
			objSession.setAttribute("SPEC_TYPE_NO",strSpecTypeNo);
			specTypeNo=Integer.parseInt(strSpecTypeNo);
			objModelAssignEdlForm.setSpecTypeNo(specTypeNo);
			
			strModleSeqNo=(String) objHttpServletRequest.getParameter("modelseqno");
			
			objSession.setAttribute("MODEL_SEQ_NO", strModleSeqNo);
			modleSeqNo = Integer.parseInt(strModleSeqNo);
			objModelAssignEdlForm.setModelseqno(modleSeqNo);
			}
				//CR_83 lines are ends here	
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelAssignEdlAction.fetchModels");
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
	
	/***************************************************************************
	 * * * * This Method is used to fetch the Characteristic Groups 
	 * that could be tied to the Characteristic Group Clause
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward fetchCharCompGrps(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering ModelAssignEdlAction.fetchCharCompGrps");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ModelAssignEdlForm objModelAssignEdlForm = (ModelAssignEdlForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		ArrayList arlSpec = null;
		try {
			
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			ArrayList arlModelList = null;
			ModelBI objModelBo = ServiceFactory.getModelBO();

			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);

			objModelAssignEdlForm
			.setPrevSpecType(objModelAssignEdlForm
					.getSpecTypeNo());
			
			LogUtil.logMessage("objModelAssignEdlForm.getSpecTypeNo() :"
					+ objModelAssignEdlForm.getSpecTypeNo());
			objModelVo.setSpecTypeSeqNo(objModelAssignEdlForm
					.getSpecTypeNo());
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			objModelAssignEdlForm.setSpecTypeList(arlSpec);
			
			if (arlModelList != null)
				objModelAssignEdlForm.setListModels(arlModelList);
			
			ComponentVO objComponentVO = new ComponentVO();		
			ArrayList arlCompGroupList = new ArrayList();
			
			objComponentVO.setUserID(objLoginVo.getUserID());
			objComponentVO.setSubSectionSeqNo(objModelAssignEdlForm.getSubSectionSeqNo());
			objComponentVO.setModelSeqNo(objModelAssignEdlForm.getModelseqno());
			//objComponentVO.setCompGrpTypeSeqNo(ApplicationConstants.COMPGRPTYPE_CHARSTC); Commented for CR-125
			
			ModelCompBI objModelCompBO = ServiceFactory.getModelCompBO();
			arlCompGroupList = objModelCompBO.fetchModelComps(objComponentVO);
			
			if (arlCompGroupList != null && arlCompGroupList.size() > 0) {
				objModelAssignEdlForm.setCompGroupList(arlCompGroupList);
				objModelAssignEdlForm.setClauseSeqNo(objModelAssignEdlForm.getClauseSeqNo());
				objModelAssignEdlForm.setCharstcEdlNo("");
				objModelAssignEdlForm.setCharstcRefEDLNo("");
			}
			else
				objModelAssignEdlForm.setMethod("fetchCharCompGrps");	
			
			ClauseVO objClauseVO = new ClauseVO();
			ArrayList arlCharGrpCombntn = new ArrayList();
			
			objClauseVO.setClauseSeqNo(objModelAssignEdlForm.getClauseSeqNo());
			objClauseVO.setUserID(objLoginVo.getUserID());
			
			ModelAssignEdlBI objModelAssignEdlBI = ServiceFactory.getModelAssignEdlBO();
			arlCharGrpCombntn = objModelAssignEdlBI.fetchCharGrpCombntn(objClauseVO);

			if (arlCharGrpCombntn != null && arlCharGrpCombntn.size() > 0)
				objModelAssignEdlForm.setCharGrpCombntnList(arlCharGrpCombntn);	
			else	{
				if (objModelAssignEdlForm.getMethod().equalsIgnoreCase("fetchCharCompGrps"))
					objModelAssignEdlForm.setMethod("fetchCharCompGrps");	
				else
					objModelAssignEdlForm.setMethod("");
			}

		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelAssignEdlAction.fetchCharCompGrps");
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

	/***************************************************************************
	 * * * * This Method is used to fetch the Characteristic Group
	 * Combinations tied with the Clause.
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward insertCharGrpCombntn(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering ModelAssignEdlAction.insertCharGrpCombntn");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ModelAssignEdlForm objModelAssignEdlForm = (ModelAssignEdlForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		ArrayList arlSpec = null;
		int intStatus = 0;
		try {
		
			String compGrpArray = objHttpServletRequest
								.getParameter("compGrpArray");
			String compArray = objHttpServletRequest
								.getParameter("compArray");
			
			String[] compGrps = compGrpArray.split(ApplicationConstants.COMMA);
			String[] comps = compArray.split(ApplicationConstants.COMMA);
			
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			ArrayList arlModelList = null;
			
			ModelBI objModelBo = ServiceFactory.getModelBO();

			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);

			objModelAssignEdlForm
			.setPrevSpecType(objModelAssignEdlForm
					.getSpecTypeNo());
			
			LogUtil.logMessage("objModelAssignEdlForm.getSpecTypeNo() :"
					+ objModelAssignEdlForm.getSpecTypeNo());
			objModelVo.setSpecTypeSeqNo(objModelAssignEdlForm
					.getSpecTypeNo());
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			objModelAssignEdlForm.setSpecTypeList(arlSpec);
			
			if (arlModelList != null)
				objModelAssignEdlForm.setListModels(arlModelList);
			
			ComponentVO objComponentVO = new ComponentVO();		
			ArrayList arlCompGroupList = new ArrayList();
			
			objComponentVO.setUserID(objLoginVo.getUserID());
			objComponentVO.setSubSectionSeqNo(objModelAssignEdlForm.getSubSectionSeqNo());
			objComponentVO.setModelSeqNo(objModelAssignEdlForm.getModelseqno());
			//objComponentVO.setCompGrpTypeSeqNo(ApplicationConstants.COMPGRPTYPE_CHARSTC);Commented for CR-125
			
			ModelCompBI objModelCompBO = ServiceFactory.getModelCompBO();
			arlCompGroupList = objModelCompBO.fetchModelComps(objComponentVO);
			
			if (arlCompGroupList != null && arlCompGroupList.size() > 0) {
				objModelAssignEdlForm.setCompGroupList(arlCompGroupList);
			}
			else
				objModelAssignEdlForm.setMethod("insertCharGrpCombntn");	
			
			LogUtil.logMessage("objModelAssignEdlForm.getClauseSeqNo() :"
					+ objModelAssignEdlForm.getClauseSeqNo());
			
			ClauseVO objClauseVO = new ClauseVO();

			objClauseVO.setComponentGroupSeqNo(compGrps);
			objClauseVO.setCompSeqNo(comps);
			objClauseVO.setClauseSeqNo(objModelAssignEdlForm.getClauseSeqNo());
			objClauseVO.setCharEdlNo(objModelAssignEdlForm.getCharstcEdlNo());
			objClauseVO.setCharRefEDLNo(objModelAssignEdlForm.getCharstcRefEDLNo());
			objClauseVO.setUserID(objLoginVo.getUserID());			
		
			ModelAssignEdlBI objModelAssignEdlBI = ServiceFactory.getModelAssignEdlBO();		
			intStatus = objModelAssignEdlBI.insertCharGrpCombntn(objClauseVO);

			if (intStatus == 0) {
				objModelAssignEdlForm.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				objModelAssignEdlForm.setCharstcEdlNo("");
				objModelAssignEdlForm.setCharstcRefEDLNo("");
			} else {
				objModelAssignEdlForm.setMessageID("" + intStatus);
			}
			
			ArrayList arlCharGrpCombntn = new ArrayList();

			arlCharGrpCombntn = objModelAssignEdlBI.fetchCharGrpCombntn(objClauseVO);

			if (arlCharGrpCombntn != null && arlCharGrpCombntn.size() > 0)
				objModelAssignEdlForm.setCharGrpCombntnList(arlCharGrpCombntn);	
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelAssignEdlAction.insertCharGrpCombntn");
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
	
	/***************************************************************************
	 * * * * This Method is used to update EDL/RefEDL value of an existing
	 * Characteristic Group Combination tied with the Clause.
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward updateCharGrpCombntn(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering ModelAssignEdlAction.updateCharGrpCombntn");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ModelAssignEdlForm objModelAssignEdlForm = (ModelAssignEdlForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		ArrayList arlSpec = null;
		int intStatus = 0;
		try {
				
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			ArrayList arlModelList = null;
			
			ModelBI objModelBo = ServiceFactory.getModelBO();

			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);

			objModelAssignEdlForm
			.setPrevSpecType(objModelAssignEdlForm
					.getSpecTypeNo());
			objModelVo.setSpecTypeSeqNo(objModelAssignEdlForm
					.getSpecTypeNo());
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			objModelAssignEdlForm.setSpecTypeList(arlSpec);
			
			if (arlModelList != null)
				objModelAssignEdlForm.setListModels(arlModelList);
			
			ComponentVO objComponentVO = new ComponentVO();		
			ArrayList arlCompGroupList = new ArrayList();
			
			objComponentVO.setUserID(objLoginVo.getUserID());
			objComponentVO.setSubSectionSeqNo(objModelAssignEdlForm.getSubSectionSeqNo());
			objComponentVO.setModelSeqNo(objModelAssignEdlForm.getModelseqno());
			//objComponentVO.setCompGrpTypeSeqNo(ApplicationConstants.COMPGRPTYPE_CHARSTC);Commented for CR-125
			
			ModelCompBI objModelCompBO = ServiceFactory.getModelCompBO();
			arlCompGroupList = objModelCompBO.fetchModelComps(objComponentVO);
			
			if (arlCompGroupList != null && arlCompGroupList.size() > 0) {
				objModelAssignEdlForm.setCompGroupList(arlCompGroupList);
			}
			else
				objModelAssignEdlForm.setMethod("updateCharGrpCombntn");	
			ClauseVO objClauseVO = new ClauseVO();
			objClauseVO.setCombntnSeqNo(objModelAssignEdlForm.getCombntnSeqNo());
			objClauseVO.setClauseSeqNo(objModelAssignEdlForm.getClauseSeqNo());
			objClauseVO.setCharEdlNo(objModelAssignEdlForm.getHdnCharEdlNo());
			objClauseVO.setCharRefEDLNo(objModelAssignEdlForm.getHdnCharRefEdlNo());
			objClauseVO.setUserID(objLoginVo.getUserID());	
			
			//Added for CR_85	set caluseseqno to link to combination compounents					
			objClauseVO.setLinkClaSeqNo(objModelAssignEdlForm.getLinkClaSeqNo());
			ModelAssignEdlBI objModelAssignEdlBI = ServiceFactory.getModelAssignEdlBO();		
			intStatus = objModelAssignEdlBI.updateCharGrpCombntn(objClauseVO);
			if (intStatus == 0) {
				objModelAssignEdlForm.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				objModelAssignEdlForm.setCharstcEdlNo("");
				objModelAssignEdlForm.setCharstcRefEDLNo("");
				objModelAssignEdlForm.setLinkClaSeqNo(0);
			} else {
				objModelAssignEdlForm.setMessageID("" + intStatus);
			}
			
			ArrayList arlCharGrpCombntn = new ArrayList();

			arlCharGrpCombntn = objModelAssignEdlBI.fetchCharGrpCombntn(objClauseVO);

			if (arlCharGrpCombntn != null && arlCharGrpCombntn.size() > 0)
				objModelAssignEdlForm.setCharGrpCombntnList(arlCharGrpCombntn);	
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelAssignEdlAction.updateCharGrpCombntn");
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
	
	/***************************************************************************
	 * * * * This Method is used to delete an existing
	 * Characteristic Group Combination tied with the Clause.
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward deleteCharGrpCombntn(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering ModelAssignEdlAction.deleteCharGrpCombntn");
		String strForwardKey = ApplicationConstants.SUCCESS;
		ModelAssignEdlForm objModelAssignEdlForm = (ModelAssignEdlForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		ArrayList arlSpec = null;
		int intStatus = 0;
		try {
				
			ModelVo objModelVo = new ModelVo();
			objModelVo.setUserID(objLoginVo.getUserID());
			ArrayList arlModelList = null;
			
			ModelBI objModelBo = ServiceFactory.getModelBO();

			SpecTypeVo objSpecTypeVo = new SpecTypeVo();
			objSpecTypeVo.setUserID(objLoginVo.getUserID());
			SpecTypeBI objSpecTypeBI = ServiceFactory.getSpecTypeBO();
			arlSpec = objSpecTypeBI.fetchSpecTypes(objSpecTypeVo);

			objModelAssignEdlForm
			.setPrevSpecType(objModelAssignEdlForm
					.getSpecTypeNo());
			
			LogUtil.logMessage("objModelAssignEdlForm.getSpecTypeNo() :"
					+ objModelAssignEdlForm.getSpecTypeNo());
			objModelVo.setSpecTypeSeqNo(objModelAssignEdlForm
					.getSpecTypeNo());
			
			arlModelList = objModelBo.fetchModels(objModelVo);
			
			objModelAssignEdlForm.setSpecTypeList(arlSpec);
			
			if (arlModelList != null)
				objModelAssignEdlForm.setListModels(arlModelList);
			
			ComponentVO objComponentVO = new ComponentVO();		
			ArrayList arlCompGroupList = new ArrayList();
			
			objComponentVO.setUserID(objLoginVo.getUserID());
			objComponentVO.setSubSectionSeqNo(objModelAssignEdlForm.getSubSectionSeqNo());
			objComponentVO.setModelSeqNo(objModelAssignEdlForm.getModelseqno());
			//objComponentVO.setCompGrpTypeSeqNo(ApplicationConstants.COMPGRPTYPE_CHARSTC);Commented for CR-125
			
			ModelCompBI objModelCompBO = ServiceFactory.getModelCompBO();
			arlCompGroupList = objModelCompBO.fetchModelComps(objComponentVO);
			
			if (arlCompGroupList != null && arlCompGroupList.size() > 0) {
				objModelAssignEdlForm.setCompGroupList(arlCompGroupList);
			}
			else
				objModelAssignEdlForm.setMethod("deleteCharGrpCombntn");	

			LogUtil.logMessage("objModelAssignEdlForm.getClauseSeqNo() :"
					+ objModelAssignEdlForm.getClauseSeqNo());
			
			ClauseVO objClauseVO = new ClauseVO();

			objClauseVO.setCombntnSeqNo(objModelAssignEdlForm.getCombntnSeqNo());
			objClauseVO.setClauseSeqNo(objModelAssignEdlForm.getClauseSeqNo());
			objClauseVO.setUserID(objLoginVo.getUserID());			
		
			ModelAssignEdlBI objModelAssignEdlBI = ServiceFactory.getModelAssignEdlBO();		
			intStatus = objModelAssignEdlBI.deleteCharGrpCombntn(objClauseVO);

			if (intStatus == 0) {
				objModelAssignEdlForm.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				objModelAssignEdlForm.setCharstcEdlNo("");
				objModelAssignEdlForm.setCharstcRefEDLNo("");
			} else {
				objModelAssignEdlForm.setMessageID("" + intStatus);
			}
			
			ArrayList arlCharGrpCombntn = new ArrayList();

			arlCharGrpCombntn = objModelAssignEdlBI.fetchCharGrpCombntn(objClauseVO);

			if (arlCharGrpCombntn != null && arlCharGrpCombntn.size() > 0)
				objModelAssignEdlForm.setCharGrpCombntnList(arlCharGrpCombntn);	
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelAssignEdlAction.deleteCharGrpCombntn");
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
//Added for CR_85
	/***************************************************************************
	 * * * * This Method is used to view/unlink the clause form combnation of the compounts  
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward fetchLinkedClause(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering ModelAssignEdlAction.fetchLinkedClause");
		String strForwardKey = ApplicationConstants.View_Unlink_Clause;
		ModelAssignEdlForm objModelAssignEdlForm = (ModelAssignEdlForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		ArrayList arlLinkedClause = null;
		int intCombntnSeqNo=0;
		int intmodelSeqNo=0;
		String modelName=null;
		String specType=null;
		String strmodelSeqNo;
		try {
			if ((String) objHttpServletRequest.getParameter("modelSeqNo") != null) {
				 intmodelSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("modelSeqNo").toString());
			}
			
			if ((String) objHttpServletRequest.getParameter("combntnSeqNo") != null) {
				 intCombntnSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("combntnSeqNo").toString());
			}
			
			if ((String) objHttpServletRequest.getParameter("selectedModelName") != null) {
				 modelName = ((String)objHttpServletRequest
						.getParameter("selectedModelName").toString());
			}
			
			

			if ((String) objHttpServletRequest.getParameter("specType") != null) {
				specType = ((String)objHttpServletRequest
						.getParameter("specType").toString());
			}
			
			SubSectionVO objSubSecMaintVO = new SubSectionVO();
			
					objSubSecMaintVO.setUserID(objLoginVo.getUserID());
					objSubSecMaintVO.setCombntnSeqNo(intCombntnSeqNo);
					objSubSecMaintVO.setModelSeqNo(intmodelSeqNo);
			    	ViewSpecByModelBI objViewSpecByModelBO = ServiceFactory
			        .getViewSpecByModelBO();
			        arlLinkedClause = (objViewSpecByModelBO
					.viewCharactersiticGrpRpt(objSubSecMaintVO));
			
              strmodelSeqNo=String.valueOf(intmodelSeqNo);
             if(modelName==null){
            	objModelAssignEdlForm.setHdnModelName("");
            }else{
	            objModelAssignEdlForm.setHdnModelName(modelName);
	            objModelAssignEdlForm.setModelseqno(intmodelSeqNo);
            }
            if(specType==null){
            	objModelAssignEdlForm.setHdnSelSpecType("");
            }else{
              objModelAssignEdlForm.setHdnSelSpecType(specType);
            }
			/** Add the sub sections for the current section to the list **/
			
				if (arlLinkedClause != null && arlLinkedClause.size() > 0){
					objModelAssignEdlForm.setCharGrpCombntnList(arlLinkedClause);	
					}
					else{
					if (objModelAssignEdlForm.getMethod().equalsIgnoreCase("fetchLinkedClause"))
						objModelAssignEdlForm.setMethod("fetchLinkedClause");	
					else
						objModelAssignEdlForm.setMethod("");
				}
				
			objModelAssignEdlForm.setClauseUnlinked(ApplicationConstants.NO);//Changes for CR_131

		}
		
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelAssignEdlAction.fetchUnlinkClause");
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
	//Added for CR_85
	

	/***************************************************************************
	 * * * * This Method is used to unlink the clause form combnation of the compounts
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse
	 * @return ActionForward
	 * @throws EMDException
	 *  
	 **************************************************************************/
	
	public ActionForward unlinkClause(ActionMapping objActionMapping,
			ActionForm objActionForm, HttpServletRequest objHttpServletRequest,
			HttpServletResponse objHttpServletResponse)
	throws DataAccessException, ApplicationException {
		
		LogUtil.logMessage("Entering ModelAssignEdlAction.unlinkClause");
		String strForwardKey = ApplicationConstants.Unlink_Clause;
		ModelAssignEdlForm objModelAssignEdlForm = (ModelAssignEdlForm) objActionForm;
		HttpSession objSession = objHttpServletRequest.getSession(false);
		LoginVO objLoginVo = (LoginVO) objSession
		.getAttribute(ApplicationConstants.USER_IN_SESSION);
		ArrayList arlSpec = null;
		int intStatus = 0;
		String strSpecTypeNo = null;
		String strModleSeqNo = null;
		int specTypeNo=-1;
		int modleSeqNo = -1;
		int intClauseSeqNo=0;
		int intcombntnSeqNo=0;
		try {
			 if ((String) objHttpServletRequest.getParameter("clauseSeqNo") != null) {
				 intClauseSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("clauseSeqNo").toString());
			}
			
			 if ((String) objHttpServletRequest.getParameter("combntnSeqNo") != null) {
				 intcombntnSeqNo = Integer.parseInt(objHttpServletRequest
						.getParameter("combntnSeqNo").toString());
			}
			objModelAssignEdlForm.setCombntnSeqNo(intcombntnSeqNo);
			objModelAssignEdlForm.setClauseSeqNo(intClauseSeqNo);
			ClauseVO objClauseVO = new ClauseVO();
			objClauseVO.setCombntnSeqNo(objModelAssignEdlForm.getCombntnSeqNo());
			objClauseVO.setClauseSeqNo(objModelAssignEdlForm.getClauseSeqNo());
			objClauseVO.setUserID(objLoginVo.getUserID());			
			ModelAssignEdlBI objModelAssignEdlBI = ServiceFactory.getModelAssignEdlBO();		
			intStatus = objModelAssignEdlBI.deleteCharGrpCombntn(objClauseVO);
     		if (intStatus == 0) {
				objModelAssignEdlForm.setMessageID(ApplicationConstants.SUCCESS_MESSAGE_ID);
				objModelAssignEdlForm.setCharstcEdlNo("");
				objModelAssignEdlForm.setCharstcRefEDLNo("");
				objModelAssignEdlForm.setClauseUnlinked(ApplicationConstants.YES);//Changes for CR_131
			} else {
				objModelAssignEdlForm.setMessageID("" + intStatus);
				objModelAssignEdlForm.setClauseUnlinked(ApplicationConstants.NO);
			}
     		
     		SubSectionVO objSubSecMaintVO = new SubSectionVO();
			
			objSubSecMaintVO.setUserID(objLoginVo.getUserID());
			objSubSecMaintVO.setCombntnSeqNo(intcombntnSeqNo);
			objSubSecMaintVO.setModelSeqNo(objModelAssignEdlForm.getModelseqno());//Added for CR_131
	    	ViewSpecByModelBI objViewSpecByModelBO = ServiceFactory
	        .getViewSpecByModelBO();
	        ArrayList arlLinkedClause = (objViewSpecByModelBO
			.viewCharactersiticGrpRpt(objSubSecMaintVO));
     		
	        if (arlLinkedClause != null && arlLinkedClause.size() > 0){
				objModelAssignEdlForm.setCharGrpCombntnList(arlLinkedClause);	
			}
				else{
				if (objModelAssignEdlForm.getMethod().equalsIgnoreCase("fetchLinkedClause"))
					objModelAssignEdlForm.setMethod("fetchLinkedClause");	
				else
					objModelAssignEdlForm.setMethod("");
			}
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception Block in ModelAssignEdlAction.deleteCharGrpCombntn");
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

}