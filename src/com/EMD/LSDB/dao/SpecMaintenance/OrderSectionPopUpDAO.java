package com.EMD.LSDB.dao.SpecMaintenance;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.common.DBHelper;
import com.EMD.LSDB.dao.common.EMDDAO;
import com.EMD.LSDB.dao.common.EMDQueries;
import com.EMD.LSDB.vo.common.ClauseVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business methods for Saving OrderSectionPopUp
 ******************************************************************************/
public class OrderSectionPopUpDAO extends EMDDAO {
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objClauseVO
	 *            the object for Inserting OrderSection
	 * @return Integer that returns 0 or 1
	 * @throws EMDException
	 **************************************************************************/
	public static int savePartOf(ClauseVO objClauseVO) throws EMDException {
		LogUtil.logMessage("Enter into OrderPerfCurveDAO:savePartOf");
		
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		int intReturn = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		try {
			strLogUser = objClauseVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_UPDATE_ORDR_SEC_POPUP);
			objCallableStatement.setInt(1, objClauseVO.getParentClauseSeqNo());
			objCallableStatement.setInt(2, objClauseVO.getVersionNo());
			objCallableStatement.setInt(3, objClauseVO.getOrderKey());
			objCallableStatement.setInt(4, objClauseVO.getSubSectionSeqNo());
			objCallableStatement.setInt(5, objClauseVO.getClauseSeqNo());
			objCallableStatement.setString(6, objClauseVO.getClauseNum());
			objCallableStatement.setString(7, objClauseVO.getUserID());
			objCallableStatement.setInt(8, objClauseVO.getPartOfClauseNo());
			objCallableStatement.registerOutParameter(9, Types.INTEGER);
			objCallableStatement.registerOutParameter(10, Types.VARCHAR);
			objCallableStatement.registerOutParameter(11, Types.VARCHAR);
			objConnection.setAutoCommit(false);
			
			intReturn = objCallableStatement.executeUpdate();
			
			if (intReturn > 0) {
				intReturn = 0;
				
			}
			
			intLSDBErrorID = (int) objCallableStatement.getInt(9);
			strOracleCode = (String) objCallableStatement.getString(10);
			strErrorMessage = (String) objCallableStatement.getString(11);
			
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Enters into Error Loop:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessageID("" + intLSDBErrorID);
				LogUtil.logMessage("Error message in ErrorInfo:"
						+ objErrorInfo.getMessageID());
				throw new DataAccessException(objErrorInfo);
				
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {// Un
				// handled
				// exception
				
				LogUtil.logMessage("strOracleCode:" + strOracleCode);
				ErrorInfo objErrorInfo = new ErrorInfo();
				StringBuffer sbErrorMessage = new StringBuffer();
				sbErrorMessage.append(strOracleCode + " ");
				sbErrorMessage.append(strErrorMessage);
				LogUtil.logMessage("sbErrorMessage.toString():"
						+ sbErrorMessage.toString());
				objErrorInfo.setMessage(sbErrorMessage.toString());
				LogUtil.logError(objErrorInfo);
				objConnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
		} catch (DataAccessException objDatExp) {
			ErrorInfo objErrorInfo = objDatExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into DataAccess Exception block in DAO:.."
					+ objDatExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDatExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil.logMessage("Enters into AppException block in  DAO :"
					+ objErrorInfo.getMessage());
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception exception...");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(null, objCallableStatement,
						objConnection);
			}
			
			catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception exception...");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return intReturn;
	}
	
}
