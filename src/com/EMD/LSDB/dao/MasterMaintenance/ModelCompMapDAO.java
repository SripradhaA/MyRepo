package com.EMD.LSDB.dao.MasterMaintenance;

import java.awt.image.DataBuffer;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.EMD.LSDB.dao.common.EMDDAO;
import com.EMD.LSDB.dao.common.EMDQueries;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.CompGroupVO;
import com.EMD.LSDB.vo.common.ComponentVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This Action class has the business methods for the Component
 *          Mappings
 ******************************************************************************/
public class ModelCompMapDAO extends EMDDAO {
	
	/***************************************************************************
	 * This Method is used to fetch the Component Mapping Details
	 * 
	 * @param objComponentVo
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/
	public static ArrayList fetchCompMap(ComponentVO objComponentVo)
	throws EMDException {
		LogUtil.logMessage("Inside ModelCompMapDAO.fetchCompMap");
		Connection objConnnection = null;
		ArrayList arlComponentList = new ArrayList();
		ArrayList arlCompList = new ArrayList();
		CallableStatement objCallableStatement = null;
		// Error out parameters
		ResultSet objCompResultSet = null;
		int intLSDBErrorID = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strLogUser = "";
		String strValidFlag = null;
		String strDisplayInCOCFlag ="N";
		try {
			strLogUser = objComponentVo.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in DAO :" + objConnnection);
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_SELECT_COMP_MAPPING);
			objCallableStatement.setInt(1, objComponentVo.getModelSeqNo());

			LogUtil.logMessage("objComponentVo.getComponentGroupSeqNo() :"
					+ objComponentVo.getComponentGroupSeqNo());
			
			// Ckeck for zero
			if (objComponentVo.getComponentGroupSeqNo() == 0) {
				
				objCallableStatement.setNull(2, Types.NULL);
			} else {
				objCallableStatement.setInt(2, objComponentVo
						.getComponentGroupSeqNo());
			}
			// Ends
			objCallableStatement.setInt(3, objComponentVo.getSubSectionSeqNo());
			objCallableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			objCallableStatement.setString(5, objComponentVo.getUserID());
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.INTEGER);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			objCallableStatement.registerOutParameter(9, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			objCompResultSet = (ResultSet) objCallableStatement.getObject(4);
			
			LogUtil
			.logMessage("Inside the fetchCompMap method of ModelCompMapDAO :resultSet"
					+ objCompResultSet);
			/*
			 * This Piece of Code is Modified For Valid Flag Change Request
			 * Starts
			 */
			
			strValidFlag = (String) objCallableStatement.getString(6);
			
			/* This Piece of Code is Modified For Valid Flag Change Request Ends */
			
			intLSDBErrorID = (int) objCallableStatement.getInt(7);
			strOracleCode = (String) objCallableStatement.getString(8);
			strErrorMessage = (String) objCallableStatement.getString(9);
			
