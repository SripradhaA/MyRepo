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
import com.EMD.LSDB.vo.common.GenArrangeVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business methods for the Order
 *          GeneralArrangement
 ******************************************************************************/
public class OrderGenArrangeDAO extends EMDDAO {
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objGenArrangeVO
	 *            the object for Fetching Images
	 * @return ArrayList of Images
	 * @throws EMDException
	 **************************************************************************/
	public static ArrayList fetchGenArrImages(GenArrangeVO objGenArrangeVO)
	throws EMDException {
		LogUtil
		.logMessage("Enters into  OrderGenArrangeDAO:fetchGenArrImages method");
		
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
			.prepareCall(EMDQueries.SP_SELECT_ORDER_GENARRANGEIMAGES);
			objCallableStatement.setInt(1, objGenArrangeVO.getOrderKey());
			/**Added for CR-74 VV49326 09-06-09**/
			objCallableStatement.setString(2,objGenArrangeVO.getDataLocationType());
			//Changed for CR-74 VV49326 09-06-09
			objCallableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			/**
			 * Modified For LSDB_CR-74[Revision Markers] on 01-June-09 by
			 * ps57222 *
			 */
			objCallableStatement.setInt(4, objGenArrangeVO.getRevisionInput());
			objCallableStatement.setString(5, objGenArrangeVO.getUserID());
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			objResultSet = (ResultSet) objCallableStatement.getObject(3);
			
			intLSDBErrorID = objCallableStatement.getInt(6);
			
			strOracleCode = objCallableStatement.getString(7);
			
			strErrorMessage = objCallableStatement.getString(8);
			
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
						.getInt(DatabaseConstants.LS404_ORDR_VIEW_SEQ_NO));
				objGenArrangeVO.setOrderKey(objResultSet
						.getInt(DatabaseConstants.LS400_ORDR_KEY));
				objGenArrangeVO
				.getFileVO()
				.setImageSeqNo(
						objResultSet
						.getInt(DatabaseConstants.LS170_IMG_SEQ_NO));
				objGenArrangeVO.setGenArrangeNotes(objResultSet
						.getString(DatabaseConstants.LS400_GEN_ARGMNT_NOTES));
				objGenArrangeVO.setMdlViewNotes(objResultSet
						.getString(DatabaseConstants.LS404_ORDR_VIEW_NOTES));
				objGenArrangeVO.setModelView(objResultSet.getString(DatabaseConstants.LS404_ORDR_VIEW_NAME));
				
				/**
				 * Added For LSDB_CR-74[Revision Markers] on 01-June-09 by
				 * ps57222 *
				 */
				
				ResultSet objGNNotesSet = (ResultSet) objResultSet
				.getObject(DatabaseConstants.GN_REVISION_MARKER);
				ArrayList arlGNList = new ArrayList();
				while (objGNNotesSet.next()) {
					arlGNList.add(objGNNotesSet
							.getString(DatabaseConstants.REVISION_NUM));
				}
				objGenArrangeVO.setGnNotesRevCode(arlGNList);
				DBHelper.closeSQLObjects(objGNNotesSet, null, null);
				ResultSet objGNRevSet = (ResultSet) objResultSet
				.getObject(DatabaseConstants.REVISION_MARKER);
				ArrayList arlGNRevList = new ArrayList();
				while (objGNRevSet.next()) {
					arlGNRevList.add(objGNRevSet
							.getString(DatabaseConstants.REVISION_NUM));
				}
				objGenArrangeVO.setRevCode(arlGNRevList);
				DBHelper.closeSQLObjects(objGNRevSet, null, null);
				
				//Added for CR-76
				objGenArrangeVO.setImgMarkFlag(objResultSet
						.getString(DatabaseConstants.IMG_SYS_MARKER));
				
				objGenArrangeVO.setNotesMarkFlag(objResultSet
						.getString(DatabaseConstants.GEN_ARR_SYS_MARKER));
				
				objGenArrangeVO.setImgMarkDesc(objResultSet
						.getString(DatabaseConstants.IMG_SYS_MARKER_DESC));
				
