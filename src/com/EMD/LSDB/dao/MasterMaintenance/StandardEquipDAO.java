package com.EMD.LSDB.dao.MasterMaintenance;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.constant.DatabaseConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.common.DBHelper;
import com.EMD.LSDB.dao.common.EMDDAO;
import com.EMD.LSDB.dao.common.EMDQueries;
import com.EMD.LSDB.vo.common.StandardEquipVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business methods for StandardEquipment
 ******************************************************************************/
public class StandardEquipDAO extends EMDDAO {
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objStandardEquipVO
	 *            the object for searching StandardEquipment
	 * @return arrayList the list contains the models
	 * @throws EMDException
	 **************************************************************************/
	public static ArrayList fetchStandardEquipment(
			StandardEquipVO objStandardEquipVO) throws EMDException {
		
		LogUtil
		.logMessage("Enters into StandardEquipDAO:fetchStandardEquipment");
		
		Connection objConnection = null;
		Statement objStatement = null;
		ResultSet objResultSet = null;
		ArrayList arlStdEquipList = new ArrayList();
		String strLogUser = "";
		try {
			strLogUser = objStandardEquipVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			LogUtil.logMessage("Enter into FileUploadDAO Connection:"
					+ objConnection);
			objStatement = objConnection.createStatement();
			objResultSet = objStatement
			.executeQuery(EMDQueries.Query_StdEquipment);
			
			while (objResultSet.next()) {
				objStandardEquipVO = new StandardEquipVO();
				objStandardEquipVO.setStandardEquipmentSeqNo(objResultSet
						.getInt(DatabaseConstants.LS060_STD_EQP_SEQ_NO));
				objStandardEquipVO.setStandardEquipmentDesc(objResultSet
						.getString(DatabaseConstants.LS060_STD_EQP_DESC));
				//Added for CR-114
				objStandardEquipVO.setStdEnggDataDesc(objResultSet
						.getString(DatabaseConstants.LS060_STD_EQP_COMMENTS));
				arlStdEquipList.add(objStandardEquipVO);
			}
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception StandardEquipDAO:fetchStandardEquipment");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				if (objResultSet != null) {
					objResultSet.close();
				}
				
				DBHelper.closeConnection(objConnection);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception StandardEquipDAO:fetchStandardEquipment");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return arlStdEquipList;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objStandardEquipVO
	 *            The Object for Insert StandardEquipment
	 * @return integer value
	 * @throws EMDException
	 **************************************************************************/
	
	public static synchronized int insertStandardEquipment(
			StandardEquipVO objStandardEquipVO) throws EMDException {
		LogUtil
		.logMessage("Enters into StandardEquipDAO:insertStandardEquipment");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intStatusCode;
		int intLSDBErrorID = 0;
		String strMessage = null;
		boolean isInserted = false;
		String strLogUser = "";
		try {
			
			strLogUser = objStandardEquipVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_INSERT_ENGGDATA);
			
			objCallableStatement.setString(1, objStandardEquipVO
					.getStandardEquipmentDesc());
			//Added for CR-114
			objCallableStatement.setString(2, objStandardEquipVO
					.getStdEnggDataDesc());
			objCallableStatement.setString(3, objStandardEquipVO.getUserID());
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			intStatusCode = objCallableStatement.executeUpdate();
			LogUtil.logMessage("Result:" + intStatusCode);
			if (intStatusCode > 0) {
				intStatusCode = 0;
			}
			
			intLSDBErrorID = objCallableStatement.getInt(4);
			LogUtil.logMessage("Error ID:" + intLSDBErrorID);
			
			strOracleCode = objCallableStatement.getString(5);
			LogUtil.logMessage("Oracle code:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(6);
			LogUtil.logMessage("three........");
			
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Enters into Error Loop:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				strMessage = String.valueOf(intLSDBErrorID);
				LogUtil.logMessage("Error message in DAO:" + strMessage);
				
				objErrorInfo.setMessageID(strMessage);
				LogUtil.logMessage("Error message in ErrorInfo:"
						+ objErrorInfo.getMessageID());
				
				throw new DataAccessException(objErrorInfo);
				
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {
				LogUtil.logMessage("enters into oracle error code block:"
						+ strOracleCode);
				ErrorInfo objErrorInfo = new ErrorInfo();
				StringBuffer objStBuffer = new StringBuffer();
				objStBuffer.append(strOracleCode + " ");
				objStBuffer.append(strErrorMessage);
				objErrorInfo.setMessage(objStBuffer.toString());
				objConnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
		} catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException StandardEquipDAO:insertStandardEquipment");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in StandardEquipDAO:insertStandardEquipment"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException StandardEquipDAO:insertStandardEquipment");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in StandardEquipDAO:insertStandardEquipment"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception StandardEquipDAO:insertStandardEquipment");
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
				LogUtil
				.logMessage("Enters into Exception StandardEquipDAO:insertStandardEquipment");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		LogUtil
		.logMessage("Inside StandardEquipDAO:insertStandardEquipment Method"
				+ isInserted);
		return intStatusCode;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objStandardEquipVO
	 *            The Object for Modify StandardEquipment
	 * @return boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static synchronized int modifyStandardEquipment(
			StandardEquipVO objStandardEquipVO) throws EMDException {
		LogUtil
		.logMessage("Enters into StandardEquipDAO:modifyStandardEquipment");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intStatusCode;
		int intLSDBErrorID = 0;
		String strMessage = null;
		boolean isInserted = false;
		String strLogUser = "";
		try {
			
			strLogUser = objStandardEquipVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_UPDATE_ENGGDATA);
			
			objCallableStatement.setInt(1, objStandardEquipVO
					.getStandardEquipmentSeqNo());
			objCallableStatement.setString(2, objStandardEquipVO
					.getStandardEquipmentDesc());
			//Added for CR-114
			objCallableStatement.setString(3, objStandardEquipVO
					.getStdEnggDataDesc());
			objCallableStatement.setString(4, objStandardEquipVO.getUserID());
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			intStatusCode = objCallableStatement.executeUpdate();
			LogUtil.logMessage("Result:" + intStatusCode);
			if (intStatusCode > 0) {
				intStatusCode = 0;
			}
			
			intLSDBErrorID = objCallableStatement.getInt(5);
			LogUtil.logMessage("Error ID:" + intLSDBErrorID);
			
			strOracleCode = objCallableStatement.getString(6);
			LogUtil.logMessage("Oracle code:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(7);
			LogUtil.logMessage("three........");
			
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Enters into Error Loop:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				strMessage = String.valueOf(intLSDBErrorID);
				LogUtil.logMessage("Error message in DAO:" + strMessage);
				
				objErrorInfo.setMessageID(strMessage);
				LogUtil.logMessage("Error message in ErrorInfo:"
						+ objErrorInfo.getMessageID());
				
				throw new DataAccessException(objErrorInfo);
				
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {
				LogUtil.logMessage("enters into oracle error code block:"
						+ strOracleCode);
				ErrorInfo objErrorInfo = new ErrorInfo();
				StringBuffer objStBuffer = new StringBuffer();
				objStBuffer.append(strOracleCode + " ");
				objStBuffer.append(strErrorMessage);
				objErrorInfo.setMessage(objStBuffer.toString());
				objConnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
		} catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException StandardEquipDAO:modifyStandardEquipment");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in StandardEquipDAO:modifyStandardEquipment"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException StandardEquipDAO:modifyStandardEquipment");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in StandardEquipDAO:modifyStandardEquipment"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception StandardEquipDAO:modifyStandardEquipment");
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
				LogUtil
				.logMessage("Enters into Exception StandardEquipDAO:modifyStandardEquipment");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		LogUtil
		.logMessage("Inside StandardEquipDAO:modifyStandardEquipment Method"
				+ isInserted);
		return intStatusCode;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objStandardEquipVO
	 *            The Object for Delete StandardEquipment
	 * @return boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static synchronized int deleteStandardEquipment(
			StandardEquipVO objStandardEquipVO) throws EMDException {
		LogUtil
		.logMessage("Enters into StandardEquipDAO:deleteStandardEquipment");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intStatusCode;
		int intLSDBErrorID = 0;
		String strMessage = null;
		boolean isInserted = false;
		String strLogUser = "";
		try {
			
			strLogUser = objStandardEquipVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_DELETE_ENGGDATA);
			
			objCallableStatement.setInt(1, objStandardEquipVO
					.getStandardEquipmentSeqNo());
			objCallableStatement.setString(2, objStandardEquipVO.getUserID());
			objCallableStatement.registerOutParameter(3, Types.INTEGER);
			objCallableStatement.registerOutParameter(4, Types.VARCHAR);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			intStatusCode = objCallableStatement.executeUpdate();
			LogUtil.logMessage("Result:" + intStatusCode);
			if (intStatusCode > 0) {
				intStatusCode = 0;
			}
			
			intLSDBErrorID = objCallableStatement.getInt(3);
			LogUtil.logMessage("Error ID:" + intLSDBErrorID);
			
			strOracleCode = objCallableStatement.getString(4);
			LogUtil.logMessage("Oracle code:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(5);
			LogUtil.logMessage("three........");
			
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Enters into Error Loop:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				strMessage = String.valueOf(intLSDBErrorID);
				LogUtil.logMessage("Error message in DAO:" + strMessage);
				
				objErrorInfo.setMessageID(strMessage);
				LogUtil.logMessage("Error message in ErrorInfo:"
						+ objErrorInfo.getMessageID());
				
				throw new DataAccessException(objErrorInfo);
				
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {
				LogUtil.logMessage("enters into oracle error code block:"
						+ strOracleCode);
				ErrorInfo objErrorInfo = new ErrorInfo();
				StringBuffer objStBuffer = new StringBuffer();
				objStBuffer.append(strOracleCode + " ");
				objStBuffer.append(strErrorMessage);
				objErrorInfo.setMessage(objStBuffer.toString());
				objConnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
		} catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException StandardEquipDAO:deleteStandardEquipment");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in StandardEquipDAO:deleteStandardEquipment"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException StandardEquipDAO:deleteStandardEquipment");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in StandardEquipDAO:deleteStandardEquipment"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception StandardEquipDAO:deleteStandardEquipment");
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
				LogUtil
				.logMessage("Enters into Exception StandardEquipDAO:deleteStandardEquipment");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		LogUtil
		.logMessage("Inside StandardEquipDAO:deleteStandardEquipment Method"
				+ isInserted);
		return intStatusCode;
	}
	
}