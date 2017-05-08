/**
 * 
 */
package com.EMD.LSDB.dao.SpecMaintenance;

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
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.dao.common.DBHelper;
import com.EMD.LSDB.dao.common.EMDQueries;
import com.EMD.LSDB.vo.common.ClauseVO;

/**
 * @author AK49339
 * 
 */
public class AcceptRejectNewClauseDAO {
	
	private AcceptRejectNewClauseDAO() {
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objGenArrangeVO
	 *            The Object used for Fetch Clause Versions
	 * @return ArrayList The Arraylist containing Clause versions
	 * @throws EMDException
	 **************************************************************************/
	public static ArrayList fetchNewClauses(ClauseVO objClauseVO)
	throws EMDException {
		LogUtil.logMessage("Inside AcceptRejectNewClauseDAO.fetch");
		
		Connection objConnnection = null;
		ArrayList arlClause = new ArrayList();
		CallableStatement objCallableStatement = null;
		ResultSet objClauseResultSet = null;
		ResultSet objEDLNos = null;
		ResultSet objRefEDLNos = null;
		ResultSet objPartOf = null;
		ResultSet objCompName = null;
		ResultSet objTableData = null;
		ResultSet objStdEqpDesc = null;
		int intLSDBErrorID = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strStdEquipdesc = null;
		
		ArrayList arledlNo = null;
		ArrayList arlrefedlNo = null;
		ArrayList arlpartof = null;
		ArrayList arlcompname = null;
		ArrayList arlTableList = null;
		int countCol=0;
		
		
		ArrayList arlCloseResultSet = new ArrayList();
		
		String strLogUser = "";
		
		try {
			strLogUser = objClauseVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in DAO :" + objConnnection);
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_SELECT_ORDER_NEW_CLAUSE);
			
			objCallableStatement.setInt(1, objClauseVO.getOrderKey());
			objCallableStatement.setString(2, objClauseVO.getUserID());
			objCallableStatement.setInt(3, objClauseVO.getSubSectionSeqNo());
			objCallableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			LogUtil
			.logMessage("Inside the fetchClause method of ModelClauseDAO :resultSet11111111");
			
			objClauseResultSet = (ResultSet) objCallableStatement.getObject(4);
			
			LogUtil
			.logMessage("Inside the fetchClause method of ModelClauseDAO :resultSet"
					+ objClauseResultSet);
			
			intLSDBErrorID = (int) objCallableStatement.getInt(5);
			
			strOracleCode = (String) objCallableStatement.getString(6);
			
			strErrorMessage = (String) objCallableStatement.getString(7);
			
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
			
			while (objClauseResultSet.next()) {
				objClauseVO = new ClauseVO();
				
				objEDLNos = null;
				objRefEDLNos = null;
				objPartOf = null;
				objCompName = null;
				objTableData = null;
				objStdEqpDesc = null;
				
				arledlNo = new ArrayList();
				arlrefedlNo = new ArrayList();
				arlpartof = new ArrayList();
				arlcompname = new ArrayList();
				//CR_90
				String  strCompGRPName =null;
				
				
				LogUtil.logMessage("enters into result set");
				objClauseVO.setClauseSeqNo(objClauseResultSet
						.getInt(DatabaseConstants.LS300_CLA_SEQ_NO));
				objClauseVO.setVersionNo(objClauseResultSet
						.getInt(DatabaseConstants.LS301_VERSION_NO));
				// objClauseVO.setClauseDesc(objClauseResultSet.getString(DatabaseConstants.LS301_CLA_DESC));
				String clauseDesc = objClauseResultSet
				.getString(DatabaseConstants.LS301_CLA_DESC);
				objClauseVO.setComments(objClauseResultSet
						.getString(DatabaseConstants.LS301_ENGG_DATA_COMMENTS));
				objClauseVO.setClauseDesc(clauseDesc);
				objEDLNos = (ResultSet) objClauseResultSet.getObject("EDL_NO");
				while (objEDLNos.next()) {
					LogUtil.logMessage("EDL Nos");
					arledlNo.add(objEDLNos
							.getString(DatabaseConstants.LS302_EDL_NO));
					LogUtil.logMessage("Exits EDL Nos");
					
				}
				
				objClauseVO.setEdlNO(arledlNo);
				
				objRefEDLNos = (ResultSet) objClauseResultSet
				.getObject("REF_EDL_NO");
				while (objRefEDLNos.next()) {
					LogUtil.logMessage(" ENTERS REF EDL Nos");
					arlrefedlNo.add(objRefEDLNos
							.getString(DatabaseConstants.LS303_REF_EDL_NO));
					LogUtil.logMessage(" EXITS REF EDL  Nos");
				}
				objClauseVO.setRefEDLNO(arlrefedlNo);
				
				objPartOf = (ResultSet) objClauseResultSet.getObject("PART_OF");
				while (objPartOf.next()) {
					LogUtil.logMessage("Part Of values");
					arlpartof.add(objPartOf
							.getString(DatabaseConstants.LS304_SUBSEC_NO));
					LogUtil.logMessage("Exits Part Of values");
					
				}
				objClauseVO.setPartOF(arlpartof);
				
				objCompName = (ResultSet) objClauseResultSet
				.getObject("COMP_NAME");
				while (objCompName.next()) {
					strCompGRPName = objCompName
					.getString(DatabaseConstants.LS130_COMP_GRP_NAME);
					LogUtil.logMessage("Comp Name Values");
					arlcompname.add(objCompName
							.getString(DatabaseConstants.LS140_COMP_NAME));
					
					LogUtil.logMessage("Exits Comp Name Values");
					
				}
				objClauseVO.setCompName(arlcompname);
				objClauseVO.setCompGroupName(strCompGRPName);
				
				LogUtil
				.logMessage("Component Name"
						+ objClauseVO.getCompName());
				
				objTableData = (ResultSet) objClauseResultSet
				.getObject("TAB_DATA");
				arlTableList = new ArrayList();
				while (objTableData.next()) {
					LogUtil.logMessage(" Enters Table Data");
					ArrayList arlTableData = new ArrayList();
					
					LogUtil.logMessage(" Enters Table Data");
					arlTableData.add(objTableData
							.getString(DatabaseConstants.LS306_TBL_DATA_COL_1));
					arlTableData.add(objTableData
							.getString(DatabaseConstants.LS306_TBL_DATA_COL_2));
					arlTableData.add(objTableData
							.getString(DatabaseConstants.LS306_TBL_DATA_COL_3));
					arlTableData.add(objTableData
							.getString(DatabaseConstants.LS306_TBL_DATA_COL_4));
					arlTableData.add(objTableData
							.getString(DatabaseConstants.LS306_TBL_DATA_COL_5));
					// arlTableData.add(objTableData.getString(DatabaseConstants.LS306_HEADER_FLAG));
					arlTableList.add(arlTableData);
					LogUtil.logMessage("exists Table Data");
				}
//				Added For CR_93
     			countCol=ApplicationUtil.getTableDataColumnsCount(arlTableList);
				objClauseVO.setTableDataColSize(countCol);
				objClauseVO.setTableArrayData1(arlTableList);
				LogUtil.logMessage("Table Data"
						+ objClauseVO.getTableArrayData1());
				// objClauseVO.setHeader(tableData6);
				
				objClauseVO.setCustomerName(objClauseResultSet
						.getString(DatabaseConstants.LS050_CUST_NAME));
				objClauseVO.setDwONumber(objClauseResultSet
						.getInt(DatabaseConstants.LS301_DWO_NUMBER));
				objClauseVO.setPartNumber(objClauseResultSet
						.getInt(DatabaseConstants.LS301_PART_NUMBER));
				objClauseVO.setPriceBookNumber(objClauseResultSet
						.getInt(DatabaseConstants.LS301_PRICE_BOOK_NUMBER));
				objStdEqpDesc = (ResultSet) objClauseResultSet
				.getObject("STD_EQUIP");
				
				while (objStdEqpDesc.next()) {
					LogUtil.logMessage("Enters Std Equipment");
					strStdEquipdesc = objStdEqpDesc
					.getString(DatabaseConstants.LS060_STD_EQP_DESC);
					objClauseVO.setStandardEquipmentDesc(strStdEquipdesc);
					LogUtil.logMessage("Exists Std Equipment");
				}
				arlClause.add(objClauseVO);
				
				// Needs to check
				arlCloseResultSet = new ArrayList();
				arlCloseResultSet.add(objEDLNos);
				arlCloseResultSet.add(objRefEDLNos);
				arlCloseResultSet.add(objPartOf);
				arlCloseResultSet.add(objCompName);
				arlCloseResultSet.add(objTableData);
				arlCloseResultSet.add(objStdEqpDesc);
				DBHelper.closeResultSets(arlCloseResultSet, null, null);
				// Ends
				
			}
			
			/*
			 * arlCloseResultSet.add(objClauseResultSet);
			 * arlCloseResultSet.add(objEDLNos);
			 * arlCloseResultSet.add(objRefEDLNos);
			 * arlCloseResultSet.add(objPartOf);
			 * arlCloseResultSet.add(objCompName);
			 * arlCloseResultSet.add(objTableData);
			 * arlCloseResultSet.add(objStdEqpDesc);
			 */
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into DataAccess Exception block in DAO:.."
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil.logMessage("Enters into AppException block in  DAO :"
					+ objErrorInfo.getMessage());
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception block in DAO:"
					+ objExp.getMessage());
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(objClauseResultSet,
						objCallableStatement, objConnnection);
				
				// DBHelper.closeResultSets(arlCloseResultSet ,
				// objCallableStatement , objConnnection);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in finally DAO:"
						+ objExp.getMessage());
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return arlClause;
		
	}
	
	public static int updateNewClauseChange(ClauseVO objClauseVO)
	throws EMDException {
		LogUtil.logMessage("Inside AccRejDAO.update");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		int intLSDBErrorID = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strLogUser = "";
		
		try {
			strLogUser = objClauseVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in DAO in Update Method:"
					+ objConnnection);
			
			LogUtil.logMessage("objClauseVO.getOrderKey():"
					+ objClauseVO.getOrderKey());
			LogUtil.logMessage("objClauseVO.getClauseSeqNo():"
					+ objClauseVO.getClauseSeqNo());
			LogUtil
			.logMessage("objClauseVO.getFlag():"
					+ objClauseVO.getFlag());
			LogUtil.logMessage("objConnnection in DAO in Update Method:"
					+ objConnnection);
			
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_ACCREJ_ORDER_NEW_CLAUSE);
			
			objCallableStatement.setInt(1, objClauseVO.getOrderKey());
			objCallableStatement.setInt(2, objClauseVO.getClauseSeqNo());
			objCallableStatement.setString(3, objClauseVO.getFlag());
			objCallableStatement.setString(4, objClauseVO.getReason());
			objCallableStatement.setString(5, objClauseVO.getUserID());
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			LogUtil
			.logMessage("Inside the Update method of AccRejDAO :intStatus .."
					+ intStatus);
			intLSDBErrorID = (int) objCallableStatement.getInt(6);
			strOracleCode = (String) objCallableStatement.getString(7);
			strErrorMessage = (String) objCallableStatement.getString(8);
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
			.logMessage("Enters into DataAccess Exception block in DAO:.."
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil.logMessage("Enters into AppException block in  DAO :"
					+ objErrorInfo.getMessage());
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception block in DAO:"
					+ objExp.getMessage());
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				DBHelper.closeSQLObjects(null, objCallableStatement,
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
		
		return intStatus;
		
	}
	
}
