/*
 * Created on Apr 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
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
import com.EMD.LSDB.vo.common.SpecificationItemVO;
import com.EMD.LSDB.vo.common.SpecificationVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business methods for the Model Specifications
 ******************************************************************************/

public class ModelSpecificationDAO extends EMDDAO {
	
	private ModelSpecificationDAO() {
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objModelVO
	 *            the object for searching specification items
	 * @return arrayList the list contains the models
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList fetchSpecificationItems(
			SpecificationVO objSpecificationVO) throws EMDException {
		LogUtil
		.logMessage("Entering ModelSpecificationDAO.fetchSpecificationItems");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		// Error out parameters
		ResultSet objResultSetSpec = null;
		int intLSDBErrorID = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		SpecificationItemVO objSpecificationitemVO = null;
		SpecificationVO objSpecVO = null;
		ArrayList arlSpecWholeList = new ArrayList();
		ArrayList arlSpecItemList = new ArrayList();
		ResultSet rsSpecItem = null;
		String strLogUser = "";
		try {
			
			strLogUser = objSpecificationVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			
			LogUtil.logMessage("objConnnection in DAO :" + objConnnection);
			
			LogUtil
			.logMessage("objModelVO.getSpecTypeSeqNo() in fetchSpecificationItems DAO"
					+ objSpecificationVO.getSpecSeqNo());
			
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_SELECT_MDL_SPEC_ITEM);
			
			objCallableStatement.setInt(1, objSpecificationVO.getModelSeqNo());
			objCallableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			objCallableStatement.setString(3, objSpecificationVO.getUserID());
			
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			objResultSetSpec = (ResultSet) objCallableStatement.getObject(2);
			LogUtil
			.logMessage("Inside the fetchSpecificationItems method of ModelSpecificationDAO :resultSet"
					+ objResultSetSpec);
			
			intLSDBErrorID = (int) objCallableStatement.getInt(4);
			strOracleCode = (String) objCallableStatement.getString(5);
			strErrorMessage = (String) objCallableStatement.getString(6);
			
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
			
			while (objResultSetSpec.next()) {
				objSpecVO = new SpecificationVO();
				arlSpecItemList = new ArrayList();
				objSpecVO.setSpecSeqNo(objResultSetSpec
						.getInt(DatabaseConstants.LS205_MDL_SPEC_SEQ_NO));
				objSpecVO.setSpecName(objResultSetSpec
						.getString(DatabaseConstants.LS205_SPEC_DESC));
				
				// Added for LSDB_CR-64 by ka57588
				objSpecVO.setHpDesc(objResultSetSpec
						.getString(DatabaseConstants.HORSE_POWER_RATE));
				// Ends
				rsSpecItem = (ResultSet) objResultSetSpec.getObject(3);
				
				LogUtil.logMessage("objRsSpecItem:" + rsSpecItem);
				
				while (rsSpecItem.next()) {
					
					objSpecificationitemVO = new SpecificationItemVO();
					objSpecificationitemVO.setModSpecItemSeqNo(rsSpecItem
							.getInt(DatabaseConstants.LS206_MDL_ITEM_SEQ_NO));
					
					objSpecificationitemVO.setModSpecItemDesc(rsSpecItem
							.getString(DatabaseConstants.LS206_ITEM_DESC));
					objSpecificationitemVO.setModSpecItemValue(rsSpecItem
							.getString(DatabaseConstants.LS206_ITEM_VALUE));
					arlSpecItemList.add(objSpecificationitemVO);
					
				}
				objSpecVO.setSpecItem(arlSpecItemList);
				arlSpecWholeList.add(objSpecVO);
			}
			LogUtil.logMessage("objSpecWholeList in DAO :"
					+ arlSpecWholeList.size());
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into catch block in ModelSpecificationDAO.fetchSpecificationItems"
					+ objDataExp.getErrorInfo().getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ModelSpecificationDAO.fetchSpecificationItems"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelSpecificationDAO.fetchSpecificationItems");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
			
		} finally {
			try {
				if (rsSpecItem != null)
					rsSpecItem.close();
				DBHelper.closeSQLObjects(objResultSetSpec,
						objCallableStatement, objConnnection);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in ModelSpecificationDAO.fetchSpecificationItems");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
				
			}
			
		}
		
