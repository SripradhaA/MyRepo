/*
 * Created on Jun 7, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.common.errorhandler;

import java.sql.Statement;
import java.util.Date;

import com.EMD.LSDB.common.exception.DataAccessException;

/**
 * @author mm57219
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ErrorInfoDAO {
	public ErrorInfo m_errorInfo;
	
	/**
	 * Default constructor with no parameter
	 */
	public ErrorInfoDAO() {
	}
	
	/**
	 * 1. Get the error details from the database 2. Create ErrorInfo object 3.
	 * Fill the error details 4. Return ErrorInfo Object
	 * 
	 * @param p_methodName
	 * @param p_className
	 * @param p_componentName
	 * @param errorCode
	 * @return ErrorInfo
	 */
	public ErrorInfo getErrorInfo(ErrorInfo p_errorInfo)
	throws DataAccessException {
		p_errorInfo = getErrorInfo(p_errorInfo.getErrorId(), p_errorInfo);
		Date date = new Date();
		p_errorInfo.setTimeStamp(date);
		return p_errorInfo;
	}
	
	/**
	 * This method is used to get the error information based on the error id.
	 * 
	 * @param errorID
	 * @return ErrorInfo
	 */
	private ErrorInfo getErrorInfo(String errorID, ErrorInfo m_errorInfo)
	throws DataAccessException {
		java.sql.Connection connection = null;
		Statement statement = null;
		String m_message = "";
		String l_errorLvl = "";
		String l_errorType = "";
		if (m_errorInfo == null) {
			m_errorInfo = new ErrorInfo();
		}
		/*
		 * try { connection = Database.prepareConnection() ; List params = new
		 * ArrayList(); params.add(errorID); String query = "select ERR_MSG,
		 * ERR_LVL, ERR_TYPE from ERROR_INFO where ERR_ID =?"; RowSet resultSet =
		 * Database.getResultsVector (connection,query,params);
		 * while(resultSet.next()) { m_message = resultSet.getString("ERR_MSG") ;
		 * l_errorLvl = (String) resultSet.getString("ERR_LVL") ; l_errorType =
		 * (String) resultSet.getString("ERR_TYPE") ; }
		 * m_errorInfo.setMessage(m_message) ; ErrorLevel m_errorLvl =
		 * ErrorLevel.get ( l_errorLvl ) ; m_errorInfo.setErrorLevel (
		 * m_errorLvl ) ; ErrorType m_Type = ErrorType.get ( l_errorType ) ;
		 * m_errorInfo.setErrorType ( m_Type ) ; m_errorInfo.setErrorId (
		 * errorID ) ; } catch (SQLException sqle) { ErrorInfo errorInfoObj =
		 * new ErrorInfo(); errorInfoObj.setMessage(sqle.getMessage()); throw
		 * new DataAccessException(errorInfoObj); }
		 * 
		 * finally { //DatabaseHelper.closeConnection(connection) ; }
		 */
		return m_errorInfo;
	}
	
}