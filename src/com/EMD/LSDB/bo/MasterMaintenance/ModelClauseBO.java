package com.EMD.LSDB.bo.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.ModelClauseBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.MasterMaintenance.ModelClauseDAO;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.ComponentVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business methods for the Clause
 ******************************************************************************/

public class ModelClauseBO implements ModelClauseBI {
	public static ModelClauseBO objaddClauseBO = null;
	
	public synchronized static ModelClauseBO getInstance() {
		if (objaddClauseBO == null) {
			objaddClauseBO = new ModelClauseBO();
		}
		return objaddClauseBO;
	}
	
	private ModelClauseBO() {
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objClauseVO
	 *            the object for inserting clause
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public synchronized int insertClause(ClauseVO objClauseVO)
	throws EMDException, Exception {
		int intReturnStatus = 0;
		//int intClauseDescErrorID=904; //Commented for CR-121
		try {
			LogUtil.logMessage("Inside the insertClause method of AddClauseBO");
			/* Commented for CR-121 
			if(objClauseVO.getClauseDesc().length() > 4000)
			{
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessageID("" + intClauseDescErrorID);
				LogUtil.logMessage("Error message in ErrorInfo:"
						+ objErrorInfo.getMessageID());
				throw new BusinessException(objErrorInfo);
			}	*/
			intReturnStatus = ModelClauseDAO.insertClause(objClauseVO);
		}
		
		catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelClauseBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelClauseBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ModelClauseBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objClauseVO
	 *            the object for fetching ParentClause clause
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList fetchParentClause(ClauseVO objClauseVO)
	throws EMDException, Exception {
		ArrayList arlParentClause = null;
		try {
			LogUtil.logMessage("Inside the ModelClauseBO :fetchParentClause ");
			arlParentClause = ModelClauseDAO.fetchParentClause(objClauseVO);
		}
		
		catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelClauseBO:BusinessException"
					+ objErrorInfo.getMessage());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelClauseBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ModelClauseBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlParentClause;
		
	}
	
	//Added For CR_88 on 4july10 by Sd41630
	
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objClauseVO
	 *            the object for updateRearrangeClauses
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public synchronized int updateRearrangeClauses(ClauseVO objClauseVO)
	throws EMDException, Exception {
		int intReturnStatus = 0;
		try {
			LogUtil.logMessage("Inside the insertClause method of AddClauseBO");
			intReturnStatus = ModelClauseDAO.updateRearrangeClauses(objClauseVO);
		}
		
		catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelClauseBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelClauseBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ModelClauseBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
		
	}
	/***************************************************************************
	 * Added for CR_99 to validate clause Description
	 * @author Satyam Computer Services Ltd
	 * @version 1.0 by RJ85495
	 * @param objClauseVO
	 *            the object for updateRearrangeClauses
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList validateClauseDescription(ClauseVO objClauseVO) throws EMDException, Exception {
		ArrayList arlClauseDesc = null;
		try{
			LogUtil.logMessage("Inside the validateClauseDescription method of AddClauseBO");
			arlClauseDesc = ModelClauseDAO.validateClauseDescription(objClauseVO);
		}
		catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelClauseBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelClauseBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ModelClauseBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlClauseDesc;
	}
	
	
	/***************************************************************************
	 * Added for CR_109 to fetch clauses for Clauses By Components Report
	 * @author Satyam Computer Services Ltd 
	 * @version 1.0 by ER91220	
	 * @param objComponentVO - the object for fetching Model level clauses
	 * @return ClauseList
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList fetchClauses(ComponentVO objComponentVo)
	throws EMDException, Exception {
		ArrayList arlClauses = null;
		try {
			LogUtil.logMessage("Inside the ModelClauseBO :fetchParentClause ");
			arlClauses = ModelClauseDAO.fetchClauses(objComponentVo);
		}
		
		catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelClauseBO:BusinessException"
					+ objErrorInfo.getMessage());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelClauseBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ModelClauseBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlClauses;
		
	}
	
//	Added For CR_134 
		
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objClauseVO
	 *            the object for updateRearrangeClauses
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public synchronized int updateUserMarker(ClauseVO objClauseVO) throws EMDException, Exception {
		int intReturnStatus = 0;
		try {
			LogUtil.logMessage("Inside the updateUserMarker method of ModelClauseBO");
			intReturnStatus = ModelClauseDAO.updateUserMarker(objClauseVO);
		}
		
		catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelClauseBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelClauseBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ModelClauseBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		LogUtil.logMessage("intReturnStatus:" + intReturnStatus);
		return intReturnStatus;
		
	}
	
}
