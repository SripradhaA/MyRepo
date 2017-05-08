/* AK49339
 * Created on Aug 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.dao.MasterMaintenance;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

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
import com.EMD.LSDB.vo.common.CustomerVO;

/*******************************************************************************************
 * @author  Satyam Computer Services Ltd  
 * @version 1.0  
 * This class has the business methods for the Customer
 ******************************************************************************************/
public class CustomerDAO extends EMDDAO {
	/*******************************************************************************************
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	objSectionVO    	The object for fetch Customer  
	 * @return     	Arraylist  			It has Arraylist of CustMaintVO    
	 * @throws     	EMDException
	 ******************************************************************************************/
	private CustomerDAO() {
	}
	
	/**
	 * fetchCustomers,insertCustomer methods are modified based on LSDB_CR-46
	 * One More input parameter is added newly(SpectypeSeqNo)
	 * Modified on 27-Aug-08
	 * by ps57222
	 */
	
	public static ArrayList fetchCustomers(CustomerVO objCustomerVO)
	throws EMDException {
		LogUtil.logMessage("Entering CustomerDAO.fetchCustomers");
		Connection objConnnection = null;
		ArrayList arlCustomerList = new ArrayList();
		CallableStatement objCallableStatement = null;
		ResultSet rsCustomer = null;
		int intLSDBErrorID = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strLogUser = "";
		
		try {
			strLogUser = objCustomerVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in DAO :" + objConnnection);
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_SELECT_CUSTOMER);
			
			//Added for LSDB_CR-76
			if (objCustomerVO.getSpecTypeSeqNo() == 0) {
				
				objCallableStatement.setNull(1, Types.NULL);
				
			} else {
				
				objCallableStatement.setInt(1, objCustomerVO.getSpecTypeSeqNo());
			}
			//Commented out for LSDB_CR-76
			/*if (objCustomerVO.getSpecTypeSeqNo() > 0) {
				
				objCallableStatement
				.setInt(1, objCustomerVO.getSpecTypeSeqNo());
			} else {
				objCallableStatement.setNull(1, Types.NULL);
			}*/
			
			if (objCustomerVO.getCustTypeSeqNo() > 0) {
				objCallableStatement
				.setInt(2, objCustomerVO.getCustTypeSeqNo());
			} else {
				objCallableStatement.setNull(2, Types.NULL);
			}
			objCallableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			
			objCallableStatement.setString(4, objCustomerVO.getUserID());
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.execute();
			rsCustomer = (ResultSet) objCallableStatement.getObject(3);
			LogUtil
			.logMessage("Inside the fetchCustomers method of CustomerDAO :resultSet"
					+ rsCustomer);
			intLSDBErrorID = (int) objCallableStatement.getInt(5);
			strOracleCode = (String) objCallableStatement.getString(6);
			strErrorMessage = (String) objCallableStatement.getString(7);
			//Handled Valid Exception
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Enters into Error Loop:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessageID("" + intLSDBErrorID);
				LogUtil.logMessage("Error message in ErrorInfo:"
						+ objErrorInfo.getMessageID());
				throw new DataAccessException(objErrorInfo);
				
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
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			while (rsCustomer.next()) {
				LogUtil.logMessage("inside resultset while");
				objCustomerVO = new CustomerVO();
				objCustomerVO.setCustomerSeqNo(rsCustomer
						.getInt(DatabaseConstants.CUSTOMER_SEQ_NO));
				objCustomerVO.setCustomerName(rsCustomer
						.getString(DatabaseConstants.CUSTOMER_NAME));
				objCustomerVO.setCustomerDesc(rsCustomer
						.getString(DatabaseConstants.CUSTOMER_DESC));
								//Added for CR_106 customer Logo Changes JG101007
				
				objCustomerVO.getFileVO().setImageSeqNo(rsCustomer
				.getInt(DatabaseConstants.LS050_CUST_IMG_SEQ_NO));
				objCustomerVO.setImageSeqNo(rsCustomer
						.getInt(DatabaseConstants.LS050_CUST_IMG_SEQ_NO));
				LogUtil.logMessage("The Sequence Number during Fetch is"
						+objCustomerVO.getImageSeqNo() );
				
				/*objCustomerVO.getFileVO().setContentType(
						rsCustomer.getString(DatabaseConstants.LS170_IMG_CONTENT_TYPE));*/
				objCustomerVO.setFirstName(rsCustomer.getString(DatabaseConstants.LS010_FIRSTNAME));
				objCustomerVO.setLastName(rsCustomer.getString(DatabaseConstants.LS010_LASTNAME));
				objCustomerVO.setLogoUpdatedDate(rsCustomer.getString(DatabaseConstants.LS050_IMG_UPDT_DATE));
				//CR_106 customer Logo Changes ends here
				arlCustomerList.add(objCustomerVO);
			}
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into DataAccess Exception block in CustomerDAO.fetchCustomers"
					+ objDataExp.getErrorInfo().getMessageID());
			objErrorInfo.setMessage(objDataExp.getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in CustomerDAO.fetchCustomers"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in CustomerDAO.fetchCustomers"
					+ objExp.getMessage());
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(rsCustomer, objCallableStatement,
						objConnnection);
			} catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception block in DAO:"
						+ objExp.getMessage());
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return arlCustomerList;
		
	}
	
	/*******************************************************************************************
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	objSectionVO    The Object for Insert Customer  
	 * @return     	boolean  		The Flag for success or failure   
	 * @throws     	EMDException
	 ******************************************************************************************/
	public static int insertCustomer(CustomerVO objCustomerVO)
	throws EMDException {
		LogUtil.logMessage("Inside the insertCustomer method of CustomerDAO");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		int intInserted;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		int intNextSeqNo = 0;
		String strLogUser = "";
		StringBuffer strBuffer = null;
		ArrayList arlImagedetlsList = new ArrayList();
		try {
			strLogUser = objCustomerVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			//Added for CR_106 customer logo JG101007
			int intFileSize = objCustomerVO.getFileVO().getFileLength();
			LogUtil.logMessage("FileSize in DAO:" + intFileSize);
			if(intFileSize > 0){
				String strContentType = objCustomerVO.getFileVO()
				.getContentType();
				LogUtil.logMessage("Next Step in getting Sequence");
				intNextSeqNo = DBHelper.getSequenceNumber(objConnection,
						DatabaseConstants.LS170_IMG_SEQ_Name);
				Timestamp objSqlDate = ApplicationUtil.getCurrentTimeStamp();
				
				// For insert into images table - starts here
				LogUtil
				.logMessage("Enters into insert Empty Image Block of CustomerDAO insertCustomer");
				arlImagedetlsList.add(new Integer(intNextSeqNo));
				arlImagedetlsList.add(strContentType);
				arlImagedetlsList.add(objCustomerVO.getUserID());
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
						objConnection, strUpdatequery, objCustomerVO
						.getFileVO());
				
				if (blnUpdated != true) {
					
					strOracleCode = "Error in Image Update";
					
				}
			}
			// For inserting into images table - Ends here
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_INSERT_CUSTOMER);
			objCallableStatement.setInt(1, objCustomerVO.getSpecTypeSeqNo());
			objCallableStatement.setString(2, objCustomerVO.getCustomerName());
			objCallableStatement.setString(3, objCustomerVO.getCustomerDesc());
			objCallableStatement.setInt(4, objCustomerVO.getCustTypeSeqNo());
			objCallableStatement.setString(5, objCustomerVO.getUserID());
			
			if (intNextSeqNo != 0)
				objCallableStatement.setInt(6, intNextSeqNo);
			else
				objCallableStatement.setNull(6, Types.NULL);
			LogUtil
			.logMessage("IntNextSeqNO" + intNextSeqNo);
			//CR_106 Changes ends here
			objCallableStatement.registerOutParameter(7, Types.INTEGER);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			objCallableStatement.registerOutParameter(9, Types.VARCHAR);
			objConnection.setAutoCommit(false);
			intStatus = objCallableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
				
			}
			
			LogUtil
			.logMessage("Inside the insertCustomer method of CustomerDAO :intStatus .."
					+ intStatus);
			intLSDBErrorID = (int) objCallableStatement.getInt(7);
			strOracleCode = (String) objCallableStatement.getString(8);
			strErrorMessage = (String) objCallableStatement.getString(9);
			
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Enters into Error Loop:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessageID("" + intLSDBErrorID);
				LogUtil.logMessage("Error message in ErrorInfo:"
						+ objErrorInfo.getMessageID());
				throw new DataAccessException(objErrorInfo);
				
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
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into DataAccess Exception block in CustomerDAO.insertCustomer"
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  CustomerDAO.insertCustomer"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in CustomerDAO.insertCustomer"
					+ objExp.getMessage());
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
				.logMessage("Enters into Exception block in DAO:Close Connection"
						+ objExp.getMessage());
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return intStatus;
		
	}
	
	/*******************************************************************************************
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	objSectionVO    The Object for Update Customer  
	 * @return     	boolean  		The Flag for success or failure   
	 * @throws     	EMDException
	 ******************************************************************************************/
	public static int updateCustomer(CustomerVO objCustomerVO)
	throws EMDException {
		LogUtil.logMessage("Entering CustomerDAO.updateCustomer");
		Connection objConnection = null;
		List arlCustomerList = new ArrayList();

		ArrayList arlImagedetlsList = new ArrayList();
		CallableStatement objCallableStatement = null;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		int intStatus = 0;
		int intInserted;
		int intNextSeqNo;
		String strLogUser = "";
		try {
			strLogUser = objCustomerVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			// Added for CR_106 customer Logo JG101007
			int intFileSize = objCustomerVO.getFileVO().getFileLength();
			if(intFileSize > 0){
				
				if (objCustomerVO.getImageSeqNo() == 0){
				
					String strContentType = objCustomerVO.getFileVO()
					.getContentType();
					LogUtil.logMessage("Next Step in getting Sequence");
					intNextSeqNo = DBHelper.getSequenceNumber(objConnection,
							DatabaseConstants.LS170_IMG_SEQ_Name);
					Timestamp objSqlDate = ApplicationUtil.getCurrentTimeStamp();
					
					// For insert into images table - starts here
				
					arlImagedetlsList.add(new Integer(intNextSeqNo));
					arlImagedetlsList.add(strContentType);
					arlImagedetlsList.add(objCustomerVO.getUserID());
					arlImagedetlsList.add(objSqlDate);
				
					intInserted = DBHelper.executeUpdate(objConnection,
							EMDQueries.Query_EmptyImage, arlImagedetlsList);
				
					
					if (intInserted == 0) {
						strOracleCode = "Error in Image Insert";
					}
										
										
					objCustomerVO.setImageSeqNo(intNextSeqNo);
				}
				
			StringBuffer strBuffer = new StringBuffer();
			strBuffer.append(EMDQueries.Query_UpdateImage + objCustomerVO.getImageSeqNo());
			
			String strUpdatequery = strBuffer.toString();
			boolean blnUpdated = DBHelper.executeDatabaseUpdateUpload(
					objConnection, strUpdatequery, objCustomerVO
					.getFileVO());
			
			if (blnUpdated != true) {
				
				strOracleCode = "Error in Image Update";
				
				}
			}
			
							
				
				
			objCallableStatement = objConnection
			
			.prepareCall(EMDQueries.SP_UPDATE_CUSTOMER);
			objCallableStatement.setInt(1, objCustomerVO.getCustomerSeqNo());
			objCallableStatement.setString(2, objCustomerVO.getCustomerName());
			objCallableStatement.setString(3, objCustomerVO.getCustomerDesc());
			objCallableStatement.setInt(4, objCustomerVO.getCustTypeSeqNo());
			
			objCallableStatement.setString(5, objCustomerVO.getUserID());
			
			// Added for CR_106 customer Logo JG101007
			if (objCustomerVO.getImageSeqNo() != 0)
				objCallableStatement.setInt(6,objCustomerVO.getImageSeqNo() );
			else
				objCallableStatement.setNull(6, Types.NULL);
										
			objCallableStatement.registerOutParameter(7, Types.INTEGER);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			objCallableStatement.registerOutParameter(9, Types.VARCHAR);
			intStatus = objCallableStatement.executeUpdate();
				
		
			
			
				
				
				
				
			if (intStatus > 0) {
				intStatus = 0;
				LogUtil.logMessage("inside intstatus::" + intStatus);
			
				
			}
			
			//CR_106 ends here
			intLSDBErrorID = (int) objCallableStatement.getInt(7);
			strOracleCode = (String) objCallableStatement.getString(8);
			strErrorMessage = (String) objCallableStatement.getString(9);
			
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Enters into Error Loop:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessageID("" + intLSDBErrorID);
				LogUtil.logMessage("Error message in ErrorInfo:"
						+ objErrorInfo.getMessageID());
				throw new DataAccessException(objErrorInfo);
				
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
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into DataAccess Exception block in CustomerDAO.updateCustomer"
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  CustomerDAO.updateCustomer"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in CustomerDAO.updateCustomer"
					+ objExp.getMessage());
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(null, objCallableStatement,
						objConnection);
			} catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception block in DAO:"
						+ objExp.getMessage());
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return intStatus;
		
	}
	
}
