/**
 * 
 */
package com.EMD.LSDB.dao.MasterMaintenance;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
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
import com.EMD.LSDB.vo.common.SectionVO;

/**
 * @author PS57222
 * 
 */
public class SectionDAO extends EMDDAO {
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSectionVO
	 *            The object for fetch Section
	 * @return Arraylist It has Arraylist of SectionVO
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList fetchSections(SectionVO objSectionVO)
	throws EMDException {
		LogUtil.logMessage("Enter into SectionDAO:fetchSections");
		
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ArrayList objResultList = new ArrayList();
		ResultSet objresultSet = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strSectionName = null;
		String strSectionCode = null;
		String strSectionDisplay = null;
		int intLSDBErrorID = 0;
		String strMessage = null;
		String strLogUser = "";
		try {
			strLogUser = objSectionVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			LogUtil.logMessage("Enter into SectionDAO Connection:"
					+ objConnection);
			LogUtil.logMessage("ModelSequenceNO:"
					+ objSectionVO.getModelSeqNo());
			
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_SELECT_MDL_SECTION);
			objCallableStatement.setInt(1, objSectionVO.getModelSeqNo());
			objCallableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			objCallableStatement.setString(3, objSectionVO.getUserID());
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.execute();
			
			objresultSet = (ResultSet) objCallableStatement.getObject(2);
			
			intLSDBErrorID = objCallableStatement.getInt(4);
			
			strOracleCode = objCallableStatement.getString(5);
			LogUtil.logMessage("OracleCode" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(6);
			
			LogUtil.logMessage("ErrorMessage" + strErrorMessage);
			
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
			
			while (objresultSet.next()) {
				LogUtil.logMessage("Enters into ResultSet Loop");
				strSectionCode = objresultSet
				.getString(DatabaseConstants.LS201_SEC_CODE);
				strSectionName = objresultSet
				.getString(DatabaseConstants.LS201_SEC_NAME);
				strSectionDisplay = strSectionCode + "." + strSectionName;
				objSectionVO = new SectionVO();
				objSectionVO.setSectionSeqNo(objresultSet
						.getInt(DatabaseConstants.LS201_SEC_SEQ_NO));
				LogUtil.logMessage("Enters into ResultSet Loop111");
				objSectionVO.setSectionCode(objresultSet
						.getString(DatabaseConstants.LS201_SEC_CODE));
				LogUtil.logMessage("Enters into ResultSet Loop222");
				if (objresultSet.getString(DatabaseConstants.LS201_SEC_DESC) != null) {
					objSectionVO.setSectionComments(objresultSet.getString(
							DatabaseConstants.LS201_SEC_DESC).trim());
				}
				objSectionVO.setSectionName(objresultSet
						.getString(DatabaseConstants.LS201_SEC_NAME));
				objSectionVO.setModelSeqNo(objresultSet
						.getInt(DatabaseConstants.LS200_MDL_SEQ_NO));
				objSectionVO.setSectionDisplay(strSectionDisplay);
				LogUtil.logMessage("SectionCode:"
						+ objSectionVO.getSectionCode());
				LogUtil.logMessage("SectionName"
						+ objSectionVO.getSectionName());
				LogUtil.logMessage("SectionComments"
						+ objSectionVO.getSectionComments());
				
				LogUtil.logMessage("Leaves From ResultSet Loop");
				objResultList.add(objSectionVO);
				
			}
		}
		
		catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException SectionDAO:fetchSections");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in SectionDAO:fetchSections"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException SectionDAO:fetchSections");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in SectionDAO:fetchSections"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception SectionDAO:fetchSections");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(objresultSet, objCallableStatement,
						objConnection);
			}
			
			catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception SectionDAO:fetchSections");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		LogUtil.logMessage("Leaves from SectionDAO ");
		LogUtil.logMessage("ArrayList value in addDAO:" + objResultList);
		return objResultList;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSectionVO
	 *            The Object for Insert Section
	 * @return boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static synchronized int insertSection(SectionVO objSectionVO)
	throws EMDException {
		LogUtil.logMessage("Enters into SectionDAO:insertSection");
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
			
			strLogUser = objSectionVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_INSERT_MDL_SECTION);
			objCallableStatement.setString(1, objSectionVO.getSectionName());
			LogUtil.logMessage(objSectionVO.getSectionName());
			
			objCallableStatement
			.setString(2, objSectionVO.getSectionComments());
			LogUtil.logMessage(objSectionVO.getSectionComments());
			objCallableStatement.setInt(3, objSectionVO.getModelSeqNo());
			LogUtil.logMessage("modelSequenceNo:"
					+ objSectionVO.getModelSeqNo());
			
			objCallableStatement.setString(4, objSectionVO.getUserID());
			
			LogUtil.logMessage("UserId in addSection DAo:"
					+ objSectionVO.getUserID());
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			intStatusCode = objCallableStatement.executeUpdate();
			LogUtil.logMessage("REsult:" + intStatusCode);
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
			.logMessage("Enters into DataAccessException SectionDAO:insertSection");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in SectionDAO:insertSection"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException SectionDAO:insertSection");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in SectionDAO:insertSection"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception SectionDAO:insertSection");
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
				.logMessage("Enters into Exception SectionDAO:insertSection");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		LogUtil.logMessage("Inside InsertSection Method" + isInserted);
		return intStatusCode;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSectionVO
	 *            The Object for Update Section
	 * @return boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int updateSection(SectionVO objSectionVO) throws EMDException {
		LogUtil.logMessage("Enters into SectionDAO:updateSection");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intStatusCode;
		int intLSDBErrorID = 0;
		String strMessage = null;
		String strLogUser = "";
		try {
			strLogUser = objSectionVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_UPDATE_MDL_SECTION);
			objCallableStatement.setInt(1, objSectionVO.getSectionSeqNo());
			LogUtil.logMessage("SectionSeqnO in updateSectionDAo:"
					+ objSectionVO.getSectionSeqNo());
			objCallableStatement.setString(2, objSectionVO.getSectionName());
			LogUtil.logMessage("SectionName in updateSectionDAo:"
					+ objSectionVO.getSectionName());
			objCallableStatement
			.setString(3, objSectionVO.getSectionComments());
			LogUtil.logMessage("SectionComments in updateSectionDAo:"
					+ objSectionVO.getSectionComments());
			objCallableStatement.setInt(4, objSectionVO.getModelSeqNo());
			LogUtil.logMessage("ModelSeqNo in updateSectionDAo:"
					+ objSectionVO.getModelSeqNo());
			objCallableStatement.setString(5, objSectionVO.getUserID());
			LogUtil.logMessage("UserID in updateSectionDAo:"
					+ objSectionVO.getUserID());
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			LogUtil.logMessage("Update Result:1111111");
			intStatusCode = objCallableStatement.executeUpdate();
			LogUtil.logMessage("Update Result:" + intStatusCode);
			if (intStatusCode > 0) {
				intStatusCode = 0;
			}
			
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
			.logMessage("Enters into DataAccessException SectionDAO:updateSection");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in SectionDAO:updateSection"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException SectionDAO:updateSection");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in SectionDAO:updateSection"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception SectionDAO:updateSection");
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
				.logMessage("Enters into Exception SectionDAO:updateSection");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		LogUtil.logMessage("Exits from DAO");
		return intStatusCode;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSectionVO
	 *            The Object for Delete Section Added for CR-4
	 * @return boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static synchronized int deleteSection(SectionVO objSectionVO)
	throws EMDException {
		LogUtil.logMessage("Enters into SectionDAO:deleteSection");
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
			
			strLogUser = objSectionVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_DELETE_MDL_SECTION);
			
			objCallableStatement.setInt(1, objSectionVO.getSectionSeqNo());
			objCallableStatement.setInt(2, objSectionVO.getModelSeqNo());
			objCallableStatement.setString(3, objSectionVO.getUserID());
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			
			intStatusCode = objCallableStatement.executeUpdate();
			
			LogUtil.logMessage("modelSequenceNo:"
					+ objSectionVO.getModelSeqNo());
			LogUtil.logMessage("SectionSequenceNo:"
					+ objSectionVO.getSectionSeqNo());
			LogUtil.logMessage("UserId in addSection DAo:"
					+ objSectionVO.getUserID());
			
			LogUtil.logMessage("REsult:" + intStatusCode);
			if (intStatusCode > 0) {
				intStatusCode = 0;
			}
			
			intLSDBErrorID = objCallableStatement.getInt(4);
			LogUtil.logMessage("Error ID:" + intLSDBErrorID);
			
			strOracleCode = objCallableStatement.getString(5);
			LogUtil.logMessage("Oracle code:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(6);
			
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
			.logMessage("Enters into DataAccessException SectionDAO:deleteSection");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in SectionDAO:deleteSection"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException SectionDAO:deleteSection");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in SectionDAO:deleteSection"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception SectionDAO:deleteSection");
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
				.logMessage("Enters into Exception SectionDAO:deleteSection");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		LogUtil.logMessage("Inside delete Section Method" + isInserted);
		
		return intStatusCode;
	}
	
}
