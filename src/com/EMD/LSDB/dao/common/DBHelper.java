/*
 * Created on Apr 13, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.dao.common;

import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.*;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.sql.RowSet;

//import weblogic.jdbc.rowset.RowSetFactory;
//import weblogic.jdbc.rowset.WLCachedRowSet;



import oracle.jdbc.OracleConnection;

import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.constant.DatabaseConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.exception.FatalException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.resourceloader.EMDEnvLoader;
import com.EMD.LSDB.vo.common.FileVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.sun.rowset.CachedRowSetImpl;

/**
 * @author MM57219
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class DBHelper {
	
	public static Connection prepareConnection() throws Exception {
		Connection connection = null;
		Connection con2 = null;
		Hashtable properties = new Hashtable();
		try {
			
			String strContextFactory = null;
			String strJNDIName = null;
			String strProURL = null;
			/** * Getting the data source name ** */
			EMDEnvLoader objEMDEnvLoader = EMDEnvLoader.getInstance();
			
			strContextFactory = (String) objEMDEnvLoader
			.getEnvEntryValue(DatabaseConstants.CONTEXT_FACTORY);
			strJNDIName = (String) objEMDEnvLoader
			.getEnvEntryValue(DatabaseConstants.JNDI_NAME);
			strProURL = (String) objEMDEnvLoader
			.getEnvEntryValue(DatabaseConstants.PROVIDER_URL_VALUE);
			/** *** Ends here *** */
			
			LogUtil
			.logMessage("Inside the Database:Connection() method........");
			System.out.println("inside dbhelper.java");
			/*Removed for Tomcat
			properties.put(Context.INITIAL_CONTEXT_FACTORY, strContextFactory);
			
			properties.put(Context.PROVIDER_URL, strProURL);
			
			Context context = new InitialContext(properties);
			
			DataSource datasource = (DataSource) context.lookup(strJNDIName);
			
			connection = datasource.getConnection();
			
			connection.setAutoCommit(false);*/
			
			//Added for Tomcat
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
			connection = ds.getConnection();
			
			//Connection dconn = ((DelegatingConnection) connection).getInnermostDelegate();
			
			connection.setAutoCommit(false);
			
		} catch (SQLException sqle) {
			
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(sqle.toString());
			throw new ApplicationException(objErrorInfo);
			//sqle.printStackTrace();
		} catch (Exception ex) {
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ex.toString());
			throw new ApplicationException(objErrorInfo);
			//ex.printStackTrace();
		}
		
		return connection;
	}
	
	public static Connection connectionforMariaDB() throws Exception{
		LogUtil.logMessage("Inside the Maria DB");
		System.out.println("inside mariadb connection");
        Connection con =null;

		try {
	    Class.forName("org.mariadb.jdbc.Driver");	    
		CallableStatement  cs = null;
	//Connection con1=DriverManager.getConnection("jdbc:oracle:thin:@172.18.24.148:1521:orcl","lsdb","lsdb");
	//	ResultSet rs = null;
		//Class.forName(HscpConstants.db2Driver);	
		/*Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/mymaria");
		con = ds.getConnection();*/
	    con = DriverManager.getConnection("jdbc:mariadb://172.18.24.148:3306/lsdb","root","techm");	
	    //con = DriverManager.getConnection("jdbc:mariadb://172.18.24.147:3306/lsdb","root","techm");	
		LogUtil.logMessage("Inside the fetchEmailPeriod method of UserDAO :resultSet"	);
		LogUtil.logMessage("outside the Maria DB");
		System.out.println("out of mariadb connection");
		}
		
		catch(Exception e){
			LogUtil.logMessage("Inside the fetchEmailPeriod method of UserDAO :resultSet"	+ e.getLocalizedMessage());
			System.out.println("Inside the fetchEmailPeriod method of UserDAO :resultSet"	+ e.getLocalizedMessage());
		}
		return con;
	}
	
	public static Connection connectionforPostgres() throws Exception{
		LogUtil.logMessage("Inside the PostgreSQL db");
		System.out.println("inside PostgreSQl db connection");
		Connection con1 = null;
		
		
		try {
			Class.forName("org.postgresql.Driver");
			CallableStatement cs2 = null;
			
			con1 = DriverManager.getConnection("jdbc:postgresql://172.18.24.148:5432/Test1","postgres","root");
			LogUtil.logMessage("Inside the PostgreSQL DB connection");
			LogUtil.logMessage("outside the PostgreSQL DB");  
			System.out.println("out of postgresqldb connection");
			}
		
		catch(Exception e){
			LogUtil.logMessage("Inside the catchblock of dbhelper postgre exception"	+ e.getLocalizedMessage());
			System.out.println("Inside the catchblock of dbhelper postgre exception"	+ e.getLocalizedMessage());
		}
		return con1;
		}
		
	public static RowSet executeQuery(Connection p_connection, String p_sql,
			List p_parameters) throws DataAccessException, FatalException, SQLException {
		PreparedStatement preparedstatement = null;
		//WLCachedRowSet cachedRowset = null;
		CachedRowSetImpl cachedRowset = new CachedRowSetImpl();
		ResultSet resultSet = null;
		boolean brokenPipeFlag = false;
		if (p_connection == null) {
			throw new DataAccessException(new NullPointerException());
		}
		try {
			preparedstatement = p_connection.prepareStatement(p_sql);
			setQueryParameters(preparedstatement, p_parameters);
			resultSet = preparedstatement.executeQuery();
			if (resultSet != null) {
				/*RowSetFactory factory = RowSetFactory.newInstance();
				cachedRowset = factory.newCachedRowSet();
				cachedRowset.populate(resultSet); Commented for trying Tomcat */

				cachedRowset.populate(resultSet);
			}
		} catch (SQLException sqle) {
			throw new DataAccessException(sqle);
		} catch (Exception exc) {
			if (!brokenPipeFlag
					&& exc.getMessage() != null
					&& ((((exc.getMessage()).trim())
							.startsWith("java.io.IOException: Broken pipe")) || (((exc
									.getMessage()).trim())
									.startsWith("java.net.SocketException: Connection reset by peer: socket")))) {
				try {
					DBHelper.closeConnection(p_connection);
					brokenPipeFlag = true;
					p_connection = DBHelper.prepareConnection();
					cachedRowset = (CachedRowSetImpl) DBHelper
					.executeDatabaseQuery(p_connection, p_sql,
							p_parameters);
				} catch (Exception ex) {
					ErrorInfo errorInfoObj = new ErrorInfo();
					errorInfoObj.setMessage(ex.getMessage());
					throw new DataAccessException(errorInfoObj);
				} finally {
					try {
						if (p_connection != null && !p_connection.isClosed()) {
							DBHelper.closeConnection(p_connection);
						}
					} catch (Exception sqlex) {
						throw new DataAccessException(sqlex);
					}
				}
			} else {
				throw new DataAccessException(exc);
			}
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedstatement != null) {
					preparedstatement.close();
				}
			} catch (SQLException sqlex) {
				throw new DataAccessException(sqlex);
			}
		}
		return cachedRowset;
	}
	
	private static void setQueryParameters(
			PreparedStatement p_preparedStatement, List p_params)
	throws SQLException, Exception {
		try {
			for (int i = 0; p_params != null && i < p_params.size(); i++) {
				Object inputObjects = p_params.get(i);
				processParams(p_preparedStatement, inputObjects, i);
			}
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			throw new SQLException(sqlex.getMessage());
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}
	
	public static void closeConnection(Connection p_connection)
	throws DataAccessException, FatalException {
		try {
			if (p_connection != null && !p_connection.isClosed()) {
				//DefaultConnectionPool objDefaultConnectionPool = new DefaultConnectionPool();
				
				//objDefaultConnectionPool.releaseConnection(p_connection);
				p_connection.commit();
				p_connection.close();
			}
		} catch (SQLException sqle) {
			throw new DataAccessException(sqle);
		} catch (Exception ex) {
			throw new DataAccessException(ex);
		}
	}
	
	private static void processParams(PreparedStatement p_preparedStatement,
			Object p_param, int p_index) throws SQLException, Exception {
		
		if (p_param == null) {
			p_preparedStatement.setNull(p_index + 1, Types.NULL);
		} else if (p_param instanceof String) {
			String strValue = (String) p_param;
			processStringParam(p_preparedStatement, strValue, p_index);
		} else if (p_param instanceof Integer) {
			Integer integerValue = (Integer) p_param;
			int intValue = integerValue.intValue();
			p_preparedStatement.setInt(p_index + 1, intValue);
		} else if (p_param instanceof Float) {
			Float floatValue = (Float) p_param;
			float fltValue = floatValue.floatValue();
			p_preparedStatement.setFloat(p_index + 1, fltValue);
		} else if (p_param instanceof Double) {
			Double doubleValue = (Double) p_param;
			double dblValue = doubleValue.doubleValue();
			p_preparedStatement.setDouble(p_index + 1, dblValue);
		} else if (p_param instanceof Long) {
			Long longValue = (Long) p_param;
			long lngValue = longValue.longValue();
			p_preparedStatement.setLong(p_index + 1, lngValue);
		} else if (p_param instanceof Boolean) {
			Boolean booleanValue = (Boolean) p_param;
			boolean boolValue = booleanValue.booleanValue();
			p_preparedStatement.setBoolean(p_index + 1, boolValue);
		} else if (p_param instanceof Date) {
			Date date = (Date) p_param;
			p_preparedStatement.setDate(p_index + 1, date);
		} else if (p_param instanceof Timestamp) {
			Timestamp timestamp = (Timestamp) p_param;
			p_preparedStatement.setTimestamp(p_index + 1, timestamp);
		} else if (p_param instanceof Time) {
			Time time = (Time) p_param;
			p_preparedStatement.setTime(p_index + 1, time);
		} else if (p_param instanceof Clob) {
			Clob clob = (Clob) p_param;
			p_preparedStatement.setClob(p_index + 1, clob);
		}
	}
	
	private static void processStringParam(
			PreparedStatement p_preparedStatement, String p_strValue,
			int p_index) throws SQLException {
		if (p_strValue.equals(VARCHAR_COLUMN)) {
			p_preparedStatement.setNull(p_index + 1, Types.VARCHAR);
		} else if (p_strValue.equals(INTEGER_COLUMN)) {
			p_preparedStatement.setNull(p_index + 1, Types.INTEGER);
		} else if (p_strValue.equals(LONG_COLUMN)) {
			p_preparedStatement.setNull(p_index + 1, Types.LONGVARCHAR);
		} else if (p_strValue.equals(FLOAT_COLUMN)) {
			p_preparedStatement.setNull(p_index + 1, Types.FLOAT);
		} else if (p_strValue.equals(DOUBLE_COLUMN)) {
			p_preparedStatement.setNull(p_index + 1, Types.DOUBLE);
		} else if (p_strValue.equals(DATE_COLUMN)) {
			p_preparedStatement.setNull(p_index + 1, Types.DATE);
		} else if (p_strValue.equals(TIME_COLUMN)) {
			p_preparedStatement.setNull(p_index + 1, Types.TIME);
		} else if (p_strValue.equals(TIMESTAMP_COLUMN)) {
			p_preparedStatement.setNull(p_index + 1, Types.TIMESTAMP);
		} else if (p_strValue.equals(CLOB_COLUMN)) {
			p_preparedStatement.setNull(p_index + 1, Types.CLOB);
		} else {
			
			p_preparedStatement.setString(p_index + 1, p_strValue);
		}
	}
	
	private static RowSet executeDatabaseQuery(Connection p_connection,
			String p_sql, List p_parameters) throws SQLException, Exception {
		PreparedStatement preparedstatement = null;
		ResultSet resultSet = null;
		//WLCachedRowSet cachedRowSet = null;
		CachedRowSetImpl cachedRowset = new CachedRowSetImpl();
		try {
			preparedstatement = p_connection.prepareStatement(p_sql);
			setQueryParameters(preparedstatement, p_parameters);
			resultSet = preparedstatement.executeQuery();
			if (resultSet != null) {
				/*RowSetFactory factory = RowSetFactory.newInstance();
				cachedRowSet = factory.newCachedRowSet();*/
				cachedRowset.populate(resultSet);
			}
		} catch (SQLException sqle) {
			throw new SQLException(sqle.getMessage());
		} catch (Exception exc) {
			throw new Exception(exc.getMessage());
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedstatement != null) {
					preparedstatement.close();
				}
			} catch (SQLException sqlex) {
				throw new SQLException(sqlex.getMessage());
			}
		}
		return cachedRowset;
	}
	
	/**
	 * @description to execute Delete/Update statement in the database
	 * @param Connection object, String sqlQuery, List inputParameters
	 * @throws DataAccessException
	 * @return int  - number of rows updated
	 */
	public static int executeUpdate(Connection p_connection, String p_sql,
			List p_parameters) throws DataAccessException, FatalException {
		PreparedStatement preparedStatement = null;
		boolean hasBrokenPipeErrorOccuredBefore = false;
		int noOfRowsUpdated = 0;
		try {
			preparedStatement = p_connection.prepareStatement(p_sql);
			setQueryParameters(preparedStatement, p_parameters);
			noOfRowsUpdated = preparedStatement.executeUpdate();
		} catch (SQLException sqle) {
			throw new DataAccessException(sqle);
		} catch (Exception exc) {
			if (!hasBrokenPipeErrorOccuredBefore
					&& exc.getMessage() != null
					&& exc.getMessage().equalsIgnoreCase(
					"Exception d'E/S: Broken pipe")) {
				try {
					DBHelper.closeConnection(p_connection);
					hasBrokenPipeErrorOccuredBefore = true;
					p_connection = DBHelper.prepareConnection();
					noOfRowsUpdated = DBHelper.executeDatabaseUpdate(
							p_connection, p_sql, p_parameters);
				} catch (Exception ex) {
					throw new DataAccessException(ex);
				} finally {
					try {
						if (p_connection != null && !p_connection.isClosed()) {
							DBHelper.closeConnection(p_connection);
						}
					} catch (Exception sqlex) {
						throw new DataAccessException(sqlex);
					}
				}
			} else {
				throw new DataAccessException(exc);
			}
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException sqlex) {
				throw new DataAccessException(sqlex);
			}
		}
		return noOfRowsUpdated;
	}
	
	private static int executeDatabaseUpdate(Connection p_connection,
			String p_query, List p_parameters) throws SQLException, Exception {
		int rowsUpdated = 0;
		PreparedStatement preparedstatement = null;
		try {
			preparedstatement = p_connection.prepareStatement(p_query);
			setQueryParameters(preparedstatement, p_parameters);
			rowsUpdated = preparedstatement.executeUpdate();
		} catch (SQLException sqle) {
			throw new SQLException(sqle.getMessage());
		} catch (Exception exc) {
			throw new Exception(exc.getMessage());
		} finally {
			try {
				if (preparedstatement != null) {
					preparedstatement.close();
				}
			} catch (SQLException sqlex) {
				throw new SQLException(sqlex.getMessage());
			}
		}
		return rowsUpdated;
	}
	
	/********Callable Statement **************/
	
	/**
	 * @description This method is a utility method to execute a procedure
	 * @param Connection object, String Procedure Name, List input parameters
	 * @throws DataAccessException
	 * @return ArrayList 
	 */
	public static ArrayList executeProcedureWithThreeOutParam(
			Connection p_connection, String p_procName, List p_inputParameters)
	throws DataAccessException, FatalException {
		
		CallableStatement callableStatement = null;
		//WLCachedRowSet cachedRowSet = null;
		
		ArrayList array = new ArrayList();
		int noOfInputParams = 0;
		
		String strErrorID = null;
		int intOracleCode = 0;
		String strErrorMessage = null;
		
		try {
			if (p_inputParameters != null) {
				noOfInputParams = p_inputParameters.size();
			}
			callableStatement = p_connection
			.prepareCall(getProcedureQueryForInOutParamsWithThreeOutParam(
					p_procName, noOfInputParams));
			setCSQueryParameters(callableStatement, p_inputParameters);
			
			callableStatement.registerOutParameter(noOfInputParams + 1,
					oracle.jdbc.driver.OracleTypes.VARCHAR);
			
			callableStatement.registerOutParameter(noOfInputParams + 2,
					oracle.jdbc.driver.OracleTypes.INTEGER);
			
			callableStatement.registerOutParameter(noOfInputParams + 3,
					oracle.jdbc.driver.OracleTypes.VARCHAR);
			
			callableStatement.execute();
			
			strErrorID = (String) callableStatement
			.getString(noOfInputParams + 1);
			
			intOracleCode = callableStatement.getInt(noOfInputParams + 2);
			
			strErrorMessage = (String) callableStatement
			.getString(noOfInputParams + 3);
			
			array.add(0, "" + strErrorID);
			array.add(1, "" + intOracleCode);
			array.add(2, "" + strErrorMessage);
			
		} catch (SQLException sqle) {
			throw new DataAccessException(sqle);
		} catch (Exception exc) {
			throw new DataAccessException(exc);
		} finally {
			try {
				
				if (callableStatement != null) {
					callableStatement.close();
				}
			} catch (SQLException sqlex) {
				throw new DataAccessException(sqlex);
			}
		}
		return array;
	}
	
	/**
	 * @description This method is a utility method to create procedure Query
	 * @param Procedure Name, No of input parameters
	 * @return String 
	 */
	private static String getProcedureQueryForInOutParamsWithThreeOutParam(
			String p_procedureName, int p_noOfInputParams) {
		StringBuffer procedureQuery = new StringBuffer();
		procedureQuery.append("{call ");
		procedureQuery.append(p_procedureName);
		procedureQuery.append("(");
		int i = 0;
		while (i < p_noOfInputParams) {
			if (i == 0) {
				procedureQuery.append("?");
			} else {
				procedureQuery.append(", ?");
			}
			i++;
		}
		if (i == 0) {
			procedureQuery.append("?");
		} else {
			procedureQuery.append(", ?,?,?");
		}
		procedureQuery.append(")}");
		return procedureQuery.toString();
	}
	
	/**
	 * @description to set the input parameters for SQL query while using callable statement
	 * @param CallableStatement, List inputParameters
	 * @exception throws SQLException, Exception 
	 */
	private static void setCSQueryParameters(
			CallableStatement p_callableStatement, List p_params)
	throws SQLException, Exception {
		try {
			for (int i = 0; p_params != null && i < p_params.size(); i++) {
				Object inputObjects = p_params.get(i);
				processCSParams(p_callableStatement, inputObjects, i);
			}
		} catch (SQLException sqlex) {
			throw new SQLException(sqlex.getMessage());
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}
	
	/**
	 * @description check the datatype and set parameters for callable statement
	 * @param CallableStatement, Object inputParameters, index to set the 
	 *	input param value
	 * @exception throws SQLException, Exception 
	 * @return void
	 */
	private static void processCSParams(CallableStatement p_callableStatement,
			Object p_param, int p_index) throws SQLException, Exception {
		if (p_param instanceof String) {
			String strValue = (String) p_param;
			processCSStringParam(p_callableStatement, strValue, p_index);
		} else if (p_param instanceof Integer) {
			Integer integerValue = (Integer) p_param;
			int intValue = integerValue.intValue();
			p_callableStatement.setInt(p_index + 1, intValue);
		} else if (p_param instanceof Float) {
			Float floatValue = (Float) p_param;
			float fltValue = floatValue.floatValue();
			p_callableStatement.setFloat(p_index + 1, fltValue);
		} else if (p_param instanceof Double) {
			Double doubleValue = (Double) p_param;
			double dblValue = doubleValue.doubleValue();
			p_callableStatement.setDouble(p_index + 1, dblValue);
		} else if (p_param instanceof Long) {
			Long longValue = (Long) p_param;
			long lngValue = longValue.longValue();
			p_callableStatement.setLong(p_index + 1, lngValue);
		} else if (p_param instanceof Boolean) {
			Boolean booleanValue = (Boolean) p_param;
			boolean boolValue = booleanValue.booleanValue();
			p_callableStatement.setBoolean(p_index + 1, boolValue);
		} else if (p_param instanceof Date) {
			Date date = (Date) p_param;
			p_callableStatement.setDate(p_index + 1, date);
		} else if (p_param instanceof Timestamp) {
			Timestamp timestamp = (Timestamp) p_param;
			p_callableStatement.setTimestamp(p_index + 1, timestamp);
		} else if (p_param instanceof Time) {
			Time time = (Time) p_param;
			p_callableStatement.setTime(p_index + 1, time);
		} else if (p_param instanceof Clob) {
			Clob clob = (Clob) p_param;
			p_callableStatement.setClob(p_index + 1, clob);
		}
	}
	
	/**
	 * @description check the datatype and set parameters for callable statement
	 * @param CallableStatement, String value, index of input param
	 * @exception throws SQLException
	 * @return void
	 */
	private static void processCSStringParam(
			CallableStatement p_callableStatement, String p_strValue,
			int p_index) throws SQLException {
		if (p_strValue.equals(VARCHAR_COLUMN)) {
			p_callableStatement.setNull(p_index + 1, Types.VARCHAR);
		} else if (p_strValue.equals(INTEGER_COLUMN)) {
			p_callableStatement.setNull(p_index + 1, Types.INTEGER);
		} else if (p_strValue.equals(LONG_COLUMN)) {
			p_callableStatement.setNull(p_index + 1, Types.LONGVARCHAR);
		} else if (p_strValue.equals(FLOAT_COLUMN)) {
			p_callableStatement.setNull(p_index + 1, Types.FLOAT);
		} else if (p_strValue.equals(DOUBLE_COLUMN)) {
			p_callableStatement.setNull(p_index + 1, Types.DOUBLE);
		} else if (p_strValue.equals(DATE_COLUMN)) {
			p_callableStatement.setNull(p_index + 1, Types.DATE);
		} else if (p_strValue.equals(TIME_COLUMN)) {
			p_callableStatement.setNull(p_index + 1, Types.TIME);
		} else if (p_strValue.equals(TIMESTAMP_COLUMN)) {
			p_callableStatement.setNull(p_index + 1, Types.TIMESTAMP);
		} else if (p_strValue.equals(CLOB_COLUMN)) {
			p_callableStatement.setNull(p_index + 1, Types.CLOB);
		} else {
			p_callableStatement.setString(p_index + 1, p_strValue);
		}
	}
	
	public static void closeSQLObjects(ResultSet resulset,
			CallableStatement callablestatement, Connection connection)
	throws Exception {
		try {
			if (resulset != null) {
				resulset.close();
				resulset = null;
			}
			if (callablestatement != null) {
				callablestatement.close();
				callablestatement = null;
			}
			
			//connection = null;
			if (connection != null) {
				DBHelper.closeConnection(connection);
			}
		} catch (Exception ex) {
			throw new Exception(ex);
		}
	}
	
	/**
	 * @description This method is a utility method to close More than one resultsets and connection object
	 * 
	 * @param ArrayList object, CallableStatement object, Connection object
	 * @return void 
	 */
	
	public static void closeResultSets(ArrayList arlResulset,
			CallableStatement callablestatement, Connection connection)
	throws Exception {
		try {
			
			for (int counter = 0; counter < arlResulset.size(); counter++) {
				ResultSet objResultSet = (ResultSet) arlResulset.get(counter);
				
				if (objResultSet != null) {
					objResultSet.close();
					objResultSet = null;
				}
			}
			
			if (callablestatement != null) {
				callablestatement.close();
				callablestatement = null;
			}
			
			if (connection != null) {
				connection.commit();
				DBHelper.closeConnection(connection);
			}
		} catch (Exception ex) {
			throw new Exception(ex);
		}
	}
	
	/**
	 * @description This method is a utility method to upload file into database
	 * After inserting an empty blob into database, this method will be called
	 * to insert the actual file into database.
	 * @param Connection object, String Query, FileTO object
	 * @return boolean 
	 */
	public static boolean executeDatabaseUpdateUpload(Connection p_connection,
			String p_query, FileVO p_fileto) throws DataAccessException {
		boolean rowsUpdated = false;
		PreparedStatement selectpreparedstatement = null;
		try {
			InputStream fileby = p_fileto.getFileStream();
			int intFileLength = p_fileto.getFileLength();
			selectpreparedstatement = p_connection.prepareStatement(p_query);
			selectpreparedstatement.setBinaryStream(1, fileby, intFileLength);
			int i = selectpreparedstatement.executeUpdate();
			rowsUpdated = true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(sqle);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException(e);
		}
		return rowsUpdated;
	}
	
	/**
	 * @description to get the next sequence number 
	 * @param Connection object, String Sequence Name
	 * @throws DataAccessException
	 * @return int  - Next sequence Number
	 */
	public static synchronized int getSequenceNumber(Connection p_connection,
			String p_seqName) throws DataAccessException, FatalException {
		int nextSequenceNumber = 0;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = p_connection.createStatement();
			resultSet = statement.executeQuery("Select " + p_seqName
					+ ".nextval from dual");
			while (resultSet.next()) {
				nextSequenceNumber = resultSet.getInt(1);
			}
		} catch (SQLException sqe) {
			throw new DataAccessException(sqe);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException sqlex) {
				throw new DataAccessException(sqlex);
			}
		}
		return nextSequenceNumber;
	}
	
	//Added for CR-128 QA Fix - This sets DBMS Session parameter
	public static void setSessionUserID(LoginVO objLoginVo)
	throws EMDException {
		LogUtil.logMessage("Inside setSessionUserID Method");
		
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		try {
				objConnection = DBHelper.prepareConnection();
				objCallableStatement = objConnection.prepareCall(EMDQueries.Query_Set_Dbms_Session_UserID);
				objCallableStatement.setString(1, objLoginVo.getUserID());
				objCallableStatement.execute();
			
		}catch (Exception objExp) {
					LogUtil.logMessage("Enters into Exception exception...");
					ErrorInfo objErrorInfo = new ErrorInfo();
					objErrorInfo.setMessage(ApplicationConstants.LOG_USER + objLoginVo.getUserID()
							+ "-" + objExp.getMessage());
					throw new ApplicationException(objExp, objErrorInfo);
		} finally {
			try {
				
				DBHelper.closeSQLObjects(null, objCallableStatement,
						objConnection);
			}
			
			catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception exception...");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ objLoginVo.getUserID() + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
	}
	//Addedd for CR-128 QA Fix Ends here
	
	public static final String INTEGER_COLUMN = "integerColumn";
	
	public static final String VARCHAR_COLUMN = "varcharColumn";
	
	public static final String FLOAT_COLUMN = "floatColumn";
	
	public static final String LONG_COLUMN = "longColumn";
	
	public static final String DOUBLE_COLUMN = "doubleColumn";
	
	public static final String DATE_COLUMN = "dateColumn";
	
	public static final String TIME_COLUMN = "timeColumn";
	
	public static final String TIMESTAMP_COLUMN = "timestampColumn";
	
	public static final String BOOLEAN_COLUMN = "booleanColumn";
	
	public static final String CLOB_COLUMN = "clobColumn";
	
}