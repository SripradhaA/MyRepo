package com.EMD.LSDB.dao.History;

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
import com.EMD.LSDB.dao.common.DBHelper;
import com.EMD.LSDB.dao.common.EMDDAO;
import com.EMD.LSDB.dao.common.EMDQueries;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SubSectionVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business methods for the History Edl Pop up
 ******************************************************************************/
public class HistoryEdlPopUpDAO extends EMDDAO {
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objClauseVO
	 *            the object for fetching details of Component Comparision Edl Pop up
	 *            The Method fetchEdlNo is used to display the EDL number used for the selected order
	 *            It display the EDL number with Clause Number and the component tied to this clause.
	 * @return ArrayList containing the details of Component Comparision Edl Pop up
	 * @throws EMDException
	 **************************************************************************/
	
	
	  /*Added for EDL comparision as per CR 75 by cm68219*/
	
	public static ArrayList fetchEdlNo(ClauseVO objClauseVO)
	throws EMDException {
		LogUtil.logMessage("Enter into HistoryEdlPopUpDAO:fetchEdlNo");
		
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		ResultSet clauseResultSet = null;
		ResultSet edlResultSet = null;
		//Added For CR_105 to align EDL Report
		ResultSet subSecResultSet = null;
		String strLogUser = "";
		int intOrderKey = 0;
		
		ArrayList arlSecList= new ArrayList();
		try {
			strLogUser = objClauseVO.getUserID();
			intOrderKey = objClauseVO.getOrderKey();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_SEL_EDL_NO);
			
			objCallableStatement.setInt(1, objClauseVO.getOrderKey());
			objCallableStatement.setString(2, objClauseVO.getUserID());
			if(objClauseVO.getSectionSeqNo() > 0){
				objCallableStatement.setInt(3,objClauseVO.getSectionSeqNo());
			}else{
				objCallableStatement.setNull(3, Types.NULL);
			}
			//CR_91
			if(objClauseVO.getDataLocationType()==DatabaseConstants.DATALOCATION){
				objCallableStatement.setString(4, objClauseVO.getDataLocationType());
			}else{
			objCallableStatement.setString(4, DatabaseConstants.DATALOCATION_SNAP_SHOT);
			}
			
			objCallableStatement.registerOutParameter(5, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			clauseResultSet = (ResultSet) objCallableStatement.getObject(5);
			
			
			while (clauseResultSet.next()) {
				SectionVO objSectionVO=new SectionVO();
				objSectionVO.setSectionSeqNo(clauseResultSet
						.getInt(DatabaseConstants.LS201_SEC_SEQ_NO));
				objSectionVO.setSectionName(clauseResultSet
						.getString(DatabaseConstants.LS201_SEC_NAME));
				//Added Code for EDL Report Changes for CR_105 by RR68151
				objSectionVO.setSectionCode(clauseResultSet
						.getString(DatabaseConstants.LS201_SEC_CODE));
				subSecResultSet = (ResultSet) clauseResultSet.getObject("SUBSECTION");
				ArrayList arlSubSectionList = new ArrayList();
				ArrayList arlClauseList = new ArrayList();
				
				while (subSecResultSet.next()) {
					SubSectionVO objSubSectionVO=new SubSectionVO();
					
					edlResultSet = (ResultSet) subSecResultSet.getObject("EDL_NO");
					arlClauseList = new ArrayList();
					while (edlResultSet.next()) {
						objClauseVO = new ClauseVO();
						objClauseVO.setClauseNum(edlResultSet.getString(DatabaseConstants.LS414_CLA_NO));
						String strCompName = "";
						objClauseVO.setSubSectionSeqNo(edlResultSet.getInt("LS202_SUBSEC_SEQ_NO"));
						objClauseVO.setClauseSeqNo(edlResultSet.getInt(DatabaseConstants.LS300_CLA_SEQ_NO));
						objClauseVO.setVersionNo(edlResultSet.getInt(DatabaseConstants.LS301_VERSION_NO));
						objClauseVO.setEdlNum(edlResultSet.getString(DatabaseConstants.LS302_EDL_NO));
						//Added Code for EDL Report Changes for CR_105 by RR68151
						objClauseVO.setSectionName(clauseResultSet.getString(DatabaseConstants.LS201_SEC_NAME));
						objClauseVO.setClauseDesc(edlResultSet.getString(DatabaseConstants.CLA_DESC));
						//Added for Ref Edl column CR_106 by RR68151
						objClauseVO.setRefEdlNum(edlResultSet.getString(DatabaseConstants.LS303_REF_EDL_NO));
						ResultSet objCompResultSet = (ResultSet) edlResultSet.getObject("COMPONENTS");
						while (objCompResultSet.next()) {
							if (strCompName == "") {
								strCompName = objCompResultSet.getString(DatabaseConstants.LS140_COMP_NAME);
							} else {
								strCompName = strCompName
								+ " "
								+ ";"
								+ " "
								+ objCompResultSet
								.getString(DatabaseConstants.LS140_COMP_NAME);
							}
							
						}
						objClauseVO.setComponentName(strCompName);
						
										
						arlClauseList.add(objClauseVO);
						
						DBHelper.closeSQLObjects(objCompResultSet, null, null);
						
					}
						objSubSectionVO.setSubSecName(subSecResultSet.getString(DatabaseConstants.SUB_SEC_NAME));
						objSubSectionVO.setSecName(clauseResultSet.getString(DatabaseConstants.LS201_SEC_NAME));
						objSubSectionVO.setSubSecCode(subSecResultSet.getString(DatabaseConstants.LS202_SUBSEC_CODE));
						objSubSectionVO.setClauseGroup(arlClauseList);
						arlSubSectionList.add(objSubSectionVO);
				}
					LogUtil.logMessage("Size of ClauseList" + arlClauseList.size());
					//objSectionVO.setClauseVO(arlClauseList);
					objSectionVO.setSubSec(arlSubSectionList);
					arlSecList.add(objSectionVO);
					DBHelper.closeSQLObjects(edlResultSet, null, null);
				}
			
			intLSDBErrorID = (int) objCallableStatement.getInt(6);
			strOracleCode = (String) objCallableStatement.getString(7);
			strErrorMessage = (String) objCallableStatement.getString(8);
			
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
			
			/**
			 * Here SP_DELETE_TEMP_TABLE procedure is called to clear the Temp Table data's 
			 * used for the selected order.All the Transactions are happened in one Time.
			 * 
			 */
			
			LogUtil.logMessage("OrderKey befor Delete:" + intOrderKey);
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_DELETE_TEMP_TABLE);
			objCallableStatement.setInt(1, intOrderKey);
			objCallableStatement.setString(2,
					DatabaseConstants.DATALOCATION_SNAP_SHOT);
			objCallableStatement.setString(3, objClauseVO.getUserID());
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			int intStatus = objCallableStatement.executeUpdate();
			LogUtil.logMessage("Delete Status:" + intStatus);
			
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
			
		} catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException HistoryEdlPopUpDAO:fetchEdlNo");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in HistoryEdlPopUpDAO:fetchEdlNo"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException HistoryEdlPopUpDAO:fetchEdlNo");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in HistoryEdlPopUpDAO:fetchEdlNo"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception HistoryEdlPopUpDAO:fetchEdlNo");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(clauseResultSet, objCallableStatement,
						objConnection);
			}
			
			catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception HistoryEdlPopUpDAO:fetchEdlNo");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		LogUtil.logMessage("Size of SectionList:"+arlSecList.size());
		return arlSecList;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objClauseVO
	 *            the object for fetching details of Component Comparision Edl Pop up
	 *            The Method fetchEDLNumberforCSV is used to display the EDL number used for the selected order
	 *            It display the EDL number with Clause Number and the component tied to this clause.
	 * @return ArrayList containing the details of Component Comparision Edl Pop up
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList fetchEDLNumberforCSV(ClauseVO objClauseVO)
	throws EMDException {
		LogUtil.logMessage("Enter into HistoryEdlPopUpDAO:fetchEDLNumberforCSV");
		
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		ResultSet mainResultSet = null;
		ResultSet clauseResultSet = null;
		ResultSet edlResultSet = null;
		
		String strLogUser = "";
		int intOrderKey = 0;
		ArrayList arlClauseList = new ArrayList();
		ArrayList arlEDLNO = new ArrayList();
		ArrayList arlEDLSAPList = new ArrayList();
		try {
			strLogUser = objClauseVO.getUserID();
			intOrderKey = objClauseVO.getOrderKey();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_SELECT_ORDR_CLA_WITH_EDL);
			LogUtil.logMessage("objClauseVO.getOrderKey():"+objClauseVO.getOrderKey());
			LogUtil.logMessage("objClauseVO.getDataLocationType():"+objClauseVO.getDataLocationType());
			LogUtil.logMessage("objClauseVO.getUserID():"+objClauseVO.getUserID());
			
			objCallableStatement.setInt(1, objClauseVO.getOrderKey());
			
			if(objClauseVO.getDataLocationType()==DatabaseConstants.DATALOCATION){
				objCallableStatement.setString(2, objClauseVO.getDataLocationType());
			}else{
				objCallableStatement.setString(2, DatabaseConstants.DATALOCATION_SNAP_SHOT);
			}
			
			objCallableStatement.setString(3, objClauseVO.getUserID());
			
			objCallableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			mainResultSet = (ResultSet) objCallableStatement.getObject(4);
			
			intLSDBErrorID = (int) objCallableStatement.getInt(5);
			strOracleCode = (String) objCallableStatement.getString(6);
			strErrorMessage = (String) objCallableStatement.getString(7);
			
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
			
			
			while (mainResultSet.next()) {
				LogUtil.logMessage("While 1:");
				SectionVO objSectionVO=new SectionVO();
				objSectionVO.setSectionSeqNo(mainResultSet
						.getInt(DatabaseConstants.LS201_SEC_SEQ_NO));
				objSectionVO.setSectionName(mainResultSet
						.getString(DatabaseConstants.LS201_SEC_NAME));
				clauseResultSet = (ResultSet) mainResultSet.getObject("CLAUSES");
				LogUtil.logMessage("objSectionVO.getSectionSeqNo();" + objSectionVO.getSectionSeqNo());
				LogUtil.logMessage("clauseResultSet" + clauseResultSet);
				
				while (clauseResultSet.next()) {
					
					LogUtil.logMessage("While 2:");
					objClauseVO = new ClauseVO();
					//ClauseSeqNo for Future use
					objClauseVO.setClauseSeqNo(clauseResultSet.getInt(DatabaseConstants.LS300_CLA_SEQ_NO));
					
					objClauseVO.setClauseNum(clauseResultSet.getString(DatabaseConstants.LS406_CLA_NUM));
					objClauseVO.setClauseDesc(clauseResultSet.getString(DatabaseConstants.CLA_DESC));
					
					String strCompName = "";
					ResultSet objCompResultSet = (ResultSet) clauseResultSet.getObject("COMPONENTS");
					while (objCompResultSet.next()) {
						LogUtil.logMessage("While 3:");
						if (strCompName == "") {
							strCompName = objCompResultSet.getString(DatabaseConstants.LS140_COMP_NAME);
						} else {
							strCompName = strCompName
							+ " "
							+ ";"
							+ " "
							+ objCompResultSet
							.getString(DatabaseConstants.LS140_COMP_NAME);
						}
						
					}
					objClauseVO.setComponentName(strCompName);
					DBHelper.closeSQLObjects(objCompResultSet, null, null);
					arlEDLNO = new ArrayList();
					edlResultSet = (ResultSet) clauseResultSet.getObject("EDLNO");
					while (edlResultSet.next()) {
						LogUtil.logMessage("While 4:");
						arlEDLNO.add(edlResultSet.getString(DatabaseConstants.LS302_EDL_NO));
					}
					LogUtil.logMessage("arlEDLNO.size():"+arlEDLNO.size());
					if(arlEDLNO.size() == 1){
						arlEDLNO.add("");
						arlEDLNO.add("");
					}else if(arlEDLNO.size() == 2){
						arlEDLNO.add("");
					}
					
					objClauseVO.setEdlNO(arlEDLNO);
					arlClauseList.add(objClauseVO);
					DBHelper.closeSQLObjects(edlResultSet, null, null);
				}
				LogUtil.logMessage("Size of ClauseList" + arlClauseList.size());
				LogUtil.logMessage("End while:");
			}
			LogUtil.logMessage("After while:");
			LogUtil.logMessage("Size of ClauseList" + arlClauseList.size());
			DBHelper.closeSQLObjects(clauseResultSet, objCallableStatement,
					objConnection);
			LogUtil.logMessage("Final step:");
			
		} catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException HistoryEdlPopUpDAO:fetchEDLNumberforCSV");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in HistoryEdlPopUpDAO:fetchEDLNumberforCSV"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException HistoryEdlPopUpDAO:fetchEDLNumberforCSV");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in HistoryEdlPopUpDAO:fetchEDLNumberforCSV"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception HistoryEdlPopUpDAO:fetchEDLNumberforCSV");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(mainResultSet, objCallableStatement,
						objConnection);
			}
			
			catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception HistoryEdlPopUpDAO:fetchEDLNumberforCSV");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		LogUtil.logMessage("Size of arlClauseList:"+arlClauseList.size());
		return arlClauseList;
	}
	
}