		return arlSpecWholeList;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objModelVO
	 *            the object for inserting spec
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int insertSpecification(SpecificationVO objSpecificationVO)
	throws EMDException {
		LogUtil
		.logMessage("Entering ModelSpecificationDAO.insertSpecification");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		boolean blnStatus = false;
		int intStatus = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		int intSpecItem = 0;
		String strLogUser = "";
		try {
			
			strLogUser = objSpecificationVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_INSERT_MDL_SPEC);
			objCallableStatement.setString(1, objSpecificationVO.getSpecName());
			
			LogUtil.logMessage("objSpecificationVO.getSpecName().."
					+ objSpecificationVO.getSpecName());
			objCallableStatement.setInt(2, objSpecificationVO.getModelSeqNo());
			
			LogUtil.logMessage("objSpecificationVO.getModSeqNo().."
					+ objSpecificationVO.getModelSeqNo());
			objCallableStatement.setString(3, objSpecificationVO.getUserID());
			
			LogUtil.logMessage("objSpecificationVO.getUserID().."
					+ objSpecificationVO.getUserID());
			
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			
			if (intStatus > 0) {
				
				int intModSpecSeqNo = (int) objCallableStatement.getInt(4);
				
				LogUtil.logMessage("After inserting LSDB205_MDL_SPEC .."
						+ intModSpecSeqNo);
				
				if (intModSpecSeqNo!=0) {   // If loop is added for CR-121
				
					objSpecificationVO.setSpecSeqNo(intModSpecSeqNo);
					intSpecItem = insertSpecificationItem(objSpecificationVO,
							objConnnection, false);
					
					if (intSpecItem == 0) {
						intStatus = 0;
					}
				}
				
				LogUtil.logMessage("isStatus .." + intStatus);
			}
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
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
		}
		
		catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into catch block in ModelSpecificationDAO.insertSpecification"
					+ objDataExp.getErrorInfo().getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ModelSpecificationDAO.insertSpecification"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelSpecificationDAO.insertSpecification");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
			
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(null, objCallableStatement,
						objConnnection);
			}
			
			catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in ModelSpecificationDAO.insertSpecification");
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
	 * @param objModelVO
	 *            the object for inserting spec item
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int insertSpecificationItem(
			SpecificationVO objSpecificationVO, Connection objConnnection,
			boolean blnFlag) throws EMDException {
		LogUtil
		.logMessage("Entering ModelSpecificationDAO.insertSpecificationItem");
		CallableStatement objCallableStatement = null;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		int intStatus = 0;
		String strLogUser = "";
		try {
			
			if (blnFlag) {
				objConnnection = DBHelper.prepareConnection();
			}
			strLogUser = objSpecificationVO.getUserID();
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_INSERT_MDL_SPEC_ITEM);
			
			objCallableStatement.setInt(1, objSpecificationVO.getSpecSeqNo());
			
			LogUtil.logMessage("objSpecificationVO.getSpecSeqNo() .."
					+ objSpecificationVO.getSpecSeqNo());
			objCallableStatement.setString(2, objSpecificationVO
					.getSpecificationItem().getModSpecItemDesc());
			LogUtil
			.logMessage("objSpecificationVO.getSpecificationItem().getModSpecItemDesc() .."
					+ objSpecificationVO.getSpecificationItem()
					.getModSpecItemDesc());
			
			objCallableStatement.setString(3, objSpecificationVO
					.getSpecificationItem().getModSpecItemValue());
			
			LogUtil
			.logMessage("objSpecificationVO.getSpecificationItem().getModSpecItemValue() .."
					+ objSpecificationVO.getSpecificationItem()
					.getModSpecItemValue());
			
			objCallableStatement.setString(4, objSpecificationVO.getUserID());
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			intStatus = objCallableStatement.executeUpdate();
			
			LogUtil
			.logMessage("Inside the insertSpecificationItem method of ModelSpecificationDAO :intStatus .."
					+ intStatus);
			if (intStatus > 0) {
				intStatus = 0;
				
			}
			
			intLSDBErrorID = (int) objCallableStatement.getInt(5);
			strOracleCode = (String) objCallableStatement.getString(6);
			strErrorMessage = (String) objCallableStatement.getString(7);
			
			if (intLSDBErrorID != 0) {
				
				LogUtil.logMessage("Error ID:" + intLSDBErrorID);
				
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
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into catch block in ModelSpecificationDAO.insertSpecificationItem"
					+ objDataExp.getErrorInfo().getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ModelSpecificationDAO.insertSpecificationItem"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelSpecificationDAO.insertSpecificationItem");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
			
		}
		
		finally {
			try {
				
				if (blnFlag)
					DBHelper.closeSQLObjects(null, objCallableStatement,
							objConnnection);
				else
					DBHelper.closeSQLObjects(null, objCallableStatement, null);
				
			}
			
			catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in ModelSpecificationDAO.insertSpecificationItem");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
				
			}
			
		}
		
		return intStatus;
	}
	
	/***************************************************************************
	 * @author ka57588 Added for LSDB_CR-64 This method is used for updating
	 *         Specification
	 * @version 1.0
	 * @param objSpecificationVO
	 *            the object for specification
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int updateSpecification(SpecificationVO objSpecificationVO)
	throws EMDException {
		LogUtil
		.logMessage("Entering ModelSpecificationDAO.updateSpecification");
		Connection objConnnection = null;
		CallableStatement callableStatement = null;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		int intStatus = 0;
		String strLogUser = "";
		try {
			
			strLogUser = objSpecificationVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			
			callableStatement = objConnnection
			.prepareCall(EMDQueries.SP_MDL_UPDATE_SPEC);
			
			callableStatement.setInt(1, objSpecificationVO.getSpecSeqNo());
			callableStatement.setString(2, objSpecificationVO.getSpecName());
			callableStatement.setInt(3, objSpecificationVO.getModelSeqNo());
			callableStatement.setString(4, objSpecificationVO.getUserID());
			
			callableStatement.registerOutParameter(5, Types.INTEGER);
			callableStatement.registerOutParameter(6, Types.VARCHAR);
			callableStatement.registerOutParameter(7, Types.VARCHAR);
			intStatus = callableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			intLSDBErrorID = (int) callableStatement.getInt(5);
			strOracleCode = (String) callableStatement.getString(6);
			strErrorMessage = (String) callableStatement.getString(7);
			
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
				throw new ApplicationException(objErrorInfo);
			}
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into catch block in ModelSpecificationDAO.updateSpecification"
					+ objDataExp.getErrorInfo().getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ModelSpecificationDAO.updateSpecification"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelSpecificationDAO.updateSpecification");
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
				.logMessage("Enters into Exception block in ModelSpecificationDAO.updateSpecification");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
				
			}
			
		}
		return intStatus;
		
	}
	
	/***************************************************************************
	 * @author ka57588 Added for LSDB_CR-64 This method is used for deleting
	 *         description
	 * @version 1.0
	 * @param objSpecificationVO
	 *            the object for specification
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int deleteSpecification(SpecificationVO objSpecificationVO)
	throws EMDException {
		LogUtil
		.logMessage("Entering ModelSpecificationDAO.deleteSpecification");
		Connection objConnnection = null;
		CallableStatement callableStatement = null;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		int intStatus = 0;
		String strLogUser = "";
		try {
			
			strLogUser = objSpecificationVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			
			callableStatement = objConnnection
			.prepareCall(EMDQueries.SP_MDL_DELETE_SPEC);
			
			callableStatement.setInt(1, objSpecificationVO.getSpecSeqNo());
			callableStatement.setInt(2, objSpecificationVO.getModelSeqNo());
			callableStatement.setString(3, objSpecificationVO.getUserID());
			
			callableStatement.registerOutParameter(4, Types.INTEGER);
			callableStatement.registerOutParameter(5, Types.VARCHAR);
			callableStatement.registerOutParameter(6, Types.VARCHAR);
			intStatus = callableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			intLSDBErrorID = (int) callableStatement.getInt(4);
			strOracleCode = (String) callableStatement.getString(5);
			strErrorMessage = (String) callableStatement.getString(6);
			
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
				throw new ApplicationException(objErrorInfo);
			}
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into catch block in ModelSpecificationDAO.deleteSpecification"
					+ objDataExp.getErrorInfo().getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ModelSpecificationDAO.deleteSpecification"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelSpecificationDAO.deleteSpecification");
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
				.logMessage("Enters into Exception block in ModelSpecificationDAO.deleteSpecification");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
				
			}
			
		}
		return intStatus;
		
	}
	
	/***************************************************************************
	 * @author ka57588 Added for LSDB_CR-64 This method is used for deleting
	 *         description
	 * @version 1.0
	 * @param objSpecificationVO
	 *            the object for specification
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int deleteSpecificationItem(SpecificationVO objSpecificationVO)
	throws EMDException {
		LogUtil
		.logMessage("Entering ModelSpecificationDAO.deleteSpecificationItem");
		Connection objConnnection = null;
		CallableStatement callableStatement = null;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		int intStatus = 0;
		String strLogUser = "";
		try {
			
			strLogUser = objSpecificationVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			
			callableStatement = objConnnection
			.prepareCall(EMDQueries.SP_DELETE_MDL_SPEC_ITEM);
			
			LogUtil
			.logMessage("objSpecificationVO.getSpecificationItem().getModSpecItemSeqNo() :"
					+ objSpecificationVO.getSpecificationItem()
					.getModSpecItemSeqNo());
			callableStatement.setInt(1, objSpecificationVO
					.getSpecificationItem().getModSpecItemSeqNo());
			callableStatement.setString(2, objSpecificationVO.getUserID());
			callableStatement.registerOutParameter(3, Types.INTEGER);
			callableStatement.registerOutParameter(4, Types.VARCHAR);
			callableStatement.registerOutParameter(5, Types.VARCHAR);
			intStatus = callableStatement.executeUpdate();
			
			LogUtil
			.logMessage("Inside the updateSpecificationItem method of ModelSpecificationDAO :intStatus .."
					+ intStatus);
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			intLSDBErrorID = (int) callableStatement.getInt(3);
			strOracleCode = (String) callableStatement.getString(4);
			strErrorMessage = (String) callableStatement.getString(5);
			
			if (intLSDBErrorID != 0) {
				
				LogUtil.logMessage("Error ID:" + intLSDBErrorID);
				
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
				StringBuffer sb = new StringBuffer();
				sb.append(strOracleCode + " ");
				sb.append(strErrorMessage);
				objErrorInfo.setMessage(sb.toString());
				throw new ApplicationException(objErrorInfo);
			}
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into catch block in ModelSpecificationDAO.updateSpecificationItem"
					+ objDataExp.getErrorInfo().getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ModelSpecificationDAO.updateSpecificationItem"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelSpecificationDAO.updateSpecificationItem");
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
				.logMessage("Enters into Exception block in ModelSpecificationDAO.updateSpecificationItem");
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
	 * @param objModelVO
	 *            the object for inserting spec item
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int updateSpecificationItem(SpecificationVO objSpecificationVO)
	throws EMDException {
		LogUtil
		.logMessage("Entering ModelSpecificationDAO.updateSpecificationItem");
		Connection objConnnection = null;
		CallableStatement callableStatement = null;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		int intStatus = 0;
		String strLogUser = "";
		try {
			
			strLogUser = objSpecificationVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			
			callableStatement = objConnnection
			.prepareCall(EMDQueries.SP_UPDATE_MDL_SPEC_ITEM);
			
			LogUtil
			.logMessage("objSpecificationVO.getSpecificationItem().getModSpecItemSeqNo() :"
					+ objSpecificationVO.getSpecificationItem()
					.getModSpecItemSeqNo());
			callableStatement.setInt(1, objSpecificationVO
					.getSpecificationItem().getModSpecItemSeqNo());
			callableStatement.setString(2, objSpecificationVO
					.getSpecificationItem().getModSpecItemDesc());
			callableStatement.setString(3, objSpecificationVO
					.getSpecificationItem().getModSpecItemValue());
			callableStatement.setString(4, objSpecificationVO.getUserID());
			
			LogUtil.logMessage("objSpecificationVO.getUserID() :"
					+ objSpecificationVO.getUserID());
			callableStatement.registerOutParameter(5, Types.INTEGER);
			callableStatement.registerOutParameter(6, Types.VARCHAR);
			callableStatement.registerOutParameter(7, Types.VARCHAR);
			intStatus = callableStatement.executeUpdate();
			
			LogUtil
			.logMessage("Inside the updateSpecificationItem method of ModelSpecificationDAO :intStatus .."
					+ intStatus);
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			intLSDBErrorID = (int) callableStatement.getInt(5);
			strOracleCode = (String) callableStatement.getString(6);
			strErrorMessage = (String) callableStatement.getString(7);
			
			if (intLSDBErrorID != 0) {
				
				LogUtil.logMessage("Error ID:" + intLSDBErrorID);
				
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
				StringBuffer sb = new StringBuffer();
				sb.append(strOracleCode + " ");
				sb.append(strErrorMessage);
				objErrorInfo.setMessage(sb.toString());
				throw new ApplicationException(objErrorInfo);
			}
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into catch block in ModelSpecificationDAO.updateSpecificationItem"
					+ objDataExp.getErrorInfo().getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ModelSpecificationDAO.updateSpecificationItem"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelSpecificationDAO.updateSpecificationItem");
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
				.logMessage("Enters into Exception block in ModelSpecificationDAO.updateSpecificationItem");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
				
			}
			
		}
		return intStatus;
		
	}
	
}