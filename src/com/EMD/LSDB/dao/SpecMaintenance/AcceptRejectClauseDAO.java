
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
import com.EMD.LSDB.vo.common.ComponentVO;

/**
 * @author VV49326
 * 
 */

/***********************************************************************
 ----------------------------------------------------------------------------------------------------------
 *    Date     Version   Modified by    	 Comments                              		Remarks 
 * 19/01/2010    1.0      RR68151       Added two new methods for Edl Indicator     Added for CR_81
 * 											Screen.  
 * 													 	 
 * 
 * --------------------------------------------------------------------------------------------------------
**************************************************************************/

public class AcceptRejectClauseDAO {
	
	private AcceptRejectClauseDAO() {
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objGenArrangeVO
	 *            The Object used for Fetch Clause Versions
	 * @return ArrayList The Arraylist containing Clause versions
	 * @throws EMDException
	 **************************************************************************/
	public static ArrayList fetchClauseVersions(ClauseVO objClauseVO)
	throws EMDException {
		LogUtil
		.logMessage("Entering AcceptRejectClauseDAO.fetchClauseVersions");
		
		Connection objConnnection = null;
		ArrayList arlClause = new ArrayList();
		ArrayList arlCloseResultSet = new ArrayList();
		CallableStatement objCallableStatement = null;
		ResultSet objClauseResultSet = null;
		ResultSet objEDLNos = null;
		ResultSet objRefEDLNos = null;
		ResultSet objSubSecSeqNos = null;
		ResultSet objTableData = null;
		ResultSet objStdEqpDesc = null;
		
		int intLSDBErrorID = 0;
		int countCol=0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strStdEquipdesc = null;
		
		String strLogUser = "";
		
		try {
			strLogUser = objClauseVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in DAO :" + objConnnection);
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_SELECT_ORDER_CLAUSE);
			
			objCallableStatement.setInt(1, objClauseVO.getClauseSeqNo());
			objCallableStatement.setInt(2, objClauseVO.getOrderKey());
			objCallableStatement.setString(3, objClauseVO.getUserID());
			
			objCallableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			objClauseResultSet = (ResultSet) objCallableStatement.getObject(4);
			
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
				
				ArrayList arledlNo = new ArrayList();
				ArrayList arlrefedlNo = new ArrayList();
				ArrayList subsecCode = new ArrayList();
				objEDLNos = null;
				objRefEDLNos = null;
				objSubSecSeqNos = null;
				objTableData = null;
				objStdEqpDesc = null;
				
				LogUtil.logMessage("enters into result set");
				objClauseVO.setClauseSeqNo(objClauseResultSet
						.getInt(DatabaseConstants.LS300_CLA_SEQ_NO));
				objClauseVO.setVersionNo(objClauseResultSet
						.getInt(DatabaseConstants.LS301_VERSION_NO));
				
				// objClauseVO.setClauseDesc(objClauseResultSet.getString(DatabaseConstants.LS301_CLA_DESC));
				String clauseDesc = objClauseResultSet
				.getString(DatabaseConstants.LS301_CLA_DESC);
				objClauseVO.setClauseDesc(clauseDesc);
				objClauseVO.setModelDefFlag(objClauseResultSet
						.getString(DatabaseConstants.LS301_DEFAULT_FLAG));
				objClauseVO.setCopyFlag(objClauseResultSet
						.getString(DatabaseConstants.COPY_FLAG));
				objClauseVO.setCustomerName(objClauseResultSet
						.getString(DatabaseConstants.LS050_CUST_NAME));
				/*commented for CR-114 
				objClauseVO.setModifiedBy(objClauseResultSet
						.getString(DatabaseConstants.LS301_UPDT_USER_ID));
				objClauseVO.setModifiedDate(objClauseResultSet
						.getString(DatabaseConstants.LS301_UPDT_DATE));*/
				//Added for CR-114
				objClauseVO.setCreatedBy(objClauseResultSet
						.getString(DatabaseConstants.LS301_CREATED_BY));
				objClauseVO.setCreatedDate(objClauseResultSet
						.getString(DatabaseConstants.LS301_CREATED_DATE));
				//Added for CR-114 Ends here
				objClauseVO.setComments(objClauseResultSet
						.getString(DatabaseConstants.LS301_ENGG_DATA_COMMENTS));
				objClauseVO.setDefaultFlag(objClauseResultSet
						.getString(DatabaseConstants.FLAG));
				
				// Added for LSDB_CR_49 for delete Clause
				objClauseVO.setDeleteFlag(objClauseResultSet
						.getString(DatabaseConstants.LS301_DELETE_FLAG));
				//Added for CR_109
				objClauseVO.setAppendixExitsFlag(objClauseResultSet
						.getString(DatabaseConstants.APPENDIX_EXISTS_FLAG));
				//Added for CR_109 Ends here
				
				//Added for CR_114
				objClauseVO.setAppendixImgSeqNo(objClauseResultSet
						.getInt(DatabaseConstants.LS170_IMG_SEQ_NO));
				//Added for CR_114 Ends Here
				
				objEDLNos = (ResultSet) objClauseResultSet.getObject("EDL_NO");
				while (objEDLNos.next()) {
					LogUtil.logMessage("Enters EDL Nos");
					arledlNo.add(objEDLNos
							.getString(DatabaseConstants.LS302_EDL_NO));
					LogUtil.logMessage("Exits EDL Nos");
					
				}
				
				objClauseVO.setEdlNO(arledlNo);
				
				objRefEDLNos = (ResultSet) objClauseResultSet
				.getObject("REF_EDL_NO");
				while (objRefEDLNos.next()) {
					LogUtil.logMessage("Enters REF EDL Nos");
					arlrefedlNo.add(objRefEDLNos
							.getString(DatabaseConstants.LS303_REF_EDL_NO));
					LogUtil.logMessage("Exists REF EDL  Nos");
				}
				objClauseVO.setRefEDLNO(arlrefedlNo);
				
				objSubSecSeqNos = (ResultSet) objClauseResultSet
				.getObject("PART_OF");
				while (objSubSecSeqNos.next()) {
					LogUtil.logMessage("Enters Part Of values");
					subsecCode.add(objSubSecSeqNos
							.getString(DatabaseConstants.LS407_PART_OF_CLA_NO));
					LogUtil.logMessage("Exits Part Of values");
					
				}
				objClauseVO.setPartOF(subsecCode);
				
				objTableData = (ResultSet) objClauseResultSet
				.getObject("TABLE_DATA");
				
				ArrayList arlTableList = new ArrayList();
				while (objTableData.next()) {
					LogUtil.logMessage("Enters Table Data");
					ArrayList arlTableData = new ArrayList();
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
					arlTableList.add(arlTableData);
					LogUtil.logMessage("Exists Table Data");
				}
//				Added For CR_93
     			countCol=ApplicationUtil.getTableDataColumnsCount(arlTableList);
				objClauseVO.setTableDataColSize(countCol);
				objClauseVO.setTableArrayData1(arlTableList);
				
				objClauseVO.setTableArrayData1(arlTableList);
				
				objClauseVO.setDwONumber(objClauseResultSet
						.getInt(DatabaseConstants.LS301_DWO_NUMBER));
				objClauseVO.setPartNumber(objClauseResultSet
						.getInt(DatabaseConstants.LS301_PART_NUMBER));
				objClauseVO.setPriceBookNumber(objClauseResultSet
						.getInt(DatabaseConstants.LS301_PRICE_BOOK_NUMBER));
				
				objStdEqpDesc = (ResultSet) objClauseResultSet
				.getObject("STD_EQP");
				while (objStdEqpDesc.next()) {
					LogUtil.logMessage("Enters Std Equipment");
					strStdEquipdesc = objStdEqpDesc
					.getString(DatabaseConstants.LS060_STD_EQP_DESC);
					objClauseVO.setStandardEquipmentDesc(strStdEquipdesc);
					LogUtil.logMessage("Exists Std Equipment");
				}
				
				arlClause.add(objClauseVO);
				
				arlCloseResultSet = new ArrayList();
				arlCloseResultSet.add(objEDLNos);
				arlCloseResultSet.add(objRefEDLNos);
				arlCloseResultSet.add(objSubSecSeqNos);
				arlCloseResultSet.add(objTableData);
				arlCloseResultSet.add(objStdEqpDesc);
				LogUtil.logMessage("Inside ResultSet8");
				DBHelper.closeResultSets(arlCloseResultSet, null, null);
				LogUtil.logMessage("Inside ResultSet9");
				
			}
			
		} catch (DataAccessException objDataExp) {
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
				
				DBHelper.closeSQLObjects(objClauseResultSet,
						objCallableStatement, objConnnection);
			}
			
			catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception exception...");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return arlClause;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objGenArrangeVO
	 *            The Object used to apply a particular clause version as
	 *            default at order level
	 * @return Boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	public static int updateClauseChange(ClauseVO objClauseVO)
	throws EMDException {
		LogUtil.logMessage("Entering AcceptRejectClauseDAO.updateClauseChange");
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
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_ACCREJ_ORDER_CLAUSE);
			objCallableStatement.setInt(1, objClauseVO.getOrderKey());
			objCallableStatement.setInt(2, objClauseVO.getClauseSeqNo());
			objCallableStatement.setInt(3, objClauseVO.getVersionNo());
			LogUtil.logMessage("objClauseVO.getVersionNo()" +objClauseVO.getVersionNo());
			objCallableStatement.setString(4, objClauseVO.getFlag());
			objCallableStatement.setString(5, objClauseVO.getIndFlag());
			LogUtil.logMessage("objClauseVO.getIndFlag()" +objClauseVO.getIndFlag());
			objCallableStatement.setString(6, objClauseVO.getReason());
			objCallableStatement.setString(7, objClauseVO.getUserID());
			objCallableStatement.registerOutParameter(8, Types.INTEGER);
			objCallableStatement.registerOutParameter(9, Types.VARCHAR);
			objCallableStatement.registerOutParameter(10, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			LogUtil
			.logMessage("Inside the Update method of ModelClauseDAO :intStatus .."
					+ intStatus);
			intLSDBErrorID = (int) objCallableStatement.getInt(8);
			strOracleCode = (String) objCallableStatement.getString(9);
			strErrorMessage = (String) objCallableStatement.getString(10);
			
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
				LogUtil.logMessage("Enters into Exception exception...");
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
	 * @param objGenArrangeVO
	 *            The Object used for Fetch Clause Versions
	 * @return ArrayList The Arraylist containing Clause versions
	 * @throws EMDException
	 **************************************************************************/
	public static ArrayList fetchDeletedClauseVersions(ClauseVO objClauseVO)
	throws EMDException {
		LogUtil
		.logMessage("Entering AcceptRejectClauseDAO.fetchDeletedClauseVersions");
		
		Connection objConnnection = null;
		ArrayList arlClause = new ArrayList();
		ArrayList arlCloseResultSet = new ArrayList();
		CallableStatement objCallableStatement = null;
		ResultSet objClauseResultSet = null;
		ResultSet objEDLNos = null;
		ResultSet objRefEDLNos = null;
		ResultSet objSubSecSeqNos = null;
		ResultSet objTableData = null;
		ResultSet objStdEqpDesc = null;
		ResultSet objModDelClaDetails = null; //Added for CR-134
		
		int intLSDBErrorID = 0;
		int countCol=0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strStdEquipdesc = null;
		//Added fro CR-134
		String strModClaFlag = null; 
		int nModVerNo = 0;
		//Ends here
		String strLogUser = "";
		
		
		try {
			strLogUser = objClauseVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in DAO :" + objConnnection);
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_FETCH_MDL_DELETED_CLAUSE);
			
			objCallableStatement.setInt(1, objClauseVO.getOrderKey());
			objCallableStatement.setInt(2, objClauseVO.getClauseSeqNo());
			objCallableStatement.setString(3, objClauseVO.getUserID());
			
			objCallableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			objClauseResultSet = (ResultSet) objCallableStatement.getObject(4);
			
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
				
				ArrayList arledlNo = new ArrayList();
				ArrayList arlrefedlNo = new ArrayList();
				ArrayList subsecCode = new ArrayList();
				objEDLNos = null;
				objRefEDLNos = null;
				objSubSecSeqNos = null;
				objTableData = null;
				objStdEqpDesc = null;
				
				LogUtil.logMessage("enters into result set");
				objClauseVO.setClauseSeqNo(objClauseResultSet
						.getInt(DatabaseConstants.LS300_CLA_SEQ_NO));
				
				objClauseVO.setVersionNo(objClauseResultSet
						.getInt(DatabaseConstants.LS301_VERSION_NO));
				
				// objClauseVO.setClauseDesc(objClauseResultSet.getString(DatabaseConstants.LS301_CLA_DESC));
				String clauseDesc = objClauseResultSet
				.getString(DatabaseConstants.LS301_CLA_DESC);
				objClauseVO.setClauseDesc(clauseDesc);
				
				objClauseVO.setCustomerName(objClauseResultSet
						.getString(DatabaseConstants.LS050_CUST_NAME));
				
				objClauseVO.setComments(objClauseResultSet
						.getString(DatabaseConstants.LS301_ENGG_DATA_COMMENTS));
				
				objClauseVO.setDwONumber(objClauseResultSet
						.getInt(DatabaseConstants.LS301_DWO_NUMBER));
				
				objClauseVO.setPartNumber(objClauseResultSet
						.getInt(DatabaseConstants.LS301_PART_NUMBER));
				
				objClauseVO.setPriceBookNumber(objClauseResultSet
						.getInt(DatabaseConstants.LS301_PRICE_BOOK_NUMBER));
				
				objClauseVO.setClauseSource(objClauseResultSet
						.getString(DatabaseConstants.LS190_CLA_SOURCE_CODE));
				
				objEDLNos = (ResultSet) objClauseResultSet.getObject("EDLNO");
				while (objEDLNos.next()) {
					LogUtil.logMessage("Enters EDL Nos");
					arledlNo.add(objEDLNos
							.getString(DatabaseConstants.LS302_EDL_NO));
					LogUtil.logMessage("Exits EDL Nos");
					
				}
				
				objClauseVO.setEdlNO(arledlNo);
				
				objRefEDLNos = (ResultSet) objClauseResultSet
				.getObject("refEDLNO");
				while (objRefEDLNos.next()) {
					LogUtil.logMessage("Enters REF EDL Nos");
					arlrefedlNo.add(objRefEDLNos
							.getString(DatabaseConstants.LS303_REF_EDL_NO));
					LogUtil.logMessage("Exists REF EDL  Nos");
				}
				objClauseVO.setRefEDLNO(arlrefedlNo);
				
				objSubSecSeqNos = (ResultSet) objClauseResultSet
				.getObject("PartOF");
				while (objSubSecSeqNos.next()) {
					LogUtil.logMessage("Enters Part Of values");
					subsecCode.add(objSubSecSeqNos
							.getString(DatabaseConstants.LS407_PART_OF_CLA_NO));
					LogUtil.logMessage("Exits Part Of values");
					
				}
				objClauseVO.setPartOF(subsecCode);
				
				objTableData = (ResultSet) objClauseResultSet
				.getObject("TABLE_DATA");
				
				ArrayList arlTableList = new ArrayList();
				while (objTableData.next()) {
					LogUtil.logMessage("Enters Table Data");
					ArrayList arlTableData = new ArrayList();
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
					arlTableList.add(arlTableData);
					LogUtil.logMessage("Exists Table Data");
				}
//				Added For CR_93
     			countCol=ApplicationUtil.getTableDataColumnsCount(arlTableList);
				objClauseVO.setTableDataColSize(countCol);
				objClauseVO.setTableArrayData1(arlTableList);
				
				objStdEqpDesc = (ResultSet) objClauseResultSet
				.getObject("STD_EQUIP");
				while (objStdEqpDesc.next()) {
					LogUtil.logMessage("Enters Std Equipment");
					strStdEquipdesc = objStdEqpDesc
					.getString(DatabaseConstants.LS060_STD_EQP_DESC);
					objClauseVO.setStandardEquipmentDesc(strStdEquipdesc);
					LogUtil.logMessage("Exists Std Equipment");
				}
				//Added fro CR-134 starts here
				objModDelClaDetails = (ResultSet) objClauseResultSet
				.getObject("NEW_MDL_CLA_VER");
				while (objModDelClaDetails.next()) {
					LogUtil.logMessage("Enters Model Delete Clause Details");
					strModClaFlag = objModDelClaDetails.getString(DatabaseConstants.NEW_MDL_CLA_FLAG);
					objClauseVO.setDelModFlag(strModClaFlag);
					nModVerNo = objModDelClaDetails.getInt(DatabaseConstants.LS301_VERSION_NO);
					objClauseVO.setModVersionNo(nModVerNo);
					LogUtil.logMessage("objClauseVO.getModVersionNo" +objClauseVO.getModVersionNo());
					LogUtil.logMessage("objClauseVO.setDelModFlag" +objClauseVO.getDelModFlag());
				}
				//Added for CR-134 ends here
				
				arlClause.add(objClauseVO);
				
				arlCloseResultSet = new ArrayList();
				arlCloseResultSet.add(objEDLNos);
				arlCloseResultSet.add(objRefEDLNos);
				arlCloseResultSet.add(objSubSecSeqNos);
				arlCloseResultSet.add(objTableData);
				arlCloseResultSet.add(objStdEqpDesc);
				arlCloseResultSet.add(objModDelClaDetails);//Added for CR-134
				LogUtil.logMessage("Inside ResultSet8");
				DBHelper.closeResultSets(arlCloseResultSet, null, null);
				LogUtil.logMessage("Inside ResultSet9");
				
			}
			
		} catch (DataAccessException objDataExp) {
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
			objExp.printStackTrace();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(objClauseResultSet,
						objCallableStatement, objConnnection);
			}
			
			catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception exception...");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return arlClause;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objGenArrangeVO
	 *            The Object used to apply a particular clause version as
	 *            default at order level
	 * @return Boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	public static int updateDeleteClause(ClauseVO objClauseVO)
	throws EMDException {
		LogUtil.logMessage("Entering AcceptRejectClauseDAO.updateDeleteClause");
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
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_ACCRJT_DELETE_CLAUSE);
			objCallableStatement.setInt(1, objClauseVO.getOrderKey());
			objCallableStatement.setInt(2, objClauseVO.getClauseSeqNo());
			objCallableStatement.setString(3, objClauseVO.getFlag());
			//Added for LSDB_CR-74
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
			.logMessage("Inside the Update method of ModelClauseDAO :intStatus .."
					+ intStatus);
			intLSDBErrorID = (int) objCallableStatement.getInt(6);
			strOracleCode = (String) objCallableStatement.getString(7);
			strErrorMessage = (String) objCallableStatement.getString(8);
			
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
				LogUtil.logMessage("Enters into Exception exception...");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return intStatus;
		
	}

//Added for CR_81 EDL Change Indicator by RR68151 on 13-Jan-10 - Starts here

	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objClauseVO
	 *            The Object used for fetching the Edl Changes for Clause
	 * @return ArrayList The Arraylist containing Clause versions
	 * @throws EMDException
	 **************************************************************************/
	public static ArrayList fetchClauseEdlChanges(ClauseVO objClauseVO)
	throws EMDException {
		LogUtil
		.logMessage("Entering AcceptRejectClauseDAO.fetchClauseEdlChanges");
		
		Connection objConnnection = null;
		ArrayList arlClause = new ArrayList();
		ArrayList arlClaEdlChngList = new ArrayList();
		CallableStatement objCallableStatement = null;
		ResultSet objClauseResultSet = null;
		ResultSet objOldCompCombntn = null;
		ResultSet objNewCompCombntn = null;
		
		int intLSDBErrorID = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intClaEdlChngType = 0;
		
		String strLogUser = "";
		
		try {
			strLogUser = objClauseVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in DAO :" + objConnnection);
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_SELECT_MDL_CLA_EDL_CHANGES);
			
			objCallableStatement.setInt(1, objClauseVO.getOrderKey());
			objCallableStatement.setInt(2, objClauseVO.getClauseSeqNo());
			objCallableStatement.setString(3, objClauseVO.getUserID());
			
			objCallableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			objClauseResultSet = (ResultSet) objCallableStatement.getObject(4);
			intClaEdlChngType = (int) objCallableStatement.getInt(5);
			
			intLSDBErrorID = (int) objCallableStatement.getInt(6);
			strOracleCode = (String) objCallableStatement.getString(7);
			strErrorMessage = (String) objCallableStatement.getString(8);
			
			// Handled Valid Exception
			if (intLSDBErrorID != 0) {
				
				ErrorInfo objErrorInfo = new ErrorInfo();
				
				objErrorInfo.setMessageID("" + intLSDBErrorID);
				
				throw new DataAccessException(objErrorInfo);
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {// Un
				// handled exception
				
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
				
				ArrayList arlOldComp = new ArrayList();
				
				ArrayList arlNewComp = new ArrayList();
				
				LogUtil.logMessage("Enters into Result Set");
				
				objClauseVO.setClaEdlChngType(intClaEdlChngType);
				
				objClauseVO.setClauseSeqNo(objClauseResultSet.getInt(DatabaseConstants.LS300_CLA_SEQ_NO));
				
				objClauseVO.setCharEdlNo(objClauseResultSet.getString(DatabaseConstants.NEW_EDL));
				
				objClauseVO.setCharRefEDLNo(objClauseResultSet.getString(DatabaseConstants.NEW_REF_EDL));

				objClauseVO.setOldCharEdlNo(objClauseResultSet.getString(DatabaseConstants.OLD_EDL));
				
				objClauseVO.setOldCharRefEDLNo(objClauseResultSet.getString(DatabaseConstants.OLD_REF_EDL));
				
				objClauseVO.setModifiedDate(objClauseResultSet.getString(DatabaseConstants.UPDT_DATE ));
				
				objClauseVO.setModifiedBy(objClauseResultSet.getString(DatabaseConstants.UPDT_USER_ID));
				
				objOldCompCombntn = (ResultSet) objClauseResultSet.getObject("OLD_COMP_COMBNTN");
				
				while (objOldCompCombntn.next()) {
					
					LogUtil.logMessage("Enters into Old Comp Combntn ResultSet");

					ComponentVO objComponentVo = new ComponentVO();
					
					objComponentVo.setComponentName(objOldCompCombntn
							.getString(DatabaseConstants.LS140_COMP_NAME));
					
					arlOldComp.add(objComponentVo);
					
				}
			
				objClauseVO.setComponentVO(arlOldComp);
				
				DBHelper.closeSQLObjects(objOldCompCombntn, null, null);
				
				objNewCompCombntn = (ResultSet) objClauseResultSet.getObject("NEW_COMP_COMBNTN");
				
				while (objNewCompCombntn.next()) {
					LogUtil.logMessage("Enters into New Comp Combntn ResultSet");
										
					arlNewComp.add(objNewCompCombntn
							.getString(DatabaseConstants.LS140_COMP_NAME));
					
				}
				LogUtil.logMessage("New Component List Size: " + arlNewComp.size());
				
				objClauseVO.setComponentList(arlNewComp);
				
				DBHelper.closeSQLObjects(objNewCompCombntn, null, null);
				
				arlClaEdlChngList.add(objClauseVO);
				
				arlClause.add(arlClaEdlChngList);
				
			}
			
		} catch (DataAccessException objDataExp) {
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
			objExp.printStackTrace();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(objClauseResultSet,
						objCallableStatement, objConnnection);
			}
			
			catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception exception...");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return arlClause;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objClauseVO
	 *            The Object used to apply a particular clause version as
	 *            default at order level
	 * @return Boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	public static int updateClauseEdlChange(ClauseVO objClauseVO)
	throws EMDException {
		LogUtil.logMessage("Entering AcceptRejectClauseDAO.updateClauseEdlChange");
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
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_ACCREJ_CLAUSE_EDL_CHANGES);
			objCallableStatement.setInt(1, objClauseVO.getOrderKey());
			objCallableStatement.setInt(2, objClauseVO.getClauseSeqNo());
			objCallableStatement.setString(3, objClauseVO.getReason());
			objCallableStatement.setString(4, objClauseVO.getFlag());
			objCallableStatement.setString(5, objClauseVO.getUserID());
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			LogUtil
			.logMessage("Inside the Update method of AcceptRejectClauseDAO :intStatus .."
					+ intStatus);
			intLSDBErrorID = (int) objCallableStatement.getInt(6);
			strOracleCode = (String) objCallableStatement.getString(7);
			strErrorMessage = (String) objCallableStatement.getString(8);
			
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Enters into Error Loop in Update Method:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				
				objErrorInfo.setMessageID("" + intLSDBErrorID);
				LogUtil.logMessage("Error message in ErrorInfo:"
						+ objErrorInfo.getMessageID());
				throw new DataAccessException(objErrorInfo);
				
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {// Un
				// handled exception
				
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
				LogUtil.logMessage("Enters into Exception exception...");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return intStatus;
		
	}
	
//Added for CR_81 EDL Change Indicator by RR68151 on 13-Jan-10 - Ends here
}
