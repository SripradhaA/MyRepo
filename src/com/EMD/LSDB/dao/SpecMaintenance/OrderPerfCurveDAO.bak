package com.EMD.LSDB.dao.SpecMaintenance;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.tomcat.dbcp.dbcp2.DelegatingConnection;//Added for Tomcat & CR-123

import oracle.jdbc.driver.OracleTypes;
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
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.dao.common.DBHelper;
import com.EMD.LSDB.dao.common.EMDDAO;
import com.EMD.LSDB.dao.common.EMDQueries;
import com.EMD.LSDB.vo.common.PerformanceCurveVO;

public class OrderPerfCurveDAO extends EMDDAO {
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objPerformanceCurveVO
	 *            The Object for Search Performance Curve Images
	 * @return ArrayList The Arraylist containing search Result
	 * @throws EMDException
	 **************************************************************************/
	public static ArrayList fetchPerfCurveImages(
			PerformanceCurveVO objPerformanceCurveVO) throws EMDException {
		LogUtil.logMessage("Enters into  OrderPerfCurveDAO:fetchGenArrImages");
		
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
			strLogUser = objPerformanceCurveVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_SELECT_ORDRPERFCURVEIMG);
			objCallableStatement.setInt(1, objPerformanceCurveVO.getOrderKey());
			//Added for CR-74 VV49326 09-06-09
			objCallableStatement.setString(2,objPerformanceCurveVO.getDataLocationType());
			//Changed for CR-74 VV49326 09-06-09
			objCallableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			/** * Modified For LSDB_CR-74 on 01-June-09 by ps57222 * */
			LogUtil.logMessage("Revision Input:"
					+ objPerformanceCurveVO.getRevisionInput());
			objCallableStatement.setInt(4, objPerformanceCurveVO
					.getRevisionInput());
			objCallableStatement
			.setString(5, objPerformanceCurveVO.getUserID());
			
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			objCallableStatement.execute();
			
			objResultSet = (ResultSet) objCallableStatement.getObject(3);
			LogUtil.logMessage("Query Executed............" + objResultSet);
			
			intLSDBErrorID = objCallableStatement.getInt(6);
			strOracleCode = objCallableStatement.getString(7);
			strErrorMessage = objCallableStatement.getString(8);
			
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
				
				objPerformanceCurveVO = new PerformanceCurveVO();
				objPerformanceCurveVO.setCurveSeqNo(objResultSet
						.getInt(DatabaseConstants.LS409_ORDR_CURVE_IMG_SEQ_NO));
				LogUtil.logMessage("Image Seq No"
						+ objPerformanceCurveVO.getCurveSeqNo());
				objPerformanceCurveVO.setOrderKey(objResultSet
						.getInt(DatabaseConstants.LS400_ORDR_KEY));
				objPerformanceCurveVO
				.getFileVO()
				.setContentType(
						objResultSet
						.getString(DatabaseConstants.LS170_IMG_CONTENT_TYPE));
				LogUtil.logMessage("Order Key"
						+ objPerformanceCurveVO.getOrderKey());
				objPerformanceCurveVO
				.getFileVO()
				.setImageSeqNo(
						objResultSet
						.getInt(DatabaseConstants.LS170_IMG_SEQ_NO));
				
				LogUtil.logMessage("Image Seq No"
						+ objPerformanceCurveVO.getFileVO().getImageSeqNo());
				
				/** Added For LSDB_CR-63 on 11-Dec-08 by ps57222 * */
				
				objPerformanceCurveVO
				.setImageName((objResultSet
						.getString(DatabaseConstants.LS409_ORDR_PDF_IMG_NAME) == null) ? ""
								: objResultSet
								.getString(DatabaseConstants.LS409_ORDR_PDF_IMG_NAME));
				
				/** Added For LSDB_CR-74 on 01-Jun-09 by ps57222 * */
				ResultSet objRevMarker = (ResultSet) objResultSet
				.getObject(DatabaseConstants.REVISION_MARKER);
				ArrayList arlRevList = new ArrayList();
				while (objRevMarker.next()) {
					arlRevList.add(objRevMarker
							.getString(DatabaseConstants.REVISION_NUM));
				}
				LogUtil.logMessage("Revision Marker:" + arlRevList.size());
				
