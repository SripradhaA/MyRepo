/**
 * 
 */
package com.EMD.LSDB.dao.admn;
import java.sql.*;
import java.util.ArrayList;

import org.apache.tomcat.dbcp.dbcp2.DelegatingConnection;//Added for Tomcat and CR-123

import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.constant.DatabaseConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.common.AdmnQueries;
import com.EMD.LSDB.dao.common.DBHelper;
import com.EMD.LSDB.dao.common.EMDQueries;
import com.EMD.LSDB.vo.admn.UserVO;

/**
 * @author VV49326
 * 
 */
public class UserMaintDAO {
	
	private UserMaintDAO() {
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objUserVO
	 *            the object for searching model
	 * @return ArrayList the list contains the Users
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList fetchUsers(UserVO objUserVO) throws EMDException {
		LogUtil.logMessage("Entering UserDAODAO.fetchUsers");
		System.out.println("entering the UserDAODAO.fetchusers");
		//Connection objConnnection = null;
		//Connection con = null;
		Connection con3= null;
		ArrayList arlUsers = new ArrayList();
		ResultSet rsUsers = null;
		//ResultSet rs = null;
		//CallableStatement objCallableStatement = null;
		CallableStatement cs = null;
		
		// Error out parameters
		int intLSDBErrorID = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strLogUser = "";
		try {
			
			strLogUser = objUserVO.getUserID();
			//objConnnection = DBHelper.prepareConnection();
			//con = DBHelper.connectionforMariaDB();
			con3 = DBHelper.connectionforPostgres();
			/*LogUtil.logMessage("objConnnection in DAO :" + objConnnection);
			System.out.println("objConnnection in DAO :" + objConnnection);*/
			/*LogUtil.logMessage("con in DAO :" + con);
			System.out.println("con in DAO :" + con);*/
			
			LogUtil.logMessage("con in DAO :" + con3);
			System.out.println("con in DAO :" + con3);
			/*objCallableStatement = objConnnection
			.prepareCall(AdmnQueries.SP_FETCH_USER);
*/			
			//cs = con.prepareCall(AdmnQueries.SP_FETCH_USER1);
			cs = con3.prepareCall(AdmnQueries.SP_FETCH_USER12);
			con3.setAutoCommit(false);
			//cs = con.prepareCall(AdmnQueries.SP_FETCH_USER2);
			//con.setAutoCommit(false);
			/*cs.registerOutParameter(1, Types.OTHER);
		    	cs.setString(2,"d");
		    	cs.setInt(3,1);
			*/
			/*objCallableStatement.setString(1, objUserVO.getUserID());*/
			cs.setString(1,objUserVO.getUserID());
			//cs.setDouble(1,2);
			//cs.setInt(1,2);
			//Adder For CR_94
			/*objCallableStatement.setInt(2, objUserVO.getOrderbyFlag());*/
			cs.setInt(2, objUserVO.getOrderbyFlag());
			/*objCallableStatement.registerOutParameter(3, OracleTypes.CURSOR);*/
			//cs.registerOutParameter(3, java.sqlTypes.CURSOR);
			/*objCallableStatement.registerOutParameter(4, Types.INTEGER);*/
			//cs.registerOutParameter(4, Types.INTEGER);
			/*objCallableStatement.registerOutParameter(5, Types.VARCHAR);*/
			//cs.registerOutParameter(5, Types.VARCHAR);
			/*objCallableStatement.registerOutParameter(6, Types.VARCHAR);*/
			//cs.registerOutParameter(6, Types.VARCHAR);
			//cs.registerOutParameter(2,Types.VARCHAR);
			cs.registerOutParameter(3,Types.VARCHAR);
			//objCallableStatement.execute();
			cs.execute();
			con3.commit();
			//rsUsers = (ResultSet) cs.getObject(1);
			//String User = cs.getString(2);
			String User = cs.getString(3);
	           System.out.println(User);
	           Statement s = con3.createStatement();
		        rsUsers = s.executeQuery(User);
	           //rsUsers = cs.executeQuery(User);
		        System.out.println(rsUsers);
			LogUtil
			.logMessage("Inside the fetchUsers method of UserDAO :resultSet"
					+ rsUsers);
			System.out.println("Inside the fetchUsers method of UserDAO :resultSet"
					+ rsUsers);
			
			
			
			/*Object User = cs.getObject(1);
	           System.out.println(User);
			rsUsers = cs.executeQuery();*/
	           //rsUsers = cs.executeQuery();
			
			/*rsUsers = (ResultSet) objCallableStatement.getObject(3);
			LogUtil
			.logMessage("Inside the fetchUsers method of UserDAO :resultSet"
					+ rsUsers);
			System.out.println("Inside the fetchUsers method of UserDAO :resultSet"
					+ rsUsers);*/
			/*rsUsers = (ResultSet) cs.getObject(2);
			rsUsers = executeQuery();*/
			//rs = (ResultSet) cs.getObject(3);
			//LogUtil.logMessage("Inside the fetchUsers method of UserDAO :resultSet"+ rs);
			//System.out.println("Inside the fetchUsers method of UserDAO :resultSet" + rs);
			
			/*intLSDBErrorID = (int) objCallableStatement.getInt(4);*/
			//intLSDBErrorID = (int) cs.getInt(4);
			/*strOracleCode = (String) objCallableStatement.getString(5);*/
			//strOracleCode = (String) cs.getString(5);
			/*strErrorMessage = (String) objCallableStatement.getString(6);*/
			//strErrorMessage = (String) cs.getString(6);
			
			// Handled Valid Exception
			/*if (intLSDBErrorID != 0) {
				
				ErrorInfo objErrorInfo = new ErrorInfo();
				
				objErrorInfo.setMessageID("" + intLSDBErrorID);
				
				throw new DataAccessException(objErrorInfo);
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {// Un
				// handled
				// exception
				
				LogUtil.logMessage("strOracleCode:" + strOracleCode);
				System.out.println("strOracleCode:" + strOracleCode);
				ErrorInfo objErrorInfo = new ErrorInfo();
				StringBuffer sb = new StringBuffer();
				sb.append(strOracleCode + " ");
				sb.append(strErrorMessage);
				objErrorInfo.setMessage(sb.toString());
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			*/
			while (rsUsers.next()) {
				objUserVO = new UserVO();
				LogUtil.logMessage("Inside ResultSet");
				System.out.println("Inside ResultSet");
				objUserVO.setUserrId(rsUsers
						.getString(DatabaseConstants.LS010_USER_ID));
				/*objUserVO.setUserrId(rs
						.getString(DatabaseConstants.LS010_USER_ID));*/
				objUserVO.setFirsttName(rsUsers
						.getString(DatabaseConstants.LS010_FIRSTNAME));
				/*objUserVO.setFirsttName(rs
						.getString(DatabaseConstants.LS010_FIRSTNAME));*/
				objUserVO.setLasttName(rsUsers
						.getString(DatabaseConstants.LS010_LASTNAME));
				/*objUserVO.setLasttName(rs
						.getString(DatabaseConstants.LS010_LASTNAME));*/
				objUserVO.setLocation(rsUsers
						.getString(DatabaseConstants.LS010_LOC));
				/*objUserVO.setLocation(rs
						.getString(DatabaseConstants.LS010_LOC));*/
				objUserVO.setEmaillId(rsUsers
						.getString(DatabaseConstants.LS010_EMAIL_ADDRESS));
				/*objUserVO.setEmaillId(rs
						.getString(DatabaseConstants.LS010_EMAIL_ADDRESS));*/
				objUserVO.setContacttNo(rsUsers
						.getString(DatabaseConstants.LS010_CONTACT_NUMBER));
				/*objUserVO.setContacttNo(rs
						.getString(DatabaseConstants.LS010_CONTACT_NUMBER));*/
				objUserVO.setRole(rsUsers
						.getString(DatabaseConstants.LS120_ROLE_ID));
				/*objUserVO.setRole(rs
						.getString(DatabaseConstants.LS120_ROLE_ID));*/
				
				//Added for CR-113
				objUserVO.setRoleName(rsUsers
						.getString(DatabaseConstants.LS120_ROLE_NAME));
				/*objUserVO.setRoleName(rs
						.getString(DatabaseConstants.LS120_ROLE_NAME));*/
				//Added for CR-113 ends here
				// String
				// name=(objUserVO.getUserrId()).concat("-").concat(objUserVO.getFirsttName()).concat("
				// ").concat(objUserVO.getLasttName());
				objUserVO.setName((objUserVO.getUserrId()).concat("-").concat(
						objUserVO.getFirsttName()).concat("  ").concat(
								objUserVO.getLasttName()));
				LogUtil.logMessage("Name:" + objUserVO.getName());
				System.out.println("Name:" + objUserVO.getName());
				LogUtil.logMessage("UserID:" + objUserVO.getUserrId());
				System.out.println("UserID:" + objUserVO.getUserrId());
				LogUtil.logMessage("RoleID:" + objUserVO.getRole());
				System.out.println("RoleID:" + objUserVO.getRole());
				LogUtil.logMessage("RoleName:" + objUserVO.getRoleName());
				System.out.println("RoleName:" + objUserVO.getRoleName());
				//Added for CR-109
				objUserVO.setPrevLoggedIn(rsUsers
						.getString(DatabaseConstants.LS010_PREV_LOGGEDIN));
               	//Added for CR-109 Ends here
		 		
				//Added for CR-126
				objUserVO.setActionNoticeFlag(rsUsers
						.getString(DatabaseConstants.LS011_ACTION_NOTICE));
				objUserVO.setInformationNoticeFlag(rsUsers
						.getString(DatabaseConstants.LS011_INFO_NOTICE));
				objUserVO.setCcNoticeFlag(rsUsers
						.getString(DatabaseConstants.LS011_CC_NOTICE));
				//Added for CR-126 ends  here
				arlUsers.add(objUserVO);
				
			}
			
		} /*catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException UserMaintDAO.fetchUsers");
			System.out.println("Enters into DataAccessException UserMaintDAO.fetchUsers");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in UserMaintDAO.fetchUsers"
					+ objErrorInfo.getMessageID());
			System.out.println("ENters into catch block in UserMaintDAO.fetchUsers"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException UserMaintDAO.fetchUsers");
			System.out.println("Enters into ApplicationException UserMaintDAO.fetchUsers");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in UserMaintDAO.fetchUsers"
					+ objErrorInfo.getMessage());
			System.out.println("Enters into catch block in UserMaintDAO.fetchUsers"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}*/
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception UserMaintDAO.fetchUsers");
			System.out.println("Enters into Exception UserMaintDAO.fetchUsers");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				/*DBHelper.closeSQLObjects(rsUsers, objCallableStatement,
						objConnnection);*/
				//DBHelper.closeSQLObjects(rsUsers, cs, con);
				DBHelper.closeSQLObjects(rsUsers, cs, con3);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception UserMaintDAO.fetchUsers");
				System.out.println("Enters into Exception UserMaintDAO.fetchUsers");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return arlUsers;
		
	}
	/*
	private static ResultSet executeQuery() {
		// TODO Auto-generated method stub
		return null;
	}*/

	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objUserVO
	 *            The Object for delete User
	 * @return int The status for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int deleteUser(UserVO objUserVO) throws EMDException {
		LogUtil.logMessage("Enters into UserMaintDAO.deleteUser");
		System.out.println("Enters into UserMaintDAO.deleteUser");
		//Connection objConnection = null;
		Connection con = null;
		//CallableStatement objCallableStatement = null;
		CallableStatement cs = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intStatusCode = 0;
		int intLSDBErrorID = 0;
		String strMessage = null;
		String strLogUser = "";
		try {
			strLogUser = objUserVO.getUserID();
			//objConnection = DBHelper.prepareConnection();
			//con = DBHelper.connectionforMariaDB();
			con = DBHelper.connectionforPostgres();
			/*objCallableStatement = objConnection
			.prepareCall(AdmnQueries.SP_DELETE_USER);*/
			//cs = con.prepareCall(AdmnQueries.SP_DELETE_USER_OLD);
			cs = con.prepareCall(AdmnQueries.SP_DELETE_USER_1);
			/*System.out.println(AdmnQueries.SP_DELETE_USER_OLD);*/
			System.out.println(AdmnQueries.SP_DELETE_USER_1);
			con.setAutoCommit(false);
			//objCallableStatement.setString(1, objUserVO.getUserrId());
			//objCallableStatement.setString(2, objUserVO.getUserID()); 
			
			/*--this is fr postgres */
			
			/*cs.registerOutParameter(1, Types.DOUBLE);
	         cs.registerOutParameter(2, Types.VARCHAR);
	         cs.registerOutParameter(3, Types.VARCHAR);*/
	         cs.setString(1, objUserVO.getUserrId());
	         System.out.println("UserID:" +objUserVO.getUserrId());
	         cs.setString(2, objUserVO.getUserID());
	         System.out.println("UpdatedUserID:" +objUserVO.getUserID());
	         cs.execute();
	         con.commit();
	         //Double user = cs.getDouble(1);
	         
	         //cs.close();
			/*-- this is for maria db
			cs.setString(1, objUserVO.getUserrId());
			System.out.println("UserID:" +objUserVO.getUserrId());
		    cs.setString(2, objUserVO.getUserID());
			System.out.println("UpdatedUserID:" +objUserVO.getUserID());
			
			cs.registerOutParameter(3,Types.INTEGER);*/
			//objCallableStatement.registerOutParameter(3, Types.INTEGER);
			//objCallableStatement.registerOutParameter(4, Types.VARCHAR);
			//objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			
			//intStatusCode = objCallableStatement.executeUpdate();
			intStatusCode = cs.executeUpdate();
			LogUtil.logMessage("Update Result:" + intStatusCode);
			System.out.println("Update Result:" + intStatusCode);
			if (intStatusCode > 0) {
				intStatusCode = 0;
			}
			LogUtil.logMessage("Status Update" + intStatusCode);
			System.out.println("Status Update" + intStatusCode);
		
			/*intLSDBErrorID = objCallableStatement.getInt(3);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(4);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(5);
			
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
				objConnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
		} catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException UserMaintDAO.deleteUser");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in UserMaintDAO.deleteUser"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);*/
		} /*catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException UserMaintDAO.deleteUser");
			System.out.println("application error catch block");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in UserMaintDAO.deleteUser"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		*/
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception UserMaintDAO.deleteUser");
			System.out.println("Enters into Exception UserMaintDAO.deleteUser");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				/*DBHelper.closeSQLObjects(null, objCallableStatement,
						objConnection);*/
				DBHelper.closeSQLObjects(null, cs, con);
			}
			
			catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception UserMaintDAO.deleteUser");
				System.out.println("Enters into Exception UserMaintDAO.deleteUser");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return intStatusCode;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objUserVO
	 *            The Object for insert User
	 * @return int The status for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int insertUser(UserVO objUserVO) throws EMDException {
		LogUtil.logMessage("Entering UserMaintDAO.insertUser");
		System.out.println("Entering UserMaintDAO.insertUser");
		
		//Connection objConnection = null;
		Connection con = null;
		//CallableStatement objCallableStatement = null;
		CallableStatement cs = null;
		// String strOracleCode = null;
		// String strErrorMessage = null;
		int intStatusCode = 0;
		int intLSDBErrorID = 0;
		//double doubleLSDBErrorID = 0.0;
		String strMessage = null;
		String strLogUser = "";
		try {
			strLogUser = objUserVO.getUserID();
			//con = DBHelper.connectionforMariaDB();
			con = DBHelper.connectionforPostgres();
			
			//objConnection = DBHelper.prepareConnection();
			/*objCallableStatement = objConnection
			.prepareCall(AdmnQueries.SP_INSERT_USER);*/
			cs = con.prepareCall(AdmnQueries.SP_INSERT_USER2); 
			con.setAutoCommit(false);
			/*objCallableStatement.setString(1, objUserVO.getUserrId());
			objCallableStatement.setString(2, objUserVO.getEmaillId());
			objCallableStatement.setString(3, objUserVO.getFirsttName());
			objCallableStatement.setString(4, objUserVO.getLocation());
			objCallableStatement.setString(5, objUserVO.getLasttName());
			objCallableStatement.setString(6, objUserVO.getContacttNo());
			objCallableStatement.setString(7, objUserVO.getPassword());
			objCallableStatement.setString(8, objUserVO.getRole());
			objCallableStatement.setString(9, objUserVO.getUserID());*/
			
			cs.setString(1, objUserVO.getUserrId());
			cs.setString(2, objUserVO.getEmaillId());
			cs.setString(3, objUserVO.getFirsttName());
			cs.setString(4, objUserVO.getLocation());
			cs.setString(5, objUserVO.getLasttName());
			cs.setString(6, objUserVO.getContacttNo());
			cs.setString(7, objUserVO.getPassword());
			cs.setString(8, objUserVO.getRole());
			cs.setString(9, objUserVO.getUserID());
			
			//Added for CR_121 Starts Here
			//objCallableStatement.setString(10,objUserVO.getServerType()); 
			
			cs.setString(10,objUserVO.getServerType());
			//cs.registerOutParameter(11, Types.DOUBLE);
			
			LogUtil.logMessage("server Type : " + objUserVO.getServerType() );
			//Added for CR_121 Ends Here
			//objCallableStatement.registerOutParameter(11, Types.INTEGER);
			
			
			
			/*objCallableStatement.registerOutParameter(12, Types.VARCHAR);
			objCallableStatement.registerOutParameter(13, Types.VARCHAR);*/
			
			//intStatusCode = objCallableStatement.executeUpdate();
			intStatusCode = cs.executeUpdate();
		
			//cs.execute();
			
			
			LogUtil.logMessage("Update Result:" + intStatusCode);
			System.out.println("Update Result:" + intStatusCode);
			con.commit();
			if (intStatusCode > 0) {
				intStatusCode = 0;
			}
			LogUtil.logMessage("Status Update" + intStatusCode);
			//intLSDBErrorID = objCallableStatement.getInt(11);
			//intLSDBErrorID = cs.getInt(11);
			//doubleLSDBErrorID = cs.getDouble(11);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			//LogUtil.logMessage("LSDBErrorID:" + doubleLSDBErrorID);
			/*strOracleCode = objCallableStatement.getString(12);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(13);*/
			
			//LogUtil.logMessage("ErrorMessage:" + strErrorMessage);
			
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Enters into Error Loop:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				strMessage = String.valueOf(intLSDBErrorID);
				LogUtil.logMessage("Error message in DAO:" + strMessage);
				
				/*if (doubleLSDBErrorID != 0) {
					LogUtil.logMessage("Enters into Error Loop:");
					ErrorInfo objErrorInfo = new ErrorInfo();
					strMessage = String.valueOf(doubleLSDBErrorID);
					LogUtil.logMessage("Error message in DAO:" + strMessage);*/
				objErrorInfo.setMessageID(strMessage);
				LogUtil.logMessage("Error message in ErrorInfo:"
						+ objErrorInfo.getMessageID());
				
				throw new DataAccessException(objErrorInfo);
				
			} /*else if (strOracleCode != null && !"0".equals(strOracleCode)) {
				LogUtil.logMessage("enters into oracle error code block:"
						+ strOracleCode);
				ErrorInfo objErrorInfo = new ErrorInfo();
				StringBuffer objStBuffer = new StringBuffer();
				objStBuffer.append(strOracleCode + " ");
				objStBuffer.append(strErrorMessage);
				objErrorInfo.setMessage(objStBuffer.toString());
				//objConnection.rollback();
				con.rollback();
				throw new ApplicationException(objErrorInfo);
			}*/
		} /*catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException UserMaintDAO.insertUser");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in UserMaintDAO.insertUser"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} */ 
		
		catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException UserMaintDAO.insertUser");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in UserMaintDAO.insertUser"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception UserMaintDAO.insertUser");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				/*DBHelper.closeSQLObjects(null, objCallableStatement,
						objConnection);*/
				DBHelper.closeSQLObjects(null, cs, con);
			}
			
			catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception UserMaintDAO.insertUser");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return intStatusCode;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objUserVO
	 *            The Object for update User
	 * @return int The status for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int updateUser(UserVO objUserVO) throws EMDException {
		LogUtil.logMessage("Entering UserMaintDAO:updateUser");
		System.out.println("Entering UserMaintDAO:updateUser");
		
		//Connection objConnection = null;
		Connection con = null;
		
		//CallableStatement objCallableStatement = null;
		CallableStatement cs = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intStatusCode = 0;
		//int intLSDBErrorID = 0;
		double doubleLSDBErrorID = 0;
		String strMessage = null;
		String strLogUser = "";
		try {
			strLogUser = objUserVO.getUserID();
			//objConnection = DBHelper.prepareConnection();
			//con = DBHelper.connectionforMariaDB();
			con = DBHelper.connectionforPostgres();
			con.setAutoCommit(false);
			//Added Condition for CR_109 to update the Login Time
			if(!"Y".equalsIgnoreCase(objUserVO.getLoginFlag()))	{
				
				/*objCallableStatement = objConnection
				.prepareCall(AdmnQueries.SP_UPDATE_USER);*/
				cs = con.prepareCall(AdmnQueries.SP_UPDATE_USER2);
				//cs = con.prepareCall(AdmnQueries.SP_UPDATE_USER1);
				/*if(objUserVO.getUserrId() == null || "".equalsIgnoreCase(objUserVO.getUserrId())){
					objCallableStatement.setNull(1, Types.NULL);*/
					if(objUserVO.getUserrId() == null || "".equalsIgnoreCase(objUserVO.getUserrId())){
						cs.setNull(1, Types.NULL);
				}
					/*else {
				objCallableStatement.setString(1, objUserVO.getUserrId());*/
				else {
					cs.setString(1, objUserVO.getUserrId());
				}
				/*if(objUserVO.getEmaillId() == null || "".equalsIgnoreCase(objUserVO.getEmaillId())){
					objCallableStatement.setNull(2, Types.NULL);*/
					if(objUserVO.getEmaillId() == null || "".equalsIgnoreCase(objUserVO.getEmaillId())){
						cs.setNull(2, Types.NULL);
				}else {
				/*objCallableStatement.setString(2, objUserVO.getEmaillId());*/
				cs.setString(2, objUserVO.getEmaillId());
				}
				/*if(objUserVO.getFirsttName() == null || "".equalsIgnoreCase(objUserVO.getFirsttName())){
					objCallableStatement.setNull(3, Types.NULL);*/
					if(objUserVO.getFirsttName() == null || "".equalsIgnoreCase(objUserVO.getFirsttName())){
						cs.setNull(3, Types.NULL);
				}else {
				//objCallableStatement.setString(3, objUserVO.getFirsttName());
				cs.setString(3, objUserVO.getFirsttName());
				}
				/*if(objUserVO.getLocation() == null || "".equalsIgnoreCase(objUserVO.getLocation())){
					objCallableStatement.setNull(4, Types.NULL);*/
					if(objUserVO.getLocation() == null || "".equalsIgnoreCase(objUserVO.getLocation())){
						cs.setNull(4, Types.NULL);
				}else {
				//objCallableStatement.setString(4, objUserVO.getLocation());
				cs.setString(4, objUserVO.getLocation());
				}
				/*if(objUserVO.getLasttName() == null || "".equalsIgnoreCase(objUserVO.getLasttName())){
					objCallableStatement.setNull(5, Types.NULL);*/
					if(objUserVO.getLasttName() == null || "".equalsIgnoreCase(objUserVO.getLasttName())){
						cs.setNull(5, Types.NULL);
				}else {
				//objCallableStatement.setString(5, objUserVO.getLasttName());
				cs.setString(5, objUserVO.getLasttName());
				}
				/*if(objUserVO.getContacttNo() == null || "".equalsIgnoreCase(objUserVO.getContacttNo())){
					objCallableStatement.setNull(6, Types.NULL);*/
					if(objUserVO.getContacttNo() == null || "".equalsIgnoreCase(objUserVO.getContacttNo())){
						cs.setNull(6, Types.NULL);
				}else {
				//objCallableStatement.setString(6, objUserVO.getContacttNo());
				cs.setString(6, objUserVO.getContacttNo());
				}
				/*if(objUserVO.getRole() == null || "".equalsIgnoreCase(objUserVO.getRole())){
					objCallableStatement.setNull(7, Types.NULL);*/
					if(objUserVO.getRole() == null || "".equalsIgnoreCase(objUserVO.getRole())){
						cs.setNull(7, Types.NULL);
				}else {
				//objCallableStatement.setString(7, objUserVO.getRole());
				cs.setString(7, objUserVO.getRole());
				}
				
				//objCallableStatement.setString(8, objUserVO.getUserID());
				cs.setString(8, objUserVO.getUserID());
				/*objCallableStatement.registerOutParameter(9, Types.INTEGER);
				objCallableStatement.registerOutParameter(10, Types.VARCHAR);
				objCallableStatement.registerOutParameter(11, Types.VARCHAR);*/
				
				//cs.registerOutParameter(9, Types.INTEGER);
				cs.registerOutParameter(9, Types.DOUBLE);
				cs.registerOutParameter(10, Types.VARCHAR);
                cs.registerOutParameter(11, Types.VARCHAR);
				
				//intStatusCode = objCallableStatement.executeUpdate();
				intStatusCode = cs.executeUpdate();
				con.commit();
				LogUtil.logMessage("Update Result:" + intStatusCode);
								if (intStatusCode > 0) {
					intStatusCode = 0;
				}
				LogUtil.logMessage("Status Update" + intStatusCode);
				//intLSDBErrorID = objCallableStatement.getInt(9);
				/*intLSDBErrorID = cs.getInt(9);
				LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);*/
				doubleLSDBErrorID = cs.getDouble(9);
				LogUtil.logMessage("LSDBErrorID:" + doubleLSDBErrorID);
				//strOracleCode = objCallableStatement.getString(10);
				strOracleCode = cs.getString(10);
				LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
				//strErrorMessage = (String) objCallableStatement.getString(11);
				strErrorMessage = (String) cs.getString(11);
				LogUtil.logMessage("ErrorMessage:" + strErrorMessage);
				
				/*if (intLSDBErrorID != 0) {
					LogUtil.logMessage("Enters into Error Loop:");
					ErrorInfo objErrorInfo = new ErrorInfo();
					strMessage = String.valueOf(intLSDBErrorID);
					LogUtil.logMessage("Error message in DAO:" + strMessage);*/
					
					if (doubleLSDBErrorID != 0) {
						LogUtil.logMessage("Enters into Error Loop:");
						ErrorInfo objErrorInfo = new ErrorInfo();
						strMessage = String.valueOf(doubleLSDBErrorID);
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
					//objConnection.rollback();
					con.rollback();
					throw new ApplicationException(objErrorInfo);
					}
			}
			/*else	{			
				
				//PreparedStatement objPreparedStatement = objConnection.prepareStatement(EMDQueries.Query_UpdateCurrLogin);
				PreparedStatement objPreparedStatement = con.prepareStatement(EMDQueries.Query_UpdateCurrLogin);
				objPreparedStatement.setString(1,objUserVO.getUserID());
				objPreparedStatement.executeUpdate();				
				LogUtil.logMessage("Enters into else block to update Current Login Only :" + objPreparedStatement);				
				objPreparedStatement.close();
				
			}*/
			
		} catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException UserMaintDAO:updateUser");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in UserMaintDAO:updateUser"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException UserMaintDAO:updateUser");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in UserMaintDAO:updateUser"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception UserMaintDAO:updateUser");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		} finally {
			try {
				
				/*DBHelper.closeSQLObjects(null, objCallableStatement,
						objConnection);*/
				DBHelper.closeSQLObjects(null, cs,
						con);
			}
			
			catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception UserMaintDAO:updateUser");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return intStatusCode;
	}
	
	//Added for CR-112
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objUserVO
	 *            the object for searching UserRoles
	 * @return ArrayList the list contains the UserRoles
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList fetchUserRoles(UserVO objUserVO) throws EMDException {
		LogUtil.logMessage("Entering UserDAODAO.fetchUserRoles");
		Connection objConnnection = null;
		ArrayList arlUserRoles = new ArrayList();
		ResultSet rsUserRoles = null;
		CallableStatement objCallableStatement = null;
		
		// Error out parameters
		int intLSDBErrorID = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strLogUser = "";
		try {
			
			strLogUser = objUserVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			
			LogUtil.logMessage("objConnnection in DAO :" + objConnnection);
			objCallableStatement = objConnnection
			.prepareCall(AdmnQueries.SP_SELECT_USER_ROLES);
			objCallableStatement.setString(1, objUserVO.getUserID());
			objCallableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(3, Types.INTEGER);
			objCallableStatement.registerOutParameter(4, Types.VARCHAR);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			rsUserRoles = (ResultSet) objCallableStatement.getObject(2);
			LogUtil.logMessage("Inside the fetchUserRoles method of UserDAO :resultSet"	+ rsUserRoles);
			
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
				throw new ApplicationException(objErrorInfo);
			}
			
			while (rsUserRoles.next()) {
				objUserVO = new UserVO();
				LogUtil.logMessage("Inside ResultSet");
				objUserVO.setRoleID(rsUserRoles.getString(DatabaseConstants.LS120_ROLE_ID ) );
				objUserVO.setRoleName(rsUserRoles.getString(DatabaseConstants.LS120_ROLE_NAME ));
				LogUtil.logMessage(" Role ID = " + objUserVO.getRoleID());
				LogUtil.logMessage("Role Name = " + objUserVO.getRoleName());
				
				arlUserRoles.add(objUserVO);
				
			}
			
		} catch (DataAccessException objDataExp) {
			LogUtil.logMessage("Enters into DataAccessException UserMaintDAO.fetchUserRoles");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil.logMessage("ENters into catch block in UserMaintDAO.fetchUserRoles"+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil.logMessage("Enters into ApplicationException UserMaintDAO.fetchUserRoles");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil.logMessage("ENters into catch block in UserMaintDAO.fetchUserRoles"+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception UserMaintDAO.fetchUserRoles");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(rsUserRoles, objCallableStatement,
						objConnnection);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception UserMaintDAO.fetchUserRoles");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return arlUserRoles;
		
	}
	
	//Added for CR-113
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objUserVO
	 *            The Object for broadcast email
	 * @return int The status for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int broadcastEmail(UserVO objUserVO) throws EMDException {
		LogUtil.logMessage("Entering UserMaintDAO.broadcastEmail");
		
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intStatusCode = 0;
		ArrayDescriptor arrdesc = null;
		ARRAY arr = null;
		int intLSDBErrorID = 0;
		String strMessage = null;
		String strLogUser = "";
		try {
			strLogUser = objUserVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
			.prepareCall(AdmnQueries.SP_BROADCAST_EMAIL);
			//Added for Tomcat
			Connection dconn = ((DelegatingConnection) objConnection).getInnermostDelegate(); //Added for CR-123
			 arrdesc = new ArrayDescriptor(DatabaseConstants.STR_ARRAY,dconn);//Updated for CR-123
             arr = new ARRAY(arrdesc, dconn, objUserVO.getUserList());//Updated for CR-123
			//Modified for Tomcat till here
			objCallableStatement.setArray(1, arr);
			objCallableStatement.setString(2, objUserVO.getSubject());
			objCallableStatement.setString(3, objUserVO.getBody());
			objCallableStatement.setString(4, objUserVO.getUserID());
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			
			intStatusCode = objCallableStatement.executeUpdate();
			
			LogUtil.logMessage("Update Result:" + intStatusCode);
			if (intStatusCode > 0) {
				intStatusCode = 0;
			}
			LogUtil.logMessage("Status Update" + intStatusCode);
			intLSDBErrorID = objCallableStatement.getInt(5);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(6);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(7);
			
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
				objConnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
		} catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException UserMaintDAO.broadcastEmail");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in UserMaintDAO.broadcastEmail"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException UserMaintDAO.broadcastEmail");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in UserMaintDAO.broadcastEmail"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception UserMaintDAO.broadcastEmail");
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
				.logMessage("Enters into Exception UserMaintDAO.broadcastEmail");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return intStatusCode;
	}
	
	
	//Added for CR-124 Starts
	
	public static ArrayList fetchEmailDetails(UserVO objUserVO) throws EMDException {
		LogUtil.logMessage("Entering UserMaintDAO.fetchEmailDetails");
		Connection objConnnection = null;
		ArrayList arlEmailDet = new ArrayList();
		ResultSet rsEmailDet = null;
		CallableStatement objCallableStatement = null;
		
		// Error out parameters
		int intLSDBErrorID = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strLogUser = "";
		try {
			
			strLogUser = objUserVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			
			LogUtil.logMessage("objConnnection in DAO :" + objConnnection);
			objCallableStatement = objConnnection
			.prepareCall(AdmnQueries.SP_SELECT_EMAILDTLS);
			if (objUserVO.getUserID()!= null) {
				objCallableStatement.setString(1, objUserVO.getUserID());
			} else {
				objCallableStatement.setNull(1, Types.NULL);
			}
			objCallableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(3, Types.INTEGER);
			objCallableStatement.registerOutParameter(4, Types.VARCHAR);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			rsEmailDet = (ResultSet) objCallableStatement.getObject(2);
			LogUtil.logMessage("Inside the fetchemailDetails method of UserDAO :resultSet"	+ rsEmailDet);
			
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
				throw new ApplicationException(objErrorInfo);
			}
			
			while (rsEmailDet.next()) {
				objUserVO = new UserVO();
				LogUtil.logMessage("Inside ResultSet");
				objUserVO.setSeqNo(rsEmailDet.getInt(DatabaseConstants.LS180_EMAIL_SEQ_NO ) );
				objUserVO.setFromEmailId(rsEmailDet.getString(DatabaseConstants.LS180_EMAIL_FROM_X ));
				objUserVO.setToEmailId(rsEmailDet.getString(DatabaseConstants.LS180_EMAIL_TO_X ));
				objUserVO.setEmailSubject(rsEmailDet.getString(DatabaseConstants.LS180_EMAIL_SUBJECT_X ));
				objUserVO.setEmailBody(rsEmailDet.getString(DatabaseConstants.LS180_EMAIL_BODY_X ));
				objUserVO.setSentTime(rsEmailDet.getString(DatabaseConstants.LS180_EMAIL_TS ));
				objUserVO.setUpdtUserId(rsEmailDet.getString(DatabaseConstants.LS180_UPDT_USER_ID ));
				objUserVO.setUpdtDate(rsEmailDet.getString(DatabaseConstants.LS180_UPDT_DATE ));
				
				/*LogUtil.logMessage(" Seq no = " + objUserVO.getSeqNo());
				LogUtil.logMessage("from email = " + objUserVO.getFromEmailId());
				LogUtil.logMessage(" to email = " + objUserVO.getToEmailId());
				LogUtil.logMessage("Sub = " + objUserVO.getEmailSubject());
				LogUtil.logMessage(" body = " + objUserVO.getEmailBody());
				LogUtil.logMessage(" time = " + objUserVO.getSentTime());
				LogUtil.logMessage(" Updt user = " + objUserVO.getUpdtUserId());
				LogUtil.logMessage(" Updt date = " + objUserVO.getUpdtDate());*/
				
				arlEmailDet.add(objUserVO);
				
			}
			
		} catch (DataAccessException objDataExp) {
			LogUtil.logMessage("Enters into DataAccessException UserMaintDAO.fetchEmailDetails");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil.logMessage("ENters into catch block in UserMaintDAO.fetchEmailDetails"+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil.logMessage("Enters into ApplicationException UserMaintDAO.fetchEmailDetails");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil.logMessage("ENters into catch block in UserMaintDAO.fetchEmailDetails"+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception UserMaintDAO.fetchEmailDetails");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(rsEmailDet, objCallableStatement,
						objConnnection);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception UserMaintDAO.fetchEmailDetails");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return arlEmailDet;
		
	}
	
	

	public static int purgeEmail(UserVO objUserVO) throws EMDException {
		LogUtil.logMessage("Enters into UserMaintDAO.purgeEmail");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ArrayDescriptor arrdesc = null;
		ARRAY arr = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intStatusCode = 0;
		;
		int intLSDBErrorID = 0;
		String strMessage = null;
		String strLogUser = "";
		try {
			strLogUser = objUserVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
			.prepareCall(AdmnQueries.SP_DELETE_EMAILDTLS);
			//Added for Tomcat
			Connection dconn = ((DelegatingConnection) objConnection).getInnermostDelegate(); //Added for CR-123
			arrdesc = new ArrayDescriptor(DatabaseConstants.IN_ARRAY,dconn);//Updated for CR-123
            arr = new ARRAY(arrdesc, dconn, objUserVO.getSeqNos());//Updated for CR-123
			//Modified for tomcat till here
						
			objCallableStatement.setArray(1, arr);
			objCallableStatement.setString(2, objUserVO.getUserID());
			objCallableStatement.registerOutParameter(3, Types.INTEGER);
			objCallableStatement.registerOutParameter(4, Types.VARCHAR);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			
			intStatusCode = objCallableStatement.executeUpdate();
			LogUtil.logMessage("Update Result:" + intStatusCode);
			if (intStatusCode > 0) {
				intStatusCode = 0;
			}
			LogUtil.logMessage("Status Update" + intStatusCode);
			intLSDBErrorID = objCallableStatement.getInt(3);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(4);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(5);
			
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
				objConnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
		} catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException UserMaintDAO.purgeEmail");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in UserMaintDAO.purgeEmail"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException UserMaintDAO.purgeEmail");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in UserMaintDAO.purgeEmail"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception UserMaintDAO.purgeEmail");
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
				.logMessage("Enters into Exception UserMaintDAO.purgeEmail");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return intStatusCode;
	}
			
	
//	Added for CR-124 Ends
	
	//Added for CR-126
	public static int save(UserVO objUserVO) throws EMDException {
		LogUtil.logMessage("Enters into UserMaintDAO.save");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ArrayDescriptor userIDArrayDescriptor = null;
		ArrayDescriptor actionNoticeFlagArrayDescriptor = null;
		ArrayDescriptor informationNoticeFlagArrayDescriptor = null;
		ArrayDescriptor cCNoticeFlagArrayDescriptor = null;
		ARRAY objUserIDArray = null;
		ARRAY objActionNoticeFlagArray = null;
		ARRAY objInformationNoticeFlagArray = null;
		ARRAY objCCNoticeFlagArray = null;
		
		String strOracleCode = null;
		String strErrorMessage = null;
		int intStatusCode = 0;
		
		int intLSDBErrorID = 0;
		String strMessage = null;
		String strLogUser = "";
		try {
			strLogUser = objUserVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
			.prepareCall(AdmnQueries.SP_UPDATE_EMAIL_SUBSCRIPTIONS);
			//Added for Tomcat
			Connection dconn = ((DelegatingConnection) objConnection).getInnermostDelegate(); //Added for CR-123
			
			userIDArrayDescriptor = new ArrayDescriptor(DatabaseConstants.STR_ARRAY,dconn);
			if(objUserVO.getUserList() != null){
			objUserIDArray = new ARRAY(userIDArrayDescriptor, dconn, objUserVO.getUserList());
			}else{
			LogUtil.logMessage("objUserVO.getUserList():null");
			objUserIDArray = new ARRAY(userIDArrayDescriptor, dconn, null);
			}
			objCallableStatement.setArray(1, objUserIDArray);
			actionNoticeFlagArrayDescriptor = new ArrayDescriptor(DatabaseConstants.STR_ARRAY,dconn);
			if(objUserVO.getActionNoticeFlags() != null){
			objActionNoticeFlagArray = new ARRAY(actionNoticeFlagArrayDescriptor, dconn, objUserVO.getActionNoticeFlags());
			}else{
			LogUtil.logMessage("objUserVO.getActionNoticeFlags():null");
			objActionNoticeFlagArray = new ARRAY(actionNoticeFlagArrayDescriptor, dconn, null);
			}
			objCallableStatement.setArray(2, objActionNoticeFlagArray);
			informationNoticeFlagArrayDescriptor = new ArrayDescriptor(DatabaseConstants.STR_ARRAY,dconn);
			if(objUserVO.getInformationNoticeFlags() != null){
			objInformationNoticeFlagArray = new ARRAY(informationNoticeFlagArrayDescriptor, dconn, objUserVO.getInformationNoticeFlags());
			}else{
			LogUtil.logMessage("objUserVO.getInformationNoticeFlags():null");
			objInformationNoticeFlagArray = new ARRAY(informationNoticeFlagArrayDescriptor, objConnection, null);
			}
			objCallableStatement.setArray(3, objInformationNoticeFlagArray);
			cCNoticeFlagArrayDescriptor = new ArrayDescriptor(DatabaseConstants.STR_ARRAY,dconn);
			if(objUserVO.getCcNoticeFlags() != null){
			objCCNoticeFlagArray = new ARRAY(cCNoticeFlagArrayDescriptor, dconn, objUserVO.getCcNoticeFlags());
			}else{
			LogUtil.logMessage("objUserVO.getCcNoticeFlags():null");
			objCCNoticeFlagArray = new ARRAY(cCNoticeFlagArrayDescriptor, dconn, null);
			}
			objCallableStatement.setArray(4, objCCNoticeFlagArray);
			//Modified till here
			
			objCallableStatement.setString(5, objUserVO.getUserID());
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			
			intStatusCode = objCallableStatement.executeUpdate();
			LogUtil.logMessage("Update Result:" + intStatusCode);
			if (intStatusCode > 0) {
				intStatusCode = 0;
			}
			LogUtil.logMessage("Status Update" + intStatusCode);
			intLSDBErrorID = objCallableStatement.getInt(6);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(7);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(8);
			
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
				objConnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
		} catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException UserMaintDAO.save");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in UserMaintDAO.save"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException UserMaintDAO.save");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in UserMaintDAO.save"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception UserMaintDAO.save");
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
				.logMessage("Enters into Exception UserMaintDAO.save");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return intStatusCode;
	}
	
	public static int fetchEmailPeriod(UserVO objUserVO) throws EMDException {
		LogUtil.logMessage("Enters into UserMaintDAO.fetchEmailPeriod");
		Connection objConnection = null,objConnection1 = null;
		CallableStatement objCallableStatement = null,objCallableStatement1 = null;
		
		ResultSet rsEmailPeriod = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intEmailPeriod;
		int intLSDBErrorID = 0;
		String strMessage = null;
		String strLogUser = "";
		try {
			String Total = "call SP_USER_TEST(?,?)";
			strLogUser = objUserVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objConnection1 =DBHelper.prepareConnection();
			
			
			objCallableStatement = objConnection
			.prepareCall(AdmnQueries.SP_SELECT_EMAIL_REMINDER);
			
			objCallableStatement1 = objConnection.prepareCall(Total);
			
			objCallableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			
			if (objUserVO.getUserID()!= null) {
				objCallableStatement.setString(2, objUserVO.getUserID());
			} else {
				objCallableStatement.setNull(2, Types.NULL);
			}
			objCallableStatement.registerOutParameter(3, Types.INTEGER);
			objCallableStatement.registerOutParameter(4, Types.VARCHAR);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			rsEmailPeriod = (ResultSet) objCallableStatement.getObject(1);
			LogUtil.logMessage("Inside the fetchEmailPeriod method of UserDAO :resultSet"	+ rsEmailPeriod);
			
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
				objConnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
			while (rsEmailPeriod.next()) {
				LogUtil.logMessage("Inside ResultSet");
				objUserVO = new UserVO();
				objUserVO.setEmailPeriod(rsEmailPeriod.getInt(DatabaseConstants.LS012_EMAIL_PERIOD ));
			}
			intEmailPeriod = objUserVO.getEmailPeriod();
			LogUtil.logMessage("intEmailPeriod:" + intEmailPeriod);
			
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
			.logMessage("Enters into DataAccessException UserMaintDAO.fetchEmailPeriod");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in UserMaintDAO.purgeEmail"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException UserMaintDAO.fetchEmailPeriod");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in UserMaintDAO.fetchEmailPeriod"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception UserMaintDAO.fetchEmailPeriod");
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
				.logMessage("Enters into Exception UserMaintDAO.fetchEmailPeriod");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return intEmailPeriod;
	}
	
	public static int updateEmailPeriod(UserVO objUserVO) throws EMDException {
		LogUtil.logMessage("Enters into UserMaintDAO.updateEmailPeriod");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intStatusCode = 0;
		int intLSDBErrorID = 0;
		String strMessage = null;
		String strLogUser = "";
		try {
			strLogUser = objUserVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
			.prepareCall(AdmnQueries.SP_UPDATE_EMAIL_REMINDER);
			
			if(objUserVO.getEmailPeriod() <= 0 ){
				objCallableStatement.setNull(1, Types.NULL);
			}else{
				objCallableStatement.setInt(1, objUserVO.getEmailPeriod());
			}
			objCallableStatement.setString(2, objUserVO.getUserID());
			objCallableStatement.registerOutParameter(3, Types.INTEGER);
			objCallableStatement.registerOutParameter(4, Types.VARCHAR);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			
			intStatusCode = objCallableStatement.executeUpdate();
			LogUtil.logMessage("Update Result:" + intStatusCode);
			if (intStatusCode > 0) {
				intStatusCode = 0;
			}
			LogUtil.logMessage("Status Update" + intStatusCode);
			intLSDBErrorID = objCallableStatement.getInt(3);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(4);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(5);
			
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
				objConnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
		} catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException UserMaintDAO.updateEmailPeriod");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in UserMaintDAO.updateEmailPeriod"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException UserMaintDAO.updateEmailPeriod");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in UserMaintDAO.updateEmailPeriod"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception UserMaintDAO.updateEmailPeriod");
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
				.logMessage("Enters into Exception UserMaintDAO.updateEmailPeriod");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return intStatusCode;
	}
	//Added for CR-126 ends here
	
	//Added for CR-128 starts here
	public static ArrayList fetchActivityLog(UserVO objUserVO) throws EMDException {
		LogUtil.logMessage("Entering UserDAO.fetchActivityLog");
		Connection objConnnection = null;
		ArrayList arlActivityLog = new ArrayList();
		ResultSet rsActivityLog = null;
		CallableStatement objCallableStatement = null;
		
		// Error out parameters
		int intLSDBErrorID = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strLogUser = "";
		try {
			
			strLogUser = objUserVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			
			LogUtil.logMessage("objConnnection in DAO :" + objConnnection);
			objCallableStatement = objConnnection
			.prepareCall(AdmnQueries.SP_FETCH_ACTIVITY_LOG);
			objCallableStatement.setString(1, objUserVO.getUserID());
			objCallableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(3, Types.INTEGER);
			objCallableStatement.registerOutParameter(4, Types.VARCHAR);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			rsActivityLog = (ResultSet) objCallableStatement.getObject(2);
			LogUtil
			.logMessage("Inside the fetchActivityLog method of UserDAO :resultSet"
					+ rsActivityLog);
			
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
				throw new ApplicationException(objErrorInfo);
			}
			while (rsActivityLog.next()) {
				objUserVO = new UserVO();
				LogUtil.logMessage("Inside ResultSet");
				objUserVO.setActivityType(rsActivityLog.getString(DatabaseConstants.LS091_ACTIVITY_TYPE));
				objUserVO.setEventType(rsActivityLog.getString(DatabaseConstants.LS092_EVENT_TYPE));
				objUserVO.setEventFrom(rsActivityLog.getString(DatabaseConstants.LS093_EVENT_FROM));
				objUserVO.setEventTo(rsActivityLog.getString(DatabaseConstants.LS093_EVENT_TO));
				objUserVO.setActionBy(rsActivityLog.getString(DatabaseConstants.ACTIONBY));
				objUserVO.setActionOn(rsActivityLog.getString(DatabaseConstants.ACTIONON));
				//commented for Tomcat
				objUserVO.setModifiedFor(rsActivityLog.getString(DatabaseConstants.LS093_MODIFIED_FOR)); //Added for CR-128
				arlActivityLog.add(objUserVO);
				
			}
			LogUtil.logMessage("arlActivityLog" +arlActivityLog.size());
		} catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException UserMaintDAO.fetchActivityLog");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in UserMaintDAO.fetchActivityLog"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException UserMaintDAO.fetchActivityLog");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in UserMaintDAO.fetchActivityLog"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception UserMaintDAO.fetchActivityLog");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(rsActivityLog, objCallableStatement,
						objConnnection);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception UserMaintDAO.fetchActivityLog");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return arlActivityLog;
		
	}
	//Added for CR-128 ends here
}
