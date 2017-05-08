package com.EMD.LSDB.dao.Suggestions;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
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
import com.EMD.LSDB.dao.common.AdmnQueries;
import com.EMD.LSDB.dao.common.DBHelper;
import com.EMD.LSDB.dao.common.EMDDAO;
import com.EMD.LSDB.dao.common.EMDQueries;
import com.EMD.LSDB.vo.admn.UserVO;
import com.EMD.LSDB.vo.common.SuggestVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business methods for the History Edl Pop up
 ******************************************************************************/
public class SuggestDAO extends EMDDAO {
	
	/***************************************************************************
	 * @author Mahindra Satyam
	 * @version 1.0
	 * @param objSuggestVO
	 *            the object that has details of the Suggestions
	 *            The Method submitSuggestion is used to submit the User
	 *            Suggestions
	 * @return int 
	 * @throws EMDException
	 **************************************************************************/
		
	public static int submitSuggestion(SuggestVO objSuggestVO) throws EMDException {
		LogUtil.logMessage("Entering SuggestDAO.submitSuggestion");
		Connection objConnnection = null;
		CallableStatement callableStatement = null;
		ArrayList arlImagedetlsList = new ArrayList();
		int intInserted = 0;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intNextSeqNo = 0;
		int intLSDBErrorID = 0;
		int intStatus = 0;
		String strLogUser = "";
		StringBuffer strBuffer = null;
		try {
			strLogUser = objSuggestVO.getUserID();
			objConnnection = DBHelper.prepareConnection();;
			
			int intFileSize = objSuggestVO.getFileVO().getFileLength();
			LogUtil.logMessage("FileSize in DAO:" + intFileSize);
			
			if (intFileSize != 0)	{
				String strContentType = objSuggestVO.getFileVO().getContentType();
				
				intNextSeqNo = DBHelper.getSequenceNumber(objConnnection,
						DatabaseConstants.LS170_IMG_SEQ_Name);
				Timestamp objSqlDate = ApplicationUtil.getCurrentTimeStamp();
				
				// For insert into images table - starts here
				LogUtil
				.logMessage("Enters into insert Empty Image Block of SuggestDAO:submitSuggestion");
				arlImagedetlsList.add(new Integer(intNextSeqNo));
				arlImagedetlsList.add(strContentType);
				arlImagedetlsList.add(objSuggestVO.getUserID());
				arlImagedetlsList.add(objSqlDate);
				
				intInserted = DBHelper.executeUpdate(objConnnection,
						EMDQueries.Query_EmptyImage, arlImagedetlsList);
				
				if (intInserted == 0) {
					strOracleCode = "Error in Image Insert";
				}
				LogUtil.logMessage("Insert Status of empty Image" + intInserted);
				
				LogUtil
				.logMessage("Enters into Update Image Block of SuggestDAO:submitSuggestion");
				
				strBuffer = new StringBuffer();
				strBuffer.append(EMDQueries.Query_UpdateImage + intNextSeqNo);
				String strUpdatequery = strBuffer.toString();
				boolean blnUpdated = DBHelper.executeDatabaseUpdateUpload(
						objConnnection, strUpdatequery, objSuggestVO
						.getFileVO());
				
				if (blnUpdated != true) {
					strOracleCode = "Error in Image Update";
			}
			
			LogUtil
			.logMessage("Leaves from Insert ImgSeqNo Block of SuggestDAO:submitSuggestion"
					+ blnUpdated);
			
			// For inserting into images table - Ends here
			}
			
			callableStatement = objConnnection.prepareCall(EMDQueries.SP_INSERT_SUGGESTION);
			
			callableStatement.setString(1, objSuggestVO.getUserID());
			callableStatement.setString(2, objSuggestVO.getScreenName());
			callableStatement.setString(3, objSuggestVO.getSuggestions());
			
			if (intNextSeqNo != 0)	{
				callableStatement.setInt(4, intNextSeqNo);				
				callableStatement.setString(5, objSuggestVO.getFileVO().getFileName());				
				callableStatement.setString(6, objSuggestVO.getFileVO().getContentType());
			}
			else	{
				callableStatement.setNull(4, Types.NULL);
				callableStatement.setNull(5, Types.NULL);
				callableStatement.setNull(6, Types.NULL);
			}
			
			callableStatement.registerOutParameter(7, Types.INTEGER);
			callableStatement.registerOutParameter(8, Types.VARCHAR);
			callableStatement.registerOutParameter(9, Types.VARCHAR);
			intStatus = callableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			LogUtil
			.logMessage("Inside the submitSuggestion method of SuggestDAO :intStatus .."
					+ intStatus);
			
			intLSDBErrorID = (int) callableStatement.getInt(7);
			strOracleCode = (String) callableStatement.getString(8);
			strErrorMessage = (String) callableStatement.getString(9);
			
			LogUtil.logMessage("Error ID:" + intLSDBErrorID);
			
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
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into DataAccess Exception block in SuggestDAO:submitSuggestion"
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  SuggestDAO:submitSuggestion"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in SuggestDAO:submitSuggestion"
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
				.logMessage("Enters into Exception block in SuggestDAO:submitSuggestion"
						+ objExp.getMessage());
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return intStatus;		
	}

	/***************************************************************************
	 * @author Mahindra Satyam
	 * @version 1.0
	 * @param objSuggestVO
	 *            the object that has details of the Suggestions
	 *            The Method fetchSuggestion is used to fetch all the details
	 *            User submitted Suggestions
	 * @return ArrayList 
	 * @throws EMDException
	 **************************************************************************/
		
	public static ArrayList fetchSuggestions(SuggestVO objSuggestVO) throws EMDException {
		LogUtil.logMessage("Entering SuggestDAO.fetchSuggestions");
		Connection objConnnection = null;
		CallableStatement callableStatement = null;
		ResultSet rsSuggestions = null;
		ArrayList arlSuggestions = new ArrayList();
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		try {
			strLogUser = objSuggestVO.getUserID();
			objConnnection = DBHelper.prepareConnection();;
			callableStatement = objConnnection
			.prepareCall(EMDQueries.SP_SELECT_SUGGESTION);
			
			if (objSuggestVO.getSuggestStatusCode() != 0)
				callableStatement.setInt(1, objSuggestVO.getSuggestStatusCode());
			else
				callableStatement.setNull(1, Types.NULL);
			//Added for CR-127
			if (objSuggestVO.getSelectedInitiator() != null &&
					!objSuggestVO.getSelectedInitiator().equalsIgnoreCase("0"))
				callableStatement.setString(2, objSuggestVO.getSelectedInitiator());
			else
				callableStatement.setNull(2, Types.NULL);
			//Added for CR-127
			callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			callableStatement.setString(4, objSuggestVO.getUserID());
			callableStatement.registerOutParameter(5, Types.INTEGER);
			callableStatement.registerOutParameter(6, Types.VARCHAR);
			callableStatement.registerOutParameter(7, Types.VARCHAR);
			
			callableStatement.execute();
			
			rsSuggestions = (ResultSet) callableStatement.getObject(3);
			
			LogUtil
			.logMessage("Inside the fetchSuggestions method of SuggestDAO :resultSet .."
					+ rsSuggestions);
			
			intLSDBErrorID = (int) callableStatement.getInt(5);
			strOracleCode = (String) callableStatement.getString(6);
			strErrorMessage = (String) callableStatement.getString(7);
			
			LogUtil.logMessage("Error ID:" + intLSDBErrorID);
			
			if (intLSDBErrorID != 0) {
				
				LogUtil.logMessage("Enters into Error Loop:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessageID("" + intLSDBErrorID);
				LogUtil.logMessage("Error message in ErrorInfo:"
						+ objErrorInfo.getMessageID());
				throw new DataAccessException(objErrorInfo);
				
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {
				// Unhandled Exception				
				LogUtil.logMessage("strOracleCode:" + strOracleCode);
				ErrorInfo objErrorInfo = new ErrorInfo();
				StringBuffer sb = new StringBuffer();
				sb.append(strOracleCode + " ");
				sb.append(strErrorMessage);
				objErrorInfo.setMessage(sb.toString());
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
			while (rsSuggestions.next()) {
				
				objSuggestVO = new SuggestVO();
								
				objSuggestVO.setSuggestId(rsSuggestions.getInt(DatabaseConstants.LS601_SUGGESTION_ID));
				
				objSuggestVO.setSuggestStatusCode(rsSuggestions.getInt(DatabaseConstants.LS600_SUGG_STATUS_CODE));
								
				objSuggestVO.setScreenName(rsSuggestions.getString(DatabaseConstants.LS601_SCREEN_NAME));
				
				objSuggestVO.setSuggestions(rsSuggestions.getString(DatabaseConstants.LS601_SUGGESTION));
				
				objSuggestVO.setImgSeqNo(rsSuggestions.getInt(DatabaseConstants.LS170_IMG_SEQ_NO));
								
				objSuggestVO.setImageName(rsSuggestions.getString(DatabaseConstants.LS602_IMG_NAME));

				objSuggestVO.setSuggestUserId(rsSuggestions.getString(DatabaseConstants.LS601_SUGG_USER_ID));
				
				objSuggestVO.setSuggestDate(rsSuggestions.getString(DatabaseConstants.LS601_SUGG_DATE));
				
				objSuggestVO.setAdminComments(rsSuggestions.getString(DatabaseConstants.LS601_SUGG_MSTR_COMMENT));
				
				objSuggestVO.setAdminUserId(rsSuggestions.getString(DatabaseConstants.LS601_MSTR_UPDT_ID));
				
				objSuggestVO.setFirstName(rsSuggestions.getString(DatabaseConstants.LS010_FIRSTNAME));
				
				objSuggestVO.setLastName(rsSuggestions.getString(DatabaseConstants.LS010_LASTNAME));
				
				LogUtil.logMessage("Suggestion ID : " + objSuggestVO.getSuggestId());
				
				arlSuggestions.add(objSuggestVO);				
			}
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into DataAccess Exception block in SuggestDAO:fetchSuggestions"
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  SuggestDAO:fetchSuggestions"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in SuggestDAO:fetchSuggestions"
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
				.logMessage("Enters into Exception block in SuggestDAO:fetchSuggestions"
						+ objExp.getMessage());
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return arlSuggestions;		
	}

	/***************************************************************************
	 * @author Mahindra Satyam
	 * @version 1.0
	 * @param objSuggestVO
	 *            the object that has details of the Suggestions
	 *            The Method fetchSuggestionStatus is used to fetch all
	 *			  the Suggestion Status
	 * @return ArrayList 
	 * @throws EMDException
	 **************************************************************************/
		
	public static ArrayList fetchSuggestionStatus(SuggestVO objSuggestVO) throws EMDException {
		LogUtil.logMessage("Entering SuggestDAO.fetchSuggestionStatus");
		Connection objConnnection = null;
		CallableStatement callableStatement = null;
		ResultSet rsSuggStatus = null;
		ArrayList arlSuggStatus = new ArrayList();
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		try {
			strLogUser = objSuggestVO.getUserID();
			objConnnection = DBHelper.prepareConnection();;
			callableStatement = objConnnection
			.prepareCall(EMDQueries.SP_SELECT_SUGG_STATUS);
			
			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			callableStatement.setString(2, objSuggestVO.getUserID());
			callableStatement.registerOutParameter(3, Types.INTEGER);
			callableStatement.registerOutParameter(4, Types.VARCHAR);
			callableStatement.registerOutParameter(5, Types.VARCHAR);
			
			callableStatement.execute();
			
			rsSuggStatus = (ResultSet) callableStatement.getObject(1);
			
			LogUtil
			.logMessage("Inside the fetchSuggestions method of SuggestDAO :resultSet .."
					+ rsSuggStatus);
			
			intLSDBErrorID = (int) callableStatement.getInt(3);
			strOracleCode = (String) callableStatement.getString(4);
			strErrorMessage = (String) callableStatement.getString(5);
			
			LogUtil.logMessage("Error ID:" + intLSDBErrorID);
			
			if (intLSDBErrorID != 0) {
				
				LogUtil.logMessage("Enters into Error Loop:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessageID("" + intLSDBErrorID);
				LogUtil.logMessage("Error message in ErrorInfo:"
						+ objErrorInfo.getMessageID());
				throw new DataAccessException(objErrorInfo);
				
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {
				// Unhandled Exception				
				LogUtil.logMessage("strOracleCode:" + strOracleCode);
				ErrorInfo objErrorInfo = new ErrorInfo();
				StringBuffer sb = new StringBuffer();
				sb.append(strOracleCode + " ");
				sb.append(strErrorMessage);
				objErrorInfo.setMessage(sb.toString());
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
			while (rsSuggStatus.next()) {
				
				objSuggestVO = new SuggestVO();
												
				objSuggestVO.setSuggestStatusCode(rsSuggStatus.getInt(DatabaseConstants.LS600_SUGG_STATUS_CODE));
								
				objSuggestVO.setSuggestStatusDesc(rsSuggStatus.getString(DatabaseConstants.LS600_SUGG_STATUS_DESC));
				
				LogUtil.logMessage("Suggestion Code : " + objSuggestVO.getSuggestStatusCode());
				
				arlSuggStatus.add(objSuggestVO);				
			}
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into DataAccess Exception block in SuggestDAO:fetchSuggestionStatus"
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  SuggestDAO:fetchSuggestionStatus"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in SuggestDAO:fetchSuggestionStatus"
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
				.logMessage("Enters into Exception block in SuggestDAO:fetchSuggestions"
						+ objExp.getMessage());
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return arlSuggStatus;		
	}
	
	/***************************************************************************
	 * @author Mahindra Satyam
	 * @version 1.0
	 * @param objSuggestVO
	 *            the object that has details of the Suggestions
	 *            The Method updateSuggestion is used to update the User
	 *            submitted Suggestions by the Admin or Master Spec Engineer
	 * @return int 
	 * @throws EMDException
	 **************************************************************************/
		
	public static int updateSuggestion(SuggestVO objSuggestVO) throws EMDException {
		LogUtil.logMessage("Entering SuggestDAO.updateSuggestion");
		Connection objConnnection = null;
		CallableStatement callableStatement = null;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		int intStatus = 0;
		String strLogUser = "";
		try {
			strLogUser = objSuggestVO.getUserID();
			objConnnection = DBHelper.prepareConnection();;
			callableStatement = objConnnection
			.prepareCall(EMDQueries.SP_UPDATE_SUGGESTION);
			
			callableStatement.setInt(1, objSuggestVO.getSuggestId());
			callableStatement.setInt(2, objSuggestVO.getSuggestStatusCode());
			callableStatement.setString(3, objSuggestVO.getSuggestions());
			callableStatement.setString(4, objSuggestVO.getAdminComments());
			callableStatement.setString(5, objSuggestVO.getUserID());			
			callableStatement.registerOutParameter(6, Types.INTEGER);
			callableStatement.registerOutParameter(7, Types.VARCHAR);
			callableStatement.registerOutParameter(8, Types.VARCHAR);
			intStatus = callableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			LogUtil
			.logMessage("Inside the submitSuggestion method of SuggestDAO :intStatus .."
					+ intStatus);
			
			intLSDBErrorID = (int) callableStatement.getInt(6);
			strOracleCode = (String) callableStatement.getString(7);
			strErrorMessage = (String) callableStatement.getString(8);
			
			LogUtil.logMessage("Error ID:" + intLSDBErrorID);
			
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
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into DataAccess Exception block in SuggestDAO:updateSuggestion"
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  SuggestDAO:updateSuggestion"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in SuggestDAO:updateSuggestion"
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
				.logMessage("Enters into Exception block in SuggestDAO:updateSuggestion"
						+ objExp.getMessage());
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return intStatus;		
	}
	
	/***************************************************************************
	 * @author Mahindra Satyam
	 * @version 1.0
	 * @param objSuggestVO
	 *            the object that has details of the Suggestions
	 *            The Method deleteAttachment is used to delete the 
	 *            attachment tied to the Suggestion
	 * @return int 
	 * @throws EMDException
	 **************************************************************************/
		
	public static int deleteAttachment(SuggestVO objSuggestVO) throws EMDException {
		LogUtil.logMessage("Entering SuggestDAO.deleteAttachment");
		Connection objConnnection = null;
		CallableStatement callableStatement = null;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		int intStatus = 0;
		String strLogUser = "";
		try {
			strLogUser = objSuggestVO.getUserID();
			objConnnection = DBHelper.prepareConnection();;
			callableStatement = objConnnection
			.prepareCall(EMDQueries.SP_DELETE_ATTACHMENT);
			
			callableStatement.setInt(1, objSuggestVO.getSuggestId());
			callableStatement.setInt(2, objSuggestVO.getImgSeqNo());
			callableStatement.setString(3, objSuggestVO.getUserID());			
			callableStatement.registerOutParameter(4, Types.INTEGER);
			callableStatement.registerOutParameter(5, Types.VARCHAR);
			callableStatement.registerOutParameter(6, Types.VARCHAR);
			intStatus = callableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			LogUtil
			.logMessage("Inside the deleteAttachment method of SuggestDAO :intStatus .."
					+ intStatus);
			
			intLSDBErrorID = (int) callableStatement.getInt(4);
			strOracleCode = (String) callableStatement.getString(5);
			strErrorMessage = (String) callableStatement.getString(6);
			
			LogUtil.logMessage("Error ID:" + intLSDBErrorID);
			
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
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into DataAccess Exception block in SuggestDAO:deleteAttachment"
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  SuggestDAO:deleteAttachment"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in SuggestDAO:deleteAttachment"
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
				.logMessage("Enters into Exception block in SuggestDAO:deleteAttachment"
						+ objExp.getMessage());
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return intStatus;		
	}
	
	//Added for CR-127
	
	//Added for CR-127 Ends here
}