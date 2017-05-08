package com.EMD.LSDB.bo.SpecMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.AcceptRejectNewClauseBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.SpecMaintenance.AcceptRejectNewClauseDAO;
import com.EMD.LSDB.vo.common.ClauseVO;

/**
 * @author AK49339
 * 
 */
public class AcceptRejectNewClauseBO implements AcceptRejectNewClauseBI {
	
	public static AcceptRejectNewClauseBO objAcceptRejectNewClauseBO = null;
	
	public synchronized static AcceptRejectNewClauseBO getInstance() {
		
		if (objAcceptRejectNewClauseBO == null) {
			objAcceptRejectNewClauseBO = new AcceptRejectNewClauseBO();
		}
		return objAcceptRejectNewClauseBO;
	}
	
	private AcceptRejectNewClauseBO() {
	}
	
	/***************************************************************************
	 * This Method is used to fetch the Component Details
	 * 
	 * @param objComponentVo
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList fetchNewClauses(ClauseVO objClauseVO) throws EMDException,
	Exception {
		LogUtil
		.logMessage("Entering AcceptRejectNewClauseBO.fetchClauseVersions");
		
		ArrayList arlClause = null;
		try {
			arlClause = AcceptRejectNewClauseDAO.fetchNewClauses(objClauseVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in AcceptRejectNewClauseBO:BusinessException"
					+ objErrorInfo.getMessage());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in AcceptRejectNewClauseBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in AcceptRejectNewClauseBO:Exception"
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
	public int updateNewClauseChange(ClauseVO objClauseVO) throws EMDException,
	Exception {
		LogUtil
		.logMessage("Entering AcceptRejectNewClauseBO.updateClauseChange");
		
		int intReturnStatus = 0;
		try {
			intReturnStatus = AcceptRejectNewClauseDAO
			.updateNewClauseChange(objClauseVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in AcceptRejectNewClauseBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in AcceptRejectNewClauseBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in AcceptRejectNewClauseBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
}