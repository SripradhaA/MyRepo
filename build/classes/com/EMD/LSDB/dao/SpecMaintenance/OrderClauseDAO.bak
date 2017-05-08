/**
 * 
 */
package com.EMD.LSDB.dao.SpecMaintenance;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
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
import com.EMD.LSDB.vo.common.ClauseTableDataVO;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.CompGroupVO;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.StandardEquipVO;
import com.EMD.LSDB.vo.common.SubSectionVO;

/**
 * @author ps57222
 * 
 */
/***********************************************************************
----------------------------------------------------------------------------------------------------------
*    Date     Version   Modified by    	 Comments                              		Remarks 
* 19/01/2010    1.0      RR68151       Added Additional parameter SelectCGCFlag     Added for CR_81
* 										to disable addClauseRev Screen	  
* 													 	 
* 
* --------------------------------------------------------------------------------------------------------
**************************************************************************/

public class OrderClauseDAO extends EMDDAO {
	
	public static ArrayList fetchClauses(ClauseVO objClauseVO)
	throws EMDException {
		LogUtil.logMessage("Enters into OrderClauseDAO:fetchClauses");
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
		ResultSet objSecResultSet = null;
		ArrayList arlClauseList = new ArrayList();
		
		String strLogUser = "";
		
		try {
			strLogUser = objClauseVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_SELECT_ORDER_CLAUSE);
			LogUtil.logMessage("ClauseSeqNo in OrderClauseDAO:fetchClauses"
					+ objClauseVO.getClauseSeqNo());
			objCallableStatement.setInt(1, objClauseVO.getClauseSeqNo());
			LogUtil.logMessage("OrderKey in OrderClauseDAO:fetchClauses"
					+ objClauseVO.getOrderKey());
			objCallableStatement.setInt(2, objClauseVO.getOrderKey());
			LogUtil.logMessage("UserID in OrderClauseDAO:fetchClauses"
					+ objClauseVO.getUserID());
			objCallableStatement.setString(3, objClauseVO.getUserID());
			objCallableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.execute();
			LogUtil.logMessage("Enters");
			objClauseResultSet = (ResultSet) objCallableStatement.getObject(4);
			LogUtil.logMessage("Leaves");
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
			
			while (objClauseResultSet.next()) {
				
				int cntEDL = 0;
				int cntRefEDl = 0;
				int cntPartOf = 0;
				int[] arlPartSubSecSeqNo = new int[4];
				String[] arlEDLNos = { "", "", "" };
				String[] arlRefEDLNos = { "", "", "" };
				String[] arlpartSubSecCode = { "", "", "", "" };
				int[] arlClauseSeqNo = new int[4];
				ArrayList arlTableRows = new ArrayList();
				ArrayList arlTableColumns = null;
				
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
				objClauseVO.setComments(objClauseResultSet
						.getString(DatabaseConstants.LS301_ENGG_DATA_COMMENTS));
				LogUtil.logMessage("Comments:" + objClauseVO.getComments());
				objClauseVO.setReason(objClauseResultSet
						.getString(DatabaseConstants.LS301_CLA_REASON));
				LogUtil.logMessage("Reason:" + objClauseVO.getReason());
				objClauseVO.setOrderNo(objClauseResultSet
						.getString(DatabaseConstants.LS400_ORDR_NO));
				objClauseVO.setSpecStatusCode(objClauseResultSet
						.getInt(DatabaseConstants.LS080_SPEC_STATUS_CODE));
				LogUtil.logMessage("OrderNo***" + objClauseVO.getOrderNo());
				objClauseVO.setModelSeqNo(objClauseResultSet
						.getInt(DatabaseConstants.LS200_MDL_SEQ_NO));
				LogUtil.logMessage("OrderNo***" + objClauseVO.getModelSeqNo());
				
				//Added for CR_100 for disabling the price book no for locomotives
				objClauseVO.setSpecTypeNo(objClauseResultSet.
						getInt(DatabaseConstants.SPEC_TYPE_SEQ_NO));
				//Added For CR_81 to bring Characteristic Flag to AddClause Screen by RR68151 - Starts here
				objClauseVO.setSelectCGCFlag(objClauseResultSet.getString(DatabaseConstants.
						LS300_CHAR_GRP_FLAG));
				LogUtil.logMessage("Char Group Flag***" + objClauseVO.getSelectCGCFlag());
				//Added For CR_81 to bring Characteristic Flag to AddClause Screen by RR68151 - Ends here
				
				//Added for CR_109
				objClauseVO.setAppendixExitsFlag(objClauseResultSet
						.getString(DatabaseConstants.APPENDIX_EXISTS_FLAG));
				//Added for CR_109 Ends here
				
				//Added for CR_114
				objClauseVO.setAppendixImgSeqNo(objClauseResultSet
						.getInt(DatabaseConstants.LS170_IMG_SEQ_NO));
				//Added for CR_114 Ends Here
				
				objEDLNoResultSet = (ResultSet) objClauseResultSet
				.getObject("EDL_NO");
				
				while (objEDLNoResultSet.next()) {
					LogUtil.logMessage("Enters into EDLNo Resultset Loop:");
					arlEDLNos[cntEDL] = objEDLNoResultSet
					.getString(DatabaseConstants.LS302_EDL_NO);
					cntEDL++;
				}
				
				objClauseVO.setEdlNo(arlEDLNos);
				DBHelper.closeSQLObjects(objEDLNoResultSet, null, null);
				LogUtil.logMessage("Length of EdlNumber:" + arlEDLNos.length);
				
				objRefEDLNoResultSet = (ResultSet) objClauseResultSet
				.getObject("REF_EDL_NO");
				
				while (objRefEDLNoResultSet.next()) {
					LogUtil.logMessage("Enters into RefEDLNo Resultset Loop:");
					arlRefEDLNos[cntRefEDl] = objRefEDLNoResultSet
					.getString(DatabaseConstants.LS303_REF_EDL_NO);
					cntRefEDl++;
				}
				
				LogUtil.logMessage("Length of RefEdlNumber:"
						+ arlRefEDLNos.length);
				
				objClauseVO.setRefEDLNo(arlRefEDLNos);
				
				DBHelper.closeSQLObjects(objRefEDLNoResultSet, null, null);
				
				objSubSecResultSet = (ResultSet) objClauseResultSet
				.getObject("PART_OF");
				
				while (objSubSecResultSet.next()) {
					LogUtil
					.logMessage("PartCode Of values"
							+ objSubSecResultSet
							.getString(DatabaseConstants.LS407_PART_OF_CLA_NO));
					arlpartSubSecCode[cntPartOf] = objSubSecResultSet
					.getString(DatabaseConstants.LS407_PART_OF_CLA_NO);
					arlClauseSeqNo[cntPartOf] = objSubSecResultSet
					.getInt(DatabaseConstants.LS300_PART_OF_CLA_SEQ_NO);
					arlPartSubSecSeqNo[cntPartOf] = objSubSecResultSet
					.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO);
					cntPartOf++;
				}
				
				objClauseVO.setPartOfCode(arlpartSubSecCode);
				LogUtil.logMessage("PartOfCode:" + objClauseVO.getPartOfCode());
				LogUtil.logMessage("Length of PartOfCode:"
						+ arlpartSubSecCode.length);
				objClauseVO.setPartOfSeqNo(arlPartSubSecSeqNo);
				objClauseVO.setClauseSeqNum(arlClauseSeqNo);
				LogUtil.logMessage("PartOfSeqNo:"
						+ objClauseVO.getPartOfSeqNo());
				
				DBHelper.closeSQLObjects(objSubSecResultSet, null, null);
				
				objTbDataResultSet = (ResultSet) objClauseResultSet
				.getObject("TABLE_DATA");
				
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
				.getObject("STD_EQP");
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
				
				objSecResultSet = (ResultSet) objClauseResultSet
				.getObject("SEC_DETAILS");
				while (objSecResultSet.next()) {
					LogUtil.logMessage("Enters into Section ResultSet");
					objClauseVO.setSectionName(objSecResultSet
							.getString(DatabaseConstants.LS201_SEC_NAME));
					objClauseVO.setSectionSeqNo(objSecResultSet
							.getInt(DatabaseConstants.LS201_SEC_SEQ_NO));
					objClauseVO.setSubSectionName(objSecResultSet
							.getString(DatabaseConstants.LS202_SUBSEC_NAME));
					objClauseVO.setSubSectionSeqNo(objSecResultSet
							.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO));
					
				}
				DBHelper.closeSQLObjects(objSecResultSet, null, null);
				
