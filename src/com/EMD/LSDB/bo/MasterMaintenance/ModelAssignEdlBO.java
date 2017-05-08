/**
 * 
 */
package com.EMD.LSDB.bo.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.ModelAssignEdlBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.MasterMaintenance.ModelAssignEdlDAO;
import com.EMD.LSDB.vo.common.ClauseVO;

/**
 * @author RR68151
 * 
 */
public class ModelAssignEdlBO implements ModelAssignEdlBI {
	
	public static ModelAssignEdlBO objModelClauseBO = null;
	
	public synchronized static ModelAssignEdlBO getInstance() {
		
		if (objModelClauseBO == null) {
			objModelClauseBO = new ModelAssignEdlBO();
		}
		return objModelClauseBO;
	}
	
	private ModelAssignEdlBO() {
	}
	
	/***************************************************************************
	 * This Method is used to fetch the Characteristic Group Combinations
	 * 
	 * @param objClauseVO
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList fetchCharGrpCombntn(ClauseVO objClauseVO)
	throws EMDException, Exception {
		LogUtil
		.logMessage("Entering ModelAssignEdlBO.fetchCharGrpCombntn");
		
		ArrayList arlCharGrpCombntn = null;
		try {
			arlCharGrpCombntn = ModelAssignEdlDAO
			.fetchCharGrpCombntn(objClauseVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in ModelAssignEdlBO:BusinessException"
					+ objErrorInfo.getMessage());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelAssignEdlBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into catch block in ModelAssignEdlBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlCharGrpCombntn;
	}

	/***************************************************************************
	 * This Method is used to insert the Characteristic Group
	 * Combination
	 * 
	 * @param objClauseVO
	 * @return integer
	 * @throws EMDException
	 **************************************************************************/
	public int insertCharGrpCombntn(ClauseVO objClauseVO)
	throws EMDException, Exception {
		LogUtil
		.logMessage("Entering ModelAssignEdlBO.insertCharGrpCombntn");
		
		int intStatus = 0;
		try {
			intStatus = ModelAssignEdlDAO
			.insertCharGrpCombntn(objClauseVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in ModelAssignEdlBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelAssignEdlBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into catch block in ModelAssignEdlBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intStatus;
	}
	
	/***************************************************************************
	 * This Method is used to update EDL/RefEDL value of a Characteristic Group
	 * Combination
	 * 
	 * @param objClauseVO
	 * @return integer
	 * @throws EMDException
	 **************************************************************************/
	public int updateCharGrpCombntn(ClauseVO objClauseVO)
	throws EMDException, Exception {
		LogUtil
		.logMessage("Entering ModelAssignEdlBO.updateCharGrpCombntn");
		
		int intStatus = 0;
		try {
			intStatus = ModelAssignEdlDAO
			.updateCharGrpCombntn(objClauseVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in ModelAssignEdlBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelAssignEdlBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into catch block in ModelAssignEdlBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intStatus;
	}
	
	/***************************************************************************
	 * This Method is used to delete the Characteristic Group
	 * Combination
	 * 
	 * @param objClauseVO
	 * @return integer
	 * @throws EMDException
	 **************************************************************************/
	public int deleteCharGrpCombntn(ClauseVO objClauseVO)
	throws EMDException, Exception {
		LogUtil
		.logMessage("Entering ModelAssignEdlBO.deleteCharGrpCombntn");
		
		int intStatus = 0;
		try {
			intStatus = ModelAssignEdlDAO
			.deleteCharGrpCombntn(objClauseVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in ModelAssignEdlBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelAssignEdlBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into catch block in ModelAssignEdlBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intStatus;
	}
}