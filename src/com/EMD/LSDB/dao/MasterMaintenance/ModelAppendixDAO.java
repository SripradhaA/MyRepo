package com.EMD.LSDB.dao.MasterMaintenance;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;

import oracle.jdbc.driver.OracleTypes;

import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.constant.DatabaseConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.dao.common.DBHelper;
import com.EMD.LSDB.dao.common.EMDDAO;
import com.EMD.LSDB.dao.common.EMDQueries;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.ModelAppendixVO;

public class ModelAppendixDAO extends EMDDAO {
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objGenArrangeVO
	 *            The Object for Search Performance Curve Images
	 * @return boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList fetchAppendixImages(
			ModelAppendixVO objModelAppendixVO) throws EMDException {
		LogUtil.logMessage("Entering ModelAppendixDAO:fetchAppendixImages");
		
		Connection objConnection = null;
		ArrayList arlImageList = new ArrayList();
		ResultSet objResultSet = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		CallableStatement objCallableStatement = null;
		int intLSDBErrorID = 0;
		String strMessage = null;
		String strLogUser = "";
		ArrayList arlMappedClause;//Added for CR_92
		try {
			strLogUser = objModelAppendixVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_SELECT_MDL_APNDX_IMG);
			objCallableStatement.setInt(1, objModelAppendixVO.getModelSeqNo());
			objCallableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			objCallableStatement.setString(3, objModelAppendixVO.getUserID());
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.execute();
			
			objResultSet = (ResultSet) objCallableStatement.getObject(2);
			LogUtil.logMessage(objResultSet);
			
			intLSDBErrorID = objCallableStatement.getInt(4);
			strOracleCode = objCallableStatement.getString(5);
			strErrorMessage = objCallableStatement.getString(6);
			
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("LSDBErrorID!=0");
				ErrorInfo objErrorInfo = new ErrorInfo();
				
				strMessage = (String.valueOf(intLSDBErrorID));
				objErrorInfo.setMessageID(strMessage);
				
				throw new DataAccessException(objErrorInfo);
			}
			if (strOracleCode != null && !"0".equals(strOracleCode)) {
				LogUtil.logMessage("Enters into oracle error code block:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				
				StringBuffer sb = new StringBuffer();
				sb.append(strOracleCode + " ");
				sb.append(strErrorMessage);
				objErrorInfo.setMessage(sb.toString());
				throw new ApplicationException(objErrorInfo);
			}
			
			while (objResultSet.next()) {
				
				objModelAppendixVO = new ModelAppendixVO();
				objModelAppendixVO.setModelSeqNo(objResultSet
						.getInt(DatabaseConstants.LS200_MDL_SEQ_NO));
				objModelAppendixVO.setImageName(objResultSet
						.getString(DatabaseConstants.LS209_IMG_NAME));
				objModelAppendixVO.setImageDesc(objResultSet
						.getString(DatabaseConstants.LS209_IMG_DESC));
				objModelAppendixVO
				.getFileVO()
				.setImageSeqNo(
						objResultSet
						.getInt(DatabaseConstants.LS170_IMG_SEQ_NO));
				objModelAppendixVO.setImgSeqNo(objResultSet
						.getInt(DatabaseConstants.LS170_IMG_SEQ_NO));
				/*  Added for CR_97 */
				objModelAppendixVO.getFileVO().setContentType(
						objResultSet.getString(DatabaseConstants.LS170_IMG_CONTENT_TYPE));
				/* CR_97 Ends here */
				//arlImageList.add(objModelAppendixVO);
				//Added for CR_92 starts here
				ResultSet objClauseSet = (ResultSet) objResultSet
				.getObject("MAPPED_CLAUSES");
				arlMappedClause = new ArrayList();
				while (objClauseSet.next()) {
					ClauseVO objClauseVO = new ClauseVO();
					String strClauseSeqNo = "";
					String strVersionNo = "";
					String strImageSeqno = "";
					String strViewMap = "";
					strClauseSeqNo = String.valueOf(objClauseSet
							.getInt(DatabaseConstants.LS300_CLA_SEQ_NO));
					strVersionNo = String.valueOf(objClauseSet
							.getInt(DatabaseConstants.LS301_VERSION_NO));
					strImageSeqno = String.valueOf(objClauseSet
							.getInt(DatabaseConstants.LS170_IMG_SEQ_NO));
					strViewMap = strClauseSeqNo + "~" + strVersionNo + "~"
					+ strImageSeqno;
					LogUtil.logMessage("Map Number in ModelAppendixDAO:fetchAppendixImages"
							+ strViewMap);
					objClauseVO.setModelSeqNo(objClauseSet
							.getInt(DatabaseConstants.LS200_MDL_SEQ_NO));
					objClauseVO.setClauseSeqNo(objClauseSet
							.getInt(DatabaseConstants.LS300_CLA_SEQ_NO));
					objClauseVO.setVersionNo(objClauseSet
							.getInt(DatabaseConstants.LS301_VERSION_NO));
					objClauseVO.setSectionSeqNo(objClauseSet
							.getInt(DatabaseConstants.LS201_SEC_SEQ_NO));
					objClauseVO.setSubSectionSeqNo(objClauseSet
							.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO));
					objClauseVO.setImageMapNo(strViewMap);
					arlMappedClause.add(objClauseVO);
				}
				LogUtil
				.logMessage("Size of ClauseVO:"
						+ arlMappedClause.size());
				objModelAppendixVO.setMapClauses(arlMappedClause);
				DBHelper.closeSQLObjects(objClauseSet, null, null);
				arlImageList.add(objModelAppendixVO);
				
			}
			
		}
		
		catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException exception in ModelAppendixDAO:fetchAppendixImages");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into catch block in ModelAppendixDAO:fetchAppendixImages"
					+ objErrorInfo.getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException exception in ModelAppendixDAO:fetchAppendixImages");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into catch block in ModelAppendixDAO:fetchAppendixImages"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception exception in ModelAppendixDAO:fetchAppendixImages");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(objResultSet, objCallableStatement,
						objConnection);
			}
			
			catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception exception in ModelAppendixDAO:fetchAppendixImages");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp);
			}
			
		}
		
		return arlImageList;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objGenArrangeVO
	 *            The Object used for Upload Appendix Image
	 * @return boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int uploadAppendixImage(ModelAppendixVO objModelAppendixVO)
	throws EMDException {
		
		LogUtil.logMessage("Entering ModelAppendixDAO:uploadAppendixImage");
		
		Connection objConnection = null;
		CallableStatement callableStatement = null;
		
		ArrayList arlImagedetlsList = new ArrayList();
		
		int intInserted;
		int intStatus = 0;
		int intLSDBErrorID = 0;
		
		StringBuffer strBuffer = null;
		String strLogUser = "";
		
		String strOracleCode = null;
		String strErrorMessage = null;
		
		try {
			
			strLogUser = objModelAppendixVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			
			int intFileSize = objModelAppendixVO.getFileVO().getFileLength();
			LogUtil.logMessage("FileSize in DAO:" + intFileSize);
			
			String strContentType = objModelAppendixVO.getFileVO()
			.getContentType();
			
			int intNextSeqNo = DBHelper.getSequenceNumber(objConnection,
					DatabaseConstants.LS170_IMG_SEQ_Name);
			Timestamp objSqlDate = ApplicationUtil.getCurrentTimeStamp();
			
			// For insert into images table - starts here
			LogUtil
			.logMessage("Enters into insert Empty Image Block of ModelAppendixDAO:uploadAppendixImage");
			arlImagedetlsList.add(new Integer(intNextSeqNo));
			arlImagedetlsList.add(strContentType);
			arlImagedetlsList.add(objModelAppendixVO.getUserID());
			arlImagedetlsList.add(objSqlDate);
			
			intInserted = DBHelper.executeUpdate(objConnection,
					EMDQueries.Query_EmptyImage, arlImagedetlsList);
			
			if (intInserted == 0) {
				strOracleCode = "Error in Image Insert";
			}
			LogUtil.logMessage("Insert Status of empty Image" + intInserted);
			
			LogUtil
			.logMessage("Enters into Update Image Block of ModelAppendixDAO:uploadAppendixImage");
			
			strBuffer = new StringBuffer();
			strBuffer.append(EMDQueries.Query_UpdateImage + intNextSeqNo);
			String strUpdatequery = strBuffer.toString();
			boolean blnUpdated = DBHelper.executeDatabaseUpdateUpload(
					objConnection, strUpdatequery, objModelAppendixVO
					.getFileVO());
			
			if (blnUpdated != true) {
				
				strOracleCode = "Error in Image Update";
				
			}
			
			LogUtil
			.logMessage("Leaves from Insert ImgSeqNo Block of ModelAppendixDAO:uploadAppendixImage"
					+ blnUpdated);
			
			// For inserting into images table - Ends here
			
			callableStatement = objConnection
			.prepareCall(EMDQueries.SP_INSERT_MDL_APNDX_IMG);
			
			callableStatement.setInt(1, objModelAppendixVO.getModelSeqNo());
			
			callableStatement.setInt(2, intNextSeqNo);
			
			callableStatement.setString(3, objModelAppendixVO.getImageName());
			
			if (!"".equals(objModelAppendixVO.getImageDesc())
					&& objModelAppendixVO.getImageDesc() != null) {
				
				callableStatement.setString(4, objModelAppendixVO
						.getImageDesc());
				
			} else {
				
				callableStatement.setNull(4, Types.NULL);
			}
			
			callableStatement.setString(5, objModelAppendixVO.getUserID());
			
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
				StringBuffer strBuffer1 = new StringBuffer();
				strBuffer1.append(strOracleCode + " ");
				strBuffer1.append(strErrorMessage);
				objErrorInfo.setMessage(strBuffer1.toString());
				objConnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
		}
		
		catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException block in ModelAppendixDAO:uploadAppendixImage");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into catch block in ModelAppendixDAO:deleteAppendixImage"
					+ objErrorInfo.getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException ModelAppendixDAO:uploadAppendixImage");
			objAppExp.printStackTrace();
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
			.logMessage("Enters into Exception ModelAppendixDAO:uploadAppendixImage");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper
				.closeSQLObjects(null, callableStatement, objConnection);
			} catch (SQLException sqlex) {
				LogUtil
				.logMessage("Enters into Exception ModelAppendixDAO:uploadAppendixImage");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + sqlex.getMessage());
				throw new ApplicationException(sqlex, objErrorInfo);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception ModelAppendixDAO:uploadAppendixImage");
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
	 * @param objGenArrangeVO
	 *            The Object used for Delete Appendix Image
	 * @return boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int deleteAppendixImage(ModelAppendixVO objModelAppendixVO)
	throws EMDException {
		
		LogUtil.logMessage("Entering ModelAppendixDAO:deleteAppendixImage");
		
		Connection objConnnection = null;
		CallableStatement callableStatement = null;
		
		int intStatus = 0;
		int intLSDBErrorID = 0;
		
		String strOracleCode = null;
		String strErrorMessage = null;
		String strLogUser = "";
		try {
			strLogUser = objModelAppendixVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("Connection Established :" + objConnnection);
			
			callableStatement = objConnnection
			.prepareCall(EMDQueries.SP_DELETE_MDL_APNDX_IMG);
			
			callableStatement.setInt(1, objModelAppendixVO.getModelSeqNo());
			
			callableStatement.setInt(2, objModelAppendixVO.getImgSeqNo());
			
			callableStatement.setString(3, objModelAppendixVO.getUserID());
			
			callableStatement.registerOutParameter(4, Types.INTEGER);
			
			callableStatement.registerOutParameter(5, Types.VARCHAR);
			
			callableStatement.registerOutParameter(6, Types.VARCHAR);
			
			intStatus = callableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			LogUtil.logMessage("intStatus .." + intStatus);
			intLSDBErrorID = (int) callableStatement.getInt(4);
			strOracleCode = (String) callableStatement.getString(5);
			strErrorMessage = (String) callableStatement.getString(6);
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
			.logMessage("Enters into DataAccessException block in ModelAppendixDAO:deleteAppendixImage");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into catch block in ModelAppendixDAO:deleteAppendixImage"
					+ objErrorInfo.getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException ModelAppendixDAO:deleteAppendixImage");
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
			.logMessage("Enters into Exception ModelAppendixDAO:deleteAppendixImage");
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
				.logMessage("Enters into Exception ModelAppendixDAO:deleteAppendixImage");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + sqlex.getMessage());
				throw new ApplicationException(sqlex, objErrorInfo);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception ModelAppendixDAO:deleteAppendixImage");
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
	 * @param objGenArrangeVO
	 *            The Object used for update Appendix Image Name/Description
	 * @return boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int updateAppendixImage(ModelAppendixVO objModelAppendixVO)
	throws EMDException {
		
		LogUtil.logMessage("Entering ModelAppendixDAO:updateAppendixImage");
		
		Connection objConnnection = null;
		CallableStatement callableStatement = null;
		
		int intStatus = 0;
		int intLSDBErrorID = 0;
		
		String strOracleCode = null;
		String strErrorMessage = null;
		String strLogUser = "";
		try {
			strLogUser = objModelAppendixVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("Connection Established :" + objConnnection);
			
			callableStatement = objConnnection
			.prepareCall(EMDQueries.SP_UPDATE_MDL_APNDX_IMG);
			
			callableStatement.setInt(1, objModelAppendixVO.getModelSeqNo());
			
			callableStatement.setInt(2, objModelAppendixVO.getImgSeqNo());
			
			LogUtil.logMessage("Image Name in DAO"
					+ objModelAppendixVO.getImageName());
			LogUtil.logMessage("Image Desc in DAO"
					+ objModelAppendixVO.getImageDesc());
			
			callableStatement.setString(3, objModelAppendixVO.getImageName());
			
			if (!"".equals(objModelAppendixVO.getImageDesc())
					&& objModelAppendixVO.getImageDesc() != null) {
				
				callableStatement.setString(4, objModelAppendixVO
						.getImageDesc());
				
			} else {
				
				callableStatement.setNull(4, Types.NULL);
			}
			
			callableStatement.setString(5, objModelAppendixVO.getUserID());
			
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
			.logMessage("Enters into DataAccessException block in ModelAppendixDAO:updateAppendixImage");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into catch block in ModelAppendixDAO:updateAppendixImage"
					+ objErrorInfo.getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException ModelAppendixDAO:updateAppendixImage");
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
			.logMessage("Enters into Exception ModelAppendixDAO:updateAppendixImage");
			objExp.printStackTrace();
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
				.logMessage("Enters into Exception ModelAppendixDAO:updateAppendixImage");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + sqlex.getMessage());
				throw new ApplicationException(sqlex, objErrorInfo);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception ModelAppendixDAO:updateAppendixImage");
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
	 * @param objGenArrangeVO
	 *            The Object used for update Appendix Image Name/Description
	 * @return boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int saveModelClaMappings(ModelAppendixVO objModelAppendixVO)
	throws EMDException {
		
		LogUtil.logMessage("Entering ModelAppendixDAO:saveModelClaMappings");
		
		Connection objConnnection = null;
		CallableStatement callableStatement = null;
		
		int intStatus = 0;
		int intLSDBErrorID = 0;
		
		String strOracleCode = null;
		String strErrorMessage = null;
		String strLogUser = "";
		try {
			strLogUser = objModelAppendixVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("Connection Established :" + objConnnection);
			
			callableStatement = objConnnection
			.prepareCall(EMDQueries.SP_MAP_MDL_CLA_APNDX_IMG);
			
			callableStatement.setInt(1, objModelAppendixVO.getModelSeqNo());
			callableStatement.setInt(2, objModelAppendixVO.getImgSeqNo());
			callableStatement.setInt(3,objModelAppendixVO.getClauseSeqNo());
			callableStatement.setInt(4,objModelAppendixVO.getVersionNo());
			callableStatement.setString(5, objModelAppendixVO.getUserID());
						
			callableStatement.registerOutParameter(6, Types.INTEGER);
			callableStatement.registerOutParameter(7, Types.VARCHAR);
			callableStatement.registerOutParameter(8, Types.VARCHAR);
			
			intStatus = callableStatement.executeUpdate();
			
			
			if (intStatus > 0) {
				intStatus = 0;
				LogUtil.logMessage("intStatus before reset to 0===" + intStatus);
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
			.logMessage("Enters into DataAccessException block in ModelAppendixDAO:saveMappings");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into catch block in ModelAppendixDAO:saveMappings"
					+ objErrorInfo.getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException ModelAppendixDAO:saveMappings");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into catch block in ModelSubSectionDAO.saveMappings"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception ModelAppendixDAO:saveMappings");
			objExp.printStackTrace();
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
				.logMessage("Enters into Exception ModelAppendixDAO:saveMappings");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + sqlex.getMessage());
				throw new ApplicationException(sqlex, objErrorInfo);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception ModelAppendixDAO:saveMappings");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return intStatus;
		
	}
	
}
