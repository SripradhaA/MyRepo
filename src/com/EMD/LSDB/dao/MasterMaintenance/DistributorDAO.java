package com.EMD.LSDB.dao.MasterMaintenance;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.dao.common.DBHelper;
import com.EMD.LSDB.dao.common.EMDDAO;
import com.EMD.LSDB.dao.common.EMDQueries;
import com.EMD.LSDB.vo.common.DistributorVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business methods for the Distributor
 ******************************************************************************/
public class DistributorDAO extends EMDDAO {
	
	private DistributorDAO() {
	}
	
	/***************************************************************************
	 * This Method is used to fetch the Distributor Details
	 * 
	 * @param objDistributorVo
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/
	public static ArrayList fetchDistributors(DistributorVO objDistributorVo)
	throws EMDException {
		LogUtil.logMessage("Entering DistributorDAO.fetch");
		Connection objConnnection = null;
		ArrayList arlDistributor = new ArrayList();
		CallableStatement objCallableStatement = null;
		// Error out parameters
		ResultSet objDistResultSet = null;
		int intLSDBErrorID = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strLogUser = "";
		try {
			strLogUser = objDistributorVo.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in DAO :" + objConnnection);
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_SELECT_DISTRIBUTOR);
			
			objCallableStatement.setString(1, objDistributorVo.getUserID());
			objCallableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(3, Types.VARCHAR);
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			objDistResultSet = (ResultSet) objCallableStatement.getObject(2);
			
			LogUtil
			.logMessage("Inside the fetchDistributor method of DistributorDAO :resultSet"
					+ objDistResultSet);
			
			intLSDBErrorID = (int) objCallableStatement.getInt(3);
			strOracleCode = (String) objCallableStatement.getString(4);
			strErrorMessage = (String) objCallableStatement.getString(5);
			
			// Handled Valid Exception
			if (intLSDBErrorID != 0) {
				
				ErrorInfo objErrorInfo = new ErrorInfo();
				
				objErrorInfo.setMessageID("" + intLSDBErrorID);
				
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
			
			while (objDistResultSet.next()) {
				objDistributorVo = new DistributorVO();
				objDistributorVo.setDistributorSeqNo(objDistResultSet
						.getInt(DatabaseConstants.DIST_SEQ_NO));
				objDistributorVo.setDistributorName(objDistResultSet
						.getString(DatabaseConstants.DIST_NAME));
				objDistributorVo.setDistributorDesc(objDistResultSet
						.getString(DatabaseConstants.DIST_DESC));
		//Added for CR_106 customer Logo Changes JG101007
				
				objDistributorVo.getFileVO().setImageSeqNo(objDistResultSet
				.getInt(DatabaseConstants.LS070_DIST_IMG_SEQ_NO));
				objDistributorVo.setImageSeqNo(objDistResultSet
						.getInt(DatabaseConstants.LS070_DIST_IMG_SEQ_NO));
				LogUtil.logMessage("The Sequence Number during Fetch is"
						+objDistributorVo.getImageSeqNo() );
				
				/*objCustomerVO.getFileVO().setContentType(
						rsCustomer.getString(DatabaseConstants.LS170_IMG_CONTENT_TYPE));*/
				objDistributorVo.setFirstName(objDistResultSet.getString(DatabaseConstants.LS010_FIRSTNAME));
				objDistributorVo.setLastName(objDistResultSet.getString(DatabaseConstants.LS010_LASTNAME));
				objDistributorVo.setLogoUpdatedDate(objDistResultSet.getString(DatabaseConstants.LS070_IMG_UPDT_DATE));
				//CR_106 customer Logo Changes ends here
				arlDistributor.add(objDistributorVo);
			}
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil.logMessage("ENters into catch block in DAO:.."
					+ objDataExp.getErrorInfo().getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil.logMessage("Enters into AppException block in  DAO :"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception block in DAO:");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(objDistResultSet,
						objCallableStatement, objConnnection);
			} catch (SQLException sqlex) {
				LogUtil.logMessage("Enters into Exception block in DAO:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + sqlex.getMessage());
				throw new ApplicationException(sqlex, objErrorInfo);
			} catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception block in DAO:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return arlDistributor;
		
	}
	
	/***************************************************************************
	 * This Method is used to insert a new Distributor.
	 * 
	 * @param objDistributorVo
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	public static int insertDistributor(DistributorVO objDistributorVo)
	throws EMDException {
		LogUtil.logMessage("Entering DistributorDAO.insert");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		int intInserted;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		int intNextSeqNo= 0;
		StringBuffer strBuffer = null;
		ArrayList arlImagedetlsList = new ArrayList();
		String strLogUser = "";
		try {
			strLogUser = objDistributorVo.getUserID();
			objConnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in DAO in Insert Method:"
					+ objConnection);
//			Added for CR_106 customer logo JG101007
			int intFileSize = objDistributorVo.getFileVO().getFileLength();
			LogUtil.logMessage("FileSize in DAO:" + intFileSize);
			if(intFileSize > 0){
				String strContentType = objDistributorVo.getFileVO()
				.getContentType();
				LogUtil.logMessage("Next Step in getting Sequence");
				
				intNextSeqNo = DBHelper.getSequenceNumber(objConnection,
						DatabaseConstants.LS170_IMG_SEQ_Name);
				LogUtil.logMessage("^^^^^^^^^^^^^^IntNextSeqNO^^^^^^^^^^^^^^= " + intNextSeqNo);
				Timestamp objSqlDate = ApplicationUtil.getCurrentTimeStamp();
				// For insert into images table - starts here
				LogUtil
				.logMessage("Enters into insert Empty Image Block of CustomerDAO insertCustomer");
				arlImagedetlsList.add(new Integer(intNextSeqNo));
				arlImagedetlsList.add(strContentType);
				arlImagedetlsList.add(objDistributorVo.getUserID());
				arlImagedetlsList.add(objSqlDate);
				LogUtil
				.logMessage("Enters into Executing inline Query");
				intInserted = DBHelper.executeUpdate(objConnection,
						EMDQueries.Query_EmptyImage, arlImagedetlsList);
				LogUtil
				.logMessage("Inserted Status" +intInserted );
				
				if (intInserted == 0) {
					strOracleCode = "Error in Image Insert";
				}
				LogUtil.logMessage("Insert Status of empty Image" + intInserted);
				
				LogUtil
				.logMessage("Enters into Update Image Block of CustomerDAO insertCustomer");
				
				 strBuffer = new StringBuffer();
				strBuffer.append(EMDQueries.Query_UpdateImage + intNextSeqNo);
				LogUtil
				.logMessage("Sequence Number" + intNextSeqNo);
				String strUpdatequery = strBuffer.toString();
				boolean blnUpdated = DBHelper.executeDatabaseUpdateUpload(
						objConnection, strUpdatequery, objDistributorVo
						.getFileVO());
				
				if (blnUpdated != true) {
					
					strOracleCode = "Error in Image Update";
					
				}
			}
			// For inserting into images table - Ends here
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_INSERT_DISTRIBUTOR);
			
			objCallableStatement.setString(1, objDistributorVo
					.getDistributorName());
			objCallableStatement.setString(2, objDistributorVo
					.getDistributorDesc());
			objCallableStatement.setString(3, objDistributorVo.getUserID());
			if (intNextSeqNo!= 0)
				objCallableStatement.setInt(4, intNextSeqNo);
			else
				objCallableStatement.setNull(4, Types.NULL);
			LogUtil
			.logMessage("IntNextSeqNO" + intNextSeqNo);
			LogUtil
			.logMessage("Sequence Number" + objDistributorVo.getImageSeqNo());
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			
			if (intStatus > 0) {
				
				intStatus = 0;
			}
			
			LogUtil
			.logMessage("Inside the insert method of DistributorDAO :intStatus .."
					+ intStatus);
			intLSDBErrorID = (int) objCallableStatement.getInt(5);
			strOracleCode = (String) objCallableStatement.getString(6);
			strErrorMessage = (String) objCallableStatement.getString(7);
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Error ID:" + intLSDBErrorID);
				LogUtil.logMessage("strOracleCode:" + strOracleCode);
				
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
			
		}
		
		catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil.logMessage("ENters into catch block in DAO:.."
					+ objDataExp.getErrorInfo().getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil.logMessage("Enters into AppException block in  DAO :"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception block in DAO:");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				DBHelper.closeSQLObjects(null, objCallableStatement,
						objConnection);
			} catch (SQLException sqlex) {
				LogUtil.logMessage("Enters into Exception block in DAO:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + sqlex.getMessage());
				throw new ApplicationException(sqlex, objErrorInfo);
			} catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception block in DAO:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return intStatus;
		
	}
	
	/***************************************************************************
	 * This Method is used to Modify Distributor Details
	 * 
	 * @param objDistributorVo
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	public static int updateDistributor(DistributorVO objDistributorVo)
	throws EMDException {
		LogUtil.logMessage("Entering DistributorDAO.update");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ArrayList arlImagedetlsList = new ArrayList();
		int intStatus = 0;
		int intInserted;
		int intNextSeqNo;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		 StringBuffer strBuffer=null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		try {
			strLogUser = objDistributorVo.getUserID();
			objConnection = DBHelper.prepareConnection();
			
			int intFileSize = objDistributorVo.getFileVO().getFileLength();
			if(intFileSize > 0){
				if (objDistributorVo.getImageSeqNo() == 0){
					
					String strContentType = objDistributorVo.getFileVO()
					.getContentType();
					
					intNextSeqNo = DBHelper.getSequenceNumber(objConnection,
							DatabaseConstants.LS170_IMG_SEQ_Name);
					Timestamp objSqlDate = ApplicationUtil.getCurrentTimeStamp();
					
					// For insert into images table - starts here
					
					arlImagedetlsList.add(new Integer(intNextSeqNo));
					arlImagedetlsList.add(strContentType);
					arlImagedetlsList.add(objDistributorVo.getUserID());
					arlImagedetlsList.add(objSqlDate);
					
					intInserted = DBHelper.executeUpdate(objConnection,
							EMDQueries.Query_EmptyImage, arlImagedetlsList);
					
					
					if (intInserted == 0) {
						strOracleCode = "Error in Image Insert";
					}
					
					
					objDistributorVo.setImageSeqNo(intNextSeqNo);
				}
				 strBuffer = new StringBuffer();
				strBuffer.append(EMDQueries.Query_UpdateImage + objDistributorVo.getImageSeqNo());
				
				String strUpdatequery = strBuffer.toString();
				boolean blnUpdated = DBHelper.executeDatabaseUpdateUpload(
						objConnection, strUpdatequery, objDistributorVo
						.getFileVO());
				
				if (blnUpdated != true) {
					
					strOracleCode = "Error in Image Update";
					
					}
				}
				
			
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_UPDATE_DISTRIBUTOR);
			
			objCallableStatement.setInt(1, objDistributorVo
					.getDistributorSeqNo());
			objCallableStatement.setString(2, objDistributorVo
					.getDistributorName());
			objCallableStatement.setString(3, objDistributorVo
					.getDistributorDesc());
			objCallableStatement.setString(4, objDistributorVo.getUserID());
			//Added for CR_106 customer Logo JG101007
			if (objDistributorVo.getImageSeqNo() != 0)
				objCallableStatement.setInt(5,objDistributorVo.getImageSeqNo() );
			
			else
				objCallableStatement.setNull(5, Types.NULL);
			LogUtil.logMessage("Next Step in getting Sequence"+objDistributorVo.getImageSeqNo()) ;
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			intStatus = objCallableStatement.executeUpdate();
			
				
			if (intStatus > 0) {
				intStatus = 0;
				LogUtil.logMessage("inside intstatus::" + intStatus);
			
				
			}
			
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			LogUtil
			.logMessage("Inside the Update method of DistributorDAO :intStatus .."
					+ intStatus);
			intLSDBErrorID = (int) objCallableStatement.getInt(6);
			strOracleCode = (String) objCallableStatement.getString(7);
			strErrorMessage = (String) objCallableStatement.getString(8);
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Error ID:" + intLSDBErrorID);
				LogUtil.logMessage("strOracleCode:" + strOracleCode);
				
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
			
		}
		
		catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil.logMessage("ENters into catch block in DAO:.."
					+ objDataExp.getErrorInfo().getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil.logMessage("Enters into AppException block in  DAO :"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception block in DAO:");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				DBHelper.closeSQLObjects(null, objCallableStatement,
						objConnection);
			} catch (SQLException sqlex) {
				LogUtil.logMessage("Enters into Exception block in DAO:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + sqlex.getMessage());
				throw new ApplicationException(sqlex, objErrorInfo);
			} catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception block in DAO:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return intStatus;
		
	}
	
	/***************************************************************************
	 * This Method is used to Delete Distributor Details For CR-55
	 * 
	 * @param objDistributorVo
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	public static int deleteDistributor(DistributorVO objDistributorVo)
	throws EMDException {
		LogUtil.logMessage("Entering DistributorDAO.delete");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		try {
			strLogUser = objDistributorVo.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in DAO in delete Method:"
					+ objConnnection);
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_DELETE_DISTRIBUTOR);
			
			objCallableStatement.setInt(1, objDistributorVo
					.getDistributorSeqNo());
			objCallableStatement.setString(2, objDistributorVo.getUserID());
			objCallableStatement.registerOutParameter(3, Types.INTEGER);
			objCallableStatement.registerOutParameter(4, Types.VARCHAR);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			LogUtil
			.logMessage("Inside the Delete method of DistributorDAO :intStatus .."
					+ intStatus);
			intLSDBErrorID = (int) objCallableStatement.getInt(3);
			strOracleCode = (String) objCallableStatement.getString(4);
			strErrorMessage = (String) objCallableStatement.getString(5);
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Error ID:" + intLSDBErrorID);
				LogUtil.logMessage("strOracleCode:" + strOracleCode);
				
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
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
		}
		
		catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil.logMessage("ENters into catch block in DAO:.."
					+ objDataExp.getErrorInfo().getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil.logMessage("Enters into AppException block in  DAO :"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception block in DAO:");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				DBHelper.closeSQLObjects(null, objCallableStatement,
						objConnnection);
			} catch (SQLException sqlex) {
				LogUtil.logMessage("Enters into Exception block in DAO:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + sqlex.getMessage());
				throw new ApplicationException(sqlex, objErrorInfo);
			} catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception block in DAO:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return intStatus;
		
	}
	
}
