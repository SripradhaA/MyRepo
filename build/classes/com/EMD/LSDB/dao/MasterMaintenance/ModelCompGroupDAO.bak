/*AK49339
 * Created on Aug 14, 2007
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

import javax.sql.RowSet;

import org.apache.tomcat.dbcp.dbcp2.DelegatingConnection;//Added for Tomcat & CR-123

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
import com.EMD.LSDB.dao.common.AdmnQueries;
import com.EMD.LSDB.dao.common.DBHelper;
import com.EMD.LSDB.dao.common.EMDDAO;
import com.EMD.LSDB.dao.common.EMDQueries;
import com.EMD.LSDB.vo.common.ChangeRequest1058VO;
import com.EMD.LSDB.vo.common.CompGroupVO;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.SpecificationVO;
import com.EMD.LSDB.vo.common.SpecificationItemVO;


/*******************************************************************************************
 * @author  Satyam Computer Services Ltd  
 * @version 1.0  
 * This class has the business methods for the Component Groups 
 ******************************************************************************************/

public class ModelCompGroupDAO extends EMDDAO {
	
	private ModelCompGroupDAO() {
	}
	
	public static ArrayList fetch(CompGroupVO objCompGroupVo)
	throws EMDException {
		LogUtil.logMessage("Entering ComponentGroupDAO.fetch");
		Connection objConnnection = null;
		ArrayList arlCompGrps = new ArrayList();
		CallableStatement callableStatement = null;
		//Error out parameters
		ResultSet objResultSet = null;
		int intLSDBErrorID = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strLogUser = "";
		String strCOCFlag = null;
		
		try {
			strLogUser = objCompGroupVo.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in DAO :" + objConnnection);
			callableStatement = objConnnection
			.prepareCall(EMDQueries.SP_SELECT_COMPGRP);
			if (objCompGroupVo.getComponentGroupSeqNo() == 0) {
				callableStatement.setNull(1, Types.NULL);
			} else {
				callableStatement.setInt(1, objCompGroupVo
						.getComponentGroupSeqNo());
			}
			//Modified For CR_81 on 24-Dec-09 by RR68151
			//callableStatement.setString(2, objCompGroupVo
			//		.getComponentgroupCat());
			
			if (objCompGroupVo.getCompGrpTypeSeqNo() == 0) {
				
				callableStatement.setNull(2, Types.NULL);
			} else {

				callableStatement.setInt(2, objCompGroupVo.getCompGrpTypeSeqNo());
			}
			
			callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			//Added for CR_97 on 15 march 2011 by Sd41630 
			//Added an OR Condition to fix the Issue of "Component Group name not displayed in CR Form"
			if (objCompGroupVo.getSpecTypeSeqNo() == -1 || objCompGroupVo.getSpecTypeSeqNo() == 0) {
				callableStatement.setNull(4, Types.NULL);
			} else {
				callableStatement.setInt(4, objCompGroupVo
						.getSpecTypeSeqNo());
			}
			//CR_97 lines End here
			
			
			callableStatement.setString(5, objCompGroupVo.getUserID());
			callableStatement.registerOutParameter(6, Types.INTEGER);
			callableStatement.registerOutParameter(7, Types.VARCHAR);
			callableStatement.registerOutParameter(8, Types.VARCHAR);
			callableStatement.execute();
			objResultSet = (ResultSet) callableStatement.getObject(3);
			LogUtil
			.logMessage("Inside the fetchComponentGroup method of ComponentGroupDAO :resultSet"
					+ objResultSet);
			intLSDBErrorID = (int) callableStatement.getInt(6);
			strOracleCode = (String) callableStatement.getString(7);
			strErrorMessage = (String) callableStatement.getString(8);
			
			//Handled Valid Exception
			if (intLSDBErrorID != 0) {
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessageID("" + intLSDBErrorID);
				throw new DataAccessException(objErrorInfo);
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {
				//Un handled exception				
				LogUtil.logMessage("strOracleCode:" + strOracleCode);
				ErrorInfo objErrorInfo = new ErrorInfo();
				StringBuffer sb = new StringBuffer();
				sb.append(strOracleCode + " ");
				sb.append(strErrorMessage);
				objErrorInfo.setMessage(sb.toString());
				throw new ApplicationException(objErrorInfo);
			}
			
			while (objResultSet.next()) {
				objCompGroupVo = new CompGroupVO();
				objCompGroupVo.setComponentGroupSeqNo(objResultSet
						.getInt(DatabaseConstants.COMP_GRP_SEQ_NO));
				objCompGroupVo.setComponentGroupName(objResultSet
						.getString(DatabaseConstants.COMP_GRP_NAME));
				objCompGroupVo.setComponentGroupIdentifier(objResultSet
						.getString(DatabaseConstants.COMP_GRP_IDENTIFIER));
				objCompGroupVo.setComments(objResultSet
						.getString(DatabaseConstants.COMP_GRP_DESC));
				//Added For CR_81 on 24-Dec-09 by RR68151
				objCompGroupVo.setCompGrpTypeSeqNo(objResultSet
						.getInt(DatabaseConstants.LS131_COMP_GRP_TYP_SEQ));
				//objCompGroupVo.setCharacterisationFlag(objResultSet
				//		.getString(DatabaseConstants.CHARZ_FLAG));
				//Added and Modified For CR_81 on 24-Dec-09 by RR68151 - Ends here
				/*Commented for CR-127 
				 objCompGroupVo.setValidFlag(objResultSet
						.getString(DatabaseConstants.LS130_VALIDATION_FLAG));*/
				//Added for LSDB_CR-77
				strCOCFlag = objResultSet
				.getString(DatabaseConstants.LS130_COC_FLAG);
				if(strCOCFlag!=null && !"".equals(strCOCFlag)){
					if("Y".equalsIgnoreCase(strCOCFlag)){
						objCompGroupVo.setDisplayInCOCFlag(true);
					}else{
						objCompGroupVo.setDisplayInCOCFlag(false);
					}
				}else{
					objCompGroupVo.setDisplayInCOCFlag(false);
				}
				
				arlCompGrps.add(objCompGroupVo);
			}
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into DataAccess Exception block in ComponentGroupDAO:fetch"
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ComponentGroupDAO:fetch"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ComponentGroupDAO:fetch"
					+ objExp.getMessage());
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(objResultSet, callableStatement,
						objConnnection);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in ComponentGroupDAO:fetch"
						+ objExp.getMessage());
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return arlCompGrps;
		
	}
	
