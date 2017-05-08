/**
 * 
 */
package com.EMD.LSDB.dao.MasterMaintenance;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

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
import com.EMD.LSDB.vo.common.SubSectionVO;

/**
 * @author vv49326
 * 
 */
public class ModelSubSectionDAO extends EMDDAO {
	
	private ModelSubSectionDAO() {
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSubSecMaintVO
	 *            The object for fetch SubSections
	 * @return Arraylist It has Arraylist of SubSecMaintVO
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList fetchSubSections(SubSectionVO objSubSecMaintVO)
	throws EMDException {
		LogUtil.logMessage("Entering ModelSubSectionDAO.fetchSubSections");
		
		Connection objConnnection = null;
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		
		int intLSDBErrorID = 0;
		ArrayList arlSubSection = new ArrayList();
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		String strSubSectionName = null;
		String strSubSectionCode = null;
		String strSubSecDisplay = null;
		String strLogUser = "";
		try {
			strLogUser = objSubSecMaintVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in DAO :" + objConnnection);
			int subSecNo=objSubSecMaintVO.getSecSeqNo();
			callableStatement = objConnnection
			.prepareCall(EMDQueries.SP_SELECT_MDL_SUBSECTION);
			callableStatement.setInt(1, objSubSecMaintVO.getModelSeqNo());
			callableStatement.setInt(2, objSubSecMaintVO.getSecSeqNo());
			callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			callableStatement.setString(4, objSubSecMaintVO.getUserID());
			callableStatement.registerOutParameter(5, Types.INTEGER);
			callableStatement.registerOutParameter(6, Types.VARCHAR);
			callableStatement.registerOutParameter(7, Types.VARCHAR);
			callableStatement.execute();
			
			resultSet = (ResultSet) callableStatement.getObject(3);
			intLSDBErrorID = (int) callableStatement.getInt(5);
			strOracleCode = (String) callableStatement.getString(6);
			strErrorMessage = (String) callableStatement.getString(7);
			
			// Handled Valid Exception
			if (intLSDBErrorID != 0) {
				ErrorInfo objErrorInfo = new ErrorInfo();
				LogUtil.logMessage("Error message in DAO:" + strMessage);
				objErrorInfo.setMessageID(String.valueOf(intLSDBErrorID));
				throw new DataAccessException(objErrorInfo);
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {
				// Un handled exception
				LogUtil.logMessage("strOracleCode:" + strOracleCode);
				ErrorInfo objErrorInfo = new ErrorInfo();
				StringBuffer strBuffer = new StringBuffer();
				strBuffer.append(strOracleCode + " ");
				strBuffer.append(strErrorMessage);
				objErrorInfo.setMessage(strBuffer.toString());
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
			while (resultSet.next()) {
				LogUtil.logMessage("Inside ResultSet");
				strSubSectionName = resultSet
				.getString(DatabaseConstants.LS202_SUBSEC_NAME);
				strSubSectionCode = resultSet
				.getString(DatabaseConstants.SUBSEC_CODE);
				strSubSecDisplay = strSubSectionCode + "." + strSubSectionName;
				objSubSecMaintVO = new SubSectionVO();
				objSubSecMaintVO.setSubSecSeqNo(resultSet
						.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO));
				objSubSecMaintVO.setSubSecName(resultSet
						.getString(DatabaseConstants.LS202_SUBSEC_NAME));
				objSubSecMaintVO.setSubSecCode(resultSet
						.getString(DatabaseConstants.SUBSEC_CODE));
				objSubSecMaintVO.setSubSecDesc(resultSet
						.getString(DatabaseConstants.LS202_SUBSEC_DESC));
				objSubSecMaintVO.setModelSeqNo(resultSet
						.getInt(DatabaseConstants.LS200_MDL_SEQ_NO));
				objSubSecMaintVO.setSecSeqNo(resultSet
						.getInt(DatabaseConstants.LS201_SEC_SEQ_NO));
				objSubSecMaintVO.setSubSecDisplay(strSubSecDisplay);
				arlSubSection.add(objSubSecMaintVO);
				
			}
			
		} catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException block in ModelSubSectionDAO.fetchSubSections");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into catch block in ModelSubSectionDAO.fetchSubSections"
					+ objErrorInfo.getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException ModelSubSectionDAO.fetchSubSections");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into catch block in ModelSubSectionDAO.fetchSubSections"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception ModelSubSectionDAO.fetchSubSections");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(resultSet, callableStatement,
						objConnnection);
			} catch (SQLException sqlex) {
				LogUtil
				.logMessage("Enters into Exception ModelSubSectionDAO.fetchSubSections");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + sqlex.getMessage());
				throw new ApplicationException(sqlex, objErrorInfo);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception ModelSubSectionDAO.fetchSubSections");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return arlSubSection;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSubSecMaintVO
	 *            The object for Insert SubSections
	 * @return Boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static synchronized int insertSubSection(
			SubSectionVO objSubSecMaintVO) throws EMDException {
		LogUtil.logMessage("Entering ModelSubSectionDAO.insertSubSection");
		Connection objConnnection = null;
		CallableStatement callableStatement = null;
		
		int intStatus = 0;
		int intLSDBErrorID = 0;
		
		String strOracleCode = null;
		String strErrorMessage = null;
		String strLogUser = "";
		try {
			strLogUser = objSubSecMaintVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("Connection Established :" + objConnnection);
			
			callableStatement = objConnnection
			.prepareCall(EMDQueries.SP_INSERT_MDL_SUBSECTION);
			callableStatement.setInt(1, objSubSecMaintVO.getModelSeqNo());
			callableStatement.setInt(2, objSubSecMaintVO.getSecSeqNo());
			callableStatement.setString(3, objSubSecMaintVO.getSubSecName());
			callableStatement.setString(4, objSubSecMaintVO.getSubSecDesc());
			callableStatement.setString(5, objSubSecMaintVO.getUserID());
			callableStatement.registerOutParameter(6, Types.INTEGER);
			callableStatement.registerOutParameter(7, Types.VARCHAR);
			callableStatement.registerOutParameter(8, Types.VARCHAR);
			
			intStatus = callableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			LogUtil.logMessage("intStatus .." + intStatus);
			intLSDBErrorID = (int) callableStatement.getInt(6);
			strOracleCode = (String) callableStatement.getString(7);
			strErrorMessage = (String) callableStatement.getString(8);
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Error ID:" + intLSDBErrorID);
				LogUtil.logMessage("strOracleCode:" + strOracleCode);
				
			}
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Enters into Error Loop:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				
				objErrorInfo.setMessageID(String.valueOf(intLSDBErrorID));
				LogUtil.logMessage("Error message in ErrorInfo:"
						+ objErrorInfo.getMessageID());
				throw new DataAccessException(objErrorInfo);
				
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {// Un
				// handled
				// exception
				LogUtil.logMessage("enters into oracle error code block:"
						+ strOracleCode);
				ErrorInfo objErrorInfo = new ErrorInfo();
				StringBuffer strBuffer = new StringBuffer();
				strBuffer.append(strOracleCode + " ");
				strBuffer.append(strErrorMessage);
				objErrorInfo.setMessage(strBuffer.toString());
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
		}
		
		catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException block in ModelSubSectionDAO.insertSubSection");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into catch block in ModelSubSectionDAO.insertSubSection"
					+ objErrorInfo.getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException ModelSubSectionDAO.insertSubSection");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into catch block in ModelSubSectionDAO.insertSubSection"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception ModelSubSectionDAO.insertSubSection");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(null, callableStatement,
						objConnnection);
			} catch (SQLException sqlex) {
				LogUtil
				.logMessage("Enters into Exception ModelSubSectionDAO.insertSubSection");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + sqlex.getMessage());
				throw new ApplicationException(sqlex, objErrorInfo);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception ModelSubSectionDAO.insertSubSection");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return intStatus;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSubSecMaintVO
	 *            The object for Modify SubSections
	 * @return Boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int updateSubSection(SubSectionVO objSubSecMaintVO)
	throws EMDException {
		LogUtil.logMessage("Entering ModelSubSectionDAO.updateSubSection");
		Connection objConnnection = null;
		CallableStatement callableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		int intStatus = 0;
		String strLogUser = "";
		try {
			strLogUser = objSubSecMaintVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in DAO :" + objConnnection);
			
			LogUtil.logMessage("Before Calling Procedure");
			callableStatement = objConnnection
			.prepareCall(EMDQueries.SP_UPDATE_MDL_SUBSECTION);
			callableStatement.setInt(1, objSubSecMaintVO.getModelSeqNo());
			callableStatement.setInt(2, objSubSecMaintVO.getSecSeqNo());
			callableStatement.setInt(3, objSubSecMaintVO.getSubSecSeqNo());
			callableStatement.setString(4, objSubSecMaintVO.getSubSecName());
			callableStatement.setString(5, objSubSecMaintVO.getSubSecDesc());
			callableStatement.setString(6, objSubSecMaintVO.getUserID());
			callableStatement.registerOutParameter(7, Types.INTEGER);
			callableStatement.registerOutParameter(8, Types.VARCHAR);
			callableStatement.registerOutParameter(9, Types.VARCHAR);
			
			intStatus = callableStatement.executeUpdate();
			
			if (intStatus > 0) {
				
				intStatus = 0;
			}
			
			intLSDBErrorID = (int) callableStatement.getInt(7);
			strOracleCode = (String) callableStatement.getString(8);
			strErrorMessage = (String) callableStatement.getString(9);
			
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Enters into Error Loop:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessageID(String.valueOf(intLSDBErrorID));
				throw new DataAccessException(objErrorInfo);
				
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {// Un
				// handled
				// exception
				LogUtil.logMessage("enters into oracle error code block:"
						+ strOracleCode);
				ErrorInfo objErrorInfo = new ErrorInfo();
				StringBuffer strBuffer = new StringBuffer();
				strBuffer.append(strOracleCode + " ");
				strBuffer.append(strErrorMessage);
				objErrorInfo.setMessage(strBuffer.toString());
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
		} catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException block in ModelSubSectionDAO:updateSubSection");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into catch block in ModelSubSectionDAO:insertSubSection"
					+ objErrorInfo.getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException ModelSubSectionDAO:updateSubSection");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into catch block in ModelSubSectionDAO:updateSubSection"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception ModelSubSectionDAO:updateSubSection");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(null, callableStatement,
						objConnnection);
			} catch (SQLException sqlex) {
				LogUtil
				.logMessage("Enters into Exception ModelSubSectionDAO:updateSubSection");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + sqlex.getMessage());
				throw new ApplicationException(sqlex, objErrorInfo);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception ModelSubSectionDAO:updateSubSection");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return intStatus;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSubSecMaintVO
	 *            The object for delte SubSections Added for CR-4
	 * @return Boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static synchronized int deleteSubSection(
			SubSectionVO objSubSecMaintVO) throws EMDException {
		LogUtil.logMessage("Entering ModelSubSectionDAO.deleteSubSection");
		Connection objConnnection = null;
		CallableStatement callableStatement = null;
		
		int intStatus = 0;
		int intLSDBErrorID = 0;
		
		String strOracleCode = null;
		String strErrorMessage = null;
		String strLogUser = "";
		try {
			strLogUser = objSubSecMaintVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("Connection Established :" + objConnnection);
			
			callableStatement = objConnnection
			.prepareCall(EMDQueries.SP_DELETE_MDL_SUBSECTION);
			
			callableStatement.setInt(1, objSubSecMaintVO.getModelSeqNo());
			
			callableStatement.setInt(2, objSubSecMaintVO.getSecSeqNo());
			
			callableStatement.setInt(3, objSubSecMaintVO.getSubSecSeqNo());
			
			callableStatement.setString(4, objSubSecMaintVO.getUserID());
			
			callableStatement.registerOutParameter(5, Types.INTEGER);
			
			callableStatement.registerOutParameter(6, Types.VARCHAR);
			
			callableStatement.registerOutParameter(7, Types.VARCHAR);
			
			intStatus = callableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			LogUtil.logMessage("intStatus .." + intStatus);
			intLSDBErrorID = (int) callableStatement.getInt(5);
			strOracleCode = (String) callableStatement.getString(6);
			strErrorMessage = (String) callableStatement.getString(7);
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Error ID:" + intLSDBErrorID);
				LogUtil.logMessage("strOracleCode:" + strOracleCode);
				
			}
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Enters into Error Loop:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				
				objErrorInfo.setMessageID(String.valueOf(intLSDBErrorID));
				LogUtil.logMessage("Error message in ErrorInfo:"
						+ objErrorInfo.getMessageID());
				throw new DataAccessException(objErrorInfo);
				
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {// Un
				// handled
				// exception
				LogUtil.logMessage("enters into oracle error code block:"
						+ strOracleCode);
				ErrorInfo objErrorInfo = new ErrorInfo();
				StringBuffer strBuffer = new StringBuffer();
				strBuffer.append(strOracleCode + " ");
				strBuffer.append(strErrorMessage);
				objErrorInfo.setMessage(strBuffer.toString());
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
		}
		
		catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException block in ModelSubSectionDAO.deleteSubSection");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into catch block in ModelSubSectionDAO.deleteSubSection"
					+ objErrorInfo.getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException ModelSubSectionDAO.deleteSubSection");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into catch block in ModelSubSectionDAO.deleteSubSection"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception ModelSubSectionDAO.deleteSubSection");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(null, callableStatement,
						objConnnection);
			} catch (SQLException sqlex) {
				LogUtil
				.logMessage("Enters into Exception ModelSubSectionDAO.deleteSubSection");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + sqlex.getMessage());
				throw new ApplicationException(sqlex, objErrorInfo);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception ModelSubSectionDAO.deleteSubSection");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return intStatus;
		
	}
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSubSecMaintVO
	 *            The object for View  SubSection Added for CR-92
	 * @return Boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList fetchSubSectionDetails(SubSectionVO objSubSectionVO)
	throws EMDException {
		
		LogUtil
		.logMessage("Enters into OrderSectionDAO:fetchSubSectionDetails");
		
		Connection objConnection = null;
		PreparedStatement objPreparedStatement = null;
		ResultSet objResultSet = null;
		ArrayList arlSubSecList = new ArrayList();
		String strLogUser = "";
		
		try {
			strLogUser = objSubSectionVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			LogUtil.logMessage("Enter into OrderSectionDAO Connection:"
					+ objConnection);
			int intSubSecSeqNo = objSubSectionVO.getSubSecSeqNo();
			objPreparedStatement = objConnection
			.prepareStatement(EMDQueries.Query_Fetch_Model_Section);
			objPreparedStatement.setInt(1, intSubSecSeqNo);
			objResultSet = objPreparedStatement.executeQuery();
			
			while (objResultSet.next()) {
				objSubSectionVO.setSecName(objResultSet
						.getString(DatabaseConstants.LS201_SEC_NAME));
				objSubSectionVO.setSubSecName(objResultSet
						.getString(DatabaseConstants.LS202_SUBSEC_NAME));
				arlSubSecList.add(objSubSectionVO);
			}
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
				
				if (objResultSet != null) {
					objResultSet.close();
				}
				
				DBHelper.closeConnection(objConnection);
			} catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception exception...");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return arlSubSecList;
	}
	
	
}
