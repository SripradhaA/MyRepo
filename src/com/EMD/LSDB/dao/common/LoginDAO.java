package com.EMD.LSDB.dao.common;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.constant.DatabaseConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.vo.admn.UserVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.EMDVO;

public class LoginDAO extends EMDDAO {
	
	private LoginDAO() {
	}
	
	public static LoginVO findUser(LoginVO objLoginVO) throws EMDException {
		LogUtil.logMessage("Inside the findUser method of LoginDAO");
		Connection objConnnection = null;
		List objArrayList = new ArrayList();
		boolean isUserAvaible = false;
		
		try {
			
			objConnnection = DBHelper.prepareConnection();
			objArrayList.add(objLoginVO.getUserID());
			objArrayList.add(objLoginVO.getPassword());
			LogUtil.logMessage("Inside the findUser method of LoginDAO loop");
			LogUtil.logMessage(objLoginVO.getUserID());
			// LogUtil.logMessage(objLoginVO.getPassword());
			
			ResultSet user = DBHelper.executeQuery(objConnnection,
					EMDQueries.query_findUser, objArrayList);
			LogUtil.logMessage(user);
			LogUtil.logMessage("isUserAvaible" + isUserAvaible);
			while (user.next()) {
				isUserAvaible = true;
				objLoginVO.setUserID(user
						.getString(DatabaseConstants.LS010_USER_ID));
				
				objLoginVO.setPassword(user
						.getString(DatabaseConstants.LS010_PWD));
				objLoginVO.setRoleID(user
						.getString(DatabaseConstants.LS120_ROLE_ID));
				objLoginVO.setFirstName(user
						.getString(DatabaseConstants.LS010_FIRSTNAME));
				objLoginVO.setLastName(user
						.getString(DatabaseConstants.LS010_LASTNAME));
				//Added for CR-134
				objLoginVO.setResetFlag(user
						.getString(DatabaseConstants.LS010_RESET_FLAG));
				LogUtil.logMessage(objLoginVO.getUserID());
				LogUtil.logMessage("isUserAvaible" + isUserAvaible);
				
				
			}
			if (!isUserAvaible)
				objLoginVO = null;
		}
		
		catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER
					+ objLoginVO.getUserID() + "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in LoginDAO:findUser:"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Inside the Exception block in LoginDAO:findUser");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + "-"
					+ objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			
			try {
				DBHelper.closeConnection(objConnnection);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Inside the Exception block in LoginDAO:findUser");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER + "-"
						+ objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
		}
		
		return objLoginVO;
		
	}
	
//Added for CR-112 to fetch Message from DB	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objEMDVO 
	 * @return ArrayList the list contains the Message
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList fetchMessage(LoginVO objLoginVO) throws EMDException {
		LogUtil.logMessage("Entering LoginDAO.fetchMessage");
		Connection objConnnection = null;
		//Connection con = null;
		ArrayList arlMessage = new ArrayList();
		ResultSet rsMessage = null;
		CallableStatement objCallableStatement = null;
		//CallableStatement cs = null;
		
		// Error out parameters
		int intLSDBErrorID = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strLogUser = "";
		try {
			
			strLogUser = objLoginVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			//con = DBHelper.connectionforMariaDB();
			
			LogUtil.logMessage("objConnnection in DAO :" + objConnnection);
			//LogUtil.logMessage("con in DAO :" + con);
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_SELECT_MSG);
			//cs = con.prepareCall(EMDQueries.SP_SELECT_MSG1);
			objCallableStatement.setString(1, objLoginVO.getUserID());
			objCallableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(3, Types.INTEGER);
			objCallableStatement.registerOutParameter(4, Types.VARCHAR);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			//cs.registerOutParameter(1, Types.VARCHAR);
			objCallableStatement.execute();
			
			//cs.execute();
			rsMessage = (ResultSet) objCallableStatement.getObject(2);
			LogUtil
			.logMessage("Inside the fetchMessage method of UserDAO :resultSet"
					+ rsMessage);
			/*rsMessage = (ResultSet) cs.getObject(1);
			LogUtil.logMessage("Inside the fetchMessage method of UserDAO :resultSet"
					+ rsMessage);
			String User = cs.getString(1);
	           System.out.println(User);
	           rsMessage = cs.executeQuery(User)*/;
			
			intLSDBErrorID = (int) objCallableStatement.getInt(3);
			strOracleCode = (String) objCallableStatement.getString(4);
			strErrorMessage = (String) objCallableStatement.getString(5);
			
			// Handled Valid Exception
			if (intLSDBErrorID != 0) {
				
				ErrorInfo objErrorInfo = new ErrorInfo();
				
				objErrorInfo.setMessageID("" + intLSDBErrorID);
				
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
				objConnnection.rollback();
				//con.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
			while (rsMessage.next()) {
				LogUtil.logMessage("Inside ResultSet");
				UserVO objUserVO = new UserVO();
				objUserVO.setMessage(rsMessage
						.getString(DatabaseConstants.LS800_MSG_DESC));
				arlMessage.add(objUserVO);
				
			}
			
		} catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException UserMaintDAO.fetchUsers");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in UserMaintDAO.fetchUsers"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException UserMaintDAO.fetchUsers");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in UserMaintDAO.fetchUsers"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception UserMaintDAO.fetchUsers");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(rsMessage, objCallableStatement,
						objConnnection);
				/*DBHelper.closeSQLObjects(rsMessage, cs,
						con);*/
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception UserMaintDAO.fetchUsers");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
 		}
		
		return arlMessage;
		
		/*private static ResultSet executeQuery() {
			// TODO Auto-generated method stub
			return null;
		}*/
	}
	
