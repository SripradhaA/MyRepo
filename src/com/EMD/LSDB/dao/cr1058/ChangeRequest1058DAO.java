package com.EMD.LSDB.dao.cr1058;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.tomcat.dbcp.dbcp2.DelegatingConnection;//Added for Tomcat & CR-123

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.jdbc.OracleTypes;

//import oracle.jdbc.driver.OracleTypes;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.constant.DatabaseConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.util.ApplicationUtil;
import com.EMD.LSDB.dao.common.AdmnQueries;
import com.EMD.LSDB.dao.common.DBHelper;
import com.EMD.LSDB.dao.common.EMDDAO;
import com.EMD.LSDB.dao.common.EMDQueries;
import com.EMD.LSDB.vo.admn.UserVO;
import com.EMD.LSDB.vo.common.ChangeRequest1058AttachmentsVO;
import com.EMD.LSDB.vo.common.ChangeRequest1058VO;
import com.EMD.LSDB.vo.common.ClauseTableDataVO;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.CompGroupVO;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.RequestCategories1058VO;
import com.EMD.LSDB.vo.common.RequestClauseChangeTypes1058VO;
import com.EMD.LSDB.vo.common.RequestTypes1058VO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.StandardEquipVO;
import com.EMD.LSDB.vo.common.SubSectionVO;

public class ChangeRequest1058DAO extends EMDDAO{
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ChangeRequest1058VO
	 * @return ArrayList contains the list of OrderNumbers
	 * @throws EMDException
	 **************************************************************************/
	

	public static ArrayList fetchCreate1058RequestOrderNo(ChangeRequest1058VO objChangeRequest1058VO)
	throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058DAO:fetchCreate1058RequestOrderNo");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ResultSet objResultSet = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		ChangeRequest1058VO objRequestDetails1058VO=null;
		ArrayList arlOrderNoList=new ArrayList();
		
		try {
			strLogUser= objChangeRequest1058VO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_SELECT_ORDRS_FOR_1058);
			 
			
			objCallableStatement.setString(1, strLogUser);
			objCallableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(3, Types.INTEGER);
			objCallableStatement.registerOutParameter(4, Types.VARCHAR);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
            
			objCallableStatement.execute();
			objResultSet = (ResultSet) objCallableStatement.getObject(2);
            
