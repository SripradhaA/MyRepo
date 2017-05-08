package com.EMD.LSDB.dao.MasterMaintenance;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
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
import com.EMD.LSDB.vo.common.ChangeRequest1058VO;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.CompGroupVO;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SubSectionVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the methods for the fetching clause descriptions
 *          for a Model
 ******************************************************************************/

/***************************************************************************
 * ------------------------------------------------------------------------------------------------------
 *     Date         version  create by              comments                              Remarks 
 * 19/01/2010        1.0      SD41630      Added new mehtod for view characterisitic     Added for CR_81
 * 										   group Report by model.  
 *  
 * 20/05/2010          1.0      SD41630      Upadted the mehtod for viewCharactersiticGrpRpt Added for CR_85
 * 02/07/2010          1.1      SD41630      Upadted the mehtod  viewMasterSpecByModel       Added for CR_88	
 * 											 for get childflag 									      
 --------------------------------------------------------------------------------------------------------
  **************************************************************************/

public class ViewSpecByModelDAO {
	
	private ViewSpecByModelDAO() {
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSubSectionVO
	 *            The object for searching clause details for a model
	 * @return arrayList The list contains the clause details
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList fetchSpecByModel(SubSectionVO objSubSectionVO)
	throws EMDException {
		LogUtil.logMessage("Entering ViewSpecByModelDAO.fetchSpecByModel");
		/** Declarations * */
		Connection objConnnection = null;
		ArrayList arlSpecs = new ArrayList();
		ArrayList arlEDLNos = new ArrayList();
		ArrayList arlRefEDLNos = new ArrayList();
		ArrayList arlpartSubSecCode = new ArrayList();
		ArrayList arlTableColumns = new ArrayList();
		ArrayList arlTableRows = new ArrayList();
		ArrayList arlComponentVO = new ArrayList();
		ArrayList resultSetList = null;
		CallableStatement objCallableStatement = null;
		
		/** Result Sets * */
		ResultSet rsSpecs = null;
		ResultSet objEDLNoResultSet = null;
		ResultSet objRefEDLNoResultSet = null;
		ResultSet objSubSecResultSet = null;
		ResultSet objTbDataResultSet = null;
		ResultSet objStdEqpResultSet = null;
		ResultSet objComponentResultSet = null;
		
		/** Error ourt parameters * */
		int intLSDBErrorID = 0;
		int countCol=0;
		String strOracleCode = null;
		String strErrorMessage = null;
		ClauseVO objClauseVO = null;
		ComponentVO objcomponentVO = null;
		String strLogUser = "";
		try {
			strLogUser = objSubSectionVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			
			LogUtil.logMessage("objConnnection in DAO :" + objConnnection);
			
			LogUtil
			.logMessage("objSubSectionVO.getSecSeqNo() in ViewSpecByModelDAO "
					+ objSubSectionVO.getSecSeqNo());
			
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_SELECT_MDL_SPEC);
			
			if (objSubSectionVO.getSecSeqNo() == 0) {
				objCallableStatement.setNull(1, Types.NULL);
			} else {
				objCallableStatement.setInt(1, objSubSectionVO.getSecSeqNo());
			}
			
			LogUtil
			.logMessage("objSubSectionVO.getModelSeqNo() in ViewSpecByModelDAO "
					+ objSubSectionVO.getModelSeqNo());
			
			if (objSubSectionVO.getModelSeqNo() == 0) {
				objCallableStatement.setNull(2, Types.NULL);
			} else {
				objCallableStatement.setInt(2, objSubSectionVO.getModelSeqNo());
			}
			
			objCallableStatement.setString(3, objSubSectionVO.getUserID());
			
			objCallableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			rsSpecs = (ResultSet) objCallableStatement.getObject(4);
			
			LogUtil
			.logMessage("Inside the fetchSpecByModel method of ViewSpecByModelDAO :resultSet"
					+ rsSpecs);
			
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
				arlComponentVO = new ArrayList();
				arlpartSubSecCode = new ArrayList();
				arlRefEDLNos = new ArrayList();
				arlEDLNos = new ArrayList();
				arlTableRows = new ArrayList();
				resultSetList = new ArrayList();
				objClauseVO.setClauseSeqNo(rsSpecs
						.getInt(DatabaseConstants.LS300_CLA_SEQ_NO));
				objClauseVO.setVersionNo(rsSpecs
						.getInt(DatabaseConstants.LS301_VERSION_NO));
				objClauseVO.setSubSectionSeqNo(rsSpecs
						.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO));
				objClauseVO.setPriceBookNumber(rsSpecs
						.getInt(DatabaseConstants.LS301_PRICE_BOOK_NUMBER));
				String clauseDesc = rsSpecs
				.getString(DatabaseConstants.LS301_CLA_DESC);
				objClauseVO.setClauseDesc(clauseDesc);
				objClauseVO.setEngDataComment(rsSpecs
						.getString(DatabaseConstants.LS301_ENGG_DATA_COMMENTS));
				
				//Added for CR_111 QA Fixes to differentiate the default and deleted clauses
				
				objClauseVO.setClauseDelFlag(rsSpecs.getString(DatabaseConstants.LS301_DELETE_FLAG));
				objClauseVO.setDefaultFlag(rsSpecs.getString(DatabaseConstants.LS301_DEFAULT_FLAG ));
				//Added for CR-134 starts here
				objClauseVO.setUserMarkFlag(rsSpecs.getString(DatabaseConstants.LS301_USR_MARKER));
				objClauseVO.setMarkClaReason(rsSpecs.getString(DatabaseConstants.LS301_USR_MARKED_REASON));
				//Added for CR-134 ends here
				
				
				//Added for CR_111 QA Fixes to differentiate the default and deleted clauses Ends here
				objTbDataResultSet = (ResultSet) rsSpecs
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
				objEDLNoResultSet = (ResultSet) rsSpecs.getObject("EDLNO");
				
				while (objEDLNoResultSet.next()) {
					LogUtil.logMessage("Enters into EDLNo Resultset Loop:");
					arlEDLNos.add(objEDLNoResultSet
							.getString(DatabaseConstants.LS302_EDL_NO));
				}
				objClauseVO.setEdlNO(arlEDLNos);
				
				LogUtil.logMessage("Length of EdlNumber:" + arlEDLNos.size());
				
				objRefEDLNoResultSet = (ResultSet) rsSpecs
				.getObject("refEDLNO");
				
				while (objRefEDLNoResultSet.next()) {
					LogUtil.logMessage("Enters into RefEDLNo Resultset Loop:");
					arlRefEDLNos.add(objRefEDLNoResultSet
							.getString(DatabaseConstants.LS303_REF_EDL_NO));
				}
				
				LogUtil.logMessage("Length of RefEdlNumber:"
						+ arlRefEDLNos.size());
				objClauseVO.setRefEDLNO(arlRefEDLNos);
				
				objSubSecResultSet = (ResultSet) rsSpecs.getObject("PartOF");
				
				while (objSubSecResultSet.next()) {
					LogUtil
					.logMessage("PartCode Of values"
							+ objSubSecResultSet
							.getString(DatabaseConstants.LS304_SUBSEC_NO));
					arlpartSubSecCode.add(objSubSecResultSet
							.getString(DatabaseConstants.LS304_SUBSEC_NO));
				}
				
				LogUtil.logMessage("Length of arlpartSubSecCode:"
						+ arlpartSubSecCode.size());
				objClauseVO.setPartOF(arlpartSubSecCode);
				
				objStdEqpResultSet = (ResultSet) rsSpecs.getObject("STD_EQUIP");
				while (objStdEqpResultSet.next()) {
					LogUtil.logMessage("Enters into std equip ResultSet");
					objClauseVO.setStandardEquipmentDesc(objStdEqpResultSet
							.getString(DatabaseConstants.LS060_STD_EQP_DESC));
					LogUtil.logMessage("StandardEquipmentDesc:"
							+ objClauseVO.getStandardEquipmentDesc());
				}
				
				objComponentResultSet = (ResultSet) rsSpecs
				.getObject("COMPONENTS");
				
				while (objComponentResultSet.next()) {
					objcomponentVO = new ComponentVO();
					LogUtil.logMessage("Enters into Component ResultSet");
					objcomponentVO.setComponentName(objComponentResultSet
							.getString(DatabaseConstants.LS140_COMP_NAME));
					
					//Added for CR-113 @vipul to fetch component group data	
					objcomponentVO.setComponentGroupName(objComponentResultSet.getString(DatabaseConstants.LS130_COMP_GRP_NAME));
					
					arlComponentVO.add(objcomponentVO);
					
					LogUtil.logMessage("Component Name:"
							+ objcomponentVO.getComponentName());
				}
				LogUtil.logMessage("Length of arlComponentVO:"
						+ arlComponentVO.size());
				objClauseVO.setComponentVO(arlComponentVO);
				objClauseVO.setDwONumber(rsSpecs
						.getInt(DatabaseConstants.LS301_DWO_NUMBER));
				objClauseVO.setPartNumber(rsSpecs
						.getInt(DatabaseConstants.LS301_PART_NUMBER));
				objClauseVO.setCustomerName(rsSpecs
						.getString(DatabaseConstants.LS050_CUST_NAME));
				objClauseVO.setModifiedBy(rsSpecs
						.getString(DatabaseConstants.LS301_UPDT_USER_ID));
				objClauseVO.setModifiedDate(rsSpecs
						.getString(DatabaseConstants.LS301_UPDT_DATE));
				arlSpecs.add(objClauseVO);
				
				/** Closing all the sub resultsets * */
				
				resultSetList.add(objEDLNoResultSet);
				resultSetList.add(objRefEDLNoResultSet);
				resultSetList.add(objSubSecResultSet);
				resultSetList.add(objTbDataResultSet);
				resultSetList.add(objStdEqpResultSet);
				resultSetList.add(objComponentResultSet);
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
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSubSectionVO
	 *            The object for searching clause details for a model
	 * @return arrayList The list contains the clause details
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList viewMasterSpecByModel(SubSectionVO objSubSectionVO)
	throws EMDException {
		LogUtil.logMessage("Entering ViewSpecByModelDAO.viewMasterSpecByModel");
		/** Declarations * */
		Connection objConnnection = null;
		ArrayList arlSpecs = new ArrayList();
		ArrayList arlEDLNos = new ArrayList();
		ArrayList arlRefEDLNos = new ArrayList();
		ArrayList arlpartSubSecCode = new ArrayList();
		ArrayList arlTableColumns = new ArrayList();
		ArrayList arlTableRows = new ArrayList();
		ArrayList arlComponentVO = new ArrayList();
		ArrayList arlCompgroupVO = new ArrayList();
		ArrayList resultSetList = null;
		ArrayList arlOrder = null;
		CallableStatement objCallableStatement = null;
		//Added for CR_94
		ArrayList arlParentClauses = new ArrayList();
		ArrayList arlChildClauses = new ArrayList();
		ArrayList arlMainList = new ArrayList();
		//CR_94Ends here
		/** Result Sets * */
		ResultSet rsSpecs = null;
		ResultSet objEDLNoResultSet = null;
		ResultSet objRefEDLNoResultSet = null;
		ResultSet objSubSecResultSet = null;
		ResultSet objTbDataResultSet = null;
		ResultSet objStdEqpResultSet = null;
		ResultSet objComponentResultSet = null;
		ResultSet objOrderInfo = null;
		
		/** Error ourt parameters * */
		int intLSDBErrorID = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		ClauseVO objClauseVO = null;
		ComponentVO objcomponentVO = null;
		CompGroupVO objcompGroupVO = null;
		OrderVO objOrderVO = null;
		String strLogUser = "";
		int countCol=0;
		int countColForOrders=0;//Added for CR_131 to obtain table data column size for tooltips
		try {
			strLogUser = objSubSectionVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_SELECT_MSTR_SPEC_BY_MDL);
			Connection dconn = ((DelegatingConnection) objConnnection).getInnermostDelegate(); //Added for CR-123 & Tomcat
			
			if (objSubSectionVO.getModelSeqNo() == 0) {
				objCallableStatement.setNull(1, Types.NULL);
			} else {
				objCallableStatement.setInt(1, objSubSectionVO.getModelSeqNo());
			}
			
			if (objSubSectionVO.getSecSeqNo() == 0) {
				objCallableStatement.setNull(2, Types.NULL);
			} else {
				objCallableStatement.setInt(2, objSubSectionVO.getSecSeqNo());
			}
			
			if (objSubSectionVO.getSubSecSeqNo() == 0) {
				objCallableStatement.setNull(3, Types.NULL);
			} else {
				objCallableStatement
				.setInt(3, objSubSectionVO.getSubSecSeqNo());
			}
			
			ArrayDescriptor arrdesc = null;
			arrdesc = new ArrayDescriptor(DatabaseConstants.STR_ARRAY,
					dconn);
			ARRAY arrOrderSeqNo = new ARRAY(arrdesc, dconn,
					objSubSectionVO.getOrderkeys());
			
			objCallableStatement.setArray(4, arrOrderSeqNo);
			
			//Modified for CR_81 Parameters Changed by RR68151
			if (objSubSectionVO.getClaCharstcFlag() == null) {
				objCallableStatement.setNull(5, Types.NULL);
			} else {
				objCallableStatement.setString(5, 
						objSubSectionVO.getClaCharstcFlag());	
			}	
			
			objCallableStatement.setString(6, objSubSectionVO.getUserID());
			objCallableStatement.registerOutParameter(7, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(8, Types.INTEGER);
			objCallableStatement.registerOutParameter(9, Types.VARCHAR);
			objCallableStatement.registerOutParameter(10, Types.VARCHAR);
			objCallableStatement.execute();
			
			rsSpecs = (ResultSet) objCallableStatement.getObject(7);
			
			intLSDBErrorID = (int) objCallableStatement.getInt(8);
			strOracleCode = (String) objCallableStatement.getString(9);
			strErrorMessage = (String) objCallableStatement.getString(10);
			
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
				
				objClauseVO.setClauseSeqNo(rsSpecs
						.getInt(DatabaseConstants.LS300_CLA_SEQ_NO));
				/*
				 * Added For LSDB_CR-50 Added on 26-July-o8 by ps57222
				 */
				objClauseVO.setVersionNo(rsSpecs
						.getInt(DatabaseConstants.LS301_VERSION_NO));
				
				objClauseVO.setSubSectionSeqNo(rsSpecs
						.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO));
				objClauseVO.setClauseNum(rsSpecs
						.getString(DatabaseConstants.LS308_CLA_NO));
				
				String clauseDesc = rsSpecs
				.getString(DatabaseConstants.LS301_CLA_DESC);
				objClauseVO.setClauseDesc(clauseDesc);
				objClauseVO.setEngDataComment(rsSpecs
						.getString(DatabaseConstants.LS301_ENGG_DATA_COMMENTS));
				
				//Added for CR_88 on 28-jun-10 by Sd41630
				objClauseVO.setClaCode(rsSpecs
						.getString(DatabaseConstants.LS300_CLA_CODE));
				objClauseVO.setChildFlag(rsSpecs
						.getString(DatabaseConstants.CHILD_FLAG));
				//CR_88 Ends here 
				//Added for CR_94 by RJ85495
				objClauseVO.setNoOfChildClauses(rsSpecs
						.getInt("NO_OF_CHILD_CLA"));
				objClauseVO.setClaHrchyLevel(rsSpecs
						.getInt("LS308_CLA_HRCHY_LEVEL"));
				objClauseVO.setParentClauseSeqNo(rsSpecs
						.getInt("LS300_PARENT_CLA_SEQ_NO"));
				objClauseVO.setNoOfChildForParent(rsSpecs
						.getInt("NO_OF_CHILD_FOR_PRNT"));
				LogUtil.logMessage("chld vlaues"+rsSpecs
						.getInt("NO_OF_CHILD_FOR_PRNT"));
				//CR_94 Ends here
				objTbDataResultSet = (ResultSet) rsSpecs
				.getObject("TABLE_DATA");
				
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
				LogUtil.logMessage("countCol For CR_93" + countCol);
				
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
					
				}
				
				objComponentResultSet = (ResultSet) rsSpecs
				.getObject("components");
				
				if (objComponentResultSet != null) {
					while (objComponentResultSet.next()) {
						objcomponentVO = new ComponentVO();
						objcompGroupVO = new CompGroupVO();
						objcompGroupVO
						.setComponentGroupName(objComponentResultSet
								.getString(DatabaseConstants.LS130_COMP_GRP_NAME));
						
						objcompGroupVO
						.setValidFlag(objComponentResultSet
								.getString(DatabaseConstants.LS130_VALIDATION_FLAG));//whether
						// it
						// is
						// validate
						// comp
						// group
						// or
						// not
						
						objcompGroupVO.setLeadFlag(objComponentResultSet
								.getString(DatabaseConstants.Italics));//whether
						// it is
						// lead
						// comp
						// group
						
						objcomponentVO.setComponentName(objComponentResultSet
								.getString(DatabaseConstants.LS140_COMP_NAME));
						
						if (objComponentResultSet
								.getString(DatabaseConstants.LS204_DEFAULT_FLAG) != null
								&& objComponentResultSet.getString(
										DatabaseConstants.LS204_DEFAULT_FLAG)
										.equals("Y")) {
							objcomponentVO.setDefaultFlag(true);
						} else {
							objcomponentVO.setDefaultFlag(false);
						}
						
						objcompGroupVO.setCompVO(objcomponentVO);
						
						arlCompgroupVO.add(objcompGroupVO);
						
					}
				}
				
				objOrderInfo = (ResultSet) rsSpecs.getObject("ORDR");
				
				arlOrder = new ArrayList();
				ClauseVO objClauseVersionVO = null;
				ResultSet objVerTabData = null;
				countColForOrders=0;
				
				while (objOrderInfo.next()) {
					objClauseVersionVO = new ClauseVO();
					objOrderVO = new OrderVO();
					objOrderVO.setOrderNo(objOrderInfo
							.getString(DatabaseConstants.LS400_ORDR_NO));
					objOrderVO.setStatusDesc(objOrderInfo
							.getString(DatabaseConstants.STATUS));
					objOrderVO.setCustomerName(objOrderInfo
							.getString(DatabaseConstants.LS050_CUST_NAME));
					objOrderVO.setVersionNo(objOrderInfo
							.getInt(DatabaseConstants.LS301_VERSION_NO));
					objOrderVO.setVersionIndicator(objOrderInfo
							.getString(DatabaseConstants.Indicator));
					
					/**Added for CR-74 18-06-09 **/
					objOrderVO.setClauseDelFlag(objOrderInfo.getString(DatabaseConstants.LS406_CLA_DEL_FLAG));
					/**Added for CR-74 18-06-09 **/
					
					String strCla = objOrderInfo.getString("Cla_Desc");
					objOrderVO.setClauseDesc(strCla);
					
					objVerTabData = (ResultSet) objOrderInfo
					.getObject("TABLE_DATA");
					ArrayList arlTableColumnsVer = null;
					ArrayList arlTableRowsVer = new ArrayList();
					while (objVerTabData.next()) {
						arlTableColumnsVer = new ArrayList();
						arlTableColumnsVer
						.add(objVerTabData
								.getString(DatabaseConstants.LS306_TBL_DATA_COL_1));
						arlTableColumnsVer
						.add(objVerTabData
								.getString(DatabaseConstants.LS306_TBL_DATA_COL_2));
						arlTableColumnsVer
						.add(objVerTabData
								.getString(DatabaseConstants.LS306_TBL_DATA_COL_3));
						arlTableColumnsVer
						.add(objVerTabData
								.getString(DatabaseConstants.LS306_TBL_DATA_COL_4));
						arlTableColumnsVer
						.add(objVerTabData
								.getString(DatabaseConstants.LS306_TBL_DATA_COL_5));
						arlTableRowsVer.add(arlTableColumnsVer);
					}
					countColForOrders=ApplicationUtil.getTableDataColumnsCount(arlTableRowsVer);
					objOrderVO.setTableArrayData1(arlTableRowsVer);
					objOrderVO.setTableDataColSize(countColForOrders);
					arlOrder.add(objOrderVO);
					objVerTabData = null;
				}

				objClauseVO.setCompGroupVO(arlCompgroupVO);
				objClauseVO.setOrderVO(arlOrder);
				objClauseVO.setModifiedBy(rsSpecs
						.getString(DatabaseConstants.LS301_UPDT_USER_ID));
				objClauseVO.setModifiedDate(rsSpecs
						.getString(DatabaseConstants.LS301_UPDT_DATE));
				arlSpecs.add(objClauseVO);
				//Added for CR_94
				if("N".equals(objSubSectionVO.getRearrFlag())){
					if(rsSpecs.getInt("LS308_CLA_HRCHY_LEVEL")==1){
						arlParentClauses.add(objClauseVO);
					}else if(rsSpecs.getInt("LS308_CLA_HRCHY_LEVEL")==2){
						arlChildClauses.add(objClauseVO);
					}
				}
				//CR_94 Ends here
				/** Closing all the sub resultsets * */
				
				resultSetList.add(objEDLNoResultSet);
				resultSetList.add(objRefEDLNoResultSet);
				resultSetList.add(objSubSecResultSet);
				resultSetList.add(objTbDataResultSet);
				resultSetList.add(objStdEqpResultSet);
				resultSetList.add(objComponentResultSet);
				resultSetList.add(objOrderInfo);
				DBHelper.closeResultSets(resultSetList, null, null);
			}
			//Added for CR_94
			if("N".equals(objSubSectionVO.getRearrFlag()))
			{
				//Added for getting three arraylists all, parent & child clauses
				arlMainList.add(arlSpecs);
				arlMainList.add(arlParentClauses);
				arlMainList.add(arlChildClauses);
			}
			//CR_94 Ends here
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
		//Added & modified for CR_94
		if("N".equals(objSubSectionVO.getRearrFlag()))
		{
			return arlMainList;
		}
		else 
			return arlSpecs;
		//CR_94 Ends here
	}
	
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSubSectionVO
	 *            The object for searching clause details for a model
	 * @return arrayList The list contains the clause details
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList viewCustomerOptionCatalog(ModelVo objModelVo)
	throws EMDException {
		LogUtil.logMessage("Entering ViewSpecByModelDAO.viewCustomerOptionCatalog");
		/** Declarations * */
		Connection objConnnection = null;
				
		ArrayList arlSecVO = new ArrayList();
		ArrayList arlSubSecVO = new ArrayList();
		ArrayList arlCompgroupVO = new ArrayList();
		ArrayList arlComponentVO = new ArrayList();
		ArrayList arlClauseVO = new ArrayList();
		ArrayList resultSetList = null;
		CallableStatement objCallableStatement = null;
		
		/** Result Sets * */
		ResultSet rsCusOptionCatalog = null;
		ResultSet objSubSecResultSet = null;
		ResultSet objCompGroupResultSet = null;
		ResultSet objComponentResultSet = null;
		ResultSet objClauseResultSet = null;
		ResultSet objTbDataResultSet = null;
				
		/** Error ourt parameters * */
		int intLSDBErrorID = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		SectionVO objSectionVO = null;
		SubSectionVO objSubSectionVO = null;
		CompGroupVO objcompGroupVO = null;
		ComponentVO objcomponentVO = null;
		ClauseVO objClauseVO = null;
		
		
		String strLogUser = "";
		try {
			strLogUser = objModelVo.getUserID();
			objConnnection = DBHelper.prepareConnection();
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_CUST_OPTN_CATLOG);
			
			if (objModelVo.getModelSeqNo() == 0) {
				objCallableStatement.setNull(1, Types.NULL);
			} else {
				objCallableStatement.setInt(1, objModelVo.getModelSeqNo());
			}
			objCallableStatement.setString(2, objModelVo.getUserID());
			objCallableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.execute();
			
			rsCusOptionCatalog = (ResultSet) objCallableStatement.getObject(3);
			
			intLSDBErrorID = (int) objCallableStatement.getInt(4);
			strOracleCode = (String) objCallableStatement.getString(5);
			strErrorMessage = (String) objCallableStatement.getString(6);
			
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
			while (rsCusOptionCatalog!= null && rsCusOptionCatalog.next()) {
				objSectionVO = new SectionVO();
				objSectionVO.setSectionName(rsCusOptionCatalog.getString("SECNAME"));
				//Added for CR_114
				objSectionVO.setSectionComments(rsCusOptionCatalog.getString(DatabaseConstants.LS201_SEC_DESC));
				//Added for CR_114 Ends	
				objSubSecResultSet = (ResultSet)rsCusOptionCatalog.getObject("SUB_SEC");
				resultSetList = new ArrayList();
				//SubSection starts
				arlSubSecVO = new ArrayList();
				while(objSubSecResultSet!=null && objSubSecResultSet.next()){
					objSubSectionVO = new SubSectionVO();
					objSubSectionVO.setSubSecName(objSubSecResultSet.getString("SUBSECNAME"));
					//Added for CR_114
					objSubSectionVO.setSubSecDesc(objSubSecResultSet.getString(DatabaseConstants.LS202_SUBSEC_DESC));
					//Added for CR_114 Ends	
					objCompGroupResultSet = (ResultSet)objSubSecResultSet.getObject("COMP_GRP");
								
					//Component Group starts
					arlCompgroupVO = new ArrayList();
					String strClaNO ="";
					String strCompGroupName="";
					while(objCompGroupResultSet !=null && objCompGroupResultSet.next()){
						objcompGroupVO = new CompGroupVO();
						strClaNO = objCompGroupResultSet.getString(DatabaseConstants.LS308_CLA_NO);
						
						strClaNO = ((strClaNO==null)? "" :strClaNO);
						strCompGroupName = objCompGroupResultSet.getString(DatabaseConstants.LS130_COMP_GRP_NAME);
						strCompGroupName = ((strCompGroupName==null)? "" :strCompGroupName);
						objcompGroupVO.setComponentGroupSeqNo(objCompGroupResultSet.getInt(DatabaseConstants.COMP_GRP_SEQ_NO));
						objcompGroupVO.setComponentGroupName(strClaNO+" "+strCompGroupName);
						
						objcompGroupVO.setValidFlag(objCompGroupResultSet.getString(DatabaseConstants.LS130_VALIDATION_FLAG));
												
						objComponentResultSet = (ResultSet)objCompGroupResultSet.getObject("COMP");
						
						//Component Starts
						arlComponentVO = new ArrayList();
						while(objComponentResultSet!=null && objComponentResultSet.next()){
							objcomponentVO = new ComponentVO();
							objcomponentVO.setComponentName(objComponentResultSet.getString(DatabaseConstants.LS140_COMP_NAME));
							objcomponentVO.setCompDefFlag(objComponentResultSet.getString(DatabaseConstants.LS204_DEFAULT_FLAG));
							objClauseResultSet = (ResultSet)objComponentResultSet.getObject("CLA_DESC");
							//Clause starts	
							arlClauseVO = new ArrayList();
							while(objClauseResultSet!=null && objClauseResultSet.next()){
								
								objClauseVO = new ClauseVO();
								objClauseVO.setClauseDesc(objClauseResultSet.getString(DatabaseConstants.LS301_CLA_DESC));
								objTbDataResultSet = (ResultSet) objClauseResultSet
								.getObject("TABLE_DATA");
								ArrayList arlTableColumnsVer = null;
								ArrayList arlTableRowsVer = new ArrayList();
								while (objTbDataResultSet!=null && objTbDataResultSet.next()) {
									arlTableColumnsVer = new ArrayList();
									arlTableColumnsVer
									.add(objTbDataResultSet
											.getString(DatabaseConstants.LS306_TBL_DATA_COL_1));
									arlTableColumnsVer
									.add(objTbDataResultSet
											.getString(DatabaseConstants.LS306_TBL_DATA_COL_2));
									arlTableColumnsVer
									.add(objTbDataResultSet
											.getString(DatabaseConstants.LS306_TBL_DATA_COL_3));
									arlTableColumnsVer
									.add(objTbDataResultSet
											.getString(DatabaseConstants.LS306_TBL_DATA_COL_4));
									arlTableColumnsVer
									.add(objTbDataResultSet
											.getString(DatabaseConstants.LS306_TBL_DATA_COL_5));
									arlTableRowsVer.add(arlTableColumnsVer);
								}
								objClauseVO.setTableArrayData1(arlTableRowsVer);
								arlClauseVO.add(objClauseVO);
								DBHelper.closeSQLObjects(objTbDataResultSet, null, null);
							}//clause ends
							objcomponentVO.setClauseVOList(arlClauseVO);
							
							arlComponentVO.add(objcomponentVO);
							DBHelper.closeSQLObjects(objClauseResultSet, null, null);
						}//comp ends
					
						objcompGroupVO.setComponent(arlComponentVO);
						arlCompgroupVO.add(objcompGroupVO);
						DBHelper.closeSQLObjects(objComponentResultSet, null, null);
					}//comp group ends
					
					objSubSectionVO.setCompGroup(arlCompgroupVO);
					arlSubSecVO.add(objSubSectionVO);
					DBHelper.closeSQLObjects(objCompGroupResultSet, null, null);
				}//subsec ends
											
				objSectionVO.setSubSec(arlSubSecVO);
				arlSecVO.add(objSectionVO);
				
				/** Closing all the sub resultsets * */
				resultSetList.add(objSubSecResultSet);
				resultSetList.add(objCompGroupResultSet);
				resultSetList.add(objComponentResultSet);
				resultSetList.add(objClauseResultSet);
				resultSetList.add(objTbDataResultSet);
				DBHelper.closeResultSets(resultSetList, null, null);
			}//Sec ends
		} catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException ViewSpecByModelDAO:viewCustomerOptionCatalog");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in ViewSpecByModelDAO:viewCustomerOptionCatalog"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException ViewSpecByModelDAO:viewCustomerOptionCatalog");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in ViewSpecByModelDAO:viewCustomerOptionCatalog"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception ViewSpecByModelDAO:viewCustomerOptionCatalog");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(rsCusOptionCatalog, objCallableStatement,
						objConnnection);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception ViewSpecByModelDAO:viewCustomerOptionCatalog");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return arlSecVO;
	}

