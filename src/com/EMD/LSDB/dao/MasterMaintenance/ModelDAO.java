/*
 * Created on Apr 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.dao.MasterMaintenance;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

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
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.SubSectionVO;
import com.EMD.LSDB.vo.common.OrderVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business methods for the Model
 ******************************************************************************/
/***************************************************************************
 * ------------------------------------------------------------------------------------------------------
 *     Date         version  modified by        comments                                Remarks 
 * 16/03/2012        1.0      SD41630       modified for model Genaral
 *                                          info text Darft and Open maintanance
 *                                           in the fetch and update model methods       Added for CR_106
 * ----------------------------------------------------------------------------------------------------------
 **************************************************************************/

public class ModelDAO extends EMDDAO {
	
	private ModelDAO() {
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objModelVO
	 *            the object for searching model
	 * @return arrayList the list contains the models
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList fetchModels(ModelVo objModelVO) throws EMDException {
		LogUtil.logMessage("Entering ModelDAO.fetchModels");
		Connection objConnnection = null;
		ArrayList arlModel = new ArrayList();
		CallableStatement objCallableStatement = null;
		// Error out parameters
		ResultSet rsModel = null;
		int intLSDBErrorID = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strLogUser = "";
		ModelVo objOutModelVO=null;
		try {
			strLogUser = objModelVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			
			
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_SELECT_MODEL);
			
			if (objModelVO.getSpecTypeSeqNo() == 0) {
				
				objCallableStatement.setNull(1, Types.NULL);
			} else {
				
				objCallableStatement.setInt(1, objModelVO.getSpecTypeSeqNo());
			}
			
			// Commented for LSDB_CR_56
			/*
			 * if(objModelVO.getModelCustTypeSeqNo()==0){
			 * 
			 * objCallableStatement.setNull(2,Types.NULL); } else{
			 * 
			 * objCallableStatement.setInt(2,objModelVO.getModelCustTypeSeqNo()); }
			 */
			
			//Added for CR_118
			if (objModelVO.getModelFlag() == null ) {
//				 Added to show all non hidden models
				objCallableStatement.setString(2,ApplicationConstants.NON_HIDDEN_MODELS);
				
			} else {
				objCallableStatement.setString(2, objModelVO.getModelFlag());
			}
			//Added for CR_118 ends
			objCallableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			objCallableStatement.setString(4, objModelVO.getUserID());
			
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			rsModel = (ResultSet) objCallableStatement.getObject(3);
						
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
			
			while (rsModel.next()) {
				objOutModelVO = new ModelVo();
				objOutModelVO.setModelSeqNo(rsModel
						.getInt(DatabaseConstants.MODEL_SEQ_NO));
				
				objOutModelVO.setSpecTypeSeqNo(rsModel
						.getInt(DatabaseConstants.SPEC_TYPE_SEQ_NO));
				
				// Commented for LSDB_CR_56
				// objModelVO.setModelCustTypeSeqNo(rsModel.getInt(DatabaseConstants.CUS_TYPE_SEQ_NO));
				
				objOutModelVO.setModelName(rsModel
						.getString(DatabaseConstants.MODEL_NAME));
				objOutModelVO.setModelDesc(rsModel
						.getString(DatabaseConstants.MODEL_DESC));
				
				objOutModelVO.setHpowerRate(rsModel
						.getString(DatabaseConstants.HORSE_POWER_RATE));
				
				// Added for CR_97 for change control type
				
				objOutModelVO.setChngCtrlTypeFlag(rsModel.
						getString(DatabaseConstants.CHANGE_CONTROL_FLAG));
				//Added For CR_106 
				
				//Added for CR_118
				objOutModelVO.setModelFlag(rsModel
						.getString(DatabaseConstants.LS200_MDL_HIDDEN_FLAG));
				//Added for CR_118 Ends
				
				if(objModelVO.getIntSuccess()==0){
				objOutModelVO.setGenInfoTextDraft(rsModel.getString(DatabaseConstants.LS200_GEN_INFO_TEXT_DRFT));
				objOutModelVO.setGenInfoTextOpen(rsModel.getString(DatabaseConstants.LS200_GEN_INFO_TEXT_OPN));
				}else {
					if(objOutModelVO.getModelSeqNo()==objModelVO.getModelSeqNo())
					{
					 if((objModelVO.getGenInfoTextDraft()!=null )&& !(objModelVO.getGenInfoTextDraft().equals(""))){
							objOutModelVO.setGenInfoTextDraft(objModelVO.getGenInfoTextDraft());
							
				         }else {
					        
							objOutModelVO.setGenInfoTextDraft(rsModel.getString(DatabaseConstants.LS200_GEN_INFO_TEXT_DRFT));
						}
						
						if((objModelVO.getGenInfoTextOpen()!=null )&& !("".equals(objModelVO.getGenInfoTextOpen()))){
							objOutModelVO.setGenInfoTextOpen(objModelVO.getGenInfoTextOpen());
						}else {
							
							objOutModelVO.setGenInfoTextOpen(rsModel.getString(DatabaseConstants.LS200_GEN_INFO_TEXT_OPN));
						}
					
					}
					
				}
				
				arlModel.add(objOutModelVO);
			}
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into DataAccess Exception block in ModelDAO:fetchModels"
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ModelDAO:fetchModels"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelDAO:fetchModels"
					+ objExp.getMessage());
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(rsModel, objCallableStatement,
						objConnnection);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in ModelDAO:fetchModels"
						+ objExp.getMessage());
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return arlModel;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objModelVO
	 *            the object for inserting model
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int insertModel(ModelVo objModelVO) throws EMDException {
		LogUtil.logMessage("Entering ModelDAO.insertModel");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		try {
			strLogUser = objModelVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_INSERT_MODEL);
			objCallableStatement.setString(1, objModelVO.getModelName());
			objCallableStatement.setString(2, objModelVO.getModelDesc());
			objCallableStatement.setString(3, objModelVO.getHpowerRate());
			objCallableStatement.setInt(4, objModelVO.getSpecTypeSeqNo());
			
			// Commented for LSDB_CR_56
			/*
			 * if(objModelVO.getSpecTypeSeqNo()==1){
			 * objCallableStatement.setInt(5,objModelVO.getModelCustTypeSeqNo());
			 * }else{ objCallableStatement.setInt(5,1);//For Domestic PM&I }
			 */
			//Added for CR_97 for Change Control Type
			objCallableStatement.setString(5, objModelVO.getChngCtrlTypeFlag());
			objCallableStatement.setString(6, objModelVO.getUserID());
			
			objCallableStatement.registerOutParameter(7, Types.INTEGER);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			objCallableStatement.registerOutParameter(9, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			
			// Success case
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			LogUtil
			.logMessage("Inside the insertModel method of ModelDAO :intStatus .."
					+ intStatus);
			intLSDBErrorID = (int) objCallableStatement.getInt(7);
			strOracleCode = (String) objCallableStatement.getString(8);
			strErrorMessage = (String) objCallableStatement.getString(9);
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Error ID:" + intLSDBErrorID);
				LogUtil.logMessage("strOracleCode:" + strOracleCode);
				
			}
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Enters into Error Loop:");
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
				StringBuffer sbErrorMessage = new StringBuffer();
				sbErrorMessage.append(strOracleCode + " ");
				sbErrorMessage.append(strErrorMessage);
				LogUtil.logMessage("sbErrorMessage.toString():"
						+ sbErrorMessage.toString());
				objErrorInfo.setMessage(sbErrorMessage.toString());
				LogUtil.logError(objErrorInfo);
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
		}
		
		catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into DataAccess Exception block in ModelDAO:insertModel"
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ModelDAO:insertModel"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelDAO:insertModel"
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
				.logMessage("Enters into Exception block in ModelDAO:insertModel"
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
	 * @param objModelVO
	 *            the object for updating model
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int updateModel(ModelVo objModelVO) throws EMDException {
		LogUtil.logMessage("Entering ModelDAO.updateModel");
		Connection objConnnection = null;
		List arlModel = new ArrayList();
		CallableStatement callableStatement = null;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		int intStatus = 0;
		String strLogUser = "";
		try {
			strLogUser = objModelVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			arlModel.add(new Integer(objModelVO.getModelSeqNo()));
			callableStatement = objConnnection
			.prepareCall(EMDQueries.SP_UPDATE_MODEL);
			
			
			callableStatement.setInt(1, objModelVO.getModelSeqNo());
			if(objModelVO.getModelName()!=null && !"".equalsIgnoreCase(objModelVO.getModelName())){
			callableStatement.setString(2, objModelVO.getModelName());
			}else {
				callableStatement.setNull(2, Types.NULL);
			}
					
			if(objModelVO.getModelDesc()!=null && !"".equalsIgnoreCase(objModelVO.getModelDesc())){
				callableStatement.setString(3, objModelVO.getModelDesc());
				}else {
					callableStatement.setNull(3, Types.NULL);
				}
			
			if(objModelVO.getHpowerRate()!=null && !"".equalsIgnoreCase(objModelVO.getHpowerRate())){
				callableStatement.setString(4, objModelVO.getHpowerRate());
				
				}else {
					callableStatement.setNull(4, Types.NULL);
				}
			
			callableStatement.setInt(5, objModelVO.getSpecTypeSeqNo());
			
			//Added for CR_97 for Change Control Type
			if(objModelVO.getChngCtrlTypeFlag()!=null && !"".equalsIgnoreCase(objModelVO.getChngCtrlTypeFlag())){
				callableStatement.setString(6, objModelVO.getChngCtrlTypeFlag());
				
				}else {
					callableStatement.setNull(6, Types.NULL);
				}
			
			// Commented for LSDB_CR_56
			// callableStatement.setInt(6,objModelVO.getModelCustTypeSeqNo());
//			Modified and Added for CR_106
			if(objModelVO.getGenInfoTextDraft()!=null && !"".equalsIgnoreCase(objModelVO.getGenInfoTextDraft())){
			callableStatement.setString(7, objModelVO.getGenInfoTextDraft());
			
			}else {
				callableStatement.setNull(7, Types.NULL);
			}
			if(objModelVO.getGenInfoTextOpen()!=null && !"".equalsIgnoreCase(objModelVO.getGenInfoTextOpen())){
			callableStatement.setString(8, objModelVO.getGenInfoTextOpen());
			
			}else {
				callableStatement.setNull(8, Types.NULL);
			}
			
			//Added for CR_118
			LogUtil.logMessage("Testing : Value of hidden model flag in Dao is "+objModelVO.getModelFlag());
			if(objModelVO.getModelFlag() == null){
				// Added to show all non hidden models
				callableStatement.setString(9,ApplicationConstants.NON_HIDDEN_MODELS);
			}else{
				callableStatement.setString(9, objModelVO.getModelFlag());
			}
			//Added for CR_118 Ends
			
			callableStatement.setString(10, objModelVO.getUserID());
			callableStatement.registerOutParameter(11, Types.INTEGER);
			callableStatement.registerOutParameter(12, Types.VARCHAR);
			callableStatement.registerOutParameter(13, Types.VARCHAR);
			intStatus = callableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			LogUtil
			.logMessage("Inside the updateModel method of ModelMaintDAO :intStatus .."
					+ intStatus);
			
			intLSDBErrorID = (int) callableStatement.getInt(11);
			strOracleCode = (String) callableStatement.getString(12);
			strErrorMessage = (String) callableStatement.getString(13);
			
			LogUtil.logMessage("Error ID:" + intLSDBErrorID);
			
			if (intLSDBErrorID != 0) {
				
				LogUtil.logMessage("Enters into Error Loop:");
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
				objErrorInfo.setMessage(sb.toString());
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into DataAccess Exception block in ModelDAO:updateModel"
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ModelDAO:updateModel"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelDAO:updateModel"
					+ objExp.getMessage());
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(null, callableStatement,
						objConnnection);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in ModelDAO:updateModel"
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
	 * @param objModelVO
	 * the object for copy model
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 *************************************************************************/
	
	public static int copyModel(ModelVo objModelVO) throws EMDException {
		LogUtil.logMessage("Entering ModelDAO.CopyModel");
		Connection objConnnection = null;
		CallableStatement callableStatement = null;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		int intStatus = 0;
		String strLogUser = "";
		try {
			strLogUser = objModelVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			callableStatement = objConnnection
			.prepareCall(EMDQueries.SP_COPY_MODEL);
			callableStatement.setInt(1, objModelVO.getModelSeqNo());
			callableStatement.setString(2, objModelVO.getModelName());
			callableStatement.setString(3, objModelVO.getModelDesc());
			callableStatement.setString(4, objModelVO.getHpowerRate());
			callableStatement.setInt(5, objModelVO.getSpecTypeSeqNo());
			// Added for CR_97 for Change Control Type
			callableStatement.setString(6, objModelVO.getChngCtrlTypeFlag());
			
			callableStatement.setString(7, objModelVO.getUserID());
			
			callableStatement.registerOutParameter(8, Types.INTEGER);
			callableStatement.registerOutParameter(9, Types.VARCHAR);
			callableStatement.registerOutParameter(10, Types.VARCHAR);
			intStatus = callableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
						
			intLSDBErrorID = (int) callableStatement.getInt(8);
			strOracleCode = (String) callableStatement.getString(9);
			strErrorMessage = (String) callableStatement.getString(10);
			
			LogUtil.logMessage("Error ID:" + intLSDBErrorID);
			
			if (intLSDBErrorID != 0) {
				
				LogUtil.logMessage("Enters into Error Loop:");
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
				objErrorInfo.setMessage(sb.toString());
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into DataAccess Exception block in ModelDAO:CopyModel"
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ModelDAO:CopyModel"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelDAO:CopyModel"
					+ objExp.getMessage());
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(null, callableStatement,
						objConnnection);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in ModelDAO:CopyModel"
						+ objExp.getMessage());
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return intStatus;
		
	}
	
	//Added for CR-113 for Clauses in orders report
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objClauseVO
	 *            the object for searching model
	 * @return arrayList the list contains the order details
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList search(ClauseVO objClauseVO) throws EMDException {
		LogUtil.logMessage("Entering ModelDAO.search");
		
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ResultSet objClauseResultSet = null;
		ResultSet objTbDataResultSet = null;
		ResultSet objEDLNoResultSet = null;
		ResultSet objRefEDLNoResultSet = null;
		ResultSet objPartofResultSet = null;
		ResultSet objStdEqpResultSet = null;
		ResultSet objCompResultSet = null;
		ResultSet objOrdersUsedResultSet = null;
		ArrayList arlOrdersList = new ArrayList();
		
		//Error out parameters	
		int intLSDBErrorID = 0;
		int countCol=0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strLogUser = "";
		
		try {
			strLogUser = objClauseVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_SELECT_CLA_IN_ORDRS);
			
			LogUtil.logMessage("objClauseVO.getClauseSeqNo():"+objClauseVO.getClauseSeqNo());
			LogUtil.logMessage("objClauseVO.getVersionNo():"+objClauseVO.getVersionNo());
			LogUtil.logMessage("objClauseVO.getShowLatestPubSpecFlag:"+objClauseVO.getShowLatestPubSpecFlag());
			LogUtil.logMessage("objClauseVO.getOrderbyFlag():"+objClauseVO.getOrderbyFlag());
			LogUtil.logMessage("objClauseVO.getUserID():"+objClauseVO.getUserID());
			
			objCallableStatement.setInt(1, objClauseVO.getClauseSeqNo());
			objCallableStatement.setInt(2, objClauseVO.getVersionNo());
			objCallableStatement.setString(3, objClauseVO.getShowLatestPubSpecFlag());
			objCallableStatement.setInt(4, objClauseVO.getOrderbyFlag());
			objCallableStatement.registerOutParameter(5, OracleTypes.CURSOR);
			objCallableStatement.setString(6, objClauseVO.getUserID());
			objCallableStatement.registerOutParameter(7, Types.INTEGER);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			objCallableStatement.registerOutParameter(9, Types.VARCHAR);
			LogUtil.logMessage("Before query execution:");
			objCallableStatement.execute();
			LogUtil.logMessage("After query execution:");
			
			objClauseResultSet = (ResultSet) objCallableStatement.getObject(5);
						
			intLSDBErrorID = (int) objCallableStatement.getInt(7);
			strOracleCode = (String) objCallableStatement.getString(8);
			strErrorMessage = (String) objCallableStatement.getString(9);
			
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
			LogUtil.logMessage("Before while loop:");
			LogUtil.logMessage("objClauseResultSet.toString():"+objClauseResultSet.toString());
			while (objClauseResultSet.next()) {
				
					LogUtil.logMessage("Inside objClauseResultSet");
					
					ArrayList arlTableColumns = null;
					ArrayList arlTableRows = new ArrayList();
					ArrayList arlEDLNos = new ArrayList();
					ArrayList arlRefEDLNos = new ArrayList();
					ArrayList arlpartSubSecCode = new ArrayList();
					ArrayList arlComponentList = new ArrayList();
					ArrayList arlOrderUsedList = new ArrayList();
					
					int cntEDL = 0;
					int cntRefEDl = 0;
					int cntPartOf = 0;
					int partofLeadCompGrp = 0;
					
					int[] arlPartSubSecSeqNo = new int[4];
					int[] arlClauseSeqNo = new int[4];
					
					objClauseVO = new ClauseVO(); 
					objClauseVO.setClauseDesc(objClauseResultSet.getString(DatabaseConstants.LS301_CLA_DESC));
					objClauseVO.setClauseSeqNo(objClauseResultSet.getInt(DatabaseConstants.LS300_CLA_SEQ_NO));
					objClauseVO.setVersionNo(objClauseResultSet.getInt(DatabaseConstants.LS301_VERSION_NO));
					objClauseVO.setCustomerName(objClauseResultSet.getString(DatabaseConstants.LS050_CUST_NAME));
					objClauseVO.setDwONumber(objClauseResultSet.getInt(DatabaseConstants.LS301_DWO_NUMBER));
					objClauseVO.setPartNumber(objClauseResultSet.getInt(DatabaseConstants.LS301_PART_NUMBER));
					objClauseVO.setPriceBookNumber(objClauseResultSet.getInt(DatabaseConstants.LS301_PRICE_BOOK_NUMBER));
					objClauseVO.setComments(objClauseResultSet.getString(DatabaseConstants.LS301_ENGG_DATA_COMMENTS));
					
					//Cursor for Table Data
					objTbDataResultSet = (ResultSet) objClauseResultSet.getObject("TABLE_DATA");
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
						arlTableRows.add(arlTableColumns);
					}
	     			countCol=ApplicationUtil.getTableDataColumnsCount(arlTableRows);
					objClauseVO.setTableDataColSize(countCol);
					objClauseVO.setTableArrayData1(arlTableRows);
					DBHelper.closeSQLObjects(objTbDataResultSet, null, null);
					
					//Cursor of EDL Number
					objEDLNoResultSet = (ResultSet) objClauseResultSet.getObject("EDLNO");
					while (objEDLNoResultSet.next()) 
					{
						LogUtil.logMessage("Inside objEDLNoResultSet");
						arlEDLNos.add(objEDLNoResultSet.getString(DatabaseConstants.LS302_EDL_NO));
						cntEDL++;
					}
					objClauseVO.setEdlNO(arlEDLNos);
					DBHelper.closeSQLObjects(objEDLNoResultSet, null, null);
					
					// Cursor of Ref EDL Number				
					objRefEDLNoResultSet = (ResultSet) objClauseResultSet.getObject("refEDLNO");
					while (objRefEDLNoResultSet.next()) {
						LogUtil.logMessage("Inside objRefEDLNoResultSet");
						arlRefEDLNos.add(objRefEDLNoResultSet.getString(DatabaseConstants.LS303_REF_EDL_NO));
						cntRefEDl++;
					}
					objClauseVO.setRefEDLNO(arlRefEDLNos);
					DBHelper.closeSQLObjects(objRefEDLNoResultSet, null, null);
									
					// Cursor of PART OF
					objPartofResultSet = (ResultSet) objClauseResultSet.getObject("PartOF");
					while (objPartofResultSet.next()) {
						LogUtil.logMessage("Inside objPartofResultSet");
						arlPartSubSecSeqNo[cntPartOf] = objPartofResultSet.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO);
						arlpartSubSecCode.add(objPartofResultSet.getString(DatabaseConstants.LS304_SUBSEC_NO));
						arlClauseSeqNo[cntPartOf] = objPartofResultSet.getInt(DatabaseConstants.LS304_PART_OF_CLA_SEQ_NO);
						partofLeadCompGrp=objPartofResultSet.getInt(DatabaseConstants.LS304_PART_OF_LEAD_CMP_GRP);
						cntPartOf++;
					}
					objClauseVO.setPartOF(arlpartSubSecCode);
					objClauseVO.setPartOfSeqNo(arlPartSubSecSeqNo);
					objClauseVO.setClauseSeqNum(arlClauseSeqNo);
					objClauseVO.setPartofLeadCompGrp(partofLeadCompGrp);
					DBHelper.closeSQLObjects(objPartofResultSet, null, null);
					
					//Cursor for Standard Equipment
					objStdEqpResultSet = (ResultSet) objClauseResultSet.getObject("STD_EQUIP");
					while (objStdEqpResultSet.next()) {
						LogUtil.logMessage("Inside objStdEqpResultSet");
						objClauseVO.setStandardEquipmentDesc(objStdEqpResultSet.getString(DatabaseConstants.LS060_STD_EQP_DESC));
					}
					DBHelper.closeSQLObjects(objStdEqpResultSet, null, null);
					
					//Cursor For ComponentName
					objCompResultSet = (ResultSet) objClauseResultSet.getObject("COMPONENTS");
					while (objCompResultSet.next()){
						LogUtil.logMessage("Inside objCompResultSet");
						ComponentVO objCompVO = new ComponentVO();
						objCompVO.setComponentSeqNo(objCompResultSet.getInt(DatabaseConstants.LS140_COMP_SEQ_NO));
						objCompVO.setComponentName(objCompResultSet.getString(DatabaseConstants.LS140_COMP_NAME));
						objCompVO.setComponentGroupName(objCompResultSet.getString(DatabaseConstants.LS130_COMP_GRP_NAME));				
						arlComponentList.add(objCompVO);
						}
					objClauseVO.setComponentList(arlComponentList);
					DBHelper.closeSQLObjects(objCompResultSet, null, null);
					
					//Cursor for OrderDetails
					objOrdersUsedResultSet = (ResultSet) objClauseResultSet.getObject("ORDERS_USED");
					while (objOrdersUsedResultSet.next()){
						LogUtil.logMessage("Inside objOrdersUsedResultSet");
						OrderVO objOrderVO = new OrderVO();
						objOrderVO.setOrderNo(objOrdersUsedResultSet.getString(DatabaseConstants.LS400_ORDR_NO));
						objOrderVO.setStatusDesc(objOrdersUsedResultSet.getString(DatabaseConstants.STATUS));
						objOrderVO.setModelName(objOrdersUsedResultSet.getString(DatabaseConstants.LS200_MDL_NAME));
						objOrderVO.setCustomerName(objOrdersUsedResultSet.getString(DatabaseConstants.LS050_CUST_NAME));
						arlOrderUsedList.add(objOrderVO);
						LogUtil.logMessage("objOrderVO.getOrderNo():"+objOrderVO.getOrderNo());
						LogUtil.logMessage("objOrderVO.getStatusDesc():"+objOrderVO.getStatusDesc());
						LogUtil.logMessage("objOrderVO.getCustomerName():"+objOrderVO.getCustomerName());
						}
					objClauseVO.setOrderList(arlOrderUsedList);
					DBHelper.closeSQLObjects(objOrdersUsedResultSet, null, null);
					
					arlOrdersList.add(objClauseVO);
				
			}
			
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into DataAccess Exception block in ModelDAO:search"
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ModelDAO:search"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelDAO:search"
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
				LogUtil
				.logMessage("Enters into Exception block in ModelDAO:search"
						+ objExp.getMessage());
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		LogUtil.logMessage("arlOrdersList.size()"+arlOrdersList.size());
		return arlOrdersList;
		
	}
	
	
	
}