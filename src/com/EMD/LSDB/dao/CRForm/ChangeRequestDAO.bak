/*
 * Created on Apr 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.dao.CRForm;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.tomcat.dbcp.dbcp2.DelegatingConnection;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;
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
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.ReqClauseVO;
import com.EMD.LSDB.vo.common.RequestCompGrpVO;
import com.EMD.LSDB.vo.common.RequestCompVO;
import com.EMD.LSDB.vo.common.RequestModelVO;
import com.EMD.LSDB.vo.common.RequestVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business methods for the Change Request
 ******************************************************************************/

public class ChangeRequestDAO extends EMDDAO {
	
	private ChangeRequestDAO() {
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param RequestVO
	 * @return ArrayList contains the list of status
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList fetchStatus(RequestVO objRequestVO)
	throws EMDException, Exception {
		LogUtil.logMessage("Inside the  ChangeRequestDAO:fetchStatus");
		Connection objConnnection = null;
		ArrayList objAlSpec = new ArrayList();
		ArrayList objStatusType = new ArrayList();
		String strLogUser = "";
		try {
			strLogUser = objRequestVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in fetchStatus :"
					+ objConnnection);
			
			ResultSet reqStatus = DBHelper.executeQuery(objConnnection,
					EMDQueries.REQ_STATUS, objAlSpec);
			
			LogUtil.logMessage("user in fetchSpecTypes :" + reqStatus);
			
			while (reqStatus.next()) {
				
				objRequestVO = new RequestVO();
				
				objRequestVO.setStatusTypeSeqNo(reqStatus
						.getInt(DatabaseConstants.LS502_STATUS_TYPE_SEQ_NO));
				objRequestVO.setStatusDesc(reqStatus
						.getString(DatabaseConstants.LS502_STATUS_TYPE_DESC));
				objStatusType.add(objRequestVO);
			}
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception ChangeRequestDAO:fetchStatus");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		} finally {
			try {
				DBHelper.closeConnection(objConnnection);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception ChangeRequestDAO:fetchStatus");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		LogUtil.logMessage("objStatusType in ChangeRequestDAO:fetchStatus :"
				+ objStatusType);
		return objStatusType;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objModelVO
	 *            the object for creating new Request ID
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int createRequestID(RequestVO objRequestVO)
	throws EMDException {
		LogUtil.logMessage("Entering ChangeRequestDAO.createRequestID");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		try {
			strLogUser = objRequestVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_CREATE_REQUEST);
			objCallableStatement.setString(1, objRequestVO.getRequestDesc());
			
			objCallableStatement.setString(2, objRequestVO.getUserID());
			
			objCallableStatement.registerOutParameter(3, Types.INTEGER);
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			//Success case
			if (intStatus > 0) {
				intStatus = 0;
				intStatus = (int) objCallableStatement.getInt(3);
			}
			
			LogUtil
			.logMessage("Inside the createRequestID method of ChangeRequestDAO :RequestID .."
					+ intStatus);
			intLSDBErrorID = (int) objCallableStatement.getInt(4);
			strOracleCode = (String) objCallableStatement.getString(5);
			strErrorMessage = (String) objCallableStatement.getString(6);
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
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
		}
		
		catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into DataAccess Exception block in ChangeRequestDAO:createRequestID"
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ChangeRequestDAO:createRequestID"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ChangeRequestDAO:createRequestID"
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
				.logMessage("Enters into Exception block in ChangeRequestDAO:createRequestID"
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
	 *            the object for creating new Request ID
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList fetchRequestDetails(RequestVO objRequestVO)
	throws EMDException {
		LogUtil.logMessage("Entering ChangeRequestDAO.fetchRequestDetails");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		ArrayList arlRequestList = new ArrayList();
		ResultSet objresultSet = null;
		
		try {
			strLogUser = objRequestVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_SEARCH_REQUEST);
			
			if (objRequestVO.getStatusTypeSeqNo() != 0) {
				objCallableStatement.setInt(1, objRequestVO
						.getStatusTypeSeqNo());
			} else {
				objCallableStatement.setNull(1, Types.NULL);
			}
			
			if (!"All".equals(objRequestVO.getLastName())
					&& !"-1".equals(objRequestVO.getLastName())) {
				
				LogUtil.logMessage("if loop:" + objRequestVO.getLastName());
				objCallableStatement.setString(2, objRequestVO.getLastName());
			} else {
				LogUtil.logMessage("else loop:" + objRequestVO.getLastName());
				objCallableStatement.setNull(2, Types.NULL);
			}
			
			if (objRequestVO.getRequestID() != 0) {
				objCallableStatement.setInt(3, objRequestVO.getRequestID());
			} else {
				objCallableStatement.setNull(3, Types.NULL);
			}
			
			if (objRequestVO.getReqSubFromDate() != null) {
				objCallableStatement.setString(4, objRequestVO
						.getReqSubFromDate());
			} else {
				objCallableStatement.setNull(4, Types.NULL);
			}
			
			if (objRequestVO.getReqSubToDate() != null) {
				objCallableStatement.setString(5, objRequestVO
						.getReqSubToDate());
			} else {
				objCallableStatement.setNull(5, Types.NULL);
			}
			
			objCallableStatement.setString(6, objRequestVO.getUserID());
			
			objCallableStatement.registerOutParameter(7, OracleTypes.CURSOR);
			
			objCallableStatement.registerOutParameter(8, Types.INTEGER);
			
			objCallableStatement.registerOutParameter(9, Types.VARCHAR);
			
			objCallableStatement.registerOutParameter(10, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			objresultSet = (ResultSet) objCallableStatement.getObject(7);
			
			intLSDBErrorID = (int) objCallableStatement.getInt(8);
			strOracleCode = (String) objCallableStatement.getString(9);
			strErrorMessage = (String) objCallableStatement.getString(10);
			
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
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
			while (objresultSet.next()) {
				
				objRequestVO = new RequestVO();
				
				objRequestVO.setReqID(objresultSet
						.getString(DatabaseConstants.REQ_ID));
				objRequestVO.setRequestDesc(objresultSet
						.getString(DatabaseConstants.LS503_REQ_DESC));
				objRequestVO.setStatusTypeSeqNo(objresultSet
						.getInt(DatabaseConstants.LS502_STATUS_TYPE_SEQ_NO));
				objRequestVO.setStatusTypeDesc(objresultSet
						.getString(DatabaseConstants.LS502_STATUS_TYPE_DESC));
				objRequestVO.setReason(objresultSet
						.getString(DatabaseConstants.LS503_REQ_REASON));
				objRequestVO.setReqUserDate(objresultSet
						.getString(DatabaseConstants.LS503_REQ_SUBMTD_DATE));
				objRequestVO.setReqUserID(objresultSet
						.getString(DatabaseConstants.LS503_REQ_USER_ID));
				objRequestVO.setMasterSpecChangedID(objresultSet
						.getString(DatabaseConstants.LS503_MSTR_CHNGD_ID));
				objRequestVO.setMasterSpecChangedDate(objresultSet
						.getString(DatabaseConstants.LS503_MSTR_CHNGD_DATE));
				objRequestVO.setApproveByUserFN(objresultSet
						.getString(DatabaseConstants.MSTR_FN));
				objRequestVO.setApproveByUserLN(objresultSet
						.getString(DatabaseConstants.MSTR_LN));
				objRequestVO.setReqByUserFN(objresultSet
						.getString(DatabaseConstants.REQ_FN));
				objRequestVO.setReqByUserLN(objresultSet
						.getString(DatabaseConstants.REQ_LN));
				objRequestVO.setAdminComments(objresultSet
						.getString(DatabaseConstants.LS503_REQ_ADMN_COMMENTS));
				
				arlRequestList.add(objRequestVO);
			}
			
		}
		
		catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into DataAccess Exception block in ChangeRequestDAO:fetchRequestDetails"
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ChangeRequestDAO:fetchRequestDetails"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ChangeRequestDAO:fetchRequestDetails"
					+ objExp.getMessage());
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(objresultSet, objCallableStatement,
						objConnnection);
			}
			
			catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in ChangeRequestDAO:fetchRequestDetails"
						+ objExp.getMessage());
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		LogUtil.logMessage("Size of ArrayList:" + arlRequestList.size());
		return arlRequestList;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param RequestVO
	 *            the object for fetching Request Model details
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/
	
	public static RequestModelVO fetchReqModelDetails(RequestVO objRequestVO)
	throws EMDException {
		LogUtil.logMessage("Entering ChangeRequestDAO.fetchReqModelDetails");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		RequestModelVO objRequestModelVO = new RequestModelVO();
		ResultSet objresultSet = null;
		
		LogUtil.logMessage("objRequestVO.getRequestID() :"
				+ objRequestVO.getRequestID());
		try {
			strLogUser = objRequestVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_SELECT_MODEL_REQ_DTLS);
			
			objCallableStatement.setInt(1, objRequestVO.getRequestID());
			objCallableStatement.setString(2, objRequestVO.getUserID());
			objCallableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			objresultSet = (ResultSet) objCallableStatement.getObject(3);
			intLSDBErrorID = (int) objCallableStatement.getInt(4);
			strOracleCode = (String) objCallableStatement.getString(5);
			strErrorMessage = (String) objCallableStatement.getString(6);
			
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
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
			while (objresultSet.next()) {
				// Added For CR_80 to add Specification Type Dropdown		
				objRequestModelVO.setSpecTypeNo(objresultSet
						.getInt(DatabaseConstants.SPEC_TYPE_SEQ_NO));
				// Added For CR_80 to add Specification Type Dropdown - Ends here
				
				objRequestModelVO.setModelSeqNo(objresultSet
						.getInt(DatabaseConstants.MODEL_SEQ_NO));
				
				objRequestModelVO.setModelName(objresultSet
						.getString(DatabaseConstants.MODEL_NAME));
				
				objRequestModelVO.setSectionSeqNo(objresultSet
						.getInt(DatabaseConstants.LS201_SEC_SEQ_NO));
				
				objRequestModelVO.setSecCode(objresultSet
						.getString(DatabaseConstants.LS201_SEC_CODE));
				
				objRequestModelVO.setSecName(objresultSet
						.getString(DatabaseConstants.LS201_SEC_NAME));
				
				objRequestModelVO.setSubSectionSeqNo(objresultSet
						.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO));
				
				objRequestModelVO.setSubSecCode(objresultSet
						.getString(DatabaseConstants.LS202_SUBSEC_CODE));
				
				objRequestModelVO.setSubSecName(objresultSet
						.getString(DatabaseConstants.LS202_SUBSEC_NAME));
				
				/*
				 * Change for Model apply flag
				 */
				if (objresultSet.getString(
						DatabaseConstants.LS504_CHNG_EFF_MDL_FLAG)
						.equalsIgnoreCase("Y")) {
					
					objRequestModelVO.setApplyModelFlag(true);
					
					//Added to display as label in preview
					objRequestModelVO.setMdlFlag("Yes");
				} else {
					objRequestModelVO.setApplyModelFlag(false);
					
					//Added to display as label in preview
					objRequestModelVO.setMdlFlag("No");
				}
				
			}
			
		}
		
		catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into DataAccess Exception block in ChangeRequestDAO:fetchReqModelDetails"
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ChangeRequestDAO:fetchReqModelDetails"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ChangeRequestDAO:fetchReqModelDetails"
					+ objExp.getMessage());
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(objresultSet, objCallableStatement,
						objConnnection);
			}
			
			catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in ChangeRequestDAO:fetchReqModelDetails"
						+ objExp.getMessage());
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return objRequestModelVO;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param RequestVO
	 *            the object for fetching Request Comp Grp and Comp details
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/
	
	public static RequestModelVO fetchReqCompGrpDetails(RequestVO objRequestVO)
	throws EMDException {
		LogUtil.logMessage("Entering ChangeRequestDAO.fetchReqCompGrpDetails");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		String strColorFlagLink = "";
		RequestModelVO objRequestModelVO = new RequestModelVO();
		RequestCompGrpVO objRequestCompGrpVO = new RequestCompGrpVO();
		RequestCompVO objRequestCompVO = new RequestCompVO();
		ResultSet objresultSet = null;
		
		try {
			strLogUser = objRequestVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_SELECT_COMPGRP_REQ_DTLS);
			
			objCallableStatement.setInt(1, objRequestVO.getRequestID());
			objCallableStatement.setString(2, objRequestVO.getUserID());
			objCallableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(4, Types.VARCHAR);
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			objresultSet = (ResultSet) objCallableStatement.getObject(3);
			strColorFlagLink = (String) objCallableStatement.getString(4);
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
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
			while (objresultSet.next()) {
				
				objRequestCompGrpVO.setChangeTypeSeqNo(objresultSet
						.getInt(DatabaseConstants.COMP_GRP_CHANGE_TYPE));
				
				objRequestCompGrpVO
				.setChangeTypeDesc(objresultSet
						.getString(DatabaseConstants.COMP_GRP_CHANGE_TYPE_DESC));
				
				objRequestCompGrpVO.setOldCompGrpSeqNo(objresultSet
						.getInt(DatabaseConstants.LS130_COMP_GRP_SEQ_NO_OLD));
				
				objRequestCompGrpVO.setOldCompGrpName(objresultSet
						.getString(DatabaseConstants.LS505_COMP_GRP_NAME_OLD));
				
				objRequestCompGrpVO.setOldCompGrpChacFlag(objresultSet
						.getString(DatabaseConstants.LS505_CHARZ_FLAG_OLD));
				
				objRequestCompGrpVO
				.setOldCompGrpValidFlag(objresultSet
						.getString(DatabaseConstants.LS505_VALIDATION_FLAG_OLD));
				
				objRequestCompGrpVO.setNewCompGrpName(objresultSet
						.getString(DatabaseConstants.LS505_COMP_GRP_NAME_NEW));
				
				objRequestCompGrpVO.setNewCompGrpChacFlag(objresultSet
						.getString(DatabaseConstants.LS505_CHARZ_FLAG_NEW));
				
				objRequestCompGrpVO
				.setNewCompGrpValidFlag(objresultSet
						.getString(DatabaseConstants.LS505_VALIDATION_FLAG_NEW));
				
				objRequestCompGrpVO.setCompGrpNameColorFlag(objresultSet
						.getString(DatabaseConstants.NAME_FLAG));
				
				objRequestCompGrpVO.setCompGrpCharColorFlag(objresultSet
						.getString(DatabaseConstants.CHRZ_FLAG));
				
				objRequestCompGrpVO.setCompGrpValdColorFlag(objresultSet
						.getString(DatabaseConstants.VLDN_FLAG));
				
				objRequestCompVO.setChangeTypeSeqNo(objresultSet
						.getInt(DatabaseConstants.COMP_CHANGE_TYPE));
				
				objRequestCompVO.setChangeTypeDesc(objresultSet
						.getString(DatabaseConstants.COMP_CHANGE_TYPE_DESC));
				
				objRequestCompVO.setOldCompSeqNo(objresultSet
						.getInt(DatabaseConstants.LS140_COMP_SEQ_NO_OLD));
				
				objRequestCompVO.setOldCompName(objresultSet
						.getString(DatabaseConstants.LS506_COMP_NAME_OLD));
				
				objRequestCompVO.setOldCompDefaultFlag(objresultSet
						.getString(DatabaseConstants.LS506_DEFAULT_FLAG_OLD));
				
				objRequestCompVO.setNewCompName(objresultSet
						.getString(DatabaseConstants.LS506_COMP_NAME_NEW));
				
				objRequestCompVO.setNewCompDefaultFlag(objresultSet
						.getString(DatabaseConstants.LS506_DEFAULT_FLAG_NEW));
				
				objRequestCompVO.setCompNameColorFlag(objresultSet
						.getString(DatabaseConstants.COMP_FLAG));
				
				objRequestCompVO.setCompDefColorFlag(objresultSet
						.getString(DatabaseConstants.DFULT_FLAG));
				
				objRequestModelVO.setRequestCompGrpVO(objRequestCompGrpVO);
				
				objRequestModelVO.setRequestCompVO(objRequestCompVO);
				
			}
			
			objRequestModelVO.setClaGrpLinkColorFlag(strColorFlagLink);
		}
		
		catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into DataAccess Exception block in ChangeRequestDAO:fetchReqCompGrpDetails"
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ChangeRequestDAO:fetchReqCompGrpDetails"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ChangeRequestDAO:fetchReqCompGrpDetails"
					+ objExp.getMessage());
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(objresultSet, objCallableStatement,
						objConnnection);
			}
			
			catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in ChangeRequestDAO:fetchReqCompGrpDetails"
						+ objExp.getMessage());
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return objRequestModelVO;
		
	}
	
	/***************************************************************************
	 * Method is used to fetch the request clause details.
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param RequestVO
	 *            the object for fetching Request Clause details
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/
	
	public static RequestModelVO fetchReqClauseDetails(RequestVO objRequestVO)
	throws EMDException {
		LogUtil.logMessage("Entering ChangeRequestDAO.fetchReqCompGrpDetails");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		String strColorFlagLink = "";
		RequestModelVO objRequestModelVO = new RequestModelVO();
		ReqClauseVO objRequestClauseVO = new ReqClauseVO();
		
		ResultSet objresultSet = null;
		ResultSet objEDLNoResultSet = null;
		ResultSet objRefEDLNoResultSet = null;
		ResultSet objClauseTabData = null;
		ResultSet objClauseComp = null;
		ResultSet objPartOfResultSet = null;
		
		try {
			strLogUser = objRequestVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_SELECT_CLA_REQ_DTLS);
			
			objCallableStatement.setInt(1, objRequestVO.getRequestID());
			objCallableStatement.setString(2, objRequestVO.getUserID());
			objCallableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(4, Types.VARCHAR);
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			objresultSet = (ResultSet) objCallableStatement.getObject(3);
			strColorFlagLink = (String) objCallableStatement.getString(4);
			intLSDBErrorID = (int) objCallableStatement.getInt(5);
			strOracleCode = (String) objCallableStatement.getString(6);
			strErrorMessage = (String) objCallableStatement.getString(7);
			
			LogUtil.logMessage("Colour Flag in Select Clause:"
					+ strColorFlagLink);
			
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
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
			while (objresultSet.next()) {
				
				int cntEDL = 0;
				int cntRefEDl = 0;
				int cntPartOf = 0;
				int cntCompSeqno = 0;
				int countCol=0;
				
				String[] strPartClaNo = { "", "", "", "" };
				int[] strPartSubSecSeqNo = new int[4];
				//String[] strPartclaDec = { "", "", "", "" };
				int[] strPartclaSeqNo = new int[4];
				String[] strEDLNos = { "", "", "" };
				String[] strRefEDLNos = { "", "", "" };
				String[] strCompSeqNo = new String[25];
				String parentClaDesc = null;
				String claDesc = "";
				String fromClaNo = "";
				String toClaNo = "";
				String toParClaNo = "";
				String stdEqpDesc = "";
				//Added for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151
				String[] strEdlClrFlags = { "", "", "" };
				String[] strRefEdlClrFlags = { "", "", "" };
				String[] strPartOfClrFlag = { "", "", "", "" };
				//Added for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151 - Ends here
				
				ArrayList arledlNo = new ArrayList();
				ArrayList arlRefEdlNo = new ArrayList();
				ArrayList arlPartOfClaNo = new ArrayList();
				ArrayList arlPartOfClaSeqNo = new ArrayList();
				ArrayList arlPartSubSecSeqNo = new ArrayList();
				
				ArrayList arlClauseDesc = new ArrayList();
				ArrayList arlTableRows = new ArrayList();
				ArrayList arlClauseComp = new ArrayList();
				
				objRequestClauseVO.setChangeTypeSeqNO(objresultSet
						.getInt(DatabaseConstants.LS501_CHNG_TYPE_SEQ_NO));
				
				objRequestClauseVO.setChangeTypeDesc(objresultSet
						.getString(DatabaseConstants.CLA_CHANGE_TYPE_DESC));
				
				fromClaNo = objresultSet
				.getString(DatabaseConstants.LS507_CHNG_FRM_CLA_NO);
				
				objRequestClauseVO.setFromClauseNo(fromClaNo);
				
				objRequestClauseVO.setFromClauseSeqNo(objresultSet
						.getInt(DatabaseConstants.LS507_CHNG_FRM_CLA_SEQ_NO));
				
				objRequestClauseVO.setFromClauseVerNo(objresultSet
						.getInt(DatabaseConstants.LS507_CHNG_FRM_VERSION_NO));
				
				toClaNo = objresultSet
				.getString(DatabaseConstants.LS507_CHANGE_TO_CLA_NO);
				
				objRequestClauseVO.setToClauseNo(toClaNo);
				
				objRequestClauseVO.setClaNoFlag(objresultSet
						.getString(DatabaseConstants.CLANO_FLAG));
				
				objRequestClauseVO.setToClauseVersionNo(objresultSet
						.getInt(DatabaseConstants.LS507_CHNG_TO_VERSION_NO));
				
				objRequestClauseVO.setToClaVerClauseNo(objresultSet
						.getString(DatabaseConstants.LS507_CHNG_TO_VERSION_NO));
				
				objRequestClauseVO.setVersionFlag(objresultSet
						.getString(DatabaseConstants.VERSN_FLAG));
				
				toParClaNo = objresultSet
				.getString(DatabaseConstants.LS507_PARNT_CLA_NO);
				
				objRequestClauseVO.setToParentClauseNo(toParClaNo);
				
				parentClaDesc = objresultSet
				.getString(DatabaseConstants.LS507_PARNT_CLA_DESC);
				if (parentClaDesc != null) {
					
					objRequestClauseVO.setToParentClaDesc(parentClaDesc);
				} else {
					
					objRequestClauseVO.setToParentClaDesc("");
				}
				
				claDesc = objresultSet
				.getString(DatabaseConstants.LS507_CLA_DESC);
				//Modified for CR_80 CR Form Enhancements III on 25-Nov-09 by RR68151
				if ((claDesc != null)
						&& (objRequestClauseVO.getChangeTypeSeqNO() == ApplicationConstants.CHANGE_TYPE_DESC_DEFAULT_VERSION
								|| objRequestClauseVO.getChangeTypeSeqNO() == ApplicationConstants.CHANGE_TYPE_DESC_DELETE_CLAUSE_VERSION )) {
					objRequestClauseVO.setToClaVerDesc(claDesc);
				} else {
					objRequestClauseVO.setToClaVerDesc("");
				}
				
				if (claDesc != null
						&& objRequestClauseVO.getChangeTypeSeqNO() != ApplicationConstants.CHANGE_TYPE_DESC_DEFAULT_VERSION
							&& objRequestClauseVO.getChangeTypeSeqNO() != ApplicationConstants.CHANGE_TYPE_DESC_DELETE_CLAUSE_VERSION) {
					objRequestClauseVO.setToClauseDesc(claDesc);
				} else {
					objRequestClauseVO.setToClauseDesc("");
				}
				
				objRequestClauseVO.setClaDescFlag(objresultSet
						.getString(DatabaseConstants.CLADESC_FLAG));
				
				objRequestClauseVO.setToComments(objresultSet
						.getString(DatabaseConstants.LS507_ENGG_DATA_COMMENTS));
				
				objRequestClauseVO.setTodwONumber(objresultSet
						.getInt(DatabaseConstants.LS507_DWO_NO));
				
				objRequestClauseVO.setToPartNumber(objresultSet
						.getInt(DatabaseConstants.LS507_PART_NO));
				
				objRequestClauseVO.setTopriceBookNumber(objresultSet
						.getInt(DatabaseConstants.LS507_PRICE_BOOK_NO));
				
				objRequestClauseVO.setToStdEquipSeqNo(objresultSet
						.getInt(DatabaseConstants.LS060_STD_EQP_SEQ_NO));
				
				stdEqpDesc = objresultSet
				.getString(DatabaseConstants.LS060_STD_EQP_DESC);
				objRequestClauseVO.setToStdEqpDesc(stdEqpDesc);
				objRequestClauseVO.setToDefaultFlag(objresultSet
						.getString(DatabaseConstants.LS507_DEFAULT_FLAG));
				
				//Added for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151
				objRequestClauseVO.setEnggDataClrFlag(objresultSet
						.getString(DatabaseConstants.ENG_DATA_CLR_FLAG));
				
				objRequestClauseVO.setDwoNoClrFlag(objresultSet
						.getString(DatabaseConstants.DWO_NO_CLR_FLAG));
				
				objRequestClauseVO.setPartNoClrFlag(objresultSet
						.getString(DatabaseConstants.PART_NO_CLR_FLAG));
				
				objRequestClauseVO.setPriceBookClrFlag(objresultSet
						.getString(DatabaseConstants.PRICE_BK_NO_CLR_FLAG));
				
				objRequestClauseVO.setStdEqpClrFlag(objresultSet
						.getString(DatabaseConstants.STD_EQP_CLR_FLAG));
				//Added for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151 - Ends here
				
				objEDLNoResultSet = (ResultSet) objresultSet
				.getObject("EDL_NO");
				while (objEDLNoResultSet.next()) {
					
					LogUtil.logMessage("Enters into EDLNo Resultset Loop:");
					
					arledlNo.add(objEDLNoResultSet
							.getString(DatabaseConstants.LS508_EDL_NO));
					
					strEDLNos[cntEDL] = objEDLNoResultSet
					.getString(DatabaseConstants.LS508_EDL_NO);
					
					//Added for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151
					strEdlClrFlags[cntEDL] = objEDLNoResultSet
					.getString(DatabaseConstants.EDL_CLR_FLAG);
					//Added for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151 - Ends here
					
					cntEDL++;
				}
				
				objRequestClauseVO.setEdlNo(arledlNo);
				objRequestClauseVO.setToEdlNo(strEDLNos);
				//Added for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151
				objRequestClauseVO.setEdlClrFlag(strEdlClrFlags);
				//Added for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151 - Ends here
				DBHelper.closeSQLObjects(objEDLNoResultSet, null, null);
				
				objRefEDLNoResultSet = (ResultSet) objresultSet
				.getObject("REF_EDL_NO");
				while (objRefEDLNoResultSet.next()) {
					
					LogUtil.logMessage("Enters into RefEDLNo Resultset Loop:");
					
					arlRefEdlNo.add(objRefEDLNoResultSet
							.getString(DatabaseConstants.LS509_REF_EDL_NO));
					
					strRefEDLNos[cntRefEDl] = objRefEDLNoResultSet
					.getString(DatabaseConstants.LS509_REF_EDL_NO);

					//Added for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151
					strRefEdlClrFlags[cntRefEDl] = objRefEDLNoResultSet
					.getString(DatabaseConstants.REF_EDL_CLR_FLAG);
					//Added for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151 - Ends here
					
					cntRefEDl++;
				}
				
				objRequestClauseVO.setRefEdlNo(arlRefEdlNo);
				objRequestClauseVO.setToRefEDLNo(strRefEDLNos);
				//Added for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151
				objRequestClauseVO.setRefEdlClrFlag(strRefEdlClrFlags);
				//Added for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151 - Ends here
				DBHelper.closeSQLObjects(objRefEDLNoResultSet, null, null);
				
				objPartOfResultSet = (ResultSet) objresultSet
				.getObject("CLA_PART_OF");
				while (objPartOfResultSet.next()) {
					
					LogUtil.logMessage("Enters into Part Of  Resultset Loop:");
					arlPartSubSecSeqNo.add(objPartOfResultSet
							.getString(DatabaseConstants.LS202_SUBSEC_SEQ_NO));
					arlPartOfClaSeqNo
					.add(objPartOfResultSet
							.getString(DatabaseConstants.LS510_PART_OF_CLA_SEQ_NO));
					arlPartOfClaNo.add(objPartOfResultSet
							.getString(DatabaseConstants.LS510_PART_OF_CLA_NO));
					//arlClauseDesc.add(objPartOfResultSet.getString(DatabaseConstants.LS510_CLA_DESC));
					
					strPartSubSecSeqNo[cntPartOf] = objPartOfResultSet
					.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO);
					
					strPartclaSeqNo[cntPartOf] = objPartOfResultSet
					.getInt(DatabaseConstants.LS510_PART_OF_CLA_SEQ_NO);
					
					strPartClaNo[cntPartOf] = objPartOfResultSet
					.getString(DatabaseConstants.LS510_PART_OF_CLA_NO);
					
					//strPartclaDec[cntPartOf] = objPartOfResultSet
					//.getString(DatabaseConstants.LS510_CLA_DESC);
					
					//Added for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151
					strPartOfClrFlag[cntPartOf] = objPartOfResultSet
					.getString(DatabaseConstants.PART_OF_CLR_FLAG);
					//Added for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151 - Ends here
					
					cntPartOf++;
				}
				
				objRequestClauseVO.setPartSubSecSeqNo(arlPartSubSecSeqNo);
				objRequestClauseVO.setPartClaSeqNo(arlPartOfClaSeqNo);
				objRequestClauseVO.setPartClaNo(arlPartOfClaNo);
				objRequestClauseVO.setPartClaDes(arlClauseDesc);
				
				objRequestClauseVO.setPartOfSubSecSeqNo(strPartSubSecSeqNo);
				objRequestClauseVO.setPartOfClaSeqNo(strPartclaSeqNo);
				objRequestClauseVO.setPartOfClaNo(strPartClaNo);
				//objRequestClauseVO.setPartOfClaDesc(strPartclaDec);
				//Added for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151
				objRequestClauseVO.setPartOfClrFlag(strPartOfClrFlag);
				//Added for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151 - Ends here
				
				DBHelper.closeSQLObjects(objPartOfResultSet, null, null);
				
				objClauseTabData = (ResultSet) objresultSet
				.getObject("CLA_TBL_DATA");
				
				while (objClauseTabData.next()) {
					
					LogUtil
					.logMessage("Enters into Table Data Resultset Loop:");
					ArrayList arlTableColumns = new ArrayList();
					arlTableColumns.add(objClauseTabData
							.getString(DatabaseConstants.LS511_TBL_DATA_COL_1));
					arlTableColumns.add(objClauseTabData
							.getString(DatabaseConstants.LS511_TBL_DATA_COL_2));
					arlTableColumns.add(objClauseTabData
							.getString(DatabaseConstants.LS511_TBL_DATA_COL_3));
					arlTableColumns.add(objClauseTabData
							.getString(DatabaseConstants.LS511_TBL_DATA_COL_4));
					arlTableColumns.add(objClauseTabData
							.getString(DatabaseConstants.LS511_TBL_DATA_COL_5));
					arlTableRows.add(arlTableColumns);
					
				}
//				Added For CR_93
     			countCol=ApplicationUtil.getTableDataColumnsCount(arlTableRows);
     			objRequestClauseVO.setTableDataColSize(countCol);
				
				objRequestClauseVO.setTableData(arlTableRows);
				DBHelper.closeSQLObjects(objClauseTabData, null, null);
				
				objClauseComp = (ResultSet) objresultSet.getObject("CLA_COMP");
				
				while (objClauseComp.next()) {
					
					arlClauseComp.add(objClauseComp
							.getString(DatabaseConstants.LS140_COMP_NAME));
					strCompSeqNo[cntCompSeqno] = objClauseComp
					.getString(DatabaseConstants.LS140_COMP_SEQ_NO);
					cntCompSeqno++;
				}
				objRequestClauseVO.setClaComp(arlClauseComp);
				objRequestClauseVO.setComponentSeqNo(strCompSeqNo);
				objRequestClauseVO.setToComponentSeqNo(ApplicationUtil
						.convertStringArrayToString(strCompSeqNo));
				DBHelper.closeSQLObjects(objClauseComp, null, null);
				
				objRequestModelVO.setReqClauseVO(objRequestClauseVO);
				
			}
			objRequestModelVO.setCompGrpLinkColorFlag(strColorFlagLink);
			
		}
		
		catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into DataAccess Exception block in ChangeRequestDAO:fetchReqClauseDetails"
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ChangeRequestDAO:fetchReqClauseDetails"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ChangeRequestDAO:fetchReqClauseDetails"
					+ objExp.getMessage());
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			objExp.printStackTrace();
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(objresultSet, objCallableStatement,
						objConnnection);
			}
			
			catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in ChangeRequestDAO:fetchReqClauseDetails"
						+ objExp.getMessage());
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return objRequestModelVO;
		
	}
	
	/***************************************************************************
	 * This Method is used to insert the request clause details.
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objRequestModelVO
	 *            the object for Inserting clause
	 * @return int that returns status of the transaction.
	 * @throws EMDException
	 **************************************************************************/
	public static synchronized int insertClaReqDetails(
			RequestModelVO objRequestModelVO) throws EMDException {
		LogUtil.logMessage("Inside the ChangeRequestDAO:insertClaReqDetails");
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
			strLogUser = objRequestModelVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_INSERT_CLA_REQ_DTLS);
			LogUtil.logMessage("RequestId:" + objRequestModelVO.getRequestID());
			objCallableStatement.setInt(1, objRequestModelVO.getRequestID());
			
			LogUtil.logMessage("ModelSeqNO:"
					+ objRequestModelVO.getModelSeqNo());
			objCallableStatement.setInt(2, objRequestModelVO.getModelSeqNo());
			LogUtil.logMessage("SectionSeqNO:"
					+ objRequestModelVO.getSectionSeqNo());
			objCallableStatement.setInt(3, objRequestModelVO.getSectionSeqNo());
			LogUtil.logMessage("SubSectionSeqNO:"
					+ objRequestModelVO.getSubSectionSeqNo());
			objCallableStatement.setInt(4, objRequestModelVO
					.getSubSectionSeqNo());
			objCallableStatement.setInt(5, objRequestModelVO.getReqClauseVO()
					.getChangeTypeSeqNO());
			LogUtil.logMessage("ChangeTypeSeqNO():"
					+ objRequestModelVO.getReqClauseVO().getChangeTypeSeqNO());
			if (objRequestModelVO.getReqClauseVO().getFromClauseNo() != null) {
				objCallableStatement.setString(6, objRequestModelVO
						.getReqClauseVO().getFromClauseNo());
			} else {
				objCallableStatement.setNull(6, Types.NULL);
			}
			
			if (objRequestModelVO.getReqClauseVO().getFromClauseSeqNo() != 0) {
				objCallableStatement.setInt(7, objRequestModelVO
						.getReqClauseVO().getFromClauseSeqNo());
			} else {
				objCallableStatement.setNull(7, Types.NULL);
			}
			
			if (objRequestModelVO.getReqClauseVO().getFromClauseVerNo() != 0) {
				objCallableStatement.setInt(8, objRequestModelVO
						.getReqClauseVO().getFromClauseVerNo());
			} else {
				objCallableStatement.setNull(8, Types.NULL);
			}
			
			if (objRequestModelVO.getReqClauseVO().getToClauseNo() != null) {
				objCallableStatement.setString(9, objRequestModelVO
						.getReqClauseVO().getToClauseNo());
			} else {
				objCallableStatement.setNull(9, Types.NULL);
			}
			
			if (objRequestModelVO.getReqClauseVO().getToClaVerClauseNo() != null) {
				objCallableStatement.setString(10, objRequestModelVO
						.getReqClauseVO().getToClaVerClauseNo());
			} else {
				objCallableStatement.setNull(10, Types.NULL);
			}
			
			if (objRequestModelVO.getReqClauseVO().getToParentClauseNo() != null) {
				objCallableStatement.setString(11, objRequestModelVO
						.getReqClauseVO().getToParentClauseNo());
			} else {
				objCallableStatement.setNull(11, Types.NULL);
			}
			
			if (objRequestModelVO.getReqClauseVO().getToParentClaDesc() != null) {
				objCallableStatement.setString(12, objRequestModelVO
						.getReqClauseVO().getToParentClaDesc());
			} else {
				objCallableStatement.setNull(12, Types.NULL);
			}
			
			if (objRequestModelVO.getReqClauseVO().getToClauseDesc() != null) {
				objCallableStatement.setString(13, objRequestModelVO
						.getReqClauseVO().getToClauseDesc());
			} else {
				objCallableStatement.setNull(13, Types.NULL);
			}
			
			if (objRequestModelVO.getReqClauseVO().getToComments() != null) {
				objCallableStatement.setString(14, objRequestModelVO
						.getReqClauseVO().getToComments());
			} else {
				objCallableStatement.setNull(14, Types.NULL);
			}
			
			if (objRequestModelVO.getReqClauseVO().getTodwONumber() != 0) {
				objCallableStatement.setInt(15, objRequestModelVO
						.getReqClauseVO().getTodwONumber());
			} else {
				objCallableStatement.setNull(15, Types.NULL);
			}
			
			if (objRequestModelVO.getReqClauseVO().getToPartNumber() != 0) {
				objCallableStatement.setInt(16, objRequestModelVO
						.getReqClauseVO().getToPartNumber());
			} else {
				objCallableStatement.setNull(16, Types.NULL);
			}
			
			if (objRequestModelVO.getReqClauseVO().getTopriceBookNumber() != 0) {
				objCallableStatement.setInt(17, objRequestModelVO
						.getReqClauseVO().getTopriceBookNumber());
			} else {
				objCallableStatement.setNull(17, Types.NULL);
			}
			
			if (objRequestModelVO.getReqClauseVO().getToStdEquipSeqNo() != 0) {
				objCallableStatement.setInt(18, objRequestModelVO
						.getReqClauseVO().getToStdEquipSeqNo());
			} else {
				objCallableStatement.setNull(18, Types.NULL);
			}
			
			if (objRequestModelVO.getReqClauseVO().getToDefaultFlag() != null) {
				objCallableStatement.setString(19, objRequestModelVO
						.getReqClauseVO().getToDefaultFlag());
			} else {
				objCallableStatement.setNull(19, Types.NULL);
			}
			//Added for Tomcat
			Connection dconn = ((DelegatingConnection) objConnnection).getInnermostDelegate(); //Added for CR-123
			//Modified for CR-123 & Tomcat
			arrdesc = new ArrayDescriptor(DatabaseConstants.STR_ARRAY,
					dconn);
			
			ARRAY arrEdlno = new ARRAY(arrdesc, dconn,
					objRequestModelVO.getReqClauseVO().getToEdlNo());//Modified Till here
			if (arrEdlno.length() == 0) {
				
				objCallableStatement.setNull(20, Types.NULL);
			} else {
				
				objCallableStatement.setArray(20, arrEdlno);
			}
			ARRAY arrRefEDLNO = new ARRAY(arrdesc, dconn,
					objRequestModelVO.getReqClauseVO().getToRefEDLNo());//Modified for Tomcat
			
			if (objRequestModelVO.getReqClauseVO().getToRefEDLNo() == null) {
				arrRefEDLNO = new ARRAY(arrdesc, dconn, null);
				
				objCallableStatement.setArray(21, arrRefEDLNO);
			} else {
				
				objCallableStatement.setArray(21, arrRefEDLNO);
			}
			
			ARRAY arrPartOfSeqNO = new ARRAY(arrdesc, dconn,
					objRequestModelVO.getReqClauseVO().getPartOfSeqNo());
			
			if (arrPartOfSeqNO.length() == 0) {
				
				objCallableStatement.setNull(22, Types.NULL);
			} else {
				
				objCallableStatement.setArray(22, arrPartOfSeqNO);
			}
			
			ARRAY arrPartOfClaSeqNo = new ARRAY(arrdesc, dconn,
					objRequestModelVO.getReqClauseVO().getPartOfClaSeqNo());
			
			if (arrPartOfClaSeqNo.length() == 0) {
				
				objCallableStatement.setNull(23, Types.NULL);
			} else {
				
				objCallableStatement.setArray(23, arrPartOfClaSeqNo);
			}
			
			ARRAY arrPartOfSeqCode = new ARRAY(arrdesc, dconn,
					objRequestModelVO.getReqClauseVO().getPartOfCode());
			
			if (arrPartOfSeqCode.length() == 0) {
				
				objCallableStatement.setNull(24, Types.NULL);
			} else {
				
				objCallableStatement.setArray(24, arrPartOfSeqCode);
			}
			
			arlTableList = objRequestModelVO.getReqClauseVO().getTableDataVO();
			
			objTableDataVO = (ClauseTableDataVO) arlTableList.get(0);
			
			tableDataArr1 = new ARRAY(arrdesc, dconn, objTableDataVO
					.getTableArrayData1());
			
			if (objTableDataVO.getTableArrayData1() == null) {
				
				tableDataArr1 = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(25, tableDataArr1);
			} else {
				
				objCallableStatement.setArray(25, tableDataArr1);
			}
			
			objTableDataVO = (ClauseTableDataVO) arlTableList.get(1);
			tableDataArr2 = new ARRAY(arrdesc, dconn, objTableDataVO
					.getTableArrayData2());
			
			if (objTableDataVO.getTableArrayData2() == null) {
				
				tableDataArr2 = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(26, tableDataArr2);
			} else {
				
				objCallableStatement.setArray(26, tableDataArr2);
			}
			
			objTableDataVO = (ClauseTableDataVO) arlTableList.get(2);
			tableDataArr3 = new ARRAY(arrdesc, dconn, objTableDataVO
					.getTableArrayData3());
			
			if (objTableDataVO.getTableArrayData3() == null) {
				
				tableDataArr3 = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(27, tableDataArr3);
			} else {
				
				objCallableStatement.setArray(27, tableDataArr3);
			}
			
			objTableDataVO = (ClauseTableDataVO) arlTableList.get(3);
			tableDataArr4 = new ARRAY(arrdesc, dconn, objTableDataVO
					.getTableArrayData4());
			
			if (objTableDataVO.getTableArrayData4() == null) {
				
				tableDataArr4 = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(28, tableDataArr4);
			} else {
				
				objCallableStatement.setArray(28, tableDataArr4);
			}
			
			objTableDataVO = (ClauseTableDataVO) arlTableList.get(4);
			tableDataArr5 = new ARRAY(arrdesc, dconn, objTableDataVO
					.getTableArrayData5());
			if (objTableDataVO.getTableArrayData5() == null) {
				
				tableDataArr5 = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(29, tableDataArr5);
			} else {
				
				objCallableStatement.setArray(29, tableDataArr5);
			}
			
			arr = new ARRAY(arrdesc, dconn, objRequestModelVO
					.getReqClauseVO().getComponentSeqNo());
			
			if (objRequestModelVO.getReqClauseVO().getComponentSeqNo() == null) {
				
				arr = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setNull(30, Types.NULL);
			} else {
				
				objCallableStatement.setArray(30, arr);
			}
			
			if (objRequestModelVO.getReqClauseVO().getReason() != null) {
				objCallableStatement.setString(31, objRequestModelVO
						.getReqClauseVO().getReason());
			} else {
				objCallableStatement.setNull(31, Types.NULL);
			}
			if (objRequestModelVO.getAdminComments() != null
					&& !"".equals(objRequestModelVO.getAdminComments())) {
				objCallableStatement.setString(32, objRequestModelVO
						.getAdminComments());
			} else {
				objCallableStatement.setNull(32, Types.NULL);
			}
			
			if (objRequestModelVO.getRequestDesc() != null
					&& !"".equals(objRequestModelVO.getRequestDesc())) {
				objCallableStatement.setString(33, objRequestModelVO
						.getRequestDesc());
			} else {
				objCallableStatement.setNull(33, Types.NULL);
			}
			
			/*
			 * Change for Model apply flag
			 */
			if (objRequestModelVO.isApplyModelFlag()) {
				objCallableStatement.setString(34, "Y");
			} else {
				objCallableStatement.setString(34, "N");
			}
			
			objCallableStatement.setString(35, objRequestModelVO
					.getReqClauseVO().getUserID());
			
			objCallableStatement.registerOutParameter(36, Types.INTEGER);
			
			objCallableStatement.registerOutParameter(37, Types.VARCHAR);
			
			objCallableStatement.registerOutParameter(38, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
				
			}
			
			intLSDBErrorID = (int) objCallableStatement.getInt(36);
			strOracleCode = (String) objCallableStatement.getString(37);
			strErrorMessage = (String) objCallableStatement.getString(38);
			
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
			.logMessage("Enters into DataAccess Exception block in ChangeRequestDAO:insertClaReqDetails"
					+ objDatExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDatExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ChangeRequestDAO:insertClaReqDetails:"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ChangeRequestDAO:insertClaReqDetails:"
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
				.logMessage("Enter into Exception block in ChangeRequestDAO:insertClaReqDetails:"
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
	 * @param objRequestModelVO
	 * @return the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int insertCompGrpReqDetails(RequestModelVO objRequestModelVO)
	throws EMDException {
		LogUtil.logMessage("Entering ChangeRequestDAO.insertCompGrpReqDetails");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		//ResultSet objresultSet = null;
		
		try {
			strLogUser = objRequestModelVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_INSERT_COMPGRP_COMP_DTLS);
			
			LogUtil.logMessage("objConnnection :" + objConnnection);
			if (objRequestModelVO.getRequestID() != 0) {
				objCallableStatement
				.setInt(1, objRequestModelVO.getRequestID());
			} else {
				objCallableStatement.setNull(1, Types.NULL);
			}
			
			LogUtil.logMessage("1111111 :");
			
			if (objRequestModelVO.getModelSeqNo() != 0) {
				objCallableStatement.setInt(2, objRequestModelVO
						.getModelSeqNo());
			} else {
				objCallableStatement.setNull(2, Types.NULL);
			}
			
			if (objRequestModelVO.getSectionSeqNo() != 0) {
				objCallableStatement.setInt(3, objRequestModelVO
						.getSectionSeqNo());
			} else {
				objCallableStatement.setNull(3, Types.NULL);
			}
			
			if (objRequestModelVO.getSubSectionSeqNo() != 0) {
				objCallableStatement.setInt(4, objRequestModelVO
						.getSubSectionSeqNo());
			} else {
				objCallableStatement.setNull(4, Types.NULL);
			}
			
			LogUtil.logMessage("2222222 :");
			if (objRequestModelVO.getRequestCompGrpVO().getChangeTypeSeqNo() != 0) {
				objCallableStatement.setInt(5, objRequestModelVO
						.getRequestCompGrpVO().getChangeTypeSeqNo());
			} else {
				objCallableStatement.setNull(5, Types.NULL);
			}
			
			if (objRequestModelVO.getRequestCompGrpVO().getOldCompGrpSeqNo() > 0) {
				objCallableStatement.setInt(6, objRequestModelVO
						.getRequestCompGrpVO().getOldCompGrpSeqNo());
			} else {
				objCallableStatement.setNull(6, Types.NULL);
			}
			
			LogUtil.logMessage("3333333 :");
			if (objRequestModelVO.getRequestCompGrpVO().getOldCompGrpName() != null
					&& !"".equals(objRequestModelVO.getRequestCompGrpVO()
							.getOldCompGrpName())) {
				objCallableStatement.setString(7, objRequestModelVO
						.getRequestCompGrpVO().getOldCompGrpName());
			} else {
				objCallableStatement.setNull(7, Types.NULL);
			}
			
			if (objRequestModelVO.getRequestCompGrpVO().getOldCompGrpChacFlag() != null
					&& !"".equals(objRequestModelVO.getRequestCompGrpVO()
							.getOldCompGrpChacFlag())) {
				objCallableStatement.setString(8, objRequestModelVO
						.getRequestCompGrpVO().getOldCompGrpChacFlag());
			} else {
				objCallableStatement.setNull(8, Types.NULL);
			}
			
			if (objRequestModelVO.getRequestCompGrpVO()
					.getOldCompGrpValidFlag() != null
					&& !"".equals(objRequestModelVO.getRequestCompGrpVO()
							.getOldCompGrpValidFlag())) {
				objCallableStatement.setString(9, objRequestModelVO
						.getRequestCompGrpVO().getOldCompGrpValidFlag());
			} else {
				objCallableStatement.setNull(9, Types.NULL);
			}
			
			if (objRequestModelVO.getRequestCompGrpVO().getNewCompGrpName() != null
					&& !"".equals(objRequestModelVO.getRequestCompGrpVO()
							.getNewCompGrpName())) {
				objCallableStatement.setString(10, objRequestModelVO
						.getRequestCompGrpVO().getNewCompGrpName());
			} else {
				objCallableStatement.setNull(10, Types.NULL);
			}
			
			LogUtil.logMessage("44444444 :");
			if (objRequestModelVO.getRequestCompGrpVO().getNewCompGrpChacFlag() != null
					&& !"".equals(objRequestModelVO.getRequestCompGrpVO()
							.getNewCompGrpChacFlag())) {
				objCallableStatement.setString(11, objRequestModelVO
						.getRequestCompGrpVO().getNewCompGrpChacFlag());
			} else {
				objCallableStatement.setNull(11, Types.NULL);
			}
			
			if (objRequestModelVO.getRequestCompGrpVO()
					.getNewCompGrpValidFlag() != null
					&& !"".equals(objRequestModelVO.getRequestCompGrpVO()
							.getNewCompGrpValidFlag())) {
				objCallableStatement.setString(12, objRequestModelVO
						.getRequestCompGrpVO().getNewCompGrpValidFlag());
			} else {
				objCallableStatement.setNull(12, Types.NULL);
			}
			LogUtil.logMessage("5555555 :");
			//Component Info
			if (objRequestModelVO.getRequestCompGrpVO().getRequestCompVO()
					.getChangeTypeSeqNo() != 0) {
				objCallableStatement.setInt(13, objRequestModelVO
						.getRequestCompGrpVO().getRequestCompVO()
						.getChangeTypeSeqNo());
			} else {
				objCallableStatement.setNull(13, Types.NULL);
			}
			
			if (objRequestModelVO.getRequestCompGrpVO().getRequestCompVO()
					.getOldCompSeqNo() > 0) {
				objCallableStatement.setInt(14, objRequestModelVO
						.getRequestCompGrpVO().getRequestCompVO()
						.getOldCompSeqNo());
			} else {
				objCallableStatement.setNull(14, Types.NULL);
			}
			
			if (objRequestModelVO.getRequestCompGrpVO().getRequestCompVO()
					.getOldCompName() != null
					&& !"".equals(objRequestModelVO.getRequestCompGrpVO()
							.getRequestCompVO().getOldCompName())) {
				objCallableStatement.setString(15, objRequestModelVO
						.getRequestCompGrpVO().getRequestCompVO()
						.getOldCompName());
			} else {
				objCallableStatement.setNull(15, Types.NULL);
			}
			
			if (objRequestModelVO.getRequestCompGrpVO().getRequestCompVO()
					.getOldCompDefaultFlag() != null
					&& !"".equals(objRequestModelVO.getRequestCompGrpVO()
							.getRequestCompVO().getOldCompDefaultFlag())) {
				objCallableStatement.setString(16, objRequestModelVO
						.getRequestCompGrpVO().getRequestCompVO()
						.getOldCompDefaultFlag());
			} else {
				objCallableStatement.setNull(16, Types.NULL);
			}
			LogUtil.logMessage("6666666 :");
			if (objRequestModelVO.getRequestCompGrpVO().getRequestCompVO()
					.getNewCompName() != null
					&& !"".equals(objRequestModelVO.getRequestCompGrpVO()
							.getRequestCompVO().getNewCompName())) {
				objCallableStatement.setString(17, objRequestModelVO
						.getRequestCompGrpVO().getRequestCompVO()
						.getNewCompName());
			} else {
				objCallableStatement.setNull(17, Types.NULL);
			}
			
			if (objRequestModelVO.getRequestCompGrpVO().getRequestCompVO()
					.getNewCompDefaultFlag() != null
					&& !"".equals(objRequestModelVO.getRequestCompGrpVO()
							.getRequestCompVO().getNewCompDefaultFlag())) {
				objCallableStatement.setString(18, objRequestModelVO
						.getRequestCompGrpVO().getRequestCompVO()
						.getNewCompDefaultFlag());
			} else {
				objCallableStatement.setNull(18, Types.NULL);
			}
			
			if (objRequestModelVO.getReason() != null
					&& !"".equals(objRequestModelVO.getReason())) {
				objCallableStatement.setString(19, objRequestModelVO
						.getReason());
			} else {
				objCallableStatement.setNull(19, Types.NULL);
			}
			LogUtil.logMessage("7777777 :");
			
			if (objRequestModelVO.getAdminComments() != null
					&& !"".equals(objRequestModelVO.getAdminComments())) {
				objCallableStatement.setString(20, objRequestModelVO
						.getAdminComments());
			} else {
				objCallableStatement.setNull(20, Types.NULL);
			}
			if (objRequestModelVO.getRequestDesc() != null
					&& !"".equals(objRequestModelVO.getRequestDesc())) {
				objCallableStatement.setString(21, objRequestModelVO
						.getRequestDesc());
			} else {
				objCallableStatement.setNull(21, Types.NULL);
			}
			
			/*
			 * Change for Model apply flag
			 */
			if (objRequestModelVO.isApplyModelFlag()) {
				objCallableStatement.setString(22, "Y");
			} else {
				objCallableStatement.setString(22, "N");
			}
			
			if (objRequestModelVO.getUserID() != null
					&& !"".equals(objRequestModelVO.getUserID())) {
				objCallableStatement.setString(23, objRequestModelVO
						.getUserID());
			} else {
				objCallableStatement.setNull(23, Types.NULL);
			}
			
			objCallableStatement.registerOutParameter(24, Types.INTEGER);
			
			objCallableStatement.registerOutParameter(25, Types.VARCHAR);
			
			objCallableStatement.registerOutParameter(26, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();

			//Success case
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			//objresultSet = (ResultSet) objCallableStatement.getObject(21);
			
			intLSDBErrorID = (int) objCallableStatement.getInt(24);
			strOracleCode = (String) objCallableStatement.getString(25);
			strErrorMessage = (String) objCallableStatement.getString(26);
			
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
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
		}
		
		catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into DataAccess Exception block in ChangeRequestDAO:insertCompGrpReqDetails"
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ChangeRequestDAO:insertCompGrpReqDetails"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ChangeRequestDAO:insertCompGrpReqDetails"
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
				.logMessage("Enters into Exception block in ChangeRequestDAO:insertCompGrpReqDetails"
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
	 * @param objSectionVO
	 * @return boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static synchronized int resetRequest(RequestVO objRequestVO)
	throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequestDAO:resetRequest");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intStatusCode;
		int intLSDBErrorID = 0;
		String strMessage = null;
		boolean isInserted = false;
		String strLogUser = "";
		try {
			
			strLogUser = objRequestVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_RESET_REQUEST);
			
			objCallableStatement.setInt(1, objRequestVO.getRequestID());
			objCallableStatement.setString(2, objRequestVO.getUserID());
			objCallableStatement.registerOutParameter(3, Types.INTEGER);
			objCallableStatement.registerOutParameter(4, Types.VARCHAR);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			
			intStatusCode = objCallableStatement.executeUpdate();
			
			LogUtil.logMessage("Result:" + intStatusCode);
			if (intStatusCode > 0) {
				intStatusCode = 0;
			}
			
			intLSDBErrorID = objCallableStatement.getInt(3);
			LogUtil.logMessage("Error ID:" + intLSDBErrorID);
			
			strOracleCode = objCallableStatement.getString(4);
			LogUtil.logMessage("Oracle code:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(5);
			
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
			.logMessage("Enters into DataAccessException ChangeRequestDAO:resetRequest");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in ChangeRequestDAO:resetRequest"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException ChangeRequestDAO:resetRequest");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in ChangeRequestDAO:resetRequest"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception ChangeRequestDAO:resetRequest");
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
				.logMessage("Enters into Exception ChangeRequestDAO:resetRequest");
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
	 * @param objSectionVO
	 * @return boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static synchronized int saveRequestStatus(RequestVO objRequestVO)
	throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequestDAO:saveRequestStatus");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intStatusCode;
		int intLSDBErrorID = 0;
		String strMessage = null;
		boolean isInserted = false;
		String strLogUser = "";
		try {
			
			strLogUser = objRequestVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_UPDATE_STATUS_REQ_DTLS);
			
			objCallableStatement.setInt(1, objRequestVO.getRequestID());
			objCallableStatement.setInt(2, objRequestVO.getStatusTypeSeqNo());
			objCallableStatement.setString(3, objRequestVO.getAdminComments());
			objCallableStatement.setString(4, objRequestVO.getUserID());
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			
			intStatusCode = objCallableStatement.executeUpdate();
			
			LogUtil.logMessage("Result:" + intStatusCode);
			if (intStatusCode > 0) {
				intStatusCode = 0;
			}
			
			intLSDBErrorID = objCallableStatement.getInt(5);
			LogUtil.logMessage("Error ID:" + intLSDBErrorID);
			
			strOracleCode = objCallableStatement.getString(6);
			LogUtil.logMessage("Oracle code:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(7);
			
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
			.logMessage("Enters into DataAccessException ChangeRequestDAO:saveRequestStatus");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in ChangeRequestDAO:saveRequestStatus"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException ChangeRequestDAO:saveRequestStatus");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in ChangeRequestDAO:saveRequestStatus"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception ChangeRequestDAO:saveRequestStatus");
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
				.logMessage("Enters into Exception ChangeRequestDAO:saveRequestStatus");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return intStatusCode;
	}

	//Added for CR_80 CR Form Enhancements III on 25-Nov-09 by RR68151
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param RequestVO
	 * @return ArrayList contains the list of status
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList fetchEffectedClauses(RequestVO objRequestVO)
	throws EMDException, Exception {
		LogUtil.logMessage("Inside the  ChangeRequestDAO:fetchEffectedClauses");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strdefaultFlag = "";
		int intLSDBErrorID = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		ResultSet objresultSet = null;
		ResultSet objClauseComp = null;
		ArrayList arlClauseList = new ArrayList();
		String strLogUser = "";
		try {
			strLogUser = objRequestVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in fetchEffectedClauses :"
					+ objConnection);
			
			objCallableStatement = objConnection
									.prepareCall(EMDQueries.SP_SELECT_EFF_CLAUSES);
			
			objCallableStatement.setInt(1, objRequestVO.getRequestID());
			objCallableStatement.setString(2, objRequestVO.getUserID());
			objCallableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			objresultSet = (ResultSet) objCallableStatement.getObject(3);
			intLSDBErrorID = (int) objCallableStatement.getInt(4);
			strOracleCode = (String) objCallableStatement.getString(5);
			strErrorMessage = (String) objCallableStatement.getString(6);
						
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
			
			while (objresultSet.next()) {
				
				ClauseVO objClauseVO = new ClauseVO();
				
				objClauseVO.setClauseSeqNo(objresultSet
						.getInt(DatabaseConstants.LS513_EFF_CLA_SEQ_NO));
				objClauseVO.setVersionNo(objresultSet
						.getInt(DatabaseConstants.LS301_VERSION_NO));
				objClauseVO.setClauseDesc(objresultSet
						.getString(DatabaseConstants.LS301_CLA_DESC));
				
				objClauseComp = (ResultSet) objresultSet.getObject(DatabaseConstants.COMP);
				
				ArrayList arlCompList= new ArrayList();
				
				while (objClauseComp.next()) {
					ComponentVO objComponentVO = new ComponentVO();			
					
					LogUtil.logMessage("Enters Clause Comp loop"); 
					
					objComponentVO.setComponentName(objClauseComp.getString(DatabaseConstants.LS140_COMP_NAME));
					
					objComponentVO.setComponentSeqNo(objClauseComp.getInt(DatabaseConstants.LS140_COMP_SEQ_NO));
					
					strdefaultFlag = (String) objClauseComp.getString(DatabaseConstants.LS204_DEFAULT_FLAG);

					if (strdefaultFlag != null && strdefaultFlag.equalsIgnoreCase("Y")) {
						objComponentVO.setDefaultFlag(true);
					} else {
						objComponentVO.setDefaultFlag(false);
					}
				
					arlCompList.add(objComponentVO);
					
				}
				objClauseVO.setComponentVO(arlCompList);
				
				LogUtil.logMessage("Leaves Clause Comp loop"+arlCompList.size());

				DBHelper.closeSQLObjects(objClauseComp, null, null);
				
				arlClauseList.add(objClauseVO);
				
			}
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception ChangeRequestDAO:fetchEffectedClauses");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		} finally {
			try {
				DBHelper.closeSQLObjects(objresultSet, objCallableStatement,
						objConnection);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception ChangeRequestDAO:fetchEffectedClauses");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		LogUtil.logMessage("objStatusType in ChangeRequestDAO:fetchEffectedClauses :"
				+ arlClauseList);
		return arlClauseList;
		
	}
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSectionVO
	 * @return boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static synchronized int reAssignChangeRequest(RequestVO objRequestVO)
	throws EMDException {
		LogUtil.logMessage("Enters into ChangeRequestDAO:saveRequestStatus");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intStatusCode;
		int intLSDBErrorID = 0;
		String strMessage = null;
		String strLogUser = "";
		try {
			
			strLogUser = objRequestVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_REASSIGN_CR_FORM);
			
			objCallableStatement.setInt(1, objRequestVO.getRequestID());
			objCallableStatement.setString(2, objRequestVO.getAssigneeId());
			objCallableStatement.setString(3, objRequestVO.getUserID());
			objCallableStatement.setString(4, objRequestVO.getUserID());
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			
			intStatusCode = objCallableStatement.executeUpdate();
			
			LogUtil.logMessage("Result:" + intStatusCode);
			if (intStatusCode > 0) {
				intStatusCode = 0;
			}
			
			intLSDBErrorID = objCallableStatement.getInt(5);
			LogUtil.logMessage("Error ID:" + intLSDBErrorID);
			
			strOracleCode = objCallableStatement.getString(6);
			LogUtil.logMessage("Oracle code:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(7);
			
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
			.logMessage("Enters into DataAccessException ChangeRequestDAO:saveRequestStatus");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in ChangeRequestDAO:saveRequestStatus"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException ChangeRequestDAO:saveRequestStatus");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in ChangeRequestDAO:saveRequestStatus"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception ChangeRequestDAO:saveRequestStatus");
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
				.logMessage("Enters into Exception ChangeRequestDAO:saveRequestStatus");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return intStatusCode;
	}	
	//Added for CR_80 CR Form Enhancements III on 25-Nov-09 by RR68151 - Ends Here
}