			// Handled Valid Exception
			if (intLSDBErrorID != 0) {
				
				ErrorInfo objErrorInfo = new ErrorInfo();
				
				objErrorInfo.setMessage("" + intLSDBErrorID);
				
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
			
			while (objCompResultSet.next()) {
				objComponentVo = new ComponentVO();
				objComponentVo.setComponentSeqNo(objCompResultSet
						.getInt(DatabaseConstants.COMP_SEQ_NO));
				objComponentVo.setComponentName(objCompResultSet
						.getString(DatabaseConstants.COMP_NAME));
				objComponentVo.setComments(objCompResultSet
						.getString(DatabaseConstants.COMP_DESC));
				objComponentVo.setComponentGroupSeqNo(objCompResultSet
						.getInt(DatabaseConstants.COMP_GRP_SEQ_NO));
				if (objCompResultSet.getString(DatabaseConstants.MDL_SEQ_NO)
						.equalsIgnoreCase("Y")) {
					objComponentVo.setApplyToModelFlag(true);
				} else {
					objComponentVo.setApplyToModelFlag(false);
				}
				if (objCompResultSet.getString(DatabaseConstants.DEFAULT_FLAG)
						.equalsIgnoreCase("Y")) {
					objComponentVo.setDefaultFlag(true);
				} else {
					objComponentVo.setDefaultFlag(false);
				}
				
				//Added for CR_118
				if(objCompResultSet.getString(DatabaseConstants.LS204_COC_FLAG).equalsIgnoreCase("Y")){
					strDisplayInCOCFlag =objCompResultSet.getString(DatabaseConstants.LS204_COC_FLAG);
					LogUtil.logMessage("display in COC flag "+strDisplayInCOCFlag);
				}
				//Added for CR_118 Ends
				arlComponentList.add(objComponentVo);
			}
			if (arlComponentList != null && arlComponentList.size() > 0) { //if loop Updated for CR-121
				
				arlCompList.add(arlComponentList);
				
				if (strValidFlag != null && !"".equals(strValidFlag)) {
					
					arlCompList.add(strValidFlag);
				}
				//Added for CR_118
				if (strDisplayInCOCFlag != null && !"".equals(strDisplayInCOCFlag)) {
					
					arlCompList.add(strDisplayInCOCFlag);
				}
				//Added for CR_118 Ends
			}
			
			
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into DataAccess Exception block in ModelCompMapDAO.fetchCompMap"
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ModelCompMapDAO.fetchCompMap"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelCompMapDAO.fetchCompMap"
					+ objExp.getMessage());
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
				.logMessage("Enters into Exception block in ModelCompMapDAO.fetchCompMap"
						+ sqlex.getMessage());
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + sqlex.getMessage());
				throw new ApplicationException(sqlex, objErrorInfo);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in ModelCompMapDAO.fetchCompMap"
						+ objExp.getMessage());
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return arlCompList;
		
	}
	
	/***************************************************************************
	 * This Method is used to delete existing component mappings and to insert
	 * new Component Mappings For Selected Model,Section, SubSection and
	 * Component Group
	 * 
	 * @param objComponentVO
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	public static int updateCompMap(ArrayList objCompVoArrList)
	throws EMDException {
		LogUtil.logMessage("Inside ModelCompMapDAO.updateCompMap");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		ComponentVO objComponentVo = null;
		String strLogUser = "";
		//Added for CR_118
		String displayinCOCFlag="";
		//Added for CR_118 Ends
		
		try {
			
		
			
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in DAO in Update Method:"
					+ objConnnection);
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_DELETE_COMP_MAPPING);
			
			if (objCompVoArrList != null && objCompVoArrList.size() > 0) {
				//Edited fir CR_118
				displayinCOCFlag = objCompVoArrList.get(0).toString();
				LogUtil.logMessage("Display in COC flag in DAO"+displayinCOCFlag);
				for (int count = 1; count < objCompVoArrList.size(); count++) {
				//Edited for CR_118 Ends
					objComponentVo = (ComponentVO) objCompVoArrList.get(count);
					// To delete existing Component Mappings
					strLogUser = objComponentVo.getUserID();
					objCallableStatement.setInt(1, objComponentVo
							.getModelSeqNo());
					objCallableStatement.setInt(2, objComponentVo
							.getComponentGroupSeqNo());
					objCallableStatement.setInt(3, objComponentVo
							.getSubSectionSeqNo());
					objCallableStatement.setString(4, objComponentVo
							.getUserID());
					objCallableStatement.registerOutParameter(5, Types.INTEGER);
					objCallableStatement.registerOutParameter(6, Types.VARCHAR);
					objCallableStatement.registerOutParameter(7, Types.VARCHAR);
					
					intStatus = objCallableStatement.executeUpdate();
					break;
				}
			}
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			LogUtil
			.logMessage("Inside the updateCompMap method of ModelCompMapDAO :intStatus for Delete .."
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
			
			// To Insert new Component Mappings
			
			objCallableStatement = null;
			strOracleCode = null;
			strErrorMessage = null;
			intLSDBErrorID = 0;
			intStatus = 0;

			LogUtil.logMessage("objCompVoArrList.size():" + objCompVoArrList.size());
			if (objCompVoArrList != null && objCompVoArrList.size() > 0) {
				//Edited for CR_118
				for (int count = 1; count < objCompVoArrList.size(); count++) {
				//Edited for CR_118 Ends	
					objComponentVo = (ComponentVO) objCompVoArrList.get(count);
					objCallableStatement = objConnnection
					.prepareCall(EMDQueries.SP_INSERT_COMP_MAPPING);
					
					objCallableStatement.setInt(1, objComponentVo
							.getModelSeqNo());
					objCallableStatement.setInt(2, objComponentVo
							.getComponentSeqNo());
					objCallableStatement.setInt(3, objComponentVo
							.getComponentGroupSeqNo());
					objCallableStatement.setInt(4, objComponentVo
							.getSubSectionSeqNo());
					if (objComponentVo.isDefaultFlag()) {
						objCallableStatement.setString(5, "Y");
					} else {
						objCallableStatement.setString(5, "N");
					}
					//Added for CR_118
					if(displayinCOCFlag == ""){
						objCallableStatement.setNull(6, Types.NULL);
					}else{
						objCallableStatement.setString(6, displayinCOCFlag);
					}
					//Added for CR_118 Ends
					//Added for CR_127
					LogUtil.logMessage("IN DAO objComponentVo.getValidationFlag():"+
							objComponentVo.getValidationFlag());
					if(objComponentVo.getValidationFlag() == ""){
						objCallableStatement.setNull(7, Types.NULL);
					}else{
						objCallableStatement.setString(7, objComponentVo.getValidationFlag());
					}
					//Added for CR_127 Ends
					
					objCallableStatement.setString(8, objComponentVo
							.getUserID());
					objCallableStatement.registerOutParameter(9, Types.INTEGER);
					objCallableStatement.registerOutParameter(10, Types.VARCHAR);
					objCallableStatement.registerOutParameter(11, Types.VARCHAR);
					
					intStatus = objCallableStatement.executeUpdate();
					
					if (intStatus > 0) {
						intStatus = 0;
					}
					
					LogUtil
					.logMessage("Inside the updateCompMap method of ModelCompMapDAO :intStatus for Insert.."
							+ intStatus);
					intLSDBErrorID = (int) objCallableStatement.getInt(9);
					strOracleCode = (String) objCallableStatement.getString(10);
					strErrorMessage = (String) objCallableStatement
					.getString(11);
					if (intLSDBErrorID != 0) {
						LogUtil.logMessage("Error ID in Insert:"
								+ intLSDBErrorID);
						LogUtil.logMessage("strOracleCode in Insert:"
								+ strOracleCode);
						
					}
					if (intLSDBErrorID != 0) {
						LogUtil
						.logMessage("Enters into Error Loop in updateCompMap Method for Insert:");
						ErrorInfo objErrorInfo = new ErrorInfo();
						
						objErrorInfo.setMessageID("" + intLSDBErrorID);
						LogUtil
						.logMessage("Error message in ErrorInfo in Insert:"
								+ objErrorInfo.getMessageID());
						throw new DataAccessException(objErrorInfo);
						
					} else if (strOracleCode != null
							&& !"0".equals(strOracleCode)) {// Un handled
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
			}
		}
		
		catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("ENters into catch block in ModelCompMapDAO.updateCompMap"
					+ objDataExp.getErrorInfo().getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ModelCompMapDAO.updateCompMap"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			LogUtil
			.logMessage("Enters into Exception block in ModelCompMapDAO.updateCompMap");
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
				.logMessage("Enters into Exception block in ModelCompMapDAO.updateCompMap");
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return intStatus;
		
	}
/***************************************************************************
	 * This Method is used to unmap the Component Group from Model and SubSection
	 * Component Group
	 * 
	 * @param objComponentVO
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	public static int unMapComponentGrp(ComponentVO objComponentVO)
	throws EMDException {
		LogUtil.logMessage("Inside ModelCompMapDAO.unMapComponentGrp");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		
		String strLogUser = "";
		try {
			

			strLogUser = objComponentVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in DAO in Update Method:"+ objConnnection);
			
			objCallableStatement = objConnnection.prepareCall(EMDQueries.SP_UNMAP_COMP_GRP);
			
			objCallableStatement.setInt(1, objComponentVO.getModelSeqNo());
			objCallableStatement.setInt(2, objComponentVO.getComponentGroupSeqNo());
			objCallableStatement.setInt(3, objComponentVO.getSubSectionSeqNo());
			objCallableStatement.setString(4, objComponentVO.getUserID());
			
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			LogUtil.logMessage("Inside the unMapComponentGrp method of ModelCompMapDAO :intStatus .."+ intStatus);
			
			intLSDBErrorID = (int) objCallableStatement.getInt(5);
			strOracleCode = (String) objCallableStatement.getString(6);
			strErrorMessage = (String) objCallableStatement.getString(7);
			
			
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
	 * This Method is used to fetch the Component and Clause Details
	 * 
	 * @param objComponentVo
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/
	public static ArrayList fetchCompClauseMapDtls(ComponentVO objComponentVo)
	throws EMDException {
		LogUtil.logMessage("Inside ModelCompMapDAO.fetchCompMapDtls");
		
		Connection objConnnection = null;
		ArrayList arlComponentList = new ArrayList();
		ArrayList arlCompList = new ArrayList();
		ArrayList arlClauseList=new ArrayList();
		ArrayList arlResultList = new ArrayList();
		CallableStatement objCallableStatement = null;
		// Error out parameters
		ResultSet objwholeResultSet = null;
		ResultSet objClauseList=null;
		ResultSet objLeadComp=null;
		
		ResultSet objEDLNoResultSet = null;
		ResultSet objRefEDLNoResultSet = null;
		ResultSet objSubSecResultSet = null;
		ResultSet objTbDataResultSet = null;
		ResultSet objStdEqpResultSet = null;
		ResultSet objClauseComp = null;
		
		
		int intLSDBErrorID = 0;
		int countCol=0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strLogUser = "";
		String strValidFlag = null;
		//Added For CR_93 
		String compSourceFlag=null;
		try {
			strLogUser = objComponentVo.getUserID();
			objConnnection = DBHelper.prepareConnection();
			
			objCallableStatement = objConnnection.prepareCall(EMDQueries.SP_SELECT_MDL_CLA_COMP_MAPP);
			objCallableStatement.setInt(1, objComponentVo.getModelSeqNo());
			objCallableStatement.setInt(2, objComponentVo.getComponentGroupSeqNo());
			compSourceFlag= objComponentVo.getCompSourceFlag();		
			
			//Modified for CR_80 CR Form Enhancements III on 25-Nov-09 by RR68151
			objCallableStatement.setInt(3, objComponentVo.getComponentSeqNo());
			if(compSourceFlag!=null){
				objCallableStatement.setString (4, objComponentVo.getCompSourceFlag());
				}else {
					objCallableStatement.setNull(4,Types.NULL);
				}
			objCallableStatement.registerOutParameter(5, OracleTypes.CURSOR);
			objCallableStatement.setString(6,objComponentVo.getUserID());
			objCallableStatement.registerOutParameter(7, Types.INTEGER);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			objCallableStatement.registerOutParameter(9, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			objwholeResultSet = (ResultSet) objCallableStatement.getObject(5);
			
			
			LogUtil
			.logMessage("Inside the fetchCompClauseMapDtls method of ModelCompMapDAO :resultSet"
					+ objwholeResultSet);
			
			
			intLSDBErrorID = (int) objCallableStatement.getInt(7);
			strOracleCode = (String) objCallableStatement.getString(8);
			strErrorMessage = (String) objCallableStatement.getString(9);
			
			// Handled Valid Exception
			if (intLSDBErrorID != 0) {
				
				ErrorInfo objErrorInfo = new ErrorInfo();
				
				objErrorInfo.setMessage("" + intLSDBErrorID);
				
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
			
			
			while (objwholeResultSet.next()) {
				
				CompGroupVO objCompGroupVO = new CompGroupVO();
							
				objCompGroupVO.setSpecTypeSeqNo(objwholeResultSet.getInt(DatabaseConstants.SPEC_TYPE_SEQ_NO));
				objCompGroupVO.setSpecType(objwholeResultSet.getString(DatabaseConstants.SPEC_TYPE));
				LogUtil.logMessage("Spec Type Details"+objCompGroupVO.getSpecTypeSeqNo()+" "+objCompGroupVO.getSpecType());
				
				objCompGroupVO.setModelSeqNo(objwholeResultSet.getInt(DatabaseConstants.MODEL_SEQ_NO));
				objCompGroupVO.setModelName(objwholeResultSet.getString(DatabaseConstants.MODEL_NAME));
				LogUtil.logMessage("Model Details"+objCompGroupVO.getModelSeqNo()+" "+objCompGroupVO.getModelName());
				
				objCompGroupVO.setSectionSeqNo(objwholeResultSet.getInt(DatabaseConstants.LS201_SEC_SEQ_NO));
				objCompGroupVO.setSecCode(objwholeResultSet.getString("SEC_CODE"));
				objCompGroupVO.setSectionName(objwholeResultSet.getString(DatabaseConstants.LS201_SEC_NAME));
				LogUtil.logMessage("Section Details"+objCompGroupVO.getSectionSeqNo()+" "+objCompGroupVO.getSecCode()+"."+objCompGroupVO.getSectionName());
				
				objCompGroupVO.setSubSectionSeqNo(objwholeResultSet.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO));
				LogUtil.logMessage("Sub Section Seq No"+objCompGroupVO.getSubSectionSeqNo());
				objCompGroupVO.setSubSectionCode(String.valueOf(objwholeResultSet.getInt(DatabaseConstants.LS202_SUBSEC_CODE)));
				objCompGroupVO.setSubSectionName(objwholeResultSet.getString(DatabaseConstants.LS202_SUBSEC_NAME));
				LogUtil.logMessage("Sub Section Details"+objCompGroupVO.getSubSectionSeqNo()+" "+objCompGroupVO.getSecCode()+"."+objCompGroupVO.getSubSectionCode()+"."+objCompGroupVO.getSubSectionName());
				
				objCompGroupVO.setComponentGroupSeqNo(objwholeResultSet.getInt(DatabaseConstants.COMP_GRP_SEQ_NO));
				objCompGroupVO.setComponentGroupName(objwholeResultSet.getString(DatabaseConstants.LS130_COMP_GRP_NAME));
				objCompGroupVO.setLeadFlag(objwholeResultSet.getString(DatabaseConstants.LS130_VALIDATION_FLAG));
				LogUtil.logMessage("Comp Grp Details"+objCompGroupVO.getComponentGroupSeqNo()+" "+objCompGroupVO.getComponentGroupName());
				
				
				objLeadComp=(ResultSet)objwholeResultSet.getObject("COMPONENTS");
				while(objLeadComp.next()){
					
					ComponentVO objComponentVO = new ComponentVO();
					arlClauseList= new ArrayList();
					
					objComponentVO.setComponentName(objLeadComp.getString(DatabaseConstants.LS140_COMP_NAME));
					objComponentVO.setCompDefFlag(objLeadComp.getString(DatabaseConstants.LS204_DEFAULT_FLAG));
					objComponentVO.setComponentSeqNo(objLeadComp.getInt(DatabaseConstants.LS140_COMP_SEQ_NO));
					LogUtil.logMessage("Component Name"+objComponentVO.getComponentName());
					
					objClauseList=(ResultSet)objLeadComp.getObject("CLAUSE");
					
					while(objClauseList.next()){
						//Added for Cr_110
						int cntEDL = 0;
						int cntRefEDl = 0;
						int cntPartOf = 0;
						int[] arlPartSubSecSeqNo = new int[4];
						String[] arlEDLNos = { "", "", "" };
						String[] arlRefEDLNos = { "", "", "" };
						String[] arlpartSubSecCode = { "", "", "", "" };
						int[] arlClauseSeqNo = new int[4];
						//Added for Cr_110 Ends 
						ClauseVO objClauseVO = new ClauseVO();
						
						ArrayList arledlNo = new ArrayList();
						ArrayList arlrefedlNo = new ArrayList();
						ArrayList subsecCode = new ArrayList();
						ArrayList arlTableColumns= new ArrayList();
						ArrayList arlComp = new ArrayList();
						
						objClauseVO.setClauseSeqNo(objClauseList.getInt(DatabaseConstants.LS300_CLA_SEQ_NO));
						objClauseVO.setVersionNo(objClauseList.getInt(DatabaseConstants.LS301_VERSION_NO));
						objClauseVO.setClauseDesc(objClauseList.getString(DatabaseConstants.LS301_CLA_DESC));
						objClauseVO.setModifiedBy(objClauseList.getString(DatabaseConstants.LS301_UPDT_USER_ID));
						objClauseVO.setModifiedDate(objClauseList.getString(DatabaseConstants.LS301_UPDT_DATE));
						
						
						/**Added for getting EDL Nos**/
						objEDLNoResultSet=(ResultSet)objClauseList.getObject("EDLNO");
						
						while(objEDLNoResultSet.next()){
							
							LogUtil.logMessage("Enters into EDLNo Resultset Loop:");
							arledlNo.add(objEDLNoResultSet.getString(DatabaseConstants.LS302_EDL_NO));						
							
							//Added for Cr_110
							LogUtil.logMessage("Enters into EDLNo Resultset Loop of Clause From");
							arlEDLNos[cntEDL] = objEDLNoResultSet
							.getString(DatabaseConstants.LS302_EDL_NO);
							cntEDL++;
							//Added for Cr_110 Ends 
						}
						LogUtil.logMessage("Leaves  EDL Loop"+arledlNo.size());
						objClauseVO.setEdlNO(arledlNo);
						objClauseVO.setEdlNo(arlEDLNos);
						DBHelper.closeSQLObjects(objEDLNoResultSet, null, null);
						
						
						/**Added for getting REF EDL Nos**/
						
						objRefEDLNoResultSet = (ResultSet) objClauseList.getObject("REFEDLNO");
						
						while (objRefEDLNoResultSet.next()) {
							
							LogUtil.logMessage("Enters into RefEDLNo Resultset Loop:");
							arlrefedlNo.add(objRefEDLNoResultSet.getString(DatabaseConstants.LS303_REF_EDL_NO));
							//Added for Cr_110
							LogUtil.logMessage("Enters into RefEDLNo Resultset Loop of Clause From:");
							arlRefEDLNos[cntRefEDl] = objRefEDLNoResultSet
							.getString(DatabaseConstants.LS303_REF_EDL_NO);
							cntRefEDl++;
							//Added for Cr_110 Ends 		
						}
						
						LogUtil.logMessage("Leaves REF EDL Loop"+arlrefedlNo.size());
						objClauseVO.setRefEDLNO(arlrefedlNo);
						objClauseVO.setRefEDLNo(arlRefEDLNos);
						DBHelper.closeSQLObjects(objRefEDLNoResultSet, null, null);
						
						
						/**Added for getting Part Of Data**/
						objSubSecResultSet = (ResultSet) objClauseList.getObject("PARTOF");
						
						while (objSubSecResultSet.next()) {
							
							LogUtil.logMessage("Enters Part Of Loop");
							subsecCode.add(objSubSecResultSet.getString(DatabaseConstants.LS304_SUBSEC_NO));
							
							//Added for Cr_110
							arlpartSubSecCode[cntPartOf] = objSubSecResultSet
							.getString(DatabaseConstants.LS304_SUBSEC_NO);
							arlClauseSeqNo[cntPartOf] = objSubSecResultSet
							.getInt(DatabaseConstants.LS304_PART_OF_CLA_SEQ_NO);
							arlPartSubSecSeqNo[cntPartOf] = objSubSecResultSet
							.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO);
							cntPartOf++;
							//Added for Cr_110 Ends 											
						}
						LogUtil.logMessage("Leaves Part Resultset Loop"+subsecCode.size());
						objClauseVO.setPartOF(subsecCode);
						//Added for Cr_110
						objClauseVO.setPartOfCode(arlpartSubSecCode);
						objClauseVO.setPartOfSeqNo(arlPartSubSecSeqNo);
						objClauseVO.setClauseSeqNum(arlClauseSeqNo);
						//Added for Cr_110 Ends 
						
						
						DBHelper.closeSQLObjects(objSubSecResultSet, null, null);
						
						
						/**Added for Table Data**/
						objTbDataResultSet = (ResultSet) objClauseList.getObject(DatabaseConstants.CLA_TBL_DATA);
						
						ArrayList arlTableRows = new ArrayList();
						while (objTbDataResultSet.next()) {
							LogUtil.logMessage("Enters Table Data Loop");
							
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
//						Added For CR_93
		     			countCol=ApplicationUtil.getTableDataColumnsCount(arlTableRows);
						objClauseVO.setTableDataColSize(countCol);
						objClauseVO.setTableArrayData1(arlTableRows);
						LogUtil.logMessage("Leaves Table Data Loop"+arlTableRows.size());
						
						DBHelper.closeSQLObjects(objTbDataResultSet, null, null);
						
						objClauseVO.setDwONumber(objClauseList.getInt(DatabaseConstants.LS301_DWO_NUMBER));
						
						objClauseVO.setPartNumber(objClauseList.getInt(DatabaseConstants.LS301_PART_NUMBER));
						
						objClauseVO.setPriceBookNumber(objClauseList.getInt(DatabaseConstants.LS301_PRICE_BOOK_NUMBER));
						
						/**Added for Standard Equipment Loop**/
						objStdEqpResultSet = (ResultSet) objClauseList.getObject(DatabaseConstants.STD_EQUIP);
						while (objStdEqpResultSet.next()) {
							LogUtil.logMessage("Enters Standard Equipment ResultSet Loop");
							objClauseVO.setStandardEquipmentDesc(objStdEqpResultSet.getString(DatabaseConstants.LS060_STD_EQP_DESC));
							objClauseVO.setStandardEquipmentSeqNo(objStdEqpResultSet.getInt(DatabaseConstants.LS060_STD_EQP_SEQ_NO));
							
						}
						LogUtil.logMessage("Leaves Std Equip Loop");
						DBHelper.closeSQLObjects(objStdEqpResultSet, null, null);
						
						objClauseVO.setComments(objClauseList.getString(DatabaseConstants.LS301_ENGG_DATA_COMMENTS));
						
						objClauseVO.setReason(objClauseList.getString(DatabaseConstants.LS301_CLA_REASON));
						
						objClauseComp = (ResultSet) objClauseList.getObject(DatabaseConstants.CLA_COMP);
						
						arlComp= new ArrayList();
						
						while (objClauseComp.next()) {
							ComponentVO objjComponentVO = new ComponentVO();
							
							
							LogUtil.logMessage("Enters Clause Comp loop");
							 
							
							 objjComponentVO.setComponentName(objClauseComp.getString(DatabaseConstants.LS140_COMP_NAME));
							
							 objjComponentVO.setCompDefFlag(objClauseComp.getString(DatabaseConstants.LS204_DEFAULT_FLAG));
							
							 objjComponentVO.setCompLeadFlag(objClauseComp.getString("ITALICS"));
							 
							 //To Add only optional components for the above clause
							 if(objjComponentVO.getCompLeadFlag().equals("Y")){
								 
								 arlComp.add(objjComponentVO.getComponentName());
								 
								 
							 }
							
							 //arlComp.add(objjComponentVO);
							
						}
						objClauseVO.setComponentVO(arlComp);
						LogUtil.logMessage("Leaves Clause Comp loop"+arlComp.size());
						
						
						
						DBHelper.closeSQLObjects(objClauseComp, null, null);
						
						arlClauseList.add(objClauseVO);
						
									
					}
					
					objComponentVO.setClauseVOList(arlClauseList);
					DBHelper.closeSQLObjects(objClauseComp, null, null);
					
					arlComponentList.add(objComponentVO);
					
				}
				
				objCompGroupVO.setComponent(arlComponentList);
				
				DBHelper.closeSQLObjects(objLeadComp, null, null);
				
				arlCompList.add(objCompGroupVO);
						
			}	
			
			
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into DataAccess Exception block in ModelCompMapDAO.fetchCompClauseMapDtls"
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ModelCompMapDAO.fetchCompClauseMapDtls"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelCompMapDAO.fetchCompClauseMapDtls"
					+ objExp.getMessage());
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(objwholeResultSet,
						objCallableStatement, objConnnection);
				
			} catch (SQLException sqlex) {
				LogUtil
				.logMessage("Enters into Exception block in ModelCompMapDAO.fetchCompClauseMapDtls"
						+ sqlex.getMessage());
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + sqlex.getMessage());
				throw new ApplicationException(sqlex, objErrorInfo);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in ModelCompMapDAO.fetchCompClauseMapDtls"
						+ objExp.getMessage());
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return arlCompList;
		
	}
	/***************************************************************************
	 * Added For CR_109
	 * This Method is used to insert new component for an existing mapping. 
	 * 
	 * @param objComponentVO
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	public static int insertCompMap(ComponentVO objComponentVo)
	throws EMDException {
		LogUtil.logMessage("Inside ModelCompMapDAO.insertCompMap");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		try {
			
			objConnnection = DBHelper.prepareConnection();
					
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_INSERT_COMP_MAPPING);
			
			objCallableStatement.setInt(1, objComponentVo.getModelSeqNo());
			objCallableStatement.setInt(2, objComponentVo.getComponentSeqNo());
			objCallableStatement.setInt(3, objComponentVo.getComponentGroupSeqNo());
			objCallableStatement.setInt(4, objComponentVo.getSubSectionSeqNo());
			if (objComponentVo.isDefaultFlag()) {
				objCallableStatement.setString(5, "Y");
			} else {
				objCallableStatement.setString(5, "N");
			}
			
			objCallableStatement.setNull(6, Types.NULL);//Added for CR-121
			//Added for CR_127
			LogUtil.logMessage("IN DAO objComponentVo.getValidationFlag():"+
					objComponentVo.getValidationFlag());
			objCallableStatement.setString(7, objComponentVo.getValidationFlag());
			
			//Added for CR_127 Ends
			objCallableStatement.setString(8, objComponentVo.getUserID());
			objCallableStatement.registerOutParameter(9, Types.INTEGER);
			objCallableStatement.registerOutParameter(10, Types.VARCHAR);
			objCallableStatement.registerOutParameter(11, Types.VARCHAR);
			
			
			intStatus = objCallableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			LogUtil
			.logMessage("Inside the insertCompMap method of ModelCompMapDAO :intStatus for Insert.."
					+ intStatus);
			intLSDBErrorID = (int) objCallableStatement.getInt(9);
			strOracleCode = (String) objCallableStatement.getString(10);
			strErrorMessage = (String) objCallableStatement.getString(11);
			
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Error ID in Insert:"
						+ intLSDBErrorID);
				LogUtil.logMessage("strOracleCode in Insert:"
						+ strOracleCode);
				
			}
			if (intLSDBErrorID != 0) {
				LogUtil
				.logMessage("Enters into Error Loop in insertCompMap Method for Insert:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				
				objErrorInfo.setMessageID("" + intLSDBErrorID);
				LogUtil
				.logMessage("Error message in ErrorInfo in Insert:"
						+ objErrorInfo.getMessageID());
				throw new DataAccessException(objErrorInfo);
				
			} else if (strOracleCode != null
					&& !"0".equals(strOracleCode)) {// Un handled
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
			.logMessage("ENters into catch block in ModelCompMapDAO.insertCompMap"
					+ objDataExp.getErrorInfo().getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ModelCompMapDAO.insertCompMap"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			LogUtil
			.logMessage("Enters into Exception block in ModelCompMapDAO.insertCompMap");
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
				.logMessage("Enters into Exception block in ModelCompMapDAO.insertCompMap");
				throw new ApplicationException(objExp, objErrorInfo);
			}			
		}		
		return intStatus;		
	}
}
