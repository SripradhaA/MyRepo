package com.EMD.LSDB.bo.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.ModelCompBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.MasterMaintenance.ModelCompDAO;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.SubSectionVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business methods for the Component
 *  * ------------------------------------------------------------------------------------------------------
 *     Date            version       modify by                 comments                              Remarks 
 * 15/03/2011            1.0         SD41630          viewLeadComponetClausesByModel           	Added for CR_97
 * 										               new method has been added 
 * 										 
  **************************************************************************/


public class ModelCompBO implements ModelCompBI {
	
	public static ModelCompBO objModelCompBO = null;
	
	public synchronized static ModelCompBO getInstance() {
		if (objModelCompBO == null) {
			objModelCompBO = new ModelCompBO();
		}
		return objModelCompBO;
	}
	
	private ModelCompBO() {
	}
	
	/***************************************************************************
	 * This Method is used to fetch the Component Details
	 * 
	 * @param objComponentVo
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList fetchComps(ComponentVO objComponentVo)
	throws EMDException, Exception {
		LogUtil.logMessage("Entering ModelCompBO.fetchComps");
		
		ArrayList arlComponent = null;
		try {
			arlComponent = ModelCompDAO.fetchComps(objComponentVo);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentBo:BusinessException"
					+ objErrorInfo.getMessage());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentBo:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ComponentBo:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlComponent;
	}
	
	/***************************************************************************
	 * This Method is used to insert a new Component.
	 * 
	 * @param objComponentVo
	 * @return boolean
	 * @throws EMDException
	 **************************************************************************/
	public int insertComp(ComponentVO objComponentVo) throws EMDException,
	Exception {
		LogUtil.logMessage("Entering ModelCompBO.insertComp");
		
		int intReturnStatus = 0;
		
		try {
			intReturnStatus = ModelCompDAO.insertComp(objComponentVo);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentBo:BusinessException"
					+ objErrorInfo.getMessageID());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentBo:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ComponentBo:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	/***************************************************************************
	 * This Method is used to Modify Component Details
	 * 
	 * @param objComponentVo
	 * @return boolean
	 * @throws EMDException
	 **************************************************************************/
	public int updateComp(ComponentVO objComponentVo) throws EMDException,
	Exception {
		LogUtil.logMessage("Entering ModelCompBO.updateComp");
		int intReturnStatus = 0;
		try {
			intReturnStatus = ModelCompDAO.updateComp(objComponentVo);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentBo:BusinessException"
					+ objErrorInfo.getMessageID());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentBo:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ComponentBo:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	/***************************************************************************
	 * This Method is used to fetch the Component Details tied to Model
	 * 
	 * @param objComponentVo
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList fetchModelComps(ComponentVO objComponentVo)
	throws EMDException, Exception {
		LogUtil.logMessage("Entering ModelCompBO.fetchModelComps");
		
		ArrayList arlComponent = null;
		try {
			arlComponent = ModelCompDAO.fetchModelComps(objComponentVo);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentBo:BusinessException"
					+ objErrorInfo.getMessage());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentBo:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ComponentBo:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlComponent;
	}
	
	/***************************************************************************
	 * This Method is used to delete the Component Details tied to Model
	 * 
	 * @param objComponentVo
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/
	public int deleteComp(ComponentVO objComponentVo) throws EMDException,
	Exception {
		LogUtil.logMessage("Entering ModelCompBO.deleteComp");
		int intReturnStatus = 0;
		try {
			intReturnStatus = ModelCompDAO.deleteComp(objComponentVo);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentBo:BusinessException"
					+ objErrorInfo.getMessageID());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentBo:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ComponentBo:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	//Added for CR_93
	/***************************************************************************
	 * This Method is used to Copy the Order Component Details to Model
	 * 
	 * @param objComponentVo
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/
	public int copyCompOrdrToMdl(ComponentVO objComponentVo) throws EMDException,
	Exception {
		LogUtil.logMessage("Entering ModelCompBO.copyCompOrdrToMdl");
		int intReturnStatus = 0;
		try {
			intReturnStatus = ModelCompDAO.copyCompOrdrToMdl(objComponentVo);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentBo:BusinessException"
					+ objErrorInfo.getMessageID());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentBo:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ComponentBo:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	
	/***************************************************************************
	 * This Method is used to fetch the LeadComponentclauses for CR_97
	 * 
	 * @param objComponentVo
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList viewLeadComponentClausesByModel(ComponentVO objComponentVO)
	throws EMDException, Exception {
		LogUtil.logMessage("Entering ModelCompBO.fetchComps");
		
		ArrayList arlComponent = null;
		try {
			arlComponent = ModelCompDAO.viewLeadComponetClausesByModel(objComponentVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentBo -viewLeadComponetClausesByModel:BusinessException"
					+ objErrorInfo.getMessage());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentBo- viewLeadComponetClausesByModel:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ComponentBo -viewLeadComponetClausesByModel:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlComponent;
	}

	
	
}
