/*
 * Created on May 5, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.dao.SpecMaintenance;

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
import com.EMD.LSDB.vo.common.AppendixVO;
import com.EMD.LSDB.vo.common.ClauseVO;

/**
 * @author PS57222
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AppendixDAO extends EMDDAO {
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objAppendixVO
	 *            The object for fetch Appendix Images
	 * @return Arraylist It has Arraylist of objAppendixVO
	 * @throws EMDException
	 **************************************************************************/
	
	/***************************************************************************
	 * FetchImage method is used to display the appendix images in appendix
	 * section at order level. Appendix turn on/off flag is used to display
	 * Appendix Turn/OFF toggle button, based on this value will display the
	 * buttin value.
	 */
	public static ArrayList fetchImage(AppendixVO objAppendixVO)
	throws EMDException {
		LogUtil.logMessage("Enters into AppendixDAO:fetchImage");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ArrayList arlImageList = new ArrayList();
		ResultSet objResultSet = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		String strAppendixFlag = null;
		ArrayList arlMappedClause;
		
		try {
			strLogUser = objAppendixVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			LogUtil.logMessage("Connection in AppendixDAO:fetchImage:"
					+ objConnection);
			LogUtil.logMessage("OrderKey in AppendixDAO:fetchImage:"
					+ objAppendixVO.getOrderKey());
			LogUtil.logMessage("DataLocationType in AppendixDAO:fetchImage:"
					+ objAppendixVO.getDataLocationType());
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_SELECT_ORDR_APNDX_IMG);
			objCallableStatement.setInt(1, objAppendixVO.getOrderKey());
			objCallableStatement.setString(2, objAppendixVO
					.getDataLocationType());
			objCallableStatement.setString(3, objAppendixVO.getUserID());
			objCallableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			objCallableStatement.execute();
			
			objResultSet = (ResultSet) objCallableStatement.getObject(4);
			strAppendixFlag = objCallableStatement.getString(5);
			
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
			while (objResultSet.next()) {
				objAppendixVO = new AppendixVO();
				objAppendixVO.setImageName(objResultSet
						.getString(DatabaseConstants.LS415_IMG_NAME));
				objAppendixVO.setImageDesc(objResultSet
						.getString(DatabaseConstants.LS415_IMG_DESC));
				objAppendixVO.setImageSeqNo(objResultSet
						.getInt(DatabaseConstants.LS170_IMG_SEQ_NO));
				/* Added for CR_97 */
				objAppendixVO.getFileVO().setContentType(objResultSet
						.getString(DatabaseConstants.LS170_IMG_CONTENT_TYPE));
				/* CR_97 Ends here */
				
				// Added For CR_101 to hide Images in Sales Spec 
				objAppendixVO.setSalesDispFlag(objResultSet.getString(DatabaseConstants.SALES_SPEC_DISP_FLAG));
				LogUtil.logMessage("Sales Display Flag"+ objAppendixVO.getSalesDispFlag());
				// CR_101 Ends here
				
				ResultSet objClauseSet = (ResultSet) objResultSet
				.getObject("Clauses");
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
					LogUtil.logMessage("Map Number in AppendixDAO:fetchImage"
							+ strViewMap);
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
				objAppendixVO.setMapClauses(arlMappedClause);
				DBHelper.closeSQLObjects(objClauseSet, null, null);
				arlImageList.add(objAppendixVO);
			}
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil.logMessage("ENters into catch block in DAO:.."
					+ objDataExp.getMessage());
			LogUtil.logMessage("ENters into catch block in DAO:.."
					+ objErrorInfo);
			LogUtil.logMessage("ENters into catch block in DAO:.."
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil.logMessage("ENters into catch block in DAO:.."
					+ objErrorInfo.getMessage());
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
			
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception exception...");
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
				LogUtil.logMessage("Enters into Exception exception...");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		LogUtil.logMessage("Size of ArrayList in AppendixDAO:fetchImage "
				+ arlImageList.size());
		return arlImageList;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objAppendixVO
	 *            The object to add Appendix Images
	 * @return Returns a integer value with the status of a transaction
	 * @throws EMDException
	 **************************************************************************/
	
	/***************************************************************************
	 * AddImage method is used to add the appendix images in appendix section at
	 * order level.This method returns an integer value as a status of
	 * transaction,if Image name already exists then it returns an LSdbError
	 * Code from Oracle end.
	 */
	
	public static int addImage(AppendixVO objAppendixVO) throws EMDException {
		LogUtil.logMessage("Enters into AppendixDAO:fetchImage");
		Connection objConnection = null;
		
		int intInserted;
		int intStatusCode = 0;
		ArrayList arlUpdateList;
		String strLogUser = "";
		CallableStatement objCallableStatement = null;
		ArrayList arlEmptyImage = new ArrayList();
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		try {
			strLogUser = objAppendixVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			
			LogUtil.logMessage("OrderKey in AppendixDAO:addImage:"
					+ objAppendixVO.getOrderKey());
			LogUtil.logMessage("DataLocationType in AppendixDAO:addImage:"
					+ objAppendixVO.getDataLocationType());
			
			int intNextSeqNo = DBHelper.getSequenceNumber(objConnection,
					DatabaseConstants.LS170_IMG_SEQ_Name);
			LogUtil.logMessage("NextSeqNo*******************:" + intNextSeqNo);
			
			Timestamp objSqlDate = ApplicationUtil.getCurrentTimeStamp();
			LogUtil.logMessage("CurrentDate*******************:" + objSqlDate);
			
			LogUtil.logMessage("Content Type in AppendixDAO:addImage:"
					+ objAppendixVO.getFileVO().getContentType());
			
			arlEmptyImage.add(new Integer(intNextSeqNo));
			arlEmptyImage.add(objAppendixVO.getFileVO().getContentType());
			arlEmptyImage.add(objAppendixVO.getUserID());
			arlEmptyImage.add(objSqlDate);
			
			/*******************************************************************
			 * This Block code insert the content type and other details of the
			 * Appendix Image with an empty blob value in LSDB170_IMAGES table.
			 */
			
			LogUtil
			.logMessage("Enters into insert Empty Image Block of AppendixDAO:uploadImage");
			intInserted = DBHelper.executeUpdate(objConnection,
					EMDQueries.Query_EmptyImage, arlEmptyImage);
			LogUtil.logMessage("Update Value:*******************:"
					+ intInserted);
			LogUtil
			.logMessage("Exits From insert Empty Image Block of AppendixDAO:uploadImage:");
			
			/*******************************************************************
			 * This Block code update the empty blob value into the binary
			 * stream of Appendix Image in LSDB170_IMAGES table.
			 */
			
			LogUtil
			.logMessage("Enters into Update Image Block of AppendixDAO:uploadImage");
			StringBuffer strBuffer = new StringBuffer();
			strBuffer.append(EMDQueries.Query_UpdateImage + intNextSeqNo);
			String strUpdatequery = strBuffer.toString();
			boolean blnUpdated = DBHelper.executeDatabaseUpdateUpload(
					objConnection, strUpdatequery, objAppendixVO.getFileVO());
			LogUtil.logMessage("Updated Image:*******************:"
					+ blnUpdated);
			LogUtil
			.logMessage("Exits From Update Image Block of AppendixDAO:uploadImage:");
			
			LogUtil
			.logMessage("Enters into Update ImgSeqNo Block of AppendixDAO:uploadImage");
			
			/*******************************************************************
			 * This Block code update the LSDB415_ORDR_APNDX_IMG table with the
			 * orderkey and ImageSeqNO.
			 */
			
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_INSERT_ORDR_APNDX_IMG);
			objCallableStatement.setInt(1, objAppendixVO.getOrderKey());
			objCallableStatement.setString(2, objAppendixVO
					.getDataLocationType());
			objCallableStatement.setInt(3, intNextSeqNo);
			objCallableStatement.setString(4, objAppendixVO.getImageName());
			objCallableStatement.setString(5, objAppendixVO.getImageDesc());
			objCallableStatement.setString(6, objAppendixVO.getUserID());
			objCallableStatement.registerOutParameter(7, Types.INTEGER);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			objCallableStatement.registerOutParameter(9, Types.VARCHAR);
			int intImgSeqUpdated = objCallableStatement.executeUpdate();
			
			if (intImgSeqUpdated > 0) {
				intStatusCode = 0;
			}
			
			intLSDBErrorID = objCallableStatement.getInt(7);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(8);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(9);
			
			LogUtil.logMessage("ErrorMessage:" + strErrorMessage);
			
			/*******************************************************************
			 * If Image name already exists in the LSDB415_ORDR_APNDX_IMG table
			 * then intLSDBErrorID is greater than zero so the connection gets
			 * rollbacked and the previous transaction for image table also
			 * rollbacked.
			 *  
			 */
			
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Enters into Error Loop:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				strMessage = String.valueOf(intLSDBErrorID);
				LogUtil.logMessage("Error message in DAO:" + strMessage);
				
				objErrorInfo.setMessageID(strMessage);
				LogUtil.logMessage("Error message in ErrorInfo:"
						+ objErrorInfo.getMessageID());
				objConnection.rollback();
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
			.logMessage("Enters into DataAccessException AppendixDAO:addImage");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in AppendixDAO:addImage"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException SAppendixDAO:addImage");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in AppendixDAO:addImage"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("ENters into Exception block in AppendixDAO:addImage");
			try {
				objConnection.rollback();
			} catch (Exception objinnerExp) {
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objinnerExp.getMessage());
				LogUtil
				.logMessage("ENters into Exception block in AppendixDAO:addImage"
						+ objinnerExp.getMessage());
				throw new ApplicationException(objinnerExp, objErrorInfo);
				
			}
			LogUtil.logMessage("Enters into Exception exception...");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			LogUtil
			.logMessage("ENters into Exception block in AppendixDAO:addImage"
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
				.logMessage("ENters into Exception block in AppendixDAO:addImage"
						+ objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return intStatusCode;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objAppendixVO
	 *            The object to modify Appendix Image name and Description
	 * @return Returns a integer value with the status of a transaction
	 * @throws EMDException
	 **************************************************************************/
	
	/***************************************************************************
	 * modifyImageName method is used to Modify the appendix image name and
	 * description in appendix section at order level.This method returns an
	 * integer value as a status of transaction,if Image name already exists
	 * then it returns an LSdbError Code from Oracle end.
	 */
	
	public static int modifyImageName(AppendixVO objAppendixVO)
	throws EMDException {
		LogUtil.logMessage("Enters into AppendixDAO:modifyImageName");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		int intStatusCode = 0;
		try {
			strLogUser = objAppendixVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			LogUtil.logMessage("Connection in AppendixDAO:modifyImageName:"
					+ objConnection);
			LogUtil.logMessage("OrderKey in AppendixDAO:modifyImageName:"
					+ objAppendixVO.getOrderKey());
			LogUtil
			.logMessage("DataLocationType in AppendixDAO:modifyImageName:"
					+ objAppendixVO.getDataLocationType());
			
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_UPDATE_ORDR_APNDX_IMG);
			objCallableStatement.setInt(1, objAppendixVO.getOrderKey());
			objCallableStatement.setString(2, objAppendixVO
					.getDataLocationType());
			objCallableStatement.setInt(3, objAppendixVO.getImageSeqNo());
			objCallableStatement.setString(4, objAppendixVO.getImageName());
			objCallableStatement.setString(5, objAppendixVO.getImageDesc());
			objCallableStatement.setString(6, objAppendixVO.getUserID());
			objCallableStatement.registerOutParameter(7, Types.INTEGER);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			objCallableStatement.registerOutParameter(9, Types.VARCHAR);
			int intImgSeqUpdated = objCallableStatement.executeUpdate();
			
			if (intImgSeqUpdated > 0) {
				intStatusCode = 0;
			}
			
			intLSDBErrorID = objCallableStatement.getInt(7);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(8);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(9);
			
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
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil.logMessage("ENters into catch block in DAO:.."
					+ objDataExp.getMessage());
			LogUtil.logMessage("ENters into catch block in DAO:.."
					+ objErrorInfo);
			LogUtil.logMessage("ENters into catch block in DAO:.."
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil.logMessage("ENters into catch block in DAO:.."
					+ objErrorInfo.getMessage());
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
			
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception exception...");
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
				LogUtil.logMessage("Enters into Exception exception...");
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
	 * @param objAppendixVO
	 *            The object to modify Appendix Image name and Description
	 * @return Returns a integer value with the status of a transaction
	 * @throws EMDException
	 **************************************************************************/
	
	/***************************************************************************
	 * deleteImage method is used to Delete the appendix Images in appendix
	 * section at order level.This method returns an integer value as a status
	 * of transaction.
	 */
	
	public static int deleteImage(AppendixVO objAppendixVO) throws EMDException {
		LogUtil.logMessage("Enters into AppendixDAO:deleteImage");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		int intStatusCode = 0;
		try {
			strLogUser = objAppendixVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			LogUtil.logMessage("Connection in AppendixDAO:deleteImage:"
					+ objConnection);
			LogUtil.logMessage("OrderKey in AppendixDAO:deleteImage:"
					+ objAppendixVO.getOrderKey());
			LogUtil.logMessage("DataLocationType in AppendixDAO:deleteImage:"
					+ objAppendixVO.getDataLocationType());
			
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_DELETE_ORDR_APNDX_IMG);
			objCallableStatement.setInt(1, objAppendixVO.getOrderKey());
			objCallableStatement.setString(2, objAppendixVO
					.getDataLocationType());
			objCallableStatement.setInt(3, objAppendixVO.getImageSeqNo());
			objCallableStatement.setString(4, objAppendixVO.getUserID());
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			int intImgSeqUpdated = objCallableStatement.executeUpdate();
			
			if (intImgSeqUpdated > 0) {
				intStatusCode = 0;
			}
			
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
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil.logMessage("ENters into catch block in DAO:.."
					+ objDataExp.getMessage());
			LogUtil.logMessage("ENters into catch block in DAO:.."
					+ objErrorInfo);
			LogUtil.logMessage("ENters into catch block in DAO:.."
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil.logMessage("ENters into catch block in DAO:.."
					+ objErrorInfo.getMessage());
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
			
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception exception...");
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
				LogUtil.logMessage("Enters into Exception exception...");
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
	 * @param objAppendixVO
	 *            The object to modify Appendix Image name and Description
	 * @return Returns a integer value with the status of a transaction
	 * @throws EMDException
	 **************************************************************************/
	
	/***************************************************************************
	 * deleteImage method is used to Delete the appendix Images in appendix
	 * section at order level.This method returns an integer value as a status
	 * of transaction.
	 */
	
	public static int saveMappings(AppendixVO objAppendixVO)
	throws EMDException {
		LogUtil.logMessage("Enters into AppendixDAO:saveMappings");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		int intStatusCode = 0;
		try {
			strLogUser = objAppendixVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			LogUtil.logMessage("Connection in AppendixDAO:saveMappings:"
					+ objConnection);
			LogUtil.logMessage("OrderKey in AppendixDAO:saveMappings:"
					+ objAppendixVO.getOrderKey());
			LogUtil.logMessage("DataLocationType in AppendixDAO:saveMappings:"
					+ objAppendixVO.getDataLocationType());
			LogUtil.logMessage("ImageSeqNo in AppendixDAO:saveMappings:"
					+ objAppendixVO.getImageSeqNo());
			LogUtil.logMessage("ClauseSeqNo in AppendixDAO:saveMappings:"
					+ objAppendixVO.getClauseSeqNo());
			LogUtil.logMessage("VersionNo in AppendixDAO:saveMappings:"
					+ objAppendixVO.getVersionNo());
			
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_CLAUSE_APNDX_IMG);
			objCallableStatement.setInt(1, objAppendixVO.getOrderKey());
			objCallableStatement.setString(2, objAppendixVO
					.getDataLocationType());
			objCallableStatement.setInt(3, objAppendixVO.getImageSeqNo());
			objCallableStatement.setInt(4, objAppendixVO.getClauseSeqNo());
			objCallableStatement.setInt(5, objAppendixVO.getVersionNo());
			objCallableStatement.setString(6, objAppendixVO.getUserID());
			objCallableStatement.registerOutParameter(7, Types.INTEGER);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			objCallableStatement.registerOutParameter(9, Types.VARCHAR);
			int intImgSeqUpdated = objCallableStatement.executeUpdate();
			
			if (intImgSeqUpdated > 0) {
				intStatusCode = 0;
			}
			
			intLSDBErrorID = objCallableStatement.getInt(7);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(8);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(9);
			
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
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil.logMessage("ENters into catch block in DAO:.."
					+ objDataExp.getMessage());
			LogUtil.logMessage("ENters into catch block in DAO:.."
					+ objErrorInfo);
			LogUtil.logMessage("ENters into catch block in DAO:.."
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil.logMessage("ENters into catch block in DAO:.."
					+ objErrorInfo.getMessage());
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
			
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception exception...");
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
				LogUtil.logMessage("Enters into Exception exception...");
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
	 * @param objAppendixVO
	 *            The Object for Search Model Appendix Images
	 * @return boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList fetchModelAppendixImages(
			AppendixVO objAppendixVO) throws EMDException {
		LogUtil.logMessage("Entering AppendixDAO:fetchModelAppendixImages");
		
		Connection objConnection = null;
		ArrayList arlImageList = new ArrayList();
		ResultSet objResultSet = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		CallableStatement objCallableStatement = null;
		int intLSDBErrorID = 0;
		String strMessage = null;
		String strLogUser = "";
	
		try {
			strLogUser = objAppendixVO.getUserID();
			
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_SELECT_MDL_APNDX_IMG);
			LogUtil.logMessage("Prepared Calling Statement");
			objCallableStatement.setString(1,objAppendixVO.getModelSeqNo());
			objCallableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			objCallableStatement.setString(3, objAppendixVO.getUserID());
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
				
				objAppendixVO = new AppendixVO();
				objAppendixVO.setModelSeqNo(objResultSet
						.getString(DatabaseConstants.LS200_MDL_SEQ_NO));
				objAppendixVO.setImageName(objResultSet
						.getString(DatabaseConstants.LS209_IMG_NAME));
				objAppendixVO.setImageDesc(objResultSet
						.getString(DatabaseConstants.LS209_IMG_DESC));
				objAppendixVO
				.getFileVO()
				.setImageSeqNo(
						objResultSet
						.getInt(DatabaseConstants.LS170_IMG_SEQ_NO));
				/*Added for CR_97 */
				objAppendixVO.getFileVO().setContentType(objResultSet
						.getString(DatabaseConstants.LS170_IMG_CONTENT_TYPE));
				/* CR_97 Ends here*/
				
				arlImageList.add(objAppendixVO);
				LogUtil.logMessage("Leaving AppendixDAO: fetchModelAppendixImages");
			}
			
		}
		
		catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException exception in AppendixDAO:fetchModelAppendixImages");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into catch block in AppendixDAO:fetchModelAppendixImages"
					+ objErrorInfo.getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException exception in AppendixDAO:fetchModelAppendixImages");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into catch block in AppendixDAO:fetchModelAppendixImages"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception exception in AppendixDAO:fetchModelAppendixImages");
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
				.logMessage("Enters into Exception exception in AppendixDAO:fetchModelAppendixImages");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp);
			}
			
		}
		
		return arlImageList;
		
	}
	
}