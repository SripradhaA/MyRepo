/**
 * 
 */
package com.EMD.LSDB.bo.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.ModelAddClauseRevBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.MasterMaintenance.ModelAddClauseRevDAO;
import com.EMD.LSDB.vo.common.ClauseVO;

/**
 * @author ps57222
 * 
 */
public class ModelAddClauseRevBO implements ModelAddClauseRevBI {
	
	private ModelAddClauseRevBO() {
		
	}
	
	public static ModelAddClauseRevBO objModelAddClauseRevBO;
	
	public synchronized static ModelAddClauseRevBO getInstance() {
		if (objModelAddClauseRevBO == null) {
			objModelAddClauseRevBO = new ModelAddClauseRevBO();
		}
		return objModelAddClauseRevBO;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objClauseVO
	 *            The object used for fetch clause Details
	 * @return int It returns the ClauseVO code
	 * @throws EMDException
	 **************************************************************************/
	
	public ArrayList fetchClauses(ClauseVO objClauseVO) throws EMDException,
	Exception {
		LogUtil.logMessage(" Enters into ModelAddClauseRevBO:fetchClauses");
		ArrayList arlClauseList = new ArrayList();
		
		try {
			arlClauseList = ModelAddClauseRevDAO.fetchClauses(objClauseVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelAddClauseRevBO:BusinessException"
					+ objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelAddClauseRevBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ModelAddClauseRevBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlClauseList;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objClauseVO
	 *            The object used for insert clause
	 * @return int It returns the status code
	 * @throws EMDException
	 **************************************************************************/
	
	public synchronized int insertClause(ClauseVO objClauseVO)
	throws EMDException, Exception {
		
		LogUtil.logMessage(" Enters into ModelAddClauseRevBO:insertClause");
		int intStatusCode;
		try {
			intStatusCode = ModelAddClauseRevDAO.insertClause(objClauseVO);
		}
		
		catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelAddClauseRevBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intStatusCode = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelAddClauseRevBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ModelAddClauseRevBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		
		return intStatusCode;
	}
}