/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSubSectionVO
	 *            The object for searching clause details for a model
	 * @return arrayList The list contains the viewCharacterisitic Group Report details 
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList viewCharactersiticGrpRpt(SubSectionVO objSubSectionVO)
	throws EMDException {
		LogUtil.logMessage("Entering ViewCharactersiticGrpRpt");
		/** Declarations * */
		Connection objConnnection = null;
		ArrayList arlViewCharGrpRpt = new ArrayList();
		ArrayList arlEDLNos = new ArrayList();
		ArrayList arlRefEDLNos = new ArrayList();
		ArrayList arlpartSubSecCode = new ArrayList();
		ArrayList arlCompgroupVO = new ArrayList();
		ArrayList arlTableColumns = new ArrayList();
		ArrayList arlTableRows = new ArrayList();
		ArrayList resultSetList = null;
		ArrayList arlOrder = null;
		CallableStatement objCallableStatement = null;
		
		/** Result Sets * */
		ResultSet rsCharGrprpt = null;
		ResultSet objEDLNoResultSet = null;
		ResultSet objRefEDLNoResultSet = null;
		ResultSet objSubSecResultSet = null;
		ResultSet objTbDataResultSet = null;
		ResultSet objStdEqpResultSet = null;
		ResultSet objCompCombntnResultSet = null;
		
		/** Error ourt parameters * */
		int intLSDBErrorID = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		ClauseVO objClauseVO = null;
		ComponentVO objcomponentVO = null;
		CompGroupVO objcompGroupVO = null;
		String strLogUser = "";
		int countCol=0;
		try {
			strLogUser = objSubSectionVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_SELECT_CHAR_RPT_SPEC_BY_MDL);
			
			if (objSubSectionVO.getModelSeqNo() == 0) {
				objCallableStatement.setNull(1, Types.NULL);
			} else {
				objCallableStatement.setInt(1, objSubSectionVO.getModelSeqNo());
			}
			//CR_85 Combntn_Seq_No  has pass value or as null as a inparm Combntn_Seq_No for CR_85
			if (objSubSectionVO.getCombntnSeqNo() == 0) {
				objCallableStatement.setNull(2, Types.NULL);
			} else {
				objCallableStatement.setInt(2, objSubSectionVO.getCombntnSeqNo());
			}
			objCallableStatement.setString(3, objSubSectionVO.getUserID());
			objCallableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.execute();
			
			rsCharGrprpt = (ResultSet) objCallableStatement.getObject(4);
			
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
			
			while (rsCharGrprpt.next()) {
				objClauseVO = new ClauseVO();
				arlCompgroupVO = new ArrayList();
				arlpartSubSecCode = new ArrayList();
				arlRefEDLNos = new ArrayList();
				arlEDLNos = new ArrayList();
				arlTableRows = new ArrayList();
				resultSetList = new ArrayList();
				
				objClauseVO.setClauseSeqNo(rsCharGrprpt
						.getInt(DatabaseConstants.LS300_CLA_SEQ_NO));
				/*
				 * Added For LSDB_CR-50 Added on 26-July-o8 by ps57222
				 */
				objClauseVO.setVersionNo(rsCharGrprpt
						.getInt(DatabaseConstants.LS301_VERSION_NO));
				
				objClauseVO.setSubSectionSeqNo(rsCharGrprpt
						.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO));
				objClauseVO.setClauseNum(rsCharGrprpt
						.getString(DatabaseConstants.LS308_CLA_NO));
				
				String clauseDesc = rsCharGrprpt
				.getString(DatabaseConstants.LS301_CLA_DESC);
				objClauseVO.setClauseDesc(clauseDesc);
				objClauseVO.setEngDataComment(rsCharGrprpt
						.getString(DatabaseConstants.LS301_ENGG_DATA_COMMENTS));
				objClauseVO.setCombntnSeqNo(rsCharGrprpt
						.getInt(DatabaseConstants.LS310_COMBNTN_SEQ_NO));
				if((rsCharGrprpt.getString(DatabaseConstants.LS311_CHARSTC_EDL_NO))==null)
					{
					objClauseVO.setCharEdlNo("");
					}else{
				objClauseVO.setCharEdlNo(rsCharGrprpt.getString(DatabaseConstants.LS311_CHARSTC_EDL_NO));
					}
				if((rsCharGrprpt.getString(DatabaseConstants.LS311_CHARSTC_REF_EDL_NO))==null){
					objClauseVO.setCharRefEDLNo("");
				}else{
				objClauseVO.setCharRefEDLNo(rsCharGrprpt.getString(DatabaseConstants.LS311_CHARSTC_REF_EDL_NO));
				}
				objTbDataResultSet = (ResultSet) rsCharGrprpt
				.getObject("TABLE_DATA");
				
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
				LogUtil.logMessage("TableDataColSize+**************"+countCol);
				
				
				objClauseVO.setTableArrayData1(arlTableRows);
				
				objEDLNoResultSet = (ResultSet) rsCharGrprpt.getObject("EDLNO");
				
				while (objEDLNoResultSet.next()) {
					arlEDLNos.add(objEDLNoResultSet
							.getString(DatabaseConstants.LS302_EDL_NO));
				}
				objClauseVO.setEdlNO(arlEDLNos);
				
				objRefEDLNoResultSet = (ResultSet) rsCharGrprpt
				.getObject("refEDLNO");
				
				while (objRefEDLNoResultSet.next()) {
					
					
					arlRefEDLNos.add(objRefEDLNoResultSet
							.getString(DatabaseConstants.LS303_REF_EDL_NO));
				}
				
				objClauseVO.setRefEDLNO(arlRefEDLNos);
				
				objSubSecResultSet = (ResultSet) rsCharGrprpt.getObject("PartOF");
				
				while (objSubSecResultSet.next()) {
					arlpartSubSecCode.add(objSubSecResultSet
							.getString(DatabaseConstants.LS304_SUBSEC_NO));
				}
				
				objClauseVO.setPartOF(arlpartSubSecCode);
				
				//Added for DWO, Part Number and Price book Number
				objClauseVO.setDwONumber(rsCharGrprpt
						.getInt(DatabaseConstants.LS301_DWO_NUMBER));
				objClauseVO.setPartNumber(rsCharGrprpt
						.getInt(DatabaseConstants.LS301_PART_NUMBER));
				objClauseVO.setPriceBookNumber(rsCharGrprpt
						.getInt(DatabaseConstants.LS301_PRICE_BOOK_NUMBER));
				
				objStdEqpResultSet = (ResultSet) rsCharGrprpt.getObject("STD_EQUIP");
				while (objStdEqpResultSet.next()) {
					
					objClauseVO.setStandardEquipmentDesc(objStdEqpResultSet
							.getString(DatabaseConstants.LS060_STD_EQP_DESC));
					
				}
				
					objCompCombntnResultSet= (ResultSet)rsCharGrprpt.getObject("COMP_COMBNTN");
				
				if(objCompCombntnResultSet !=null){
					while(objCompCombntnResultSet.next()){
						objcomponentVO = new ComponentVO();
						objcompGroupVO = new CompGroupVO();
						LogUtil.logMessage("Inside ResultSet objCompCombntnResultSet.Comp Grp Name : " +
								objCompCombntnResultSet.getString(DatabaseConstants.COMP_GRP_NAME));
						
						objcomponentVO.setComponentGroupSeqNo(objCompCombntnResultSet
								.getInt(DatabaseConstants.COMP_GRP_SEQ_NO));

						objcomponentVO.setComponentGroupName(objCompCombntnResultSet
								.getString(DatabaseConstants.COMP_GRP_NAME));
						
						objcomponentVO.setComponentSeqNo(objCompCombntnResultSet
								.getInt(DatabaseConstants.LS140_COMP_SEQ_NO));
						
						objcomponentVO.setComponentName(objCompCombntnResultSet
								.getString(DatabaseConstants.LS140_COMP_NAME));
						LogUtil.logMessage("Inside ResultSet objCompCombntnResultSet.Comp  Name : " +
								objCompCombntnResultSet
								.getString(DatabaseConstants.LS140_COMP_NAME));
						objcompGroupVO
						.setValidFlag(objCompCombntnResultSet
								.getString(DatabaseConstants.LS130_VALIDATION_FLAG));//whether
						// it is validate comp  group or  not
						
						/*objcompGroupVO.setLeadFlag(objComponentResultSet
								.getString(DatabaseConstants.Italics));*/
						LogUtil.logMessage("Inside ResultSet objCompCombntnResultSet.Validation flag : " +
								objCompCombntnResultSet
								.getString(DatabaseConstants.LS130_VALIDATION_FLAG));
						
                        objcompGroupVO.setCompVO(objcomponentVO);
						
						arlCompgroupVO.add(objcompGroupVO);
						
														
					}
						
												
				}
												
				objClauseVO.setCompGroupVO(arlCompgroupVO);
				objClauseVO.setOrderVO(arlOrder);
				objClauseVO.setModifiedBy(rsCharGrprpt
						.getString(DatabaseConstants.LS301_UPDT_USER_ID));
				objClauseVO.setModifiedDate(rsCharGrprpt
						.getString(DatabaseConstants.LS301_UPDT_DATE));
				
				arlViewCharGrpRpt.add(objClauseVO);
				
				/** Closing all the sub resultsets * */
				
				resultSetList.add(objEDLNoResultSet);
				resultSetList.add(objRefEDLNoResultSet);
				resultSetList.add(objSubSecResultSet);
				resultSetList.add(objTbDataResultSet);
				resultSetList.add(objStdEqpResultSet);
				resultSetList.add(objCompCombntnResultSet);
				DBHelper.closeResultSets(resultSetList, null, null);
			}
		} catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException ViewCharactersiticGrpRpt Error info");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in ViewCharactersiticGrpRpt  Error messsage id"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException ViewCharactersiticGrpRpt:Application");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in ViewCharactersiticGrpRpt Application Exception"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception ViewCharactersiticGrpRpt : Exception");
			
			 //objExp.printStackTrace();
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(rsCharGrprpt, objCallableStatement,
						objConnnection);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception ViewCharactersiticGrpRpt in the finally block");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return arlViewCharGrpRpt;
	}

