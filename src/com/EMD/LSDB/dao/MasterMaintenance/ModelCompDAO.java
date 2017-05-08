package com.EMD.LSDB.dao.MasterMaintenance;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;
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
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.CompGroupVO;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.SubSectionVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business methods for the Component
 * * /***************************************************************************
 * ------------------------------------------------------------------------------------------------------
 *     Date            version       modify by                 comments                              Remarks 
 * 15/03/2011            1.0         SD41630          viewLeadComponetClausesByModel           	Added for CR_97
 * 										               new method has been added 
 * 										 
  **************************************************************************/
 

public class ModelCompDAO extends EMDDAO {
	
	private ModelCompDAO() {
	}
	
	/***************************************************************************
	 * This Method is used to fetch the Component Details
	 * 
	 * @param objComponentVo
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/
	public static ArrayList fetchComps(ComponentVO objComponentVo)
	throws EMDException {
		LogUtil.logMessage("Inside ModelCompDAO.fetchComps");
		Connection objConnnection = null;
		ArrayList arlComponent = new ArrayList();
		CallableStatement objCallableStatement = null;
		// Error out parameters
		ResultSet objCompResultSet = null;
		int intLSDBErrorID = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		String strLogUser = "";
		try {
			strLogUser = objComponentVo.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in DAO :" + objConnnection);
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_SELECT_COMPONENT);
			
			if (objComponentVo.getComponentGroupSeqNo() != 0) {
				objCallableStatement.setInt(1, objComponentVo
						.getComponentGroupSeqNo());
			} else {
				objCallableStatement.setNull(1, Types.NULL);
			}
			
			if (objComponentVo.getCharacterisationFlag() != null
					&& !objComponentVo.getCharacterisationFlag().equals("")) {
				objCallableStatement.setString(2, objComponentVo
						.getCharacterisationFlag());
			} else {
				objCallableStatement.setNull(2, Types.NULL);
			}
			
			objCallableStatement.setString(3, objComponentVo.getUserID());
			objCallableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			objCompResultSet = (ResultSet) objCallableStatement.getObject(4);
			
			LogUtil
			.logMessage("Inside the fetchComponent method of ComponentDAO :resultSet"
					+ objCompResultSet);
			
			intLSDBErrorID = (int) objCallableStatement.getInt(5);
			strOracleCode = (String) objCallableStatement.getString(6);
			strErrorMessage = (String) objCallableStatement.getString(7);
			
			// Handled Valid Exception
			if (intLSDBErrorID != 0) {
				
				ErrorInfo objErrorInfo = new ErrorInfo();
				strMessage = String.valueOf(intLSDBErrorID);
				objErrorInfo.setMessageID(strMessage);
				
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
			
			while (objCompResultSet.next()) {
				objComponentVo = new ComponentVO();
				objComponentVo.setComponentSeqNo(objCompResultSet
						.getInt(DatabaseConstants.COMP_SEQ_NO));
				objComponentVo.setComponentName(objCompResultSet
						.getString(DatabaseConstants.COMP_NAME));
				objComponentVo.setComments(objCompResultSet
						.getString(DatabaseConstants.COMP_DESC));
				objComponentVo.setComponentIdentifier(objCompResultSet
						.getString(DatabaseConstants.COMP_IDENTIFIER));
				objComponentVo.setComponentGroupName(objCompResultSet
						.getString(DatabaseConstants.COMP_GRP_NAME));
				if (objCompResultSet.getString(DatabaseConstants.DISP_SPEC)
						.equals("Y")) {
					objComponentVo.setDisplayInSpecFlag(true);
				} else {
					objComponentVo.setDisplayInSpecFlag(false);
				}
				arlComponent.add(objComponentVo);
			}
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("ENters into catch block in ModelCompDAO:fetchComps:"
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in ModelCompDAO:fetchComps:"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelCompDAO:fetchComps:");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(objCompResultSet,
						objCallableStatement, objConnnection);
			} catch (SQLException sqlex) {
				LogUtil
				.logMessage("Enters into Exception block in ModelCompDAO:fetchComps:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + sqlex.getMessage());
				throw new ApplicationException(sqlex, objErrorInfo);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in ModelCompDAO:fetchComps:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return arlComponent;
		
	}
	
	/***************************************************************************
	 * This Method is used to insert a new Component.
	 * 
	 * @param objComponentVO
	 * @return boolean
	 * @throws EMDException
	 **************************************************************************/
	public static int insertComp(ComponentVO objComponentVo)
	throws EMDException {
		LogUtil.logMessage("Inside ModelCompDAO:insertComp");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		try {
			strLogUser = objComponentVo.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in DAO in Insert Method:"
					+ objConnnection);
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_INSERT_COMPONENT);
			
