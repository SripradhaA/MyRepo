/**
 * 
 */
package com.EMD.LSDB.dao.MasterMaintenance;

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
import com.EMD.LSDB.dao.common.DBHelper;
import com.EMD.LSDB.dao.common.EMDDAO;
import com.EMD.LSDB.dao.common.EMDQueries;
import com.EMD.LSDB.vo.common.GenArrangeVO;

/**
 * @author PS57222
 * 
 */
public class ModelGenArrangeDAO extends EMDDAO {
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objGenArrangeVO
	 *            The Object for Search GenArrImages
	 * @return boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList fetchGenArrImages(GenArrangeVO objGenArrangeVO)
	throws EMDException {
		LogUtil
		.logMessage("Enters into  ModelGenArrangeDAO:fetchGenArrImages method");
		
		Connection objConnection = null;
		ArrayList arlArrayList = new ArrayList();
		ResultSet objResultSet = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		CallableStatement objCallableStatement = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		try {
			strLogUser = objGenArrangeVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_SELECT_GENARRANGEIMAGES);
			objCallableStatement.setInt(1, objGenArrangeVO.getModelSeqNo());
			objCallableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			objCallableStatement.setString(3, objGenArrangeVO.getUserID());
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			objResultSet = (ResultSet) objCallableStatement.getObject(2);
			
			intLSDBErrorID = objCallableStatement.getInt(4);
			LogUtil.logMessage("LSDB ErrorID in fetchGenImagesDAO:"
					+ intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(5);
			LogUtil.logMessage("LSDB OracleCode in fetchGenImagesDAO:"
					+ strOracleCode);
			strErrorMessage = objCallableStatement.getString(6);
			LogUtil.logMessage("LSDB ErrorMessage in fetchGenImagesDAO:"
					+ strErrorMessage);
			
			if (intLSDBErrorID != 0) {
				
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(String.valueOf(intLSDBErrorID));
				
				throw new DataAccessException(objErrorInfo);
			}
			if (strOracleCode != null && !"0".equals(strOracleCode)) {
				LogUtil.logMessage("Enters into oracle error code block:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				
				StringBuffer strStBuffer = new StringBuffer();
				strStBuffer.append(strOracleCode + " ");
				strStBuffer.append(strErrorMessage);
				objErrorInfo.setMessage(strStBuffer.toString());
				throw new ApplicationException(objErrorInfo);
			}
			
			while (objResultSet.next()) {
				objGenArrangeVO = new GenArrangeVO();
				objGenArrangeVO.setModelViewSeqNo(objResultSet
						.getInt(DatabaseConstants.LS207_MDL_VIEW_SEQ_NO));
				objGenArrangeVO
				.getFileVO()
				.setImageSeqNo(
						objResultSet
						.getInt(DatabaseConstants.LS170_IMG_SEQ_NO));
				objGenArrangeVO.setModelSeqNo(objResultSet
						.getInt(DatabaseConstants.LS200_MDL_SEQ_NO));
				objGenArrangeVO.setGenArrangeNotes(objResultSet
						.getString(DatabaseConstants.LS200_GEN_ARGMNT_NOTES));
				
				objGenArrangeVO.setModelView(objResultSet
						.getString(DatabaseConstants.LS207_MDL_VIEW_NAME));
				//Added for CR_101
				objGenArrangeVO.setMdlViewNotes(objResultSet
						.getString(DatabaseConstants.LS207_MDL_VIEW_NOTES));
				
				
				arlArrayList.add(objGenArrangeVO);
				
			}
			
		}
		
		catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException in ModelGenArrangeDAO:fetchGenArrImages");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in ModelGenArrangeDAO:fetchGenArrImages"
					+ objErrorInfo.getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException block in ModelGenArrangeDAO:fetchGenArrImages");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in ModelGenArrangeDAO:fetchGenArrImages"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelGenArrangeDAO:fetchGenArrImages");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(objResultSet, objCallableStatement,
						objConnection);
			}
			
			catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in ModelGenArrangeDAO:fetchGenArrImages");
				objExp.printStackTrace();
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		LogUtil.logMessage("ArrayList value in addDAO:" + arlArrayList);
		LogUtil
		.logMessage("Exits from ModelGenArrangeDAO:fetchGenArrImages Method ");
		