	/*******************************************************************************************
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	objSectionVO    The Object for Insert Component Group 
	 * @return     	boolean  		The Flag for success or failure   
	 * @throws     	EMDException
	 ******************************************************************************************/
	public static int insertCompGroup(CompGroupVO objCompGroupVO)
	throws EMDException {
		LogUtil
		.logMessage("Inside the insertCompGroup method of ModelCompGroupDAO");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		try {
			strLogUser = objCompGroupVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_INSERT_COMPGRP);
			objCallableStatement.setString(1, objCompGroupVO
					.getComponentGroupName());
			objCallableStatement.setString(2, objCompGroupVO.getComments());
			//Modified For CR_81 on 24-Dec-09 by RR68151
			//objCallableStatement.setString(3, objCompGroupVO
			//		.getCharacterisationFlag());
			objCallableStatement.setInt(3, objCompGroupVO
					.getCompGrpTypeSeqNo());
			//Modified For CR_81 on 24-Dec-09 by RR68151 - Ends here
			objCallableStatement.setString(4, objCompGroupVO
					.getComponentGroupIdentifier());
			//Updated for CR-127
			objCallableStatement.setNull(5, Types.NULL);
					
			//Change for LSDB_CR-77
			if(objCompGroupVO.isDisplayInCOCFlag()){
				objCallableStatement.setString(6, "Y");
			}else{
				objCallableStatement.setString(6, "N");
			}
			//Ends
//			Addedfor CR_97
			if (objCompGroupVO.getSpecTypeSeqNo() == -1) {
				objCallableStatement.setNull(7, Types.NULL);
			} else {
				LogUtil
				.logMessage("******************Inside the insertCompGroup method of ModelCompGroupDAO"+objCompGroupVO
						.getSpecTypeSeqNo());
				objCallableStatement.setInt(7, objCompGroupVO
						.getSpecTypeSeqNo());
			}
			objCallableStatement.setString(8, objCompGroupVO.getUserID());
			
			objCallableStatement.registerOutParameter(9, Types.INTEGER);
			
			objCallableStatement.registerOutParameter(10, Types.VARCHAR);
			
			objCallableStatement.registerOutParameter(11, Types.VARCHAR);
			
			objConnnection.setAutoCommit(false);
			intStatus = objCallableStatement.executeUpdate();
			if (intStatus > 0) {
				intStatus = 0;
				//objConnnection.commit();
			}
			LogUtil
			.logMessage("Inside the insertCompGroup method of ModelCompGroupDAO :intStatus .."
					+ intStatus);
			intLSDBErrorID = (int) objCallableStatement.getInt(9);
			strOracleCode = (String) objCallableStatement.getString(10);
			strErrorMessage = (String) objCallableStatement.getString(11);
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Enters into Error Loop:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessageID("" + intLSDBErrorID);
				LogUtil.logMessage("Error message in ErrorInfo:"
						+ objErrorInfo.getMessageID());
				throw new DataAccessException(objErrorInfo);
				
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {
				//Un handled exception				
				LogUtil.logMessage("strOracleCode:" + strOracleCode);
				ErrorInfo objErrorInfo = new ErrorInfo();
				StringBuffer strbuffer = new StringBuffer();
				strbuffer.append(strOracleCode + " ");
				strbuffer.append(strErrorMessage);
				LogUtil.logMessage("sb.toString():" + strbuffer.toString());
				objErrorInfo.setMessage(strbuffer.toString());
				LogUtil.logError(objErrorInfo);
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
		}
		
		catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into DataAccess Exception block in ComponentGroupDAO:insertCompGroup"
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ComponentGroupDAO:insertCompGroup"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ComponentGroupDAO:insertCompGroup"
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
				.logMessage("Enters into Exception block in ComponentGroupDAO:insertCompGroup"
						+ objExp.getMessage());
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return intStatus;
		
	}
	