//Added for CR-118
public static ArrayList editCompGroupInCOC(ModelVo objModelVo) throws EMDException{
	LogUtil.logMessage("Entering editCompGroupInCOC");
	/** Declarations * */
	Connection objConnnection = null;
	ArrayList arlCompGroups = new ArrayList();
	CallableStatement objCallableStatement = null;
	CompGroupVO objCompGroupVO;
	/** Result Sets * */
	
	ResultSet objCompGroupsResultSet = null;
	
	/** Error ourt parameters * */
	int intLSDBErrorID = 0;
	String strOracleCode = null;
	String strErrorMessage = null;
	
	String strLogUser = "";
	
	try {
		strLogUser = objModelVo.getUserID();
		objConnnection = DBHelper.prepareConnection();
		objCallableStatement = objConnnection
		.prepareCall(EMDQueries.SP_SEL_COMPGRPS_FOR_COC);
		
		if (objModelVo.getModelSeqNo() == 0) {
			objCallableStatement.setNull(1, Types.NULL);
		} else {
			objCallableStatement.setInt(1, objModelVo.getModelSeqNo());
		}
		
		objCallableStatement.setString(2, objModelVo.getUserID());
		objCallableStatement.registerOutParameter(3, OracleTypes.CURSOR);
		objCallableStatement.registerOutParameter(4, Types.INTEGER);
		objCallableStatement.registerOutParameter(5, Types.VARCHAR);
		objCallableStatement.registerOutParameter(6, Types.VARCHAR);
		

		objCallableStatement.execute();
		
		objCompGroupsResultSet = (ResultSet) objCallableStatement.getObject(3);
		
		intLSDBErrorID = (int) objCallableStatement.getInt(4);
		strOracleCode = (String) objCallableStatement.getString(5);
		strErrorMessage = (String) objCallableStatement.getString(6);
		
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
		
		while (objCompGroupsResultSet.next()) {
			objCompGroupVO = new CompGroupVO();
			objCompGroupVO.setComponentGroupSeqNo(objCompGroupsResultSet.getInt(DatabaseConstants.LS130_COMP_GRP_SEQ_NO));
			objCompGroupVO.setComponentGroupName(objCompGroupsResultSet.getString(DatabaseConstants.LS130_COMP_GRP_NAME));
			objCompGroupVO.setDispInCOC(objCompGroupsResultSet.getString(DatabaseConstants.LS204_COC_FLAG));
			arlCompGroups.add(objCompGroupVO);
		}
		
		
	} catch (DataAccessException objDataExp) {
		LogUtil
		.logMessage("Enters into DataAccessException editCompGroupInCOC Error info");
		ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
		LogUtil
		.logMessage("ENters into catch block in editCompGroupInCOC  Error messsage id"
				+ objErrorInfo.getMessageID());
		throw new BusinessException(objDataExp, objErrorInfo);
	} catch (ApplicationException objAppExp) {
		LogUtil
		.logMessage("Enters into ApplicationException editCompGroupInCOC:Application");
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
				+ "-" + objErrorInfo.getMessage());
		LogUtil
		.logMessage("ENters into catch block in editCompGroupInCOC Application Exception"
				+ objErrorInfo.getMessage());
		throw new ApplicationException(objAppExp, objErrorInfo);
	}
	
	catch (Exception objExp) {
		LogUtil
		.logMessage("Enters into Exception editCompGroupInCOC : Exception");
		
		 //objExp.printStackTrace();
		ErrorInfo objErrorInfo = new ErrorInfo();
		objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
				+ "-" + objExp.getMessage());
		throw new ApplicationException(objExp, objErrorInfo);
	}
	
	finally {
		try {
			
			DBHelper.closeSQLObjects(objCompGroupsResultSet, objCallableStatement,
					objConnnection);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception editCompGroupInCOC in the finally block");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER
					+ strLogUser + "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
	}
	return arlCompGroups;
	
}

