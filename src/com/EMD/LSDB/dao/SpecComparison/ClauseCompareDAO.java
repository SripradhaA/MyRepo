package com.EMD.LSDB.dao.SpecComparison;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
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
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SubSectionVO;

public class ClauseCompareDAO extends EMDDAO {
	
	private ClauseCompareDAO() {
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objOrderVO
	 *            the object for publishing the Order
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	//Added for CR-135 starts here.
	public static ArrayList fetchOrderVsModelDetails(OrderVO objOrderVo) throws EMDException {
		LogUtil.logMessage("Enters into ClauseCompareDAO:fetchOrderVsModelDetails");
		
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		
		ArrayList arlFrmTableRows = null;
		ArrayList arlTableRows = null;
		ArrayList arlSectionList = null;
		ArrayList arlOrderModelDet= new ArrayList();
		ArrayList arlFinalList = new ArrayList();
		
		ResultSet objResultSetMdlOdrCla = null;
		ResultSet objChangeFromResultSet = null;
		ResultSet objFrmEDLResultSet = null;
		ResultSet objFrmRefEDLResultSet = null;
		ResultSet objFrmPartOfResultSet = null;
		ResultSet objFromStdEquipResultSet = null;
		ResultSet objFrmTbDataResultSet = null;
		ResultSet objFrmCompResultSet = null;
		
		ResultSet objChangeToResultSet = null;
		ResultSet objToEDLResultSet = null;
		ResultSet objToRefEDLResultSet = null;
		ResultSet objToPartOfResultSet = null;
		ResultSet objToStdEquipResultSet = null;
		ResultSet objToTbDataResultSet = null;
		ResultSet objToCompResultSet = null;
		
		String strMessage = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strLogUser = "";
		int intLSDBErrorID = 0;
		int frmCountCol= 0;
		int countCol = 0;
		
		ClauseVO objClauseVo = new ClauseVO();
		
		try{			
			strLogUser = objOrderVo.getUserID();
			objConnection = DBHelper.prepareConnection();
			LogUtil
			.logMessage("Connection in ClauseCompareDAO:fetchOrderVsModelDetails:"
					+ objConnection);
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_COMPARE_MDL_ORDR_CLA);
			objCallableStatement.setInt(1, objOrderVo.getOrderKey());
			LogUtil.logMessage("1. Order Key :"+objOrderVo.getOrderKey());
			if (objOrderVo.getSelectedSectionSeqNo() > 0) {
				objCallableStatement.setInt(2, objOrderVo.getSelectedSectionSeqNo());
			}else{
				objCallableStatement.setNull(2, Types.NULL);
			}
			LogUtil.logMessage("2. SectionSeqNO:"+objOrderVo.getSelectedSectionSeqNo());
			objCallableStatement.setString(3, objOrderVo.getUserID());
			LogUtil.logMessage("3. User ID:"+objOrderVo.getUserID());
			objCallableStatement.setString(4, objOrderVo.getDataLocTypeCode());
			LogUtil.logMessage("4. Data Loc Type:"+objOrderVo.getDataLocTypeCode());
			objCallableStatement.registerOutParameter(5, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			objCallableStatement.execute();
			
			objResultSetMdlOdrCla = (ResultSet) objCallableStatement.getObject(5);
			LogUtil
			.logMessage("ResultSet Value in ClauseCompareDAO:fetchOrderVsModelDetails:"
					+ objResultSetMdlOdrCla);
			intLSDBErrorID = objCallableStatement.getInt(6);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(7);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(8);
			
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
			
			while (objResultSetMdlOdrCla.next()) {
				arlFinalList = new ArrayList();
				arlSectionList = new ArrayList();
				String	strEDLNo = "";
				String	strRefEDLNo = "";
				String	strPartOf = "";
				
				LogUtil.logMessage("Inside REsult set while");
				objClauseVo = new ClauseVO();		
				SubSectionVO objSubSecVo = new SubSectionVO();
				
				objSubSecVo.setSecName(objResultSetMdlOdrCla.getString(DatabaseConstants.LS201_SEC_NAME));
				objSubSecVo.setSubSecName(objResultSetMdlOdrCla.getString(DatabaseConstants.SUBSECNAME));
				objSubSecVo.setSecSeqNo(objResultSetMdlOdrCla.getInt(DatabaseConstants.LS201_SEC_SEQ_NO));
				objSubSecVo.setSubSecSeqNo(objResultSetMdlOdrCla.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO));
				arlSectionList.add(objSubSecVo);
								
				objClauseVo.setSectionName(objResultSetMdlOdrCla.getString(DatabaseConstants.LS201_SEC_NAME));
				LogUtil.logMessage("Sec NAme: "+objClauseVo.getSectionName());
				objClauseVo.setSubSectionName(objResultSetMdlOdrCla.getString(DatabaseConstants.SUBSECNAME));
				LogUtil.logMessage("SubSec NAme: "+objClauseVo.getSubSectionName());
				objClauseVo.setReason(objResultSetMdlOdrCla.getString(DatabaseConstants.LS406_CLA_REASON));
				LogUtil.logMessage("Reason: "+objClauseVo.getReason());
				
				objChangeFromResultSet = (ResultSet) objResultSetMdlOdrCla.getObject("CHANGE_FROM");
				LogUtil.logMessage("Before Change From resultset set while");
				while(objChangeFromResultSet.next()){
					LogUtil.logMessage("Inside Change From resultset");
					objClauseVo.setChangeFromClaNo(objChangeFromResultSet.getString(DatabaseConstants.LS308_CLA_NO));
					LogUtil.logMessage("Cla NO:" +objClauseVo.getChangeFromClaNo());
					objClauseVo.setChangeFromClaVerNo(objChangeFromResultSet.getInt(DatabaseConstants.LS301_VERSION_NO));
					LogUtil.logMessage("Version NO:" +objClauseVo.getChangeFromClaVerNo());
					objClauseVo.setChangeFromClaDesc(objChangeFromResultSet.getString(DatabaseConstants.LS301_CLA_DESC));
					LogUtil.logMessage("Desc:" +objClauseVo.getChangeFromClaDesc());
					objClauseVo.setChangeFromPriceBookNo(objChangeFromResultSet.getString(DatabaseConstants.LS301_PRICE_BOOK_NUMBER));
					LogUtil.logMessage("PBNo:" +objClauseVo.getChangeFromPriceBookNo());
					objClauseVo.setChangeFromEnggComments(objChangeFromResultSet.getString(DatabaseConstants.LS301_ENGG_DATA_COMMENTS));
					LogUtil.logMessage("Engg Comments:" +objClauseVo.getChangeFromEnggComments());
					
					
					objFrmCompResultSet = (ResultSet) objChangeFromResultSet.getObject("components");
					while(objFrmCompResultSet.next()){
						LogUtil.logMessage("Enters into Frm Components Resultset Loop:");
						objClauseVo.setChangeFromCompName(objFrmCompResultSet.getString(DatabaseConstants.LS140_COMP_NAME));
						objClauseVo.setChangeFromCompGrpName(objFrmCompResultSet.getString(DatabaseConstants.LS130_COMP_GRP_NAME));
					}
					DBHelper.closeSQLObjects(objFrmCompResultSet, null, null);
										
					objFrmTbDataResultSet = (ResultSet) objChangeFromResultSet.getObject("TABLE_DATA");
					ArrayList arlFrmTableColumns = null;
					arlFrmTableRows = new ArrayList();
					while (objFrmTbDataResultSet.next()) {
						LogUtil.logMessage("Enters into Frm TableData Resultset Loop:");
						arlFrmTableColumns = new ArrayList();
						arlFrmTableColumns.add(objFrmTbDataResultSet.getString(DatabaseConstants.LS306_TBL_DATA_COL_1));
						arlFrmTableColumns.add(objFrmTbDataResultSet.getString(DatabaseConstants.LS306_TBL_DATA_COL_2));
						arlFrmTableColumns.add(objFrmTbDataResultSet.getString(DatabaseConstants.LS306_TBL_DATA_COL_3));
						arlFrmTableColumns.add(objFrmTbDataResultSet.getString(DatabaseConstants.LS306_TBL_DATA_COL_4));
						arlFrmTableColumns.add(objFrmTbDataResultSet.getString(DatabaseConstants.LS306_TBL_DATA_COL_5));
						arlFrmTableRows.add(arlFrmTableColumns);
					}
					frmCountCol=ApplicationUtil.getTableDataColumnsCount(arlFrmTableRows);
					objClauseVo.setFrmTableDataColSize(frmCountCol);
					objClauseVo.setFrmTableArrayData1(arlFrmTableRows);
					DBHelper.closeSQLObjects(objFrmTbDataResultSet, null, null);
					
					objFrmEDLResultSet = (ResultSet) objChangeFromResultSet.getObject("EDLNO");
					while(objFrmEDLResultSet.next()){
						LogUtil.logMessage("Inside EdlNo loop");
						strEDLNo += "EDL "+ objFrmEDLResultSet.getString(DatabaseConstants.LS302_EDL_NO)+"\n";
					}
					if (!strEDLNo.equalsIgnoreCase(""))
						objClauseVo.setChangeFromEdlNo(strEDLNo);
					strEDLNo = "";
					DBHelper.closeSQLObjects(objFrmEDLResultSet, null, null);
					
					objFrmRefEDLResultSet = (ResultSet) objChangeFromResultSet.getObject("refEDLNO");
					while(objFrmRefEDLResultSet.next()){
						LogUtil.logMessage("Inside RefEdlNo loop");
						strRefEDLNo += "(ref EDL "+ objFrmRefEDLResultSet.getString(DatabaseConstants.LS303_REF_EDL_NO)+")\n";
					}
					if (!strRefEDLNo.equalsIgnoreCase(""))
						objClauseVo.setChangeFromRefEdlNo(strRefEDLNo);
					strRefEDLNo = "";
					DBHelper.closeSQLObjects(objFrmRefEDLResultSet, null, null);
					
					objFrmPartOfResultSet =(ResultSet) objChangeFromResultSet.getObject("PartOF");
					while(objFrmPartOfResultSet.next()){
						LogUtil.logMessage("Inside PartOf");
						strPartOf += "Part of "+ objFrmPartOfResultSet.getString(DatabaseConstants.LS304_SUBSEC_NO)+"\n";
						}
					if (!strPartOf.equalsIgnoreCase(""))
						objClauseVo.setChangeFromPartOfNo(strPartOf);
					strPartOf = "";
					DBHelper.closeSQLObjects(objFrmPartOfResultSet, null, null);
					
					objFromStdEquipResultSet =(ResultSet) objChangeFromResultSet.getObject("STD_EQUIP");
					while(objFromStdEquipResultSet.next()){
					LogUtil.logMessage("Inside Equip");
						objClauseVo.setChangeFromStdEqp(objFromStdEquipResultSet.getString(DatabaseConstants.LS060_STD_EQP_DESC));
					}
					DBHelper.closeSQLObjects(objFromStdEquipResultSet, null, null);
					
					objClauseVo.setChangeFromDwoNo(objChangeFromResultSet.getString(DatabaseConstants.LS301_DWO_NUMBER));
					objClauseVo.setChangeFromPartNo(objChangeFromResultSet.getString(DatabaseConstants.LS301_PART_NUMBER));
					
				}
				DBHelper.closeSQLObjects(objChangeFromResultSet, null, null);
				
				
				objChangeToResultSet = (ResultSet) objResultSetMdlOdrCla.getObject("CHANGE_TO");
				LogUtil.logMessage("Before Change To resultset set while");
				while(objChangeToResultSet.next()){
					LogUtil.logMessage("Inside Change To resultset");
					objClauseVo.setClauseNum(objChangeToResultSet.getString(DatabaseConstants.LS406_CLA_NUM));
					LogUtil.logMessage("Cla NO:" +objClauseVo.getClauseNum());
					objClauseVo.setVersionNo(objChangeToResultSet.getInt(DatabaseConstants.LS301_VERSION_NO));
					LogUtil.logMessage("Ver NO:" +objClauseVo.getVersionNo());
					objClauseVo.setClauseDesc(objChangeToResultSet.getString(DatabaseConstants.LS301_CLA_DESC));
					LogUtil.logMessage("Desc:" +objClauseVo.getClauseDesc());
					objClauseVo.setDwoNumber(objChangeToResultSet.getString(DatabaseConstants.LS301_DWO_NUMBER));
					LogUtil.logMessage("Dwo NO:" +objClauseVo.getDwONumber());
					objClauseVo.setPartNo(objChangeToResultSet.getString(DatabaseConstants.LS301_PART_NUMBER));
					LogUtil.logMessage("part NO:" +objClauseVo.getPartNumber());
					objClauseVo.setPriceBookNo(objChangeToResultSet.getString(DatabaseConstants.LS301_PRICE_BOOK_NUMBER));
					LogUtil.logMessage("PBN:" +objClauseVo.getPriceBookNumber());
					objClauseVo.setEngDataComment(objChangeToResultSet.getString(DatabaseConstants.LS301_ENGG_DATA_COMMENTS));
					LogUtil.logMessage("Cmnts:" +objClauseVo.getEngDataComment());
					
					
					objToCompResultSet = (ResultSet) objChangeToResultSet.getObject("components");
					while(objToCompResultSet.next()){
						LogUtil.logMessage("Enters into to Components Resultset Loop:");
						objClauseVo.setChangeToCompName(objToCompResultSet.getString(DatabaseConstants.LS140_COMP_NAME));
						objClauseVo.setChangeToCompGrpName(objToCompResultSet.getString(DatabaseConstants.LS130_COMP_GRP_NAME));
					}
					DBHelper.closeSQLObjects(objToCompResultSet, null, null);
					
					objToTbDataResultSet = (ResultSet) objChangeToResultSet.getObject("TABLE_DATA");
					ArrayList arlToTableColumns = null;
					arlTableRows = new ArrayList();
					while (objToTbDataResultSet.next()) {
						LogUtil.logMessage("Enters into to TableData Resultset Loop:");
						arlToTableColumns = new ArrayList();
						arlToTableColumns.add(objToTbDataResultSet.getString(DatabaseConstants.LS306_TBL_DATA_COL_1));
						arlToTableColumns.add(objToTbDataResultSet.getString(DatabaseConstants.LS306_TBL_DATA_COL_2));
						arlToTableColumns.add(objToTbDataResultSet.getString(DatabaseConstants.LS306_TBL_DATA_COL_3));
						arlToTableColumns.add(objToTbDataResultSet.getString(DatabaseConstants.LS306_TBL_DATA_COL_4));
						arlToTableColumns.add(objToTbDataResultSet.getString(DatabaseConstants.LS306_TBL_DATA_COL_5));
						arlTableRows.add(arlToTableColumns);
					}
					countCol=ApplicationUtil.getTableDataColumnsCount(arlTableRows);
					objClauseVo.setTableDataColSize(countCol);
					objClauseVo.setTableArrayData1(arlTableRows);
					DBHelper.closeSQLObjects(objFrmTbDataResultSet, null, null);
					
					
					objToEDLResultSet = (ResultSet) objChangeToResultSet.getObject("EDLNO");
					LogUtil.logMessage("before EdlNo loop");
					while(objToEDLResultSet.next()){
						LogUtil.logMessage("Inside EdlNo loop");
						strEDLNo += "EDL "+ objToEDLResultSet.getString(DatabaseConstants.LS302_EDL_NO)+"\n";
					}
					if (!strEDLNo.equalsIgnoreCase(""))
						objClauseVo.setEdlNum(strEDLNo);
					strEDLNo = "";
					DBHelper.closeSQLObjects(objToEDLResultSet, null, null);
					
					objToRefEDLResultSet = (ResultSet) objChangeToResultSet.getObject("refEDLNO");
					LogUtil.logMessage("before RefEdlNo loop");
					while(objToRefEDLResultSet.next()){
						LogUtil.logMessage("Inside RefEdlNo loop");
						strRefEDLNo += "(ref EDL "+ objToRefEDLResultSet.getString(DatabaseConstants.LS303_REF_EDL_NO)+")\n";
					}
					if (!strRefEDLNo.equalsIgnoreCase(""))
						objClauseVo.setRefEdlNum(strRefEDLNo);
					strRefEDLNo = "";
					DBHelper.closeSQLObjects(objToRefEDLResultSet, null, null);
					
					objToPartOfResultSet =(ResultSet) objChangeToResultSet.getObject("PartOF");
					LogUtil.logMessage("before PartOf");
					while(objToPartOfResultSet.next()){
						LogUtil.logMessage("Inside PartOf");
						strPartOf += "Part of "+ objToPartOfResultSet.getString(DatabaseConstants.LS407_PART_OF_CLA_NO)+"\n";
						}
					if (!strPartOf.equalsIgnoreCase(""))
						objClauseVo.setPartOfNo(strPartOf);
					strPartOf = "";
					DBHelper.closeSQLObjects(objToPartOfResultSet, null, null);
					
					objToStdEquipResultSet =(ResultSet) objChangeToResultSet.getObject("STD_EQUIP");
					LogUtil.logMessage("before Equip");
					while(objToStdEquipResultSet.next()){
					LogUtil.logMessage("Inside Equip");
						objClauseVo.setStdEqpDesc(objToStdEquipResultSet.getString(DatabaseConstants.LS060_STD_EQP_DESC));
					}
					DBHelper.closeSQLObjects(objToStdEquipResultSet, null, null);
				}
				DBHelper.closeSQLObjects(objChangeToResultSet, null, null);
				
				arlFinalList.add(objClauseVo);
				
				objClauseVo.setSectionDet(arlSectionList);
				objClauseVo.setClauseDet(arlFinalList);
				arlOrderModelDet.add(objClauseVo);
			}
		}
	catch (DataAccessException objDataExp) {
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
			
			LogUtil.logMessage("Closing connection...");
			
			DBHelper.closeSQLObjects(objResultSetMdlOdrCla,
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
	LogUtil
	.logMessage("Arraylist Value in ClauseCompareDAO:fetchOrderVsModelDetails:"
			+ arlOrderModelDet.size());
		
		return arlOrderModelDet;
	}
//	Added for CR-135 ends here.	
	
	
}