	/*******************************************************************************************
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	objSectionVO    The Object for Update Component Group  
	 * @return     	boolean  		The Flag for success or failure   
	 * @throws     	EMDException
	 ******************************************************************************************/
	public static int updateCompGroup(CompGroupVO objCompGroupVO)
	throws EMDException {
		LogUtil.logMessage("Entering ModelCompGroupDAO.updateCompGroup");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		int intStatus = 0;
		String strLogUser = "";
		try {
			strLogUser = objCompGroupVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_UPDATE_COMPGRP);
			objCallableStatement.setInt(1, objCompGroupVO
					.getComponentGroupSeqNo());
			objCallableStatement.setString(2, objCompGroupVO
					.getComponentGroupName());
			objCallableStatement.setString(3, objCompGroupVO.getComments());
			//Modified For CR_81 on 24-Dec-09 by RR68151
			//objCallableStatement.setString(4, objCompGroupVO
			//		.getCharacterisationFlag());
			objCallableStatement.setInt(4, objCompGroupVO
					.getCompGrpTypeSeqNo());
			//Modified For CR_81 on 24-Dec-09 by RR68151 - Ends here
			objCallableStatement.setString(5, objCompGroupVO
					.getComponentGroupIdentifier());
			//Updated for CR-127
			objCallableStatement.setNull(6, Types.NULL);
						
			//Change for LSDB_CR-77
			if(objCompGroupVO.isDisplayInCOCFlag()){
				objCallableStatement.setString(7, "Y");
			}else{
				objCallableStatement.setString(7, "N");
			}
			//Ends
			
			objCallableStatement.setString(8, objCompGroupVO.getUserID());
			objCallableStatement.registerOutParameter(9, Types.INTEGER);
			objCallableStatement.registerOutParameter(10, Types.VARCHAR);
			objCallableStatement.registerOutParameter(11, Types.VARCHAR);
			intStatus = objCallableStatement.executeUpdate();
			LogUtil
			.logMessage("Inside the updateCompGroup method of ModelCompGroupDAO :intStatus .."
					+ intStatus);
			if (intStatus > 0) {
				intStatus = 0;
				//objConnnection.commit();
				LogUtil.logMessage("inside intstatus::" + intStatus);
				
			}
			intLSDBErrorID = (int) objCallableStatement.getInt(9);
			strOracleCode = (String) objCallableStatement.getString(10);
			strErrorMessage = (String) objCallableStatement.getString(11);
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Enters into Error Loop:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessageID(String.valueOf(intLSDBErrorID));
				LogUtil.logMessage("Error message in ErrorInfo:"
						+ objErrorInfo.getMessageID());
				throw new DataAccessException(objErrorInfo);
				
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {
				//Un handled exception				
				LogUtil.logMessage("strOracleCode:" + strOracleCode);
				ErrorInfo objErrorInfo = new ErrorInfo();
				StringBuffer strbuffer = new StringBuffer();
				strbuffer.append(strOracleCode + " ");
				strbuffer.append(strErrorMessage);
				LogUtil.logMessage("sb.toString():" + strbuffer.toString());
				objErrorInfo.setMessage(strbuffer.toString());
				LogUtil.logError(objErrorInfo);
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into DataAccess Exception block in ComponentGroupDAO:updateCompGroup"
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ComponentGroupDAO:updateCompGroup"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ComponentGroupDAO:updateCompGroup"
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
				.logMessage("Enters into Exception block in ComponentGroupDAO:updateCompGroup"
						+ objExp.getMessage());
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return intStatus;
		
	}
	
	
	/*******************************************************************************************
	 * Added for LSDB_CR_67 by ka57588
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	objSectionVO    The Object for Update Component Group  
	 * @return     	boolean  		The Flag for success or failure   
	 * @throws     	EMDException
	 ******************************************************************************************/
	public static int deleteCompGroup(CompGroupVO objCompGroupVO)
	throws EMDException {
		LogUtil.logMessage("Entering ModelCompGroupDAO.deleteCompGroup");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		int intStatus = 0;
		String strLogUser = "";
		try {
			LogUtil.logMessage("Component Group Seq No :"+objCompGroupVO
					.getComponentGroupSeqNo());
			strLogUser = objCompGroupVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_MDL_DELETE_COMPGRP);
			objCallableStatement.setInt(1, objCompGroupVO
					.getComponentGroupSeqNo());
			objCallableStatement.setString(2, objCompGroupVO.getUserID());
			objCallableStatement.registerOutParameter(3, Types.INTEGER);
			objCallableStatement.registerOutParameter(4, Types.VARCHAR);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			intStatus = objCallableStatement.executeUpdate();
			if (intStatus > 0) {
				intStatus = 0;
				LogUtil.logMessage("inside intstatus::" + intStatus);
			}
			intLSDBErrorID = (int) objCallableStatement.getInt(3);
			strOracleCode = (String) objCallableStatement.getString(4);
			strErrorMessage = (String) objCallableStatement.getString(5);
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Enters into Error Loop:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessageID(String.valueOf(intLSDBErrorID));
				LogUtil.logMessage("Error message in ErrorInfo:"
						+ objErrorInfo.getMessageID());
				throw new DataAccessException(objErrorInfo);
				
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {
				//Un handled exception				
				LogUtil.logMessage("strOracleCode:" + strOracleCode);
				ErrorInfo objErrorInfo = new ErrorInfo();
				StringBuffer strbuffer = new StringBuffer();
				strbuffer.append(strOracleCode + " ");
				strbuffer.append(strErrorMessage);
				LogUtil.logMessage("sb.toString():" + strbuffer.toString());
				objErrorInfo.setMessage(strbuffer.toString());
				LogUtil.logError(objErrorInfo);
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into DataAccess Exception block in ComponentGroupDAO:deleteCompGroup"
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ComponentGroupDAO:deleteCompGroup"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ComponentGroupDAO:deleteCompGroup"
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
				.logMessage("Enters into Exception block in ComponentGroupDAO:deleteCompGroup"
						+ objExp.getMessage());
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return intStatus;
		
	}
	
	
	/*******************************************************************************************
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	objSectionVO    The Object for fetch Component Group report 
	 * @return     	boolean  		The Flag for success or failure   
	 * @throws     	EMDException
	 ******************************************************************************************/
	
	public static ArrayList fetchCompGrpReport(CompGroupVO objCompGroupVo)
	throws EMDException {
		LogUtil.logMessage("Entering ComponentGroupDAO.fetchCompGrpReport");
		Connection objConnnection = null;
		ArrayList arlCompGrps = new ArrayList();
		CallableStatement objcallableStatement = null;
		//Error out parameters
		ResultSet objResultSet = null;
		
		int intLSDBErrorID = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strLogUser = "";
		//Added For CR_104
		ARRAY objModelSeqNos = null;
		ArrayDescriptor arrayDescriptor = null;
		
		
		try {
			strLogUser = objCompGroupVo.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in DAO :" + objConnnection);
			objcallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_SELECT_COMP_COMPGRP_REPORT);
//			Addedfor CR_97 on 15 march by SD41630 lines starts here 
			if (objCompGroupVo.getSpecTypeSeqNo()==0) {
				LogUtil.logMessage("objConnnectio:" );
				objcallableStatement.setNull(1, Types.NULL);
			} else {								
				objcallableStatement.setInt(1, objCompGroupVo
						.getSpecTypeSeqNo());
			}
			//CR)97 Lines ends here 
			objcallableStatement.setString(2, objCompGroupVo.getUserID());
				//Added for CR_101 to include the Order level component flag
				objcallableStatement.setString(3, objCompGroupVo.getIncOrderComp());
				//String orderFlag = objCompGroupVo.getIncOrderComp();
			//CR_101 Ends here
			//Added For CR_104
			Connection dconn = ((DelegatingConnection) objConnnection).getInnermostDelegate(); //Added for CR-123	
			arrayDescriptor = new ArrayDescriptor(DatabaseConstants.STR_ARRAY, dconn);
			
			if(objCompGroupVo.getModelSelected()!=null){
			if (objCompGroupVo.getModelSelected().length != 0) {
				objModelSeqNos = new ARRAY(arrayDescriptor, dconn,
						objCompGroupVo.getModelSelected());
					}
			} else {
				objModelSeqNos = new ARRAY(arrayDescriptor, dconn,
						null);
							}
					
						
			objcallableStatement.setArray(4, objModelSeqNos);
			//Added for CR_108
			if (objCompGroupVo.getComponentGroupSeqNo() != 0 && objCompGroupVo.getComponentGroupSeqNo() != -1)
				objcallableStatement.setInt(5, objCompGroupVo.getComponentGroupSeqNo());
			else
				objcallableStatement.setNull(5, Types.NULL);
			
			if (objCompGroupVo.getComponentSeqNo() != 0 && objCompGroupVo.getComponentSeqNo() != -1)
				objcallableStatement.setInt(6, objCompGroupVo.getComponentSeqNo());
			else
				objcallableStatement.setNull(6, Types.NULL);
			
			if (objCompGroupVo.getShowLatestPubSpecFlag() != null && !"".equalsIgnoreCase(objCompGroupVo.getShowLatestPubSpecFlag()))
				objcallableStatement.setString(7, objCompGroupVo.getShowLatestPubSpecFlag());
			else
				objcallableStatement.setNull(7, Types.NULL);
			//Added for CR_108 - Ends here
			
			//Added for CR-121 for sorting by Vb106565 starts here 
			if (objCompGroupVo.getOrderbyCompgrpFlag() != 0 && objCompGroupVo.getOrderbyCompgrpFlag()!=-1)
				objcallableStatement.setInt(8, objCompGroupVo.getOrderbyCompgrpFlag());
			else
				objcallableStatement.setInt(8, ApplicationConstants.ORDERBY_FLAG_ONE);
			LogUtil.logMessage("Order by Flag: "+objCompGroupVo.getOrderbyCompgrpFlag());
			
			if (objCompGroupVo.getOrderbyCompFlag() != 0 && objCompGroupVo.getOrderbyCompFlag() != -1)
				objcallableStatement.setInt(9, objCompGroupVo.getOrderbyCompFlag());
			else
				objcallableStatement.setInt(9, ApplicationConstants.ORDERBY_FLAG_ONE);
			LogUtil.logMessage("Order by Flag: "+objCompGroupVo.getOrderbyCompFlag());
			//Added for CR-121 for sorting by Vb106565 ends here 

			objcallableStatement.registerOutParameter(10, OracleTypes.CURSOR);
			objcallableStatement.registerOutParameter(11, Types.INTEGER);
			objcallableStatement.registerOutParameter(12, Types.VARCHAR);
			objcallableStatement.registerOutParameter(13, Types.VARCHAR);
					
			objcallableStatement.execute();
					
			objResultSet = (ResultSet) objcallableStatement.getObject(10);
			intLSDBErrorID = (int) objcallableStatement.getInt(11);
			strOracleCode = (String) objcallableStatement.getString(12);
			strErrorMessage = (String) objcallableStatement.getString(13);
			
			//Handled Valid Exception
			if (intLSDBErrorID != 0) {
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessageID("" + intLSDBErrorID);
				throw new DataAccessException(objErrorInfo);
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {
				//Un handled exception				
				LogUtil.logMessage("strOracleCode:" + strOracleCode);
				ErrorInfo objErrorInfo = new ErrorInfo();
				StringBuffer sb = new StringBuffer();
				sb.append(strOracleCode + " ");
				sb.append(strErrorMessage);
				objErrorInfo.setMessage(sb.toString());
				throw new ApplicationException(objErrorInfo);
			}
			
			while (objResultSet.next()) {
				objCompGroupVo = new CompGroupVO();
				
				ResultSet objComponent = null;
				ResultSet objModelsAffected = null;
				ResultSet objModelDefault = null;
				ResultSet objOrdersUsed = null; //Added For CR_108
				ArrayList arlComponent = new ArrayList();
				objCompGroupVo.setComponentGroupSeqNo(objResultSet
						.getInt(DatabaseConstants.COMP_GRP_SEQ_NO));
				objCompGroupVo.setComponentGroupName(objResultSet
						.getString(DatabaseConstants.COMP_GRP_NAME));
				objCompGroupVo.setComponentGroupIdentifier(objResultSet
						.getString(DatabaseConstants.COMP_GRP_IDENTIFIER));
				objCompGroupVo.setCharacterisationFlag(objResultSet
						.getString(DatabaseConstants.CHARACTERIZATION));
				//Added For CR_81 by RR68151 on 07-Jan-2010
				objCompGroupVo.setCompGrpTypeName(objResultSet
						.getString(DatabaseConstants.LS131_COMP_GRP_TYP_DESC));
				//Added For CR_81 by RR68151 on 07-Jan-2010 - Ends here
				objComponent = (ResultSet) objResultSet.getObject(5);
				
				while (objComponent.next()) {
					ComponentVO objComponentVO = new ComponentVO();
					ArrayList arlModelAffected = new ArrayList();
					ArrayList arlModelDefault = new ArrayList();
					ArrayList arlOrdersUsed = new ArrayList();
					objComponentVO.setComponentName(objComponent
							.getString(DatabaseConstants.COMP_NAME));
					objComponentVO.setComponentIdentifier(objComponent
							.getString(DatabaseConstants.COMP_IDENTIFIER));
					//Added for CR_101 to include order level components
					objComponentVO.setCompSourceFlag(objComponent
							.getString(DatabaseConstants.COMP_SOURCE_CODE));
					//CR_101 Ends here
					objModelsAffected = (ResultSet) objComponent
					.getObject(DatabaseConstants.MODELS_USED);
					
					while (objModelsAffected.next()) {
						arlModelAffected.add(objModelsAffected
								.getString(DatabaseConstants.MDL_NAME));
					}
					objComponentVO.setModelsAffected(arlModelAffected);
					DBHelper.closeSQLObjects(objModelsAffected, null, null);
					
					objModelDefault = (ResultSet) objComponent
					.getObject(DatabaseConstants.DEF_MODELS);
					
					while (objModelDefault.next()) {
						arlModelDefault.add(objModelDefault
								.getString(DatabaseConstants.MDL_NAME));
					}
					objComponentVO.setModelDefault(arlModelDefault);
					//arlComponent.add(objComponentVO);
					DBHelper.closeSQLObjects(objModelDefault, null, null);
					
					//Added for CR_108 to include Components in Order Report
					
					objOrdersUsed = (ResultSet) objComponent.getObject(DatabaseConstants.ORDERS_USED);
					
					while (objOrdersUsed.next()) {
						arlOrdersUsed.add(objOrdersUsed
								.getString(DatabaseConstants.LS400_ORDR_NO));
						arlOrdersUsed.add(objOrdersUsed
								.getString(DatabaseConstants.STATUS));
						//Added for CR_112 By ER91220 to bring Customer Name in Components In Order Report
						arlOrdersUsed.add(objOrdersUsed
								.getString(DatabaseConstants.CUSTOMER_NAME));
						//CR_112 Ends here
					}
					objComponentVO.setOrdersUsed(arlOrdersUsed);
					arlComponent.add(objComponentVO);
					DBHelper.closeSQLObjects(objOrdersUsed, null, null);
					//Added for CR_108 to include Components in Order Report - Ends here
				}
				DBHelper.closeSQLObjects(objComponent, null, null);
				
				objCompGroupVo.setComponentVO(arlComponent);
				
				arlCompGrps.add(objCompGroupVo);
				
			}
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into DataAccess Exception block in ComponentGroupDAO:fetch "
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ComponentGroupDAO:fetch"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ComponentGroupDAO:fetch"
					+ objExp.getMessage());
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(objResultSet, objcallableStatement,
						objConnnection);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in ComponentGroupDAO:fetch"
						+ objExp.getMessage());
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return arlCompGrps;
		
	}

	//Added For CR_81 on 24-Dec-09 by RR68151
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objCompGroupVo
	 * @return ArrayList contains the list of Component Group Types
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList fetchCompGrpTypes(CompGroupVO objCompGroupVo)
	throws EMDException {
		LogUtil.logMessage("Inside the  ComponentGroupDAO:fetchCompGrpTypes");
		Connection objConnnection = null;
		ArrayList objCompGrpType = new ArrayList();
		List objAlCompGrpTypes = new ArrayList();
		String strLogUser = "";
		try {
			strLogUser = objCompGroupVo.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in fetchSpecTypes :"
					+ objConnnection);
			
			RowSet compGrpType = DBHelper.executeQuery(objConnnection,
					EMDQueries.Query_Comp_Group_Type, objAlCompGrpTypes);
			
			LogUtil.logMessage("compGrpType in fetchCompGrpTypes :" + compGrpType);
			
			while (compGrpType.next()) {
				LogUtil.logMessage("inside loop type seq No :"
						+ compGrpType.getInt(DatabaseConstants.LS131_COMP_GRP_TYP_SEQ));
				LogUtil.logMessage("inside loop type Name :"
						+ compGrpType.getString(DatabaseConstants.LS131_COMP_GRP_TYP_DESC));
				
				objCompGroupVo = new CompGroupVO();
				
				objCompGroupVo.setCompGrpTypeSeqNo(compGrpType
						.getInt(DatabaseConstants.LS131_COMP_GRP_TYP_SEQ));
				objCompGroupVo.setCompGrpTypeName(compGrpType
						.getString(DatabaseConstants.LS131_COMP_GRP_TYP_DESC));
				
				objCompGrpType.add(objCompGroupVo);
			}
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception ComponentGroupDAO:fetchCompGrpTypes");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		} finally {
			try {
				DBHelper.closeConnection(objConnnection);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception ComponentGroupDAO:fetchCompGrpTypes");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		LogUtil.logMessage("objCompGrpType in fetchCompGrpTypes :" + objCompGrpType);
		return objCompGrpType;
		
	}
	
	//Added For CR_81 on 24-Dec-09 by RR68151 - Ends here
	
	//Added for CR-121
	

	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objCompGroupVo
	 * @return ArrayList contains the list of Component Group Types
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList viewCompInOrdMap(CompGroupVO objCompGroupVo)
	throws EMDException {
		LogUtil.logMessage("Inside the  ComponentGroupDAO:fetchCompInOrdMap");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		ResultSet objCommonResultSet  	= null;
		ResultSet objSpecItemsResultSet = null;
		ResultSet objItemsResultSet		= null;
		ArrayList arlCommonList 	= new ArrayList();
		ArrayDescriptor arrayDescriptor = null;
		ARRAY objArray = null;
		try {
				objConnection = DBHelper.prepareConnection();
				objCallableStatement = objConnection.prepareCall(EMDQueries.SP_SEL_SPEC_ITEM_MLTPL_ORDRS);
				//LogUtil.logMessage("objCompGroupVo.getOrderKeys():"+objCompGroupVo.getOrderKeys());
				
				Connection dconn = ((DelegatingConnection) objConnection).getInnermostDelegate(); //Added for CR-123 & Tomcat	
				arrayDescriptor = new ArrayDescriptor(DatabaseConstants.IN_ARRAY, dconn);
				if(objCompGroupVo.getOrderKeys() != null )
				{
					objArray = new ARRAY(arrayDescriptor, dconn,objCompGroupVo.getOrderKeys());
				}
				else
				{
					LogUtil.logMessage("objCompGroupVo.getOrderKeys():null");
					objArray = new ARRAY(arrayDescriptor, dconn, null); 
				}
				objCallableStatement.setArray(1,objArray);
				
				
				objCallableStatement.setString(2, objCompGroupVo.getDataLocationType());
				
				if(objCompGroupVo.getUserID() == null || "".equalsIgnoreCase(objCompGroupVo.getUserID())){
					objCallableStatement.setNull(3, Types.NULL);
				}else {
					objCallableStatement.setString(3, objCompGroupVo.getUserID());
				}
				objCallableStatement.registerOutParameter(4, OracleTypes.CURSOR);
				objCallableStatement.registerOutParameter(5, Types.INTEGER);
				objCallableStatement.registerOutParameter(6, Types.VARCHAR);
				objCallableStatement.registerOutParameter(7, Types.VARCHAR);
				
				objCallableStatement.execute();
				
				objCommonResultSet = (ResultSet) objCallableStatement.getObject(4);
			
				
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
				
			while(objCommonResultSet.next()){
				//LogUtil.logMessage("Enters into objCommonResultSet:");
				CompGroupVO objCompGroupVO =new CompGroupVO();
				objCompGroupVO.setSpecDesc(objCommonResultSet.getString(DatabaseConstants.LS401_SPEC_DESC));
				
				//Cursor of SpecItems 				
				objSpecItemsResultSet = (ResultSet) objCommonResultSet.getObject("SPEC_ITEMS");
				ArrayList arlSpecItemsList 	= new ArrayList();
				while (objSpecItemsResultSet.next()) {
				//LogUtil.logMessage("Inside objSpecItemsResultSet");
				SpecificationVO objSpecificationVO =new SpecificationVO();
				objSpecificationVO.setOrderKey(objSpecItemsResultSet.getInt(DatabaseConstants.LS400_ORDR_KEY));
				objSpecificationVO.setItemCount(objSpecItemsResultSet.getInt(DatabaseConstants.ITEMS_COUNT));
				objSpecificationVO.setSpecSeqNo(objSpecItemsResultSet.getInt(DatabaseConstants.LS401_ORDR_SPEC_SEQ_NO));
				//Cursor of Items
				objItemsResultSet = (ResultSet) objSpecItemsResultSet.getObject("ITEMS");
				ArrayList arlItemsList 	= new ArrayList();
				while (objItemsResultSet.next()) {
				//LogUtil.logMessage("Inside objItemsResultSet");
				SpecificationItemVO objSpecificationItemVO =new SpecificationItemVO();	
				objSpecificationItemVO.setItemDesc(objItemsResultSet.getString(DatabaseConstants.LS402_ITEM_DESC));
				objSpecificationItemVO.setItemValue(objItemsResultSet.getString(DatabaseConstants.LS402_ITEM_VALUE));
				arlItemsList.add(objSpecificationItemVO);
				}
				objSpecificationVO.setSpecItemList(arlItemsList);
				arlSpecItemsList.add(objSpecificationVO);
				DBHelper.closeSQLObjects(objItemsResultSet, null, null);	
				
				}
				objCompGroupVO.setSpecificationItemVO(arlSpecItemsList);
				DBHelper.closeSQLObjects(objSpecItemsResultSet, null, null);
				
				arlCommonList.add(objCompGroupVO);
			}
			
			DBHelper.closeSQLObjects(objCommonResultSet, null, null);
			//LogUtil.logMessage("arlCommonList.size():"+arlCommonList.size());
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
		return arlCommonList;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objCompGroupVo
	 * @return ArrayList contains the list of Component Group Types
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList viewCompGrpInOrdMap(CompGroupVO objCompGroupVo)
	throws EMDException {
		LogUtil.logMessage("Inside the  ComponentGroupDAO:viewCompGrpInOrdMap");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		ResultSet objCommonResultSet  	= null;
		ResultSet objCompGrpsResultSet = null;
		ArrayList arlCommonList 	= new ArrayList();
		ArrayDescriptor arrayDescriptor = null;
		ARRAY objArray = null;
		try {
				objConnection = DBHelper.prepareConnection();
				objCallableStatement = objConnection.prepareCall(EMDQueries.SP_SEL_COMP_FRM_MLTPL_ORDRS);
				//LogUtil.logMessage("objCompGroupVo.getOrderKeys():"+objCompGroupVo.getOrderKeys());
				
				Connection dconn = ((DelegatingConnection) objConnection).getInnermostDelegate(); //Added for CR-123 & Tomcat	
				arrayDescriptor = new ArrayDescriptor(DatabaseConstants.IN_ARRAY, dconn);
				if(objCompGroupVo.getOrderKeys() != null )
				{
					objArray = new ARRAY(arrayDescriptor, dconn,objCompGroupVo.getOrderKeys());
				}
				else
				{
					LogUtil.logMessage("objCompGroupVo.getOrderKeys():null");
					objArray = new ARRAY(arrayDescriptor, dconn, null); 
				}
				objCallableStatement.setArray(1,objArray);
				
				
				objCallableStatement.setString(2, objCompGroupVo.getDataLocationType());
				
				if(objCompGroupVo.getUserID() == null || "".equalsIgnoreCase(objCompGroupVo.getUserID())){
					objCallableStatement.setNull(3, Types.NULL);
				}else {
					objCallableStatement.setString(3, objCompGroupVo.getUserID());
				}
				objCallableStatement.registerOutParameter(4, OracleTypes.CURSOR);
				objCallableStatement.registerOutParameter(5, Types.INTEGER);
				objCallableStatement.registerOutParameter(6, Types.VARCHAR);
				objCallableStatement.registerOutParameter(7, Types.VARCHAR);
				
				objCallableStatement.execute();
				
				objCommonResultSet = (ResultSet) objCallableStatement.getObject(4);
			
				
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
				
			while(objCommonResultSet.next()){
				//LogUtil.logMessage("Enters into objCommonResultSet:");
				CompGroupVO objCompGroupVO =new CompGroupVO();
				
				objCompGroupVO.setComponentGroupName(objCommonResultSet.getString(DatabaseConstants.LS130_COMP_GRP_NAME));
				
				//Cursor of Compgrps 				
				objCompGrpsResultSet = (ResultSet) objCommonResultSet.getObject("COMPONENTS");
				ArrayList arlCompGrpsList 	= new ArrayList();
				while (objCompGrpsResultSet.next()) {
				//LogUtil.logMessage("Inside objCompGrpsResultSet");
				ComponentVO objComponentVo = new ComponentVO();
				objComponentVo.setOrderKey(objCompGrpsResultSet.getInt(DatabaseConstants.LS400_ORDR_KEY));
				objComponentVo.setComponentGroupSeqNo(objCompGrpsResultSet.getInt(DatabaseConstants.LS130_COMP_GRP_SEQ_NO));
				objComponentVo.setComponentName(objCompGrpsResultSet.getString(DatabaseConstants.LS140_COMP_NAME));
				objComponentVo.setCompColorFlag(objCompGrpsResultSet.getString(DatabaseConstants.COLOUR_FLAG));
				arlCompGrpsList.add(objComponentVo);
				}
				objCompGroupVO.setComponentVO(arlCompGrpsList);
				DBHelper.closeSQLObjects(objCompGrpsResultSet, null, null);
				
				arlCommonList.add(objCompGroupVO);
			}
			
			DBHelper.closeSQLObjects(objCommonResultSet, null, null);
			//LogUtil.logMessage("arlCommonList.size():"+arlCommonList.size());
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
		return arlCommonList;
		
	}
}