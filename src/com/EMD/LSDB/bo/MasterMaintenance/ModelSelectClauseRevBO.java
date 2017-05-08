/**
 * 
 */
package com.EMD.LSDB.bo.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.ModelSelectClauseRevBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.MasterMaintenance.ModelSelectClauseRevDAO;
import com.EMD.LSDB.vo.common.ClauseVO;

/**
 * @author VV49326
 * 
 */
public class ModelSelectClauseRevBO implements ModelSelectClauseRevBI {
	
	public static ModelSelectClauseRevBO objModelClauseBO = null;
	
	public synchronized static ModelSelectClauseRevBO getInstance() {
		
		if (objModelClauseBO == null) {
			objModelClauseBO = new ModelSelectClauseRevBO();
		}
		return objModelClauseBO;
	}
	
	private ModelSelectClauseRevBO() {
	}
	
	/***************************************************************************
	 * This Method is used to fetch the Component Details
	 * 
	 * @param objComponentVo
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList fetchClauseVersions(ClauseVO objClauseVO)
	throws EMDException, Exception {
		LogUtil
		.logMessage("Entering ModelSelectClauseRevBO.fetchClauseVersions");
		
		ArrayList arlClause = null;
		try {
			arlClause = ModelSelectClauseRevDAO
			.fetchClauseVersions(objClauseVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in ModelSelectClauseRevBO:BusinessException"
					+ objErrorInfo.getMessage());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelSelectClauseRevBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into catch block in ModelSelectClauseRevBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlClause;
	}
	
	/***************************************************************************
	 * This Method is used to Apply a particular Clause version as default
	 * 
	 * @param objComponentVo
	 * @return Boolean
	 * @throws EMDException
	 **************************************************************************/
	public int updateApplyDefaultClause(ClauseVO objClauseVO)
	throws EMDException, Exception {
		LogUtil
		.logMessage("Entering ModelSelectClauseRevBO.UpdateApplyDefaultClause");
		
		int intReturnStatus = 0;
		try {
			intReturnStatus = ModelSelectClauseRevDAO
			.updateApplyDefaultClause(objClauseVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in ModelSelectClauseRevBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelSelectClauseRevBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into catch block in ModelSelectClauseRevBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	/***************************************************************************
	 * This Method deleteClause is used to delete the clause and all it's
	 * subclauses and it will delete all it's versions
	 * 
	 * @param objClauseVO
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	public int deleteClause(ClauseVO objClauseVO) throws EMDException,
	Exception {
		LogUtil.logMessage("Entering ModelSelectClauseRevBO.deleteClause");
		
		int intReturnStatus = 0;
		try {
			intReturnStatus = ModelSelectClauseRevDAO.deleteClause(objClauseVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in ModelSelectClauseRevBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelSelectClauseRevBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into catch block in ModelSelectClauseRevBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	/***************************************************************************
	 * This Method deleteVersion is used to delete the selected version of a
	 * clause and all it's subclauses.
	 * 
	 * @param objClauseVO
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	public int deleteVersion(ClauseVO objClauseVO) throws EMDException,
	Exception {
		LogUtil.logMessage("Entering ModelSelectClauseRevBO.deleteVersion");
		
		int intReturnStatus = 0;
		try {
			intReturnStatus = ModelSelectClauseRevDAO
			.deleteVersion(objClauseVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in ModelSelectClauseRevBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelSelectClauseRevBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into catch block in ModelSelectClauseRevBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	/***************************************************************************
	 * This Method is used to Update Clause
	 * 
	 * @param objComponentVo
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/
	public int updateClauseVersion(ClauseVO objClauseVO) throws EMDException,
	Exception {
		LogUtil
		.logMessage("Entering ModelSelectClauseRevBO.updateClauseVersion");
		
		int intReturnStatus = 0;
		try {
			intReturnStatus = ModelSelectClauseRevDAO.updateClause(objClauseVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in ModelSelectClauseRevBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelSelectClauseRevBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into catch block in ModelSelectClauseRevBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	/** Added for LSDB_CR-61 on 03-Dec-08 * */
	/***************************************************************************
	 * This Method is used to fetch clause details for the selected Lead
	 * Component
	 * 
	 * @param objClauseVO
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList fetchClauseForLeadComp(ClauseVO objClauseVO)
	throws EMDException, Exception {
		LogUtil
		.logMessage("Entering ModelSelectClauseRevBO.fetchClauseForLeadComp");
		
		ArrayList arlClause = null;
		try {
			arlClause = ModelSelectClauseRevDAO
			.fetchClauseForLeadComp(objClauseVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in ModelSelectClauseRevBO:BusinessException"
					+ objErrorInfo.getMessage());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelSelectClauseRevBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into catch block in ModelSelectClauseRevBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlClause;
	}
	
}