public static int updateCompGroupsInCOC(ModelVo objModelVo) throws EMDException{

		LogUtil
				.logMessage("Enters into ChangeRequest1058DAO:addClauseNonLsdb");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		int intStatusCode = 0;
		String strLogUser = "";
		ArrayDescriptor arrayDescriptor = null;
		ARRAY objArray = null;
		try {
			strLogUser = objModelVo.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
					.prepareCall(EMDQueries.SP_UPDATE_COMPGRPS_FOR_COC);
			Connection dconn = ((DelegatingConnection) objConnection).getInnermostDelegate(); //Added for CR-123 & Tomcat
			
			objCallableStatement.setInt(1,objModelVo.getModelSeqNo());
			
			arrayDescriptor = new ArrayDescriptor(DatabaseConstants.IN_ARRAY, dconn);
			if(objModelVo.getComponentGrpSeqNos() != null )
			{
				objArray = new ARRAY(arrayDescriptor, dconn,objModelVo.getComponentGrpSeqNos());
			}
			else
			{
				LogUtil.logMessage("objModelVo.getComponentGrpSeqNos():null");
				objArray = new ARRAY(arrayDescriptor, dconn, null); 
			}
			objCallableStatement.setArray(2,objArray);
			
			arrayDescriptor = new ArrayDescriptor(DatabaseConstants.STR_ARRAY, dconn);
			if(objModelVo.getDispInCOCFlags() != null )
			{
				objArray = new ARRAY(arrayDescriptor, dconn,objModelVo.getDispInCOCFlags());
			}
			else
			{
				LogUtil.logMessage("objModelVo.getDispInCOCFlags() :null");
				objArray = new ARRAY(arrayDescriptor, dconn, null); 
			}
			objCallableStatement.setArray(3,objArray);
			
			objCallableStatement.setString(4,strLogUser);
			
			objCallableStatement.registerOutParameter(5,Types.INTEGER);
			objCallableStatement.registerOutParameter(6,Types.VARCHAR);
			objCallableStatement.registerOutParameter(7,Types.VARCHAR);			

			intStatusCode = objCallableStatement.executeUpdate();
			LogUtil.logMessage("Update Result:" + intStatusCode);
			if (intStatusCode > 0) {
				intStatusCode = 0;
			}
			LogUtil.logMessage("Status Update" + intStatusCode);
	
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
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil.logMessage("Enters into catch block in DAO:.."
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
	

}