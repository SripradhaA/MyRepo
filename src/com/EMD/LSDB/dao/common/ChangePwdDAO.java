package com.EMD.LSDB.dao.common;

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
import com.EMD.LSDB.vo.common.PasswordVO;

public class ChangePwdDAO extends EMDDAO {
	
	private ChangePwdDAO() {
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param SpecificationItemVO
	 *            the object for inserting spec item
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int updatePwd(PasswordVO objPasswordVO) throws EMDException {
		LogUtil.logMessage("Entering ChangePwdDAO.updatePwd");
		Connection objConnnection = null;
		CallableStatement callableStatement = null;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		int intStatus = 0;
		String strLogUser = "";
		try {
			strLogUser = objPasswordVO.getUserId();
			objConnnection = DBHelper.prepareConnection();
			
			callableStatement = objConnnection
			.prepareCall(EMDQueries.SP_UPDATE_PWD);
			callableStatement.setString(1, objPasswordVO.getNewPassword());
			callableStatement.setString(2, objPasswordVO.getUserId());
			callableStatement.setString(3, objPasswordVO.getUserID());
			callableStatement.setString(4, objPasswordVO.getFlag());
			callableStatement.registerOutParameter(5, Types.INTEGER);
			callableStatement.registerOutParameter(6, Types.VARCHAR);
			callableStatement.registerOutParameter(7, Types.VARCHAR);
			intStatus = callableStatement.executeUpdate();
			
			LogUtil
			.logMessage("Inside the updateSpecificationItem method of OrderSpecificationDAO :intStatus .."
					+ intStatus);
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			intLSDBErrorID = (int) callableStatement.getInt(5);
			strOracleCode = (String) callableStatement.getString(6);
			strErrorMessage = (String) callableStatement.getString(7);
			
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Error ID:" + intLSDBErrorID);
				
			}
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
				StringBuffer sb = new StringBuffer();
				sb.append(strOracleCode + " ");
				sb.append(strErrorMessage);
				objErrorInfo.setMessage(sb.toString());
				throw new ApplicationException(objErrorInfo);
			}
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into DataAccess Exception block in ChangePwdDAO:updatePwd"
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ChangePwdDAO:updatePwd"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ChangePwdDAO:updatePwd"
					+ objExp.getMessage());
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(null, callableStatement,
						objConnnection);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in ChangePwdDAO:updatePwd"
						+ objExp.getMessage());
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return intStatus;
		
	}
}