//	Added for CR-112 to Add Message Of the Day in home screen
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objEMDVO
	 *            The Object for insert Message
	 * @return int The status for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int insertMessage(EMDVO objEMDVO) throws EMDException {
		LogUtil.logMessage("Entering LoginDAO.insertMessage");
		
		//Connection objConnection = null;
		Connection con = null;
		//CallableStatement objCallableStatement = null;
		CallableStatement cs = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intStatusCode = 0;
		;
		int intLSDBErrorID = 0;
		String strMessage = null;
		String strLogUser = "";
		try {
			strLogUser = objEMDVO.getUserID();
			//objConnection = DBHelper.prepareConnection();
			con = DBHelper.connectionforMariaDB();
			/*objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_INSERT_MSG);*/
			
			cs = con.prepareCall(EMDQueries.SP_INSERT_MSG1);
			
			/*objCallableStatement.setString(1, objEMDVO.getMessage());
			objCallableStatement.setString(2, objEMDVO.getUserID());
			objCallableStatement.registerOutParameter(3, Types.VARCHAR);
			objCallableStatement.registerOutParameter(4, Types.VARCHAR);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);*/
			
			
			cs.setString(1, objEMDVO.getMessage());
			cs.setString(2, objEMDVO.getUserID());
			cs.registerOutParameter(3, Types.DOUBLE);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			
			
			//intStatusCode = objCallableStatement.executeUpdate();
			
			intStatusCode = cs.executeUpdate();
			LogUtil.logMessage("Update Result:" + intStatusCode);
			if (intStatusCode > 0) {
				intStatusCode = 0;
			}
			LogUtil.logMessage("Status Update" + intStatusCode);
			/*intLSDBErrorID = objCallableStatement.getInt(3);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(4);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(5);*/
			
			//LogUtil.logMessage("ErrorMessage:" + strErrorMessage);
			
			intLSDBErrorID = cs.getInt(3);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = cs.getString(4);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) cs.getString(5);
			
			LogUtil.logMessage("ErrorMessage:" + strErrorMessage);
			
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
				// objConnection.rollback();
				con.rollback();
				throw new ApplicationException(objErrorInfo);
			}
		} catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException LoginDAO.insertMessage");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in LoginDAO.insertMessage"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException LoginDAO.insertMessage");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in LoginDAO.insertMessage"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception LoginDAO.insertMessage");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				/*DBHelper.closeSQLObjects(null, objCallableStatement,
						objConnection);*/
				DBHelper.closeSQLObjects(null, cs,
						con);
			}
			
			catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception LoginDAO.insertMessage");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return intStatusCode;
	}
	//Added for CR-112 to Fetch Message Of the Day in home screen Ends Here
	
	
	
	
}
