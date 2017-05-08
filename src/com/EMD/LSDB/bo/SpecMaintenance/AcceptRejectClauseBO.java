
package com.EMD.LSDB.bo.SpecMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.AcceptRejectClauseBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.SpecMaintenance.AcceptRejectClauseDAO;
import com.EMD.LSDB.vo.common.ClauseVO;

/**
 * @author VV49326
 * 
 */
/***********************************************************************
----------------------------------------------------------------------------------------------------------
*    Date     Version   Modified by    	 Comments                              		Remarks 
* 19/01/2010    1.0      RR68151       Added two new methods for Edl Indicator     Added for CR_81
* 											Screen.  
* 													 	 
* 
* --------------------------------------------------------------------------------------------------------
**************************************************************************/


public class AcceptRejectClauseBO implements AcceptRejectClauseBI {
	
	public static AcceptRejectClauseBO objModelClauseBO = null;
	
	public synchronized static AcceptRejectClauseBO getInstance() {
		
		if (objModelClauseBO == null) {
			objModelClauseBO = new AcceptRejectClauseBO();
		}
		return objModelClauseBO;
	}
	
	private AcceptRejectClauseBO() {
	}
	
	/***************************************************************************
	 * This Method is used to fetch the Clause Details
	 * 
	 * @param objComponentVo
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList fetchClauseVersions(ClauseVO objClauseVO)
	throws EMDException, Exception {
		LogUtil.logMessage("Entering AcceptRejectClauseBO.fetchClauseVersions");
		
		ArrayList arlClause = null;
		try {
			arlClause = AcceptRejectClauseDAO.fetchClauseVersions(objClauseVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in AcceptRejectClauseBO:BusinessException"
					+ objErrorInfo.getMessage());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in AcceptRejectClauseBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in AcceptRejectClauseBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlClause;
	}
	
	/***************************************************************************
	 * This Method is used to Apply a particular Clause version as default at
	 * Order level
	 * 
	 * @param objComponentVo
	 * @return Boolean
	 * @throws EMDException
	 **************************************************************************/
	public int updateClauseChange(ClauseVO objClauseVO) throws EMDException,
	Exception {
		LogUtil.logMessage("Entering AcceptRejectClauseBO.UpdateClauseChange");
		
		int intReturnStatus = 0;
		try {
			intReturnStatus = AcceptRejectClauseDAO
			.updateClauseChange(objClauseVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in AcceptRejectClauseBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in AcceptRejectClauseBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into catch block in AcceptRejectClauseBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	/***************************************************************************
	 * This Method is used to Apply a particular Clause version as default at
	 * Order level
	 * 
	 * @param objComponentVo
	 * @return Boolean
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList fetchDeletedClauseVersions(ClauseVO objClauseVO)
	throws EMDException, Exception {
		LogUtil
		.logMessage("Entering AcceptRejectClauseBO.fetchDeletedClauseVersions");
		
		ArrayList arlClause = null;
		try {
			arlClause = AcceptRejectClauseDAO
			.fetchDeletedClauseVersions(objClauseVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in AcceptRejectClauseBO:BusinessException"
					+ objErrorInfo.getMessage());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in AcceptRejectClauseBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in AcceptRejectClauseBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlClause;
	}
	
	/***************************************************************************
	 * This Method is used to update accept/reject for clause delete at Model
	 * level
	 * 
	 * @param objComponentVo
	 * @return Boolean
	 * @throws EMDException
	 **************************************************************************/
	public int updateDeleteClause(ClauseVO objClauseVO) throws EMDException,
	Exception {
		LogUtil.logMessage("Entering AcceptRejectClauseBO.updateDeleteClause");
		
		int intReturnStatus = 0;
		try {
			intReturnStatus = AcceptRejectClauseDAO
			.updateDeleteClause(objClauseVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in AcceptRejectClauseBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in AcceptRejectClauseBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into catch block in AcceptRejectClauseBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}

//	Added for CR_81 EDL Change Indicator by RR68151 on 13-Jan-10 - Starts here
	
	/***************************************************************************
	 * This Method is used to fetch Edl Changes for a Clause at Model Level.
	 * 
	 * @param objClauseVO
	 * @return Boolean
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList fetchClauseEdlChanges(ClauseVO objClauseVO)
	throws EMDException, Exception {
		LogUtil
		.logMessage("Entering AcceptRejectClauseBO.fetchClauseEdlChanges");
		
		ArrayList arlClause = null;
		try {
			arlClause = AcceptRejectClauseDAO
			.fetchClauseEdlChanges(objClauseVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in AcceptRejectClauseBO:BusinessException"
					+ objErrorInfo.getMessage());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in AcceptRejectClauseBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in AcceptRejectClauseBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlClause;
	}
	
	/***************************************************************************
	 * This Method is used to update Edl Change for the Clause at Order Level.
	 * 
	 * @param objClauseVO
	 * @return Boolean
	 * @throws EMDException
	 **************************************************************************/
	public int updateClauseEdlChange(ClauseVO objClauseVO) throws EMDException,
	Exception {
		LogUtil.logMessage("Entering AcceptRejectClauseBO.updateClauseEdlChange");
		
		int intReturnStatus = 0;
		try {
			intReturnStatus = AcceptRejectClauseDAO
			.updateClauseEdlChange(objClauseVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in AcceptRejectClauseBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in AcceptRejectClauseBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into catch block in AcceptRejectClauseBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
//	Added for CR_81 EDL Change Indicator by RR68151 on 13-Jan-10 - Ends here
}
