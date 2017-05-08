/**
 * 
 */
package com.EMD.LSDB.dao.MasterMaintenance;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.tomcat.dbcp.dbcp2.DelegatingConnection;//Added for CR-123 & Tomcat

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
import com.EMD.LSDB.dao.common.EMDQueries;
import com.EMD.LSDB.vo.common.ClauseTableDataVO;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.StandardEquipVO;

/**
 * @author VV49326
 *  
 */

/***************************************************************************
 * ------------------------------------------------------------------------------------------------------
 *     Date         version  create by   modify by             comments                              Remarks 
 * 19/01/2010        1.0      SD41630                 Added fetchClauseVersions  mehtod         Added for CR_81
 * 													  for view characterisitic flag setting   
 * 													  Object	 
 * 
 * --------------------------------------------------------------------------------------------------------
  **************************************************************************/
public class ModelSelectClauseRevDAO {
	
	private ModelSelectClauseRevDAO() {
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objClauseVO
	 *            The Object used for Fetch Clause Versions
	 * @return ArrayList The Arraylist containing Clause versions
	 * @throws EMDException
	 **************************************************************************/
	public static ArrayList fetchClauseVersions(ClauseVO objClauseVO)
	throws EMDException {
		LogUtil
		.logMessage("Entering ModelSelectClauseRevDAO.fetchClauseVersions");
		
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		
		ResultSet objClauseResultSet = null;
		ResultSet objEDLNoResultSet = null;
		ResultSet objRefEDLNoResultSet = null;
		ResultSet objSubSecResultSet = null;
		ResultSet objTbDataResultSet = null;
		ResultSet objStdEqpResultSet = null;
		ResultSet objClauseComp = null;
		
		ArrayList arlClauseList = new ArrayList();
		
		ArrayList arlTableColumns = null;
		ArrayList arlAllVersion = new ArrayList();
		ArrayList arlDefaultVersion = new ArrayList();
		int countCol=0;
		
		String strLogUser = "";
		try {
			strLogUser = objClauseVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_SELECT_MDL_CLAUSE);
			LogUtil
			.logMessage("ModelSeqNo ModelSelectClauseRevDAO:fetchClauses:"
					+ objClauseVO.getModelSeqNo());
			objCallableStatement.setInt(1, objClauseVO.getModelSeqNo());
			LogUtil
			.logMessage("SubSectionSeqNo ModelSelectClauseRevDAO:fetchClauses:"
					+ objClauseVO.getSubSectionSeqNo());
			objCallableStatement.setInt(2, objClauseVO.getSubSectionSeqNo());
			LogUtil
			.logMessage("ClauseSeqNo ModelSelectClauseRevDAO:fetchClauses:"
					+ objClauseVO.getClauseSeqNo());
			LogUtil
			.logMessage("VersionNo ModelSelectClauseRevDAO:fetchClauses:"
					+ objClauseVO.getVersionNo());
			if (objClauseVO.getClauseSeqNo() == 0) {
				objCallableStatement.setNull(3, Types.NULL);
				
			} else {
				objCallableStatement.setInt(3, objClauseVO.getClauseSeqNo());
			}
			if (objClauseVO.getVersionNo() == 0) {
				objCallableStatement.setNull(4, Types.NULL);
			} else {
				objCallableStatement.setInt(4, objClauseVO.getVersionNo());
			}
			if (objClauseVO.getDefaultFlag() == null) {
				objCallableStatement.setNull(5, Types.NULL);
			} else {
				objCallableStatement.setString(5, objClauseVO.getDefaultFlag());
			}
			objCallableStatement.registerOutParameter(6, OracleTypes.CURSOR);
			objCallableStatement.setString(7, objClauseVO.getUserID());
			objCallableStatement.registerOutParameter(8, Types.INTEGER);
			objCallableStatement.registerOutParameter(9, Types.VARCHAR);
			objCallableStatement.registerOutParameter(10, Types.VARCHAR);
			objCallableStatement.execute();
			
			objClauseResultSet = (ResultSet) objCallableStatement.getObject(6);
			
			intLSDBErrorID = objCallableStatement.getInt(8);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(9);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(10);
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
			
			while (objClauseResultSet.next()) {
				ArrayList arledlNo = new ArrayList();
				ArrayList arlrefedlNo = new ArrayList();
				ArrayList subsecCode = new ArrayList();
				ArrayList arlPartOfClaNo = new ArrayList();
				ArrayList arlClaComp = new ArrayList();
				int cntEDL = 0;
				int cntRefEDl = 0;
				int cntPartOf = 0;
				int cntCompSeqno = 0;
				int[] arlPartSubSecSeqNo = new int[4];
				int[] crPartclaSeqNo = new int[4];
				String[] arlPartclaSeqNo = { "", "", "", "" };
				String[] arlEDLNos = { "", "", "" };
				String[] arlRefEDLNos = { "", "", "" };
				String[] arlpartSubSecCode = { "", "", "", "" };
				String[] strCompSeqNo = new String[25];
				objClauseVO = new ClauseVO();
				
				objClauseVO.setClauseSeqNo(objClauseResultSet
						.getInt(DatabaseConstants.LS300_CLA_SEQ_NO));
				LogUtil.logMessage("ClauseSeqNo:"
						+ objClauseVO.getClauseSeqNo());
				objClauseVO.setVersionNo(objClauseResultSet
						.getInt(DatabaseConstants.LS301_VERSION_NO));
				LogUtil.logMessage("VersionNo:" + objClauseVO.getVersionNo());
				objClauseVO.setClauseDesc(objClauseResultSet
						.getString(DatabaseConstants.LS301_CLA_DESC));
				LogUtil.logMessage("ClauseDesc:" + objClauseVO.getClauseDesc());
				objClauseVO.setClauseDescForTextArea(objClauseResultSet
						.getString(DatabaseConstants.LS301_CLA_DESC));
				objClauseVO.setCustomerName(objClauseResultSet
						.getString(DatabaseConstants.LS050_CUST_NAME));
				LogUtil.logMessage("CustomerName:"
						+ objClauseVO.getCustomerName());
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
				objClauseVO.setDefaultFlag(objClauseResultSet
						.getString(DatabaseConstants.LS301_DEFAULT_FLAG));
//				Added For CR_81 on 24-Dec-09 by sd41630
				objClauseVO.setSelectCGCFlag(objClauseResultSet.getString(DatabaseConstants.LS300_CHAR_GRP_FLAG));
//				Added For CR_81 on 24-Dec-09 by sd41630 Ends here
				//Added for CR_109
				objClauseVO.setAppendixExitsFlag(objClauseResultSet
						.getString(DatabaseConstants.APPENDIX_EXISTS_FLAG));
				//Added for CR_109 Ends here
				objClauseVO.setAppendixImgSeqNo(objClauseResultSet
						.getInt(DatabaseConstants.LS170_IMG_SEQ_NO));
				//Added for CR-121
				objClauseVO.setSubClaExistsFlag(objClauseResultSet
						.getString(DatabaseConstants.SUB_CLAUSE_EXISTS));
				//Added for CR_134
				objClauseVO.setUsedVersionFlag(objClauseResultSet.getString(DatabaseConstants.USED_VERSION_FLAG));
				objClauseVO.setUserMarkFlag(objClauseResultSet.getString(DatabaseConstants.LS301_USR_MARKER));
				objClauseVO.setMarkClaReason(objClauseResultSet.getString(DatabaseConstants.LS301_USR_MARKED_REASON));
				//Added for CR_134 Ends here
				objEDLNoResultSet = (ResultSet) objClauseResultSet.getObject(8);
				
				while (objEDLNoResultSet.next()) {
					LogUtil.logMessage("Enters into EDLNo Resultset Loop:");
					arlEDLNos[cntEDL] = objEDLNoResultSet
					.getString(DatabaseConstants.LS302_EDL_NO);/*
					* This
					* String
					* array
					* is
					* used
					* to
					* display
					* the
					* EDL
					* number
					* in Add
					* Clause
					* Revision
					* Engineering
					* data
					* EDL
					* number
					* textbox
					*/
					arledlNo.add(objEDLNoResultSet
							.getString(DatabaseConstants.LS302_EDL_NO));/*
							* this
							* arraylist
							* is
							* used
							* to
							* display
							* the
							* EDL
							* number
							* in
							* Select
							* clause
							* revision
							* table
							* Engineering
							* data
							* column
							*/
					
					cntEDL++;
					
				}
				LogUtil.logMessage("Size of EDL NO ArrayList:"
						+ arledlNo.size());
				objClauseVO.setEdlNo(arlEDLNos);
				objClauseVO.setEdlNO(arledlNo);
				LogUtil.logMessage("Length of EdlNumber:" + arlEDLNos.length);
				
				DBHelper.closeSQLObjects(objEDLNoResultSet, null, null);
				
				objRefEDLNoResultSet = (ResultSet) objClauseResultSet
				.getObject(9);
				while (objRefEDLNoResultSet.next()) {
					LogUtil.logMessage("Enters into RefEDLNo Resultset Loop:");
					arlRefEDLNos[cntRefEDl] = objRefEDLNoResultSet
					.getString(DatabaseConstants.LS303_REF_EDL_NO);
					arlrefedlNo.add(objRefEDLNoResultSet
							.getString(DatabaseConstants.LS303_REF_EDL_NO));
					
					cntRefEDl++;
					
				}
				
				LogUtil.logMessage("Length of RefEdlNumber:"
						+ arlRefEDLNos.length);
				objClauseVO.setRefEDLNo(arlRefEDLNos);
				objClauseVO.setRefEDLNO(arlrefedlNo);
				
				DBHelper.closeSQLObjects(objRefEDLNoResultSet, null, null);
				
				objSubSecResultSet = (ResultSet) objClauseResultSet
				.getObject(10);
				
				while (objSubSecResultSet.next()) {
					LogUtil
					.logMessage("PartCode Of values"
							+ objSubSecResultSet
							.getString(DatabaseConstants.LS304_SUBSEC_NO));
					arlpartSubSecCode[cntPartOf] = objSubSecResultSet
					.getString(DatabaseConstants.LS304_SUBSEC_NO);
					arlPartSubSecSeqNo[cntPartOf] = objSubSecResultSet
					.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO);
					arlPartclaSeqNo[cntPartOf] = objSubSecResultSet
					.getString(DatabaseConstants.LS304_PART_OF_CLA_SEQ_NO);
					crPartclaSeqNo[cntPartOf] = objSubSecResultSet
					.getInt(DatabaseConstants.LS304_PART_OF_CLA_SEQ_NO);
					subsecCode.add(objSubSecResultSet
							.getString(DatabaseConstants.LS304_SUBSEC_NO));
					arlPartOfClaNo
					.add(objSubSecResultSet
							.getString(DatabaseConstants.LS304_PART_OF_CLA_NUMBER));
					
					cntPartOf++;
					
				}
				
				/***************************************************************
				 * one more parameter partOfclaSeqNo is selecting from part of
				 * cursor based on LSDB_CR-48 * Added on 04-Aug-08 by ps57222
				 *  
				 **************************************************************/
				
				objClauseVO.setPartOfCode(arlpartSubSecCode);
				objClauseVO.setPartOF(subsecCode);
				LogUtil.logMessage("PartOfCode:" + objClauseVO.getPartOfCode());
				LogUtil.logMessage("Length of PartOfCode:"
						+ arlpartSubSecCode.length);
				objClauseVO.setPartOfSeqNo(arlPartSubSecSeqNo);
				LogUtil.logMessage("PartOfSeqNo:"
						+ objClauseVO.getPartOfSeqNo());
				objClauseVO.setPartOfClaSeqNo(arlPartclaSeqNo);
				objClauseVO.setReqpartOfClaSeqNo(crPartclaSeqNo);
				LogUtil.logMessage("PartOfClaSeqNo:"
						+ objClauseVO.getPartOfClaSeqNo());
				objClauseVO.setPartOfClaNo(arlPartOfClaNo);
				
				DBHelper.closeSQLObjects(objSubSecResultSet, null, null);
				
				objTbDataResultSet = (ResultSet) objClauseResultSet
				.getObject(11);
				
				ArrayList arlTableRows = new ArrayList();
				while (objTbDataResultSet.next()) {
					LogUtil.logMessage("Enters into TableData Resultset Loop:");
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
					
					LogUtil
					.logMessage("headerflag:"
							+ objTbDataResultSet
							.getString(DatabaseConstants.LS306_HEADER_FLAG));
				}
//				Added For CR_93
     			countCol=ApplicationUtil.getTableDataColumnsCount(arlTableRows);
				objClauseVO.setTableDataColSize(countCol);
				objClauseVO.setTableArrayData1(arlTableRows);
				
				DBHelper.closeSQLObjects(objTbDataResultSet, null, null);
				
				objClauseVO.setDwONumber(objClauseResultSet
						.getInt(DatabaseConstants.LS301_DWO_NUMBER));
				LogUtil.logMessage("DWO Number:" + objClauseVO.getDwONumber());
				objClauseVO.setPartNumber(objClauseResultSet
						.getInt(DatabaseConstants.LS301_PART_NUMBER));
				LogUtil
				.logMessage("Part Number:"
						+ objClauseVO.getPartNumber());
				objClauseVO.setPriceBookNumber(objClauseResultSet
						.getInt(DatabaseConstants.LS301_PRICE_BOOK_NUMBER));
				LogUtil.logMessage("Price book number:"
						+ objClauseVO.getPriceBookNumber());
				
				objStdEqpResultSet = (ResultSet) objClauseResultSet
				.getObject(15);
				while (objStdEqpResultSet.next()) {
					LogUtil.logMessage("Enters into std equip ResultSet");
					objClauseVO.setStandardEquipmentDesc(objStdEqpResultSet
							.getString(DatabaseConstants.LS060_STD_EQP_DESC));
					objClauseVO.setStandardEquipmentSeqNo(objStdEqpResultSet
							.getInt(DatabaseConstants.LS060_STD_EQP_SEQ_NO));
					LogUtil.logMessage("StandardEquipmentDesc:"
							+ objClauseVO.getStandardEquipmentDesc());
					
				}
				
				DBHelper.closeSQLObjects(objStdEqpResultSet, null, null);
				
				objClauseVO.setComments(objClauseResultSet
						.getString(DatabaseConstants.LS301_ENGG_DATA_COMMENTS));
				LogUtil.logMessage("Comments:" + objClauseVO.getComments());
				objClauseVO.setReason(objClauseResultSet
						.getString(DatabaseConstants.LS301_CLA_REASON));
				LogUtil.logMessage("Reason:" + objClauseVO.getReason());
				
				objClauseComp = (ResultSet) objClauseResultSet.getObject(18);
				while (objClauseComp.next()) {
					
					LogUtil.logMessage("Enters into Clause Comp ResultSet");
					arlClaComp.add(objClauseComp
							.getString(DatabaseConstants.LS140_COMP_NAME));
					strCompSeqNo[cntCompSeqno] = objClauseComp
					.getString(DatabaseConstants.LS140_COMP_SEQ_NO);
					cntCompSeqno++;
					
				}
				
				objClauseVO.setCompName(arlClaComp);
				/** Added for LSDB_CR-45 on 11-Nov-08 by ps57222* */
				objClauseVO.setComponentSeqNo(ApplicationUtil
						.convertStringArrayToString(strCompSeqNo));
				DBHelper.closeSQLObjects(objClauseComp, null, null);
				
				arlAllVersion.add(objClauseVO);
				
				if ("Y".equals(objClauseResultSet
						.getString(DatabaseConstants.LS301_DEFAULT_FLAG))) {
					arlDefaultVersion.add(objClauseVO);
				}
				
			}
			LogUtil.logMessage("ArrayList Size For AllVersions:"
					+ arlAllVersion.size());
			LogUtil.logMessage("ArrayList Size For DefaultVersion:"
					+ arlDefaultVersion.size());
			
			arlClauseList.add(arlAllVersion);
			arlClauseList.add(arlDefaultVersion);
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into DataAccessException block in ModelSelectClauseRevDAO:fetchClauses"
					+ objDataExp.getMessage());
			LogUtil
			.logMessage("ENters into DataAccessException block in ModelSelectClauseRevDAO:fetchClauses"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into ApplicationException block in ModelSelectClauseRevDAO:fetchClauses"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
			
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelSelectClauseRevDAO:fetchClauses:");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(objClauseResultSet,
						objCallableStatement, objConnection);
				
			}
			
			catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in ModelSelectClauseRevDAO:fetchClauses:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
		}
		LogUtil
		.logMessage("arlParentClauseList size..."
				+ arlClauseList.size());
		return arlClauseList;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objGenArrangeVO
	 *            The Object used for Fetch Clause Versions
	 * @return Boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	public static int updateApplyDefaultClause(ClauseVO objClauseVO)
	throws EMDException {
		LogUtil
		.logMessage("Entering ModelSelectClauseRevDAO.updateApplyDefaultClause");
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
			.prepareCall(EMDQueries.SP_APPLY_MDL_DEF_CLAUSE);
			
			objCallableStatement.setInt(1, objClauseVO.getClauseSeqNo());
			objCallableStatement.setInt(2, objClauseVO.getVersionNo());
			objCallableStatement.setString(3, objClauseVO.getReason());
			objCallableStatement.setString(4, objClauseVO.getUserID());
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			LogUtil
			.logMessage("Inside the Update method of ModelSelectClauseRevDAO :intStatus .."
					+ intStatus);
			intLSDBErrorID = (int) objCallableStatement.getInt(5);
			strOracleCode = (String) objCallableStatement.getString(6);
			strErrorMessage = (String) objCallableStatement.getString(7);
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
				
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {//Un
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
			.logMessage("Enters into catch block in ModelSelectClauseRevDAO:updateApplyDefaultClause"
					+ objDataExp.getErrorInfo().getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ModelSelectClauseRevDAO:updateApplyDefaultClause"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelSelectClauseRevDAO:updateApplyDefaultClause");
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
				.logMessage("Enters into Exception block in ModelSelectClauseRevDAO:updateApplyDefaultClause");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + sqlex.getMessage());
				throw new ApplicationException(sqlex, objErrorInfo);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in ModelSelectClauseRevDAO:updateApplyDefaultClause");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
				
			}
			
		}
		
		return intStatus;
		
	}
	
	/***************************************************************************
	 * @author This Method deleteClause is used to delete the clause and all
	 *         it's subclauses and it will delete all it's versions
	 * @version 1.0
	 * @param objClauseVO
	 * @return int The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	public static int deleteClause(ClauseVO objClauseVO) throws EMDException {
		LogUtil.logMessage("Entering ModelSelectClauseRevDAO.deleteClause");
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
			LogUtil
			.logMessage("objConnnection in DAO in ModelSelectClauseRevDAO.deleteClause:"
					+ objConnnection);
			
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_DELETE_MDL_CLAUSE);
			
			objCallableStatement.setInt(1, objClauseVO.getClauseSeqNo());
			objCallableStatement.setString(2, objClauseVO.getUserID());
			objCallableStatement.registerOutParameter(3, Types.INTEGER);
			objCallableStatement.registerOutParameter(4, Types.VARCHAR);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			LogUtil
			.logMessage("Inside the Update method of ModelSelectClauseRevDAO.deleteClause:intStatus .."
					+ intStatus);
			intLSDBErrorID = (int) objCallableStatement.getInt(3);
			strOracleCode = (String) objCallableStatement.getString(4);
			strErrorMessage = (String) objCallableStatement.getString(5);
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
				
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {//Un
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
			.logMessage("Enters into catch block in ModelSelectClauseRevDAO:deleteClause"
					+ objDataExp.getErrorInfo().getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ModelSelectClauseRevDAO:deleteClause"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelSelectClauseRevDAO:deleteClause");
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
				.logMessage("Enters into Exception block in ModelSelectClauseRevDAO:deleteClause");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + sqlex.getMessage());
				throw new ApplicationException(sqlex, objErrorInfo);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in ModelSelectClauseRevDAO:deleteClause");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
				
			}
			
		}
		
		return intStatus;
		
	}
	
	/***************************************************************************
	 * @author This Method deleteVersion is used to delete the selected version
	 *         of a clause and all it's subclauses.
	 * @version 1.0
	 * @param objClauseVO
	 * @return int The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	public static int deleteVersion(ClauseVO objClauseVO) throws EMDException {
		LogUtil.logMessage("Entering ModelSelectClauseRevDAO.deleteVersion");
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
			LogUtil
			.logMessage("objConnnection in DAO in ModelSelectClauseRevDAO.deleteVersion:"
					+ objConnnection);
			
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_DELETE_MDL_CLAUSE_VER);
			
			objCallableStatement.setInt(1, objClauseVO.getClauseSeqNo());
			objCallableStatement.setInt(2, objClauseVO.getVersionNo());
			objCallableStatement.setString(3, objClauseVO.getUserID());
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			LogUtil
			.logMessage("Inside the Update method of ModelSelectClauseRevDAO.deleteVersion:intStatus .."
					+ intStatus);
			intLSDBErrorID = (int) objCallableStatement.getInt(4);
			strOracleCode = (String) objCallableStatement.getString(5);
			strErrorMessage = (String) objCallableStatement.getString(6);
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Error ID:" + intLSDBErrorID);
				LogUtil.logMessage("strOracleCode:" + strOracleCode);
				
			}
			if (intLSDBErrorID != 0) {
				LogUtil
				.logMessage("Enters into Error Loop in ModelSelectClauseRevDAO.deleteVersion:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				
				objErrorInfo.setMessageID("" + intLSDBErrorID);
				LogUtil.logMessage("Error message in ErrorInfo:"
						+ objErrorInfo.getMessageID());
				throw new DataAccessException(objErrorInfo);
				
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {//Un
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
			.logMessage("Enters into catch block in ModelSelectClauseRevDAO:deleteVersion"
					+ objDataExp.getErrorInfo().getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ModelSelectClauseRevDAO:deleteVersion"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelSelectClauseRevDAO:deleteVersion");
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
				.logMessage("Enters into Exception block in ModelSelectClauseRevDAO:deleteVersion");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + sqlex.getMessage());
				throw new ApplicationException(sqlex, objErrorInfo);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in ModelSelectClauseRevDAO:deleteVersion");
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
	 * @param objClauseVO
	 *            the object for Updating Clause
	 * @return boolean that returns True or False
	 * @throws EMDException
	 **************************************************************************/
	public static synchronized int updateClause(ClauseVO objClauseVO)
	throws EMDException {
		LogUtil.logMessage("Inside the ModelClauseDAO:updateClause");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		ArrayDescriptor arrdesc = null;
		ArrayList arlStandardEquipmentList = new ArrayList();
		ARRAY arr = null;
		ClauseTableDataVO objTableDataVO = null;
		ArrayList arlTableList;
		int intClauseSeqNo = 0;
		int intClauseVersionNo = 0;
		ARRAY tableDataArr1, tableDataArr2, tableDataArr3, tableDataArr4, tableDataArr5 = null;
		String strLogUser = "";
		try {
			strLogUser = objClauseVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_UPDATE_MDL_CLAUSE);
			Connection dconn = ((DelegatingConnection) objConnnection).getInnermostDelegate(); //Added for CR-123 & Tomcat
			objCallableStatement.setInt(1, objClauseVO.getClauseSeqNo());
			objCallableStatement.setInt(2, objClauseVO.getVersionNo());
			objCallableStatement.setString(3, objClauseVO.getClauseDesc());
			arrdesc = new ArrayDescriptor(DatabaseConstants.STR_ARRAY,
					dconn);
			
			ARRAY arrEdlno = new ARRAY(arrdesc, dconn, objClauseVO
					.getEdlNo());
			if (arrEdlno.length() == 0) {
				objCallableStatement.setNull(4, Types.NULL);
			} else {
				objCallableStatement.setArray(4, arrEdlno);
			}
			ARRAY arrRefEDLNO = new ARRAY(arrdesc, dconn, objClauseVO
					.getRefEDLNo());
			
			if (objClauseVO.getRefEDLNo() == null) {
				arrRefEDLNO = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(5, arrRefEDLNO);
			} else {
				objCallableStatement.setArray(5, arrRefEDLNO);
			}
			
			ARRAY arrPartOfSeqNO = new ARRAY(arrdesc, dconn,
					objClauseVO.getPartOfSeqNo());
			if (arrPartOfSeqNO.length() == 0) {
				
				objCallableStatement.setNull(6, Types.NULL);
			} else {
				
				objCallableStatement.setArray(6, arrPartOfSeqNO);
			}
			
			ARRAY arrPartOfSeqCode = new ARRAY(arrdesc, dconn,
					objClauseVO.getPartOfCode());
			
			if (arrPartOfSeqCode.length() == 0) {
				objCallableStatement.setNull(7, Types.NULL);
			} else {
				objCallableStatement.setArray(7, arrPartOfSeqCode);
			}
			LogUtil
			.logMessage("DWONumber in DAO:"
					+ objClauseVO.getDwONumber());
			if (objClauseVO.getDwONumber() == 0) {
				objCallableStatement.setNull(8, Types.NULL);
			} else {
				objCallableStatement.setInt(8, objClauseVO.getDwONumber());
			}
			LogUtil.logMessage("PartNumber in DAO:"
					+ objClauseVO.getPartNumber());
			if (objClauseVO.getPartNumber() == 0) {
				objCallableStatement.setNull(9, Types.NULL);
			} else {
				objCallableStatement.setInt(9, objClauseVO.getPartNumber());
			}
			LogUtil.logMessage("PriceBookNumber in DAO:"
					+ objClauseVO.getPriceBookNumber());
			if (objClauseVO.getPriceBookNumber() == 0) {
				objCallableStatement.setNull(10, Types.NULL);
			} else {
				objCallableStatement.setInt(10, objClauseVO
						.getPriceBookNumber());
			}
			if (objClauseVO.getObjStandardEquipVO() == null) {
				objCallableStatement.setNull(11, Types.NULL);
			} else {
				arlStandardEquipmentList = objClauseVO.getObjStandardEquipVO();
				StandardEquipVO objStandardEquipVO = (StandardEquipVO) arlStandardEquipmentList
				.get(0);
				objCallableStatement.setInt(11, objStandardEquipVO
						.getStandardEquipmentSeqNo());
			}
			if (objClauseVO.getComments() == null) {
				objCallableStatement.setNull(12, Types.NULL);
			} else {
				objCallableStatement.setString(12, objClauseVO.getComments());
			}
			
			arlTableList = objClauseVO.getTableDataVO();
			
			objTableDataVO = (ClauseTableDataVO) arlTableList.get(0);
			
			tableDataArr1 = new ARRAY(arrdesc, dconn, objTableDataVO
					.getTableArrayData1());
			
			if (objTableDataVO.getTableArrayData1() == null) {
				tableDataArr1 = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(13, tableDataArr1);
			} else {
				objCallableStatement.setArray(13, tableDataArr1);
			}
			
			objTableDataVO = (ClauseTableDataVO) arlTableList.get(1);
			tableDataArr2 = new ARRAY(arrdesc, dconn, objTableDataVO
					.getTableArrayData2());
			
			if (objTableDataVO.getTableArrayData2() == null) {
				tableDataArr2 = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(14, tableDataArr2);
			} else {
				objCallableStatement.setArray(14, tableDataArr2);
			}
			
			objTableDataVO = (ClauseTableDataVO) arlTableList.get(2);
			tableDataArr3 = new ARRAY(arrdesc, dconn, objTableDataVO
					.getTableArrayData3());
			
			if (objTableDataVO.getTableArrayData3() == null) {
				tableDataArr3 = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(15, tableDataArr3);
			} else {
				objCallableStatement.setArray(15, tableDataArr3);
			}
			
			objTableDataVO = (ClauseTableDataVO) arlTableList.get(3);
			tableDataArr4 = new ARRAY(arrdesc, dconn, objTableDataVO
					.getTableArrayData4());
			
			if (objTableDataVO.getTableArrayData4() == null) {
				tableDataArr4 = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(16, tableDataArr4);
			} else {
				objCallableStatement.setArray(16, tableDataArr4);
			}
			
			objTableDataVO = (ClauseTableDataVO) arlTableList.get(4);
			tableDataArr5 = new ARRAY(arrdesc, dconn, objTableDataVO
					.getTableArrayData5());
			if (objTableDataVO.getTableArrayData5() == null) {
				tableDataArr5 = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(17, tableDataArr5);
			} else {
				objCallableStatement.setArray(17, tableDataArr5);
			}
			LogUtil.logMessage("Apply Default Flag in ModelClauseDAO:"
					+ objClauseVO.getDefaultFlag());
			if ("Y".equals(objClauseVO.getDefaultFlag())) {
				objCallableStatement
				.setString(18, objClauseVO.getDefaultFlag());
				LogUtil.logMessage("Apply Default Flag in ModelClauseDAO:"
						+ objClauseVO.getDefaultFlag());
			} else {
				objCallableStatement.setNull(18, Types.NULL);
			}
			
			objCallableStatement.setString(19, objClauseVO.getUserID());
			
			objCallableStatement.registerOutParameter(20, Types.INTEGER);
			objCallableStatement.registerOutParameter(21, Types.VARCHAR);
			objCallableStatement.registerOutParameter(22, Types.VARCHAR);
			
			objConnnection.setAutoCommit(false);
			
			intStatus = objCallableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
				
			}
			
			intLSDBErrorID = (int) objCallableStatement.getInt(20);
			strOracleCode = (String) objCallableStatement.getString(21);
			strErrorMessage = (String) objCallableStatement.getString(22);
			
			if (intLSDBErrorID != 0) {
				
				LogUtil.logMessage("Enters into Error Loop:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessageID("" + intLSDBErrorID);
				LogUtil.logMessage("Error message in ErrorInfo:"
						+ objErrorInfo.getMessageID());
				throw new DataAccessException(objErrorInfo);
				
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {//Un
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
		}
		
		catch (DataAccessException objDatExp) {
			ErrorInfo objErrorInfo = objDatExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into DataAccess Exception block in ModelClauseDAO:updateClause"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDatExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ModelClauseDAO:updateClause:"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelClauseDAO:updateClause:"
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
			}
			
			catch (Exception objExp) {
				LogUtil
				.logMessage("Enter into Exception block in ModelClauseDAO:updateClause:"
						+ objExp.getMessage());
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
	 * @param objClauseVO
	 *            This Method is used to fetch clause details for the selected
	 *            Lead Component
	 * @return ArrayList The Arraylist containing Clause versions
	 * @throws EMDException
	 **************************************************************************/
	public static ArrayList fetchClauseForLeadComp(ClauseVO objClauseVO)
	throws EMDException {
		LogUtil
		.logMessage("Entering ModelSelectClauseRevDAO.fetchClauseForLeadComp");
		
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		int countCol=0;
		
		ResultSet objClauseResultSet = null;
		ResultSet objEDLNoResultSet = null;
		ResultSet objRefEDLNoResultSet = null;
		ResultSet objSubSecResultSet = null;
		ResultSet objTbDataResultSet = null;
		ResultSet objStdEqpResultSet = null;
		ResultSet objClauseComp = null;
		
		ArrayList arlClauseList = new ArrayList();
		
		ArrayList arlTableColumns = null;
		ArrayList arlAllVersion = new ArrayList();
		ArrayList arlDefaultVersion = new ArrayList();
		
		String strLogUser = "";
		try {
			strLogUser = objClauseVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_SELECT_MDL_CLA_LEADCOMP);
			LogUtil
			.logMessage("ModelSeqNo ModelSelectClauseRevDAO:fetchClauseForLeadComp:"
					+ objClauseVO.getModelSeqNo());
			objCallableStatement.setInt(1, objClauseVO.getModelSeqNo());
			LogUtil
			.logMessage("SubSectionSeqNo ModelSelectClauseRevDAO:fetchClauseForLeadComp:"
					+ objClauseVO.getSubSectionSeqNo());
			objCallableStatement.setInt(2, objClauseVO.getSubSectionSeqNo());
			LogUtil
			.logMessage("LeadCompGrpSeqNo ModelSelectClauseRevDAO:fetchClauseForLeadComp:"
					+ objClauseVO.getLeadCompGrpSeqNo());
			LogUtil
			.logMessage("LeadCompSeqNo ModelSelectClauseRevDAO:fetchClauseForLeadComp:"
					+ objClauseVO.getComponentSeqNo());
			LogUtil
			.logMessage("Flag ModelSelectClauseRevDAO:fetchClauseForLeadComp:"
					+ objClauseVO.getDefaultFlag());
			
			if (objClauseVO.getLeadCompGrpSeqNo() == 0) {
				objCallableStatement.setNull(3, Types.NULL);
				
			} else {
				objCallableStatement.setInt(3, objClauseVO
						.getLeadCompGrpSeqNo());
			}
			if ((Integer.parseInt(objClauseVO.getComponentSeqNo())) == 0) {
				objCallableStatement.setNull(4, Types.NULL);
			} else {
				objCallableStatement.setInt(4, Integer.parseInt(objClauseVO
						.getComponentSeqNo()));
			}
			if (objClauseVO.getDefaultFlag() == null) {
				objCallableStatement.setNull(5, Types.NULL);
			} else {
				objCallableStatement.setString(5, objClauseVO.getDefaultFlag());
			}
			//Added For CR_80 to bring Core Clauses for NOT REQUIRED option by RR68151
			if (objClauseVO.getCoreClausesFlag() == null) {
				objCallableStatement.setNull(6, Types.NULL);
			} else {
				objCallableStatement.setString(6, objClauseVO.getCoreClausesFlag());
			}
			
			objCallableStatement.registerOutParameter(7, OracleTypes.CURSOR);
			objCallableStatement.setString(8, objClauseVO.getUserID());
			objCallableStatement.registerOutParameter(9, Types.INTEGER);
			objCallableStatement.registerOutParameter(10, Types.VARCHAR);
			objCallableStatement.registerOutParameter(11, Types.VARCHAR);
			objCallableStatement.execute();
			
			objClauseResultSet = (ResultSet) objCallableStatement.getObject(7);
			
			intLSDBErrorID = objCallableStatement.getInt(9);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(10);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(11);
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
			
			while (objClauseResultSet.next()) {
				ArrayList arledlNo = new ArrayList();
				ArrayList arlrefedlNo = new ArrayList();
				ArrayList subsecCode = new ArrayList();
				ArrayList arlPartOfClaNo = new ArrayList();
				ArrayList arlClaComp = new ArrayList();
				int cntEDL = 0;
				int cntRefEDl = 0;
				int cntPartOf = 0;
				int cntCompSeqno = 0;
				int[] arlPartSubSecSeqNo = new int[4];
				int[] crPartclaSeqNo = new int[4];
				String[] arlPartclaSeqNo = { "", "", "", "" };
				String[] arlEDLNos = { "", "", "" };
				String[] arlRefEDLNos = { "", "", "" };
				String[] arlpartSubSecCode = { "", "", "", "" };
				String[] strCompSeqNo = new String[25];
				objClauseVO = new ClauseVO();
				
				objClauseVO.setClauseSeqNo(objClauseResultSet
						.getInt(DatabaseConstants.LS300_CLA_SEQ_NO));
				LogUtil.logMessage("ClauseSeqNo:"
						+ objClauseVO.getClauseSeqNo());
				
				objClauseVO.setVersionNo(objClauseResultSet
						.getInt(DatabaseConstants.LS301_VERSION_NO));
				objClauseVO.setClauseNum(objClauseResultSet
						.getString(DatabaseConstants.LS308_CLA_NO));
				
				objClauseVO.setClauseDesc(objClauseResultSet
						.getString(DatabaseConstants.LS301_CLA_DESC));
				
				objClauseVO.setClauseDescForTextArea(objClauseResultSet
						.getString(DatabaseConstants.LS301_CLA_DESC));
				objClauseVO.setCustomerName(objClauseResultSet
						.getString(DatabaseConstants.LS050_CUST_NAME));
				
				objClauseVO.setModifiedBy(objClauseResultSet
						.getString(DatabaseConstants.LS301_UPDT_USER_ID));
				objClauseVO.setModifiedDate(objClauseResultSet
						.getString(DatabaseConstants.LS301_UPDT_DATE));
				objClauseVO.setDefaultFlag(objClauseResultSet
						.getString(DatabaseConstants.LS301_DEFAULT_FLAG));
				
				objEDLNoResultSet = (ResultSet) objClauseResultSet
				.getObject(DatabaseConstants.EDLNO);
				
				while (objEDLNoResultSet.next()) {
					
					arlEDLNos[cntEDL] = objEDLNoResultSet
					.getString(DatabaseConstants.LS302_EDL_NO);/*
					* This
					* String
					* array
					* is
					* used
					* to
					* display
					* the
					* EDL
					* number
					* in Add
					* Clause
					* Revision
					* Engineering
					* data
					* EDL
					* number
					* textbox
					*/
					arledlNo.add(objEDLNoResultSet
							.getString(DatabaseConstants.LS302_EDL_NO));/*
							* this
							* arraylist
							* is
							* used
							* to
							* display
							* the
							* EDL
							* number
							* in
							* Select
							* clause
							* revision
							* table
							* Engineering
							* data
							* column
							*/
					
					cntEDL++;
					
				}
				
				objClauseVO.setEdlNo(arlEDLNos);
				objClauseVO.setEdlNO(arledlNo);
				
				DBHelper.closeSQLObjects(objEDLNoResultSet, null, null);
				
				objRefEDLNoResultSet = (ResultSet) objClauseResultSet
				.getObject(DatabaseConstants.refEDLNO);
				while (objRefEDLNoResultSet.next()) {
					
					arlRefEDLNos[cntRefEDl] = objRefEDLNoResultSet
					.getString(DatabaseConstants.LS303_REF_EDL_NO);
					arlrefedlNo.add(objRefEDLNoResultSet
							.getString(DatabaseConstants.LS303_REF_EDL_NO));
					
					cntRefEDl++;
					
				}
				
				objClauseVO.setRefEDLNo(arlRefEDLNos);
				objClauseVO.setRefEDLNO(arlrefedlNo);
				
				DBHelper.closeSQLObjects(objRefEDLNoResultSet, null, null);
				
				objSubSecResultSet = (ResultSet) objClauseResultSet
				.getObject(DatabaseConstants.PartOF);
				
				while (objSubSecResultSet.next()) {
					
					arlpartSubSecCode[cntPartOf] = objSubSecResultSet
					.getString(DatabaseConstants.LS304_SUBSEC_NO);
					arlPartSubSecSeqNo[cntPartOf] = objSubSecResultSet
					.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO);
					arlPartclaSeqNo[cntPartOf] = objSubSecResultSet
					.getString(DatabaseConstants.LS304_PART_OF_CLA_SEQ_NO);
					crPartclaSeqNo[cntPartOf] = objSubSecResultSet
					.getInt(DatabaseConstants.LS304_PART_OF_CLA_SEQ_NO);
					subsecCode.add(objSubSecResultSet
							.getString(DatabaseConstants.LS304_SUBSEC_NO));
					
					cntPartOf++;
					
				}
				
				/***************************************************************
				 * one more parameter partOfclaSeqNo is selecting from part of
				 * cursor based on LSDB_CR-48 * Added on 04-Aug-08 by ps57222
				 *  
				 **************************************************************/
				
				objClauseVO.setPartOfCode(arlpartSubSecCode);
				objClauseVO.setPartOF(subsecCode);
				
				objClauseVO.setPartOfSeqNo(arlPartSubSecSeqNo);
				
				objClauseVO.setPartOfClaSeqNo(arlPartclaSeqNo);
				objClauseVO.setReqpartOfClaSeqNo(crPartclaSeqNo);
				
				objClauseVO.setPartOfClaNo(arlPartOfClaNo);
				
				DBHelper.closeSQLObjects(objSubSecResultSet, null, null);
				
				objTbDataResultSet = (ResultSet) objClauseResultSet
				.getObject(DatabaseConstants.CLA_TBL_DATA);
				
				ArrayList arlTableRows = new ArrayList();
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
				objClauseVO.setTableArrayData1(arlTableRows);
				
				DBHelper.closeSQLObjects(objTbDataResultSet, null, null);
				
				objClauseVO.setDwONumber(objClauseResultSet
						.getInt(DatabaseConstants.LS301_DWO_NUMBER));
				
				objClauseVO.setPartNumber(objClauseResultSet
						.getInt(DatabaseConstants.LS301_PART_NUMBER));
				
				objClauseVO.setPriceBookNumber(objClauseResultSet
						.getInt(DatabaseConstants.LS301_PRICE_BOOK_NUMBER));
				
				objStdEqpResultSet = (ResultSet) objClauseResultSet
				.getObject(DatabaseConstants.STD_EQUIP);
				while (objStdEqpResultSet.next()) {
					objClauseVO.setStandardEquipmentDesc(objStdEqpResultSet
							.getString(DatabaseConstants.LS060_STD_EQP_DESC));
					objClauseVO.setStandardEquipmentSeqNo(objStdEqpResultSet
							.getInt(DatabaseConstants.LS060_STD_EQP_SEQ_NO));
					
				}
				
				DBHelper.closeSQLObjects(objStdEqpResultSet, null, null);
				
				objClauseVO.setComments(objClauseResultSet
						.getString(DatabaseConstants.LS301_ENGG_DATA_COMMENTS));
				
				objClauseVO.setReason(objClauseResultSet
						.getString(DatabaseConstants.LS301_CLA_REASON));
				
				objClauseComp = (ResultSet) objClauseResultSet
				.getObject(DatabaseConstants.CLA_COMP);
				while (objClauseComp.next()) {
					
					arlClaComp.add(objClauseComp
							.getString(DatabaseConstants.LS140_COMP_NAME));
					strCompSeqNo[cntCompSeqno] = objClauseComp
					.getString(DatabaseConstants.LS140_COMP_SEQ_NO);
					cntCompSeqno++;
					
				}
				
				objClauseVO.setCompName(arlClaComp);
				
				objClauseVO.setComponentSeqNo(ApplicationUtil
						.convertStringArrayToString(strCompSeqNo));
				DBHelper.closeSQLObjects(objClauseComp, null, null);
				
				arlAllVersion.add(objClauseVO);
				
				/*
				 * if ("Y".equals(objClauseResultSet
				 * .getString(DatabaseConstants.LS301_DEFAULT_FLAG))) {
				 * arlDefaultVersion.add(objClauseVO); }
				 */
				
			}
			LogUtil.logMessage("ArrayList Size For AllVersions:"
					+ arlAllVersion.size());
			LogUtil.logMessage("ArrayList Size For DefaultVersion:"
					+ arlDefaultVersion.size());
			
			arlClauseList.add(arlAllVersion);
			//arlClauseList.add(arlDefaultVersion);
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into DataAccessException block in ModelSelectClauseRevDAO:fetchClauseForLeadComp"
					+ objDataExp.getMessage());
			LogUtil
			.logMessage("ENters into DataAccessException block in ModelSelectClauseRevDAO:fetchClauseForLeadComp"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into ApplicationException block in ModelSelectClauseRevDAO:fetchClauseForLeadComp"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
			
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelSelectClauseRevDAO:fetchClauseForLeadComp:");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(objClauseResultSet,
						objCallableStatement, objConnection);
				
			}
			
			catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in ModelSelectClauseRevDAO:fetchClauseForLeadComp:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
		}
		LogUtil
		.logMessage("arlParentClauseList size..."
				+ arlClauseList.size());
		return arlClauseList;
	}
	
}