			LogUtil
			.logMessage("ResultSet Value in ChangeRequest1058DAO:fetchCreate1058RequestOrderNo:"
					+ objResultSet);
			intLSDBErrorID = objCallableStatement.getInt(3);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(4);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(5);
			
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
			
			
			while(objResultSet.next()){
				objRequestDetails1058VO =new ChangeRequest1058VO();
				objRequestDetails1058VO.setOrderNo(objResultSet.getString(DatabaseConstants.LS400_ORDR_NO));
				objRequestDetails1058VO.setOrderKey(objResultSet.getInt(DatabaseConstants.LS400_ORDR_KEY));
				objRequestDetails1058VO.setMdlName(objResultSet.getString(DatabaseConstants.LS200_MDL_NAME));
				objRequestDetails1058VO.setCustName(objResultSet.getString(DatabaseConstants.LS050_CUST_NAME));
				//Added for CR-116
				objRequestDetails1058VO.setNonLsdbFlag(objResultSet.getString(DatabaseConstants.NON_LSDB_FLAG));
				
				arlOrderNoList.add(objRequestDetails1058VO);
				
			}
			
            
            
		}
		catch (DataAccessException objDataExp) {
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
		return arlOrderNoList;
			
	}
	
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objRequestDetails1058VO
	 *            the object for creating LSDB Request
	 * @return int  the status for success or failure
	 * @throws EMDException
	 **************************************************************************/
	public static int create1058LSDBRequest(ChangeRequest1058VO objChangeRequest1058VO)
	throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058DAO:create1058LSDBRequest");
		//LogUtil.logMessage("Order No:" + objChangeRequest1058VO.getOrderNo());
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		
		int SeqNo1058 = 0;
		
		try {
			strLogUser= objChangeRequest1058VO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_CREATE_1058_LSDB_REQUEST);			 
			objCallableStatement.setInt(1, objChangeRequest1058VO.getOrderKey());
			LogUtil.logMessage("objChangeRequest1058VO.getOrderKey(): " + objChangeRequest1058VO.getOrderKey());
			objCallableStatement.setString(2, objChangeRequest1058VO.getDataLocTypeCode());
			LogUtil.logMessage("objChangeRequest1058VO.getDataLocTypeCode(): " + objChangeRequest1058VO.getDataLocTypeCode());
			objCallableStatement.setString(3, strLogUser);
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
            
			objCallableStatement.execute();
			SeqNo1058 = objCallableStatement.getInt(4);
			LogUtil.logMessage("SeqNo1058: " + SeqNo1058);

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
		}
		catch (DataAccessException objDataExp) {
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
		return SeqNo1058;
			
	}	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objChangeRequest1058VO
	 *            the object for creating NonLSDB Request
	 * @return int  the status for success or failure
	 * @throws EMDException
	 **************************************************************************/
	public static int create1058NonLSDBRequest(ChangeRequest1058VO objChangeRequest1058VO)
	throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058DAO:create1058NonLSDBRequest");
	//	LogUtil.logMessage("Order No:" + objChangeRequest1058VO.getOrderNo());
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		
		int SeqNo1058 = 0;
		
		try {
			strLogUser= objChangeRequest1058VO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_CREATE_1058_NLSDB_REQUEST);			 					
			objCallableStatement.setString(1, objChangeRequest1058VO.getOrderNo());
			LogUtil.logMessage("objChangeRequest1058VO.getOrderNo(): " + objChangeRequest1058VO.getOrderNo());
			objCallableStatement.setString(2, strLogUser);
			objCallableStatement.registerOutParameter(3, Types.INTEGER);
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			
            
			objCallableStatement.execute();
			SeqNo1058 = objCallableStatement.getInt(3);
			LogUtil.logMessage("SeqNo1058: " + SeqNo1058);
			
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
		}
		catch (DataAccessException objDataExp) {
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
		return SeqNo1058;
			
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ChangeRequest1058VO
	 * @return ArrayList contains the 1058 Request Details
	 * @throws EMDException
	 **************************************************************************/

	public static ArrayList fetch1058Details(ChangeRequest1058VO objChangeRequest1058VO)
	throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058DAO:fetch1058Details");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ResultSet objCommonResultSet  = null;
		ResultSet objAttachResultSet  = null;
		ResultSet objActionResultSet  = null;
		ResultSet objSysEnggResultSet = null;
		ResultSet objOprEnggResultSet = null;
		ResultSet objFinanceResultSet = null;
		ResultSet objPgmMgrResultSet  = null;
		ResultSet objPropMgrResultSet = null;
		ChangeRequest1058AttachmentsVO objChangeRequest1058AttachmentsVO=null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		ArrayList arlRequest1058List = new ArrayList();
		ArrayDescriptor modelArrayDescriptor = null;
		ArrayDescriptor statusArrayDescriptor = null;
		ARRAY objMdlArray = null;
		ARRAY objStatusArray = null;
		try {
				objConnection = DBHelper.prepareConnection();
				objCallableStatement = objConnection.prepareCall(EMDQueries.SP_SELECT_1058_REQUESTS);
				/*LogUtil.logMessage("objChangeRequest1058VO.getSeqNo1058():"+objChangeRequest1058VO.getSeqNo1058());
				LogUtil.logMessage("objChangeRequest1058VO.getOrderNo():"+objChangeRequest1058VO.getOrderNo());
				LogUtil.logMessage("objChangeRequest1058VO.getCustSeqNo():"+objChangeRequest1058VO.getCustSeqNo());
				LogUtil.logMessage("objChangeRequest1058VO.getAwApproval():"+objChangeRequest1058VO.getAwApproval());
				LogUtil.logMessage("objChangeRequest1058VO.getNonLsdbFlag():"+objChangeRequest1058VO.getNonLsdbFlag());
				LogUtil.logMessage("objChangeRequest1058VO.getUserID():"+objChangeRequest1058VO.getUserID());*/
				
			Connection dconn = ((DelegatingConnection) objConnection).getInnermostDelegate(); //Added for CR-123
			
			if(objChangeRequest1058VO.getSeqNo1058() <= 0 ){
				objCallableStatement.setNull(1, Types.NULL);
			}else{
				objCallableStatement.setInt(1, objChangeRequest1058VO.getSeqNo1058());
			}	
			
			if(objChangeRequest1058VO.getOrderNo() == null || "".equalsIgnoreCase(objChangeRequest1058VO.getOrderNo()) || "*".equalsIgnoreCase(objChangeRequest1058VO.getOrderNo())){
				objCallableStatement.setNull(2, Types.NULL);
			}else {
				objCallableStatement.setString(2, objChangeRequest1058VO.getOrderNo());
			}
			
			if(objChangeRequest1058VO.getCustSeqNo() <= 0 ){
				objCallableStatement.setNull(3, Types.NULL);
			}else{
				objCallableStatement.setInt(3, objChangeRequest1058VO.getCustSeqNo());
			}
			
			/*if(objChangeRequest1058VO.getMdlSeqNo() <= 0 ){
				objCallableStatement.setNull(4, Types.NULL);
			}else{
				objCallableStatement.setInt(4, objChangeRequest1058VO.getMdlSeqNo());
			}*/

			modelArrayDescriptor = new ArrayDescriptor(DatabaseConstants.IN_ARRAY, dconn);
			if(objChangeRequest1058VO.getModelSeqNos() != null )
			{
				objMdlArray = new ARRAY(modelArrayDescriptor, dconn,objChangeRequest1058VO.getModelSeqNos());
			}
			else
			{
				LogUtil.logMessage("objChangeRequest1058VO.getModelSeqNos():null");
				objMdlArray = new ARRAY(modelArrayDescriptor, dconn, null); 
			}
			objCallableStatement.setArray(4,objMdlArray);
			
		    /*Added for CR-126 */
			statusArrayDescriptor = new ArrayDescriptor(DatabaseConstants.IN_ARRAY, dconn);
			if(objChangeRequest1058VO.getStatusSeqNos() != null )
			{
				objStatusArray = new ARRAY(statusArrayDescriptor, dconn,objChangeRequest1058VO.getStatusSeqNos());
			}
			else
			{
				LogUtil.logMessage("objChangeRequest1058VO.getStatusSeqNos():null");
				objStatusArray = new ARRAY(statusArrayDescriptor, dconn, null); 
			}
			objCallableStatement.setArray(5,objStatusArray);
			/*Added for CR-126 Ends here*/  
			
			if(objChangeRequest1058VO.getAwApproval() == null 
					|| "".equalsIgnoreCase(objChangeRequest1058VO.getAwApproval())
					|| objChangeRequest1058VO.getAwApproval().equalsIgnoreCase("-1")){
				objCallableStatement.setNull(6, Types.NULL);
			}else {
				objCallableStatement.setString(6, objChangeRequest1058VO.getAwApproval());
			}
			
			if(objChangeRequest1058VO.getNonLsdbFlag() == null || "".equalsIgnoreCase(objChangeRequest1058VO.getNonLsdbFlag())){
				objCallableStatement.setNull(7, Types.NULL);
			}else {
				objCallableStatement.setString(7, objChangeRequest1058VO.getNonLsdbFlag());
			}
			
			if(objChangeRequest1058VO.getUserID() == null || "".equalsIgnoreCase(objChangeRequest1058VO.getUserID())){
				objCallableStatement.setNull(8, Types.NULL);
			}else {
				objCallableStatement.setString(8, objChangeRequest1058VO.getUserID());
			}
			
			
			
				objCallableStatement.registerOutParameter(9, OracleTypes.CURSOR);
				objCallableStatement.registerOutParameter(10, Types.INTEGER);
				objCallableStatement.registerOutParameter(11, Types.VARCHAR);
				objCallableStatement.registerOutParameter(12, Types.VARCHAR);
            
				objCallableStatement.execute();
				objCommonResultSet = (ResultSet) objCallableStatement.getObject(9);
            
		
			intLSDBErrorID = objCallableStatement.getInt(10);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(11);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(12);
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
			
			
			while(objCommonResultSet.next()){
				
				LogUtil.logMessage("ENters into objResultSet:..");
				objChangeRequest1058VO =new ChangeRequest1058VO();
								
				//Request Details Section
				objChangeRequest1058VO.setOrderNo(objCommonResultSet.getString(DatabaseConstants.LS400_ORDR_NO));
				objChangeRequest1058VO.setSeqNo1058(objCommonResultSet.getInt(DatabaseConstants.LS900_1058_SEQ_NO));
				objChangeRequest1058VO.setSpecType(objCommonResultSet.getInt(DatabaseConstants.SPEC_TYPE_SEQ_NO));
				objChangeRequest1058VO.setNonLsdbFlag(objCommonResultSet.getString(DatabaseConstants.LS900_NON_LSDB_REQ));
				objChangeRequest1058VO.setId1058(objCommonResultSet.getString(DatabaseConstants.LS900_1058_ID));
				objChangeRequest1058VO.setCustName(objCommonResultSet.getString(DatabaseConstants.LS050_CUST_NAME));
				objChangeRequest1058VO.setOrderQty(objCommonResultSet.getInt(DatabaseConstants.ORDR_QTY));
				objChangeRequest1058VO.setStatusSeqNo(objCommonResultSet.getInt(DatabaseConstants.LS902_1058_STATUS_SEQ_NO));
				objChangeRequest1058VO.setGenDesc(objCommonResultSet.getString(DatabaseConstants.LS900_GEN_DESC));
				objChangeRequest1058VO.setStatusDesc(objCommonResultSet.getString(DatabaseConstants.LS902_1058_STATUS_DESC));
				objChangeRequest1058VO.setActualSellPrice(objCommonResultSet.getBigDecimal(DatabaseConstants.LS900_ACTUAL_SELL_PRICE));
				objChangeRequest1058VO.setCustMdlName(objCommonResultSet.getString(DatabaseConstants.LS200_MDL_NAME));
				objChangeRequest1058VO.setMdlName(objCommonResultSet.getString(DatabaseConstants.LS200_MDL_NAME));
				objChangeRequest1058VO.setSpecRev(objCommonResultSet.getString(DatabaseConstants.LS900_SPEC_REVISION));
				objChangeRequest1058VO.setMdlSeqNo(objCommonResultSet.getInt(DatabaseConstants.LS200_MDL_SEQ_NO));
				objChangeRequest1058VO.setOrderKey(objCommonResultSet.getInt(DatabaseConstants.LS400_ORDR_KEY));
				objChangeRequest1058VO.setDataLocType(objCommonResultSet.getString(DatabaseConstants.LS150_DATA_LOC_TYPE_CODE));
				//Added for CR-116
				objChangeRequest1058VO.setLagacy1058Flag(objCommonResultSet.getString(DatabaseConstants.LS900_LEGACY_1058_FLAG));
				objChangeRequest1058VO.setLagacyFileLoc(objCommonResultSet.getString(DatabaseConstants.LS924_1058_FILE_LOC));
				
				//Added for CR-120
				objChangeRequest1058VO.setCreatedOn(objCommonResultSet.getString(DatabaseConstants.CREATED_ON));
				
				//Added for CR-126
				objChangeRequest1058VO.setUserID(objCommonResultSet.getString(DatabaseConstants.LS010_USER_ID));
				objChangeRequest1058VO.setNumber1058(objCommonResultSet.getString(DatabaseConstants.LS900_LEGACY_1058_ID));
				objChangeRequest1058VO.setCustomer(objCommonResultSet.getString(DatabaseConstants.LS050_CUST_NAME));
				objChangeRequest1058VO.setModel(objCommonResultSet.getString(DatabaseConstants.LS200_MDL_NAME));
				
				//User Details Section
				
				if(objCommonResultSet.getString(DatabaseConstants.LS010_FIRSTNAME)!= null 
					&& objCommonResultSet.getString(DatabaseConstants.LS010_LASTNAME)!= null){
					objChangeRequest1058VO.setUserName(objCommonResultSet.getString(DatabaseConstants.LS010_FIRSTNAME).trim()
							+" "+objCommonResultSet.getString(DatabaseConstants.LS010_LASTNAME).trim());
				}else{
					  objChangeRequest1058VO.setUserName("");			
				}
				
				objChangeRequest1058VO.setContactNo(objCommonResultSet.getString(DatabaseConstants.LS010_CONTACT_NUMBER));
				
				//Requested Change Deatails Section
				objChangeRequest1058VO.setReqFrom(objCommonResultSet.getString(DatabaseConstants.LS906_CATEGORY_NAME));
				objChangeRequest1058VO.setReqTypeSeqNo(objCommonResultSet.getInt(DatabaseConstants.LS901_REQ_TYPE_SEQ_NO));
				objChangeRequest1058VO.setReqTypeDesc(objCommonResultSet.getString(DatabaseConstants.LS901_REQ_TYPE_DESC));
				objChangeRequest1058VO.setGenDesc(objCommonResultSet.getString(DatabaseConstants.LS900_GEN_DESC));
				objChangeRequest1058VO.setCategorySeqNo(objCommonResultSet.getInt(DatabaseConstants.LS906_CATEGORY_SEQ_NO));
				objChangeRequest1058VO.setCategoryName(objCommonResultSet.getString(DatabaseConstants.LS906_CATEGORY_NAME));
				//Attachment Deatils
				objAttachResultSet 		 = (ResultSet) objCommonResultSet.getObject("ATTACHMENTS");
				ArrayList arlImageList   = new ArrayList();
				while (objAttachResultSet.next()) {
				LogUtil.logMessage("Inside objAttachResultSet:");
				objChangeRequest1058AttachmentsVO =new ChangeRequest1058AttachmentsVO();
				objChangeRequest1058AttachmentsVO.setImgSeqNo(objAttachResultSet.getInt(DatabaseConstants.LS170_IMG_SEQ_NO));
				objChangeRequest1058AttachmentsVO.setImgName(objAttachResultSet.getString(DatabaseConstants.LS907_IMAGE_NAME));
				
				
				//Added for CR-135 -- To fetch PDF Attachments
				//objChangeRequest1058AttachmentsVO.imageBlob = objAttachResultSet.getBlob(DatabaseConstants.LS170_IMG) ;
				//objChangeRequest1058AttachmentsVO.setImage(objAttachResultSet.getString(DatabaseConstants.LS170_IMG));
				arlImageList.add(objChangeRequest1058AttachmentsVO);
				}
				objChangeRequest1058VO.setAttachmentsList(arlImageList);
				DBHelper.closeSQLObjects(objAttachResultSet, null, null);
				
				//Requested Effectivity Details
				objChangeRequest1058VO.setUnitNumber(objCommonResultSet.getString(DatabaseConstants.LS908_UNIT_NO));
				objChangeRequest1058VO.setRoadNumber(objCommonResultSet.getString(DatabaseConstants.LS908_ROAD_NO));
				objChangeRequest1058VO.setMcrNumber(objCommonResultSet.getString(DatabaseConstants.LS908_MCR_NO));
				objChangeRequest1058VO.setMcrReq(objCommonResultSet.getInt(DatabaseConstants.LS908_MCR_REQ));
				
				/*Representatives and Section Deatails Starts Here*/
				
				//Cursor of System Engineering section
				objSysEnggResultSet = (ResultSet) objCommonResultSet.getObject("SYSTEM_ENGINEERING_SECTION");
				while (objSysEnggResultSet.next()) {
					
				LogUtil.logMessage("Inside objSysEnggResultSet:");
				
				objChangeRequest1058VO.setSysEnggFirstName(objSysEnggResultSet.getString(DatabaseConstants.LS010_FIRSTNAME));
				objChangeRequest1058VO.setSysEnggLastName(objSysEnggResultSet.getString(DatabaseConstants.LS010_LASTNAME));
				objChangeRequest1058VO.setSysEnggUserName(objSysEnggResultSet.getString(DatabaseConstants.LS010_FIRSTNAME).trim()
														+" "+objSysEnggResultSet.getString(DatabaseConstants.LS010_LASTNAME).trim());
				objChangeRequest1058VO.setSysEnggStatusSeq(objSysEnggResultSet.getInt(DatabaseConstants.LS905_SECTION_STATUS_SEQ ));
				objChangeRequest1058VO.setSysEnggStatusDesc(objSysEnggResultSet.getString(DatabaseConstants.LS905_SECTION_STATUS_DESC));
				objChangeRequest1058VO.setSysEnggUserID(objSysEnggResultSet.getString(DatabaseConstants.LS010_USER_ID));
				}
				DBHelper.closeSQLObjects(objSysEnggResultSet, null, null);
				
				objChangeRequest1058VO.setSysEnggDateReceived(objCommonResultSet.getString(DatabaseConstants.LS919_DATE_RECEIVED));
				objChangeRequest1058VO.setSysEnggDateCompleted(objCommonResultSet.getString(DatabaseConstants.LS919_DATE_COMPLETED));
				objChangeRequest1058VO.setSystemEngComment(objCommonResultSet.getString(DatabaseConstants.LS919_SYSTEM_ENG_COMMENT));
				objChangeRequest1058VO.setPartNoAdded(objCommonResultSet.getString(DatabaseConstants.LS919_PART_NO_ADDED));
				objChangeRequest1058VO.setPartNoDeleted(objCommonResultSet.getString(DatabaseConstants.LS919_PART_NO_DELETED));
				objChangeRequest1058VO.setDesignHrs(objCommonResultSet.getString(DatabaseConstants.LS919_DESIGN_HRS));
				objChangeRequest1058VO.setChangeAffectsWeight(objCommonResultSet.getString(DatabaseConstants.LS919_CHANGE_AFFECTS_WEIGHT));
				objChangeRequest1058VO.setChangeAffectsClear(objCommonResultSet.getString(DatabaseConstants.LS919_CHANGE_AFFECTS_CLEAR));
				objChangeRequest1058VO.setDraftingHrs(objCommonResultSet.getString(DatabaseConstants.LS919_DRAFTING_HRS));
				objChangeRequest1058VO.setDrawingDueDate(objCommonResultSet.getString(DatabaseConstants.LS919_DRAWING_DUE_DATE));
				objChangeRequest1058VO.setCompletionDate(objCommonResultSet.getString(DatabaseConstants.LS919_COMPLETION_DATE));
				objChangeRequest1058VO.setWorkOrderUSD(objCommonResultSet.getBigDecimal(DatabaseConstants.LS919_WORK_ORDER_USD));
				objChangeRequest1058VO.setStationAffected(objCommonResultSet.getString(DatabaseConstants.LS919_STATION_AFFECTED));
				objChangeRequest1058VO.setOwnerFlag(objCommonResultSet.getString(DatabaseConstants.OWNER_FLAG));
				//Cursor of OPERATION ENGINEERING SECTION
				
				objOprEnggResultSet = (ResultSet) objCommonResultSet.getObject("OPERATIONS_SECTION");
				
				while (objOprEnggResultSet.next()) {
				LogUtil.logMessage("Inside objOprEnggResultSet:");
				objChangeRequest1058VO.setOprEnggFirstName(objOprEnggResultSet.getString(DatabaseConstants.LS010_FIRSTNAME));
				objChangeRequest1058VO.setOprEnggLastName(objOprEnggResultSet.getString(DatabaseConstants.LS010_LASTNAME));
				objChangeRequest1058VO.setOprEnggUserName(objOprEnggResultSet.getString(DatabaseConstants.LS010_FIRSTNAME).trim()
						+" "+objOprEnggResultSet.getString(DatabaseConstants.LS010_LASTNAME).trim());
				objChangeRequest1058VO.setOprEnggStatusSeq(objOprEnggResultSet.getInt(DatabaseConstants.LS905_SECTION_STATUS_SEQ ));
				objChangeRequest1058VO.setOprEnggStatusDesc(objOprEnggResultSet.getString(DatabaseConstants.LS905_SECTION_STATUS_DESC));
				objChangeRequest1058VO.setOprEnggUserID(objOprEnggResultSet.getString(DatabaseConstants.LS010_USER_ID));
				}
				DBHelper.closeSQLObjects(objOprEnggResultSet, null, null);
				
				objChangeRequest1058VO.setOprEnggDateReceived(objCommonResultSet.getString(DatabaseConstants.LS920_DATE_RECEIVED));
				objChangeRequest1058VO.setOprEnggDateCompleted(objCommonResultSet.getString(DatabaseConstants.LS920_DATE_COMPLETED));
				objChangeRequest1058VO.setOperationComments(objCommonResultSet.getString(DatabaseConstants.LS920_OPERATION_COMMENTS));
				objChangeRequest1058VO.setDisposExcessMaterial(objCommonResultSet.getString(DatabaseConstants.LS920_DISPOS_EXCESS_MATERIAL));
				objChangeRequest1058VO.setLaborImpact(objCommonResultSet.getString(DatabaseConstants.LS920_LABOR_IMPACT));
				objChangeRequest1058VO.setSupplierAffectedCost(objCommonResultSet.getString(DatabaseConstants.LS920_SUPPLIER_AFFECTED_COST));
				objChangeRequest1058VO.setRecEffectivityDel(objCommonResultSet.getString(DatabaseConstants.LS920_REC_EFFECTIVITY_DEL));
				objChangeRequest1058VO.setToolingCostUSD(objCommonResultSet.getBigDecimal(DatabaseConstants.LS920_TOOLING_COST_USD));
				objChangeRequest1058VO.setScrapCostUSD(objCommonResultSet.getBigDecimal(DatabaseConstants.LS920_SCRAP_COST_USD));
				objChangeRequest1058VO.setReworkCost(objCommonResultSet.getBigDecimal(DatabaseConstants.LS920_REWORK_COST));
				
				
				//Cursor of FINANCE SECTION
				objFinanceResultSet = (ResultSet) objCommonResultSet.getObject("FINANCE_SECTION");
				while (objFinanceResultSet.next()) {
				LogUtil.logMessage("Inside objFinanceResultSet:");
				objChangeRequest1058VO.setFinanceFirstName(objFinanceResultSet.getString(DatabaseConstants.LS010_FIRSTNAME));
				objChangeRequest1058VO.setFinanceLastName(objFinanceResultSet.getString(DatabaseConstants.LS010_LASTNAME));
				objChangeRequest1058VO.setFinanceUserName(objFinanceResultSet.getString(DatabaseConstants.LS010_FIRSTNAME).trim()
						+" "+objFinanceResultSet.getString(DatabaseConstants.LS010_LASTNAME).trim());
				objChangeRequest1058VO.setFinanceStatusSeq(objFinanceResultSet.getInt(DatabaseConstants.LS905_SECTION_STATUS_SEQ ));
				objChangeRequest1058VO.setFinanceStatusDesc(objFinanceResultSet.getString(DatabaseConstants.LS905_SECTION_STATUS_DESC));
				objChangeRequest1058VO.setFinanceUserID(objFinanceResultSet.getString(DatabaseConstants.LS010_USER_ID));
				}
				DBHelper.closeSQLObjects(objFinanceResultSet, null, null);
				
				objChangeRequest1058VO.setFinanceDateReceived(objCommonResultSet.getString(DatabaseConstants.LS921_DATE_RECEIVED));
				objChangeRequest1058VO.setFinanceDateCompleted(objCommonResultSet.getString(DatabaseConstants.LS921_DATE_COMPLETED));
				objChangeRequest1058VO.setFinanceComments(objCommonResultSet.getString(DatabaseConstants.LS921_FINANCE_COMMENT));
				objChangeRequest1058VO.setProdCostChange(objCommonResultSet.getString(DatabaseConstants.LS921_PROD_COST_CHANGE));
				objChangeRequest1058VO.setProdCostChangeSup(objCommonResultSet.getString(DatabaseConstants.LS921_PROD_COST_CHANGE_SUP));
				
				//Cursor of PROGRAM MANAGER SECTION
				objPgmMgrResultSet = (ResultSet) objCommonResultSet.getObject("PROGRAM_MANAGER_SECTION");
				while (objPgmMgrResultSet.next()) {
				LogUtil.logMessage("Inside objPgmMgrResultSet:");
				objChangeRequest1058VO.setPgmMgrFirstName(objPgmMgrResultSet.getString(DatabaseConstants.LS010_FIRSTNAME));
				objChangeRequest1058VO.setPgmMgrLastName(objPgmMgrResultSet.getString(DatabaseConstants.LS010_LASTNAME));
				objChangeRequest1058VO.setPgmMgrUserName(objPgmMgrResultSet.getString(DatabaseConstants.LS010_FIRSTNAME).trim()
						+" "+objPgmMgrResultSet.getString(DatabaseConstants.LS010_LASTNAME).trim());
				objChangeRequest1058VO.setPgmMgrStatusSeq(objPgmMgrResultSet.getInt(DatabaseConstants.LS905_SECTION_STATUS_SEQ ));
				objChangeRequest1058VO.setPgmMgrStatusDesc(objPgmMgrResultSet.getString(DatabaseConstants.LS905_SECTION_STATUS_DESC));
				objChangeRequest1058VO.setPgmMgrUserID(objPgmMgrResultSet.getString(DatabaseConstants.LS010_USER_ID));
				}
				DBHelper.closeSQLObjects(objPgmMgrResultSet, null, null);
				
				objChangeRequest1058VO.setProgManagerDateReceived(objCommonResultSet.getString(DatabaseConstants.LS922_DATE_RECEIVED));
				objChangeRequest1058VO.setProgManagerDateCompleted(objCommonResultSet.getString(DatabaseConstants.LS922_DATE_COMPLETED));
				objChangeRequest1058VO.setProgManagerComments(objCommonResultSet.getString(DatabaseConstants.LS922_PROG_MANAGER_COMMENT));
				
				
				//Cursor of PROPOSAL MANAGER SECTION
				objPropMgrResultSet = (ResultSet) objCommonResultSet.getObject("PROPOSAL_MANAGER_SECTION");
				while (objPropMgrResultSet.next()) {
				LogUtil.logMessage("Inside objPropMgrResultSet:");
				objChangeRequest1058VO.setPropMgrFirstName(objPropMgrResultSet.getString(DatabaseConstants.LS010_FIRSTNAME));
				objChangeRequest1058VO.setPropMgrLastName(objPropMgrResultSet.getString(DatabaseConstants.LS010_LASTNAME));
				objChangeRequest1058VO.setPropMgrUserName(objPropMgrResultSet.getString(DatabaseConstants.LS010_FIRSTNAME).trim()
						+" "+objPropMgrResultSet.getString(DatabaseConstants.LS010_LASTNAME).trim());
				objChangeRequest1058VO.setPropMgrStatusSeq(objPropMgrResultSet.getInt(DatabaseConstants.LS905_SECTION_STATUS_SEQ ));
				objChangeRequest1058VO.setPropMgrStatusDesc(objPropMgrResultSet.getString(DatabaseConstants.LS905_SECTION_STATUS_DESC));
				objChangeRequest1058VO.setPropMgrUserID(objPropMgrResultSet.getString(DatabaseConstants.LS010_USER_ID));
				}
				DBHelper.closeSQLObjects(objPropMgrResultSet, null, null);
				
				objChangeRequest1058VO.setPropManagerDateReceived(objCommonResultSet.getString(DatabaseConstants.LS923_DATE_RECEIVED));
				objChangeRequest1058VO.setPropManagerDateCompleted(objCommonResultSet.getString(DatabaseConstants.LS923_DATE_COMPLETED));
				objChangeRequest1058VO.setPropManagerComments(objCommonResultSet.getString(DatabaseConstants.LS923_PROP_MANAGER_COMMENT));
				objChangeRequest1058VO.setSellpriceCustomer(objCommonResultSet.getBigDecimal(DatabaseConstants.LS923_SELL_PRICE_CUSTOMER));
				objChangeRequest1058VO.setMarkUp(objCommonResultSet.getBigDecimal(DatabaseConstants.LS923_MARK_UP));
				objChangeRequest1058VO.setCustomerApprovalReq(objCommonResultSet.getString(DatabaseConstants.LS923_CUSTOMER_APPROVAL_REQ));
				objChangeRequest1058VO.setCustomerDecision(objCommonResultSet.getString(DatabaseConstants.LS923_CUSTOMER_DECISION));
				objChangeRequest1058VO.setCountDays(objCommonResultSet.getInt(DatabaseConstants.COUNT_OF_DAYS));
				objChangeRequest1058VO.setCustomerDecisionDate(objCommonResultSet.getString(DatabaseConstants.LS923_CUSTOMER_DECISION_DATE));
				objChangeRequest1058VO.setCustApprovalDet(objCommonResultSet.getString(DatabaseConstants.LS923_CUSTOMER_APPROVAL_DET));

				LogUtil.logMessage("PropMgrResultSet: objChangeRequest1058VO.getPropManagerComments" + objChangeRequest1058VO.getPropManagerComments());
				
				/*Representatives and Section Deatails Ends Here*/
				
				//Cursor of ActionRecord Section				
				objActionResultSet = (ResultSet) objCommonResultSet.getObject("ACTION_RECORDS");
				ArrayList arlActionList      = new ArrayList();
				while (objActionResultSet.next()) {
				LogUtil.logMessage("Inside objActionResultSet");
				
				ChangeRequest1058VO objActionDetails1058VO =new ChangeRequest1058VO();
				objActionDetails1058VO.setUserName(objActionResultSet.getString(DatabaseConstants.LS010_FIRSTNAME).trim()+" "+objActionResultSet.getString(DatabaseConstants.LS010_LASTNAME).trim());
				objActionDetails1058VO.setSection(objActionResultSet.getString(DatabaseConstants.LS904_SECTION_NAME));
				objActionDetails1058VO.setAction(objActionResultSet.getString(DatabaseConstants.LS905_SECTION_STATUS_DESC));
				objActionDetails1058VO.setActionDate(objActionResultSet.getString(DatabaseConstants.LS909_ACTION_DATE));
				arlActionList.add(objActionDetails1058VO);
				
				}
				objChangeRequest1058VO.setActionList(arlActionList);
				DBHelper.closeSQLObjects(objActionResultSet, null, null);
				
				arlRequest1058List.add(objChangeRequest1058VO);
				LogUtil.logMessage("objChangeRequest1058VO.getSeqNo1058():"+objChangeRequest1058VO.getSeqNo1058());
			
			
				
			
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
		return arlRequest1058List;
			
	}
	

	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @return arrayList the list contains the Representative details
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList fetchRepresentativeDetails(UserVO objUserVO)
	throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058DAO:fetchRepresentativeDetails");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ResultSet objResultSet = null;
		ResultSet objUserListResultSet = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		ChangeRequest1058VO objRequestDetails1058VO=null;
		ArrayList arlRepresentativeList = new ArrayList();
		ArrayList arlOprUserList 		= new ArrayList();
		ArrayList arlFinrUserList 		= new ArrayList();
		ArrayList arlPgmUserList 		= new ArrayList();
		ArrayList arlPropmUserList 		= new ArrayList();
		ArrayList arlOtherUserList 	 	= new ArrayList();
		
		try {
				objConnection = DBHelper.prepareConnection();
				objCallableStatement = objConnection.prepareCall(AdmnQueries.SP_FETCH_USER);
				LogUtil.logMessage("objUserVO.getUserID():"+objUserVO.getUserID());
				if(objUserVO.getUserID() == null || "".equalsIgnoreCase(objUserVO.getUserID())){
					objCallableStatement.setNull(1, Types.NULL);
				}else {
					objCallableStatement.setString(1, objUserVO.getUserID());
				}
				
				objCallableStatement.setInt(2, objUserVO.getOrderbyFlag());
				objCallableStatement.registerOutParameter(3, OracleTypes.CURSOR);
				objCallableStatement.registerOutParameter(4, Types.INTEGER);
				objCallableStatement.registerOutParameter(5, Types.VARCHAR);
				objCallableStatement.registerOutParameter(6, Types.VARCHAR);
				
				objCallableStatement.execute();
				
				objResultSet = (ResultSet) objCallableStatement.getObject(3);
				LogUtil
				.logMessage("Inside the fetchUsers method of UserDAO :resultSet"
						+ objResultSet);
				
				intLSDBErrorID = (int) objCallableStatement.getInt(4);
				strOracleCode = (String) objCallableStatement.getString(5);
				strErrorMessage = (String) objCallableStatement.getString(6);
				
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
					objConnection.rollback();
					throw new ApplicationException(objErrorInfo);
				}
				
			objRequestDetails1058VO =new ChangeRequest1058VO();
			
			while(objResultSet.next()){
				
				LogUtil.logMessage("Enters into objResultSet:");
				
				ChangeRequest1058VO objChangeRequest1058VO =new ChangeRequest1058VO();
				objChangeRequest1058VO.setFirstName(objResultSet.getString(DatabaseConstants.LS010_FIRSTNAME));
				objChangeRequest1058VO.setLastName(objResultSet.getString(DatabaseConstants.LS010_LASTNAME));
				objChangeRequest1058VO.setUserID(objResultSet.getString(DatabaseConstants.LS010_USER_ID));
				objChangeRequest1058VO.setRoleID(objResultSet.getString(DatabaseConstants.LS120_ROLE_ID));
				
				if(objChangeRequest1058VO.getRoleID().trim().equals("OPR")){
					arlOprUserList.add(objChangeRequest1058VO);
				}else if(objChangeRequest1058VO.getRoleID().trim().equals("FINR")){
					arlFinrUserList.add(objChangeRequest1058VO);
				}else if(objChangeRequest1058VO.getRoleID().trim().equals("PGM")){
					arlPgmUserList.add(objChangeRequest1058VO);
				}else if(objChangeRequest1058VO.getRoleID().trim().equals("PROPM")){
					arlPropmUserList.add(objChangeRequest1058VO);
				}else if(!objChangeRequest1058VO.getRoleID().trim().equals("ASM") 
					 && !objChangeRequest1058VO.getRoleID().trim().equals("DSBD") ){
					arlOtherUserList.add(objChangeRequest1058VO);
				}	
			
			}
			objRequestDetails1058VO.setOpRepList(arlOprUserList);
			objRequestDetails1058VO.setFinRepList(arlFinrUserList);
			objRequestDetails1058VO.setPgmMgrList(arlPgmUserList);
			objRequestDetails1058VO.setPropMgrList(arlPropmUserList);
			objRequestDetails1058VO.setSysEnggList(arlOtherUserList);
			arlRepresentativeList.add(objRequestDetails1058VO);
			
			DBHelper.closeSQLObjects(objResultSet, null, null);
			LogUtil.logMessage("arlRepresentativeList.size():"+arlRepresentativeList.size());
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
		return arlRepresentativeList;
			
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objChangeRequest1058VO
	 *            The Object for insertRepresentative
	 * @return int The status for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int insertRepresentative(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException {
		LogUtil.logMessage("Entering ChangeRequest1058DAO.insertRepresentative");
		
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intStatusCode = 0;
		ArrayDescriptor arrdesc = null;
		ARRAY arr = null;
		int intLSDBErrorID = 0;
		String strMessage = null;
		String strLogUser = "";
		try {
			strLogUser = objChangeRequest1058VO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_INSERT_1058_USER_LIST);
			Connection dconn = ((DelegatingConnection) objConnection).getInnermostDelegate(); //Added for CR-123 
			
			arrdesc = new ArrayDescriptor(DatabaseConstants.STR_ARRAY,
					dconn);
			arr = new ARRAY(arrdesc, dconn,objChangeRequest1058VO.getRepList());
			objCallableStatement.setInt(1, objChangeRequest1058VO.getSeqNo1058());
			objCallableStatement.setArray(2, arr);
			if (objChangeRequest1058VO.getSaveOnlyFlag() != null) 
				objCallableStatement.setString(3, objChangeRequest1058VO.getSaveOnlyFlag());
			else
				objCallableStatement.setNull(3, Types.NULL);
			objCallableStatement.setString(4, objChangeRequest1058VO.getUserID());
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			
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
			LogUtil
			.logMessage("Enters into DataAccessException ChangeRequest1058DAO.insertRepresentative");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in ChangeRequest1058DAO.insertRepresentative"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException ChangeRequest1058DAO.insertRepresentative");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into catch block in ChangeRequest1058DAO.insertRepresentative"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception ChangeRequest1058DAO.insertRepresentative");
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
				LogUtil
				.logMessage("Enters into Exception ChangeRequest1058DAO.insertRepresentative");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return intStatusCode;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objChangeRequest1058VO
	 * @return int The status for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int complete(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException {
		LogUtil.logMessage("Entering ChangeRequest1058DAO.complete");
		
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intStatusCode = 0;
		int intLSDBErrorID = 0;
		String strMessage = null;
		String strLogUser = "";
		try {
			strLogUser = objChangeRequest1058VO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_UPDATE_1058_STATUS);
			
			objCallableStatement.setInt(1, objChangeRequest1058VO.getSeqNo1058());
			if(objChangeRequest1058VO.getSectionSeqNo() == 0 ){
				objCallableStatement.setNull(2, Types.NULL);
			}else{
				objCallableStatement.setInt(2, objChangeRequest1058VO.getSectionSeqNo());
			}
			if(objChangeRequest1058VO.getSectionStatus() == 0 ){
				objCallableStatement.setNull(3, Types.NULL);
			}else{
				objCallableStatement.setInt(3, objChangeRequest1058VO.getSectionStatus());
			}
			objCallableStatement.setString(4, objChangeRequest1058VO.getUserID());
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			
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
			LogUtil
			.logMessage("Enters into DataAccessException ChangeRequest1058DAO.complete");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in ChangeRequest1058DAO.complete"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException ChangeRequest1058DAO.complete");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into catch block in ChangeRequest1058DAO.complete"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception ChangeRequest1058DAO.complete");
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
				LogUtil
				.logMessage("Enters into Exception ChangeRequest1058DAO.complete");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return intStatusCode;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ChangeRequest1058VO
	 * @return ArrayList contains the 1058 Status Details
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList fetch1058Status(ChangeRequest1058VO objChangeRequest1058VO)throws EMDException {
		LogUtil.logMessage("Entering ChangeRequest1058DAO.fetch1058Status");
		Connection objConnnection = null;
		ArrayList arlStatusList = new ArrayList();
		CallableStatement objCallableStatement = null;
		ResultSet rsStatus = null;
		int intLSDBErrorID = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strLogUser = "";
		
		try {
			strLogUser = objChangeRequest1058VO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in DAO :" + objConnnection);
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_SELECT_1058_STATUS);
			
			objCallableStatement.setString(1, objChangeRequest1058VO.getUserID());
			objCallableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(3, Types.INTEGER);
			objCallableStatement.registerOutParameter(4, Types.VARCHAR);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);			
			objCallableStatement.execute();
			
			rsStatus = (ResultSet) objCallableStatement.getObject(2);
			LogUtil
			.logMessage("Inside the fetch1058Status method of ChangeRequest1058DAO :resultSet"
					+ rsStatus);
			
			intLSDBErrorID = (int) objCallableStatement.getInt(3);
			strOracleCode = (String) objCallableStatement.getString(4);
			strErrorMessage = (String) objCallableStatement.getString(5);
			
			
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
			
			while (rsStatus.next()) {									
					objChangeRequest1058VO = new ChangeRequest1058VO();
					objChangeRequest1058VO.setStatusDesc(rsStatus.getString(DatabaseConstants.LS902_1058_STATUS_DESC));
					LogUtil.logMessage(objChangeRequest1058VO.getStatusDesc());
					objChangeRequest1058VO.setStatusSeqNo(rsStatus.getInt(DatabaseConstants.LS902_1058_STATUS_SEQ_NO));
					LogUtil.logMessage(" "+ objChangeRequest1058VO.getStatusSeqNo());
					arlStatusList.add(objChangeRequest1058VO);
					
			}
			LogUtil.logMessage("arlStatusList in DAO :"
					+ arlStatusList.size());
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into catch block in ChangeRequest1058DAO.fetchStatus"
					+ objDataExp.getErrorInfo().getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ChangeRequest1058DAO.fetchStatus"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ChangeRequest1058DAO.fetchStatus");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
			
		} finally {
			try {
				if (rsStatus != null)
					rsStatus.close();
				DBHelper.closeSQLObjects(rsStatus,
						objCallableStatement, objConnnection);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in ChangeRequest1058DAO.fetchStatus");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
				
			}
			
		}
		return arlStatusList;
	}
		
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ChangeRequest1058VO
	 * @return ArrayList contains the NonLsdbClause Details
	 * @throws EMDException
	 **************************************************************************/
	

	public static ArrayList fetchNLsdbClauseDetails(ChangeRequest1058VO objChangeRequest1058VO)
	throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058DAO:fetchNLsdbClauseDetails");
		
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ResultSet objNLsdbClauseResultSet = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		
		int intLSDBErrorID = 0;
		String strLogUser = "";
		ArrayList arlNLsdbClauseDetailsList=new ArrayList();
		
		try {
			strLogUser= objChangeRequest1058VO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_SEL_1058_NLSDB_CLA_CHNG_REQ);
			 
			if(objChangeRequest1058VO.getSeqNo1058() == 0 ){
				objCallableStatement.setNull(1, Types.NULL);
			}else{
			objCallableStatement.setInt(1, objChangeRequest1058VO.getSeqNo1058());
			}
			
			if(objChangeRequest1058VO.getClaChngReqSeq() == 0 ){
				objCallableStatement.setNull(2, Types.NULL);
			}else{
			objCallableStatement.setInt(2, objChangeRequest1058VO.getClaChngReqSeq());
			}
			
			if(objChangeRequest1058VO.getUserID() == null || "".equalsIgnoreCase(objChangeRequest1058VO.getUserID())){
				objCallableStatement.setNull(3, Types.NULL);
			}else {
			objCallableStatement.setString(3, objChangeRequest1058VO.getUserID());
			}
			
			objCallableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
            
			objCallableStatement.execute();
			objNLsdbClauseResultSet = (ResultSet) objCallableStatement.getObject(4);
            
			LogUtil
			.logMessage("ResultSet Value in ChangeRequest1058DAO:fetchNLsdbClauseDetails:"
					+ objNLsdbClauseResultSet);
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
				
				if (objNLsdbClauseResultSet != null) {
					
					LogUtil.logMessage("ChangeRequest1058DAO : objNLsdbClauseResultSet");
				
					while(objNLsdbClauseResultSet.next()){
						
							objChangeRequest1058VO = new ChangeRequest1058VO();
							objChangeRequest1058VO.setChngFromSpecSec(objNLsdbClauseResultSet.getString(DatabaseConstants.LS918_CHANGE_FROM_SPEC_SECT));
							objChangeRequest1058VO.setChngFromClaDesc(objNLsdbClauseResultSet.getString(DatabaseConstants.LS918_CHANGE_FROM_CLA_DESC));
							objChangeRequest1058VO.setChngFromEnggData(objNLsdbClauseResultSet.getString(DatabaseConstants.LS918_CHANGE_FROM_ENG_DATA));
							objChangeRequest1058VO.setChngToSpecSec(objNLsdbClauseResultSet.getString(DatabaseConstants.LS918_CHANGE_TO_SPEC_SECTION));
							objChangeRequest1058VO.setChngToClaDesc(objNLsdbClauseResultSet.getString(DatabaseConstants.LS918_CHANGE_TO_CLA_DESC));
							objChangeRequest1058VO.setChngToEnggData(objNLsdbClauseResultSet.getString(DatabaseConstants.LS918_CHANGE_TO_ENG_DATA));
							objChangeRequest1058VO.setNLsdbReason(objNLsdbClauseResultSet.getString(DatabaseConstants.LS918_REASON));
							
							arlNLsdbClauseDetailsList.add(objChangeRequest1058VO);
					}		
					
					LogUtil.logMessage("arlNLsdbClauseDetailsList :" + arlNLsdbClauseDetailsList.size());
					}
					
				
				
			
            
		}
		catch (DataAccessException objDataExp) {
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
		return arlNLsdbClauseDetailsList;
			
	}

	//----@cr110---@108447---start
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objChangeRequest1058VO
	 *            the object for creating NonLSDB Request
	 * @return int the status for success or failure
	 * @throws EMDException
	 **************************************************************************/
	public static int addClauseNonLsdb(
			ChangeRequest1058VO objChangeRequest1058VO) throws EMDException {
		LogUtil
				.logMessage("Enters into ChangeRequest1058DAO:addClauseNonLsdb");
		// LogUtil.logMessage("Order No:" +
		// objChangeRequest1058VO.getOrderNo());
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intStatusCode = 0;
		int intLSDBErrorID = 0;
		String strLogUser = "";

		try {
			strLogUser = objChangeRequest1058VO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
					.prepareCall(EMDQueries.SP_INS_1058_NLSDB_CLA_CHNG_REQ);
			
			objCallableStatement.setInt(1,objChangeRequest1058VO.getSeqNo1058());
			objCallableStatement.setString(2,objChangeRequest1058VO.getChangeFromSpec());
			objCallableStatement.setString(3,objChangeRequest1058VO.getChangeFromClaDesc());
			objCallableStatement.setString(4,objChangeRequest1058VO.getChangeFromEngData());
			objCallableStatement.setString(5,objChangeRequest1058VO.getChangeToSpec());
			objCallableStatement.setString(6,objChangeRequest1058VO.getChangeToClaDesc());
			objCallableStatement.setString(7,objChangeRequest1058VO.getChangeToEngData());
			objCallableStatement.setString(8,objChangeRequest1058VO.getChangeClaReason());
			objCallableStatement.setString(9,strLogUser);
			
			objCallableStatement.registerOutParameter(10,Types.INTEGER);
			objCallableStatement.registerOutParameter(11,Types.VARCHAR);
			objCallableStatement.registerOutParameter(12,Types.VARCHAR);			

			intStatusCode = objCallableStatement.executeUpdate();
			LogUtil.logMessage("Update Result:" + intStatusCode);
			if (intStatusCode > 0) {
				intStatusCode = 0;
			}
			LogUtil.logMessage("Status Update" + intStatusCode);
	
			intLSDBErrorID = objCallableStatement.getInt(10);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(11);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(12);

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
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objChangeRequest1058VO
	 *            the object for creating NonLSDB Request
	 * @return int the status for success or failure
	 * @throws EMDException
	 **************************************************************************/
	public static int deleteClauseNonLsdb(
			ChangeRequest1058VO objChangeRequest1058VO) throws EMDException {
		LogUtil
				.logMessage("Enters into ChangeRequest1058DAO:deleteClauseNonLsdb");
	
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		int intStatusCode = 0;
		String strLogUser = "";

		try {
			strLogUser = objChangeRequest1058VO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
					.prepareCall(EMDQueries.SP_DEL_1058_NLSDB_CLA_CHNG_REQ);
			
			objCallableStatement.setInt(1,objChangeRequest1058VO.getSeqNo1058());
			objCallableStatement.setInt(2,objChangeRequest1058VO.getClaChangeReqSeqNo());
			objCallableStatement.setString(3,objChangeRequest1058VO.getUserID());
			
			objCallableStatement.registerOutParameter(4,Types.INTEGER);
			objCallableStatement.registerOutParameter(5,Types.VARCHAR);
			objCallableStatement.registerOutParameter(6,Types.VARCHAR);
			
			intStatusCode = objCallableStatement.executeUpdate();
			LogUtil.logMessage("Update Result:" + intStatusCode);
			if (intStatusCode > 0) {
				intStatusCode = 0;
			}
			LogUtil.logMessage("Status Update" + intStatusCode);
	
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
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objChangeRequest1058VO
	 *            the object for creating NonLSDB Request
	 * @return int the status for success or failure
	 * @throws EMDException
	 **************************************************************************/
	public static int updateClauseNonLsdb(
			ChangeRequest1058VO objChangeRequest1058VO) throws EMDException {
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

		try {
			strLogUser = objChangeRequest1058VO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
					.prepareCall(EMDQueries.SP_UPD_1058_NLSDB_CLA_CHNG_REQ);
			
			objCallableStatement.setInt(1,objChangeRequest1058VO.getSeqNo1058());
			objCallableStatement.setInt(2,objChangeRequest1058VO.getClaChangeReqSeqNo());
			objCallableStatement.setString(3,objChangeRequest1058VO.getChangeFromSpec());
			objCallableStatement.setString(4,objChangeRequest1058VO.getChangeFromClaDesc());
			objCallableStatement.setString(5,objChangeRequest1058VO.getChangeFromEngData());
			objCallableStatement.setString(6,objChangeRequest1058VO.getChangeToSpec());
			objCallableStatement.setString(7,objChangeRequest1058VO.getChangeToClaDesc());
			objCallableStatement.setString(8,objChangeRequest1058VO.getChangeToEngData());
			objCallableStatement.setString(9,objChangeRequest1058VO.getChangeClaReason());
			objCallableStatement.setString(10,strLogUser);
			
			objCallableStatement.registerOutParameter(11,Types.INTEGER);
			objCallableStatement.registerOutParameter(12,Types.VARCHAR);
			objCallableStatement.registerOutParameter(13,Types.VARCHAR);			

			intStatusCode = objCallableStatement.executeUpdate();
			LogUtil.logMessage("Update Result:" + intStatusCode);
			if (intStatusCode > 0) {
				intStatusCode = 0;
			}
			LogUtil.logMessage("Status Update" + intStatusCode);
	
			intLSDBErrorID = objCallableStatement.getInt(11);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(12);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(13);

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
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objChangeRequest1058VO
	 *            the object for creating NonLSDB Request
	 * @return int the status for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList selectClauseNonLsdb(
			ChangeRequest1058VO objChangeRequest1058VO) throws EMDException,Exception {
		LogUtil
				.logMessage("Enters into ChangeRequest1058DAO:selectClauseNonLsdb");
	
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		ResultSet rsClaList=null;
		ArrayList arlclaList=new ArrayList();

		try {
			strLogUser = objChangeRequest1058VO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
					.prepareCall(EMDQueries.SP_SEL_1058_NLSDB_CLA_CHNG_REQ);
			
			objCallableStatement.setInt(1,objChangeRequest1058VO.getSeqNo1058());
			objCallableStatement.setNull(2,Types.NULL);
			//objCallableStatement.setInt(2,objChangeRequest1058VO.getClaChangeReqSeqNo());
			objCallableStatement.setString(3,objChangeRequest1058VO.getUserId());
			objCallableStatement.registerOutParameter(4,OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(5,Types.INTEGER);
			objCallableStatement.registerOutParameter(6,Types.VARCHAR);
			objCallableStatement.registerOutParameter(7,Types.VARCHAR);			

			objCallableStatement.execute();
			
			rsClaList=(ResultSet)objCallableStatement.getObject(4);
			
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
			
			while(rsClaList.next()){
				objChangeRequest1058VO = new ChangeRequest1058VO();
				objChangeRequest1058VO.setSeqNo1058(rsClaList.getInt(DatabaseConstants.LS900_1058_SEQ_NO));
				objChangeRequest1058VO.setClaChangeReqSeqNo(rsClaList.getInt(DatabaseConstants.LS910_CLA_CHANGE_REQ_SEQ_NO));
				objChangeRequest1058VO.setChangeFromSpec(rsClaList.getString(DatabaseConstants.LS918_CHANGE_FROM_SPEC_SECT));
				objChangeRequest1058VO.setChangeFromClaDesc(rsClaList.getString(DatabaseConstants.LS918_CHANGE_FROM_CLA_DESC));
				objChangeRequest1058VO.setChangeFromEngData(rsClaList.getString(DatabaseConstants.LS918_CHANGE_FROM_ENG_DATA));
				objChangeRequest1058VO.setChangeToSpec(rsClaList.getString(DatabaseConstants.LS918_CHANGE_TO_SPEC_SECTION));
				objChangeRequest1058VO.setChangeToClaDesc(rsClaList.getString(DatabaseConstants.LS918_CHANGE_TO_CLA_DESC));
				objChangeRequest1058VO.setChangeToEngData(rsClaList.getString(DatabaseConstants.LS918_CHANGE_TO_ENG_DATA));
				objChangeRequest1058VO.setReason(rsClaList.getString(DatabaseConstants.LS918_REASON));		
				arlclaList.add(objChangeRequest1058VO);
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
		
		return arlclaList;
	}

	
	//----@cr110---@108447---end
	
	//Added by @vb106565 for CR-110 Starts here
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objChangeRequest1058VO
	 *            The Object for System Engineer Section
	 * @return int The status for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	

	public static int sysEngrSecDetails(ChangeRequest1058VO objChangeRequest1058VO)
	throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058DAO:sysEngrSecDetails");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		
		
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intStatusCode=0;
		int intLSDBErrorID = 0;	
		String strLogUser = "";
		try {
			strLogUser = objChangeRequest1058VO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_UPDATE_SYSTEM_ENGR);			 
			LogUtil.logMessage("objChangeRequest1058VO.getSeqNo1058():"+objChangeRequest1058VO.getSeqNo1058());		
			objCallableStatement.setInt(1, objChangeRequest1058VO.getSeqNo1058());
			
			if (objChangeRequest1058VO.getSectionStatus() != 0) {
				objCallableStatement.setInt(2, objChangeRequest1058VO
						.getSectionStatus());
			} else {
				objCallableStatement.setNull(2, Types.NULL);
			}
				
			LogUtil.logMessage("objChangeRequest1058VO.getSectionStatus():"+objChangeRequest1058VO.getSectionStatus());
			objCallableStatement.setString(3, objChangeRequest1058VO.getSystemEngComment());
			objCallableStatement.setString(4, objChangeRequest1058VO.getPartNoAdded());
			objCallableStatement.setString(5, objChangeRequest1058VO.getPartNoDeleted());
			objCallableStatement.setString(6, objChangeRequest1058VO.getChangeAffectsWeight());
			objCallableStatement.setString(7, objChangeRequest1058VO.getChangeAffectsClear());
			objCallableStatement.setString(8, objChangeRequest1058VO.getDesignHrs());
			objCallableStatement.setString(9, objChangeRequest1058VO.getDraftingHrs());			
			objCallableStatement.setString(10, objChangeRequest1058VO.getDrawingDueDate());
			objCallableStatement.setString(11, objChangeRequest1058VO.getCompletionDate());
			objCallableStatement.setBigDecimal(12, objChangeRequest1058VO.getWorkOrderUSD());
			objCallableStatement.setString(13, objChangeRequest1058VO.getStationAffected());
			LogUtil.logMessage("objChangeRequest1058VO.getUserID():"+objChangeRequest1058VO.getUserID());
			objCallableStatement.setString(14, objChangeRequest1058VO.getUserID());		
			objCallableStatement.registerOutParameter(15, Types.INTEGER);
			objCallableStatement.registerOutParameter(16, Types.VARCHAR);
			objCallableStatement.registerOutParameter(17, Types.VARCHAR);

			intStatusCode = objCallableStatement.executeUpdate();
			
			LogUtil.logMessage("Update Result:" + intStatusCode);
			if (intStatusCode > 0) {
				intStatusCode = 0;
			}
			LogUtil.logMessage("Status Update" + intStatusCode);
			intLSDBErrorID = objCallableStatement.getInt(15);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(16);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(17);
			
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
            
		}
		catch (DataAccessException objDataExp) {
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
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objChangeRequest1058VO
	 *            The Object for Operations Section
	 * @return int The status for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int operationsSecDetails(ChangeRequest1058VO objChangeRequest1058VO)
	throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058DAO:operationsSecDetails");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intStatusCode=0;
		int intLSDBErrorID = 0;	
		String strLogUser = "";
		try {			
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_UPDATE_OPERATIONS_REP);			 		
			objCallableStatement.setInt(1, objChangeRequest1058VO.getSeqNo1058());
			if (objChangeRequest1058VO.getSectionStatus() != 0) {
				objCallableStatement.setInt(2, objChangeRequest1058VO
						.getSectionStatus());
			} else {
				objCallableStatement.setNull(2, Types.NULL);
			}
			objCallableStatement.setString(3, objChangeRequest1058VO.getOperationComments());
			objCallableStatement.setString(4, objChangeRequest1058VO.getDisposExcessMaterial());
			objCallableStatement.setString(5, objChangeRequest1058VO.getSupplierAffectedCost());
			objCallableStatement.setString(6, objChangeRequest1058VO.getLaborImpact());
			objCallableStatement.setString(7, objChangeRequest1058VO.getRecEffectivityDel());
			objCallableStatement.setBigDecimal(8, objChangeRequest1058VO.getToolingCostUSD());
			objCallableStatement.setBigDecimal(9, objChangeRequest1058VO.getScrapCostUSD());			
			objCallableStatement.setBigDecimal(10, objChangeRequest1058VO.getReworkCost());
			LogUtil.logMessage("objChangeRequest1058VO.getUserID():"+objChangeRequest1058VO.getUserID());
			objCallableStatement.setString(11, objChangeRequest1058VO.getUserID());
			
			objCallableStatement.registerOutParameter(12, Types.INTEGER);
			objCallableStatement.registerOutParameter(13, Types.VARCHAR);
			objCallableStatement.registerOutParameter(14, Types.VARCHAR);

			intStatusCode = objCallableStatement.executeUpdate();
			
			LogUtil.logMessage("Update Result:" + intStatusCode);
			if (intStatusCode > 0) {
				intStatusCode = 0;
			}
			LogUtil.logMessage("Status Update" + intStatusCode);
			intLSDBErrorID = objCallableStatement.getInt(12);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(13);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(14);
			
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
	        
		}
		catch (DataAccessException objDataExp) {
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
	
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objChangeRequest1058VO
	 *            The Object for Finance Section
	 * @return int The status for success or failure
	 * @throws EMDException
	 **************************************************************************/
	

	public static int financeSecDetails(ChangeRequest1058VO objChangeRequest1058VO)
	throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058DAO:financeSecDetails");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intStatusCode=0;
		int intLSDBErrorID = 0;	
		String strLogUser = "";
		try {			
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_UPDATE_FINANCE_REP);			 		
			objCallableStatement.setInt(1, objChangeRequest1058VO.getSeqNo1058());
			if (objChangeRequest1058VO.getSectionStatus() != 0) {
				objCallableStatement.setInt(2, objChangeRequest1058VO
						.getSectionStatus());
			} else {
				objCallableStatement.setNull(2, Types.NULL);
			}
			objCallableStatement.setString(3, objChangeRequest1058VO.getFinanceComments());
			objCallableStatement.setString(4, objChangeRequest1058VO.getProdCostChange());
			objCallableStatement.setString(5, objChangeRequest1058VO.getProdCostChangeSup());
			LogUtil.logMessage("objChangeRequest1058VO.getUserID():"+objChangeRequest1058VO.getUserID());
			objCallableStatement.setString(6, objChangeRequest1058VO.getUserID());
			objCallableStatement.registerOutParameter(7, Types.INTEGER);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			objCallableStatement.registerOutParameter(9, Types.VARCHAR);

			intStatusCode = objCallableStatement.executeUpdate();
			
			LogUtil.logMessage("Update Result:" + intStatusCode);
			if (intStatusCode > 0) {
				intStatusCode = 0;
			}
			LogUtil.logMessage("Status Update" + intStatusCode);
			intLSDBErrorID = objCallableStatement.getInt(7);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(8);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(9);
			
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
	       
		}
		catch (DataAccessException objDataExp) {
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

	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objChangeRequest1058VO
	 *            The Object for Program Manager Section
	 * @return int The status for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	 
	public static int programMgrSecDetails(ChangeRequest1058VO objChangeRequest1058VO)
	throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058DAO:programMgrSecDetails");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intStatusCode=0;
		int intLSDBErrorID = 0;	
		String strLogUser = "";
		try {			
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_UPDATE_PROGRAM_MGR);			 		
			objCallableStatement.setInt(1, objChangeRequest1058VO.getSeqNo1058());
			if (objChangeRequest1058VO.getSectionStatus() != 0) {
				objCallableStatement.setInt(2, objChangeRequest1058VO
						.getSectionStatus());
			} else {
				objCallableStatement.setNull(2, Types.NULL);
			}
			objCallableStatement.setString(3, objChangeRequest1058VO.getProgManagerComments());
			LogUtil.logMessage("objChangeRequest1058VO.getUserID():"+objChangeRequest1058VO.getUserID());
			objCallableStatement.setString(4, objChangeRequest1058VO.getUserID());
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);

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
	       
		}
		catch (DataAccessException objDataExp) {
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

	




	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objChangeRequest1058VO
	 *            The Object for Proposal Manager Section
	 * @return int The status for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
public static int proposalMgrSecDetails(ChangeRequest1058VO objChangeRequest1058VO)
	throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058DAO:proposalMgrSecDetails");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intStatusCode=0;
		int intLSDBErrorID = 0;	
		String strLogUser = "";
		try {			
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_UPDATE_PROPOSAL_MGR);			 		
			objCallableStatement.setInt(1, objChangeRequest1058VO.getSeqNo1058());
			if (objChangeRequest1058VO.getSectionStatus() != 0) {
				objCallableStatement.setInt(2, objChangeRequest1058VO
						.getSectionStatus());
			} else {
				objCallableStatement.setNull(2, Types.NULL);
			}
			objCallableStatement.setString(3, objChangeRequest1058VO.getPropManagerComments());
			objCallableStatement.setBigDecimal(4, objChangeRequest1058VO.getSellpriceCustomer());
			objCallableStatement.setBigDecimal(5, objChangeRequest1058VO.getMarkUp());
			objCallableStatement.setString(6, objChangeRequest1058VO.getCustomerApprovalReq());
			objCallableStatement.setString(7, objChangeRequest1058VO.getCustomerDecision());
			objCallableStatement.setString(8, objChangeRequest1058VO.getCustomerDecisionDate());
			objCallableStatement.setString(9, objChangeRequest1058VO.getCustomerApprovalDet());
			objCallableStatement.setBigDecimal(10, objChangeRequest1058VO.getActualSellPrice());
			objCallableStatement.setString(11, objChangeRequest1058VO.getUserID());
			objCallableStatement.registerOutParameter(12, Types.INTEGER);
			objCallableStatement.registerOutParameter(13, Types.VARCHAR);
			objCallableStatement.registerOutParameter(14, Types.VARCHAR);

			intStatusCode = objCallableStatement.executeUpdate();
			
			LogUtil.logMessage("Update Result:" + intStatusCode);
			if (intStatusCode > 0) {
				intStatusCode = 0;
			}
			LogUtil.logMessage("Status Update" + intStatusCode);
			intLSDBErrorID = objCallableStatement.getInt(12);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(13);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(14);
			
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
	        
		}
		catch (DataAccessException objDataExp) {
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
	//Added by @vb106565 for CR-110 Ends here
	
	
	//Added by @rr108354 for CR-110 starts here
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objChangeRequest1058VO
	 *            The object for uploading and fetching attachment of 1058 Request
	 * @return Arraylist It has Arraylist of objChangeRequest1058AttachmentsVO
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList uploadAttachment(ChangeRequest1058VO objChangeRequest1058VO)
	throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058DAO:uploadAttachment");
		Connection objConnection = null;
		ArrayList arlImagedetlsList = new ArrayList();
		ArrayList arlAttachmentReturned=new ArrayList();
		int intInserted;
		int intStatus;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		StringBuffer strBuffer = null;
		String strLogUser = "";
		ResultSet objResultSet=null;
		
		CallableStatement objCallableStatement = null;
		ChangeRequest1058AttachmentsVO objChangeRequest1058AttachmentsVO=null;
		
		try {
			strLogUser = objChangeRequest1058VO.getUserID();
			objConnection = DBHelper.prepareConnection();
			
			int intFileSize = objChangeRequest1058VO.getFileToAttach().getFileLength();
			LogUtil.logMessage("FileSize in DAO:" + intFileSize);
			
			String strContentType = objChangeRequest1058VO.getFileToAttach().getContentType();
			int intNextSeqNo = DBHelper.getSequenceNumber(objConnection,
					DatabaseConstants.LS170_IMG_SEQ_Name);
			Timestamp objSqlDate = ApplicationUtil.getCurrentTimeStamp();
			arlImagedetlsList.add(new Integer(intNextSeqNo));
			arlImagedetlsList.add(strContentType);
			arlImagedetlsList.add(objChangeRequest1058VO.getUserID());
			arlImagedetlsList.add(objSqlDate);
			
			LogUtil.logMessage("Queries for Inserting images");
			LogUtil
			.logMessage("Enters into insert Empty Attachment Block of ChangeRequest1058DAO:uploadAttachment");
			
			intInserted = DBHelper.executeUpdate(objConnection,
					EMDQueries.Query_EmptyImage, arlImagedetlsList);
			LogUtil.logMessage("Insert Status of empty Attachment" + intInserted);
			
			LogUtil
			.logMessage("Enters into Update Attcahment Block of ChangeRequest1058DAO:uploadAttachment");
			
			strBuffer = new StringBuffer();
			strBuffer.append(EMDQueries.Query_UpdateImage + intNextSeqNo);
			LogUtil
			.logMessage("image sequence number is "+intNextSeqNo);
			String strUpdatequery = strBuffer.toString();
			boolean blnUpdated = DBHelper.executeDatabaseUpdateUpload(
					objConnection, strUpdatequery, objChangeRequest1058VO.getFileToAttach());
			
			LogUtil
			.logMessage("Enters into Insert ImgSeqNo Block of ChangeRequest1058DAO:uploadAttachment"
					+ blnUpdated);
			if(blnUpdated){
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_INSERT_1058_ATTACHMENT );
			objCallableStatement.setInt(1, objChangeRequest1058VO.getSeqNo1058());
			objCallableStatement.setInt(2, intNextSeqNo);
			objCallableStatement.setString(3,objChangeRequest1058VO.getFileToAttach().getFileName());
			objCallableStatement.setString(4,objChangeRequest1058VO.getUserID());
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;				
			}
		
			intLSDBErrorID = (int) objCallableStatement.getInt(5);
			strOracleCode = (String) objCallableStatement.getString(6);
			strErrorMessage = (String) objCallableStatement.getString(7);
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
				objConnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
			
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_SELECT_1058_ATTACHMENTS );
			objCallableStatement.setInt(1, objChangeRequest1058VO.getSeqNo1058());
			objCallableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			objCallableStatement.setString(3,objChangeRequest1058VO.getUserID());
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			
			objCallableStatement.execute();
			objResultSet = (ResultSet) objCallableStatement.getObject(2);
			
			LogUtil
			.logMessage("ResultSet Value in ChangeRequest1058DAO:uploadAttachment:"
					+ objResultSet.getFetchSize());
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
			
			
			while(objResultSet.next()){
				LogUtil
				.logMessage("Inside the resultSet of upload attachment");
				objChangeRequest1058AttachmentsVO =new ChangeRequest1058AttachmentsVO();
				objChangeRequest1058AttachmentsVO.setImgSeqNo(objResultSet.getInt(DatabaseConstants.LS170_IMG_SEQ_NO));
				objChangeRequest1058AttachmentsVO.setImgName(objResultSet.getString(DatabaseConstants.LS907_IMAGE_NAME));
				arlAttachmentReturned.add(objChangeRequest1058AttachmentsVO);
			}
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
		LogUtil.logMessage("Object Returned "+objChangeRequest1058AttachmentsVO);
		return arlAttachmentReturned;
			
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objChangeRequest1058VO
	 *            The object for deleting and fetching attachment of 1058 Request
	 * @return Arraylist It has Arraylist of objChangeRequest1058AttachmentsVO
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList deleteAttachment(ChangeRequest1058VO objChangeRequest1058VO)
	throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058DAO:deleteAttachment");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intStatus=0;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		ResultSet objResultSet=null;
		ArrayList arlAttachmentReturned=new ArrayList();
		ChangeRequest1058AttachmentsVO objChangeRequest1058AttachmentsVO =null;
		
		try {
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_DELETE_1058_ATTACHMENT);
			
			objCallableStatement.setInt(1,objChangeRequest1058VO.getSeqNo1058());
			objCallableStatement.setInt(2,objChangeRequest1058VO.getAttachmentToDelete());
			objCallableStatement.setString(3,objChangeRequest1058VO.getUserID());
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			if (intStatus > 0) {
				intStatus = 0;				
			}
			
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
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_SELECT_1058_ATTACHMENTS );
			objCallableStatement.setInt(1, objChangeRequest1058VO.getSeqNo1058());
			objCallableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			objCallableStatement.setString(3,objChangeRequest1058VO.getUserID());
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.execute();
			objResultSet = (ResultSet) objCallableStatement.getObject(2);
			
			LogUtil
			.logMessage("ResultSet Value in ChangeRequest1058DAO:deleteAttachment:"
					+ objResultSet.getFetchSize());
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
			
			
			while(objResultSet.next()){
				LogUtil
				.logMessage("Inside the resultSet of delete attachment");
				objChangeRequest1058AttachmentsVO =new ChangeRequest1058AttachmentsVO();
				objChangeRequest1058AttachmentsVO.setImgSeqNo(objResultSet.getInt(DatabaseConstants.LS170_IMG_SEQ_NO));
				objChangeRequest1058AttachmentsVO.setImgName(objResultSet.getString(DatabaseConstants.LS907_IMAGE_NAME));
				arlAttachmentReturned.add(objChangeRequest1058AttachmentsVO);
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
		return arlAttachmentReturned;
			
	}
	
	
	
	
	
	
	public static ArrayList fetch1058RequestCategories(LoginVO objLoginVo)
	throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058DAO:fetch1058RequestCategories");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		ResultSet objResultSet=null;
		ArrayList arlCategories=new ArrayList();
		RequestCategories1058VO objRequestCategories1058VO =null;
		
		try {
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_SELECT_1058_REQ_CATEGORY);
			
			objCallableStatement.setString(1,objLoginVo.getUserID());
			objCallableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(3, Types.INTEGER);
			objCallableStatement.registerOutParameter(4, Types.VARCHAR);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			
			objCallableStatement.execute();
			objResultSet = (ResultSet) objCallableStatement.getObject(2);
			
			intLSDBErrorID = objCallableStatement.getInt(3);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(4);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(5);
			
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
			
			
			while(objResultSet.next()){
				LogUtil
				.logMessage("Inside the resultSet of Select Categories");
				objRequestCategories1058VO =new RequestCategories1058VO();
				objRequestCategories1058VO.setCategorySeqNo(objResultSet.getInt(DatabaseConstants.LS906_CATEGORY_SEQ_NO));
				objRequestCategories1058VO.setCategoryName(objResultSet.getString(DatabaseConstants.LS906_CATEGORY_NAME));
				
				arlCategories.add(objRequestCategories1058VO);
			}
			
			LogUtil
			.logMessage(arlCategories);
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
		//LogUtil.logMessage("Attachment has been deleted " + intUpdated);
		return arlCategories;
			
	}
			
	
	public static ArrayList fetch1058RequestTypes(LoginVO objLoginVo)
	throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058DAO:fetch1058RequestTypes");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		ResultSet objResultSet=null;
		//ArrayList arlAttachmentReturned=new ArrayList();
		//ChangeRequest1058AttachmentsVO objChangeRequest1058AttachmentsVO =null;
		ArrayList arlRequestTypes=new ArrayList();
		RequestTypes1058VO objRequestTypes1058VO =null;
		
		try {
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_SELECT_1058_REQ_TYPES);
			
			objCallableStatement.setString(1,objLoginVo.getUserID());
			objCallableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(3, Types.INTEGER);
			objCallableStatement.registerOutParameter(4, Types.VARCHAR);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			
			objCallableStatement.execute();
			objResultSet = (ResultSet) objCallableStatement.getObject(2);
			
			intLSDBErrorID = objCallableStatement.getInt(3);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(4);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(5);
			
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
			
			
			while(objResultSet.next()){
				LogUtil
				.logMessage("Inside the resultSet of Select Categories");
				objRequestTypes1058VO =new RequestTypes1058VO();
				objRequestTypes1058VO.setRequestTypeSeqNo(objResultSet.getInt(DatabaseConstants.LS901_REQ_TYPE_SEQ_NO));
				objRequestTypes1058VO.setRequestTypeName(objResultSet.getString(DatabaseConstants.LS901_REQ_TYPE_DESC));
				
				arlRequestTypes.add(objRequestTypes1058VO);
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
		//LogUtil.logMessage("Attachment has been deleted " + intUpdated);
		return arlRequestTypes;
			
	}
			
	
	public static ArrayList fetch1058ClauseChangeTypes(LoginVO objLoginVo)
	throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058DAO:fetch1058ClauseChangeTypes");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		ResultSet objResultSet=null;
		//ArrayList arlAttachmentReturned=new ArrayList();
		//ChangeRequest1058AttachmentsVO objChangeRequest1058AttachmentsVO =null;
		ArrayList arlClauseChangeTypes=new ArrayList();
		RequestClauseChangeTypes1058VO objRequestClauseChangeTypes1058VO =null;
		
		try {
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_SELECT_1058_CLA_CHNG_TYPES);
			
			objCallableStatement.setString(1,objLoginVo.getUserID());
			objCallableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(3, Types.INTEGER);
			objCallableStatement.registerOutParameter(4, Types.VARCHAR);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			
			objCallableStatement.execute();
			objResultSet = (ResultSet) objCallableStatement.getObject(2);
			
			intLSDBErrorID = objCallableStatement.getInt(3);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(4);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(5);
			
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
			
			
			while(objResultSet.next()){
				LogUtil
				.logMessage("Inside the resultSet of Select Categories");
				objRequestClauseChangeTypes1058VO =new RequestClauseChangeTypes1058VO();
				objRequestClauseChangeTypes1058VO.setChangeTypeSeqNo(objResultSet.getInt(DatabaseConstants.LS903_CHANGE_TYPE_SEQ_NO));
				objRequestClauseChangeTypes1058VO.setChangeTypeName(objResultSet.getString(DatabaseConstants.LS903_CHANGE_TYPE_DESC));
				
				arlClauseChangeTypes.add(objRequestClauseChangeTypes1058VO);
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
		//LogUtil.logMessage("Attachment has been deleted " + intUpdated);
		return arlClauseChangeTypes;
			
	}
			
	
	public static ArrayList fetchOrderSections1058(SectionVO objSectionVo)
	throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058DAO:fetchOrderSections1058");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		ResultSet objResultSet=null;
		//ArrayList arlAttachmentReturned=new ArrayList();
		//ChangeRequest1058AttachmentsVO objChangeRequest1058AttachmentsVO =null;
		SectionVO objSectionVO = null;
		ArrayList arlSectionList=new ArrayList();
		
		try {
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_SELECT_SEC_FOR_1058);
			
			LogUtil.logMessage("Order Key in Dao" + objSectionVo.getOrderKey());
			LogUtil.logMessage("DataLoc Type in Dao" + objSectionVo.getDataLocationType());
			LogUtil.logMessage("UserID in Dao" + objSectionVo.getUserID());
			
			objCallableStatement.setInt(1,objSectionVo.getOrderKey());
			objCallableStatement.setString(2,objSectionVo.getDataLocationType());
			//objCallableStatement.setInt(3,objSectionVo.getSectionSeqNo());
			objCallableStatement.setString(3,objSectionVo.getUserID());
			objCallableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			
			objCallableStatement.execute();
			objResultSet = (ResultSet) objCallableStatement.getObject(4);
			
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
			
			
			while(objResultSet.next()){
				LogUtil
				.logMessage("Inside the resultSet of Select Categories");
				objSectionVO =new SectionVO();
				objSectionVO.setSectionCode(objResultSet.getString(DatabaseConstants.LS201_SEC_CODE));
				objSectionVO.setSectionName(objResultSet.getString(DatabaseConstants.LS201_SEC_NAME));
				objSectionVO.setSectionSeqNo(objResultSet.getInt(DatabaseConstants.LS201_SEC_SEQ_NO));
				arlSectionList.add(objSectionVO);
				
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
		//LogUtil.logMessage("Attachment has been deleted " + intUpdated);
		return arlSectionList;
			
	}
			
	public static ArrayList fetchOrderSubSections1058(SectionVO objSectionVo)
	throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058DAO:fetchOrderSubSections1058");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		ResultSet objResultSet=null;
		//ArrayList arlAttachmentReturned=new ArrayList();
		//ChangeRequest1058AttachmentsVO objChangeRequest1058AttachmentsVO =null;
		SubSectionVO objSubSectionVO = null;
		ArrayList arlSubSectionList=new ArrayList();
		
		try {
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_SELECT_SUBSEC_FOR_1058);
			
			objCallableStatement.setInt(1,objSectionVo.getOrderKey());
			objCallableStatement.setString(2,objSectionVo.getDataLocationType());
			objCallableStatement.setInt(3,objSectionVo.getSectionSeqNo());
			objCallableStatement.setString(4,objSectionVo.getUserID());
			objCallableStatement.registerOutParameter(5, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			
			objCallableStatement.execute();
			objResultSet = (ResultSet) objCallableStatement.getObject(5);
			
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
			
			
			while(objResultSet.next()){
				LogUtil
				.logMessage("Inside the resultSet of Select Categories");
				objSubSectionVO =new SubSectionVO();
				objSubSectionVO.setSubCode(objResultSet.getString(DatabaseConstants.subcode));
				objSubSectionVO.setSubSecSeqNo(objResultSet.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO));
				objSubSectionVO.setSubSecName(objResultSet.getString(DatabaseConstants.LS202_SUBSEC_NAME));
				objSubSectionVO.setSubSecCode(objResultSet.getString(DatabaseConstants.LS202_SUBSEC_CODE));
				arlSubSectionList.add(objSubSectionVO);
				
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
		//LogUtil.logMessage("Attachment has been deleted " + intUpdated);
		return arlSubSectionList;
			
	}
			
	
	public static ArrayList fetchComponentGrpMap1058(ComponentVO objComponentVO)
	throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058DAO:fetchComponentGrpMap1058");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		ResultSet objResultSet=null;
		ResultSet objComponentResultSet=null;
		//ArrayList arlAttachmentReturned=new ArrayList();
		//ChangeRequest1058AttachmentsVO objChangeRequest1058AttachmentsVO =null;
		CompGroupVO objCompGroupVO=null;
		ComponentVO objComponentRetVO=null;
		ArrayList arlCompGrpList=new ArrayList();
		ArrayList arlCompList=new ArrayList();
		
		try {
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_SELECT_COMPMAP_FOR_1058);
			
			objCallableStatement.setInt(1,objComponentVO.getOrderKey());
			objCallableStatement.setString(2,objComponentVO.getDataLocationType());
			
			
			if(objComponentVO.getReviseCheck()!=1){
					objCallableStatement.setInt(3,objComponentVO.getSubSectionSeqNo());
					if(objComponentVO.getComponentGroupSeqNo()!= 0){
						objCallableStatement.setInt(4,objComponentVO.getComponentGroupSeqNo());
					}
					else{
						objCallableStatement.setNull(4, Types.NULL);
					}
			}
			else{
					ClauseVO objClauseVO =(ClauseVO)objComponentVO.getClauseVOList().get(0);
					objCallableStatement.setInt(3,objClauseVO.getSubSectionSeqNo());
					
					if(objClauseVO.getLeadCompGrpSeqNo()!= 0){
						objCallableStatement.setInt(4,objClauseVO.getLeadCompGrpSeqNo());
					}
					else{
						objCallableStatement.setNull(4, Types.NULL);
					}
				
			}
			
			
			objCallableStatement.setString(5,objComponentVO.getUserID());
			objCallableStatement.registerOutParameter(6, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(7, Types.INTEGER);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			objCallableStatement.registerOutParameter(9, Types.VARCHAR);
			
		
			objCallableStatement.execute();
			objResultSet = (ResultSet) objCallableStatement.getObject(6);
			
			intLSDBErrorID = objCallableStatement.getInt(7);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(8);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(9);
			
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
			
			
			while(objResultSet.next()){
				LogUtil
				.logMessage("Inside the resultSet of fetchComponentGrpMap1058");
				objCompGroupVO =new CompGroupVO();
				
				objCompGroupVO.setComponentGroupSeqNo(objResultSet.getInt(DatabaseConstants.LS130_COMP_GRP_SEQ_NO));
				objCompGroupVO.setComponentGroupName(objResultSet.getString(DatabaseConstants.LS130_COMP_GRP_NAME));
				objCompGroupVO.setValidFlag(objResultSet.getString(DatabaseConstants.LS130_VALIDATION_FLAG));
				
				objComponentResultSet =(ResultSet)objResultSet.getObject("COMPONENTS");
					while(objComponentResultSet.next()){
						LogUtil
						.logMessage("Inside the resultSet of fetchComponentGrpMap1058 Components");
						objComponentRetVO =new ComponentVO();
						objComponentRetVO.setComponentSeqNo(objComponentResultSet.getInt(DatabaseConstants.LS140_COMP_SEQ_NO));
						objComponentRetVO.setComponentName(objComponentResultSet.getString(DatabaseConstants.LS140_COMP_NAME));
						objComponentRetVO.setSelectedComponent(objComponentResultSet.getString(DatabaseConstants.SELECTED));
						LogUtil
						.logMessage(objComponentResultSet.getString(DatabaseConstants.LS140_COMP_NAME)+" "+objComponentResultSet.getString(DatabaseConstants.SELECTED));
						arlCompList.add(objComponentRetVO);
					}
				objCompGroupVO.setComponentVO(arlCompList);
				arlCompGrpList.add(objCompGroupVO);
				
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
		//LogUtil.logMessage("Attachment has been deleted " + intUpdated);
		return arlCompGrpList;
			
	}
			
	
	public static ArrayList fetchComponentGrpMap1058(ComponentVO objComponentVO,Connection objConnection,String blnConnFlag)
	throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058DAO:fetchComponentGrpMap1058 with objectConnection");
		
		CallableStatement objCallableStatement=null;
		
		
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		ResultSet objResultSet=null;
		ResultSet objComponentResultSet=null;
		//ArrayList arlAttachmentReturned=new ArrayList();
		//ChangeRequest1058AttachmentsVO objChangeRequest1058AttachmentsVO =null;
		CompGroupVO objCompGroupVO=null;
		ComponentVO objComponentRetVO=null;
		ArrayList arlCompGrpList=new ArrayList();
		ArrayList arlCompList=new ArrayList();
		
		try {			
			
			if(objConnection==null && (blnConnFlag.equalsIgnoreCase("N"))){			
				objConnection=DBHelper.prepareConnection();		
			}
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_SELECT_COMPMAP_FOR_1058);
			objCallableStatement.setInt(1,objComponentVO.getOrderKey());
			objCallableStatement.setString(2,objComponentVO.getDataLocationType());
			objCallableStatement.setInt(3,objComponentVO.getSubSectionSeqNo());
			if(objComponentVO.getComponentGroupSeqNo()!= 0){
				objCallableStatement.setInt(4,objComponentVO.getComponentGroupSeqNo());
			}
			else{
				objCallableStatement.setNull(4, Types.NULL);
			}
			objCallableStatement.setString(5,objComponentVO.getUserID());
			objCallableStatement.registerOutParameter(6, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(7, Types.INTEGER);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			objCallableStatement.registerOutParameter(9, Types.VARCHAR);
		
			objCallableStatement.execute();
			objResultSet = (ResultSet) objCallableStatement.getObject(6);
			intLSDBErrorID = objCallableStatement.getInt(7);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(8);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(9);
			
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
			
			
			while(objResultSet.next()){
				LogUtil
				.logMessage("Inside the resultSet of fetchComponentGrpMap1058");
				objCompGroupVO =new CompGroupVO();
				
				objCompGroupVO.setComponentGroupSeqNo(objResultSet.getInt(DatabaseConstants.LS130_COMP_GRP_SEQ_NO));
				objCompGroupVO.setComponentGroupName(objResultSet.getString(DatabaseConstants.LS130_COMP_GRP_NAME));
				objCompGroupVO.setValidFlag(objResultSet.getString(DatabaseConstants.LS130_VALIDATION_FLAG));
				
				objComponentResultSet =(ResultSet)objResultSet.getObject("COMPONENTS");
					while(objComponentResultSet.next()){
						LogUtil
						.logMessage("Inside the resultSet of fetchComponentGrpMap1058 Components");
						objComponentRetVO =new ComponentVO();
						objComponentRetVO.setComponentSeqNo(objComponentResultSet.getInt(DatabaseConstants.LS140_COMP_SEQ_NO));
						objComponentRetVO.setComponentName(objComponentResultSet.getString(DatabaseConstants.LS140_COMP_NAME));
						objComponentRetVO.setSelectedComponent(objComponentResultSet.getString(DatabaseConstants.SELECTED));
						LogUtil
						.logMessage(objComponentResultSet.getString(DatabaseConstants.LS140_COMP_NAME)+" "+objComponentResultSet.getString(DatabaseConstants.SELECTED));
						arlCompList.add(objComponentRetVO);
					}
				objCompGroupVO.setComponentVO(arlCompList);
				arlCompGrpList.add(objCompGroupVO);
				
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
				
				/**Added for CR-68 Order New Component -  To close Connection when called from action
				 * and use same connection when called within same DAO**/
				if("N".equalsIgnoreCase(blnConnFlag)){
				DBHelper.closeSQLObjects(objResultSet,
						objCallableStatement, objConnection);
				}else{
					
					DBHelper.closeSQLObjects(objResultSet,
							objCallableStatement, null);
				}
			}
			
			catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception exception...");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		//LogUtil.logMessage("Attachment has been deleted " + intUpdated);
		return arlCompGrpList;
			
	}
			
	
	
	
			
	public static int saveClause(ClauseVO objClauseVO)
	throws EMDException {
		LogUtil.logMessage("Inside the OrderClauseDAO:saveClause");
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
		
		String strLogUser = "";
		try {
			strLogUser = objClauseVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_INSERT_1058_CLA_CHNG_REQ);
			Connection dconn = ((DelegatingConnection) objConnection).getInnermostDelegate(); //Added for CR-123 
			if (objClauseVO.getSeqNo1058()!=0)
				objCallableStatement.setInt(1, objClauseVO.getSeqNo1058());
			else{
				objCallableStatement.setNull(1, Types.NULL);}
			if (objClauseVO.getClauseChangeType()!=0)
				objCallableStatement.setInt(2, objClauseVO.getClauseChangeType());
			else{
				objCallableStatement.setNull(2, Types.NULL);}
			if (objClauseVO.getModelSeqNo()!=0)
				objCallableStatement.setInt(3, objClauseVO.getModelSeqNo());
			else{
				objCallableStatement.setNull(3, Types.NULL);}
			if (objClauseVO.getSubSectionSeqNo()!=0)
				objCallableStatement.setInt(4, objClauseVO.getSubSectionSeqNo());
			else{
				objCallableStatement.setNull(4, Types.NULL);}
			if (objClauseVO.getLeadCompGrpSeqNo()!=0)
				objCallableStatement.setInt(5, objClauseVO.getLeadCompGrpSeqNo());
			else{
				objCallableStatement.setNull(5, Types.NULL);}			
			if (objClauseVO.getOldComponentSeqNo()!=0)
				objCallableStatement.setInt(6, objClauseVO.getOldComponentSeqNo());
			else{
				objCallableStatement.setNull(6, Types.NULL);}
			if (objClauseVO.getChangeFromClaSeqNo()!=0)
				objCallableStatement.setInt(7, objClauseVO.getChangeFromClaSeqNo());
			else{
				objCallableStatement.setNull(7, Types.NULL);}
			if (objClauseVO.getChangeFromClaVerNo()!=0)
				objCallableStatement.setInt(8, objClauseVO.getChangeFromClaVerNo());
			else{
				objCallableStatement.setNull(8, Types.NULL);}
			if (objClauseVO.getChangeFromClaNo()!=null)
				objCallableStatement.setString(9, objClauseVO.getChangeFromClaNo());
			else{
				objCallableStatement.setNull(9, Types.NULL);}
			if (objClauseVO.getChangeToClaSeqNo()!=0)
				objCallableStatement.setInt(10, objClauseVO.getChangeToClaSeqNo());
			else{
				objCallableStatement.setNull(10, Types.NULL);}
			if (objClauseVO.getChangeToClaVerNo()!=0)
				objCallableStatement.setInt(11, objClauseVO.getChangeToClaVerNo());
			else{
				objCallableStatement.setNull(11, Types.NULL);}
			if (objClauseVO.getParentClauseSeqNo()!=0)
				objCallableStatement.setInt(12, objClauseVO.getParentClauseSeqNo());
			else{
				objCallableStatement.setNull(12, Types.NULL);}
			if (objClauseVO.getClauseDesc()!=null)
				objCallableStatement.setString(13, objClauseVO.getClauseDesc());
			else{
				objCallableStatement.setNull(13, Types.NULL);}			
			
			arrdesc = new ArrayDescriptor(DatabaseConstants.STR_ARRAY,
					dconn);
			//Table Data
			
			arlTableList = objClauseVO.getTableDataVO();
			
			objTableDataVO = (ClauseTableDataVO) arlTableList.get(0);
			
			tableDataArr1 = new ARRAY(arrdesc, dconn, objTableDataVO
					.getTableArrayData1());
			if (objTableDataVO.getTableArrayData1() == null) {
				tableDataArr1 = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(14, tableDataArr1);
			} else {
				objCallableStatement.setArray(14, tableDataArr1);
			}
			
			objTableDataVO = (ClauseTableDataVO) arlTableList.get(1);
			tableDataArr2 = new ARRAY(arrdesc, dconn, objTableDataVO
					.getTableArrayData2());
			
			if (objTableDataVO.getTableArrayData2() == null) {
				tableDataArr2 = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(15, tableDataArr2);
			} else {
				objCallableStatement.setArray(15, tableDataArr2);
			}
			
			objTableDataVO = (ClauseTableDataVO) arlTableList.get(2);
			tableDataArr3 = new ARRAY(arrdesc, dconn, objTableDataVO
					.getTableArrayData3());
			
			if (objTableDataVO.getTableArrayData3() == null) {
				tableDataArr3 = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(16, tableDataArr3);
			} else {
				objCallableStatement.setArray(16, tableDataArr3);
			}
			
			objTableDataVO = (ClauseTableDataVO) arlTableList.get(3);
			tableDataArr4 = new ARRAY(arrdesc, dconn, objTableDataVO
					.getTableArrayData4());
			
			if (objTableDataVO.getTableArrayData4() == null) {
				tableDataArr4 = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(17, tableDataArr4);
			} else {
				objCallableStatement.setArray(17, tableDataArr4);
			}
			
			objTableDataVO = (ClauseTableDataVO) arlTableList.get(4);
			tableDataArr5 = new ARRAY(arrdesc, dconn, objTableDataVO
					.getTableArrayData5());
			if (objTableDataVO.getTableArrayData5() == null) {
				tableDataArr5 = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(18, tableDataArr5);
			} else {
				objCallableStatement.setArray(18, tableDataArr5);
			}			
			//ComponentVO
			
			if(objClauseVO.getComponentVO()!=null){
			arr = new ARRAY(arrdesc, dconn,
					processComponentVO(objClauseVO));
			objCallableStatement.setArray(19, arr);
			}
			else{
				arr =new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(19, arr);
			}
			
			//Ref EDL
			ARRAY arrRefEDLNO = new ARRAY(arrdesc, dconn, objClauseVO
					.getRefEDLNo());
			
			if (objClauseVO.getRefEDLNo() == null) {
				arrRefEDLNO = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(20, arrRefEDLNO);
			} else {
				objCallableStatement.setArray(20, arrRefEDLNO);
			}
			//EDL NO
			ARRAY arrEdlno = new ARRAY(arrdesc, dconn, objClauseVO
					.getEdlNo());
			if (objClauseVO.getEdlNo() == null) {
				arrEdlno = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(21, arrEdlno);
			} else {
				objCallableStatement.setArray(21, arrEdlno);
			}
			
			//Part Of SeqNO
			ARRAY arrPartOfSeqNO = new ARRAY(arrdesc, dconn,
					objClauseVO.getPartOfSeqNo());
			if (objClauseVO.getPartOfSeqNo() == null) {
				arrPartOfSeqNO = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(22, arrPartOfSeqNO);
				LogUtil.logMessage("inside if loop null:");
			} else {
				LogUtil.logMessage("inside else loop :");
				objCallableStatement.setArray(22, arrPartOfSeqNO);
			}
			ARRAY arrClauseSeqNo = new ARRAY(arrdesc, dconn,
					objClauseVO.getClauseSeqNoArray());
			
			if (objClauseVO.getClauseSeqNoArray() == null) {
				arrClauseSeqNo = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(23, arrClauseSeqNo);
			} else {
				objCallableStatement.setArray(23, arrClauseSeqNo);
			}
			ARRAY arrClauseNo = new ARRAY(arrdesc, dconn, objClauseVO
					.getClauseNoArray());
			if (objClauseVO.getClauseNoArray() == null) {
				arrClauseNo = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(24, arrClauseNo);
			} else {
				objCallableStatement.setArray(24, arrClauseNo);
			}
			if (objClauseVO.getDwONumber()!=0)
				objCallableStatement.setInt(25, objClauseVO.getDwONumber());
			else{
				objCallableStatement.setNull(25, Types.NULL);}
			
			if (objClauseVO.getPartNumber()!=0)
				objCallableStatement.setInt(26, objClauseVO.getPartNumber());
			else{
				objCallableStatement.setNull(26, Types.NULL);}
			
			if (objClauseVO.getPriceBookNumber() == 0) {
				objCallableStatement.setNull(27, Types.NULL);
			} else {
				objCallableStatement.setInt(27, objClauseVO
						.getPriceBookNumber());
			}
			if (objClauseVO.getObjStandardEquipVO() == null) {
				objCallableStatement.setNull(28, Types.NULL);
			} else {
				arlStandardEquipmentList = objClauseVO.getObjStandardEquipVO();
				StandardEquipVO objStandardEquipVO = (StandardEquipVO) arlStandardEquipmentList
				.get(0);
				objCallableStatement.setInt(28, objStandardEquipVO
						.getStandardEquipmentSeqNo());
			}
			
			if (objClauseVO.getComments() == null) {
				objCallableStatement.setNull(29, Types.NULL);
			} else {
				objCallableStatement.setString(29, objClauseVO.getComments());
			}
			objCallableStatement.setString(30, objClauseVO.getReason());
			objCallableStatement.setString(31,strLogUser);
			objCallableStatement.registerOutParameter(32, Types.INTEGER);
			objCallableStatement.registerOutParameter(33, Types.VARCHAR);
			objCallableStatement.registerOutParameter(34, Types.VARCHAR);
			intStatus = objCallableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;				
			}
			
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
			
			LogUtil.logMessage("Completed the DAo ChangeRequest1058DAO:saveClause");
			
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
		return intStatus;
			
	}
	
	
	
	public static int saveDeleteClause(ClauseVO objClauseVO)
	throws EMDException {
		LogUtil.logMessage("Inside the OrderClauseDAO:saveDeleteClause");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		ArrayDescriptor arrdesc = null;
		ARRAY arr = null;
		ARRAY tableDataArr1, tableDataArr2, tableDataArr3, tableDataArr4, tableDataArr5 = null;
		
		String strLogUser = "";
		try {
			strLogUser = objClauseVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_INSERT_1058_CLA_CHNG_REQ);
			Connection dconn = ((DelegatingConnection) objConnection).getInnermostDelegate(); //Added for CR-123 
			if (objClauseVO.getSeqNo1058()!=0)
				objCallableStatement.setInt(1, objClauseVO.getSeqNo1058());
			else
				objCallableStatement.setNull(1, Types.NULL);
			if (objClauseVO.getClauseChangeType()!=0)
				objCallableStatement.setInt(2, objClauseVO.getClauseChangeType());
			else
				objCallableStatement.setNull(2, Types.NULL);
			if (objClauseVO.getModelSeqNo()!=0)
				objCallableStatement.setInt(3, objClauseVO.getModelSeqNo());
			else
				objCallableStatement.setNull(3, Types.NULL);
			objCallableStatement.setNull(4, Types.NULL);
			objCallableStatement.setNull(5, Types.NULL);
			objCallableStatement.setNull(6, Types.NULL);
			if (objClauseVO.getChangeFromClaSeqNo()!=0)
				objCallableStatement.setInt(7, objClauseVO.getChangeFromClaSeqNo());
			else
				objCallableStatement.setNull(7, Types.NULL);
			if (objClauseVO.getChangeFromClaVerNo()!=0)
				objCallableStatement.setInt(8, objClauseVO.getChangeFromClaVerNo());
			else
				objCallableStatement.setNull(8, Types.NULL);
			if (objClauseVO.getChangeFromClaNo()!=null)
				objCallableStatement.setString(9, objClauseVO.getChangeFromClaNo());
			else
				objCallableStatement.setNull(9, Types.NULL);
			objCallableStatement.setNull(10, Types.NULL);
			objCallableStatement.setNull(11, Types.NULL);
			objCallableStatement.setNull(12, Types.NULL);
			objCallableStatement.setNull(13, Types.NULL);
			arrdesc = new ArrayDescriptor(DatabaseConstants.STR_ARRAY,
					dconn);
			tableDataArr1 = new ARRAY(arrdesc, dconn, null);
			objCallableStatement.setArray(14, tableDataArr1);
			tableDataArr2 = new ARRAY(arrdesc, dconn, null);
			objCallableStatement.setArray(15, tableDataArr2);
			tableDataArr3 = new ARRAY(arrdesc, dconn, null);
			objCallableStatement.setArray(16, tableDataArr3);
			tableDataArr4 = new ARRAY(arrdesc, dconn, null);
			objCallableStatement.setArray(17, tableDataArr4);
			tableDataArr5 = new ARRAY(arrdesc, dconn, null);
			objCallableStatement.setArray(18, tableDataArr5);
			arr =new ARRAY(arrdesc, dconn, null);
			objCallableStatement.setArray(19, arr);
			ARRAY arrRefEDLNO = new ARRAY(arrdesc, dconn, null);
			objCallableStatement.setArray(20, arrRefEDLNO);
			ARRAY arrEdlno = new ARRAY(arrdesc, dconn, null);
			objCallableStatement.setArray(21, arrEdlno);
			ARRAY arrPartOfSeqNO = new ARRAY(arrdesc, dconn, null);
			objCallableStatement.setArray(22, arrPartOfSeqNO);
			ARRAY  arrClauseSeqNo = new ARRAY(arrdesc, dconn, null);
			objCallableStatement.setArray(23, arrClauseSeqNo);
			ARRAY arrClauseNo = new ARRAY(arrdesc, dconn, null);
			objCallableStatement.setArray(24, arrClauseNo);				
			objCallableStatement.setNull(25, Types.NULL);
			objCallableStatement.setNull(26, Types.NULL);
			objCallableStatement.setNull(27, Types.NULL);
			objCallableStatement.setNull(28, Types.NULL);		
			objCallableStatement.setNull(29, Types.NULL);
			objCallableStatement.setString(30, objClauseVO.getReason());
			objCallableStatement.setString(31,strLogUser);
			objCallableStatement.registerOutParameter(32, Types.INTEGER);
			objCallableStatement.registerOutParameter(33, Types.VARCHAR);
			objCallableStatement.registerOutParameter(34, Types.VARCHAR);
			intStatus = objCallableStatement.executeUpdate();
			LogUtil.logMessage("Status:" + intStatus);
			if (intStatus > 0) {
				intStatus = 0;
				
			}			
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
			
			LogUtil.logMessage("Completed the DAo ChangeRequest1058DAO:saveClause");
			
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
		return intStatus;
			
	}
	
	
	
	
	
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
	 * @param ChangeRequest1058VO
	 * @return ArrayList contains the LsdbClause Details
	 * @throws EMDException
	 **************************************************************************/
	

	public static ArrayList fetchLsdbClauseDetails(ChangeRequest1058VO objChangeRequest1058VO)
	throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058DAO:fetchLsdbClauseDetails");
		
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ResultSet objLsdbClauseResultSet = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		
		int intLSDBErrorID = 0;
		String strLogUser = "";
		ArrayList arlLsdbClauseDetailsList=new ArrayList();
		ArrayList arlComponents=new ArrayList();
	
		
		try {
			strLogUser= objChangeRequest1058VO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_SELECT_1058_CLA_CHNG_REQ);
			 
			if(objChangeRequest1058VO.getSeqNo1058() == 0 ){
				objCallableStatement.setNull(1, Types.NULL);
			}else{
			objCallableStatement.setInt(1, objChangeRequest1058VO.getSeqNo1058());
			}
			
			if(objChangeRequest1058VO.getClaChngReqSeq() == 0 ){
				objCallableStatement.setNull(2, Types.NULL);
			}else{
			objCallableStatement.setInt(2, objChangeRequest1058VO.getClaChngReqSeq());
			}
			
			if(objChangeRequest1058VO.getUserID() == null || "".equalsIgnoreCase(objChangeRequest1058VO.getUserID())){
				objCallableStatement.setNull(3, Types.NULL);
			}else {
			objCallableStatement.setString(3, objChangeRequest1058VO.getUserID());
			}
			
			objCallableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
            
			objCallableStatement.execute();
			objLsdbClauseResultSet = (ResultSet) objCallableStatement.getObject(4);
            
			LogUtil
			.logMessage("ResultSet Value in ChangeRequest1058DAO:fetchLsdbClauseDetails:"
					+ objLsdbClauseResultSet);
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
			
			
			
				String strPreReason = "";
				String strPastClauseNo = "";
				String strPastClauseDesc = "";
				String strPreClauseDesc = "";
				String strPreClauseNo="";
				ResultSet objResultSetEDLNO = null;
				ResultSet objResultSetRefEDLNO = null;
				ResultSet objResultSetPartOf = null;
				ResultSet objResultSetStdEquip = null;
				ResultSet objResultSetTableData = null;
				ResultSet objResultSetComponents =null;
				
				ArrayList arlTableRows = new ArrayList();			
				ArrayList allFromToClause= new ArrayList();
				ComponentVO objComponentVO =null;
				
				
				ClauseVO objFromClauseVO = new ClauseVO();
				ClauseVO objToClauseVO = new ClauseVO();
				ClauseVO objCommonClausVO = new ClauseVO();
				
				if (objLsdbClauseResultSet != null) {
					
					LogUtil.logMessage("Order DAO : objClauseRS");
				
					while(objLsdbClauseResultSet.next()){
					
					allFromToClause = new ArrayList();
					objCommonClausVO = new ClauseVO();
					objFromClauseVO = new ClauseVO();
					objToClauseVO = new ClauseVO();
					
					
					objCommonClausVO.setSeqNo1058(objLsdbClauseResultSet.getInt(DatabaseConstants.LS900_1058_SEQ_NO));
					objCommonClausVO.setClauseChangeReqSeqNo(objLsdbClauseResultSet.getInt(DatabaseConstants.LS910_CLA_CHANGE_REQ_SEQ_NO));
					objCommonClausVO.setClauseChangeType(objLsdbClauseResultSet.getInt(DatabaseConstants.LS903_CHANGE_TYPE_SEQ_NO));
					objCommonClausVO.setClauseChangeTypeDesc(objLsdbClauseResultSet.getString(DatabaseConstants.LS903_CHANGE_TYPE_DESC));
					strPreReason = objLsdbClauseResultSet.getString(DatabaseConstants.LS910_REASON);
					objCommonClausVO.setStrPreReason(strPreReason);
					objCommonClausVO.setClauseUpdatedToSpec(objLsdbClauseResultSet.getString(DatabaseConstants.LS910_CLA_UPDATED_TO_SPEC));
					
					allFromToClause.add(objCommonClausVO);
					ResultSet objFromClauseRS = (ResultSet) objLsdbClauseResultSet.getObject("CHANGE_FROM");
					
					while (objFromClauseRS.next()) {
						
						int cntEDL = 0;
						int cntRefEDl = 0;
						int cntPartOf = 0;
						int[] arlPartSubSecSeqNo = new int[4];
						String[] arlEDLNos = { "", "", "" };
						String[] arlRefEDLNos = { "", "", "" };
						String[] arlpartSubSecCode = { "", "", "", "" };
						int[] arlClauseSeqNo = new int[4];
						
						
						LogUtil.logMessage("while looop objFromClauseRS");
						
						strPastClauseNo = objFromClauseRS.getString("LS910_CHANGE_FROM_CLA_NUM");
						strPastClauseDesc = objFromClauseRS.getString("LS301_CLA_DESC");
						
						objFromClauseVO.setStrPastClauseNo(strPastClauseNo);
						objFromClauseVO.setChangeFromClaNo(strPastClauseNo);

						objFromClauseVO.setChangeFromClaSeqNo(objFromClauseRS.getInt(DatabaseConstants.LS910_CHANGE_FROM_CLA_SEQ_NO));
						objFromClauseVO.setChangeFromClaVerNo(objFromClauseRS.getInt(DatabaseConstants.LS910_CHANGE_FROM_VER_NO));
						objFromClauseVO.setStrPastClauseDesc(strPastClauseDesc);
						
						
						objResultSetTableData = (ResultSet) objFromClauseRS
						.getObject("TABLE_DATE");
					
						LogUtil.logMessage("while looop objFromClauseRS objResultSetTableData" + objResultSetTableData);
						
						ArrayList arlTableColumns = new ArrayList();		
						arlTableRows = new ArrayList();						
						
						while (objResultSetTableData.next()) {
							
							arlTableColumns = new ArrayList();
							arlTableColumns.add(objResultSetTableData
									.getString(DatabaseConstants.LS306_TBL_DATA_COL_1));
							arlTableColumns.add(objResultSetTableData
									.getString(DatabaseConstants.LS306_TBL_DATA_COL_2));
							arlTableColumns.add(objResultSetTableData
									.getString(DatabaseConstants.LS306_TBL_DATA_COL_3));
							arlTableColumns.add(objResultSetTableData
									.getString(DatabaseConstants.LS306_TBL_DATA_COL_4));
							arlTableColumns.add(objResultSetTableData
									.getString(DatabaseConstants.LS306_TBL_DATA_COL_5));
							// tableData6.add(objResultSetTableData.getString(DatabaseConstants.LS306_HEADER_FLAG));
							arlTableRows.add(arlTableColumns);
							LogUtil.logMessage("exists Table Data : " + arlTableColumns);

						}
						
						if (arlTableRows.size() > 0)
							objFromClauseVO.setTableArrayData1(arlTableRows);
						
						String strEngData = "";
						
										
						
						objResultSetEDLNO = (ResultSet) objFromClauseRS
						.getObject("EDLNO");
						
						while (objResultSetEDLNO.next()) {
							strEngData += ApplicationConstants.EDL
							+ objResultSetEDLNO
							.getString("LS302_EDL_NO");
							strEngData += "\n";
							
							LogUtil.logMessage("Enters into EDLNo Resultset Loop of Clause From");
							arlEDLNos[cntEDL] = objResultSetEDLNO
							.getString(DatabaseConstants.LS302_EDL_NO);
							cntEDL++;
						}
						objFromClauseVO.setEdlNo(arlEDLNos);
						DBHelper.closeSQLObjects(objResultSetEDLNO, null, null);

						objResultSetRefEDLNO = (ResultSet) objFromClauseRS
						.getObject("refEDLNO");
						
						while (objResultSetRefEDLNO.next()) {
							strEngData += ApplicationConstants.REF_EDL
							+ objResultSetRefEDLNO
							.getString(DatabaseConstants.LS303_REF_EDL_NO) + ApplicationConstants.REF_EDL_END;//CR-87
							strEngData += "\n";
							
							LogUtil.logMessage("Enters into RefEDLNo Resultset Loop of Clause From:");
							arlRefEDLNos[cntRefEDl] = objResultSetRefEDLNO
							.getString(DatabaseConstants.LS303_REF_EDL_NO);
							cntRefEDl++;
						}
						objFromClauseVO.setRefEDLNo(arlRefEDLNos);
						DBHelper.closeSQLObjects(objResultSetRefEDLNO, null, null);
						
						objResultSetPartOf = (ResultSet) objFromClauseRS
						.getObject("PartOF");
						
						while (objResultSetPartOf.next()) {
							
							strEngData += ApplicationConstants.PARTOF
							+ objResultSetPartOf
							.getString(DatabaseConstants.LS304_PART_OF_CLA_NUMBER);
							
							strEngData += "\n";
							
							arlpartSubSecCode[cntPartOf] = objResultSetPartOf
							.getString(DatabaseConstants.LS304_PART_OF_CLA_NUMBER);
							arlClauseSeqNo[cntPartOf] = objResultSetPartOf
							.getInt(DatabaseConstants.LS304_PART_OF_CLA_SEQ_NO);
							arlPartSubSecSeqNo[cntPartOf] = objResultSetPartOf
							.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO);
							cntPartOf++;
							
						}
						objFromClauseVO.setPartOfCode(arlpartSubSecCode);
						LogUtil.logMessage("PartOfCode:" + objFromClauseVO.getPartOfCode());
						LogUtil.logMessage("Length of PartOfCode:"
								+ arlpartSubSecCode.length);
						objFromClauseVO.setPartOfSeqNo(arlPartSubSecSeqNo);
						objFromClauseVO.setClauseSeqNum(arlClauseSeqNo);
						LogUtil.logMessage("PartOfSeqNo:"
								+ objFromClauseVO.getPartOfSeqNo());
						
						DBHelper.closeSQLObjects(objResultSetPartOf, null, null);
						
						if (objFromClauseRS.getInt(DatabaseConstants.LS301_DWO_NUMBER) != 0) {
							strEngData += ApplicationConstants.DWO
							+ objFromClauseRS.getInt(DatabaseConstants.LS301_DWO_NUMBER);
							strEngData += "\n";
							objFromClauseVO.setDwONumber(objFromClauseRS.getInt(DatabaseConstants.LS301_DWO_NUMBER));
						}
						
						if (objFromClauseRS.getInt(DatabaseConstants.LS301_PART_NUMBER) != 0) {
							strEngData += ApplicationConstants.PART_NUMBER
							+ objFromClauseRS.getInt(DatabaseConstants.LS301_PART_NUMBER);
							strEngData += "\n";
							
							objFromClauseVO.setPartNumber(objFromClauseRS.getInt(DatabaseConstants.LS301_PART_NUMBER));
							
						}

						if (objFromClauseRS.getInt(DatabaseConstants.LS301_PRICE_BOOK_NUMBER) != 0) {
							strEngData += ApplicationConstants.PRICE_BOOK_NO
							+ objFromClauseRS.getInt(DatabaseConstants.LS301_PRICE_BOOK_NUMBER);
							strEngData += "\n";
							
							objFromClauseVO.setPriceBookNumber(objFromClauseRS.getInt(DatabaseConstants.LS301_PRICE_BOOK_NUMBER));
							
						}
						
						objResultSetStdEquip = (ResultSet) objFromClauseRS
						.getObject("STD_EQUIP");
						
						if (objResultSetStdEquip.next()) {
							strEngData += objResultSetStdEquip
							.getString(DatabaseConstants.LS060_STD_EQP_DESC);
							strEngData += "\n";
							
							LogUtil.logMessage("Enters into std equip ResultSet");
							objFromClauseVO.setStandardEquipmentDesc(objResultSetStdEquip
									.getString(DatabaseConstants.LS060_STD_EQP_DESC));
							objFromClauseVO.setStandardEquipmentSeqNo(objResultSetStdEquip
									.getInt(DatabaseConstants.LS060_STD_EQP_SEQ_NO));
							LogUtil.logMessage("StandardEquipmentDesc:"
									+ objFromClauseVO.getStandardEquipmentDesc());
						}
						DBHelper.closeSQLObjects(objResultSetStdEquip, null, null);
						
						String strEngDataComments = objFromClauseRS
						.getString(DatabaseConstants.LS301_ENGG_DATA_COMMENTS);
						if (strEngDataComments != null
								&& !"".equals(strEngDataComments)) {
							strEngData += strEngDataComments;
							strEngData += "\n";
							objFromClauseVO.setEngDataComment(strEngDataComments);
						}
						
						objFromClauseVO.setStrEngData(strEngData);	
						
						objFromClauseVO.setReason(DatabaseConstants.LS301_CLA_REASON);
						
						objResultSetComponents =(ResultSet)objFromClauseRS
						.getObject("COMPONENTS");
						while(objResultSetComponents.next()){
							objComponentVO =new ComponentVO();
							objComponentVO.setComponentSeqNo(objResultSetComponents.getInt(DatabaseConstants.LS140_COMP_SEQ_NO));
							objComponentVO.setComponentName(objResultSetComponents.getString(DatabaseConstants.LS140_COMP_NAME));
							arlComponents.add(objComponentVO);
						}
						objFromClauseVO.setComponentVO(arlComponents);
						
											
						
						objFromClauseVO.setStrEngData(strEngData);						
					}
					DBHelper.closeSQLObjects(objFromClauseRS, null, null);	
					
					allFromToClause.add(objFromClauseVO);
												
					ResultSet objToClauseRS = (ResultSet) objLsdbClauseResultSet.getObject("CHANGE_TO");
				
					while (objToClauseRS.next()) {
						
						LogUtil.logMessage("OrderDao:::While loop objToClasueRs ");	
						int cntEDL = 0;
						int cntRefEDl = 0;
						int cntPartOf = 0;
						int[] arlPartSubSecSeqNo = new int[4];
						String[] arlEDLNos = { "", "", "" };
						String[] arlRefEDLNos = { "", "", "" };
						int[] arlClauseSeqNo = new int[4];
						
						strPreClauseNo = objToClauseRS.getString(DatabaseConstants.LS910_CHANGE_TO_CLA_NUM);
						strPreClauseDesc = objToClauseRS.getString(DatabaseConstants.LS910_CHANGE_TO_CLA_DESC);
						
						objToClauseVO.setStrPreClauseNo(strPreClauseNo);
						objToClauseVO.setChangeToClaNo(strPreClauseNo);
						objToClauseVO.setChangeToClaSeqNo(objToClauseRS.getInt(DatabaseConstants.LS910_CHANGE_TO_CLA_SEQ_NO));
						objToClauseVO.setStrPastClauseDesc(strPreClauseDesc);
						objToClauseVO.setStrPreClauseDesc(strPreClauseDesc);
						objToClauseVO.setChangeToClaVerNo(objToClauseRS.getInt(DatabaseConstants.LS910_CHANGE_TO_VER_NO));
						
						objResultSetTableData = (ResultSet) objToClauseRS.getObject("TABLE_DATE");
						
						arlTableRows = new ArrayList();
						ArrayList arlTableColumns = new ArrayList();
						
						while (objResultSetTableData.next()) {
							
							arlTableColumns = new ArrayList();
							
							arlTableColumns.add(objResultSetTableData
									.getString(DatabaseConstants.LS915_TBL_DATA_COL_1));
							arlTableColumns.add(objResultSetTableData
									.getString(DatabaseConstants.LS915_TBL_DATA_COL_2));
							arlTableColumns.add(objResultSetTableData
									.getString(DatabaseConstants.LS915_TBL_DATA_COL_3));
							arlTableColumns.add(objResultSetTableData
									.getString(DatabaseConstants.LS915_TBL_DATA_COL_4));
							arlTableColumns.add(objResultSetTableData
									.getString(DatabaseConstants.LS915_TBL_DATA_COL_5));
							// tableData6.add(objResultSetTableData.getString(DatabaseConstants.LS306_HEADER_FLAG));
							arlTableRows.add(arlTableColumns);
							LogUtil.logMessage("exists Table Data");
							
						}
						DBHelper.closeSQLObjects(objResultSetTableData, null, null);
						
						LogUtil.logMessage("arlTableRows : " + arlTableRows.size());
						
						if (arlTableRows.size() > 0)
							objToClauseVO.setTableArrayData1(arlTableRows);
						
						String strEngData = "";
						
						
						
							objResultSetEDLNO = (ResultSet) objToClauseRS.getObject("EDLNO");
							
							while (objResultSetEDLNO.next()) {
								strEngData += ApplicationConstants.EDL
								+ objResultSetEDLNO.getString(DatabaseConstants.LS911_EDL_NO);
								strEngData += "\n";
								
								LogUtil.logMessage("Enters into EDLNo Resultset Loop of Clause To");
								arlEDLNos[cntEDL] = objResultSetEDLNO
								.getString(DatabaseConstants.LS911_EDL_NO);
								cntEDL++;
							}
							objToClauseVO.setEdlNo(arlEDLNos);
							DBHelper.closeSQLObjects(objResultSetEDLNO, null, null);
							
							objResultSetRefEDLNO = (ResultSet) objToClauseRS.getObject("refEDLNO");
				
							
							while (objResultSetRefEDLNO.next()) {
								strEngData += ApplicationConstants.REF_EDL
								+ objResultSetRefEDLNO
								.getString(DatabaseConstants.LS912_REF_EDL_NO) + ApplicationConstants.REF_EDL_END;//CR-87
								strEngData += "\n";
								
								LogUtil.logMessage("Enters into RefEDLNo Resultset Loop of Clause To:");
								arlRefEDLNos[cntRefEDl] = objResultSetRefEDLNO
								.getString(DatabaseConstants.LS912_REF_EDL_NO);
								cntRefEDl++;
							}
							objToClauseVO.setRefEDLNo(arlRefEDLNos);
							DBHelper.closeSQLObjects(objResultSetRefEDLNO, null, null);
						
						objResultSetPartOf = (ResultSet) objToClauseRS.getObject("PartOF");
						
						while (objResultSetPartOf.next()) {
							
							strEngData += ApplicationConstants.PARTOF
							+ objResultSetPartOf
							.getString(DatabaseConstants.LS913_PART_OF_CLA_NO);
							
							strEngData += "\n";
							
							/*
							arlpartSubSecCode[cntPartOf] = objResultSetPartOf
							.getString(DatabaseConstants.LS407_PART_OF_CLA_NO);*/
							arlClauseSeqNo[cntPartOf] = objResultSetPartOf
							.getInt(DatabaseConstants.LS913_PART_OF_CLA_SEQ_NO);
							arlPartSubSecSeqNo[cntPartOf] = objResultSetPartOf
							.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO);
							cntPartOf++;
							
						}
						objToClauseVO.setPartOfSeqNo(arlPartSubSecSeqNo);
						objToClauseVO.setClauseSeqNum(arlClauseSeqNo);
						
						DBHelper.closeSQLObjects(objResultSetPartOf, null, null);
						
						if (objToClauseRS.getInt(DatabaseConstants.LS910_DWO_NO) != 0) {
							strEngData += ApplicationConstants.DWO
							+ objToClauseRS.getInt(DatabaseConstants.LS910_DWO_NO);
							strEngData += "\n";
							
							objToClauseVO.setDwONumber(objToClauseRS.getInt(DatabaseConstants.LS910_DWO_NO));
								
						}

						if (objToClauseRS.getInt(DatabaseConstants.LS910_PART_NO) != 0) {
							strEngData += ApplicationConstants.PART_NUMBER
							+ objToClauseRS.getInt(DatabaseConstants.LS910_PART_NO);
							strEngData += "\n";
							
							objToClauseVO.setPartNumber(objToClauseRS.getInt(DatabaseConstants.LS910_PART_NO));
							
						}
						
						if (objToClauseRS.getInt(DatabaseConstants.LS910_PRICE_BOOK_NO) != 0) {
							strEngData += ApplicationConstants.PRICE_BOOK_NO
							+ objToClauseRS.getInt(DatabaseConstants.LS910_PRICE_BOOK_NO);
							strEngData += "\n";
							
							objToClauseVO.setPriceBookNumber(objToClauseRS.getInt(DatabaseConstants.LS910_PRICE_BOOK_NO));
							
						}
						
						objResultSetStdEquip = (ResultSet) objToClauseRS
						.getObject("STD_EQUIP");
						
						if (objResultSetStdEquip.next()) {
							strEngData += objResultSetStdEquip
							.getString("LS060_STD_EQP_DESC");
							strEngData += "\n";		
							
							objToClauseVO.setStandardEquipmentDesc(objResultSetStdEquip
									.getString(DatabaseConstants.LS060_STD_EQP_DESC));
							objToClauseVO.setStandardEquipmentSeqNo(objResultSetStdEquip
									.getInt(DatabaseConstants.LS060_STD_EQP_SEQ_NO));
							}
						
						DBHelper.closeSQLObjects(objResultSetStdEquip, null, null);
						
						String strEngDataComments = objToClauseRS
						.getString(DatabaseConstants.LS910_ENG_DATA_COMMENTS);
						if (strEngDataComments != null
								&& !"".equals(strEngDataComments)) {
							strEngData += strEngDataComments;
							strEngData += "\n";
							
							objToClauseVO.setEngDataComment(strEngDataComments);
						}

						objResultSetComponents =(ResultSet)objToClauseRS
						.getObject("COMPONENTS");
						while(objResultSetComponents.next()){
							objComponentVO =new ComponentVO();
							objComponentVO.setComponentSeqNo(objResultSetComponents.getInt(DatabaseConstants.LS140_COMP_SEQ_NO));
							objComponentVO.setComponentName(objResultSetComponents.getString(DatabaseConstants.LS140_COMP_NAME));
							arlComponents.add(objComponentVO);
						}
						objToClauseVO.setComponentVO(arlComponents);
						
						objToClauseVO.setStrEngData(strEngData);					
						
					}
					
					DBHelper.closeSQLObjects(objToClauseRS, null, null);
					allFromToClause.add(objToClauseVO);
					
					LogUtil.logMessage("allFromToClause :" + allFromToClause.size());
					
					arlLsdbClauseDetailsList.add(allFromToClause);
					
					LogUtil.logMessage("arlLsdbClauseDetailsList.size() :" + arlLsdbClauseDetailsList.size());
					}
					
				}			
            
		}
		catch (DataAccessException objDataExp) {
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
		return arlLsdbClauseDetailsList;
	}

	public static int deleteSelectedClause(ChangeRequest1058VO objChangeRequest1058VO)
	throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058DAO:deleteSelectedClause");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intStatus=0;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		
		try {
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_DELETE_1058_CHNG_REQ);
			
			objCallableStatement.setInt(1,objChangeRequest1058VO.getSeqNo1058());
			objCallableStatement.setInt(2,objChangeRequest1058VO.getClaChngReqSeq());
			objCallableStatement.setString(3,objChangeRequest1058VO.getUserID());
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			if (intStatus > 0) {
				intStatus = 0;				
			}			
			
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
		return intStatus;
			
	}
	
	
	public static ArrayList reviseSelectedClause(ChangeRequest1058VO objChangeRequest1058VO)
	throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058DAO:reviseSelectedClause");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		ResultSet objResultSet=null;
		ResultSet objResultSetEDLNO = null;
		ResultSet objResultSetRefEDLNO = null;
		ResultSet objResultSetPartOf = null;
		ResultSet objResultSetStdEquip = null;
		ResultSet objResultSetTableData = null;
		ResultSet objResultSetComponents =null;
		ResultSet objResultSetLeadComponent =null;
		ArrayList arlReviseClauseDetails=new ArrayList();
		ArrayList arlTableRows = new ArrayList();	
		ArrayList arlComponents=new ArrayList();
		
		ClauseVO objClauseVO=null;
		ComponentVO objComponentVO =null;
		
		try {
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_SEL_1058_CHNG_CLA_DTLS);
			
			objCallableStatement.setInt(1,objChangeRequest1058VO.getSeqNo1058());
			objCallableStatement.setInt(2,objChangeRequest1058VO.getClaChngReqSeq());
			objCallableStatement.setString(3,objChangeRequest1058VO.getUserID());
			objCallableStatement.registerOutParameter(4,OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			
			objCallableStatement.execute();
			objResultSet = (ResultSet) objCallableStatement.getObject(4);
			
			LogUtil
			.logMessage("ResultSet Value in ChangeRequest1058DAO:fetch1058Details:"
					+ objResultSet.getFetchSize());
			
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
			
			while(objResultSet.next()){
				int cntEDL = 0;
				int cntRefEDl = 0;
				int cntPartOf = 0;
				int[] arlPartSubSecSeqNo = new int[4];
				String[] arlEDLNos = { "", "", "" };
				String[] arlRefEDLNos = { "", "", "" };
				String[] arlpartSubSecCode = { "", "", "", "" };
				int[] arlClauseSeqNo = new int[4];
				
				objClauseVO=new ClauseVO();
				objClauseVO.setSectionSeqNo(objResultSet.getInt(DatabaseConstants.LS201_SEC_SEQ_NO));
				objClauseVO.setSectionName(objResultSet.getString(DatabaseConstants.LS201_SEC_NAME));
				objClauseVO.setSecCode(objResultSet.getString(DatabaseConstants.LS201_SEC_CODE));
				objClauseVO.setSectionDesc(objResultSet.getString(DatabaseConstants.LS201_SEC_DESC));
				objClauseVO.setModelSeqNo(objResultSet.getInt(DatabaseConstants.LS200_MDL_SEQ_NO));
				objClauseVO.setSubSectionSeqNo(objResultSet.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO));
				objClauseVO.setSubSectionName(objResultSet.getString(DatabaseConstants.LS202_SUBSEC_NAME));
				objClauseVO.setSubSecCode(objResultSet.getString(DatabaseConstants.SUB_CODE));
				objClauseVO.setSubSectionDesc(objResultSet.getString(DatabaseConstants.LS202_SUBSEC_DESC));
				objClauseVO.setLeadCompGrpSeqNo(objResultSet.getInt(DatabaseConstants.LS910_LEAD_COMP_GRP_SEQ_NO));
				objClauseVO.setLeadComponentGrpName(objResultSet.getString(DatabaseConstants.LS130_COMP_GRP_NAME));
				objClauseVO.setOldComponentSeqNo(objResultSet.getInt(DatabaseConstants.LS910_OLD_COMP_SEQ_NO));
				objClauseVO.setOldComponentName(objResultSet.getString(DatabaseConstants.LS140_COMP_NAME));
								
				objResultSetLeadComponent=(ResultSet)objResultSet.getObject(DatabaseConstants.LEAD_COMPONENT);
				while(objResultSetLeadComponent.next()){
					objClauseVO.setLeadComponentSeqNo(objResultSetLeadComponent.getInt(DatabaseConstants.LS140_COMP_SEQ_NO));
					objClauseVO.setLeadComponentName(objResultSetLeadComponent.getString(DatabaseConstants.LS140_COMP_NAME));
				}				
				
				objClauseVO.setClauseChangeType(objResultSet.getInt(DatabaseConstants.LS903_CHANGE_TYPE_SEQ_NO));
				objClauseVO.setClauseChangeTypeDesc(objResultSet.getString(DatabaseConstants.LS903_CHANGE_TYPE_DESC));
				objClauseVO.setChangeFromClaNo(objResultSet.getString(DatabaseConstants.LS910_CHANGE_FROM_CLA_NUM));
				objClauseVO.setChangeFromClaSeqNo(objResultSet.getInt(DatabaseConstants.LS910_CHANGE_FROM_CLA_SEQ_NO));
				objClauseVO.setChangeFromClaVerNo(objResultSet.getInt(DatabaseConstants.LS910_CHANGE_FROM_VER_NO));
				objClauseVO.setChangeToClaNo(objResultSet.getString(DatabaseConstants.LS910_CHANGE_TO_CLA_NUM));
				objClauseVO.setChangeToClaSeqNo(objResultSet.getInt(DatabaseConstants.LS910_CHANGE_TO_CLA_SEQ_NO));
				objClauseVO.setChangeToClaVerNo(objResultSet.getInt(DatabaseConstants.LS910_CHANGE_TO_VER_NO));
				objClauseVO.setClauseDescForTextArea(objResultSet.getString(DatabaseConstants.LS910_CHANGE_TO_CLA_DESC));
				objClauseVO.setParentClauseSeqNo(objResultSet.getInt(DatabaseConstants.LS910_CHANGE_TO_PRNT_CLA));
				objClauseVO.setParentClauseDesc(objResultSet.getString(DatabaseConstants.LS301_CLA_DESC));
				objClauseVO.setEngDataComment(objResultSet.getString(DatabaseConstants.LS910_ENG_DATA_COMMENTS));
				objClauseVO.setReason(objResultSet.getString(DatabaseConstants.LS910_REASON));
				objClauseVO.setDwONumber(objResultSet.getInt(DatabaseConstants.LS910_DWO_NO));
				objClauseVO.setPriceBookNumber(objResultSet.getInt(DatabaseConstants.LS910_PRICE_BOOK_NO));
				objClauseVO.setPartNumber(objResultSet.getInt(DatabaseConstants.LS910_PART_NO));
				
				objResultSetEDLNO = (ResultSet) objResultSet.getObject("EDLNO");
				while (objResultSetEDLNO.next()) {
									
					LogUtil.logMessage("Enters into EDLNo Resultset of reviseSelectedClause");
					arlEDLNos[cntEDL] = objResultSetEDLNO
					.getString(DatabaseConstants.LS911_EDL_NO);
					cntEDL++;
				}
				objClauseVO.setEdlNo(arlEDLNos);
				DBHelper.closeSQLObjects(objResultSetEDLNO, null, null);
				
				objResultSetRefEDLNO = (ResultSet) objResultSet.getObject("refEDLNO");
	
				while (objResultSetRefEDLNO.next()) {
					
					LogUtil.logMessage("Enters into RefEDLNo Resultset Loop of reviseSelectedClause");
					arlRefEDLNos[cntRefEDl] = objResultSetRefEDLNO
					.getString(DatabaseConstants.LS912_REF_EDL_NO);
					cntRefEDl++;
				}
				objClauseVO.setRefEDLNo(arlRefEDLNos);
				DBHelper.closeSQLObjects(objResultSetRefEDLNO, null, null);
				
				objResultSetPartOf = (ResultSet) objResultSet.getObject("PartOF");				
				
				while (objResultSetPartOf.next()) {
					
					
					LogUtil
					.logMessage("PartCode Of values"
							+ objResultSetPartOf
							.getString(DatabaseConstants.LS913_PART_OF_CLA_NO));
										
					arlpartSubSecCode[cntPartOf] = objResultSetPartOf
					.getString(DatabaseConstants.LS913_PART_OF_CLA_NO);
					arlClauseSeqNo[cntPartOf] = objResultSetPartOf
					.getInt(DatabaseConstants.LS913_PART_OF_CLA_SEQ_NO);
					arlPartSubSecSeqNo[cntPartOf] = objResultSetPartOf
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
				
				DBHelper.closeSQLObjects(objResultSetPartOf, null, null);
				
				objResultSetTableData = (ResultSet) objResultSet.getObject("TABLE_DATE");
				
				arlTableRows = new ArrayList();
				ArrayList arlTableColumns = new ArrayList();
				while (objResultSetTableData.next()) {
					
					arlTableColumns = new ArrayList();
					
					arlTableColumns.add(objResultSetTableData
							.getString(DatabaseConstants.LS915_TBL_DATA_COL_1));
					arlTableColumns.add(objResultSetTableData
							.getString(DatabaseConstants.LS915_TBL_DATA_COL_2));
					arlTableColumns.add(objResultSetTableData
							.getString(DatabaseConstants.LS915_TBL_DATA_COL_3));
					arlTableColumns.add(objResultSetTableData
							.getString(DatabaseConstants.LS915_TBL_DATA_COL_4));
					arlTableColumns.add(objResultSetTableData
							.getString(DatabaseConstants.LS915_TBL_DATA_COL_5));
					// tableData6.add(objResultSetTableData.getString(DatabaseConstants.LS306_HEADER_FLAG));
					arlTableRows.add(arlTableColumns);
					LogUtil.logMessage("exists Table Data");
					
				}
				
				DBHelper.closeSQLObjects(objResultSetTableData, null, null);
				
				LogUtil.logMessage("arlTableRows : " + arlTableRows.size());
				
				if (arlTableRows.size() > 0)
					objClauseVO.setTableArrayData1(arlTableRows);
				
				objResultSetStdEquip = (ResultSet) objResultSet
				.getObject("STD_EQUIP");
				
				if (objResultSetStdEquip.next()) {
					
					LogUtil.logMessage("Enters into std equip ResultSet");
					objClauseVO.setStandardEquipmentDesc(objResultSetStdEquip
							.getString(DatabaseConstants.LS060_STD_EQP_DESC));
					objClauseVO.setStandardEquipmentSeqNo(objResultSetStdEquip
							.getInt(DatabaseConstants.LS060_STD_EQP_SEQ_NO));
					LogUtil.logMessage("StandardEquipmentDesc:"
							+ objClauseVO.getStandardEquipmentDesc());
					}
				DBHelper.closeSQLObjects(objResultSetStdEquip, null, null);
				
				
				objResultSetComponents =(ResultSet)objResultSet
				.getObject("COMPONENTS");
				String strComps ="";
				while(objResultSetComponents.next()){
					String compTied =objResultSetComponents.getString(DatabaseConstants.COMP_TIED);
					
					if("Y".equalsIgnoreCase(compTied) || compTied=="Y"){
					objComponentVO =new ComponentVO();
					objComponentVO.setComponentSeqNo(objResultSetComponents.getInt(DatabaseConstants.LS140_COMP_SEQ_NO));
					strComps+=objComponentVO.getComponentSeqNo()+"~";
					objComponentVO.setComponentName(objResultSetComponents.getString(DatabaseConstants.LS140_COMP_NAME));
					arlComponents.add(objComponentVO);
					}
				}
				
				objClauseVO.setComponentVO(arlComponents);
				objClauseVO.setStrComps(strComps);
				arlReviseClauseDetails.add(objClauseVO);
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
		return arlReviseClauseDetails;
			
	}
	
	public static int updateClause(ClauseVO objClauseVO)
	throws EMDException {
		LogUtil.logMessage("Inside the OrderClauseDAO:updateClause");
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
		
		String strLogUser = "";
		try {
			strLogUser = objClauseVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_UPD_1058_CHNG_CLA_DTLS);
			Connection dconn = ((DelegatingConnection) objConnection).getInnermostDelegate(); //Added for CR-123 
			if (objClauseVO.getSeqNo1058()!=0)
				objCallableStatement.setInt(1, objClauseVO.getSeqNo1058());
			else{
				objCallableStatement.setNull(1, Types.NULL);}			
			if(objClauseVO.getClauseChangeReqSeqNo()!=0){
				objCallableStatement.setInt(2, objClauseVO.getClauseChangeReqSeqNo());
			}
			else{objCallableStatement.setNull(2, Types.NULL);}			
			if (objClauseVO.getClauseChangeType()!=0)
				objCallableStatement.setInt(3, objClauseVO.getClauseChangeType());
			else{
				objCallableStatement.setNull(3, Types.NULL);}
			if (objClauseVO.getChangeToClaSeqNo()!=0)
				objCallableStatement.setInt(4, objClauseVO.getChangeToClaSeqNo());
			else{
				objCallableStatement.setNull(4, Types.NULL);}			
			if (objClauseVO.getChangeToClaVerNo()!=0)
				objCallableStatement.setInt(5, objClauseVO.getChangeToClaVerNo());
			else{
				objCallableStatement.setNull(5, Types.NULL);}			
			if (objClauseVO.getClauseDesc()!=null)
				objCallableStatement.setString(6, objClauseVO.getClauseDesc());
			else{
				objCallableStatement.setNull(6, Types.NULL);}			
			arrdesc = new ArrayDescriptor(DatabaseConstants.STR_ARRAY,
					dconn);			
			arlTableList = objClauseVO.getTableDataVO();
			objTableDataVO = (ClauseTableDataVO) arlTableList.get(0);
			tableDataArr1 = new ARRAY(arrdesc, dconn, objTableDataVO
					.getTableArrayData1());
			if (objTableDataVO.getTableArrayData1() == null) {
				tableDataArr1 = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(7, tableDataArr1);
			} else {
				objCallableStatement.setArray(7, tableDataArr1);
			}
			
			objTableDataVO = (ClauseTableDataVO) arlTableList.get(1);
			tableDataArr2 = new ARRAY(arrdesc, dconn, objTableDataVO
					.getTableArrayData2());			
			if (objTableDataVO.getTableArrayData2() == null) {
				tableDataArr2 = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(8, tableDataArr2);
			} else {
				objCallableStatement.setArray(8, tableDataArr2);
			}			
			objTableDataVO = (ClauseTableDataVO) arlTableList.get(2);
			tableDataArr3 = new ARRAY(arrdesc, dconn, objTableDataVO
					.getTableArrayData3());			
			if (objTableDataVO.getTableArrayData3() == null) {
				tableDataArr3 = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(9, tableDataArr3);
			} else {
				objCallableStatement.setArray(9, tableDataArr3);
			}			
			objTableDataVO = (ClauseTableDataVO) arlTableList.get(3);
			tableDataArr4 = new ARRAY(arrdesc, dconn, objTableDataVO
					.getTableArrayData4());			
			if (objTableDataVO.getTableArrayData4() == null) {
				tableDataArr4 = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(10, tableDataArr4);
			} else {
				objCallableStatement.setArray(10, tableDataArr4);
			}			
			objTableDataVO = (ClauseTableDataVO) arlTableList.get(4);
			tableDataArr5 = new ARRAY(arrdesc, dconn, objTableDataVO
					.getTableArrayData5());
			if (objTableDataVO.getTableArrayData5() == null) {
				tableDataArr5 = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(11, tableDataArr5);
			} else {
				objCallableStatement.setArray(11, tableDataArr5);
			}			
			if(objClauseVO.getComponentVO()!=null){
				arr = new ARRAY(arrdesc, dconn,
						processComponentVO(objClauseVO));
				objCallableStatement.setArray(12, arr);
				}
				else{
					arr =new ARRAY(arrdesc, dconn, null);
					objCallableStatement.setArray(12, arr);
				}
			ARRAY arrRefEDLNO = new ARRAY(arrdesc, dconn, objClauseVO
					.getRefEDLNo());
			
			if (objClauseVO.getRefEDLNo() == null) {
				arrRefEDLNO = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(13, arrRefEDLNO);
			} else {
				objCallableStatement.setArray(13, arrRefEDLNO);
			}
			ARRAY arrEdlno = new ARRAY(arrdesc, dconn, objClauseVO
					.getEdlNo());
			if (objClauseVO.getEdlNo() == null) {
				arrEdlno = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(14, arrEdlno);
			} else {
				objCallableStatement.setArray(14, arrEdlno);
			}			
			ARRAY arrPartOfSeqNO = new ARRAY(arrdesc, dconn,
					objClauseVO.getPartOfSeqNo());
			if (objClauseVO.getPartOfSeqNo() == null) {
				arrPartOfSeqNO = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(15, arrPartOfSeqNO);
			} else {
				objCallableStatement.setArray(15, arrPartOfSeqNO);
			}
			ARRAY arrClauseSeqNo = new ARRAY(arrdesc, dconn,
					objClauseVO.getClauseSeqNoArray());
			
			if (objClauseVO.getClauseSeqNoArray() == null) {
				arrClauseSeqNo = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(16, arrClauseSeqNo);
			} else {
				objCallableStatement.setArray(16, arrClauseSeqNo);
			}
			ARRAY arrClauseNo = new ARRAY(arrdesc, dconn, objClauseVO
					.getClauseNoArray());
			if (objClauseVO.getClauseNoArray() == null) {
				arrClauseNo = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(17, arrClauseNo);
			} else {
				objCallableStatement.setArray(17, arrClauseNo);
			}
			if (objClauseVO.getDwONumber()!=0)
				objCallableStatement.setInt(18, objClauseVO.getDwONumber());
			else{
				objCallableStatement.setNull(18, Types.NULL);}
			
			if (objClauseVO.getPartNumber()!=0)
				objCallableStatement.setInt(19, objClauseVO.getPartNumber());
			else{
				objCallableStatement.setNull(19, Types.NULL);}
			
			if (objClauseVO.getPriceBookNumber() == 0) {
				objCallableStatement.setNull(20, Types.NULL);
			} else {
				objCallableStatement.setInt(20, objClauseVO
						.getPriceBookNumber());
			}
			if (objClauseVO.getObjStandardEquipVO() == null) {
				objCallableStatement.setNull(21, Types.NULL);
			} else {
				arlStandardEquipmentList = objClauseVO.getObjStandardEquipVO();
				StandardEquipVO objStandardEquipVO = (StandardEquipVO) arlStandardEquipmentList
				.get(0);
				objCallableStatement.setInt(21, objStandardEquipVO
						.getStandardEquipmentSeqNo());
			}
			
			if (objClauseVO.getComments() == null) {
				objCallableStatement.setNull(22, Types.NULL);
			} else {
				objCallableStatement.setString(22, objClauseVO.getComments());
			}
			objCallableStatement.setString(23, objClauseVO.getReason());
			objCallableStatement.setString(24,strLogUser);
			objCallableStatement.registerOutParameter(25, Types.INTEGER);
			objCallableStatement.registerOutParameter(26, Types.VARCHAR);
			objCallableStatement.registerOutParameter(27, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			if (intStatus > 0) {
				intStatus = 0;				
			}
			intLSDBErrorID = (int) objCallableStatement.getInt(25);
			strOracleCode = (String) objCallableStatement.getString(26);
			strErrorMessage = (String) objCallableStatement.getString(27);
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
			
			LogUtil.logMessage("Completed the DAo ChangeRequest1058DAO:updateClause");
			
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
		return intStatus;
			
	}
	
	
	public static int updateDeleteClause(ClauseVO objClauseVO)
	throws EMDException {
		LogUtil.logMessage("Inside the OrderClauseDAO:updateDeleteClause");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		ArrayDescriptor arrdesc = null;
		ARRAY arr = null;
		ARRAY tableDataArr1, tableDataArr2, tableDataArr3, tableDataArr4, tableDataArr5 = null;
		
		String strLogUser = "";
		try {
			strLogUser = objClauseVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_UPD_1058_CHNG_CLA_DTLS);
			Connection dconn = ((DelegatingConnection) objConnection).getInnermostDelegate(); //Added for CR-123 
			if (objClauseVO.getSeqNo1058()!=0)
				objCallableStatement.setInt(1, objClauseVO.getSeqNo1058());
			else{
				objCallableStatement.setNull(1, Types.NULL);}
			
			if(objClauseVO.getClauseChangeReqSeqNo()!=0){
				objCallableStatement.setInt(2, objClauseVO.getClauseChangeReqSeqNo());
			}
			else{objCallableStatement.setNull(2, Types.NULL);}
			
			if (objClauseVO.getClauseChangeType()!=0)
				objCallableStatement.setInt(3, objClauseVO.getClauseChangeType());
			else{
				objCallableStatement.setNull(3, Types.NULL);
			}
			objCallableStatement.setInt(4, 0);
			objCallableStatement.setInt(5, 0);	
			if (objClauseVO.getClauseDesc()!=null)
				objCallableStatement.setString(6, objClauseVO.getClauseDesc());
			else{
				objCallableStatement.setNull(6, Types.NULL);}
			
			arrdesc = new ArrayDescriptor(DatabaseConstants.STR_ARRAY,
					dconn);
			
			tableDataArr1 = new ARRAY(arrdesc, dconn, null);
			objCallableStatement.setArray(7, tableDataArr1);
		
			tableDataArr2 = new ARRAY(arrdesc, dconn, null);
			objCallableStatement.setArray(8, tableDataArr2);
		
			tableDataArr3 = new ARRAY(arrdesc, dconn, null);
			objCallableStatement.setArray(9, tableDataArr3);
		
			tableDataArr4 = new ARRAY(arrdesc, dconn, null);
			objCallableStatement.setArray(10, tableDataArr4);
		
			tableDataArr5 = new ARRAY(arrdesc, dconn, null);
			objCallableStatement.setArray(11, tableDataArr5);
		
			arr =new ARRAY(arrdesc, dconn, null);
			objCallableStatement.setArray(12, arr);
		
			ARRAY arrRefEDLNO = new ARRAY(arrdesc, dconn, null);
			objCallableStatement.setArray(13, arrRefEDLNO);
		
		//EDL NO
		
		
			ARRAY arrEdlno = new ARRAY(arrdesc, dconn, null);
			objCallableStatement.setArray(14, arrEdlno);
		
		//Part Of SeqNO
		
		
			ARRAY arrPartOfSeqNO = new ARRAY(arrdesc, objConnection, null);
			objCallableStatement.setArray(15, arrPartOfSeqNO);
				
		ARRAY  arrClauseSeqNo = new ARRAY(arrdesc, objConnection, null);
			objCallableStatement.setArray(16, arrClauseSeqNo);
				
		ARRAY arrClauseNo = new ARRAY(arrdesc, objConnection, null);
			objCallableStatement.setArray(17, arrClauseNo);
			
			objCallableStatement.setNull(18, Types.NULL);
			
			
			objCallableStatement.setNull(19, Types.NULL);
		
		
			objCallableStatement.setNull(20, Types.NULL);
				
			objCallableStatement.setNull(21, Types.NULL);
		
		
			objCallableStatement.setNull(22, Types.NULL);
		
		objCallableStatement.setString(23, objClauseVO.getReason());
		objCallableStatement.setString(24,strLogUser);
		objCallableStatement.registerOutParameter(25, Types.INTEGER);
		objCallableStatement.registerOutParameter(26, Types.VARCHAR);
		objCallableStatement.registerOutParameter(27, Types.VARCHAR);
		intStatus = objCallableStatement.executeUpdate();
		LogUtil.logMessage("Status:" + intStatus);
		
		if (intStatus > 0) {
			intStatus = 0;			
		}
			intLSDBErrorID = (int) objCallableStatement.getInt(25);
			strOracleCode = (String) objCallableStatement.getString(26);
			strErrorMessage = (String) objCallableStatement.getString(27);
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
			
			LogUtil.logMessage("Completed the DAo ChangeRequest1058DAO:updateDeleteClause");
			
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
		return intStatus;
			
	}
	
	
	
	public static int updateClausesToSpec(ChangeRequest1058VO objChangeRequest1058VO)
	throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058DAO:updateClausesToSpec");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intStatus=0;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		
		try {
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_UPD_CHNG_TO_SPEC);
			
			objCallableStatement.setInt(1,objChangeRequest1058VO.getSeqNo1058());
			LogUtil.logMessage("Seq NO in DAO " + objChangeRequest1058VO.getSeqNo1058());
			if(objChangeRequest1058VO.getClaChngReqSeq()!=0){
				objCallableStatement.setInt(2,objChangeRequest1058VO.getClaChngReqSeq());}
			else{
				objCallableStatement.setNull(2,Types.NULL);
			}
			
			objCallableStatement.setString(3,objChangeRequest1058VO.getUserID());
			
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			
			if (intStatus > 0)
				intStatus = 0;
			
			intLSDBErrorID = objCallableStatement.getInt(4);
			strOracleCode = objCallableStatement.getString(5);
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
		return intStatus;
			
	}
	//Added by @rr108354 for CR-110 ends here
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objChangeRequest1058VO
	 * @return int The status for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int update1058details(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException {
		LogUtil.logMessage("Entering ChangeRequest1058DAO.update1058details");
		
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intStatusCode = 0;
		int intLSDBErrorID = 0;
		String strMessage = null;
		String strLogUser = "";
		try {
			strLogUser = objChangeRequest1058VO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_UPDATE_1058_DETAILS);
			
			objCallableStatement.setInt(1, objChangeRequest1058VO.getSeqNo1058());
			LogUtil.logMessage("objChangeRequest1058VO.getSeqNo1058() :" + objChangeRequest1058VO.getSeqNo1058());
			if(objChangeRequest1058VO.getCustName() == null || objChangeRequest1058VO.getCustName().equalsIgnoreCase("")){
				objCallableStatement.setNull(2, Types.NULL);
			}else{
				objCallableStatement.setString(2, objChangeRequest1058VO.getCustName());
			}
			if(objChangeRequest1058VO.getMdlName() == null || objChangeRequest1058VO.getMdlName().equalsIgnoreCase("")){
				objCallableStatement.setNull(3, Types.NULL);
			}else{
				objCallableStatement.setString(3, objChangeRequest1058VO.getMdlName());
			}
			if(objChangeRequest1058VO.getOrderQty() == 0 ){
				objCallableStatement.setNull(4, Types.NULL);
			}else{
				objCallableStatement.setInt(4, objChangeRequest1058VO.getOrderQty());
			}
			if(objChangeRequest1058VO.getCategorySeqNo() == 0 ){
				objCallableStatement.setNull(5, Types.NULL);
			}else{
				objCallableStatement.setInt(5, objChangeRequest1058VO.getCategorySeqNo());
			}
			if(objChangeRequest1058VO.getReqTypeSeqNo() == 0 ){
				objCallableStatement.setNull(6, Types.NULL);
			}else{
				objCallableStatement.setInt(6, objChangeRequest1058VO.getReqTypeSeqNo());
			}
			if(objChangeRequest1058VO.getGenDesc() == null || objChangeRequest1058VO.getGenDesc().equalsIgnoreCase("")){
				objCallableStatement.setNull(7, Types.NULL);
			}else{
				objCallableStatement.setString(7, objChangeRequest1058VO.getGenDesc());
			}
			if(objChangeRequest1058VO.getUnitNumber() == null || objChangeRequest1058VO.getUnitNumber().equalsIgnoreCase("")){
				objCallableStatement.setNull(8, Types.NULL);
			}else{
				objCallableStatement.setString(8, objChangeRequest1058VO.getUnitNumber());
			}
			if(objChangeRequest1058VO.getMcrReq() == 0 ){
				objCallableStatement.setNull(9, Types.NULL);
			}else{
				objCallableStatement.setInt(9, objChangeRequest1058VO.getMcrReq());
			}
			if(objChangeRequest1058VO.getMcrNumber() == null || objChangeRequest1058VO.getMcrNumber().equalsIgnoreCase("")){
				objCallableStatement.setNull(10, Types.NULL);
			}else{
				objCallableStatement.setString(10, objChangeRequest1058VO.getMcrNumber());
			}
			if(objChangeRequest1058VO.getRoadNumber() == null || objChangeRequest1058VO.getRoadNumber().equalsIgnoreCase("")){
				objCallableStatement.setNull(11, Types.NULL);
			}else{
				objCallableStatement.setString(11, objChangeRequest1058VO.getRoadNumber());
			}
			if(objChangeRequest1058VO.getSpecRev() == null || objChangeRequest1058VO.getSpecRev().equalsIgnoreCase("")){
				objCallableStatement.setNull(12, Types.NULL);
			}else{
				objCallableStatement.setString(12, objChangeRequest1058VO.getSpecRev());
			}
			objCallableStatement.setString(13, objChangeRequest1058VO.getUserID());
			objCallableStatement.registerOutParameter(14, Types.INTEGER);
			objCallableStatement.registerOutParameter(15, Types.VARCHAR);
			objCallableStatement.registerOutParameter(16, Types.VARCHAR);
			LogUtil.logMessage(""+ objChangeRequest1058VO.getOrderQty());
			
			intStatusCode = objCallableStatement.executeUpdate();
			
			LogUtil.logMessage("Update Result:" + intStatusCode);
			if (intStatusCode > 0) {
				intStatusCode = 0;
			}
			LogUtil.logMessage("Status Update" + intStatusCode);
			intLSDBErrorID = objCallableStatement.getInt(14);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(15);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(16);
			
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
			LogUtil
			.logMessage("Enters into DataAccessException ChangeRequest1058DAO.complete");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in ChangeRequest1058DAO.complete"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException ChangeRequest1058DAO.complete");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into catch block in ChangeRequest1058DAO.complete"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception ChangeRequest1058DAO.complete");
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
				LogUtil
				.logMessage("Enters into Exception ChangeRequest1058DAO.complete");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}		
		return intStatusCode;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objChangeRequest1058VO
	 *            The Object for Action Records
	 * @return int The status for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int insert1058ActionRecord(ChangeRequest1058VO objChangeRequest1058VO)
	throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058DAO:insert1058ActionRecord");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intStatusCode=0;
		int intLSDBErrorID = 0;	
		String strLogUser = "";
		try {			
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_INSERT_1058_ACTION);			 		
			objCallableStatement.setInt(1, objChangeRequest1058VO.getSeqNo1058());
			if (objChangeRequest1058VO.getSectionSeqNo() != 0) {
				objCallableStatement.setInt(2, objChangeRequest1058VO
						.getSectionSeqNo());
			} else {
				objCallableStatement.setNull(2, Types.NULL);
			}
			if (objChangeRequest1058VO.getSectionStatus() != 0) {
				objCallableStatement.setInt(3, objChangeRequest1058VO
						.getSectionStatus());
			} else {
				objCallableStatement.setNull(3, Types.NULL);
			}
			objCallableStatement.setString(4, objChangeRequest1058VO.getUserID());
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);

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
	       
		}
		catch (DataAccessException objDataExp) {
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
	
	//Added for CR-117
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objChangeRequest1058VO
	 *            The Object for uploadLegacyReport
	 * @return int The status for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int uploadLegacyReport(ChangeRequest1058VO objChangeRequest1058VO)
	throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058DAO:uploadLegacyReport");
		
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intStatusCode=0;
		int intLSDBErrorID = 0;	
		String strLogUser = "";
		try {			
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_INSERT_1058_LEGACY_REQ);
			
			objCallableStatement.setString(1, objChangeRequest1058VO.getNumber1058());
			objCallableStatement.setInt(2, objChangeRequest1058VO.getStatusSeqNo());
			objCallableStatement.setString(3, objChangeRequest1058VO.getOrderNo());
			objCallableStatement.setString(4, objChangeRequest1058VO.getModel());
			objCallableStatement.setString(5, objChangeRequest1058VO.getCustomer());
			if(objChangeRequest1058VO.getGenDesc() == null || 
			   objChangeRequest1058VO.getGenDesc().equalsIgnoreCase("")){
				objCallableStatement.setNull(6,Types.NULL);
			}else{
				objCallableStatement.setString(6, objChangeRequest1058VO.getGenDesc());	
			}
			objCallableStatement.setString(7, objChangeRequest1058VO.getIssuedBy());
			objCallableStatement.setString(8, objChangeRequest1058VO.getSpecRev());
			objCallableStatement.setString(9, objChangeRequest1058VO.getUploadAttachment());
			objCallableStatement.setBigDecimal(10, objChangeRequest1058VO.getActualSellPrice());
			
			//Added for CR_118 Legacy procedure change effect
			LogUtil.logMessage("value of design hrs in DAO "+objChangeRequest1058VO.getDesignHrs());
			LogUtil.logMessage("value of drafting hrs in DAO "+objChangeRequest1058VO.getDesignHrs());
			LogUtil.logMessage("value of work order USD in DAO "+objChangeRequest1058VO.getWorkOrderUSD());
			
			
			if("".equalsIgnoreCase(objChangeRequest1058VO.getDesignHrs()) || objChangeRequest1058VO.getDesignHrs()== null){
				objCallableStatement.setNull(11,Types.NULL);
			}else{
				objCallableStatement.setString(11, objChangeRequest1058VO.getDesignHrs());
			}
			
			if("".equalsIgnoreCase(objChangeRequest1058VO.getDraftingHrs()) || objChangeRequest1058VO.getDraftingHrs()== null){
				objCallableStatement.setNull(12,Types.NULL);
			}else{
				objCallableStatement.setString(12, objChangeRequest1058VO.getDraftingHrs());
			}
			
			objCallableStatement.setBigDecimal(13, objChangeRequest1058VO.getWorkOrderUSD());
			
			//Added for CR_118 Ends Legacy procedure change effect
			
			objCallableStatement.setString(14, objChangeRequest1058VO.getUserID());
			
			objCallableStatement.registerOutParameter(15, Types.INTEGER);
			objCallableStatement.registerOutParameter(16, Types.VARCHAR);
			objCallableStatement.registerOutParameter(17, Types.VARCHAR);

			intStatusCode = objCallableStatement.executeUpdate();
			
			LogUtil.logMessage("Update Result:" + intStatusCode);
			if (intStatusCode > 0) {
				intStatusCode = 0;
			}
			LogUtil.logMessage("Status Update" + intStatusCode);
			intLSDBErrorID = objCallableStatement.getInt(15);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(16);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(17);
			
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
	       
		}
		catch (DataAccessException objDataExp) {
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
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objChangeRequest1058VO
	 *            The Object for delete1058Request
	 * @return int The status for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int delete1058Request(ChangeRequest1058VO objChangeRequest1058VO)
	throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058DAO:delete1058Request");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intStatus=0;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		ArrayDescriptor arrayDescriptor = null;
		ARRAY objArray = null;
		
		try {
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_DELETE_1058_REQUEST);
			
			Connection dconn = ((DelegatingConnection) objConnection).getInnermostDelegate(); //Added for CR-123 
			arrayDescriptor = new ArrayDescriptor(DatabaseConstants.IN_ARRAY, dconn);
			if(objChangeRequest1058VO.getSeqNo() != null )
			{
				objArray = new ARRAY(arrayDescriptor, dconn,objChangeRequest1058VO.getSeqNo());
			}
			else
			{
				LogUtil.logMessage("objChangeRequest1058VO.getModelSeqNos():null");
				objArray = new ARRAY(arrayDescriptor, dconn, null); 
			}
			objCallableStatement.setArray(1,objArray);
			objCallableStatement.setString(2,objChangeRequest1058VO.getUserID());
			
			objCallableStatement.registerOutParameter(3, Types.INTEGER);
			objCallableStatement.registerOutParameter(4, Types.VARCHAR);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			if (intStatus > 0) {
				intStatus = 0;				
			}			
			
			intLSDBErrorID = objCallableStatement.getInt(3);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(4);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(5);
			
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
		return intStatus;
			
	}
	
	//Added for CR-117 Ends here
	
	//Added For CR-120 
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @return arrayList the list contains the All Users List 
	 * @throws EMDException
	 **************************************************************************/
	/*public static ArrayList fetchUsers(UserVO objUserVO) throws EMDException {
		LogUtil.logMessage("Entering UserDAODAO.fetchUsers");
		System.out.println("entering the UserDAODAO.fetchusers");
		//Connection objConnnection = null;
		Connection con = null;
		ArrayList arlUsers = new ArrayList();
		ResultSet rsUsers = null;
		//ResultSet rs = null;
		//CallableStatement objCallableStatement = null;
		CallableStatement cs = null;
		
		// Error out parameters
		int intLSDBErrorID = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strLogUser = "";
		try {
			
			strLogUser = objUserVO.getUserID();
			//objConnnection = DBHelper.prepareConnection();
			con = DBHelper.connectionforMariaDB();
			LogUtil.logMessage("objConnnection in DAO :" + objConnnection);
			System.out.println("objConnnection in DAO :" + objConnnection);
			LogUtil.logMessage("con in DAO :" + con);
			System.out.println("con in DAO :" + con);
			objCallableStatement = objConnnection
			.prepareCall(AdmnQueries.SP_FETCH_USER);
			
			cs = con.prepareCall(AdmnQueries.SP_FETCH_USER1);
			objCallableStatement.setString(1, objUserVO.getUserID());
			//cs.setString(1,objUserVO.getUserID());
			cs.setDouble(1,2);
			//Adder For CR_94
			objCallableStatement.setInt(2, objUserVO.getOrderbyFlag());
			//cs.setInt(2, objUserVO.getOrderbyFlag());
			objCallableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			//cs.registerOutParameter(3, java.sqlTypes.CURSOR);
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			//cs.registerOutParameter(4, Types.INTEGER);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			//cs.registerOutParameter(5, Types.VARCHAR);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			//cs.registerOutParameter(6, Types.VARCHAR);
			cs.registerOutParameter(2,Types.VARCHAR);
			//objCallableStatement.execute();
			cs.execute();
			
			rsUsers = (ResultSet) objCallableStatement.getObject(3);
			LogUtil
			.logMessage("Inside the fetchUsers method of UserDAO :resultSet"
					+ rsUsers);
			System.out.println("Inside the fetchUsers method of UserDAO :resultSet"
					+ rsUsers);
			rsUsers = (ResultSet) cs.getObject(2);
			//rs = (ResultSet) cs.getObject(3);
			//LogUtil.logMessage("Inside the fetchUsers method of UserDAO :resultSet"+ rs);
			//System.out.println("Inside the fetchUsers method of UserDAO :resultSet" + rs);
			
			intLSDBErrorID = (int) objCallableStatement.getInt(4);
			//intLSDBErrorID = (int) cs.getInt(4);
			strOracleCode = (String) objCallableStatement.getString(5);
			//strOracleCode = (String) cs.getString(5);
			strErrorMessage = (String) objCallableStatement.getString(6);
			//strErrorMessage = (String) cs.getString(6);
			
			// Handled Valid Exception
			if (intLSDBErrorID != 0) {
				
				ErrorInfo objErrorInfo = new ErrorInfo();
				
				objErrorInfo.setMessageID("" + intLSDBErrorID);
				
				throw new DataAccessException(objErrorInfo);
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {// Un
				// handled
				// exception
				
				LogUtil.logMessage("strOracleCode:" + strOracleCode);
				System.out.println("strOracleCode:" + strOracleCode);
				ErrorInfo objErrorInfo = new ErrorInfo();
				StringBuffer sb = new StringBuffer();
				sb.append(strOracleCode + " ");
				sb.append(strErrorMessage);
				objErrorInfo.setMessage(sb.toString());
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
			while (rsUsers.next()) {
				objUserVO = new UserVO();
				LogUtil.logMessage("Inside ResultSet");
				System.out.println("Inside ResultSet");
				objUserVO.setUserrId(rsUsers
						.getString(DatabaseConstants.LS010_USER_ID));
				objUserVO.setUserrId(rs
						.getString(DatabaseConstants.LS010_USER_ID));
				objUserVO.setFirsttName(rsUsers
						.getString(DatabaseConstants.LS010_FIRSTNAME));
				objUserVO.setFirsttName(rs
						.getString(DatabaseConstants.LS010_FIRSTNAME));
				objUserVO.setLasttName(rsUsers
						.getString(DatabaseConstants.LS010_LASTNAME));
				objUserVO.setLasttName(rs
						.getString(DatabaseConstants.LS010_LASTNAME));
				objUserVO.setLocation(rsUsers
						.getString(DatabaseConstants.LS010_LOC));
				objUserVO.setLocation(rs
						.getString(DatabaseConstants.LS010_LOC));
				objUserVO.setEmaillId(rsUsers
						.getString(DatabaseConstants.LS010_EMAIL_ADDRESS));
				objUserVO.setEmaillId(rs
						.getString(DatabaseConstants.LS010_EMAIL_ADDRESS));
				objUserVO.setContacttNo(rsUsers
						.getString(DatabaseConstants.LS010_CONTACT_NUMBER));
				objUserVO.setContacttNo(rs
						.getString(DatabaseConstants.LS010_CONTACT_NUMBER));
				objUserVO.setRole(rsUsers
						.getString(DatabaseConstants.LS120_ROLE_ID));
				objUserVO.setRole(rs
						.getString(DatabaseConstants.LS120_ROLE_ID));
				
				//Added for CR-113
				objUserVO.setRoleName(rsUsers
						.getString(DatabaseConstants.LS120_ROLE_NAME));
				objUserVO.setRoleName(rs
						.getString(DatabaseConstants.LS120_ROLE_NAME));
				//Added for CR-113 ends here
				// String
				// name=(objUserVO.getUserrId()).concat("-").concat(objUserVO.getFirsttName()).concat("
				// ").concat(objUserVO.getLasttName());
				objUserVO.setName((objUserVO.getUserrId()).concat("-").concat(
						objUserVO.getFirsttName()).concat("  ").concat(
								objUserVO.getLasttName()));
				LogUtil.logMessage("Name:" + objUserVO.getName());
				System.out.println("Name:" + objUserVO.getName());
				LogUtil.logMessage("UserID:" + objUserVO.getUserrId());
				System.out.println("UserID:" + objUserVO.getUserrId());
				LogUtil.logMessage("RoleID:" + objUserVO.getRole());
				System.out.println("RoleID:" + objUserVO.getRole());
				LogUtil.logMessage("RoleName:" + objUserVO.getRoleName());
				System.out.println("RoleName:" + objUserVO.getRoleName());
				//Added for CR-109
				objUserVO.setPrevLoggedIn(rsUsers
						.getString(DatabaseConstants.LS010_PREV_LOGGEDIN));
               	//Added for CR-109 Ends here
				
				//Added for CR-126
				objUserVO.setActionNoticeFlag(rsUsers
						.getString(DatabaseConstants.LS011_ACTION_NOTICE));
				objUserVO.setInformationNoticeFlag(rsUsers
						.getString(DatabaseConstants.LS011_INFO_NOTICE));
				objUserVO.setCcNoticeFlag(rsUsers
						.getString(DatabaseConstants.LS011_CC_NOTICE));
				//Added for CR-126 ends  here
				arlUsers.add(objUserVO);
				
			}
			
		} catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException UserMaintDAO.fetchUsers");
			System.out.println("Enters into DataAccessException UserMaintDAO.fetchUsers");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in UserMaintDAO.fetchUsers"
					+ objErrorInfo.getMessageID());
			System.out.println("ENters into catch block in UserMaintDAO.fetchUsers"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException UserMaintDAO.fetchUsers");
			System.out.println("Enters into ApplicationException UserMaintDAO.fetchUsers");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in UserMaintDAO.fetchUsers"
					+ objErrorInfo.getMessage());
			System.out.println("Enters into catch block in UserMaintDAO.fetchUsers"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception UserMaintDAO.fetchUsers");
			System.out.println("Enters into Exception UserMaintDAO.fetchUsers");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(rsUsers, objCallableStatement,
						objConnnection);
				DBHelper.closeSQLObjects(rsUsers, cs, con);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception UserMaintDAO.fetchUsers");
				System.out.println("Enters into Exception UserMaintDAO.fetchUsers");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return arlUsers;
		
	}
	*/
	
	
	
	
	public static ArrayList fetchIssuedByUserList(UserVO objUserVO)
	throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058DAO:fetchIssuedByUserList");
		System.out.println("Enters into ChangeRequest1058DAO:fetchIssuedByUserList");
		//Connection objConnection = null;
		Connection con = null;
		//CallableStatement objCallableStatement = null;
		CallableStatement cs = null;
		ResultSet objResultSet = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		ArrayList arlIssuedByUserList = new ArrayList();
		
		try {
				//objConnection = DBHelper.prepareConnection();
				con = DBHelper.prepareConnection();
				//objCallableStatement = objConnection.prepareCall(AdmnQueries.SP_FETCH_USER);
				cs = con.prepareCall(AdmnQueries.SP_FETCH_USER1);
				
				LogUtil.logMessage("objUserVO.getUserID():"+objUserVO.getUserID());
				System.out.println("objUserVO.getUserID():"+objUserVO.getUserID());
				/*if(objUserVO.getUserID() == null || "".equalsIgnoreCase(objUserVO.getUserID())){
					objCallableStatement.setNull(1, Types.NULL);*/
					/*if(objUserVO.getUserID() == null || "".equalsIgnoreCase(objUserVO.getUserID())){
						cs.setNull(1, Types.NULL);
				}else {
					objCallableStatement.setString(1, objUserVO.getUserID());
				}*/
					cs.setDouble(1,2);
	                cs.registerOutParameter(2,Types.VARCHAR);
				/*objCallableStatement.setInt(2, objUserVO.getOrderbyFlag());
				objCallableStatement.registerOutParameter(3, OracleTypes.CURSOR);
				objCallableStatement.registerOutParameter(4, Types.INTEGER);
				objCallableStatement.registerOutParameter(5, Types.VARCHAR);
				objCallableStatement.registerOutParameter(6, Types.VARCHAR);*/
				
				//objCallableStatement.execute();
				cs.execute();
				
				//objResultSet = (ResultSet) objCallableStatement.getObject(3);
				objResultSet = (ResultSet) cs.getObject(2);
				LogUtil
				.logMessage("Inside the fetchUsers method of UserDAO :resultSet"
						+ objResultSet);
				System.out.println("Inside the fetchUsers method of UserDAO :resultSet"
						+ objResultSet);
				
				/*intLSDBErrorID = (int) objCallableStatement.getInt(4);
				strOracleCode = (String) objCallableStatement.getString(5);
				strErrorMessage = (String) objCallableStatement.getString(6);
				
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
					objConnection.rollback();
					throw new ApplicationException(objErrorInfo);
				}
				*/
			while(objResultSet.next()){
				LogUtil.logMessage("Enters into objResultSet:");
				ChangeRequest1058VO objChangeRequest1058VO =new ChangeRequest1058VO();
				objChangeRequest1058VO.setFirstName(objResultSet.getString(DatabaseConstants.LS010_FIRSTNAME));
				objChangeRequest1058VO.setLastName(objResultSet.getString(DatabaseConstants.LS010_LASTNAME));
				objChangeRequest1058VO.setUserID(objResultSet.getString(DatabaseConstants.LS010_USER_ID));
				objChangeRequest1058VO.setRoleID(objResultSet.getString(DatabaseConstants.LS120_ROLE_ID));
				arlIssuedByUserList.add(objChangeRequest1058VO);
			}
			
			DBHelper.closeSQLObjects(objResultSet, null, null);
			LogUtil.logMessage("arlIssuedByUserList.size():"+arlIssuedByUserList.size());
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
				
				/*DBHelper.closeSQLObjects(null, objCallableStatement,
						objConnection);*/
				DBHelper.closeSQLObjects(null,cs,con);
			}
			
			catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception exception...");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return arlIssuedByUserList;
			
	}
	//Added for CR-120 Ends here
	//Added for CR-126
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objChangeRequest1058VO
	 *            The Object for updateLegacyReport
	 * @return int The status for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int updateLegacyReport(ChangeRequest1058VO objChangeRequest1058VO)
	throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequest1058DAO:updateLegacyReport");

		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intStatusCode=0;
		int intLSDBErrorID = 0;	
		String strLogUser = "";
		try {			
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_UPDATE_1058_LEGACY_REQ);
			
			objCallableStatement.setInt(1, objChangeRequest1058VO.getSeqNo1058());
			objCallableStatement.setString(2, objChangeRequest1058VO.getNumber1058());
			objCallableStatement.setInt(3, objChangeRequest1058VO.getStatusSeqNo());
			objCallableStatement.setString(4, objChangeRequest1058VO.getOrderNo());
			objCallableStatement.setString(5, objChangeRequest1058VO.getModel());
			objCallableStatement.setString(6, objChangeRequest1058VO.getCustomer());
			if(objChangeRequest1058VO.getGenDesc() == null || 
			   objChangeRequest1058VO.getGenDesc().equalsIgnoreCase("")){
				objCallableStatement.setNull(7,Types.NULL);
			}else{
				objCallableStatement.setString(7, objChangeRequest1058VO.getGenDesc());	
			}
			objCallableStatement.setString(8, objChangeRequest1058VO.getIssuedBy());
			objCallableStatement.setString(9, objChangeRequest1058VO.getSpecRev());
			if(objChangeRequest1058VO.getUploadAttachment() == null){
				objCallableStatement.setNull(10,Types.NULL);
			}else{
				objCallableStatement.setString(10, objChangeRequest1058VO.getUploadAttachment());
			}
			objCallableStatement.setBigDecimal(11, objChangeRequest1058VO.getActualSellPrice());
			
			if("".equalsIgnoreCase(objChangeRequest1058VO.getDesignHrs()) || objChangeRequest1058VO.getDesignHrs()== null){
				objCallableStatement.setNull(12,Types.NULL);
			}else{
				objCallableStatement.setString(12, objChangeRequest1058VO.getDesignHrs());
			}
			
			if("".equalsIgnoreCase(objChangeRequest1058VO.getDraftingHrs()) || objChangeRequest1058VO.getDraftingHrs()== null){
				objCallableStatement.setNull(13,Types.NULL);
			}else{
				objCallableStatement.setString(13, objChangeRequest1058VO.getDraftingHrs());
			}
			
			objCallableStatement.setBigDecimal(14, objChangeRequest1058VO.getWorkOrderUSD());

			//Added for CR_118 Ends Legacy procedure change effect
			
			objCallableStatement.setString(15, objChangeRequest1058VO.getUserID());
			
			objCallableStatement.registerOutParameter(16, Types.INTEGER);
			objCallableStatement.registerOutParameter(17, Types.VARCHAR);
			objCallableStatement.registerOutParameter(18, Types.VARCHAR);

			intStatusCode = objCallableStatement.executeUpdate();
			
			LogUtil.logMessage("Update Result:" + intStatusCode);
			if (intStatusCode > 0) {
				intStatusCode = 0;
			}
			LogUtil.logMessage("Status Update" + intStatusCode);
			intLSDBErrorID = objCallableStatement.getInt(16);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(17);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(18);
			
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
	       
		}
		catch (DataAccessException objDataExp) {
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

	
	
	public static ArrayList fetch1058ReportDetails(ChangeRequest1058VO objChangeRequest1058VO)throws EMDException {
		LogUtil.logMessage("Entering ChangeRequest1058DAO.fetch1058ReportDetails");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ResultSet objResultSet = null;
		ResultSet objChangeFromResultSet = null;
		ResultSet objChangeToResultSet = null;
		ResultSet objEdlNoResultSet = null;
		ResultSet objRefEdlNoResultSet = null;
		ResultSet objPartOfResultSet = null;
		ResultSet objToEdlNoResultSet = null;
		ResultSet objToRefEdlNoResultSet = null;
		ResultSet objToPartOfResultSet = null;
		ResultSet objToStdEquipResultSet = null;
		ResultSet objFromStdEquipResultSet = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strMessage = null;
		String strLogUser = "";
		ArrayList arlCommonList 	= new ArrayList();	
		
		try {
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_SELECT_1058_REPORTS);
			LogUtil.logMessage("objChangeRequest1058VO.getUserID():"+objChangeRequest1058VO.getUserID());
			
			if(objChangeRequest1058VO.getUserID()== null ){
				objCallableStatement.setNull(1, Types.NULL);
			}else{
				LogUtil.logMessage("into else" + objChangeRequest1058VO.getUserID());
				objCallableStatement.setString(1, objChangeRequest1058VO.getUserID());
			}
			//Added for CR-127 sorting
			objCallableStatement.setInt(2, objChangeRequest1058VO.getOrderbyFlag());
						
			objCallableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			
			objCallableStatement.execute();
			objResultSet = (ResultSet) objCallableStatement.getObject(3);
			LogUtil
			.logMessage("Inside the fetch1058Rep method of UserDAO :resultSet"
					+ objResultSet);
			
			intLSDBErrorID = (int) objCallableStatement.getInt(4);
			strOracleCode = (String) objCallableStatement.getString(5);
			strErrorMessage = (String) objCallableStatement.getString(6);
			
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
				objConnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
			while(objResultSet.next()){
				String	strEDLNo = "";
				String	strRefEDLNo = "";
				String	strPartOf = "";
				
				LogUtil.logMessage("Enters into objResultSet:");
				objChangeRequest1058VO =new ChangeRequest1058VO();
				objChangeRequest1058VO.setOrderNo(objResultSet.getString(DatabaseConstants.LS400_ORDR_NO));
				objChangeRequest1058VO.setIssuedBy(objResultSet.getString(DatabaseConstants.ISSUEDBY));
				objChangeRequest1058VO.setNumber1058(objResultSet.getString(DatabaseConstants.LSDB_1058_ID));
				objChangeRequest1058VO.setDataLocTypeCode(objResultSet.getString(DatabaseConstants.LS150_DATA_LOC_TYPE_CODE));
				objChangeRequest1058VO.setOrderKey(objResultSet.getInt(DatabaseConstants.LS400_ORDR_KEY));
				objChangeRequest1058VO.setCustName(objResultSet.getString(DatabaseConstants.LS050_CUST_NAME));
				objChangeRequest1058VO.setSeqNo1058(objResultSet.getInt(DatabaseConstants.LS900_1058_SEQ_NO));
				objChangeRequest1058VO.setStatusDesc(objResultSet.getString(DatabaseConstants.LS902_1058_STATUS_DESC));
				objChangeRequest1058VO.setSpecRev(objResultSet.getString(DatabaseConstants.LS900_SPEC_REVISION));
				objChangeRequest1058VO.setSysEngineerName(objResultSet.getString(DatabaseConstants.SYSTEMENGINEERNAME));
				objChangeRequest1058VO.setClaChangeReqSeqNo(objResultSet.getInt(DatabaseConstants.LS910_CLA_CHANGE_REQ_SEQ_NO));
				objChangeRequest1058VO.setChangeTypeSeqNo(objResultSet.getString(DatabaseConstants.LS903_CHANGE_TYPE_SEQ_NO));
				objChangeRequest1058VO.setChangeTypeDesc(objResultSet.getString(DatabaseConstants.LS903_CHANGE_TYPE_DESC));
				objChangeRequest1058VO.setReason(objResultSet.getString(DatabaseConstants.LS910_REASON));
				objChangeRequest1058VO.setClaUpdatedToSpec(objResultSet.getString(DatabaseConstants.LS910_CLA_UPDATED_TO_SPEC));
				objChangeRequest1058VO.setRandomNo(objResultSet.getString(DatabaseConstants.LS910_CLA_CHANGE_REQ_SEQ_NO));
				
					objChangeFromResultSet = (ResultSet) objResultSet.getObject("CHANGE_FROM");
					LogUtil.logMessage("Outside While:");	
					while(objChangeFromResultSet.next()){
						LogUtil.logMessage("Inside Change From");
						objChangeRequest1058VO.setChangeFromClaNo(objChangeFromResultSet.getString(DatabaseConstants.LS910_CHANGE_FROM_CLA_NUM));
						objChangeRequest1058VO.setChangeFromClaSeqNo(objChangeFromResultSet.getString(DatabaseConstants.LS910_CHANGE_FROM_CLA_SEQ_NO));
						objChangeRequest1058VO.setChangeFromVerNo(objChangeFromResultSet.getString(DatabaseConstants.LS910_CHANGE_FROM_VER_NO));
						objChangeRequest1058VO.setChangeFromClaDesc(objChangeFromResultSet.getString(DatabaseConstants.LS301_CLA_DESC));
						
						objEdlNoResultSet = (ResultSet) objChangeFromResultSet.getObject("EDLNO");
						while(objEdlNoResultSet.next()){
							LogUtil.logMessage("Inside EdlNo loop");
							strEDLNo += "EDL "+ objEdlNoResultSet.getString(DatabaseConstants.LS302_EDL_NO)+"\n";
							}
						if (!strEDLNo.equalsIgnoreCase(""))
							objChangeRequest1058VO.setChangeFromEdlNo(strEDLNo);
						strEDLNo = "";
						DBHelper.closeSQLObjects(objEdlNoResultSet, null, null);
											
						objRefEdlNoResultSet = (ResultSet) objChangeFromResultSet.getObject("refEDLNO");
						while(objRefEdlNoResultSet.next()){
							LogUtil.logMessage("Inside RefEdlNo loop");
							strRefEDLNo += "(ref EDL "+ objRefEdlNoResultSet.getString(DatabaseConstants.LS303_REF_EDL_NO)+")\n";
							}
						if (!strRefEDLNo.equalsIgnoreCase(""))
							objChangeRequest1058VO.setChangeFromRefEdlNo(strRefEDLNo);
						strRefEDLNo = "";
						DBHelper.closeSQLObjects(objRefEdlNoResultSet, null, null);
						
						objPartOfResultSet =(ResultSet) objChangeFromResultSet.getObject("PartOF");
						while(objPartOfResultSet.next()){
							LogUtil.logMessage("Inside PartOf");
							strPartOf += "Part of "+ objPartOfResultSet.getString(DatabaseConstants.LS304_PART_OF_CLA_NUMBER)+"\n";
							}
						if (!strPartOf.equalsIgnoreCase(""))
							objChangeRequest1058VO.setChangeFromPartOfNo(strPartOf);
						strPartOf = "";
						DBHelper.closeSQLObjects(objPartOfResultSet, null, null);
						
						objChangeRequest1058VO.setChangeFromDwoNo(objChangeFromResultSet.getString(DatabaseConstants.LS301_DWO_NUMBER));
						objChangeRequest1058VO.setChangeFromPartNo(objChangeFromResultSet.getString(DatabaseConstants.LS301_PART_NUMBER));
						objChangeRequest1058VO.setChangeFromPriceBookNo(objChangeFromResultSet.getString(DatabaseConstants.LS301_PRICE_BOOK_NUMBER));
						objChangeRequest1058VO.setChangeFromEngiComments(objChangeFromResultSet.getString(DatabaseConstants.LS301_ENGG_DATA_COMMENTS));
						
						objFromStdEquipResultSet =(ResultSet) objChangeFromResultSet.getObject("STD_EQUIP");
						while(objFromStdEquipResultSet.next()){
						LogUtil.logMessage("Inside Equip");
							objChangeRequest1058VO.setChangeFromEquip(objFromStdEquipResultSet.getString(DatabaseConstants.LS060_STD_EQP_DESC));
						}
						DBHelper.closeSQLObjects(objFromStdEquipResultSet, null, null);
						}
					DBHelper.closeSQLObjects(objChangeFromResultSet, null, null);
					
					objChangeToResultSet = (ResultSet) objResultSet.getObject("CHANGE_TO");
					while(objChangeToResultSet.next()){
						LogUtil.logMessage("Inside Change To");
						objChangeRequest1058VO.setChangeToClaNo(objChangeToResultSet.getString(DatabaseConstants.LS910_CHANGE_TO_CLA_NUM));
						objChangeRequest1058VO.setChangeToClaSeqNo(objChangeToResultSet.getString(DatabaseConstants.LS910_CHANGE_TO_CLA_SEQ_NO));
						objChangeRequest1058VO.setChangeToVerNo(objChangeToResultSet.getString(DatabaseConstants.LS910_CHANGE_TO_VER_NO));
						objChangeRequest1058VO.setChangeToClaDesc(objChangeToResultSet.getString(DatabaseConstants.LS910_CHANGE_TO_CLA_DESC));
						objToEdlNoResultSet = (ResultSet) objChangeToResultSet.getObject("EDLNO");
						
						while(objToEdlNoResultSet.next()){
							LogUtil.logMessage("Inside EdlNo");
							strEDLNo += "EDL "+ objToEdlNoResultSet.getString(DatabaseConstants.LS911_EDL_NO) +"\n";
							}		
						if (!strEDLNo.equalsIgnoreCase(""))
							objChangeRequest1058VO.setChangeToEdlNo(strEDLNo);
						strEDLNo = "";
						DBHelper.closeSQLObjects(objToEdlNoResultSet, null, null);
						
						objToRefEdlNoResultSet = (ResultSet) objChangeToResultSet.getObject("refEDLNO");
						while(objToRefEdlNoResultSet.next()){
							LogUtil.logMessage("Inside RefEdlNo");
							strRefEDLNo += "(ref EDL "+ objToRefEdlNoResultSet.getString(DatabaseConstants.LS912_REF_EDL_NO)+")\n";
							}
						if (!strRefEDLNo.equalsIgnoreCase(""))
							objChangeRequest1058VO.setChangeToRefEdlNo(strRefEDLNo);
						strRefEDLNo = "";
						DBHelper.closeSQLObjects(objToRefEdlNoResultSet, null, null);
						
						objToPartOfResultSet =(ResultSet) objChangeToResultSet.getObject("PartOF");
						while(objToPartOfResultSet.next()){
							LogUtil.logMessage("Inside PartOf");
							strPartOf += "Part of "+ objToPartOfResultSet.getString(DatabaseConstants.LS913_PART_OF_CLA_NO)+"\n";
							}
						if (!strPartOf.equalsIgnoreCase(""))
							objChangeRequest1058VO.setChangeToPartOfNo(strPartOf);
						strPartOf = "";
						DBHelper.closeSQLObjects(objToPartOfResultSet, null, null);
						
						objChangeRequest1058VO.setChangeToDwoNo(objChangeToResultSet.getString(DatabaseConstants.LS910_DWO_NO));
						objChangeRequest1058VO.setChangeToPartNo(objChangeToResultSet.getString(DatabaseConstants.LS910_PART_NO));
						objChangeRequest1058VO.setChangeToPriceBookNo(objChangeToResultSet.getString(DatabaseConstants.LS910_PRICE_BOOK_NO));
						objChangeRequest1058VO.setChangeToEngiComments(objChangeToResultSet.getString(DatabaseConstants.LS910_ENG_DATA_COMMENTS));
						
						objToStdEquipResultSet =(ResultSet) objChangeToResultSet.getObject("STD_EQUIP");
						while(objToStdEquipResultSet.next()){
						LogUtil.logMessage("Inside Equip");
							objChangeRequest1058VO.setChangeToEquip(objToStdEquipResultSet.getString(DatabaseConstants.LS060_STD_EQP_DESC));
						}
						DBHelper.closeSQLObjects(objToStdEquipResultSet, null, null);
					}
					DBHelper.closeSQLObjects(objChangeToResultSet, null, null);
					arlCommonList.add(objChangeRequest1058VO);
			}
			DBHelper.closeSQLObjects(objResultSet, null, null);
		}		catch (DataAccessException objDataExp) {
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
				return arlCommonList;
					
				}
//Added for CR-127 Starts here
	public static ArrayList fetch1058PendingReportDetails(ChangeRequest1058VO objChangeRequest1058VO)throws EMDException {
		LogUtil.logMessage("Entering ChangeRequest1058DAO.fetch1058PendingReportDetails");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ResultSet objResultSet = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strMessage = null;
		String strLogUser = "";
		ArrayList arlCommonList 	= new ArrayList();	
		
		try {
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_SELECT_OPENPENDING_1058);
			LogUtil.logMessage("objChangeRequest1058VO.getUserID():"+objChangeRequest1058VO.getUserID());
			
			if(objChangeRequest1058VO.getUserID()== null ){
				objCallableStatement.setNull(1, Types.NULL);
			}else{
				LogUtil.logMessage("into else" + objChangeRequest1058VO.getUserID());
				objCallableStatement.setString(1, objChangeRequest1058VO.getUserID());
			}
			
			objCallableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(3, Types.INTEGER);
			objCallableStatement.registerOutParameter(4, Types.VARCHAR);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			
			objCallableStatement.execute();
			objResultSet = (ResultSet) objCallableStatement.getObject(2);
			LogUtil
			.logMessage("Inside the fetch1058Rep method of UserDAO :resultSet"
					+ objResultSet);
			
			intLSDBErrorID = (int) objCallableStatement.getInt(3);
			strOracleCode = (String) objCallableStatement.getString(4);
			strErrorMessage = (String) objCallableStatement.getString(5);
			
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
				objConnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
			while(objResultSet.next()){
				
				LogUtil.logMessage("Enters into objResultSet:");
				objChangeRequest1058VO =new ChangeRequest1058VO();
				objChangeRequest1058VO.setId1058(objResultSet.getString(DatabaseConstants.LSDB_1058_ID));
				objChangeRequest1058VO.setCustName(objResultSet.getString(DatabaseConstants.LS050_CUST_NAME));
				objChangeRequest1058VO.setStatusDesc(objResultSet.getString(DatabaseConstants.LS902_1058_STATUS_DESC));
				objChangeRequest1058VO.setSysEngineerName(objResultSet.getString(DatabaseConstants.SYSTEMENGINEER));
				objChangeRequest1058VO.setAwaitingActionOnName(objResultSet.getString(DatabaseConstants.WAITINGONACTION));
				objChangeRequest1058VO.setLastTransc(objResultSet.getString(DatabaseConstants.LASTTRANSACTION));
				arlCommonList.add(objChangeRequest1058VO);
			}
			DBHelper.closeSQLObjects(objResultSet, null, null);
		}		catch (DataAccessException objDataExp) {
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
				return arlCommonList;
					
				}

	public static ArrayList fetch1058MdlClaChanges(ChangeRequest1058VO objChangeRequest1058VO)throws EMDException {
	LogUtil.logMessage("Entering ChangeRequest1058DAO.fetch1058MdlClaChanges");
	Connection objConnection = null;
	CallableStatement objCallableStatement = null;
	ResultSet objResultSet = null;
	ResultSet objChangeFromResultSet = null;
	ResultSet objChangeToResultSet = null;
	ResultSet objFromEdlNoResultSet = null;
	ResultSet objFromRefEdlNoResultSet = null;
	ResultSet objFromPartOfResultSet = null;
	ResultSet objFromCompNameResultSet = null;
	ResultSet objFromTblDataResultSet = null;
	ResultSet objFromStdEquipResultSet = null;
	ResultSet objToEdlNoResultSet = null;
	ResultSet objToRefEdlNoResultSet = null;
	ResultSet objToPartOfResultSet = null;
	ResultSet objToCompNameResultSet = null;
	ResultSet objToTblDataResultSet = null;
	ResultSet objToStdEquipResultSet = null;
	String strOracleCode = null;
	String strErrorMessage = null;
	int intLSDBErrorID = 0;
	String strMessage = null;
	String strLogUser = "";
	ArrayList arlCommonList = new ArrayList();	
	
	try {
		objConnection = DBHelper.prepareConnection();
		objCallableStatement = objConnection.prepareCall(EMDQueries.SP_SELECT_MDL_CLA_CHANGES);
		LogUtil.logMessage("objChangeRequest1058VO.getUserID():"+objChangeRequest1058VO.getUserID());
		
		objCallableStatement.setInt(1,objChangeRequest1058VO.getOrderKey());
		LogUtil.logMessage("objChangeRequest1058VO.getOrderKey():"+objChangeRequest1058VO.getOrderKey());
		objCallableStatement.setString(2,objChangeRequest1058VO.getDataLocType());
		LogUtil.logMessage("objChangeRequest1058VO.getDataLocTypeCode():"+objChangeRequest1058VO.getDataLocType());
		objCallableStatement.setInt(3,objChangeRequest1058VO.getSeqNo1058());
		if(objChangeRequest1058VO.getUserID()== null ){
			objCallableStatement.setNull(4, Types.NULL);
		}else{
			LogUtil.logMessage("into else" + objChangeRequest1058VO.getUserID());
			objCallableStatement.setString(4, objChangeRequest1058VO.getUserID());
		}
		
		
		
		objCallableStatement.registerOutParameter(5, OracleTypes.CURSOR);
		objCallableStatement.registerOutParameter(6, Types.INTEGER);
		objCallableStatement.registerOutParameter(7, Types.VARCHAR);
		objCallableStatement.registerOutParameter(8, Types.VARCHAR);
		
		objCallableStatement.execute();
		objResultSet = (ResultSet) objCallableStatement.getObject(5);
		LogUtil
		.logMessage("Inside the fetch1058MdlClaChanges method of CR-DAO :resultSet"
				+ objResultSet);
		
		intLSDBErrorID = (int) objCallableStatement.getInt(6);
		strOracleCode = (String) objCallableStatement.getString(7);
		strErrorMessage = (String) objCallableStatement.getString(8);
		
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
			objConnection.rollback();
			throw new ApplicationException(objErrorInfo);
		}
		
		while(objResultSet.next()){
			String	strEDLNo = "";
			String	strRefEDLNo = "";
			String	strPartOf = "";
											
			LogUtil.logMessage("Enters into objResultSet:");
			objChangeRequest1058VO =new ChangeRequest1058VO();
			objChangeRequest1058VO.setMdlClaSeqNo(objResultSet.getInt(DatabaseConstants.LS300_CLA_SEQ_NO));
			LogUtil.logMessage("cla seq no" + objChangeRequest1058VO.getClaChangeReqSeqNo());
			objChangeRequest1058VO.setSubSectionSeqNo(objResultSet.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO));
			LogUtil.logMessage("SUBSEC_SEQ_NO" + objChangeRequest1058VO.getSubSectionSeqNo());
			objChangeRequest1058VO.setClauseNumber(objResultSet.getString(DatabaseConstants.LS406_CLA_NUM));
			LogUtil.logMessage("CLA_NUM" + objChangeRequest1058VO.getClauseNumber());
			objChangeRequest1058VO.setChangeTypeSeqNo(objResultSet.getString(DatabaseConstants.LS903_CHANGE_TYPE_SEQ_NO));
			LogUtil.logMessage("CHANGE_TYPE_SEQ_NO" + objChangeRequest1058VO.getChangeTypeSeqNo());
			objChangeRequest1058VO.setClaExistsFlag(objResultSet.getString(DatabaseConstants.CLA_EXISTS_FLAG));
			
				objChangeFromResultSet = (ResultSet) objResultSet.getObject("CHANGE_FROM");
				while(objChangeFromResultSet.next()){
					LogUtil.logMessage("Inside Change From");
					objChangeRequest1058VO.setChangeFromClaDesc(objChangeFromResultSet.getString(DatabaseConstants.LS301_CLA_DESC));
					LogUtil.logMessage("Cla From Desc "+objChangeRequest1058VO.getChangeFromClaDesc());
					objChangeRequest1058VO.setChangeFromDwoNo(objChangeFromResultSet.getString(DatabaseConstants.LS301_DWO_NUMBER));
					objChangeRequest1058VO.setChangeFromPartNo(objChangeFromResultSet.getString(DatabaseConstants.LS301_PART_NUMBER));
					objChangeRequest1058VO.setChangeFromPriceBookNo(objChangeFromResultSet.getString(DatabaseConstants.LS301_PRICE_BOOK_NUMBER));
					
					String strEngData = "";
					
					objFromEdlNoResultSet = (ResultSet) objChangeFromResultSet.getObject("EDL_NO");
					while(objFromEdlNoResultSet.next()){
						LogUtil.logMessage("Inside EdlNo loop");
						strEDLNo += "EDL "+ objFromEdlNoResultSet.getString(DatabaseConstants.LS302_EDL_NO)+"\n";
						
						strEngData += ApplicationConstants.EDL+ objFromEdlNoResultSet.getString("LS302_EDL_NO");
						LogUtil.logMessage("Engidata" +strEngData);
						strEngData += "\n";
						}
					if (!strEDLNo.equalsIgnoreCase(""))
						objChangeRequest1058VO.setChangeFromEdlNo(strEDLNo);
					strEDLNo = "";
					DBHelper.closeSQLObjects(objFromEdlNoResultSet, null, null);
										
					objFromRefEdlNoResultSet = (ResultSet) objChangeFromResultSet.getObject("REF_EDL_NO");
					while(objFromRefEdlNoResultSet.next()){
						LogUtil.logMessage("Inside RefEdlNo loop");
						strRefEDLNo += "(ref EDL "+ objFromRefEdlNoResultSet.getString(DatabaseConstants.LS303_REF_EDL_NO)+")\n";
						
						strEngData += ApplicationConstants.REF_EDL + objFromRefEdlNoResultSet.getString(DatabaseConstants.LS303_REF_EDL_NO) + ApplicationConstants.REF_EDL_END;//CR-87
						LogUtil.logMessage("Engidata" +strEngData);
						strEngData += "\n";
						}
					if (!strRefEDLNo.equalsIgnoreCase(""))
						objChangeRequest1058VO.setChangeFromRefEdlNo(strRefEDLNo);
					strRefEDLNo = "";
					DBHelper.closeSQLObjects(objFromRefEdlNoResultSet, null, null);
					
					objFromPartOfResultSet =(ResultSet) objChangeFromResultSet.getObject("PART_OF");
					while(objFromPartOfResultSet.next()){
						LogUtil.logMessage("Inside PartOf");
						strPartOf += "Part of "+ objFromPartOfResultSet.getString(DatabaseConstants.LS304_SUBSEC_NO)+"\n"; //Edited for Part Of issue
						
						strEngData += ApplicationConstants.PARTOF + objFromPartOfResultSet.getString(DatabaseConstants.LS304_SUBSEC_NO); //Edited for Part Of issue
						LogUtil.logMessage("Engidata" +strEngData);
						strEngData += "\n";
						}
					if (!strPartOf.equalsIgnoreCase(""))
						objChangeRequest1058VO.setChangeFromPartOfNo(strPartOf);
					strPartOf = "";
					DBHelper.closeSQLObjects(objFromPartOfResultSet, null, null);
					
					
					/*objFromCompNameResultSet =(ResultSet) objChangeFromResultSet.getObject("COMP_NAME");
					while(objFromCompNameResultSet.next()){
						LogUtil.logMessage("Inside Comp Nmae");
						objChangeRequest1058VO.setChngFromCompName(objFromCompNameResultSet.getString(DatabaseConstants.LS140_COMP_NAME));
						objChangeRequest1058VO.setChngFromCompSeqNo(objFromCompNameResultSet.getString(DatabaseConstants.LS140_COMP_SEQ_NO));
						objChangeRequest1058VO.setChngFromCompGrpSeqNo(objFromCompNameResultSet.getString(DatabaseConstants.LS130_COMP_GRP_SEQ_NO));
						objChangeRequest1058VO.setChngFromCompGrpName(objFromCompNameResultSet.getString(DatabaseConstants.LS130_COMP_GRP_NAME));
						}
					DBHelper.closeSQLObjects(objFromCompNameResultSet, null, null);*/
					
					objFromTblDataResultSet =(ResultSet) objChangeFromResultSet.getObject("TAB_DATA");
					while(objFromTblDataResultSet.next()){
						LogUtil.logMessage("Inside Table Data");
						objChangeRequest1058VO.setChngFrmTblDataCol1(objFromTblDataResultSet.getString(DatabaseConstants.LS306_TBL_DATA_COL_1));
						objChangeRequest1058VO.setChngFrmTblDataCol2(objFromTblDataResultSet.getString(DatabaseConstants.LS306_TBL_DATA_COL_2));
						objChangeRequest1058VO.setChngFrmTblDataCol3(objFromTblDataResultSet.getString(DatabaseConstants.LS306_TBL_DATA_COL_3));
						objChangeRequest1058VO.setChngFrmTblDataCol4(objFromTblDataResultSet.getString(DatabaseConstants.LS306_TBL_DATA_COL_4));
						objChangeRequest1058VO.setChngFrmTblDataCol5(objFromTblDataResultSet.getString(DatabaseConstants.LS306_TBL_DATA_COL_5));
						objChangeRequest1058VO.setFrmHeaderFlag(objFromTblDataResultSet.getString(DatabaseConstants.LS306_HEADER_FLAG));
					}
					DBHelper.closeSQLObjects(objFromTblDataResultSet, null, null);
				
					objFromStdEquipResultSet =(ResultSet) objChangeFromResultSet.getObject("STD_EQUIP");
					while(objFromStdEquipResultSet.next()){
					LogUtil.logMessage("Inside Equip");
						objChangeRequest1058VO.setChangeFromEquip(objFromStdEquipResultSet.getString(DatabaseConstants.LS060_STD_EQP_DESC));
						objChangeRequest1058VO.setChangeFromEquipSeqNo(objFromStdEquipResultSet.getString(DatabaseConstants.LS060_STD_EQP_SEQ_NO));
					}
					DBHelper.closeSQLObjects(objFromStdEquipResultSet, null, null);
					
					objChangeRequest1058VO.setChangeFromEngiComments(objChangeFromResultSet.getString(DatabaseConstants.LS301_ENGG_DATA_COMMENTS));
					
					}
				DBHelper.closeSQLObjects(objChangeFromResultSet, null, null);
				
				
				String strToEngiData="";
				
				objChangeToResultSet = (ResultSet) objResultSet.getObject("CHANGE_TO");
				while(objChangeToResultSet.next()){
					LogUtil.logMessage("Inside Change To");
					objChangeRequest1058VO.setChangeToClaDesc(objChangeToResultSet.getString(DatabaseConstants.LS301_CLA_DESC));
					LogUtil.logMessage("Cla Desc "+objChangeRequest1058VO.getChangeToClaDesc());
					objChangeRequest1058VO.setCustName(objChangeToResultSet.getString(DatabaseConstants.LS050_CUST_NAME));
					objChangeRequest1058VO.setChangeToDwoNo(objChangeToResultSet.getString(DatabaseConstants.LS301_DWO_NUMBER));
					objChangeRequest1058VO.setChangeToPartNo(objChangeToResultSet.getString(DatabaseConstants.LS301_PART_NUMBER));
					objChangeRequest1058VO.setChangeToPriceBookNo(objChangeToResultSet.getString(DatabaseConstants.LS301_PRICE_BOOK_NUMBER));
					
					objToEdlNoResultSet = (ResultSet) objChangeToResultSet.getObject("EDL_NO");
					while(objToEdlNoResultSet.next()){
						LogUtil.logMessage("Inside EdlNo loop");
						strEDLNo += "EDL "+ objToEdlNoResultSet.getString(DatabaseConstants.LS302_EDL_NO)+"\n";
						
						strToEngiData += ApplicationConstants.EDL+ objToEdlNoResultSet.getString("LS302_EDL_NO");
						LogUtil.logMessage("Engidata" +strToEngiData);
						strToEngiData += "\n";
						}
					if (!strEDLNo.equalsIgnoreCase(""))
						objChangeRequest1058VO.setChangeToEdlNo(strEDLNo);
					strEDLNo = "";
					DBHelper.closeSQLObjects(objToEdlNoResultSet, null, null);
										
					objToRefEdlNoResultSet = (ResultSet) objChangeToResultSet.getObject("REF_EDL_NO");
					while(objToRefEdlNoResultSet.next()){
						LogUtil.logMessage("Inside RefEdlNo loop");
						strRefEDLNo += "(ref EDL "+ objToRefEdlNoResultSet.getString(DatabaseConstants.LS303_REF_EDL_NO)+")\n";
						
						strToEngiData += ApplicationConstants.REF_EDL + objToRefEdlNoResultSet.getString(DatabaseConstants.LS303_REF_EDL_NO) + ApplicationConstants.REF_EDL_END;//CR-87
						LogUtil.logMessage("Engidata" +strToEngiData);
						strToEngiData += "\n";
						}
					if (!strRefEDLNo.equalsIgnoreCase(""))
						objChangeRequest1058VO.setChangeToRefEdlNo(strRefEDLNo);
					strRefEDLNo = "";
					DBHelper.closeSQLObjects(objToRefEdlNoResultSet, null, null);
					
					objToPartOfResultSet =(ResultSet) objChangeToResultSet.getObject("PART_OF");
					while(objToPartOfResultSet.next()){
						LogUtil.logMessage("Inside PartOf");
						strPartOf += "Part of "+ objToPartOfResultSet.getString(DatabaseConstants.LS304_SUBSEC_NO)+"\n"; //Edited for Part Of issue
						
						strToEngiData += ApplicationConstants.PARTOF + objToPartOfResultSet.getString(DatabaseConstants.LS304_SUBSEC_NO); //Edited for Part Of issue
						LogUtil.logMessage("Engidata" +strToEngiData);
						strToEngiData += "\n";
						}
					if (!strPartOf.equalsIgnoreCase(""))
						objChangeRequest1058VO.setChangeToPartOfNo(strPartOf);
					strPartOf = "";
					DBHelper.closeSQLObjects(objToPartOfResultSet, null, null);
					
					/*objToCompNameResultSet =(ResultSet) objChangeToResultSet.getObject("COMP_NAME");
					while(objToCompNameResultSet.next()){
						LogUtil.logMessage("Inside Comp Nmae");
						objChangeRequest1058VO.setChngToCompName(objToCompNameResultSet.getString(DatabaseConstants.LS140_COMP_NAME));
						objChangeRequest1058VO.setChngToCompSeqNo(objToCompNameResultSet.getString(DatabaseConstants.LS140_COMP_SEQ_NO));
						objChangeRequest1058VO.setChngToCompGrpSeqNo(objToCompNameResultSet.getString(DatabaseConstants.LS130_COMP_GRP_SEQ_NO));
						objChangeRequest1058VO.setChngToCompGrpName(objToCompNameResultSet.getString(DatabaseConstants.LS130_COMP_GRP_NAME));
						}
					DBHelper.closeSQLObjects(objToCompNameResultSet, null, null);*/
					
					objToTblDataResultSet =(ResultSet) objChangeToResultSet.getObject("TAB_DATA");
					while(objToTblDataResultSet.next()){
						LogUtil.logMessage("Inside Table Data");
						objChangeRequest1058VO.setChngToTblDataCol1(objToTblDataResultSet.getString(DatabaseConstants.LS306_TBL_DATA_COL_1));
						objChangeRequest1058VO.setChngToTblDataCol2(objToTblDataResultSet.getString(DatabaseConstants.LS306_TBL_DATA_COL_2));
						objChangeRequest1058VO.setChngToTblDataCol3(objToTblDataResultSet.getString(DatabaseConstants.LS306_TBL_DATA_COL_3));
						objChangeRequest1058VO.setChngToTblDataCol4(objToTblDataResultSet.getString(DatabaseConstants.LS306_TBL_DATA_COL_4));
						objChangeRequest1058VO.setChngToTblDataCol5(objToTblDataResultSet.getString(DatabaseConstants.LS306_TBL_DATA_COL_5));
						objChangeRequest1058VO.setToHeaderFlag(objToTblDataResultSet.getString(DatabaseConstants.LS306_HEADER_FLAG));
					}
					
					DBHelper.closeSQLObjects(objToTblDataResultSet, null, null);
				
					objToStdEquipResultSet =(ResultSet) objChangeToResultSet.getObject("STD_EQUIP");
					while(objToStdEquipResultSet.next()){
					LogUtil.logMessage("Inside Equip");
						objChangeRequest1058VO.setChangeToEquip(objToStdEquipResultSet.getString(DatabaseConstants.LS060_STD_EQP_DESC));
						objChangeRequest1058VO.setChangeToEquipSeqNo(objToStdEquipResultSet.getString(DatabaseConstants.LS060_STD_EQP_SEQ_NO));
					}
					DBHelper.closeSQLObjects(objToStdEquipResultSet, null, null);
					
					objChangeRequest1058VO.setChangeToEngiComments(objChangeToResultSet.getString(DatabaseConstants.LS301_ENGG_DATA_COMMENTS));
					
					}
				DBHelper.closeSQLObjects(objChangeToResultSet, null, null);
				arlCommonList.add(objChangeRequest1058VO); // Updated for delete flag issue
		}
		DBHelper.closeSQLObjects(objResultSet, null, null);
	}		catch (DataAccessException objDataExp) {
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
			return arlCommonList;
				
			}
	
	public static int importModelClaChanges(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException {
		LogUtil.logMessage("Entering ChangeRequest1058DAO.importModelClaChanges");
		
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		ArrayDescriptor claSeqNoArrDesc=null;
		ArrayDescriptor claChangeTypeArrDesc=null;
		ARRAY objClaSeqNoArrDesc = null;
		ARRAY objClaChaTypeArrDesc = null;
		int intStatusCode = 0;
		int intLSDBErrorID = 0;
		String strMessage = null;
		String strLogUser = "";
		try {
			strLogUser = objChangeRequest1058VO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_IMPORT_MDL_CLA_TO_1058);
			Connection dconn = ((DelegatingConnection) objConnection).getInnermostDelegate(); //Added for CR-123 
			
			objCallableStatement.setInt(1, objChangeRequest1058VO.getSeqNo1058());
			LogUtil.logMessage("objChangeRequest1058VO.getSeqNo1058() :" + objChangeRequest1058VO.getSeqNo1058());
			/*if(objChangeRequest1058VO.getClauseChangeType() == 0){
				objCallableStatement.setNull(2, Types.NULL);
			}else{
				objCallableStatement.setInt(2, objChangeRequest1058VO.getClauseChangeType());
			}
			if(objChangeRequest1058VO.getClaSeqNo() == null){
				objCallableStatement.setNull(3, Types.NULL);
			}else{
				objCallableStatement.setString(3, objChangeRequest1058VO.getClaSeqNo());
			}*/
			
			claChangeTypeArrDesc = new ArrayDescriptor(DatabaseConstants.IN_ARRAY, dconn);
			if(objChangeRequest1058VO.getClaChangeType() != null )
			{
				objClaChaTypeArrDesc = new ARRAY(claChangeTypeArrDesc, dconn,objChangeRequest1058VO.getClaChangeType());
			}
			else
			{
				LogUtil.logMessage("objChangeRequest1058VO.getClaChangeType():null");
				objClaChaTypeArrDesc = new ARRAY(claChangeTypeArrDesc, dconn, null); 
			}
			objCallableStatement.setArray(2,objClaChaTypeArrDesc);
			
			claSeqNoArrDesc = new ArrayDescriptor(DatabaseConstants.IN_ARRAY, dconn);
			if(objChangeRequest1058VO.getClaSeqNo() != null )
			{
				objClaSeqNoArrDesc = new ARRAY(claSeqNoArrDesc, dconn,objChangeRequest1058VO.getClaSeqNo());
			}
			else
			{
				LogUtil.logMessage("objChangeRequest1058VO.getModelSeqNos():null");
				objClaSeqNoArrDesc = new ARRAY(claSeqNoArrDesc, dconn, null); 
			}
			objCallableStatement.setArray(3,objClaSeqNoArrDesc);
			if(objChangeRequest1058VO.getReason() == null || objChangeRequest1058VO.getReason().equalsIgnoreCase("")){
				objCallableStatement.setNull(4, Types.NULL);
			}else{
				objCallableStatement.setString(4, objChangeRequest1058VO.getReason());
			}
			
			objCallableStatement.setString(5, objChangeRequest1058VO.getUserID());
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
					
			intStatusCode = objCallableStatement.executeUpdate();
			
			LogUtil.logMessage("Update Result:" + intStatusCode);
			if (intStatusCode > 0) {
				intStatusCode = 0;
			}
			LogUtil.logMessage("Status Update" + intStatusCode);
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
		} catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException ChangeRequest1058DAO.complete");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in ChangeRequest1058DAO.complete"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException ChangeRequest1058DAO.complete");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into catch block in ChangeRequest1058DAO.complete"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception ChangeRequest1058DAO.complete");
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
				LogUtil
				.logMessage("Enters into Exception ChangeRequest1058DAO.complete");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}		
		return intStatusCode;
	}
	//Added for CR-127 ends here
	
	//Added for CR-130 starts here
	
	public static ArrayList fetchSubSectionList(ChangeRequest1058VO objChangeRequest1058VO)throws EMDException {
		LogUtil.logMessage("Entering ChangeRequest1058DAO.fetchSubSectionList");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ResultSet objResultSet = null;
		ResultSet objClaDetailResultSet = null;
		ResultSet objEdlNoResultSet = null;
		ResultSet objRefEdlNoResultSet = null;
		ResultSet objPartOfResultSet = null;
		ResultSet objCompNameResultSet = null;
		ResultSet objTblDataResultSet = null;
		ResultSet objStdEquipResultSet = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strMessage = null;
		String strLogUser = "";
		ArrayList arlSubSectionList = new ArrayList();	
		
		try {
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_SELECT_MDL_SUBSEC_CHANGES);
			LogUtil.logMessage("objChangeRequest1058VO.getUserID():"+objChangeRequest1058VO.getUserID());
			
			objCallableStatement.setInt(1,objChangeRequest1058VO.getSeqNo1058());
			
			objCallableStatement.setInt(2,objChangeRequest1058VO.getOrderKey());
			LogUtil.logMessage("objChangeRequest1058VO.getOrderKey():"+objChangeRequest1058VO.getOrderKey());
			objCallableStatement.setString(3,objChangeRequest1058VO.getDataLocType());
			LogUtil.logMessage("objChangeRequest1058VO.getDataLocTypeCode():"+objChangeRequest1058VO.getDataLocType());
			
			if(objChangeRequest1058VO.getUserID()== null ){
				objCallableStatement.setNull(4, Types.NULL);
			}else{
				LogUtil.logMessage("into else" + objChangeRequest1058VO.getUserID());
				objCallableStatement.setString(4, objChangeRequest1058VO.getUserID());
			}
			
			objCallableStatement.registerOutParameter(5, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			
			objCallableStatement.execute();
			objResultSet = (ResultSet) objCallableStatement.getObject(5);
			LogUtil.logMessage("Inside the fetchSubSectionList method of CR-DAO :resultSet"+ objResultSet);
			
			intLSDBErrorID = (int) objCallableStatement.getInt(6);
			strOracleCode = (String) objCallableStatement.getString(7);
			strErrorMessage = (String) objCallableStatement.getString(8);
			
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
				objConnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
			while(objResultSet.next()){
				String	strEDLNo = "";
				String	strRefEDLNo = "";
				String	strPartOf = "";
												
				LogUtil.logMessage("Enters into objResultSet:");
				objChangeRequest1058VO = new ChangeRequest1058VO();
				objChangeRequest1058VO.setSubSecCode(objResultSet.getString(DatabaseConstants.SUB_CODE));
				LogUtil.logMessage("SUBSEC_CODE" + objChangeRequest1058VO.getSubSectionSeqNo());
				objChangeRequest1058VO.setSubSecName(objResultSet.getString(DatabaseConstants.LS202_SUBSEC_NAME));
				LogUtil.logMessage("SUBSEC_NAME" + objChangeRequest1058VO.getSubSectionSeqNo());
				objChangeRequest1058VO.setSubSectionSeqNo(objResultSet.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO));
				LogUtil.logMessage("SUBSEC_SEQ_NO" + objChangeRequest1058VO.getSubSectionSeqNo());
				objChangeRequest1058VO.setChangeTypeSeqNo(objResultSet.getString(DatabaseConstants.LS903_CHANGE_TYPE_SEQ_NO));
				LogUtil.logMessage("CHANGE_TYPE_SEQ_NO" + objChangeRequest1058VO.getChangeTypeSeqNo());
				objChangeRequest1058VO.setSubSecExistsFlag(objResultSet.getString(DatabaseConstants.SUBSEC_EXISTS_FLAG));
				
					objClaDetailResultSet = (ResultSet) objResultSet.getObject("CLAUSE_DETAILS");
					ArrayList arlClaDetailList = new ArrayList();	
					while(objClaDetailResultSet.next()){
						LogUtil.logMessage("Inside Clause Details");
						ChangeRequest1058VO objClauseDetails1058VO =new ChangeRequest1058VO();
						objClauseDetails1058VO.setChangeFromClaSeqNo(objClaDetailResultSet.getString(DatabaseConstants.LS300_CLA_SEQ_NO));
						objClauseDetails1058VO.setChangeFromClaDesc(objClaDetailResultSet.getString(DatabaseConstants.LS301_CLA_DESC));
						LogUtil.logMessage("Cla From Desc "+objClauseDetails1058VO.getChangeFromClaDesc());
						objClauseDetails1058VO.setChangeFromDwoNo(objClaDetailResultSet.getString(DatabaseConstants.LS301_DWO_NUMBER));
						objClauseDetails1058VO.setChangeFromPartNo(objClaDetailResultSet.getString(DatabaseConstants.LS301_PART_NUMBER));
						objClauseDetails1058VO.setChangeFromPriceBookNo(objClaDetailResultSet.getString(DatabaseConstants.LS301_PRICE_BOOK_NUMBER));
						
						String strEngData = "";
						
						objEdlNoResultSet = (ResultSet) objClaDetailResultSet.getObject("EDL_NO");
						while(objEdlNoResultSet.next()){
							LogUtil.logMessage("Inside EdlNo loop");
							strEDLNo += "EDL "+ objEdlNoResultSet.getString(DatabaseConstants.LS302_EDL_NO)+"\n";
							strEngData += ApplicationConstants.EDL+ objEdlNoResultSet.getString("LS302_EDL_NO");
							LogUtil.logMessage("Engidata" +strEngData);
							strEngData += "\n";
							}
						if (!strEDLNo.equalsIgnoreCase(""))
							objClauseDetails1058VO.setChangeFromEdlNo(strEDLNo);
						strEDLNo = "";
						DBHelper.closeSQLObjects(objEdlNoResultSet, null, null);
											
						objRefEdlNoResultSet = (ResultSet) objClaDetailResultSet.getObject("REF_EDL_NO");
						while(objRefEdlNoResultSet.next()){
							LogUtil.logMessage("Inside RefEdlNo loop");
							strRefEDLNo += "(ref EDL "+ objRefEdlNoResultSet.getString(DatabaseConstants.LS303_REF_EDL_NO)+")\n";
							
							strEngData += ApplicationConstants.REF_EDL + objRefEdlNoResultSet.getString(DatabaseConstants.LS303_REF_EDL_NO) + ApplicationConstants.REF_EDL_END;//CR-87
							LogUtil.logMessage("Engidata" +strEngData);
							strEngData += "\n";
							}
						if (!strRefEDLNo.equalsIgnoreCase(""))
							objClauseDetails1058VO.setChangeFromRefEdlNo(strRefEDLNo);
						strRefEDLNo = "";
						DBHelper.closeSQLObjects(objRefEdlNoResultSet, null, null);
						
						objPartOfResultSet =(ResultSet) objClaDetailResultSet.getObject("PART_OF");
						while(objPartOfResultSet.next()){
							LogUtil.logMessage("Inside PartOf");
							strPartOf += "Part of "+ objPartOfResultSet.getString(DatabaseConstants.LS304_SUBSEC_NO)+"\n";//Edited for Part Of issue 
							
							strEngData += ApplicationConstants.PARTOF + objPartOfResultSet.getString(DatabaseConstants.LS304_SUBSEC_NO);//Edited for Part Of issue
							LogUtil.logMessage("Engidata" +strEngData);
							strEngData += "\n";
							}
						if (!strPartOf.equalsIgnoreCase(""))
							objClauseDetails1058VO.setChangeFromPartOfNo(strPartOf);
						strPartOf = "";
						DBHelper.closeSQLObjects(objPartOfResultSet, null, null);
						
						
						/*objFromCompNameResultSet =(ResultSet) objChangeFromResultSet.getObject("COMP_NAME");
						while(objFromCompNameResultSet.next()){
							LogUtil.logMessage("Inside Comp Nmae");
							objClauseDetails1058VO.setChngFromCompName(objFromCompNameResultSet.getString(DatabaseConstants.LS140_COMP_NAME));
							objClauseDetails1058VO.setChngFromCompSeqNo(objFromCompNameResultSet.getString(DatabaseConstants.LS140_COMP_SEQ_NO));
							objClauseDetails1058VO.setChngFromCompGrpSeqNo(objFromCompNameResultSet.getString(DatabaseConstants.LS130_COMP_GRP_SEQ_NO));
							objClauseDetails1058VO.setChngFromCompGrpName(objFromCompNameResultSet.getString(DatabaseConstants.LS130_COMP_GRP_NAME));
							}
						DBHelper.closeSQLObjects(objFromCompNameResultSet, null, null);*/
						
						objTblDataResultSet =(ResultSet) objClaDetailResultSet.getObject("TAB_DATA");
						while(objTblDataResultSet.next()){
							LogUtil.logMessage("Inside Table Data");
							objClauseDetails1058VO.setChngFrmTblDataCol1(objTblDataResultSet.getString(DatabaseConstants.LS306_TBL_DATA_COL_1));
							objClauseDetails1058VO.setChngFrmTblDataCol2(objTblDataResultSet.getString(DatabaseConstants.LS306_TBL_DATA_COL_2));
							objClauseDetails1058VO.setChngFrmTblDataCol3(objTblDataResultSet.getString(DatabaseConstants.LS306_TBL_DATA_COL_3));
							objClauseDetails1058VO.setChngFrmTblDataCol4(objTblDataResultSet.getString(DatabaseConstants.LS306_TBL_DATA_COL_4));
							objClauseDetails1058VO.setChngFrmTblDataCol5(objTblDataResultSet.getString(DatabaseConstants.LS306_TBL_DATA_COL_5));
							objClauseDetails1058VO.setFrmHeaderFlag(objTblDataResultSet.getString(DatabaseConstants.LS306_HEADER_FLAG));
						}
						DBHelper.closeSQLObjects(objTblDataResultSet, null, null);
					
						objStdEquipResultSet =(ResultSet) objClaDetailResultSet.getObject("STD_EQUIP");
						while(objStdEquipResultSet.next()){
						LogUtil.logMessage("Inside Equip");
							objClauseDetails1058VO.setChangeFromEquip(objStdEquipResultSet.getString(DatabaseConstants.LS060_STD_EQP_DESC));
							objClauseDetails1058VO.setChangeFromEquipSeqNo(objStdEquipResultSet.getString(DatabaseConstants.LS060_STD_EQP_SEQ_NO));
						}
						DBHelper.closeSQLObjects(objStdEquipResultSet, null, null);
						
						objClauseDetails1058VO.setChangeFromEngiComments(objClaDetailResultSet.getString(DatabaseConstants.LS301_ENGG_DATA_COMMENTS));
						arlClaDetailList.add(objClauseDetails1058VO);
						}
					objChangeRequest1058VO.setClaDetailList(arlClaDetailList);
					DBHelper.closeSQLObjects(objClaDetailResultSet, null, null);
					arlSubSectionList.add(objChangeRequest1058VO);
			}
			DBHelper.closeSQLObjects(objResultSet, null, null);
		}		catch (DataAccessException objDataExp) {
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
				return arlSubSectionList;
					
				}
	
	public static int importModelSubSectionChanges(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException {
		LogUtil.logMessage("Entering ChangeRequest1058DAO.importModelSubSectionChanges");
		
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		ArrayDescriptor subSecSeqNoArrDesc=null;
		ArrayDescriptor claChangeTypeArrDesc=null;
		ARRAY objSubSecSeqNoArrDesc = null;
		ARRAY objClaChaTypeArrDesc = null;
		int intStatusCode = 0;
		int intLSDBErrorID = 0;
		String strMessage = null;
		String strLogUser = "";
		try {
			strLogUser = objChangeRequest1058VO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_IMPORT_MDL_SUBSEC_TO_1058);
			Connection dconn = ((DelegatingConnection) objConnection).getInnermostDelegate(); //Added for CR-123 
			
			objCallableStatement.setInt(1, objChangeRequest1058VO.getSeqNo1058());
			LogUtil.logMessage("objChangeRequest1058VO.getSeqNo1058() :" + objChangeRequest1058VO.getSeqNo1058());
			
			subSecSeqNoArrDesc = new ArrayDescriptor(DatabaseConstants.IN_ARRAY, dconn);
			if(objChangeRequest1058VO.getSubSecSeqNo() != null )
			{
				objSubSecSeqNoArrDesc = new ARRAY(subSecSeqNoArrDesc, dconn,objChangeRequest1058VO.getSubSecSeqNo());
			}
			else
			{
				LogUtil.logMessage("objChangeRequest1058VO.getModelSeqNos():null");
				objSubSecSeqNoArrDesc = new ARRAY(subSecSeqNoArrDesc, dconn, null); 
			}
			objCallableStatement.setArray(2,objSubSecSeqNoArrDesc);
			
			LogUtil.logMessage("objChangeRequest1058VO.getClaChangeType():" + objChangeRequest1058VO.getClaChangeType());
			claChangeTypeArrDesc = new ArrayDescriptor(DatabaseConstants.IN_ARRAY, dconn);
			if(objChangeRequest1058VO.getClaChangeType() != null )
			{
				objClaChaTypeArrDesc = new ARRAY(claChangeTypeArrDesc, dconn,objChangeRequest1058VO.getClaChangeType());
			}
			else
			{
				LogUtil.logMessage("objChangeRequest1058VO.getClaChangeType():null");
				objClaChaTypeArrDesc = new ARRAY(claChangeTypeArrDesc, dconn, null); 
			}
			objCallableStatement.setArray(3,objClaChaTypeArrDesc);
			
			
			if(objChangeRequest1058VO.getReason() == null || objChangeRequest1058VO.getReason().equalsIgnoreCase("")){
				objCallableStatement.setNull(4, Types.NULL);
			}else{
				objCallableStatement.setString(4, objChangeRequest1058VO.getReason());
			}
			
			objCallableStatement.setString(5, objChangeRequest1058VO.getUserID());
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
					
			intStatusCode = objCallableStatement.executeUpdate();
			
			LogUtil.logMessage("Update Result:" + intStatusCode);
			if (intStatusCode > 0) {
				intStatusCode = 0;
			}
			LogUtil.logMessage("Status Update" + intStatusCode);
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
		} catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException ChangeRequest1058DAO.complete");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in ChangeRequest1058DAO.complete"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException ChangeRequest1058DAO.complete");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into catch block in ChangeRequest1058DAO.complete"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception ChangeRequest1058DAO.complete");
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
				LogUtil
				.logMessage("Enters into Exception ChangeRequest1058DAO.complete");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}		
		return intStatusCode;
	}
	
	public static ArrayList fetch1058SubSectionList(ChangeRequest1058VO objChangeRequest1058VO)throws EMDException {
		LogUtil.logMessage("Entering ChangeRequest1058DAO.fetch1058SubSectionList");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ResultSet objResultSet = null;
		ResultSet objClaDetailResultSet = null;
		ResultSet objEdlNoResultSet = null;
		ResultSet objRefEdlNoResultSet = null;
		ResultSet objPartOfResultSet = null;
		ResultSet objCompNameResultSet = null;
		ResultSet objTblDataResultSet = null;
		ResultSet objStdEquipResultSet = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strMessage = null;
		String strLogUser = "";
		ArrayList arl1058SubSectionList = new ArrayList();	
		ArrayList arlTableRows = null;
		
		try {
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_SELECT_1058_SUBSEC_CHNG_REQ);
			LogUtil.logMessage("objChangeRequest1058VO.getUserID():"+objChangeRequest1058VO.getUserID());
			
			objCallableStatement.setInt(1,objChangeRequest1058VO.getSeqNo1058());
			
			if(objChangeRequest1058VO.getSubSecChngReqSeq() == 0 ){
				objCallableStatement.setNull(2, Types.NULL);
			}else{
			objCallableStatement.setInt(2, objChangeRequest1058VO.getSubSecChngReqSeq());
			}
			
			if(objChangeRequest1058VO.getUserID()== null ){
				objCallableStatement.setNull(3, Types.NULL);
			}else{
				LogUtil.logMessage("into else" + objChangeRequest1058VO.getUserID());
				objCallableStatement.setString(3, objChangeRequest1058VO.getUserID());
			}
			
			objCallableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			
			objCallableStatement.execute();
			objResultSet = (ResultSet) objCallableStatement.getObject(4);
			LogUtil.logMessage("Inside the fetchSubSectionList method of CR-DAO :resultSet"+ objResultSet);
			
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
				objConnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
			while(objResultSet.next()){
				String	strEDLNo = "";
				String	strRefEDLNo = "";
				String	strPartOf = "";
												
				LogUtil.logMessage("Enters into objResultSet:");
				objChangeRequest1058VO =new ChangeRequest1058VO();
				objChangeRequest1058VO.setSubSecCode(objResultSet.getString(DatabaseConstants.SUB_CODE));
				LogUtil.logMessage("SUBSEC_CODE" + objChangeRequest1058VO.getSubSectionSeqNo());
				objChangeRequest1058VO.setSubSecName(objResultSet.getString(DatabaseConstants.LS202_SUBSEC_NAME));
				LogUtil.logMessage("SUBSEC_NAME" + objChangeRequest1058VO.getSubSectionSeqNo());
				objChangeRequest1058VO.setSubSectionSeqNo(objResultSet.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO));
				LogUtil.logMessage("SUBSEC_SEQ_NO" + objChangeRequest1058VO.getSubSectionSeqNo());
				objChangeRequest1058VO.setSubSecChngReqSeqNo(objResultSet.getInt(DatabaseConstants.LS925_SUBSEC_CHNG_REQ_SEQ_NO));
				LogUtil.logMessage("objChangeRequest1058VO.getSubSecChngReqSeqNo():" + objChangeRequest1058VO.getSubSecChngReqSeqNo());
				objChangeRequest1058VO.setChangeTypeSeqNo(objResultSet.getString(DatabaseConstants.LS903_CHANGE_TYPE_SEQ_NO));
				LogUtil.logMessage("CHANGE_TYPE_SEQ_NO" + objChangeRequest1058VO.getChangeTypeSeqNo());
				objChangeRequest1058VO.setReasonSubSec(objResultSet.getString(DatabaseConstants.LS925_REASON));
				LogUtil.logMessage("ReasonSubSec" + objChangeRequest1058VO.getReasonSubSec());
				objChangeRequest1058VO.setUpdatedToSpecFlag(objResultSet.getString(DatabaseConstants.LS925_SUBSEC_UPDATED_TO_SPEC));
				LogUtil.logMessage("UpdatedToSpecFlag" + objChangeRequest1058VO.getUpdatedToSpecFlag());
				
					objClaDetailResultSet = (ResultSet) objResultSet.getObject("CLAUSE_DETAILS");
					ArrayList arlClaDetailList = new ArrayList();
					while(objClaDetailResultSet.next()){
						ChangeRequest1058VO objClauseDetails1058VO =new ChangeRequest1058VO();
						LogUtil.logMessage("Inside Clause Details");
						objClauseDetails1058VO.setClauseNumber(objClaDetailResultSet.getString(DatabaseConstants.LS406_CLA_NUM));
						objClauseDetails1058VO.setChangeFromClaSeqNo(objClaDetailResultSet.getString(DatabaseConstants.LS300_CLA_SEQ_NO));
						objClauseDetails1058VO.setChangeFromClaDesc(objClaDetailResultSet.getString(DatabaseConstants.LS301_CLA_DESC));
						LogUtil.logMessage("Cla From Desc "+objClauseDetails1058VO.getChangeFromClaDesc());
						objClauseDetails1058VO.setChangeFromDwoNo(objClaDetailResultSet.getString(DatabaseConstants.LS301_DWO_NUMBER));
						objClauseDetails1058VO.setChangeFromPartNo(objClaDetailResultSet.getString(DatabaseConstants.LS301_PART_NUMBER));
						objClauseDetails1058VO.setChangeFromPriceBookNo(objClaDetailResultSet.getString(DatabaseConstants.LS301_PRICE_BOOK_NUMBER));
						
						String strEngData = "";
						
						objEdlNoResultSet = (ResultSet) objClaDetailResultSet.getObject("EDL_NO");
						while(objEdlNoResultSet.next()){
							LogUtil.logMessage("Inside EdlNo loop");
							strEDLNo += "EDL "+ objEdlNoResultSet.getString(DatabaseConstants.LS302_EDL_NO)+"\n";
							
							strEngData += ApplicationConstants.EDL+ objEdlNoResultSet.getString("LS302_EDL_NO");
							LogUtil.logMessage("Engidata" +strEngData);
							strEngData += "\n";
							}
						if (!strEDLNo.equalsIgnoreCase(""))
							objClauseDetails1058VO.setChangeFromEdlNo(strEDLNo);
						strEDLNo = "";
						DBHelper.closeSQLObjects(objEdlNoResultSet, null, null);
											
						objRefEdlNoResultSet = (ResultSet) objClaDetailResultSet.getObject("REF_EDL_NO");
						while(objRefEdlNoResultSet.next()){
							LogUtil.logMessage("Inside RefEdlNo loop");
							strRefEDLNo += "(ref EDL "+ objRefEdlNoResultSet.getString(DatabaseConstants.LS303_REF_EDL_NO)+")\n";
							
							strEngData += ApplicationConstants.REF_EDL + objRefEdlNoResultSet.getString(DatabaseConstants.LS303_REF_EDL_NO) + ApplicationConstants.REF_EDL_END;//CR-87
							LogUtil.logMessage("Engidata" +strEngData);
							strEngData += "\n";
							}
						if (!strRefEDLNo.equalsIgnoreCase(""))
							objClauseDetails1058VO.setChangeFromRefEdlNo(strRefEDLNo);
						strRefEDLNo = "";
						DBHelper.closeSQLObjects(objRefEdlNoResultSet, null, null);
						
						objPartOfResultSet =(ResultSet) objClaDetailResultSet.getObject("PART_OF");
						while(objPartOfResultSet.next()){
							LogUtil.logMessage("Inside PartOf");
							strPartOf += "Part of "+ objPartOfResultSet.getString(DatabaseConstants.LS304_SUBSEC_NO)+"\n"; //Edited for Part Of issue
							
							strEngData += ApplicationConstants.PARTOF + objPartOfResultSet.getString(DatabaseConstants.LS304_SUBSEC_NO); //Edited for Part Of issue
							LogUtil.logMessage("Engidata" +strEngData);
							strEngData += "\n";
							}
						if (!strPartOf.equalsIgnoreCase(""))
							objClauseDetails1058VO.setChangeFromPartOfNo(strPartOf);
						strPartOf = "";
						DBHelper.closeSQLObjects(objPartOfResultSet, null, null);
						
						
						/*objFromCompNameResultSet =(ResultSet) objChangeFromResultSet.getObject("COMP_NAME");
						while(objFromCompNameResultSet.next()){
							LogUtil.logMessage("Inside Comp Nmae");
							objClauseDetails1058VO.setChngFromCompName(objFromCompNameResultSet.getString(DatabaseConstants.LS140_COMP_NAME));
							objClauseDetails1058VO.setChngFromCompSeqNo(objFromCompNameResultSet.getString(DatabaseConstants.LS140_COMP_SEQ_NO));
							objClauseDetails1058VO.setChngFromCompGrpSeqNo(objFromCompNameResultSet.getString(DatabaseConstants.LS130_COMP_GRP_SEQ_NO));
							objClauseDetails1058VO.setChngFromCompGrpName(objFromCompNameResultSet.getString(DatabaseConstants.LS130_COMP_GRP_NAME));
							}
						DBHelper.closeSQLObjects(objFromCompNameResultSet, null, null);*/
						
						/*For table data*/
						objTblDataResultSet = (ResultSet) objClaDetailResultSet
						.getObject("TAB_DATA");
						
						ArrayList arlTableColumns = new ArrayList();		
						arlTableRows = new ArrayList();						
						
						while (objTblDataResultSet.next()) {
							LogUtil.logMessage("Inside Table Data");
							arlTableColumns = new ArrayList();
							arlTableColumns.add(objTblDataResultSet
									.getString(DatabaseConstants.LS306_TBL_DATA_COL_1));
							arlTableColumns.add(objTblDataResultSet
									.getString(DatabaseConstants.LS306_TBL_DATA_COL_2));
							arlTableColumns.add(objTblDataResultSet
									.getString(DatabaseConstants.LS306_TBL_DATA_COL_3));
							arlTableColumns.add(objTblDataResultSet
									.getString(DatabaseConstants.LS306_TBL_DATA_COL_4));
							arlTableColumns.add(objTblDataResultSet
									.getString(DatabaseConstants.LS306_TBL_DATA_COL_5));
							/*arlTableColumns.add(objTblDataResultSet.
									getString(DatabaseConstants.LS306_HEADER_FLAG));*/
							arlTableRows.add(arlTableColumns);
							LogUtil.logMessage("exists Table Data : " + arlTableColumns);

						}
						
						if (arlTableRows.size() > 0)
							objClauseDetails1058VO.setTableArrayData1(arlTableRows);
						
						DBHelper.closeSQLObjects(objTblDataResultSet, null, null);
						
						/*For table data ends here*/
						
					
						objStdEquipResultSet =(ResultSet) objClaDetailResultSet.getObject("STD_EQUIP");
						while(objStdEquipResultSet.next()){
						LogUtil.logMessage("Inside Equip");
							objClauseDetails1058VO.setChangeFromEquip(objStdEquipResultSet.getString(DatabaseConstants.LS060_STD_EQP_DESC));
							objClauseDetails1058VO.setChangeFromEquipSeqNo(objStdEquipResultSet.getString(DatabaseConstants.LS060_STD_EQP_SEQ_NO));
						}
						DBHelper.closeSQLObjects(objStdEquipResultSet, null, null);
						
						objClauseDetails1058VO.setChangeFromEngiComments(objClaDetailResultSet.getString(DatabaseConstants.LS301_ENGG_DATA_COMMENTS));
						arlClaDetailList.add(objClauseDetails1058VO);
					}
				objChangeRequest1058VO.setClaDetailList(arlClaDetailList);
				DBHelper.closeSQLObjects(objClaDetailResultSet, null, null);
				arl1058SubSectionList.add(objChangeRequest1058VO);
			}
			DBHelper.closeSQLObjects(objResultSet, null, null);
		}		catch (DataAccessException objDataExp) {
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
				return arl1058SubSectionList;
					
				}
	
	//Added for CR-130 ends here
	
	//Added for CR-134
	public static ArrayList fetch1058TransitionReportDetails(ChangeRequest1058VO objChangeRequest1058VO)throws EMDException {
		LogUtil.logMessage("Entering ChangeRequest1058DAO.fetch1058TransitionReportDetails");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ResultSet objResultSet = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strMessage = null;
		String strLogUser = "";
		ArrayList arlCommonList 	= new ArrayList();	
		
		try {
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_SELECT_1058_TRANSITIONS);
			LogUtil.logMessage("objChangeRequest1058VO.getUserID():"+objChangeRequest1058VO.getUserID());
			
			if(objChangeRequest1058VO.getUserID()== null ){
				objCallableStatement.setNull(1, Types.NULL);
			}else{
				LogUtil.logMessage("into else" + objChangeRequest1058VO.getUserID());
				objCallableStatement.setString(1, objChangeRequest1058VO.getUserID());
			}
			
			objCallableStatement.setString(2,objChangeRequest1058VO.getFromDate());
			objCallableStatement.setString(3,objChangeRequest1058VO.getToDate());
			
			objCallableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			
			objCallableStatement.execute();
			objResultSet = (ResultSet) objCallableStatement.getObject(4);
			LogUtil
			.logMessage("Inside the fetch1058TransitionReportDetails method of ChangeREquest1058DAO :resultSet"
					+ objResultSet);
			
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
				objConnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
			while(objResultSet.next()){
				
				LogUtil.logMessage("Enters into objResultSet:");
				objChangeRequest1058VO =new ChangeRequest1058VO();
				objChangeRequest1058VO.setNumber1058(objResultSet.getString(DatabaseConstants.LSDB_1058_NUMBER));
				objChangeRequest1058VO.setStatusDesc(objResultSet.getString(DatabaseConstants.LS1058_STATUS));
				objChangeRequest1058VO.setTransitionDate(objResultSet.getString(DatabaseConstants.LS909_ACTION_DATE));
				LogUtil.logMessage("Action DAte" +objChangeRequest1058VO.getTransitionDate());
				objChangeRequest1058VO.setActionUser(objResultSet.getString(DatabaseConstants.ACTION_USER));
				arlCommonList.add(objChangeRequest1058VO);
			}
			DBHelper.closeSQLObjects(objResultSet, null, null);
		}		catch (DataAccessException objDataExp) {
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
				return arlCommonList;
					
				}
	
}