		return arlArrayList;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objGenArrangeVO
	 *            The Object used for Upload GenArrImages in LSDB_images table
	 * @return boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int updateMdlGenArgmntViewDtls(GenArrangeVO objGenArrangeVO)
	throws EMDException {
		
		LogUtil.logMessage("Enter into GenArrByModelDAO:updateMdlGenArgmntView method");
		
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ArrayList arlArrayList = new ArrayList();
		int intStatusCode = 0;
		int intInserted;
		StringBuffer strBuffer = null;
		String strLogUser = "";
		int intStatus=0;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		try {
			strLogUser = objGenArrangeVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			if(objGenArrangeVO.getFileVO().getFileLength()!=0)
			{
			
			//objConnection.setAutoCommit(false);
			int intFileSize = objGenArrangeVO.getFileVO().getFileLength();
			LogUtil.logMessage("FileSize in DAO:" + intFileSize);
			
			int intModelSeqNo = objGenArrangeVO.getModelSeqNo();
			LogUtil.logMessage("ModelSeqNo in ModelGenArrDAO:" + intModelSeqNo);
			int intModelViewSeqNo = objGenArrangeVO.getModelViewSeqNo();
			LogUtil.logMessage("ModelViewSeqNo in ModelGenArrDAO:"
					+ intModelViewSeqNo);
			String UserId = objGenArrangeVO.getUserID();
			LogUtil.logMessage("UserID in ModelGenArrDAO:" + UserId);
			String strContentType = objGenArrangeVO.getFileVO()
			.getContentType();
			LogUtil.logMessage("ContentType:*******************:"
					+ strContentType);
			
			int intNextSeqNo = DBHelper.getSequenceNumber(objConnection,
					DatabaseConstants.LS170_IMG_SEQ_Name);
			LogUtil.logMessage("NextSeqNo*******************:" + intNextSeqNo);
			Timestamp objSqlDate = ApplicationUtil.getCurrentTimeStamp();
			LogUtil.logMessage("CurrentDate*******************:" + objSqlDate);
			
			arlArrayList.add(new Integer(intNextSeqNo));
			arlArrayList.add(strContentType);
			arlArrayList.add(objGenArrangeVO.getUserID());
			arlArrayList.add(objSqlDate);
			intInserted = DBHelper.executeUpdate(objConnection,
					EMDQueries.Query_EmptyImage, arlArrayList);
			LogUtil.logMessage("Update Value:*******************:"
					+ intInserted);
			
			strBuffer = new StringBuffer();
			strBuffer.append(EMDQueries.Query_UpdateImage + intNextSeqNo);
			String strUpdatequery = strBuffer.toString();
			
			boolean blnUpdated = DBHelper.executeDatabaseUpdateUpload(
					objConnection, strUpdatequery, objGenArrangeVO.getFileVO());
			LogUtil.logMessage("Updated Image:*******************:"
					+ blnUpdated);
			objGenArrangeVO.setModelImgSeqNo(intNextSeqNo);
		}
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_UPDATE_MDL_GEN_ARGMNT_VIEW);
			objCallableStatement.setInt(1, objGenArrangeVO.getModelViewSeqNo());
			objCallableStatement.setInt(2, objGenArrangeVO.getModelSeqNo());
			objCallableStatement.setString(3, objGenArrangeVO.getModelView());
			objCallableStatement.setString(4, objGenArrangeVO.getMdlViewNotes());
			if(objGenArrangeVO.getModelImgSeqNo()!=0){
			objCallableStatement.setInt(5, objGenArrangeVO.getModelImgSeqNo());
			}else{
				objCallableStatement.setNull(5, Types.NULL);
			}
			objCallableStatement.setString(6, objGenArrangeVO.getUserID());
			objCallableStatement.registerOutParameter(7, Types.INTEGER);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			objCallableStatement.registerOutParameter(9, Types.VARCHAR);
						
			intStatus = objCallableStatement.executeUpdate();
			LogUtil
			.logMessage("Inside the modifyMdlViewNameAndNotes method of GenArrByModelDAO :intStatus .."
					+ intStatus);
			if (intStatus > 0) {
				intStatus = 0;
				LogUtil.logMessage("inside intstatus::" + intStatus);
				
			}
			
			intLSDBErrorID = (int) objCallableStatement.getInt(7);
			strOracleCode = (String) objCallableStatement.getString(8);
			strErrorMessage = (String) objCallableStatement.getString(9);
			if (intLSDBErrorID != 0) {
				
				LogUtil.logMessage("Enters into Error Loop:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessageID("" + intLSDBErrorID);
				LogUtil.logMessage("Error message in ErrorInfo:"
						+ objErrorInfo.getMessageID());
				throw new  DataAccessException(objErrorInfo);
				
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {
				//Un handled exception				
				LogUtil.logMessage("strOracleCode:" + strOracleCode);
				ErrorInfo objErrorInfo = new ErrorInfo();
				StringBuffer strbuffer = new StringBuffer();
				strbuffer.append(strOracleCode + " ");
				strbuffer.append(strErrorMessage);
				LogUtil.logMessage("sb.toString():" + strbuffer.toString());
				objErrorInfo.setMessage(strbuffer.toString());
				LogUtil.logError(objErrorInfo);
				objConnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
			
			LogUtil
			.logMessage("Exits From Update ImgSeqNo Block of GenArrByModelDAO:updateMdlGenArgmntView");
			
			
			
		}
		
		catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException GenArrByModelDAO:uploadImage");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("ENters into DataAccessException catch block in GenArrByModelDAO:uploadImage"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (Exception objExp) {
			try {
				objConnection.rollback();
			} catch (Exception objinnerExp) {
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objinnerExp.getMessage());
				LogUtil
				.logMessage("ENters into Exception block in GenArrByModelDAO:uploadImage1"
						+ objinnerExp.getMessage());
				throw new ApplicationException(objinnerExp, objErrorInfo);
				
			}
			LogUtil.logMessage("Enters into Exception exception...");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			LogUtil
			.logMessage("ENters into Exception block in GenArrByModelDAO:uploadImage2"
					+ objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(null, objCallableStatement,
						objConnection);
			}
			
			catch (Exception objExp) {
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				LogUtil
				.logMessage("ENters into Exception block in GenArrByModelDAO:uploadImage in finally block"
						+ objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return intStatusCode;
	}
	
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objGenArrangeVO
	 *            The Object used for deleteMdlGenArgmtView in LSDB_images table
	 * @return boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int deleteMdlGenArgmtView(GenArrangeVO objGenArrangeVO)
	throws EMDException {
		
		LogUtil.logMessage("Enter into GenArrByModelDAO:deleteMdlGenArgmtView method");
		
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		int intStatusCode = 0;
		String strLogUser = "";
		int intStatus=0;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		try {
			strLogUser = objGenArrangeVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			String UserId = objGenArrangeVO.getUserID();
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_DELETE_MDL_GEN_ARGMNT_VIEW);
			objCallableStatement.setInt(1, objGenArrangeVO.getModelViewSeqNo());
			objCallableStatement.setInt(2, objGenArrangeVO.getModelSeqNo());
			objCallableStatement.setString(3, objGenArrangeVO.getUserID());
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
						
			intStatus = objCallableStatement.executeUpdate();
			LogUtil
			.logMessage("Inside  method of deleteMdlGenArgmtView :intStatus .."
					+ intStatus);
			if (intStatus > 0) {
				intStatus = 0;
				LogUtil.logMessage("inside intstatus::" + intStatus);
				
			}
			
			intLSDBErrorID = (int) objCallableStatement.getInt(4);
			strOracleCode = (String) objCallableStatement.getString(5);
			strErrorMessage = (String) objCallableStatement.getString(6);
			if (intLSDBErrorID != 0) {
				
				LogUtil.logMessage("Enters into Error Loop:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessageID("" + intLSDBErrorID);
				LogUtil.logMessage("Error message in ErrorInfo:"
						+ objErrorInfo.getMessageID());
				throw new  DataAccessException(objErrorInfo);
				
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {
				//Un handled exception				
				LogUtil.logMessage("strOracleCode:" + strOracleCode);
				ErrorInfo objErrorInfo = new ErrorInfo();
				StringBuffer strbuffer = new StringBuffer();
				strbuffer.append(strOracleCode + " ");
				strbuffer.append(strErrorMessage);
				LogUtil.logMessage("sb.toString():" + strbuffer.toString());
				objErrorInfo.setMessage(strbuffer.toString());
				LogUtil.logError(objErrorInfo);
				objConnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
		}
		
		catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException GenArrByModelDAO:deleteMdlGenArgmtView");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("ENters into DataAccessException catch block in GenArrByModelDAO:deleteMdlGenArgmtView"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (Exception objExp) {
			try {
				objConnection.rollback();
			} catch (Exception objinnerExp) {
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objinnerExp.getMessage());
				LogUtil
				.logMessage("ENters into Exception block in GenArrByModelDAO:deleteMdlGenArgmtView"
						+ objinnerExp.getMessage());
				throw new ApplicationException(objinnerExp, objErrorInfo);
				
			}
			LogUtil.logMessage("Enters into Exception exception...");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			LogUtil
			.logMessage("ENters into Exception block in GenArrByModelDAO:deleteMdlGenArgmtView"
					+ objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(null, objCallableStatement,
						objConnection);
			}
			
			catch (Exception objExp) {
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				LogUtil
				.logMessage("ENters into Exception block in GenArrByModelDAO:deleteMdlGenArgmtView in finally block"
						+ objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return intStatusCode;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objGenArrangeVO
	 *            The Object used for Modify GenArrNotes in LSDB200_MODEL table
	 * @return boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int modifyGenArrNotes(GenArrangeVO objGenArrangeVO)
	throws EMDException {
		LogUtil
		.logMessage("Enters into GenArrByModelDAO:modifyGenArrNotes method");
		
		Connection objConnection = null;
		ArrayList arlArrayList = new ArrayList();
		int intStatusCode = 0;
		String strLogUser = "";
		arlArrayList.add(objGenArrangeVO.getGenArrangeNotes());
		LogUtil.logMessage("GenArrangeNotes in modifyGenArrNotes:"
				+ objGenArrangeVO.getGenArrangeNotes());
		arlArrayList.add(new Integer(objGenArrangeVO.getModelSeqNo()));
		LogUtil
		.logMessage("Inside modifyGenArrNotes method in  ModelGenArrangeDAO:(objGenArrangeVO.getModelSeqNo()):"
				+ objGenArrangeVO.getModelSeqNo());
		
		LogUtil
		.logMessage("Enter into modifyGenArrNotes in GenArrByModelDAO Connection:"
				+ objConnection);
		try {
			strLogUser = objGenArrangeVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			
			intStatusCode = DBHelper.executeUpdate(objConnection,
					EMDQueries.Query_updateGenArrNotes, arlArrayList);
			LogUtil.logMessage("Return value of executeUpdate Method:"
					+ intStatusCode);
			
			if (intStatusCode > 0) {
				intStatusCode = 0;
			}
			
		} catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException GenArrByModelDAO:modifyGenArrNotes");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("ENters into DataAccessException block in GenArrByModelDAO:modifyGenArrNotes"
					+ objErrorInfo.getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in GenArrByModelDAO:modifyGenArrNotes");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			LogUtil
			.logMessage("ENters into Exception block in GenArrByModelDAO:modifyGenArrNotes"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeConnection(objConnection);
			}
			
			catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in GenArrByModelDAO:modifyGenArrNotes");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				LogUtil
				.logMessage("ENters into Exception block in GenArrByModelDAO:modifyGenArrNotes"
						+ objErrorInfo.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		LogUtil
		.logMessage("Leaves From GenArrByModelDAO:modifyGenArrNotes method");
		return intStatusCode;
	}
	

	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objGenArrangeVO
	 *            The Object used for Upload GenArrImages in LSDB_images table
	 * @return boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int uploadMdlGenArgmntViewDtls(GenArrangeVO objGenArrangeVO)
	throws EMDException {
		
		LogUtil.logMessage("Enter into GenArrByModelDAO:uploadMdlGenArgmntViewDtls method@@@@@@");
		
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ArrayList arlArrayList = new ArrayList();
		int intStatusCode = 0;
		int intInserted;
		StringBuffer strBuffer = null;
		String strLogUser = "";
		int intStatus=0;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		try {
			strLogUser = objGenArrangeVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			
			int intFileSize = objGenArrangeVO.getFileVO().getFileLength();
			LogUtil.logMessage("FileSize in DAO:" + intFileSize);
			
			int intModelSeqNo = objGenArrangeVO.getModelSeqNo();
			LogUtil.logMessage("ModelSeqNo in ModelGenArrDAO:" + intModelSeqNo);
			int intModelViewSeqNo = objGenArrangeVO.getModelViewSeqNo();
			LogUtil.logMessage("ModelViewSeqNo in ModelGenArrDAO:"
					+ intModelViewSeqNo);
			String UserId = objGenArrangeVO.getUserID();
			LogUtil.logMessage("UserID in ModelGenArrDAO:" + UserId);
			String strContentType = objGenArrangeVO.getFileVO()
			.getContentType();
			LogUtil.logMessage("ContentType:*******************:"
					+ strContentType);
			
			int intNextSeqNo = DBHelper.getSequenceNumber(objConnection,
					DatabaseConstants.LS170_IMG_SEQ_Name);
			LogUtil.logMessage("NextSeqNo*******************:" + intNextSeqNo);
			Timestamp objSqlDate = ApplicationUtil.getCurrentTimeStamp();
			LogUtil.logMessage("CurrentDate*******************:" + objSqlDate);
			
			arlArrayList.add(new Integer(intNextSeqNo));
			arlArrayList.add(strContentType);
			arlArrayList.add(objGenArrangeVO.getUserID());
			arlArrayList.add(objSqlDate);
			intInserted = DBHelper.executeUpdate(objConnection,
					EMDQueries.Query_EmptyImage, arlArrayList);
			LogUtil.logMessage("Update Value:*******************:"
					+ intInserted);
			
			strBuffer = new StringBuffer();
			strBuffer.append(EMDQueries.Query_UpdateImage + intNextSeqNo);
			String strUpdatequery = strBuffer.toString();
			
			boolean blnUpdated = DBHelper.executeDatabaseUpdateUpload(
					objConnection, strUpdatequery, objGenArrangeVO.getFileVO());
			LogUtil.logMessage("Updated Image:*******************uploadMdlGenArgmntViewDtls:"
					+ blnUpdated);
			objGenArrangeVO.setModelImgSeqNo(intNextSeqNo);
		
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_INSERT_MDL_GEN_ARGMNT_VIEW);
			objCallableStatement.setInt(1, objGenArrangeVO.getModelSeqNo());
			objCallableStatement.setString(2, objGenArrangeVO.getMdlNewViewName());
			LogUtil.logMessage("inside objGenArrangeVO.getMdlNewViewName()::" + objGenArrangeVO.getMdlNewViewName());
			if(objGenArrangeVO.getMdlNewViewNotes()!=null || objGenArrangeVO.getMdlNewViewNotes()!="" ){
				objCallableStatement.setString(3, objGenArrangeVO.getMdlNewViewNotes());
				}else{
					objCallableStatement.setNull(3, Types.NULL);
				}
					
			if(objGenArrangeVO.getModelImgSeqNo()!=0){
			objCallableStatement.setInt(4, objGenArrangeVO.getModelImgSeqNo());
			}else{
				objCallableStatement.setNull(4, Types.NULL);
			}
			objCallableStatement.setString(5, objGenArrangeVO.getUserID());
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
						
			intStatus = objCallableStatement.executeUpdate();
			LogUtil
			.logMessage("Inside the uploadMdlGenArgmntViewDtls method of GenArrByModelDAO :intStatus .."
					+ intStatus);
			if (intStatus > 0) {
				intStatus = 0;
				LogUtil.logMessage("inside intstatus::" + intStatus);
				
			}
			
			intLSDBErrorID = (int) objCallableStatement.getInt(6);
			strOracleCode = (String) objCallableStatement.getString(7);
			strErrorMessage = (String) objCallableStatement.getString(8);
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Enters into Error Loop:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessageID("" + intLSDBErrorID);
				LogUtil.logMessage("Error message in ErrorInfo:"
						+ objErrorInfo.getMessageID());
				throw new  DataAccessException(objErrorInfo);
				
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {
				//Un handled exception				
				LogUtil.logMessage("strOracleCode:" + strOracleCode);
				ErrorInfo objErrorInfo = new ErrorInfo();
				StringBuffer strbuffer = new StringBuffer();
				strbuffer.append(strOracleCode + " ");
				strbuffer.append(strErrorMessage);
				LogUtil.logMessage("sb.toString():" + strbuffer.toString());
				objErrorInfo.setMessage(strbuffer.toString());
				LogUtil.logError(objErrorInfo);
				objConnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
			
			LogUtil
			.logMessage("Exits From Update ImgSeqNo Block of GenArrByModelDAO:uploadMdlGenArgmntViewDtls");
		
		
		}
		
		catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException GenArrByModelDAO:uploadMdlGenArgmntViewDtls");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("ENters into DataAccessException catch block in GenArrByModelDAO:uploadMdlGenArgmntViewDtls"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (Exception objExp) {
			try {
				objConnection.rollback();
			} catch (Exception objinnerExp) {
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objinnerExp.getMessage());
				LogUtil
				.logMessage("ENters into Exception block in GenArrByModelDAO:uploadMdlGenArgmntViewDtls"
						+ objinnerExp.getMessage());
				throw new ApplicationException(objinnerExp, objErrorInfo);
				
			}
			LogUtil.logMessage("Enters into Exception exception...");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			LogUtil
			.logMessage("ENters into Exception block in GenArrByModelDAO:uploadMdlGenArgmntViewDtls"
					+ objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(null, objCallableStatement,
						objConnection);
			}
			
			catch (Exception objExp) {
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				LogUtil
				.logMessage("ENters into Exception block in GenArrByModelDAO:uploadImage in finally block"
						+ objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return intStatusCode;
	}
	
	
}