				objGenArrangeVO.setNotesMarkDesc(objResultSet
						.getString(DatabaseConstants.GEN_ARR_SYS_MARKER_DESC));
				
				arlArrayList.add(objGenArrangeVO);
				
			}
			
		}
		
		catch (DataAccessException objDataExp) {
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
			LogUtil.logMessage("Enters into Exception block in DAO:");
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
		
		LogUtil
		.logMessage("Exits from OrderGenArrangeDAO:fetchGenArrImages Method ");
		
		return arlArrayList;
		
	}
	//modified For CR_101
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objGenArrangeVO
	 *            the object for Updating Images
	 * @return Integer staing the result of Updation
	 * @throws EMDException
	 **************************************************************************/
	
	public static int updateOrdGenArgmntViewDtls(GenArrangeVO objGenArrangeVO)
	throws EMDException {
		LogUtil.logMessage("Enter into OrderGenArrangeDAO:updateOrdGenArgmntViewDtls method");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ArrayList arlArrayList = new ArrayList();
		int intStatusCode = 0;
		int intInsertNullImage;
		int intImgSeqUpdated;
		StringBuffer strBuffer = null;
		ArrayList arlUpdateList = null;
		String strLogUser = "";
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		try {
			strLogUser = objGenArrangeVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			if(objGenArrangeVO.getFileVO().getFileLength()!=0)
			{
			int intFileSize = objGenArrangeVO.getFileVO().getFileLength();
			LogUtil.logMessage("FileSize in DAO:" + intFileSize);
			
			String strContentType = objGenArrangeVO.getFileVO()
			.getContentType();
			
			int intNextSeqNo = DBHelper.getSequenceNumber(objConnection,
					DatabaseConstants.LS170_IMG_SEQ_Name);
			LogUtil.logMessage("NextSeqNo*******************:" + intNextSeqNo);
			Timestamp objSqlDate = ApplicationUtil.getCurrentTimeStamp();
			LogUtil.logMessage("CurrentDate*******************:" + objSqlDate);
			
			arlArrayList.add(new Integer(intNextSeqNo));
			arlArrayList.add(strContentType);
			arlArrayList.add(objGenArrangeVO.getUserID());
			arlArrayList.add(objSqlDate);
			
			LogUtil
			.logMessage("Enters into insert Empty Image Block of OrderGenArrangeDAO:updateOrdGenArgmntViewDtls");
			intInsertNullImage = DBHelper.executeUpdate(objConnection,
					EMDQueries.Query_EmptyImage, arlArrayList);
			LogUtil
			.logMessage("Exits From insert Empty Image Block of OrderGenArrangeDAO:updateOrdGenArgmntViewDtls:");
			
			LogUtil
			.logMessage("Enters into Update Image Block of OrderGenArrangeDAO:updateOrdGenArgmntViewDtls");
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
				.prepareCall(EMDQueries.SP_UPDATE_ORDR_GEN_ARGMNT_VIEW);
				objCallableStatement.setInt(1, objGenArrangeVO.getModelViewSeqNo());
				LogUtil.logMessage("objGenArrangeVO.getDataLocationType():*******************:"
						+ objGenArrangeVO.getDataLocationType());
				objCallableStatement.setInt(2, objGenArrangeVO.getOrderKey());
				objCallableStatement.setString(3, objGenArrangeVO.getDataLocationType());
				objCallableStatement.setString(4, objGenArrangeVO.getModelView());
				LogUtil.logMessage("ModelView:*******************:"
						+ objGenArrangeVO.getModelView());
				objCallableStatement.setString(5, objGenArrangeVO.getMdlViewNotes());
				if(objGenArrangeVO.getModelImgSeqNo()!=0){
					LogUtil.logMessage("MdlViewNotes:*******************:"
							+ objGenArrangeVO.getMdlViewNotes());
				objCallableStatement.setInt(6, objGenArrangeVO.getModelImgSeqNo());
				}else{
					objCallableStatement.setNull(6, Types.NULL);
				}
				objCallableStatement.setString(7, objGenArrangeVO.getUserID());
				objCallableStatement.registerOutParameter(8, Types.INTEGER);
				objCallableStatement.registerOutParameter(9, Types.VARCHAR);
				objCallableStatement.registerOutParameter(10, Types.VARCHAR);
							
				intImgSeqUpdated = objCallableStatement.executeUpdate();
			LogUtil
			.logMessage(" GenArrByModelDAO:updateOrdGenArgmntViewDtls intImgSeqUpdated"+intImgSeqUpdated);
			
			if (intImgSeqUpdated > 0) {
				intImgSeqUpdated = 0;
			}
			
		
		intLSDBErrorID = (int) objCallableStatement.getInt(8);
		strOracleCode = (String) objCallableStatement.getString(9);
		strErrorMessage = (String) objCallableStatement.getString(10);
		if (intLSDBErrorID != 0) {
			intStatusCode=intLSDBErrorID;
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
		.logMessage("Exits From Update ImgSeqNo Block of GenArrByModelDAO:updateOrdGenArgmntViewDtls"+intStatusCode);
		
	}
			
		catch (DataAccessException objDataExp) {
			LogUtil.logMessage("Enters into DataAccessException exception...");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil.logMessage("Enters into catch block in DAO:.."
					+ objErrorInfo.getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
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
	 * @param objGenArrangeVO
	 *            the object for Updating Notes
	 * @return Integer staing the result of Updation
	 * @throws EMDException
	 **************************************************************************/
	public static int updateNotes(GenArrangeVO objGenArrangeVO)
	throws EMDException {
		LogUtil.logMessage("Enters into OrderGenArrDAO:updateNotes method");
		
		Connection objConnection = null;
		ArrayList arlArrayList = new ArrayList();
		int intStatusCode = 0;
		String strLogUser = "";
		
		arlArrayList.add(objGenArrangeVO.getGenArrangeNotes());
		LogUtil.logMessage("GenArrangeNotes in modifyGenArrNotes:"
				+ objGenArrangeVO.getGenArrangeNotes());
		arlArrayList.add(new Integer(objGenArrangeVO.getOrderKey()));
		LogUtil
		.logMessage("Inside modifyGenArrNotes method in  ModelGenArrangeDAO:(objGenArrangeVO.getOrderKey()):"
				+ objGenArrangeVO.getOrderKey());
		
		try {
			strLogUser = objGenArrangeVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			
			intStatusCode = DBHelper.executeUpdate(objConnection,
					EMDQueries.Query_ORDER_UPDATE_NOTES, arlArrayList);
			LogUtil.logMessage("Return value of executeUpdate Method:"
					+ intStatusCode);
			
			if (intStatusCode > 0) {
				intStatusCode = 0;
			}
			
		} catch (DataAccessException objDataExp) {
			LogUtil.logMessage("Enters into DataAccessException exception...");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil.logMessage("Enters into catch block in DAO:.."
					+ objErrorInfo.getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
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
				
				DBHelper.closeConnection(objConnection);
			}
			
			catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception exception...");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
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
	 *            the object for Updating Images
	 * @return Integer staing the result of Updation
	 * @throws EMDException
	 **************************************************************************/
	
	public static int deleteOrdGenArgmtView(GenArrangeVO objGenArrangeVO)
	throws EMDException {
		
		LogUtil.logMessage("Enter into GenArrByModelDAO:deleteOrdGenArgmtView method");
		
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
			.prepareCall(EMDQueries.SP_DELETE_ORDR_GEN_ARGMNT_VIEW);
			objCallableStatement.setInt(1, objGenArrangeVO.getModelViewSeqNo());
			objCallableStatement.setInt(2, objGenArrangeVO.getOrderKey());
			objCallableStatement.setString(3, objGenArrangeVO.getDataLocationType());
			objCallableStatement.setString(4, objGenArrangeVO.getUserID());
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
						
			intStatus = objCallableStatement.executeUpdate();
			LogUtil
			.logMessage("Inside  method of deleteOrdGenArgmtView :intStatus .."
					+ intStatus);
			if (intStatus > 0) {
				intStatus = 0;
				LogUtil.logMessage("inside intstatus::" + intStatus);
				
			}
			
			intLSDBErrorID = (int) objCallableStatement.getInt(5);
			strOracleCode = (String) objCallableStatement.getString(6);
			strErrorMessage = (String) objCallableStatement.getString(7);
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
			.logMessage("Exits From Update ImgSeqNo Block of GenArrByModelDAO:deleteOrdGenArgmtView");
			
			
			
		}
		
		catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException GenArrByModelDAO:deleteOrdGenArgmtView");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("ENters into DataAccessException catch block in GenArrByModelDAO:deleteOrdGenArgmtView"
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
				.logMessage("ENters into Exception block in GenArrByModelDAO:deleteOrdGenArgmtView"
						+ objinnerExp.getMessage());
				throw new ApplicationException(objinnerExp, objErrorInfo);
				
			}
			LogUtil.logMessage("Enters into Exception exception...");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			LogUtil
			.logMessage("ENters into Exception block in GenArrByModelDAO:deleteOrdGenArgmtView"
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
	 *            the object for Updating Images
	 * @return Integer staing the result of Updation
	 * @throws EMDException
	 **************************************************************************/
	
	public static int uploadOrdGenArgmntViewDtls(GenArrangeVO objGenArrangeVO)
	throws EMDException {
		LogUtil.logMessage("Enter into OrderGenArrangeDAO:uploadOrdGenArgmntViewDtls method");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ArrayList arlArrayList = new ArrayList();
		int intStatusCode = 0;
		int intInsertNullImage;
		int intImgSeqUpdated;
		StringBuffer strBuffer = null;
		ArrayList arlUpdateList = null;
		String strLogUser = "";
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		try {
			strLogUser = objGenArrangeVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			
			
			int intFileSize = objGenArrangeVO.getFileVO().getFileLength();
			LogUtil.logMessage("FileSize in DAO:" + intFileSize);
			
			String strContentType = objGenArrangeVO.getFileVO()
			.getContentType();
			
			int intNextSeqNo = DBHelper.getSequenceNumber(objConnection,
					DatabaseConstants.LS170_IMG_SEQ_Name);
			LogUtil.logMessage("NextSeqNo*******************:" + intNextSeqNo);
			Timestamp objSqlDate = ApplicationUtil.getCurrentTimeStamp();
			LogUtil.logMessage("CurrentDate*******************:" + objSqlDate);
			
			arlArrayList.add(new Integer(intNextSeqNo));
			arlArrayList.add(strContentType);
			arlArrayList.add(objGenArrangeVO.getUserID());
			arlArrayList.add(objSqlDate);
			
			LogUtil
			.logMessage("Enters into insert Empty Image Block of OrderGenArrangeDAO:uploadOrdGenArgmntViewDtls");
			intInsertNullImage = DBHelper.executeUpdate(objConnection,
					EMDQueries.Query_EmptyImage, arlArrayList);
			LogUtil
			.logMessage("Exits From insert Empty Image Block of OrderGenArrangeDAO:uploadOrdGenArgmntViewDtls:");
			
			LogUtil
			.logMessage("Enters into Update Image Block of OrderGenArrangeDAO:uploadOrdGenArgmntViewDtls");
			strBuffer = new StringBuffer();
			strBuffer.append(EMDQueries.Query_UpdateImage + intNextSeqNo);
			String strUpdatequery = strBuffer.toString();
			boolean blnUpdated = DBHelper.executeDatabaseUpdateUpload(
					objConnection, strUpdatequery, objGenArrangeVO.getFileVO());
			LogUtil.logMessage("Updated Image:*******************:"
					+ blnUpdated);
			LogUtil
			.logMessage("Exits From Update Image Block of OrderGenArrangeDAO:uploadOrdGenArgmntViewDtls:");
			
			/*arlUpdateList = new ArrayList();
			arlUpdateList.add(new Integer(intNextSeqNo));
			arlUpdateList.add(objGenArrangeVO.getUserID());
			arlUpdateList.add(objSqlDate);
			arlUpdateList.add(new Integer(objGenArrangeVO.getOrderKey()));
			arlUpdateList.add(new Integer(objGenArrangeVO.getModelViewSeqNo()));
			arlUpdateList.add(DatabaseConstants.DATALOCATION);
			LogUtil
			.logMessage("before update imgseqno OrderGenArrangeDAO:uploadOrdGenArgmntViewDtls:");
						
			intImgSeqUpdated = DBHelper.executeUpdate(objConnection,
					EMDQueries.Query_ORDER_UpdateImgSeqNo, arlUpdateList);*/
			
			objGenArrangeVO.setModelImgSeqNo(intNextSeqNo);
			LogUtil
			.logMessage("before caling SP_INSERT_ORDR_GEN_ARGMNT_VIEW  OrderGenArrangeDAO:uploadOrdGenArgmntViewDtls:");
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_INSERT_ORDR_GEN_ARGMNT_VIEW);
			objCallableStatement.setInt(1, objGenArrangeVO.getOrderKey());
			objCallableStatement.setString(2, objGenArrangeVO.getDataLocationType());
			LogUtil.logMessage("inside objGenArrangeVO.getMdlNewViewName()::" + objGenArrangeVO.getMdlNewViewName());
			if(objGenArrangeVO.getMdlNewViewName()!=null || objGenArrangeVO.getMdlNewViewName()!="" ){
				objCallableStatement.setString(3, objGenArrangeVO.getMdlNewViewName());
				}else{
					objCallableStatement.setNull(3, Types.NULL);
				}
			
			if(objGenArrangeVO.getMdlNewViewNotes()!=null || objGenArrangeVO.getMdlNewViewNotes()!="" ){
				objCallableStatement.setString(4, objGenArrangeVO.getMdlNewViewNotes());
				}else{
					objCallableStatement.setNull(4, Types.NULL);
				}
				
			if(objGenArrangeVO.getModelImgSeqNo()!=0){
				objCallableStatement.setInt(5, objGenArrangeVO.getModelImgSeqNo());
				}else{
					objCallableStatement.setNull(5, Types.NULL);
				}
					
			
			objCallableStatement.setString(6, objGenArrangeVO.getUserID());
			objCallableStatement.registerOutParameter(7, Types.INTEGER);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			objCallableStatement.registerOutParameter(9, Types.VARCHAR);
						
			intImgSeqUpdated = objCallableStatement.executeUpdate();
			LogUtil
			.logMessage("Inside the uploadOrdGenArgmntViewDtls method of GenArrByModelDAO :intStatus .."
					+ intImgSeqUpdated);
			if (intImgSeqUpdated > 0) {
				intImgSeqUpdated = 0;
				LogUtil.logMessage("inside intstatus::" + intImgSeqUpdated);
				
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
			.logMessage("Exits From Update ImgSeqNo Block of GenArrByModelDAO:uploadOrdGenArgmntViewDtls");
		
		
		}
		
		catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException GenArrByModelDAO:uploadOrdGenArgmntViewDtls");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("ENters into DataAccessException catch block in GenArrByModelDAO:uploadOrdGenArgmntViewDtls"
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
				.logMessage("ENters into Exception block in GenArrByModelDAO:uploadOrdGenArgmntViewDtls"
						+ objinnerExp.getMessage());
				throw new ApplicationException(objinnerExp, objErrorInfo);
				
			}
			LogUtil.logMessage("Enters into Exception exception...");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			LogUtil
			.logMessage("ENters into Exception block in GenArrByModelDAO:uploadOrdGenArgmntViewDtls"
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
				.logMessage("ENters into Exception block in GenArrByModelDAO:uploadOrdGenArgmntViewDtls in finally block"
						+ objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return intStatusCode;
	}
	
	
	
}