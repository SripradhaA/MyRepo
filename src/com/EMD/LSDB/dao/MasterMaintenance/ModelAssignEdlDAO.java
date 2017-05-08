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

import org.apache.tomcat.dbcp.dbcp2.DelegatingConnection;//Added for Tomcat

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
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.StandardEquipVO;
/***********************************************************************
----------------------------------------------------------------------------------------------------------
*    Date     Version   Modified by    	     Comments                              		          Remarks 
* 05/05/2010    1.0      Sd41630         Upadted the updateCharGrpCombntn method      		   Added for CR_85
*                                        for set the in param  
* --------------------------------------------------------------------------------------------------------
**************************************************************************/
public class ModelAssignEdlDAO {
	
	private ModelAssignEdlDAO() {
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objClauseVO
	 *            The Object used for Fetch Characteristic Group Combinations
	 * @return ArrayList The Arraylist containing Clause versions
	 * @throws EMDException
	 **************************************************************************/
	public static ArrayList fetchCharGrpCombntn(ClauseVO objClauseVO)
	throws EMDException {
		LogUtil
		.logMessage("Entering ModelAssignEdlDAO.fetchCharGrpCombntn");
		
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		
		ResultSet objCombnGrpResultSet = null;
		ResultSet objCompCombntn = null;
		ArrayList arlCharGrpCombntn = new ArrayList();
		ArrayList arlComp = new ArrayList();
		ComponentVO objComponentVo = new ComponentVO();
		
		String strLogUser = "";
		try {
			strLogUser = objClauseVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_SELECT_EDL_MAPP);
			LogUtil
			.logMessage("ModelSeqNo ModelAssignEdlDAO:fetchCharGrpCombntn:"
					+ objClauseVO.getModelSeqNo());
			
			objCallableStatement.setInt(1, objClauseVO.getClauseSeqNo());

			objCallableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			objCallableStatement.setString(3, objClauseVO.getUserID());
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.execute();
			
			objCombnGrpResultSet = (ResultSet) objCallableStatement.getObject(2);
			
			intLSDBErrorID = objCallableStatement.getInt(4);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(5);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(6);
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
			
			while (objCombnGrpResultSet.next()) {

				objClauseVO = new ClauseVO();
				
				arlComp = new ArrayList();
				
				objClauseVO.setCombntnSeqNo(objCombnGrpResultSet
						.getInt(DatabaseConstants.LS310_COMBNTN_SEQ_NO));
				LogUtil.logMessage("Combntn SeqNo :"
						+ objClauseVO.getCombntnSeqNo());
				objClauseVO.setCharEdlNo(objCombnGrpResultSet
						.getString(DatabaseConstants.LS311_CHARSTC_EDL_NO));
				LogUtil.logMessage("Charstc EDL :" + objClauseVO.getCharEdlNo());
				objClauseVO.setCharRefEDLNo(objCombnGrpResultSet
						.getString(DatabaseConstants.LS311_CHARSTC_REF_EDL_NO));
				LogUtil.logMessage("Charcstc RefEDL :" + objClauseVO.getCharRefEDLNo());
				
				objClauseVO.setNoOfLinks(objCombnGrpResultSet
						.getInt(DatabaseConstants.NO_OF_LINKS));
				LogUtil.logMessage("NO_OF_LINKS :" + objClauseVO.getNoOfLinks());
				
				objCompCombntn = (ResultSet) objCombnGrpResultSet.
									getObject(DatabaseConstants.COMP_COMBNTN);
				
				while (objCompCombntn.next()) {
					
					LogUtil.logMessage("Enters into Comp Combntn ResultSet");

					objComponentVo = new ComponentVO();
					
					LogUtil.logMessage("Inside ResultSet fetchCharComGrp.Comp Grp Name : " +
							objCompCombntn.getString(DatabaseConstants.COMP_GRP_NAME));
					
					objComponentVo.setComponentGroupSeqNo(objCompCombntn
							.getInt(DatabaseConstants.COMP_GRP_SEQ_NO));

					objComponentVo.setComponentGroupName(objCompCombntn
							.getString(DatabaseConstants.COMP_GRP_NAME));
					
					objComponentVo.setComponentSeqNo(objCompCombntn
							.getInt(DatabaseConstants.LS140_COMP_SEQ_NO));
					
					objComponentVo.setComponentName(objCompCombntn
							.getString(DatabaseConstants.LS140_COMP_NAME));
					
					
					arlComp.add(objComponentVo);
				}
				
				objClauseVO.setComponentVO(arlComp);
				
				DBHelper.closeSQLObjects(objCompCombntn, null, null);
				
				arlCharGrpCombntn.add(objClauseVO);
	
			}
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into DataAccessException block in ModelAssignEdlDAO:fetchCharGrpCombntn"
					+ objDataExp.getMessage());
			LogUtil
			.logMessage("ENters into DataAccessException block in ModelAssignEdlDAO:fetchCharGrpCombntn"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into ApplicationException block in ModelAssignEdlDAO:fetchCharGrpCombntn"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
			
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelAssignEdlDAO:fetchCharGrpCombntn:");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(objCombnGrpResultSet,
						objCallableStatement, objConnection);
				
			}
			
			catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in ModelAssignEdlDAO:fetchCharGrpCombntn:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
		}
		
		LogUtil
		.logMessage("ArrayList CharGrpCombntn : " + arlCharGrpCombntn);
		return arlCharGrpCombntn;
	}
	
	/***************************************************************************
	 * This Method is used to insert a new Characteristic Group Combination
	 * 
	 * @param objClauseVO
	 * @return boolean
	 * @throws EMDException
	 **************************************************************************/
	public static int insertCharGrpCombntn(ClauseVO objClauseVO)
	throws EMDException {
		LogUtil
		.logMessage("Entering ModelAssignEdlDAO.insertCharGrpCombntn");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		// Error out parameters
		ArrayDescriptor arrdesc = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		try {
			strLogUser = objClauseVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in DAO in insertCharGrpCombntn Method:"
					+ objConnnection);
			
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_INSERT_EDL_MAPP);
			Connection dconn = ((DelegatingConnection) objConnnection).getInnermostDelegate(); //Added for CR-123 & Tomcat

			arrdesc = new ArrayDescriptor(DatabaseConstants.STR_ARRAY,
					dconn);
			
			ARRAY arrCompGrpSeq = new ARRAY(arrdesc, dconn,
									objClauseVO.getComponentGroupSeqNo());
			
			if (objClauseVO.getComponentGroupSeqNo() == null) {
				arrCompGrpSeq = new ARRAY(arrdesc, dconn, null);
				
				objCallableStatement.setArray(1, arrCompGrpSeq);
			} else {
				
				objCallableStatement.setArray(1, arrCompGrpSeq);
			}

			ARRAY arrCompSeq = new ARRAY(arrdesc, dconn,
					objClauseVO.getCompSeqNo());

			if (objClauseVO.getCompSeqNo() == null) {
				arrCompSeq = new ARRAY(arrdesc, dconn, null);
			
				objCallableStatement.setArray(2, arrCompSeq);
			} else {
			
				objCallableStatement.setArray(2, arrCompSeq);
			}

			objCallableStatement.setInt(3, objClauseVO.getClauseSeqNo());

			LogUtil.logMessage("objConnnection in DAO in insertCharGrpCombntn Method:"
					+ objClauseVO.getClauseSeqNo());
			
			if (objClauseVO.getCharEdlNo() == null || "".equalsIgnoreCase(objClauseVO.getCharEdlNo())) {
				
				objCallableStatement.setNull(4, Types.NULL);
				
			} else {
				
				objCallableStatement.setString(4, objClauseVO.getCharEdlNo());
			}

			if (objClauseVO.getCharRefEDLNo() == null || "".equalsIgnoreCase(objClauseVO.getCharRefEDLNo())) {
				
				objCallableStatement.setNull(5, Types.NULL);
				
			} else {
				
				objCallableStatement.setString(5, objClauseVO.getCharRefEDLNo());
			}

			objCallableStatement.setString(6, objClauseVO.getUserID());
			objCallableStatement.registerOutParameter(7, Types.INTEGER);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			objCallableStatement.registerOutParameter(9, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			LogUtil
			.logMessage("Inside the insert method of ModelAssignDAO :intStatus .."
					+ intStatus);
			intLSDBErrorID = (int) objCallableStatement.getInt(7);
			strOracleCode = (String) objCallableStatement.getString(8);
			strErrorMessage = (String) objCallableStatement.getString(9);

			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Enters into Error Loop in Insert Method:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessageID("" + intLSDBErrorID);
				LogUtil.logMessage("Error message in ErrorInfo:"
						+ objErrorInfo.getMessageID());
				throw new DataAccessException(objErrorInfo);
				
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {
				// Un handled exception
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
			.logMessage("ENters into catch block in ModelAssignEdlDAO:insertCharGrpCombntn:"
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in ModelAssignEdlDAO:insertCharGrpCombntn:"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelAssignEdlDAO:insertCharGrpCombntn:");
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
				.logMessage("Enters into Exception block in ModelAssignEdlDAO:insertCharGrpCombntn:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + sqlex.getMessage());
				throw new ApplicationException(sqlex, objErrorInfo);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in ModelAssignEdlDAO:insertCharGrpCombntn:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return intStatus;	
	}	
	
	/***************************************************************************
	 * This Method is used to update the existing EDL/RefEDL value of a
	 * Characteristic Group Combination
	 * 
	 * @param objClauseVO
	 * @return boolean
	 * @throws EMDException
	 **************************************************************************/
	public static int updateCharGrpCombntn(ClauseVO objClauseVO)
	throws EMDException {
		LogUtil
		.logMessage("Entering ModelAssignEdlDAO.updateCharGrpCombntn");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		try {
			strLogUser = objClauseVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in DAO in updateCharGrpCombntn Method:"
					+ objConnnection);
			
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_UPDATE_EDL_MAPP);

			objCallableStatement.setInt(1, objClauseVO.getCombntnSeqNo());
			
			objCallableStatement.setInt(2, objClauseVO.getClauseSeqNo());
			
			if (objClauseVO.getCharEdlNo() == null || "".equalsIgnoreCase(objClauseVO.getCharEdlNo())) {
				LogUtil.logMessage("objClauseVO.getCharEdlNo() in DAO in updateCharGrpCombntn Method:"
						+ objClauseVO.getCharEdlNo());	
				objCallableStatement.setNull(3, Types.NULL);
				
			} else {
				LogUtil.logMessage("objClauseVO.getCharEdlNo() in DAO in updateCharGrpCombntn Method:"
						+ objClauseVO.getCharEdlNo());			
				objCallableStatement.setString(3, objClauseVO.getCharEdlNo());
			}

			if (objClauseVO.getCharRefEDLNo() == null || "".equalsIgnoreCase(objClauseVO.getCharRefEDLNo())) {
				LogUtil.logMessage("objClauseVO.getCharRefEdlNo() in DAO in updateCharGrpCombntn Method:"
						+ objClauseVO.getCharRefEDLNo());			
				objCallableStatement.setNull(4, Types.NULL);
				
			} else {
				LogUtil.logMessage("objClauseVO.getCharRefEdlNo() in DAO in updateCharGrpCombntn Method:"
						+ objClauseVO.getCharRefEDLNo());			
				objCallableStatement.setString(4, objClauseVO.getCharRefEDLNo());
			}
			
			
			//CR_85
			if(objClauseVO.getLinkClaSeqNo()==0)
			{
				objCallableStatement.setNull(5, Types.NULL);
			}else{
				
			objCallableStatement.setInt(5, objClauseVO.getLinkClaSeqNo());
			}
			objCallableStatement.setString(6, objClauseVO.getUserID());
			objCallableStatement.registerOutParameter(7, Types.INTEGER);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			objCallableStatement.registerOutParameter(9, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			LogUtil
			.logMessage("Inside the insert method of ModelAssignDAO :intStatus .."
					+ intStatus);
			intLSDBErrorID = (int) objCallableStatement.getInt(7);
			strOracleCode = (String) objCallableStatement.getString(8);
			strErrorMessage = (String) objCallableStatement.getString(9);

			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Enters into Error Loop in updateCharGrpCombntn Method:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessageID("" + intLSDBErrorID);
				LogUtil.logMessage("Error message in ErrorInfo:"
						+ objErrorInfo.getMessageID());
				throw new DataAccessException(objErrorInfo);
				
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {
				// Un handled exception
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
			.logMessage("ENters into catch block in ModelAssignEdlDAO:updateCharGrpCombntn:"
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in ModelAssignEdlDAO:updateCharGrpCombntn:"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelAssignEdlDAO:updateCharGrpCombntn:");
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
				.logMessage("Enters into Exception block in ModelAssignEdlDAO:updateCharGrpCombntn:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + sqlex.getMessage());
				throw new ApplicationException(sqlex, objErrorInfo);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in ModelAssignEdlDAO:updateCharGrpCombntn:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return intStatus;	
	}	

	/***************************************************************************
	 * This Method is used to delete a Characteristic Group Combination
	 * 
	 * @param objClauseVO
	 * @return boolean
	 * @throws EMDException
	 **************************************************************************/
	public static int deleteCharGrpCombntn(ClauseVO objClauseVO)
	throws EMDException {
		LogUtil
		.logMessage("Entering ModelAssignEdlDAO.deleteCharGrpCombntn");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		try {
			strLogUser = objClauseVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in DAO in deleteCharGrpCombntn Method:"
					+ objConnnection);
			
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_DELETE_EDL_MAPP);

			objCallableStatement.setInt(1, objClauseVO.getCombntnSeqNo());		
			objCallableStatement.setInt(2, objClauseVO.getClauseSeqNo());
			objCallableStatement.setString(3, objClauseVO.getUserID());
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			LogUtil
			.logMessage("Inside the insert method of ModelAssignDAO :intStatus .."
					+ intStatus);
			intLSDBErrorID = (int) objCallableStatement.getInt(4);
			strOracleCode = (String) objCallableStatement.getString(5);
			strErrorMessage = (String) objCallableStatement.getString(6);

			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Enters into Error Loop in deleteCharGrpCombntn Method:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessageID("" + intLSDBErrorID);
				LogUtil.logMessage("Error message in ErrorInfo:"
						+ objErrorInfo.getMessageID());
				throw new DataAccessException(objErrorInfo);
				
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {
				// Un handled exception
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
			.logMessage("ENters into catch block in ModelAssignEdlDAO:deleteCharGrpCombntn:"
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in ModelAssignEdlDAO:deleteCharGrpCombntn:"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelAssignEdlDAO:deleteCharGrpCombntn:");
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
				.logMessage("Enters into Exception block in ModelAssignEdlDAO:deleteCharGrpCombntn:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + sqlex.getMessage());
				throw new ApplicationException(sqlex, objErrorInfo);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in ModelAssignEdlDAO:deleteCharGrpCombntn:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return intStatus;	
	}	
	
}