				/**Added for CR-76 VV49326 - Starts Here**/
				objPerformanceCurveVO.setSysMarkFlag(objResultSet
						.getString(DatabaseConstants.IMG_SYS_MARKER));
				objPerformanceCurveVO.setSysMarkDesc(objResultSet
						.getString(DatabaseConstants.SYS_MARKER_DESC));
				/**Added for CR-76 VV49326 - Ends Here**/
				objPerformanceCurveVO.setRevCode(arlRevList);
				DBHelper.closeSQLObjects(objRevMarker, null, null);
				arlImageList.add(objPerformanceCurveVO);
				
			}
			
		} catch (DataAccessException objDataExp) {
			LogUtil.logMessage("Enters into DataAccessException exception...");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil.logMessage("ENters into catch block in DAO:.."
					+ objErrorInfo.getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil.logMessage("Enters into ApplicationException exception...");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil.logMessage("ENters into catch block in DAO:.."
					+ objErrorInfo.getMessage());
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
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
		
		return arlImageList;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objPerformanceCurveVO
	 *            The Object used for Upload Performance Curve
	 * @return boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	public static int uploadPerfCurveImage(
			PerformanceCurveVO objPerformanceCurveVO) throws EMDException {
		
		LogUtil.logMessage("Enter into OrderPerfCurveDAO:uploadImage method");
		
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ArrayList arlImagedetlsList = new ArrayList();
		int intReturn = 0;
		int intInserted;
		int intImgSeqUpdated;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		StringBuffer strBuffer = null;
		String strLogUser = "";
		try {
			strLogUser = objPerformanceCurveVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			int intFileSize = objPerformanceCurveVO.getFileVO().getFileLength();
			LogUtil.logMessage("FileSize in DAO:" + intFileSize);
			int intOrderKey = objPerformanceCurveVO.getOrderKey();
			
			LogUtil
			.logMessage("OrderKey in OrderPerfCurveDAO: UploadPerfCurveImage"
					+ intOrderKey);
			String strContentType = objPerformanceCurveVO.getFileVO()
			.getContentType();
			
			int intNextSeqNo = DBHelper.getSequenceNumber(objConnection,
					DatabaseConstants.LS170_IMG_SEQ_Name);
			int intNextCurveSeqNo = DBHelper.getSequenceNumber(objConnection,
					DatabaseConstants.LS409_ORDR_CURV_IMG_SEQ_NO);
			
			Timestamp objSqlDate = ApplicationUtil.getCurrentTimeStamp();
			
			arlImagedetlsList.add(new Integer(intNextSeqNo));
			arlImagedetlsList.add(strContentType);
			arlImagedetlsList.add(objPerformanceCurveVO.getUserID());
			arlImagedetlsList.add(objSqlDate);
			
			LogUtil.logMessage("Queries for Inserting images");
			LogUtil
			.logMessage("Enters into insert Empty Image Block of OrderPerfCurveDAO:uploadImage");
			
			intInserted = DBHelper.executeUpdate(objConnection,
					EMDQueries.Query_EmptyImage, arlImagedetlsList);
			LogUtil.logMessage("Insert Status of empty Image" + intInserted);
			
			LogUtil
			.logMessage("Enters into Update Image Block of OrderPerfCurveDAO:uploadImage");
			
			strBuffer = new StringBuffer();
			strBuffer.append(EMDQueries.Query_UpdateImage + intNextSeqNo);
			String strUpdatequery = strBuffer.toString();
			boolean blnUpdated = DBHelper.executeDatabaseUpdateUpload(
					objConnection, strUpdatequery, objPerformanceCurveVO
					.getFileVO());
			
			LogUtil
			.logMessage("Enters into Insert ImgSeqNo Block of OrderPerfCurveDAO:uploadImage"
					+ blnUpdated);
			
			ArrayList arlOrderImageDtls = new ArrayList();
			arlOrderImageDtls.add(new Integer(intNextCurveSeqNo));
			arlOrderImageDtls.add(new Integer(objPerformanceCurveVO
					.getOrderKey()));
			arlOrderImageDtls.add(objPerformanceCurveVO.getUserID());
			arlOrderImageDtls.add(objSqlDate);
			arlOrderImageDtls.add(new Integer(intNextSeqNo));
			arlOrderImageDtls.add(DatabaseConstants.DATALOCATION);
			
			// added for uploading PDF image name
			
			if (objPerformanceCurveVO.getFileVO().getFileName() != null) {
				String strFileName = objPerformanceCurveVO.getFileVO()
				.getFileName();
				// strFileName =
				// strFileName.substring(0,strFileName.indexOf("."));
				arlOrderImageDtls.add(new String(strFileName));
				//Added for CR_121 for Performance Curve Rearrange
				arlOrderImageDtls.add(new Integer(objPerformanceCurveVO.getOrderByCode()));
				
			}
			// ENDs
			
			intImgSeqUpdated = DBHelper.executeUpdate(objConnection,
					EMDQueries.Query_Insert_Ordr_Perf_ImgSeqNo,
					arlOrderImageDtls);
			
			LogUtil
			.logMessage("Insert ImgSeqNo Block of OrderPerfCurveDAO:uploadImage"
					+ intImgSeqUpdated);
			
			if (intImgSeqUpdated == 1) {
				intReturn = 0;
				LogUtil.logMessage("blnReturn" + intReturn);
			}
			
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
				StringBuffer sbErrorMessage = new StringBuffer();
				sbErrorMessage.append(strOracleCode + " ");
				sbErrorMessage.append(strErrorMessage);
				LogUtil.logMessage("sbErrorMessage.toString():"
						+ sbErrorMessage.toString());
				objErrorInfo.setMessage(sbErrorMessage.toString());
				LogUtil.logError(objErrorInfo);
				objConnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
		} catch (DataAccessException objDatExp) {
			ErrorInfo objErrorInfo = objDatExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into DataAccess Exception block in DAO:.."
					+ objDatExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDatExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil.logMessage("Enters into AppException block in  DAO :"
					+ objErrorInfo.getMessage());
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
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
		
		return intReturn;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objPerformanceCurveVO
	 *            The Object used for deleting Performance Curve
	 * @return boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	public static int deletePerfCurveImage(
			PerformanceCurveVO objPerformanceCurveVO) throws EMDException {
		LogUtil.logMessage("Enter into OrderPerfCurveDAO:deleteImage method");
		
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		int intReturn = 0;
		int intImgSeqUpdated;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		ArrayList arlUpdateList = null;
		String strLogUser = "";
		try {
			strLogUser = objPerformanceCurveVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			LogUtil
			.logMessage("Enters into delete ImgSeqNo Block of OrderPerfCurveDAO:deleteImage");
			
			arlUpdateList = new ArrayList();
			
			arlUpdateList.add(new Integer(objPerformanceCurveVO.getOrderKey()));
			arlUpdateList
			.add(new Integer(objPerformanceCurveVO.getCurveSeqNo()));
			
			intImgSeqUpdated = DBHelper.executeUpdate(objConnection,
					EMDQueries.Query_Delete_Ordr_Perf_ImgSeqNo, arlUpdateList);
			
			LogUtil
			.logMessage("Exits From Delete ImgSeqNo Block of OrderPerfCurveDAO:deleteImage");
			
			if (intImgSeqUpdated == 1) {
				intReturn = 0;
			}
			
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
				StringBuffer sbErrorMessage = new StringBuffer();
				sbErrorMessage.append(strOracleCode + " ");
				sbErrorMessage.append(strErrorMessage);
				LogUtil.logMessage("sbErrorMessage.toString():"
						+ sbErrorMessage.toString());
				objErrorInfo.setMessage(sbErrorMessage.toString());
				LogUtil.logError(objErrorInfo);
				objConnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
		} catch (DataAccessException objDatExp) {
			ErrorInfo objErrorInfo = objDatExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into DataAccess Exception block in DAO:.."
					+ objDatExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDatExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil.logMessage("Enters into AppException block in  DAO :"
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
		
		return intReturn;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objPerformanceCurveVO
	 *            The Object used to modify Performance Curve Image Name *
	 * @return int value which indicate success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int modifyPerfCurveImageName(
			PerformanceCurveVO objPerformanceCurveVO) throws EMDException {
		
		LogUtil
		.logMessage("Entering OrderPerfCurveDAO:modifyPerfCurveImageName");
		
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intStatusCode;
		int intLSDBErrorID = 0;
		String strMessage = null;
		String strLogUser = "";
		try {
			strLogUser = objPerformanceCurveVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_UPDATE_ORDR_PERF_CURV);
			objCallableStatement.setInt(1, objPerformanceCurveVO.getOrderKey());
			LogUtil
			.logMessage("OrderKey in OrderPerfCurveDAO:modifyPerfCurveImageName:"
					+ objPerformanceCurveVO.getOrderKey());
			objCallableStatement.setString(2, objPerformanceCurveVO
					.getDataLocationType());
			objCallableStatement.setInt(3, objPerformanceCurveVO
					.getCurveSeqNo());
			LogUtil
			.logMessage("CurSeqNo in OrderPerfCurveDAO:modifyPerfCurveImageName:"
					+ objPerformanceCurveVO.getCurveSeqNo());
			objCallableStatement.setString(4, objPerformanceCurveVO
					.getImageName());
			LogUtil
			.logMessage("Image Name in OrderPerfCurveDAO:modifyPerfCurveImageName:"
					+ objPerformanceCurveVO.getImageName());
			objCallableStatement
			.setString(5, objPerformanceCurveVO.getUserID());
			
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			
			intStatusCode = objCallableStatement.executeUpdate();
			
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
			.logMessage("Enters into DataAccessException OrderPerfCurveDAO:modifyPerfCurveImageName");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in OrderPerfCurveDAO:modifyPerfCurveImageName"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException OrderPerfCurveDAO:modifyPerfCurveImageName");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in OrderPerfCurveDAO:modifyPerfCurveImageName"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception OrderPerfCurveDAO:modifyPerfCurveImageName");
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
				.logMessage("Enters into Exception OrderPerfCurveDAO:modifyPerfCurveImageName");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return intStatusCode;
	}
	
//	Added for CR_121 Rearrange Order Performance Curve
	/***************************************************************************
	 * * * Used to save th eorder performance curve rearrange
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objPerformanceCurveVO
	 *            The Object for save Performance Curve rearrange
	 * @return int status of the save operation
	 * @throws EMDException
	 **************************************************************************/
	public static int reArrangePerfCurve(
			PerformanceCurveVO objPerformanceCurveVO) throws EMDException {
		
		LogUtil
		.logMessage("Entering OrderPerfCurveDAO:reArrangePerfCurve");
		
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intStatusCode;
		int intLSDBErrorID = 0;
		String strMessage = null;
		String strLogUser = "";
		ARRAY objArray =null;
		ArrayDescriptor arrayDescriptor = null; 
		try {
			strLogUser = objPerformanceCurveVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_REARRANGE_ORDR_PERF_CURV);
			Connection dconn = ((DelegatingConnection) objConnection).getInnermostDelegate(); //Added for CR-123 & TOmcat
			objCallableStatement.setInt(1, objPerformanceCurveVO.getOrderKey());
			LogUtil
			.logMessage("OrderKey in OrderPerfCurveDAO:reArrangePerfCurve:"
					+ objPerformanceCurveVO.getOrderKey());
			objCallableStatement.setString(2, objPerformanceCurveVO
					.getDataLocationType());
			
			
			arrayDescriptor = new ArrayDescriptor(DatabaseConstants.IN_ARRAY, dconn);
			if(objPerformanceCurveVO.getPerfCurveList() != null )
			{
				objArray = new ARRAY(arrayDescriptor, dconn,objPerformanceCurveVO.getPerfCurveList());
			}
			else
			{
				LogUtil.logMessage("objPerformanceCurveVO.getPerfCurveList():null");
				objArray = new ARRAY(arrayDescriptor, dconn, null); 
			}
			objCallableStatement.setArray(3,objArray);
			
			objCallableStatement
			.setString(4, objPerformanceCurveVO.getUserID());
			
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			
			intStatusCode = objCallableStatement.executeUpdate();
			
			if (intStatusCode > 0) {
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
			LogUtil
			.logMessage("Enters into DataAccessException OrderPerfCurveDAO:reArrangePerfCurve");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in OrderPerfCurveDAO:reArrangePerfCurve"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException OrderPerfCurveDAO:reArrangePerfCurve");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in OrderPerfCurveDAO:reArrangePerfCurve"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception OrderPerfCurveDAO:reArrangePerfCurve");
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
				.logMessage("Enters into Exception OrderPerfCurveDAO:reArrangePerfCurve");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return intStatusCode;
	}
	
}