			objCallableStatement.setInt(1, objComponentVo
					.getComponentGroupSeqNo());
			objCallableStatement
			.setString(2, objComponentVo.getComponentName());
			objCallableStatement.setString(3, objComponentVo.getComments());
			if (objComponentVo.isDisplayInSpecFlag()) {
				objCallableStatement.setString(4, "Y");
			} else {
				objCallableStatement.setString(4, "N");
			}
			objCallableStatement.setString(5, objComponentVo
					.getComponentIdentifier());
			objCallableStatement.setString(6, objComponentVo.getUserID());
			objCallableStatement.registerOutParameter(7, Types.INTEGER);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			objCallableStatement.registerOutParameter(9, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			LogUtil
			.logMessage("Inside the insert method of ComponentDAO :intStatus .."
					+ intStatus);
			intLSDBErrorID = (int) objCallableStatement.getInt(7);
			strOracleCode = (String) objCallableStatement.getString(8);
			strErrorMessage = (String) objCallableStatement.getString(9);
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Error ID:" + intLSDBErrorID);
				LogUtil.logMessage("strOracleCode:" + strOracleCode);
				
			}
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Enters into Error Loop in Insert Method:");
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
				LogUtil.logMessage("sb.toString():" + sb.toString());
				objErrorInfo.setMessage(sb.toString());
				LogUtil.logError(objErrorInfo);
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
		}
		
		catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("ENters into catch block in ModelCompDAO:insertComp:"
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in ModelCompDAO:insertComp:"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelCompDAO:insertComp:");
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
				LogUtil
				.logMessage("Enters into Exception block in ModelCompDAO:insertComp:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + sqlex.getMessage());
				throw new ApplicationException(sqlex, objErrorInfo);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in ModelCompDAO:insertComp:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return intStatus;
		
	}
	
	/***************************************************************************
	 * This Method is used to Modify Component Details
	 * 
	 * @param objComponentVo
	 * @return boolean
	 * @throws EMDException
	 **************************************************************************/
	public static int updateComp(ComponentVO objComponentVo)
	throws EMDException {
		LogUtil.logMessage("Inside ComponentDAO:updateComp");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		try {
			strLogUser = objComponentVo.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in DAO in Update Method:"
					+ objConnnection);
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_UPDATE_COMPONENT);
			
			objCallableStatement.setInt(1, objComponentVo.getComponentSeqNo());
			objCallableStatement
			.setString(2, objComponentVo.getComponentName());
			objCallableStatement.setString(3, objComponentVo.getComments());
			if (objComponentVo.isDisplayInSpecFlag()) {
				objCallableStatement.setString(4, "Y");
			} else {
				objCallableStatement.setString(4, "N");
			}
			objCallableStatement.setString(5, objComponentVo
					.getComponentIdentifier());
			objCallableStatement.setString(6, objComponentVo.getUserID());
			objCallableStatement.setInt(7, objComponentVo
					.getComponentGroupSeqNo());
			objCallableStatement.registerOutParameter(8, Types.INTEGER);
			objCallableStatement.registerOutParameter(9, Types.VARCHAR);
			objCallableStatement.registerOutParameter(10, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			LogUtil
			.logMessage("Inside the Update method of ComponentDAO :intStatus .."
					+ intStatus);
			intLSDBErrorID = (int) objCallableStatement.getInt(8);
			strOracleCode = (String) objCallableStatement.getString(9);
			strErrorMessage = (String) objCallableStatement.getString(10);
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Error ID:" + intLSDBErrorID);
				LogUtil.logMessage("strOracleCode:" + strOracleCode);
				
			}
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Enters into Error Loop in Update Method:");
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
				LogUtil.logMessage("sb.toString():" + sb.toString());
				objErrorInfo.setMessage(sb.toString());
				LogUtil.logError(objErrorInfo);
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
		}
		
		catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("ENters into catch block in ModelCompDAO:updateComp:"
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in ModelCompDAO:updateComp:"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelCompDAO:updateComp:");
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
				LogUtil
				.logMessage("Enters into Exception block in ModelCompDAO:updateComp:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + sqlex.getMessage());
				throw new ApplicationException(sqlex, objErrorInfo);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in ModelCompDAO:updateComp:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return intStatus;
		
	}
	
	/***************************************************************************
	 * This Method is used to fetch the Component Details tied to the Model
	 * 
	 * @param objComponentVo
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/
	public static ArrayList fetchModelComps(ComponentVO objComponentVo)
	throws EMDException {
		LogUtil.logMessage("Inside ModelCompDAO.fetchModelComps");
		Connection objConnnection = null;
		ArrayList arlComponent = new ArrayList();
		ArrayList arlComponentGroup = new ArrayList();
		CallableStatement objCallableStatement = null;
		// Error out parameters
		ResultSet objCompResultSet = null;
		ResultSet objCompGroupResultSet = null;
		int intLSDBErrorID = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		String strLogUser = "";
		try {
			strLogUser = objComponentVo.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in DAO :" + objConnnection);
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_SELECT_MDL_COMPONENT);
			
			/*Added For CR_81 Locomotive and PowerProducts Enhancements by RR68151		
			if (objComponentVo.getCharacterisationFlag() != null
					&& !objComponentVo.getCharacterisationFlag().equals("")) {
				objCallableStatement.setString(1, objComponentVo
						.getCharacterisationFlag());
				LogUtil.logMessage("Characterisation Flag"
						+ objComponentVo.getCharacterisationFlag());
			} else {
				objCallableStatement.setNull(1, Types.NULL);
				LogUtil.logMessage("Characterisation FlagNULL");
			}*/

			if (objComponentVo.getCompGrpTypeSeqNo() == 0) {
				
				objCallableStatement.setNull(1, Types.NULL);
			} else {

				objCallableStatement.setInt(1, objComponentVo.getCompGrpTypeSeqNo());
			}
			LogUtil.logMessage("Comp Grp Type Seq No :" + objComponentVo.getCompGrpTypeSeqNo());
			//Added For CR_81 by RR68151 - Ends here
			
			objCallableStatement.setString(2, objComponentVo.getUserID());
			LogUtil.logMessage("User" + objComponentVo.getUserID());
			objCallableStatement.setInt(3, objComponentVo.getModelSeqNo());
			LogUtil.logMessage("Model Seq No" + objComponentVo.getModelSeqNo());
			
			if (objComponentVo.getSubSectionSeqNo() == 0) { /*
			* Modified For
			* Attach to Clause
			* CR *
			*/
				objCallableStatement.setNull(4, Types.NULL);
			} else {
				objCallableStatement.setInt(4, objComponentVo
						.getSubSectionSeqNo());
			}
			
			if (objComponentVo.getComponentGroupSeqNo() == 0) {
				objCallableStatement.setNull(5, Types.NULL);
			} else {
				objCallableStatement.setInt(5, objComponentVo
						.getComponentGroupSeqNo());
			}
			objCallableStatement.registerOutParameter(6, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(7, Types.INTEGER);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			objCallableStatement.registerOutParameter(9, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			objCompGroupResultSet = (ResultSet) objCallableStatement
			.getObject(6);
			
			LogUtil
			.logMessage("Inside the fetchComponent method of ComponentDAO :resultSet"
					+ objCompGroupResultSet);
			
			intLSDBErrorID = (int) objCallableStatement.getInt(7);
			
			strOracleCode = (String) objCallableStatement.getString(8);
			
			strErrorMessage = (String) objCallableStatement.getString(9);
			
			// Handled Valid Exception
			if (intLSDBErrorID != 0) {
				
				ErrorInfo objErrorInfo = new ErrorInfo();
				strMessage = String.valueOf(intLSDBErrorID);
				objErrorInfo.setMessageID(strMessage);
				
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
			
			while (objCompGroupResultSet.next()) {
				LogUtil.logMessage("Inside  resultSet");
				CompGroupVO objCompGroupVo = new CompGroupVO();
				
				objCompGroupVo.setComponentGroupSeqNo(objCompGroupResultSet
						.getInt(DatabaseConstants.COMP_GRP_SEQ_NO));
				objCompGroupVo.setComponentGroupName(objCompGroupResultSet
						.getString(DatabaseConstants.LS130_COMP_GRP_NAME));
				LogUtil.logMessage("CompGroupName:"
						+ objCompGroupVo.getComponentGroupName());
				objCompResultSet = (ResultSet) objCompGroupResultSet
				.getObject("Components");
				
				while (objCompResultSet.next()) {
					objComponentVo = new ComponentVO();
					objComponentVo.setComponentGroupSeqNo(objCompGroupResultSet
							.getInt(DatabaseConstants.COMP_GRP_SEQ_NO));
					objComponentVo.setComponentSeqNo(objCompResultSet
							.getInt(DatabaseConstants.LS140_COMP_SEQ_NO));
					
					objComponentVo.setComponentName(objCompResultSet
							.getString(DatabaseConstants.LS140_COMP_NAME));
					LogUtil.logMessage("ComponentName:"
							+ objComponentVo.getComponentName());
					objComponentVo.setComments(objCompResultSet
							.getString(DatabaseConstants.LS140_COMP_DESC));
					
					objComponentVo
					.setComponentIdentifier(objCompResultSet
							.getString(DatabaseConstants.LS140_COMP_IDENTIFIER));
					
					/*
					 * if(objCompResultSet.getString(DatabaseConstants.LS140_DISP_IN_SPEC).equals("Y")){
					 * objComponentVo.setDisplayInSpecFlag(true);
					 * LogUtil.logMessage("6"); }else{
					 * objComponentVo.setDisplayInSpecFlag(false);
					 * LogUtil.logMessage("6"); }
					 */
					arlComponent.add(objComponentVo);
					
				}
				objCompGroupVo.setComponentVO(arlComponent);
				DBHelper.closeSQLObjects(objCompResultSet, null, null);
				arlComponentGroup.add(objCompGroupVo);
			}
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("ENters into catch block in ModelCompDAO:fetchComps:"
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in ModelCompDAO:fetchComps:"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelCompDAO:fetchComps:");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(objCompResultSet,
						objCallableStatement, objConnnection);
			} catch (SQLException sqlex) {
				LogUtil
				.logMessage("Enters into Exception block in ModelCompDAO:fetchComps:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + sqlex.getMessage());
				throw new ApplicationException(sqlex, objErrorInfo);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in ModelCompDAO:fetchComps:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return arlComponentGroup;
		
	}
	public static int deleteComp(ComponentVO objComponentVo)
	throws EMDException {
		LogUtil.logMessage("Inside ComponentDAO:deleteComp");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		try {
			strLogUser = objComponentVo.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in DAO in delete Method:"
					+ objConnnection);
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_Delete_COMPONENT);
			
			objCallableStatement.setInt(1, objComponentVo.getComponentSeqNo());
			
			objCallableStatement.setString(2, objComponentVo.getUserID());
			
			objCallableStatement.registerOutParameter(3, Types.INTEGER);
			objCallableStatement.registerOutParameter(4, Types.VARCHAR);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			LogUtil
			.logMessage("Inside the delete method of ComponentDAO :intStatus .."
					+ intStatus);
			intLSDBErrorID = (int) objCallableStatement.getInt(3);
			strOracleCode = (String) objCallableStatement.getString(4);
			strErrorMessage = (String) objCallableStatement.getString(5);
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Error ID:" + intLSDBErrorID);
				LogUtil.logMessage("strOracleCode:" + strOracleCode);
				
			}
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Enters into Error Loop in delete Method:");
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
				LogUtil.logMessage("sb.toString():" + sb.toString());
				objErrorInfo.setMessage(sb.toString());
				LogUtil.logError(objErrorInfo);
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
		}
		
		catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("ENters into catch block in ModelCompDAO:deleteComp:"
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in ModelCompDAO:deleteComp:"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelCompDAO:deleteComp:");
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
				LogUtil
				.logMessage("Enters into Exception block in ModelCompDAO:deleteComp:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + sqlex.getMessage());
				throw new ApplicationException(sqlex, objErrorInfo);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in ModelCompDAO:deleteComp:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return intStatus;
		
	}
	
	
	/***************************************************************************
	 * This Method is used to add the Component from order to Model 
	 * Component
	 * 
	 * @param objComponentVO
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	public static int copyCompOrdrToMdl(ComponentVO objComponentVO)
	throws EMDException {
		LogUtil.logMessage("Inside ModelCompMapDAO.copyCompOrdrToMdl");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		int intNewClauseSeqNo=-1;
		String strLogUser = "";
		try {
			

			strLogUser = objComponentVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("**********objConnnection********** :");
			objCallableStatement=objConnnection.prepareCall(EMDQueries.SP_COPY_ORDR_COMP_TO_MDL);
			objCallableStatement.setInt(1, objComponentVO.getModelSeqNo());
			objCallableStatement.setInt(2, objComponentVO.getComponentGroupSeqNo());
			objCallableStatement.setInt(3, objComponentVO.getComponentSeqNo());
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			objCallableStatement.setString(5, objComponentVO.getUserID());
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			intStatus=objCallableStatement.executeUpdate();
			intNewClauseSeqNo = objCallableStatement.getInt(4);
			
			LogUtil
			.logMessage("Inside the fetchCompClauseMapDtls method of ModelCompMapDAO :resultSet:from SP_COPY_ORDR_COMP_TO_MDL "
					+ intNewClauseSeqNo);
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			intLSDBErrorID = (int) objCallableStatement.getInt(6);
			strOracleCode = (String) objCallableStatement.getString(7);
			strErrorMessage = (String) objCallableStatement.getString(8);
			
			
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Enters into Error Loop in unMapComponentGrp method of ModelCompMapDAO");
				LogUtil.logMessage("Error ID:" + intLSDBErrorID);
				LogUtil.logMessage("strOracleCode:" + strOracleCode);
				
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessageID("" + intLSDBErrorID);
				LogUtil.logMessage("Error message in ErrorInfo:"+ objErrorInfo.getMessageID());
				throw new DataAccessException(objErrorInfo);
				
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {// Un
				// handled
				// exception
				
				LogUtil.logMessage("strOracleCode:" + strOracleCode);
				ErrorInfo objErrorInfo = new ErrorInfo();
				StringBuffer sb = new StringBuffer();
				sb.append(strOracleCode + " ");
				sb.append(strErrorMessage);
				LogUtil.logMessage("sb.toString():" + sb.toString());
				objErrorInfo.setMessage(sb.toString());
				LogUtil.logError(objErrorInfo);
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
			if(intNewClauseSeqNo!=-1)
			{
				objComponentVO.setNewClaSeqNo(intNewClauseSeqNo);							
			}		
		}
		
		catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("ENters into catch block in ModelCompMapDAO.unMapComponentGrp"
					+ objDataExp.getErrorInfo().getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ModelCompMapDAO.unMapComponentGrp"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			LogUtil
			.logMessage("Enters into Exception block in ModelCompMapDAO.unMapComponentGrp");
			throw new ApplicationException(objExp, objErrorInfo);
		} finally {
			try {
				DBHelper.closeSQLObjects(null, objCallableStatement,
						objConnnection);
			} catch (Exception objExp) {
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				LogUtil
				.logMessage("Enters into Exception block in ModelCompMapDAO.unMapComponentGrp");
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}		
		return intStatus;		
	}	
	
	
	/***************************************************************************
	 * This Method is used to fetch the LeadComponentclauses for CR_97
	 * 
	 * @param objSubSectionVO
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/
	public static ArrayList viewLeadComponetClausesByModel(ComponentVO objComponentVO)
	throws EMDException {
		LogUtil.logMessage("Entering ViewSpecByModelDAO.viewLeadComponetClausesByModel");
		/** Declarations * */
		Connection objConnnection = null;
		ArrayList arlSpecs = new ArrayList();
		ArrayList arlEDLNos = new ArrayList();
		ArrayList arlRefEDLNos = new ArrayList();
		ArrayList arlpartSubSecCode = new ArrayList();
		ArrayList arlTableColumns = new ArrayList();
		ArrayList arlTableRows = new ArrayList();
		ArrayList arlLeadComponentVO = null;
		ArrayList arlCompgroupVO = new ArrayList();
		ArrayList resultSetList = null;
		
		
		CallableStatement objCallableStatement = null;
		/** Result Sets * */
		ResultSet rsSpecs = null;
		ResultSet objEDLNoResultSet = null;
		ResultSet objRefEDLNoResultSet = null;
		ResultSet objSubSecResultSet = null;
		ResultSet objTbDataResultSet = null;
		ResultSet objStdEqpResultSet = null;
		//ResultSet objComponentResultSet = null;
		ResultSet objClauseComp = null;
		
		/** Error ourt parameters * */
		int intLSDBErrorID = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		ClauseVO objClauseVO = null;
		CompGroupVO objcompGroupVO = null;
		ComponentVO objLeadComponentVO = null;
		String strLogUser = "";
		int countCol=0;
		try {
			strLogUser = objComponentVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_LSDB_MDL_CLAUSE);
			
			if (objComponentVO.getComponentGroupSeqNo() == 0) {
				objCallableStatement.setNull(1, Types.NULL);
			} else {
				objCallableStatement.setInt(1, objComponentVO.getComponentGroupSeqNo());
			}
			
			if (objComponentVO.getComponentSeqNo() == 0) {
				objCallableStatement.setNull(2, Types.NULL);
			} else {
				objCallableStatement.setInt(2, objComponentVO.getComponentSeqNo());
			}
			objCallableStatement.registerOutParameter(3, OracleTypes.CURSOR);		
			objCallableStatement.setString(4, objComponentVO.getUserID());
			
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.execute();
			
			rsSpecs = (ResultSet) objCallableStatement.getObject(3);
			
			intLSDBErrorID = (int) objCallableStatement.getInt(5);
			strOracleCode = (String) objCallableStatement.getString(6);
			strErrorMessage = (String) objCallableStatement.getString(7);
			
			if (intLSDBErrorID != 0) {
				
				ErrorInfo objErrorInfo = new ErrorInfo();
				
				objErrorInfo.setMessageID("" + intLSDBErrorID);
				
				throw new DataAccessException(objErrorInfo);
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {
				
				LogUtil.logMessage("strOracleCode:" + strOracleCode);
				ErrorInfo objErrorInfo = new ErrorInfo();
				StringBuffer sb = new StringBuffer();
				sb.append(strOracleCode + " ");
				sb.append(strErrorMessage);
				objErrorInfo.setMessage(sb.toString());
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
			/** looping through result set for getting the clauses * */
			
			while (rsSpecs.next()) {
				objClauseVO = new ClauseVO();
				arlCompgroupVO = new ArrayList();
				arlpartSubSecCode = new ArrayList();
				arlRefEDLNos = new ArrayList();
				arlEDLNos = new ArrayList();
				arlTableRows = new ArrayList();
				resultSetList = new ArrayList();
				arlLeadComponentVO= new ArrayList();
				LogUtil.logMessage("in lead component clauses");
				objClauseVO.setModelSeqNo(rsSpecs
						.getInt(DatabaseConstants.LS200_MDL_SEQ_NO));
				//Added for CR-111 to add a Model name column
				objClauseVO.setModelName(rsSpecs.getString(DatabaseConstants.LS200_MDL_NAME));
				objClauseVO.setSubSectionSeqNo(rsSpecs
						.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO));
				objClauseVO.setClauseSeqNo(rsSpecs
						.getInt(DatabaseConstants.LS300_CLA_SEQ_NO));
						objClauseVO.setVersionNo(rsSpecs
						.getInt(DatabaseConstants.LS301_VERSION_NO));
						LogUtil.logMessage("clause seq no +" +objClauseVO.getClauseSeqNo());		
				String clauseDesc = rsSpecs
				.getString(DatabaseConstants.LS301_CLA_DESC);
				objClauseVO.setClauseDesc(clauseDesc);
				//Added for CR-121
				objClauseVO.setSubClaExistsFlag(rsSpecs.getString(DatabaseConstants.SUB_CLAUSE_EXISTS));
				//Added for CR-121 ends here
				objClauseVO.setTableArrayData1(arlTableRows);
				objEDLNoResultSet = (ResultSet) rsSpecs.getObject("EDLNO");
				
				while (objEDLNoResultSet.next()) {
					arlEDLNos.add(objEDLNoResultSet
							.getString(DatabaseConstants.LS302_EDL_NO));
				}
				objClauseVO.setEdlNO(arlEDLNos);
				
				objRefEDLNoResultSet = (ResultSet) rsSpecs
				.getObject("refEDLNO");
				
				while (objRefEDLNoResultSet.next()) {
					arlRefEDLNos.add(objRefEDLNoResultSet
							.getString(DatabaseConstants.LS303_REF_EDL_NO));
				}
				
				objClauseVO.setRefEDLNO(arlRefEDLNos);
				
				objSubSecResultSet = (ResultSet) rsSpecs.getObject("PartOF");
				
				while (objSubSecResultSet.next()) {
					arlpartSubSecCode.add(objSubSecResultSet
							.getString(DatabaseConstants.LS304_SUBSEC_NO));
				}
				
				objClauseVO.setPartOF(arlpartSubSecCode);
				
				
				objTbDataResultSet = (ResultSet) rsSpecs
				.getObject("CLA_TBL_DATA");
				
				while (objTbDataResultSet.next()) {
					arlTableColumns = new ArrayList();
					arlTableColumns.add(objTbDataResultSet
							.getString(DatabaseConstants.LS306_TBL_DATA_COL_1));
					arlTableColumns.add(objTbDataResultSet
							.getString(DatabaseConstants.LS306_TBL_DATA_COL_2));
					arlTableColumns.add(objTbDataResultSet
							.getString(DatabaseConstants.LS306_TBL_DATA_COL_3));
					arlTableColumns.add(objTbDataResultSet
							.getString(DatabaseConstants.LS306_TBL_DATA_COL_4));
					arlTableColumns.add(objTbDataResultSet
							.getString(DatabaseConstants.LS306_TBL_DATA_COL_5));
					arlTableRows.add(arlTableColumns);
				}
//				Added For CR_93
     			countCol=ApplicationUtil.getTableDataColumnsCount(arlTableRows);
				objClauseVO.setTableDataColSize(countCol);
							
				
				//Added for DWO, Part Number and Price book Number
				objClauseVO.setDwONumber(rsSpecs
						.getInt(DatabaseConstants.LS301_DWO_NUMBER));
				objClauseVO.setPartNumber(rsSpecs
						.getInt(DatabaseConstants.LS301_PART_NUMBER));
				objClauseVO.setPriceBookNumber(rsSpecs
						.getInt(DatabaseConstants.LS301_PRICE_BOOK_NUMBER));
				
				objStdEqpResultSet = (ResultSet) rsSpecs.getObject("STD_EQUIP");
				while (objStdEqpResultSet.next()) {
					
					objClauseVO.setStandardEquipmentDesc(objStdEqpResultSet
							.getString(DatabaseConstants.LS060_STD_EQP_DESC));
					objClauseVO.setStandardEquipmentSeqNo(objStdEqpResultSet.getInt(DatabaseConstants.LS060_STD_EQP_SEQ_NO));
					
				}
				objClauseVO.setEngDataComment(rsSpecs.getString(DatabaseConstants.LS301_ENGG_DATA_COMMENTS));
				objClauseVO.setReason(rsSpecs
						.getString(DatabaseConstants.LS301_CLA_REASON));
				
				
				
				objClauseComp = (ResultSet) rsSpecs.getObject(DatabaseConstants.CLA_COMP);
				
				//arlLeadComponentVO= new ArrayList();
				
				while (objClauseComp.next()) {
					objLeadComponentVO = new ComponentVO();
					objcompGroupVO = new CompGroupVO();
					
					
					LogUtil.logMessage("Enters Clause Comp loop");
					 
					objcompGroupVO.setCompGrpTypeSeqNo(objClauseComp.getInt(DatabaseConstants.LS130_COMP_GRP_SEQ_NO));
					objcompGroupVO.setCompGrpTypeName(objClauseComp.getString(DatabaseConstants.LS130_COMP_GRP_NAME));
					objcompGroupVO.setValidFlag(objClauseComp.getString(DatabaseConstants.LS130_VALIDATION_FLAG));
					//objcompGroupVO.setLeadFlag(objClauseComp.getString(DatabaseConstants.Italics));
					objLeadComponentVO.setComponentSeqNo(objClauseComp.getInt(DatabaseConstants.LS140_COMP_SEQ_NO));
					objLeadComponentVO.setComponentName(objClauseComp.getString(DatabaseConstants.LS140_COMP_NAME));
					
					if (objClauseComp
							.getString(DatabaseConstants.LS204_DEFAULT_FLAG) != null
							&& objClauseComp.getString(
									DatabaseConstants.LS204_DEFAULT_FLAG)
									.equals("Y")) {
						objLeadComponentVO.setDefaultFlag(true);
					} else {
						objLeadComponentVO.setDefaultFlag(false);
					}
					objLeadComponentVO.setCompLeadFlag(objClauseComp.getString("ITALICS"));
					
					objcompGroupVO.setCompVO(objLeadComponentVO);
					arlCompgroupVO.add(objcompGroupVO);					
					
				}
				
						

				objClauseVO.setCompGroupVO(arlCompgroupVO);
				
				arlSpecs.add(objClauseVO);
				
				/** Closing all the sub resultsets * */
				
				resultSetList.add(objEDLNoResultSet);
				resultSetList.add(objRefEDLNoResultSet);
				resultSetList.add(objSubSecResultSet);
				resultSetList.add(objTbDataResultSet);
				resultSetList.add(objStdEqpResultSet);
				//resultSetList.add(objComponentResultSet);
				resultSetList.add(objClauseComp);
				DBHelper.closeResultSets(resultSetList, null, null);
			}
			
		} catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException ViewSpecByModelDAO:fetchSpecByModel");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in ViewSpecByModelDAO:fetchSpecByModel"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException ViewSpecByModelDAO:fetchSpecByModel");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in ViewSpecByModelDAO:fetchSpecByModel"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception ViewSpecByModelDAO:fetchSpecByModel");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(rsSpecs, objCallableStatement,
						objConnnection);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception ViewSpecByModelDAO:fetchSpecByModel");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
			return arlSpecs;
		
	}
	
	
	
}