				if ("Y".equals(objClauseResultSet
						.getString(DatabaseConstants.FLAG))) {
					arlClauseList.add(objClauseVO);
				}
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
					+ objErrorInfo);
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
				
				DBHelper.closeSQLObjects(objClauseResultSet,
						objCallableStatement, objConnection);
				
			}
			
			catch (Exception objExp) {
				
				LogUtil.logMessage("Enters into Exception exception...");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return arlClauseList;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objClauseVO
	 *            the object for Inserting clause
	 * @return boolean that returns True or False
	 * @throws EMDException
	 **************************************************************************/
	
	public static synchronized int insertClause(ClauseVO objClauseVO)
	throws EMDException {
		LogUtil.logMessage("Inside the OrderClauseDAO:insertClause");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		ArrayDescriptor arrdesc = null;
		ArrayList arlStandardEquipmentList = new ArrayList();
		ARRAY arr = null;
		ClauseTableDataVO objTableDataVO = null;
		ArrayList arlTableList;
		ARRAY tableDataArr1, tableDataArr2, tableDataArr3, tableDataArr4, tableDataArr5 = null;
		//Added for CR_97
		int intClauseSeqNo = 0;
		int intCompSeqNo = 0;
		
		String strLogUser = "";
		try {
			strLogUser = objClauseVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_INSERT_ORDER_CLAUSE);
			Connection dconn = ((DelegatingConnection) objConnection).getInnermostDelegate(); //Added for CR-123 & Tomcat
			objCallableStatement.setInt(1, objClauseVO.getOrderKey());
			objCallableStatement
			.setString(2, objClauseVO.getDataLocationType());
			
			objCallableStatement.setInt(3, objClauseVO.getModelSeqNo());
			objCallableStatement.setInt(4, objClauseVO.getSubSectionSeqNo());
			
			if (objClauseVO.getCustomerSeqNo() <= 0) {
				objCallableStatement.setNull(5, Types.NULL);
			} else {
				objCallableStatement.setInt(5, objClauseVO.getCustomerSeqNo());
			}
			
			LogUtil.logMessage("ClauseSEqNo:******"
					+ objClauseVO.getClauseSeqNo());
			objCallableStatement.setInt(6, objClauseVO.getClauseSeqNo());
			
			if (objClauseVO.getParentClauseSeqNo() <= 0) {
				objCallableStatement.setNull(7, Types.NULL);
			} else {
				objCallableStatement.setInt(7, objClauseVO
						.getParentClauseSeqNo());
			}
			objCallableStatement.setString(8, objClauseVO.getClauseDesc());
			
			arrdesc = new ArrayDescriptor(DatabaseConstants.STR_ARRAY,
					dconn);
			arr = new ARRAY(arrdesc, dconn,
					processComponentVO(objClauseVO));
			objCallableStatement.setArray(9, arr);
			
			ARRAY arrEdlno = new ARRAY(arrdesc, dconn, objClauseVO
					.getEdlNo());
			if (objClauseVO.getEdlNo() == null) {
				arrEdlno = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(10, arrEdlno);
			} else {
				objCallableStatement.setArray(10, arrEdlno);
			}
			
			ARRAY arrRefEDLNO = new ARRAY(arrdesc, dconn, objClauseVO
					.getRefEDLNo());
			
			if (objClauseVO.getRefEDLNo() == null) {
				arrRefEDLNO = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(11, arrRefEDLNO);
			} else {
				objCallableStatement.setArray(11, arrRefEDLNO);
			}
			
			ARRAY arrPartOfSeqNO = new ARRAY(arrdesc, dconn,
					objClauseVO.getPartOfSeqNo());
			if (objClauseVO.getPartOfSeqNo() == null) {
				arrPartOfSeqNO = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(12, arrPartOfSeqNO);
				LogUtil.logMessage("inside if loop null:");
			} else {
				LogUtil.logMessage("inside else loop :");
				objCallableStatement.setArray(12, arrPartOfSeqNO);
			}
			ARRAY arrClauseSeqNo = new ARRAY(arrdesc, dconn,
					objClauseVO.getClauseSeqNoArray());
			
			if (objClauseVO.getClauseSeqNoArray() == null) {
				arrClauseSeqNo = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(13, arrClauseSeqNo);
			} else {
				objCallableStatement.setArray(13, arrClauseSeqNo);
			}
			ARRAY arrClauseNo = new ARRAY(arrdesc, dconn, objClauseVO
					.getClauseNoArray());
			if (objClauseVO.getClauseNoArray() == null) {
				arrClauseNo = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(14, arrClauseNo);
			} else {
				objCallableStatement.setArray(14, arrClauseNo);
			}
			
			if (objClauseVO.getDwONumber() == 0) {
				objCallableStatement.setNull(15, Types.NULL);
			} else {
				objCallableStatement.setInt(15, objClauseVO.getDwONumber());
			}
			if (objClauseVO.getPartNumber() == 0) {
				objCallableStatement.setNull(16, Types.NULL);
			} else {
				objCallableStatement.setInt(16, objClauseVO.getPartNumber());
			}
			if (objClauseVO.getPriceBookNumber() == 0) {
				objCallableStatement.setNull(17, Types.NULL);
			} else {
				objCallableStatement.setInt(17, objClauseVO
						.getPriceBookNumber());
			}
			
			if (objClauseVO.getObjStandardEquipVO() == null) {
				objCallableStatement.setNull(18, Types.NULL);
			} else {
				arlStandardEquipmentList = objClauseVO.getObjStandardEquipVO();
				StandardEquipVO objStandardEquipVO = (StandardEquipVO) arlStandardEquipmentList
				.get(0);
				objCallableStatement.setInt(18, objStandardEquipVO
						.getStandardEquipmentSeqNo());
			}
			if (objClauseVO.getComments() == null) {
				objCallableStatement.setNull(19, Types.NULL);
			} else {
				objCallableStatement.setString(19, objClauseVO.getComments());
			}
			
			objCallableStatement.setString(20, objClauseVO.getReason());
			
			arlTableList = objClauseVO.getTableDataVO();
			
			objTableDataVO = (ClauseTableDataVO) arlTableList.get(0);
			
			tableDataArr1 = new ARRAY(arrdesc, dconn, objTableDataVO
					.getTableArrayData1());
			
			if (objTableDataVO.getTableArrayData1() == null) {
				tableDataArr1 = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(21, tableDataArr1);
			} else {
				objCallableStatement.setArray(21, tableDataArr1);
			}
			
			objTableDataVO = (ClauseTableDataVO) arlTableList.get(1);
			tableDataArr2 = new ARRAY(arrdesc, dconn, objTableDataVO
					.getTableArrayData2());
			
			if (objTableDataVO.getTableArrayData2() == null) {
				tableDataArr2 = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(22, tableDataArr2);
			} else {
				objCallableStatement.setArray(22, tableDataArr2);
			}
			
			objTableDataVO = (ClauseTableDataVO) arlTableList.get(2);
			tableDataArr3 = new ARRAY(arrdesc, dconn, objTableDataVO
					.getTableArrayData3());
			
			if (objTableDataVO.getTableArrayData3() == null) {
				tableDataArr3 = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(23, tableDataArr3);
			} else {
				objCallableStatement.setArray(23, tableDataArr3);
			}
			
			objTableDataVO = (ClauseTableDataVO) arlTableList.get(3);
			tableDataArr4 = new ARRAY(arrdesc, dconn, objTableDataVO
					.getTableArrayData4());
			
			if (objTableDataVO.getTableArrayData4() == null) {
				tableDataArr4 = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(24, tableDataArr4);
			} else {
				objCallableStatement.setArray(24, tableDataArr4);
			}
			
			objTableDataVO = (ClauseTableDataVO) arlTableList.get(4);
			tableDataArr5 = new ARRAY(arrdesc, dconn, objTableDataVO
					.getTableArrayData5());
			if (objTableDataVO.getTableArrayData5() == null) {
				tableDataArr5 = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(25, tableDataArr5);
			} else {
				objCallableStatement.setArray(25, tableDataArr5);
			}
			
			/*
			 * Starts here Added for CR LSDB_CR_28
			 */
			
			LogUtil.logMessage("objClauseVO.getClauseSource() :"
					+ objClauseVO.getClauseSource());
			if (objClauseVO.getClauseSource() == null) {
				
				objCallableStatement.setNull(26, Types.NULL);
			} else {
				
				objCallableStatement.setString(26, objClauseVO
						.getClauseSource());
			}
			
			// Ends here
			
			objCallableStatement.setString(27, objClauseVO.getUserID());
			
			/* Added for Attach Clause CR-Begin */
			if (objClauseVO.getLeadCompGrpSeqNo() == 0) {
				objCallableStatement.setNull(28, Types.NULL);
			} else {
				objCallableStatement.setInt(28, objClauseVO
						.getLeadCompGrpSeqNo());
			}
			/* Added for Attach Clause CR-End */
			
			LogUtil.logMessage("Lead Component Group Seq No"
					+ objClauseVO.getLeadCompGrpSeqNo());
			
			/*Added for CR-68 Order New Component - Starts Here*/
			if (objClauseVO.getOrderCompName() != null
					&& !("".equals(objClauseVO.getOrderCompName()))) {
				objCallableStatement.setString(29, objClauseVO
						.getOrderCompName());
				
			} else {
				
				objCallableStatement.setNull(29, Types.NULL);
			}
			/*Added for CR-68 Order New Component - Ends Here*/
			
			LogUtil.logMessage("Lead Component Name"
					+ objClauseVO.getOrderCompName());
			//Added for CR_97
			objCallableStatement.registerOutParameter(30, Types.INTEGER);
			objCallableStatement.registerOutParameter(31, Types.INTEGER);
			//CR_97 ends here
			objCallableStatement.registerOutParameter(32, Types.INTEGER);
			objCallableStatement.registerOutParameter(33, Types.VARCHAR);
			objCallableStatement.registerOutParameter(34, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			LogUtil.logMessage("Status:" + intStatus);
			if (intStatus > 0) {
				intStatus = 0;
				
			}
			
			//Added for CR_97
			intCompSeqNo = (int) objCallableStatement.getInt(30);
			intClauseSeqNo = (int) objCallableStatement.getInt(31);
			if(intCompSeqNo!=0 && intClauseSeqNo!=0 && "Y".equals(objClauseVO.getNewCRFlag()))
			{
				
				objClauseVO.setClauseNo(intClauseSeqNo);
				objClauseVO.setCompSeqNum(intCompSeqNo);
				intStatus = createNewCRForm(objConnection, objClauseVO,"Y");
			}
			//CR_97 Ends here
			intLSDBErrorID = (int) objCallableStatement.getInt(32);
			strOracleCode = (String) objCallableStatement.getString(33);
			strErrorMessage = (String) objCallableStatement.getString(34);
				
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
		}
		
		catch (DataAccessException objDatExp) {
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
		
		return intStatus;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objClauseVO
	 *            the object for processing Components
	 * @return integer Array that returns component Sequence No's
	 * @throws EMDException
	 **************************************************************************/
	private static int[] processComponentVO(ClauseVO objClauseVO)
	throws EMDException {
		LogUtil
		.logMessage("Inside the Inside the OrderClauseDAO:processComponentVO ");
		
		int intComponentSeqNo[];
		
		ArrayList componentSeqArray = objClauseVO.getComponentVO();
		intComponentSeqNo = new int[componentSeqArray.size()];
		if (componentSeqArray != null && componentSeqArray.size() > 0) {
			for (int counter = 0; counter < componentSeqArray.size(); counter++) {
				intComponentSeqNo[counter] = Integer.parseInt(componentSeqArray
						.get(counter).toString());
			}
		}
		
		return intComponentSeqNo;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objClauseVO
	 *            the object for deleting clause
	 * @return boolean that returns True or False
	 * @throws EMDException
	 **************************************************************************/
	
	public static int deleteClause(ClauseVO objClauseVO) throws EMDException {
		LogUtil.logMessage("Inside the OrderClauseDAO:deleteClause");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		
		try {
			strLogUser = objClauseVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_DELETE_ORDER_CLAUSE);
			
			objCallableStatement.setInt(1, objClauseVO.getOrderKey());
			objCallableStatement.setInt(2, objClauseVO.getClauseSeqNo());
			objCallableStatement.setInt(3, objClauseVO.getSubSectionSeqNo());
			objCallableStatement
			.setString(4, objClauseVO.getDataLocationType());
			//Added for LSDB_CR-74 by KA57588 --Starts here
			objCallableStatement.setString(5, objClauseVO.getReason());
			//Ends here
			//CR_86 
			objCallableStatement.setString(6, objClauseVO.getRemoveClause());
			objCallableStatement.setString(7, objClauseVO.getUserID());
			objCallableStatement.registerOutParameter(8, Types.INTEGER);
			objCallableStatement.registerOutParameter(9, Types.VARCHAR);
			objCallableStatement.registerOutParameter(10, Types.VARCHAR);
			LogUtil.logMessage("Status:" + intStatus);
			intStatus = objCallableStatement.executeUpdate();
			
			LogUtil.logMessage("Status:" + intStatus);
			if (intStatus > 0) {
				intStatus = 0;
				
			}
			
			intLSDBErrorID = (int) objCallableStatement.getInt(8);
			strOracleCode = (String) objCallableStatement.getString(9);
			strErrorMessage = (String) objCallableStatement.getString(10);
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
		}
		
		catch (DataAccessException objDatExp) {
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
						objConnection);
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
	 * This method is for updating user level marker
	 * Added for LSDB_CR-74 by ka57588
	 * @version 1.0
	 * @param objClauseVO
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	
	public static int updateUserMarker(ClauseVO objClauseVO) throws EMDException {
		LogUtil.logMessage("Inside the OrderClauseDAO:updateUserMarker");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		
		try {
			strLogUser = objClauseVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_MARK_ORDR_CLAUSE);
			
			objCallableStatement.setInt(1, objClauseVO.getOrderKey());
			objCallableStatement.setInt(2, objClauseVO.getClauseSeqNo());
			objCallableStatement.setString(3, objClauseVO.getFlag());
			//Added for CR_109
			objCallableStatement.setString(4, objClauseVO.getMarkClaReason());
			//Added for CR_109 End here
			objCallableStatement.setString(5, objClauseVO.getUserID());
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			LogUtil.logMessage("Status:" + intStatus);
			intStatus = objCallableStatement.executeUpdate();
			
			LogUtil.logMessage("Status:" + intStatus);
			if (intStatus > 0) {
				intStatus = 0;
				
			}
			
			intLSDBErrorID = (int) objCallableStatement.getInt(6);
			strOracleCode = (String) objCallableStatement.getString(7);
			strErrorMessage = (String) objCallableStatement.getString(8);
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
		}
		
		catch (DataAccessException objDatExp) {
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
						objConnection);
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
	 * This method is for updating user level marker
	 * Added for LSDB_CR-74 by ka57588
	 * @version 1.0
	 * @param objClauseVO
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	
	public static int updateClauseReason(ClauseVO objClauseVO) throws EMDException {
		LogUtil.logMessage("Inside the OrderClauseDAO:updateClauseReason");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		
		try {
			strLogUser = objClauseVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_UPDATE_CLAUSE_REASON );
			
			objCallableStatement.setInt(1, objClauseVO.getOrderKey());
			objCallableStatement.setString(2, objClauseVO.getDataLocationType());
			objCallableStatement.setInt(3, objClauseVO.getClauseSeqNo());
			objCallableStatement.setString(4, objClauseVO.getReason());
			objCallableStatement.setString(5, objClauseVO.getUserID());
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			LogUtil.logMessage("Status:" + intStatus);
			intStatus = objCallableStatement.executeUpdate();
			
			LogUtil.logMessage("Status:" + intStatus);
			if (intStatus > 0) {
				intStatus = 0;
				
			}
			
			intLSDBErrorID = (int) objCallableStatement.getInt(6);
			strOracleCode = (String) objCallableStatement.getString(7);
			strErrorMessage = (String) objCallableStatement.getString(8);
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
		}
		
		catch (DataAccessException objDatExp) {
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
						objConnection);
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
	 * This method is for displaying Order Clause reason
	 * Added for LSDB_CR-74 by ka57588
	 * @version 1.0
	 * @param objClauseVO
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList displayClauseReason(ClauseVO objClauseVO) throws EMDException {
		LogUtil.logMessage("Inside the OrderClauseDAO:displayClauseReason");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ResultSet objClauseResultSet = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		ArrayList arlClause = new ArrayList();
		ClauseVO objClause = null;
		try {
			strLogUser = objClauseVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_SELECT_ORDR_CLA_VER);
			
			objCallableStatement.setInt(1, objClauseVO.getClauseSeqNo());
			objCallableStatement.setInt(2, objClauseVO.getVersionNo());
			objCallableStatement.setInt(3, objClauseVO.getOrderKey());
			objCallableStatement.setString(4, objClauseVO.getUserID());
			objCallableStatement.registerOutParameter(5, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			objCallableStatement.execute();
			
			objClauseResultSet = (ResultSet) objCallableStatement.getObject(5);
						
			intLSDBErrorID = (int) objCallableStatement.getInt(6);
			strOracleCode = (String) objCallableStatement.getString(7);
			strErrorMessage = (String) objCallableStatement.getString(8);
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
			
			while(objClauseResultSet.next()){
				
				objClause = new ClauseVO();
				objClause.setReason(objClauseResultSet.getString(DatabaseConstants.LS406_CLA_REASON));
				arlClause.add(objClause);
			}
		}
		
		catch (DataAccessException objDatExp) {
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
			LogUtil.logMessage("Enters into Exception block in DAO:"
					+ objExp.getMessage());
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				DBHelper.closeSQLObjects(objClauseResultSet, objCallableStatement,
						objConnection);
			} catch (Exception objExp) {
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
	 * @param objComponentVO
	 * Added for CR-68 Order New Component
	 * @return boolean
	 * @throws EMDException
	 **************************************************************************/
	
	public static boolean validateOrderComponent(ComponentVO objComponentVO)
	throws EMDException {
		LogUtil.logMessage("Entering OrderDAO.validateOrderComponent");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		boolean blnValid = false;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		
		try {
			strLogUser = objComponentVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_VALIDATE_ORDR_COMP_NAME);
			objCallableStatement.setString(1, objComponentVO.getOrderNo());
			objCallableStatement.setInt(2, objComponentVO.getModelSeqNo());
			objCallableStatement.setInt(3, objComponentVO.getSubSectionSeqNo());
			objCallableStatement.setInt(4, objComponentVO
					.getComponentGroupSeqNo());
			objCallableStatement
			.setString(5, objComponentVO.getComponentName());
			objCallableStatement.setString(6, objComponentVO.getUserID());
			objCallableStatement.registerOutParameter(7, Types.INTEGER);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			objCallableStatement.registerOutParameter(9, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			intLSDBErrorID = (int) objCallableStatement.getInt(7);
			strOracleCode = (String) objCallableStatement.getString(8);
			strErrorMessage = (String) objCallableStatement.getString(9);
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Error ID:" + intLSDBErrorID);
				LogUtil.logMessage("strOracleCode:" + strOracleCode);
				
			}
			
			if (intLSDBErrorID > 0) {
				LogUtil.logMessage("Component Name is Invalid");
				blnValid = false;
				
			} else {
				LogUtil.logMessage("Component Name is Valid");
				blnValid = true;
			}
			
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
		
		catch (DataAccessException objDatExp) {
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
						objConnection);
			}
			
			catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception block:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return blnValid;
		
	}
	/***************************************************************************
	 * This method is for updating user level marker
	 * Added for LSDB_CR-92 by RJ85495
	 * @version 1.0
	 * @param objClauseVO
	 * @return int							yet to be done
	 * @throws EMDException
	 **************************************************************************/
	
	public static int updateSysMarker(ClauseVO objClauseVO) throws EMDException {
		LogUtil.logMessage("Inside the OrderClauseDAO:updateSysMarker");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		
		try {
			strLogUser = objClauseVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_UPDATE_SYS_MARKER);
			
			objCallableStatement.setInt(1, objClauseVO.getOrderKey());
			objCallableStatement.setInt(2, objClauseVO.getClauseSeqNo());
			objCallableStatement.setString(3, objClauseVO.getSysMarkFlag());
			objCallableStatement.setString(4, objClauseVO.getUserID());
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			LogUtil.logMessage("Status:" + intStatus);
			intStatus = objCallableStatement.executeUpdate();
			
			LogUtil.logMessage("Status:" + intStatus);
			if (intStatus > 0)
			{
				intStatus = 0;
			}
			
			intLSDBErrorID = (int) objCallableStatement.getInt(5);
			strOracleCode = (String) objCallableStatement.getString(6);
			strErrorMessage = (String) objCallableStatement.getString(7);
			
			if (intLSDBErrorID != 0)
			{
				LogUtil.logMessage("Enters into Error Loop:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				strMessage = String.valueOf(intLSDBErrorID);
				LogUtil.logMessage("Error message in DAO:" + strMessage);
				
				objErrorInfo.setMessageID(strMessage);
				LogUtil.logMessage("Error message in ErrorInfo:"+ objErrorInfo.getMessageID());
				
				throw new DataAccessException(objErrorInfo);
			}
			else if (strOracleCode != null && !"0".equals(strOracleCode))
			{
				LogUtil.logMessage("enters into oracle error code block:"+ strOracleCode);
				ErrorInfo objErrorInfo = new ErrorInfo();
				StringBuffer objStBuffer = new StringBuffer();
				objStBuffer.append(strOracleCode + " ");
				objStBuffer.append(strErrorMessage);
				
				objErrorInfo.setMessage(objStBuffer.toString());
				objConnection.rollback();
				
				throw new ApplicationException(objErrorInfo);
			}
		}
		catch (DataAccessException objDatExp) {
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
						objConnection);
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
	 * This method is for updating user level marker
	 * Added for LSDB_CR-92 by RJ85495
	 * @version 1.0
	 * @param objClauseVO
	 * @return int							yet to be done
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList deletedClausesHistoy(ClauseVO objClauseVO) throws EMDException {
		LogUtil.logMessage("Inside the OrderClauseDAO:deletedClausesHistoy");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		
		int intStatus = 0;
		int intLSDBErrorID = 0;
		int countCol=0;

		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		String strLogUser = "";
		
		ArrayList arlClauseGroup = new ArrayList();
		ArrayList arlComponentList = new ArrayList();
		ArrayList arlCompGrouplist = null;
		ArrayList arlClauseDetails=new ArrayList();
		
		ResultSet objClauseResultSet = null;
		ResultSet objClausesResultSet=null;
		ResultSet objTbDataResultSet = null;
		ResultSet objEDLNoResultSet=null;
		ResultSet objRefEDLNoResultSet=null;
		ResultSet objPartOfResultSet=null;
		ResultSet objStdEqpResultSet=null;
		ResultSet objCompResultSet=null;
		ResultSet objDelClaCntResultSet=null;

		ClauseVO objClauseGroupVO = new ClauseVO();
		
		try {
			strLogUser = objClauseVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_DELETED_CLAUSES_HISTORY);
			
			objCallableStatement.setInt(1, objClauseVO.getOrderKey());
			objCallableStatement.setString(2, objClauseVO.getUserID());
			objCallableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			LogUtil.logMessage("Status:" + intStatus);
			intStatus = objCallableStatement.executeUpdate();
			
			LogUtil.logMessage("Status:" + intStatus);
			if (intStatus > 0) {
				intStatus = 0;
				
			}
			objClauseResultSet = (ResultSet) objCallableStatement.getObject(3);
			intLSDBErrorID = (int) objCallableStatement.getInt(4);
			strOracleCode = (String) objCallableStatement.getString(5);
			strErrorMessage = (String) objCallableStatement.getString(6);
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
			while(objClauseResultSet.next())
			{
				arlClauseGroup = new ArrayList();
				arlCompGrouplist = new ArrayList();
				objClauseGroupVO = new ClauseVO();
				
				CompGroupVO objCompGroupVO = new CompGroupVO();
				objCompGroupVO.setSectionSeqNo(objClauseResultSet
						.getInt(DatabaseConstants.LS201_SEC_SEQ_NO));
				objCompGroupVO.setSecCode(objClauseResultSet.
						getString(DatabaseConstants.SEC_CODE));
				objCompGroupVO.setSectionName(objClauseResultSet
						.getString(DatabaseConstants.LS201_SEC_NAME));
				objCompGroupVO.setSubSectionSeqNo(objClauseResultSet
						.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO));
				objCompGroupVO.setSubSectionCode(objClauseResultSet
						.getString(DatabaseConstants.LS202_SUBSEC_CODE));
				objCompGroupVO.setSubSectionName(objClauseResultSet
						.getString(DatabaseConstants.LS202_SUBSEC_NAME));
				arlCompGrouplist.add(objCompGroupVO);
				//DEL_CLA_CNT
				objDelClaCntResultSet = (ResultSet)objClauseResultSet
					.getObject("DEL_CLA_CNT");
				while(objDelClaCntResultSet.next()){
					objClauseGroupVO.setNonCurrDelCount(objDelClaCntResultSet
							.getInt("NON_CURR_DEL_CNT"));
					objClauseGroupVO.setCurrDelCount(objDelClaCntResultSet
							.getInt("CURR_DEL_CNT"));
				}
				DBHelper.closeSQLObjects(objDelClaCntResultSet, null, null);
				//CLAUSES
				objClausesResultSet = (ResultSet)objClauseResultSet
					.getObject("CLAUSES");
				while(objClausesResultSet.next()) {
					int cntEDL = 0;
					int cntRefEDl = 0;
					int cntPartOf = 0;
					int[] arlPartSubSecSeqNo = new int[4];
					String[] arlEDLNos = { "", "", "" };
					String[] arlRefEDLNos = { "", "", "" };
					String[] arlpartSubSecCode = { "", "", "", "" };
					int[] arlClauseSeqNo = new int[4];
					ArrayList arlTableRows = new ArrayList();
					ArrayList arlTableColumns = null;
					LogUtil.logMessage("Enters into Clauses resultset loop:");
					objClauseVO = new ClauseVO();
					objClauseVO.setClauseSeqNo(objClausesResultSet
							.getInt(DatabaseConstants.LS300_CLA_SEQ_NO));
					LogUtil.logMessage("ClauseSeqNo:"
							+ objClauseVO.getClauseSeqNo());
					objClauseVO.setVersionNo(objClausesResultSet
							.getInt(DatabaseConstants.LS301_VERSION_NO));
					LogUtil.logMessage("VersionNo:" + objClauseVO.getVersionNo());
					objClauseVO.setSubSectionSeqNo(objClausesResultSet
							.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO));
					LogUtil.logMessage("SubSec:" + objClauseVO.getSubSectionSeqNo());
					objClauseVO.setClauseNum(objClausesResultSet
							.getString(DatabaseConstants.LS406_CLA_NUM));
					LogUtil.logMessage("Clause number: "
							+ objClauseVO.getClauseNum());
					objClauseVO.setPriceBookNumber(objClausesResultSet
							.getInt(DatabaseConstants.LS301_PRICE_BOOK_NUMBER));
					LogUtil.logMessage("Price book number: "
							+ objClauseVO.getPriceBookNumber());
					objClauseVO.setClauseDesc(objClausesResultSet
							.getString(DatabaseConstants.LS301_CLA_DESC));
					LogUtil.logMessage("ClauseDesc: " + objClauseVO.getClauseDesc());
					objClauseVO.setDwONumber(objClausesResultSet
							.getInt(DatabaseConstants.LS301_DWO_NUMBER));
					LogUtil.logMessage("DWO Number: " + objClauseVO.getDwONumber());
					objClauseVO.setPartNumber(objClausesResultSet
							.getInt(DatabaseConstants.LS301_PART_NUMBER));
					LogUtil.logMessage("Part Number:"
							+ objClauseVO.getPartNumber());
					objClauseVO.setEngDataComment(objClausesResultSet
							.getString(DatabaseConstants.LS301_ENGG_DATA_COMMENTS));
					LogUtil.logMessage("Comments:" + objClauseVO.getEngDataComment());
					objClauseVO.setCurrRevFlag(objClausesResultSet
							.getString(DatabaseConstants.CURR_REV_FLAG));
					LogUtil.logMessage("CurrRevFlag:" + objClauseVO.getCurrRevFlag());
					//TABLE_DATA
					objTbDataResultSet = (ResultSet) objClausesResultSet
											.getObject("TABLE_DATA");
					arlTableRows = new ArrayList();
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
					}
//					Added For CR_93
	     			countCol=ApplicationUtil.getTableDataColumnsCount(arlTableRows);
					objClauseVO.setTableDataColSize(countCol);
					objClauseVO.setTableArrayData1(arlTableRows);
					DBHelper.closeSQLObjects(objTbDataResultSet, null, null);
					//EDLNO
					objEDLNoResultSet = (ResultSet) objClausesResultSet
										.getObject("EDLNO");
					while (objEDLNoResultSet.next()) {
						LogUtil.logMessage("Enters into EDLNo Resultset Loop:");
						arlEDLNos[cntEDL] = objEDLNoResultSet
											.getString(DatabaseConstants.LS302_EDL_NO);
						cntEDL++;
					}
					
					objClauseVO.setEdlNo(arlEDLNos);
					DBHelper.closeSQLObjects(objEDLNoResultSet, null, null);
					LogUtil.logMessage("Length of EdlNumber:" + arlEDLNos.length);
					//refEDLNO
					objRefEDLNoResultSet = (ResultSet) objClausesResultSet
										.getObject("refEDLNO");
					while (objRefEDLNoResultSet.next()) {
						LogUtil.logMessage("Enters into RefEDLNo Resultset Loop:");
						arlRefEDLNos[cntRefEDl] = objRefEDLNoResultSet
												.getString(DatabaseConstants.LS303_REF_EDL_NO);
						cntRefEDl++;
					}
					
					LogUtil.logMessage("Length of RefEdlNumber:"+ arlRefEDLNos.length);
					
					objClauseVO.setRefEDLNo(arlRefEDLNos);
					
					DBHelper.closeSQLObjects(objRefEDLNoResultSet, null, null);
					//STD_EQUIP
					objStdEqpResultSet = (ResultSet) objClausesResultSet
					.getObject("STD_EQUIP");
					while (objStdEqpResultSet.next()) {
						LogUtil.logMessage("Enters into std equip ResultSet");
						objClauseVO.setStandardEquipmentDesc(objStdEqpResultSet
								.getString(DatabaseConstants.LS060_STD_EQP_DESC));
						LogUtil.logMessage("StandardEquipmentDesc:"
								+ objClauseVO.getStandardEquipmentDesc());
						
					}
					DBHelper.closeSQLObjects(objStdEqpResultSet, null, null);
					//PART_OF
					objPartOfResultSet = (ResultSet) objClausesResultSet
											.getObject("PART_OF");
					while (objPartOfResultSet.next()) {
						LogUtil
						.logMessage("PartCode Of values"+
								objPartOfResultSet.getString(DatabaseConstants.LS407_PART_OF_CLA_NO));
						arlpartSubSecCode[cntPartOf] = objPartOfResultSet
										.getString(DatabaseConstants.LS407_PART_OF_CLA_NO);
						arlClauseSeqNo[cntPartOf] = objPartOfResultSet
										.getInt(DatabaseConstants.LS300_PART_OF_CLA_SEQ_NO);
						arlPartSubSecSeqNo[cntPartOf] = objPartOfResultSet
										.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO);
						cntPartOf++;
					}
					objClauseVO.setPartOfCode(arlpartSubSecCode);
					LogUtil.logMessage("PartOfCode:" + objClauseVO.getPartOfCode());
					LogUtil.logMessage("Length of PartOfCode:"+ arlpartSubSecCode.length);
					objClauseVO.setPartOfSeqNo(arlPartSubSecSeqNo);
					objClauseVO.setClauseSeqNum(arlClauseSeqNo);
					LogUtil.logMessage("PartOfSeqNo:"+ objClauseVO.getPartOfSeqNo());
					DBHelper.closeSQLObjects(objPartOfResultSet, null, null);
					objCompResultSet = (ResultSet) objClausesResultSet
					.getObject("COMP_NAME");
					arlComponentList = new ArrayList();
					while(objCompResultSet.next()){
						LogUtil.logMessage("Enters into Comp name Result Set");
						ComponentVO objCompVO = new ComponentVO();
						objCompVO.setComponentName(objCompResultSet
								.getString(DatabaseConstants.LS140_COMP_NAME));
						objCompVO.setComponentSeqNo(objCompResultSet
								.getInt(DatabaseConstants.LS140_COMP_SEQ_NO));
						objCompVO.setComponentGroupSeqNo(objCompResultSet
								.getInt(DatabaseConstants.LS130_COMP_GRP_SEQ_NO));
						objCompVO.setComponentGroupName(objCompResultSet
								.getString(DatabaseConstants.LS130_COMP_GRP_NAME));
						arlComponentList.add(objCompVO);
						LogUtil.logMessage("component name: "+objCompVO.getComponentName());
					}
					objClauseVO.setComponentList(arlComponentList);	
					
					arlClauseGroup.add(objClauseVO);
					DBHelper.closeSQLObjects(objCompResultSet, null, null);
				}
				DBHelper.closeSQLObjects(objClausesResultSet, null, null);
				objClauseGroupVO.setCompGroupVO(arlCompGrouplist);
				objClauseGroupVO.setClauseGroup(arlClauseGroup);
				arlClauseDetails.add(objClauseGroupVO);
			}
			DBHelper.closeSQLObjects(objClauseResultSet, null, null);
			LogUtil.logMessage("Size of arlClauseDetails: " + arlClauseDetails.size());
		}
		catch (DataAccessException objDatExp) {
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
			LogUtil.logMessage("Enters into Exception block in DAO:"
									+ objExp.getMessage());
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
									+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				DBHelper.closeSQLObjects(null, objCallableStatement,objConnection);
			} catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception exception...");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
									+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
		}
		return arlClauseDetails;
	}
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objClauseVO
	 *            the object for Inserting clause
	 * @return boolean that returns True or False
	 * @throws EMDException
	 **************************************************************************/
	
	public static synchronized int retrieveDeletedClause(ClauseVO objClauseVO)
	throws EMDException {
		LogUtil.logMessage("Inside the OrderClauseDAO:retrieveDeletedClause");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		
		int intLSDBErrorID = 0;
		int intStatus = 0;

		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		String strLogUser = "";
				
		try {
			strLogUser = objClauseVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_RETRIEVE_DELETED_CLAUSE);
			
			objCallableStatement.setInt(1, objClauseVO.getOrderKey());
			objCallableStatement.setInt(2, objClauseVO.getClauseSeqNo());
			objCallableStatement.setString(3, objClauseVO.getReason());
			objCallableStatement.setString(4, objClauseVO.getCurrRevFlag());
			objCallableStatement.setString(5, objClauseVO.getUserID());
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			LogUtil.logMessage("Status:" + intStatus);
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			intLSDBErrorID = (int) objCallableStatement.getInt(6);
			strOracleCode = (String) objCallableStatement.getString(7);
			strErrorMessage = (String) objCallableStatement.getString(8);
			
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Enters into Error Loop:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				strMessage = String.valueOf(intLSDBErrorID);
				LogUtil.logMessage("Error message in DAO:" + strMessage);
				
				objErrorInfo.setMessageID(strMessage);
				LogUtil.logMessage("Error message in ErrorInfo:"
								+ objErrorInfo.getMessageID());
				intStatus = intLSDBErrorID;
				
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
		}
		
		catch (DataAccessException objDatExp) {
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
			LogUtil.logMessage("Enters into Exception block in DAO:"
						+ objExp.getMessage());
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
						+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				DBHelper.closeSQLObjects(null, objCallableStatement,objConnection);
			}
			catch (Exception objExp) {
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
	 * @version 1.0 Added for CR_97 by RJ85495 14-03-11
	 * @param objConnection,objClauseVO,blnConnFlag
	 *            the object for Inserting clause
	 * @return int that returns an integer
	 * @throws EMDException
	 **************************************************************************/
	
	public static int createNewCRForm(Connection objConnection,ClauseVO objClauseVO,String blnConnFlag)
	throws EMDException{
		LogUtil.logMessage("Inside the OrderClauseDAO:createNewCRForm");
		//Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		
		int intLSDBErrorID = 0;
		int intStatus = 0;
		int intReqID =0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		String strLogUser = "";
				
		try {
			strLogUser = objClauseVO.getUserID();
			if(objConnection==null && (blnConnFlag.equalsIgnoreCase("N"))){
				objConnection=DBHelper.prepareConnection();				
				}
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_CREATE_NEW_ORDR_COMP_REQ);
			
			objCallableStatement.setInt(1, objClauseVO.getModelSeqNo());
			objCallableStatement.setInt(2, objClauseVO.getSectionSeqNo());
			objCallableStatement.setInt(3, objClauseVO.getSubSectionSeqNo());
			objCallableStatement.setInt(4, objClauseVO.getLeadCompGrpSeqNo());
			objCallableStatement.setInt(5, objClauseVO.getCompSeqNum());
			objCallableStatement.setInt(6, objClauseVO.getClauseNo());
			objCallableStatement.setString(7, objClauseVO.getReason());
			objCallableStatement.setString(9, objClauseVO.getUserID());
			objCallableStatement.registerOutParameter(8, Types.INTEGER);
			objCallableStatement.registerOutParameter(10, Types.INTEGER);
			objCallableStatement.registerOutParameter(11, Types.VARCHAR);
			objCallableStatement.registerOutParameter(12, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			LogUtil.logMessage("Status:" + intStatus);
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			intReqID = (int) objCallableStatement.getInt(8);
			LogUtil.logMessage("the request ID is :"+intReqID);
			intLSDBErrorID = (int) objCallableStatement.getInt(10);
			strOracleCode = (String) objCallableStatement.getString(11);
			strErrorMessage = (String) objCallableStatement.getString(12);
			
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Enters into Error Loop:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				strMessage = String.valueOf(intLSDBErrorID);
				LogUtil.logMessage("Error message in DAO:" + strMessage);
				
				objErrorInfo.setMessageID(strMessage);
				LogUtil.logMessage("Error message in ErrorInfo:"
								+ objErrorInfo.getMessageID());
				intStatus = intLSDBErrorID;
				
				throw new DataAccessException(objErrorInfo);
			}else if (strOracleCode != null && !"0".equals(strOracleCode)) {
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
		}
		
		catch (DataAccessException objDatExp) {
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
			LogUtil.logMessage("Enters into Exception block in DAO:"
						+ objExp.getMessage());
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
						+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				if("N".equals(blnConnFlag))
				DBHelper.closeSQLObjects(null, objCallableStatement,objConnection);
			}
			catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception exception...");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
								+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
		}
		return intReqID;
	}
	
	/***************************************************************************
	 * This method is for addDelSalesVerEnggSpec
	 * Added for LSDB_CR-99 by SD41630
	 * @version 1.0
	 * @param objClauseVO
	 * @return int	
	 * @throws EMDException
	 **************************************************************************/
	
	public static int addDelSalesVerEnggSpecDAO(ClauseVO objClauseVO) throws EMDException {
		LogUtil.logMessage("Inside the OrderClauseDAO:addDelSalesVerEnggSpecDAO");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		
		try {
			strLogUser = objClauseVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_INS_OR_DEL_SALES_VERSION);
			
			objCallableStatement.setInt(1, objClauseVO.getOrderKey());
			objCallableStatement.setInt(2, objClauseVO.getClauseSeqNo());
			objCallableStatement.setString(3, objClauseVO.getSalesClaDesc());
			objCallableStatement.setString(4, objClauseVO.getSalesVerFLAG());
			objCallableStatement.setString(5, objClauseVO.getUserID());
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			LogUtil.logMessage("Status:" + intStatus);
			intStatus = objCallableStatement.executeUpdate();
			
			LogUtil.logMessage("Status:" + intStatus);
			if (intStatus > 0)
			{
				intStatus = 0;
			}
			
			intLSDBErrorID = (int) objCallableStatement.getInt(6);
			strOracleCode = (String) objCallableStatement.getString(7);
			strErrorMessage = (String) objCallableStatement.getString(8);
			
			if (intLSDBErrorID != 0)
			{
				LogUtil.logMessage("Enters into Error Loop:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				strMessage = String.valueOf(intLSDBErrorID);
				LogUtil.logMessage("Error message in DAO:" + strMessage);
				
				objErrorInfo.setMessageID(strMessage);
				LogUtil.logMessage("Error message in ErrorInfo:"+ objErrorInfo.getMessageID());
				
				throw new DataAccessException(objErrorInfo);
			}
			else if (strOracleCode != null && !"0".equals(strOracleCode))
			{
				LogUtil.logMessage("enters into oracle error code block:"+ strOracleCode);
				ErrorInfo objErrorInfo = new ErrorInfo();
				StringBuffer objStBuffer = new StringBuffer();
				objStBuffer.append(strOracleCode + " ");
				objStBuffer.append(strErrorMessage);
				
				objErrorInfo.setMessage(objStBuffer.toString());
				objConnection.rollback();
				
				throw new ApplicationException(objErrorInfo);
			}
		}
		catch (DataAccessException objDatExp) {
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
						objConnection);
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
	 * This method is to fetch Order Specific Clauses
	 * Added for LSDB_CR_108 by ER91220
	 * @version 1.0
	 * @param objOrderVO
	 * @return int	
	 * @throws EMDException
	 **************************************************************************/
	public static ArrayList fetchOrdrSpecificClauses(OrderVO objOrderVO)
	throws EMDException {
		LogUtil.logMessage("Enters into OrderClauseDAO:fetchOrdrSpecificClauses");
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
		ResultSet objCompResultSet = null;
		ArrayList arlClauseList = new ArrayList();
		ClauseVO objClauseVO = null;
		ArrayDescriptor arrdesc = null;
		String strLogUser = "";
		
		try {
			strLogUser = objOrderVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			Connection dconn = ((DelegatingConnection) objConnection).getInnermostDelegate(); //Added for CR-123
			arrdesc = new ArrayDescriptor(DatabaseConstants.STR_ARRAY,dconn);
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_SELECT_ORDR_SPECIFIC_CLA);
			
			ARRAY arrOrderKeys = null;
			
			if (objOrderVO.getOrderKeys() != null) 
			{
				arrOrderKeys = new ARRAY(arrdesc, dconn,
						objOrderVO.getOrderKeys());
			}
			else{	
				arrOrderKeys = new ARRAY(arrdesc, dconn, null);  
			} 

			objCallableStatement.setArray(1, arrOrderKeys);
			objCallableStatement.setString(2, DatabaseConstants.DATALOCATION_SNAP_SHOT);
			objCallableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			objCallableStatement.setString(4, objOrderVO.getUserID());
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.execute();

			objClauseResultSet = (ResultSet) objCallableStatement.getObject(3);

			intLSDBErrorID = objCallableStatement.getInt(5);
			strOracleCode = objCallableStatement.getString(6);
			strErrorMessage = (String) objCallableStatement.getString(7);
			
			if (intLSDBErrorID != 0) {
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
				
				int cntEDL = 0;
				int cntRefEDl = 0;
				int cntPartOf = 0;
				int[] arlPartSubSecSeqNo = new int[4];
				ArrayList arlEDLNos = new ArrayList();
				ArrayList arlRefEDLNos = new ArrayList();
				ArrayList arlpartSubSecCode = new ArrayList();
				int[] arlClauseSeqNo = new int[4];
				ArrayList arlTableRows = new ArrayList();
				ArrayList arlTableColumns = null;
				int sectionSeqNo = 0;
				
				 
				objClauseVO = new ClauseVO();

				objClauseVO.setOrderNo(objClauseResultSet.getString(DatabaseConstants.LS400_ORDR_NO));
				
				objClauseVO.setStatus(objClauseResultSet.getString(DatabaseConstants.STATUS));
				
				objClauseVO.setClauseSeqNo(objClauseResultSet.getInt(DatabaseConstants.LS300_CLA_SEQ_NO));
				
				objClauseVO.setVersionNo(objClauseResultSet.getInt(DatabaseConstants.LS301_VERSION_NO));
				
				objClauseVO.setClauseNum(objClauseResultSet.getString(DatabaseConstants.LS406_CLA_NUM));
				
				objClauseVO.setClauseDesc(objClauseResultSet.getString(DatabaseConstants.LS301_CLA_DESC));
				
				objClauseVO.setDwONumber(objClauseResultSet.getInt(DatabaseConstants.LS301_DWO_NUMBER));
				
				objClauseVO.setComments(objClauseResultSet.getString(DatabaseConstants.LS301_ENGG_DATA_COMMENTS));
				
				objClauseVO.setPartNumber(objClauseResultSet.getInt(DatabaseConstants.LS301_PART_NUMBER));
				
				objClauseVO.setPriceBookNumber(objClauseResultSet.getInt(DatabaseConstants.LS301_PRICE_BOOK_NUMBER));
				
				//Cursor of EDL Number
				objEDLNoResultSet = (ResultSet) objClauseResultSet.getObject("EDL_NO");
				
				while (objEDLNoResultSet.next()) 
				{
					arlEDLNos.add(objEDLNoResultSet.getString(DatabaseConstants.LS302_EDL_NO));
					cntEDL++;
				}
				
				objClauseVO.setEdlNO(arlEDLNos);
				DBHelper.closeSQLObjects(objEDLNoResultSet, null, null);
				
				// Cursor of Ref EDL Number				
				objRefEDLNoResultSet = (ResultSet) objClauseResultSet.getObject("REF_EDL_NO");
				while (objRefEDLNoResultSet.next()) {
					arlRefEDLNos.add(objRefEDLNoResultSet.getString(DatabaseConstants.LS303_REF_EDL_NO));
					cntRefEDl++;
				}
								
				objClauseVO.setRefEDLNO(arlRefEDLNos);
				DBHelper.closeSQLObjects(objRefEDLNoResultSet, null, null);
				
				
				// Cursor of PART OF
				objSubSecResultSet = (ResultSet) objClauseResultSet.getObject("PartOF");
				
				while (objSubSecResultSet.next()) {
					arlpartSubSecCode.add(objSubSecResultSet.getString(DatabaseConstants.LS407_PART_OF_CLA_NO));
					arlClauseSeqNo[cntPartOf] = objSubSecResultSet.getInt(DatabaseConstants.LS300_PART_OF_CLA_SEQ_NO);
					arlPartSubSecSeqNo[cntPartOf] = objSubSecResultSet.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO);
					sectionSeqNo=objSubSecResultSet.getInt(DatabaseConstants.LS201_SEC_SEQ_NO);
					cntPartOf++;
				}
				
				objClauseVO.setPartOF(arlpartSubSecCode);
				objClauseVO.setPartOfSeqNo(arlPartSubSecSeqNo);
				objClauseVO.setClauseSeqNum(arlClauseSeqNo);
				objClauseVO.setSectionSeqNo(sectionSeqNo);
				//Added for CR-127
				objClauseVO.setClauseDelFlag(objClauseResultSet
						.getString(DatabaseConstants.LS406_CLA_DEL_FLAG));
				
				DBHelper.closeSQLObjects(objSubSecResultSet, null, null);
				
				//Cursor for Table Data
				objTbDataResultSet = (ResultSet) objClauseResultSet.getObject("TABLE_DATA");
				
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
     			countCol=ApplicationUtil.getTableDataColumnsCount(arlTableRows);
				objClauseVO.setTableDataColSize(countCol);
				objClauseVO.setTableArrayData1(arlTableRows);
				DBHelper.closeSQLObjects(objTbDataResultSet, null, null);
				

				// Cursor for Standard Equipment
				objStdEqpResultSet = (ResultSet) objClauseResultSet.getObject("STD_EQUIP");
				while (objStdEqpResultSet.next()) {
					objClauseVO.setStandardEquipmentDesc(objStdEqpResultSet.getString(DatabaseConstants.LS060_STD_EQP_DESC));
					
				}
				DBHelper.closeSQLObjects(objStdEqpResultSet, null, null);
				
				//Cursor For Components
				objCompResultSet = (ResultSet) objClauseResultSet.getObject("COMPONENTS");
				
				while (objCompResultSet.next()){
					objClauseVO.setCompSeqNum(objCompResultSet.getInt(DatabaseConstants.COMP_SEQ_NO));
					LogUtil.logMessage("Component Sequence Number = "+objClauseVO.getCompSeqNum());
					objClauseVO.setComponentName(objCompResultSet.getString(DatabaseConstants.COMP_NAME));
					LogUtil.logMessage("Component Name = "+objClauseVO.getComponentName());
					objClauseVO.setDeleteFlag(objCompResultSet.getString(DatabaseConstants.COMPONENT_DELETE_FLAG));
					LogUtil.logMessage("Delete Flag = "+objClauseVO.getDeleteFlag());
					}
				DBHelper.closeSQLObjects(objCompResultSet, null, null);
				
				arlClauseList.add(objClauseVO);
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
					+ objErrorInfo);
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
				
				DBHelper.closeSQLObjects(objClauseResultSet,
						objCallableStatement, objConnection);
				
			}
			
			catch (Exception objExp) {
				
				LogUtil.logMessage("Enters into Exception exception...");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return arlClauseList;
	}
		
	/***************************************************************************
	 * This method is for Rearranging of Order Clauses
	 * Added for LSDB_CR-111 by RR68151
	 * @version 1.0
	 * @param objClauseVO
	 * @return int	
	 * @throws EMDException
	 **************************************************************************/
	
	public static int saveOrderClaReArrange(ClauseVO objClauseVO) throws EMDException {
		LogUtil.logMessage("Inside the OrderClauseDAO:saveOrderClaReArrange");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		ArrayDescriptor arrClaSeqNos = null;
		String strLogUser = "";
		
		try {
			strLogUser = objClauseVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_REARRANGE_ORDR_CLAUSES);
			Connection dconn = ((DelegatingConnection) objConnection).getInnermostDelegate(); //Added for CR-123
			
			objCallableStatement.setInt(1, objClauseVO.getOrderKey());
			objCallableStatement.setInt(2, objClauseVO.getSubSectionSeqNo());
			arrClaSeqNos = new ArrayDescriptor(DatabaseConstants.IN_ARRAY,dconn);
			LogUtil.logMessage("ArrayList ClaSeqNoList Size :"+ objClauseVO.getClaSeqNoList().length);
			ARRAY arrClaSeqNo = new ARRAY(arrClaSeqNos, dconn, objClauseVO.getClaSeqNoList());
			objCallableStatement.setArray(3, arrClaSeqNo);
			objCallableStatement.setString(4, objClauseVO.getUserID());
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			LogUtil.logMessage("Status:" + intStatus);
			intStatus = objCallableStatement.executeUpdate();
			
			LogUtil.logMessage("Status:" + intStatus);
			if (intStatus > 0)
			{
				intStatus = 0;
			}
			
			intLSDBErrorID = (int) objCallableStatement.getInt(5);
			strOracleCode = (String) objCallableStatement.getString(6);
			strErrorMessage = (String) objCallableStatement.getString(7);
			
			if (intLSDBErrorID != 0)
			{
				LogUtil.logMessage("Enters into Error Loop:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				strMessage = String.valueOf(intLSDBErrorID);
				LogUtil.logMessage("Error message in DAO:" + strMessage);
				
				objErrorInfo.setMessageID(strMessage);
				LogUtil.logMessage("Error message in ErrorInfo:"+ objErrorInfo.getMessageID());
				
				throw new DataAccessException(objErrorInfo);
			}
			else if (strOracleCode != null && !"0".equals(strOracleCode))
			{
				LogUtil.logMessage("enters into oracle error code block:"+ strOracleCode);
				ErrorInfo objErrorInfo = new ErrorInfo();
				StringBuffer objStBuffer = new StringBuffer();
				objStBuffer.append(strOracleCode + " ");
				objStBuffer.append(strErrorMessage);
				
				objErrorInfo.setMessage(objStBuffer.toString());
				objConnection.rollback();
				
				throw new ApplicationException(objErrorInfo);
			}
		}
		catch (DataAccessException objDatExp) {
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
						objConnection);
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
	 * This method is for populating the which are the Clauses with Indicators
	 * Added for LSDB_CR-113 
	 * @version 1.0
	 * @param objClauseVO
	 * @return int			
	 * @throws EMDException
	 **************************************************************************/
	public static ArrayList clausesWithIndicators(ClauseVO objclauseVO)
	throws EMDException {
		LogUtil.logMessage("Enter into OrderClauseDAO:clausesWithIndicators");
		
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		int countCol=0;
		ResultSet objSectionResultSet = null;
		ResultSet objSubSecResultSet = null;
		ResultSet objClauseResultSet = null;
		ResultSet objPartofResultSet = null;
		ResultSet objEDLNoResultSet = null;
		ResultSet objRefEDLNoResultSet = null;
		ResultSet objTbDataResultSet = null;
		ResultSet objStdEqpResultSet = null;
		ResultSet objCompResultSet = null;
		String strLogUser = "";
		ArrayList arlSecList= new ArrayList();
		ArrayList arlComponentList = new ArrayList();
		boolean subSecExists = false;
		boolean clauseExists = false;
		
		try {
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_SEL_CLA_WITH_INDICATORS);
			/*LogUtil.logMessage("OrderKey = "+ objclauseVO.getOrderKey());
			LogUtil.logMessage("USER ID = "+ objclauseVO.getUserID());*/
			if (objclauseVO.getOrderKey() != 0) {
				objCallableStatement.setInt(1, objclauseVO.getOrderKey());
			} else {
				objCallableStatement.setNull(1, Types.NULL);
			}
			
			objCallableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			objCallableStatement.setString(3, objclauseVO.getUserID());
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.execute();
			
			objSectionResultSet = (ResultSet) objCallableStatement.getObject(2);

			intLSDBErrorID = (int) objCallableStatement.getInt(4);
			strOracleCode = (String) objCallableStatement.getString(5);
			strErrorMessage = (String) objCallableStatement.getString(6);
			
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Enters into Error Loop:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessageID("" + intLSDBErrorID);
				LogUtil.logMessage("Error message in ErrorInfo:"
						+ objErrorInfo.getMessageID());
				throw new DataAccessException(objErrorInfo);
				
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {
				
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
			
			while (objSectionResultSet.next()) {
				
				LogUtil.logMessage("Inside objSectionResultSet");
				objSubSecResultSet = (ResultSet) objSectionResultSet.getObject("SUBSECTION");
				
				ArrayList arlSubSectionList = new ArrayList();
				ArrayList arlClauseList = new ArrayList();
				subSecExists = false;
				
				while (objSubSecResultSet.next()) {
					
					
					LogUtil.logMessage("Inside objSubSectionResultSet");
					objClauseResultSet = (ResultSet) objSubSecResultSet.getObject("CLAUSES");
					arlClauseList = new ArrayList();
					clauseExists = false;
					
					while (objClauseResultSet.next()) {
						
						clauseExists = true;	
						LogUtil.logMessage("Inside objClauseResultSet");
						
						int cntEDL = 0;
						int cntRefEDl = 0;
						int cntPartOf = 0;
						int[] arlPartSubSecSeqNo = new int[4];
						ArrayList arlEDLNos = new ArrayList();
						ArrayList arlRefEDLNos = new ArrayList();
						ArrayList arlpartSubSecCode = new ArrayList();
						int[] arlClauseSeqNo = new int[4];
						ArrayList arlTableRows = new ArrayList();
						ArrayList arlTableColumns = null;
						int partofLeadCompGrp = 0;
						ClauseVO objClauseVO = new ClauseVO(); 
						
						objClauseVO.setClauseSeqNo(objClauseResultSet.getInt(DatabaseConstants.LS300_CLA_SEQ_NO));
						
						objClauseVO.setClauseNum(objClauseResultSet.getString(DatabaseConstants.LS406_CLA_NUM));
						
						objClauseVO.setIndicatorFlag(objClauseResultSet.getString(DatabaseConstants.INDICATOR_FLAG));
						
						objClauseVO.setVersionNo(objClauseResultSet.getInt(DatabaseConstants.LS301_VERSION_NO));
						
						objClauseVO.setClauseDesc(objClauseResultSet.getString(DatabaseConstants.LS301_CLA_DESC));
						
						objClauseVO.setCustomerName(objClauseResultSet.getString(DatabaseConstants.LS050_CUST_NAME));
						
						objClauseVO.setDwONumber(objClauseResultSet.getInt(DatabaseConstants.LS301_DWO_NUMBER));
						
						objClauseVO.setPartNumber(objClauseResultSet.getInt(DatabaseConstants.LS301_PART_NUMBER));
						
						objClauseVO.setPriceBookNumber(objClauseResultSet.getInt(DatabaseConstants.LS301_PRICE_BOOK_NUMBER));
						
						objClauseVO.setComments(objClauseResultSet.getString(DatabaseConstants.LS301_ENGG_DATA_COMMENTS));
						
						objClauseVO.setClaReason(objClauseResultSet.getString(DatabaseConstants.LS301_CLA_REASON));
						
						
						//Cursor of EDL Number
						objEDLNoResultSet = (ResultSet) objClauseResultSet.getObject("EDL_NO");
						while (objEDLNoResultSet.next()) 
						{
							LogUtil.logMessage("Inside objEDLNoResultSet");
							arlEDLNos.add(objEDLNoResultSet.getString(DatabaseConstants.LS302_EDL_NO));
							cntEDL++;
						}
						objClauseVO.setEdlNO(arlEDLNos);
						DBHelper.closeSQLObjects(objEDLNoResultSet, null, null);
						
						
						// Cursor of Ref EDL Number				
						objRefEDLNoResultSet = (ResultSet) objClauseResultSet.getObject("REF_EDL_NO");
						while (objRefEDLNoResultSet.next()) {
							LogUtil.logMessage("Inside objRefEDLNoResultSet");
							arlRefEDLNos.add(objRefEDLNoResultSet.getString(DatabaseConstants.LS303_REF_EDL_NO));
							cntRefEDl++;
						}
										
						objClauseVO.setRefEDLNO(arlRefEDLNos);
						DBHelper.closeSQLObjects(objRefEDLNoResultSet, null, null);
										
						// Cursor of PART OF
						
						objPartofResultSet = (ResultSet) objClauseResultSet.getObject("PART_OF");
						while (objPartofResultSet.next()) {
							LogUtil.logMessage("Inside objPartofResultSet");
							arlpartSubSecCode.add(objPartofResultSet.getString(DatabaseConstants.LS304_SUBSEC_NO));
							arlClauseSeqNo[cntPartOf] = objPartofResultSet.getInt(DatabaseConstants.LS304_PART_OF_CLA_SEQ_NO);
							arlPartSubSecSeqNo[cntPartOf] = objPartofResultSet.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO);
							partofLeadCompGrp=objPartofResultSet.getInt(DatabaseConstants.LS304_PART_OF_LEAD_CMP_GRP);
							cntPartOf++;
						}
						
						objClauseVO.setPartOF(arlpartSubSecCode);
						objClauseVO.setPartOfSeqNo(arlPartSubSecSeqNo);
						objClauseVO.setClauseSeqNum(arlClauseSeqNo);
						objClauseVO.setPartofLeadCompGrp(partofLeadCompGrp);
						
						DBHelper.closeSQLObjects(objPartofResultSet, null, null);
						
						//Cursor For ComponentName
						objCompResultSet = (ResultSet) objClauseResultSet.getObject("COMP_NAME");
						arlComponentList = new ArrayList();
						while (objCompResultSet.next()){
							LogUtil.logMessage("Inside objCompResultSet");
							ComponentVO objCompVO = new ComponentVO();
							objCompVO.setComponentName(objCompResultSet.getString(DatabaseConstants.LS140_COMP_NAME));
							objCompVO.setComponentSeqNo(objCompResultSet.getInt(DatabaseConstants.LS140_COMP_SEQ_NO));
							objCompVO.setComponentGroupSeqNo(objCompResultSet.getInt(DatabaseConstants.LS130_COMP_GRP_SEQ_NO));
							objCompVO.setComponentGroupName(objCompResultSet.getString(DatabaseConstants.LS130_COMP_GRP_NAME));
							/*
							objCompVO.setValidationFlag(objCompResultSet.getString(DatabaseConstants.LS130_VALIDATION_FLAG));
							objCompVO.setCompDefFlag(objCompResultSet.getString(DatabaseConstants.LS204_DEFAULT_FLAG));
							objCompVO.setCompLeadFlag(objCompResultSet.getString(DatabaseConstants.Italics));*/
							arlComponentList.add(objCompVO);
							}
						objClauseVO.setComponentList(arlComponentList);
						DBHelper.closeSQLObjects(objCompResultSet, null, null);
					
						
						//Cursor for Table Data
						objTbDataResultSet = (ResultSet) objClauseResultSet.getObject("TAB_DATA");
						while (objTbDataResultSet.next()) {
							LogUtil.logMessage("Inside objTbDataResultSet");
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
							arlTableColumns.add(objTbDataResultSet
									.getString(DatabaseConstants.LS306_HEADER_FLAG));
							arlTableRows.add(arlTableColumns);
							
						}
		     			countCol=ApplicationUtil.getTableDataColumnsCount(arlTableRows);
						objClauseVO.setTableDataColSize(countCol);
						objClauseVO.setTableArrayData1(arlTableRows);
						DBHelper.closeSQLObjects(objTbDataResultSet, null, null);
						
						// Cursor for Standard Equipment
						objStdEqpResultSet = (ResultSet) objClauseResultSet.getObject("STD_EQUIP");
						while (objStdEqpResultSet.next()) {
							LogUtil.logMessage("Inside objStdEqpResultSet");
							objClauseVO.setStandardEquipmentSeqNo(objStdEqpResultSet.getInt(DatabaseConstants.LS060_STD_EQP_SEQ_NO));
							objClauseVO.setStandardEquipmentDesc(objStdEqpResultSet.getString(DatabaseConstants.LS060_STD_EQP_DESC));
						}
						DBHelper.closeSQLObjects(objStdEqpResultSet, null, null);
						arlClauseList.add(objClauseVO);
						LogUtil.logMessage("objClauseVO.getClauseNum():"+objClauseVO.getClauseNum());
						LogUtil.logMessage("objClauseVO.getClauseNo():"+objClauseVO.getClauseSeqNo());
					}
					if(clauseExists){
						subSecExists = true;
						SubSectionVO objSubSectionVO=new SubSectionVO();
						objSubSectionVO.setSubSecSeqNo(objSubSecResultSet.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO));
						LogUtil.logMessage("Inside objSubSectionResultSet" + objSubSectionVO.getSubSecSeqNo());
						objSubSectionVO.setSubSecCode(objSubSecResultSet.getString(DatabaseConstants.SUBSEC_CODE));
						
						objSubSectionVO.setSubSecName(objSubSecResultSet.getString(DatabaseConstants.LS202_SUBSEC_NAME));
						LogUtil.logMessage("Inside objSubSectionResultSet" + objSubSectionVO.getSubSecName());
						objSubSectionVO.setClauseGroup(arlClauseList);
						arlSubSectionList.add(objSubSectionVO);
					}
					
				}
					if(subSecExists){
						
					//LogUtil.logMessage("Size of ClauseList" + arlClauseList.size());
					SectionVO objSectionVO=new SectionVO();
					objSectionVO.setSectionSeqNo(objSectionResultSet.getInt(DatabaseConstants.LS201_SEC_SEQ_NO));
					objSectionVO.setSectionCode(objSectionResultSet.getString(DatabaseConstants.LS201_SEC_CODE));
					objSectionVO.setSectionName(objSectionResultSet.getString(DatabaseConstants.LS201_SEC_NAME));
					objSectionVO.setSubSec(arlSubSectionList);
					arlSecList.add(objSectionVO);
					//LogUtil.logMessage("objSectionVO.getSectionName():"+objSectionVO.getSectionName());
					}
					DBHelper.closeSQLObjects(objClauseResultSet, null, null);
				}
				
		} catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException OrderClauseDAO:clausesWithIndicators");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in OrderClauseDAO:clausesWithIndicators"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException OrderClauseDAO:clausesWithIndicators");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in OrderClauseDAO:clausesWithIndicators"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception OrderClauseDAO:clausesWithIndicators");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(objClauseResultSet, objCallableStatement,
						objConnection);
			}
			
			catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception OrderClauseDAO:clausesWithIndicators");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		LogUtil.logMessage("Size of SectionList:"+arlSecList.size());
		return arlSecList;
	}
	
	//Added for CR-121
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objClauseVO
	 *            the object for deleting OptionalClauses
	 * @return boolean that returns True or False
	 * @throws EMDException
	 **************************************************************************/
	
	public static int removeOptionalClauses(ClauseVO objClauseVO) throws EMDException {
		LogUtil.logMessage("Inside the OrderClauseDAO:removeOptionalClauses");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		ArrayDescriptor arrdesc = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		
		try {
			strLogUser = objClauseVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_REMOVE_CLA_TEMP);
			Connection dconn = ((DelegatingConnection) objConnection).getInnermostDelegate(); //Added for CR-123
			
			objCallableStatement.setInt(1, objClauseVO.getOrderKey());
			objCallableStatement.setString(2, objClauseVO.getDataLocationType());
			
			arrdesc = new ArrayDescriptor(DatabaseConstants.IN_ARRAY,
					dconn);
			ARRAY arrOrdClaArray = new ARRAY(arrdesc, dconn, null);
			objCallableStatement.setArray(3, arrOrdClaArray);
			
			
			objCallableStatement.setString(4, objClauseVO.getRemoveAllOptCla());
			objCallableStatement.setString(5, objClauseVO.getUserID());
			
			LogUtil.logMessage("objClauseVO.getOrderKey()::::" + objClauseVO.getOrderKey());
			LogUtil.logMessage("objClauseVO.getDataLocationType()::::"+ objClauseVO.getDataLocationType());
			LogUtil.logMessage("arrOrdClaArray::::"+ arrOrdClaArray);
			LogUtil.logMessage("objClauseVO.getRemoveAllOptCla()::::"+ objClauseVO.getRemoveAllOptCla());
			LogUtil.logMessage("objClauseVO.getUserID()::::"+ objClauseVO.getUserID());
			
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			
			LogUtil.logMessage("Status:" + intStatus);
			intStatus = objCallableStatement.executeUpdate();
			
			LogUtil.logMessage("Status:" + intStatus);
			if (intStatus > 0) {
				intStatus = 0;
				
			}
			
			intLSDBErrorID = (int) objCallableStatement.getInt(6);
			strOracleCode = (String) objCallableStatement.getString(7);
			strErrorMessage = (String) objCallableStatement.getString(8);
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
		}
		
		catch (DataAccessException objDatExp) {
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
						objConnection);
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
	
}
